package ris.lib.core;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.app.MasterHandler;
import ris.lib.core.bean.SystemParamBean;
import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.database.RisSystemParamTbl;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MasterUtil;
import ris.lib.mwm.bean.ConnectionInfoBean;
import ris.portable.common.Const;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
/// コンフィグクラス の概要の説明です。
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
/// (Rev.)			(Date)          (ID / NAME)             (Comment)
/// V1.00.00		: 2009.02.18    extends 112478 / A.Kobayashi  extends original
/// V1.00.14		: 2009.08.07    extends 112478 / A.Kobayashi  extends プリンタの設定追加
/// V2.01.00		: 2011.08.05    extends 999999 / K.Aizawa     extends NML-CAT3-034 NML-1-X02
/// V2.01.01.13000	: 2011.11.24	: 999999 / NSK_M.Ochiai	: OMH-1-2
///
/// </summary>
public final class Configuration
{
	public static Log logger = LogFactory.getLog(Configuration.class);

	public static String RIS_APPCODE = "RIS";
	public static Configuration instance = new Configuration();
	private static Object syncRoot = new Object();// ロックオブジェクト

	//System.Configuration.AppSettingsReader configurationAppSettings = null;

	//画面管理クラス
	//private WindowController windowControllerObj = null;

	//コア管理クラス
	private CoreController coreControllerObj = null;

	//端末設定情報
	private Hashtable termConfData = null;

	//システム情報
	private SystemParamBean systemParamBean = null;

	//ボタン定義
	//ArrayList menuButtonList = null;

	//カレントサーバタイプ
	String currentServerNameString = "";

	private String dbMaxResultsString = "500";
	//RISサーバ接続変数(RR)
	private String risDbSourceString		= "";
	private String risDbUserString			= Const.RISUSER;
	private String risDbPasswordString		= "";

	//RTRISサーバ接続変数
	private String rtrisDbSourceString		= "";
	private String rtrisDbUserString		= "";
	private String rtrisDbPasswordString	= "";
	private String rrisDbSourceString		= "";
	private String rrisDbUserString			= "";
	private String rrisDbPasswordString		= "";

	//認証サーバ接続変数
	private String authenticationDbSourceStr	= null;
	private String authenticationDbUserStr		= null;
	private String authenticationDbPasswordStr	= null;

	//監査証跡サーバ接続変数(Audittrail)
	private String audittrailDbSourceString		= "";
	private String audittrailDbUserString		= "";
	private String audittrailDbPasswordString	= "";

	//監査証跡変数
	private String hospitalIDStr			= null;		// 病院ID
	private String passwordChangePageURLStr = null;		// パスワード変更ページURL
	private String defaultAutoLogoutSecStr	= "3600";	// デフォルトオートログアウト時間（秒）
	private String loginDelayTimeSecStr		= null;		// ログイン遅延時間（秒）

	//スタータス表示略称変数
	private String statusMsg0Str 		= "";		//未受
	private String statusMsg1Str 		= "";		//遅刻
	private String statusMsg2Str 		= "";		//呼出
	private String statusMsg3Str 		= "";		//受済
	private String statusMsg4Str 		= "";		//検中
	private String statusMsg5Str 		= "";		//保留
	private String statusMsg6Str 		= "";		//再呼
	private String statusMsg7Str 		= "";		//再受
	private String statusMsg8Str 		= "";		//検済
	private String statusMsg9Str 		= "";		//中止
	private String statusMsg10Str		= "";		//削除	// 2011.03.18 Add Yk.Suzuki@CIJ A0005

	//スタータス色変数
	private String statusColor0Str		= "0";		//未受
	private String statusColor1Str		= "0";		//遅刻
	private String statusColor2Str		= "0";		//呼出
	private String statusColor3Str		= "0";		//受済
	private String statusColor4Str		= "0";		//検中
	private String statusColor5Str		= "0";		//保留
	private String statusColor6Str		= "0";		//再呼
	private String statusColor7Str		= "0";		//再受
	private String statusColor8Str		= "0";		//検済
	private String statusColor9Str		= "0";		//中止
	private String statusColor10Str		= "0";		//削除	// 2011.03.18 Add Yk.Suzuki@CIJ A0005

	//スタータス背景色変数
	private String statusColorBk0Str	= "0";		//未受
	private String statusColorBk1Str	= "0";		//遅刻
	private String statusColorBk2Str	= "0";		//呼出
	private String statusColorBk3Str	= "0";		//受済
	private String statusColorBk4Str	= "0";		//検中
	private String statusColorBk5Str	= "0";		//保留
	private String statusColorBk6Str	= "0";		//再呼
	private String statusColorBk7Str	= "0";		//再受
	private String statusColorBk8Str	= "0";		//検済
	private String statusColorBk9Str	= "0";		//中止
	private String statusColorBk10Str	= "0";		//削除	// 2011.03.18 Add Yk.Suzuki@CIJ A0005

	private String printerDriverStr = "";		//プリンタドライバ情報

	//SYSTEMDEFINE情報
	private String licensenoStr				= "";
	private String kenzouFlgStr				= "0";
	private String hsplinkflagStr			= "";		//未使用
	private String confirmTypeStr			= "";
	private String sq_hspidStr				= "";
	private String buisetFlgStr				= "0";
	private String markercharacterStr		= "|";
	private String templatemarkercharStr	= "/";
	private String childlowlimitStr			= "";
	private String childhighlimitStr		= "";
	private String babylowlimitStr			= "";
	private String babyhighlimitStr			= "";
	private String risordersendflagStr		= "1";
	private String risordersendflag2Str		= "1";
	private String imageurlflagStr			= "0";
	private String imageurlStr				= "";
	private String imguserlabelStr			= "";
	private String imgpasswordlabelStr		= "";
	private String imgpidlabelStr			= "";
	private String imgdatelabelStr			= "";
	private String imgmodalitylabelStr		= "";
	private String imgacnolabelStr			= "";
	private String reporturlflagStr			= "0";
	private String reporturlStr				= "";
	private String rptuserlabelStr			= "";
	private String rptpasswordlabelStr		= "";
	private String rptpidlabelStr			= "";
	private String rptdatelabelStr			= "";
	private String rptmodalitylabelStr		= "";
	private String rptacnolabelStr			= "";
	private String schemaurlflagStr			= "0";
	private String extendorderuseflagStr	= "0";
	private String schemaurlStr				= "";
	private String schuserlabelStr			= "";
	private String schpasswordlabelStr		= "";
	private String schpidlabelStr			= "";
	private String schedatelabelStr			= "";
	private String schmodalitylabelStr		= "";
	private String schordernolabelStr		= "";
	private String schacnolabelStr			= "";		//未使用
	private String usecrkindStr				= "";		//未使用
	private String auditflagStr				= "1";
	private String systemidStr				= "";
	private String kenzoufixStr				= "0";
	private String kenzoupassdlgStr			= "2";
	private String kenzouconnectwaitStr		= "10";
	private String kenzoushowwaitStr		= "3600";
	private String mppsKensaFlgStr			= "";
	// 2010.10.18 Add K.Shinohara Start
	private String mppsSatueiFlgStr			= "";
	// 2010.10.18 Add K.Shinohara End
	private String mppsFlgStr				= "";
	private String mppsUserStr				= "";
	private String mppsPasswordStr			= "";
	private String mppsServiceNameStr		= "";
	private String mppsIntervalStr			= "";
	private String mppsRetryCountStr		= "";
	private String mppsFilmSetTypeStr		= "";
	private String mppsFixStr				= "";
	private String mppsPasswdDlgStr			= "";
	private String mppsUnitFlgStr			= "";
	private String mwmUserStr				= "";
	private String mwmPasswordStr			= "";
	private String mwmServiceNameStr		= "";
	private String mwmUketukeEntryStr		= "0";
	private String mwmReceiptEntryStr		= "0";
	// 2010.07.30 Add Y.Shibata Start
	private String todayZoueizaiIdstr	    = "";
	private String todayZoueizaiKbnstr	    = "";
	// 2010.07.30 Add Y.Shibata End

	//排他変数
	private String ipAddressStr = ""; //CHECKOUT_INFO.IP_ADDRESS

	//現在のログインユーザ情報
	private UserAccountBean userBean = null;

	//端末情報
	private String terminal = null;

	//端末定義設定(TERMINALDEFINE)
	private TerminalInfoBean terminalInfoBean = null;

	//帳票情報Hash
	//private Hashtable printInfoHash = null;

	private boolean kensaMppsNormalBool	= false;	//検査系MPPSExposureDose取得SQL標準タイプ
	private boolean examItemBool			= false;	//管理項目タイプ

	// 2010.11.01 Add K.Shinohara Start
	private boolean forceActiveFlg			= false;	//ForceActive使用フラグ
	// 2010.11.01 Add K.Shinohara End

	// 2011.05.06 Add K.Shinohara Start A0027
	private boolean lumpMWMKikiModeFlg		= false;	//V1系一括MWM機器取得フラグ
	// 2011.05.06 Add K.Shinohara End

	//前回実施者の引き継ぎ
	private String gisiIDStr			= null;
	private String kangosiIDStr			= null;
	private String kensaDoctorIDStr		= null;
	private String enforceDoctorIDStr	= null;

	//帳票プレビュー設定
	//private Hashtable previewAllHash = new Hashtable();

	// 2010.07.30 Add T.Ootsuka Start
	// チーム登録処理使用フラグ
	private boolean teamInfoDataFlg		= false;
	// 2010.07.30 Add T.Ootsuka End

	//監査証跡モード
	private boolean auditTrailModeBool = false;

	//表示一覧IDリスト
	//private ArrayList showListIDList = new ArrayList();

	// 2011.06.24 Add K.Shinohara Start A0060
	private boolean unKnownDateBool = false;
	private String unKnownDateString = "";
	// 2011.06.24 Add K.Shinohara End

	// 2011.05.23 Add H.Orikasa Start 業務詳細高速化対応
	// SystemParam2のDB情報
	// 初回起動時の情報を保持する
	private DataTable systemParam2Tbl = null;
	// 2011.05.23 Add H.Orikasa End   業務詳細高速化対応

	// 2011.08.16 Mod T.Ootsuka@MERX Start NML-1-X02(問題点修正)
	// 帳票処理使用マスタ
	//private Hashtable mstHashTableForReport		= new Hashtable();
	// コメント
	//// 2011.08.05 Add T.Ootsuka@MERX Start NML-1-X02(問題点修正)
	//// 帳票処理使用マスタ
	//private Hashtable mstHashTableForReport;
	//// 2011.08.05 Add T.Ootsuka@MERX End

	// 2011.08.16 Mod T.Ootsuka@MERX End

	// 2011.08.18 Add T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
	// 部位進捗変換情報
	private Hashtable codeConvertBuiStatus		= new Hashtable();
	// 2011.08.18 Add T.Ootsuka@MERX End

	// 2011.10.24 Add K.Shinohara Start YRH-1-03  OMH-1-08【山田赤十字】
	// 検査終了時の造影剤チェック機能
	private boolean zoueizaiCheckFlg	= false;
	// 2011.10.24 Add K.Shinohara End

	// システムパラメータ２
	// 2011.01.24 Add T.Nishikubo Start KANRO-R-22
	private boolean receiptExtendPatientInfoFlg = false;	// 受付ダイアログ患者情報拡張フラグ
	// 2011.01.24 Add T.Nishikubo End
	private boolean preStsFlg = false;						// プレチェックステータスフラグ		// 2011.01.24 DD.Chinh Add KANRO-R-19
	private boolean orderDeleteFlg = false;				// 削除オーダフラグ // 2011.03.18 Add Yk.Suzuki@CIJ A0005

	private Integer mwmtimeout = 2000; // MWMソケットタイムアウト値
	private String kanaromatext  = ""; // かなローマ変換テキストファイル値

	// 初期化用

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public Configuration ()
	{

	}

	/// <summary>
	/// Configurationクラス（Singleton）のインスタンスを取得する
	/// </summary>
	/// <returns>Configurationクラスのインスタンス</returns>
	static public Configuration GetInstance()
	{
		synchronized(syncRoot)
		{
			if( instance == null )
			{
				instance = new Configuration();
			}
		}
		return instance;
	}

    /// <summary>
    /// RIS用のDBユーザ取得
    /// </summary>
    /// <remarks></remarks>
    /// <returns>カレントサーバ名</returns>
    public String GetRisCurrentServerType()
    {
        return currentServerNameString;
    }

    /// <summary>
    /// RIS用のカレントサーバ名
    /// </summary>
    /// <remarks></remarks>
    /// <param name="currentServerNameString">カレントサーバ名</param>
    public void SetRisCurrentServerType(String m_CurrentServerNameString)
    {
        if (m_CurrentServerNameString != null)
        {
            if (m_CurrentServerNameString.compareTo(CommonString.UNITY_CA_MODE) == 0
                || m_CurrentServerNameString.compareTo(CommonString.UNITY_EMG_CA_MODE) == 0
                || m_CurrentServerNameString.compareTo(CommonString.LOCAL_CA_MODE) == 0)
            {
                this.currentServerNameString = m_CurrentServerNameString;
            }
        }
    }

    /*
	/// <summary>
	/// 画面遷移管理クラスの設定
	/// </summary>
	/// <param name="windowControllerObj">画面遷移管理クラス</param>
	public void SetWindowController( WindowController windowControllerObj )
	{
		if( windowControllerObj != null )
		{
			this.windowControllerObj = windowControllerObj;
		}
	}
	*/

    /*
	/// <summary>
	/// 画面遷移管理クラスの取得
	/// </summary>
	/// <returns>画面遷移管理クラス</returns>
	public WindowController GetWindowController()
	{
		return this.windowControllerObj;
	}
	*/

	/// <summary>
	/// 画面遷移管理クラスの設定
	/// </summary>
	/// <param name="windowControllerObj">画面遷移管理クラス</param>
	public void SetCoreController( CoreController coreControllerObj )
	{
		if( coreControllerObj != null )
		{
			this.coreControllerObj = coreControllerObj;
		}
	}

	/// <summary>
	/// コア管理クラスの取得
	/// </summary>
	/// <returns>画面遷移管理クラス</returns>
	public CoreController GetCoreController()
	{
		return this.coreControllerObj;
	}

	/// <summary>
	/// DB最大取得件数の取得
	/// </summary>
	/// <returns></returns>
	public String GetDbMaxResultsStr()
	{
		return this.dbMaxResultsString;
	}

	/// <summary>
	/// DB最大取得件数の設定
	/// </summary>
	/// <param name="dbMaxResultsString">最大表示件数</param>
	public void SetDbMaxResultsStr( String dbMaxResultsString )
	{
		if( dbMaxResultsString != null )
		{
			this.dbMaxResultsString = dbMaxResultsString;
		}
	}

	/*
	/// <summary>
	/// ConfigurationAppSettingsを生成する
	/// </summary>
	public void CreateConfigurationAppSettings()
	{
		if (this.configurationAppSettings == null)
		{
			this.configurationAppSettings = new System.Configuration.AppSettingsReader();
		}
	}
	*/

	/// <summary>
	/// 端末設定情報の設定
	/// </summary>
	/// <param name="terminalConf">端末設定情報</param>
	public void SetTermConfData(Hashtable terminalConf)
	{
		if (terminalConf != null)
		{
			this.termConfData = terminalConf;
		}
	}

	/// <summary>
	/// 端末設定情報を取得する(文字列)
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>端末設定情報-文字列</returns>
	public String GetTermConfDataString(String key)
	{
		String ret = "";

		try
		{
			if ((this.termConfData != null) && (this.termConfData.containsKey(key)))
			{
				ret = this.termConfData.get(key).toString();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return ret;
	}

	/// <summary>
	/// 端末設定情報を取得する(数値)
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>端末設定情報-数値</returns>
	public int GetTermConfDataInt(String key)
	{
		int ret = -1;

		try
		{
			if ((this.termConfData != null) && (this.termConfData.containsKey(key)))
			{
				if (this.termConfData.get(key).toString().length() > 0)
				{
					ret = Integer.parseInt(this.termConfData.get(key).toString());
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return ret;
	}

	/// <summary>
	/// 端末設定情報を取得する(浮動少数点)
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>端末設定情報-浮動少数点</returns>
	public Float GetTermConfDataFloat(String key)
	{
		float ret = -1;

		try
		{
			if ((this.termConfData != null) && (this.termConfData.containsKey(key)))
			{
				if (this.termConfData.get(key).toString().length() > 0)
				{
					ret = Float.parseFloat(this.termConfData.get(key).toString());
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return ret;
	}



	// DB接続用

	/// <summary>
	/// RIS用DBのSID取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>SID</returns>
	public String GetRisDbSourceStr()
	{
		return risDbSourceString;
	}

	/// <summary>
	/// RIS用DBのSID設定
	/// </summary>
	/// <remarks></remarks>
    /// <param name="risDbSourceString">SID</param>
	public void SetRisDbSourceStr( String risDbSourceString )
	{
		if( risDbSourceString != null )
		{
			this.risDbSourceString = risDbSourceString;
		}
	}

	/// <summary>
	/// RIS用DBのユーザ取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>ユーザ</returns>
	public String GetRisDbUserStr()
	{
		return risDbUserString;
	}

	/// <summary>
	/// RIS用DBのユーザ設定
	/// </summary>
	/// <remarks></remarks>
    /// <param name="risDbUserString">ユーザ</param>
	public void SetRisDbUserStr( String risDbUserString )
	{
		if( risDbUserString != null )
		{
			this.risDbUserString = risDbUserString;
		}
	}

	/// <summary>
	/// RIS用DBのパスワード取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>パスワード</returns>
	public String GetRisDbPasswordStr()
	{
		return risDbPasswordString;
	}

	/// <summary>
	///	RIS用DBのパスワード設定
	/// </summary>
	/// <remarks></remarks>
    /// <param name="risDbPasswordString">パスワード</param>
	public void SetRisDbPasswordStr( String risDbPasswordString )
	{
		if( risDbPasswordString != null )
		{
			this.risDbPasswordString = risDbPasswordString;
		}
	}
//
	/// <summary>
	/// RTRIS用DBのSID取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>SID</returns>
	public String GetRtrisDbSourceStr()
	{
		return rtrisDbSourceString;
	}

	/// <summary>
	/// RTRIS用DBのSID設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="rtrisDbSource">SID</param>
	public void SetRtrisDbSourceStr(String rtrisDbSource)
	{
		if (rtrisDbSource != null)
		{
			this.rtrisDbSourceString = rtrisDbSource;
		}
	}

	/// <summary>
	/// RTRIS用DBのユーザID取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>ユーザID</returns>
	public String GetRtrisDbUserStr()
	{
		return rtrisDbUserString;
	}

	/// <summary>
	/// RTRIS用DBのユーザID設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="rtrisDbUser">ユーザID</param>
	public void SetRtrisDbUserStr(String rtrisDbUser)
	{
		if (rtrisDbUser != null)
		{
			this.rtrisDbUserString = rtrisDbUser;
		}
	}

	/// <summary>
	/// RTRIS用DBのパスワード取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>パスワード</returns>
	public String GetRtrisDbPasswordStr()
	{
		return rtrisDbPasswordString;
	}

	/// <summary>
	/// RTRIS用DBのパスワード設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="rtrisDbPassword">パスワード</param>
	public void SetRtrisDbPasswordStr(String rtrisDbPassword)
	{
		if (rtrisDbPassword != null)
		{
			this.rtrisDbPasswordString = rtrisDbPassword;
		}
	}
//
	/// <summary>
	/// RRIS用DBのSID取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>SID</returns>
	public String GetRrisDbSourceStr()
	{
		return rrisDbSourceString;
	}

	/// <summary>
	/// RRIS用DBのSID設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="rrisDbSource">SID</param>
	public void SetRrisDbSourceStr(String rrisDbSource)
	{
		if (rrisDbSource != null)
		{
			this.rrisDbSourceString = rrisDbSource;
		}
	}

	/// <summary>
	/// RRIS用DBのユーザID取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>ユーザID</returns>
	public String GetRrisDbUserStr()
	{
		return rrisDbUserString;
	}

	/// <summary>
	/// RRIS用DBのユーザID設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="rrisDbUser">ユーザID</param>
	public void SetRrisDbUserStr(String rrisDbUser)
	{
		if (rrisDbUser != null)
		{
			this.rrisDbUserString = rrisDbUser;
		}
	}

	/// <summary>
	/// RRIS用DBのパスワード取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>パスワード</returns>
	public String GetRrisDbPasswordStr()
	{
		return rrisDbPasswordString;
	}

	/// <summary>
	/// RRIS用DBのパスワード設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="rrisDbPassword">パスワード</param>
	public void SetRrisDbPasswordStr(String rrisDbPassword)
	{
		if (rrisDbPassword != null)
		{
			this.rrisDbPasswordString = rrisDbPassword;
		}
	}
//
	/// <summary>
	/// 認証データーベースのSID取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>SID</returns>
	public String GetAuthenticationDbSourceStr()
	{
		return this.authenticationDbSourceStr;
	}

	/// <summary>
	/// 認証データーベースのSID設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="authenticationDbSourceStr">SID</param>
	public void SetAuthenticationDbSourceStr( String authenticationDbSourceStr )
	{
		if( authenticationDbSourceStr != null )
		{
			this.authenticationDbSourceStr = authenticationDbSourceStr;
		}
	}

	/// <summary>
	/// 認証用のDBユーザ取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>ユーザ</returns>
	public String GetAuthenticationDbUserStr()
	{
		return this.authenticationDbUserStr;
	}

	/// <summary>
	/// 認証用のDBユーザ設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="authenticationDbUserStr">ユーザ</param>
	public void SetAuthenticationDbUserStr( String authenticationDbUserStr )
	{
		if( authenticationDbUserStr != null )
		{
			this.authenticationDbUserStr = authenticationDbUserStr;
		}
	}

	/// <summary>
	/// 認証用のDBパスワード取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>パスワード</returns>
	public String GetAuthenticationDbPasswordStr()
	{
		return this.authenticationDbPasswordStr;
	}

	/// <summary>
	/// 認証用のDBパスワード設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="authenticationDbPasswordStr">パスワード</param>
	public void SetAuthenticationDbPasswordStr( String authenticationDbPasswordStr )
	{
		if( authenticationDbPasswordStr != null )
		{
			this.authenticationDbPasswordStr = authenticationDbPasswordStr;
		}
	}
//
	/// <summary>
	/// 監査証跡コアデーターベースのSID取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>SID</returns>
	public String GetAudittrailDbSourceStr()
	{
		return audittrailDbSourceString;
	}

	/// <summary>
	/// 監査証跡コアデーターベースのSID設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="audittrailDbSourceString">SID</param>
	public void SetAudittrailDbSourceStr( String audittrailDbSourceString )
	{
		if( audittrailDbSourceString != null )
		{
			this.audittrailDbSourceString = audittrailDbSourceString;
		}
	}

	/// <summary>
	/// 監査証跡用のDBユーザ取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>ユーザ</returns>
	public String GetAudittrailDbUserStr()
	{
		return audittrailDbUserString;
	}

	/// <summary>
	/// 監査証跡用のDBユーザ設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="audittrailDbUserString">ユーザ</param>
	public void SetAudittrailDbUserStr( String audittrailDbUserString )
	{
		if( audittrailDbUserString != null )
		{
			this.audittrailDbUserString = audittrailDbUserString;
		}
	}

	/// <summary>
	/// 監査証跡用のDBパスワード取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>パスワード</returns>
	public String GetAudittrailDbPasswordStr()
	{
		return audittrailDbPasswordString;
	}

	/// <summary>
	///	監査証跡用のDBパスワード設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="audittrailDbPasswordString">パスワード</param>
	public void SetAudittrailDbPasswordStr( String audittrailDbPasswordString )
	{
		if( audittrailDbPasswordString != null )
		{
			this.audittrailDbPasswordString = audittrailDbPasswordString;
		}
	}


	// 監査証跡用

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
	/// デフォルトオートログアウト時間（秒）の取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns>デフォルトオートログアウト時間（秒）</returns>
	public String GetDefaultAutoLogoutSecStr()
	{
		return this.defaultAutoLogoutSecStr;
	}

	/// <summary>
	/// デフォルトオートログアウト時間（秒）の設定
	/// </summary>
	/// <remarks></remarks>
	/// <param name="defaultAutoLogoutMinStr">デフォルトオートログアウト時間（分）</param>
	public void SetDefaultAutoLogoutSecStr( String defaultAutoLogoutMinStr )
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
				defaultAutoLogoutMinInt = defaultAutoLogoutMinInt*60;
				this.defaultAutoLogoutSecStr = defaultAutoLogoutMinInt.toString();// ここで分を秒に変換
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
		return this.loginDelayTimeSecStr;
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
				this.loginDelayTimeSecStr = loginDelayTimeSecInt.toString();
			}
		}
	}

	/// <summary>
	/// 現在のログインユーザ情報をsetする
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public void SetUserAccountBean( UserAccountBean userBean )
	{
		if( userBean != null )
		{
			this.userBean = userBean;
		}
	}

	/// <summary>
	/// 現在のログインユーザ情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public UserAccountBean GetUserAccountBean()
	{
		return this.userBean;
	}

	/*
	/// <summary>
	/// 帳票情報の設定
	/// </summary>
	/// <param name="hash">帳票情報</param>
	public void SetPrintInfoHash(Hashtable hash)
	{
		this.printInfoHash = hash;
	}
	*/

	/*
	/// <summary>
	/// 帳票情報の取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public Hashtable GetPrintInfoHash()
	{
		return this.printInfoHash;
	}
	*/
	/// <summary>
	/// 検査系MPPSExposureDose取得SQL標準タイプの設定
	/// </summary>
	/// <param name="kensaMppsNormal">検査系MPPSExposureDose取得SQL標準タイプ</param>
	public void SetKensaMppsNormal(boolean kensaMppsNormal)
	{
		this.kensaMppsNormalBool = kensaMppsNormal;
	}

	/// <summary>
	/// 検査系MPPSExposureDose取得SQL標準タイプの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public boolean GetKensaMppsNormal()
	{
		return this.kensaMppsNormalBool;
	}

	/// <summary>
	/// 管理項目タイプの設定
	/// </summary>
	/// <param name="examItem">管理項目タイプ</param>
	public void SetExamItemFlg(boolean examItem)
	{
		this.examItemBool = examItem;
	}

	/// <summary>
	/// 管理項目タイプの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public boolean GetExamItemFlg()
	{
		return this.examItemBool;
	}

	// 2010.11.01 Add K.Shinohara Start
	/// <summary>
	/// ForceActive実施フラグの設定
	/// </summary>
	/// <param name="examItem">ForceActive実施フラグ</param>
	public void SetForceActiveFlg(boolean forceActive)
	{
		this.forceActiveFlg = forceActive;
	}

	/// <summary>
	/// ForceActive実施フラグの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public boolean GetForceActiveFlg()
	{
		return this.forceActiveFlg;
	}
	// 2010.11.01 Add K.Shinohara End

	/// <summary>
	/// 監査証跡モードの設定
	/// </summary>
	/// <param name="auditTrailMode">監査証跡モード</param>
	public void SetAuditTrailMode(boolean auditTrailMode)
	{
		this.auditTrailModeBool = auditTrailMode;
	}

	/// <summary>
	/// 監査証跡モードの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public boolean GetAuditTrailMode()
	{
		return this.auditTrailModeBool;
	}

	// 2011.05.06 Add K.Shinohara Start A0027
	/// <summary>
	/// V1系一括MWM機器取得フラグの設定
	/// </summary>
	/// <param name="lumpMWMKikiMode">V1系一括MWM機器取得フラグ</param>
	public void SetLumpMWMKikiModeFlg(boolean lumpMWMKikiMode)
	{
		this.lumpMWMKikiModeFlg = lumpMWMKikiMode;
	}

	/// <summary>
	/// V1系一括MWM機器取得フラグの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public boolean GetLumpMWMKikiModeFlg()
	{
		return this.lumpMWMKikiModeFlg;
	}
	// 2011.05.06 Add K.Shinohara End

	// 2011.10.24 Add K.Shinohara Start YRH-1-03  OMH-1-08【山田赤十字】
	/// <summary>
	/// 造影剤チェックフラグの設定
	/// </summary>
	/// <param name="zoueizaiCheck">造影剤チェックフラグ</param>
	public void SetZoueizaiCheckFlg(boolean zoueizaiCheck)
	{
		this.zoueizaiCheckFlg = zoueizaiCheck;
	}

	/// <summary>
	/// 造影剤チェックフラグの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public boolean GetZoueizaiCheckFlg()
	{
		return this.zoueizaiCheckFlg;
	}
	// 2011.10.24 Add K.Shinohara End



	// 各DB情報

	/// <summary>
	/// 現在の時刻を取得する(トラン開始、コミット有)
	/// </summary>
	/// <returns></returns>
	public Timestamp GetSysDate(Connection con) throws Exception
	{
		MasterHandler masterHandler = new MasterHandler();
		return masterHandler.GetSysDate(con);
	}

	/*
	/// <summary>
	/// 現在の時刻を取得する(トラン開始、コミット無)
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns></returns>
	public Timestamp GetSysDate(Connection con, Transaction trans)
	{
		MasterHandler masterHandler = new MasterHandler();
		return masterHandler.GetSysDate(con, trans);
	}
	*/

	/// <summary>
	/// システム情報(SystemParam)を取得
	/// </summary>
	/// <returns>システム情報(SystemParam)</returns>
	public SystemParamBean GetSystemParam()
	{
		return systemParamBean;
	}

	/// <summary>
	/// システム情報(SystemParam)のDataTableを設定
	/// </summary>
	public void SetSystemParam(SystemParamBean m_systemParamBean)
	{
		this.systemParamBean = m_systemParamBean;
	}

	// 2011.05.23 Add H.Orikasa Start 業務詳細高速化対応
	/// <summary>
	/// システムパラム２ のDataTableを設定
	/// </summary>
	/// <param name="systemParam2Tbl">システムパラム２ のDataTable</param>
	public void SetRRisSystemParam2(DataTable systemParam2Tbl)
	{
		if (null != systemParam2Tbl)
		{
			this.systemParam2Tbl = systemParam2Tbl;
		}
	}
	// 2011.05.23 Add H.Orikasa End 業務詳細高速化対応

	/// <summary>
	/// RISのシステムパラム２取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisSystemParam2()
	{
		// 2011.05.23 Mod H.Orikasa Start 業務詳細高速化対応
		// DBの情報ではなく、メンバで保持している値を返す
		return this.systemParam2Tbl;
		//return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SYSTEMPARAM2, true);
		// 2011.05.23 Mod H.Orikasa End   業務詳細高速化対応
	}

	/// <summary>
	/// RISの検査種別マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisKensaTypeMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_KENSATYPEMASTER, true, con);
	}

	/// <summary>
	/// RISの検査室マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisExamRoomMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_EXAMROOMMASTER, true, con);
	}

	/// <summary>
	/// RISの検査機器マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisKensaKikiMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_KENSAKIKIMASTER, true, con);
	}

	/// <summary>
	/// RISの部位セットマスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisBuiSetMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_BUISETMASTER, true, con);
	}

	/// <summary>
	/// RISの部位分類マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisBuiBunruiMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_BUIBUNRUIMASTER, true, con);
	}

	/// <summary>
	/// RISの部位マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisBuiMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_BUIMASTER, true, con);
	}

	/// <summary>
	/// RISの方法マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisHoukouMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_HOUKOUMASTER, true, con);
	}

	/// <summary>
	/// RISの検査方法マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisKensaHouhouMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_KENSAHOUHOUMASTER, true, con);
	}

	/// <summary>
	/// RISの左右マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisSayuuMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SAYUUMASTER, true, con);
	}

	/// <summary>
	/// RISの病室マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisByousituMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_BYOUSITUMASTER, true, con);
	}

	/// <summary>
	/// RISの病棟マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisByoutouMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_BYOUTOUMASTER, true, con);
	}

	/// <summary>
	/// RISの依頼科マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisSectionMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SECTIONMASTER, true, con);
	}

	/// <summary>
	/// RISの依頼医マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisSectionDoctorMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SECTIONDOCTORMASTER, true, con);
	}

	/// <summary>
	/// RISの項目変換定義マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisCodeConvertMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_CODECONVERT, true, con);
	}

	/// <summary>
	/// RISのステータス定義マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisStatusDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_STATUSDEFINE, false, con);
	}

	/// <summary>
	/// RISの施設マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisHospitalMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_HOSPITALMASTER, true, con);
	}

	/// <summary>
	/// 端末情報
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisTerminalInfo(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_TERMINALINFO, true, con);
	}

	/// <summary>
	/// RISの帳票種別マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPrintMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PRINTMASTER, true, con);
	}

	/// <summary>
	/// RISの帳票定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisDefaultPrintDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_DEFAULTPRINTDEFINE, false, con);
	}

	/// <summary>
	/// RISの検査種別毎帳票定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPrintDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PRINTDEFINE, false, con);
	}

	/// <summary>
	/// RISの検査種別毎帳票詳細定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPrintFormDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PRINTFORMDEFINE, false, con);
	}

	/// <summary>
	/// RISの検索ｻﾏﾘ表示項目ﾏｽﾀ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisSummaryItemMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SUMMARYITEMMASTER, true, con);
	}

	/// <summary>
	/// RISの検索ｻﾏﾘ表示項目(履歴)ﾏｽﾀ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisHistoryTabItemMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_HISTORYTABITEMMASTER, true, con);
	}

	/// <summary>
	/// RISの定型ｺﾒﾝﾄﾏｽﾀ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPreDefinedCommentMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PREDEFINEDCOMMENTMASTER, true, con);
	}

	/// <summary>
	/// RISのソート条件取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisSortPatternInfo(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SORTPATTERNINFO, true, con);
	}

	/// <summary>
	/// RISの診療日区分設定取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisDayClassificationTable(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_DAYCLASSIFICATIONTABLE, false, con);
	}

	/// <summary>
	/// RISの診療時間帯区分設定取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisTimezoneTable(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_TIMEZONETABLE, false, con);
	}

	/// <summary>
	/// RISのCR撮影メニューマスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisCRSatueiMenuMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_CRSATUEIMENUMASTER, true, con);
	}

	/// <summary>
	/// RISのCR撮影メニュー定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisCRSatueiMenuDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_CRSATUEIMENUDEFINE, true, con);
	}

	/// <summary>
	/// RISのフィルムマスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisFilmMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_FILMMASTER, true, con);
	}

	/// <summary>
	/// RISの部位情報項目定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisBuiItemDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_BUIITEMDEFINE, true, con);
	}

	/// <summary>
	/// RISの撮影情報項目定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisSatueiItemDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SATUEIITEMDEFINE, true, con);
	}

	/// <summary>
	/// RISの器材情報項目定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPartsItemDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PARTSITEMDEFINE, true, con);
	}

	/// <summary>
	/// RISの器材単位マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPartsUnitMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PARTSUNITMASTER, true, con);
	}

	/// <summary>
	/// RISのグループ権限設定取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisGroupCompetenceTable(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_GROUPCOMPETENCETABLE, false, con);
	}

	/// <summary>
	/// RISの拡張色定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisExtendColorDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_EXTEND_COLORDEFINE, false, con);
	}

	/// <summary>
	/// RISの区分定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisDivisionConvert(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_DEVISIONCONVERT, false, con);
	}

	/// <summary>
	/// RISのURL定義取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisURLDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_URLDEFINE, true, con);
	}

	/// <summary>
	/// RISの検査情報項目定義設定取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisExamItemDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_EXAMITEMDEFINE, true, con);
	}

	/// <summary>
	/// RISの管理情報コンボデータ設定取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisComboListItems(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_COMBOLISTITEMS, true, con);
	}

	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// グループマスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisGroupMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_GROUPMASTER, true, con);
	}
	// 2010.07.30 Add T.Ootsuka End

	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	/// <summary>
	/// RISの器材詳細区分マスタ取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisDetailPartsBunruiMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_DETAILPARTSBUNRUIMASTER, true, con);
	}

	/// <summary>
	/// RISの器材マスタを取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPartsMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PARTSMASTER, true, con);
	}

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	/// <summary>
	/// RISのオーダ種別取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisExamTimingDefine(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_EXAMTIMINGDEFINE, true, con);
	}
	// 2011.11.22 Add NSK_M.Ochiai End

	// コメント化 2011.08.01 Del H.Orikasa NML-CAT9-017 対応
	///// <summary>
	///// RISの定型コメントマスタを取得
	///// </summary>
	///// <returns></returns>
	//public DataTable GetRRisPredefinedCommentMaster()
	//{
	//    StandardCoreController ctrl = new StandardCoreController();
	//    return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PREDEFINEDCOMMENTMASTER, true, con);
	//}


	/// <summary>
	/// RISの定型コメント分類マスタを取得
	/// </summary>
	/// <returns></returns>
	public DataTable GetRRisPredefinedCommentKbnMaster(Connection con) throws Exception
	{
		return Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_PREDEFINEDCOMMENTKBNMASTER, true, con);
	}
	// 2011.03.10 Add CIJ_R.Aoyama Merge  End  MY-1-10

	/*
	// 2011.08.05 Add T.Ootsuka@MERX Start NML-1-X02
	/// <summary>
	/// 印刷処理に使用するマスタ情報を取得する
	/// </summary>
	public void GetMasterForReport()
	{
		try
		{
			// 2011.08.16 Mod T.Ootsuka@MERX Start NML-1-X02(問題点修正)
			mstHashTableForReport.clear();
			//Hashtable mstHashTable = new Hashtable();
			// 2011.08.16 Mod T.Ootsuka@MERX End

			// 各マスタ取得

			DataTable mstSysParam2		= Configuration.GetInstance().GetRRisSystemParam2();				// システムパラム２
			DataTable mstKType			= Configuration.GetInstance().GetRRisKensaTypeMaster();				// 検査種別
			DataTable mstRoom			= Configuration.GetInstance().GetRRisExamRoomMaster();				// 検査室
			DataTable mstKiki			= Configuration.GetInstance().GetRRisKensaKikiMaster();				// 検査機器
			DataTable mstBui			= Configuration.GetInstance().GetRRisBuiMaster();					// 部位
			DataTable mstHoukou			= Configuration.GetInstance().GetRRisHoukouMaster();				// 方向
			DataTable mstSayuu			= Configuration.GetInstance().GetRRisSayuuMaster();					// 左右
			DataTable mstKensaHouhou	= Configuration.GetInstance().GetRRisKensaHouhouMaster();			// 検査方法
			DataTable mstFilm			= Configuration.GetInstance().GetRRisFilmMaster();					// フィルム
			DataTable mstByoutou		= Configuration.GetInstance().GetRRisByoutouMaster();				// 病棟
			DataTable mstByousitu		= Configuration.GetInstance().GetRRisByousituMaster();				// 病室
			DataTable mstSection		= Configuration.GetInstance().GetRRisSectionMaster();				// 依頼科
			DataTable mstSectionDoc		= Configuration.GetInstance().GetRRisSectionDoctorMaster();			// 依頼科医師
			DataTable mstComment		= Configuration.GetInstance().GetRRisPreDefinedCommentMaster();		// 定型コメント
			DataTable mstStatus			= Configuration.GetInstance().GetRRisStatusDefine();				// ステータス
			DataTable mstUnit			= Configuration.GetInstance().GetRRisPartsUnitMaster();				// 器材単位
			DataTable mstCode			= Configuration.GetInstance().GetRRisCodeConvertMaster();			// 項目変換
			DataTable mstSatueiDef		= Configuration.GetInstance().GetRRisSatueiItemDefine();			// 撮影項目



			// ハッシュテーブルへ格納
			// 2011.08.16 Mod T.Ootsuka@MERX Start NML-1-X02(問題点修正)
			mstHashTableForReport.Add(MasterUtil.RIS_SYSTEMPARAM2, mstSysParam2);
			mstHashTableForReport.Add(MasterUtil.RIS_KENSATYPEMASTER, mstKType);
			mstHashTableForReport.Add(MasterUtil.RIS_EXAMROOMMASTER, mstRoom);
			mstHashTableForReport.Add(MasterUtil.RIS_KENSAKIKIMASTER, mstKiki);
			mstHashTableForReport.Add(MasterUtil.RIS_BUIMASTER, mstBui);
			mstHashTableForReport.Add(MasterUtil.RIS_HOSPITALMASTER, mstHoukou);
			mstHashTableForReport.Add(MasterUtil.RIS_SAYUUMASTER, mstSayuu);
			mstHashTableForReport.Add(MasterUtil.RIS_KENSAHOUHOUMASTER, mstKensaHouhou);
			mstHashTableForReport.Add(MasterUtil.RIS_FILMMASTER, mstFilm);
			mstHashTableForReport.Add(MasterUtil.RIS_BYOUTOUMASTER, mstByoutou);
			mstHashTableForReport.Add(MasterUtil.RIS_BYOUSITUMASTER, mstByousitu);
			mstHashTableForReport.Add(MasterUtil.RIS_SECTIONMASTER, mstSection);
			mstHashTableForReport.Add(MasterUtil.RIS_SECTIONDOCTORMASTER, mstSectionDoc);
			mstHashTableForReport.Add(MasterUtil.RIS_PREDEFINEDCOMMENTMASTER, mstComment);
			mstHashTableForReport.Add(MasterUtil.RIS_STATUSDEFINE, mstStatus);
			mstHashTableForReport.Add(MasterUtil.RIS_PARTSUNITMASTER, mstUnit);
			mstHashTableForReport.Add(MasterUtil.RIS_CODECONVERT, mstCode);
			mstHashTableForReport.Add(MasterUtil.RIS_SATUEIITEMDEFINE, mstSatueiDef);
			// コメント
			//mstHashTable.Add(MasterUtil.RIS_SYSTEMPARAM2, mstSysParam2);
			//mstHashTable.Add(MasterUtil.RIS_KENSATYPEMASTER, mstKType);
			//mstHashTable.Add(MasterUtil.RIS_EXAMROOMMASTER, mstRoom);
			//mstHashTable.Add(MasterUtil.RIS_KENSAKIKIMASTER, mstKiki);
			//mstHashTable.Add(MasterUtil.RIS_BUIMASTER, mstBui);
			//mstHashTable.Add(MasterUtil.RIS_HOSPITALMASTER, mstHoukou);
			//mstHashTable.Add(MasterUtil.RIS_SAYUUMASTER, mstSayuu);
			//mstHashTable.Add(MasterUtil.RIS_KENSAHOUHOUMASTER, mstKensaHouhou);
			//mstHashTable.Add(MasterUtil.RIS_FILMMASTER, mstFilm);
			//mstHashTable.Add(MasterUtil.RIS_BYOUTOUMASTER, mstByoutou);
			//mstHashTable.Add(MasterUtil.RIS_BYOUSITUMASTER, mstByousitu);
			//mstHashTable.Add(MasterUtil.RIS_SECTIONMASTER, mstSection);
			//mstHashTable.Add(MasterUtil.RIS_SECTIONDOCTORMASTER, mstSectionDoc);
			//mstHashTable.Add(MasterUtil.RIS_PREDEFINEDCOMMENTMASTER, mstComment);
			//mstHashTable.Add(MasterUtil.RIS_STATUSDEFINE, mstStatus);
			//mstHashTable.Add(MasterUtil.RIS_PARTSUNITMASTER, mstUnit);
			//mstHashTable.Add(MasterUtil.RIS_CODECONVERT, mstCode);
			//mstHashTable.Add(MasterUtil.RIS_SATUEIITEMDEFINE, mstSatueiDef);

			// 2011.08.16 Mod T.Ootsuka@MERX End



			// 2011.08.16 Del T.Ootsuka@MERX Start NML-1-X02(問題点修正)
			//mstHashTableForReport = mstHashTable;
			// 2011.08.16 Del T.Ootsuka@MERX End

		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}
	*/

	/*
	/// <summary>
	/// 帳票印刷使用マスタ情報取得
	/// </summary>
	/// <returns></returns>
	public Hashtable GetMstHashTableForReport()
	{
		return mstHashTableForReport;
	}
	// 2011.08.05 Add T.Ootsuka@MERX End
	*/


	// 共通項目

	/// <summary>
	/// StudyInstanceUIDを作成する
	/// </summary>
	/// <param name="risID"></param>
	/// <returns></returns>
	public String CreateStudyInstanceUID(String risID)
	{
		String retID = "";

		retID += CommonString.STUDY_INSTANCE_UID_HEADER;
		retID += "." + this.licensenoStr;
		retID += "." + risID;

		return retID;
	}
//
	// ipAddressStrのSET
	public void SetIPAddress( String ipAddress )
	{
		if( ipAddress != null )
		{
			this.ipAddressStr = ipAddress;
		}
	}
	// ipAddressStrのGET
	public String GetIPAddress()
	{
		return this.ipAddressStr;
	}
//
	/// <summary>
	/// 端末名取得
	/// </summary>
	/// <returns></returns>
	public String GetTerminalName()
	{
		return this.terminal;
	}

	/// <summary>
	/// 端末名設定
	/// </summary>
	public void SetTerminalName(String terminal)
	{
		this.terminal = terminal;
	}
//
	/// <summary>
	/// 端末定義設定取得
	/// </summary>
	/// <returns></returns>
	public TerminalInfoBean GetTerminalInfoBean()
	{
		return this.terminalInfoBean;
	}

	/// <summary>
	/// 端末定義設定設定
	/// </summary>
	public void SetTerminalInfoBean(TerminalInfoBean terminalInfo)
	{
		this.terminalInfoBean = terminalInfo;
	}

	// ステータス

	/// <summary>
	/// ステータス表示略称-Status0の取得(未受)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg0()
	{
		return this.statusMsg0Str;
	}

	/// <summary>
	/// ステータス表示略称-Status0の設定(未受)
	/// </summary>
	public void SetStatusMsg0(String statusMsg0)
	{
		this.statusMsg0Str = statusMsg0;
	}

	/// <summary>
	/// ステータス表示略称-Status1の取得(遅刻)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg1()
	{
		return this.statusMsg1Str;
	}

	/// <summary>
	/// ステータス表示略称-Status1の設定(遅刻)
	/// </summary>
	public void SetStatusMsg1(String statusMsg1)
	{
		this.statusMsg1Str = statusMsg1;
	}

	/// <summary>
	/// ステータス表示略称-Status2の取得(呼出)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg2()
	{
		return this.statusMsg2Str;
	}

	/// <summary>
	/// ステータス表示略称-Status2の設定(呼出)
	/// </summary>
	public void SetStatusMsg2(String statusMsg2)
	{
		this.statusMsg2Str = statusMsg2;
	}

	/// <summary>
	/// ステータス表示略称-Status3の取得(受済)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg3()
	{
		return this.statusMsg3Str;
	}

	/// <summary>
	/// ステータス表示略称-Status3の設定(受済)
	/// </summary>
	public void SetStatusMsg3(String statusMsg3Str)
	{
		this.statusMsg3Str = statusMsg3Str;
	}

	/// <summary>
	/// ステータス表示略称-Status4の取得(実施中)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg4()
	{
		return this.statusMsg4Str;
	}

	/// <summary>
	/// ステータス表示略称-Status4の設定(実施中)
	/// </summary>
	public void SetStatusMsg4(String statusMsg4)
	{
		this.statusMsg4Str = statusMsg4;
	}

	/// <summary>
	/// ステータス表示略称-Status5の取得(保留)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg5()
	{
		return this.statusMsg5Str;
	}

	/// <summary>
	/// ステータス表示略称-Status5の設定(保留)
	/// </summary>
	public void SetStatusMsg5(String statusMsg5)
	{
		this.statusMsg5Str = statusMsg5;
	}

	/// <summary>
	/// ステータス表示略称-Status6の取得(再呼出)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg6()
	{
		return this.statusMsg6Str;
	}

	/// <summary>
	/// ステータス表示略称-Status6の設定(再呼出)
	/// </summary>
	public void SetStatusMsg6(String statusMsg6)
	{
		this.statusMsg6Str = statusMsg6;
	}

	/// <summary>
	/// ステータス表示略称-Status7の取得(再受付)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg7()
	{
		return this.statusMsg7Str;
	}

	/// <summary>
	/// ステータス表示略称-Status7の設定(再受付)
	/// </summary>
	public void SetStatusMsg7(String statusMsg7)
	{
		this.statusMsg7Str = statusMsg7;
	}

	/// <summary>
	/// ステータス表示略称-Status8の取得(実施済)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg8()
	{
		return this.statusMsg8Str;
	}

	/// <summary>
	/// ステータス表示略称-Status8の設定(実施済)
	/// </summary>
	public void SetStatusMsg8(String statusMsg8)
	{
		this.statusMsg8Str = statusMsg8;
	}

	/// <summary>
	/// ステータス表示略称-Status9の取得(中止)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg9()
	{
		return this.statusMsg9Str;
	}

	/// <summary>
	/// ステータス表示略称-Status9の設定(中止)
	/// </summary>
	public void SetStatusMsg9(String statusMsg9)
	{
		this.statusMsg9Str = statusMsg9;
	}

	// 2011.03.18 Add Yk.Suzuki@CIJ Start A0005
	/// <summary>
	/// ステータス表示略称-Status10の取得(削除)
	/// </summary>
	/// <returns></returns>
	public String GetStatusMsg10()
	{
		return this.statusMsg10Str;
	}

	/// <summary>
	/// ステータス表示略称-Status10の設定(削除)
	/// </summary>
	public void SetStatusMsg10(String statusMsg10)
	{
		this.statusMsg10Str = statusMsg10;
	}
	// 2011.03.18 Add Yk.Suzuki@CIJ End
//
	/// <summary>
	/// ステータス色-Status0の取得(未受)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor0()
	{
		return this.statusColor0Str;
	}

	/// <summary>
	/// ステータス色-Status0の設定(未受)
	/// </summary>
	public void SetStatusColor0(String statusColor0)
	{
		this.statusColor0Str = statusColor0;
	}

	/// <summary>
	/// ステータス色-Status1の取得(遅刻)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor1()
	{
		return this.statusColor1Str;
	}

	/// <summary>
	/// ステータス色-Status1の設定(遅刻)
	/// </summary>
	public void SetStatusColor1(String statusColor1)
	{
		this.statusColor1Str = statusColor1;
	}

	/// <summary>
	/// ステータス色-Status2の取得(呼出)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor2()
	{
		return this.statusColor2Str;
	}

	/// <summary>
	/// ステータス色-Status2の設定(呼出)
	/// </summary>
	public void SetStatusColor2(String statusColor2)
	{
		this.statusColor2Str = statusColor2;
	}

	/// <summary>
	/// ステータス色-Status3の取得(受済)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor3()
	{
		return this.statusColor3Str;
	}

	/// <summary>
	/// ステータス色-Status3の設定(受済)
	/// </summary>
	public void SetStatusColor3(String statusColor3Str)
	{
		this.statusColor3Str = statusColor3Str;
	}

	/// <summary>
	/// ステータス色-Status4の取得(実施中)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor4()
	{
		return this.statusColor4Str;
	}

	/// <summary>
	/// ステータス色-Status4の設定(実施中)
	/// </summary>
	public void SetStatusColor4(String statusColor4)
	{
		this.statusColor4Str = statusColor4;
	}

	/// <summary>
	/// ステータス色-Status5の取得(保留)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor5()
	{
		return this.statusColor5Str;
	}

	/// <summary>
	/// ステータス色-Status5の設定(保留)
	/// </summary>
	public void SetStatusColor5(String statusColor5)
	{
		this.statusColor5Str = statusColor5;
	}

	/// <summary>
	/// ステータス色-Status6の取得(再呼出)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor6()
	{
		return this.statusColor6Str;
	}

	/// <summary>
	/// ステータス色-Status6の設定(再呼出)
	/// </summary>
	public void SetStatusColor6(String statusColor6)
	{
		this.statusColor6Str = statusColor6;
	}

	/// <summary>
	/// ステータス色-Status7の取得(再受付)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor7()
	{
		return this.statusColor7Str;
	}

	/// <summary>
	/// ステータス色-Status7の設定(再受付)
	/// </summary>
	public void SetStatusColor7(String statusColor7)
	{
		this.statusColor7Str = statusColor7;
	}

	/// <summary>
	/// ステータス色-Status8の取得(実施済)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor8()
	{
		return this.statusColor8Str;
	}

	/// <summary>
	/// ステータス色-Status8の設定(実施済)
	/// </summary>
	public void SetStatusColor8(String statusColor8)
	{
		this.statusColor8Str = statusColor8;
	}

	/// <summary>
	/// ステータス色-Status9の取得(中止)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor9()
	{
		return this.statusColor9Str;
	}

	/// <summary>
	/// ステータス色-Status9の設定(中止)
	/// </summary>
	public void SetStatusColor9(String statusColor9)
	{
		this.statusColor9Str = statusColor9;
	}

	// 2011.03.18 Add Yk.Suzuki@CIJ Start A0005
	/// <summary>
	/// ステータス色-Status10の取得(削除)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColor10()
	{
		return this.statusColor10Str;
	}

	/// <summary>
	/// ステータス色-Status10の設定(削除)
	/// </summary>
	public void SetStatusColor10(String statusColor10)
	{
		this.statusColor10Str = statusColor10;
	}
	// 2011.03.18 Add Yk.Suzuki@CIJ End
//
	/// <summary>
	/// ステータス背景色-Status0の取得(未受)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk0()
	{
		return this.statusColorBk0Str;
	}

	/// <summary>
	/// ステータス背景色-Status0の設定(未受)
	/// </summary>
	public void SetStatusColorBk0(String statusColorBk0)
	{
		this.statusColorBk0Str = statusColorBk0;
	}

	/// <summary>
	/// ステータス背景色-Status1の取得(遅刻)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk1()
	{
		return this.statusColorBk1Str;
	}

	/// <summary>
	/// ステータス背景色-Status1の設定(遅刻)
	/// </summary>
	public void SetStatusColorBk1(String statusColorBk1)
	{
		this.statusColorBk1Str = statusColorBk1;
	}

	/// <summary>
	/// ステータス背景色-Status2の取得(呼出)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk2()
	{
		return this.statusColorBk2Str;
	}

	/// <summary>
	/// ステータス背景色-Status2の設定(呼出)
	/// </summary>
	public void SetStatusColorBk2(String statusColorBk2)
	{
		this.statusColorBk2Str = statusColorBk2;
	}

	/// <summary>
	/// ステータス背景色-Status3の取得(受済)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk3()
	{
		return this.statusColorBk3Str;
	}
//
	/// <summary>
	/// ステータス背景色-Status3の設定(受済)
	/// </summary>
	public void SetStatusColorBk3(String statusColorBk3Str)
	{
		this.statusColorBk3Str = statusColorBk3Str;
	}

	/// <summary>
	/// ステータス背景色-Status4の取得(実施中)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk4()
	{
		return this.statusColorBk4Str;
	}

	/// <summary>
	/// ステータス背景色-Status4の設定(実施中)
	/// </summary>
	public void SetStatusColorBk4(String statusColorBk4)
	{
		this.statusColorBk4Str = statusColorBk4;
	}

	/// <summary>
	/// ステータス背景色-Status5の取得(保留)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk5()
	{
		return this.statusColorBk5Str;
	}

	/// <summary>
	/// ステータス背景色-Status5の設定(保留)
	/// </summary>
	public void SetStatusColorBk5(String statusColorBk5)
	{
		this.statusColorBk5Str = statusColorBk5;
	}

	/// <summary>
	/// ステータス背景色-Status6の取得(再呼出)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk6()
	{
		return this.statusColorBk6Str;
	}

	/// <summary>
	/// ステータス背景色-Status6の設定(再呼出)
	/// </summary>
	public void SetStatusColorBk6(String statusColorBk6)
	{
		this.statusColorBk6Str = statusColorBk6;
	}

	/// <summary>
	/// ステータス背景色-Status7の取得(再受付)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk7()
	{
		return this.statusColorBk7Str;
	}

	/// <summary>
	/// ステータス背景色-Status7の設定(再受付)
	/// </summary>
	public void SetStatusColorBk7(String statusColorBk7)
	{
		this.statusColorBk7Str = statusColorBk7;
	}

	/// <summary>
	/// ステータス背景色-Status8の取得(実施済)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk8()
	{
		return this.statusColorBk8Str;
	}

	/// <summary>
	/// ステータス背景色-Status8の設定(実施済)
	/// </summary>
	public void SetStatusColorBk8(String statusColorBk8)
	{
		this.statusColorBk8Str = statusColorBk8;
	}

	/// <summary>
	/// ステータス背景色-Status9の取得(中止)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk9()
	{
		return this.statusColorBk9Str;
	}

	/// <summary>
	/// ステータス背景色-Status9の設定(中止)
	/// </summary>
	public void SetStatusColorBk9(String statusColorBk9)
	{
		this.statusColorBk9Str = statusColorBk9;
	}

	// 2011.03.18 Add Yk.Suzuki@CIJ Start A0005
	/// <summary>
	/// ステータス背景色-Status10の取得(削除)
	/// </summary>
	/// <returns></returns>
	public String GetStatusColorBk10()
	{
		return this.statusColorBk10Str;
	}

	/// <summary>
	/// ステータス背景色-Status10の設定(削除)
	/// </summary>
	public void SetStatusColorBk10(String statusColorBk10)
	{
		this.statusColorBk10Str = statusColorBk10;
	}
	// 2011.03.18 Add Yk.Suzuki@CIJ End


	/// <summary>
	/// プリンタ取得
	/// </summary>
	/// <returns>プリンタ設定</returns>
	public String GetPrinterDriver()
	{
		return this.printerDriverStr;
	}

	/// <summary>
	/// プリンタ設定
	/// </summary>
	public void SetPrinterDriver(String printerDriver)
	{
		this.printerDriverStr = printerDriver;
	}

	// SYSTEMDEFINE情報

	/// <summary>
	/// ライセンス取得
	/// </summary>
	/// <returns>ライセンス</returns>
	public String GetLicenseno()
	{
		return this.licensenoStr;
	}

	/// <summary>
	/// ライセンス設定
	/// </summary>
	public void SetLicenseno(String licenseno)
	{
		this.licensenoStr = licenseno;
	}

	/// <summary>
	/// 検像ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>検像ﾌﾗｸﾞ設定</returns>
	public String GetKenzouFlg()
	{
		return this.kenzouFlgStr;
	}

	/// <summary>
	/// 検像ﾌﾗｸﾞ設定
	/// </summary>
	public void SetKenzouFlg(String kenzouFlg)
	{
		this.kenzouFlgStr = kenzouFlg;
	}

	/// <summary>
	/// 遠隔診断対応ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>遠隔診断対応ﾌﾗｸﾞ設定</returns>
	public String GetHspLinkFlag()
	{
		return this.hsplinkflagStr;
	}

	/// <summary>
	/// 遠隔診断対応ﾌﾗｸﾞ設定
	/// </summary>
	public void SetHspLinkFlag(String hsplinkflag)
	{
		this.hsplinkflagStr = hsplinkflag;
	}

	/// <summary>
	/// ﾕｰｻﾞ認証ﾀｲﾌﾟ取得
	/// </summary>
	/// <returns>ﾕｰｻﾞ認証ﾀｲﾌﾟ設定</returns>
	public String GetConfirmType()
	{
		return this.confirmTypeStr;
	}

	/// <summary>
	/// ﾕｰｻﾞ認証ﾀｲﾌﾟ設定
	/// </summary>
	public void SetConfirmType(String confirmType)
	{
		this.confirmTypeStr = confirmType;
	}

	/// <summary>
	/// SQﾄﾞﾒｲﾝ取得
	/// </summary>
	/// <returns>SQﾄﾞﾒｲﾝ</returns>
	public String GetSqHspid()
	{
		return this.sq_hspidStr;
	}

	/// <summary>
	/// SQﾄﾞﾒｲﾝ設定
	/// </summary>
	public void SetSqHspid(String sq_hspid)
	{
		this.sq_hspidStr = sq_hspid;
	}

	/// <summary>
	/// 部位セット取得
	/// </summary>
	/// <returns>部位セット設定</returns>
	public String GetBuisetFlg()
	{
		return this.buisetFlgStr;
	}

	/// <summary>
	/// 部位セット設定
	/// </summary>
	public void SetBuisetFlg(String buisetFlg)
	{
		this.buisetFlgStr = buisetFlg;
	}

	/// <summary>
	/// markercharacter取得
	/// </summary>
	/// <returns>複数項目の区切り文字（ﾃﾞﾌｫﾙﾄは、’|’）</returns>
	public String GetMarkerCharacter()
	{
		return this.markercharacterStr;
	}

	/// <summary>
	/// markercharacter設定
	/// </summary>
	public void SetMarkerCharacter(String markercharacter)
	{
		if (markercharacter.length() > 0)
		{
			this.markercharacterStr = markercharacter;
		}
	}

	/// <summary>
	/// ﾃﾝﾌﾟﾚｰﾄ項目の区切文字取得
	/// </summary>
	/// <returns>ﾃﾝﾌﾟﾚｰﾄ項目の区切文字（ﾃﾞｨﾌｫﾙﾄは、’/’）</returns>
	public String GetTemplateMarkerChar()
	{
		return this.templatemarkercharStr;
	}

	/// <summary>
	/// ﾃﾝﾌﾟﾚｰﾄ項目の区切文字設定
	/// </summary>
	public void SetTemplateMarkerChar(String templatemarkerchar)
	{
		if (templatemarkerchar.length() > 0)
		{
			this.templatemarkercharStr = templatemarkerchar;
		}
	}

	/// <summary>
	/// 小児下限設定年齢取得
	/// </summary>
	/// <returns>小児下限設定年齢、撮影装置に送信する撮影ｺｰﾄﾞ変換で使用する</returns>
	public String GetChildlowlimit()
	{
		return this.childlowlimitStr;
	}

	/// <summary>
	/// 小児下限設定年齢設定
	/// </summary>
	public void SetChildlowlimit(String childlowlimit)
	{
		this.childlowlimitStr = childlowlimit;
	}

	/// <summary>
	/// 小児上限設定年齢取得
	/// </summary>
	/// <returns>小児上限設定年齢</returns>
	public String GetChildhighlimit()
	{
		return this.childhighlimitStr;
	}

	/// <summary>
	/// 小児上限設定年齢設定
	/// </summary>
	public void SetChildhighlimit(String childhighlimit)
	{
		this.childhighlimitStr = childhighlimit;
	}

	/// <summary>
	/// 乳児下限設定年齢取得
	/// </summary>
	/// <returns>乳児下限設定年齢</returns>
	public String GetBabylowlimit()
	{
		return this.babylowlimitStr;
	}

	/// <summary>
	///  乳児下限設定年齢設定
	/// </summary>
	public void SetBabylowlimit(String babylowlimit)
	{
		this.babylowlimitStr = babylowlimit;
	}

	/// <summary>
	/// 乳児上限設定年齢取得
	/// </summary>
	/// <returns>乳児下限設定年齢</returns>
	public String GetBabyhighlimit()
	{
		return this.babyhighlimitStr;
	}

	/// <summary>
	/// 乳児上限設定年齢設定
	/// </summary>
	public void SetBabyhighlimit(String babyhighlimit)
	{
		this.babyhighlimitStr = babyhighlimit;
	}

	/// <summary>
	/// RIS発行ｵｰﾀﾞ受付転送ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>RIS発行ｵｰﾀﾞ受付転送ﾌﾗｸﾞ</returns>
	public String GetRisOrdersendFlag()
	{
		return this.risordersendflagStr;
	}

	/// <summary>
	/// RIS発行ｵｰﾀﾞ受付転送ﾌﾗｸﾞ設定
	/// </summary>
	public void SetRisOrdersendFlag(String risordersendflag)
	{
		this.risordersendflagStr = risordersendflag;
	}

	/// <summary>
	/// RIS発行ｵｰﾀ実績転送ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>RIS発行ｵｰﾀ実績転送ﾌﾗｸﾞ</returns>
	public String GetRisOrdersendFlag2()
	{
		return this.risordersendflag2Str;
	}

	/// <summary>
	/// RIS発行ｵｰﾀ実績転送ﾌﾗｸﾞ設定
	/// </summary>
	public void SetRisOrdersendFlag2(String risordersendflag2)
	{
		this.risordersendflag2Str = risordersendflag2;
	}

	/// <summary>
	/// 画像連携使用ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>画像連携使用ﾌﾗｸﾞ</returns>
	public String GetImageUrlFlag()
	{
		return this.imageurlflagStr;
	}

	/// <summary>
	/// 画像連携使用ﾌﾗｸﾞ設定
	/// </summary>
	public void SetImageUrlFlag(String imageurlflag)
	{
		this.imageurlflagStr = imageurlflag;
	}

	/// <summary>
	/// 画像連携URL取得
	/// </summary>
	/// <returns>画像連携URL</returns>
	public String GetImageUrl()
	{
		return this.imageurlStr;
	}

	/// <summary>
	/// 画像連携URL設定
	/// </summary>
	public void SetImageUrl(String imageurl)
	{
		this.imageurlStr = imageurl;
	}

	/// <summary>
	/// 画像連携URLﾕｰｻﾞ名取得
	/// </summary>
	/// <returns>画像連携URLﾕｰｻﾞ名</returns>
	public String GetImgUserLabel()
	{
		return this.imguserlabelStr;
	}

	/// <summary>
	/// 画像連携URLﾕｰｻﾞ名設定
	/// </summary>
	public void SetImgUserLabel(String imguserlabel)
	{
		this.imguserlabelStr = imguserlabel;
	}

	/// <summary>
	/// 画像連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子取得
	/// </summary>
	/// <returns>画像連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子</returns>
	public String GetImgPasswordLabel()
	{
		return this.imgpasswordlabelStr;
	}

	/// <summary>
	/// 画像連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子設定
	/// </summary>
	public void SetImgPasswordLabel(String imgpasswordlabel)
	{
		this.imgpasswordlabelStr = imgpasswordlabel;
	}

	/// <summary>
	/// 画像連携URL患者ID指定時の識別子取得
	/// </summary>
	/// <returns>画像連携URL患者ID指定時の識別子</returns>
	public String GetImgPidLabel()
	{
		return this.imgpidlabelStr;
	}

	/// <summary>
	/// 画像連携URL患者ID指定時の識別子設定
	/// </summary>
	public void SetImgPidLabel(String imgpidlabel)
	{
		this.imgpidlabelStr = imgpidlabel;
	}

	/// <summary>
	/// 画像連携URL検査日指定時の識別子取得
	/// </summary>
	/// <returns>画像連携URL検査日指定時の識別子</returns>
	public String GetImgDateLabel()
	{
		return this.imgdatelabelStr;
	}

	/// <summary>
	/// 画像連携URL検査日指定時の識別子設定
	/// </summary>
	public void SetImgDateLabel(String imgdatelabel)
	{
		this.imgdatelabelStr = imgdatelabel;
	}

	/// <summary>
	/// 画像連携URLﾓﾀﾞﾘﾃｨ指定時の識別子取得
	/// </summary>
	/// <returns>画像連携URLﾓﾀﾞﾘﾃｨ指定時の識別子</returns>
	public String GetImgModalityLabel()
	{
		return this.imgmodalitylabelStr;
	}

	/// <summary>
	/// 画像連携URLﾓﾀﾞﾘﾃｨ指定時の識別子設定
	/// </summary>
	public void SetImgModalityLabel(String imgmodalitylabel)
	{
		this.imgmodalitylabelStr = imgmodalitylabel;
	}

	/// <summary>
	/// 画像連携URLAccessionNo指定時の識別子取得
	/// </summary>
	/// <returns>画像連携URLAccessionNo指定時の識別子</returns>
	public String GetImgAcnoLabel()
	{
		return this.imgacnolabelStr;
	}

	/// <summary>
	/// 画像連携URLAccessionNo指定時の識別子設定
	/// </summary>
	public void SetImgAcnoLabel(String imgacnolabel)
	{
		this.imgacnolabelStr = imgacnolabel;
	}

	/// <summary>
	/// 所見連携ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>所見連携ﾌﾗｸﾞ</returns>
	public String GetReportUrlFlag()
	{
		return this.reporturlflagStr;
	}

	/// <summary>
	/// 所見連携ﾌﾗｸﾞ設定
	/// </summary>
	public void SetReportUrlFlag(String reporturlflag)
	{
		this.reporturlflagStr = reporturlflag;
	}

	/// <summary>
	/// 所見連携URL取得
	/// </summary>
	/// <returns>所見連携URL</returns>
	public String GetReportUrl()
	{
		return this.reporturlStr;
	}

	/// <summary>
	/// 所見連携URL設定
	/// </summary>
	public void SetReportUrl(String reporturl)
	{
		this.reporturlStr = reporturl;
	}

	/// <summary>
	/// 所見連携URLﾕｰｻﾞ名指定時の識別子取得
	/// </summary>
	/// <returns>所見連携URLﾕｰｻﾞ名指定時の識別子</returns>
	public String GetRptUserLabel()
	{
		return this.rptuserlabelStr;
	}

	/// <summary>
	/// 所見連携URLﾕｰｻﾞ名指定時の識別子設定
	/// </summary>
	public void SetRptUserLabel(String rptuserlabel)
	{
		this.rptuserlabelStr = rptuserlabel;
	}

	/// <summary>
	/// 所見連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子取得
	/// </summary>
	/// <returns>所見連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子</returns>
	public String GetRptPasswordLabel()
	{
		return this.rptpasswordlabelStr;
	}

	/// <summary>
	/// 所見連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子設定
	/// </summary>
	public void SetRptPasswordLabel(String rptpasswordlabel)
	{
		this.rptpasswordlabelStr = rptpasswordlabel;
	}

	/// <summary>
	/// 所見連携URL患者ID指定時の識別子取得
	/// </summary>
	/// <returns>所見連携URL患者ID指定時の識別子</returns>
	public String GetRptPidLabel()
	{
		return this.rptpidlabelStr;
	}

	/// <summary>
	/// 所見連携URL患者ID指定時の識別子設定
	/// </summary>
	public void SetRptPidLabel(String rptpidlabel)
	{
		this.rptpidlabelStr = rptpidlabel;
	}

	/// <summary>
	/// 所見連携URL検査日指定時の識別子取得
	/// </summary>
	/// <returns>所見連携URL検査日指定時の識別子</returns>
	public String GetRptDateLabel()
	{
		return this.rptdatelabelStr;
	}

	/// <summary>
	/// 所見連携URL検査日指定時の識別子設定
	/// </summary>
	public void SetRptDateLabel(String rptedatelabel)
	{
		this.rptdatelabelStr = rptedatelabel;
	}

	/// <summary>
	/// 所見連携URLﾓﾀﾞﾘﾃｨ指定時の識別子取得
	/// </summary>
	/// <returns>所見連携URLﾓﾀﾞﾘﾃｨ指定時の識別子</returns>
	public String GetRptModalityLabel()
	{
		return this.rptmodalitylabelStr;
	}

	/// <summary>
	/// 所見連携URLﾓﾀﾞﾘﾃｨ指定時の識別子設定
	/// </summary>
	public void SetRptModalityLabel(String rptmodalitylabel)
	{
		this.rptmodalitylabelStr = rptmodalitylabel;
	}

	/// <summary>
	/// 所見連携URLAccessionNo指定時の識別子取得
	/// </summary>
	/// <returns>所見連携URLﾓﾀﾞﾘﾃｨ指定時の識別子</returns>
	public String GetRptAcnoLabel()
	{
		return this.rptacnolabelStr;
	}

	/// <summary>
	/// 所見連携URLAccessionNo指定時の識別子設定
	/// </summary>
	public void SetRptAcnoLabel(String rptacnolabel)
	{
		this.rptacnolabelStr = rptacnolabel;
	}

	/// <summary>
	/// ｼｪｰﾏ連携使用ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携使用ﾌﾗｸﾞ</returns>
	public String GetSchemaUrlFlag()
	{
		return this.schemaurlflagStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携使用ﾌﾗｸﾞ設定
	/// </summary>
	public void SetSchemaUrlFlag(String schemaurlflag)
	{
		this.schemaurlflagStr = schemaurlflag;
	}

	/// <summary>
	/// EXTENDORDERINFO.SCHEMAURL付加ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>EXTENDORDERINFO.SCHEMAURL付加ﾌﾗｸﾞ 0：付加しない　1：付加する</returns>
	public String GetExtendOrderUseFlag()
	{
		return this.extendorderuseflagStr;
	}

	/// <summary>
	/// EXTENDORDERINFO.SCHEMAURL付加ﾌﾗｸﾞ設定
	/// </summary>
	public void SetExtendOrderUseFlag(String extendorderuseflag)
	{
		this.extendorderuseflagStr = extendorderuseflag;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URL取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携URL</returns>
	public String GetSchemaUrl()
	{
		return this.schemaurlStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URL設定
	/// </summary>
	public void SetSchemaUrl(String schemaurl)
	{
		this.schemaurlStr = schemaurl;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLﾕｰｻﾞ名指定時の識別子取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携URLﾕｰｻﾞ名指定時の識別子</returns>
	public String GetSchUserLabel()
	{
		return this.schuserlabelStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLﾕｰｻﾞ名指定時の識別子設定
	/// </summary>
	public void SetSchUserLabel(String schuserlabel)
	{
		this.schuserlabelStr = schuserlabel;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子</returns>
	public String GetSchPasswordLabel()
	{
		return this.schpasswordlabelStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLﾊﾟｽﾜｰﾄﾞ指定時の識別子設定
	/// </summary>
	public void SetSchPasswordLabel(String schpasswordlabel)
	{
		this.schpasswordlabelStr = schpasswordlabel;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URL患者ID指定時の識別子取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携患者ID指定時の識別子</returns>
	public String GetSchPidLabel()
	{
		return this.schpidlabelStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URL患者ID指定時の識別子設定
	/// </summary>
	public void SetSchPidLabel(String schpidlabel)
	{
		this.schpidlabelStr = schpidlabel;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URL検査日指定時の識別子取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携URL検査日指定時の識別子</returns>
	public String GetSchEdateLabel()
	{
		return this.schedatelabelStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URL検査日指定時の識別子設定
	/// </summary>
	public void SetSchEdateLabel(String schedatelabel)
	{
		this.schedatelabelStr = schedatelabel;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLﾓﾀﾞﾘﾃｨ指定時の識別子取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携URLﾓﾀﾞﾘﾃｨ指定時の識別子</returns>
	public String GetSchModalityLabel()
	{
		return this.schmodalitylabelStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLﾓﾀﾞﾘﾃｨ指定時の識別子設定
	/// </summary>
	public void SetSchModalityLabel(String schmodalitylabel)
	{
		this.schmodalitylabelStr = schmodalitylabel;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLｵｰﾀﾞ番号指定時の識別子取得
	/// </summary>
	/// <returns>ｼｪｰﾏ連携URLｵｰﾀﾞ番号指定時の識別子</returns>
	public String GetSchOrdernoLabel()
	{
		return this.schordernolabelStr;
	}

	/// <summary>
	/// ｼｪｰﾏ連携URLｵｰﾀﾞ番号指定時の識別子設定
	/// </summary>
	public void SetSchOrdernoLabel(String schordernolabel)
	{
		this.schordernolabelStr = schordernolabel;
	}

	/// <summary>
	/// AccessionNo指定時の識別子取得
	/// </summary>
	/// <returns>AccessionNo指定時の識別子</returns>
	public String GetSchAcnoLabel()
	{
		return this.schacnolabelStr;
	}

	/// <summary>
	/// AccessionNo指定時の識別子設定
	/// </summary>
	public void SetSchAcnoLabel(String schacnolabel)
	{
		this.schacnolabelStr = schacnolabel;
	}

	/// <summary>
	/// 使用CR機種数取得
	/// </summary>
	/// <returns>使用CR機種数 ﾃﾞｨﾌｫﾙﾄ：1</returns>
	public String GetUsecrKind()
	{
		return this.usecrkindStr;
	}

	/// <summary>
	/// 使用CR機種数設定
	/// </summary>
	public void SetUsecrKind(String usecrkind)
	{
		this.usecrkindStr = usecrkind;
	}

	/// <summary>
	/// 監査証跡保存ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>監査証跡保存ﾌﾗｸﾞ</returns>
	public String GetAuditFlag()
	{
		return this.auditflagStr;
	}

	/// <summary>
	/// 監査証跡保存ﾌﾗｸﾞ設定
	/// </summary>
	public void SetAuditFlag(String auditflag)
	{
		this.auditflagStr = auditflag;
	}

	/// <summary>
	/// 特注処理時に各ｼｽﾃﾑに割当てる識別ｺｰﾄﾞ取得
	/// </summary>
	/// <returns>特注処理時に各ｼｽﾃﾑに割当てる識別ｺｰﾄﾞ</returns>
	public String GetSystemID()
	{
		return this.systemidStr;
	}

	/// <summary>
	/// 特注処理時に各ｼｽﾃﾑに割当てる識別ｺｰﾄﾞ設定
	/// </summary>
	public void SetSystemID(String systemid)
	{
		this.systemidStr = systemid;
	}

	/// <summary>
	/// 検像終了確認自動ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>検像終了確認自動ﾌﾗｸﾞ 0：自動化しない（ﾃﾞｨﾌｫﾙﾄ）　1：自動化する</returns>
	public String GetKenzouFix()
	{
		return this.kenzoufixStr;
	}

	/// <summary>
	/// 検像終了確認自動ﾌﾗｸﾞ設定
	/// </summary>
	public void SetKenzouFix(String kenzoufix)
	{
		this.kenzoufixStr = kenzoufix;
	}

	/// <summary>
	/// 検像技師ﾊﾟｽﾜｰﾄﾞ確認ﾌﾗｸﾞ取得
	/// </summary>
	/// <returns>検像技師ﾊﾟｽﾜｰﾄﾞ確認ﾌﾗｸﾞ　0：表示しない　1：表示する（ﾃﾞｨﾌｫﾙﾄ）</returns>
	public String GetKenzouPassDlg()
	{
		return this.kenzoupassdlgStr;
	}

	/// <summary>
	/// 検像技師ﾊﾟｽﾜｰﾄﾞ確認ﾌﾗｸﾞ設定
	/// </summary>
	public void SetKenzouPassDlg(String kenzoupassdlg)
	{
		this.kenzoupassdlgStr = kenzoupassdlg;
	}

	/// <summary>
	/// 検像ﾋﾞｭｰｱとの接続待ち時間(秒)取得
	/// </summary>
	/// <returns>検像ﾋﾞｭｰｱとの接続待ち時間(秒)</returns>
	public String GetKenzouConnectWait()
	{
		return this.kenzouconnectwaitStr;
	}

	/// <summary>
	/// 検像ﾋﾞｭｰｱとの接続待ち時間(秒)設定
	/// </summary>
	public void SetKenzouConnectWait(String kenzouconnectwait)
	{
		this.kenzouconnectwaitStr = kenzouconnectwait;
	}

	/// <summary>
	/// 検像ﾋﾞｭｰｱ連携での操作待ち時間(秒)取得
	/// </summary>
	/// <returns>検像ﾋﾞｭｰｱ連携での操作待ち時間(秒)</returns>
	public String GetKenzouShowWait()
	{
		return this.kenzoushowwaitStr;
	}

	/// <summary>
	/// 検像ﾋﾞｭｰｱ連携での操作待ち時間(秒)設定
	/// </summary>
	public void SetKenzouShowWait(String kenzoushowwait)
	{
		this.kenzoushowwaitStr = kenzoushowwait;
	}

	/// <summary>
	/// 検査MPPS-Flg取得
	/// </summary>
	/// <returns>検査MPPS-Flg</returns>
	public String GetMppsKensaFlg()
	{
		return this.mppsKensaFlgStr;
	}

	/// <summary>
	/// 検査MPPS-Flg設定
	/// </summary>
	public void SetMppsKensaFlg(String mppsFlg)
	{
		this.mppsKensaFlgStr = mppsFlg;
	}

	// 2010.10.18 Add K.Shinohara Start
	/// <summary>
	/// 撮影系MPPS-Flg取得
	/// </summary>
	/// <returns>撮影系MPPS-Flg</returns>
	public String GetMppsSatueiFlg()
	{
		return this.mppsSatueiFlgStr;
	}

	/// <summary>
	/// 撮影系MPPS-Flg設定
	/// </summary>
	public void SetMppsSatueiFlg(String mppsFlg)
	{
		this.mppsSatueiFlgStr = mppsFlg;
	}
	// 2010.10.18 Add K.Shinohara End

	/// <summary>
	/// MPPS-Flg取得
	/// </summary>
	/// <returns>MPPS-Flg</returns>
	public String GetMppsFlg()
	{
		return this.mppsFlgStr;
	}

	/// <summary>
	/// MPPS-Flg設定
	/// </summary>
	public void SetMppsFlg(String mppsFlg)
	{
		this.mppsFlgStr = mppsFlg;
	}

	/// <summary>
	/// MPPS-User取得
	/// </summary>
	/// <returns>MPPS-User</returns>
	public String GetMppsUser()
	{
		return this.mppsUserStr;
	}

	/// <summary>
	/// MPPS-User設定
	/// </summary>
	public void SetMppsUser(String mppsUser)
	{
		this.mppsUserStr = mppsUser;
	}

	/// <summary>
	/// MPPS-Password取得
	/// </summary>
	/// <returns>MPPS-Password</returns>
	public String GetMppsPassword()
	{
		return this.mppsPasswordStr;
	}

	/// <summary>
	/// MPPS-Password設定
	/// </summary>
	public void SetMppsPassword(String mppsPassword)
	{
		this.mppsPasswordStr = mppsPassword;
	}

	/// <summary>
	/// MPPS-ServiceName取得
	/// </summary>
	/// <returns>MPPS-ServiceName取得</returns>
	public String GetMppsServiceName()
	{
		return this.mppsServiceNameStr;
	}

	/// <summary>
	/// MPPS-ServiceName設定
	/// </summary>
	public void SetMppsServiceName(String mppsServiceName)
	{
		this.mppsServiceNameStr = mppsServiceName;
	}

	/// <summary>
	/// MPPS-Interval取得
	/// </summary>
	/// <returns>MPPS-Interval取得</returns>
	public String GetMppsInterval()
	{
		return this.mppsIntervalStr;
	}

	/// <summary>
	/// MPPS-Interval取得
	/// </summary>
	/// <returns>MPPS-Interval取得</returns>
	public int GetMppsIntervalInt()
	{
		int retInt = 2;
		try
		{
			if (this.mppsIntervalStr.length() > 0)
			{
				retInt = Integer.parseInt(this.mppsIntervalStr);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// MPPS-Interval設定
	/// </summary>
	public void SetMppsInterval(String mppsInterval)
	{
		this.mppsIntervalStr = mppsInterval;
	}

	/// <summary>
	/// MPPS-RetryCount取得
	/// </summary>
	/// <returns>MPPS-RetryCount取得</returns>
	public String GetMppsRetryCount()
	{
		return this.mppsRetryCountStr;
	}

	/// <summary>
	/// MPPS-RetryCount設定
	/// </summary>
	public void SetMppsRetryCount(String mppsRetryCount)
	{
		this.mppsRetryCountStr = mppsRetryCount;
	}

	/// <summary>
	/// MPPS-FilmSetType取得
	/// </summary>
	/// <returns>MPPS-FilmSetType取得</returns>
	public String GetMppsFilmSetType()
	{
		return this.mppsFilmSetTypeStr;
	}

	/// <summary>
	/// MPPS-FilmSetType設定
	/// </summary>
	public void SetMppsFilmSetType(String mppsFilmSetType)
	{
		this.mppsFilmSetTypeStr = mppsFilmSetType;
	}

	/// <summary>
	/// MPPS-Fix取得
	/// </summary>
	/// <returns>MPPS-Fix取得</returns>
	public String GetMppsFix()
	{
		return this.mppsFixStr;
	}

	/// <summary>
	/// MPPS-Fix設定
	/// </summary>
	public void SetMppsFix(String mppsFix)
	{
		this.mppsFixStr = mppsFix;
	}

	/// <summary>
	/// MPPS-PasswdDlg取得
	/// </summary>
	/// <returns>MPPS-PasswdDlg取得</returns>
	public String GetMppsPasswdDlg()
	{
		return this.mppsPasswdDlgStr;
	}

	/// <summary>
	/// MPPS-PasswdDlg設定
	/// </summary>
	public void SetMppsPasswdDlg(String mppsPasswdDlg)
	{
		this.mppsPasswdDlgStr = mppsPasswdDlg;
	}

	/// <summary>
	/// MPPS-UnitFlg取得
	/// </summary>
	/// <returns>MPPS-UnitFlg取得</returns>
	public String GetMppsUnitFlg()
	{
		return this.mppsUnitFlgStr;
	}

	/// <summary>
	/// MPPS-UnitFlg設定
	/// </summary>
	public void SetMppsUnitFlg(String mppsUnitFlg)
	{
		this.mppsUnitFlgStr = mppsUnitFlg;
	}

	/// <summary>
	/// MWM-User取得
	/// </summary>
	/// <returns>MPPS-User取得</returns>
	public String GetMwmUser()
	{
		return this.mwmUserStr;
	}

	/// <summary>
	/// MWM-User設定
	/// </summary>
	public void SetMwmUser(String mwmUser)
	{
		this.mwmUserStr = mwmUser;
	}

	/// <summary>
	/// MWM-Password取得
	/// </summary>
	/// <returns>MPPS-Password取得</returns>
	public String GetMwmPassword()
	{
		return this.mwmPasswordStr;
	}

	/// <summary>
	/// MWM-Password設定
	/// </summary>
	public void SetMwmPassword(String mwmPassword)
	{
		this.mwmPasswordStr = mwmPassword;
	}

	/// <summary>
	/// MWM-ServiceName取得
	/// </summary>
	/// <returns>MPPS-ServiceName取得</returns>
	public String GetMwmServiceName()
	{
		return this.mwmServiceNameStr;
	}

	/// <summary>
	/// MWM-ServiceName設定
	/// </summary>
	public void SetMwmServiceName(String mwmServiceName)
	{
		this.mwmServiceNameStr = mwmServiceName;
	}

	/// <summary>
	/// MWM-UketukeEntry取得
	/// </summary>
	/// <returns>MPPS-UketukeEntry取得</returns>
	public String GetMwmUketukeEntry()
	{
		return this.mwmUketukeEntryStr;
	}

	/// <summary>
	/// MWM-UketukeEntry設定
	/// </summary>
	public void SetMwmUketukeEntry(String mwmUketukeEntry)
	{
		this.mwmUketukeEntryStr = mwmUketukeEntry;
	}

	/// <summary>
	/// MWM-ReceiptEntry取得
	/// </summary>
	/// <returns>MPPS-ReceiptEntry取得</returns>
	public String GetMwmReceiptEntry()
	{
		return this.mwmReceiptEntryStr;
	}

	/// <summary>
	/// MWM-ReceiptEntry設定
	/// </summary>
	public void SetMwmReceiptEntry(String mwmReceiptEntry)
	{
		this.mwmReceiptEntryStr = mwmReceiptEntry;
	}

	// 2010.07.30 Add Y.Shibata Start
	/// <summary>
	/// 本日造影剤ID取得
	/// </summary>
	/// <returns>本日造影剤ID取得</returns>
	public String GetTodayZoueizaiIdstr()
	{
		return this.todayZoueizaiIdstr;
	}

	/// <summary>
	/// 本日造影剤ID設定
	/// </summary>
	public void SetTodayZoueizaiIdstr(String todayZoueizaiId)
	{
		this.todayZoueizaiIdstr = todayZoueizaiId;
	}

	/// <summary>
	/// 本日造影剤区分取得
	/// </summary>
	/// <returns>本日造影剤区分取得</returns>
	public String GetTodayZoueizaiKbnstr()
	{
		return this.todayZoueizaiKbnstr;
	}

	/// <summary>
	/// 本日造影剤区分設定
	/// </summary>
	public void SetTodayZoueizaiKbnstr(String todayZoueizaiKbn)
	{
		this.todayZoueizaiKbnstr = todayZoueizaiKbn;
	}
	// 2010.07.30 Add Y.Shibata End

	/// <summary>
	/// MWMDBコネクションの設定有無
	/// </summary>
	/// <returns></returns>
	public boolean IsMWMDBConnectionEnabled()
	{
		boolean retBool = false;

		if (Configuration.GetInstance().GetMwmServiceName().length()	> 0 &&
			Configuration.GetInstance().GetMwmUser().length()			> 0 &&
			Configuration.GetInstance().GetMwmPassword().length()		> 0)
		{
			retBool = true;
		}

		return retBool;
	}

	/*
	/// <summary>
	/// ボタン定義のArrayListを取得
	/// </summary>
	/// <returns>ボタン定義</returns>
	public ArrayList GetMenuButtonList()
	{
		return this.menuButtonList;
	}
	*/

	/*
	/// <summary>
	/// ボタン定義のArrayListを設定
	/// </summary>
	public void SetMenuButtonList(ArrayList m_menuButtonList)
	{
		this.menuButtonList = m_menuButtonList;
	}
	*/

	/*
	/// <summary>
	/// 表示一覧IDリストを取得
	/// </summary>
	/// <returns>表示一覧IDリスト</returns>
	public ArrayList GetShowListIDList()
	{
		return this.showListIDList;
	}
	*/

	/*
	/// <summary>
	/// 表示一覧IDリストへIDを追加する
	/// </summary>
	public void AddShowListIDList(String idStr)
	{
		if (!this.showListIDList.Contains(idStr))
		{
			this.showListIDList.Add(idStr);
		}
	}
	*/

	/*
	/// <summary>
	/// 表示一覧IDリストからIDを削除する
	/// </summary>
	public void DelShowListIDList(String idStr)
	{
		if (this.showListIDList.Contains(idStr))
		{
			this.showListIDList.Remove(idStr);
		}
	}
	*/

	/*
	// 2010.10.26 Add K.Shinohara Start
	// 中止部位表示フラグ（一覧用）
	public boolean GetStopBuiShowFlg()
	{
		// システムIDの設定を元に、中止部位表示フラグを制御する
		// 標準
		if (Configuration.GetInstance().GetSystemID().Equals(String.Empty))
		{
			return true;
		}


		return false;
	}
	// 2010.10.26 Add K.Shinohara End
	*/

	// システムパラメータ２
	// 2011.01.24 Add T.Nishikubo Start KANRO-R-22
	/// <summary>
	/// 受付ダイアログ患者情報拡張フラグを取得
	/// </summary>
	/// <returns></returns>
	public boolean GetReceiptExtendPatientInfoFlg()
	{
		return this.receiptExtendPatientInfoFlg;
	}
	/// <summary>
	/// 受付ダイアログ患者情報拡張フラグを設定
	/// </summary>
	/// <param name="receiptExtendPatientInfoFlg"></param>
	public void SetReceiptExtendPatientInfoFlg(String receiptExtendPatientInfoFlg)
	{
		this.receiptExtendPatientInfoFlg = false;
		if (receiptExtendPatientInfoFlg != null &&
			receiptExtendPatientInfoFlg.equals(CommonString.EXTEND_PATIENT_INFO_ON))
		{
			this.receiptExtendPatientInfoFlg = true;
		}
	}
	// 2011.01.24 Add T.Nishikubo End

	// 2011.01.24 Add DD.Chinh Start KANRO-R-19
	/// <summary>
	/// プレチェック検索条件機能フラグを取得
	/// </summary>
	/// <returns></returns>
	public boolean GetPreStsFlg()
	{
		return this.preStsFlg;
	}
	/// <summary>
	/// プレチェック検索条件機能フラグを設定
	/// </summary>
	/// <param name="receiptExtendPatientInfoFlg"></param>
	public void SetPreStsFlg(String flgValue)
	{
		this.preStsFlg = false;
		if (flgValue != null &&
			flgValue.equals(CommonString.PRECHECK_STATUS_ON))
		{
			this.preStsFlg = true;
		}
	}
	// 2011.01.24 Add DD.Chinh End

	// 2011.03.18 Add Yk.Suzuki@CIJ Start A0005
	/// <summary>
	/// 削除オーダフラグを取得
	/// </summary>
	/// <returns>削除オーダフラグ</returns>
	public boolean GetOrderDeleteFlg()
	{
		return this.orderDeleteFlg;
	}
	/// <summary>
	/// 削除オーダフラグを設定
	/// </summary>
	/// <param name="flgValue">削除オーダフラグ</param>
	public void SetOrderDeleteFlg(String flgValue)
	{
		this.orderDeleteFlg = false;
		if (false == StringUtils.isEmpty(flgValue) &&
				CommonString.SMILE_ORDER_DELETE_ON.equals(flgValue))
		{
			this.orderDeleteFlg = true;
		}
	}
	// 2011.03.18 Add Yk.Suzuki@CIJ End

	/*


	// ロジック

	/// <summary>
	/// ダイナミックプロパティ項目の取得(文字列)
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>項目の値</returns>
	public String GetDynamicPropertyString(String key)
	{
		String str = "";
		try
		{
			str = ((String)(this.configurationAppSettings.GetValue(key, typeof(String))));
		}
		catch (Exception e)
		{
			logger.fatal(e);
			throw e;
		}
		return str;
	}

	/// <summary>
	/// ダイナミックプロパティ項目の取得(数値)
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>項目の値</returns>
	public int GetDynamicPropertyInt(String key)
	{
		int retInt = 0;
		try
		{
			retInt = ((int)(this.configurationAppSettings.GetValue(key, typeof(int))));
		}
		catch (Exception e)
		{
			logger.fatal(e);
			throw e;
		}
		return retInt;
	}

	/// <summary>
	/// ダイナミックプロパティ項目の取得(浮動少数点)
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>項目の値</returns>
	public float GetDynamicPropertyFloat(String key)
	{
		float retFloat = 0;
		try
		{
			retFloat = ((float)(this.configurationAppSettings.GetValue(key, typeof(float))));
		}
		catch (Exception e)
		{
			logger.fatal(e);
			throw e;
		}
		return retFloat;
	}

	/// <summary>
	/// ダイナミックプロパティ項目の取得(ブーリアン)
	/// </summary>
	/// <param name="key">キー</param>
	/// <returns>項目の値</returns>
	public boolean GetDynamicPropertyBool(String key)
	{
		boolean retBool = false;
		try
		{
			retBool = ((bool)(this.configurationAppSettings.GetValue(key, typeof(bool))));
		}
		catch (Exception e)
		{
			logger.fatal(e);
			throw e;
		}
		return retBool;
	}
	*/

	/// <summary>
	/// 項目変換情報より名称を取得する
	/// </summary>
	/// <param name="dt">項目変換情報</param>
	/// <param name="itemID">識別子</param>
	/// <param name="value">値</param>
	/// <returns></returns>
	public String GetCodeConvertValue(DataTable dt, String itemID, String value, Connection con)
	{
		String retStr = "";

		try
		{
			if (dt == null)
			{
				dt = Configuration.GetInstance().GetRRisCodeConvertMaster(con);
			}

			for (int i=0; i<dt.getRowCount(); i++)
			{
				DataRow row = dt.getRows().get(i);

				String rowItemID = row.get(MasterUtil.RIS_ITEMID).toString();
				String rowValue  = row.get(MasterUtil.RIS_ITEMVALUE).toString();

				//識別子と値が一致した場合
				if (rowItemID.equals(itemID) && rowValue.equals(value))
				{
					retStr = row.get(MasterUtil.RIS_VALUELABEL).toString();
					break;
				}
			}
		}
		catch(Exception ex)
		{
			logger.fatal(ex);
		}

		return retStr;
	}

	/*
	/// <summary>
	/// 項目変換情報より項目リストを取得する
	/// </summary>
	/// <param name="dt">項目変換情報</param>
	/// <param name="itemID">識別子</param>
	/// <returns></returns>
	public ArrayList GetCodeConvertList(DataTable dt, String itemID)
	{
		ArrayList retList = new ArrayList();

		try
		{
			if (dt == null)
			{
				dt = Configuration.GetInstance().GetRRisCodeConvertMaster();
			}

			for (int i=0; i<dt.Rows.Count; i++)
			{
				DataRow row = dt.Rows[i];

				String rowItemID = row[MasterUtil.RIS_ITEMID].toString();

				//識別子が一致した場合
				if (rowItemID == itemID)
				{
					retList.Add(row);
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retList;
	}
	*/

	/// <summary>
	/// システムパラムの情報を取得する
	/// </summary>
	/// <param name="dt">情報</param>
	/// <param name="subID">サブID</param>
	/// <param name="value">カラム名</param>
	/// <returns></returns>
	public String GetSystemParamValue(DataTable dt, String subID, String value)
	{
		String retStr = "";

		try
		{
			if (dt != null)
			{
				for (int i=0; i<dt.getRowCount(); i++)
				{
					DataRow row = dt.getRows().get(i);

					String rowSubID  = row.get(RisSystemParamTbl.SUBID_COLUMN).toString();

					//サブIDが一致＆カラムが存在する
					if (rowSubID != null && rowSubID.equals(subID) && Arrays.asList(dt.getColmunNames()).indexOf(value) > -1)
					{
						retStr = row.get(value).toString();
						break;
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retStr;
	}

	/*
	/// <summary>
	/// ステータスを文字列に変換する
	/// </summary>
	/// <param name="dt">ステータス定義情報</param>
	/// <param name="statusStr">ステータス</param>
	/// <returns></returns>
	public String GetStatusString(DataTable dt, String statusStr)
	{
		String retStr = "";

		try
		{
			if (dt == null)
			{
				dt = Configuration.GetInstance().GetRRisStatusDefine();
			}

			for (int i=0; i<dt.Rows.Count; i++)
			{
				DataRow row = dt.Rows[i];

				String rowStatus = row[MasterUtil.RIS_STAUSCODE].toString();

				//ステータスが一致した場合
				if (rowStatus == statusStr)
				{
					retStr = row[MasterUtil.RIS_SHORTLABEL].toString();
					break;
				}
			}
		}
		catch(Exception ex)
		{
			logger.fatal(ex);
		}

		return retStr;
	}

	/// <summary>
	/// オーダコメントIDから文字列を取得する
	/// </summary>
	/// <param name="commentID">オーダコメントID</param>
	/// <returns></returns>
	public String GetOrderCommentValue(DataTable dt, String commentID)
	{
		String retStr = "";
		try
		{
			//区切り文字を取得
			char delimiterStr = char.Parse(this.markercharacterStr);

			//コメントIDを分割
			String [] commentList = commentID.Split(delimiterStr);

			//定型コメントマスタを取得
			DataTable cmtDt = dt;
			if (dt == null)
			{
				cmtDt = GetRRisPreDefinedCommentMaster();
			}

			//コメントIDでループ
			for (int i=0; i<commentList.length(); i++)
			{
				String id = commentList[i];
				if (id.length() > 0)
				{
					//コメントを取得し、なければ値をそのまま設定する
					String cmtStr = "";
					for (int j=0; j<cmtDt.Rows.Count; j++)
					{
						//ID一致＆ｵｰﾀﾞｺﾒﾝﾄの場合
						if (cmtDt.Rows[j][MasterUtil.RIS_COMMENT_ID].toString() == id &&
							cmtDt.Rows[j][MasterUtil.RIS_RIS_COMMENTKBN].toString() == CommonString.PRECOMMENTKBN_ORDER)
						{
							cmtStr = cmtDt.Rows[j][MasterUtil.RIS_COMMENT_NAME].toString();
							break;
						}
					}

					if (cmtStr.length() <= 0)
					{
						cmtStr = id;
					}

					//戻り値に設定
					if (retStr.length() <= 0)
					{
						retStr  = cmtStr;
					}
					else
					{
						retStr += " " + cmtStr;
					}
				}
			}
		}
		catch(Exception ex)
		{
			logger.fatal(ex);
		}
		return retStr;
	}

	/// <summary>
	/// 検査種別IDリストを取得する(カンマ区切り)
	/// </summary>
	/// <returns></returns>
	public String GetKensatypeIDs(String typeID)
	{
		String retStr = "";
		DataTable dt = Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_KENSATYPEMASTER, false);

		//検査種別のループ
		for (int i=0; i<dt.Rows.Count; i++)
		{
			DataRow row = dt.Rows[i];

			//検査種別IDをリストに追加
			String kTypeID = row[MasterUtil.RIS_KENSATYPE_ID].toString();

			boolean addFlg = false;

			// 検査種別のタイプを判断する

			//撮影系の場合
			if (typeID == CommonString.KENSATYPE_SHOW_FLG_PHOTO &&
				row[MasterUtil.RIS_PICTUREFLAG01].toString() == CommonString.KENSATYPE_SHOW_FLG_ON)
			{
				addFlg = true;
			}
			//ポータブルの場合
			else if (typeID == CommonString.KENSATYPE_SHOW_FLG_PORTABLE &&
					 row[MasterUtil.RIS_PICTUREFLAG02].toString() == CommonString.KENSATYPE_SHOW_FLG_ON)
			{
				addFlg = true;
			}
			//検査系の場合
			else if (typeID == CommonString.KENSATYPE_SHOW_FLG_INSPECT &&
					 row[MasterUtil.RIS_PICTUREFLAG03].toString() == CommonString.KENSATYPE_SHOW_FLG_ON)
			{
				addFlg = true;
			}
			//核医学の場合
			else if (typeID == CommonString.KENSATYPE_SHOW_FLG_NUCLEAR &&
					 row[MasterUtil.RIS_PICTUREFLAG04].toString() == CommonString.KENSATYPE_SHOW_FLG_ON)
			{
				addFlg = true;
			}
			// 2011.08.05 Add K.Aizawa@MERX Start NML-CAT3-034
			//業務３の場合
			else if (typeID == CommonString.KENSATYPE_SHOW_FLG_NUCLEAR &&
					 row[MasterUtil.RIS_PICTUREFLAG05].toString() == CommonString.KENSATYPE_SHOW_FLG_ON)
			{
				addFlg = true;
			}
			// 2011.08.05 Add K.Aizawa@MERX End



			if (addFlg)
			{
				if (retStr.length() <= 0)
				{
					retStr  = kTypeID;
				}
				else
				{
					retStr += "," + kTypeID;
				}
			}
		}
		return retStr;
	}

	/// <summary>
	/// 検査タイプIDを元に検査種別フィルタフラグ条件を取得する
	/// </summary>
	/// <param name="dt">検査種別情報</param>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <returns></returns>
	public String GetKensatypeFilterFlg(DataTable dt, String kensatypeID)
	{
		String retStr = "";

		try
		{
			if (dt == null)
			{
				//検査種別マスタ取得
				dt = GetRRisKensaTypeMaster();
			}

			//検査種別マスタよりIDを取得
			MasterUtil mUtil = new MasterUtil();
			String id = mUtil.FindData(dt, MasterUtil.RIS_ID, MasterUtil.RIS_KENSATYPE_ID, kensatypeID);
			if (id.length() > 0)
			{
				int idInt = Integer.parseInt(id);

				for (int i=1; i<CommonString.KENSATYPEFILTER_LENGTH-1; i++)
				{
					if (idInt == 1 || i == idInt)
					{
						retStr += "1%";
						break;
					}
					else
					{
						retStr += "_";
					}
				}
			}


		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retStr;
	}

	/// <summary>
	/// 検査種別IDIntを元にフィルタと一致するかを戻す
	/// </summary>
	/// <param name="kTypeIDInt">検査種別IDInt</param>
	/// <param name="filterStr">フィルタ文字列</param>
	/// <returns></returns>
	public boolean IsMatchKensatypeIDInt(int kTypeIDInt, String filterStr)
	{
		boolean retBool = false;
		try
		{
			if (kTypeIDInt != -1 && filterStr.length() > 0)
			{
				if (filterStr.length() >= kTypeIDInt &&
					filterStr.SubString(kTypeIDInt-1, 1) == CommonString.FLG_ON)
				{
					retBool = true;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retBool;
	}
	*/

	/// <summary>
	/// 実績詳細区分を取得する
	/// </summary>
	/// <param name="kTypeDt">検査種別情報</param>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <returns></returns>
	public String GetOperationKbn(DataTable kTypeDt, String kensatypeID, Connection con) throws Exception
	{
		String retStr = CommonString.OPE_KBN_INSPECT;

		if (kTypeDt == null)
		{
			kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster(con);
		}

		if (kTypeDt != null)
		{
			//検査種別情報のループ
			for (int i=0; i<kTypeDt.getRowCount(); i++)
			{
				DataRow row = kTypeDt.getRows().get(i);

				//検査種別IDの一致チェック
				if (row.get(MasterUtil.RIS_KENSATYPE_ID).toString().equals(kensatypeID))
				{
					String screenform = row.get(MasterUtil.RIS_SCREENFORM).toString();
					String pictureFlg01 = row.get(MasterUtil.RIS_PICTUREFLAG01).toString();
					String pictureFlg02 = row.get(MasterUtil.RIS_PICTUREFLAG02).toString();

					if (CommonString.SCREEN_FORM_INSPECT.equals(screenform))
					{
						//検査系
						retStr = CommonString.OPE_KBN_INSPECT;
						break;
					}
					else if (CommonString.SCREEN_FORM_PHOTO.equals(screenform) ||
							 (CommonString.PICTURE_FLG_ON.equals(pictureFlg01) ||
							  CommonString.PICTURE_FLG_ON.equals(pictureFlg02)))
					{
						//撮影系
						retStr = CommonString.OPE_KBN_PHOTO;
						break;
					}
				}
			}
		}

		return retStr;
	}

	/// <summary>
	/// MMWM-コネクション情報を取得する
	/// </summary>
	/// <returns></returns>
	public ConnectionInfoBean GetMWMConnectionInfo()
	{
		logger.debug("begin");
		ConnectionInfoBean retBean = null;
		try
		{
			retBean = new ConnectionInfoBean();
			retBean.SetUser(this.GetMwmUser());
			retBean.SetPassword(this.GetMwmPassword());
			retBean.SetService(this.GetMwmServiceName());

			//値の確認
			if (
				retBean.GetService().length()  <= 0 ||
				retBean.GetUser().length()     <= 0 ||
				retBean.GetPassword().length() <= 0)
			{
				//どれかが空の場合はnullを戻す
				retBean = null;
			}
		}
		catch (Exception ex)
		{
			logger.error(ex);
		}

		return retBean;
	}

	// 2010.09.15 Add K.Shinohara Start
	/// <summary>
	/// 検査種別マスタに登録されているモダリティを取得する
	/// </summary>
	/// <param name="kTypeDt"></param>
	/// <param name="kensatypeID"></param>
	/// <returns></returns>
	public String GetKensaTypeModality(DataTable kTypeDt, String kensatypeID, Connection con) throws Exception
	{
		String retStr = "";

		if (kTypeDt == null)
		{
			kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster(con);
		}

		if (kTypeDt != null)
		{
			//検査種別情報のループ
			for (int i=0; i<kTypeDt.getRowCount(); i++)
			{
				DataRow row = kTypeDt.getRows().get(i);

				//検査種別IDの一致チェック
				if (kensatypeID.equals(row.get(MasterUtil.RIS_KENSATYPE_ID).toString()))
				{
					retStr = row.get(MasterUtil.RIS_MODALITY).toString();
					break;
				}
			}
		}

		return retStr;
	}
	// 2010.09.15 Add K.Shinohara End

	/*
	/// <summary>
	/// 帳票情報を取得する
	/// </summary>
	/// <param name="printID">プリントID</param>
	/// <returns></returns>
	public PrintMasterBean GetPrintMasterBean(String printID)
	{
		//帳票定義の設定
		CommonUtil.InitPrintMaster();

		PrintMasterBean retBean = null;
		if (this.printInfoHash != null &&
			this.printInfoHash.containsKey(printID))
		{
			retBean = (PrintMasterBean)this.printInfoHash[printID];
		}
		return retBean;
	}

    /// <summary>
    /// 技医のデフォルトIDの取得
    /// </summary>
    /// <returns>技医のデフォルトID</returns>
    public String GetDefaultGisiIDStr()
    {
        return gisiIDStr;
    }

    /// <summary>
    /// 技医のデフォルトIDの取得
    /// </summary>
    /// <param name="gisiID">技医のデフォルトID</param>
    public void SetDefaultGisiIDStr(String gisiID)
    {
        if (gisiID != null && gisiID.length() > 0)
        {
            gisiIDStr = gisiID;
        }
    }

    /// <summary>
    /// 看護婦のデフォルトIDの取得
    /// </summary>
    /// <returns>看護婦のデフォルトID</returns>
    public String GetDefaultKangosiIDStr()
    {
        return kangosiIDStr;
    }

    /// <summary>
    /// 看護婦のデフォルトIDの取得
    /// </summary>
    /// <param name="kangosiID">看護婦のデフォルトID</param>
    public void SetDefaultKangosiIDStr(String kangosiID)
    {
        if (kangosiID != null && kangosiID.length() > 0)
        {
            kangosiIDStr = kangosiID;
        }
    }

    /// <summary>
    /// 放医のデフォルトIDの取得
    /// </summary>
    /// <returns>放医のデフォルトID</returns>
    public String GetDefaultKensaDoctorIDStr()
    {
        return kensaDoctorIDStr;
    }

    /// <summary>
    /// 放医のデフォルトIDの取得
    /// </summary>
    /// <param name="kensaDoctorID">放医のデフォルトID</param>
    public void SetDefaultKensaDoctorIDStr(String kensaDoctorID)
    {
        if (kensaDoctorID != null && kensaDoctorID.length() > 0)
        {
            kensaDoctorIDStr = kensaDoctorID;
        }
    }

    /// <summary>
    /// 施医のデフォルトIDの取得
    /// </summary>
    /// <returns>施医のデフォルトID</returns>
    public String GetDefaultEnforceDoctorIDStr()
    {
        return enforceDoctorIDStr;
    }

    /// <summary>
    /// 施医のデフォルトIDの取得
    /// </summary>
    /// <param name="enforceDoctorID">施医のデフォルトID</param>
    public void SetDefaultEnforceDoctorIDStr(String enforceDoctorID)
    {
        if (enforceDoctorID != null && enforceDoctorID.length() > 0)
        {
            enforceDoctorIDStr = enforceDoctorID;
        }
    }

	/// <summary>
	/// 帳票プレビュー設定の設定
	/// </summary>
	/// <param name="hash">帳票プレビュー設定</param>
	public void SetPreviewAllHash(Hashtable previewAll)
	{
		this.previewAllHash = previewAll;
	}

	/// <summary>
	/// 帳票プレビュー設定の取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public Hashtable GetPreviewAllHash()
	{
		return this.previewAllHash;
	}

	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// チーム情報使用フラグ設定
	/// </summary>
	/// <param name="teamInfoFlg"></param>
	public void SetTeamInfoFlg(boolean teamInfoFlg)
	{
		this.teamInfoDataFlg	= teamInfoFlg;
	}

	/// <summary>
	/// チーム情報使用フラグ取得
	/// </summary>
	/// <param name="teamInfoFlg"></param>
	/// <returns></returns>
	public boolean GetTeamInfoFlg()
	{
		return this.teamInfoDataFlg;
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	// 2011.06.24 Add K.Shinohara Start A0060
	/// <summary>
	/// 日未定機能ON/OFFフラグの設定
	/// </summary>
	/// <param name="unKnownDate">日未定機能ON/OFFフラグ</param>
	public void SetUnKnownDate(boolean unKnownDate)
	{
		this.unKnownDateBool = unKnownDate;
	}

	/// <summary>
	/// 日未定機能ON/OFFフラグの取得
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public boolean IsUnKnownDate()
	{
		return this.unKnownDateBool;
	}

	/// <summary>
	/// 日未定機能-日未定時刻(文字)の取得
	/// </summary>
	/// <returns>日未定機能-日未定時刻(文字)</returns>
	public String GetUnKnownDateString()
	{
		return unKnownDateString;
	}

	/// <summary>
	/// 日未定機能-日未定時刻(文字)の取得
	/// </summary>
	/// <param name="unKnownDate">日未定機能-日未定時刻(文字)</param>
	public void SetUnKnownDateString(String unKnownDate)
	{
		if (unKnownDate != null && unKnownDate.length() > 0)
		{
			this.unKnownDateString = unKnownDate;
		}
		else
		{
			//値が空の場合は機能OFFにする
			this.unKnownDateBool = false;
		}
	}
	// 2011.06.24 Add K.Shinohara End

	/**
	 * @return mwmtimeout
	 */
	public Integer getMwmtimeout() {
		return mwmtimeout;
	}

	/**
	 * @param mwmtimeout セットする mwmtimeout
	 */
	public void setMwmtimeout(Integer mwmtimeout) {
		this.mwmtimeout = mwmtimeout;
	}

	public String getKanaromatext() {
		return kanaromatext;
	}

	public void setKanaromatext(String kanaromatext) {
		this.kanaromatext = kanaromatext;
	}

	/*
	// 2011.08.18 Add T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
	/// <summary>
	/// 部位進捗情報設定
	/// </summary>
	/// <param name="codeDt"></param>
	public void SetCodeConvertBuiStatus(DataTable codeDt)
	{
		// クリア
		codeConvertBuiStatus.clear();
		if (codeDt != null && codeDt.Rows.Count > 0)
		{
			// 部位進捗情報取得
			ArrayList buiStatusList = Configuration.GetInstance().GetCodeConvertList(codeDt, CommonString.CODECONVERT_ID_BUISTATUS);

			for (int j = 0; j < buiStatusList.Count; j++)
			{
				DataRow buiRow = (DataRow)buiStatusList[j];
				if (buiRow != null)
				{
					String buiStatusCode = buiRow[MasterUtil.RIS_ITEMVALUE].toString();
					codeConvertBuiStatus.Add(buiStatusCode, buiRow);
				}
			}
		}
	}

	/// <summary>
	/// 部位進捗情報取得
	/// </summary>
	/// <returns></returns>
	public Hashtable GetCodeConvertBuiStatus()
	{
		return codeConvertBuiStatus;
	}
	// 2011.08.18 Add T.Ootsuka@MERX End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	/// <summary>
	/// オーダ種別情報より名称を取得する
	/// </summary>
	/// <param name="dt">オーダ種別情報</param>
	/// <param name="itemID">識別子</param>
	/// <param name="value">値</param>
	/// <returns></returns>
	public ArrayList GetExamTimingDefineList(DataTable dt, String value)
	{
		ArrayList TimingDefineList = new ArrayList();
		try
		{
			if (dt == null)
			{
				dt = Configuration.GetInstance().GetRRisExamTimingDefine();
			}
			for (int i=0; i<dt.Rows.Count; i++)
			{
				DataRow row = dt.Rows[i];
				String rowValue  = row[MasterUtil.RIS_EXAM_TIMING].toString();

				//識別子と値が一致した場合
				if (rowValue == value)
				{
					TimingDefineList.Add(row[MasterUtil.RIS_LABEL].toString());
					TimingDefineList.Add(row[MasterUtil.RIS_COLOR].toString());
					TimingDefineList.Add(row[MasterUtil.RIS_COLORBK].toString());
					break;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return TimingDefineList;
	}
	// 2011.11.22 Add NSK_M.Ochiai End
	*/

}
