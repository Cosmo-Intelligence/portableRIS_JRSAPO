package ris.lib.core.bean;

/// <summary>
/// ソート情報クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.11	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class SortInfoBean
{
	// private members
	private String keyNameStr		= "";
	private String nameStr			= "";
	private String headerTextStr	= "";
	private String sortStr			= "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public SortInfoBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[SortInfoBean]");
		strBuild.append("KeyNameStr="		+ keyNameStr + ";");
		strBuild.append("NameStr="		+ nameStr + ";");
		strBuild.append("HeaderTextStr="	+ headerTextStr + ";");
		strBuild.append("SortStr="		+ sortStr + ";");
		//
		return strBuild.toString();
	}

	// keyNameStrのSET
	public void SetKeyName( String keyName )
	{
		if( keyName != null )
		{
			this.keyNameStr = keyName;
		}
	}
	// keyNameStrのGET
	public String GetKeyName()
	{
		return this.keyNameStr;
	}
//
	// nameStrのSET
	public void SetName( String name )
	{
		if( name != null )
		{
			this.nameStr = name;
		}
	}
//
	// nameStrのGET
	public String GetName()
	{
		return this.nameStr;
	}

	// headerTextStrのSET
	public void SetHeaderText( String headerText )
	{
		if( headerText != null )
		{
			this.headerTextStr = headerText;
		}
	}
	// headerTextStrのGET
	public String GetHeaderText()
	{
		return this.headerTextStr;
	}
//
	// sortStrのSET
	public void SetSort( String sort )
	{
		if( sort != null )
		{
			this.sortStr = sort;
		}
	}
	// sortStrのGET
	public String GetSort()
	{
		return this.sortStr;
	}
//
	// Sort用文字列のGet
	public String GetSortString()
	{
		String retStr = "";
		if (this.keyNameStr.length() > 0 && this.sortStr.length() > 0)
		{
			retStr = this.keyNameStr + this.sortStr;
		}
		return retStr;
	}
}
