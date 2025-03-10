package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExInfuseBean;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
/// RisExInfuseInfoTbl の概要の説明です。
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisExInfuseInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "EXINFUSETABLE";
	private  final String TABLE_CAPTION = "";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	private static final String RIS_ID_COLUMN					= "RIS_ID";
	public static final String BUI_NO_COLUMN					= "BUI_NO";
	public static final String NO_COLUMN						= "NO";
	public static final String INFUSE_ID_COLUMN				= "INFUSE_ID";
	public static final String INFUSE_NAME_COLUMN				= "INFUSE_NAME";
	public static final String SUURYOU_IJI_COLUMN				= "SUURYOU_IJI";
	public static final String SUURYOU_COLUMN					= "SUURYOU";
	public static final String PARTSBUNRUI_ID_COLUMN			= "PARTSBUNRUI_ID";
	public static final String PARTSBUNRUI_NAME_COLUMN		= "PARTSBUNRUI_NAME";
	public static final String DETAILPARTSBUNRUI_ID_COLUMN	= "DETAILPARTSBUNRUI_ID";
	public static final String DETAILPARTSBUNRUI_NAME_COLUMN	= "DETAILPARTSBUNRUI_NAME";
	public static final String BARCODEDATA_COLUMN				= "BARCODEDATA";

	public static final String SHOW_NO_COLUMN					= "SHOW_NO"; //表示用
	public static final String UNIT_COLUMN					= "UNIT";    //表示用

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisExInfuseInfoTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption = TABLE_CAPTION;
	}

	/// <summary>
	/// 手技情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="infuse">手技情報</param>
	/// <returns></returns>
	public boolean InsertInfuseData(Connection con, ExInfuseBean infuse) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && infuse != null && infuse.GetRisID().length() > 0)
		{
			retFlg = Insert(con, CreateInsertSQL(infuse), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/*
	/// <summary>
	/// 手技情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="infuse">手技情報</param>
	/// <returns></returns>
	public boolean UpdateInfuseData(Connection con, Transaction trans, ExInfuseBean infuse)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && infuse != null)
		{
			retFlg = Update(con, trans, CreateUpdateSQL(infuse));
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// 手技情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="buiNo">削除対象手技情報の部位No</param>
	/// <param name="risID">削除対象手技情報のRisID</param>
	/// <param name="no">削除対象手技情報のNo</param>
	/// <returns></returns>
	public boolean DeleteInfuseData(Connection con, Transaction trans, String risID, String buiNo, String no)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null && buiNo != null && no != null )
		{
			retFlg = Delete( con, trans, CreateDeleteSQL(risID,buiNo,no) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/// <summary>
	/// 手技情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象手技情報のRisID</param>
	/// <returns></returns>
	public boolean DeleteAllInfuseDataByRisID(Connection con, String risID) throws Exception
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

	/*
	/// <summary>
	/// 手技情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="buiNo">削除対象手技情報の部位No</param>
	/// <param name="risID">削除対象手技情報のRisID</param>
	/// <param name="no">削除対象手技情報のNo</param>
	/// <returns>手技情報</returns>
	public ExInfuseBean GetInfuseData(Connection con, Transaction trans, String risID, String buiNo, String no)
	{
		// parameters
		ExInfuseBean infuseBean = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null && buiNo != null && no != null )
		{
			DataTable dt = Select( con, trans, CreateSelectSQL(risID, buiNo, no) );
			if( dt != null && dt.Rows.Count == 1 )
			{
				infuseBean = this.CreateExInfuseBean(dt.Rows[0]);
			}
		}

		// end log
		logger.debug("end");

		return infuseBean;
	}
	*/

	/// <summary>
	/// RisIDに紐付く手技情報リストを取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">検索対象オーダー部位のRisID</param>
	/// <returns>手技情報リスト</returns>
	public ArrayList GetInfuseListByRisID(Connection con, String risID) throws Exception
	{
		// parameters
		ArrayList infuseBeanList = new ArrayList();

		// begin log
		logger.debug("begin");

		if( con != null && risID != null )
		{
			DataTable dt = Select( con, CreateSelectByRisIdSQL(risID), null );
			for( int i=0; dt != null && i<dt.getRowCount(); i++ )
			{
				ExInfuseBean infuseBean = this.CreateExInfuseBean(dt.getRows().get(i));
				infuseBeanList.add(infuseBean);
			}
		}

		// end log
		logger.debug("end");

		return infuseBeanList;
	}

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="infuse">INSERT対象の手技データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL(ExInfuseBean infuse)
	{
		logger.debug("begin");

		SetInfoValue(infuse);

		logger.debug("end");

		return this.GetInsertSQL();

	}

	/*
	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="infuse">UPDATE対象の手技データ</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateSQL(ExInfuseBean infuse)
	{
		logger.debug("begin");

		SetInfoValue(infuse);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="buiNo"></param>
	/// <param name="no"></param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL( String risID, String buiNo, String no )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(BUI_NO_COLUMN, buiNo, true);
		this.AddColValue(NO_COLUMN, no, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQLByRisID( String risID )
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
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="buiNo"></param>
	/// <param name="no"></param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL( String risID, String buiNo, String no )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(BUI_NO_COLUMN, buiNo, true);
		this.AddColValue(NO_COLUMN, no, true);

		this.AddOrderKeyAsc(RIS_ID_COLUMN);
		this.AddOrderKeyAsc(BUI_NO_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する(RisIDで検索)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="buiUIDStr">SELECT対象部位のUID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectByRisIdSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		this.AddOrderKeyAsc(BUI_NO_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	//
	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="infuse"></param>
	private void SetInfoValue(ExInfuseBean infuse)
	{
		logger.debug("begin");

		// カラム値設定

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN,					infuse.GetRisID(), true);
		this.AddColValue(BUI_NO_COLUMN,					infuse.GetBuiNo(), true);
		this.AddColValue(NO_COLUMN,						infuse.GetNo(),    true);

		this.AddColValue(INFUSE_ID_COLUMN,				infuse.GetInfuseID());
		this.AddColValue(SUURYOU_IJI_COLUMN,			infuse.GetSuuryoIji());
		this.AddColValue(SUURYOU_COLUMN,				infuse.GetSuuryo());
		this.AddColValue(PARTSBUNRUI_ID_COLUMN,			infuse.GetPartsBunruiID());
		this.AddColValue(DETAILPARTSBUNRUI_ID_COLUMN,	infuse.GetDetailPartsBunruiID());
		this.AddColValue(BARCODEDATA_COLUMN,			infuse.GetBarcodeData());

		logger.debug("end");
	}

	//
	/// <summary>
	/// 手技情報Bean作成
	/// </summary>
	/// <param name="row"></param>
	/// <returns></returns>
	private ExInfuseBean CreateExInfuseBean(DataRow row)
	{
		// 手技情報Bean作成
		ExInfuseBean bean = new ExInfuseBean();

		// beanへ設定

		// PrimaryKey
		bean.SetRisID(row.get(RIS_ID_COLUMN).toString());
		bean.SetBuiNo(row.get(BUI_NO_COLUMN).toString());
		bean.SetNo(row.get(NO_COLUMN).toString());

		// Data
		if(row.get(INFUSE_ID_COLUMN) != null)
			bean.SetInfuseID(row.get(INFUSE_ID_COLUMN).toString());
		if(row.get(SUURYOU_IJI_COLUMN) != null)
			bean.SetSuuryoIji(row.get(SUURYOU_IJI_COLUMN).toString());
		if(row.get(SUURYOU_COLUMN) != null)
			bean.SetSuuryo(row.get(SUURYOU_COLUMN).toString());
		if(row.get(PARTSBUNRUI_ID_COLUMN) != null)
			bean.SetPartsBunruiID(row.get(PARTSBUNRUI_ID_COLUMN).toString());
		if(row.get(DETAILPARTSBUNRUI_ID_COLUMN) != null)
			bean.SetDetailPartsBunruiID(row.get(DETAILPARTSBUNRUI_ID_COLUMN).toString());
		if (row.get(BARCODEDATA_COLUMN) != null)
			bean.SetBarcodeData(row.get(BARCODEDATA_COLUMN).toString());

		return bean;
	}

	/*
	/// <summary>
	///	帳票用-実績手技情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintExInfuseDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
	//	sqlStrBuild.append(" SELECT /*+ INDEX(EI) INDEX(OM) INDEX(EO) INDEX(EM) */ EI.* FROM ");
	//	// 元の処理
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ EI.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT EI.* FROM ");			//

	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " EI");
	//	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
	//	// 2011.06.24 Add K.Shinohara Start A0060
	//	sqlStrBuild.append(", EXMAINTABLE EM ");
	//	// 2011.06.24 Add K.Shinohara End
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
	//	sqlStrBuild.append(" OM.RIS_ID = EI.RIS_ID ");
	//	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
	//	sqlStrBuild.append(" ORDER BY OM.RIS_ID, EI.BUI_NO, EI.NO ");
	//
	//	logger.debug("end");
	//
	//	return sqlStrBuild.toString();
	//}
}
