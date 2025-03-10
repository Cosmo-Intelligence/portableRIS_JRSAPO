package ris.lib.core.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.portable.common.Const;

/// <summary>
///
/// UserManageテーブル情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.23	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class UserAccountBean
{
	private boolean loginFlagBool			= false;
	private String ipAddressStr				= "";
	private String userIDStr				= "";
	private String userNameStr				= "";
	private String passwordStr				= "";
	private String licenseToUseStr			= "0";
	private String userValidityFlagStr		= "0";
	private String expiryFlagStr			= "1";
	private String warningFlagStr			= "";
	private String passwordFlagStr			= "0";
	private String appCodeStr				= "";
	private int autoLogoutSecInt			= Integer.parseInt(Configuration.GetInstance().GetDefaultAutoLogoutSecStr()); // 分
	private String loginErrorMsgStr			= "";
	private String staffIDStr				= "";
	private Timestamp expiryTimestamp		= Const.TIMESTAMP_MINVALUE;
	private Timestamp warningTimestamp		= Const.TIMESTAMP_MINVALUE;
    private ArrayList comprtrnceArrayList	= new ArrayList();

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public UserAccountBean()
	{
        ipAddressStr = Configuration.GetInstance().GetIPAddress();
	}
//
	public void SetLoginFlag( boolean flag )
	{
		loginFlagBool = flag;
	}
	public boolean GetLoginFlag()
	{
		return this.loginFlagBool;
	}
//
	public void SetUserID( String userIDStr )
	{
		if( userIDStr != null )
		{
			this.userIDStr = userIDStr;
		}
	}
	public String GetUserID()
	{
		return this.userIDStr;
	}
//
	public void SetUserName( String userNameStr )
	{
		if( userNameStr != null )
		{
			this.userNameStr = userNameStr;
		}
	}
	public String GetUserName()
	{
		return this.userNameStr;
	}
//
	public void SetPassword( String passwordStr )
	{
		if( passwordStr != null )
		{
			this.passwordStr = passwordStr;
		}
	}
	public String GetPassword()
	{
		return this.passwordStr;
	}
//
	public String GetIPAddress()
	{
		return this.ipAddressStr;
	}
//
	public void SetLicenseToUse( String licenseToUseStr )
	{
		if( licenseToUseStr != null )
		{
			if( "0".equals(licenseToUseStr) || "1".equals(licenseToUseStr) || "2".equals(licenseToUseStr) )
			{
				this.licenseToUseStr = licenseToUseStr;
			}
		}
	}
	public String GetLicenseToUse()
	{
		return this.licenseToUseStr;
	}
//
	public void SetUserValidityFlag( String userValidityFlagStr )
	{
		if( userValidityFlagStr != null )
		{
			this.userValidityFlagStr = userValidityFlagStr;
		}
	}
	public String GetUserValidityFlag()
	{
		return this.userValidityFlagStr;
	}
//
	public void SetExpiryFlag( String expiryFlagStr )
	{
		if( expiryFlagStr != null )
		{
			this.expiryFlagStr = expiryFlagStr;
		}
	}
	public String GetExpiryFlag()
	{
		return this.expiryFlagStr;
	}
//
	public void SetWarningFlag( String warningFlagStr )
	{
		if( warningFlagStr != null )
		{
			this.warningFlagStr = warningFlagStr;
		}
	}
	public String GetWarningFlag()
	{
		return this.warningFlagStr;
	}
//
	public void SetPasswordFlag( String passwordFlagStr )
	{
		if( passwordFlagStr != null )
		{
			this.passwordFlagStr = passwordFlagStr;
		}
	}
	public String GetPasswordFlag()
	{
		return this.passwordFlagStr;
	}
//
	public void SetAutoLogoutTime( int autoLogoutMinuteInt )
	{
		if( autoLogoutMinuteInt > 0 )
		{
			this.autoLogoutSecInt = autoLogoutMinuteInt * 60;
		}
	}
	public int GetAutoLogoutTime()
	{
		return this.autoLogoutSecInt;
	}
//
	public String GetLoginErrorMsg()
	{
		return this.loginErrorMsgStr;
	}
	public void SetLoginErrorMsg( String loginErrorMsgStr )
	{
		if( loginErrorMsgStr != null )
		{
			this.loginErrorMsgStr = loginErrorMsgStr;
		}
	}
//
	public void SetAppCode( String appCode )
	{
		if( appCode != null )
		{
			this.appCodeStr = appCode;
		}
	}
	public String GetAppCode()
	{
		return this.appCodeStr;
	}
//
	public void SetStaffID(String staffID)
	{
		if (staffID != null)
		{
			this.staffIDStr = staffID;
		}
	}
	public String GetStaffID()
	{
		return this.staffIDStr;
	}
//
	public void SetExpiryDate(Timestamp expiryDate)
	{
		this.expiryTimestamp = expiryDate;
	}
	public Timestamp GetExpiryDate()
	{
		return this.expiryTimestamp;
	}
//
	public void SetWarningDate(Timestamp warningDate)
	{
		this.warningTimestamp = warningDate;
	}
	public Timestamp GetWarningDate()
	{
		return this.warningTimestamp;
	}
//
    public void SetComprtrnceArrayList(ArrayList m_comprtrnceArrayList)
    {
        if (m_comprtrnceArrayList != null)
        {
            this.comprtrnceArrayList = m_comprtrnceArrayList;
        }
    }
//
	public boolean IsComprtrnceEnabled()
	{
		boolean retBool = false;
		if (this.comprtrnceArrayList.size() > 0)
		{
			retBool = true;
		}
		return retBool;
	}
	//
    /// <summary>
    /// 権限の判定
    /// </summary>
    /// <param name="idStr">権限ID</param>
    /// <returns></returns>
    public boolean CheckCompetence(String idStr)
    {
        boolean comprtrnceBool = false;

        if (StringUtils.isEmpty(idStr)) {
        	return comprtrnceBool;
        }

        for (int i = 0; i < comprtrnceArrayList.size(); i++)
        {
            if (idStr.equals(comprtrnceArrayList.get(i).toString()))
            {
                return true;
            }
        }
        return comprtrnceBool;
    }
}
