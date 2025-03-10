package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ToHisInfoBean;

/// <summary>
///
/// ToHisInfoテーブル管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.05	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisToHisInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final  String TABLE_NAME					= "TOHISINFO";
	public static final  String TABLE_CAPTION				= "HIS通信情報";
	public static String TableNameStr						= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	public static final String REQUEST_ID_COLUMN			= "REQUESTID";
	public static final String REQUEST_DATE_COLUMN			= "REQUESTDATE";
	public static final String RIS_ID_COLUMN				= "RIS_ID";
	public static final String REQUEST_USER_COLUMN			= "REQUESTUSER";
	public static final String REQUEST_TERMINAL_ID_COLUMN	= "REQUESTTERMINALID";
	public static final String REQUEST_TYPE_COLUMN 			= "REQUESTTYPE";
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
	public RisToHisInfoTbl()
	{
		this.tableNameStr = TableNameStr;
	}

	/*
	//
	/// <summary>
	/// 受付通知チェック
	/// </summary>
	/// <param name="con"></param>
	/// <param name="trans"></param>
	/// <param name="bean"></param>
	/// <returns></returns>
	private boolean CheckInsertDataRC01( Connection con, Transaction trans, ToHisInfoBean bean)
	{
		logger.debug("begin");

		boolean ret = false;

		String sqlStr = "Select " + RisToHisInfoTbl.MESSAGEID1_COLUMN + " from " + this.tableNameStr + " ";
		String sqlStr1 = sqlStr + " where " + RisToHisInfoTbl.REQUEST_TYPE_COLUMN + " = '" + CommonString.TOHIS_ORDER_RECEPT + "' ";
		sqlStr1 +=  " and " + RisToHisInfoTbl.MESSAGEID1_COLUMN + " = '" + bean.GetMessageID1() + "' ";
		DataTable dt1 = Select(con, trans, sqlStr1);

		String sqlStr2 = sqlStr + " where REQUESTTYPE = '" + CommonString.TOHIS_ORDER_ABORT_RECEPT + "' ";
		sqlStr2 +=  " and " + RisToHisInfoTbl.MESSAGEID1_COLUMN + " = '" + bean.GetMessageID1() + "' ";
		DataTable dt2 = Select(con, trans, sqlStr2);

		if((dt1 != null)&&(dt2 != null))
		{
			//一致する場合
			if(dt1.Rows.Count == dt2.Rows.Count) ret = true;
			//if(dt1.Rows.Count == dt2.Rows.Count) ret = false;

			//初回は0件
			//if((dt1.Rows.Count == 0)&&(dt2.Rows.Count == 0)) ret = true;
		}

		logger.debug("end");

		return ret;
	}
	*/

	/*
    /// <summary>
	/// 実施通知実行チェック
	/// </summary>
	/// <param name="con"></param>
	/// <param name="trans"></param>
	/// <param name="bean"></param>
	/// <returns></returns>
	private boolean CheckInsertDataOP01( Connection con, Transaction trans, ToHisInfoBean bean)
	{
		logger.debug("begin");

		//自分と同一のORDERNOで、かつ、REQUEST_TYPE==OP01のレコードが
		//存在する場合は、当該照射実施が初回であってもOP02として登録する

		boolean ret = true;
		String sqlStr = "Select " + RisToHisInfoTbl.MESSAGEID1_COLUMN + " from " + this.tableNameStr + " ";
		sqlStr += " where " + RisToHisInfoTbl.REQUEST_TYPE_COLUMN + " = '" + CommonString.TOHIS_ORDER_EXECUTE + "' ";
		sqlStr += " and " + RisToHisInfoTbl.MESSAGEID1_COLUMN +" = '" + bean.GetMessageID1() + "' ";

		DataTable dt = Select(con, trans, sqlStr);
		if((dt != null)&&(dt.Rows.Count > 0)) ret = false;

		logger.debug("end");

		return ret;
	}
	*/

	/*
	/// <summary>
	/// 実施再送通知実行チェック
	/// </summary>
	/// <param name="con"></param>
	/// <param name="trans"></param>
	/// <param name="bean"></param>
	/// <returns></returns>
	private boolean CheckInsertDataOP02( Connection con, Transaction trans, ToHisInfoBean bean)
	{
		logger.debug("begin");

		boolean ret = true;

		logger.debug("end");

		return ret;
	}
	*/

	/// <summary>
	/// HIS送信情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">HIS送信データ</param>
	/// <returns></returns>
	public boolean InsertToHisData( Connection con, ToHisInfoBean bean , String userName) throws Exception
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
	/// <summary>
	/// ToHisInfo情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象ToHisInfoのRisID</param>
	/// <returns>判定</returns>
	public boolean DeleteToHisInfoData(Connection con, Transaction trans, String risID)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			retFlg = ForceDelete(con, trans, CreateDeleteSQL(risID));
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	///	 His送信情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">risID</param>
	/// <returns>His送信情報</returns>
	public ToHisInfoBean GetToHisMaxDate(Connection con, Transaction trans, String risID)
	{
		// parameters
		ToHisInfoBean toHisInfoBean = new ToHisInfoBean();

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null )
		{
			String rcSql = CreateMaxRcSelectSQL(risID);
			DataTable rcDateTable = Select(con, trans, rcSql);

			if (rcDateTable != null && rcDateTable.Rows.Count > 0)
			{
				toHisInfoBean.SetMaxRcDate(rcDateTable.Rows[0][0].toString());
			}

			String oPSql = CreateMaxOpSelectSQL(risID);
			DataTable oPDateTable = Select(con, trans, oPSql);

			if (oPDateTable != null && oPDateTable.Rows.Count > 0)
			{
				toHisInfoBean.SetMaxOpDate(oPDateTable.Rows[0][0].toString());
			}
		}

		// end log
		logger.debug("end");

		return toHisInfoBean;
	}
	*/

	/*
	/// <summary>
	///	 His送信情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">risID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>His送信情報</returns>
	public DataTable GetRisToHisInfo(Connection con, Transaction trans, String risID, String requestID)
	{
		// parameters
		DataTable dt = null;

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

	/*
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="kanjaID"></param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

	/*
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String risID, String requestID)
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

	/*
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateMaxOpSelectSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);

		this.AddColValue(REQUEST_TYPE_COLUMN, CommonString.TOHIS_ORDER_EXECUTE, true, SignType.In);
		this.AddColValue(REQUEST_TYPE_COLUMN, CommonString.TOHIS_ORDER_RE_EXECUTE, true, SignType.In);
		this.AddColValue(REQUEST_TYPE_COLUMN, CommonString.TOHIS_ORDER_ABORT_EXECUTE, true, SignType.In);
		this.AddColValue(TRANSFER_STATUS_COLUMN, "01", true, SignType.Equal);

		String tempSelectSQL = this.GetSelectColmunSQL("MAX(" + TRANSFER_DATE_COLUMN + ")", this.tableNameStr);

		logger.debug("end");

		return tempSelectSQL;
	}
	*/

	/*
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateMaxRcSelectSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		this.AddColValue(REQUEST_TYPE_COLUMN, CommonString.TOHIS_ORDER_RECEPT, true, SignType.Equal);
		this.AddColValue(TRANSFER_STATUS_COLUMN, "01", true, SignType.Equal);

		String tempSelectSQL = this.GetSelectColmunSQL("MAX(" + TRANSFER_DATE_COLUMN + ")", this.tableNameStr);

		logger.debug("end");

		return tempSelectSQL;
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
