package ris.lib.core.util;

/// <summary>
/// メッセージコンフィグ定義
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.02.18	: 112478 / A.Kobayashi		: original
/// 2.04.001		: 2010.10.04	: 999999 / DD.Chinh			: KANRO-R-11
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo		: KANRO-R-7
/// V2.04.00		: 2011.02.16	: 999999 / T.Nishikubo		: KANRO-R-27
/// V2.01.00		: 2011.07.11    extends 999999 / NSK_H.Hashimoto	: NML-CAT1-002
/// V2.01.00		: 2011.07.21    extends 999999 / NSK_T.Koudate	: NML-CAT2-004
/// V2.01.00		: 2011.08.02    extends 999999 / NSK_T.Wada		: NML-CAT2-001
/// V2.01.00		: 2011.08.05	: 999999 / K.Aizawa			: NML-1-X03 NML-CAT2-010
/// V2.01.00		: 2011.08.22    extends 999999 / DD.Chinh			: NML-CAT3-035
/// V2.01.01.13000	: 2011.11.25	: 999999 / NSK_T.Wada		: OMH-1-9
/// </summary>
public final class MessageParameter
{
	// 内部メッセージ用（DB接続後はErrorMsgMasterを使用する）

	public static final String initStartLogger_MessageString			= "Loggerの初期化に成功しました。";
	public static final String initStartExeException_MessageString	= "正常な処理することができませんでした。";
	public static final String initLoggerException_MessageString		= "Loggerの初期化に失敗しました。\r\nログファイルの出力先およびコンフィグ設定ファイルをご確認ください。";
	public static final String initDuplicate_MessageString			= "アプリケーションは、すでに起動中です。 重複起動は出来ないため、アプリケーションを終了致します。";

	//ログインエラーメッセージ
	public static final String USERID_NOMATCH_MSG						= "ユーザIDまたはパスワードが間違っています。";
	public static final String USERISLOCKED_MSG						= "このユーザは現在使用できません。";
	public static final String PASSWORDNOMATCH_MSG					= "ユーザIDまたはパスワードが間違っています。";
	public static final String PASSWORDEXPIRED_MSG					= "パスワードの有効期限が切れています。このユーザではログインできません。\r\n管理者に連絡して、パスワードを設定してください。";
	public static final String NOLICENSE_MSG							= "このユーザはアクセス権限がありません。";
	public static final String PASSWORD_EXPIREISNEAR_MSG1				= "でパスワードの有効期限が切れます。\r\n期限までに";
	public static final String PASSWORD_EXPIREISNEAR_MSG2				= "によりパスワードを変更してください。";
	public static final String PASSWORD_EXPIREISNEAR_MSG3				= "パスワードを変更してください。";
	public static final String PASSWORD_EXPIREISNEAR_MSG4				= "\r\n\r\n今すぐ変更する場合は、\r\nパスワード変更ボタンを押下してください。";
	public static final String PASSWORD_EXPIREISNEAR_MSG5				= "\r\n\r\nパスワードの変更は下記アドレスより行って下さい。";

	public static final String PASSWORD_CHANGEOK_MSG					= "パスワードは更新されました。古いパスワードで既にログインしている場合は、新しいパスワードでログインし直してください。";
	public static final String NO_APPROVE_RIGHTS_MSG					= "ユーザ[{0}]は確定権限がありません。";

	// データベースエラーメッセージ
	public static final String DATABASE_CONNECTERR_MSG				= "データベースの接続に失敗しました。";
	public static final String DATABASE_CLOSEERR_MSG					= "データベースの切断に失敗しました。";
	public static final String DATABASE_TRANACTIONERR_MSG				= "データベースのトランザクションに不整合が発生しました。";



	// 外部メッセージ

	// システム用

	//システム
	public static final String infoTitle_MessageString						= "infoTitle_MessageString";
	public static final String exceptionTitle_MessageString					= "exceptionTitle_MessageString";
	// 2010.09.14 Add K.Shinohara Start
	public static final String errorTitle_MessageString						= "errorTitle_MessageString";
	// 2010.09.14 Add K.Shinohara End
	public static final String changePassword_MessageString					= "changePassword_MessageString";

	//DB接続
	public static final String createOleDbConn_NG_MessageString				= "createOleDbConn_NG_MessageString";
	public static final String disposeOleDbConn_NG_MessageString				= "disposeOleDbConn_NG_MessageString";
	public static final String isDBConection_NG_MessageString					= "isDBConection_NG_MessageString";
	public static final String isDBConection_Retry_MessageString				= "isDBConection_Retry_MessageString";
	public static final String createOleDbConn_OK_MessageString				= "createOleDbConn_OK_MessageString";
	public static final String disposeOleDbConn_OK_MessageString				= "disposeOleDbConn_OK_MessageString";
	public static final String connectOleDbConn_MessageString					= "connectOleDbConn_MessageString";
	public static final String infoTitleOKGetOleDbConn_MessageString			= "infoTitleOKGetOleDbConn_MessageString";
	public static final String infoTitleOKAddOleDbConn_MessageString			= "infoTitleOKAddOleDbConn_MessageString";
	public static final String infoTitleOKDisposeOleDbConn_MessageString		= "infoTitleOKDisposeOleDbConn_MessageString";
	public static final String infoTitleOKReturnOleDbConn_MessageString		= "infoTitleOKReturnOleDbConn_MessageString";
	public static final String initDBException_MessageString					= "initDBException_MessageString";
	public static final String dbConnectionException_MessageString			= "dbConnectionException_MessageString";
	public static final String reConnectionException_MessageString			= "reConnectionException_MessageString";
	public static final String dbException_MessageString						= "dbException_MessageString";

	//共通処理
	public static final String deleteDirException_MessageString				= "deleteDirException_MessageString";
	public static final String deleteFileException_MessageString				= "deleteFileException_MessageString";
	public static final String noDeleteFileException_MessageString			= "noDeleteFileException_MessageString";
	public static final String debugDeleteLogFile_MessageString				= "debugDeleteLogFile_MessageString";

	//監査認証
	public static final String auditTrail_RegisterException_MessageString		= "auditTrail_RegisterException_MessageString";
	public static final String auditTrail_UserLoginException_MessageString	= "auditTrail_UserLoginException_MessageString";
	public static final String loginException_MessageString					= "loginException_MessageString";
	public static final String equalizeUserInfoException_MessageString		= "equalizeUserInfoException_MessageString";
	public static final String registAuditTrailException_MessageString		= "registAuditTrailException_MessageString";



	// 初期化用

	public static final String initExternalMessageException_MessageString				= "initExternalMessageException_MessageString";
	public static final String initDataBaseConfException_MessageString				= "initDataBaseConfException_MessageString";
	public static final String initAppException_MessageString							= "initAppException_MessageString";
	public static final String initDataBaseException_MessageString					= "initDataBaseException_MessageString";
	public static final String initFileException_MessageString						= "initFileException_MessageString";
	public static final String initFormException_MessageString						= "initFormException_MessageString";
	public static final String initMessageConfException_MessageString					= "initMessageConfException_MessageString";
	public static final String initMasterDataException_MessageString					= "initMasterDataException_MessageString";
	public static final String initMenuButtonException_MessageString					= "initMenuButtonException_MessageString";
	public static final String initCommonAutoLogoutMinException_MessageString			= "initCommonAutoLogoutMinException_MessageString";
	public static final String initCommonHospitalIDException_MessageString			= "initCommonHospitalIDException_MessageString";
	public static final String initCommonLoginDelayTimeSecException_MessageString		= "initCommonLoginDelayTimeSecException_MessageString";
	public static final String initCommonPwdChgPageURLException_MessageString			= "initCommonPwdChgPageURLException_MessageString";
	public static final String initTerminalIDException_MessageString					= "initTerminalIDException_MessageString";
	public static final String initCurrentConfirmAuditDefineException_MessageString	= "initCurrentConfirmAuditDefineException_MessageString";
	public static final String initSystemDefineException_MessageString				= "initSystemDefineException_MessageString";
	public static final String initMppsConnectionException_MessageString				= "initMppsConnectionException_MessageString";
	public static final String initExamDailyConfigException_MessageString				= "initExamDailyConfigException_MessageString";

	//コンフィグ読込
	public static final String initMainServerNameException_MessageString				= "initMainServerNameException_MessageString";
	public static final String initMainServerName_MessageString 						= "initMainServerName_MessageString";
	public static final String initConfException_MessageString 						= "initConfException_MessageString";
	public static final String initDbSourceException_MessageString 					= "initDbSourceException_MessageString";
	public static final String initDbSource_MessageString 							= "initDbSource_MessageString";
	public static final String initDbUserException_MessageString 						= "initDbUserException_MessageString";
	public static final String initDbUser_MessageString 								= "initDbUser_MessageString";
	public static final String initDbPasswordException_MessageString					= "initDbPasswordException_MessageString";
	public static final String initDbPassword_MessageString 							= "initDbPassword_MessageString";
	public static final String initPrinterDriverException_MessageString				= "initPrinterDriverException_MessageString";
	public static final String initPrinterDriver_MessageString						= "initPrinterDriver_MessageString";
	public static final String infoTitleNullExRoom_MessageString						= "infoTitleNullExRoom_MessageString";
	public static final String initWorkSource_MessageString							= "initWorkSource_MessageString";
	public static final String initWorkDbUser_MessageString							= "initWorkDbUser_MessageString";
	public static final String initWorkDbPassword_MessageString						= "initWorkDbPassword_MessageString";
	public static final String initMppsSource_MessageString 							= "initMppsSource_MessageString";
	public static final String initMppsDbUser_MessageString 							= "initMppsDbUser_MessageString";
	public static final String initMppsDbPassword_MessageString 						= "initMppsDbPassword_MessageString";
	public static final String initMwmSource_MessageString 							= "initMwmSource_MessageString";
	public static final String initMwmDbUser_MessageString 							= "initMwmDbUser_MessageString";
	public static final String initMwmDbPassword_MessageString 						= "initMwmDbPassword_MessageString";



	// 帳票用

	public static final String printerInstallError_MessageString			= "printerInstallError_MessageString";
	public static final String printerNotMaster_MessageString				= "printerNotMaster_MessageString";
	public static final String printerNotDailyForm_MessageString			= "printerNotDailyForm_MessageString";
	public static final String printerTermPrintNotFound_MessageString 	= "printerTermPrintNotFound_MessageString";
	public static final String printerInitCodeError_MessageString 		= "printerInitCodeError_MessageString";
	public static final String printerStartDocumentError_MessageString 	= "printerStartDocumentError_MessageString";
	public static final String printerStartPrinterError_MessageString 	= "printerStartPrinterError_MessageString";
	public static final String printerOutputError_MessageString 			= "printerOutputError_MessageString";
	public static final String printerEndDocumentError_MessageString 		= "printerEndDocumentError_MessageString";
	public static final String printerEndPrinterError_MessageString 		= "printerEndPrinterError_MessageString";
	public static final String printerError_MessageString 				= "printerError_MessageString";
	public static final String printerStartError_MessageString 			= "printerStartError_MessageString";
	public static final String printerCloseError_MessageString 			= "printerCloseError_MessageString";
	public static final String printerGetCrObjectHeadError_MessageString 	= "printerGetCrObjectHeadError_MessageString";
	public static final String printFormError_MessageString 				= "printFormError_MessageString";
	public static final String printFormOpenError_MessageString 			= "printFormOpenError_MessageString";
	public static final String printFormKensatypeError_MessageString 		= "printFormKensatypeError_MessageString";
	public static final String printerGetFormObject_MessageString 		= "printerGetFormObject_MessageString";
	public static final String printerOpendError_MessageString 			= "printerOpendError_MessageString";
	public static final String printerPrintedError_MessageString 			= "printerPrintedError_MessageString";
	public static final String printerHdcError_MessageString 				= "printerHdcError_MessageString";
	public static final String printerNoPrinterError_MessageString 		= "printerNoPrinterError_MessageString";
	public static final String printerNotPrinterError_MessageString 		= "printerNotPrinterError_MessageString";
	public static final String printerStartDcError_MessageString 			= "printerStartDcError_MessageString";
	public static final String printerOpenError_MessageString 			= "printerOpenError_MessageString";
	public static final String printerNotPasswordError_MessageString 		= "printerNotPasswordError_MessageString";
	public static final String printerOptionError_MessageString 			= "printerOptionError_MessageString";
	public static final String printerExCharError_MessageString 			= "printerExCharError_MessageString";
	public static final String printerNoOpenError_MessageString 			= "printerNoOpenError_MessageString";
	public static final String printerEndPageError_MessageString 			= "printerEndPageError_MessageString";
	public static final String printerEndDocError_MessageString 			= "printerEndDocError_MessageString";
	public static final String printerNoPageError_MessageString 			= "printerNoPageError_MessageString";
	public static final String printerWriteDocError_MessageString 		= "printerWriteDocError_MessageString";
	public static final String printerInvalidHdcError_MessageString 		= "printerInvalidHdcError_MessageString";
	public static final String printerNoInstPrinterError_MessageString 	= "printerNoInstPrinterError_MessageString";
	public static final String printerCreateDcError_MessageString 		= "printerCreateDcError_MessageString";
	public static final String printerStartPagePError_MessageString 		= "printerStartPagePError_MessageString";
	public static final String printerEndPagePError_MessageString 		= "printerEndPagePError_MessageString";
	public static final String printerOleDrawError_MessageString 			= "printerOleDrawError_MessageString";
	public static final String printerOutputNoneError_MessageString 		= "printerOutputNoneError_MessageString";
	public static final String printerObjectDrawError_MessageString 		= "printerObjectDrawError_MessageString";
	public static final String printerPDFOpenError_MessageString 			= "printerPDFOpenError_MessageString";
	public static final String printerPDFDllError_MessageString 			= "printerPDFDllError_MessageString";
	public static final String printerErrUrlError_MessageString 			= "printerErrUrlError_MessageString";
	public static final String printerSetFormSizeError_MessageString 		= "printerSetFormSizeError_MessageString";
	public static final String printerPrtCtlError_MessageString 			= "printerPrtCtlError_MessageString";
	public static final String printerPaperSizeError_MessageString 		= "printerPaperSizeError_MessageString";
	public static final String printerAddFormDeniedError_MessageString	= "printerAddFormDeniedError_MessageString";
	public static final String printerNoOpenDocError_MessageString 		= "printerNoOpenDocError_MessageString";
	public static final String printerNotOrder_MessageString				= "printerNotOrder_MessageString";
	public static final String printerDataNothing_MessageString			= "printerDataNothing_MessageString";
	public static final String printerIraiInfo_MessageString				= "printerIraiInfo_MessageString";
	public static final String printerExamDailyInfo_MessageString			= "printerExamDailyInfo_MessageString";
	public static final String printerReceptInfo_MessageString			= "printerReceptInfo_MessageString";// 2011.11.11 ADD NSK_Y.Fujimura OMH-1-01
	//
	public static final String printerObjectSama_MessageString 			= "printerObjectSama_MessageString";
	public static final String printerObjectTosi_MessageString 			= "printerObjectTosi_MessageString";
	public static final String printerObjectMai_MessageString 			= "printerObjectMai_MessageString";
	public static final String printerObjectM_MessageString 				= "printerObjectM{0}_MessageString";
	public static final String printerObjectF_MessageString 				= "printerObjectF{0}_MessageString";
	public static final String printerObjectIn_MessageString 				= "printerObjectIn_MessageString";
	public static final String printerObjectOut_MessageString 			= "printerObjectOut_MessageString";
	public static final String printerObjectInout_MessageString 			= "printerObjectInout_MessageString";
	public static final String printerObjectGairai_MessageString 			= "printerObjectGairai_MessageString";
	public static final String printerObjectSikyu_MessageString 			= "printerObjectSikyu_MessageString";
	public static final String printerObjectDokuei_MessageString 			= "printerObjectDokuei_MessageString";
	public static final String printerObjectDokueiOff_MessageString 		= "printerObjectDokueiOff_MessageString";
	public static final String printerObjectDokueiOn_MessageString 		= "printerObjectDokueiOn_MessageString";
	public static final String printerObjectDokueiSkip_MessageString 		= "printerObjectDokueiSkip_MessageString";
	public static final String printerObjectDokueiEmg_MessageString 		= "printerObjectDokueiEmg_MessageString";
	public static final String printerObjectKaikeiOff_MessageString 		= "printerObjectKaikeiOff_MessageString";
	public static final String printerObjectKaikeiOn_MessageString 		= "printerObjectKaikeiOn_MessageString";
	public static final String printerObjectRiNeedle_MessageString 		= "printerObjectRiNeedle_MessageString";
	public static final String printerObjectRiInspect_MessageString 		= "printerObjectRiInspect_MessageString";
	public static final String printerObjectRiFollow_MessageString 		= "printerObjectRiFollow_MessageString";
	public static final String printerObjectRiOther_MessageString 		= "printerObjectRiOther_MessageString";
	public static final String printerObjectInfectionMarkON_MessageString = "printerObjectInfectionMarkON_MessageString";
	//
	public static final String printNameReceipt_MessageString 			= "printNameReceipt_MessageString";
	public static final String printNameRequest_MessageString 			= "printNameRequest_MessageString";
	public static final String printNameLabelKanja_MessageString 			= "printNameLabelKanja_MessageString";
	public static final String printNameLabelKensa_MessageString 			= "printNameLabelKensa_MessageString";
	public static final String printNameExReport_MessageString 			= "printNameExReport_MessageString";
	public static final String printNameExamDailyReport_MessageString 	= "printNameExamDailyReport_MessageString";
	public static final String printNameListReference_MessageString 		= "printNameListReference_MessageString";
	public static final String printNameListPortable_MessageString 		= "printNameListPortable_MessageString";
	public static final String printNameListLedger_MessageString 			= "printNameListLedger_MessageString";
	// 2011.08.16 Add DD.Chinh@MERX Start NML-1-X03
	public static final String reportTempFolderCreateError_MessageString	= "reportTempFolderCreateError_MessageString";
	// 2011.08.16 Add DD.Chinh@MERX End
	// 2011.08.05 Add K.Aizawa@MERX Start NML-1-X03
	public static final String reportTempFolderNotFound_MessageString		= "reportTempFolderNotFound_MessageString";
	// 2011.08.05 Add K.Aizawa@MERX End



	// ロジック用

	public static final String loginAutoLogout_MessageString										= "loginAutoLogout_MessageString";
	public static final String loginLogoff_MessageString											= "loginLogoff_MessageString";
	public static final String loginChangeUser_MessageString										= "loginChangeUser_MessageString";
	public static final String exeException_MessageString											= "exeException_MessageString";
	public static final String exeErrorException_MessageString									= "exeErrorException_MessageString";
	public static final String checkCloseNG_MessageString											= "checkCloseNG_MessageString";
	public static final String dataNotRegist_MessageString										= "dataNotRegist_MessageString";
	public static final String searchRisOrderDataException_MessageString							= "searchRisOrderDataException_MessageString";
	public static final String deleteException_MessageString										= "deleteException_MessageString";
	public static final String orderNotFound_MessageString										= "orderNotFound_MessageString";
	public static final String statusUpdated_MessageString										= "statusUpdated_MessageString";
	public static final String orderCancel_MessageString											= "orderCancel_MessageString";
	public static final String otherTermEditing_MessageString										= "otherTermEditing_MessageString";
	public static final String receiptOterTermException_MessageString								= "receiptOterTermException_MessageString";
	public static final String msgDlgSetButtonException_MessageString								= "msgDlgSetButtonException_MessageString";
	public static final String dbTransactionError_MessageString 									= "dbTransactionError_MessageString";
	public static final String dbRollBackError_MessageString 										= "dbRollBackError_MessageString";
	public static final String dbSelectError_MessageString 										= "dbSelectError_MessageString";
	public static final String dbInsertError_MessageString 										= "dbInsertError_MessageString";
	public static final String dbDeleteError_MessageString 										= "dbDeleteError_MessageString";
	public static final String dbUpdateError_MessageString 										= "dbUpdateError_MessageString";
	public static final String dbForceDeleteError_MessageString 									= "dbForceDeleteError_MessageString";
	public static final String authenticationPasswordNGTitle_MessageString						= "authenticationPasswordNGTitle_MessageString";
	public static final String loginInfoException_MessageString 									= "loginInfoException_MessageString";
	public static final String loginSuccessTitle_MessageString 									= "loginSuccessTitle_MessageString";
	public static final String loginWarningTitle_MessageString 									= "loginWarningTitle_MessageString";
	public static final String userEqualizeFailure_MessageString 									= "userEqualizeFailure_MessageString";
	public static final String userDataMiss_MessageString 										= "userDataMiss_MessageString";
	public static final String closeApp_MessageString 											= "closeApp_MessageString";
	public static final String reLoginTitle_MessageString 										= "reLoginTitle_MessageString";
	public static final String risdbConnect_MessageString 										= "risdbConnect_MessageString";
	public static final String risdbDisConnect_MessageString 										= "risdbDisConnect_MessageString";
	public static final String risdbConnectNo_MessageString 										= "risdbConnectNo_MessageString";
	public static final String getDBException_MessageString 										= "getDBException_MessageString";
	public static final String noHit_MessageString												= "noHit_MessageString";
	public static final String overMaxCount_MessageString 										= "overMaxCount_MessageString";
	public static final String noSearch_MessageString 											= "noSearch_MessageString";
	public static final String unCheckoutIPException_MessageString 								= "unCheckoutIPException_MessageString";
	public static final String unCheckoutCheckoutIDException_MessageString						= "unCheckoutCheckoutIDException_MessageString";
	public static final String getCheckoutInfoException_MessageString								= "getCheckoutInfoException_MessageString";
	public static final String checkoutException_MessageString 									= "checkoutException_MessageString";
	public static final String dbConnectError_MessageString 										= "dbConnectError_MessageString";
	public static final String getReceiptNumberError_MessageString 								= "getReceiptNumberError_MessageString";
	public static final String orderStatusMismatch_MessageString 									= "orderStatusMismatch_MessageString";
	public static final String deleteItemDefineNG_MessageString 									= "deleteItemDefineNG_MessageString";
	public static final String mwmdbConnectNo_MessageString 										= "mwmdbConnectNo_MessageString";
	public static final String receiptError_MessageString 										= "receiptError_MessageString";
	public static final String callError_MessageString 											= "callError_MessageString";
	public static final String yuusenError_MessageString 											= "yuusenError_MessageString";
	public static final String tardyError_MessageString 											= "tardyError_MessageString";
	public static final String getKeyNameException_MessageString 									= "getKeyNameException_MessageString";
	public static final String selectDBMaxCountOver_Format_MessageString							= "selectDBMaxCountOver_Format_MessageString";
	public static final String curCellWarn_MessageString 											= "curCellWarn_MessageString";
	public static final String exeDataError_MessageString 										= "exeDataError_MessageString";
	public static final String orverLength_MessageString 											= "orverLength_MessageString";
	public static final String unAddToHisInfo_MessageString 										= "unAddToHisInfo_MessageString";
	public static final String unAddToReportInfo_MessageString 									= "unAddToReportInfo_MessageString";
	public static final String unAddToPacsInfo_MessageString 										= "unAddToPacsInfo_MessageString";
	public static final String addToReportInfo_MessageString 										= "addToReportInfo_MessageString";
	public static final String addToPacsInfo_MessageString 										= "addToPacsInfo_MessageString";
	public static final String surrogateInputWarning_MessageString								= "surrogateInputWarning_MessageString";
	public static final String selectMenuButtonError_MessageString 								= "selectMenuButtonError_MessageString";
	public static final String registerTerminalDataException_MessageString						= "registerTerminalDataException_MessageString";
	// 2011.08.22 Add DD.Chinh@MERX Start NML-CAT3-035
	public static final String byteCutTerminalNameException_MessageString							= "byteCutTerminalNameException_MessageString";
	public static final String extractTerminalNameException_MessageString							= "extractTerminalNameException_MessageString";
	// 2011.08.22 Add DD.Chinh@MERX End
	public static final String registerTerminalData_MessageString 								= "registerTerminalData_MessageString";
	public static final String notFoundTerminalData_MessageString 								= "notFoundTerminalData_MessageString";
	public static final String reloadTerminalData_MessageString 									= "reloadTerminalData_MessageString";
	public static final String registerAutoTerminalDataException_MessageString 					= "registerAutoTerminalDataException_MessageString";
	public static final String notfoundKenzouIPAddress_MessageString								= "notfoundKenzouIPAddress_MessageString";
	public static final String notfoundKenzouSendPort_MessageString 								= "notfoundKenzouSendPort_MessageString";
	public static final String notfoundKenzouRecvPort_MessageString 								= "notfoundKenzouRecvPort_MessageString";
	public static final String notfoundImageFilePath1_MessageString 								= "notfoundImageFilePath1_MessageString";
	public static final String notfoundReportFormPath1_MessageString 								= "notfoundReportFormPath1_MessageString";
	public static final String notfoundImageFilePath2_MessageString 								= "notfoundImageFilePath2_MessageString";
	public static final String notfoundReportFormPath2_MessageString 								= "notfoundReportFormPath2_MessageString";
	public static final String notfoundKanaRomaList_MessageString 								= "notfoundKanaRomaList_MessageString";
	public static final String updateSearchPatternInfoException_MessageString						= "updateSearchPatternInfoException_MessageString";
	public static final String showItemDetailFormCollectCheckName_MessageString					= "showItemDetailFormCollectCheckName_MessageString";
	public static final String showItemNotColumn_MessageString									= "showItemNotColumn_MessageString";
	public static final String registerShowItemDefineDataException_MessageString					= "registerShowItemDefineDataException_MessageString";
	public static final String searchPatientInfoError_MessageString								= "searchPatientInfoError_MessageString";
	public static final String getBrowserNotFound_MessageString									= "getBrowserNotFound_MessageString";
	public static final String getUrlPathNotFound_MessageString									= "getUrlPathNotFound_MessageString";
	public static final String listKensaDateMonthOver_MessageString								= "listKensaDateMonthOver_MessageString";
	public static final String saveRisExecutionInfoException_MessageString						= "saveRisExecutionInfoException_MessageString";
	public static final String operationNoSave_MessageString										= "operationNoSave_MessageString";
	public static final String operationStop_MessageString 										= "operationStop_MessageString";
	public static final String operationDelete_MessageString 										= "operationDelete_MessageString";
	public static final String operationCheckUpdateMustItem_MessageString 						= "operationCheckUpdateMustItem_MessageString";
	public static final String operationCheckUpdateMaxCountOverBui_MessageString 					= "operationCheckUpdateMaxCountOverBui_MessageString";
	public static final String operationCheckUpdateMaxCountOverFilm_MessageString 				= "operationCheckUpdateMaxCountOverFilm_MessageString";
	public static final String operationCheckUpdateMaxCountOverItem_MessageString 				= "operationCheckUpdateMaxCountOverItem_MessageString";
	public static final String operationCheckUpdateItemMulti_MessageString 						= "operationCheckUpdateItemMulti_MessageString";
	public static final String operationCheckUpdateItemIjiSuuryou_MessageString 					= "operationCheckUpdateItemIjiSuuryou_MessageString";
	public static final String operationFilmReCalc_MessageString 									= "operationFilmReCalc_MessageString";
	public static final String operationDisableUpdateListShowItemDefine_MessageString 			= "operationDisableUpdateListShowItemDefine_MessageString";
	public static final String operationAddBuiWarning_MessageString 								= "operationAddBuiWarning_MessageString";
	public static final String operationAddSatuei_MessageString 									= "operationAddSatuei_MessageString";
	public static final String operationFilmAutoCalcWarning_MessageString 						= "operationFilmAutoCalcWarning_MessageString";
	public static final String operationNoMatchKensaDate_MessageString 							= "operationNoMatchKensaDate_MessageString";
	public static final String operationNoMatchGyoumuKbn_MessageString							= "operationNoMatchGyoumuKbn_MessageString";
	public static final String operationUpdateExecutionKensaDateStatusException_MessageString		= "operationUpdateExecutionKensaDateStatusException_MessageString";
	public static final String operationUpdateKensaStartProcException_MessageString 				= "operationUpdateKensaStartProcException_MessageString";
	public static final String operationUpdateKensaFinishProcException_MessageString 				= "operationUpdateKensaFinishProcException_MessageString";
	public static final String operationStatusRest_MessageString 									= "operationStatusRest_MessageString";
	// 2011.08.05 Add H.Satou@MERX Start NML-CAT2-010
	public static final String operationStatusInoperation_MessageString							= "operationStatusInoperation_MessageString";
	// 2011.08.05 Add H.Satou@MERX End
	public static final String operationStatusFinish_MessageString 								= "operationStatusFinish_MessageString";
	public static final String operationStatusFinishNotSend_MessageString 						= "operationStatusFinishNotSend_MessageString";
	public static final String operationStatusFinishSaveOnly_MessageString 						= "operationStatusFinishSaveOnly_MessageString";
	public static final String operationCheckMarkOnSatueiData_MessageString 						= "operationCheckMarkOnSatueiData_MessageString";
	public static final String operationCheckMarkOnSatueikikiData_MessageString 					= "operationCheckMarkOnSatueiDatakiki_MessageString";	// 2010.10.04 Add Y.Shibata SEIRO-R-1
	public static final String operationCheckFinishItemList_MessageString 						= "operationCheckFinishItemList_MessageString";
	public static final String operationCheckFinishItemListNG_MessageString 						= "operationCheckFinishItemListNG_MessageString";
	public static final String operationRestoreRisKensaDataException_MessageString 				= "operationRestoreRisKensaDataException_MessageString";
	public static final String checkoutError_MessageString 										= "checkoutError_MessageString";
	public static final String checkoutErrorByOperation_MessageString 							= "checkoutErrorByOperation_MessageString";
	public static final String checkoutSameKanjaByOperation_MessageString 						= "checkoutSameKanjaByOperation_MessageString";
	public static final String selectItemFormNotMatchValue3Warning_MessageString 					= "selectItemFormNotMatchValue3Warning_MessageString";
	public static final String selectItemFormNoMatchLengthWarning_MessageString 					= "selectItemFormNoMatchLengthWarning_MessageString";
	public static final String selectItemFormNoMatchModeWarning_MessageString 					= "selectItemFormNoMatchModeWarning_MessageString";
	public static final String selectItemFormNotFoundPartsWarning_MessageString 					= "selectItemFormNotFoundPartsWarning_MessageString";
	public static final String selectItemFormNotMatchKensatypeIDWarning_MessageString 			= "selectItemFormNotMatchKensatypeIDWarning_MessageString";
	public static final String selectItemFormCancel_MessageString 								= "selectItemFormCancel_MessageString";
	public static final String selectItemSubFormNoSelect_MessageString 							= "selectItemSubFormNoSelect_MessageString";
	public static final String selectItemDuplication_MessageString 								= "selectItemDuplication_MessageString";
	public static final String selectItemParts_MessageString 										= "selectItemParts_MessageString";
	public static final String selectItemInfuse_MessageString 									= "selectItemInfuse_MessageString";
	public static final String changeUserNG_MessageString 										= "changeUserNG_MessageString";
	public static final String changeUserNoMatchGisi_MessageString 								= "changeUserNoMatchGisi_MessageString";
	public static final String changeUserNoMatchKangosi_MessageString 							= "changeUserNoMatchKangosi_MessageString";
	public static final String registerToHisInfoException_MessageString 							= "registerToHisInfoException_MessageString";
	public static final String registerToReportInfoException_MessageString 						= "registerToReportInfoException_MessageString";
	public static final String registerToPacsInfoException_MessageString 							= "registerToPacsInfoException_MessageString";
	public static final String registerToReportPacsInfoException_MessageString 					= "registerToReportPacsInfoException_MessageString";
	public static final String noReceiptOrder_MessageString										= "noReceiptOrder_MessageString";
	public static final String disReceipt_MessageString 											= "disReceipt_MessageString";
	public static final String disReceiptExamSaveOn_MessageString 								= "disReceiptExamSaveOn_MessageString";
	public static final String updateYuusenFlgException_MessageString 							= "updateYuusenFlgException_MessageString";
	public static final String updateInfectionFlgException_MessageString 							= "updateInfectionFlgException_MessageString";
	public static final String updateStatusException_MessageString 								= "updateStatusException_MessageString";
	public static final String updateKakuhoNG_MessageString 										= "updateKakuhoNG_MessageString";
	public static final String updateKakuhoException_MessageString 								= "updateKakuhoException_MessageString";
	public static final String updateDisKakuhoNG_MessageString 									= "updateDisKakuhoNG_MessageString";
	public static final String updateDisKakuhoOther_MessageString 								= "updateDisKakuhoOther_MessageString";
	public static final String updateDisKakuhoException_MessageString 							= "updateDisKakuhoException_MessageString";
	public static final String updateRenrakuMemoException_MessageString 							= "updateRenrakuMemoException_MessageString";
	public static final String createRisOrderRegister_MessageString 								= "createRisOrderRegister_MessageString";
	public static final String createRisOrderRegisterReceipt_MessageString 						= "createRisOrderRegisterReceipt_MessageString";
	public static final String createRisOrderRegisterException_MessageString 						= "createRisOrderRegisterException_MessageString";
	public static final String createRisOrderNotTitle_MessageString 								= "createRisOrderNotTitle_MessageString";
	public static final String createRisOrderNotPatientID_MessageString 							= "createRisOrderNotPatientID_MessageString";
	public static final String createRisOrderNotKensatypeID_MessageString 						= "createRisOrderNotKensatypeID_MessageString";
	public static final String createRisOrderNotSection_MessageString 							= "createRisOrderNotSection_MessageString";
	public static final String createRisOrderNotSectionDoctor_MessageString 						= "createRisOrderNotSectionDoctor_MessageString";
	public static final String createRisOrderNotSatueiInfo_MessageString 							= "createRisOrderNotSatueiInfo_MessageString";
	public static final String createRisOrderCheckMaxCountOverBui_MessageString 					= "createRisOrderCheckMaxCountOverBui_MessageString";
	public static final String createRisPatientRegisterException_MessageString 					= "createRisPatientRegisterException_MessageString";
	public static final String createRisPatientNotID_MessageString 								= "createRisPatientNotID_MessageString";
	public static final String createRisPatientNotKana_MessageString 								= "createRisPatientNotKana_MessageString";
	public static final String createRisPatientNotKanaFull_MessageString 							= "createRisPatientNotKanaFull_MessageString";		// 2010.10.04 Add DD.Chinh KANRO-R-11
	public static final String createRisPatientNotKanji_MessageString 							= "createRisPatientNotKanji_MessageString";
	public static final String createRisPatientNotBirthday_MessageString 							= "createRisPatientNotBirthday_MessageString";
	public static final String createRisPatientNotSex_MessageString 								= "createRisPatientNotSex_MessageString";
	public static final String createRisPatientNotNyugai_MessageString 							= "createRisPatientNotNyugai_MessageString";
	public static final String createRisPatientIDMulti_MessageString 								= "createRisPatientIDMulti_MessageString";
	public static final String noDateFormatException_MessageString 								= "noDateFormatException_MessageString";
	public static final String updateRisSijiInfoException_MessageString 							= "updateRisSijiInfoException_MessageString";
	public static final String operationPreCheckMove_MessageString 								= "operationPreCheckMove_MessageString";
	public static final String receiptNGByStatus_MessageString 									= "receiptNGByStatus_MessageString";
	public static final String receiptConfirm_MessageString 										= "receiptConfirm_MessageString";
	public static final String receiptException_MessageString 									= "receiptException_MessageString";
	public static final String tcpSendMessageException_MessageString 								= "tcpSendMessageException_MessageString";
	public static final String searchPatternOverExamRoomException_MessageString 					= "searchPatternOverExamRoomException_MessageString";
	public static final String searchPatternOverKensaKikiException_MessageString 					= "searchPatternOverKensaKikiException_MessageString";
	public static final String UpdateRisOrderInfo_MessageString 									= "UpdateRisOrderInfo_MessageString";
	public static final String MWMSendMessage_MessageString										= "MWMSendMessage_MessageString";
	public static final String changePasswordCheckOldPasswordNG_MessageString 					= "ChangePasswordCheckOldPasswordNG_MessageString";
	public static final String changePasswordCheckNewPasswordNG_MessageString 					= "ChangePasswordCheckNewPasswordNG_MessageString";
	public static final String changePasswordCheckNew2PasswordNG_MessageString 					= "ChangePasswordCheckNew2PasswordNG_MessageString";
	public static final String changePasswordCheckConfPasswordNG_MessageString 					= "ChangePasswordCheckConfPasswordNG_MessageString";
	public static final String changePasswordOK_MessageString 									= "ChangePasswordOK_MessageString";
	public static final String changePasswordNG_MessageString 									= "ChangePasswordNG_MessageString";
	public static final String sendToHisInfoByPatient_MessageString 								= "sendToHisInfoByPatient_MessageString";
	public static final String updateKenzouOperationException_MessageString 						= "updateKenzouOperationException_MessageString";
	public static final String kenzouEndConfirm_MessageString 									= "kenzouEndConfirm_MessageString";
	public static final String changeExamDateOK_MessageString										= "changeExamDateOK_MessageString";
	public static final String registerShowListDefineDataException_MessageString					= "registerShowListDefineDataException_MessageString";
	public static final String returnKenzouConfirm_MessageString									= "returnKenzouConfirm_MessageString";
	public static final String searchWhere_MessageString											= "searchWhere_MessageString";
	public static final String searchNotFound_MessageString										= "searchNotFound_MessageString";
	public static final String updateOK_MessageString												= "updateOK_MessageString";
	public static final String checkListFormKensaType_MessageString                               = "checkListFormKensaType_MessageString";
	public static final String sortItemIDNull_MessageString										= "sortItemIDNull_MessageString";
	public static final String sortOrderNull_MessageString										= "sortOrderNull_MessageString";
	public static final String sortDefaultOnly_MessageString										= "sortDefaultOnly_MessageString";
	public static final String sortDeleteDefault_MessageString									= "sortDeleteDefault_MessageString";
	public static final String sortDelete_MessageString											= "sortDelete_MessageString";
	public static final String sortInsertException_MessageString									= "sortInsertException_MessageString";
	public static final String sortUpdateException_MessageString									= "sortUpdateException_MessageString";
	public static final String sortDeleteException_MessageString									= "sortDeleteException_MessageString";
	public static final String operationPreCheckRegister_MessageString							= "operationPreCheckRegister_MessageString";
	public static final String updateExamDailyException_MessageString								= "updateExamDailyException_MessageString";
	public static final String operationPreCheckPrint_MessageString								= "operationPreCheckPrint_MessageString";
	public static final String orderDetailInfoOrderBuiToolTip_MessageString						= "orderDetailInfoOrderBuiToolTip_MessageString";
	public static final String listPreviewTitle_MessageString										= "listPreviewTitle_MessageString";
	public static final String listPreviewValue1_MessageString									= "listPreviewValue1_MessageString";
	public static final String listPreviewValue2_MessageString									= "listPreviewValue2_MessageString";
	public static final String mustExamItemCheckNG_MessageString									= "mustExamItemCheckNG_MessageString";
	public static final String examRoomMachineUpdateStatusLabel1_MessageString					= "examRoomMachineUpdateStatusLabel1_MessageString"; // 2010.07.30 Add Y.Shibata
	public static final String examRoomMachineUpdateStatusLabel2_MessageString					= "examRoomMachineUpdateStatusLabel2_MessageString"; // 2010.07.30 Add Y.Shibata
	public static final String todayZoueizaiStopRegist_MessageString								= "todayZoueizaiStopRegist_MessageString"; // 2010.07.30 Add Y.Shibata
	// 2010.07.30 Add T.Ootsuka Start
	public static final String changeTeamInfoOK_MessageString										= "changeTeamInfoOK_MessageString";
	public static final String insertTeamInfoWorningData_MessageString							= "insertTeamInfoWorningData_MessageString";
	public static final String insertTeamInfoOver_MessageString									= "insertTeamInfoOver_MessageString";
	// 2010.07.30 Add T.Ootsuka End
	public static final String csvOutputFailed_MessageString										= "csvOutputFailed_MessageString";
	public static final String orderNotSelect_MessageString										= "orderNotSelect_MessageString";
	// 2010.08.30 Add K.Shinohara Start
	public static final String kenzouStartExceptionStatus_MessageString							= "kenzouStartExceptionStatus_MessageString";
	// 2010.08.30 Add K.Shinohara End
	// 2010.09.01 Add K.Shinohara Start
	public static final String orderDeleted_MessageString											= "orderDeleted_MessageString";
	// 2010.09.01 Add K.Shinohara End
	public static final String updateRisResultPatientData_MessageString							= "updateRisResultPatientData_MessageString";
	public static final String updateRisResultPatientDataException_MessageString					= "updateRisResultPatientDataException_MessageString";
	public static final String addLoginUserToTeamInfo_MessageString								= "addLoginUserToTeamInfo_MessageString";
	public static final String addLoginUserToTeamInfoKango_MessageString							= "addLoginUserToTeamInfoKango_MessageString";
	public static final String teamKensaGisiNotFound_MessageString								= "teamKensaGisiNotFound_MessageString";
	// 2010.09.22 Add K.Shinohara Start
	public static final String douishoCheckException_MessageString								= "douishoCheckException_MessageString";
	// 2010.09.22 Add K.Shinohara End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-7
	public static final String patientIdNotMatch_MessageString									= "patientIdNotMatch_MessageString";
	// Add T.Nishikubo End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	public static final String updatePreparingNG_MessageString 									= "updatePreparingNG_MessageString";
	public static final String updatePreparingException_MessageString 							= "updatePreparingException_MessageString";
	public static final String updateLiftPreparingNG_MessageString 								= "updateLiftPreparingNG_MessageString";
	public static final String updateLiftPreparingException_MessageString 						= "updateLiftPreparingException_MessageString";
	public static final String updateLiftPreparingOther_MessageString 							= "updateLiftPreparingOther_MessageString";
	// 2010.11.16 Add T.Nishikubo End
	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	public static final String registerPatientSideEffectInfoException_MessageString				= "registerPatientSideEffectInfoException_MessageString";
	public static final String updatePatientSideEffectInfoException_MessageString					= "updatePatientSideEffectInfoException_MessageString";
	public static final String deletePatientSideEffectInfoException_MessageString					= "deletePatientSideEffectInfoException_MessageString";
	// 2011.03.10 Add CIJ_R.Aoyama Merge  End  MY-1-10
	// 2011.03.14 Add CIJ_R.Aoyama Merge Start MY-1-11
	public static final String operationKensaStartProcException_MessageString						= "operationKensaStartProcException_MessageString";
	// 2011.03.14 Add CIJ_R.Aoyama Merge  End  MY-1-11

	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	public static final String unParameterWarning_MessageString									= "unParameterWarning_MessageString";
	public static final String infoTitleNullDataUID_MessageString									= "infoTitleNullDataUID_MessageString";
	public static final String infoTitleNGGetCheckoutInfo_MessageString							= "infoTitleNGGetCheckoutInfo_MessageString";
	public static final String infoTitleRequestParameter_MessageString							= "infoTitleRequestParameter_MessageString";
	public static final String checkout_Error_Message_Format_MessageString						= "checkout_Error_Message_Format_MessageString";
	public static final String debugSectionDoctorCount_MessageString								= "debugSectionDoctorCount_MessageString";
	public static final String debugSectionTechnicianCount_MessageString							= "debugSectionTechnicianCount_MessageString";
	public static final String debugSectionNurseCount_MessageString								= "debugSectionNurseCount_MessageString";
	public static final String debugSectionOtherCount_MessageString								= "debugSectionOtherCount_MessageString";
	public static final String closeCheck_MessageString											= "closeCheck_MessageString";
	// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
	public static final String receiptDate_MessageString											= "receiptDate_MessageString";
	// 2011.07.11 Add NSK_H.Hashimoto End
	// 2011.07.28 Add NSK_T.Koudate Start NML-CAT2-004
	public static final String updateExamDirectorForOperatorCheck_MessageString					= "updateExamDirectorForOperatorCheck_MessageString";
	public static final String nonRegisterExamDirectorCheck_MessageString							= "nonRegisterExamDirectorCheck_MessageString";
	public static final String setIndexMedPersonOutOfRangeException_MessageString					= "setIndexMedPersonOutOfRangeException_MessageString";
	public static final String requireInputPassword_MessageString									= "requireInputPassword_MessageString";
	// 2011.07.28 Add NSK_T.Koudate End
	// 2011.08.02 Add NSK_T.Wada Start NML-CAT2-001
	public static final String linkPathNotFound_MessageString										= "linkPathNotFound_MessageString";
	// 2011.08.02 Add NSK_T.Wada End
	// 2011.11.25 Add NSK_T.Wada	Start OMH-1-9
	public static final String registerCloseAppointSearchException_MessageString					= "registerCloseAppointSearchException_MessageString";
	public static final String closeAppointAddWarning_MessageString 								= "closeAppointAddWarning_MessageString";
	public static final String closeAppointReceiptWarning_MessageString 							= "closeAppointReceiptWarning_MessageString";
	// 2011.11.25 Add NSK_T.Wada End

	//コミュニケーションメッセージ情報
	public static final String infoTitleNullCommunicationMessageID_MessageString					= "infoTitleNullCommunicationMessageID_MessageString";
	public static final String infoTitleNullCommunicationMessage_MessageString					= "infoTitleNullCommunicationMessage_MessageString";
	public static final String infoTitleNullCommunicationMsgSearchParameter_MessageString			= "infoTitleNullCommunicationMsgSearchParameter_MessageString";
	public static final String infoTitleOKCommunicationMsgInfoBean_MessageString					= "infoTitleOKCommunicationMsgInfoBean_MessageString";
	public static final String infoTitleNullCommunicationMsgInfoBean_MessageString				= "infoTitleNullCommunicationMsgInfoBean_MessageString";
	public static final String infoTitleOKRegisterCommunicationMsgInfo_MessageString				= "infoTitleOKRegisterCommunicationMsgInfo_MessageString";
	public static final String infoTitleNGRegisterCommunicationMsgInfo_MessageString				= "infoTitleNGRegisterCommunicationMsgInfo_MessageString";
	public static final String infoTitleOKRegisterCommunicationAllMsgInfo_MessageString			= "infoTitleOKRegisterCommunicationAllMsgInfo_MessageString";
	public static final String infoTitleNGRegisterCommunicationAllMsgInfo_MessageString			= "infoTitleNGRegisterCommunicationAllMsgInfo_MessageString";
	public static final String infoTitleOKRegisterCommunicationTerminalMemo_MessageString			= "infoTitleOKRegisterCommunicationTerminalMemo_MessageString";
	public static final String infoTitleNGRegisterCommunicationTerminalMemo_MessageString			= "infoTitleNGRegisterCommunicationTerminalMemo_MessageString";
	public static final String infoTitleOKCheckinCommunicationMsgInfo_MessageString				= "infoTitleOKCheckinCommunicationMsgInfo_MessageString";
	public static final String infoTitleNGCheckinCommunicationMsgInfo_MessageString				= "infoTitleNGCheckinCommunicationMsgInfo_MessageString";
	public static final String infoTitleOKCheckinCommunicationAllMsgInfo_MessageString			= "infoTitleOKCheckinCommunicationAllMsgInfo_MessageString";
	public static final String infoTitleNGCheckinCommunicationAllMsgInfo_MessageString			= "infoTitleNGCheckinCommunicationAllMsgInfo_MessageString";
	public static final String infoTitleOKUpdateCommunicationSendtoStatusChecked_MessageString	= "infoTitleOKUpdateCommunicationSendtoStatusChecked_MessageString";
	public static final String infoTitleNGUpdateCommunicationSendtoStatusChecked_MessageString	= "infoTitleNGUpdateCommunicationSendtoStatusChecked_MessageString";
	public static final String infoTitleOKDeleteCommunicationMsgInfo_MessageString				= "infoTitleOKDeleteCommunicationMsgInfo_MessageString";
	public static final String infoTitleNGDeleteCommunicationMsgInfo_MessageString				= "infoTitleNGDeleteCommunicationMsgInfo_MessageString";
	public static final String infoTitleOKGetUserCommunicationMsgList_MessageString				= "infoTitleOKGetUserCommunicationMsgList_MessageString";
	public static final String infoTitleNGGetUserCommunicationMsgList_MessageString				= "infoTitleNGGetUserCommunicationMsgList_MessageString";
	public static final String infoTitleOKGetUserEntryCommunicationMsgList_MessageString			= "infoTitleOKGetUserEntryCommunicationMsgList_MessageString";
	public static final String infoTitleNGGetUserEntryCommunicationMsgList_MessageString			= "infoTitleNGGetUserEntryCommunicationMsgList_MessageString";
	public static final String infoTitleOKGetSerialCommunicationMsgList_MessageString				= "infoTitleOKGetSerialCommunicationMsgList_MessageString";
	public static final String infoTitleNGGetSerialCommunicationMsgList_MessageString				= "infoTitleNGGetSerialCommunicationMsgList_MessageString";
	public static final String inputSearchCommunicationMsgList_MessageString						= "inputSearchCommunicationMsgList_MessageString";
	public static final String checkoutCommunicationMsgException_MessageString					= "checkoutCommunicationMsgException_MessageString";
	public static final String updateCommunicationSendtoStatusException_MessageString				= "updateCommunicationSendtoStatusException_MessageString";
	public static final String deleteCommunicationMsgException_MessageString						= "deleteCommunicationMsgException_MessageString";
	public static final String registerCommunicationMsgException_MessageString					= "registerCommunicationMsgException_MessageString";
	public static final String checkinCommunicationMsgException_MessageString						= "checkinCommunicationMsgException_MessageString";
	public static final String checkoutCommunicationAllMsgException_MessageString					= "checkoutCommunicationAllMsgException_MessageString";
	public static final String uncheckoutCommunicationAllMsgException_MessageString				= "uncheckoutCommunicationAllMsgException_MessageString";
	public static final String registerCommunicationAllMsgException_MessageString					= "registerCommunicationAllMsgException_MessageString";
	public static final String checkinCommunicationAllMsgException_MessageString					= "checkinCommunicationAllMsgException_MessageString";
	public static final String deleteCommunicationMsgCheck_MessageString							= "deleteCommunicationMsgCheck_MessageString";
	public static final String updateCommunicationMsgCheck_MessageString							= "updateCommunicationMsgCheck_MessageString";
	public static final String nonRegisterCommunicationMsgCloseCheck_MessageString				= "nonRegisterCommunicationMsgCloseCheck_MessageString";
	public static final String inputCommunicationMsgSendtoCheckSize_MessageString					= "inputCommunicationMsgSendtoCheckSize_MessageString";
	public static final String inputCommunicationMsgTitleCheck_MessageString						= "inputCommunicationMsgTitleCheck_MessageString";
	public static final String inputCommunicationMsgMessageCheck_MessageString					= "inputCommunicationMsgMessageCheck_MessageString";
	public static final String deleteCommunicationMsg_MessageString								= "deleteCommunicationMsg_MessageString";
	public static final String registerCommunicationTerminalMemoException_MessageString			= "registerCommunicationTerminalMemoException_MessageString";
	public static final String updateCommunicationTerminalMemoException_MessageString				= "updateCommunicationTerminalMemoException_MessageString";
	public static final String checkoutCommunicationTerminalMemoException_MessageString			= "checkoutCommunicationTerminalMemoException_MessageString";
	public static final String inputCommunicationMsgUserCheck_MessageString						= "inputCommunicationMsgUserCheck_MessageString";	// 2011.02.25 Add T.Nishikubo
	// 2011.02.28 Add T.Nishikubo Start
	public static final String nonRegisterAllMsgCloseCheck_MessageString							= "nonRegisterAllMsgCloseCheck_MessageString";
	public static final String nonRegisterTerminalMemoCloseCheck_MessageString					= "nonRegisterTerminalMemoCloseCheck_MessageString";
	// 2011.02.28 Add T.Nishikubo End
	// 2011.02.16 Add T.Nishikubo End
	// 2011.06.27 Add Yk.Suzuki@CIJ Start NML-CAT2-023
	public static final String checkUpdateSijiIsiID_MessageString									= "checkUpdateSijiIsiID_MessageString";
	// 2011.06.27 Add Yk.Suzuki@CIJ End



	// MWM

	public static final String mustOrderInfoPatientInfo_MessageString					= "mustOrderInfoPatientInfo_MessageString";
	public static final String getMWMConnectionInfoError_MessageString 				= "getMWMConnectionInfoError_MessageString";
	public static final String registerWorkListInfoException_MessageString 			= "registerWorkListInfoException_MessageString";
	public static final String deleteWorkListInfoException_MessageString 				= "deleteWorkListInfoException_MessageString";
	public static final String mwmMultiKensatypeIDWarning_MessageString 				= "mwmMultiKensatypeIDWarning_MessageString";
	public static final String registerMWMInfoKikiIDException_MessageString 			= "registerMWMInfoKikiIDException_MessageString";
	public static final String registerMWMInfoMWMTypeErrorException_MessageString		= "registerMWMInfoMWMTypeErrorException_MessageString";
	public static final String registerMWMInfoMWMTypeException_MessageString			= "registerMWMInfoMWMTypeException_MessageString";
	public static final String registerMWMInfoAeException_MessageString				= "registerMWMInfoAeException_MessageString";
	public static final String registerMWMInfoAeTitleMWMException_MessageString		= "registerMWMInfoAeTitleMWMException_MessageString";
	public static final String registerMWMInfoModalityTypeException_MessageString		= "registerMWMInfoModalityTypeException_MessageString";
	public static final String checkMWMPatientNameRomaException_MessageString			= "checkMWMPatientNameRomaException_MessageString";
	public static final String checkMWMPatientNameKanjiException_MessageString		= "checkMWMPatientNameKanjiException_MessageString";
	public static final String checkMWMPatientNameKanaException_MessageString			= "checkMWMPatientNameKanaException_MessageString";
	public static final String checkMWMPatientIDWarning_MessageString					= "checkMWMPatientIDWarning_MessageString";
	public static final String checkMWMDoctorEnglishNameWarning_MessageString			= "checkMWMDoctorEnglishNameWarning_MessageString";
	public static final String checkMWMSectionEnglishNameWarning_MessageString		= "checkMWMSectionEnglishNameWarning_MessageString";
	public static final String questionMWMPatientIDWarning_MessageString				= "questionMWMPatientIDWarning_MessageString";
	public static final String cancelMWMPatientIDWarning_MessageString				= "cancelMWMPatientIDWarning_MessageString";
	public static final String checkMWMEndException_MessageString						= "checkMWMEndException_MessageString";
	public static final String checkKenzouMWMAETitleException_MessageString			= "checkKenzouMWMAETitleException_MessageString";
	// 2010.09.15 Add K.Shinohara Start
	public static final String registerMWMSatueiDataNullException_MessageString		= "registerMWMSatueiDataNullException_MessageString";
	public static final String registerMWMBuiDataNullException_MessageString			= "registerMWMBuiDataNullException_MessageString";
	// 2010.09.15 Add K.Shinohara End
	// 2010.09.16 Add K.Shinohara Start
	public static final String mwmOrderNotSelect_MessageString						= "mwmOrderNotSelect_MessageString";
	public static final String lumpMWMMultiKensaTypeSelect_MessageString				= "lumpMWMMultiKensaTypeSelect_MessageString";
	public static final String lumpMWMSend_MessageString								= "lumpMWMSend_MessageString";
	public static final String lumpMWMMultiSelect_MessageString						= "lumpMWMMultiSelect_MessageString";
	// 2010.09.16 Add K.Shinohara End

	//トリガ
	public static final String tcpClientConnectException_MessageString				= "tcpClientConnectException_MessageString";
	public static final String tcpClientException_MessageString						= "tcpClientException_MessageString";
	public static final String workListSendSuccess_MessageString						= "workListSendSuccess_MessageString";
	public static final String workListSendError_MessageString						= "workListSendError_MessageString";



	// MPPS

	//MPPS-検査
	public static final String mppsKensaGetMppsException_MessageString			= "mppsKensaGetMppsException_MessageString";
	public static final String mppsKensaGetMppsSateiException_MessageString		= "mppsKensaGetMppsSateiException_MessageString";
	public static final String mppsKensaNotAETitle_MessageString					= "mppsKensaNotAETitle_MessageString";
	public static final String mppsKensaNotAll_MessageString						= "mppsKensaNotAll_MessageString";
	public static final String mppsKensaNotBui_MessageString						= "mppsKensaNotBui_MessageString";
	public static final String mppsKensaNotSatei_MessageString					= "mppsKensaNotSatei_MessageString";
	public static final String mppsKensaOverride_MessageString					= "mppsKensaOverride_MessageString";

	//MPPS-撮影
	public static final String mppsPhotoGetMppsException_MessageString			= "mppsPhotoGetMppsException_MessageString";
	public static final String mppsPhotoDataNotFound_MessageString				= "mppsPhotoDataNotFound_MessageString";
	public static final String mppsPhotoGetDataException_MessageString			= "mppsPhotoGetDataException_MessageString";
	public static final String mppsPhotoStop_MessageString						= "mppsPhotoStop_MessageString";
	public static final String mppsPhotoInfoGettingData_MessageString				= "mppsPhotoInfoGettingData_MessageString";
	public static final String mppsPhotoInfoEnd_MessageString						= "mppsPhotoInfoEnd_MessageString";
	public static final String mppsPhotoInfoNotFound_MessageString				= "mppsPhotoInfoNotFound_MessageString";
	public static final String mppsPhotoInfoException_MessageString				= "mppsPhotoInfoException_MessageString";
	public static final String mppsPhotoInfoStop_MessageString					= "mppsPhotoInfoStop_MessageString";
	public static final String mppsPhotoInfoCount_MessageString					= "mppsPhotoInfoCount_MessageString";

	public static final String mppsPhotoGetResultDataException_MessageString		= "mppsPhotoGetResultDataException_MessageString";
	public static final String mppsPhotoGetResultCount_MessageString				= "mppsPhotoGetResultCount_MessageString";
	public static final String mppsPhotoMatchingStart_MessageString				= "mppsPhotoMatchingStart_MessageString";
	public static final String mppsPhotoMatchingEnd_MessageString					= "mppsPhotoMatchingEnd_MessageString";
	public static final String mppsPhotoMatchingStartMi_MessageString				= "mppsPhotoMatchingStartMi_MessageString";
	public static final String mppsPhotoMatchingEndMi_MessageString				= "mppsPhotoMatchingEndMi_MessageString";
	public static final String mppsPhotoMatchingException_MessageString			= "mppsPhotoMatchingException_MessageString";
	public static final String mppsPhotoMatchingSumi_MessageString				= "mppsPhotoMatchingSumi_MessageString";
	public static final String mppsPhotoMatchingSatueiCodeNoMatch_MessageString	= "mppsPhotoMatchingSatueiCodeNoMatch_MessageString";
	public static final String mppsPhotoMatchingFix_MessageString					= "mppsPhotoMatchingFix_MessageString";
	public static final String mppsPhotoMatchingNoMatch_MessageString				= "mppsPhotoMatchingNoMatch_MessageString";
	public static final String mppsPhotoInfoReGet_MessageString					= "mppsPhotoInfoReGet_MessageString";
	public static final String mppsPhotoInfoReStart_MessageString					= "mppsPhotoInfoReStart_MessageString";
	public static final String mppsPhotoInfoNotSave_MessageString					= "mppsPhotoInfoNotSave_MessageString";



	// 画像情報

	public static final String viewerIPAddressException_MessageString				= "viewerIPAddressException_MessageString";
	public static final String viewerSendTextException_MessageString				= "viewerSendTextException_MessageString";
	public static final String viewerSendStartException_MessageString				= "viewerSendStartException_MessageString";
	public static final String viewerRecvStartException_MessageString				= "viewerRecvStartException_MessageString";
	public static final String viewerThreadCloseException_MessageString			= "viewerThreadCloseException_MessageString";
	public static final String viewerError_MessageString							= "viewerError_MessageString";
	public static final String viewerGetShotCountKindError_MessageString			= "viewerGetShotCountKindError_MessageString";
	public static final String viewerGetShotCountAccError_MessageString			= "viewerGetShotCountAccError_MessageString";
	public static final String viewerGetShotCountNumError_MessageString			= "viewerGetShotCountNumError_MessageString";
	public static final String viewerSendGetKenzouMaisuException_MessageString	= "viewerSendGetKenzouMaisuException_MessageString";
	public static final String viewerRecvGetKenzouMaisuException_MessageString	= "viewerRecvGetKenzouMaisuException_MessageString";
	public static final String viewerTitleQuery_MessageString						= "viewerTitleQuery_MessageString";
	public static final String viewerTitleEnd_MessageString						= "viewerTitleEnd_MessageString";
	public static final String viewerSendKenzouStart_MessageString				= "viewerSendKenzouStart_MessageString";
	public static final String viewerSendKenzouStartError_MessageString			= "viewerSendKenzouStartError_MessageString";
	public static final String viewerSendKenzouStartException_MessageString		= "viewerSendKenzouStartException_MessageString";
	public static final String viewerSendKenzouProc_MessageString					= "viewerSendKenzouProc_MessageString";
	public static final String viewerSendKenzouProcException_MessageString		= "viewerSendKenzouProcException_MessageString";
	public static final String viewerSendKenzouCancel_MessageString				= "viewerSendKenzouCancel_MessageString";
	public static final String viewerSendKenzouCanceled_MessageString				= "viewerSendKenzouCanceled_MessageString";
	public static final String viewerSendKenzouCancelException_MessageString		= "viewerSendKenzouCancelException_MessageString";
	public static final String viewerSendKenzouCancelOK_MessageString				= "viewerSendKenzouCancelOK_MessageString";
	public static final String viewerKenzouEnd_MessageString						= "viewerKenzouEnd_MessageString";
	public static final String viewerKenzouCancel_MessageString					= "viewerKenzouCancel_MessageString";
	public static final String viewerKenzouStop_MessageString						= "viewerKenzouStop_MessageString";
	public static final String viewerKenzouTimeout_MessageString 					= "viewerKenzouTimeout_MessageString";
	public static final String viewerRecvMessage_MessageString 					= "viewerRecvMessage_MessageString";
	public static final String viewerRecvCleanupMessage_MessageString 			= "viewerRecvCleanupMessage_MessageString";
	// 2010.09.11 Add K.Shinohara Start
	public static final String viewerKenzouNone_MessageString						= "viewerKenzouNone_MessageString";
	public static final String viewerKenzouActive_MessageString					= "viewerKenzouActive_MessageString";
	public static final String viewerKenzouErrorCode_MessageString				= "viewerKenzouErrorCode_MessageString";
	// 2010.09.11 Add K.Shinohara End



	// デバッグ用

	public static final String infoTitleString_MessageString									= "infoTitleString_MessageString";
	public static final String infoTitleUserID_MessageString									= "infoTitleUserID_MessageString";
	public static final String infoTitleStartAutoLogoutTimer_MessageString					= "infoTitleStartAutoLogoutTimer_MessageString";
	public static final String infoTitleStopAutoLogoutTimer_MessageString						= "infoTitleStopAutoLogoutTimer_MessageString";
	public static final String infoTitleDataTableCount_MessageString							= "infoTitleDataTableCount_MessageString";
	public static final String infoTitleRisID_MessageString									= "infoTitleRisID_MessageString";
	public static final String infoTitleKanjaID_MessageString									= "infoTitleKanjaID_MessageString";
	public static final String infoTitleTableName_MessageString								= "infoTitleTableName_MessageString";
	public static final String infoTitleTemplateID_MessageString								= "infoTitleTemplateID_MessageString";
	public static final String infoTitleTerminalID_MessageString								= "infoTitleTerminalID_MessageString";
	public static final String infoTitleWindowAppID_MessageString								= "infoTitleWindowAppID_MessageString";
	public static final String infoTitleTreatReqAnswerMode_MessageString						= "infoTitleTreatReqAnswerMode_MessageString";
	public static final String infoTitleOrderCount_MessageString								= "infoTitleOrderCount_MessageString";
	public static final String infoTitleIPAddress_MessageString								= "infoTitleIPAddress_MessageString";
	public static final String infoTitleDate_MessageString									= "infoTitleDate_MessageString";
	public static final String infoTitleKensaTypeID_MessageString								= "infoTitleKensaTypeID_MessageString";
	public static final String infoTitleListType_MessageString								= "infoTitleListType_MessageString";
	public static final String infoTitleAccessionNo_MessageString								= "infoTitleAccessionNo_MessageString";
	public static final String infoTitleKensakikiID_MessageString								= "infoTitleKensakikiID_MessageString";
	public static final String infoTitleKensaroomID_MessageString								= "infoTitleKensaroomID_MessageString";
	public static final String infoTitleComment_MessageString									= "infoTitleComment_MessageString";
	public static final String infoTitleTypeIndex_MessageString								= "infoTitleTypeIndex_MessageString";
	public static final String infoTitleStudyID_MessageString									= "infoTitleStudyID_MessageString";
	public static final String infoTitleMPPSSOPInstanceUID_MessageString						= "infoTitleMPPSSOPInstanceUID_MessageString";
	public static final String infoTitleAETitle_MessageString									= "infoTitleAETitle_MessageString";
	public static final String infoTitlePort_MessageString									= "infoTitlePort_MessageString";
	public static final String infoTitleTimeOut_MessageString									= "infoTitleTimeOut_MessageString";
	public static final String infoTitleMessage_MessageString									= "infoTitleMessage_MessageString";
	public static final String infoTitleResponseMessage_MessageString							= "infoTitleResponseMessage_MessageString";
	public static final String infoTitleCleanupKenzouPort_MessageString						= "infoTitleCleanupKenzouPort_MessageString";
	public static final String infoTitlePatternID_MessageString								= "infoTitlePatternID_MessageString";
	public static final String infoTitleMainID_MessageString									= "infoTitleMainID_MessageString";
	public static final String infoTitleSubID_MessageString									= "infoTitleSubID_MessageString";
	public static final String infoTitleColumn_MessageString									= "infoTitleColumn_MessageString";
	public static final String infoTitleGyomuURL_MessageString								= "infoTitleGyomuURL_MessageString";
	public static final String infoTitleExDateStr_MessageString								= "infoTitleExDateStr_MessageString";
	//
	public static final String infoTitleOKLogin_MessageString 								= "infoTitleOKLogin_MessageString";
	public static final String infoTitleNGLogin_MessageString 								= "infoTitleNGLogin_MessageString";
	public static final String infoTitleOKRegisterAuditTrail_MessageString					= "infoTitleOKRegisterAuditTrail_MessageString";
	public static final String infoTitleNGRegisterAuditTrail_MessageString					= "infoTitleNGRegisterAuditTrail_MessageString";
	public static final String infoTitleOKChangeOrderStatus_MessageString						= "infoTitleOKChangeOrderStatus_MessageString";
	public static final String infoTitleNGChangeOrderStatus_MessageString						= "infoTitleNGChangeOrderStatus_MessageString";
	public static final String infoTitleOKCheckInCheckoutID_MessageString						= "infoTitleOKCheckInCheckoutID_MessageString";
	public static final String infoTitleOKUnCheckOutCount_MessageString						= "infoTitleOKUnCheckOutCount_MessageString";
	public static final String infoTitleOKCheckInDetaUID_MessageString						= "infoTitleOKCheckInDetaUID_MessageString";
	public static final String infoTitleOKUnCheckOutIP_MessageString							= "infoTitleOKUnCheckOutIP_MessageString";
	public static final String infoTitleOKRegisterTerminalData_MessageString					= "infoTitleOKRegisterTerminalData_MessageString";
	public static final String infoTitleNGRegisterTerminalData_MessageString					= "infoTitleNGRegisterTerminalData_MessageString";
	public static final String infoTitleOKUpdateSearchPatternInfo_MessageString				= "infoTitleOKUpdateSearchPatternInfo_MessageString";
	public static final String infoTitleNGUpdateSearchPatternInfo_MessageString				= "infoTitleNGUpdateSearchPatternInfo_MessageString";
	public static final String infoTitleOKRegisterShowItemDefineData_MessageString			= "infoTitleOKRegisterShowItemDefineData_MessageString";
	public static final String infoTitleNGRegisterShowItemDefineData_MessageString			= "infoTitleNGRegisterShowItemDefineData_MessageString";
	public static final String infoTitleOKRegisterOperationListItemDefineData_MessageString	= "infoTitleOKRegisterOperationListItemDefineData_MessageString";
	public static final String infoTitleNGRegisterOperationListItemDefineData_MessageString	= "infoTitleNGRegisterOperationListItemDefineData_MessageString";
	public static final String infoTitleOKRegisterRisExamOperationHistory_MessageString		= "infoTitleOKRegisterRisExamOperationHistory_MessageString";
	public static final String infoTitleNGRegisterRisExamOperationHistory_MessageString		= "infoTitleNGRegisterRisExamOperationHistory_MessageString";
	public static final String infoTitleOKSaveRisExecutionInfo_MessageString					= "infoTitleOKSaveRisExecutionInfo_MessageString";
	public static final String infoTitleNGSaveRisExecutionInfo_MessageString					= "infoTitleNGSaveRisExecutionInfo_MessageString";
	public static final String infoTitleOKCheckUserIDPassword_MessageString 					= "infoTitleOKCheckUserIDPassword_MessageString";
	public static final String infoTitleNGCheckUserIDPassword_MessageString 					= "infoTitleNGCheckUserIDPassword_MessageString";
	public static final String infoTitleOKUpdateRisSijiInfo_MessageString						= "infoTitleOKUpdateRisSijiInfo_MessageString";
	public static final String infoTitleNGUpdateRisSijiInfo_MessageString						= "infoTitleNGUpdateRisSijiInfo_MessageString";
	public static final String infoTitleOKChangeRisOrderStatusNoCheckout_MessageString		= "infoTitleOKChangeRisOrderStatusNoCheckout_MessageString";
	public static final String infoTitleNGChangeRisOrderStatusNoCheckout_MessageString		= "infoTitleNGChangeRisOrderStatusNoCheckout_MessageString";
	public static final String infoTitleOKRegisterShowListDefineData_MessageString			= "infoTitleOKRegisterShowListDefineData_MessageString";
	public static final String infoTitleNGRegisterShowListDefineData_MessageString			= "infoTitleNGRegisterShowListDefineData_MessageString";
	public static final String infoTitleOKRegisterSortPatternInfo_MessageString				= "infoTitleOKRegisterSortPatternInfo_MessageString";
	public static final String infoTitleNGRegisterSortPatternInfo_MessageString				= "infoTitleNGRegisterSortPatternInfo_MessageString";
	public static final String infoTitleOKUpdateSortPatternInfo_MessageString					= "infoTitleOKUpdateSortPatternInfo_MessageString";
	public static final String infoTitleNGUpdateSortPatternInfo_MessageString					= "infoTitleNGUpdateSortPatternInfo_MessageString";
	public static final String infoTitleOKDeleteSortPatternInfo_MessageString					= "infoTitleOKDeleteSortPatternInfo_MessageString";
	public static final String infoTitleNGDeleteSortPatternInfo_MessageString					= "infoTitleNGDeleteSortPatternInfo_MessageString";
	public static final String infoTitleOKUpdateSystemParam2Value_MessageString				= "infoTitleOKUpdateSystemParam2Value_MessageString";
	public static final String infoTitleNGUpdateSystemParam2Value_MessageString				= "infoTitleNGUpdateSystemParam2Value_MessageString";
	public static final String infoTitleOKRegisterExamDailyInfo_MessageString					= "infoTitleOKRegisterExamDailyInfo_MessageString";
	public static final String infoTitleNGRegisterExamDailyInfo_MessageString					= "infoTitleNGRegisterExamDailyInfo_MessageString";
	// 2010.07.30 Add T.Ootsuka Start
	public static final String infoTitleOKGetTeamInfo_MessageString							= "infoTitleOKGetTeamInfo_MessageString";
	public static final String infoTitleNGGetTeamInfo_MessageString							= "infoTitleNGGetTeamInfo_MessageString";
	public static final String infoTitleOKRegisterTeamInfo_MessageString						= "infoTitleOKRegisterTeamInfo_MessageString";
	public static final String infoTitleNGRegisterTeamInfo_MessageString						= "infoTitleNGRegisterTeamInfo_MessageString";
	public static final String infoTitleOKUpdateTeamInfo_MessageString						= "infoTitleOKUpdateTeamInfo_MessageString";
	public static final String infoTitleNGUpdateTeamInfo_MessageString						= "infoTitleNGUpdateTeamInfo_MessageString";
	// 2010.07.30 Add T.Ootsuka End
	public static final String infoTitleOKOutputCsv_MessageString								= "infoTitleOKOutputCsv_MessageString";
	public static final String infoTitleNGOutputCsv_MessageString								= "infoTitleNGOutputCsv_MessageString";
	//
	public static final String infoTitleNullUserID_MessageString								= "infoTitleNullUserID_MessageString";
	public static final String infoTitleNullString_MessageString								= "infoTitleNullString_MessageString";
	public static final String infoTitleNullPlanePassword_MessageString						= "infoTitleNullPlanePassword_MessageString";
	public static final String infoTitleNullPassword_MessageString							= "infoTitleNullPassword_MessageString";
	public static final String infoTitleNullAuditTrailInfoBean_MessageString					= "infoTitleNullAuditTrailInfoBean_MessageString";
	public static final String infoTitleNullRequestParameter_MessageString					= "infoTitleNullRequestParameter_MessageString";
	public static final String infoTitleNullOrderSearchParameter_MessageString				= "infoTitleNullOrderSearchParameter_MessageString";
	public static final String infoTitleNullUserSearchParameter_MessageString					= "infoTitleNullUserSearchParameter_MessageString";
	public static final String infoTitleNullPresetParameter_MessageString						= "infoTitleNullPresetParameter_MessageString";
	public static final String infoTitleNullPartsSearchParameter_MessageString				= "infoTitleNullPartsSearchParameter_MessageString";
	public static final String infoTitleNullDataTable_MessageString							= "infoTitleNullDataTable_MessageString";
	public static final String infoTitleNullDataRow_MessageString								= "infoTitleNullDataRow_MessageString";
	public static final String infoTitleNullAdmissionUser_MessageString						= "infoTitleNullAdmissionUser_MessageString";
	public static final String infoTitleNullReason_MessageString 								= "infoTitleNullReason_MessageString";
	public static final String infoTitleNullStatus_MessageString 								= "infoTitleNullStatus_MessageString";
	public static final String infoTitleNullRisID_MessageString								= "infoTitleNullRisID_MessageString";
	public static final String infoTitleNullCheckoutID_MessageString							= "infoTitleNullCheckoutID_MessageString";
	public static final String infoTitleNullOrderInfoBean_MessageString						= "infoTitleNullOrderInfoBean_MessageString";
	public static final String infoTitleNullOrderIndicateBean_MessageString					= "infoTitleNullOrderIndicateBean_MessageString";
	public static final String infoTitleNullExtendOrderInfoBean_MessageString					= "infoTitleNullExtendOrderInfoBean_MessageString";
	public static final String infoTitleNullExecutionInfoBean_MessageString					= "infoTitleNullExecutionInfoBean_MessageString";
	public static final String infoTitleNullExtendExamInfoBean_MessageString					= "infoTitleNullExtendExamInfoBean_MessageString";
	public static final String infoTitleNullBean_MessageString								= "infoTitleNullBean_MessageString";
	public static final String infoTitleNullAccessionNo_MessageString							= "infoTitleNullAccessionNo_MessageString";
	public static final String infoTitleNullKensakikiID_MessageString							= "infoTitleNullKensakikiID_MessageString";
	public static final String infoTitleNullTypeIndex_MessageString							= "infoTitleNullTypeIndex_MessageString";
	public static final String infoTitleNullPrintParameter_MessageString						= "infoTitleNullPrintParameter_MessageString";
	public static final String infoTitleNullPatientID_MessageString							= "infoTitleNullPatientID_MessageString";
	public static final String infoTitleNullPatientInfoBean_MessageString 					= "infoTitleNullPatientInfoBean_MessageString";
	public static final String infoTitleNullKensaTypeID_MessageString							= "infoTitleNullKensaTypeID_MessageString";
	public static final String infoTitleNullTerminalInfoBean_MessageString					= "infoTitleNullTerminalInfoBean_MessageString";
	public static final String infoTitleNullTerminalID_MessageString							= "infoTitleNullTerminalID_MessageString";
	public static final String infoTitleNullDate_MessageString								= "infoTitleNullDate_MessageString";
	public static final String infoTitleNullExamOperationHistory_MessageString				= "infoTitleNullExamOperationHistory_MessageString";
	public static final String infoTitleNullAccessInfoBean_MessageString						= "infoTitleNullAccessInfoBean_MessageString";
	public static final String infoTitleNullMppsType_MessageString							= "infoTitleNullMppsType_MessageString";
	public static final String infoTitleNullMPPSSOPInstanceUID_MessageString					= "infoTitleNullMPPSSOPInstanceUID_MessageString";
	public static final String infoTitleNullAETitle_MessageString								= "infoTitleNullAETitle_MessageString";
	public static final String infoTitleArrayListCount_MessageString							= "infoTitleArrayListCount_MessageString";
	public static final String infoTitleZeroArrayList_MessageString							= "infoTitleZeroArrayList_MessageString";
	public static final String infoTitleNullArrayList_MessageString							= "infoTitleNullArrayList_MessageString";
	public static final String infoTitleNullPatternID_MessageString							= "infoTitleNullPatternID_MessageString";
	public static final String infoTitleNullMainID_MessageString								= "infoTitleNullMainID_MessageString";
	public static final String infoTitleNullSubID_MessageString								= "infoTitleNullSubID_MessageString";
	public static final String infoTitleNullColumn_MessageString								= "infoTitleNullColumn_MessageString";
	public static final String infoTitleNullExamDailyInfoBean_MessageString					= "infoTitleNullExamDailyInfoBean_MessageString";
	public static final String infoTitleNullKensaRoom_MessageString							= "infoTitleNullKensaRoom_MessageString"; // 2010.07.30 Add Y.Shibata
	// 2010.07.30 Add T.Ootsuka Start
	public static final String infoTitleNullTeamInfo_MessageString							= "infoTitleNullTeamInfo_MessageString";
	public static final String infoTitleNullTeamInfoBean_MessageString						= "infoTitleNullTeamInfoBean_MessageString";
	// 2010.07.30 Add T.Ootsuka End
	public static final String infoTitleNullOutputCsvParameter_MessageString					= "infoTitleNullOutputCsvParameter_MessageString";

	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	public static final String infoTitleSideEffectID_MessageString							= "infoTitleSideEffectID_MessageString";
	public static final String infoTitlePatientSideEffectInfoCount_MessageString				= "infoTitlePatientSideEffectInfoCount_MessageString";
	public static final String infoTitleNullKanjaID_MessageString								= "infoTitleNullKanjaID_MessageString";
	public static final String infoTitleNullPatientSideEffectInfoBean_MessageString			= "infoTitleNullPatientSideEffectInfoBean_MessageString";
	public static final String infoTitleNullSideEffectID_MessageString						= "infoTitleNullSideEffectID_MessageString";
	public static final String infoTitleOKGetPatientSideEffectInfo_MessageString				= "infoTitleOKGetPatientSideEffectInfo_MessageString";
	public static final String infoTitleNGGetPatientSideEffectInfo_MessageString				= "infoTitleNGGetPatientSideEffectInfo_MessageString";
	public static final String infoTitleOKRegisterPatientSideEffectInfo_MessageString			= "infoTitleOKRegisterPatientSideEffectInfo_MessageString";
	public static final String infoTitleNGRegisterPatientSideEffectInfo_MessageString			= "infoTitleNGRegisterPatientSideEffectInfo_MessageString";
	public static final String infoTitleOKUpdatePatientSideEffectInfo_MessageString			= "infoTitleOKUpdatePatientSideEffectInfo_MessageString";
	public static final String infoTitleNGUpdatePatientSideEffectInfo_MessageString			= "infoTitleNGUpdatePatientSideEffectInfo_MessageString";
	// 2011.03.10 Add CIJ_R.Aoyama Merge  End  MY-1-10
	// 2011.11.25 Add NSK_T.Wada	Start OMH-1-9
	public static final String infoTitleNullCloseAppointSearch_MessageString					= "infoTitleNullCloseAppointSearch_MessageString";
	public static final String infoTitleOKRegisterCloseAppointSearch_MessageString			= "infoTitleOKRegisterCloseAppointSearch_MessageString";
	public static final String infoTitleNGRegisterCloseAppointSearch_MessageString			= "infoTitleNGRegisterCloseAppointSearch_MessageString";
	public static final String infoTitleOKUpdateCloseAppointSearch_MessageString				= "infoTitleOKUpdateCloseAppointSearch_MessageString";
	public static final String infoTitleNGUpdateCloseAppointSearch_MessageString				= "infoTitleNGUpdateCloseAppointSearch_MessageString";
	// 2011.11.25 Add NSK_W.Wada	End
}
