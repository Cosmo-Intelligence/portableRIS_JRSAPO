package ris.portable.rest.resources;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExBuiBean;
import ris.lib.core.bean.ExFilmBean;
import ris.lib.core.bean.ExInfuseBean;
import ris.lib.core.bean.ExSatueiBean;
import ris.lib.core.bean.ExZoueizaiBean;
import ris.lib.core.bean.ExamOperationHistoryInfoBean;
import ris.lib.core.bean.ExecutionInfoBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.PatientInfoBean;
import ris.lib.core.bean.PresetInfoBean;
import ris.lib.core.database.MPExposureDoseTbl;
import ris.lib.core.database.MPFilmConsumptionTbl;
import ris.lib.core.database.MPMppsMasterTbl;
import ris.lib.core.database.MPPreformedProtocolCodeTbl;
import ris.lib.core.database.RisCrSatueiMenuMaster;
import ris.lib.core.database.RisSummaryView;
import ris.lib.core.database.RisSystemParamTbl;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MWMParameter;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.PresetParameter;
import ris.lib.core.util.RequestParameter;
import ris.lib.core.util.TextUtil;
import ris.portable.common.Application;
import ris.portable.common.Config;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.database.DataBase;
import ris.portable.model.dto.StatusUpdateDto;
import ris.portable.util.AppCommon;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;
import ris.portable.util.Util;

@Path("/status")
public class StatusUpdateResource {

	private static Log logger = LogFactory.getLog(StatusUpdateResource.class);

	// ステータス識別子
	private static final String STATUSID_BACK         = "back";
	private static final String STATUSID_REGISTERED   = "registered";
	private static final String STATUSID_UNREGISTERED = "unregistered";
	// 2019.08.06 Add Cosmo Start 排他対応
	private static final String STATUSID_REGISTERED_2   = "registered2";
	private static final String STATUSID_UNREGISTERED_2 = "unregistered2";
	// 2019.08.06 Add Cosmo End 排他対応

	//2020.09.08 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加 一括受付対応
	private static final String STATUSID_LUMPREGISTERED = "lumpregistered";
	//2020.09.08 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加 一括受付対応

	//2020.09.14 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加 一括MWM対応
	private static final String STATUSID_MWMREGISTERED = "mwmregistered";
	//2020.09.14 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加 一括MWM対応

	private static final String STATUSID_REST         = "rest";
	private static final String STATUSID_INOPERATION  = "inoperation";

	private static final String STATUSID_FINISHED     = "finished";

	// デバッグURL
	// http://localhost:8080/PortableRIS/rest/orderdetail?ris_id=2010102010002030&kanja_id=9999200&uketuke_tantou_id=999999&byoutou_id=ZZ&kensatype_id=Q&status=0

	@GET
	@Path("{statusid}")
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@PathParam("statusid") String status_id,
	        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加
	    	//一括受付、一括MWM登録追加により、一部複数件処理を行うところがあるため、rid_idを配列で受け取るように変更
			// @QueryParam("ris_id") String ris_id,
			@QueryParam("ris_id[]") List<String> ris_id,
			//2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加
			@QueryParam("kensasitu_id") String kensasitu_id,
			@QueryParam("kensakiki_id") String kensakiki_id,
	        //2020.09.10 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
			@QueryParam("kanja_nyugai") String kanja_nyugai,
			@QueryParam("kensatype_id") String kensatype_id,
	        //2020.09.10 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
			@QueryParam("zisshisha_id1") String zisshisha_id1,
			@QueryParam("zisshisha_id2") String zisshisha_id2,
			@QueryParam("zisshisha_id3") String zisshisha_id3,
			@QueryParam("zisshisha_name1") String zisshisha_name1,
			@QueryParam("zisshisha_name2") String zisshisha_name2,
			@QueryParam("zisshisha_name3") String zisshisha_name3,
			// 2021.12.15 Add H.Taira@COSMO Start
			@QueryParam("status") String status,
			// 2021.12.15 Add H.Taira@COSMO End
			@QueryParam("mpps") String mpps,
			@Context HttpServletRequest request) throws Exception {

//        return doPost(status_id, ris_id, kensasitu_id, kensakiki_id, mpps, request);
		// 2021.12.15 Mod H.Taira@COSMO Start
        return doPost(status_id, ris_id, kensasitu_id, kensakiki_id, kanja_nyugai, kensatype_id,
        		zisshisha_id1, zisshisha_id2, zisshisha_id3, zisshisha_name1, zisshisha_name2, zisshisha_name3, status, mpps, request);
        //return doPost(status_id, ris_id, kensasitu_id, kensakiki_id, kanja_nyugai, kensatype_id,
        //		zisshisha_id1, zisshisha_id2, zisshisha_id3, zisshisha_name1, zisshisha_name2, zisshisha_name3, mpps, request);
        // 2021.12.15 Mod H.Taira@COSMO End
	}

	@POST
	@Path("{statusid}")
	@Produces("application/json;charset=UTF-8")
	public Response doPost(
			@PathParam("statusid") String status_id,
	        //2020.09.07 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加
			// @FormParam("ris_id") String ris_id,
			@FormParam("ris_id[]") List<String> ris_id,
	        //2020.09.07 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加
			@FormParam("kensasitu_id") String kensasitu_id,
			@FormParam("kensakiki_id") String kensakiki_id,
	        //2020.09.10 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
			@FormParam("kanja_nyugai") String kanja_nyugai,
			@FormParam("kensatype_id") String kensatype_id,
	        //2020.09.10 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
			@FormParam("zisshisha_id1") String zisshisha_id1,
			@FormParam("zisshisha_id2") String zisshisha_id2,
			@FormParam("zisshisha_id3") String zisshisha_id3,
			@FormParam("zisshisha_name1") String zisshisha_name1,
			@FormParam("zisshisha_name2") String zisshisha_name2,
			@FormParam("zisshisha_name3") String zisshisha_name3,
			// 2021.12.15 Add H.Taira@COSMO Start
			@FormParam("status") String status,
			// 2021.12.15 Add H.Taira@COSMO End
			@FormParam("mpps") String mpps,
			@Context HttpServletRequest request) throws Exception {

		logger.debug("☆★☆検査ステータス更新リクエスト---開始");

		// 検査ステータス更新情報
		StatusUpdateDto statusDto = new StatusUpdateDto();

		//2020.09.08 Mod Nishihara@COSMO start 複数オーダーの一括処理機能追加 ris_idを配列で受け取れるように変更
		if (STATUSID_BACK.equals(status_id)) { // 戻る

			logger.debug("☆★☆検査ステータス更新リクエスト---戻る");

			// 戻る実行
			// ExecuteBack(ris_id, request, statusDto);
			ExecuteBack(ris_id.get(0).toString(), request, statusDto);

		} else if (STATUSID_REGISTERED.equals(status_id)) { // 受付

			logger.debug("☆★☆検査ステータス更新リクエスト---受付");
			// 受付実行
			// ExecuteRegistered(ris_id, Const.STATUS_UNREGISTERED, Const.STATUS_ISREGISTERED, request, statusDto,0);
			ExecuteRegistered(ris_id.get(0).toString(), Const.STATUS_UNREGISTERED, Const.STATUS_ISREGISTERED, request, statusDto, 0);

		} else if (STATUSID_UNREGISTERED.equals(status_id)) { // 取消

			logger.debug("☆★☆検査ステータス更新リクエスト---取消");

			// 取消実行
			// ExecuteRegistered(ris_id, Const.STATUS_ISREGISTERED, Const.STATUS_UNREGISTERED, request, statusDto,0);
			ExecuteRegistered(ris_id.get(0).toString(), Const.STATUS_ISREGISTERED, Const.STATUS_UNREGISTERED, request, statusDto, 0);
		// 2019.08.06 Add Cosmo Start 排他対応

		} else if (STATUSID_REGISTERED_2.equals(status_id)) { // 受付

			logger.debug("☆★☆検査ステータス更新リクエスト---受付");

			// 受付実行2
			// ExecuteRegistered(ris_id, Const.STATUS_UNREGISTERED, Const.STATUS_ISREGISTERED, request, statusDto,1);
			ExecuteRegistered(ris_id.get(0).toString().toString(), Const.STATUS_UNREGISTERED, Const.STATUS_ISREGISTERED, request, statusDto, 1);

		//2020.09.14 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加 一括受付、一括MWM登録対応
		} else if (STATUSID_LUMPREGISTERED.equals(status_id)) { // 一括受付

			logger.debug("☆★☆検査ステータス更新リクエスト---一括受付");

			// 一括受付実行
			for(int i=0; i<ris_id.size(); i++) {
				ExecuteLumpRegistered(ris_id.get(i).toString().toString(), Const.STATUS_UNREGISTERED, Const.STATUS_ISREGISTERED, kensakiki_id, request, statusDto, 1);
			}

		} else if (STATUSID_MWMREGISTERED.equals(status_id)) { // 一括MWM登録

			logger.debug("☆★☆受済オーダ一括MWM登録リクエスト---一括MWM登録");

			// 一括MWM登録実行
			for(int i=0; i<ris_id.size(); i++) {
				ExecuteMWMRegistered(ris_id.get(i).toString(), kensakiki_id, request, statusDto);
			}
		//2020.09.14 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加 一括受付、一括MWM登録対応

		} else if (STATUSID_UNREGISTERED_2.equals(status_id)) { // 取消

			logger.debug("☆★☆検査ステータス更新リクエスト---取消");

			// 取消実行2
			//一括MWM登録処理追加に伴い、kensakiki_idに渡す値、MWM登録処理を行うかのflgを追加。
			// ExecuteRegistered(ris_id, Const.STATUS_ISREGISTERED, Const.STATUS_UNREGISTERED, request, statusDto,1);
			ExecuteRegistered(ris_id.get(0).toString(), Const.STATUS_ISREGISTERED, Const.STATUS_UNREGISTERED, request, statusDto, 1);
		// 2019.08.06 Add Cosmo End 排他対応

		} else if (STATUSID_REST.equals(status_id)) { // 保留

			logger.debug("☆★☆検査ステータス更新リクエスト---保留");

			// 保留実行
			// ExecuteRest(ris_id, Const.STATUS_REST, kensasitu_id, kensakiki_id, request, statusDto);
			ExecuteRest(ris_id.get(0).toString(), Const.STATUS_REST, kensasitu_id, kensakiki_id,
								zisshisha_id1, zisshisha_id2, zisshisha_id3, zisshisha_name1, zisshisha_name2, zisshisha_name3, request, statusDto);


		} else if (STATUSID_INOPERATION.equals(status_id)) { // 開始

			logger.debug("☆★☆検査ステータス更新リクエスト---開始");
			// ExecuteInoperation(ris_id, Const.STATUS_INOPERATION, kensasitu_id, kensakiki_id, request, statusDto);

			// 開始実行
			// 2021.12.15 Mod H.Taira@COSMO Start
			ExecuteInoperation(ris_id.get(0).toString(), Const.STATUS_INOPERATION, kensasitu_id, kensakiki_id, kanja_nyugai, kensatype_id,
					zisshisha_id1, zisshisha_id2, zisshisha_id3, zisshisha_name1, zisshisha_name2, zisshisha_name3, status, request, statusDto);
			//ExecuteInoperation(ris_id.get(0).toString(), Const.STATUS_INOPERATION, kensasitu_id, kensakiki_id, kanja_nyugai, kensatype_id,
			//					zisshisha_id1, zisshisha_id2, zisshisha_id3, zisshisha_name1, zisshisha_name2, zisshisha_name3, request, statusDto);
			// 2021.12.15 Mod H.Taira@COSMO End

		} else if (STATUSID_FINISHED.equals(status_id)) { // 終了

			logger.debug("☆★☆検査ステータス更新リクエスト---終了");

			// 終了実行
			// ExecuteFinished(ris_id, Const.STATUS_ISFINISHED, kensasitu_id, kensakiki_id, mpps, request, statusDto);
			ExecuteFinished(ris_id.get(0).toString(), Const.STATUS_ISFINISHED, kensasitu_id, kensakiki_id, kanja_nyugai, kensatype_id,
								zisshisha_id1, zisshisha_id2, zisshisha_id3, zisshisha_name1, zisshisha_name2, zisshisha_name3, mpps, request, statusDto);
		//2020.09.08 Mod Nishihara@COSMO end 複数オーダーの一括処理機能追加 ris_idを配列で受け取れるように変更
		} else {

			// 検査ステータス不正判定
			statusDto.setResult(Const.RESULT_NG);
			statusDto.setErrlevel(Const.ERRLEVEL_WARN);
			statusDto.setMsg("検査ステータス不正な為、処理が行えませんでした。");

		}

		// JSON変換
		String json = Util.getJson(statusDto);

		logger.debug("☆★☆検査ステータス更新リクエスト---終了");

		logger.debug("☆★☆検査ステータス更新リクエスト---JSON:" + json);

        return Response.ok().entity(json).build();
	}

	/**
	 * 戻る 処理実行
	 * @param ris_id  :RIS識別ID
	 * @param request :httpリクエスト
	 * @param dto     :ステータス更新情報
	 * @return
	 */
	private boolean ExecuteBack(
			String ris_id,
			HttpServletRequest request,
			StatusUpdateDto dto) throws Exception {

		try{
			// パラメータ取得不可判定
			if (StringUtils.isEmpty(ris_id)) {
				//dto.setResult(Const.RESULT_NG);
				//dto.setErrlevel(Const.ERRLEVEL_WARN);
				//dto.setMsg("引数が不足している為、処理が行えませんでした。");
				logger.debug("RIS_IDが不足している為、処理が行えませんでした。");
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
				// 変換後検査ステータス
				String convstatus = "";

				// オーダ詳細情報
				DataTable orderDt = null;
				// オーダ詳細情報取得
				orderDt = DataBase.getOrderDetail(ris_id, convstatus.split(","), risconn);

				// オーダ詳細情報取得失敗
				if (orderDt.getRowCount() == 0) {
					//dto.setResult(Const.RESULT_NG);
					//dto.setErrlevel(Const.ERRLEVEL_WARN);
					//dto.setMsg("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					logger.debug("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					return false;
				}

				// アプリケーションクラス
				Application app = new Application();
                // 管理クラス初期化
                app.InitConfiguration(risconn, request);

				// WorkListInfoの削除を行う
				String risID  = ris_id;
				String accNo  = orderDt.getRows().get(0).get(RisSummaryView.ACNO_COLUMN).toString();
				String kikiID = orderDt.getRows().get(0).get(RisSummaryView.JISSI_KENSAKIKI_ID_COLUMN).toString();
				// 実施検査機器IDが取得できない場合
				if (StringUtils.isEmpty(kikiID)) {
					kikiID = orderDt.getRows().get(0).get(RisSummaryView.KENSAKIKI_ID_COLUMN).toString();
				}
				Configuration.GetInstance().GetCoreController().DeleteWorkListByAccesionNo(risID, accNo, kikiID, CommonString.MWMDELETE_INDEX_0, risconn);
				//検中の場合Exmainの状態を戻す。
				if(orderDt.getRows().get(0).get(RisSummaryView.STATUS_COLUMN).toString().compareTo(CommonString.STATUS_INOPERATION) == 0){
					boolean result = false;
					result = DataBase.ExMAinReturnUpdate(risID, risconn);

					// エラー判定
					if (result == false) {
						//2020.06.02 Add Start Cosmo@Nishihara
						risconn.rollback();
						//2020.06.02 Add End Cosmo@Nishihara
						dto.setResult(Const.RESULT_NG);
						dto.setErrlevel(Const.ERRLEVEL_ERROR);
						dto.setMsg("戻る処理で更新が失敗しました。");
						return false;
					}
					//2020.06.02 Add Start Cosmo@Nishihara
					risconn.commit();
					//2020.06.02 Add End Cosmo@Nishihara
				}

			} finally {
				// 2019.08.06 Add Cosmo Start 排他対応
				Configuration.GetInstance().GetCoreController().unCheckOutExclusive(dto,ris_id,risconn,config.getExclusive());
				// 2019.08.06 Add Cosmo End 排他対応
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
	 * 受付／取消 処理実行
	 * @param ris_id    :RIS識別ID
	 * @param oldstatus :更新前ステータス
	 * @param newstatus :更新後ステータス
	 * @param request   :httpリクエスト
	 * @param dto       :ステータス更新情報
	 * @return
	 */
	private boolean ExecuteRegistered(
			String ris_id,
			String oldstatus,
			String newstatus,
			HttpServletRequest request,
			StatusUpdateDto dto,
			// 2019.08.06 Add Cosmo Start 排他対応
			int type
			// 2019.08.06 Add Cosmo End 排他対応
			) throws Exception {

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
				// 変換後検査ステータス
				String convstatus = config.convStatusServer(oldstatus);

				// オーダ詳細情報
				DataTable orderDt = null;
				// オーダ詳細情報取得
				orderDt = DataBase.getOrderDetail(ris_id, convstatus.split(","), risconn);

				// オーダ詳細情報取得失敗
				if (orderDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					return false;
				}

				// 検査ステータス更新
				boolean result = false;

				// アプリケーションクラス
				Application app = new Application();
                // 管理クラス初期化
                app.InitConfiguration(risconn, request);
                StringBuilder errMsg = new StringBuilder();
                // 検査ステータス更新
				result = Configuration.GetInstance().GetCoreController().ChangeRisOrderStatus(
						ris_id,
						config.getAppid(),
						newstatus,
				        Configuration.GetInstance().GetUserAccountBean(),
				        orderDt.getRows().get(0).get(RisSummaryView.KENSASITU_ID_COLUMN).toString(),
				        orderDt.getRows().get(0).get(RisSummaryView.KENSAKIKI_ID_COLUMN).toString(),
						risconn,
						// 2019.08.06 Add Cosmo Start 排他対応
						type,
						config.getExclusive(),
						errMsg
						// 2019.08.06 Add Cosmo End 排他対応
						);

				if (!result) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					if(errMsg.length() ==0){
						dto.setMsg("検査ステータスの更新に失敗しました。");
					}
					dto.setMsg(errMsg.toString());
					return false;
				}else{
					// 2019.08.06 Add Cosmo Start 排他対応
					if(type == 0){
						Configuration.GetInstance().GetCoreController().unCheckOutExclusive(dto,ris_id,risconn,config.getExclusive());
					}
					// 2019.08.06 Add Cosmo End 排他対応
				}

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

	//2020.09.18 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加
	/**
	 * 一括受付 処理実行
	 * @param ris_id    :RIS識別ID
	 * @param oldstatus :更新前ステータス
	 * @param newstatus :更新後ステータス
	 * @param newstatus :検査機器ID
	 * @param request   :httpリクエスト
	 * @param dto       :ステータス更新情報
	 * @return
	 */
	private boolean ExecuteLumpRegistered(
			String ris_id,
			String oldstatus,
			String newstatus,
			String kensakiki_id,
			HttpServletRequest request,
			StatusUpdateDto dto,
			int type
			) throws Exception {

		try{
			// パラメータ取得不可判定
			if (StringUtils.isEmpty(ris_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("引数が不足している為、処理が行えませんでした。");
				return false;
			}

			// パラメータ取得不可判定
			if (StringUtils.isEmpty(kensakiki_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("検査機器を選択してください。");
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
				// 変換後検査ステータス
				String convstatus = config.convStatusServer(oldstatus);

				// オーダ詳細情報
				DataTable orderDt = null;
				// オーダ詳細情報取得
				orderDt = DataBase.getOrderDetail(ris_id, convstatus.split(","), risconn);

				// オーダ詳細情報取得失敗
				if (orderDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					return false;
				}

				// 検査ステータス更新
				boolean result = false;

				// アプリケーションクラス
				Application app = new Application();
                // 管理クラス初期化
                app.InitConfiguration(risconn, request);
                StringBuilder errMsg = new StringBuilder();
                // 検査ステータス更新
				result = Configuration.GetInstance().GetCoreController().ChangeRisOrderStatus(
						ris_id,
						config.getAppid(),
						newstatus,
				        Configuration.GetInstance().GetUserAccountBean(),
				        orderDt.getRows().get(0).get(RisSummaryView.KENSASITU_ID_COLUMN).toString(),
				        orderDt.getRows().get(0).get(RisSummaryView.KENSAKIKI_ID_COLUMN).toString(),
						risconn,
						// 2019.08.06 Add Cosmo Start 排他対応
						type,
						config.getExclusive(),
						errMsg
						// 2019.08.06 Add Cosmo End 排他対応
						);

				if (!result) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					if(errMsg.length() ==0){
						dto.setMsg("検査ステータスの更新に失敗しました。");
					}
					dto.setMsg(errMsg.toString());
					return false;
				}else{
					// 2019.08.06 Add Cosmo Start 排他対応
					if(type == 0){
						Configuration.GetInstance().GetCoreController().unCheckOutExclusive(dto,ris_id,risconn,config.getExclusive());
					}
					// 2019.08.06 Add Cosmo End 排他対応
				}
				//ここまで受付処理と同じ処理

				//●受付ボタン押下で一括受済にした際、MWM登録も行う。
				boolean mwmresult = mwmRegister(ris_id, kensakiki_id, risconn, config, request, dto);

				if (!mwmresult) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					return false;
				}

				// コミット
				risconn.commit();

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
	//2020.09.18 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加

	//2020.09.14 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加
	/**
	 * 一MWM登録 処理実行
	 * @param ris_id    :RIS識別ID
	 * @param oldstatus :更新前ステータス
	 * @param newstatus :更新後ステータス
	 * @param request   :httpリクエスト
	 * @param dto       :ステータス更新情報
	 * @return
	 */
	private boolean ExecuteMWMRegistered(
			String ris_id,
			String kensakiki_id,
			HttpServletRequest request,
			StatusUpdateDto dto
			) throws Exception {

		try{
			// パラメータ取得不可判定
			if (StringUtils.isEmpty(ris_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("引数が不足している為、処理が行えませんでした。");
				return false;
			}

			// パラメータ取得不可判定
			if (StringUtils.isEmpty(kensakiki_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("検査機器を選択してください。");
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
				boolean mwmresult = mwmRegister(ris_id, kensakiki_id, risconn, config, request, dto);

				if (!mwmresult) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					return false;
				}

				// コミット
				risconn.commit();

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
	//2020.09.14 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加

	/**
	 * 保留 処理実行
	 * @param ris_id       :RIS識別ID
	 * @param newstatus    :更新後検査ステータス
	 * @param kensasitu_id :検査室ID
	 * @param kensakiki_id :検査機器ID
	 * @param request      :httpリクエスト
	 * @param dto          :ステータス更新情報
	 * @return
	 */
	private boolean ExecuteRest(
			String ris_id,
			String newstatus,
			String kensasitu_id,
			String kensakiki_id,
			//2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
			String zisshisha_id1,
			String zisshisha_id2,
			String zisshisha_id3,
			String zisshisha_name1,
			String zisshisha_name2,
			String zisshisha_name3,
			//2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
			HttpServletRequest request,
			StatusUpdateDto dto) throws Exception {

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
				// 変換後検査ステータス
				String convstatus = "";

				// オーダ詳細情報
				DataTable orderDt = null;
				// オーダ詳細情報取得
				orderDt = DataBase.getOrderDetail(ris_id, convstatus.split(","), risconn);

				// オーダ詳細情報取得失敗
				if (orderDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					return false;
				}

				// 検査ステータス更新
				boolean result = false;

				// アプリケーションクラス
				Application app = new Application();
                // 管理クラス初期化
                app.InitConfiguration(risconn, request);

                ExecutionInfoBean exBean = null;
                OrderInfoBean orderBean = null;
                PatientInfoBean patientBean = null;
                ArrayList patientCommentList = null;

                // 登録情報取得
        		// 実績メイン情報取得
        		exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(ris_id, risconn);

        		// オーダ情報取得
        		orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(ris_id, risconn);

        		// 患者情報の取得
        		// (標準)患者進捗が受済以降の場合は患者履歴よりデータを取得する
        		int nowStatus = TextUtil.ParseStringToInt(exBean.GetStatus());
        		int chkStatus = TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED);
        		if (chkStatus <= nowStatus)
        		{
        			// 受済→実績患者情報
        			patientBean = Configuration.GetInstance().GetCoreController().GetRisResultPatientInfo(orderBean.GetRisID(), orderBean.GetKanjaID(), risconn);
        		}
        		else
        		{
        			// 未受→最新の患者情報
        			patientBean = Configuration.GetInstance().GetCoreController().GetRisPatientInfo(orderBean.GetKanjaID(), risconn);
        		}

        		// 患者コメント
        		patientCommentList = new ArrayList();
        		patientCommentList.add(AppCommon.GetPatientComment(patientBean.GetCommentList(), MasterUtil.RIS_PATIENTCOMMENT_DEF));
        		patientCommentList.add(AppCommon.GetPatientComment(patientBean.GetCommentList(), orderBean.GetKensatypeID()));

				// mod sta 201806_ポータブルRIS検査系種別対応
				//// 実績撮影情報が無い場合、プリセットからデータを作成する
        		//if (exBean.GetExSatueiList().size() == 0) {

				boolean iskensakei = isOperationKbnInspect(orderBean.GetKensatypeID(), risconn);

				// 撮影区分が撮影系の場合 & 実績撮影情報が無い場合、プリセットからデータを作成する
				if (exBean.GetExSatueiList().size() == 0 && !iskensakei){

				// mod end 201806_ポータブルRIS検査系種別対応
    				ArrayList satueiList = new ArrayList();
    				ArrayList partsList  = new ArrayList();
    				ArrayList infuseList = new ArrayList();
    				ArrayList filmList = new ArrayList();

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
    								satueiBean.SetRisID(exBean.GetRisID());
    								satueiBean.SetBuiNo(buiBean.GetNo());
    								satueiBean.SetNo(no.toString());
    								satueiBean.SetSatueiStatus(buiBean.GetSatueiStatus());
    								satueiBean.SetSatueiMenuID(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_ID).toString());
    								satueiBean.SetSatueiMenuName(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_NAME).toString());
    								satueiBean.SetSatueiMenuNameKana(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_NAME_KANA).toString());
    								satueiBean.SetSatueiCode(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEI_CODE + "01").toString());
    								satueiBean.SetFilmID(menuDt.getRows().get(0).get(MasterUtil.RIS_FILM_ID).toString());
    								satueiBean.SetPartition(menuDt.getRows().get(0).get(MasterUtil.RIS_PARTITION).toString());
    								satueiBean.SetUsed(menuDt.getRows().get(0).get(MasterUtil.RIS_USED).toString());
        							//satueiBean.SetReshotFlg(menuDt.getRows().get(0).get(MasterUtil.RIS_RESHOT_FLG).toString());
        							//satueiBean.SetCassetteNo(menuDt.getRows().get(0).get(MasterUtil.RIS_CASSETTE_NO).toString());
        							//satueiBean.SetPortableStatus(menuDt.getRows().get(0).get(MasterUtil.RIS_PORTABLE_STATUS).toString());
        							//satueiBean.SetKensaKikiID(menuDt.getRows().get(0).get(MasterUtil.RIS_KENSAKIKI_ID).toString());
        							//satueiBean.SetSatueiAddFlag(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIADDFLAG).toString());
    								satueiBean.SetExamDataToBean2(menuDt.getRows().get(0));	//ExamData01~20
    								satueiBean.SetMppsDataToBean(satueiRow);	//Mpps情報
        							//
        							satueiList.add(satueiBean);

        							no++;
    							}
    						}

    						no = 1;

    						// 造影剤
    						for (int n = 0; n < presetinfo.GetZoueizaiList().size(); n++){

								DataRow itemRow = (DataRow) presetinfo.GetZoueizaiList().get(n);

    							// 器材Beanへ変換
    							ExZoueizaiBean partsBean = new ExZoueizaiBean();
    							partsBean.SetRisID(exBean.GetRisID());
    							partsBean.SetBuiNo(buiBean.GetNo());
    							partsBean.SetNo(no);
    							partsBean.SetPartsID(itemRow.get(MasterUtil.RIS_ZOUEIZAI_ID).toString());
    							partsBean.SetSuuryoIji(itemRow.get(MasterUtil.RIS_SUURYOU_IJI).toString());
    							partsBean.SetSuuryo(itemRow.get(MasterUtil.RIS_SUURYOU).toString());
    							partsBean.SetPartsBunruiID(itemRow.get(MasterUtil.RIS_PARTSBUNRUI_ID).toString());
    							partsBean.SetDetailPartsBunruiID(itemRow.get(MasterUtil.RIS_DETAILPARTSBUNRUI_ID).toString());
    							//partsBean.SetBarcodeData(itemRow.get(MasterUtil.RIS_BARCODEDATA).toString());

    							//
    							partsList.add(partsBean);

    							no++;
    						}

    						no = 1;

    						// 手技
    						for (int n = 0; n < presetinfo.GetInfuseList().size(); n++){

								DataRow itemRow = (DataRow) presetinfo.GetInfuseList().get(n);

    							// 手技Beanへ変換
    							ExInfuseBean infuseBean = new ExInfuseBean();
    							infuseBean.SetRisID(exBean.GetRisID());
    							infuseBean.SetBuiNo(buiBean.GetNo());
    							infuseBean.SetNo(no.toString());
    							infuseBean.SetInfuseID(itemRow.get(MasterUtil.RIS_INFUSE_ID).toString());
    							infuseBean.SetSuuryoIji(itemRow.get(MasterUtil.RIS_SUURYOU_IJI).toString());
    							infuseBean.SetSuuryo(itemRow.get(MasterUtil.RIS_SUURYOU).toString());
    							infuseBean.SetPartsBunruiID(itemRow.get(MasterUtil.RIS_PARTSBUNRUI_ID).toString());
    							infuseBean.SetDetailPartsBunruiID(itemRow.get(MasterUtil.RIS_DETAILPARTSBUNRUI_ID).toString());
    							//infuseBean.SetBarcodeData(itemRow.get(MasterUtil.RIS_BARCODEDATA).toString());

    							//
    							infuseList.add(infuseBean);

    							no++;
    						}

    						no = 1;

    						// フィルム
    						for (int n = 0; n < presetinfo.GetFilmList().size(); n++){

								DataRow filmRow = (DataRow) presetinfo.GetFilmList().get(n);

    							// フィルム情報Bean作成
    							ExFilmBean filmBean = new ExFilmBean();

    							filmBean.SetRisID(exBean.GetRisID());
    							filmBean.SetBuiNo(buiBean.GetNo());
    							filmBean.SetNo(no.toString());
    							filmBean.SetFilmID(filmRow.get(MasterUtil.RIS_FILM_ID).toString());
    							filmBean.SetPartition(filmRow.get(MasterUtil.RIS_PARTITION).toString());
    							// 2024.07.24 Mod Nishihara@COSMO Start 帝京大学病院環境対応
    							// filmBean.SetUsed(filmRow.get(MasterUtil.RIS_USED).toString());
    							filmBean.SetUsed(filmRow.get(MasterUtil.RIS_USE).toString());
    							// 2024.07.24 Mod Nishihara@COSMO End   帝京大学病院環境対応
    							filmBean.SetLoss(filmRow.get(MasterUtil.RIS_LOSS).toString());

    							//
    							filmList.add(filmBean);

    							no++;
    						}
    					}
    				}

    				// 各リストを設定
    				exBean.ReconstructExSatueiList(satueiList);
    				exBean.ReconstructExZoueizaiList(partsList);
    				exBean.ReconstructExInfuseList(infuseList);
    				exBean.ReconstructExFilmList(filmList);
        		}

                // RIS識別IDを設定
                exBean.SetRisID(ris_id);
                // 検査ステータスを保留に設定
                exBean.SetStatus(newstatus);
                // 検査室を設定
                exBean.SetKensaSituID(Util.toNullString(kensasitu_id));
                // 検査機器を設定
                exBean.SetKensaKikiID(Util.toNullString(kensakiki_id));

                // 検査情報保存
				result = Configuration.GetInstance().GetCoreController().SaveRisExecutionInfo(exBean, patientCommentList, risconn);

				if (!result) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("検査ステータスの更新に失敗しました。");
					return false;
				} else {

					// 実績操作履歴情報の登録[検査保留]
					ExamOperationHistoryInfoBean operationBean = new ExamOperationHistoryInfoBean();
					operationBean.SetRisID(orderBean.GetRisID());
					operationBean.SetOperationType(Integer.parseInt(CommonString.OPERATIONTYPE_HOLD_OPERATE));
					Configuration.GetInstance().GetCoreController().RegisterRisExamOperationHistory(operationBean, risconn);

					// WorkListInfoの削除を行う
					String risID  = orderBean.GetRisID();
					String accNo  = orderBean.GetAccessionNo();
					String kikiID = Util.toNullString(kensakiki_id);
					Configuration.GetInstance().GetCoreController().DeleteWorkListByAccesionNo(risID, accNo, kikiID, CommonString.MWMDELETE_INDEX_0, risconn);
				}

				// 2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
				// EXMAINTABLEの検査技師IDと検査技師名を詳細画面で選択した実施者に更新する。
				boolean updateresult2 = DataBase.zishisha_Update(ris_id, zisshisha_id1, zisshisha_id2, zisshisha_id3,
																							zisshisha_name1, zisshisha_name2, zisshisha_name3, risconn);
				// 更新失敗時
				if (!updateresult2) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("実施者(担当技師)の更新に失敗しました。");
					return false;
				}

				// コミット
				risconn.commit();
				// 2020.09.16 Add Nishihara@COSMO end 実施技師登録機能追加対応

			} finally {
				// 2019.08.06 Add Cosmo Start 排他対応
				Configuration.GetInstance().GetCoreController().unCheckOutExclusive(dto,ris_id,risconn,config.getExclusive());
				// 2019.08.06 Add Cosmo End 排他対応
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
	 * 開始 処理実行
	 * @param ris_id       :RIS識別ID
	 * @param newstatus    :更新後検査ステータス
	 * @param kensasitu_id :検査室ID
	 * @param kensakiki_id :検査機器ID
	 * @param request      :httpリクエスト
	 * @param dto          :ステータス更新情報
	 * @return
	 */
	private boolean ExecuteInoperation(
			String ris_id,
			String newstatus,
			String kensasitu_id,
			String kensakiki_id,
			//2020.09.10 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
			String kanja_nyugai,
			String kensatype_id,
			//2020.09.10 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
			//2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
			String zisshisha_id1,
			String zisshisha_id2,
			String zisshisha_id3,
			String zisshisha_name1,
			String zisshisha_name2,
			String zisshisha_name3,
			//2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
			// 2021.12.15 Add H.Taira@COSMO Start
			String status,
			// 2021.12.15 Add H.Taira@COSMO End
			HttpServletRequest request,
			StatusUpdateDto dto) throws Exception {

		try{
			// パラメータ取得不可判定
			if (StringUtils.isEmpty(ris_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("引数が不足している為、処理が行えませんでした。");
				return false;
			}

			// パラメータ取得不可判定
			if (StringUtils.isEmpty(kensasitu_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("検査室が選択されていません。");
				return false;
			}

			// パラメータ取得不可判定
			if (StringUtils.isEmpty(kensakiki_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("検査機器が選択されていません。");
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
				// 変換後検査ステータス
				String convstatus = "";

				// オーダ詳細情報
				DataTable orderDt = null;
				// オーダ詳細情報取得
				orderDt = DataBase.getOrderDetail(ris_id, convstatus.split(","), risconn);

				// オーダ詳細情報取得失敗
				if (orderDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					return false;
				}

				// 検査ステータス更新
				boolean result = false;

				// アプリケーションクラス
				Application app = new Application();
                // 管理クラス初期化
                app.InitConfiguration(risconn, request);

                ExecutionInfoBean exBean = null;
                OrderInfoBean orderBean = null;
                PatientInfoBean patientBean = null;
                ArrayList patientCommentList = null;

                // 登録情報取得
        		// 実績メイン情報取得
        		exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(ris_id, risconn);

        		// オーダ情報取得
        		orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(ris_id, risconn);

        		// 患者情報の取得
        		// (標準)患者進捗が受済以降の場合は患者履歴よりデータを取得する
        		int nowStatus = TextUtil.ParseStringToInt(exBean.GetStatus());
        		
        		// 2021.12.15 Add H.Taira@COSMO Start
				// ステータスチェック
				if (Integer.parseInt(status) != nowStatus) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					return false;
				}
				// 2021.12.15 Add H.Taira@COSMO End
        		
        		int chkStatus = TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED);
        		if (chkStatus <= nowStatus)
        		{
        			// 受済→実績患者情報
        			patientBean = Configuration.GetInstance().GetCoreController().GetRisResultPatientInfo(orderBean.GetRisID(), orderBean.GetKanjaID(), risconn);
        		}
        		else
        		{
        			// 未受→最新の患者情報
        			patientBean = Configuration.GetInstance().GetCoreController().GetRisPatientInfo(orderBean.GetKanjaID(), risconn);
        		}

        		// 患者コメント
        		patientCommentList = new ArrayList();
        		patientCommentList.add(AppCommon.GetPatientComment(patientBean.GetCommentList(), MasterUtil.RIS_PATIENTCOMMENT_DEF));
        		patientCommentList.add(AppCommon.GetPatientComment(patientBean.GetCommentList(), orderBean.GetKensatypeID()));

				// 実績情報の更新(検査日、ステータス)
                Timestamp sysDate = Configuration.GetInstance().GetSysDate(risconn);

				// 更新する実績メイン情報の準備
				ExecutionInfoBean updExBean = new ExecutionInfoBean();
				updExBean.SetRisID(ris_id);
				updExBean.SetKensaDate(new SimpleDateFormat("yyyyMMdd").format(sysDate));

                // 検査ステータスを開始に設定
				updExBean.SetStatus(newstatus);

				// 拡張情報の更新有無判定
				boolean updExBeanFlg = true;

				// === ポータブルRISの仕様上、先にMWM登録を実施する =====================================================

				// MWM情報の登録
				// 情報の準備
				//OrderSearchParameter oParam = new OrderSearchParameter();
				//oParam.SetRisID(ris_id);
				DataTable setOrderDt = DataBase.getOrderList(ris_id, risconn);//Configuration.GetInstance().GetCoreController().GetRisOrderList(oParam, risconn);
				ArrayList setRisIDList = new ArrayList();
				setRisIDList.add(ris_id);
				String setGisiID = (String) SessionControler.getValue(request, SessionControler.LOGINUSER);
				String setGisiName = (String) SessionControler.getValue(request, SessionControler.LOGINUSERNAME);

				// MWM情報の登録
				MWMParameter mwmParam = new MWMParameter();
				mwmParam.SetMWMMode(CommonString.MWM_MODE_PORTABLE);
				mwmParam.SetOrderDt(setOrderDt);
				mwmParam.SetRisIDList(setRisIDList);
				mwmParam.SetAllFlg(false);
				mwmParam.SetGisiID(setGisiID);
				mwmParam.SetGisiName(setGisiName);
				mwmParam.SetKikiID(kensakiki_id);
				mwmParam.SetMstKensatype(Configuration.GetInstance().GetRRisKensaTypeMaster(risconn));
				mwmParam.SetMstKensakiki(Configuration.GetInstance().GetRRisKensaKikiMaster(risconn));
				mwmParam.SetMstBui(Configuration.GetInstance().GetRRisBuiMaster(risconn));
				mwmParam.SetMstHoukou(Configuration.GetInstance().GetRRisHoukouMaster(risconn));
				mwmParam.SetMstKHouhou(Configuration.GetInstance().GetRRisKensaHouhouMaster(risconn));
				mwmParam.SetMstDoctor(Configuration.GetInstance().GetRRisSectionDoctorMaster(risconn));
				mwmParam.SetMstSection(Configuration.GetInstance().GetRRisSectionMaster(risconn));
				// 2010.09.01 Add K.Shinohara Start
				mwmParam.SetOpeKbn(CommonString.OPE_KBN_PHOTO);
				// 2010.09.01 Add K.Shinohara End

				// MWMソケット通信タイムアウト設定
				Configuration.GetInstance().setMwmtimeout(config.getMwmtimeout());
				// かなローマ変換テキストファイル設定
				Configuration.GetInstance().setKanaromatext(config.getKanaromatext());

				try {
					// MWM登録
					AppCommon.RegisterMWM(mwmParam, risconn);
				} catch (Exception e) {
					// WorkListInfoの削除を行う
					String risID  = orderBean.GetRisID();
					String accNo  = orderBean.GetAccessionNo();
					String kikiID = Util.toNullString(kensakiki_id);
					Configuration.GetInstance().GetCoreController().DeleteWorkListByAccesionNo(risID, accNo, kikiID, CommonString.MWMDELETE_INDEX_0, risconn);

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg(e.getMessage());
					return false;
				}

				// =============================================================================================

				// 実績情報の検査日、ステータスと拡張情報の検像ステータスを更新する
				result = Configuration.GetInstance().GetCoreController().UpdateRisExecutionKensaDateStatus(
								updExBeanFlg,
								updExBean,
								risconn);

				if (!result) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("検査ステータスの更新に失敗しました。");
					return false;
				}

				// 実績情報の更新(検査開始)

				ExecutionInfoBean kStartExBean = new ExecutionInfoBean();
				kStartExBean.SetRisID(ris_id);
				// 2010.09.15 Mod K.Shinohara Start
				// 検査開始日時の初回保存チェックを行う
				String examStartDateSave = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.FIRSTONLYSAVE, RisSystemParamTbl.VALUE1_COLUMN);
				kStartExBean.SetExamStartDateTime(Configuration.GetInstance().GetSysDate(risconn));

				if ((CommonString.FLG_ON.equals(examStartDateSave)) &&
					(!Const.TIMESTAMP_MINVALUE.equals(exBean.GetExamStartDateTime())))
				{
					kStartExBean.SetExamStartDateTime(exBean.GetExamStartDateTime());
				}
				else
				{
					kStartExBean.SetExamStartDateTime(Configuration.GetInstance().GetSysDate(risconn));
				}

				kStartExBean.SetExamTerminalID("");
				//kStartExBean.SetStartNumber();	SCC内で最新＋１
				kStartExBean.SetGyoumuKbn(AppCommon.GetGyoumuKbnToday(risconn));

                // 検査ステータスを開始に設定
				kStartExBean.SetStatus(newstatus);
                // 検査室を設定
				kStartExBean.SetKensaSituID(kensasitu_id);
                // 検査室を設定
				kStartExBean.SetKensaKikiID(kensakiki_id);

				// 実績メイン情報を更新する(検査開始)
				result = Configuration.GetInstance().GetCoreController().UpdateRisKensaStartProc(kStartExBean, risconn);

				if (!result) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("検査ステータスの更新に失敗しました。");
					return false;
				}

				// 実績操作履歴情報の登録[検査開始]
				ExamOperationHistoryInfoBean operationBean = new ExamOperationHistoryInfoBean();
				operationBean.SetRisID(ris_id);
				operationBean.SetOperationType(Integer.parseInt(CommonString.OPERATIONTYPE_START_OPERATE));
				Configuration.GetInstance().GetCoreController().RegisterRisExamOperationHistory(operationBean, risconn);

				// 2020.09.01 Add Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器・入外区分・検査種別)
				//検査室・検査機器・入外区分・検査種別の選択したプルダウンの値のDB登録処理(PortableRIS_TerminalInfo)

				//操作端末名を取得
				String termName = Configuration.GetInstance().GetTerminalInfoBean().GetTerminalName();
				//IPアドレスを取得
				String ipaddress = request.getRemoteAddr();

				// 検査室、検査機器・入外区分・検査種別を選択した値に更新/登録
				boolean updateresult1 = DataBase.PortableRIS_TerminalInfoUpdate(termName, ipaddress, kensasitu_id, kensakiki_id, kanja_nyugai, kensatype_id, risconn);
				// 更新失敗時
				if (!updateresult1) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("PORTABLERIS_TERMINALINFOの更新に失敗しました。");
					return false;
				}
				// 2020.09.01 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器・入外区分・検査種別)

				// 2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
				// EXMAINTABLEの検査技師IDと検査技師名を詳細画面で選択した実施者に更新する。
				boolean updateresult2 = DataBase.zishisha_Update(ris_id, zisshisha_id1, zisshisha_id2, zisshisha_id3,
																							zisshisha_name1, zisshisha_name2, zisshisha_name3, risconn);
				// 更新失敗時
				if (!updateresult2) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("実施者(担当技師)の更新に失敗しました。");
					return false;
				}

				// コミット
				risconn.commit();
				// 2020.09.16 Add Nishihara@COSMO end 実施技師登録機能追加対応

			} finally {
				// 2019.08.06 Add Cosmo Start 排他対応
				Configuration.GetInstance().GetCoreController().unCheckOutExclusive(dto,ris_id,risconn,config.getExclusive());
				// 2019.08.06 Add Cosmo End 排他対応
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
	 * 終了 処理実行
	 * @param ris_id       :RIS識別ID
	 * @param newstatus    :更新後検査ステータス
	 * @param kensasitu_id :検査室ID
	 * @param kensakiki_id :検査機器ID
	 * @param mpps         :MPPS取得フラグ
	 * @param request      :httpリクエスト
	 * @param dto          :ステータス更新情報
	 * @return
	 */
	private boolean ExecuteFinished(
			String ris_id,
			String newstatus,
			String kensasitu_id,
			String kensakiki_id,
			//2020.09.10 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
			String kanja_nyugai,
			String kensatype_id,
			//2020.09.10 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)
			//2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
			String zisshisha_id1,
			String zisshisha_id2,
			String zisshisha_id3,
			String zisshisha_name1,
			String zisshisha_name2,
			String zisshisha_name3,
			//2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
			String mpps,
			HttpServletRequest request,
			StatusUpdateDto dto) throws Exception {

		try{
			// パラメータ取得不可判定
			if (StringUtils.isEmpty(ris_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("引数が不足している為、処理が行えませんでした。");
				return false;
			}

			// パラメータ取得不可判定
			if (StringUtils.isEmpty(kensasitu_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("検査室が選択されていません。");
				return false;
			}

			// パラメータ取得不可判定
			if (StringUtils.isEmpty(kensakiki_id)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("検査機器が選択されていません。");
				return false;
			}

			// パラメータ取得不可判定
			if (StringUtils.isEmpty(mpps)) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg("MPPS情報が未設定の為、処理が行えませんでした。");
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
				// 変換後検査ステータス
				String convstatus = "";

				// オーダ詳細情報
				DataTable orderDt = null;
				// オーダ詳細情報取得
				orderDt = DataBase.getOrderDetail(ris_id, convstatus.split(","), risconn);

				// オーダ詳細情報取得失敗
				if (orderDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("現在のステータスは最新ではありません。\n一覧画面に戻って更新を行って下さい。");
					return false;
				}

				// 検査ステータス更新
				boolean result = false;

				// アプリケーションクラス
				Application app = new Application();
                // 管理クラス初期化
                app.InitConfiguration(risconn, request);

                ExecutionInfoBean exBean = null;
                OrderInfoBean orderBean = null;
                PatientInfoBean patientBean = null;
                ArrayList patientCommentList = null;

                // 登録情報取得
        		// 実績メイン情報取得
        		exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(ris_id, risconn);

        		// オーダ情報取得
        		orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(ris_id, risconn);

        		// 患者情報の取得
        		// (標準)患者進捗が受済以降の場合は患者履歴よりデータを取得する
        		int nowStatus = TextUtil.ParseStringToInt(exBean.GetStatus());
        		int chkStatus = TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED);
        		if (chkStatus <= nowStatus)
        		{
        			// 受済→実績患者情報
        			patientBean = Configuration.GetInstance().GetCoreController().GetRisResultPatientInfo(orderBean.GetRisID(), orderBean.GetKanjaID(), risconn);
        		}
        		else
        		{
        			// 未受→最新の患者情報
        			patientBean = Configuration.GetInstance().GetCoreController().GetRisPatientInfo(orderBean.GetKanjaID(), risconn);
        		}

        		// 患者コメント
        		patientCommentList = new ArrayList();
        		patientCommentList.add(AppCommon.GetPatientComment(patientBean.GetCommentList(), MasterUtil.RIS_PATIENTCOMMENT_DEF));
        		patientCommentList.add(AppCommon.GetPatientComment(patientBean.GetCommentList(), orderBean.GetKensatypeID()));

				// 検査室ID
				exBean.SetKensaSituID(kensasitu_id);
        		// 検査機器ID
				exBean.SetKensaKikiID(kensakiki_id);
				// 検査責任者ID
				exBean.SetJisisyaID(Configuration.GetInstance().GetUserAccountBean().GetUserID());
				// 検査責任者名
				exBean.SetJisisyaName(Configuration.GetInstance().GetUserAccountBean().GetUserName());
				// 最終入力者ID
				exBean.SetKensaGisiID(Configuration.GetInstance().GetUserAccountBean().GetUserID());
				// 最終入力者名
				exBean.SetKensaGisiName(Configuration.GetInstance().GetUserAccountBean().GetUserName());

				// mod sta 201806_ポータブルRIS検査系種別対応
				//// 実績撮影情報が無い場合、プリセットからデータを作成する
        		//if (exBean.GetExSatueiList().size() == 0) {

				boolean iskensakei = isOperationKbnInspect(orderBean.GetKensatypeID(), risconn);

				// 撮影区分が撮影系の場合 & 実績撮影情報が無い場合、プリセットからデータを作成する
				if (exBean.GetExSatueiList().size() == 0 && !iskensakei){

				// mod end 201806_ポータブルRIS検査系種別対応

    				ArrayList satueiList = new ArrayList();
    				ArrayList partsList  = new ArrayList();
    				ArrayList infuseList = new ArrayList();
    				ArrayList filmList = new ArrayList();

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
    								satueiBean.SetRisID(exBean.GetRisID());
    								satueiBean.SetBuiNo(buiBean.GetNo());
    								satueiBean.SetNo(no.toString());
    								satueiBean.SetSatueiStatus(buiBean.GetSatueiStatus());
    								satueiBean.SetSatueiMenuID(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_ID).toString());
    								satueiBean.SetSatueiMenuName(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_NAME).toString());
    								satueiBean.SetSatueiMenuNameKana(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIMENU_NAME_KANA).toString());
    								satueiBean.SetSatueiCode(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEI_CODE + "01").toString());
    								satueiBean.SetFilmID(menuDt.getRows().get(0).get(MasterUtil.RIS_FILM_ID).toString());
    								satueiBean.SetPartition(menuDt.getRows().get(0).get(MasterUtil.RIS_PARTITION).toString());
    								satueiBean.SetUsed(menuDt.getRows().get(0).get(MasterUtil.RIS_USED).toString());
        							//satueiBean.SetReshotFlg(menuDt.getRows().get(0).get(MasterUtil.RIS_RESHOT_FLG).toString());
        							//satueiBean.SetCassetteNo(menuDt.getRows().get(0).get(MasterUtil.RIS_CASSETTE_NO).toString());
        							//satueiBean.SetPortableStatus(menuDt.getRows().get(0).get(MasterUtil.RIS_PORTABLE_STATUS).toString());
        							//satueiBean.SetKensaKikiID(menuDt.getRows().get(0).get(MasterUtil.RIS_KENSAKIKI_ID).toString());
        							//satueiBean.SetSatueiAddFlag(menuDt.getRows().get(0).get(MasterUtil.RIS_SATUEIADDFLAG).toString());
    								satueiBean.SetExamDataToBean2(menuDt.getRows().get(0));	//ExamData01~20
    								satueiBean.SetMppsDataToBean(satueiRow);	//Mpps情報
        							//
        							satueiList.add(satueiBean);

        							no++;
    							}
    						}

    						no = 1;

    						// 造影剤
    						for (int n = 0; n < presetinfo.GetZoueizaiList().size(); n++){

    							DataRow itemRow = (DataRow) presetinfo.GetZoueizaiList().get(n);

    							// 器材Beanへ変換
    							ExZoueizaiBean partsBean = new ExZoueizaiBean();
    							partsBean.SetRisID(exBean.GetRisID());
    							partsBean.SetBuiNo(buiBean.GetNo());
    							partsBean.SetNo(no);
    							partsBean.SetPartsID(itemRow.get(MasterUtil.RIS_ZOUEIZAI_ID).toString());
    							partsBean.SetSuuryoIji(itemRow.get(MasterUtil.RIS_SUURYOU_IJI).toString());
    							partsBean.SetSuuryo(itemRow.get(MasterUtil.RIS_SUURYOU).toString());
    							partsBean.SetPartsBunruiID(itemRow.get(MasterUtil.RIS_PARTSBUNRUI_ID).toString());
    							partsBean.SetDetailPartsBunruiID(itemRow.get(MasterUtil.RIS_DETAILPARTSBUNRUI_ID).toString());
    							//partsBean.SetBarcodeData(itemRow.get(MasterUtil.RIS_BARCODEDATA).toString());

    							//
    							partsList.add(partsBean);

    							no++;
    						}

    						no = 1;

    						// 手技
    						for (int n = 0; n < presetinfo.GetInfuseList().size(); n++){

    							DataRow itemRow = (DataRow) presetinfo.GetInfuseList().get(n);

    							// 手技Beanへ変換
    							ExInfuseBean infuseBean = new ExInfuseBean();
    							infuseBean.SetRisID(exBean.GetRisID());
    							infuseBean.SetBuiNo(buiBean.GetNo());
    							infuseBean.SetNo(no.toString());
    							infuseBean.SetInfuseID(itemRow.get(MasterUtil.RIS_INFUSE_ID).toString());
    							infuseBean.SetSuuryoIji(itemRow.get(MasterUtil.RIS_SUURYOU_IJI).toString());
    							infuseBean.SetSuuryo(itemRow.get(MasterUtil.RIS_SUURYOU).toString());
    							infuseBean.SetPartsBunruiID(itemRow.get(MasterUtil.RIS_PARTSBUNRUI_ID).toString());
    							infuseBean.SetDetailPartsBunruiID(itemRow.get(MasterUtil.RIS_DETAILPARTSBUNRUI_ID).toString());
    							//infuseBean.SetBarcodeData(itemRow.get(MasterUtil.RIS_BARCODEDATA).toString());

    							//
    							infuseList.add(infuseBean);

    							no++;
    						}

    						no = 1;

    						// フィルム
    						for (int n = 0; n < presetinfo.GetFilmList().size(); n++){

    							DataRow filmRow = (DataRow) presetinfo.GetFilmList().get(n);

    							// フィルム情報Bean作成
    							ExFilmBean filmBean = new ExFilmBean();

    							filmBean.SetRisID(exBean.GetRisID());
    							filmBean.SetBuiNo(buiBean.GetNo());
    							filmBean.SetNo(no.toString());
    							filmBean.SetFilmID(filmRow.get(MasterUtil.RIS_FILM_ID).toString());
    							filmBean.SetPartition(filmRow.get(MasterUtil.RIS_PARTITION).toString());
    							// 2024.07.24 Mod Nishihara@COSMO Start 帝京大学病院環境対応
    							// filmBean.SetUsed(filmRow.get(MasterUtil.RIS_USED).toString());
    							filmBean.SetUsed(filmRow.get(MasterUtil.RIS_USE).toString());
    							// 2024.07.24 Mod Nishihara@COSMO End   帝京大学病院環境対応
    							filmBean.SetLoss(filmRow.get(MasterUtil.RIS_LOSS).toString());

    							//
    							filmList.add(filmBean);

    							no++;
    						}
    					}
    				}

    				// 各リストを設定
    				exBean.ReconstructExSatueiList(satueiList);
    				exBean.ReconstructExZoueizaiList(partsList);
    				exBean.ReconstructExInfuseList(infuseList);
    				exBean.ReconstructExFilmList(filmList);
        		}

				// MPPS結果情報取得
				DataTable mppsResultDt = null;
				DataTable crsatueiDt = null;

				// ACNO取得
				String acno = orderBean.GetAccessionNo();

				// MPPS情報取得
				if (Const.MPPS_GET_ON.equals(mpps)) {

					// MPPS結果情報取得
					mppsResultDt = getMppsResultData(acno, config.getMppstimeout());

					// MPPS結果情報コピー
					DataTable mppsCopyDt = null;

					// MPPS結果が取得出来た場合
					if (mppsResultDt != null) {
						mppsCopyDt = mppsResultDt.copy();
					}

					// 撮影情報の検索
					RequestParameter rParam = new RequestParameter();
					crsatueiDt = Configuration.GetInstance().GetCoreController().GetRisSatueiMenuDataTable(rParam, risconn);

					// 撮影／MPPS情報設定
					setInfoList(exBean.GetExSatueiList(), mppsCopyDt, crsatueiDt, dto);

					// 撮影情報が0件の場合、または、撮影情報とMPPS情報の件数の不整合が発生した場合、または、引当ができないようであれば保留にする。
					if ((dto.getSatuei_array().size() == 0)
							|| (dto.getSatuei_array().size() != dto.getMpps_array().size())
							|| !isMppsHikiate(exBean.GetExSatueiList(), mppsCopyDt))
					{
		                // 検査ステータスを保留に設定
		                exBean.SetStatus(Const.STATUS_REST);

		                // 検査情報保存
						result = Configuration.GetInstance().GetCoreController().SaveRisExecutionInfo(exBean, patientCommentList, risconn);

						if (!result) {
							risconn.rollback();
							dto.setResult(Const.RESULT_NG);
							dto.setErrlevel(Const.ERRLEVEL_WARN);
							dto.setMsg("検査ステータスの更新に失敗しました。");
							return false;
						} else {

							// 実績操作履歴情報の登録[検査保留]
							ExamOperationHistoryInfoBean operationBean = new ExamOperationHistoryInfoBean();
							operationBean.SetRisID(orderBean.GetRisID());
							operationBean.SetOperationType(Integer.parseInt(CommonString.OPERATIONTYPE_HOLD_OPERATE));
							Configuration.GetInstance().GetCoreController().RegisterRisExamOperationHistory(operationBean, risconn);

							// WorkListInfoの削除を行う
							String risID  = orderBean.GetRisID();
							String accNo  = orderBean.GetAccessionNo();
							String kikiID = Util.toNullString(kensakiki_id);
							Configuration.GetInstance().GetCoreController().DeleteWorkListByAccesionNo(risID, accNo, kikiID, CommonString.MWMDELETE_INDEX_0, risconn);
						}

						return true;
					}

					// 撮影／MPPS情報を破棄する
					dto.setSatuei_array(null);
					dto.setMpps_array(null);
				}

				// 再送か判断する
				boolean reSendFlg = false;
				if (exBean.GetEndCount() > 0)
				{
					reSendFlg = true;
				}

				// 実績情報の更新
				// 2010.09.15 Mod K.Shinohara Start
				// 検査開始日時の初回保存チェックを行う
				String examStartDateSave = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.FIRSTONLYSAVE, RisSystemParamTbl.VALUE1_COLUMN);

				// 実施済以外
				if (!CommonString.STATUS_ISFINISHED.equals(exBean.GetStatus()))
				{
					// 2010.08.31 Mod K.Shinohara Start
					// 検査終了日時の初回保存チェックを行う
					String examEndDateSave = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.FIRSTONLYSAVE, RisSystemParamTbl.VALUE2_COLUMN);
					if (!(CommonString.FLG_ON.equals(examEndDateSave)) ||
						(!reSendFlg))
					{
						exBean.SetExamEndDateTime(Configuration.GetInstance().GetSysDate(risconn));	// 現在のSysDate
					}
					// 2010.08.31 Mod K.Shinohara End
				}

				// 検査ステータス
				exBean.SetStatus(newstatus);

        		// 実績部位情報を更新
        		if (exBean.GetExBuiList().size() > 0) {
        			// 撮影情報
					for (int n = 0; n < exBean.GetExBuiList().size(); n++){

						ExBuiBean buiBean = (ExBuiBean) exBean.GetExBuiList().get(n);

						// 撮影ステータスが中止の場合は、対象外とする
						if (CommonString.SATUEISTATUS_STOP.equals(buiBean.GetSatueiStatus())) {
							continue;
						}

						// 撮影ステータス更新
						buiBean.SetSatueiStatus(CommonString.SATUEISTATUS_SUMI);

						// add sta 201806_ポータブルRIS検査系種別対応
						// 検査系の場合"撮影済のみ(ﾌﾟﾘｾｯﾄ無)"
						if(iskensakei) {
							buiBean.SetPresetName(CommonString.PRESET_ID_DUMMY_STR);
						}
						// add end 201806_ポータブルRIS検査系種別対応
					}
        		}

				// 実績撮影情報を更新
        		if (exBean.GetExSatueiList().size() > 0) {

        			// フィルムマスタ取得
        			DataTable filmDt = Configuration.GetInstance().GetRRisFilmMaster(risconn);

        			// 撮影情報
					for (int n = 0; n < exBean.GetExSatueiList().size(); n++){

						ExSatueiBean satueiBean = (ExSatueiBean) exBean.GetExSatueiList().get(n);

						// 撮影ステータスが中止の場合は、対象外とする
						if (CommonString.SATUEISTATUS_STOP.equals(satueiBean.GetSatueiStatus())) {
							continue;
						}

						// 撮影ステータス更新
						satueiBean.SetSatueiStatus(CommonString.SATUEISTATUS_SUMI);
						// 撮影検査機器ID更新
						satueiBean.SetKensaKikiID(kensakiki_id);

						// 実績撮影情報MPPS引当処理
						if (Const.MPPS_GET_ON.equals(mpps)) {

							// MPPS引き当て処理
							MppsMachingForSatueiCode(satueiBean, mppsResultDt, filmDt);

						}
					}

        			// 撮影情報を逆からループし引当てる
					for (int n = exBean.GetExSatueiList().size() -1; n >= 0; n--){

						ExSatueiBean satueiBean = (ExSatueiBean) exBean.GetExSatueiList().get(n);

						// 撮影ステータスが中止の場合は、対象外とする
						if (CommonString.SATUEISTATUS_STOP.equals(satueiBean.GetSatueiStatus())) {
							continue;
						}

						// 実績撮影情報MPPS引当処理
						if (Const.MPPS_GET_ON.equals(mpps)) {

							// MPPS引き当て追加処理
							if (MppsMachingAddForSatueiCode(exBean.GetExSatueiList(), satueiBean, mppsResultDt, filmDt)) {
								// 引当追加が行えた場合は、再ループ
								n++;
							}
						}
					}

					// 実績撮影情報MPPS引当処理
					if (Const.MPPS_GET_ON.equals(mpps)) {

						// MPPS残引き当て処理
						if (!MppsMachingAddForSatueiCode(exBean.GetExSatueiList(), mppsResultDt, filmDt, crsatueiDt)) {
							dto.setResult(Const.RESULT_NG);
							dto.setErrlevel(Const.ERRLEVEL_WARN);
							dto.setMsg("MPPS追加引当処理が、正常に行えませんでした。\nRISの方から実施して下さい。");
							return false;
						}
					}
        		}

        		// フィルム情報
				ArrayList<ExFilmBean> filmList = new ArrayList<ExFilmBean>();

				try {
        			// フィルム計算
        			FilmAutoCalc(exBean.GetExSatueiList(), filmList);
        		} catch (Exception e) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg(e.getMessage());
					return false;
        		}

				// フィルムリストを設定
				exBean.ReconstructExFilmList(filmList);

				// 検査終了初回のみ
				if (!reSendFlg)
				{
					// 患者実績情報更新判定
					String patResultUpdStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.PATINFCHANGE, RisSystemParamTbl.VALUE1_COLUMN);
					if (!CommonString.PATRESULT_UPD_FLG_OFF.equals(patResultUpdStr))
					{
						exBean.SetUpdatePatientResultsInfo(true);
					}
				}

				// 実績情報を更新する(検査終了)
				result = Configuration.GetInstance().GetCoreController().UpdateRisKensaFinishProc(exBean, patientCommentList, risconn);

				if (!result) {
					risconn.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("検査ステータスの更新に失敗しました。");
					return false;
				}

				ExamOperationHistoryInfoBean operationBean = new ExamOperationHistoryInfoBean();

				// 実績操作履歴情報の登録[実績登録]
				operationBean = new ExamOperationHistoryInfoBean();
				operationBean.SetRisID(ris_id);
				operationBean.SetOperationType(Integer.parseInt(CommonString.OPERATIONTYPE_REGISTER_OPERATE));
				Configuration.GetInstance().GetCoreController().RegisterRisExamOperationHistory(operationBean, risconn);

				// ToHisInfoへの書込み
				if (!CommonString.SEISAN_FLG_SUMI.equals(orderBean.GetExtendOrderInfoBean().GetSeisanFlg()))
				{
					// 未清算のみ行う
					boolean retToHisFlg = Configuration.GetInstance().GetCoreController().RegisterToHisInfoByOperation(
						orderBean,
						null,
						reSendFlg,
						newstatus,
						risconn);

					if (!retToHisFlg)
					{
						// エラーメッセージ
						String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerToHisInfoException_MessageString);
						logger.fatal(msg);
					}
				}

				// ToReportInfo, ToPacsInfoへの書込み
				boolean retToReportFlg = Configuration.GetInstance().GetCoreController().RegisterToReportPacsInfoByOperation(
						orderBean,
						null,
						reSendFlg,
						newstatus,
						risconn);

				if (!retToReportFlg)
				{
					// エラーメッセージ
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerToReportPacsInfoException_MessageString);
					logger.fatal(msg);
				}

				// 実績操作履歴情報の登録[HIS実績送信]
				operationBean = new ExamOperationHistoryInfoBean();
				operationBean.SetRisID(ris_id);
				operationBean.SetOperationType(Integer.parseInt(CommonString.OPERATIONTYPE_SEND_OPERATE));
				Configuration.GetInstance().GetCoreController().RegisterRisExamOperationHistory(operationBean, risconn);

				// WorkListInfoの削除を行う
				Configuration.GetInstance().GetCoreController().DeleteWorkListByAccesionNo(ris_id, acno, kensakiki_id, CommonString.MWMDELETE_INDEX_0, risconn);

				// 2020.08.31 Add Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器・入外区分・検査種別)
				//検査室・検査機器・入外区分・検査種別を選択したプルダウンの値のDB登録処理(PortableRIS_TerminalInfo)

				//操作端末名を取得
				String termName = Configuration.GetInstance().GetTerminalInfoBean().GetTerminalName();
				//IPアドレスを取得
				String ipaddress = request.getRemoteAddr();

				// 検査室、検査機器・入外区分・検査種別を選択した値に更新/登録
				boolean updateresult1 = DataBase.PortableRIS_TerminalInfoUpdate(termName, ipaddress, kensasitu_id, kensakiki_id, kanja_nyugai, kensatype_id, risconn);
				// 更新失敗時
				if (!updateresult1) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("PORTABLERIS_TERMINALINFOの更新に失敗しました。");
					return false;
				}

				// 2020.08.31 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器・入外区分・検査種別)

				// 2020.09.16 Add Nishihara@COSMO start 実施技師登録機能追加対応
				// EXMAINTABLEの検査技師IDと検査技師名を詳細画面で選択した実施者に更新する。
				boolean updateresult2 = DataBase.zishisha_Update(ris_id, zisshisha_id1, zisshisha_id2, zisshisha_id3,
																							zisshisha_name1, zisshisha_name2, zisshisha_name3, risconn);
				// 更新失敗時
				if (!updateresult2) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("実施者(担当技師)の更新に失敗しました。");
					return false;
				}

				// コミット
				risconn.commit();
				// 2020.09.16 Add Nishihara@COSMO end 実施技師登録機能追加対応

			} finally {
				// 2019.08.06 Add Cosmo Start 排他対応
				Configuration.GetInstance().GetCoreController().unCheckOutExclusive(dto,ris_id,risconn,config.getExclusive());
				// 2019.08.06 Add Cosmo End 排他対応
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
		ArrayList presetBeanList = Configuration.GetInstance().GetCoreController().GetRisPresetInfoData(pParam, con);

		PresetInfoBean presetBean = null;
		if (presetBeanList != null && presetBeanList.size() > 0)
		{
			presetBean = (PresetInfoBean)presetBeanList.get(0);
		}

		return presetBean;
	}

	/**
	 * MPPS結果情報取得
	 * @param acno
	 * @param timeout
	 * @throws Exception
	 */
	private DataTable getMppsResultData(String acno, Integer timeout) throws Exception {

		Connection con = null;
		DataTable mppsDt = null;
		DataTable mppsResultDt = null;
		long loopTimeout = System.currentTimeMillis() + timeout;

		try {

			//con = DataBase.getMppsConnection(Configuration.GetInstance().GetMppsServiceName(), Configuration.GetInstance().GetMppsUser(), Configuration.GetInstance().GetMppsPassword());
			con = DataBase.getMppsConnection();

			// 接続情報チェック
			if (con == null) {
				throw new Exception("MPPS用のDB接続情報が設定されていません。");
			}

			// 指定時間経過で処理を抜ける
			while (System.currentTimeMillis() <= loopTimeout)
			{
				// 撮影系-MPPSマスタ情報を取得する
				 mppsDt = Configuration.GetInstance().GetCoreController().GetSatueiMppsMasterData(acno, con);

				if (mppsDt.getRowCount() > 0)
				{
					break;
				}
				else
				{
					// リトライ
					Thread.sleep(1000);
				}
			}

			// 撮影系-MPPSマスタ情報が取得出来た場合、MPPS結果情報を取得する
			if (mppsDt != null && mppsDt.getRowCount() > 0) {
				// 撮影系-MPPS結果情報を取得する
				 mppsResultDt = Configuration.GetInstance().GetCoreController().GetSatueiMppsResultData(mppsDt.getRows().get(0).get(MPMppsMasterTbl.SOPINSTANCEUID_COLUMN).toString() , con);
			}

			// 撮影系-MPPS結果情報が取得できた場合
			if (mppsResultDt != null) {

				// 実績情報をSYSTEMDEFINEの定義に応じて変換する
				for (int i = 0; i < mppsResultDt.getRowCount(); i++) {

					DataRow mpRow = mppsResultDt.getRows().get(i);

					String xray = mpRow.get(MPExposureDoseTbl.RDXRAYTUBECURRENTINUA_COLUMN).toString();
					String film = mpRow.get(MPFilmConsumptionTbl.BAMCNUMBEROFFILMS_COLUMN).toString();

					// μA→mAへ変換
					if (CommonString.FLG_ON.equals(Configuration.GetInstance().GetMppsUnitFlg())) {
						Double xrayDbl = TextUtil.ParseStringToDouble(xray);
						if (!Const.DOUBLE_MINVALUE.equals(xrayDbl)) {
							xrayDbl = xrayDbl / 1000;
							xray = xrayDbl.toString();
						}
					}

					mpRow.put(MPExposureDoseTbl.RDXRAYTUBECURRENTINUA_COLUMN, xray);

					// フィルム数判定
					if (CommonString.FLG_ON.equals(Configuration.GetInstance().GetMppsFilmSetType()))
					{
						// nullまたは0を空へ設定する
						int filmInt = TextUtil.ParseStringToInt(film);
						if (filmInt == 0)
						{
							film = "";
						}
					}

					mpRow.put(MPFilmConsumptionTbl.BAMCNUMBEROFFILMS_COLUMN, film);
				}
			}
		} catch (Exception e) {
			logger.error("MPPS情報が取得できませんでした。 " + e.toString());
		} finally {
			DataBase.closeConnection(con);
		}

		return mppsResultDt;
	}

	/**
	 * 撮影情報/MPPS情報リスト取得
	 * @param dt :撮影情報/MPPS情報
	 * @return
	 */
	private void setInfoList(ArrayList satueiList, DataTable mppsDt, DataTable crsatueiDt, StatusUpdateDto dto) throws Exception {

		// RIS撮影情報リスト
		ArrayList<StatusUpdateDto.Info> satueiInfoList = new ArrayList<StatusUpdateDto.Info>();

		if (satueiList != null) {
			for (int i = 0; i < satueiList.size(); i++)
			{
				ExSatueiBean satueiBean = (ExSatueiBean)satueiList.get(i);

				// 撮影ステータスが中止の場合は、対象外とする
				if (CommonString.SATUEISTATUS_STOP.equals(satueiBean.GetSatueiStatus())) {
					continue;
				}

				String menuid = "";
				String menucode = "";
				String menuname = "";
				String menunamekana = "";

				menuid = satueiBean.GetSatueiMenuID();

				DataRow row = getSatueiRow(crsatueiDt, menuid, RisCrSatueiMenuMaster.SATUEIMENU_ID_COLUMN);

				if (row != null) {
					menuid = row.get(RisCrSatueiMenuMaster.SATUEIMENU_ID_COLUMN).toString();
					menucode = row.get(RisCrSatueiMenuMaster.SATUEI_CODE01_COLUMN).toString();
					menuname = row.get(RisCrSatueiMenuMaster.SATUEIMENU_NAME_COLUMN).toString();
					menunamekana = row.get(RisCrSatueiMenuMaster.SATUEIMENU_NAME_KANA_COLUMN).toString();

					// 念のため、補填
					satueiBean.SetSatueiCode(menucode);
					satueiBean.SetSatueiMenuName(menuname);
					satueiBean.SetSatueiMenuNameKana(menunamekana);
				}

				StatusUpdateDto.Info info = new StatusUpdateDto.Info();

				// 情報設定
				info.setId(menucode);
				info.setName(menuname);

				satueiInfoList.add(info);
			}
		}

		// 撮影情報設定
		dto.setSatuei_array(satueiInfoList);

		// MPPS情報リスト
		ArrayList<StatusUpdateDto.Info> mppsInfoList = new ArrayList<StatusUpdateDto.Info>();

		if (mppsDt != null) {
			for (int i = 0; i < mppsDt.getRowCount(); i++) {

				String menuid = "";
				String menucode = "";
				String meaning = "";
				String menuname = "";

				menucode = mppsDt.getRows().get(i).get(MPPreformedProtocolCodeTbl.CODEVALUE_COLUMN).toString();
				meaning = mppsDt.getRows().get(i).get(MPPreformedProtocolCodeTbl.CODEMEANING_COLUMN).toString();

				// MPPS追加撮影、または、再撮影の場合はスキップする
				if (meaning.indexOf(Const.MPPS_CODEMEANING_ADD) > -1 || meaning.indexOf(Const.MPPS_CODEMEANING_AGAIN) > -1) {
					continue;
				}

				// MPPS通常撮影のみ対象とする

				DataRow row = getSatueiRow(crsatueiDt, menucode, RisCrSatueiMenuMaster.SATUEI_CODE01_COLUMN);

				if (row != null) {
					menuid = row.get(RisCrSatueiMenuMaster.SATUEIMENU_ID_COLUMN).toString();
					menucode = row.get(RisCrSatueiMenuMaster.SATUEI_CODE01_COLUMN).toString();
					menuname = row.get(RisCrSatueiMenuMaster.SATUEIMENU_NAME_COLUMN).toString();
				}

				StatusUpdateDto.Info info = new StatusUpdateDto.Info();

				// 情報設定
				info.setId(menucode);
				info.setName(menuname);

				mppsInfoList.add(info);
			}
		}

		// MPPS情報設定
		dto.setMpps_array(mppsInfoList);
	}

	/**
	 * 撮影情報取得
	 * @param dt
	 * @param menuid
	 * @return
	 */
	private DataRow getSatueiRow(DataTable dt, String satuei, String col) {

		DataRow ret = null;

		if (StringUtils.isEmpty(satuei)) {
			return ret;
		}

		for (int i = 0; i < dt.getRowCount(); i++) {

			String s = dt.getRows().get(i).get(col).toString();

			if (satuei.equals(s)) {
				ret = dt.getRows().get(i);
				break;
			}
		}

		// 撮影コードで取得できなかった場合は、末尾文字を除外してもう一度取得を行う
		if (ret == null && RisCrSatueiMenuMaster.SATUEI_CODE01_COLUMN.equals(col)) {
			for (int i = 0; i < dt.getRowCount(); i++) {

				String s = dt.getRows().get(i).get(col).toString();

				if (StringUtils.isEmpty(s)) {
					continue;
				}

				if (satuei.substring(0, satuei.length() -1).equals(s.substring(0, s.length() -1))) {
					ret = dt.getRows().get(i);
					break;
				}
			}
		}

		return ret;
	}

	/**
	 * 撮影情報/MPPS情報引当確認
	 * @param satueiList
	 * @param mppsDt
	 * @throws Exception
	 */
	private boolean isMppsHikiate(ArrayList satueiList, DataTable mppsDt) throws Exception {

		if (satueiList != null) {
			for (int i = 0; i < satueiList.size(); i++)
			{
				ExSatueiBean satueiBean = (ExSatueiBean)satueiList.get(i);

				// 撮影ステータスが中止の場合は、対象外とする
				if (CommonString.SATUEISTATUS_STOP.equals(satueiBean.GetSatueiStatus())) {
					continue;
				}

				if (mppsDt != null) {

					// 引き当てチェック
					Integer index = MppsMachingForSatueiCode(satueiBean, mppsDt, true);

					// 引き当てできない場合
					if (index == null) {
						return false;
					}

					// 引き当てが完了したレコードは削除する
					mppsDt.remove(index);
				}
			}
		}

		return true;
	}

	/// <summary>
	/// 撮影コード引当確認
	/// </summary>
	private Integer MppsMachingForSatueiCode(ExSatueiBean satueiBean, DataTable mpDt, boolean normalOnly)
	{
		Integer ret = null;

		// 撮影情報を読み込む
		String sRowSCode  = satueiBean.GetSatueiCode();

		String sSatueiCode = "";
		String mSatueiCode = "";

		for (int i = 0; i < mpDt.getRowCount(); i++) {
			DataRow mpRow = mpDt.getRows().get(i);

			// MPPS情報を読み込む
			String rowSCode = mpRow.get(MPPreformedProtocolCodeTbl.CODEVALUE_COLUMN).toString();
			String meaning  = mpDt.getRows().get(i).get(MPPreformedProtocolCodeTbl.CODEMEANING_COLUMN).toString();

			// MPPS通常撮影のみの場合
			if (normalOnly) {
				// MPPS追加撮影、または、再撮影の場合はスキップする
				if (meaning.indexOf(Const.MPPS_CODEMEANING_ADD) > -1 || meaning.indexOf(Const.MPPS_CODEMEANING_AGAIN) > -1) {
					continue;
				}
			} else {
				// MPPS通常撮影の場合はスキップする
				if (!(meaning.indexOf(Const.MPPS_CODEMEANING_ADD) > -1 || meaning.indexOf(Const.MPPS_CODEMEANING_AGAIN) > -1)) {
					continue;
				}
			}

			// 撮影ｺｰﾄﾞ判定
			boolean matchFlg = IsMppsMatchingSatueiCode(sRowSCode, rowSCode, sSatueiCode, mSatueiCode);

			if (!matchFlg)
			{
				continue;
			}

			ret = i;
			break;
		}

		return ret;
	}

	/// <summary>
	/// 撮影コード引当処理：通常撮影
	/// </summary>
	/// <param name="row"></param>
	/// <param name="tbl"></param>
	private void MppsMachingForSatueiCode(ExSatueiBean satueiBean, DataTable mpDt, DataTable filmDt)
	{
		// 撮影情報を読み込む
		String sRowBuiNo  = satueiBean.GetBuiNo();
		String sRowSMenu  = satueiBean.GetSatueiMenuName();
		String sRowSCode  = satueiBean.GetSatueiCode();

		String sSatueiCode = "";
		String mSatueiCode = "";

		if (mpDt != null) {
			for (int i = 0; i < mpDt.getRowCount(); i++) {

				DataRow mpRow = mpDt.getRows().get(i);

				// MPPS情報を読み込む
				String rowSOPID		= mpRow.get(MPPreformedProtocolCodeTbl.MPPSSOPINSTANCEUID_COLUMN).toString();
				String rowVMNO		= mpRow.get(MPPreformedProtocolCodeTbl.VMNO_COLUMN).toString();
				String rowSCode		= mpRow.get(MPPreformedProtocolCodeTbl.CODEVALUE_COLUMN).toString();
				String rowFilmID	= mpRow.get(MPFilmConsumptionTbl.BAMCFILMSIZEID_COLUMN).toString();
				String rowFilmCnt	= mpRow.get(MPFilmConsumptionTbl.BAMCNUMBEROFFILMS_COLUMN).toString();
				String rowKVP		= mpRow.get(MPExposureDoseTbl.RDKVP_COLUMN).toString();
				String rowXRAY		= mpRow.get(MPExposureDoseTbl.RDXRAYTUBECURRENTINUA_COLUMN).toString();
				String rowTIME		= mpRow.get(MPExposureDoseTbl.RDEXPOSURETIME_COLUMN).toString();
				String meaning		= mpRow.get(MPPreformedProtocolCodeTbl.CODEMEANING_COLUMN).toString();

				// MPPS追加撮影、または、再撮影の場合はスキップする
				if (meaning.indexOf(Const.MPPS_CODEMEANING_ADD) > -1 || meaning.indexOf(Const.MPPS_CODEMEANING_AGAIN) > -1) {
					continue;
				}

				// MPPS通常撮影のみ対象とする

				boolean matchFlg = IsMppsMatchingSatueiCode(sRowSCode, rowSCode, sSatueiCode, mSatueiCode);

				if (!matchFlg)
				{
					continue;
				}

				// 撮影情報 - MPPS情報設定
				satueiBean.SetMPMppsInstanceUID(rowSOPID);
				satueiBean.SetMPMppsVmNo(rowVMNO);

				// 引当処理

				// 引き当て情報を設定する
				satueiBean = SetMachingData(satueiBean, rowKVP, rowXRAY, rowTIME, rowFilmID, rowFilmCnt, filmDt);

				// ログ出力
				String msg = "MPPSログ_引当て処理正常終了 部位No%s 撮影ﾒﾆｭｰ:%s 撮影ｺｰﾄﾞ:%s MppsUID:%s VMNo:%s";
				msg = String.format(msg, sRowBuiNo, sRowSMenu, sRowSCode, rowSOPID, rowVMNO);
				logger.info(msg);

				// 引き当てが完了したレコードは削除する
				mpDt.remove(i);
				break;
			}
		}
	}

	/// <summary>
	/// 撮影コード引当追加処理
	/// </summary>
	private boolean MppsMachingAddForSatueiCode(ArrayList<ExSatueiBean> satueiBeanList, ExSatueiBean satueiBean, DataTable mpDt, DataTable filmDt) {

		// MPPS情報が無い場合は処理を終える
		if (mpDt == null || (mpDt.getRowCount() <= 0))
		{
			return false;
		}

		// 引き当てチェック
		Integer index = MppsMachingForSatueiCode(satueiBean, mpDt, false);

		// 引き当てできない場合
		if (index == null) {
			return false;
		}

		// 撮影情報複製
		ExSatueiBean satueiBeanAdd = satueiBean.copy();

		// MPPS情報を読み込む
		String rowSOPID		= mpDt.getRows().get(index).get(MPPreformedProtocolCodeTbl.MPPSSOPINSTANCEUID_COLUMN).toString();
		String rowVMNO		= mpDt.getRows().get(index).get(MPPreformedProtocolCodeTbl.VMNO_COLUMN).toString();
		String rowSCode		= mpDt.getRows().get(index).get(MPPreformedProtocolCodeTbl.CODEVALUE_COLUMN).toString();
		String rowFilmID	= mpDt.getRows().get(index).get(MPFilmConsumptionTbl.BAMCFILMSIZEID_COLUMN).toString();
		String rowFilmCnt	= mpDt.getRows().get(index).get(MPFilmConsumptionTbl.BAMCNUMBEROFFILMS_COLUMN).toString();
		String rowKVP		= mpDt.getRows().get(index).get(MPExposureDoseTbl.RDKVP_COLUMN).toString();
		String rowXRAY		= mpDt.getRows().get(index).get(MPExposureDoseTbl.RDXRAYTUBECURRENTINUA_COLUMN).toString();
		String rowTIME		= mpDt.getRows().get(index).get(MPExposureDoseTbl.RDEXPOSURETIME_COLUMN).toString();

		// 引き当てが完了したレコードは削除する
		mpDt.remove(index);

		// 引き当て情報を設定する
		satueiBeanAdd = SetMachingData(satueiBeanAdd, rowKVP, rowXRAY, rowTIME, rowFilmID, rowFilmCnt, filmDt);

		// 撮影情報を読み込む
		String sRowBuiNo  = satueiBeanAdd.GetBuiNo();
		String sRowSMenu  = satueiBeanAdd.GetSatueiMenuName();
		String sRowSCode  = satueiBeanAdd.GetSatueiCode();

		// ログ出力
		String msg = "MPPSログ_引当て処理正常終了 部位No%s 撮影ﾒﾆｭｰ:%s 撮影ｺｰﾄﾞ:%s MppsUID:%s VMNo:%s";
		msg = String.format(msg, sRowBuiNo, sRowSMenu, sRowSCode, rowSOPID, rowVMNO);
		logger.info(msg);

		// 撮影情報 - MPPS情報設定
		satueiBeanAdd.SetMPMppsInstanceUID(rowSOPID);
		satueiBeanAdd.SetMPMppsVmNo(rowVMNO);
		satueiBeanAdd.SetSatueiAddFlag(CommonString.FLG_ON);

		// 撮影情報 - 再撮設定
		satueiBeanAdd.SetReshotFlg(CommonString.RESHOT_FLG_ON);

		// 撮影情報 - シーケンス設定
		String seq = AppCommon.GetSequenceSatuei(satueiBeanList, sRowBuiNo);
		satueiBeanAdd.SetNo(seq);

		// 撮影情報追加
		satueiBeanList.add(satueiBeanAdd);

		return true;
	}

	/// <summary>
	/// 撮影コード引当追加処理（残）
	/// </summary>
	private boolean MppsMachingAddForSatueiCode(ArrayList<ExSatueiBean> satueiBeanList, DataTable mpDt, DataTable filmDt, DataTable crsatueiDt) {

		// MPPS情報が無い場合は処理を終える
		if (mpDt == null || (mpDt.getRowCount() <= 0))
		{
			return true;
		}

		// 部位Noが最大の撮影情報を取得
		ExSatueiBean satueiBean = AppCommon.GetMaxBuiNoExSatueiBean(satueiBeanList);

		for (int i = 0; i < mpDt.getRowCount(); i++) {

			// MPPS情報を読み込む
			String rowSOPID		= mpDt.getRows().get(i).get(MPPreformedProtocolCodeTbl.MPPSSOPINSTANCEUID_COLUMN).toString();
			String rowVMNO		= mpDt.getRows().get(i).get(MPPreformedProtocolCodeTbl.VMNO_COLUMN).toString();
			String rowSCode		= mpDt.getRows().get(i).get(MPPreformedProtocolCodeTbl.CODEVALUE_COLUMN).toString();
			String rowFilmID	= mpDt.getRows().get(i).get(MPFilmConsumptionTbl.BAMCFILMSIZEID_COLUMN).toString();
			String rowFilmCnt	= mpDt.getRows().get(i).get(MPFilmConsumptionTbl.BAMCNUMBEROFFILMS_COLUMN).toString();
			String rowKVP		= mpDt.getRows().get(i).get(MPExposureDoseTbl.RDKVP_COLUMN).toString();
			String rowXRAY		= mpDt.getRows().get(i).get(MPExposureDoseTbl.RDXRAYTUBECURRENTINUA_COLUMN).toString();
			String rowTIME		= mpDt.getRows().get(i).get(MPExposureDoseTbl.RDEXPOSURETIME_COLUMN).toString();

			// CR撮影メニューマスタ対象レコード取得
			DataRow crsatueiRow = getSatueiRow(crsatueiDt, rowSCode, RisCrSatueiMenuMaster.SATUEI_CODE01_COLUMN);

			// 取得できない場合
			if (crsatueiRow == null) {
				return false;
			}

			// 引き当てが完了したレコードは削除する
			mpDt.remove(i);
			i--;

			// 撮影情報Bean作成
			ExSatueiBean satueiBeanAdd = new ExSatueiBean();

			satueiBeanAdd.SetRisID(satueiBean.GetRisID());
			satueiBeanAdd.SetBuiNo(satueiBean.GetBuiNo());
			satueiBeanAdd.SetNo(satueiBean.GetNo());
			satueiBeanAdd.SetKensaKikiID(satueiBean.GetKensaKikiID());
			satueiBeanAdd.SetSatueiStatus(CommonString.SATUEISTATUS_SUMI);
			satueiBeanAdd.SetSatueiMenuID(crsatueiRow.get(MasterUtil.RIS_SATUEIMENU_ID).toString());
			satueiBeanAdd.SetSatueiMenuName(crsatueiRow.get(MasterUtil.RIS_SATUEIMENU_NAME).toString());
			satueiBeanAdd.SetSatueiMenuNameKana(crsatueiRow.get(MasterUtil.RIS_SATUEIMENU_NAME_KANA).toString());
			satueiBeanAdd.SetSatueiCode(crsatueiRow.get(MasterUtil.RIS_SATUEI_CODE + "01").toString());
			satueiBeanAdd.SetFilmID(crsatueiRow.get(MasterUtil.RIS_FILM_ID).toString());
			satueiBeanAdd.SetPartition(crsatueiRow.get(MasterUtil.RIS_PARTITION).toString());
			satueiBeanAdd.SetUsed(crsatueiRow.get(MasterUtil.RIS_USED).toString());
			satueiBeanAdd.SetExamDataToBean2(crsatueiRow);	//ExamData01~20
			satueiBeanAdd.SetMppsDataToBean(crsatueiRow);	//Mpps情報

			// 引き当て情報を設定する
			satueiBeanAdd = SetMachingData(satueiBeanAdd, rowKVP, rowXRAY, rowTIME, rowFilmID, rowFilmCnt, filmDt);

			// 撮影情報を読み込む
			String sRowBuiNo  = satueiBeanAdd.GetBuiNo();
			String sRowSMenu  = satueiBeanAdd.GetSatueiMenuName();
			String sRowSCode  = satueiBeanAdd.GetSatueiCode();

			// ログ出力
			String msg = "MPPSログ_引当て処理正常終了 部位No%s 撮影ﾒﾆｭｰ:%s 撮影ｺｰﾄﾞ:%s MppsUID:%s VMNo:%s";
			msg = String.format(msg, sRowBuiNo, sRowSMenu, sRowSCode, rowSOPID, rowVMNO);
			logger.info(msg);

			// 撮影情報 - MPPS情報設定
			satueiBeanAdd.SetMPMppsInstanceUID(rowSOPID);
			satueiBeanAdd.SetMPMppsVmNo(rowVMNO);
			satueiBeanAdd.SetSatueiAddFlag(CommonString.FLG_ON);

			// 撮影情報 - 再撮設定
			satueiBeanAdd.SetReshotFlg(CommonString.RESHOT_FLG_ON);

			// 撮影情報 - シーケンス設定
			String seq = AppCommon.GetSequenceSatuei(satueiBeanList, sRowBuiNo);
			satueiBeanAdd.SetNo(seq);

			// 撮影情報追加
			satueiBeanList.add(satueiBeanAdd);

		}
		return true;
	}

	/// <summary>
	/// 撮影ｺｰﾄﾞ判定
	/// </summary>
	/// <param name="satueiCode">撮影ｺｰﾄﾞ:撮影</param>
	/// <param name="mppsSatueiCode">撮影ｺｰﾄﾞ:MPPS</param>
	/// <param name="sSatueiCode">(戻)撮影ｺｰﾄﾞ:撮影</param>
	/// <param name="mSatueiCode">(戻)撮影ｺｰﾄﾞ:MPPS</param>
	/// <returns></returns>
	private boolean IsMppsMatchingSatueiCode(String satueiCode, String mppsSatueiCode, String sSatueiCode, String mSatueiCode)
	{
		boolean retFlg = false;
		try
		{
			String msg = "";
			boolean   astariskFlg = false;
			String astariskStr = CommonString.FLG_OFF_STR;

			// *有無判定
			if (satueiCode.indexOf("*") != -1)
			{
				astariskFlg = true;
				astariskStr = CommonString.FLG_ON_STR;
			}

			// *により撮影ｺｰﾄﾞを準備する
			if (astariskFlg)
			{
				sSatueiCode = satueiCode.substring(0, satueiCode.indexOf("*"));
				mSatueiCode = mppsSatueiCode.substring(0, mppsSatueiCode.length()-1);
			}
			else
			{
				sSatueiCode = satueiCode;
				mSatueiCode = mppsSatueiCode;
			}

			// 撮影ｺｰﾄﾞ判定
			if (!sSatueiCode.equals(mSatueiCode))
			{
				// 撮影ｺｰﾄﾞ不一致
				// ログ出力
				msg = "MPPSログ_撮影ｺｰﾄﾞが不一致のためスキップします。撮影情報-撮影ｺｰﾄﾞ:%s MPPS情報-撮影ｺｰﾄﾞ:%s *=%s";
				msg = String.format(msg, sSatueiCode, mSatueiCode, astariskStr);
				logger.info(msg);
			}
			else
			{
				retFlg = true;
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retFlg;
	}

	/// <summary>
	/// 引き当て情報を設定する
	/// </summary>
	/// <param name="row">撮影情報</param>
	/// <param name="rowKVP">KVP</param>
	/// <param name="rowXRAY">XRAY</param>
	/// <param name="rowTIME">TIME</param>
	/// <param name="rowFilmID">フィルムID</param>
	/// <param name="rowFilmCnt">枚数</param>
	/// <returns></returns>
	private ExSatueiBean SetMachingData(ExSatueiBean satueiBean, String rowKVP, String rowXRAY, String rowTIME, String rowFilmID, String rowFilmCnt, DataTable filmDt)
	{
		try
		{
			// 各情報の設定

			// ExamData01～03
			if (rowKVP.length() > 0)
			{
				satueiBean.SetExamData01(rowKVP);
			}

			if (rowXRAY.length() > 0)
			{
				satueiBean.SetExamData02(rowXRAY);
			}

			if (rowTIME.length() > 0)
			{
				satueiBean.SetExamData03(rowTIME);
			}

			MasterUtil mUtil = new MasterUtil();

			DataRow filmRow = mUtil.FindDataRow(filmDt, MasterUtil.RIS_MODALITY_FILM_CODE, rowFilmID);
			// フィルムIDがマスタに存在する場合のみ、引当を行う
			if (filmRow != null)
			{
				satueiBean.SetFilmID(filmRow.get(MasterUtil.RIS_FILM_ID).toString());
			}

			if (rowFilmCnt.length() > 0)
			{
				satueiBean.SetUsed(rowFilmCnt);
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return satueiBean;
	}

	/// <summary>
	/// フィルム自動計算
	/// </summary>
	private void FilmAutoCalc(ArrayList<ExSatueiBean> satueiBeanList, ArrayList<ExFilmBean> filmList) throws Exception
	{
		try
		{
			ArrayList keyList  = new ArrayList();	//キーリスト
			ArrayList valList  = new ArrayList();	//値リスト
			String    filmKey  = "";				//部位NO|フィルムID|分割数
			String    markChar = "|";
			String    risId    = "";

			// 撮影一覧を元にフィルム再計算用のListを作成する

			for (int i = 0; i < satueiBeanList.size(); i++)
			{
				ExSatueiBean satueiBean = satueiBeanList.get(i);

				risId = satueiBean.GetRisID();

				String status		= satueiBean.GetSatueiStatus();
				String buiNo		= satueiBean.GetBuiNo();
				String filmID		= satueiBean.GetFilmID();
				int	   partition	= AppCommon.StrToInt0(satueiBean.GetPartition());
				int    used			= AppCommon.StrToInt0(satueiBean.GetUsed());
				int    loss			= 0;
				String reShot		= satueiBean.GetReshotFlg();
				if (CommonString.RESHOT_FLG_ON.equals(reShot))
				{
					//再撮影の場合usedをlossへ設定する
					loss = used;
					used = 0;
				}

				//「済」のみ処理対象
				if (CommonString.SATUEISTATUS_SUMI.equals(status))
				{
					//Hashキーを作成
					filmKey = buiNo + markChar + filmID + markChar + partition;

					//値リストを作成(Partition, Used, Loss)
					ArrayList valueList = new ArrayList();
					valueList.add(partition);
					valueList.add(used);
					valueList.add(loss);

					if (!keyList.contains(filmKey))
					{
						//キーが一致しない場合は追加
						keyList.add(filmKey);
						valList.add(valueList);
					}
					else
					{
						//キーが一致した場合は加算する
						int index = keyList.indexOf(filmKey);
						ArrayList oldValues = (ArrayList)valList.get(index);
						if (oldValues != null && oldValues.size() == 3)
						{
							oldValues.set(1, AppCommon.StrToInt0(oldValues.get(1).toString()) + used);		//枚数
							oldValues.set(2, AppCommon.StrToInt0(oldValues.get(2).toString()) + loss);		//ロス
						}
					}
				}
			}

			for (int i = 0; i < keyList.size(); i++)
			{
				String[] keyStr = keyList.get(i).toString().split("\\" + markChar);
				ArrayList values = (ArrayList)valList.get(i);

				if (keyStr.length == 3 && values.size() == 3)
				{
					//枚数orロスが３桁以上になった場合
					if (values.get(1).toString().length() > 2 ||
						values.get(2).toString().length() > 2)
					{
						throw new Exception("枚数またはロスが2桁を超えました。");
					}

					//枚数、ロスが0の場合
					if ("0".equals(values.get(1).toString()) &&
							"0".equals(values.get(2).toString()))
					{
						//行を追加しない
						continue;
					}

					// 設定値の準備
					String buiNo = keyStr[0];
					String seq   = "";

					seq = AppCommon.GetSequenceFilm(filmList, buiNo);

					// フィルム情報の設定
					ExFilmBean filmBean = new ExFilmBean();

					filmBean.SetRisID(risId);
					filmBean.SetBuiNo(buiNo);
					filmBean.SetNo(seq);
					filmBean.SetFilmID(keyStr[1]);
					filmBean.SetPartition(values.get(0).toString());
					filmBean.SetUsed(values.get(1).toString());
					filmBean.SetLoss(values.get(2).toString());

					//
					filmList.add(filmBean);
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
			throw new Exception("集計エラーが発生しました。\n" + ex.getMessage());
		}
	}

	// add sta 201806_ポータブルRIS検査系種別対応
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
	// add end 201806_ポータブルRIS検査系種別対応

	//2020.09.14 Add Nishihara@COSMO start 複数オーダーの一括処理機能追加 一括受付、一括MWM登録対応
	/// <summary>
	/// MWM登録処理
	/// </summary>
	private boolean mwmRegister(String ris_id, String kensakiki_id, Connection risconn,
											Config config, HttpServletRequest request, StatusUpdateDto dto) throws Exception
	{
		try
		{
			// MWM情報の登録
			// 情報の準備
    		// オーダ情報取得
            OrderInfoBean orderBean = null;
    		orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(ris_id, risconn);
			DataTable setOrderDt = DataBase.getOrderList(ris_id, risconn);//Configuration.GetInstance().GetCoreController().GetRisOrderList(oParam, risconn);
			ArrayList setRisIDList = new ArrayList();
			setRisIDList.add(ris_id);
			String setGisiID = (String) SessionControler.getValue(request, SessionControler.LOGINUSER);
			String setGisiName = (String) SessionControler.getValue(request, SessionControler.LOGINUSERNAME);

			// MWM情報の登録
			MWMParameter mwmParam = new MWMParameter();
			mwmParam.SetMWMMode(CommonString.MWM_MODE_PORTABLE);
			mwmParam.SetOrderDt(setOrderDt);
			mwmParam.SetRisIDList(setRisIDList);
			mwmParam.SetAllFlg(false);
			mwmParam.SetGisiID(setGisiID);
			mwmParam.SetGisiName(setGisiName);
			mwmParam.SetKikiID(kensakiki_id);
			mwmParam.SetMstKensatype(Configuration.GetInstance().GetRRisKensaTypeMaster(risconn));
			mwmParam.SetMstKensakiki(Configuration.GetInstance().GetRRisKensaKikiMaster(risconn));
			mwmParam.SetMstBui(Configuration.GetInstance().GetRRisBuiMaster(risconn));
			mwmParam.SetMstHoukou(Configuration.GetInstance().GetRRisHoukouMaster(risconn));
			mwmParam.SetMstKHouhou(Configuration.GetInstance().GetRRisKensaHouhouMaster(risconn));
			mwmParam.SetMstDoctor(Configuration.GetInstance().GetRRisSectionDoctorMaster(risconn));
			mwmParam.SetMstSection(Configuration.GetInstance().GetRRisSectionMaster(risconn));
			mwmParam.SetOpeKbn(CommonString.OPE_KBN_PHOTO);

			// MWMソケット通信タイムアウト設定
			Configuration.GetInstance().setMwmtimeout(config.getMwmtimeout());
			// かなローマ変換テキストファイル設定
			Configuration.GetInstance().setKanaromatext(config.getKanaromatext());

			try {
				// MWM登録
				AppCommon.RegisterMWM(mwmParam, risconn);
			} catch (Exception e) {
				// WorkListInfoの削除を行う
				String risID  = orderBean.GetRisID();
				String accNo  = orderBean.GetAccessionNo();
				String kikiID = Util.toNullString(kensakiki_id);
				Configuration.GetInstance().GetCoreController().DeleteWorkListByAccesionNo(risID, accNo, kikiID, CommonString.MWMDELETE_INDEX_0, risconn);

				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_WARN);
				dto.setMsg(e.getMessage());
				return false;
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
	//2020.09.14 Add Nishihara@COSMO end 複数オーダーの一括処理機能追加 一括受付、一括MWM登録対応
}
