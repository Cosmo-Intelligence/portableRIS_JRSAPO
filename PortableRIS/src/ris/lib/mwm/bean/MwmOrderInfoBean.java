package ris.lib.mwm.bean;

import java.util.ArrayList;

import ris.portable.common.Const;

/// <summary>
///
/// オーダ情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class MwmOrderInfoBean
{
	private String orderNoStr					= "";				//Ris.OrderMainTable.OrderNo
	private String accessionNoStr				= "";				//Ris.OrderMainTable.AccessionNo
	private String risIDStr						= "";				//Ris.OrderMainTable.RIS_ID, Ris.OrderBuiTable.RIS_ID,
	private String kanjaIDStr					= "";				//Ris.OrderMainTable.Kanja_ID, Ris.ExMainTable.Kanja_ID
	private String statusStr					= "";				//Ris.OrderMainTable.STATUS
	private String kensatypeIDStr				= "";				//Ris.OrderMainTable.KENSATYPE_ID
	private String kensaDateStr					= "";				//Ris.OrderMainTable.KENSA_DATE
	private String kensasituIDStr				= "";				//Ris.OrderMainTable.KENSASITU_ID
	private String kensakikiIDStr				= "";				//Ris.OrderMainTable.KENSAKIKI_ID
	private String denpyoNyugaiKbnStr			= "";				//Ris.OrderMainTable.DENPYO_NYUGAIKBN
	private String kensaDateAgeStr				= "";				//Ris.OrderMainTable.KENSA_DATE_AGE]
	private String dokueiFlgStr					= "";				//Ris.OrderMainTable.DOKUEI_FLG
	private int    terminalIDInt				= Const.INT_MINVALUE;
	private String terminalNameStr				= "";
	private String yuusenFlgStr					= "";
	private String planIDStr					= "";
	private String renrakuMemoStr				= "";
	private String orderClassIficationStr		= "";
	private String kensaTimestampStr				= "";
	private String kensaSijiStr					= "";
	private String systemKbnStr					= "";

	private String iraiDoctorNoStr				= "";
	private String sectionIDStr					= "";
	private String denpyoByoutouIDStr			= "";
	private String remarksStr					= "";
	private String bikouStr						= "";
	private String inquireCodeStr				= "";
	private String studyInstanceUIDStr			= "";				//Ris.OrderMainTable.StudyInstanceUID
	private String iraiSectionIDStr				= "";				//Ris.OrderMainTable.Irai_Section_ID
	private String iraiDoctorEnglishNameStr		= "";				//Ris.OrderMainTable.Irai_Doctor_No →SectionDoctorMaster.Doctor_English_Name
	private String sectionEnglishNameStr		= "";				//Ris.OrderMainTable.Irai_Section_ID→SectionMaster.Section_Doctor_EnglishName
	private String schProcStepDescreptionStr	= "";				//Ris.WorkListInfo.schProcStepDescreption
	private String reqProcDescreptionStr		= "";				//Ris.WorkListInfo.reqProcDescreption
	private String schActionVersionStr			= "";				//Ris.WorkListInfo.schActionVersion
	private String schActionDesignatorStr		= "";				//Ris.WorkListInfo.schActionDesignator
	private String schActionCodeStr				= "";				//Ris.WorkListInfo.schActionCode
	private String schActionMeaningsStr			= "";				//Ris.WorkListInfo.schActionMeanings
	private String perfPhysiciansNameRomaStr	= "";				//Ris.WorkListInfo.perfPhysiciansNameRoma
	private String perfPhysiciansNameKanjiStr	= "";				//Ris.WorkListInfo.perfPhysiciansNameKanji
	private String perfPhysiciansNameKanaStr	= "";				//Ris.WorkListInfo.perfPhysiciansNameKana
	private String reqProcedureCodesStr			= "";				//Ris.WorkListInfo.reqProcedureCodes
	private String reqProcedureDesignatorStr	= "";				//Ris.WorkListInfo.reqProcedureDesignator
	private String reqProcedureVersionStr		= "";				//Ris.WorkListInfo.reqProcedureVersion
	private String reqProcedureMeaningsStr		= "";				//Ris.WorkListInfo.reqProcedureMeanings
	private String requestingPhysicianStr		= "";				//Ris.WorkListInfo.requestingPhysician
	private String requestingServiceStr			= "";				//Ris.WorkListInfo.requestingService
	private String patientResidenceStr			= "";				//Ris.WorkListInfo.patientResidence
	private String patientNameRomaStr			= "";				//Ris.WorkListInfo.patientNameRoma
	private String patientNameKanjiStr			= "";				//Ris.WorkListInfo.patientNameKanji
	private String patientNameKanaStr			= "";				//Ris.WorkListInfo.patientNameKana

	private ArrayList arrListOrderBui = new ArrayList(); //OrderBuiBeanのリスト


	//
	// risIDStrのSET
	public void SetRisID( String risID )
	{
		if( risID != null )
		{
			this.risIDStr = risID;
		}
	}
	// risIDStrのGET
	public String GetRisID()
	{
		return this.risIDStr;
	}
	//
	// kanjaIDStrのSET
	public void SetKanjaID( String kanjaID )
	{
		if( kanjaID != null )
		{
			this.kanjaIDStr = kanjaID;
		}
	}
	// kanjaIDStrのGET
	public String GetKanjaID()
	{
		return this.kanjaIDStr;
	}
	//
	// orderNoStrのSET
	public void SetOrderNo( String orderNo )
	{
		if( orderNo != null )
		{
			this.orderNoStr = orderNo;
			if( this.accessionNoStr.length() == 0 )
			{
				this.accessionNoStr = orderNo;
			}
		}
	}
	// orderNoStrのGET
	public String GetOrderNo()
	{
		return this.orderNoStr;
	}

	//
	// statusStrのSET
	public void SetStatus( String status )
	{
		if( status != null )
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
	// kensatypeIDStrのSET
	public void SetKensatypeID( String kensatypeID )
	{
		if( kensatypeID != null )
		{
			this.kensatypeIDStr = kensatypeID;
		}
	}
	// kensatypeIDStrのGET
	public String GetKensatypeID()
	{
		return this.kensatypeIDStr;
	}

	//
	// kensaTimestampStrのSET
	public void SetKensaTimestamp( String kensaTimestamp )
	{
		if( kensaTimestamp != null )
		{
			this.kensaTimestampStr = kensaTimestamp;
		}
	}
	// kensaTimestampStrのGET
	public String GetKensaTimestamp()
	{
		if (kensaTimestampStr == null || kensaTimestampStr.length() == 0)
		{
			return "0";
		}
		else
		{
			return kensaTimestampStr;
		}
	}

	// kensaTimestampStrのGET
	public String GetKensaTimestampStr()
	{
		String kensaTimeStr = kensaTimestampStr;

		if (kensaTimeStr == null || kensaTimeStr.length() == 0)
		{
			return "";
		}
		else
		{
			if ("9999".equals(kensaTimeStr) || "999999".equals(kensaTimeStr))
			{
				return "";
			}
			else
			{
                int kensaTimeInt = kensaTimeStr.length();

                //左0埋めで6桁にする
                for (int k = kensaTimeInt; k < 6; k++)
                {
                    kensaTimeStr = "0" + kensaTimeStr;
                }
                String hhStr = kensaTimeStr.substring(0, 2);
                String miStr = kensaTimeStr.substring(2, 2 + 2);

                kensaTimeStr = hhStr + ":" + miStr;
				return kensaTimeStr;
			}
		}
	}

	// kensaDateStrのSET
	public void SetKensaDate( String kensaDate )
	{
		if( kensaDate != null )
		{
			this.kensaDateStr = kensaDate;
		}
	}
	// kensaDateStrのGET
	public int GetKensaDate()
	{
		if (kensaDateStr == null || kensaDateStr.length() == 0)
		{
			return Const.INT_MINVALUE;
		}
		else
		{
			return Integer.parseInt(kensaDateStr);
		}
	}

	// kensaDateStrのGET
	public String GetKensaDateStr()
	{
		String kensaTimestampStr = "";
		if (kensaDateStr != null && kensaDateStr.length() == 8)
		{
			String yyyyStr = kensaDateStr.substring( 0, 4 );
			String mmStr   = kensaDateStr.substring( 4, 4 + 2 );
			String ddStr   = kensaDateStr.substring( 6, 6 + 2 );
			kensaTimestampStr =  yyyyStr + "/" + mmStr + "/" + ddStr;
		}
		return kensaTimestampStr;
	}

	//
	// kensasituIDStrのSET
	public void SetKensasituID( String kensasituID )
	{
		if( kensasituID != null )
		{
			this.kensasituIDStr = kensasituID;
		}
	}
	// kensasituIDStrのGET
	public String GetKensasituID()
	{
		return this.kensasituIDStr;
	}

	//
	// planIDStrのSET
	public void SetPlanID( String planID )
	{
		if( planID != null )
		{
			this.planIDStr = planID;
		}
	}
	// kensasituIDStrのGET
	public String GetPlanID()
	{
		return this.planIDStr;
	}

	//
	// kensakikiIDStrのSET
	public void SetKensakikiID( String kensakikiID )
	{
		if( kensakikiID != null )
		{
			this.kensakikiIDStr = kensakikiID;
		}
	}
	// kensakikiIDStrのGET
	public String GetKensakikiID()
	{
		return this.kensakikiIDStr;
	}

	//
	// denpyoNyugaiKbnStrのSET
	public void SetDenpyoNyugaiKbn( String denpyoNyugaiKbn )
	{
		if( denpyoNyugaiKbn != null )
		{
			this.denpyoNyugaiKbnStr = denpyoNyugaiKbn;
		}
	}
	// denpyoNyugaiKbnStrのGET
	public String GetDenpyoNyugaiKbn()
	{
		return this.denpyoNyugaiKbnStr;
	}

	//
	// kensaDateAgeStrのSET
	public void SetKensaDateAge( String kensaDateAge )
	{
		if( kensaDateAge != null )
		{
			this.kensaDateAgeStr = kensaDateAge;
		}
	}
	// kensaDateAgeStrのGET
	public int GetKensaDateAge()
	{
		if (kensaDateAgeStr == null || kensaDateAgeStr.length() ==0)
		{
			return Const.INT_MINVALUE;
		}
		else
		{
			return Integer.parseInt(kensaDateAgeStr);
		}
	}

	// accessionNoStrのSET
	public void SetAccessionNo( String accessionNo )
	{
		if( accessionNo != null )
		{
			this.accessionNoStr = accessionNo;
		}
	}
	// accessionNoStrのGET
	public String GetAccessionNo()
	{
		return accessionNoStr;
	}

	// dokueiFlgStrのSET
	public void SetDokueiFlg( String dokueiFlg )
	{
		if( dokueiFlg != null )
		{
			this.dokueiFlgStr = dokueiFlg;
		}
	}
	// dokueiFlgStrのGET
	public String GetDokueiFlg()
	{
		return dokueiFlgStr;
	}

	//
	// terminalIDIntのSET
	public void SetTerminalID( int terminalID )
	{
		this.terminalIDInt = terminalID;
	}
	// terminalIDIntのGET
	public int GetTerminalID()
	{
		return this.terminalIDInt;
	}

	// terminalNameStrのSET
	public void SetTerminalName( String terminalName )
	{
		if( terminalName != null )
		{
			this.terminalNameStr = terminalName;
		}
	}
	// terminalNameStrのGET
	public String GetTerminalName()
	{
		return terminalNameStr;
	}

	// yuusenFlgStrのSET
	public void SetYuusenFlg( String yuusenFlg )
	{
		if( yuusenFlg != null )
		{
			this.yuusenFlgStr = yuusenFlg;
		}
	}
	// yuusenFlgStrのGET
	public String GetYuusenFlg()
	{
		return yuusenFlgStr;
	}

	// renrakuMemoStrのSET
	public void SetRenrakuMemo( String renrakuMemo )
	{
		if( renrakuMemo != null )
		{
			this.renrakuMemoStr = renrakuMemo;
		}
	}
	// renrakuMemoStrのGET
	public String GetRenrakuMemo()
	{
		return renrakuMemoStr;
	}

	// kensaSijiStrのSET
	public void SetKensaSiji( String kensaSiji )
	{
		if( kensaSiji != null )
		{
			this.kensaSijiStr = kensaSiji;
		}
	}
	// kensaSijiStrのGET
	public String GetKensaSiji()
	{
		return kensaSijiStr;
	}

	// orderClassIficationStrのSET
	public void SetOrderClassIfication( String classIfication )
	{
		if( classIfication != null )
		{
			this.orderClassIficationStr = classIfication;
		}
	}
	// orderClassIficationStrのGET
	public String GetOrderClassIfication()
	{
		return orderClassIficationStr;
	}

	// systemKbnStrのSET
	public void SetSystemKbn( String systemKbn )
	{
		if( systemKbn != null )
		{
			this.systemKbnStr = systemKbn;
		}
	}
	// systemKbnStrのGET
	public String GetSystemKbn()
	{
		return systemKbnStr;
	}

	// iraiDoctorNoStrのSET
	public void SetIraiDoctorNo( String iraiDoctorNo )
	{
		if( iraiDoctorNo != null )
		{
			this.iraiDoctorNoStr = iraiDoctorNo;
		}
	}
	// iraiDoctorNoStrのGET
	public String GetIraiDoctorNo()
	{
		return iraiDoctorNoStr;
	}

	// sectionIDStrのSET
	public void SetSectionID( String sectionID )
	{
		if( sectionID != null )
		{
			this.sectionIDStr = sectionID;
		}
	}
	// sectionIDStrのGET
	public String GetSectionID()
	{
		return sectionIDStr;
	}

	// denpyoByoutouIDStrのSET
	public void SetDenpyoByoutouID( String denpyoByoutouID )
	{
		if( denpyoByoutouID != null )
		{
			this.denpyoByoutouIDStr = denpyoByoutouID;
		}
	}
	// denpyoByoutouIDStrのGET
	public String GetDenpyoByoutouID()
	{
		return denpyoByoutouIDStr;
	}

	// remarksStrのSET
	public void SetRemarks( String remarks )
	{
		if( remarks != null )
		{
			this.remarksStr = remarks;
		}
	}
	// remarksStrのGET
	public String GetRemarks()
	{
		return remarksStr;
	}

	// bikouStrのSET
	public void SetBikou( String bikou )
	{
		if( bikou != null )
		{
			this.bikouStr = bikou;
		}
	}
	// bikouStrのGET
	public String GetBikou()
	{
		return bikouStr;
	}

	// inquireCodeStrのSET
	public void SetInquireCode( String inquireCode )
	{
		if( inquireCode != null )
		{
			this.inquireCodeStr = inquireCode;
		}
	}
	// inquireCodeStrのGET
	public String GetInquireCode()
	{
		return inquireCodeStr;
	}
//
	// studyInstanceUIDStrのSET
	public void SetStudyInstanceUID( String studyInstanceUID )
	{
		if( studyInstanceUID != null )
		{
			this.studyInstanceUIDStr = studyInstanceUID;
		}
	}
	// studyInstanceUIDStrのGET
	public String GetStudyInstanceUID()
	{
		return this.studyInstanceUIDStr;
	}
//
	// iraiSectionIDStrのSET
	public void SetIraiSectionID( String iraiSectionID )
	{
		if( iraiSectionID != null )
		{
			this.iraiSectionIDStr = iraiSectionID;
		}
	}
	// iraiSectionIDStrのGET
	public String GetIraiSectionID()
	{
		return this.iraiSectionIDStr;
	}
//
	// iraiDoctorEnglishNameStrのSET
	public void SetIraiDoctorEnglishName( String iraiDoctorEnglishName )
	{
		if( iraiDoctorEnglishName != null )
		{
			this.iraiDoctorEnglishNameStr = iraiDoctorEnglishName;
		}
	}
	// iraiDoctorEnglishNameStrのGET
	public String GetIraiDoctorEnglishName()
	{
		return this.iraiDoctorEnglishNameStr;
	}
//
	// sectionEnglishNameStrのSET
	public void SetSectionEnglishName( String sectionEnglishName )
	{
		if( sectionEnglishName != null )
		{
			this.sectionEnglishNameStr = sectionEnglishName;
		}
	}
	// sectionEnglishNameStrのGET
	public String GetSectionEnglishName()
	{
		return this.sectionEnglishNameStr;
	}
//
	// arrListOrderBuiの取得
	public ArrayList GetOrderBuiList()
	{
		return this.arrListOrderBui;
	}
	// arrListOrderBuiの初期化
	public void ReconstructOrderBuiList(ArrayList arrListOrder)
	{
		if (arrListOrder != null)
		{
			this.arrListOrderBui = arrListOrder;
		}
	}
//
	//
	// schProcStepDescreptionStrのSET
	public void SetSchProcStepDescreption(String schProcStepDescreption)
	{
		if (schProcStepDescreption != null)
		{
			this.schProcStepDescreptionStr = schProcStepDescreption;
		}
	}
	// schProcStepDescreptionStrのGET
	public String GetSchProcStepDescreption()
	{
		return this.schProcStepDescreptionStr;
	}
	//
//
	// reqProcDescreptionStrのSET
	public void SetReqProcDescreption(String reqProcDescreption)
	{
		if (reqProcDescreption != null)
		{
			this.reqProcDescreptionStr = reqProcDescreption;
		}
	}
	// reqProcDescreptionStrのGET
	public String GetReqProcDescreption()
	{
		return this.reqProcDescreptionStr;
	}
//
	//
	// schActionVersionStrのSET
	public void SetSchActionVersion(String schActionVersion)
	{
		if (schActionVersion != null)
		{
			this.schActionVersionStr = schActionVersion;
		}
	}
	// schActionVersionStrのGET
	public String GetSchActionVersion()
	{
		return this.schActionVersionStr;
	}
//
	// schActionDesignatorStrのSET
	public void SetSchActionDesignator( String schActionDesignator )
	{
		if( schActionDesignator != null )
		{
			this.schActionDesignatorStr = schActionDesignator;
		}
	}
	// schActionDesignatorStrのGET
	public String GetSchActionDesignator()
	{
		return this.schActionDesignatorStr;
	}
//
	// schActionCodeStrのSET
	public void SetSchActionCode( String schActionCode )
	{
		if( schActionCode != null )
		{
			this.schActionCodeStr = schActionCode;
		}
	}
	// schActionCodeStrのGET
	public String GetSchActionCode()
	{
		return this.schActionCodeStr;
	}
//
	// schActionMeaningsStrのSET
	public void SetSchActionMeanings( String schActionMeanings )
	{
		if( schActionMeanings != null )
		{
			this.schActionMeaningsStr = schActionMeanings;
		}
	}
	// schActionMeaningsStrのGET
	public String GetSchActionMeanings()
	{
		return this.schActionMeaningsStr;
	}
//
	// perfPhysiciansNameRomaStrのSET
	public void SetPerfPhysiciansNameRoma( String perfPhysiciansNameRoma )
	{
		if( perfPhysiciansNameRoma != null )
		{
			this.perfPhysiciansNameRomaStr = perfPhysiciansNameRoma;
		}
	}
	// perfPhysiciansNameRomaStrのGET
	public String GetPerfPhysiciansNameRoma()
	{
		return this.perfPhysiciansNameRomaStr;
	}
//
	// perfPhysiciansNameKanjiStrのSET
	public void SetPerfPhysiciansNameKanji( String perfPhysiciansNameKanji )
	{
		if( perfPhysiciansNameKanji != null )
		{
			this.perfPhysiciansNameKanjiStr = perfPhysiciansNameKanji;
		}
	}
	// perfPhysiciansNameKanjiStrのGET
	public String GetPerfPhysiciansNameKanji()
	{
		return this.perfPhysiciansNameKanjiStr;
	}
//
	// perfPhysiciansNameKanaStrのSET
	public void SetPerfPhysiciansNameKana( String perfPhysiciansNameKana )
	{
		if( perfPhysiciansNameKana != null )
		{
			this.perfPhysiciansNameKanaStr = perfPhysiciansNameKana;
		}
	}
	// perfPhysiciansNameKanaStrのGET
	public String GetPerfPhysiciansNameKana()
	{
		return this.perfPhysiciansNameKanaStr;
	}
//
	// reqProcedureCodesStrのSET
	public void SetReqProcedureCodes( String reqProcedureCodes )
	{
		if( reqProcedureCodes != null )
		{
			this.reqProcedureCodesStr = reqProcedureCodes;
		}
	}
	// reqProcedureCodesStrのGET
	public String GetReqProcedureCodes()
	{
		return this.reqProcedureCodesStr;
	}
//
	// reqProcedureDesignatorStrのSET
	public void SetReqProcedureDesignator( String reqProcedureDesignator )
	{
		if( reqProcedureDesignator != null )
		{
			this.reqProcedureDesignatorStr = reqProcedureDesignator;
		}
	}
	// reqProcedureDesignatorStrのGET
	public String GetReqProcedureDesignator()
	{
		return this.reqProcedureDesignatorStr;
	}
//
	// reqProcedureVersionStrのSET
	public void SetReqProcedureVersion( String reqProcedureVersion )
	{
		if( reqProcedureVersion != null )
		{
			this.reqProcedureVersionStr = reqProcedureVersion;
		}
	}
	// reqProcedureVersionStrのGET
	public String GetReqProcedureVersion()
	{
		return this.reqProcedureVersionStr;
	}
//
	// reqProcedureMeaningsStrのSET
	public void SetReqProcedureMeanings( String reqProcedureMeanings)
	{
		if (reqProcedureMeanings != null)
		{
			this.reqProcedureMeaningsStr = reqProcedureMeanings;
		}
	}
	// reqProcedureMeaningsStrのGET
	public String GetReqProcedureMeanings()
	{
		return this.reqProcedureMeaningsStr;
	}
//
	// requestingPhysicianStrのSET
	public void SetRequestingPhysician( String requestingPhysician)
	{
		if (requestingPhysician != null)
		{
			this.requestingPhysicianStr = requestingPhysician;
		}
	}
	// requestingPhysicianStrのGET
	public String GetRequestingPhysician()
	{
		return this.requestingPhysicianStr;
	}
//
	// requestingServiceStrのSET
	public void SetRequestingService( String requestingService)
	{
		if (requestingService != null)
		{
			this.requestingServiceStr = requestingService;
		}
	}
	// requestingServiceStrのGET
	public String GetRequestingService()
	{
		return this.requestingServiceStr;
	}
//
	// patientResidenceStrのSET
	public void SetPatientResidence(String patientResidence)
	{
		if (patientResidence != null)
		{
			this.patientResidenceStr = patientResidence;
		}
	}
	// patientResidenceStrのGET
	public String GetPatientResidence()
	{
		return this.patientResidenceStr;
	}
//
	// patientNameRomaStrのSET
	public void SetPatientNameRoma(String patientNameRoma)
	{
		if (patientNameRoma != null)
		{
			this.patientNameRomaStr = patientNameRoma;
		}
	}
	// patientNameRomaStrのGET
	public String GetPatientNameRoma()
	{
		return this.patientNameRomaStr;
	}
//
	// patientNameKanjiStrのSET
	public void SetPatientNameKanji(String patientNameKanji)
	{
		if (patientNameKanji != null)
		{
			this.patientNameKanjiStr = patientNameKanji;
		}
	}
	// patientNameKanjiStrのGET
	public String GetPatientNameKanji()
	{
		return this.patientNameKanjiStr;
	}
//
	// patientNameKanaStrのSET
	public void SetPatientNameKana(String patientNameKana)
	{
		if (patientNameKana != null)
		{
			this.patientNameKanaStr = patientNameKana;
		}
	}
	// patientNameKanaStrのGET
	public String GetPatientNameKana()
	{
		return this.patientNameKanaStr;
	}
}
