package ris.lib.core.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/// <summary>
/// チーム情報管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.00.00	: 2010.07.30	: / T.Ootsuka			: original
///
/// </summary>
public class TeamInfoHandler
{
	public static Log logger = LogFactory.getLog(TeamInfoHandler.class);

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public TeamInfoHandler()
	{
		//
	}

	/*
	/// <summary>
	/// オーダ情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public TeamInfoBean GetTeamInfoData(String terminalID)
	{
		// parameters
		Connection con	= null;
		Transaction transaction	= null;
		RisTeamInfoTbl teamInfoTbl		= new RisTeamInfoTbl();
		TeamInfoBean teamInfoBean		= null;

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con			= DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction	= con.BeginTransaction();

			if ((con != null) && (transaction != null) && (terminalID != null))
			{
				teamInfoBean = teamInfoTbl.GetTeamInfoBean(con, transaction, terminalID);
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

		return teamInfoBean;
	}
	*/

	/*
	/// <summary>
	/// チーム情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象チーム情報のTerminal</param>
	/// <returns></returns>
	public boolean DeleteTeamInfoData(String terminalID, String WindowappId)
	{
		// parameters
		boolean retFlg = false;
		RisTeamInfoTbl teamInfoTbl		= new RisTeamInfoTbl();
		Connection con			= null;
		Transaction trans			= null;

		// begin log
		logger.debug("begin");
		try
		{
			con			= DBConnectionFactory.GetInstance().GetRisDBConnection();
			trans		= con.BeginTransaction();
			if ((con != null) && (trans != null) && (terminalID != null) && (terminalID.length() > 0))
			{
				retFlg = teamInfoTbl.DeleteTeamInfoData(con, trans, terminalID, WindowappId);
			}

			// commit
			trans.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (trans != null)
			{
				try
				{
					trans.Rollback();
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

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// チーム情報の更新
	/// </summary>
	/// <param name="orderInfoBean">オーダ情報</param>
	/// <returns></returns>
	public boolean UpdateRisTeamInfo(TeamInfoBean teamInfoBean)
	{
		// parameters
		boolean retBool = false;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisTeamInfoTbl risTeamInfoTbl = new RisTeamInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if ((rCon != null) && (rTransaction != null) && (teamInfoBean != null))
			{
				retBool = risTeamInfoTbl.UpdateTeamInfo(rCon, rTransaction, teamInfoBean);
				TeamInfoBean teamInfoBeantest = risTeamInfoTbl.GetTeamInfoBean(rCon, rTransaction, teamInfoBean.GetTerminalID().toString());
			}

			// commit
			rTransaction.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (rTransaction != null)
			{
				try
				{
					rTransaction.Rollback();
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
			DBConnectionFactory.GetInstance().ReturnDBConnection(rCon);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// チーム情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="teamInfoBean">DBトランザクションオブジェクト</param>
	/// <returns>チーム情報</returns>
	public boolean InsertNewTeamInfo(TeamInfoBean teamInfoBean)
	{
		// parameters
		boolean retBool = false;
		RisTeamInfoTbl risTeamInfoTbl = new RisTeamInfoTbl();
		Connection con			= null;
		Transaction trans			= null;

		// begin log
		logger.debug("begin");
		try
		{
			con			= DBConnectionFactory.GetInstance().GetRisDBConnection();
			trans		= con.BeginTransaction();
			if ((con != null) && (trans != null) && (teamInfoBean != null))
			{
				// TEAMINFOTABLE 更新
				retBool = risTeamInfoTbl.InsertTeamInfoData(con, trans, teamInfoBean);
			}

			// commit
			trans.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (trans != null)
			{
				try
				{
					trans.Rollback();
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
