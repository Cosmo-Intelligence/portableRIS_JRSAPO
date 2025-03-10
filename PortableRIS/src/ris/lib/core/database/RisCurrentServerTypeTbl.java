package ris.lib.core.database;

import java.sql.Connection;

import ris.portable.util.DataTable;

/// <summary>
///
/// サーバータイプ情報の管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.25	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.05	: 999999 / H.Satou@MERX	: NML-1-X04
///
/// </summary>
public class RisCurrentServerTypeTbl extends BaseTbl
{
	// アクセステーブル名
	private String tableNameStr = "CURRENTSERVERTYPE";

	// カラム定義
	public static final String CURRENTTYPE_COLUMN             = "CURRENTTYPE";

	/// <summary>
	/// publicコンストラクタ
	/// </summary>
	/// <remarks></remarks>
	public RisCurrentServerTypeTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
	}

	/// <summary>
	/// システム設定情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>システム設定情報</returns>
	public DataTable GetCurrentServerType( Connection con ) throws Exception
	{
		// parameters
		DataTable dt = null;

		// start log
		logger.debug("begin");

		if( con != null )
		{
			dt = Select( con, CreateSelectSQL(), null );
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL()
	{
		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		// start log
		logger.debug("begin");

		// create sql
		sqlStrBuild.append("SELECT * from " + tableNameStr);

		// end log
		logger.debug("end");

		return sqlStrBuild.toString();
	}

	/*
	/// <summary>
	/// SELECT
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="sqlStr">実行するSQL文</param>
	/// <returns>SELECT結果のDataTable</returns>
	private DataTable Select( Connection con,  trans, String sqlStr )
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
	*/
}
