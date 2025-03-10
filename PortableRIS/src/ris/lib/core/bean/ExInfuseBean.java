package ris.lib.core.bean;

/// <summary>
/// ExInfuseBean の概要の説明です。
/// </summary>
public class ExInfuseBean
{
	// private members
	private String risIDStr					=  ""; //Ris.ExZoueizaiTable.RIS_ID
	private String buiNoStr					= "0"; //Ris.ExZoueizaiTable.Bui_No
	private String noStr					= "0"; //Ris.ExZoueizaiTable.No
	private String infuseIDStr				=  ""; //Ris.ExZoueizaiTable.Infuse_ID
	private String suuryoIjiStr				=  ""; //Ris.ExZoueizaiTable.Suuryou_Iji
	private String suuryoStr				=  ""; //Ris.ExZoueizaiTable.Suuryou
	private String detailPartsBunruiIDStr	=  ""; //Ris.ExZoueizaiTable.DetailPartsBunrui_ID
	private String partsBunruiIDStr			=  ""; //Ris.ExZoueizaiTable.PartsBunrui_ID
	private String barcodeDataStr			=  ""; //Ris.ExZoueizaiTable.BarcodeData

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExInfuseBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExZoueizaiBean]");
		strBuild.append("RIS_ID="				+ risIDStr + ";");
		strBuild.append("Bui_No="				+ buiNoStr + ";");
		strBuild.append("No="					+ noStr + ";");
		strBuild.append("Infuse_ID="			+ infuseIDStr + ";");
		strBuild.append("Suuryou_Iji="			+ suuryoIjiStr + ";");
		strBuild.append("Suuryou="				+ suuryoStr + ";");
		strBuild.append("DetailPartsBunrui_ID=" + detailPartsBunruiIDStr + ";");
		strBuild.append("PartsBunrui_ID="		+ partsBunruiIDStr + ";");
		strBuild.append("BarcodeData="			+ barcodeDataStr + ";");

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

	// buiNoStrのSET
	public void SetBuiNo( String buiNo )
	{
		if( buiNo != null )
		{
			this.buiNoStr = buiNo;
		}
	}
	public void SetBuiNo( Integer buiNo )
	{
		if( buiNo >= 0 )
		{
			this.buiNoStr = buiNo.toString();
		}
	}
	// buiNoStrのGET
	public String GetBuiNo()
	{
		return this.buiNoStr;
	}
	// buiNoStrのGET
	public String GetBuiNoString()
	{
		if (this.buiNoStr.length() <= 1)
		{
			return "0" + this.buiNoStr;
		}
		else
		{
			return this.buiNoStr;
		}
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

	// infuseIDStrのSET
	public void SetInfuseID(String infuseID)
	{
		if (infuseID != null)
		{
			this.infuseIDStr = infuseID;
		}
	}
	// infuseIDStrのGET
	public String GetInfuseID()
	{
		return this.infuseIDStr;
	}

	// suuryoIjiStrのSET
	public void SetSuuryoIji( String suuryoIji )
	{
		if( suuryoIji != null )
		{
			this.suuryoIjiStr = suuryoIji;
		}
	}
	public void SetSuuryoIji( Integer suuryoIji )
	{
		if( suuryoIji >= 0 )
		{
			this.suuryoIjiStr = suuryoIji.toString();
		}
	}
	// suuryoIjiStrのGET
	public String GetSuuryoIji()
	{
		return this.suuryoIjiStr;
	}

	// suuryoStrのSET
	public void SetSuuryo( String suuryo )
	{
		if( suuryo != null )
		{
			this.suuryoStr = suuryo;
		}
	}
	public void SetSuuryo( Integer suuryo )
	{
		if( suuryo >= 0 )
		{
			this.suuryoStr = suuryo.toString();
		}
	}
	// suuryoStrのGET
	public String GetSuuryo()
	{
		return this.suuryoStr;
	}

	// detailPartsBunruiIDStrのSET
	public void SetDetailPartsBunruiID( String bunruiID )
	{
		if( bunruiID != null )
		{
			this.detailPartsBunruiIDStr = bunruiID;
		}
	}
	public void SetDetailPartsBunruiID( Integer bunruiID )
	{
		if( bunruiID >= 0 )
		{
			this.detailPartsBunruiIDStr = bunruiID.toString();
		}
	}
	// detailPartsBunruiIDStrのGET
	public String GetDetailPartsBunruiID()
	{
		return this.detailPartsBunruiIDStr;
	}

	// partsBunruiIDStrのSET
	public void SetPartsBunruiID( String bunruiID )
	{
		if( bunruiID != null )
		{
			this.partsBunruiIDStr = bunruiID;
		}
	}
	public void SetPartsBunruiID( Integer bunruiID )
	{
		if( bunruiID >= 0 )
		{
			this.partsBunruiIDStr = bunruiID.toString();
		}
	}
	// partsBunruiIDStrのGET
	public String GetPartsBunruiID()
	{
		return this.partsBunruiIDStr;
	}

	// barcodeDataStrのSET
	public void SetBarcodeData(String barcodeData)
	{
		if (barcodeData != null)
		{
			this.barcodeDataStr = barcodeData;
		}
	}
	// barcodeDataStrのGET
	public String GetBarcodeData()
	{
		return this.barcodeDataStr;
	}
}
