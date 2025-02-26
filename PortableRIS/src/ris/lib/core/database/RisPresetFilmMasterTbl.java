package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.util.PresetParameter;
import ris.portable.util.DataTable;

/// <summary>
/// プリセットフィルムマスタの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisPresetFilmMasterTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PRESETFILMMASTER";
	private  static final String TABLE_CAPTION = "プリセットフィルムマスタ";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String PRESET_ID_COLUMN		= "PRESET_ID";		//Ris.PresetFilmMaster.PRESET_ID
	public static final String NO_COLUMN				= "NO";				//Ris.PresetFilmMaster.NO
	public static final String FILM_ID_COLUMN			= "FILM_ID";		//Ris.PresetFilmMaster.FILM_ID
	public static final String PARTITION_COLUMN		= "PARTITION";		//Ris.PresetFilmMaster.PARTITION
	public static final String USE_COLUMN				= "USE";			//Ris.PresetFilmMaster.USE
	public static final String LOSS_COLUMN				= "LOSS";			//Ris.PresetFilmMaster.LOSS

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisPresetFilmMasterTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// プリセットフィルム情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>プリセットフィルム情報</returns>
	public ArrayList GetPresetFilmData(Connection con, PresetParameter param) throws Exception
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
			PRESET_ID_COLUMN		+ ", " +
			NO_COLUMN				+ ", " +
			FILM_ID_COLUMN			+ ", " +
			PARTITION_COLUMN		+ ", " +
			USE_COLUMN				+ ", " +
			LOSS_COLUMN;

		cmd = this.GetSelectColmunSQL(colmunName, TABLE_NAME);

		return cmd;
	}
}
