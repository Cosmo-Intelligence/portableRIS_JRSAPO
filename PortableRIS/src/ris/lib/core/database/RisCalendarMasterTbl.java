package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import ris.lib.core.Configuration;
import ris.portable.util.DataTable;

/// <summary>
///
/// 祝日情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.12	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisCalendarMasterTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "CALENDARMASTER";
	private static final String TABLE_CAPTION = "";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String HIZUKE_COLUMN	= "HIZUKE";
	public static final String BIKO_COLUMN		= "BIKO";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisCalendarMasterTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// 祝日情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="date">日付</param>
	/// <returns>祝日情報</returns>
	public DataTable GetHolidayData(Connection con, Timestamp date) throws Exception
	{
		// parameters
		DataTable retList = null;

		// start log
		logger.debug("begin");

		if (con != null)
		{
			String sqlText = CreateSelectSQL(date);
			retList = Select(con, sqlText, null);
		}

		// end log
		logger.debug("end");

		return retList;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="date">日付</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(Timestamp date)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		this.AddColValue(HIZUKE_COLUMN, new SimpleDateFormat("yyyy/MM/dd").format(date), true, SignType.Equal);

		logger.debug("end");

		return this.GetSelectSQL();
	}
}
