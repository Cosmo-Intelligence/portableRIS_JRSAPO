package ris.lib.core.database;

/// <summary>
///
/// MPMppsDefineTblテーブル管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MPMppsDefineTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "MPPSDEFINE";
	private static final String TABLE_CAPTION	= "MPPS定義情報";

	// カラム定義
	public static final String AE_TITLE_COLUMN					= "AE_TITLE";
	public static final String MPPSTYPE_COLUMN					= "MPPSTYPE";
	public static final String MODE_KVP_COLUMN					= "MODE_KVP";
	public static final String MODE_XRAYTUBECURRENT_MA_COLUMN	= "MODE_XRAYTUBECURRENT_MA";
	public static final String MODE_EXPOSURETIME_SEC_COLUMN	= "MODE_EXPOSURETIME_SEC";
	public static final String MODE_KV_COLUMN					= "MODE_KV";
	public static final String KEYWORD_KV_COLUMN				= "KEYWORD_KV";
	public static final String MODE_MAS_COLUMN					= "MODE_MAS";
	public static final String KEYWORD_MAS_COLUMN				= "KEYWORD_MAS";
	public static final String MODE_EXPOSURENO_COLUMN			= "MODE_EXPOSURENO";
	public static final String MODE_CTDI_COLUMN				= "MODE_CTDI";
	public static final String KEYWORD_CTDI_COLUMN				= "KEYWORD_CTDI";
	public static final String MODE_DLP_COLUMN					= "MODE_DLP";
	public static final String KEYWORD_DLP_COLUMN				= "KEYWORD_DLP";
	public static final String MODE_FLUOROSCOPY_COLUMN			= "MODE_FLUOROSCOPY";
	public static final String MODE_IMAGEAREA_COLUMN			= "MODE_IMAGEAREA";
	public static final String MODE_D_DISTANCE_NM_COLUMN		= "MODE_D_DISTANCE_NM";
	public static final String MODE_E_DISTANCE_NM_COLUMN		= "MODE_E_DISTANCE_NM";
	public static final String MODE_ENTRANCEDOSE_DGY_COLUMN	= "MODE_ENTRANCEDOSE_DGY";
	public static final String MODE_ENTRANCEDOSE_MGY_COLUMN	= "MODE_ENTRANCEDOSE_MGY";
	public static final String MODE_EXPOSEDAREA_COLUMN 		= "MODE_EXPOSEDAREA";
	public static final String MODE_RADIATIONMODE_COLUMN 		= "MODE_RADIATIONMODE";
	public static final String MODE_FILTERTYPE_COLUMN 			= "MODE_FILTERTYPE";
	public static final String MODE_FILTERMATERIAL_COLUMN		= "MODE_FILTERMATERIAL";
	public static final String KVP_ID_COLUMN 					= "KVP_ID";
	public static final String XRAYTUBECURRENT_MA_ID_COLUMN	= "XRAYTUBECURRENT_MA_ID";
	public static final String EXPOSURETIME_SEC_ID_COLUMN		= "EXPOSURETIME_SEC_ID";
	public static final String EXPOSURETIME_MIN_ID_COLUMN		= "EXPOSURETIME_MIN_ID";
	public static final String MAS_ID_COLUMN 					= "MAS_ID";
	public static final String KV_ID_COLUMN 					= "KV_ID";
	public static final String EXPOSURENO_ID_COLUMN 			= "EXPOSURENO_ID";
	public static final String CTDI_ID_COLUMN 					= "CTDI_ID";
	public static final String DLP_ID_COLUMN 					= "DLP_ID";
	public static final String FLUOROSCOPY_ID_COLUMN 			= "FLUOROSCOPY_ID";
	public static final String IMAGEAREA_ID_COLUMN 			= "IMAGEAREA_ID";
	public static final String D_DISTANCE_MM_ID_COLUMN 		= "D_DISTANCE_MM_ID";
	public static final String D_DISTANCE_CM_ID_COLUMN 		= "D_DISTANCE_CM_ID";
	public static final String E_DISTANCE_MM_ID_COLUMN 		= "E_DISTANCE_MM_ID";
	public static final String ENTRANCEDOSE_DGY_ID_COLUMN 		= "ENTRANCEDOSE_DGY_ID";
	public static final String ENTRANCEDOSE_MGY_ID_COLUMN 		= "ENTRANCEDOSE_MGY_ID";
	public static final String EXPOSEDAREA_ID_COLUMN 			= "EXPOSEDAREA_ID";
	public static final String RADIATIONMODE_ID_COLUMN 		= "RADIATIONMODE_ID";
	public static final String FILTERTYPE_ID_COLUMN 			= "FILTERTYPE_ID";
	public static final String FILTERMATERIAL_ID_COLUMN 		= "FILTERMATERIAL_ID";
	public static final String SHOWORDER_COLUMN 				= "SHOWORDER";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MPMppsDefineTbl()
	{
        this.tableNameStr = TABLE_NAME;
	}

	/*
	/// <summary>
	/// MPPS定義情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>MPPS定義情報</returns>
	public DataTable GetMppsDefineData(Connection con, Transaction trans)
	{
		// parameters
		DataTable retDt = null;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null)
		{
			String sqlStr = CreateSelectSQL();
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
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL()
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddOrderKeyAsc(SHOWORDER_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	*/
}
