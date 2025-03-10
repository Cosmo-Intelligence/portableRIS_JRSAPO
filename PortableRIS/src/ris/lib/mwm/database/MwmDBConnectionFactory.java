package ris.lib.mwm.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.mwm.bean.ConnectionInfoBean;
import ris.lib.mwm.util.MwmMessageParameter;
import ris.portable.database.DataBase;

/// <summary>
///
/// DB管理クラス
/// データベースコネクションを生成・破棄するクラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class MwmDBConnectionFactory {
	// / <summary>
	// / Singletonのインスタンス
	// / </summary>
	private static MwmDBConnectionFactory instance = null;
	//
	// / <summary>
	// / ロックオブジェクト
	// / </summary>
	private static Object syncRoot = new Object();

	Connection mwmConnection = null;

	// 接続情報
	private String service = "";
	private String user = "";
	private String password = "";

	// / <summary>
	// / private コンストラクタ
	// / </summary>
	protected MwmDBConnectionFactory() {
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
	}

	//
	// / <summary>
	// / シングルトンを取得する
	// / </summary>
	// / <returns>Singletonのインスタンス</returns>
	public static MwmDBConnectionFactory GetInstance() {
		synchronized (syncRoot) {
			if (instance == null) {
				instance = new MwmDBConnectionFactory();
			}
		}

		return instance;
	}

	// / <summary>
	// / MWM用接続情報を設定する
	// / </summary>
	// / <param name="conBean"></param>
	public void SetMwmConnectionInfo(ConnectionInfoBean conBean) {
		if (conBean != null) {
			this.service = conBean.GetService();
			this.user = conBean.GetUser();
			this.password = conBean.GetPassword();
		}
	}

	// / <summary>
	// / MWM用のデータベースコネクションを取得する
	// / </summary>
	// / <returns>MWM用のConnection</returns>
	public Connection GetMwmDBConnection(ArrayList logList) {
		// start log
		logList.add("MwmDBConnectionFactory.GetMwmDBConnection-begin");

		try {
			// 接続状態を確認してから、ＤＢ接続する
			if (mwmConnection != null) {
				ReturnMwmDBConnection(logList);
			}

			//logList.add(MwmMessageParameter.initMwmSource_MessageString
			//		+ this.service);
			//logList.add(MwmMessageParameter.initMwmDbUser_MessageString
			//		+ this.user);
			//logList.add(MwmMessageParameter.initMwmDbPassword_MessageString
			//		+ " - ");

			//mwmConnection = DataBase.getMwmConnection(this.service, this.user, this.password);
			mwmConnection = DataBase.getMwmConnection();

			logList.add(MwmMessageParameter.createOleDbConn_OK_MessageString
					+ mwmConnection.hashCode());

		} catch (Exception e) {
			logList.add(MwmMessageParameter.createOleDbConn_NG_MessageString
					+ e.toString());
		}

		// end log
		logList.add("MwmDBConnectionFactory.GetMwmDBConnection-end");

		return mwmConnection;
	}

	// / <summary>
	// / 使用済みのMWM用データベースコネクションを返却する
	// / </summary>
	public void ReturnMwmDBConnection(ArrayList logList) {
		// start log
		logList.add("MwmDBConnectionFactory.ReturnMwmDBConnection-begin");

		if (mwmConnection != null) {

			try {
				logList.add(MwmMessageParameter.disposeOleDbConn_OK_MessageString
						+ mwmConnection.hashCode());
				DataBase.closeConnection(mwmConnection);
				mwmConnection = null;

			} catch (Exception e) {
				logList.add(MwmMessageParameter.disposeOleDbConn_NG_MessageString
						+ e.toString());
			}
		}

		// end log
		logList.add("MwmDBConnectionFactory.ReturnMwmDBConnection-end");
	}

	// / <summary>
	// / Mwm用DBの現在の接続状態を返す
	// / </summary>
	// / <returns>true:接続中、false:切断中</returns>
	public boolean GetMwmDBStatus() {
		if (mwmConnection != null) {
			return true;
		}
		else {
			return false;
		}
		/*
		if (mwmConnection != null
				&& mwmConnection.State == System.Data.ConnectionState.Open) {
			return true;
		} else {
			return false;
		}
		*/
	}

}
