package ris.lib.core.util;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExtendColorDefineBean;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
/// CommonUtil の概要の説明です。
/// </summary>
public class CommonUtil
{
	public static Log logger = LogFactory.getLog(CommonUtil.class);

	/*
	/// <summary>
	/// カーソルを通常表示（矢印）に変更
	/// </summary>
	public static void CursorToDefault()
	{
		// カーソルを元に戻す
		Cursor.Current = Cursors.Default;
	}
	*/
	/*
	//
	/// <summary>
	/// カーソルを待ち表示（砂時計）に変更
	/// </summary>
	public static void CursorToWait()
	{
		// カーソルを砂時計にする
		Cursor.Current = Cursors.WaitCursor;
	}
	*/
	/*
	/// <summary>
	/// 帳票情報の設定
	/// </summary>
	public static void InitPrintMaster()
	{
		try
		{
			//各マスタ情報を取得
			DataTable mstKtype		= Configuration.GetInstance().GetRRisKensaTypeMaster();		//検査種別
			DataTable mstPrt		= Configuration.GetInstance().GetRRisPrintMaster();			//帳票種別
			DataTable mstDefaultPrt	= Configuration.GetInstance().GetRRisDefaultPrintDefine();	//帳票定義
			DataTable mstPrtDef		= Configuration.GetInstance().GetRRisPrintDefine();			//検査種別毎帳票定義
			DataTable mstPrtFormDef = Configuration.GetInstance().GetRRisPrintFormDefine();		//検査種別毎帳票詳細定義

			//帳票情報を準備する
			Hashtable prtHash = new Hashtable();

			//帳票種別のループ
			for (int i=0; i<mstPrt.Rows.Count; i++)
			{
				DataRow row = mstPrt.Rows[i];

				PrintMasterBean printBean = new PrintMasterBean();
				printBean.SetPrintID(row[MasterUtil.RIS_PRINTID].toString());
				printBean.SetPrintName(row[MasterUtil.RIS_NAME].toString());
				printBean.SetExplanation(row[MasterUtil.RIS_EXPLANATION].toString());
				printBean.SetShoworder(Integer.parseInt(row[MasterUtil.RIS_SHOWORDER].toString()));
				// 2010.06.30 Add T.Nishikubo Start
				if (printBean.GetPrintID().Equals(CommonString.PRINT_ID_TOUTYOKU))
				{
					printBean.SetFormFileName(row[RisSystemParamTbl.VALUE1_COLUMN].toString());
				}
				// 2010.06.30 Add T.Nishikubo End

				//基本となる帳票種別を処理する
				if (!prtHash.ContainsKey(printBean.GetPrintID()))
				{
					prtHash.Add(printBean.GetPrintID(), printBean);
				}
			}

			//帳票定義のループ
			for (int i=0; i<mstDefaultPrt.Rows.Count; i++)
			{
				DataRow row = mstDefaultPrt.Rows[i];

				//PrintIDが一致する情報を処理する
				if (prtHash.ContainsKey(row[MasterUtil.RIS_PRINTID].toString()))
				{
					PrintMasterBean bean = (PrintMasterBean)prtHash[row[MasterUtil.RIS_PRINTID].toString()];
					// 2010.06.30 Mod T.Nishikubo Start
					//bean.SetFormFileName(row[MasterUtil.RIS_FORMFILENAME].toString());
					if (!bean.GetPrintID().Equals(CommonString.PRINT_ID_TOUTYOKU))
					{
						bean.SetFormFileName(row[MasterUtil.RIS_FORMFILENAME].toString());
					}
					// 2010.06.30 Mod T.Nishikubo End
					bean.SetAutoPrint1(row[MasterUtil.RIS_AUTOPRINT1].toString());
					bean.SetA1Screen1(row[MasterUtil.RIS_A1SCREEN1].toString());
					bean.SetA1Screen2(row[MasterUtil.RIS_A1SCREEN2].toString());
					bean.SetA1Screen3(row[MasterUtil.RIS_A1SCREEN3].toString());
					bean.SetA1Screen4(row[MasterUtil.RIS_A1SCREEN4].toString());
					bean.SetA1Screen5(row[MasterUtil.RIS_A1SCREEN5].toString());
					bean.SetAutoPrint2(row[MasterUtil.RIS_AUTOPRINT2].toString());
					bean.SetA2Screen1(row[MasterUtil.RIS_A2SCREEN1].toString());
					bean.SetA2Screen2(row[MasterUtil.RIS_A2SCREEN2].toString());
					bean.SetAutoPrint3(row[MasterUtil.RIS_AUTOPRINT3].toString());
					bean.SetA3Screen1(row[MasterUtil.RIS_A3SCREEN1].toString());
					bean.SetA3Screen2(row[MasterUtil.RIS_A3SCREEN2].toString());
					bean.SetDoPreview(row[MasterUtil.RIS_DOPREVIEW].toString());
				}
			}

			//検査種別毎帳票定義のループ
			for (int i=0; i<mstPrtDef.Rows.Count; i++)
			{
				DataRow row = mstPrtDef.Rows[i];

				String kTypeID = row[MasterUtil.RIS_KENSATYPE_ID].toString();
				String printID = row[MasterUtil.RIS_PRINTID].toString();

				//PrintIDが一致する情報を処理する
				if (prtHash.ContainsKey(printID))
				{
					PrintMasterBean bean = (PrintMasterBean)prtHash[printID];

					//検査種別毎に処理する
					if (!bean.GetPrintDefineHash().ContainsKey(kTypeID))
					{
						PrintDefineBean setBean = new PrintDefineBean();
						setBean.SetKensatypeID(kTypeID);
						setBean.SetPrintID(printID);
						setBean.SetKbn1(row[MasterUtil.RIS_KBN1].toString());
						setBean.SetKbn2(row[MasterUtil.RIS_KBN2].toString());
						setBean.SetKbn3(row[MasterUtil.RIS_KBN3].toString());
						setBean.SetAutoPrint1(row[MasterUtil.RIS_AUTOPRINT1].toString());
						setBean.SetAutoPrint2(row[MasterUtil.RIS_AUTOPRINT2].toString());
						setBean.SetAutoPrint3(row[MasterUtil.RIS_AUTOPRINT3].toString());
						//
						bean.GetPrintDefineHash().Add(kTypeID, setBean);
					}
				}
			}

			//帳票定義のループ
			for (String printID : prtHash.Keys)
			{
				if (prtHash[printID] != null)
				{
					PrintMasterBean bean = (PrintMasterBean)prtHash[printID];

					//検査種別のループ
					for (int i=0; i<mstKtype.Rows.Count; i++)
					{
						String kTypeID = mstKtype.Rows[i][MasterUtil.RIS_KENSATYPE_ID].toString();

						ArrayList list = null;
						if (bean.GetPrintFormDefineHash().ContainsKey(kTypeID))
						{
							list = (ArrayList)bean.GetPrintFormDefineHash()[kTypeID];
						}
						else
						{
							list = new ArrayList();
							bean.GetPrintFormDefineHash().Add(kTypeID, list);
						}

						//検査種別毎帳票詳細定義のループ(1帳票,1検査種別で複数定義)
						for (int j=0;j<mstPrtFormDef.Rows.Count; j++)
						{
							DataRow row = mstPrtFormDef.Rows[j];

							String rowKTypeID = row[MasterUtil.RIS_KENSATYPE_ID].toString();
							String rowPrintID = row[MasterUtil.RIS_PRINTID].toString();

							//PrintID,検査種別IDが一致する情報を処理する
							if (printID == rowPrintID &&
								kTypeID == rowKTypeID)
							{
								PrintFormDefineBean setBean = new PrintFormDefineBean();
					            setBean.SetKensatypeID(kTypeID);
					            setBean.SetPrintID(printID);
					            setBean.SetDetailKbn1(row[MasterUtil.RIS_DETAILKBN1].toString());
					            setBean.SetDetailKbn2(row[MasterUtil.RIS_DETAILKBN2].toString());
								setBean.SetDetailKbn3(row[MasterUtil.RIS_DETAILKBN3].toString());
					            setBean.SetFormFileName(row[MasterUtil.RIS_FORMFILENAME].toString());
					            setBean.SetAutoPrintFlag(row[MasterUtil.RIS_AUTOPRINTFLAG].toString());
								//
								list.Add(setBean);
							}
						}
					}
				}
			}

			//帳票情報へ設定する
			Configuration.GetInstance().SetPrintInfoHash(prtHash);
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.initDataBaseConfException_MessageString) + "\r\n" + e);
			throw e;
		}
	}
	*/

	/*
	/// <summary>
	/// 帳票用の検索条件に追加を行う
	/// </summary>
	/// <param name="builder">検索条件文字列</param>
	/// <param name="param">一覧検索条件</param>
	public static StringBuilder AddPrintWhereString(StringBuilder builder, OrderSearchParameter param)
	{
		StringBuilder retBuilder = builder;
		if (builder != null && param != null)
		{
			// 2011.08.19 Add H.Orikasa Start A0060(修正)
			// 他検査フラグの場合
			if (param.GetOtherFlg())
			{
				// 他検査の場合

				Timestamp otherDate	= param.GetExecutePeriodStartDate();

				// 患者ID、検査日
				builder.append(" AND OM.KANJA_ID = '" + param.GetKanjaID() + "'");
				builder.append(" AND ((OM.RIS_ID = EM.RIS_ID ");
				builder.append(" AND   EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'");
				builder.append(" AND   EM.KENSA_DATE = " + otherDate.toString("yyyyMMdd") + ") ");
				builder.append(" OR   (OM.RIS_ID = EM.RIS_ID ");
				builder.append(" AND   EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'");
				builder.append(" AND   OM.KENSA_DATE = " + otherDate.toString("yyyyMMdd") + ")) ");

				return builder;


			}

			// 日未定のみの場合
			if (param.GetRptUnKnownDateFlg() &&
				param.GetExecutePeriodStartDate() == Timestamp.MaxValue && param.GetExecutePeriodEndDate() == Timestamp.MinValue)
			{
				// 日未定日付を設定する
				Timestamp unKnownTimestamp = TextUtil.ParseDateStrToTimestamp(Configuration.GetInstance().GetUnKnownDateString());
				param.SetExecutePeriodStartDate(unKnownTimestamp);
				param.SetExecutePeriodEndDate(unKnownTimestamp);
			}
			// 2011.08.19 Add H.Orikasa End

			if (param.GetExecutePeriodStartDate() != Timestamp.MinValue && param.GetExecutePeriodEndDate() != Timestamp.MinValue)
			{
				// 2010.12.27 Add K.Shinohara Start
				// 自動印刷時は患者IDも条件に含む
				if ((param.GetAutoPrintSearchBool()) &&
					(param.GetKanjaID().length() > 0))
				{
					//患者ID
					builder.append(" AND OM.KANJA_ID = '" + param.GetKanjaID() + "'");
				}
				// 2010.12.27 Add K.Shinohara End

				// 2011.08.19 Mod H.Orikasa Start A0060(修正)
				// 帳票タイプに応じて、検索条件を変更する
				if (param.GetPrintID() == CommonString.PRINT_ID_UKETUKE		||
					param.GetPrintID() == CommonString.PRINT_ID_IRAI		||
					param.GetPrintID() == CommonString.PRINT_ID_JISSEKI		||
					param.GetPrintID() == CommonString.PRINT_ID_LABELKANJA	||
					param.GetPrintID() == CommonString.PRINT_ID_LABELKENSA)
				{
					// 単票-RisIDで取得

					if (param.GetRisIDArrayList().Count <= 0)
					{
						//０件の場合はエラー
						builder.append(" AND OM.RIS_ID = EM.RIS_ID ");
						builder.append(" AND OM.RIS_ID = 'FATAL'");
					}
					else if (param.GetRisIDArrayList().Count == 1)
					{
						//１件の場合はイコール
						builder.append(" AND OM.RIS_ID = EM.RIS_ID ");
						builder.append(" AND OM.RIS_ID = '" + param.GetRisIDArrayList()[0].toString() + "'");
					}
					else if (param.GetRisIDArrayList().Count > 1)
					{
						//Ｎ件の場合はIN文
						builder.append(" AND OM.RIS_ID = EM.RIS_ID ");
						builder.append(" AND OM.RIS_ID IN (");

						for (int i=0; i < param.GetRisIDArrayList().Count; i++)
						{
							String risIDStr = param.GetRisIDArrayList()[i].toString();
							if (i==0)
							{
								builder.append(" '" + risIDStr + "'");
							}
							else
							{
								builder.append(", '" + risIDStr + "'");
							}
						}

						builder.append(" )");
					}


				}
				else if (param.GetPrintID() == CommonString.PRINT_ID_YOTEI		||
						 param.GetPrintID() == CommonString.PRINT_ID_PORTABLE	||
						 param.GetPrintID() == CommonString.PRINT_ID_DAITYO		||
						 param.GetPrintID() == CommonString.PRINT_ID_TOUTYOKU)
				{
					// 連票-期間で取得

					if (param.GetRequestSearchBool())
					{
						//依頼日
						builder.append(" AND OM.RIS_ID = EM.RIS_ID ");	// 2011.08.19 Add H.Orikasa Start A0060
						builder.append(" AND EO.ORDER_DATE >= TO_DATE('" + param.GetExecutePeriodStartDate().toString("yyyyMMdd 00:00:00") + "', 'YYYY-MM-DD HH24:MI:SS')");
						builder.append(" AND EO.ORDER_DATE <= TO_DATE('" + param.GetExecutePeriodEndDate().toString("yyyyMMdd 23:59:59")   + "', 'YYYY-MM-DD HH24:MI:SS')");
					}
					else
					{
						//検査日
						// 2011.08.19 Mod H.Orikasa Start A0060
						boolean     unKnownBool= param.GetRptUnKnownDateFlg();
						String   unKnownStr	= Configuration.GetInstance().GetUnKnownDateString();
						Timestamp startDate	= param.GetExecutePeriodStartDate();
						Timestamp closeDate	= param.GetExecutePeriodEndDate();

						if (!unKnownBool)
						{
							// 日未定OFFの場合
							builder.append(" AND ((OM.RIS_ID = EM.RIS_ID ");
							builder.append(" AND   EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'");
							builder.append(" AND   EM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
							builder.append(" AND   EM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ") ");
							builder.append(" OR   (OM.RIS_ID = EM.RIS_ID ");
							builder.append(" AND   EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'");
							builder.append(" AND   OM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
							builder.append(" AND   OM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ")) ");
						}
						else
						{
							// 日未定ONの場合
							builder.append(" AND ((OM.RIS_ID = EM.RIS_ID ");
							builder.append(" AND   EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'");
							builder.append(" AND   ((EM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
							builder.append(" AND     EM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ") ");
							builder.append(" OR      EM.KENSA_DATE = " + Configuration.GetInstance().GetUnKnownDateString() + ")) ");
							builder.append(" OR   (OM.RIS_ID = EM.RIS_ID ");
							builder.append(" AND   EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'");
							builder.append(" AND   ((OM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
							builder.append(" AND     OM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ") ");
							builder.append(" OR      OM.KENSA_DATE = " + Configuration.GetInstance().GetUnKnownDateString() + "))) ");
						}
					}

				}
				// コメント
				//if (param.GetRequestSearchBool())
				//{
				//    //依頼日
				//    builder.append(" AND OM.RIS_ID = EM.RIS_ID ");	// 2011.08.19 Add H.Orikasa Start A0060
				//    builder.append(" AND EO.ORDER_DATE >= TO_DATE('" + param.GetExecutePeriodStartDate().toString("yyyyMMdd 00:00:00") + "', 'YYYY-MM-DD HH24:MI:SS')");
				//    builder.append(" AND EO.ORDER_DATE <= TO_DATE('" + param.GetExecutePeriodEndDate().toString("yyyyMMdd 23:59:59")   + "', 'YYYY-MM-DD HH24:MI:SS')");
				//}
				//else
				//{
				//    //検査日
				//    // 2011.08.19 Mod H.Orikasa Start A0060
				//    boolean     unKnownBool= param.GetRptUnKnownDateFlg();
				//    String   unKnownStr	= Configuration.GetInstance().GetUnKnownDateString();
				//    Timestamp startDate	= param.GetExecutePeriodStartDate();
				//    Timestamp closeDate	= param.GetExecutePeriodEndDate();

				//    if (!unKnownBool)
				//    {
				//        // 日未定OFFの場合
				//        builder.append(" AND ((OM.RIS_ID = EM.RIS_ID ");
				//        builder.append(" AND   EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'");
				//        builder.append(" AND   EM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
				//        builder.append(" AND   EM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ") ");
				//        builder.append(" OR   (OM.RIS_ID = EM.RIS_ID ");
				//        builder.append(" AND   EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'");
				//        builder.append(" AND   OM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
				//        builder.append(" AND   OM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ")) ");
				//    }
				//    else
				//    {
				//        // 日未定ONの場合
				//        builder.append(" AND ((OM.RIS_ID = EM.RIS_ID ");
				//        builder.append(" AND   EM.RECEIPTFLAG = '" + CommonString.RECEIPT_FLG_ON + "'");
				//        builder.append(" AND   ((EM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
				//        builder.append(" AND     EM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ") ");
				//        builder.append(" OR      EM.KENSA_DATE = " + Configuration.GetInstance().GetUnKnownDateString() + ")) ");
				//        builder.append(" OR   (OM.RIS_ID = EM.RIS_ID ");
				//        builder.append(" AND   EM.RECEIPTFLAG <> '" + CommonString.RECEIPT_FLG_ON + "'");
				//        builder.append(" AND   ((OM.KENSA_DATE >= " + startDate.toString("yyyyMMdd"));
				//        builder.append(" AND     OM.KENSA_DATE <= " + closeDate.toString("yyyyMMdd") + ") ");
				//        builder.append(" OR      OM.KENSA_DATE = " + Configuration.GetInstance().GetUnKnownDateString() + "))) ");
				//    }
				//    // コメント
				//    //builder.append(" AND OM.KENSA_DATE >= "	+ param.GetExecutePeriodStartDate().toString("yyyyMMdd"));
				//    //builder.append(" AND OM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd"));
				//
				//    // 2011.08.19 Mod H.Orikasa End
				//}

				// 2011.08.19 Mod H.Orikasa End
			}
			else if (param.GetKanjaID().length() > 0)
			{
				//患者ID
				// 2011.06.24 Add K.Shinohara Start A0060
				builder.append(" AND OM.RIS_ID = EM.RIS_ID ");
				// 2011.06.24 Add K.Shinohara End
				builder.append(" AND OM.KANJA_ID = '" + param.GetKanjaID() + "'");
			}
			else
			{
				//エラー
				// 2011.06.24 Add K.Shinohara Start A0060
				builder.append(" AND OM.RIS_ID = EM.RIS_ID ");
				// 2011.06.24 Add K.Shinohara End
				builder.append(" AND OM.RIS_ID = 'FATAL'");
			}
		}
		return retBuilder;
	}
	*/

	/*
	/// <summary>
	/// 誕生日をString型へ変換
	/// </summary>
	/// <param name="birthdayStr">誕生日文字列</param>
	/// <param name="formatStr">フォーマット</param>
	/// <returns></returns>
	public static String ParseBirthdayToTimestamp(String birthdayStr, String formatStr, CultureInfo culture)
	{
		String retStr = "";

		try
		{
			if (birthdayStr.length() == 8)
			{
				String tempBirthdayStr = birthdayStr.SubString(0, 4);
				tempBirthdayStr += "/" + birthdayStr.SubString(4, 2);
				tempBirthdayStr += "/" + birthdayStr.SubString(6, 2);

				Timestamp birthdayDate = Timestamp.MinValue;
				try
				{
					birthdayDate = Timestamp.Parse(tempBirthdayStr);
				}
				catch
				{
				}
				if (birthdayDate != Timestamp.MinValue)
				{
					if (culture == null)
					{
						retStr = birthdayDate.toString(formatStr);
					}
					else
					{
						retStr = birthdayDate.toString(formatStr, culture);
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retStr;
	}
	*/

	/*
	/// <summary>
	/// 性別を変換する
	/// </summary>
	/// <param name="typeStr">タイプ</param>
	/// <param name="sexStr">性別文字列</param>
	/// <returns></returns>
	public static String ConvertSexName(String typeStr, String sexStr)
	{
		String retStr = "";
		String msgStr = "";
		if (sexStr == CommonString.IS_MELE)
		{
			//男
			msgStr = String.Format(MessageParameter.printerObjectM_MessageString, typeStr);
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(msgStr);
		}
		else if (sexStr == CommonString.IS_FEMALE)
		{
			//女
			msgStr = String.Format(MessageParameter.printerObjectF_MessageString, typeStr);
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(msgStr);
		}
		//else if (sexStr == CommonString.IS_OTHER)
		//{
		//    retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectO_MessageString);
		//}
		return retStr;
	}
	*/

	/*
	/// <summary>
	/// 入外区分を変換する
	/// </summary>
	/// <param name="nyugaiStr">入外区分文字列</param>
	/// <param name="denpyoFlg">伝票入外フラグ</param>
	/// <returns></returns>
	public static String ConvertInoutName(String nyugaiStr, boolean denpyoFlg)
	{
		String retStr = "";
		if (nyugaiStr == CommonString.IS_NYUUIN)
		{
			//入院
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectIn_MessageString);
		}
		else if (nyugaiStr == CommonString.IS_GAIRAI)
		{
			//外来
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectOut_MessageString);
		}
		else if (nyugaiStr == CommonString.NYUGAI_KBN_INOUT)
		{
			//伝票入外のみ
			if (denpyoFlg)
			{
				//入院中外来
				retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectInout_MessageString);
			}
		}
		return retStr;
	}
	*/

	/*
	/// <summary>
	/// 検査日を変換する
	/// </summary>
	/// <param name="kensadateStr">検査日文字列</param>
	/// <returns></returns>
	public static Timestamp ConvertKensaDate(String kensadateStr)
	{
		Timestamp retDate = Timestamp.MinValue;
		if (kensadateStr != null && kensadateStr.length() > 0 && kensadateStr.length() == 8)
		{
			try
			{
				String yyyyStr = kensadateStr.SubString(0, 4);
				String mmStr   = kensadateStr.SubString(4, 2);
				String ddStr   = kensadateStr.SubString(6, 2);
				String dateStr = yyyyStr + "/" + mmStr + "/" + ddStr;
				retDate  = Timestamp.Parse(dateStr);
			}
			catch
			{
			}
		}
		return retDate;
	}
	*/

	/// <summary>
	/// 検査時刻を変換する
	/// </summary>
	/// <param name="timeStr">検査時刻文字列</param>
	/// <returns></returns>
	public static String ConvertKensaTime(String timeStr)
	{
		String retStr = "";
		if (timeStr != null && timeStr.length() > 0)
		{
			//左0埋めで6桁にする
			int kensaTimeInt = timeStr.length();
			for (int k = kensaTimeInt; k < 6; k++)
			{
				timeStr = "0" + timeStr;
			}

			//特定時刻の場合は空を戻す
			if (CommonString.LIST_FORMAT_TIME_NULL1.equals(timeStr) ||
				timeStr.equals(CommonString.LIST_FORMAT_TIME_NULL2))
			{
				return retStr;
			}

			//フォーマットで整形し値を設定する
			try
			{
				String hhStr = timeStr.substring(0, 2);
				String miStr = timeStr.substring(2, 2 + 2);
				retStr = hhStr + ":" + miStr;
			}
			catch (Exception e)
			{
			}
		}
		return retStr;
	}

	/*
	// 2010.07.30 Add DD.Chinh Start
	/// <summary>
	/// 検査時刻を変換する
	/// </summary>
	/// <param name="timeStr">検査時刻文字列</param>
	/// <returns></returns>
	public static String ConvertKensaFullTime(String timeStr)
	{
		String retStr = "";
		if (timeStr != null && timeStr.length() > 0)
		{
			//左0埋めで6桁にする
			int kensaTimeInt = timeStr.length();
			for (int k = kensaTimeInt; k < 6; k++)
			{
				timeStr = "0" + timeStr;
			}

			//特定時刻の場合は空を戻す
			if (timeStr == CommonString.LIST_FORMAT_TIME_NULL1 ||
				timeStr == CommonString.LIST_FORMAT_TIME_NULL2)
			{
				return retStr;
			}

			//フォーマットで整形し値を設定する
			try
			{
				String hhStr = timeStr.SubString(0, 2);
				String miStr = timeStr.SubString(2, 2);
				String ssStr = timeStr.SubString(4, 2);
				retStr = hhStr + ":" + miStr + ":" + ssStr;
			}
			catch
			{
			}
		}
		return retStr;
	}
	// 2010.07.30 Add DD.Chinh End
	*/

	/*
	/// <summary>
	/// 撮影進捗を変換する
	/// </summary>
	/// <param name="statusStr">撮影進捗文字列</param>
	/// <returns></returns>
	public static String ConvertSatueiStatus(String statusStr)
	{
		String retStr = "";

		if (statusStr == CommonString.SATUEISTATUS_MI)
		{
			//未
			retStr = CommonString.SATUEISTATUS_MI_STR;
		}
		else if (statusStr == CommonString.SATUEISTATUS_STOP)
		{
			//中止
			retStr = CommonString.SATUEISTATUS_STOP_STR;
		}
		else if (statusStr == CommonString.SATUEISTATUS_SUMI)
		{
			//済
			retStr = CommonString.SATUEISTATUS_SUMI_STR;
		}

		return retStr;
	}
	*/

	/*
	/// <summary>
	/// RI区分を変換する
	/// </summary>
	/// <param name="riStr">RI区分文字列</param>
	/// <returns></returns>
	public static String ConvertRIKbn(String riStr)
	{
		String retStr = "";
		if (riStr == CommonString.RI_ORDER_FLG_NEEDLE)
		{
			//注射
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectRiNeedle_MessageString);
		}
		else if (riStr == CommonString.RI_ORDER_FLG_INSPECT)
		{
			//検査
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectRiInspect_MessageString);
		}
		return retStr;
	}
	*/

	/*
	/// <summary>
	/// RI区分を変換する
	/// </summary>
	/// <param name="riStr">RI区分文字列</param>
	/// <returns></returns>
	public static String ConvertRIKbnAll(String riStr)
	{
		String retStr = "";
		if (riStr == CommonString.RI_ORDER_FLG_NEEDLE)
		{
			//注射
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectRiNeedle_MessageString);
		}
		else if (riStr == CommonString.RI_ORDER_FLG_INSPECT)
		{
			//検査
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectRiInspect_MessageString);
		}
		else if (riStr == CommonString.RI_ORDER_FLG_FOLLOW)
		{
			//追跡
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectRiFollow_MessageString);
		}
		else
		{
			//その他
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectRiOther_MessageString);
		}
		return retStr;
	}
	*/

	/*
	/// <summary>
	/// 感染症マークを変換する
	/// </summary>
	/// <param name="markStr">感染症マーク文字列</param>
	/// <returns></returns>
	public static String ConvertInfectionMark(String markStr)
	{
		String retStr = "";
		if (markStr == CommonString.FLG_ON)
		{
			//あり
			retStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.printerObjectInfectionMarkON_MessageString);
		}
		return retStr;
	}
	*/

	/// <summary>
	/// 拡張色定義を取得する
	/// </summary>
	/// <param name="dt">拡張色定義情報</param>
	/// <param name="key">キー</param>
	/// <returns></returns>
	public static ExtendColorDefineBean GetExtendColorDefine(DataTable dt, String key, Connection con)
	{
		ExtendColorDefineBean retBean = new ExtendColorDefineBean();
		try
		{
			//未指定の場合は取得する
			if (dt == null)
			{
				dt = Configuration.GetInstance().GetRRisExtendColorDefine(con);
			}

			//キーを元に行を取得する
			String whereStr = MasterUtil.RIS_KEYVALUE + "='" + key + "'";
			for (int i = 0; i < dt.getRowCount(); i++){

				DataRow row = dt.getRows().get(i);
				if (row.get(MasterUtil.RIS_KEYVALUE).toString().equals(key)) {
					retBean.SetKeyValue(row.get(MasterUtil.RIS_KEYVALUE).toString());
					retBean.SetLabel(row.get(MasterUtil.RIS_LABEL).toString());
					retBean.SetColorMode(row.get(MasterUtil.RIS_COLORMODE).toString());
					retBean.SetColor(row.get(MasterUtil.RIS_COLOR).toString());
					retBean.SetColorBk(row.get(MasterUtil.RIS_COLORBK).toString());
					retBean.SetShoworder(row.get(MasterUtil.RIS_SHOWORDER).toString());
					break;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retBean;
	}

	/*
	// 2010.06.23 Add T.Nishikubo Start
	/// <summary>
	/// 分から時間・分に変換
	/// </summary>
	/// <param name="ttlMin">変換元の分</param>
	/// <returns>時間と分のテーブル</returns>
	public static Hashtable GetHourMinute(String ttlMin)
	{
		logger.debug("begin");
		Hashtable retTbl = new Hashtable();
		try
		{
			int wkMin = 0;
			if (Integer.parseInt(ttlMin) > 0)
			{
				wkMin = Integer.parseInt(ttlMin);
			}
			retTbl.Add(CommonString.KEY_HOUR, (wkMin / 60).toString());
			retTbl.Add(CommonString.KEY_MINUTE, (wkMin % 60).toString());
		}
		catch (Exception ex)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString) + "\r\n" + ex);
		}
		finally
		{
			logger.debug("end");
		}
		return retTbl;
	}
	// 2010.06.23 Add T.Nishikubo End
	*/

	/*
	// 2011.08.19 Mod H.Orikasa Start A0060(修正)
	/// <summary>
	/// 日付を用意する
	/// </summary>
	/// <param name="startDate">開始日</param>
	/// <param name="endDate">終了日</param>
	/// <param name="orderDate">予定検査日</param>
	/// <param name="exDate">実績検査日</param>
	/// <param name="isUnknownDate">日未定フラグ</param>
	public static void CheckDateSpan(ref Timestamp startDate, ref Timestamp endDate, Timestamp orderDate, Timestamp exDate, ref boolean isUnknownDate)
	{
		try
		{
			// 日未定対応

			String unknownStr = Configuration.GetInstance().GetUnKnownDateString();
			String uStartDate = orderDate.toString(CommonString.LIST_FORMAT_DATE_2);
			String uCloseDate = exDate.toString(CommonString.LIST_FORMAT_DATE_2);
			if (Configuration.GetInstance().IsUnKnownDate())
			{
				if (uStartDate == unknownStr && uCloseDate != unknownStr)
				{
					//開始日が日未定の場合、終了日を開始日に上書きする
					isUnknownDate = true;
					orderDate = exDate;
				}
				else if (uStartDate != unknownStr && uCloseDate == unknownStr)
				{
					//終了日が日未定の場合、開始日を終了日に上書きする
					isUnknownDate = true;
					exDate = orderDate;
				}
				else if (uStartDate == unknownStr && uCloseDate == unknownStr)
				{
					//開始日、終了日が日未定の場合は日未定フラグONで抜ける
					isUnknownDate = true;
					return;
				}
			}



			//開始日のチェック(予定検査日)
			if (startDate > orderDate)
			{
				startDate = orderDate;
			}
			//開始日のチェック(実績検査日)
			if (startDate > exDate)
			{
				startDate = exDate;
			}

			//終了日のチェック(予定検査日)
			if (endDate < orderDate)
			{
				endDate = orderDate;
			}
			//終了日のチェック(実績検査日)
			if (endDate < exDate)
			{
				endDate = exDate;
			}
		}
		catch (Exception ex)
		{
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString) + "\r\n" + ex);
			logger.fatal(ex);
		}
	}
	*/
	// コメント
	//// 2010.10.29 Mod K.Shinohara Start
	///// <summary>
	///// 日付を用意する
	///// </summary>
	///// <param name="startDate">開始日</param>
	///// <param name="endDate">終了日</param>
	///// <param name="orderDate">検査日</param>
	//public static void CheckDateSpan(ref Timestamp startDate, ref Timestamp endDate, Timestamp kensaDate)
	//{
	//    try
	//    {
	//        //開始日のチェック
	//        if (startDate > kensaDate)
	//        {
	//            startDate = kensaDate;
	//        }

	//        //終了日のチェック
	//        if (endDate < kensaDate)
	//        {
	//            endDate = kensaDate;
	//        }
	//    }
	//    catch (Exception ex)
	//    {
	//        Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString) + "\r\n" + ex);
	//        logger.fatal(ex);
	//    }
	//}
	//// 2010.10.29 Mod K.Shinohara End

	// 2011.08.19 Mod H.Orikasa End
}
