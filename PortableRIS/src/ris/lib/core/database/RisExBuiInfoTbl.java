package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExBuiBean;
import ris.lib.core.util.CommonString;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
///
/// 実績部位情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.12	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisExBuiInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static  final String TABLE_NAME = "EXBUITABLE";
	private  final String TABLE_CAPTION = "";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String RIS_ID_COLUMN			= "RIS_ID";
	public static final String NO_COLUMN				= "NO";
	public static final String BUISET_ID_COLUMN		= "BUISET_ID";
	public static final String BUI_ID_COLUMN			= "BUI_ID";
	public static final String HOUKOU_ID_COLUMN		= "HOUKOU_ID";
	public static final String SAYUU_ID_COLUMN			= "SAYUU_ID";
	public static final String KENSAHOUHOU_ID_COLUMN	= "KENSAHOUHOU_ID";
	public static final String SATUEISTATUS_COLUMN		= "SATUEISTATUS";
	public static final String PRESET_NAME_COLUMN		= "PRESET_NAME";
	public static final String HIS_ORIGINAL_FLG_COLUMN	= "HIS_ORIGINAL_FLG";
	public static final String BUICOMMENT_COLUMN		= "BUICOMMENT";
	public static final String BUIORDER_NO_COLUMN		= "BUIORDER_NO";
	public static final String KENSASITU_ID_COLUMN		= "KENSASITU_ID";
	public static final String KENSAKIKI_ID_COLUMN		= "KENSAKIKI_ID";

	// 2024.07.24 Del Nishihara@COSMO Start 帝京大学病院環境対応
	// 2021.11.16 Add Nishihara@COSMO start
//	public static final String TAII_ID_COLUMN		= "TAII_ID";
//	public static final String SHIJI_ID_COLUMN		= "SHIJI_ID";
//	public static final String KAKUSYU_ID_COLUMN	= "KAKUSYU_ID";
//	public static final String US_ID_COLUMN			= "US_ID";
//	public static final String SIJI_ID_COLUMN		= "SIJI_ID";
//	public static final String ADDENDUM01_COLUMN	= "ADDENDUM01";
//	public static final String ADDENDUM02_COLUMN	= "ADDENDUM02";
	// 2021.11.16 Add Nishihara@COSMO end
	// 2024.07.24 Del Nishihara@COSMO Start 帝京大学病院環境対応

	//名称用
	public static final String BUI_NAME_COLUMN			= "BUI_NAME";
	public static final String HOUKOU_NAME_COLUMN		= "HOUKOU_NAME";
	public static final String BUI_RYAKUNAME_COLUMN	= "BUI_RYAKUNAME";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisExBuiInfoTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption = TABLE_CAPTION;
	}

	/// <summary>
	/// RisIDに紐付く実績部位情報リストを取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">検索対象実績部位のRisID</param>
	/// <returns>実績部位情報リスト</returns>
	public ArrayList GetExBuiListByRisID(Connection con, String risID) throws Exception
	{
		// parameters
		ArrayList buiBeanList = new ArrayList();

		// begin log
		logger.debug("begin");

		if (con != null && risID != null)
		{
			DataTable dt = Select(con, CreateSelectByRisIdSQL(risID), null);
			for (int i=0; dt != null && i<dt.getRowCount(); i++)
			{
				ExBuiBean buiBean = CreateExBuiBean(dt.getRows().get(i));
				buiBeanList.add(buiBean);
			}
		}

		// end log
		logger.debug("end");

		return buiBeanList;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する(RisIDで検索)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="buiUIDStr">SELECT対象部位のUID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectByRisIdSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		this.AddOrderKeyAsc(RIS_ID_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// 実績部位情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">実績部位情報</param>
	/// <returns></returns>
	public boolean InsertExBuiData(Connection con, ExBuiBean bean) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && bean != null && bean.GetRisID().length() > 0)
		{
			retFlg = Insert(con, CreateInsertSQL(bean), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/*
	/// <summary>
	/// 実績部位情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bui">実績部位情報</param>
	/// <returns></returns>
	public DataTable SelectExBuiData( Connection con, Transaction trans, String risStr)
	{
		// parameters
		DataTable dataTable = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risStr != null )
		{
			dataTable = Select( con, trans, CreateSelectSQL(risStr) );
		}

		if( dataTable != null )
		{
			//カラム追加
			dataTable.Columns.Add( BUI_NAME_COLUMN, typeof(String) );
			dataTable.Columns.Add( HOUKOU_NAME_COLUMN, typeof(String) );
		}

		// end log
		logger.debug("end");

		return dataTable;
	}
	*/

	/// <summary>
	/// 実績部位情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象実績部位のRisID</param>
	/// <param name="no">削除対象実績部位のNo</param>
	/// <returns></returns>
	public boolean DeleteAllExBuiData( Connection con, String risID ) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && risID.length() > 0)
		{
			retFlg = ForceDelete(con, CreateDeleteSQL(risID), null );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// オーダー部位情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bui">オーダー部位情報</param>
	/// <returns></returns>
	public boolean InsertOrderBuiData(Connection con, DataRow dr) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && dr != null)
		{
			retFlg = Insert(con, CreateInsertSQL(dr), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bui">INSERT対象の部位データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL(DataRow dr)
	{
		logger.debug("begin");

		SetInfoValue(dr);

		logger.debug("end");

		return this.GetInsertSQL();

	}

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">INSERT対象の部位データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL( ExBuiBean bean )
	{
		logger.debug("begin");

		SetInfoValue(bean);

		logger.debug("end");

		return this.GetInsertSQL();

	}

	/*
	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bui">UPDATE対象の部位データ</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateSQL(ExBuiBean bean)
	{
		logger.debug("begin");

		SetInfoValue(bean);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="buiUIDStr">削除対象部位患者のUID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}

	/*
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RIS_ID_COLUMN, risID,true,SignType.Equal);

		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	*/

	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="bui">部位情報</param>
	private void SetInfoValue(ExBuiBean bean)
	{
		logger.debug("begin");

		this.keys.clear();

		// カラム値設定

		this.AddColValue(RIS_ID_COLUMN,				bean.GetRisID(), true);
		this.AddColValue(NO_COLUMN,					bean.GetNo(),    true);
		//
		this.AddColValue(BUISET_ID_COLUMN,			bean.GetBuiSetID());
		this.AddColValue(BUI_ID_COLUMN,				bean.GetBuiID());
		this.AddColValue(HOUKOU_ID_COLUMN,			bean.GetHoukouID());
		this.AddColValue(SAYUU_ID_COLUMN,			bean.GetSayuuID());
		this.AddColValue(KENSAHOUHOU_ID_COLUMN,		bean.GetKensaHouhouID());
		this.AddColValue(SATUEISTATUS_COLUMN,		bean.GetSatueiStatus());
		this.AddColValue(PRESET_NAME_COLUMN,		bean.GetPresetName());
		this.AddColValue(HIS_ORIGINAL_FLG_COLUMN,	bean.GetHisOriginalFlg());
		this.AddColValue(BUICOMMENT_COLUMN,			bean.GetBuiComment());
		this.AddColValue(BUIORDER_NO_COLUMN,		bean.GetBuiOrderNo());
		this.AddColValue(KENSASITU_ID_COLUMN,		bean.GetKensaSituID());
		this.AddColValue(KENSAKIKI_ID_COLUMN,		bean.GetKensaKikiID());

		// 2024.07.24 Del Nishihara@COSMO Start 帝京大学病院環境対応
//		this.AddColValue(TAII_ID_COLUMN,		bean.GetTaiiID());
//		this.AddColValue(SHIJI_ID_COLUMN,		bean.GetShijiID());
//		this.AddColValue(KAKUSYU_ID_COLUMN,		bean.getKakusyuID());
//		this.AddColValue(US_ID_COLUMN,		bean.GetUsID());
//		this.AddColValue(SIJI_ID_COLUMN,		bean.GetSijiID());
//		this.AddColValue(ADDENDUM01_COLUMN,		bean.GetAddendum01ID());
//		this.AddColValue(ADDENDUM02_COLUMN,		bean.GetAddendum02ID());
		// 2024.07.24 Del Nishihara@COSMO End   帝京大学病院環境対応


		logger.debug("end");
	}

	/// <summary>
	/// 実績部位情報Bean作成
	/// </summary>
	/// <param name="row">実績部位情報</param>
	/// <returns></returns>
	public ExBuiBean CreateExBuiBean(DataRow row)
	{
		// 実績部位情報Bean作成
		ExBuiBean bean = new ExBuiBean();

		// beanへ設定

		// PrimaryKey
		bean.SetRisID(row.get(RIS_ID_COLUMN).toString());
		bean.SetNo(Integer.parseInt(row.get(NO_COLUMN).toString()));

		// Data
		if (row.get(BUISET_ID_COLUMN) != null)
			bean.SetBuiSetID(row.get(BUISET_ID_COLUMN).toString());
		if (row.get(BUI_ID_COLUMN) != null)
			bean.SetBuiID(row.get(BUI_ID_COLUMN).toString());
		if (row.get(HOUKOU_ID_COLUMN) != null)
			bean.SetHoukouID(row.get(HOUKOU_ID_COLUMN).toString());
		if (row.get(SAYUU_ID_COLUMN) != null)
			bean.SetSayuuID(row.get(SAYUU_ID_COLUMN).toString());
		if (row.get(KENSAHOUHOU_ID_COLUMN) != null)
			bean.SetKensaHouhouID(row.get(KENSAHOUHOU_ID_COLUMN).toString());
		if (row.get(SATUEISTATUS_COLUMN) != null)
			bean.SetSatueiStatus(row.get(SATUEISTATUS_COLUMN).toString());
		if (row.get(PRESET_NAME_COLUMN) != null)
			bean.SetPresetName(row.get(PRESET_NAME_COLUMN).toString());
		if (row.get(HIS_ORIGINAL_FLG_COLUMN) != null)
			bean.SetHisOriginalFlg(row.get(HIS_ORIGINAL_FLG_COLUMN).toString());
		if (row.get(BUICOMMENT_COLUMN) != null)
			bean.SetBuiComment(row.get(BUICOMMENT_COLUMN).toString());
		if (row.get(BUIORDER_NO_COLUMN) != null)
			bean.SetBuiOrderNo(row.get(BUIORDER_NO_COLUMN).toString());
		if (row.get(KENSASITU_ID_COLUMN) != null)
			bean.SetKensaSituID(row.get(KENSASITU_ID_COLUMN).toString());
		if (row.get(KENSAKIKI_ID_COLUMN) != null)
			bean.SetKensaKikiID(row.get(KENSAKIKI_ID_COLUMN).toString());

		// 2024.07.24 Del Nishihara@COSMO Start 帝京大学病院環境対応
		// 2021.11.16 Add Nishihara@COSMO start
//		if (row.get(TAII_ID_COLUMN) != null)
//			bean.SetTaiiID(row.get(TAII_ID_COLUMN).toString());
//		if (row.get(SHIJI_ID_COLUMN) != null)
//			bean.SetShijiID(row.get(SHIJI_ID_COLUMN).toString());
//		if (row.get(KAKUSYU_ID_COLUMN) != null)
//			bean.setKakusyuID(row.get(KAKUSYU_ID_COLUMN).toString());
//		if (row.get(US_ID_COLUMN) != null)
//			bean.SetUsID(row.get(US_ID_COLUMN).toString());
//		if (row.get(SIJI_ID_COLUMN) != null)
//			bean.SetSijiID(row.get(SIJI_ID_COLUMN).toString());
//		if (row.get(ADDENDUM01_COLUMN) != null)
//			bean.SetAddendum01ID(row.get(ADDENDUM01_COLUMN).toString());
//		if (row.get(ADDENDUM02_COLUMN) != null)
//			bean.SetAddendum02ID(row.get(ADDENDUM02_COLUMN).toString());
		// 2024.07.24 Del Nishihara@COSMO End   帝京大学病院環境対応
		// 2021.11.16 Add Nishihara@COSMO end

		return bean;
	}

	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="bui">部位情報</param>
	private void SetInfoValue(DataRow dr)
	{
		logger.debug("begin");

		this.keys.clear();

		// beanへ設定

		if (dr.get(RIS_ID_COLUMN) != null)
		{
			this.AddColValue(RIS_ID_COLUMN, dr.get(RIS_ID_COLUMN).toString(), true);
		}
		if (dr.get(NO_COLUMN) != null)
		{
			this.AddColValue(NO_COLUMN, dr.get(NO_COLUMN).toString(), true);
		}
		if (dr.get(BUISET_ID_COLUMN) != null)
		{
			this.AddColValue(BUISET_ID_COLUMN, dr.get(BUISET_ID_COLUMN).toString());
		}
		if (dr.get(BUI_ID_COLUMN) != null)
		{
			this.AddColValue(BUI_ID_COLUMN, dr.get(BUI_ID_COLUMN).toString());
		}
		if (dr.get(SAYUU_ID_COLUMN) != null)
		{
			this.AddColValue(SAYUU_ID_COLUMN, dr.get(SAYUU_ID_COLUMN).toString());
		}
		if (dr.get(HOUKOU_ID_COLUMN) != null)
		{
			this.AddColValue(HOUKOU_ID_COLUMN, dr.get(HOUKOU_ID_COLUMN).toString());
		}
		if (dr.get(KENSAHOUHOU_ID_COLUMN) != null)
		{
			this.AddColValue(KENSAHOUHOU_ID_COLUMN, dr.get(KENSAHOUHOU_ID_COLUMN).toString());
		}
		if (dr.get(BUICOMMENT_COLUMN) != null)
		{
			this.AddColValue(BUICOMMENT_COLUMN, dr.get(BUICOMMENT_COLUMN).toString());
		}
		if (dr.get(BUIORDER_NO_COLUMN) != null)
		{
			this.AddColValue(BUIORDER_NO_COLUMN, dr.get(BUIORDER_NO_COLUMN).toString());
		}
		if (dr.get(KENSASITU_ID_COLUMN) != null)
		{
			this.AddColValue(KENSASITU_ID_COLUMN, dr.get(KENSASITU_ID_COLUMN).toString());
		}
		if (dr.get(KENSAKIKI_ID_COLUMN) != null)
		{
			this.AddColValue(KENSAKIKI_ID_COLUMN, dr.get(KENSAKIKI_ID_COLUMN).toString());
		}

		// 2024.07.24 Del Nishihara@COSMO Start 帝京大学病院環境対応
		// 2021.11.16 Add Nishihara@COSMO start
//		if (dr.get(TAII_ID_COLUMN) != null)
//		{
//			this.AddColValue(TAII_ID_COLUMN, dr.get(TAII_ID_COLUMN).toString());
//		}
//		if (dr.get(SHIJI_ID_COLUMN) != null)
//		{
//			this.AddColValue(SHIJI_ID_COLUMN, dr.get(SHIJI_ID_COLUMN).toString());
//		}
//		if (dr.get(KAKUSYU_ID_COLUMN) != null)
//		{
//			this.AddColValue(KAKUSYU_ID_COLUMN, dr.get(KAKUSYU_ID_COLUMN).toString());
//		}
//		if (dr.get(US_ID_COLUMN) != null)
//		{
//			this.AddColValue(US_ID_COLUMN, dr.get(US_ID_COLUMN).toString());
//		}
//		if (dr.get(SIJI_ID_COLUMN) != null)
//		{
//			this.AddColValue(SIJI_ID_COLUMN, dr.get(SIJI_ID_COLUMN).toString());
//		}
//		if (dr.get(ADDENDUM01_COLUMN) != null)
//		{
//			this.AddColValue(ADDENDUM01_COLUMN, dr.get(ADDENDUM01_COLUMN).toString());
//		}
//		if (dr.get(ADDENDUM02_COLUMN) != null)
//		{
//			this.AddColValue(ADDENDUM02_COLUMN, dr.get(ADDENDUM02_COLUMN).toString());
//		}
		// 2024.07.24 Del Nishihara@COSMO End   帝京大学病院環境対応
		// 2021.11.16 Add Nishihara@COSMO end

		//固定で設定
		this.AddColValue(SATUEISTATUS_COLUMN, CommonString.SATUEISTATUS_VALUE);
		this.AddColValue(HIS_ORIGINAL_FLG_COLUMN, CommonString.HIS_ORIGINAL_FLG_OFF_VALUE);

		logger.debug("end");
	}

	/*
	/// <summary>
	///	帳票用-実績部位情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintExBuiDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
	//	sqlStrBuild.append(" SELECT /*+ INDEX(EB) INDEX(OM) INDEX(EO) INDEX(EM) */ EB.* FROM ");
	//	// 元の処理
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ EB.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT EB.* FROM ");			//
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " EB");
	//	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
	//	// 2011.06.24 Add K.Shinohara Start A0060
	//	sqlStrBuild.append(", EXMAINTABLE EM ");
	//	// 2011.06.24 Add K.Shinohara End
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
	//	sqlStrBuild.append(" OM.RIS_ID = EB.RIS_ID ");
	//	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
	//	sqlStrBuild.append(" ORDER BY OM.RIS_ID, EB.NO ");
	//
	//	logger.debug("end");
	//
	//	return sqlStrBuild.toString();
	//}

	/*
	//2010.10.29 Add K.Shinohara Start
	/// <summary>
	///	一覧用-実績部位情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetListExBuiDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	//private String CreateListSelectSQL(OrderSearchParameter param)
	//{
	//	logger.debug("begin");
	//
	//	// parameters
	//	StringBuilder sqlStrBuild = new StringBuilder("");
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//	sqlStrBuild.append(" SELECT /*+ INDEX(EB) INDEX(EM) */ EB.* FROM ");
	//	// 元の処理
	//	//// 2010.11.08 Mod K.Shinohara Start
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ EB.* FROM ");
	//	////sqlStrBuild.append(" SELECT /*+ LEADING(EM EB) USE_NL(EM EB) */ EB.* FROM ");
	//	////sqlStrBuild.append(" SELECT EB.* FROM ");
	//	//// 2010.11.08 Mod K.Shinohara End
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(" EXBUITABLE EB, EXMAINTABLE EM ");
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" EM.RIS_ID = EB.RIS_ID ");
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

	/*
	// 2011.09.09 Add T.Ootsuka@MERX Start NML-CAT9-031
	/// <summary>
	///	一覧用-実績部位情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetListExBuiDataTableByRisIdList(Connection con, Transaction trans, OrderSearchParameter param)
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
					// 中止部位は除外する(=他検査)
					if (drv.Row[SATUEISTATUS_COLUMN].toString() == CommonString.SATUEISTATUS_STOP)
					{
						continue;
					}

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
