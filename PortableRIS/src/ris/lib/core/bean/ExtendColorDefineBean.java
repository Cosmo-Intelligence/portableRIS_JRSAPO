package ris.lib.core.bean;

/// <summary>
///
/// 拡張色定義情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.05	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class ExtendColorDefineBean
{
	private String keyValueStr		= "";	//Ris.Extend_ColorDefineBean.KEYVALUE
	private String labelStr			= "";	//Ris.Extend_ColorDefineBean.LABEL
	private String colorModeStr		= "";	//Ris.Extend_ColorDefineBean.COLORMODE
	private String colorStr			= "";	//Ris.Extend_ColorDefineBean.COLOR
	private String colorBkStr		= "";	//Ris.Extend_ColorDefineBean.COLORBK
	private String showorderStr		= "";	//Ris.Extend_ColorDefineBean.SHOWORDER
//
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExtendColorDefineBean()
	{
		//
	}
//
	// keyValueStrのSET
	public void SetKeyValue(String keyValue)
	{
		if (keyValue != null)
		{
			this.keyValueStr = keyValue;
		}
	}
	// keyValueStrのGET
	public String GetKeyValue()
	{
		return this.keyValueStr;
	}
//
	// labelStrのSET
	public void SetLabel(String label)
	{
		if (label != null)
		{
			this.labelStr = label;
		}
	}
	// labelStrのGET
	public String GetLabel()
	{
		return this.labelStr;
	}
//
	// colorModeStrのSET
	public void SetColorMode(String colorMode)
	{
		if (colorMode != null)
		{
			this.colorModeStr = colorMode;
		}
	}
	// colorModeStrのGET
	public String GetColorMode()
	{
		return this.colorModeStr;
	}
//
	// colorStrのSET
	public void SetColor(String color)
	{
		if (color != null)
		{
			this.colorStr = color;
		}
	}
	// colorStrのGET
	public String GetColor()
	{
		return this.colorStr;
	}
//
	// colorBkStrのSET
	public void SetColorBk(String colorBk)
	{
		if (colorBk != null)
		{
			this.colorBkStr = colorBk;
		}
	}
	// colorBkStrのGET
	public String GetColorBk()
	{
		return this.colorBkStr;
	}
//
	// showorderStrのSET
	public void SetShoworder(String showorder)
	{
		if (showorder != null)
		{
			this.showorderStr = showorder;
		}
	}
	// showorderStrのGET
	public String GetShoworder()
	{
		return this.showorderStr;
	}
}
