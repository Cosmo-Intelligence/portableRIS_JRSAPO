package ris.lib.core.database;

import java.sql.Connection;

import ris.portable.util.DataTable;

/// <summary>
///
/// システム情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.25	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.05	: 999999 / H.Satou@MERX	: NML-1-X04
///
/// </summary>
public class RisSystemDefineTbl extends BaseTbl
{
	// アクセステーブル名
	private String tableNameStr = "SYSTEMDEFINE";

	// カラム定義
	public static final String LICENSENO_COLUMN			= "LICENSENO";
	public static final String KENZOUFLAG_COLUMN			= "KENZOUFLAG";
	public static final String HSPLINKFLAG_COLUMN			= "HSPLINKFLAG";
	public static final String CONFIRMTYPE_COLUMN			= "CONFIRMTYPE";
	public static final String SQ_USER_COLUMN				= "SQ_USER";
	public static final String SQ_PASSWORD_COLUMN			= "SQ_PASSWORD";
	public static final String SQ_SERVICENAME_COLUMN		= "SQ_SERVICENAME";
	public static final String SQ_HSPID_COLUMN				= "SQ_HSPID";
	public static final String BUISETFLAG_COLUMN			= "BUISETFLAG";
	public static final String MARKERCHARACTER_COLUMN		= "MARKERCHARACTER";
	public static final String TEMPLATEMARKERCHAR_COLUMN	= "TEMPLATEMARKERCHAR";
	public static final String CHILDLOWLIMIT_COLUMN 		= "CHILDLOWLIMIT";
	public static final String CHILDHIGHLIMIT_COLUMN 		= "CHILDHIGHLIMIT";
	public static final String BABYLOWLIMIT_COLUMN 		= "BABYLOWLIMIT";
	public static final String BABYHIGHLIMIT_COLUMN 		= "BABYHIGHLIMIT";
	public static final String RISORDERSENDFLAG_COLUMN		= "RISORDERSENDFLAG";
	public static final String RISORDERSENDFLAG2_COLUMN	= "RISORDERSENDFLAG2";
	public static final String IMAGEURLFLAG_COLUMN 		= "IMAGEURLFLAG";
	public static final String IMAGEURL_COLUMN 			= "IMAGEURL";
	public static final String IMGUSERLABEL_COLUMN 		= "IMGUSERLABEL";
	public static final String IMGPASSWORDLABEL_COLUMN 	= "IMGPASSWORDLABEL";
	public static final String IMGPIDLABEL_COLUMN 			= "IMGPIDLABEL";
	public static final String IMGEDATELABEL_COLUMN		= "IMGEDATELABEL";
	public static final String IMGMODALITYLABEL_COLUMN		= "IMGMODALITYLABEL";
	public static final String IMGACNOLABEL_COLUMN 		= "IMGACNOLABEL";
	public static final String REPORTURLFLAG_COLUMN 		= "REPORTURLFLAG";
	public static final String REPORTURL_COLUMN			= "REPORTURL";
	public static final String RPTUSERLABEL_COLUMN			= "RPTUSERLABEL";
	public static final String RPTPASSWORDLABEL_COLUMN		= "RPTPASSWORDLABEL";
	public static final String RPTPIDLABEL_COLUMN 			= "RPTPIDLABEL";
	public static final String RPTEDATELABEL_COLUMN 		= "RPTEDATELABEL";
	public static final String RPTMODALITYLABEL_COLUMN 	= "RPTMODALITYLABEL";
	public static final String RPTACNOLABEL_COLUMN 		= "RPTACNOLABEL";
	public static final String SCHEMAURLFLAG_COLUMN 		= "SCHEMAURLFLAG";
	public static final String EXTENDORDERUSEFLAG_COLUMN	= "EXTENDORDERUSEFLAG";
	public static final String SCHEMAURL_COLUMN 			= "SCHEMAURL";
	public static final String SCHUSERLABEL_COLUMN 		= "SCHUSERLABEL";
	public static final String SCHPASSWORDLABEL_COLUMN 	= "SCHPASSWORDLABEL";
	public static final String SCHPIDLABEL_COLUMN 			= "SCHPIDLABEL";
	public static final String SCHEDATELABEL_COLUMN 		= "SCHEDATELABEL";
	public static final String SCHMODALITYLABEL_COLUMN 	= "SCHMODALITYLABEL";
	public static final String SCHORDERNOLABEL_COLUMN 		= "SCHORDERNOLABEL";
	public static final String SCHACNOLABEL_COLUMN 		= "SCHACNOLABEL";
	public static final String USECRKIND_COLUMN 			= "USECRKIND";
	public static final String SYSREPORTFORM01_COLUMN 		= "SYSREPORTFORM01";
	public static final String SYSREPORTFORM02_COLUMN 		= "SYSREPORTFORM02";
	public static final String AUDITFLAG_COLUMN 			= "AUDITFLAG";
	public static final String SYSTEMID_COLUMN 			= "SYSTEMID";
	public static final String KENZOUFIX_COLUMN			= "KENZOUFIX";
	public static final String KENZOUPASSDLG_COLUMN		= "KENZOUPASSDLG";
	public static final String KENZOUCONNECTWAIT_COLUMN	= "KENZOUCONNECTWAIT";
	public static final String KENZOUSHOWWAIT_COLUMN		= "KENZOUSHOWWAIT";
	public static final String MPPSFLG_COLUMN				= "MPPSFLG";
	public static final String MPPS_USER_COLUMN			= "MPPS_USER";
	public static final String MPPS_PASSWORD_COLUMN		= "MPPS_PASSWORD";
	public static final String MPPS_SERVICENAME_COLUMN		= "MPPS_SERVICENAME";
	public static final String MPPS_INTERVAL_COLUMN		= "MPPS_INTERVAL";
	public static final String MPPS_RETRYCOUNT_COLUMN		= "MPPS_RETRYCOUNT";
	public static final String MPPS_FILMSETTYPE_COLUMN		= "MPPS_FILMSETTYPE";
	public static final String MPPS_FIX_COLUMN				= "MPPS_FIX";
	public static final String MPPS_PASSWDDLG_COLUMN		= "MPPS_PASSWDDLG";
	public static final String MWM_USER_COLUMN				= "MWM_USER";
	public static final String MWM_PASSWORD_COLUMN			= "MWM_PASSWORD";
	public static final String MWM_SERVICENAME_COLUMN		= "MWM_SERVICENAME";
	public static final String MWM_UKETUKEENTRY_COLUMN		= "MWM_UKETUKEENTRY";
	public static final String MWM_RECEIPTENTRY_COLUMN		= "MWM_RECEIPTENTRY";
	public static final String MPPS_UNITFLG_COLUMN			= "MPPS_UNITFLG";
	// 2010.07.30 Add DD.Chinh Start
	public static final String TODAY_ZOUEIZAI_ID_COLUMN	= "TODAY_ZOUEIZAI_ID";	//RRis.SYSTEMDEFINE.TODAY_ZOUEIZAI_ID
	public static final String TODAY_ZOUEIZAI_KBN_COLUMN	= "TODAY_ZOUEIZAI_KBN";	//RRis.SYSTEMDEFINE.TODAY_ZOUEIZAI_KBN
	// 2010.07.30 Add DD.Chinh End

	/// <summary>
	/// publicコンストラクタ
	/// </summary>
	/// <remarks></remarks>
	public RisSystemDefineTbl()
	{
		//
	}

	/// <summary>
	/// システム設定情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>システム設定情報</returns>
	public DataTable GetSystemDefine( Connection con ) throws Exception
	{
		// parameters
		DataTable dt = null;

		// start log
		logger.debug("begin");

		if( con != null )
		{
			dt = Select( con, CreateSelectSQL(), null);
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL()
	{
		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		// start log
		logger.debug("begin");

		// create sql
		sqlStrBuild.append("SELECT * from " + tableNameStr);

		// end log
		logger.debug("end");

		return sqlStrBuild.toString();
	}

	/*
	/// <summary>
	/// SELECT
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="sqlStr">実行するSQL文</param>
	/// <returns>SELECT結果のDataTable</returns>
	private DataTable Select( Connection con, String sqlStr )
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if( con != null && sqlStr != null )
		{
			// sql log
			// 2011.08.05 Mod H.Satou@MERX Start NML-1-X04
			// ログレベルの変更(DEBUG→INFO)
			logger.info(sqlStr);
			// コメント
			//logger.debug(sqlStr);

			// 2011.08.05 Mod H.Satou@MERX End

			try
			{
				// create adapter
				adapter = new DataAdapter( sqlStr, con );
				adapter.SelectCommand.Transaction = trans;

				dt = new DataTable();
				adapter.Fill(dt);
			}
			catch( Exception e )
			{
				throw e;
			}
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/
}
