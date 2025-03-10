package ris.lib.core.util;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.portable.common.Const;

/// <summary>
/// TextUtil の概要の説明です。
/// </summary>
/// <remarks>
/// サロゲートペアチェック等、文字列に関するUTILITYクラス
///
/// Copyright (C) 2009-2011, Yokogawa Medical Solutions Corporation
///
///	(Rev.)		      (Date)            (ID / NAME)			  (Comment)
/// V2.01.00        extends 2011.08.05  extends 999999 / DD.Chinh       extends NML-CAT3-035
///
/// </remarks>
public class TextUtil
{
	private static Log logger = LogFactory.getLog(TextUtil.class);

	/// <summary>
	/// 文字チェック関数
	/// </summary>
	/// <param name="text">チェックする文字列</param>
	/// <returns>true:チェックOK false:チェックNG（Unicode形式の文字、サロゲート文字を含んでいる）</returns>
	//public static boolean CheckShiftJis(String text)
	//{
	//	boolean flgBool = true;
	//	text = text.Replace("\r\n", "");
	//
	//	Encoding utf = Encoding.GetEncoding("utf-8");
	//	Encoding sjis = Encoding.GetEncoding(932);
	//
	//	for (int i = 0; i < text.length(); i++)
	//	{
	//		byte[] utfBstr = utf.GetBytes(text.SubString(i, 1));
	//		byte[] sjisBstr = sjis.GetBytes(text.SubString(i, 1));
	//
	//		String sjisStr = System.Text.Encoding.GetEncoding(932).GetString(sjisBstr);
	//		String utfStr = System.Text.Encoding.UTF8.GetString(utfBstr);
	//
	//		//UTF と CP932 で文字列が文字列が文字化けするかを確認する
	//		if (!sjisStr.Equals(utfStr))
	//		{
	//			return false;
	//		}
	//
	//		/* 省略
	//		//念のための文字コードで範囲チェック
	//		if ((sjisBstr[0] >= 0x00 && sjisBstr[0] <= 0x7E) || (sjisBstr[0] >= 0xA1 && sjisBstr[0] <= 0xDF))
	//		{
	//			// ASCII        extends 0x00～0x7F
	//			// 半角カタカナ extends 0xA1～0xDF
	//		}
	//		else if (
	//				((sjisBstr[0] >= 0x81 && sjisBstr[0] <= 0x9F) || (sjisBstr[0] >= 0xE0 && sjisBstr[0] <= 0xFC)) &&
	//				((sjisBstr[1] >= 0x40 && sjisBstr[1] <= 0x7E) || (sjisBstr[1] >= 0x80 && sjisBstr[1] <= 0xFC))
	//				)
	//		{
	//			// 漢字 extends 第1バイト: 0x81～0x9F、0xE0～0xFC
	//			//        第2バイト: 0x40～0x7E、0x80～0xFC
	//		}
	//		else
	//		{
	//			return false;
	//		}
	//		*/
	//
	//		//サロゲート文字の判定
	//		if (Char.IsSurrogate(text[i]))
	//		{
	//			return false;
	//		}
	//	}
	//
	//	/* 省略
	//	//結合文字の判定
	//	System.Globalization.StringInfo info = new System.Globalization.StringInfo(text);
	//	for (int i = 0; i < info.length()InTextElements; i++)
	//	{
	//		String surrogateCheckStr = info.SubStringByTextElements(i, 1);
	//		char[] surrogateCheckChar = surrogateCheckStr.ToCharArray();
	//		if (surrogateCheckChar.length() > 1)
	//		{
	//			return false;
	//		}
	//	}
	//	*/
	//	return flgBool;
	//}

	/// <summary>
	/// String型変数をint型変数へと変換
	/// </summary>
	/// <param name="numStr">変換対象文字</param>
	/// <returns>int型変数 変換失敗時は0を返す</returns>
	public static int ParseStringToInt(String numStr)
	{
		int retInt = 0;
		if (numStr != null && !"".equals(numStr))
		{
			//try-catch
			try
			{
				retInt = Integer.parseInt(numStr);
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
		return retInt;
	}

	/*
	// 2010.09.11 Add K.Shinohara Start
	/// <summary>
	/// String型変数をint型変数へと変換(デフォルト値定義)
	/// </summary>
	/// <param name="numStr">変換対象文字</param>
	/// <returns>int型変数 変換失敗時は0を返す</returns>
	public static int ParseStringToInt(String numStr, int defInt)
	{
		int retInt = defInt;
		if (numStr != null && numStr != "")
		{
			//try-catch
			try
			{
				retInt = Integer.parseInt(numStr);
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
		return retInt;
	}
	// 2010.09.11 Add K.Shinohara End
	*/

	/*
	/// <summary>
	/// String型変数をdecimal型変数へと変換
	/// </summary>
	/// <param name="numStr">変換対象文字</param>
	/// <returns>decimal型変数 変換失敗時はdecimal.MinValueを返す</returns>
	public static decimal ParseStringToDecimal(String numStr)
	{
		decimal retDec = decimal.MinValue;
		if (numStr != null && numStr != "")
		{
			//try-catch
			try
			{
				retDec = decimal.Parse(numStr);
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
		return retDec;
	}
	*/

	/// <summary>
	/// String型変数をdouble型変数へと変換
	/// </summary>
	/// <param name="numStr">変換対象文字</param>
	/// <returns>decimal型変数 変換失敗時はdouble.MinValueを返す</returns>
	public static Double ParseStringToDouble(String numStr)
	{
		Double retDbl = Const.DOUBLE_MINVALUE;

		if (numStr != null && numStr != "")
		{
			//try-catch
			try
			{
				retDbl = Double.parseDouble(numStr);
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
		return retDbl;
	}

	/// <summary>
	/// String型変数をdatetime型変数へと変換
	/// </summary>
	/// <param name="dateStr">変換対象文字</param>
	/// <returns>datetime型変数 変換失敗時はTimestamp.MinValueを返す</returns>
	public static Timestamp ParseStringToDateTime(String dateStr)
	{
		Timestamp retDate = Const.TIMESTAMP_MINVALUE;
		if (dateStr != null && !"".equals(dateStr))
		{
			//try-catch
			try
			{
				retDate = Timestamp.valueOf(dateStr);
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
		return retDate;
	}

	/*
	/// <summary>
	/// String型変数をdatetime型変数へと変換
	/// </summary>
	/// <param name="dateStr"></param>
	/// <returns></returns>
	public static Timestamp ParseDateStrToTimestamp(String dateStr)
	{
		Timestamp retDate = Timestamp.MinValue;
		if (dateStr != null && dateStr != "")
		{
			//try-catch
			try
			{
				if (dateStr.length() > 0 && dateStr.length() == 6)
				{
					String workStr = "";
					workStr = dateStr.SubString(0, 2) + ":" +
							  dateStr.SubString(2, 2) + ":" +
							  dateStr.SubString(4, 2);
					retDate = Timestamp.Parse(workStr);
				}
				else if (dateStr.length() > 0 && dateStr.length() == 8)
				{
					String workStr = "";
					workStr = dateStr.SubString(0, 4) + "/" +
							  dateStr.SubString(4, 2) + "/" +
							  dateStr.SubString(6, 2);
					retDate = Timestamp.Parse(workStr);
				}
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}
		}
		return retDate;
	}
	*/

	/*
	/// <summary>
	/// 検査時刻を文字列に変換する
	/// </summary>
	/// <param name="timeStr"></param>
	/// <returns></returns>
	public static String ConvertKensaTimeString(String timeStr)
	{
		String retStr = "00:00:00";
		try
		{
			//左0埋めで6桁にする
			timeStr = timeStr.PadLeft(6, '0');

			String hhStr = timeStr.SubString(0, 2);
			String miStr = timeStr.SubString(2, 2);
			String ssStr = timeStr.SubString(4, 2);
			retStr = hhStr + ":" + miStr + ":" + ssStr;
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
	/// カンマ編集された検索条件を取得
	/// </summary>
	/// <param name="list"></param>
	/// <returns>String</returns>
	public static String GetSearchCommaParam(ArrayList list)
	{
		String resStr = "";
		try
		{
			for (int i = 0; i < list.Count; i++)
			{
				if (i == 0)
				{
					resStr = list[i].toString();
				}
				else
				{
					resStr = resStr + "," + list[i].toString();
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return resStr;
	}
	*/

	/*
	// 2011.08.05 Add DD.Chinh@MERX Start NML-CAT3-035
	/// <summary>
	/// 文字列を指定したエンコードでバイト長にカットします
	/// </summary>
	/// <param name="str">カットしたい文字列</param>
	/// <param name="byteCount">バイト数</param>
	/// <param name="encoding">エンコード</param>
	/// <returns>カットされた文字列</returns>
	public static String ByteCutString(String str, int byteCount, Encoding encoding)
	{
		// 2011.08.22 Mod DD.Chinh@MERX Start NML-CAT3-035
		String retStr = str;
		try
		{
			//引数が空の場合は空の文字列を返します
			if (String.IsNullOrEmpty(str) || byteCount <= 0 || encoding == null)
			{
				return String.Empty;
			}
			//現在のバイト数を取得します
			int nowStrByteCount = encoding.GetByteCount(str);
			//指定したバイト数を超えていた場合に、文字列をカットする処理を行います
			if (byteCount < nowStrByteCount)
			{
				//１文字の大きさを計算するためのリスト
				System.Collections.Generic.List<char> charByteCountCalcList = new System.Collections.Generic.List<char>(byteCount);
				//文字を格納するためのバッファ
				StringBuilder buff = new StringBuilder(byteCount);
				//現在のバイト数を初期化
				nowStrByteCount = 0;
				for (int i=0; i < byteCount; i++)
				{
					char c = str[i];
					//１文字の大きさを取得する
					charByteCountCalcList.clear();
					charByteCountCalcList.Add(c);
					int charByteSize = encoding.GetByteCount(charByteCountCalcList.ToArray());
					// byteCountを超えるようであれば終了する
					nowStrByteCount += charByteSize;
					if (byteCount < nowStrByteCount)
					{
						break;
					}
					buff.append(c);
				}
				retStr = buff.toString();
			}
		}
		catch (Exception ex)
		{
			retStr = String.Empty;
			logger.fatal(ex);
		}
		return retStr;

		// コメント
		////引数が空の場合は空の文字列を返します
		//if (encoding == null || byteCount <= 0 || String.IsNullOrEmpty(str))
		//{
		//    return String.Empty;
		//}
		////現在のバイト数を取得します
		//int nowStrByteCount = encoding.GetByteCount(str);
		////指定したバイト数を超えていた場合に、文字列をカットする処理を行います
		//if (byteCount < nowStrByteCount)
		//{
		//    //１文字の大きさを計算するためのリスト
		//    System.Collections.Generic.List<char> charByteCountCalcList = new System.Collections.Generic.List<char>(byteCount);
		//    //文字を格納するためのバッファ
		//    StringBuilder buff = new StringBuilder(byteCount);
		//    //現在のバイト数を初期化
		//    nowStrByteCount = 0;
		//    for (int i=0; i < byteCount; i++)
		//    {
		//        char c = str[i];
		//        //１文字の大きさを取得する
		//        charByteCountCalcList.clear();
		//        charByteCountCalcList.Add(c);
		//        int charByteSize = encoding.GetByteCount(charByteCountCalcList.ToArray());
		//        // byteCountを超えるようであれば終了する
		//        nowStrByteCount += charByteSize;
		//        if (byteCount < nowStrByteCount)
		//        {
		//            break;
		//        }
		//        buff.append(c);
		//    }
		//    str = buff.toString();
		//}
		//return str;

		// 2011.08.22 Mod DD.Chinh@MERX End
	}
	// 2011.08.05 Add DD.Chinh@MERX End
	*/
}
