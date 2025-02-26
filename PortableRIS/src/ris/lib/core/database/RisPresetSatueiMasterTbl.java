package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.util.PresetParameter;
import ris.portable.util.DataTable;

/// <summary>
/// プリセット撮影マスタの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisPresetSatueiMasterTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PRESETSATUEIMASTER";
	private static final String TABLE_CAPTION = "プリセット撮影マスタ";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String PRESET_ID_COLUMN			= "PRESET_ID";			//Ris.PresetSatueiMaster.PRESET_ID
	public static final String NO_COLUMN					= "NO";					//Ris.PresetSatueiMaster.NO
	public static final String PRESETDATA01_COLUMN			= "PRESETDATA01";		//Ris.PresetSatueiMaster.PRESETDATA01
	public static final String PRESETDATA02_COLUMN			= "PRESETDATA02";		//Ris.PresetSatueiMaster.PRESETDATA02
	public static final String PRESETDATA03_COLUMN			= "PRESETDATA03";		//Ris.PresetSatueiMaster.PRESETDATA03
	public static final String PRESETDATA04_COLUMN			= "PRESETDATA04";		//Ris.PresetSatueiMaster.PRESETDATA04
	public static final String PRESETDATA05_COLUMN			= "PRESETDATA05";		//Ris.PresetSatueiMaster.PRESETDATA05
	public static final String PRESETDATA06_COLUMN			= "PRESETDATA06";		//Ris.PresetSatueiMaster.PRESETDATA06
	public static final String PRESETDATA07_COLUMN			= "PRESETDATA07";		//Ris.PresetSatueiMaster.PRESETDATA07
	public static final String PRESETDATA08_COLUMN			= "PRESETDATA08";		//Ris.PresetSatueiMaster.PRESETDATA08
	public static final String PRESETDATA09_COLUMN			= "PRESETDATA09";		//Ris.PresetSatueiMaster.PRESETDATA09
	public static final String PRESETDATA10_COLUMN			= "PRESETDATA10";		//Ris.PresetSatueiMaster.PRESETDATA10
	public static final String PRESETDATA11_COLUMN			= "PRESETDATA11";		//Ris.PresetSatueiMaster.PRESETDATA11
	public static final String PRESETDATA12_COLUMN			= "PRESETDATA12";		//Ris.PresetSatueiMaster.PRESETDATA12
	public static final String PRESETDATA13_COLUMN			= "PRESETDATA13";		//Ris.PresetSatueiMaster.PRESETDATA13
	public static final String PRESETDATA14_COLUMN			= "PRESETDATA14";		//Ris.PresetSatueiMaster.PRESETDATA14
	public static final String PRESETDATA15_COLUMN			= "PRESETDATA15";		//Ris.PresetSatueiMaster.PRESETDATA15
	public static final String PRESETDATA16_COLUMN			= "PRESETDATA16";		//Ris.PresetSatueiMaster.PRESETDATA16
	public static final String PRESETDATA17_COLUMN			= "PRESETDATA17";		//Ris.PresetSatueiMaster.PRESETDATA17
	public static final String PRESETDATA18_COLUMN			= "PRESETDATA18";		//Ris.PresetSatueiMaster.PRESETDATA18
	public static final String PRESETDATA19_COLUMN			= "PRESETDATA19";		//Ris.PresetSatueiMaster.PRESETDATA19
	public static final String PRESETDATA20_COLUMN			= "PRESETDATA20";		//Ris.PresetSatueiMaster.PRESETDATA20
	public static final String SATUEIMENU_ID_COLUMN		= "SATUEIMENU_ID";		//Ris.PresetSatueiMaster.SATUEIMENU_ID

	public static final String KVP_COLUMN					= "KVP";				//Ris.PresetSatueiMaster.KVP
	public static final String XRAYTUBECURRENT_MA_COLUMN	= "XRAYTUBECURRENT_MA";	//Ris.PresetSatueiMaster.XRAYTUBECURRENT_MA
	public static final String EXPOSURETIME_SEC_COLUMN		= "EXPOSURETIME_SEC";	//Ris.PresetSatueiMaster.EXPOSURETIME_SEC
	public static final String EXPOSURETIME_MIN_COLUMN		= "EXPOSURETIME_MIN";	//Ris.PresetSatueiMaster.EXPOSURETIME_MIN
	public static final String KV_COLUMN					= "KV";					//Ris.PresetSatueiMaster.KV
	public static final String MAS_COLUMN					= "MAS";				//Ris.PresetSatueiMaster.MAS
	public static final String EXPOSURENO_COLUMN			= "EXPOSURENO";			//Ris.PresetSatueiMaster.EXPOSURENO
	public static final String CTDI_COLUMN					= "CTDI";				//Ris.PresetSatueiMaster.CTDI
	public static final String DLP_COLUMN					= "DLP";				//Ris.PresetSatueiMaster.DLP
	public static final String FLUOROSCOPY_COLUMN			= "FLUOROSCOPY";		//Ris.PresetSatueiMaster.FLUOROSCOPY
	public static final String IMAGEAREA_COLUMN			= "IMAGEAREA";			//Ris.PresetSatueiMaster.IMAGEAREA
	public static final String D_DISTANCE_MM_COLUMN		= "D_DISTANCE_MM";		//Ris.PresetSatueiMaster.D_DISTANCE_MM
	public static final String D_DISTANCE_CM_COLUMN		= "D_DISTANCE_CM";		//Ris.PresetSatueiMaster.D_DISTANCE_CM
	public static final String E_DISTANCE_MM_COLUMN		= "E_DISTANCE_MM";		//Ris.PresetSatueiMaster.E_DISTANCE_MM
	public static final String ENTRANCEDOSE_DGY_COLUMN		= "ENTRANCEDOSE_DGY";	//Ris.PresetSatueiMaster.ENTRANCEDOSE_DGY
	public static final String ENTRANCEDOSE_MGY_COLUMN		= "ENTRANCEDOSE_MGY";	//Ris.PresetSatueiMaster.ENTRANCEDOSE_MGY
	public static final String EXPOSEDAREA_COLUMN			= "EXPOSEDAREA";		//Ris.PresetSatueiMaster.EXPOSEDAREA
	public static final String RADIATIONMODE_COLUMN		= "RADIATIONMODE";		//Ris.PresetSatueiMaster.RADIATIONMODE
	public static final String FILTERTYPE_COLUMN			= "FILTERTYPE";			//Ris.PresetSatueiMaster.FILTERTYPE
	public static final String FILTERMATERIAL_COLUMN		= "FILTERMATERIAL";		//Ris.PresetSatueiMaster.FILTERMATERIAL

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisPresetSatueiMasterTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// プリセット撮影情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>プリセット撮影情報</returns>
	public ArrayList GetPresetSatueiData(Connection con, PresetParameter param) throws Exception
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
		String addWhereStr = "";

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

		//プリセットIDリスト
		if (param.GetPresetIDList().length() > 0)
		{
			addWhereStr = "AND " + PRESET_ID_COLUMN + " IN (";

			String[] presetIDs = param.GetPresetIDList().split(",");
			for (int i=0; i<presetIDs.length; i++)
			{
				if (i==0)
				{
					addWhereStr += "'" + presetIDs[i] + "'";
				}
				else
				{
					addWhereStr += ", '" + presetIDs[i] + "'";
				}
			}
			addWhereStr += ")";
		}

		logger.debug("end");

		String colmunName =
			PRESET_ID_COLUMN			+ ", " +
			NO_COLUMN					+ ", " +
			PRESETDATA01_COLUMN			+ ", " +
			PRESETDATA02_COLUMN			+ ", " +
			PRESETDATA03_COLUMN			+ ", " +
			PRESETDATA04_COLUMN			+ ", " +
			PRESETDATA05_COLUMN			+ ", " +
			PRESETDATA06_COLUMN			+ ", " +
			PRESETDATA07_COLUMN			+ ", " +
			PRESETDATA08_COLUMN			+ ", " +
			PRESETDATA09_COLUMN			+ ", " +
			PRESETDATA10_COLUMN			+ ", " +
			PRESETDATA11_COLUMN			+ ", " +
			PRESETDATA12_COLUMN			+ ", " +
			PRESETDATA13_COLUMN			+ ", " +
			PRESETDATA14_COLUMN			+ ", " +
			PRESETDATA15_COLUMN			+ ", " +
			PRESETDATA16_COLUMN			+ ", " +
			PRESETDATA17_COLUMN			+ ", " +
			PRESETDATA18_COLUMN			+ ", " +
			PRESETDATA19_COLUMN			+ ", " +
			PRESETDATA20_COLUMN			+ ", " +
			SATUEIMENU_ID_COLUMN;
		/* 一ノ瀬保留
		//検査MPPSの場合
		if (CommonString.FLG_ON.equals(Configuration.GetInstance().GetMppsKensaFlg()))
		{
			colmunName += ", ";
			colmunName += KVP_COLUMN				+ ", " +
						  XRAYTUBECURRENT_MA_COLUMN	+ ", " +
						  EXPOSURETIME_SEC_COLUMN	+ ", " +
						  EXPOSURETIME_MIN_COLUMN	+ ", " +
						  KV_COLUMN					+ ", " +
						  MAS_COLUMN				+ ", " +
						  EXPOSURENO_COLUMN			+ ", " +
						  CTDI_COLUMN				+ ", " +
						  DLP_COLUMN				+ ", " +
						  FLUOROSCOPY_COLUMN		+ ", " +
						  IMAGEAREA_COLUMN			+ ", " +
						  D_DISTANCE_MM_COLUMN		+ ", " +
						  D_DISTANCE_CM_COLUMN		+ ", " +
						  E_DISTANCE_MM_COLUMN		+ ", " +
						  ENTRANCEDOSE_DGY_COLUMN	+ ", " +
						  ENTRANCEDOSE_MGY_COLUMN	+ ", " +
						  EXPOSEDAREA_COLUMN		+ ", " +
						  RADIATIONMODE_COLUMN		+ ", " +
						  FILTERTYPE_COLUMN			+ ", " +
						  FILTERMATERIAL_COLUMN;
		}
		*/
		cmd = this.GetSelectColmunSQL(colmunName, TABLE_NAME);
		cmd += addWhereStr;

		return cmd;
	}
}
