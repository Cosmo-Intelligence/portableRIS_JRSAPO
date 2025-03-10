package ris.lib.core.database;

/// <summary>
///
/// MPFilmConsumptionTblテーブル管理クラス(WorkDB用)
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MPFilmConsumptionTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "FILMCONSUMPTION";
	private static final String TABLE_CAPTION	= "";

	// カラム定義
	public static final String VMNO_COLUMN					= "VMNO";
	public static final String MPPSSOPINSTANCEUID_COLUMN	= "MPPSSOPINSTANCEUID";
	public static final String BAMCNUMBEROFFILMS_COLUMN		= "BAMCNUMBEROFFILMS";
	public static final String BAMCMEDIUMTYPE_COLUMN		= "BAMCMEDIUMTYPE";
	public static final String BAMCFILMSIZEID_COLUMN		= "BAMCFILMSIZEID";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MPFilmConsumptionTbl()
	{
        this.tableNameStr = TABLE_NAME;
	}
}
