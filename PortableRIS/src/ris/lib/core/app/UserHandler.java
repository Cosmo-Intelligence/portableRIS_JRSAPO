package ris.lib.core.app;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.Configuration;
import ris.lib.core.database.RisUserSubView;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.UserSearchParameter;
import ris.portable.util.DataTable;


/// <summary>
///
/// ユーザ情報管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class UserHandler
{
	public static Log logger = LogFactory.getLog(ResultHandler.class);

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public UserHandler()
	{
		//
	}

	/*
	/// <summary>
	/// 権限を確認する
	/// </summary>
	/// <param name="userID">対象ユーザID</param>
	/// <returns>判定</returns>
    public ArrayList GetUserAuthorization(Connection con, Transaction transaction, String userID)
	{
		// parameters
		RisUserInfoTbl risUserInfoTbl = new RisUserInfoTbl();
		RisGroupCompetenceTbl risGroupCompetenceTbl = new RisGroupCompetenceTbl();
        ArrayList comprtrnceArrayList = new ArrayList();

		// begin log
		logger.debug("begin");

		try
		{
			if( userID != null && userID.length() > 0 )
			{
				String id = risUserInfoTbl.GetID(con, transaction, userID);
				if (id != null && id.length() > 0)
				{
                    //所属する権限を取得
                    comprtrnceArrayList = risGroupCompetenceTbl.GetCompetenceID(con, transaction, id);
				}
			}
		}
		catch ( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );
			throw e;
		}

		// end log
		logger.debug("end");

        return comprtrnceArrayList;
	}
	*/

	/*
	/// <summary>
	/// ログインアカウントをチェックする
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="planePassword">平文のパスワード</param>
	/// <returns></returns>
	public UserAccountBean Login( String userID, String planePassword )
	{
		// parameters
		Connection con = null;
		Transaction transaction = null;
		Connection localCon = null;
		Transaction localTransaction = null;
		UserAccountBean userAccount = null;
		RisUserInfoTbl userInfoTbl = new RisUserInfoTbl();
		UserManageTbl userManageTbl = new UserManageTbl();
		AttrManageTbl attrManageTbl = new AttrManageTbl();

		// begin log
		logger.debug("begin");

		//サーブDBから情報を取得
		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetUserManageDBConnection();
			transaction = con.BeginTransaction();

			if( con != null && transaction != null && userID != null && planePassword != null )
			{
				userAccount = userManageTbl.CheckRisLoginAccount( con, transaction, userID, planePassword );

				//コミット
				transaction.Commit();

				if( userAccount != null )
				{
					// とりあえずユーザIDは存在する
					// 2010.09.19 Mod K.Shinohara Start
					if (userAccount.GetUserValidityFlag().compareTo("0") != 0
						&& userAccount.GetPasswordFlag().compareTo("0") != 0
						&& userAccount.GetLicenseToUse().compareTo("0") != 0
						&& ((userAccount.GetExpiryFlag().compareTo("1") != 0)
						|| (Configuration.GetInstance().GetRisCurrentServerType() == CommonString.UNITY_EMG_CA_MODE)))
					//if (userAccount.GetUserValidityFlag().compareTo("0") != 0
					//    && userAccount.GetPasswordFlag().compareTo("0") != 0
					//    && userAccount.GetLicenseToUse().compareTo("0") != 0
					//    && userAccount.GetExpiryFlag().compareTo("1") != 0 )
					// 2010.09.19 Mod K.Shinohara End
					{
						// ログインOK
						userAccount.SetLoginFlag(true);

						// オートログアウト時間を設定
						userAccount.SetAutoLogoutTime(Integer.parseInt(attrManageTbl.GetUserAutoLogoutMinTime( con, transaction, userID )));

						// パスワード更新警告を出すべきか否かを判定
						// 2010.09.19 Mod K.Shinohara Start
						if ((userAccount.GetWarningFlag().StartsWith(CommonString.LOGIN_PASSWORD_WARN)) &&
							(Configuration.GetInstance().GetRisCurrentServerType() != CommonString.UNITY_EMG_CA_MODE))
						//if (userAccount.GetWarningFlag().StartsWith(CommonString.LOGIN_PASSWORD_WARN))
						// 2010.09.19 Mod K.Shinohara End
						{
							String loginErrMsg = "";
							//連携モード判定
							if (Configuration.GetInstance().GetRisCurrentServerType() == CommonString.LOCAL_CA_MODE ||
								Configuration.GetInstance().GetRisCurrentServerType() == CommonString.UNITY_EMG_CA_MODE)
							{
								//ローカル時
								loginErrMsg = userAccount.GetWarningFlag().SubString(2);
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG1;
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG3;
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG4;
							}
							else if (Configuration.GetInstance().GetRisCurrentServerType() == CommonString.UNITY_CA_MODE)
							{
								//SERV時
								loginErrMsg = userAccount.GetWarningFlag().SubString(2);
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG1;
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG3;
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG5;
							}
							userAccount.SetLoginErrorMsg(loginErrMsg);
						}
					}
					else
					{
						// ログイン失敗の理由をメッセージとして登録
						if( userAccount.GetPasswordFlag().compareTo("0") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.PASSWORDNOMATCH_MSG);
						}
						else if( userAccount.GetUserValidityFlag().compareTo("0") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.USERISLOCKED_MSG);
						}
						else if( userAccount.GetLicenseToUse().compareTo("0") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.NOLICENSE_MSG);
						}
						else if( userAccount.GetExpiryFlag().compareTo("1") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.PASSWORDEXPIRED_MSG);
						}
					}
                }
                else
                {
                    // ユーザIDが存在しない
                    userAccount = new UserAccountBean();
                    userAccount.SetUserID(userID);
                    userAccount.SetLoginErrorMsg(MessageParameter.USERID_NOMATCH_MSG);
                }
            }
		}
		catch( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			if( transaction != null )
			{
				try
				{
					transaction.Rollback();
				}
				catch( Exception e1 )
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1 );
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		//ローカルDBから情報を取得
		try
		{
			// get connection and begin transaction
			localCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			localTransaction = localCon.BeginTransaction();

			if (localCon != null && localTransaction != null && userID != null && planePassword != null)
			{
				if (userAccount != null)
				{
					//スタッフIDの取得
					String staffID = userInfoTbl.GetStaffID(localCon, localTransaction, userID);
					userAccount.SetStaffID(staffID);

					//権限の取得
					ArrayList comprtrnceArrayList = GetUserAuthorization(localCon, localTransaction, userID);

					//権限の設定
					userAccount.SetComprtrnceArrayList(comprtrnceArrayList);
				}

				//コミット
				localTransaction.Commit();
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (localTransaction != null)
			{
				try
				{
					localTransaction.Rollback();
				}
				catch (Exception e1)
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(localCon);
		}

		// end log
		logger.debug("end");

		return userAccount;
	}
	*/

	/*
	/// <summary>
	/// ログインアカウントをチェックする
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="planePassword">平文のパスワード</param>
	/// <returns></returns>
	public UserAccountBean Login( String userID )
	{
		// parameters
		Connection con = null;
		Transaction transaction = null;
		UserAccountBean userAccount = null;
		UserManageTbl userManageTbl = new UserManageTbl();
		AttrManageTbl attrManageTbl = new AttrManageTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetUserManageDBConnection();
			transaction = con.BeginTransaction();

			if( con != null && transaction != null && userID != null )
			{
				userAccount = userManageTbl.CheckRisLoginAccount( con, transaction, userID, userID );

				if( userAccount != null )
				{
					// とりあえずユーザIDは存在する
					if( userAccount.GetUserValidityFlag().compareTo("0") != 0
						&& userAccount.GetLicenseToUse().compareTo("0") != 0
						&& userAccount.GetExpiryFlag().compareTo("1") != 0 )
					{
						// ログインOK
						userAccount.SetLoginFlag(true);

						// オートログアウト時間を設定
						userAccount.SetAutoLogoutTime(Integer.parseInt(attrManageTbl.GetUserAutoLogoutMinTime( con, transaction, userID )));

						// パスワード更新警告を出すべきか否かを判定
						if( userAccount.GetWarningFlag().StartsWith(CommonString.LOGIN_PASSWORD_WARN) )
						{
							String loginErrMsg = userAccount.GetWarningFlag().SubString(2);
							loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG1;
							if (Configuration.GetInstance().GetPasswordChangePageURL().length() > 0)
							{
								loginErrMsg += Configuration.GetInstance().GetPasswordChangePageURL();
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG2;
							}
							else
							{
								loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG3;
							}
							loginErrMsg += MessageParameter.PASSWORD_EXPIREISNEAR_MSG4;

							userAccount.SetLoginErrorMsg(loginErrMsg);
						}
					}
					else
					{
						// ログイン失敗の理由をメッセージとして登録
						if( userAccount.GetPasswordFlag().compareTo("0") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.PASSWORDNOMATCH_MSG);
						}
						else if( userAccount.GetUserValidityFlag().compareTo("0") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.USERISLOCKED_MSG);
						}
						else if( userAccount.GetLicenseToUse().compareTo("0") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.NOLICENSE_MSG);
						}
						else if( userAccount.GetExpiryFlag().compareTo("1") == 0 )
						{
							userAccount.SetLoginErrorMsg(MessageParameter.PASSWORDEXPIRED_MSG);
						}
					}
                }
                else
                {
                    // ユーザIDが存在しない
                    userAccount = new UserAccountBean();
                    userAccount.SetUserID(userID);
                    userAccount.SetLoginErrorMsg(MessageParameter.USERID_NOMATCH_MSG);
                }
			}


			transaction.Commit();
		}
		catch( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			if( transaction != null )
			{
				try
				{
					transaction.Rollback();
				}
				catch( Exception e1 )
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1 );
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return userAccount;
	}
	*/

	/// <summary>
	/// ユーザ情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable GetUserInfo(UserSearchParameter param, Connection con) throws Exception
	{
		// parameters
		DataTable dt = null;
		RisUserSubView risUserSubView = new RisUserSubView();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && param != null)
			{
				dt = risUserSubView.GetUserSubView(con, param);
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
			throw e;
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// チームユーザ情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable GetTeamUserInfo(UserSearchParameter param)
	{
		// parameters
		DataTable dt = null;
		Connection con = null;
		Transaction transaction = null;
		RisUserSubView risUserSubView = new RisUserSubView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				dt = risUserSubView.GetUserSubViewTeamUser(con, transaction, param);
			}

			// commit
			transaction.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	/*
	/// <summary>
	/// 認証を行う
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="password">パスワード</param>
	/// <returns></returns>
	public boolean CheckUserIDPassword(String userID, String password)
	{
		// parameters
		boolean retBool = false;
		Connection con = null;
		Transaction transaction = null;
		UserManageTbl userManageTbl = new UserManageTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetUserManageDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && userID != null && password != null)
			{
				UserAccountBean userAccount = userManageTbl.CheckRisLoginAccount(con, transaction, userID, password);
				if (userAccount != null &&
					userAccount.GetPasswordFlag().compareTo("0") != 0)
				{
					retBool = true;
				}

				//コミット
				transaction.Commit();
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// ユーザ情報を取得する
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <returns>ユーザ情報</returns>
	public DataRow GetUserManageDataRow(String userID)
	{
		// parameters
		DataRow retRow = null;
		Connection con = null;
		Transaction transaction = null;
		UserManageTbl userManageTbl = new UserManageTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && userID != null && userID.length() > 0)
			{
				retRow = userManageTbl.GetUserManage(con, transaction, userID);
			}

			// commit
			transaction.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retRow;
	}
	*/

	/*
	/// <summary>
	/// ユーザ情報を更新する
	/// </summary>
	/// <param name="userBean">ユーザ情報</param>
	/// <returns>ユーザ情報</returns>
	public boolean UpdateUserManageData(UserAccountBean userBean)
	{
		// parameters
		boolean retBool = false;
		Connection con = null;
		Transaction transaction = null;
		UserManageTbl userManageTbl = new UserManageTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && userBean != null)
			{
				retBool = userManageTbl.UpdateUserData(con, transaction, userBean);
			}

			// commit
			transaction.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 監査認証情報を取得、設定する
	/// </summary>
	/// <param name="currentType">ｻｰﾊﾞ種別</param>
	/// <returns></returns>
	public boolean GetGetCurrentConfirmAuditDefine(String currentType)
	{
		// parameters
		boolean retBool = false;
		Connection con = null;
		Transaction transaction = null;
		CurrentConfirmAuditDefineView view = new CurrentConfirmAuditDefineView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && currentType != null)
			{
				DataTable dt = view.GetCurrentConfirmAuditDefine(con, transaction, currentType);
				if (dt != null && dt.Rows.Count > 0)
				{
					DataRow row = dt.Rows[0];
					if (row != null)
					{
						String serviceName	= row[CurrentConfirmAuditDefineView.DB_SERVICENAME_COLUMN].toString();
						String user			= row[CurrentConfirmAuditDefineView.DB_USER_COLUMN].toString();
						String password		= row[CurrentConfirmAuditDefineView.DB_PASSWORD_COLUMN].toString();

						if (serviceName.length() > 0 && user.length() > 0 && password.length() > 0)
						{
							Configuration.GetInstance().SetAudittrailDbSourceStr(serviceName);
							Configuration.GetInstance().SetAudittrailDbUserStr(user);
							Configuration.GetInstance().SetAudittrailDbPasswordStr(password);
							//
							Configuration.GetInstance().SetAuthenticationDbSourceStr(serviceName);
							Configuration.GetInstance().SetAuthenticationDbUserStr(user);
							Configuration.GetInstance().SetAuthenticationDbPasswordStr(password);
							//
							retBool = true;
						}
					}
				}
			}

			// commit
			transaction.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/
}
