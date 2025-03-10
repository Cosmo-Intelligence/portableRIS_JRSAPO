package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.util.PresetParameter;
import ris.portable.util.DataTable;

/// <summary>
/// プリセット管理項目マスタの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisPresetExamMasterTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PRESETEXAMMASTER";
	private static final String TABLE_CAPTION = "プリセット管理項目マスタ";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String PRESET_ID_COLUMN		= "PRESET_ID";		//Ris.PresetExamMaster.PRESET_ID
	public static final String EXAMDATA01_COLUMN		= "EXAMDATA01";		//Ris.PresetExamMaster.ExamData01
	public static final String EXAMDATA02_COLUMN		= "EXAMDATA02";		//Ris.PresetExamMaster.ExamData02
	public static final String EXAMDATA03_COLUMN		= "EXAMDATA03";		//Ris.PresetExamMaster.ExamData03
	public static final String EXAMDATA04_COLUMN		= "EXAMDATA04";		//Ris.PresetExamMaster.ExamData04
	public static final String EXAMDATA05_COLUMN		= "EXAMDATA05";		//Ris.PresetExamMaster.ExamData05
	public static final String EXAMDATA06_COLUMN		= "EXAMDATA06";		//Ris.PresetExamMaster.ExamData06
	public static final String EXAMDATA07_COLUMN		= "EXAMDATA07";		//Ris.PresetExamMaster.ExamData07
	public static final String EXAMDATA08_COLUMN		= "EXAMDATA08";		//Ris.PresetExamMaster.ExamData08
	public static final String EXAMDATA09_COLUMN		= "EXAMDATA09";		//Ris.PresetExamMaster.ExamData09
	public static final String EXAMDATA10_COLUMN		= "EXAMDATA10";		//Ris.PresetExamMaster.ExamData10
	public static final String EXAMDATA11_COLUMN		= "EXAMDATA11";		//Ris.PresetExamMaster.ExamData11
	public static final String EXAMDATA12_COLUMN		= "EXAMDATA12";		//Ris.PresetExamMaster.ExamData12
	public static final String EXAMDATA13_COLUMN		= "EXAMDATA13";		//Ris.PresetExamMaster.ExamData13
	public static final String EXAMDATA14_COLUMN		= "EXAMDATA14";		//Ris.PresetExamMaster.ExamData14
	public static final String EXAMDATA15_COLUMN		= "EXAMDATA15";		//Ris.PresetExamMaster.ExamData15
	public static final String EXAMDATA16_COLUMN		= "EXAMDATA16";		//Ris.PresetExamMaster.ExamData16
	public static final String EXAMDATA17_COLUMN		= "EXAMDATA17";		//Ris.PresetExamMaster.ExamData17
	public static final String EXAMDATA18_COLUMN		= "EXAMDATA18";		//Ris.PresetExamMaster.ExamData18
	public static final String EXAMDATA19_COLUMN		= "EXAMDATA19";		//Ris.PresetExamMaster.ExamData19
	public static final String EXAMDATA20_COLUMN		= "EXAMDATA20";		//Ris.PresetExamMaster.ExamData20

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisPresetExamMasterTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// プリセット管理項目情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>プリセットフィルム情報</returns>
	public ArrayList GetPresetExamData(Connection con, PresetParameter param) throws Exception
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

		//プリセットID
		if (param.GetPresetID().length() > 0)
		{
			this.AddColSetValue(PRESET_ID_COLUMN, "?", true, SignType.Equal);
			String valueStr = param.GetPresetID();
			arglist.add(valueStr);
			//Parameter oraParam = new Parameter(PRESET_ID_COLUMN, valueStr);
			//cmd.Parameters.Add(oraParam);
		}

		logger.debug("end");

		String colmunName =
			PRESET_ID_COLUMN		+ ", " +
			EXAMDATA01_COLUMN		+ ", " +
			EXAMDATA02_COLUMN		+ ", " +
			EXAMDATA03_COLUMN		+ ", " +
			EXAMDATA04_COLUMN		+ ", " +
			EXAMDATA05_COLUMN		+ ", " +
			EXAMDATA06_COLUMN		+ ", " +
			EXAMDATA07_COLUMN		+ ", " +
			EXAMDATA08_COLUMN		+ ", " +
			EXAMDATA09_COLUMN		+ ", " +
			EXAMDATA10_COLUMN		+ ", " +
			EXAMDATA11_COLUMN		+ ", " +
			EXAMDATA12_COLUMN		+ ", " +
			EXAMDATA13_COLUMN		+ ", " +
			EXAMDATA14_COLUMN		+ ", " +
			EXAMDATA15_COLUMN		+ ", " +
			EXAMDATA16_COLUMN		+ ", " +
			EXAMDATA17_COLUMN		+ ", " +
			EXAMDATA18_COLUMN		+ ", " +
			EXAMDATA19_COLUMN		+ ", " +
			EXAMDATA20_COLUMN;

		cmd = this.GetSelectColmunSQL(colmunName, TABLE_NAME);

		return cmd;
	}
}
