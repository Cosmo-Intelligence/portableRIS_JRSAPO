package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExecutionInfoBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.util.CommonString;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
/// 実績テーブルの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.03.10	: 112478 / A.Kobayashi		: original
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo		: KANRO-R-3 KANRO-R-9
/// V2.01.00		: 2011.07.11    extends 999999 / NSK_H.Hashimoto	: NML-CAT1-002
/// V2.01.00		: 2011.07.26    extends 999999 / NSK_T.Koudate	: NML-CAT2-004
/// V2.01.00		: 2011.08.05	: 999999 / H.Satou@MERX		: NML-CAT2-010
/// V2.01.00		: 2011.08.22	: 999999 / T.Nishikubo		: NML-CAT1-000
/// V2.01.01.13000	: 2011.11.15    extends 999999 / NSK_H.Hashimoto	: OMH-1-05
/// V2.01.01.13000	: 2011.11.21	: 999999 / NSK_M.Ochiai		: OMH-1-7
///
/// </summary>
public class RisExMainTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "EXMAINTABLE";

	// カラム定義
	// EXMAINTABLE COLUMN
	public static final String RIS_ID_COLUMN					= "RIS_ID";
	public static final String KENSATYPE_ID_COLUMN				= "KENSATYPE_ID";
	public static final String KENSA_DATE_COLUMN				= "KENSA_DATE";
	public static final String KENSASITU_ID_COLUMN				= "KENSASITU_ID";
	public static final String KENSAKIKI_ID_COLUMN				= "KENSAKIKI_ID";
	public static final String KANJA_ID_COLUMN					= "KANJA_ID";
	public static final String KENSA_DATE_AGE_COLUMN			= "KENSA_DATE_AGE";
	public static final String DENPYO_NYUGAIKBN_COLUMN			= "DENPYO_NYUGAIKBN";
	public static final String UKETUKE_TANTOU_ID_COLUMN		= "UKETUKE_TANTOU_ID";
	public static final String UKETUKE_TANTOU_NAME_COLUMN		= "UKETUKE_TANTOU_NAME";
	public static final String RECEIPTDATE_COLUMN				= "RECEIPTDATE";
	public static final String RECEIPTTERMINALID_COLUMN		= "RECEIPTTERMINALID";
	public static final String RECEIPTNUMBER_COLUMN			= "RECEIPTNUMBER";
	// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
	public static final String RECEIPT_INITIAL_CHAR_COLUMN		= "RECEIPT_INITIAL_CHAR";
	// 2011.11.15 Add NSK_H.Hashimoto End
	public static final String KENSA_GISI_ID_COLUMN			= "KENSA_GISI_ID";
	public static final String KENSA_GISI_NAME_COLUMN			= "KENSA_GISI_NAME";
	public static final String EXAMSTARTDATE_COLUMN			= "EXAMSTARTDATE";
	public static final String EXAMENDDATE_COLUMN				= "EXAMENDDATE";
	public static final String EXAMTERMINALID_COLUMN			= "EXAMTERMINALID";
	public static final String STARTNUMBER_COLUMN				= "STARTNUMBER";
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//public static final String KANGOSI_ID_COLUMN				= "KANGOSI_ID";
	//public static final String KANGOSI_NAME_COLUMN				= "KANGOSI_NAME";

	// 2011.07.26 Del NSK_T.Koudate End
	public static final String KENSAI_ID_COLUMN				= "KENSAI_ID";
	public static final String KENSAI_NAME_COLUMN				= "KENSAI_NAME";
	public static final String BIKOU_COLUMN					= "BIKOU";
	public static final String RENRAKU_MEMO_COLUMN				= "RENRAKU_MEMO";
	public static final String SIJI_ISI_ID_COLUMN				= "SIJI_ISI_ID";
	public static final String SIJI_ISI_NAME_COLUMN			= "SIJI_ISI_NAME";
	public static final String SIJI_ISI_COMMENT_COLUMN			= "SIJI_ISI_COMMENT";
	public static final String TOUSITIME_COLUMN				= "TOUSITIME";
	public static final String BAKUSYASUU_COLUMN				= "BAKUSYASUU";
	public static final String GYOUMU_KBN_COLUMN				= "GYOUMU_KBN";
	public static final String STATUS_COLUMN					= "STATUS";
	public static final String RECEIPTFLAG_COLUMN				= "RECEIPTFLAG";
	public static final String YUUSEN_FLG_COLUMN				= "YUUSEN_FLG";
	public static final String EXAMSAVEFLAG_COLUMN				= "EXAMSAVEFLAG";
	public static final String ENFORCEDOC_ID_COLUMN			= "ENFORCEDOC_ID";
	public static final String ENFORCEDOC_NAME_COLUMN			= "ENFORCEDOC_NAME";
	public static final String ENDCOUNT_COLUMN					= "ENDCOUNT";
	public static final String DOUISHO_CHECK_NAME_COLUMN		= "DOUISHO_CHECK_NAME";
	public static final String DOUISHO_CHECK_FLAG_COLUMN		= "DOUISHO_CHECK_FLAG";
	public static final String INFECTION_CHECK_NAME_COLUMN		= "INFECTION_CHECK_NAME";
	public static final String INFECTION_CHECK_FLAG_COLUMN		= "INFECTION_CHECK_FLAG";
	public static final String YOBIDASI_DATE_COLUMN			= "YOBIDASI_DATE";
	// 2010.07.30 Add T.Ootsuka Start
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//public static final String KENSA_GISI_ID2_COLUMN			= "KENSA_GISI_ID2";
	//public static final String KENSA_GISI_NAME2_COLUMN			= "KENSA_GISI_NAME2";
	//public static final String KENSA_GISI_ID3_COLUMN			= "KENSA_GISI_ID3";
	//public static final String KENSA_GISI_NAME3_COLUMN			= "KENSA_GISI_NAME3";
	//public static final String KENSA_GISI_ID4_COLUMN			= "KENSA_GISI_ID4";
	//public static final String KENSA_GISI_NAME4_COLUMN			= "KENSA_GISI_NAME4";
	//public static final String KENSA_GISI_ID5_COLUMN			= "KENSA_GISI_ID5";
	//public static final String KENSA_GISI_NAME5_COLUMN			= "KENSA_GISI_NAME5";

	// 2011.07.26 Del NSK_T.Koudate End
	public static final String ENFORCEDOC_ID2_COLUMN			= "ENFORCEDOC_ID2";
	public static final String ENFORCEDOC_NAME2_COLUMN			= "ENFORCEDOC_NAME2";
	public static final String ENFORCEDOC_ID3_COLUMN			= "ENFORCEDOC_ID3";
	public static final String ENFORCEDOC_NAME3_COLUMN			= "ENFORCEDOC_NAME3";
	public static final String ENFORCEDOC_ID4_COLUMN			= "ENFORCEDOC_ID4";
	public static final String ENFORCEDOC_NAME4_COLUMN			= "ENFORCEDOC_NAME4";
	public static final String ENFORCEDOC_ID5_COLUMN			= "ENFORCEDOC_ID5";
	public static final String ENFORCEDOC_NAME5_COLUMN			= "ENFORCEDOC_NAME5";

	public static final String ENFORCEDOC_SECTION1_COLUMN		= "ENFORCEDOC_SECTIONID";
	public static final String ENFORCEDOC_SECTION2_COLUMN		= "ENFORCEDOC_SECTIONID2";
	public static final String ENFORCEDOC_SECTION3_COLUMN		= "ENFORCEDOC_SECTIONID3";
	public static final String ENFORCEDOC_SECTION4_COLUMN		= "ENFORCEDOC_SECTIONID4";
	public static final String ENFORCEDOC_SECTION5_COLUMN		= "ENFORCEDOC_SECTIONID5";

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//public static final String KANGOSI_ID2_COLUMN				= "KANGOSI_ID2";
	//public static final String KANGOSI_NAME2_COLUMN			= "KANGOSI_NAME2";
	//public static final String KANGOSI_ID3_COLUMN				= "KANGOSI_ID3";
	//public static final String KANGOSI_NAME3_COLUMN			= "KANGOSI_NAME3";

	// 2011.07.26 Del NSK_T.Koudate End
	public static final String JISISYA_ID_COLUMN				= "JISISYA_ID";
	public static final String JISISYA_NAME_COLUMN				= "JISISYA_NAME";
	// 2010.07.30 Add T.Ootsuka End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	public static final String DRESSING_ROOM_ID_COLUMN			= "DRESSING_ROOM_ID";
	// 2010.11.16 Add T.Nishikubo End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	public static final String PHARMA_CHECK_NAME_COLUMN		= "PHARMA_CHECK_NAME";
	public static final String PHARMA_CHECK_FLAG_COLUMN		= "PHARMA_CHECK_FLAG";
	// 2010.11.16 Add T.Nishikubo End

	// 2010.11.22 Add K.Shinohara Start
	// 追加定義（業務日誌に使用）
	public static final String DENPYO_NYUGAIKBN_NAME_COLUMN	= "DENPYO_NYUGAIKBN_NAME";
	// 2010.11.22 Add K.Shinohara End

	/* 一ノ瀬保留
	// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
	public static final String UNKNOWNDATE_FLG_COLUMN			= "UNKNOWNDATE_FLG";
	// 2011.07.11 Add NSK_H.Hashimoto End

	// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	public static final String MED_PERSON_ID01_COLUMN				= "MED_PERSON_ID01";
	public static final String MED_PERSON_NAME01_COLUMN			= "MED_PERSON_NAME01";
	public static final String MED_PERSON_SYOKUINKBN01_COLUMN		= "MED_PERSON_SYOKUINKBN01";
	public static final String MED_PERSON_ID02_COLUMN				= "MED_PERSON_ID02";
	public static final String MED_PERSON_NAME02_COLUMN			= "MED_PERSON_NAME02";
	public static final String MED_PERSON_SYOKUINKBN02_COLUMN		= "MED_PERSON_SYOKUINKBN02";
	public static final String MED_PERSON_ID03_COLUMN				= "MED_PERSON_ID03";
	public static final String MED_PERSON_NAME03_COLUMN			= "MED_PERSON_NAME03";
	public static final String MED_PERSON_SYOKUINKBN03_COLUMN		= "MED_PERSON_SYOKUINKBN03";
	public static final String MED_PERSON_ID04_COLUMN				= "MED_PERSON_ID04";
	public static final String MED_PERSON_NAME04_COLUMN			= "MED_PERSON_NAME04";
	public static final String MED_PERSON_SYOKUINKBN04_COLUMN		= "MED_PERSON_SYOKUINKBN04";
	public static final String MED_PERSON_ID05_COLUMN				= "MED_PERSON_ID05";
	public static final String MED_PERSON_NAME05_COLUMN			= "MED_PERSON_NAME05";
	public static final String MED_PERSON_SYOKUINKBN05_COLUMN		= "MED_PERSON_SYOKUINKBN05";
	public static final String MED_PERSON_ID06_COLUMN				= "MED_PERSON_ID06";
	public static final String MED_PERSON_NAME06_COLUMN			= "MED_PERSON_NAME06";
	public static final String MED_PERSON_SYOKUINKBN06_COLUMN		= "MED_PERSON_SYOKUINKBN06";
	public static final String MED_PERSON_ID07_COLUMN				= "MED_PERSON_ID07";
	public static final String MED_PERSON_NAME07_COLUMN			= "MED_PERSON_NAME07";
	public static final String MED_PERSON_SYOKUINKBN07_COLUMN		= "MED_PERSON_SYOKUINKBN07";
	public static final String MED_PERSON_ID08_COLUMN				= "MED_PERSON_ID08";
	public static final String MED_PERSON_NAME08_COLUMN			= "MED_PERSON_NAME08";
	public static final String MED_PERSON_SYOKUINKBN08_COLUMN		= "MED_PERSON_SYOKUINKBN08";
	public static final String MED_PERSON_ID09_COLUMN				= "MED_PERSON_ID09";
	public static final String MED_PERSON_NAME09_COLUMN			= "MED_PERSON_NAME09";
	public static final String MED_PERSON_SYOKUINKBN09_COLUMN		= "MED_PERSON_SYOKUINKBN09";
	public static final String MED_PERSON_ID10_COLUMN				= "MED_PERSON_ID10";
	public static final String MED_PERSON_NAME10_COLUMN			= "MED_PERSON_NAME10";
	public static final String MED_PERSON_SYOKUINKBN10_COLUMN		= "MED_PERSON_SYOKUINKBN10";

	// 2011.07.26 Add NSK_T.Koudate End
	*/
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisExMainTbl()
	{
		this.tableNameStr = TABLE_NAME;
	}

	/// <summary>
	/// 実績情報ステータスを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="orderInfoBean">オーダ情報</param>
	/// <param name="status">変更前ステータス</param>
	/// <param name="user">ユーザ情報</param>
	/// <returns>判定</returns>
	public boolean UpdateExecutionStatus( Connection con, String risID, OrderInfoBean orderInfoBean, String status, UserAccountBean user) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && orderInfoBean != null && user != null)
		{
			// ExMainTable 更新
			String sqlStr =  CreateUpdateStatusSQL(risID, orderInfoBean, status, user);
			retFlg = Update(con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="status">Update後のステータス</param>
	/// <param name="orderInfoBean">オーダ情報</param>
	/// <param name="user">Updateするユーザ情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateStatusSQL(String risID, OrderInfoBean orderInfoBean, String status, UserAccountBean user)
	{
		logger.debug("begin");
		boolean addStatusFlg = false;

		keys.clear();
		inList.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);

		if (CommonString.STATUS_UNREGISTERED.equals(status))
		{
			// To 未受付
			if (CommonString.STATUS_ISREGISTERED.equals(orderInfoBean.GetStatus()))
			{
				//受済→未受の場合
				this.AddColValue(UKETUKE_TANTOU_ID_COLUMN,		"");
				this.AddColValue(UKETUKE_TANTOU_NAME_COLUMN,	"");
				this.AddColValue(KENSATYPE_ID_COLUMN,			"");
				this.AddColValue(KENSASITU_ID_COLUMN,			"");
				this.AddColValue(KENSAKIKI_ID_COLUMN,			"");
				this.AddColValue(DENPYO_NYUGAIKBN_COLUMN,		"");
				this.AddColValue(EXAMSAVEFLAG_COLUMN,			CommonString.EXMAIN_EXAMSAVE_OFF);
				this.AddColSetValue(KENSA_DATE_AGE_COLUMN,		"null");
				this.AddColValue(TOUSITIME_COLUMN,				"");
				this.AddColValue(BAKUSYASUU_COLUMN,				"");
				this.AddColValue(KENSAI_ID_COLUMN,				"");
				this.AddColValue(KENSAI_NAME_COLUMN,			"");
				this.AddColValue(KENSA_GISI_ID_COLUMN,			"");
				this.AddColValue(KENSA_GISI_NAME_COLUMN,		"");
				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//this.AddColValue(KANGOSI_ID_COLUMN, "");
				//this.AddColValue(KANGOSI_NAME_COLUMN,			"");

				// 2011.07.26 Del NSK_T.Koudate End
				this.AddColValue(BIKOU_COLUMN,					"");
				this.AddColSetValue(EXAMSTARTDATE_COLUMN,		"null");
				this.AddColSetValue(EXAMENDDATE_COLUMN,			"null");
				this.AddColSetValue(EXAMTERMINALID_COLUMN,		"null");
				this.AddColSetValue(STARTNUMBER_COLUMN,			"null");
				this.AddColValue(GYOUMU_KBN_COLUMN,				"");
				//this.AddColSetValue(RECEIPTTERMINALID_COLUMN,	"null");
				this.AddColValue(RECEIPTFLAG_COLUMN,			CommonString.RECEIPT_FLG_OFF);
				//this.AddColSetValue(RECEIPTDATE_COLUMN,		"null");
				this.AddColSetValue(RECEIPTNUMBER_COLUMN,		"null");
				// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
				//if (Configuration.GetInstance().GetSystemID().equals(CommonString.SYSID_OMH))
				//{
				//	this.AddColSetValue(RECEIPT_INITIAL_CHAR_COLUMN, "null");
				//}
				// 2011.11.15 Add NSK_H.Hashimoto End

				// 2010.07.30 Add T.Ootsuka Start
				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//this.AddColValue(KENSA_GISI_ID2_COLUMN, "");
				//this.AddColValue(KENSA_GISI_NAME2_COLUMN,		"");
				//this.AddColValue(KENSA_GISI_ID3_COLUMN,			"");
				//this.AddColValue(KENSA_GISI_NAME3_COLUMN,		"");
				//this.AddColValue(KENSA_GISI_ID4_COLUMN,			"");
				//this.AddColValue(KENSA_GISI_NAME4_COLUMN,		"");
				//this.AddColValue(KENSA_GISI_ID5_COLUMN,			"");
				//this.AddColValue(KENSA_GISI_NAME5_COLUMN,		"");

				// 2011.07.26 Del NSK_T.Koudate End
				this.AddColValue(ENFORCEDOC_ID2_COLUMN,			"");
				this.AddColValue(ENFORCEDOC_NAME2_COLUMN,		"");
				this.AddColValue(ENFORCEDOC_ID3_COLUMN,			"");
				this.AddColValue(ENFORCEDOC_NAME3_COLUMN,		"");
				this.AddColValue(ENFORCEDOC_ID4_COLUMN,			"");
				this.AddColValue(ENFORCEDOC_NAME4_COLUMN,		"");
				this.AddColValue(ENFORCEDOC_ID5_COLUMN,			"");
				this.AddColValue(ENFORCEDOC_NAME5_COLUMN,		"");

				this.AddColValue(ENFORCEDOC_SECTION1_COLUMN,	"");
				this.AddColValue(ENFORCEDOC_SECTION2_COLUMN,	"");
				this.AddColValue(ENFORCEDOC_SECTION3_COLUMN,	"");
				this.AddColValue(ENFORCEDOC_SECTION4_COLUMN,	"");
				this.AddColValue(ENFORCEDOC_SECTION5_COLUMN,	"");

				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//this.AddColValue(KANGOSI_ID2_COLUMN, "");
				//this.AddColValue(KANGOSI_NAME2_COLUMN,			"");
				//this.AddColValue(KANGOSI_ID3_COLUMN,			"");
				//this.AddColValue(KANGOSI_NAME3_COLUMN,			"");

				// 2011.07.26 Del NSK_T.Koudate End
				this.AddColValue(JISISYA_ID_COLUMN,				"");
				this.AddColValue(JISISYA_NAME_COLUMN,			"");
				// 2010.07.30 Add T.Ootsuka End

				/* 一ノ瀬保留
				// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
				// 担当者
				this.AddColValue(MED_PERSON_ID01_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME01_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN01_COLUMN, "");
				this.AddColValue(MED_PERSON_ID02_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME02_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN02_COLUMN, "");
				this.AddColValue(MED_PERSON_ID03_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME03_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN03_COLUMN, "");
				this.AddColValue(MED_PERSON_ID04_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME04_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN04_COLUMN, "");
				this.AddColValue(MED_PERSON_ID05_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME05_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN05_COLUMN, "");
				this.AddColValue(MED_PERSON_ID06_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME06_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN06_COLUMN, "");
				this.AddColValue(MED_PERSON_ID07_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME07_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN07_COLUMN, "");
				this.AddColValue(MED_PERSON_ID08_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME08_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN08_COLUMN, "");
				this.AddColValue(MED_PERSON_ID09_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME09_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN09_COLUMN, "");
				this.AddColValue(MED_PERSON_ID10_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME10_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN10_COLUMN, "");

				// 2011.07.26 Add NSK_T.Koudate End
				*/

				//# region コメント化(自動印刷-受付時-初回のみ で利用) 2011.09.14 Del H.Orikasa NML-CAT9-032
				//// 2011.02.21 Add Yk.Suzuki@CIJ Start 受済時刻クリア
				//// 受付時刻クリアフラグが立っている場合は、
				//// DBの受付時刻をクリアする
				//boolean receiptDateClearFlg = Configuration.GetInstance().GetDynamicPropertyBool("ListBaseForm.DisReceiptClear.Value");
				//if (receiptDateClearFlg)
				//{
				//    this.AddColSetValue(RECEIPTDATE_COLUMN, "null");
				//}
				//// 2011.02.21 Add Yk.Suzuki@CIJ End

			}
			else if (CommonString.STATUS_REREGISTERED.equals(orderInfoBean.GetStatus()) ||
					 CommonString.STATUS_RECALLING.equals(orderInfoBean.GetStatus()))
			{
				//再呼･再受→未受の場合、ステータスを保留に設定
				this.AddColValue(STATUS_COLUMN, CommonString.STATUS_REST);
				addStatusFlg = true;
			}

			if (CommonString.STATUS_ISCALLING.equals(orderInfoBean.GetStatus()) ||
				CommonString.STATUS_RECALLING.equals(orderInfoBean.GetStatus()))
			{
				//呼出･再呼→未受の場合、呼出日時をクリアする
				this.AddColSetValue(YOBIDASI_DATE_COLUMN,		"null");
			}

		}
		else if (CommonString.STATUS_ISLATE.equals(status))
		{
			// To 遅刻

		}
		else if (CommonString.STATUS_ISCALLING.equals(status))
		{
			// To 呼出
			if (CommonString.STATUS_REST.equals(orderInfoBean.GetStatus()))
			{
				//保留の場合、ステータスを再呼に設定
				this.AddColValue(STATUS_COLUMN, CommonString.STATUS_RECALLING);
				addStatusFlg = true;
			}
			this.AddColSetValue(YOBIDASI_DATE_COLUMN, SysDateTbl.SYSDATE_COLUMN);

		}
		else if (CommonString.STATUS_ISREGISTERED.equals(status))
		{
			// To 受済
			this.AddColValue(KENSATYPE_ID_COLUMN,			orderInfoBean.GetKensatypeID());

			//実績検査日の設定
			if (orderInfoBean.IsReceitpDateFlg())
			{
				// 実績検査日設定→当日設定の場合

				// 当日を設定
				this.AddColSetValue(KENSA_DATE_COLUMN,		"TO_NUMBER(TO_CHAR(SYSDATE, 'YYYYMMDD'))");
			}
			else
			{
				// 実績検査日設定→予定日の場合

				// 2011.08.26 Mod NSK_H.Hashimoto Start NML-CAT1-002
				// コメント
				//this.AddColValue(KENSA_DATE_COLUMN,			orderInfoBean.GetKensaDate());

				String strKensaDate		= (orderInfoBean.GetKensaDate()).toString();
				String strUnknownDate	= Configuration.GetInstance().GetUnKnownDateString();

				if (strKensaDate.equals(strUnknownDate))
				{
					// 日未定の場合は、当日を設定
					this.AddColSetValue(KENSA_DATE_COLUMN,	"TO_NUMBER(TO_CHAR(SYSDATE, 'YYYYMMDD'))");
				}
				else
				{
					// 日未定ではない場合は、予定日を設定
					this.AddColValue(KENSA_DATE_COLUMN,		orderInfoBean.GetKensaDate());
				}
				// 2011.08.26 Mod NSK_H.Hashimoto End
			}
			this.AddColValue(KENSASITU_ID_COLUMN,			orderInfoBean.GetKensasituID());
			this.AddColValue(KENSAKIKI_ID_COLUMN,			orderInfoBean.GetKensakikiID());
			this.AddColValue(DENPYO_NYUGAIKBN_COLUMN,		orderInfoBean.GetDenpyoNyugaiKbn());
			this.AddColValue(UKETUKE_TANTOU_ID_COLUMN,		user.GetStaffID());
			this.AddColValue(UKETUKE_TANTOU_NAME_COLUMN,	user.GetUserName());
			this.AddColValue(RECEIPTTERMINALID_COLUMN,		orderInfoBean.GetTerminalID());
			this.AddColValue(RECEIPTFLAG_COLUMN,			CommonString.RECEIPT_FLG_ON);
			this.AddColSetValue(RECEIPTDATE_COLUMN,			SysDateTbl.SYSDATE_COLUMN);
			this.AddColValue(RECEIPTNUMBER_COLUMN,			orderInfoBean.GetReceptNumber());
			// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
			//if (Configuration.GetInstance().GetSystemID().equals(CommonString.SYSID_OMH))
			//{
			//	this.AddColValue(RECEIPT_INITIAL_CHAR_COLUMN, orderInfoBean.GetReceptInitialChar());
			//}
			// 2011.11.15 Add NSK_H.Hashimoto End

			if (CommonString.STATUS_REST.equals(orderInfoBean.GetStatus()))
			{
				//保留の場合、ステータスを再受に設定
				this.AddColValue(STATUS_COLUMN, CommonString.STATUS_REREGISTERED);
				addStatusFlg = true;
			}

			if (CommonString.STATUS_ISFINISHED.equals(orderInfoBean.GetStatus()))
			{
				//検済の場合
				this.AddColSetValue(KENSA_DATE_AGE_COLUMN,			"null");
				this.AddColValue(TOUSITIME_COLUMN,					"");
				this.AddColValue(BAKUSYASUU_COLUMN,					"");
				this.AddColValue(KENSAI_ID_COLUMN,					"");
				this.AddColValue(KENSAI_NAME_COLUMN,				"");
				this.AddColValue(KENSA_GISI_ID_COLUMN,				"");
				this.AddColValue(KENSA_GISI_NAME_COLUMN,			"");
				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//this.AddColValue(KANGOSI_ID_COLUMN, "");
				//this.AddColValue(KANGOSI_NAME_COLUMN,				"");

				// 2011.07.26 Del NSK_T.Koudate End
				this.AddColValue(BIKOU_COLUMN,						"");
				this.AddColSetValue(EXAMSTARTDATE_COLUMN,			"null");
				this.AddColSetValue(EXAMENDDATE_COLUMN,				"null");
				this.AddColSetValue(EXAMTERMINALID_COLUMN,			"null");
				this.AddColSetValue(STARTNUMBER_COLUMN,				"null");
				this.AddColValue(GYOUMU_KBN_COLUMN,					"");
				this.AddColValue(SIJI_ISI_ID_COLUMN,				"");
				this.AddColValue(SIJI_ISI_NAME_COLUMN,				"");
				this.AddColValue(SIJI_ISI_COMMENT_COLUMN,			"");
				//this.AddColValue(YUUSEN_FLG_COLUMN,				"");
				this.AddColValue(EXAMSAVEFLAG_COLUMN,				CommonString.EXMAIN_EXAMSAVE_OFF);
				this.AddColValue(ENFORCEDOC_ID_COLUMN,				"");
				this.AddColValue(ENFORCEDOC_NAME_COLUMN,			"");
				this.AddColValue(ENDCOUNT_COLUMN,					0);
				this.AddColSetValue(DOUISHO_CHECK_NAME_COLUMN,		"null");
				this.AddColValue(DOUISHO_CHECK_FLAG_COLUMN,			0);
				this.AddColSetValue(INFECTION_CHECK_NAME_COLUMN,	"null");
				this.AddColValue(INFECTION_CHECK_FLAG_COLUMN,		0);
				this.AddColSetValue(YOBIDASI_DATE_COLUMN,			"null");
				// 2010.07.30 Add T.Ootsuka Start
				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//this.AddColValue(KENSA_GISI_ID2_COLUMN, "");
				//this.AddColValue(KENSA_GISI_NAME2_COLUMN,			"");
				//this.AddColValue(KENSA_GISI_ID3_COLUMN,				"");
				//this.AddColValue(KENSA_GISI_NAME3_COLUMN,			"");
				//this.AddColValue(KENSA_GISI_ID4_COLUMN,				"");
				//this.AddColValue(KENSA_GISI_NAME4_COLUMN,			"");
				//this.AddColValue(KENSA_GISI_ID5_COLUMN,				"");
				//this.AddColValue(KENSA_GISI_NAME5_COLUMN,			"");

				// 2011.07.26 Del NSK_T.Koudate End
				this.AddColValue(ENFORCEDOC_ID2_COLUMN,				"");
				this.AddColValue(ENFORCEDOC_NAME2_COLUMN,			"");
				this.AddColValue(ENFORCEDOC_ID3_COLUMN,				"");
				this.AddColValue(ENFORCEDOC_NAME3_COLUMN,			"");
				this.AddColValue(ENFORCEDOC_ID4_COLUMN,				"");
				this.AddColValue(ENFORCEDOC_NAME4_COLUMN,			"");
				this.AddColValue(ENFORCEDOC_ID5_COLUMN,				"");
				this.AddColValue(ENFORCEDOC_NAME5_COLUMN,			"");

				this.AddColValue(ENFORCEDOC_SECTION1_COLUMN,		"");
				this.AddColValue(ENFORCEDOC_SECTION2_COLUMN,		"");
				this.AddColValue(ENFORCEDOC_SECTION3_COLUMN,		"");
				this.AddColValue(ENFORCEDOC_SECTION4_COLUMN,		"");
				this.AddColValue(ENFORCEDOC_SECTION5_COLUMN,		"");

				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//this.AddColValue(KANGOSI_ID2_COLUMN, "");
				//this.AddColValue(KANGOSI_NAME2_COLUMN,				"");
				//this.AddColValue(KANGOSI_ID3_COLUMN,				"");
				//this.AddColValue(KANGOSI_NAME3_COLUMN,				"");

				// 2011.07.26 Del NSK_T.Koudate End
				this.AddColValue(JISISYA_ID_COLUMN,					"");
				this.AddColValue(JISISYA_NAME_COLUMN,				"");
				// 2010.07.30 Add T.Ootsuka End

				/* 一ノ瀬保留
				// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
				// 担当者
				this.AddColValue(MED_PERSON_ID01_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME01_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN01_COLUMN, "");
				this.AddColValue(MED_PERSON_ID02_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME02_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN02_COLUMN, "");
				this.AddColValue(MED_PERSON_ID03_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME03_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN03_COLUMN, "");
				this.AddColValue(MED_PERSON_ID04_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME04_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN04_COLUMN, "");
				this.AddColValue(MED_PERSON_ID05_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME05_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN05_COLUMN, "");
				this.AddColValue(MED_PERSON_ID06_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME06_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN06_COLUMN, "");
				this.AddColValue(MED_PERSON_ID07_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME07_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN07_COLUMN, "");
				this.AddColValue(MED_PERSON_ID08_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME08_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN08_COLUMN, "");
				this.AddColValue(MED_PERSON_ID09_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME09_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN09_COLUMN, "");
				this.AddColValue(MED_PERSON_ID10_COLUMN, "");
				this.AddColValue(MED_PERSON_NAME10_COLUMN, "");
				this.AddColValue(MED_PERSON_SYOKUINKBN10_COLUMN, "");

				// 2011.07.26 Add NSK_T.Koudate End
				*/
			}

		}
		else if (CommonString.STATUS_RECALLING.equals(status))
		{
			// To 再呼
			this.AddColSetValue(YOBIDASI_DATE_COLUMN, SysDateTbl.SYSDATE_COLUMN); // 2011.08.15 Add H.Orikasa A0088

		}
		else if (CommonString.STATUS_REREGISTERED.equals(status))
		{
			// To 再受付

			this.AddColSetValue(RECEIPTDATE_COLUMN,			SysDateTbl.SYSDATE_COLUMN);

			// 2011.09.14 Add H.Orikasa Start NML-CAT2-017
			// 受付番号:患者単位の場合は再受付時に受付番号を発番する
			if (CommonString.SYSTEMPARAM_1.equals(Configuration.GetInstance().GetSystemParam().GetReceiptKanjaValue1Str()))
			{
				this.AddColValue(RECEIPTNUMBER_COLUMN, orderInfoBean.GetReceptNumber());

				// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
				if (Configuration.GetInstance().GetSystemID().equals(CommonString.SYSID_OMH))
				{
					// 受付番号の頭文字を取得
					this.AddColValue(RECEIPT_INITIAL_CHAR_COLUMN, orderInfoBean.GetReceptInitialChar());
				}
				// 2011.11.15 Add NSK_H.Hashimoto End
			}


		}
		//
		if (!addStatusFlg)
		{
			this.AddColValue(STATUS_COLUMN, Integer.parseInt(status));
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/// <summary>
	/// 実績情報ステータスを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="executionInfoBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean UpdateExecutionKensaDateAge(Connection con, String risID, ExecutionInfoBean executionInfoBean) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && executionInfoBean != null)
		{
			// ExMainTable 更新
			retFlg = Update(con, CreateUpdateKensaDateAgeSQL(risID, executionInfoBean), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="executionInfoBean">Update用実績情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateKensaDateAgeSQL(String risID, ExecutionInfoBean executionInfoBean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);

		if (CommonString.STATUS_ISREGISTERED.equals(executionInfoBean.GetStatus()))
		{
			// 受済
			this.AddColValue(KENSA_DATE_AGE_COLUMN, executionInfoBean.GetKensaDateAge());
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/*
	/// <summary>
	/// 連絡メモ情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="userAccount">ユーザ情報</param>
	/// <param name="memo">連絡メモ</param>
	/// <returns>判定</returns>
	public boolean UpdateRisRenrakuMemo( Connection con, Transaction trans, String risID, UserAccountBean userAccount, String memo)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null )
		{
			// ExMainTable 更新
			retBool = Update(con, trans, CreateUpdateRisRenrakuMemoSql(risID, userAccount, false, memo));
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 検査室情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <returns>判定</returns>
	public boolean UpdateRisExRoom( Connection con, Transaction trans, String risID, String exRoomID)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null )
		{
			// ExMainTable 更新
			retBool = Update( con, trans, CreateUpdateRisExRoomIDSQL(risID, false, exRoomID) );
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	// 2010.07.30 Add DD.Chinh Start
	/// <summary>
	/// 検査室、検査機器情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <param name="exKikiID">検査機器情報</param>
	/// <returns>判定</returns>
	public boolean UpdateRisExamRoomMachine(Connection con, Transaction trans, String risID, String exRoomID, String exKikiID)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			// ExMainTable 更新
			retBool = Update(con, trans, CreateUpdateExamRoomMachineIDSQL(risID, false, exRoomID, exKikiID));
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <param name="exKikiID">検査機器情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateExamRoomMachineIDSQL(String risID, boolean extend, String exRoomID, String exKikiID)
	{
		logger.debug("begin");

		if (!extend)
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
			this.AddColValue(KENSASITU_ID_COLUMN, exRoomID);
			this.AddColValue(KENSAKIKI_ID_COLUMN, exKikiID);
		}
		else
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	// 2010.07.30 Add DD.Chinh End
	*/

	/*
	/// <summary>
	/// 優先ステータスを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RisID</param>
	/// <param name="yuusenFlg">優先ステータス</param>
	/// <returns>判定</returns>
	public boolean UpdateExecutionYuusenStatus( Connection con, Transaction trans, String risID, String yuusenFlg)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null  )
		{
			// ExMainTable 更新
			retFlg = Update( con, trans, CreateUpdateYuusenStatusSQL(risID, false, yuusenFlg) );
		}

		// end log
		logger.debug("end");

		return retFlg;

	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="user">ユーザ情報</param>
	/// <param name="extend"></param>
	/// <param name="memo">メモ情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisRenrakuMemoSql(String risID, UserAccountBean user, boolean extend, String memo)
	{
		logger.debug("begin");

		if(!extend)
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
			this.AddColValue(RENRAKU_MEMO_COLUMN, memo);
		}
		else
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisExRoomIDSQL( String risID, boolean extend, String exRoomID)
	{
		logger.debug("begin");

		if(!extend)
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
			this.AddColValue(KENSASITU_ID_COLUMN, exRoomID);
		}
		else
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risID">Update対象データの内部UID</param>
	/// <param name="status">Update後のステータス</param>
	/// <returns>判定</returns>
	private String CreateUpdateYuusenStatusSQL( String risID, boolean extend, String yuusenFlg)
	{
		logger.debug("begin");

		if(!extend)
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
			this.AddColValue(YUUSEN_FLG_COLUMN, yuusenFlg);
		}
		else
		{
			keys.clear();
			inList.clear();
			this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/// <summary>
	///	 実績情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">risID</param>
	/// <returns>実績情報</returns>
	public ExecutionInfoBean GetExecutionInfoBean(Connection con, String risID) throws Exception
	{
		// parameters
		ExecutionInfoBean executionInfoBean = new ExecutionInfoBean();

		// begin log
		logger.debug("begin");

		if( con != null && risID != null )
		{
			String sql = CreateSelectSQL(risID);
			DataTable dt = Select( con, sql, null );
			if( dt != null && dt.getRowCount() == 1 )
			{
				DataRow row = dt.getRows().get(0);

				// beanへ設定

				if ( row.get(RisExMainTbl.RIS_ID_COLUMN) != null )
				{
					executionInfoBean.SetRisID(row.get(RisExMainTbl.RIS_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.KENSATYPE_ID_COLUMN) != null )
				{
					executionInfoBean.SetKensaTypeID(row.get(RisExMainTbl.KENSATYPE_ID_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.KENSA_DATE_COLUMN) != null)
				{
					executionInfoBean.SetKensaDate(row.get(RisExMainTbl.KENSA_DATE_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.KENSASITU_ID_COLUMN) != null )
				{
					executionInfoBean.SetKensaSituID(row.get(RisExMainTbl.KENSASITU_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.KENSAKIKI_ID_COLUMN) != null )
				{
					executionInfoBean.SetKensaKikiID(row.get(RisExMainTbl.KENSAKIKI_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.KANJA_ID_COLUMN) != null )
				{
					executionInfoBean.SetKanjaID(row.get(RisExMainTbl.KANJA_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.KENSA_DATE_AGE_COLUMN) != null )
				{
					executionInfoBean.SetKensaDateAge(row.get(RisExMainTbl.KENSA_DATE_AGE_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.DENPYO_NYUGAIKBN_COLUMN) != null )
				{
					executionInfoBean.SetDenpyoNyugaiKbn(row.get(RisExMainTbl.DENPYO_NYUGAIKBN_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.UKETUKE_TANTOU_ID_COLUMN) != null )
				{
					executionInfoBean.SetUketukeTantouID(row.get(RisExMainTbl.UKETUKE_TANTOU_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.UKETUKE_TANTOU_NAME_COLUMN) != null )
				{
					executionInfoBean.SetUketukeTantouName(row.get(RisExMainTbl.UKETUKE_TANTOU_NAME_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.RECEIPTDATE_COLUMN) != null )
				{
					executionInfoBean.SetReceiptDateTime((Timestamp)row.get(RisExMainTbl.RECEIPTDATE_COLUMN));
				}
				if( row.get(RisExMainTbl.RECEIPTTERMINALID_COLUMN) != null )
				{
					executionInfoBean.SetReceiptTerminalID(row.get(RisExMainTbl.RECEIPTTERMINALID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.RECEIPTNUMBER_COLUMN) != null )
				{
					executionInfoBean.SetReceiptNumber(row.get(RisExMainTbl.RECEIPTNUMBER_COLUMN).toString());
				}
				//// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
				//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
				//{
				//	if (row.get(RisExMainTbl.RECEIPT_INITIAL_CHAR_COLUMN) != null)
				//	{
				//		executionInfoBean.SetReceiptInitialChar(row.get(RisExMainTbl.RECEIPT_INITIAL_CHAR_COLUMN).toString());
				//	}
				//}
				// 2011.11.15 Add NSK_H.Hashimoto End
				if (row.get(RisExMainTbl.KENSA_GISI_ID_COLUMN) != null)
				{
					executionInfoBean.SetKensaGisiID(row.get(RisExMainTbl.KENSA_GISI_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.KENSA_GISI_NAME_COLUMN) != null )
				{
					executionInfoBean.SetKensaGisiName(row.get(RisExMainTbl.KENSA_GISI_NAME_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.EXAMSTARTDATE_COLUMN) != null )
				{
					executionInfoBean.SetExamStartDateTime((Timestamp)row.get(RisExMainTbl.EXAMSTARTDATE_COLUMN));
				}
				if( row.get(RisExMainTbl.EXAMENDDATE_COLUMN) != null )
				{
					executionInfoBean.SetExamEndDateTime((Timestamp)row.get(RisExMainTbl.EXAMENDDATE_COLUMN));
				}
				if( row.get(RisExMainTbl.EXAMTERMINALID_COLUMN) != null )
				{
					executionInfoBean.SetExamTerminalID(row.get(RisExMainTbl.EXAMTERMINALID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.STARTNUMBER_COLUMN) != null )
				{
					executionInfoBean.SetStartNumberStr(row.get(RisExMainTbl.STARTNUMBER_COLUMN).toString());
				}
				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//if (row.get(RisExMainTbl.KANGOSI_ID_COLUMN) != null)
				//{
				//    executionInfoBean.SetKangosiID(row.get(RisExMainTbl.KANGOSI_ID_COLUMN).toString());
				//}
				//if( row.get(RisExMainTbl.KANGOSI_NAME_COLUMN) != null )
				//{
				//    executionInfoBean.SetKangosiName(row.get(RisExMainTbl.KANGOSI_NAME_COLUMN).toString());
				//}

				// 2011.07.26 Del NSK_T.Koudate End
				if( row.get(RisExMainTbl.KENSAI_ID_COLUMN) != null )
				{
					executionInfoBean.SetKensaDoctorID(row.get(RisExMainTbl.KENSAI_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.KENSAI_NAME_COLUMN) != null )
				{
					executionInfoBean.SetKensaDoctorName(row.get(RisExMainTbl.KENSAI_NAME_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.BIKOU_COLUMN) != null )
				{
					executionInfoBean.SetBikou(row.get(RisExMainTbl.BIKOU_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.RENRAKU_MEMO_COLUMN) != null )
				{
					executionInfoBean.SetRenrakuMemo(row.get(RisExMainTbl.RENRAKU_MEMO_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.SIJI_ISI_ID_COLUMN) != null )
				{
					executionInfoBean.SetSijiIsiID(row.get(RisExMainTbl.SIJI_ISI_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.SIJI_ISI_NAME_COLUMN) != null )
				{
					executionInfoBean.SetSijiIsiName(row.get(RisExMainTbl.SIJI_ISI_NAME_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.SIJI_ISI_COMMENT_COLUMN) != null )
				{
					executionInfoBean.SetSijiIsiComment(row.get(RisExMainTbl.SIJI_ISI_COMMENT_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.TOUSITIME_COLUMN) != null )
				{
					executionInfoBean.SetTousiTime(row.get(RisExMainTbl.TOUSITIME_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.BAKUSYASUU_COLUMN) != null )
				{
					executionInfoBean.SetBakushaSuu(row.get(RisExMainTbl.BAKUSYASUU_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.GYOUMU_KBN_COLUMN) != null )
				{
					executionInfoBean.SetGyoumuKbn(row.get(RisExMainTbl.GYOUMU_KBN_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.STATUS_COLUMN) != null )
				{
					executionInfoBean.SetStatus(row.get(RisExMainTbl.STATUS_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.RECEIPTFLAG_COLUMN) != null )
				{
					executionInfoBean.SetReceiptFlg(row.get(RisExMainTbl.RECEIPTFLAG_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.YUUSEN_FLG_COLUMN) != null )
				{
					executionInfoBean.SetYuusenFlg(row.get(RisExMainTbl.YUUSEN_FLG_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.EXAMSAVEFLAG_COLUMN) != null )
				{
					executionInfoBean.SetExamSaveFlg(row.get(RisExMainTbl.EXAMSAVEFLAG_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.ENFORCEDOC_ID_COLUMN) != null )
				{
					executionInfoBean.SetEnforceDocID(row.get(RisExMainTbl.ENFORCEDOC_ID_COLUMN).toString());
				}
				if( row.get(RisExMainTbl.ENFORCEDOC_NAME_COLUMN) != null )
				{
					executionInfoBean.SetEnforceDocName(row.get(RisExMainTbl.ENFORCEDOC_NAME_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENDCOUNT_COLUMN) != null)
				{
					executionInfoBean.SetEndCountStr(row.get(RisExMainTbl.ENDCOUNT_COLUMN).toString());
				}
				// 2010.07.30 Add T.Ootsuka Start
				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//if (row.get(RisExMainTbl.KENSA_GISI_ID2_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiID2(row.get(RisExMainTbl.KENSA_GISI_ID2_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KENSA_GISI_NAME2_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiName2(row.get(RisExMainTbl.KENSA_GISI_NAME2_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KENSA_GISI_ID3_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiID3(row.get(RisExMainTbl.KENSA_GISI_ID3_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KENSA_GISI_NAME3_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiName3(row.get(RisExMainTbl.KENSA_GISI_NAME3_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KENSA_GISI_ID4_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiID4(row.get(RisExMainTbl.KENSA_GISI_ID4_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KENSA_GISI_NAME4_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiName4(row.get(RisExMainTbl.KENSA_GISI_NAME4_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KENSA_GISI_ID5_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiID5(row.get(RisExMainTbl.KENSA_GISI_ID5_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KENSA_GISI_NAME5_COLUMN) != null)
				//{
				//    executionInfoBean.SetKensaGisiName5(row.get(RisExMainTbl.KENSA_GISI_NAME5_COLUMN).toString());
				//}

				// 2011.07.26 Del NSK_T.Koudate End
				if (row.get(RisExMainTbl.ENFORCEDOC_ID2_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocID2(row.get(RisExMainTbl.ENFORCEDOC_ID2_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_NAME2_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocName2(row.get(RisExMainTbl.ENFORCEDOC_NAME2_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_ID3_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocID3(row.get(RisExMainTbl.ENFORCEDOC_ID3_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_NAME3_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocName3(row.get(RisExMainTbl.ENFORCEDOC_NAME3_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_ID4_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocID4(row.get(RisExMainTbl.ENFORCEDOC_ID4_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_NAME4_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocName4(row.get(RisExMainTbl.ENFORCEDOC_NAME4_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_ID5_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocID5(row.get(RisExMainTbl.ENFORCEDOC_ID5_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_NAME5_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocName5(row.get(RisExMainTbl.ENFORCEDOC_NAME5_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_SECTION1_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocSectionID1(row.get(RisExMainTbl.ENFORCEDOC_SECTION1_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_SECTION2_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocSectionID2(row.get(RisExMainTbl.ENFORCEDOC_SECTION2_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_SECTION3_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocSectionID3(row.get(RisExMainTbl.ENFORCEDOC_SECTION3_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_SECTION4_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocSectionID4(row.get(RisExMainTbl.ENFORCEDOC_SECTION4_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.ENFORCEDOC_SECTION5_COLUMN) != null)
				{
					executionInfoBean.SetEnforceDocSectionID5(row.get(RisExMainTbl.ENFORCEDOC_SECTION5_COLUMN).toString());
				}
				// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
				// コメント(担当者にまとめるため廃止)
				//if (row.get(RisExMainTbl.KANGOSI_ID2_COLUMN) != null)
				//{
				//    executionInfoBean.SetKangosiID2(row.get(RisExMainTbl.KANGOSI_ID2_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KANGOSI_NAME2_COLUMN) != null)
				//{
				//    executionInfoBean.SetKangosiName2(row.get(RisExMainTbl.KANGOSI_NAME2_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KANGOSI_ID3_COLUMN) != null)
				//{
				//    executionInfoBean.SetKangosiID3(row.get(RisExMainTbl.KANGOSI_ID3_COLUMN).toString());
				//}
				//if (row.get(RisExMainTbl.KANGOSI_NAME3_COLUMN) != null)
				//{
				//    executionInfoBean.SetKangosiName3(row.get(RisExMainTbl.KANGOSI_NAME3_COLUMN).toString());
				//}

				// 2011.07.26 Del NSK_T.Koudate End
				if (row.get(RisExMainTbl.JISISYA_ID_COLUMN) != null)
				{
					executionInfoBean.SetJisisyaID(row.get(RisExMainTbl.JISISYA_ID_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.JISISYA_NAME_COLUMN) != null)
				{
					executionInfoBean.SetJisisyaName(row.get(RisExMainTbl.JISISYA_NAME_COLUMN).toString());
				}
				/*
				// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
				if (row.get(RisExMainTbl.UNKNOWNDATE_FLG_COLUMN) != null)
				{
					executionInfoBean.SetUnknownDateFlg(row.get(RisExMainTbl.UNKNOWNDATE_FLG_COLUMN).toString());
				}
				// 2011.07.11 Add NSK_H.Hashimoto End
				// 2010.07.30 Add T.Ootsuka End
				// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
				//関東労災による特注処理対応
				//if (dt.Columns.Contains(DRESSING_ROOM_ID_COLUMN) && row.get(DRESSING_ROOM_ID_COLUMN) != null)
				//{
				//	executionInfoBean.SetDressingRoomId(row.get(DRESSING_ROOM_ID_COLUMN).toString());
				//}
				// 2010.11.16 Add T.Nishikubo End

				// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
				// 担当者
				if (row.get(RisExMainTbl.MED_PERSON_ID01_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID01(row.get(RisExMainTbl.MED_PERSON_ID01_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME01_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName01(row.get(RisExMainTbl.MED_PERSON_NAME01_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN01_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn01(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN01_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID02_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID02(row.get(RisExMainTbl.MED_PERSON_ID02_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME02_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName02(row.get(RisExMainTbl.MED_PERSON_NAME02_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN02_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn02(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN02_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID03_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID03(row.get(RisExMainTbl.MED_PERSON_ID03_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME03_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName03(row.get(RisExMainTbl.MED_PERSON_NAME03_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN03_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn03(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN03_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID04_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID04(row.get(RisExMainTbl.MED_PERSON_ID04_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME04_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName04(row.get(RisExMainTbl.MED_PERSON_NAME04_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN04_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn04(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN04_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID05_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID05(row.get(RisExMainTbl.MED_PERSON_ID05_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME05_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName05(row.get(RisExMainTbl.MED_PERSON_NAME05_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN05_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn05(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN05_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID06_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID06(row.get(RisExMainTbl.MED_PERSON_ID06_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME06_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName06(row.get(RisExMainTbl.MED_PERSON_NAME06_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN06_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn06(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN06_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID07_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID07(row.get(RisExMainTbl.MED_PERSON_ID07_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME07_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName07(row.get(RisExMainTbl.MED_PERSON_NAME07_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN07_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn07(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN07_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID08_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID08(row.get(RisExMainTbl.MED_PERSON_ID08_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME08_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName08(row.get(RisExMainTbl.MED_PERSON_NAME08_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN08_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn08(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN08_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID09_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID09(row.get(RisExMainTbl.MED_PERSON_ID09_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME09_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName09(row.get(RisExMainTbl.MED_PERSON_NAME09_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN09_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn09(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN09_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_ID10_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonID10(row.get(RisExMainTbl.MED_PERSON_ID10_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_NAME10_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonName10(row.get(RisExMainTbl.MED_PERSON_NAME10_COLUMN).toString());
				}
				if (row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN10_COLUMN) != null)
				{
					executionInfoBean.SetMedPersonSyokuinKbn10(row.get(RisExMainTbl.MED_PERSON_SYOKUINKBN10_COLUMN).toString());
				}

				// 2011.07.26 Add NSK_T.Koudate End
				*/
			}
		}

		// end log
		logger.debug("end");

		return executionInfoBean;
	}

	/*
	/// <summary>
	///	 ステータスの取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">risID</param>
	/// <returns>ステータス名</returns>
	public String GetStatus( Connection con, Transaction trans, String risID )
	{
		// parameters
		String status = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null )
		{
			String sql = CreateSelectSQL(risID);
			DataTable dt = Select( con, trans, sql );
			if( dt != null && dt.Rows.Count == 1 )
			{
				if( dt.Rows[0][RisSummaryView.STATUS_COLUMN] != System.DBNull.Value )
				{
					status = dt.Rows[0][RisSummaryView.STATUS_COLUMN].toString();
				}
			}
		}

		// end log
		logger.debug("end");

		return status;
	}
	*/

	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();
		this.AddColValue(RIS_ID_COLUMN, risID,true,SignType.Equal);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	/// <summary>
	/// 実績情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean SaveExecutionInfo(Connection con, ExecutionInfoBean exBean) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && exBean != null && exBean.GetRisID().length() > 0)
		{
			// ExMainTable 更新
			String sqlStr = CreateSaveSQL(exBean);
			retFlg = Update(con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <param name="extend">拡張テーブルフラグ</param>
	/// <returns></returns>
	private String CreateSaveSQL(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		SetColValue(exBean);

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/// <summary>
	/// 実績メイン情報の検査日、ステータスを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>判定</returns>
	public boolean UpdateKensaDateStatus(Connection con, ExecutionInfoBean exBean) throws Exception
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && exBean != null && exBean.GetRisID().length() > 0)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateKensaDateStatusSQL(exBean);
			retBool = Update(con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateKensaDateStatusSQL(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, exBean.GetRisID(), true, SignType.Equal);
		//
		this.AddColValue(KENSA_DATE_COLUMN, exBean.GetKensaDateInt());
		if (exBean.GetStatus().length() > 0)
		{
			this.AddColValue(STATUS_COLUMN, Integer.parseInt(exBean.GetStatus()));
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/// <summary>
	/// 実績情報を更新する(検査開始)
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean UpdateKensaStartProc(Connection con, ExecutionInfoBean exBean) throws Exception
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && exBean != null && exBean.GetRisID().length() > 0)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateKensaStartProcSql(exBean);
			retBool = Update(con, sqlStr, null);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateKensaStartProcSql(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN,			exBean.GetRisID(), true, SignType.Equal);
		//
		this.AddColValue(EXAMSTARTDATE_COLUMN,	exBean.GetExamStartDateTime());

		this.AddColValue(EXAMTERMINALID_COLUMN, exBean.GetExamTerminalIDInt());
		this.AddColValue(STARTNUMBER_COLUMN,	exBean.GetStartNumber());
		this.AddColValue(GYOUMU_KBN_COLUMN,		exBean.GetGyoumuKbn());
		this.AddColValue(STATUS_COLUMN,			exBean.GetStatusInt());
		this.AddColValue(KENSASITU_ID_COLUMN,	exBean.GetKensaSituID());
		this.AddColValue(KENSAKIKI_ID_COLUMN,	exBean.GetKensaKikiID());

		// 2011.03.18 Del K.Shinohara Start
		// 検査開始時は保存フラグを変更しない
		//this.AddColValue(EXAMSAVEFLAG_COLUMN,	exBean.GetExamSaveFlg());
		// 2011.03.18 Del K.Shinohara End

		logger.debug("end");

		return this.GetUpdateSQL();
	}

	/*
	/// <summary>
	/// 実績情報の連絡メモを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>判定</returns>
	public boolean UpdateExecutionRenrakuMemo(Connection con, Transaction trans, ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && exBean != null && exBean.GetRisID().length() > 0)
		{
			// ExMainTable 更新
			String sqlStr = CreateExecutionRenrakuMemoSql(exBean);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>判定</returns>
	private String CreateExecutionRenrakuMemoSql(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, exBean.GetRisID(), true, SignType.Equal);
		//
		this.AddColValue(RENRAKU_MEMO_COLUMN, exBean.GetRenrakuMemo());

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// 実績情報の備考を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risIDStr">RisID</param>
	/// <param name="bikouStr">備考</param>
	/// <returns>判定</returns>
	public boolean UpdateExecutionBikou(Connection con, Transaction trans, String risIDStr, String bikouStr)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risIDStr.length() > 0)
		{
			// ExMainTable 更新
			String sqlStr = CreateExecutionBikouSql(risIDStr, bikouStr);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="risIDStr">RisID</param>
	/// <param name="bikouStr">備考</param>
	/// <returns></returns>
	private String CreateExecutionBikouSql(String risIDStr, String bikouStr)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risIDStr, true, SignType.Equal);
		//
		this.AddColValue(BIKOU_COLUMN, bikouStr);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean RestoreKensaData(Connection con, Transaction trans, ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && exBean != null && exBean.GetRisID().length() > 0)
		{
			// ExMainTable 更新
			String sqlStr = CreateRestoreKensaDataSql(exBean);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>判定</returns>
	private String CreateRestoreKensaDataSql(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN,					exBean.GetRisID(), true, SignType.Equal);
		//
		this.AddColValue(KENSA_DATE_COLUMN,				exBean.GetKensaDateInt());
		if (exBean.GetExamStartTimestamp() == Timestamp.MinValue)
		{
			this.AddColSetValue(EXAMSTARTDATE_COLUMN,	"NULL");
		}
		else
		{
			this.AddColValue(EXAMSTARTDATE_COLUMN,		exBean.GetExamStartTimestamp());
		}
		//2010.09.19 Mod H.Orikasa Start
		if (exBean.GetExamTerminalID().length() > 0)
		{
			this.AddColValue(EXAMTERMINALID_COLUMN, exBean.GetExamTerminalIDInt());
		}
		else
		{
			this.AddColSetValue(EXAMTERMINALID_COLUMN, "NULL");
		}
		//this.AddColValue(EXAMTERMINALID_COLUMN,			exBean.GetExamTerminalIDInt());
		//2010.09.19 Mod H.Orikasa End
		this.AddColValue(STARTNUMBER_COLUMN,			exBean.GetStartNumber());
		this.AddColValue(GYOUMU_KBN_COLUMN,				exBean.GetGyoumuKbn());
		this.AddColValue(STATUS_COLUMN,					exBean.GetStatusInt());
		this.AddColValue(EXAMSAVEFLAG_COLUMN,			exBean.GetExamSaveFlg());

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	// 2011.08.16 Add DD.Chinh@MERX Start NML-CAT2-010
	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean UnReceiptData(Connection con, Transaction trans, ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && exBean != null && exBean.GetRisID().length() > 0)
		{
			// ExMainTable 更新
			String sqlStr = CreateUnReceiptDataSql(exBean);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>SQL文</returns>
	private String CreateUnReceiptDataSql(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN,			exBean.GetRisID(), true, SignType.Equal);
		//
		this.AddColValue(STATUS_COLUMN,			exBean.GetStatusInt());
		this.AddColValue(EXAMSTARTDATE_COLUMN,	null);
		this.AddColValue(STARTNUMBER_COLUMN,	null);
		this.AddColValue(EXAMTERMINALID_COLUMN, null);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	// 2011.08.16 Add DD.Chinh@MERX End
	*/

	/*
	/// <summary>
	/// 優先フラグを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="yuusenFlg">優先フラグ</param>
	/// <returns>判定</returns>
	public boolean UpdateRisYuusenFlg(Connection con, Transaction trans, String risID, String yuusenFlg)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateRisYuusenFlg(risID, yuusenFlg);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 感染症チェックフラグを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="infectionFlg">感染症フラグ</param>
	/// <returns>判定</returns>
	public boolean UpdateRisInfectionFlg(Connection con, Transaction trans, String risID, String infectionFlg)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateRisInfectionFlg(risID, infectionFlg);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 同意書チェックフラグを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="douishoFlg">同意書チェックフラグ</param>
	/// <returns>判定</returns>
	public boolean UpdateRisDouishoFlg(Connection con, Transaction trans, String risID, String douishoFlg)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateRisDouishoFlg(risID, douishoFlg);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	/// <summary>
	/// 薬剤発注チェックフラグを更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="pharmaFlg">薬剤発注チェックフラグ</param>
	/// <returns>判定</returns>
	public boolean UpdateRisPharmaFlg(Connection con, Transaction trans, String risID, String pharmaFlg)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateRisPharmaFlg(risID, pharmaFlg);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	// 2010.11.16 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// 優先フラグ　Update文作成
	/// </summary>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisYuusenFlg(String risID, String yuusenFlg)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		//
		this.AddColValue(YUUSEN_FLG_COLUMN, yuusenFlg);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// 感染症チェックフラグ　Update文作成
	/// </summary>
	/// <param name="infectionFlg">感染症フラグ</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisInfectionFlg(String risID, String infectionFlg)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		//
		if (infectionFlg == CommonString.INFECTION_CHECK_ON)
		{
			this.AddColValue(INFECTION_CHECK_FLAG_COLUMN, infectionFlg);
			this.AddColValue(INFECTION_CHECK_NAME_COLUMN, Configuration.GetInstance().GetUserAccountBean().GetUserName());
		}
		else
		{
			this.AddColValue(INFECTION_CHECK_FLAG_COLUMN, infectionFlg);
			this.AddColSetValue(INFECTION_CHECK_NAME_COLUMN, "NULL");
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	/// 同意書チェックフラグ　Update文作成
	/// </summary>
	/// <param name="douishoFlg">同意書チェックフラグ</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisDouishoFlg(String risID, String douishoFlg)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		//
		if (douishoFlg == CommonString.DOUISHO_CHECK_ON)
		{
			this.AddColValue(DOUISHO_CHECK_FLAG_COLUMN, douishoFlg);
			this.AddColValue(DOUISHO_CHECK_NAME_COLUMN, Configuration.GetInstance().GetUserAccountBean().GetUserName());
		}
		else
		{
			this.AddColValue(DOUISHO_CHECK_FLAG_COLUMN, douishoFlg);
			this.AddColSetValue(DOUISHO_CHECK_NAME_COLUMN, "NULL");
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	/// <summary>
	/// 薬剤発注チェックフラグ　Update文作成
	/// </summary>
	/// <param name="pharmaFlg">薬剤発注チェックフラグ</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisPharmaFlg(String risID, String pharmaFlg)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);
		//
		if (pharmaFlg == CommonString.PHARMA_CHECK_ON)
		{
			this.AddColValue(PHARMA_CHECK_FLAG_COLUMN, pharmaFlg);
			this.AddColValue(PHARMA_CHECK_NAME_COLUMN, Configuration.GetInstance().GetUserAccountBean().GetUserName());
		}
		else
		{
			this.AddColValue(PHARMA_CHECK_FLAG_COLUMN, pharmaFlg);
			this.AddColSetValue(PHARMA_CHECK_NAME_COLUMN, "NULL");
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	// 2010.11.16 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// 確保処理を行う
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="terminalID">端末ID</param>
	/// <returns>判定</returns>
	public boolean UpdateRisKakuho(Connection con, Transaction trans, String risID, String terminalID)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateRisKakuho(risID, terminalID);
			retBool = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="terminalID">端末ID</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisKakuho(String risID, String terminalID)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true, SignType.Equal);

		if (terminalID.length() > 0)
		{
			this.AddColValue(EXAMTERMINALID_COLUMN, Integer.parseInt(terminalID));
			// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
			//関東労災による特注処理対応
			if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
			{
				this.AddColValue(DRESSING_ROOM_ID_COLUMN, Integer.parseInt(terminalID));
			}
			// 2010.11.16 Add T.Nishikubo End
		}
		else
		{
			// 2010.11.16 Mod T.Nishikubo Start KANRO-R-3
			//関東労災による特注処理対応
			if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
			{
				this.AddColSetValue(DRESSING_ROOM_ID_COLUMN, "NULL");
			}
			else
			{
				this.AddColSetValue(EXAMTERMINALID_COLUMN, "NULL");
			}
			//this.AddColSetValue(EXAMTERMINALID_COLUMN, "NULL");
			// 2010.11.16 Mod T.Nishikubo End
		}

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="exBean">実績情報</param>
	private void SetColValue(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		this.keys.clear();

		// カラム値設定(※追加時は、GetExecutionInfoBean も同様に修正する事)

		this.AddColValue(RIS_ID_COLUMN, exBean.GetRisID(), true);
		//
		this.AddColValue(KENSATYPE_ID_COLUMN,			exBean.GetKensaTypeID());
		this.AddColValue(KENSA_DATE_COLUMN,				exBean.GetKensaDate());
		this.AddColValue(KENSASITU_ID_COLUMN,			exBean.GetKensaSituID());
		this.AddColValue(KENSAKIKI_ID_COLUMN,			exBean.GetKensaKikiID());
		this.AddColValue(KANJA_ID_COLUMN,				exBean.GetKanjaID());
		if (exBean.GetKensaDateAge().length() > 0)
		{
			this.AddColValue(KENSA_DATE_AGE_COLUMN,		Integer.parseInt(exBean.GetKensaDateAge()));
		}
		this.AddColValue(DENPYO_NYUGAIKBN_COLUMN,		exBean.GetDenpyoNyugaiKbn());
		this.AddColValue(UKETUKE_TANTOU_ID_COLUMN,		exBean.GetUketukeTantouID());
		this.AddColValue(UKETUKE_TANTOU_NAME_COLUMN,	exBean.GetUketukeTantouName());
		this.AddColValue(RECEIPTDATE_COLUMN,			exBean.GetReceiptDateTime());
		if (exBean.GetReceiptTerminalID().length() > 0)
		{
			this.AddColValue(RECEIPTTERMINALID_COLUMN,	Integer.parseInt(exBean.GetReceiptTerminalID()));
		}
		if (exBean.GetReceiptNumber().length() > 0)
		{
			this.AddColValue(RECEIPTNUMBER_COLUMN,		Integer.parseInt(exBean.GetReceiptNumber()));
		}
		this.AddColValue(KENSA_GISI_ID_COLUMN,			exBean.GetKensaGisiID());
		this.AddColValue(KENSA_GISI_NAME_COLUMN,		exBean.GetKensaGisiName());
		this.AddColValue(EXAMSTARTDATE_COLUMN,			exBean.GetExamStartDateTime());
		this.AddColValue(EXAMENDDATE_COLUMN,			exBean.GetExamEndDateTime());
		if (exBean.GetExamTerminalID().length() > 0)
		{
			this.AddColValue(EXAMTERMINALID_COLUMN,		Integer.parseInt(exBean.GetExamTerminalID()));
		}
		this.AddColValue(STARTNUMBER_COLUMN,			exBean.GetStartNumber());
		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//this.AddColValue(KANGOSI_ID_COLUMN, exBean.GetKangosiID());
		//this.AddColValue(KANGOSI_NAME_COLUMN,			exBean.GetKangosiName());

		// 2011.07.26 Del NSK_T.Koudate End
		this.AddColValue(KENSAI_ID_COLUMN,				exBean.GetKensaDoctorID());
		this.AddColValue(KENSAI_NAME_COLUMN,			exBean.GetKensaDoctorName());
		this.AddColValue(BIKOU_COLUMN,					exBean.GetBikou());
		this.AddColValue(RENRAKU_MEMO_COLUMN,			exBean.GetRenrakuMemo());
		this.AddColValue(SIJI_ISI_ID_COLUMN,			exBean.GetSijiIsiID());
		this.AddColValue(SIJI_ISI_NAME_COLUMN,			exBean.GetSijiIsiName());
		this.AddColValue(SIJI_ISI_COMMENT_COLUMN,		exBean.GetSijiIsiComment());
		this.AddColValue(TOUSITIME_COLUMN,				exBean.GetTousiTime());
		this.AddColValue(BAKUSYASUU_COLUMN,				exBean.GetBakushaSuu());
		this.AddColValue(GYOUMU_KBN_COLUMN,				exBean.GetGyoumuKbn());
		if (exBean.GetStatus().length() > 0)
		{
			this.AddColValue(STATUS_COLUMN,				Integer.parseInt(exBean.GetStatus()));
		}
		this.AddColValue(RECEIPTFLAG_COLUMN,			exBean.GetReceiptFlg());
		this.AddColValue(YUUSEN_FLG_COLUMN,				exBean.GetYuusenFlg());
		this.AddColValue(EXAMSAVEFLAG_COLUMN,			exBean.GetExamSaveFlg());
		this.AddColValue(ENFORCEDOC_ID_COLUMN,			exBean.GetEnforceDocID());
		this.AddColValue(ENFORCEDOC_NAME_COLUMN,		exBean.GetEnforceDocName());
		this.AddColValue(ENDCOUNT_COLUMN,				exBean.GetEndCount());
		// 2010.07.30 Add T.Ootsuka Start
		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//this.AddColValue(KENSA_GISI_ID2_COLUMN, exBean.GetKensaGisiID2());
		//this.AddColValue(KENSA_GISI_NAME2_COLUMN,		exBean.GetKensaGisiName2());
		//this.AddColValue(KENSA_GISI_ID3_COLUMN,			exBean.GetKensaGisiID3());
		//this.AddColValue(KENSA_GISI_NAME3_COLUMN,		exBean.GetKensaGisiName3());
		//this.AddColValue(KENSA_GISI_ID4_COLUMN,			exBean.GetKensaGisiID4());
		//this.AddColValue(KENSA_GISI_NAME4_COLUMN,		exBean.GetKensaGisiName4());
		//this.AddColValue(KENSA_GISI_ID5_COLUMN,			exBean.GetKensaGisiID5());
		//this.AddColValue(KENSA_GISI_NAME5_COLUMN,		exBean.GetKensaGisiName5());

		// 2011.07.26 Del NSK_T.Koudate End
		this.AddColValue(ENFORCEDOC_ID2_COLUMN,			exBean.GetEnforceDocID2());
		this.AddColValue(ENFORCEDOC_NAME2_COLUMN,		exBean.GetEnforceDocName2());
		this.AddColValue(ENFORCEDOC_ID3_COLUMN,			exBean.GetEnforceDocID3());
		this.AddColValue(ENFORCEDOC_NAME3_COLUMN,		exBean.GetEnforceDocName3());
		this.AddColValue(ENFORCEDOC_ID4_COLUMN,			exBean.GetEnforceDocID4());
		this.AddColValue(ENFORCEDOC_NAME4_COLUMN,		exBean.GetEnforceDocName4());
		this.AddColValue(ENFORCEDOC_ID5_COLUMN,			exBean.GetEnforceDocID5());
		this.AddColValue(ENFORCEDOC_NAME5_COLUMN,		exBean.GetEnforceDocName5());

		this.AddColValue(ENFORCEDOC_SECTION1_COLUMN,	exBean.GetEnforceDocSectionID1());
		this.AddColValue(ENFORCEDOC_SECTION2_COLUMN,	exBean.GetEnforceDocSectionID2());
		this.AddColValue(ENFORCEDOC_SECTION3_COLUMN,	exBean.GetEnforceDocSectionID3());
		this.AddColValue(ENFORCEDOC_SECTION4_COLUMN,	exBean.GetEnforceDocSectionID4());
		this.AddColValue(ENFORCEDOC_SECTION5_COLUMN,	exBean.GetEnforceDocSectionID5());

		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//this.AddColValue(KANGOSI_ID2_COLUMN, exBean.GetKangosiID2());
		//this.AddColValue(KANGOSI_NAME2_COLUMN,			exBean.GetKangosiName2());
		//this.AddColValue(KANGOSI_ID3_COLUMN,			exBean.GetKangosiID3());
		//this.AddColValue(KANGOSI_NAME3_COLUMN,			exBean.GetKangosiName3());

		// 2011.07.26 Del NSK_T.Koudate End
		this.AddColValue(JISISYA_ID_COLUMN,				exBean.GetJisisyaID());
		this.AddColValue(JISISYA_NAME_COLUMN,			exBean.GetJisisyaName());
		// 2010.07.30 Add T.Ootsuka End

		/* 一ノ瀬保留
		// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
		this.AddColValue(UNKNOWNDATE_FLG_COLUMN, exBean.GetUnknownDateFlg());
		// 2011.07.11 Add NSK_H.Hashimoto End


		// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
		// 担当者
		this.AddColValue(MED_PERSON_ID01_COLUMN, exBean.GetMedPersonID01());
		this.AddColValue(MED_PERSON_NAME01_COLUMN, exBean.GetMedPersonName01());
		this.AddColValue(MED_PERSON_SYOKUINKBN01_COLUMN, exBean.GetMedPersonSyokuinKbn01());
		this.AddColValue(MED_PERSON_ID02_COLUMN, exBean.GetMedPersonID02());
		this.AddColValue(MED_PERSON_NAME02_COLUMN, exBean.GetMedPersonName02());
		this.AddColValue(MED_PERSON_SYOKUINKBN02_COLUMN, exBean.GetMedPersonSyokuinKbn02());
		this.AddColValue(MED_PERSON_ID03_COLUMN, exBean.GetMedPersonID03());
		this.AddColValue(MED_PERSON_NAME03_COLUMN, exBean.GetMedPersonName03());
		this.AddColValue(MED_PERSON_SYOKUINKBN03_COLUMN, exBean.GetMedPersonSyokuinKbn03());
		this.AddColValue(MED_PERSON_ID04_COLUMN, exBean.GetMedPersonID04());
		this.AddColValue(MED_PERSON_NAME04_COLUMN, exBean.GetMedPersonName04());
		this.AddColValue(MED_PERSON_SYOKUINKBN04_COLUMN, exBean.GetMedPersonSyokuinKbn04());
		this.AddColValue(MED_PERSON_ID05_COLUMN, exBean.GetMedPersonID05());
		this.AddColValue(MED_PERSON_NAME05_COLUMN, exBean.GetMedPersonName05());
		this.AddColValue(MED_PERSON_SYOKUINKBN05_COLUMN, exBean.GetMedPersonSyokuinKbn05());
		this.AddColValue(MED_PERSON_ID06_COLUMN, exBean.GetMedPersonID06());
		this.AddColValue(MED_PERSON_NAME06_COLUMN, exBean.GetMedPersonName06());
		this.AddColValue(MED_PERSON_SYOKUINKBN06_COLUMN, exBean.GetMedPersonSyokuinKbn06());
		this.AddColValue(MED_PERSON_ID07_COLUMN, exBean.GetMedPersonID07());
		this.AddColValue(MED_PERSON_NAME07_COLUMN, exBean.GetMedPersonName07());
		this.AddColValue(MED_PERSON_SYOKUINKBN07_COLUMN, exBean.GetMedPersonSyokuinKbn07());
		this.AddColValue(MED_PERSON_ID08_COLUMN, exBean.GetMedPersonID08());
		this.AddColValue(MED_PERSON_NAME08_COLUMN, exBean.GetMedPersonName08());
		this.AddColValue(MED_PERSON_SYOKUINKBN08_COLUMN, exBean.GetMedPersonSyokuinKbn08());
		this.AddColValue(MED_PERSON_ID09_COLUMN, exBean.GetMedPersonID09());
		this.AddColValue(MED_PERSON_NAME09_COLUMN, exBean.GetMedPersonName09());
		this.AddColValue(MED_PERSON_SYOKUINKBN09_COLUMN, exBean.GetMedPersonSyokuinKbn09());
		this.AddColValue(MED_PERSON_ID10_COLUMN, exBean.GetMedPersonID10());
		this.AddColValue(MED_PERSON_NAME10_COLUMN, exBean.GetMedPersonName10());
		this.AddColValue(MED_PERSON_SYOKUINKBN10_COLUMN, exBean.GetMedPersonSyokuinKbn10());

		// 2011.07.26 Add NSK_T.Koudate End
		*/
	}

	/*
	/// <summary>
	/// オーダ情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象オーダ情報のRisID</param>
	/// <returns>判定</returns>
	public boolean DeleteExMainData(Connection con, Transaction trans, String risID)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null)
		{
			//強制DELETE
			String sqlStr = CreateDeleteSQL(risID);
			retFlg = ForceDelete(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">RIS_ID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

	/*
	/// <summary>
	/// 実績メイン情報を登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="executionInfoBean">実績メイン情報</param>
	/// <returns></returns>
	public boolean InsertNewExMainData(Connection con, Transaction trans, ExecutionInfoBean executionInfoBean)
	{
		// parameters
		boolean retBool = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && executionInfoBean != null)
		{
			// EXMAINTABLE 更新
			String sqlStr = CreateNewInsertSQL(executionInfoBean);
			retBool = Insert(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// Insert文作成
	/// </summary>
	/// <param name="bean">実績メイン情報</param>
	/// <returns>判定</returns>
	private String CreateNewInsertSQL(ExecutionInfoBean bean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN,		bean.GetRisID());
		//
		this.AddColValue(KANJA_ID_COLUMN,	bean.GetKanjaID());
		this.AddColValue(STATUS_COLUMN,		bean.GetStatus());


		logger.debug("end");

		return this.GetInsertSQL();
	}
	*/

	/*
	/// <summary>
	/// 実績情報指示情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="executionInfoBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean UpdateRisSijiInfo(Connection con, Transaction trans, ExecutionInfoBean executionInfoBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && executionInfoBean != null)
		{
			// ExMainTable 更新
			String sqlStr = CreateUpdateSijiInfoSQL(executionInfoBean);
			retFlg = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
    /// <summary>
	/// RIS実績情報の日時情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="executionInfoBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean UpdateRisExamTimestamp(Connection con, Transaction trans, ExecutionInfoBean executionInfoBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && executionInfoBean != null)
		{
			// ExMainTable 更新
            String sqlStr = CreateUpdateRisExamTimestampSQL(executionInfoBean);
			retFlg = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	/// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="executionInfoBean">Update用実績情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateSijiInfoSQL(ExecutionInfoBean executionInfoBean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN,				executionInfoBean.GetRisID(), true, SignType.Equal);
		//
		this.AddColValue(SIJI_ISI_ID_COLUMN,		executionInfoBean.GetSijiIsiID());
		this.AddColValue(SIJI_ISI_NAME_COLUMN,		executionInfoBean.GetSijiIsiName());
		this.AddColValue(SIJI_ISI_COMMENT_COLUMN,	executionInfoBean.GetSijiIsiComment());

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
    /// <summary>
	/// Update文作成
	/// </summary>
	/// <param name="executionInfoBean">Update用実績情報</param>
	/// <returns>判定</returns>
	private String CreateUpdateRisExamTimestampSQL(ExecutionInfoBean executionInfoBean)
	{
		logger.debug("begin");

		keys.clear();
		inList.clear();

		this.AddColValue(RIS_ID_COLUMN,				executionInfoBean.GetRisID(), true, SignType.Equal);
		//
        if (executionInfoBean.GetExamStartTimestamp() == Timestamp.MinValue)
        {
			this.AddColSetValue(EXAMSTARTDATE_COLUMN, "NULL");
        }
        else
        {
            this.AddColValue(EXAMSTARTDATE_COLUMN, executionInfoBean.GetExamStartTimestamp());
        }

		if (executionInfoBean.GetExamEndTimestamp() == Timestamp.MinValue)
        {
			this.AddColSetValue(EXAMENDDATE_COLUMN, "NULL");
        }
        else
        {
            this.AddColValue(EXAMENDDATE_COLUMN, executionInfoBean.GetExamEndTimestamp());
		}

		// 2010.07.30 Add T.Ootsuka Start
		// 業務区分
		if (executionInfoBean.GetGyoumuKbn() == String.Empty)
		{
			this.AddColSetValue(GYOUMU_KBN_COLUMN, "NULL");
		}
		else
		{
			this.AddColSetValue(GYOUMU_KBN_COLUMN, executionInfoBean.GetGyoumuKbn());
		}
		// 2010.07.30 Add T.Ootsuka End

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	/// <summary>
	///	帳票用-実績情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintExMainDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
	//	sqlStrBuild.append(" SELECT /*+ INDEX(EM) INDEX(OM) INDEX(EO) */ EM.* FROM ");
	//	// 元の処理
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ EM.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT EM.* FROM ");			//
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " EM");
	//	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
	//	sqlStrBuild.append(" WHERE ");
	//	// 2011.06.24 Mod K.Shinohara Start A0060
	//	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID ");
	//	//sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
	//	//sqlStrBuild.append(" OM.RIS_ID = EM.RIS_ID ");
	//	// 2011.06.24 Mod K.Shinohara End
	//	CommonUtil.AddPrintWhereString(sqlStrBuild, param);

	//	logger.debug("end");

	//	return sqlStrBuild.toString();
	//}

	/*
	//2010.10.29 Add K.Shinohara Start
	/// <summary>
	///	他検査用-実績情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetOtherExMainDataTable(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		DataTable retDt = new DataTable();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sql = CreateOtherSelectSQL(param);

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

	/*
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateOtherSelectSQL(OrderSearchParameter param)
	{
		logger.debug("begin");

		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		sqlStrBuild.append(" SELECT EM.* FROM ");
		sqlStrBuild.append(" EXMAINTABLE EM ");
		sqlStrBuild.append(" WHERE ");
		if (param.GetKanjaIDOnlyBool())
		{
			sqlStrBuild.append(" EM.KANJA_ID = '" + param.GetKanjaID() + "'");
		}
		else
		{
			sqlStrBuild.append("     EM.KENSA_DATE >= "	+ param.GetExecutePeriodStartDate().toString("yyyyMMdd"));
			sqlStrBuild.append(" AND EM.KENSA_DATE <= " + param.GetExecutePeriodEndDate().toString("yyyyMMdd"));
			if (param.GetKanjaID().length() > 0)
			{
				sqlStrBuild.append(" AND EM.KANJA_ID = '" + param.GetKanjaID() + "'");
			}
		}

		logger.debug("end");

		return sqlStrBuild.toString();
	}
	//2010.10.29 Add K.Shinohara End
	*/

	/*
	//2011.11.21 Add NSK_M.Ochiai Start extends OMH-1-7
	private String UpdateRisRevisionInfoSql(ExecutionInfoBean exBean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, exBean.GetRisID(), true, SignType.Equal);
		//
		this.AddColSetValue(EXAMSTARTDATE_COLUMN, "NULL");
		this.AddColSetValue(EXAMTERMINALID_COLUMN, "NULL");
		this.AddColValue(STARTNUMBER_COLUMN, exBean.GetStartNumber() + 1);
		this.AddColValue(STATUS_COLUMN, CommonString.STATUS_ISREGISTERED);
		this.AddColValue(EXAMSAVEFLAG_COLUMN, CommonString.EXMAIN_EXAMSAVE_OFF);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	public boolean UpdateRisRevisionInfo(Connection con, Transaction trans, ExecutionInfoBean executionInfoBean)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && executionInfoBean != null)
		{
			// ExMainTable 更新
			String sqlStr = UpdateRisRevisionInfoSql(executionInfoBean);
			retFlg = Update(con, trans, sqlStr);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	//2011.11.21 Add NSK_M.Ochiai End
	*/
}
