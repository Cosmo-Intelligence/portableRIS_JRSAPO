package ris.lib.core.database;

import java.sql.Connection;
import java.text.SimpleDateFormat;

import ris.lib.core.Configuration;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.OrderSearchParameter;
import ris.portable.common.Const;
import ris.portable.util.DataTable;


/// <summary>
/// オーダメインビュークラス
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.02.26	: 112478 / A.Kobayashi		: original
/// V1.00.06		: 2009.07.09	: 112478 / A.Kobayashi		: 管理番号を一覧に表示
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo		: KANRO-R-3 KANRO-R-9
/// V2.04.001 		: 2011.01.24	: 999999 / DD.Chinh			: KANRO-R-19 KANRO-R-22
/// V2.01.00		: 2011.07.29    extends 999999 / NSK_T.Koudate	: NML-CAT2-004
/// V2.01.00		: 2011.07.29    extends 999999 / NSK_S.Imai		: NML-CAT2-029
/// V2.01.00		: 2011.08.22	: 999999 / T.Nishikubo		: NML-CAT1-000
/// V2.01.01.13000	: 2011.11.15    extends 999999 / NSK_H.Hashimoto	: OMH-1-05
/// V2.01.01.13000	: 2011.11.22  　: 999999 / NSK_M.Ochiai		: OMH-1-2
///
/// </summary>
public class RisSummaryView extends BaseTbl
{
	// アクセステーブル名
	public static final String VIEW_NAME		= "RISSUMMARYVIEW";
	private static final String TABLE_CAPTION	= "オーダ情報";

	// カラム定義
	// Viewに存在するカラムのみ追加する事
	public static final String RIS_ID_COLUMN						= "RIS_ID";
	public static final String KENSATYPE_ID_COLUMN					= "KENSATYPE_ID";
	public static final String KENSATYPE_NAME_COLUMN				= "KENSATYPE_NAME";
	public static final String KENSATYPE_RYAKUNAME_COLUMN			= "KENSATYPE_RYAKUNAME";
	public static final String KANJA_ID_COLUMN						= "KANJA_ID";
	public static final String KANJAID2_COLUMN						= "KANJAID2";
	public static final String BYOUSITU_ID_COLUMN					= "BYOUSITU_ID";
	public static final String BYOUSITU_COLUMN						= "BYOUSITU";
	public static final String BYOUSITU_RYAKUNAME_COLUMN			= "BYOUSITU_RYAKUNAME";
	public static final String BYOUTOU_ID_COLUMN					= "BYOUTOU_ID";
	public static final String BYOUTOU_COLUMN						= "BYOUTOU";
	public static final String BYOUTOU_RYAKUNAME_COLUMN			= "BYOUTOU_RYAKUNAME";
	public static final String KENSASITU_ID_COLUMN					= "KENSASITU_ID";
	public static final String YOTEIKENSASITU_ID_COLUMN			= "YOTEIKENSASITU_ID";
	public static final String YOTEIKENSASITU_NAME_COLUMN			= "YOTEIKENSASITU_NAME";
	public static final String YOTEIKENSASITU_RYAKUNAME_COLUMN		= "YOTEIKENSASITU_RYAKUNAME";
	public static final String JISSIKENSASITU_ID_COLUMN			= "JISSIKENSASITU_ID";
	public static final String JISSIKENSASITU_NAME_COLUMN			= "JISSIKENSASITU_NAME";
	public static final String JISSIKENSASITU_RYAKUNAME_COLUMN		= "JISSIKENSASITU_RYAKUNAME";
	public static final String EXAMROOM_NAME_COLUMN				= "EXAMROOM_NAME";
	public static final String EXAMROOM_RYAKUNAME_COLUMN			= "EXAMROOM_RYAKUNAME";
	public static final String KENSAKIKI_ID_COLUMN					= "KENSAKIKI_ID";
	public static final String YOYAKU_KENSAKIKI_ID_COLUMN			= "YOYAKU_KENSAKIKI_ID";
	public static final String YOTEIKENSAROOM_COLUMN				= "YOTEIKENSAROOM";
	public static final String YOTEIKENSAROOMRYAKU_COLUMN			= "YOTEIKENSAROOMRYAKU";
	public static final String JISSI_KENSAKIKI_ID_COLUMN			= "JISSI_KENSAKIKI_ID";
	public static final String KENSAROOM_COLUMN					= "KENSAROOM";
	public static final String KENSAROOMRYAKU_COLUMN				= "KENSAROOMRYAKU";
	public static final String MODALITY_TYPE_COLUMN				= "MODALITY_TYPE";
	public static final String IRAI_SECTION_COLUMN					= "IRAI_SECTION";
	public static final String IRAI_SECTIONRYAKU_COLUMN			= "IRAI_SECTIONRYAKU";
	public static final String KANJYA_SECTION_COLUMN				= "KANJYA_SECTION";
	public static final String KANJA_SECTIONRYAKU_COLUMN			= "KANJA_SECTIONRYAKU";
	public static final String SECTION_ID_COLUMN					= "SECTION_ID";
	public static final String IRAI_SECTION_ID_COLUMN				= "IRAI_SECTION_ID";
	public static final String IRAI_DOCTOR_NO_COLUMN				= "IRAI_DOCTOR_NO";
	public static final String IRAI_DOCTOR_NAME_COLUMN				= "IRAI_DOCTOR_NAME";
	public static final String KENSA_SIJI_COLUMN					= "KENSA_SIJI";
	public static final String DENPYO_INSATU_FLG_COLUMN			= "DENPYO_INSATU_FLG";
	public static final String GYOUMU_KBN_COLUMN					= "GYOUMU_KBN";
	public static final String KENSA_STARTTIME_COLUMN				= "KENSA_STARTTIME";
	public static final String RECEIPTDATE_COLUMN					= "RECEIPTDATE";
	public static final String KENSA_DATE_COLUMN					= "KENSA_DATE";
	public static final String KENSA_DATE2_COLUMN					= "KENSA_DATE2";
	public static final String KANJA_SYOKAI_FLG_COLUMN				= "KANJA_SYOKAI_FLG";
	public static final String DOUISHO_FLG_COLUMN					= "DOUISHO_FLG";
	public static final String KANASIMEI_COLUMN					= "KANASIMEI";
	public static final String KANJISIMEI_COLUMN					= "KANJISIMEI";
	public static final String ROMASIMEI_COLUMN					= "ROMASIMEI";
	public static final String ROMASIMEI2_COLUMN					= "ROMASIMEI2";
	public static final String KENSA_DATE_AGE_COLUMN				= "KENSA_DATE_AGE";
	public static final String SEX_COLUMN							= "SEX";
	public static final String TRANSPORTTYPE_COLUMN				= "TRANSPORTTYPE";
	public static final String BIRTHDAY_COLUMN						= "BIRTHDAY";
	public static final String RENRAKU_MEMO_COLUMN					= "RENRAKU_MEMO";
	public static final String RENRAKU_MEMO2_COLUMN				= "RENRAKU_MEMO2";
	public static final String SIJI_ISI_COMMENT_COLUMN				= "SIJI_ISI_COMMENT";
	public static final String SIJI_ISI_NAME_COLUMN				= "SIJI_ISI_NAME";
	public static final String INPUTDATE_COLUMN					= "INPUTDATE";
	public static final String INPUTTIME_COLUMN					= "INPUTTIME";
	public static final String KENSAI_NAME_COLUMN					= "KENSAI_NAME";
	public static final String UKETUKE_TANTOU_NAME_COLUMN			= "UKETUKE_TANTOU_NAME";
	public static final String SEISAN_FLG_COLUMN					= "SEISAN_FLG";
	public static final String SYOTISITU_FLG_COLUMN				= "SYOTISITU_FLG";
	public static final String PORTABLE_FLG_COLUMN					= "PORTABLE_FLG";
	public static final String YUUSEN_FLG_COLUMN					= "YUUSEN_FLG";
	public static final String DOKUEI_FLG_COLUMN					= "DOKUEI_FLG";
	public static final String KANJA_NYUGAIKBN_COLUMN				= "KANJA_NYUGAIKBN";
	public static final String DENPYO_NYUGAIKBN_COLUMN 			= "DENPYO_NYUGAIKBN";
	public static final String HIS_HAKKO_DATE_COLUMN				= "HIS_HAKKO_DATE";
	public static final String ISITATIAI_FLG_COLUMN				= "ISITATIAI_FLG";
	public static final String RI_ORDER_FLG_COLUMN					= "RI_ORDER_FLG";
	public static final String SIJI_ISI_ID_COLUMN					= "SIJI_ISI_ID";
	public static final String UKETUKE_TANTOU_ID_COLUMN			= "UKETUKE_TANTOU_ID";
	public static final String ORDERNO_COLUMN						= "ORDERNO";
	public static final String STATUS_COLUMN						= "STATUS";
	public static final String RECEIPTDATE2_COLUMN					= "RECEIPTDATE2";
	public static final String ACNO_COLUMN							= "ACNO";
	public static final String SIKYU_FLG_COLUMN					= "SIKYU_FLG";
	public static final String KENSA_GISI_NAME_COLUMN				= "KENSA_GISI_NAME";
	public static final String RECEIPTNUMBER_COLUMN				= "RECEIPTNUMBER";
	public static final String EXAMSTARTDATE_COLUMN				= "EXAMSTARTDATE";
	public static final String EXAMENDDATE_COLUMN					= "EXAMENDDATE";
	public static final String DENPYO_BYOUTOU_NAME_COLUMN			= "DENPYO_BYOUTOU_NAME";
	public static final String DENPYO_BYOUTOU_RYAKUNAME_COLUMN 	= "DENPYO_BYOUTOU_RYAKUNAME";
	public static final String SYSTEMKBN_COLUMN					= "SYSTEMKBN";
	public static final String IRAIDOCTOR_TEL_COLUMN				= "IRAIDOCTOR_TEL";
	public static final String IRAISECTION_TEL_COLUMN				= "IRAISECTION_TEL";
	public static final String BYOUTOU_TEL_COLUMN					= "BYOUTOU_TEL";
	public static final String REMARKS_COLUMN						= "REMARKS";
	public static final String ORDERCOMMENT_ID_COLUMN				= "ORDERCOMMENT_ID";
	public static final String KENZOUSTATUS_FLG_COLUMN 			= "KENZOUSTATUS_FLG";
	public static final String KENZOUKINKYUU_FLG_COLUMN			= "KENZOUKINKYUU_FLG";
	public static final String KENZOU_TANTOU_NAME_COLUMN			= "KENZOU_TANTOU_NAME";
	public static final String ORDER_DATE_COLUMN					= "ORDER_DATE";
	public static final String DOUISHO_CHECK_NAME_COLUMN			= "DOUISHO_CHECK_NAME";
	public static final String DOUISHO_CHECK_FLAG_COLUMN			= "DOUISHO_CHECK_FLAG";
	public static final String INFECTION_COLUMN					= "INFECTION";
	public static final String INFECTION_CHECK_NAME_COLUMN 		= "INFECTION_CHECK_NAME";
	public static final String INFECTION_CHECK_FLAG_COLUMN 		= "INFECTION_CHECK_FLAG";
	public static final String YOBIDASI_DATE_COLUMN				= "YOBIDASI_DATE";
	public static final String KENZOU_DATE_COLUMN					= "KENZOU_DATE";
	public static final String EXAMTERMINALID_COLUMN				= "EXAMTERMINALID";
	public static final String STUDYINSTANCEUID_COLUMN				= "STUDYINSTANCEUID";
	public static final String ORDER_KENSA_DATE_COLUMN				= "ORDER_KENSA_DATE";
	public static final String EX_KENSA_DATE_COLUMN				= "EX_KENSA_DATE";
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	public static final String DRESSING_ROOM_ID_COLUMN				= "DRESSING_ROOM_ID";
	// 2010.11.16 Add T.Nishikubo End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	public static final String PHARMA_CHECK_NAME_COLUMN			= "PHARMA_CHECK_NAME";
	public static final String PHARMA_CHECK_FLAG_COLUMN			= "PHARMA_CHECK_FLAG";
	// 2010.11.16 Add T.Nishikubo End

	// 2011.01.24 Add T.Nishikubo Start KANRO-R-22
	public static final String DENPYO_BYOUTOU_ID_COLUMN			= "DENPYO_BYOUTOU_ID";
	// 2011.01.24 Add T.Nishikubo End

	// 2011.07.29 Add NSK_T.Koudate Start NML-CAT2-004
	public static final String UNKNOWNDATE_FLG_COLUMN				= "UNKNOWNDATE_FLG";
	public static final String JISISYA_NAME_COLUMN					= "JISISYA_NAME";
	public static final String MED_PERSON_ID01_COLUMN				= "MED_PERSON_ID01";
	public static final String MED_PERSON_NAME01_COLUMN			= "MED_PERSON_NAME01";
	public static final String MED_PERSON_SYOKUINKBN01_COLUMN		= "MED_PERSON_SYOKUINKBN01";
	public static final String MED_PERSON_ID02_COLUMN				= "MED_PERSON_ID02";
	public static final String MED_PERSON_NAME02_COLUMN			= "MED_PERSON_NAME02";
	public static final String MED_PERSON_SYOKUINKBN02_COLUMN		= "MED_PERSON_SYOKUINKBN02";
	public static final String MED_PERSON_ID03_COLUMN				= "MED_PERSON_ID03";
	public static final String MED_PERSON_NAME03_COLUMN			= "MED_PERSON_NAME03";
	public static final String MED_PERSON_SYOKUINKBN03_COLUMN		= "MED_PERSON_SYOKUINKBN03";
	public static final String MED_PERSON_ID04_COLUMN				= "MED_PERSON_ID04";
	public static final String MED_PERSON_NAME04_COLUMN			= "MED_PERSON_NAME04";
	public static final String MED_PERSON_SYOKUINKBN04_COLUMN		= "MED_PERSON_SYOKUINKBN04";
	public static final String MED_PERSON_ID05_COLUMN				= "MED_PERSON_ID05";
	public static final String MED_PERSON_NAME05_COLUMN			= "MED_PERSON_NAME05";
	public static final String MED_PERSON_SYOKUINKBN05_COLUMN		= "MED_PERSON_SYOKUINKBN05";
	public static final String MED_PERSON_ID06_COLUMN				= "MED_PERSON_ID06";
	public static final String MED_PERSON_NAME06_COLUMN			= "MED_PERSON_NAME06";
	public static final String MED_PERSON_SYOKUINKBN06_COLUMN		= "MED_PERSON_SYOKUINKBN06";
	public static final String MED_PERSON_ID07_COLUMN				= "MED_PERSON_ID07";
	public static final String MED_PERSON_NAME07_COLUMN			= "MED_PERSON_NAME07";
	public static final String MED_PERSON_SYOKUINKBN07_COLUMN		= "MED_PERSON_SYOKUINKBN07";
	public static final String MED_PERSON_ID08_COLUMN				= "MED_PERSON_ID08";
	public static final String MED_PERSON_NAME08_COLUMN			= "MED_PERSON_NAME08";
	public static final String MED_PERSON_SYOKUINKBN08_COLUMN		= "MED_PERSON_SYOKUINKBN08";
	public static final String MED_PERSON_ID09_COLUMN				= "MED_PERSON_ID09";
	public static final String MED_PERSON_NAME09_COLUMN			= "MED_PERSON_NAME09";
	public static final String MED_PERSON_SYOKUINKBN09_COLUMN		= "MED_PERSON_SYOKUINKBN09";
	public static final String MED_PERSON_ID10_COLUMN				= "MED_PERSON_ID10";
	public static final String MED_PERSON_NAME10_COLUMN			= "MED_PERSON_NAME10";
	public static final String MED_PERSON_SYOKUINKBN10_COLUMN		= "MED_PERSON_SYOKUINKBN10";
	// 2011.07.29 Add NSK_T.Koudate End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	public static final String EXAM_TIMING_COLUMN					= "EXAM_TIMING";
	// 2011.11.22 Add NSK_M.Ochiai End

	//表示,処理用カラム
	public static final String NO_COLMUN							= "NO";
	public static final String SELECT_COLMUN						= "SELECT";
	public static final String TAKENSA_FLG_COLUMN					= "TAKENSA_FLG";
	public static final String KENSA_DATE_STRING_COLUMN			= "KENSA_DATE_STRING";
	public static final String KENSA_STARTTIME_STRING_COLUMN		= "KENSA_STARTTIME_STRING";
	public static final String KENSATYPE_COMMENT_COLUMN			= "KENSATYPE_COMMENT";
	public static final String KAKUHO_COLUMN						= "KAKUHO";
	public static final String SATUEI_COUNT_COLUMN					= "SATUEI_COUNT";
	public static final String IRAI_SECTION_RYAKU_NAME_COLUMN		= "IRAI_SECTION_RYAKU_NAME";
	public static final String TRANSPORTTYPE_TITLE_COLUMN			= "TRANSPORTTYPE_TITLE";
	public static final String BUI_NAME_COLUMN						= "BUI_NAME";
	public static final String HOUKOU_NAME_COLUMN					= "HOUKOU_NAME";
	public static final String KENSAHOUHOU_NAME_COLUMN				= "KENSAHOUHOU_NAME";
	public static final String BUICOMMENTE_COLUMN					= "BUICOMMENT";
	public static final String STATUS_NAME_COLUMN					= "STATUS_NAME";
	public static final String GYOUMU_KBN_NAME_COLUMN				= "GYOUMU_KBN_NAME";
	public static final String RECEIPTDATE2_STRING_COLUMN			= "RECEIPTDATE2_STRING";
	public static final String KANJA_SYOKAI_FLG_NAME_COLUMN		= "KANJA_SYOKAI_FLG_NAME";
	public static final String DOUISHO_FLG_NAME_COLUMN				= "DOUISHO_FLG_NAME";
	public static final String YUUSEN_FLG_NAME_COLUMN				= "YUUSEN_FLG_NAME";
	public static final String SEX_NAME_COLUMN						= "SEX_NAME";
	public static final String TAKENSA_FLG_NAME_COLUMN				= "TAKENSA_FLG_NAME";
	public static final String SIKYU_FLG_NAME_COLUMN				= "SIKYU_FLG_NAME";
	public static final String INPUTDATE_STRING_COLUMN 			= "INPUTDATE_STRING";
	public static final String INPUTTIME_STRING_COLUMN 			= "INPUTTIME_STRING";
	public static final String SEISAN_FLG_NAME_COLUMN				= "SEISAN_FLG_NAME";
	public static final String SYOTISITU_FLG_NAME_COLUMN			= "SYOTISITU_FLG_NAME";
	public static final String DOKUEI_FLG_NAME_COLUMN				= "DOKUEI_FLG_NAME";
	public static final String RI_ORDER_FLG_NAME_COLUMN			= "RI_ORDER_FLG_NAME";
	public static final String ORDERCOMMENT_ID_NAME_COLUMN 		= "ORDERCOMMENT_ID_NAME";
	public static final String PATIENTCOMMENT_COLUMN				= "PATIENTCOMMENT";
	public static final String KENZOUSTATUS_FLG_NAME_COLUMN		= "KENZOUSTATUS_FLG_NAME";
	public static final String KENZOUKINKYUU_FLG_NAME_COLUMN		= "KENZOUKINKYUU_FLG_NAME";
	public static final String EXAMSTARTDATE_STRING_COLUMN			= "EXAMSTARTDATE_STRING";
	public static final String EXAMENDDATE_STRING_COLUMN			= "EXAMENDDATE_STRING";
	public static final String KANJA_NYUGAIKBN_STRING_COLUMN		= "KANJA_NYUGAIKBN_STRING";
	public static final String DENPYO_NYUGAIKBN_STRING_COLUMN		= "DENPYO_NYUGAIKBN_STRING";
	public static final String SYSTEMKBN_STRING_COLUMN				= "SYSTEMKBN_STRING";
	public static final String ORDER_DATE_STRING_COLUMN			= "ORDER_DATE_STRING";
	public static final String YOBIDASI_DATE_STRING_COLUMN			= "YOBIDASI_DATE_STRING";
	public static final String KENZOU_DATE_STRING_COLUMN			= "KENZOU_DATE_STRING";
	public static final String DOUISHO_CHECK_FLAG_NAME_COLUMN		= "DOUISHO_CHECK_FLAG_NAME";
	public static final String KANJA_WAIT_STATE_NAME_COLUMN		= "KANJA_WAIT_STATE_NAME";
	public static final String KANJA_WAIT_TIME_NAME_COLUMN			= "KANJA_WAIT_TIME_NAME";
	public static final String TODAY_IRAI_ORDER_NAME_COLUMN		= "TODAY_IRAI_ORDER_NAME";
	public static final String ZOUEI_FLG_NAME_COLUMN				= "ZOUEI_FLG_NAME";
	public static final String KENSAHOUHOU_ID_COLUMN				= "KENSAHOUHOU_ID";
	public static final String INFECTION_CHECK_FLAG_NAME_COLUMN	= "INFECTION_CHECK_FLAG_NAME";
	public static final String COLLECTIONNO_COLUMN					= "COLLECTIONNO";				//管理番号	2009.07.09	AddBy A.Kobayashi
	public static final String KENSA_DATE_AGE_STRING_COLUMN		= "KENSA_DATE_AGE_STRING";		//空表示用	2009.07.09	AddBy A.Kobayashi
	public static final String BUICOMMENT_LINE_COLUMN				= "BUICOMMENT_LINE";			//改行あり
	public static final String TAKENSA_INOPE_FLG_COLUMN			= "TAKENSA_INOPE_FLG";
	public static final String KENSA_DATE_AGE_DBL_COLUMN			= "KENSA_DATE_AGE_DBL";
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	public static final String PHARMA_CHECK_FLAG_NAME_COLUMN		= "PHARMA_CHECK_FLAG_NAME";
	// 2010.11.16 Add T.Nishikubo End
	public static final String PRECHECK_STATUS_COLUMN				= "PRECHECK_STATUS";			// 2011.01.24 Add DD.Chinh KANRO-R-19
	// 2011.08.04 Add NSK_T.Koudate Start NML-CAT2-004
	public static final String MED_PERSON_NAME_COLUMN				= "MED_PERSON_NAME";
	// 2011.08.04 Add NSK_T.Koudate End

	// 2011.08.03 Add NSK_S.Imai Start NML-CAT2-029
	public static final String KENSASITU_CHANGE_COLUMN				= "KENSASITU_CHANGE";
	// 2011.08.03 Add NSK_S.Imai End
	// 2011.11.15 Mod NSK_H.Hashimoto Start OMH-1-05
	public static final String RECEIPT_INITIAL_CHAR_COLUMN			= "RECEIPT_INITIAL_CHAR";
	// 2011.11.15 Mod NSK_H.Hashimoto End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	public static final String EXAM_TIMING_LABEL_COLUMN			= "EXAM_TIMING_LABEL";
	public static final String EXAM_TIMING_COLOR_COLUMN			= "EXAM_TIMING_COLOR";
	public static final String EXAM_TIMING_COLORBK_COLUMN			= "EXAM_TIMING_COLORBK";
	// 2011.11.22 Add NSK_M.Ochiai End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisSummaryView()
	{
		this.tableNameStr = VIEW_NAME;
		this.infoCaption = TABLE_CAPTION;
	}

	/// <summary>
	/// 取得するカラム文字列を作成
	/// </summary>
	/// <returns></returns>
	private String GetColumnString()
	{
		String retStr = "";

		// 取得するカラム文字列を作成

		retStr =
			  RIS_ID_COLUMN + ", "
			+ KENSATYPE_ID_COLUMN + ", "
			+ KENSATYPE_NAME_COLUMN + ", "
			+ KENSATYPE_RYAKUNAME_COLUMN + ", "
			+ KANJA_ID_COLUMN + ", "
			+ KANJAID2_COLUMN + ", "
			+ BYOUSITU_ID_COLUMN + ", "
			+ BYOUSITU_COLUMN + ", "
			+ BYOUSITU_RYAKUNAME_COLUMN + ", "
			+ BYOUTOU_ID_COLUMN + ", "
			+ BYOUTOU_COLUMN + ", "
			+ BYOUTOU_RYAKUNAME_COLUMN + ", "
			+ KENSASITU_ID_COLUMN + ", "
			+ YOTEIKENSASITU_NAME_COLUMN + ", "
			+ YOTEIKENSASITU_RYAKUNAME_COLUMN + ", "
			+ JISSIKENSASITU_NAME_COLUMN + ", "
			+ JISSIKENSASITU_RYAKUNAME_COLUMN + ", "
			+ EXAMROOM_NAME_COLUMN + ", "
			+ EXAMROOM_RYAKUNAME_COLUMN + ", "
			+ KENSAKIKI_ID_COLUMN + ", "
			+ YOYAKU_KENSAKIKI_ID_COLUMN + ", "
			+ YOTEIKENSAROOM_COLUMN + ", "
			+ YOTEIKENSAROOMRYAKU_COLUMN + ", "
			+ JISSI_KENSAKIKI_ID_COLUMN + ", "
			+ KENSAROOM_COLUMN + ", "
			+ KENSAROOMRYAKU_COLUMN + ", "
			+ MODALITY_TYPE_COLUMN + ", "
			+ IRAI_SECTION_COLUMN + ", "
			+ IRAI_SECTIONRYAKU_COLUMN + ", "
			+ KANJYA_SECTION_COLUMN + ", "
			+ KANJA_SECTIONRYAKU_COLUMN + ", "
			+ SECTION_ID_COLUMN + ", "
			+ IRAI_SECTION_ID_COLUMN + ", "
			+ IRAI_DOCTOR_NO_COLUMN + ", "
			+ IRAI_DOCTOR_NAME_COLUMN + ", "
			+ KENSA_SIJI_COLUMN + ", "
			+ DENPYO_INSATU_FLG_COLUMN + ", "
			+ GYOUMU_KBN_COLUMN + ", "
			+ KENSA_STARTTIME_COLUMN + ", "
			+ RECEIPTDATE_COLUMN + ", "
			+ KENSA_DATE_COLUMN + ", "
			+ KENSA_DATE2_COLUMN + ", "
			+ KANJA_SYOKAI_FLG_COLUMN + ", "
			+ DOUISHO_FLG_COLUMN + ", "
			+ KANASIMEI_COLUMN + ", "
			+ KANJISIMEI_COLUMN + ", "
			+ ROMASIMEI_COLUMN + ", "
			+ ROMASIMEI2_COLUMN + ", "
			+ KENSA_DATE_AGE_COLUMN + ", "
			+ SEX_COLUMN + ", "
			+ TRANSPORTTYPE_COLUMN + ", "
			+ BIRTHDAY_COLUMN + ", "
			+ RENRAKU_MEMO_COLUMN + ", "
			+ RENRAKU_MEMO2_COLUMN + ", "
			+ SIJI_ISI_COMMENT_COLUMN + ", "
			+ SIJI_ISI_NAME_COLUMN + ", "
			+ INPUTDATE_COLUMN + ", "
			+ INPUTTIME_COLUMN + ", "
			+ KENSAI_NAME_COLUMN + ", "
			+ UKETUKE_TANTOU_NAME_COLUMN + ", "
			+ SEISAN_FLG_COLUMN + ", "
			+ SYOTISITU_FLG_COLUMN + ", "
			+ PORTABLE_FLG_COLUMN + ", "
			+ YUUSEN_FLG_COLUMN + ", "
			+ DOKUEI_FLG_COLUMN + ", "
			+ KANJA_NYUGAIKBN_COLUMN + ", "
			+ DENPYO_NYUGAIKBN_COLUMN + ", "
			+ HIS_HAKKO_DATE_COLUMN + ", "
			+ ISITATIAI_FLG_COLUMN + ", "
			+ RI_ORDER_FLG_COLUMN + ", "
			+ SIJI_ISI_ID_COLUMN + ", "
			+ UKETUKE_TANTOU_ID_COLUMN + ", "
			+ ORDERNO_COLUMN + ", "
			+ STATUS_COLUMN + ", "
			+ RECEIPTDATE2_COLUMN + ", "
			+ ACNO_COLUMN + ", "
			+ SIKYU_FLG_COLUMN + ", "
			+ KENSA_GISI_NAME_COLUMN + ", "
			+ RECEIPTNUMBER_COLUMN + ", "
			+ EXAMSTARTDATE_COLUMN + ", "
			+ EXAMENDDATE_COLUMN + ", "
			+ DENPYO_BYOUTOU_NAME_COLUMN + ", "
			+ DENPYO_BYOUTOU_RYAKUNAME_COLUMN + ", "
			+ SYSTEMKBN_COLUMN + ", "
			+ IRAIDOCTOR_TEL_COLUMN + ", "
			+ IRAISECTION_TEL_COLUMN + ", "
			+ BYOUTOU_TEL_COLUMN + ", "
			+ REMARKS_COLUMN + ", "
			+ ORDERCOMMENT_ID_COLUMN + ", "
			+ KENZOUSTATUS_FLG_COLUMN + ", "
			+ KENZOUKINKYUU_FLG_COLUMN + ", "
			+ KENZOU_TANTOU_NAME_COLUMN + ", "
			+ ORDER_DATE_COLUMN + ", "
			+ DOUISHO_CHECK_NAME_COLUMN + ", "
			+ DOUISHO_CHECK_FLAG_COLUMN + ", "
			+ INFECTION_COLUMN + ", "
			+ INFECTION_CHECK_NAME_COLUMN + ", "
			+ INFECTION_CHECK_FLAG_COLUMN + ", "
			+ YOBIDASI_DATE_COLUMN + ", "
			+ KENZOU_DATE_COLUMN + ", "
			+ EXAMTERMINALID_COLUMN + ", "
			+ INFECTION_COLUMN + ", "
			+ INFECTION_CHECK_NAME_COLUMN + ", "
			+ INFECTION_CHECK_FLAG_COLUMN + ", "
			+ STUDYINSTANCEUID_COLUMN + ", "
			+ ORDER_KENSA_DATE_COLUMN + ", "
			+ EX_KENSA_DATE_COLUMN
			/* 一ノ瀬保留
			// 2011.07.29 Add NSK_T.Koudate Start NML-CAT2-004
			+ UNKNOWNDATE_FLG_COLUMN + ", "
			+ JISISYA_NAME_COLUMN + ", "
			+ MED_PERSON_ID01_COLUMN + ", "
			+ MED_PERSON_NAME01_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN01_COLUMN + ", "
			+ MED_PERSON_ID02_COLUMN + ", "
			+ MED_PERSON_NAME02_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN02_COLUMN + ", "
			+ MED_PERSON_ID03_COLUMN + ", "
			+ MED_PERSON_NAME03_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN03_COLUMN + ", "
			+ MED_PERSON_ID04_COLUMN + ", "
			+ MED_PERSON_NAME04_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN04_COLUMN + ", "
			+ MED_PERSON_ID05_COLUMN + ", "
			+ MED_PERSON_NAME05_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN05_COLUMN + ", "
			+ MED_PERSON_ID06_COLUMN + ", "
			+ MED_PERSON_NAME06_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN06_COLUMN + ", "
			+ MED_PERSON_ID07_COLUMN + ", "
			+ MED_PERSON_NAME07_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN07_COLUMN + ", "
			+ MED_PERSON_ID08_COLUMN + ", "
			+ MED_PERSON_NAME08_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN08_COLUMN + ", "
			+ MED_PERSON_ID09_COLUMN + ", "
			+ MED_PERSON_NAME09_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN09_COLUMN + ", "
			+ MED_PERSON_ID10_COLUMN + ", "
			+ MED_PERSON_NAME10_COLUMN + ", "
			+ MED_PERSON_SYOKUINKBN10_COLUMN
			*/
			// 2011.07.29 Add NSK_T.Koudate End
			;
			// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
			//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
			//{
			//	// 2011.11.15 Mod NSK_H.Hashimoto Start OMH-1-05
			//	retStr	+= ", " + RECEIPT_INITIAL_CHAR_COLUMN
			//	// 2011.11.15 Mod NSK_H.Hashimoto End
			//			 + ", " + EXAM_TIMING_COLUMN;
			//}
			// 2011.11.22 Add NSK_M.Ochiai End

			// 2010.11.16 Add T.Nishikubo Start KANRO-R-3 KANRO-R-9
			//関東労災による特注処理対応
			//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
			//{
			//	retStr +=
			//		  ", "+ DRESSING_ROOM_ID_COLUMN
			//		+ ", "+ PHARMA_CHECK_NAME_COLUMN
			//		+ ", "+ PHARMA_CHECK_FLAG_COLUMN;
			//}
			// 2010.11.16 Add T.Nishikubo End
			// 2011.01.24 Add T.Nishikubo Start KANRO-R-22
			// 患者情報表示拡張機能
			if (Configuration.GetInstance().GetReceiptExtendPatientInfoFlg())
			{
				retStr += ", " + DENPYO_BYOUTOU_ID_COLUMN;
			}
			// 2011.01.24 Add T.Nishikubo End



		return retStr;
	}

	/// <summary>
	/// 不足カラム追加
	/// </summary>
	/// <param name="dt"></param>
	private void AddColumns(DataTable dt)
	{
		// 不足カラム追加

		/* 一ノ瀬保留
		dt.Columns.Add(NO_COLMUN,							typeof(int));
		dt.Columns.Add(SELECT_COLMUN,						typeof(String));
		dt.Columns.Add(TRANSPORTTYPE_TITLE_COLUMN,			typeof(String));
		dt.Columns.Add(BUI_NAME_COLUMN,						typeof(String));
		dt.Columns.Add(HOUKOU_NAME_COLUMN,					typeof(String));
		dt.Columns.Add(KENSAHOUHOU_NAME_COLUMN,				typeof(String));
		dt.Columns.Add(BUICOMMENTE_COLUMN,					typeof(String));
		dt.Columns.Add(PATIENTCOMMENT_COLUMN,				typeof(String));
		dt.Columns.Add(STATUS_NAME_COLUMN,					typeof(String));
		dt.Columns.Add(KENSA_DATE_AGE_STRING_COLUMN,		typeof(String));
		dt.Columns.Add(KENSA_DATE_STRING_COLUMN,			typeof(String));
		dt.Columns.Add(KENSA_STARTTIME_STRING_COLUMN,		typeof(String));
		dt.Columns.Add(TAKENSA_FLG_COLUMN,					typeof(String));
		dt.Columns.Add(KENSATYPE_COMMENT_COLUMN,			typeof(String));
		dt.Columns.Add(KAKUHO_COLUMN,						typeof(String));
		dt.Columns.Add(SATUEI_COUNT_COLUMN,					typeof(String));
		dt.Columns.Add(ORDER_DATE_STRING_COLUMN,			typeof(String));
		dt.Columns.Add(YOBIDASI_DATE_STRING_COLUMN,			typeof(String));
		dt.Columns.Add(KENZOU_DATE_STRING_COLUMN,			typeof(String));
		dt.Columns.Add(DOUISHO_CHECK_FLAG_NAME_COLUMN,		typeof(String));
		dt.Columns.Add(INFECTION_CHECK_FLAG_NAME_COLUMN,	typeof(String));
		// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
		//関東労災による特注処理対応
		if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
		{
			dt.Columns.Add(PHARMA_CHECK_FLAG_NAME_COLUMN, typeof(String));
		}
		// 2010.11.16 Add T.Nishikubo End
		//
		dt.Columns.Add(KANJA_WAIT_STATE_NAME_COLUMN,		typeof(String));
		dt.Columns.Add(KANJA_WAIT_TIME_NAME_COLUMN,			typeof(String));
		dt.Columns.Add(TODAY_IRAI_ORDER_NAME_COLUMN,		typeof(String));
		dt.Columns.Add(ZOUEI_FLG_NAME_COLUMN,				typeof(String));
		dt.Columns.Add(KENSAHOUHOU_ID_COLUMN,				typeof(String));
		dt.Columns.Add(BUICOMMENT_LINE_COLUMN,				typeof(String));
		dt.Columns.Add(TAKENSA_INOPE_FLG_COLUMN,			typeof(String));
		dt.Columns.Add(KENSA_DATE_AGE_DBL_COLUMN,			typeof(double));
		// 2011.01.24 Add DD.Chinh Start KANRO-R-19
		//関東労災による特注処理
		if (Configuration.GetInstance().GetPreStsFlg())
		{
			dt.Columns.Add(PRECHECK_STATUS_COLUMN, typeof(String));
		}
		// 2011.01.24 Add DD.Chinh End

		// 2011.08.04 Add NSK_T.Koudate Start NML-CAT2-004
		dt.Columns.Add(MED_PERSON_NAME_COLUMN, typeof(String));
		// 2011.08.04 Add NSK_T.Koudate End

		// 2011.08.03 Add NSK_S.Imai Start NML-CAT2-029
		dt.Columns.Add(KENSASITU_CHANGE_COLUMN, typeof(String));
		// 2011.08.04 Add NSK_T.Koudate End
		*/

	}

	/// <summary>
	/// オーダ情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchOrderData(Connection con, OrderSearchParameter param) throws Exception
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if (con != null && param != null)
		{
			String sql = CreateSelectSQL(param);

			//★制限なし
			dt = Select(con, sql, null);

			if (dt != null)
			{
				//不足カラム追加
				AddColumns(dt);
			}
			param.SetExecSql(sql);	//実行SQL文を設定
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/*
	/// <summary>
	/// オーダ情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のarrayList</returns>
	public ArrayList SearchOrderData(Connection con, Transaction trans, ArrayList risIDArrayList)
	{
		// parameters
		ArrayList arrayList = new ArrayList();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risIDArrayList != null)
		{

			for (int i = 0; i < risIDArrayList.Count; i++)
			{
				OrderSearchParameter param = new OrderSearchParameter();
				DataTable dt = null;
				String risIDStr = risIDArrayList[i].toString();
				param.SetRisID(risIDStr);

				String sql = CreateSelectSQL(param);

				//★制限なし
				dt = Select(con, trans, sql, false);

				if (dt != null && dt.Rows.Count > 0)
				{
					arrayList.Add(dt.Rows[0]);
				}
			}
		}

		// end log
		logger.debug("end");

		return arrayList;
	}
	*/

	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">依頼の検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(OrderSearchParameter param)
	{
		logger.debug("begin");

		StringBuilder strBufOrSQL = new StringBuilder("");

		this.keys.clear();
		this.inList.clear();

		//取得するカラム文字列を作成
		String colmunName = GetColumnString();

		//検索条件を追加する
		AddWhere(param);

		//オーダ番号でのソートがtrueだったら検査種別・オーダ番号昇順でソート
		//帳票使用
		if (param.GetOrderSortFlg())
		{
			this.AddOrderKeyAsc(KENSATYPE_ID_COLUMN);
			this.AddOrderKeyAsc(ORDERNO_COLUMN);
		}
		else if (param.GetOrderByExamEndDateDesc())
		{
			this.AddOrderKeyDesc(EXAMENDDATE_COLUMN);
		}
		else
		{
			this.AddOrderKeyAsc(RIS_ID_COLUMN);
		}

		logger.debug("end");

		return this.GetSelectColmunSQL(colmunName, VIEW_NAME);
	}

	/*
	/// <summary>
	/// オーダ情報件数を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	public int CountOrderData(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		int count = 0;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sql = CreateCountSQL(param);
			DataTable dt = Select(con, trans, sql);
			if (dt != null && dt.Rows.Count == 1)
			{
				count = Integer.parseInt(dt.Rows[0][0].toString());
				//count = (int)(decimal)dt.Rows[0][0];
			}
		}

		// end log
		logger.debug("end");

		return count;
	}

	//
	/// <summary>
	/// COUNT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">依頼の検索条件</param>
	/// <returns>COUNT用のSQL文</returns>
	private String CreateCountSQL(OrderSearchParameter param)
	{
		logger.debug("begin");

		StringBuilder strBufOrSQL = new StringBuilder("");

		this.keys.clear();
		this.inList.clear();

		//検索条件を追加する
		AddWhere(param);

		//count(*)用セレクト文の作成
		String tempSelectSQL = this.GetSelectCountSQL(RIS_ID_COLUMN);

		logger.debug("end");

		return tempSelectSQL;
	}
	*/

	/// <summary>
	/// 検索条件を追加する
	/// </summary>
	/// <param name="param"></param>
	private void AddWhere(OrderSearchParameter param)
	{
		//RISID
		if (param.GetRisID().length() > 0)
		{
			this.AddColValue(RIS_ID_COLUMN, param.GetRisID(), true, SignType.Equal);
		}
		else
		{
			String[] risIDs = param.GetRisIDList().split(",");
			if (risIDs.length == 1)
			{
				this.AddColValue(RIS_ID_COLUMN, risIDs[0], true, SignType.Equal);
			}
			else
			{
				for (int i = 0; i < risIDs.length; i++)
				{
					this.AddColValue(RIS_ID_COLUMN, risIDs[i], true, SignType.In);
				}
			}
		}

		//2010.10.19 Add H.Orikasa Start
		//日未定あり
		if (param.GetUnKnownDateBool() && !Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodStartDate()) && !Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodEndDate()))
		{
			String startDate = new SimpleDateFormat("yyyyMMdd").format(param.GetExecutePeriodStartDate());
			String endDate = new SimpleDateFormat("yyyyMMdd").format(param.GetExecutePeriodEndDate());
			String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);

			if (unknownDateStr != null && unknownDateStr.length() > 7)
			{
				String preStr = "WHERE";
				this.AddColSetValue(" " + preStr + " ( " + KENSA_DATE_COLUMN + " >= " + startDate
						+ " and " + KENSA_DATE_COLUMN + " <= " + endDate
						+ " or " + KENSA_DATE_COLUMN + " = " + unknownDateStr + " ) ");
			}
			else
			{
				//検査日
				if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodStartDate()) )
				{
					this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(startDate), true, SignType.Under);
				}
				if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodEndDate()) )
				{
					this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(endDate), true, SignType.Over);
				}
			}
		}
		//2010.10.19 Add H.Orikasa End

		//検査種別
		String[] kensaTypes = param.GetRrisKensaTypeID().split(",");
		if (kensaTypes.length == 1)
		{
			this.AddColValue(KENSATYPE_ID_COLUMN, kensaTypes[0], true, SignType.Equal);
		}
		else
		{
			for (int i = 0; i < kensaTypes.length; i++)
			{
				this.AddColValue(KENSATYPE_ID_COLUMN, kensaTypes[i], true, SignType.In);
			}
		}

		//入外区分
		String[] nyugaiKbn = param.GetNyugaiKbn().split(",");
		for (int i = 0; i < nyugaiKbn.length; i++)
		{
			this.AddColValue(KANJA_NYUGAIKBN_COLUMN, nyugaiKbn[i], true, SignType.In);
		}

		if (param.GetKanjaIDOnlyBool())
		{
			//患者ID
			this.AddColValue(KANJA_ID_COLUMN, param.GetKanjaID(), true, SignType.Equal);
		}
		else
		{
			//患者ID
			this.AddColValue(KANJA_ID_COLUMN, param.GetKanjaID(), true, SignType.Like);

			////患者氏名
			//this.AddColValue(KANJISIMEI_COLUMN, param.GetKanjaKanjiSimei(), true, SignType.Like);

			////患者カナ氏名
			//this.AddColValue(KANASIMEI_COLUMN, param.GetKanjaKanaSimei(), true, SignType.Like);
		}

		if (param.GetOrderDateBool())
		{
			//依頼日
			if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodStartDate()) )
			{
				this.AddColValue(ORDER_DATE_COLUMN, param.GetExecutePeriodStartDate(), true, SignType.Under);
			}
			if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodEndDate()) )
			{
				this.AddColValue(ORDER_DATE_COLUMN, param.GetExecutePeriodEndDate(), true, SignType.Over);
			}
		}
		else
		{
			if (!param.GetUnKnownDateBool())
			{
				// 2010.07.20 Mod T.Nishikubo Start
				//通常　日未定なし
				if (param.GetWindowAppID().equals(CommonString.MENUBUTTON_ID_T1))
				{
					//EXAMSTARTDATE
					this.AddColValue(EXAMSTARTDATE_COLUMN, param.GetExecutePeriodStartDate(), true, SignType.Under, "");
					this.AddColValue(EXAMSTARTDATE_COLUMN, param.GetExecutePeriodEndDate(), true, SignType.Over, "");
				}
				else
				{
					//検査日
					if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodStartDate()) )
					{
						String startDate = new SimpleDateFormat("yyyyMMdd").format(param.GetExecutePeriodStartDate());
						this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(startDate), true, SignType.Under);
					}
					if (!Const.TIMESTAMP_MINVALUE.equals(param.GetExecutePeriodEndDate()) )
					{
						String endDate = new SimpleDateFormat("yyyyMMdd").format(param.GetExecutePeriodEndDate());
						this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(endDate), true, SignType.Over);
					}
					// 2010.09.02 Add K.Shinohara Start
					if (!Const.TIMESTAMP_MINVALUE.equals(param.GetRemoveKensaDate()) )
					{
						String removeDate = new SimpleDateFormat("yyyyMMdd").format(param.GetRemoveKensaDate());
						this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(removeDate), true, SignType.NotEqual);
					}
					// 2010.09.02 Add K.Shinohara End
				}
				// 元の処理
				////通常　日未定なし
				////検査日
				//if (param.GetExecutePeriodStartDate() != Timestamp.MinValue)
				//{
				//    String startDate = param.GetExecutePeriodStartDate().Date.toString("yyyyMMdd");
				//    this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(startDate), true, SignType.Under);
				//}
				//if (param.GetExecutePeriodEndDate() != Timestamp.MinValue)
				//{
				//    String endDate = param.GetExecutePeriodEndDate().Date.toString("yyyyMMdd");
				//    this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(endDate), true, SignType.Over);
				//}

				// 2010.07.20 Mod T.Nishikubo End
			}
			else
			{
				// コメント化 2010.10.19 Del H.Orikasa
				////日未定あり
				//if (param.GetExecutePeriodStartDate() != Timestamp.MinValue && param.GetExecutePeriodEndDate() != Timestamp.MinValue)
				//{
				//    String startDate = param.GetExecutePeriodStartDate().Date.toString("yyyyMMdd");
				//    String endDate = param.GetExecutePeriodEndDate().Date.toString("yyyyMMdd");
				//    String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);

				//    if (unknownDateStr != null && unknownDateStr.length() > 7)
				//    {
				//        //2010.10.13 Mod H.Orikasa Start
				//        String preStr = "WHERE";
				//        if (param.GetKanjaID().length() > 0)
				//        {
				//            preStr = "AND";
				//        }
				//        this.AddColSetValue(" " + preStr + " ( " + KENSA_DATE_COLUMN + " >= " + startDate
				//        + " and " + KENSA_DATE_COLUMN + " <= " + endDate
				//        + " or " + KENSA_DATE_COLUMN + " = " + unknownDateStr + " ) ");
				//        // コメント
				//        //this.AddColSetValue(" and ( " + KENSA_DATE_COLUMN + " >= " + startDate
				//        //+ " and " + KENSA_DATE_COLUMN + " <= " + endDate
				//        //+ " or " + KENSA_DATE_COLUMN + " = " + unknownDateStr + " ) ");
				//
				//        //2010.10.13 Mod H.Orikasa End
				//    }
				//    else
				//    {
				//        //検査日
				//        if (param.GetExecutePeriodStartDate() != Timestamp.MinValue)
				//        {
				//            this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(startDate), true, SignType.Under);
				//        }
				//        if (param.GetExecutePeriodEndDate() != Timestamp.MinValue)
				//        {
				//            this.AddColValue(KENSA_DATE_COLUMN, Integer.parseInt(endDate), true, SignType.Over);
				//        }
				//    }
				//}

			}
		}

		//ステータス
		try
		{
			String[] statuses = param.GetRrisStatus().split(",");
			if (statuses.length == 1)
			{
				if (statuses[0].length() > 0)
				{
					this.AddColValue(STATUS_COLUMN, Integer.parseInt(statuses[0]), true, SignType.Equal);
				}
			}
			else
			{
				for (int i = 0; i < statuses.length; i++)
				{
					if (statuses[i].length() > 0)
					{
						this.AddColValue(STATUS_COLUMN, Integer.parseInt(statuses[i]), true, SignType.In);
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		//検査室
		String roomNullStr = KENSASITU_ID_COLUMN + " " + SignType.isNull;
		String[] kensaRooms = param.GetRrisExamRoomID().split(",");

		if (param.GetRrisEmptyRoom())
		{
			//未割当ての場合
			if (param.GetRrisExamRoomID().length() > 0)
			{
				//指定検査室＋Null条件
				String whereStr = "";
				this.AddColSetValue(" and ( " + KENSASITU_ID_COLUMN + " : (");
				for (int i = 0; i < kensaRooms.length; i++)
				{
					String roomStr = kensaRooms[i];
					if (roomStr.length() > 0)
					{
						if (whereStr.length() <= 0)
						{
							whereStr = "'" + roomStr + "'";
						}
						else
						{
							whereStr += ", '" + roomStr + "'";
						}
					}
				}
				this.AddColSetValue(whereStr);

				this.AddColSetValue(") or " + roomNullStr + " ) ");
			}
		}
		else
		{
			for (int i = 0; i < kensaRooms.length; i++)
			{
				this.AddColValue(KENSASITU_ID_COLUMN, kensaRooms[i], true, SignType.In);
			}
		}

		//検査機器
		String kikiNullStr = KENSAKIKI_ID_COLUMN + " " + SignType.isNull;
		String[] kensaKikis = param.GetRrisKensaKikiID().split(",");

		if (param.GetRrisEmptyKiki())
		{
			//未割当ての場合
			if (param.GetRrisKensaKikiID().length() > 0)
			{
				//指定検査機器＋Null条件
				String whereStr = "";
				this.AddColSetValue(" and ( " + KENSAKIKI_ID_COLUMN + " : (");
				for (int i = 0; i < kensaKikis.length; i++)
				{
					String kikiStr = kensaKikis[i];
					if (kikiStr.length() > 0)
					{
						if (whereStr.length() <= 0)
						{
							whereStr = "'" + kikiStr + "'";
						}
						else
						{
							whereStr += ", '" + kikiStr + "'";
						}
					}
				}
				this.AddColSetValue(whereStr);

				this.AddColSetValue(") or " + kikiNullStr + " ) ");
			}
		}
		else
		{
			for (int i = 0; i < kensaKikis.length; i++)
			{
				this.AddColValue(KENSAKIKI_ID_COLUMN, kensaKikis[i], true, SignType.In);
			}
		}

		//依頼科
		this.AddColValue(IRAI_SECTION_ID_COLUMN, param.GetIraiSectionID(), true, SignType.Equal);

		//オーダ日=検査日
		if (param.GetDateOrderKensa())
		{
			String colStr = "TO_CHAR(" + HIS_HAKKO_DATE_COLUMN + ",'YYYYMMDD')";
			this.AddColValue(colStr, KENSA_DATE_COLUMN, true, SignType.ColumnEqual);
		}

		//指示医師ID
		if (param.GetSijiIsiID().length() > 0)
		{
			this.AddColValue(SIJI_ISI_ID_COLUMN, param.GetSijiIsiID(), true, SignType.Equal);
		}

		// 2011.01.24 Add DD.Chinh Start KANRO-R-19
		if (Configuration.GetInstance().GetPreStsFlg())
		{
			//関東労災による特注処理
			if (CommonString.PRECHECK_STATUS_MI.equals(param.GetPreStsStr()))
			{
				String preStsNullStr = SIJI_ISI_ID_COLUMN + " " + SignType.isNull;
				this.AddColSetValue(" AND " + preStsNullStr + " ");
			}
			if (CommonString.PRECHECK_STATUS_SUMI.equals(param.GetPreStsStr()))
			{
				String preStsNullStr = SIJI_ISI_ID_COLUMN + " " + SignType.isNotNull;
				this.AddColSetValue(" AND " + preStsNullStr + " ");
			}
		}
		// 2011.01.24 Add DD.Chinh End

		//技師ID
		if (param.GetGisiID().length() > 0)
		{
			this.AddColValue(UKETUKE_TANTOU_ID_COLUMN, param.GetGisiID(), true, SignType.Equal);
		}

		//RIオーダフラグ
		if (CommonString.SEARCH_PATTERN_RIORDER_MULTI.equals(param.GetRIOrderFlg()))
		{
			this.AddColValue(RI_ORDER_FLG_COLUMN, CommonString.RI_ORDER_FLG_NEEDLE, true, SignType.In);
			this.AddColValue(RI_ORDER_FLG_COLUMN, CommonString.RI_ORDER_FLG_INSPECT, true, SignType.In);
		}
		else if (CommonString.SEARCH_PATTERN_RIORDER_NEEDLE.equals(param.GetRIOrderFlg()))
		{
			this.AddColValue(RI_ORDER_FLG_COLUMN, CommonString.RI_ORDER_FLG_NEEDLE, true, SignType.Equal);
		}
		else if (CommonString.SEARCH_PATTERN_RIORDER_INSPECT.equals(param.GetRIOrderFlg()))
		{
			this.AddColValue(RI_ORDER_FLG_COLUMN, CommonString.RI_ORDER_FLG_INSPECT, true, SignType.Equal);
		}

		//ポータブルフラグ
		if (CommonString.SEARCH_PATTERN_PORTABLE_MULTI.equals(param.GetPortableFlg()))
		{
			this.AddColValue(PORTABLE_FLG_COLUMN, CommonString.PORTABLE_FLG_NORMAL, true, SignType.In);
			this.AddColValue(PORTABLE_FLG_COLUMN, CommonString.PORTABLE_FLG_OPE, true, SignType.In);
		}
		else if (CommonString.SEARCH_PATTERN_PORTABLE_NORMAL.equals(param.GetPortableFlg()))
		{
			this.AddColValue(PORTABLE_FLG_COLUMN, CommonString.PORTABLE_FLG_NORMAL, true, SignType.Equal);
		}
		else if (CommonString.SEARCH_PATTERN_PORTABLE_OPE.equals(param.GetPortableFlg()))
		{
			this.AddColValue(PORTABLE_FLG_COLUMN, CommonString.PORTABLE_FLG_OPE, true, SignType.Equal);
		}

		//検像進捗フラグ
		String[] kenzouStatuss = param.GetKenzouStatusFlg().split(",");

		if (kenzouStatuss.length == 1)
		{
			this.AddColValue(KENZOUSTATUS_FLG_COLUMN, kenzouStatuss[0], true, SignType.Equal);
		}
		else
		{
			for (int i = 0; i < kenzouStatuss.length; i++)
			{
				this.AddColValue(KENZOUSTATUS_FLG_COLUMN, kenzouStatuss[i], true, SignType.In);
			}
		}
		// 2010.06.23 Add T.Nishikubo Start
		//業務区分
		String[] gyoumuKbnList = param.GetGyoumKbnStr().split(",");

		if (gyoumuKbnList.length == 1)
		{
			this.AddColValue(GYOUMU_KBN_COLUMN, gyoumuKbnList[0], true, SignType.Equal);
		}
		else
		{
			for (int i = 0; i < gyoumuKbnList.length; i++)
			{
				this.AddColValue(GYOUMU_KBN_COLUMN, gyoumuKbnList[i], true, SignType.In);
			}
		}
		// 2010.06.23 Add T.Nishikubo End

		//2010.10.12 Add H.Orikasa Start
		//件数制限フラグ
		if (param.GetSaveCount())
		{
			String colStr = " AND ROWNUM <= " + Configuration.GetInstance().GetSystemParam().GetResultcountValue1Int();
			this.AddColSetValue(colStr);
		}
		//2010.10.12 Add H.Orikasa End

		// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
		//関東労災による特注処理対応
		//if (Configuration.GetInstance().GetSystemID().equals(CommonString.SYSID_KANRO))
		//{
		//	if (param.GetPreparing().equals(CommonString.FLG_ON))
		//	{
		//		this.AddColValue(DRESSING_ROOM_ID_COLUMN, null, true, SignType.isNotNull);
		//		// 2011.02.28 Del T.Ootsuka Start KANRO-R-29
		//		//this.AddColValue(STATUS_COLUMN, CommonString.STATUS_ISREGISTERED, true, SignType.Equal);
		//		// 2011.02.28 Del T.Ootsuka End
		//	}
		//}
		// 2010.11.16 Add T.Nishikubo End
	}

	/*
	/// <summary>
	/// 他検査情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchTakensaOrderData(Connection con, Transaction trans, OrderSearchParameter param)
	{
		Logger.SetLogMode(Logger.WARNING_LEVEL);
		logger.debug("begin");

		DataTable retDt = null;
		try
		{
			if (con != null && trans != null && param != null)
			{
				String sql = CreateTakensaSelectSQLString(param);

				//2010.10.14 Mod H.Orikasa Start

				//SELECT
				DataTable tempDt = Select(con, trans, sql, false);
				//検索結果に対してSortする
				retDt = tempDt.Clone();
				DataView dv = new DataView(tempDt);
				dv.Sort = "Kensa_StartTime, KENSATYPE_ID";
				//ソートされたレコードのコピー
				for (DataRowView drv : dv)
				{
					retDt.ImportRow(drv.Row);
				}
				// コメント
				////SELECT
				//retDt = Select(con, trans, sql, false);

				////Command cmd = CreateTakensaSelectSQL(param);
				////cmd.Connection  = con;
				////cmd.Transaction = trans;
				////retDt = Select(cmd, false);


				//2010.10.14 Mod H.Orikasa End

				if (retDt != null)
				{
					if (!retDt.Columns.Contains(YOTEIKENSASITU_NAME_COLUMN))
					{
						retDt.Columns.Add(YOTEIKENSASITU_NAME_COLUMN);
					}
					if (!retDt.Columns.Contains(JISSIKENSASITU_NAME_COLUMN))
					{
						retDt.Columns.Add(JISSIKENSASITU_NAME_COLUMN);
					}

					//不足カラム追加
					AddColumns(retDt);
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		logger.debug("end");
		Logger.SetLogMode(Logger.DEBUG_LEVEL);

		return retDt;
	}

	/// <summary>
	/// 他検査SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private Command CreateTakensaSelectSQL(OrderSearchParameter param)
	{
		logger.debug("begin");

		Command cmd = new Command();

		this.keys.clear();
		this.ClearOrderKey();

		//患者ID
		if (param.GetKanjaID().length() > 0)
		{
			this.AddColSetValue(KANJA_ID_COLUMN, ":" + KANJA_ID_COLUMN, true, SignType.Equal);
			Parameter oraParam = new Parameter(KANJA_ID_COLUMN, param.GetKanjaID());
			cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(KANJA_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, param.GetKanjaID(), ParameterDirection.Input);
		}
		//検査日
		if (param.GetKensaDate().length() > 0)
		{
			this.AddColSetValue(KENSA_DATE_COLUMN, ":" + KENSA_DATE_COLUMN, true, SignType.Equal);
			int kensaDateInt = Integer.parseInt(param.GetKensaDate());
			Parameter oraParam = new Parameter(KENSA_DATE_COLUMN, kensaDateInt);
			cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(KENSA_DATE_COLUMN, .DataAccess.Client.DbType.Int32, kensaDateInt, ParameterDirection.Input);
		}

		//取得するカラム文字列を作成
		String colmunName = GetColumnString();

		cmd.CommandText = this.GetSelectColmunSQL(colmunName, VIEW_NAME);

		logger.debug("end");

		return cmd;
	}

	/// <summary>
	/// 他検査SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateTakensaSelectSQLString(OrderSearchParameter param)
	{
		logger.debug("begin");
		String retStr = "";

		//2010.10.14 Mod H.Orikasa Start
		retStr = "SELECT "
				+ "E.RIS_ID, "
				+ "O.Kensatype_ID, "
				+ "O.Kanja_ID, "
				+ "O.Kensa_Date, "
				+ "E.ReceiptDate, "
				+ "E.Status, "
				+ "KM.Kensatype_Name, "
				+ "KM.Kensatype_Ryakuname, "
				+ "O.Kensa_StartTime, "
				+ "O.KensaSitu_ID YoteiKensasitu_ID, "
				+ "E.KensaSitu_ID JissiKensasitu_ID, "
				+ "E.ExamStartDate, "
				+ "E.ExamEndDate "
				+ "FROM "
				+ "ExMainTable E INNER JOIN "
				+ "OrderMainTable O ON E.RIS_ID=O.RIS_ID "
				+ "INNER JOIN "
				+ "PatientInfo P ON O.Kanja_ID=P.KANJA_ID "
				+ "LEFT OUTER JOIN "
				+ "KENSATYPEMASTER KM ON O.Kensatype_ID =  KM.Kensatype_ID "
				+ "WHERE "
				+ "P.Kanja_ID         =  '" + param.GetKanjaID()   + "' "
				+ "AND E.RIS_ID       <> '" + param.GetRisID()     + "' "
				+ "AND O.Kensa_Date   =  "  + param.GetKensaDate() + " ";
		// コメント
		//// 2010.09.17 Mod K.Shinohara Start
		//retStr += "SELECT ";
		//retStr += "SV.RIS_ID, ";
		//retStr += "SV.Kensatype_ID, ";
		//retStr += "SV.Kanja_ID, ";
		//retStr += "SV.Kensa_Date, ";
		//retStr += "SV.ReceiptDate, ";
		//retStr += "SV.Status, ";
		//retStr += "KM.Kensatype_Name, ";
		//retStr += "KM.Kensatype_Ryakuname, ";
		//retStr += "SV.Kensa_StartTime, ";
		//retStr += "SV.YoteiKensasitu_ID, ";
		//retStr += "SV.JissiKensasitu_ID, ";
		//// 2010.09.19 Add K.Shinohara Start
		//retStr += "SV.EXAMSTARTDATE, ";
		//// 2010.09.19 Add K.Shinohara End
		//retStr += "SV.EXAMENDDATE ";
		//retStr += "FROM ";
		//retStr += "SUMMARYSUBVIEW SV, ";
		//retStr += "KENSATYPEMASTER KM ";
		//retStr += "WHERE ";
		//retStr += "SV.Kanja_ID         =  '" + param.GetKanjaID() + "' ";
		//retStr += "AND SV.RIS_ID       <> '" + param.GetRisID() + "' ";
		//retStr += "AND SV.Kensa_Date   =  " + param.GetKensaDate() + " ";
		//retStr += "AND SV.Kensatype_ID =  KM.Kensatype_ID(+) ";
		//retStr += "ORDER BY SV.Kensa_StartTime, KM.KENSATYPE_ID ";
		////retStr += "SELECT ";
		////retStr += "EX.RIS_ID, ";
		////retStr += "EX.Kensatype_ID, ";
		////retStr += "EX.Kanja_ID, ";
		////retStr += "EX.Kensa_Date, ";
		////retStr += "EX.ReceiptDate, ";
		////retStr += "EX.Status, ";
		////retStr += "KM.Kensatype_Name, ";
		////retStr += "KM.Kensatype_Ryakuname, ";
		////retStr += "OM.Kensa_StartTime, ";
		////retStr += "OM.KensaSitu_ID YoteiKensasitu_ID, ";
		////retStr += "EX.KensaSitu_ID JissiKensasitu_ID ";
		////retStr += ", EX.EXAMENDDATE ";	// 2010.09.16 Add T.Ootsuka V2ST-10
		////retStr += "FROM ";
		////retStr += "EXMAINTABLE EX, ";
		////retStr += "ORDERMAINTABLE OM, ";
		////retStr += "KENSATYPEMASTER KM ";
		////retStr += "WHERE ";
		////retStr += "EX.Kanja_ID         =  '" + param.GetKanjaID() + "' ";
		////retStr += "AND EX.RIS_ID       <> '" + param.GetRisID() + "' ";
		////retStr += "AND OM.Kensa_Date   =  " + param.GetKensaDate() + " ";
		////retStr += "AND OM.Kensatype_ID =  KM.Kensatype_ID(+) ";
		////retStr += "AND EX.RIS_ID       =  OM.RIS_ID ";
		////retStr += "ORDER BY OM.Kensa_StartTime, KM.KENSATYPE_ID ";
		//// 2010.09.17 Mod K.Shinohara End

		//2010.10.14 Mod H.Orikasa End

		logger.debug("end");

		return retStr;
	}


	/// <summary>
	/// 業務詳細過去情報の件数を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public int SearchGyomuOldOrderDataCount(Connection con, Transaction trans, OrderSearchParameter param)
	{
		Logger.SetLogMode(Logger.WARNING_LEVEL);
		logger.debug("begin");

		int retInt = 0;
		try
		{
			if (con != null && trans != null && param != null)
			{
				String sql = CreateGyomuOldOrderDataSelectSQLString(param);

				//SELECT
				DataTable dt = Select(con, trans, sql, false);

				if (dt != null && dt.Rows.Count == 1)
				{
					retInt = Integer.parseInt(dt.Rows[0][0].toString());
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		logger.debug("end");
		Logger.SetLogMode(Logger.DEBUG_LEVEL);

		return retInt;
	}

	/// <summary>
	/// 業務詳細過去情報SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateGyomuOldOrderDataSelectSQLString(OrderSearchParameter param)
	{
		logger.debug("begin");

		StringBuilder strBufOrSQL = new StringBuilder("");

		this.keys.clear();
		this.inList.clear();

		//検索条件を追加する
		if (param.GetExecutePeriodStartDate() != Timestamp.MinValue)
		{
			String startDate = param.GetExecutePeriodStartDate().Date.toString("yyyyMMdd");
			this.AddColValue(KENSA_DATE_COLUMN, startDate, true, SignType.Under);
		}
		if (param.GetExecutePeriodEndDate() != Timestamp.MinValue)
		{
			String endDate = param.GetExecutePeriodEndDate().Date.toString("yyyyMMdd");
			this.AddColValue(KENSA_DATE_COLUMN, endDate, true, SignType.Over);
		}
		this.AddColValue(KENSATYPE_ID_COLUMN, param.GetRrisKensaTypeID(), true, SignType.Equal);
		this.AddColValue(KANJA_ID_COLUMN, param.GetKanjaID(), true, SignType.Equal);
		this.AddColValue(STATUS_COLUMN, TextUtil.ParseStringToInt(CommonString.STATUS_ISFINISHED) , true, SignType.Equal);
		this.AddColValue(RIS_ID_COLUMN, param.GetRisID(), true, SignType.NotEqual);

		//count(*)用セレクト文の作成
		String tempSelectSQL = this.GetSelectCountSQL(RIS_ID_COLUMN);

		logger.debug("end");

		return tempSelectSQL;
	}

	//2010.10.29 Add K.Shinohara Start
	/// <summary>
	/// 他検査情報リストを検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchTakensaOrderDataList(Connection con, Transaction trans, OrderSearchParameter param)
	{
		logger.debug("begin");

		DataTable retDt = null;
		try
		{
			if (con != null && trans != null && param != null &&
				((param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
				  param.GetExecutePeriodEndDate()   != Timestamp.MinValue) ||
				  param.GetUnKnownDateBool()))
			{
				String sql = CreateTakensaListSelectSQLString(param);

				//SELECT
				DataTable tempDt = Select(con, trans, sql, false);

				if (tempDt != null)
				{
					// 不足カラム追加
					if (!tempDt.Columns.Contains(KENSA_DATE_COLUMN))
					{
						tempDt.Columns.Add(KENSA_DATE_COLUMN);
					}
					if (!tempDt.Columns.Contains(YOTEIKENSASITU_NAME_COLUMN))
					{
						tempDt.Columns.Add(YOTEIKENSASITU_NAME_COLUMN);
					}
					if (!tempDt.Columns.Contains(JISSIKENSASITU_NAME_COLUMN))
					{
						tempDt.Columns.Add(JISSIKENSASITU_NAME_COLUMN);
					}
					if (!tempDt.Columns.Contains(KENSATYPE_NAME_COLUMN))
					{
						tempDt.Columns.Add(KENSATYPE_NAME_COLUMN);
					}
					if (!tempDt.Columns.Contains(KENSATYPE_RYAKUNAME_COLUMN))
					{
						tempDt.Columns.Add(KENSATYPE_RYAKUNAME_COLUMN);
					}

					//不足カラム追加
					AddColumns(tempDt);



					//検査日に値を設定する
					for (DataRow row : tempDt.Rows)
					{
						int statusInt = TextUtil.ParseStringToInt(row[STATUS_COLUMN].toString());
						int checkInt  = TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED);

						//検査日を設定
						if (statusInt >= checkInt)
						{
							//受済以降→実績情報の検査日
							row[KENSA_DATE_COLUMN] = row[EX_KENSA_DATE_COLUMN];
						}
						else
						{
							//受済未満→オーダ情報の検査日
							row[KENSA_DATE_COLUMN] = row[ORDER_KENSA_DATE_COLUMN];
						}
					}

					//検索結果に対してSortする
					retDt = tempDt.Clone();
					DataView dv = new DataView(tempDt);
					dv.Sort = "KENSA_DATE, KENSA_STARTTIME, KENSATYPE_ID, RIS_ID";	// 2011.09.22 Mod H.Orikasa NML-CAT9-035
					//dv.Sort = "KENSA_DATE, KENSA_STARTTIME, KENSATYPE_ID";
					//ソートされたレコードのコピー
					for (DataRowView drv : dv)
					{
						retDt.ImportRow(drv.Row);
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		logger.debug("end");

		return retDt;
	}
	*/

	/// <summary>
	/// 他検査SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	//private String CreateTakensaListSelectSQLString(OrderSearchParameter param)
	//{
	//	logger.debug("begin");
	//	String retStr = "";
	//
	//	// 2011.03.02 Mod K.Shinohara Start
	//	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//	retStr = "SELECT  /*+ INDEX(OM) INDEX(EM) */  "
	//			+ "OM.RIS_ID, OM.KANJA_ID, OM.KENSATYPE_ID, OM.KENSA_DATE AS ORDER_KENSA_DATE, OM.KENSA_STARTTIME, OM.KENSASITU_ID AS YOTEIKENSASITU_ID, "
	//			+ "EM.KENSA_DATE AS EX_KENSA_DATE, EM.RECEIPTDATE, EM.STATUS, EM.KENSASITU_ID AS JISSIKENSASITU_ID, EM.EXAMSTARTDATE, EM.EXAMENDDATE "
	//			+ "FROM ORDERMAINTABLE OM, EXMAINTABLE EM ";
	//	// 元の処理
	//	//retStr = "SELECT  /*+ RULE */  "
	//	//        + "OM.RIS_ID, OM.KANJA_ID, OM.KENSATYPE_ID, OM.KENSA_DATE AS ORDER_KENSA_DATE, OM.KENSA_STARTTIME, OM.KENSASITU_ID AS YOTEIKENSASITU_ID, "
	//	//        + "EM.KENSA_DATE AS EX_KENSA_DATE, EM.RECEIPTDATE, EM.STATUS, EM.KENSASITU_ID AS JISSIKENSASITU_ID, EM.EXAMSTARTDATE, EM.EXAMENDDATE "
	//	//        + "FROM ORDERMAINTABLE OM, EXMAINTABLE EM ";
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End

	//	// 検査日(受付実施後はExMainTable、実施前はOrderMainTableの検査日を条件とする)
	//	if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
	//		param.GetExecutePeriodEndDate()   != Timestamp.MinValue &&
	//		param.GetUnKnownDateBool())
	//	{
	//		// 検査日or日未定
	//		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);
	//
	//		retStr += " WHERE ((OM.RIS_ID = EM.RIS_ID ";
	//		retStr += " AND     EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'";
	//		retStr += " AND     ((EM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//		retStr += " AND       EM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ";
	//		retStr += " OR        EM.KENSA_DATE = " + unknownDateStr + ")) ";
	//		retStr += " OR     (OM.RIS_ID = EM.RIS_ID ";
	//		retStr += " AND     EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'";
	//		retStr += " AND     ((OM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//		retStr += " AND       OM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ";
	//		retStr += " OR        OM.KENSA_DATE = " + unknownDateStr + "))) ";
	//	}
	//	else if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
	//		     param.GetExecutePeriodEndDate()   != Timestamp.MinValue)
	//	{
	//		// 検査日
	//		retStr += " WHERE ((OM.RIS_ID = EM.RIS_ID ";
	//		retStr += " AND     EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'";
	//		retStr += " AND     EM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//		retStr += " AND     EM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ";
	//		retStr += " OR     (OM.RIS_ID = EM.RIS_ID ";
	//		retStr += " AND     EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'";
	//		retStr += " AND     OM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//		retStr += " AND     OM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ")) ";
	//	}
	//	else
	//	{
	//		// 日未定
	//		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);
	//
	//		retStr += " WHERE ((OM.RIS_ID = EM.RIS_ID ";
	//		retStr += " AND     EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'";
	//		retStr += " AND     EM.KENSA_DATE = " + unknownDateStr + ") ";
	//		retStr += " OR     (OM.RIS_ID = EM.RIS_ID ";
	//		retStr += " AND     EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'";
	//		retStr += " AND     OM.KENSA_DATE = " + unknownDateStr + ")) ";
	//	}
	//
	//	//検索条件の追加
	//	// 患者ID
	//	if (param.GetKanjaIDOnlyBool())
	//	{
	//		retStr += "AND OM.KANJA_ID = '" + param.GetKanjaID() + "' ";
	//	}
	//	// コメント
	//	////retStr = "SELECT " // 2010.11.09 Mod K.Shinohara
	//	//retStr = "SELECT  /*+ RULE */  "
	//	//        + "OM.RIS_ID, OM.KANJA_ID, OM.KENSATYPE_ID, OM.KENSA_DATE AS ORDER_KENSA_DATE, OM.KENSA_STARTTIME, OM.KENSASITU_ID AS YOTEIKENSASITU_ID, "
	//	//        + "EM.KENSA_DATE AS EX_KENSA_DATE, EM.RECEIPTDATE, EM.STATUS, EM.KENSASITU_ID AS JISSIKENSASITU_ID, EM.EXAMSTARTDATE, EM.EXAMENDDATE "
	//	//        + "FROM ORDERMAINTABLE OM, EXMAINTABLE EM "
	//	//        + "WHERE OM.RIS_ID = EM.RIS_ID ";
	//
	//	////検索条件の追加
	//	//// 患者ID
	//	//if (param.GetKanjaIDOnlyBool())
	//	//{
	//	//    retStr += "AND OM.KANJA_ID = '" + param.GetKanjaID() + "' ";
	//	//}
	//
	//	//// 検査日(受付実施後はExMainTable、実施前はOrderMainTableの検査日を条件とする)
	//	//if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
	//	//    param.GetExecutePeriodEndDate()   != Timestamp.MinValue &&
	//	//    param.GetUnKnownDateBool())
	//	//{
	//	//    // 検査日or日未定
	//	//    String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);
	//
	//	//    retStr += " AND ((EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'";
	//	//    retStr += " AND   ((EM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//	//    retStr += " AND     EM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ";
	//	//    retStr += " OR     EM.KENSA_DATE = " + unknownDateStr + ")) ";
	//	//    retStr += " OR   (EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'";
	//	//    retStr += " AND   ((OM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//	//    retStr += " AND     OM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ";
	//	//    retStr += " OR     OM.KENSA_DATE = " + unknownDateStr + "))) ";
	//	//}
	//	//else if (param.GetExecutePeriodStartDate() != Timestamp.MinValue &&
	//	//         param.GetExecutePeriodEndDate()   != Timestamp.MinValue)
	//	//{
	//	//    // 検査日
	//	//    retStr += " AND ((EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'";
	//	//    retStr += " AND   EM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//	//    retStr += " AND   EM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ") ";
	//	//    retStr += " OR   (EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'";
	//	//    retStr += " AND   OM.KENSA_DATE >= " + param.GetExecutePeriodStartDate().toString("yyyyMMdd");
	//	//    retStr += " AND   OM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd") + ")) ";
	//	//}
	//	//else
	//	//{
	//	//    // 日未定
	//	//    String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);
	//
	//	//    retStr += " AND ((EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'";
	//	//    retStr += " AND   EM.KENSA_DATE = " + unknownDateStr + ") ";
	//	//    retStr += " OR   (EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'";
	//	//    retStr += " AND   OM.KENSA_DATE = " + unknownDateStr + ")) ";
	//	//}
	//
	//
	//	logger.debug("end");
	//
	//	return retStr;
	//}
	//2010.10.29 Add K.Shinohara End
}
