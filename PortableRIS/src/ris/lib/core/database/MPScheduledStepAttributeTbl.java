package ris.lib.core.database;

/// <summary>
///
/// MPScheduledStepAttributeTblテーブル管理クラス(WorkDB用)
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MPScheduledStepAttributeTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "SCHEDULEDSTEPATTRIBUTE";
	private static final String TABLE_CAPTION	= "";

	// カラム定義
	public static final String VMNO_COLUMN								= "VMNO";
	public static final String MPPSSOPINSTANCEUID_COLUMN				= "MPPSSOPINSTANCEUID";
	public static final String STUDYINSTANCEUID_COLUMN					= "STUDYINSTANCEUID";
	public static final String RSSREFERENCEDSOPCLASSUID_COLUMN			= "RSSREFERENCEDSOPCLASSUID";
	public static final String RSSREFERENCEDSOPINSTANCEUID_COLUMN		= "RSSREFERENCEDSOPINSTANCEUID";
	public static final String ACCESSIONNUMBER_COLUMN					= "ACCESSIONNUMBER";
	public static final String PLACERORDERNUMBERIMAGINGSERVIC_COLUMN	= "PLACERORDERNUMBERIMAGINGSERVIC";
	public static final String FILLERORDERNUMBERIMAGINGSERVIC_COLUMN	= "FILLERORDERNUMBERIMAGINGSERVIC";
	public static final String REQUESTEDPROCEDUREID_COLUMN				= "REQUESTEDPROCEDUREID";
	public static final String REQUESTEDPROCEDUREDESCRIPTION_COLUMN	= "REQUESTEDPROCEDUREDESCRIPTION";
	public static final String SCHEDULEDPROCEDURESTEPID_COLUMN			= "SCHEDULEDPROCEDURESTEPID";
	public static final String SCHEDULEDPROCEDURESTEPDESCRIPT_COLUMN	= "SCHEDULEDPROCEDURESTEPDESCRIPT";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MPScheduledStepAttributeTbl()
	{
        this.tableNameStr = TABLE_NAME;
	}
}
