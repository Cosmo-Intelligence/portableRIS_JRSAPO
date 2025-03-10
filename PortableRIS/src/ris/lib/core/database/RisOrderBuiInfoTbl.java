package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import ris.lib.core.Configuration;
import ris.lib.core.bean.OrderBuiBean;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
///
/// 部位情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.12	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisOrderBuiInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "ORDERBUITABLE";
	private static final String TABLE_CAPTION = "ｵｰﾀﾞ部位情報";
	private static String TableNameStr = Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String RIS_ID_COLUMN			= "RIS_ID";
	public static final String NO_COLUMN				= "NO";
	public static final String BUISET_ID_COLUMN		= "BUISET_ID";
	public static final String BUI_ID_COLUMN			= "BUI_ID";
	public static final String HOUKOU_ID_COLUMN		= "HOUKOU_ID";
	public static final String SAYUU_ID_COLUMN			= "SAYUU_ID";
	public static final String KENSAHOUHOU_ID_COLUMN	= "KENSAHOUHOU_ID";
	public static final String BUICOMMENT_ID_COLUMN	= "BUICOMMENT_ID";
	public static final String BUICOMMENT_COLUMN		= "BUICOMMENT";
	public static final String BUIORDER_NO_COLUMN		= "BUIORDER_NO";
	public static final String KENSASITU_ID_COLUMN		= "KENSASITU_ID";
	public static final String KENSAKIKI_ID_COLUMN		= "KENSAKIKI_ID";
	public static final String ADDENDUM01_COLUMN		= "ADDENDUM01";
	public static final String ADDENDUM02_COLUMN		= "ADDENDUM02";
	public static final String ADDENDUM03_COLUMN		= "ADDENDUM03";
	public static final String ADDENDUM04_COLUMN		= "ADDENDUM04";
	public static final String ADDENDUM05_COLUMN		= "ADDENDUM05";

	//名称用
	public static final String BUIBUNRUI_NAME_COLUMN	= "BUIBUNRUI_NAME";
	public static final String BUI_NAME_COLUMN			= "BUI_NAME";
	public static final String HOUKOU_NAME_COLUMN		= "HOUKOU_NAME";
	public static final String SAYUU_NAME_COLUMN		= "SAYUU_NAME";
	public static final String KENSAHOUHOU_NAME_COLUMN = "KENSAHOUHOU_NAME";

	/// <summary>
	/// コンストラクタ
	/// </summary>
    public RisOrderBuiInfoTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//

		this.tableNameStr = TableNameStr;
	}

    /*
	/// <summary>
	/// オーダー部位情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bui">オーダー部位情報</param>
	/// <returns></returns>
	public boolean InsertOrderBuiData( Connection con, Transaction trans, OrderBuiBean bui )
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && bui != null )
		{
			retFlg = Insert( con, trans, CreateInsertSQL(bui) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

    /*
	/// <summary>
	/// オーダー部位情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象オーダー部位のRisID</param>
	/// <returns></returns>
	public boolean DeleteOrderBuiData(Connection con, Transaction trans, String risID)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			retFlg = ForceDelete(con, trans, CreateDeleteSQL(risID));
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

    /*
	/// <summary>
	/// オーダー部位情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象オーダー部位のRisID</param>
	/// <param name="no">削除対象オーダー部位のNo</param>
	/// <returns></returns>
	public boolean DeleteOrderBuiData( Connection con, Transaction trans, String risID, String no )
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null && no != null )
		{
			retFlg = Delete( con, trans, CreateDeleteSQL(risID,no) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/// <summary>
	/// オーダー部位情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">検索対象オーダー部位のRisID</param>
	/// <param name="no">検索対象オーダー部位のNo</param>
	/// <returns>プラン部位情報</returns>
	public DataTable GetOrderBuiData( Connection con, String risID) throws Exception
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if( con != null && risID != null )
		{
			ArrayList<Object> arglist = new ArrayList<Object>();
			String cmd = CreateSelectSQL(risID, arglist);
			dt = Select(con, cmd, arglist);
		}

		if (dt != null)
		{
			//dt.Columns.Add(RisOrderBuiInfoTbl.BUIBUNRUI_NAME_COLUMN);
			//dt.Columns.Add(RisOrderBuiInfoTbl.BUI_NAME_COLUMN);
			//dt.Columns.Add(RisOrderBuiInfoTbl.HOUKOU_NAME_COLUMN);
			//dt.Columns.Add(RisOrderBuiInfoTbl.SAYUU_NAME_COLUMN);
			//dt.Columns.Add(RisOrderBuiInfoTbl.KENSAHOUHOU_NAME_COLUMN);
			//dt.TableName = TABLE_NAME;
		}

		// end log
		logger.debug("end");

		return dt;
	}

    /*
	/// <summary>
	/// オーダー部位情報を取得する
	/// </summary>
	/// <param name="risID">検索対象オーダー部位のRisID</param>
	/// <returns>オーダ部位情報</returns>
	public DataTable GetOrderBuiData( String risID)
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
			if( con != null && trans != null && risID != null )
			{
				Command cmd = CreateSelectSQL(risID);
				cmd.Connection  = con;
				cmd.Transaction = trans;
				dt = Select(cmd, false);
			}

			if (dt != null)
			{
				dt.Columns.Add(RisOrderBuiInfoTbl.BUIBUNRUI_NAME_COLUMN);
				dt.Columns.Add(RisOrderBuiInfoTbl.BUI_NAME_COLUMN);
				dt.Columns.Add(RisOrderBuiInfoTbl.HOUKOU_NAME_COLUMN);
				dt.Columns.Add(RisOrderBuiInfoTbl.SAYUU_NAME_COLUMN);
				dt.Columns.Add(RisOrderBuiInfoTbl.KENSAHOUHOU_NAME_COLUMN);
				dt.TableName = TABLE_NAME;
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

    /*
	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bui">INSERT対象の部位データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL( OrderBuiBean bui )
	{
		logger.debug("begin");

		SetInfoValue(bui);

		logger.debug("end");

		return this.GetInsertSQL();

	}
	*/

    /*
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RIS_ID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

    /*
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RIS_ID</param>
	/// <param name="no">部位NO</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String risID, String no)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(NO_COLUMN, no, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>SQL文</returns>
	private String CreateSelectSQL(String risID, ArrayList<Object> arglist)
	{
		logger.debug("begin");

		String cmd = "";

		this.keys.clear();
		this.inList.clear();
		this.ClearOrderKey();

		//患者ID
		if (risID.length() > 0)
		{
			this.AddColSetValue(RIS_ID_COLUMN, "?", true, SignType.Equal);
			arglist.add(risID);
			//Parameter oraParam = new Parameter(RIS_ID_COLUMN, risID);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(RIS_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, risID, ParameterDirection.Input);
		}

		this.AddOrderKeyAsc(RIS_ID_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		cmd = this.GetSelectColmunSQL(" * ", TABLE_NAME);

		logger.debug("end");

		return cmd;
	}

    /*
	/// <summary>
	/// オーダー部位情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="orderBuiBean">オーダー部位情報</param>
	/// <returns></returns>
	public boolean InsertNewOrderBuiData(Connection con, Transaction trans, OrderBuiBean orderBuiBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && orderBuiBean != null)
		{
			String sqlStr = CreateNewInsertSQL(orderBuiBean);
			retFlg = Insert(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

    /*
	/// <summary>
	/// Insert文作成
	/// </summary>
	/// <param name="bean">オーダー部位情報</param>
	/// <returns>判定</returns>
	private String CreateNewInsertSQL(OrderBuiBean bean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN,			bean.GetRisID());
		this.AddColValue(NO_COLUMN,				bean.GetNo());
		//
		this.AddColValue(BUISET_ID_COLUMN,		bean.GetBuiSetID());
		this.AddColValue(BUI_ID_COLUMN,			bean.GetBuiID());
		this.AddColValue(HOUKOU_ID_COLUMN,		bean.GetHoukouID());
		this.AddColValue(SAYUU_ID_COLUMN,		bean.GetSayuuID());
		this.AddColValue(KENSAHOUHOU_ID_COLUMN, bean.GetKensaHouhouID());
		this.AddColValue(KENSASITU_ID_COLUMN,	bean.GetKensaSituID());
		this.AddColValue(KENSAKIKI_ID_COLUMN,	bean.GetKensaKikiID());

		logger.debug("end");

		return this.GetInsertSQL();
	}
	*/

    /*
	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="bui">部位情報</param>
	private void SetInfoValue(OrderBuiBean bui)
	{
		logger.debug("begin");

		// カラム値設定

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN,			bui.GetRisID(), true);
		this.AddColValue(NO_COLUMN,				bui.GetNo(),    true);

		this.AddColValue(BUISET_ID_COLUMN,		bui.GetBuiSetID());
		this.AddColValue(BUI_ID_COLUMN,			bui.GetBuiID());
		this.AddColValue(HOUKOU_ID_COLUMN,		bui.GetHoukouID());
		this.AddColValue(SAYUU_ID_COLUMN,		bui.GetSayuuID());
		this.AddColValue(KENSAHOUHOU_ID_COLUMN,	bui.GetKensaHouhouID());
		this.AddColValue(BUICOMMENT_ID_COLUMN,	bui.GetBuiCommentID());
		this.AddColValue(BUICOMMENT_COLUMN,		bui.GetBuiComment());
		this.AddColValue(BUIORDER_NO_COLUMN,	bui.GetBuiOrderNo());
		this.AddColValue(KENSASITU_ID_COLUMN,	bui.GetKensaSituID());
		this.AddColValue(KENSAKIKI_ID_COLUMN,	bui.GetKensaKikiID());
		this.AddColValue(ADDENDUM01_COLUMN,		bui.GetAddendum01());
		this.AddColValue(ADDENDUM02_COLUMN,		bui.GetAddendum02());
		this.AddColValue(ADDENDUM03_COLUMN,		bui.GetAddendum03());
		this.AddColValue(ADDENDUM04_COLUMN,		bui.GetAddendum04());
		this.AddColValue(ADDENDUM05_COLUMN,		bui.GetAddendum05());



		logger.debug("end");
	}
	*/

	/// <summary>
	/// オーダー部位情報Bean作成
	/// </summary>
	/// <param name="row"></param>
	/// <returns></returns>
	public OrderBuiBean CreateOrderBuiBean(DataRow row)
	{
		// オーダー部位情報Bean作成
		OrderBuiBean bean = new OrderBuiBean();

		// beanへ設定

		// PrimaryKey
		bean.SetRisID(row.get(RIS_ID_COLUMN).toString());
		bean.SetNo(Integer.parseInt(row.get(NO_COLUMN).toString()));

		// Data
		if(row.get(BUISET_ID_COLUMN) != null)
			bean.SetBuiSetID(row.get(BUISET_ID_COLUMN).toString());
		if(row.get(BUI_ID_COLUMN) != null)
			bean.SetBuiID(row.get(BUI_ID_COLUMN).toString());
		if(row.get(HOUKOU_ID_COLUMN) != null)
			bean.SetHoukouID(row.get(HOUKOU_ID_COLUMN).toString());
		if(row.get(SAYUU_ID_COLUMN) != null)
			bean.SetSayuuID(row.get(SAYUU_ID_COLUMN).toString());
		if(row.get(KENSAHOUHOU_ID_COLUMN) != null)
			bean.SetKensaHouhouID(row.get(KENSAHOUHOU_ID_COLUMN).toString());
		if(row.get(BUICOMMENT_ID_COLUMN) != null)
			bean.SetBuiCommentID(row.get(BUICOMMENT_ID_COLUMN).toString());
		if(row.get(BUICOMMENT_COLUMN) != null)
			bean.SetBuiComment(row.get(BUICOMMENT_COLUMN).toString());
		if(row.get(BUIORDER_NO_COLUMN) != null)
			bean.SetBuiOrderNo(row.get(BUIORDER_NO_COLUMN).toString());
		if(row.get(KENSASITU_ID_COLUMN) != null)
			bean.SetKensaSituID(row.get(KENSASITU_ID_COLUMN).toString());
		if(row.get(KENSAKIKI_ID_COLUMN) != null)
			bean.SetKensaKikiID(row.get(KENSAKIKI_ID_COLUMN).toString());
		if(row.get(ADDENDUM01_COLUMN) != null)
			bean.SetAddendum01(row.get(ADDENDUM01_COLUMN).toString());
		if(row.get(ADDENDUM02_COLUMN) != null)
			bean.SetAddendum02(row.get(ADDENDUM02_COLUMN).toString());
		if(row.get(ADDENDUM03_COLUMN) != null)
			bean.SetAddendum03(row.get(ADDENDUM03_COLUMN).toString());
		if(row.get(ADDENDUM04_COLUMN) != null)
			bean.SetAddendum04(row.get(ADDENDUM04_COLUMN).toString());
		if(row.get(ADDENDUM05_COLUMN) != null)
			bean.SetAddendum05(row.get(ADDENDUM05_COLUMN).toString());
		//Name
		if (Arrays.asList(row.getColumnNames()).indexOf(BUIBUNRUI_NAME_COLUMN) > -1 &&
			row.get(BUIBUNRUI_NAME_COLUMN) != null)
			bean.SetBuibunruiName(row.get(BUIBUNRUI_NAME_COLUMN).toString());
		if (Arrays.asList(row.getColumnNames()).indexOf(BUI_NAME_COLUMN) > -1 &&
			row.get(BUI_NAME_COLUMN) != null)
			bean.SetBuiName(row.get(BUI_NAME_COLUMN).toString());
		if (Arrays.asList(row.getColumnNames()).indexOf(HOUKOU_NAME_COLUMN) > -1 &&
			row.get(HOUKOU_NAME_COLUMN) != null)
			bean.SetHoukouName(row.get(HOUKOU_NAME_COLUMN).toString());
		if (Arrays.asList(row.getColumnNames()).indexOf(SAYUU_NAME_COLUMN) > -1 &&
			row.get(SAYUU_NAME_COLUMN) != null)
			bean.SetSayuuName(row.get(SAYUU_NAME_COLUMN).toString());
		if (Arrays.asList(row.getColumnNames()).indexOf(KENSAHOUHOU_NAME_COLUMN) > -1 &&
			row.get(KENSAHOUHOU_NAME_COLUMN) != null)
			bean.SetKensaHouhouName(row.get(KENSAHOUHOU_NAME_COLUMN).toString());

		return bean;
	}

    /*
	/// <summary>
	///	帳票用-オーダ部位情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintOrderBuiDataTable(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		DataTable retDt = new DataTable();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sql = CreateSelectSQL(param);

			if (sql.length() > 0)
			{
				//SELECT
				retDt = Select(con, trans, sql, false);
			}
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	//private String CreateSelectSQL(OrderSearchParameter param)
    //{
    //	logger.debug("begin");

    //	// parameters
    //	StringBuilder sqlStrBuild = new StringBuilder("");

    //	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
    //	sqlStrBuild.append(" SELECT /*+ INDEX(OB) INDEX(OM) INDEX(EO) INDEX(EM) */ OB.* FROM ");
    //	// 元の処理
    //	//sqlStrBuild.append(" SELECT /*+ RULE */ OB.* FROM ");	//2010.11.08 Mod H.Orikasa
    //	////sqlStrBuild.append(" SELECT OB.* FROM ");			//

    //	// 2011.08.22 Mod T.Nishikubo@MERX End
    //	sqlStrBuild.append(TABLE_NAME + " OB");
    //	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
    //	// 2011.06.24 Add K.Shinohara Start A0060
    //	sqlStrBuild.append(", EXMAINTABLE EM ");
    //	// 2011.06.24 Add K.Shinohara End
    //	sqlStrBuild.append(" WHERE ");
    //	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
    //	sqlStrBuild.append(" OM.RIS_ID = OB.RIS_ID ");
    //	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
    //	sqlStrBuild.append(" ORDER BY OM.RIS_ID, OB.NO ");

    //	logger.debug("end");

    //	return sqlStrBuild.toString();
    //}

    /*
	//2010.10.29 Add K.Shinohara Start
	/// <summary>
	///	一覧用-オーダ部位情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetListOrderBuiDataTable(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		DataTable retDt = new DataTable();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null &&
			((param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
			  param.GetExecutePeriodEndDate()   != Timestamp.MinValue) ||
			  param.GetUnKnownDateBool()))
		{
			String sql = CreateListSelectSQL(param);

			if (sql.length() > 0)
			{
				//SELECT
				DataTable tempDt = Select(con, trans, sql, false);
				//検索結果に対してSortする
				retDt = tempDt.Clone();
				DataView dv = new DataView(tempDt);
				dv.Sort = "RIS_ID, NO";
				//ソートされたレコードのコピー
				for (DataRowView drv : dv)
				{
					retDt.ImportRow(drv.Row);
				}
			}
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="param">一覧検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
    //private String CreateListSelectSQL(OrderSearchParameter param)
    //{
    //	logger.debug("begin");

    //	// parameters
    //	StringBuilder sqlStrBuild = new StringBuilder("");

    //	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
    //	sqlStrBuild.append(" SELECT /*+ INDEX(OB) INDEX(OM) */ OB.* FROM ");
    //	// 元の処理
    //	//// 2010.11.08 Mod K.Shinohara Start
    //	//sqlStrBuild.append(" SELECT /*+ RULE */ OB.* FROM ");
    //	////sqlStrBuild.append(" SELECT /*+ LEADING(OM OB) USE_NL(OM OB) */ OB.* FROM ");
    //	////sqlStrBuild.append(" SELECT OB.* FROM ");
    //	//// 2010.11.08 Mod K.Shinohara End

    //	// 2011.08.22 Mod T.Nishikubo@MERX End
    //	sqlStrBuild.append(" ORDERBUITABLE OB, ORDERMAINTABLE OM ");
    //	sqlStrBuild.append(" WHERE ");
    //	sqlStrBuild.append(" OM.RIS_ID = OB.RIS_ID ");
    //	if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
    //		param.GetExecutePeriodEndDate()   != Timestamp.MinValue &&
    //		param.GetUnKnownDateBool())
    //	{
    //		// 検査日or日未定
    //		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);

    //		sqlStrBuild.append(" AND ((OM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd"));
    //		sqlStrBuild.append(" AND   OM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ");
    //		sqlStrBuild.append(" OR   OM.KENSA_DATE = " + unknownDateStr + ") ");
    //	}
    //	else if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
    //		     param.GetExecutePeriodEndDate()   != Timestamp.MinValue)
    //	{
    //		// 検査日
    //		sqlStrBuild.append(" AND OM.KENSA_DATE >= "	+ param.GetExecutePeriodStartDate().toString("yyyyMMdd"));
    //		sqlStrBuild.append(" AND OM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd"));
    //	}
    //	else
    //	{
    //		// 日未定
    //		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);

    //		sqlStrBuild.append(" AND OM.KENSA_DATE = " + unknownDateStr);
    //	}
    //	if (param.GetKanjaIDOnlyBool() && param.GetKanjaID().length() > 0)
    //	{
    //		sqlStrBuild.append(" AND OM.KANJA_ID = '" + param.GetKanjaID() + "' ");
    //	}

    //	logger.debug("end");

    //	return sqlStrBuild.toString();
    //}
	//2010.10.29 Add K.Shinohara End

    /*
	// 2011.09.09 Add T.Ootsuka@MERX Start NML-CAT9-031
	/// <summary>
	///	一覧用-オーダ部位情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetListOrderBuiDataTableByRisIdList(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		DataTable retDt = new DataTable();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sql = CreateListSelectSQLByRisIdList(param.GetRisIDList());

			if (sql.length() > 0)
			{
				//SELECT
				DataTable tempDt = Select(con, trans, sql, false);
				//検索結果に対してSortする
				retDt = tempDt.Clone();
				DataView dv = new DataView(tempDt);
				dv.Sort = "RIS_ID, NO";
				//ソートされたレコードのコピー
				for (DataRowView drv : dv)
				{
					retDt.ImportRow(drv.Row);
				}
			}
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

    /*
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateListSelectSQLByRisIdList(String risIdList)
	{
		logger.debug("begin");

		this.keys.clear();

		String[] risIDs = risIdList.Split(',');
		for (int i=0; i<risIDs.length(); i++)
		{
			this.AddColValue(RIS_ID_COLUMN, risIDs[i], true, SignType.In);
		}

		logger.debug("end");

		return this.GetSelectSQL();

		//logger.debug("begin");

		//this.keys.clear();
		//this.inList.clear();

		//StringBuilder sqlStrBuild = new StringBuilder("");

		//sqlStrBuild.append(" SELECT * FROM ").append(TABLE_NAME);
		//sqlStrBuild.append(" WHERE ").append(RIS_ID_COLUMN);
		//sqlStrBuild.append(" IN(");

		//String[] risIdArray = risIdList.Split(',');
		//for (int i = 0; i < risIdArray.length(); i++)
		//{
		//    String risId = "'" + risIdArray[i] + "'";
		//    if (i < risIdArray.length() -1)
		//    {
		//        risId += ",";
		//    }
		//    sqlStrBuild.appendFormat(risId);
		//}

		//sqlStrBuild.append(")");

		//logger.debug("end");

		//return sqlStrBuild.toString();
	}
	// 2011.09.09 Add T.Ootsuka@MERX End
	*/
}
