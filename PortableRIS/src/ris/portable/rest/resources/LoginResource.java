package ris.portable.rest.resources;

import java.sql.Connection;

import jakarta.servlet.ServletContext;
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
import ris.portable.common.Application;
import ris.portable.common.Config;
import ris.portable.common.Const;
import ris.portable.common.SessionControler;
import ris.portable.database.DataBase;
import ris.portable.model.dto.LoginDto;
import ris.portable.model.dto.StatusUpdateDto;
import ris.portable.util.DataTable;
import ris.portable.util.Util;

@Path("/login")
public class LoginResource {

	private static Log logger = LogFactory.getLog(LoginResource.class);


	@Context
	private ServletContext context;

	@GET
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@QueryParam("userid") String userid,
			@QueryParam("password") String password,
			// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
			@QueryParam("mode") String mode,
			// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
			@Context HttpServletRequest request) throws Exception {

        return doPost(userid, password, mode, request);
	}

	@POST
	@Produces("application/json;charset=UTF-8")
	public Response doPost(
			@FormParam("userid") String userid,
			@FormParam("password") String password,
			// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
			@FormParam("mode") String mode,
			// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
			@Context HttpServletRequest request) throws Exception {

		logger.debug("☆★☆ログイン認証リクエスト---開始");

		// ログイン情報
		LoginDto login = new LoginDto();

		// 実行
		Execute(userid, password, mode, request, login);

		// JSON変換
		String json = Util.getJson(login);

		logger.debug("☆★☆ログイン認証リクエスト---JSON:" + json);

		logger.debug("☆★☆ログイン認証リクエスト---終了");

		return Response.ok().entity(json).build();
	}

	/**
	 * 処理実行
	 * @param userid   :ユーザID
	 * @param password :パスワード
     * @param mode     :モード
	 * @param request  :httpリクエスト
	 * @param dto      :ログイン情報
	 * @return
	 */
	private boolean Execute(
			String userid,
			String password,
			// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
			String mode,
			// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
			HttpServletRequest request,
			LoginDto dto) throws Exception {

		try{
			// 参照モードの場合
			if (!StringUtils.isEmpty(mode) && Const.MODE_READ.equals(mode)) {

				// 2018.12.12 Add S.Ichinose@COSMO Start 呼出参照機能対応
				// 参照モードユーザ情報設定
				dto.setUserid("");
				dto.setUsername("");
				dto.setIsReadMode(true);
				// 2018.12.12 Add S.Ichinose@COSMO End   呼出参照機能対応

			} else {

				// ユーザIDチェック
				if (StringUtils.isEmpty(userid)) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("ユーザIDが入力されていません。");
					return false;
				}

				// パスワードチェック
				if (StringUtils.isEmpty(password)) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("パスワードが入力されていません。");
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

				// ログインユーザ情報
				DataTable userDt = null;

				try {
					// ログインユーザ情報取得
					userDt = DataBase.getUserManage(userid, password, risconn);
				} finally {
					DataBase.closeConnection(risconn);
				}

				// ログインユーザ認証失敗
				if (userDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("ユーザIDまたはパスワードが間違っています。");
					return false;
				}

			// ユーザ情報設定
				dto.setUserid(userDt.getRows().get(0).get("USERID").toString());
				dto.setUsername(userDt.getRows().get(0).get("USERNAME").toString());
			}

			// ログインセッション保持
			// 2019.01.04 Mod S.Ichinose@COSMO Start 呼出参照機能対応
			this.doLogin(dto, request);
			// 2019.01.04 Mod S.Ichinose@COSMO End   呼出参照機能対応

			// 2020.01.30 Mod Cosmo Start 排他IP変更対応
			// 2019.08.07 Add Cosmo Start 排他対応
			//ip設定
			//Configuration.GetInstance().SetIPAddress(request.getRemoteAddr());
			Config config = (Config)SessionControler.getValue(request, SessionControler.SYSTEMCONFIG);
			//実IP使用フラグがtrueの場合は実IPを利用する。そうでない場合は端末名を使用する。
			if(config.getUsePIP()){
				Configuration.GetInstance().SetIPAddress(request.getRemoteAddr());
			}else{
				Configuration.GetInstance().SetIPAddress(dto.getUserid()  + "_" + config.getAppid());
			}
			// 2019.08.07 Add Cosmo End 排他対応
			// 2020.01.30 Mod Cosmo END 排他IP変更対応
			// 2019.10.03 Add Cosmo Start 排他ロック対応
			//初ログイン時に自身のIPでポータブルから登録された場合はそのレコードを削除する。
			// 設定ファイル情報取得
			try {
				// 2020.01.30 Del Cosmo END 排他IP変更対応
				//Config config = (Config)SessionControler.getValue(request, SessionControler.SYSTEMCONFIG);
				// 2020.01.30 Del Cosmo END 排他IP変更対応
				if (!StringUtils.isEmpty(mode) && Const.MODE_DEL_LOCK.equals(mode) && config.getExclusive()) {

					// コネクション取得
					Connection risconn = DataBase.getRisConnection();

					// コネクション取得判定
					if (risconn == null) {
						dto.setResult(Const.RESULT_NG);
						dto.setErrlevel(Const.ERRLEVEL_ERROR);
						dto.setMsg("データベースへ接続できませんでした。");
						return false;
					}
					StatusUpdateDto stDto = new StatusUpdateDto();
					DataTable befor_pulldowndata = null;

					try {
						// アプリケーションクラス
						Application app = new Application();
		                // 管理クラス初期化
		                app.InitConfiguration(risconn, request);
						//排他ロック解除排他ロック解除で失敗しても全てにぎりつぶす。
						Configuration.GetInstance().GetCoreController().unCheckOutLoginLockExclusive(stDto, Configuration.GetInstance().GetIPAddress(), config.getAppid(), risconn,config.getExclusive());

						// 2020.08.27 Add Nishihara@COSMO start MWM対象装置の制御対応(検査室・検査機器・入外区分・検査種別)
						//操作端末名(EXAMOPERATIONHISTORY.OPERATIONTERMINAL)を取得
						String termName = Configuration.GetInstance().GetTerminalInfoBean().GetTerminalName();
						//上記のip設定でセットしたIPアドレスを取得
						String ipaddress = request.getRemoteAddr();

						// ログインした端末の検査室、検査機器のプルダウンの保持されているデータがあれば取得
						befor_pulldowndata = DataBase.getPortableRIS_TerminalInfo(termName, ipaddress, risconn);
						// 2020.08.27 Add Nishihara@COSMO end (検査室・検査機器・入外区分・検査種別)

					} finally {
						DataBase.closeConnection(risconn);
					}

					// 2020.08.27 Add Nishihara@COSMO start MWM対象装置の制御対応(検査室・検査機器・入外区分・検査種別)
					//検査室・検査機器の前回選択したプルダウンの情報保持
					if(befor_pulldowndata.getRowCount() > 0) {
						dto.setKensasituid_pulldown(befor_pulldowndata.getRows().get(0).get("KENSASITU_ID").toString());
						dto.setKensakikiid_pulldown(befor_pulldowndata.getRows().get(0).get("KENSAKIKI_ID").toString());
						dto.setKanjanyugai_pulldown(befor_pulldowndata.getRows().get(0).get("KANJA_NYUGAI").toString());
						dto.setKensatypeid_pulldown(befor_pulldowndata.getRows().get(0).get("KENSATYPE_ID").toString());
					}else {
						dto.setKensasituid_pulldown("");
						dto.setKensakikiid_pulldown("");
						dto.setKanjanyugai_pulldown("");
						dto.setKensatypeid_pulldown("");
					}
					// 2020.08.27 Add Nishihara@COSMO end MWM対象装置の制御対応(検査室・検査機器・入外区分・検査種別)
				}
			}catch(Exception e){
				//握りつぶす
				logger.error(e.toString(), e);
			}
			// 2019.10.03 Add Cosmo End 排他ロック対応
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
	 * ログインセッション保持
	 * @param dto
	 * @param request
	 * @return
	 */
	private boolean doLogin(
			// 2019.01.04 Mod S.Ichinose@COSMO Start 呼出参照機能対応
			LoginDto dto,
			// 2019.01.04 Mod S.Ichinose@COSMO End   呼出参照機能対応
			HttpServletRequest request) throws Exception {

		// セッションの破棄
		SessionControler.clearSession(request);

		// セッション作成
		SessionControler.createSession(request);

		// システム設定取り込み
		Config config = Config.getConfig(context, dto);
		SessionControler.setValue(request, SessionControler.SYSTEMCONFIG, config);

		// ログインユーザー保持
		SessionControler.setValue(request, SessionControler.LOGINUSER, dto.getUserid());
		SessionControler.setValue(request, SessionControler.LOGINUSERNAME, dto.getUsername());

		return true;
	}
}
