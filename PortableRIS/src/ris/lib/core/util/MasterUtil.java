package ris.lib.core.util;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

// 2010.07.30 Add T.Ootsuka Start

// 2010.07.30 Add T.Ootsuka End



/// <summary>
///
/// マスタテーブルカラム定義
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)				(Comment)
/// V1.00.00		: 2009.02.22	: 112478 / A.Kobayashi	: original
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo	: KANRO-R-5
/// V2.01.00		: 2011.08.05	: 999999 / K.Aizawa		: NML-CAT2-015 NML-CAT3-034
/// V2.01.01.13000	: 2011.11.22	: 999999 / NSK_M.Ochiai	: OMH-1-2
///
/// </summary>
public class MasterUtil
{
	public static Log logger = LogFactory.getLog(MasterUtil.class);

	// 診断用RIS マスタテーブル名

	public static String RIS_SYSTEMPARAM					= Configuration.GetInstance().GetRisDbUserStr() + "." + "SystemParam";
	public static String RIS_SYSTEMPARAM2					= Configuration.GetInstance().GetRisDbUserStr() + "." + "SystemParam2";
	public static String RIS_BUIITEMDEFINE					= Configuration.GetInstance().GetRisDbUserStr() + "." + "BuiItemDefine";
	public static String RIS_SATUEIITEMDEFINE				= Configuration.GetInstance().GetRisDbUserStr() + "." + "SatueiItemDefine";
	public static String RIS_PARTSITEMDEFINE				= Configuration.GetInstance().GetRisDbUserStr() + "." + "PartsItemDefine";
	public static String RIS_KENSATYPEMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "KensaTypeMaster";
	public static String RIS_BUISETMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "BuiSetMaster";
	public static String RIS_BUIBUNRUIMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "BuiBunruiMaster";
	public static String RIS_BUIMASTER						= Configuration.GetInstance().GetRisDbUserStr() + "." + "BuiMaster";
	public static String RIS_SAYUUMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "SayuuMaster";
	public static String RIS_HOUKOUMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "HoukouMaster";
	public static String RIS_KENSAHOUHOUMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "KensaHouhouMaster";
	public static String RIS_EXAMROOMMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "ExamRoomMaster";
	public static String RIS_KENSAKIKIMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "KensaKikiMaster";
	public static String RIS_BYOUTOUMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "ByoutouMaster";
	public static String RIS_BYOUSITUMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "ByousituMaster";
	public static String RIS_SECTIONMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "SectionMaster";
	public static String RIS_CODECONVERT					= Configuration.GetInstance().GetRisDbUserStr() + "." + "CodeConvert";
	public static String RIS_STATUSDEFINE					= Configuration.GetInstance().GetRisDbUserStr() + "." + "StatusDefine";
	public static String RIS_TERMINALINFO					= Configuration.GetInstance().GetRisDbUserStr() + "." + "TerminalInfo";
	public static String RIS_HOSPITALMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "HospitalMaster";
	public static String RIS_PRINTMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "PrintMaster";
	public static String RIS_DEFAULTPRINTDEFINE				= Configuration.GetInstance().GetRisDbUserStr() + "." + "DefaultPrintDefine";
	public static String RIS_PRINTDEFINE					= Configuration.GetInstance().GetRisDbUserStr() + "." + "PrintDefine";
	public static String RIS_PRINTFORMDEFINE				= Configuration.GetInstance().GetRisDbUserStr() + "." + "PrintFormDefine";
	public static String RIS_SUMMARYITEMMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "SummaryItemMaster";
	public static String RIS_PREDEFINEDCOMMENTMASTER		= Configuration.GetInstance().GetRisDbUserStr() + "." + "PreDefinedCommentMaster";
	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	public static String RIS_PREDEFINEDCOMMENTKBNMASTER		= Configuration.GetInstance().GetRisDbUserStr() + "." + "PredefinedCommentKbnMaster";
	// 2011.03.10 Add CIJ_R.Aoyama Merge End MY-1-10
	public static String RIS_SORTPATTERNINFO				= Configuration.GetInstance().GetRisDbUserStr() + "." + "SortPatternInfo";
	public static String RIS_SECTIONDOCTORMASTER			= Configuration.GetInstance().GetRisDbUserStr() + "." + "SectionDoctorMaster";
	public static String RIS_CALENDARMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "CalendarMaster";
	public static String RIS_DAYCLASSIFICATIONTABLE			= Configuration.GetInstance().GetRisDbUserStr() + "." + "DayClassificationTable";
	public static String RIS_TIMEZONETABLE					= Configuration.GetInstance().GetRisDbUserStr() + "." + "TimeZoneTable";
	public static String RIS_CRSATUEIMENUMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "CRSatueiMenuMaster";
	public static String RIS_CRSATUEIMENUDEFINE				= Configuration.GetInstance().GetRisDbUserStr() + "." + "CRSatueiMenuDefine";
	public static String RIS_FILMMASTER						= Configuration.GetInstance().GetRisDbUserStr() + "." + "FilmMaster";
	public static String RIS_PARTSBUNRUIMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "PartsBunruiMaster";
	public static String RIS_DETAILPARTSBUNRUIMASTER		= Configuration.GetInstance().GetRisDbUserStr() + "." + "DetailPartsBunruiMaster";
	public static String RIS_PARTSMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "PartsMaster";
	public static String RIS_PARTSUNITMASTER				= Configuration.GetInstance().GetRisDbUserStr() + "." + "PartsUnitMaster";
	public static String RIS_GROUPCOMPETENCETABLE			= Configuration.GetInstance().GetRisDbUserStr() + "." + "GroupCompetenceTable";
	public static String RIS_DEVISIONCONVERT				= Configuration.GetInstance().GetRisDbUserStr() + "." + "DevisionConvert";
	public static String RIS_EXTEND_COLORDEFINE				= Configuration.GetInstance().GetRisDbUserStr() + "." + "Extend_ColorDefine";
	public static String RIS_URLDEFINE						= Configuration.GetInstance().GetRisDbUserStr() + "." + "UrlDefine";
	public static String RIS_HISTORYTABITEMMASTER			= Configuration.GetInstance().GetRisDbUserStr() + "." + "HistoryTabItemMaster";
	public static String RIS_EXAMITEMDEFINE					= Configuration.GetInstance().GetRisDbUserStr() + "." + "ExamItemDefine";
	public static String RIS_COMBOLISTITEMS					= Configuration.GetInstance().GetRisDbUserStr() + "." + "ComboListItems";
	// 2010.07.30 Add T.Ootsuka Start
	// チーム登録ダイアログ使用
	public static String RIS_GROUPMASTER					= Configuration.GetInstance().GetRisDbUserStr() + "." + "GroupMaster";
	// 2010.07.30 Add T.Ootsuka End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	public static String RIS_EXAMTIMINGDEFINE				= Configuration.GetInstance().GetRisDbUserStr() + "." + "ExamTimingDefine";
	// 2011.11.22 Add NSK_M.Ochiai End



	// RIS カラム名

	//RIS 定義固定値
	public static String RIS_PATIENTCOMMENT_DEF				= "COMMON";

	public static String RIS_FLAGS							= "FLAGS";
	public static String RIS_SYSTEM							= "SYSTEM";
	public static String RIS_VALUE1							= "VALUE1";
	public static String RIS_VALUE2							= "VALUE2";
	public static String RIS_SUBID							= "SUBID";
	public static String RIS_RAD_ID							= "RAD_ID";

	public static String RIS_RIS_ID							= "RIS_ID";
	public static String RIS_KANJA_ID						= "KANJA_ID";
	public static String RIS_BUIBUNRUI_ID					= "BUIBUNRUI_ID";
	public static String RIS_BUIBUNRUI_NAME					= "BUIBUNRUI_NAME";
	public static String RIS_BUISET_ID						= "BUISET_ID";
	public static String RIS_BUISET_NAME					= "BUISET_NAME";
	public static String RIS_BUI_ID							= "BUI_ID";
	public static String RIS_BUI_NAME						= "BUI_NAME";
	public static String RIS_BUI_ENGLISHNAME				= "BUI_ENGLISHNAME";
	public static String RIS_BUI_RYAKUNAME					= "BUI_RYAKUNAME";
	public static String RIS_SAYUU_ID						= "SAYUU_ID";
	public static String RIS_SAYUU_NAME						= "SAYUU_NAME";
	public static String RIS_SAYUU_ENGLISHNAME				= "SAYUU_ENGLISHNAME";
	public static String RIS_SAYUU_RYAKUNAME				= "SAYUU_RYAKUNAME";
	public static String RIS_SAYUU_FLG						= "SAYUU_FLG";
	public static String RIS_HOUKOU_ID						= "HOUKOU_ID";
	public static String RIS_HOUKOU_NAME					= "HOUKOU_NAME";
	public static String RIS_HOUKOU_ENGLISHNAME				= "HOUKOU_ENGLISHNAME";
	public static String RIS_HOUKOU_RYAKUNAME				= "HOUKOU_RYAKUNAME";
	public static String RIS_DEF_HOUKOU_ID					= "DEF_HOUKOU_ID";
	public static String RIS_KENSAHOUHOU_ID					= "KENSAHOUHOU_ID";
	public static String RIS_KENSAHOUHOU_NAME				= "KENSAHOUHOU_NAME";
	public static String RIS_KENSAHOUHOU_ENGLISHNAME		= "KENSAHOUHOU_ENGLISHNAME";
	public static String RIS_KENSAHOUHOU_RYAKUNAME			= "KENSAHOUHOU_RYAKUNAME";
	public static String RIS_DEF_KENSAHOUHOU_ID				= "DEF_KENSAHOUHOU_ID";
	public static String RIS_KENSATYPEID					= "KENSATYPEID";
	public static String RIS_KENSATYPE_ID					= "KENSATYPE_ID";
	public static String RIS_KENSATYPE_NAME					= "KENSATYPE_NAME";
	public static String RIS_KENSATYPE_RYAKUNAME			= "KENSATYPE_RYAKUNAME";
	public static String RIS_KENSATYPE_FILTER_FLG			= "KENSATYPE_FILTER_FLG";
	public static String RIS_DETAILORDER_FLG				= "DETAILORDER_FLG";
	public static String RIS_SCREENFORM						= "SCREENFORM";
	public static String RIS_DEF_KAIKEI_FLG					= "DEF_KAIKEI_FLG";
	public static String RIS_ZOUEI_FLG						= "ZOUEI_FLG";
	public static String RIS_ZOUEICHECK_FLG					= "ZOUEICHECK_FLG";
	public static String RIS_SATUEIMENU_ID					= "SATUEIMENU_ID";
	public static String RIS_SATUEIMENU_NAME				= "SATUEIMENU_NAME";
	public static String RIS_SATUEIMENU_NAME_KANA			= "SATUEIMENU_NAME_KANA";
	public static String RIS_SATUEIMENU_NAME_ENGLISH		= "SATUEIMENU_NAME_ENGLISH";
	public static String RIS_SATUEI_CODE					= "SATUEI_CODE";
	public static String RIS_PRESETDATA						= "PRESETDATA";
	public static String RIS_CRPRESETDATA					= "CRPRESETDATA";
	public static String RIS_FILM_ID						= "FILM_ID";
	public static String RIS_FILM_NAME						= "FILM_NAME";
	public static String RIS_FILM_RYAKUNAME					= "FILM_RYAKUNAME";
	public static String RIS_PARTITION						= "PARTITION";
	public static String RIS_USED							= "USED";
	// 2024.07.24 Add Nishihara@COSMO Start 帝京大学病院環境対応
	public static String RIS_USE							= "USE";
	// 2024.07.24 Add Nishihara@COSMO End   帝京大学病院環境対応
	public static String RIS_MODALITY_FILM_CODE				= "MODALITY_FILM_CODE";
	public static String RIS_PICTUREFLAG01					= "PICTUREFLAG01";
	public static String RIS_PICTUREFLAG02					= "PICTUREFLAG02";
	public static String RIS_PICTUREFLAG03					= "PICTUREFLAG03";
	public static String RIS_PICTUREFLAG04					= "PICTUREFLAG04";
	public static String RIS_PICTUREFLAG05					= "PICTUREFLAG05";		// 2011.08.05 Add K.Aizawa@MERX NML-CAT3-034
	public static String RIS_EXAMROOM_ID					= "EXAMROOM_ID";
	public static String RIS_NO								= "NO";
	public static String RIS_VALUE							= "VALUE";
	public static String RIS_ID								= "ID";
	public static String RIS_NAME							= "NAME";
	public static String RIS_RNAME							= "RNAME";
	public static String RIS_EXAMROOM_NAME					= "EXAMROOM_NAME";
	public static String RIS_EXAMROOM_RYAKUNAME				= "EXAMROOM_RYAKUNAME";
	public static String RIS_KENSAKIKI_NO					= "KENSAKIKI_NO";
	public static String RIS_KENSAKIKI_ID					= "KENSAKIKI_ID";
	public static String RIS_KENSAKIKI_ID2					= "KENSAKIKI_ID2";		// 2010.09.17 Add Y.Shibata KANRO-R-1
	public static String RIS_KENSAKIKI_NAME					= "KENSAKIKI_NAME";
	public static String RIS_KENSAKIKI_RYAKUNAME			= "KENSAKIKI_RYAKUNAME";
	public static String RIS_KENSAKIKI_NAME2				= "KENSAKIKI_NAME2";	// 2010.09.17 Add Y.Shibata KANRO-R-1
	public static String RIS_AE_TITLE_MWM					= "AE_TITLE_MWM";
	public static String RIS_KIKITYPE						= "KIKITYPE";
	public static String RIS_ITEMID							= "ITEMID";
	public static String RIS_ITEMNAME						= "ITEMNAME";
	public static String RIS_ITEMVALUE						= "ITEMVALUE";
	public static String RIS_ITEMCONTENTS					= "ITEMCONTENTS";
	public static String RIS_VALUEPATH						= "VALUEPATH";
	public static String RIS_VALUEOPTIONS					= "VALUEOPTIONS";
	public static String RIS_VALUELABEL						= "VALUELABEL";
	public static String RIS_STAUSCODE						= "STATUSCODE";
	public static String RIS_LABEL							= "LABEL";
	public static String RIS_SHORTLABEL						= "SHORTLABEL";
	public static String RIS_COLORMODE						= "COLORMODE";
	public static String RIS_COLOR							= "COLOR";
	public static String RIS_COLORBK						= "COLORBK";
	public static String RIS_TERMINALID						= "TERMINALID";
	public static String RIS_TERMINALNAME					= "TERMINALNAME";
	public static String RIS_TERMINALADDRESS				= "TERMINALADDRESS";
	public static String RIS_CONTENTS						= "CONTENTS";
	public static String RIS_HOSPITAL_NAME					= "NAME";
	public static String RIS_USEFLAG						= "USEFLAG";
	public static String RIS_SHOWORDER						= "SHOWORDER";
	public static String RIS_PRINTID						= "PRINTID";
	public static String RIS_FORMFILENAME					= "FORMFILENAME";
	public static String RIS_AUTOPRINT1						= "AUTOPRINT1";
	public static String RIS_A1SCREEN1						= "A1SCREEN1";
	public static String RIS_A1SCREEN2						= "A1SCREEN2";
	public static String RIS_A1SCREEN3						= "A1SCREEN3";
	public static String RIS_A1SCREEN4						= "A1SCREEN4";
	public static String RIS_A1SCREEN5						= "A1SCREEN5";
	public static String RIS_AUTOPRINT2						= "AUTOPRINT2";
	public static String RIS_A2SCREEN1						= "A2SCREEN1";
	public static String RIS_A2SCREEN2						= "A2SCREEN2";
	public static String RIS_AUTOPRINT3						= "AUTOPRINT3";
	public static String RIS_A3SCREEN1						= "A3SCREEN1";
	public static String RIS_A3SCREEN2						= "A3SCREEN2";
	public static String RIS_DOPREVIEW						= "DOPREVIEW";
	public static String RIS_KBN1							= "KBN1";
	public static String RIS_KBN2							= "KBN2";
	public static String RIS_KBN3							= "KBN3";
	public static String RIS_DETAILKBN1						= "DETAILKBN1";
	public static String RIS_DETAILKBN2						= "DETAILKBN2";
	public static String RIS_DETAILKBN3						= "DETAILKBN3";
	public static String RIS_AUTOPRINTFLAG					= "AUTOPRINTFLAG";
	public static String RIS_REPORTOUTPUT					= "REPORTOUTPUT";
	public static String RIS_OUTPUTTYPE						= "OUTPUTTYPE";
	public static String RIS_OUTPUTTYPE_NAME				= "OUTPUTTYPE_NAME";
	public static String RIS_DEFAULTPRINTER					= "DEFAULTPRINTER";	//デフォルトプリンタ出力	// 2010.07.30 Add DD.Chinh
	public static String RIS_EXPLAIN						= "EXPLAIN";
	public static String RIS_DISPLAYMODE					= "DISPLAYMODE";
	public static String RIS_COMBOMODE						= "COMBOMODE";
	public static String RIS_DATATYPE						= "DATATYPE";
	public static String RIS_DISPLAYSIZE					= "DISPLAYSIZE";
	public static String RIS_ALIGNMENT						= "ALIGNMENT";
	public static String RIS_TITLEALIGNMENT					= "TITLEALIGNMENT";
	public static String RIS_DISPLAYFORMAT					= "DISPLAYFORMAT";
	public static String RIS_DISPLAYNAME					= "DISPLAYNAME";
	public static String RIS_BASEDISPLAYNAME				= "BASEDISPLAYNAME";
	public static String RIS_NAME_CHANGE_FLG				= "NAME_CHANGE_FLG";
	public static String RIS_BASENAME_CHANGE_FLG			= "BASENAME_CHANGE_FLG";
	public static String RIS_COMMENT_ID						= "COMMENT_ID";
	public static String RIS_COMMENT_NAME					= "COMMENT_NAME";
	public static String RIS_RIS_COMMENTKBN					= "RIS_COMMENTKBN";
	public static String RIS_PATTERNID						= "PATTERNID";
	public static String RIS_PATTERNNAME					= "PATTERNNAME";
	public static String RIS_PATTERNTYPE					= "PATTERNTYPE";
	public static String RIS_WINDOWAPPID					= "WINDOWAPPID";
	public static String RIS_FIRST_ITEM						= "FIRST_ITEM";
	public static String RIS_FIRST_TYPE						= "FIRST_TYPE";
	public static String RIS_SECOND_ITEM					= "SECOND_ITEM";
	public static String RIS_SECOND_TYPE					= "SECOND_TYPE";
	public static String RIS_THIRD_ITEM						= "THIRD_ITEM";
	public static String RIS_THIRD_TYPE						= "THIRD_TYPE";
	public static String RIS_FOURTH_ITEM					= "FOURTH_ITEM";
	public static String RIS_FOURTH_TYPE					= "FOURTH_TYPE";
	public static String RIS_FIFTH_ITEM						= "FIFTH_ITEM";
	public static String RIS_FIFTH_TYPE						= "FIFTH_TYPE";
	public static String RIS_EXPLANATION					= "EXPLANATION";
	public static String RIS_SECTION_ID						= "SECTION_ID";
	public static String RIS_SECTION_NAME					= "SECTION_NAME";
	public static String RIS_SECTION_ENGLISHNAME			= "SECTION_ENGLISHNAME";
	public static String RIS_SECTION_RYAKUNAME				= "SECTION_RYAKUNAME";
	public static String RIS_SECTION_TEL					= "SECTION_TEL";
	public static String RIS_BYOUTOU_ID						= "BYOUTOU_ID";
	public static String RIS_BYOUTOU_NAME					= "BYOUTOU_NAME";
	public static String RIS_BYOUTOU_RYAKUNAME				= "BYOUTOU_RYAKUNAME";
	public static String RIS_DENPYO_BYOUTOU_ID				= "DENPYO_BYOUTOU_ID";
	public static String RIS_DENPYO_BYOUTOU_NAME			= "DENPYO_BYOUTOU_NAME";
	public static String RIS_DENPYO_BYOUTOU_RYAKUNAME		= "DENPYO_BYOUTOU_RYAKUNAME";
	public static String RIS_BYOUSITU_ID					= "BYOUSITU_ID";
	public static String RIS_BYOUSITU_NAME					= "BYOUSITU_NAME";
	public static String RIS_BYOUSITU_RYAKUNAME				= "BYOUSITU_RYAKUNAME";
	public static String RIS_DENPYO_BYOSITU_ID				= "DENPYO_BYOSITU_ID";
	public static String RIS_DENPYO_BYOSITU_NAME			= "DENPYO_BYOSITU_NAME";
	public static String RIS_DENPYO_BYOSITU_RYAKUNAME		= "DENPYO_BYOSITU_RYAKUNAME";
	public static String RIS_BYOUTOU_TEL					= "BYOUTOU_TEL";
	public static String RIS_DOCTOR_ID						= "DOCTOR_ID";
	public static String RIS_DOCTOR_NAME					= "DOCTOR_NAME";
	public static String RIS_DOCTOR_ENGLISH_NAME			= "DOCTOR_ENGLISH_NAME";
	public static String RIS_DOCTOR_TEL						= "DOCTOR_TEL";
	public static String RIS_MODALITY						= "MODALITY";
	public static String RIS_MODALITY_TYPE					= "MODALITY_TYPE";
	public static String RIS_HIZUKE							= "HIZUKE";
	public static String RIS_BIKO							= "BIKO";
	public static String RIS_DATECLASSIFICATION				= "DATECLASSIFICATION";
	public static String RIS_ZONE1_TIME						= "ZONE1_TIME";
	public static String RIS_ZONE1_CODE						= "ZONE1_CODE";
	public static String RIS_ZONE2_TIME						= "ZONE2_TIME";
	public static String RIS_ZONE2_CODE						= "ZONE2_CODE";
	public static String RIS_ZONE3_TIME						= "ZONE3_TIME";
	public static String RIS_ZONE3_CODE						= "ZONE3_CODE";
	public static String RIS_ZONE4_TIME						= "ZONE4_TIME";
	public static String RIS_ZONE4_CODE						= "ZONE4_CODE";
	public static String RIS_ZONE5_TIME						= "ZONE5_TIME";
	public static String RIS_ZONE5_CODE						= "ZONE5_CODE";
	public static String RIS_ZONE6_TIME						= "ZONE6_TIME";
	public static String RIS_ZONE6_CODE						= "ZONE6_CODE";
	public static String RIS_ZONE7_TIME						= "ZONE7_TIME";
	public static String RIS_ZONE7_CODE						= "ZONE7_CODE";
	public static String RIS_ZONE8_TIME						= "ZONE8_TIME";
	public static String RIS_ZONE8_CODE						= "ZONE8_CODE";
	public static String RIS_ZONE9_TIME						= "ZONE9_TIME";
	public static String RIS_ZONE9_CODE						= "ZONE9_CODE";
	public static String RIS_ZONE10_TIME					= "ZONE10_TIME";
	public static String RIS_ZONE10_CODE					= "ZONE10_CODE";
	public static String RIS_DAYOFWEEK						= "DAYOFWEEK";
	public static String RIS_WEEK01							= "WEEK01";
	public static String RIS_WEEK02							= "WEEK02";
	public static String RIS_WEEK03							= "WEEK03";
	public static String RIS_WEEK04							= "WEEK04";
	public static String RIS_WEEK05							= "WEEK05";
	public static String RIS_WEEK06							= "WEEK06";
	public static String RIS_PARTS_BUNRUI_ID				= "PARTS_BUNRUI_ID";
	public static String RIS_PARTS_BUNRUI_NAME				= "PARTS_BUNRUI_NAME";
	public static String RIS_SUURYOU_IJI					= "SUURYOU_IJI";
	public static String RIS_UNIT_IJI						= "UNIT_IJI";
	public static String RIS_SUURYOU						= "SUURYOU";
	public static String RIS_UNIT							= "UNIT";
	public static String RIS_TYPE							= "TYPE";
	public static String RIS_BARCODEID						= "BARCODEID";
	public static String RIS_BARCODEDATA					= "BARCODEDATA";
	public static String RIS_PARTSBUNRUI_ID					= "PARTSBUNRUI_ID";
	public static String RIS_DETAILPARTSBUNRUI_ID			= "DETAILPARTSBUNRUI_ID";
	public static String RIS_DETAIL_PARTS_BUNRUI_ID			= "DETAIL_PARTS_BUNRUI_ID";
	public static String RIS_DETAIL_PARTS_BUNRUI_NAME		= "DETAIL_PARTS_BUNRUI_NAME";
	public static String RIS_MARK							= "MARK";
	public static String RIS_BUI_NO_NAME					= "BUI_NO_NAME";
	public static String RIS_SATUEISTATUS_NAME				= "SATUEISTATUS_NAME";
	public static String RIS_BUICOMMENT						= "BUICOMMENT";
	public static String RIS_SEQ							= "SEQ";
	public static String RIS_PRESET_NAME					= "PRESET_NAME";
	public static String RIS_BUICOMMENT2					= "BUICOMMENT2";
	public static String RIS_UNSEND_FLG						= "UNSEND_FLG";
	public static String RIS_HIS_ORIGINAL_FLG				= "HIS_ORIGINAL_FLG";
	public static String RIS_PRESET_ID						= "PRESET_ID";
	public static String RIS_BUIORDER_NO					= "BUIORDER_NO";
	public static String RIS_SATUEISTATUS_ID				= "SATUEISTATUS_ID";
	public static String RIS_SATUEISTATUS					= "SATUEISTATUS";
	public static String RIS_BUI_NO							= "BUI_NO";
	public static String RIS_MULTISELECT					= "MULTISELECT";
	public static String RIS_HIS_ORIGINAL_FLG_NAME			= "HIS_ORIGINAL_FLG_NAME";
	public static String RIS_RESHOT_FLG_NAME				= "RESHOT_FLG_NAME";
	public static String RIS_SATUEI_CODE_NAME				= "SATUEI_CODE_NAME";
	public static String RIS_LOSS							= "LOSS";
	public static String RIS_EXAMDATA						= "EXAMDATA";
	public static String RIS_EXAMDATA01						= "EXAMDATA01";
	public static String RIS_EXAMDATA02						= "EXAMDATA02";
	public static String RIS_EXAMDATA03						= "EXAMDATA03";
	public static String RIS_EXAMDATA04						= "EXAMDATA04";
	public static String RIS_EXAMDATA05						= "EXAMDATA05";
	public static String RIS_EXAMDATA06						= "EXAMDATA06";
	public static String RIS_EXAMDATA07						= "EXAMDATA07";
	public static String RIS_EXAMDATA08						= "EXAMDATA08";
	public static String RIS_EXAMDATA09						= "EXAMDATA09";
	public static String RIS_EXAMDATA10						= "EXAMDATA10";
	public static String RIS_EXAMDATA11						= "EXAMDATA11";
	public static String RIS_EXAMDATA12						= "EXAMDATA12";
	public static String RIS_EXAMDATA13						= "EXAMDATA13";
	public static String RIS_EXAMDATA14						= "EXAMDATA14";
	public static String RIS_EXAMDATA15						= "EXAMDATA15";
	public static String RIS_EXAMDATA16						= "EXAMDATA16";
	public static String RIS_EXAMDATA17						= "EXAMDATA17";
	public static String RIS_EXAMDATA18						= "EXAMDATA18";
	public static String RIS_EXAMDATA19						= "EXAMDATA19";
	public static String RIS_EXAMDATA20						= "EXAMDATA20";
	// 2011.02.03 Add K.Shinohara Start
	public static String RIS_SATUEIADDFLAG					= "SATUEIADDFLAG";
	// 2011.02.03 Add K.Shinohara End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	public static String RIS_EXAM_TIMING					= "EXAM_TIMING";
	// 2011.11.22 Add NSK_M.Ochiai End

	//MPPS　↓
	public static String RIS_KVP							= "KVP";
	public static String RIS_UA								= "UA";
	public static String RIS_MSEC							= "MSEC";
	public static String RIS_MA								= "MA";
	public static String RIS_SEC							= "SEC";
	public static String RIS_MAS							= "MAS";
	public static String RIS_MPPSINSTANCEUID				= "MPPSINSTANCEUID";
	public static String RIS_MPPSVMNO						= "MPPSVMNO";
	public static String RIS_MPPS_SATUEI_CODE				= "MPPS_SATUEI_CODE";
	public static String RIS_MPPS_AE_TITLE					= "MPPS_AE_TITLE";
	public static String RIS_XRAYTUBECURRENT_MA				= "XRAYTUBECURRENT_MA";
	public static String RIS_EXPOSURETIME_SEC				= "EXPOSURETIME_SEC";
	public static String RIS_EXPOSURETIME_MIN				= "EXPOSURETIME_MIN";
	public static String RIS_KV								= "KV";
	public static String RIS_EXPOSURENO						= "EXPOSURENO";
	public static String RIS_CTDI							= "CTDI";
	public static String RIS_DLP							= "DLP";
	public static String RIS_FLUOROSCOPY					= "FLUOROSCOPY";
	public static String RIS_IMAGEAREA						= "IMAGEAREA";
	public static String RIS_D_DISTANCE_MM					= "D_DISTANCE_MM";
	public static String RIS_D_DISTANCE_CM					= "D_DISTANCE_CM";
	public static String RIS_E_DISTANCE_MM					= "E_DISTANCE_MM";
	public static String RIS_ENTRANCEDOSE_DGY				= "ENTRANCEDOSE_DGY";
	public static String RIS_ENTRANCEDOSE_MGY				= "ENTRANCEDOSE_MGY";
	public static String RIS_EXPOSEDAREA					= "EXPOSEDAREA";
	public static String RIS_RADIATIONMODE					= "RADIATIONMODE";
	public static String RIS_FILTERTYPE						= "FILTERTYPE";
	public static String RIS_FILTERMATERIAL					= "FILTERMATERIAL";
	//MPPS　↑
	public static String RIS_SATUEI_CODE1					= "SATUEI_CODE1";
	public static String RIS_SATUEI_CODE2					= "SATUEI_CODE2";
	public static String RIS_SATUEIMENU_NAME_KANA2			= "SATUEIMENU_NAME_KANA2";
	public static String RIS_DBFLG							= "DBFLG";
	public static String RIS_SEQ_DISP						= "SEQ_DISP";
	public static String RIS_MODE							= "MODE";
	public static String RIS_SEQ_OLD						= "SEQ_OLD";
	public static String RIS_RESHOT_FLG						= "RESHOT_FLG";
	public static String RIS_CASSETTE_NO					= "CASSETTE_NO";
	public static String RIS_PORTABLE_STATUS				= "PORTABLE_STATUS";
	public static String RIS_MPPSTYPE						= "MPPSTYPE";
	public static String RIS_MPPS_INST_ID					= "MPPS_INST_ID";
	public static String RIS_MPPS_VM_NO						= "MPPS_VM_NO";
	public static String RIS_DELFLG							= "DELFLG";
	public static String RIS_KBN_NAME						= "KBN_NAME";
	public static String RIS_KBN_TYPE						= "KBN_TYPE";
	public static String RIS_KBN_ID							= "KBN_ID";
	public static String RIS_KBN_NAME2						= "KBN_NAME2";
	public static String RIS_NAME2							= "NAME2";
	public static String RIS_UPDATE_FLG						= "UPDATE_FLG";
	public static String RIS_PARTS_ID						= "PARTS_ID";
	public static String RIS_INFUSE_ID						= "INFUSE_ID";
	public static String RIS_INFUSE_NAME					= "INFUSE_NAME";
	public static String RIS_ZOUEIZAI_ID					= "ZOUEIZAI_ID";
	public static String RIS_ZOUEIZAI_NAME					= "ZOUEIZAI_NAME";
	public static String RIS_ZOUEIZAI_RYAKUNAME				= "ZOUEIZAI_RYAKUNAME";
	public static String RIS_ZOUEIZAIIJITANNI_ID			= "ZOUEIZAIIJITANNI_ID";
	public static String RIS_ZOUEIZAIIJITANNI_NAME			= "ZOUEIZAIIJITANNI_NAME";
	public static String RIS_ZOUEIZAITANNI_ID				= "ZOUEIZAITANNI_ID";
	public static String RIS_ZOUEIZAITANNI_NAME				= "ZOUEIZAITANNI_NAME";
	public static String RIS_ZOUEIZAI_FLAG					= "ZOUEIZAI_FLAG";
	public static String RIS_PARTSLISTS						= "PARTSLISTS";
	public static String RIS_ATTRIBUTE						= "ATTRIBUTE";
	public static String RIS_MWMTYPE						= "MWMTYPE";
	public static String RIS_MWMDELETE						= "MWMDELETE";
	public static String RIS_DOKUEI_FLG						= "DOKUEI_FLG";
	public static String RIS_SYOTISITU_FLG					= "SYOTISITU_FLG";
	public static String RIS_RI_ORDER_FLG					= "RI_ORDER_FLG";
	public static String RIS_PORTABLEFLAG					= "PORTABLEFLAG";
	public static String RIS_ISITATIAI_FLG					= "ISITATIAI_FLG";
	public static String RIS_COMPETENCEID					= "COMPETENCEID";
	public static String RIS_LUMPMWMFLAG					= "LUMPMWMFLAG";
	public static String RIS_RECEIPTMWMFLAG					= "RECEIPTMWMFLAG";
	// 2010.07.30 Add Y.Shibata Start
	public static String RIS_KENSAKIKI_MODE					= "KENSAKIKI_MODE";		//Ris.KensaKikiMaster.KENSAKIKI_MODE
	public static String RIS_REF_KENSAKIKI_ID_1				= "REF_KENSAKIKI_ID_1";	//Ris.KensaKikiMaster.REF_KENSAKIKI_ID_1
	public static String RIS_REF_KENSAKIKI_ID_2				= "REF_KENSAKIKI_ID_2";	//Ris.KensaKikiMaster.REF_KENSAKIKI_ID_2
	public static String RIS_REF_KENSAKIKI_ID_3				= "REF_KENSAKIKI_ID_3";	//Ris.KensaKikiMaster.REF_KENSAKIKI_ID_3
	public static String RIS_REF_KENSAKIKI_ID_4				= "REF_KENSAKIKI_ID_4";	//Ris.KensaKikiMaster.REF_KENSAKIKI_ID_4
	public static String RIS_REF_KENSAKIKI_ID_5				= "REF_KENSAKIKI_ID_5";	//Ris.KensaKikiMaster.REF_KENSAKIKI_ID_5
	// 2010.07.30 Add Y.Shibata End
	public static String RIS_KEYVALUE						= "KEYVALUE";
	public static String RIS_MWMPARAM						= "MWMPARAM";
	public static String RIS_OTHER_FIRST_FLG				= "OTHER_FIRST_FLG";
	public static String RIS_BUI_FILM_NO					= "BUI_FILM_NO";
	public static String RIS_ORDER_NO						= "ORDER_NO";
	public static String RIS_OUTPUT_BUI_FLG					= "OUTPUT_BUI_FLG";
	public static String RIS_SOP_UID						= "SOP_UID";
	public static String RIS_AETITLE						= "AETITLE";
	public static String RIS_STATUS							= "STATUS";
	public static String RIS_STARTTIME						= "STARTTIME";
	public static String RIS_ENDTIME						= "ENDTIME";
	public static String RIS_HIKIATE_FLG					= "HIKIATE_FLG";
	public static String RIS_DEFAULT						= "DEFAULT";
	public static String RIS_URLFLAG						= "URLFLAG";
	public static String RIS_URLADDRESS						= "URLADDRESS";
	public static String RIS_DEFAULTTABTYPE1				= "DEFAULTTABTYPE1";
	public static String RIS_DEFAULTTABTYPE2				= "DEFAULTTABTYPE2";
	public static String RIS_DEFAULTTABTYPE3				= "DEFAULTTABTYPE3";
	public static String RIS_HISTORYFLAG1					= "HISTORYFLAG1";
	public static String RIS_HISTORYFLAG2					= "HISTORYFLAG2";
	public static String RIS_HISTORYFLAG3					= "HISTORYFLAG3";
	public static String RIS_DEFAULTDAYCOUNT1				= "DEFAULTDAYCOUNT1";
	public static String RIS_DEFAULTDAYCOUNT2				= "DEFAULTDAYCOUNT2";
	public static String RIS_DEFAULTDAYCOUNT3				= "DEFAULTDAYCOUNT3";
	public static String RIS_DATAGROUPID					= "DATAGROUPID";
	public static String RIS_NECESSARYFLAG					= "NECESSARYFLAG";

	// 2010.07.30 Add T.Ootsuka Start
	public static String RIS_TANTO_SECTION_ID				= "TANTO_SECTION_ID";
	// 2010.07.30 Add T.Ootsuka End

	// 2010.07.30 Add T.Nishikubo Start
	public static String RIS_ZOUEIZAITANNI					= "ZOUEIZAITANNI";
	public static String RIS_ZOUEIZAIIJITANNI				= "ZOUEIZAIIJITANNI";
	// 2010.07.30 Add T.Nishikubo End

	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	public static String RIS_ZOUEI_EC_FLG					= "ZOUEI_EC_FLG";
	public static String RIS_SIDEEFFECT_FLG					= "SIDEEFFECT_FLG";

	public static String RIS_COMMENTKBN						= "COMMENTKBN";
	public static String RIS_COMMENTKBN_NAME				= "COMMENTKBN_NAME";
	public static String RIS_SIDEEFFECT_FLAG				= "SIDEEFFECT_FLAG";
	// 2011.03.10 Add CIJ_R.Aoyama Merge  End  MY-1-10

	// 2010.11.16 Add T.Nishikubo Start	KANRO-R-5
	public static String RIS_BACK_COLOR						= "BACK_COLOR";
	// 2010.11.16 Add T.Nishikubo End

	// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
	public static String RIS_RECEIPTNUMBERKEYVALUE			= "RECEIPTNUMBERKEYVALUE";
	// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

	// 2011.08.05 Add K.Aizawa@MERX Start NML-CAT2-015
	public static String RIS_STUDYINSTANCEUID_INCREMENT		= "STUDYINSTANCEUID_INCREMENT";	//Ris.KensaKikiMaster.STUDYINSTANCEUID_INCREMENT
	// 2011.08.05 Add K.Aizawa@MERX End



	/// <summary>
	/// コンストラクタ
	/// </summary>
    public MasterUtil()
	{
		//
	}

	// ロジック

	/// <summary>
	/// 検索対象の値（検索キー）から取得対象のカラムにある値を取得する
	/// </summary>
	/// <param name="targetDataTable">対象のテーブル</param>
	/// <param name="targetTableNameStr">取得対象のカラム</param>
	/// <param name="keyTableNameStr">検索対象のカラム</param>
	/// <param name="keyStr">検索対象の値（検索キー）</param>
	/// <returns>検索値</returns>
	public String FindData(DataTable targetDataTable, String targetColumnNameStr, String keyColumnNameStr, String keyStr)
	{
		// 2011.08.22 Mod H.Orikasa Start NML-CAT9-021
		String retStr = "";
		try
		{
			if (keyStr.length() > 0)
			{
				for (int i = 0; i < targetDataTable.getRowCount(); i++)
				{
					if (targetDataTable.getRows().get(i).get(keyColumnNameStr).toString().equals(keyStr))
					{
						retStr = targetDataTable.getRows().get(i).get(targetColumnNameStr).toString();
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
		// コメント
		//if (keyStr.length() > 0)
		//{
		//    for (int i = 0; i < targetDataTable.Rows.Count; i++)
		//    {
		//        if (targetDataTable.Rows[i][keyColumnNameStr].toString() == keyStr)
		//        {
		//            return targetDataTable.Rows[i][targetColumnNameStr].toString();
		//        }
		//    }
		//}
		//return "";

		// 2011.08.22 Mod H.Orikasa End
	}

	/// <summary>
	/// 検索対象の値（検索キー）から取得対象のDataRowを取得する
	/// </summary>
	/// <param name="targetDataTable">対象のテーブル</param>
	/// <param name="keyColumnNameStr">検索対象のカラム</param>
	/// <param name="keyStr">検索対象の値（検索キー）</param>
	/// <returns>検索値</returns>
	public DataRow FindDataRow(DataTable targetDataTable, String keyColumnNameStr, String keyStr)
	{
		// 2011.08.22 Mod H.Orikasa Start NML-CAT9-021
		DataRow retRow = null;
		try
		{
			if (keyStr.length() > 0)
			{
				for (int i = 0; i < targetDataTable.getRowCount(); i++)
				{
					if (keyStr.equals(targetDataTable.getRows().get(i).get(keyColumnNameStr).toString()))
					{
						retRow = targetDataTable.getRows().get(i);
						break;
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retRow;
		// コメント
		//if (keyStr.length() > 0)
		//{
		//    for (int i = 0; i < targetDataTable.Rows.Count; i++)
		//    {
		//        if (targetDataTable.Rows[i][keyColumnNameStr].toString() == keyStr)
		//        {
		//            return targetDataTable.Rows[i];
		//        }
		//    }
		//}
		//return null;

		// 2011.08.22 Mod H.Orikasa End
	}

	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// 検索対象の値（検索キー）から取得対象のDataRowを取得する
	/// </summary>
	/// <param name="targetDataTable">対象のテーブル</param>
	/// <param name="keyColumnNameStr">検索対象のカラム1</param>
	/// <param name="keyStr">検索対象の値（検索キー）1</param>
	/// <param name="keyColumnNameStr">検索対象のカラム2</param>
	/// <param name="keyStr">検索対象の値（検索キー）2</param>
	/// <param name="keyColumnNameStr">検索対象のカラム3</param>
	/// <param name="keyStr">検索対象の値（検索キー）3</param>
	/// <returns>検索値</returns>
	public ArrayList FindDataList(DataTable targetDataTable, String keyColumnName1Str, String key1Str,
															String keyColumnName2Str, String key2Str,
															String keyColumnName3Str, String key3Str)
	{
		// 2011.08.22 Mod H.Orikasa Start NML-CAT9-021
		ArrayList retList = null;
		try
		{
			ArrayList dataRowList = new ArrayList();
			dataRowList.clear();
			for (int i = 0; i < targetDataTable.getRowCount(); i++)
			{
				if ((StringUtils.isEmpty(key1Str) || (targetDataTable.getRows().get(i).get(keyColumnName1Str).toString().indexOf(key1Str) > -1)) &&
					(StringUtils.isEmpty(key2Str) || (targetDataTable.getRows().get(i).get(keyColumnName2Str).toString().indexOf(key2Str) > -1)) &&
					(StringUtils.isEmpty(key3Str) || (targetDataTable.getRows().get(i).get(keyColumnName3Str).toString().indexOf(key3Str) > -1)))
				{
					dataRowList.add(targetDataTable.getRows().get(i));
				}
			}
			if (dataRowList.size() > 0)
			{
				retList = dataRowList;
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retList;
		// コメント
		//ArrayList dataRowList = new ArrayList();
		//dataRowList.clear();
		//for (int i = 0; i < targetDataTable.Rows.Count; i++)
		//{
		//    if ((String.IsNullOrEmpty(key1Str) || (targetDataTable.Rows[i][keyColumnName1Str].toString().IndexOf(key1Str) > -1)) &&
		//        (String.IsNullOrEmpty(key2Str) || (targetDataTable.Rows[i][keyColumnName2Str].toString().IndexOf(key2Str) > -1)) &&
		//        (String.IsNullOrEmpty(key3Str) || (targetDataTable.Rows[i][keyColumnName3Str].toString().IndexOf(key3Str) > -1)))
		//    {
		//        dataRowList.Add(targetDataTable.Rows[i]);
		//    }
		//}
		//if (dataRowList.Count > 0)
		//{
		//    return dataRowList;
		//}
		//else
		//{
		//    return null;
		//}

		// 2011.08.22 Mod H.Orikasa End
	}
	// 2010.07.30 Add T.Ootsuka End

	// 2010.08.31 Add T.Ootsuka Start KUM-1.1
	/// <summary>
	/// 検索対象の値（検索キー）から取得対象のDataRowを取得する
	/// </summary>
	/// <param name="targetDataTable">対象のテーブル</param>
	/// <param name="keyColumnNameStr">検索対象のカラム</param>
	/// <param name="keyStr">検索対象の値（検索キー）</param>
	/// <returns>検索値リスト</returns>
	public ArrayList FindDataRows(DataTable targetDataTable, String keyColumnNameStr, String keyStr)
	{
		// 2011.08.22 Mod H.Orikasa Start NML-CAT9-021
		ArrayList retList = null;
		try
		{
			ArrayList dataRowList	= new ArrayList();
			dataRowList.clear();
			if (keyStr.length() > 0)
			{
				for (int i = 0; i < targetDataTable.getRowCount(); i++)
				{
					if (keyStr.equals(targetDataTable.getRows().get(i).get(keyColumnNameStr).toString()))
					{
						dataRowList.add(targetDataTable.getRows().get(i));
					}
				}
				if (dataRowList.size() > 0)
				{
					retList = dataRowList;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retList;
		// コメント
		//ArrayList retList	= new ArrayList();
		//retList.clear();
		//if (keyStr.length() > 0)
		//{
		//    for (int i = 0; i < targetDataTable.Rows.Count; i++)
		//    {
		//        if (targetDataTable.Rows[i][keyColumnNameStr].toString() == keyStr)
		//        {
		//            retList.Add(targetDataTable.Rows[i]);
		//        }
		//    }
		//    if (retList.Count == 0)
		//    {
		//        retList	= null;
		//    }
		//}
		//return retList;

		// 2011.08.22 Mod H.Orikasa End
	}
	// 2010.08.31 Add T.Ootsuka End


}
