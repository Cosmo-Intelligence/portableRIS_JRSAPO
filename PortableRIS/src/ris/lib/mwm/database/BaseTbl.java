package ris.lib.mwm.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ris.lib.mwm.util.MwmMessageParameter;
import ris.lib.mwm.util.SqlUtil;
import ris.portable.common.Const;
import ris.portable.database.DataBaseCore;
import ris.portable.util.DataTable;

/// <summary>
/// BaseTbl の概要の説明です。
///
/// BaseTblには、テーブルへのDBアクセスメソッドを定義しています。
/// 各テーブルアクセスクラスへの基本クラスとなります。
///
/// ■提供する機能概要
/// DB操作の基本である（Insert, Update, Delete, Select)メソッドを提供します。
///
/// ■使用方法概要
/// 1. BaseTblを継承して、対象テーブルのクラスを作成します。
/// 2. 継承クラスに対象テーブル名、削除の方法（物理 or 論理)等を設定します。
/// 3. 要求される仕様におうじたDBアクセスメソッド（SQL取得関数）を記述します。
///
/// ■DBアクセスメソッド（SQL取得関数）記述方法概要
/// 1. keysメンバにフィールド情報（更新フィード名、値、検索条件等）を設定します。
/// 　 設定内容は、各メソッド(Insert, Update, Delete, Select)によって異なりますが、
///    共通の関数AddColValueを使用します。　
///    AddColValueは、文字列、数値、日付型に対応したインターフェースをもちます。
///    詳細は、各関数の定義を参照してください。
///    pKeyパラメータについて
///		AddColValueの第３引数　pKeyは、Where句対象フィールドかどうかを示し、各SQL取得関数それぞれで振舞いが異なります。
///		○各SQL取得関数でのpKey=true時の振る舞い
///			Insert: 影響しない
///			Update: Where句に使用する
///			Delete: Where句に使用する
/// 		Select: Where句に使用する
///
/// 2. 各SQL取得関数を実行します。
///
/// ■使用例（Insert, Update文取得関数の作成）
/// AttachFiledInfoTblを例とする
/// ①　フィールド情報の設定
///     本例では、フィールド情報設定関数SetInfoValue(Updateと共通を作成し、その中で設定している。
/// ②　SQL取得関数の実行
///
/// private String CreateInsertSQL(
///		AttachedFileInfoBean attached,
///		String newUID,
///		UserAccountBean user)
/// {
///		logger.debug("begin");
///		attached.SetAttachedFileUID(newUID);		//ユーザIDの取得、設定
///		SetInfoValue(attached, user, SQLType.Insert); //①　フィールド情報設定関数の実行
///		logger.debug("end");
///		return this.getInsertSQL();	//②　SQL取得関数の実行
/// }
///
///	private String CreateUpdateSQL(
///		AttachedFileInfoBean attached,
///		UserAccountBean user)
/// {
///		logger.debug("begin");
///		SetInfoValue(attached, user, SQLType.Update); //①　フィールド情報設定関数の実行
///		logger.debug("end");
///		return this.getUpdateSQL();	//②　SQL取得関数の実行
/// }
///
/// private void SetInfoValue(
///		AttachedFileInfoBean attached,
///		UserAccountBean user,
///		int sqlType)
/// {
///		logger.debug("begin");
///		this.keys.clear();
///
///     // Keysにフィールド情報を登録します。pKeyが設定されていますが、Insertには作用しません。
///     // Update時には、Where句に使用されます。
///		this.AddColValue(ATTACHED_FILE_UID, attached.GetAttachedFileUID(), true);　
///		・・・略・・・
///		this.AddColValue(FILE_TYPE, attached.GetFileType());
///		switch(sqlType)
///		{
///			//Insert文固有の定義（全フィールド情報分必要）
///			case SQLType.Insert:
///				//登録情報
///				this.AddColValue(ENTRY_DATE, Timestamp.Now);
///				this.AddColValue(ENTRY_USR_ID, user.GetUserID());
///				this.AddColValue(ENTRY_USR_NAME, user.GetUserName());
///				//更新情報（値は空）
///				this.AddColValue(UPD_DATE, Const.TIMESTAMP_MINVALUE);
///				this.AddColValue(UPD_USR_ID, "");
///				this.AddColValue(UPD_USR_NAME, "");
///				break;
///			//Update文固有の定義（必須情報のみでOK）
///			case SQLType.Update:
///				//更新情報
///				this.AddColValue(UPD_DATE, Timestamp.Now);
///				this.AddColValue(UPD_USR_ID, user.GetUserID());
///				this.AddColValue(UPD_USR_NAME, user.GetUserName());
///				break;
///		}
///		logger.debug("end");
/// }
///
/// ■使用例（Delete文取得関数の作成）
///  Deleteでは、対象レコードの削除方法が、物理削除か、論理削除かで異なります。
///  設定するフィールド情報は、検索条件（Where句情報）になります。
///
/// 1. 物理削除の場合
/// private String CreateDeleteSQL(
///		String attachedFileUID)
/// {
///		logger.debug("begin");
///		this.keys.clear();
///		this.AddColValue(ATTACHED_FILE_UID, attachedFileUID, true); //検索条件(Where句情報）を設定する
///		logger.debug("end");
///		return this.getDeleteSQL();			//Delete文を取得する
/// }
///
/// ２．論理削除の場合
/// ①　コンストラクタで、論理削除を使用することと、対象フィールドを設定します。
/// public RequestInfoTbl()
/// {
///		・・・略・・・
///		this.deleteType = DeleteType.Logic;	//論理削除を示すフラグ
///		this.DeleteFlagColumnName = DEL_FLG_COLUMN; //削除フラグを示すフィールド
///		・・・略・・・
///	}
///
///	②　その他は物理削除時と同様です。
///	　　論理削除ですので、実際に作成されるSQL文は、this.DeleteFlagColumnNameを更新するUpdate文になります。
///
///	■使用例（Select文取得関数の作成）
///  設定するフィールド情報は、検索条件（Where句情報）になります。
///  検索には、>=などの等号や、Likeが指定でき、AddColValueの第４引数で指定します。（省略は"="）
///  論理削除テーブルの場合は、有効なデータのみ検索対象となります。
///  ソート順序は、AddOrderKeyAsc(String name) もしくは、AddOrderKeyDesc(String name)を使用します。
///  ソートの優先順位は、先に定義した順になります。
///
///	private String CreateSelectSQL(
///	ReportSearchParameter param)
/// {
///		logger.debug("begin");
///		this.keys.clear();
///		this.AddColValue(KANJA_ID_COLUMN, param.GetPatientID(), true, SignType.Equal);
///		this.AddColValue(REPORT_KIND_COLUMN, param.GetReportType(), true, SignType.Equal);　
///		this.AddColValue(ENTRY_DATE_COLUMN, param.GetEntrylDateSearchStart(), true, SignType.Under); //期間指定
///		this.AddColValue(ENTRY_DATE_COLUMN, param.GetEntryDateSearchEnd(), true, SignType.Over);//期間指定
///		this.ClearOrderKey();
///		this.AddOrderKeyAsc(KANJA_ID_COLUMN) //第一キー昇順
///		this.AddOrderKeyDesc(ENTRY_DATE_COLUMN) //第二キー降順
///		logger.debug("end");
///		return this.getSelectSQL();
/// }
///
///	■使用例（in, is Null検索の設定方法）
///	AddColValue関数のパラメータを以下のようにしてください
///
///		this.AddColValue(KANJA_ID_COLUMN, param.GetPatientID(), true, SignType.In);　// in
///		this.AddColValue(KANJA_ID_COLUMN, "", true, SignType.isNull);  // is Null
///
///
/// Copyright (C) 2008, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2008.02.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class BaseTbl
{
	/// <summary>
	/// テーブル名称
	/// </summary>
	public String tableNameStr = "";

	/// <summary>
	/// Exception発生時に表示するクラス固有のメッセージ
	/// </summary>
	public String infoCaption = "";

	/// <summary>
	/// フィールド情報を埋め込むリスト
	/// </summary>
	public ArrayList keys = new ArrayList();

	/// <summary>
	/// ソート情報を埋め込むリスト
	/// </summary>
	public ArrayList orderKeys = new ArrayList();

	private class InInfo
	{
		public String fieldName = "";
		public String inString = "";
		public String orString = ""; //in1000件エラー対応
		public boolean   isNull = false;	//2008.03.26 AddBy E.Yoshimi	in句 or fieldName is Null ※主として検査室・検査機器未割り当て検索に使用する
	}

	public class InList
	{
		private ArrayList inKeys = new ArrayList();


		public Integer getCount()
		{
			return inKeys.size();
		}

		public void clear()
		{
			this.inKeys.clear();
		}

		public void AddInKey(String fieldName, String fieldVal)
		{
			InInfo info = SearchInInfo(fieldName);
			if(0 < info.inString.length()) info.inString += ", ";
			info.inString += fieldVal;

			if(0 < info.orString.length()) info.orString += " or ";
			info.orString += String.format("%s = %s", fieldName, fieldVal);
		}

		private InInfo SearchInInfo(String fieldName)
		{
			InInfo info = null;
			for(int i=0; i<this.inKeys.size(); i++)
			{
				InInfo tempInfo = (InInfo)this.inKeys.get(i);
				if(tempInfo.fieldName.equals(fieldName))
				{
					info = tempInfo;
					break;
				}
			}
			if(null == info)
			{
				info = new InInfo();
				info.fieldName = fieldName;
				this.inKeys.add(info);
			}
			return info;
		}

		public String GetInString(Integer i)
		{
			String ret = "";
			InInfo info = (InInfo)this.inKeys.get(i);
			ret = info.fieldName + " in (" + info.inString + ") ";

			//2008.03.26  AddBy E.Yoshimi Start
			if (info.isNull)
			{
				ret += " or " + info.fieldName + " is Null ";
			}
			//2008.03.26  AddBy E.Yoshimi End

			//ただSQL文が長くなりすぎるとエラーになる可能性も有り
			if (info.inString.split(",").length >= 1000)
			{
				ret = String.format("(%s)", info.orString);
			}

			return ret;
		}
	}

	/// <summary>
	/// in文字列を保存するリスト
	/// </summary>
	public InList inList = new InList();

	/// <summary>
	/// 削除タイプ
	/// </summary>
	private int deleteType = DeleteType.Physic;

	/// <summary>
	/// 論理削除用、削除フラグ担うカラム名
	/// </summary>
	private String DeleteFlagColumnName = "";

	/// <summary>
	/// 実行するSQLのタイプリストクラス
	/// Update_DECは、リビジョンアップを表す。
	/// </summary>
	protected class SQLType
	{
		public static final int Insert = 10;
		public static final int Update = 20;
		public static final int Select = 30;
		public static final int Delete = 40;
	}

	/// <summary>
	/// 削除タイプリストクラス
	/// Physic レコード削除
	/// Logic 論理削除（削除フラグを立てる）
	/// </summary>
	protected class DeleteType
	{
		public static final int Physic = 10;
		public static final int Logic = 20;
	}

	/// <summary>
	/// 論理削除フラグリスト
	/// 0: 有効
	/// 1: 削除
	/// </summary>
	protected class LogicDeleteVal
	{
		public static final String Alive = "0";
		public static final String Deleted = "1";
	}

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public BaseTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（文字列）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	private void SetColumnInfo( String key, String val, boolean pKey, String sign )
	{
		ColumnInfo info = new ColumnInfo();
		info.fieldName = key;
		info.fieldVal = val;
		info.pKey = pKey;
		info.sign = sign;
		keys.add(info);
	}

	//2008.03.26  AddBy E.Yoshimi Start
	/// <summary>
	/// (in句 or FieldName is Null)を生成する
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	/// <param name="pIsNull">True:in句の後に続ける</param>
	private void SetColumnInfo( String key, String val, boolean pKey, String sign, boolean pIsNull )
	{
		ColumnInfo info = new ColumnInfo();
		info.fieldName = key;
		info.fieldVal = val;
		info.pKey = pKey;
		info.sign = sign;
		info.pIsNull = pIsNull;
		keys.add(info);
	}
	//2008.03.26  AddBy E.Yoshimi End


	/// <summary>
	/// テーブル名を返す
	/// </summary>
	/// <returns></returns>
	public String GetTableName()
	{
		return this.tableNameStr;
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（文字列）
	/// </summary>
	/// <param name="val"></param>
	protected void AddColValue( String key, String val )
	{
		AddColValue(key, val, false);
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（文字列）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	protected void AddColValue( String key, String val, boolean pKey )
	{
		AddColValue(key, val, pKey, SignType.Equal);
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（文字列）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	protected void AddColValue( String key, String val, boolean pKey, String  sign )
	{
		if(val == null)
		{
			this.SetColumnInfo( key, "NULL", pKey, sign);
		}
		else if(0 != val.length())
		{
			if ( !SignType.Like.equals(sign))
				this.SetColumnInfo( key, "'" + SqlUtil.EscapeInsertSQL(val) + "'", pKey, sign );
			else
				this.SetColumnInfo( key, SqlUtil.EscapeInsertSQL(val), pKey, sign );
		}
		else
		{
			this.SetColumnInfo( key, "''", pKey, sign);
		}
	}

	//2008.03.26  AddBy E.Yoshimi Start
	/// <summary>
	/// tempSQLにSQL文を追加する（in句 or FieldName is Null）節の生成(検索条件「未割り当ても含む」に相当)
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	protected void AddColValue( String key, String val, boolean pKey, String  sign, boolean pIsNull )
	{
		if(val == null)
		{
			this.SetColumnInfo( key, "NULL", pKey, sign);
		}
		else
		{
			this.SetColumnInfo( key,   "'" + SqlUtil.EscapeInsertSQL(val) + "'", pKey, sign , pIsNull);
		}
	}
	//2008.03.26  AddBy E.Yoshimi End


	/// <summary>
	/// tempSQLにSQL文を追加する（文字列）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	protected void AddColSetValue( String key, String val )
	{
		this.SetColumnInfo( key, val, false, SignType.Equal);
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（数値）
	/// </summary>
	/// <param name="val"></param>
	protected void AddColValue( String key, Integer val )
	{
		AddColValue(key, val, false);
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（数値）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	protected void AddColValue( String key, Integer val, boolean pKey )
	{
		if (!Const.INT_MINVALUE.equals(val) )
		{
			this.SetColumnInfo( key, SqlUtil.EscapeInsertSQL(val.toString()), pKey, SignType.Equal);
		}
		else
		{
			this.SetColumnInfo( key, "''", pKey, SignType.Equal);
		}
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（数値）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	protected void AddColValue( String key, Integer val, boolean pKey, String sign )
	{
		this.SetColumnInfo( key, SqlUtil.EscapeInsertSQL(val.toString()), pKey, sign);
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（数値(decimal)）
	/// </summary>
	/// <param name="val"></param>
	protected void AddColValue( String key, BigDecimal val )
	{
		AddColValue(key, val, false);
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（数値(decimal)）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	protected void AddColValue( String key, BigDecimal val, boolean pKey )
	{
		if (val != null && Const.DECIMAL_MINVALUE.compareTo(val) != 0) {
			this.SetColumnInfo( key, SqlUtil.EscapeInsertSQL(val.toString()), pKey, SignType.Equal);
		} else {
			this.SetColumnInfo( key, "''", pKey, SignType.Equal);
		}
	}

	/// <summary>
	/// tempSQLにSQL文を追加する（数値(decimal)）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	protected void AddColValue( String key, BigDecimal val, boolean pKey, String sign )
	{
		if (val != null && Const.DECIMAL_MINVALUE.compareTo(val) != 0) {
		    this.SetColumnInfo( key, SqlUtil.EscapeInsertSQL(val.toString()), pKey, sign);
		}
	}

	/// <summary>
	/// tempSQLにSQLを追加する（日時）
	/// </summary>
	/// <param name="val"></param>
	protected void AddColValue( String key, Timestamp val )
	{
		AddColValue(key, val, false);
	}

	/// <summary>
	/// tempSQLにSQLを追加する（日時）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	protected void AddColValue( String key, Timestamp val, boolean pKey )
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(val) ) {
			this.SetColumnInfo(key, "TO_DATE('"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(val)
					+ "','YYYY-MM-DD HH24:MI:SS')", pKey, SignType.Equal);
		} else {
			this.SetColumnInfo(key, "''", pKey, SignType.Equal);
		}
	}

	/// <summary>
	/// tempSQLにSQLを追加する（日時）
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	/// <param name="pKey"></param>
	/// <param name="sign"></param>
	protected void AddColValue( String key, Timestamp val, boolean pKey, String sign )
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(val) ) {
			if (SignType.Over.equals(sign))
				this.SetColumnInfo(key, "TO_DATE('"
						+ new SimpleDateFormat("yyyy-MM-dd").format(val)
						+ " 23:59:59', 'YYYY-MM-DD HH24:MI:SS')", pKey, sign);
			else
				this.SetColumnInfo(key, "TO_DATE('"
						+ new SimpleDateFormat("yyyy-MM-dd").format(val)
						+ " 00:00:00', 'YYYY-MM-DD HH24:MI:SS')", pKey, sign);
		}
	}


	/// <summary>
	/// Insert文の接頭語を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateInsertHead()
	{
		return CreateInsertHead(this.tableNameStr);
	}

	protected String CreateInsertHead( String tableName )
	{
		String headStr = "insert into " + tableName + " ( ";

		for(int i=0; i<this.keys.size(); i++)
		{
			ColumnInfo info = (ColumnInfo)keys.get(i);
			if(0 < i) headStr += ", ";
			headStr += info.fieldName;
		}
		headStr += " ) values (";

		return headStr;
	}

	/// <summary>
	/// Insert文の末尾を作成する
	/// </summary>
	/// <returns></returns>
	private String CreateInsertTail()
	{
		return ")";
	}

	/// <summary>
	///　インサート文フィールド情報部作成
	/// </summary>
	/// <returns></returns>
	private String CreateInsertFieldsVal()
	{
		String cols = "";
		for(int i=0; i<this.keys.size(); i++)
		{
			ColumnInfo info = (ColumnInfo)keys.get(i);
			if(0 < cols.length()) cols += ", ";
			cols += info.fieldVal;
		}

		return cols;
	}

	/// <summary>
	/// インサート文作成
	/// </summary>
	/// <returns></returns>
	protected String GetInsertSQL()
	{
		return GetInsertSQL(this.tableNameStr);
	}

	protected String GetInsertSQL( String tableName )
	{
		return this.CreateInsertHead(tableName) + this.CreateInsertFieldsVal() + this.CreateInsertTail();
	}

	/// <summary>
	/// Update文の接頭語を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateUpdateHead()
	{
		return this.CreateUpdateHead(this.tableNameStr);
	}

	/// <summary>
	/// Update文の接頭語を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateUpdateHead( String tableName )
	{
		return "update " + tableName + " set ";
	}

	/// <summary>
	/// Update文の末尾を作成する
	/// </summary>
	/// <returns></returns>
	private String CreateUpdateTail()
	{
		return CreateWhere(false);
	}

	/// <summary>
	/// アップデート文フィールド情報部作成
	/// </summary>
	/// <returns></returns>
	private String CreateUpdateFieldsVal()
	{
		String cols = "";
		for(int i=0; i<this.keys.size(); i++)
		{
			ColumnInfo info = (ColumnInfo)keys.get(i);
			if(!info.pKey)
			{
				if(0 < cols.length()) cols += ", ";
				cols += info.fieldName;
				cols += " = ";
				cols += info.fieldVal;
			}
		}

		return cols;
	}

	/// <summary>
	/// アップデート文作成
	/// </summary>
	/// <returns></returns>
	protected String GetUpdateSQL()
	{
		return this.CreateUpdateHead(this.tableNameStr) + this.CreateUpdateFieldsVal() + this.CreateUpdateTail();
	}

	/// <summary>
	/// アップデート文作成
	/// </summary>
	/// <returns></returns>
	protected String GetUpdateSQL( String tableName )
	{
		return this.CreateUpdateHead(tableName) + this.CreateUpdateFieldsVal() + this.CreateUpdateTail();
	}

	/// <summary>
	/// デリート文作成
	/// </summary>
	/// <returns></returns>
	protected String GetDeleteSQL()
	{
		return this.CreateDeleteHead() + this.CreateDeleteTail();
	}

	/// <summary>
	/// デリート文作成(テーブル指定）
	/// </summary>
	/// <returns></returns>
	protected String GetDeleteSQL(String tableName)
	{
		return this.CreateDeleteHead(tableName) + this.CreateDeleteTail();
	}

	/// <summary>
	/// Delete文の接頭語を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateDeleteHead()
	{
		return CreateDeleteHead(tableNameStr);
	}

	protected String CreateDeleteHead( String tableName )
	{
		String ret = "";
		if(this.deleteType == DeleteType.Physic)
		{
			ret =  "delete " + CreateFrom(tableName) + " ";
		}
		else
		{
			ret =  "update " + tableName + " set " + this.DeleteFlagColumnName + "='" + LogicDeleteVal.Deleted + "'";
		}
		return ret;
	}

	protected String CreateFrom( String tableName )
	{
		return "from " + tableName;
	}
	/// <summary>
	/// Delete文の末尾を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateDeleteTail()
	{
		return CreateWhere(false);
	}

	/// <summary>
	/// セレクト文作成
	/// </summary>
	/// <returns></returns>
	protected String GetSelectSQL()
	{
		return this.CreateSelectHead() + this.CreateSelectTail();
	}

	/// <summary>
	/// セレクト文作成
	/// </summary>
	/// <returns></returns>
	protected String GetSelectSQL( String tableName )
	{
		return this.CreateSelectHead(tableName) + this.CreateSelectTail();
	}

	/// <summary>
	/// セレクト文作成
	/// </summary>
	/// <returns></returns>
	protected String GetSelectColmunSQL( String colmun, String tableName )
	{
		return this.CreateSelectHead(colmun, tableName) + this.CreateSelectTail();
	}

//		/// <summary>
//		/// セレクト文作成
//		/// </summary>
//		/// <returns></returns>
//		protected String GetUnDateSelectColmunSQL( String colmun, String tableName, OrderSearchParameter param )
//		{
//			String selectStr = "";
//			String selectHeadStr = this.CreateSelectHead(colmun, tableName);
//			String selectWhereStr = this.CreateWhere(true);
//
//			if (selectWhereStr.Trim().length() <= 0)
//			{
//				selectStr = selectHeadStr + selectWhereStr + " Where ";
//			}
//			else
//			{
//				selectStr = selectHeadStr + selectWhereStr + " and ";
//			}
//
//			selectStr = selectStr + "(" + RisSummaryView.KENSA_DATE_COLUMN + " = '20991231' or "
//				+ "(" + RisSummaryView.KENSA_DATE_COLUMN + " >= '" + param.GetPlanExamStartDate().Date.toString("yyyyMMdd") + "' and "
//				+       RisSummaryView.KENSA_DATE_COLUMN + " <= '" + param.GetPlanExamEndDate().Date.toString("yyyyMMdd") + "' ))"
//				+ CreateSelectOrderBy();
//
//			return selectStr;
//		}

//		/// <summary>
//		/// セレクト文作成
//		/// </summary>
//		/// <returns></returns>
//		protected String GetOrderSelectColmunSQL( String colmun, String tableName, OrderSearchParameter param )
//		{
//			String selectStr = "";
//			String selectHeadStr = this.CreateSelectHead(colmun, tableName);
//			String selectWhereStr = this.CreateWhere(true);
//			String selectStatusesWhereStr = "";
//			String selectResultStatusesWhereStr = "";
//
//			//ステータス
//			String[] statuses = param.GetRrisStatus().Split(',');
//
//			if (statuses.length() > 0 && statuses[0] != "")
//			{
//				selectStatusesWhereStr = selectWhereStr + " and STATUS : (";
//
//				for(int i = 0; i < statuses.length(); i++)
//				{
//					if (i == 0)
//					{
//						selectStatusesWhereStr = selectStatusesWhereStr + "'" + statuses[i] + "'";
//					}
//					else
//					{
//						selectStatusesWhereStr = selectStatusesWhereStr + ",'" + statuses[i] + "'";
//					}
//				}
//
//				selectStatusesWhereStr = selectStatusesWhereStr + ")";
//			}
//
//			//実績ステータス
//			String[] resultStatuses = param.GetRrisResultStatus().Split(',');
//
//			if (resultStatuses.length() > 0 && resultStatuses[0] != "")
//			{
//				if (selectStatusesWhereStr.IndexOf(" where ") >= 0)
//				{
//					selectResultStatusesWhereStr = selectWhereStr.Replace(" where ", " or ") + " and RESULTSTATUS : (";
//				}
//				else
//				{
//					selectResultStatusesWhereStr = selectWhereStr + " and RESULTSTATUS : (";
//				}
//
//				for(int i=0; i<resultStatuses.length(); i++)
//				{
//					if (i == 0)
//					{
//						selectResultStatusesWhereStr = selectResultStatusesWhereStr + "'" + resultStatuses[i] + "'";
//					}
//					else
//					{
//						selectResultStatusesWhereStr =selectResultStatusesWhereStr +  ",'" + resultStatuses[i] + "'";
//					}
//				}
//
//				selectResultStatusesWhereStr = selectResultStatusesWhereStr + ")";
//			}
//
//			if (selectStatusesWhereStr == "" && selectResultStatusesWhereStr == "")
//			{
//				selectStr = selectHeadStr + selectWhereStr;
//			}
//			else
//			{
//				selectStr = selectHeadStr + selectStatusesWhereStr + selectResultStatusesWhereStr;
//			}
//
//			return selectStr;
//		}

	/// <summary>
	/// count(*)用セレクト文の作成
	/// </summary>
	/// <returns></returns>
	protected String GetSelectCountSQL(String columnName)
	{
		return this.CreateSelectCountHead(columnName) + this.CreateSelectTail();
	}

	/// <summary>
	/// count(*)用セレクト文の作成
	/// </summary>
	/// <returns></returns>
	protected String GetSelectCountSQL( String columnName, String tableName )
	{
		return this.CreateSelectCountHead(columnName, tableName) + this.CreateSelectTail();
	}

	/// <summary>
	/// Select文の接頭語を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateSelectHead()
	{
		return CreateSelectHead(tableNameStr);
	}

	/// <summary>
	/// Select文を作成する
	/// </summary>
	/// <param name="tableName">検索テーブル</param>
	/// <returns></returns>
	protected String CreateSelectHead( String tableName )
	{
		return "select * " + CreateFrom(tableName) + " ";
	}

	/// <summary>
	/// Select文を作成する
	/// </summary>
	/// <param name="tableName">検索テーブル</param>
	/// <param name="colmunName">指定カラム</param>
	/// <returns>結果</returns>
	protected String CreateSelectHead( String colmunName, String tableName )
	{
		return "select " + colmunName + " " + CreateFrom(tableName) + " ";
	}

	/// <summary>
	/// Select Count(*)文の接頭語を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateSelectCountHead( String columnName )
	{
		return CreateSelectCountHead(columnName, tableNameStr);
	}

	/// <summary>
	///	 count文
	///	</summary>
	/// <param name="columnName">必須カラム</param>
	/// <param name="tableName">対象テーブル</param>
	/// <returns>sql文</returns>
	protected String CreateSelectCountHead( String columnName, String tableName )
	{
		return "select count(" + columnName + ") " + CreateFrom(tableName) + " ";
	}

	/// <summary>
	/// Select文の末尾を作成する
	/// </summary>
	/// <returns></returns>
	protected String CreateSelectTail()
	{
		return CreateWhere(true) + CreateSelectOrderBy();
	}

	/// <summary>
	/// Where文を作成する
	/// </summary>
	/// <param name="addNotTestKanjaSql">テスト患者を除くＳＱＬを追加するかどうか(True:追加する False:追加しない)</param>
	/// <returns></returns>
	protected String CreateWhere(boolean addNotTestKanjaSql)
	{
		String cols = "";
		//削除タイプにより変更
		if((this.deleteType == DeleteType.Logic)&&(0 < this.DeleteFlagColumnName.length()))
		{
			cols += " where " + this.DeleteFlagColumnName + "='" + LogicDeleteVal.Alive + "'";
		}
		for(int i=0; i<this.keys.size(); i++)
		{
			ColumnInfo info = (ColumnInfo)keys.get(i);
			if(info.pKey)
			{

				if(SignType.Like.equals(info.sign))
				{
					if("''".equals(info.fieldVal)) continue;

					if((null != cols) && (0 < cols.length())) cols += " and ";
					else cols += " where ";
					//Like比較
					cols += info.fieldName + " " + info.sign + " '" + SqlUtil.EscapeSelectSQL(info.fieldVal) + "'";
				}
				else if(SignType.In.equals(info.sign))
				{
					if("''".equals(info.fieldVal)) continue;

					this.inList.AddInKey(info.fieldName, info.fieldVal);
				}
				else if(SignType.isNull.equals(info.sign))
				{
					if((null != cols) && (0 < cols.length())) cols += " and ";
					else cols += " where ";
					//is NULL
					cols += info.fieldName + " is NULL";
				}
				else if(SignType.isNotNull.equals(info.sign))
				{
					if((null != cols) && (0 < cols.length())) cols += " and ";
					else cols += " where ";
					//is NULL
					cols += info.fieldName + " is not NULL";
				}
				else
				{
					if("''".equals(info.fieldVal)) continue;

					if((null != cols) && (0 < cols.length())) cols += " and ";
					else cols += " where ";

					//それ以外（=, <=, >=)
					//cols += info.fieldName + " " + info.sign + " " + info.fieldVal;

					//2008.03.26  AddBy E.Yoshimi Start	or結合がTrueだったら追記する
					if (info.pIsNull)
					{
						cols = cols + "(" + info.fieldName + " " + info.sign + " " + info.fieldVal + " or " + info.fieldName + " is NULL ) ";
					}
					else
					{
						cols += info.fieldName + " " + info.sign + " " + info.fieldVal;
					}
					//2008.03.26  AddBy E.Yoshimi End

				}
			}
		}

		//in文を加えた状態で取得
		cols = CreateInTerm(cols);

		return cols;
	}

	public String CreateInTerm( String cols )
	{
		for(int i=0; i<this.inList.getCount(); i++)
		{
			if((null != cols) && (0 < cols.length())) cols += " and ";
			else cols += " where ";

			//2008.03.26  EditBy E.Yoshimi Start	//in句の前後に()をつける
			cols += "(" + inList.GetInString(i) + ")";
			//cols += inList.GetInString(i);
			//2008.03.26  EditBy E.Yoshimi End
		}

		return cols;
	}

	/// <summary>
	/// OrderBy句作成
	/// </summary>
	/// <returns></returns>
	protected String CreateSelectOrderBy()
	{
		String ret = "";

		for(int i=0; i<orderKeys.size(); i++)
		{
			OrderInfo info = (OrderInfo)orderKeys.get(i);
			if(0 == ret.length())
			{
				ret += " Order By ";
			}
			else
			{
				ret += ", ";
			}
			ret += info.key + " " + info.type;
		}

		return ret;
	}

	/// <summary>
	/// SELECT
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="sqlStr">実行するSQL文</param>
	/// <param name="maxRecordLimitFlg">最大表示件数を制限するか否かのフラグ（true：制限あり　false：制限なし）</param>
	/// <returns>SELECT結果のDataTable</returns>
	protected DataTable Select( Connection con, String sqlStr, ArrayList<Object> arglist, ArrayList logList ) throws Exception
	{
		// parameters
		DataTable dt = null;
		Object[] args = new Object[0];

		// begin log
		//logger.debug("begin");

		if( con != null && sqlStr != null )
		{
			// logに出す文字列を作成
			String logStr = sqlStr;

			try {
				if (arglist != null && arglist.size() > 0) {
					args = new Object[arglist.size()];
					arglist.toArray(args);
				}
				dt = DataBaseCore.executeQuery(sqlStr, args, con);
			} catch (Exception e) {
				logList.add(MwmMessageParameter.dbInsertError_MessageString + this.infoCaption + e.toString());
				throw e;
			} finally {
			}
		}

		// end log
		//logger.debug("end");

		return dt;
	}

//		/// <summary>
//		/// SELECT
//		/// </summary>
//		/// <remarks></remarks>
//		/// <param name="con">データベース接続オブジェクト</param>
//		/// <param name="sqlStr">実行するSQL文</param>
//		/// <param name="arrDbParameters">OleDbParameterの配列</param>
//		/// <returns>SELECT結果のDataTable</returns>
//		protected ArrayList CallProcedure( OleDbConnection con, OleDbTransaction trans, String sqlStr, ArrayList arrDbParameters )
//		{
//			// parameters
//			ArrayList retArrList = new ArrayList();
//			OleDbCommand cmd = null;
//
//			// begin log
//			//logger.debug("begin");
//
//			if( con != null && sqlStr != null )
//			{
//				// logに出す文字列を作成
//				String logStr = sqlStr;
//
//				// sql log
//				logger.debug(logStr);
//
//				try
//				{
//					// create command
//					cmd = con.CreateCommand();
//					cmd.CommandText	= sqlStr;
//					cmd.Transaction = trans;
//
//					int dbParamCount = arrDbParameters.Count;
//					for( int i=0; i<dbParamCount; i++ )
//					{
//						cmd.Parameters.add((OleDbParameter)arrDbParameters[i]);
//					}
//
//					int nCount = cmd.ExecuteNonQuery();
//					if( nCount == 1 )
//					{
//						for( int j=0; j<dbParamCount; j++ )
//						{
//							OleDbParameter curParam = (OleDbParameter)cmd.Parameters[j];
//							if( curParam.Direction == ParameterDirection.Output)
//							{
//								retArrList.add(curParam);
//							}
//						}
//					}
//				}
//				catch( Exception e )
//				{
//					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbSelectError_MessageString);
//					logger.fatal(message + this.infoCaption, e );
//					throw e;
//				}
//				finally
//				{
//					if( cmd != null )
//					{
//						cmd.Dispose();
//					}
//				}
//			}
//
//			// end log
//			//logger.debug("end");
//
//			return retArrList;
//		}
//
//		/// <summary>
//		/// SELECT
//		/// </summary>
//		/// <remarks></remarks>
//		/// <param name="con">データベース接続オブジェクト</param>
//		/// <param name="sqlStr">実行するSQL文</param>
//		/// <returns>SELECT結果のDataTable</returns>
//		protected DataTable Select( OleDbConnection con, OleDbTransaction trans, String sqlStr )
//		{
//			// 最大表示件数の制限なしでSELECT
//			return this.Select( con, trans, sqlStr, false );
//		}
//
//		/// <summary>
//		/// SELECT
//		/// </summary>
//		/// <remarks></remarks>
//		/// <param name="con">データベース接続オブジェクト</param>
//		/// <param name="sqlStr">実行するSQL文</param>
//		/// <param name="maxRecordLimitFlg">最大表示件数を制限するか否かのフラグ（true：制限あり　false：制限なし）</param>
//		/// <returns>SELECT結果のDataTable</returns>
//		protected DataTable Select( OleDbConnection con, OleDbTransaction trans, String sqlStr, boolean maxRecordLimitFlg )
//		{
//			// parameters
//			DataSet ds = null;
//			DataTable dt = null;
//			OleDbDataAdapter adapter = null;
//
//			// begin log
//			//logger.debug("begin");
//
//			if( con != null && sqlStr != null )
//			{
//				// logに出す文字列を作成
//				String logStr = sqlStr;
//
//				if( maxRecordLimitFlg )
//				{
//					logStr += " [MaxLimit=" + Configuration.GetInstance().GetDbMaxResultsStr() + "]";
//				}
//				else
//				{
//					logStr += " [MaxLimit=OFF]";
//				}
//
//				try
//				{
//					//SQL時間測定用
//					Timestamp sqlStartTime = Timestamp.Now;
//
//					if( maxRecordLimitFlg )
//					{
//						String sqlWithLimit = "select * from (" + sqlStr + ") where ROWNUM <=" + Configuration.GetInstance().GetDbMaxResultsStr();
//
//						// create adapter
//						adapter = new OleDbDataAdapter( sqlWithLimit, con );
//						adapter.SelectCommand.Transaction = trans;
//
//						dt = new DataTable();
//						adapter.Fill(dt);
//						dt.TableName = this.tableNameStr;
//					}
//					else
//					{
//						// create adapter
//						adapter = new OleDbDataAdapter( sqlStr, con );
//						adapter.SelectCommand.Transaction = trans;
//
//						// 最大表示件数の制限なしでSELECT
//						dt = new DataTable();
//						adapter.Fill(dt);
//						dt.TableName = this.tableNameStr;
//					}
//
//#if (DEBUG)
//					//SQL時間
//					TimeSpan sqlTime = Timestamp.Now - sqlStartTime;
//					double sec = sqlTime.TotalSeconds;
//
//					//本物SQL
//					if (adapter != null &&
//						adapter.SelectCommand != null)
//					{
//						logStr = adapter.SelectCommand.CommandText;
//					}
//
//					String addLog = "";
//					if (sec > 10)
//					{
//						addLog = "×××[10秒以上]危険！！！×××";
//					}
//					else if (sec > 5)
//					{
//						addLog = "▲[5秒以上]見直し！▲";
//					}
//					else if (sec > 1)
//					{
//						addLog = "△[1秒以上]注意！△";
//					}
//					else if (sec > 0.5)
//					{
//						addLog = "○[0.5秒以上]確認！○";
//					}
//
//					logger.debug(String.Format("[SQL-TIME],{0:F3},{1},{2}",
//						sec,
//						addLog,
//						'"' + logStr + '"'));
//#else
//					logger.debug(logStr);
//#endif
//				}
//				catch( Exception e )
//				{
//					logger.debug(logStr); //エラー時にＳＱＬが出ないのでここで出力
//
//					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbSelectError_MessageString);
//					logger.fatal(message + this.infoCaption, e );
//					throw e;
//				}
//				finally
//				{
//					if( adapter != null )
//					{
//						adapter.Dispose();
//					}
//					if( ds != null )
//					{
//						ds.clear();
//						ds.Dispose();
//					}
//				}
//			}
//
//			// end log
//			//logger.debug("end");
//
//			//データテーブルにDouble型がある場合はDecimalに変換
//			dt = ConvTypeDoubleToDecimal(dt);
//
//			return dt;
//		}

	/// <summary>
	/// INSERT
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="sqlStr">実行するSQL文</param>
	/// <param name="logList">ログリスト</param>
	/// <returns>【INSERTに成功】true　【INSERTに失敗】false</returns>
	protected boolean Insert(Connection con, String sqlStr, ArrayList<Object> arglist, ArrayList logList) throws Exception
	{
		// parameters
		boolean retFlg = false;
		Object[] args = new Object[0];

		// begin log
		logList.add("Insert-begin");

		if( con != null && sqlStr != null )
		{
			// sql log
			logList.add(sqlStr);

			try {
				if (arglist != null && arglist.size() > 0) {
					args = new Object[arglist.size()];
					arglist.toArray(args);
				}
				DataBaseCore.executeSQL(sqlStr, args, con);
				retFlg = true;
			} catch (Exception e) {
				logList.add(MwmMessageParameter.dbInsertError_MessageString + this.infoCaption + e.toString());
				throw e;
			} finally {
			}
		}

		// end log
		logList.add("Insert-end");

		return retFlg;
	}
//
//		/// <summary>
//		/// UPDATE
//		/// </summary>
//		/// <remarks></remarks>
//		/// <param name="con">データベース接続オブジェクト</param>
//		/// <param name="sqlStr">実行するSQL文</param>
//		/// <returns>【UPDATEに成功】true　【UPDATEに失敗】false</returns>
//		protected boolean Update( OleDbConnection con, OleDbTransaction trans, String sqlStr )
//		{
//			// parameters
//			boolean retFlg = false;
//			OleDbCommand cmd = null;
//
//			// begin log
//			//logger.debug("begin");
//
//			if( con != null && sqlStr != null )
//			{
//				// sql log
//				logger.debug(sqlStr);
//
//				try
//				{
//					// create command
//					cmd = con.CreateCommand();
//					cmd.CommandText	= sqlStr;
//					cmd.Transaction = trans;
//					int nCount = cmd.ExecuteNonQuery();
//					if( nCount == 1 )
//					{
//						retFlg = true;
//					}
//				}
//				catch( Exception e )
//				{
//					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbUpdateError_MessageString);
//					logger.fatal(message + this.infoCaption, e );
//					throw e;
//				}
//				finally
//				{
//					if( cmd != null )
//					{
//						cmd.Dispose();
//					}
//				}
//			}
//
//			// end log
//			//logger.debug("end");
//
//			return retFlg;
//		}

//		/// <summary>
//		/// DELETE
//		/// </summary>
//		/// <remarks></remarks>
//		/// <param name="con">データベース接続オブジェクト</param>
//		/// <param name="sqlStr">実行するSQL文</param>
//		/// <returns>【DELETEに成功】true　【DELETEに失敗】false</returns>
//		protected boolean Delete( OleDbConnection con, OleDbTransaction trans, String sqlStr )
//		{
//			// parameters
//			boolean retFlg = false;
//			OleDbCommand cmd = null;
//
//			// begin log
//			logger.debug("begin");
//
//			if( con != null && sqlStr != null )
//			{
//				// sql log
//				logger.debug(sqlStr);
//
//				try
//				{
//					// create command
//					cmd = con.CreateCommand();
//					cmd.CommandText	= sqlStr;
//					cmd.Transaction = trans;
//					int nCount = cmd.ExecuteNonQuery();
//					if( nCount == 1 )
//					{
//						retFlg = true;
//					}
//				}
//				catch( Exception e )
//				{
//					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbDeleteError_MessageString);
//					logger.fatal(message + this.infoCaption, e );
//					throw e;
//				}
//				finally
//				{
//					if( cmd != null )
//					{
//						cmd.Dispose();
//					}
//				}
//			}
//
//			// end log
//			logger.debug("end");
//
//			return retFlg;
//		}

	/// <summary>
	/// 強制DELETE
	/// </summary>
	/// <remarks></remarks>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="sqlStr">実行するSQL文</param>
	/// <param name="logList">ログリスト</param>
	/// <returns>【DELETEに成功】true　【DELETEに失敗】false</returns>
	protected boolean ForceDelete(Connection con, String sqlStr, ArrayList<Object> arglist, ArrayList logList) throws Exception
	{
		// parameters
		boolean retFlg = true;
		Object[] args = new Object[0];

		// begin log
		logList.add("ForceDelete-begin");

		if( con != null && sqlStr != null )
		{
			// sql log
			logList.add(sqlStr);

			try
			{
				//SQL文の検索条件チェックを行う≪※Update,Delete時必須≫
				CheckSQLWhere(sqlStr);

				// create command
				if (arglist != null && arglist.size() > 0) {
					args = new Object[arglist.size()];
					arglist.toArray(args);
				}
				DataBaseCore.executeSQL(sqlStr, args, con);
				retFlg = true;
			}
			catch( Exception e )
			{
				retFlg = false;
				logList.add( MwmMessageParameter.dbForceDeleteError_MessageString + this.infoCaption + e.toString() );
				throw e;
			}
			finally
			{
			}
		}

		// end log
		logList.add("ForceDelete-end");

		return retFlg;
	}

	private class OrderType
	{
		public static final String ASC = "ASC";
		public static final String DESC = "DESC";
	}

	private class OrderInfo
	{
		public String key = "";
		public String type = "";
	}

	public void ClearOrderKey()
	{
		this.orderKeys.clear();
	}

	public void AddOrderKeyAsc(String name)
	{
		OrderInfo info = CreateOrderInfo(name, OrderType.ASC);
		this.orderKeys.add(info);
	}

	public void AddOrderKeyDesc(String name)
	{
		OrderInfo info = CreateOrderInfo(name, OrderType.DESC);
		this.orderKeys.add(info);
	}

	private OrderInfo CreateOrderInfo(String key, String type)
	{
		OrderInfo info = new OrderInfo();
		info.key = key;
		info.type = type;

		return info;
	}

	/// <summary>
	/// SQL文の検索条件チェックを行う≪※Update,Delete時必須≫
	/// </summary>
	/// <param name="sqlStr">SQL文</param>
	/// <returns></returns>
	private void CheckSQLWhere(String sqlStr) throws Exception
	{
		//条件が含まれていない場合は例外エラーとする
		if (!sqlStr.toLowerCase().contains("where")) {
			throw new Exception("ERROR Not Where");
		}
	}

//		/// <summary>
//		/// 指定データテーブルのDouble型の項目をDecimal型に変換して返します。
//		/// </summary>
//		/// <param name="dt">元データテーブル</param>
//		/// <returns>変換後データテーブル</returns>
//		private DataTable ConvTypeDoubleToDecimal(DataTable dt)
//		{
//			DataTable wkDt = null;
//
//			try
//			{
//				//データチェック
//				if (dt == null) return dt;
//				if (dt.Columns == null) return dt;
//
//				//カラム内のデータタイプでDoubleがあるかをチェック
//				StringCollection doubleKeys = new StringCollection();
//				for(int i=0; i<dt.Columns.Count; i++)
//				{
//					if (dt.Columns[i].DataType == typeof(System.Double))
//					{
//						//ダブルタイプリストに追加
//						String name = dt.Columns[i].ColumnName;
//						if (!doubleKeys.Contains(name))
//						{
//							doubleKeys.add(name);
//						}
//					}
//				}
//
//				//Double型が無い場合は処理終了
//				if (doubleKeys.Count <= 0) return dt;
//
//				//Double型がある場合は型変更
//				//クローンを作成
//				wkDt = dt.Clone();
//				for (DataRow row : dt.Rows)
//				{
//					wkDt.Rows.add(row.ItemArray);
//				}
//				//ダブル型の項目をDecimalに変更
//				for (String keyName : doubleKeys)
//				{
//					wkDt.Columns.Remove(keyName);
//					wkDt.Columns.add(keyName, typeof(System.Decimal));
//					for(int i=0; i<dt.Rows.Count; i++)
//					{
//						DataRow row = dt.Rows[i];
//						if (row.IsNull(keyName))
//						{
//							//Nullの場合はNullを設定
//							wkDt.Rows[i][keyName] = DBNull.Value;
//						}
//						else
//						{
//							try
//							{
//								//Decimalに変換して設定
//								wkDt.Rows[i][keyName] = Decimal.Parse(row[keyName].toString());
//							}
//							catch
//							{
//								//念のためエラー時はNull設定
//								wkDt.Rows[i][keyName] = DBNull.Value;
//							}
//						}
//					}
//				}
//			}
//			catch( Exception e )
//			{
//				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbSelectError_MessageString);
//				logger.fatal(message + this.infoCaption, e );
//				return dt;
//			}
//			return wkDt;
//		}

	/// <summary>
	/// カラム情報クラス
	/// </summary>
	public class ColumnInfo
	{
		/// <summary>
		/// フィールド名
		/// </summary>
		public String fieldName;

		/// <summary>
		/// 値
		/// </summary>
		public String fieldVal;

		/// <summary>
		/// Keyフラグ
		/// 以前は、PKeyかどうかを示すフラグであったが、
		/// 現状では、Where句に使用するかどうかを示す。
		/// true: Where句項目
		/// false:　Where句には使用しない
		/// </summary>
		public boolean pKey = false;

		/// <summary>
		/// 等号
		/// Where句で使用する際に使用する等号を指定する。
		/// 書式は、
		///  filedName + sign + value
		/// となる
		public String sign = SignType.Equal;

		//2008.03.26  AddBy E.Yoshimi Start
		/// <summary>
		/// in句 or FieldName is Nullを生成する時に指定
		/// </summary>
		public boolean pIsNull = false;
		//2008.03.26  AddBy E.Yoshimi End


	}

	/// <summary>
	/// 等号
	/// </summary>
	public final class SignType
	{
		public static final String Equal = "=";
		public static final String NotEqual = "!=";
		public static final String Over = "<=";
		public static final String Under = ">=";
		public static final String Like = "like";
		public static final String In = "in";
		public static final String isNull = "is NULL";
		public static final String isNotNull = "is not NULL";
	}
}

