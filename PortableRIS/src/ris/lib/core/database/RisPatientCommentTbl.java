package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// 患者コメントクラス
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.29	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
public class RisPatientCommentTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PATIENTCOMMENTS";		//患者共通コメントのテーブル
	private static String TableNameStr		= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;
	private static String rrisTableNameStr	= TABLE_NAME;

	// カラム定義
	public static final String PATIENTID_COLUMN			= "KANJA_ID";		//患者ID
	public static final String PATIENTKENSATYPE_COLUMN		= "KENSATYPE_ID";	//検査種別ID
	public static final String PATIENTCOMMENT_COLUMN		= "PATIENTCOMMENT";	//患者コメント

	//
	private String SQL_TYPE_INSERT = "0";
	private String SQL_TYPE_UPDATE = "1";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisPatientCommentTbl()
	{
		this.tableNameStr = TableNameStr;
	}

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <param name="rrisFlg">治療DBフラグ</param>
	public RisPatientCommentTbl(boolean rrisFlg)
	{
		this.tableNameStr = rrisTableNameStr;
	}

	/*
	/// <summary>
	/// RRISの患者共通コメントを検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件(患者ID)</param>
	/// <returns>検索結果のDataTable</returns>
	public String SearchPatientComment( Connection con, Transaction trans, String patientId )
	{
		// parameters
		DataTable dt = null;
		String patientCommentStr = "";

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && patientId != null )
		{
			String sql = CreateSelectSQL(patientId);
			dt = Select( con, trans, sql );

			if (dt != null && dt.Rows.Count > 0)
			{
				DataRow row = dt.Rows[0];	//一行取得
				if (!row.IsNull(PATIENTCOMMENT_COLUMN))
				{
					patientCommentStr = ((String)row[PATIENTCOMMENT_COLUMN]);
				    patientCommentStr.Replace("\r\n", " ");
				}
			}
		}

		// end log
		logger.debug("end");

		return patientCommentStr;
	}
	*/

	/*
	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL( String patientIDStr )
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		//対象の患者IDを設定する
		if( patientIDStr != null )
		{
			this.AddColValue( PATIENTID_COLUMN, patientIDStr, true, SignType.Equal );
			this.AddColValue( PATIENTKENSATYPE_COLUMN, MasterUtil.RIS_PATIENTCOMMENT_DEF, true, SignType.Equal );
		}

		logger.debug("end");

		// 元のSQL文の途中に、OR句を追加する
		String tempSelectSQL = this.GetSelectColmunSQL(PATIENTCOMMENT_COLUMN, TABLE_NAME);

		return tempSelectSQL;
	}
	*/

	/// <summary>
	/// RRISの特定患者の患者共通コメントを全て取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="param">検索条件(患者ID)</param>
	/// <returns>検索結果のDataTable</returns>
	public ArrayList GetPatientCommentAll(Connection con, String patientId) throws Exception
	{
		// parameters
		ArrayList retList = new ArrayList();

		// begin log
		logger.debug("begin");

		if (con != null &&  patientId != null)
		{
			String sql = CreateSelectSQLAll(patientId);
			DataTable dt = Select(con, sql, null);
			for (int i=0; i<dt.getRowCount(); i++)
			{
				DataRow row = dt.getRows().get(i);
				retList.add(row);
			}
		}

		// end log
		logger.debug("end");

		return retList;
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQLAll(String patientIDStr)
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		this.AddColValue(PATIENTID_COLUMN, patientIDStr, true, SignType.Equal);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// 患者コメントを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patientID">患者ID</param>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="comment">コメント</param>
	/// <returns></returns>
	public boolean UpdatePatientComment(Connection con, String patientID, String kensatypeID, String comment) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null &&
			patientID != null && patientID.length() > 0 && kensatypeID != null && kensatypeID.length() > 0)
		{
			//更新を試みる
			retFlg = Update(con, CreateIntUpdSQL(patientID, kensatypeID, comment, SQL_TYPE_UPDATE), null);
			if (!retFlg)
			{
				//挿入を試みる
				retFlg = Insert(con, CreateIntUpdSQL(patientID, kensatypeID, comment, SQL_TYPE_INSERT), null);
			}
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="patientID">患者ID</param>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="comment">コメント</param>
	/// <param name="sqlType">SQLタイプ</param>
	/// <returns>判定</returns>
	private String CreateIntUpdSQL(String patientID, String kensatypeID, String comment, String sqlType)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();
		this.AddColValue(PATIENTID_COLUMN,			patientID,		true, SignType.Equal);
		this.AddColValue(PATIENTKENSATYPE_COLUMN,	kensatypeID,	true, SignType.Equal);
		//
		this.AddColValue(PATIENTCOMMENT_COLUMN, comment);

		logger.debug("end");

		if (sqlType == SQL_TYPE_INSERT)
		{
			return this.GetInsertSQL();
		}
		else
		{
			return this.GetUpdateSQL();
		}
	}

	/*
	/// <summary>
	///	帳票用-患者コメント情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintPatientCommentDataTable(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		DataTable retDt = new DataTable();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sql = CreateSelectSQL(param);

			if (sql.length() > 0)
			{
				//SELECT
				retDt = Select(con, trans, sql, false);
			}
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	//private String CreateSelectSQL(OrderSearchParameter param)
	//{
	//	logger.debug("begin");
	//
	//	// parameters
	//	StringBuilder sqlStrBuild = new StringBuilder("");
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//	sqlStrBuild.append(" SELECT /*+ INDEX(PI) INDEX(OM) INDEX(EO) INDEX(EM) */ PI.* FROM ");
	//	// 元の処理
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ PI.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT PI.* FROM ");			//
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " PI");
	//	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
	//	// 2011.06.24 Add K.Shinohara Start A0060
	//	sqlStrBuild.append(", EXMAINTABLE EM ");
	//	// 2011.06.24 Add K.Shinohara End
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
	//	sqlStrBuild.append(" PI.KANJA_ID = OM.KANJA_ID ");
	//	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
	//
	//	logger.debug("end");
	//
	//	return sqlStrBuild.toString();
	//}
}
