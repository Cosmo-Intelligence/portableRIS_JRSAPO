package ris.lib.mwm.util;

/// <summary>
/// メッセージコンフィグ定義
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public  final class MwmMessageParameter
{
	static public String initStartLogger_MessageString						= "Loggerの初期化に成功しました。";
	static public String initStartExeException_MessageString				= "正常な処理することができませんでした。";
	static public String initLoggerException_MessageString					= "Loggerの初期化に失敗しました。\r\nログファイルの出力先およびコンフィグ設定ファイルをご確認ください。";
	static public String initDuplicate_MessageString						= "アプリケーションは、すでに起動中です。 重複起動は出来ないため、アプリケーションを終了致します。";

	//DB接続
	static public String createOleDbConn_NG_MessageString					= "Connectionの作成に失敗しました。";

	static public String createOleDbConn_OK_MessageString					= "Connectionの作成に成功しました。 ハッシュコード:";
	static public String disposeOleDbConn_OK_MessageString					= "Connectionを破棄します。 ハッシュコード:";
	static public String disposeOleDbConn_NG_MessageString					= "Connectionの破棄に失敗しました。";
	static public String connectOleDbConn_MessageString						= "Connectionに接続中です。";
	static public String dbTransactionError_MessageString					= "DBトランザクションでエラーが発生しました。";
	static public String dbRollBackError_MessageString						= "RollBack時にエラーが発生しました。";
	static public String dbSelectError_MessageString						= "Select文実行にて例外が発生しました。      :";
	static public String dbInsertError_MessageString						= "Insert文実行にて例外が発生しました。      :";
	static public String dbForceDeleteError_MessageString					= "ForceDelete文実行にて例外が発生しました。 :";

	static public String initMwmSource_MessageString						= "MWM Source      = ";
	static public String initMwmDbUser_MessageString						= "MWM DB User     = ";
	static public String initMwmDbPassword_MessageString					= "MWM DB Password = ";
}
