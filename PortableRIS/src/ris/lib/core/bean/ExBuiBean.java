package ris.lib.core.bean;

import ris.lib.core.util.CommonString;

/// <summary>
/// ExBuiBean の概要の説明です。
/// </summary>
public class ExBuiBean
{
	// private members
	private String risIDStr				= "";		//Ris.ExBuiTable.RIS_ID
	private String noStr				= "0";		//Ris.ExBuiTable.No
	private String buiSetIDStr			= "";		//Ris.ExBuiTable.BuiSet_ID
	private String buiIDStr				= "";		//Ris.ExBuiTable.BuiID
	private String houkouIDStr			= "";		//Ris.ExBuiTable.Houkou_ID
	private String sayuuIDStr			= "";		//Ris.ExBuiTable.Sayuu_ID
	private String kensaHouhouIDStr		= "";		//Ris.ExBuiTable.KensaHouhou_ID
	private String satueiStatusStr		= "0";		//Ris.ExBuiTable.SatueiStatus 0:未　1:済　2:中止
	private String presetNameStr		= "";		//Ris.ExBuiTable.Preset_Name
	private String hisOriginalFlgStr	= "";		//Ris.ExBuiTable.His_Original_Flg
	private String buiCommentStr		= "";		//Ris.ExBuiTable.BuiComment
	private String buiOrderNoStr		= "";		//Ris.ExBuiTable.BuiOrder_No
	private String kensaSituIDStr		= "";		//Ris.ExBuiTable.KensaSitu_ID
	private String kensaKikiIDStr		= "";		//Ris.ExBuiTable.KensaKiki_ID
	private boolean   delFlg				= false;	//これがtrueの場合は、ExBuiTableから部位情報を削除

	//表示用
	private String buiNameStr			= "";
	private String houkouNameStr		= "";
	private String sayuuNameStr			= "";
	private String kensaHouhouNameStr	= "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExBuiBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExBuiBean]");
		strBuild.append("RIS_ID="			+ risIDStr				+ ";");
		strBuild.append("No="				+ noStr					+ ";");
		strBuild.append("BuiSet_ID="		+ buiSetIDStr			+ ";");
		strBuild.append("BuiID="			+ buiIDStr				+ ";");
		strBuild.append("Houkou_ID="		+ houkouIDStr			+ ";");
		strBuild.append("Sayuu_ID="			+ sayuuIDStr			+ ";");
		strBuild.append("KensaHouhou_ID="	+ kensaHouhouIDStr		+ ";");
		strBuild.append("SatueiStatus="		+ satueiStatusStr		+ ";");
		strBuild.append("Preset_Name="		+ presetNameStr			+ ";");
		strBuild.append("His_Original_Flg=" + hisOriginalFlgStr		+ ";");
		strBuild.append("BuiComment="		+ buiCommentStr			+ ";");
		strBuild.append("BuiOrder_No="		+ buiOrderNoStr			+ ";");
		strBuild.append("KensaSitu_ID="		+ kensaSituIDStr		+ ";");
		strBuild.append("KensaKiki_ID="		+ kensaKikiIDStr		+ ";");
		strBuild.append("DEL_FLG="			+ delFlg				+ ";");
		strBuild.append("BuiName="			+ buiNameStr			+ ";");
		strBuild.append("HoukouName="		+ houkouNameStr			+ ";");
		strBuild.append("SayuuName="		+ sayuuNameStr			+ ";");
		strBuild.append("KensahouhouName="	+ kensaHouhouNameStr	+ ";");

		return strBuild.toString();
	}

	// risIDStrのSET
	public void SetRisID( String risID )
	{
		if( risID != null )
		{
			this.risIDStr = risID;
		}
	}
	// risIDStrのGET
	public String GetRisID()
	{
		return this.risIDStr;
	}

	// noStrのSET
	public void SetNo( String no )
	{
		if( no != null )
		{
			this.noStr = no;
		}
	}
	public void SetNo( Integer no )
	{
		if( no >= 0 )
		{
			this.noStr = no.toString();
		}
	}
	// noStrのGET
	public String GetNo()
	{
		return this.noStr;
	}

	// noStrのGET
	public String GetNoString()
	{
		if (this.noStr.length() <= 1)
		{
			return "0" + this.noStr;
		}
		else
		{
			return this.noStr;
		}
	}

	// buiSetIDStrのSET
	public void SetBuiSetID( String buiSetID )
	{
		if( buiSetID != null )
		{
			this.buiSetIDStr = buiSetID;
		}
	}
	// buiSetIDStrのGET
	public String GetBuiSetID()
	{
		return this.buiSetIDStr;
	}

	// buiIDStrのSET
	public void SetBuiID( String buiID )
	{
		if( buiID != null )
		{
			this.buiIDStr = buiID;
		}
	}
	// buiIDStrのGET
	public String GetBuiID()
	{
		return this.buiIDStr;
	}

	// houkouIDStrのSET
	public void SetHoukouID( String houkouID )
	{
		if( houkouID != null )
		{
			this.houkouIDStr = houkouID;
		}
	}
	// houkouIDStrのGET
	public String GetHoukouID()
	{
		return this.houkouIDStr;
	}

	// sayuuIDStrのSET
	public void SetSayuuID( String sayuuID )
	{
		if( sayuuID != null )
		{
			this.sayuuIDStr = sayuuID;
		}
	}
	// sayuuIDStrのGET
	public String GetSayuuID()
	{
		return this.sayuuIDStr;
	}

	// kensaHouhouIDStrのSET]
	public void SetKensaHouhouID( String kensaHouhouID )
	{
		if( kensaHouhouID != null )
		{
			this.kensaHouhouIDStr = kensaHouhouID;
		}
	}
	// kensaHouhouIDStrのGET
	public String GetKensaHouhouID()
	{
		return this.kensaHouhouIDStr;
	}

	// satueiStatusStrのSET（0:未　1:済　2:中止）
	public void SetSatueiStatus(String status)
	{
		if (status != null)
		{
			if (status.compareTo("0") == 0 || status.compareTo("1") == 0 || status.compareTo("2") == 0)
			{
				this.satueiStatusStr = status;
			}
			else
			{
				this.satueiStatusStr = "0";
			}
		}
	}
	public void SetSatueiStatus(Integer status)
	{
		if (status >= 0 && status <= 2)
		{
			this.satueiStatusStr = status.toString();
		}
		else
		{
			this.satueiStatusStr = "0";
		}
	}
	// satueiStatusStrのGET
	public String GetSatueiStatus()
	{
		return this.satueiStatusStr;
	}

	// satueiStatusStrのGET
	public String GetSatueiStatusString()
	{
		if (CommonString.SATUEISTATUS_MI.equals(this.satueiStatusStr))
		{
			return CommonString.SATUEISTATUS_MI_STR;
		}
		else if (CommonString.SATUEISTATUS_SUMI.equals(this.satueiStatusStr))
		{
			return CommonString.SATUEISTATUS_SUMI_STR;
		}
		else if (CommonString.SATUEISTATUS_STOP.equals(this.satueiStatusStr))
		{
			return CommonString.SATUEISTATUS_STOP_STR;
		}
		else
		{
			return CommonString.SATUEISTATUS_MI_STR;
		}
	}

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

	// hisOriginalFlgStrのSET
	public void SetHisOriginalFlg(String flg)
	{
		if (flg != null)
		{
			this.hisOriginalFlgStr = flg;
		}
	}
	// hisOriginalFlgStrのGET
	public String GetHisOriginalFlg()
	{
		return this.hisOriginalFlgStr;
	}

	// hisOriginalFlgStrのGET
	public String GetHisOriginalFlgString()
	{
		if (CommonString.HIS_ORIGINAL_FLG_ON_STR.equals(this.hisOriginalFlgStr))
		{
			return CommonString.HIS_ORIGINAL_FLG_ON_STR;
		}
		else
		{
			return "";
		}
	}

	// buiCommentStrのSET
	public void SetBuiComment( String buiComment )
	{
		if( buiComment != null )
		{
			this.buiCommentStr = buiComment;
		}
	}
	// buiCommentStrのGET
	public String GetBuiComment()
	{
		return this.buiCommentStr;
	}

	// buiOrderNoStrのSET
	public void SetBuiOrderNo( String buiOrderNo )
	{
		if( buiOrderNo != null )
		{
			this.buiOrderNoStr = buiOrderNo;
		}
	}
	// buiOrderNoStrのGET
	public String GetBuiOrderNo()
	{
		return this.buiOrderNoStr;
	}

	// kensaSituIDStrのSET
	public void SetKensaSituID( String kensaSituID )
	{
		if( kensaSituID != null )
		{
			this.kensaSituIDStr = kensaSituID;
		}
	}
	// kensaSituIDStrのGET
	public String GetKensaSituID()
	{
		return this.kensaSituIDStr;
	}

	// kensaKikiIDStrのSET
	public void SetKensaKikiID( String kensaKikiID )
	{
		if( kensaKikiID != null )
		{
			this.kensaKikiIDStr = kensaKikiID;
		}
	}
	// kensaKikiIDStrのGET
	public String GetKensaKikiID()
	{
		return this.kensaKikiIDStr;
	}

	// delFlgのSET
	public void SetDelFlgON()
	{
		this.delFlg = true;
	}
	public void SetDelFlgOff()
	{
		this.delFlg = false;
	}
	// delFlgのGET
	public boolean IsDelRecord()
	{
		return this.delFlg;
	}

	// buiNameStrのSET
	public void SetBuiName(String buiName)
	{
		if (buiName != null)
		{
			this.buiNameStr = buiName;
		}
	}
	// buiNameStrのGET
	public String GetBuiName()
	{
		return this.buiNameStr;
	}

	// houkouNameStrのSET
	public void SetHoukouName(String houkouName)
	{
		if (houkouName != null)
		{
			this.houkouNameStr = houkouName;
		}
	}
	// houkouNameStrのGET
	public String GetHoukouName()
	{
		return this.houkouNameStr;
	}

	// sayuuNameStrのSET
	public void SetSayuuName(String sayuuName)
	{
		if (sayuuName != null)
		{
			this.sayuuNameStr = sayuuName;
		}
	}
	// sayuuNameStrのGET
	public String GetSayuuName()
	{
		return this.sayuuNameStr;
	}

	// kensaHouhouNameStrのSET
	public void SetKensaHouhouName(String kensaHouhouName)
	{
		if (kensaHouhouName != null)
		{
			this.kensaHouhouNameStr = kensaHouhouName;
		}
	}
	// kensaHouhouNameStrのGET
	public String GetKensaHouhouName()
	{
		return this.kensaHouhouNameStr;
	}
}
