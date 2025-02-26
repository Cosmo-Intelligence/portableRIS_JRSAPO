package ris.lib.core.bean;

/// <summary>
///
/// オーダ指示情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.05	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class OrderIndicateBean
{
	private String risIDStr				= "";		//Ris.OrderIndicateTable.Ris_ID
	private String orderCommentIDStr	= "";		//Ris.OrderIndicateTable.OrderComment_ID
	private String kensaSijiStr			= "";		//Ris.OrderIndicateTable.Kensa_Siji
	private String rinsyouStr			= "";		//Ris.OrderIndicateTable.Rinsyou
	private String remarksStr			= "";		//Ris.OrderIndicateTable.Remarks
//
	// risIDStrのSET
	public void SetRisID(String risID)
	{
		if (risID != null)
		{
			this.risIDStr = risID;
		}
	}
	// risIDStrのGET
	public String GetRisID()
	{
		return this.risIDStr;
	}
//
	// orderCommentIDStrのSET
	public void SetOrderCommentID(String orderCommentID)
	{
		if (orderCommentID != null)
		{
			this.orderCommentIDStr = orderCommentID;
		}
	}
	// orderCommentIDStrのGET
	public String GetOrderCommentID()
	{
		return this.orderCommentIDStr;
	}
//
	// kensaSijiStrのSET
	public void SetKensaSiji(String kensaSiji)
	{
		if (kensaSiji != null)
		{
			this.kensaSijiStr = kensaSiji;
		}
	}
	// kensaSijiStrのGET
	public String GetKensaSiji()
	{
		return this.kensaSijiStr;
	}
//
	// rinsyouStrのSET
	public void SetRinsyou(String rinsyou)
	{
		if (rinsyou != null)
		{
			this.rinsyouStr = rinsyou;
		}
	}
	// rinsyouStrのGET
	public String GetRinsyou()
	{
		return this.rinsyouStr;
	}
//
	// remarksStrのSET
	public void SetRemarks(String remarks)
	{
		if (remarks != null)
		{
			this.remarksStr = remarks;
		}
	}
	// remarksStrのGET
	public String GetRemarks()
	{
		return this.remarksStr;
	}
}
