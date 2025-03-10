package ris.lib.core.util;

import java.util.ArrayList;

/// <summary>
///
/// プリセット検索条件クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class PresetParameter
{
	// private members
	private String presetIDStr			= "";
	private String kensatypeIDStr		= "";
	private String buiIDStr				= "";
	private String houkouIDStr			= "";
	private String sayuuIDStr			= "";
	private String kensaHouhouIDStr		= "";
	private int    useFlagInt			= -1;
	private String presetIDsStr			= "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public PresetParameter()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[PresetParameter]");
		strBuild.append("PresetID="			+ presetIDStr		+ ";");
		strBuild.append("KensatypeID="		+ kensatypeIDStr	+ ";");
		strBuild.append("BuiID="			+ buiIDStr			+ ";");
		strBuild.append("Houkou_ID="		+ houkouIDStr		+ ";");
		strBuild.append("Sayuu_ID="			+ sayuuIDStr		+ ";");
		strBuild.append("KensaHouhou_ID="	+ kensaHouhouIDStr	+ ";");
		strBuild.append("UseFlag="			+ useFlagInt		+ ";");
		strBuild.append("presetIDs="		+ presetIDsStr		+ ";");

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
	// useFlagIntのSET
	public void SetUseFlag(int useFlag)
	{
		this.useFlagInt = useFlag;
	}
	// useFlagIntのGET
	public int GetUseFlag()
	{
		return this.useFlagInt;
	}
//
	// presetIDsStrのSET
	public void SetPresetIDList(String presetIDs)
	{
		if (presetIDs != null)
		{
			this.presetIDsStr = presetIDs;
		}
	}
	// presetIDsStrのSET
	public void SetPresetIDList(ArrayList list)
	{
		this.presetIDsStr = "";
		ArrayList checkList = new ArrayList();
		for (int i=0; i<list.size(); i++)
		{
			String pID = list.get(i).toString();
			if (!checkList.contains(pID))
			{
				if (this.presetIDsStr.length() <= 0)
				{
					this.presetIDsStr = list.get(i).toString();
				}
				else
				{
					this.presetIDsStr = ", " + list.get(i).toString();
				}
				checkList.add(pID);
			}
		}
	}
	// presetIDsStrのGET
	public String GetPresetIDList()
	{
		return this.presetIDsStr;
	}
}
