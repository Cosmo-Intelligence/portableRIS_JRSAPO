package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExFilmBean;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
///
/// 実績フィルム情報の管理クラス
///
/// Copyright (C) 2008, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2008.09.04	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisExFilmInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "EXFILMTABLE";
	private  final String TABLE_CAPTION = "";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String RIS_ID_COLUMN				= "RIS_ID";
	public static final String BUI_NO_COLUMN				= "BUI_NO";
	public static final String NO_COLUMN					= "NO";
	public static final String FILM_ID_COLUMN				= "FILM_ID";
	public static final String PARTITION_COLUMN			= "PARTITION";
	public static final String USED_COLUMN					= "USED";
	public static final String LOSS_COLUMN					= "LOSS";

	//表示用
	public static final String FILM_NAME_COLUMN			= "FILM_NAME";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisExFilmInfoTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// RisIDに紐付くフィルム情報リストを取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">検索対象のRisID</param>
	/// <returns>フィルム情報リスト</returns>
	public ArrayList GetFilmListByRisID(Connection con, String risID) throws Exception
	{
		// parameters
		ArrayList filmBeanList = new ArrayList();

		// begin log
		logger.debug("begin");

		if (con != null && risID != null)
		{
			DataTable dt = Select(con, CreateSelectByRisIdSQL(risID), null);
			for (int i=0; dt != null && i<dt.getRowCount(); i++)
			{
				ExFilmBean filmBean = this.CreateExFilmBean(dt.getRows().get(i));
				filmBeanList.add(filmBean);
			}
		}

		// end log
		logger.debug("end");

		return filmBeanList;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する(RisIDで検索)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">SELECT対象部位のRIS_ID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectByRisIdSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.orderKeys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true);

		this.AddOrderKeyAsc(RIS_ID_COLUMN);
		this.AddOrderKeyAsc(BUI_NO_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// 実績フィルム情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">実績フィルム情報</param>
	/// <returns></returns>
	public boolean InsertExFilmData(Connection con, ExFilmBean bean) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && bean != null)
		{
			retFlg = Insert(con, CreateInsertSQL(bean), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// 実績フィルム情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象フィルム情報のRisID</param>
	/// <returns></returns>
	public boolean DeleteAllFilmDataByRisID(Connection con, String risID) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && risID.length() > 0)
		{
			retFlg = ForceDelete(con, CreateDeleteSQLByRisID(risID), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">INSERT対象のフィルム情報</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL( ExFilmBean bean )
	{
		logger.debug("begin");

		SetInfoValue(bean);

		logger.debug("end");

		return this.GetInsertSQL();

	}

	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQLByRisID(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}

	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="bean"></param>
	private void SetInfoValue(ExFilmBean bean)
	{
		logger.debug("begin");

		// カラム値設定

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, bean.GetRisID(), true);
		this.AddColValue(BUI_NO_COLUMN, bean.GetBuiNo(), true);
		this.AddColValue(NO_COLUMN, bean.GetNo(), true);

		this.AddColValue(FILM_ID_COLUMN, bean.GetFilmID());
		this.AddColValue(PARTITION_COLUMN, bean.GetPartition());
		this.AddColValue(USED_COLUMN, bean.GetUsed());
		this.AddColValue(LOSS_COLUMN, bean.GetLoss());

		logger.debug("end");
	}

	/// <summary>
	/// フィルム情報Bean作成
	/// </summary>
	/// <param name="row"></param>
	/// <returns></returns>
	private ExFilmBean CreateExFilmBean(DataRow row)
	{
		// フィルム情報Bean作成
		ExFilmBean bean = new ExFilmBean();

		// beanへ設定

		// PrimaryKey
		bean.SetRisID((String)row.get(RIS_ID_COLUMN));
		bean.SetBuiNo(row.get(BUI_NO_COLUMN).toString());
		bean.SetNo(row.get(NO_COLUMN).toString());

		// Data
		if (row.get(FILM_ID_COLUMN) != null)
			bean.SetFilmID(row.get(FILM_ID_COLUMN).toString());
		if (row.get(PARTITION_COLUMN) != null)
			bean.SetPartition(row.get(PARTITION_COLUMN).toString());
		if (row.get(USED_COLUMN) != null)
			bean.SetUsed(row.get(USED_COLUMN).toString());
		if (row.get(LOSS_COLUMN) != null)
			bean.SetLoss(row.get(LOSS_COLUMN).toString());

		return bean;
	}

	/*
	/// <summary>
	///	帳票用-実績フィルム情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintExFilmDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
	//
	//	// parameters
	//	StringBuilder sqlStrBuild = new StringBuilder("");
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//	sqlStrBuild.append(" SELECT /*+ INDEX(EF) INDEX(OM) INDEX(EO) INDEX(EM) */ EF.* FROM ");
	//	// 元の処理
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ EF.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT EF.* FROM ");			//
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " EF");
	//	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
	//	// 2011.06.24 Add K.Shinohara Start A0060
	//	sqlStrBuild.append(", EXMAINTABLE EM ");
	//	// 2011.06.24 Add K.Shinohara End
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
	//	sqlStrBuild.append(" OM.RIS_ID = EF.RIS_ID ");
	//	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
	//	sqlStrBuild.append(" ORDER BY OM.RIS_ID, EF.BUI_NO, EF.NO ");
	//
	//	logger.debug("end");
	//
	//	return sqlStrBuild.toString();
	//}

	/*
	//2010.10.29 Add K.Shinohara Start
	/// <summary>
	/// 一覧用-実績フィルム情報の取得
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>フィルム情報リスト</returns>
	public Hashtable GetListFilmList(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		Hashtable retHash = new Hashtable();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null &&
			((param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
			  param.GetExecutePeriodEndDate()   != Timestamp.MinValue) ||
			  param.GetUnKnownDateBool()))
		{
			String sqlStr = CreateListSelectSQL(param);
			DataTable dt = Select(con, trans, sqlStr);
			for (int i=0; dt != null && i<dt.Rows.Count; i++)
			{
				ExFilmBean filmBean = this.CreateExFilmBean(dt.Rows[i]);

				String risID = filmBean.GetRisID();
				if (retHash.ContainsKey(risID))
				{
					//リストへ追加
					ArrayList filmList = (ArrayList)retHash[risID];
					filmList.Add(filmBean);
				}
				else
				{
					//リストを作成
					ArrayList filmList = new ArrayList();
					filmList.Add(filmBean);
					retHash.Add(risID, filmList);
				}
			}
		}

		// end log
		logger.debug("end");

		return retHash;
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する(RisIDで検索)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">一覧検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	//private String CreateListSelectSQL(OrderSearchParameter param)
	//{
	//	// parameters
	//	StringBuilder sqlStrBuild = new StringBuilder("");
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//	sqlStrBuild.append(" SELECT /*+ INDEX(EF) INDEX(EM) */ EF.* FROM ");
	//	// 元の処理
	//	//// 2010.11.08 Mod K.Shinohara Start
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ EF.* FROM ");
	//	////sqlStrBuild.append(" SELECT /*+ LEADING(EM EF) USE_NL(EM EF) */ EF.* FROM ");
	//	////sqlStrBuild.append(" SELECT EF.* FROM ");
	//	//// 2010.11.08 Mod K.Shinohara End
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(" EXFILMTABLE EF, EXMAINTABLE EM ");
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" EM.RIS_ID = EF.RIS_ID ");
	//	if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
	//		param.GetExecutePeriodEndDate()   != Timestamp.MinValue &&
	//		param.GetUnKnownDateBool())
	//	{
	//		// 検査日or日未定
	//		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);
	//
	//		sqlStrBuild.append(" AND ((EM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd"));
	//		sqlStrBuild.append(" AND   EM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ");
	//		sqlStrBuild.append(" OR   EM.KENSA_DATE = " + unknownDateStr + ") ");
	//	}
	//	else if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
	//		     param.GetExecutePeriodEndDate()   != Timestamp.MinValue)
	//	{
	//		// 検査日
	//		sqlStrBuild.append(" AND EM.KENSA_DATE >= "	+ param.GetExecutePeriodStartDate().toString("yyyyMMdd"));
	//		sqlStrBuild.append(" AND EM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd"));
	//	}
	//	else
	//	{
	//		// 日未定
	//		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);
	//
	//		sqlStrBuild.append(" AND EM.KENSA_DATE = " + unknownDateStr);
	//	}
	//	if (param.GetKanjaIDOnlyBool() && param.GetKanjaID().length() > 0)
	//	{
	//		sqlStrBuild.append(" AND EM.KANJA_ID = '" + param.GetKanjaID() + "' ");
	//	}
	//
	//	logger.debug("end");
	//
	//	return sqlStrBuild.toString();
	//}
	//2010.10.29 Add K.Shinohara End
}
