package ris.lib.mwm.app;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.mwm.bean.MWMInfoBean;
import ris.lib.mwm.database.MwmDBConnectionFactory;
import ris.lib.mwm.database.WorkListInfoTbl;
import ris.lib.mwm.util.MwmMessageParameter;
import ris.portable.util.DataTable;

/// <summary>
/// MWMHandlerクラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2008.09.02	: 082898 / H.Orikasa	: original
/// V1.00.08	: 2008.11.03	: 112478 / A.Kobayashi	: MWM情報の削除関数追加
///
/// </summary>
public class MWMHandler
{
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MWMHandler()
	{
		//
	}

	/// <summary>
	/// MWM情報を取得する
	/// </summary>
	/// <param name="studyID">Study_Instance_UID</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public DataTable GetMWMData(String studyID, ArrayList logList) throws Exception
	{
		// parameters
		DataTable retDt = null;
		Connection con = null;
		WorkListInfoTbl workListTbl = new WorkListInfoTbl();

		// begin log
		logList.add("MWMHandler.GetMWMData-begin");

		try
		{
			// MWM側のコネクションを取得
			con = MwmDBConnectionFactory.GetInstance().GetMwmDBConnection(logList);

			if (studyID.length() > 0)
			{
				//取得を行う
				retDt = workListTbl.SelectMWMData(con, studyID, logList);
			}

			// すべて成功した場合はCOMMIT
			con.commit();
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			logList.add(MwmMessageParameter.dbTransactionError_MessageString + e.toString());

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				logList.add(MwmMessageParameter.dbRollBackError_MessageString + e1.toString());
			}

			throw e;
		}
		finally
		{
			MwmDBConnectionFactory.GetInstance().ReturnMwmDBConnection(logList);
		}

		// end log
		logList.add("MWMHandler.GetMWMData-end");

		return retDt;
	}

	// 2010.07.30 Add K.Shinohara Start
    /// <summary>
    /// 新しいMWM情報を登録する
    /// </summary>
    /// <param name="mwmBeanList">MWM情報リスト</param>
    /// <param name="logList">ログリスト</param>
    /// <returns></returns>
    public boolean RegistMWMData(ArrayList mwmBeanList, ArrayList logList) throws Exception
    {
        // parameters
        boolean retFlg = false;
        Connection con = null;
        WorkListInfoTbl workListTbl = new WorkListInfoTbl();

        // begin log
        logList.add("MWMHandler.RegistMWMData-begin");

        try
        {
            // MWM側のコネクションを取得
            con = MwmDBConnectionFactory.GetInstance().GetMwmDBConnection(logList);

            for (int i = 0; i < mwmBeanList.size(); i++)
            {
                MWMInfoBean mwmInfo = (MWMInfoBean)mwmBeanList.get(i);

                if (mwmInfo != null)
                {
                    //登録を行う
                    retFlg = workListTbl.InsertMWMData(con, mwmInfo, logList);
                    if (retFlg == false)
                    {
                        throw new Exception();
                    }
                }
            }

            // すべて成功した場合はCOMMIT
            con.commit();
        }
        catch (Exception e)
        {
            // Exceptionが出たら戻り値をfalseに設定する
            retFlg = false;
            logList.add(MwmMessageParameter.dbTransactionError_MessageString + e.toString());

            try
            {
                con.rollback();
            }
            catch (Exception e1)
            {
                logList.add(MwmMessageParameter.dbRollBackError_MessageString + e1.toString());
            }

            throw e;
        }
        finally
        {
            MwmDBConnectionFactory.GetInstance().ReturnMwmDBConnection(logList);
        }

        // end log
        logList.add("MWMHandler.RegistMWMData-end");

        return retFlg;
    }
	// 2010.07.30 Add K.Shinohara End

	/// <summary>
	/// 新しいMWM情報を登録する（機器単位）
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public boolean RegistMWMDataDeleteAETitle( ArrayList mwmBeanList, ArrayList logList ) throws Exception
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		WorkListInfoTbl workListTbl = new WorkListInfoTbl();

		// begin log
		logList.add("MWMHandler.RegistMWMDataDeleteAETitle-begin");

		try
		{
			// MWM側のコネクションを取得
			con = MwmDBConnectionFactory.GetInstance().GetMwmDBConnection(logList);

			for (int i=0; i<mwmBeanList.size(); i++)
			{
				MWMInfoBean mwmInfo = (MWMInfoBean)mwmBeanList.get(i);

				if (mwmInfo != null )
				{
					//削除を行う
					retFlg = workListTbl.DeleteMWMDataByAETitle( con, mwmInfo, logList );
					if( retFlg == false )
					{
						throw new Exception();
					}
				}
			}

			for (int i=0; i<mwmBeanList.size(); i++)
			{
				MWMInfoBean mwmInfo = (MWMInfoBean)mwmBeanList.get(i);

				if (mwmInfo != null )
				{
					//登録を行う
					retFlg = workListTbl.InsertMWMData( con, mwmInfo, logList );
					if( retFlg == false )
					{
						throw new Exception();
					}
				}
			}

			// すべて成功した場合はCOMMIT
			con.commit();
		}
		catch( Exception e )
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logList.add(MwmMessageParameter.dbTransactionError_MessageString + e.toString());

			try
			{
				con.rollback();
			}
			catch( Exception e1 )
			{
				logList.add(MwmMessageParameter.dbRollBackError_MessageString + e1.toString());
			}

			throw e;
		}
		finally
		{
			MwmDBConnectionFactory.GetInstance().ReturnMwmDBConnection(logList);
		}

		// end log
		logList.add("MWMHandler.RegistMWMDataDeleteAETitle-end");

		return retFlg;
	}

	/// <summary>
	/// 新しいMWM情報を登録する
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public boolean RegistMWMDataDeleteAccNo( ArrayList mwmBeanList, ArrayList logList ) throws Exception
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		WorkListInfoTbl workListTbl = new WorkListInfoTbl();

		// begin log
		logList.add("MWMHandler.RegistMWMDataDeleteAccNo-begin");

		try
		{
			// MWM側のコネクションを取得
			con = MwmDBConnectionFactory.GetInstance().GetMwmDBConnection(logList);

			for (int i=0; i<mwmBeanList.size(); i++)
			{
				MWMInfoBean mwmInfo = (MWMInfoBean)mwmBeanList.get(i);

				if (mwmInfo != null )
				{
					//削除を行う
					retFlg = workListTbl.DeleteMWMDataByAccNo( con, mwmInfo, logList );
					if( retFlg == false )
					{
						throw new Exception();
					}
				}
			}

			for (int i=0; i<mwmBeanList.size(); i++)
			{
				MWMInfoBean mwmInfo = (MWMInfoBean)mwmBeanList.get(i);

				if (mwmInfo != null )
				{
					//登録を行う
					retFlg = workListTbl.InsertMWMData( con, mwmInfo, logList );
					if( retFlg == false )
					{
						throw new Exception();
					}
				}
			}

			// すべて成功した場合はCOMMIT
			con.commit();
		}
		catch( Exception e )
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logList.add(MwmMessageParameter.dbTransactionError_MessageString);

			try
			{
				con.rollback();
			}
			catch( Exception e1 )
			{
				logList.add(MwmMessageParameter.dbRollBackError_MessageString + e1.toString());
			}

			throw e;
		}
		finally
		{
			MwmDBConnectionFactory.GetInstance().ReturnMwmDBConnection(logList);
		}

		// end log
		logList.add("MWMHandler.RegistMWMDataDeleteAccNo-end");

		return retFlg;
	}

	/// <summary>
	/// MWM情報を削除する
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public boolean DeleteMWMDataAccNo( ArrayList mwmBeanList, ArrayList logList ) throws Exception
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		WorkListInfoTbl workListTbl = new WorkListInfoTbl();

		// begin log
		logList.add("MWMHandler.DeleteMWMDataAccNo-begin");

		try
		{
			// MWM側のコネクションを取得
			con = MwmDBConnectionFactory.GetInstance().GetMwmDBConnection(logList);

			for (int i=0; i<mwmBeanList.size(); i++)
			{
				MWMInfoBean mwmInfo = (MWMInfoBean)mwmBeanList.get(i);

				if (mwmInfo != null )
				{
					//削除を行う
					retFlg = workListTbl.DeleteMWMDataByAccNo( con, mwmInfo, logList );
					if( retFlg == false )
					{
						throw new Exception();
					}
				}
			}

			// すべて成功した場合はCOMMIT
			con.commit();
		}
		catch( Exception e )
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logList.add(MwmMessageParameter.dbTransactionError_MessageString);

			try
			{
				con.rollback();
			}
			catch( Exception e1 )
			{
				logList.add(MwmMessageParameter.dbRollBackError_MessageString + e1.toString());
			}

			throw e;
		}
		finally
		{
			MwmDBConnectionFactory.GetInstance().ReturnMwmDBConnection(logList);
		}

		// end log
		logList.add("MWMHandler.DeleteMWMDataAccNo-end");

		return retFlg;
	}

	/// <summary>
	/// MWM情報を削除する
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public boolean DeleteMWMDataAETitle(ArrayList mwmBeanList, ArrayList logList) throws Exception
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		WorkListInfoTbl workListTbl = new WorkListInfoTbl();

		// begin log
		logList.add("MWMHandler.DeleteMWMDataAETitle-begin");

		try
		{
			// MWM側のコネクションを取得
			con = MwmDBConnectionFactory.GetInstance().GetMwmDBConnection(logList);

			for (int i=0; i<mwmBeanList.size(); i++)
			{
				MWMInfoBean mwmInfo = (MWMInfoBean)mwmBeanList.get(i);

				if (mwmInfo != null)
				{
					//削除を行う
					retFlg = workListTbl.DeleteMWMDataByAETitle(con, mwmInfo, logList);
					if (retFlg == false)
					{
						throw new Exception();
					}
				}
			}

			// すべて成功した場合はCOMMIT
			con.commit();
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logList.add(MwmMessageParameter.dbTransactionError_MessageString);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				logList.add(MwmMessageParameter.dbRollBackError_MessageString + e1.toString());
			}

			throw e;
		}
		finally
		{
			MwmDBConnectionFactory.GetInstance().ReturnMwmDBConnection(logList);
		}

		// end log
		logList.add("MWMHandler.DeleteMWMDataAETitle-end");

		return retFlg;
	}

	/*
	/// <summary>
	///
	/// </summary>
	private class ErrorCatchException extends ApplicationException {

	}
	*/
}
