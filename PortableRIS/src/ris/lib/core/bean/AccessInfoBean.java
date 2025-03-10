package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.portable.common.Const;

/// <summary>
///
/// 排他情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.05	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class AccessInfoBean
{
	// private members
	private String		idStr			= "";					//AccessInfo.ID
	private String		appIDStr		= "";					//AccessInfo.APPID
	private String		ipAddressStr	= "";					//AccessInfo.IPADDRESS
	private String		accessModeStr	= "1";					//AccessInfo.ACCESSMODE
	private Timestamp	entryTimeDT		= Const.TIMESTAMP_MINVALUE;	//AccessInfo.ENTRYTIME

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public AccessInfoBean()
	{
		//
	}
//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[AccessInfoBean]");
		strBuild.append("ID="			+	idStr			+ ";");
		strBuild.append("APPID="		+	appIDStr		+ ";");
		strBuild.append("IPADDRESS="	+	ipAddressStr	+ ";");
		strBuild.append("ACCESSMODE="	+	accessModeStr	+ ";");
		strBuild.append("ENTRYTIME="	+	entryTimeDT		+ ";");

		return strBuild.toString();
	}
//
	// idStrのSET
	public void SetID(String id)
	{
		if (id != null)
		{
			this.idStr = id;
		}
	}
	// idStrのGET
	public String GetID()
	{
		return this.idStr;
	}
//
	// appIDStrのSET
	public void SetAppID(String appID)
	{
		if (appID != null)
		{
			this.appIDStr = appID;
		}
	}
	// appIDStrのGET
	public String GetAppID()
	{
		return this.appIDStr;
	}
//
	// ipAddressStrのSET
	public void SetIpAddress(String ipAddress)
	{
		if (ipAddress != null)
		{
			this.ipAddressStr = ipAddress;
		}
	}
	// ipAddressStrのGET
	public String GetIpAddress()
	{
		return this.ipAddressStr;
	}
//
	// accessModeStrのSET
	public void SetAccessMode(String accessMode)
	{
		if (accessMode != null)
		{
			this.accessModeStr = accessMode;
		}
	}
	// accessModeStrのGET
	public String GetAccessMode()
	{
		return this.accessModeStr;
	}
//
	// entryTimeDTのSET
	public void SetEntryTime(Timestamp entryTime)
	{
		this.entryTimeDT = entryTime;
	}
	// entryTimeDTのGET
	public Timestamp GetEntryTime()
	{
		return this.entryTimeDT;
	}
}
