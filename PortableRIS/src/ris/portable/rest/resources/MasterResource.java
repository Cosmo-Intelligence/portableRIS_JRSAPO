package ris.portable.rest.resources;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
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
import ris.portable.database.DataBase;
import ris.portable.model.dto.MasterInfoDto;
import ris.portable.util.DataTable;
import ris.portable.util.Util;

@Path("/master")
public class MasterResource {

	private static Log logger = LogFactory.getLog(MasterResource.class);

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

		logger.debug("☆★☆マスタ情報取得リクエスト---開始");

		// マスタ情報
		MasterInfoDto master = new MasterInfoDto();

		// 実行
		Execute(request, master);

		// JSON変換
		String json = Util.getJson(master);

		logger.debug("☆★☆マスタ情報取得リクエスト---JSON:" + json);

		logger.debug("☆★☆マスタ情報取得リクエスト---終了");

		return Response.ok().entity(json).build();
	}

	/**
	 * 処理実行
	 * @param request  :httpリクエスト
	 * @param dto      :マスタ情報
	 * @return
	 */
	private boolean Execute(
			HttpServletRequest request,
			MasterInfoDto dto) throws Exception {

		try {
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
				// 受付マスタ情報
				DataTable uketukeDt = null;
				// 受付マスタ情報取得
				uketukeDt = DataBase.getUserManage(config.getSyokuinkbn().split(","), risconn);

				// 受付マスタ情報取得失敗
				if (uketukeDt.getRowCount() == 0){
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("受付者情報を取得できませんでした。");
					return false;
				}

				// 受付者情報設定
				dto.setUketuke_array(getMasterList(uketukeDt));

				// 病棟マスタ情報
				DataTable byoutouDt = null;
				// 病棟マスタ情報取得
				byoutouDt = DataBase.getByoutouMaster(config.getByoutouid().split(","), risconn);

				// 病棟マスタ情報取得失敗
				if (byoutouDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("病棟情報を取得できませんでした。");
					return false;
				}

				// 病棟情報設定
				dto.setByoutou_array(getMasterList(byoutouDt));

				// 検査種別マスタ情報
				DataTable kensatypeDt = null;
				// 検査種別マスタ情報取得
				kensatypeDt = DataBase.getKensaTypeMaster(config.getKensatypeid().split(","), risconn);

				// 検査種別マスタ情報取得失敗
				if (kensatypeDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("検査種別情報を取得できませんでした。");
					return false;
				}

				// 検査種別情報設定
				dto.setKensatype_array(getMasterList(kensatypeDt));

				// 検査室マスタ情報
				DataTable kensasituDt = null;
				// 検査室マスタ情報取得
				kensasituDt = DataBase.getKensasituMaster(config.getKensatypeid().split(","), risconn);

				// 検査室マスタ情報取得失敗
				if (kensasituDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("検査室情報を取得できませんでした。");
				}

				// 検査室情報設定
				dto.setKensasitu_array(getMaster2List(kensasituDt, config.getKensatypeid().split(","), risconn));

				// 連絡メモテンプレート情報
				DataTable renrakumemoDt = null;
				// 連絡メモテンプレート情報取得
				renrakumemoDt = DataBase.getTemplateContents(config.getGroupcode().split(","), risconn);

				// 連絡メモテンプレート情報取得失敗
				if (renrakumemoDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("連絡メモテンプレート情報を取得できませんでした。");
					return false;
				}

				// 連絡メモテンプレート情報設定
				ArrayList<MasterInfoDto.Template> templateList = new ArrayList<MasterInfoDto.Template>();

				for (int i = 0; i < renrakumemoDt.getRowCount(); i++) {
					MasterInfoDto.Template template = new MasterInfoDto.Template();

					template.setId(Integer.parseInt(renrakumemoDt.getRows().get(i).get("ID").toString()));
					template.setName(renrakumemoDt.getRows().get(i).get("NAME").toString());

					templateList.add(template);
				}

				// 連絡メモテンプレート情報設定
				dto.setTemplate_array(templateList);

				// 検査ステータス情報
				DataTable kensastatusDt = null;
				// 検査ステータス情報取得
				kensastatusDt = DataBase.getKensaStatus(config.getStatuscode().split(","), risconn);

				// 検査ステータス情報取得失敗
				if (kensastatusDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("検査ステータス情報を取得できませんでした。");
					return false;
				}

				// 検査ステータス情報設定
				ArrayList<MasterInfoDto.Status> statusList = new ArrayList<MasterInfoDto.Status>();

				// 2020.08.25 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
				String statuscolorbk_on =  config.getStatusColorBk_ON();
				String statuscolorbk_off =  config.getStatusColorBk_OFF();
				String statuscolor_on =  config.getStatusColor_ON();
				String statuscolor_off =  config.getStatusColor_OFF();

//				for (int i = 0; i < kensastatusDt.getRowCount(); i++) {
//				MasterInfoDto.Status status = new MasterInfoDto.Status();
//
//				status.setStatuscode(Integer.parseInt(kensastatusDt.getRows().get(i).get("STATUSCODE").toString()));
//				status.setShortlabel(kensastatusDt.getRows().get(i).get("SHORTLABEL").toString());
//				status.setColor(Util.convertBGRtoRGB(Integer.parseInt(kensastatusDt.getRows().get(i).get("COLOR").toString())));
//				status.setColorbk(Util.convertBGRtoRGB(Integer.parseInt(kensastatusDt.getRows().get(i).get("COLORBK").toString())));
//
//				statusList.add(status);
//				}

				//DBから取得した検査進捗ボタンの色と設定ファイルから取得した進捗ボタンの色をそれぞれ詰める
				for (int i = 0; i < kensastatusDt.getRowCount(); i++) {
					MasterInfoDto.Status status = new MasterInfoDto.Status();

					status.setStatuscode(Integer.parseInt(kensastatusDt.getRows().get(i).get("STATUSCODE").toString()));
					status.setShortlabel(kensastatusDt.getRows().get(i).get("SHORTLABEL").toString());
					status.setColor(Util.convertBGRtoRGB(Integer.parseInt(kensastatusDt.getRows().get(i).get("COLOR").toString())));
					status.setColorbk(Util.convertBGRtoRGB(Integer.parseInt(kensastatusDt.getRows().get(i).get("COLORBK").toString())));
					status.setColor_ON(Util.convertBGRtoRGB(Integer.parseInt(statuscolor_on)));
					status.setColorbk_ON(Util.convertBGRtoRGB(Integer.parseInt(statuscolorbk_on)));
					status.setColor_OFF(Util.convertBGRtoRGB(Integer.parseInt(statuscolor_off)));
					status.setColorbk_OFF(Util.convertBGRtoRGB(Integer.parseInt(statuscolorbk_off)));

					statusList.add(status);
				}
				// 2020.08.25 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

				// 検査ステータス情報設定
				dto.setStatus_array(statusList);

				// 入外区分情報取得
				String[] nyugaikbnArray = config.getNyugaikbn().split(",");

				// 入外区分情報取得失敗
				if (nyugaikbnArray.length == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("入外区分情報を取得できませんでした。");
					return false;
				}

				// 入外区分情報設定
				dto.setNyugai_array(getKbnList(nyugaikbnArray));

				// ポータブル区分情報取得
				String[] portablekbnArray = config.getPortablekbn().split(",");

				// ポータブル区分情報取得失敗
				if (portablekbnArray.length == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("ポータブル区分情報を取得できませんでした。");
					return false;
				}

				// ポータブル区分情報設定
				dto.setPortable_array(getKbnList(portablekbnArray));

				// 2020.09.14 Add Nishihara@COSMO start 実施技師登録機能追加対応
				// 実施者(担当技師)マスタ情報
				DataTable zisshishaDt = null;
				// 実施者(担当技師)マスタ情報取得
				zisshishaDt = DataBase.getZisshishaMaster(risconn);

				// 実施者(担当技師)マスタ情報取得失敗
				if (zisshishaDt.getRowCount() == 0) {
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_ERROR);
					dto.setMsg("実施者(担当技師を取得できませんでした。)");
					return false;
				}

				// 実施者(担当技師)マスタ情報設定
				ArrayList<MasterInfoDto.Zisshisha> zisshishaList = new ArrayList<MasterInfoDto.Zisshisha>();

				//USERIDIDとUSERNAMEを結び付けてリストに入れる
				for (int i = 0; i < zisshishaDt.getRowCount(); i++) {
					MasterInfoDto.Zisshisha zisshisha = new MasterInfoDto.Zisshisha();

					zisshisha.setUserId(zisshishaDt.getRows().get(i).get("USERID").toString());
					zisshisha.setUserName(zisshishaDt.getRows().get(i).get("USERNAME").toString());

					zisshishaList.add(zisshisha);
				}

				// 実施者(担当技師)マスタ情報設定
				dto.setZisshisha_array(zisshishaList);

				// 2020.09.14 Add Nishihara@COSMO end 実施技師登録機能追加対応

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
	 * マスタリスト取得
	 * @param dt :マスタ情報
	 * @return
	 */
	private ArrayList<MasterInfoDto.Master> getMasterList(DataTable dt) throws Exception {

		// マスタリスト情報
		ArrayList<MasterInfoDto.Master> masterList = new ArrayList<MasterInfoDto.Master>();

		for (int i = 0; i < dt.getRowCount(); i++) {
			MasterInfoDto.Master master = new MasterInfoDto.Master();

			// マスタ情報設定
			master.setId(dt.getRows().get(i).get("ID").toString());
			master.setName(dt.getRows().get(i).get("NAME").toString());

			masterList.add(master);
		}

		return masterList;
	}

	/**
	 * マスタリスト2取得
	 * @param dt :マスタ情報
	 * @return
	 */
	private ArrayList<MasterInfoDto.Master2> getMaster2List(DataTable dt, String[] kensatypeids, Connection con) throws Exception {

		// マスタリスト情報
		ArrayList<MasterInfoDto.Master2> masterList = new ArrayList<MasterInfoDto.Master2>();

		for (int i = 0; i < dt.getRowCount(); i++) {

			for (String kensatypeid: dt.getRows().get(i).get("KENSATYPE_ID").toString().split(",")) {

				// 検査種別IDが不一致の場合
				if (!isEqualKensatype(kensatypeids, kensatypeid)) {
					continue;
				}

				// 検査室IDが一致した場合
				if (isEqualsKensasitu(masterList, dt.getRows().get(i).get("ID").toString())) {
					continue;
				}

				MasterInfoDto.Master2 master2 = new MasterInfoDto.Master2();

				// 検査室マスタ情報設定
				master2.setId(dt.getRows().get(i).get("ID").toString());
				master2.setName(dt.getRows().get(i).get("NAME").toString());

				// 検査機器マスタ情報
				DataTable kensakikiDt = null;
				// 検査機器マスタ情報取得
				kensakikiDt = DataBase.getKensakikiMaster(dt.getRows().get(i).get("KENSAKIKI_ID").toString().split(","), con);

				// 検査機器情報設定
				master2.setKensakiki_array(getMasterList(kensakikiDt));

				// 検査室情報追加
				masterList.add(master2);
			}
		}

		return masterList;
	}

	/**
	 * 検査種別イコールチェック
	 * @param kensatypeids
	 * @param compareid
	 * @return
	 */
	private boolean isEqualKensatype(String[] kensatypeids, String compareid) {

		for (String kensatypeid : kensatypeids) {
			// 検査種別IDが一致した場合
			if (kensatypeid.equals(compareid)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 検査室イコールチェック
	 * @param masterList
	 * @param kensasituid
	 * @return
	 */
	private boolean isEqualsKensasitu(ArrayList<MasterInfoDto.Master2> masterList, String kensasituid) {

		for (MasterInfoDto.Master2 kensasitu : masterList) {
			// 検査室IDが一致した場合
			if (kensasitu.getId().equals(kensasituid)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 区分 マスタリスト取得
	 * @param array : 設定ファイルから取得した設定値
	 * @return
	 * @throws Exception
	 */
	private ArrayList<MasterInfoDto.Master> getKbnList(String[] array) throws Exception {

		// マスタリスト情報
		ArrayList<MasterInfoDto.Master> masterList = new ArrayList<MasterInfoDto.Master>();

		for (int i = 0; i < array.length; i++) {
			MasterInfoDto.Master master = new MasterInfoDto.Master();

			// ID と 名称 を分割
			String[] temp = array[i].split(":");

			// マスタ情報設定
			master.setId(temp[0]);
			master.setName(temp[1]);

			masterList.add(master);
		}

		return masterList;

	}
}
