package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExtendOrderInfoBean;
import ris.portable.util.DataTable;

/// <summary>
///
/// 拡張オーダ情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			 (Date)			 (ID / NAME)			  (Comment)
/// V1.00.00		: 2009.03.12	: 112478 / A.Kobayashi	: original
/// V2.01.00		: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
/// V2.01.01.13000	: 2011.11.25	: 999999 / NSK_M.Ochiai	: OMH-1-2
///
/// </summary>
public class RisExtendOrderInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "EXTENDORDERINFO";
	private static String TableNameStr = Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	// ORDERMAINTABLE COLUMN
	public static final String RIS_ID_COLUMN					= "RIS_ID";
	public static final String ORDER_DATE_COLUMN				= "ORDER_DATE";
	public static final String USER_NO_COLUMN					= "USER_NO";
	public static final String UPDATEDATE_COLUMN				= "UPDATEDATE";
	public static final String UPDATETIME_COLUMN				= "UPDATETIME";
	public static final String RIS_HAKKO_TERMINAL_COLUMN		= "RIS_HAKKO_TERMINAL";
	public static final String RIS_HAKKO_USER_COLUMN			= "RIS_HAKKO_USER";
	public static final String HIS_HAKKO_DATE_COLUMN			= "HIS_HAKKO_DATE";
	public static final String HIS_HAKKO_TERMINAL_COLUMN		= "HIS_HAKKO_TERMINAL";
	public static final String HIS_HAKKO_USER_COLUMN			= "HIS_HAKKO_USER";
	public static final String HIS_UPDATE_DATE_COLUMN			= "HIS_UPDATE_DATE";
	public static final String RI_ORDER_FLG_COLUMN				= "RI_ORDER_FLG";
	public static final String SATUEI_PLACE_COLUMN				= "SATUEI_PLACE";
	public static final String YOTEIKAIKEI_FLG_COLUMN			= "YOTEIKAIKEI_FLG";
	public static final String ISITATIAI_FLG_COLUMN			= "ISITATIAI_FLG";
	public static final String DENPYO_INSATU_FLG_COLUMN		= "DENPYO_INSATU_FLG";
	public static final String PORTABLE_FLG_COLUMN				= "PORTABLE_FLG";
	public static final String KANJA_SYOKAI_FLG_COLUMN			= "KANJA_SYOKAI_FLG";
	public static final String SIKYU_FLG_COLUMN				= "SIKYU_FLG";
	public static final String SEISAN_DATE_COLUMN				= "SEISAN_DATE";
	public static final String SEISAN_FLG_COLUMN				= "SEISAN_FLG";
	public static final String SEISAN_KBN_DATE_COLUMN			= "SEISAN_KBN_DATE";
	public static final String DOUISHO_FLG_COLUMN 				= "DOUISHO_FLG";
	public static final String ADDENDUM01_COLUMN 				= "ADDENDUM01";
	public static final String ADDENDUM02_COLUMN 				= "ADDENDUM02";
	public static final String ADDENDUM03_COLUMN 				= "ADDENDUM03";
	public static final String ADDENDUM04_COLUMN 				= "ADDENDUM04";
	public static final String ADDENDUM05_COLUMN 				= "ADDENDUM05";
	public static final String ADDENDUM06_COLUMN 				= "ADDENDUM06";
	public static final String ADDENDUM07_COLUMN 				= "ADDENDUM07";
	public static final String ADDENDUM08_COLUMN 				= "ADDENDUM08";
	public static final String ADDENDUM09_COLUMN 				= "ADDENDUM09";
	public static final String ADDENDUM10_COLUMN 				= "ADDENDUM10";
	public static final String ADDENDUM11_COLUMN 				= "ADDENDUM11";
	public static final String ADDENDUM12_COLUMN 				= "ADDENDUM12";
	public static final String ADDENDUM13_COLUMN 				= "ADDENDUM13";
	public static final String ADDENDUM14_COLUMN 				= "ADDENDUM14";
	public static final String ADDENDUM15_COLUMN 				= "ADDENDUM15";
	public static final String ADDENDUM16_COLUMN 				= "ADDENDUM16";
	public static final String ADDENDUM17_COLUMN 				= "ADDENDUM17";
	public static final String ADDENDUM18_COLUMN 				= "ADDENDUM18";
	public static final String ADDENDUM19_COLUMN 				= "ADDENDUM19";
	public static final String ADDENDUM20_COLUMN 				= "ADDENDUM20";
	public static final String SHEMAURL_COLUMN					= "SHEMAURL";
	public static final String KENZOUKINKYUU_FLG_COLUMN		= "KENZOUKINKYUU_FLG";
	// 2011.11.25 Add NSK_M.Ochiai Start OMH-1-2
	public static final String EXAM_TIMING_COLUMN				= "EXAM_TIMING";
	// 2011.11.25 Add NSK_M.Ochiai End
	//
	public static final String ADDENDUM_COLUMN 				= "ADDENDUM";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisExtendOrderInfoTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
		this.tableNameStr = TableNameStr;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID"></param>
	/// <returns></returns>
	private String CreateSelectSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RIS_ID_COLUMN, risID,true,SignType.Equal);

		logger.debug("end");

		return this.GetSelectSQL();
	}


	/// <summary>
	/// 拡張オーダ情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件(患者ID)</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchExtendOrderInfo( Connection con, String risIDStr ) throws Exception
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if( con != null && risIDStr != null )
		{
			String sql = CreateSelectSQL(risIDStr);
			dt = Select( con,  sql, null);
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/*
	/// <summary>
	/// 拡張オーダ情報を取得する(conとtransを引数にしないVer)
	/// </summary>
	/// <param name="param">検索条件(RIS_ID)</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchExtendOrderInfo(String risIDStr )
	{
		Connection con = null;
		Transaction trans = null;

		con = DBConnectionFactory.GetInstance().GetRisDBConnection();
		trans = con.BeginTransaction();

		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		try
		{
			if( con != null && trans != null && risIDStr != null )
			{
				String sql = CreateSelectSQL(risIDStr);
				dt = Select( con, trans, sql );
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
	/// オーダー部位情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象オーダー部位のRisID</param>
	/// <returns>判定</returns>
	public boolean DeleteExtendOrderData(Connection con, Transaction trans, String risID)
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
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RIS_ID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

	/// <summary>
	///	 ステータスの取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">risID</param>
	/// <returns>ステータス名</returns>
	public ExtendOrderInfoBean GetExtendOrderInfoBean(Connection con, String risID) throws Exception
	{
		// parameters
		ExtendOrderInfoBean extendOrderInfoBean = new ExtendOrderInfoBean();

		// begin log
		logger.debug("begin");

		if (con != null && risID != null)
		{
			ArrayList<Object> arglist = new ArrayList<Object>();
			String cmd = CreateSelectCommandSQL(risID, arglist);
			DataTable dt = Select(con, cmd, arglist);

			if (dt != null && dt.getRowCount() == 1)
			{
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.RIS_ID_COLUMN) != null)
				{
					extendOrderInfoBean.SetRisID(dt.getRows().get(0).get(RIS_ID_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ORDER_DATE_COLUMN) != null)
				{
					extendOrderInfoBean.SetOrderDate(dt.getRows().get(0).get(ORDER_DATE_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.USER_NO_COLUMN) != null)
				{
					extendOrderInfoBean.SetUserNo(dt.getRows().get(0).get(USER_NO_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.UPDATEDATE_COLUMN) != null)
				{
					extendOrderInfoBean.SetUpdatedate(dt.getRows().get(0).get(UPDATEDATE_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.UPDATETIME_COLUMN) != null)
				{
					extendOrderInfoBean.SetUpdatetime(dt.getRows().get(0).get(UPDATETIME_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.RIS_HAKKO_TERMINAL_COLUMN) != null)
				{
					extendOrderInfoBean.SetRisHakkoTerminal(dt.getRows().get(0).get(RIS_HAKKO_TERMINAL_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.RIS_HAKKO_USER_COLUMN) != null)
				{
					extendOrderInfoBean.SetRisHakkoUser(dt.getRows().get(0).get(RIS_HAKKO_USER_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.HIS_HAKKO_DATE_COLUMN) != null)
				{
					extendOrderInfoBean.SetHisHakkoDate(dt.getRows().get(0).get(HIS_HAKKO_DATE_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.HIS_HAKKO_TERMINAL_COLUMN) != null)
				{
					extendOrderInfoBean.SetHisHakkoTerminal(dt.getRows().get(0).get(HIS_HAKKO_TERMINAL_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.HIS_HAKKO_USER_COLUMN) != null)
				{
					extendOrderInfoBean.SetHisHakkoUser(dt.getRows().get(0).get(HIS_HAKKO_USER_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.HIS_UPDATE_DATE_COLUMN) != null)
				{
					extendOrderInfoBean.SetHisUpdateDate(dt.getRows().get(0).get(HIS_UPDATE_DATE_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.RI_ORDER_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetRiOrderFlg(dt.getRows().get(0).get(RI_ORDER_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.SATUEI_PLACE_COLUMN) != null)
				{
					extendOrderInfoBean.SetSatueiPlace(dt.getRows().get(0).get(SATUEI_PLACE_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.YOTEIKAIKEI_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetYoteiKaikeiFlg(dt.getRows().get(0).get(YOTEIKAIKEI_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ISITATIAI_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetIsitatiaiFlg(dt.getRows().get(0).get(ISITATIAI_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.DENPYO_INSATU_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetDenpyoInsatuFlg(dt.getRows().get(0).get(DENPYO_INSATU_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.PORTABLE_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetPortableFlg(dt.getRows().get(0).get(PORTABLE_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.KANJA_SYOKAI_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetKanjaSyokaiFlg(dt.getRows().get(0).get(KANJA_SYOKAI_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.SIKYU_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetSikyuFlg(dt.getRows().get(0).get(SIKYU_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.SEISAN_DATE_COLUMN) != null)
				{
					extendOrderInfoBean.SetSeisanDate(dt.getRows().get(0).get(SEISAN_DATE_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.SEISAN_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetSeisanFlg(dt.getRows().get(0).get(SEISAN_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.SEISAN_KBN_DATE_COLUMN) != null)
				{
					extendOrderInfoBean.SetSeisanKbnDate(dt.getRows().get(0).get(SEISAN_KBN_DATE_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.DOUISHO_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetDouishoFlg(dt.getRows().get(0).get(DOUISHO_FLG_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM01_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum01(dt.getRows().get(0).get(ADDENDUM01_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM02_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum02(dt.getRows().get(0).get(ADDENDUM02_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM03_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum03(dt.getRows().get(0).get(ADDENDUM03_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM04_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum04(dt.getRows().get(0).get(ADDENDUM04_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM05_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum05(dt.getRows().get(0).get(ADDENDUM05_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM06_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum06(dt.getRows().get(0).get(ADDENDUM06_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM07_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum07(dt.getRows().get(0).get(ADDENDUM07_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM08_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum08(dt.getRows().get(0).get(ADDENDUM08_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM09_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum09(dt.getRows().get(0).get(ADDENDUM09_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM10_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum10(dt.getRows().get(0).get(ADDENDUM10_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM11_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum11(dt.getRows().get(0).get(ADDENDUM11_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM12_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum12(dt.getRows().get(0).get(ADDENDUM12_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM13_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum13(dt.getRows().get(0).get(ADDENDUM13_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM14_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum14(dt.getRows().get(0).get(ADDENDUM14_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM15_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum15(dt.getRows().get(0).get(ADDENDUM15_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM16_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum16(dt.getRows().get(0).get(ADDENDUM16_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM17_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum17(dt.getRows().get(0).get(ADDENDUM17_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM18_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum18(dt.getRows().get(0).get(ADDENDUM18_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM19_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum19(dt.getRows().get(0).get(ADDENDUM19_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.ADDENDUM20_COLUMN) != null)
				{
					extendOrderInfoBean.SetAddendum20(dt.getRows().get(0).get(ADDENDUM20_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.SHEMAURL_COLUMN) != null)
				{
					extendOrderInfoBean.SetShemaurl(dt.getRows().get(0).get(SHEMAURL_COLUMN).toString());
				}
				if (dt.getRows().get(0).get(RisExtendOrderInfoTbl.KENZOUKINKYUU_FLG_COLUMN) != null)
				{
					extendOrderInfoBean.SetKenzoukinkyuuFlg(dt.getRows().get(0).get(KENZOUKINKYUU_FLG_COLUMN).toString());
				}
//				// 2011.11.25 Add NSK_M.Ochiai Start OMH-1-2
//				if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
//				{
//					if (RisExtendOrderInfoTbl.EXAM_TIMING_COLUMN) != null)
//					{
//						extendOrderInfoBean.SetExamTiming(dt.getRows().get(0).get(EXAM_TIMING_COLUMN).toString());
//					}
//				}
//				// 2011.11.25 Add NSK_M.Ochiai End
			}
		}

		// end log
		logger.debug("end");

		return extendOrderInfoBean;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectCommandSQL(String risID, ArrayList<Object> arglist)
	{
		logger.debug("begin");

		String cmd = "";

		this.keys.clear();
		this.inList.clear();

		//RISID
		if (risID.length() > 0)
		{
			this.AddColSetValue(RIS_ID_COLUMN, "?", true, SignType.Equal);
			arglist.add(risID);
			//Parameter oraParam = new Parameter(RIS_ID_COLUMN, risID);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(RIS_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, risID, ParameterDirection.Input);
		}

		cmd = this.GetSelectColmunSQL(" * ", TABLE_NAME);

		logger.debug("end");

		return cmd;
	}

	/*
	/// <summary>
	/// 拡張オーダ情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="orderInfoBean">オーダ情報</param>
	/// <returns></returns>
	public boolean UpdateExtendOrderInfo(Connection con, Transaction trans, ExtendOrderInfoBean extendOrderInfoBean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && extendOrderInfoBean != null)
		{
			// ORDERMAINTABLE 更新
			retBool = Update(con, trans, CreateUpdateExtendOrderDataSQL(extendOrderInfoBean));
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="bean">拡張オーダ情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateExtendOrderDataSQL(ExtendOrderInfoBean bean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN, bean.GetRisID(), true, SignType.Equal);
		this.AddColValue(RI_ORDER_FLG_COLUMN, bean.GetRiOrderFlg());
		this.AddColValue(SIKYU_FLG_COLUMN, bean.GetSikyuFlg());
		this.AddColValue(ISITATIAI_FLG_COLUMN, bean.GetIsitatiaiFlg());
		this.AddColValue(PORTABLE_FLG_COLUMN, bean.GetPortableFlg());

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// 拡張オーダ情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="extendOrderInfoBean">拡張オーダ情報</param>
	/// <returns></returns>
	public boolean InsertNewExtendOrderInfo(Connection con, Transaction trans, ExtendOrderInfoBean extendOrderInfoBean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && extendOrderInfoBean != null)
		{
			// EXTENDORDERINFO 更新
			String sqlStr = CreateNewInsertSQL(extendOrderInfoBean);
			retBool = Insert(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Insert文作成
	/// </summary>
	/// <param name="bean">拡張オーダ情報</param>
	/// <returns>判定</returns>
	private String CreateNewInsertSQL(ExtendOrderInfoBean bean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN, bean.GetRisID(), true);
		//
		this.AddColValue(ORDER_DATE_COLUMN,				bean.GetOrderTimestamp());
		this.AddColValue(RIS_HAKKO_TERMINAL_COLUMN,		bean.GetRisHakkoTerminal());
		this.AddColValue(RIS_HAKKO_USER_COLUMN,			bean.GetRisHakkoUser());
		this.AddColValue(HIS_HAKKO_DATE_COLUMN,			bean.GetHisHakkoTimestamp());
		this.AddColValue(RI_ORDER_FLG_COLUMN,			bean.GetRiOrderFlg());
		this.AddColValue(SATUEI_PLACE_COLUMN,			bean.GetSatueiPlace());
		this.AddColValue(YOTEIKAIKEI_FLG_COLUMN,		bean.GetYoteiKaikeiFlg());
		this.AddColValue(ISITATIAI_FLG_COLUMN,			bean.GetIsitatiaiFlg());
		this.AddColValue(DENPYO_INSATU_FLG_COLUMN,		bean.GetDenpyoInsatuFlg());
		this.AddColValue(PORTABLE_FLG_COLUMN,			bean.GetPortableFlg());
		this.AddColValue(KANJA_SYOKAI_FLG_COLUMN,		bean.GetKanjaSyokaiFlg());
		this.AddColValue(SEISAN_FLG_COLUMN,				bean.GetSeisanFlg());

		logger.debug("end");

		return this.GetInsertSQL();
	}
	*/

	/*
	/// <summary>
	///	帳票用-拡張オーダ情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintExtendOrderDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
	//	private String CreateSelectSQL(OrderSearchParameter param)
	//	{
	//		logger.debug("begin");
	//
	//		// parameters
	//	StringBuilder sqlStrBuild = new StringBuilder("");

	//		// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//		sqlStrBuild.append(" SELECT /*+ INDEX(EO) INDEX(OM) INDEX(EM) */ EO.* FROM ");
	//		// 元の処理
	//		//sqlStrBuild.append(" SELECT /*+ RULE */ EO.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT EO.* FROM ");			//

	//		// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " EO");
	//	sqlStrBuild.append(", ORDERMAINTABLE OM ");
	//	// 2011.06.24 Add K.Shinohara Start A0060
	//	sqlStrBuild.append(", EXMAINTABLE EM ");
	//	// 2011.06.24 Add K.Shinohara End
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID ");
	//	CommonUtil.AddPrintWhereString(sqlStrBuild, param);

	//	logger.debug("end");

	//	return sqlStrBuild.toString();
	//}
}
