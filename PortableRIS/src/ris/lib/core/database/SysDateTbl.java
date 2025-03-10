package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;

import ris.portable.util.DataTable;


/// <summary>
///
/// システム日付の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.12	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class SysDateTbl extends BaseTbl
{
	// アクセステーブル名
	public  static final String TABLE_NAME = "DUAL";
	private static final String TABLE_CAPTION = "";
	private static String TableNameStr	= "RRIS" + "." + TABLE_NAME;

	// カラム定義
	public static final String SYSDATE_COLUMN = "SYSDATE";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public SysDateTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption = TABLE_CAPTION;
	}

	/// <summary>
	/// 現在の時刻を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>現在の時刻</returns>
	public Timestamp GetSysDate(Connection con) throws Exception
	{
		// parameters
		Timestamp retDate = new Timestamp(System.currentTimeMillis());

		// start log
		logger.debug("begin");

		if (con != null)
		{
			String sqlText = CreateSelectSQL();
			DataTable dt = Select(con, sqlText, null);
			if (dt != null && dt.getRowCount() > 0)
			{
				try
				{
					if (dt.getRowCount() > 0) {
						retDate = (Timestamp)dt.getRows().get(0).get(SYSDATE_COLUMN);
					}
				}
				catch (Exception e)
				{
				}
			}
		}

		// end log
		logger.debug("end");

		return retDate;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL()
	{
		return this.GetSelectColmunSQL(SYSDATE_COLUMN, TABLE_NAME);
	}
}
