package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.Configuration;
import ris.lib.core.util.UserSearchParameter;
import ris.portable.util.DataTable;

/// <summary>
///
/// RisUserSubViewテーブル管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.25	: 112478 / A.Kobayashi	: original
/// V2.04.00	: 2011.02.16	: 999999 / DD.Chinh   	: KANRO-R-27
///
/// </summary>
public class RisUserSubView extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "USERSUBVIEW";
	private  final String TABLE_CAPTION = "";
	private static String TableNameStr = Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String ID_COLUMN			= "ID";
	public static final String LOGINID_COLUMN		= "LOGINID";
	public static final String PASSWORD_COLUMN		= "PASSWORD";
	public static final String USERNAME_COLUMN		= "USERNAME";
	public static final String STAFFID_COLUMN		= "STAFFID";
	public static final String ATTRIBUTE_COLUMN	= "ATTRIBUTE";
	public static final String SHOWORDER_COLUMN	= "SHOWORDER";
	public static final String SYOKUIN_KBN_COLUMN	= "SYOKUIN_KBN";
	// 2010.09.21 Add K.Shinohara Start
	public static final String USERIDVALIDITYFLAG_COLUMN	= "USERIDVALIDITYFLAG";
	// 2010.09.21 Add K.Shinohara End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <remarks></remarks>
	public RisUserSubView()
	{
		this.infoCaption = TABLE_CAPTION;
		this.tableNameStr = TableNameStr;
	}

	/// <summary>
	/// ユーザ情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>ユーザ情報</returns>
	public DataTable GetUserSubView(Connection con, UserSearchParameter param) throws Exception
	{
		// parameters
		DataTable dt = null;

		// start log
		logger.debug("begin");

		if (con != null && param != null)
		{
			String sqlText = CreateSelectSQL(param);
			dt = Select(con, sqlText, null);
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// ユーザ情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>ユーザ情報</returns>
	public DataTable GetUserSubViewTeamUser(Connection con, Transaction trans, UserSearchParameter param)
	{
		// parameters
		DataTable dt = null;

		// start log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sqlText = CreateTeamSelectSQL(param);
			dt = Select(con, trans, sqlText);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	/*
	// 2011.02.16 Add DD.Chinh Start KANRO-R-27
	/// <summary>
	/// ユーザ情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="syokuinKbn">職員区分（空の場合は全て）</param>
	/// <returns>ユーザ情報</returns>
	public DataTable GetUserSubViewSyokuinKbn(Connection con, Transaction trans, String syokuinKbn)
	{
		// parameters
		DataTable dt = null;

		// start log
		logger.debug("begin");

		if (con != null && trans != null)
		{
			String sqlText = CreateSyokuinSelectSQL(syokuinKbn);
			dt = Select(con, trans, sqlText);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	// 2011.02.16 Add DD.Chinh End
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>ELECT用のSQL文</returns>
	private String CreateSelectSQL(UserSearchParameter param)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		try
		{
			if (param.GetID().length() > 0)
			{
				this.AddColValue(ID_COLUMN, Integer.parseInt(param.GetID()), true, SignType.Equal);
			}
		}
		catch (Exception e)
		{
		}
		// 2010.07.30 Add T.Ootsuka Start
		if (param.GetUserName().length() > 0)
		{
			this.AddColValue(USERNAME_COLUMN, param.GetUserName(), true, SignType.Equal);
		}
		// 2010.07.30 Add T.Ootsuka End
		this.AddColValue(LOGINID_COLUMN, param.GetLoginID(), true, SignType.Equal);
		this.AddColValue(STAFFID_COLUMN, param.GetStaffID(), true, SignType.Equal);
		String[] attribute = param.GetAttribute().split(",");
		for (int i=0; i<attribute.length; i++)
		{
			this.AddColValue(ATTRIBUTE_COLUMN, attribute[i], true, SignType.In);
		}
		String[] syokuinKbn = param.GetSyokuinKbn().split(",");
		for (int i=0; i<syokuinKbn.length; i++)
		{
			this.AddColValue(SYOKUIN_KBN_COLUMN, syokuinKbn[i], true, SignType.In);
		}
		this.AddColValue(SHOWORDER_COLUMN, -1, true, SignType.Down);

		this.AddOrderKeyAsc(SHOWORDER_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateTeamSelectSQL(UserSearchParameter param)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		// 2010.07.30 Add T.Ootsuka Start
		if (param.GetUserName().length() > 0)
		{
			this.AddColValue(USERNAME_COLUMN,  "%" + param.GetUserName() + "%", true, SignType.Like);
		}
		// 2010.07.30 Add T.Ootsuka End
		try
		{
			this.AddColValue(LOGINID_COLUMN, "%" + param.GetLoginID() + "%", true, SignType.Like);
		}
		catch
		{
		}
		String[] attribute = param.GetAttribute().Split(',');
		for (int i=0; i<attribute.length(); i++)
		{
			this.AddColValue(ATTRIBUTE_COLUMN, attribute[i], true, SignType.In);
		}

		// 2010.08.31 Add H.Orikasa Start V2ST-0
		String[] syokuinKbn = param.GetSyokuinKbn().Split(',');
		for (int i=0; i < syokuinKbn.length(); i++)
		{
			this.AddColValue(SYOKUIN_KBN_COLUMN, syokuinKbn[i], true, SignType.In);
		}
		// 2010.08.31 Add H.Orikasa End

		// 2010.09.21 Add K.Shinohara Start
		if (param.GetUserIDValidityFlag().length() > 0)
		{
			this.AddColValue(USERIDVALIDITYFLAG_COLUMN, param.GetUserIDValidityFlag(), true, SignType.Equal);
		}
		// 2010.09.21 Add K.Shinohara End

		this.AddColValue(SHOWORDER_COLUMN, -1, true, SignType.Down);

		this.AddOrderKeyAsc(SHOWORDER_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	/*
	// 2011.02.16 Add DD.Chinh Start KANRO-R-27
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="syokuinKbn">職員区分（空の場合は全て）</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSyokuinSelectSQL(String syokuinKbn)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		// 職員区分がセットされている場合はキーとして使う
		if (!String.IsNullOrEmpty(syokuinKbn))
		{
			this.AddColValue(SYOKUIN_KBN_COLUMN, syokuinKbn, true, SignType.Equal);
		}
		this.AddColValue(SHOWORDER_COLUMN, -1, true, SignType.Down);

		this.AddOrderKeyAsc(SHOWORDER_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	// 2011.02.16 Add DD.Chinh End
	*/
}
