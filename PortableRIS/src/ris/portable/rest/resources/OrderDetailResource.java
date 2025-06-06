package ris.portable.rest.resources;

import java.sql.Connection;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import ris.lib.core.Configuration;
import ris.lib.core.bean.AccessInfoBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.database.RisAccessInfoTbl;
import ris.lib.core.util.MasterUtil;
import ris.portable.common.Application;
import ris.portable.common.Config;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.database.DataBase;
import ris.portable.model.dto.OrderDetailDto;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;
import ris.portable.util.Util;

@Path("/orderdetail")
public class OrderDetailResource {

	private static Log logger = LogFactory.getLog(OrderDetailResource.class);

	// デバッグURL
	// http://localhost:8080/PortableRIS/rest/orderdetail?ris_id=2010102610004044

	@GET
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@QueryParam("ris_id") String ris_id,
			@Context HttpServletRequest request) throws Exception {

        return doPost(ris_id, request);
	}

	@POST
	@Produces("application/json;charset=UTF-8")
	public Response doPost(
			@FormParam("ris_id") String ris_id,
			@Context HttpServletRequest request) throws Exception {

		logger.debug("☆★☆オーダ詳細情報取得リクエスト---開始");

		// オーダ詳細情報
		OrderDetailDto order = new OrderDetailDto();

		// 実行
		Execute(ris_id, request, order);

		// JSON変換
		String json = Util.getJson(order);

		logger.debug("☆★☆オーダ詳細情報取得リクエスト---JSON:" + json);

		logger.debug("☆★☆オーダ詳細情報取得リクエスト---終了");

        return Response.ok().entity(json).build();
	}

	/**
	 * 処理実行
	 * @param ris_id  :RIS識別ID
	 * @param request :httpリクエスト
	 * @param dto     :オーダ詳細情報
	 * @return
	 */
	private boolean Execute(
			String ris_id,
			HttpServletRequest request,
			OrderDetailDto dto) throws Exception {

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
			
			// 一括MWMオーダー操作制御
			if(DataBase.isMWMOrder(ris_id,config.getStatuscode_ukezumi(), risconn)) {
				String errMsg = "対象オーダは検査開始用に操作済の為、詳細画面の表示は行えません。";
				logger.debug(errMsg);
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg(errMsg);
				return false;
			}

			// add sta 201806_ポータブルRIS検査系種別対応
			// アプリケーションクラス
			Application app = new Application();
            // 管理クラス初期化
            app.InitConfiguration(risconn, request);
            // add end 201806_ポータブルRIS検査系種別対応

			try {
				// 2019.08.06 Add Cosmo Start 排他対応
				if(config.getExclusive()){
					// オーダー情報をチェックアウトする
					AccessInfoBean accessBean = new AccessInfoBean();
					UserAccountBean userBean = Configuration.GetInstance().GetUserAccountBean();
					accessBean.SetID(ris_id);
					accessBean.SetAppID(config.getAppid());
					accessBean.SetIpAddress(userBean.GetIPAddress());
					String checkoutID = Configuration.GetInstance().GetCoreController().CheckoutRisOrder(accessBean, risconn);
					if (checkoutID != null && checkoutID.length() > 0)
					{
						risconn.commit();
					}else{
						//①チェックアウト情報取得
						DataRow checkoutRow = Configuration.GetInstance().GetCoreController().GetCheckoutData(ris_id, risconn);
						if (checkoutRow != null)
						{
							//②IPアドレスから端末情報を取得
							String ipAddress = checkoutRow.get(RisAccessInfoTbl.IPADDRESS_COLUMN).toString();
							TerminalInfoBean terminalBean = Configuration.GetInstance().GetCoreController().GetTerminalInfoDataByIPAdrress(ipAddress, risconn);
							if (terminalBean == null)
							{
								terminalBean = new TerminalInfoBean();
							}

							//③RisIDからオーダ情報を取得
							OrderInfoBean orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(ris_id, risconn);
							if (orderBean != null)
							{
								MasterUtil mUtil = new MasterUtil();
								Configuration configuration = new Configuration();
								String kTypeNM = mUtil.FindData(
													configuration.GetRRisKensaTypeMaster(risconn),
													MasterUtil.RIS_KENSATYPE_NAME,
													MasterUtil.RIS_KENSATYPE_ID,
													orderBean.GetKensatypeID());

								//④メッセージ表示
								String errMsg = "指定のオーダは、他の端末で編集中です。\n\n　端末名		：%s\n　患者ＩＤ		：%s\n　検査日		：%s\n　検査種別		：%s\n　ＲＩＳＩＤ	：%s";
								errMsg = String.format(errMsg,
									terminalBean.GetTerminalName(),
									orderBean.GetKanjaID(),
									orderBean.GetKensaDateValue().toString(),
									kTypeNM,
									ris_id);
								logger.debug(errMsg);
								dto.setResult(Const.RESULT_NG);
								dto.setErrlevel(Const.ERRLEVEL_WARN);
								dto.setMsg(errMsg);
								return false;
							}
						}

					}
				}
				// 2019.08.06 Add Cosmo End 排他対応
				
				// オーダ詳細情報
				DataTable orderDt = null;
				// オーダ詳細情報取得
				orderDt = DataBase.getOrderDetail(ris_id, "".split(","), risconn);

				// オーダ詳細情報取得失敗
				if (orderDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("該当するデータがありませんでした。" + Const.LINE_FEED + "一覧の更新を行って下さい。");
					return false;
				}

				// オーダ詳細情報設定
				dto.setOrder_detail(this.getOrderDetail(config, orderDt, risconn));

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

	/**
	 * オーダ詳細取得
	 * @param config :設定ファイル情報
	 * @param dt     :オーダ詳細情報
	 * @param conn   :DB接続文字列
	 * @return
	 * @throws Exception
	 */
	private OrderDetailDto.OrderDetail getOrderDetail(Config config, DataTable dt, Connection conn) throws Exception {

		OrderDetailDto.OrderDetail order = new OrderDetailDto.OrderDetail();

		// オーダ情報設定
		order.setRis_id(dt.getRows().get(0).get("RIS_ID").toString());
		order.setKanja_id(dt.getRows().get(0).get("KANJA_ID").toString());
		order.setStatus(config.convStatusClient(dt.getRows().get(0).get("STATUS").toString()));
		order.setSex(dt.getRows().get(0).get("SEX").toString());
		order.setKanjisimei(dt.getRows().get(0).get("KANJISIMEI").toString());
		order.setKanasimei(dt.getRows().get(0).get("KANASIMEI").toString());
		order.setByoutou(dt.getRows().get(0).get("BYOUTOU").toString());
		order.setByousitu(dt.getRows().get(0).get("BYOUSITU").toString());
		order.setAge(dt.getRows().get(0).get("AGE").toString());
		order.setIrai_section(dt.getRows().get(0).get("IRAI_SECTION").toString());
		order.setIrai_doctor_name(dt.getRows().get(0).get("IRAI_DOCTOR_NAME").toString());
		order.setRenraku_memo(dt.getRows().get(0).get("RENRAKU_MEMO").toString());
		// 2020.09.15 Add Nishihara@COSMO start 実施技師登録機能追加対応
		order.setMed_person_id01(dt.getRows().get(0).get("MED_PERSON_ID01").toString());
		order.setMed_person_id02(dt.getRows().get(0).get("MED_PERSON_ID02").toString());
		order.setMed_person_id03(dt.getRows().get(0).get("MED_PERSON_ID03").toString());
		// 2020.09.15 Add Nishihara@COSMO end 実施技師登録機能追加対応
		order.setAcno(dt.getRows().get(0).get("ACNO").toString());
		order.setReceiptnumber(Util.isNullAndParseInt(dt.getRows().get(0).get("RECEIPTNUMBER")));
		order.setReceiptdate((Timestamp)dt.getRows().get(0).get("RECEIPTDATE"));
		order.setKensa_date(Util.isNullAndParseInt(dt.getRows().get(0).get("KENSA_DATE")));
		order.setIrai_info(config.getOrdercomment_id() +  getOrderCommentValue(dt.getRows().get(0).get("ORDERCOMMENT_ID").toString(), conn) + Const.LINE_FEED
			+ config.getKensa_siji() + dt.getRows().get(0).get("KENSA_SIJI").toString() + Const.LINE_FEED
			+ config.getRinsyou() + dt.getRows().get(0).get("RINSYOU").toString() + Const.LINE_FEED
			+ config.getRemarks() + dt.getRows().get(0).get("REMARKS").toString());

		order.setKanja_info(config.getHandicapped() + dt.getRows().get(0).get("HANDICAPPED").toString() + Const.LINE_FEED
			+ config.getInfection() + dt.getRows().get(0).get("INFECTION").toString() + Const.LINE_FEED
			+ config.getContraindication() + dt.getRows().get(0).get("CONTRAINDICATION").toString() + Const.LINE_FEED
			+ config.getAllergy() + dt.getRows().get(0).get("ALLERGY").toString() + Const.LINE_FEED
			+ config.getPregnancy() + dt.getRows().get(0).get("PREGNANCY").toString() + Const.LINE_FEED
			+ config.getNotes() + dt.getRows().get(0).get("NOTES").toString() + Const.LINE_FEED
			+ config.getExamdata() + dt.getRows().get(0).get("EXAMDATA").toString());

		DataTable dtBuiInfo = null;

		try {
			dtBuiInfo = DataBase.getSatueiBuiInfo(dt.getRows().get(0).get("RIS_ID").toString(), Integer.parseInt(dt.getRows().get(0).get("STATUS").toString()), conn);
		} catch(Exception ex) {
			throw ex;
		}

		String buiInfo = "";
		for (int i=0; i<dtBuiInfo.getRowCount(); i++) {
			if (!buiInfo.equals("")) {
				buiInfo += Const.LINE_FEED;
			}
			buiInfo += dtBuiInfo.getRows().get(i).get("BUI_NAME").toString() + Const.SLASH;
			buiInfo += dtBuiInfo.getRows().get(i).get("HOUKOU_NAME").toString() + Const.SLASH;
			buiInfo += dtBuiInfo.getRows().get(i).get("SAYUU_NAME").toString() + Const.SLASH;
			buiInfo += dtBuiInfo.getRows().get(i).get("KENSAHOUHOU_NAME").toString();
			buiInfo += Const.LINE_FEED;
			buiInfo += config.getBuicomment();
			buiInfo += dtBuiInfo.getRows().get(i).get("BUICOMMENT").toString();
		}
		order.setSatueibui_info(buiInfo);

		// add sta 201806_ポータブルRIS検査系種別対応
		String kensatypeId = dt.getRows().get(0).get(MasterUtil.RIS_KENSATYPE_ID).toString();
		String kensaKbn = Configuration.GetInstance().GetOperationKbn(null, kensatypeId, conn);
		order.setKensa_kbn(kensaKbn);
		// add end 201806_ポータブルRIS検査系種別対応

		return order;
	}

	/**
	 * オーダコメントIDから文字列を取得する(区切り文字半角スペース)
	 * @param commentId   :オーダコメントID
	 * @param conn        :DB接続文字列
	 * @return            :オーダコメントIDから生成した文字列
	 * @throws Exception
	 */
	private String getOrderCommentValue(String commentId, Connection conn) throws Exception {
		return getOrderCommentValue(commentId, " ", conn);
	}

	/**
	 * オーダコメントIDから文字列を取得する
	 * @param commentId       :オーダコメントID
	 * @param valueDelimiter  :文字列を連結する区切り文字
	 * @param conn            :DB接続文字列
	 * @return                :オーダコメントIDから生成した文字列
	 * @throws Exception
	 */
	private String getOrderCommentValue(String commentId, String valueDelimiter, Connection conn)
			throws Exception {

		String retStr = "";
		if (StringUtils.isEmpty(commentId)) {
			return retStr;
		}

		String markercharacterStr = "\\|";
		String[] commentList = commentId.split(markercharacterStr);
		if (commentList.length == 0) {
			return retStr;
		}

		DataTable dtComment = DataBase.getPredefineCommentMaster(commentList, conn);
		for (int i = 0; i < commentList.length; i++) {
			String id = commentList[i];
			if (id.length() == 0) {
				continue;
			}
			String cmtStr = "";
			for (int j = 0; j < dtComment.getRowCount(); j++) {
				DataRow row = dtComment.getRows().get(j);
				if (row.get("COMMENT_ID").toString().equals(id)
						&& row.get("RIS_COMMENTKBN").toString()
								.equals(Const.PRECOMMENTKBN_ORDER)) {
					cmtStr = row.get("COMMENT_NAME").toString();
					break;
				}
			}
			if (cmtStr.length() == 0) {
				cmtStr = id;
			}
			if (retStr.length() == 0) {
				retStr = cmtStr;
			} else {
				retStr += valueDelimiter + cmtStr;
			}
		}

		return retStr;
	}
}
