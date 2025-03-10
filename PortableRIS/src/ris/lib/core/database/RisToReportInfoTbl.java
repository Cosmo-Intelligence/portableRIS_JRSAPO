package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ToHisInfoBean;

/// <summary>
///
/// ToReportInfoテーブル管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.05	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisToReportInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final  String TABLE_NAME					= "TOREPORTINFO";
	public static final  String TABLE_CAPTION				= "REPORT通信情報";
	public static String risTableNameStr					= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	public static final String REQUEST_ID_COLUMN			= "REQUESTID";
	public static final String REQUEST_DATE_COLUMN			= "REQUESTDATE";
	public static final String RIS_ID_COLUMN				= "RIS_ID";
	public static final String REQUEST_USER_COLUMN			= "REQUESTUSER";
	public static final String REQUEST_TERMINAL_ID_COLUMN	= "REQUESTTERMINALID";
	public static final String REQUEST_TYPE_COLUMN			= "REQUESTTYPE";
	public static final String MESSAGEID1_COLUMN			= "MESSAGEID1";
	public static final String MESSAGEID2_COLUMN			= "MESSAGEID2";
	public static final String TRANSFER_STATUS_COLUMN		= "TRANSFERSTATUS";
	public static final String TRANSFER_DATE_COLUMN			= "TRANSFERDATE";
	public static final String TRANSFER_RESULT_COLUMN		= "TRANSFERRESULT";
	public static final String TRANSFER_TEXT_COLUMN			= "TRANSFERTEXT";
	//
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisToReportInfoTbl()
	{
		//
	}

	/*
	//
	/// <summary>
	/// 所見送信ﾘｸｴｽﾄ情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>所見送信ﾘｸｴｽﾄ情報</returns>
	public DataTable GetRisToReportInfo(Connection con, Transaction trans, String risID, String requestID)
	{
		// parameters
		DataTable dt = null;
		this.tableNameStr = risTableNameStr;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			String sql = CreateSelectSQL(risID, requestID);
			// 2010.11.04 Mod K.Shinohara Start
			DataTable tempDt = Select(con, trans, sql);
			// 検索結果に対してSortする
			dt = tempDt.Clone();
			DataView dv = new DataView(tempDt);
			dv.Sort = "REQUESTDATE DESC, REQUESTID DESC";	// 2011.02.23 Mod H.Orikasa
			//dv.Sort = "REQUESTDATE DESC";					//
			// ソートされたレコードのコピー
			for (DataRowView drv : dv)
			{
				dt.ImportRow(drv.Row);
			}
			//dt = Select(con, trans, sql);
			// 2010.11.04 Mod K.Shinohara End
		}

		if (dt != null)
		{
			dt.TableName = TABLE_NAME;
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/

	/// <summary>
	/// REPORT送信情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">HIS送信データ</param>
	/// <returns></returns>
	public boolean InsertToReportData( Connection con, ToHisInfoBean bean , String userName) throws Exception
	{
		// parameters
		boolean retFlg = false;
		this.tableNameStr = userName + "." + TABLE_NAME;

		// begin log
		logger.debug("begin");

		if( con != null && bean != null)
		{
			retFlg = Insert( con, CreateInsertSQL(bean), null );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/*
	//
	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String risID, String requestID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true,SignType.Equal);

		if (requestID != null && requestID.length() > 0)
		{
			this.AddColValue(REQUEST_ID_COLUMN, requestID, true,SignType.Equal);
		}
		// 2010.11.04 Del K.Shinohara
		//this.AddOrderKeyDesc(REQUEST_DATE_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	*/

	//
	/// <summary>
	/// インサートSQL
	/// </summary>
	/// <param name="bean">HIS送信データ</param>
	/// <returns></returns>
	private String CreateInsertSQL( ToHisInfoBean bean )
	{
		logger.debug("begin");

		SetInfoValue(bean);

		logger.debug("end");

		return this.GetInsertSQL();
	}

	//
	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="bean"></param>
	private void SetInfoValue( ToHisInfoBean bean )
	{
		logger.debug("begin");

		this.keys.clear();
		try
		{
			this.AddColValue( REQUEST_ID_COLUMN, Integer.parseInt(bean.GetRequestID()) );
			this.AddColSetValue( REQUEST_DATE_COLUMN, SysDateTbl.SYSDATE_COLUMN );
			this.AddColValue( RIS_ID_COLUMN, bean.GetRisID() );
			// 2011.02.17 Mod H.Orikasa Start
			if (bean.GetRequestUser().length() > 10)
			{
				this.AddColValue(REQUEST_USER_COLUMN, bean.GetRequestUser().substring(0, 10));
			}
			else
			{
				this.AddColValue(REQUEST_USER_COLUMN, bean.GetRequestUser());
			}
			//this.AddColValue( REQUEST_USER_COLUMN, bean.GetRequestUser() );
			// 2011.02.17 Mod H.Orikasa End
			this.AddColValue( REQUEST_TERMINAL_ID_COLUMN, bean.GetRequestTerminalID() );
			this.AddColValue( REQUEST_TYPE_COLUMN, bean.GetRequestType() );
			this.AddColValue( MESSAGEID1_COLUMN, bean.GetMessageID1() );
			this.AddColValue( MESSAGEID2_COLUMN, bean.GetMessageID2() );
			this.AddColValue( TRANSFER_STATUS_COLUMN, bean.GetTransferStatus() );
			this.AddColValue( TRANSFER_DATE_COLUMN, bean.GetTransferDate() );
			this.AddColValue( TRANSFER_RESULT_COLUMN, bean.GetTransferResult() );
			this.AddColValue( TRANSFER_TEXT_COLUMN, bean.GetTransferText() );
		}
		catch( Exception e )
		{
			logger.fatal(e);
		}

		logger.debug("end");
	}
}
