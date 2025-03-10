package ris.lib.core.bean;

/// <summary>
///
/// 一覧表示項目定義設定の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.12	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class ShowItemDefineBean
{
	public String	windowAppIDStr		= "";
	public int		idInt				= 0;
	public int		itemIDInt			= 0;
	public int		displaySizeInt		= 0;
	public int		alignmentInt		= 0;
	public int		titleAlignmentInt	= 0;
	public String	displayFormatStr	= "";
	public String	displayNameStr		= "";
	public int		nameChangeFlgInt	= 0;
	public int		showorderInt		= 0;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ShowItemDefineBean()
	{
		//
	}
//
	// windowAppIDStrのGET
	public String GetWindowAppID()
	{
		return this.windowAppIDStr;
	}
	// windowAppIDStrのSET
	public void SetWindowAppID(String windowAppID)
	{
		if (windowAppID != null)
		{
			this.windowAppIDStr = windowAppID;
		}
	}
//
	// idIntのGET
	public Integer GetID()
	{
		return this.idInt;
	}
	// idIntのSET
	public void SetID(Integer id)
	{
		this.idInt = id;
	}
//
	// itemIDIntのGET
	public Integer GetItemID()
	{
		return this.itemIDInt;
	}
	// itemIDIntのSET
	public void SetItemID(Integer itemID)
	{
		this.itemIDInt = itemID;
	}
//
	// displaySizeIntのGET
	public Integer GetDisplaySize()
	{
		return this.displaySizeInt;
	}
	// displaySizeIntのSET
	public void SetDisplaySize(Integer displaySize)
	{
		this.displaySizeInt = displaySize;
	}
//
	// alignmentIntのGET
	public Integer GetAlignment()
	{
		return this.alignmentInt;
	}
	// alignmentIntのSET
	public void SetAlignment(Integer alignment)
	{
		this.alignmentInt = alignment;
	}
//
	// titleAlignmentIntのGET
	public Integer GetTitleAlignment()
	{
		return this.titleAlignmentInt;
	}
	// titleAlignmentIntのSET
	public void SetTitleAlignment(Integer titleAlignment)
	{
		this.titleAlignmentInt = titleAlignment;
	}
//
	// displayFormatStrのGET
	public String GetDisplayFormat()
	{
		return this.displayFormatStr;
	}
	// displayFormatStrのSET
	public void SetDisplayFormat(String displayFormat)
	{
		if (displayFormat != null)
		{
			this.displayFormatStr = displayFormat;
		}
	}
//
	// displayNameStrのGET
	public String GetDisplayName()
	{
		return this.displayNameStr;
	}
	// displayNameStrのSET
	public void SetDisplayName(String displayName)
	{
		if (displayName != null)
		{
			this.displayNameStr = displayName;
		}
	}
//
	// nameChangeFlgIntのGET
	public Integer GetNameChangeFlg()
	{
		return this.nameChangeFlgInt;
	}
	// nameChangeFlgIntのSET
	public void SetNameChangeFlg(Integer nameChangeFlg)
	{
		this.nameChangeFlgInt = nameChangeFlg;
	}
//
	// showorderIntのGET
	public Integer GetShoworder()
	{
		return this.showorderInt;
	}
	// showorderIntのSET
	public void SetShoworder(Integer showorder)
	{
		this.showorderInt = showorder;
	}
}
