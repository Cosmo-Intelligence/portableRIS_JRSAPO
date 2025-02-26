package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.lib.core.Configuration;
import ris.portable.common.Const;

/// <summary>
/// UserManageBean の概要の説明です。
/// </summary>
public class UserManageBean
{
    // private members
    private String userIDStr				= "";												//UserManage.USERID
    private String hospitalIDStr			= Configuration.GetInstance().GetHospitalIDStr();	//UserManage.HOSPITALID
    private String passwordStr				= "";												//UserManage.PASSWORD
    private String userNameStr				= "";												//UserManage.USERNAME
    private String userNameEngStr			= "";												//UserManage.USERNAMEENG
    private Timestamp pwdExpiryTimestamp	= Const.TIMESTAMP_MINVALUE; 						//UserManage.PASSWORDEXPIRYDATE
    private Timestamp pwdWarningTimestamp	= Const.TIMESTAMP_MINVALUE; 						//UserManage.PASSWORDWARNINGDATE
    private String validityFlgStr			= "";												//UserManage.USERIDVALIDITYFLAG
    private Timestamp updateTimestamp		= Const.TIMESTAMP_MINVALUE;							//UserManage.UPDATEDATETIME
    //
    public UserManageBean()
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
    // passwordStrのSET
    public void SetPassword(String password)
    {
        if (password != null)
        {
            this.passwordStr = password;
        }
    }
    // passwordStrのGET
    public String GetPassword()
    {
        return this.passwordStr;
    }
    //
    // userNameStrのSET
    public void SetUserName(String userName)
    {
        if (userName != null)
        {
            this.userNameStr = userName;
        }
    }
    // userNameStrのGET
    public String GetUserName()
    {
        return this.userNameStr;
    }
    //
    // userNameEngStrのSET
    public void SetUserNameEng(String userNameEng)
    {
        if (userNameEng != null)
        {
            this.userNameEngStr = userNameEng;
        }
    }
    // userNameEngStrのGET
    public String GetUserNameEng()
    {
        return this.userNameEngStr;
    }
    //
    // pwdExpiryTimestampのSET
    public void SetPasswordExpiryDate(Timestamp expiryDate)
    {
        if (!Const.TIMESTAMP_MINVALUE.equals(expiryDate) )
        {
            this.pwdExpiryTimestamp = expiryDate;
        }
    }
    // pwdExpiryTimestampのGET
    public Timestamp GetPasswordExpiryDate()
    {
        return this.pwdExpiryTimestamp;
    }
    //
    // pwdWarningTimestampのSET
    public void SetPasswordWarningDate(Timestamp warningDate)
    {
        if (!Const.TIMESTAMP_MINVALUE.equals(warningDate) )
        {
            this.pwdWarningTimestamp = warningDate;
        }
    }
    // pwdWarningTimestampのGET
    public Timestamp GetPasswordWarningDate()
    {
        return this.pwdWarningTimestamp;
    }
    //
    // validityFlgStrのSET
    public void SetValidityFlg(String flg)
    {
        if (flg != null)
        {
            this.validityFlgStr = flg;
        }
    }
    // validityFlgStrのGET
    public String GetValidityFlg()
    {
        return this.validityFlgStr;
    }
    //
    // updateTimestampのSET
    public void SetUpdateTimestamp(Timestamp updateDT)
    {
        if (!Const.TIMESTAMP_MINVALUE.equals(updateDT) )
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
