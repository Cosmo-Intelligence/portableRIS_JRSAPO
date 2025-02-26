package ris.lib.core.database;

import java.sql.Connection;

import ris.portable.util.DataTable;

/// <summary>
///
/// システムパラメータ情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.03.25	: 112478 / A.Kobayashi		: original
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo		: KANRO-R-7 KANRO-R-8 KANRO-R-19 KANRO-R-22
/// V2.01.00		: 2011.07.14	: 999999 / NSK_R.Akimoto	: NML-CAT2-017
/// V2.01.00		: 2011.08.02    extends 999999 / NSK_T.Koudate	: NML-CAT2-004
/// V2.01.00		: 2011.08.22	: 999999 / T.Nishikubo		: NML-2-X01
/// V2.01.01.13000	: 2011.11.14    extends 999999 / NSK_H.Hashimoto	: OMH-1-04
/// V2.01.00		: 2011.11.17    extends 999999 / NSK_T.Wada		: OMH3-9
/// V2.01.01.13000	: 2011.11.18    extends 999999 / NSK_H.Hashimoto	: OMH-1-03
/// V2.01.01.13000	: 2011.11.21    extends 999999 / NSK_M.Ochiai		: OMH-1-10
///
/// </summary>
public class RisSystemParamTbl extends BaseTbl
{
	// アクセステーブル名
	private static final String TableNameStr  = "SYSTEMPARAM";
	private static final String TableName2Str = "SYSTEMPARAM2";

	public static final String VALUE1_COLUMN		= "VALUE1";
	public static final String VALUE2_COLUMN		= "VALUE2";
	public static final String VALUE3_COLUMN		= "VALUE3";
	public static final String VALUE4_COLUMN		= "VALUE4";
	public static final String VALUE5_COLUMN		= "VALUE5";
	public static final String VALUE6_COLUMN		= "VALUE6";
	public static final String VALUE7_COLUMN		= "VALUE7";
	public static final String VALUE8_COLUMN		= "VALUE8";
	public static final String VALUE9_COLUMN		= "VALUE9";
	public static final String VALUE10_COLUMN		= "VALUE10";

	public static final String MAINID_COLUMN		= "MAINID";
	public static final String SUBID_COLUMN		= "SUBID";

	public static final String SYSTEM_COLUMN		= "SYSTEM";
	public static final String RECEIPTNO_COLUMN	= "RECEIPTNO";

	//SUBID(SystemParam)
	public static final String KANJAID				= "KANJAID";
	public static final String SIJI_ISI_COMMENT	= "SIJI_ISI_COMMENT";
	public static final String RENRAKU_MEMO		= "RENRAKU_MEMO";
	public static final String BIKOU				= "BIKOU";
	public static final String HANDICAPPED			= "HANDICAPPED";
	public static final String NOTES				= "NOTES";
	public static final String PLACE				= "PLACE";
	public static final String RAD_ID				= "RAD_ID";
	public static final String RESULTCOUNT			= "RESULTCOUNT";
	public static final String COMBOLISTS			= "COMBOLISTS";
	public static final String FLAGS				= "FLAGS";
	public static final String RECEIPTNO			= "RECEIPTNO";
	public static final String MAXCOUNT			= "MAXCOUNT";
	public static final String BARCODE				= "BARCODE";
	public static final String STATUSBGCOLOR		= "STATUSBGCOLOR";
	public static final String SELECTROWBGCOLOR	= "SELECTROWBGCOLOR";
	public static final String SELECTROWTEXTCOLOR	= "SELECTROWTEXTCOLOR";
	public static final String SUURYOU				= "SUURYOU";
	public static final String SUURYOU_IJI			= "SUURYOU_IJI";
	public static final String SETSTARTTIME		= "SETSTARTTIME";
	public static final String PASSWDDLG			= "PASSWDDLG";
	// 2010.07.30 Add T.Ootsuka Start
	// 新規テンプレート区分追加
	public static final String KANJAKYOTUCOMMENT	= "KANJAKYOTUCOMMENT";
	public static final String KENSATYPECOMMENT	= "KENSATYPECOMMENT";
	// 2010.07.30 Add T.Ootsuka End

	// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
	public static final String RECEIPTRULE			= "RECEIPTRULE";
	// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

	// 2011.07.14 Add NSK_R.Akimoto Start NML-CAT2-017
	public static final String RECEIPTKANJA		= "RECEIPTKANJA";
	// 2011.07.14 Add NSK_R.Akimoto end

	//SUBID(SystemParam2)
	public static final String EMPHASISCOLOR		= "EMPHASISCOLOR";
	public static final String SELECTCOLOR			= "SELECTCOLOR";
	public static final String DETAILORDER			= "DETAILORDER";
	public static final String PATINFCHANGE		= "PATINFCHANGE";
	public static final String UKETUKEMULTIBUI		= "UKETUKEMULTIBUI";
	public static final String UNKNOWNDATE         = "UNKNOWNDATE";
	public static final String MPPSDEFINE			= "MPPSDEFINE";
	public static final String KANJACHECK2			= "KANJACHECK2";
	public static final String KENZOUMWM			= "KENZOUMWM";
	public static final String REQUESTFLG			= "REQUESTFLG";
	public static final String BUI_ADD				= "BUI_ADD";
	public static final String LISTDESIGN			= "LISTDESIGN";
	public static final String DEF_ROOM_KIKI		= "DEF_ROOM_KIKI";
	public static final String RECEIPTDATE			= "RECEIPTDATE";
	public static final String ORDERUKETUKE		= "ORDERUKETUKE";
	public static final String THERARIS			= "THERARIS";
	public static final String GYOMU_HEIGHT		= "GYOMU_HEIGHT";
	public static final String GYOMU_HEIGHT2		= "GYOMU_HEIGHT2";
	public static final String DIARY				= "DIARY";
	public static final String EXAMITEM			= "EXAMITEM";
	public static final String MWM_MULTICOUNT		= "MWM_MULTICOUNT"; // 2010.07.30 Add Y.Shibata Start
	// 2010.11.01 Add K.Shinohara Start
	public static final String FORCEACTIVE			= "FORCEACTIVE";
	// 2010.11.01 Add K.Shinohara End
	// 2011.05.06 Add K.Shinohara Start A0027
	public static final String LUMPMWMKIKIMODE		= "LUMPMWMKIKIMODE";
	// 2011.05.06 Add K.Shinohara End
	// 2011.08.02 Add NSK_T.Koudate Start NML-CAT2-004
	public static final String ATTESTATION_MODE	= "ATTESTATION_MODE";
	// 2011.08.02 Add NSK_T.Koudate End
	// 2011.11.14 Add NSK_H.Hashimoto Start OMH-1-04
	public static final String OUTPUT_CSV_REFERENCE = "OUTPUT_CSV_REFERENCE";
	// 2011.11.14 Add NSK_H.Hashimoto End
	// 2011.11.17 Add NSK_T.Wada Start OMH3-9
	public static final String CLOSE_APPOINT_TIME = "CLOSE_APPOINT_TIME";
	// 2011.11.17 Add NSK_T.Wada End
	// 2011.11.18 Add NSK_H.Hashimoto Start OMH-1-03
	public static final String EMG_COLOR = "EMG_COLOR";
	// 2011.11.18 Add NSK_H.Hashimoto End
	// 2011.11.21 Add NSK_M.Ochiai Start OMH-1-10
	public static final String ITEMDEFINE_TIME_TYPE = "ITEMDEFINE_TIME_TYPE";
	// 2011.11.21 Add NSK_M.Ochiai End

	// 2010.07.30 Add T.Ootsuka
	// 患者情報表示設定情報
	public static final String KANJA_AGEWARNING				= "KANJA_AGEWARNING";
	public static final String KANJA_SEXCOLOR					= "KANJA_SEXCOLOR";
	public static final String KANJA_AGEFORMAT					= "KANJA_AGEFORMAT";
	public static final String KANJA_HIGHLIGHTMODE				= "KANJA_HIGHLIGHTMODE";
	public static final String KANJA_HIGHLIGHTBEFORCOLOR		= "KANJA_HIGHLIGHTBEFORCOLOR";
	public static final String KANJA_HIGHLIGHTAFTERCOLOR		= "KANJA_HIGHLIGHTAFTERCOLOR";
	public static final String KANJA_HIGHLIGHTWARNING			= "KANJA_HIGHLIGHTWARNING";
	public static final String KANJA_HIGHLIGHT_SINGULARITYMODE	= "KANJA_HIGHLIGHT_SINGULARITYMODE";
	public static final String KANJA_ORDERCOMMENTMODE			= "KANJA_ORDERCOMMENTMODE";
	// 2010.07.30 Add T.Ootsuka End

	// 2011.03.14 Add CIJ_R.Aoyama Merge Start MY-1-10
	public static final String SIDE_EFFECT_MODE				= "SIDE_EFFECT_MODE";
	// 2011.03.14 Add CIJ_R.Aoyama Merge  End  MY-1-10

	// 2010.08.27 Add K.Shinohara Start
	// 検査開始日時・終了日時の初回保存
	public static final String FIRSTONLYSAVE		= "FIRSTONLYSAVE";
	// 2010.08.27 Add K.Shinohara End

	public static final String CHECK_PATIENT_ID	= "CHECK_PATIENT_ID";	// 2010.11.16 Add T.Nishikubo KANRO-R-7
	public static final String LIST_SHOW_DAY		= "LIST_SHOW_DAY";		// 2010.11.16 Add T.Nishikubo KANRO-R-8
	public static final String LIST_ITEM_DRAG					= "LIST_ITEM_DRAG";						// 2010.12.24 Add Yk.Suzuki@CIJ Start 一覧項目のドラッグ＆ドロップ
	public static final String PRECHECK_STATUS					= "PRECHECK_STATUS";	// 2011.01.24 Add DD.Chinh KANRO-R-19
	public static final String RECEIPT_EXTEND_PATIENTINFO		= "RECEIPT_EXTEND_PATIENTINFO";		// 2011.01.24 Add T.Nishikubo KANRO-R-22
	public static final String SMILE_ORDER_DELETE				= "SMILE_ORDER_DELETE";		// 2011.03.18 Add Yk.Suzuki@CIJ A0005

	// 2011.08.22 Add T.Nishikubo@MERX Start NML-2-X01
	public static final String HOLIDAY_MODE		= "HOLIDAY_MODE";
	// 2011.08.22 Add T.Nishikubo@MERX End

	// 2011.10.24 Add K.Shinohara Start YRH-1-03  OMH-1-08【山田赤十字】
	public static final String ZOUEIZAI_CHECK		= "ZOUEIZAI_CHECK";
	// 2011.10.24 Add K.Shinohara End

	//未対応パラメータ
	public static final String SATUEITUIKAMODE		= "SATUEITUIKAMODE";
	public static final String SPECIALMODE			= "SPECIALMODE";
	public static final String AUTOKENSASTART		= "AUTOKENSASTART";
	public static final String FIX_TIMING			= "FIX_TIMING";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <remarks></remarks>
	public RisSystemParamTbl()
	{
		this.tableNameStr = TableNameStr;
	}

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <param name="flg"></param>
	public RisSystemParamTbl(boolean flg)
	{
		if (flg)
		{
			this.tableNameStr = TableName2Str;
		}
	}

	/// <summary>
	/// システム設定情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>システム設定情報</returns>
	public DataTable GetSystemParam(Connection con) throws Exception
	{
		// parameters
		DataTable dt = null;

		// start log
		logger.debug("begin");

		if (con != null)
		{
			String sqlStr = CreateSelectSQL();
			dt = Select(con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return dt;
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

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// システム設定情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>システム設定情報</returns>
	public DataTable GetSystemParam( Connection con, String mainID, String subID ) throws Exception
	{
		// parameters
		DataTable dt = null;

		// start log
		logger.debug("begin");

		if( con != null )
		{
			String sqlStr = CreateSelectSQL(mainID, subID);
			dt = Select( con, sqlStr, null );
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="mainID">メインキー</param>
	/// <param name="subID">サブキー</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String mainID, String subID)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(MAINID_COLUMN, mainID, true);
		this.AddColValue(SUBID_COLUMN,	subID, true);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// SystemParam2の値を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="mainID">メインキー</param>
	/// <param name="subID">サブキー</param>
	/// <param name="column">列名</param>
	/// <param name="valueStr">値</param>
	/// <returns></returns>
	public boolean UpdateValue(Connection con, String mainID, String subID, String column, String valueStr) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null &&
			mainID != null && mainID.length() > 0 && subID != null && subID.length() > 0 &&
			column != null && column.length() > 0)
		{
			//更新を試みる
			retFlg = Update(con, CreateUpdateSQL(mainID, subID, column, valueStr), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="mainID">メインキー</param>
	/// <param name="subID">サブキー</param>
	/// <param name="column">列名</param>
	/// <param name="valueStr">値</param>
	/// <returns>判定</returns>
	private String CreateUpdateSQL(String mainID, String subID, String column, String valueStr)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(MAINID_COLUMN, mainID, true, SignType.Equal);
		this.AddColValue(SUBID_COLUMN, subID, true, SignType.Equal);
		//
		this.AddColValue(column, valueStr);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
}
