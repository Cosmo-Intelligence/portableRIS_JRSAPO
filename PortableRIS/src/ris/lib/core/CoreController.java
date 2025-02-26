package ris.lib.core;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.bean.AccessInfoBean;
import ris.lib.core.bean.ExamOperationHistoryInfoBean;
import ris.lib.core.bean.ExecutionInfoBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.PatientInfoBean;
import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.util.OrderSearchParameter;
import ris.lib.core.util.PresetParameter;
import ris.lib.core.util.RequestParameter;
import ris.lib.core.util.UserSearchParameter;
import ris.lib.mwm.bean.ConnectionInfoBean;
import ris.portable.model.dto.BaseDto;
import ris.portable.model.dto.StatusUpdateDto;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
///
/// コア管理インターフェースクラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)				(Comment)
/// V1.00.00		: 2009.02.18	: 112478 / A.Kobayashi	: original
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo	: KANRO-R-9
/// V2.04.00		: 2011.02.16	: 999999 / T.Nishikubo	: KANRO-R-27
/// V2.01.00		: 2011.07.19	: 999999 / NSK_T.Wada	: NML-CAT2-001
/// V2.01.00		: 2011.08.03	: 999999 / NSK_S.Imai	: NML-CAT2-029
/// V2.01.00		: 2011.08.11	: 999999 / NSK_T.Koudate: NML-CAT2-004
/// V2.01.00		: 2011.08.16	: 999999 / DD.Chinh		: NML-CAT2-010
/// V2.01.01.13000	: 2011.11.21	: 999999 / NSK_M.Ochiai extends OMH-1-7
/// V2.01.01.13000	: 2011.11.22	: 999999 / NSK_T.Wada	: OMH-1-9
///
/// </summary>
public abstract class CoreController
{
	public static Log logger = LogFactory.getLog(CoreController.class);

	/// <summary>
	/// 外部メッセージテーブルを設定する
	/// </summary>
	/// <param name="val">外部メッセージテーブル</param>
	public abstract void SetExternalMessageTableImpl(Hashtable val);

	/// <summary>
	/// メッセージを取得する
	/// </summary>
	/// <param name="key">取得キー</param>
	/// <returns>メッセージ</returns>
	public abstract String GetMessageStringImpl(String key);

	/// <summary>
	/// 共通情報をDBから取得する
	/// </summary>
//	public abstract CommonParameterBean GetCommonParameters();

	/// <summary>
	/// テンプレート情報をDBから取得する
	/// </summary>
//	public abstract DataTable TelplateGroupMaster(String groupCode);

	/// <summary>
	/// RIS接続確認
	/// </summary>
	/// <returns>true:成功 false:失敗</returns>
//	public abstract boolean GetRISDBConnection();

	///// <summary>
	///// 使用済みのRIS用データベースコネクションを返却する
	///// </summary>
//	public abstract void ReturnRisDBConnection();

	/// <summary>
	/// ログインする
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="planePassword">パスワード</param>
	/// <returns>判定</returns>
//	public abstract boolean Login( String userID, String planePassword );

	/// <summary>
	/// ログインする
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <returns>判定</returns>
//	public abstract boolean Login( String userID );

	/// <summary>
	/// 監査認証情報を取得、設定する
	/// </summary>
	/// <param name="currentType">ｻｰﾊﾞ種別</param>
	/// <returns></returns>
//	public abstract boolean GetCurrentConfirmAuditDefine(String currentType);

	/// <summary>
	/// 監査証跡を登録する
	/// </summary>
	/// <param name="auditBean">監査証跡情報</param>
	/// <returns>判定</returns>
//	public abstract boolean RegisterAuditTrail( AuditTrailInfoBean auditBean );

    /// <summary>
    /// SQ/Servにあるユーザ情報をローカルにダウンロードする
    /// </summary>
    /// <returns></returns>
//    public abstract boolean EqualizeUserInfo(UserAccountBean userBean);

	/// <summary>
	/// RIS用マスターデータをDBから収集する
	/// </summary>
//	public abstract void CollectRisMasterInfo();

	/// <summary>
	/// RIS用マスターデータを取得する
	/// </summary>
	/// <returns>マスターデータ</returns>
//	public abstract DataSet GetRisMasterInfo();

	/// <summary>
	/// Ris全データをアンチェックアウトする
	/// </summary>
	/// <returns></returns>
//	public abstract void UncheckoutRisAllData();

	/// <summary>
	/// チェックアウト情報を取得する
	/// </summary>
	/// <param name="risID">RisID</param>
	/// <returns>チェックアウト情報</returns>
	public abstract DataRow GetCheckoutData(String risID, Connection con);

    /// <summary>
    /// チェックアウト情報を取得する
    /// </summary>
    /// <remarks></remarks>
    /// <param name="kanjaIDStr">患者ID</param>
    /// <param name="kensaDateStr">検査日</param>
    /// <param name="risIdStr">対象外のRIS_ID（自オーダ）</param>
    /// <returns>判定</returns>
//	public abstract String GetCheckoutData(String kanjaIDStr, String kensaDateStr, String risIdStr);

	// 2010.09.10 Add K.Shinohara Start
	/// <summary>
	/// チェックアウト情報を取得する(オーダキャンセル用)
	/// </summary>
	/// <param name="accessBean">AccessInfoBean</param>
	/// <returns>チェックアウト情報</returns>
//	public abstract DataRow GetCheckoutData_OrderCancel(AccessInfoBean accessBean, String ownerAppID);
	// 2010.09.10 Add K.Shinohara End

	/// <summary>
	/// システム設定情報をDBから取得する
	/// </summary>
	public abstract DataTable GetRisSystemDefine(Connection con);

    /// <summary>
    /// システム情報をDBから取得する
    /// </summary>
	public abstract DataTable GetRisSystemParam(Connection con);

	/// <summary>
	/// チェックアウトを解除する
	/// </summary>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns>判定</returns>
	public abstract boolean UncheckoutRisData(String checkoutID, Connection con) throws Exception;

	// 2019.10.03 Add Cosmo Start 排他ロック対応
	/// <summary>
	/// チェックアウトを解除する
	/// </summary>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns>判定</returns>
	public abstract boolean UncheckoutunLoginLockRisData(String ipAddress,String appID, Connection con) throws Exception;

	// 2019.10.03 Add Cosmo End 排他ロック対応

	// 2010.09.09 Add K.Shinohara Start
	/// <summary>
	/// チェックアウトを解除する
	/// </summary>
	/// <param name="bean">アクセス情報</param>
	/// <returns>判定</returns>
//	public abstract boolean UncheckoutRisData(AccessInfoBean bean);
	// 2010.09.09 Add K.Shinohara End

	/// <summary>
	/// 登録済みのRisオーダ一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
	public abstract DataTable GetRisOrderList( OrderSearchParameter param, Connection con );

	/// <summary>
	/// 登録済みのRisオーダ一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
//	public abstract ArrayList GetRisOrderList(ArrayList risIDArrayList);

    /// <summary>
    /// 登録済みのRisオーダ部位一覧を取得する
    /// </summary>
    /// <returns>オーダ部位一覧</returns>
    public abstract DataTable GetRisOrderBuiList(OrderSearchParameter param, Connection con);

	/// <summary>
	/// Risに登録済みの患者情報を取得する
	/// </summary>
	/// <returns>患者情報</returns>
	public abstract PatientInfoBean GetRisPatientInfo(String patientID, Connection con);

	/// <summary>
	/// 登録済みの患者一覧情報を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
//	public abstract DataTable GetRisPatientInfo(PatientInfoBean bean);

	/// <summary>
	/// 登録済みの実績患者情報を取得する
	/// </summary>
	/// <returns>患者情報</returns>
	public abstract PatientInfoBean GetRisResultPatientInfo( String risID, String patientID, Connection con );

	/// <summary>
	/// 当該オーダを編集するためにcheckoutする
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">チェックアウト情報</param>
	/// <returns>他ユーザによって編集中等でcheckoutできない場合は空文字を返す
	/// checkoutに成功した場合はCheckoutIDを返す</returns>
	public abstract String CheckoutRisOrder(AccessInfoBean bean, Connection con);

	// 2010.09.10 Add K.Shinohara Start
	/// <summary>
	/// 当該オーダを編集するためにcheckoutする(オーダキャンセル用)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">チェックアウト情報</param>
	/// <param name="ownerAppID">親画面ID</param>
	/// <returns>他ユーザによって編集中等でcheckoutできない場合は空文字を返す
	/// checkoutに成功した場合はCheckoutIDを返す</returns>
//	public abstract String CheckoutRisOrder_OrderCancel(AccessInfoBean bean, String ownerAppID);
	// 2010.09.10 Add K.Shinohara End

	/// <summary>
	/// 指定された実績情報（引数のrisID）を取得する
	/// </summary>
	/// <param name="risID">取得対象の実績情報のRISID</param>
	/// <param name="satueiFinishFlg">撮影済ボタンフラグ(1：撮影済ボタン押下時動作、0：それ以外の動作)</param>
	/// <returns>実績情報</returns>
	// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
	public abstract ExecutionInfoBean GetRisExecutionInfo( String risID, int satueiFinishFlg, Connection con );
//	public abstract ExecutionInfoBean GetRisExecutionInfo( String risID, Connection con );
	// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加

    /// <summary>
    /// スタータスマスタを取得する
    /// </summary>
    /// <returns>DataTable</returns>
//    public abstract DataTable GetStatusDefine();

	/// <summary>
	/// RISオーダ情報のステータスを変更する
	/// </summary>
	/// <param name="risID">ステータス変更対象オーダの内部UID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="status">変更後のステータス</param>
	/// <param name="user">更新ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
	// 2019.08.06 Mod Cosmo Start 排他対応
	//public abstract boolean ChangeRisOrderStatus(String risID, String appID, String status, UserAccountBean user, String roomID, String kikiID, Connection con);
	public abstract boolean ChangeRisOrderStatus(String risID, String appID, String status, UserAccountBean user, String roomID, String kikiID, Connection con,int type,boolean exclusiv,StringBuilder returnErrorMsg);
	// 2019.08.06 Mod Cosmo Start 排他対応

	// 2010.07.30 Add DD.Chinh Start
	/// <summary>
	/// RIS検査室、検査機器情報を変更する
	/// </summary>
	/// <param name="risID">ステータス変更対象オーダの内部UID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="status">ステータス</param>
	/// <param name="user">更新ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
//	public abstract boolean ChangeExamRoomMachine(String risID, String status, UserAccountBean user, String roomID, String kikiID);
	// 2010.07.30 Add DD.Chinh End

	/// <summary>
	/// 指定された実績情報（引数のrisID）のみを取得する
	/// </summary>
	/// <param name="risID">取得対象の実績情報のRISID</param>
	/// <returns>実績情報</returns>
//	public abstract ExecutionInfoBean GetRisExecutionInfoOnly(String risID);

	/// <summary>
	/// 受付権限を確認する
	/// </summary>
	/// <param name="userID">対象ユーザID</param>
	/// <returns>判定</returns>
//	public abstract boolean CheckReceptUserAuthorization( String userID );

	/// <summary>
	/// 指定条件での RIS DB のレコード件数を取得
	/// </summary>
	/// <param name="param"></param>
	/// <returns>レコード件数</returns>
//	public abstract int GetRisOrderCount( OrderSearchParameter param );

	/// <summary>
	/// 患者情報の更新
	/// </summary>
	/// <param name="patientInfoBean">患者情報</param>
	/// <returns>成功：true　失敗：false</returns>
	public abstract boolean UpdateRisPatientData(PatientInfoBean patientInfoBean, Connection con);

	/// <summary>
	/// 実績患者情報を最新の患者情報へ更新する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="risID">RISID</param>
	/// <returns></returns>
//	public abstract boolean UpdateRisResultPatientData(String kanjaID, String risID);

	/// <summary>
	///  患者コメントを更新する
	/// </summary>
	/// <param name="patientID">患者ID</param>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="comment">コメント</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean UpdateRisPatientComment(String patientID, String kensatypeID, String comment);

	/// <summary>
	/// 患者身長体重情報の更新
	/// </summary>
	/// <param name="risIDStr">RisID</param>
	/// <param name="patientInfoBean">患者情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean UpdateRisPatientResultTallWeightData(String risIDStr, PatientInfoBean patientInfoBean);

	/// <summary>
	/// テンプレート取得
	/// </summary>
	/// <param name="id">TEMPLATEID</param>
	/// <returns>テンプレー</returns>
//	public abstract DataTable GetTemplateContents(String templateID, String param);

	/// <summary>
	/// オーダ部位情報取得
	/// </summary>
	/// <param name="id">RIS_ID</param>
	/// <returns>オーダ部位情報</returns>
//	public abstract DataTable GetRisOrderBuiList(String risID);

	/// <summary>
	/// 連絡メモ情報の更新
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="userAccount">ユーザID</param>
	/// <param name="memo">連絡メモ情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean UpdateRisRenrakuMemo(String risID, UserAccountBean userAccount, String memo);

	/// <summary>
	/// 検査室情報の更新
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean UpdateRisExRoom(String risID, String exRoomID);

	/// <summary>
	/// オーダ補足情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>オーダ補足情報</returns>
//	public abstract DataTable GetOrderIndicateTable(String risID);

	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// オーダ指示情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>オーダ指示情報</returns>
//	public abstract OrderIndicateBean GetRisOrderIndicate(String risID);
	// 2010.07.30 Add T.Ootsuka End

	/// <summary>
	/// HIS受信履歴情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="recieveID">受信ID</param>
	/// <returns>HIS受信履歴情報</returns>
//	public abstract DataTable GetFromHisInfo(String risID, String recieveID);

	/// <summary>
	/// HIS送信履歴情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>HIS送信履歴情報</returns>
//	public abstract DataTable GetToHisInfo(String risID, String requestID);

	/// <summary>
	/// 所見送信ﾘｸｴｽﾄ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>所見送信ﾘｸｴｽﾄ情報</returns>
//	public abstract DataTable GetToReportInfo(String risID, String requestID);

	/// <summary>
	/// 画像送信ﾘｸｴｽﾄ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>画像送信ﾘｸｴｽﾄ情報</returns>
//	public abstract DataTable GetToPacsInfo(String risID, String requestID);

	/// <summary>
	/// RIS用のオーダ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="satueiFinishFlg">撮影済ボタンフラグ(1：撮影済ボタン押下時動作、0：それ以外の動作)</param>
	/// <returns>オーダ情報</returns>
	// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
	public abstract OrderInfoBean GetRisOrderInfo(String risID, int satueiFinishFlg, Connection con) throws Exception;
//	public abstract OrderInfoBean GetRisOrderInfo(String risID, Connection con) throws Exception;
	// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加

	/// <summary>
	/// サーバータイプの取得
	/// </summary>
	/// <returns>サーバータイプ</returns>
	public abstract DataTable GetRisCurrentServerType(Connection con);

	/// <summary>
	/// 自端末のTerminalIDを取得する
	/// </summary>
	/// <returns>TerminalID</returns>
//	public abstract int GetMyTerminalID();

	/// <summary>
	/// 指定端末の情報を取得する
	/// </summary>
	/// <param name="terminalID">端末ID</param>
	/// <returns>端末情報</returns>
//	public abstract TerminalInfoBean GetTerminalData(String terminalID);

	/// <summary>
	/// 指定端末の情報を取得する
	/// </summary>
	/// <param name="IPAdrress">ipadrress</param>
	/// <returns>端末情報</returns>
	public abstract TerminalInfoBean GetTerminalInfoDataByIPAdrress(String ipadrress, Connection con);

	/// <summary>
	/// 指定端末の情報を保存する
	/// </summary>
	/// <param name="terminalBean">端末情報</param>
	/// <returns>判定</returns>
//	public abstract boolean RegisterTerminalData(TerminalInfoBean terminalBean);

	/// <summary>
	/// 一覧検索条件を取得する
	/// </summary>
	/// <param name="IPAdrress">ipadrress</param>
	/// <returns>端末情報</returns>
//	public abstract ArrayList GetSearchPatternInfo(OrderSearchParameter param);

	/// <summary>
	/// 一覧検索条件を更新する
	/// </summary>
	/// <param name="list">一覧検索条件</param>
	/// <param name="historyFlg">履歴フラグ</param>
	/// <returns>true:成功 false:失敗</returns>
//	public abstract boolean UpdateSearchPatternInfo(ArrayList list, boolean historyFlg);

	/// <summary>
	/// 一覧表示項目定義設定の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>一覧表示項目定義設定</returns>
//	public abstract DataTable GetShowItemDefineData(OrderSearchParameter param);

	// 2011.08.03 Add NSK_S.Imai Start NML-CAT2-029
	/// <summary>
	/// 受付ダイアログ一覧表示項目定義設定の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>一覧表示項目定義設定</returns>
//	public abstract DataTable GetReceiptDlgItemDefineData(OrderSearchParameter param);
	// 2011.08.03 Add NSK_S.Imai End

	/// <summary>
	/// 一覧表示項目定義設定を登録する
	/// </summary>
	/// <param name="windowID">登録対象の画面ID</param>
	/// <param name="terminalID">登録対象の端末ID</param>
	/// <param name="dt">登録情報</param>
	///<param name="historyFlg">履歴フラグ</param>
	/// <returns></returns>
//	public abstract boolean RegisterShowItemDefineData(String windowID, int terminalID, DataTable dt, boolean historyFlg);

	// 2011.08.03 Add NSK_S.Imai Start NML-CAT2-029
	/// <summary>
	/// 受付ダイアログ一覧表示項目定義設定を登録する
	/// </summary>
	/// <param name="windowID">登録対象の画面ID</param>
	/// <param name="terminalID">登録対象の端末ID</param>
	/// <param name="dt">登録情報</param>
	/// <returns>登録処理結果</returns>
//	public abstract boolean RegisterReceiptDlgItemDefineData(String windowID, int terminalID, DataTable dt);
	// 2011.08.03 Add NSK_S.Imai End

	/// <summary>
	/// 実施詳細画面一覧表示項目定義設定を登録する
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="listType">一覧タイプ</param>
	/// <param name="dt">登録情報</param>
	/// <returns></returns>
//	public abstract boolean RegisterOperationListItemDefineData(String kensatypeID, String listType, DataTable dt);

	/// <summary>
	/// マスタを取得する
	/// </summary>
	/// <param name="tableNameStr">指定マスタテーブル名</param>
	/// <param name="keyColumnStr">指定カラム名</param>
	/// <param name="keyData">指定値（条件句)</param>
	/// <returns>結果</returns>
//	public abstract DataRow GetMaster(String tableNameStr, String keyColumnStr, String keyData);

	/// <summary>
	/// マスタデータの取得
	/// </summary>
	/// <param name="tableNameStr">テーブル名</param>
	/// <param name="ascBool">ASCﾌﾗｸﾞ</param>
	/// <returns>マスタデータ</returns>
	public abstract DataTable GetMaster(String tableNameStr, boolean ascBool, Connection con) throws Exception;

	// 2010.07.30 Add T.Nishikubo Start
	/// <summary>
	/// マスタデータの取得
	/// </summary>
	/// <param name="tableNameStr">テーブル名</param>
	/// <param name="ascBool">ASCﾌﾗｸﾞ</param>
	/// <returns>マスタデータ</returns>
//	public abstract DataTable GetMaster(String tableNameStr, String keyColumnStr, String keyData, String sortCol);
	// 2010.07.30 Add T.Nishikubo End

	/// <summary>
	///拡張オーダ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>拡張オーダ情報</returns>
//	public abstract DataTable GetRisExtendOrderInfo(String risID);

	/// <summary>
	/// オーダ情報の更新
	///
	/// (拡張オーダ情報を含む)　オーダ詳細で使用
	/// </summary>
	/// <param name="orderInfoBean">オーダ情報</param>
//	public abstract boolean UpdateRisOrderInfo(OrderInfoBean orderInfoBean);

	/// <summary>
	/// 実績操作履歴情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>実績操作履歴情報</returns>
//	public abstract DataTable GetRisExamOperationHistory(String risID);

	/// <summary>
	/// 実績操作履歴情報の登録
	/// </summary>
	/// <param name="bean">実績操作履歴情報</param>
	/// <returns>実績操作履歴情報</returns>
	public abstract boolean RegisterRisExamOperationHistory(ExamOperationHistoryInfoBean bean, Connection con);

	/// <summary>
	/// 祝日情報の取得
	/// </summary>
	/// <param name="date">日付</param>
	/// <returns>祝日情報</returns>
	public abstract DataTable GetHolidayDataTable(Timestamp date, Connection con);

	/// <summary>
	/// ユーザ情報の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>ユーザ情報</returns>
	public abstract DataTable GetUserInfo(UserSearchParameter param, Connection con);

	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// ユーザ情報の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>ユーザ情報</returns>
//	public abstract DataTable GetTeamUserInfo(UserSearchParameter param);
	// 2010.07.30 Add T.Ootsuka End

	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	/// <summary>
	/// ユーザ情報の取得
	/// </summary>
	/// <param name="syokuinKbn">職員区分（空の場合は全て）</param>
	/// <returns>ユーザ情報</returns>
//	public abstract DataTable GetUserMaster(String syokuinKbn);
	// 2011.02.16 Add T.Nishikubo End

	/// <summary>
	/// HIS送信履歴情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>HIS送信履歴情報</returns>
//	public abstract ToHisInfoBean GetToHisMaxDate(String risID);

	/// <summary>
	/// 器材情報を取得する
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="partsBunruiDt">(戻)器材区分情報</param>
	/// <param name="detailPartsDt">(戻)器材詳細区分情報</param>
	/// <param name="partsDt">(戻)器材情報</param>
	/// <param name="infuseDt">(戻)手技情報</param>
//	public abstract void GetPartsMasterList(String kensatypeID,
//		ref DataTable partsBunruiDt, ref DataTable detailPartsDt, ref DataTable partsDt, ref DataTable infuseDt);

	// 2011.02.14 Add K.Shinohara Start
	/// <summary>
	/// 器材情報を取得する
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="partsBaseDt">器材情報(全検査種別)</param>
	/// <param name="infuseBaseDt">手技情報(全検査種別)</param>
	/// <param name="partsBunruiDt">(戻)器材区分情報</param>
	/// <param name="detailPartsDt">(戻)器材詳細区分情報</param>
	/// <param name="partsDt">(戻)器材情報</param>
	/// <param name="infuseDt">(戻)手技情報</param>
//	public abstract void GetPartsMasterList(String kensatypeID, DataTable partsBaseDt, DataTable infuseBaseDt,
//		ref DataTable partsBunruiDt, ref DataTable detailPartsDt, ref DataTable partsDt, ref DataTable infuseDt);
	// 2011.02.14 Add K.Shinohara End

	/// <summary>
	/// WorkListInfoをAccessionNoで削除する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="accessionNo">ACCESSIONNO</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <param name="typeIndex">操作タイプ</param>
	/// <returns></returns>
	public abstract boolean DeleteWorkListByAccesionNo(String risID, String accessionNo, String kikiID, Integer typeIndex, Connection con);

	/// <summary>
	/// WorkListInfoをAETitleで削除する
	/// </summary>
	/// <param name="AETitle">AEタイトル</param>
	/// <returns></returns>
//	public abstract boolean DeleteWorkListByAETitle(String aeTitle);

	/// <summary>
	/// 実績情報の保存を行う
	/// </summary>
	/// <param name="bean">実績情報</param>
	/// <param name="patientCommentList">患者コメントリスト</param>
	/// <returns></returns>
	public abstract boolean SaveRisExecutionInfo(ExecutionInfoBean bean, ArrayList patientCommentList, Connection con);

	// 2023.07.26 Add Nishihara@COSMO Start 撮影済ボタン機能追加
	/// <summary>
	/// 実績情報の保存を行う
	/// </summary>
	/// <param name="bean">実績情報</param>
	/// <param name="patientCommentList">患者コメントリスト</param>
	/// <returns></returns>
	public abstract boolean RegistSatueiRisExecutionInfo(ExecutionInfoBean bean, Connection con);
	// 2023.07.26 Add Nishihara@COSMO End 撮影済ボタン機能追加

	/// <summary>
	/// プリセット情報の取得
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <param name="satueiFinishFlg">撮影済ボタンフラグ(1：撮影済ボタン押下時動作、0：それ以外の動作)</param>
	/// <returns>プリセット情報</returns>
	// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
	public abstract ArrayList GetRisPresetInfoData(PresetParameter param, int satueiFinishFlg, Connection con);
//	public abstract ArrayList GetRisPresetInfoData(PresetParameter param, Connection con);
	// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加

	/// <summary>
	/// プリセット撮影情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>プリセット撮影情報</returns>
	public abstract ArrayList GetRisPresetSatueiData(PresetParameter param, Connection con);

	/// <summary>
	/// 器材情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>器材情報</returns>
//	public abstract DataTable GetRisPartsDataTable(PartsSearchParameter param);

	/// <summary>
	/// 手技情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>手技情報</returns>
//	public abstract DataTable GetRisInfuseDataTable(PartsSearchParameter param);

	/// <summary>
	/// CR撮影メニュー情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>CR撮影メニュー情報</returns>
	public abstract DataTable GetRisSatueiMenuDataTable(RequestParameter param, Connection con);

	/// <summary>
	/// 実績情報の検査日、ステータスと拡張情報の検像ステータスを更新する
	/// </summary>
	/// <param name="updExmainFlg">実績メイン情報更新可否フラグ</param>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>成功：true　失敗：false</returns>
	public abstract boolean UpdateRisExecutionKensaDateStatus(boolean updExmainFlg, ExecutionInfoBean exBean, Connection con);

	/// <summary>
	/// 実績情報の連絡メモを更新する
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean UpdateRisExecutionRenrakuMemo(ExecutionInfoBean exBean);

	/// <summary>
	/// 実績情報を更新する(検査開始)
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
	public abstract boolean UpdateRisKensaStartProc(ExecutionInfoBean exBean, Connection con);

	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean RestoreRisKensaData(ExecutionInfoBean exBean);

	// 2011.08.16 Add DD.Chinh@MERX Start NML-CAT2-010
	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean UnReceiptData(ExecutionInfoBean exBean);
	// 2011.08.16 Add DD.Chinh@MERX End

	/// <summary>
	/// 実績情報を更新する(検査終了)
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <param name="patientCommentList">患者コメントリスト</param>
	/// <returns>成功：true　失敗：false</returns>
	public abstract boolean UpdateRisKensaFinishProc(ExecutionInfoBean exBean, ArrayList patientCommentList, Connection con);

	/// <summary>
	/// 認証を行う
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="password">パスワード</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean CheckUserIDPassword(String userID, String password);

	// 2011.08.11 Add NSK_T.Koudate Start NML-CAT2-004
	/// <summary>
	/// 認証を行う(ログインはしない)
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <param name="password">パスワード</param>
	/// <param name="userAccount">返されるユーザ情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean CheckUserIDPassword(String userID, String planePassword,
//		ref UserAccountBean userAccount);
	// 2011.08.11 Add NSK_T.Koudate End

	/// <summary>
	/// ToHisInfoへ情報を登録する(検査終了時)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <param name="reSendFlg">再送信フラグ</param>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	public abstract boolean RegisterToHisInfoByOperation(OrderInfoBean orderBean, TerminalInfoBean terminalBean, boolean reSendFlg, String status, Connection con);

	/// <summary>
	/// ToHisInfoへ情報を登録する(患者)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="patientBean">患者情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
//	public abstract boolean RegisterToHisInfoByPatient(OrderInfoBean orderBean, PatientInfoBean patientBean, TerminalInfoBean terminalBean);

	/// <summary>
	/// ToReportInfoへ情報を登録する(検査終了時)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <param name="reSendFlg">再送信フラグ</param>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	public abstract boolean RegisterToReportPacsInfoByOperation(OrderInfoBean orderBean, TerminalInfoBean terminalBean, boolean reSendFlg, String status, Connection con);

	/// <summary>
	/// 優先フラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="oldYuusenFlg">旧優先フラグ</param>
	/// <param name="newYuusenFlg">新設定フラグ</param>
	/// <returns></returns>
//	public abstract boolean UpdateRisYuusenFlg(String risID, String appID, String oldYuusenFlg, String newYuusenFlg);

	/// <summary>
	/// 感染症フラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="infectionFlg">感染症フラグ</param>
	/// <returns></returns>
//	public abstract boolean UpdateRisInfectionFlg(String risID, String appID, String infectionFlg);

	/// <summary>
	/// 同意書チェックフラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="douishoFlg">同意書チェックフラグ</param>
	/// <returns></returns>
//	public abstract boolean UpdateRisDouishoFlg(String risID, String appID, String douishoFlg);

	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	/// <summary>
	/// 薬剤発注チェックフラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="pharmaFlg">薬剤発注チェックフラグ</param>
	/// <returns></returns>
//	public abstract boolean UpdateRisPharmaFlg(String risID, String appID, String pharmaFlg);
	// 2010.11.16 Add T.Nishikubo End

	/// <summary>
	/// 確保処理を行う
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="terminalID">端末ID</param>
	/// <returns></returns>
//	public abstract boolean UpdateRisKakuho(String risID, String appID, String terminalID);

	/// <summary>
	/// オーダを削除する(OrderMainTable.Staus=-9にする)
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
//	public abstract int DeleteOrderData(String risID);

	/// <summary>
	/// オーダ情報を登録する
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="indicateBean">オーダ指示情報</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean RegisterRisOrderInfo(OrderInfoBean orderBean, OrderIndicateBean indicateBean, ExecutionInfoBean exBean);

	/// <summary>
	/// 患者情報を登録する
	/// </summary>
	/// <param name="patientBean">患者情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean RegisterRisPatientInfo(PatientInfoBean patientBean);

	/// <summary>
	/// ToReportInfo,ToPacsInfoへ情報を登録する(オーダ登録時)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
//	public abstract String RegisterToReportPacsInfoByCreateOrder(OrderInfoBean orderBean, TerminalInfoBean terminalBean);

	/// <summary>
	/// 他検査一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
//	public abstract DataTable GetRisOtherKensaList(OrderSearchParameter param);

	/// <summary>
	/// (治療DB)他検査一覧を取得する
	/// </summary>
	/// <returns>オーダ一覧</returns>
//	public abstract DataTable GetRtrisOtherKensaList(OrderSearchParameter param);

	/// <summary>
	/// RIS実績情報の指示情報を更新する
	/// </summary>
	/// <param name="appID">画面ID</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
//	public abstract boolean UpdateRisSijiInfo(String appID, ExecutionInfoBean exBean);

    /// <summary>
	/// RIS実績情報の日時情報を更新する
	/// </summary>
    /// <param name="exBean">実績情報</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
//   public abstract boolean UpdateRisExamTimestamp(ExecutionInfoBean exBean);

	/// <summary>
	/// RISオーダ情報のステータスを変更する
	/// </summary>
	/// <param name="risID">ステータス変更対象オーダの内部UID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="status">変更後のステータス</param>
	/// <param name="user">更新ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
//	public abstract boolean ChangeRisOrderStatusNoCheckout(String risID, String appID, String status, UserAccountBean user, String roomID, String kikiID);

	/// <summary>
	/// テーブル定義情報取得
	/// </summary>
	/// <param name="tabName">テーブル名称</param>
	/// <param name="mwmFlg">MWMフラグ</param>
	/// <returns>テーブル定義情報</returns>
//	public abstract DataTable GetUserTabColumnsData(String tabName, boolean mwmFlg);

	/// <summary>
	/// WorkListInfoを取得する
	/// </summary>
	/// <param name="studyID">Study_Instance_UID</param>
	/// <param name="conBean">MWM接続情報</param>
	/// <returns></returns>
//	public abstract DataTable SelectWorkListInfo(String studyID, ConnectionInfoBean conBean);

	/// <summary>
	/// WorkListInfoへ登録を行う
	/// </summary>
	/// <param name="mwmBeanList">MWM情報リスト</param>
	/// <param name="conBean">MWM接続情報</param>
	/// <param name="deleteMode">削除モード</param>
	/// <returns></returns>
	public abstract boolean RegisterWorkListInfo(ArrayList mwmBeanList, ConnectionInfoBean conBean, String deleteMode);

	/// <summary>
	/// ユーザ情報の取得
	/// </summary>
	/// <param name="userID">ユーザID</param>
	/// <returns>ユーザ情報</returns>
//	public abstract DataRow GetUserManage(String userID);

	/// <summary>
	/// ユーザ情報の更新
	/// </summary>
	/// <param name="userBean">ユーザ情報</param>
	/// <returns>ユーザ情報</returns>
//	public abstract boolean UpdateUserManage(UserAccountBean userBean);

	/// <summary>
	/// テーブル情報を取得する
	/// </summary>
	/// <param name="tableName">テーブル名</param>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
//	public abstract DataTable GetRisTableInfo(String tableName, String kanjaID, String risID);

	/// <summary>
	/// 検像情報更新を行う
	/// </summary>
	/// <param name="extendExamInfoBean">拡張実績情報</param>
	/// <param name="bikouStr">コメント</param>
	/// <returns></returns>
//	public abstract boolean UpdateKenzouOperation(ExtendExamInfoBean extendExamInfoBean, String bikouStr);

	/// <summary>
	/// 治療DBの患者共通コメントを検索する
	/// </summary>
	/// <param name="patientID">患者ID</param>
	/// <returns>患者検索結果一覧</returns>
//	public abstract String GetRrisPatientComment(String patientID);

	/// <summary>
	/// 指定されたオーダの進捗（引数のrisID）を取得する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>進捗</returns>
//	public abstract String GetRisStatus(String risID);

	/// <summary>
	/// SystemParam2の値を更新する
	/// </summary>
	/// <param name="mainID">メインキー</param>
	/// <param name="subID">サブキー</param>
	/// <param name="column">列名</param>
	/// <param name="valueStr">値</param>
	/// <returns></returns>
//	public abstract boolean UpdateSystemParam2Value(String mainID, String subID, String column, String valueStr);

	/// <summary>
	/// 業務詳細過去情報の件数を取得する
	/// </summary>
	/// <param name="oParam">検索条件</param>
	/// <returns>件数</returns>
//	public abstract int GetRrisGyomuOldOrderDataCount(OrderSearchParameter oParam);

	/// <summary>
	/// 帳票用-オーダ情報Hashの取得
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>オーダ情報</returns>
//	public abstract Hashtable GetPrintOrderDataHash(PrintParameter printParam);

	/// <summary>
	/// 受付票の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintReceiptReport(PrintParameter printParam);

	/// <summary>
	/// 依頼票の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintRequestReport(PrintParameter printParam);

	/// <summary>
	/// 患者袋ラベルの印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintLabelKanjaReport(PrintParameter printParam);

	/// <summary>
	/// 検査袋ラベルの印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintLabelKensaReport(PrintParameter printParam);

	/// <summary>
	/// 実績票の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintExReport(PrintParameter printParam);

	/// <summary>
	/// 予定一覧の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintScheduleListReport(PrintParameter printParam);

	/// <summary>
	/// 検査台帳の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintOrderListReport(PrintParameter printParam);

	// 2010.06.23 Add T.Nishikubo Start
	/// <summary>
	/// 業務日誌の印刷
	/// </summary>
	/// <param name="printParam">印刷条件</param>
	/// <param name="diaryBtn">日誌1/日誌2</param>
	/// <returns>印刷成功：true　印刷失敗：false</returns>
//	public abstract boolean PrintDiaryReport(PrintParameter printParam, String diaryBtn);
	// 2010.06.23 Add T.Nishikubo End

	/// <summary>
	/// MPPS-MPPS定義情報Hashの取得
	/// </summary>
	/// <returns>オーダ情報</returns>
//	public abstract Hashtable GetMppsDefineHash();

	/// <summary>
	/// 検査系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="accessionNo">AccessionNo</param>
	/// <returns>検査系-MPPSマスタ情報</returns>
//	public abstract DataTable GetKensaMppsMasterData(String accessionNo);

	/// <summary>
	/// 検査系-MPPS照射情報を取得する
	/// </summary>
	/// <param name="accessionNo">AccessionNo</param>
	/// <returns>検査系-MPPS照射情報</returns>
//	public abstract DataTable GetKensaMppsExposureDoseData(String accessionNo);

	/// <summary>
	/// 撮影系-MPPSマスタ情報を取得する
	/// </summary>
	/// <param name="accessionNo">AccessionNo</param>
	/// <returns>撮影系-MPPSマスタ情報</returns>
	public abstract DataTable GetSatueiMppsMasterData(String accessionNo, Connection con);

	/// <summary>
	/// 撮影系-MPPS実績情報を取得する
	/// </summary>
	/// <param name="sopInstanceUID">MPPSSOPInstanceUID</param>
	/// <returns>撮影系-MPPS実績情報</returns>
	public abstract DataTable GetSatueiMppsResultData(String sopInstanceUID, Connection con);

	/// <summary>
	/// 一覧デザイン設定の取得
	/// </summary>
	/// <param name="windowAppID">画面ID</param>
	/// <param name="terminalID">端末ID</param>
	/// <param name="listType">一覧タイプ</param>
	/// <returns>一覧表示項目定義設定</returns>
//	public abstract DataTable GetShowListDefineData(String windowAppID, int terminalID, String listType);

	/// <summary>
	/// 一覧デザイン設定を登録する
	/// </summary>
	/// <param name="styleRow">デザイン情報</param>
	/// <returns></returns>
//	public abstract boolean RegisterShowListDefineData(DataRow styleRow);

	/// <summary>
	/// ソート条件パターン設定を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>ソート条件パターン設定</returns>
//	public abstract DataTable GetSortPatternInfo(OrderSearchParameter param);

	/// <summary>
	/// ソート条件パターン設定を登録する
	/// </summary>
	/// <param name="auditBean">ソート条件パターン設定</param>
	/// <returns>判定</returns>
//	public abstract boolean RegisterSortPatternInfo(DataRow row);

	/// <summary>
	/// ソート条件パターン設定を更新する
	/// </summary>
	/// <param name="auditBean">ソート条件パターン設定</param>
	/// <param name="historyFlg">履歴フラグ</param>
	/// <returns>判定</returns>
//	public abstract boolean UpdateSortPatternInfo(DataRow row, boolean historyFlg);

	/// <summary>
	/// ソート条件パターン設定を削除する
	/// </summary>
	/// <param name="auditBean">ソート条件パターン設定</param>
	/// <returns>判定</returns>
//	public abstract boolean DeleteSortPatternInfo(int patternID);

	/// <summary>
	/// 登録済みの履歴一覧を取得する
	/// </summary>
	/// <returns>履歴一覧</returns>
//	public abstract DataTable GetRisHistoryList(OrderSearchParameter param);

	// 2010.11.08 Add K.Shinohara Start
	/// <summary>
	/// 登録済みの履歴数をカウントする
	/// </summary>
	/// <remarks></remarks>
	/// <returns></returns>
//	public abstract int GetRisHistoryCount(OrderSearchParameter param);
	// 2010.11.08 Add K.Shinohara End

	// 2010.06.23 Add T.Nishikubo Start
	/// <summary>
	/// 業務日誌情報の取得
	/// </summary>
	/// <param name="exDateStr">検査日</param>
	/// <returns>業務日誌情報</returns>
//	public abstract ExamDailyInfoBean GetExamDailyData(String exDateStr);

	/// <summary>
	/// 業務日誌情報を登録する
	/// </summary>
	/// <param name="exDailyBean">業務日誌情報</param>
	/// <returns>結果</returns>
//	public abstract boolean RegisterExamDailyData(ExamDailyInfoBean exDailyBean);
	// 2010.06.23 Add T.Nishikubo End

	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// チーム情報を取得する
	/// </summary>
	/// <param name="terminalID">端末ID</param>
	/// <returns>チーム情報</returns>
//	public abstract TeamInfoBean GetTeamInfoData(String terminalID);

	/// <summary>
	/// チーム情報を更新する
	/// </summary>
	/// <param name="teamInfoBean">チーム情報</param>
	/// <returns>更新成否</returns>
//	public abstract boolean UpdateTeamInfoData(TeamInfoBean teamInfoBean);

	/// <summary>
	/// チーム情報を登録する
	/// </summary>
	/// <param name="teamInfoBean">チーム情報</param>
	/// <returns>登録成否</returns>
//	public abstract boolean InsertTeamInfoData(TeamInfoBean teamInfoBean);
	// 2010.07.30 Add T.Ootsuka End

	// 2011.03.10 Add CIJ_R.Aoyama Merge Start MY-1-10
	/// <summary>
	/// 患者副作用情報を取得する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>患者副作用情報</returns>
//	public abstract ArrayList GetPatientSideEffectInfoBean(String kanjaID);

	/// <summary>
	/// 患者副作用情報を取得する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>患者副作用情報</returns>
//	public abstract DataTable GetPatientSideEffectInfoData(String kanjaID);

	/// <summary>
	/// 患者副作用情報の件数を取得する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>患者副作用情報件数</returns>
//	public abstract int GetPatientSideEffectInfoCount(String kanjaID);

	/// <summary>
	/// 患者副作用情報を登録する
	/// </summary>
	/// <param name="patientSideEffectInfo">患者副作用情報</param>
	/// <returns>成否</returns>
//	public abstract boolean InsertPatientSideEffectInfo(PatientSideEffectInfoBean patientSideEffectInfo);

	/// <summary>
	/// 患者副作用情報を更新する
	/// </summary>
	/// <param name="patientSideEffectInfo">患者副作用情報</param>
	/// <returns>成否</returns>
//	public abstract boolean UpdatePatientSideEffectInfo(PatientSideEffectInfoBean patientSideEffectInfo);

	/// <summary>
	/// 患者副作用情報を削除する
	/// </summary>
	/// <param name="sideEffectInfoID">患者副作用情報</param>
	/// <returns>成否</returns>
//	public abstract boolean DeletePatientSideEffectInfo(String sideEffectInfoID);
	// 2011.03.10 Add CIJ_R.Aoyama Merge  End  MY-1-10

	// 2010.07.30 Add T.Nishikubo Start
	/// <summary>
	/// CSVファイルを成形して出力する
	/// </summary>
	/// <param name="csvParam">CSV出力パラメータ</param>
	/// <returns>結果</returns>
//	public abstract boolean OutputCsvFile(ref OutputCsvParameter csvParam);
	// 2010.07.30 Add T.Nishikubo End

	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	// 掲示板関連
	/// <summary>
	/// 新しいメッセージIDを取得する
	/// </summary>
	/// <returns>InstanceID</returns>
//	public abstract String GetNewMessageID(Connection con, Transaction transaction);

	/// <summary>
	/// 掲示板用チェックアウト処理
	/// </summary>
	/// <param name="messageId">チェックアウト対象のメッセージID</param>
	/// <returns>チェックアウトしたメッセージID</returns>
//	public abstract String CheckoutMsgData(String messageId);

	/// <summary>
	/// 編集完了したデータをチェックインする
	/// </summary>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns></returns>
//	public abstract boolean UncheckoutMsgData(String checkoutID);

	/// <summary>
	/// dataUIDで指定されたデータとCheckoutIDの組み合わせをチェックする
	/// </summary>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <param name="dataUID">チェックアウト対象データのUID</param>
	/// <returns></returns>
//	public abstract boolean CanCheckin(String checkoutID, String dataUID);

	/// <summary>
	/// チェックアウト情報を取得する
	/// </summary>
	/// <param name="checkoutID"></param>
	/// <returns><returns>
//	public abstract AccessInfoBean GetCheckoutMsgInfo(String checkoutID);

	/// <summary>
	/// 掲示板全データのチェックアウト取消し
	/// </summary>
	/// <returns></returns>
//	public abstract void UncheckoutAllMsgData();

	/// <summary>
	/// [xx-01] 当該コミュニケーションメッセージを編集するためにcheckoutする
	/// 　※CommunicationMsgInfoBeanの送信モードにより、通常テーブルor送信キューテーブルをcheckout
	/// </summary>
	/// <param name="messageIDStr">チェックアウト対象のメッセージID</param>
	/// <returns>チェックアウトID</returns>
//	public abstract String CheckoutCommunicationMsg( String messageIDStr );

	/// <summary>
	/// [xx-02] 全体告知情報を編集するためにcheckoutする
	/// </summary>
	/// <returns>チェックアウトID</returns>
//	public abstract String CheckoutCommunicationAllMsg();

	// 2011.02.28 Add T.Nishikubo Start
	/// <summary>
	/// [xx-02a] 端末メモを編集するためにcheckoutする
	/// </summary>
	/// <returns>チェックアウトID</returns>
//	public abstract String CheckoutCommunicationTerminalMemo();
	// 2011.02.28 Add T.Nishikubo End

	/// <summary>
	/// [xx-03] 新規のコミュニケーションメッセージ情報を登録する
	/// 　※CommunicationMsgInfoBeanの送信モードにより、通常テーブルor送信キューテーブルに登録
	/// </summary>
	/// <param name="bean">登録対象データ</param>
	/// <returns>登録成功：true　登録失敗：false</returns>
//	public abstract boolean RegisterCommunicationMsgInfo(CommunicationMsgInfoBean comMsgBean);

	/// <summary>
	/// [xx-04] 新規の全体告知情報を登録する
	/// </summary>
	/// <param name="messageStr">全体告知メッセージ</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
//	public abstract CommunicationMsgInfoBean RegisterCommunicationAllMsgInfo(String messageStr);

	/// <summary>
	/// [xx-04a] 新規の端末メモを登録する
	/// </summary>
	/// <param name="messageStr">端末メモ</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
//	public abstract CommunicationMsgInfoBean RegisterCommunicationTerminalMemoInfo(String messageStr);

	/// <summary>
	/// [xx-05] 既存のコミュニケーションメッセージ情報を更新する
	/// 　※CommunicationMsgInfoBeanの送信モードにより、通常テーブルor送信キューテーブルを更新
	/// </summary>
	/// <param name="bean">更新対象データ</param>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns>更新成功：true　更新失敗：false</returns>
//	public abstract boolean CheckinCommunicationMsgInfo(CommunicationMsgInfoBean comMsgBean, String checkoutID);

	/// <summary>
	/// [xx-06] 既存の全体告知情報を更新する
	/// </summary>
	/// <param name="messageStr">全体告知メッセージ</param>
	/// <param name="checkoutID">チェックアウトID</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
//	public abstract CommunicationMsgInfoBean CheckinCommunicationAllMsgInfo(String messageStr, String checkoutID);

	/// <summary>
	/// [xx-06a] 既存の端末メモを更新する
	/// </summary>
	/// <param name="messageStr">端末メモ</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
//	public abstract CommunicationMsgInfoBean UpdateCommunicationTerminalMemoInfo(String messageStr);

	/// <summary>
	/// [xx-07] 既存のコミュニケーションメッセージの通知先ステータスを確認済みに更新する
	/// </summary>
	/// <param name="bean">更新対象データ</param>
	/// <returns>更新成功：true　更新失敗：false</returns>
//	public abstract boolean UpdateCommunicationSendtoStatusChecked(CommunicationMsgInfoBean comMsgBean);

	/// <summary>
	/// [xx-08] コミュニケーションメッセージ情報を削除する(削除フラグ=1)
	/// </summary>
	/// <param name="messageIDStr">メッセージID</param>
	/// <returns>更新成功：true　更新失敗：false</returns>
//	public abstract boolean DeleteCommunicationMsgInfo( String messageIDStr );

	/// <summary>
	/// [xx-09] 指定ユーザ宛のコミュニケーションメッセージ情報の一覧を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果一覧</returns>
//	public abstract CommunicationMsgInfoBean[] GetCommunicationUserMsgList(CommunicationMsgSearchParameter param);

	/// <summary>
	/// [xx-10] 指定ユーザが登録したコミュニケーションメッセージ情報の一覧を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果一覧</returns>
//	public abstract CommunicationMsgInfoBean[] GetCommunicationUserEntryMsgList(CommunicationMsgSearchParameter param);

	/// <summary>
	/// [xx-11] 指定メッセージの関連メッセージ情報の一覧を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果一覧</returns>
//	public abstract CommunicationMsgInfoBean[] GetCommunicationSerialMsgList(CommunicationMsgSearchParameter param);

	/// <summary>
	/// [xx-12] 指定メッセージIDのコミュニケーションメッセージ情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="messageIDStr">メッセージID</param>
	/// <returns>コミュニケーションメッセージ情報</returns>
//	public abstract CommunicationMsgInfoBean GetCommunicationMsg( String messageIDStr );

	/// <summary>
	/// [xx-13] 全体告知情報を取得する
	/// </summary>
	/// <remarks></remarks>
	/// <returns>コミュニケーションメッセージ情報</returns>
//	public abstract CommunicationMsgInfoBean GetCommunicationAllMsg();

	/// <summary>
	/// [xx-13a] 端末メモを取得する
	/// </summary>
	/// <remarks></remarks>
	/// <returns>コミュニケーションメッセージ情報</returns>
//	public abstract CommunicationMsgInfoBean GetCommunicationTerminalMemo();


	// 2011.02.16 Add T.Nishikubo End

	// 2011.07.19 Add NSK_T.Wada	Start NML-CAT2-001
	/// <summary>
	/// LINK情報一覧を取得する
	/// </summary>
	/// <returns>Link情報一覧</returns>
//	public abstract DataTable GetRisLinkInfo();
	// 2011.07.19 Add NSK_W.Wada	End

	/// <summary>
	/// オーダ情報に部位略称を付加する
	/// </summary>
	/// <param name="orderDt">オーダ情報</param>
	/// <param name="oParam">オーダパラメータ</param>
	/// <returns></returns>
//	public abstract void ModifyOrder_MultiLocus(DataTable orderDt, OrderSearchParameter oParam);

	// 2011.11.22 Add NSK_T.Wada	Start OMH-1-9
	/// <summary>
	/// クローズ予約検索条件を取得する
	/// </summary>
	/// <param name="intTerminalId">端末ID</param>
	/// <param name="strWindowappId">画面ID</param>
	/// <returns>クローズ予約検索条件</returns>
//	public abstract DataTable GetCloseAppointSearchData(int intTerminalId, String strWindowappId);

	/// <summary>
	/// クローズ予約検索条件を登録する
	/// </summary>
	/// <param name="row">登録データ</param>
	/// <returns>成否</returns>
//	public abstract boolean InsertCloseAppointSearchData(DataRow row);

	/// <summary>
	/// クローズ予約検索条件を更新する
	/// </summary>
	/// <param name="row">更新データ</param>
	/// <returns>成否</returns>
//	public abstract boolean UpdateCloseAppointSearchData(DataRow row);

	// 2011.11.22 Add NSK_W.Wada	End

	//2011.11.21 Add NSK_M.Ochiai Start extends OMH-1-7
	/// <summary>
	/// 実績情報のREVISION処理を実行する
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>成功：true　失敗：false</returns>
//	public abstract boolean UpdateRisToRevision(ExecutionInfoBean exBean);
	//2011.11.21 Add NSK_M.Ochiai End

	// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
	public abstract boolean unCheckOutExclusive(BaseDto dto,String checkoutID,Connection con,boolean exclusive);
	// 2019.08.06 Add Cosmo Start 排他対応
//	public abstract boolean unCheckOutExclusive(StatusUpdateDto dto,String checkoutID,Connection con,boolean exclusive);
	// 2019.08.06 Add Cosmo End 排他対応
	// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加
	// 2019.10.03 Add Cosmo Start 排他ロック対応
	public abstract boolean unCheckOutLoginLockExclusive(StatusUpdateDto dto,String checkoutIp,String checkoutAppID,Connection con,boolean exclusive);
	// 2019.10.03 Add Cosmo End 排他ロック対応
}
