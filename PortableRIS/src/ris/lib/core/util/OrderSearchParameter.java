package ris.lib.core.util;

import java.sql.Timestamp;
import java.util.ArrayList;

import ris.portable.common.Const;
import ris.portable.util.DataTable;

/// <summary>
///
/// オーダ検索条件クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
/// V2.04.001 	: 2011.01.24	: 999999 / DD.Chinh     extends KANRO-R-19
///
/// </summary>
public class OrderSearchParameter
{
	// private members
	private String risIDStr						= "";					//Ris.RisID
	private String kanjaIDStr					= "";					//Ris.PatientInfo.Kanja_ID
	private String kanjiSimeiStr				= "";					//Ris.PatientInfo.KanjiSimei
	private String kanaSimeiStr					= "";					//Ris.PatientInfo.KanaSimei
	private String nyugaiKbnStr					= "";					//Ris.PatientInfo.Kanja_NyugaiKbn
	private String detailOrderTypeStr			= "";					//Ris.OrderMainTable.KensaType_ID
	private String disDetailOrderTypeStr		= "";					//Ris.OrderMainTable.KensaType_ID
	private String iraiSectionIDStr				= "";					//Ris.OrderMainTable.Irai_Section_ID
	private String iraiDoctorNoStr				= "";					//Ris.OrderMainTable.Irai_Doctor_No
	private Timestamp executePeriodStartDT		= Const.TIMESTAMP_MINVALUE;	//Ris.OrderMainTable.ExamEndDate
	private Timestamp executePeriodEndDT		= Const.TIMESTAMP_MINVALUE;	//Ris.OrderMainTable.ExamEndDate
	private boolean maxRecordLimitBool			= true;					//最大表示件数を適用するか否かのフラグ
	private Timestamp receiptStartDT			= Const.TIMESTAMP_MINVALUE;	//Ris.ExMainTable.ReceiptDate
	private Timestamp receiptEndDT				= Const.TIMESTAMP_MINVALUE;	//Ris.ExMainTable.ReceiptDate
	private String sijiIsiIDStr					= "";					//Ris.ExMainTable.Siji_Isi_ID
	private String gisiIDStr					= "";					//Ris.
	private boolean orderByExamEndDateDescBool	= false;				//ExamEndDateのDescでソートするかどうか
	private String risIDListStr					= "";					//Ris.OrderMainTable.RIS_ID（INを用いて検索）
	private String rrisStatusStr				= "";					//RRisのステータス
	private String rrisKensaTypeIDStr			= "";					//RIS.KensaTypeID（INを用いて検索）
	private String rrisExamRoomIDStr			= "";					//RIS.ExamRoom_ID（INを用いて検索）
	private String rrisKensaKikiIDStr			= "";					//Ris.KENSAKIKI_ID（INを用いて検索）

	private boolean rrisEmptyRoom					= false;				//RRIS true:検査室未割り当てを含む
	private boolean rrisEmptyKiki					= false;				//RRIS true":検査機器未割り当てを含む
	private boolean unKnownDateBool				= false;				//日未定予約
	private boolean orderSortBool					= false;				//オーダ番号でソートする
	private boolean showBuiModeBool				= false;				//部位展開フラグ
	private int  terminalIDInt					= -1;					//端末ID
	private String windowAppIDStr				= "";					//画面ID
	private String searchTypeStr				= "";					//検索パターンタイプ
	private DataTable showItemDefineDt			= null;					//表示項目定義情報
	private String kensaDateStr					= "";					//検査日
	private boolean takensaSearchBool				= false;				//他検査検索フラグ
	private boolean patCmtSearchBool				= false;				//患者コメント検索フラグ
	private boolean shotCntSearchBool				= false;				//撮影数検索フラグ
	private boolean requestSearchBool				= false;				//依頼日検索フラグ
	private boolean kanjaIDOnlyBool				= false;				//患者IDのみ検索フラグ
	private boolean dateOrderKensaBool				= false;				//オーダ日=検査日フラグ
	private boolean saveCountBool					= false;				//件数制限フラグ 2010.10.12 Add H.Orikasa
	private String riOrderFlgStr				= "";					//Ris.ExtendOrderInfo.RI_Order_Flg
	private String portableFlgStr				= "";					//Ris.ExtendOrderInfo.Portable_Flg
	private String kenzouStatusFlgStr			= "";					//Ris.ExtendExamInfo.Kenzou_Status_Flg
	private String kenzouTantouFlgStr			= "";					//検像担当者=実施者フラグ
	private boolean orderDateBool					= false;				//オーダ発効日検索フラグ
	private boolean historyFlgBool					= false;				//履歴フラグ
	// 2010.06.23 Add T.Nishikubo Start
	private String gyoumKbnStr					= "";					// 業務区分
	// 2010.06.23 Add T.Nishikubo End
	private Timestamp orderKensaDateStartDT		= Const.TIMESTAMP_MINVALUE;	//Ris.OrderMainTable.Kensa_Date
	private Timestamp orderKensaDateEndDT		= Const.TIMESTAMP_MINVALUE;	//Ris.OrderMainTable.Kensa_Date
	private Timestamp exKensaDateStartDT			= Const.TIMESTAMP_MINVALUE;	//Ris.ExMainTable.Kensa_Date
	private Timestamp exKensaDateEndDT			= Const.TIMESTAMP_MINVALUE;	//Ris.ExMainTable.Kensa_Date
	// 2010.09.02 Add K.Shinohara Start
	private Timestamp removeKensaDate			= Const.TIMESTAMP_MINVALUE;	//患者履歴画面用 除外する検査日
	// 2010.09.02 Add K.Shinohara End
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	private String preparingStr					= "";				//更衣中
	// 2010.11.16 Add T.Nishikubo End
	private String preStsStr					= "";					//ﾌﾟﾚﾁｪｯｸ進捗検索条件	// 2011.01.24 Add DD.Chinh KANRO-R-19
	// 2010.12.27 Add K.Shinohara Start
	private boolean autoPrintSearchBool			= false;				//帳票検索
	// 2010.12.27 Add K.Shinohara End

	// 2011.06.24 Add K.Shinohara Start A0060
	private boolean isRptUnKnownDateBool			= false;				//日未定フラグ
	// 2011.06.24 Add K.Shinohara End

	private String printIDStr					= "";					//帳票ID		// 2011.08.19 Add H.Orikasa A0060(修正)
	private ArrayList risIDList					= new ArrayList();		//RISIDリスト	//
	private boolean otherFlgBool					= false;				//他検査フラグ	//

	private String execSqlStr					= "";					//実行SQL

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public OrderSearchParameter()
	{
		//
	}
//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[OrderSearchParameter]");
		strBuild.append("RIS_ID="						+ risIDStr							+ ";");
		strBuild.append("KANJA_ID="						+ kanjaIDStr						+ ";");
		strBuild.append("KanjiSimei="					+ kanjiSimeiStr						+ ";");
		strBuild.append("KanaSimei="					+ kanaSimeiStr						+ ";");
		strBuild.append("Kanja_NyugaiKbn="				+ nyugaiKbnStr						+ ";");
		strBuild.append("KensaType_ID="					+ detailOrderTypeStr				+ ";");
		strBuild.append("DisKensaType_ID="				+ disDetailOrderTypeStr				+ ";");
		strBuild.append("Irai_Section_ID="				+ iraiSectionIDStr					+ ";");
		strBuild.append("Irai_Doctor_No="				+ iraiDoctorNoStr					+ ";");
		strBuild.append("ExamEndDate="					+ executePeriodStartDT.toString()	+ "-" + executePeriodEndDT.toString()  + ";");
		strBuild.append("MAX_RECORD_LIMIT="				+ maxRecordLimitBool				+ ";");
		strBuild.append("ORDERBY_EXAMENDDATE_DESC="		+ orderByExamEndDateDescBool		+ ";");
		strBuild.append("RIS_ID IN ("					+ risIDListStr						+ ");");

		strBuild.append("rrisKENSATYPE_ID="				+ rrisKensaTypeIDStr				+ ";"); //RIS検査種別
		strBuild.append("rrisEXAMROOM_ID="				+ rrisExamRoomIDStr					+ ";"); //RIS検査室
		strBuild.append("rrisKENSAKIKI_ID="				+ rrisKensaKikiIDStr				+ ";"); //RIS検査機器
		strBuild.append("rrisSTATUS="					+ rrisStatusStr						+ ";"); //RISステータス

		strBuild.append("rrisEmptyRoom="				+ rrisEmptyRoom						+ ";"); //RRIS検査室未割り当て
		strBuild.append("rrisEmptyKiki="				+ rrisEmptyKiki						+ ";"); //RRIS検査機器未割り当て
        strBuild.append("showBuiMode="                  + showBuiModeBool					+ ";"); //部位展開モード
		strBuild.append("terminalID="					+ terminalIDInt						+ ";"); //端末ID
		strBuild.append("windowAppID="					+ windowAppIDStr					+ ";"); //画面ID
		strBuild.append("searchType="					+ searchTypeStr						+ ";"); //検索パターンタイプ
		strBuild.append("kensaDate="					+ kensaDateStr						+ ";"); //検査日
		strBuild.append("orderDateMode="				+ orderDateBool						+ ";"); //依頼日モード
		strBuild.append("historyFlg="					+ historyFlgBool					+ ";"); //履歴フラグ
		strBuild.append("preStsStr="				+ preStsStr							+ ";"); //ﾌﾟﾚﾁｪｯｸ進捗

		return strBuild.toString();
	}
//
	/// <summary>
	/// 検索条件のRIS_IDを取得する
	/// </summary>
	/// <returns>RIS_ID</returns>
	public String GetRisID()
	{
		return this.risIDStr;
	}

	/// <summary>
	/// 検索条件のRIS_IDを設定する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	public void SetRisID( String risID )
	{
		if( risID != null )
		{
			this.risIDStr = risID;
		}
	}
//
	/// <summary>
	/// 検索条件の患者IDを取得する
	/// </summary>
	/// <returns>患者ID</returns>
	public String GetKanjaID()
	{
		return this.kanjaIDStr;
	}

	/// <summary>
	/// 検索条件の患者IDを設定する
	/// </summary>
	/// <param name="kanjaID">患者ID（「*」をサポート）</param>
	public void SetKanjaID( String kanjaID )
	{
		if( kanjaID != null )
		{
			this.kanjaIDStr = kanjaID;
		}
	}
//
	/// <summary>
	/// 検索条件の患者漢字氏名を取得する
	/// </summary>
	/// <returns>患者漢字氏名</returns>
	public String GetKanjaKanjiSimei()
	{
		return this.kanjiSimeiStr;
	}

	/// <summary>
	/// 検索条件の患者漢字氏名を設定する
	/// </summary>
	/// <param name="kanjiSimei">患者漢字氏名（「*」をサポート）</param>
	public void SetKanjaKanjiSimei( String kanjiSimei )
	{
		if( kanjiSimei != null )
		{
			this.kanjiSimeiStr = kanjiSimei;
		}
	}
//
	/// <summary>
	/// 検索条件の患者カナ氏名を取得する
	/// </summary>
	/// <returns>患者カナ氏名</returns>
	public String GetKanjaKanaSimei()
	{
		return this.kanaSimeiStr;
	}
	/// <summary>
	/// 検索条件の患者カナ氏名を設定する
	/// </summary>
	/// <param name="kanaSimei">患者カナ氏名（「*」をサポート）</param>
	public void SetKanjaKanaSimei( String kanaSimei )
	{
		if( kanaSimei != null )
		{
			this.kanaSimeiStr = kanaSimei;
		}
	}
//
	/// <summary>
	/// 検索条件の患者入外区分を取得する
	/// </summary>
	/// <returns>患者入外区分</returns>
	public String GetNyugaiKbn()
	{
		return this.nyugaiKbnStr;
	}
	/// <summary>
	/// 検索条件の患者入外区分を設定する
	/// </summary>
	/// <param name="nyugaiKbn">患者入外区分（複数指定の場合はカンマ（,）区切り）</param>
	public void SetNyugaiKbn( String nyugaiKbn )
	{
		if( nyugaiKbn != null )
		{
			this.nyugaiKbnStr = nyugaiKbn;
		}
	}
//
	/// <summary>
	/// 検索条件の詳細オーダ種別を取得する
	/// </summary>
	/// <returns>詳細オーダ種別</returns>
	public String GetDetailOrderType()
	{
		return this.detailOrderTypeStr;
	}

	/// <summary>
	/// 検索条件の詳細オーダ種別を設定する
	/// </summary>
	/// <param name="detailOrderType">詳細オーダ種別（複数指定の場合はカンマ（,）区切り）</param>
	public void SetDetailOrderType( String detailOrderType )
	{
		if( detailOrderType != null )
		{
			this.detailOrderTypeStr = detailOrderType;
		}
	}
//
	/// <summary>
	/// 検索条件の詳細オーダ種別(対象外)を取得する
	/// </summary>
	/// <returns>詳細オーダ種別</returns>
	public String GetDisDetailOrderType()
	{
		return this.disDetailOrderTypeStr;
	}

	/// <summary>
	/// 検索条件の詳細オーダ種別(対象外)を設定する
	/// </summary>
	/// <param name="disDetailOrderType">詳細オーダ種別（複数指定の場合はカンマ（,）区切り）</param>
	public void SetDisDetailOrderType( String disDetailOrderType )
	{
		if( disDetailOrderType != null )
		{
			this.disDetailOrderTypeStr = disDetailOrderType;
		}
	}

	/// <summary>
	/// 検索条件の詳細オーダ種別(対象外)を追加する
	/// </summary>
	/// <param name="disDetailOrderType">詳細オーダ種別（複数指定の場合はカンマ（,）区切り）</param>
	public void AddDisDetailOrderType( String disDetailOrderType )
	{
		if( disDetailOrderType != null &&
			disDetailOrderType.length() > 0)
		{
			if (this.disDetailOrderTypeStr.length() > 0)
			{
				this.disDetailOrderTypeStr = this.disDetailOrderTypeStr + ",";
			}
			this.disDetailOrderTypeStr = this.disDetailOrderTypeStr + disDetailOrderType;
		}
	}

	/// <summary>
	/// 検索条件の依頼科IDを取得する
	/// </summary>
	/// <returns>依頼科ID</returns>
	public String GetIraiSectionID()
	{
		return this.iraiSectionIDStr;
	}

	/// <summary>
	/// 検索条件の依頼科IDを設定する
	/// </summary>
	/// <param name="iraiSectionID">依頼科ID（複数指定の場合はカンマ（,）区切り）</param>
	public void SetIraiSectionID( String iraiSectionID )
	{
		if( iraiSectionID != null )
		{
			this.iraiSectionIDStr = iraiSectionID;
		}
	}
//
	/// <summary>
	/// 検索条件の依頼医師IDを取得する
	/// </summary>
	/// <returns>依頼医師ID</returns>
	public String GetIraiDoctorNo()
	{
		return this.iraiDoctorNoStr;
	}

	/// <summary>
	/// 検索条件の依頼医師IDを設定する
	/// </summary>
	/// <param name="iraiDoctorNo">依頼医師ID（複数指定の場合はカンマ（,）区切り）</param>
	public void SetIraiDoctorNo( String iraiDoctorNo )
	{
		if( iraiDoctorNo != null )
		{
			this.iraiDoctorNoStr = iraiDoctorNo;
		}
	}
//
	/// <summary>
	/// 検索条件の実施日（期間検索の開始日）を設定する
	/// </summary>
	/// <param name="startDate">実施日（期間検索の開始日）</param>
	public void SetExecutePeriodStartDate( Timestamp startDate )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(startDate) )
		{
			this.executePeriodStartDT = startDate;
		}
	}

	/// <summary>
	/// 検索条件の実施日（期間検索の開始日）を取得する
	/// </summary>
	/// <returns>実施日（期間検索の開始日）</returns>
	public Timestamp GetExecutePeriodStartDate()
	{
		return this.executePeriodStartDT;
	}
//
	/// <summary>
	/// 検索条件の実施日（期間検索の終了日）を設定する
	/// </summary>
	/// <param name="endDate">実施日（期間検索の終了日）</param>
	public void SetExecutePeriodEndDate( Timestamp endDate )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(endDate) )
		{
			this.executePeriodEndDT = endDate;
		}
	}

	/// <summary>
	/// 検索条件の実施日（期間検索の終了日）を取得する
	/// </summary>
	/// <returns>実施日（期間検索の終了日）</returns>
	public Timestamp GetExecutePeriodEndDate()
	{
		return this.executePeriodEndDT;
	}
//
	/// <summary>
	/// 実施日をクリアする
	/// </summary>
	public void ClearExecutePeriodDate()
	{
		this.executePeriodStartDT = Const.TIMESTAMP_MINVALUE;
		this.executePeriodEndDT = Const.TIMESTAMP_MINVALUE;
	}
//
	// maxRecordLimitBoolのSET
	public void SetMaxRecordLimitOn()
	{
		this.maxRecordLimitBool = true;
	}

	// maxRecordLimitBoolのSET
	public void SetMaxRecordLimitOff()
	{
		this.maxRecordLimitBool = false;
	}

	// maxRecordLimitBoolのGET
	public boolean HasMaxRecordLimit()
	{
		return this.maxRecordLimitBool;
	}
//
	/// <summary>
	/// 検索条件の受付日（開始日）を取得する
	/// </summary>
	/// <returns>受付日（開始日）</returns>
	public Timestamp GetReceiptStartDate()
	{
		return this.receiptStartDT;
	}

	/// <summary>
	/// 検索条件の受付日（開始日）を設定する
	/// </summary>
	/// <param name="startDate">受付日（開始日）</param>
	public void SetReceiptStartDate( Timestamp startDate )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(startDate) )
		{
			this.receiptStartDT = startDate;
		}
	}
//
	/// <summary>
	/// 検索条件の受付日（終了日）を取得する
	/// </summary>
	/// <returns>受付日（期間検索の終了日）</returns>
	public Timestamp GetReceiptEndDate()
	{
		return this.receiptEndDT;
	}
	/// <summary>
	/// 検索条件の受付日（終了日）を設定する
	/// </summary>
	/// <param name="endDate">受付日（終了日）</param>
	public void SetReceiptEndDate( Timestamp endDate )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(endDate) )
		{
			this.receiptEndDT = endDate;
		}
	}
//
	/// <summary>
	/// 検索条件のSIJI_ISI_IDを取得する
	/// </summary>
	/// <returns>SIJI_ISI_ID</returns>
	public String GetSijiIsiID()
	{
		return this.sijiIsiIDStr;
	}

	/// <summary>
	/// 検索条件のSIJI_ISI_IDを設定する
	/// </summary>
	/// <param name="sijiIsiID">SIJI_ISI_ID</param>
	public void SetSijiIsiID(String sijiIsiID)
	{
		if (sijiIsiID != null)
		{
			this.sijiIsiIDStr = sijiIsiID;
		}
	}
//
	/// <summary>
	/// 検索条件のgisiIDを取得する
	/// </summary>
	/// <returns>gisiID</returns>
	public String GetGisiID()
	{
		return this.gisiIDStr;
	}

	/// <summary>
	/// 検索条件のgisiIDを設定する
	/// </summary>
	/// <param name="gisiID">gisiID</param>
	public void SetGisiID(String gisiID)
	{
		if (gisiID != null)
		{
			this.gisiIDStr = gisiID;
		}
	}
//
	/// <summary>
	/// ExamEndDateのDescでソートするかどうかを設定する
	/// </summary>
	/// <param name="flg"></param>
	public void SetOrderByExamEndDateDesc( boolean flg )
	{
		this.orderByExamEndDateDescBool = flg;
	}
	/// <summary>
	/// ExamEndDateのDescでソートするかどうかを取得する
	/// </summary>
	/// <returns></returns>
	public boolean GetOrderByExamEndDateDesc()
	{
		return this.orderByExamEndDateDescBool;
	}
//
	/// <summary>
	/// RIS_IDリストを設定する
	/// </summary>
	/// <param name="flg"></param>
	public void SetRisIDList( String risID )
	{
		this.risIDListStr = risID;
	}

	/// <summary>
	/// RIS_IDリストを取得する
	/// </summary>
	/// <returns></returns>
	public String GetRisIDList()
	{
		return this.risIDListStr;
	}
//
	/// <summary>
	/// 検索条件のRisの検査機器IDを設定する
	/// </summary>
	/// <returns></returns>
	public String GetRrisKensaKikiID()
	{
		return this.rrisKensaKikiIDStr;
	}

	/// <summary>
	/// 検索条件のRisの検査機器IDを設定する
	/// </summary>
	/// <param name="kensaKikiID"></param>
	public void SetRrisKensaKikiID( String kensaKikiID )
	{
		if( kensaKikiID != null )
		{
			this.rrisKensaKikiIDStr = kensaKikiID;
		}
	}
//
	/// <summary>
	/// 検索条件のRisの検査室IDを取得する
	/// </summary>
	public String GetRrisExamRoomID()
	{
		return this.rrisExamRoomIDStr;
	}

	/// <summary>
	/// 検索条件のRisの検査室IDを設定する
	/// </summary>
	/// <param name="kensaRoomID"></param>
	/// <returns></returns>
	public void SetRrisExamRoomID(String kensaRoomID)
	{
		if( kensaRoomID != null)
		{
			this.rrisExamRoomIDStr = kensaRoomID;
		}
	}
//
	/// <summary>
	/// 検索条件の検査室未割り当てを取得する
	/// </summary>
	/// <returns>検査室未割り当て条件</returns>
	public boolean GetRrisEmptyRoom()
	{
		return this.rrisEmptyRoom;
	}

	/// <summary>
	/// 検索条件の検査室未割り当てを設定する
	/// </summary>
	/// <param name="nyugaiKbn">検査室未割り当て（未割り当てを含む場合はtrue）</param>
	public void SetRrisEmptyRoom( boolean rrisEmptyRoom )
	{
		this.rrisEmptyRoom = rrisEmptyRoom;
	}
//
	/// <summary>
	/// 検索条件の検査機器未割り当てを取得する
	/// </summary>
	/// <returns>検査機器未割り当て条件</returns>
	public boolean GetRrisEmptyKiki()
	{
		return this.rrisEmptyKiki;
	}

	/// <summary>
	/// 検索条件の検査機器未割り当てを設定する
	/// </summary>
	/// <param name="nyugaiKbn">検査機器未割り当て（未割り当てを含む場合はtrue）</param>
	public void SetRrisEmptyKiki( boolean rrisEmptyKiki )
	{
		this.rrisEmptyKiki = rrisEmptyKiki;
	}
//
	/// <summary>
	/// 検索条件のRRisの検査種別IDを取得する
	/// </summary>
	public String GetRrisKensaTypeID()
	{
		return this.rrisKensaTypeIDStr;
	}

	/// <summary>
	/// 検索条件のRRisの検査種別IDを設定する
	/// </summary>
	/// <param name="kensaRoomID"></param>
	/// <returns></returns>
	public void SetRrisKensaTypeID(String kensaTypeID)
	{
		if( kensaTypeID != null)
		{
			this.rrisKensaTypeIDStr = kensaTypeID;
		}
	}
//
	/// <summary>
	/// 検索条件のRRisのステータスを取得する
	/// </summary>
	/// <returns>ステータス</returns>
	public String GetRrisStatus()
	{
		return this.rrisStatusStr;
	}

	/// <summary>
	/// 検索条件のRRisのステータスを設定する
	/// </summary>
	/// <param name="status">ステータス（複数指定の場合はカンマ（,）区切り）</param>
	public void SetRrisStatus( String Status )
	{
		if( Status != null )
		{
			this.rrisStatusStr = Status;
		}
	}
//
	/// <summary>
	/// 検索条件のオーダ番号ソートフラグを取得する
	/// </summary>
    /// <returns>オーダ番号ソートフラグ</returns>
	public boolean GetOrderSortFlg()
	{
		return this.orderSortBool;
	}

	/// <summary>
	/// 検索条件のオーダ番号ソートフラグを設定する
	/// </summary>
    /// <param name="orderSortBool">オーダ番号ソートフラグ</param>
	public void SetOrderSortFlg(boolean orderSortBool)
	{
		this.orderSortBool = orderSortBool;
	}

    /// <summary>
    /// 部位展開モードを設定する
    /// </summary>
    /// <param name="showBuiMode">部位展開モード</param>
    public void SetShowBuiMode(boolean showBuiMode)
    {
        this.showBuiModeBool = showBuiMode;
    }

    /// <summary>
    /// 部位展開モードを取得する
    /// </summary>
    /// <returns>部位展開モード</returns>
    public boolean GetShowBuiMode()
    {
        return this.showBuiModeBool;
    }
//
	/// <summary>
	/// 検索条件の端末IDを取得する
	/// </summary>
	/// <returns>端末ID</returns>
	public int GetTerminalID()
	{
		return this.terminalIDInt;
	}

	/// <summary>
	/// 検索条件の端末IDを設定する
	/// </summary>
	/// <param name="terminalID">端末ID</param>
	public void SetTerminalID(int terminalID)
	{
		this.terminalIDInt = terminalID;
	}
//
	/// <summary>
	/// 検索条件の画面IDを取得する
	/// </summary>
	/// <returns>画面ID</returns>
	public String GetWindowAppID()
	{
		return this.windowAppIDStr;
	}

	/// <summary>
	/// 検索条件の画面IDを設定する
	/// </summary>
	/// <param name="windowAppID">画面ID</param>
	public void SetWindowAppID(String windowAppID)
	{
		if (windowAppID != null)
		{
			this.windowAppIDStr = windowAppID;
		}
	}
//
	/// <summary>
	/// 検索条件の検索パターンタイプを取得する
	/// </summary>
	/// <returns>検索パターンタイプ</returns>
	public String GetSearchType()
	{
		return this.searchTypeStr;
	}

	/// <summary>
	/// 検索条件の検索パターンタイプを設定する
	/// </summary>
	/// <param name="searchType">検索パターンタイプ</param>
	public void SetSearchType(String searchType)
	{
		if (searchType != null)
		{
			this.searchTypeStr = searchType;
		}
	}
//
	/// <summary>
	/// 検索条件の表示項目定義情報を取得する
	/// </summary>
	/// <returns>表示項目定義情報</returns>
	public DataTable GetShowItemDefineDt()
	{
		return this.showItemDefineDt;
	}

	/// <summary>
	/// 検索条件の表示項目定義情報を設定する
	/// </summary>
	/// <param name="showItemDefine">表示項目定義情報</param>
	public void SetShowItemDefineDt(DataTable showItemDefine)
	{
		this.showItemDefineDt = showItemDefine;
	}
//
	/// <summary>
	/// 検索条件の検査日を取得する
	/// </summary>
	/// <returns>検査日</returns>
	public String GetKensaDate()
	{
		return this.kensaDateStr;
	}

	/// <summary>
	/// 検索条件の検査日を設定する
	/// </summary>
	/// <param name="kensaDate">検査日</param>
	public void SetKensaDate(String kensaDate)
	{
		this.kensaDateStr = kensaDate;
	}
//
	/// <summary>
	/// 検索条件の他検査検索有無フラグを取得する
	/// </summary>
    /// <returns>他検査検索有無フラグ</returns>
    public boolean GetTakensaSearch()
	{
		return this.takensaSearchBool;
	}

	/// <summary>
	/// 検索条件の他検査検索有無フラグを設定する
	/// </summary>
    /// <param name="takensaSearch">他検査検索有無フラグ</param>
	public void SetTakensaSearch(boolean takensaSearch)
	{
		this.takensaSearchBool = takensaSearch;
	}
//
	/// <summary>
	/// 検索条件の患者コメント検索有無フラグを取得する
	/// </summary>
    /// <returns>患者コメント検索有無フラグ</returns>
    public boolean GetPatCmtSearchBool()
	{
		return this.patCmtSearchBool;
	}

	/// <summary>
	/// 検索条件の患者コメント検索有無フラグを設定する
	/// </summary>
    /// <param name="patCmtSearch">患者コメント検索有無フラグ</param>
	public void SetPatCmtSearchBool(boolean patCmtSearch)
	{
		this.patCmtSearchBool = patCmtSearch;
	}
//
	/// <summary>
	/// 検索条件の撮影数検索有無フラグを取得する
	/// </summary>
    /// <returns>撮影数検索有無フラグ</returns>
    public boolean GetShotCntSearchBool()
	{
		return this.shotCntSearchBool;
	}

	/// <summary>
	/// 検索条件の撮影数検索有無フラグを設定する
	/// </summary>
	/// <param name="shotCntSearch">撮影数検索有無フラグ</param>
	public void SetShotCntSearchBool(boolean shotCntSearch)
	{
		this.shotCntSearchBool = shotCntSearch;
	}
//
	/// <summary>
	/// 検索条件の依頼日検索有無フラグを取得する
	/// </summary>
    /// <returns>依頼日検索有無フラグ</returns>
    public boolean GetRequestSearchBool()
	{
		return this.requestSearchBool;
	}

	/// <summary>
	/// 検索条件の依頼日検索有無フラグを設定する
	/// </summary>
    /// <param name="requestSearch">依頼日検索有無フラグ</param>
	public void SetRequestSearchBool(boolean requestSearch)
	{
		this.requestSearchBool = requestSearch;
	}
//
	/// <summary>
	/// 検索条件の患者IDのみ検索フラグを取得する
	/// </summary>
    /// <returns>患者IDのみ検索フラグ</returns>
    public boolean GetKanjaIDOnlyBool()
	{
		return this.kanjaIDOnlyBool;
	}

	/// <summary>
	/// 検索条件の患者IDのみ検索フラグを設定する
	/// </summary>
    /// <param name="kanjaIDOnly">患者IDのみ検索フラグ</param>
	public void SetKanjaIDOnlyBool(boolean kanjaIDOnly)
	{
		this.kanjaIDOnlyBool = kanjaIDOnly;
	}
//
	/// <summary>
	/// 検索条件のオーダ日=検査日フラグを取得する
	/// </summary>
    /// <returns>検査日フラグ</returns>
    public boolean GetDateOrderKensa()
	{
		return this.dateOrderKensaBool;
	}

	/// <summary>
	/// 検索条件のオーダ日=検査日フラグを設定する
	/// </summary>
    /// <param name="dateOrderKensa">検査日フラグ</param>
	public void SetDateOrderKensa(boolean dateOrderKensa)
	{
		this.dateOrderKensaBool = dateOrderKensa;
	}
//
	//2010.10.12 Add H.Orikasa Start
	/// <summary>
	/// 検索条件の件数制限フラグを取得する
	/// </summary>
	/// <returns>件数制限フラグ</returns>
	public boolean GetSaveCount()
	{
		return this.saveCountBool;
	}

	/// <summary>
	/// 検索条件の件数制限フラグを設定する
	/// </summary>
	/// <param name="saveCount">件数制限フラグ</param>
	public void SetSaveCount(boolean saveCount)
	{
		this.saveCountBool = saveCount;
	}
	//2010.10.12 Add H.Orikasa End
//
	/// <summary>
	/// 検索条件のRIオーダフラグを取得する
	/// </summary>
	/// <returns>RIオーダフラグ</returns>
	public String GetRIOrderFlg()
	{
		return this.riOrderFlgStr;
	}

	/// <summary>
	/// 検索条件のRIオーダフラグを設定する
	/// </summary>
	/// <param name="riOrderFlg">RIオーダフラグ</param>
	public void SetRIOrderFlg(String riOrderFlg)
	{
		this.riOrderFlgStr = riOrderFlg;
	}
//
	/// <summary>
	/// 検索条件のポータブルフラグを取得する
	/// </summary>
	/// <returns>ポータブルフラグ</returns>
	public String GetPortableFlg()
	{
		return this.portableFlgStr;
	}

	/// <summary>
	/// 検索条件のポータブルフラグを設定する
	/// </summary>
	/// <param name="portableFlg">ポータブルフラグ</param>
	public void SetPortableFlg(String portableFlg)
	{
		this.portableFlgStr = portableFlg;
	}
//
	/// <summary>
	/// 検索条件の検像進捗フラグを取得する
	/// </summary>
	/// <returns>検像進捗フラグ</returns>
	public String GetKenzouStatusFlg()
	{
		return this.kenzouStatusFlgStr;
	}

	/// <summary>
	/// 検索条件の検像進捗フラグを設定する
	/// </summary>
	/// <param name="kenzouStatusFlg">検像進捗フラグ</param>
	public void SetKenzouStatusFlg(String kenzouStatusFlg)
	{
		this.kenzouStatusFlgStr = kenzouStatusFlg;
	}
//
	/// <summary>
	/// 検索条件の検像担当フラグを取得する
	/// </summary>
	/// <returns>検像担当フラグ</returns>
	public String GetKenzouTantouFlg()
	{
		return this.kenzouTantouFlgStr;
	}

	/// <summary>
	/// 検索条件の検像担当フラグを設定する
	/// </summary>
	/// <param name="kenzouTantouFlg">検像担当フラグ</param>
	public void SetKenzouTantouFlg(String kenzouTantouFlg)
	{
		this.kenzouTantouFlgStr = kenzouTantouFlg;
	}
//
	/// <summary>
	/// 検索条件の依頼日フラグを設定する
	/// </summary>
	/// <param name="orderDate">依頼日フラグ</param>
	/// <returns></returns>
	public void SetOrderDateBool(boolean orderDate)
	{
		this.orderDateBool = orderDate;
	}

	/// <summary>
	/// 検索条件の依頼日フラグを取得する
	/// </summary>
	/// <returns>依頼日フラグ</returns>
	public boolean GetOrderDateBool()
	{
		return orderDateBool;
	}
//
    /// <summary>
    /// 検索条件の日未定フラグを取得する
    /// </summary>
    /// <returns>日未定フラグ</returns>
    public boolean GetUnKnownDateBool()
    {
        return this.unKnownDateBool;
    }

    /// <summary>
    /// 検索条件の日未定フラグを設定する
    /// </summary>
    /// <param name="unKnownSearch">日未定フラグ</param>
    /// <returns></returns>
    public void SetUnKnownDateBool(boolean unKnownDate)
    {
        this.unKnownDateBool = unKnownDate;
    }
//

	/// <summary>
	/// 検索条件の実行SQLを取得する
	/// </summary>
	/// <returns>実行SQL</returns>
	public String GetExecSql()
	{
		String retStr = "";
		if (this.execSqlStr.indexOf("from") !=-1)
		{
			retStr = this.execSqlStr.substring(this.execSqlStr.indexOf("from"));
		}
		else if (this.execSqlStr.indexOf("FROM") !=-1)
		{
			retStr = this.execSqlStr.substring(this.execSqlStr.indexOf("FROM"));
		}
		retStr = "SELECT * " + retStr;
		return retStr;
	}

	/// <summary>
	/// 検索条件の実行SQLを設定する
	/// </summary>
	/// <param name="execSql">実行SQL</param>
	public void SetExecSql(String execSql)
	{
		this.execSqlStr = execSql;
	}
//
	/// <summary>
	/// 検索条件の履歴フラグを設定する
	/// </summary>
	/// <param name="historyFlg">履歴フラグ</param>
	/// <returns></returns>
	public void SetHistoryFlg(boolean historyFlg)
	{
		this.historyFlgBool = historyFlg;
	}

	/// <summary>
	/// 検索条件の履歴フラグを取得する
	/// </summary>
	/// <returns>履歴フラグ</returns>
	public boolean GetHistoryFlg()
	{
		return historyFlgBool;
	}
//
	// 2010.06.23 Add T.Nishikubo Start
	/// <summary>
	/// 検索条件の業務区分を取得する
	/// </summary>
	/// <returns>業務区分</returns>
	public String GetGyoumKbnStr()
	{
		return this.gyoumKbnStr;
	}

	/// <summary>
	/// 検索条件の業務区分を設定する
	/// </summary>
	/// <param name="gyoumKbnStr">業務区分</param>
	public void SetGyoumKbnStr(String gyoumKbnStr)
	{
		this.gyoumKbnStr = gyoumKbnStr;
	}
	// 2010.06.23 Add T.Nishikubo End
//
	/// <summary>
	/// 検索条件のオーダ検査日（開始日）を取得する
	/// </summary>
	/// <returns>オーダ検査日（開始日）</returns>
	public Timestamp GetOrderKensaStartDate()
	{
		return this.orderKensaDateStartDT;
	}

	/// <summary>
	/// 検索条件のオーダ検査日（開始日）を設定する
	/// </summary>
	/// <param name="orderKensaDateStart">オーダ検査日（開始日）</param>
	public void SetOrderKensaStartDate(Timestamp orderKensaDateStart)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(orderKensaDateStart) )
		{
			this.orderKensaDateStartDT = orderKensaDateStart;
		}
	}

	/// <summary>
	/// 検索条件のオーダ検査日（終了日）を取得する
	/// </summary>
	/// <returns>オーダ検査日（終了日）</returns>
	public Timestamp GetOrderKensaEndDate()
	{
		return this.orderKensaDateEndDT;
	}

	/// <summary>
	/// 検索条件のオーダ検査日（終了日）を設定する
	/// </summary>
	/// <param name="orderKensaDateEnd">オーダ検査日（終了日）</param>
	public void SetOrderKensaEndDate(Timestamp orderKensaDateEnd)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(orderKensaDateEnd) )
		{
			this.orderKensaDateEndDT = orderKensaDateEnd;
		}
	}

	// 2010.09.02 Add K.Shinohara Start
	/// <summary>
	/// 検索条件の除外する検査日を設定する
	/// </summary>
	/// <param name="todayDataRemoveFlg">除外する検査日</param>
	/// <returns></returns>
	public void SetRemoveKensaDate(Timestamp removeKensaDate)
	{
		this.removeKensaDate = removeKensaDate;
	}

	/// <summary>
	/// 検索条件の除外する検査日を取得する
	/// </summary>
	/// <returns>除外する検査日</returns>
	public Timestamp GetRemoveKensaDate()
	{
		return this.removeKensaDate;
	}
	// 2010.09.02 Add K.Shinohara End

	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	/// <summary>
	/// 検索条件-更衣中を設定する
	/// </summary>
	public void SetPreparing(String preparingStr)
	{
		if (preparingStr != null)
		{
			this.preparingStr = preparingStr;
		}
	}

	/// <summary>
	/// 検索条件-更衣中を取得する
	/// </summary>
	public String GetPreparing()
	{
		return this.preparingStr;
	}
	// 2010.11.16 Add T.Nishikubo End

	// 2010.12.27 Add K.Shinohara Start
	//
	/// <summary>
	/// 帳票自動印刷用検索条件かを判別するフラグを設定する
	/// </summary>
	/// <param name="printSearch">帳票自動印刷用検索フラグ</param>
	/// <returns></returns>
	public void SetAutoPrintSearchBool(boolean autoPrintSearch)
	{
		this.autoPrintSearchBool = autoPrintSearch;
	}

	/// <summary>
	/// 帳票自動印刷用検索条件かを判別するフラグを取得する
	/// </summary>
	/// <returns>帳票自動印刷用検索フラグ</returns>
	public boolean GetAutoPrintSearchBool()
	{
		return this.autoPrintSearchBool;
	}
	// 2010.12.27 Add K.Shinohara End

	// 2011.01.24 Add DD.Chinh Start KANRO-R-19
	/// <summary>
	/// 検索条件のﾌﾟﾚﾁｪｯｸ進捗を取得する
	/// </summary>
	/// <returns>ﾌﾟﾚﾁｪｯｸ進捗</returns>
	public String GetPreStsStr()
	{
		return this.preStsStr;
	}

	/// <summary>
	/// 検索条件のﾌﾟﾚﾁｪｯｸ進捗を設定する
	/// </summary>
	/// <param name="preSts">ﾌﾟﾚﾁｪｯｸ進捗</param>
	public void SetPreStsStr(String preSts)
	{
		if (preSts != null)
		{
			this.preStsStr = preSts;
		}
	}
	// 2011.01.24 Add DD.Chinh End

	// 2011.06.24 Add K.Shinohara Start A0060
	/// <summary>
	/// 日未定フラグ(帳票)を設定する
	/// </summary>
	/// <param name="isRptUnKnownDate">日未定フラグ(帳票)</param>
	public void SetRptUnKnownDateFlg(boolean isRptUnKnownDate)
	{
		this.isRptUnKnownDateBool = isRptUnKnownDate;
	}
	/// <summary>
	/// 日未定フラグ(帳票)を取得する
	/// </summary>
	/// <returns>日未定フラグ(帳票)</returns>
	public boolean GetRptUnKnownDateFlg()
	{
		return this.isRptUnKnownDateBool;
	}
	// 2011.06.24 Add K.Shinohara End

	// 2011.08.19 Add H.Orikasa Start A0060(修正)
	/// <summary>
	/// 帳票IDを設定する
	/// </summary>
	/// <param name="printID">帳票ID</param>
	public void SetPrintID(String printID)
	{
		if (printID != null)
		{
			this.printIDStr = printID;
		}
	}

	/// <summary>
	/// 帳票IDを取得する
	/// </summary>
	public String GetPrintID()
	{
		return this.printIDStr;
	}

	/// <summary>
	/// RISIDリストを設定する
	/// </summary>
	/// <param name="list">RISIDリスト</param>
	public void SetRisIDArrayList(ArrayList list)
	{
		this.risIDList = list;
	}

	/// <summary>
	/// RISIDリストを取得する
	/// </summary>
	public ArrayList GetRisIDArrayList()
	{
		return this.risIDList;
	}

	/// <summary>
	/// 他検査フラグを設定する
	/// </summary>
	/// <param name="otherFlg">他検査フラグ</param>
	public void SetOtherFlg(boolean otherFlg)
	{
		this.otherFlgBool = otherFlg;
	}

	/// <summary>
	/// 他検査フラグを取得する
	/// </summary>
	public boolean GetOtherFlg()
	{
		return this.otherFlgBool;
	}
	// 2011.08.19 Add H.Orikasa End
}
