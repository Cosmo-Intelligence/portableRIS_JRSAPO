package ris.portable.rest.resources;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.portable.common.Config;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.data.UserInfo;
import ris.portable.database.DataBase;
import ris.portable.model.dto.OrderListDto;
import ris.portable.parameter.ParameterReplacerOrderInfo;
import ris.portable.parameter.ParameterReplacerUserInfo;
import ris.portable.util.DataTable;
import ris.portable.util.ParameterReplacer;
import ris.portable.util.Util;

@Path("/orderlist")
public class OrderListResource {

	private static Log logger = LogFactory.getLog(OrderListResource.class);

	// デバッグURL
	// http://localhost:8080/PortableRIS/rest/orderlist?kensa_date=20101020&kanja_id=9999200&uketuke_tantou_id=999999&byoutou_id=ZZ&kensatype_id=Q&status=0

	@GET
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@FormParam("kensa_date") String kensa_date,
			@FormParam("kanja_id") String kanja_id,
			@FormParam("uketuke_tantou_id") String uketuke_tantou_id,
			@FormParam("byoutou_id") String byoutou_id,
			@FormParam("kensatype_id") String kensatype_id,
			@FormParam("nyugaikbn") String nyugaikbn,
			@FormParam("portablekbn") String portablekbn,
			@FormParam("status") String status,
			@FormParam("sort") String sort,
			@Context HttpServletRequest request) throws Exception {

        return doPost(kensa_date, kanja_id, uketuke_tantou_id, byoutou_id, kensatype_id, nyugaikbn, portablekbn, status, sort, request);
	}

	@POST
	@Produces("application/json;charset=UTF-8")
	public Response doPost(
			@FormParam("kensa_date") String kensa_date,
			@FormParam("kanja_id") String kanja_id,
			@FormParam("uketuke_tantou_id") String uketuke_tantou_id,
			@FormParam("byoutou_id") String byoutou_id,
			@FormParam("kensatype_id") String kensatype_id,
			@FormParam("nyugaikbn") String nyugaikbn,
			@FormParam("portablekbn") String portablekbn,
			@FormParam("status") String status,
			@FormParam("sort") String sort,
			@Context HttpServletRequest request) throws Exception {

		logger.debug("☆★☆オーダ一覧情報取得リクエスト---開始");

		// オーダ一覧情報
		OrderListDto order = new OrderListDto();

		// 実行
		Execute(kensa_date,
				kanja_id,
				uketuke_tantou_id,
				byoutou_id,
				kensatype_id,
				nyugaikbn,
				portablekbn,
				status,
				sort,
				request,
				order);

		// JSON変換
		String json = Util.getJson(order);

		logger.debug("☆★☆オーダ一覧情報取得リクエスト---JSON:" + json);

		logger.debug("☆★☆オーダ一覧情報取得リクエスト---終了");

        return Response.ok().entity(json).build();
	}

	/**
	 * 処理実行
	 * @param kensa_date        :検査日
	 * @param kanja_id          :患者ID
	 * @param uketuke_tantou_id :受付担当者ID
	 * @param byoutou_id        :病棟ID
	 * @param kensatype_id      :検査種別ID
	 * @param nyugaikbn         :入外区分
	 * @param portablekbn       :ポータブル区分
	 * @param status            :検査ステータス
	 * @param sort              :ソート
	 * @param request           :httpリクエスト
	 * @param dto               :オーダ一覧情報
	 * @return
	 */
	private boolean Execute(
			String kensa_date,
			String kanja_id,
			String uketuke_tantou_id,
			String byoutou_id,
			String kensatype_id,
			String nyugaikbn,
			String portablekbn,
			String status,
			String sort,
			HttpServletRequest request,
			OrderListDto dto) throws Exception {

		try{
			// 設定ファイル情報取得
			Config config = (Config)SessionControler.getValue(request, SessionControler.SYSTEMCONFIG);
			// ユーザ情報を取得する
			UserInfo userInfo = new UserInfo();
			// ユーザID
			userInfo.setUserId((String)SessionControler.getValue(request, SessionControler.LOGINUSER));
			// パスワード
			userInfo.setPassword((String)SessionControler.getValue(request, SessionControler.LOGIN_PASSWORD));

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
				String convstatus = config.convStatusServer(status);

				// オーダ一覧情報
				DataTable orderDt = null;
				// オーダ一覧情報取得
				orderDt = DataBase.getOrderList(
								kensa_date,
								kanja_id,
								uketuke_tantou_id,
								byoutou_id,
								kensatype_id,
								nyugaikbn,
								portablekbn,
								status,
								sort,
								convstatus.split(","), risconn);

				// オーダ一覧情報取得失敗
				if (orderDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("該当するデータは１件もありませんでした。");
					return false;
				}

				// オーダ一覧情報設定
				dto.setOrder_array(this.getOrderList(config, orderDt, userInfo));

				if (dto.getOrder_array().size() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("該当するデータは１件もありませんでした。");
					return false;
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

	/**
	 * オーダ一覧取得
	 * @param config :設定ファイル情報
	 * @param dt     :オーダ一覧情報
	 * @param userInfo :ユーザ情報
	 * @return
	 */
	private ArrayList<OrderListDto.Order> getOrderList(Config config, DataTable dt, UserInfo userInfo){
		// ViewAirを起動するためのURL設定を取得
		String viewAirUrl = config.getViewAirUrl();
		// パラメータを置換するクラスで使用するユーザ情報パラメータクラスを生成
		ParameterReplacerUserInfo parameterReplacerUserInfo = new ParameterReplacerUserInfo(userInfo);
		// オーダ一覧情報
		ArrayList<OrderListDto.Order> orderList = new ArrayList<OrderListDto.Order>();

		for (int i = 0; i < dt.getRowCount(); i++) {
			OrderListDto.Order order = new OrderListDto.Order();

			// オーダ情報設定
			order.setRis_id(dt.getRows().get(i).get("RIS_ID").toString());
			order.setKanja_id(dt.getRows().get(i).get("KANJA_ID").toString());
			order.setStatus(config.convStatusClient(dt.getRows().get(i).get("STATUS").toString()));
			order.setSex(dt.getRows().get(i).get("SEX").toString());
			order.setKanjisimei(dt.getRows().get(i).get("KANJISIMEI").toString());
			order.setKanasimei(dt.getRows().get(i).get("KANASIMEI").toString());
			order.setByoutou(dt.getRows().get(i).get("BYOUTOU").toString());
			order.setByousitu(dt.getRows().get(i).get("BYOUSITU").toString());
			order.setAge(dt.getRows().get(i).get("AGE").toString());
			order.setIrai_section(dt.getRows().get(i).get("IRAI_SECTION").toString());
			order.setIrai_doctor_name(dt.getRows().get(i).get("IRAI_DOCTOR_NAME").toString());
			order.setRenraku_memo(dt.getRows().get(i).get("RENRAKU_MEMO").toString());

			// 2020.03.05 Nishihara@COSMO Start 町田市民病院PortableRIS改造対応
			order.setTransition_flg(config.getTransitionFlg());
			// 2020.03.05 Nishihara@COSMO End 町田市民病院PortableRIS改造対応
			// パラメータを置換するクラスで使用するオーダ情報パラメータクラスを生成
			ParameterReplacerOrderInfo parameterReplacerOrderInfo = new ParameterReplacerOrderInfo();
			// アクセッション番号
			parameterReplacerOrderInfo.setAccessionNo(dt.getRows().get(i).get("ACNO").toString());
			// パラメータを置換したViewAirのURLをセット
			order.setViewAirUrl(getViewAirUrl(viewAirUrl, parameterReplacerUserInfo, parameterReplacerOrderInfo));
			// 検査ステータス変換確認
			if (order.getStatus() == null) {
				logger.warn("ステータス変換に失敗しました。設定ファイルを確認してください。検査ステータス：" + dt.getRows().get(i).get("STATUS").toString());
			} else {
				orderList.add(order);
			}
		}

		return orderList;
	}

	/**
	 * ViewAirを起動するためのURL設定に含まれるパラメータを置換したViewAirのURLを取得する
	 * @param viewAirUrlConfig ViewAirを起動するためのURL設定
	 * @param parameterReplacerUserInfo パラメータを置換するクラスで使用するユーザ情報パラメータクラス
	 * @return パラメータを置換したViewAirのURL
	 */
	private String getViewAirUrl(String viewAirUrlConfig, ParameterReplacerUserInfo parameterReplacerUserInfo, ParameterReplacerOrderInfo parameterReplacerOrderInfo) {
		if (viewAirUrlConfig == null) {
			// ViewAirを起動するためのURL設定がnullである場合
			return null;
		}
		if (viewAirUrlConfig == "") {
			// ViewAirを起動するためのURL設定が空文字である場合
			return "";
		}
		// パラメータを置換するクラスでパラメータを置換する
		ParameterReplacer parameterReplacer = new ParameterReplacer(viewAirUrlConfig, parameterReplacerUserInfo, parameterReplacerOrderInfo);
		// パラメータを置換した文字列を取得する
		String viewAirUrl = parameterReplacer.getReplacedString();
		if (parameterReplacer.ExistsUndefinedParameter()) {
			// 未定義パラメータが存在する場合
			// WARNログを出力する
			String message = String.format("[%s]は定義されていなパラメータです。", parameterReplacer.getUndefinedParameter());
			logger.warn(message);
		}
		return viewAirUrl;
	}
}
