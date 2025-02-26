package ris.lib.core.bean;

/// <summary>
/// システム共通パラメータクラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class CommonParameterBean
{
	// private members
	private String hospitalIDStr			= null;
	private String loginDelaySecStr			= null;
	private String passwordChangePageURLStr = null;
	private String defaultAutoLogoutMinStr	= null;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public CommonParameterBean()
	{
		//
	}

	/// <summary>
	/// HospitalIDの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>HospitalID</returns>
	public String GetHospitalIDStr()
	{
		return this.hospitalIDStr;
	}

	/// <summary>
	/// HospitalIDの設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="hospitalIDStr">HospitalID</param>
	public void SetHospitalIDStr( String hospitalIDStr )
	{
		if( hospitalIDStr != null )
		{
			this.hospitalIDStr = hospitalIDStr;
		}
	}

	/// <summary>
	/// パスワード変更ページのURLを取得する
	/// </summary>
	/// <returns></returns>
	public String GetPasswordChangePageURL()
	{
		return this.passwordChangePageURLStr;
	}

	/// <summary>
	/// パスワード変更ページのURLを設定する
	/// </summary>
	/// <param name="passwordChangePageURL">パスワード変更ページ</param>
	public void SetPasswordChangePageURL( String passwordChangePageURL )
	{
		if( passwordChangePageURL != null )
		{
			this.passwordChangePageURLStr = passwordChangePageURL;
		}
	}

	/// <summary>
	/// デフォルトオートログアウト時間（分）の取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>デフォルトオートログアウト時間（分）</returns>
	public String GetDefaultAutoLogoutMinStr()
	{
		return this.defaultAutoLogoutMinStr;
	}

	/// <summary>
	/// デフォルトオートログアウト時間（分）の設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="defaultAutoLogoutMinStr">デフォルトオートログアウト時間（分）</param>
	public void SetDefaultAutoLogoutMinStr( String defaultAutoLogoutMinStr )
	{
		if( defaultAutoLogoutMinStr != null )
		{
			Integer defaultAutoLogoutMinInt = 0;

			try
			{
				defaultAutoLogoutMinInt = Integer.parseInt(defaultAutoLogoutMinStr);
			}
			catch (Exception e)
			{
			}

			if( defaultAutoLogoutMinInt > 0 )
			{
				this.defaultAutoLogoutMinStr = defaultAutoLogoutMinInt.toString();
			}
		}
	}

	/// <summary>
	/// ログイン遅延時間（秒）の取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>ログイン遅延時間（秒）</returns>
	public String GetLoginDelayTimeSecStr()
	{
		return this.loginDelaySecStr;
	}

	/// <summary>
	/// ログイン遅延時間（秒）の設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="loginDelayTimeSecStr">ログイン遅延時間（秒）</param>
	public void SetLoginDelayTimeSecStr( String loginDelayTimeSecStr )
	{
		if( loginDelayTimeSecStr != null )
		{
			Integer loginDelayTimeSecInt = 0;

			try
			{
				loginDelayTimeSecInt = Integer.parseInt(loginDelayTimeSecStr);
			}
			catch (Exception e)
			{

			}

			if( loginDelayTimeSecInt > 0 )
			{
				this.loginDelaySecStr = loginDelayTimeSecInt.toString();
			}
		}
	}
}
