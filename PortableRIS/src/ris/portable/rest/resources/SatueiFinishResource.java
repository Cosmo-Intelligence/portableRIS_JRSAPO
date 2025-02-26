package ris.portable.rest.resources;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExBuiBean;
import ris.lib.core.bean.ExSatueiBean;
import ris.lib.core.bean.ExecutionInfoBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.PresetInfoBean;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.PresetParameter;
import ris.lib.core.util.RequestParameter;
import ris.lib.core.util.TextUtil;
import ris.portable.common.Application;
import ris.portable.common.Config;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.database.DataBase;
import ris.portable.model.dto.SatueiFinishDto;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;
import ris.portable.util.Util;

@Path("/satueiFinish")
public class SatueiFinishResource {

	private static Log logger = LogFactory.getLog(SatueiFinishResource.class);

	@GET
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@QueryParam("ris_id") String ris_id,
			@QueryParam("kensasitu_id") String kensasitu_id,
			@QueryParam("kensakiki_id") String kensakiki_id,
			@Context HttpServletRequest request) throws Exception {

        return doPost(ris_id, kensasitu_id, kensakiki_id, request);
	}

	@POST
	@Produces("application/json;charset=UTF-8")
	public Response doPost(
			@FormParam("ris_id") String ris_id,
			@FormParam("kensasitu_id") String kensasitu_id,
			@FormParam("kensakiki_id") String kensakiki_id,
			@Context HttpServletRequest request) throws Exception {

		logger.debug("☆★☆撮影済リクエスト---開始");

		// 連絡メモ更新情報
		SatueiFinishDto satueiFinish = new SatueiFinishDto();

		// 実行
		Execute(ris_id, kensasitu_id, kensakiki_id, request, satueiFinish);

		// JSON変換
		String json = Util.getJson(satueiFinish);

		logger.debug("☆★☆撮影済リクエスト---JSON:" + json);

		logger.debug("☆★☆撮影済リクエスト---終了");

        return Response.ok().entity(json).build();
	}

	/**
	 * 処理実行
	 * @param ris_id       :RIS識別ID
	 * @param no           :部位No
	 * @param request      :httpリクエスト
	 * @param dto          :撮影済情報
	 * @return
	 */
	private boolean Execute(
			String ris_id,
			String kensasitu_id,
			String kensakiki_id,
			HttpServletRequest request,
			SatueiFinishDto dto) throws Exception {

		try{
			// パラメータ取得不可判定
			if (StringUtils.isEmpty(ris_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("引数が不足している為、処理が行えませんでした。");
				return false;
			}

			// 設定ファイル情報取得
			Config config = (Config)SessionControler.getValue(request, SessionControler.SYSTEMCONFIG);

			// 設定ファイル情報取得判定
			if (config == null) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_ERROR);
				dto.setMsg("システム設定情報が取得できませんでした。");
				return false;
			}

			// コネクション取得
			Connection risconn = DataBase.getRisConnection();

			// コネクション取得判定
			if (risconn == null) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_ERROR);
				dto.setMsg("データベースへ接続できませんでした。");
				return false;
			}

			try {

				// アプリケーションクラス
				Application app = new Application();
                // 管理クラス初期化
                app.InitConfiguration(risconn, request);

                ExecutionInfoBean exBean = null;
                OrderInfoBean orderBean = null;

				// 検査ステータス更新
				boolean result = false;

                // 登録情報取得
        		// 実績メイン情報取得
        		exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(ris_id, 1, risconn);

        		// オーダ情報取得
        		orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(ris_id, 1, risconn);


        		// 検査系か撮影系かの判定(true：検査系、false：撮影系)
				boolean iskensakei = isOperationKbnInspect(orderBean.GetKensatypeID(), risconn);

				// 撮影区分が撮影系の場合 & 実績撮影情報が無い場合、プリセットからデータを作成する
				if (exBean.GetExSatueiList().size() == 0 && !iskensakei){

				// mod end 201806_ポータブルRIS検査系種別対応
    				ArrayList satueiList = new ArrayList();
    				ArrayList buiList = new ArrayList();


    				// 部位情報件数分実行
    				for (int i = 0; i < exBean.GetExBuiList().size(); i++) {

    					ExBuiBean buiBean = (ExBuiBean) exBean.GetExBuiList().get(i);

    					PresetInfoBean presetinfo = getPreset(exBean.GetKensaTypeID(), buiBean, risconn);

    					if (presetinfo != null) {

    						Integer no = 1;

    						// 撮影情報
    						for (int n = 0; n < presetinfo.GetSatueiList().size(); n++){

    							DataRow satueiRow = (DataRow) presetinfo.GetSatueiList().get(n);

    							// 撮影情報Bean作成
    							ExSatueiBean satueiBean = new ExSatueiBean();

    							// 撮影情報の検索
    							RequestParameter rParam = new RequestParameter();
    							rParam.SetSatueiMenuIDs(satueiRow.get(MasterUtil.RIS_SATUEIMENU_ID).toString());
    							DataTable menuDt = Configuration.GetInstance().GetCoreController().GetRisSatueiMenuDataTable(rParam, risconn);

    							if (menuDt.getRowCount() > 0)
    							{
    								buiBean.SetSatueiStatus("1");
    								buiBean.SetKensaSituID(kensasitu_id);
    								buiBean.SetKensaKikiID(kensakiki_id);

    								satueiBean.SetRisID(exBean.GetRisID());
    								satueiBean.SetBuiNo(buiBean.GetNo());
    								satueiBean.SetNo(no.toString());
    								satueiBean.SetSatueiStatus("1");
    								satueiBean.SetSatueiMenuID(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_ID).toString());
    								satueiBean.SetSatueiMenuName(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_NAME).toString());
    								satueiBean.SetSatueiMenuNameKana(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_NAME_KANA).toString());
    								satueiBean.SetSatueiCode(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEI_CODE + "01").toString());
    								satueiBean.SetFilmID(menuDt.getRows().get(0).get(MasterUtil.RIS_FILM_ID).toString());
    								satueiBean.SetPartition(menuDt.getRows().get(0).get(MasterUtil.RIS_PARTITION).toString());
    								satueiBean.SetUsed(menuDt.getRows().get(0).get(MasterUtil.RIS_USED).toString());
    								satueiBean.SetExamDataToBean2(menuDt.getRows().get(0));	//ExamData01~20
    								satueiBean.SetMppsDataToBean(satueiRow);	//Mpps情報
        							//
        							satueiList.add(satueiBean);

        							no++;
    							}
    						}
    					}
						buiList.add(buiBean);
    				}
    				// 各リストを設定
    				exBean.ReconstructExSatueiList(satueiList);
    				exBean.ReconstructExBuiList(buiList);
				}
				// 保留以上やMWM登録時など撮影済ボタンが押下される前にEXSATUEITABLEにデータがある場合は
				// EXSATUEITABLE.SATUEISTATUSとEXBUITABLEのSATUEISTATUS、KENSASITU_ID、KENSAKIKI_IDを更新する
				else if (exBean.GetExSatueiList().size() > 0 && !iskensakei) {
    				ArrayList satueiList = new ArrayList();
    				ArrayList buiList = new ArrayList();
    				ArrayList buiNoList = new ArrayList();

    				// 撮影情報件数分実行
    				for (int i = 0; i < exBean.GetExSatueiList().size(); i++) {
						// 撮影情報Bean作成
						ExSatueiBean satueiBean = (ExSatueiBean) exBean.GetExSatueiList().get(i);
						satueiBean.SetSatueiStatus("1");
						satueiList.add(satueiBean);

						// 撮影情報と紐づく部位情報がある場合のみ部位情報の撮影ステータスを１にするため、
						// 撮影情報に存在する対象オーダの部位NOを格納
						if(!buiNoList.contains(satueiBean.GetBuiNo())) {
							buiNoList.add(satueiBean.GetBuiNo());
						}
    				}

//    				// 部位情報件数分実行
    				for (int i = 0; i < exBean.GetExBuiList().size(); i++) {

    					ExBuiBean buiBean = (ExBuiBean) exBean.GetExBuiList().get(i);

    					// 撮影情報の部位NOと部位情報のNOが一致するもののみステータスを１にする
    					// ※撮影情報にデータがない場合は、対象オーダの部位情報に紐づくPRESETMASTERやCRSATUEIMASTERのデータがないことが原因
    					if (buiNoList.contains(buiBean.GetNo())) {
							buiBean.SetSatueiStatus("1");
							buiBean.SetKensaSituID(kensasitu_id);
							buiBean.SetKensaKikiID(kensakiki_id);
    					}

						buiList.add(buiBean);

    				}
    				// 各リストを設定
    				exBean.ReconstructExSatueiList(satueiList);
    				exBean.ReconstructExBuiList(buiList);
				}

                // RIS識別IDを設定
				exBean.SetRisID(ris_id);
                // 検査室を設定
                exBean.SetKensaSituID(Util.toNullString(kensasitu_id));
                // 検査機器を設定
                exBean.SetKensaKikiID(Util.toNullString(kensakiki_id));

                // 撮影情報(EXBUITABLE、EXSATUEITABLE)を登録、EXMAINTABLEを更新
				result = Configuration.GetInstance().GetCoreController().RegistSatueiRisExecutionInfo(exBean, risconn);

				if (!result) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("撮影情報の登録に失敗しました。");
					return false;
				}
				else {
					risconn.commit();

					// 排他を解除(登録後、詳細画面を更新するため)
					Configuration.GetInstance().GetCoreController().unCheckOutExclusive(dto, ris_id, risconn, config.getExclusive());
				}

			} catch (Exception e) {
				// ロールバック
				risconn.rollback();
				throw e;
			} finally {
				DataBase.closeConnection(risconn);
			}

		} catch (Exception ex) {
			logger.error(ex.toString(), ex);
			dto.setResult(Const.RESULT_NG);
			dto.setErrlevel(Const.ERRLEVEL_ERROR);
			dto.setMsg(ex.getMessage());
			return false;
		}

		return true;
	}

	/// <summary>
	/// 検査系判定
	/// </summary>
	/// <param name="kensatypeId">検査種別ID</param>
	/// <param name="risconn">Connectionオブジェクト</param>
	/// <returns></returns>
	private boolean isOperationKbnInspect(String kensatypeId, Connection risconn) throws Exception {

		String opeKbn = Configuration.GetInstance().GetOperationKbn(null, kensatypeId, risconn);

		return CommonString.OPE_KBN_INSPECT.equals(opeKbn);

	}

	/**
	 * プリセット情報取得
	 * @param kensatypeId
	 * @param buiBean
	 * @param con
	 * @return
	 */
	private PresetInfoBean getPreset(String kensatypeId, ExBuiBean buiBean, Connection con) {

		// プリセットの確認を行う
		PresetParameter pParam = new PresetParameter();
		pParam.SetKensatypeID(kensatypeId);
		pParam.SetBuiID(buiBean.GetBuiID());
		pParam.SetHoukouID(buiBean.GetHoukouID());
		pParam.SetSayuuID(buiBean.GetSayuuID());
		pParam.SetKensaHouhouID(buiBean.GetKensaHouhouID());
		pParam.SetUseFlag(TextUtil.ParseStringToInt(CommonString.USEFLAG_ON));
		// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
		ArrayList presetBeanList = Configuration.GetInstance().GetCoreController().GetRisPresetInfoData(pParam, 1, con);
//		ArrayList presetBeanList = Configuration.GetInstance().GetCoreController().GetRisPresetInfoData(pParam, con);
		// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加

		PresetInfoBean presetBean = null;
		if (presetBeanList != null && presetBeanList.size() > 0)
		{
			presetBean = (PresetInfoBean)presetBeanList.get(0);
		}

		return presetBean;
	}

}
