package ris.lib.core.database;

/// <summary>
///
/// MPExposureDoseTblテーブル管理クラス(WorkDB用)
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MPExposureDoseTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "EXPOSUREDOSE";
	private static final String TABLE_CAPTION	= "MPPS照射情報";

	// カラム定義
	public static final String VMNO_COLUMN						= "VMNO";
	public static final String MPPSSOPINSTANCEUID_COLUMN		= "MPPSSOPINSTANCEUID";
	public static final String RDKVP_COLUMN						= "RDKVP";
	public static final String RDXRAYTUBECURRENTINUA_COLUMN		= "RDXRAYTUBECURRENTINUA";
	public static final String RDEXPOSURETIME_COLUMN			= "RDEXPOSURETIME";
	public static final String RDRADIATIONMODE_COLUMN			= "RDRADIATIONMODE";
	public static final String RDFILTERTYPE_COLUMN				= "RDFILTERTYPE";
	public static final String RDFILTERMATERIAL_COLUMN			= "RDFILTERMATERIAL";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MPExposureDoseTbl()
	{
        this.tableNameStr = TABLE_NAME;
	}

	/*
	/// <summary>
	/// 検査系-MPPS照射情報を取得する
	/// </summary>
	/// <param name="con"></param>
	/// <param name="trans"></param>
	/// <param name="acNo"></param>
	/// <returns></returns>
	public DataTable GetKensaExposureDoseData(Connection con, Transaction trans, String acNo)
	{
		// parameters
		DataTable retDt = null;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && acNo.length() > 0)
		{
			String sqlStr = CreateSelectSQLByKensa(acNo);
			retDt = Select(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retDt;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="acNo">AccessionNo</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQLByKensa(String acNo)
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		if (!Configuration.GetInstance().GetKensaMppsNormal())
		{
			//改良版(DISTINCT有 VMNO条件無)
			sqlStrBuild.append(" SELECT DISTINCT ");
			sqlStrBuild.append("   MPP.SOPINSTANCEUID, ");
			sqlStrBuild.append("   EXP.VMNO, ");
			sqlStrBuild.append("   MPP.PERFORMEDSTATIONAETITLE, ");
			sqlStrBuild.append("   EXP.RDKVP, ");
			sqlStrBuild.append("   EXP.RDXRAYTUBECURRENTINUA, ");
			sqlStrBuild.append("   EXP.RDEXPOSURETIME, ");
			sqlStrBuild.append("   EXP.RDRADIATIONMODE, ");
			sqlStrBuild.append("   EXP.RDFILTERTYPE, ");
			sqlStrBuild.append("   EXP.RDFILTERMATERIAL ");
			sqlStrBuild.append(" FROM  MPPSMASTER MPP, ");
			sqlStrBuild.append("       EXPOSUREDOSE EXP, ");
			sqlStrBuild.append("       SCHEDULEDSTEPATTRIBUTE SCH ");
			sqlStrBuild.append(" WHERE SCH.ACCESSIONNUMBER = '" + acNo + "' " );
			sqlStrBuild.append("   AND SUBSTR(UPPER(MPP.PERFORMEDPROCEDURESTEPSTATUS), 1, 4) = 'COMP' ");
			sqlStrBuild.append("   AND MPP.SOPINSTANCEUID = EXP.MPPSSOPINSTANCEUID ");
			sqlStrBuild.append("   AND MPP.SOPINSTANCEUID = SCH.MPPSSOPINSTANCEUID ");
			sqlStrBuild.append(" ORDER BY EXP.VMNO ");
		}
		else
		{
			//標準(DISTINCT無 VMNO条件有)
			sqlStrBuild.append(" SELECT ");
			sqlStrBuild.append("   MPP.SOPINSTANCEUID, ");
			sqlStrBuild.append("   EXP.VMNO, ");
			sqlStrBuild.append("   MPP.PERFORMEDSTATIONAETITLE, ");
			sqlStrBuild.append("   EXP.RDKVP, ");
			sqlStrBuild.append("   EXP.RDXRAYTUBECURRENTINUA, ");
			sqlStrBuild.append("   EXP.RDExposureTime, ");
			sqlStrBuild.append("   EXP.RDRADIATIONMODE, ");
			sqlStrBuild.append("   EXP.RDFILTERTYPE, ");
			sqlStrBuild.append("   EXP.RDFILTERMATERIAL ");
			sqlStrBuild.append(" FROM  MPPSMASTER MPP, ");
			sqlStrBuild.append("       EXPOSUREDOSE EXP, ");
			sqlStrBuild.append("       SCHEDULEDSTEPATTRIBUTE SCH ");
			sqlStrBuild.append(" WHERE SCH.ACCESSIONNUMBER = '" + acNo + "' " );
			sqlStrBuild.append("   AND SUBSTR(UPPER(MPP.PERFORMEDPROCEDURESTEPSTATUS), 1, 4) = 'COMP' ");
			sqlStrBuild.append("   AND MPP.SOPINSTANCEUID = EXP.MPPSSOPINSTANCEUID ");
			sqlStrBuild.append("   AND MPP.SOPINSTANCEUID = SCH.MPPSSOPINSTANCEUID ");
			sqlStrBuild.append("   AND EXP.VMNO           = SCH.VMNO ");
			sqlStrBuild.append(" ORDER BY EXP.VMNO ");
		}
		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/
}
