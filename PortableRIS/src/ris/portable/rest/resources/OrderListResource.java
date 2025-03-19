package ris.portable.rest.resources;

import java.sql.Connection;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.portable.common.Config;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.database.DataBase;
import ris.portable.model.dto.OrderListDto;
import ris.portable.util.DataTable;
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
				dto.setOrder_array(this.getOrderList(config, orderDt));

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
	 * @return
	 */
	private ArrayList<OrderListDto.Order> getOrderList(Config config, DataTable dt){

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
			// 検査ステータス変換確認
			if (order.getStatus() == null) {
				logger.warn("ステータス変換に失敗しました。設定ファイルを確認してください。検査ステータス：" + dt.getRows().get(i).get("STATUS").toString());
			} else {
				orderList.add(order);
			}
		}

		return orderList;
	}
}
