package ris.lib.mwm.core;

import java.util.ArrayList;

import ris.lib.mwm.app.MWMHandler;
import ris.lib.mwm.bean.ConnectionInfoBean;
import ris.lib.mwm.database.MwmDBConnectionFactory;
import ris.lib.mwm.util.MwmMasterUtil;
import ris.portable.util.DataTable;

// <summary>
/// MWM用マネージャクラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class MwmManager
{
	private ConnectionInfoBean conBean = null;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <param name="bean">接続情報</param>
	/// <param name="logList">ログリスト</param>
	public MwmManager(ConnectionInfoBean bean, ArrayList logList)
	{
		try
		{
			logList.add("MwmManager.MwmManager-begin");

			//接続情報保持
			this.conBean = bean;

			//MWM用接続情報を設定する
			MwmDBConnectionFactory.GetInstance().SetMwmConnectionInfo(this.conBean);
		}
		catch (Exception ex)
		{
			logList.add(ex.toString());
		}
		finally
		{
			logList.add("MwmManager.MwmManager-end");
		}
	}

	/// <summary>
	/// Mwm用DBに接続できるかチェックする
	/// </summary>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public boolean CheckMWMDBConnection(ArrayList logList)
	{
		//DB接続
		MwmDBConnectionFactory.GetInstance().GetMwmDBConnection(logList);

		return MwmDBConnectionFactory.GetInstance().GetMwmDBStatus();
	}

	/// <summary>
	/// MWM情報を取得する
	/// </summary>
	/// <param name="studyID">Study_Instance_UID</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public DataTable GetMWMInfo(String studyID, ArrayList logList)
	{
		DataTable retDt = null;
		try
		{
			// begin log
			logList.add("MwmManager.GetMWMInfo-begin");

			MWMHandler mwmHandler = new MWMHandler();

			//MWM情報を取得する
			retDt = mwmHandler.GetMWMData(studyID, logList);
			if (retDt != null)
			{
				//成功
				logList.add("MwmManager.GetMWMInfo-SUCCESS");
			}
			else
			{
				//失敗
				logList.add("MwmManager.GetMWMInfo-ERROR");
			}
		}
		catch (Exception ex)
		{
			logList.add(ex.toString());
		}
		finally
		{
			// end log
			logList.add("MwmManager.GetMWMInfo-end");
		}
		return retDt;
	}

	/// <summary>
	/// MWM情報を登録する
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="logList">ログリスト</param>
	/// <param name="deleteMode">削除モード</param>
	/// <returns></returns>
	public boolean RegisterMWMInfo(ArrayList mwmBeanList, ArrayList logList, String deleteMode)
	{
		boolean retBool = false;
		try
		{
			// begin log
			logList.add("MwmManager.RegisterMWMInfo-begin");
			logList.add("MwmManager.RegisterMWMInfo-deleteMode:" + deleteMode);

			MWMHandler mwmHandler = new MWMHandler();

			if (MwmMasterUtil.DELETE_MODE_ACCNO.equals(deleteMode))
			{
				logList.add("MwmManager.RegisterMWMInfo-DELETE_MODE_ACCNO");

				//新しいMWM情報を登録する
				retBool = mwmHandler.RegistMWMDataDeleteAccNo(mwmBeanList, logList);
				if (retBool)
				{
					//成功
					logList.add("MwmManager.RegisterMWMInfo-SUCCESS");
				}
				else
				{
					//失敗
					logList.add("MwmManager.RegisterMWMInfo-ERROR");
				}
			}
			else if (MwmMasterUtil.DELETE_MODE_AETITLE.equals(deleteMode))
			{
				logList.add("MwmManager.RegisterMWMInfo-DELETE_MODE_AETITLE");

				//新しいMWM情報を登録する
				retBool = mwmHandler.RegistMWMDataDeleteAETitle(mwmBeanList, logList);
				if (retBool)
				{
					//成功
					logList.add("MwmManager.RegisterMWMInfo-SUCCESS");
				}
				else
				{
					//失敗
					logList.add("MwmManager.RegisterMWMInfo-ERROR");
				}
            }
			// 2010.07.30 Add K.Shinohara Start
            else if (MwmMasterUtil.DELETE_MODE_NONE.equals(deleteMode))
            {
                logList.add("MwmManager.RegisterMWMInfo");

                //新しいMWM情報を登録する
                retBool = mwmHandler.RegistMWMData(mwmBeanList, logList);
                if (retBool)
                {
                    //成功
                    logList.add("MwmManager.RegisterMWMInfo-SUCCESS");
                }
                else
                {
                    //失敗
                    logList.add("MwmManager.RegisterMWMInfo-ERROR");
                }
            }
			// 2010.07.30 Add K.Shinohara End
		}
		catch (Exception ex)
		{
			logList.add(ex.toString());
		}
		finally
		{
			// end log
			logList.add("MwmManager.RegisterMWMInfo-end");
		}
		return retBool;
	}

	/// <summary>
	/// MWM情報を削除する
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public boolean DeleteMWMInfo(ArrayList mwmBeanList, ArrayList logList)
	{
		boolean retBool = false;
		try
		{
			// begin log
			logList.add("MwmManager.DeleteMWMInfo-begin");

			MWMHandler mwmHandler = new MWMHandler();

			//新しいMWM情報を削除する
			retBool = mwmHandler.DeleteMWMDataAccNo(mwmBeanList, logList);
			if (retBool)
			{
				//成功
				logList.add("MwmManager.DeleteMWMInfo-SUCCESS");
			}
			else
				{
				//失敗
				logList.add("MwmManager.DeleteMWMInfo-ERROR");
			}
		}
		catch (Exception ex)
		{
			logList.add(ex.toString());
		}
		finally
		{
			// end log
			logList.add("MwmManager.DeleteMWMInfo-end");
		}
		return retBool;
	}

	/// <summary>
	/// MWM情報を削除する
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="logList">ログリスト</param>
	/// <returns></returns>
	public boolean DeleteMWMInfoAETitle(ArrayList mwmBeanList, ArrayList logList)
	{
		boolean retBool = false;
		try
		{
			// begin log
			logList.add("MwmManager.DeleteMWMInfoAETitle-begin");

			MWMHandler mwmHandler = new MWMHandler();

			//新しいMWM情報を削除する
			retBool = mwmHandler.DeleteMWMDataAETitle(mwmBeanList, logList);
			if (retBool)
			{
				//成功
				logList.add("MwmManager.DeleteMWMInfoAETitle-SUCCESS");
			}
			else
			{
				//失敗
				logList.add("MwmManager.DeleteMWMInfoAETitle-ERROR");
			}
		}
		catch (Exception ex)
		{
			logList.add(ex.toString());
		}
		finally
		{
			// end log
			logList.add("MwmManager.DeleteMWMInfoAETitle-end");
		}
		return retBool;
	}
}
