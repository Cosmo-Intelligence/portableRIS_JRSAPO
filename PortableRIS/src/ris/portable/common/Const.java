package ris.portable.common;

import java.math.BigDecimal;
import java.sql.Timestamp;

import ris.lib.core.util.CommonString;

public class Const {

	/**
	 * 処理結果 "OK"
	 */
	public static final String RESULT_OK = "OK";
	/**
	 * 処理結果 "NG"
	 */
	public static final String RESULT_NG = "NG";

	/**
	 * エラーレベル "WARN"
	 */
	public static final String ERRLEVEL_WARN = "WARN";
	/**
	 * エラーレベル "ERROR"
	 */
	public static final String ERRLEVEL_ERROR = "ERROR";
	/**
	 * エラーレベル "NO_SESSION"
	 */
	public static final String ERRLEVEL_NO_SESSION = "NO_SESSION";

	/**
	 * RISDBリソース
	 */
	public static final String DBNAME_RIS = "java:comp/env/jdbc/oracle/rris";

	/**
	 * MWMDBリソース
	 */
	public static final String DBNAME_MWM = "java:comp/env/jdbc/oracle/mwm";

	/**
	 * MPPSDBリソース
	 */
	public static final String DBNAME_MPPS = "java:comp/env/jdbc/oracle/mpps";

	/**
	 * 設定ファイルパス
	 */
	public static final String CONFIG_FILE = "/WEB-INF/PortableRIS.config.xml";

	/**
	 * かなローマ変換用テキストファイルパス
	 */
	public static final String KANAROMATEXT = "/WEB-INF/KanaRomaList.txt";

	/**
	 *  検査ステータス
	 */
	public static final String STATUS_UNREGISTERED     = CommonString.STATUS_UNREGISTERED;     // 未受付
	public static final String STATUS_ISLATE           = CommonString.STATUS_ISLATE;           // 遅刻
	public static final String STATUS_ISCALLING        = CommonString.STATUS_ISCALLING;        // 呼出中
	public static final String STATUS_ISREGISTERED     = CommonString.STATUS_ISREGISTERED;     // 受付済
	public static final String STATUS_INOPERATION      = CommonString.STATUS_INOPERATION;      // 実施中
	public static final String STATUS_REST             = CommonString.STATUS_REST;             // 保留
	public static final String STATUS_RECALLING        = CommonString.STATUS_RECALLING;        // 再呼出
	public static final String STATUS_REREGISTERED     = CommonString.STATUS_REREGISTERED;     // 再受付
	public static final String STATUS_ISFINISHED       = CommonString.STATUS_ISFINISHED;       // 実施済
	public static final String STATUS_STOP             = CommonString.STATUS_STOP;             // 中止
	public static final String STATUS_DELETE           = CommonString.STATUS_DELETE;           // 削除
	public static final String STATUS_DELETE_SAVEPOINT = CommonString.STATUS_DELETE_SAVEPOINT; // 削除ステータス検索条件保存位置(100桁目)

	/**
	 *  オーダ一覧 ソート条件
	 */
	public static final String ORDERLIST_SORT_KANASIMEI = "0"; // 名前順
	public static final String ORDERLIST_SORT_BYOUTOU   = "1"; // 病棟順

	/**
	 * MPPS取得設定値
	 */
	public static final String MPPS_GET_OFF = "0"; // MPPS取得しない
	public static final String MPPS_GET_ON  = "1"; // MPPS取得する

	/**
	 *  改行コード
	 */
	public static final String LINE_FEED = "\n";

	/**
	 *  部位情報 区切り文字
	 */
	public static final String SLASH = "／";

	/**
	 *  timestamp型最小値
	 */
	public static Timestamp TIMESTAMP_MINVALUE = Timestamp.valueOf("0001-01-01 00:00:00");

	/**
	 *  int型最小値
	 */
	public static Integer INT_MINVALUE = Integer.MIN_VALUE;

	/**
	 *  decimal型最小値
	 */
	public static BigDecimal DECIMAL_MINVALUE = BigDecimal.valueOf(new Double("-79228162514264337593543950335"));

	/**
	 *  double型最小値
	 */
	public static Double DOUBLE_MINVALUE = Double.MIN_VALUE;

	/**
	 *  RISDBユーザ名
	 */
	public static final String RISUSER = "RRIS";

	/**
	 *  MPPS.CODEMEANING：NORMAL
	 */
	public static final String MPPS_CODEMEANING_NORMAL = "NORMAL";

	/**
	 *  MPPS.CODEMEANING：AGAIN
	 */
	public static final String MPPS_CODEMEANING_AGAIN = "AGAIN";

	/**
	 *  MPPS.CODEMEANING：ADD
	 */
	public static final String MPPS_CODEMEANING_ADD = "ADD";

	/**
	 *  コメント区分
	 */
	public static final String PRECOMMENTKBN_ORDER = CommonString.PRECOMMENTKBN_ORDER; //ｵｰﾀﾞｺﾒﾝﾄ
	public static final String PRECOMMENTKBN_BUI   = CommonString.PRECOMMENTKBN_BUI;   //部位ｺﾒﾝﾄ
	public static final String PRECOMMENTKBN_EX	   = CommonString.PRECOMMENTKBN_EX;    //実施ｺﾒﾝﾄ

	// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
	/**
	 * 処理モード：参照
	 */
	public static final String MODE_READ = "read";
	// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
	// 2019.10.03. Add Cosmo Start 排他ロック対応
	/**
	 * 処理モード：初ログイン
	 */
	public static final String MODE_DEL_LOCK= "delLock";

	// 2019.10.03. Add Cosmo End 排他ロック対応

}
