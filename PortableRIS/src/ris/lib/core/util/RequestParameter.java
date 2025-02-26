package ris.lib.core.util;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.bean.TerminalInfoBean;
import ris.portable.common.Const;

/// <summary>
/// 検索条件IDクラス の概要の説明です。
///
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.20	: 112478 / A.Kobayashi	: original
///
///
/// </summary>
public class RequestParameter
{
	private static Log logger = LogFactory.getLog(RequestParameter.class);

	private String					patientIDStr			= "";							// 次画面で初期表示する患者情報を示すID（DBカラムのKANJA_IDに対応）
	private String					patientKanjiNameStr		= "";
	private String					requestIDStr			= "";							// 次画面で初期表示するオーダ情報等を示すID
	private String					orderNoStr				= "";							// オーダNo
	private boolean					sessionBool				= false;						// StandardWindowControllerに保持している
	//  RequestParameterを使用することを明示するフラグ
	//  これがtrueのときはStandardWindowControllerで保持されている
	//  RequestParameterを使用する
	private String					kensaTypeIDStr			= "";							// 次画面で初期表示するオーダ種別の区分を示すID
	//  32:X線シミュレータ  33:CTシミュレータ  34:照射オーダ
	private boolean					reloadFlg				= false;						// 今自分の画面が表示中だったら、それを捨てて作り直すか否かを示すフラグ
	//private DialogResult			dlgRet					= DialogResult.None;			// DialogResult戻り値
	private String					retMessage				= "";							// 汎用メッセージ
	private boolean					orderUpdBool			= false;						// 照射オーダ全更新フラグ
	private String					checkoutIdStr			= null;							// チェックアウトID
	private boolean					projectDetailShowBool	= false;						// 方針詳細直接表示フラグ
	//private Color					formBackColor			= Color.Gainsboro;				// フォーム背景色
	//private Color					fontForeColor			= Color.Black;					// フォント文字色
	private String					systemIDStr			    = "";
	private String					yuusenFlgStr			= "0";
	private Timestamp				kensaTimestamp			= Const.TIMESTAMP_MINVALUE;
	private String					kensaDateAgeStr			= "0";
	private String					kensaTypeNameStr		= "";
	private String					kensaKikiNameStr		= "";
	private String					kensaRoomNameStr		= "";
	private String					treatMachineIDStr		= "";
	private String					treatRoomIDStr			= "";
	private String					kensaDateStr			= "";
	private boolean					unDateFlgBool			= false;						// 日未定予約フラグ
	private String					statusStr				= "";
	private String					appIDStr				= "";
	private boolean					toOperationVisibleFlg	= false;
	private String					templateKbnStr			= "";							// ﾃﾝﾌﾟﾚｰﾄ区分
	private boolean					updateFlgBool			= false;						// 更新フラグ
	private String					satueiMenuIDsStr		= "";							// 撮影メニューID
	//private SENDKIND				kenzouKind				= SENDKIND.UnKnown;				// 検像種別
	//private SENDKIND				kenzouKindRet			= SENDKIND.UnKnown;				// 検像種別(戻り値)
	private TerminalInfoBean		terminalBean			= null;							// 端末情報

	@Override
	public String toString()
	{

		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[RequestParameter]");
		strBuild.append("PATIENT_ID="           + patientIDStr                 + ";");
		strBuild.append("PATIENT_KANJI_NAME="   + patientKanjiNameStr          + ";");
		strBuild.append("REQUEST_ID="           + requestIDStr                 + ";");
		strBuild.append("ORDER_NO="             + orderNoStr                   + ";");
		strBuild.append("SESSION_BOOL="         + sessionBool                  + ";");
		strBuild.append("KENSATYPE_ID="         + kensaTypeIDStr               + ";");
		strBuild.append("RELOAD_FLG="           + reloadFlg                    + ";");
		//strBuild.append("DLGRET="               + dlgRet                       + ";");
		strBuild.append("RETMESSAGE="           + retMessage                   + ";");
		strBuild.append("ORDERUPDBOOL="         + orderUpdBool                 + ";");
		strBuild.append("CHECKOUTID="           + checkoutIdStr                + ";");
		strBuild.append("PROJECTDETAILSHOW="    + projectDetailShowBool        + ";");
		//strBuild.append("FORMBACKCOLOR="        + formBackColor                + ";");
		//strBuild.append("FONTFORECOLOR="        + fontForeColor                + ";");
		strBuild.append("SYSTEMIDSTR="          + systemIDStr				   + ";");
		strBuild.append("YUUSENFLGSTR="         + yuusenFlgStr                 + ";");
		strBuild.append("KENSADATETIME="        + kensaTimestamp.toString()     + ";");
		strBuild.append("KENSADATEAGESTR="      + kensaDateAgeStr              + ";");
		strBuild.append("KENSATYPENAMESTR="     + kensaTypeNameStr             + ";");
		strBuild.append("KENSAKIKINAMESTR="     + kensaKikiNameStr             + ";");
		strBuild.append("KENSAROOMNAMESTR="     + kensaRoomNameStr             + ";");
		strBuild.append("KENSADATESTR="         + kensaDateStr                 + ";");
		strBuild.append("UNDATEFLGBOOL="        + unDateFlgBool                + ";");
		strBuild.append("STATUS="				+ statusStr                    + ";");
		strBuild.append("APP_ID="				+ appIDStr                     + ";");
		strBuild.append("TO_OPERATION="			+ toOperationVisibleFlg        + ";");
		strBuild.append("TEMPLATE_KBN="			+ templateKbnStr               + ";");
		strBuild.append("UPDATE_FLG="			+ updateFlgBool     + ";");
		strBuild.append("SATUEIMENU_ID="		+ satueiMenuIDsStr             + ";");
		//strBuild.append("KENZOUKIND="			+ kenzouKind.toString()        + ";");
		//strBuild.append("KENZOUKIND(RET)="		+ kenzouKindRet.toString()     + ";");
		if (terminalBean != null)
		{
			strBuild.append("TERMINALINFO="		+ terminalBean.toString()      + ";");
		}

		return strBuild.toString();
	}
//
	/// <summary>
	/// 患者IDを設定する
	/// </summary>
	/// <param name="patientIDStr">患者ID</param>
	public void SetPatientID( String patientIDStr )
	{
		if( patientIDStr != null )
		{
			this.patientIDStr = patientIDStr;
		}
	}

	/// <summary>
	/// 患者IDを取得する
	/// </summary>
	/// <returns>患者ID</returns>
	public String GetPatientID()
	{
		return this.patientIDStr;
	}
//
	/// <summary>
	/// 患者漢字名称を設定する
	/// </summary>
	/// <param name="patientKanjiName">患者漢字名称</param>
	public void SetPatientKanjiName(String patientKanjiName)
	{
		if (patientKanjiName != null)
		{
			this.patientKanjiNameStr = patientKanjiName;
		}
	}

	/// <summary>
	/// 患者漢字名称を取得する
	/// </summary>
	/// <returns>患者漢字名称</returns>
	public String GetPatientKanjiName()
	{
		return this.patientKanjiNameStr;
	}
//
	/// <summary>
	/// RIS_IDを設定する
	/// </summary>
	/// <param name="requestIDStr">RIS_ID(昔の仕様がREQUEST_UIDだった名残)</param>
	public void SetRequestID( String requestIDStr )
	{
		if( requestIDStr != null )
		{
			this.requestIDStr = requestIDStr;
		}
	}

	/// <summary>
	/// RIS_IDを取得する
	/// </summary>
	/// <returns>RIS_ID</returns>
	public String GetRequestID()
	{
		return this.requestIDStr;
	}
//
	/// <summary>
	/// ORDER_NOを設定する
	/// </summary>
	/// <param name="orderNo">ORDER_NO</param>
	public void SetOrderNo(String orderNo)
	{
		if (orderNo != null)
		{
			this.orderNoStr = orderNo;
		}
	}

	/// <summary>
	/// ORDER_NOを取得する
	/// </summary>
	/// <returns>ORDER_NO</returns>
	public String GetOrderNo()
	{
		return this.orderNoStr;
	}
//
	/// <summary>
	/// SYSTEM_IDを設定する
	/// </summary>
	/// <param name="systemID">SYSTEM_ID</param>
	public void SetSystemtID( String systemID )
	{
		if( systemID != null )
		{
			this.systemIDStr = systemID;
		}
	}

	/// <summary>
	/// SYSTEM_IDを取得する
	/// </summary>
	/// <returns>SYSTEM_ID</returns>
	public String GetSystemtID()
	{
		return this.systemIDStr;
	}
//
	/// <summary>
	/// YUUSEN_FLGを取得する
	/// </summary>
	/// <returns>優先フラグ</returns>
	public String GetYuusenFlg()
	{
		return this.yuusenFlgStr;
	}

	/// <summary>
	/// YUUSEN_FLGを設定する
	/// </summary>
	/// <param name="yuusenFlg">優先フラグ</param>
	public void SetYuusenFlg( String yuusenFlg )
	{
		if( yuusenFlg != null )
		{
			this.yuusenFlgStr = yuusenFlg;
		}
	}
//
	/// <summary>
	/// 年齢を取得する
	/// </summary>
	/// <returns>優先フラグ</returns>
	public String GetKensaDateAge()
	{
		return this.kensaDateAgeStr;
	}

	/// <summary>
	/// 年齢を設定する
	/// </summary>
	/// <param name="kensaDateAge">検査年齢</param>
	public void SetKensaDateAge( String kensaDateAge )
	{
		if( kensaDateAge != null )
		{
			this.kensaDateAgeStr = kensaDateAge;
		}
	}
//
	/*
	/// <summary>
	/// 日付を取得する
	/// </summary>
	/// <returns>日付</returns>
	public Timestamp GetKensaDate()
	{
		return this.kensaTimestamp;
	}

	/// <summary>
	/// 日付を設定する(文字型)
	/// </summary>
	/// <param name="kensaDate">日付</param>
	public void SetKensaDate( String kensaDate )
	{
		if( kensaDate != null && kensaDate.length() >= 10)
		{
			try
			{
				this.kensaTimestamp = new Timestamp( Integer.parseInt("20" + kensaDate.substring(0,2)), Integer.parseInt(kensaDate.substring(3,2)), Integer.parseInt(kensaDate.substring(6,2)), 0, 0, 0, 0);
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
	}
	*/
	/// <summary>
	/// 日付を設定する(日付型)
	/// </summary>
	/// <param name="kensaDate">日付</param>
	public void SetKensaDate( Timestamp kensaDate )
	{
		if(!Const.TIMESTAMP_MINVALUE.equals(kensaDate))
		{
			try
			{
				this.kensaTimestamp = kensaDate;
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
	}
//
	/// <summary>
	/// StandardWindowControllerに保持している
	/// RequestParameterを使用することを明示するフラグ
	/// これが立っていると当該RequestParameterクラスの他メンバーは使用されない
	/// </summary>
	/// <param name="sessionBool"></param>
	public void SetSession( boolean sessionBool )
	{
		this.sessionBool = sessionBool;
	}

	/// <summary>
	/// StandardWindowControllerで保持している
	/// RequestParameterを使用するか否かを示すフラグを取得する
	/// </summary>
	/// <returns>
	/// 【true】StandardWindowControllerで保持されているRequestParameterを使用する
	/// 【false】当該RequestParameterインスタンスを使用する
	///</returns>
	public boolean IsSession()
	{
		return sessionBool;
	}
//
	/// <summary>
	/// オーダ種別を設定する
	/// </summary>
	/// <param name="treatDetailKindIDStr">詳細検査種別コード</param>
	public void SetKensaTypeID( String kensaTypeIDStr )
	{
		if( kensaTypeIDStr != null )
		{
			this.kensaTypeIDStr = kensaTypeIDStr;
		}
	}

	/// <summary>
	/// オーダ種別を取得する
	/// </summary>
	/// <returns>詳細検査種別</returns>
	public String GetKensaTypeID()
	{
		return this.kensaTypeIDStr;
	}
//
	/// <summary>
	/// reloadFlgをセットする
	/// </summary>
	/// <param name="flg"></param>
	public void SetReloadFlg( boolean flg )
	{
		this.reloadFlg = flg;
	}

	/// <summary>
	/// reloadFlgを取得する
	/// </summary>
	/// <returns></returns>
	public boolean GetReloadFlg()
	{
		return this.reloadFlg;
	}
//
	/*
	/// <summary>
	/// DialogResult戻り値を取得する
	/// </summary>
	/// <returns></returns>
	public DialogResult GetDlgRet()
	{
		return this.dlgRet;
	}

	/// <summary>
	/// DialogResult戻り値をセットする
	/// </summary>
	/// <param name="dlgResult"></param>
	public void SetDlgRet( DialogResult dlgResult )
	{
		this.dlgRet = dlgResult;
	}
	*/
//
	/// <summary>
	/// 汎用メッセージを取得する
	/// </summary>
	/// <returns></returns>
	public String GetRetMessage()
	{
		return this.retMessage;
	}

	/// <summary>
	/// 汎用メッセージをセットする
	/// </summary>
	/// <param name="RetMsg"></param>
	public void SetRetMessage( String RetMsg )
	{
		if( RetMsg != null )
		{
			this.retMessage = RetMsg;
		}
	}
//
	/// <summary>
	/// 照射オーダ全更新フラグを取得する
	/// </summary>
	/// <returns></returns>
	public boolean GetOrderAllUpdate()
	{
		return this.orderUpdBool;
	}

	/// <summary>
	/// 照射オーダ全更新フラグをセットする
	/// </summary>
	/// <param name="orderUpd"></param>
	public void SetOrderAllUpdate( boolean orderUpd )
	{
		this.orderUpdBool = orderUpd;
	}
//
	/// <summary>
	/// チェックアウトIDを設定する
	/// </summary>
	/// <param name="checkoutID">チェックアウトID</param>
	public void SetCheckoutID( String checkoutID )
	{
		if( checkoutID != null )
		{
			this.checkoutIdStr = checkoutID;
		}
	}

	/// <summary>
	/// チェックアウトIDを取得する
	/// </summary>
	/// <returns>checkoutID</returns>
	public String GetCheckoutID()
	{
		return this.checkoutIdStr;
	}
//
	/// <summary>
	/// プロジェクト表示フラグ
	/// </summary>
	/// <param name="val"></param>
	public void SetProjectDirectFlag(boolean val)
	{
		this.projectDetailShowBool = val;
	}

	/// <summary>
	/// プロジェクト表示フラグ
	/// </summary>
	public boolean GetProjectDirectFlag()
	{
		return this.projectDetailShowBool;
	}
//
	/*
	/// <summary>
	/// フォーム背景色
	/// </summary>
	/// <param name="val"></param>
	public void SetFormBackColor(Color val)
	{
		this.formBackColor = val;
	}

	/// <summary>
	/// フォーム背景色
	/// </summary>
	public Color GetFormBackColor()
	{
		return this.formBackColor;
	}
//
	/// <summary>
	/// フォント前景色
	/// </summary>
	/// <param name="val"></param>
	public void SetFontForeColor(Color val)
	{
		this.fontForeColor = val;
	}

	/// <summary>
	/// フォント前景色
	/// </summary>
	public Color GetFontForeColor()
	{
		return this.fontForeColor;
	}
	*/
//
	/// <summary>
	/// 検査種別名の取得
	/// </summary>
	/// <param name="val"></param>
	public void SetKensaTypeName(String kensaTypeName)
	{
		this.kensaTypeNameStr = kensaTypeName;
	}

	/// <summary>
	/// 検査種別名の設定
	/// </summary>
	public String GetKensaTypeName()
	{
		return this.kensaTypeNameStr;
	}
//
	/// <summary>
	/// 検査機器の設定
	/// </summary>
	/// <param name="val"></param>
	public void SetKensaKikiName(String kensaKikiName)
	{
		this.kensaKikiNameStr = kensaKikiName;
	}

	/// <summary>
	///  検査機器の取得
	/// </summary>
	public String GetKensaKikiName()
	{
		return this.kensaKikiNameStr;
	}
//
	/// <summary>
	/// 検査室の設定
	/// </summary>
	/// <param name="val"></param>
	public void SetKensaRoomName(String kensaRoomName)
	{
		this.kensaRoomNameStr = kensaRoomName;
	}

	/// <summary>
	/// 検査室の取得
	/// </summary>
	public String GetKensaRoomName()
	{
		return this.kensaRoomNameStr;
	}
//
	/// <summary>
	/// 治療機器IDの設定
	/// </summary>
	/// <param name="val"></param>
	public void SetTreatMachineID(String treatMachineID)
	{
		this.treatMachineIDStr = treatMachineID;
	}

	/// <summary>
	/// 治療機器IDの取得
	/// </summary>
	public String GetTreatMachineID()
	{
		return this.treatMachineIDStr;
	}
//
	/// <summary>
	/// 治療室IDの設定
	/// </summary>
	/// <param name="val"></param>
	public void SetTreatRoomID(String treatRoomID)
	{
		this.treatRoomIDStr = treatRoomID;
	}

	/// <summary>
	/// 治療室IDの取得
	/// </summary>
	public String GetTreatRoomID()
	{
		return this.treatRoomIDStr;
	}
//
	/// <summary>
	/// 検査日時の設定
	/// </summary>
	/// <param name="val"></param>
	public void SetKensaDateStr(String kensaDate)
	{
		this.kensaDateStr = kensaDate;
	}

	/// <summary>
	/// 検査日時の取得
	/// </summary>
	public String GetKensaDateStr()
	{
		return this.kensaDateStr;
	}
//
	/// <summary>
	/// 日未定予約の設定
	/// </summary>
	/// <param name="unDateFlg">判定</param>
	public void SetUnDateFlg(boolean unDateFlg)
	{
		this.unDateFlgBool = unDateFlg;
	}

	/// <summary>
	/// 日未定予約の取得
	/// </summary>
	public boolean GetUnDateFlg()
	{
		return this.unDateFlgBool;
	}
//
	/// <summary>
	/// ステータスの設定
	/// </summary>
	/// <param name="status">ステータスの</param>
	public void SetStatusID(String status)
	{
		this.statusStr = status;
	}

	/// <summary>
	/// ステータスの取得
	/// </summary>
	/// <returns>ステータスの</returns>
	public String GetStatusID()
	{
		return this.statusStr;
	}
//
	/// <summary>
	/// 画面IDの設定
	/// </summary>
	/// <param name="appID">画面ID</param>
	public void SetAppID(String appID)
	{
		this.appIDStr = appID;
	}

	/// <summary>
	/// 画面IDの取得
	/// </summary>
	/// <returns>画面ID</returns>
	public String GetAppID()
	{
		return this.appIDStr;
	}
//
	/// <summary>
	/// 実施画面へボタン表示フラグの設定
	/// </summary>
	/// <param name="toOperationVisible">実施画面へボタン表示フラグ</param>
	public void SetToOperationVisible(boolean toOperationVisible)
	{
		this.toOperationVisibleFlg = toOperationVisible;
	}

	/// <summary>
	/// 実施画面へボタン表示フラグの取得
	/// </summary>
	/// <returns>実施画面へボタン表示フラグ</returns>
	public boolean GetToOperationVisible()
	{
		return this.toOperationVisibleFlg;
	}
//
	/// <summary>
	/// ﾃﾝﾌﾟﾚｰﾄ区分を設定する
	/// </summary>
	/// <param name="templateKbn">ﾃﾝﾌﾟﾚｰﾄ区分</param>
	public void SetTemplateKbn(String templateKbn)
	{
		if (templateKbn != null)
		{
			this.templateKbnStr = templateKbn;
		}
	}

	/// <summary>
	/// ﾃﾝﾌﾟﾚｰﾄ区分を取得する
	/// </summary>
	/// <returns>ﾃﾝﾌﾟﾚｰﾄ区分</returns>
	public String GetTemplateKbn()
	{
		return this.templateKbnStr;
	}
//
	/// <summary>
	/// 更新フラグの設定
	/// </summary>
	/// <param name="updateFlg">更新フラグ</param>
	public void SetUpdateFlg(boolean updateFlg)
	{
		this.updateFlgBool = updateFlg;
	}

	/// <summary>
	/// 更新フラグの取得
	/// </summary>
	/// <returns>更新フラグ</returns>
	public boolean GetUpdateFlg()
	{
		return this.updateFlgBool;
	}
//
	/// <summary>
	/// 撮影メニューIDを設定する
	/// </summary>
	/// <param name="satueiMenuID">撮影メニューID</param>
	public void SetSatueiMenuIDs(String satueiMenuIDs)
	{
		if (satueiMenuIDs != null)
		{
			this.satueiMenuIDsStr = satueiMenuIDs;
		}
	}

	/// <summary>
	/// 撮影メニューIDを取得する
	/// </summary>
	/// <returns>撮影メニューID</returns>
	public String GetSatueiMenuIDs()
	{
		return this.satueiMenuIDsStr;
	}
//
	/*
	/// <summary>
	/// 検像種別を設定する
	/// </summary>
	/// <param name="kenzouKind">検像種別</param>
	public void SetKenzouKind(SENDKIND kind)
	{
		this.kenzouKind = kind;
	}

	/// <summary>
	/// 検像種別を取得する
	/// </summary>
	/// <returns>検像種別</returns>
	public SENDKIND GetKenzouKind()
	{
		return this.kenzouKind;
	}
//
	/// <summary>
	/// 検像種別(戻り値)を設定する
	/// </summary>
	/// <param name="kindRet">検像種別(戻り値)</param>
	public void SetKenzouKindRet(SENDKIND kindRet)
	{
		this.kenzouKindRet = kindRet;
	}

	/// <summary>
	/// 検像種別(戻り値)を取得する
	/// </summary>
	/// <returns>検像種別(戻り値)</returns>
	public SENDKIND GetKenzouKindRet()
	{
		return this.kenzouKindRet;
	}
	*/
//
	/// <summary>
	/// 端末情報を設定する
	/// </summary>
	/// <param name="terminalInfoBean">端末情報</param>
	public void SetTerminalBean(TerminalInfoBean terminalInfoBean)
	{
		this.terminalBean = terminalInfoBean;
	}

	/// <summary>
	/// 端末情報を取得する
	/// </summary>
	/// <returns>端末情報</returns>
	public TerminalInfoBean GetTerminalBean()
	{
		return this.terminalBean;
	}
}
