package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;

import ris.lib.core.Configuration;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.TextUtil;
import ris.portable.common.Const;
import ris.portable.util.DataTable;


/// <summary>
///
/// RECEIPTKANJANUMBERCONTROL情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// 2.04.001 	: 2010.10.04	: 999999 / DD.Chinh     extends original	KANRO-R-6
///
/// </summary>
public class RisReceiptKanjaNumberControlTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME		= "RECEIPTKANJANUMBERCONTROL";
	public static final String TABLE_CAPTION	= "受付患者番号管理ﾃｰﾌﾞﾙ";

	// カラム定義
	public static final String KANJAID_COLUMN		 = "KANJA_ID";
	public static final String RECEIPTNUMBER_COLUMN = "RECEIPTNUMBER";
	public static final String RECEIPTDATE_COLUMN	 = "RECEIPTDATE";
	public static final String MAXNO_COLUMN		 = "MAXNO";			// 仮想カラム

	/// <summary>
	/// publicコンストラクタ
	/// </summary>
	/// <remarks></remarks>
	public RisReceiptKanjaNumberControlTbl()
	{
		this.tableNameStr	= TABLE_NAME;
		this.infoCaption	= TABLE_CAPTION;

		if (RisReceiptKanjaNumberControlTbl.TABLE_NAME.equals(this.tableNameStr))
		{
			//検索条件チェック無効化
			this.IsCheckSQLWhere = false;
		}
	}

	/// <summary>
	/// 受付患者番号情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>受付患者番号情報</returns>
	public String GetData(Connection con, String kanjaID) throws Exception
	{
		// parameters
		DataTable dt = null;
		Integer number = Const.INT_MINVALUE;

		// start log
		logger.debug("begin");

		if (con != null)
		{
			// MAX受付番号の取得
			number = GetMaxReceiptNumber(con);

			// 患者IDで検索
			dt = Select(con, CreateSelectSQL(kanjaID), null);
			if (dt != null)
			{
				// 患者IDが既に登録された場合
				if (dt.getRowCount() > 0)
				{
					Timestamp sysDate = new Timestamp(System.currentTimeMillis());
					Timestamp receiptDate = new Timestamp(System.currentTimeMillis());
					try
					{
						sysDate		= Configuration.GetInstance().GetSysDate(con);	// 2011.05.23 Mod H.Orikasa 業務詳細高速化対応
						//sysDate	= Configuration.GetInstance().GetSysDate().Date;			//
						receiptDate = TextUtil.ParseStringToDateTime(dt.getRows().get(0).get(RECEIPTDATE_COLUMN).toString());
					}
					catch (Exception e)
					{
					}

					//現在日付が受付日付を越えていたら、MAX受付番号+1を発番する
					if (sysDate.compareTo(receiptDate) > 0)
					{
						number++;
					}
					//現在日付が受付日付だったら、現在の受付番号を発番する
					else
					{
						number = TextUtil.ParseStringToInt(dt.getRows().get(0).get(RECEIPTNUMBER_COLUMN).toString());
					}

					// 更新
					UpdateReceptKanjaNumberControlData(con, kanjaID, number.toString());
				}

				// 患者IDが未登録の場合
				else
				{
					number++;

					// 新規登録
					InsertReceptKanjaNumberControlData(con, kanjaID, number.toString());
				}
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

		return number.toString();
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String kanjaID)
	{
		// start log
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(KANJAID_COLUMN, kanjaID, true, SignType.Equal);

		// end log
		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// MAX受付患者番号を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>MAX受付患者番号</returns>
	public int GetMaxReceiptNumber(Connection con) throws Exception
	{
		// parameters
		DataTable dt = null;
		Integer maxNo = Const.INT_MINVALUE;

		// start log
		logger.debug("begin");

		if (con != null)
		{
			dt = Select(con, CreateSelectMaxReceiptNumberSQL(), null);

			if (dt != null)
			{
				if (dt.getRowCount() > 0)
				{
					maxNo = TextUtil.ParseStringToInt(dt.getRows().get(0).get(MAXNO_COLUMN).toString());
				}
			}
		}

		return maxNo;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectMaxReceiptNumberSQL()
	{
		String sqlStr = "SELECT NVL(MAX(" + RECEIPTNUMBER_COLUMN
					  + "),0) AS " + MAXNO_COLUMN + " FROM " + TABLE_NAME
					  + " WHERE " + RECEIPTDATE_COLUMN + " >= TO_DATE(TO_CHAR("
					  + SysDateTbl.SYSDATE_COLUMN + ", 'YYYY/MM/DD'))";
		return sqlStr;
	}

	/// <summary>
	/// 受付患者番号管理情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="receiptNumberStr">受付番号</param>
	/// <returns>判定</returns>
	public boolean UpdateReceptKanjaNumberControlData(Connection con, String kanjaID, String receiptNumberStr) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && kanjaID != null && kanjaID.length() > 0 &&
			receiptNumberStr != null && receiptNumberStr.length() > 0)
		{
			String updateSqlStr = CreateUpdateSQL(kanjaID, receiptNumberStr);
			retFlg = Update(con, updateSqlStr, null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="receiptNumberStr">受付番号</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateSQL(String kanjaID, String receiptNumberStr)
	{
		// start log
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RECEIPTNUMBER_COLUMN, receiptNumberStr);
		this.AddColSetValue(RECEIPTDATE_COLUMN, SysDateTbl.SYSDATE_COLUMN);
		//
		this.AddColValue(KANJAID_COLUMN, kanjaID, true, SignType.Equal);

		// end log
		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/// <summary>
	/// 受付患者番号管理情報を新規追加する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="receiptNumberStr">受付番号</param>
	/// <returns>判定</returns>
	public boolean InsertReceptKanjaNumberControlData(Connection con, String kanjaID, String receiptNumberStr) throws Exception
	{
		// paremeters
		boolean retBool = false;

		// start log
		logger.debug("begin");

		if (con != null && kanjaID != null && kanjaID.length() > 0 &&
			receiptNumberStr != null && receiptNumberStr.length() > 0)
		{
			String insertSqlStr = CreateInsertSQL(kanjaID, receiptNumberStr);
			retBool = Insert(con, insertSqlStr, null);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="receiptNumberStr">受付番号</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL(String kanjaID, String receiptNumberStr)
	{
		logger.debug("begin");

		this.keys.clear();
		inList.clear();
		try
		{
			if (kanjaID != null && kanjaID.length() > 0 &&
				receiptNumberStr != null && receiptNumberStr.length() > 0)
			{
				this.AddColValue(KANJAID_COLUMN, kanjaID);
				this.AddColValue(RECEIPTNUMBER_COLUMN, receiptNumberStr);
				this.AddColSetValue(RECEIPTDATE_COLUMN, SysDateTbl.SYSDATE_COLUMN);
			}
		}
		catch (Exception e)
		{
			logger.fatal(e);
		}

		logger.debug("end");

		return this.GetInsertSQL();
	}
}
