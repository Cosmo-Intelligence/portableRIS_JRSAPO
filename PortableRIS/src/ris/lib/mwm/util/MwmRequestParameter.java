package ris.lib.mwm.util;

import java.util.ArrayList;

/// <summary>
/// 検索条件クラス の概要の説明です。
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class MwmRequestParameter
{
	private String			buiIdStr				= "";			// 部位IDとして使用					（MWM用）
	private String			buiNoStr				= "";			// 部位Noとして使用					（MWM用）
	private String			houkouIDStr				= "";			// 方向IDとして使用					（MWM用）
	private String			kensaHouhouIDStr		= "";			// 検査方法IDとして使用				（MWM用）
	private String			statusIDStr				= "";			// 撮影進捗として使用				（MWM用）
	private ArrayList		satueiMenuArrayList		= null;			// 撮影メニューIDのListとして使用	（MWM用）

	@Override
	public String toString()
	{

		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[MwmRequestParameter]");
		strBuild.append("BUI_ID="			+ buiIdStr				+ ";");
		strBuild.append("BUI_NO="			+ buiNoStr				+ ";");
		strBuild.append("HOUKOU_ID="		+ houkouIDStr			+ ";");
		strBuild.append("KENSAHOUKOU_ID="	+ kensaHouhouIDStr		+ ";");
		strBuild.append("STATUS_ID="		+ statusIDStr			+ ";");
		strBuild.append("SATUEIMENU="		+ satueiMenuArrayList	+ ";");

		return strBuild.toString();
	}

	/// <summary>
	/// 部位IDを取得する
	/// </summary>
	/// <returns></returns>
	public String GetBuiId()
	{
		return this.buiIdStr;
	}

	/// <summary>
	/// 部位IDをセットする
	/// </summary>
	/// <param name="buiId"></param>
	public void SetBuiId( String buiId )
	{
		if( buiId != null )
		{
			this.buiIdStr = buiId;
		}
	}

	/// <summary>
	/// 部位Noを取得する
	/// </summary>
	/// <returns></returns>
	public String GetBuiNo()
	{
		return this.buiNoStr;
	}

	/// <summary>
	/// 部位Noをセットする
	/// </summary>
	/// <param name="buiNo"></param>
	public void SetBuiNo( String buiNo )
	{
		if( buiNo != null )
		{
			this.buiNoStr = buiNo;
		}
	}

	/// <summary>
	/// 方向IDを取得する
	/// </summary>
	/// <returns></returns>
	public String GetHoukouID()
	{
		return this.houkouIDStr;
	}

	/// <summary>
	/// 方向IDをセットする
	/// </summary>
	/// <param name="houkouID"></param>
	public void SetHoukouID( String houkouID )
	{
		if( houkouID != null )
		{
			this.houkouIDStr = houkouID;
		}
	}

	/// <summary>
	/// 検査方法IDを取得する
	/// </summary>
	/// <returns></returns>
	public String GetKensaHouhouID()
	{
		return this.kensaHouhouIDStr;
	}

	/// <summary>
	/// 検査方法IDをセットする
	/// </summary>
	/// <param name="kensaHouhouID"></param>
	public void SetKensaHouhouID( String kensaHouhouID )
	{
		if( kensaHouhouID != null )
		{
			this.kensaHouhouIDStr = kensaHouhouID;
		}
	}

	/// <summary>
	/// 進捗IDを取得する
	/// </summary>
	/// <returns></returns>
	public String GetStatusID()
	{
		return this.statusIDStr;
	}

	/// <summary>
	/// 進捗IDをセットする
	/// </summary>
	/// <param name="kensaHouhouID"></param>
	public void SetStatusID( String statusID )
	{
		if( statusID != null )
		{
			this.statusIDStr = statusID;
		}
	}

	/// <summary>
	/// 撮影メニューIDのListを取得する
	/// </summary>
	/// <returns></returns>
	public ArrayList GetSatueiMenuArrayList()
	{
		if (this.satueiMenuArrayList == null)
		{
			// null の場合は空のListを返す
			return new ArrayList();
		}
		else
		{
			return this.satueiMenuArrayList;
		}
	}

	/// <summary>
	/// 撮影メニューIDのListをセットする
	/// </summary>
	/// <param name="list"></param>
	public void SetSatueiMenuArrayList( ArrayList list )
	{
		if( list != null )
		{
			this.satueiMenuArrayList = list;
		}
	}
}
