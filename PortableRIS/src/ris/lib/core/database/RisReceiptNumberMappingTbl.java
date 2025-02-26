package ris.lib.core.database;

import java.sql.Connection;

import org.apache.cxf.common.util.StringUtils;

import ris.portable.util.DataTable;

/// <summary>
///
/// 受付番号管理テーブルに対する操作を行うクラス
///
/// Copyright (C) 2011, Yokogawa Electric Corpration
///
/// (Rev.)		(Date)			(ID / NAME)				(Comment)
/// V2.07.000 :	2011.03.09 :	999999 / T.Ohyama@CIJ :	original MY-1-08
///
/// </summary>
public class RisReceiptNumberMappingTbl extends BaseTbl
{
	// テーブル定義
	public static final String TABLE_NAME					= "RECEIPTNUMBERMAPPINGTABLE";
	public static final String TABLE_CAPTION				= "受付番号ﾏｯﾋﾟﾝｸﾞﾃｰﾌﾞﾙ";

	// カラム定義
	public static final String KEYVALUE_COLUMN				= "KEYVALUE";
	public static final String RECEIPTNUMBER_COLUMN		= "RECEIPTNUMBER";
	public static final String RANDOMRECEIPTNUMBER_COLUMN	= "RANDOMRECEIPTNUMBER";


	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisReceiptNumberMappingTbl()
	{
		this.tableNameStr	= TABLE_NAME;
		this.infoCaption	= TABLE_CAPTION;
	}

	/// <summary>
	/// 指定したKey情報と受付番号からランダム受付番号を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="receiptNumber">受付番号</param>
	/// <returns>取得できなかった場合はnullを返します</returns>
	public String GetRandomReceiptNumber(Connection con, String keyValue,int receiptNumber) throws Exception
	{
		// start log
		logger.debug("begin");

		if( con != null && !StringUtils.isEmpty(keyValue))
		{
			//ランダム値を検索
			DataTable dt = Select(con, CreateSelectSQL(keyValue,receiptNumber), null);

			if( dt != null && 0 < dt.getRowCount() )
			{
				//取得できた場合は直ちに返却
				return dt.getRows().get(0).get(RANDOMRECEIPTNUMBER_COLUMN).toString();
			}
		}
		// end log
		logger.debug("end");

		return null;
	}
	/// <summary>
	/// RECEIPTNUMBERMAPPINGTABLE内を検索してランダム受付番号を取得するSQLを作成する
	/// </summary>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="receiptNumber">受付番号</param>
	/// <returns>取得できなかった場合はnullを返します</returns>
	private String CreateSelectSQL(String keyValue, int receiptNumber)
	{
		// start log
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		this.AddColValue(KEYVALUE_COLUMN		, keyValue		, true , SignType.Equal );
		this.AddColValue(RECEIPTNUMBER_COLUMN	, receiptNumber , true , SignType.Equal );

		// end log
		logger.debug("end");
		return this.GetSelectSQL();
	}
}
