package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.portable.common.Const;

/// <summary>
/// CommunicationMsgSearchParameter の概要の説明です。
///
/// Copyright (C) 2011, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.04.00	: 2011.02.16	: 999999 / T.Nishikubo	: TheraRisから移植
///
/// </summary>
///
public class CommunicationMsgSearchParameter
{
	/// <summary>
	/// 一覧モード列挙体
	/// </summary>
	public enum EListMode
	{
		eRecvList,		//受信リスト
		eEntryList,		//登録リスト
		eSerialList		//関連メッセージリスト
	}

	private String messageIDStr			= "";					//COMMUNICATION_MESSAGE_INFO.MESSAGE_ID
	private String bunruiIDStr			= "";					//COMMUNICATION_MESSAGE_INFO.BUNRUI_ID
	private Timestamp startEntryDate	= Const.TIMESTAMP_MINVALUE;	//COMMUNICATION_MESSAGE_INFO.ENTRY_DATE
	private Timestamp endEntryDate		= Const.TIMESTAMP_MINVALUE;
	private String entryUsrIDStr		= "";					//COMMUNICATION_MESSAGE_INFO.ENTRY_USR_ID
	private String sendtoUsrIDStr		= "";					//COMMUNICATION_MESSAGE_INFO.SENDTO_USER_ID (リスト内)
	private boolean uncheckOnlyFlg		= false;				//未確認のみフラグ
	private Integer	youhenjiFlg			= Const.INT_MINVALUE;	// 要返フラグ
	private Integer	juuyouFlg			= Const.INT_MINVALUE;	// 重要フラグ
	private Integer	kinnkyuuFlg			= Const.INT_MINVALUE;	// 緊急フラグ
	private Integer	sendToUserFlg		= Const.INT_MINVALUE;	// 全ユーザフラグ
	private Integer	terminalId			= Const.INT_MINVALUE;	// 端末ID
	// 2011.02.16 Add T.Nishikubo End

	public CommunicationMsgSearchParameter()
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
		strBuild.append("[CommunicationMsgSearchParameter]");
		strBuild.append("MESSAGE_ID=" + messageIDStr + ";");						//メッセージID
		strBuild.append("BUNRUI_ID=" + bunruiIDStr + ";");							//分類ID
		strBuild.append("START_ENTRY_DATE=" + startEntryDate.toString() + ";");		//登録日期間開始
		strBuild.append("END_ENTRY_DATE=" + endEntryDate.toString() + ";");			//登録日期間終了
		strBuild.append("ENTRY_USR_ID=" + entryUsrIDStr + ";");						//登録ユーザID
		strBuild.append("UNCHECK_ONLY_FLG=" + uncheckOnlyFlg + ";");				//未確認のみフラグ
		strBuild.append("YOUHENJI_FLG="			+ youhenjiFlg.toString() + ";");	//要返フラグ
		strBuild.append("JUUYOU_FLG="			+ juuyouFlg.toString() + ";");		//重要フラグ
		strBuild.append("KINNKYUU_FLG="			+ kinnkyuuFlg.toString() + ";");	//緊急フラグ
		strBuild.append("SENDTO_USER_FLG="		+ sendToUserFlg.toString() + ";");	//全ユーザフラグ
		strBuild.append("TERMINALID="			+ terminalId.toString() + ";");		//端末ID

		return strBuild.toString();
	}
//
//
//
	/// <summary>
	/// メッセージID(messageIDStr)のSET
	/// </summary>
	/// <param name="messageIDStr">メッセージID</param>
	public void SetMessageID( String messageIDStr )
	{
		if( messageIDStr != null )
		{
			this.messageIDStr = messageIDStr;
		}
	}
	/// <summary>
	/// メッセージID(messageIDStr)のGET
	/// </summary>
	/// <returns>メッセージID</returns>
	public String GetMessageID()
	{
		return this.messageIDStr;
	}
//
	/// <summary>
	/// 分類ID(bunruiIDStr)のSET
	/// </summary>
	/// <param name="bunruiIDStr">分類ID</param>
	public void SetBunruiID( String bunruiIDStr )
	{
		if( bunruiIDStr != null )
		{
			this.bunruiIDStr = bunruiIDStr;
		}
	}
	/// <summary>
	/// 分類ID(bunruiIDStr)のGET
	/// </summary>
	/// <returns>分類ID</returns>
	public String GetBunruiID()
	{
		return this.bunruiIDStr;
	}
//
	/// <summary>
	/// 登録日期間開始(startEntryDate)のSET
	/// </summary>
	/// <param name="startEntryDate">登録日期間開始</param>
	public void SetStartEntryDate( Timestamp startEntryDate )
	{
		this.startEntryDate = startEntryDate;
	}
	/// <summary>
	/// 登録日期間開始(startEntryDate)のGET
	/// </summary>
	/// <returns>登録日期間開始</returns>
	public Timestamp GetStartEntryDate()
	{
		return this.startEntryDate;
	}
//
	/// <summary>
	/// 登録日期間終了(endEntryDate)のSET
	/// </summary>
	/// <param name="endEntryDate">登録日期間終了</param>
	public void SetEndEntryDate( Timestamp endEntryDate )
	{
		this.endEntryDate = endEntryDate;
	}
	/// <summary>
	/// 登録日期間終了(endEntryDate)のGET
	/// </summary>
	/// <returns>登録日期間終了</returns>
	public Timestamp GetEndEntryDate()
	{
		return this.endEntryDate;
	}
//
	/// <summary>
	/// 登録ユーザID(entryUsrIDStr)のSET
	/// </summary>
	/// <param name="entryUsrIDStr">登録ユーザID</param>
	public void SetEntryUsrID( String entryUsrIDStr )
	{
		if( entryUsrIDStr != null )
		{
			this.entryUsrIDStr = entryUsrIDStr;
		}
	}
	/// <summary>
	/// 登録ユーザID(entryUsrIDStr)のGET
	/// </summary>
	/// <returns>登録ユーザID</returns>
	public String GetEntryUsrID()
	{
		return this.entryUsrIDStr;
	}
//
	/// <summary>
	/// 通知先ユーザID(sendtoUsrIDStr)のSET
	/// </summary>
	/// <param name="sendtoUsrIDStr">通知先ユーザID</param>
	public void SetSendtoUsrID( String sendtoUsrIDStr )
	{
		if( sendtoUsrIDStr != null )
		{
			this.sendtoUsrIDStr = sendtoUsrIDStr;
		}
	}
	/// <summary>
	/// 通知先ユーザID(sendtoUsrIDStr)のGET
	/// </summary>
	/// <returns>通知先ユーザID</returns>
	public String GetSendtoUsrID()
	{
		return this.sendtoUsrIDStr;
	}
//
	/// <summary>
	/// 未確認のみフラグ(uncheckOnlyFlg)のSET
	/// </summary>
	/// <param name="uncheckOnlyFlg">未確認のみフラグ</param>
	public void SetUncheckedOnlyFlg( boolean uncheckOnlyFlg )
	{
		this.uncheckOnlyFlg = uncheckOnlyFlg;
	}
	/// <summary>
	/// 未確認のみフラグ(uncheckOnlyFlg)のGET
	/// </summary>
	/// <returns>未確認のみフラグ</returns>
	public boolean GetUncheckedOnlyFlg()
	{
		return this.uncheckOnlyFlg;
	}

	/// <summary>
	/// 要返フラグ(youhenjiFlg)のSET
	/// </summary>
	/// <param name="youhenjiFlg">要返フラグ(1:ON else:OFF)</param>
	public void SetYouhenjiFlg(Integer youhenjiFlg)
	{
		this.youhenjiFlg = youhenjiFlg;
	}
	/// <summary>
	/// 要返フラグ(youhenjiFlg)のGET
	/// </summary>
	/// <returns>要返フラグ(1:ON else:OFF)</returns>
	public Integer GetYouhenjiFlg()
	{
		return this.youhenjiFlg;
	}

	/// <summary>
	/// 重要フラグ(juuyouFlg)のSET
	/// </summary>
	/// <param name="juuyouFlg">重要フラグ(1:ON else:OFF)</param>
	public void SetJuuyouFlg(Integer juuyouFlg)
	{
		this.juuyouFlg = juuyouFlg;
	}
	/// <summary>
	/// 重要フラグ(juuyouFlg)のGET
	/// </summary>
	/// <returns>重要フラグ(1:ON else:OFF)</returns>
	public Integer GetJuuyouFlg()
	{
		return this.juuyouFlg;
	}

	/// <summary>
	/// 緊急フラグ(kinnkyuuFlg)のSET
	/// </summary>
	/// <param name="kinnkyuuFlg">緊急フラグ(1:ON else:OFF)</param>
	public void SetKinnkyuuFlg(Integer kinnkyuuFlg)
	{
		this.kinnkyuuFlg = kinnkyuuFlg;
	}
	/// <summary>
	/// 緊急フラグ(kinnkyuuFlg)のGET
	/// </summary>
	/// <returns>緊急フラグ(1:ON else:OFF)</returns>
	public Integer GetKinnkyuuFlg()
	{
		return this.kinnkyuuFlg;
	}

	/// <summary>
	/// 全ユーザフラグ(sendToUserFlg)のSET
	/// </summary>
	/// <param name="sendToUserFlg">全ユーザフラグ(1:全ユーザ else:その他)</param>
	public void SetsendToUserFlg(Integer sendToUserFlg)
	{
		this.sendToUserFlg = sendToUserFlg;
	}
	/// <summary>
	/// 全ユーザフラグ(sendToUserFlg)のGET
	/// </summary>
	/// <returns>全ユーザフラグ(1:全ユーザ else:その他)</returns>
	public Integer GetsendToUserFlg()
	{
		return this.sendToUserFlg;
	}

	/// <summary>
	/// 端末ID(terminalId)のSET
	/// </summary>
	/// <param name="terminalId">端末ID</param>
	public void SetTerminalId(Integer terminalId)
	{
		this.terminalId = terminalId;
	}
	/// <summary>
	/// 端末ID(terminalId)のGET
	/// </summary>
	/// <returns>端末ID</returns>
	public Integer GetTerminalId()
	{
		return this.terminalId;
	}

}
