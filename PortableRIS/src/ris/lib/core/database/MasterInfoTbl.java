package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.portable.util.DataRow;
import ris.portable.util.DataTable;


/// <summary>
///
/// マスタテーブルの管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.22	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.05	: 999999 / H.Satou@MERX	: NML-1-X04
///
/// </summary>
public class MasterInfoTbl extends BaseTbl
{
	public  final String GROUPCODE_COLUMN		= "GROUPCODE";
	public  final String MAINGROUPNAME_COLUMN	= "MAINGROUPNAME";
	public  final String SUBGROUPNAME_COLUMN		= "SUBGROUPNAME";
    private  final String SHOWORDER				= "SHOWORDER";

	public MasterInfoTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
	}
	/*
	/// <summary>
	/// マスタデータをDBから収集する
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <returns></returns>
	public DataSet CollectRisMasterInfo(Connection con, Transaction trans)
	{
		DataSet ds = new DataSet("MasterTables");

		//// ORDER BY SHOWORDER ASCを行うテーブルのみ 配列[][2]に対して true をセットする
		//String [,] tables = {
		//					   {MasterUtil.RIS_BUIBUNRUIMASTER , "true"},
		//					   {MasterUtil.RIS_KENSATYPEMASTER , "true"},
		//};
		//配列[,0] extends データテーブルに設定するテーブル名
		//配列[,1] extends ＤＢ検索時に使用するテーブル名(データテーブルと同じ場合はnullを設定)
		//配列[,2] extends ORDER BY SHOWORDER ASCを行う場合は"true"を設定する
		//配列[,3] extends SHOWORDERの開始範囲(SHOWORDER≧配列[,2])
		//配列[,4] extends SHOWORDERの終了範囲(SHOWORDER≦配列[,3])
		String [,] tables = {
							   {MasterUtil.RIS_KENSATYPEMASTER                  , null, "true" , "", ""},//
							   {MasterUtil.RIS_BUIBUNRUIMASTER                  , null, "true" , "", ""},//
							   {MasterUtil.RIS_BUIMASTER                        , null, "true" , "", ""},//
							   {MasterUtil.RIS_SAYUUMASTER                      , null, "true" , "", ""},//
							   {MasterUtil.RIS_HOUKOUMASTER                     , null, "true" , "", ""},//
							   {MasterUtil.RIS_KENSAHOUHOUMASTER                , null, "true" , "", ""},//
							   {MasterUtil.RIS_EXAMROOMMASTER                   , null, "true" , "", ""},//
							   {MasterUtil.RIS_KENSAKIKIMASTER                  , null, "true" , "", ""},//
							   {MasterUtil.RIS_BYOUTOUMASTER                    , null, "true" , "", ""},//
							   {MasterUtil.RIS_BYOUSITUMASTER                   , null, "true" , "", ""},//
							   {MasterUtil.RIS_SECTIONMASTER                    , null, "true" , "", ""},//
								//2009.02.27   Start
							   {MasterUtil.RIS_CODECONVERT	                    , null, "" , "", ""},//
							   {MasterUtil.RIS_STATUSDEFINE                     , null, "true" , "", ""},//
								//2009.02.27   End
							   {MasterUtil.RIS_TERMINALINFO	                    , null, ""     , "", ""},//
							   {MasterUtil.RIS_HOSPITALMASTER                   , null, ""     , "", ""},//	2009.06.26
							   {MasterUtil.RIS_DEFAULTPRINTDEFINE               , null, ""     , "", ""},//
							   {MasterUtil.RIS_SUMMARYITEMMASTER                , null, "true" , "", ""},//
							   {MasterUtil.RIS_PREDEFINEDCOMMENTMASTER          , null, ""     , "", ""},//

							};

		for(int i=0; i<tables.length() / 5; i++)
		{
			this.tableNameStr = tables[i,0].toString();
			//検索時のテーブル名が指定されている場合は変更する
			if (tables[i,1] != null &&
				tables[i,1].length() > 0)
			{
				this.tableNameStr = tables[i,1].toString();
			}

			this.keys.clear();
			this.inList.clear();
			this.ClearOrderKey();

			try
			{
				//SHOWORDERの下限条件
				if (tables[i,3] != null &&
					tables[i,3].length() > 0)
				{
                    this.AddColValue(SHOWORDER, Integer.parseInt(tables[i, 3]), true, SignType.Under);
				}
				//SHOWORDERの上限条件
				if (tables[i,4] != null &&
					tables[i,4].length() > 0)
				{
                    this.AddColValue(SHOWORDER, Integer.parseInt(tables[i, 4]), true, SignType.Over);
				}
			}
			catch
			{
			}

			//ソート条件
			if (tables[i,2] == "true")
			{
                this.AddOrderKeyAsc(SHOWORDER);
			}

			String sql = GetSelectSQL();
			DataTable dt = Select(con, trans, sql);

			//検索時にテーブル名を変更している場合は戻す
			if (tables[i,1] != null &&
				tables[i,1].length() > 0)
			{
				dt.TableName = tables[i,0].toString();
			}

			ds.Merge(dt);
		}
		return ds;
	}
	*/

	/// <summary>
    /// マスタデータの取得
    /// </summary>
    /// <param name="con">コネクション</param>
    /// <param name="trans">トランザクション</param>
    /// <param name="tableNameStr">テーブル名</param>
    /// <param name="ascBool">ORDER BY SHOWORDER ASCの有無</param>
    /// <returns>マスタデータ</returns>
	public DataRow GetMasterDataTable(Connection con, String tableNameStr, String keyColumnStr, String keyData) throws Exception
    {
        // parameters
		DataRow dataRow = null;

        // begin log
        logger.debug("begin");

        if (con != null && tableNameStr != null && tableNameStr.length() > 0
			&& keyColumnStr != null && keyColumnStr.length() > 0 && keyData != null)
        {
            try
            {
				this.AddColValue(keyColumnStr, keyData, true, SignType.Equal);

				ArrayList<Object> arglist = new ArrayList<Object>();
				String cmd = CreateMasterSelectSQL(tableNameStr, keyColumnStr, keyData, arglist);
				// sql log
				logger.debug(cmd);

				DataTable dt = Select(con, cmd, arglist);

				if (dt != null && dt.getRowCount() > 0)
				{
					dataRow = dt.getRows().get(0);
				}
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        // end log
        logger.debug("end");

		return dataRow;

    }

	/// <summary>
	/// 他検査SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateMasterSelectSQL(String tableNameStr, String keyColumnStr, String keyData, ArrayList<Object> arglist)
	{
		String cmd = "";

		this.keys.clear();
		this.ClearOrderKey();
		this.inList.clear();

		this.AddColSetValue(keyColumnStr, "?", true, SignType.Equal);
		arglist.add(keyData);
		//Parameter oraParam = new Parameter(keyColumnStr, keyData);
		//cmd.Parameters.Add(oraParam);
		//cmd.Parameters.Add(keyColumnStr, .DataAccess.Client.DbType.Varchar2, keyData, ParameterDirection.Input);

		cmd = this.GetSelectColmunSQL(" * ", tableNameStr);

		return cmd;
	}

    /// <summary>
    /// マスタデータの取得
    /// </summary>
    /// <param name="con">コネクション</param>
    /// <param name="trans">トランザクション</param>
    /// <param name="tableNameStr">テーブル名</param>
    /// <param name="ascBool">ORDER BY SHOWORDER ASCの有無</param>
    /// <returns>マスタデータ</returns>
    public DataTable GetMasterDataTable(Connection con, String tableNameStr, boolean ascBool) throws Exception
    {
        // parameters
        DataTable dt = null;

        // begin log
        logger.debug("begin");

        if (con != null && tableNameStr != null && tableNameStr.length() > 0)
        {
            String sqlStr = "";

            this.keys.clear();
            this.inList.clear();
            this.tableNameStr = tableNameStr;

            if (ascBool)
            {
                this.AddOrderKeyAsc(SHOWORDER);
                sqlStr = this.GetSelectSQL();
            }
            else
            {
                sqlStr = this.GetSelectSQL();
            }

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
                dt = Select(con, sqlStr, null);
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        // end log
        logger.debug("end");

        return dt;
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
	public DataTable GetTelplateGroupMaster( Connection con, Transaction trans, String groupCode)
	{
		// parameters
		DataTable dt = null;
		DataAdapter adapter = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null)
		{
			String sqlStr = "";

			if (groupCode.length() > 0)
			{
				sqlStr = "SELECT * FROM TEMPLATEGROUPMASTER " + " WHERE GROUPCODE = '" + groupCode + "' ORDER BY GROUPCODE";
			}
			else
			{
				sqlStr = "SELECT * FROM TEMPLATEGROUPMASTER ORDER BY GROUPCODE";
			}

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
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
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

	/*
	/// <summary>
	/// SELECT
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="sqlStr">実行するSQL文</param>
	/// <returns>SELECT結果のDataTable</returns>
	public DataTable GetTelplateContents( Connection con, Transaction trans, String templateIDStr, String paramStr)
	{
		// parameters
		DataTable dt = null;
		DataAdapter adapter = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null)
		{
			String sqlStr = "";

			if (paramStr != null && paramStr.length() > 0)
			{
				sqlStr = "SELECT CONTENTS FROM TEMPLATECONTENTS WHERE GROUPCODE = " + templateIDStr
					+ " AND KENSATYPE_FILTER_FLAG LIKE '" + paramStr + "' ORDER BY COUNT";
			}
			else
			{
				sqlStr = "SELECT CONTENTS FROM TEMPLATECONTENTS WHERE GROUPCODE = " + templateIDStr
					+ " ORDER BY COUNT";
			}

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
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
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

	/*
	/// <summary>
	/// SELECT
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="tableName">テーブル名</param>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
	public DataTable GetTableDataTable(Connection con, Transaction trans, String tableName, String kanjaID, String risID)
	{
		// parameters
		DataTable retDt = null;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			String sqlStr = "";

			if (tableName.length() > 0)
			{
				if (kanjaID.length() > 0)
				{
					sqlStr = "SELECT * FROM " + tableName + " WHERE KANJA_ID = '" + kanjaID + "' ";
				}
				else if (risID.length() > 0)
				{
					sqlStr = "SELECT * FROM " + tableName + " WHERE RIS_ID = '" + risID + "' ";
				}

				retDt = Select(con, trans, sqlStr);
			}
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/*
	// 2010.07.30 Add T.Nishikubo Start
	/// <summary>
	/// マスタデータの取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="tableNameStr">テーブル名</param>
	/// <param name="keyColumnStr">キーカラム名</param>
	/// <param name="keyData">キーカラム値</param>
	/// <param name="sortCol">ソートカラム名</param>
	/// <returns>マスタデータ</returns>
	public DataTable GetMasterDataTable(Connection con, Transaction trans, String tableNameStr, String keyColumnStr, String keyData, String sortCol)
	{
		// parameters
		DataTable dt = null;
		DataAdapter adapter = null;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && !String.IsNullOrEmpty(tableNameStr) && !String.IsNullOrEmpty(keyColumnStr) && !String.IsNullOrEmpty(keyData))
		{
			String sqlStr = "";

			this.keys.clear();
			this.orderKeys.clear();
			this.tableNameStr = tableNameStr;
			this.AddColValue(keyColumnStr, keyData, true, SignType.Equal);

			if (!String.IsNullOrEmpty(sortCol))
			{
				this.AddOrderKeyAsc(sortCol);
				sqlStr = this.GetSelectSQL();
			}
			else
			{
				sqlStr = this.GetSelectSQL();
			}

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
				adapter = new DataAdapter(sqlStr, con);
				adapter.SelectCommand.Transaction = trans;
				dt = new DataTable();
				adapter.Fill(dt);
			}
			catch (Exception e)
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
				throw e;
			}
			finally
			{
				if (adapter != null)
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

	/*
	// 統計CSV出力用
	/// <summary>
	/// マスタデータの取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>マスタデータ</returns>
	public DataTable GetMasterDataTable(Connection con, Transaction trans, OutputCsvParameter param)
	{
		// parameters
		DataTable retDt = null;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null)
		{
			int tabNo = param.GetTabNo();

			// SQL作成

			String sql = "";

			if (tabNo.Equals(CommonString.STATISTICS_TAB_KENSATYPE))
			{
				// 検査種別
				sql = CreateKensaTypeSelectSQL();
			}
			else if (tabNo.Equals(CommonString.STATISTICS_TAB_HOUHOU))
			{
				// 方法別
				sql = CreateHouhouSelectSQL();
			}
			else if (tabNo.Equals(CommonString.STATISTICS_TAB_BUIBUNRUI))
			{
				// 部位分類
				sql = CreateBuiBunruiSelectSQL(param.GetKensaTypeId());
			}
			else if (tabNo.Equals(CommonString.STATISTICS_TAB_BUI))
			{
				// 部位別
				sql = CreateBuiSelectSQL();
			}
			else if (tabNo.Equals(CommonString.STATISTICS_TAB_ZOUEIZAI))
			{
				// 器材別
				sql = CreateZoueizaiSelectSQL(param.GetZoueizaiKbnId());
			}
			else if (tabNo.Equals(CommonString.STATISTICS_TAB_FILM))
			{
				// フィルム別
				sql = CreateFilmSelectSQL();
			}


			if (sql.length() > 0)
			{
				//SELECT
				retDt = Select(con, trans, sql, false);
			}

			// sql log
			logger.debug(sql);
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/*
	// SQL文生成

	// 検査種別
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateKensaTypeSelectSQL()
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");
		sqlStrBuild.append("  SELECT KENSATYPE_ID, KENSATYPE_NAME");
		sqlStrBuild.append("    FROM KENSATYPEMASTER");
		sqlStrBuild.append("   WHERE USEFLAG = '" + CommonString.USEFLAG_ON + "'");
		sqlStrBuild.append(" ORDER BY SHOWORDER");

		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/*
	// 方法別
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateHouhouSelectSQL()
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");
		sqlStrBuild.append("  SELECT khhm.KENSATYPE_ID, ktm.KENSATYPE_NAME, khhm.KENSAHOUHOU_ID, khhm.KENSAHOUHOU_NAME");
		sqlStrBuild.append("    FROM KENSATYPEMASTER ktm, KENSAHOUHOUMASTER khhm");
		sqlStrBuild.append("   WHERE khhm.KENSATYPE_ID = ktm.KENSATYPE_ID(+) ");
		sqlStrBuild.append("     AND khhm.USEFLAG = '" + CommonString.USEFLAG_ON + "'");
		sqlStrBuild.append("     AND ktm.USEFLAG = '"  + CommonString.USEFLAG_ON + "'");
		sqlStrBuild.append(" ORDER BY ktm.SHOWORDER, khhm.SHOWORDER");

		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/*
	// 部位分類別
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="kensaTypeId">SELECT対象の検査種別ID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateBuiBunruiSelectSQL(String kensaTypeId)
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");
		sqlStrBuild.append("  SELECT bbm.KENSATYPE_ID, ktm.KENSATYPE_NAME, bbm.BUIBUNRUI_ID, bbm.BUIBUNRUI_NAME");
		sqlStrBuild.append("    FROM KENSATYPEMASTER ktm, BUIBUNRUIMASTER bbm");
		sqlStrBuild.append("   WHERE bbm.KENSATYPE_ID = ktm.KENSATYPE_ID(+) ");
		sqlStrBuild.append("     AND ktm.USEFLAG = '" + CommonString.USEFLAG_ON + "'");
		sqlStrBuild.append("     AND bbm.USEFLAG = '" + CommonString.USEFLAG_ON + "' ");
		if (!String.IsNullOrEmpty(kensaTypeId))
		{
			sqlStrBuild.append("     AND bbm.KENSATYPE_ID = '" + kensaTypeId + "' ");
		}
		sqlStrBuild.append(" ORDER BY ktm.SHOWORDER, bbm.SHOWORDER");

		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/*
	// 部位別
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateBuiSelectSQL()
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");
		sqlStrBuild.append("  SELECT bm.KENSATYPE_ID, ktm.KENSATYPE_NAME, bm.BUI_ID, bm.BUI_NAME");
		sqlStrBuild.append("    FROM KENSATYPEMASTER ktm, BUIMASTER bm");
		sqlStrBuild.append("   WHERE bm.KENSATYPE_ID = ktm.KENSATYPE_ID(+) ");
		sqlStrBuild.append("     AND ktm.USEFLAG = '" + CommonString.USEFLAG_ON + "'");
		sqlStrBuild.append("     AND bm.USEFLAG = '"  + CommonString.USEFLAG_ON + "' ");
		sqlStrBuild.append(" ORDER BY ktm.SHOWORDER, bm.SHOWORDER");

		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/*
	// 器材別
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="ZoueizaiKbnId">SELECT対象の器材区分ID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateZoueizaiSelectSQL(String ZoueizaiKbnId)
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");
		sqlStrBuild.append("  SELECT pm.ZOUEIZAI_ID, pm.ZOUEIZAI_NAME, pum1.ZOUEIZAITANNI_NAME AS ZOUEIZAITANNI, pum2.ZOUEIZAITANNI_NAME AS ZOUEIZAIIJITANNI");
		sqlStrBuild.append("    FROM PARTSMASTER pm, PARTSUNITMASTER pum1, PARTSUNITMASTER pum2 ");
		sqlStrBuild.append("   WHERE pm.ZOUEIZAITANNI_ID    = pum1.ZOUEIZAITANNI_ID(+) ");
		sqlStrBuild.append("     AND pm.ZOUEIZAIIJITANNI_ID = pum2.ZOUEIZAITANNI_ID(+) ");
		sqlStrBuild.append("     AND pm.USEFLAG = '" + CommonString.USEFLAG_ON + "'");
		if (!String.IsNullOrEmpty(ZoueizaiKbnId))
		{
			sqlStrBuild.append("     AND pm.ZOUEIZAIKBN = '" + ZoueizaiKbnId + "' ");
		}
		sqlStrBuild.append(" ORDER BY pm.SHOWORDER");

		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/*
	// フィルム別
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateFilmSelectSQL()
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");
		sqlStrBuild.append("  SELECT FILM_ID, FILM_NAME ");
		sqlStrBuild.append("   FROM FILMMASTER ");
		sqlStrBuild.append("   WHERE USEFLAG = '" + CommonString.USEFLAG_ON + "'");
		sqlStrBuild.append(" ORDER BY SHOWORDER ");

		logger.debug("end");

		return sqlStrBuild.toString();
	}
	// 2010.07.30 Add T.Nishikubo End
	*/
}
