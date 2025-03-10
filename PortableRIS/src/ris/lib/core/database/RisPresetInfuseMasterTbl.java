package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.util.PresetParameter;
import ris.portable.util.DataTable;

/// <summary>
/// プリセット手技マスタの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisPresetInfuseMasterTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PRESETINFUSEMASTER";
	private static final String TABLE_CAPTION = "プリセット手技マスタ";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String PRESET_ID_COLUMN				= "PRESET_ID";				//Ris.PresetInfuseMaster.PRESET_ID
	public static final String NO_COLUMN						= "NO";						//Ris.PresetInfuseMaster.NO
	public static final String INFUSE_ID_COLUMN				= "INFUSE_ID";				//Ris.PresetInfuseMaster.INFUSE_ID
	public static final String INFUSEKBN_COLUMN				= "INFUSEKBN";				//Ris.PresetInfuseMaster.INFUSEKBN
	public static final String SUURYOU_IJI_COLUMN				= "SUURYOU_IJI";			//Ris.PresetInfuseMaster.SUURYOU_IJI
	public static final String SUURYOU_COLUMN					= "SUURYOU";				//Ris.PresetInfuseMaster.SUURYOU
	public static final String PARTSBUNRUI_ID_COLUMN			= "PARTSBUNRUI_ID";			//Ris.PresetInfuseMaster.PARTSBUNRUI_ID
	public static final String DETAILPARTSBUNRUI_ID_COLUMN		= "DETAILPARTSBUNRUI_ID";	//Ris.PresetInfuseMaster.DETAILPARTSBUNRUI_ID

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisPresetInfuseMasterTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// プリセット手技情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>プリセット手技情報</returns>
	public ArrayList GetPresetInfuseData(Connection con, PresetParameter param) throws Exception
	{
		// parameters
		ArrayList retList = new ArrayList();

		// begin log
		logger.debug("begin");

		if (con != null && param != null)
		{
			ArrayList<Object> arglist = new ArrayList<Object>();
			String cmd = CreateSelectSQL(param, arglist);
			DataTable dt = Select(con, cmd, arglist);
			for (int i=0; i<dt.getRowCount(); i++)
			{
				retList.add(dt.getRows().get(i));
			}
		}

		// end log
		logger.debug("end");

		return retList;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(PresetParameter param, ArrayList<Object> arglist)
	{
		logger.debug("begin");

		String cmd = "";

		this.keys.clear();

		this.ClearOrderKey();
		this.AddOrderKeyAsc(PRESET_ID_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		//プリセットID
		if (param.GetPresetID().length() > 0)
		{
			this.AddColSetValue(PRESET_ID_COLUMN, "?", true, SignType.Equal);
			String valueStr = param.GetPresetID();
			arglist.add(valueStr);
			//Parameter oraParam = new Parameter(PRESET_ID_COLUMN, valueStr);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(PRESET_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, valueStr, ParameterDirection.Input);
		}

		logger.debug("end");

		String colmunName =
			PRESET_ID_COLUMN				+ ", " +
			NO_COLUMN						+ ", " +
			INFUSE_ID_COLUMN				+ ", " +
			INFUSEKBN_COLUMN				+ ", " +
			SUURYOU_IJI_COLUMN				+ ", " +
			SUURYOU_COLUMN					+ ", " +
			PARTSBUNRUI_ID_COLUMN			+ ", " +
			DETAILPARTSBUNRUI_ID_COLUMN;

		cmd = this.GetSelectColmunSQL(colmunName, TABLE_NAME);

		return cmd;
	}
}
