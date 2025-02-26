package ris.lib.core.util;

/// <summary>
///
/// ユーザ検索条件クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class UserSearchParameter
{
	// private members
	private String idStr			= "";	//Ris.UsersubView.ID
	private String loginIDStr		= "";	//Ris.UsersubView.LOGINID
	private String staffIDStr		= "";	//Ris.UsersubView.STAFFID
	private String attributeStr		= "";	//Ris.UsersubView.ATTRIBUTE
	private String syokuinKbnStr	= "";	//Ris.UsersubView.SYOKUIN_KBN
	// 2010.07.30 Add T.Ootsuka Start
	private String userNameStr		= "";	//Ris.UsersubView.USERNAME
	// 2010.07.30 Add T.Ootsuka End
	// 2010.09.21 Add K.Shinohara Start
	private String userIDValidityFlagStr	= "";	//Ris.UsersubView.USERIDVALIDITYFLAG
	// 2010.09.21 Add K.Shinohara End

	public UserSearchParameter()
	{
		//
	}
//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[UserSearchParameter]");
		strBuild.append("ID="			+ idStr				+ ";");
		strBuild.append("LOGINID="		+ loginIDStr		+ ";");
		strBuild.append("STAFFID="		+ staffIDStr		+ ";");
		strBuild.append("ATTRIBUTE="	+ attributeStr		+ ";");
		strBuild.append("SYOKUIN_KBN="	+ syokuinKbnStr		+ ";");
		// 2010.07.30 Add T.Ootsuka Start
		strBuild.append("USERNAME="		+ userNameStr		+ ";");
		// 2010.07.30 Add T.Ootsuka End
		// 2010.09.21 Add K.Shinohara Start
		strBuild.append("USERIDVALIDITYFLAG="	+ userIDValidityFlagStr	+ ";");
		// 2010.09.21 Add K.Shinohara End

		return strBuild.toString();
	}
//
	/// <summary>
	/// 検索条件のIDを取得する
	/// </summary>
	/// <returns>id</returns>
	public String GetID()
	{
		return this.idStr;
	}

	/// <summary>
	/// 検索条件のIDを設定する
	/// </summary>
	/// <param name="id">ID</param>
	public void SetID(String id)
	{
		if (id != null)
		{
			this.idStr = id;
		}
	}
//
	/// <summary>
	/// 検索条件のloginIDを取得する
	/// </summary>
	/// <returns>LOGINID</returns>
	public String GetLoginID()
	{
		return this.loginIDStr;
	}

	/// <summary>
	/// 検索条件のLOGINIDを設定する
	/// </summary>
	/// <param name="loginID">LOGINID</param>
	public void SetLoginID(String loginID)
	{
		if (loginID != null)
		{
			this.loginIDStr = loginID;
		}
	}
//
	/// <summary>
	/// 検索条件のSTAFFIDを取得する
	/// </summary>
	/// <returns>STAFFID</returns>
	public String GetStaffID()
	{
		return this.staffIDStr;
	}

	/// <summary>
	/// 検索条件のSTAFFIDを設定する
	/// </summary>
	/// <param name="staffID">STAFFID</param>
	public void SetStaffID(String staffID)
	{
		if (staffID != null)
		{
			this.staffIDStr = staffID;
		}
	}
//
	/// <summary>
	/// 検索条件のATTRIBUTEを取得する
	/// </summary>
	/// <returns>ATTRIBUTE</returns>
	public String GetAttribute()
	{
		return this.attributeStr;
	}

	/// <summary>
	/// 検索条件のATTRIBUTEを設定する
	/// </summary>
	/// <param name="attribute">ATTRIBUTE</param>
	public void SetAttribute(String attribute)
	{
		if (attribute != null)
		{
			this.attributeStr = attribute;
		}
	}
//
	/// <summary>
	/// 検索条件のSYOKUIN_KBNを取得する
	/// </summary>
	/// <returns>SYOKUIN_KBN</returns>
	public String GetSyokuinKbn()
	{
		return this.syokuinKbnStr;
	}

	/// <summary>
	/// 検索条件のSYOKUIN_KBNを設定する
	/// </summary>
	/// <param name="syokuinKbn">SYOKUIN_KBN</param>
	public void SetSyokuinKbn(String syokuinKbn)
	{
		if (syokuinKbn != null)
		{
			this.syokuinKbnStr = syokuinKbn;
		}
	}

	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// 検索条件のUSERNAMEを取得する
	/// </summary>
	/// <returns>ユーザ名</returns>
	public String GetUserName()
	{
		return this.userNameStr;
	}

	/// <summary>
	/// 検索条件のUSERNAMEを設定する
	/// </summary>
	/// <param name="userName">ユーザID</param>
	public void SetUserName(String userName)
	{
		if (userName != null)
		{
			this.userNameStr = userName;
		}
	}
	// 2010.07.30 Add T.Ootsuka End

	// 2010.09.21 Add K.Shinohara Start
	/// <summary>
	/// 検索条件のUSERIDVALIDITYFLAGを取得する
	/// </summary>
	/// <returns>ユーザID使用フラグ</returns>
	public String GetUserIDValidityFlag()
	{
		return this.userIDValidityFlagStr;
	}

	/// <summary>
	/// 検索条件のUSERIDVALIDITYFLAGを設定する
	/// </summary>
	/// <param name="userIDValidityFlag">ユーザID使用フラグ</param>
	public void SetUserIDValidityFlag(String userIDValidityFlag)
	{
		if (userIDValidityFlag != null)
		{
			this.userIDValidityFlagStr = userIDValidityFlag;
		}
	}
	// 2010.09.21 Add K.Shinohara End
}
