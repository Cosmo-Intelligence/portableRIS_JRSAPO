package ris.lib.mwm.util;

/// <summary>
/// MwmMasterUtil定義
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class MwmMasterUtil
{
	//RISスタータス変数
	public static final String risStatus0Str								= "0";	//未受
	public static final String risStatus1Str								= "1";	//遅刻
	public static final String risStatus2Str								= "2";	//呼出
	public static final String risStatus3Str								= "10";	//受済
	public static final String risStatus4Str								= "20";	//検中
	public static final String risStatus5Str								= "21";	//保留
	public static final String risStatus6Str								= "24";	//再呼
	public static final String risStatus7Str 								= "25";	//再受
	public static final String risStatus8Str 								= "90";	//検済
	public static final String risStatus9Str 								= "91";	//中止

	//MWM Type
	public static final String MWM_TYPE_CRS1								= "500";
	public static final String MWM_TYPE_CRS2							    = "501";
	public static final String MWM_TYPE_FCR1							    = "550";
	public static final String MWM_TYPE_KCR1							    = "600";
	public static final String MWM_TYPE_CTS1								= "0";
	public static final String MWM_TYPE_PMR1								= "1";
	public static final String MWM_TYPE_CTS2								= "2";

	// 性別
	public static final String IS_OTOKO_MSG									= "M";
	public static  final String IS_ONNA_MSG									= "F";
	public static final String OTHER_SEX_MSG								= "O";

	// 性別
	public static final String IS_OTOKO										= "M";
	public static final String IS_ONNA										= "F";
	public static final String OTHER_SEX									= "O";

	// 入外区分
	public static final String IS_GAIRAI_MSG								= "外来";
	public static final String IS_NYUUIN_MSG								= "入院";
	public static final String IS_KINKYU_MSG								= "緊急外来";

	// 入外区分
	public static final String IS_GAIRAI									= "1";
	public static final String IS_NYUUIN									= "2";
	public static final String IS_KINKYU									= "3";

	//MWM用設定フラグ
	public static final String CHILD_AGE_TYPE								= "0";
	public static final String BABY_AGE_TYPE								= "1";
	public static final String OTHER_AGE_TYPE								= "2";

	//検査機器
	public static final String RIS_KENSAKIKI_ID							= "KENSAKIKI_ID";
	public static final String RIS_KENSAKIKI_NAME						= "KENSAKIKI_NAME";
	public static final String RIS_KENSAKIKI_RYAKUNAME					= "KENSAKIKI_RYAKUNAME";

	//依頼医
	public static final String DOCTOR_ID								= "DOCTOR_ID";
	public static final String DOCTOR_NAME								= "DOCTOR_NAME";
	public static final String DOCTOR_ENGLISH_NAME						= "DOCTOR_ENGLISH_NAME";

	//依頼科
	public static final String SECTION_ID								= "SECTION_ID";
	public static final String SECTION_NAME								= "SECTION_NAME";
	public static final String SECTION_ENGLISHNAME						= "SECTION_ENGLISHNAME";

	//WorkListInfo
	public static final String RIS_MWM_TYPE								= "MWMTYPE";
	public static final String RIS_KIKI_TYPE							= "KIKITYPE";
	public static final String RIS_NAME_MODE							= "NAMEMODE";
	public static final String RIS_MWM_PARAM01							= "MWMPARAM01";
	public static final String RIS_MWM_PARAM02							= "MWMPARAM02";
	public static final String RIS_MWM_PARAM03							= "MWMPARAM03";
	public static final String RIS_MWM_PARAM04							= "MWMPARAM04";
	public static final String RIS_MWM_PARAM05							= "MWMPARAM05";
	public static final String RIS_MWM_PARAM06							= "MWMPARAM06";
	public static final String RIS_MWM_PARAM07							= "MWMPARAM07";
	public static final String RIS_MWM_PARAM08							= "MWMPARAM08";
	public static final String RIS_MWM_PARAM09							= "MWMPARAM09";
	public static final String RIS_MWM_PARAM10							= "MWMPARAM10";
	public static final String RIS_AE_TITLE_MWM							= "AE_TITLE_MWM";
	public static final String RIS_MPPS_TYPE							= "MPPSTYPE";
	public static final String RIS_AE_TITLE_PPS							= "AE_TITLE_PPS";
	public static final String RIS_MODALITY_TYPE						= "MODALITY_TYPE";

	//削除モード
	public static final String DELETE_MODE_ACCNO						= "0"; //オーダ単位で削除登録
	public static final String DELETE_MODE_AETITLE						= "1"; //AE単位で削除登録
    public static final String DELETE_MODE								= "9"; //オーダ単位で削除
	// 2010.07.30 Add K.Shinohara Start
    public static final String DELETE_MODE_NONE							= "2"; //削除しない
	// 2010.07.30 Add K.Shinohara End
}
