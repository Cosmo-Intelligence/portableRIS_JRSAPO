package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExtendExamInfoBean;
import ris.portable.common.Const;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
///
/// 実績拡張情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.12	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisExtendExamInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "EXTENDEXAMINFO";
	public static String TableNameStr = Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	// EXTENDEXAMINFO COLUMN
	public static final String RIS_ID_COLUMN				= "RIS_ID";
	public static final String DOUISHO_FLG_COLUMN			= "DOUISHO_FLG";
	public static final String DEJITAIZU_FLG_COLUMN		= "DEJITAIZU_FLG";
	public static final String DOKUEI_FLG_COLUMN			= "DOKUEI_FLG";
	public static final String JISSEKIKAIKEI_FLG_COLUMN	= "JISSEKIKAIKEI_FLG";
	public static final String FILMAUTO_FLG_COLUMN			= "FILMAUTO_FLG";
	public static final String SETPORTABLE_FLG_COLUMN		= "SETPORTABLE_FLG";
	public static final String KENZOU_TANTOU_ID_COLUMN		= "KENZOU_TANTOU_ID";
	public static final String KENZOU_TANTOU_NAME_COLUMN	= "KENZOU_TANTOU_NAME";
	public static final String KENZOUKINKYUU_FLG_COLUMN	= "KENZOUKINKYUU_FLG";
	public static final String KENZOU_STATUS_FLG_COLUMN	= "KENZOU_STATUS_FLG";
	public static final String KENZOU_DATE_COLUMN			= "KENZOU_DATE";
	public static final String EXAMDATA01_COLUMN			= "EXAMDATA01";
	public static final String EXAMDATA02_COLUMN			= "EXAMDATA02";
	public static final String EXAMDATA03_COLUMN			= "EXAMDATA03";
	public static final String EXAMDATA04_COLUMN			= "EXAMDATA04";
	public static final String EXAMDATA05_COLUMN			= "EXAMDATA05";
	public static final String EXAMDATA06_COLUMN			= "EXAMDATA06";
	public static final String EXAMDATA07_COLUMN			= "EXAMDATA07";
	public static final String EXAMDATA08_COLUMN			= "EXAMDATA08";
	public static final String EXAMDATA09_COLUMN			= "EXAMDATA09";
	public static final String EXAMDATA10_COLUMN			= "EXAMDATA10";
	public static final String EXAMDATA11_COLUMN			= "EXAMDATA11";
	public static final String EXAMDATA12_COLUMN			= "EXAMDATA12";
	public static final String EXAMDATA13_COLUMN			= "EXAMDATA13";
	public static final String EXAMDATA14_COLUMN			= "EXAMDATA14";
	public static final String EXAMDATA15_COLUMN			= "EXAMDATA15";
	public static final String EXAMDATA16_COLUMN			= "EXAMDATA16";
	public static final String EXAMDATA17_COLUMN			= "EXAMDATA17";
	public static final String EXAMDATA18_COLUMN			= "EXAMDATA18";
	public static final String EXAMDATA19_COLUMN			= "EXAMDATA19";
	public static final String EXAMDATA20_COLUMN			= "EXAMDATA20";

	/// <summary>
	/// コンストラクタ
	/// </summary>
    public RisExtendExamInfoTbl()
	{
		//
		this.tableNameStr = TableNameStr;
	}

	/// <summary>
	/// 拡張実績情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">取得対象のRisID</param>
	/// <returns>拡張実績情報</returns>
	public ExtendExamInfoBean GetExtendExamInfoData(Connection con, String risID) throws Exception
	{
		// parameters
		ExtendExamInfoBean bean = new ExtendExamInfoBean();

		// begin log
		logger.debug("begin");

		if (con != null && risID != null)
		{
			DataTable dt = Select(con, CreateSelectSQL(risID), null);
			if (dt != null && dt.getRowCount() == 1)
			{
				bean = CreateExtendExamInfoBean(dt.getRows().get(0));
			}
		}

		// end log
		logger.debug("end");

		return bean;
	}

	/// <summary>
	/// SELECT文の生成
	/// </summary>
	/// <param name="risID">取得対象のRisID</param>
	/// <returns>拡張実績情報を取得するSQL文</returns>
	private String CreateSelectSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// RisExtendExamInfoBean 作成
	/// </summary>
	/// <param name="row">1レコードのデータ</param>
	/// <returns>ExtendExamInfoBean</returns>
	public ExtendExamInfoBean CreateExtendExamInfoBean(DataRow row)
	{
		ExtendExamInfoBean bean = new ExtendExamInfoBean();

		// beanへ設定

		// Set Primay Key
		if (row.get(RIS_ID_COLUMN) == null)
		{
			return null;
		}

		bean.SetRisID(row.get(RIS_ID_COLUMN).toString());
		//
		if (row.get(DOUISHO_FLG_COLUMN) != null)
			bean.SetDouishoFlg(row.get(DOUISHO_FLG_COLUMN).toString());
		if (row.get(DEJITAIZU_FLG_COLUMN) != null)
			bean.SetDejitaizuFlg(row.get(DEJITAIZU_FLG_COLUMN).toString());
		if (row.get(DOKUEI_FLG_COLUMN) != null)
			bean.SetDokueiFlg(row.get(DOKUEI_FLG_COLUMN).toString());
		if (row.get(JISSEKIKAIKEI_FLG_COLUMN) != null)
			bean.SetJissekiKaikeiFlg(row.get(JISSEKIKAIKEI_FLG_COLUMN).toString());
		if (row.get(FILMAUTO_FLG_COLUMN) != null)
			bean.SetFilmAutoFlg(row.get(FILMAUTO_FLG_COLUMN).toString());
		if (row.get(SETPORTABLE_FLG_COLUMN) != null)
			bean.SetSetPortableFlg(row.get(SETPORTABLE_FLG_COLUMN).toString());
		if (row.get(KENZOU_TANTOU_ID_COLUMN) != null)
			bean.SetKenzouTantouID(row.get(KENZOU_TANTOU_ID_COLUMN).toString());
		if (row.get(KENZOU_TANTOU_NAME_COLUMN) != null)
			bean.SetKenzouTantouName(row.get(KENZOU_TANTOU_NAME_COLUMN).toString());
		if (row.get(KENZOUKINKYUU_FLG_COLUMN) != null)
			bean.SetKenzouKinkyuuFlg(row.get(KENZOUKINKYUU_FLG_COLUMN).toString());
		if (row.get(KENZOU_STATUS_FLG_COLUMN) != null)
			bean.SetKenzouStatus(row.get(KENZOU_STATUS_FLG_COLUMN).toString());
		if (row.get(KENZOU_DATE_COLUMN) != null)
			bean.SetKenzouDate((Timestamp)(row.get(KENZOU_DATE_COLUMN)));
		//
		if (row.get(EXAMDATA01_COLUMN) != null)
		{
			bean.SetExamData01(row.get(EXAMDATA01_COLUMN).toString());
		}
		if (row.get(EXAMDATA02_COLUMN) != null)
		{
			bean.SetExamData02(row.get(EXAMDATA02_COLUMN).toString());
		}
		if (row.get(EXAMDATA03_COLUMN) != null)
		{
			bean.SetExamData03(row.get(EXAMDATA03_COLUMN).toString());
		}
		if (row.get(EXAMDATA04_COLUMN) != null)
		{
			bean.SetExamData04(row.get(EXAMDATA04_COLUMN).toString());
		}
		if (row.get(EXAMDATA05_COLUMN) != null)
		{
			bean.SetExamData05(row.get(EXAMDATA05_COLUMN).toString());
		}
		if (row.get(EXAMDATA06_COLUMN) != null)
		{
			bean.SetExamData06(row.get(EXAMDATA06_COLUMN).toString());
		}
		if (row.get(EXAMDATA07_COLUMN) != null)
		{
			bean.SetExamData07(row.get(EXAMDATA07_COLUMN).toString());
		}
		if (row.get(EXAMDATA08_COLUMN) != null)
		{
			bean.SetExamData08(row.get(EXAMDATA08_COLUMN).toString());
		}
		if (row.get(EXAMDATA09_COLUMN) != null)
		{
			bean.SetExamData09(row.get(EXAMDATA09_COLUMN).toString());
		}
		if (row.get(EXAMDATA10_COLUMN) != null)
		{
			bean.SetExamData10(row.get(EXAMDATA10_COLUMN).toString());
		}
		if (row.get(EXAMDATA11_COLUMN) != null)
		{
			bean.SetExamData11(row.get(EXAMDATA11_COLUMN).toString());
		}
		if (row.get(EXAMDATA12_COLUMN) != null)
		{
			bean.SetExamData12(row.get(EXAMDATA12_COLUMN).toString());
		}
		if (row.get(EXAMDATA13_COLUMN) != null)
		{
			bean.SetExamData13(row.get(EXAMDATA13_COLUMN).toString());
		}
		if (row.get(EXAMDATA14_COLUMN) != null)
		{
			bean.SetExamData14(row.get(EXAMDATA14_COLUMN).toString());
		}
		if (row.get(EXAMDATA15_COLUMN) != null)
		{
			bean.SetExamData15(row.get(EXAMDATA15_COLUMN).toString());
		}
		if (row.get(EXAMDATA16_COLUMN) != null)
		{
			bean.SetExamData16(row.get(EXAMDATA16_COLUMN).toString());
		}
		if (row.get(EXAMDATA17_COLUMN) != null)
		{
			bean.SetExamData17(row.get(EXAMDATA17_COLUMN).toString());
		}
		if (row.get(EXAMDATA18_COLUMN) != null)
		{
			bean.SetExamData18(row.get(EXAMDATA18_COLUMN).toString());
		}
		if (row.get(EXAMDATA19_COLUMN) != null)
		{
			bean.SetExamData19(row.get(EXAMDATA19_COLUMN).toString());
		}
		if (row.get(EXAMDATA20_COLUMN) != null)
		{
			bean.SetExamData20(row.get(EXAMDATA20_COLUMN).toString());
		}

		return bean;
	}

	/// <summary>
	/// 実績拡張情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">実績拡張情報</param>
	/// <returns></returns>
	public boolean InsertExtendExamInfo( Connection con,
		ExtendExamInfoBean bean  ) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null )
		{
			retFlg = Insert(con, CreateInsertSQL(bean), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// 実績拡張情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象のRisID</param>
	/// <returns></returns>
	public boolean DeleteExtendExamData(Connection con, String risID) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && risID != null )
		{
			retFlg = ForceDelete(con, CreateDeleteSQL(risID), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">INSERT対象のデータ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL(ExtendExamInfoBean bean )
	{
		logger.debug("begin");

		SetColValue(bean);

		logger.debug("end");

		return this.GetInsertSQL();

	}

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

	/// <summary>
	/// 拡張実績情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="extendBean">拡張実績情報</param>
	/// <returns>判定</returns>
	public boolean SaveExtendExamInfo(Connection con, ExtendExamInfoBean extendBean) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && extendBean != null && extendBean.GetRisID().length() > 0)
		{
			// ExtendExamInfo 更新
			retFlg = Update(con, CreateSaveSQL(extendBean), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <param name="extend">拡張テーブルフラグ</param>
	/// <returns></returns>
	private String CreateSaveSQL(ExtendExamInfoBean exBean)
	{
		logger.debug("begin");

		SetColValue(exBean);

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/// <summary>
	/// 拡張実績情報の検像ステータスを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="extendBean">拡張実績情報</param>
	/// <returns>判定</returns>
	public boolean UpdateKenzouStatus(Connection con, ExtendExamInfoBean extendBean) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && extendBean != null && extendBean.GetRisID().length() > 0)
		{
			// ExtendExamInfo 更新
			retFlg = Update(con, CreateUpdateKenzouStatusSQL(extendBean), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="extendBean">拡張実績情報</param>
	/// <returns></returns>
	private String CreateUpdateKenzouStatusSQL(ExtendExamInfoBean extendBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, extendBean.GetRisID(), true);
		//
		this.AddColValue(KENZOU_STATUS_FLG_COLUMN, extendBean.GetKenzouStatus());

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/// <summary>
	/// 検像情報更新を行う
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="extendBean">拡張実績情報</param>
	/// <returns>判定</returns>
	public boolean UpdateKenzouStatusOperation(Connection con, ExtendExamInfoBean extendBean) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && extendBean != null && extendBean.GetRisID().length() > 0)
		{
			// ExtendExamInfo 更新
			retFlg = Update(con, CreateUpdateKenzouStatusOperation(extendBean), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="extendBean">拡張実績情報</param>
	/// <returns></returns>
	private String CreateUpdateKenzouStatusOperation(ExtendExamInfoBean extendBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, extendBean.GetRisID(), true);
		//
		this.AddColValue(KENZOU_TANTOU_ID_COLUMN,   extendBean.GetKenzouTantouID());
		this.AddColValue(KENZOU_TANTOU_NAME_COLUMN, extendBean.GetKenzouTantouName());
		this.AddColValue(KENZOU_STATUS_FLG_COLUMN, extendBean.GetKenzouStatus());
		// 2010.08.30 Add K.Shinohara Start
		if (!Const.TIMESTAMP_MINVALUE.equals(extendBean.GetKenzouDate()) )
		{
			this.AddColValue(KENZOU_DATE_COLUMN, extendBean.GetKenzouDate());
		}
		// 2010.08.30 Add K.Shinohara End

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="bean">実績拡張情報</param>
	private void SetColValue(ExtendExamInfoBean bean)
	{
		logger.debug("begin");

		this.keys.clear();

		// カラム値設定

		this.AddColValue(RIS_ID_COLUMN,				bean.GetRisID(), true);
		//
		this.AddColValue(DOUISHO_FLG_COLUMN,		bean.GetDouishoFlg());
		this.AddColValue(DEJITAIZU_FLG_COLUMN,		bean.GetDejitaizuFlg());
		this.AddColValue(DOKUEI_FLG_COLUMN,			bean.GetDokueiFlg());
		this.AddColValue(JISSEKIKAIKEI_FLG_COLUMN,	bean.GetJissekiKaikeiFlg());
		this.AddColValue(FILMAUTO_FLG_COLUMN,		bean.GetFilmAutoFlg());
		this.AddColValue(SETPORTABLE_FLG_COLUMN,	bean.GetSetPortableFlg());
		this.AddColValue(KENZOU_TANTOU_ID_COLUMN,	bean.GetKenzouTantouID());
		this.AddColValue(KENZOU_TANTOU_NAME_COLUMN,	bean.GetKenzouTantouName());
		this.AddColValue(KENZOUKINKYUU_FLG_COLUMN,	bean.GetKenzouKinkyuuFlg());
		this.AddColValue(KENZOU_STATUS_FLG_COLUMN,	bean.GetKenzouStatus());
		this.AddColValue(KENZOU_DATE_COLUMN,		bean.GetKenzouDate());
		if (Configuration.GetInstance().GetExamItemFlg())
		{
			this.AddColValue(EXAMDATA01_COLUMN, 	bean.GetExamData01());
			this.AddColValue(EXAMDATA02_COLUMN, 	bean.GetExamData02());
			this.AddColValue(EXAMDATA03_COLUMN, 	bean.GetExamData03());
			this.AddColValue(EXAMDATA04_COLUMN, 	bean.GetExamData04());
			this.AddColValue(EXAMDATA05_COLUMN, 	bean.GetExamData05());
			this.AddColValue(EXAMDATA06_COLUMN, 	bean.GetExamData06());
			this.AddColValue(EXAMDATA07_COLUMN, 	bean.GetExamData07());
			this.AddColValue(EXAMDATA08_COLUMN, 	bean.GetExamData08());
			this.AddColValue(EXAMDATA09_COLUMN, 	bean.GetExamData09());
			this.AddColValue(EXAMDATA10_COLUMN, 	bean.GetExamData10());
			this.AddColValue(EXAMDATA11_COLUMN, 	bean.GetExamData11());
			this.AddColValue(EXAMDATA12_COLUMN, 	bean.GetExamData12());
			this.AddColValue(EXAMDATA13_COLUMN, 	bean.GetExamData13());
			this.AddColValue(EXAMDATA14_COLUMN, 	bean.GetExamData14());
			this.AddColValue(EXAMDATA15_COLUMN, 	bean.GetExamData15());
			this.AddColValue(EXAMDATA16_COLUMN, 	bean.GetExamData16());
			this.AddColValue(EXAMDATA17_COLUMN, 	bean.GetExamData17());
			this.AddColValue(EXAMDATA18_COLUMN, 	bean.GetExamData18());
			this.AddColValue(EXAMDATA19_COLUMN, 	bean.GetExamData19());
			this.AddColValue(EXAMDATA20_COLUMN, 	bean.GetExamData20());
		}

		logger.debug("end");
	}

    /*
	/// <summary>
	///	帳票用-実績拡張情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintExtendExamDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
    //	sqlStrBuild.append(" SELECT /*+ INDEX(EE) INDEX(OM) INDEX(EO) INDEX(EM) */ EE.* FROM ");
    //	// 元の処理
    //	//sqlStrBuild.append(" SELECT /*+ RULE */ EE.* FROM ");	//2010.11.08 Mod H.Orikasa
    //	////sqlStrBuild.append(" SELECT EE.* FROM ");			//
    //
    //	// 2011.08.22 Mod T.Nishikubo@MERX End
    //	sqlStrBuild.append(TABLE_NAME + " EE");
    //	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
    //	// 2011.06.24 Add K.Shinohara Start A0060
    //	sqlStrBuild.append(", EXMAINTABLE EM ");
    //	// 2011.06.24 Add K.Shinohara End
    //	sqlStrBuild.append(" WHERE ");
    //	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
    //	sqlStrBuild.append(" OM.RIS_ID = EE.RIS_ID ");
    //	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
    //
    //	logger.debug("end");
    //
    //	return sqlStrBuild.toString();
    //}
}
