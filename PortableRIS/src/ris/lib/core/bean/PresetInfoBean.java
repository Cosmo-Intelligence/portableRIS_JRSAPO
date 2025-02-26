package ris.lib.core.bean;

import java.util.ArrayList;

/// <summary>
/// PresetInfoBean の概要の説明です。
/// </summary>
public class PresetInfoBean
{
	// private members
	private String presetIDStr			= "";	//Ris.PresetMaster.PRESET_ID
	private String presetNameStr		= "";	//Ris.PresetMaster.PRESET_NAME
	private String kensatypeIDStr		= "";	//Ris.PresetMaster.KENSATYPE_ID
	private String buiIDStr				= "";	//Ris.PresetMaster.BUI_ID
	private String houkouIDStr			= "";	//Ris.PresetMaster.HOUKOU_ID
	private String sayuuIDStr			= "";	//Ris.PresetMaster.SAYUU_ID
	private String kensaHouhouIDStr		= "";	//Ris.PresetMaster.KENSAHOUHOU_ID
	private String useFlagStr			= "";	//Ris.PresetMaster.USEFLAG
	private String showOrderStr			= "";	//Ris.PresetMaster.SHOWORDER

	private ArrayList arrListSatuei = new ArrayList(); //PresetSatueiMasterのリスト
	//--> PresetMaster.PRESET_ID=PresetSatueiMaster.PRESET_IDとなるレコード（複数）のリスト

	private ArrayList arrListZoueizai = new ArrayList(); //PresetZoueizaiMasterのリスト
	//--> PresetMaster.PRESET_ID=PresetZoueizaiMaster.PRESET_IDとなるレコード（複数）のリスト

	private ArrayList arrListInfuse = new ArrayList(); //PresetInfuseMasterのリスト
	//--> PresetMaster.PRESET_ID=PresetInfuseMaster.PRESET_IDとなるレコード（複数）のリスト

	private ArrayList arrListFilm = new ArrayList(); //PresetFilmMasterのリスト
	//--> PresetMaster.PRESET_ID=PresetFilmMaster.PRESET_IDとなるレコード（複数）のリスト

	private ArrayList arrListExamItem = new ArrayList(); //PresetExamMasterのリスト
	//--> PresetMaster.PRESET_ID=PresetExamMaster.PRESET_IDとなるレコード（複数）のリスト

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public PresetInfoBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[PresetInfoBean]");
		strBuild.append("PresetID="			+ presetIDStr		+ ";");
		strBuild.append("PresetName="		+ presetNameStr		+ ";");
		strBuild.append("KensatypeID="		+ kensatypeIDStr	+ ";");
		strBuild.append("BuiID="			+ buiIDStr			+ ";");
		strBuild.append("Houkou_ID="		+ houkouIDStr		+ ";");
		strBuild.append("Sayuu_ID="			+ sayuuIDStr		+ ";");
		strBuild.append("KensaHouhou_ID="	+ kensaHouhouIDStr	+ ";");
		strBuild.append("UseFlag="			+ useFlagStr		+ ";");
		strBuild.append("ShowOrder="		+ showOrderStr		+ ";");

		return strBuild.toString();
	}
//
	// presetIDStrのSET
	public void SetPresetID(String presetID)
	{
		if (presetID != null)
		{
			this.presetIDStr = presetID;
		}
	}
	// presetIDStrのGET
	public String GetPresetID()
	{
		return this.presetIDStr;
	}
//
	// presetNameStrのSET
	public void SetPresetName(String presetName)
	{
		if (presetName != null)
		{
			this.presetNameStr = presetName;
		}
	}
	// presetNameStrのGET
	public String GetPresetName()
	{
		return this.presetNameStr;
	}
//
	// kensatypeIDStrのSET
	public void SetKensatypeID(String kensatypeID)
	{
		if (kensatypeID != null)
		{
			this.kensatypeIDStr = kensatypeID;
		}
	}
	// kensatypeIDStrのGET
	public String GetKensatypeID()
	{
		return this.kensatypeIDStr;
	}
//
	// buiIDStrのSET
	public void SetBuiID(String buiID)
	{
		if (buiID != null)
		{
			this.buiIDStr = buiID;
		}
	}
	// buiIDStrのGET
	public String GetBuiID()
	{
		return this.buiIDStr;
	}
//
	// houkouIDStrのSET
	public void SetHoukouID(String houkouID)
	{
		if (houkouID != null)
		{
			this.houkouIDStr = houkouID;
		}
	}
	// houkouIDStrのGET
	public String GetHoukouID()
	{
		return this.houkouIDStr;
	}
//
	// sayuuIDStrのSET
	public void SetSayuuID(String sayuuID)
	{
		if (sayuuID != null)
		{
			this.sayuuIDStr = sayuuID;
		}
	}
	// sayuuIDStrのGET
	public String GetSayuuID()
	{
		return this.sayuuIDStr;
	}
//
	// kensaHouhouIDStrのSET]
	public void SetKensaHouhouID(String kensaHouhouID)
	{
		if (kensaHouhouID != null)
		{
			this.kensaHouhouIDStr = kensaHouhouID;
		}
	}
	// kensaHouhouIDStrのGET
	public String GetKensaHouhouID()
	{
		return this.kensaHouhouIDStr;
	}
//
	// useFlagStrのSET]
	public void SetUseFlag(String useFlag)
	{
		if (useFlag != null)
		{
			this.useFlagStr = useFlag;
		}
	}
	// useFlagStrのGET
	public String GetUseFlag()
	{
		return this.useFlagStr;
	}
//
	// showOrderStrのSET]
	public void SetShowOrder(String showOrder)
	{
		if (showOrder != null)
		{
			this.showOrderStr = showOrder;
		}
	}
	// showOrderStrのGET
	public String GetShowOrder()
	{
		return this.showOrderStr;
	}
//
	// arrListSatueiの取得
	public ArrayList GetSatueiList()
	{
		return this.arrListSatuei;
	}
	// arrListSatueiの初期化
	public void ReconstructSatueiList(ArrayList satueiList)
	{
		if (satueiList != null)
		{
			this.arrListSatuei.clear();
			this.arrListSatuei = satueiList;
		}
	}
//
	// arrListZoueizaiの取得
	public ArrayList GetZoueizaiList()
	{
		return this.arrListZoueizai;
	}
	// arrListZoueizaiの初期化
	public void ReconstructZoueizaiList(ArrayList zoueizaiList)
	{
		if (zoueizaiList != null)
		{
			this.arrListZoueizai.clear();
			this.arrListZoueizai = zoueizaiList;
		}
	}
//
	// arrListInfuseの取得
	public ArrayList GetInfuseList()
	{
		return this.arrListInfuse;
	}
	// arrListInfuseの初期化
	public void ReconstructInfuseList(ArrayList infuseList)
	{
		if (infuseList != null)
		{
			this.arrListInfuse.clear();
			this.arrListInfuse = infuseList;
		}
	}
//
	// arrListFilmの取得
	public ArrayList GetFilmList()
	{
		return this.arrListFilm;
	}
	// arrListFilmの初期化
	public void ReconstructFilmList(ArrayList filmList)
	{
		if (filmList != null)
		{
			this.arrListFilm.clear();
			this.arrListFilm = filmList;
		}
	}
//
	// arrListExamItemの取得
	public ArrayList GetExamItemList()
	{
		return this.arrListExamItem;
	}
	// arrListExamItemの初期化
	public void ReconstructExamItemList(ArrayList examList)
	{
		if (examList != null)
		{
			this.arrListExamItem.clear();
			this.arrListExamItem = examList;
		}
	}
}
