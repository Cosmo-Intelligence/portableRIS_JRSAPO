package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.bean.ExamOperationHistoryInfoBean;

/// <summary>
///
/// ExamOperationHistoryテーブル管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.14	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisExamOperationHistoryTbl extends BaseTbl
{

	// アクセステーブル名
	public static final String TABLE_NAME = "EXAMOPERATIONHISTORY";

	// カラム定義
	// ORDERMAINTABLE COLUMN
	public static final String LOG_ID_COLUMN					= "LOG_ID";
	public static final String RIS_ID_COLMUN					= "RIS_ID";
	public static final String OPERATIONTYPE_COLUMN			= "OPERATIONTYPE";
	public static final String OPERATIONTYPE_STR_COLUMN		= "OPERATIONTYPE_STR";
	public static final String OPERATOR_COLUMN					= "OPERATOR";
	public static final String OPERATIONTERMINAL_COLUMN		= "OPERATIONTERMINAL";
	public static final String OPERATIONTIME_COLUMN			= "OPERATIONTIME";


	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisExamOperationHistoryTbl()
	{
		this.tableNameStr = TABLE_NAME;
	}

	/// <summary>
	/// ログ情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">操作ログ情報</param>
	/// <returns></returns>
	public boolean InsertExamOperationHistory( Connection con, ExamOperationHistoryInfoBean bean ) throws Exception
	{
		// parameters
		boolean retFlg = false;

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
	///	 実績操作履歴情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">risID</param>
	/// <returns>実績操作履歴情報</returns>
	public DataTable GetRisExamOperationHistory(Connection con, Transaction trans, String risID)
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			String sql = CreateSelectSQL(risID);
			// 2010.11.04 Mod K.Shinohara Start
			DataTable tempDt = Select(con, trans, sql);
			// 検索結果に対してSortする
			dt = tempDt.Clone();
			DataView dv = new DataView(tempDt);
			dv.Sort = "OPERATIONTIME DESC, LOG_ID DESC";	// 2011.02.23 Mod H.Orikasa
			//dv.Sort = "OPERATIONTIME DESC";				//
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
			//カラム追加
			dt.Columns.Add(OPERATIONTYPE_STR_COLUMN, typeof(String));

			dt.TableName = TABLE_NAME;
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/

	/*
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RIS_ID_COLMUN, risID, true, SignType.Equal);
		// 2010.11.04 Del K.Shinohara
		//this.AddOrderKeyDesc(OPERATIONTIME_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	*/

	//
	/// <summary>
	/// インサートSQL
	/// </summary>
	/// <param name="bean">操作ログ情報</param>
	/// <returns></returns>
	private String CreateInsertSQL( ExamOperationHistoryInfoBean bean )
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
	private void SetInfoValue( ExamOperationHistoryInfoBean bean )
	{
		logger.debug("begin");

		this.keys.clear();
		try
		{
			this.AddColValue( LOG_ID_COLUMN, Integer.parseInt(bean.GetLogID()) );
			this.AddColValue( RIS_ID_COLMUN, bean.GetRisID() );
			this.AddColValue( OPERATIONTYPE_COLUMN, bean.GetOperationType() );
			this.AddColSetValue(OPERATIONTIME_COLUMN, SysDateTbl.SYSDATE_COLUMN );
			this.AddColValue( OPERATOR_COLUMN, bean.GetOperator() );
			this.AddColValue( OPERATIONTERMINAL_COLUMN, bean.GetOperatorminal() );

		}
		catch( Exception e )
		{
			logger.fatal(e);
		}

		logger.debug("end");
	}
}
