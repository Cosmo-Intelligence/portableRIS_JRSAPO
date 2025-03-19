package ris.portable.rest.resources;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ris.portable.common.Const;
import ris.portable.model.dto.SystemDto;
import ris.portable.util.Util;

@Path("/loginsystem")
public class LoginSystemResource {

	private static Log logger = LogFactory.getLog(LoginSystemResource.class);

	@GET
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@Context HttpServletRequest request) throws Exception {

		return doPost(request);
	}

	@Context
	private ServletContext context;

	@POST
	@Produces("application/json;charset=UTF-8")
	public Response doPost(
			@Context HttpServletRequest request) throws Exception {

		logger.debug("☆★☆システム情報取得リクエスト---開始");

		// JSONクラス
		SystemDto system = new SystemDto();

		// 実行
		Execute(request, system);

		// JSON変換
		String json = Util.getJson(system);

		logger.debug("☆★☆システム情報取得リクエスト---JSON:" + json);

		logger.debug("☆★☆システム情報取得リクエスト---終了");

		return Response.ok().entity(json).build();
	}

	/**
	 * 処理実行
	 * @param request :httpリクエスト
	 * @param dto     :システム情報
	 * @return
	 * @throws Exception
	 */
	public boolean Execute(
			HttpServletRequest request,
			SystemDto dto) throws Exception {


		try{
			// 設定ファイル情報取得(SessionControlerがnullの為、設定ファイル読込処理を追加)
			URL url = context.getResource(Const.CONFIG_FILE);
			logger.debug(Const.CONFIG_FILE + " = " + url);

			InputStream stream = context.getResourceAsStream(Const.CONFIG_FILE);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(stream);
			Element root = doc.getDocumentElement();

			HashMap<String,String> map = new HashMap<String,String>();
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				logger.debug(root.getChildNodes().item(i).getNodeName() + ":" + root.getChildNodes().item(i).getTextContent());
				map.put(root.getChildNodes().item(i).getNodeName(), Util.toNullString(root.getChildNodes().item(i).getTextContent()));
			}

			// 2021.09.01 Add Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応
			Boolean loginEscKeyFlg = Boolean.valueOf(map.get("LoginEscKeyFlg"));
			// 2021.09.01 Add Nishihara@COSMO end ログイン画面のESCキーフォーカス除外有無対応

			// 設定ファイル情報取得判定
			if (loginEscKeyFlg == null) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_ERROR);
				dto.setMsg("システム設定情報が取得できませんでした。");
				return false;
			}

			// 設定ファイル「ログイン画面のESCキーフォーカス除外有無」の値をセット
			dto.setLoginesckey_flg(loginEscKeyFlg);


		} catch(Exception ex) {
			logger.error(ex.toString(), ex);
			dto.setResult(Const.RESULT_NG);
			dto.setErrlevel(Const.ERRLEVEL_ERROR);
			dto.setMsg(ex.getMessage());

			return false;
		}

		return true;
	}
}
