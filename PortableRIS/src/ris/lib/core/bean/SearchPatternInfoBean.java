package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.portable.common.Const;

/// <summary>
/// 検索パターン設定クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.11	: 112478 / A.Kobayashi	: original
/// V2.04.001	: 2010.11.16	: 999999 / T.Nishikubo	: KANRO-R-3
/// V2.04.001　 extends 2011.01.24    extends 999999 / DD.Chinh     extends KANRO-R-19
/// </summary>
public class SearchPatternInfoBean
{
	//Ris.SearchPatternInfo用
	private Integer      patternIDInt 		= -1; 					//Ris.SearchPatternInfo.PATTERNID
	private String   patternNameStr 	= ""; 					//Ris.SearchPatternInfo.PATTERNNAME
	private String   patternTypeStr 	= ""; 					//Ris.SearchPatternInfo.PATTERNTYPE
	private String   windowAppIDStr		= ""; 					//Ris.SearchPatternInfo.WINDOWAPPID
	private Integer      examDateNoInt 		= 0;  					//Ris.SearchPatternInfo.EXAMDATENO
	private String   examDateStr 		= ""; 					//Ris.SearchPatternInfo.EXAMDATE
	private String   patIDStr 			= ""; 					//Ris.SearchPatternInfo.PATID
	private String   statusStr 			= ""; 					//Ris.SearchPatternInfo.STATUS
	private String   kensaTypesStr 		= ""; 					//Ris.SearchPatternInfo.KENSATYPES
	private String   examRoomsStr 		= ""; 					//Ris.SearchPatternInfo.EXAMROOMS
	private Integer      inoutPatientInt	= 0;  					//Ris.SearchPatternInfo.INOUTPATIENT
	private String   requestSectionStr 	= ""; 					//Ris.SearchPatternInfo.REQUESTSECTION
	private String   requestDoctorStr 	= ""; 					//Ris.SearchPatternInfo.REQUESTDOCTOR
	private String   indicateDoctorStr 	= ""; 					//Ris.SearchPatternInfo.INDICATEDOCTOR
	private String   enforcetcStr 		= ""; 					//Ris.SearchPatternInfo.ENFORCETC
	private String   portableFlagStr 	= ""; 					//Ris.SearchPatternInfo.PORTABLEFLAG
	private String   orderTypeStr 		= ""; 					//Ris.SearchPatternInfo.ORDERTYPE
	private String   orderDateModeStr 	= ""; 					//Ris.SearchPatternInfo.ORDERDATEMODE
	private String   explanationStr 	= ""; 					//Ris.SearchPatternInfo.EXPLANATION
	private String   freeWhereStr 		= ""; 					//Ris.SearchPatternInfo.FREEWHERE
	private Integer      showorderInt 		= 0;					//Ris.SearchPatternInfo.SHOWORDER
	private Timestamp updateDatetime 	= Const.TIMESTAMP_MINVALUE;	//Ris.SearchPatternInfo.UPDATEDATE
	private Integer      userIDInt			= 0;					//Ris.SearchPatternInfo.USERID
	private Integer      terminalIDInt 		= 0; 					//Ris.SearchPatternInfo.TERMINALID
	private String   kensakikiStr 		= ""; 					//Ris.SearchPatternInfo.KENSAKIKI
	private String   unDecidedFlagStr 	= ""; 					//Ris.SearchPatternInfo.UNDECIDEDFLAG
	private String   examRoomFlagStr 	= ""; 					//Ris.SearchPatternInfo.EXAMROOMFLAG
	private String   kensakikiFlagStr 	= ""; 					//Ris.SearchPatternInfo.KENSAKIKIFLAG
	private String   searchModeStr 		= ""; 					//Ris.SearchPatternInfo.SEARCHMODE
	private String   buiDisplayModeStr 	= ""; 					//Ris.SearchPatternInfo.BUIDISPLAYMODE
	private String   riOrderFlgStr		= ""; 					//Ris.SearchPatternInfo.RI_ORDER_FLG
	private String   kenzouStatusStr	= ""; 					//Ris.SearchPatternInfo.KENZOU_STATUS
	private String   kenzouGisiFlagStr	= ""; 					//Ris.SearchPatternInfo.KENZOU_GISIFLAG
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	private String   preparingStr		= "";
	// 2010.11.16 Add T.Nishikubo End

	//Ris.HistorySearchPatternInfo用
	private String   examDataDefStr		= "";					//Ris.HistorySearchPatternInfo.EXAMDATEDEF
	private String   kensatypeDefStr	= "";					//Ris.HistorySearchPatternInfo.KENSATYPEDEF

	private String   precheckStatusStr	= "";					//Ris.SearchPatternInfo.PRECHECK_STATUS		// 2011.01.24 Add DD.Chinh KANRO-R-19

	// 2011.06.24 Add Yk.Suzuki@CIJ Start NML-CAT2-024、NML-CAT2-025
	private String autoReceiptStr		= "";					// Ris.SearchPatternInfo.AUTORECEIPT
	private String autoExpandStr		= "";					// Ris.SearchPatternInfo.AUTOEXPAND
	// 2011.06.24 Add Yk.Suzuki@CIJ End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public SearchPatternInfoBean()
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
		strBuild.append("[SearchPatternInfoBean]");
		strBuild.append("PATTERNID="		+ patternIDInt 			+ ";");
		strBuild.append("PATTERNNAME=" 		+ patternNameStr 		+ ";");
		strBuild.append("PATTERNTYPE="		+ patternTypeStr 		+ ";");
		strBuild.append("WINDOWAPPID=" 		+ windowAppIDStr 		+ ";");
		strBuild.append("EXAMDATENO=" 		+ examDateNoInt 		+ ";");
		strBuild.append("EXAMDATE=" 		+ examDateStr 			+ ";");
		strBuild.append("PATID=" 			+ patIDStr 				+ ";");
		strBuild.append("STATUS=" 			+ statusStr 			+ ";");
		strBuild.append("KENSATYPES=" 		+ kensaTypesStr 		+ ";");
		strBuild.append("EXAMROOMS=" 		+ examRoomsStr 			+ ";");
		strBuild.append("INOUTPATIENT=" 	+ inoutPatientInt 		+ ";");
		strBuild.append("REQUESTSECTION=" 	+ requestSectionStr 	+ ";");
		strBuild.append("REQUESTDOCTOR=" 	+ requestDoctorStr 		+ ";");
		strBuild.append("INDICATEDOCTOR=" 	+ indicateDoctorStr 	+ ";");
		strBuild.append("ENFORCETC=" 		+ enforcetcStr 			+ ";");
		strBuild.append("PORTABLEFLAG=" 	+ portableFlagStr 		+ ";");
		strBuild.append("ORDERTYPE=" 		+ orderTypeStr 			+ ";");
		strBuild.append("ORDERDATEMODE=" 	+ orderDateModeStr 		+ ";");
		strBuild.append("EXPLANATION=" 		+ explanationStr 		+ ";");
		strBuild.append("FREEWHERE=" 		+ freeWhereStr 			+ ";");
		strBuild.append("SHOWORDER=" 		+ showorderInt			+ ";");
		strBuild.append("UPDATEDATE=" 		+ updateDatetime 		+ ";");
		strBuild.append("USERID=" 			+ userIDInt 			+ ";");
		strBuild.append("TERMINALID=" 		+ terminalIDInt 		+ ";");
		strBuild.append("KENSAKIKI=" 		+ kensakikiStr 			+ ";");
		strBuild.append("UNDECIDEDFLAG=" 	+ unDecidedFlagStr 		+ ";");
		strBuild.append("EXAMROOMFLAG=" 	+ examRoomFlagStr 		+ ";");
		strBuild.append("KENSAKIKIFLAG=" 	+ kensakikiFlagStr 		+ ";");
		strBuild.append("SEARCHMODE=" 		+ searchModeStr 		+ ";");
		strBuild.append("BUIDISPLAYMODE=" 	+ buiDisplayModeStr 	+ ";");
		strBuild.append("RI_ORDER_FLG=" 	+ riOrderFlgStr			+ ";");
		strBuild.append("KENZOU_STATUS="	+ kenzouStatusStr		+ ";");
		strBuild.append("KENZOU_GISIFLAG="	+ kenzouGisiFlagStr		+ ";");
		//
		strBuild.append("EXAMDATEDEF="		+ examDataDefStr		+ ";");
		strBuild.append("KENSATYPEDEF="		+ kensatypeDefStr		+ ";");

		strBuild.append("PRECHECKSTATUS=" + precheckStatusStr	+ ";");

		strBuild.append("AUTORECEIPT="		+ autoReceiptStr		+ ";");
		strBuild.append("AUTOEXPAND="		+ autoExpandStr			+ ";");

		return strBuild.toString();
	}
//
	// patternIDIntのSET
	public void SetPatternID(Integer patternID)
	{
		this.patternIDInt = patternID;
	}
	// patternIDIntのGET
	public Integer GetPatternID()
	{
		return this.patternIDInt;
	}
//
	// patternNameStrのSET
	public void SetPatternName(String patternName)
	{
		if (patternName != null)
		{
			this.patternNameStr = patternName;
		}
	}
	// patternNameStrのGET
	public String GetPatternName()
	{
		return this.patternNameStr;
	}
//
	// patternTypeStrのSET
	public void SetPatternType(String patternType)
	{
		if (patternType != null)
		{
			this.patternTypeStr = patternType;
		}
	}
	// patternTypeStrのGET
	public String GetPatternType()
	{
		return this.patternTypeStr;
	}
//
	// windowAppIDStrのSET
	public void SetWindowAppID(String windowAppID)
	{
		if (windowAppID != null)
		{
			this.windowAppIDStr = windowAppID;
		}
	}
	// windowAppIDStrのGET
	public String GetWindowAppID()
	{
		return this.windowAppIDStr;
	}
//
	// examDateNoIntのSET
	public void SetExamDateNo(Integer examDateNo)
	{
		this.examDateNoInt = examDateNo;
	}
	// examDateNoIntのGET
	public Integer GetExamDateNo()
	{
		return this.examDateNoInt;
	}
//
	// examDateStrのSET
	public void SetExamDate(String examDate)
	{
		if (examDate != null)
		{
			this.examDateStr = examDate;
		}
	}
	// examDateStrのGET
	public String GetExamDate()
	{
		return this.examDateStr;
	}
//
	// patIDStrのSET
	public void SetPatID(String patID)
	{
		if (patID != null)
		{
			this.patIDStr = patID;
		}
	}
	// patIDStrのGET
	public String GetPatID()
	{
		return this.patIDStr;
	}
//
	// statusStrのSET
	public void SetStatus(String status)
	{
		if (status != null)
		{
			this.statusStr = status;
		}
	}
	// statusStrのGET
	public String GetStatus()
	{
		return this.statusStr;
	}
//
	// kensaTypesStrのSET
	public void SetKensaTypes(String kensaTypes)
	{
		if (kensaTypes != null)
		{
			this.kensaTypesStr = kensaTypes;
		}
	}
	// kensaTypesStrのGET
	public String GetKensaTypes()
	{
		return this.kensaTypesStr;
	}
//
	// examRoomsStrのSET
	public void SetExamRooms(String examRooms)
	{
		if (examRooms != null)
		{
			this.examRoomsStr = examRooms;
		}
	}
	// examRoomsStrのGET
	public String GetExamRooms()
	{
		return this.examRoomsStr;
	}
//
	// inoutPatientIntのSET
	public void SetInoutPatient(Integer inoutPatient)
	{
		this.inoutPatientInt = inoutPatient;
	}
	// inoutPatientIntのGET
	public Integer GetInoutPatient()
	{
		return this.inoutPatientInt;
	}
//
	// requestSectionStrのSET
	public void SetRequestSection(String requestSection)
	{
		if (requestSection != null)
		{
			this.requestSectionStr = requestSection;
		}
	}
	// requestSectionStrのGET
	public String GetRequestSection()
	{
		return this.requestSectionStr;
	}
//
	// requestDoctorStrのSET
	public void SetRequestDoctor(String requestDoctor)
	{
		if (requestDoctor != null)
		{
			this.requestDoctorStr = requestDoctor;
		}
	}
	// requestDoctorStrのGET
	public String GetRequestDoctor()
	{
		return this.requestDoctorStr;
	}
//
	// indicateDoctorStrのSET
	public void SetIndicateDoctor(String indicateDoctor)
	{
		if (indicateDoctor != null)
		{
			this.indicateDoctorStr = indicateDoctor;
		}
	}
	// indicateDoctorStrのGET
	public String GetIndicateDoctor()
	{
		return this.indicateDoctorStr;
	}
//
	// enforcetcStrのSET
	public void SetEnforcetc(String enforcetc)
	{
		if (enforcetc != null)
		{
			this.enforcetcStr = enforcetc;
		}
	}
	// enforcetcStrのGET
	public String GetEnforcetc()
	{
		return this.enforcetcStr;
	}
//
	// portableFlagStrのSET
	public void SetPortableFlag(String portableFlag)
	{
		if (portableFlag != null)
		{
			this.portableFlagStr = portableFlag;
		}
	}
	// portableFlagStrのGET
	public String GetPortableFlag()
	{
		return this.portableFlagStr;
	}
//
	// orderTypeStrのSET
	public void SetOrderType(String orderType)
	{
		if (orderType != null)
		{
			this.orderTypeStr = orderType;
		}
	}
	// orderTypeStrのGET
	public String GetOrderType()
	{
		return this.orderTypeStr;
	}
//
	// orderDateModeStrのSET
	public void SetOrderDateMode(String orderDateMode)
	{
		if (orderDateMode != null)
		{
			this.orderDateModeStr = orderDateMode;
		}
	}
	// orderDateModeStrのGET
	public String GetOrderDateMode()
	{
		return this.orderDateModeStr;
	}
//
	// explanationStrのSET
	public void SetExplanation(String explanation)
	{
		if (explanation != null)
		{
			this.explanationStr = explanation;
		}
	}
	// explanationStrのGET
	public String GetExplanation()
	{
		return this.explanationStr;
	}
//
	// freeWhereStrのSET
	public void SetFreeWhere(String freeWhere)
	{
		if (freeWhere != null)
		{
			this.freeWhereStr = freeWhere;
		}
	}
	// freeWhereStrのGET
	public String GetFreeWhere()
	{
		return this.freeWhereStr;
	}
//
	// showorderIntのSET
	public void SetShoworder(Integer showorder)
	{
		this.showorderInt = showorder;
	}
	// showorderIntのGET
	public Integer GetShoworder()
	{
		return this.showorderInt;
	}
//
	// updateDatetimeのSET
	public void SetUpdateDate(Timestamp updateDate)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(updateDate) )
		{
			this.updateDatetime = updateDate;
		}
	}
	// updateDatetimeのGET
	public Timestamp GetUpdateDate()
	{
		return this.updateDatetime;
	}
//
	// userIDIntのSET
	public void SetUserID(Integer userID)
	{
		this.userIDInt = userID;
	}
	// userIDIntのGET
	public Integer GetUserID()
	{
		return this.userIDInt;
	}
//
	// terminalIDIntのSET
	public void SetTerminalID(Integer terminalID)
	{
		this.terminalIDInt = terminalID;
	}
	// terminalIDIntのGET
	public Integer GetTerminalID()
	{
		return this.terminalIDInt;
	}
//
	// kensakikiStrのSET
	public void SetKensakiki(String kensakiki)
	{
		if (kensakiki != null)
		{
			this.kensakikiStr = kensakiki;
		}
	}
	// kensakikiStrのGET
	public String GetKensakiki()
	{
		return this.kensakikiStr;
	}
//
	// unDecidedFlagStrのSET
	public void SetUnDecidedFlag(String unDecidedFlag)
	{
		if (unDecidedFlag != null)
		{
			this.unDecidedFlagStr = unDecidedFlag;
		}
	}
	// unDecidedFlagStrのGET
	public String GetUnDecidedFlag()
	{
		return this.unDecidedFlagStr;
	}
//
	// examRoomFlagStrのSET
	public void SetExamRoomFlag(String examRoomFlag)
	{
		if (examRoomFlag != null)
		{
			this.examRoomFlagStr = examRoomFlag;
		}
	}
	// examRoomFlagStrのGET
	public String GetExamRoomFlag()
	{
		return this.examRoomFlagStr;
	}
//
	// kensakikiFlagStrのSET
	public void SetKensakikiFlag(String kensakikiFlag)
	{
		if (kensakikiFlag != null)
		{
			this.kensakikiFlagStr = kensakikiFlag;
		}
	}
	// kensakikiFlagStrのGET
	public String GetKensakikiFlag()
	{
		return this.kensakikiFlagStr;
	}
//
	// searchModeStrのSET
	public void SetSearchMode(String searchMode)
	{
		if (searchMode != null)
		{
			this.searchModeStr = searchMode;
		}
	}
	// searchModeStrのGET
	public String GetSearchMode()
	{
		return this.searchModeStr;
	}
//
	// buiDisplayModeStrのSET
	public void SetBuiDisplayMode(String buiDisplayMode)
	{
		if (buiDisplayMode != null)
		{
			this.buiDisplayModeStr = buiDisplayMode;
		}
	}
	// buiDisplayModeStrのGET
	public String GetBuiDisplayMode()
	{
		return this.buiDisplayModeStr;
	}
//
	// riOrderFlgStrのSET
	public void SetRiOrderFlg(String riOrderFlg)
	{
		if (riOrderFlg != null)
		{
			this.riOrderFlgStr = riOrderFlg;
		}
	}
	// riOrderFlgStrのGET
	public String GetRiOrderFlg()
	{
		return this.riOrderFlgStr;
	}
//
	// kenzouStatusStrのSET
	public void SetKenzouStatus(String kenzouStatus)
	{
		if (kenzouStatus != null)
		{
			this.kenzouStatusStr = kenzouStatus;
		}
	}
	// kenzouStatusStrのGET
	public String GetKenzouStatus()
	{
		return this.kenzouStatusStr;
	}
//
	// kenzouGisiFlagStrのSET
	public void SetKenzouGisiFlag(String kenzouGisiFlag)
	{
		if (kenzouGisiFlag != null)
		{
			this.kenzouGisiFlagStr = kenzouGisiFlag;
		}
	}
	// kenzouGisiFlagStrのGET
	public String GetKenzouGisiFlag()
	{
		return this.kenzouGisiFlagStr;
	}

	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	//
	// preparingBoolのSET
	public void SetPreparing(String preparingStr)
	{
		if (preparingStr != null)
		{
			this.preparingStr = preparingStr;
		}
	}
	// preparingBoolのGET
	public String GetPreparing()
	{
		return this.preparingStr;
	}
	// 2010.11.16 Add T.Nishikubo End

	// 2011.01.24 Add DD.Chinh Start KANRO-R-19
	//
	// precheckStatusStrのSET
	public void SetPrecheckStatus(String precheckStatus)
	{
		if (precheckStatus != null)
		{
			this.precheckStatusStr = precheckStatus;
		}
	}
	// precheckStatusStrのGET
	public String GetPrecheckStatus()
	{
		return this.precheckStatusStr;
	}
	// 2011.01.24 Add DD.Chinh End

	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////

	// examDataDefStrのSET
	public void SetExamDataDef(String examDataDef)
	{
		if (examDataDef != null)
		{
			this.examDataDefStr = examDataDef;
		}
	}
	// examDataDefStrのGET
	public String GetExamDataDef()
	{
		return this.examDataDefStr;
	}
//
	// kensatypeDefStrのSET
	public void SetKensatypeDef(String kensatypeDef)
	{
		if (kensatypeDef != null)
		{
			this.kensatypeDefStr = kensatypeDef;
		}
	}
	// kensatypeDefStrのGET
	public String GetKensatypeDef()
	{
		return this.kensatypeDefStr;
	}
	// 2011.06.24 Add Yk.Suzuki@CIJ Start NML-CAT2-024、NML-CAT2-025
	// autoReceiptStrのSET
	public void SetAutoReceipt(String autoReceipt)
	{
		if (autoReceipt != null)
		{
			this.autoReceiptStr = autoReceipt;
		}
	}
	// autoReceiptStrのGET
	public String GetAutoReceipt()
	{
		return this.autoReceiptStr;
	}

	// autoExpandStrのSET
	public void SetAutoExpand(String autoExpand)
	{
		if (autoExpand != null)
		{
			this.autoExpandStr = autoExpand;
		}
	}
	// autoExpandStrのGET
	public String GetAutoExpand()
	{
		return this.autoExpandStr;
	}
	// 2011.06.24 Add Yk.Suzuki@CIJ End
}
