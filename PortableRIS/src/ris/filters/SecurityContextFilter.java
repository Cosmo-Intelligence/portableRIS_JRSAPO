/**
 * 認証情報のチェック用JAX-RSフィルター（Apache CXF独自形式）。
 * セッションに認証済み情報のないリクエストにはエラーレスポンスを返却する。
 */
package ris.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.model.dto.BaseDto;
import ris.portable.util.Util;

@Provider
public class SecurityContextFilter implements ContainerRequestFilter {

	private Log logger = LogFactory.getLog(SecurityContextFilter.class);

	@Context
	private HttpServletRequest request;
	@Context
	private UriInfo ui;
	
	/**
	 * フィルターInterfaceのメソッド実装
	 * @param message JAX-RSが受け取ったリクエストのメッセージ情報
	 * @param classResourceInfo JAX-RSが受け取ったリクエストに対するリソース情報
	 * @return 処理結果のレスポンス
	 */
	public void filter(ContainerRequestContext context) {
		// ログイン用のリクエストはチェック対象外

		// 2021.09.01 Add Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無の設定値取得用のリクエストもチェック対象から外す
		// if (ui.getAbsolutePath().toString().endsWith("login")) {
		if (ui.getAbsolutePath().toString().endsWith("login") || ui.getAbsolutePath().toString().endsWith("loginsystem")) {
			return;
		}
		// 2021.09.01 mod Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無の設定値取得用のリクエストもチェック対象から外す

		// 認証済みでなければエラーレスポンスを返却
		if (!SessionControler.isLogin(request)) {
			logger.debug("Not Logged In!!");
			BaseDto dto = new BaseDto();
			dto.setResult(Const.RESULT_NG);
			// セッションタイムアウトエラーを設定
			dto.setErrlevel(Const.ERRLEVEL_NO_SESSION);
			dto.setMsg("ログイン認証を行って下さい。");
			createFaultResponse(context, dto);
		}
	}

	/**
	 * 認証なしエラーの場合にクライアントへ返却するレスポンスを作成
	 * @param loginDto レスポンスにJSON形式で含めるログインデータ
	 * @return 認証なしエラー時のレスポンス
	 */
	private void createFaultResponse(ContainerRequestContext context, BaseDto dto) {
		// JSON変換
		String json = Util.getJson(dto);

		// HTTP 401 認証エラーを返却する場合以下のコメント部を使用すること
		// context.abortWith(Response.status(401).entity(json).build());
		context.abortWith(Response.ok().entity(json).build());
	}
}
