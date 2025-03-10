package ris.lib.core.database;

import java.sql.Connection;
import java.text.SimpleDateFormat;

import ris.lib.core.util.OrderSearchParameter;
import ris.portable.common.Const;
import ris.portable.util.DataTable;

/// <summary>
/// 履歴タブビュークラス
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.00.00	: 2009.02.26	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.05	: 999999 / H.Satou@MERX	: NML-CAT3-036
///
/// </summary>
public class RisHistoryTabSummaryView extends BaseTbl
{
	// アクセステーブル名
	public static final String  VIEW_NAME		= "HISTORYTABSUMMARYVIEW";
	private static final String TABLE_CAPTION	= "履歴タブビュー情報";

	// カラム定義
	// Viewに存在するカラムのみ追加する事
	public static final String RIS_ID_COLUMN						= "RIS_ID";
	public static final String KENSATYPE_ID_COLUMN					= "KENSATYPE_ID";
	public static final String KENSATYPE_NAME_COLUMN				= "KENSATYPE_NAME";
	public static final String KENSATYPE_RYAKUNAME_COLUMN			= "KENSATYPE_RYAKUNAME";
	public static final String KANJA_ID_COLUMN						= "KANJA_ID";
	public static final String KANJAID2_COLUMN						= "KANJAID2";
	public static final String STATUS_COLUMN						= "STATUS";
	public static final String KENSA_DATE_COLUMN					= "KENSA_DATE";
	public static final String KENSA_DATE2_COLUMN					= "KENSA_DATE2";
	public static final String KENSA_STARTTIME_COLUMN				= "KENSA_STARTTIME";
	public static final String IRAI_SECTION_COLUMN					= "IRAI_SECTION";
	public static final String IRAI_RYAKUSECTION_COLUMN			= "IRAI_RYAKUSECTION";
	public static final String IRAI_DOCTOR_NAME_COLUMN				= "IRAI_DOCTOR_NAME";
	public static final String KANJA_NYUGAIKBN_COLUMN				= "KANJA_NYUGAIKBN";
	public static final String BYOUTOU_COLUMN						= "BYOUTOU";
	public static final String RYAKUBYOUTOU_COLUMN					= "RYAKUBYOUTOU";
	public static final String EXAMSTARTDATE_COLUMN				= "EXAMSTARTDATE";
	public static final String EXAMENDDATE_COLUMN					= "EXAMENDDATE";
	public static final String ORDER_KENSA_DATE_COLUMN				= "ORDER_KENSA_DATE";	//2010.10.29 Add K.Shinohara
	public static final String EX_KENSA_DATE_COLUMN				= "EX_KENSA_DATE";		//

	//表示,処理用カラム
	public static final String STATUS_NAME_COLUMN					= "STATUS_NAME";
	public static final String KENSA_DATE_STRING_COLUMN			= "KENSA_DATE_STRING";
	public static final String KENSA_STARTTIME_STRING_COLUMN		= "KENSA_STARTTIME_STRING";
	public static final String KANJA_NYUGAIKBN_STRING_COLUMN		= "KANJA_NYUGAIKBN_STRING";
	public static final String BUI_NAME_COLUMN						= "BUI_NAME";
	public static final String HOUKOU_NAME_COLUMN					= "HOUKOU_NAME";
	public static final String KENSAHOUHOU_NAME_COLUMN				= "KENSAHOUHOU_NAME";
	public static final String EXAMSTARTDATE_STRING_COLUMN			= "EXAMSTARTDATE_STRING";
	public static final String EXAMENDDATE_STRING_COLUMN			= "EXAMENDDATE_STRING";
	public static final String ZOUEIZAI_NAME_COLUMN				= "ZOUEIZAI_NAME";
	public static final String ZOUEIZAI_RYAKUNAME_COLUMN			= "ZOUEIZAI_RYAKUNAME";
	public static final String SHOW_IMAGE_COLUMN					= "SHOW_IMAGE";
	public static final String SHOW_REPORT_COLUMN					= "SHOW_REPORT";
	public static final String SHOW_SCHEMA_COLUMN					= "SHOW_SCHEMA";
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
	public static final String SATUEISTATUS_COLUMN					= "SATUEISTATUS";
	public static final String SATUEISTATUSNAME_COLUMN				= "SATUEISTATUSNAME";
	public static final String SATUEISTATUSSORT_COLUMN				= "SATUEISTATUSSORT";
	// 2011.08.05 Add H.Satou@MERX End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisHistoryTabSummaryView()
	{
		this.tableNameStr = VIEW_NAME;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// 取得するカラム文字列を作成
	/// </summary>
	/// <returns></returns>
	private String GetColumnString()
	{
		String retStr = "";

		// 取得するカラム文字列を作成

		retStr =
			  RIS_ID_COLUMN					+ ", "
			+ KENSATYPE_ID_COLUMN			+ ", "
			+ KENSATYPE_NAME_COLUMN			+ ", "
			+ KENSATYPE_RYAKUNAME_COLUMN	+ ", "
			+ KANJA_ID_COLUMN				+ ", "
			+ KANJAID2_COLUMN				+ ", "
			+ STATUS_COLUMN					+ ", "
			+ KENSA_DATE_COLUMN				+ ", "
			+ KENSA_DATE2_COLUMN			+ ", "
			+ KENSA_STARTTIME_COLUMN		+ ", "
			+ IRAI_SECTION_COLUMN			+ ", "
			+ IRAI_RYAKUSECTION_COLUMN		+ ", "
			+ IRAI_DOCTOR_NAME_COLUMN		+ ", "
			+ KANJA_NYUGAIKBN_COLUMN		+ ", "
			+ BYOUTOU_COLUMN				+ ", "
			+ RYAKUBYOUTOU_COLUMN			+ ", "
			+ EXAMSTARTDATE_COLUMN			+ ", "
			+ EXAMENDDATE_COLUMN			+ ", "
			+ ORDER_KENSA_DATE_COLUMN		+ ", "	//2010.10.29 Add K.Shinohara
			+ EX_KENSA_DATE_COLUMN					//
			;



		return retStr;
	}

	/// <summary>
	/// 不足カラム追加
	/// </summary>
	/// <param name="dt"></param>
	private void AddColumns(DataTable dt)
	{
		// 不足カラム追加
		/* 一ノ瀬保留
		dt.Columns.Add(STATUS_NAME_COLUMN,				typeof(String));
		dt.Columns.Add(KENSA_DATE_STRING_COLUMN,		typeof(String));
		dt.Columns.Add(KENSA_STARTTIME_STRING_COLUMN,	typeof(String));
		dt.Columns.Add(KANJA_NYUGAIKBN_STRING_COLUMN,	typeof(String));
		dt.Columns.Add(BUI_NAME_COLUMN,					typeof(String));
		dt.Columns.Add(HOUKOU_NAME_COLUMN,				typeof(String));
		dt.Columns.Add(KENSAHOUHOU_NAME_COLUMN,			typeof(String));
		dt.Columns.Add(EXAMSTARTDATE_STRING_COLUMN,		typeof(String));
		dt.Columns.Add(EXAMENDDATE_STRING_COLUMN,		typeof(String));
		dt.Columns.Add(ZOUEIZAI_NAME_COLUMN,			typeof(String));
		dt.Columns.Add(ZOUEIZAI_RYAKUNAME_COLUMN,		typeof(String));
		dt.Columns.Add(SHOW_IMAGE_COLUMN,				typeof(String));
		dt.Columns.Add(SHOW_REPORT_COLUMN,				typeof(String));
		dt.Columns.Add(SHOW_SCHEMA_COLUMN,				typeof(String));
		// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
		dt.Columns.Add(SATUEISTATUS_COLUMN,				typeof(String));
		dt.Columns.Add(SATUEISTATUSNAME_COLUMN,			typeof(String));
		dt.Columns.Add(SATUEISTATUSSORT_COLUMN,			typeof(String));
		// 2011.08.05 Add H.Satou@MERX End
		*/

	}

	/// <summary>
	/// オーダ情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchOrderDataHistory(Connection con, OrderSearchParameter param) throws Exception
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if (con != null && param != null)
		{
			String sql = CreateSelectSQLHistory(param);

			//★制限なし
			dt = Select(con, sql, null);

			if (dt != null)
			{
				//不足カラム追加
				AddColumns(dt);
			}
			param.SetExecSql(sql);	//実行SQL文を設定
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/*
	// 2010.11.08 Add K.Shinohara Start
	/// <summary>
	/// オーダ情報件数を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	public int CountOrderDataHistory(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		int count = 0;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sql = CreateCountSQLHistory(param);
			DataTable dt = Select(con, trans, sql);
			if (dt != null && dt.Rows.Count == 1)
			{
				count = Integer.parseInt(dt.Rows[0][0].toString());
			}
		}

		// end log
		logger.debug("end");

		return count;
	}
	// 2010.11.08 Add K.Shinohara End
	*/
	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">依頼の検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQLHistory(OrderSearchParameter param)
	{
		logger.debug("begin");

		StringBuilder strBufOrSQL = new StringBuilder("");

		this.keys.clear();
		this.inList.clear();

		//取得するカラム文字列を作成
		String colmunName = GetColumnString();

		//検索条件を追加する
		AddWhere(param);

		logger.debug("end");

		return this.GetSelectColmunSQL(colmunName, VIEW_NAME);
	}

	// 2010.11.08 Add K.Shinohara Start
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">依頼の検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateCountSQLHistory(OrderSearchParameter param)
	{
		logger.debug("begin");

		StringBuilder strBufOrSQL = new StringBuilder("");

		this.keys.clear();
		this.inList.clear();

		//検索条件を追加する
		AddWhere(param);

		this.AddColValue(RIS_ID_COLUMN, param.GetRisID(), true, SignType.NotEqual);

		logger.debug("end");

		return this.GetSelectCountSQL(RIS_ID_COLUMN);
	}
	// 2010.11.08 Add K.Shinohara End

	/// <summary>
	/// 検索条件を追加する
	/// </summary>
	/// <param name="param"></param>
	private void AddWhere(OrderSearchParameter param)
	{
		//検査日
		if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodStartDate()) )
		{
			String startDate = new SimpleDateFormat("yyyyMMdd").format(param.GetExecutePeriodStartDate());
			this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(startDate), true, SignType.Under);
		}
		if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodEndDate()) )
		{
			String endDate = new SimpleDateFormat("yyyyMMdd").format(param.GetExecutePeriodEndDate());
			this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(endDate), true, SignType.Over);
		}

		//患者ID
		this.AddColValue(KANJA_ID_COLUMN, param.GetKanjaID(), true, SignType.Equal);

		//ステータス
		try
		{
			String[] statuses = param.GetRrisStatus().split(",");
			if (statuses.length == 1)
			{
				if (statuses[0].length() > 0)
				{
					this.AddColValue(STATUS_COLUMN, Integer.parseInt(statuses[0]), true, SignType.Equal);
				}
			}
			else
			{
				for (int i = 0; i < statuses.length; i++)
				{
					if (statuses[i].length() > 0)
					{
						this.AddColValue(STATUS_COLUMN, Integer.parseInt(statuses[i]), true, SignType.In);
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		//検査種別
		String[] kensaTypes = param.GetRrisKensaTypeID().split(",");
		if (kensaTypes.length == 1)
		{
			this.AddColValue(KENSATYPE_ID_COLUMN, kensaTypes[0], true, SignType.Equal);
		}
		else
		{
			for (int i = 0; i < kensaTypes.length; i++)
			{
				this.AddColValue(KENSATYPE_ID_COLUMN, kensaTypes[i], true, SignType.In);
			}
		}
	}
}
