package ris.lib.core.app;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.database.RisTerminalInfoTbl;

/// <summary>
///
/// 端末データ管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.25	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class TerminalHandler
{
	public static Log logger = LogFactory.getLog(TerminalHandler.class);

	/*
	/// <summary>
	/// 端末情報と端末定義設定の取得
	/// </summary>
	/// <param name="terminalID">端末ID</param>
	/// <returns></returns>
	public TerminalInfoBean GetTerminalData( String terminalID )
	{
		// parameters
		TerminalInfoBean terminalInfoBean = new TerminalInfoBean();

		Connection con = null;
		Transaction transaction = null;
		RisTerminalInfoTbl terminalInfoTbl = new RisTerminalInfoTbl();
		RisTerminalDefineTbl terminalDefineTbl = new RisTerminalDefineTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if( con != null && transaction != null && terminalID != null )
			{
				//端末情報
				terminalInfoBean = terminalInfoTbl.GetTerminalInfoData( con, transaction, terminalID, terminalInfoBean);

				//端末定義設定の取得
				terminalInfoBean = terminalDefineTbl.GetTerminalDefineData( con, transaction, terminalID, terminalInfoBean);

				// すべて成功した場合はCOMMIT
				transaction.Commit();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);

			// 失敗した場合はROLLBACK
			try
			{
				transaction.Rollback();
			}
			catch( Exception e1 )
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1 );
			}
			throw ex;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return terminalInfoBean;
	}
	*/

	/// <summary>
	/// 端末情報の取得
	/// </summary>
	/// <param name="ipaddress">IPAddress</param>
	/// <returns></returns>
	public TerminalInfoBean GetTerminalDataByIPAddress( String ipaddress, Connection con ) throws Exception
	{
		// parameters
		TerminalInfoBean terminalInfoBean = new TerminalInfoBean();

		RisTerminalInfoTbl terminalInfoTbl = new RisTerminalInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if( con != null && ipaddress != null )
			{
				//端末情報
				terminalInfoBean = terminalInfoTbl.GetTerminalInfoDataByIPAdrress( con, ipaddress, terminalInfoBean);
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
			throw ex;
		}

		// end log
		logger.debug("end");

		return terminalInfoBean;
	}

	/*
	/// <summary>
	/// 端末情報を登録(Insert/Update)する
	/// </summary>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
	public boolean RegisterTerminalData( TerminalInfoBean terminalBean )
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		Transaction transaction = null;
		RisTerminalInfoTbl terminalInfoTbl = new RisTerminalInfoTbl();
		RisTerminalDefineTbl terminalDefineTbl = new RisTerminalDefineTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if( con != null && transaction != null && terminalBean != null )
			{
				if (terminalBean.GetTerminalID() == "")	//端末IDがないので新規登録
				{
					//端末IDを取得する
					terminalBean.SetTerminalID(terminalInfoTbl.GetNewlID(con, transaction, "TerminalID"));
					//表示順を取得する
					terminalBean.SetShowOrder(terminalInfoTbl.GetNewlID(con, transaction, "ShowOrder"));

					//未登録の時Insert
					retFlg = terminalInfoTbl.InsertTerminalInfoData(con, transaction, terminalBean);
					if (retFlg == true)
					{
						retFlg = terminalDefineTbl.InsertTerminalDefineData(con, transaction, terminalBean);
					}
				}
				else
				{
					//既存のShowOrderを取得する
					terminalBean.SetShowOrder(terminalInfoTbl.GetTerminalShowOrder(con, transaction, terminalBean.GetTerminalID()));

					//登録済みの時Update
					retFlg = terminalInfoTbl.UpdateTerminalInfoData(con, transaction, terminalBean);
					if (retFlg == true)
					{
						retFlg = terminalDefineTbl.UpdateTerminalDefineData(con, transaction, terminalBean);
					}
				}
			}

			// 全ての登録に成功したらCOMMIT
			// そうでない場合はROLLBACK
			if(retFlg)
			{
				transaction.Commit();
			}
			else
			{
				transaction.Rollback();
			}
		}
		catch( Exception e )
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			if( transaction != null )
			{
				try
				{
					transaction.Rollback();
				}
				catch( Exception e1 )
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1 );
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
	*/

	/*
	/// <summary>
	/// IPアドレスより端末IDを取得する
	/// </summary>
	/// <param name="ipAddress">IPAddress</param>
	/// <returns></returns>
	public int GetMyTerminalID(String ipAddress)
	{
		// begin log
		logger.debug("begin");

		int terminalIDInt = -1;

		Connection con = null;
		Transaction transaction = null;
		RisTerminalInfoTbl terminalInfoTbl = new RisTerminalInfoTbl();

		try
		{
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if( con != null && transaction != null && ipAddress != null)
			{
				terminalIDInt = terminalInfoTbl.GetMyTerminalID(con, transaction, ipAddress);
			}

			transaction.Commit();
		}
		catch( Exception e)
		{
			terminalIDInt = -1;
			try
			{
				transaction.Rollback();
			}
			catch( Exception e1 )
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1 );
			}
			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return terminalIDInt;
	}
	*/
}
