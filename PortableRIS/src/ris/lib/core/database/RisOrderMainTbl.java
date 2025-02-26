package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.bean.OrderInfoBean;
import ris.portable.util.DataTable;

/// <summary>
///
/// オーダ情報テーブルの管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.10	: 112478 / A.Kobayashi	: original
/// V2.01.00    extends 2011.07.11    extends 999999 / NSK_H.Hashimoto extends NML-CAT1-002
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisOrderMainTbl extends BaseTbl
{
	// アクセステーブル名
	public  final String TABLE_NAME = "ORDERMAINTABLE";

	// カラム定義
	// ORDERMAINTABLE COLUMN
	public static final String RIS_ID_COLUMN				= "RIS_ID";
	public static final String SYSTEMKBN_COLUMN			= "SYSTEMKBN";
	public static final String STUDYINSTANCEUID_COLUMN		= "STUDYINSTANCEUID";
	public static final String ORDERNO_COLUMN				= "ORDERNO";
	public static final String ACCESSIONNO_COLUMN			= "ACCESSIONNO";
	public static final String KENSA_DATE_COLUMN			= "KENSA_DATE";
	public static final String KENSA_STARTTIME_COLUMN		= "KENSA_STARTTIME";
	public static final String KENSATYPE_ID_COLUMN			= "KENSATYPE_ID";
	public static final String KENSASITU_ID_COLUMN			= "KENSASITU_ID";
	public static final String KENSAKIKI_ID_COLUMN			= "KENSAKIKI_ID";
	public static final String SYOTISITU_FLG_COLUMN			= "SYOTISITU_FLG";
	public static final String KANJA_ID_COLUMN				= "KANJA_ID";
	public static final String KENSA_DATE_AGE_COLUMN		= "KENSA_DATE_AGE";
	public static final String DENPYO_NYUGAIKBN_COLUMN		= "DENPYO_NYUGAIKBN";
	public static final String DENPYO_BYOUTOU_ID_COLUMN	= "DENPYO_BYOUTOU_ID";
	public static final String DENPYO_BYOSITU_ID_COLUMN	= "DENPYO_BYOSITU_ID";
	public static final String IRAI_SECTION_ID_COLUMN		= "IRAI_SECTION_ID";
	public static final String IRAI_DOCTOR_NAME_COLUMN		= "IRAI_DOCTOR_NAME";
	public static final String IRAI_DOCTOR_NO_COLUMN		= "IRAI_DOCTOR_NO";
	public static final String ORDER_SECTION_ID_COLUMN		= "ORDER_SECTION_ID";
	public static final String IRAI_DOCTOR_RENRAKU_COLUMN	= "IRAI_DOCTOR_RENRAKU";
	public static final String DOKUEI_FLG_COLUMN			= "DOKUEI_FLG";

	// 2010.11.22 Add K.Shinohara Start
	// 追加定義（業務日誌に使用）
	public static final String DENPYO_BYOUTOU_NAME_COLUMN	= "DENPYO_BYOUTOU_NAME";
	public static final String DENPYO_BYOUTOU_RYAKUNAME_COLUMN	= "DENPYO_BYOUTOU_RYAKUNAME";
	// 2010.11.22 Add K.Shinohara End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisOrderMainTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
		this.tableNameStr = TABLE_NAME;
	}

	/// <summary>
	///	オーダ情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">risID</param>
	/// <returns>ステータス名</returns>
	public OrderInfoBean GetOrderInfoBean( Connection con, String risID ) throws Exception
	{
		// parameters
		OrderInfoBean orderInfoBean = new OrderInfoBean();

		// begin log
		logger.debug("begin");

		if( con != null && risID != null )
		{
			ArrayList<Object> arglist = new ArrayList<Object>();
			String cmd = CreateSelectSQL(risID, arglist);
			DataTable dt = Select(con, cmd, arglist);

			if (dt != null && dt.getRowCount() == 1)
			{
				orderInfoBean.SetRisID(dt.getRows().get(0).get(RIS_ID_COLUMN).toString());
				orderInfoBean.SetSystemKbn(dt.getRows().get(0).get(RisOrderMainTbl.SYSTEMKBN_COLUMN).toString());
				orderInfoBean.SetStudyInstanceUID(dt.getRows().get(0).get(RisOrderMainTbl.STUDYINSTANCEUID_COLUMN).toString());
				orderInfoBean.SetOrderNo(dt.getRows().get(0).get(RisOrderMainTbl.ORDERNO_COLUMN).toString());
				orderInfoBean.SetAccessionNo(dt.getRows().get(0).get(RisOrderMainTbl.ACCESSIONNO_COLUMN).toString());
				orderInfoBean.SetKensaDate(dt.getRows().get(0).get(RisOrderMainTbl.KENSA_DATE_COLUMN).toString());
				orderInfoBean.SetKensaStartTime(dt.getRows().get(0).get(RisOrderMainTbl.KENSA_STARTTIME_COLUMN).toString());
				orderInfoBean.SetKensatypeID(dt.getRows().get(0).get(RisOrderMainTbl.KENSATYPE_ID_COLUMN).toString());
				orderInfoBean.SetKensasituID(dt.getRows().get(0).get(RisOrderMainTbl.KENSASITU_ID_COLUMN).toString());
				orderInfoBean.SetKensakikiID(dt.getRows().get(0).get(RisOrderMainTbl.KENSAKIKI_ID_COLUMN).toString());
				orderInfoBean.SetSyotisituFlg(dt.getRows().get(0).get(RisOrderMainTbl.SYOTISITU_FLG_COLUMN).toString());
				orderInfoBean.SetKanjaID(dt.getRows().get(0).get(RisOrderMainTbl.KANJA_ID_COLUMN).toString());
				orderInfoBean.SetKensaDateAge(dt.getRows().get(0).get(RisOrderMainTbl.KENSA_DATE_AGE_COLUMN).toString());
				orderInfoBean.SetDenpyoNyugaiKbn(dt.getRows().get(0).get(RisOrderMainTbl.DENPYO_NYUGAIKBN_COLUMN).toString());
				orderInfoBean.SetDenpyoByoutouID(dt.getRows().get(0).get(RisOrderMainTbl.DENPYO_BYOUTOU_ID_COLUMN).toString());
				orderInfoBean.SetDenpyoByosituID(dt.getRows().get(0).get(RisOrderMainTbl.DENPYO_BYOSITU_ID_COLUMN).toString());
				orderInfoBean.SetIraiSectionID(dt.getRows().get(0).get(RisOrderMainTbl.IRAI_SECTION_ID_COLUMN).toString());
				orderInfoBean.SetIraiDoctorName(dt.getRows().get(0).get(RisOrderMainTbl.IRAI_DOCTOR_NAME_COLUMN).toString());
				orderInfoBean.SetIraiDoctorNo(dt.getRows().get(0).get(RisOrderMainTbl.IRAI_DOCTOR_NO_COLUMN).toString());
				orderInfoBean.SetOrderSectionID(dt.getRows().get(0).get(RisOrderMainTbl.ORDER_SECTION_ID_COLUMN).toString());
				orderInfoBean.SetIraiDoctorRenraku(dt.getRows().get(0).get(RisOrderMainTbl.IRAI_DOCTOR_RENRAKU_COLUMN).toString());
				orderInfoBean.SetDokueiFlg(dt.getRows().get(0).get(RisOrderMainTbl.DOKUEI_FLG_COLUMN).toString());
			}
		}

		// end log
		logger.debug("end");

		return orderInfoBean;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String risID, ArrayList<Object> arglist)
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
	/// 検査室情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <returns>判定</returns>
	public boolean UpdateRisExRoom( Connection con, Transaction trans, String risID, String exRoomID)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null )
		{
			// ExMainTable 更新
			retBool = Update( con, trans, CreateUpdateRisExRoomIDSQL(risID, false, exRoomID) );
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	// 2010.07.30 Add DD.Chinh Start
	/// <summary>
	/// 検査室、検査機器情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// /// <param name="exKikiID">検査機器情報</param>
	/// <returns>判定</returns>
	public boolean UpdateRisExamRoomMachine(Connection con, Transaction trans, String risID, String exRoomID, String exKikiID)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			// OrderMainTable 更新
			retBool = Update(con, trans, CreateUpdateExamRoomMachineIDSQL(risID, false, exRoomID, exKikiID));
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
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <param name="exKikiID">検査機器情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateExamRoomMachineIDSQL(String risID, boolean extend, String exRoomID, String exKikiID)
	{
		logger.debug("begin");

		if (!extend)
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
			this.AddColValue(KENSASITU_ID_COLUMN, exRoomID);
			this.AddColValue(KENSAKIKI_ID_COLUMND, exKikiID);
		}
		else
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	// 2010.07.30 Add DD.Chinh End
	*/

	/*
	/// <summary>
	/// オーダ情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="orderInfoBean">オーダ情報</param>
	/// <returns></returns>
	public boolean UpdateOrderInfo(Connection con, Transaction trans, OrderInfoBean orderInfoBean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && orderInfoBean != null)
		{
			// ORDERMAINTABLE 更新
			retBool = Update(con, trans, CreateUpdateOrderDataSQL(orderInfoBean));
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
	/// <summary>
	/// オーダ情報(KENSA_DATEのみ)を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="orderInfoBean">オーダ情報</param>
	/// <returns></returns>
	public boolean UpdateOrderInfoOnlyKensaDate(Connection con, OrderInfoBean orderInfoBean) throws Exception
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && orderInfoBean != null)
		{
			// ORDERMAINTABLE 更新
			retBool = Update(con, CreateUpdateOrderDataOnlyKensaDateSQL(orderInfoBean), null);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	// 2011.07.11 Add NSK_H.Hashimoto End

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisExRoomIDSQL( String risID, boolean extend, String exRoomID)
	{
		logger.debug("begin");

		if(!extend)
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
			this.AddColValue(KENSASITU_ID_COLUMN, exRoomID);
		}
		else
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="bean">オーダ情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateOrderDataSQL(OrderInfoBean bean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN,			bean.GetRisID(), true, SignType.Equal);
		this.AddColValue(SYSTEMKBN_COLUMN,		bean.GetSystemKbn());
		this.AddColValue(DOKUEI_FLG_COLUMN,		bean.GetDokueiFlg());
		this.AddColValue(SYOTISITU_FLG_COLUMN,	bean.GetSyotisituFlg());

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
	/// <summary>
	/// Update文作成(KENSA_DATEのみ)
	/// </summary>
	/// <param name="bean">オーダ情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateOrderDataOnlyKensaDateSQL(OrderInfoBean bean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN, bean.GetRisID(), true, SignType.Equal);
		this.AddColValue(KENSA_DATE_COLUMN, bean.GetKensaDate());

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	// 2011.07.11 Add NSK_H.Hashimoto End

	/*
	/// <summary>
	/// オーダ情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象オーダ情報のRisID</param>
	/// <returns>判定</returns>
	public boolean DeleteOrderMainData(Connection con, Transaction trans, String risID)
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

	/*
	/// <summary>
	/// オーダーメイン情報を登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="orderInfoBean">オーダメイン情報</param>
	/// <returns></returns>
	public boolean InsertNewOrderMainData(Connection con, Transaction trans, OrderInfoBean orderInfoBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && orderInfoBean != null && orderInfoBean.GetRisID().length() > 0)
		{
			String sqlStr = CreateNewInsertSQL(orderInfoBean);
			retFlg = Insert(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bui">INSERT対象の部位データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateNewInsertSQL(OrderInfoBean bean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN,					bean.GetRisID());
		//
		this.AddColValue(SYSTEMKBN_COLUMN,				bean.GetSystemKbn());
		this.AddColValue(STUDYINSTANCEUID_COLUMN,		bean.GetStudyInstanceUID());
		this.AddColValue(ORDERNO_COLUMN,				bean.GetOrderNo());
		this.AddColValue(ACCESSIONNO_COLUMN,			bean.GetAccessionNo());
		this.AddColValue(KENSA_DATE_COLUMN,				bean.GetKensaDate());
		if (bean.GetKensaStartTime().length() > 0)
		{
			this.AddColValue(KENSA_STARTTIME_COLUMN,	Integer.parseInt(bean.GetKensaStartTime()));
		}
		this.AddColValue(KENSATYPE_ID_COLUMN,			bean.GetKensatypeID());
		this.AddColValue(KENSASITU_ID_COLUMN,			bean.GetKensasituID());
		this.AddColValue(KENSAKIKI_ID_COLUMND,			bean.GetKensakikiID());
		this.AddColValue(SYOTISITU_FLG_COLUMN,			bean.GetSyotisituFlg());
		this.AddColValue(KANJA_ID_COLUMN,				bean.GetKanjaID());
		this.AddColValue(KENSA_DATE_AGE_COLUMN,			bean.GetKensaDateAge());
		this.AddColValue(DENPYO_NYUGAIKBN_COLUMN,		bean.GetDenpyoNyugaiKbn());
		this.AddColValue(DENPYO_BYOUTOU_ID_COLUMN,		bean.GetDenpyoByoutouID());
		this.AddColValue(IRAI_SECTION_ID_COLUMN,		bean.GetIraiSectionID());
		this.AddColValue(IRAI_DOCTOR_NAME_COLUMN,		bean.GetIraiDoctorName());
		this.AddColValue(IRAI_DOCTOR_NO_COLUMN,			bean.GetIraiDoctorNo());
		this.AddColValue(IRAI_DOCTOR_RENRAKU_COLUMN,	bean.GetIraiDoctorRenraku());
		this.AddColValue(DOKUEI_FLG_COLUMN,				bean.GetDokueiFlg());

		logger.debug("end");

		return this.GetInsertSQL();
	}
	*/

	/*
	/// <summary>
	///	帳票用-オーダ情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintOrderMainDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
			param.SetExecSql(sql);
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
	//{
	//	logger.debug("begin");

	//	// parameters
	//	StringBuilder sqlStrBuild = new StringBuilder("");

	//	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//	sqlStrBuild.append(" SELECT/*+ INDEX(OM) INDEX(EO) INDEX(EM) */ OM.* FROM ");
	//	// 元の処理
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ OM.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT OM.* FROM ");			//

	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " OM ");
	//	sqlStrBuild.append(", EXTENDORDERINFO EO ");
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
