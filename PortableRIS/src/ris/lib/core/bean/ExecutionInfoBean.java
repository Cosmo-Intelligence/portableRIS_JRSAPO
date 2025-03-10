package ris.lib.core.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.util.CommonString;
import ris.portable.common.Const;

/// <summary>
///
/// 実績情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.03.05	: 112478 / A.Kobayashi		: original
/// V2.01.00		: 2011.07.11    extends 999999 / NSK_H.Hashimoto	: NML-CAT1-002
/// V2.01.00		: 2011.07.26    extends 999999 / NSK_T.Koudate	: NML-CAT2-004
/// V2.01.01.13000	: 2011.11.15    extends 999999 / NSK_H.Hashimoto	: OMH-1-05
///
/// </summary>
public class ExecutionInfoBean
{
	// priavate members
	private String		risIDStr					= ""; 								//Ris.ExMainTable.RIS_ID
	private String		kensaTypeIDStr				= ""; 								//Ris.ExMainTable.KENSATYPE_ID
	private String		kensaDateStr				= ""; 								//Ris.ExMainTable.KENSA_DATE
	private String		kensaSituIDStr 				= ""; 								//Ris.ExMainTable.KENSASITU_ID
	private String		kensaKikiIDStr 				= ""; 								//Ris.ExMainTable.KENSAKIKI_ID
	private String		kanjaIDStr 					= ""; 								//Ris.ExMainTable.KANJA_ID
	private String		kensaDateAgeStr 			= CommonString.PATIENT_NULL_AGE;	//Ris.ExMainTable.KENSA_DATE_AGE
	private String		denpyoNyugaiKbnStr			= ""; 								//Ris.ExMainTable.DENPYO_NYUGAIKBN
	private String		uketukeTantouIDStr			= ""; 								//Ris.ExMainTable.UKETUKE_TANTOU_ID
	private String		uketukeTantouNameStr		= ""; 								//Ris.ExMainTable.UKETUKE_TANTOU_NAME
	private Timestamp	receiptDateTime			= Const.TIMESTAMP_MINVALUE;			//Ris.ExMainTable.RECEIPTDATE
	private String		receiptTerminalIDStr		= ""; 								//Ris.ExMainTable.RECEIPTTERMINALID
	private String		receiptNumberStr			= ""; 								//Ris.ExMainTable.RECEIPTNUMBER
	// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
	private String		receiptInitialCharStr		= ""; 								//Ris.ExMainTable.RECEIPT_INITIAL_CHAR
	// 2011.11.15 Add NSK_H.Hashimoto End
	private String		kensaGisiIDStr				= ""; 								//Ris.ExMainTable.KENSA_GISI_ID
	private String		kensaGisiNameStr			= ""; 								//Ris.ExMainTable.KENSA_GISI_NAME
	private Timestamp	examStartDateTime			= Const.TIMESTAMP_MINVALUE;			//Ris.ExMainTable.EXAMSTARTDATE
	private Timestamp	examStartDateFormShowTime	= Const.TIMESTAMP_MINVALUE;
	private Timestamp	examEndDateTime				= Const.TIMESTAMP_MINVALUE;			//Ris.ExMainTable.EXAMENDDATE
	private String		examTerminalIDStr			= ""; 								//Ris.ExMainTable.EXAMTERMINALID
	private int			startNumberInt				= 0;  								//Ris.ExMainTable.STARTNUMBER
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//private String		kangosiIDStr				= ""; 								//Ris.ExMainTable.KANGOSI_ID
	//private String		kangosiNameStr				= ""; 								//Ris.ExMainTable.KANGOSI_NAME

	// 2011.07.26 Del NSK_T.Koudate End
	private String		kensaDoctorIDStr			= ""; 								//Ris.ExMainTable.KENSAI_ID
	private String		kensaDoctorNameStr			= ""; 								//Ris.ExMainTable.KENSAI_NAME
	private String 		bikouStr					= ""; 								//Ris.ExMainTable.BIKOU
	private String 		renrakuMemoStr				= ""; 								//Ris.ExMainTable.RENRAKU_MEMO
	private String 		sijiIsiIDStr				= ""; 								//Ris.ExMainTable.SIJI_ISI_ID
	private String 		sijiIsiNameStr				= ""; 								//Ris.ExMainTable.SIJI_ISI_NAME
	private String 		sijiIsiCommentStr			= ""; 								//Ris.ExMainTable.SIJI_ISI_COMMENT
	private String 		tousiTimeStr				= ""; 								//Ris.ExMainTable.TOUSITIME
	private String 		bakushaSuuStr				= ""; 								//Ris.ExMainTable.BAKUSYASUU
	private String 		gyoumuKbnStr				= ""; 								//Ris.ExMainTable.GYOUMU_KBN
	private String 		statusStr					= "0";								//Ris.ExMainTable.STATUS
	private String 		receiptFlgStr				= "OF";								//Ris.ExMainTable.RECEIPTFLAG
	private String 		yuusenFlgStr				= "0";								//Ris.ExMainTable.YUUSEN_FLG
	private String 		examSaveFlgStr				= "0";								//Ris.ExMainTable.EXAMSAVEFLAG
	private String 		enforceDocIDStr				= "";								//Ris.ExMainTable.ENFORCEDOC_ID
	private String 		enforceDocNameStr			= "";								//Ris.ExMainTable.ENFORCEDOC_NAME
	private Integer 	endCountInt					= 0;								//Ris.ExMainTable.ENDCOUNT
	private String 		douishoCheckStr				= "";								//Ris.ExMainTable.DOUISHO_CHECK_NAME
	private Integer 	douishoCheckFlgInt			= 0;								//Ris.ExMainTable.DOUISHO_CHECK_FLAG
	private String 		infectionCheckStr			= "";								//Ris.ExMainTable.INFECTION_CHECK_NAME
	private Integer 	infectionCheckFlgInt		= 0;								//Ris.ExMainTable.INFECTION_CHECK_FLAG
	private Timestamp	yobidasiDateTime			= Const.TIMESTAMP_MINVALUE;			//Ris.ExMainTable.YOBIDASI_DATE
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	// 2010.07.30 Add T.Ootsuka Start
	//private String		kensaGisiID2Str				= ""; 								//Ris.ExMainTable.KENSA_GISI_ID2
	//private String		kensaGisiName2Str			= ""; 								//Ris.ExMainTable.KENSA_GISI_NAME2
	//private String		kensaGisiID3Str				= ""; 								//Ris.ExMainTable.KENSA_GISI_ID3
	//private String		kensaGisiName3Str			= ""; 								//Ris.ExMainTable.KENSA_GISI_NAME3
	//private String		kensaGisiID4Str				= ""; 								//Ris.ExMainTable.KENSA_GISI_ID4
	//private String		kensaGisiName4Str			= ""; 								//Ris.ExMainTable.KENSA_GISI_NAME4
	//private String		kensaGisiID5Str				= ""; 								//Ris.ExMainTable.KENSA_GISI_ID5
	//private String		kensaGisiName5Str			= ""; 								//Ris.ExMainTable.KENSA_GISI_NAME5

	// 2011.07.26 Del NSK_T.Koudate End
	private String		enforceDocID2Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_ID2
	private String		enforceDocName2Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_NAME2
	private String		enforceDocID3Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_ID3
	private String		enforceDocName3Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_NAME3
	private String		enforceDocID4Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_ID4
	private String		enforceDocName4Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_NAME4
	private String		enforceDocID5Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_ID5
	private String		enforceDocName5Str			= ""; 								//Ris.ExMainTable.ENFORCEDOC_NAME5

	private String		enforceDocSectionID1Str		= ""; 								//Ris.ExMainTable.ENFORCEDOC_SECTIONID
	private String		enforceDocSectionID2Str		= ""; 								//Ris.ExMainTable.ENFORCEDOC_SECTIONID2
	private String		enforceDocSectionID3Str		= ""; 								//Ris.ExMainTable.ENFORCEDOC_SECTIONID3
	private String		enforceDocSectionID4Str		= ""; 								//Ris.ExMainTable.ENFORCEDOC_SECTIONID4
	private String		enforceDocSectionID5Str		= ""; 								//Ris.ExMainTable.ENFORCEDOC_SECTIONID5

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//private String		kangosiID2Str				= ""; 								//Ris.ExMainTable.KANGOSI_ID2
	//private String		kangosiName2Str				= ""; 								//Ris.ExMainTable.KANGOSI_NAME2
	//private String		kangosiID3Str				= ""; 								//Ris.ExMainTable.KANGOSI_ID3
	//private String		kangosiName3Str				= ""; 								//Ris.ExMainTable.KANGOSI_NAME3

	// 2011.07.26 Del NSK_T.Koudate End
	private String		jisisyaIDStr				= ""; 								//Ris.ExMainTable.JISISYA_ID
	private String		jisisyaNameStr				= ""; 								//Ris.ExMainTable.JISISYA_NAME
	// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
	private String		unknownDateFlgStr			= "0"; 								//Ris.ExMainTable.UNKNOWNDATE_FLG
	// 2011.07.11 Add NSK_H.Hashimoto End
	// 2010.07.30 Add T.Ootsuka End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	private String		dressingRoomIDStr			= ""; 								//Ris.ExMainTable.DRESSING_ROOM_ID
	// 2010.11.16 Add T.Nishikubo End
	// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	private String	medPersonID01Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID01
	private String	medPersonName01Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME01
	private String	medPersonSyokuinKbn01Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN01
	private String	medPersonID02Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID02
	private String	medPersonName02Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME02
	private String	medPersonSyokuinKbn02Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN02
	private String	medPersonID03Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID03
	private String	medPersonName03Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME03
	private String	medPersonSyokuinKbn03Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN03
	private String	medPersonID04Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID04
	private String	medPersonName04Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME04
	private String	medPersonSyokuinKbn04Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN04
	private String	medPersonID05Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID05
	private String	medPersonName05Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME05
	private String	medPersonSyokuinKbn05Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN05
	private String	medPersonID06Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID06
	private String	medPersonName06Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME06
	private String	medPersonSyokuinKbn06Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN06
	private String	medPersonID07Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID07
	private String	medPersonName07Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME07
	private String	medPersonSyokuinKbn07Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN07
	private String	medPersonID08Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID08
	private String	medPersonName08Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME08
	private String	medPersonSyokuinKbn08Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN08
	private String	medPersonID09Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID09
	private String	medPersonName09Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME09
	private String	medPersonSyokuinKbn09Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN09
	private String	medPersonID10Str				= "";		// Ris.TeamInfoTable.MED_PERSON_ID10
	private String	medPersonName10Str				= "";		// Ris.TeamInfoTable.MED_PERSON_NAME10
	private String	medPersonSyokuinKbn10Str		= "";		// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN10

	// 2011.07.26 Add NSK_T.Koudate End
	//
	private ArrayList arrListExBui					= new ArrayList();					//ExBuiBeanのリスト
	//--> ExBuiTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト
	private ArrayList arrListExZoueizai				= new ArrayList();					//ExZoueizaiBeanのリスト
	//--> ExZoueizaiTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト
	private ArrayList arrListExInfuse				= new ArrayList();					//ExInfuseBeanのリスト
	//--> ExInfuseTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト
	private ArrayList arrListExFilm					= new ArrayList();					//ExFilmBeanのリスト
	//--> ExFilmTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト
	private ArrayList arrListExSatuei				= new ArrayList();					//ExSatueiBeanのリスト
	//--> ExSatueiTable.RIS_ID=ExMainTable.RIS_IDとなるレコート（複数）のリスト
	private PatientInfoBean resultPatientBean		= null;								//実績患者情報（実施済みの場合にのみ、Core側でSET）
	private ExtendExamInfoBean extendExamInfoBean	= null;								//ExtendExamInfo情報

	//判定用情報
	private boolean isExamStartDateTimeNullBool    = false;
	private boolean isUpdatePatientResultsInfoBool = false;

	// 照射または検査終了を示す 実績患者情報の更新で使用
	private boolean resultBool = false;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExecutionInfoBean()
	{
		//
	}
	//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExecutionInfoBean]");
		strBuild.append("RIS_ID="					+ risIDStr						+ ";");
		strBuild.append("RIS_ID=" 					+ risIDStr						+ ";");
		strBuild.append("KENSATYPE_ID=" 			+ kensaTypeIDStr				+ ";");
		strBuild.append("KENSA_DATE=" 				+ kensaDateStr					+ ";");
		strBuild.append("KENSASITU_ID=" 			+ kensaSituIDStr 				+ ";");
		strBuild.append("KENSAKIKI_ID=" 			+ kensaKikiIDStr 				+ ";");
		strBuild.append("KANJA_ID=" 				+ kanjaIDStr 					+ ";");
		strBuild.append("KENSA_DATE_AGE=" 			+ kensaDateAgeStr 				+ ";");
		strBuild.append("DENPYO_NYUGAIKBN="			+ denpyoNyugaiKbnStr			+ ";");
		strBuild.append("UKETUKE_TANTOU_ID="		+ uketukeTantouIDStr			+ ";");
		strBuild.append("UKETUKE_TANTOU_NAME="		+ uketukeTantouNameStr			+ ";");
		strBuild.append("RECEIPTDATE=" 				+ receiptDateTime				+ ";");
		strBuild.append("RECEIPTTERMINALID="		+ receiptTerminalIDStr			+ ";");
		strBuild.append("RECEIPTNUMBER=" 			+ receiptNumberStr				+ ";");
		// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
		//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
		//{
		//	strBuild.append("RECEIPT_INITIAL_CHAR=" + receiptInitialCharStr			+ ";");
		//}
		// 2011.11.15 Add NSK_H.Hashimoto End
		strBuild.append("KENSA_GISI_ID=" 			+ kensaGisiIDStr				+ ";");
		strBuild.append("KENSA_GISI_NAME=" 			+ kensaGisiNameStr				+ ";");
		strBuild.append("EXAMSTARTDATE="			+ examStartDateTime				+ ";");
		strBuild.append("EXAMSTARTDATE(FORMSHOW)="	+ examStartDateFormShowTime		+ ";");
		strBuild.append("EXAMENDDATE="				+ examEndDateTime				+ ";");
		strBuild.append("EXAMTERMINALID="			+ examTerminalIDStr				+ ";");
		strBuild.append("STARTNUMBER="				+ startNumberInt				+ ";");
		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//strBuild.append("KANGOSI_ID=" 				+ kangosiIDStr					+ ";");
		//strBuild.append("KANGOSI_NAME=" 			+ kangosiNameStr				+ ";");

		// 2011.07.26 Del NSK_T.Koudate End
		strBuild.append("KENSAI_ID=" 				+ kensaDoctorIDStr				+ ";");
		strBuild.append("KENSAI_NAME=" 				+ kensaDoctorNameStr			+ ";");
		strBuild.append("BIKOU=" 					+ bikouStr						+ ";");
		strBuild.append("RENRAKU_MEMO=" 			+ renrakuMemoStr				+ ";");
		strBuild.append("SIJI_ISI_ID=" 				+ sijiIsiIDStr					+ ";");
		strBuild.append("SIJI_ISI_NAME=" 			+ sijiIsiNameStr				+ ";");
		strBuild.append("SIJI_ISI_COMMENT=" 		+ sijiIsiCommentStr				+ ";");
		strBuild.append("TOUSITIME=" 				+ tousiTimeStr					+ ";");
		strBuild.append("BAKUSYASUU=" 				+ bakushaSuuStr					+ ";");
		strBuild.append("GYOUMU_KBN=" 				+ gyoumuKbnStr					+ ";");
		strBuild.append("STATUS=" 					+ statusStr						+ ";");
		strBuild.append("RECEIPTFLAG=" 				+ receiptFlgStr					+ ";");
		strBuild.append("YUUSEN_FLG=" 				+ yuusenFlgStr					+ ";");
		strBuild.append("EXAMSAVEFLAG=" 			+ examSaveFlgStr				+ ";");
		strBuild.append("ENFORCEDOC_ID="			+ enforceDocIDStr				+ ";");
		strBuild.append("ENFORCEDOC_NAME="			+ enforceDocNameStr				+ ";");
		strBuild.append("ENDCOUNT=" 				+ endCountInt					+ ";");
		strBuild.append("DOUISHO_CHECK_NAME=" 		+ douishoCheckStr				+ ";");
		strBuild.append("DOUISHO_CHECK_FLAG=" 		+ douishoCheckFlgInt			+ ";");
		strBuild.append("INFECTION_CHECK_NAME=" 	+ infectionCheckStr				+ ";");
		strBuild.append("INFECTION_CHECK_FLAG=" 	+ infectionCheckFlgInt			+ ";");
		strBuild.append("YOBIDASI_DATE=" 			+ yobidasiDateTime				+ ";");
		// 2010.07.30 Add T.Ootsuka Start
		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//strBuild.append("KENSA_GISI_ID2=" 			+ kensaGisiID2Str				+ ";");
		//strBuild.append("KENSA_GISI_NAME2=" 		+ kensaGisiName2Str				+ ";");
		//strBuild.append("KENSA_GISI_ID3=" 			+ kensaGisiID3Str				+ ";");
		//strBuild.append("KENSA_GISI_NAME3=" 		+ kensaGisiName3Str				+ ";");
		//strBuild.append("KENSA_GISI_ID4=" 			+ kensaGisiID4Str				+ ";");
		//strBuild.append("KENSA_GISI_NAME4=" 		+ kensaGisiName4Str				+ ";");
		//strBuild.append("KENSA_GISI_ID5=" 			+ kensaGisiID5Str				+ ";");
		//strBuild.append("KENSA_GISI_NAME5=" 		+ kensaGisiName5Str				+ ";");

		// 2011.07.26 Del NSK_T.Koudate End
		strBuild.append("ENFORCEDOC_ID2=" 			+ enforceDocID2Str				+ ";");
		strBuild.append("ENFORCEDOC_NAME2=" 		+ enforceDocName2Str			+ ";");
		strBuild.append("ENFORCEDOC_ID3=" 			+ enforceDocID3Str				+ ";");
		strBuild.append("ENFORCEDOC_NAME3=" 		+ enforceDocName3Str			+ ";");
		strBuild.append("ENFORCEDOC_ID4=" 			+ enforceDocID4Str				+ ";");
		strBuild.append("ENFORCEDOC_NAME4=" 		+ enforceDocName4Str			+ ";");
		strBuild.append("ENFORCEDOC_ID5=" 			+ enforceDocID5Str				+ ";");
		strBuild.append("ENFORCEDOC_NAME5=" 		+ enforceDocName5Str			+ ";");

		strBuild.append("ENFORCEDOC_SECTIONID =" 	+ enforceDocSectionID1Str		+ ";");
		strBuild.append("ENFORCEDOC_SECTIONID2=" 	+ enforceDocSectionID2Str		+ ";");
		strBuild.append("ENFORCEDOC_SECTIONID3=" 	+ enforceDocSectionID3Str		+ ";");
		strBuild.append("ENFORCEDOC_SECTIONID4=" 	+ enforceDocSectionID4Str		+ ";");
		strBuild.append("ENFORCEDOC_SECTIONID5=" 	+ enforceDocSectionID5Str		+ ";");

		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//strBuild.append("KANGOSI_ID2=" 				+ kangosiID2Str					+ ";");
		//strBuild.append("KANGOSI_NAME2=" 			+ kangosiName2Str				+ ";");
		//strBuild.append("KANGOSI_ID3=" 				+ kangosiID3Str					+ ";");
		//strBuild.append("KANGOSI_NAME3=" 			+ kangosiName3Str				+ ";");

		// 2011.07.26 Del NSK_T.Koudate End
		strBuild.append("JISISYA_ID=" 				+ jisisyaIDStr					+ ";");
		strBuild.append("JISISYA_NAME=" 			+ jisisyaNameStr				+ ";");
		// 2010.07.30 Add T.Ootsuka End			+ ";");
		// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
		strBuild.append("UNKNOWNDATE_FLG=" 			+ unknownDateFlgStr				+ ";");
		// 2011.07.11 Add NSK_H.Hashimoto End
		// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
		// 担当者
		strBuild.append("MED_PERSON_ID01="	+	medPersonID01Str	+	";");
		strBuild.append("MED_PERSON_NAME01="	+	medPersonName01Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN01="	+	medPersonSyokuinKbn01Str	+	";");
		strBuild.append("MED_PERSON_ID02="	+	medPersonID02Str	+	";");
		strBuild.append("MED_PERSON_NAME02="	+	medPersonName02Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN02="	+	medPersonSyokuinKbn02Str	+	";");
		strBuild.append("MED_PERSON_ID03="	+	medPersonID03Str	+	";");
		strBuild.append("MED_PERSON_NAME03="	+	medPersonName03Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN03="	+	medPersonSyokuinKbn03Str	+	";");
		strBuild.append("MED_PERSON_ID04="	+	medPersonID04Str	+	";");
		strBuild.append("MED_PERSON_NAME04="	+	medPersonName04Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN04="	+	medPersonSyokuinKbn04Str	+	";");
		strBuild.append("MED_PERSON_ID05="	+	medPersonID05Str	+	";");
		strBuild.append("MED_PERSON_NAME05="	+	medPersonName05Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN05="	+	medPersonSyokuinKbn05Str	+	";");
		strBuild.append("MED_PERSON_ID06="	+	medPersonID06Str	+	";");
		strBuild.append("MED_PERSON_NAME06="	+	medPersonName06Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN06="	+	medPersonSyokuinKbn06Str	+	";");
		strBuild.append("MED_PERSON_ID07="	+	medPersonID07Str	+	";");
		strBuild.append("MED_PERSON_NAME07="	+	medPersonName07Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN07="	+	medPersonSyokuinKbn07Str	+	";");
		strBuild.append("MED_PERSON_ID08="	+	medPersonID08Str	+	";");
		strBuild.append("MED_PERSON_NAME08="	+	medPersonName08Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN08="	+	medPersonSyokuinKbn08Str	+	";");
		strBuild.append("MED_PERSON_ID09="	+	medPersonID09Str	+	";");
		strBuild.append("MED_PERSON_NAME09="	+	medPersonName09Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN09="	+	medPersonSyokuinKbn09Str	+	";");
		strBuild.append("MED_PERSON_ID10="	+	medPersonID10Str	+	";");
		strBuild.append("MED_PERSON_NAME10="	+	medPersonName10Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN10="	+	medPersonSyokuinKbn10Str	+	";");

		// 2011.07.26 Add NSK_T.Koudate End
		for (int i=0; i<arrListExBui.size(); i++)
		{
			strBuild.append("ExBuiBean="		+ ((ExBuiBean)arrListExBui.get(i)).toString() + ";");
		}
		for (int i=0; i<arrListExZoueizai.size(); i++)
		{
			strBuild.append("ExZoueizaiBean="	+ ((ExZoueizaiBean)arrListExZoueizai.get(i)).toString() + ";");
		}
		for (int i=0; i<arrListExInfuse.size(); i++)
		{
			strBuild.append("ExInfuseBean="		+ ((ExInfuseBean)arrListExInfuse.get(i)).toString() + ";");
		}
		for (int i=0; i<arrListExFilm.size(); i++)
		{
			strBuild.append("ExFilmBean="		+ ((ExFilmBean)arrListExFilm.get(i)).toString() + ";");
		}
		for (int i=0; i<arrListExSatuei.size(); i++)
		{
			strBuild.append("ExSatueiBean="		+ ((ExSatueiBean)arrListExSatuei.get(i)).toString() + ";");
		}
		if( resultPatientBean != null )
		{
			strBuild.append(resultPatientBean.toString());
		}
		if (extendExamInfoBean != null)
		{
			strBuild.append(extendExamInfoBean.toString());
		}

		return strBuild.toString();
	}
//
//
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
	// kensaTypeIDStrのSET
	public void SetKensaTypeID( String kensaTypeID )
	{
		if( kensaTypeID != null )
		{
			this.kensaTypeIDStr = kensaTypeID;
		}
	}
	// kensaTypeIDStrのGET
	public String GetKensaTypeID()
	{
		return this.kensaTypeIDStr;
	}
//
	// kensaDateStrのSET
	public void SetKensaDate( String kensaDate )
	{
		if( kensaDate != null )
		{
			this.kensaDateStr = kensaDate;
		}
	}
	// kensaDateStrのGET
	public String GetKensaDate()
	{
		return this.kensaDateStr;
	}
	// kensaDateStrのGET
	public Integer GetKensaDateInt()
	{
		Integer retInt = 0;
		if (this.kensaDateStr.length() > 0)
		{
			retInt = Integer.parseInt(this.kensaDateStr);
		}
		return retInt;
	}
	// kensaDateStrのGET
	public Timestamp GetKensaDateValue()
	{
		Timestamp retDate = Const.TIMESTAMP_MINVALUE;

		try
		{
			if (this.kensaDateStr.toString().length() > 0 &&
				this.kensaDateStr.length() == 8)
			{
				String dateStr = this.kensaDateStr.substring(0, 4) + "-" +
								 this.kensaDateStr.substring(4, 4 + 2) + "-" +
								 this.kensaDateStr.substring(6, 6 + 2) + " 00:00:00";

				retDate = Timestamp.valueOf(dateStr);
			}
		}
		catch (Exception e)
		{
		}
		return retDate;
	}
//
	// kensaSituIDStrのSET
	public void SetKensaSituID( String kensaSituID )
	{
		if( kensaSituID != null )
		{
			this.kensaSituIDStr = kensaSituID;
		}
	}
	// kensaSituIDStrのGET
	public String GetKensaSituID()
	{
		return this.kensaSituIDStr;
	}
//
	// kensaKikiIDStrのSET
	public void SetKensaKikiID( String kensaKikiID )
	{
		if( kensaKikiID != null )
		{
			this.kensaKikiIDStr = kensaKikiID;
		}
	}
	// kensaKikiIDStrのGET
	public String GetKensaKikiID()
	{
		return this.kensaKikiIDStr;
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
	// kensaDateAgeStrのSET
	public void SetKensaDateAge(String kensaDateAge)
	{
		if( kensaDateAge != null && kensaDateAge.length() > 0)
		{
			this.kensaDateAgeStr = kensaDateAge;
		}
	}
	// kensaDateAgeStrのGET
	public String GetKensaDateAge()
	{
		return this.kensaDateAgeStr;
	}
//
	// denpyoNyugaiKbnStrのGET
	public String GetDenpyoNyugaiKbn()
	{
		return this.denpyoNyugaiKbnStr;
	}
	// denpyoNyugaiKbnStrのSET
	public void SetDenpyoNyugaiKbn(String denpyoNyugaiKbn)
	{
		if (denpyoNyugaiKbn != null)
		{
			this.denpyoNyugaiKbnStr = denpyoNyugaiKbn;
		}
	}
//
	// uketukeTantouIDStrのSET
	public void SetUketukeTantouID( String tantouID )
	{
		if( tantouID != null )
		{
			this.uketukeTantouIDStr = tantouID;
		}
	}
	// uketukeTantouIDStrのGET
	public String GetUketukeTantouID()
	{
		return this.uketukeTantouIDStr;
	}
//
	// uketukeTantouNameStrのSET
	public void SetUketukeTantouName( String tantouName )
	{
		if( tantouName != null )
		{
			this.uketukeTantouNameStr = tantouName;
		}
	}
	// uketukeTantouNameStrのGET
	public String GetUketukeTantouName()
	{
		return this.uketukeTantouNameStr;
	}
//
	// receiptTimestampのSET
	public void SetReceiptDateTime( Timestamp receipt )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(receipt) )
		{
			this.receiptDateTime = receipt;
		}
	}
	// receiptTimestampのGET
	public Timestamp GetReceiptDateTime()
	{
		return this.receiptDateTime;
	}
//
	// receiptTerminalIDStrのSET
	public void SetReceiptTerminalID( String terminalID )
	{
		if( terminalID != null )
		{
			this.receiptTerminalIDStr = terminalID;
		}
	}
	// receiptTerminalIDStrのGET
	public String GetReceiptTerminalID()
	{
		return this.receiptTerminalIDStr;
	}
//
	// receiptNumberStrのSET
	public void SetReceiptNumber(String receiptNumber)
	{
		if (receiptNumber != null)
		{
			this.receiptNumberStr = receiptNumber;
		}
	}
	// receiptNumberStrのGET
	public String GetReceiptNumber()
	{
		return this.receiptNumberStr;
	}
//
	// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
	// receiptInitialCharStrのSET
	public void SetReceiptInitialChar(String receiptInitialChar)
	{
		if (receiptInitialChar != null)
		{
			this.receiptInitialCharStr = receiptInitialChar;
		}
	}
	// receiptInitialCharStrのGET
	public String GetReceiptInitialChar()
	{
		return this.receiptInitialCharStr;
	}
	// 2011.11.15 Add NSK_H.Hashimoto End
//
	// kensaGisiIDStrのSET
	public void SetKensaGisiID( String kensaGisi )
	{
		if( kensaGisi != null )
		{
			this.kensaGisiIDStr = kensaGisi;
		}
	}
	// kensaGisiIDStrのGET
	public String GetKensaGisiID()
	{
		return this.kensaGisiIDStr;
	}
//
	// kensaGisiNameStrのSET
	public void SetKensaGisiName( String gisiName )
	{
		if( gisiName != null )
		{
			this.kensaGisiNameStr = gisiName;
		}
	}
	// kensaGisiNameStrのGET
	public String GetKensaGisiName()
	{
		return this.kensaGisiNameStr;
	}
//
	// examStartTimestampのSET
	public void SetExamStartDateTime( Timestamp start )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(start) )
		{
			this.examStartDateTime = start;
		}
	}
	// examStartTimestampのGET
	public Timestamp GetExamStartDateTime()
	{
		return this.examStartDateTime;
	}
//
    // examStartTimestamp(hh:mm)のGET
    public String GetExamStartDateTimeFormat()
    {
        if (examStartDateTime != null && !Const.TIMESTAMP_MINVALUE.equals(examStartDateTime))
        {
            //検査時刻を変換する
			return new SimpleDateFormat(CommonString.LIST_FORMAT_TIME_0).format(this.examStartDateTime);
        }
        else
        {
            return "";
        }
    }
//
	// examStartDateFormShowTimeのSET
	public void SetExamStartDateTimeFormShow(Timestamp examStartDateFormShow)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(examStartDateFormShow) )
		{
			this.examStartDateFormShowTime = examStartDateFormShow;
		}
	}
//
	// examStartDateFormShowTimeのGET
	public Timestamp GetExamStartDateTimeFormShow()
	{
		return this.examStartDateFormShowTime;
	}
//
	// examEndTimestampのSET
	public void SetExamEndDateTime( Timestamp end )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(end) )
		{
			this.examEndDateTime = end;
		}
	}
	// examEndTimestampのGET
	public Timestamp GetExamEndDateTime()
	{
		return this.examEndDateTime;
	}
//
    // examEndTimestamp(hh:mm)のGET
    public String GetExamEndDateTimeFormat()
    {
        if (examEndDateTime != null && !Const.TIMESTAMP_MINVALUE.equals(examEndDateTime) )
        {
            //検査時刻を変換する
			return new SimpleDateFormat(CommonString.LIST_FORMAT_TIME_0).format(this.examEndDateTime);
        }
        else
        {
            return "";
        }
    }
//
	// examTerminalIDStrのSET
	public void SetExamTerminalID( String terminalID )
	{
		if( terminalID != null )
		{
			this.examTerminalIDStr = terminalID;
		}
	}
	// examTerminalIDStrのGET
	public String GetExamTerminalID()
	{
		return this.examTerminalIDStr;
	}
	// examTerminalIDStrのGET
	public Integer GetExamTerminalIDInt()
	{
		Integer retInt = 0;

		if (this.examTerminalIDStr.length() > 0)
		{
			retInt = Integer.parseInt(this.examTerminalIDStr);
		}

		return retInt;
	}
//
	// startNumberIntのSET
	public void SetStartNumber( Integer startNumber )
	{
		this.startNumberInt = startNumber;
	}
	// startNumberIntのSET
	public void SetStartNumberStr(String startNumber)
	{
		try
		{
			if (startNumber.length() > 0)
			{
				this.startNumberInt = Integer.parseInt(startNumber);
			}
		}
		catch (Exception e)
		{
		}
	}
	// startNumberIntのGET
	public Integer GetStartNumber()
	{
		return this.startNumberInt;
	}
//
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//        // kangosiIDStrのSET
	//        public void SetKangosiID( String kangosiID )
	//        {
	//            if( kangosiID != null )
	//            {
	//                this.kangosiIDStr = kangosiID;
	//            }
	//        }
	//        // kangosiIDStrのGET
	//        public String GetKangosiID()
	//        {
	//            return this.kangosiIDStr;
	//        }
	////
	//        // kangosiNameStrのSET
	//        public void SetKangosiName( String kangosiName )
	//        {
	//            if( kangosiName != null )
	//            {
	//                this.kangosiNameStr = kangosiName;
	//            }
	//        }
	//        // kangosiNameStrのGET
	//        public String GetKangosiName()
	//        {
	//            return this.kangosiNameStr;
	//        }

	// 2011.07.26 Del NSK_T.Koudate End
	// kensaDoctorIDStrのSET
	public void SetKensaDoctorID( String doctorID )
	{
		if( doctorID != null )
		{
			this.kensaDoctorIDStr = doctorID;
		}
	}
	// kensaDoctorIDStrのGET
	public String GetKensaDoctorID()
	{
		return this.kensaDoctorIDStr;
	}
//
	// kensaDoctorNameStrのSET
	public void SetKensaDoctorName( String doctorName )
	{
		if( doctorName != null )
		{
			this.kensaDoctorNameStr = doctorName;
		}
	}
	// kensaDoctorNameStrGET
	public String GetKensaDoctorName()
	{
		return this.kensaDoctorNameStr;
	}
//
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
		return this.bikouStr;
	}
//
	// renrakuMemoStrのSET
	public void SetRenrakuMemo( String memo )
	{
		if( memo != null )
		{
			this.renrakuMemoStr = memo;
		}
	}
	// renrakuMemoStrGET
	public String GetRenrakuMemo()
	{
		return this.renrakuMemoStr;
	}
//
	// sijiIsiIDStrのSET
	public void SetSijiIsiID( String sijiIsiID )
	{
		if( sijiIsiID != null )
		{
			this.sijiIsiIDStr = sijiIsiID;
		}
	}
	// sijiIsiIDStrのGET
	public String GetSijiIsiID()
	{
		return this.sijiIsiIDStr;
	}
//
	// sijiIsiNameStrのSET
	public void SetSijiIsiName( String sijiIsiName )
	{
		if( sijiIsiName != null )
		{
			this.sijiIsiNameStr = sijiIsiName;
		}
	}
	// sijiIsiNameStrのGET
	public String GetSijiIsiName()
	{
		return this.sijiIsiNameStr;
	}
//
	// sijiIsiCommentStrのSET
	public void SetSijiIsiComment( String comment )
	{
		if( comment != null )
		{
			this.sijiIsiCommentStr = comment;
		}
	}
	// sijiIsiCommentStrのGET
	public String GetSijiIsiComment()
	{
		return this.sijiIsiCommentStr;
	}
//
	// tousiTimeStrのSET
	public void SetTousiTime( String tousiTime )
	{
		if( tousiTime != null )
		{
			this.tousiTimeStr = tousiTime;
		}
	}
	// tousiTimeStrのGET
	public String GetTousiTime()
	{
		return this.tousiTimeStr;
	}
//
	// bakushaSuuStrのSET
	public void SetBakushaSuu( String bakushaSuu )
	{
		if( bakushaSuu != null )
		{
			this.bakushaSuuStr = bakushaSuu;
		}
	}
	// bakushaSuuStrのGET
	public String GetBakushaSuu()
	{
		return this.bakushaSuuStr;
	}
//
	// gyoumuKbnStrのGET
	public String GetGyoumuKbn()
	{
		return this.gyoumuKbnStr;
	}
	// gyoumuKbnStrのSET
	public void SetGyoumuKbn(String gyoumuKbn)
	{
		if (gyoumuKbn != null)
		{
			this.gyoumuKbnStr = gyoumuKbn;
		}
	}
//
	// statusStrのSET
	public void SetStatus( String status )
	{
		if( status != null )
		{
			if (status.compareTo(CommonString.STATUS_UNREGISTERED) == 0
				|| status.compareTo(CommonString.STATUS_ISLATE) == 0
				|| status.compareTo(CommonString.STATUS_ISCALLING) == 0
				|| status.compareTo(CommonString.STATUS_ISREGISTERED) == 0
				|| status.compareTo(CommonString.STATUS_INOPERATION) == 0
				|| status.compareTo(CommonString.STATUS_REST) == 0
				|| status.compareTo(CommonString.STATUS_ISFINISHED) == 0
				|| status.compareTo(CommonString.STATUS_STOP) == 0
				|| status.compareTo(CommonString.STATUS_RECALLING) == 0
				|| status.compareTo(CommonString.STATUS_REREGISTERED) == 0
				|| status.compareTo(CommonString.STATUS_STOP) == 0)
				//|| status.compareTo(CommonString.STATUS_ORDERCANCEL) == 0
				//|| status.compareTo(CommonString.STATUS_HOLD) == 0
				//|| status.compareTo(CommonString.STATUS_WAIT) == 0
				//|| status.compareTo(CommonString.STATUS_FINISHED) == 0
				//|| status.compareTo(CommonString.STATUS_DALETE) == 0
				//|| status.compareTo(CommonString.STATUS_CANCEL) == 0)
			{
				this.statusStr = status;
			}
			else
			{
				this.statusStr = CommonString.STATUS_UNREGISTERED;
			}

			// 2011.03.18 Add Yk.Suzuki Start A0005
			if (Configuration.GetInstance().GetOrderDeleteFlg())
			{
				// 削除ステータスを許可する
				if (status.compareTo(CommonString.STATUS_DELETE) == 0)
				{
					this.statusStr = status;
				}
			}
			// 2011.03.18 Add Yk.Suzuki End
		}
		else
		{
			this.statusStr = CommonString.STATUS_UNREGISTERED;
		}
	}
	// statusStrのGET
	public String GetStatus()
	{
		return this.statusStr;
	}
	// statusStrのGET
	public Integer GetStatusInt()
	{
		Integer retInt = 0;

		if (this.statusStr.length() > 0)
		{
			retInt = Integer.parseInt(this.statusStr);
		}

		return retInt;
	}
//
	// receiptFlgStrのSET
	public void SetReceiptFlg( String flg )
	{
		if( flg != null )
		{
			if( flg.compareTo("OF") == 0 || flg.compareTo("ON") == 0 )
			{
				this.receiptFlgStr = flg;
			}
		}
	}
	// receiptFlgStrのGET
	public String GetReceiptFlg()
	{
		return this.receiptFlgStr;
	}
//
	// yuusenFlgStrのGET
	public String GetYuusenFlg()
	{
		return this.yuusenFlgStr;
	}
	// yuusenFlgStrのSET
	public void SetYuusenFlg(String yuusenFlg)
	{
		if (yuusenFlg != null)
		{
			this.yuusenFlgStr = yuusenFlg;
		}
	}
//
	// examSaveFlgStrのSET
	public void SetExamSaveFlg( String flg )
	{
		if( flg != null )
		{
			if( flg.compareTo("0") == 0 || flg.compareTo("1") == 0 )
			{
				this.examSaveFlgStr = flg;
			}
		}
	}
	// examSaveFlgStrのGET
	public String GetExamSaveFlg()
	{
		return this.examSaveFlgStr;
	}
//
	// enforceDocIDStrのSET
	public void SetEnforceDocID( String docID )
	{
		if( docID != null )
		{
			this.enforceDocIDStr = docID;
		}
	}
	// enforceDocIDStrのGET
	public String GetEnforceDocID()
	{
		return this.enforceDocIDStr;
	}
//
	// enforceDocNameStrのSET
	public void SetEnforceDocName( String docName )
	{
		if( docName != null )
		{
			this.enforceDocNameStr = docName;
		}
	}
	// enforceDocNameStrのGET
	public String GetEnforceDocName()
	{
		return this.enforceDocNameStr;
	}
//
	// endCountIntのSET
	public void SetEndCount( Integer endCount )
	{
		this.endCountInt = endCount;
	}
	// endCountIntのSET
	public void SetEndCountStr(String endCount)
	{
		try
		{
			if (endCount.length() > 0)
			{
				this.endCountInt = Integer.parseInt(endCount);
			}
		}
		catch (Exception e)
		{
		}
	}
	// endCountIntのGET
	public Integer GetEndCount()
	{
		return this.endCountInt;
	}
//
	// douishoCheckStrのSET
	public void SetDouishoCheck(String douishoCheck)
	{
		if (douishoCheck != null)
		{
			this.douishoCheckStr = douishoCheck;
		}
	}
	// douishoCheckStrのGET
	public String GetDouishoCheck()
	{
		return this.douishoCheckStr;
	}
//
	// douishoCheckFlgIntのSET
	public void SetDouishoCheckFlg( Integer douishoCheckFlg )
	{
		this.douishoCheckFlgInt = douishoCheckFlg;
	}
	// douishoCheckFlgIntのSET
	public void SetDouishoCheckFlgStr(String douishoCheckFlg)
	{
		try
		{
			if (douishoCheckFlg.length() > 0)
			{
				this.douishoCheckFlgInt = Integer.parseInt(douishoCheckFlg);
			}
		}
		catch (Exception e)
		{
		}
	}
	// douishoCheckFlgIntのGET
	public Integer GetDouishoCheckFlg()
	{
		return this.douishoCheckFlgInt;
	}
//
	// infectionCheckStrのSET
	public void SetInfectionCheck(String infectionCheck)
	{
		if (infectionCheck != null)
		{
			this.infectionCheckStr = infectionCheck;
		}
	}
	// infectionCheckStrのGET
	public String GetInfectionCheck()
	{
		return this.infectionCheckStr;
	}
//
	// infectionCheckFlgIntのSET
	public void SetInfectionCheckFlg( Integer infectionCheckFlg )
	{
		this.infectionCheckFlgInt = infectionCheckFlg;
	}
	// infectionCheckFlgIntのSET
	public void SetInfectionCheckFlgStr(String infectionCheckFlg)
	{
		try
		{
			if (infectionCheckFlg.length() > 0)
			{
				this.infectionCheckFlgInt = Integer.parseInt(infectionCheckFlg);
			}
		}
		catch (Exception e)
		{
		}
	}
	// infectionCheckFlgIntのGET
	public Integer GetInfectionCheckFlg()
	{
		return this.infectionCheckFlgInt;
	}
//
	// yobidasiTimestampのSET
	public void SetYobidasiDate(Timestamp yobidasiDate)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(yobidasiDate) )
		{
			this.yobidasiDateTime = yobidasiDate;
		}
	}
	// yobidasiTimestampのGET
	public Timestamp GetYobidasiDate()
	{
		return this.yobidasiDateTime;
	}
//
//
//
	// arrListOrderBuiの取得
	public ArrayList GetExBuiList()
	{
		return this.arrListExBui;
	}
	// arrListOrderBuiの初期化
	public void ReconstructExBuiList( ArrayList exBuiList )
	{
		if (exBuiList != null)
		{
			this.arrListExBui.clear();
			this.arrListExBui = exBuiList;
		}
	}
//
	// arrListZoueizaiの取得
	public ArrayList GetExZoueizaiList()
	{
		return this.arrListExZoueizai;
	}
	// arrListZoueizaiの初期化
	public void ReconstructExZoueizaiList(ArrayList zoueizaiList)
	{
		if( zoueizaiList != null )
		{
			this.arrListExZoueizai.clear();
			this.arrListExZoueizai = zoueizaiList;
		}
	}
//
	// arrListInfuseの取得
	public ArrayList GetExInfuseList()
	{
		return this.arrListExInfuse;
	}
	// arrListInfuseの初期化
	public void ReconstructExInfuseList(ArrayList listInfuse)
	{
		if (listInfuse != null)
		{
			this.arrListExInfuse.clear();
			this.arrListExInfuse = listInfuse;
		}
	}
//
	// arrListFilmの取得
	public ArrayList GetExFilmList()
	{
		return this.arrListExFilm;
	}
	// arrListFilmの初期化
	public void ReconstructExFilmList(ArrayList listFilm)
	{
		if (listFilm != null)
		{
			this.arrListExFilm.clear();
			this.arrListExFilm = listFilm;
		}
	}
//
	// arrListSatueiの取得
	public ArrayList GetExSatueiList()
	{
		return this.arrListExSatuei;
	}
	// arrListSatueiの初期化
	public void ReconstructExSatueiList(ArrayList listSatuei)
	{
		if (listSatuei != null)
		{
			this.arrListExSatuei.clear();
			this.arrListExSatuei = listSatuei;
		}
	}
//
	//
	public void SetResultPatientInfo( PatientInfoBean resultPatient )
	{
		if( resultPatient != null )
		{
			this.resultPatientBean = resultPatient;
		}
	}
	//
	public PatientInfoBean GetResultPatientInfo()
	{
		return this.resultPatientBean;
	}
//
	//
	public void SetResultBool( boolean result )
	{
		this.resultBool = result;
	}
	//
	public boolean GetResultBool()
	{
		return this.resultBool;
	}
//
	// extendExamInfoBeanのSET
	public void SetExtendExamInfoBean(ExtendExamInfoBean extendExamInfo)
	{
		this.extendExamInfoBean = extendExamInfo;
	}
	// extendExamInfoBeanのGET
	public ExtendExamInfoBean GetExtendExamInfoBean()
	{
		return this.extendExamInfoBean;
	}
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	//
	// isExamStartTimestampNullBoolのSET
	public void SetExamStartDateTimeNull(boolean flg)
	{
		this.isExamStartDateTimeNullBool = flg;
	}
	// isExamStartTimestampNullBoolのGET
	public boolean IsExamStartDateTimeNull()
	{
		return this.isExamStartDateTimeNullBool;
	}
	//
	// isUpdatePatientResultsInfoBoolのSET
	public void SetUpdatePatientResultsInfo(boolean flg)
	{
		this.isUpdatePatientResultsInfoBool = flg;
	}
	// isUpdatePatientResultsInfoBoolのGET
	public boolean IsUpdatePatientResultsInfo()
	{
		return this.isUpdatePatientResultsInfoBool;
	}

	// 2010.07.30 Add T.Ootsuka Start
	//
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	// KensaGisiID2StrのSET
	//        public void SetKensaGisiID2( String KensaGisiID2 )
	//        {
	//            if( KensaGisiID2 != null )
	//            {
	//                this.kensaGisiID2Str = KensaGisiID2;
	//            }
	//        }
	//        // KensaGisiID2StrのGET
	//        public String GetKensaGisiID2()
	//        {
	//            return this.kensaGisiID2Str;
	//        }
	////
	//        // KensaGisiName2StrのSET
	//        public void SetKensaGisiName2( String KensaGisiName2 )
	//        {
	//            if( KensaGisiName2 != null )
	//            {
	//                this.kensaGisiName2Str = KensaGisiName2;
	//            }
	//        }
	//        // KensaGisiName2StrのGET
	//        public String GetKensaGisiName2()
	//        {
	//            return this.kensaGisiName2Str;
	//        }
	////
	//        // KensaGisiID3StrのSET
	//        public void SetKensaGisiID3( String KensaGisiID3 )
	//        {
	//            if( KensaGisiID3 != null )
	//            {
	//                this.kensaGisiID3Str = KensaGisiID3;
	//            }
	//        }
	//        // KensaGisiID3StrのGET
	//        public String GetKensaGisiID3()
	//        {
	//            return this.kensaGisiID3Str;
	//        }
	////
	//        // KensaGisiName3StrのSET
	//        public void SetKensaGisiName3( String KensaGisiName3 )
	//        {
	//            if( KensaGisiName3 != null )
	//            {
	//                this.kensaGisiName3Str = KensaGisiName3;
	//            }
	//        }
	//        // KensaGisiName3StrのGET
	//        public String GetKensaGisiName3()
	//        {
	//            return this.kensaGisiName3Str;
	//        }
	////
	//        // KensaGisiID4StrのSET
	//        public void SetKensaGisiID4( String KensaGisiID4 )
	//        {
	//            if( KensaGisiID4 != null )
	//            {
	//                this.kensaGisiID4Str = KensaGisiID4;
	//            }
	//        }
	//        // KensaGisiID4StrのGET
	//        public String GetKensaGisiID4()
	//        {
	//            return this.kensaGisiID4Str;
	//        }
	////
	//        // KensaGisiName4StrのSET
	//        public void SetKensaGisiName4( String KensaGisiName4 )
	//        {
	//            if( KensaGisiName4 != null )
	//            {
	//                this.kensaGisiName4Str = KensaGisiName4;
	//            }
	//        }
	//        // KensaGisiName4StrのGET
	//        public String GetKensaGisiName4()
	//        {
	//            return this.kensaGisiName4Str;
	//        }
	////
	//        // KensaGisiID5StrのSET
	//        public void SetKensaGisiID5( String KensaGisiID5 )
	//        {
	//            if( KensaGisiID5 != null )
	//            {
	//                this.kensaGisiID5Str = KensaGisiID5;
	//            }
	//        }
	//        // KensaGisiID5StrのGET
	//        public String GetKensaGisiID5()
	//        {
	//            return this.kensaGisiID5Str;
	//        }
	////
	//        // KensaGisiName5StrのSET
	//        public void SetKensaGisiName5( String KensaGisiName5 )
	//        {
	//            if( KensaGisiName5 != null )
	//            {
	//                this.kensaGisiName5Str = KensaGisiName5;
	//            }
	//        }
	//        // KensaGisiName5StrのGET
	//        public String GetKensaGisiName5()
	//        {
	//            return this.kensaGisiName5Str;
	//        }

	// 2011.07.26 Del NSK_T.Koudate End
	//
	// enforceDocSectionID1StrのSET
	public void SetEnforceDocSectionID1(String enforceDocSectionID1)
	{
		if (enforceDocSectionID1 != null)
		{
			this.enforceDocSectionID1Str = enforceDocSectionID1;
		}
	}
	// enforceDocSectionID1StrのGET
	public String GetEnforceDocSectionID1()
	{
		return this.enforceDocSectionID1Str;
	}

//
	// enforceDocID2StrのSET
	public void SetEnforceDocID2( String enforceDocID2 )
	{
		if( enforceDocID2 != null )
		{
			this.enforceDocID2Str = enforceDocID2;
		}
	}
	// enforceDocID2StrのGET
	public String GetEnforceDocID2()
	{
		return this.enforceDocID2Str;
	}
//
	// enforceDocName2StrのSET
	public void SetEnforceDocName2( String enforceDocName2 )
	{
		if( enforceDocName2 != null )
		{
			this.enforceDocName2Str = enforceDocName2;
		}
	}
	// enforceDocName2StrのGET
	public String GetEnforceDocName2()
	{
		return this.enforceDocName2Str;
	}
	//
	// enforceDocSectionID2StrのSET
	public void SetEnforceDocSectionID2(String enforceDocSectionID2)
	{
		if (enforceDocSectionID2 != null)
		{
			this.enforceDocSectionID2Str = enforceDocSectionID2;
		}
	}
	// enforceDocSectionID2StrのGET
	public String GetEnforceDocSectionID2()
	{
		return this.enforceDocSectionID2Str;
	}
//
	// enforceDocID3StrのSET
	public void SetEnforceDocID3( String enforceDocID3 )
	{
		if( enforceDocID3 != null )
		{
			this.enforceDocID3Str = enforceDocID3;
		}
	}
	// enforceDocID3StrのGET
	public String GetEnforceDocID3()
	{
		return this.enforceDocID3Str;
	}
//
	// enforceDocName3StrのSET
	public void SetEnforceDocName3( String enforceDocName3 )
	{
		if( enforceDocName3 != null )
		{
			this.enforceDocName3Str = enforceDocName3;
		}
	}
	// enforceDocName3StrのGET
	public String GetEnforceDocName3()
	{
		return this.enforceDocName3Str;
	}
	//
	// enforceDocSectionID3StrのSET
	public void SetEnforceDocSectionID3(String enforceDocSectionID3)
	{
		if (enforceDocSectionID3 != null)
		{
			this.enforceDocSectionID3Str = enforceDocSectionID3;
		}
	}
	// enforceDocSectionID3StrのGET
	public String GetEnforceDocSectionID3()
	{
		return this.enforceDocSectionID3Str;
	}
//
	// enforceDocID4StrのSET
	public void SetEnforceDocID4( String enforceDocID4 )
	{
		if( enforceDocID4 != null )
		{
			this.enforceDocID4Str = enforceDocID4;
		}
	}
	// enforceDocID4StrのGET
	public String GetEnforceDocID4()
	{
		return this.enforceDocID4Str;
	}
//
	// enforceDocName4StrのSET
	public void SetEnforceDocName4( String enforceDocName4 )
	{
		if( enforceDocName4 != null )
		{
			this.enforceDocName4Str = enforceDocName4;
		}
	}
	// enforceDocName4StrのGET
	public String GetEnforceDocName4()
	{
		return this.enforceDocName4Str;
	}
	//
	// enforceDocSectionID4StrのSET
	public void SetEnforceDocSectionID4(String enforceDocSectionID4)
	{
		if (enforceDocSectionID4 != null)
		{
			this.enforceDocSectionID4Str = enforceDocSectionID4;
		}
	}
	// enforceDocSectionID4StrのGET
	public String GetEnforceDocSectionID4()
	{
		return this.enforceDocSectionID4Str;
	}
//
	// enforceDocID5StrのSET
	public void SetEnforceDocID5( String enforceDocID5 )
	{
		if( enforceDocID5 != null )
		{
			this.enforceDocID5Str = enforceDocID5;
		}
	}
	// enforceDocID5StrのGET
	public String GetEnforceDocID5()
	{
		return this.enforceDocID5Str;
	}
//
	// enforceDocName5StrのSET
	public void SetEnforceDocName5( String enforceDocName5 )
	{
		if( enforceDocName5 != null )
		{
			this.enforceDocName5Str = enforceDocName5;
		}
	}
	// enforceDocName5StrのGET
	public String GetEnforceDocName5()
	{
		return this.enforceDocName5Str;
	}
	//
	// enforceDocSectionID5StrのSET
	public void SetEnforceDocSectionID5(String enforceDocSectionID5)
	{
		if (enforceDocSectionID5 != null)
		{
			this.enforceDocSectionID5Str = enforceDocSectionID5;
		}
	}
	// enforceDocSectionID5StrのGET
	public String GetEnforceDocSectionID5()
	{
		return this.enforceDocSectionID5Str;
	}
//
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//        // kangosiID2StrのSET
	//        public void SetKangosiID2( String kangosiID2 )
	//        {
	//            if( kangosiID2 != null )
	//            {
	//                this.kangosiID2Str = kangosiID2;
	//            }
	//        }
	//        // kangosiID2StrのGET
	//        public String GetKangosiID2()
	//        {
	//            return this.kangosiID2Str;
	//        }
	////
	//        // kangosiName2StrのSET
	//        public void SetKangosiName2( String kangosiName2 )
	//        {
	//            if( kangosiName2 != null )
	//            {
	//                this.kangosiName2Str = kangosiName2;
	//            }
	//        }
	//        // kangosiName2StrのGET
	//        public String GetKangosiName2()
	//        {
	//            return this.kangosiName2Str;
	//        }
	////
	//        // kangosiID3StrのSET
	//        public void SetKangosiID3( String kangosiID3 )
	//        {
	//            if( kangosiID3 != null )
	//            {
	//                this.kangosiID3Str = kangosiID3;
	//            }
	//        }
	//        // kangosiID3StrのGET
	//        public String GetKangosiID3()
	//        {
	//            return this.kangosiID3Str;
	//        }
	////
	//        // kangosiName3StrのSET
	//        public void SetKangosiName3( String kangosiName3 )
	//        {
	//            if( kangosiName3 != null )
	//            {
	//                this.kangosiName3Str = kangosiName3;
	//            }
	//        }
	//        // kangosiName3StrのGET
	//        public String GetKangosiName3()
	//        {
	//            return this.kangosiName3Str;
	//        }

	// 2011.07.26 Del NSK_T.Koudate End
	//
	// JisisyaIDStrのSET
	public void SetJisisyaID( String JisisyaID )
	{
		if( JisisyaID != null )
		{
			this.jisisyaIDStr = JisisyaID;
		}
	}
	// JisisyaIDStrのGET
	public String GetJisisyaID()
	{
		return this.jisisyaIDStr;
	}
//
	// JisisyaNameStrのSET
	public void SetJisisyaName( String JisisyaName )
	{
		if( JisisyaName != null )
		{
			this.jisisyaNameStr = JisisyaName;
		}
	}
	// JisisyaNameStrのGET
	public String GetJisisyaName()
	{
		return this.jisisyaNameStr;
	}
	// 2010.07.30 Add T.Ootsuka End

	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	//
	// kakuhoRoomIDStrのSET
	public void SetDressingRoomId(String dressingRoomIDStr)
	{
		if (dressingRoomIDStr != null)
		{
			this.dressingRoomIDStr = dressingRoomIDStr;
		}
	}
	// kakuhoRoomIDStrのGET
	public String GetDressingRoomId()
	{
		return this.dressingRoomIDStr;
	}
	// 2010.11.16 Add T.Nishikubo End

	// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
	// unknownDateFlgStrのSET
	public void SetUnknownDateFlg(String strUnknownDateFlg)
	{
		if (strUnknownDateFlg != null)
		{
			this.unknownDateFlgStr = strUnknownDateFlg;
		}
	}
	// unknownDateFlgStrのGET
	public String GetUnknownDateFlg()
	{
		return this.unknownDateFlgStr;
	}
	// 2011.07.11 Add NSK_H.Hashimoto End

	// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	// medPersonID01StrのSET
	public void SetMedPersonID01(String medPersonID01)
	{
		if (medPersonID01 != null)
		{
			this.medPersonID01Str = medPersonID01;
		}
	}
	// medPersonID01のGET
	public String GetMedPersonID01()
	{
		return this.medPersonID01Str;
	}
	// medPersonName01StrのSET
	public void SetMedPersonName01(String medPersonName01)
	{
		if (medPersonName01 != null)
		{
			this.medPersonName01Str = medPersonName01;
		}
	}
	// medPersonName01のGET
	public String GetMedPersonName01()
	{
		return this.medPersonName01Str;
	}
	// medPersonSyokuinKbn01StrのSET
	public void SetMedPersonSyokuinKbn01(String medPersonSyokuinKbn01)
	{
		if (medPersonSyokuinKbn01 != null)
		{
			this.medPersonSyokuinKbn01Str = medPersonSyokuinKbn01;
		}
	}
	// medPersonSyokuinKbn01のGET
	public String GetMedPersonSyokuinKbn01()
	{
		return this.medPersonSyokuinKbn01Str;
	}
	// medPersonID02StrのSET
	public void SetMedPersonID02(String medPersonID02)
	{
		if (medPersonID02 != null)
		{
			this.medPersonID02Str = medPersonID02;
		}
	}
	// medPersonID02のGET
	public String GetMedPersonID02()
	{
		return this.medPersonID02Str;
	}
	// medPersonName02StrのSET
	public void SetMedPersonName02(String medPersonName02)
	{
		if (medPersonName02 != null)
		{
			this.medPersonName02Str = medPersonName02;
		}
	}
	// medPersonName02のGET
	public String GetMedPersonName02()
	{
		return this.medPersonName02Str;
	}
	// medPersonSyokuinKbn02StrのSET
	public void SetMedPersonSyokuinKbn02(String medPersonSyokuinKbn02)
	{
		if (medPersonSyokuinKbn02 != null)
		{
			this.medPersonSyokuinKbn02Str = medPersonSyokuinKbn02;
		}
	}
	// medPersonSyokuinKbn02のGET
	public String GetMedPersonSyokuinKbn02()
	{
		return this.medPersonSyokuinKbn02Str;
	}
	// medPersonID03StrのSET
	public void SetMedPersonID03(String medPersonID03)
	{
		if (medPersonID03 != null)
		{
			this.medPersonID03Str = medPersonID03;
		}
	}
	// medPersonID03のGET
	public String GetMedPersonID03()
	{
		return this.medPersonID03Str;
	}
	// medPersonName03StrのSET
	public void SetMedPersonName03(String medPersonName03)
	{
		if (medPersonName03 != null)
		{
			this.medPersonName03Str = medPersonName03;
		}
	}
	// medPersonName03のGET
	public String GetMedPersonName03()
	{
		return this.medPersonName03Str;
	}
	// medPersonSyokuinKbn03StrのSET
	public void SetMedPersonSyokuinKbn03(String medPersonSyokuinKbn03)
	{
		if (medPersonSyokuinKbn03 != null)
		{
			this.medPersonSyokuinKbn03Str = medPersonSyokuinKbn03;
		}
	}
	// medPersonSyokuinKbn03のGET
	public String GetMedPersonSyokuinKbn03()
	{
		return this.medPersonSyokuinKbn03Str;
	}
	// medPersonID04StrのSET
	public void SetMedPersonID04(String medPersonID04)
	{
		if (medPersonID04 != null)
		{
			this.medPersonID04Str = medPersonID04;
		}
	}
	// medPersonID04のGET
	public String GetMedPersonID04()
	{
		return this.medPersonID04Str;
	}
	// medPersonName04StrのSET
	public void SetMedPersonName04(String medPersonName04)
	{
		if (medPersonName04 != null)
		{
			this.medPersonName04Str = medPersonName04;
		}
	}
	// medPersonName04のGET
	public String GetMedPersonName04()
	{
		return this.medPersonName04Str;
	}
	// medPersonSyokuinKbn04StrのSET
	public void SetMedPersonSyokuinKbn04(String medPersonSyokuinKbn04)
	{
		if (medPersonSyokuinKbn04 != null)
		{
			this.medPersonSyokuinKbn04Str = medPersonSyokuinKbn04;
		}
	}
	// medPersonSyokuinKbn04のGET
	public String GetMedPersonSyokuinKbn04()
	{
		return this.medPersonSyokuinKbn04Str;
	}
	// medPersonID05StrのSET
	public void SetMedPersonID05(String medPersonID05)
	{
		if (medPersonID05 != null)
		{
			this.medPersonID05Str = medPersonID05;
		}
	}
	// medPersonID05のGET
	public String GetMedPersonID05()
	{
		return this.medPersonID05Str;
	}
	// medPersonName05StrのSET
	public void SetMedPersonName05(String medPersonName05)
	{
		if (medPersonName05 != null)
		{
			this.medPersonName05Str = medPersonName05;
		}
	}
	// medPersonName05のGET
	public String GetMedPersonName05()
	{
		return this.medPersonName05Str;
	}
	// medPersonSyokuinKbn05StrのSET
	public void SetMedPersonSyokuinKbn05(String medPersonSyokuinKbn05)
	{
		if (medPersonSyokuinKbn05 != null)
		{
			this.medPersonSyokuinKbn05Str = medPersonSyokuinKbn05;
		}
	}
	// medPersonSyokuinKbn05のGET
	public String GetMedPersonSyokuinKbn05()
	{
		return this.medPersonSyokuinKbn05Str;
	}
	// medPersonID06StrのSET
	public void SetMedPersonID06(String medPersonID06)
	{
		if (medPersonID06 != null)
		{
			this.medPersonID06Str = medPersonID06;
		}
	}
	// medPersonID06のGET
	public String GetMedPersonID06()
	{
		return this.medPersonID06Str;
	}
	// medPersonName06StrのSET
	public void SetMedPersonName06(String medPersonName06)
	{
		if (medPersonName06 != null)
		{
			this.medPersonName06Str = medPersonName06;
		}
	}
	// medPersonName06のGET
	public String GetMedPersonName06()
	{
		return this.medPersonName06Str;
	}
	// medPersonSyokuinKbn06StrのSET
	public void SetMedPersonSyokuinKbn06(String medPersonSyokuinKbn06)
	{
		if (medPersonSyokuinKbn06 != null)
		{
			this.medPersonSyokuinKbn06Str = medPersonSyokuinKbn06;
		}
	}
	// medPersonSyokuinKbn06のGET
	public String GetMedPersonSyokuinKbn06()
	{
		return this.medPersonSyokuinKbn06Str;
	}
	// medPersonID07StrのSET
	public void SetMedPersonID07(String medPersonID07)
	{
		if (medPersonID07 != null)
		{
			this.medPersonID07Str = medPersonID07;
		}
	}
	// medPersonID07のGET
	public String GetMedPersonID07()
	{
		return this.medPersonID07Str;
	}
	// medPersonName07StrのSET
	public void SetMedPersonName07(String medPersonName07)
	{
		if (medPersonName07 != null)
		{
			this.medPersonName07Str = medPersonName07;
		}
	}
	// medPersonName07のGET
	public String GetMedPersonName07()
	{
		return this.medPersonName07Str;
	}
	// medPersonSyokuinKbn07StrのSET
	public void SetMedPersonSyokuinKbn07(String medPersonSyokuinKbn07)
	{
		if (medPersonSyokuinKbn07 != null)
		{
			this.medPersonSyokuinKbn07Str = medPersonSyokuinKbn07;
		}
	}
	// medPersonSyokuinKbn07のGET
	public String GetMedPersonSyokuinKbn07()
	{
		return this.medPersonSyokuinKbn07Str;
	}
	// medPersonID08StrのSET
	public void SetMedPersonID08(String medPersonID08)
	{
		if (medPersonID08 != null)
		{
			this.medPersonID08Str = medPersonID08;
		}
	}
	// medPersonID08のGET
	public String GetMedPersonID08()
	{
		return this.medPersonID08Str;
	}
	// medPersonName08StrのSET
	public void SetMedPersonName08(String medPersonName08)
	{
		if (medPersonName08 != null)
		{
			this.medPersonName08Str = medPersonName08;
		}
	}
	// medPersonName08のGET
	public String GetMedPersonName08()
	{
		return this.medPersonName08Str;
	}
	// medPersonSyokuinKbn08StrのSET
	public void SetMedPersonSyokuinKbn08(String medPersonSyokuinKbn08)
	{
		if (medPersonSyokuinKbn08 != null)
		{
			this.medPersonSyokuinKbn08Str = medPersonSyokuinKbn08;
		}
	}
	// medPersonSyokuinKbn08のGET
	public String GetMedPersonSyokuinKbn08()
	{
		return this.medPersonSyokuinKbn08Str;
	}
	// medPersonID09StrのSET
	public void SetMedPersonID09(String medPersonID09)
	{
		if (medPersonID09 != null)
		{
			this.medPersonID09Str = medPersonID09;
		}
	}
	// medPersonID09のGET
	public String GetMedPersonID09()
	{
		return this.medPersonID09Str;
	}
	// medPersonName09StrのSET
	public void SetMedPersonName09(String medPersonName09)
	{
		if (medPersonName09 != null)
		{
			this.medPersonName09Str = medPersonName09;
		}
	}
	// medPersonName09のGET
	public String GetMedPersonName09()
	{
		return this.medPersonName09Str;
	}
	// medPersonSyokuinKbn09StrのSET
	public void SetMedPersonSyokuinKbn09(String medPersonSyokuinKbn09)
	{
		if (medPersonSyokuinKbn09 != null)
		{
			this.medPersonSyokuinKbn09Str = medPersonSyokuinKbn09;
		}
	}
	// medPersonSyokuinKbn09のGET
	public String GetMedPersonSyokuinKbn09()
	{
		return this.medPersonSyokuinKbn09Str;
	}
	// medPersonID10StrのSET
	public void SetMedPersonID10(String medPersonID10)
	{
		if (medPersonID10 != null)
		{
			this.medPersonID10Str = medPersonID10;
		}
	}
	// medPersonID10のGET
	public String GetMedPersonID10()
	{
		return this.medPersonID10Str;
	}
	// medPersonName10StrのSET
	public void SetMedPersonName10(String medPersonName10)
	{
		if (medPersonName10 != null)
		{
			this.medPersonName10Str = medPersonName10;
		}
	}
	// medPersonName10のGET
	public String GetMedPersonName10()
	{
		return this.medPersonName10Str;
	}
	// medPersonSyokuinKbn10StrのSET
	public void SetMedPersonSyokuinKbn10(String medPersonSyokuinKbn10)
	{
		if (medPersonSyokuinKbn10 != null)
		{
			this.medPersonSyokuinKbn10Str = medPersonSyokuinKbn10;
		}
	}
	// medPersonSyokuinKbn10のGET
	public String GetMedPersonSyokuinKbn10()
	{
		return this.medPersonSyokuinKbn10Str;
	}

	// 2011.07.26 Add NSK_T.Koudate End
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	///// <summary>
	///// チーム情報を担当者へ設定する
	///// </summary>
	///// <param name="teamInfoBean">チーム情報</param>
	//public void SetTeamKensaGisi(TeamInfoBean teamInfoBean)
	//{
	//    if (teamInfoBean != null)
	//    {
	//        SetKensaGisiID(teamInfoBean.GetKensaGisiID1());
	//        SetKensaGisiName(teamInfoBean.GetKensaGisiName1());
	//        SetKensaGisiID2(teamInfoBean.GetKensaGisiID2());
	//        SetKensaGisiName2(teamInfoBean.GetKensaGisiName2());
	//        SetKensaGisiID3(teamInfoBean.GetKensaGisiID3());
	//        SetKensaGisiName3(teamInfoBean.GetKensaGisiName3());
	//        SetKensaGisiID4(teamInfoBean.GetKensaGisiID4());
	//        SetKensaGisiName4(teamInfoBean.GetKensaGisiName4());
	//        SetKensaGisiID5(teamInfoBean.GetKensaGisiID5());
	//        SetKensaGisiName5(teamInfoBean.GetKensaGisiName5());
	//    }
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	/// <summary>
	/// チーム情報を担当者へ設定する
	/// </summary>
	/// <param name="teamInfoBean">チーム情報</param>
	public void SetTeamMedPerson(TeamInfoBean teamInfoBean)
	{
		if (teamInfoBean != null)
		{
			SetMedPersonID01(teamInfoBean.GetMedPersonID01());
			SetMedPersonName01(teamInfoBean.GetMedPersonName01());
			SetMedPersonSyokuinKbn01(teamInfoBean.GetMedPersonSyokuinKbn01());
			SetMedPersonID02(teamInfoBean.GetMedPersonID02());
			SetMedPersonName02(teamInfoBean.GetMedPersonName02());
			SetMedPersonSyokuinKbn02(teamInfoBean.GetMedPersonSyokuinKbn02());
			SetMedPersonID03(teamInfoBean.GetMedPersonID03());
			SetMedPersonName03(teamInfoBean.GetMedPersonName03());
			SetMedPersonSyokuinKbn03(teamInfoBean.GetMedPersonSyokuinKbn03());
			SetMedPersonID04(teamInfoBean.GetMedPersonID04());
			SetMedPersonName04(teamInfoBean.GetMedPersonName04());
			SetMedPersonSyokuinKbn04(teamInfoBean.GetMedPersonSyokuinKbn04());
			SetMedPersonID05(teamInfoBean.GetMedPersonID05());
			SetMedPersonName05(teamInfoBean.GetMedPersonName05());
			SetMedPersonSyokuinKbn05(teamInfoBean.GetMedPersonSyokuinKbn05());
			SetMedPersonID06(teamInfoBean.GetMedPersonID06());
			SetMedPersonName06(teamInfoBean.GetMedPersonName06());
			SetMedPersonSyokuinKbn06(teamInfoBean.GetMedPersonSyokuinKbn06());
			SetMedPersonID07(teamInfoBean.GetMedPersonID07());
			SetMedPersonName07(teamInfoBean.GetMedPersonName07());
			SetMedPersonSyokuinKbn07(teamInfoBean.GetMedPersonSyokuinKbn07());
			SetMedPersonID08(teamInfoBean.GetMedPersonID08());
			SetMedPersonName08(teamInfoBean.GetMedPersonName08());
			SetMedPersonSyokuinKbn08(teamInfoBean.GetMedPersonSyokuinKbn08());
			SetMedPersonID09(teamInfoBean.GetMedPersonID09());
			SetMedPersonName09(teamInfoBean.GetMedPersonName09());
			SetMedPersonSyokuinKbn09(teamInfoBean.GetMedPersonSyokuinKbn09());
			SetMedPersonID10(teamInfoBean.GetMedPersonID10());
			SetMedPersonName10(teamInfoBean.GetMedPersonName10());
			SetMedPersonSyokuinKbn10(teamInfoBean.GetMedPersonSyokuinKbn10());
		}
	}

	// 2011.07.26 Add NSK_T.Koudate End

	/// <summary>
	/// チーム情報を施行医へ設定する
	/// </summary>
	/// <param name="teamInfoBean">チーム情報</param>
	public void SetTeamEnforceDoc(TeamInfoBean teamInfoBean)
	{
		if (teamInfoBean != null)
		{
			SetEnforceDocID(teamInfoBean.GetEnforcedocID1());
			SetEnforceDocName(teamInfoBean.GetEnforcedocName1());
			SetEnforceDocSectionID1(teamInfoBean.GetEnforcedocSecID1());
			SetEnforceDocID2(teamInfoBean.GetEnforcedocID2());
			SetEnforceDocName2(teamInfoBean.GetEnforcedocName2());
			SetEnforceDocSectionID2(teamInfoBean.GetEnforcedocSecID2());
			SetEnforceDocID3(teamInfoBean.GetEnforcedocID3());
			SetEnforceDocName3(teamInfoBean.GetEnforcedocName3());
			SetEnforceDocSectionID3(teamInfoBean.GetEnforcedocSecID3());
			SetEnforceDocID4(teamInfoBean.GetEnforcedocID4());
			SetEnforceDocName4(teamInfoBean.GetEnforcedocName4());
			SetEnforceDocSectionID4(teamInfoBean.GetEnforcedocSecID4());
			SetEnforceDocID5(teamInfoBean.GetEnforcedocID5());
			SetEnforceDocName5(teamInfoBean.GetEnforcedocName5());
			SetEnforceDocSectionID5(teamInfoBean.GetEnforcedocSecID5());
		}
	}

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	///// <summary>
	///// チーム情報を看護師へ設定する
	///// </summary>
	///// <param name="teamInfoBean">チーム情報</param>
	//public void SetTeamKangosi(TeamInfoBean teamInfoBean)
	//{
	//    if (teamInfoBean != null)
	//    {
	//        SetKangosiID(teamInfoBean.GetKangosiID1());
	//        SetKangosiName(teamInfoBean.GetKangosiName1());
	//        SetKangosiID2(teamInfoBean.GetKangosiID2());
	//        SetKangosiName2(teamInfoBean.GetKangosiName2());
	//        SetKangosiID3(teamInfoBean.GetKangosiID3());
	//        SetKangosiName3(teamInfoBean.GetKangosiName3());
	//    }
	//}

	// 2011.07.26 Del NSK_T.Koudate End
	//2010.09.22 Add H.Orikasa End
}
