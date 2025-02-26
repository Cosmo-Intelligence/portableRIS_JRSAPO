package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.Configuration;
import ris.lib.core.util.RequestParameter;
import ris.portable.util.DataTable;

/// <summary>
///
/// CR撮影メニュー情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.12	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisCrSatueiMenuMaster extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "CRSATUEIMENUMASTER";
	private static final String TABLE_CAPTION = "CR撮影ﾒﾆｭｰﾏｽﾀ";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String SATUEIMENU_ID_COLUMN			= "SATUEIMENU_ID";
	public static final String SATUEIMENU_NAME_COLUMN			= "SATUEIMENU_NAME";
	public static final String SATUEIMENU_NAME_KANA_COLUMN		= "SATUEIMENU_NAME_KANA";
	public static final String SATUEIMENU_NAME_ENGLISH_COLUMN	= "SATUEIMENU_NAME_ENGLISH";
	public static final String SATUEI_CODE01_COLUMN			= "SATUEI_CODE01";
	public static final String SATUEI_CODE02_COLUMN			= "SATUEI_CODE02";
	public static final String SATUEI_CODE03_COLUMN			= "SATUEI_CODE03";
	public static final String SATUEI_CODE04_COLUMN			= "SATUEI_CODE04";
	public static final String SATUEI_CODE05_COLUMN			= "SATUEI_CODE05";
	public static final String SATUEI_CODE06_COLUMN			= "SATUEI_CODE06";
	public static final String SATUEI_CODE07_COLUMN			= "SATUEI_CODE07";
	public static final String SATUEI_CODE08_COLUMN			= "SATUEI_CODE08";
	public static final String SATUEI_CODE09_COLUMN			= "SATUEI_CODE09";
	public static final String SATUEI_CODE10_COLUMN			= "SATUEI_CODE10";
	public static final String FILM_ID_COLUMN					= "FILM_ID";
	public static final String PARTITION_COLUMN				= "PARTITION";
	public static final String USED_COLUMN						= "USED";
	public static final String CRPRESETDATA01_COLUMN			= "CRPRESETDATA01";
	public static final String CRPRESETDATA02_COLUMN			= "CRPRESETDATA02";
	public static final String CRPRESETDATA03_COLUMN			= "CRPRESETDATA03";
	public static final String CRPRESETDATA04_COLUMN			= "CRPRESETDATA04";
	public static final String CRPRESETDATA05_COLUMN			= "CRPRESETDATA05";
	public static final String CRPRESETDATA06_COLUMN			= "CRPRESETDATA06";
	public static final String CRPRESETDATA07_COLUMN			= "CRPRESETDATA07";
	public static final String CRPRESETDATA08_COLUMN			= "CRPRESETDATA08";
	public static final String CRPRESETDATA09_COLUMN			= "CRPRESETDATA09";
	public static final String CRPRESETDATA10_COLUMN			= "CRPRESETDATA10";
	public static final String CRPRESETDATA11_COLUMN			= "CRPRESETDATA11";
	public static final String CRPRESETDATA12_COLUMN			= "CRPRESETDATA12";
	public static final String CRPRESETDATA13_COLUMN			= "CRPRESETDATA13";
	public static final String CRPRESETDATA14_COLUMN			= "CRPRESETDATA14";
	public static final String CRPRESETDATA15_COLUMN			= "CRPRESETDATA15";
	public static final String CRPRESETDATA16_COLUMN			= "CRPRESETDATA16";
	public static final String CRPRESETDATA17_COLUMN			= "CRPRESETDATA17";
	public static final String CRPRESETDATA18_COLUMN			= "CRPRESETDATA18";
	public static final String CRPRESETDATA19_COLUMN			= "CRPRESETDATA19";
	public static final String CRPRESETDATA20_COLUMN			= "CRPRESETDATA20";
	public static final String USEFLAG_COLUMN					= "USEFLAG";
	public static final String SHOWORDER_COLUMN				= "SHOWORDER";
	public static final String KENSATYPE_FILTER_FLG_COLUMN		= "KENSATYPE_FILTER_FLG";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisCrSatueiMenuMaster()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// CR撮影メニュー情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>CR撮影メニュー情報</returns>
	public DataTable GetCrSatueiMenuData(Connection con, RequestParameter param) throws Exception
	{
		// parameters
		DataTable retDt = new DataTable();

		// begin log
		logger.debug("begin");

		if (con != null && param != null)
		{
			retDt = Select(con, CreateSelectSQL(param), null);
		}

		// end log
		logger.debug("end");

		return retDt;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(RequestParameter param)
	{
		logger.debug("begin");

		this.keys.clear();

		//撮影メニューID
		String[] menuIDs = param.GetSatueiMenuIDs().split(",");
		if (menuIDs.length == 1)
		{
			this.AddColValue(SATUEIMENU_ID_COLUMN, menuIDs[0], true);
		}
		else
		{
			for (int i=0; i<menuIDs.length; i++)
			{
				this.AddColValue(SATUEIMENU_ID_COLUMN, menuIDs[i], true, SignType.In);
			}
		}

		logger.debug("end");

		return this.GetSelectSQL();
	}
}
