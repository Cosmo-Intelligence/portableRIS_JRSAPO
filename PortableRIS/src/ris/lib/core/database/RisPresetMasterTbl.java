package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.PresetInfoBean;
import ris.lib.core.util.PresetParameter;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
/// プリセットマスタの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisPresetMasterTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PRESETMASTER";
	private static final String TABLE_CAPTION = "プリセットマスタ";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String PRESET_ID_COLUMN			= "PRESET_ID";			//Ris.PresetMaster.PRESET_ID
	public static final String PRESET_NAME_COLUMN			= "PRESET_NAME";		//Ris.PresetMaster.PRESET_NAME
	public static final String KENSATYPE_ID_COLUMN			= "KENSATYPE_ID";		//Ris.PresetMaster.KENSATYPE_ID
	public static final String BUI_ID_COLUMN				= "BUI_ID";				//Ris.PresetMaster.BUI_ID
	public static final String HOUKOU_ID_COLUMN			= "HOUKOU_ID";			//Ris.PresetMaster.HOUKOU_ID
	public static final String SAYUU_ID_COLUMN				= "SAYUU_ID";			//Ris.PresetMaster.SAYUU_ID
	public static final String KENSAHOUHOU_ID_COLUMN		= "KENSAHOUHOU_ID";		//Ris.PresetMaster.KENSAHOUHOU_ID
	public static final String USEFLAG_COLUMN				= "USEFLAG";			//Ris.PresetMaster.USEFLAG
	public static final String SHOWORDER_COLUMN			= "SHOWORDER";			//Ris.PresetMaster.SHOWORDER

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisPresetMasterTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption  = TABLE_CAPTION;
	}

	/// <summary>
	/// プリセット情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>プリセット情報</returns>
	public ArrayList GetPresetInfoData(Connection con, PresetParameter param) throws Exception
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
			if (dt.getRowCount() > 0)
			{
				for (int i=0; i<dt.getRowCount(); i++)
				{
					//プリセット情報Bean作成
					retList.add(CreatePresetInfoBean(dt.getRows().get(i)));
				}
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

		//検査種別ID
		if (param.GetKensatypeID().length() > 0)
		{
			this.AddColSetValue(KENSATYPE_ID_COLUMN, "?", true, SignType.Equal);
			String valueStr = param.GetKensatypeID();
			arglist.add(valueStr);
			//Parameter oraParam = new Parameter(KENSATYPE_ID_COLUMN, valueStr);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(KENSATYPE_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, valueStr, ParameterDirection.Input);
		}

		//部位ID
		if (param.GetBuiID().length() > 0)
		{
			this.AddColSetValue(BUI_ID_COLUMN, "?", true, SignType.Equal);
			String valueStr = param.GetBuiID();
			arglist.add(valueStr);
			//Parameter oraParam = new Parameter(BUI_ID_COLUMN, valueStr);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(BUI_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, valueStr, ParameterDirection.Input);
		}

		//方向ID
		if (param.GetHoukouID().length() > 0)
		{
			this.AddColSetValue(HOUKOU_ID_COLUMN, "?", true, SignType.Equal);
			String valueStr = param.GetHoukouID();
			arglist.add(valueStr);
			//Parameter oraParam = new Parameter(HOUKOU_ID_COLUMN, valueStr);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(HOUKOU_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, valueStr, ParameterDirection.Input);
		}

		//左右ID
		if (param.GetSayuuID().length() > 0)
		{
			this.AddColSetValue(SAYUU_ID_COLUMN, "?", true, SignType.Equal);
			String valueStr = param.GetSayuuID();
			arglist.add(valueStr);
			//Parameter oraParam = new Parameter(SAYUU_ID_COLUMN, valueStr);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(SAYUU_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, valueStr, ParameterDirection.Input);
		}

		//検査方法ID
		if (param.GetKensaHouhouID().length() > 0)
		{
			this.AddColSetValue(KENSAHOUHOU_ID_COLUMN, "?", true, SignType.Equal);
			String valueStr = param.GetKensaHouhouID();
			arglist.add(valueStr);
			//Parameter oraParam = new Parameter(KENSAHOUHOU_ID_COLUMN, valueStr);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(KENSAHOUHOU_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, valueStr, ParameterDirection.Input);
		}

		//使用可否フラグID
		if (param.GetUseFlag() != -1)
		{
			this.AddColSetValue(USEFLAG_COLUMN, "?", true, SignType.Equal);
			arglist.add(param.GetUseFlag());
			//Parameter oraParam = new Parameter(USEFLAG_COLUMN, param.GetUseFlag());
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(USEFLAG_COLUMN, .DataAccess.Client.DbType.Int32, param.GetUseFlag(), ParameterDirection.Input);
		}

		logger.debug("end");

		String colmunName =
			PRESET_ID_COLUMN		+ ", " +
			PRESET_NAME_COLUMN		+ ", " +
			KENSATYPE_ID_COLUMN		+ ", " +
			BUI_ID_COLUMN			+ ", " +
			HOUKOU_ID_COLUMN		+ ", " +
			SAYUU_ID_COLUMN			+ ", " +
			KENSAHOUHOU_ID_COLUMN	+ ", " +
			USEFLAG_COLUMN			+ ", " +
			SHOWORDER_COLUMN;

		this.AddOrderKeyAsc(SHOWORDER_COLUMN);

		cmd = this.GetSelectColmunSQL(colmunName, TABLE_NAME);

		return cmd;
	}

	/// <summary>
	/// プリセット情報Bean作成
	/// </summary>
	/// <param name="row"></param>
	/// <returns></returns>
	public PresetInfoBean CreatePresetInfoBean(DataRow row)
	{
		// プリセットBean作成
		PresetInfoBean bean = new PresetInfoBean();

		// beanへ設定

		// PrimaryKey
		bean.SetPresetID(row.get(PRESET_ID_COLUMN).toString());

		// Data
		if (row.get(PRESET_NAME_COLUMN) != null)
			bean.SetPresetName(row.get(PRESET_NAME_COLUMN).toString());
		if (row.get(KENSATYPE_ID_COLUMN) != null)
			bean.SetKensatypeID(row.get(KENSATYPE_ID_COLUMN).toString());
		if (row.get(BUI_ID_COLUMN) != null)
			bean.SetBuiID(row.get(BUI_ID_COLUMN).toString());
		if (row.get(HOUKOU_ID_COLUMN) != null)
			bean.SetHoukouID(row.get(HOUKOU_ID_COLUMN).toString());
		if (row.get(SAYUU_ID_COLUMN) != null)
			bean.SetSayuuID(row.get(SAYUU_ID_COLUMN).toString());
		if (row.get(KENSAHOUHOU_ID_COLUMN) != null)
			bean.SetKensaHouhouID(row.get(KENSAHOUHOU_ID_COLUMN).toString());
		if (row.get(USEFLAG_COLUMN) != null)
			bean.SetUseFlag(row.get(USEFLAG_COLUMN).toString());
		if (row.get(SHOWORDER_COLUMN) != null)
			bean.SetShowOrder(row.get(SHOWORDER_COLUMN).toString());



		return bean;
	}
}
