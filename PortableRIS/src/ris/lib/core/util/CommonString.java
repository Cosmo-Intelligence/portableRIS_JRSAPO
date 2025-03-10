package ris.lib.core.util;


/// <summary>
///
/// 定義情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.02.19	: 112478 / A.Kobayashi		: original
/// V2.01.002		: 2010.09.03	: 999999 / DD.Chinh			: KUM-2.4
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo		: KANRO-R-9
/// V2.04.001 		: 2011.01.24	: 999999 / DD.Chinh			: KANRO-R-19 KANRO-R-22
/// V2.04.001		: 2011.02.16    extends 999999 / DD.Chinh			: KANRO-R-27
/// V2.06.010		: 2011.05.19	: 999999 / T.Ootsuka		: KG-2-X02
/// V2.01.00		: 2011.07.11    extends 999999 / NSK_H.Hashimoto	: NML-CAT1-002
/// V2.01.00		: 2011.07.29	: 999999 / T.Wada			: NML-CAT2-030
/// V2.01.00		: 2011.07.29    extends 999999 / NSK_T.Koudate	: NML-CAT2-004
/// V2.01.00		: 2011.08.01	: 999999 / T.Wada			: NML-CAT2-001
/// V2.01.00		: 2011.08.05	: 999999 / K.Aizawa			: NML-CAT3-034 NML-CAT2-010 NML-CAT3-036 NML-CAT3-035
/// V2.01.00		: 2011.08.22	: 999999 / T.Nishikubo		: NML-2-X01
/// V2.01.01.13000  extends 2011.11.11	: 999999 / NSK_M.Ochiai		: OMH-1-9
/// V2.01.01.13000	: 2011.11.14    extends 999999 / NSK_H.Hashimoto	: OMH-1-04
/// V2.01.01.13000  extends 2011.11.21	: 999999 / NSK_M.Ochiai		: OMH-1-7
/// V2.01.01.13000  extends 2011.11.21	: 999999 / NSK_M.Ochiai		: OMH-1-10
/// V2.01.01.13000  extends 2011.11.25	: 999999 / T.Wada			: OMH-1-9
///	V2.01.01.13000	: 2011.11.22	: 999999 / NSK_M.Ochiai		: OMH-1-2
///
/// </summary>
public  final class CommonString
{
	public static final String RIS_MODE					= "診断";

	public static final String STUDY_INSTANCE_UID_HEADER   = "1.2.392.200045.6960.4.7";

	//バージョン情報
	public static final String		VERSION_TITLE			= "RadiQuest/RIS V";
	public static final String		VERSION					= "2.01.01.13000";

	//病院識別ID
	public static final String SYSID_KANRO					= "KANRO";	//関東労災	2010.10.04
	public static final String SYSID_OMH					= "OMH";	//大垣市民	2011.11

	public static final String KENSATYPEID_RQRIS			= "RQ/RIS";
	public static final String KENSATYPEID_RQRIS_S			= "RQ/RIS_S"; //撮影系
	public static final String KENSATYPEID_RQRIS_K			= "RQ/RIS_K"; //検査系
	public static final int	TERMINAL_BASE_ID			= 0;

	public static final String SORT_ASC					= " ASC";
	public static final String SORT_DESC					= " DESC";

	public static final String DATA_TYPE_VARCHAR2			= "VARCHAR2";

	public static final String LOGIN_PASSWORD_WARN			= "1-";

	//画面サイズタイプ
	public static final String DISPLAYSIZE_TYPE_A			= "typeA";
	public static final String DISPLAYSIZE_TYPE_B			= "typeB";

	//ボタン画像
	public static final String MENUBUTTON_IMAGE_YELLOW		= "Yellow";
	public static final String MENUBUTTON_IMAGE_RED		= "Red";
	public static final String MENUBUTTON_IMAGE_BLUE		= "Blue";

	//ボタンID
	public static final String MENUBUTTON_ID_B1			= "B1";	//予定･実績参照
	public static final String MENUBUTTON_ID_E1			= "E1";	//プレチェック
	public static final String MENUBUTTON_ID_E2			= "E2";	//プレチェック指示詳細
	public static final String MENUBUTTON_ID_C1			= "C1";	//RISｵｰﾀﾞ登録
	public static final String MENUBUTTON_ID_D1			= "D1";	//受　付
	public static final String MENUBUTTON_ID_D2			= "D2";	//受付確認ダイアログ
	public static final String MENUBUTTON_ID_G1			= "G1";	//撮影系
	public static final String MENUBUTTON_ID_G2			= "G2";	//撮影業務詳細
	public static final String MENUBUTTON_ID_H1			= "H1";	//検査系
	public static final String MENUBUTTON_ID_H2			= "H2";	//検査業務詳細
	public static final String MENUBUTTON_ID_J1			= "J1";	//ｵﾍﾟ室･ﾎﾟｰﾀﾌﾞﾙ
	public static final String MENUBUTTON_ID_K1			= "K1";	//核医学
	public static final String MENUBUTTON_ID_F1			= "F1";	//検像一覧
	public static final String MENUBUTTON_ID_F2			= "F2";	//検像業務詳細
	public static final String MENUBUTTON_ID_P1			= "P1";	//検査台帳
	public static final String MENUBUTTON_ID_R1			= "R1";	//統　計

	//2011.11.11 Add NSK_M.Ochiai Start extends OMH-1-9
	public static final String MENUBUTTON_ID_V1            = "V1";	//クローズ予約
	//2011.11.11 Add NSK_M.Ochiai End

	// 2010.06.23 Add T.Nishikubo Start
	public static final String MENUBUTTON_ID_T1			= "T1";	//業務日誌
	// 2010.06.23 Add T.Nishikubo End
	//
	public static final String MENUBUTTON_ID_X3			= "X3";	//患者履歴一覧

	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	public static final String MENUBUTTON_ID_U1			= "U1";	//掲示板
	// 2011.02.16 Add T.Nishikubo End

	// 2011.08.05 Add K.Aizawa@MERX Start NML-CAT3-034
	public static final String MENUBUTTON_ID_I1			= "I1";	//業務３
	// 2011.08.05 Add K.Aizawa@MERX End

	//一覧アイテムID
	public static final Integer LIST_ITEM_ID_NO								= 1;		//No
	public static final Integer LIST_ITEM_ID_SELECT							= 2;		//選択
	public static final Integer LIST_ITEM_ID_GYOUMU_KBN_NAME				= 4;		//区
	public static final Integer LIST_ITEM_ID_STATUS_NAME					= 5;		//進捗
	public static final Integer LIST_ITEM_ID_KENSA_STARTTIME_STRING			= 6;		//予約
	public static final Integer LIST_ITEM_ID_RECEIPTDATE2_STRING			= 7;		//受付
	public static final Integer LIST_ITEM_ID_RECEIPTNUMBER					= 8;		//受付番号
	public static final Integer LIST_ITEM_ID_KENSA_DATE_STRING				= 9;		//検査日
	public static final Integer LIST_ITEM_ID_KANJA_SYOKAI_FLG_NAME			= 10;		//紹介
	public static final Integer LIST_ITEM_ID_DOUISHO_FLG_NAME				= 11;		//同意書
	public static final Integer LIST_ITEM_ID_YUUSEN_FLG_NAME				= 12;		//優先
	public static final Integer LIST_ITEM_ID_KANJA_ID						= 13;		//患者ID
	public static final Integer LIST_ITEM_ID_KANASIMEI						= 14;		//患者氏名
	public static final Integer LIST_ITEM_ID_KENSA_DATE_AGE_STRING			= 15;		//年齢
	public static final Integer LIST_ITEM_ID_SEX_NAME						= 16;		//性
	public static final Integer LIST_ITEM_ID_TRANSPORTTYPE_TITLE			= 17;		//状態
	public static final Integer LIST_ITEM_ID_IRAI_SECTION					= 18;		//依頼科
	public static final Integer LIST_ITEM_ID_BYOUTOU						= 19;		//病棟
	public static final Integer LIST_ITEM_ID_KENSATYPE_NAME					= 20;		//検査種別
	public static final Integer LIST_ITEM_ID_BUI_NAME						= 21;		//部位
	public static final Integer LIST_ITEM_ID_KENSAHOUHOU_NAME				= 22;		//方法
	public static final Integer LIST_ITEM_ID_HOUKOU_NAME					= 23;		//方向
	public static final Integer LIST_ITEM_ID_TAKENSA_FLG_NAME				= 24;		//本日他検査
	public static final Integer LIST_ITEM_ID_IRAI_DOCTOR_NAME				= 25;		//依頼医
	public static final Integer LIST_ITEM_ID_KANJYA_SECTION					= 26;		//所属科
	public static final Integer LIST_ITEM_ID_KENSA_SIJI						= 27;		//依頼目的
	public static final Integer LIST_ITEM_ID_BUICOMMENT						= 28;		//撮影指示ｺﾒﾝﾄ
	public static final Integer LIST_ITEM_ID_SIKYU_FLG_NAME					= 29;		//至急
	public static final Integer LIST_ITEM_ID_RENRAKU_MEMO					= 30;		//連絡メモ
	public static final Integer LIST_ITEM_ID_SIJI_ISI_COMMENT				= 31;		//指示医師コメント
	public static final Integer LIST_ITEM_ID_SIJI_ISI_NAME					= 32;		//指示医師
	public static final Integer LIST_ITEM_ID_YOTEIKENSAROOM					= 33;		//予定検査機器
	public static final Integer LIST_ITEM_ID_KENSAROOM						= 34;		//実績検査機器
	public static final Integer LIST_ITEM_ID_INPUTDATE_STRING				= 35;		//入力
	public static final Integer LIST_ITEM_ID_INPUTTIME_STRING				= 36;		//時間
	public static final Integer LIST_ITEM_ID_KENSA_GISI_NAME				= 38;		//最終入力者
	public static final Integer LIST_ITEM_ID_UKETUKE_TANTOU_NAME			= 39;		//受付者
	public static final Integer LIST_ITEM_ID_SEISAN_FLG_NAME				= 40;		//精算
	public static final Integer LIST_ITEM_ID_SYOTISITU_FLG_NAME				= 41;		//処置室
	public static final Integer LIST_ITEM_ID_DOKUEI_FLG_NAME				= 42;		//読影フラグ
	public static final Integer LIST_ITEM_ID_KANJISIMEI						= 43;		//患者氏名(漢字)
	public static final Integer LIST_ITEM_ID_ROMASIMEI						= 44;		//患者氏名(ローマ字)
	public static final Integer LIST_ITEM_ID_RI_ORDER_FLG_NAME				= 45;		//RI区分
	public static final Integer LIST_ITEM_ID_KENSATYPE_COMMENT				= 46;		//患者コメント
	public static final Integer LIST_ITEM_ID_PATIENTCOMMENT					= 47;		//検査種別コメント
	public static final Integer LIST_ITEM_ID_KAKUHO							= 48;		//確保
	public static final Integer LIST_ITEM_ID_ORDERCOMMENT_ID_NAME			= 49;		//オーダコメント
	public static final Integer LIST_ITEM_ID_KENZOUSTATUS_FLG_NAME			= 51;		//検像進捗
	public static final Integer LIST_ITEM_ID_KENZOUKINKYUU_FLG_NAME			= 52;		//検像緊急
	public static final Integer LIST_ITEM_ID_SATUEI_COUNT					= 53;		//撮影数
	public static final Integer LIST_ITEM_ID_KENZOU_TANTOU_NAME				= 54;		//検像担当
	public static final Integer LIST_ITEM_ID_BYOUSITU						= 55;		//病室名
	public static final Integer LIST_ITEM_ID_EXAMSTARTDATE_STRING			= 58;		//検査開始時刻
	public static final Integer LIST_ITEM_ID_EXAMENDDATE_STRING				= 59;		//検査終了時刻
	public static final Integer LIST_ITEM_ID_YOTEIKENSASITU_NAME			= 62;		//予定検査室
	public static final Integer LIST_ITEM_ID_JISSIKENSASITU_NAME			= 63;		//実施検査室
	public static final Integer LIST_ITEM_ID_KANJA_NYUGAIKBN_STRING			= 64;		//患者入外
	public static final Integer LIST_ITEM_ID_DENPYO_NYUGAIKBN_STRING		= 65;		//伝票入外
	public static final Integer LIST_ITEM_ID_DENPYO_BYOUTOU_NAME			= 66;		//伝票病棟
	public static final Integer LIST_ITEM_ID_ACNO							= 67;		//AccessionNo
	public static final Integer LIST_ITEM_ID_SYSTEMKBN_STRING				= 68;		//オーダ区分
	public static final Integer LIST_ITEM_ID_REMARKS						= 71;		//備考
	public static final Integer LIST_ITEM_ID_IRAIDOCTOR_TEL					= 72;		//依頼医連絡先
	public static final Integer LIST_ITEM_ID_IRAISECTION_TEL				= 73;		//依頼科連絡先
	public static final Integer LIST_ITEM_ID_BYOUTOU_TEL					= 74;		//病棟連絡先
	public static final Integer LIST_ITEM_ID_DOUISHO_CHECK_FLG				= 92;		//同意書ﾁｪｯｸﾌﾗｸ
	public static final Integer LIST_ITEM_ID_DOUISHO_CHECK_NAME				= 93;		//同意書ﾁｪｯｸ者
	public static final Integer LIST_ITEM_ID_INFECTION						= 94;		//感染症
	public static final Integer LIST_ITEM_ID_ORDER_DATE_STRING				= 95;		//オーダ日時
	public static final Integer LIST_ITEM_ID_INFECTION_CHECK_FLG_NAME		= 102;		//感染症ﾁｪｯｸﾌﾗｸﾞ
	public static final Integer LIST_ITEM_ID_INFECTION_CHECK_NAME			= 103;		//感染症ﾁｪｯｸ者
	public static final Integer LIST_ITEM_ID_KANJA_WAIT_STATE				= 109;		//患者待ち状態
	public static final Integer LIST_ITEM_ID_KANJA_WAIT_TIME				= 110;		//患者待ち時間
	public static final Integer LIST_ITEM_ID_TODAY_IRAI_ORDER				= 111;		//当日依頼ｵｰﾀﾞ
	public static final Integer LIST_ITEM_ID_ZOUEI_FLG						= 112;		//要造影表示
	public static final Integer LIST_ITEM_ID_YOBIDASI_DATE_STRING			= 119;		//呼出時刻
	public static final Integer LIST_ITEM_ID_KENZOU_DATE_STRING				= 120;		//検像時刻
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	public static final Integer LIST_ITEM_ID_PHARMA_CHECK_FLG				= 149;		//薬剤発注ﾁｪｯｸﾌﾗｸ
	public static final Integer LIST_ITEM_ID_PHARMA_CHECK_NAME				= 150;		//薬剤発注ﾁｪｯｸ者
	// 2010.11.16 Add T.Nishikubo End
	public static final Integer LIST_ITEM_ID_PRECHECK_STATUS				= 154;		//ﾌﾟﾚﾁｪｯｸ進捗		// 2011.01.24 Add DD.Chinh KANRO-R-19
	//
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
	public static final Integer LIST_ITEM_ID_SATUEISTATUS_NAME				= 167;		//部位進捗
	// 2011.08.05 Add H.Satou@MERX End
	public static final Integer LIST_ITEM_ID_HIDE							= 5000;		//隠しカラム 2011.07.28 Add H.Orikasa A0083
	// 2011.08.04 Add NSK_T.Koudate Start NML-CAT2-004
	public static final Integer LIST_ITEM_ID_JISISYA_NAME					= 153;		//検査責任者
	public static final Integer LIST_ITEM_ID_MED_PERSON_NAME				= 172;		//担当者
	// 2011.08.04 Add NSK_T.Koudate End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	public static final Integer LIST_ITEM_ID_EXAM_TIMING_LABEL				= 195;		//オーダ種別
	// 2011.11.22 Add NSK_M.Ochiai End

	//
	public static final Integer LIST_ITEM_ID_RIS_ID							= 5000;		//RIS_ID
	public static final Integer LIST_ITEM_ID_GYOUMU_KBN						= 5001;		//区
	public static final Integer LIST_ITEM_ID_STATUS							= 5002;		//進捗ＩＤ
	public static final Integer LIST_ITEM_ID_KENSA_STARTTIME				= 5003;		//予約
	public static final Integer LIST_ITEM_ID_RECEIPTDATE2					= 5004;		//受付
	public static final Integer LIST_ITEM_ID_KENSA_DATE						= 5005;		//検査日
	public static final Integer LIST_ITEM_ID_KANJA_SYOKAI_FLG				= 5006;		//紹介フラグ
	public static final Integer LIST_ITEM_ID_DOUISHO_FLG					= 5007;		//同意書フラグ
	public static final Integer LIST_ITEM_ID_YUUSEN_FLG						= 5008;		//優先フラグ
	public static final Integer LIST_ITEM_ID_KENSA_DATE_AGE					= 5009;		//年齢（数値）
	public static final Integer LIST_ITEM_ID_SEX							= 5010;		//性別
	public static final Integer LIST_ITEM_ID_TRANSPORTTYPE					= 5011;		//状態ID
	public static final Integer LIST_ITEM_ID_IRAI_SECTION_ID				= 5012;		//依頼科ID
	public static final Integer LIST_ITEM_ID_IRAI_SECTION_RYAKU_NAME		= 5013;		//依頼科略名称
	public static final Integer LIST_ITEM_ID_BYOUTOU_ID						= 5014;		//病棟ID
	public static final Integer LIST_ITEM_ID_BYOUTOU_RYAKU_NAME				= 5015;		//病棟略名称
	public static final Integer LIST_ITEM_ID_KENSATYPE_ID					= 5016;		//検査種別ID
	public static final Integer LIST_ITEM_ID_KENSATYPE_RYAKU_NAME			= 5017;		//検査種別略名称
	public static final Integer LIST_ITEM_ID_BUI_ID							= 5018;		//部位ID
	public static final Integer LIST_ITEM_ID_BUI_RYAKU_NAME					= 5019;		//部位略名称
	public static final Integer LIST_ITEM_ID_HOUKOU_ID						= 5020;		//方法ID
	public static final Integer LIST_ITEM_ID_HOUKOU_RYAKU_NAME				= 5021;		//方法略名称
	public static final Integer LIST_ITEM_ID_KENSAHOUHOU_ID					= 5022;		//方向ID
	public static final Integer LIST_ITEM_ID_KENSAHOUHOU_RYAKU_NAME			= 5023;		//方向略名称
	public static final Integer LIST_ITEM_ID_TAKENSA_FLG					= 5024;		//本日他検査フラグ
	public static final Integer LIST_ITEM_ID_IRAI_DOCTOR_ID					= 5025;		//依頼医ID
	public static final Integer LIST_ITEM_ID_KANJYA_SECTION_ID				= 5026;		//所属科ID
	public static final Integer LIST_ITEM_ID_KANJYA_SECTION_RYAKU_NAME 		= 5027;		//所属科略名称
	public static final Integer LIST_ITEM_ID_SIKYU_FLG						= 5028;		//至急
	public static final Integer LIST_ITEM_ID_SIJI_ISI_ID					= 5029;		//指示医師ID
	public static final Integer LIST_ITEM_ID_YOTEIKENSAROOM_ID				= 5030;		//予定検査機器ID
	public static final Integer LIST_ITEM_ID_YOTEIKENSAROOM_RYAKU_NAME	 	= 5031;		//予定検査機器略名称
	public static final Integer LIST_ITEM_ID_KENSAROOM_ID					= 5032;		//実施検査機器ID
	public static final Integer LIST_ITEM_ID_KENSAROOM_RYAKU_NAME			= 5033;		//実施検査機器略名称
	public static final Integer LIST_ITEM_ID_INPUTDATE						= 5034;		//入力
	public static final Integer LIST_ITEM_ID_INPUTTIME						= 5035;		//時間
	public static final Integer LIST_ITEM_ID_KENSA_GISI_ID					= 5036;		//実施技師ID
	public static final Integer LIST_ITEM_ID_UKETUKE_TANTOU_ID				= 5037;		//受付者ID
	public static final Integer LIST_ITEM_ID_SEISAN_FLG						= 5038;		//精算フラグ
	public static final Integer LIST_ITEM_ID_SYOTISITU_FLG					= 5039;		//処置室フラグ
	public static final Integer LIST_ITEM_ID_DOKUEI_FLG						= 5040;		//読影フラグ
	public static final Integer LIST_ITEM_ID_RI_ORDER_FLG					= 5041;		//RI区分
	public static final Integer LIST_ITEM_ID_ORDERCOMMENT_ID				= 5042;		//オーダコメントID
	public static final Integer LIST_ITEM_ID_KENZOUSTATUS_FLG				= 5043;		//検像進捗フラグ
	public static final Integer LIST_ITEM_ID_KENZOUKINKYUU_FLG				= 5044;		//検像緊急フラグ
	public static final Integer LIST_ITEM_ID_BYOUSITU_ID					= 5045;		//病室名ID
	public static final Integer LIST_ITEM_ID_BYOUSITU_RYAKU_NAME			= 5046;		//病室略名称
	public static final Integer LIST_ITEM_ID_EXAMSTARTDATE					= 5047;		//検査開始時刻
	public static final Integer LIST_ITEM_ID_EXAMENDDATE					= 5048;		//検査終了時刻
	public static final Integer LIST_ITEM_ID_YOTEIKENSASITU_ID				= 5049;		//予定検査室ID
	public static final Integer LIST_ITEM_ID_YOTEIKENSASITU_RYAKU_NAME		= 5050;		//予定検査室略名称
	public static final Integer LIST_ITEM_ID_JISSIKENSASITU_ID				= 5051;		//実施検査室ID
	public static final Integer LIST_ITEM_ID_JISSIKENSASITU_RYAKU_NAME		= 5052;		//実施検査室略名称
	public static final Integer LIST_ITEM_ID_KANJA_NYUGAIKBN				= 5053;		//患者入外
	public static final Integer LIST_ITEM_ID_DENPYO_NYUGAIKBN				= 5054;		//伝票入外
	public static final Integer LIST_ITEM_ID_DENPYO_BYOUTOU_ID				= 5055;		//伝票病棟ID
	public static final Integer LIST_ITEM_ID_DENPYO_BYOUTOU_RYAKU_NAME		= 5056;		//伝票病棟略名称
	public static final Integer LIST_ITEM_ID_SYSTEMKBN						= 5057;		//オーダ区分
	public static final Integer LIST_ITEM_ID_MODALITY_TYPE					= 5058;		//モダリティタイプ
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
	public static final Integer LIST_ITEM_ID_SATUEISTATUS					= 5059;		//部位進捗(CODE)
	public static final Integer LIST_ITEM_ID_SATUEISTATUS_SHOWORDER			= 5060;		//部位進捗(SHOWORDER)
	// 2011.08.05 Add H.Satou@MERX End
	// コメント
	//public static final Integer LIST_ITEM_ID_RIS_ID						= 200;		//RIS_ID
	//public static final Integer LIST_ITEM_ID_GYOUMU_KBN					= 201;		//区
	//public static final Integer LIST_ITEM_ID_STATUS						= 202;		//進捗ＩＤ
	//public static final Integer LIST_ITEM_ID_KENSA_STARTTIME				= 203;		//予約
	//public static final Integer LIST_ITEM_ID_RECEIPTDATE2					= 204;		//受付
	//public static final Integer LIST_ITEM_ID_KENSA_DATE					= 205;		//検査日
	//public static final Integer LIST_ITEM_ID_KANJA_SYOKAI_FLG				= 206;		//紹介フラグ
	//public static final Integer LIST_ITEM_ID_DOUISHO_FLG					= 207;		//同意書フラグ
	//public static final Integer LIST_ITEM_ID_YUUSEN_FLG					= 208;		//優先フラグ
	//public static final Integer LIST_ITEM_ID_KENSA_DATE_AGE				= 209;		//年齢（数値）
	//public static final Integer LIST_ITEM_ID_SEX							= 210;		//性別
	//public static final Integer LIST_ITEM_ID_TRANSPORTTYPE				= 211;		//状態ID
	//public static final Integer LIST_ITEM_ID_IRAI_SECTION_ID				= 212;		//依頼科ID
	//public static final Integer LIST_ITEM_ID_IRAI_SECTION_RYAKU_NAME		= 213;		//依頼科略名称
	//public static final Integer LIST_ITEM_ID_BYOUTOU_ID					= 214;		//病棟ID
	//public static final Integer LIST_ITEM_ID_BYOUTOU_RYAKU_NAME			= 215;		//病棟略名称
	//public static final Integer LIST_ITEM_ID_KENSATYPE_ID					= 216;		//検査種別ID
	//public static final Integer LIST_ITEM_ID_KENSATYPE_RYAKU_NAME			= 217;		//検査種別略名称
	//public static final Integer LIST_ITEM_ID_BUI_ID						= 218;		//部位ID
	//public static final Integer LIST_ITEM_ID_BUI_RYAKU_NAME				= 219;		//部位略名称
	//public static final Integer LIST_ITEM_ID_HOUKOU_ID					= 220;		//方法ID
	//public static final Integer LIST_ITEM_ID_HOUKOU_RYAKU_NAME			= 221;		//方法略名称
	//public static final Integer LIST_ITEM_ID_KENSAHOUHOU_ID				= 222;		//方向ID
	//public static final Integer LIST_ITEM_ID_KENSAHOUHOU_RYAKU_NAME		= 223;		//方向略名称
	//public static final Integer LIST_ITEM_ID_TAKENSA_FLG					= 224;		//本日他検査フラグ
	//public static final Integer LIST_ITEM_ID_IRAI_DOCTOR_ID				= 225;		//依頼医ID
	//public static final Integer LIST_ITEM_ID_KANJYA_SECTION_ID			= 226;		//所属科ID
	//public static final Integer LIST_ITEM_ID_KANJYA_SECTION_RYAKU_NAME 	= 227;		//所属科略名称
	//public static final Integer LIST_ITEM_ID_SIKYU_FLG					= 228;		//至急
	//public static final Integer LIST_ITEM_ID_SIJI_ISI_ID					= 229;		//指示医師ID
	//public static final Integer LIST_ITEM_ID_YOTEIKENSAROOM_ID			= 230;		//予定検査機器ID
	//public static final Integer LIST_ITEM_ID_YOTEIKENSAROOM_RYAKU_NAME 	= 231;		//予定検査機器略名称
	//public static final Integer LIST_ITEM_ID_KENSAROOM_ID					= 232;		//実施検査機器ID
	//public static final Integer LIST_ITEM_ID_KENSAROOM_RYAKU_NAME			= 233;		//実施検査機器略名称
	//public static final Integer LIST_ITEM_ID_INPUTDATE					= 234;		//入力
	//public static final Integer LIST_ITEM_ID_INPUTTIME					= 235;		//時間
	//public static final Integer LIST_ITEM_ID_KENSA_GISI_ID				= 236;		//実施技師ID
	//public static final Integer LIST_ITEM_ID_UKETUKE_TANTOU_ID			= 237;		//受付者ID
	//public static final Integer LIST_ITEM_ID_SEISAN_FLG					= 238;		//精算フラグ
	//public static final Integer LIST_ITEM_ID_SYOTISITU_FLG				= 239;		//処置室フラグ
	//public static final Integer LIST_ITEM_ID_DOKUEI_FLG					= 240;		//読影フラグ
	//public static final Integer LIST_ITEM_ID_RI_ORDER_FLG					= 241;		//RI区分
	//public static final Integer LIST_ITEM_ID_ORDERCOMMENT_ID				= 242;		//オーダコメントID
	//public static final Integer LIST_ITEM_ID_KENZOUSTATUS_FLG				= 243;		//検像進捗フラグ
	//public static final Integer LIST_ITEM_ID_KENZOUKINKYUU_FLG			= 244;		//検像緊急フラグ
	//public static final Integer LIST_ITEM_ID_BYOUSITU_ID					= 245;		//病室名ID
	//public static final Integer LIST_ITEM_ID_BYOUSITU_RYAKU_NAME			= 246;		//病室略名称
	//public static final Integer LIST_ITEM_ID_EXAMSTARTDATE				= 247;		//検査開始時刻
	//public static final Integer LIST_ITEM_ID_EXAMENDDATE					= 248;		//検査終了時刻
	//public static final Integer LIST_ITEM_ID_YOTEIKENSASITU_ID			= 249;		//予定検査室ID
	//public static final Integer LIST_ITEM_ID_YOTEIKENSASITU_RYAKU_NAME	= 250;		//予定検査室略名称
	//public static final Integer LIST_ITEM_ID_JISSIKENSASITU_ID			= 251;		//実施検査室ID
	//public static final Integer LIST_ITEM_ID_JISSIKENSASITU_RYAKU_NAME	= 252;		//実施検査室略名称
	//public static final Integer LIST_ITEM_ID_KANJA_NYUGAIKBN				= 253;		//患者入外
	//public static final Integer LIST_ITEM_ID_DENPYO_NYUGAIKBN				= 254;		//伝票入外
	//public static final Integer LIST_ITEM_ID_DENPYO_BYOUTOU_ID			= 255;		//伝票病棟ID
	//public static final Integer LIST_ITEM_ID_DENPYO_BYOUTOU_RYAKU_NAME	= 256;		//伝票病棟略名称
	//public static final Integer LIST_ITEM_ID_SYSTEMKBN					= 257;		//オーダ区分
	//public static final Integer LIST_ITEM_ID_MODALITY_TYPE				= 258;		//モダリティタイプ


	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	public static final Integer LIST_ITEM_ID_EXAM_TIMING					= 5061;		//オーダ種別
	// 2011.11.22 Add NSK_M.Ochiai End

	//部位グリッドアイテムID
	public static final Integer BUI_LIST_ITEM_ID_BUI_NO_NAME				= 1;		//部位No
	public static final Integer BUI_LIST_ITEM_ID_BUI_NAME					= 2;		//部位
	public static final Integer BUI_LIST_ITEM_ID_HOUKOU_NAME				= 3;		//方向
	public static final Integer BUI_LIST_ITEM_ID_SAYUU_NAME					= 4;		//左右
	public static final Integer BUI_LIST_ITEM_ID_KENSAHOUHOU_NAME			= 5;		//検査方法
	public static final Integer BUI_LIST_ITEM_ID_SATUEISTATUS_NAME			= 6;		//進捗
	public static final Integer BUI_LIST_ITEM_ID_BUICOMMENT					= 7;		//部位コメント
	public static final Integer BUI_LIST_ITEM_ID_MARK						= 8;		//マーク
	public static final Integer BUI_LIST_ITEM_ID_BUIBUNRUI_NAME				= 9;		//部位分類
	public static final Integer BUI_LIST_ITEM_ID_BUISET_NAME				= 206;		//部位セット名称
	//
	public static final Integer BUI_LIST_ITEM_ID_KENSAHOUHOU_ID				= 201;		//検査方法ID
	public static final Integer BUI_LIST_ITEM_ID_BUI_ID						= 202;		//部位ID
	public static final Integer BUI_LIST_ITEM_ID_HOUKOU_ID					= 203;		//方向ID
	public static final Integer BUI_LIST_ITEM_ID_SAYUU_ID					= 204;		//左右ID
	public static final Integer BUI_LIST_ITEM_ID_SEQ						= 205;		//連番
	public static final Integer BUI_LIST_ITEM_ID_BUICOMMENT2				= 207;		//部位コメント２
	public static final Integer BUI_LIST_ITEM_ID_UNSEND_FLG					= 208;		//未送信フラグ
	public static final Integer BUI_LIST_ITEM_ID_HIS_ORIGINAL_FLG			= 209;		//HISオリジナルフラグ
	public static final Integer BUI_LIST_ITEM_ID_BUIORDER_NO				= 210;		//部位ｵｰﾀﾞ番号
	public static final Integer BUI_LIST_ITEM_ID_SATUEISTATUS				= 211;		//進捗ID
	public static final Integer BUI_LIST_ITEM_ID_FILM_ID					= 212;		//フィルムID
	public static final Integer BUI_LIST_ITEM_ID_BUI_NO						= 213;		//部位No
	public static final Integer BUI_LIST_ITEM_ID_MULTISELECT				= 214;		//複数行選択
	public static final Integer BUI_LIST_ITEM_ID_HIS_ORIGINAL_FLG_NAME		= 215;		//HISマーク
	public static final Integer BUI_LIST_ITEM_ID_BUISET_ID					= 216;		//部位セットID

	//撮影グリッドアイテムID(共通)
	public static final Integer SATUEI_LIST_ITEM_ID_BUI_NO_NAME				= 1;		//部位No
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEISTATUS			= 2;		//進捗
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEIMENU_NAME			= 3;		//撮影メニュー
	public static final Integer SATUEI_LIST_ITEM_ID_KENSAKIKI_NAME			= 4;		//実施装置
	public static final Integer SATUEI_LIST_ITEM_ID_FILM_NAME				= 5;		//フィルム種
	public static final Integer SATUEI_LIST_ITEM_ID_PARTITION				= 6;		//分割枚数
	public static final Integer SATUEI_LIST_ITEM_ID_USED					= 7;		//枚数
	public static final Integer SATUEI_LIST_ITEM_ID_RESHOT_FLG_NAME			= 8;		//再撮
	public static final Integer SATUEI_LIST_ITEM_ID_MARK					= 9;		//選択
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEIMENU_NAME_KANA	= 10;		//カナ撮影名称
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEI_CODE_NAME		= 11;		//撮影コード
	public static final Integer SATUEI_LIST_ITEM_ID_LOSS					= 14;		//ロス
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEIKIKI_NAME			= 15;		//撮影装置	// 2010.10.04 Add Y.Shibata KANRO-R-1
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA01				= 101;		//EXAMDATA01
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA02				= 102;		//EXAMDATA02
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA03				= 103;		//EXAMDATA03
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA04				= 104;		//EXAMDATA04
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA05				= 105;		//EXAMDATA05
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA06				= 106;		//EXAMDATA06
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA07				= 107;		//EXAMDATA07
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA08				= 108;		//EXAMDATA08
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA09				= 109;		//EXAMDATA09
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA10				= 110;		//EXAMDATA10
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA11				= 111;		//EXAMDATA11
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA12				= 112;		//EXAMDATA12
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA13				= 113;		//EXAMDATA13
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA14				= 114;		//EXAMDATA14
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA15				= 115;		//EXAMDATA15
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA16				= 116;		//EXAMDATA16
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA17				= 117;		//EXAMDATA17
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA18				= 118;		//EXAMDATA18
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA19				= 119;		//EXAMDATA19
	public static final Integer SATUEI_LIST_ITEM_ID_EXAMDATA20				= 120;		//EXAMDATA20
	//撮影グリッドアイテムID(撮影系)
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEI_CODE1			= 201;		//撮影装置コード1
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEI_CODE2			= 202;		//撮影装置コード2
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEIMENU_NAME_KANA2	= 203;		//カナ撮影名称
	public static final Integer SATUEI_LIST_ITEM_ID_SEQ						= 204;		//連番
	public static final Integer SATUEI_LIST_ITEM_ID_BUI_NO					= 205;		//部位NO
	public static final Integer SATUEI_LIST_ITEM_ID_DBFLG					= 206;		//DBフラグ
	public static final Integer SATUEI_LIST_ITEM_ID_SEQ_DISP				= 207;		//表示連番
	public static final Integer SATUEI_LIST_ITEM_ID_MODE					= 208;		//状態
	public static final Integer SATUEI_LIST_ITEM_ID_SEQ_OLD					= 209;		//元連番
	public static final Integer SATUEI_LIST_ITEM_ID_MULTISELECT				= 210;		//複数行選択
	public static final Integer SATUEI_LIST_ITEM_ID_FILM_ID					= 211;		//フィルムID
	public static final Integer SATUEI_LIST_ITEM_ID_RESHOT_FLG				= 212;		//再撮
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEIMENU_ID			= 213;		//撮影メニューID
	public static final Integer SATUEI_LIST_ITEM_ID_SATUEI_CODE				= 214;		//撮影コード
	public static final Integer SATUEI_LIST_ITEM_ID_KENSAKIKI_ID			= 215;		//実施装置ID
	public static final Integer SATUEI_LIST_ITEM_ID_CASSETTE_NO				= 216;		//カセッテNO
	public static final Integer SATUEI_LIST_ITEM_ID_PORTABLE_STATUS			= 217;		//ポータブル進捗
	public static final Integer SATUEI_LIST_ITEM_ID_MPPS_INST_ID			= 218;		//MPPS_INST_ID
	public static final Integer SATUEI_LIST_ITEM_ID_MPPS_VM_NO				= 219;		//MPPS_VM_NO

	//撮影グリッドアイテムID(検査系)
	public static final Integer INSPECT_LIST_ITEM_ID_KENSAKIKI_ID			= 201;		//実施装置ID
	public static final Integer INSPECT_LIST_ITEM_ID_SEQ					= 202;		//連番
	public static final Integer INSPECT_LIST_ITEM_ID_BUI_NO					= 203;		//部位NO
	public static final Integer INSPECT_LIST_ITEM_ID_DBFLG					= 204;		//DBフラグ
	public static final Integer INSPECT_LIST_ITEM_ID_MULTISELECT			= 205;		//複数行選択
	public static final Integer INSPECT_LIST_ITEM_ID_DELFLG					= 206;		//削除フラグ
	public static final Integer INSPECT_LIST_ITEM_ID_MPPS_INST_ID			= 391;		//MPPS_INST_ID
	public static final Integer INSPECT_LIST_ITEM_ID_MPPS_VM_NO				= 392;		//MPPS_VM_NO
	public static final Integer INSPECT_LIST_ITEM_ID_MPPS_SATUEI_CODE		= 393;		//MPPS_Satuei_Code
	public static final Integer INSPECT_LIST_ITEM_ID_MPPS_AE_TITLE			= 394;		//MPPS_AE_TITLE

	//撮影グリッドアイテムID(検査系MPPS)
	public static final Integer INSPECT_LIST_ITEM_ID_KVP					= 301;		//X線管電圧(KVP)
	public static final Integer INSPECT_LIST_ITEM_ID_MA						= 302;		//X線管電流(mA)
	public static final Integer INSPECT_LIST_ITEM_ID_SEC					= 303;		//曝射時間(sec)
	public static final Integer INSPECT_LIST_ITEM_ID_MIN					= 304;		//曝射時間(min)
	public static final Integer INSPECT_LIST_ITEM_ID_MAS					= 305;		//mAs値
	public static final Integer INSPECT_LIST_ITEM_ID_KV						= 306;		//KV
	public static final Integer INSPECT_LIST_ITEM_ID_EXNO					= 307;		//照射数
	public static final Integer INSPECT_LIST_ITEM_ID_CTDI					= 308;		//CTDI
	public static final Integer INSPECT_LIST_ITEM_ID_DLP					= 309;		//DLP
	public static final Integer INSPECT_LIST_ITEM_ID_FSCOPY					= 310;		//透視時間(min)
	public static final Integer INSPECT_LIST_ITEM_ID_IMGAREA				= 311;		//画像面積線量積
	public static final Integer INSPECT_LIST_ITEM_ID_DDISTMM				= 312;		//線源検出器間距離(mm)
	public static final Integer INSPECT_LIST_ITEM_ID_DDISTCM				= 313;		//線源検出器間距離(cm)
	public static final Integer INSPECT_LIST_ITEM_ID_EDISTMM				= 314;		//線源入射面間距離(mm)
	public static final Integer INSPECT_LIST_ITEM_ID_EDOSEDGY				= 315;		//入射線量(dGy)
	public static final Integer INSPECT_LIST_ITEM_ID_EDOSEMGY				= 316;		//入射面線量(mGy)
	public static final Integer INSPECT_LIST_ITEM_ID_EXAREA					= 317;		//照射面積
	public static final Integer INSPECT_LIST_ITEM_ID_RADIAMODE				= 318;		//放射線モード
	public static final Integer INSPECT_LIST_ITEM_ID_FILTERTYPE				= 319;		//フィルタタイプ
	public static final Integer INSPECT_LIST_ITEM_ID_FILTERMATERIAL			= 320;		//フィルタ材料

	//
	//器材手技グリッドアイテムID
	public static final Integer ITEM_LIST_ITEM_ID_MARK						= 1;  		//選択
	public static final Integer ITEM_LIST_ITEM_ID_KBN_NAME					= 2;  		//区分(器材分類)
	public static final Integer ITEM_LIST_ITEM_ID_BUI_NO_NAME				= 3;		//部位No
	public static final Integer ITEM_LIST_ITEM_ID_NAME						= 4;		//名称
	public static final Integer ITEM_LIST_ITEM_ID_SUURYOU_IJI				= 5;		//医事数
	public static final Integer ITEM_LIST_ITEM_ID_UNIT_IJI					= 6;		//医事単位
	public static final Integer ITEM_LIST_ITEM_ID_SUURYOU					= 7;		//実際数
	public static final Integer ITEM_LIST_ITEM_ID_UNIT						= 8;		//実際単位
	public static final Integer ITEM_LIST_ITEM_ID_KBN_TYPE					= 901;		//区分タイプ
	public static final Integer ITEM_LIST_ITEM_ID_KBN_ID					= 902;		//区分ID
	public static final Integer ITEM_LIST_ITEM_ID_KBN_NAME2					= 903;		//区分(器材分類)2
	public static final Integer ITEM_LIST_ITEM_ID_DETAILPARTSBUNRUI_ID		= 904;		//詳細区分ID
	public static final Integer ITEM_LIST_ITEM_ID_DETAILPARTSBUNRUI_NAME	= 905;		//詳細区分名称
	public static final Integer ITEM_LIST_ITEM_ID_ID						= 906;		//器材ID
	public static final Integer ITEM_LIST_ITEM_ID_NAME2						= 907;		//器材名称2
	public static final Integer ITEM_LIST_ITEM_ID_SEQ						= 908;		//連番
	public static final Integer ITEM_LIST_ITEM_ID_BUI_NO					= 909;		//部位No
	public static final Integer ITEM_LIST_ITEM_ID_DBFLG						= 910;		//DBフラグ
	public static final Integer ITEM_LIST_ITEM_ID_BARCODEDATA				= 911;		//バーコード
	public static final Integer ITEM_LIST_ITEM_ID_UPDATE_FLG				= 912;		//更新フラグ

	//フィルムグリッドアイテムID
	public static final Integer FILM_LIST_ITEM_ID_BUI_NO_NAME				= 1;  		//部位No
	public static final Integer FILM_LIST_ITEM_ID_FILM_NAME					= 2;  		//フィルム種
	public static final Integer FILM_LIST_ITEM_ID_PARTITION					= 3;  		//分割
	public static final Integer FILM_LIST_ITEM_ID_USED						= 4;  		//枚数
	public static final Integer FILM_LIST_ITEM_ID_LOSS						= 5;  		//ロス
	public static final Integer FILM_LIST_ITEM_ID_FILM_ID					= 200;  	//フィルムID
	public static final Integer FILM_LIST_ITEM_ID_BUI_ID					= 201;  	//部位ID
	public static final Integer FILM_LIST_ITEM_ID_BUI_NO					= 202;  	//部位No
	public static final Integer FILM_LIST_ITEM_ID_MULTISELECT				= 203;  	//複数行選択
	public static final Integer FILM_LIST_ITEM_ID_DBFLG						= 204;  	//DBフラグ
	public static final Integer FILM_LIST_ITEM_ID_DELFLG					= 205;  	//削除フラグ

	//履歴グリッドアイテムID
	public static final Integer HISTORY_ITEM_ID_KENSA_DATE					= 1;		//検査日
	public static final Integer HISTORY_ITEM_ID_STATUS_NAME					= 2;		//検査進捗
	public static final Integer HISTORY_ITEM_ID_KENSATYPE_NAME				= 3;		//検査種別
	public static final Integer HISTORY_ITEM_ID_BUI_NAME					= 4;		//部位名称
	public static final Integer HISTORY_ITEM_ID_ZOUEIZAI_NAME				= 5;		//使用造影剤名称
	public static final Integer HISTORY_ITEM_ID_IRAI_SECTION				= 6;		//依頼科
	public static final Integer HISTORY_ITEM_ID_IRAI_DOCTOR_NAME			= 7;		//依頼医
	public static final Integer HISTORY_ITEM_ID_KANJA_NYUGAIKBN_STRING		= 8;		//患者入外
	public static final Integer HISTORY_ITEM_ID_BYOUTOU						= 9;		//患者病棟
	public static final Integer HISTORY_ITEM_ID_HOUKOU_NAME					= 10;		//方向
	public static final Integer HISTORY_ITEM_ID_KENSAHOUHOU_NAME			= 11;		//検査方法
	public static final Integer HISTORY_ITEM_ID_EXAMSTARTDATE_STRING		= 12;		//検査開始時刻
	public static final Integer HISTORY_ITEM_ID_EXAMENDDATE_STRING			= 13;		//検査終了時刻
	public static final Integer HISTORY_ITEM_ID_KENSA_STARTTIME_STRING		= 14;		//予約時刻
	public static final Integer HISTORY_ITEM_ID_SHOW_IMAGE					= 15;		//画像参照
	public static final Integer HISTORY_ITEM_ID_SHOW_REPORT					= 16;		//ﾚﾎﾟｰﾄ参照
	public static final Integer HISTORY_ITEM_ID_SHOW_SCHEMA					= 17;		//ｼｪｰﾏ参照
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
	public static final Integer HISTORY_ITEM_ID_SATUEISTATUS_NAME			= 20;		//部位進捗
	// 2011.08.05 Add H.Satou@MERX End
	//
	public static final Integer HISTORY_ITEM_ID_KENSATYPE_RYAKU_NAME		= 200;		//検査種別略名称
	public static final Integer HISTORY_ITEM_ID_BUI_RYAKU_NAME				= 201;		//部位略名称
	public static final Integer HISTORY_ITEM_ID_HOUKOU_RYAKU_NAME			= 202;		//方法略名称
	public static final Integer HISTORY_ITEM_ID_KENSAHOUHOU_RYAKU_NAME		= 203;		//方向略名称
	public static final Integer HISTORY_ITEM_ID_ZOUEIZAI_RYAKU_NAME			= 204;		//使用造影剤略名称
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
	public static final Integer HISTORY_ITEM_ID_SATUEISTATUS				= 205;		//部位進捗(CODE)
	public static final Integer HISTORY_ITEM_ID_SATUEISTATUS_SHOWORDER		= 206;		//部位進捗(ソート)
	// 2011.08.05 Add H.Satou@MERX End

	//管理項目グリッドアイテムID
	public static final Integer EXAMDATA_ITEM_ID_01						= 101;		//管理項目01
	public static final Integer EXAMDATA_ITEM_ID_02						= 102;		//管理項目02
	public static final Integer EXAMDATA_ITEM_ID_03						= 103;		//管理項目03
	public static final Integer EXAMDATA_ITEM_ID_04						= 104;		//管理項目04
	public static final Integer EXAMDATA_ITEM_ID_05						= 105;		//管理項目05
	public static final Integer EXAMDATA_ITEM_ID_06						= 106;		//管理項目06
	public static final Integer EXAMDATA_ITEM_ID_07						= 107;		//管理項目07
	public static final Integer EXAMDATA_ITEM_ID_08						= 108;		//管理項目08
	public static final Integer EXAMDATA_ITEM_ID_09						= 109;		//管理項目09
	public static final Integer EXAMDATA_ITEM_ID_10						= 110;		//管理項目10
	public static final Integer EXAMDATA_ITEM_ID_11						= 111;		//管理項目11
	public static final Integer EXAMDATA_ITEM_ID_12						= 112;		//管理項目12
	public static final Integer EXAMDATA_ITEM_ID_13						= 113;		//管理項目13
	public static final Integer EXAMDATA_ITEM_ID_14						= 114;		//管理項目14
	public static final Integer EXAMDATA_ITEM_ID_15						= 115;		//管理項目15
	public static final Integer EXAMDATA_ITEM_ID_16						= 116;		//管理項目16
	public static final Integer EXAMDATA_ITEM_ID_17						= 117;		//管理項目17
	public static final Integer EXAMDATA_ITEM_ID_18						= 118;		//管理項目18
	public static final Integer EXAMDATA_ITEM_ID_19						= 119;		//管理項目19
	public static final Integer EXAMDATA_ITEM_ID_20						= 120;		//管理項目20

	// マスタ固定値
	public static final String IS_OFF = "0";
	public static final String IS_ON = "1";

	//入外区分
	public static final String IS_GAIRAI						= "1";
	public static final String IS_NYUUIN						= "2";
	public static final String IS_KINKYU						= "3";

	//性別
	public static final String IS_MELE							= "M";
	public static final String IS_FEMALE						= "F";
	public static final String IS_OTHER							= "O";
	public static final String SEX_TYPE_1						= "1"; //男,女
	// 2010.10.26 Mod K.Shinohara Start
	public static final String SEX_TYPE_2						= "2"; //男性,女性
	//public static final String SEX_TYPE_2						= "1"; //男性,女性
	// 2010.10.26 Mod K.Shinohara End

	public static final String AGE_FORMAT = "歳";

	// 優先
	public static final String IS_NONE_MSG						= "";
	public static final String IS_SYOU_MSG						= "承";
	public static final String IS_YUU_MSG						= "優";
	public static final String IS_NONE							= "0";
	public static final String IS_SYOU							= "1";
	public static final String IS_YUU							= "2";

	//確保
	public static final String IS_KAKUHO_THIS					= "●";
	public static final String IS_KAKUHO_OTHER					= "○";

	//ソート条件デフォルト文字
	public static final String SORT_DEFAULT_STR				= "★";

	//項目変換テーブル識別用ID
	public static final String CODECONVERT_ID_GYOMUKBN			= "GYOMUKBN";		//業務区分
	public static final String CODECONVERT_ID_ZOUEIKBN			= "ZOUEIKBN";		//造影
	public static final String CODECONVERT_ID_TRANSPORT			= "TRANSPORT";		//患者移動情報
	public static final String CODECONVERT_ID_KANJASYOKAI		= "SYOKAI";			//紹介
	public static final String CODECONVERT_ID_DOUISHO			= "DOUISHO";		//同意書
	public static final String CODECONVERT_ID_YUUSEN			= "YUUSEN";			//優先
	public static final String CODECONVERT_ID_SIKYU				= "SIKYU";			//至急
	public static final String CODECONVERT_ID_SEISAN			= "SEISAN";			//精算
	public static final String CODECONVERT_ID_SYOTISITU			= "SYOTISITU";		//処置室
	public static final String CODECONVERT_ID_DOKUEI			= "DOKUEI";			//読影
	public static final String CODECONVERT_ID_RIORDER			= "RIORDER";		//RI区分
	public static final String CODECONVERT_ID_KENZOUSTATUS		= "KZOUSTATUS";		//検像進捗
	public static final String CODECONVERT_ID_KENZOUKINKYUU		= "KZOUKINKYU";		//検像緊急
	public static final String CODECONVERT_ID_SYSTEMKBN			= "SYSTEMKBN";		//オーダ区分
	public static final String CODECONVERT_ID_INOUT				= "INOUT";			//入外
	public static final String CODECONVERT_ID_SEX				= "SEX";			//性別
	public static final String CODECONVERT_ID_ISITATIAI			= "ISITATIAI";		//放科医師立会い区分
	public static final String CODECONVERT_ID_PORTABLE			= "PORTABLE";		//ポータブル区分
	public static final String CODECONVERT_ID_ZOUEI_FLG			= "ZOUEI_FLG";		//造影　■マーク
	public static final String CODECONVERT_ID_TODAYIRAI			= "TODAYIRAI";		//当日依頼
	public static final String CODECONVERT_ID_KANJAWAIT			= "KANJAWAIT";		//患者待ち時間
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
	public static final String CODECONVERT_ID_BUISTATUS			= "BUISTATUS";		//部位進捗
	// 2011.08.05 Add H.Satou@MERX End

	//サーバモード
	public static final String UNITY_CA_MODE					= "SERV_NML";
	public static final String UNITY_EMG_CA_MODE				= "SERV_EMG";
	public static final String LOCAL_CA_MODE					= "LOCAL";

	//文字コードのエンコード指定
	public static final int	ENCODE_MS932						= 932;
	public static final String ENCODE_DEF						= "Shift_JIS";	// 2011.08.23 Add H.Orikasa NML-CAT9-020

	//受付フラグ
	public static final String RECEIPT_FLG_ON					= "ON";
	public static final String RECEIPT_FLG_OFF					= "OF";

		//排他
	public static final String APPID							= "UR";
	public static final String ACCESSMODE						= "1";

	//用紙ｻｲｽﾞ制御ﾌﾗｸﾞ
	public static final String PRINT_OUTPUTTYPE_ON				= "ON";
	public static final String PRINT_OUTPUTTYPE_ON_STR			= "設定する";
	public static final String PRINT_OUTPUTTYPE_OFF				= "OFF";

	// 2010.07.30 Add DD.Chinh Start
	//デフォルトプリンタ
	public static final String DEFAULT_PRINTER_ON				= "ON";
	public static final String DEFAULT_PRINTER_ON_STR			= "設定する";
	// 2010.07.30 Add DD.Chinh End

	//COMポート使用機器定義
	public static final Integer COMPORT_TYPE_NOTHING				= 0;	//未使用
	public static final Integer COMPORT_TYPE_CARD					= 1;	//カードリーダ
	public static final Integer COMPORT_TYPE_IDT					= 2;	//IDT通信

	//ﾀｯﾁﾊﾟﾈﾙ対応指定
	public static final String TACHPANEL_FLAG_ON				= "1";	//ﾀｯﾁﾊﾟﾈﾙ
	public static final String TACHPANEL_FLAG_OFF				= "0";	//標準ﾊﾟﾈﾙ

	//メニューボタンID
	public static final String MENUBUTTON_ID_CLOSE				= "SYSEND";	//終了ボタン

	//オーダ日=検査日
	public static final String ORDERDATE_FLG_ON					= "ON";

	//部位展開表示
	public static final String BUI_DISPLAY_MODE_ON				= "ON";

	//未割当フラグ
	public static final String ID_EMPTY_FLG_ON					= "ON";

	//表示検査種別
	public static final String KENSATYPE_SHOW_FLG_ON			= "ON";
	public static final String KENSATYPE_SHOW_FLG_PHOTO			= "1";	//撮影系
	public static final String KENSATYPE_SHOW_FLG_PORTABLE		= "2";	//ポータブル
	public static final String KENSATYPE_SHOW_FLG_INSPECT		= "3";	//検査系
	public static final String KENSATYPE_SHOW_FLG_NUCLEAR		= "4";	//核医学

	//検査種別初期タブ
	public static final String KENSATYPE_DEFTAB1_CODE			= "T1";	//依頼詳細
	public static final String KENSATYPE_DEFTAB2_CODE			= "T2"; //患者詳細
	public static final String KENSATYPE_DEFTAB3_CODE			= "T3"; //検査履歴

	//実績詳細区分
	public static final String OPE_KBN_PHOTO					= "1";	//撮影
	public static final String OPE_KBN_INSPECT					= "2";	//検査

	//表示モード
	public static final String OPE_SHOW_MODE_REF				= "0";	//参照
	public static final String OPE_SHOW_MODE_MOD				= "1";	//更新
	public static final String OPE_SHOW_MODE_REFHISTRY			= "2";	//参照(履歴なし）

	//患者属性
	public static final int	PATIENT_MARK_ON						= 1;	//有効

	//患者無効年齢
	public static final String	PATIENT_NULL_AGE				= "999";

	//システムパラメータ
	public static final String SYSTEMPARAM_0					= "0";
	public static final String SYSTEMPARAM_1					= "1";
	public static final String SYSTEMPARAM_2					= "2";

	//固定 1 or 2
	public static final String VALEU_1                         = "1";
	public static final String VALEU_2                         = "2";
	public static final String VALEU_3                         = "3";

	//プルダウン「なし」用ID
	public static final String PULLDOWN_NULL_ID				= "0";

	//左右使用ﾌﾗｸﾞ
	public static final String	SAYUU_FLG_ON					= "1";	//使用

	//メニューNO文字列
	public static final String SATUEIMENU_NO_STRING				= "No.";
	public static final String ITEM_NO_STRING					= "No.";

	//数値入力タイプ
	public static final Integer INPUT_NUMBER_TYPE_ITEM				= 0;
	public static final Integer INPUT_NUMBER_TYPE_SATUEI			= 1;
	public static final Integer INPUT_NUMBER_TYPE_FILM				= 2;
	public static final Integer INPUT_NUMBER_TYPE_NORMAL			= 3;
	// 2011.03.19 Add K.Shinohara A0002
	public static final Integer INPUT_NUMBER_TYPE_INFUSE			= 4;

	public static final Integer INPUT_NUMBER_MAX_FILM				= 99;		//2桁Max
	public static final Integer INPUT_NUMBER_MAX_INFUSE				= 999999;	//6桁Max
	public static final Integer INPUT_NUMBER_MAX_SATUEI				= 99999999;	//8桁Max

	//検査種別フィルタフラグMAXサイズ
	public static final Integer KENSATYPEFILTER_LENGTH				= 64;

	//器材区分
	public static final int	ITEM_KBN_PARTS						= 0;			//器材
	public static final int	ITEM_KBN_INFUSE						= 1;			//手技
	public static final String ITEM_KBN_DUMMY_ID				= "-1";			//ダミー区分ID
	public static final String ITEM_KBN_DUMMY_NAME				= "区分不明";	//ダミー区分名称

	//DBフラグ
	public static final String DB_FLG_ON						= "1";
	public static final String DB_FLG_OFF						= "0";

	//選択マーク
	public static final String SELECT_MARK						= "●";

	//器材用マーク
	public static final String ITEM_MARK_ON						= "■";
	public static final String ITEM_MARK_OFF					= "□";

	//MWM一括出力
	public static final String MULTI_MWM_NOSEND_STRING			= "(出力しない)";

	//一覧タイプ
	public static final String OPE_LIST_TYPE_BUI				= "0";	//部位一覧
	public static final String OPE_LIST_TYPE_SATUEI				= "1";	//撮影一覧
	public static final String OPE_LIST_TYPE_ITEM				= "2";	//器材一覧

	//カラムタイプ
	public static final String COLUMN_TYPE_FILM					= "0";
	public static final String COLUMN_TYPE_STRING				= "1";
	public static final String COLUMN_TYPE_COMBO				= "2";
	public static final String COLUMN_TYPE_NUMBER				= "3";
	public static final String COLUMN_TYPE_NUMBERCOMBO			= "4";

	//HIS送信 TOHIS
	public static final String TOHIS_ORDER_RECEPT						= "RC01"; //受付通知
	public static final String TOHIS_ORDER_RECEPT_STR					= "RC01:[受付通知]";
	public static final String TOHIS_ORDER_ABORT_RECEPT					= "RC99"; //受付取消通知
	public static final String TOHIS_ORDER_ABORT_RECEPT_STR				= "RC99:[受付取消通知]";
	public static final String TOHIS_ORDER_EXECUTE						= "OP01"; //実施通知
	public static final String TOHIS_ORDER_EXECUTE_STR					= "OP01:[実施通知]";
	public static final String TOHIS_ORDER_RE_EXECUTE					= "OP02"; //実施通知（再送）
	public static final String TOHIS_ORDER_RE_EXECUTE_STR				= "OP02:[実施通知（再送）]";
	public static final String TOHIS_ORDER_ABORT_EXECUTE				= "OP99"; //中止通知
	public static final String TOHIS_ORDER_ABORT_EXECUTE_STR			= "OP99:[中止通知]";
	public static final String TOHIS_PATIENT							= "PR01"; //患者情報取得要求
	public static final String TOHIS_PATIENT_STR						= "PR01:[患者情報取得要求]";
	public static final String TOHIS_PATIENT_PROFILE					= "PR21"; //患者プロファイル情報取得要求
	public static final String TOHIS_PATIENT_PROFILE_STR				= "PR21:[患者プロファイル情報取得要求]";
	public static final String TOHIS_ORDER								= "OR01"; //ｵｰﾀﾞ取得要求
	public static final String TOHIS_ORDER_STR							= "OR01:[ｵｰﾀﾞ取得要求]";

	/* 削除予定
	public static final String ORDER_CREATE_NOTIFICATION				= "OC01"; //新規照射予約作成
	public static final String ORDER_CREATE_NOTIFICATION_STR			= "OC01:[新規照射作成]";
	public static final String ORDER_MODIFY_NOTIFICATION				= "OC02"; //既存照射予約更新
	public static final String ORDER_MODIFY_NOTIFICATION_STR			= "OC02:[既存照射更新]";
	public static final String SATUEI_CREATE_NOTIFICATION				= "OC31"; //新規撮影予約作成
	public static final String SATUEI_CREATE_NOTIFICATION_STR			= "OC31:[新規撮影作成]";
	public static final String SATUEI_MODIFY_NOTIFICATION				= "OC32"; //既存撮影予約更新
	public static final String SATUEI_MODIFY_NOTIFICATION_STR			= "OC32:[既存撮影更新]";
	public static final String SATUEI_DELETE_NOTIFICATION				= "OC98"; //撮影予約削除
	public static final String SATUEI_DELETE_NOTIFICATION_STR			= "OC98:[撮影削除]";
	public static final String ORDER_DELETE_NOTIFICATION				= "OC99"; //既存照射予約削除
	public static final String ORDER_DELETE_NOTIFICATION_STR			= "OC99:[既存照射削除]";
	public static final String SATUEI_RECEPTION_NOTIFICATION			= "RP01"; //撮影予約受付通知
	public static final String SATUEI_RECEPTION_NOTIFICATION_STR		= "RP01:[撮影受付通知]";
	public static final String SATUEI_RECEPTION_ABORT_NOTIFICATION		= "RP98"; //撮影予約受付取消通知
	public static final String SATUEI_RECEPTION_ABORT_NOTIFICATION_STR	= "RP98:[撮影受付取消通知]";
	public static final String SATUEI_NOTIFICATION						= "OP31"; //撮影実施通知
	public static final String SATUEI_NOTIFICATION_STR					= "OP31:[撮影実施通知]";
	public static final String SATUEI_RENOTIFICATION					= "OP32"; //撮影実施通知（再送時）
	public static final String SATUEI_RENOTIFICATION_STR				= "OP32:[撮影実施通知]";
	public static final String SATUEI_EXECUTE_ABORT_NOTIFICATION		= "OP98"; //撮影中止通知
	public static final String SATUEI_EXECUTE_ABORT_NOTIFICATION_STR	= "OP98:[撮影中止通知]";
	*/

	//HIS受信 FROMHIS
	public static final String FROMHIS_ORDER							= "OI01";
	public static final String FROMHIS_ORDER_STR						= "OI01:[ｵｰﾀﾞ情報]";
	public static final String FROMHIS_ORDER_CHANGE						= "OI02";
	public static final String FROMHIS_ORDER_CHANGE_STR					= "OI02:[ｵｰﾀﾞ変更]";
	public static final String FROMHIS_ORDER_CANCEL						= "OI99";
	public static final String FROMHIS_ORDER_CANCEL_STR					= "OI99:[ｵｰﾀﾞｷｬﾝｾﾙ]";
	public static final String FROMHIS_PATIENT							= "PI01";
	public static final String FROMHIS_PATIENT_STR						= "PI01:[患者情報]";
	public static final String FROMHIS_DEATH							= "PI99";
	public static final String FROMHIS_DEATH_STR						= "PI99:[死亡退院情報]";
	public static final String FROMHIS_ACCOUNT							= "AC01";
	public static final String FROMHIS_ACCOUNT_STR						= "AC01:[会計通知]";

	//HIS受信 TOREPORT
	public static final String TOREPORT_ORDER							= "OI01";
	public static final String TOREPORT_ORDER_STR						= "OI01:[ｵｰﾀﾞ（予約）]";
	public static final String TOREPORT_ORDER_CANCEL					= "OI99";
	public static final String TOREPORT_ORDER_CANCEL_STR				= "OI99:[ｵｰﾀﾞｷｬﾝｾﾙ]";
	public static final String TOREPORT_ORDER_RECEPT					= "RC01";
	public static final String TOREPORT_ORDER_RECEPT_STR				= "RC01:[受付]";
	public static final String TOREPORT_ORDER_ABORT_RECEPT				= "RC99";
	public static final String TOREPORT_ORDER_ABORT_RECEPT_STR			= "RC99:[受付取消]";
	public static final String TOREPORT_ORDER_EXECUTE					= "OP01";
	public static final String TOREPORT_ORDER_EXECUTE_STR				= "OP01:[検査完了]";
	public static final String TOREPORT_ORDER_RE_EXECUTE				= "OP02";
	public static final String TOREPORT_ORDER_RE_EXECUTE_STR			= "OP02:[検査完了（再送）]";
	public static final String TOREPORT_ORDER_ABORT_EXECUTE				= "OP99";
	public static final String TOREPORT_ORDER_ABORT_EXECUTE_STR			= "OP99:[検査中止]";

	//HIS受信 TOPACS
	public static final String TOPACS_ORDER								= "OI01";
	public static final String TOPACS_ORDER_STR							= "OI01:[ｵｰﾀﾞ（予約）]";
	public static final String TOPACS_ORDER_CANCEL						= "OI99";
	public static final String TOPACS_ORDER_CANCEL_STR					= "OI99:[ｵｰﾀﾞｷｬﾝｾﾙ]";
	public static final String TOPACS_ORDER_RECEPT						= "RC01";
	public static final String TOPACS_ORDER_RECEPT_STR					= "RC01:[受付]";
	public static final String TOPACS_ORDER_ABORT_RECEPT				= "RC99";
	public static final String TOPACS_ORDER_ABORT_RECEPT_STR			= "RC99:[受付取消]";
	public static final String TOPACS_ORDER_EXECUTE						= "OP01";
	public static final String TOPACS_ORDER_EXECUTE_STR					= "OP01:[検査完了]";
	public static final String TOPACS_ORDER_RE_EXECUTE					= "OP02";
	public static final String TOPACS_ORDER_RE_EXECUTE_STR				= "OP02:[検査完了（再送）]";
	public static final String TOPACS_ORDER_ABORT_EXECUTE				= "OP99";
	public static final String TOPACS_ORDER_ABORT_EXECUTE_STR			= "OP99:[検査中止]";

	//実績操作履歴情報ﾃｰﾌﾞﾙ EXAMOPERATIONHISTORY
	//操作種別
	//01：RISｵｰﾀﾞ登録 02：呼出　03：再呼　04：遅刻09：ＲＩＳ ｵｰﾀﾞｷｬﾝｾﾙ10：受付 11：取消　12：再受付　13：HIS受付送信
	//20：検査開始　21：検査保留　22：実績保存23：実績登録　24：HIS実績送信
	//881：所見送信(予約)　882：所見送信(受済)883：所見送信(保留)　884：所見送信(実績)
	//891：PACS送信(予約)　892：PACS送信(受済)893：PACS送信(保留)　894：PACS送信(実績)
	public static final String OPERATIONTYPE_ORDER							=  "1";
	public static final String OPERATIONTYPE_ORDER_STR						=  "1[RISｵｰﾀﾞ登録]";
	public static final Integer    OPERATIONTYPE_CALL_INT						=   2;
	public static final String OPERATIONTYPE_CALL							=  "2";
	public static final String OPERATIONTYPE_CALL_STR						=  "2[呼出]";
	public static final Integer    OPERATIONTYPE_RE_CALLL_INT					=   3;
	public static final String OPERATIONTYPE_RE_CALL						=  "3";
	public static final String OPERATIONTYPE_RE_CALLL_STR					=  "3[再呼]";
	public static final Integer    OPERATIONTYPE_LATE_INT						=   4;
	public static final String OPERATIONTYPE_LATE							=  "4";
	public static final String OPERATIONTYPE_LATE_STR						=  "4[遅刻]";
	public static final String OPERATIONTYPE_ORDER_MOD						=  "8";						//未使用
	public static final String OPERATIONTYPE_ORDER_MOD_STR					=  "8[RISｵｰﾀﾞ変更]";		//
	public static final String OPERATIONTYPE_ABORT_ORDER					=  "9";
	public static final String OPERATIONTYPE_ABORT_ORDER_STR				=  "9[RISｵｰﾀﾞｷｬﾝｾﾙ]";
	public static final Integer    OPERATIONTYPE_RECEPT_INT						=  10;
	public static final String OPERATIONTYPE_RECEPT							= "10";
	public static final String OPERATIONTYPE_RECEPT_STR						= "10[受付]";
	public static final Integer    OPERATIONTYPE_ABORT_RECEPT_INT				=  11;
	public static final String OPERATIONTYPE_ABORT_RECEPT					= "11";
	public static final String OPERATIONTYPE_ABORT_RECEPT_STR				= "11[受付取消]";
	public static final Integer    OPERATIONTYPE_RE_RECEPT_INT					=  12;
	public static final String OPERATIONTYPE_RE_RECEPT						= "12";
	public static final String OPERATIONTYPE_RE_RECEPT_STR					= "12[再受付]";
	public static final String OPERATIONTYPE_START_OPERATE					= "20";
	public static final String OPERATIONTYPE_START_OPERATE_STR				= "20[検査開始]";
	public static final String OPERATIONTYPE_HOLD_OPERATE					= "21";
	public static final String OPERATIONTYPE_HOLD_OPERATE_STR				= "21[検査保留]";
	public static final String OPERATIONTYPE_SAVE_OPERATE					= "22";
	public static final String OPERATIONTYPE_SAVE_OPERATE_STR				= "22[実績保存]";
	public static final String OPERATIONTYPE_REGISTER_OPERATE				= "23";
	public static final String OPERATIONTYPE_REGISTER_OPERATE_STR			= "23[実績登録]";
	public static final String OPERATIONTYPE_SEND_OPERATE					= "24";
	public static final String OPERATIONTYPE_SEND_OPERATE_STR				= "24[HIS実績送信]";
	public static final Integer    OPERATIONTYPE_KENSADATE_CHANGE_INT			=  30;						// 2010.07.30 Add T.Ootsuka 検査日時変更
	public static final String OPERATIONTYPE_KENSADATE_CHANGE				= "30";						//
	public static final String OPERATIONTYPE_KENSADATE_CHANGE_STR			= "30[実績時間変更]";		//

	public static final int	OPERATIONTYPE_CHANGE_EXAM_ROOM_INT				=  114;
	public static final String OPERATIONTYPE_CHANGE_EXAM_ROOM				= "114";
	// 2011.07.29 Mod NSK_T.Wada Start NML-CAT2-030
	public static final String OPERATIONTYPE_CHANGE_EXAM_ROOM_STR			= "114[検査室検査機器変更]";
	//
	//public static final String OPERATIONTYPE_CHANGE_EXAM_ROOM_STR			= "114[検査室変更]";

	// 2011.07.29 Mod NSK_T.Wada End
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT2-010
	public static final String OPERATIONTYPE_ISREGISTERED_UNDO				= "117";
	public static final String OPERATIONTYPE_ISREGISTERED_UNDO_STR			= "117[受済戻し]";
	// 2011.08.05 Add H.Satou@MERX End
	public static final Integer    OPERATIONTYPE_PRECHEK_INT					=  136;
	public static final String OPERATIONTYPE_PRECHEK						= "136";
	public static final String OPERATIONTYPE_PRECHEK_STR					= "136[プレチェック登録]";

	// 未使用(聖路加特注版)

	public static final Integer    OPERATIONTYPE_HIS_RECEPT_INT				=  13;
	public static final String OPERATIONTYPE_HIS_RECEPT					= "13";
	public static final String OPERATIONTYPE_HIS_RECEPT_STR				= "13[HIS受付送信]";

	public static final int	OPERATIONTYPE_PRIORITY_INT						=  100;
	public static final String OPERATIONTYPE_PRIORITY						= "100";
	public static final String OPERATIONTYPE_PRIORITY_STR					= "100[優先]";
	public static final int	OPERATIONTYPE_CONFIRM_INT						=  101;
	public static final String OPERATIONTYPE_CONFIRM						= "101";
	public static final String OPERATIONTYPE_CONFIRM_STR					= "101[承認]";
	public static final int	OPERATIONTYPE_SECURE_INT						=  102;
	public static final String OPERATIONTYPE_SECURE							= "102";
	public static final String OPERATIONTYPE_SECURE_STR						= "102[確保]";
	public static final int	OPERATIONTYPE_RETRACT_SECURE_INT				=  103;
	public static final String OPERATIONTYPE_RETRACT_SECURE					= "103";
	public static final String OPERATIONTYPE_RETRACT_SECURE_STR				= "103[確保解除]";
	public static final int	OPERATIONTYPE_MESSAGE_MEMO_INT					=  104;
	public static final String OPERATIONTYPE_MESSAGE_MEMO					= "104";
	public static final String OPERATIONTYPE_MESSAGE_MEMO_STR				= "104[連絡メモ登録]";
	public static final int	OPERATIONTYPE_INFECTION_INT						=  105;
	public static final String OPERATIONTYPE_INFECTION						= "105";
	public static final String OPERATIONTYPE_INFECTION_STR					= "105[感染症チェック]";
	public static final int	OPERATIONTYPE_CANCEL_INFECTION_INT				=  106;
	public static final String OPERATIONTYPE_CANCEL_INFECTION				= "106";
	public static final String OPERATIONTYPE_CANCEL_INFECTION_STR			= "106[感染症チェック解除]";
	public static final int	OPERATIONTYPE_CONSENT_INT						=  107;
	public static final String OPERATIONTYPE_CONSENT						= "107";
	public static final String OPERATIONTYPE_CONSENT_STR					= "107[同意書チェック]";
	public static final int	OPERATIONTYPE_CANCEL_CONSENT_INT				=  108;
	public static final String OPERATIONTYPE_CANCEL_CONSENT					= "108";
	public static final String OPERATIONTYPE_CANCEL_CONSENT_STR				= "108[同意書チェック解除]";
	public static final int	OPERATIONTYPE_EMERGENCY_INT						=  109;
	public static final String OPERATIONTYPE_EMERGENCY						= "109";
	public static final String OPERATIONTYPE_EMERGENCY_STR					= "109[緊急]";
	public static final int	OPERATIONTYPE_SEMI_EMERGENCY_INT				=  110;
	public static final String OPERATIONTYPE_SEMI_EMERGENCY					= "110";
	public static final String OPERATIONTYPE_SEMI_EMERGENCY_STR				= "110[準緊急]";
	public static final int	OPERATIONTYPE_RETRACT_EMERGENCY_INT				=  111;
	public static final String OPERATIONTYPE_RETRACT_EMERGENCY				= "111";
	public static final String OPERATIONTYPE_RETRACT_EMERGENCY_STR			= "111[緊急解除]";
	public static final int	OPERATIONTYPE_CANCEL_INT						=  112;
	public static final String OPERATIONTYPE_CANCEL							= "112";
	public static final String OPERATIONTYPE_CANCEL_STR						= "112[キャンセル]";
	public static final int	OPERATIONTYPE_RETRACT_CANCEL_INT				=  113;
	public static final String OPERATIONTYPE_RETRACT_CANCEL					= "113";
	public static final String OPERATIONTYPE_RETRACT_CANCEL_STR				= "113[キャンセル解除]";
	public static final int	OPERATIONTYPE_LEAVE_INT							=  115;
	public static final String OPERATIONTYPE_LEAVE							= "115";
	public static final String OPERATIONTYPE_LEAVE_STR						= "115[離席]";
	public static final int	OPERATIONTYPE_CANCEL_LEAVE_INT					=  116;
	public static final String OPERATIONTYPE_CANCEL_LEAVE					= "116";
	public static final String OPERATIONTYPE_CANCEL_LEAVE_STR				= "116[離席解除]";
	public static final int	OPERATIONTYPE_REGISTER_CMNT_MESSAGE_INT			=  120;
	public static final String OPERATIONTYPE_REGISTER_CMNT_MESSAGE			= "120";
	public static final String OPERATIONTYPE_REGISTER_CMNT_MESSAGE_STR		= "120[コメント登録(連絡メモ)]";
	public static final int	OPERATIONTYPE_REGISTER_CMNT_PATIENT_INT			=  121;
	public static final String OPERATIONTYPE_REGISTER_CMNT_PATIENT			= "121";
	public static final String OPERATIONTYPE_REGISTER_CMNT_PATIENT_STR		= "121[コメント登録(患者コメント)]";
	public static final int	OPERATIONTYPE_REGISTER_CMNT_EXAM_INT			=  122;
	public static final String OPERATIONTYPE_REGISTER_CMNT_EXAM				= "122";
	public static final String OPERATIONTYPE_REGISTER_CMNT_EXAM_STR			= "122[コメント登録(検査種別コメント)]";
	public static final int	OPERATIONTYPE_REGISTER_CMNT_YELLOW_INT			=  123;
	public static final String OPERATIONTYPE_REGISTER_CMNT_YELLOW			= "123";
	public static final String OPERATIONTYPE_REGISTER_CMNT_YELLOW_STR		= "123[コメント登録(RISイエロー)]";
	public static final int	OPERATIONTYPE_REGISTER_CMNT_ALLERGY_INT			=  124;
	public static final String OPERATIONTYPE_REGISTER_CMNT_ALLERGY			= "124";
	public static final String OPERATIONTYPE_REGISTER_CMNT_ALLERGY_STR		= "124[コメント登録(RISアレルギー)]";
	public static final int	OPERATIONTYPE_REGISTER_CMNT_CARE_INT			=  125;
	public static final String OPERATIONTYPE_REGISTER_CMNT_CARE				= "125";
	public static final String OPERATIONTYPE_REGISTER_CMNT_CARE_STR			= "125[コメント登録(看護注意)]";

	public static final int	OPERATIONTYPE_STARTSHOT_INT						=  130;
	public static final String OPERATIONTYPE_STARTSHOT						= "130";
	public static final String OPERATIONTYPE_STARTSHOT_STR					= "130[検像開始]";
	public static final int	OPERATIONTYPE_ENDSHOT_INT						=  131;
	public static final String OPERATIONTYPE_ENDSHOT						= "131";
	public static final String OPERATIONTYPE_ENDSHOT_STR					= "131[検像終了]";
	public static final int	OPERATIONTYPE_PC_CANCEL_INT						=  132;
	public static final String OPERATIONTYPE_PC_CANCEL						= "132";
	public static final String OPERATIONTYPE_PC_CANCEL_STR					= "132[優先承認解除]";
	public static final Integer    OPERATIONTYPE_LATE_CANCEL_INT				=  133;
	public static final String OPERATIONTYPE_LATE_CANCEL					= "133";
	public static final String OPERATIONTYPE_LATE_CANCEL_STR				= "133[遅刻解除]";
	public static final Integer    OPERATIONTYPE_CALL_CANCEL_INT				=  134;
	public static final String OPERATIONTYPE_CALL_CANCEL					= "134";
	public static final String OPERATIONTYPE_CALL_CANCEL_STR				= "134[呼出解除]";
	public static final Integer    OPERATIONTYPE_ORDERREG_INT					=  135;
	public static final String OPERATIONTYPE_ORDERREG						= "135";
	public static final String OPERATIONTYPE_ORDERREG_STR					= "135[オーダ詳細登録]";
	public static final int	OPERATIONTYPE_CHANGE_PATIENT_SNAPSHOT_INT		=  137;
	public static final String OPERATIONTYPE_CHANGE_PATIENT_SNAPSHOT		= "137";
	public static final String OPERATIONTYPE_CHANGE_PATIENT_SNAPSHOT_STR	= "137[患者スナップショット更新]";



	public static final String OPERATIONTYPE_TOREPORT_ORDER					= "881";
	public static final String OPERATIONTYPE_TOREPORT_ORDER_STR				= "881[所見送信(予約)]";
	public static final String OPERATIONTYPE_TOREPORT_RECEPT				= "882";
	public static final String OPERATIONTYPE_TOREPORT_RECEPT_STR			= "882[所見送信(受済)]";
	public static final String OPERATIONTYPE_TOREPORT_HOLD_OPERATE			= "883";
	public static final String OPERATIONTYPE_TOREPORT_HOLD_OPERATE_STR		= "883[所見送信(保留)]";
	public static final String OPERATIONTYPE_TOREPORT_REGISTER_OPERATE		= "884";
	public static final String OPERATIONTYPE_TOREPORT_REGISTER_OPERATE_STR	= "884[所見送信(実績)]";
	public static final String OPERATIONTYPE_TOPACS_ORDER					= "891";
	public static final String OPERATIONTYPE_TOPACS_ORDER_STR				= "891[PACS送信(予約)]";
	public static final String OPERATIONTYPE_TOPACS_RECEPT					= "892";
	public static final String OPERATIONTYPE_TOPACS_RECEPT_STR				= "892[PACS送信(受済)]";
	public static final String OPERATIONTYPE_TOPACS_HOLD_OPERATE			= "893";
	public static final String OPERATIONTYPE_TOPACS_HOLD_OPERATE_STR		= "893[PACS送信(保留)]";
	public static final String OPERATIONTYPE_TOPACS_REGISTER_OPERATE		= "894";
	public static final String OPERATIONTYPE_TOPACS_REGISTER_OPERATE_STR	= "894[PACS送信(実績)]";

	//ステータス
	public static final String STATUS_UNREGISTERED							= "0";         // 未受付
	public static final String STATUS_ISLATE								= "1";         // 遅刻
	public static final String STATUS_ISCALLING								= "2";         // 呼出中
	public static final String STATUS_ISREGISTERED							= "10";        // 受付済
	public static final String STATUS_INOPERATION							= "20";        // 実施中
	public static final String STATUS_REST									= "21";        // 保留
	public static final String STATUS_RECALLING								= "24";        // 再呼出
	public static final String STATUS_REREGISTERED							= "25";        // 再受付
	public static final String STATUS_ISFINISHED							= "90";        // 実施済
	public static final String STATUS_STOP									= "91";        // 中止
	public static final String STATUS_DELETE								= "-9";			// 削除										// 2011.03.18 Add Yk.Suzuki@CIJ A0005
	public static final String STATUS_DELETE_SAVEPOINT						= "99";			// 削除ステータス検索条件保存位置(100桁目)	//

	//
	//public static final String STATUS_NULL								= "-99";       // NULL
	//public static final String STATUS_ORDERCANCEL							= "9";         // 予約取消
	//public static final String STATUS_HOLD								= "22";        // 検査保留
	//public static final String STATUS_WAIT								= "23";        // 検査待機
	//public static final String STATUS_FINISHED							= "29";        // 検査終了
	//public static final String STATUS_CANCEL								= "92";		   // キャンセル
	//public static final String STATUS_DALETE								= "-9";        // 削除

	//ソート条件
	public static final String SORT_PARAMETER1_MSG	= "マニュアル";
	public static final String SORT_PATTERN_AC		= "AC";
	public static final String SORT_PATTERN_DC		= "DC";

    //権限
	public static final String COMPETENCEID_ORDER		= "1";		//オーダ登録変更操作
	public static final String COMPETENCEID_PRECHECK	= "2";		//プレチェック操作
	public static final String COMPETENCEID_RECEPT		= "3";      //受付操作
	public static final String COMPETENCEID_EX			= "4";		//実施操作
	public static final String COMPETENCEID_EX2			= "5";		//実施データ入力操作
	public static final String COMPETENCEID_LEDGER		= "6";		//検査台帳操作
	public static final String COMPETENCEID_STATISTICS	= "7";		//統計出力操作
	public static final String COMPETENCEID_MASTER		= "50";		//マスタメンテナンス操作
	public static final String COMPETENCEID_IMAGING		= "51";		//検像操作

	//2009.02.20
	public static final String YOKOGAWA_USERID = "YOKOGAWA";        // インストール時に自動作成される管理ユーザ

    //撮影部位の固定値
	public static final String SATUEISTATUS_VALUE					= "0";
	public static final String HIS_ORIGINAL_FLG_OFF_VALUE			= "1";
	public static final String HIS_ORIGINAL_FLG_ON_VALUE			= "1";
	public static final String HIS_ORIGINAL_FLG_ON_STR				= "・";

	//撮影ステータス
	public static final String SATUEISTATUS_MI						= "0";
	public static final String SATUEISTATUS_MI_STR					= "未";
	public static final String SATUEISTATUS_SUMI					= "1";
	public static final String SATUEISTATUS_SUMI_STR				= "済";
	public static final String SATUEISTATUS_STOP					= "2";
	public static final String SATUEISTATUS_STOP_STR				= "中止";

	//再撮影マーク
	public static final String RESHOT_FLG_OFF						= "0";
	public static final String RESHOT_FLG_OFF_STR					= "";
	public static final String RESHOT_FLG_ON						= "1";
	public static final String RESHOT_FLG_ON_STR					= "再";

	//オーダフラグ
	public static final String HIS_ORDER_FLG_VALUE					= "0";
	public static final String RIS_ORDER_FLG_VALUE					= "1";

	//送信フラグ
	public static final String SEND_FLG_VALUE						= "1";

	//画像連携フラグ
	public static final String IMAGE_URL_VALUE						= "1";
	//所見連携フラグ
	public static final String REPORT_URL_VALUE						= "1";
	//ｼｪｰﾏ連携フラグ
	public static final String SCHEMA_URL_VALUE						= "1";

	//連携タイプ
	public static final String URL_TYPE_IMAGE						= "1";	//画像
	public static final String URL_TYPE_REPORT						= "2";	//ﾚﾎﾟｰﾄ
	public static final String URL_TYPE_SCHEMA						= "3";	//ｼｪｰﾏ

	//実績保存フラグ
	public static final String EXMAIN_EXAMSAVE_ON					= "1";
	public static final String EXMAIN_EXAMSAVE_OFF					= "0";

	//使用フラグ
	public static final String USEFLAG_ON							= "1";

	//マスタ用非表示
	public static final String MASTER_USEFLAG_ON		= "1";
	public static final String MASTER_USEFLAG_OFF		= "-1";

	//ﾃﾝﾌﾟﾚｰﾄ区分
	public static final String TEMPLATE_KBN_DRSIJI		= "1";		//指示コメント
	public static final String TEMPLATE_KBN_RENRAKU		= "2";		//連絡メモ
	public static final String TEMPLATE_KBN_JISSI		= "3";		//実施コメント
	// 2010.07.30 Add T.Ootsuka Start
	// テンプレート区分新規追加
	public static final String TEMPLATE_KBN_KANJAKYOTU	= "4";		// 患者共通コメント
	public static final String TEMPLATE_KBN_KENSATYPE	= "5";		// 検査種別コメント
	// 2010.07.30 Add T.Ootsuka End

	//ﾃﾝﾌﾟﾚｰﾄｸﾞﾙｰﾌﾟﾏｽﾀ
	public static final String TEMPLATE_GROUP_DRSIJI1	= "1001";	//撮影範囲
	public static final String TEMPLATE_GROUP_DRSIJI2	= "1002";	//造影有無
	public static final String TEMPLATE_GROUP_DRSIJI3	= "1003";	//特殊撮影
	public static final String TEMPLATE_GROUP_RENRAKU1	= "2001";	//患者状態
	public static final String TEMPLATE_GROUP_RENRAKU2	= "2002";	//メッセージ
	public static final String TEMPLATE_GROUP_RENRAKU3	= "2003";	//技師業務
	public static final String TEMPLATE_GROUP_JISSI1	= "3001";	//だれが
	public static final String TEMPLATE_GROUP_JISSI2	= "3002";	//なにを
	public static final String TEMPLATE_GROUP_JISSI3	= "3003";	//どうした
	// 2010.07.30 Add T.Ootsuka Start
	// テンプレートグループマスタ新規追加
	// 患者共通コメント
	public static final String TEMPLATE_GROUP_KANJAKYOTU1	= "4001";		// 患者状態
	public static final String TEMPLATE_GROUP_KANJAKYOTU2	= "4002";		// メッセージ
	public static final String TEMPLATE_GROUP_KANJAKYOTU3	= "4003";		// 技師業務
	// 検査種別コメント
	public static final String TEMPLATE_GROUP_KENSATYPE1	= "5001";		// 患者状態
	public static final String TEMPLATE_GROUP_KENSATYPE2	= "5002";		// メッセージ
	public static final String TEMPLATE_GROUP_KENSATYPE3	= "5003";		// 技師業務
	// 2010.07.30 Add T.Ootsuka End

	//業務詳細画面コメント欄タイトル
	public static final String OPE_CMT_TITLE_RENRAKU	= "[ 連絡メモ ]";
	public static final String OPE_CMT_TITLE_KANJA		= "[ 患者共通コメント ]";
	public static final String OPE_CMT_TITLE_KANJA2		= "[ 治療RIS 患者共通コメント ]";
	public static final String OPE_CMT_TITLE_KENSATYPE	= "[ 検査種別コメント ]";
	public static final String OPE_CMT_TITLE_BIKOU		= "[ 撮影実施コメント ]";
	public static final String OPE_TEL_TITLE			= "連）";

	//cm,kg単位
	public static final String HEIGHT_UNIT				= "cm";
	public static final String WEIGHT_UNIT				= "kg";

	//プレチェック除外文字列
	public static final String PRECHECK_OUT_STR		= "〆";

	//職種区分
	public static final String SYOKUIN_KBN_DOCTOR		= "1";	//指示医師
	public static final String SYOKUIN_KBN_GISI		= "3";	//検査担当技師
	public static final String SYOKUIN_KBN_KANGO		= "4";	//看護師
	public static final String SYOKUIN_KBN_OTHER		= "5";	//事務その他

	// 2011.07.29 Add NSK_T.Koudate Start NML-CAT2-004
	//職種区分(並び順)
	public static final String SYOKUIN_KBN_ORDER_DOCTOR= "ISI";	//指示医師
	public static final String SYOKUIN_KBN_ORDER_GISI	= "GISI";	//検査担当技師
	public static final String SYOKUIN_KBN_ORDER_KANGO	= "KANGO";	//看護師
	public static final String SYOKUIN_KBN_ORDER_OTHER	= "JIMU";	//事務その他
	// 2011.07.29 Add NSK_T.Koudate End

	//診療日区分（診療時間帯区分設定）
	public static final String DATE_CLASS_HOLIDAY		= "0";	//休診日
	public static final String DATE_CLASS_SPECIAL		= "1";	//特殊診療日
	public static final String DATE_CLASS_NORMAL		= "9";	//診療日

	//業務区分
	// 2010.07.30 Mod T.Ootsuka Start
	public static final String GYOUMUKBN_DAY			= "1";	//日勤
	public static final String GYOUMUKBN_WATCH			= "2";	//当直
	public static final String GYOUMUKBN_LATE			= "3";	//深夜
	public static final String GYOUMUKBN_EMG			= "4";	//緊急

	// コメント
	//public static final String GYOUMUKBN_DAY			= "1";	//日勤
	//public static final String GYOUMUKBN_WATCH			= "2";	//当直
	//public static final String GYOUMUKBN_LATE			= "2";	//深夜
	//public static final String GYOUMUKBN_EMG			= "2";	//緊急

	// 2010.07.30 Mod T.Ootsuka End

	//読影フラグ
	public static final String DOKUEI_FLG_OFF			= "0";	//不要
	public static final String DOKUEI_FLG_ON			= "1";	//必要
	public static final String DOKUEI_FLG_SKIP			= "2";	//省略
	public static final String DOKUEI_FLG_EMG			= "3";	//至急

	//2011.11.21 Add NSK_M.Ochiai Start extends OMH-1-9
	//クローズ予約からRISオーダ登録画面のパラメータ
	public static final String RIS_ORDER_PARAM_KENSADATE			= "予約日";
	public static final String RIS_ORDER_PARAM_KENSATYPE			= "検査種別";
	public static final String RIS_ORDER_PARAM_KENSASITU			= "検査室";
	public static final String RIS_ORDER_PARAM_KENSAKIKI			= "検査機器";
	//2011.11.21 Add NSK_M.Ochiai End

	//処置室使用区分
	public static final String SYOTISITU_FLG_OFF		= "0";	//使用しない
	public static final String SYOTISITU_FLG_ON		= "1";	//使用する

	//RIオーダ区分
	public static final String RI_ORDER_FLG_NEEDLE		= "1";	//RI注射オーダ
	public static final String RI_ORDER_FLG_INSPECT		= "2";	//RI検査オーダ
	public static final String RI_ORDER_FLG_FOLLOW		= "3";	//RI追跡オーダ
	public static final String RI_ORDER_FLG_OTHER		= "0";	//その他検査

	public static final String RI_ORDER_FLG_ELSE		= "3";  //帳票用　その他

	//ポータブル区分
	public static final String PORTABLE_FLG_NORMAL		= "1";	//ポータブル
	public static final String PORTABLE_FLG_OPE			= "2";	//手術室
	public static final String PORTABLE_FLG_OTHER		= "0";	//その他
	//
	public static final String PORTABLE_FLG_ELSE		= "3";	//帳票用　その他

	//医師立会区分
	public static final String ISITATIAI_FLG_OFF		= "0";	//立会なし
	public static final String ISITATIAI_FLG_ON			= "1";	//立会あり

	//入外
	public static final String NYUGAI_KBN_OUT			= "1";	//外来
	public static final String NYUGAI_KBN_IN			= "2";	//入院
	public static final String NYUGAI_KBN_INOUT			= "3";	//入院中外来

	//至急
	public static final String SIKYU_FLG_ON			= "1";	//至急

	//実績会計
	public static final String JISSEKI_KAIKEI_OFF		= "0";		//会計なし
	public static final String JISSEKI_KAIKEI_OFF_STR	= "しない";	//
	public static final String JISSEKI_KAIKEI_ON		= "1";		//会計あり
	public static final String JISSEKI_KAIKEI_ON_STR	= "する";	//

	//感染症
	public static final String INFECTION_CHECK_ON	= "1";
	public static final String INFECTION_CHECK_OFF		= "0";
	public static final String INFECTION_CHECK_FLAG_STR = "済";

	//同意書
	public static final String DOUISHO_CHECK_ON = "1";
	public static final String DOUISHO_CHECK_OFF = "0";
	public static final String DOUISHO_CHECK_FLAG_STR = "済";

	//検像緊急
	public static final String KENZOU_KINKYU_OFF		= "0";		//通常
	public static final String KENZOU_KINKYU_OFF_STR	= "通常";	//
	public static final String KENZOU_KINKYU_ON		= "1";		//緊急
	public static final String KENZOU_KINKYU_ON_STR	= "緊急";	//

	//検像フラグ
	public static final String KENZOU_FLG_OFF			= "0";		//使用しない
	public static final String KENZOU_FLG_ON			= "1";		//使用する

	//検像ステータス
	public static final String KENZOU_STATUS_MI		= "0";		//未
	public static final String KENZOU_STATUS_SUMI		= "1";		//済
	public static final String KENZOU_STATUS_SAI		= "2";		//再

	//検像完了確認
	public static final String KENZOU_FIX_OFF			= "0";		//自動化しない
	public static final String KENZOU_FIX_ON			= "1";		//自動化する

	//検像終了認証
	public static final String KENZOU_PASS_FLG_OFF		= "0";		//表示しない
	public static final String KENZOU_PASS_FLG_ON		= "1";		//表示する

	//検像通信受信キー文字列
	public static final String KENZOU_RETSTR_CODE		= "CODE";	//CODE

	//MWM Type
	public static final String MWM_TYPE_DICOM				=   "0"; // DICOM   CR以外 標準
	public static final String MWM_TYPE_DICOM_FIRIPS_MR	=   "1"; // DICOM   CR以外 ﾌｨﾘｯﾌﾟｽMR
	public static final String MWM_TYPE_DICOM2				=   "2"; // DICOM   CR以外 標準2
	public static final String MWM_TYPE_DICOM_SATUEICODE	=  "10"; // DICOM   CR以外 撮影ｺｰﾄﾞ使用
	public static final String MWM_TYPE_DICOM2_PATCOMMENT	=  "12"; // DICOM   CR以外 標準2(PatientComment)
	public static final String MWM_TYPE_DICOM3				=  "70"; // DICOM   CR以外 標準3
	public static final String MWM_TYPE_DICOM_CR			= "500"; // DICOM   CR     標準
	public static final String MWM_TYPE_DICOM_CR2			= "501"; // DICOM   CR     標準2
	public static final String MWM_TYPE_DICOM_FCR			= "550"; // DICOM   FCR    標準 (Fuji製CR )
	public static final String MWM_TYPE_DICOM_FCR2			= "551"; // DICOM   FCR    標準2(Fuji製CR )
	public static final String MWM_TYPE_DICOM_FCR3			= "552"; // DICOM   FCR    標準3(ｹｱｽﾄﾘｰﾑﾍﾙｽ（旧：ｺﾀﾞｯｸ）)
	public static final String MWM_TYPE_DICOM_KOCR			= "600"; // DICOM   KOCR   標準 (ｺﾆｶ製CR  )
	public static final String MWM_TYPE_DICOM_KOCR2		= "650"; // DICOM   KOCR   標準2(ｺﾆｶ製CR2 )
	public static final String MWM_TYPE_DICOM_KOCR3		= "651"; // DICOM   KOCR   標準3(ｺﾆｶ製CR3 )
	public static final String MWM_TYPE_NON_DICOM_MWM		= "900"; // 非DICOM FCR-IDT標準 （Fuji製CR)
	public static final String MWM_TYPE_NON_DICOM_MWM_E	= "901"; // 非DICOM FCR-IDT拡張 （Fuji製CR）
	public static final String MWM_TYPE_NON_DICOM_MWM_E2	= "902"; // 非DICOM FCR-IDT拡張2（Fuji製CR）
	public static final String MWM_TYPE_KODACK_CR			= "910"; // 非DICOM KODAK CR    (Kodak製CR)
	public static final String MWM_TYPE_STUDIX_CR			= "920"; // 非DICOM CANON CXDI  (Canon製CR)
	public static final String MWM_TYPE_TOSHIBA_CR			= "930"; // 非DICOM 東芝CR      (東芝製CR)
	//
	public static final String MWM_UNKNOWN					= "UnKnown";
	public static final String MWM_UNKNOWN_AE_TITLE		= "Unknown AE-TITLE";
	public static final String MWM_UNKNOWN_KENSA_NAME		= "Unknown description";
	public static final String MWM_UNKNOWN_KENSA_DATE		= "00000000";
	public static final String MWM_UNKNOWN_KANASIMEI		= "ｶﾝｼﾞｬﾌﾒｲ";
	public static final String MWM_UNKNOWN_ROMASIMEI		= "Unknown Patient";
	public static final String MWM_UNKNOWN_KANJISIMEI2		= "患者不明";
	public static final String MWM_DELIMITATION			= ";";

	// MWM削除フラグイベント
	public static final int	MWMDELETE_INDEX_0		= 0;		//オーダ削除、受付取消時
	public static final int	MWMDELETE_INDEX_1		= 1;		//保存
	public static final int	MWMDELETE_INDEX_2		= 2;		//保留
	public static final int	MWMDELETE_INDEX_3		= 3;		//終了
	public static final int	MWMDELETE_INDEX_4		= 4;		//戻る
	//2011.11.21 Add NSK_M.Ochiai Start extends OMH-1-7
	public static final int	MWMDELETE_INDEX_5		= 5;		//RIVISION
	//2011.11.21 Add NSK_M.Ochiai End

	//DICOM接続マスタ固定値
	public static final String	MWM_PHYSICIANS_NOSET		= "0";		//医師名-設定しない
	public static final String	MWM_PHYSICIANS_GISI			= "1";		//医師名-実施技師名
	public static final String	MWM_DESCRIPTION_FIXSTR		= "0";		//検査名-固定値
	public static final String	MWM_DESCRIPTION_BUIHOUHOU	= "1";		//検査名-部位＋方法
	public static final String	MWM_DESCRIPTION_BUIHOUKOU	= "2";		//検査名-部位＋方向
	public static final String	MWM_DESCRIPTION_BUI			= "3";		//検査名-部位
	public static final String	MWM_ACTIONCODE_FIXSTR		= "0";		//撮影ﾒﾆｭｰｺｰﾄﾞ-固定値
	public static final String	MWM_ACTIONCODE_SATUEI		= "1";		//撮影ﾒﾆｭｰｺｰﾄﾞ-撮影コード
	public static final String	MWM_ACTIONCODE_SATUEICHG	= "2";		//撮影ﾒﾆｭｰｺｰﾄﾞ-撮影コード(*変換)
	public static final String	MWM_DESIGNATOR_FIXSTR		= "0";		//符号化体系指定子-固定値
	public static final String	MWM_DESIGNATOR_BUISATUEINO	= "1";		//符号化体系指定子-部位番号＋撮影番号
	public static final String	MWM_MEANINGS_FIXSTR			= "0";		//ANK撮影名称-固定値
	public static final String	MWM_MEANINGS_SATUEI_ENG		= "1";		//ANK撮影名称-撮影ﾒﾆｭｰ(英語)
	public static final String	MWM_MEANINGS_SATUEI_KANA	= "2";		//ANK撮影名称-撮影ﾒﾆｭｰ(カナ)
	public static final String	MWM_MEANINGS_SATUEI_KANJI	= "3";		//ANK撮影名称-撮影ﾒﾆｭｰ(漢字)
	public static final String	MWM_STEPID_FIXSTR			= "0";		//実施装置名?-固定値
	public static final String	MWM_STEPID_COUNT			= "1";		//実施装置名?-検査開始操作回数
	public static final String	MWM_R_DESCRIPTION_FIXSTR	= "0";		//ｵｰﾀﾞ部位-固定値
	public static final String	MWM_R_DESCRIPTION_BUIHOUHOU	= "1";		//ｵｰﾀﾞ部位-部位＋方法
	public static final String	MWM_R_DESCRIPTION_BUIHOUKOU	= "2";		//ｵｰﾀﾞ部位-部位＋方向
	public static final String	MWM_R_DESCRIPTION_BUI		= "3";		//ｵｰﾀﾞ部位-部位
	public static final String	MWM_PHYSICIAN_NOSET			= "0";		//依頼医師名(ﾛｰﾏ)-セットしない
	public static final String	MWM_PHYSICIAN_REQDOCTOR		= "1";		//依頼医師名(ﾛｰﾏ)-依頼医名
	public static final String	MWM_SERVICE_NOSET			= "0";		//依頼科-セットしない
	public static final String	MWM_SERVICE_REQSECTION		= "1";		//依頼科-依頼科

	//フィルム自動計算フラグ
	public static final String FILM_AUTO_FLG_ON		= "0";		//自動計算ON
	public static final String FILM_AUTO_FLG_OFF		= "1";		//自動計算OFF

	//伝票印刷フラグ
	public static final String DENPYO_INSATU_FLG_ON	= "1";		//印刷

	//患者紹介フラグ
	public static final String KANJA_SYOKAI_FLG_OFF	= "0";		//通常
	public static final String KANJA_SYOKAI_FLG_ON		= "1";		//患者紹介

	//プリセット共通
	public static final String PRESET_ID_DUMMY			= "PST_ID";
	public static final String PRESET_ID_DUMMY_STR		= "撮影済のみ(ﾌﾟﾘｾｯﾄ無)";

	//部位セットフラグ
	public static final String BUISET_FLG_ON			= "1";		//使用する

	//バーコード
	public static final String BARCODE_TYPE_PARTSID	= "1";		//検索対象-器材ID
	public static final String BARCODE_TYPE_BARCODE	= "2";		//検索対象-バーコード
	public static final String BARCODE_NUM_MST			= "2";		//数量をマスタの値を利用

	//2011.11.21 Add NSK_M.Ochiai Start extends OMH-1-9
	//RISオーダ登録の戻るボタン名称
	public static final String ORDER_CREATE_CLOSE_BTN_STR	= "戻る";	//クローズ画面から遷移された場合の「閉じる」ボタン名
	//2011.11.21 Add NSK_M.Ochiai End

	//端末設定
	//Card用
	//ビット/秒
	public static final String TERM_CARD_RATE1 = "100";
	public static final String TERM_CARD_RATE2 = "300";
	public static final String TERM_CARD_RATE3 = "1200";
	public static final String TERM_CARD_RATE4 = "2400";
	public static final String TERM_CARD_RATE5 = "4800";
	public static final String TERM_CARD_RATE6 = "9600";
	//データビット
	public static final String TERM_CARD_BIT1 = "5";
	public static final String TERM_CARD_BIT2 = "6";
	public static final String TERM_CARD_BIT3 = "7";
	public static final String TERM_CARD_BIT4 = "8";
	//パリティ
	public static final String TERM_CARD_PARITY1_STR	= "なし";
	public static final String TERM_CARD_PARITY1		= "0";
	public static final String TERM_CARD_PARITY2_STR	= "偶数";
	public static final String TERM_CARD_PARITY2		= "2";
	public static final String TERM_CARD_PARITY3_STR	= "奇数";
	public static final String TERM_CARD_PARITY3		= "1";
	public static final String TERM_CARD_PARITY4_STR	= "マーク";
	public static final String TERM_CARD_PARITY4		= "3";
	public static final String TERM_CARD_PARITY5_STR	= "スペース";
	public static final String TERM_CARD_PARITY5		= "4";
	//ストップビット
	public static final String TERM_CARD_STOPBIT1_STR	= "1";
	public static final String TERM_CARD_STOPBIT1		= "0";
	public static final String TERM_CARD_STOPBIT2_STR	= "1.5";
	public static final String TERM_CARD_STOPBIT2		= "1";
	public static final String TERM_CARD_STOPBIT3_STR	= "2";
	public static final String TERM_CARD_STOPBIT3		= "2";

	//再表示ボタンモード
	public static final String LIST_RELOADMODE_JIDOU	= "1";
	public static final String LIST_RELOADMODE_SYUDOU	= "2";
	public static final String LIST_RELOADMODE_OTO		= "3";

	//一覧検索条件デフォルト
	public static final String SEARCH_PATTERN_DEFAULT_TYPE		= "00";
	public static final String SEARCH_PATTERN_TOUCYOKU_TYPE	= "01";
	//ソート条件デフォルト
	public static final String SORT_PATTERN_DEFAULT_TYPE		= "00";

	//一覧検索条件判定用
	public static final Integer    SEARCH_PATTERN_INOUT_IN			= 1;	//入外区分-入院
	public static final Integer    SEARCH_PATTERN_INOUT_OUT		= 2;	//入外区分-外来
	public static final Integer    SEARCH_PATTERN_INOUT_MULTI		= 3;	//入外区分-入院+外来
	public static final String SEARCH_PATTERN_PORTABLE_NORMAL	= "1";	//ﾎﾟｰﾀﾌﾞﾙﾌﾗｸﾞ-ﾎﾟｰﾀﾌﾞﾙ
	public static final String SEARCH_PATTERN_PORTABLE_OPE		= "2";	//ﾎﾟｰﾀﾌﾞﾙﾌﾗｸﾞ-ｵﾍﾟ
	public static final String SEARCH_PATTERN_PORTABLE_MULTI	= "3";	//ﾎﾟｰﾀﾌﾞﾙﾌﾗｸﾞ-ﾎﾟｰﾀﾌﾞﾙ+ｵﾍﾟ
	public static final String SEARCH_PATTERN_RIORDER_NEEDLE	= "1";	//RIｵｰﾀﾞ区分-RI注射
	public static final String SEARCH_PATTERN_RIORDER_INSPECT	= "2";	//RIｵｰﾀﾞ区分-RI検査
	public static final String SEARCH_PATTERN_RIORDER_MULTI	= "3";	//RIｵｰﾀﾞ区分-RI注射+RI検査
	public static final String SEARCH_PATTERN_SHOWMODE_NORMAL	= "00";	//表示ﾓｰﾄﾞ-手動
	public static final String SEARCH_PATTERN_SHOWMODE_AUTO	= "01";	//表示ﾓｰﾄﾞ-自動
	public static final String SEARCH_PATTERN_SHOWMODE_SOUND	= "02";	//表示ﾓｰﾄﾞ-音告知
	public static final String SEARCH_PATTERN_KENZOUSTATUS_NONE= "0";	//検像進捗-未
	public static final String SEARCH_PATTERN_KENZOUSTATUS_FIX	= "1";	//検像進捗-済
	public static final String SEARCH_PATTERN_KENZOUSTATUS_RE	= "2";	//検像進捗-再
	public static final String SEARCH_PATTERN_KENZOUTANTOU_ON	= "ON";	//検像担当者=実施者フラグ

	//一覧表示項目定義
	public static final int	LIST_DATATYPE_STRING		= 0; //ﾃﾞｰﾀﾀｲﾌﾟ　文字列
	public static final int	LIST_DATATYPE_DATETIME		= 1; //ﾃﾞｰﾀﾀｲﾌﾟ　日時
	public static final int	LIST_DATATYPE_DATE			= 2; //ﾃﾞｰﾀﾀｲﾌﾟ　日付
	public static final int	LIST_DATATYPE_TIME			= 3; //ﾃﾞｰﾀﾀｲﾌﾟ　時刻
	// 2010.07.30 Add T.Ootsuka Start
	public static final int	LIST_DATATYPE_AGE			= 4; //ﾃﾞｰﾀﾀｲﾌﾟ　年齢
	// 2010.07.30 Add T.Ootsuka End

	public static final String LIST_DATATYPE_DATETIME_STR	= "日時"; //ﾃﾞｰﾀﾀｲﾌﾟ文字列　日時
	public static final String LIST_DATATYPE_DATE_STR		= "日付"; //ﾃﾞｰﾀﾀｲﾌﾟ文字列　日付
	public static final String LIST_DATATYPE_TIME_STR		= "時刻"; //ﾃﾞｰﾀﾀｲﾌﾟ文字列　時刻
	// 2010.07.30 Add T.Ootsuka Start
	public static final String LIST_DATATYPE_AGE_STR		= "年齢"; //ﾃﾞｰﾀﾀｲﾌﾟ文字列　年齢
	// 2010.07.30 Add T.Ootsuka End
	//
	public static final int	LIST_ALIGNMENT_LEFT			= 0; //表示位置　左端
	public static final int	LIST_ALIGNMENT_CENTER		= 1; //表示位置　中央
	public static final int	LIST_ALIGNMENT_RIGHT		= 2; //表示位置　右端
	//
	public static final int	LIST_NAMECHANGE_ON			= 1; //名称略称ﾌﾗｸﾞ使用可否　選択可
	public static final int	LIST_NAMECHANGE_OFF			= 0; //名称略称ﾌﾗｸﾞ使用可否　選択不可
	public static final int	LIST_NAMECHANGE_NORMAL		= 0; //名称略称ﾌﾗｸﾞ　名称
	public static final int	LIST_NAMECHANGE_RYAKU		= 1; //名称略称ﾌﾗｸﾞ　略称
	//
	public static final int	LIST_DATATYPE_R_STRING		= 0;  //ﾃﾞｰﾀﾀｲﾌﾟ　文字列
	public static final int	LIST_DATATYPE_R_NUMBER		= 9;  //ﾃﾞｰﾀﾀｲﾌﾟ　数値
	public static final int	LIST_DATATYPE_R_IMAGE		= -1; //ﾃﾞｰﾀﾀｲﾌﾟ　イメージ
	//

	// 2011.11.21 Add NSK_M.Ochiai Start OMH-1-10
	public static final int	LIST_DATATYPE_R_DATETIME	= 8;  //ﾃﾞｰﾀﾀｲﾌﾟ　時刻
	public static final String SYSPARAM2_VALUE1			= "1";
	// 2011.11.21 Add NSK_M.Ochiai End

	//
	public static final int	LIST_DISPLAY_MODE_NOEDIT	= 0; //表示ﾓｰﾄﾞ　編集不可
	public static final int	LIST_DISPLAY_MODE_EDIT		= 1; //表示ﾓｰﾄﾞ　編集可
	//
	public static final int	LIST_COMBO_MODE_OFF			= 0; //ｺﾝﾎﾞﾓｰﾄﾞ　なし
	public static final int	LIST_COMBO_MODE_ON			= 1; //ｺﾝﾎﾞﾓｰﾄﾞ　あり
	//
	public static final int	LIST_MUST_FLG_OFF			= 0; //必須ﾌﾗｸﾞ　非必須
	public static final int	LIST_MUST_FLG_ON			= 1; //必須ﾌﾗｸﾞ　必須
	//
	public static final String LIST_FORMAT_TIME_NULL1		= "9999";
	public static final String LIST_FORMAT_TIME_NULL2		= "999999";
	//
	public static final String LIST_FORMAT_DATE_0			= "yyyy/MM/dd";
	public static final String LIST_FORMAT_DATE_1			= "yyyy.MM.dd";
	public static final String LIST_FORMAT_DATE_2			= "yyyyMMdd";
	public static final String LIST_FORMAT_DATE_3			= "yyyy年MM月dd日";
	public static final String LIST_FORMAT_DATE_4			= "ggyy年MM月dd日";
	public static final String LIST_FORMAT_DATE_5			= "H.yy年";
	public static final String LIST_FORMAT_DATE_6			= "gg yy/MM/dd";
	public static final String LIST_FORMAT_DATE_7			= "ggyy/MM/dd";
	public static final String LIST_FORMAT_TIME_0			= "HH:mm";
	public static final String LIST_FORMAT_TIME_1			= "HH:mm:ss";
	public static final String LIST_FORMAT_TIME_2			= "HHmmss";
	public static final String LIST_FORMAT_TIME_3			= "HH時mm分";
	public static final String LIST_FORMAT_TIME_4			= "HH時mm分ss秒";
	public static final String LIST_FORMAT_TIME_5			= "HH:mm:ss";
	public static final String LIST_FORMAT_TIME_6			= "dddd";
	public static final String LIST_FORMAT_TIME_7			= "（ddd）";	// 2010.06.23 Add T.Nishikubo
	public static final String LIST_FORMAT_TIME_ALL1		= "YYYY/MM/DD";
	public static final String LIST_FORMAT_TIME_ALL2		= "YYYY.MM.DD";
	public static final String LIST_FORMAT_TIME_ALL3		= "YYYY年MM月DD日";
	public static final String LIST_FORMAT_TIME_ALL1_		= "yyyy/MM/dd";
	public static final String LIST_FORMAT_TIME_ALL2_		= "yyyy.MM.dd";
	public static final String LIST_FORMAT_TIME_ALL3_		= "yyyy年MM月dd日";
	public static final String LIST_FORMAT_TIME_HH			= "hh";
	public static final String LIST_FORMAT_TIME_HH_		= "HH";
	public static final String LIST_FORMAT_TIME_MM			= "NN";
	public static final String LIST_FORMAT_TIME_NN			= "nn";
	public static final String LIST_FORMAT_TIME_MM_		= "mm";
	public static final String LIST_FORMAT_TIME_SS			= "SS";
	public static final String LIST_FORMAT_TIME_SS_		= "ss";
	public static final String LIST_FORMAT_TIME_HH24		= "HH24";
	public static final String LIST_FORMAT_TIME_MI			= "MI";

	// 2011.11.21 Add NSK_M.Ochiai Start OMH-1-10
	public static final String DATETIME_YYYYMMDD_HHMM		= "yyyy/MM/dd HH:mm";
	// 2011.11.21 Add NSK_M.Ochiai End

	public static String LIST_FORMAT_DATETIME_0		= LIST_FORMAT_DATE_0 + ' ' + LIST_FORMAT_TIME_0; // [yyyy/MM/dd HH:mm]
	public static String LIST_FORMAT_DATETIME_1		= LIST_FORMAT_DATE_0 + ' ' + LIST_FORMAT_TIME_1; // [yyyy/MM/dd HH:mm:ss]
	public static String LIST_FORMAT_DATETIME_2		= LIST_FORMAT_DATE_1 + ' ' + LIST_FORMAT_TIME_0; // [yyyy.MM.dd HH:mm]
	public static String LIST_FORMAT_DATETIME_3		= LIST_FORMAT_DATE_1 + ' ' + LIST_FORMAT_TIME_1; // [yyyy.MM.dd HH:mm:ss]
	public static String LIST_FORMAT_DATETIME_4		= LIST_FORMAT_DATE_3 + ' ' + LIST_FORMAT_TIME_3; // [yyyy年MM月dd日 HH時mm分]
	public static String LIST_FORMAT_DATETIME_5		= LIST_FORMAT_DATE_3 + ' ' + LIST_FORMAT_TIME_4; // [yyyy年MM月dd日 HH時mm分ss秒]
	public static String LIST_FORMAT_DATETIME_6		= LIST_FORMAT_DATE_4 + ' ' + LIST_FORMAT_TIME_0; // [ggyy年MM月dd日 HH:mm]
	public static String LIST_FORMAT_DATETIME_7		= LIST_FORMAT_DATE_0 + ' ' + LIST_FORMAT_TIME_6; // [yyyy/MM/dd dddd]
	public static String LIST_FORMAT_DATETIME_8		= LIST_FORMAT_DATE_1 + ' ' + LIST_FORMAT_TIME_6; // [yyyy.MM.dd dddd]
	public static String LIST_FORMAT_DATETIME_9		= LIST_FORMAT_DATE_4 + ' ' + LIST_FORMAT_TIME_6; // [ggyy年MM月dd日 dddd]

	public static final String PASSWORD_STRING				= "yyyy/MM/dd 23:59:59";
	public static final String TODATE_STRING				= "YYYY/MM/DD HH24:MI:SS";

	// 2010.07.30 Add T.Ootsuka Start
	public static final String OPERATION_FORMAT_DATE1			= "gg.yy.MM.dd.";
	public static final String OPERATION_FORMAT_DATE2			= "(yyyy)";
	public static final String KANJA_LUNARAGE_NORMALFORMAT1	= "{0}ヶ月{1}日";
	public static final String KANJA_LUNARAGE_NORMALFORMAT2	= "{0}歳{1}ヶ月";
	public static final String KANJA_LUNARAGE_NORMALFORMAT3	= "{0}歳";
	public static final String KANJA_LUNARAGE_MODEFORMAT1		= "{0}.{1}.{2}";
	public static final String KANJA_LUNARAGE_MODEFORMAT2		= "{0}歳{1}ヶ月{2}日";
	public static final String KANJA_LUNARAGE_MODEFORMAT3		= "{0:D3}{1:D2}{2:D2}";
	public static final String KANJA_LUNARAGE_MODEFORMAT4		= "{0}";
	public static final String KANJA_LUNARAGE_MODEFORMAT5		= "{0:D}.{1:D2}";
	public static final String KANJA_LUNARAGE_MODEFORMAT6		= "{0:D}.{1:D2}.{2:D2}";
	public static final String KANJA_LUNARAGE_MODEFORMAT7		= "{0}歳";
	public static final String KANJA_LUNARAGE_MODEFORMAT8		= "{0:D}歳{1:D2}ヶ月";
	public static final String KANJA_LUNARAGE_MODEFORMAT9		= "{0:D}歳{1:D2}ヶ月{2:D2}日";
	public static final String KANJA_LUNARAGE_MODE0			= "0"; // 標準
	public static final String KANJA_LUNARAGE_MODE1			= "1";
	public static final String KANJA_LUNARAGE_MODE2			= "2";
	public static final String KANJA_LUNARAGE_MODE3			= "3";
	public static final String KANJA_LUNARAGE_MODE4			= "4";
	public static final String KANJA_LUNARAGE_MODE5			= "5";
	public static final String KANJA_LUNARAGE_MODE6			= "6";
	public static final String KANJA_LUNARAGE_MODE7			= "7";
	public static final String KANJA_LUNARAGE_MODE8			= "8";
	public static final String KANJA_LUNARAGE_MODE9			= "9";
	public static final String KANJA_LUNARAGE_MODEFORMAT1STR	= "Y.M.DD";
	public static final String KANJA_LUNARAGE_MODEFORMAT2STR	= "Y歳Mヶ月DD日";
	public static final String KANJA_LUNARAGE_MODEFORMAT3STR	= "YYYMMDD";
	public static final String KANJA_LUNARAGE_MODEFORMAT4STR	= "Y";
	public static final String KANJA_LUNARAGE_MODEFORMAT5STR	= "Y.MM";
	public static final String KANJA_LUNARAGE_MODEFORMAT6STR	= "Y.MM.DD";
	public static final String KANJA_LUNARAGE_MODEFORMAT7STR	= "Y歳";
	public static final String KANJA_LUNARAGE_MODEFORMAT8STR	= "Y歳MMヶ月";
	public static final String KANJA_LUNARAGE_MODEFORMAT9STR	= "Y歳MMヶ月DD日";
	// 2010.07.30 Add T.Ootsuka End

	//パスワードチェック
	public static final String PASSWORD_CHECK_FLG_ALLON	= "0";	//常に認証
	public static final String PASSWORD_CHECK_FLG_ON		= "1";	//ログイン=実施者時は省略
	public static final String PASSWORD_CHECK_FLG_OFF		= "2";	//常に省略

	//患者情報変更
	public static final String PATRESULT_UPD_FLG_ON		= "1";
	public static final String PATRESULT_UPD_FLG_OFF		= "0";

	//検査開始時刻設定定義
	public static final String STARTTIME_SET_NULL			= "0";	//時刻未設定
	public static final String STARTTIME_SET_RECEIPT		= "1";	//受付時刻を設定
	public static final String STARTTIME_SET_ENDDATE		= "2";	//検査終了時刻を設定
	public static final String STARTTIME_SET_FORMSHOW		= "3";	//業務詳細画面展開時刻を設定

	//造影剤チェック
	public static final String ZOUEIZAI_CHECK_FLG_ON		= "1";	//チェックする
	public static final String ZOUEIZAI_CHECK_ERROR		= "0";	//エラーとする
	public static final String ZOUEIZAI_CHECK_DLG			= "1";	//確認ダイアログ

	//デフォルト要造影フラグ　実際はCodeConvert
	public static final String ZOUEI_FLG_0 = "0";	//要造影不要
	public static final String ZOUEI_FLG_1 = "1";	//要造影要

	//デフォルト当日依頼フラグ　実際はCodeConvert
	public static final String TODAY_IRAI_0 = "0";	//当日依頼不要
	public static final String TODAY_IRAI_1 = "1";	//当日依頼要

	//デフォルト患者待ち時間フラグ　実際はCodeConvert
	public static final String KANJAWAIT_0 = "0";	// 0 < value < KANJAWAIT_1
	public static final String KANJAWAIT_1 = "1";	// KANJAWAIT_1 =< value < KANJAWAIT_2
	public static final String KANJAWAIT_2 = "2";	// KANJAWAIT_2 =< value < KANJAWAIT_3
	public static final String KANJAWAIT_3 = "3";	// KANJAWAIT_3 =< value

	//精算区分フラグ
	public static final String SEISAN_FLG_MI				= "0";	//未清算
	public static final String SEISAN_FLG_SUMI				= "1";	//清算済

	//ExtendOrderInfo.SchemaURL付加ﾌﾗｸﾞ
	public static final String SCHEMAURL_ADD_FLG_ON		= "1";	//付加
	public static final String SCHEMAURL_ADD_FLG_OFF		= "0";	//付加しない

	//受付NO表示有無
	public static final String RECEIPTNO_FLG_ON			= "1";	//受付NO表示

	//ToHisInfo書込有無
	public static final String TOHISINFO_SEND_FLG_ON		= "1";	//送信する

	//ToReportInfo書込有無
	public static final String TOREPORTINFO_SEND_FLG_ON	= "1";	//送信する

	//ToPacsInfo書込有無
	public static final String TOPACSINFO_SEND_FLG_ON		= "1";	//送信する

	//優先業務詳細区分
	public static final String SCREEN_FORM_PHOTO			= "01";	//撮影
	public static final String SCREEN_FORM_INSPECT			= "02";	//検査

	//画面フラグ
	public static final String PICTURE_FLG_ON				= "ON";	//ON

	//フラグ
	public static final String FLG_ON						= "1";
	public static final String FLG_ON_STR					= "有";
	public static final String FLG_OFF						= "0";
	public static final String FLG_OFF_STR					= "無";

	//MWMしきい値
	public static final Integer MWMTYPE_VALUE					= 899;	//MWM接続ﾀｲﾌﾟ0-899：DICOM MWM　接続　（詳細は今後）900-999：非DICOM 接続　（詳細は今後）NULL：MWM接続しない（条件送信しない）

	//MPPSしきい値
	public static final Integer MPPSTYPE_VALUE					= 899;	//MPPS接続ﾀｲﾌﾟ0-899：DICOM MPPS 接続　（詳細は今後）900-999：非DICOM 接続　（詳細は今後）NULL：MPPS接続しない（実績収集しない）
	//MPPSステータス
	public static final String MPPS_COMP					= "COMP";

	//処理モード
	public static final String MWM_MODE_OPERATION			= "0";	//実施モード
	public static final String MWM_MODE_RECEIPT			= "1";	//受付モード
	public static final String MWM_MODE_PORTABLE			= "2";	//ポータブル一括モード // 2010.07.30 Add Y.Shibata Start

	//SOKECT
	public static String SUCCESS_MESSAGE			= "OK";
	public static String ERROR_MESSAGE				= "NG";

	//帳票ID
	public static final String PRINT_ID_UKETUKE			= "1";	//受付票
	public static final String PRINT_ID_IRAI				= "2";	//依頼票
	public static final String PRINT_ID_JISSEKI			= "3";	//実績票
	public static final String PRINT_ID_DAITYO				= "34";	//検査台帳
	public static final String PRINT_ID_YOTEI				= "41";	//予定一覧表
	public static final String PRINT_ID_PORTABLE			= "42";	//ポータブル一覧
	public static final String PRINT_ID_LABELKANJA			= "51";	//患者袋ラベル
	public static final String PRINT_ID_LABELKENSA			= "52";	//検査袋ラベル
	// 2010.06.23 Add T.Nishikubo
	public static final String PRINT_ID_TOUTYOKU			= "71";	//当直日誌

	//帳票区分
	public static final String PRINT_NYUGAI_KBN_OTHER		= "0";	//分類なし
	public static final String PRINT_NYUGAI_KBN_KANJA		= "1";	//患者入外
	public static final String PRINT_NYUGAI_KBN_DENPYO		= "2";	//伝票入外
	public static final String PRINT_RI_KBN_OTHER			= "0";	//分類なし
	public static final String PRINT_RI_KBN_RI				= "1";	//RI区分
	public static final String PRINT_POTA_KBN_OTHER		= "0";	//分類なし
	public static final String PRINT_POTA_KBN_POTA			= "1";	//ポータブル区分

	//帳票出力タイミング
	public static final String PRINT_ACTION_RECEIPT		= "1";	//受付時
	public static final String PRINT_ACTION_EXSTART		= "2";	//検査開始時
	public static final String PRINT_ACTION_EXEND			= "3";	//検査終了時
	public static final String PRINT_ACTION_CLICK			= "9";	//クリック時

	//自動印刷フラグ
	public static final String AUTOPRINT_FALSE				= "0";	//しない
	public static final String AUTOPRINT_TRUE				= "1";	//する
	public static final String AUTOPRINT_FIRST				= "9";	//初回のみ

	//帳票プレビューフラグ
	public static final String PRINTPREVIEW_FALSE			= "0";	//しない
	public static final String PRINTPREVIEW_TRUE			= "1";	//する

	//拡張色情報キー
	public static final String EXCOLORDEF_INOPERATION		= "検中他検査";

	//URLキー
	public static final String URL_KEY_USER_ID				= "%USER_ID%";
	public static final String URL_KEY_USER_PW				= "%USER_PW%";
	public static final String URL_KEY_KANJA_ID			= "%KANJA_ID%";
	public static final String URL_KEY_EXAM_DATE			= "%EXAM_DATE%";
	public static final String URL_KEY_MODALITY_TYPE		= "%MODALITY_TYPE%";
	public static final String URL_KEY_AC_NO				= "%AC_NO%";
	public static final String URL_KEY_ORDER_NO			= "%ORDER_NO%";
	public static final String URL_KEY_RIS_ID				= "%RIS_ID%";
	public static final String URL_KEY_SHEMA_URL			= "%SHEMA_URL%";
	// 2011.03.23 Add K.Shinohara Start A0012
	public static final String URL_KEY_EXAM_DATE2			= "%EXAM_DATE2%";
	public static final String URL_KEY_AC_NO2				= "%AC_NO2%";
	public static final String URL_KEY_ORDER_NO2			= "%ORDER_NO2%";
	public static final String URL_KEY_RIS_ID2				= "%RIS_ID2%";
	// 2011.03.23 Add K.Shinohara End

	//URLキー(業務詳細)
	public static final String URL_GYOMU_KEY_RIS_ID		= "%RIS_ID%";
	public static final String URL_GYOMU_KEY_PATIENT_ID	= "%PATIENT_ID%";
	public static final String URL_GYOMU_KEY_KENSA_TYPE	= "%KENSA_TYPE%";
	public static final String URL_GYOMU_KEY_REQUEST_TYPE	= "%REQUEST_TYPE%";
	public static final String URL_GYOMU_KEY_USER			= "%USER%";
	public static final String URL_GYOMU_KEY_PASSWORD		= "%PASSWORD%";
	//
	public static final String URL_GYOMU_VAL_PATIENT		= "patient";
	public static final String URL_GYOMU_VAL_RPATIENT		= "result_patient";

	//URL_ID(業務詳細)
	public static final String URL_GYOMU_ID_LIST1			= "URL0";		//一覧依頼詳細表示 // 2010.07.30 Add Y.Shibata Start
	public static final String URL_GYOMU_ID_REQUEST1		= "URL1";		//依頼詳細タブ表示１
	public static final String URL_GYOMU_ID_PATIENT1		= "URL2";		//患者詳細タブ表示１
	public static final String URL_GYOMU_ID_HISTORY1		= "URL31";		//検査履歴タブ表示１
	public static final String URL_GYOMU_ID_HISTORY2		= "URL32";		//検査履歴タブ表示２
	public static final String URL_GYOMU_ID_PATIENT2		= "URL41";		//患者履歴タブ表示２

	//オーダ受付ボタンタイプ
	public static final String ORDERUKETUKE_DLG			= "1";	//受付ダイアログ
	public static final String ORDERUKETUKE_AUTO			= "2";	//受付処理

	//器材検索画面用
	public static final String ITEMSEARCH_ALL_ID			= "ALL_ID";
	public static final String ITEMSEARCH_ALL_ID_STR		= "全て";
	public static final String ITEMSEARCH_KTYPE_NONE		= "";
	public static final String ITEMSEARCH_KTYPE_ID			= "ID";
	public static final String ITEMSEARCH_KTYPE_NAME		= "名称";
	public static final String ITEMSEARCH_KTYPE_RNAME		= "略名称";

	//区分定義アイテムID
	public static final String DIVCONVERT_KIZAI_KBN		= "KIZAIKBN";
	public static final String DIVCONVERT_INFUSE_KBN		= "INFUSEKBN";

	//部位編集区分
	public static final Integer OPEBUI_KBN_ADD					= 1;
	public static final Integer OPEBUI_KBN_STOP				= 2;
	public static final Integer OPEBUI_KBN_DELETE				= 3;

	//撮影編集区分
	public static final Integer OPESATUEI_KBN_ADD				= 1;
	public static final Integer OPESATUEI_KBN_STOP				= 2;
	public static final Integer OPESATUEI_KBN_RESHOT			= 3;
	public static final Integer OPESATUEI_KBN_REMARK			= 4;
	public static final Integer OPESATUEI_KBN_DELETE			= 5;

	//フィルム編集区分
	public static final Integer OPEFILM_KBN_ADD				= 1;
	public static final Integer OPEFILM_KBN_DELETE				= 2;

	//前後期間
	public static final Integer SEARCH_BEFORE_YEAR				= -30;
	public static final Integer SEARCH_AFTER_YEAR				=  30;

	// 2010.06.23 Add T.Nishikubo Start
	// 業務日誌
	public static final String G_TIME_FLG_ON				= "1";		// 日跨ぎ検索
	public static final String DEFAULT_G_TIME				= "8:00";	// デフォルト業務基準時刻
	// どちらの日誌ボタンが押されたかの判定用
	public static final String DIARY_BUTTON1				= "DIARY1";	// 日誌１ボタン
	public static final String DIARY_BUTTON2				= "DIARY2";	// 日誌２ボタン
	// 帳票用
	public static final String DIARY_HOUR					= "時間";
	public static final String DIARY_MINUTE				= "分";
	public static final String KEY_HOUR					= "hour";
	public static final String KEY_MINUTE					= "minute";
	// 2010.06.23 Add T.Nishikubo End

	// 2010.07.30 Add T.Ootsuka Start
	// ハイライト設定機能モード用定数
	public static final String HIGHLIGHTMODE_SINGULARITY	= "0";
	public static final String HIGHLIGHTMODE_MULTI			= "1";
	// 2010.07.30 Add T.Ootsuka End

	// 2010.07.30 Add DD.Chinh Start
	//本日造影剤区分ID、区分名称
	public static final String ZOUEIZAI_NYUGAI_KBN_0 = "0";
	public static final String ZOUEIZAI_NYUGAI_KBN_1 = "1";
	public static final String ZOUEIZAI_NYUGAI_KBN_2 = "2";

	public static final String ZOUEIZAI_NYUGAI_NAME_0 = "なし";
	public static final String ZOUEIZAI_NYUGAI_NAME_1 = "入外共通";
	public static final String ZOUEIZAI_NYUGAI_NAME_2 = "入外個別";
	// 2010.07.30 Add DD.Chinh End

	// 2010.07.30 Add T.Ootsuka Start
	// 定型コメント業務詳細警告機能
	public static final String PREDEFINED_DISPMODE_0		= "0";
	public static final String PREDEFINED_DISPMODE_1		= "1";
	// 2010.07.30 Add T.Ootsuka End

	// 2010.07.30 Add T.Ootsuka Start
	// 使用フラグ
	public static final Integer USEDFLG_0	= 0;
	public static final Integer USEDFLG_1	= 1;
	public static final String RIS_COMMENTKBN_0	= "0";

	// 画面表示モード
	public static final boolean STAFFINFODISPMODE_DEF		= true;
	public static final boolean STAFFINFODISPMODE_NOMAL	= false;

	// ハイライト警告条件区切り文字
	public static final char HIGHLIGHTWORNING_SPLIT	= ',';

	// 業務詳細チームグリッド列幅計算用スクロールバーサイズ
	public static final Integer DATAGRID_SCROLLBAR_SIZE	= 20;
	// 2010.07.30 Add T.Ootsuka End
	public static final Integer DATAGRID_SCROLLBAR_OFFSET  = 3;		// 2010.07.30 Add DD.Chinh

	// 2010.07.30 Add Y.Shibata Start
	public static final String KENSAKIKI_MODE_SINGLE	= "0"; //実施機器モード
	public static final String KENSAKIKI_MODE_MULTI	= "1"; //仮想機器モード

	// デフォルト装置数設定
	public static final Integer KENSAKIKI_MULTIMWM_DEFAULTCOUNT	= 2;
	public static final Integer KENSAKIKI_MULTIMWM_MAXCOUNT		= 5;
	// 2010.07.30 Add Y.Shibata End

	//一覧タイプ
	public static final String LIST_TYPE_NONE		= "0";	//未使用
	public static final String LIST_TYPE_BUI		= "1";	//部位
	public static final String LIST_TYPE_SATUEI	= "2";	//撮影
	public static final String LIST_TYPE_ITEM		= "3";	//器材
	public static final String LIST_TYPE_FILM		= "4";	//フィルム

	//コメント区分
	public static final String PRECOMMENTKBN_ORDER	= "0";	//ｵｰﾀﾞｺﾒﾝﾄ
	public static final String PRECOMMENTKBN_BUI	= "10";	//部位ｺﾒﾝﾄ
	public static final String PRECOMMENTKBN_EX	= "20";	//実施ｺﾒﾝﾄ

	// 2010.07.30 Add T.Nishikubo Start
	// 統計
	// タブ番号
	public static final Integer STATISTICS_TAB_KENSATYPE		= 0;	// 検査種別
	public static final Integer STATISTICS_TAB_HOUHOU			= 1;	// 方法別
	public static final Integer STATISTICS_TAB_BUIBUNRUI		= 2;	// 部位分類
	public static final Integer STATISTICS_TAB_BUI				= 3;	// 部位別
	public static final Integer STATISTICS_TAB_ZOUEIZAI		= 4;	// 器材別
	public static final Integer STATISTICS_TAB_BUINAIYOU		= 5;	// 部位内容
	public static final Integer STATISTICS_TAB_ORDERNAIYOU		= 6;	// オーダ内容
	public static final Integer STATISTICS_TAB_FILM			= 7;	// フィルム別

	// 出力ドキュメントタイトル
	public static final String STATISTICS_TITLE_KENSATYPE	= "検査種別検査件数";
	public static final String STATISTICS_TITLE_HOUHOU		= "方法別検査件数";
	public static final String STATISTICS_TITLE_BUIBUNRUI	= "部位分類別検査件数";
	public static final String STATISTICS_TITLE_BUI		= "部位別検査件数";
	public static final String STATISTICS_TITLE_ZOUEIZAI	= "器材別使用数";
	public static final String STATISTICS_TITLE_BUINAIYOU	= "部位内容";
	public static final String STATISTICS_TITLE_ORDERNAIYOU = "オーダ内容";
	public static final String STATISTICS_TITLE_FILM		= "フィルム別使用数";

	// ヘッダ部項目名
	public static final String STATISTICS_HEAD_KIKAN		= "対象期間:";
	public static final String STATISTICS_HEAD_SECTION		= "依頼科:";
	public static final String STATISTICS_HEAD_KENSATYPE	= "検査種別:";
	public static final String STATISTICS_HEAD_KIZAIKBN	= "器材区分:";

	// データ部項目名
	public static final String STATISTICS_COL_KENSATYPE	= "検査種別";
	public static final String STATISTICS_COL_HOUHOU		= "方法";
	public static final String STATISTICS_COL_BUI			= "部位";
	public static final String STATISTICS_COL_BUIBUNRUI	= "部位分類";
	public static final String STATISTICS_COL_KIZAI_NAME	= "名称";
	public static final String STATISTICS_COL_SUURYOU		= "数量";
	public static final String STATISTICS_COL_KIZAI_TANNI	= "単位";
	public static final String STATISTICS_COL_SUURYOU_IJI	= "医事数量";
	public static final String STATISTICS_COL_KIZAIIJITANI	= "医事単位";
	public static final String STATISTICS_COL_KENSADATE	= "検査日";
	public static final String STATISTICS_COL_ORDERNO		= "オーダー番号";
	public static final String STATISTICS_COL_KENSATYPEID	= "種別ID";
	public static final String STATISTICS_COL_KENSASTATUS	= "検査進捗";
	public static final String STATISTICS_COL_SECTIONID	= "科ID";
	public static final String STATISTICS_COL_IRAISECTION	= "依頼科";
	public static final String STATISTICS_COL_IRAIDOCTORNO	= "依頼医番号";
	public static final String STATISTICS_COL_IRAIDOCTOR	= "依頼医氏名";
	public static final String STATISTICS_COL_PATIENTID	= "患者ID";
	public static final String STATISTICS_COL_PAT_ROMENAME	= "ローマ氏名";
	public static final String STATISTICS_COL_PAT_NAME		= "漢字氏名";
	public static final String STATISTICS_COL_SEX			= "性別";
	public static final String STATISTICS_COL_NYUGAI		= "入外区分";
	public static final String STATISTICS_COL_BYOUTOU		= "病棟";
	public static final String STATISTICS_COL_AGE			= "年齢";
	public static final String STATISTICS_COL_BUINAME		= "部位名";
	public static final String STATISTICS_COL_KENSAHOUHOU	= "検査方法";
	public static final String STATISTICS_COL_BUISTATUS	= "部位進捗";
	public static final String STATISTICS_COL_KIKI_ID		= "装置ID";
	public static final String STATISTICS_COL_KIKI_NAME	= "装置名";
	public static final String STATISTICS_COL_ORDERDATE	= "オーダー日";
	public static final String STATISTICS_COL_RESRV_TIME	= "予約時刻";
	public static final String STATISTICS_COL_RECPT_DATE	= "受付日";
	public static final String STATISTICS_COL_RECPT_TIME	= "受付時刻";
	public static final String STATISTICS_COL_START_TIME	= "開始時刻";
	public static final String STATISTICS_COL_END_TIME		= "終了時刻";
	// 2010.09.03 Mod DD.Chinh Start KUM-2.4
	// 2011.08.04 Mod NSK_T.Koudate Start NML-CAT2-004
	public static final String STATISTICS_COL_GISI 		= "最終入力者";
	// コメント(担当者から最終入力者へ変更)
	//public static final String STATISTICS_COL_GISI 		= "担当者1";

	// 2011.08.04 Mod NSK_T.Koudate End
	// 2011.08.04 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//public static final String STATISTICS_COL_GISI2 		= "担当者2";
	//public static final String STATISTICS_COL_GISI3 		= "担当者3";
	//public static final String STATISTICS_COL_GISI4 		= "担当者4";
	//public static final String STATISTICS_COL_GISI5 		= "担当者5";

	// 2011.08.04 Del NSK_T.Koudate End
	//public static final String STATISTICS_COL_GISI 		= "技師";
	// 2010.09.03 Mod DD.Chinh End
	public static final String STATISTICS_COL_HOUI			= "放医";
	// 2010.09.03 Mod DD.Chinh Start KUM-2.4
	// 2011.08.04 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//// 2010.09.03 Mod DD.Chinh Start KUM-2.4
	//public static final String STATISTICS_COL_NURSE		= "看護師1";
	//public static final String STATISTICS_COL_NURSE2		= "看護師2";
	//public static final String STATISTICS_COL_NURSE3		= "看護師3";
	////public static final String STATISTICS_COL_NURSE		= "看護師";
	//// 2010.09.03 Mod DD.Chinh End

	// 2011.08.04 Del NSK_T.Koudate End
	// 2010.09.03 Add DD.Chinh Start KUM-2.4
	public static final String STATISTICS_COL_ENFORCEDOC1	= "施行医1";
	public static final String STATISTICS_COL_ENFORCEDOC2	= "施行医2";
	public static final String STATISTICS_COL_ENFORCEDOC3	= "施行医3";
	public static final String STATISTICS_COL_ENFORCEDOC4	= "施行医4";
	public static final String STATISTICS_COL_ENFORCEDOC5	= "施行医5";
	public static final String STATISTICS_COL_ENFORCEDOC1KA= "施行医1所属科";
	public static final String STATISTICS_COL_ENFORCEDOC2KA= "施行医2所属科";
	public static final String STATISTICS_COL_ENFORCEDOC3KA= "施行医3所属科";
	public static final String STATISTICS_COL_ENFORCEDOC4KA= "施行医4所属科";
	public static final String STATISTICS_COL_ENFORCEDOC5KA= "施行医5所属科";
	// 2011.08.04 Mod NSK_T.Koudate Start NML-CAT2-004
	public static final String STATISTICS_COL_JISISYA		= "検査責任者";
	// コメント(実施者から検査責任者変更)
	//public static final String STATISTICS_COL_JISISYA		= "実施者";

	// 2011.08.04 Mod NSK_T.Koudate End
	public static final String STATISTICS_COL_KENZOU_TANTOU= "検像担当者";
	// 2010.09.03 Add DD.Chinh End
	// 2011.08.04 Add NSK_T.Koudate Start NML-CAT2-004
	public static final String STATISTICS_COL_MED_PERSON	= "担当者";
	// 2011.08.04 Add NSK_T.Koudate End
	public static final String STATISTICS_COL_HOU_SIJII	= "放科指示医師";
	public static final String STATISTICS_COL_PAT_COND		= "患者状態";
	public static final String STATISTICS_COL_START_DATE	= "検査開始日";
	public static final String STATISTICS_COL_END_DATE		= "検査終了日";
	public static final String STATISTICS_COL_FILM_NAME	= "フィルム名";
	public static final String STATISTICS_COL_FILM_SUU		= "枚数";
	public static final String STATISTICS_COL_FILM_LOSS	= "ロス";

	public static final String STATISTICS_COL_NYUUIN		= "入院";
	public static final String STATISTICS_COL_GAIRAI		= "外来";
	public static final String STATISTICS_COL_GOUKEI		= "合計";

	// 統計コンボボックス未指定時の出力値
	public static final String STATISTICS_KENSATYPE_ALL	= "(全て)";	// 検査種別
	public static final String STATISTICS_SECTION_ALL		= "(全て)";	// 依頼科
	public static final String STATISTICS_ZOUEIZAI_ALL		= "(全て)";	// 器材区分

	// 出力ファイル拡張子
	public static final String CSV_FILE_EXTENSION			= ".csv";

	// 2010.07.30 Add T.Nishikubo End

	//RI区分
	public static final String RIKBN_NOTHING		= "0";	//なし
	public static final String RIKBN_NEEDLE		= "1";	//注射
	public static final String RIKBN_INSPECT		= "2";	//検査
	public static final String RIKBN_FOLLOWUP		= "3";	//追跡

	// 2010.09.09 Add K.Shinohara Start
	// 排他ロックモード
	public static final String ROCK_ACCESSMODE_REF	= "0";	//参照
	public static final String ROCK_ACCESSMODE_MOD	= "1";	//更新
	// 2010.09.09 Add K.Shinohara End

	// 2010.09.21 Add K.Shinohara Start
	// ユーザID使用フラグ
	public static final String USERIDVALIDITYFLAG_OFF	= "0";
	public static final String USERIDVALIDITYFLAG_ON	= "1";
	// 2010.09.21 Add K.Shinohara End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	// 薬剤発注チェック
	public static final String PHARMA_CHECK_ON			= "1";
	public static final String PHARMA_CHECK_OFF		= "0";
	public static final String PHARMA_CHECK_FLAG_STR	= "済";
	// 2010.11.16 Add T.Nishikubo End

	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	public static final String PATIENTSIDEEFFECT_NULL_CHECK_NG = "未入力項目：";
	// 2011.03.10 Add CIJ_R.Aoyama Merge  End  MY-1-10

	// 2011.01.24 Add DD.Chinh Start KANRO-R-19
	public static final String PRECHECK_STATUS_ON		= "1";

	public static final String PRECHECK_STATUS_MI		 = "0";
	public static final String PRECHECK_STATUS_SUMI	 = "1";
	public static final String PRECHECK_STATUS_MI_STR	 = "未";
	public static final String PRECHECK_STATUS_SUMI_STR = "済";
	// 2011.01.24 Add DD.Chinh End

	// 2011.01.24 Add T.Nishikubo Start KANRO-R-22
	// 患者情報表示拡張機能
	public static final String EXTEND_PATIENT_INFO_ON		= "1";		// 機能ON
	public static final String EXTEND_PATIENT_INFO_OFF		= "0";		// 機能OFF
	// 2011.01.24 Add T.Nishikubo End

	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	// 掲示板関連
	//削除フラグ（0：使用中　1：削除済）
	public static final Integer DEL_FLG_FALSE = 0;
	public static final Integer DEL_FLG_TRUE  = 1;

	public static final String COMMSG_PREFIX_STRING			= "MSG";				//掲示板機能接頭語
	// 2011.02.28 Add T.Nishikubo Start
	public static final String COMMSG_SHORT_PREFIX_STRING		= "M";
	public static final Integer COMMSG_IP_LENGTH					= 15;					//IPアドレスの長さ
	// 2011.02.28 Add T.Nishikubo End

	//コミュニケーションメッセージ
	//---通知先ユーザID文字列のセパレータ
	public static final String COMMSG_SENDTO_USER_ID_SEPARATOR	= "|";
	//---全体告知メッセージ
	public static final String COMMSG_ALL_MESSAGE_ID			= COMMSG_PREFIX_STRING + "0000000000000";	//メッセージID
	public static final String COMMSG_ALL_BUNRUI_ID			= "00000";				//分類ID
	//---端末メモ
	public static final String COMMSG_TERM_MESSAGE_ID			= COMMSG_PREFIX_STRING + "0000000000001";	//メッセージID
	public static final String COMMSG_TERM_BUNRUI_ID			= "00001";				//分類ID
	//---個別メッセージ
	public static final String COMMSG_USER_BUNRUI_ID			= "00002";				//分類ID
	//---通知先ステータス
	public static final String COMMSG_SENDTO_STATUS_CHECKED	= "1";					//確認済み
	public static final String COMMSG_SENDTO_STATUS_UNCHECK	= "0";					//未確認
	public static final String COMMSG_SENDTO_STATUS_NOTHING	= "9";					//通知先になし
	//---掲示板用フラグ
	public static final Integer COMMSG_FLG_OFF						= 0;					//フラグOFF
	public static final Integer COMMSG_FLG_ON						= 1;					//フラグON

	// 2011.02.16 Add DD.Chinh Start KANRO-R-27
	public static final Integer SENDTO_USER_ID_MAXLEN				= 2000;					//通知先ユーザIDの最大長
	public static final Integer SENDTO_STATUS_MAXLEN				= 2000;					//通知先ステータスの最大長
	public static final String OVER_VALUE_STRING				= "1";
	// 2011.02.16 Add DD.Chinh End

	// 2011.02.16 Add T.Nishikubo End

	// 2011.03.18 Add Yk.Suzuki@CIJ Start A0005
	public static final String SMILE_ORDER_DELETE_ON		= "1";
	public static final String SMILE_ORDER_DELETE_OFF		= "0";
	// 2011.03.18 Add Yk.Suzuki@CIJ End

	// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
	public static final String RECEIPT_RANDOM_FLG_ON	= "1"; // ランダム発番する
	public static final String RECEIPT_RANDOM_FLG_OFF	= "0"; // ランダム発番しない
	// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

	// 2011.05.19 Add T.Ootsuka Start KG-2-X02
	// 一覧検索条件デフォルト設定
	public static final String DEFAULTSEARCHCRITERIA_FLG_OFF	= "0";
	public static final String DEFAULTSEARCHCRITERIA_FLG_ON	= "1";
	// 2011.05.19 Add T.Ootsuka End

	// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
	public static final String UNKNOWNDATE_FLG_OFF	= "0";
	public static final String UNKNOWNDATE_FLG_ON	= "1";
	// 2011.07.11 Add NSK_H.Hashimoto End

	// 2011.08.01 Add NSK_T.Wada Start NML-CAT2-001
	//LINKタイプ（1：起動AP、2：URL、3:起動フォルダ）
	public static final Integer LINK_TYPE_AP					= 1;
	public static final Integer LINK_TYPE_URL					= 2;
	public static final Integer LINK_TYPE_FOLDER				= 3;
	// 2011.08.01 Add NSK_T.Wada End

	// 2011.08.02 Add NSK_T.Koudate Start NML-CAT2-004
	// 認証ダイアログの初期フォーカス（1：ユーザID 2：パスワード）
	public static final String AUTHENTICATION_DEFAULT_FOCUS_USERID = "1";
	public static final String AUTHENTICATION_DEFAULT_FOCUS_PASSWORD = "2";
	// 2011.08.02 Add NSK_T.Koudate End

	// 2011.08.05 Add DD.Chinh@MERX Start NML-CAT3-035
	public static final String LOCALHOST_IPADRESS				= "127.0.0.1";		//デフォルト端末IPアドレス
	// 2011.08.05 Add DD.Chinh@MERX End

	// 2011.08.22 Add T.Nishikubo@MERX Start NML-2-X01
	public static final String HOLIDAY_MODE_TYPE_COUNT			= "1";				//回数判定
	public static final String HOLIDAY_MODE_TYPE_WEEK			= "2";				//週判定
	// 2011.08.22 Add T.Nishikubo@MERX End

	// 2011.11.14 Add NSK_H.Hashimoto Start OMH-1-04
	// CSV出力用定義
	public static final String OUTPUT_CSV_DEFAULT_FILENAME = "予定実績";
	public static final String OUTPUT_CSV_DLG_FILTER = "CSV|*.csv";
	// ヘッダ部項目名
	public static final String KENSAYOYAKULIST_HEAD_KENSADATE = "検査日：";
	// データ部項目名
	public static final String KENSAYOYAKULIST_COL_NO = "No";
	// 2011.11.14 Add NSK_H.Hashimoto End

	// 2011.11.25 Add NSK_T.Wada Start OMH-1-09
	public static final String CLOSE_YOYAKU_BUSINESS_START_TIME	= "0900";
	public static final String CLOSE_YOYAKU_BUSINESS_END_TIME		= "1800";
	public static final int	CLOSE_YOYAKU_BUSINESS_INTERVAL		= 15;
	public static final String	DATE_FOMAT_YYYYMMDD					= "yyyyMMdd";
	public static final String	DATE_FOMAT_YYYYMMDDHHMM				= "yyyyMMddHHmm";
	public static final String	DATE_FOMAT_YYYYMMDDHHMMSS			= "yyyyMMddHHmmss";
	public static final String	TIME_FOMAT_HH						= "HH";
	public static final String	TIME_FOMAT_MM						= "mm";
	public static final String	ZERO_PADDING_FORMAT_0000			= "{0:0000}";
	public static final String	ZERO_PADDING_FORMAT_000000			= "{0:000000}";
	// 2011.11.25 Add NSK_T.Wada Start End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	//オーダ種別
	public static final String EXAM_TIMING_ITEM_ID				= "195";	//ITEM_ID
	public static final Integer EXAM_TIMING_LABEL					= 0;		//ラベル
	public static final Integer EXAM_TIMING_COLOR					= 1;		//文字色
	public static final Integer EXAM_TIMING_COLORBK				= 2;		//背景色
	// 2011.11.22 Add NSK_M.Ochiai End
}






