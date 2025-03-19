package ris.lib.core.bean;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.commons.lang3.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.database.RisUserSubView;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.UserSearchParameter;
import ris.portable.common.Const;
import ris.portable.util.DataTable;

/// <summary>
/// CommunicationInfoBean の概要の説明です。
///
/// Copyright (C) 2011, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.04.00	: 2011.02.16	: 999999 / T.Nishikubo	: TheraRisから移植
///
/// </summary>
public class CommunicationMsgInfoBean
{
	private String messageIDStr			= "";							//COMMUNICATION_MESSAGE_INFO.MESSAGE_ID
	private String upMessageIDStr		= "";							//COMMUNICATION_MESSAGE_INFO.UP_MESSAGE_ID
	private String bunruiIDStr			= "";							//COMMUNICATION_MESSAGE_INFO.BUNRUI_ID
	private Timestamp entryTimestamp	= Const.TIMESTAMP_MINVALUE;		//COMMUNICATION_MESSAGE_INFO.ENTRY_DATE
	private String entryUsrIDStr		= "";							//COMMUNICATION_MESSAGE_INFO.ENTRY_USR_ID
	private String entryUsrNameStr		= "";							//COMMUNICATION_MESSAGE_INFO.ENTRY_USR_NAME
	private Timestamp updTimestamp		= Const.TIMESTAMP_MINVALUE;		//COMMUNICATION_MESSAGE_INFO.UPD_DATE
	private String updUsrIDStr			= "";							//COMMUNICATION_MESSAGE_INFO.UPD_USR_ID
	private String updUsrNameStr		= "";							//COMMUNICATION_MESSAGE_INFO.UPD_USR_NAME
	private String titleStr				= "";							//COMMUNICATION_MESSAGE_INFO.TITLE
	private String messageStr			= "";							//COMMUNICATION_MESSAGE_INFO.MESSAGE
	private Hashtable sendtoStatusHash	= new Hashtable();				//COMMUNICATION_MESSAGE_INFO.SENDTO_USER_ID
																		//							 SENDTO_STATUS
																		//キー:ID、値:statusのハッシュテーブル
	private Integer delFlgInt				= CommonString.DEL_FLG_FALSE;	//COMMUNICATION_MESSAGE_INFO.DEL_FLG
	private String loginUserStatus		= CommonString.COMMSG_SENDTO_STATUS_UNCHECK;//ログインユーザの確認済みフラグ
	private Integer youhenjiFlg				= Const.INT_MINVALUE;			//COMMUNICATION_MESSAGE_INFO.YOUHENJI_FLG
	private Integer juuyouFlg				= Const.INT_MINVALUE;			//COMMUNICATION_MESSAGE_INFO.JUUYOU_FLG
	private Integer kinnkyuuFlg				= Const.INT_MINVALUE;			//COMMUNICATION_MESSAGE_INFO.KINNKYUU_FLG
	private Integer sendToUserFlg			= Const.INT_MINVALUE;			//COMMUNICATION_MESSAGE_INFO.SENDTO_USER_FLG
	private Integer terminalId				= Const.INT_MINVALUE;			//COMMUNICATION_MESSAGE_INFO.TERMINALID

	public CommunicationMsgInfoBean()
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
		strBuild.append("[CommunicationMsgInfoBean]");
		strBuild.append("MESSAGE_ID="		+ messageIDStr + ";");				//メッセージID
		strBuild.append("UP_MESSAGE_ID="	+ upMessageIDStr + ";");			//親メッセージID
		strBuild.append("BUNRUI_ID="		+ bunruiIDStr + ";");				//分類ID
		strBuild.append("ENTRY_DATE="		+ entryTimestamp.toString() + ";");	//登録日
		strBuild.append("ENTRY_USR_ID="		+ entryUsrIDStr + ";");				//登録ユーザID
		strBuild.append("ENTRY_USR_NAME="	+ entryUsrNameStr + ";");			//登録ユーザ名
		strBuild.append("UPD_DATE="			+ updTimestamp.toString() + ";");	//更新日
		strBuild.append("UPD_USR_ID="		+ updUsrIDStr + ";");				//更新ユーザID
		strBuild.append("UPD_USR_NAME="		+ updUsrNameStr + ";");				//更新ユーザ名
		strBuild.append("TITLE="			+ titleStr + ";");					//タイトル
		strBuild.append("MESSAGE="			+ messageStr + ";");				//メッセージ

		//通知先ステータスハッシュテーブル
		try
		{
			Enumeration e = this.sendtoStatusHash.keys();
			String key = "";
			while (e.hasMoreElements()){
				key = (String)e.nextElement();
				strBuild.append("SENDTO_USER_ID[" + key + "]=SENDTO_STATUS[" + this.sendtoStatusHash.get(key).toString() + "];");
			}
		}
		catch (Exception e)
		{
		}

		strBuild.append("DEL_FLG="				+ delFlgInt + ";");					//削除フラグ
		strBuild.append("LOGIN_USER_STATUS="	+ loginUserStatus + ";");			//ログインユーザの確認済みフラグ
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
	/// 親メッセージID(upMessageIDStr)のSET
	/// </summary>
	/// <param name="upMessageIDStr">親メッセージID</param>
	public void SetUpMessageID( String upMessageIDStr )
	{
		if( upMessageIDStr != null )
		{
			this.upMessageIDStr = upMessageIDStr;
		}
	}
	/// <summary>
	/// 親メッセージID(upMessageIDStr)のGET
	/// </summary>
	/// <returns>親メッセージID</returns>
	public String GetUpMessageID()
	{
		return this.upMessageIDStr;
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
	/// 登録日(entryTimestamp)のSET
	/// </summary>
	/// <param name="entryTimestamp">登録日</param>
	public void SetEntryTimestamp( Timestamp entryTimestamp )
	{
		this.entryTimestamp = entryTimestamp;
	}
	/// <summary>
	/// 登録日(entryTimestamp)のGET
	/// </summary>
	/// <returns>登録日</returns>
	public Timestamp GetEntryTimestamp()
	{
		return this.entryTimestamp;
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
	/// 登録ユーザ名(entryUsrNameStr)のSET
	/// </summary>
	/// <param name="entryUsrNameStr">登録ユーザ名</param>
	public void SetEntryUsrName( String entryUsrNameStr )
	{
		if( entryUsrNameStr != null )
		{
			this.entryUsrNameStr = entryUsrNameStr;
		}
	}
	/// <summary>
	/// 登録ユーザ名(entryUsrNameStr)のGET
	/// </summary>
	/// <returns>登録ユーザ名</returns>
	public String GetEntryUsrName()
	{
		return this.entryUsrNameStr;
	}
//
	/// <summary>
	/// 更新日(updTimestamp)のSET
	/// </summary>
	/// <param name="updTimestamp">更新日</param>
	public void SetUpdTimestamp( Timestamp updTimestamp )
	{
		this.updTimestamp = updTimestamp;
	}
	/// <summary>
	/// 更新日(updTimestamp)のGET
	/// </summary>
	/// <returns>更新日</returns>
	public Timestamp GetUpdTimestamp()
	{
		return this.updTimestamp;
	}
//
	/// <summary>
	/// 更新ユーザID(updUsrIDStr)のSET
	/// </summary>
	/// <param name="updUsrIDStr">更新ユーザID</param>
	public void SetUpdUsrID( String updUsrIDStr )
	{
		if( updUsrIDStr != null )
		{
			this.updUsrIDStr = updUsrIDStr;
		}
	}
	/// <summary>
	/// 更新ユーザID(updUsrIDStr)のGET
	/// </summary>
	/// <returns>更新ユーザID</returns>
	public String GetUpdUsrID()
	{
		return this.updUsrIDStr;
	}
//
	/// <summary>
	/// 更新ユーザ名(updUsrNameStr)のSET
	/// </summary>
	/// <param name="updUsrNameStr">更新ユーザ名</param>
	public void SetUpdUsrName( String updUsrNameStr )
	{
		if( updUsrNameStr != null )
		{
			this.updUsrNameStr = updUsrNameStr;
		}
	}
	/// <summary>
	/// 更新ユーザ名(updUsrNameStr)のGET
	/// </summary>
	/// <returns>更新ユーザ名</returns>
	public String GetUpdUsrName()
	{
		return this.updUsrNameStr;
	}
//
	/// <summary>
	/// タイトル(titleStr)のSET
	/// </summary>
	/// <param name="titleStr">タイトル</param>
	public void SetTitle( String titleStr )
	{
		if( titleStr != null )
		{
			this.titleStr = titleStr;
		}
	}
	/// <summary>
	/// タイトル(titleStr)のGET
	/// </summary>
	/// <returns>タイトル</returns>
	public String GetTitle()
	{
		return this.titleStr;
	}
//
	/// <summary>
	/// メッセージ(messageStr)のSET
	/// </summary>
	/// <param name="messageStr">メッセージ</param>
	public void SetMessage( String messageStr )
	{
		if( messageStr != null )
		{
			this.messageStr = messageStr;
		}
	}
	/// <summary>
	/// メッセージ(messageStr)のGET
	/// </summary>
	/// <returns>メッセージ</returns>
	public String GetMessage()
	{
		return this.messageStr;
	}
//
	/// <summary>
	/// 通知先ユーザID文字列と通知先ステータス文字列をハッシュテーブル(sendtoStatusHash)に展開
	/// </summary>
	/// <param name="sendtoUsrIDStr">通知先ユーザID文字列</param>
	/// <param name="sendtoStatusStr">通知先ステータス文字列</param>
	public void SetSendtoStringToHashtable( String sendtoUsrIDStr, String sendtoStatusStr )
	{
		this.sendtoStatusHash = new Hashtable();

		try
		{
			//通知先ユーザID文字列を配列に展開
			String[] tempIDArray = new String[0];
			if( sendtoUsrIDStr != null )
			{
				String tmpStr = StringUtils.strip(sendtoUsrIDStr, CommonString.COMMSG_SENDTO_USER_ID_SEPARATOR);
				tempIDArray = tmpStr.split(CommonString.COMMSG_SENDTO_USER_ID_SEPARATOR);
			}

			//通知先ステータス文字列を配列に展開
			String[] tempStatusArray = new String[0];
			if( sendtoStatusStr != null )
			{
				int sendtoStatusCount = sendtoStatusStr.length();
				tempStatusArray = new String[sendtoStatusCount];
				for( int i=0; i<sendtoStatusCount; i++ )
				{
					tempStatusArray[i] = sendtoStatusStr.substring(i, i + 1);
				}
			}

			//通知先ハッシュテーブルを作成
			int sendtoUsrIDCount = tempIDArray.length;
			for( int i=0; i<sendtoUsrIDCount; i++ )
			{
				String status = CommonString.COMMSG_SENDTO_STATUS_NOTHING;
				if( tempStatusArray.length >= (i+1) )
				{
					if( CommonString.COMMSG_SENDTO_STATUS_CHECKED.equals(tempStatusArray[i]) == true )
					{
						status = CommonString.COMMSG_SENDTO_STATUS_CHECKED;
					}
					else
					{
						status = CommonString.COMMSG_SENDTO_STATUS_UNCHECK;
					}
				}
				this.sendtoStatusHash.put(tempIDArray[i], status);
			}
		}
		catch (Exception e)
		{
		}
	}

	/// <summary>
	/// 通知先ステータスハッシュテーブルから通知先ユーザID文字列と通知先ステータス文字列のGET
	/// </summary>
	/// <returns>配列([0]:通知先ユーザID文字列、[1]:通知先ステータス文字列)</returns>
	public String[] GetSendtoHashtabletoString(Connection con)
	{
		String sendtoUsrIDStr = "";
		String sendtoStatusStr = "";
		String overStr = "";

		// ユーザ情報取得
		UserSearchParameter userParam = new UserSearchParameter();
		userParam.SetUserIDValidityFlag(CommonString.USERIDVALIDITYFLAG_ON);
		DataTable userDt = Configuration.GetInstance().GetCoreController().GetUserInfo(userParam, con);
		if (userDt == null || userDt.getRowCount() == 0)
		{
			return new String[] { sendtoUsrIDStr, sendtoStatusStr, overStr };
		}

		//通知先ステータスハッシュテーブルからキーを文字列に展開
		try
		{
			ArrayList userIDArr = new ArrayList();
			ArrayList userStatusArr = new ArrayList();
			for (Integer i = 0; i < userDt.getRowCount(); i++)
			{
				String userID = userDt.getRows().get(i).get(RisUserSubView.LOGINID_COLUMN).toString();
				if (!sendtoStatusHash.containsKey(userID))
				{
					continue;
				}
				userIDArr.add(userID);
				userStatusArr.add(sendtoStatusHash.get(userID).toString());
			}

			for (Integer i = 0; i < userIDArr.size(); i++)
			{
				String tmp1 = sendtoUsrIDStr + CommonString.COMMSG_SENDTO_USER_ID_SEPARATOR + userIDArr.get(i);
				String tmp2 = sendtoStatusStr + userStatusArr.get(i);
				if (tmp1.length() >= CommonString.SENDTO_USER_ID_MAXLEN || tmp2.length() >= CommonString.SENDTO_STATUS_MAXLEN)
				{
					overStr = CommonString.OVER_VALUE_STRING;
					break;
				}
				else
				{
					sendtoUsrIDStr  = tmp1;
					sendtoStatusStr = tmp2;
				}
			}
		}
		catch (Exception e)
		{
		}
		sendtoUsrIDStr += CommonString.COMMSG_SENDTO_USER_ID_SEPARATOR;

		return new String[] {sendtoUsrIDStr, sendtoStatusStr, overStr};
	}
	/// <summary>
	/// 通知先ステータスハッシュテーブル(sendtoStatusHash)のSET
	/// </summary>
	/// <param name="sendtoStatusHash">通知先ステータスハッシュテーブル(キー:ID、値:status)</param>
	public void SetSendtoStatusHashtable(Hashtable sendtoStatusHash)
	{
		if( sendtoStatusHash != null )
		{
			this.sendtoStatusHash = sendtoStatusHash;
		}
	}
	/// <summary>
	/// 通知先ステータスハッシュテーブル(sendtoStatusHash)のGET
	/// </summary>
	/// <returns>通知先ステータスハッシュテーブル(キー:ID、値:status)</returns>
	public Hashtable GetSendtoStatusHashtable()
	{
		return this.sendtoStatusHash;
	}
//
	/// <summary>
	/// 削除フラグ(delFlgInt)のSET
	///   ※0or1以外が入ってきたら0をsetする
	/// </summary>
	/// <param name="delFlg">削除フラグ</param>
	public void SetDelFlg( String delFlg )
	{
		this.delFlgInt = CommonString.DEL_FLG_FALSE;
		if( delFlg != null )
		{
			if( delFlg.compareTo(CommonString.DEL_FLG_FALSE.toString()) == 0 ||
				delFlg.compareTo(CommonString.DEL_FLG_TRUE.toString()) == 0 )
			{
				try
				{
					this.delFlgInt = Integer.parseInt(delFlg);
				}
				catch (Exception e)
				{
				}
			}
		}
	}
	/// <summary>
	/// 削除フラグ(delFlgInt)のSET
	///    ※0or1以外が入ってきたら0をsetする
	/// </summary>
	/// <param name="delFlg">削除フラグ</param>
	public void SetDelFlg( Integer delFlg )
	{
		this.delFlgInt = CommonString.DEL_FLG_FALSE;
		if( delFlg == CommonString.DEL_FLG_FALSE || delFlg == CommonString.DEL_FLG_TRUE )
		{
			this.delFlgInt = delFlg;
		}
	}
	// delFlgIntのGET
	/// <summary>
	/// 削除フラグ(delFlgInt)のGET
	/// </summary>
	/// <returns>削除フラグ</returns>
	public Integer GetDelFlg()
	{
		return this.delFlgInt;
	}
//
	/// <summary>
	/// ログインユーザステータス(loginUserStatus)のSET
	///   ※0or10r9以外が入ってきたら0をsetする
	/// </summary>
	/// <param name="loginUserStatus">ログインユーザステータス(0:未確認/1:確認済み/9:通知先になし)</param>
	public void SetLoginUserStatus( String loginUserStatus )
	{
		this.loginUserStatus = CommonString.COMMSG_SENDTO_STATUS_UNCHECK;
		if( loginUserStatus != null )
		{
			if( CommonString.COMMSG_SENDTO_STATUS_CHECKED.equals(loginUserStatus) == true ||
					CommonString.COMMSG_SENDTO_STATUS_NOTHING.equals(loginUserStatus) == true )
			{
				this.loginUserStatus = loginUserStatus;
			}
		}
	}
	/// <summary>
	/// ログインユーザステータス(loginUserStatus)のGET
	/// </summary>
	/// <returns>ログインユーザステータス(0:未確認/1:確認済み/9:通知先になし)</returns>
	public String GetLoginUserStatus()
	{
		return this.loginUserStatus;
	}
//
	/// <summary>
	/// 該当ユーザの通知先ステータスのSET
	///   ※0or1以外が入ってきたら0をsetする
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="sendtoStatus">通知先ステータス</param>
	public void SetSendtoUsrStatusOfUserID(String userID, String sendtoStatus)
	{
		try
		{
			String status = CommonString.COMMSG_SENDTO_STATUS_UNCHECK;
			if( CommonString.COMMSG_SENDTO_STATUS_CHECKED.equals(sendtoStatus) == true )
			{
				status = sendtoStatus;
			}

			//通知先ステータスハッシュテーブルの該当ユーザの値を更新
			if( sendtoStatusHash.contains(userID) == true )
			{
				sendtoStatusHash.put(userID, status);
			}
		}
		catch (Exception e)
		{
		}
	}
	/// <summary>
	/// 該当ユーザの通知先ステータスのGET
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <returns>通知先ステータス(0:未確認/1:確認済み/9:通知先になし)</returns>
	public String GetSendtoUsrStatusOfUserID(String userID)
	{
		String status = CommonString.COMMSG_SENDTO_STATUS_NOTHING;

		//通知先ステータスハッシュテーブルの該当ユーザの値を取得
		if( sendtoStatusHash.contains(userID) == true )
		{
			status = sendtoStatusHash.get(userID).toString();
		}

		return status;
	}
	/// <summary>
	/// 通知先ステータスの指定値一括SET
	///   ※0or1以外が入ってきたら0をsetする
	/// </summary>
	/// <param name="sendtoStatus">通知先ステータス</param>
	public void SetSendtoUsrStatusAll(String sendtoStatus)
	{
		try
		{
			String status = CommonString.COMMSG_SENDTO_STATUS_UNCHECK;
			if( CommonString.COMMSG_SENDTO_STATUS_CHECKED.equals(sendtoStatus) == true )
			{
				status = sendtoStatus;
			}

			//指定通知先ステータスを設定
			Hashtable tempHash = new Hashtable();
			Enumeration e = this.sendtoStatusHash.keys();
			String key = "";
			while (e.hasMoreElements()){
				key = (String)e.nextElement();
				tempHash.put( key, status );
			}
			this.sendtoStatusHash = tempHash;
		}
		catch (Exception e)
		{
		}
	}
	/// <summary>
	/// 通知先ステータスの指定ステータス有無チェック
	/// </summary>
	/// <returns>true:指定ステータスあり false:指定ステータスなし</returns>
	public boolean ExistsSendtoUsrStatus( String status)
	{
		boolean ret = false;

		try
		{
			//通知先ステータスハッシュテーブルに指定ステータスがあるかチェック
			Enumeration e = this.sendtoStatusHash.keys();
			String key = "";
			while (e.hasMoreElements()){
				key = (String)e.nextElement();
				if( this.sendtoStatusHash.get(key).toString().equals(status) == true )
				{
					//指定ステータスがある場合
					ret = true;
					break;
				}
			}
		}
		catch (Exception e)
		{
		}

		return ret;
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
