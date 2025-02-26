package ris.lib.mwm.bean;

import java.sql.Timestamp;

import ris.portable.common.Const;

/// <summary>
/// MWMInfoBean の概要の説明です。
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class MWMInfoBean
{
	// private members
	private String		nameModeStr						= "0";					//氏名キャレット変換フラグ　1:データ中の半角スペース・全角スペースを「^」に変換する　0:変換しない
	private int			mwmTypeInt						= 1000;					//0-899の場合にのみAETitleを使用
	private String		schStationAeTitleStr			= "";					//Ris.WorkListInfo.SCH_STATION_AE_TITLE
	private String		schProcStepLocationStr			= "";					//Ris.WorkListInfo.SCH_PROC_STEP_LOCATION
	private Timestamp	schProcStepStartDateDT			= new Timestamp(System.currentTimeMillis());			//Ris.WorkListInfo.SCH_PROC_STEP_START_DATE
	private Timestamp	schProcStepStartTimeDT			= new Timestamp(System.currentTimeMillis());			//Ris.WorkListInfo.SCH_PROC_STEP_START_TIME
	private String 		schPerfPhysiciansNameRomaStr	= "";					//Ris.WorkListInfo.SCH_PERF_PHYSICIANS_NAME_ROMA
	private String 		schPerfPhysiciansNameKanjiStr	= "";					//Ris.WorkListInfo.SCH_PERF_PHYSICIANS_NAME_KANJI
	private String 		schPerfPhysiciansNameKanaStr	= "";					//Ris.WorkListInfo.SCH_PERF_PHYSICIANS_NAME_KANA
	private String 		schProcStepDescriptionStr		= "UnKnown";			//Ris.WorkListInfo.SCH_PROC_STEP_DESCRIPTION
	private String 		schProcStepIDStr				= "1";					//Ris.WorkListInfo.SCH_PROC_STEP_ID
	private String 		commentsOnTheSchProcStepStr		= "";					//Ris.WorkListInfo.COMMENTS_ON_THE_SCH_PROC_STEP
	private String 		modalityStr						= "";					//Ris.WorkListInfo.MODALITY
	private String 		reqProcIDStr					= "";					//Ris.WorkListInfo.REQ_PROC_ID
	private String 		studyInstanceUIDStr				= "";					//Ris.WorkListInfo.STUDY_INSTANCE_UID
	private String 		reqProcDescriptionStr			= "UnKnown";			//Ris.WorkListInfo.REQ_PROC_DESCRIPTION
	private String 		requestingPhysicianStr			= "";					//Ris.WorkListInfo.REQUESTING_PHYSICIAN
	private String 		requestingServiceStr			= "";					//Ris.WorkListInfo.REQUESTING_SERVICE
	private String 		accessionNumberStr				= "";					//Ris.WorkListInfo.ACCESSION_NUMBER
	private String 		institutionNameStr				= "";					//Ris.WorkListInfo.INSTITUTION_NAME
	private String 		institutionAddressStr			= "";					//Ris.WorkListInfo.INSTITUTION_ADDRESS
	private String 		patientNameRomaStr				= "";					//Ris.WorkListInfo.PATIENT_NAME_ROMA
	private String 		patientNameKanjiStr				= "";					//Ris.WorkListInfo.PATIENT_NAME_KANJI
	private String 		patientNameKanaStr				= "";					//Ris.WorkListInfo.PATIENT_NAME_KANA
	private String 		patientIDStr					= "";					//Ris.WorkListInfo.PATIENT_ID
	private Timestamp	patientBirthDateDT				= Const.TIMESTAMP_MINVALUE;	//Ris.WorkListInfo.PATIENT_BIRTH_DATE
	private String		patientSexStr					= "";					//Ris.WorkListInfo.PATIENT_SEX
	private String		patientWeightStr				= "";					//Ris.WorkListInfo.PATIENT_WEIGHT
	private String		patientSizeStr					= "";					//Ris.WorkListInfo.PATIENT_SIZE
	private String		patientCommentsStr				= "";					//Ris.WorkListInfo.PATIENT_COMMENTS
	private String		patientResidenceStr				= "";					//Ris.WorkListInfo.PATIENT_RESIDENCE
	private String		schActionCodesStr				= "";					//Ris.WorkListInfo.SCH_ACTION_CODES
	private String		schActionDesignatorStr			= "";					//Ris.WorkListInfo.SCH_ACTION_DESIGNATOR
	private String		schActionVersionStr				= "";					//Ris.WorkListInfo.SCH_ACTION_VERSION
	private String		schActionMeaningsStr			= "";					//Ris.WorkListInfo.SCH_ACTION_MEANINGS
	private String		refStudyClassUIDStr				= "";					//Ris.WorkListInfo.REF_STUDY_CLASS_UID
	private String		refStudyInstanceUIDStr			= "";					//Ris.WorkListInfo.REF_STUDY_INSTANCE_UID
	private String		refPhysicianNameStr				= "";					//Ris.WorkListInfo.REF_PHYSICIAN_NAME
	private String		reqProcedureCodesStr			= "";					//Ris.WorkListInfo.REQ_PROCEDURE_CODES
	private String		reqProcedureDesignatorStr		= "";					//Ris.WorkListInfo.REQ_PROCEDURE_DESIGNATOR
	private String		reqProcedureVersionStr			= "";					//Ris.WorkListInfo.REQ_PROCEDURE_VERSION
	private String		reqProcedureMeaningsStr			= "";					//Ris.WorkListInfo.REQ_PROCEDURE_MEANINGS
	private int			numberOfCopiesInt				= 0;					//Ris.WorkListInfo.NUMBEROFCOPIES
	//
	private String kikiIDStr = ""; //Ris.KensaKikiMaster.KENSAKIKI_ID

	public MWMInfoBean()
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
		strBuild.append("[MWMInfoBean]");
		strBuild.append("SCH_STATION_AE_TITLE="				+ schStationAeTitleStr					+ ";");
		strBuild.append("SCH_PROC_STEP_LOCATION="			+ schProcStepLocationStr				+ ";");
		strBuild.append("SCH_PROC_STEP_START_DATE="			+ schProcStepStartDateDT.toString()		+ ";");
		strBuild.append("SCH_PROC_STEP_START_TIME="			+ schProcStepStartTimeDT.toString()		+ ";");
		strBuild.append("SCH_PERF_PHYSICIANS_NAME_ROMA="	+ schPerfPhysiciansNameRomaStr			+ ";");
		strBuild.append("SCH_PERF_PHYSICIANS_NAME_KANJI="	+ schPerfPhysiciansNameKanjiStr			+ ";");
		strBuild.append("SCH_PERF_PHYSICIANS_NAME_KANA="	+ schPerfPhysiciansNameKanaStr			+ ";");
		strBuild.append("SCH_PROC_STEP_DESCRIPTION="		+ schProcStepDescriptionStr				+ ";");
		strBuild.append("SCH_PROC_STEP_ID="					+ schProcStepIDStr						+ ";");
		strBuild.append("COMMENTS_ON_THE_SCH_PROC_STEP="	+ commentsOnTheSchProcStepStr			+ ";");
		strBuild.append("MODALITY="							+ modalityStr							+ ";");
		strBuild.append("REQ_PROC_ID="						+ reqProcIDStr							+ ";");
		strBuild.append("STUDY_INSTANCE_UID="				+ studyInstanceUIDStr					+ ";");
		strBuild.append("REQ_PROC_DESCRIPTION="				+ reqProcDescriptionStr					+ ";");
		strBuild.append("REQUESTING_PHYSICIAN="				+ requestingPhysicianStr				+ ";");
		strBuild.append("REQUESTING_SERVICE="				+ requestingServiceStr					+ ";");
		strBuild.append("ACCESSION_NUMBER="					+ accessionNumberStr					+ ";");
		strBuild.append("INSTITUTION_NAME="					+ institutionNameStr					+ ";");
		strBuild.append("INSTITUTION_ADDRESS="				+ institutionAddressStr					+ ";");
		strBuild.append("PATIENT_NAME_ROMA="				+ patientNameRomaStr					+ ";");
		strBuild.append("PATIENT_NAME_KANJI="				+ patientNameKanjiStr					+ ";");
		strBuild.append("PATIENT_NAME_KANA="				+ patientNameKanaStr					+ ";");
		strBuild.append("PATIENT_ID="						+ patientIDStr							+ ";");
		strBuild.append("PATIENT_BIRTH_DATE="				+ patientBirthDateDT.toString()			+ ";");
		strBuild.append("PATIENT_SEX="						+ patientSexStr							+ ";");
		strBuild.append("PATIENT_WEIGHT="					+ patientWeightStr						+ ";");
		strBuild.append("PATIENT_SIZE="						+ patientSizeStr						+ ";");
		strBuild.append("PATIENT_COMMENTS="					+ patientCommentsStr					+ ";");
		strBuild.append("PATIENT_RESIDENCE="				+ patientResidenceStr					+ ";");
		strBuild.append("SCH_ACTION_CODES="					+ schActionCodesStr						+ ";");
		strBuild.append("SCH_ACTION_DESIGNATOR="			+ schActionDesignatorStr				+ ";");
		strBuild.append("SCH_ACTION_VERSION="				+ schActionVersionStr					+ ";");
		strBuild.append("SCH_ACTION_MEANINGS="				+ schActionMeaningsStr					+ ";");
		strBuild.append("REF_STUDY_CLASS_UID="				+ refStudyClassUIDStr					+ ";");
		strBuild.append("REF_STUDY_INSTANCE_UID="			+ refStudyInstanceUIDStr				+ ";");
		strBuild.append("REF_PHYSICIAN_NAME="				+ refPhysicianNameStr					+ ";");
		strBuild.append("REQ_PROCEDURE_CODES="				+ reqProcedureCodesStr					+ ";");
		strBuild.append("REQ_PROCEDURE_DESIGNATOR="			+ reqProcedureDesignatorStr				+ ";");
		strBuild.append("REQ_PROCEDURE_VERSION="			+ reqProcedureVersionStr				+ ";");
		strBuild.append("REQ_PROCEDURE_MEANINGS="			+ reqProcedureMeaningsStr				+ ";");
		strBuild.append("NUMBEROFCOPIES="					+ numberOfCopiesInt						+ ";");
		//
		strBuild.append("KIKI_ID=" + kikiIDStr + ";");

		return strBuild.toString();
	}
//
	// schStationAeTitleStrのGET
	public String GetSchStationAeTitle()
	{
		/* DBがNOT NULLなので、このロジックはとりあえず外す
		String aeTitle = "";

		if( this.mwmTypeInt >= 0 && this.mwmTypeInt <= 899 )
		{
			aeTitle = this.schStationAeTitleStr;
		}
		*/

		return this.schStationAeTitleStr;
	}
	// schStationAeTitleStrのSET
	public void SetSchStationAeTitle(String schStationAeTitle)
	{
		this.schStationAeTitleStr = schStationAeTitle;
	}
//
	// schProcStepLocationStrのGET
	public String GetSchProcStepLocation()
	{
		/* DBがNOT NULLなので、このロジックはとりあえず外す
		String aeTitle = "";

		if( this.mwmTypeInt >= 0 && this.mwmTypeInt <= 899 )
		{
			aeTitle = this.schProcStepLocationStr;
		}
		*/

		return this.schProcStepLocationStr;
	}
	// schProcStepLocationStrのSET
	public void SetSchProcStepLocation(String schProcStepLocation)
	{
		this.schProcStepLocationStr = schProcStepLocation;
	}
//
	// schProcStepStartDateDTのGET
	public Timestamp GetSchProcStepStartDate()
	{
		return this.schProcStepStartDateDT;
	}
	// schProcStepStartDateDTのSET
	public void SetSchProcStepStartDate(Timestamp schProcStepStartDate)
	{
		this.schProcStepStartDateDT = schProcStepStartDate;
	}
//
	// schProcStepStartTimeDTのGET
	public Timestamp GetSchProcStepStartTime()
	{
		return this.schProcStepStartTimeDT;
	}
	// schProcStepStartTimeDTのSET
	public void SetSchProcStepStartTime(Timestamp schProcStepStartTime)
	{
		this.schProcStepStartTimeDT = schProcStepStartTime;
	}
//
	// schPerfPhysiciansNameRomaStrのGET
	public String GetSchPerfPhysiciansNameRoma()
	{
		return this.schPerfPhysiciansNameRomaStr;
	}
	// schPerfPhysiciansNameRomaStrのSET
	public void SetSchPerfPhysiciansNameRoma(String schPerfPhysiciansNameRoma)
	{
		this.schPerfPhysiciansNameRomaStr = schPerfPhysiciansNameRoma;
	}
//
	// schPerfPhysiciansNameKanjiStrのGET
	public String GetSchPerfPhysiciansNameKanji()
	{
		return this.schPerfPhysiciansNameKanjiStr;
	}
	// schPerfPhysiciansNameKanjiStrのSET
	public void SetSchPerfPhysiciansNameKanji(String schPerfPhysiciansNameKanji)
	{
		this.schPerfPhysiciansNameKanjiStr = schPerfPhysiciansNameKanji;
	}
//
	// schPerfPhysiciansNameKanaStrのGET
	public String GetSchPerfPhysiciansNameKana()
	{
		return this.schPerfPhysiciansNameKanaStr;
	}
	// schPerfPhysiciansNameKanaStrのSET
	public void SetSchPerfPhysiciansNameKana(String schPerfPhysiciansNameKana)
	{
		this.schPerfPhysiciansNameKanaStr = schPerfPhysiciansNameKana;
	}
//
	// schProcStepDescriptionStrのGET
	public String GetSchProcStepDescription()
	{
		return this.schProcStepDescriptionStr;
	}
	// schProcStepDescriptionStrのSET
	public void SetSchProcStepDescription(String schProcStepDescription)
	{
		this.schProcStepDescriptionStr = schProcStepDescription;
	}
//
	// schProcStepIDStrのGET
	public String GetSchProcStepID()
	{
		return this.schProcStepIDStr;
	}
	// schProcStepIDStrのSET
	public void SetSchProcStepID(String schProcStepID)
	{
		this.schProcStepIDStr = schProcStepID;
	}
//
	// commentsOnTheSchProcStepStrのGET
	public String GetCommentsOnTheSchProcStep()
	{
		return this.commentsOnTheSchProcStepStr;
	}
	// commentsOnTheSchProcStepStrのSET
	public void SetCommentsOnTheSchProcStep(String commentsOnTheSchProcStep)
	{
		this.commentsOnTheSchProcStepStr = commentsOnTheSchProcStep;
	}
//
	// modalityStrのGET
	public String GetModality()
	{
		return this.modalityStr;
	}
	// modalityStrのSET
	public void SetModality(String modality)
	{
		this.modalityStr = modality;
	}
//
	// reqProcIDStrのGET
	public String GetReqProcID()
	{
		return this.reqProcIDStr;
	}
	// reqProcIDStrのSET
	public void SetReqProcID(String reqProcID)
	{
		this.reqProcIDStr = reqProcID;
	}
//
	// studyInstanceUIDStrのGET
	public String GetStudyInstanceUID()
	{
		return this.studyInstanceUIDStr;
	}
	// studyInstanceUIDStrのSET
	public void SetStudyInstanceUID(String studyInstanceUID)
	{
		this.studyInstanceUIDStr = studyInstanceUID;
	}
//
	// reqProcDescriptionStrのGET
	public String GetReqProcDescription()
	{
		return this.reqProcDescriptionStr;
	}
	// reqProcDescriptionStrのSET
	public void SetReqProcDescription(String reqProcDescription)
	{
		this.reqProcDescriptionStr = reqProcDescription;
	}
//
	// requestingPhysicianStrのGET
	public String GetRequestingPhysician()
	{
		return this.requestingPhysicianStr;
	}
	// requestingPhysicianStrのSET
	public void SetRequestingPhysician(String requestingPhysician)
	{
		this.requestingPhysicianStr = requestingPhysician;
	}
//
	// requestingServiceStrのGET
	public String GetRequestingService()
	{
		return this.requestingServiceStr;
	}
	// requestingPhysicianStrのSET
	public void SetRequestingService(String requestingService)
	{
		this.requestingServiceStr = requestingService;
	}
//
	// accessionNumberStrのGET
	public String GetAccessionNumber()
	{
		return this.accessionNumberStr;
	}
	// accessionNumberStrのSET
	public void SetAccessionNumber(String accessionNumber)
	{
		this.accessionNumberStr = accessionNumber;
	}
//
	// institutionNameStrのGET
	public String GetInstitutionName()
	{
		return this.institutionNameStr;
	}
	// institutionNameStrのSET
	public void SetInstitutionName(String institutionName)
	{
		this.institutionNameStr = institutionName;
	}
//
	// institutionAddressStrのGET
	public String GetInstitutionAddress()
	{
		return this.institutionAddressStr;
	}
	// institutionAddressStrのSET
	public void SetInstitutionAddress(String institutionAddress)
	{
		this.institutionAddressStr = institutionAddress;
	}
//
	// patientNameRomaStrのGET
	public String GetPatientNameRoma()
	{
		return this.patientNameRomaStr;
	}
	// patientNameRomaStrのSET
	public void SetPatientNameRoma(String patientNameRoma)
	{
		// parameters
		this.patientNameRomaStr = patientNameRoma;
	}
//
	// patientNameKanjiStrのGET
	public String GetPatientNameKanji()
	{
		return this.patientNameKanjiStr;
	}
	// patientNameKanjiStrのSET
	public void SetPatientNameKanji(String patientNameKanji)
	{
		// parameters
		this.patientNameKanjiStr = patientNameKanji;
	}
//
	// patientNameKanaStrのGET
	public String GetPatientNameKana()
	{
		return this.patientNameKanaStr;
	}

	// patientNameKanaStrのSET
	public void SetPatientNameKana(String patientNameKana)
	{
		// parameters
		this.patientNameKanaStr = patientNameKana;
	}
//
	// patientIDStrのGET
	public String GetPatientID()
	{
		return this.patientIDStr;
	}

	// patientIDStrのSET
	public void SetPatientID(String patientID)
	{
		this.patientIDStr = patientID;
	}
//
	// patientBirthDateDTのGET
	public Timestamp GetPatientBirthDate()
	{
		return this.patientBirthDateDT;
	}
	// patientBirthDateDTのSET
	public void SetPatientBirthDate(Timestamp patientBirthDate)
	{
		this.patientBirthDateDT = patientBirthDate;
	}
//
	// patientSexStrのGET
	public String GetPatientSex()
	{
		return this.patientSexStr;
	}
	// patientSexStrのSET
	public void SetPatientSex(String patientSex)
	{
		this.patientSexStr = patientSex;
	}
//
	// patientWeightStrのGET
	public String GetPatientWeight()
	{
		return this.patientWeightStr;
	}
	// patientWeightStrのSET
	public void SetPatientWeight(String patientWeight)
	{
		this.patientWeightStr = patientWeight;
	}
//
	// patientSizeStrのGET
	public String GetPatientSize()
	{
		return this.patientSizeStr;
	}
	// patientSizeStrのSET
	public void SetPatientSize(String patientSize)
	{
		this.patientSizeStr = patientSize;
	}
//
	// patientCommentsStrのGET
	public String GetPatientComments()
	{
		return this.patientCommentsStr;
	}
	// patientCommentsStrのSET
	public void SetPatientComments(String patientComments)
	{
		this.patientCommentsStr = patientComments;
	}
//
	// patientResidenceStrのGET
	public String GetPatientResidence()
	{
		return this.patientResidenceStr;
	}
	// patientResidenceStrのSET
	public void SetPatientResidence(String patientResidence)
	{
		this.patientResidenceStr = patientResidence;
	}
//
	// schActionCodesStrのGET
	public String GetSchActionCodes()
	{
		return this.schActionCodesStr;
	}
	// schActionCodesStrのSET
	public void SetSchActionCodes(String schActionCodes)
	{
		this.schActionCodesStr = schActionCodes;
	}
//
	// schActionDesignatorStrのGET
	public String GetSchActionDesignator()
	{
		return this.schActionDesignatorStr;
	}
	// schActionDesignatorStrのGET
	public void SetSchActionDesignator(String schActionDesignator)
	{
		this.schActionDesignatorStr = schActionDesignator;
	}
//
	// schActionVersionStrのGET
	public String GetSchActionVersion()
	{
		return this.schActionVersionStr;
	}

	// schActionVersionStrのGET
	public void SetSchActionVersion(String schActionVersion)
	{
		this.schActionVersionStr = schActionVersion;
	}
//
	// schActionMeaningsStrのGET
	public String GetSchActionMeanings()
	{
		return this.schActionMeaningsStr;
	}
	// schActionMeaningsStrのGET
	public void SetSchActionMeanings(String schActionMeanings)
	{
		this.schActionMeaningsStr = schActionMeanings;
	}
//
	// refStudyClassUIDStrのGET
	public String GetRefStudyClassUID()
	{
		return this.refStudyClassUIDStr;
	}
	// refStudyClassUIDStrのSET
	public void SetRefStudyClassUID(String refStudyClassUID)
	{
		this.refStudyClassUIDStr = refStudyClassUID;
	}
//
	// refStudyInstanceUIDStrのGET
	public String GetRefStudyInstanceUID()
	{
		return this.refStudyInstanceUIDStr;
	}
	// refStudyInstanceUIDStrのSET
	public void SetRefStudyInstanceUID(String refStudyInstanceUID)
	{
		this.refStudyInstanceUIDStr = refStudyInstanceUID;
	}
//
	// refPhysicianNameStrのGET
	public String GetRefPhysicianName()
	{
		return this.refPhysicianNameStr;
	}
	// refPhysicianNameStrのSET
	public void SetRefPhysicianName(String refPhysicianName)
	{
		this.refPhysicianNameStr = refPhysicianName;
	}
//
	// reqProcedureCodesStrのGET
	public String GetReqProcedureCodes()
	{
		return this.reqProcedureCodesStr;
	}
	// reqProcedureCodesStrのSET
	public void SetReqProcedureCodes(String reqProcedureCodes)
	{
		this.reqProcedureCodesStr = reqProcedureCodes;
	}
//
	// reqProcedureDesignatorStrのGET
	public String GetReqProcedureDesignator()
	{
		return this.reqProcedureDesignatorStr;
	}
	// reqProcedureDesignatorStrのSET
	public void SetReqProcedureDesignator(String reqProcedureDesignator)
	{
		this.reqProcedureDesignatorStr = reqProcedureDesignator;
	}
//
	// reqProcedureVersionStrのGET
	public String GetReqProcedureVersion()
	{
		return this.reqProcedureVersionStr;
	}
	// reqProcedureVersionStrのSET
	public void SetReqProcedureVersion(String reqProcedureVersion)
	{
		this.reqProcedureVersionStr = reqProcedureVersion;
	}
//
	// reqProcedureMeaningsStrのGET
	public String GetReqProcedureMeanings()
	{
		return this.reqProcedureMeaningsStr;
	}
	// reqProcedureMeaningsStrのSET
	public void SetReqProcedureMeanings(String reqProcedureMeanings)
	{
		this.reqProcedureMeaningsStr = reqProcedureMeanings;
	}
//
	// numberOfCopiesIntのGET
	public int GetNumberOfCopies()
	{
		return this.numberOfCopiesInt;
	}
	// numberOfCopiesIntのSET
	public void SetNumberOfCopies(int numberOfCopies)
	{
		this.numberOfCopiesInt = numberOfCopies;
	}
//
//
//
	// KikiInfoBeanのSET
	public void SetKikiInfo( KensaKikiBean kikiInfo )
	{
		if( kikiInfo != null )
		{
			this.schStationAeTitleStr = kikiInfo.GetAETitle();
			this.schProcStepLocationStr = kikiInfo.GetAETitle();
			this.modalityStr = kikiInfo.GetModality();
			this.nameModeStr = kikiInfo.GetNameMode();
			this.kikiIDStr   = kikiInfo.GetKensaKikiID(); //2007.11.19 Add H.Orikasa

			try
			{
				this.mwmTypeInt = Integer.parseInt(kikiInfo.GetMwmType());
			}
			catch( Exception ex )
			{
			}
		}
	}
//
	//// PatientInfoBeanのSET
	//public void SetPatientInfo( MwmPatientInfoBean patientInfo )
	//{
	//    if( patientInfo != null )
	//    {
	//        this.patientNameRomaStr		= patientInfo.GetRomaSimei();
	//        this.patientNameKanjiStr	= patientInfo.GetKanjiSimei();
	//        this.patientNameKanaStr		= patientInfo.GetKanaSimei();
	//        this.patientIDStr			= patientInfo.GetKanjaID();
	//        this.patientSexStr			= patientInfo.GetSex();
	//        try
	//        {
	//            this.patientWeightInt = Integer.parseInt(patientInfo.GetWeight());
	//        }
	//        catch
	//        {
	//        }
	//        try
	//        {
	//            this.patientSizeInt = Integer.parseInt(patientInfo.GetTall());
	//        }
	//        catch
	//        {
	//        }
	//        String birthday = patientInfo.GetBirthday();
	//        try
	//        {
	//            int yyyy = Integer.parseInt(birthday.SubString(0,4));
	//            int mm = Integer.parseInt(birthday.SubString(4,2));
	//            int dd = Integer.parseInt(birthday.SubString(6,2));
	//            this.patientBirthDateDT = new Timestamp( yyyy, mm, dd );

	//        }
	//        catch
	//        {
	//        }
	//    }
	//}
//
	// OrderInfoBeanのSET
	public void SetOrderInfo(MwmOrderInfoBean orderInfo)
	{
		if (orderInfo != null)
		{
			this.reqProcIDStr			= orderInfo.GetOrderNo();
			this.studyInstanceUIDStr	= orderInfo.GetStudyInstanceUID();
			this.accessionNumberStr		= orderInfo.GetAccessionNo();
			this.refStudyClassUIDStr	= orderInfo.GetStudyInstanceUID();
			this.refStudyInstanceUIDStr = orderInfo.GetStudyInstanceUID();
			this.requestingPhysicianStr = orderInfo.GetIraiDoctorEnglishName();
			this.requestingServiceStr	= orderInfo.GetSectionEnglishName();
		}
	}

	//// ExecutionInfoBeanのSET
	//public void SetExecutionInfoBean( MwmExecutionInfoBean executionInfoBean )
	//{
	//    if( executionInfoBean != null )
	//    {
	//        try
	//        {
	//            this.schProcStepIDStr = executionInfoBean.GetStartNumber().toString();
	//        }
	//        catch
	//        {
	//        }
	//    }
	//}

	//kikiIDStrのGET
	public String GetKensaKikiID()
	{
		return this.kikiIDStr;
	}
}
