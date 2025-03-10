package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.Configuration;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.util.MessageParameter;
import ris.portable.util.DataTable;

/// <summary>
/// 排他テーブルの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.05.14	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class RisReceiptNumberAccessInfoTbl extends BaseTbl
{
	// アクセステーブル名
	private String TableNameStr = "RECEIPTNUMBERACCESSINFO";
	private  final String TABLE_CAPTION = "";

	// カラム定義
	private String KEYVALUE_COLUMN = "KEYVALUE";
	private String RIS_ID_COLUMN = "RIS_ID";
	private String IPADDRESS_COLUMN = "IPADDRESS";
	private String ENTRYTIME_COLUMN = "ENTRYTIME";
	//
	public RisReceiptNumberAccessInfoTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
		this.tableNameStr = TableNameStr;
		this.infoCaption = TABLE_CAPTION;
	}

	//
	/// <summary>
	/// 編集対象のデータとチェックアウトIDの整合性を確認する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="dataUIDStr">チェックアウト対象のデータUID</param>
	/// <returns></returns>
	public boolean CanCheckin(Connection con, String checkoutIDStr, String dataUIDStr) throws Exception
	{
		// paremeters
		boolean retFlg = true;
		DataTable dt = null;

		// start log
		logger.debug("begin");

		try
		{
			if (dataUIDStr != null && checkoutIDStr != null)
			{
				if (dataUIDStr.length() > 0 && checkoutIDStr.length() > 0)
				{
					dt = Select(con, CreateCheckDataSelectSQL(checkoutIDStr, dataUIDStr), null);
				}

				if (dt != null)
				{
					if (dt.getRowCount() > 0)
					{
						retFlg = false;
					}
				}
			}
		}
		catch (Exception e)
		{
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutCheckoutIDException_MessageString);
			logger.fatal(message + dataUIDStr, e);
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutCheckoutIDException_MessageString) + checkoutIDStr, e);

			throw e;
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	//
	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// 編集を完了したデータのロックを外す
	/// </summary>
	/// <remarks></remarks>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="checkoutIDStr">チェックアウトID</param>
	/// <returns>【アンチェックアウト成功】true　【アンチェックアウト失敗】false</returns>
	public boolean Uncheckout(Connection con, String keyValue, String checkoutIDStr) throws Exception
	// コメント
	///// <summary>
	///// 編集を完了したデータのロックを外す
	///// </summary>
	///// <remarks></remarks>
	///// <param name="checkoutIDStr">チェックアウトID</param>
	///// <returns>【アンチェックアウト成功】true　【アンチェックアウト失敗】false</returns>
	//public boolean Uncheckout(Connection con, Transaction transaction, String checkoutIDStr)

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		// paremeters
		boolean retFlg = false;
		//InstanceIDSeqTbl instanceIDSeqTbl = new InstanceIDSeqTbl();

		// start log
		logger.debug("begin");

		try
		{
			if (con != null && checkoutIDStr != null && checkoutIDStr.length() > 0)
			{
				// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
				String deleteSqlStr = CreateDeleteSQL(keyValue);
				// コメント
				//String deleteSqlStr = CreateDeleteSQL();

				// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
				if (Delete(con, deleteSqlStr, null))
				{
					retFlg = true;
				}
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutCheckoutIDException_MessageString) + checkoutIDStr, e);

			throw e;
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// 編集対象のデータをロックする
	/// </summary>
	/// <remarks></remarks>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="dataUIDStr">チェックアウト対象のデータUID</param>
	/// <returns>チェックアウトID</returns>
	public boolean Checkout(Connection con, String keyValue, String dataUIDStr)
	// コメント
	///// <summary>
	///// 編集対象のデータをロックする
	///// </summary>
	///// <remarks></remarks>
	///// <param name="dataUIDStr">チェックアウト対象のデータUID</param>
	///// <returns>チェックアウトID</returns>
	//public boolean Checkout( Connection con, Transaction transaction, String dataUIDStr )

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		// paremeters
		boolean retBool = false;

		// start log
		logger.debug("begin");

		try
		{
			if (dataUIDStr != null && dataUIDStr.length() > 0)
			{
				// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
				String insertSqlStr = CreateInsertSQL(keyValue, dataUIDStr, Configuration.GetInstance().GetUserAccountBean());
				// コメント
				//String insertSqlStr = CreateInsertSQL(dataUIDStr, Configuration.GetInstance().GetUserAccountBean());

				// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08

				retBool = Insert(con, insertSqlStr, null);
			}
		}
		catch (Exception e)
		{
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkoutException_MessageString);
			logger.fatal(message + dataUIDStr, e);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	//
	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// インサートSQL
	/// </summary>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="dataUIDStr">チェックアウト対象のデータUID</param>
	/// <param name="user">現在の操作ユーザのデータ</param>
	/// <returns></returns>
	private String CreateInsertSQL(String keyValue, String dataUIDStr, UserAccountBean user)
	// コメント
	///// <summary>
	///// インサートSQL
	///// </summary>
	///// <param name="dataUIDStr">チェックアウト対象のデータUID</param>
	///// <param name="user">現在の操作ユーザのデータ</param>
	///// <returns></returns>
	//private String CreateInsertSQL(String dataUIDStr, UserAccountBean user)

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		logger.debug("begin");

		// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
		SetInfoValue(keyValue, dataUIDStr, user);
		// コメント
		//SetInfoValue(dataUIDStr, user);

		// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08

		logger.debug("end");

		return this.GetInsertSQL();
	}

	//
	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="keyValue">KEY情報</param>
	/// <param name="dataUIDStr">チェックアウト対象のデータUID</param>
	/// <param name="user">現在の操作ユーザのデータ</param>
	private void SetInfoValue(String keyValue, String dataUIDStr, UserAccountBean user)
	// コメント
	///// <summary>
	///// カラム値設定
	///// </summary>
	///// <param name="bean"></param>
	//private void SetInfoValue(String dataUIDStr, UserAccountBean user)

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		logger.debug("begin");

		this.keys.clear();
		inList.clear();
		try
		{
			if (dataUIDStr != null && user != null)
			{
				// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
				this.AddColValue(KEYVALUE_COLUMN, keyValue);
				// コメント
				//this.AddColValue(KEYVALUE_COLUMN, RisReceiptNumberControlTbl.DEFAULT);

				// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
				this.AddColValue(RIS_ID_COLUMN, dataUIDStr);
				this.AddColValue(IPADDRESS_COLUMN, user.GetIPAddress());
				this.AddColSetValue(ENTRYTIME_COLUMN, SysDateTbl.SYSDATE_COLUMN);
			}
		}
		catch (Exception e)
		{
			logger.fatal(e);
		}

		logger.debug("end");
	}

	// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="keyValue">KEY情報</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String keyValue)
	// コメント
	///// <summary>
	///// DELETE用のSQL文を作成する
	///// </summary>
	///// <remarks></remarks>
	///// <param name="buiUIDStr">削除対象部位患者のUID</param>
	///// <returns>DELETE用のSQL文</returns>
	//private String CreateDeleteSQL()

	// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
	{
		logger.debug("begin");

		this.keys.clear();
		inList.clear();
		// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
		if (!"".equals(keyValue))
		{
			this.AddColValue(KEYVALUE_COLUMN, keyValue, true);
		}
		// コメント
		//this.AddColValue(KEYVALUE_COLUMN, RisReceiptNumberControlTbl.DEFAULT, true);

		// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
		this.AddColValue(IPADDRESS_COLUMN, Configuration.GetInstance().GetIPAddress(), true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}

	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <param name="dataUIDStr">SELECT対象のデータUID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateCheckDataSelectSQL( String checkoutID, String dataUIDStr )
	{
		logger.debug("begin");

		this.keys.clear();
		inList.clear();
		// 2011.03.29 Mod K.Shinohara Start A0007
		this.AddColValue(KEYVALUE_COLUMN, checkoutID, true);
		//this.AddColValue(RIS_ID_COLUMN, dataUIDStr, true);
		//this.AddColValue(KEYVALUE_COLUMN, checkoutID, true);
		//this.AddColValue(IPADDRESS_COLUMN, Configuration.GetInstance().GetIPAddress(), true);
		// 2011.03.29 Mod K.Shinohara End

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/*
	//
	/// <summary>
	/// 当該IPアドレスでロックしている全データのロックを解除する
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
	public void UncheckoutAll()
	{
		// paremeters
		boolean refBool = false;
		Connection con = null;
		Transaction transaction = null;
		InstanceIDSeqTbl instanceIDSeqTbl = new InstanceIDSeqTbl();

		// start log
		logger.debug("begin");

		try
		{
			// get connection
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();

			// begin transaction
			transaction = con.BeginTransaction();

			// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
			String deleteSqlStr = CreateDeleteSQL(String.Empty);
			// コメント
			//String deleteSqlStr = CreateDeleteSQL();

			// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
			if (deleteSqlStr != null && deleteSqlStr.length() > 0)
			{
				refBool = ForceDelete(con, transaction, deleteSqlStr);
			}

			// commit
			transaction.Commit();

			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUnCheckOutIP_MessageString);
			String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleOKUnCheckOutCount_MessageString);
			logger.info(message + Configuration.GetInstance().GetIPAddress());
			logger.info(message2 + refBool.toString());
		}
		catch (Exception e)
		{
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unCheckoutIPException_MessageString);
			logger.fatal(message + (Dns.GetHostEntry(Dns.GetHostName()).AddressList[0]).toString(), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					String message10 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message10, e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return;
	}
	*/
}
