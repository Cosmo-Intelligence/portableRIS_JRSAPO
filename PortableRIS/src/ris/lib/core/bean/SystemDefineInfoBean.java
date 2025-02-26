package ris.lib.core.bean;

/// <summary>
/// システム情報クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.04.13	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class SystemDefineInfoBean
{
	// private members
	private String curServerTypeStr       = null;
	private String sqDbUserIDStr          = null;
	private String sqDbPasswordStr        = null;
	private String sqDbDriverStr          = null;
	private String sqDbSIDStr             = null;
	private String sqDbIPAddressStr       = null;
	private String studyInstUIDStr        = null;
	private String kanjaIDDigitCountStr   = null;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public SystemDefineInfoBean()
	{
		//
	}

	/// <summary>
	/// 現在の動作モードを取得する
	/// </summary>
	/// <returns>現在の動作モード</returns>
	public String GetCurServerType()
	{
		return this.curServerTypeStr;
	}
	/// <summary>
	/// 現在の動作モードを設定する
	/// </summary>
	/// <param name="curServerTypeStr">現在の動作モード</param>
	public void SetCurServerType( String curServerTypeStr )
	{
		if( curServerTypeStr != null )
		{
			this.curServerTypeStr = curServerTypeStr;
		}
	}

	/// <summary>
	/// ShadeQuest接続用のDBユーザIDの取得
	/// </summary>
	/// <returns>ShadeQuest接続用のDBユーザID</returns>
	public String GetSQDBUserID()
	{
		return this.sqDbUserIDStr;
	}
	/// <summary>
	/// ShadeQuest接続用のDBユーザIDの設定
	/// </summary>
	/// <param name="sqDbUserIDStr">ShadeQuest接続用のDBユーザID</param>
	public void SetSQDBUserID( String sqDbUserIDStr )
	{
		if( sqDbUserIDStr != null )
		{
			this.sqDbUserIDStr = sqDbUserIDStr;
		}
	}

	/// <summary>
	/// ShadeQuest接続用のDBパスワードの取得
	/// </summary>
	/// <returns>ShadeQuest接続用のDBパスワード</returns>
	public String GetSQDBPassword()
	{
		return this.sqDbPasswordStr;
	}
	/// <summary>
	/// ShadeQuest接続用のDBパスワードの設定
	/// </summary>
	/// <param name="sqDbPasswordStr">ShadeQuest接続用のDBパスワード</param>
	public void SetSQDBPassword( String sqDbPasswordStr )
	{
		if( sqDbPasswordStr != null )
		{
			this.sqDbPasswordStr = sqDbPasswordStr;
		}
	}

	/// <summary>
	/// ShadeQuest接続用のDBドライバの取得
	/// </summary>
	/// <returns>ShadeQuest接続用のDBドライバ</returns>
	public String GetSQDBDriver()
	{
		return this.sqDbDriverStr;
	}
	/// <summary>
	/// ShadeQuest接続用のDBドライバの設定
	/// </summary>
	/// <param name="sqDbDriverStr">ShadeQuest接続用のDBドライバ</param>
	public void SetSQDBDriver( String sqDbDriverStr )
	{
		if( sqDbDriverStr != null )
		{
			this.sqDbDriverStr = sqDbDriverStr;
		}
	}

	/// <summary>
	/// ShadeQuest接続用のSIDの取得
	/// </summary>
	/// <returns>ShadeQuest接続用のSID</returns>
	public String GetSQDBSID()
	{
		return this.sqDbSIDStr;
	}
	/// <summary>
	/// ShadeQuest接続用のSIDの設定
	/// </summary>
	/// <param name="sqDbSIDStr">ShadeQuest接続用のSID</param>
	public void SetSQDBSID( String sqDbSIDStr )
	{
		if( sqDbSIDStr != null )
		{
			this.sqDbSIDStr = sqDbSIDStr;
		}
	}

	/// <summary>
	/// ShadeQuest接続用のIPアドレスの取得
	/// </summary>
	/// <returns>ShadeQuest接続用のIPアドレス</returns>
	public String GetSQDBIPAddress()
	{
		return this.sqDbIPAddressStr;
	}
	/// <summary>
	/// ShadeQuest接続用のIPアドレスの設定
	/// </summary>
	/// <param name="sqDbIPAddressStr">ShadeQuest接続用のIPアドレス</param>
	public void SetSQDBIPAddress( String sqDbIPAddressStr )
	{
		if( sqDbIPAddressStr != null )
		{
			this.sqDbIPAddressStr = sqDbIPAddressStr;
		}
	}

	/// <summary>
	/// StudyInstanceUIDを取得
	/// </summary>
	/// <returns>StudyInstanceUID</returns>
	public String GetStudyInstanceUID()
	{
		return this.studyInstUIDStr;
	}
	/// <summary>
	/// StudyInstanceUIDを設定
	/// </summary>
	/// <param name="studyInstUIDStr">StudyInstanceUID</param>
	public void SetStudyInstanceUID( String studyInstUIDStr )
	{
		if( studyInstUIDStr != null )
		{
			this.studyInstUIDStr = studyInstUIDStr;
		}
	}

	/// <summary>
	/// 患者ID桁数の取得
	/// </summary>
	/// <returns>患者ID桁数</returns>
	public String GetKanjaIDDigitCount()
	{
		return this.kanjaIDDigitCountStr;
	}
	/// <summary>
	/// 患者ID桁数の設定
	/// </summary>
	/// <param name="kanjaIDDigitCountStr">患者ID桁数</param>
	public void SetKanjaIDDigitCount( String kanjaIDDigitCountStr )
	{
		if( kanjaIDDigitCountStr != null )
		{
			Integer tempKanjaIDDigitCountInt = 0;

			try
			{
				tempKanjaIDDigitCountInt = Integer.parseInt(kanjaIDDigitCountStr);
			}
			catch (Exception e)
			{
			}

			if( tempKanjaIDDigitCountInt > 0 )
			{
                this.kanjaIDDigitCountStr = tempKanjaIDDigitCountInt.toString();
			}
		}
	}
}
