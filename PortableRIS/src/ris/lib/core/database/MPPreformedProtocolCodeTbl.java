package ris.lib.core.database;

/// <summary>
///
/// MPPreformedProtocolCodeTblテーブル管理クラス(WorkDB用)
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MPPreformedProtocolCodeTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "PERFORMEDPROTOCOLCODE";
	private static final String TABLE_CAPTION	= "";

	// カラム定義
	public static final String VMNO_COLUMN						= "VMNO";
	public static final String MPPSSOPINSTANCEUID_COLUMN		= "MPPSSOPINSTANCEUID";
	public static final String CODEVALUE_COLUMN				= "CODEVALUE";
	public static final String CODINGSCHEMEDESIGNATOR_COLUMN	= "CODINGSCHEMEDESIGNATOR";
	public static final String CODINGSCHEMEVERSION_COLUMN		= "CODINGSCHEMEVERSION";
	public static final String CODEMEANING_COLUMN				= "CODEMEANING";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MPPreformedProtocolCodeTbl()
	{
        this.tableNameStr = TABLE_NAME;
	}
}
