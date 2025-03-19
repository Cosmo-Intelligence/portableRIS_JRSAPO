package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.TextUtil;
import ris.portable.common.Const;
import ris.portable.util.DataTable;

/// <summary>
///
/// RECEIPTNUMBERCONTROL情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.03.25	: 112478 / A.Kobayashi		: original
/// V2.01.01.13000	: 2011.11.15    extends 999999 / NSK_H.Hashimoto	: OMH-1-05
///
/// </summary>
public class RisReceiptNumberControlTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "RECEIPTNUMBERCONTROL";
	public static final String TABLE_CAPTION	= "受付番号管理ﾃｰﾌﾞﾙ";

	// カラム定義
	public static final String RECEIPTNUMBER_COLUMN             = "RECEIPTNUMBER";
	public static final String MAXNUMBER_COLUMN                 = "MAXNUMBER";
	public static final String RESETDATE_COLUMN                 = "RESETDATE";
	public static final String RESETTIMING_COLUMN               = "RESETTIMING";
	public static final String KEYVALUE_COLUMN			         = "KEYVALUE";
	public static final String DEFAULT							 = "DEFAULT";

	// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
	public static final String MINNUMBER_COLUMN = "MINNUMBER";
	public static final String RANDOM_FLG_COLUMN = "RANDOM_FLG";

	// 定数定義
	private  final String TIME_FORMAT_SHORT = "yyyy/MM/dd";
	private  final String TIME_FORMAT_LONG = "yyyy/MM/dd HHmmss";
	// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

	// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
	public static final String INITIAL_CHAR_COLUMN = "INITIAL_CHAR";
	// 2011.11.15 Add NSK_H.Hashimoto End

	/// <summary>
	/// publicコンストラクタ
	/// </summary>
	/// <remarks></remarks>
	public RisReceiptNumberControlTbl()
	{
		this.tableNameStr	= TABLE_NAME;
		this.infoCaption	= TABLE_CAPTION;

		if (RisReceiptNumberControlTbl.TABLE_NAME.equals(this.tableNameStr))
		{
			//検索条件チェック無効化
			this.IsCheckSQLWhere = false;
		}
	}

	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// テーブル情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="keyValue">KEY情報</param>
	/// <returns>システム設定情報</returns>
	public String GetData(Connection con, String keyValue) throws Exception
	// コメント
	///// <summary>
	///// テーブル情報を取得する
	///// </summary>
	///// <remarks></remarks>
	///// <param name="con">データベース接続オブジェクト</param>
	///// <param name="trans">DBトランザクションオブジェクト</param>
	///// <returns>システム設定情報</returns>
	//public String GetData( Connection con, Transaction trans )

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		// parameters
		DataTable dt = null;
		String number = null;

		// start log
		logger.debug("begin");

		if( con != null )
		{
			// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
			dt = Select(con, CreateSelectSQL(keyValue), null);
			// コメント
			//dt = Select( con, trans, CreateSelectSQL() );

			// 2011.03.09 Mod T.Ohyama@CIJ End MY-1-08

			if (dt != null)
			{
				//　RECEIPTNUMBER	：受付番号
				//	RESETDATE		：受付番号ﾘｾｯﾄ日
				//	RESETTIMING		：受付番号ﾘｾｯﾄ時刻
				//	MAXNUMBER		：受付番号最大数

				// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
				Timestamp lastRegDate = Const.TIMESTAMP_MINVALUE; // 最終発行日時
				try
				{
					lastRegDate = (Timestamp)dt.getRows().get(0).get(RESETDATE_COLUMN);
				}
				catch (Exception e)
				{
				}
				// コメント
				//String resetDate = dt.Rows[0][RESETDATE_COLUMN].toString();

				// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
				String resetTiming = dt.getRows().get(0).get(RESETTIMING_COLUMN).toString();

				SysDateTbl sysDateTbl  = new SysDateTbl();

				// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
				// システム時刻との比較が、日付での比較となっていた。
				//
				// リセット日時＝システム時刻の日付＆リセット時刻
				// に変更し、リセット日時と最終登録日時(DBのRESET_DATEカラム)との比較に変更
				// また、日付データの結合方法を改修した。

				Timestamp sysDatetime = Const.TIMESTAMP_MINVALUE;
				try
				{
					sysDatetime = (Timestamp)sysDateTbl.GetSysDate(con);
				}
				catch (Exception e)
				{

				}

				Timestamp resetDatetime = Const.TIMESTAMP_MINVALUE;
				try
				{
					if (resetTiming.length() == 6)
					{
						String s = new SimpleDateFormat(TIME_FORMAT_SHORT).format(sysDatetime) + " " + resetTiming;
						SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT_LONG);
						resetDatetime = new Timestamp(sf.parse(s).getTime());
					}
					else
					{
						resetDatetime = sysDatetime;
					}
				}
				catch (Exception e)
				{
				}

				// コメント
				//String sysDateStr = "";
				//try
				//{
				//    sysDateStr = ((Timestamp)sysDateTbl.GetSysDate(con, trans)).toString();
				//}
				//catch
				//{
				//}

				//Timestamp sysDatetime = new Timestamp();
				//Timestamp resetDatetime = new Timestamp();

				//try
				//{
				//    sysDatetime = new Timestamp(Integer.parseInt(sysDateStr.SubString(0, 4)), Integer.parseInt(sysDateStr.SubString(5, 2)), Integer.parseInt(sysDateStr.SubString(8, 2)));

				//    if (resetTiming.length() == 6)
				//    {
				//        resetDatetime = new Timestamp(Integer.parseInt(resetDate.SubString(0, 4)), Integer.parseInt(resetDate.SubString(5, 2)), Integer.parseInt(resetDate.SubString(8, 2)), Integer.parseInt(resetTiming.SubString(0, 2)), Integer.parseInt(resetTiming.SubString(2, 2)), Integer.parseInt(resetTiming.SubString(4, 2)));
				//    }
				//    else
				//    {
				//        resetDatetime = new Timestamp(Integer.parseInt(resetDate.SubString(0, 4)), Integer.parseInt(resetDate.SubString(5, 2)), Integer.parseInt(resetDate.SubString(8, 2)));
				//    }
				//}
				//catch
				//{
				//}

				// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08

				// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
				// 最終発行日がリセット時刻よりも前で、現在時刻がリセット時刻を越えていた場合は、
				// 受付番号1を発番する
				// (本日中に既にリセットが行われている場合はリセットしない)
				if (sysDatetime.compareTo(resetDatetime) >= 0 && resetDatetime.compareTo(lastRegDate) >= 0)
				// コメント
				////現在日時がﾘｾｯﾄ日時を越えていたら受付番号1を発番する
				//if (sysDatetime > resetDatetime)

				// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
				{
					number = "1";

					// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
					if (CommonString.SYSTEMPARAM_1.equals(Configuration.GetInstance().GetSystemParam().GetReceiptRuleValue1Str()))
					{
						number = dt.getRows().get(0).get(MINNUMBER_COLUMN).toString();
					}
					// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08
				}
				//受付番号が最大数と同じ場合は、受付番号0を発番する
				else if (dt.getRows().get(0).get(MAXNUMBER_COLUMN).toString().equals(dt.getRows().get(0).get(RECEIPTNUMBER_COLUMN).toString()))
				{
					number = "1";

					// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
					if (CommonString.SYSTEMPARAM_1.equals(Configuration.GetInstance().GetSystemParam().GetReceiptRuleValue1Str()))
					{
						number = dt.getRows().get(0).get(MINNUMBER_COLUMN).toString();
					}
					// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08
				}
				else
				{
					Integer numberInt = Integer.parseInt(dt.getRows().get(0).get(RECEIPTNUMBER_COLUMN).toString()) + 1;
					number = numberInt.toString();
				}

				// 更新
				// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
				UpdateReceptNumberControlData(con, keyValue, number);
				// コメント
				//UpdateReceptNumberControlData(con, trans, number);

				// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08

				// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
				if (CommonString.SYSTEMPARAM_1.equals(Configuration.GetInstance().GetSystemParam().GetReceiptRuleValue2Str())
					&& CommonString.RECEIPT_RANDOM_FLG_ON.equals(dt.getRows().get(0).get(RANDOM_FLG_COLUMN).toString()))
				{
					// マッピング検索用のテーブルアクセスクラスを生成
					RisReceiptNumberMappingTbl risReceiptNumberMapping = new RisReceiptNumberMappingTbl();
					// ランダム番号を検索してあった場合はnumberをランダム番号で置き換える
					String randomReceiptNumber = risReceiptNumberMapping.GetRandomReceiptNumber(con, keyValue, TextUtil.ParseStringToInt(number));
					if (!StringUtils.isEmpty(randomReceiptNumber))
					{
						number = randomReceiptNumber;
					}
				}
				// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08
			}
			else
			{
				//取得できなかった時：
				//受付番号取得排他テーブルを解除（DELETE）し、
				//受付処理を失敗とする
				String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getReceiptNumberError_MessageString);
				throw new Exception(msg);
			}
		}

		// end log
		logger.debug("end");

		return number;
	}

	/*
	// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
	/// <summary>
	/// 受付番号の頭文字を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="keyValue">KEY情報</param>
	/// <returns>システム設定情報</returns>
	public String GetInitialChar(Connection con, Transaction trans, String keyValue)
	{
		// parameters
		DataTable dt = null;
		String strValue = null;

		// start log
		logger.debug("begin");

		if ((con != null) && (trans != null))
		{
			dt = Select(con, trans, CreateSelectSQL(keyValue));

			if (dt != null)
			{
				strValue = dt.Rows[0][INITIAL_CHAR_COLUMN].toString();
			}
			else
			{
				//取得できなかった時：
				//受付番号取得排他テーブルを解除（DELETE）し、
				//受付処理を失敗とする
				String msg =
					Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getReceiptNumberError_MessageString);
				throw new Exception(msg);
			}
		}

		// end log
		logger.debug("end");

		return strValue;
	}
	// 2011.11.15 Add NSK_H.Hashimoto End
	*/

	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// 受付番号管理情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="receiptNumberStr">受付番号</param>
	/// <returns></returns>
	public boolean UpdateReceptNumberControlData(Connection con, String keyValue, String receptNumberStr) throws Exception
	// コメント
	///// <summary>
	///// 受付番号管理情報を更新する
	///// </summary>
	///// <param name="con">データベース接続オブジェクト</param>
	///// <param name="trans">DBトランザクションオブジェクト</param>
	///// <param name="patientBean">更新後の患者データ</param>
	///// <returns></returns>
	//public boolean UpdateReceptNumberControlData(Connection con, Transaction trans, String receptNumberStr)

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && receptNumberStr != null)
		{
			// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
			retFlg = Update(con, CreateUpdateSQL(keyValue, receptNumberStr), null);
			// コメント
			//retFlg = Update(con, trans, CreateUpdateSQL(receptNumberStr));

			// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="receiptNumberStr">受付番号</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateUpdateSQL(String keyValue, String receptNumberStr)
	// コメント
	///// <summary>
	///// SELECT用のSQL文を作成する
	///// </summary>
	///// <remarks></remarks>
	///// <param name="con">データベース接続オブジェクト</param>
	///// <returns>SELECT用のSQL文</returns>
	//private String CreateUpdateSQL(String receptNumberStr)

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		// start log
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RECEIPTNUMBER_COLUMN, receptNumberStr);
		this.AddColSetValue(RESETDATE_COLUMN, SysDateTbl.SYSDATE_COLUMN);

		// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
		this.AddColValue(KEYVALUE_COLUMN, keyValue, true, SignType.Equal);
		// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

		// end log
		logger.debug("end");

		return this.GetUpdateSQL();
	}

	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="keyValue">KEY情報</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String keyValue)
	// コメント
	///// <summary>
	///// SELECT用のSQL文を作成する
	///// </summary>
	///// <remarks></remarks>
	///// <param name="con">データベース接続オブジェクト</param>
	///// <returns>SELECT用のSQL文</returns>
	//private String CreateSelectSQL()

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		// start log
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
		this.AddColValue(KEYVALUE_COLUMN, keyValue, true, SignType.Equal);
		//this.AddColValue(KEYVALUE_COLUMN, "DEFAULT", true, SignType.Equal);
		// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08

		// end log
		logger.debug("end");

		return this.GetSelectSQL();
	}
}
