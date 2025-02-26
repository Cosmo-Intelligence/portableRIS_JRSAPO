package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.lib.core.Configuration;
import ris.portable.common.Const;

/// <summary>
///
/// ToHisInfoテーブル情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.05	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class ToHisInfoBean
{
	// private members
	private String requestIDStr			= "";					//Ris.ToHisInfo.RequestID
	private Timestamp requestDateDT		= new Timestamp(System.currentTimeMillis());			//Ris.ToHisInfo.RequestDate //insertでsysdateに変更
	private String risIDStr				= "";					//Ris.ToHisInfo.RIS_ID
	private String requestUserStr		= ""; 					//Ris.ToHisInfo.RequestUser
	private String requestTerminalIDStr = ""; 					//Ris.ToHisInfo.RequestTerminalID
	private String requestTypeStr		= ""; 					//Ris.ToHisInfo.RequestType
	private String messageID1Str		= ""; 					//Ris.ToHisInfo.MessageID1
	private String messageID2Str		= ""; 					//Ris.ToHisInfo.MessageID2
	private String transferStatusStr	= "00";					//Ris.ToHisInfo.TransferStatus
	private Timestamp transferDateDT	= Const.TIMESTAMP_MINVALUE;	//Ris.ToHisInfo.TransferDate
	private String transferResultStr	= "";					//Ris.ToHisInfo.TransferResult
	private String transferTextStr		= "";					//Ris.ToHisInfo.TransferText

	private String maxOpDateStr = "";
	private String maxRcDateStr = "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ToHisInfoBean()
	{
		//
	}

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <param name="requestType">要求タイプ</param>
	/// <param name="requestID">要求ID</param>
	public ToHisInfoBean( String requestType, String requestID )
	{
		if( requestType != null )
		{
			this.requestTypeStr = requestType;
		}
		if( requestID != null )
		{
			this.requestIDStr = requestID;
		}
		this.requestTerminalIDStr = Configuration.GetInstance().GetTerminalName();
	}
	//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ToHisInfoBean]");
		strBuild.append("RequestID="			+ requestIDStr				+ ";");
		strBuild.append("RequestDate="			+ requestDateDT.toString()	+ ";");
		strBuild.append("RIS_ID="				+ risIDStr					+ ";");
		strBuild.append("RequestUser="			+ requestUserStr			+ ";");
		strBuild.append("RequestTerminalID="	+ requestTerminalIDStr		+ ";");
		strBuild.append("RequestType="			+ requestTypeStr			+ ";");
		strBuild.append("MessageID1="			+ messageID1Str				+ ";");
		strBuild.append("MessageID2="			+ messageID2Str				+ ";");
		strBuild.append("TransferStatus="		+ transferStatusStr			+ ";");
		strBuild.append("TransferDate="			+ transferDateDT.toString() + ";");
		strBuild.append("TransferResult="		+ transferResultStr			+ ";");
		strBuild.append("TransferText="			+ transferTextStr			+ ";");

		return strBuild.toString();
	}
	//
	// requestIDStrのGET
	public String GetRequestID()
	{
		return this.requestIDStr;
	}
	// requestIDStrのSET
	public void SetRequestID( String requestID )
	{
		if( requestID != null )
		{
			try
			{
				Integer.parseInt(requestID);
				this.requestIDStr = requestID;
			}
			catch( Exception ex )
			{
			}
		}
	}
	//
	// requestDateDTのGET
	public Timestamp GetRequestDate()
	{
		return this.requestDateDT;
	}
	// requestDateDTのSET
	public void SetRequestDate( Timestamp requestDT )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(requestDT) )
		{
			this.requestDateDT = requestDT;
		}
	}
	//
	// risIDStrのGET
	public String GetRisID()
	{
		return this.risIDStr;
	}
	// risIDStrのSET
	public void SetRisID( String risID )
	{
		if( risID != null )
		{
			this.risIDStr = risID;
		}
	}
	//
	// requestUserStrのGET
	public String GetRequestUser()
	{
		return this.requestUserStr;
	}
	// requestUserStrのSET
	public void SetRequestUser( String requestUser )
	{
		if( requestUser != null )
		{
			this.requestUserStr = requestUser;
		}
	}
	//
	// requestTerminalIDStrのGET
	public String GetRequestTerminalID()
	{
		return this.requestTerminalIDStr;
	}
	// requestTerminalIDStrのSET
	public void SetRequestTerminalID( String requestTerminalID )
	{
		if( requestTerminalID != null )
		{
			this.requestTerminalIDStr = requestTerminalID;
		}
	}
	//
	// requestTypeStrのGET
	public String GetRequestType()
	{
		return this.requestTypeStr;
	}
	// requestTypeStrのSET
	public void SetRequestType( String requestType )
	{
		if( requestType != null )
		{
			this.requestTypeStr = requestType;
		}
	}
	//
	// messageID1StrのGET
	public String GetMessageID1()
	{
		return this.messageID1Str;
	}
	// messageID1StrのSET
	public void SetMessageID1( String message1 )
	{
		if( message1 != null )
		{
			this.messageID1Str = message1;
		}
	}
	//
	// messageID2StrのGET
	public String GetMessageID2()
	{
		return this.messageID2Str;
	}
	// messageID2StrのSET
	public void SetMessageID2( String message2 )
	{
		if( message2 != null )
		{
			this.messageID2Str = message2;
		}
	}
	//
	// transferStatusStrのGET
	public String GetTransferStatus()
	{
		return this.transferStatusStr;
	}
	// transferStatusStrのSET
	public void SetTransferStatus( String transferStatus )
	{
		if( transferStatus != null )
		{
			this.transferStatusStr = transferStatus;
		}
	}
	//
	// transferDateDTのGET
	public Timestamp GetTransferDate()
	{
		return this.transferDateDT;
	}
	// transferDateDTのSET
	public void SetTransferDate( Timestamp transferDT )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(transferDT) )
		{
			this.transferDateDT = transferDT;
		}
	}
	//
	// transferResultStrのGET
	public String GetTransferResult()
	{
		return this.transferResultStr;
	}
	// transferResultStrのSET
	public void SetTransferResult( String transferResult )
	{
		if( transferResult != null )
		{
			this.transferResultStr = transferResult;
		}
	}
	//
	// transferTextStrのGET
	public String GetTransferText()
	{
		return this.transferTextStr;
	}
	// transferTextStrのSET
	public void SetTransferText( String transferText )
	{
		if( transferText != null )
		{
			this.transferTextStr = transferText;
		}
	}
	//
	public void SetUserInfo( UserAccountBean user )
	{
		if( user != null )
		{
			this.requestUserStr = user.GetUserID();
		}
	}
	public void SetUserInfoByStaffID(UserAccountBean user)
	{
		if (user != null)
		{
			this.requestUserStr = user.GetUserID();
		}
	}
	//
	public void SetOrderInfo( OrderInfoBean order )
	{
		if( order != null )
		{
			this.risIDStr = order.GetRisID();
			this.messageID2Str = order.GetKanjaID();

			if( this.messageID1Str.length() == 0 )
			{
				this.messageID1Str = order.GetOrderNo();
			}
		}
	}
	//
	public void SetOrderInfo( ExecutionInfoBean order )
	{
		if( order != null )
		{
			this.risIDStr = order.GetRisID();
			this.messageID2Str = order.GetKanjaID();
		}
	}
	//
	public void SetTreatRequestOrder( OrderInfoBean order )
	{
		if( order != null )
		{
			this.messageID1Str = order.GetOrderNo();
		}
	}
	//
	public void SetPatientInfo( PatientInfoBean patient )
	{
		if( patient != null )
		{
			this.messageID1Str = patient.GetKanjaID();
			this.messageID2Str = patient.GetKanaSimei();
		}
	}

	// maxOpDateStrのGET
	public String GetMaxOpDate()
	{
		return this.maxOpDateStr;
	}
	// maxOpDateStrのSET
	public void SetMaxOpDate(String maxOpDate)
	{
		if (maxOpDate != null)
		{
			this.maxOpDateStr = maxOpDate;
		}
	}

	// maxRcDateStrのGET
	public String GetMaxRcDate()
	{
		return this.maxRcDateStr;
	}
	// maxRcDateStrのSET
	public void SetMaxRcDate(String maxRcDate)
	{
		if (maxRcDate != null)
		{
			this.maxRcDateStr = maxRcDate;
		}
	}
}
