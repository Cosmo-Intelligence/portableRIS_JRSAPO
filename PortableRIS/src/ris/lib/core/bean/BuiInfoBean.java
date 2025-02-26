package ris.lib.core.bean;

/// <summary>
/// 部位情報クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.11	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class BuiInfoBean
{
	// private members
	private String risIDStr				= "";		//Ris.OrderBuiTable.RIS_ID,			Ris.ExBuiTable.RIS_ID
	private String noStr				= "0";		//Ris.OrderBuiTable.No,				Ris.ExBuiTable.No
	private String buiSetIDStr			= ""; 		//Ris.OrderBuiTable.BuiSet_ID,		Ris.ExBuiTable.BuiSet_ID
	private String buiSetNameStr		= ""; 		//->BuiSet_Name,					->BuiSet_Name
	private String buiIDStr				= ""; 		//Ris.OrderBuiTable.BuiID,			Ris.ExBuiTable.BuiID
	private String houkouIDStr			= ""; 		//Ris.OrderBuiTable.Houkou_ID,		Ris.ExBuiTable.Houkou_ID
	private String sayuuIDStr			= ""; 		//Ris.OrderBuiTable.Sayuu_ID,		Ris.ExBuiTable.Sayuu_ID
	private String kensaHouhouIDStr		= ""; 		//Ris.OrderBuiTable.KensaHouhou_ID, Ris.ExBuiTable.KensaHouhou_ID
	private String buiCommentIDStr		= ""; 		//Ris.OrderBuiTable.BuiComment_ID
	private String buiCommentStr		= ""; 		//Ris.OrderBuiTable.BuiComment,		Ris.ExBuiTable.BuiComment
	private String buiOrderNoStr		= ""; 		//Ris.OrderBuiTable.BuiOrder_No,	Ris.ExBuiTable.BuiOrder_No
	private String kensaSituIDStr		= ""; 		//Ris.OrderBuiTable.KensaSitu_ID,	Ris.ExBuiTable.KensaSitu_ID
	private String kensaKikiIDStr		= ""; 		//Ris.OrderBuiTable.KensaKiki_ID,	Ris.ExBuiTable.KensaKiki_ID
	private String addendum01Str 		= ""; 		//Ris.OrderBuiTable.Addendum01
	private String addendum02Str 		= ""; 		//Ris.OrderBuiTable.Addendum02
	private String addendum03Str 		= ""; 		//Ris.OrderBuiTable.Addendum03
	private String addendum04Str 		= ""; 		//Ris.OrderBuiTable.Addendum04
	private String addendum05Str 		= ""; 		//Ris.OrderBuiTable.Addendum05
	private String satueiStatusStr		= "0";		//Ris.ExBuiTable.SatueiStatus --> 0:未　1:済　2:中止
	private String presetNameStr		= "";		//Ris.ExBuiTable.Preset_Name
	private String hisOriginalFlgStr	= "";		//Ris.ExBuiTable.His_Original_Flg
	private boolean delFlg					= false;

	//部位選択用
	private String buibunruiIDStr		= "";
	private String buibunruiNameStr		= "";
	private String buiNameStr			= "";
	private String sayuuNameStr			= "";
	private String houkouNameStr		= "";
	private String kensaHouhouNameStr	= "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public BuiInfoBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[BuiInfoBean]");
		strBuild.append("RIS_ID="			+ risIDStr				+ ";");
		strBuild.append("No="				+ noStr					+ ";");
		strBuild.append("BuiSet_ID="		+ buiSetIDStr			+ ";");
		strBuild.append("BuiSet_Name="		+ buiSetNameStr			+ ";");
		strBuild.append("BuiID="			+ buiIDStr				+ ";");
		strBuild.append("Houkou_ID="		+ houkouIDStr			+ ";");
		strBuild.append("Sayuu_ID="			+ sayuuIDStr			+ ";");
		strBuild.append("KensaHouhou_ID="	+ kensaHouhouIDStr		+ ";");
		strBuild.append("BuiComment_ID="	+ buiCommentIDStr		+ ";");
		strBuild.append("BuiComment="		+ buiCommentStr			+ ";");
		strBuild.append("BuiOrder_No="		+ buiOrderNoStr			+ ";");
		strBuild.append("KensaSitu_ID="		+ kensaSituIDStr		+ ";");
		strBuild.append("KensaKiki_ID="		+ kensaKikiIDStr		+ ";");
		strBuild.append("Addendum01="		+ addendum01Str 		+ ";");
		strBuild.append("Addendum02="		+ addendum02Str 		+ ";");
		strBuild.append("Addendum03="		+ addendum03Str 		+ ";");
		strBuild.append("Addendum04="		+ addendum04Str 		+ ";");
		strBuild.append("Addendum05="		+ addendum05Str 		+ ";");
		strBuild.append("SatueiStatus="		+ satueiStatusStr		+ ";");
		strBuild.append("Preset_Name="		+ presetNameStr			+ ";");
		strBuild.append("His_Original_Flg=" + hisOriginalFlgStr		+ ";");
		strBuild.append("DEL_FLG="			+ delFlg				+ ";");

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

	// buiSetNameStrのSET
	public void SetBuiSetName(String buiSetName)
	{
		if (buiSetName != null)
		{
			this.buiSetNameStr = buiSetName;
		}
	}
	// buiSetNameStrのGET
	public String GetBuiSetName()
	{
		return this.buiSetNameStr;
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

	// buiCommentIDStrのSET
	public void SetBuiCommentID( String buiCommentID )
	{
		if( buiCommentID != null )
		{
			this.buiCommentIDStr = buiCommentID;
		}
	}
	// buiCommentIDStrのGET
	public String GetBuiCommentID()
	{
		return this.buiCommentIDStr;
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

	// addendum01StrのSET
	public void SetAddendum01( String addendum01 )
	{
		if( addendum01 != null )
		{
			this.addendum01Str = addendum01;
		}
	}
	// addendum01StrのGET
	public String GetAddendum01()
	{
		return this.addendum01Str;
	}

	// addendum02StrのSET
	public void SetAddendum02( String addendum02 )
	{
		if( addendum02 != null )
		{
			this.addendum02Str = addendum02;
		}
	}
	// addendum02StrのGET
	public String GetAddendum02()
	{
		return this.addendum02Str;
	}

	// addendum03StrのSET
	public void SetAddendum03( String addendum03 )
	{
		if( addendum03 != null )
		{
			this.addendum03Str = addendum03;
		}
	}
	// addendum03StrのGET
	public String GetAddendum03()
	{
		return this.addendum03Str;
	}

	// addendum04StrのSET
	public void SetAddendum04( String addendum04 )
	{
		if( addendum04 != null )
		{
			this.addendum04Str = addendum04;
		}
	}
	// addendum04StrのGET
	public String GetAddendum04()
	{
		return this.addendum04Str;
	}

	// addendum05StrのSET
	// addendum04StrのSET
	public void SetAddendum05( String addendum05 )
	{
		if( addendum05 != null )
		{
			this.addendum05Str = addendum05;
		}
	}
	// addendum05StrのGET
	public String GetAddendum05()
	{
		return this.addendum05Str;
	}

	// satueiStatusStrのSET（0:未　1:済　2:中止）
	public void SetSatueiStatus( String status )
	{
		if( status != null )
		{
			if( status.compareTo("0") == 0 || status.compareTo("1") == 0 || status.compareTo("2") == 0 )
			{
				this.satueiStatusStr = status;
			}
			else
			{
				this.satueiStatusStr = "0";
			}
		}
	}
	public void SetSatueiStatus( Integer status )
	{
		if( status >= 0 && status <= 2 )
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

	// presetNameStrのSET
	public void SetPresetName( String presetName )
	{
		if( presetName != null )
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
	public void SetHisOriginalFlg( String flg )
	{
		if( flg != null )
		{
			this.hisOriginalFlgStr = flg;
		}
	}
	// hisOriginalFlgStrのGET
	public String GetHisOriginalFlg()
	{
		return this.hisOriginalFlgStr;
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

	// buibunruiIDStrのSET
	public void SetBuibunruiID(String buibunruiID)
	{
		if (buibunruiID != null)
		{
			this.buibunruiIDStr = buibunruiID;
		}
	}
	// buibunruiIDStr
	public String GetBuibunruiID()
	{
		return this.buibunruiIDStr;
	}

	// buibunruiNameStrのSET
	public void SetBuibunruiName(String buibunruiName)
	{
		if (buibunruiName != null)
		{
			this.buibunruiNameStr = buibunruiName;
		}
	}
	// buibunruiNameStr
	public String GetBuibunruiName()
	{
		return this.buibunruiNameStr;
	}

	// buiNameStrのSET
	public void SetBuiName(String buiName)
	{
		if (buiName != null)
		{
			this.buiNameStr = buiName;
		}
	}
	// buiNameStr
	public String GetBuiName()
	{
		return this.buiNameStr;
	}

	// sayuuNameStrのSET
	public void SetSayuuName(String sayuuName)
	{
		if (sayuuName != null)
		{
			this.sayuuNameStr = sayuuName;
		}
	}
	// sayuuNameStr
	public String GetSayuuName()
	{
		return this.sayuuNameStr;
	}

	// houkouNameStrのSET
	public void SetHoukouName(String houkouName)
	{
		if (houkouName != null)
		{
			this.houkouNameStr = houkouName;
		}
	}
	// houkouNameStr
	public String GetHoukouName()
	{
		return this.houkouNameStr;
	}

	// kensaHouhouNameStrのSET
	public void SetKensaHouhouName(String kensaHouhouName)
	{
		if (kensaHouhouName != null)
		{
			this.kensaHouhouNameStr = kensaHouhouName;
		}
	}
	// kensaHouhouNameStr
	public String GetKensaHouhouName()
	{
		return this.kensaHouhouNameStr;
	}
}
