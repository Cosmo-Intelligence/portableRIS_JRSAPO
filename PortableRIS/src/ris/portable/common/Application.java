package ris.portable.common;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.Configuration;
import ris.lib.core.app.StandardCoreController;
import ris.lib.core.bean.SystemParamBean;
import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.database.RisCurrentServerTypeTbl;
import ris.lib.core.database.RisSystemDefineTbl;
import ris.lib.core.database.RisSystemParamTbl;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.MessageParameter;
import ris.portable.database.DataBase;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

public class Application {

	private static Log logger = LogFactory.getLog(Application.class);

	//マスタ
	private DataTable mstSystemParam2 = null;

	/*
	 * 管理クラス初期化
	 */
	public void InitConfiguration(Connection con, HttpServletRequest request) throws Exception {

		// CoreコントローラクラスをConfigurationにSET
		Configuration.GetInstance().SetCoreController(new StandardCoreController());

		// RISスキーマを設定
		Configuration.GetInstance().SetRisDbUserStr(DataBase.getRisDBUser());

		// SystemParam2設定
		Configuration.GetInstance().SetRRisSystemParam2(Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SYSTEMPARAM2, true, con));
		this.mstSystemParam2 = Configuration.GetInstance().GetRRisSystemParam2();

		// SystemDefine設定
		InitSystemDefine(con);

		// SystemParam設定
		InitSystemParam(con);

		// SystemParam2設定
		//InitSystemParam2();

		// ログインユーザ情報取得
		UserAccountBean userBean = new UserAccountBean();
		userBean.SetUserID(SessionControler.getValue(request, SessionControler.LOGINUSER).toString());
		userBean.SetUserName(SessionControler.getValue(request, SessionControler.LOGINUSERNAME).toString());
		userBean.SetStaffID(SessionControler.getValue(request, SessionControler.LOGINUSER).toString());

        // 現在のログインユーザ情報設定
        Configuration.GetInstance().SetUserAccountBean(userBean);

		// ターミナル情報取得
		TerminalInfoBean terminalBean = new TerminalInfoBean();
		terminalBean.SetTerminalID("");
		terminalBean.SetTerminalName("WEB" + request.getRemoteAddr());

        // ターミナル情報設定
        Configuration.GetInstance().SetTerminalInfoBean(terminalBean);

	}

	/// <summary>
	/// システム情報の設定
	/// </summary>
	/// <remarks></remarks>
	private void InitSystemDefine(Connection con) throws Exception
	{
		try
		{
			// システム設定情報をDBから取得
			//DataTable currentServerTypeDataTable = Configuration.GetInstance().GetCoreController().GetRisCurrentServerType(con);
			DataTable systemDefineInfoDataTable = Configuration.GetInstance().GetCoreController().GetRisSystemDefine(con);

			//NULL チェック
			if (systemDefineInfoDataTable == null)
			{
				String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initDataBaseConfException_MessageString);
				logger.fatal(msg);
				throw new Exception(msg);
			}

			//Localモード もしくは Unityモード の確認
			String serverFlg = CommonString.LOCAL_CA_MODE;
			/* 一ノ瀬保留
			if (currentServerTypeDataTable != null && Arrays.asList(currentServerTypeDataTable.getColmunNames()).indexOf(RisCurrentServerTypeTbl.CURRENTTYPE_COLUMN) > -1)
			{
				serverFlg = currentServerTypeDataTable.getRows().get(0).get(RisCurrentServerTypeTbl.CURRENTTYPE_COLUMN).toString();
			}
			if (serverFlg == null || serverFlg.length() <= 0)
			{
				String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initCurrentConfirmAuditDefineException_MessageString);
				logger.fatal(msg);
				throw new Exception(msg);
			}

			*/
			Configuration.GetInstance().SetRisCurrentServerType(serverFlg);
			logger.info(RisCurrentServerTypeTbl.CURRENTTYPE_COLUMN + " = " + serverFlg);

			//エラー時メッセージ準備
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initSystemDefineException_MessageString);

			// 値を設定する

			// 認証用情報の設定
			//UNITY か LOCAL により、接続先を設定する
			/* 一ノ瀬保留
			if (serverFlg == CommonString.UNITY_CA_MODE)
			{
				//監査認証情報を取得、設定する
				if (!Configuration.GetInstance().GetCoreController().GetCurrentConfirmAuditDefine(serverFlg))
				{
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initCurrentConfirmAuditDefineException_MessageString);
					logger.fatal(msg);
					throw new Exception(msg);
				}
			}
			else
			{
				// LOCALのDB SID
				Configuration.GetInstance().SetAudittrailDbSourceStr(Configuration.GetInstance().GetRisDbSourceStr());
				Configuration.GetInstance().SetAuthenticationDbSourceStr(Configuration.GetInstance().GetRisDbSourceStr());
				// LOCALのDBユーザID
				Configuration.GetInstance().SetAudittrailDbUserStr(Configuration.GetInstance().GetRisDbUserStr());
				Configuration.GetInstance().SetAuthenticationDbUserStr(Configuration.GetInstance().GetRisDbUserStr());
				// LOCALのDBユーザパスワード
				Configuration.GetInstance().SetAudittrailDbPasswordStr(Configuration.GetInstance().GetRisDbPasswordStr());
				Configuration.GetInstance().SetAuthenticationDbPasswordStr(Configuration.GetInstance().GetRisDbPasswordStr());
				#region コメント
				//// LOCALのDB SID
				//Configuration.GetInstance().SetAudittrailDbSourceStr(Configuration.GetInstance().GetRisDbSourceStr());
				//Configuration.GetInstance().SetAuthenticationDbSourceStr(Configuration.GetInstance().GetRisDbSourceStr());

				//// LOCALのDBユーザID
				//if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SQ_USER_COLUMN))
				//{
				//    String message = MessageParameter.ERROE_CODE_000026;
				//    logger.fatal(message + " " + RisSystemDefineTbl.SQ_USER_COLUMN);
				//    throw new Exception(message);
				//}
				//Configuration.GetInstance().SetAudittrailDbUserStr(Configuration.GetInstance().GetRisDbUserStr());
				//Configuration.GetInstance().SetAuthenticationDbUserStr(Configuration.GetInstance().GetRisDbUserStr());

				//// LOCALのDBユーザパスワード
				//if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SQ_PASSWORD_COLUMN))
				//{
				//    String message = MessageParameter.ERROE_CODE_000026;
				//    logger.fatal(message + " " + RisSystemDefineTbl.SQ_PASSWORD_COLUMN);
				//    throw new Exception(message);
				//}
				//Configuration.GetInstance().SetAudittrailDbPasswordStr(Configuration.GetInstance().GetRisDbPasswordStr());
				//Configuration.GetInstance().SetAuthenticationDbPasswordStr(Configuration.GetInstance().GetRisDbPasswordStr());
			}
			*/

			// SystemDefine の値を設定する

			// LICENSENO
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.LICENSENO_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.LICENSENO_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetLicenseno(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.LICENSENO_COLUMN).toString());

			// KENZOUFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.KENZOUFLAG_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.KENZOUFLAG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetKenzouFlg(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.KENZOUFLAG_COLUMN).toString());

			// BUISETFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.BUISETFLAG_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.BUISETFLAG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetBuisetFlg(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.BUISETFLAG_COLUMN).toString());

			// MARKERCHARACTER
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MARKERCHARACTER_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MARKERCHARACTER_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMarkerCharacter(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MARKERCHARACTER_COLUMN).toString());

			// TEMPLATEMARKERCHAR
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.TEMPLATEMARKERCHAR_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.TEMPLATEMARKERCHAR_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetTemplateMarkerChar(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.TEMPLATEMARKERCHAR_COLUMN).toString());

			// CHILDLOWLIMIT
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.CHILDLOWLIMIT_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.CHILDLOWLIMIT_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetChildlowlimit(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.CHILDLOWLIMIT_COLUMN).toString());

			// CHILDHIGHLIMIT
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.CHILDHIGHLIMIT_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.CHILDHIGHLIMIT_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetChildhighlimit(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.CHILDHIGHLIMIT_COLUMN).toString());

			// BABYLOWLIMIT
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.BABYLOWLIMIT_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.BABYLOWLIMIT_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetBabylowlimit(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.BABYLOWLIMIT_COLUMN).toString());

			// BABYHIGHLIMIT
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.BABYHIGHLIMIT_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.BABYHIGHLIMIT_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetBabyhighlimit(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.BABYHIGHLIMIT_COLUMN).toString());

			// RISORDERSENDFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RISORDERSENDFLAG_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RISORDERSENDFLAG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRisOrdersendFlag(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RISORDERSENDFLAG_COLUMN).toString());

			// RISORDERSENDFLAG2
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RISORDERSENDFLAG2_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RISORDERSENDFLAG2_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRisOrdersendFlag2(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RISORDERSENDFLAG2_COLUMN).toString());

			// IMAGEURLFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMAGEURLFLAG_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMAGEURLFLAG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImageUrlFlag(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMAGEURLFLAG_COLUMN).toString());

			// IMAGEURL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMAGEURL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMAGEURL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImageUrl(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMAGEURL_COLUMN).toString());

			// IMGUSERLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMGUSERLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMGUSERLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImgUserLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMGUSERLABEL_COLUMN).toString());

			// IMGPASSWORDLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMGPASSWORDLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMGPASSWORDLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImgPasswordLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMGPASSWORDLABEL_COLUMN).toString());

			// IMGPIDLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMGPIDLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMGPIDLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImgPidLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMGPIDLABEL_COLUMN).toString());

			// IMGEDATELABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMGEDATELABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMGEDATELABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImgDateLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMGEDATELABEL_COLUMN).toString());

			// IMGMODALITYLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMGMODALITYLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMGMODALITYLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImgModalityLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMGMODALITYLABEL_COLUMN).toString());

			// IMGMODALITYLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMGMODALITYLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMGMODALITYLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImgModalityLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMGMODALITYLABEL_COLUMN).toString());

			// IMGACNOLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.IMGACNOLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.IMGACNOLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetImgAcnoLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.IMGACNOLABEL_COLUMN).toString());

			// REPORTURLFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.REPORTURLFLAG_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.REPORTURLFLAG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetReportUrlFlag(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.REPORTURLFLAG_COLUMN).toString());

			// REPORTURL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.REPORTURL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.REPORTURL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetReportUrl(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.REPORTURL_COLUMN).toString());

			// RPTUSERLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RPTUSERLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RPTUSERLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRptUserLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RPTUSERLABEL_COLUMN).toString());

			// RPTPASSWORDLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RPTPASSWORDLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RPTPASSWORDLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRptPasswordLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RPTPASSWORDLABEL_COLUMN).toString());

			// RPTPIDLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RPTPIDLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RPTPIDLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRptPidLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RPTPIDLABEL_COLUMN).toString());

			// RPTEDATELABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RPTEDATELABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RPTEDATELABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRptDateLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RPTEDATELABEL_COLUMN).toString());

			// RPTMODALITYLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RPTMODALITYLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RPTMODALITYLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRptModalityLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RPTMODALITYLABEL_COLUMN).toString());

			// RPTACNOLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.RPTACNOLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.RPTACNOLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetRptAcnoLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.RPTACNOLABEL_COLUMN).toString());

			// SCHEMAURLFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHEMAURLFLAG_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHEMAURLFLAG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchemaUrlFlag(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHEMAURLFLAG_COLUMN).toString());

			// EXTENDORDERUSEFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.EXTENDORDERUSEFLAG_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.EXTENDORDERUSEFLAG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetExtendOrderUseFlag(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.EXTENDORDERUSEFLAG_COLUMN).toString());

			// SCHEMAURL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHEMAURL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHEMAURL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchemaUrl(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHEMAURL_COLUMN).toString());

			// SCHUSERLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHUSERLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHUSERLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchUserLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHUSERLABEL_COLUMN).toString());

			// SCHPASSWORDLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHPASSWORDLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHPASSWORDLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchPasswordLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHPASSWORDLABEL_COLUMN).toString());

			// SCHPIDLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHPIDLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHPIDLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchPidLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHPIDLABEL_COLUMN).toString());

			// SCHEDATELABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHEDATELABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHEDATELABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchEdateLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHEDATELABEL_COLUMN).toString());

			// SCHMODALITYLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHMODALITYLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHMODALITYLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchModalityLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHMODALITYLABEL_COLUMN).toString());

			// SCHORDERNOLABEL
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SCHORDERNOLABEL_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SCHORDERNOLABEL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSchOrdernoLabel(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SCHORDERNOLABEL_COLUMN).toString());

			// AUDITFLAG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.AUDITFLAG_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.USECRKIND_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetAuditFlag(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.AUDITFLAG_COLUMN).toString());

			// SYSTEMID
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.SYSTEMID_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.SYSTEMID_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetSystemID(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.SYSTEMID_COLUMN).toString());

			// KENZOUFIX
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.KENZOUFIX_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.KENZOUFIX_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetKenzouFix(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.KENZOUFIX_COLUMN).toString());

			// KENZOUPASSDLG
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.KENZOUPASSDLG_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.KENZOUPASSDLG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetKenzouPassDlg(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.KENZOUPASSDLG_COLUMN).toString());

			// KENZOUCONNECTWAIT
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.KENZOUCONNECTWAIT_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.KENZOUCONNECTWAIT_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetKenzouConnectWait(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.KENZOUCONNECTWAIT_COLUMN).toString());

			// KENZOUSHOWWAIT
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.KENZOUSHOWWAIT_COLUMN) < 0)
			{
				 logger.fatal(message + " " + RisSystemDefineTbl.KENZOUSHOWWAIT_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetKenzouShowWait(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.KENZOUSHOWWAIT_COLUMN).toString());

			//検査MPPSフラグ
			String kMppsFlg = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.MPPSDEFINE, RisSystemParamTbl.VALUE1_COLUMN);
			Configuration.GetInstance().SetMppsKensaFlg(kMppsFlg);

			// 2010.10.18 Add K.Shinohara Start
			// 撮影系MPPS使用フラグ
			String sMppsFlg = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.MPPSDEFINE, RisSystemParamTbl.VALUE4_COLUMN);
			Configuration.GetInstance().SetMppsSatueiFlg(sMppsFlg);
			// 2010.10.18 Add K.Shinohara End

			//MPPSFLG_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPSFLG_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPSFLG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsFlg(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPSFLG_COLUMN).toString());

			//MPPS_USER_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_USER_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_USER_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsUser(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_USER_COLUMN).toString());

			//MPPS_PASSWORD_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_PASSWORD_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_PASSWORD_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsPassword(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_PASSWORD_COLUMN).toString());

			//MPPS_SERVICENAME_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_SERVICENAME_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_SERVICENAME_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsServiceName(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_SERVICENAME_COLUMN).toString());

			//MPPS_INTERVAL_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_INTERVAL_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_INTERVAL_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsInterval(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_INTERVAL_COLUMN).toString());

			//MPPS_RETRYCOUNT_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_RETRYCOUNT_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_RETRYCOUNT_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsRetryCount(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_RETRYCOUNT_COLUMN).toString());

			//MPPS_FILMSETTYPE_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_FILMSETTYPE_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_FILMSETTYPE_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsFilmSetType(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_FILMSETTYPE_COLUMN).toString());

			//MPPS_FIX_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_FIX_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_FIX_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsFix(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_FIX_COLUMN).toString());

			//MPPS_PASSWDDLG_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_PASSWDDLG_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_PASSWDDLG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsPasswdDlg(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_PASSWDDLG_COLUMN).toString());

			//MWM_USER_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MWM_USER_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MWM_USER_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMwmUser(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MWM_USER_COLUMN).toString());

			//MWM_PASSWORD_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MWM_PASSWORD_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MWM_PASSWORD_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMwmPassword(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MWM_PASSWORD_COLUMN).toString());

			//MWM_SERVICENAME_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MWM_SERVICENAME_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MWM_SERVICENAME_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMwmServiceName(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MWM_SERVICENAME_COLUMN).toString());

			//MWM_UKETUKEENTRY_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MWM_UKETUKEENTRY_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MWM_UKETUKEENTRY_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMwmUketukeEntry(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MWM_UKETUKEENTRY_COLUMN).toString());

			//MWM_RECEIPTENTRY_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MWM_RECEIPTENTRY_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MWM_RECEIPTENTRY_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMwmReceiptEntry(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MWM_RECEIPTENTRY_COLUMN).toString());

			//MPPS_UNITFLG_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.MPPS_UNITFLG_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.MPPS_UNITFLG_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetMppsUnitFlg(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.MPPS_UNITFLG_COLUMN).toString());

			// 2010.07.30 Add Y.Shibata Start
			//TODAY_ZOUEIZAI_ID_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.TODAY_ZOUEIZAI_ID_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.TODAY_ZOUEIZAI_ID_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetTodayZoueizaiIdstr(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.TODAY_ZOUEIZAI_ID_COLUMN).toString());

			//TODAY_ZOUEIZAI_KBN_COLUMN
			if (Arrays.asList(systemDefineInfoDataTable.getColmunNames()).indexOf(RisSystemDefineTbl.TODAY_ZOUEIZAI_KBN_COLUMN) < 0)
			{
				logger.fatal(message + " " + RisSystemDefineTbl.TODAY_ZOUEIZAI_KBN_COLUMN);
				throw new Exception(message);
			}
			Configuration.GetInstance().SetTodayZoueizaiKbnstr(systemDefineInfoDataTable.getRows().get(0).get(RisSystemDefineTbl.TODAY_ZOUEIZAI_KBN_COLUMN).toString());
			// 2010.07.30 Add Y.Shibata End


			//管理項目フラグ
			String examItemFlg = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.EXAMITEM, RisSystemParamTbl.VALUE1_COLUMN);
			if (CommonString.FLG_ON.equals(examItemFlg))
			{
				Configuration.GetInstance().SetExamItemFlg(true);
			}

			// 2010.11.01 Add K.Shinohara Start
			// ForceActive使用フラグ
			String forceActiveFlg = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.FORCEACTIVE, RisSystemParamTbl.VALUE1_COLUMN);
			if (CommonString.FLG_ON.equals(forceActiveFlg))
			{
				Configuration.GetInstance().SetForceActiveFlg(true);
			}
			// 2010.11.01 Add K.Shinohara End

			// 2011.05.06 Add K.Shinohara Start A0027
			// V1系一括MWM機器取得フラグ
			String lumpMWMKikiModeFlg = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.LUMPMWMKIKIMODE, RisSystemParamTbl.VALUE1_COLUMN);
			if (CommonString.FLG_ON.equals(lumpMWMKikiModeFlg))
			{
				Configuration.GetInstance().SetLumpMWMKikiModeFlg(true);
			}
			// 2011.05.06 Add K.Shinohara End

			// 2011.10.24 Add K.Shinohara Start YRH-1-03  OMH-1-08【山田赤十字】
			String zoueizaiCheckFlg = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.ZOUEIZAI_CHECK, RisSystemParamTbl.VALUE1_COLUMN);
			if (CommonString.FLG_ON.equals(zoueizaiCheckFlg))
			{
				Configuration.GetInstance().SetZoueizaiCheckFlg(true);
			}
			// 2011.10.24 Add K.Shinohara End
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initDataBaseConfException_MessageString) + "\r\n" + e);
			throw e;
		}
	}


	/*
	 * システム情報の設定
	 */
	private void InitSystemParam(Connection con) throws Exception
	{
		try {

			//DBからシステム情報を取得する
			DataTable dt = Configuration.GetInstance().GetCoreController().GetRisSystemParam(con);
			SystemParamBean systemParamBean = new SystemParamBean();

			if (dt != null) {

				for (int i = 0; i < dt.getRowCount(); i++) {
					DataRow row = dt.getRows().get(i);

					if (row.get(RisSystemParamTbl.SUBID_COLUMN) != null) {
						continue;
					}

					String subIdStr = row.get(RisSystemParamTbl.SUBID_COLUMN).toString();

					// 値を設定する
					if (subIdStr.equals(RisSystemParamTbl.KANJAID)) {
						systemParamBean.SetKanjaIDValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.SIJI_ISI_COMMENT)) {
						systemParamBean.SetSijiIsiCommentValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.RENRAKU_MEMO)) {
						systemParamBean.SetRenrakuMemoValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.BIKOU)) {
						systemParamBean.SetBikouValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.HANDICAPPED)) {
						systemParamBean.SetHandicappedValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.NOTES)) {
						systemParamBean.SetNotesValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.PLACE)) {
						systemParamBean.SetPlaceValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetPlaceValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.RAD_ID)) {
						systemParamBean.SetRadIdValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.RESULTCOUNT)) {
						systemParamBean.SetResultcountValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.COMBOLISTS)) {
						systemParamBean.SetCombolistsValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.FLAGS)) {
						systemParamBean.SetFlagsValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetFlagsValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
						//アプリ上では使用しない
						systemParamBean.SetFlagsValue3(row.get(RisSystemParamTbl.VALUE3_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.RECEIPTNO)) {
						systemParamBean.SetReceiptnoValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.MAXCOUNT)) {
						systemParamBean.SetMaxcountValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetMaxcountValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
						systemParamBean.SetMaxcountValue3(row.get(RisSystemParamTbl.VALUE3_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.BARCODE)) {
						systemParamBean.SetBarcodeValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetBarcodeValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
						systemParamBean.SetBarcodeValue3(row.get(RisSystemParamTbl.VALUE3_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.STATUSBGCOLOR)) {
						systemParamBean.SetStatusbgcolorValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetStatusbgcolorValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
						systemParamBean.SetStatusbgcolorValue3(row.get(RisSystemParamTbl.VALUE3_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.SELECTROWBGCOLOR)) {
						systemParamBean.SetSelectrowbgcolorValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetSelectrowbgcolorValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
						systemParamBean.SetSelectrowbgcolorValue3(row.get(RisSystemParamTbl.VALUE3_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.SELECTROWTEXTCOLOR)) {
						systemParamBean.SetSelectrowtextcolorValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetSelectrowtextcolorValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
						systemParamBean.SetSelectrowtextcolorValue3(row.get(RisSystemParamTbl.VALUE3_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.SUURYOU)) {
						systemParamBean.SetSuuryouValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetSuuryouValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.SUURYOU_IJI)) {
						systemParamBean.SetSuuryouIjiValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetSuuryouIjiValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.SETSTARTTIME)) {
						systemParamBean.SetSetstarttimeValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.PASSWDDLG)) {
						systemParamBean.SetPasswordValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetPasswordValue2(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
					}
					// 2010.07.30 Add T.Ootsuka Start
					// 新規テンプレート区分追加
					else if (subIdStr.equals(RisSystemParamTbl.KANJAKYOTUCOMMENT)) {
						systemParamBean.SetKanjaKyotuCommentValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					else if (subIdStr.equals(RisSystemParamTbl.KENSATYPECOMMENT)) {
						systemParamBean.SetKensaTypeCommentValue1(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					// 2010.07.30 Add T.Ootsuka End
					// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
					else if (subIdStr.equals(RisSystemParamTbl.RECEIPTRULE)) {
						systemParamBean.SetReceiptRuleValue1Str(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
						systemParamBean.SetReceiptRuleValue2Str(row.get(RisSystemParamTbl.VALUE2_COLUMN).toString());
					}
					// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08
					// 2011.07.14 Add NSK_R.Akimoto Add Start NML-CAT2-017
					else if (subIdStr.equals(RisSystemParamTbl.RECEIPTKANJA)) {
						// RECEIPTKANJAの値を設定
						systemParamBean.SetReceiptKanjaValue1Str(row.get(RisSystemParamTbl.VALUE1_COLUMN).toString());
					}
					// 2011.07.14 Add NSK_R.Akimoto Add End
				}
			}

			//読込んだシステム情報を設定する
			Configuration.GetInstance().SetSystemParam(systemParamBean);

		} catch (Exception e) {
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initDataBaseConfException_MessageString) + "\r\n" + e);
			throw e;
		}
	}

	/// <summary>
	/// システム情報の設定
	/// </summary>
	private void InitSystemParam2() throws Exception
	{
		try
		{
			// 2011.01.24 Add T.Nishikubo Start KANRO-R-22
			// 受付ダイアログ 患者情報拡張フラグ
			String receptPatInfFlg = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.RECEIPT_EXTEND_PATIENTINFO,
				RisSystemParamTbl.VALUE1_COLUMN);
			Configuration.GetInstance().SetReceiptExtendPatientInfoFlg(receptPatInfFlg);
			// 2011.01.24 Add T.Nishikubo End

			// 2011.01.24 Add DD.Chinh Start KANRO-R-19
			// プレチェック検索情報機能フラグ
			String preStsFlg = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.PRECHECK_STATUS,
				RisSystemParamTbl.VALUE1_COLUMN);
			Configuration.GetInstance().SetPreStsFlg(preStsFlg);
			// 2011.01.24 Add DD.Chinh End

			// 2011.03.18 Add Yk.Suzuki@CIJ Start A0005
			String orderDeleteFlg = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.SMILE_ORDER_DELETE,
				RisSystemParamTbl.VALUE1_COLUMN);
			Configuration.GetInstance().SetOrderDeleteFlg(orderDeleteFlg);
			// 2011.03.18 Add Yk.Suzuki@CIJ End

			// 2011.08.22 Add T.Nishikubo@MERX Start NML-2-X01
			String holidayMode = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.HOLIDAY_MODE,
				RisSystemParamTbl.VALUE1_COLUMN);
			Configuration.GetInstance().GetSystemParam().SetHolidayModeValue1(holidayMode);
			// 2011.08.22 Add T.Nishikubo@MERX End

			// 2011.11.18 Add NSK_H.Hashimoto Start OMH-1-03
			ArrayList EmergencyValList = new ArrayList();
			String strEmergencyVal = "";

			strEmergencyVal = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.EMG_COLOR,
				RisSystemParamTbl.VALUE1_COLUMN
				);
			EmergencyValList.add(strEmergencyVal);
			strEmergencyVal = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.EMG_COLOR,
				RisSystemParamTbl.VALUE2_COLUMN
				);
			EmergencyValList.add(strEmergencyVal);
			strEmergencyVal = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.EMG_COLOR,
				RisSystemParamTbl.VALUE3_COLUMN
				);
			EmergencyValList.add(strEmergencyVal);
			strEmergencyVal = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.EMG_COLOR,
				RisSystemParamTbl.VALUE4_COLUMN
				);
			EmergencyValList.add(strEmergencyVal);
			strEmergencyVal = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.EMG_COLOR,
				RisSystemParamTbl.VALUE5_COLUMN
				);
			EmergencyValList.add(strEmergencyVal);
			strEmergencyVal = Configuration.GetInstance().GetSystemParamValue(
				this.mstSystemParam2,
				RisSystemParamTbl.EMG_COLOR,
				RisSystemParamTbl.VALUE6_COLUMN
				);
			EmergencyValList.add(strEmergencyVal);
			Configuration.GetInstance().GetSystemParam().SetEemergencyValueList(EmergencyValList);
			// 2011.11.18 Add NSK_H.Hashimoto End
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initDataBaseConfException_MessageString) + "\r\n" + e);
			throw e;
		}
	}

}
