package ris.lib.core.app;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.bean.AccessInfoBean;
import ris.lib.core.database.RisAccessInfoTbl;
import ris.lib.core.util.CommonString;
import ris.portable.util.DataRow;


/// <summary>
///
/// 排他管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.22	: 112478 / A.Kobayashi	: original
/// V2.04.00	: 2011.02.16	: 999999 / T.Nishikubo	: KANRO-R-27
///
/// </summary>
public class AccessInfoHandler
{
	public static Log logger = LogFactory.getLog(AccessInfoHandler.class);

	/// <summary>
	/// チェックアウト情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RisID</param>
	/// <returns>チェックアウト情報</returns>
	public DataRow GetCheckoutData(String risID, Connection con) throws Exception
	{
		// paremeters
		DataRow retRow = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// start log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0)
			{
				retRow = accessInfoTbl.GetCheckoutData(con, risID);
			}
		}
		catch (Exception e)
		{
			throw e;
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
	/// <param name="risIdStr">対象外のRIS_ID（自オーダ）</param>
	/// <returns>端末名称</returns>
	public String GetCheckoutData(String kanjaIDStr, String kensaDateStr, String risIDStr)
	{
		// paremeters
		String retStr = "";
		Connection con = null;
		Transaction transaction = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();
		RisTerminalInfoTbl terminalInfoTbl = new RisTerminalInfoTbl();
		TerminalInfoBean termBean = new TerminalInfoBean();

		// start log
		logger.debug("begin");

		try
		{
			if (kanjaIDStr != null && kanjaIDStr.length() > 0 && kensaDateStr != null && kensaDateStr.length() == 8
				&& risIDStr != null && risIDStr.length() > 0)
			{
				// get connection
				con = DBConnectionFactory.GetInstance().GetRisDBConnection();

				// begin transaction
				transaction = con.BeginTransaction();

				String ipAddress = accessInfoTbl.GetCheckoutData(con, transaction, kanjaIDStr, kensaDateStr, risIDStr);
				if (ipAddress != null && ipAddress.length() > 0)
				{
					//端末情報を取得する
					terminalInfoTbl.GetTerminalInfoDataByIPAdrress(con, transaction, ipAddress, termBean);
					if (termBean != null)
					{
						retStr = termBean.GetTerminalName();

						// 2011.02.16 Add H.Orikasa Start
						// 端末名称が空の場合は、スペースを戻す(端末名-空問題対応)
						if (retStr.length() <= 0)
						{
							retStr = " ";
						}
						// 2011.02.16 Add H.Orikasa End
					}
				}

				// commit
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

		return retStr;
	}
	*/

	/*
	// 2010.09.10 Add K.Shinohara Start
	/// <summary>
	/// チェックアウト情報を取得する(オーダキャンセル用)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="accessBean">AccessInfoBean</param>
	/// <returns>チェックアウト情報</returns>
	public DataRow GetCheckoutData_OrderCancel(AccessInfoBean accessBean, String ownerAppID)
	{
		// paremeters
		DataRow retRow = null;
		Connection con = null;
		Transaction transaction = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// start log
		logger.debug("begin");

		try
		{
			if (accessBean != null)
			{
				// get connection
				con = DBConnectionFactory.GetInstance().GetRisDBConnection();

				// begin transaction
				transaction = con.BeginTransaction();

				retRow = accessInfoTbl.GetCheckoutData_OrderCancel(con, transaction, accessBean, ownerAppID);

				// commit
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

		return retRow;
	}
	// 2010.09.10 Add K.Shinohara End
	*/

	/// <summary>
	/// チェックアウトを行う
	/// </summary>
	/// <param name="bean">排他情報</param>
	/// <returns></returns>
	public String CheckoutRisData(AccessInfoBean bean, Connection con) throws Exception
	{
		// parameters
		String retStr = "";
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && bean != null)
			{
				// 2010.09.09 Mod K.Shinohara Start
				if (bean.GetAccessMode().equals(CommonString.ROCK_ACCESSMODE_MOD))
				{
					// 更新モード：更新モードにて排他ロックがかかっているかチェックする
					DataRow checkoutRow = accessInfoTbl.GetCheckoutData(con, bean.GetID());
					if (checkoutRow != null)
					{
						retStr = "";
					}
					else
					{
						retStr = accessInfoTbl.Checkout(con, bean);
					}
				}
				else
				{
					// 2010.10.28 Mod K.Shinohara Start
					// 参照モード：専用テーブルへ登録する
					retStr = accessInfoTbl.CheckoutReference(con, bean);
					//// 参照モード：無条件で登録する
					//retStr = accessInfoTbl.Checkout(con, transaction, bean);
					// 2010.10.28 Mod K.Shinohara End
				}
				//retStr = accessInfoTbl.Checkout(con, transaction, bean);
				// 2010.09.09 Mod K.Shinohara End
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return retStr;
	}

	/*
	// 2010.09.10 Add K.Shinohara Start
	/// <summary>
	/// チェックアウトを行う(オーダキャンセル用)
	/// </summary>
	/// <param name="bean">排他情報</param>
	/// <returns></returns>
	public String CheckoutRisData_OrderCancel(AccessInfoBean bean, String ownerAppID)
	{
		// parameters
		String retStr = "";
		Connection con = null;
		Transaction transaction = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && bean != null)
			{
				// オーダキャンセル：全てのモードにて排他ロックがかかっているかチェックする
				DataRow checkoutRow = accessInfoTbl.GetCheckoutData_OrderCancel(con, transaction, bean, ownerAppID);
				if (checkoutRow != null)
				{
					retStr = "";
				}
				else
				{
					retStr = accessInfoTbl.Checkout(con, transaction, bean);
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

		return retStr;
	}
	// 2010.09.10 Add K.Shinohara End
	*/

	/// <summary>
	/// チェックアウトの解除を行う
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RisID</param>
	/// <returns>【アンチェックアウト成功】true　【アンチェックアウト失敗】false</returns>
	public boolean Uncheckout(String risID, Connection con) throws Exception
	{
		// paremeters
		boolean retFlg = false;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// start log
		logger.debug("begin");

		try
		{
			if (risID != null && risID.length() > 0)
			{
				retFlg = accessInfoTbl.UnCheckout(con, risID);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	// 2019.10.03 Add Cosmo Start 排他ロック対応
	/// <summary>
	/// チェックアウトの解除を行う
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RisID</param>
	/// <param name="risID">RisID</param>
	/// <returns>【アンチェックアウト成功】true　【アンチェックアウト失敗】false</returns>
	public boolean UncheckoutLoginLock(String ipAddress,String appID, Connection con) throws Exception
	{
		// paremeters
		boolean retFlg = false;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// start log
		logger.debug("begin");

		try
		{
			if (ipAddress != null && appID != null && ipAddress.length() > 0 && appID.length() > 0)
			{
				retFlg = accessInfoTbl.UnCheckoutByIpAndAppID(con, ipAddress,appID);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	// 2019.10.03 Add Cosmo End 排他ロック対応
	/*
	// 2010.09.09 Add K.Shinohara Start
	/// <summary>
	/// チェックアウトの解除を行う
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">アクセス情報</param>
	/// <returns>【アンチェックアウト成功】true　【アンチェックアウト失敗】false</returns>
	public boolean Uncheckout(AccessInfoBean bean)
	{
		// paremeters
		boolean retFlg = false;
		Connection con = null;
		Transaction transaction = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// start log
		logger.debug("begin");

		try
		{
			if (bean != null)
			{
				// get connection
				con = DBConnectionFactory.GetInstance().GetRisDBConnection();

				// begin transaction
				transaction = con.BeginTransaction();

				retFlg = accessInfoTbl.UnCheckout(con, transaction, bean);

				// commit
				transaction.Commit();
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutCheckoutIDException_MessageString) + bean.GetID(), e);

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

		return retFlg;
	}
	// 2010.09.09 Add K.Shinohara End
	*/

	/*
	/// <summary>
	/// 当該IPアドレスでロックしている全データのロックを解除する
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public void UncheckoutAll()
	{
		// paremeters
		Connection con = null;
		Transaction transaction = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();
		String ipAddressStr = "";

		// start log
		logger.debug("begin");

		try
		{
			// get connection
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();

			// begin transaction
			transaction = con.BeginTransaction();

			//IPアドレスを取得
			if (Configuration.GetInstance().GetUserAccountBean() != null)
			{
				ipAddressStr = Configuration.GetInstance().GetUserAccountBean().GetIPAddress();
			}
			else
			{
				ipAddressStr = Configuration.GetInstance().GetIPAddress();
				//ipAddressStr = (Dns.GetHostEntry(Dns.GetHostName()).AddressList[0]).toString();
			}

			accessInfoTbl.UncheckoutAll(con, transaction, ipAddressStr);

			// commit
			transaction.Commit();
		}
		catch (Exception e)
		{
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutIPException_MessageString);
			logger.fatal(message + ipAddressStr, e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					String message10 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message10, e1);
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

		return;
	}
	*/

	/*
	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	/// <summary>
	/// 掲示板全データのチェックアウト取消し
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public void UncheckoutAllMsgData()
	{
		// paremeters
		Connection con = null;
		Transaction transaction = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();
		String ipAddressStr = "";

		// start log
		logger.debug("begin");

		try
		{
			// get connection
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();

			// begin transaction
			transaction = con.BeginTransaction();

			//IPアドレスを取得
			if (Configuration.GetInstance().GetUserAccountBean() != null)
			{
				ipAddressStr = Configuration.GetInstance().GetUserAccountBean().GetIPAddress();
			}
			else
			{
				ipAddressStr = Configuration.GetInstance().GetIPAddress();
			}

			accessInfoTbl.UncheckoutAllMsgData(con, transaction, ipAddressStr);

			// commit
			transaction.Commit();
		}
		catch (Exception e)
		{
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutIPException_MessageString);
			logger.fatal(message + ipAddressStr, e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					String message10 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message10, e1);
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

		return;
	}
	// 2011.02.16 Add T.Nishikubo End
	*/

	/*
	// 2011.02.28 Add T.Nishikubo Start
	/// <summary>
	/// 端末メモのチェックアウトの解除を行う
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">アクセス情報</param>
	/// <returns>【アンチェックアウト成功】true　【アンチェックアウト失敗】false</returns>
	public boolean UncheckoutTerminalMemoData(AccessInfoBean bean)
	{
		// paremeters
		boolean retFlg = false;
		Connection con = null;
		Transaction transaction = null;
		RisAccessInfoTbl accessInfoTbl = new RisAccessInfoTbl();

		// start log
		logger.debug("begin");

		try
		{
			if (bean != null)
			{
				// get connection
				con = DBConnectionFactory.GetInstance().GetRisDBConnection();

				// begin transaction
				transaction = con.BeginTransaction();

				retFlg = accessInfoTbl.UnCheckoutTerminalMemoData(con, transaction, bean);

				// commit
				transaction.Commit();
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutCheckoutIDException_MessageString) + bean.GetID(), e);

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

		return retFlg;
	}
	// 2011.02.28 Add T.Nishikubo End
	*/
}
