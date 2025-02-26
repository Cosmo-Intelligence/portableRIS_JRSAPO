package ris.lib.core.database;

import java.sql.Connection;

import ris.portable.util.DataTable;

/// <summary>
///
/// MPMppsMasterTblテーブル管理クラス(WorkDB用)
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MPMppsMasterTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "MPPSMASTER";
	private static final String TABLE_CAPTION	= "MPPSマスタ情報";

	// カラム定義
	public static final String SOPINSTANCEUID_COLUMN					= "SOPINSTANCEUID";
	public static final String PERFORMEDPROCEDURESTEPSTATUS_COLUMN		= "PERFORMEDPROCEDURESTEPSTATUS";
	public static final String PERFORMEDSTATIONAETITLE_COLUMN			= "PERFORMEDSTATIONAETITLE";
	public static final String PERFORMEDPROCEDURESTEPSTARTTIM_COLUMN	= "PERFORMEDPROCEDURESTEPSTARTTIM";
	public static final String PERFORMEDPROCEDURESTEPENDTIME_COLUMN	= "PERFORMEDPROCEDURESTEPENDTIME";
	public static final String TOTALTIMEOFFLUOROSCOPY_COLUMN			= "TOTALTIMEOFFLUOROSCOPY";
	public static final String INSTANCECREATIONDATETIME_COLUMN			= "INSTANCECREATIONDATETIME";
	public static final String TOTALNUMBEROFEXPOSURES_COLUMN			= "TOTALNUMBEROFEXPOSURES";
	public static final String IMAGEAREADOSEPRODUCT_COLUMN				= "IMAGEAREADOSEPRODUCT";
	public static final String DISTANCESOURCETODETECTOR_COLUMN			= "DISTANCESOURCETODETECTOR";
	public static final String DISTANCESOURCETOENTRANCE_COLUMN			= "DISTANCESOURCETOENTRANCE";
	public static final String ENTRANCEDOSE_COLUMN						= "ENTRANCEDOSE";
	public static final String ENTRANCEDOSEINMGY_COLUMN				= "ENTRANCEDOSEINMGY";
	public static final String EXPOSEDAREA_COLUMN						= "EXPOSEDAREA";
	public static final String KVP_COLUMN								= "KVP";
	public static final String COMMENTSONRADIATIONDOSE_COLUMN			= "COMMENTSONRADIATIONDOSE";
	public static final String XRAYTUBECURRENTINUA_COLUMN				= "XRAYTUBECURRENTINUA";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MPMppsMasterTbl()
	{
        this.tableNameStr = TABLE_NAME;
	}

	/// <summary>
	/// 検査系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="con"></param>
	/// <param name="trans"></param>
	/// <param name="acNo">AccessionNo</param>
	/// <returns></returns>
	public DataTable GetKensaMppsMasterData(Connection con, String acNo) throws Exception
	{
		// parameters
		DataTable retDt = null;

		// begin log
		logger.debug("begin");

		if (con != null && acNo.length() > 0)
		{
			String sqlStr = CreateSelectSQLByKensa(acNo);
			retDt = Select(con, sqlStr, null);
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

		sqlStrBuild.append(" SELECT DISTINCT ");
		sqlStrBuild.append("   MPP.SOPINSTANCEUID, ");
		sqlStrBuild.append("   MPP.PERFORMEDSTATIONAETITLE, ");
		sqlStrBuild.append("   MPP.TOTALNUMBEROFEXPOSURES, ");
		sqlStrBuild.append("   MPP.TOTALTIMEOFFLUOROSCOPY, ");
		sqlStrBuild.append("   MPP.IMAGEAREADOSEPRODUCT, ");
		sqlStrBuild.append("   MPP.DISTANCESOURCETODETECTOR, ");
		sqlStrBuild.append("   MPP.DISTANCESOURCETOENTRANCE, ");
		sqlStrBuild.append("   MPP.ENTRANCEDOSE, ");
		sqlStrBuild.append("   MPP.ENTRANCEDOSEINMGY, ");
		sqlStrBuild.append("   MPP.EXPOSEDAREA, ");
		sqlStrBuild.append("   MPP.KVP, ");
		sqlStrBuild.append("   MPP.XRAYTUBECURRENTINUA, ");
		sqlStrBuild.append("   MPP.COMMENTSONRADIATIONDOSE ");
		sqlStrBuild.append(" FROM MPPSMASTER MPP, ");
		sqlStrBuild.append("      SCHEDULEDSTEPATTRIBUTE SCH ");
		sqlStrBuild.append(" WHERE SCH.ACCESSIONNUMBER = '" + acNo + "' ");
		sqlStrBuild.append("   AND SUBSTR(UPPER(MPP.PERFORMEDPROCEDURESTEPSTATUS), 1, 4) = 'COMP' ");
		sqlStrBuild.append("   AND MPP.SOPINSTANCEUID = SCH.MPPSSOPINSTANCEUID ");

		logger.debug("end");

		return sqlStrBuild.toString();
	}

	/// <summary>
	/// 撮影系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="con"></param>
	/// <param name="trans"></param>
	/// <param name="acNo">AccessionNo</param>
	/// <returns></returns>
	public DataTable GetSatueiMppsMasterData(Connection con, String acNo) throws Exception
	{
		// parameters
		DataTable retDt = null;

		// begin log
		logger.debug("begin");

		if (con != null && acNo.length() > 0)
		{
			String sqlStr = CreateSelectSQLBySatuei(acNo);
			retDt = Select(con, sqlStr, null);
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
	private String CreateSelectSQLBySatuei(String acNo)
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		//SQLの作成
		sqlStrBuild.append(" select");
		sqlStrBuild.append("   *");
		sqlStrBuild.append(" from");
		sqlStrBuild.append("   (");
		sqlStrBuild.append("     select");
		sqlStrBuild.append("       MPPS.PERFORMEDPROCEDURESTEPSTATUS,");
		sqlStrBuild.append("       MPPS.SOPINSTANCEUID");
		sqlStrBuild.append("     from");
		sqlStrBuild.append("       MPPSMASTER MPPS");
		sqlStrBuild.append("     where");
		sqlStrBuild.append("       MPPS.SOPINSTANCEUID in(");
		sqlStrBuild.append("         select");
		sqlStrBuild.append("           SCH.MPPSSOPINSTANCEUID");
		sqlStrBuild.append("         from");
		sqlStrBuild.append("           SCHEDULEDSTEPATTRIBUTE SCH");
		sqlStrBuild.append("         where");
		sqlStrBuild.append("           SCH.ACCESSIONNUMBER = '" + acNo + "'");
		sqlStrBuild.append("       )");
		sqlStrBuild.append("     order by");
		sqlStrBuild.append("       MPPS.PERFORMEDPROCEDURESTEPSTARTDAT,");
		sqlStrBuild.append("       MPPS.PERFORMEDPROCEDURESTEPSTARTTIM");
		sqlStrBuild.append("   )");
		sqlStrBuild.append(" where");
		sqlStrBuild.append("   rownum = 1");
		sqlStrBuild.append(" and  SUBSTR(UPPER(PERFORMEDPROCEDURESTEPSTATUS), 1, 4) = 'COMP'");

		logger.debug("end");

		return sqlStrBuild.toString();
	}

	/// <summary>
	/// 撮影系-MPPS実績情報を取得する
	/// </summary>
	/// <param name="con"></param>
	/// <param name="trans"></param>
	/// <param name="sopInstanceUID">MPPSSOPInstanceUID</param>
	/// <returns></returns>
	public DataTable GetSatueiMppsResultData(Connection con, String sopInstanceUID) throws Exception
	{
		// parameters
		DataTable retDt = null;

		// begin log
		logger.debug("begin");

		if (con != null && sopInstanceUID.length() > 0)
		{
			String sqlStr = CreateSelectSQLBySatueiResult(sopInstanceUID);
			retDt = Select(con, sqlStr, null);

			//不足カラムの追加
			// 一ノ瀬保留
			//retDt.Columns.Add(MasterUtil.RIS_HIKIATE_FLG);
		}

		// end log
		logger.debug("end");

		return retDt;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="sopInstanceUID">MPPSSOPInstanceUID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQLBySatueiResult(String sopInstanceUID)
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		//SQLの作成
		sqlStrBuild.append(" SELECT ");
		sqlStrBuild.append("  PPC.MPPSSOPINSTANCEUID, PPC.VMNO, PPC.CODEVALUE, UPPER(PPC.CODEMEANING) AS CODEMEANING, ");
		sqlStrBuild.append("  EDS.RDKVP, EDS.RDXRAYTUBECURRENTINUA, EDS.RDEXPOSURETIME, ");
		sqlStrBuild.append("  FCP.BAMCNUMBEROFFILMS, FCP.BAMCFILMSIZEID");
		sqlStrBuild.append(" FROM PERFORMEDPROTOCOLCODE PPC, EXPOSUREDOSE EDS, FILMCONSUMPTION FCP  ");
		sqlStrBuild.append(" WHERE PPC.MPPSSOPINSTANCEUID = '" + sopInstanceUID + "'   ");
		sqlStrBuild.append(" AND PPC.MPPSSOPINSTANCEUID = EDS.MPPSSOPINSTANCEUID(+)    ");
		sqlStrBuild.append(" AND PPC.VMNO               = EDS.VMNO(+)    ");
		sqlStrBuild.append(" AND PPC.MPPSSOPINSTANCEUID = FCP.MPPSSOPINSTANCEUID(+)    ");
		sqlStrBuild.append(" AND PPC.VMNO               = FCP.VMNO(+)  ");
		sqlStrBuild.append(" ORDER BY PPC.VMNO");

		logger.debug("end");

		return sqlStrBuild.toString();
	}
}
