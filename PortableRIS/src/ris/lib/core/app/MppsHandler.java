package ris.lib.core.app;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.Configuration;
import ris.lib.core.database.MPMppsMasterTbl;
import ris.lib.core.util.MessageParameter;
import ris.portable.util.DataTable;

/// <summary>
///
/// MPPS管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.25	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MppsHandler
{
	public static Log logger = LogFactory.getLog(MppsHandler.class);

	/*
	/// <summary>
	/// MPPS定義情報Hashを取得する
	/// </summary>
	/// <returns></returns>
	public Hashtable GetMppsDefineHash()
	{
		// parameters
		Hashtable retHash = new Hashtable();

		Connection	con				= null;
		Transaction	transaction		= null;
		MPMppsDefineTbl		mppsDefineTbl	= new MPMppsDefineTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null)
			{
				//MPPS定義情報を取得する
				DataTable mppsDt = mppsDefineTbl.GetMppsDefineData(con, transaction);

				//情報のループ
				for (int i=0; i<mppsDt.Rows.Count; i++)
				{
					DataRow row = mppsDt.Rows[i];
					String aeTitle = row[MPMppsDefineTbl.AE_TITLE_COLUMN].toString();

					if (!retHash.ContainsKey(aeTitle))
					{
						//Hashへ追加
						retHash.Add(aeTitle, row);
					}
				}

				//commit
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
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}
			throw ex;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retHash;
	}

	/// <summary>
	/// 検査系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="acNo">AccesionNo</param>
	/// <returns></returns>
	public DataTable GetKensaMppsMasterData(String acNo)
	{
		// parameters
		DataTable retDt = null;

		Connection	con				= null;
		Transaction	transaction		= null;
		MPMppsMasterTbl		mppsMasterTbl	= new MPMppsMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetMppsDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && acNo.length() > 0)
			{
				//検査系-MPPSマスタ情報を取得する
				retDt = mppsMasterTbl.GetKensaMppsMasterData(con, transaction, acNo);

				//commit
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
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}
			throw ex;
		}
		finally
		{
			////実際に開放
			//try
			//{
			//    con.Close();
			//    con.Dispose();
			//}
			//catch (Exception e)
			//{
			//    String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.disposeOleDbConn_NG_MessageString);
			//    logger.fatal(message, e);
			//}
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retDt;
	}

	/// <summary>
	/// 検査系-MPPS照射情報を取得する
	/// </summary>
	/// <param name="acNo">AccesionNo</param>
	/// <returns></returns>
	public DataTable GetKensaMppsExposureDoseData(String acNo)
	{
		// parameters
		DataTable retDt = null;

		Connection	con				= null;
		Transaction	transaction		= null;
		MPExposureDoseTbl	exposureDoseTbl	= new MPExposureDoseTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetMppsDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && acNo.length() > 0)
			{
				//検査系-MPPS照射情報を取得する
				retDt = exposureDoseTbl.GetKensaExposureDoseData(con, transaction, acNo);

				//commit
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
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}
			throw ex;
		}
		finally
		{
			////実際に開放
			//try
			//{
			//    con.Close();
			//    con.Dispose();
			//}
			//catch (Exception e)
			//{
			//    String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.disposeOleDbConn_NG_MessageString);
			//    logger.fatal(message, e);
			//}
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/// <summary>
	/// 撮影系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="acNo">AccesionNo</param>
	/// <returns></returns>
	public DataTable GetSatueiMppsMasterData(String acNo, Connection con) throws Exception
	{
		// parameters
		DataTable retDt = null;

		MPMppsMasterTbl		mppsMasterTbl	= new MPMppsMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && acNo.length() > 0)
			{
				//撮影系-MPPSマスタ情報を取得する
				retDt = mppsMasterTbl.GetSatueiMppsMasterData(con, acNo);

				//commit
				con.commit();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);

			// 失敗した場合はROLLBACK
			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}
			throw ex;
		}
		finally
		{
			////実際に開放
			//try
			//{
			//    con.Close();
			//    con.Dispose();
			//}
			//catch (Exception e)
			//{
			//    String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.disposeOleDbConn_NG_MessageString);
			//    logger.fatal(message, e);
			//}
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retDt;
	}

	/// <summary>
	/// 撮影系-MPPS実績情報を取得する
	/// </summary>
	/// <param name="sopInstanceUID">MPPSSOPInstanceUID</param>
	/// <returns></returns>
	public DataTable GetSatueiMppsResultData(String sopInstanceUID, Connection con) throws Exception
	{
		// parameters
		DataTable retDt = null;

		MPMppsMasterTbl		mppsMasterTbl	= new MPMppsMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && sopInstanceUID.length() > 0)
			{
				//撮影系-MPPS実績情報を取得する
				retDt = mppsMasterTbl.GetSatueiMppsResultData(con, sopInstanceUID);

				//commit
				con.commit();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);

			// 失敗した場合はROLLBACK
			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}
			throw ex;
		}
		finally
		{
			////実際に開放
			//try
			//{
			//    con.Close();
			//    con.Dispose();
			//}
			//catch (Exception e)
			//{
			//    String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.disposeOleDbConn_NG_MessageString);
			//    logger.fatal(message, e);
			//}
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retDt;
	}
}
