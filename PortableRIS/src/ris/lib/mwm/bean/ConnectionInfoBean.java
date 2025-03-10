package ris.lib.mwm.bean;

/// <summary>
/// コネクション情報
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2008.09.02	: 082898 / H.Orikasa	: original
///
/// </summary>
public class ConnectionInfoBean
{
	// private members
	private String providerStr	= "";
	private String serviceStr	= "";
	private String userStr		= "";
	private String passwordStr	= "";

	public ConnectionInfoBean()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ConnectionInfoBean]");
		strBuild.append("PROVIDER="	+ providerStr	+ ";");
		strBuild.append("SERVICE="	+ serviceStr	+ ";");
		strBuild.append("USER="		+ userStr		+ ";");
		strBuild.append("PASSWORD="	+ passwordStr	+ ";");

		return strBuild.toString();
	}
//
	// providerStrのSET
	public void SetProvider( String provider )
	{
		if( provider != null )
		{
			this.providerStr = provider;
		}
	}
	// providerStrのGET
	public String GetProvider()
	{
		return this.providerStr;
	}
//
	// serviceStrのSET
	public void SetService( String service )
	{
		if( service != null )
		{
			this.serviceStr = service;
		}
	}
	// serviceStrのGET
	public String GetService()
	{
		return this.serviceStr;
	}
//
	// userStrのSET
	public void SetUser( String user )
	{
		if( user != null )
		{
			this.userStr = user;
		}
	}
	// userStrのGET
	public String GetUser()
	{
		return this.userStr;
	}
//
	// passwordStrのSET
	public void SetPassword( String password )
	{
		if( password != null )
		{
			this.passwordStr = password;
		}
	}
	// passwordStrのGET
	public String GetPassword()
	{
		return this.passwordStr;
	}
}
