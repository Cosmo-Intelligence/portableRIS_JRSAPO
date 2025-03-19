package ris.portable.rest.resources;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import jakarta.servlet.http.HttpServletRequest;
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
import ris.portable.model.dto.SystemDto;
import ris.portable.util.DataTable;
import ris.portable.util.Util;

@Path("/system")
public class SystemResource {

	private static Log logger = LogFactory.getLog(SystemResource.class);

	@GET
	@Produces("application/json;charset=UTF-8")
	public Response doGet(
			@Context HttpServletRequest request) throws Exception {

		return doPost(request);
	}

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

		Connection risconn = null;

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
			 risconn = DataBase.getRisConnection();

			// コネクション取得判定
			if (risconn == null) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_ERROR);
				dto.setMsg("データベースへ接続できませんでした。");
				return false;
			}

			DataTable sysdateDat = null;

			// システム日付
			Timestamp sysdate = null;

			// システム日付取得
			sysdateDat = DataBase.getSysdate(risconn);

			// システム日付取得失敗
			if (sysdateDat.getRowCount() == 0) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_ERROR);
				dto.setMsg("システム日付情報を取得できませんでした。");
				return false;
			}

			// 取得したシステム日付の代入
			sysdate = (Timestamp)sysdateDat.getRows().get(0).get("SYSDATE");

			// 年月日でフォーマット
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00");

			// String型に変換
			String date = sdf.format(sysdate);

			// JSONクラスに書き込み
			dto.setSysdate(date);

			DataTable kanjaIdDat = null;

			// 患者ID 最大入力文字数
			String kanjaidlen = "";

			// 患者ID最大桁数取得
			kanjaIdDat = DataBase.getSystemParam(risconn, "KANJAID");

			// 患者ID最大桁数取得失敗
			if (kanjaIdDat.getRowCount() == 0) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_ERROR);
				dto.setMsg("患者ID最大桁数情報を取得できませんでした。");
				return false;
			}

			// 取得した患者ID最大桁数の代入
			kanjaidlen = (String)kanjaIdDat.getRows().get(0).get("VALUE1");

			// JSONクラスに書き込み
			dto.setKanjaidlen(kanjaidlen);

			DataTable renrakumemoDat = null;

			// 連絡メモ 最大入力バイト数
			String renrakumemolen = "";

			// 連絡メモ最大入力バイト数取得
			renrakumemoDat = DataBase.getSystemParam(risconn, "RENRAKU_MEMO");

			// 連絡メモ最大入力バイト数取得失敗
			if (renrakumemoDat.getRowCount() == 0) {
				dto.setResult(Const.RESULT_NG);
				dto.setErrlevel(Const.ERRLEVEL_ERROR);
				dto.setMsg("連絡メモ最大入力バイト数情報を取得できませんでした。");
				return false;
			}

			// 取得した連絡メモ最大入力バイト数の代入
			renrakumemolen = (String)renrakumemoDat.getRows().get(0).get("VALUE1");

			// JSONクラスに書き込み
			dto.setRenrakumemolen(renrakumemolen);

			// MPPS使用フラグ
			dto.setUse_mpps(config.getUseMPPS());

			// ポータブル区分使用フラグ
			dto.setUse_portable_kbn(config.getUsePortableKbn());

			// 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
			// 受付時MWM登録使用フラグ
			dto.setUse_receipt_mwm(config.getUseReceiptMWM());
			// 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応

			// 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
			dto.setStatuscolor_flg(config.getStatusColorFlg());
			// 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

			// DB切断
			DataBase.closeConnection(risconn);

		} catch(Exception ex) {
			logger.error(ex.toString(), ex);
			dto.setResult(Const.RESULT_NG);
			dto.setErrlevel(Const.ERRLEVEL_ERROR);
			dto.setMsg(ex.getMessage());

			// DB切断
			DataBase.closeConnection(risconn);

			return false;
		}

		return true;
	}
}
