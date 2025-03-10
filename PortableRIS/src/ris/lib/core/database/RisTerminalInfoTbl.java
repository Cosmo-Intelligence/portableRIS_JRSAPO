package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.TerminalInfoBean;
import ris.portable.util.DataTable;

/// <summary>
///
/// TerminalInfoテーブル管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.25	: 112478 / A.Kobayashi	: original
/// V2.04.001	: 2010.11.16	: 999999 / T.Nishikubo	: KANRO-R-3
/// V2.01.00    extends 2011.08.22    extends 999999 / DD.Chinh     extends NML-CAT3-035
///
/// </summary>
public class RisTerminalInfoTbl extends BaseTbl
{
	//テーブル
	public static final String TABLE_NAME = "TERMINALINFO";
	private static final String TABLE_CAPTION = "端末情報";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	//アイテム
	public static final String TERMINAL_ID_COLUMN       = "TERMINALID";
	public static final String TERMINAL_NAME_COLUMN     = "TERMINALNAME";
	public static final String TERMINAL_ADDRESS_COLUMN  = "TERMINALADDRESS";
	public static final String ENTRYDATE_COLUMN         = "ENTRYDATE";
	public static final String EXPLANATION_COLUMN       = "EXPLANATION";
	public static final String SHOWORDER_COLUMN         = "SHOWORDER";
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	public static final String DRESSING_ROOM_COLUMN		 = "DRESSING_ROOM";
	// 2010.11.16 Add T.Nishikubo End
	// 2011.08.22 Add DD.Chinh@MERX Start NML-CAT3-035
	public static final int TERMINAL_NAME_COLUMN_SIZE	= 64;		//TERMINALINFO.TERMINALNAMEのDBサイズ
	// 2011.08.22 Add DD.Chinh@MERX End

	public RisTerminalInfoTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
		logger.debug("begin");

		this.infoCaption = TABLE_CAPTION;
		this.tableNameStr = TableNameStr;

		logger.debug("end");

	}

	/*
	/// <summary>
	/// 端末情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="terminalID">取得対象の端末ID</param>
	/// <param name="terminalBean">端末情報格納先</param>
	/// <returns></returns>
	public TerminalInfoBean GetTerminalInfoData(Connection con, Transaction trans, String terminalID, TerminalInfoBean terminalBean)
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && terminalID != null )
		{
			Command cmd = CreateSelectSQL(TERMINAL_ID_COLUMN, terminalID);
			cmd.Connection  = con;
			cmd.Transaction = trans;
			dt = Select(cmd, false);
		}
		if( dt != null && dt.Rows.Count > 0 )
		{
			if (terminalBean != null)
			{

				if( dt.Rows[0][TERMINAL_ID_COLUMN] != System.DBNull.Value )
				{
					terminalBean.SetTerminalID(dt.Rows[0][TERMINAL_ID_COLUMN].toString());
				}
				if( dt.Rows[0][TERMINAL_NAME_COLUMN] != System.DBNull.Value )
				{
					terminalBean.SetTerminalName((String)(dt.Rows[0][TERMINAL_NAME_COLUMN]));
				}
				if( dt.Rows[0][TERMINAL_ADDRESS_COLUMN] != System.DBNull.Value )
				{
					terminalBean.SetTerminalAddress((String)(dt.Rows[0][TERMINAL_ADDRESS_COLUMN]));
				}
				if( dt.Rows[0][ENTRYDATE_COLUMN] != System.DBNull.Value )
				{
					terminalBean.SetEntryDate((Timestamp)(dt.Rows[0][ENTRYDATE_COLUMN]));
				}
				if( dt.Rows[0][EXPLANATION_COLUMN] != System.DBNull.Value )
				{
					terminalBean.SetExplanation((String)(dt.Rows[0][EXPLANATION_COLUMN]));
				}
				if( dt.Rows[0][SHOWORDER_COLUMN] != System.DBNull.Value )
				{
					terminalBean.SetShowOrder(dt.Rows[0][SHOWORDER_COLUMN].toString());
				}
				// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
				if (dt.Columns.Contains(DRESSING_ROOM_COLUMN) && dt.Rows[0][DRESSING_ROOM_COLUMN] != System.DBNull.Value)
				{
					terminalBean.SetKakuhoRoom((String)(dt.Rows[0][DRESSING_ROOM_COLUMN]));
				}
				// 2010.11.16 Add T.Nishikubo End
			}
		}

		// end log
		logger.debug("end");

		return terminalBean;
	}
	*/

	/// <summary>
	/// 端末情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="ipadrress">取得対象のIPAddress</param>
	/// <param name="terminalBean">端末情報格納先</param>
	/// <returns></returns>
	public TerminalInfoBean GetTerminalInfoDataByIPAdrress(Connection con, String ipadrress, TerminalInfoBean terminalBean) throws Exception
	{
		// parameters
		DataTable dt = null;

		// begin log
		logger.debug("begin");

		if( con != null && ipadrress != null )
		{
			ArrayList<Object> arglist = new ArrayList<Object>();
			String cmd = CreateSelectSQL(TERMINAL_ADDRESS_COLUMN, ipadrress, arglist);
			dt = Select(con, cmd, arglist);
		}
		if( dt != null && dt.getRowCount() > 0 )
		{
			if (terminalBean != null)
			{

				if(dt.getRows().get(0).get(TERMINAL_ID_COLUMN) != null)
				{
					terminalBean.SetTerminalID(dt.getRows().get(0).get(TERMINAL_ID_COLUMN).toString());
				}
				if(dt.getRows().get(0).get(TERMINAL_NAME_COLUMN) != null)
				{
					terminalBean.SetTerminalName(dt.getRows().get(0).get(TERMINAL_NAME_COLUMN).toString());
				}
				if(dt.getRows().get(0).get(TERMINAL_ADDRESS_COLUMN) != null)
				{
					terminalBean.SetTerminalAddress(dt.getRows().get(0).get(TERMINAL_ADDRESS_COLUMN).toString());
				}
				if(dt.getRows().get(0).get(ENTRYDATE_COLUMN) != null)
				{
					terminalBean.SetEntryDate((Timestamp)(dt.getRows().get(0).get(ENTRYDATE_COLUMN)));
				}
				if(dt.getRows().get(0).get(EXPLANATION_COLUMN) != null)
				{
					terminalBean.SetExplanation(dt.getRows().get(0).get(EXPLANATION_COLUMN).toString());
				}
				if(dt.getRows().get(0).get(SHOWORDER_COLUMN) != null)
				{
					terminalBean.SetShowOrder(dt.getRows().get(0).get(SHOWORDER_COLUMN).toString());
				}
				// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
				//if (dt.getRows().get(0).get(DRESSING_ROOM_COLUMN) != null)
				//{
				//	terminalBean.SetKakuhoRoom(dt.getRows().get(0).get(DRESSING_ROOM_COLUMN).toString());
				//}
				// 2010.11.16 Add T.Nishikubo End
			}
		}

		// end log
		logger.debug("end");

		return terminalBean;
	}

	/*
	/// <summary>
	/// 新規の端末ID、またはShowOrderを取得する(Max+1)
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="itemName">テーブルカラム名(TerminalIDまたはShowOrder)</param>
	/// <returns>新TerminalID</returns>
	public String GetNewlID(Connection con, Transaction trans, String itemName)
	{
		DataTable dt = null;
		String rstStr = "";

		logger.debug("begin");

		if( con != null && trans != null )
		{
			dt = Select( con, trans, CreateSelectNewIDSQL(itemName) );
		}

		if( dt != null && dt.Rows.Count > 0 )
		{
			rstStr = dt.Rows[0][itemName].toString();
			if(rstStr == "")
			{
				rstStr = "1";
			}
		}
		else
		{
			rstStr = "1";	//開始番号
		}

		logger.debug("end");

		return rstStr;
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String itemName, String paramStr, ArrayList<Object> arglist)
	{
		logger.debug("begin");

		String cmd = "";

		this.keys.clear();
		this.ClearOrderKey();

		this.AddOrderKeyAsc(SHOWORDER_COLUMN);

		//カラム
		if (paramStr.length() > 0)
		{
			this.AddColSetValue(itemName, "?", true, SignType.Equal);
			arglist.add(paramStr);
		}

		logger.debug("end");

		String colmunName =
			  TERMINAL_ID_COLUMN + ", "
			+ TERMINAL_NAME_COLUMN + ", "
			+ TERMINAL_ADDRESS_COLUMN + ", "
			+ ENTRYDATE_COLUMN + ", "
			+ EXPLANATION_COLUMN + ", "
			+ SHOWORDER_COLUMN;
		// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
		//関東労災による特注処理対応
		//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
		//{
		//	colmunName	+= ", " + DRESSING_ROOM_COLUMN;
		//}
		// 2010.11.16 Add T.Nishikubo End

		cmd = this.GetSelectColmunSQL(colmunName, TABLE_NAME);

		return cmd;
	}

	/*
	/// <summary>
	/// 発番用のSQL文を作成する
	/// </summary>
	/// <param name="itemName">テーブルカラム名(TerminalIDまたはShowOrder)</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectNewIDSQL(String itemName)
	{
		logger.debug("begin");

		StringBuilder sqlStrBuild = new StringBuilder("");

		sqlStrBuild.append("SELECT MAX("+itemName+")+1 "+itemName+" from " + tableNameStr );

		// end log
		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/*
	/// <summary>
	/// 指定端末情報のShowOrderを取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="terminalID"></param>
	/// <returns></returns>
	public String GetTerminalShowOrder(Connection con, Transaction trans, String terminalID)
	{
		DataTable dt = null;
		String rstStr = "";

		logger.debug("begin");

		if( con != null && trans != null )
		{
			Command cmd = CreateSelectSQL(TERMINAL_ID_COLUMN, terminalID);
			cmd.Connection  = con;
			cmd.Transaction = trans;
			dt = Select(cmd, false);
		}

		if( dt != null && dt.Rows.Count > 0 )
		{
			rstStr = dt.Rows[0][SHOWORDER_COLUMN].toString();
		}
		else
		{
			rstStr = "1";	//開始番号
		}

		logger.debug("end");

		return rstStr;

	}
	*/

	/*
	/// <summary>
	/// 端末情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="terminalBean">更新する端末データ</param>
	/// <returns></returns>
	public boolean UpdateTerminalInfoData(Connection con, Transaction trans, TerminalInfoBean terminalBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && terminalBean != null )
		{
			retFlg = Update( con, trans, CreateUpdateSQL(terminalBean) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// 端末情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patientBean">対象データ</param>
	/// <returns></returns>
	public boolean InsertTerminalInfoData(Connection con, Transaction trans, TerminalInfoBean terminalBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && terminalBean != null )
		{
			retFlg = Insert( con, trans, CreateInsertSQL(terminalBean) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="terminalBean">INSERT対象の端末データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL( TerminalInfoBean terminalBean )
	{
		logger.debug("begin");

		SetInfoValue(terminalBean, SQLType.Insert);

		logger.debug("end");

		return this.GetInsertSQL();
	}
	*/

	/*
	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="patient">UPDATE対象のデータ</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateSQL( TerminalInfoBean terminalBean )
	{
		logger.debug("begin");

		SetInfoValue(terminalBean, SQLType.Update);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="TerminalInfoBean">端末情報</param>
	/// <param name="sqlType">SQLタイプ</param>
	private void SetInfoValue(TerminalInfoBean bean, int sqlType)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(TERMINAL_ID_COLUMN, bean.GetTerminalID(), true);

		this.AddColValue(TERMINAL_NAME_COLUMN, bean.GetTerminalName());
		this.AddColValue(TERMINAL_ADDRESS_COLUMN, bean.GetTerminalAddress());
		this.AddColSetValue(ENTRYDATE_COLUMN, SysDateTbl.SYSDATE_COLUMN);
		this.AddColValue(EXPLANATION_COLUMN, bean.GetExplanation());
		this.AddColValue(SHOWORDER_COLUMN, bean.GetShowOrder());
		// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
		//関東労災による特注処理対応
		if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
		{
			this.AddColValue(DRESSING_ROOM_COLUMN, bean.GetKakuhoRoom());
		}
		// 2010.11.16 Add T.Nishikubo End

		logger.debug("end");
	}
	*/

	/*
	/// <summary>
	/// IPアドレスから端末IDを取得する
	/// </summary>
	/// <param name="ipAddress">IPAddress</param>
	/// <returns>TerminalID</returns>
	public int GetMyTerminalID(Connection con, Transaction trans, String ipAddress)
	{
		DataTable dt = null;
		int rstInt = -1;

		logger.debug("begin");

		if( con != null && trans != null && ipAddress != null)
		{
			Command cmd = CreateSelectSQL(TERMINAL_ADDRESS_COLUMN, ipAddress);
			cmd.Connection  = con;
			cmd.Transaction = trans;
			dt = Select(cmd, false);
		}

		if( dt != null && dt.Rows.Count > 0 )
		{
			rstInt = Integer.parseInt(dt.Rows[0][TERMINAL_ID_COLUMN].toString());
		}

		logger.debug("end");

		return rstInt;

	}
	*/
}
