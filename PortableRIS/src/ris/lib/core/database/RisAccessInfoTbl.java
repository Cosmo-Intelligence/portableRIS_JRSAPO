package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.bean.AccessInfoBean;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;


/// <summary>
/// 排他テーブルの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.04	: 112478 / A.Kobayashi	: original
/// V2.04.00	: 2011.02.16	: 999999 / T.Nishikubo	: KANRO-R-27
///
/// </summary>
public class RisAccessInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "ACCESSINFO";
	public static final String TABLE_CAPTION	= "排他管理情報";
	// 2010.10.28 Add K.Shinohara
	private static final String TABLE_NAME_REFERENCE	= "ACCESSREFERENCEINFO";

	// カラム定義
	public static final String ID_COLUMN				= "ID";
	public static final String APPID_COLUMN			= "APPID";
	public static final String IPADDRESS_COLUMN		= "IPADDRESS";
	public static final String ACCESSMODE_COLUMN		= "ACCESSMODE";
	public static final String ENTRYTIME_COLUMN		= "ENTRYTIME";

    public static final String KANJA_ID_COLUMN         = "KANJA_ID";
    public static final String KENSA_DATE_COLUMN       = "KENSA_DATE";
    public static final String RIS_ID_COLUMN           = "RIS_ID";

	/// <summary>
	/// コンストラクタ
	/// </summary>
    public RisAccessInfoTbl()
	{
		this.tableNameStr = TABLE_NAME;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// 排他情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RisID</param>
	/// <returns></returns>
	public DataRow GetCheckoutData(Connection con, String risID) throws Exception
	{
		// parameters
		DataRow row = null;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && risID.length() > 0)
		{
			String sqlStr = CreateSelectSQL(risID);
			DataTable dt = Select(con, sqlStr, null);
			if (dt != null && dt.getRowCount() > 0)
			{
				row = dt.getRows().get(0);
			}
		}

		// end log
		logger.debug("end");

		return row;
	}

    /*
    /// <summary>
	/// チェックアウト情報を取得する
	/// </summary>
	/// <remarks></remarks>
    /// <param name="kanjaIDStr">患者ID</param>
    /// <param name="kensaDateStr">検査日</param>
    /// <param name="risIdStr">対象外のRIS_ID（自オーダ）</param>
    /// <returns>IPアドレス</returns>
    public String GetCheckoutData(Connection con, Transaction trans, String kanjaIDStr, String kensaDateStr,String risIDStr)
    {
        // parameters
        String retStr = "";
        DataTable dt = null;

        // begin log
        logger.debug("begin");

        if (con != null && trans != null
            && kanjaIDStr != null && kanjaIDStr.length() > 0
            && kensaDateStr != null && kensaDateStr.length() == 8
            && risIDStr != null && risIDStr.length() > 0)
        {
            String sqlStr = CreateSelectSQL(kanjaIDStr, kensaDateStr, risIDStr);
            dt = Select(con, trans, sqlStr);

            if (dt != null && dt.Rows.Count > 0)
            {
                retStr = dt.Rows[0][IPADDRESS_COLUMN].toString();
            }
        }

        // end log
        logger.debug("end");

		return retStr;
	}
	*/

    /*
	// 2010.09.09 Add K.Shinohara Start
	/// <summary>
	/// チェックアウト情報を取得する(オーダキャンセル用)
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="accessBean">AccessInfoBean</param>
	/// <returns></returns>
	public DataRow GetCheckoutData_OrderCancel(Connection con, Transaction trans, AccessInfoBean bean, String ownerAppID)
	{
		// parameters
		DataRow row = null;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && bean != null)
		{
			// 2010.10.28 Mod K.Shinohara Start
			String sqlStr = CreateSelectSQL(bean.GetID());
			//String sqlStr = CreateSelectSQL_OrderCancel(bean, ownerAppID);
			// 2010.10.28 Mod K.Shinohara End
			DataTable dt = Select(con, trans, sqlStr);
			if (dt != null && dt.Rows.Count > 0)
			{
				row = dt.Rows[0];
			}
			// 2010.10.28 Add K.Shinohara Start
			else
			{
				sqlStr = CreateSelectSQL_Reference(bean, ownerAppID);
				dt = Select(con, trans, sqlStr);
				if (dt != null && dt.Rows.Count > 0)
				{
					row = dt.Rows[0];
				}
			}
			// 2010.10.28 Add K.Shinohara End
		}

		// end log
		logger.debug("end");

		return row;
	}
	// 2010.09.09 Add K.Shinohara End
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RisID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(ID_COLUMN, risID, true);
		// 2010.10.28 Del K.Shinohara
		//// 2010.09.09 Add K.Shinohara Start
		//// 常に更新モードの情報を返す
		//this.AddColValue(ACCESSMODE_COLUMN, CommonString.ROCK_ACCESSMODE_MOD, true);
		//// 2010.09.09 Add K.Shinohara End


		logger.debug("end");

		return this.GetSelectSQL();
	}

    /*
    /// <summary>
    /// SELECT用のSQL文を作成する
    /// </summary>
    /// <remarks></remarks>
    /// <param name="kanjaIDStr">患者ID</param>
    /// <param name="kensaDateStr">日付</param>
    /// <returns>SELECT用のSQL文</returns>
    private String CreateSelectSQL(String kanjaIDStr, String kensaDateStr, String risIDStr)
    {
        logger.debug("begin");

        StringBuilder strBuild = new StringBuilder();

		strBuild.append("select EX.RIS_ID, AC.IPADDRESS from ACCESSINFO AC, EXMAINTABLE EX where AC.ID = EX.RIS_ID");
        strBuild.append(" and EX." + KANJA_ID_COLUMN + " = '" + kanjaIDStr + "'");
        strBuild.append(" and EX." + KENSA_DATE_COLUMN + " = '" + kensaDateStr + "'");
        strBuild.append(" and EX." + RIS_ID_COLUMN + " != '" + risIDStr + "'");
		// 2010.09.15 Del K.Shinohara
        //strBuild.append(" and (AC." + APPID_COLUMN + " = '" + CommonString.MENUBUTTON_ID_G2 + "' or AC." + APPID_COLUMN + " = '" + CommonString.MENUBUTTON_ID_G2 + "')");

        return strBuild.toString();
	}
	*/

    /*
	// 2010.10.28 Del K.Shinohara
	// 2010.09.09 Add K.Shinohara Start
	///// <summary>
	///// SELECT用のSQL文を作成する(オーダキャンセル用)
	///// </summary>
	///// <remarks></remarks>
	///// <param name="risID">RisID</param>
	///// <returns>SELECT用のSQL文</returns>
	//private String CreateSelectSQL_OrderCancel(AccessInfoBean bean, String ownerAppID)
	//{
	//    logger.debug("begin");

	//    StringBuilder strBuild = new StringBuilder();

	//    strBuild.append(" select * from " + TABLE_NAME);
	//    strBuild.append(" where " + ID_COLUMN + " = '" + bean.GetID() + "'");
	//    strBuild.append(" and not (" + APPID_COLUMN + " = '" + ownerAppID + "'");
	//    strBuild.append(" and " + IPADDRESS_COLUMN + " = '" + bean.GetIpAddress() + "')");

	//    logger.debug("end");

	//    return strBuild.toString();
	//}
	// 2010.09.09 Add K.Shinohara End
	*/

    /*
	// 2010.10.28 Add K.Shinohara Start
	/// <summary>
	/// SELECT用のSQL文を作成する(オーダキャンセル用)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RisID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL_Reference(AccessInfoBean bean, String ownerAppID)
	{
		logger.debug("begin");

		StringBuilder strBuild = new StringBuilder();

		strBuild.append(" select * from " + TABLE_NAME_REFERENCE);
		strBuild.append(" where " + ID_COLUMN + " = '" + bean.GetID() + "'");
		strBuild.append(" and not (" + APPID_COLUMN + " = '" + ownerAppID + "'");
		strBuild.append(" and " + IPADDRESS_COLUMN + " = '" + bean.GetIpAddress() + "')");

		logger.debug("end");

		return strBuild.toString();
	}
	// 2010.10.28 Add K.Shinohara End
	*/

	/// <summary>
	/// 編集対象のデータをロックする
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">排他情報</param>
	/// <returns>チェックアウトID</returns>
	public String Checkout(Connection con, AccessInfoBean bean) throws Exception
	{
		// parameters
		String retStr = "";

		// begin log
		logger.debug("begin");

		if (con != null && bean != null && bean.GetID().length() > 0)
		{
			boolean retFlg = Insert(con, CreateInsertSQL(bean), null);
			if (retFlg)
			{
				retStr = bean.GetID();
			}
		}

		// end log
		logger.debug("end");

		return retStr;
	}

	// 2010.10.28 Add K.Shinohara Start
	/// <summary>
	/// 参照モードチェックアウトを行う
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="bean">排他情報</param>
	/// <returns>チェックアウトID</returns>
	public String CheckoutReference(Connection con, AccessInfoBean bean) throws Exception
	{
		// parameters
		String retStr = "";

		// begin log
		logger.debug("begin");

		if (con != null && bean != null && bean.GetID().length() > 0)
		{
			boolean retFlg = Insert(con, CreateInsertReferenceSQL(bean), null);
			if (retFlg)
			{
				retStr = bean.GetID();
			}
		}

		// end log
		logger.debug("end");

		return retStr;
	}
	// 2010.10.28 Add K.Shinohara End

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">INSERT対象の排他情報</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL(AccessInfoBean bean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(ID_COLUMN, bean.GetID(), true);
		this.AddColValue(APPID_COLUMN, bean.GetAppID());
		this.AddColValue(IPADDRESS_COLUMN, bean.GetIpAddress());
		this.AddColValue(ACCESSMODE_COLUMN, bean.GetAccessMode());
		this.AddColSetValue(ENTRYTIME_COLUMN, SysDateTbl.SYSDATE_COLUMN);

		logger.debug("end");

		return this.GetInsertSQL();
	}

	// 2010.10.28 Add K.Shinohara Start
	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">INSERT対象の排他情報</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertReferenceSQL(AccessInfoBean bean)
	{
		logger.debug("begin");

		StringBuilder strBuild = new StringBuilder();

		strBuild.append(" insert into " + TABLE_NAME_REFERENCE);
		strBuild.append(" (" + ID_COLUMN + "," + APPID_COLUMN + "," + IPADDRESS_COLUMN + "," + ENTRYTIME_COLUMN + ") values");
		strBuild.append(" ('" + bean.GetID() + "','" + bean.GetAppID() + "','" + bean.GetIpAddress() + "'," + SysDateTbl.SYSDATE_COLUMN + ")");

		logger.debug("end");

		return strBuild.toString();
	}
	// 2010.10.28 Add K.Shinohara End

	/// <summary>
	/// 編集対象のデータのロックを解除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">対象RISID</param>
	/// <returns>チェックアウトID</returns>
	public boolean UnCheckout(Connection con, String risID) throws Exception
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && risID.length() > 0)
		{
			String sqlStr = CreateDeleteSQL(risID);
			retBool = Delete(con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return retBool;
	}


	/// <summary>
	/// 編集対象のデータのロックを解除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="ipAddress">対象ipアドレス</param>
	/// <param name="appID">対象アプリID</param>
	/// <returns>チェックアウトID</returns>
	public boolean UnCheckoutByIpAndAppID(Connection con, String ipAddress, String appID) throws Exception
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && ipAddress != null && appID != null &&  ipAddress.length() > 0 && appID.length() > 0)
		{
			String sqlStr = CreateDeleteSQL(ipAddress,appID);
			retBool = Delete(con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
    /*
	// 2010.09.09 Add K.Shinohara Start
	/// <summary>
	/// 編集対象のデータのロックを解除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">アクセス情報</param>
	/// <returns>チェックアウトID</returns>
	public boolean UnCheckout(Connection con, Transaction trans, AccessInfoBean bean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && bean != null)
		{
			String sqlStr = CreateDeleteSQL(bean);
			retBool = Delete(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	// 2010.09.09 Add K.Shinohara End
	*/

	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="checkoutIDStr">アンチェックアウト対象のチェックアウトID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String checkoutIDStr)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(ID_COLUMN, checkoutIDStr, true);
		// 2010.10.28 Del K.Shinohara
		//// 2010.09.09 Add K.Shinohara Start
		//// 常に更新モードの情報を削除する
		//this.AddColValue(ACCESSMODE_COLUMN, CommonString.ROCK_ACCESSMODE_MOD, true);
		//// 2010.09.09 Add K.Shinohara End


		logger.debug("end");

		return this.GetDeleteSQL();
	}

	// 2019.10.03 Add Cosmo Start 排他ロック対応
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="checkoutIDStr">アンチェックアウト対象のIPアドレス</param>
	/// <param name="checkoutIDStr">アンチェックアウト対象のアプリID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String checkoutIpStr,String checkoutIAppIDStr)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(IPADDRESS_COLUMN, checkoutIpStr, true);
		this.AddColValue(APPID_COLUMN, checkoutIAppIDStr, true);


		logger.debug("end");

		return this.GetDeleteSQL();
	}
	// 2019.10.03 Add Cosmo End 排他ロック対応
    /*
	// 2010.09.09 Add K.Shinohara Start
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="checkoutIDStr">アンチェックアウト対象のチェックアウトID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(AccessInfoBean bean)
	{
		logger.debug("begin");

		// 2010.10.28 Mod K.Shinohara Start
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(" delete " + TABLE_NAME_REFERENCE);
		// 2011.02.24 Mod K.Shinohara Start
		// RIS_ID指定が無い場合の対応
		if (bean.GetID() != "")
		{
			strBuild.append(" where "	+ ID_COLUMN			+ " = '" + bean.GetID()			+ "'");
			strBuild.append(" and "		+ APPID_COLUMN		+ " = '" + bean.GetAppID()		+ "'");
			strBuild.append(" and "		+ IPADDRESS_COLUMN	+ " = '" + bean.GetIpAddress()	+ "'");
		}
		else
		{
			strBuild.append(" where "	+ APPID_COLUMN		+ " = '" + bean.GetAppID()		+ "'");
			strBuild.append(" and "		+ IPADDRESS_COLUMN	+ " = '" + bean.GetIpAddress()	+ "'");
		}
		//strBuild.append(" where "	+ ID_COLUMN			+ " = '" + bean.GetID()			+ "'");
		//strBuild.append(" and "		+ APPID_COLUMN		+ " = '" + bean.GetAppID()		+ "'");
		//strBuild.append(" and "		+ IPADDRESS_COLUMN	+ " = '" + bean.GetIpAddress()	+ "'");
		// 2011.02.24 Mod K.Shinohara End

		logger.debug("end");

		return strBuild.toString();
		// コメント
		//this.keys.clear();
		//this.AddColValue(ID_COLUMN, bean.GetID(), true);
		//this.AddColValue(APPID_COLUMN, bean.GetAppID(), true);
		//this.AddColValue(IPADDRESS_COLUMN, bean.GetIpAddress(), true);
		//this.AddColValue(ACCESSMODE_COLUMN, bean.GetAccessMode(), true);

		//logger.debug("end");

		//return this.GetDeleteSQL();

		// 2010.10.28 Mod K.Shinohara End
	}
	// 2010.09.09 Add K.Shinohara End
	*/

    /*
	/// <summary>
	/// 当該IPアドレスでロックしている全データのロックを解除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="ipAddress">IPアドレス</param>
	public void UncheckoutAll(Connection con, Transaction trans, String ipAddress)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && ipAddress != null && ipAddress.length() > 0)
		{
			String sqlStr = CreateIPAddressDeleteSQL(ipAddress);
			retBool = ForceDelete(con, trans, sqlStr);

			// 2010.10.28 Add K.Shinohara Start
			sqlStr = CreateIPAddressDeleteReferenceSQL(ipAddress);
			retBool = ForceDelete(con, trans, sqlStr);
			// 2010.10.28 Add K.Shinohara End
		}

		// end log
		logger.debug("end");
	}
	*/

    /*
	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	/// <summary>
	/// 掲示板全データのチェックアウト取消し
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="ipAddress">IPアドレス</param>
	public void UncheckoutAllMsgData(Connection con, Transaction trans, String ipAddress)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && ipAddress != null && ipAddress.length() > 0)
		{
			String sqlStr = CreateMsgDeleteSQL(ipAddress);
			retBool = ForceDelete(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");
	}
	*/

    /*
	/// <summary>
	/// IPアドレスで掲示板データのアクセス情報をDELETEするSQL文を作成する
	/// </summary>
	/// <param name="ipAddress">IPアドレス</param>
	/// <remarks></remarks>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateMsgDeleteSQL(String ipAddress)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(ID_COLUMN, CommonString.COMMSG_PREFIX_STRING + "%", true, SignType.Like);
		this.AddColValue(IPADDRESS_COLUMN, ipAddress, true);

		logger.debug("end");

		// 2011.03.01 Mod T.Nishikubo Start
		String exCondStr = " OR " + ID_COLUMN + SignType.Equal + " '" + CommonString.COMMSG_SHORT_PREFIX_STRING + ipAddress + "'";
		return this.GetDeleteSQL() + exCondStr;
		//return this.GetDeleteSQL();
		// 2011.03.01 Mod T.Nishikubo End
	}
	// 2011.02.16 Add T.Nishikubo End
	*/

    /*
	/// <summary>
	/// IPアドレスでDELETEするSQL文を作成する
	/// </summary>
	/// <param name="ipAddress">IPアドレス</param>
	/// <remarks></remarks>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateIPAddressDeleteSQL(String ipAddress)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(IPADDRESS_COLUMN, ipAddress, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

    /*
	// 2010.10.28 Add K.Shinohara Start
	/// <summary>
	/// IPアドレスでDELETEするSQL文を作成する
	/// </summary>
	/// <param name="ipAddress">IPアドレス</param>
	/// <remarks></remarks>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateIPAddressDeleteReferenceSQL(String ipAddress)
	{
		logger.debug("begin");

		StringBuilder strBuild = new StringBuilder();

		strBuild.append(" delete " + TABLE_NAME_REFERENCE);
		strBuild.append(" where " + IPADDRESS_COLUMN + " = '" + ipAddress + "'");

		logger.debug("end");

		return strBuild.toString();
	}
	// 2010.10.28 Add K.Shinohara End
	*/

    /*
	// 2011.02.28 Add T.Nishikubo Start
	/// <summary>
	/// 編集対象のデータのロックを解除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">アクセス情報</param>
	/// <returns>チェックアウトID</returns>
	public boolean UnCheckoutTerminalMemoData(Connection con, Transaction trans, AccessInfoBean bean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && bean != null && bean.GetID().length() > 0 && bean.GetIpAddress().length() > 0)
		{
			String sqlStr = CreateDeleteTerminalMemoSQL(bean);
			retBool = Delete(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

    /*
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">アクセス情報</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteTerminalMemoSQL(AccessInfoBean bean)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(ID_COLUMN, bean.GetID(), true);
		this.AddColValue(IPADDRESS_COLUMN, bean.GetIpAddress(), true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	// 2011.02.28 Add T.Nishikubo End
	*/
}
