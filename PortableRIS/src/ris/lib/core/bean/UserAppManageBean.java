package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.lib.core.Configuration;
import ris.portable.common.Const;

/// <summary>
/// UserAppManageBean の概要の説明です。
/// </summary>
public class UserAppManageBean
{
    // private members
    private String userIDStr		= "";												//UserAppManage.USERID
    private String hospitalIDStr	= Configuration.GetInstance().GetHospitalIDStr();	//UserAppManage.HOSPITALID
    private String appCodeStr		= Configuration.RIS_APPCODE;						//UserAppManage.APPCODE
    private String licenseToUse		= "";												//UserAppManage.LICENCETOUSE
    private String myAttrIDStr		= "";												//UserAppManage.MYATTRID
    private Timestamp updateTimestamp = Const.TIMESTAMP_MINVALUE;								//UserAppManage.UPDATEDATETIME
    //
    public UserAppManageBean()
    {
        //
    }
    //
    // userIDStrのSET
    public void SetUserID(String userID)
    {
        if (userID != null)
        {
            this.userIDStr = userID;
        }
    }
    // userIDStrのGET
    public String GetUserID()
    {
        return this.userIDStr;
    }
    //
    // hospitalIDStrのGET
    public String GetHospitalID()
    {
        return this.hospitalIDStr;
    }
    //
    // appCodeStrのGET
    public String GetAppCode()
    {
        return this.appCodeStr;
    }
    //
    // licenseToUseのSET
    public void SetLicenseToUse(String license)
    {
        if (license != null)
        {
            this.licenseToUse = license;
        }
    }
    // licenseToUseのGET
    public String GetLicenseToUse()
    {
        return this.licenseToUse;
    }
    //
    // myAttrIDStrのSET
    public void SetMyAttrID(String attrID)
    {
        if (attrID != null)
        {
            this.myAttrIDStr = attrID;
        }
    }
    // myAttrIDStrのGET
    public String GetMyAttrID()
    {
        return this.myAttrIDStr;
    }
    //
    // updateTimestampのSET
    public void SetUpdateTimestamp(Timestamp updateDT)
    {
        if (!Const.TIMESTAMP_MINVALUE.equals(updateDT))
        {
            this.updateTimestamp = updateDT;
        }
    }
    // updateTimestampのGET
    public Timestamp GetUpdateTimestamp()
    {
        return this.updateTimestamp;
    }
}
