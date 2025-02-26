package ris.lib.core.database;

import java.sql.Connection;

import ris.lib.core.Configuration;
import ris.lib.core.util.MessageParameter;
import ris.portable.util.DataTable;

/// <summary>
///
/// RisLogSequenceTblテーブル管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.18	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.05	: 999999 / H.Satou@MERX	: NML-1-X04
///
/// </summary>
public class RisLogSequenceTbl extends BaseTbl
{
	public RisLogSequenceTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
	}

	//
	/// <summary>
	/// SELECT
	/// </summary>
	/// <remarks></remarks>
	/// <returns>新しいUID</returns>
	public String GetNewUID( Connection con ) throws Exception
	{
		// parameters
		String newUID = null;
		DataTable dt = null;

		// start log
		logger.debug("begin");

		try
		{
			if( con != null )
			{
				dt = Select( con, CreateRemoteSelectSQL(), null );
				if( dt != null && dt.getRowCount() > 0 )
				{
					if( dt.getRows().get(0).get("NEWUID") != null )
					{
						newUID = dt.getRows().get(0).get("NEWUID").toString();
					}
				}
			}
		}
		catch( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			throw e;
		}

		// end log
		logger.debug("end");

		return newUID;
	}

	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateRemoteSelectSQL()
	{
		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		// start log
		logger.debug("begin");

		sqlStrBuild.append("SELECT TO_CHAR( " + Configuration.GetInstance().GetRisDbUserStr() + ".LOGSEQUENCE.NEXTVAL, 'FM00000000' ) as NEWUID from dual");

		// end log
		logger.debug("end");

		return sqlStrBuild.toString();
	}
	//

	/*
	//
	/// <summary>
	/// SELECT
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="sqlStr">実行するSQL文</param>
	/// <returns>SELECT結果のDataTable</returns>
	private DataTable Select( Connection con, Transaction trans, String sqlStr )
	{
		// parameters
		DataTable dt = null;
		DataAdapter adapter = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && sqlStr != null )
		{
			// sql log
			// 2011.08.05 Mod H.Satou@MERX Start NML-1-X04
			// ログレベルの変更(DEBUG→INFO)
			logger.info(sqlStr);
			// コメント
			//logger.debug(sqlStr);

			// 2011.08.05 Mod H.Satou@MERX End

			try
			{
				// create adapter
				adapter = new DataAdapter( sqlStr, con );
				adapter.SelectCommand.Transaction = trans;

				dt = new DataTable();
				adapter.Fill(dt);
			}
			catch( Exception e )
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );
				throw e;
			}
			finally
			{
				if( adapter != null )
				{
					adapter.Dispose();
				}
			}
		}

		// end log
		logger.debug("end");

		return dt;
	}
	//
	*/
}
