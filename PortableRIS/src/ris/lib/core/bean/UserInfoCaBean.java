package ris.lib.core.bean;

/// <summary>
/// UserInfoCaBean の概要の説明です。
/// </summary>
public class UserInfoCaBean
{
	// private members
	private int		idInt			= 0; 	//UserInfoCa.ID
	private String	loginIDStr		= ""; 	//UserInfoCa.LOGINID
	private String	staffIDStr		= ""; 	//UserInfoCa.STAFFID
	private String	hospitalIDStr	= ""; 	//UserInfoCa.HOSPITALID
	private String	syokuinKbnStr	= ""; 	//UserInfoCa.SYOKUIN_KBN
	private int		attributeInt	= -1; 	//UserInfoCa.ATTRIBUTE
	private int		showorderInt	= 0; 	//UserInfoCa.SHOWORDER

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public UserInfoCaBean()
    {
        //
    }
	//
	// idStrのSET
	public void SetID(int id)
	{
		this.idInt = id;
	}
	// idStrのGET
	public int GetID()
	{
		return this.idInt;
	}
	//
	// loginIDStrのSET
	public void SetLoginID(String loginID)
	{
		if (loginID != null)
		{
			this.loginIDStr = loginID;
		}
	}
	// loginIDStrのGET
	public String GetLoginID()
	{
		return this.loginIDStr;
	}
	//
	// staffIDStrのSET
	public void SetStaffID(String staffID)
	{
		if (staffID != null)
		{
			this.staffIDStr = staffID;
		}
	}
	// staffIDStrのGET
	public String GetStaffID()
	{
		return this.staffIDStr;
	}
	//
	// hospitalIDStrのSET
	public void SetHospitalID(String hospitalID)
	{
		if (hospitalID != null)
		{
			this.hospitalIDStr = hospitalID;
		}
	}
	// hospitalIDStrのGET
	public String GetHospitalID()
	{
		return this.hospitalIDStr;
	}
	//
	// syokuinKbnStrのSET
	public void SetSyokuinKbn(String syokuinKbn)
	{
		if (syokuinKbn != null)
		{
			this.syokuinKbnStr = syokuinKbn;
		}
	}
	// syokuinKbnStrのGET
	public String GetSyokuinKbn()
	{
		return this.syokuinKbnStr;
	}
	//
	// attributeIntのSET
	public void SetAttribute(int attribute)
	{
		this.attributeInt = attribute;
	}
	// attributeIntのGET
	public int GetAttribute()
	{
		return this.attributeInt;
	}
	//
	// showorderIntのSET
	public void SetShoworder(int showorder)
	{
		this.showorderInt = showorder;
	}
	// showorderIntのGET
	public int GetShoworder()
	{
		return this.showorderInt;
	}
}
