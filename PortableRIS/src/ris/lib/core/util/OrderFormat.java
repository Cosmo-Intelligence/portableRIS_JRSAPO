package ris.lib.core.util;

import java.sql.Connection;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.database.MasterInfoTbl;
import ris.portable.util.DataTable;

/// <summary>
///
/// オーダ情報の形成クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		:           	:        /          		: original
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo		: KANRO-R-9
/// V2.04.001 		: 2011.01.24	: 999999 / DD.Chinh			: KANRO-R-19 KANRO-R-29
/// V2.01.00		: 2011.08.04    extends 999999 / NSK_T.Koudate	: NML-CAT2-004
/// V2.01.00		: 2011.08.05	: 999999 / H.Satou@MERX		: NML-CAT3-036
/// V2.01.01.13000	: 2011.11.15    extends 999999 / NSK_H.Hashimoto	: OMH-1-05
/// V2.01.01.13000	: 2011.11.22	: 999999 / NSK_M.Ochiai		: OMH-1-2
///
/// </summary>
public class OrderFormat
{
	public static Log logger = LogFactory.getLog(OrderFormat.class);

	//要造影のために部位情報保持をしておく
	private Hashtable buiKensaHouhouHashtable = new Hashtable();

	//2010.10.29 K.Shinohara Start
	//各マスタ
	private DataTable mstKensaType		= null;
	private DataTable mstBui			= null;
	private DataTable mstHoukou			= null;
	private DataTable mstSayuu			= null;
	private DataTable mstKensahouhou	= null;
	//2010.10.29 K.Shinohara End

	/*
	/// <summary>
	/// フォーマットを設定する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="transaction">DBトランザクションオブジェクト</param>
	/// <param name="dt">オーダ情報</param>
	/// <param name="buiDt">オーダ部位情報</param>
	/// <param name="param">検索条件</param>
	/// <param name="termBean">端末情報</param>
	/// <returns></returns>
    public DataTable OrderFormatData(Connection con, DataTable dt, DataTable buiDt, OrderSearchParameter param, TerminalInfoBean termBean)
    {
		// begin log
		logger.debug("begin");

		MasterInfoTbl masterTbl = new MasterInfoTbl();
		DataTable retDt = null;
		try
		{
			//不足カラムの追加
			AddColumns(dt);

			if (dt == null || dt.getRowCount() <= 0)
			{
				retDt = dt;
			}
			else
			{
				MasterUtil					masterUtil				= new MasterUtil();
				RisSummaryView				view					= new RisSummaryView();
				RisPatientCommentTbl		patientCommentTbl		= new RisPatientCommentTbl();
				RisExFilmInfoTbl			risExFilmInfoTbl		= new RisExFilmInfoTbl();
				RisPatientInfoTbl			patientInfoTbl			= new RisPatientInfoTbl();
				RisPatientResultsInfoTbl	patientResultsInfoTbl	= new RisPatientResultsInfoTbl();

				//各マスタの取得
				GetMaster(con, masterTbl); //2010.10.29 Add K.Shinohara
				DataTable codeDt	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_CODECONVERT, false);				//項目変換マスタ
				DataTable statusDt	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_STATUSDEFINE, false);				//ステータス定義
				DataTable commentDt	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_PREDEFINEDCOMMENTMASTER, false);	//定型コメントマスタ
				DataTable sectionDt	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_SECTIONMASTER, false);				//依頼科マスタ
				DataTable eColorDt	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_EXTEND_COLORDEFINE, false);			//拡張色定義
				DataTable sParam2Dt	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_SYSTEMPARAM2, false);				//システムパラム２ //2010.07.30 Add T.Ootsuka
				//DataTable houhouDt= masterTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_KENSAHOUHOUMASTER, false);			//検査方法マスタ   //2010.10.29 Del K.Shinohara
				// 2010.11.18 Add T.Nishikubo Start
				DataTable termInfDt	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_TERMINALINFO, false);				//端末情報マスタ
				// 2010.11.18 Add T.Nishikubo End

				// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
				DataTable eTimigDt = null;
				//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
				//{
				//	eTimigDt = masterTbl.GetMasterDataTable(con, MasterUtil.RIS_EXAMTIMINGDEFINE, false);				//オーダ種別
				//}
				// 2011.11.22 Add NSK_M.Ochiai End

				//要造影表示マーク取得およびカラムに設定
				String markFlgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_ZOUEI_FLG, CommonString.ZOUEI_FLG_1, con);
				//要造影なし表示マーク取得およびカラムに設定
				String noMarkFlgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_ZOUEI_FLG, CommonString.ZOUEI_FLG_0, con);

				//当日依頼表示マーク取得およびカラムに設定
				String todayIraiStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_TODAYIRAI, CommonString.TODAY_IRAI_1, con);
				//当日依頼表示マーク取得およびカラムに設定
				String noTodayIraiStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_TODAYIRAI, CommonString.TODAY_IRAI_0, con);

				// 2010.10.13 Add H.Ishikawa@CIJ Start 一覧高速化対応 ループ外へ移動
				//待ち時間0表示マーク取得およびカラムに設定
				String kanjaWait0Str = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_KANJAWAIT, CommonString.KANJAWAIT_0, con);
				//待ち時間1表示マーク取得およびカラムに設定
				String kanjaWait1Str = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_KANJAWAIT, CommonString.KANJAWAIT_1, con);
				//待ち時間2マーク取得およびカラムに設定
				String kanjaWait2Str = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_KANJAWAIT, CommonString.KANJAWAIT_2, con);
				//待ち時間3表示マーク取得およびカラムに設定
				String kanjaWait3Str = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_KANJAWAIT, CommonString.KANJAWAIT_3, con);

				String kanjaWait0MarkStr = "";
				String kanjaWait1MarkStr = "!";
				String kanjaWait2MarkStr = "!!";
				String kanjaWait3MarkStr = "!!!";

				double kanjaWait0TimeInt = 15;
				double kanjaWait1TimeInt = 30;
				double kanjaWait2TimeInt = 60;

				try
				{
					String[] kanjaWait0 = kanjaWait0Str.split(",");
					String[] kanjaWait1 = kanjaWait1Str.split(",");
					String[] kanjaWait2 = kanjaWait2Str.split(",");
					String[] kanjaWait3 = kanjaWait3Str.split(",");

					kanjaWait0TimeInt = Double.parseDouble(kanjaWait0[0].toString());
					kanjaWait1TimeInt = Double.parseDouble(kanjaWait1[0].toString());
					kanjaWait2TimeInt = Double.parseDouble(kanjaWait2[0].toString());

					kanjaWait0MarkStr = kanjaWait0[1].toString();
					kanjaWait1MarkStr = kanjaWait1[1].toString();
					kanjaWait2MarkStr = kanjaWait2[1].toString();
					kanjaWait3MarkStr = kanjaWait3[1].toString();
				}
				catch (Exception e)
				{
				}
				// 2010.10.13 Mod H.Ishikawa@CIJ End

				//検中他検査
				ExtendColorDefineBean exColorDef = CommonUtil.GetExtendColorDefine(eColorDt, CommonString.EXCOLORDEF_INOPERATION, con);

				//システム日時
				Timestamp sysDataTime	= Configuration.GetInstance().GetSysDate(con);	// 2011.05.23 Mod H.Orikasa 業務詳細高速化対応
				//Timestamp sysDataTime	= Configuration.GetInstance().GetSysDate();					//

				//不足カラムの追加
				AddColumns(dt);

				//表示項目定義を取得
				DataTable showItemDefineDt = null;
				if (param.GetShowItemDefineDt() != null)
				{
					showItemDefineDt = param.GetShowItemDefineDt();
				}

				//要造影フラグの初期化
				String zoueiflgStr = CommonString.ZOUEI_FLG_0;
				MasterUtil mUtil = new MasterUtil();
				this.buiKensaHouhouHashtable.clear();

				//部位情報を設定する
				// 2011.08.18 Mod T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
				retDt = SetBuiInfo(con, dt, param);
				// コメント
				//// 2011.08.05 Mod H.Satou@MERX Start NML-CAT2-036
				//retDt = SetBuiInfo(con, transaction, dt, param, codeDt);
				//// コメント
				////retDt = SetBuiInfo(con, transaction, dt, param);
				//

				// 2011.08.05 Mod H.Satou@MERX End
				// 2011.08.18 Mod T.Ootsuka@MERX End

				//2010.10.29 Add K.Shinohara Start
				//他検査情報を準備する
				Hashtable otherHash = new Hashtable();
				if (param.GetTakensaSearch() || exColorDef.GetColorMode() == CommonString.FLG_ON)
				{
					otherHash = GetOtherDataHash(con, dt, param);
				}
				//2010.10.29 Add K.Shinohara End

				// 1件毎の処理
				for (int i = 0; i < dt.Rows.Count; i++)
				{
					// 現在のレコード
					DataRow curOrderRow = dt.Rows[i];

					// 表示データの加工を行う

					// 区
					if (!curOrderRow.IsNull(RisSummaryView.GYOUMU_KBN_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.GYOUMU_KBN_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_GYOMUKBN, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.GYOUMU_KBN_NAME_COLUMN] = flgStr;
						}
					}


					// ステータス
					if (!curOrderRow.IsNull(RisSummaryView.STATUS_COLUMN))
					{
						String statusLabel = curOrderRow[RisSummaryView.STATUS_COLUMN].toString();
						String statusLabeltr = Configuration.GetInstance().GetStatusString(statusDt, statusLabel);
						curOrderRow[RisSummaryView.STATUS_NAME_COLUMN] = statusLabeltr;
					}


					// 予約
					if (!curOrderRow.IsNull(RisSummaryView.KENSA_STARTTIME_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_KENSA_STARTTIME_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_TIME_0;
						}

						String kensaTimeStr = curOrderRow[RisSummaryView.KENSA_STARTTIME_COLUMN].toString();
						//検査時刻を文字列に変換する
						kensaTimeStr = TextUtil.ConvertKensaTimeString(kensaTimeStr);
						//フォーマットで整形し値を設定する
						String setStr = "";
						try
						{
							Timestamp time = Timestamp.Parse(kensaTimeStr);
							setStr = time.toString(formatStr);

						}
						catch
						{
						}
						curOrderRow[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = setStr;
					}


					// 受付
					if (!curOrderRow.IsNull(RisSummaryView.RECEIPTDATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_RECEIPTDATE2_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_TIME_0;
						}

						String receiptDateStr = curOrderRow[RisSummaryView.RECEIPTDATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setStr = "";
						Timestamp receptTimestamp = Timestamp.MinValue;
						try
						{
							receptTimestamp = Timestamp.Parse(receiptDateStr);
							setStr = receptTimestamp.toString(formatStr);

						}
						catch
						{
						}
						curOrderRow[RisSummaryView.RECEIPTDATE2_STRING_COLUMN] = setStr;

						// 2011.09.14 Add H.Orikasa Start NML-CAT9-032
						// 受済未満の場合は受付時刻を空とする
						if (!curOrderRow.IsNull(RisSummaryView.STATUS_COLUMN))
						{
							if (TextUtil.ParseStringToInt(curOrderRow[RisSummaryView.STATUS_COLUMN].toString()) <
								TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED))
							{
								curOrderRow[RisSummaryView.RECEIPTDATE2_STRING_COLUMN] = "";
							}
						}
						// 2011.09.14 Add H.Orikasa End

						// 患者待ち状態 患者待ち時間

						if (!curOrderRow.IsNull(RisSummaryView.STATUS_COLUMN))
						{
							String statusLabel = curOrderRow[RisSummaryView.STATUS_COLUMN].toString();

							if (statusLabel.Equals(CommonString.STATUS_ISREGISTERED))
							{
								TimeSpan diffTime = sysDataTime - receptTimestamp;
								double diffMinutesTime = diffTime.TotalMinutes;

								if (diffMinutesTime < kanjaWait0TimeInt)
								{
									curOrderRow[RisSummaryView.KANJA_WAIT_STATE_NAME_COLUMN] = kanjaWait0MarkStr;
								}
								else if (diffMinutesTime < kanjaWait1TimeInt)
								{
									curOrderRow[RisSummaryView.KANJA_WAIT_STATE_NAME_COLUMN] = kanjaWait1MarkStr;
								}
								else if (diffMinutesTime < kanjaWait2TimeInt)
								{
									curOrderRow[RisSummaryView.KANJA_WAIT_STATE_NAME_COLUMN] = kanjaWait2MarkStr;
								}
								else
								{
									curOrderRow[RisSummaryView.KANJA_WAIT_STATE_NAME_COLUMN] = kanjaWait3MarkStr;
								}

								if (diffTime.TotalMinutes > 0)
								{
									//患者待ち時間
									curOrderRow[RisSummaryView.KANJA_WAIT_TIME_NAME_COLUMN] = diffTime.Hours.toString("00") + ":" + diffTime.Minutes.toString("00");
								}
							}
						}


					}


					// 検査日
					if (!curOrderRow.IsNull(RisSummaryView.KENSA_DATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_KENSA_DATE_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_DATE_0;
						}

						String kensaDateStr = curOrderRow[RisSummaryView.KENSA_DATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setStr = "";
						try
						{
							String yyyyStr = kensaDateStr.SubString(0, 4);
							String mmStr   = kensaDateStr.SubString(4, 2);
							String ddStr   = kensaDateStr.SubString(6, 2);
							String dateStr = yyyyStr + "/" + mmStr + "/" + ddStr;
							Timestamp time  = Timestamp.Parse(dateStr);
							setStr = time.toString(formatStr);

							// 2010.08.26 Del K.Shinohara Start
							// 検査時刻は影響しない
							////特殊処理
							//String kensaTimeStr = curOrderRow[RisSummaryView.KENSA_STARTTIME_COLUMN].toString();
							//if (kensaTimeStr == CommonString.LIST_FORMAT_TIME_NULL1 ||
							//    kensaTimeStr == CommonString.LIST_FORMAT_TIME_NULL2)
							//{
							//    //特定時刻の場合は空にする
							//    setStr = "";
							//}
							// 2010.08.26 Del K.Shinohara End
						}
						catch
						{
						}
						curOrderRow[RisSummaryView.KENSA_DATE_STRING_COLUMN] = setStr;
					}


					// 紹介フラグ
					if (!curOrderRow.IsNull(RisSummaryView.KANJA_SYOKAI_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.KANJA_SYOKAI_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_KANJASYOKAI, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.KANJA_SYOKAI_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 同意書フラグ
					if (!curOrderRow.IsNull(RisSummaryView.DOUISHO_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.DOUISHO_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_DOUISHO, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.DOUISHO_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 優先
					if (!curOrderRow.IsNull(RisSummaryView.YUUSEN_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.YUUSEN_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_YUUSEN, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.YUUSEN_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 年齢
					if (!curOrderRow.IsNull(RisSummaryView.KENSA_DATE_AGE_COLUMN))
					{
						// 2010.09.08 Mod H.Orikasa Start
						PatientInfoBean patientBean = new PatientInfoBean();
						// 患者情報設定
						patientBean.SetBirthday(curOrderRow[RisSummaryView.BIRTHDAY_COLUMN].toString());
						// 患者情報取得成功時
						if (patientBean != null)
						{
							// 検査日取得
							String kensaDateStr		= curOrderRow[RisSummaryView.KENSA_DATE_STRING_COLUMN].toString();
							String kensaDate		= curOrderRow[RisSummaryView.KENSA_DATE_COLUMN].toString();
							Timestamp kensaTimestamp	= new Timestamp();
							try
							{
								//ExecutionInfoBean exInfoBean	= Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(curOrderRow[RisSummaryView.RIS_ID_COLUMN].toString());

								String unknownDateType	= Configuration.GetInstance().GetSystemParamValue(sParam2Dt, RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE1_COLUMN);
								String unKnownDate		= Configuration.GetInstance().GetSystemParamValue(sParam2Dt, RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);
								// 日未定対応
								if ((unknownDateType != null) && (unKnownDate != null)
									&& ((unknownDateType == CommonString.VALEU_1) || (unknownDateType == CommonString.VALEU_2))
									&& (unKnownDate == kensaDate))
								{
									// 日未定の場合、SYSDATEを取得
									kensaTimestamp	= sysDataTime;										// 2011.05.23 Mod H.Orikasa 業務詳細高速化対応
									//kensaTimestamp	= Configuration.GetInstance().GetSysDate();			//
								}
								else
								{
									if (String.IsNullOrEmpty(kensaDateStr))
									{
										kensaTimestamp	= sysDataTime;									// 2011.05.23 Mod H.Orikasa 業務詳細高速化対応
										//kensaTimestamp	= Configuration.GetInstance().GetSysDate();		//
									}
									else
									{
										kensaTimestamp	= Timestamp.Parse(kensaDateStr);
									}
								}
							}
							catch (Exception ex)
							{
								logger.fatal(ex);
								kensaTimestamp	= Timestamp.MinValue;
							}

							// 年齢フォーマットモード取得
							//フォーマット文字列を取得する
							String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_KENSA_DATE_AGE_STRING);
							if (formatStr.length() <= 0)
							{
								formatStr = "";
							}

							// 年齢フォーマットモードの取得
							String ageFormatMode	= CommonString.KANJA_LUNARAGE_MODE0;
							if (formatStr.Equals(CommonString.KANJA_LUNARAGE_MODEFORMAT4STR))
							{
								ageFormatMode	= CommonString.KANJA_LUNARAGE_MODE4;
							}
							else if (formatStr.Equals(CommonString.KANJA_LUNARAGE_MODEFORMAT5STR))
							{
								ageFormatMode	= CommonString.KANJA_LUNARAGE_MODE5;
							}
							else if (formatStr.Equals(CommonString.KANJA_LUNARAGE_MODEFORMAT6STR))
							{
								ageFormatMode	= CommonString.KANJA_LUNARAGE_MODE6;
							}
							else if (formatStr.Equals(CommonString.KANJA_LUNARAGE_MODEFORMAT7STR))
							{
								ageFormatMode	= CommonString.KANJA_LUNARAGE_MODE7;
							}
							else if (formatStr.Equals(CommonString.KANJA_LUNARAGE_MODEFORMAT8STR))
							{
								ageFormatMode	= CommonString.KANJA_LUNARAGE_MODE8;
							}
							else if (formatStr.Equals(CommonString.KANJA_LUNARAGE_MODEFORMAT9STR))
							{
								ageFormatMode	= CommonString.KANJA_LUNARAGE_MODE9;
							}


							String ageFormatStr	= patientBean.GetLunarAge(kensaTimestamp, ageFormatMode, 0, 0);
							curOrderRow[RisSummaryView.KENSA_DATE_AGE_STRING_COLUMN]	= ageFormatStr;

							//数値の年齢を設定
							double ageDbl		= 0d;
							patientBean.GetLunarAge(kensaTimestamp, ageFormatMode, 0, 0, ref ageDbl);
							curOrderRow[RisSummaryView.KENSA_DATE_AGE_DBL_COLUMN]		= ageDbl;
						}

						// コメント
						//curOrderRow[RisSummaryView.KENSA_DATE_AGE_STRING_COLUMN] = curOrderRow[RisSummaryView.KENSA_DATE_AGE_COLUMN].toString();
						////年齢は999のとき、空値で設定
						//if (curOrderRow[RisSummaryView.KENSA_DATE_AGE_COLUMN].toString() == CommonString.PATIENT_NULL_AGE)
						//{
						//    curOrderRow[RisSummaryView.KENSA_DATE_AGE_STRING_COLUMN] = "";
						//}

						// 2010.09.08 Mod H.Orikasa End
					}


					// 性別
					if (!curOrderRow.IsNull(RisSummaryView.SEX_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.SEX_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_SEX, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.SEX_NAME_COLUMN] = flgStr;
						}
					}


					// 患者搬送状態
					if (!curOrderRow.IsNull(RisSummaryView.TRANSPORTTYPE_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.TRANSPORTTYPE_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_TRANSPORT, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.TRANSPORTTYPE_TITLE_COLUMN] = flgStr;
						}
					}


					// 依頼科略名
					if (!curOrderRow.IsNull(RisSummaryView.IRAI_SECTION_ID_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.IRAI_SECTION_ID_COLUMN].toString();
						String flgStr = masterUtil.FindData(sectionDt, MasterUtil.RIS_SECTION_RYAKUNAME, MasterUtil.RIS_SECTION_ID, flgValue);
						curOrderRow[RisSummaryView.IRAI_SECTION_RYAKU_NAME_COLUMN] = flgStr;
					}


					// 本日他検査フラグ
					// 2010.10.29 Mod K.Shinohara Start
					if (otherHash.Count > 0)
					{
						if (!curOrderRow.IsNull(RisSummaryView.RIS_ID_COLUMN) &&
							!curOrderRow.IsNull(RisSummaryView.KANJA_ID_COLUMN) &&
							!curOrderRow.IsNull(RisSummaryView.KENSA_DATE_COLUMN))
						{
							String takensaStr= "";
							String risID     = curOrderRow[RisSummaryView.RIS_ID_COLUMN].toString();
							String kanjaID   = curOrderRow[RisSummaryView.KANJA_ID_COLUMN].toString();
							String kensaDate = curOrderRow[RisSummaryView.KENSA_DATE_COLUMN].toString();

							//検査日が一致する場合
							if (otherHash.ContainsKey(kensaDate))
							{
								ArrayList otherList = (ArrayList)otherHash[kensaDate];

								for (int j=0; j<otherList.Count; j++)
								{
									DataRow tRow = (DataRow)otherList[j];

									String rowRisID			= tRow[RisSummaryView.RIS_ID_COLUMN].toString();
									String rowKanjaID		= tRow[RisSummaryView.KANJA_ID_COLUMN].toString();
									String rowKTypeID		= tRow[RisSummaryView.KENSATYPE_ID_COLUMN].toString();
									String rowKTypeRName	= masterUtil.FindData(mstKensaType, MasterUtil.RIS_KENSATYPE_RYAKUNAME, MasterUtil.RIS_KENSATYPE_ID, rowKTypeID);
									String rowStatus		= tRow[RisSummaryView.STATUS_COLUMN].toString();
									String rowStatusShort	= Configuration.GetInstance().GetStatusString(statusDt, rowStatus);

									//同一患者＆自オーダ以外
									if (rowKanjaID == kanjaID && rowRisID != risID)
									{
										// 2011.07.27 Add H.Orikasa Start A0005
										// キャンセルオーダ機能がオンの場合、削除オーダは除外
										if (Configuration.GetInstance().GetOrderDeleteFlg())
										{
											// 削除ステータスの場合
											if (rowStatus == CommonString.STATUS_DELETE)
											{
												continue;
											}
										}
										// 2011.07.27 Add H.Orikasa End

										//検査種別略+ｽﾃｰﾀｽ文字列
										String lineStr = rowKTypeRName + rowStatusShort;
										if (takensaStr.length() <= 0)
										{
											takensaStr  = lineStr;
										}
										else
										{
											takensaStr += " " + lineStr;
										}

										//検中の場合
										if (rowStatus == CommonString.STATUS_INOPERATION)
										{
											//検中他検査フラグON
											curOrderRow[RisSummaryView.TAKENSA_INOPE_FLG_COLUMN] = CommonString.FLG_ON;
										}
									}
								}
								curOrderRow[RisSummaryView.TAKENSA_FLG_NAME_COLUMN] = takensaStr;
							}
						}
					}
					// コメント
					//if (param.GetTakensaSearch() || exColorDef.GetColorMode() == CommonString.FLG_ON)
					//{
					//    if (!curOrderRow.IsNull(RisSummaryView.RIS_ID_COLUMN) &&
					//        !curOrderRow.IsNull(RisSummaryView.KANJA_ID_COLUMN) &&
					//        !curOrderRow.IsNull(RisSummaryView.KENSA_DATE_COLUMN))
					//    {
					//        String takensaStr= "";
					//        String risID     = curOrderRow[RisSummaryView.RIS_ID_COLUMN].toString();
					//        String kanjaID   = curOrderRow[RisSummaryView.KANJA_ID_COLUMN].toString();
					//        String kensaDate = curOrderRow[RisSummaryView.KENSA_DATE_COLUMN].toString();

					//        //他検査情報を取得
					//        OrderSearchParameter oParam = new OrderSearchParameter();
					//        oParam.SetRisID(risID);
					//        oParam.SetKanjaID(kanjaID);
					//        oParam.SetKensaDate(kensaDate);
					//        DataTable takensaDt = view.SearchTakensaOrderData(con, transaction, oParam);

					//        for (int j=0; j<takensaDt.Rows.Count; j++)
					//        {
					//            DataRow tRow = takensaDt.Rows[j];

					//            String rowRisID			= tRow[RisSummaryView.RIS_ID_COLUMN].toString();
					//            String rowKTypeRName	= tRow[RisSummaryView.KENSATYPE_RYAKUNAME_COLUMN].toString();
					//            String rowStatus		= tRow[RisSummaryView.STATUS_COLUMN].toString();
					//            String rowStatusShort	= Configuration.GetInstance().GetStatusString(statusDt, rowStatus);

					//            //自オーダ以外
					//            if (rowRisID != risID)
					//            {
					//                //検査種別略+ｽﾃｰﾀｽ文字列
					//                String lineStr = rowKTypeRName + rowStatusShort;
					//                if (takensaStr.length() <= 0)
					//                {
					//                    takensaStr  = lineStr;
					//                }
					//                else
					//                {
					//                    takensaStr += " " + lineStr;
					//                }

					//                //検中の場合
					//                if (rowStatus == CommonString.STATUS_INOPERATION)
					//                {
					//                    //検中他検査フラグON
					//                    curOrderRow[RisSummaryView.TAKENSA_INOPE_FLG_COLUMN] = CommonString.FLG_ON;
					//                }
					//            }
					//        }
					//        curOrderRow[RisSummaryView.TAKENSA_FLG_NAME_COLUMN] = takensaStr;
					//    }
					//}

					// 2010.10.29 Mod K.Shinohara End


					// 至急
					if (!curOrderRow.IsNull(RisSummaryView.SIKYU_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.SIKYU_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_SIKYU, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.SIKYU_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 入力,時間
					if (!curOrderRow.IsNull(RisSummaryView.INPUTDATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatDateStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_INPUTDATE_STRING);
						if (formatDateStr.length() <= 0)
						{
							formatDateStr = CommonString.LIST_FORMAT_DATE_0;
						}
						String formatTimeStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_INPUTTIME_STRING);
						if (formatTimeStr.length() <= 0)
						{
							formatTimeStr = CommonString.LIST_FORMAT_TIME_0;
						}

						String inputDateStr = curOrderRow[RisSummaryView.INPUTDATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setDateStr = "";
						String setTimeStr = "";
						try
						{
							Timestamp time = Timestamp.Parse(inputDateStr);
							setDateStr = time.toString(formatDateStr);
						}
						catch
						{
						}
						try
						{
							Timestamp time = Timestamp.Parse(inputDateStr);
							setTimeStr = time.toString(formatTimeStr);
						}
						catch
						{
						}
						curOrderRow[RisSummaryView.INPUTDATE_STRING_COLUMN] = setDateStr;
						curOrderRow[RisSummaryView.INPUTTIME_STRING_COLUMN] = setTimeStr;
					}


					// 精算フラグ
					if (!curOrderRow.IsNull(RisSummaryView.SEISAN_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.SEISAN_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_SEISAN, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.SEISAN_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 処置室フラグ
					if (!curOrderRow.IsNull(RisSummaryView.SYOTISITU_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.SYOTISITU_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_SYOTISITU, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.SYOTISITU_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 読影フラグ
					if (!curOrderRow.IsNull(RisSummaryView.DOKUEI_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.DOKUEI_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_DOKUEI, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.DOKUEI_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// RI区分
					if (curOrderRow.IsNull(RisSummaryView.RI_ORDER_FLG_COLUMN))
					{
						curOrderRow[RisSummaryView.RI_ORDER_FLG_COLUMN] = CommonString.RIKBN_NOTHING;
					}
					if (!curOrderRow.IsNull(RisSummaryView.RI_ORDER_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.RI_ORDER_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_RIORDER, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.RI_ORDER_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 確保
					// 2010.11.16 Mod T.Nishikubo Start KANRO-R-3
					//関東労災による特注処理対応
					if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
					{
						// 関労処理
						if (!curOrderRow.IsNull(RisSummaryView.DRESSING_ROOM_ID_COLUMN) && termBean != null)
						{
							String statusStr = curOrderRow[RisSummaryView.STATUS_COLUMN].toString();
							String flgValue = curOrderRow[RisSummaryView.DRESSING_ROOM_ID_COLUMN].toString();
							if (flgValue.length() > 0)		// 2011.02.16 Add T.Nishikubo KANRO-R-29
							//if (flgValue.length() > 0 && statusStr == CommonString.STATUS_ISREGISTERED)
							{
								// 2010.11.18 Mod T.Nishikubo Start
								if (flgValue == termBean.GetTerminalID())
								{
									//自身が確保
									curOrderRow[RisSummaryView.KAKUHO_COLUMN] = termBean.GetKakuhoRoom();
								}
								else
								{
									//その他が確保
									String DressingRoom = masterUtil.FindData(termInfDt,
																				RisTerminalInfoTbl.DRESSING_ROOM_COLUMN,
																				RisTerminalInfoTbl.TERMINAL_ID_COLUMN,
																				flgValue
																			  );
									curOrderRow[RisSummaryView.KAKUHO_COLUMN] = DressingRoom;
								}
								//curOrderRow[RisSummaryView.KAKUHO_COLUMN] = termBean.GetKakuhoRoom();
								// 2010.11.18 Mod T.Nishikubo End
							}
						}

					}
					else
					{
						// 標準処理
						if (!curOrderRow.IsNull(RisSummaryView.EXAMTERMINALID_COLUMN) && termBean != null)
						{
							String statusStr = curOrderRow[RisSummaryView.STATUS_COLUMN].toString();		//2010.09.19 Add H.Orikasa
							String flgValue = curOrderRow[RisSummaryView.EXAMTERMINALID_COLUMN].toString();
							//if (flgValue.length() > 0)														//2010.09.19 Mod H.Orikasa
							if (flgValue.length() > 0 && statusStr == CommonString.STATUS_ISREGISTERED)		//
							{
								if (flgValue == termBean.GetTerminalID())
								{
									//自身が確保
									curOrderRow[RisSummaryView.KAKUHO_COLUMN] = CommonString.IS_KAKUHO_THIS;
								}
								else
								{
									//その他が確保
									curOrderRow[RisSummaryView.KAKUHO_COLUMN] = CommonString.IS_KAKUHO_OTHER;
								}
							}
						}

					}
					// 旧処理
					//if (!curOrderRow.IsNull(RisSummaryView.EXAMTERMINALID_COLUMN) && termBean != null)
					//{
					//    String statusStr = curOrderRow[RisSummaryView.STATUS_COLUMN].toString();		//2010.09.19 Add H.Orikasa
					//    String flgValue = curOrderRow[RisSummaryView.EXAMTERMINALID_COLUMN].toString();
					//    //if (flgValue.length() > 0)														//2010.09.19 Mod H.Orikasa
					//    if (flgValue.length() > 0 && statusStr == CommonString.STATUS_ISREGISTERED)		//
					//    {
					//        if (flgValue == termBean.GetTerminalID())
					//        {
					//            //自身が確保
					//            curOrderRow[RisSummaryView.KAKUHO_COLUMN] = CommonString.IS_KAKUHO_THIS;
					//        }
					//        else
					//        {
					//            //その他が確保
					//            curOrderRow[RisSummaryView.KAKUHO_COLUMN] = CommonString.IS_KAKUHO_OTHER;
					//        }
					//    }
					//}

					// 2010.11.16 Mod T.Nishikubo End



					// オーダコメントID
					if (!curOrderRow.IsNull(RisSummaryView.ORDERCOMMENT_ID_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.ORDERCOMMENT_ID_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetOrderCommentValue(commentDt, flgValue);
						curOrderRow[RisSummaryView.ORDERCOMMENT_ID_NAME_COLUMN] = flgStr;
					}


					// 検像進捗フラグ
					if (!curOrderRow.IsNull(RisSummaryView.KENZOUSTATUS_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.KENZOUSTATUS_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_KENZOUSTATUS, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.KENZOUSTATUS_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 検像緊急フラグ
					if (!curOrderRow.IsNull(RisSummaryView.KENZOUKINKYUU_FLG_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.KENZOUKINKYUU_FLG_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_KENZOUKINKYUU, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.KENZOUKINKYUU_FLG_NAME_COLUMN] = flgStr;
						}
					}


					// 撮影数
					//SetBuiRow内で設定


					// 検査開始時刻
					if (!curOrderRow.IsNull(RisSummaryView.EXAMSTARTDATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_EXAMSTARTDATE_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_TIME_0;
						}

						String examStartDateStr = curOrderRow[RisSummaryView.EXAMSTARTDATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setStr = "";
						try
						{
							Timestamp time = Timestamp.Parse(examStartDateStr);
							setStr = time.toString(formatStr);

						}
						catch
						{
						}
						curOrderRow[RisSummaryView.EXAMSTARTDATE_STRING_COLUMN] = setStr;
					}


					// 検査終了時刻
					if (!curOrderRow.IsNull(RisSummaryView.EXAMENDDATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_EXAMENDDATE_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_TIME_0;
						}

						String examEndDateStr = curOrderRow[RisSummaryView.EXAMENDDATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setStr = "";
						try
						{
							Timestamp time = Timestamp.Parse(examEndDateStr);
							setStr = time.toString(formatStr);

						}
						catch
						{
						}
						curOrderRow[RisSummaryView.EXAMENDDATE_STRING_COLUMN] = setStr;
					}


					// 患者入外
					if (!curOrderRow.IsNull(RisSummaryView.KANJA_NYUGAIKBN_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.KANJA_NYUGAIKBN_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_INOUT, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.KANJA_NYUGAIKBN_STRING_COLUMN] = flgStr;
						}
					}


					// 伝票入外
					if (!curOrderRow.IsNull(RisSummaryView.DENPYO_NYUGAIKBN_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.DENPYO_NYUGAIKBN_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_INOUT, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.DENPYO_NYUGAIKBN_STRING_COLUMN] = flgStr;
						}
					}


					// オーダ区分
					if (!curOrderRow.IsNull(RisSummaryView.SYSTEMKBN_COLUMN))
					{
						String flgValue = curOrderRow[RisSummaryView.SYSTEMKBN_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_SYSTEMKBN, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.SYSTEMKBN_STRING_COLUMN] = flgStr;
						}
					}


					// 患者共通コメント,検査種別コメント
					if (param.GetPatCmtSearchBool())
					{
						if (!curOrderRow.IsNull(RisSummaryView.KANJA_ID_COLUMN))
						{
							String patientId	= curOrderRow[RisSummaryView.KANJA_ID_COLUMN].toString();
							String kTypeID		= curOrderRow[RisSummaryView.KENSATYPE_ID_COLUMN].toString();
							ArrayList patCmtList = patientCommentTbl.GetPatientCommentAll(con, transaction, patientId);
							for (int j=0; j<patCmtList.Count; j++)
							{
								DataRow cmtRow = (DataRow)patCmtList[j];
								String type = cmtRow[RisPatientCommentTbl.PATIENTKENSATYPE_COLUMN].toString();
								String cmt  = cmtRow[RisPatientCommentTbl.PATIENTCOMMENT_COLUMN].toString();
								cmt = cmt.Replace("\r\n", "");
								if (type == MasterUtil.RIS_PATIENTCOMMENT_DEF)
								{
									//患者コメント
									curOrderRow[RisSummaryView.PATIENTCOMMENT_COLUMN] = cmt;
								}
								else if (type == kTypeID)
								{
									//検査種別コメント
									curOrderRow[RisSummaryView.KENSATYPE_COMMENT_COLUMN] = cmt;
								}
							}

						}
					}


					// 連絡メモ
					if (!curOrderRow.IsNull(RisSummaryView.RENRAKU_MEMO_COLUMN))
					{
						String renrakuMemoStr = curOrderRow[RisSummaryView.RENRAKU_MEMO_COLUMN].toString();
						renrakuMemoStr = renrakuMemoStr.Replace("\r\n", " ");
						curOrderRow[RisSummaryView.RENRAKU_MEMO_COLUMN] = renrakuMemoStr.Replace("\n\n", " ");
					}


					// 依頼目的
					if (!curOrderRow.IsNull(RisSummaryView.KENSA_SIJI_COLUMN))
					{
						String kensaSijiStr = curOrderRow[RisSummaryView.KENSA_SIJI_COLUMN].toString();
						kensaSijiStr = kensaSijiStr.Replace("\r\n", " ");
						curOrderRow[RisSummaryView.KENSA_SIJI_COLUMN] = kensaSijiStr.Replace("\n\n", " ");
					}


					// オーダ日時
					if (!curOrderRow.IsNull(RisSummaryView.ORDER_DATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_ORDER_DATE_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_DATE_0;
						}

						String orderDateStr = curOrderRow[RisSummaryView.ORDER_DATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setStr = "";
						try
						{
							Timestamp time  = Timestamp.Parse(orderDateStr);
							setStr = time.toString(formatStr);
						}
						catch
						{
						}
						curOrderRow[RisSummaryView.ORDER_DATE_STRING_COLUMN] = setStr;

						// 当日依頼ｵｰﾀﾞ

						if (setStr.Equals(sysDataTime.toString("yyyy/MM/dd")))
						{
							curOrderRow[RisSummaryView.TODAY_IRAI_ORDER_NAME_COLUMN] = todayIraiStr;
						}
						else
						{
							curOrderRow[RisSummaryView.TODAY_IRAI_ORDER_NAME_COLUMN] = noTodayIraiStr;
						}

					}


					// 同意書ﾁｪｯｸﾌﾗｸﾞ
					if (!curOrderRow.IsNull(RisSummaryView.DOUISHO_CHECK_FLAG_COLUMN))
					{
						String douishoCheckFlagStr = curOrderRow[RisSummaryView.DOUISHO_CHECK_FLAG_COLUMN].toString();

						if (CommonString.DOUISHO_CHECK_ON == douishoCheckFlagStr)
						{
							curOrderRow[RisSummaryView.DOUISHO_CHECK_FLAG_NAME_COLUMN] = CommonString.DOUISHO_CHECK_FLAG_STR;
						}
					}


					// 同意書ﾁｪｯｸ者
					//なし


					// 感染症
					if (!curOrderRow.IsNull(RisSummaryView.INFECTION_COLUMN))
					{
						String infectionStr = curOrderRow[RisSummaryView.INFECTION_COLUMN].toString();
						curOrderRow[RisSummaryView.INFECTION_COLUMN] = infectionStr.Replace("\r\n", " "); //改行を半角スペースに変換
					}


					// 感染症ﾁｪｯｸﾌﾗｸﾞ

					if (!curOrderRow.IsNull(RisSummaryView.INFECTION_CHECK_FLAG_COLUMN))
					{
						String infectionCheckFlagStr = curOrderRow[RisSummaryView.INFECTION_CHECK_FLAG_COLUMN].toString();

						if (CommonString.INFECTION_CHECK_ON == infectionCheckFlagStr)
						{
							curOrderRow[RisSummaryView.INFECTION_CHECK_FLAG_NAME_COLUMN] =  CommonString.INFECTION_CHECK_FLAG_STR;
						}
					}


					// 感染症ﾁｪｯｸ者
					// なし


					// 要造影表示

					try
					{
						//RIS_IDから部位の検査方法IDを取得
						String risStr = curOrderRow[RisSummaryView.RIS_ID_COLUMN].toString();
						if (buiKensaHouhouHashtable.Contains(risStr))
						{
							ArrayList buiKensaHouhouArrayList = (ArrayList)buiKensaHouhouHashtable[risStr];

							for (int j = 0; j < buiKensaHouhouArrayList.Count; j++)
							{
								String kensaHouhouIDStr = buiKensaHouhouArrayList[j].toString();
								//要造影フラグがあるかを確認
								zoueiflgStr = mUtil.FindData(this.mstKensahouhou, MasterUtil.RIS_ZOUEI_FLG, MasterUtil.RIS_KENSAHOUHOU_ID, kensaHouhouIDStr);	//2010.10.29 Mod K.Shinohara
								//zoueiflgStr = mUtil.FindData(houhouDt, MasterUtil.RIS_ZOUEI_FLG, MasterUtil.RIS_KENSAHOUHOU_ID, kensaHouhouIDStr);			//
								if (CommonString.ZOUEI_FLG_1 == zoueiflgStr)
								{
									break;
								}
							}
						}
					}
					catch (Exception e)
					{
						logger.fatal(e);
					}

					//要造影表示ONの場合
					if (CommonString.ZOUEI_FLG_1 == zoueiflgStr)
					{
						curOrderRow[RisSummaryView.ZOUEI_FLG_NAME_COLUMN] = markFlgStr;
					}
					else
					{
						curOrderRow[RisSummaryView.ZOUEI_FLG_NAME_COLUMN] = noMarkFlgStr;
					}



					// 呼出時刻
					if (!curOrderRow.IsNull(RisSummaryView.YOBIDASI_DATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_YOBIDASI_DATE_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_DATE_0;
						}

						String yobidasiDateStr = curOrderRow[RisSummaryView.YOBIDASI_DATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setStr = "";
						try
						{
							Timestamp time  = Timestamp.Parse(yobidasiDateStr);
							setStr = time.toString(formatStr);
						}
						catch
						{
						}
						curOrderRow[RisSummaryView.YOBIDASI_DATE_STRING_COLUMN] = setStr;
					}


					// 検像時刻
					if (!curOrderRow.IsNull(RisSummaryView.KENZOU_DATE_COLUMN))
					{
						//フォーマット文字列を取得する
						String formatStr = GetFormatString(showItemDefineDt, CommonString.LIST_ITEM_ID_KENZOU_DATE_STRING);
						if (formatStr.length() <= 0)
						{
							formatStr = CommonString.LIST_FORMAT_DATE_0;
						}

						String kenzouDateStr = curOrderRow[RisSummaryView.KENZOU_DATE_COLUMN].toString();
						//フォーマットで整形し値を設定する
						String setStr = "";
						try
						{
							Timestamp time  = Timestamp.Parse(kenzouDateStr);
							setStr = time.toString(formatStr);
						}
						catch
						{
						}
						curOrderRow[RisSummaryView.KENZOU_DATE_STRING_COLUMN] = setStr;
					}


					// オーダ検査日,実績検査日(帳票用)

					//実績検査日が空の場合はオーダ検査日を設定する
					if (curOrderRow.IsNull(RisSummaryView.EX_KENSA_DATE_COLUMN))
					{
						curOrderRow[RisSummaryView.EX_KENSA_DATE_COLUMN] = curOrderRow[RisSummaryView.ORDER_KENSA_DATE_COLUMN];
					}



					// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
					//関東労災による特注処理対応
					if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
					{
						// 薬剤発注ﾁｪｯｸﾌﾗｸﾞ
						if (!curOrderRow.IsNull(RisSummaryView.PHARMA_CHECK_FLAG_COLUMN))
						{
							String PHARMACheckFlagStr = curOrderRow[RisSummaryView.PHARMA_CHECK_FLAG_COLUMN].toString();

							if (CommonString.PHARMA_CHECK_ON == PHARMACheckFlagStr)
							{
								curOrderRow[RisSummaryView.PHARMA_CHECK_FLAG_NAME_COLUMN] = CommonString.PHARMA_CHECK_FLAG_STR;
							}
						}


						// 薬剤発注ﾁｪｯｸ者
						//なし

					}
					// 2010.11.16 Add T.Nishikubo End

					// 2011.01.24 Add DD.Chinh Start KANRO-R-19
					// ﾌﾟﾚﾁｪｯｸ進捗
					String precheckStr	= Configuration.GetInstance().GetSystemParamValue(sParam2Dt, RisSystemParamTbl.PRECHECK_STATUS, RisSystemParamTbl.VALUE1_COLUMN);
					if (precheckStr == CommonString.FLG_ON)
					{
						//関東労災による特注処理
						if (curOrderRow.Table.Columns.Contains(RisSummaryView.PRECHECK_STATUS_COLUMN))
						{
							if (curOrderRow.IsNull(RisSummaryView.SIJI_ISI_ID_COLUMN) ||
								curOrderRow[RisSummaryView.SIJI_ISI_ID_COLUMN].toString() == "")
							{
								curOrderRow[RisSummaryView.PRECHECK_STATUS_COLUMN] = CommonString.PRECHECK_STATUS_MI_STR;
							}
							else
							{
								curOrderRow[RisSummaryView.PRECHECK_STATUS_COLUMN] = CommonString.PRECHECK_STATUS_SUMI_STR;
							}
						}
					}

					// 2011.01.24 Add DD.Chinh End

					// 2011.08.04 Add NSK_T.Koudate Start NML-CAT2-004
					// 担当者
					if (!curOrderRow.IsNull(RisSummaryView.MED_PERSON_ID01_COLUMN))
					{
						curOrderRow[RisSummaryView.MED_PERSON_NAME_COLUMN] =
							TeamInfoBeanUtil.GetMedPersonStringSimple(curOrderRow);
					}


					// 2011.08.04 Add NSK_T.Koudate End

					// 2011.11.15 Mod NSK_H.Hashimoto Start OMH-1-05
					// 受付番号
					if (!curOrderRow.IsNull(RisSummaryView.RECEIPTNUMBER_COLUMN))
					{
						// 受付番号:グループ単位発行の場合
						if ((Configuration.GetInstance().GetSystemParam().GetReceiptRuleValue1Str() == CommonString.SYSTEMPARAM_1)
						&&  (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH)))
						{
							String strInitial = curOrderRow[RisExMainTbl.RECEIPT_INITIAL_CHAR_COLUMN].toString();
							if (strInitial == null)
							{
								strInitial = "";
							}
							else
							{
								strInitial = strInitial.Replace(" ", "");
							}

							String strReceiptNumber = curOrderRow[RisSummaryView.RECEIPTNUMBER_COLUMN].toString();
							if (String.IsNullOrEmpty(strReceiptNumber))
							{
								curOrderRow[RisSummaryView.RECEIPT_INITIAL_CHAR_COLUMN] = "";
							}
							else
							{
								if (strReceiptNumber.length() > 3)
								{
									strReceiptNumber = strReceiptNumber.Remove(3);
								}
								// 頭文字＋３桁番号(計４文字)
								curOrderRow[RisSummaryView.RECEIPT_INITIAL_CHAR_COLUMN] = strInitial + strReceiptNumber;
							}
						}
						else
						{
							curOrderRow[RisSummaryView.RECEIPT_INITIAL_CHAR_COLUMN] =
								curOrderRow[RisSummaryView.RECEIPTNUMBER_COLUMN].toString();
						}
					}

					// 2011.11.15 Mod NSK_H.Hashimoto End

					// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
					// オーダ種別
					if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
					{
						if (!curOrderRow.IsNull(RisSummaryView.EXAM_TIMING_COLUMN))
						{
							String flgValue = curOrderRow[RisSummaryView.EXAM_TIMING_COLUMN].toString();
							ArrayList GetExamTimingDefineList = Configuration.GetInstance().GetExamTimingDefineList(eTimigDt, flgValue);

							curOrderRow[RisSummaryView.EXAM_TIMING_LABEL_COLUMN]	=	GetExamTimingDefineList[CommonString.EXAM_TIMING_LABEL].toString();
						}
					}

					// 2011.11.22 Add NSK_M.Ochiai End

				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		finally
		{
			// end log
			logger.debug("end");
		}

        return retDt;
	}
	*/
    /*
	/// <summary>
	/// フォーマットを設定する(履歴)
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="transaction">DBトランザクションオブジェクト</param>
	/// <param name="dt">オーダ情報</param>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	public DataTable OrderFormatDataHistory(Connection con, Transaction transaction, DataTable dt, OrderSearchParameter param)
	{
		// begin log
		logger.debug("begin");

		MasterInfoTbl masterTbl = new MasterInfoTbl();
		RisSummaryView view = new RisSummaryView();
		RisExZoueizaiInfoTbl exZoueizaiTbl = new RisExZoueizaiInfoTbl();
		DataTable retDt = null;
		MasterUtil mUtil = new MasterUtil();
		try
		{
			//不足カラムの追加
			AddColumns(dt);

			if (dt == null || dt.Rows.Count <= 0)
			{
				retDt = dt;
			}
			else
			{
				//各マスタの取得
				GetMaster(con, transaction, masterTbl); //2010.10.29 Add K.Shinohara
				DataTable codeDt	= masterTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_CODECONVERT,  false);				//項目変換マスタ
				DataTable statusDt	= masterTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_STATUSDEFINE, false);				//ステータス定義
				DataTable partsDt	= masterTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_PARTSMASTER,  false);				//器材マスタ
				DataTable eColorDt	= masterTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_EXTEND_COLORDEFINE, false);			//拡張色定義

				// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
				if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
				{
					DataTable  eTimigDt	= masterTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_EXAMTIMINGDEFINE, false);				//器材マスタ
				}
				// 2011.11.22 Add NSK_M.Ochiai End

				//不足カラムの追加
				AddColumns(dt);

				DataTable showItemDefineDt = null;
				// 表示項目定義を取得
				if (param.GetShowItemDefineDt() != null)
				{
					showItemDefineDt = param.GetShowItemDefineDt();
				}


				//部位情報を設定する
				// 2011.08.18 Mod T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
				retDt = SetBuiInfo(con, transaction, dt, param);
				// コメント
				//// 2011.08.05 Mod H.Satou@MERX Start NML-CAT2-036
				//retDt = SetBuiInfo(con, transaction, dt, param, codeDt);
				//// コメント
				////retDt = SetBuiInfo(con, transaction, dt, param);
				//
				//// 2011.08.05 Mod H.Satou@MERX End

				// 2011.08.18 Mod T.Ootsuka@MERX End

				//造影剤取得判定
				boolean getZoueizaiFlg = false;
				String zoueizaiColName = "";
				DataRow rowZName  = GetItemRow(showItemDefineDt, CommonString.HISTORY_ITEM_ID_ZOUEIZAI_NAME);			//造影剤
				if (rowZName != null)
				{
					//名称,略称判断
					if (rowZName[MasterUtil.RIS_NAME_CHANGE_FLG].toString() == CommonString.LIST_NAMECHANGE_NORMAL.toString())
					{
						zoueizaiColName = RisHistoryTabSummaryView.ZOUEIZAI_NAME_COLUMN;
					}
					else
					{
						zoueizaiColName = RisHistoryTabSummaryView.ZOUEIZAI_RYAKUNAME_COLUMN;
					}
					getZoueizaiFlg = true;
				}

				//検中他検査
				ExtendColorDefineBean exColorDef = CommonUtil.GetExtendColorDefine(eColorDt, CommonString.EXCOLORDEF_INOPERATION);

				// 1件毎の処理
				for (int i = 0; i < dt.Rows.Count; i++)
				{
					// 現在のレコード
					DataRow curOrderRow = dt.Rows[i];

					// 表示データの加工を行う

					// ステータス
					if (!curOrderRow.IsNull(RisSummaryView.STATUS_COLUMN))
					{
						try
						{
							String statusLabel = curOrderRow[RisSummaryView.STATUS_COLUMN].toString();
							String statusLabeltr = Configuration.GetInstance().GetStatusString(statusDt, statusLabel);
							curOrderRow[RisSummaryView.STATUS_NAME_COLUMN] = statusLabeltr;
						}
						catch (Exception ex)
						{
							logger.fatal(ex);
						}
					}


					// 予約
					if (!curOrderRow.IsNull(RisSummaryView.KENSA_STARTTIME_COLUMN))
					{
						try
						{
							//フォーマット文字列を取得する
							String formatStr = GetFormatString(showItemDefineDt, CommonString.HISTORY_ITEM_ID_KENSA_STARTTIME_STRING);
							if (formatStr.length() <= 0)
							{
								formatStr = CommonString.LIST_FORMAT_TIME_0;
							}

							String kensaTimeStr = curOrderRow[RisSummaryView.KENSA_STARTTIME_COLUMN].toString();
							//検査時刻を文字列に変換する
							kensaTimeStr = TextUtil.ConvertKensaTimeString(kensaTimeStr);
							//フォーマットで整形し値を設定する
							String setStr = "";
							try
							{
								Timestamp time = Timestamp.Parse(kensaTimeStr);
								setStr = time.toString(formatStr);

							}
							catch
							{
							}
							curOrderRow[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = setStr;
						}
						catch (Exception ex)
						{
							logger.fatal(ex);
						}
					}


					// 検査日
					if (!curOrderRow.IsNull(RisSummaryView.KENSA_DATE_COLUMN))
					{
						try
						{
							//フォーマット文字列を取得する
							String formatStr = GetFormatString(showItemDefineDt, CommonString.HISTORY_ITEM_ID_KENSA_DATE);
							if (formatStr.length() <= 0)
							{
								formatStr = CommonString.LIST_FORMAT_DATE_0;
							}

							String kensaDateStr = curOrderRow[RisSummaryView.KENSA_DATE_COLUMN].toString();
							//フォーマットで整形し値を設定する
							String setStr = "";
							try
							{
								String yyyyStr = kensaDateStr.SubString(0, 4);
								String mmStr   = kensaDateStr.SubString(4, 2);
								String ddStr   = kensaDateStr.SubString(6, 2);
								String dateStr = yyyyStr + "/" + mmStr + "/" + ddStr;
								Timestamp time  = Timestamp.Parse(dateStr);
								setStr = time.toString(formatStr);

								// 2010.08.26 Del K.Shinohara Start
								// 検査時刻は影響しない
								////特殊処理
								//String kensaTimeStr = curOrderRow[RisSummaryView.KENSA_STARTTIME_COLUMN].toString();
								//if (kensaTimeStr == CommonString.LIST_FORMAT_TIME_NULL1 ||
								//kensaTimeStr == CommonString.LIST_FORMAT_TIME_NULL2)
								//{
								//    //特定時刻の場合は空にする
								//    setStr = "";
								//}
								// 2010.08.26 Del K.Shinohara End
							}
							catch
							{
							}
							curOrderRow[RisSummaryView.KENSA_DATE_STRING_COLUMN] = setStr;
						}
						catch (Exception ex)
						{
							logger.fatal(ex);
						}
					}


					// 検査開始時刻
					if (!curOrderRow.IsNull(RisSummaryView.EXAMSTARTDATE_COLUMN))
					{
						try
						{
							//フォーマット文字列を取得する
							String formatStr = GetFormatString(showItemDefineDt, CommonString.HISTORY_ITEM_ID_EXAMSTARTDATE_STRING);
							if (formatStr.length() <= 0)
							{
								formatStr = CommonString.LIST_FORMAT_TIME_0;
							}

							String examStartDateStr = curOrderRow[RisSummaryView.EXAMSTARTDATE_COLUMN].toString();
							//フォーマットで整形し値を設定する
							String setStr = "";
							try
							{
								Timestamp time = Timestamp.Parse(examStartDateStr);
								setStr = time.toString(formatStr);

							}
							catch
							{
							}
							curOrderRow[RisSummaryView.EXAMSTARTDATE_STRING_COLUMN] = setStr;
						}
						catch (Exception ex)
						{
							logger.fatal(ex);
						}
					}


					// 検査終了時刻
					if (!curOrderRow.IsNull(RisSummaryView.EXAMENDDATE_COLUMN))
					{
						try
						{
							//フォーマット文字列を取得する
							String formatStr = GetFormatString(showItemDefineDt, CommonString.HISTORY_ITEM_ID_EXAMENDDATE_STRING);
							if (formatStr.length() <= 0)
							{
								formatStr = CommonString.LIST_FORMAT_TIME_0;
							}

							String examEndDateStr = curOrderRow[RisSummaryView.EXAMENDDATE_COLUMN].toString();
							//フォーマットで整形し値を設定する
							String setStr = "";
							try
							{
								Timestamp time = Timestamp.Parse(examEndDateStr);
								setStr = time.toString(formatStr);

							}
							catch
							{
							}
							curOrderRow[RisSummaryView.EXAMENDDATE_STRING_COLUMN] = setStr;
						}
						catch (Exception ex)
						{
							logger.fatal(ex);
						}
					}


					// 患者入外
					if (!curOrderRow.IsNull(RisSummaryView.KANJA_NYUGAIKBN_COLUMN))
					{
						try
						{
						}
						catch (Exception ex)
						{
							logger.fatal(ex);
						}
						String flgValue = curOrderRow[RisSummaryView.KANJA_NYUGAIKBN_COLUMN].toString();
						String flgStr = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_INOUT, flgValue);
						if (flgStr.Trim().length() > 0)
						{
							curOrderRow[RisSummaryView.KANJA_NYUGAIKBN_STRING_COLUMN] = flgStr;
						}
					}


					// 使用造影剤

					if (!curOrderRow.IsNull(RisSummaryView.RIS_ID_COLUMN))
					{
						try
						{
							//造影剤列が存在する場合のみ処理する
							if (getZoueizaiFlg)
							{
								String zoueizaiStr = "";
								//RisIDを元に造影剤を取得
								String risID = curOrderRow[RisSummaryView.RIS_ID_COLUMN].toString();
								ArrayList zoueizaiList = exZoueizaiTbl.GetZoueizaiListByRisID(con, transaction, risID);
								for (int j=0; j<zoueizaiList.Count; j++)
								{
									ExZoueizaiBean zBean = (ExZoueizaiBean)zoueizaiList[j];
									if (zBean != null && zBean.GetPartsID().length() > 0)
									{
										DataRow partsRow = mUtil.FindDataRow(partsDt, MasterUtil.RIS_ZOUEIZAI_ID, zBean.GetPartsID());
										if (partsRow != null)
										{
											//造影剤フラグONの場合追加する
											String zoueizaiFlg = partsRow[MasterUtil.RIS_ZOUEIZAI_FLAG].toString();
											if (zoueizaiFlg == CommonString.FLG_ON)
											{
												//値の判断(名称or略称)
												String nameStr = "";
												if (zoueizaiColName == RisHistoryTabSummaryView.ZOUEIZAI_NAME_COLUMN)
												{
													nameStr = partsRow[MasterUtil.RIS_ZOUEIZAI_NAME].toString();
												}
												else if (zoueizaiColName == RisHistoryTabSummaryView.ZOUEIZAI_RYAKUNAME_COLUMN)
												{
													nameStr = partsRow[MasterUtil.RIS_ZOUEIZAI_RYAKUNAME].toString();
												}

												if (zoueizaiStr.length() <= 0)
												{
													zoueizaiStr += nameStr;
												}
												else
												{
													zoueizaiStr += ", " + nameStr;
												}
											}
										}
									}
								}
								curOrderRow[zoueizaiColName] = zoueizaiStr;
							}
						}
						catch (Exception ex)
						{
							logger.fatal(ex);
						}
					}



					// 他検査
					if (exColorDef.GetColorMode() == CommonString.FLG_ON)
					{
						if (!curOrderRow.IsNull(RisSummaryView.RIS_ID_COLUMN) &&
							!curOrderRow.IsNull(RisSummaryView.KANJA_ID_COLUMN) &&
							!curOrderRow.IsNull(RisSummaryView.KENSA_DATE_COLUMN))
						{
							String takensaStr= "";
							String risID     = curOrderRow[RisSummaryView.RIS_ID_COLUMN].toString();
							String kanjaID   = curOrderRow[RisSummaryView.KANJA_ID_COLUMN].toString();
							String kensaDate = curOrderRow[RisSummaryView.KENSA_DATE_COLUMN].toString();

							//他検査情報を取得
							OrderSearchParameter oParam = new OrderSearchParameter();
							oParam.SetRisID(risID);
							oParam.SetKanjaID(kanjaID);
							oParam.SetKensaDate(kensaDate);
							DataTable takensaDt = view.SearchTakensaOrderData(con, transaction, oParam);

							for (int j=0; j<takensaDt.Rows.Count; j++)
							{
								DataRow tRow = takensaDt.Rows[j];

								String rowRisID			= tRow[RisSummaryView.RIS_ID_COLUMN].toString();
								String rowKTypeRName	= tRow[RisSummaryView.KENSATYPE_RYAKUNAME_COLUMN].toString();
								String rowStatus		= tRow[RisSummaryView.STATUS_COLUMN].toString();
								String rowStatusShort	= Configuration.GetInstance().GetStatusString(statusDt, rowStatus);

								//自オーダ以外
								if (rowRisID != risID)
								{
									//検査種別略+ｽﾃｰﾀｽ文字列
									String lineStr = rowKTypeRName + rowStatusShort;
									if (takensaStr.length() <= 0)
									{
										takensaStr  = lineStr;
									}
									else
									{
										takensaStr += " " + lineStr;
									}

									//検中の場合
									if (rowStatus == CommonString.STATUS_INOPERATION)
									{
										//検中他検査フラグON
										curOrderRow[RisSummaryView.TAKENSA_INOPE_FLG_COLUMN] = CommonString.FLG_ON;
									}
								}
							}
							curOrderRow[RisSummaryView.TAKENSA_FLG_NAME_COLUMN] = takensaStr;
						}
					}



				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		finally
		{
			// end log
			logger.debug("end");
		}

		return retDt;
	}
	*/

	//2010.10.29 Add K.Shinohara Start
	/// <summary>
	/// マスターを取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="transaction">DBトランザクションオブジェクト</param>
	/// <param name="masterTbl">マスタ定義</param>
	private void GetMaster(Connection con, MasterInfoTbl masterTbl) throws Exception
	{
		if (con != null && masterTbl != null)
		{
			this.mstKensaType	= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_KENSATYPEMASTER, false);
			this.mstBui			= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_BUIMASTER, false);
			this.mstHoukou		= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_HOUKOUMASTER, false);
			this.mstSayuu		= masterTbl.GetMasterDataTable(con, MasterUtil.RIS_SAYUUMASTER, false);
			this.mstKensahouhou = masterTbl.GetMasterDataTable(con, MasterUtil.RIS_KENSAHOUHOUMASTER, false);
		}
	}
	//2010.10.29 Add K.Shinohara End

	/// <summary>
	/// 不足カラムの追加
	/// </summary>
	/// <param name="dt">情報</param>
	private void AddColumns(DataTable dt)
	{
		/* 一ノ瀬保留
		try
		{
			if (dt != null)
			{
				// 追加

				//部位用
				if (!dt.Columns.Contains(RisBuiSummaryView.BUI_NO_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.BUI_NO_COLUMN);
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.SAYUU_NAME_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.SAYUU_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.SAYUU_RYAKUNAME_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.SAYUU_RYAKUNAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.BUI_RYAKUNAME_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.BUI_RYAKUNAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.HOUKOU_RYAKUNAME_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.HOUKOU_RYAKUNAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.KENSAHOUHOU_RYAKUNAME_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.KENSAHOUHOU_RYAKUNAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.BUICOMMENT_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.BUICOMMENT_COLUMN);
				}

				//複数部位用
				if (!dt.Columns.Contains(RisBuiSummaryView.BUI_COUNT_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.BUI_COUNT_COLUMN, typeof(int));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.BUI_NAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.BUI_NAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.BUI_RNAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.BUI_RNAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.SATUEI_COUNT_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.SATUEI_COUNT_STRING_COLUMN, typeof(String));
				}
				// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
				if (!dt.Columns.Contains(RisBuiSummaryView.SATUEISTATUS_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.SATUEISTATUS_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.SATUEISTATUSNAME_STRING_COLUMN))
				{
					dt.Columns.Add(RisBuiSummaryView.SATUEISTATUSNAME_STRING_COLUMN, typeof(String));
				}
				if (!dt.Columns.Contains(RisBuiSummaryView.SATUEISTATUSSORT_COLUMN ))
				{
					dt.Columns.Add(RisBuiSummaryView.SATUEISTATUSSORT_COLUMN, typeof(String));
				}
				// 2011.08.05 Add H.Satou@MERX End

				//表示用
				if (!dt.Columns.Contains(RisSummaryView.GYOUMU_KBN_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.GYOUMU_KBN_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.RECEIPTDATE2_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.RECEIPTDATE2_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.KANJA_SYOKAI_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.KANJA_SYOKAI_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.DOUISHO_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.DOUISHO_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.YUUSEN_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.YUUSEN_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.SEX_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.SEX_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.IRAI_SECTION_RYAKU_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.IRAI_SECTION_RYAKU_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.TAKENSA_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.TAKENSA_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.SIKYU_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.SIKYU_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.INPUTDATE_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.INPUTDATE_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.INPUTTIME_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.INPUTTIME_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.SEISAN_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.SEISAN_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.SYOTISITU_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.SYOTISITU_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.DOKUEI_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.DOKUEI_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.RI_ORDER_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.RI_ORDER_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.ORDERCOMMENT_ID_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.ORDERCOMMENT_ID_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.KENZOUSTATUS_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.KENZOUSTATUS_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.KENZOUKINKYUU_FLG_NAME_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.KENZOUKINKYUU_FLG_NAME_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.EXAMSTARTDATE_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.EXAMSTARTDATE_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.EXAMENDDATE_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.EXAMENDDATE_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.KANJA_NYUGAIKBN_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.KANJA_NYUGAIKBN_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.DENPYO_NYUGAIKBN_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.DENPYO_NYUGAIKBN_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.SYSTEMKBN_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.SYSTEMKBN_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.YOBIDASI_DATE_STRING_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.YOBIDASI_DATE_STRING_COLUMN);
				}
				if (!dt.Columns.Contains(RisSummaryView.TAKENSA_INOPE_FLG_COLUMN))
				{
					dt.Columns.Add(RisSummaryView.TAKENSA_INOPE_FLG_COLUMN);
				}
				// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
				if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
				{
					if (!dt.Columns.Contains(RisSummaryView.EXAM_TIMING_LABEL_COLUMN))
					{
						dt.Columns.Add(RisSummaryView.EXAM_TIMING_LABEL_COLUMN);
					}
				}// 2011.11.22 Add NSK_M.Ochiai End


			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		*/
	}

	/*
	/// <summary>
	/// フォーマット文字列を取得する
	/// </summary>
	/// <param name="dt">表示項目定義情報</param>
	/// <param name="itemID">アイテムID</param>
	/// <returns></returns>
	private String GetFormatString(DataTable dt, int itemID)
	{
		String retStr = "";

		if (dt != null)
		{
			for (int i=0; i<dt.Rows.Count; i++)
			{
				DataRow row = dt.Rows[i];

				if (row[MasterUtil.RIS_ITEMID].toString() == itemID.toString())
				{
					retStr = row[MasterUtil.RIS_DISPLAYFORMAT].toString();

					break;
				}
			}
		}

		return retStr;
	}

	/// <summary>
	/// 表示項目定義を取得する
	/// </summary>
	/// <param name="dt">表示項目定義情報</param>
	/// <param name="itemID">アイテムID</param>
	/// <returns></returns>
	private DataRow GetItemRow(DataTable dt, int itemID)
	{
		DataRow retRow = null;

		if (dt != null)
		{
			for (int i=0; i<dt.Rows.Count; i++)
			{
				DataRow row = dt.Rows[i];

				if (row[MasterUtil.RIS_ITEMID].toString() == itemID.toString())
				{
					retRow = row;

					break;
				}
			}
		}

		return retRow;
	}
	*/
	/*
	// 2010.10.29 Mod K.Shinohara Start
	// 2011.08.18 Mod T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
	/// <summary>
	/// 部位情報を設定する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="transaction">DBトランザクションオブジェクト</param>
	/// <param name="orderDt">オーダ情報</param>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	private DataTable SetBuiInfo(Connection con, DataTable orderDt, OrderSearchParameter param)
	// コメント
	//// 2011.08.05 Mod H.Satou@MERX Start NML-CAT2-036
	///// <summary>
	///// 部位情報を設定する
	///// </summary>
	///// <param name="con">データベース接続オブジェクト</param>
	///// <param name="transaction">DBトランザクションオブジェクト</param>
	///// <param name="orderDt">オーダ情報</param>
	///// <param name="param">検索条件</param>
	///// <param name="codeDt">項目変換ﾃｰﾌﾞﾙ</param>
	///// <returns></returns>
	//private DataTable SetBuiInfo(Connection con, Transaction trans, DataTable orderDt, OrderSearchParameter param, DataTable codeDt)
	//// コメント
	/////// <summary>
	/////// 部位情報を設定する
	/////// </summary>
	/////// <param name="con">データベース接続オブジェクト</param>
	/////// <param name="transaction">DBトランザクションオブジェクト</param>
	/////// <param name="orderDt">オーダ情報</param>
	/////// <param name="param">検索条件</param>
	/////// <returns></returns>
	////private DataTable SetBuiInfo(Connection con, Transaction trans, DataTable orderDt, OrderSearchParameter param)
	//
	//// 2011.08.05 Mod H.Satou@MERX End

	// 2011.08.18 Mod T.Ootsuka@MERX End
	{
		RisOrderBuiInfoTbl orderBuiTbl	= new RisOrderBuiInfoTbl();
		RisExBuiInfoTbl    exBuiTbl		= new RisExBuiInfoTbl();
		RisExFilmInfoTbl exFilmInfoTbl	= new RisExFilmInfoTbl();

		Hashtable orderBuiHash	= new Hashtable();	//オーダ部位Hash(Key:RisID, Value:オーダ部位リスト)
		Hashtable exBuiHash		= new Hashtable();	//実績部位Hash  (Key:RisID, Value:実績部位リスト)

		// 部位情報の取得

		//オーダ情報から対象となる検査日を求める
		OrderSearchParameter oParam = new OrderSearchParameter();
		Timestamp startDate = Timestamp.MaxValue;
		Timestamp endDate   = Timestamp.MinValue;
		boolean kanjaIDOnlyFlg = true;		// 患者ID指定フラグ
		boolean unknownDateFlg = false;	// 日未定フラグ
		boolean isRptUnKnownBool = false;	// 2011.08.19 Add H.Orikasa A0060(修正)

		// 日未定日付を取得しておく
		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);

		//オーダ情報のループ
		String kanjaIDStr = "";
		for (int i=0; i<orderDt.Rows.Count; i++)
		{
			String kanjaID		= orderDt.Rows[i][RisSummaryView.KANJA_ID_COLUMN].toString();
			Timestamp orderDate	= CommonUtil.ConvertKensaDate(orderDt.Rows[i][RisSummaryView.ORDER_KENSA_DATE_COLUMN].toString());
			Timestamp exDate		= CommonUtil.ConvertKensaDate(orderDt.Rows[i][RisSummaryView.EX_KENSA_DATE_COLUMN].toString());
			if (exDate == Timestamp.MinValue)
			{
				exDate = orderDate;
			}

			if (kanjaIDOnlyFlg && kanjaIDStr.length() > 0)
			{
				// 複数患者が混在している場合はフラグをOFFにする
				if (!kanjaIDStr.Equals(kanjaID))
				{
					kanjaIDOnlyFlg = false;
				}
			}
			else if (kanjaIDStr.length() <= 0)
			{
				// 初回患者IDを設定
				kanjaIDStr = kanjaID;
			}

			// 日未定検査の場合はフラグをONにする（検索条件の日付は変更しない）
			if (exDate.toString(CommonString.LIST_FORMAT_DATE_2).Equals(unknownDateStr))
			{
				unknownDateFlg = true;
				continue;
			}

			//日付を用意する
			CommonUtil.CheckDateSpan(ref startDate, ref endDate, orderDate, exDate, ref isRptUnKnownBool);	// 2011.08.19 Mod H.Orikasa Start A0060(修正)
			//CommonUtil.CheckDateSpan(ref startDate, ref endDate, exDate);									//
		}
		oParam.SetExecutePeriodStartDate(startDate);
		oParam.SetExecutePeriodEndDate(endDate);
		oParam.SetKanjaID(kanjaIDStr);
		oParam.SetKanjaIDOnlyBool(kanjaIDOnlyFlg);
		oParam.SetUnKnownDateBool(unknownDateFlg);
		oParam.SetRptUnKnownDateFlg(isRptUnKnownBool);	// 2011.08.19 Add H.Orikasa Start A0060(修正)

		//オーダ部位情報を取得
		DataTable orderBuiDt = orderBuiTbl.GetListOrderBuiDataTable(con, trans, oParam);
		for (int i=0; i<orderBuiDt.Rows.Count; i++)
		{
			DataRow orderBuiRow = orderBuiDt.Rows[i];
			String risID = orderBuiRow[RisOrderBuiInfoTbl.RIS_ID_COLUMN].toString();
			if (orderBuiHash.ContainsKey(risID))
			{
				//リストへ追加
				ArrayList buiList = (ArrayList)orderBuiHash[risID];
				buiList.Add(orderBuiRow);
			}
			else
			{
				//リストを作成
				ArrayList buiList = new ArrayList();
				buiList.Add(orderBuiRow);
				orderBuiHash.Add(risID, buiList);
			}
		}

		//実績部位情報を取得
		DataTable exBuiDt = exBuiTbl.GetListExBuiDataTable(con, trans, oParam);
		for (int i=0; i<exBuiDt.Rows.Count; i++)
		{
			DataRow exBuiRow = exBuiDt.Rows[i];
			String risID = exBuiRow[RisExBuiInfoTbl.RIS_ID_COLUMN].toString();
			if (exBuiHash.ContainsKey(risID))
			{
				//リストへ追加
				ArrayList buiList = (ArrayList)exBuiHash[risID];
				buiList.Add(exBuiRow);
			}
			else
			{
				//リストを作成
				ArrayList buiList = new ArrayList();
				buiList.Add(exBuiRow);
				exBuiHash.Add(risID, buiList);
			}
		}



		// 撮影情報の取得(撮影数用)
		Hashtable exFilmHash = new Hashtable();
		if (param.GetShotCntSearchBool())
		{
			//一覧用-実績フィルム情報の取得
			exFilmHash = exFilmInfoTbl.GetListFilmList(con, trans, oParam);
		}


		// オーダ情報のループ

		for (int i = 0; i < orderDt.Rows.Count; i++)
		{
			DataRow row = orderDt.Rows[i];

			String risIDStr		= row[RisSummaryView.RIS_ID_COLUMN].toString();
			String statusStr	= row[RisSummaryView.STATUS_COLUMN].toString();

			//部位リストをステータスにより判断する
			boolean exBuiBool = false;
			ArrayList buiList = new ArrayList();
			if (TextUtil.ParseStringToInt(statusStr) >= TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED))
			{
				//受済以降→実績部位リスト
				if (exBuiHash.ContainsKey(risIDStr))
				{
					buiList = (ArrayList)exBuiHash[risIDStr];
				}
				exBuiBool = true;
			}
			else
			{
				//受済以前→オーダ部位リスト
				if (orderBuiHash.ContainsKey(risIDStr))
				{
					buiList = (ArrayList)orderBuiHash[risIDStr];
				}
			}

			//検査方法IDリストの作成(要造影フラグ用)
			ArrayList kensaHouhouIDList = new ArrayList();
			for (int buiDtCount = 0; buiDtCount < buiList.Count; buiDtCount++)
			{
				DataRow buiRow = (DataRow)buiList[buiDtCount];
				kensaHouhouIDList.Add(buiRow[RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN].toString());
			}
			buiKensaHouhouHashtable.Add(risIDStr, kensaHouhouIDList);

			//部位の設定
			// 2011.08.18 Mod T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
			SetBuiRow(row, buiList, exFilmHash, param, exBuiBool);
			// コメント
			//// 2011.08.05 Mod H.Satou@MERX Start NML-CAT3-036
			//SetBuiRow(row, buiList, exFilmHash, param, exBuiBool, codeDt);
			//// コメント
			////SetBuiRow(row, buiList, exFilmHash, param, exBuiBool);
			//
			//// 2011.08.05 Mod H.Satou@MERX End

			// 2011.08.18 Mod T.Ootsuka@MERX End
		}



		return orderDt;
	}
	*/
	/*
	// 2011.08.18 Mod T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
	/// <summary>
	/// 部位の設定
	/// </summary>
	/// <param name="row">オーダ情報行</param>
	/// <param name="buiList">部位情報</param>
	/// <param name="exFilmHash">実績撮影情報</param>
	/// <param name="param">検索条件</param>
	/// <param name="exBuiBool">実績部位フラグ</param>
	private void SetBuiRow(DataRow row, ArrayList buiList, Hashtable exFilmHash, OrderSearchParameter param, boolean exBuiBool)
	// コメント
	//// 2011.08.05 Mod H.Satou@MERX Start NML-CAT3-036
	///// <summary>
	///// 部位の設定
	///// </summary>
	///// <param name="row">オーダ情報行</param>
	///// <param name="buiList">部位情報</param>
	///// <param name="exFilmHash">実績撮影情報</param>
	///// <param name="param">検索条件</param>
	///// <param name="exBuiBool">実績部位フラグ</param>
	///// <param name="codeDt">項目変換ﾃｰﾌﾞﾙ</param>
	//private void SetBuiRow(DataRow row, ArrayList buiList, Hashtable exFilmHash, OrderSearchParameter param, boolean exBuiBool, DataTable codeDt)
	////// コメント
	/////// <summary>
	/////// 部位の設定
	/////// </summary>
	/////// <param name="row">オーダ情報行</param>
	/////// <param name="buiList">部位情報</param>
	/////// <param name="exFilmHash">実績撮影情報</param>
	/////// <param name="param">検索条件</param>
	/////// <param name="exBuiBool">実績部位フラグ</param>
	////private void SetBuiRow(DataRow row, ArrayList buiList, Hashtable exFilmHash, OrderSearchParameter param, boolean exBuiBool)
	////
	// 2011.08.05 Mod H.Satou@MERX End

	// 2011.08.18 Mod T.Ootsuka@MERX End
	{
		MasterUtil masterUtil = new MasterUtil();

		if (row != null && buiList.Count > 0)
		{
			String risID = row[RisSummaryView.RIS_ID_COLUMN].toString();

			StringBuilder buiNames			= new StringBuilder();
			StringBuilder buiRNames			= new StringBuilder();
			StringBuilder sayuuNames		= new StringBuilder();
			StringBuilder sayuuRNames		= new StringBuilder();
			StringBuilder houkouNames		= new StringBuilder();
			StringBuilder houkouRNames		= new StringBuilder();
			StringBuilder khouhouNames		= new StringBuilder();
			StringBuilder khouhouRNames		= new StringBuilder();
			StringBuilder buiComments		= new StringBuilder();
			StringBuilder khouhouIDs		= new StringBuilder();
			StringBuilder buiCommentLines	= new StringBuilder();
			StringBuilder satueiCounts		= new StringBuilder();
			// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
			StringBuilder satueiIDs			= new StringBuilder();
			StringBuilder satueiNames		= new StringBuilder();
			StringBuilder satueiSorts		= new StringBuilder();
			// 2011.08.05 Add H.Satou@MERX End

			//区切り文字を決定する
			String charStr = "\r\n";
			if (param.GetHistoryFlg())
			{
				charStr = ",";
			}

			//部位情報のループ
			int buiCount = 0;
			for (int i = 0; i < buiList.Count; i++)
			{
				DataRow b_row			= (DataRow)buiList[i];
				//
				String b_RisID			= b_row[RisBuiSummaryView.RIS_ID_COLUMN].toString();
				String b_no				= b_row[RisBuiSummaryView.NO_COLUMN].toString();
				String b_buiID			= b_row[RisBuiSummaryView.BUI_ID_COLUMN].toString();
				String b_houkouID		= b_row[RisBuiSummaryView.HOUKOU_ID_COLUMN].toString();
				String b_sayuuID		= b_row[RisBuiSummaryView.SAYUU_ID_COLUMN].toString();
				String b_khouhouID		= b_row[RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN].toString();
				String b_buiComment		= b_row[RisBuiSummaryView.BUICOMMENT_COLUMN].toString().Replace("\r\n", " ");
				String b_buiCommentLine = b_row[RisBuiSummaryView.BUICOMMENT_COLUMN].toString();
				String b_buiName		= "";
				String b_buiRName		= "";
				String b_sayuuName		= "";
				String b_sayuuRName		= "";
				String b_houkouName		= "";
				String b_houkouRName	= "";
				String b_khouhouName	= "";
				String b_khouhouRName	= "";
				String b_status			= "";
				String b_satueiCount	= "0";

				// 2011.08.18 Mod T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
				// 部位進捗変換情報取得
				Hashtable codeConvBuiStatus		= Configuration.GetInstance().GetCodeConvertBuiStatus();
				DataRow buiStatusRow			= (DataRow)codeConvBuiStatus[CommonString.SATUEISTATUS_MI];
				String b_statusName = "";
				String b_statusShowOrder = "0";
				if (buiStatusRow != null)
				{
					// 進捗文字列取得
					b_statusName	= buiStatusRow[MasterUtil.RIS_VALUELABEL].toString();
					// ShowOrder取得
					b_statusShowOrder	= buiStatusRow[MasterUtil.RIS_SHOWORDER].toString();
				}

				// コメント
				//// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
				//String b_statusName = Configuration.GetInstance().GetCodeConvertValue(codeDt, CommonString.CODECONVERT_ID_BUISTATUS, CommonString.SATUEISTATUS_MI);
				//ArrayList buiStatusList = Configuration.GetInstance().GetCodeConvertList(codeDt, CommonString.CODECONVERT_ID_BUISTATUS);
				//String b_statusShowOrder = "0";
				//if (buiStatusList != null)
				//{
				//    for (int j = 0; j < buiStatusList.Count; j++)
				//    {
				//        DataRow buiRow = (DataRow)buiStatusList[j];
				//        if (CommonString.SATUEISTATUS_MI == buiRow[MasterUtil.RIS_ITEMVALUE].toString())
				//        {
				//            b_statusShowOrder = buiRow[MasterUtil.RIS_SHOWORDER].toString();
				//            break;
				//        }
				//    }
				//}
				//// 2011.08.05 Add H.Satou@MERX End

				// 2011.08.18 Mod T.Ootsuka@MERX End

				// 各名称の補填

				//部位情報
				DataRow mBuiRow		= masterUtil.FindDataRow(this.mstBui, MasterUtil.RIS_BUI_ID, b_buiID);
				if (mBuiRow != null)
				{
					b_buiName	= mBuiRow[MasterUtil.RIS_BUI_NAME].toString();
					b_buiRName	= mBuiRow[MasterUtil.RIS_BUI_RYAKUNAME].toString();
				}
				//方向情報
				DataRow mHoukouRow	= masterUtil.FindDataRow(this.mstHoukou, MasterUtil.RIS_HOUKOU_ID, b_houkouID);
				if (mHoukouRow != null)
				{
					b_houkouName	= mHoukouRow[MasterUtil.RIS_HOUKOU_NAME].toString();
					b_houkouRName	= mHoukouRow[MasterUtil.RIS_HOUKOU_RYAKUNAME].toString();
				}
				//左右情報
				DataRow mSayuuRow	= masterUtil.FindDataRow(this.mstSayuu, MasterUtil.RIS_SAYUU_ID, b_sayuuID);
				if (mSayuuRow != null)
				{
					b_sayuuName		= mSayuuRow[MasterUtil.RIS_SAYUU_NAME].toString();
					b_sayuuRName	= mSayuuRow[MasterUtil.RIS_SAYUU_RYAKUNAME].toString();
				}
				//検査方法情報
				DataRow mKHouhouRow = masterUtil.FindDataRow(this.mstKensahouhou, MasterUtil.RIS_KENSAHOUHOU_ID, b_khouhouID);
				if (mKHouhouRow != null)
				{
					b_khouhouName	= mKHouhouRow[MasterUtil.RIS_KENSAHOUHOU_NAME].toString();
					b_khouhouRName	= mKHouhouRow[MasterUtil.RIS_KENSAHOUHOU_RYAKUNAME].toString();
				}



				//実績部位の場合
				if (exBuiBool)
				{
					b_status = b_row[RisBuiSummaryView.SATUEISTATUS_COLUMN].toString();
					// 2011.08.18 Mod T.Ootsuka@MERX Start NML-CAT3-036(問題点修正)
					// Code→String変換する
					if (codeConvBuiStatus != null && codeConvBuiStatus.Count > 0)
					{
						DataRow buiStatusRow2	= (DataRow)codeConvBuiStatus[b_status];
						if (buiStatusRow != null)
						{
							// 進捗文字列取得
							b_statusName		= buiStatusRow2[MasterUtil.RIS_VALUELABEL].toString();
							// ShowOrder取得
							b_statusShowOrder	= buiStatusRow2[MasterUtil.RIS_SHOWORDER].toString();
						}
					}

					// コメント
					//// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
					//// Code→String変換する
					//if (buiStatusList != null)
					//{
					//    for (int j = 0; j < buiStatusList.Count; j++)
					//    {
					//        DataRow buiRow = (DataRow)buiStatusList[j];
					//        if (b_status == buiRow[MasterUtil.RIS_ITEMVALUE].toString())
					//        {
					//            b_statusName = buiRow[MasterUtil.RIS_VALUELABEL].toString();
					//            b_statusShowOrder = buiRow[MasterUtil.RIS_SHOWORDER].toString();
					//            break;
					//        }
					//    }
					//}
					//// 2011.08.05 Add H.Satou@MERX End

					// 2011.08.18 Mod T.Ootsuka@MERX End
				}

				if (!Configuration.GetInstance().GetStopBuiShowFlg())
				{
					//中止部位は設定しない
					if (b_status == CommonString.SATUEISTATUS_STOP)
					{
						continue;
					}
				}

				//撮影数の準備
				int shotCountInt = 0;
				if (exFilmHash != null && exFilmHash.ContainsKey(risID))
				{
					ArrayList exFilmList = (ArrayList)exFilmHash[risID];

					for (int j=0; j<exFilmList.Count; j++)
					{
						ExFilmBean filmBean = (ExFilmBean)exFilmList[j];
						if (filmBean.GetBuiNo() == b_no)
						{
							shotCountInt += (filmBean.GetUsedInt() * filmBean.GetPartitionInt());
						}
					}
				}
				b_satueiCount = shotCountInt.toString();

				if (risID == b_RisID)
				{
					//複数部位フラグOFF＆部位NOが1以外は処理しない
					if (!param.GetShowBuiMode() && b_no != "1")
					{
						continue;
					}

					// 各情報をリストに追加

					//部位名称
					if (buiNames.length() <= 0)
					{
						buiNames.append(b_buiName);
					}
					else
					{
						buiNames.append(charStr + b_buiName);
					}
					//部位略称
					if (buiRNames.length() <= 0)
					{
						buiRNames.append(b_buiRName);
					}
					else
					{
						buiRNames.append(charStr + b_buiRName);
					}
					//左右名称
					if (sayuuNames.length() <= 0)
					{
						sayuuNames.append(b_sayuuName);
					}
					else
					{
						sayuuNames.append(charStr + b_sayuuName);
					}
					//左右略称
					if (sayuuRNames.length() <= 0)
					{
						sayuuRNames.append(b_sayuuRName);
					}
					else
					{
						sayuuRNames.append(charStr + b_sayuuRName);
					}
					//方向名称
					if (houkouNames.length() <= 0)
					{
						houkouNames.append(b_houkouName);
					}
					else
					{
						houkouNames.append(charStr + b_houkouName);
					}
					//方向略称
					if (houkouRNames.length() <= 0)
					{
						houkouRNames.append(b_houkouRName);
					}
					else
					{
						houkouRNames.append(charStr + b_houkouRName);
					}
					//検査方法ID
					if (khouhouIDs.length() <= 0)
					{
						khouhouIDs.append(b_khouhouID);
					}
					else
					{
						khouhouIDs.append(charStr + b_khouhouID);
					}
					//検査方法名称
					if (khouhouNames.length() <= 0)
					{
						khouhouNames.append(b_khouhouName);
					}
					else
					{
						khouhouNames.append(charStr + b_khouhouName);
					}
					//検査方法略称
					if (khouhouRNames.length() <= 0)
					{
						khouhouRNames.append(b_khouhouRName);
					}
					else
					{
						khouhouRNames.append(charStr + b_khouhouRName);
					}
					//部位コメント
					if (buiComments.length() <= 0)
					{
						buiComments.append(b_buiComment);
					}
					else
					{
						buiComments.append(charStr + b_buiComment);
					}
					//部位コメント
					if (buiCommentLines.length() <= 0)
					{
						buiCommentLines.append(b_buiCommentLine);
					}
					else
					{
						buiCommentLines.append(charStr + b_buiCommentLine);
					}
					//撮影数
					if (satueiCounts.length() <= 0)
					{
						satueiCounts.append(b_satueiCount);
					}
					else
					{
						satueiCounts.append(charStr + b_satueiCount);
					}

					// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
					if (satueiNames.length() <= 0)
					{
						satueiIDs.append(b_status);
						satueiNames.append(b_statusName);
						satueiSorts.append(b_statusShowOrder);
					}
					else
					{
						satueiIDs.append(charStr + b_status);
						satueiNames.append(charStr + b_statusName);
					}
					// 2011.08.05 Add H.Satou@MERX End


					buiCount += 1;
				}
			}

			// 部位情報を設定(カラムチェックをする事)

			if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_COUNT_COLUMN))
			{
				row[RisBuiSummaryView.BUI_COUNT_COLUMN]				= buiCount;
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.BUI_NAME_STRING_COLUMN]		= buiNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.BUI_RNAME_STRING_COLUMN]		= buiRNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN]		= sayuuNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN]	= sayuuRNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN]	= houkouNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN]	= houkouRNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN]	= khouhouNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN]	= khouhouRNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.BUICOMMENT_COLUMN))
			{
				row[RisBuiSummaryView.BUICOMMENT_COLUMN]			= buiComments.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN))
			{
				row[RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN]		= khouhouIDs.toString();
			}
			if (row.Table.Columns.Contains(RisSummaryView.BUICOMMENT_LINE_COLUMN))
			{
				row[RisSummaryView.BUICOMMENT_LINE_COLUMN]		    = buiCommentLines.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SATUEI_COUNT_STRING_COLUMN))
			{
				row[RisBuiSummaryView.SATUEI_COUNT_STRING_COLUMN]	= satueiCounts.toString();
			}
			// 2011.08.05 Add H.Satou@MERX Start NML-CAT3-036
			if (row.Table.Columns.Contains(RisBuiSummaryView.SATUEISTATUS_COLUMN))
			{
				row[RisBuiSummaryView.SATUEISTATUS_COLUMN]			= satueiIDs.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SATUEISTATUSNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.SATUEISTATUSNAME_STRING_COLUMN]= satueiNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SATUEISTATUSNAME_COLUMN))
			{
				row[RisBuiSummaryView.SATUEISTATUSNAME_COLUMN]		= satueiNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SATUEISTATUSSORT_COLUMN))
			{
				row[RisBuiSummaryView.SATUEISTATUSSORT_COLUMN]		= satueiSorts.toString();
			}
			// 2011.08.05 Add H.Satou@MERX End


		}
	}
	// コメント
	///// <summary>
	///// 部位情報を設定する
	///// </summary>
	///// <param name="con">データベース接続オブジェクト</param>
	///// <param name="transaction">DBトランザクションオブジェクト</param>
	///// <param name="orderDt">オーダ情報</param>
	///// <param name="param">検索条件</param>
	///// <returns></returns>
	//private DataTable SetBuiInfo(Connection con, Transaction trans, DataTable orderDt, OrderSearchParameter param)
	//{
	//    RisBuiSummaryView buiView = new RisBuiSummaryView();
	//    RisExFilmInfoTbl exFilmInfoTbl = new RisExFilmInfoTbl();

	//    //オーダ情報のループ
	//    for (int i = 0; i < orderDt.Rows.Count; i++)
	//    {
	//        DataRow row = orderDt.Rows[i];

	//        String risStr = row[RisSummaryView.RIS_ID_COLUMN].toString();

	//        ArrayList buiKensaHouhouArrayList	= new ArrayList();
	//        ArrayList exFilmList				= new ArrayList();

	//        //部位情報を取得
	//        DataTable buiDt = buiView.SearchBuiData(con, trans, risStr);

	//        //撮影情報を取得
	//        if (param.GetShotCntSearchBool())
	//        {
	//            //実績フィルム情報の取得
	//            exFilmList = exFilmInfoTbl.GetFilmListByRisID(con, trans, risStr);
	//        }

	//        //オーダ紐付く部位情報を保持する
	//        for (int buiDtCount = 0; buiDtCount < buiDt.Rows.Count; buiDtCount++)
	//        {
	//            buiKensaHouhouArrayList.Add(buiDt.Rows[buiDtCount][RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN].toString());
	//        }
	//        buiKensaHouhouHashtable.Add(risStr, buiKensaHouhouArrayList);

	//        //部位の設定
	//        SetBuiRow(row, buiDt, exFilmList, param);
	//    }

	//    return orderDt;
	//}

	///// <summary>
	///// 部位の設定
	///// </summary>
	///// <param name="row">オーダ情報行</param>
	///// <param name="buiDt">部位情報</param>
	///// <param name="exFilmList">実績撮影情報</param>
	///// <param name="param">検索条件</param>
	//private void SetBuiRow(DataRow row, DataTable buiDt, ArrayList exFilmList, OrderSearchParameter param)
	//{
	//    if (row != null && buiDt.Rows.Count > 0)
	//    {
	//        String risID = row[RisSummaryView.RIS_ID_COLUMN].toString();

	//        StringBuilder buiNames			= new StringBuilder();
	//        StringBuilder buiRNames			= new StringBuilder();
	//        StringBuilder sayuuNames		= new StringBuilder();
	//        StringBuilder sayuuRNames		= new StringBuilder();
	//        StringBuilder houkouNames		= new StringBuilder();
	//        StringBuilder houkouRNames		= new StringBuilder();
	//        StringBuilder khouhouNames		= new StringBuilder();
	//        StringBuilder khouhouRNames		= new StringBuilder();
	//        StringBuilder buiComments		= new StringBuilder();
	//        StringBuilder khouhouIDs		= new StringBuilder();
	//        StringBuilder buiCommentLines	= new StringBuilder();
	//        StringBuilder satueiCounts		= new StringBuilder();

	//        //区切り文字を決定する
	//        String charStr = "\r\n";
	//        if (param.GetHistoryFlg())
	//        {
	//            charStr = ",";
	//        }

	//        //部位情報のループ
	//        int buiCount = 0;
	//        for (int i = 0; i < buiDt.Rows.Count; i++)
	//        {
	//            DataRow b_row			= buiDt.Rows[i];
	//            //
	//            String b_RisID			= b_row[RisBuiSummaryView.RIS_ID_COLUMN].toString();
	//            String b_no				= b_row[RisBuiSummaryView.NO_COLUMN].toString();
	//            String b_buiName		= b_row[RisBuiSummaryView.BUI_NAME_COLUMN].toString();
	//            String b_buiRName		= b_row[RisBuiSummaryView.BUI_RYAKUNAME_COLUMN].toString();
	//            String b_sayuuName		= b_row[RisBuiSummaryView.SAYUU_NAME_COLUMN].toString();
	//            String b_sayuuRName		= b_row[RisBuiSummaryView.SAYUU_RYAKUNAME_COLUMN].toString();
	//            String b_houkouName		= b_row[RisBuiSummaryView.HOUKOU_NAME_COLUMN].toString();
	//            String b_houkouRName	= b_row[RisBuiSummaryView.HOUKOU_RYAKUNAME_COLUMN].toString();
	//            String b_khouhouID		= b_row[RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN].toString();
	//            String b_khouhouName	= b_row[RisBuiSummaryView.KENSAHOUHOU_NAME_COLUMN].toString();
	//            String b_khouhouRName	= b_row[RisBuiSummaryView.KENSAHOUHOU_RYAKUNAME_COLUMN].toString();
	//            String b_status			= b_row[RisBuiSummaryView.SATUEISTATUS_COLUMN].toString();
	//            String b_buiComment		= b_row[RisBuiSummaryView.BUICOMMENT_COLUMN].toString().Replace("\r\n", " ");
	//            String b_buiCommentLine = b_row[RisBuiSummaryView.BUICOMMENT_COLUMN].toString();
	//            String b_satueiCount	= "0";

	//            // 2010.10.26 Mod K.Shinohara Start
	//            if (!Configuration.GetInstance().GetStopBuiShowFlg())
	//            {
	//                //中止部位は設定しない
	//                if (b_status == CommonString.SATUEISTATUS_STOP)
	//                {
	//                    continue;
	//                }
	//            }
	//            ////中止部位は設定しない
	//            //if (b_status == CommonString.SATUEISTATUS_STOP)
	//            //{
	//            //    continue;
	//            //}
	//            // 2010.10.26 Mod K.Shinohara End

	//            //撮影数の準備
	//            int shotCountInt = 0;
	//            for (int j=0; j<exFilmList.Count; j++)
	//            {
	//                ExFilmBean filmBean = (ExFilmBean)exFilmList[j];
	//                if (filmBean.GetBuiNo() == b_no)
	//                {
	//                    shotCountInt += (filmBean.GetUsedInt() * filmBean.GetPartitionInt());
	//                }
	//            }
	//            b_satueiCount = shotCountInt.toString();

	//            if (risID == b_RisID)
	//            {
	//                //複数部位フラグOFF＆部位NOが1以外は処理しない
	//                if (!param.GetShowBuiMode() && b_no != "1")
	//                {
	//                    continue;
	//                }

	//                // 各情報をリストに追加

	//                //部位名称
	//                if (buiNames.length() <= 0)
	//                {
	//                    buiNames.append(b_buiName);
	//                }
	//                else
	//                {
	//                    buiNames.append(charStr + b_buiName);
	//                }
	//                //部位略称
	//                if (buiRNames.length() <= 0)
	//                {
	//                    buiRNames.append(b_buiRName);
	//                }
	//                else
	//                {
	//                    buiRNames.append(charStr + b_buiRName);
	//                }
	//                //左右名称
	//                if (sayuuNames.length() <= 0)
	//                {
	//                    sayuuNames.append(b_sayuuName);
	//                }
	//                else
	//                {
	//                    sayuuNames.append(charStr + b_sayuuName);
	//                }
	//                //左右略称
	//                if (sayuuRNames.length() <= 0)
	//                {
	//                    sayuuRNames.append(b_sayuuRName);
	//                }
	//                else
	//                {
	//                    sayuuRNames.append(charStr + b_sayuuRName);
	//                }
	//                //方向名称
	//                if (houkouNames.length() <= 0)
	//                {
	//                    houkouNames.append(b_houkouName);
	//                }
	//                else
	//                {
	//                    houkouNames.append(charStr + b_houkouName);
	//                }
	//                //方向略称
	//                if (houkouRNames.length() <= 0)
	//                {
	//                    houkouRNames.append(b_houkouRName);
	//                }
	//                else
	//                {
	//                    houkouRNames.append(charStr + b_houkouRName);
	//                }
	//                //検査方法ID
	//                if (khouhouIDs.length() <= 0)
	//                {
	//                    khouhouIDs.append(b_khouhouID);
	//                }
	//                else
	//                {
	//                    khouhouIDs.append(charStr + b_khouhouID);
	//                }
	//                //検査方法名称
	//                if (khouhouNames.length() <= 0)
	//                {
	//                    khouhouNames.append(b_khouhouName);
	//                }
	//                else
	//                {
	//                    khouhouNames.append(charStr + b_khouhouName);
	//                }
	//                //検査方法略称
	//                if (khouhouRNames.length() <= 0)
	//                {
	//                    khouhouRNames.append(b_khouhouRName);
	//                }
	//                else
	//                {
	//                    khouhouRNames.append(charStr + b_khouhouRName);
	//                }
	//                //部位コメント
	//                if (buiComments.length() <= 0)
	//                {
	//                    buiComments.append(b_buiComment);
	//                }
	//                else
	//                {
	//                    buiComments.append(charStr + b_buiComment);
	//                }
	//                //部位コメント
	//                if (buiCommentLines.length() <= 0)
	//                {
	//                    buiCommentLines.append(b_buiCommentLine);
	//                }
	//                else
	//                {
	//                    buiCommentLines.append(charStr + b_buiCommentLine);
	//                }
	//                //撮影数
	//                if (satueiCounts.length() <= 0)
	//                {
	//                    satueiCounts.append(b_satueiCount);
	//                }
	//                else
	//                {
	//                    satueiCounts.append(charStr + b_satueiCount);
	//                }

	//

	//                buiCount += 1;
	//            }
	//        }

	//        // 部位情報を設定(カラムチェックをする事)

	//        if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_COUNT_COLUMN))
	//        {
	//            row[RisBuiSummaryView.BUI_COUNT_COLUMN]				= buiCount;
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_NAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.BUI_NAME_STRING_COLUMN]		= buiNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_RNAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.BUI_RNAME_STRING_COLUMN]		= buiRNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN]		= sayuuNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN]	= sayuuRNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN]	= houkouNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN]	= houkouRNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN]	= khouhouNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN]	= khouhouRNames.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.BUICOMMENT_COLUMN))
	//        {
	//            row[RisBuiSummaryView.BUICOMMENT_COLUMN]			= buiComments.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN))
	//        {
	//            row[RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN]		= khouhouIDs.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisSummaryView.BUICOMMENT_LINE_COLUMN))
	//        {
	//            row[RisSummaryView.BUICOMMENT_LINE_COLUMN]		    = buiCommentLines.toString();
	//        }
	//        if (row.Table.Columns.Contains(RisBuiSummaryView.SATUEI_COUNT_STRING_COLUMN))
	//        {
	//            row[RisBuiSummaryView.SATUEI_COUNT_STRING_COLUMN]	= satueiCounts.toString();
	//        }

	//
	//    }
	//}

	// 2010.10.29 Mod K.Shinohara End

	// 2010.10.29 Add K.Shinohara Start
	/// <summary>
	/// 他検査情報を準備する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="transaction">DBトランザクションオブジェクト</param>
	/// <param name="orderDt">オーダ情報</param>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	private Hashtable GetOtherDataHash(Connection con, Transaction trans, DataTable orderDt, OrderSearchParameter param)
	{
		Hashtable otherHash = new Hashtable();

		RisSummaryView risSummaryView = new RisSummaryView();

		OrderSearchParameter oParam = new OrderSearchParameter();
		Timestamp startDate = Timestamp.MaxValue;
		Timestamp endDate   = Timestamp.MinValue;

		String kanjaIDStr = "";

		boolean kanjaIDOnlyFlg = true;		// 患者ID指定フラグ
		boolean unknownDateFlg = false;	// 日未定フラグ
		boolean isRptUnKnownBool = false;	// 2011.08.19 Add H.Orikasa A0060(修正)

		// 日未定日付を取得しておく
		String unknownDateStr = Configuration.GetInstance().GetSystemParamValue(Configuration.GetInstance().GetRRisSystemParam2(), RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);

		//オーダ情報のループ
		for (int i=0; i<orderDt.Rows.Count; i++)
		{
			Timestamp orderDate	= CommonUtil.ConvertKensaDate(orderDt.Rows[i][RisSummaryView.ORDER_KENSA_DATE_COLUMN].toString());
			Timestamp exDate		= CommonUtil.ConvertKensaDate(orderDt.Rows[i][RisSummaryView.EX_KENSA_DATE_COLUMN].toString());
			if (exDate == Timestamp.MinValue)
			{
				exDate = orderDate;
			}

			String kanjaID = orderDt.Rows[i][RisSummaryView.KANJA_ID_COLUMN].toString();

			if (kanjaIDOnlyFlg && kanjaIDStr.length() > 0)
			{
				// 複数患者が混在している場合はフラグをOFFにする
				if (!kanjaIDStr.Equals(kanjaID))
				{
					kanjaIDOnlyFlg = false;
				}
			}
			else if (kanjaIDStr.length() <= 0)
			{
				// 初回患者IDを設定
				kanjaIDStr = kanjaID;
			}

			// 日未定検査の場合はフラグをONにする（検索条件の日付は変更しない）
			if (exDate.toString(CommonString.LIST_FORMAT_DATE_2).Equals(unknownDateStr))
			{
				unknownDateFlg = true;
				continue;
			}

			//日付を用意する
			CommonUtil.CheckDateSpan(ref startDate, ref endDate, orderDate, exDate, ref isRptUnKnownBool);	// 2011.08.19 Mod H.Orikasa Start A0060(修正)
			//CommonUtil.CheckDateSpan(ref startDate, ref endDate, exDate);									//
		}

		//検索条件の準備
		oParam.SetExecutePeriodStartDate(startDate);
		oParam.SetExecutePeriodEndDate(endDate);
		oParam.SetKanjaIDOnlyBool(kanjaIDOnlyFlg);
		oParam.SetKanjaID(kanjaIDStr);
		oParam.SetUnKnownDateBool(unknownDateFlg);
		oParam.SetRptUnKnownDateFlg(isRptUnKnownBool);	// 2011.08.19 Add H.Orikasa Start A0060(修正)

		//他検査情報リストを取得する
		DataTable otherDt = risSummaryView.SearchTakensaOrderDataList(con, trans, oParam);
		if (otherDt != null)
		{
			//他検査情報のループ
			for (DataRow row : otherDt.Rows)
			{
				//検査日毎にオーダリストを作成する
				String kDateStr = row[RisSummaryView.KENSA_DATE_COLUMN].toString();
				if (otherHash.ContainsKey(kDateStr))
				{
					//リストへ追加
					ArrayList otherList = (ArrayList)otherHash[kDateStr];
					otherList.Add(row);
				}
				else
				{
					//リストを作成
					ArrayList otherList = new ArrayList();
					otherList.Add(row);
					otherHash.Add(kDateStr, otherList);
				}
			}
		}

		return otherHash;
	}
	// 2010.10.29 Add K.Shinohara End

	// 2011.09.09 Add T.Ootsuka@MERX Start NML-CAT9-031
	/// <summary>
	/// 部位情報を設定する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="transaction">DBトランザクションオブジェクト</param>
	/// <param name="orderDt">オーダ情報</param>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	public void SetBuiInfoForReception(Connection con, Transaction trans, DataTable orderDt, OrderSearchParameter oParam)
	{
		RisOrderBuiInfoTbl orderBuiTbl	= new RisOrderBuiInfoTbl();
		RisExBuiInfoTbl    exBuiTbl		= new RisExBuiInfoTbl();
		MasterInfoTbl masterTbl = new MasterInfoTbl();

		Hashtable orderBuiHash	= new Hashtable();	//オーダ部位Hash(Key:RisID, Value:オーダ部位リスト)
		Hashtable exBuiHash		= new Hashtable();	//実績部位Hash  (Key:RisID, Value:実績部位リスト)

		// 不足カラムの追加
		if (!orderDt.Columns.Contains(RisSummaryView.SELECT_COLMUN))
		{
			orderDt.Columns.Add(RisSummaryView.SELECT_COLMUN);
		}
		AddColumns(orderDt);


		// 部位情報の取得

		//各マスタの取得
		GetMaster(con, trans, masterTbl);

		//オーダ部位情報を取得
		DataTable orderBuiDt = orderBuiTbl.GetListOrderBuiDataTableByRisIdList(con, trans, oParam);
		for (int i=0; i<orderBuiDt.Rows.Count; i++)
		{
			DataRow orderBuiRow = orderBuiDt.Rows[i];
			String risID = orderBuiRow[RisOrderBuiInfoTbl.RIS_ID_COLUMN].toString();
			if (orderBuiHash.ContainsKey(risID))
			{
				//リストへ追加
				ArrayList buiList = (ArrayList)orderBuiHash[risID];
				buiList.Add(orderBuiRow);
			}
			else
			{
				//リストを作成
				ArrayList buiList = new ArrayList();
				buiList.Add(orderBuiRow);
				orderBuiHash.Add(risID, buiList);
			}
		}

		//実績部位情報を取得
		DataTable exBuiDt = exBuiTbl.GetListExBuiDataTableByRisIdList(con, trans, oParam);
		for (int i=0; i<exBuiDt.Rows.Count; i++)
		{
			DataRow exBuiRow = exBuiDt.Rows[i];
			String risID = exBuiRow[RisExBuiInfoTbl.RIS_ID_COLUMN].toString();
			if (exBuiHash.ContainsKey(risID))
			{
				//リストへ追加
				ArrayList buiList = (ArrayList)exBuiHash[risID];
				buiList.Add(exBuiRow);
			}
			else
			{
				//リストを作成
				ArrayList buiList = new ArrayList();
				buiList.Add(exBuiRow);
				exBuiHash.Add(risID, buiList);
			}
		}


		// オーダ情報のループ

		for (int i = 0; i < orderDt.Rows.Count; i++)
		{
			DataRow row = orderDt.Rows[i];

			String risIDStr		= row[RisSummaryView.RIS_ID_COLUMN].toString();
			String statusStr	= row[RisSummaryView.STATUS_COLUMN].toString();

			//部位リストをステータスにより判断する
			//boolean exBuiBool = false;
			ArrayList buiList = new ArrayList();
			if (TextUtil.ParseStringToInt(statusStr) >= TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED))
			{
				//受済以降→実績部位リスト
				if (exBuiHash.ContainsKey(risIDStr))
				{
					buiList = (ArrayList)exBuiHash[risIDStr];
				}
				//exBuiBool = true;
			}
			else
			{
				//受済以前→オーダ部位リスト
				if (orderBuiHash.ContainsKey(risIDStr))
				{
					buiList = (ArrayList)orderBuiHash[risIDStr];
				}
			}

			//部位の設定
			SetBuiRow(row, buiList, oParam);
		}


	}

	/// <summary>
	/// 部位の設定
	/// </summary>
	/// <param name="row">オーダ情報行</param>
	/// <param name="buiList">部位情報</param>
	/// <param name="param">検索条件</param>
	private void SetBuiRow(DataRow row, ArrayList buiList, OrderSearchParameter param)
	{
		MasterUtil masterUtil = new MasterUtil();

		if (row != null && buiList.Count > 0)
		{
			String risID = row[RisSummaryView.RIS_ID_COLUMN].toString();
			// 名称
			StringBuilder buiNames			= new StringBuilder();
			StringBuilder sayuuNames		= new StringBuilder();
			StringBuilder houkouNames		= new StringBuilder();
			StringBuilder khouhouNames		= new StringBuilder();
			// 略称
			StringBuilder buiRNames			= new StringBuilder();
			StringBuilder sayuuRNames		= new StringBuilder();
			StringBuilder houkouRNames		= new StringBuilder();
			StringBuilder khouhouRNames		= new StringBuilder();

			//区切り文字を決定する
			String charStr = "\r\n";
			if (param.GetHistoryFlg())
			{
				charStr = ",";
			}

			//部位情報のループ
			int buiCount = 0;
			for (int i = 0; i < buiList.Count; i++)
			{
				DataRow b_row			= (DataRow)buiList[i];
				//
				String b_RisID			= b_row[RisBuiSummaryView.RIS_ID_COLUMN].toString();
				String b_no				= b_row[RisBuiSummaryView.NO_COLUMN].toString();
				String b_buiID			= b_row[RisBuiSummaryView.BUI_ID_COLUMN].toString();
				String b_houkouID		= b_row[RisBuiSummaryView.HOUKOU_ID_COLUMN].toString();
				String b_sayuuID		= b_row[RisBuiSummaryView.SAYUU_ID_COLUMN].toString();
				String b_khouhouID		= b_row[RisBuiSummaryView.KENSAHOUHOU_ID_COLUMN].toString();
				// 名称
				String b_buiName		= "";
				String b_sayuuName		= "";
				String b_houkouName		= "";
				String b_khouhouName	= "";
				// 略称
				String b_buiRName		= "";
				String b_sayuuRName		= "";
				String b_houkouRName	= "";
				String b_khouhouRName	= "";

				// 各名称の補填

				//部位情報
				DataRow mBuiRow		= masterUtil.FindDataRow(this.mstBui, MasterUtil.RIS_BUI_ID, b_buiID);
				if (mBuiRow != null)
				{
					b_buiName	= mBuiRow[MasterUtil.RIS_BUI_NAME].toString();
				}
				//方向情報
				DataRow mHoukouRow	= masterUtil.FindDataRow(this.mstHoukou, MasterUtil.RIS_HOUKOU_ID, b_houkouID);
				if (mHoukouRow != null)
				{
					b_houkouName	= mHoukouRow[MasterUtil.RIS_HOUKOU_NAME].toString();
				}
				//左右情報
				DataRow mSayuuRow	= masterUtil.FindDataRow(this.mstSayuu, MasterUtil.RIS_SAYUU_ID, b_sayuuID);
				if (mSayuuRow != null)
				{
					b_sayuuName	= mSayuuRow[MasterUtil.RIS_SAYUU_NAME].toString();
				}
				//検査方法情報
				DataRow mKHouhouRow = masterUtil.FindDataRow(this.mstKensahouhou, MasterUtil.RIS_KENSAHOUHOU_ID, b_khouhouID);
				if (mKHouhouRow != null)
				{
					b_khouhouName	= mKHouhouRow[MasterUtil.RIS_KENSAHOUHOU_NAME].toString();
				}
				//部位略称情報
				if (mBuiRow != null)
				{
					b_buiRName	= mBuiRow[MasterUtil.RIS_BUI_RYAKUNAME].toString();
				}
				//方向略称情報
				if (mHoukouRow != null)
				{
					b_houkouRName	= mHoukouRow[MasterUtil.RIS_HOUKOU_RYAKUNAME].toString();
				}
				//左右略称情報
				if (mSayuuRow != null)
				{
					b_sayuuRName	= mSayuuRow[MasterUtil.RIS_SAYUU_RYAKUNAME].toString();
				}
				//検査方法略称情報
				if (mKHouhouRow != null)
				{
					b_khouhouRName	= mKHouhouRow[MasterUtil.RIS_KENSAHOUHOU_RYAKUNAME].toString();
				}



				if (risID == b_RisID)
				{
					//複数部位フラグOFF＆部位NOが1以外は処理しない
					if (!param.GetShowBuiMode() && b_no != "1")
					{
						continue;
					}

					// 各情報をリストに追加

					//部位名称
					if (buiNames.length() <= 0)
					{
						buiNames.append(b_buiName);
					}
					else
					{
						buiNames.append(charStr + b_buiName);
					}
					//左右名称
					if (sayuuNames.length() <= 0)
					{
						sayuuNames.append(b_sayuuName);
					}
					else
					{
						sayuuNames.append(charStr + b_sayuuName);
					}
					//方向名称
					if (houkouNames.length() <= 0)
					{
						houkouNames.append(b_houkouName);
					}
					else
					{
						houkouNames.append(charStr + b_houkouName);
					}
					//検査方法名称
					if (khouhouNames.length() <= 0)
					{
						khouhouNames.append(b_khouhouName);
					}
					else
					{
						khouhouNames.append(charStr + b_khouhouName);
					}

					//部位略称
					if (buiRNames.length() <= 0)
					{
						buiRNames.append(b_buiRName);
					}
					else
					{
						buiRNames.append(charStr + b_buiRName);
					}
					//左右略称
					if (sayuuRNames.length() <= 0)
					{
						sayuuRNames.append(b_sayuuRName);
					}
					else
					{
						sayuuRNames.append(charStr + b_sayuuRName);
					}
					//方向略称
					if (houkouRNames.length() <= 0)
					{
						houkouRNames.append(b_houkouRName);
					}
					else
					{
						houkouRNames.append(charStr + b_houkouRName);
					}
					//検査方法略称
					if (khouhouRNames.length() <= 0)
					{
						khouhouRNames.append(b_khouhouRName);
					}
					else
					{
						khouhouRNames.append(charStr + b_khouhouRName);
					}



					buiCount += 1;
				}
			}

			// 部位情報を設定(カラムチェックをする事)

			if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_COUNT_COLUMN))
			{
				row[RisBuiSummaryView.BUI_COUNT_COLUMN]				= buiCount;
			}
			// 名称
			if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.BUI_NAME_STRING_COLUMN]		= buiNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.SAYUU_NAME_STRING_COLUMN]	= sayuuNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.HOUKOU_NAME_STRING_COLUMN]	= houkouNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.KHOUHOU_NAME_STRING_COLUMN]	= khouhouNames.toString();
			}
			// 略称
			if (row.Table.Columns.Contains(RisBuiSummaryView.BUI_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.BUI_RNAME_STRING_COLUMN]		= buiRNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.SAYUU_RNAME_STRING_COLUMN]	= sayuuRNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.HOUKOU_RNAME_STRING_COLUMN]	= houkouRNames.toString();
			}
			if (row.Table.Columns.Contains(RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN))
			{
				row[RisBuiSummaryView.KHOUHOU_RNAME_STRING_COLUMN]	= khouhouRNames.toString();
			}


		}
	}
	// 2011.09.09 Add T.Ootsuka@MERX End
	*/
}
