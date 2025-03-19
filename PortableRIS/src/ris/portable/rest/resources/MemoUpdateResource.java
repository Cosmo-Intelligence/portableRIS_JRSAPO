package ris.portable.rest.resources;

import java.sql.Connection;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.bean.AccessInfoBean;
import ris.portable.common.Application;
import ris.portable.common.Config;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.database.DataBase;
import ris.portable.model.dto.MemoUpdateDto;
import ris.portable.util.Util;

@Path("/memo")
public class MemoUpdateResource {

	private static Log logger = LogFactory.getLog(MemoUpdateResource.class);

	// デバッグURL
	// http://localhost:8080/PortableRIS/rest/memo?ris_id=2010102010002030&renraku_memo=aaa

	@GET
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@QueryParam("ris_id") String ris_id,
			@QueryParam("renraku_memo") String renraku_memo,
			@Context HttpServletRequest request) throws Exception {

        return doPost(ris_id, renraku_memo, request);
	}

	@POST
	@Produces("application/json;charset=UTF-8")
	public Response doPost(
			@FormParam("ris_id") String ris_id,
			@FormParam("renraku_memo") String renraku_memo,
			@Context HttpServletRequest request) throws Exception {

		logger.debug("☆★☆連絡メモ更新リクエスト---開始");

		// 連絡メモ更新情報
		MemoUpdateDto memo = new MemoUpdateDto();

		// 実行
		Execute(ris_id, renraku_memo, request, memo);

		// JSON変換
		String json = Util.getJson(memo);

		logger.debug("☆★☆連絡メモ更新リクエスト---JSON:" + json);

		logger.debug("☆★☆連絡メモ更新リクエスト---終了");

        return Response.ok().entity(json).build();
	}

	/**
	 * 処理実行
	 * @param ris_id       :RIS識別ID
	 * @param renraku_memo :連絡メモ
	 * @param request      :httpリクエスト
	 * @param dto          :連絡メモ更新情報
	 * @return
	 */
	private boolean Execute(
			String ris_id,
			String renraku_memo,
			HttpServletRequest request,
			MemoUpdateDto dto) throws Exception {

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

				// 2019.08.06 Mod Cosmo Start 排他対応
                // オーダー情報をチェックアウトする
				AccessInfoBean accessBean = new AccessInfoBean();
 				String checkoutId = null;
				accessBean.SetID(ris_id);
				accessBean.SetAppID(config.getAppid());
				accessBean.SetIpAddress("");
				if(config.getExclusive()){
					checkoutId = "OK";
				}else{
	 				checkoutId = Configuration.GetInstance().GetCoreController().CheckoutRisOrder(accessBean, risconn);
				}
				// 2019.08.06 Mod Cosmo End 排他対応


				// チェックアウトできなかった場合
				if (StringUtils.isEmpty(checkoutId)) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("指定のオーダは、他の端末で編集中です。");
					return false;
				}

				// コミット
				risconn.commit();

				// 連絡メモ更新
				boolean result = DataBase.memoUpdate(ris_id, renraku_memo, risconn);
				// 連絡メモ更新失敗時
				if (!result) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("連絡メモが更新できませんでした。");
					return false;
				}

				// チェックアウト解除
				//result = Configuration.GetInstance().GetCoreController().UncheckoutRisData(checkoutId, risconn);
				if(!config.getExclusive()){
					result = Configuration.GetInstance().GetCoreController().UncheckoutRisData(checkoutId, risconn);
				}else{
					result = true;
				}

				// チェックアウト解除失敗
				if (!result) {
					// ロールバック
					risconn.rollback();

					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("チェックアウト解除ができませんでした。");
					return false;
				}

				// コミット
				risconn.commit();
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

}
