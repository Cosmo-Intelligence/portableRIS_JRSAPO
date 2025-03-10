package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.util.CommonString;
import ris.portable.util.DataTable;


/// <summary>
/// 部位サマリテーブルの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.04	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.05	: 999999 / H.Satou@MERX	: NML-CAT3-036
///
/// </summary>
public class RisBuiSummaryView extends BaseTbl
{
	// アクセステーブル名
	public  final String VIEW_NAME       = "BUISUMMARYVIEW";
	private  final String TABLE_CAPTION  = "部位サマリ";

	// カラム定義
	public  final String RIS_ID_COLUMN					= "RIS_ID";
	public  final String KENSA_DATE_COLUMN				= "KENSA_DATE";
	public  final String KANJA_ID_COLUMN					= "KANJA_ID";
	public  final String NO_COLUMN						= "NO";
	public  final String BUI_NO_COLUMN					= "BUI_NO";
	public  final String BUI_ID_COLUMN					= "BUI_ID";
	public  final String BUI_NAME_COLUMN					= "BUI_NAME";
	public  final String BUI_RYAKUNAME_COLUMN			= "BUI_RYAKUNAME";
	public  final String BUICOMMENT_COLUMN				= "BUICOMMENT";
	public  final String BUISHOWORDER_COLUMN				= "BUISHOWORDER";
	public  final String SAYUU_ID_COLUMN					= "SAYUU_ID";
	public  final String SAYUU_NAME_COLUMN				= "SAYUU_NAME";
	public  final String SAYUU_RYAKUNAME_COLUMN			= "SAYUU_RYAKUNAME";
	public  final String HOUKOU_ID_COLUMN				= "HOUKOU_ID";
	public  final String HOUKOU_NAME_COLUMN				= "HOUKOU_NAME";
	public  final String HOUKOU_RYAKUNAME_COLUMN			= "HOUKOU_RYAKUNAME";
	public  final String KENSAHOUHOU_ID_COLUMN			= "KENSAHOUHOU_ID";
	public  final String KENSAHOUHOU_NAME_COLUMN			= "KENSAHOUHOU_NAME";
	public  final String KENSAHOUHOU_RYAKUNAME_COLUMN	= "KENSAHOUHOU_RYAKUNAME";
	public  final String SATUEISTATUS_COLUMN				= "SATUEISTATUS";
	//
	public  final String BUI_COUNT_COLUMN				= "BUI_COUNT";
	public  final String BUI_NAME_STRING_COLUMN			= "BUI_NAME_STRING";
	public  final String BUI_RNAME_STRING_COLUMN			= "BUI_RNAME_STRING";
	public  final String SAYUU_NAME_STRING_COLUMN		= "SAYUU_NAME_STRING";
	public  final String SAYUU_RNAME_STRING_COLUMN		= "SAYUU_RNAME_STRING";
	public  final String HOUKOU_NAME_STRING_COLUMN		= "HOUKOU_NAME_STRING";
	public  final String HOUKOU_RNAME_STRING_COLUMN		= "HOUKOU_RNAME_STRING";
	public  final String KHOUHOU_NAME_STRING_COLUMN		= "KHOUHOU_NAME_STRING";
	public  final String KHOUHOU_RNAME_STRING_COLUMN		= "KHOUHOU_RNAME_STRING";
	public  final String SATUEI_COUNT_STRING_COLUMN		= "SATUEI_COUNT_STRING";
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
	public  final String SATUEISTATUSNAME_COLUMN			= "SATUEISTATUSNAME";
	public  final String SATUEISTATUSNAME_STRING_COLUMN	= "SATUEISTATUSNAME_STRING";
	public  final String SATUEISTATUSSORT_COLUMN			= "SATUEISTATUSSORT";
	// 2011.08.05 Add H.Satou@MERX End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisBuiSummaryView()
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
            + KENSA_DATE_COLUMN				+ ", "
            + NO_COLUMN						+ ", "
            + BUI_ID_COLUMN					+ ", "
            + BUI_NAME_COLUMN 				+ ", "
            + BUI_RYAKUNAME_COLUMN 			+ ", "
			+ SAYUU_ID_COLUMN 				+ ", "
			+ SAYUU_NAME_COLUMN 			+ ", "
            + SAYUU_RYAKUNAME_COLUMN 		+ ", "
			+ HOUKOU_ID_COLUMN 				+ ", "
			+ HOUKOU_NAME_COLUMN 			+ ", "
            + HOUKOU_RYAKUNAME_COLUMN		+ ", "
			+ KENSAHOUHOU_ID_COLUMN			+ ", "
			+ KENSAHOUHOU_NAME_COLUMN		+ ", "
            + KENSAHOUHOU_RYAKUNAME_COLUMN	+ ", "
			+ SATUEISTATUS_COLUMN			+ ", "
			+ BUICOMMENT_COLUMN				+ ", "
            + BUISHOWORDER_COLUMN;



		return retStr;
	}

	/// <summary>
	/// 部位情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchBuiData( Connection con, String risIDStr ) throws Exception
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if( con != null && risIDStr != null )
		{
			String sqlStr = CreateSelectSQL(risIDStr);
			dt = Select( con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/*
	/// <summary>
	/// 部位情報を検索する
	/// </summary>
	/// <param name="risIDStr">RIS_ID</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchBuiData( String risIDStr )
	{
		// parameters
		DataTable dt = null;
		Connection con = null;
		Transaction trans = null;

		con = DBConnectionFactory.GetInstance().GetRisDBConnection();
		trans = con.BeginTransaction();

		// begin log
		logger.debug("begin");

		try
		{
			if( con != null && trans != null && risIDStr != null )
			{
				String sqlStr = CreateSelectSQL(risIDStr);
				dt = Select( con, trans, sqlStr);
			}
			trans.Commit();
		}
		catch(Exception ex)
		{
			logger.fatal(ex);
			trans.Rollback();
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/
	// 2011.03.04 Del K.Shinohara Start
	/*
	/// <summary>
	/// 部位情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
    public DataTable SearchBuiData(Connection con, Transaction trans, OrderSearchParameter param)
    {
        // parameters
        DataTable dt = null;

        // begin log
        logger.debug("begin");

        if (con != null && trans != null && param != null)
        {
            Command cmd = CreateSelectSQL(param);
            cmd.Connection  = con;
			cmd.Transaction = trans;
            dt = Select(cmd, false);
            //dt = Select(con, trans, sqlStr);
        }

        // end log
        logger.debug("end");

        return dt;
    }
	*/
	// 2011.03.04 Del K.Shinohara End

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="kanjaID"></param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.ClearOrderKey();
		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(SATUEISTATUS_COLUMN, CommonString.SATUEISTATUS_STOP, true, SignType.NotEqual);
		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		//取得するカラム文字列を作成
        String colmunName = GetColumnString();

		return this.GetSelectColmunSQL(colmunName, VIEW_NAME);
	}

    /*
    /// <summary>
    /// SELECT用のSQL文を作成する
    /// </summary>
    /// <remarks></remarks>
    /// <param name="param">検索条件</param>
    /// <returns>SELECT用のSQL文</returns>
    private String CreateSelectSQL( OrderSearchParameter param )
    {
        logger.debug("begin");

		this.keys.clear();
		this.ClearOrderKey();
		this.AddOrderKeyAsc(RIS_ID_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

        //検査日
        if (param.GetExecutePeriodStartDate() != Timestamp.MinValue)
        {
            this.AddColValue(KENSA_DATE_COLUMN, param.GetExecutePeriodStartDate().Date.toString("yyyyMMdd"), true, SignType.Under);
        }
        if (param.GetExecutePeriodEndDate() != Timestamp.MinValue)
        {
            this.AddColValue(KENSA_DATE_COLUMN, param.GetExecutePeriodEndDate().Date.toString("yyyyMMdd"), true, SignType.Over);
        }

        logger.debug("end");

		String colmunName =
			  RIS_ID_COLUMN + ", "
			+ KENSA_DATE_COLUMN + ", "
			+ NO_COLUMN + ", "
			+ BUI_ID_COLUMN + ", "
			+ BUI_NAME_COLUMN + ", "
			+ BUI_RYAKUNAME_COLUMN + ", "
			+ BUICOMMENT_COLUMN + ", "
			+ BUISHOWORDER_COLUMN + ", "
			+ SAYUU_NAME_COLUMN + ", "
			+ SAYUU_RYAKUNAME_COLUMN + ", "
			+ HOUKOU_NAME_COLUMN + ", "
			+ HOUKOU_RYAKUNAME_COLUMN + ", "
			+ KENSAHOUHOU_NAME_COLUMN + ", "
			+ KENSAHOUHOU_RYAKUNAME_COLUMN;
		return this.GetSelectColmunSQL(colmunName, VIEW_NAME);
    }
    */

	// 2011.03.04 Del K.Shinohara Start
	/*
    /// <summary>
    /// SELECT用のSQL文を作成する
    /// </summary>
    /// <remarks></remarks>
    /// <param name="param">検索条件</param>
    /// <returns>SELECT用のSQL文</returns>
    private Command CreateSelectSQL(OrderSearchParameter param)
    {
        logger.debug("begin");

        Command cmd = new Command();

        this.keys.clear();
        this.ClearOrderKey();
        this.AddOrderKeyAsc(RIS_ID_COLUMN);
        this.AddOrderKeyAsc(NO_COLUMN);

        //検査日
        if (param.GetExecutePeriodStartDate() != Timestamp.MinValue)
        {
			this.AddColSetValue(KENSA_DATE_COLUMN, ":" + KENSA_DATE_COLUMN + "_1", true, SignType.Under);
			String startDate = param.GetExecutePeriodStartDate().Date.toString("yyyyMMdd");
			Parameter oraParam = new Parameter(KENSA_DATE_COLUMN + "_1", startDate);
			cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(KENSA_DATE_COLUMN + "_1", .DataAccess.Client.DbType.Int32, Integer.parseInt(startDate), ParameterDirection.Input);
        }
        if (param.GetExecutePeriodEndDate() != Timestamp.MinValue)
        {
            this.AddColSetValue(KENSA_DATE_COLUMN, ":" + KENSA_DATE_COLUMN + "_2", true, SignType.Over);
			String endDate = param.GetExecutePeriodEndDate().Date.toString("yyyyMMdd");
			Parameter oraParam = new Parameter(KENSA_DATE_COLUMN + "_2", endDate);
			cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(KENSA_DATE_COLUMN + "_2", .DataAccess.Client.DbType.Int32, Integer.parseInt(endDate), ParameterDirection.Input);
        }

		//患者ID
		if (param.GetExecutePeriodStartDate() == Timestamp.MinValue &&
			param.GetExecutePeriodEndDate()   == Timestamp.MinValue &&
			param.GetKanjaID().length() > 0)
		{
			this.AddColSetValue(KANJA_ID_COLUMN, ":" + KANJA_ID_COLUMN, true, SignType.Equal);
			String kanjaID = param.GetKanjaID();
			Parameter oraParam = new Parameter(KANJA_ID_COLUMN, kanjaID);
			cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(KANJA_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, kanjaID, ParameterDirection.Input);
		}

		//RIS_ID
		String[] risIDs = param.GetRisIDList().Split(',');
		if (param.GetRisIDList().length() > 0)
		{
			if (risIDs.length() == 1)
			{
				//1件の場合-バインド変数利用
				this.AddColSetValue(RIS_ID_COLUMN, ":" + RIS_ID_COLUMN, true, SignType.Equal);
				String risID = risIDs[0];
				Parameter oraParam = new Parameter(RIS_ID_COLUMN, risID);
				cmd.Parameters.Add(oraParam);
				//cmd.Parameters.Add(RIS_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, risID, ParameterDirection.Input);
			}
			else
			{
				//N件の場合-バインド不可のため普通に追加する
				for (int i=0; i<risIDs.length(); i++)
				{
					this.AddColValue(RIS_ID_COLUMN, risIDs[i], true, SignType.In);
				}
			}
		}

        logger.debug("end");

		//取得するカラム文字列を作成
        String colmunName = GetColumnString();

        cmd.CommandText = this.GetSelectColmunSQL(colmunName, VIEW_NAME);

        return cmd;
    }
	*/
	// 2011.03.04 Del K.Shinohara End

}
