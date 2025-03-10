package ris.lib.core.app;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.CoreController;
import ris.lib.core.bean.AccessInfoBean;
import ris.lib.core.bean.ExamOperationHistoryInfoBean;
import ris.lib.core.bean.ExecutionInfoBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.PatientInfoBean;
import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.database.RisAccessInfoTbl;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.OrderSearchParameter;
import ris.lib.core.util.PresetParameter;
import ris.lib.core.util.RequestParameter;
import ris.lib.core.util.UserSearchParameter;
import ris.lib.mwm.bean.ConnectionInfoBean;
import ris.lib.mwm.core.MwmManager;
import ris.portable.common.Const;
import ris.portable.model.dto.StatusUpdateDto;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;



/// <summary>
///
/// コア管理実装クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)				(Comment)
/// V1.00.00		: 2009.02.18	: 112478 / A.Kobayashi	: original
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo	: KANRO-R-9
/// V2.04.00		: 2011.02.16	: 999999 / T.Nishikubo	: KANRO-R-27
/// V2.01.00		: 2011.07.19	: 999999 / NSK_T.Wada	: NML-CAT2-001
/// V2.01.00		: 2011.08.03	: 999999 / NSK_S.Imai	: NML-CAT2-029
/// V2.01.00		: 2011.08.05	: 999999 / DD.Chinh		: NML-1-X03
/// V2.01.00		: 2011.08.11	: 999999 / NSK_T.Koudate: NML-CAT2-004
/// V2.01.00		: 2011.08.16	: 999999 / DD.Chinh		: NML-CAT2-010 NML-1-X04
/// V2.01.01.13000	: 2011.11.21	: 999999 / NSK_M.Ochiai extends OMH-1-7
/// V2.01.01.13000	: 2011.11.22	: 999999 / NSK_T.Wada	: OMH-1-9
/// </summary>
public final class StandardCoreController extends CoreController
{
	//外部メッセージテーブル
	private Hashtable externalMessageTable = null;

	//RISマスタデータ
//	private DataSet risMasterInfo = null;

	// 共通群
	/// <summary>
	/// 外部メッセージテーブルを設定する
	/// </summary>
	/// <param name="val">外部メッセージテーブル</param>
	@Override
	public void SetExternalMessageTableImpl(Hashtable val)
	{
		this.externalMessageTable = val;
	}

	/// <summary>
	/// メッセージを取得する
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>メッセージ文字列</returns>
	@Override
	public String GetMessageStringImpl(String key)
	{
		String ret = "";

		if ((this.externalMessageTable != null) && (this.externalMessageTable.contains(key)))
		{
			ret = this.externalMessageTable.get(key).toString();
		}
		else {
			ret = key;
		}

		return ret;
	}

	/*
	/// <summary>
	/// オラクルエラーを画面に表示する
	/// </summary>
	/// <param name="ex">Exception</param>
	private void ShowMessage(Exception ex)
	{
		if (ex != null)
		{
			if (ex.GetType().Name == typeof(Exception).Name ||
				ex.GetType().Name == typeof(ArgumentException).Name)
			{
				Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(
					Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbException_MessageString)
				+ "\r\n" + ex.Message);
			}
		}
	}
	*/

	/// <summary>
	/// チェックアウトを行う
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">チェックアウト情報</param>
	/// <returns>他ユーザによって編集中等でcheckoutできない場合は空文字を返す
	/// checkoutに成功した場合はCheckoutIDを返す</returns>
	@Override
	public String CheckoutRisOrder(AccessInfoBean bean, Connection con)
	{
		// 進捗：完了

		// parameters
		String checkoutIDStr = "";
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (bean != null)
		{
			logger.info(bean.toString());
		}
		else
		{
			logger.info("[AccessInfoBean] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (bean != null)
			{
				checkoutIDStr = accessInfoHandler.CheckoutRisData(bean, con);
			}
		}
		catch (Exception e)
		{
			//ここではオラクルエラーは表示させない
			checkoutIDStr = "";
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (!StringUtils.isEmpty(checkoutIDStr))
		{
			logger.info(checkoutIDStr);
		}
		else
		{
			logger.info("[CheckoutID] NULL");
		}
		//
		//
		//////////////////////////////

		return checkoutIDStr;
	}

	/*
	// 2010.09.10 Add K.Shinohara Start
	/// <summary>
	/// チェックアウトを行う(オーダキャンセル用)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">チェックアウト情報</param>
	/// <param name="ownerAppID">親画面ID</param>
	/// <returns>他ユーザによって編集中等でcheckoutできない場合は空文字を返す
	/// checkoutに成功した場合はCheckoutIDを返す</returns>
	@Override
	public String CheckoutRisOrder_OrderCancel(AccessInfoBean bean, String ownerAppID)
	{
		// 進捗：完了

		// parameters
		String checkoutIDStr = "";
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (bean != null)
		{
			logger.info(bean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullAccessInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (bean != null)
			{
				checkoutIDStr = accessInfoHandler.CheckoutRisData_OrderCancel(bean, ownerAppID);
			}
		}
		catch (Exception e)
		{
			//ここではオラクルエラーは表示させない
			checkoutIDStr = "";
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (checkoutIDStr != null)
		{
			logger.info(checkoutIDStr);
		}
		else
		{
			logger.info("[CheckoutID] NULL");
		}
		//
		//
		//////////////////////////////

		return checkoutIDStr;
	}
	// 2010.09.10 Add K.Shinohara End
	*/

	/// <summary>
	/// チェックアウトを解除する
	/// </summary>
	/// <param name="risID">RisID</param>
	/// <returns>判定</returns>
	@Override
	public boolean UncheckoutRisData(String risID, Connection con) throws Exception
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		// begin log
		logger.debug("begin");

		if (risID != null)
		{
			try
			{
				retFlg = accessInfoHandler.Uncheckout(risID, con);
			}
			catch (Exception e)
			{
				logger.fatal(e);
				throw e;	// オラクルエラーの場合メッセージを表示
			}
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	// 2019.10.03 Add Cosmo Start 排他ロック対応
	/// <summary>
	/// チェックアウトを解除する
	/// </summary>
	/// <param name="risID">RisID</param>
	/// <returns>判定</returns>
	@Override
	public boolean UncheckoutunLoginLockRisData(String ipAddress,String appID, Connection con) throws Exception
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		// begin log
		logger.debug("begin");

		if (ipAddress != null && appID != null)
		{
			try
			{
				retFlg = accessInfoHandler.UncheckoutLoginLock(ipAddress, appID, con);
			}
			catch (Exception e)
			{
				logger.fatal(e);
				throw e;	// オラクルエラーの場合メッセージを表示
			}
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	// 2019.10.03 Add Cosmo End 排他ロック対応
	/*
	// 2010.09.09 Add K.Shinohara Start
	/// <summary>
	/// チェックアウトを解除する
	/// </summary>
	/// <param name="bean">アクセス情報</param>
	/// <returns>判定</returns>
	@Override
	public boolean UncheckoutRisData(AccessInfoBean bean)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		// begin log
		logger.debug("begin");

		if (bean != null)
		{
			try
			{
				retFlg = accessInfoHandler.Uncheckout(bean);
			}
			catch (Exception e)
			{
				ShowMessage(e);	// オラクルエラーの場合メッセージを表示
				logger.fatal(e);
			}
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	// 2010.09.09 Add K.Shinohara End
	*/

	/*
	/// <summary>
	/// 全データをアンチェックアウトする
	/// </summary>
	/// <returns></returns>
	@Override
	public void UncheckoutRisAllData()
	{
		// 進捗：完了

		// parameters
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();
		RisReceiptNumberAccessInfoTbl risReceiptNumberAccessInfoTbl = null;

		// begin log
		logger.debug("begin");

		try
		{
			//当該IPアドレスでロックしている全データのロックを解除する
			accessInfoHandler.UncheckoutAll();

			risReceiptNumberAccessInfoTbl = new RisReceiptNumberAccessInfoTbl();
			risReceiptNumberAccessInfoTbl.UncheckoutAll();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.Warn(e);
		}

		// end log
		logger.debug("end");

		return;
	}
	*/

	/// <summary>
	/// チェックアウト情報を取得する
	/// </summary>
	/// <param name="risID">RisID</param>
	/// <returns>チェックアウト情報</returns>
	@Override
	public DataRow GetCheckoutData(String risID, Connection con)
	{
		// 進捗：完了

		// parameters
		DataRow retRow = null;
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		// begin log
		logger.debug("begin");

		if (risID != null)
		{
			try
			{
				retRow = accessInfoHandler.GetCheckoutData(risID, con);
			}
			catch (Exception e)
			{
				logger.fatal(e);
			}
		}

		// end log
		logger.debug("end");

		return retRow;
	}

	/*
    /// <summary>
    /// チェックアウト情報を取得する
    /// </summary>
    /// <remarks></remarks>
    /// <param name="kanjaIDStr">患者ID</param>
    /// <param name="kensaDateStr">検査日</param>
    /// <returns>端末名称</returns>
	@Override
    public String GetCheckoutData(String kanjaIDStr, String kensaDateStr, String risIDStr)
	{
		// 進捗：完了

		// parameters
        String retStr = "";
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		// begin log
		logger.debug("begin");

        if (kanjaIDStr != null && kensaDateStr != null && risIDStr != null)
		{
			try
			{
				retStr = accessInfoHandler.GetCheckoutData(kanjaIDStr, kensaDateStr, risIDStr);
			}
			catch (Exception e)
			{
				ShowMessage(e);	// オラクルエラーの場合メッセージを表示
				logger.fatal(e);
			}
		}

		// end log
		logger.debug("end");

		return retStr;
	}
	*/

	/*
	// 2010.09.10 Add K.Shinohara Start
	/// <summary>
	/// チェックアウト情報を取得する(オーダキャンセル用)
	/// </summary>
	/// <param name="accessBean">AccessInfoBean</param>
	/// <returns>チェックアウト情報</returns>
	@Override
	public DataRow GetCheckoutData_OrderCancel(AccessInfoBean accessBean, String ownerAppID)
	{
		// 進捗：完了

		// parameters
		DataRow retRow = null;
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		// begin log
		logger.debug("begin");

		if (accessBean != null)
		{
			try
			{
				retRow = accessInfoHandler.GetCheckoutData_OrderCancel(accessBean, ownerAppID);
			}
			catch (Exception e)
			{
				ShowMessage(e);	// オラクルエラーの場合メッセージを表示
				logger.fatal(e);
			}
		}

		// end log
		logger.debug("end");

		return retRow;
	}
	// 2010.09.10 Add K.Shinohara End
	*/

	/// <summary>
	/// システム設定情報をDBから取得する
	/// </summary>
	/// <returns></returns>
	@Override
	public DataTable GetRisSystemDefine(Connection con)
	{
		// parameters
		DataTable dataTable = null;
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");

		try
		{
			dataTable = masterHandler.GetRisSystemDefine(con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}

		return dataTable;
	}

	/// <summary>
	/// システム情報をDBから取得する
	/// </summary>
	@Override
	public DataTable GetRisSystemParam(Connection con)
	{
		// parameters
		DataTable dataTable = null;
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");

		try
		{
			dataTable = masterHandler.GetRisSystemParam(con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}

		return dataTable;
	}

	/// <summary>
	/// ｶﾚﾝﾄ認証監査証跡ｻｰﾊﾞ種別をDBから取得する
	/// </summary>
	@Override
	public DataTable GetRisCurrentServerType(Connection con)
	{
		// parameters
		DataTable dataTable = null;
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");

		try
		{
			dataTable = masterHandler.GetRisCurrentServerType(con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}

		return dataTable;
	}

	/*
	/// <summary>
	/// 共通情報をDBから取得する
	/// </summary>
	@Override
	public CommonParameterBean GetCommonParameters()
	{
		// parameters
		CommonParameterBean commonParam = null;
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");

		try
		{
			commonParam = masterHandler.GetCommonParameters();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}

		return commonParam;
	}
	*/

	/*
	/// <summary>
	/// テンプレート取得
	/// </summary>
	/// <param name="id">TEMPLATEID</param>
	/// <returns></returns>
	@Override
	public DataTable GetTemplateContents(String id, String paramStr)
	{
		logger.debug("begin");
		DataTable dt = null;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (id != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTemplateID_MessageString) + id);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTemplateID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			MasterHandler masterHandler = new MasterHandler();
			dt = masterHandler.GetTemplateContents(id, paramStr);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		logger.debug("end");

		return dt;
	}
	*/

	/*
	// DB接続の関数群
	/// <summary>
	/// RISの接続する
	/// </summary>
	/// <returns>接続状態 true:接続 false:切断</returns>
	@Override
	public boolean GetRISDBConnection()
	{
		boolean retBool = false;
		try
		{
			Connection rConnection = DBConnectionFactory.GetInstance().GetRisDBConnection();

			if (rConnection != null && rConnection.State == System.Data.ConnectionState.Open)
			{
				DBConnectionFactory.GetInstance().ReturnDBConnection(rConnection);

				retBool = true;
			}
			else
			{
				retBool = false;
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}
		return retBool;
	}
	*/

	/*
	/// <summary>
	/// RIS用マスターデータをDBから収集する
	/// </summary>
	@Override
	public void CollectRisMasterInfo()
	{
		logger.debug("begin");

		try
		{
			MasterHandler masterHandler = new MasterHandler();
			this.risMasterInfo = masterHandler.CollectRisMasterInfo();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		logger.debug("end");
	}
	*/

	/*
	/// <summary>
	/// テンプレートグループマスタの取得
	/// </summary>
	/// <returns></returns>
	@Override
	public DataTable TelplateGroupMaster(String groupCode)
	{
		logger.debug("begin");
		DataTable dt = null;

		try
		{
			MasterHandler masterHandler = new MasterHandler();
			dt = masterHandler.GetTelplateGroupMaster(groupCode);

		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		logger.debug("end");

		return dt;
	}
	*/


	/*
	 *  DB処理群
	 */

	/*
	/// <summary>
	/// ログインする
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="planePassword">パスワード</param>
	/// <returns>判定</returns>
	@Override
	public boolean Login(String userID, String planePassword)
	{
		// 進捗：完了

		// parameters
		boolean loginFlg = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (userID != null)
		{
			logger.info("[String]:" + userID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullUserID_MessageString));
		}
		//
		//
		//////////////////////////////
		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (planePassword != null)
		{
			logger.info("[String]:" + planePassword);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPlanePassword_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			// ログインに成功したら、ConfigurationにUserAccountBeanをSETする
			UserHandler userHandler = new UserHandler();
			UserAccountBean userAccount = userHandler.Login(userID, planePassword);
			Configuration.GetInstance().SetUserAccountBean(userAccount);

			loginFlg = userAccount.GetLoginFlag();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.loginException_MessageString);
			logger.fatal(message, e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (loginFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKLogin_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGLogin_MessageString));
		}
		//
		//
		//////////////////////////////

		return loginFlg;
	}
	*/

	/*
	/// <summary>
	/// RIS用マスターデータを取得する
	/// </summary>
	/// <returns>マスターデータ</returns>
	@Override
	public DataSet GetRisMasterInfo()
	{
		return this.risMasterInfo;
	}
	*/

	/*
	/// <summary>
	/// ログインする
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="planePassword">パスワード</param>
	/// <returns>判定</returns>
	@Override
	public boolean Login(String userID)
	{
		// 進捗：完了

		// parameters
		boolean loginFlg = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (userID != null)
		{
			logger.info("[String]:" + userID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullUserID_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			// ログインに成功したら、ConfigurationにUserAccountBeanをSETする
			UserHandler userHandler = new UserHandler();
			UserAccountBean userAccount = userHandler.Login(userID);
			Configuration.GetInstance().SetUserAccountBean(userAccount);

			loginFlg = userAccount.GetLoginFlag();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.loginException_MessageString);
			logger.fatal(message, e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (loginFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKLogin_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGLogin_MessageString));
		}
		//
		//
		//////////////////////////////

		return loginFlg;
	}
	*/

	/*
	/// <summary>
	/// 監査認証情報を取得、設定する
	/// </summary>
	/// <param name="currentType">ｻｰﾊﾞ種別</param>
	/// <returns></returns>
	@Override
	public boolean GetCurrentConfirmAuditDefine(String currentType)
	{
		// parameters
		boolean retBool = false;
		UserHandler userHandler = new UserHandler();

		// begin log
		logger.debug("begin");

		if (currentType != null)
		{
			try
			{
				retBool = userHandler.GetGetCurrentConfirmAuditDefine(currentType);
			}
			catch (Exception e)
			{
				ShowMessage(e);	// オラクルエラーの場合メッセージを表示
				logger.fatal(e);
			}
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// SQ/Servにあるユーザ情報をローカルにダウンロードする
	/// </summary>
	/// <returns>判定</returns>
	@Override
	public boolean EqualizeUserInfo(UserAccountBean userBean)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		try
		{
			if (Configuration.GetInstance().GetRisCurrentServerType() == CommonString.UNITY_CA_MODE && userBean != null)
			{
				SQUserInfoHandler userInfoHandler = new SQUserInfoHandler();
				retFlg = userInfoHandler.EqualizeUserInfo(userBean);
			}
			else
			{
				retFlg = true;
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.equalizeUserInfoException_MessageString);
			logger.fatal(message, e);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// 監査証跡を登録する
	/// </summary>
	/// <param name="auditBean">監査証跡情報</param>
	/// <returns>判定</returns>
	@Override
	public boolean RegisterAuditTrail(AuditTrailInfoBean auditBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (auditBean != null)
		{
			logger.info(auditBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullAuditTrailInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (auditBean != null)
			{
				//監査認証保存フラグ判断
				if (Configuration.GetInstance().GetAuditFlag() != CommonString.FLG_ON)
				{
					retFlg = true;
				}
				else
				{
					AuditTrailHandler auditHandler = new AuditTrailHandler();
					retFlg = auditHandler.RegistAuditTrailData(auditBean);
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registAuditTrailException_MessageString);
			logger.fatal(message, e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterAuditTrail_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterAuditTrail_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// オーダ部位情報取得
	/// </summary>
	/// <param name="id">RIS_ID</param>
	/// <returns>オーダ部位情報</returns>
	@Override
	public DataTable GetRisOrderBuiList(String risID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		// begin log
		logger.debug("begin");

		try
		{
			dt = orderHandler.GetRisOrderBuiInfo(risID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/// <summary>
	/// 登録済みのオーダ一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
	@Override
	public DataTable GetRisOrderList(OrderSearchParameter param, Connection con)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				//オーダ情報を検索する
				dt = orderHandler.SearchRisOrderData(param, con);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.searchRisOrderDataException_MessageString);
			//Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg + "\r\n" + e.Message);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.getRowCount());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}

	/*
	/// <summary>
	/// 登録済みのオーダ一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
	@Override
	public ArrayList GetRisOrderList(ArrayList risIDArrayList)
	{
		// 進捗：完了

		// parameters
		ArrayList arrayList = new ArrayList();
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risIDArrayList != null)
		{
			logger.info(risIDArrayList.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (risIDArrayList != null)
			{
				arrayList = orderHandler.SearchRisOrderData(risIDArrayList);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			arrayList = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (arrayList != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + arrayList.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return arrayList;
	}
	*/

	/// <summary>
	/// 登録済みのオーダ部位一覧を取得する
	/// </summary>
	/// <returns>オーダ部位一覧</returns>
	@Override
	public DataTable GetRisOrderBuiList(OrderSearchParameter param, Connection con)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				dt = orderHandler.SearchRisOrderBuiData(param, con);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.getRowCount());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}

	/*
	/// <summary>
	/// 登録済みの患者一覧情報を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
	@Override
	public DataTable GetRisPatientInfo(PatientInfoBean bean)
	{
		// parameters
		DataTable dt = null;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (bean != null)
		{
			logger.info("[String]:" + bean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (bean != null && (bean.GetKanjaID().length() > 0 || bean.GetKanaSimei().length() > 0))
			{
				dt = patientHandler.GetRisPatientData(bean);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/

	/// <summary>
	/// 登録済みの患者情報を取得する
	/// </summary>
	/// <returns>患者情報「</returns>
	@Override
	public PatientInfoBean GetRisPatientInfo(String patientID, Connection con)
	{
		// 進捗：完了

		// parameters
		// parameters
		PatientInfoBean patientData = null;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientID != null)
		{
			logger.info("[String]:" + patientID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (patientID != null && patientID.length() > 0)
			{
				patientData = patientHandler.GetRisPatientData(patientID, con);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			patientData = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (patientData != null)
		{
			logger.info(patientData.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////


		return patientData;
	}

	/// <summary>
	/// 登録済みの実績患者情報を取得する
	/// </summary>
	/// <returns>患者情報</returns>
	@Override
	public PatientInfoBean GetRisResultPatientInfo(String risID, String patientID, Connection con)
	{
		// 進捗：完了

		// parameters
		// parameters
		PatientInfoBean patientData = null;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientID != null)
		{
			logger.info("[String]:" + patientID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (patientID != null && patientID.length() > 0)
			{
				patientData = patientHandler.GetRisResultsPatientData(risID, patientID, con);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			patientData = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (patientData != null)
		{
			logger.info(patientData.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////


		return patientData;
	}

	/*
	/// <summary>
	/// マスタを取得する
	/// </summary>
	/// <param name="tableNameStr">指定マスタテーブル名</param>
	/// <param name="keyColumnStr">指定カラム名</param>
	/// <param name="keyData">指定値（条件句)</param>
	/// <returns>結果</returns>
	@Override
	public DataRow GetMaster(String tableNameStr, String keyColumnStr, String keyData)
	{
		logger.debug("begin");

		MasterHandler masterHandler = new MasterHandler();
		DataRow dataRow = null;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (tableNameStr != null)
		{
			logger.info("[String]:" + tableNameStr);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (keyColumnStr != null)
		{
			logger.info("[String]:" + keyColumnStr);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (keyData != null)
		{
			logger.info("[String]:" + keyData);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		try
		{
			dataRow = masterHandler.GetMaster(tableNameStr, keyColumnStr, keyData);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
			throw e;
		}

		return dataRow;
	}
	*/

	/// <summary>
	/// マスタデータを取得する
	/// </summary>
	/// <returns>DataTable</returns>
	@Override
	public DataTable GetMaster(String tableNameStr, boolean ascBool, Connection con) throws Exception
	{
		logger.debug("begin");

		MasterHandler masterHandler = new MasterHandler();
		DataTable masterDataTable = new DataTable();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (tableNameStr != null)
		{
			logger.info("[String]:" + tableNameStr);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		try
		{
			masterDataTable = masterHandler.GetMaster(tableNameStr, ascBool, con);
		}
		catch (Exception e)
		{
			logger.fatal(e);
			throw e;
		}

		return masterDataTable;
	}

	/*
	// 2010.07.30 Add T.Nishikubo Start
	/// <summary>
	/// マスタを取得する
	/// </summary>
	/// <param name="tableNameStr">指定マスタテーブル名</param>
	/// <param name="keyColumnStr">指定カラム名</param>
	/// <param name="keyData">指定値（条件句)</param>
	/// <param name="sortCol">ソート順カラム</param>
	/// <returns>結果</returns>
	@Override
	public DataTable GetMaster(String tableNameStr, String keyColumnStr, String keyData, String sortCol)
	{
		logger.debug("begin");

		MasterHandler masterHandler = new MasterHandler();
		DataTable masterDataTable = null;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (tableNameStr != null)
		{
			logger.info("[String]:" + tableNameStr);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (keyColumnStr != null)
		{
			logger.info("[String]:" + keyColumnStr);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (keyData != null)
		{
			logger.info("[String]:" + keyData);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (sortCol != null)
		{
			logger.info("[String]:" + sortCol);
		}
		else
		{
			logger.info("[String]:");
		}
		//
		//
		//////////////////////////////

		try
		{
			masterDataTable = masterHandler.GetMaster(tableNameStr, keyColumnStr, keyData, sortCol);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
			throw e;
		}

		return masterDataTable;
	}
	// 2010.07.30 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// スタータスマスタを取得する
	/// </summary>
	/// <returns>DataTable</returns>
	@Override
	public DataTable GetStatusDefine()
	{
		logger.debug("begin");

		MasterHandler masterHandler = new MasterHandler();
		DataTable statusDefineDataTable = new DataTable();

		try
		{
			statusDefineDataTable = masterHandler.GetMaster(MasterUtil.RIS_STATUSDEFINE, true);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
			throw e;
		}

		return statusDefineDataTable;
	}
	*/

	/// <summary>
	/// RISオーダ情報のステータスを変更する
	/// </summary>
	/// <param name="risID">ステータス変更対象オーダの内部UID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="status">変更後のステータス</param>
	/// <param name="user">更新ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
	@Override
	// 2019.08.06 Mod Cosmo Start 排他対応
	//public boolean ChangeRisOrderStatus(String risID, String appID, String status, UserAccountBean user, String roomID, String kikiID, Connection con)
	public boolean ChangeRisOrderStatus(String risID, String appID, String status, UserAccountBean user, String roomID, String kikiID, Connection con,int type,boolean exclusiv,StringBuilder returnErrorMsg)
	// 2019.08.06 Mod Cosmo End 排他対応
	{
		// 進捗：済み

		// parameters
		boolean retFlg = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		if (status != null)
		{
			logger.info("[String]:" + status);
		}
		else
		{
			logger.info("[Status] NULL");
		}
		if (user != null)
		{
			logger.info(user.toString());
		}
		else
		{
			logger.info("[AdmissionUser] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");
		try
		{
			if (risID != null && risID.length() > 0 && status != null && user != null)
			{
				// オーダー情報をチェックアウトする
				AccessInfoBean accessBean = new AccessInfoBean();
				// 2019.08.06 Mod Cosmo Start 排他対応
				//dummyのOK
				String checkoutID = "ok";
				//accessBean.SetID(risID);
				//accessBean.SetAppID(appID);
				//accessBean.SetIpAddress(user.GetIPAddress());
				//String checkoutID = CheckoutRisOrder(accessBean, con);
				if(type == 1 || !exclusiv){
					accessBean.SetID(risID);
					accessBean.SetAppID(appID);
					accessBean.SetIpAddress(user.GetIPAddress());
					checkoutID = CheckoutRisOrder(accessBean, con);
				}
				// 2019.08.06 Mod Cosmo End 排他対応
				if (checkoutID != null && checkoutID.length() > 0)
				{
					try
					{
						con.commit();
						// オーダー情報ステータスを更新
						retFlg = resultHandler.UpdateRisExecutionResultStatus(risID, status, user, roomID, kikiID, con);
					}
					finally
					{
						// 2019.08.06 Mod Cosmo Start 排他対応
						//オーダー情報をアンチェックアウトする
						//UncheckoutRisData(checkoutID, con);
						//con.commit();
						if(type == 1 || !exclusiv){
							//オーダー情報をアンチェックアウトする
							UncheckoutRisData(checkoutID, con);
							con.commit();
						}
						// 2019.08.06 Mod Cosmo End 排他対応
					}
					retFlg = true;
				}
				else
				{
					//①チェックアウト情報取得
					DataRow checkoutRow = GetCheckoutData(risID, con);
					if (checkoutRow != null)
					{
						//②IPアドレスから端末情報を取得
						String ipAddress = checkoutRow.get(RisAccessInfoTbl.IPADDRESS_COLUMN).toString();
						TerminalInfoBean terminalBean = GetTerminalInfoDataByIPAdrress(ipAddress, con);
						if (terminalBean == null)
						{
							terminalBean = new TerminalInfoBean();
						}

						//③RisIDからオーダ情報を取得
						OrderInfoBean orderBean = GetRisOrderInfo(risID, con);
						if (orderBean != null)
						{
							MasterUtil mUtil = new MasterUtil();
							Configuration config = new Configuration();
							String kTypeNM = mUtil.FindData(
												config.GetRRisKensaTypeMaster(con),
												MasterUtil.RIS_KENSATYPE_NAME,
												MasterUtil.RIS_KENSATYPE_ID,
												orderBean.GetKensatypeID());

							//④メッセージ表示
							String errMsg = "指定のオーダは、他の端末で編集中です。\n\n　端末名		：%s\n　患者ＩＤ		：%s\n　検査日		：%s\n　検査種別		：%s\n　ＲＩＳＩＤ	：%s";
							errMsg = String.format(errMsg,
								terminalBean.GetTerminalName(),
								orderBean.GetKanjaID(),
								orderBean.GetKensaDateValue().toString(),
								kTypeNM,
								risID);
							logger.debug(errMsg);
							returnErrorMsg.append(errMsg);
							//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(errMsg);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info("ChangeOrderStatus [OK]");
		}
		else
		{
			logger.info("ChangeOrderStatus [NG]");
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}

	/*
	// 2010.07.30 Add DD.Chinh Start
	/// <summary>
	/// RIS検査室、検査機器を変更する
	/// </summary>
	/// <param name="risID">ステータス変更対象オーダの内部UID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="status">ステータス</param>
	/// <param name="user">更新ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
	@Override
	public boolean ChangeExamRoomMachine(String risID, String status, UserAccountBean user, String roomID, String kikiID)
	{
		boolean retFlg = false;
		ResultHandler resultHandler = new ResultHandler();

		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		if (status != null)
		{
			logger.info("[String]:" + status);
		}
		else
		{
			logger.info("[Status] NULL");
		}
		if (user != null)
		{
			logger.info(user.toString());
		}
		else
		{
			logger.info("[AdmissionUser] NULL");
		}
		if (roomID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKensaroomID_MessageString) + roomID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKensaRoom_MessageString));
		}
		if (kikiID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKensakikiID_MessageString) + kikiID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKensakikiID_MessageString));
		}


		logger.debug("begin");
		try
		{
			if (risID != null && risID.length() > 0 && status != null && user != null)
			{
				// オーダー情報ステータスを更新
				retFlg = resultHandler.UpdateRisExamRoomMachine(risID, status, user, roomID, kikiID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info("ChangeOrderStatus [OK]");
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGChangeOrderStatus_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	// 2010.07.30 Add DD.Chinh End
	*/

	/// <summary>
	/// 指定された実績情報（引数のrisID）を取得する
	/// </summary>
	/// <param name="risID">取得対象の実績情報のRISID</param>
	/// <returns>実績情報</returns>
	@Override
	public ExecutionInfoBean GetRisExecutionInfo(String risID, Connection con)
	{
		// 進捗：完了

		// parameters
		ExecutionInfoBean executionInfoBean = null;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0)
			{
				executionInfoBean = resultHandler.GetExecutionInfoBean(risID, con);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			executionInfoBean = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (executionInfoBean != null)
		{
			logger.info(executionInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		return executionInfoBean;
	}

	/*
	// 2011.02.17 Add H.Orikasa Start
	/// <summary>
	/// 指定された実績情報（引数のrisID）のみを取得する
	/// </summary>
	/// <param name="risID">取得対象の実績情報のRISID</param>
	/// <returns>実績情報</returns>
	@Override
	public ExecutionInfoBean GetRisExecutionInfoOnly(String risID)
	{
		// 進捗：完了

		// parameters
		ExecutionInfoBean executionInfoBean = null;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0)
			{
				executionInfoBean = resultHandler.GetExecutionInfoBeanOnly(risID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			executionInfoBean = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (executionInfoBean != null)
		{
			logger.info(executionInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		return executionInfoBean;
	}
	// 2011.02.17 Add H.Orikasa End
	*/

	/*
	/// <summary>
	/// 受付権限を確認する
	/// </summary>
	/// <param name="userID">対象ユーザID</param>
	/// <returns>判定</returns>
	@Override
	public boolean CheckReceptUserAuthorization(String userID)
	{
		// 進捗：完了

		// parameters
		boolean receptUserAuthorizationBool = false;
		UserHandler userHandler = new UserHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (userID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleUserID_MessageString) + userID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullUserID_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		//receptUserAuthorizationBool = userHandler.CheckReceptUserAuthorization(userID);

		// end log
		logger.debug("end");

		return receptUserAuthorizationBool;
	}
	*/

	/*
	/// <summary>
	/// 登録済みのオーダ一数をカウントする
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	@Override
	public int GetRisOrderCount(OrderSearchParameter param)
	{
		// 進捗：完了

		// parameters
		int orderCount = 0;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				orderCount = orderHandler.CountRisOrderData(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOrderCount_MessageString) + orderCount);
		//
		//
		//////////////////////////////

		return orderCount;
	}
	*/

	/*
	/// <summary>
	/// リストデータを「,」区切で結合して返す
	/// </summary>
	/// <param name="arrData">リストデータ</param>
	/// <returns></returns>
	private String MakeStringData(ArrayList arrData)
	{
		String tempStr = "";
		for (int i = 0; i < arrData.Count; i++)
		{
			if (tempStr == "")
			{
				tempStr = arrData[i].toString();
			}
			else
			{
				tempStr = tempStr + "," + arrData[i].toString();
			}
		}
		return tempStr;
	}
	*/

	/// <summary>
	/// 患者情報の更新
	/// </summary>
	/// <param name="patientInfoBean">患者情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisPatientData(PatientInfoBean patientInfoBean, Connection con)
	{
		logger.debug("begin");
		boolean ret = true;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientInfoBean != null)
		{
			logger.info(patientInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////
		try
		{
			patientHandler.UpdateRisPatientData(patientInfoBean, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}

	/*
	/// <summary>
	/// 実績患者情報を最新の患者情報へ更新する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="risID">RISID</param>
	/// <returns></returns>
	@Override
	public boolean UpdateRisResultPatientData(String kanjaID, String risID)
	{
		logger.debug("begin");
		boolean ret = true;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (kanjaID != null)
		{
			logger.info("[String]:" + kanjaID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientID_MessageString));
		}
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////
		try
		{
			patientHandler.UpdateRisResultPatientData(kanjaID, risID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	///  患者コメントを更新する
	/// </summary>
	/// <param name="patientID">患者ID</param>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="comment">コメント</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisPatientComment(String patientID, String kensatypeID, String comment)
	{
		logger.debug("begin");
		boolean ret = true;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientID != null)
		{
			logger.info("[String]:" + patientID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			patientHandler.UpdateRisPatientComment(patientID, kensatypeID, comment);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 患者身長体重情報の更新
	/// </summary>
	/// <param name="risIDStr">RisID</param>
	/// <param name="patientInfoBean">患者情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisPatientResultTallWeightData(String risIDStr, PatientInfoBean patientInfoBean)
	{
		logger.debug("begin");
		boolean ret = true;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risIDStr != null)
		{
			logger.info("[UserID] :" + risIDStr);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		if (patientInfoBean != null)
		{
			logger.info(patientInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////
		try
		{
			//実績患者身長体重情報を更新する
			patientHandler.UpdateRisPatientResultTallWeightData(risIDStr, patientInfoBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 連絡メモ情報の更新
	/// </summary>
	/// <param name="memo">連絡メモ情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisRenrakuMemo(String risID, UserAccountBean userAccount, String memo)
	{
		logger.debug("begin");
		boolean ret = true;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}

		//
		if (memo != null)
		{
			logger.info("[String]:" + "NULL");
		}
		else
		{
			logger.info("[String]:" + memo);
		}
		//
		//
		//////////////////////////////
		try
		{
			ret = orderHandler.UpdateRisRenrakuMemo(risID, userAccount, memo);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 連絡メモ情報の更新
	/// </summary>
	/// <param name="memo">連絡メモ情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public DataTable GetOrderIndicateTable(String risID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			dt = orderHandler.GetOrderIndicateTable(risID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// オーダ指示情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>オーダ指示情報</returns>
	@Override
	public OrderIndicateBean GetRisOrderIndicate(String risID)
	{
		// 進捗：完了

		// parameters
		OrderIndicateBean orderIndicateBean	= null;
		OrderHandler orderHandler			= new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0)
			{
				orderIndicateBean = orderHandler.GetOrderIndicateData(risID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (orderIndicateBean != null)
		{
			logger.info(orderIndicateBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		return orderIndicateBean;
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	/*
	/// <summary>
	/// 検査室情報の更新
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisExRoom(String risID, String exRoomID)
	{
		logger.debug("begin");
		boolean ret = true;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}

		//
		if (exRoomID != null)
		{
			logger.info("[String]:" + "NULL");
		}
		else
		{
			logger.info("[String]:" + exRoomID);
		}
		//
		//
		//////////////////////////////
		try
		{
			ret = orderHandler.UpdateRisExRoom(risID, exRoomID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;

	}
	*/

	/*
	/// <summary>
	/// HIS受信履歴情報の取得
	/// </summary>
	/// <returns>HIS受信履歴情報</returns>
	@Override
	public DataTable GetFromHisInfo(String risID, String recieveID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		try
		{
			dt = orderHandler.GetFromHisInfo(risID, recieveID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// HIS送信履歴情報の取得
	/// </summary>
	/// <returns>HIS送信履歴情報</returns>
	@Override
	public DataTable GetToHisInfo(String risID, String recieveID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		try
		{
			dt = orderHandler.GetToHisInfo(risID, recieveID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 所見送信ﾘｸｴｽﾄ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>所見送信ﾘｸｴｽﾄ情報</returns>
	@Override
	public DataTable GetToReportInfo(String risID, String recieveID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		try
		{
			dt = orderHandler.GetToReportInfo(risID, recieveID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 画像送信ﾘｸｴｽﾄ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>画像送信ﾘｸｴｽﾄ情報</returns>
	@Override
	public DataTable GetToPacsInfo(String risID, String recieveID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		try
		{
			dt = orderHandler.GetToPacsInfo(risID, recieveID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/// <summary>
	/// RIS用のオーダ情報の取得
	/// </summary>
	/// <returns>オーダ情報</returns>
	@Override
	public OrderInfoBean GetRisOrderInfo(String risID, Connection con) throws Exception
	{
		// 進捗：完了

		// parameters
		OrderInfoBean orderInfoBean = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0)
			{
				orderInfoBean = orderHandler.GetRisOrderData(risID, con);
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
			throw ex;
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (orderInfoBean != null)
		{
			logger.info(orderInfoBean.toString());
		}
		else
		{
			logger.info("[ExecutionInfoBean] NULL");
		}
		//
		//
		//////////////////////////////

		return orderInfoBean;
	}

	/*
	/// <summary>
	/// 自端末のTerminalIDをIPAddressから取得する
	/// </summary>
	/// <returns>TerminalID</returns>
	@Override
	public int GetMyTerminalID()
	{
		logger.debug("begin");

		int terminalIDInt = -1;

		TerminalHandler terminalHandler = new TerminalHandler();

		try
		{
			terminalIDInt = terminalHandler.GetMyTerminalID(Configuration.GetInstance().GetIPAddress());
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			terminalIDInt = -1;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTerminalID_MessageString) + terminalIDInt.toString());
		//
		//
		//////////////////////////////

		return terminalIDInt;
	}
	*/

	/*
	/// <summary>
	/// 指定端末の情報を取得する
	/// </summary>
	/// <param name="terminalID">端末ID</param>
	/// <returns>端末情報</returns>
	@Override
	public TerminalInfoBean GetTerminalData(String terminalID)
	{
		TerminalInfoBean terminalInfoData = null;
		TerminalHandler terminalHandler = new TerminalHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (terminalID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTemplateID_MessageString) + terminalID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTerminalID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (terminalID != null && terminalID.length() > 0)
			{
				terminalInfoData = terminalHandler.GetTerminalData(terminalID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			terminalInfoData = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (terminalInfoData != null)
		{
			logger.info(terminalInfoData.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTerminalInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		return terminalInfoData;
	}
	*/

	/// <summary>
	/// 指定端末の情報を取得する
	/// </summary>
	/// <param name="IPAdrress">ipadrress</param>
	/// <returns>端末情報</returns>
	@Override
	public TerminalInfoBean GetTerminalInfoDataByIPAdrress(String ipadrress, Connection con)
	{
		TerminalInfoBean terminalInfoData = null;
		TerminalHandler terminalHandler = new TerminalHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (ipadrress != null)
		{
			logger.info("[IPAddress]:" + ipadrress);
		}
		else
		{
			logger.info("[TerminalID] NULL");
		}
		//
		//
		//////////////////////////////

		try
		{
			if (ipadrress != null && ipadrress.length() > 0)
			{
				terminalInfoData = terminalHandler.GetTerminalDataByIPAddress(ipadrress, con);
			}
		}
		catch (Exception e)
		{
			terminalInfoData = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (terminalInfoData != null)
		{
			logger.info(terminalInfoData.toString());
		}
		else
		{
			logger.info("[TerminalInfoBean] NULL");
		}
		//
		//
		//////////////////////////////

		return terminalInfoData;
	}

	/*
	/// <summary>
	/// 指定端末の情報を保存する
	/// </summary>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
	@Override
	public boolean RegisterTerminalData(TerminalInfoBean terminalBean)
	{
		TerminalHandler terminalHandler = new TerminalHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (terminalBean == null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTerminalInfoBean_MessageString));
			rtnBool = false;
		}
		//
		//
		//////////////////////////////

		try
		{
			if (terminalBean != null)
			{
				rtnBool = terminalHandler.RegisterTerminalData(terminalBean);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示 2011.09.20 Del H.Orikasa NML-2-X04(修正)
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterTerminalData_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterTerminalData_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;
	}
	*/

	/*
	/// <summary>
	/// 一覧検索条件を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>一覧検索条件</returns>
	@Override
	public ArrayList GetSearchPatternInfo(OrderSearchParameter param)
	{
		ArrayList retList = new ArrayList();
		SearchPatternHandler searchPatternHandler = new SearchPatternHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (param != null)
			{
				if (!param.GetHistoryFlg())
				{
					//一覧検索条件の取得
					retList = searchPatternHandler.GetSearchPatternInfo(param);
				}
				else
				{
					//一覧検索条件の取得(履歴)
					retList = searchPatternHandler.GetSearchPatternInfoHistory(param);
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retList != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleArrayListCount_MessageString) + retList.Count);
			int count = retList.Count;
			if (count == 0)
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleZeroArrayList_MessageString));
			}
			for (int i = 0; i < count; i++)
			{
				logger.info(((SearchPatternInfoBean)retList[i]).toString());
			}
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullArrayList_MessageString));
		}
		//
		//
		//////////////////////////////

		return retList;
	}
	*/

	/*
	/// <summary>
	/// 一覧検索条件を更新する
	/// </summary>
	/// <param name="list">一覧検索条件</param>
	/// <param name="historyFlg">履歴フラグ</param>
	/// <returns>true:成功 false:失敗</returns>
	@Override
	public boolean UpdateSearchPatternInfo(ArrayList list, boolean historyFlg)
	{
		boolean retBool = false;
		SearchPatternHandler searchPatternHandler = new SearchPatternHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (list != null)
		{
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleArrayListCount_MessageString);
			msg += list.Count;
			logger.info(msg);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullArrayList_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (list != null)
			{
				if (!historyFlg)
				{
					//検索条件を更新する
					retBool = searchPatternHandler.UpdateSearchPatternList(list);
				}
				else
				{
					//検索条件を更新する(履歴)
					retBool = searchPatternHandler.UpdateSearchPatternListHistory(list);
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdateSearchPatternInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdateSearchPatternInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 一覧表示項目定義設定の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>一覧表示項目定義設定</returns>
	@Override
	public DataTable GetShowItemDefineData(OrderSearchParameter param)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		ShowItemDefineHandler showItemDefineHandler = new ShowItemDefineHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////


		// begin log
		logger.debug("begin");

		try
		{
			dt = showItemDefineHandler.GetShowItemDefineData(param);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	// 2011.08.03 Add NSK_S.Imai Start NML-CAT2-029
	/// <summary>
	/// 受付ダイアログ一覧表示項目定義設定の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>一覧表示項目定義設定</returns>
	@Override
	public DataTable GetReceiptDlgItemDefineData(OrderSearchParameter param)
	{
		// parameters
		DataTable dt = null;
		ShowItemDefineHandler showItemDefineHandler = new ShowItemDefineHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////


		// begin log
		logger.debug("begin");

		try
		{
			dt = showItemDefineHandler.GetReceiptDlgItemDefineData(param);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	// 2011.08.03 Add NSK_S.Imai End
	*/

	/*
	/// <summary>
	/// 一覧表示項目定義設定を登録する
	/// </summary>
	/// <param name="windowID">登録対象の画面ID</param>
	/// <param name="terminalID">登録対象の端末ID</param>
	/// <param name="dt">登録情報</param>
	///<param name="historyFlg">履歴フラグ</param>
	/// <returns></returns>
	@Override
	public boolean RegisterShowItemDefineData(String windowID, int terminalID, DataTable dt, boolean historyFlg)
	{
		ShowItemDefineHandler showItemDefineHandler = new ShowItemDefineHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleWindowAppID_MessageString) + windowID);
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTerminalID_MessageString) + terminalID);
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (windowID != null && terminalID != 0 && dt != null)
			{
				rtnBool = showItemDefineHandler.RegisterShowItemDefineData(windowID, terminalID, dt, historyFlg);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterShowItemDefineData_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterShowItemDefineData_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;
	}
	*/

	/*
	/// <summary>
	/// 実施詳細画面一覧表示項目定義設定を登録する
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="listType">一覧タイプ</param>
	/// <param name="dt">登録情報</param>
	/// <returns></returns>
	@Override
	public boolean RegisterOperationListItemDefineData(String kensatypeID, String listType, DataTable dt)
	{
		ShowItemDefineHandler showItemDefineHandler = new ShowItemDefineHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKensaTypeID_MessageString) + listType);
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleListType_MessageString) + listType);
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (kensatypeID != null && listType != null && dt != null)
			{
				rtnBool = showItemDefineHandler.RegisterOperationListItemDefineData(kensatypeID, listType, dt);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterOperationListItemDefineData_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterOperationListItemDefineData_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;
	}
	*/

	/*
	// 2011.08.03 Add NSK_S.Imai Start NML-CAT2-029
	/// <summary>
	/// 受付ダイアログ一覧表示項目定義設定を登録する
	/// </summary>
	/// <param name="windowID">登録対象の画面ID</param>
	/// <param name="terminalID">登録対象の端末ID</param>
	/// <param name="dt">登録情報</param>
	/// <returns>登録処理結果</returns>
	@Override
	public boolean RegisterReceiptDlgItemDefineData(String windowID, int terminalID, DataTable dt)
	{
		ShowItemDefineHandler showItemDefineHandler = new ShowItemDefineHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleWindowAppID_MessageString) + windowID);
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTerminalID_MessageString) + terminalID);
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (windowID != null && dt != null)
			{
				rtnBool = showItemDefineHandler.RegisterReceiptDlgItemDefineData(windowID, terminalID, dt);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterShowItemDefineData_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterShowItemDefineData_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;
	}
	// 2011.08.03 Add NSK_S.Imai End
	 */

	/*
	/// <summary>
	///拡張オーダ情報の取得
	/// </summary>
	/// <param name="risID">RIS ID</param>
	/// <returns>拡張オーダ情報</returns>
	@Override
	public DataTable GetRisExtendOrderInfo(String risID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		RisExtendOrderInfoTbl risExtendOrderInfoTbl = new RisExtendOrderInfoTbl();


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		// begin log
		logger.debug("begin");

		try
		{
			dt = risExtendOrderInfoTbl.SearchExtendOrderInfo(risID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// オーダ情報の更新
	/// </summary>
	/// <param name="orderInfoBean">オーダ情報</param>
	@Override
	public boolean UpdateRisOrderInfo(OrderInfoBean orderInfoBean)
	{
		logger.debug("begin");
		boolean ret = true;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (orderInfoBean != null)
		{
			logger.info(orderInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//オーダ情報の更新
			orderHandler.UpdateRisOrderInfo(orderInfoBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 実績操作履歴情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>実績操作履歴情報</returns>
	@Override
	public DataTable GetRisExamOperationHistory(String risID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		ResultHandler resultHandler = new ResultHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		try
		{
			dt = resultHandler.GetRisExamOperationHistory(risID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/// <summary>
	/// 実績操作履歴情報の登録
	/// </summary>
	/// <param name="bean">実績操作履歴情報</param>
	/// <returns>実績操作履歴情報</returns>
	@Override
	public boolean RegisterRisExamOperationHistory(ExamOperationHistoryInfoBean bean, Connection con)
	{
		// 進捗：完了

		// parameters
		boolean retBool = false;
		ResultHandler resultHandler = new ResultHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (bean != null)
		{
			logger.info(bean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExamOperationHistory_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			retBool = resultHandler.RegisterExamOperationHistory(bean, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterRisExamOperationHistory_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterRisExamOperationHistory_MessageString));
		}
		//
		//
		//////////////////////////////

		return retBool;
	}

	/// <summary>
	/// 祝日情報の取得
	/// </summary>
	/// <param name="date">日付</param>
	/// <returns>祝日情報</returns>
	@Override
	public DataTable GetHolidayDataTable(Timestamp date, Connection con)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (!Const.TIMESTAMP_MINVALUE.equals(date))
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDate_MessageString) + date);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDate_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			dt = masterHandler.GetHolidayDataTable(date, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.getRowCount());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}

	/// <summary>
	/// ユーザ情報の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>ユーザ情報</returns>
	@Override
	public DataTable GetUserInfo(UserSearchParameter param, Connection con)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		UserHandler userHandler = new UserHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullUserSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			dt = userHandler.GetUserInfo(param, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.getRowCount());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}

	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// チームユーザ情報の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>ユーザ情報</returns>
	@Override
	public DataTable GetTeamUserInfo(UserSearchParameter param)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		UserHandler userHandler = new UserHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullUserSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			dt = userHandler.GetTeamUserInfo(param);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	/*
	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	/// <summary>
	/// ユーザ情報の取得
	/// </summary>
	/// <param name="syokuinKbn">職員区分（空の場合は全て）</param>
	/// <returns>ユーザ情報</returns>
	@Override
	public DataTable GetUserMaster(String syokuinKbn)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		CommunicationMsgHandler communicationMsgHandler = new CommunicationMsgHandler();

		// begin log
		logger.debug("begin");

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (syokuinKbn != null)
		{
			logger.info(syokuinKbn);
		}
		//
		//
		//////////////////////////////

		try
		{
			dt = communicationMsgHandler.GetUserInfo(syokuinKbn);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	// 2011.02.16 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// HIS送信履歴情報の取得
	/// </summary>
	/// <returns>HIS送信履歴情報</returns>
	@Override
	public ToHisInfoBean GetToHisMaxDate(String risID)
	{
		// 進捗：完了

		// parameters
		ToHisInfoBean bean = null;
		OrderHandler orderHandler = new OrderHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////


		try
		{
			bean = orderHandler.GetToHisMaxDate(risID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			bean = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (bean != null)
		{
			logger.info(bean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullBean_MessageString));
		}
		//
		//
		//////////////////////////////

		return bean;
	}
	*/

	/*
	/// <summary>
	/// 器材情報を取得する
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="partsBunruiDt">(戻)器材区分情報</param>
	/// <param name="detailPartsDt">(戻)器材詳細区分情報</param>
	/// <param name="partsDt">(戻)器材情報</param>
	/// <param name="infuseDt">(戻)手技情報</param>
	@Override
	public void GetPartsMasterList(String kensatypeID,
		ref DataTable partsBunruiDt, ref DataTable detailPartsDt, ref DataTable partsDt, ref DataTable infuseDt)
	{
		// 進捗：完了

		// parameters
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (kensatypeID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKensaTypeID_MessageString) + kensatypeID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKensaTypeID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//器材マスタの取得
			masterHandler.GetPartsMasterList(
				kensatypeID,
				ref partsBunruiDt,
				ref detailPartsDt,
				ref partsDt,
				ref infuseDt);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (partsBunruiDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + partsBunruiDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		if (detailPartsDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + detailPartsDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		if (partsDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + partsDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		if (infuseDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + infuseDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////
	}
	*/

	/*
	// 2011.02.14 Add K.Shinohara Start
	/// <summary>
	/// 器材情報を取得する
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="partsBaseDt">器材情報(全検査種別)</param>
	/// <param name="infuseBaseDt">手技情報(全検査種別)</param>
	/// <param name="partsBunruiDt">(戻)器材区分情報</param>
	/// <param name="detailPartsDt">(戻)器材詳細区分情報</param>
	/// <param name="partsDt">(戻)器材情報</param>
	/// <param name="infuseDt">(戻)手技情報</param>
	@Override
	public void GetPartsMasterList(String kensatypeID, DataTable partsBaseDt, DataTable infuseBaseDt,
		ref DataTable partsBunruiDt, ref DataTable detailPartsDt, ref DataTable partsDt, ref DataTable infuseDt)
	{
		// 進捗：完了

		// parameters
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (kensatypeID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKensaTypeID_MessageString) + kensatypeID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKensaTypeID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//器材マスタの取得
			masterHandler.GetPartsMasterList(
				kensatypeID,
				partsBaseDt,
				infuseBaseDt,
				ref partsBunruiDt,
				ref detailPartsDt,
				ref partsDt,
				ref infuseDt);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (partsBunruiDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + partsBunruiDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		if (detailPartsDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + detailPartsDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		if (partsDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + partsDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		if (infuseDt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + infuseDt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////
	}
	// 2011.02.14 Add K.Shinohara End
	*/

	/// <summary>
	/// WorkListInfoをAccessionNoで削除する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="accessionNo">ACCESSIONNO</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <param name="typeIndex">操作タイプ</param>
	/// <returns></returns>
	@Override
	public boolean DeleteWorkListByAccesionNo(String risID, String accessionNo, String kikiID, Integer typeIndex, Connection con)
	{
		// 進捗：完了

		// parameters
		boolean retBool = false;
		ResultHandler resultHandler = new ResultHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[RisID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}

		if (accessionNo != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleAccessionNo_MessageString) + accessionNo);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullAccessionNo_MessageString));
		}
		if (kikiID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKensakikiID_MessageString) + kikiID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKensakikiID_MessageString));
		}
		if (typeIndex != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTypeIndex_MessageString) + typeIndex);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTypeIndex_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (risID.length() > 0 && accessionNo.length() > 0)							//2010.11.09 Mod H.Orikasa
			//if (risID.length() > 0 && accessionNo.length() > 0 && kikiID.length() > 0)	//
			{
				retBool = resultHandler.DeleteWorkListInfo(risID, accessionNo, kikiID, typeIndex, con);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/*
	/// <summary>
	/// WorkListInfoをAETitleで削除する
	/// </summary>
	/// <param name="AETitle">AEタイトル</param>
	/// <returns></returns>
	@Override
	public boolean DeleteWorkListByAETitle(String aeTitle)
	{
		// 進捗：完了

		// parameters
		boolean retBool = false;
		ResultHandler resultHandler = new ResultHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (aeTitle != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleAETitle_MessageString) + aeTitle);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullAETitle_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (aeTitle.length() > 0)
			{
				retBool = resultHandler.DeleteWorkListInfo(aeTitle);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/// <summary>
	/// 実績情報の保存を行う
	/// </summary>
	/// <param name="bean">実績情報</param>
	/// <param name="patientCommentList">患者コメントリスト</param>
	/// <returns></returns>
	@Override
	public boolean SaveRisExecutionInfo(ExecutionInfoBean bean, ArrayList patientCommentList, Connection con)
	{
		// 進捗：完了

		// parameters
		boolean retBool = false;
		ResultHandler resultHandler = new ResultHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (bean != null)
		{
			logger.info(bean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (bean != null && patientCommentList != null)
			{
				retBool = resultHandler.SaveRisExecutionInfo(bean, patientCommentList, con);
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKSaveRisExecutionInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGSaveRisExecutionInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retBool;
	}

	/// <summary>
	/// プリセット情報の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>プリセット情報リスト</returns>
	@Override
	public ArrayList GetRisPresetInfoData(PresetParameter param, Connection con)
	{
		// 進捗：完了

		// parameters
		ArrayList retList = new ArrayList();
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPresetParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			retList = masterHandler.GetPresetInfoData(param, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retList != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleArrayListCount_MessageString) + retList.size());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleZeroArrayList_MessageString));
		}
		//
		//
		//////////////////////////////

		return retList;
	}

	/// <summary>
	/// プリセット撮影情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>プリセット撮影情報</returns>
	@Override
	public ArrayList GetRisPresetSatueiData(PresetParameter param, Connection con)
	{
		// 進捗：完了

		// parameters
		ArrayList retList = new ArrayList();
		MasterHandler masterHandler = new MasterHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPartsSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			retList = masterHandler.GetPresetSatueiData(param, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retList != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleArrayListCount_MessageString) + retList.size());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleZeroArrayList_MessageString));
		}
		//
		//
		//////////////////////////////

		return retList;
	}

	/*
	/// <summary>
	/// 器材情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>器材情報</returns>
	@Override
	public DataTable GetRisPartsDataTable(PartsSearchParameter param)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MasterHandler masterHandler = new MasterHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPartsSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			dt = masterHandler.GetPartsDataTable(param);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 手技情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>手技情報</returns>
	@Override
	public DataTable GetRisInfuseDataTable(PartsSearchParameter param)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MasterHandler masterHandler = new MasterHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPartsSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			dt = masterHandler.GetInfuseDataTable(param);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/// <summary>
	/// CR撮影メニュー情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>CR撮影メニュー情報</returns>
	@Override
	public DataTable GetRisSatueiMenuDataTable(RequestParameter param, Connection con)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MasterHandler masterHandler = new MasterHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullRequestParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			dt = masterHandler.GetSatueiMenuDataTable(param, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.getRowCount());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}

	/// <summary>
	/// 実績情報の検査日、ステータスと拡張情報の検像ステータスを更新する
	/// </summary>
	/// <param name="updExmainFlg">実績メイン情報更新可否フラグ</param>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisExecutionKensaDateStatus(boolean updExmainFlg, ExecutionInfoBean exBean, Connection con)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			ret = resultHandler.UpdateRisExecutionKensaDateStatus(updExmainFlg, exBean, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}

	/*
	/// <summary>
	/// 実績情報の連絡メモを更新する
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisExecutionRenrakuMemo(ExecutionInfoBean exBean)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			ret = resultHandler.UpdateRisExecutionRenrakuMemo(exBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/// <summary>
	/// 実績情報を更新する(検査開始)
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisKensaStartProc(ExecutionInfoBean exBean, Connection con)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//実績情報を更新する(検査開始)
			ret = resultHandler.UpdateRisKensaStartProc(exBean, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}

	/*
	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean RestoreRisKensaData(ExecutionInfoBean exBean)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//実績情報を元に戻す
			ret = resultHandler.RestoreRisKensaData(exBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	// 2011.08.16 Add DD.Chinh@MERX Start NML-CAT2-010
	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UnReceiptData(ExecutionInfoBean exBean)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//実績情報を元に戻す
			ret = resultHandler.UnReceiptData(exBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	// 2011.08.16 Add DD.Chinh@MERX End
	*/

	/// <summary>
	/// 実績情報を更新する(検査終了)
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <param name="patientCommentList">患者コメントリスト</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean UpdateRisKensaFinishProc(ExecutionInfoBean exBean, ArrayList patientCommentList, Connection con)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//実績情報を更新する(検査終了)
			ret = resultHandler.UpdateRisKensaFinishProc(exBean, patientCommentList, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}

	/*
	/// <summary>
	/// 認証を行う
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="password">パスワード</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean CheckUserIDPassword(String userID, String password)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (userID != null)
		{
			logger.info("[String]:" + userID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullUserID_MessageString));
		}
		//
		//
		//////////////////////////////
		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (password != null)
		{
			logger.info("[String]:" + password);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPassword_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			// 認証
			UserHandler userHandler = new UserHandler();
			retFlg = userHandler.CheckUserIDPassword(userID, password);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.loginException_MessageString);
			logger.fatal(message, e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKCheckUserIDPassword_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGCheckUserIDPassword_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	// 2011.08.11 Add NSK_T.Koudate Start NML-CAT2-004
	/// <summary>
	/// 認証を行う(ログインはしない)
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="password">パスワード</param>
	/// <param name="userAccount">返されるユーザ情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean CheckUserIDPassword(String userID, String planePassword,
		ref UserAccountBean userAccount)
	{
		// parameters
		boolean loginFlg = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (userID != null)
		{
			logger.info("[String]:" + userID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullUserID_MessageString));
		}
		//
		//
		//////////////////////////////
		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (planePassword != null)
		{
			logger.info("[String]:" + planePassword);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPlanePassword_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			// ログインに成功したら、ConfigurationにUserAccountBeanをSETする
			UserHandler userHandler = new UserHandler();
			userAccount = userHandler.Login(userID, planePassword);

			loginFlg = userAccount.GetLoginFlag();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.loginException_MessageString);
			logger.fatal(message, e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (loginFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKCheckUserIDPassword_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGCheckUserIDPassword_MessageString));
		}
		//
		//
		//////////////////////////////

		return loginFlg;
	}
	// 2011.08.11 Add NSK_T.Koudate End
	*/

	/// <summary>
	/// ToHisInfoへ情報を登録する(検査終了時)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <param name="reSendFlg">再送信フラグ</param>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	@Override
	public boolean RegisterToHisInfoByOperation(OrderInfoBean orderBean, TerminalInfoBean terminalBean, boolean reSendFlg, String status, Connection con)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (orderBean != null)
		{
			logger.info(orderBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderInfoBean_MessageString));
		}
		if (terminalBean != null)
		{
			logger.info(terminalBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTerminalInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//実績情報を更新する(検査終了)
			ret = resultHandler.RegisterToHisInfoByOperation(orderBean, terminalBean, reSendFlg, status, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}

	/*
	/// <summary>
	/// ToHisInfoへ情報を登録する(患者)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="patientBean">患者情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
	@Override
	public boolean RegisterToHisInfoByPatient(OrderInfoBean orderBean, PatientInfoBean patientBean, TerminalInfoBean terminalBean)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (orderBean != null)
		{
			logger.info(orderBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderInfoBean_MessageString));
		}
		if (patientBean != null)
		{
			logger.info(patientBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientInfoBean_MessageString));
		}
		if (terminalBean != null)
		{
			logger.info(terminalBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTerminalInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//ToHisInfoへ情報を登録する(患者)
			ret = resultHandler.RegisterToHisInfoByPatient(orderBean, patientBean, terminalBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/// <summary>
	/// ToHisInfoへ情報を登録する(検査終了時)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <param name="reSendFlg">再送信フラグ</param>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	@Override
	public boolean RegisterToReportPacsInfoByOperation(OrderInfoBean orderBean, TerminalInfoBean terminalBean, boolean reSendFlg, String status, Connection con)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (orderBean != null)
		{
			logger.info(orderBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderInfoBean_MessageString));
		}
		if (terminalBean != null)
		{
			logger.info(terminalBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTerminalInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//ToReportInfo, ToPacsInfoへ情報を登録する
			ret = resultHandler.RegisterToReportPacsInfoByOperation(orderBean, terminalBean, reSendFlg, status, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}

	/*
	/// <summary>
	/// 優先フラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="oldYuusenFlg">旧優先フラグ</param>
	/// <param name="newYuusenFlg">新設定フラグ</param>
	/// <returns></returns>
	@Override
	public boolean UpdateRisYuusenFlg(String risID, String appID, String oldYuusenFlg, String newYuusenFlg)
	{
		boolean retBool = false;

		logger.debug("begin");
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////
		try
		{
			//優先フラグを更新する
			retBool = resultHandler.UpdateRisYuusenFlg(risID, appID, oldYuusenFlg, newYuusenFlg);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retBool = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 感染症フラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="infectionFlg">感染症フラグ</param>
	/// <returns></returns>
	@Override
	public boolean UpdateRisInfectionFlg(String risID, String appID, String infectionFlg)
	{
		boolean retBool = false;

		logger.debug("begin");
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////
		try
		{
			//感染症フラグを更新する
			retBool = resultHandler.UpdateRisInfectionFlg( risID, appID, infectionFlg);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retBool = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 同意書チェックフラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="douishoFlg">同意書チェックフラグ</param>
	/// <returns></returns>
	@Override
	public boolean UpdateRisDouishoFlg(String risID, String appID, String douishoFlg)
	{
		boolean retBool = false;

		logger.debug("begin");
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////
		try
		{
			//同意書フラグを更新する
			retBool = resultHandler.UpdateRisDouishoFlg(risID, appID, douishoFlg);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retBool = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return retBool;
	}
	*/

	/*
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	/// <summary>
	/// 薬剤発注チェックフラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="pharmaFlg">薬剤発注チェックフラグ</param>
	/// <returns></returns>
	@Override
	public boolean UpdateRisPharmaFlg(String risID, String appID, String pharmaFlg)
	{
		boolean retBool = false;

		logger.debug("begin");
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////
		try
		{
			//薬剤発注フラグを更新する
			retBool = resultHandler.UpdateRisPharmaFlg(risID, appID, pharmaFlg);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retBool = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return retBool;
	}
	// 2010.11.16 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// 確保処理を行う
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="terminalID">端末ID</param>
	/// <returns></returns>
	@Override
	public boolean UpdateRisKakuho(String risID, String appID, String terminalID)
	{
		boolean retBool = false;

		logger.debug("begin");
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////
		try
		{
			//確保処理を行う
			retBool = resultHandler.UpdateRisKakuho(risID, appID, terminalID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retBool = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return retBool;
	}
	*/

	/*
	/// <summary>
	/// オーダを削除する(OrderMainTable.Staus=-9にする)
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
	@Override
	public int DeleteOrderData(String risID)
	{

		int rstInt = 0;
		ResultHandler resultHandler = new ResultHandler();
		boolean rstBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		//
		//
		//////////////////////////////

		try
		{
			if (risID != null && risID.length() > 0)
			{

				//レコードの存在有無チェック
				RisOrderMainTbl orderMainTbl = new RisOrderMainTbl();
				OrderInfoBean orderInfoBean = GetRisOrderInfo(risID);

				if (orderInfoBean == null)
				{
					//「該当レコードが存在しません」
					rstBool = false;
					rstInt = 1;
				}
				else
				{
					rstBool = true;	//削除可能
				}

				if (rstBool == true)
				{
					//削除
					rstBool = resultHandler.DeleteOrderData(risID);

					//失敗
					if (!rstBool)
					{
						rstInt = -1;
					}
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rstInt = -1;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return rstInt;

	}
	*/

	/*
	/// <summary>
	/// オーダ情報を登録する
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="indicateBean">オーダ指示情報</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean RegisterRisOrderInfo(OrderInfoBean orderBean, OrderIndicateBean indicateBean, ExecutionInfoBean exBean)
	{
		logger.debug("begin");
		boolean ret = false;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (orderBean != null)
		{
			logger.info(orderBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderInfoBean_MessageString));
		}
		if (orderBean.GetExtendOrderInfoBean() != null)
		{
			logger.info(orderBean.GetExtendOrderInfoBean().toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExtendOrderInfoBean_MessageString));
		}
		if (indicateBean != null)
		{
			logger.info(indicateBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderIndicateBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//実績情報を更新する(検査終了)
			ret = orderHandler.RegisterRisOrderInfo(orderBean, indicateBean, exBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 患者情報を登録する
	/// </summary>
	/// <param name="patientBean">患者情報</param>
	/// <returns>成功：true　失敗：false</returns>
	@Override
	public boolean RegisterRisPatientInfo(PatientInfoBean patientBean)
	{
		logger.debug("begin");
		boolean ret = false;
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientBean != null)
		{
			logger.info(patientBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//患者情報を登録する
			ret = patientHandler.RegisterRisPatientInfo(patientBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// ToReportInfo,ToPacsInfoへ情報を登録する(オーダ登録時)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
	@Override
	public String RegisterToReportPacsInfoByCreateOrder(OrderInfoBean orderBean, TerminalInfoBean terminalBean)
	{
		logger.debug("begin");
		String retStr = "";
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (orderBean != null)
		{
			logger.info(orderBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderInfoBean_MessageString));
		}
		if (terminalBean != null)
		{
			logger.info(terminalBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTerminalInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//ToReportInfoへ情報を登録する(オーダ登録時)
			ret = resultHandler.RegisterToReportInfoByCreateOrder(orderBean, terminalBean);
			if (!ret)
			{
				String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerToReportInfoException_MessageString);
				retStr += msg;
			}

			//ToPacsInfoへ情報を登録する(オーダ登録時)
			ret = resultHandler.RegisterToPacsInfoByCreateOrder(orderBean, terminalBean);
			if (!ret)
			{
				String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerToPacsInfoException_MessageString);
				if (retStr.length() <= 0)
				{
					retStr += msg;
				}
				else
				{
					retStr += "\r\n" + msg;
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retStr = e.Message;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return retStr;
	}
	*/

	/*
	/// <summary>
	/// 他検査一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
	@Override
	public DataTable GetRisOtherKensaList(OrderSearchParameter param)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				dt = orderHandler.GetRisOtherKensaList(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// (治療DB)他検査一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
	@Override
	public DataTable GetRtrisOtherKensaList(OrderSearchParameter param)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				dt = orderHandler.GetRtrisOtherKensaList(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// RIS実績情報の指示情報を更新する
	/// </summary>
	/// <param name="appID">画面ID</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
	@Override
	public boolean UpdateRisSijiInfo(String appID, ExecutionInfoBean exBean)
	{
		// 進捗：済み

		// parameters
		boolean retFlg = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (appID.length() > 0 && exBean != null && exBean.GetRisID().length() > 0)
			{
				try
				{
					//指示情報を更新する
					retFlg = resultHandler.UpdateRisSijiInfo(appID, exBean);
				}
				finally
				{
					//実績情報をアンチェックアウトする
					UncheckoutRisData(exBean.GetRisID());
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info("ChangeOrderStatus [OK]");
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGChangeOrderStatus_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
    /// <summary>
	/// RIS実績情報の日時情報を更新する
	/// </summary>
    /// <param name="exBean">実績情報</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
	@Override
    public boolean UpdateRisExamTimestamp(ExecutionInfoBean exBean)
    {
        // 進捗：済み

        // parameters
        boolean retFlg = false;
        ResultHandler resultHandler = new ResultHandler();

        //////////////////////////////
        // 入力パラメータのデバッグ出力
        //
        if (exBean != null)
        {
            logger.info(exBean.toString());
        }
        else
        {
            logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
        }
        //
        //
        //////////////////////////////

        // begin log
        logger.debug("begin");

        try
        {
            if (exBean != null && exBean.GetRisID().length() > 0)
            {
                //日時情報を更新する
                retFlg = resultHandler.UpdateRisExamTimestamp(exBean);
            }
        }
        catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
            retFlg = false;
            logger.fatal(e);
        }

        // end log
        logger.debug("end");

        //////////////////////////////
        // 出力パラメータのデバッグ出力
        //
        if (retFlg)
        {
            logger.info("ChangeOrderStatus [OK]");
        }
        else
        {
            logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGChangeOrderStatus_MessageString));
        }
        //
        //
        //////////////////////////////

        return retFlg;
    }
	*/

	/*
	/// <summary>
	/// RISオーダ情報のステータスを変更する
	/// </summary>
	/// <param name="risID">ステータス変更対象オーダの内部UID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="status">変更後のステータス</param>
	/// <param name="user">更新ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
	@Override
	public boolean ChangeRisOrderStatusNoCheckout(String risID, String appID, String status, UserAccountBean user, String roomID, String kikiID)
	{
		// 進捗：済み

		// parameters
		boolean retFlg = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (risID != null)
		{
			logger.info("[UserID] :" + risID);
		}
		else
		{
			logger.info("[RisID] NULL");
		}
		if (status != null)
		{
			logger.info("[String]:" + status);
		}
		else
		{
			logger.info("[Status] NULL");
		}
		if (user != null)
		{
			logger.info(user.toString());
		}
		else
		{
			logger.info("[AdmissionUser] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0 && status != null && user != null)
			{
				// オーダー情報ステータスを更新
				retFlg = resultHandler.UpdateRisExecutionResultStatus(risID, status, user, roomID, kikiID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info("ChangeRisOrderStatusNoCheckout [OK]");
		}
		else
		{
			logger.info("ChangeRisOrderStatusNoCheckout [NG]");
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// テーブル定義情報取得
	/// </summary>
	/// <param name="tabName">テーブル名称</param>
	/// <param name="mwmFlg">MWMフラグ</param>
	/// <returns>テーブル定義情報</returns>
	@Override
	public DataTable GetUserTabColumnsData(String tabName, boolean mwmFlg)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MasterHandler masterHandler = new MasterHandler();

		// begin log
		logger.debug("begin");

		try
		{
			if (tabName != null && tabName.length() > 0)
			{
				if (!mwmFlg)
				{
					//列情報を取得する(ローカル用のDB)
					dt = masterHandler.GetUserTableColumns(tabName);
				}
				else
				{
					//列情報を取得する(MWM用のDB)
					dt = masterHandler.GetMwmUserTableColumns(tabName);
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// WorkListInfoを取得する
	/// </summary>
	/// <param name="studyID">Study_Instance_UID</param>
	/// <param name="conBean">MWM接続情報</param>
	/// <returns></returns>
	@Override
	public DataTable SelectWorkListInfo(String studyID, ConnectionInfoBean conBean)
	{
		DataTable retDt = null;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleStudyID_MessageString) + studyID);
		//
		//
		//////////////////////////////

		try
		{
			if (studyID.length() > 0 && conBean != null)
			{
				//MWMライブラリ生成
				ArrayList logList = new ArrayList();
				MwmManager mManager = new MwmManager(conBean, ref logList);

				//MWMDB接続チェック
				if (!mManager.CheckMWMDBConnection(ref logList))
				{
					//接続失敗
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mwmdbConnectNo_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
					logger.fatal(message);
				}
				else
				{
					//MWM情報を取得する
					retDt = mManager.GetMWMInfo(studyID, ref logList);
				}

				//MWM処理ログを出力する(必須)
				if (logList != null)
				{
					for (int i = 0; i < logList.Count; i++)
					{
						String logStr = logList[i].toString();
						logger.info(logStr);
					}
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/// <summary>
	/// WorkListInfoへ登録を行う
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="conBean">MWM接続情報</param>
	/// <param name="deleteMode">削除モード</param>
	/// <returns></returns>
	@Override
	public boolean RegisterWorkListInfo(ArrayList mwmBeanList, ConnectionInfoBean conBean, String deleteMode)
	{
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (mwmBeanList != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleArrayListCount_MessageString) + mwmBeanList.size());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullArrayList_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (mwmBeanList != null && mwmBeanList.size() > 0 && conBean != null)
			{
				//MWMライブラリ生成
				ArrayList logList = new ArrayList();
				MwmManager mManager = new MwmManager(conBean, logList);

				//MWMDB接続チェック
				if (!mManager.CheckMWMDBConnection(logList))
				{
					//接続失敗
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mwmdbConnectNo_MessageString);
					//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
					logger.fatal(message);
					rtnBool = false;
				}
				else
				{
					//MWM書き込み
					if (mManager.RegisterMWMInfo(mwmBeanList, logList, deleteMode))
					{
						rtnBool = true;
					}
					else
					{
						rtnBool = false;
					}
				}

				//MWM処理ログを出力する(必須)
				if (logList != null)
				{
					for (int i = 0; i < logList.size(); i++)
					{
						String logStr = logList.get(i).toString();
						logger.info(logStr);
					}
				}
			}
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return rtnBool;
	}

	/*
	/// <summary>
	/// ユーザ情報の取得
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <returns>ユーザ情報</returns>
	@Override
	public DataRow GetUserManage(String userID)
	{
		// 進捗：完了

		// parameters
		DataRow retRow = null;
		UserHandler userHandler = new UserHandler();

		// begin log
		logger.debug("begin");


		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleUserID_MessageString) + userID);
		//
		//
		//////////////////////////////

		try
		{
			//ユーザ情報を取得する
			retRow = userHandler.GetUserManageDataRow(userID);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retRow;
	}
	*/

	/*
	/// <summary>
	/// ユーザ情報の更新
	/// </summary>
	/// <param name="userBean">ユーザ情報</param>
	/// <returns>ユーザ情報</returns>
	@Override
	public boolean UpdateUserManage(UserAccountBean userBean)
	{
		// 進捗：完了

		// parameters
		boolean retBool = false;
		UserHandler userHandler = new UserHandler();

		// begin log
		logger.debug("begin");

		try
		{
			if (userBean != null && userBean.GetUserID().length() > 0)
			{
				//ユーザ情報を更新する
				retBool = userHandler.UpdateUserManageData(userBean);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// テーブル情報を取得する
	/// </summary>
	/// <param name="tableName">テーブル名</param>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
	@Override
	public DataTable GetRisTableInfo(String tableName, String kanjaID, String risID)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MasterHandler mstHandler = new MasterHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTableName_MessageString) + tableName);
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKanjaID_MessageString) + kanjaID);
		logger.info("[UserID] :" + risID);
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (tableName.length() > 0 && (kanjaID.length() > 0 || risID.length() > 0))
			{
				dt = mstHandler.GetTableTable(tableName, kanjaID, risID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 検像情報更新を行う
	/// </summary>
	/// <param name="extendExamInfoBean">拡張実績情報</param>
	/// <param name="bikouStr">コメント</param>
	/// <returns></returns>
	@Override
	public boolean UpdateKenzouOperation(ExtendExamInfoBean extendExamInfoBean, String bikouStr)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (extendExamInfoBean != null)
		{
			logger.info(extendExamInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExtendExamInfoBean_MessageString));
		}
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleComment_MessageString) + bikouStr);
		//
		//
		//////////////////////////////

		try
		{
			//検像情報更新を行う
			ret = resultHandler.UpdateKenzouOperation(extendExamInfoBean, bikouStr);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 治療DBの患者共通コメントを検索する
	/// </summary>
	/// <param name="patientID">患者ID</param>
	/// <returns>患者検索結果一覧</returns>
	@Override
	public String GetRrisPatientComment(String patientID)
	{
		// parameters
		String retStr = "";
		PatientHandler patientHandler = new PatientHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientID != null)
		{
			logger.info("[String]:" + patientID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (patientID != null && patientID.length() > 0)
			{
				//治療DBの患者共通コメントを検索する
				retStr = patientHandler.GetRrisPatientComment(patientID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retStr;
	}
	*/

	/*
	/// <summary>
	/// 指定されたオーダの進捗（引数のrisID）を取得する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>進捗</returns>
	@Override
	public String GetRisStatus(String risID)
	{
		// parameters
		String status = "";
		ResultHandler resultHandler = new ResultHandler();

		// begin log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0)
			{
				status = resultHandler.GetRisStatus(risID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return status;
	}
	*/

	/*
	/// <summary>
	/// SystemParam2の値を更新する
	/// </summary>
	/// <param name="mainID">メインキー</param>
	/// <param name="subID">サブキー</param>
	/// <param name="column">列名</param>
	/// <param name="valueStr">値</param>
	/// <returns></returns>
	@Override
	public boolean UpdateSystemParam2Value(String mainID, String subID, String column, String valueStr)
	{
		logger.debug("begin");
		boolean ret = false;
		MasterHandler masterHandler = new MasterHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (mainID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleMainID_MessageString) + mainID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullMainID_MessageString));
		}
		if (subID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleSubID_MessageString) + subID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullSubID_MessageString));
		}
		if (column != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleColumn_MessageString) + column);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullColumn_MessageString));
		}
		if (valueStr != null)
		{
			logger.info("[String]:" + valueStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullString_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			//SystemParam2の値を更新する
			ret = masterHandler.UpdateSystemParam2Value(mainID, subID, column, valueStr);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (ret)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdateSystemParam2Value_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdateSystemParam2Value_MessageString));
		}
		//
		//
		//////////////////////////////

		return ret;
	}
	*/

	/*
	/// <summary>
	/// 業務詳細過去情報の件数を取得する
	/// </summary>
	/// <param name="oParam">検索条件</param>
	/// <returns>件数</returns>
	@Override
	public int GetRrisGyomuOldOrderDataCount(OrderSearchParameter oParam)
	{
		// parameters
		int retInt = 0;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (oParam != null)
		{
			logger.info(oParam.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (oParam != null)
			{
				//業務詳細過去情報の件数を取得する
				retInt = orderHandler.GetRisGyomuOldOrderDataCount(oParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retInt;
	}
	*/



	// 帳票の関数郡

	/*
	/// <summary>
	/// 帳票用-オーダ情報Hashの取得
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>オーダ情報</returns>
	@Override
	public Hashtable GetPrintOrderDataHash(PrintParameter printParam)
	{
		// 進捗：完了

		// parameters
		Hashtable retHash = null;
		PrintHandler printHandler = new PrintHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (printParam != null)
		{
			logger.info(printParam.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.info("begin");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");		//

		try
		{
			//帳票用-オーダ情報の取得
			retHash = printHandler.GetPrintOrderDataHash(printParam);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("end");	//

		return retHash;
	}
	*/

	/*
	/// <summary>
	/// 受付票の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintReceiptReport(PrintParameter printParam)
	{
		logger.info("begin");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");		//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			//受付票の印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintReceiptReport(printParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 依頼票の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintRequestReport(PrintParameter printParam)
	{
		logger.info("begin");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");		//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			//依頼票の印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintRequestReport(printParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 患者袋ラベルの印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintLabelKanjaReport(PrintParameter printParam)
	{
		logger.info("begin");	// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");	//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			//患者袋ラベルの印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintLabelKanjaReport(printParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 検査袋ラベルの印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintLabelKensaReport(PrintParameter printParam)
	{
		logger.info("begin");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");		//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			//検査袋ラベルの印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintLabelKensaReport(printParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 実績票の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintExReport(PrintParameter printParam)
	{
		logger.info("begin");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");		//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			//実績票の印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintExReport(printParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 予定一覧の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintScheduleListReport(PrintParameter printParam)
	{
		logger.info("begin");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");		//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			//予定一覧の印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintScheduleListReport(printParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
		}
		return ret;
	}
	*/

	/*
	/// <summary>
	/// 検査台帳の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintOrderListReport(PrintParameter printParam)
	{
		logger.info("begin");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");		//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			//検査台帳の印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintOrderListReport(printParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
		}
		return ret;
	}
	*/

	/*
	// 2010.06.23 Add T.Nishikubo Start
	/// <summary>
	/// 業務日誌の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <param name="diaryBtn">日誌1/日誌2</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
	@Override
	public boolean PrintDiaryReport(PrintParameter printParam, String diaryBtn)
	{
		logger.info("begin");	// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
		//logger.debug("begin");	//
		boolean ret = true;
		CoReportsManager coReportsManager = new CoReportsManager();

		// 2011.08.05 Add DD.Chinh@MERX Start NML-1-X03
		if (!coReportsManager.GetPreviewPathBool())
		{
			//帳票プレビューファイル保存用のパスが存在しない場合、印刷処理を終了する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.reportTempFolderNotFound_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
			ret = false;
			return ret;
		}
		// 2011.08.05 Add DD.Chinh@MERX End

		try
		{
			//////////////////////////////
			// 入力パラメータのデバッグ出力
			//
			if (printParam != null)
			{
				logger.info(printParam.toString());
			}
			else
			{
				logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPrintParameter_MessageString));
			}
			//
			//
			//////////////////////////////

			// 業務日誌の印刷
			if (printParam != null)
			{
				ret = coReportsManager.PrintDiaryReport(printParam, diaryBtn);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			coReportsManager = null;
			logger.info("end");		// 2011.08.16 Mod T.Ootsuka@MERX NML-1-X04(問題点修正)
			//logger.debug("end");	//
		}
		return ret;
	}
	// 2010.06.23 Add T.Nishikubo End
	*/


	// MPPSの関数郡
	/*
	/// <summary>
	/// MPPS-MPPS定義情報Hashの取得
	/// </summary>
	/// <returns>オーダ情報</returns>
	@Override
	public Hashtable GetMppsDefineHash()
	{
		// 進捗：完了

		// parameters
		Hashtable retHash = null;
		MppsHandler mppsHandler = new MppsHandler();

		// begin log
		logger.debug("begin");

		try
		{
			//MPPS定義情報Hashの取得
			retHash = mppsHandler.GetMppsDefineHash();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retHash;
	}
	*/

	/*
	/// <summary>
	/// 検査系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="accessionNo">AccessionNo</param>
	/// <returns>検査系-MPPSマスタ情報</returns>
	@Override
	public DataTable GetKensaMppsMasterData(String accessionNo)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MppsHandler mppsHandler = new MppsHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (accessionNo != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleAccessionNo_MessageString) + accessionNo);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullAccessionNo_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			//検査系-MPPSマスタ情報を取得する
			dt = mppsHandler.GetKensaMppsMasterData(accessionNo);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			//エラーメッセージ表示
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mppsKensaGetMppsException_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 検査系-MPPS照射情報を取得する
	/// </summary>
	/// <param name="accessionNo">AccessionNo</param>
	/// <returns>検査系-MPPS照射情報</returns>
	@Override
	public DataTable GetKensaMppsExposureDoseData(String accessionNo)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MppsHandler mppsHandler = new MppsHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (accessionNo != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleAccessionNo_MessageString) + accessionNo);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullAccessionNo_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			//検査系-MPPS照射情報を取得する
			dt = mppsHandler.GetKensaMppsExposureDoseData(accessionNo);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			//エラーメッセージ表示
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mppsKensaGetMppsSateiException_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);

			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/// <summary>
	/// 撮影系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="accessionNo">AccessionNo</param>
	/// <returns>撮影系-MPPSマスタ情報</returns>
	@Override
	public DataTable GetSatueiMppsMasterData(String accessionNo, Connection con)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MppsHandler mppsHandler = new MppsHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (accessionNo != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleAccessionNo_MessageString) + accessionNo);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullAccessionNo_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			//撮影系-MPPSマスタ情報を取得する
			dt = mppsHandler.GetSatueiMppsMasterData(accessionNo, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.getRowCount());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}

	/// <summary>
	/// 撮影系-MPPS実績情報を取得する
	/// </summary>
	/// <param name="sopInstanceUID">MPPSSOPInstanceUID</param>
	/// <returns>撮影系-MPPS実績情報</returns>
	@Override
	public DataTable GetSatueiMppsResultData(String sopInstanceUID, Connection con)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		MppsHandler mppsHandler = new MppsHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (sopInstanceUID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleMPPSSOPInstanceUID_MessageString) + sopInstanceUID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullMPPSSOPInstanceUID_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			//撮影系-MPPS実績情報を取得する
			dt = mppsHandler.GetSatueiMppsResultData(sopInstanceUID, con);
		}
		catch (Exception e)
		{
			//ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.getRowCount());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}

	/*
	/// <summary>
	/// 一覧デザイン設定の取得
	/// </summary>
	/// <param name="windowAppID">画面ID</param>
	/// <param name="terminalID">端末ID</param>
	/// <param name="listType">一覧タイプ</param>
	/// <returns>一覧表示項目定義設定</returns>
	@Override
	public DataTable GetShowListDefineData(String windowAppID, int terminalID, String listType)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		ShowListDefineHandler showListDefineHandler = new ShowListDefineHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleWindowAppID_MessageString)	+ windowAppID);
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTerminalID_MessageString)		+ terminalID);
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleListType_MessageString)		+ listType);
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			dt = showListDefineHandler.GetShowListDefineData(windowAppID, terminalID, listType);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 一覧デザイン設定を登録する
	/// </summary>
	/// <param name="styleRow">デザイン情報</param>
	/// <returns></returns>
	@Override
	public boolean RegisterShowListDefineData(DataRow styleRow)
	{
		ShowListDefineHandler showListDefineHandler = new ShowListDefineHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (styleRow == null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataRow_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (styleRow != null)
			{
				rtnBool = showListDefineHandler.RegisterShowListDefineData(styleRow);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterShowListDefineData_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterShowListDefineData_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;
	}
	*/

	/*
	/// <summary>
	/// ソート条件パターン設定を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>ソート条件パターン設定</returns>
	@Override
	public DataTable GetSortPatternInfo(OrderSearchParameter param)
	{
		DataTable dt = null;
		SortPatternHandler sortPatternHandler = new SortPatternHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (param != null)
			{
				if (!param.GetHistoryFlg())
				{
					//ソート条件パターン設定の取得
					dt = sortPatternHandler.GetSortPatternInfo(param);
				}
				else
				{
					//ソート条件パターン設定の取得(履歴)
					dt = sortPatternHandler.GetSortPatternInfoHistory(param);
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	/// <summary>
	/// ソート条件パターン設定を登録する
	/// </summary>
	/// <param name="auditBean">ソート条件パターン設定</param>
	/// <returns>判定</returns>
	@Override
	public boolean RegisterSortPatternInfo(DataRow row)
	{
		// parameters
		boolean retFlg = false;
		SortPatternHandler sortPatternHandler = new SortPatternHandler();

		// begin log
		logger.debug("begin");

		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (row == null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataRow_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (row != null)
			{
				//ソート条件パターン設定を登録する
				retFlg = sortPatternHandler.RegisterSortPatternInfo(row);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterSortPatternInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterSortPatternInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// ソート条件パターン設定を更新する
	/// </summary>
	/// <param name="auditBean">ソート条件パターン設定</param>
	/// <param name="historyFlg">履歴フラグ</param>
	/// <returns>判定</returns>
	@Override
	public boolean UpdateSortPatternInfo(DataRow row, boolean historyFlg)
	{
		// parameters
		boolean retFlg = false;
		SortPatternHandler sortPatternHandler = new SortPatternHandler();

		// begin log
		logger.debug("begin");

		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (row == null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataRow_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (row != null)
			{
				if (!historyFlg)
				{
					//ソート条件パターン設定を更新する
					retFlg = sortPatternHandler.UpdateSortPatternInfo(row);
				}
				else
				{
					retFlg = sortPatternHandler.UpdateSortPatternInfoHistory(row);
				}
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdateSortPatternInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdateSortPatternInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// ソート条件パターン設定を削除する
	/// </summary>
	/// <param name="auditBean">ソート条件パターン設定</param>
	/// <returns>判定</returns>
	@Override
	public boolean DeleteSortPatternInfo(int patternID)
	{
		// parameters
		boolean retFlg = false;
		SortPatternHandler sortPatternHandler = new SortPatternHandler();

		// begin log
		logger.debug("begin");

		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patternID.toString().length() > 0)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitlePatternID_MessageString) + patternID.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatternID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (patternID.toString().length() > 0)
			{
				//ソート条件パターン設定を更新する
				retFlg = sortPatternHandler.DeleteSortPatternInfo(patternID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdateSortPatternInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdateSortPatternInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// 登録済みの履歴一覧を取得する
	/// </summary>
	/// <returns>履歴一覧</returns>
	@Override
	public DataTable GetRisHistoryList(OrderSearchParameter param)
	{
		// 進捗：完了

		// parameters
		DataTable dt = null;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				//オーダ情報を検索する(履歴)
				dt = orderHandler.SearchRisOrderDataHistory(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.searchRisOrderDataException_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg + "\r\n" + e.Message);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (dt != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleDataTableCount_MessageString) + dt.Rows.Count);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataTable_MessageString));
		}
		//
		//
		//////////////////////////////

		return dt;
	}
	*/

	/*
	// 2010.11.08 Add K.Shinohara Start
	/// <summary>
	/// 登録済みの履歴数をカウントする
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	@Override
	public int GetRisHistoryCount(OrderSearchParameter param)
	{
		// 進捗：完了

		// parameters
		int orderCount = 0;
		OrderHandler orderHandler = new OrderHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOrderSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				orderCount = orderHandler.CountRisOrderDataHistory(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOrderCount_MessageString) + orderCount);
		//
		//
		//////////////////////////////

		return orderCount;
	}
	// 2010.11.08 Add K.Shinohara End
	*/

	/*
	// 2010.06.23 Add T.Nishikubo Start
	// 業務区分
	/// <summary>
	/// 業務日誌情報の取得
	/// </summary>
	/// <param name="exDateStr">検査日</param>
	/// <returns>業務日誌情報</returns>
	@Override
	public ExamDailyInfoBean GetExamDailyData(String exDateStr)
	{
		// 進捗：完了

		// parameters
		ExamDailyInfoBean exDailyBean = null;
		ExamDailyHandler examDailyHandler = new ExamDailyHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleExDateStr_MessageString) + exDateStr);
		//
		//
		//////////////////////////////


		// begin log
		logger.debug("begin");

		try
		{
			exDailyBean = examDailyHandler.GetExamDailyData(exDateStr);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			exDailyBean = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (exDailyBean != null)
		{
			logger.info(exDailyBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExamDailyInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		return exDailyBean;
	}
	*/

	/*
	/// <summary>
	/// 業務日誌情報を登録する
	/// </summary>
	/// <param name="exDailyBean">業務日誌情報</param>
	/// <returns></returns>
	@Override
	public boolean RegisterExamDailyData(ExamDailyInfoBean exDailyBean)
	{
		ExamDailyHandler examDailyHandler = new ExamDailyHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exDailyBean != null)
		{
			logger.info(exDailyBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExamDailyInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (exDailyBean != null)
			{
				rtnBool = examDailyHandler.RegisterExamDailyData(exDailyBean);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterExamDailyInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterExamDailyInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;
	}
	// 2010.06.23 Add T.Nishikubo End
	*/

	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// チーム情報を登録する
	/// </summary>
	/// <param name="terminalID">端末ID</param>
	/// <returns>チーム情報</returns>
	@Override
	public TeamInfoBean GetTeamInfoData(String terminalID)
	{
		TeamInfoHandler teamInfoHandler = new TeamInfoHandler();
		TeamInfoBean rtnBean = null;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (terminalID != null)
		{
			logger.info(terminalID.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTeamInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (terminalID != null)
			{
				rtnBean = teamInfoHandler.GetTeamInfoData(terminalID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBean = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBean != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKGetTeamInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGGetTeamInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBean;
	}
	*/

	/*
	/// <summary>
	/// チーム情報を更新する
	/// </summary>
	/// <param name="teamInfoBean">チーム情報</param>
	/// <returns>更新成否</returns>
	@Override
	public boolean UpdateTeamInfoData(TeamInfoBean teamInfoBean)
		{
		TeamInfoHandler teamInfoHandler = new TeamInfoHandler();
		boolean rtnboolean = false;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (teamInfoBean != null)
		{
			logger.info(teamInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTeamInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (teamInfoBean != null)
			{
				rtnboolean = teamInfoHandler.UpdateRisTeamInfo(teamInfoBean);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnboolean = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnboolean != false)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdateTeamInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdateTeamInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnbool;
	}
	*/

	/*
	/// <summary>
	/// チーム情報を登録する
	/// </summary>
	/// <param name="teamInfoBean">チーム情報</param>
	/// <returns>登録成否</returns>
	@Override
	public boolean InsertTeamInfoData(TeamInfoBean teamInfoBean)
	{
		TeamInfoHandler teamInfoHandler = new TeamInfoHandler();
		boolean rtnboolean = false;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (teamInfoBean != null)
		{
			logger.info(teamInfoBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullTeamInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (teamInfoBean != null)
			{
				rtnboolean = teamInfoHandler.InsertNewTeamInfo(teamInfoBean);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnboolean = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnboolean != false)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterTeamInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterTeamInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnbool;
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	/*
	// 2010.07.30 Add T.Nishikubo Start
	// 統計
	/// <summary>
	/// CSVファイルを成形して出力する
	/// </summary>
	/// <param name="csvParam">CSV出力パラメータ</param>
	/// <returns>結果</returns>
	@Override
	public boolean OutputCsvFile(ref OutputCsvParameter csvParam)
	{
		// parameters
		OutputCsvHandler csvHandler = new OutputCsvHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (csvParam != null)
		{
			logger.info(csvParam.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullOutputCsvParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (csvParam != null)
			{
				rtnBool = csvHandler.OutputCsvFile(ref csvParam);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKOutputCsv_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGOutputCsv_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;

	}
	// 2010.07.30 Add T.Nishikubo End
	*/

	/*
	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	// 掲示板関連
	/// <summary>
	/// 新しいメッセージIDを取得する
	/// </summary>
	/// <returns>新規メッセージID</returns>
	@Override
	public String GetNewMessageID(Connection con, Transaction transaction)
	{
		// 進捗：完了

		String retStr = "";

		// begin log
		logger.debug("begin");

		try
		{
			CommunicationMsgInfoTbl communicationMsgInfoTbl = new CommunicationMsgInfoTbl();
			retStr = communicationMsgInfoTbl.GetNewMessageId(con, transaction);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retStr;
	}
	*/

	/*
	/// <summary>
	/// 掲示板用チェックアウト処理
	/// </summary>
	/// <param name="messageId">チェックアウト対象のメッセージID</param>
	/// <returns>チェックアウトしたメッセージID</returns>
	@Override
	public String CheckoutMsgData(String messageId)
	{
		// 進捗：完了

		// parameters
		String checkoutIDStr = null;

		// begin log
		logger.debug("begin");

		if (messageId != null)
		{
			try
			{
				// 2011.02.28 Mod T.Nishikubo Start
				if (messageId.Equals(CommonString.COMMSG_TERM_MESSAGE_ID))
				{
					//端末メモ
					AccessInfoBean accessBean = new AccessInfoBean();
					AccessInfoHandler accessInfoHandler = new AccessInfoHandler();
					String ipAddress = Configuration.GetInstance().GetUserAccountBean().GetIPAddress();
					if (String.IsNullOrEmpty(ipAddress))
					{
						return checkoutIDStr;
					}
					if (ipAddress.length() > CommonString.COMMSG_IP_LENGTH)
					{
						ipAddress = ipAddress.SubString(0, CommonString.COMMSG_IP_LENGTH);
					}
					messageId = ipAddress;
					accessBean.SetID(CommonString.COMMSG_SHORT_PREFIX_STRING + messageId);
					accessBean.SetAppID(CommonString.MENUBUTTON_ID_U1);
					accessBean.SetIpAddress(ipAddress);
					//checkoutIDStr = accessInfoHandler.CheckoutTerminalMemoData(accessBean);
					checkoutIDStr = CheckoutRisOrder(accessBean);
				}
				else
				{
					//その他
					AccessInfoBean accessBean = new AccessInfoBean();
					accessBean.SetID(messageId);
					accessBean.SetAppID(CommonString.MENUBUTTON_ID_U1);
					accessBean.SetIpAddress(Configuration.GetInstance().GetUserAccountBean().GetIPAddress());
					checkoutIDStr = CheckoutRisOrder(accessBean);
				}
				//AccessInfoBean accessBean = new AccessInfoBean();
				//accessBean.SetID(messageId);
				//accessBean.SetAppID(CommonString.MENUBUTTON_ID_U1);
				//accessBean.SetIpAddress(Configuration.GetInstance().GetUserAccountBean().GetIPAddress());
				//checkoutIDStr = CheckoutRisOrder(accessBean);
				// 2011.02.28 Mod T.Nishikubo End

			}
			catch (Exception e)
			{
				//ここではオラクルエラーは表示させない
				logger.fatal(e);
			}
		}

		// end log
		logger.debug("end");

		return checkoutIDStr;
	}
	*/

	/*
	/// <summary>
	/// 掲示板用チェックイン処理
	/// </summary>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns>判定</returns>
	@Override
	public boolean UncheckoutMsgData(String checkoutID)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (checkoutID != null)
		{
			try
			{
				// 2011.02.28 Mod T.Nishikubo Start
				if (checkoutID.Equals(CommonString.COMMSG_TERM_MESSAGE_ID))
				{
					//端末メモの場合
					AccessInfoBean accBean = new AccessInfoBean();
					AccessInfoHandler accessInfoHandler = new AccessInfoHandler();
					String ipAddress = Configuration.GetInstance().GetIPAddress();
					if (String.IsNullOrEmpty(ipAddress))
					{
						return retFlg;
					}
					if (ipAddress.length() > CommonString.COMMSG_IP_LENGTH)
					{
						ipAddress = ipAddress.SubString(0, CommonString.COMMSG_IP_LENGTH);
					}
					accBean.SetID(CommonString.COMMSG_SHORT_PREFIX_STRING + ipAddress);
					accBean.SetAppID(CommonString.MENUBUTTON_ID_U1);
					accBean.SetIpAddress(ipAddress);
					retFlg = accessInfoHandler.UncheckoutTerminalMemoData(accBean);
				}
				else
				{
					//その他
					retFlg = UncheckoutRisData(checkoutID);
				}
				//retFlg = UncheckoutRisData(checkoutID);
				// 2011.02.28 Mod T.Nishikubo End
			}
			catch (Exception e)
			{
				//ここではオラクルエラーは表示させない
				logger.fatal(e);
			}
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// 指定されたデータとCheckoutIDの組み合わせをチェックする
	/// </summary>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <param name="appID">画面ID</param>
	/// <returns>判定</returns>
	@Override
	public boolean CanCheckin(String checkoutID, String appID)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		try
		{
			if (checkoutID != null && appID != null)
			{
				DataRow row = GetCheckoutData(checkoutID);
				String rowId	= row[RisAccessInfoTbl.ID_COLUMN].toString();
				String rowappId = row[RisAccessInfoTbl.APPID_COLUMN].toString();
				String rowIp	= row[RisAccessInfoTbl.IPADDRESS_COLUMN].toString();
				if(rowId.Equals(checkoutID) && rowappId.Equals(appID) && rowIp.Equals(Configuration.GetInstance().GetIPAddress()))
				{
					// メッセージID、画面ID、IPアドレスが一致
					retFlg = true;
				}
			}
		}
		catch (Exception e)
		{
			//ここではオラクルエラーは表示させない
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// チェックアウト情報を取得する
	/// </summary>
	/// <param name="checkoutID">チェックアウトＩＤ</param>
	/// <returns>チェックアウト情報</returns>
	@Override
	public AccessInfoBean GetCheckoutMsgInfo(String checkoutID)
	{
		// 進捗：完了

		// parameters
		AccessInfoBean bean = null;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (checkoutID != null)
		{
			logger.info("[String]:" + checkoutID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullDataUID_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (checkoutID != null)
			{
				CommunicationMsgHandler hdlr = new CommunicationMsgHandler();
				bean = hdlr.GetCheckoutInfo(checkoutID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (bean != null)
		{
			logger.info(bean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGGetCheckoutInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return bean;
	}
	*/

	/*
	/// <summary>
	/// 掲示板全データのチェックアウト取消し
	/// </summary>
	/// <returns></returns>
	@Override
	public void UncheckoutAllMsgData()
	{
		// 進捗：完了

		// parameters
		AccessInfoHandler accessInfoHandler = new AccessInfoHandler();

		// begin log
		logger.debug("begin");

		try
		{
			//当該IPアドレスでロックしている全データのロックを解除する
			accessInfoHandler.UncheckoutAllMsgData();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.Warn(e);
		}

		// end log
		logger.debug("end");

		return;
	}
	*/

	/*
	// コミュニケーションメッセージ情報の関数群
	/// <summary>
	/// [xx-01] 当該コミュニケーションメッセージを編集するためにcheckoutする
	/// </summary>
	/// <param name="messageIDStr">チェックアウト対象のメッセージID</param>
	/// <returns>チェックアウトID</returns>
	@Override
	public String CheckoutCommunicationMsg( String messageIDStr )
	{
		// 進捗：完了

		// parameters
		String checkoutIDStr = null;
		CommunicationMsgInfoBean comMsgBean = null;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (messageIDStr != null)
		{
			logger.info("[String]:" + messageIDStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMessageID_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (messageIDStr != null)
			{
				comMsgBean = comMsgHandler.GetCommunicationMsg(messageIDStr);
			}
			if (comMsgBean != null)
			{
				checkoutIDStr = CheckoutMsgData(messageIDStr);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			checkoutIDStr = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (checkoutIDStr != null)
		{
			logger.info(checkoutIDStr);
		}
		else
		{
			logger.info("[CheckoutID] NULL");
		}
		//
		//
		//////////////////////////////

		return checkoutIDStr;
	}
	*/

	/*
	/// <summary>
	/// [xx-02] 全体告知情報を編集するためにcheckoutする
	/// </summary>
	/// <returns>チェックアウトID</returns>
	@Override
	public String CheckoutCommunicationAllMsg()
	{
		// 進捗：完了

		// parameters
		String checkoutIDStr = null;
		CommunicationMsgInfoBean comMsgBean = null;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info("[String]:" + CommonString.COMMSG_ALL_MESSAGE_ID);
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			comMsgBean = comMsgHandler.GetCommunicationMsg(CommonString.COMMSG_ALL_MESSAGE_ID);
			if (comMsgBean != null)
			{
				checkoutIDStr = CheckoutMsgData(CommonString.COMMSG_ALL_MESSAGE_ID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			checkoutIDStr = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (checkoutIDStr != null)
		{
			logger.info(checkoutIDStr);
		}
		else
		{
			logger.info("[CheckoutID] NULL");
		}
		//
		//
		//////////////////////////////

		return checkoutIDStr;
	}
	*/

	/*
	// 2011.02.28 Add T.Nishikubo Start
	/// <summary>
	/// [xx-02a] 端末メモを編集するためにcheckoutする
	/// </summary>
	/// <returns>チェックアウトID</returns>
	@Override
	public String CheckoutCommunicationTerminalMemo()
	{
		// 進捗：完了

		// parameters
		String checkoutIDStr = null;
		CommunicationMsgInfoBean comMsgBean = null;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info("[String]:" + CommonString.COMMSG_TERM_MESSAGE_ID);
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			comMsgBean = comMsgHandler.GetCommunicationMsg(CommonString.COMMSG_TERM_MESSAGE_ID);
			if (comMsgBean != null)
			{
				checkoutIDStr = CheckoutMsgData(CommonString.COMMSG_TERM_MESSAGE_ID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			checkoutIDStr = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (checkoutIDStr != null)
		{
			logger.info(checkoutIDStr);
		}
		else
		{
			logger.info("[CheckoutID] NULL");
		}
		//
		//
		//////////////////////////////

		return checkoutIDStr;
	}
	// 2011.02.28 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// [xx-03] 新規のコミュニケーションメッセージ情報を登録する
	/// </summary>
	/// <param name="bean">登録対象データ</param>
	/// <returns>登録成功：true　登録失敗：false</returns>
	@Override
	public boolean RegisterCommunicationMsgInfo(CommunicationMsgInfoBean comMsgBean)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (comMsgBean != null)
		{
			logger.info(comMsgBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMsgInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (comMsgBean != null)
			{
				retFlg = comMsgHandler.RegisterCommunicationMsgData(comMsgBean);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterCommunicationMsgInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterCommunicationMsgInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// [xx-04] 新規の全体告知情報を登録する
	/// </summary>
	/// <param name="messageStr">全体告知メッセージ</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
	@Override
	public CommunicationMsgInfoBean RegisterCommunicationAllMsgInfo(String messageStr)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();
		CommunicationMsgInfoBean newComMsgBean = null;

		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (messageStr != null)
		{
			logger.info("[String]:" + messageStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMessage_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (messageStr != null)
			{
				// 全体告知情報を新規登録する
				retFlg = comMsgHandler.RegisterAllMessageData(messageStr);

				// アンチェックアウトする
				UncheckoutMsgData(CommonString.COMMSG_ALL_MESSAGE_ID);
			}

			if (retFlg)
			{
				// 新しい全体告知情報を取得する
				newComMsgBean = GetCommunicationAllMsg();
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterCommunicationAllMsgInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterCommunicationAllMsgInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return newComMsgBean;
	}
	*/

	/*
	/// <summary>
	/// [xx-04a] 新規の端末メモを登録する
	/// </summary>
	/// <param name="messageStr">端末メモ</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
	@Override
	public CommunicationMsgInfoBean RegisterCommunicationTerminalMemoInfo(String messageStr)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();
		CommunicationMsgInfoBean newComMsgBean = null;

		/////////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (messageStr != null)
		{
			logger.info("[String]:" + messageStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMessage_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (messageStr != null)
			{
				// 端末メモを新規登録する
				retFlg = comMsgHandler.RegisterTerminalMemoData(messageStr);

				// アンチェックアウトする
				UncheckoutMsgData(CommonString.COMMSG_TERM_MESSAGE_ID);
			}

			if (retFlg)
			{
				// 新しい端末メモを取得する
				newComMsgBean = GetCommunicationTerminalMemo();
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterCommunicationTerminalMemo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterCommunicationTerminalMemo_MessageString));
		}
		//
		//
		//////////////////////////////

		return newComMsgBean;
	}
	*/

	/*
	/// <summary>
	/// [xx-05] 既存のコミュニケーションメッセージ情報を更新する
	/// </summary>
	/// <param name="bean">更新対象データ</param>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns>更新成功：true　更新失敗：false</returns>
	@Override
	public boolean CheckinCommunicationMsgInfo(CommunicationMsgInfoBean comMsgBean, String checkoutID)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (comMsgBean != null)
		{
			logger.info(comMsgBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMsgInfoBean_MessageString));
		}

		if (checkoutID != null)
		{
			logger.info("[String]:" + checkoutID);
		}
		else
		{
			logger.info("[CheckoutID] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (comMsgBean != null && checkoutID != null)
			{
				retFlg = CanCheckin(checkoutID, CommonString.MENUBUTTON_ID_U1);

				if (retFlg)
				{
					// コミュニケーションメッセージ情報を更新する
					retFlg = comMsgHandler.UpdateCommunicationMsgData(comMsgBean);

					// アンチェックアウトする
					UncheckoutMsgData(checkoutID);
				}
			}
		}
		catch (Exception e)
		{
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKCheckinCommunicationMsgInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGCheckinCommunicationMsgInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// [xx-06] 既存の全体告知情報を更新する
	/// </summary>
	/// <param name="messageStr">全体告知メッセージ</param>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
	@Override
	public CommunicationMsgInfoBean CheckinCommunicationAllMsgInfo(String messageStr, String checkoutID)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();
		CommunicationMsgInfoBean newComMsgBean = null;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (messageStr != null)
		{
			logger.info("[String]:" + messageStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMessage_MessageString));
		}

		if (checkoutID != null)
		{
			logger.info("[String]:" + checkoutID);
		}
		else
		{
			logger.info("[CheckoutID] NULL");
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (messageStr != null && checkoutID != null)
			{
				if (CanCheckin(checkoutID, CommonString.MENUBUTTON_ID_U1))
				{
					// 全体告知情報を更新する
					retFlg = comMsgHandler.UpdateAllMessageData(messageStr);

					// アンチェックアウトする
					UncheckoutMsgData(checkoutID);
				}
			}

			if (retFlg)
			{
				// 新しい全体告知情報を取得する
				newComMsgBean = GetCommunicationAllMsg();
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKCheckinCommunicationAllMsgInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGCheckinCommunicationAllMsgInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return newComMsgBean;
	}
	*/

	/*
	/// <summary>
	/// [xx-06a] 既存の端末メモを更新する
	/// </summary>
	/// <param name="messageStr">端末メモ</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
	@Override
	public CommunicationMsgInfoBean UpdateCommunicationTerminalMemoInfo(String messageStr)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();
		CommunicationMsgInfoBean newComMsgBean = null;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (messageStr != null)
		{
			logger.info("[String]:" + messageStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMessage_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			// 2011.02.28 Mod T.Nishikubo Start
			String ipAddress = Configuration.GetInstance().GetIPAddress();
			if (messageStr != null && ipAddress.length() > 0)
			{
				if (CanCheckin(CommonString.COMMSG_SHORT_PREFIX_STRING + ipAddress, CommonString.MENUBUTTON_ID_U1))
				{
					// 端末メモを更新する
					retFlg = comMsgHandler.UpdateTerminalMemoData(messageStr);

					// アンチェックアウトする
					UncheckoutMsgData(CommonString.COMMSG_TERM_MESSAGE_ID);
				}
			}
			//if (messageStr != null)
			//{
			//    if (CanCheckin(CommonString.COMMSG_TERM_MESSAGE_ID, CommonString.MENUBUTTON_ID_U1))
			//    {
			//        // 端末メモを更新する
			//        retFlg = comMsgHandler.UpdateTerminalMemoData(messageStr);

			//        // アンチェックアウトする
			//        UncheckoutMsgData(CommonString.COMMSG_TERM_MESSAGE_ID);
			//    }
			//}
			// 2011.02.28 Mod T.Nishikubo End
			if (retFlg)
			{
				// 新しい端末メモを取得する
				newComMsgBean = GetCommunicationTerminalMemo();
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterCommunicationTerminalMemo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterCommunicationTerminalMemo_MessageString));
		}
		//
		//
		//////////////////////////////

		return newComMsgBean;
	}
	*/

	/*
	/// <summary>
	/// [xx-07] 既存のコミュニケーションメッセージの通知先ステータスを確認済みに更新する
	/// </summary>
	/// <param name="bean">更新対象データ</param>
	/// <returns>更新成功：true　更新失敗：false</returns>
	@Override
	public boolean UpdateCommunicationSendtoStatusChecked(CommunicationMsgInfoBean comMsgBean)
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (comMsgBean != null)
		{
			logger.info(comMsgBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMsgInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (comMsgBean != null)
			{
				retFlg = comMsgHandler.UpdataSendtoStatusChecked(comMsgBean);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdateCommunicationSendtoStatusChecked_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdateCommunicationSendtoStatusChecked_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// [xx-08] コミュニケーションメッセージ情報を削除する(削除フラグ=1)
	/// </summary>
	/// <param name="messageIDStr">メッセージID</param>
	/// <returns>更新成功：true　更新失敗：false</returns>
	@Override
	public boolean DeleteCommunicationMsgInfo( String messageIDStr )
	{
		// 進捗：完了

		// parameters
		boolean retFlg = false;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (messageIDStr != null)
		{
			logger.info("[String]:" + messageIDStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMessageID_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (messageIDStr != null)
			{
				retFlg = comMsgHandler.DeleteCommunicationMsgData( messageIDStr );
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retFlg = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retFlg)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKDeleteCommunicationMsgInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGDeleteCommunicationMsgInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// [xx-09] 指定ユーザ宛のコミュニケーションメッセージ情報の一覧を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果一覧</returns>
	@Override
	public CommunicationMsgInfoBean[] GetCommunicationUserMsgList(CommunicationMsgSearchParameter param)
	{
		// 進捗：完了

		// parameters
		CommunicationMsgInfoBean[] beanArray = null;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleRequestParameter_MessageString) + param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMsgSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				beanArray = comMsgHandler.GetUserCommunicationMsgList(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			beanArray = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (beanArray != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKGetUserCommunicationMsgList_MessageString) + beanArray.length());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGGetUserCommunicationMsgList_MessageString));
		}
		//
		//
		//////////////////////////////

		return beanArray;
	}
	*/

	/*
	/// <summary>
	/// [xx-10] 指定ユーザが登録したコミュニケーションメッセージ情報の一覧を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果一覧</returns>
	@Override
	public CommunicationMsgInfoBean[] GetCommunicationUserEntryMsgList(CommunicationMsgSearchParameter param)
	{
		// 進捗：完了

		// parameters
		CommunicationMsgInfoBean[] beanArray = null;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleRequestParameter_MessageString) + param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMsgSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				beanArray = comMsgHandler.GetUserEntryCommunicationMsgList(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			beanArray = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (beanArray != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKGetUserEntryCommunicationMsgList_MessageString) + beanArray.length());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGGetUserEntryCommunicationMsgList_MessageString));
		}
		//
		//
		//////////////////////////////

		return beanArray;
	}
	*/

	/*
	/// <summary>
	/// [xx-11] 指定メッセージの関連メッセージ情報の一覧を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果一覧</returns>
	@Override
	public CommunicationMsgInfoBean[] GetCommunicationSerialMsgList(CommunicationMsgSearchParameter param)
	{
		// 進捗：完了

		// parameters
		CommunicationMsgInfoBean[] beanArray = null;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (param != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleRequestParameter_MessageString) + param.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMsgSearchParameter_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (param != null)
			{
				beanArray = comMsgHandler.GetSerialCommunicationMsgList(param);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			beanArray = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (beanArray != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKGetSerialCommunicationMsgList_MessageString) + beanArray.length());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGGetSerialCommunicationMsgList_MessageString));
		}
		//
		//
		//////////////////////////////

		return beanArray;
	}
	*/

	/*
	/// <summary>
	/// [xx-12] 指定メッセージIDのコミュニケーションメッセージ情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="messageIDStr">メッセージID</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
	@Override
	public CommunicationMsgInfoBean GetCommunicationMsg( String messageIDStr )
	{
		// 進捗：完了

		// parameters
		CommunicationMsgInfoBean comMsgBean = null;
		CommunicationMsgHandler comMsgHandler = new CommunicationMsgHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (messageIDStr != null)
		{
			logger.info("[String]:" + messageIDStr);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMessageID_MessageString));
		}
		//
		//
		//////////////////////////////

		// begin log
		logger.debug("begin");

		try
		{
			if (messageIDStr != null)
			{
				comMsgBean = comMsgHandler.GetCommunicationMsg( messageIDStr );
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			comMsgBean = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (comMsgBean != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKCommunicationMsgInfoBean_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCommunicationMsgInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		return comMsgBean;
	}
	*/

	/*
	/// <summary>
	/// [xx-13] 全体告知情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <returns>コミュニケーションメッセージ情報</returns>
	@Override
	public CommunicationMsgInfoBean GetCommunicationAllMsg()
	{
		// 進捗：完了

		// parameters
		CommunicationMsgInfoBean comMsgBean = null;

		// begin log
		logger.debug("begin");

		try
		{
			//全体告知情報のメッセージIDのコミュニケーションメッセージ情報を取得する
			comMsgBean = GetCommunicationMsg( CommonString.COMMSG_ALL_MESSAGE_ID );
		}
		catch (Exception e)
		{
			//ここではオラクルエラーは表示させない
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return comMsgBean;

	}
	*/

	/*
	/// <summary>
	/// [xx-13a] 端末メモを取得する
	/// </summary>
	/// <remarks></remarks>
	/// <returns>コミュニケーションメッセージ情報</returns>
	@Override
	public CommunicationMsgInfoBean GetCommunicationTerminalMemo()
	{
		// 進捗：完了

		// parameters
		CommunicationMsgInfoBean comMsgBean = null;

		// begin log
		logger.debug("begin");

		try
		{
			//端末メモのメッセージIDのコミュニケーションメッセージ情報を取得する
			CommunicationMsgHandler hdlr = new CommunicationMsgHandler();
			comMsgBean = hdlr.GetTerminalMemo();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return comMsgBean;

	}
	*/



	/*
	// 2011.02.16 Add T.Nishikubo End
	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	/// <summary>
	/// 患者副作用情報を取得する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>患者副作用情報</returns>
	@Override
	public ArrayList GetPatientSideEffectInfoBean(String kanjaID)
	{
		PatientSideEffectHandler patientSideEffectHandler = new PatientSideEffectHandler();
		ArrayList retList	= null;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (!String.IsNullOrEmpty(kanjaID))
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKanjaID_MessageString) + kanjaID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKanjaID_MessageString));
		}
		//
		//////////////////////////////

		try
		{
			if (!String.IsNullOrEmpty(kanjaID))
			{
				retList = patientSideEffectHandler.SelectPatientSideEffectInfoBean(kanjaID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retList = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retList != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKGetPatientSideEffectInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGGetPatientSideEffectInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retList;
	}
	*/

	/*
	/// <summary>
	/// 患者副作用情報を取得する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>患者副作用情報</returns>
	@Override
	public DataTable GetPatientSideEffectInfoData(String kanjaID)
	{
		PatientSideEffectHandler patientSideEffectHandler = new PatientSideEffectHandler();
		DataTable retDataTable	= null;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (!String.IsNullOrEmpty(kanjaID))
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKanjaID_MessageString) + kanjaID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKanjaID_MessageString));
		}
		//
		//////////////////////////////

		try
		{
			if (!String.IsNullOrEmpty(kanjaID))
			{
				retDataTable = patientSideEffectHandler.SelectPatientSideEffectInfoData(kanjaID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			retDataTable = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (retDataTable != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKGetPatientSideEffectInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGGetPatientSideEffectInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return retDataTable;
	}
	*/

	/*
	/// <summary>
	/// 患者副作用情報の件数を取得する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>患者副作用情報件数</returns>
	@Override
	public int GetPatientSideEffectInfoCount(String kanjaID)
	{
		PatientSideEffectHandler patientSideEffectHandler = new PatientSideEffectHandler();
		int retCnt	= -1;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (!String.IsNullOrEmpty(kanjaID))
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleKanjaID_MessageString) + kanjaID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullKanjaID_MessageString));
		}
		//
		//////////////////////////////

		try
		{
			if (!String.IsNullOrEmpty(kanjaID))
			{
				retCnt = patientSideEffectHandler.SelectPatientSideEffectInfoCount(kanjaID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitlePatientSideEffectInfoCount_MessageString) + retCnt.toString());
		//
		//
		//////////////////////////////

		return retCnt;
	}
	*/

	/*
	/// <summary>
	/// 患者副作用情報を登録する
	/// </summary>
	/// <param name="patientSideEffectInfo">患者副作用情報</param>
	/// <returns>成否</returns>
	@Override
	public boolean InsertPatientSideEffectInfo(PatientSideEffectInfoBean patientSideEffectInfo)
	{
		PatientSideEffectHandler patientSideEffectInfoHandler = new PatientSideEffectHandler();
		boolean rtnboolean = false;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientSideEffectInfo != null)
		{
			logger.info(patientSideEffectInfo.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientSideEffectInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (patientSideEffectInfo != null)
			{
				rtnboolean = patientSideEffectInfoHandler.RegisterPatientSideEffectInfoData(patientSideEffectInfo);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnboolean = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnboolean != false)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterPatientSideEffectInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterPatientSideEffectInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnbool;
	}
	*/

	/*
	/// <summary>
	/// 患者副作用情報を更新する
	/// </summary>
	/// <param name="patientSideEffectInfo">患者副作用情報</param>
	/// <returns>成否</returns>
	@Override
	public boolean UpdatePatientSideEffectInfo(PatientSideEffectInfoBean patientSideEffectInfo)
	{
		PatientSideEffectHandler patientSideEffectInfoHandler = new PatientSideEffectHandler();
		boolean rtnboolean = false;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (patientSideEffectInfo != null)
		{
			logger.info(patientSideEffectInfo.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullPatientSideEffectInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (patientSideEffectInfo != null)
			{
				rtnboolean = patientSideEffectInfoHandler.UpdatePatientSideEffectInfoData(patientSideEffectInfo);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnboolean = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnboolean != false)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdatePatientSideEffectInfo_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdatePatientSideEffectInfo_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnbool;
	}
	*/

	/*
	/// <summary>
	/// 患者副作用情報を削除する
	/// </summary>
	/// <param name="sideEffectInfoID">患者副作用情報</param>
	/// <returns>成否</returns>
	@Override
	public boolean DeletePatientSideEffectInfo(String sideEffectInfoID)
	{
		// parameters
		boolean retBool = false;
		PatientSideEffectHandler patientSideEffectHandler = new PatientSideEffectHandler();

		// begin log
		logger.debug("begin");

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (sideEffectInfoID != null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleSideEffectID_MessageString) + sideEffectInfoID);
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullSideEffectID_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (sideEffectInfoID.length() > 0)
			{
				retBool = patientSideEffectHandler.DeletePatientSideEffectInfoData(sideEffectInfoID);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	// 2011.03.10 Add CIJ_R.Aoyama Merge  End  MY-1-10
	*/

	/*
	// 2011.07.19 Add NSK_T.Wada	Start NML-CAT2-001
	/// <summary>
	/// LINK情報一覧を取得する
	/// </summary>
	/// <returns>Link情報一覧</returns>
	@Override
	public DataTable GetRisLinkInfo()
	{
		// parameters
		DataTable dt = null;
		LinkInfoHandler linkInfoHandler = new LinkInfoHandler();

		// begin log
		logger.debug("begin");

		try
		{
			dt = linkInfoHandler.GetRisLinkData();
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	// 2011.07.19 Add NSK_W.Wada	End
	*/

	/*
	// 2011.09.09 Add T.Ootsuka@MERX Start NML-CAT9-031
	/// <summary>
	/// オーダ情報に部位略称を付加する
	/// </summary>
	/// <param name="orderDt">オーダ情報</param>
	/// <param name="oParam">オーダパラメータ</param>
	/// <returns></returns>
	@Override
	public void ModifyOrder_MultiLocus(DataTable orderDt, OrderSearchParameter oParam)
	{
		// parameters
		OrderHandler orderHandler = new OrderHandler();

		// begin log
		logger.debug("begin");

		try
		{
			// オーダ情報に部位略称を付加する
			orderHandler.ModifyOrder_MultiLocus(orderDt, oParam);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			logger.fatal(e);
		}

		// end log
		logger.debug("end");
	}
	// 2011.09.09 Add T.Ootsuka@MERX End
	*/

	/*
	// 2011.11.22 Add NSK_T.Wada	Start OMH-1-9
	/// <summary>
	/// クローズ予約検索条件を取得する
	/// </summary>
	/// <param name="intTerminalId">端末ID</param>
	/// <param name="strWindowappId">画面ID</param>
	/// <returns>クローズ予約検索条件</returns>
	@Override
	public DataTable GetCloseAppointSearchData(int intTerminalId, String strWindowappId)
	{
		// parameters
		DataTable dt = null;
		CloseAppointSearchHandler closeAppointSearchHandler = new CloseAppointSearchHandler();

		// begin log
		logger.debug("begin");

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleTerminalID_MessageString) + intTerminalId);
		logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleWindowAppID_MessageString) + strWindowappId);
		//
		//
		//////////////////////////////
		try
		{
			if (strWindowappId != null && intTerminalId != 0)
			{
				dt = closeAppointSearchHandler.GetCloseAppointSearchData(intTerminalId, strWindowappId);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			dt = null;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/

	/*
	/// <summary>
	/// クローズ予約検索条件を登録する
	/// </summary>
	/// <param name="row">登録データ</param>
	/// <returns>成否</returns>
	@Override
	public boolean InsertCloseAppointSearchData(DataRow row)
	{
		CloseAppointSearchHandler closeAppointSearchHandler = new CloseAppointSearchHandler();
		boolean rtnBool = false;

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (row == null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCloseAppointSearch_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (row != null)
			{
				rtnBool = closeAppointSearchHandler.InsertCloseAppointSearchData(row);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnBool = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnBool)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKRegisterCloseAppointSearch_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGRegisterCloseAppointSearch_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnBool;
	}
	*/

	/*
	/// <summary>
	/// クローズ予約検索条件を更新する
	/// </summary>
	/// <param name="row">更新データ</param>
	/// <returns>成否</returns>
	@Override
	public boolean UpdateCloseAppointSearchData(DataRow row)
	{
		CloseAppointSearchHandler closeAppointSearchHandler = new CloseAppointSearchHandler();
		boolean rtnboolean = false;
		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (row == null)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullCloseAppointSearch_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			if (row != null)
			{
				rtnboolean = closeAppointSearchHandler.UpdateCloseAppointSearchData(row);
			}
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			rtnboolean = false;
			logger.fatal(e);
		}

		// end log
		logger.debug("end");

		//////////////////////////////
		// 出力パラメータのデバッグ出力
		//
		if (rtnboolean != false)
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUpdateCloseAppointSearch_MessageString));
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNGUpdateCloseAppointSearch_MessageString));
		}
		//
		//
		//////////////////////////////

		return rtnbool;
	}
	// 2011.11.22 Add NSK_W.Wada	End
	*/

	/*
	//2011.11.21 Add NSK_M.Ochiai Start extends OMH-1-7
	@Override
	public boolean UpdateRisToRevision(ExecutionInfoBean exBean)
	{
		logger.debug("begin");
		boolean ret = false;
		ResultHandler resultHandler = new ResultHandler();

		//////////////////////////////
		// 入力パラメータのデバッグ出力
		//
		if (exBean != null)
		{
			logger.info(exBean.toString());
		}
		else
		{
			logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleNullExecutionInfoBean_MessageString));
		}
		//
		//
		//////////////////////////////

		try
		{
			ret = resultHandler.UpdateRisToRevision(exBean);
		}
		catch (Exception e)
		{
			ShowMessage(e);	// オラクルエラーの場合メッセージを表示
			ret = false;
			logger.fatal(e);
		}
		finally
		{
			logger.debug("end");
		}
		return ret;
	}
	//2011.11.21 Add NSK_M.Ochiai End
	*/
	// 2019.08.06 Add Cosmo Start 排他対応
	public boolean unCheckOutExclusive(StatusUpdateDto dto,String checkoutID,Connection con,boolean exclusive){
		boolean returnVal = true;
		if(exclusive){
			try
			{
				//オーダー情報をアンチェックアウトする
				boolean result = UncheckoutRisData(checkoutID, con);
				if(!result){
					// ロールバック
					con.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("チェックアウト解除ができませんでした。");
				}
				con.commit();
			}catch(Exception e){
				returnVal = false;
			}
		}

		return returnVal;
	}
	// 2019.08.06 Add Cosmo End 排他対応
	// 2019.10.03 Add Cosmo Start 排他ロック対応
	public boolean unCheckOutLoginLockExclusive(StatusUpdateDto dto,
			String checkoutIp, String checkoutAppID, Connection con,boolean exclusive){
		boolean returnVal = true;
		if(exclusive){
			try
			{
				//オーダー情報をアンチェックアウトする
				boolean result = UncheckoutunLoginLockRisData(checkoutIp, checkoutAppID, con);
				if(!result){
					// ロールバック
					con.rollback();
					dto.setResult(Const.RESULT_NG);
					dto.setErrlevel(Const.ERRLEVEL_WARN);
					dto.setMsg("チェックアウト解除ができませんでした。");
				}
				con.commit();
			}catch(Exception e){
				returnVal = false;
			}
		}
		return returnVal;
	}
	// 2019.10.03 Add Cosmo End 排他ロック対応
}
