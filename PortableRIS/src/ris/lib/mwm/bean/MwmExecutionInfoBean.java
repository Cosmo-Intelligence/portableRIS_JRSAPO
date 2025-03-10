package ris.lib.mwm.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import ris.lib.mwm.util.MwmMasterUtil;
import ris.portable.common.Const;

/// <summary>
///
/// 実績情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2008.08.24	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MwmExecutionInfoBean
{
	// priavate members
	private String risIDStr = ""; //Ris.ExMainTable.RIS_ID, EXMAIN_EXTEND_TABLE.RIS_ID
	private String kensaTypeIDStr = ""; //Ris.ExMainTable.KENSATYPE_ID
	private String kensaDateStr = ""; //Ris.ExMainTable.KENSA_DATE
	private String kensaSituIDStr = ""; //Ris.ExMainTable.KENSASITU_ID
	private String kensaKikiIDStr = ""; //Ris.ExMainTable.KENSAKIKI_ID
	private String kanjaIDStr = ""; //Ris.ExMainTable.KANJA_ID
	private String kensaDateAgeStr = "999"; //Ris.ExMainTable.KENSA_DATE_AGE
	private String denpyoNyugaiKbnStr = ""; //Ris.ExMainTable.DENPYO_NYUGAIKBN
	//--> DB的には常にNULLをSETするので、「SetDenpyoNyugaiKbn()」は用意しない
	private String uketukeTantouIDStr = ""; //Ris.ExMainTable.UKETUKE_TANTOU_ID
	private String uketukeTantouNameStr = ""; //Ris.ExMainTable.UKETUKE_TANTOU_NAME
	private Timestamp receiptTimestamp = Const.TIMESTAMP_MINVALUE; //Ris.ExMainTable.RECEIPTDATE
	private String receiptTerminalIDStr = ""; //Ris.ExMainTable.RECEIPTTERMINALID
	private String kensaGisiIDStr = ""; //Ris.ExMainTable.KENSA_GISI_ID
	private String kensaGisiNameStr = ""; //Ris.ExMainTable.KENSA_GISI_NAME
	private Timestamp examStartTimestamp = Const.TIMESTAMP_MINVALUE; //Ris.ExMainTable.EXAMSTARTDATE
	private Timestamp examEndTimestamp = Const.TIMESTAMP_MINVALUE; //Ris.ExMainTable.EXAMENDDATE
	private String examTerminalIDStr = ""; //Ris.ExMainTable.EXAMTERMINALID
	private int startNumberInt = 1; //Ris.ExMainTable.STARTNUMBER
	//--> DB的には常に「1」をSETするので、「SetStartNumber()」は用意しない
	private String kangosiIDStr = ""; //Ris.ExMainTable.KANGOSI_ID
	private String kangosiNameStr = ""; //Ris.ExMainTable.KANGOSI_NAME
	private String kensaDoctorIDStr = ""; //Ris.ExMainTable.KENSAI_ID
	private String kensaDoctorNameStr = ""; //Ris.ExMainTable.KENSAI_NAME
	private String bikouStr = ""; //Ris.ExMainTable.BIKOU
	private String renrakuMemoStr = ""; //Ris.ExMainTable.RENRAKU_MEMO
	private String sijiIsiIDStr = ""; //Ris.ExMainTable.SIJI_ISI_ID
	private String sijiIsiNameStr = ""; //Ris.ExMainTable.SIJI_ISI_NAME
	private String sijiIsiCommentStr = ""; //Ris.ExMainTable.SIJI_ISI_COMMENT
	private String tousiTimeStr = ""; //Ris.ExMainTable.TOUSITIME
	private String bakushaSuuStr = ""; //Ris.ExMainTable.BAKUSYASUU
	private String gyoumuKbnStr = "0"; //Ris.ExMainTable.GYOUMU_KBN
	//--> DB的には常に「0」をSETするので、「SetGyoumuKbn()」は用意しない
	private String statusStr = "0"; //Ris.ExMainTable.STATUS
	private String receiptFlgStr = "OF"; //Ris.ExMainTable.RECEIPTFLAG
	private String yuusenFlgStr = "0"; //Ris.ExMainTable.YUUSEN_FLG
	//--> DB的には常に「0」をSETするので、「SetYuusenFlg()」は用意しない
	private String examSaveFlgStr = "0"; //Ris.ExMainTable.EXAMSAVEFLAG
	private String enforceDocIDStr = ""; //Ris.ExMainTable.ENFORCEDOC_ID
	private String enforceDocNameStr = ""; //Ris.ExMainTable.ENFORCEDOC_NAME
	private int endCountInt = 0; //Ris.ExMainTable.ENDCOUNT
	//--> DB的には常に「0」をSETするので、「SetEndCount()」は用意しない
	private String orderClassificationStr = ""; //EXMAIN_EXTEND_TABLE.ORDER_CLASSIFICATION
	//--> 0:治療依頼オーダ　1:撮影系オーダ　2:照射オーダ
	//--> 0or1or2以外が入ってきたら""をSETする
	private String commentsStr = ""; //EXMAIN_EXTEND_TABLE.COMMENTS
	private String projectUIDStr = ""; //EXMAIN_EXTEND_TABLE.PROJECT_UID
	private String projectRevStr = ""; //EXMAIN_EXTEND_TABLE.PROJECT_REV
	private String planUIDStr = ""; //EXMAIN_EXTEND_TABLE.PLAN_UID
	private String planRevStr = ""; //EXMAIN_EXTEND_TABLE.PLAN_REV
	private String courseIDStr = ""; //EXMAIN_EXTEND_TABLE.COURSE_ID
	private String refPointIDStr = ""; //EXMAIN_EXTEND_TABLE.REFPOINT_ID
	private String treatKindIDStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_KIND_ID
	private String treatKindNameStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_KIND_NAME
	private String treatDetailKindIDStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_DETAIL_KIND_ID
	private String treatDetailKindNameStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_DETAIL_KIND_NAME
	private String treatItemIDStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_ITEM_ID
	private String treatItemNameStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_ITEM_NAME
	private String treatMachineIDStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_MACHINE_ID
	private String treatRoomIDStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_ROOM_ID
	private String qualityIDStr = ""; //EXMAIN_EXTEND_TABLE.QUALITY_ID
	private String qualityNameStr = ""; //EXMAIN_EXTEND_TABLE.QUALITY_NAME
	private String sourceIDStr = ""; //EXMAIN_EXTEND_TABLE.SOURCE_ID
	private String sourceNameStr = ""; //EXMAIN_EXTEND_TABLE.SOURCE_NAME
	private String resultParam1Str = ""; //EXMAIN_EXTEND_TABLE.RESULT_PARAM1
	private String resultParam2Str = ""; //EXMAIN_EXTEND_TABLE.RESULT_PARAM2
	private String doseStr = "0"; //EXMAIN_EXTEND_TABLE.DOSE
	private String treatTimeStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_TIME
	private int psInt = 0; //EXMAIN_EXTEND_TABLE.PS
	private String treatDr1IDStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_DR1_ID
	private String treatDr1NameStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_DR1_NAME
	private String treatDr2IDStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_DR2_ID
	private String treatDr2NameStr = ""; //EXMAIN_EXTEND_TABLE.TREAT_DR2_NAME
	private int confStatusInt = 0; //EXMAIN_EXTEND_TABLE.CONF_STATUS
	//--> 0:確認不要　1:要確認　2:確認済
	//--> 0or1or2以外が入ってきたら「0」をSETする
	private String confReqUsrIDStr = ""; //EXMAIN_EXTEND_TABLE.CONF_REQ_USR_ID
	private String confReqUsrKanjiNameStr = ""; //EXMAIN_EXTEND_TABLE.CONF_REQ_USR_KANJINAME
	private Timestamp confReqTimestamp = Const.TIMESTAMP_MINVALUE; //EXMAIN_EXTEND_TABLE.CONF_REQ_DATE
	private String confUsrIDStr = ""; //EXMAIN_EXTEND_TABLE.CONF_USR_ID
	private String confUsrKanjiNameStr = ""; //EXMAIN_EXTEND_TABLE.CONF_USR_KANJINAME
	private Timestamp confTimestamp = Const.TIMESTAMP_MINVALUE; //EXMAIN_EXTEND_TABLE.CONF_DATE
	private String aeTitleStr = ""; //EXMAIN_EXTEND_TABLE.AE_TITLE
	private String studyInstanceUIDStr = ""; //EXMAIN_EXTEND_TABLE.STUDY_INSTANCE_UID
	private int delFlgInt = 0; //EXMAIN_EXTEND_TABLE.DEL_FLG
	//--> 0:使用中　1:削除済
	//--> 0or1以外が入ってきたら「0」をSETする
	private String enfEng1IDStr = ""; //EXMAIN_EXTEND_TABLE.ENF_ENG1_ID
	private String enfEng1KanjiNameStr = ""; //EXMAIN_EXTEND_TABLE.ENF_ENG1_KANJINAME
	private String enfEng2IDStr = ""; //EXMAIN_EXTEND_TABLE.ENF_ENG2_ID
	private String enfEng2KanjiNameStr = ""; //EXMAIN_EXTEND_TABLE.ENF_ENG2_KANJINAME
	private String enfEng3IDStr = ""; //EXMAIN_EXTEND_TABLE.ENF_ENG3_ID
	private String enfEng3KanjiNameStr = ""; //EXMAIN_EXTEND_TABLE.ENF_ENG3_KANJINAME
	//--> 実施可能か否かを示すフラグ（0:実施不可能　1:実施可能）で、照射情報の確定・確定解除と連動
	private Timestamp entryTimestamp = Const.TIMESTAMP_MINVALUE; //EXMAIN_EXTEND_TABLE.ENTRY_DATE
	private String entryUsrIDStr = ""; //EXMAIN_EXTEND_TABLE.ENTRY_USR_ID
	private String entryUsrNameStr = ""; //EXMAIN_EXTEND_TABLE.ENTRY_USR_NAME
	private Timestamp updTimestamp = Const.TIMESTAMP_MINVALUE; //EXMAIN_EXTEND_TABLE.UPD_DATE
	private String updUsrIDStr = ""; //EXMAIN_EXTEND_TABLE.UPD_USR_ID
	private String updUsrNameStr = ""; //EXMAIN_EXTEND_TABLE.UPD_USR_NAME
	private String lgPreRisIDStr = "";//EXMAIN_EXTEND_TABLE.LG_PRE_RIS_ID
	private String lgApproveCommentStr = "";//EXMAIN_EXTEND_TABLE.LG_APPROVE_COMMENT

	private String enterTimeStr = "";//EXMAIN_EXTEND_TABLE.ENTER_TIME
	private String exitTimeStr  = "";//EXMAIN_EXTEND_TABLE.EXIT_TIME
	private String stayTimeStr  = "";//EXMAIN_EXTEND_TABLE.STAY_TIME

	// ステータス管理用に追加
	private String statusUpdRsnStr = ""; //EXMAIN_EXTEND_TABLE.STATUS_UPD_RSN
	private String statusUpdUserIDStr = ""; //EXMAIN_EXTEND_TABLE.STATUS_UPD_USR_ID
	private String statusUpdUserNameStr = ""; //EXMAIN_EXTEND_TABLE.STATUS_UPD_USR_NAME
	private Timestamp statusUpdDateDT = Const.TIMESTAMP_MINVALUE; //EXMAIN_EXTEND_TABLE.STATUS_UPD_DATE
	// ステータス管理用に追加

	private ArrayList arrListOrderBui = new ArrayList(); //OrderBuiBeanのリスト
	//--> ExBuiTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト

	private ArrayList arrListZoueizai = new ArrayList(); //ExZoueizaiBeanのリスト
	//--> ExZoueizaiTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト

	private ArrayList arrListFilm = new ArrayList(); //ExFilmBeanのリスト
	//--> ExFilmTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト

	private ArrayList arrListSatuei = new ArrayList(); //ExSatueiBeanのリスト
	//--> ExSatueiTable.RIS_ID=ExMainTable.RIS_IDとなるレコート（複数）のリスト

	private MwmPatientInfoBean resultPatientBean = null; //実績患者情報（実施済みの場合にのみ、Core側でSET）

	private ArrayList arrListPhotoProgress = new ArrayList(); //PhotoProgressInfoBeanのリスト(撮影系のみ)

	// 照射または検査終了を示す 実績患者情報の更新で使用
	private boolean resultBool = false;

	//
	public MwmExecutionInfoBean()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
	}
	//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExecutionInfoBean]");
		strBuild.append("RIS_ID=" + risIDStr + ";");
		strBuild.append("KENSATYPE_ID=" + kensaTypeIDStr + ";");
		strBuild.append("KENSASITU_ID=" + kensaSituIDStr + ";");
		strBuild.append("KENSAKIKI_ID=" + kensaKikiIDStr + ";");
		strBuild.append("KANJA_ID=" + kanjaIDStr + ";");
		strBuild.append("UKETUKE_TANTOU_ID=" + uketukeTantouIDStr + ";");
		strBuild.append("UKETUKE_TANTOU_NAME=" + uketukeTantouNameStr + ";");
		strBuild.append("RECEIPTDATE=" + receiptTimestamp.toString() + ";");
		strBuild.append("RECEIPTTERMINALID=" + receiptTerminalIDStr + ";");
		strBuild.append("KENSA_GISI_ID=" + kensaGisiIDStr + ";");
		strBuild.append("KENSA_GISI_NAME=" + kensaGisiNameStr + ";");
		strBuild.append("EXAMSTARTDATE=" + examStartTimestamp.toString() + ";");
		strBuild.append("EXAMENDDATE=" + examEndTimestamp.toString() + ";");
		strBuild.append("EXAMTERMINALID=" + examTerminalIDStr + ";");
		strBuild.append("KANGOSI_ID=" + kangosiIDStr + ";");
		strBuild.append("KANGOSI_NAME=" + kangosiNameStr + ";");
		strBuild.append("KENSAI_ID=" + kensaDoctorIDStr + ";");
		strBuild.append("KENSAI_NAME=" + kensaDoctorNameStr + ";");
		strBuild.append("BIKOU=" + bikouStr + ";");
		strBuild.append("RENRAKU_MEMO=" + renrakuMemoStr + ";");
		strBuild.append("SIJI_ISI_ID=" + sijiIsiIDStr + ";");
		strBuild.append("SIJI_ISI_NAME=" + sijiIsiNameStr + ";");
		strBuild.append("SIJI_ISI_COMMENT=" + sijiIsiCommentStr + ";");
		strBuild.append("TOUSITIME=" + tousiTimeStr + ";");
		strBuild.append("BAKUSYASUU=" + bakushaSuuStr + ";");
		strBuild.append("STATUS=" + statusStr + ";");
		strBuild.append("STATUS_UPD_RSN=" + statusUpdRsnStr + ";");
		strBuild.append("STATUS_UPD_USR_ID=" + statusUpdUserIDStr + ";");
		strBuild.append("STATUS_UPD_USR_NAME=" + statusUpdUserNameStr + ";");
		strBuild.append("STATUS_UPD_DATE=" + statusUpdDateDT.toString() + ";");
		strBuild.append("RECEIPTFLAG=" + receiptFlgStr + ";");
		strBuild.append("EXAMSAVEFLAG=" + examSaveFlgStr + ";");
		strBuild.append("ENFORCEDOC_ID=" + enforceDocIDStr + ";");
		strBuild.append("ENFORCEDOC_NAME=" + enforceDocNameStr + ";");
		strBuild.append("ORDER_CLASSIFICATION=" + orderClassificationStr + ";");
		strBuild.append("COMMENTS=" + commentsStr + ";");
		strBuild.append("PROJECT_UID=" + projectUIDStr + ";");
		strBuild.append("PROJECT_REV=" + projectRevStr + ";");
		strBuild.append("PLAN_UID=" + planUIDStr + ";");
		strBuild.append("PLAN_REV=" + planRevStr + ";");
		strBuild.append("COURSE_ID=" + courseIDStr + ";");
		strBuild.append("REFPOINT_ID=" + refPointIDStr + ";");
		strBuild.append("TREAT_KIND_ID=" + treatKindIDStr + ";");
		strBuild.append("TREAT_KIND_NAME=" + treatKindNameStr + ";");
		strBuild.append("TREAT_DETAIL_KIND_ID=" + treatDetailKindIDStr + ";");
		strBuild.append("TREAT_DETAIL_KIND_NAME=" + treatDetailKindNameStr + ";");
		strBuild.append("TREAT_ITEM_ID=" + treatItemIDStr + ";");
		strBuild.append("TREAT_ITEM_NAME=" + treatItemNameStr + ";");
		strBuild.append("TREAT_MACHINE_ID=" + treatMachineIDStr + ";");
		strBuild.append("TREAT_ROOM_ID=" + treatRoomIDStr + ";");
		strBuild.append("QUALITY_ID=" + qualityIDStr + ";");
		strBuild.append("QUALITY_NAME=" + qualityNameStr + ";");
		strBuild.append("SOURCE_ID=" + sourceIDStr + ";");
		strBuild.append("SOURCE_NAME=" + sourceNameStr + ";");
		strBuild.append("RESULT_PARAM1=" + resultParam1Str + ";");
		strBuild.append("RESULT_PARAM2=" + resultParam2Str + ";");
		strBuild.append("DOSE=" + doseStr + ";");
		strBuild.append("TREAT_TIME=" + treatTimeStr + ";");
		strBuild.append("PS=" + psInt + ";");
		strBuild.append("TREAT_DR1_ID=" + treatDr1IDStr + ";");
		strBuild.append("TREAT_DR1_NAME=" + treatDr1NameStr + ";");
		strBuild.append("TREAT_DR2_ID=" + treatDr2IDStr + ";");
		strBuild.append("TREAT_DR2_NAME=" + treatDr2NameStr + ";");
		strBuild.append("CONF_STATUS=" + confStatusInt + ";");
		strBuild.append("CONF_REQ_USR_ID=" + confReqUsrIDStr + ";");
		strBuild.append("CONF_REQ_USR_KANJINAME=" + confReqUsrKanjiNameStr + ";");
		strBuild.append("CONF_REQ_DATE=" + confReqTimestamp.toString() + ";");
		strBuild.append("CONF_USR_ID=" + confUsrIDStr + ";");
		strBuild.append("CONF_USR_KANJINAME=" + confUsrKanjiNameStr + ";");
		strBuild.append("CONF_DATE=" + confTimestamp.toString() + ";");
		strBuild.append("AE_TITLE=" + aeTitleStr + ";");
		strBuild.append("STUDY_INSTANCE_UID=" + studyInstanceUIDStr + ";");
		strBuild.append("DEL_FLG=" + delFlgInt + ";");
		strBuild.append("ENF_ENG1_ID=" + enfEng1IDStr + ";");
		strBuild.append("ENF_ENG1_KANJINAME=" + enfEng1KanjiNameStr + ";");
		strBuild.append("ENF_ENG2_ID=" + enfEng2IDStr + ";");
		strBuild.append("ENF_ENG2_KANJINAME=" + enfEng2KanjiNameStr + ";");
		strBuild.append("ENF_ENG3_ID=" + enfEng3IDStr + ";");
		strBuild.append("ENF_ENG3_KANJINAME=" + enfEng3KanjiNameStr + ";");
		strBuild.append("ENTRY_DATE=" + entryTimestamp.toString() + ";");
		strBuild.append("ENTRY_USR_ID=" + entryUsrIDStr + ";");
		strBuild.append("ENTRY_USR_NAME=" + entryUsrNameStr + ";");
		strBuild.append("UPD_DATE=" + updTimestamp.toString() + ";");
		strBuild.append("UPD_USR_ID=" + updUsrIDStr + ";");
		strBuild.append("UPD_USR_NAME=" + updUsrNameStr + ";");
		strBuild.append("LG_PRE_RIS_ID=" + lgPreRisIDStr + ";");
		strBuild.append("LG_APPROVE_COMMENT=" + lgApproveCommentStr + ";");
		strBuild.append("ENTER_TIME=" + enterTimeStr + ";");
		strBuild.append("EXIT_TIME=" + exitTimeStr + ";");
		strBuild.append("STAY_TIME=" + stayTimeStr + ";");
		int orderBuiCount = arrListOrderBui.size();

		if( resultPatientBean != null )
		{
			strBuild.append(resultPatientBean.toString());
		}

		return strBuild.toString();
	}
	//
	// risIDStrのSET
	public void SetRisID( String risID )
	{
		if( risID != null )
		{
			this.risIDStr = risID;
		}
	}
	// risIDStrのGET
	public String GetRisID()
	{
		return this.risIDStr;
	}
	//
	// kensaTypeIDStrのSET
	public void SetKensaTypeID( String kensaTypeID )
	{
		if( kensaTypeID != null )
		{
			this.kensaTypeIDStr = kensaTypeID;
		}
	}
	// kensaTypeIDStrのGET
	public String GetKensaTypeID()
	{
		return this.kensaTypeIDStr;
	}
	//
	// kensaDateStrのSET --> V1.01では不使用
	/*
	public void SetKensaDate( String kensaDate )
	{
		if( kensaDate != null )
		{
			this.kensaDateStr = kensaDate;
		}
	}
	*/
	// kensaDateStrのGET
	public String GetKensaDate()
	{
		return this.kensaDateStr;
	}
	//
	// kensaSituIDStrのSET
	public void SetKensaSituID( String kensaSituID )
	{
		if( kensaSituID != null )
		{
			this.kensaSituIDStr = kensaSituID;
		}
	}
	// kensaSituIDStrのGET
	public String GetKensaSituID()
	{
		return this.kensaSituIDStr;
	}
	//
	// kensaKikiIDStrのSET
	public void SetKensaKikiID( String kensaKikiID )
	{
		if( kensaKikiID != null )
		{
			this.kensaKikiIDStr = kensaKikiID;
		}
	}
	// kensaKikiIDStrのGET
	public String GetKensaKikiID()
	{
		return this.kensaKikiIDStr;
	}
	//
	// kanjaIDStrのSET
	public void SetKanjaID( String kanjaID )
	{
		if( kanjaID != null )
		{
			this.kanjaIDStr = kanjaID;
		}
	}
	// kanjaIDStrのGET
	public String GetKanjaID()
	{
		return this.kanjaIDStr;
	}

	//
	// kensaDateAgeStrのSET
	public void SetKensaDateAge(String kensaDateAge)
	{
		if( kensaDateAge != null && kensaDateAge.length() > 0)
		{
			this.kensaDateAgeStr = kensaDateAge;
		}
	}

	//
	// kensaDateAgeStrのGET
	public String GetKensaDateAge()
	{
		return this.kensaDateAgeStr;
	}
	//
	// denpyoNyugaiKbnStrのGET
	public String GetDenpyoNyugaiKbn()
	{
		return this.denpyoNyugaiKbnStr;
	}
	//
	// uketukeTantouIDStrのSET
	public void SetUketukeTantouID( String tantouID )
	{
		if( tantouID != null )
		{
			this.uketukeTantouIDStr = tantouID;
		}
	}
	// uketukeTantouIDStrのGET
	public String GetUketukeTantouID()
	{
		return this.uketukeTantouIDStr;
	}
	//
	// uketukeTantouNameStrのSET
	public void SetUketukeTantouName( String tantouName )
	{
		if( tantouName != null )
		{
			this.uketukeTantouNameStr = tantouName;
		}
	}
	// uketukeTantouNameStrのGET
	public String GetUketukeTantouName()
	{
		return this.uketukeTantouNameStr;
	}
	//
	// receiptTimestampのSET
	public void SetReceiptTimestamp( Timestamp receipt )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(receipt) )
		{
			this.receiptTimestamp = receipt;
		}
	}
	// receiptTimestampのGET
	public Timestamp GetReceiptTimestamp()
	{
		return this.receiptTimestamp;
	}
	//
	// receiptTerminalIDStrのSET
	public void SetReceiptTerminalID( String terminalID )
	{
		if( terminalID != null )
		{
			this.receiptTerminalIDStr = terminalID;
		}
	}
	// receiptTerminalIDStrのGET
	public String GetReceiptTerminalID()
	{
		return this.receiptTerminalIDStr;
	}
	//
	// kensaGisiIDStrのSET
	public void SetKensaGisiID( String kensaGisi )
	{
		if( kensaGisi != null )
		{
			this.kensaGisiIDStr = kensaGisi;
		}
	}
	// kensaGisiIDStrのGET
	public String GetKensaGisiID()
	{
		return this.kensaGisiIDStr;
	}
	//
	// kensaGisiNameStrのSET
	public void SetKensaGisiName( String gisiName )
	{
		if( gisiName != null )
		{
			this.kensaGisiNameStr = gisiName;
		}
	}
	// kensaGisiNameStrのGET
	public String GetKensaGisiName()
	{
		return this.kensaGisiNameStr;
	}
	//
	// examStartTimestampのSET
	public void SetExamStartTimestamp( Timestamp start )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(start) )
		{
			this.examStartTimestamp = start;
		}
	}
	// examStartTimestampのGET
	public Timestamp GetExamStartTimestamp()
	{
		return this.examStartTimestamp;
	}
	//
	// examEndTimestampのSET
	public void SetExamEndTimestamp( Timestamp end )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(end) )
		{
			this.examEndTimestamp = end;
		}
	}
	// examEndTimestampのGET
	public Timestamp GetExamEndTimestamp()
	{
		return this.examEndTimestamp;
	}
	//
	// examTerminalIDStrのSET
	public void SetExamTerminalID( String terminalID )
	{
		if( terminalID != null )
		{
			this.examTerminalIDStr = terminalID;
		}
	}
	// examTerminalIDStrのGET
	public String GetExamTerminalID()
	{
		return this.examTerminalIDStr;
	}
	//
	// startNumberIntのGET
	public void SetStartNumber( int startNumber )
	{
		this.startNumberInt = startNumber;
	}
	//
	// startNumberIntのGET
	public int GetStartNumber()
	{
		return this.startNumberInt;
	}
	//
	// kangosiIDStrのSET
	public void SetKangosiID( String kangosiID )
	{
		if( kangosiID != null )
		{
			this.kangosiIDStr = kangosiID;
		}
	}
	// kangosiIDStrのGET
	public String GetKangosiID()
	{
		return this.kangosiIDStr;
	}
	//
	// kangosiNameStrのSET
	public void SetKangosiName( String kangosiName )
	{
		if( kangosiName != null )
		{
			this.kangosiNameStr = kangosiName;
		}
	}
	// kangosiNameStrのGET
	public String GetKangosiName()
	{
		return this.kangosiNameStr;
	}
	//
	// kensaDoctorIDStrのSET
	public void SetKensaDoctorID( String doctorID )
	{
		if( doctorID != null )
		{
			this.kensaDoctorIDStr = doctorID;
		}
	}
	// kensaDoctorIDStrのGET
	public String GetKensaDoctorID()
	{
		return this.kensaDoctorIDStr;
	}
	//
	// kensaDoctorNameStrのSET
	public void SetKensaDoctorName( String doctorName )
	{
		if( doctorName != null )
		{
			this.kensaDoctorNameStr = doctorName;
		}
	}
	// kensaDoctorNameStrGET
	public String GetKensaDoctorName()
	{
		return this.kensaDoctorNameStr;
	}
	//
	// bikouStrのSET
	public void SetBikou( String bikou )
	{
		if( bikou != null )
		{
			this.bikouStr = bikou;
		}
	}
	// bikouStrのGET
	public String GetBikou()
	{
		return this.bikouStr;
	}
	//
	// renrakuMemoStrのSET
	public void SetRenrakuMemo( String memo )
	{
		if( memo != null )
		{
			this.renrakuMemoStr = memo;
		}
	}
	// renrakuMemoStrGET
	public String GetRenrakuMemo()
	{
		return this.renrakuMemoStr;
	}
	//
	// sijiIsiIDStrのSET
	public void SetSijiIsiID( String sijiIsiID )
	{
		if( sijiIsiID != null )
		{
			this.sijiIsiIDStr = sijiIsiID;
		}
	}
	// sijiIsiIDStrのGET
	public String GetSijiIsiID()
	{
		return this.sijiIsiIDStr;
	}
	//
	// sijiIsiNameStrのSET
	public void SetSijiIsiName( String sijiIsiName )
	{
		if( sijiIsiName != null )
		{
			this.sijiIsiNameStr = sijiIsiName;
		}
	}
	// sijiIsiNameStrのGET
	public String GetSijiIsiName()
	{
		return this.sijiIsiNameStr;
	}
	//
	// sijiIsiCommentStrのSET
	public void SetSijiIsiComment( String comment )
	{
		if( comment != null )
		{
			this.sijiIsiCommentStr = comment;
		}
	}
	// sijiIsiCommentStrのGET
	public String GetSijiIsiComment()
	{
		return this.sijiIsiCommentStr;
	}
	//
	// tousiTimeStrのSET
	public void SetTousiTime( String tousiTime )
	{
		if( tousiTime != null )
		{
			this.tousiTimeStr = tousiTime;
		}
	}
	// tousiTimeStrのGET
	public String GetTousiTime()
	{
		return this.tousiTimeStr;
	}
	//
	// bakushaSuuStrのSET
	public void SetBakushaSuu( String bakushaSuu )
	{
		if( bakushaSuu != null )
		{
			this.bakushaSuuStr = bakushaSuu;
		}
	}
	// bakushaSuuStrのGET
	public String GetBakushaSuu()
	{
		return this.bakushaSuuStr;
	}
	//
	// gyoumuKbnStrのGET
	public String GetGyoumuKbn()
	{
		return this.gyoumuKbnStr;
	}
	//
	// statusStrのSET
	public void SetStatus( String status )
	{
		if( status != null )
		{
			if( status.compareTo(MwmMasterUtil.risStatus0Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus1Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus2Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus3Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus4Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus5Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus6Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus7Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus8Str) == 0
				|| status.compareTo(MwmMasterUtil.risStatus9Str) == 0)
			{
				this.statusStr = status;
			}
			else
			{
				this.statusStr = MwmMasterUtil.risStatus0Str;
			}
		}
		else
		{
			this.statusStr = MwmMasterUtil.risStatus0Str;
		}
	}
	// statusStrのGET
	public String GetStatus()
	{
		return this.statusStr;
	}
	//
	// receiptFlgStrのSET
	public void SetReceiptFlg( String flg )
	{
		if( flg != null )
		{
			if( flg.compareTo("OF") == 0 || flg.compareTo("ON") == 0 )
			{
				this.receiptFlgStr = flg;
			}
		}
	}
	// receiptFlgStrのGET
	public String GetReceiptFlg()
	{
		return this.receiptFlgStr;
	}
	//
	// yuusenFlgStrのGET
	public String GetYuusenFlg()
	{
		return this.yuusenFlgStr;
	}
	//
	// examSaveFlgStrのSET
	public void SetExamSaveFlg( String flg )
	{
		if( flg != null )
		{
			if( flg.compareTo("0") == 0 || flg.compareTo("1") == 0 )
			{
				this.examSaveFlgStr = flg;
			}
		}
	}
	// examSaveFlgStrのGET
	public String GetExamSaveFlg()
	{
		return this.examSaveFlgStr;
	}
	//
	// enforceDocIDStrのSET
	public void SetEnforceDocID( String docID )
	{
		if( docID != null )
		{
			this.enforceDocIDStr = docID;
		}
	}
	// enforceDocIDStrのGET
	public String GetEnforceDocID()
	{
		return this.enforceDocIDStr;
	}
	//
	// enforceDocNameStrのSET
	public void SetEnforceDocName( String docName )
	{
		if( docName != null )
		{
			this.enforceDocNameStr = docName;
		}
	}
	// enforceDocNameStrのGET
	public String GetEnforceDocName()
	{
		return this.enforceDocNameStr;
	}
	//
	// endCountIntのSET
	public void SetEndCount( int endCount )
	{
		this.endCountInt = endCount;
	}
	//
	// endCountIntのGET
	public int GetEndCount()
	{
		return this.endCountInt;
	}
	//
	// orderClassificationStrのSET
	public void SetOrderClassification( String classification )
	{
		if( classification != null )
		{
			this.orderClassificationStr = classification;
		}
	}
	// orderClassificationStrのGET
	public String GetOrderClassification()
	{
		return this.orderClassificationStr;
	}
	//
	// commentsStrのSET
	public void SetComments( String comments )
	{
		if( comments != null )
		{
			this.commentsStr = comments;
		}
	}
	// commentsStrのGET
	public String GetComments()
	{
		return this.commentsStr;
	}
	//
	// projectUIDStrのSET
	public void SetProjectUID( String projectUID )
	{
		if( projectUID != null )
		{
			this.projectUIDStr = projectUID;
		}
	}
	// projectUIDStrのGET
	public String GetProjectUID()
	{
		return this.projectUIDStr;
	}
	//
	// projectRevStrのSET
	public void SetProjectRev( String rev )
	{
		if( rev != null )
		{
			this.projectRevStr = rev;
		}
	}
	// projectRevStrのGET
	public String GetProjectRev()
	{
		return this.projectRevStr;
	}
	//
	// planUIDStrのSET
	public void SetPlanUID( String planUID )
	{
		if( planUID != null )
		{
			this.planUIDStr = planUID;
		}
	}
	// planUIDStrのGET
	public String GetPlanUID()
	{
		return this.planUIDStr;
	}
	//
	// planRevStrのSET
	public void SetPlanRev( String rev )
	{
		if( rev != null )
		{
			this.planRevStr = rev;
		}
	}
	// planRevStrのGET
	public String GetPlanRev()
	{
		return this.planRevStr;
	}
	//
	// courseIDStrのSET
	public void SetCourceID( String courseID )
	{
		if( courseID != null )
		{
			this.courseIDStr = courseID;
		}
	}
	// courseIDStrのGET
	public String GetCourseID()
	{
		return this.courseIDStr;
	}
	//
	// refPointIDStrのSET
	public void SetRefPointID( String refPointID )
	{
		if( refPointID != null )
		{
			this.refPointIDStr = refPointID;
		}
	}
	// refPointIDStrのGET
	public String GetRefPointID()
	{
		return this.refPointIDStr;
	}
	//
	// treatKindIDStrのSET
	public void SetTreatKindID( String kindID )
	{
		if( kindID != null )
		{
			this.treatKindIDStr = kindID;
		}
	}
	// treatKindIDStrのGET
	public String GetTreatKindID()
	{
		return this.treatKindIDStr;
	}
	//
	// treatKindNameStrのSET
	public void SetTreatKindName( String kindName )
	{
		if( kindName != null )
		{
			this.treatKindNameStr = kindName;
		}
	}
	// treatKindNameStrのGET
	public String GetTreatKindName()
	{
		return this.treatKindNameStr;
	}
	//
	// treatDetailKindIDStrのSET
	public void SetTreatDetailKindID( String kindID )
	{
		if( kindID != null )
		{
			this.treatDetailKindIDStr = kindID;
		}
	}
	// treatDetailKindIDStrのGET
	public String GetTreatDetailKindID()
	{
		return this.treatDetailKindIDStr;
	}
	//
	// treatDetailKindNameStrのSET
	public void SetTreatDetailKindName( String kindName )
	{
		if( kindName != null )
		{
			this.treatDetailKindNameStr = kindName;
		}
	}
	// treatDetailKindNameStrのGET
	public String GetTreatDetailKindName()
	{
		return this.treatDetailKindNameStr;
	}
	//
	// treatItemIDStrのSET
	public void SetTreatItemID( String itemID )
	{
		if( itemID != null )
		{
			this.treatItemIDStr = itemID;
		}
	}
	// treatItemIDStrのGET
	public String GetTreatItemID()
	{
		return this.treatItemIDStr;
	}
	//
	// treatItemNameStrのSET
	public void SetTreatItemName( String itemName )
	{
		if( itemName != null )
		{
			this.treatItemNameStr = itemName;
		}
	}
	// treatItemNameStrのGET
	public String GetTreatItemName()
	{
		return this.treatItemNameStr;
	}
	//
	// treatMachineIDStrのSET
	public void SetTreatMachineID( String machineID )
	{
		if( machineID != null )
		{
			this.treatMachineIDStr = machineID;
		}
	}
	// treatMachineIDStrのGET
	public String GetTreatMachineID()
	{
		return this.treatMachineIDStr;
	}
	//
	// treatRoomIDStrのSET
	public void SetTreatRoomID( String roomID )
	{
		if( roomID != null )
		{
			this.treatRoomIDStr = roomID;
		}
	}
	// treatRoomIDStrのGET
	public String GetTreatRoomID()
	{
		return this.treatRoomIDStr;
	}
	//
	// qualityIDStrのSET
	public void SetQualityID( String qualityID )
	{
		if( qualityID != null )
		{
			this.qualityIDStr = qualityID;
		}
	}
	// qualityIDStrのGET
	public String GetQualityID()
	{
		return this.qualityIDStr;
	}
	//
	// qualityNameStrのSET
	public void SetQualityName( String qualityName )
	{
		if( qualityName != null )
		{
			this.qualityNameStr = qualityName;
		}
	}
	// qualityNameStrのGET
	public String GetQualityName()
	{
		return this.qualityNameStr;
	}
	//
	// sourceIDStrのSET
	public void SetSourceID( String sourceID )
	{
		if( sourceID != null )
		{
			this.sourceIDStr = sourceID;
		}
	}
	// sourceIDStrのGET
	public String GetSourceID()
	{
		return this.sourceIDStr;
	}
	//
	// sourceNameStrのSET
	public void SetSourceName( String sourceName )
	{
		if( sourceName != null )
		{
			this.sourceNameStr = sourceName;
		}
	}
	// sourceNameStrのGET
	public String GetSourceName()
	{
		return this.sourceNameStr;
	}
	//
	// resultParam1StrのSET
	public void SetResultParam1( String param1 )
	{
		if( param1 != null )
		{
			this.resultParam1Str = param1;
		}
	}
	// resultParam1StrのGET
	public String GetResultParam1()
	{
		return this.resultParam1Str;
	}
	//
	// resultParam2StrのSET
	public void SetResultParam2( String param2 )
	{
		if( param2 != null )
		{
			this.resultParam2Str = param2;
		}
	}
	// resultParam2StrのGET
	public String GetResultParam2()
	{
		return this.resultParam2Str;
	}
	//
	// doseStrのSET
	public void SetDose( String dose )
	{
		if( dose != null )
		{
			this.doseStr = dose;
		}
	}
	// doseStrのGET
	public String GetDose()
	{
		return this.doseStr;
	}
	//
	// treatTimeStrのSET
	public void SetTreatTime( String treatTime )
	{
		if( treatTime != null )
		{
			this.treatTimeStr = treatTime;
		}
	}
	// treatTimeStrのGET
	public String GetTreatTime()
	{
		return this.treatTimeStr;
	}
	//
	// psIntのSET
	public void SetPS( int ps )
	{
		this.psInt = ps;
	}
	// psIntのGET
	public int GetPS()
	{
		return this.psInt;
	}
	//
	// treatDr1IDStrのSET
	public void SetTreatDr1ID( String drID )
	{
		if( drID != null )
		{
			this.treatDr1IDStr = drID;
		}
	}
	// treatDr1IDStrのGET
	public String GetTreatDr1ID()
	{
		return this.treatDr1IDStr;
	}
	//
	// treatDr1NameStrのSET
	public void SetTreatDr1Name( String drName )
	{
		if( drName != null )
		{
			this.treatDr1NameStr = drName;
		}
	}
	// treatDr1NameStrのGET
	public String GetTreatDr1Name()
	{
		return this.treatDr1NameStr;
	}
	//
	// treatDr2IDStrのSET
	public void SetTreatDr2ID( String drID )
	{
		if( drID != null )
		{
			this.treatDr2IDStr = drID;
		}
	}
	// treatDr2IDStrのGET
	public String GetTreatDr2ID()
	{
		return this.treatDr2IDStr;
	}
	//
	// treatDr2NameStrのSET
	public void SetTreatDr2Name( String drName )
	{
		if( drName != null )
		{
			this.treatDr2NameStr = drName;
		}
	}
	// treatDr2NameStrのGET
	public String GetTreatDr2Name()
	{
		return this.treatDr2NameStr;
	}
	//
	// confStatusIntのSET
	public void SetConfStatus( int status )
	{
		if( status == 0 || status == 1 || status == 2 )
		{
			this.confStatusInt = status;
		}
		else
		{
			this.confStatusInt = 0;
		}
	}
	// confStatusIntのGET
	public int GetConfStatus()
	{
		return this.confStatusInt;
	}
	//
	// confReqUsrIDStrのSET
	public void SetConfReqUsrID( String usrID )
	{
		if( usrID != null )
		{
			this.confReqUsrIDStr = usrID;
		}
	}
	// confReqUsrIDStrのGET
	public String GetConfReqUsrID()
	{
		return this.confReqUsrIDStr;
	}
	//
	// confReqUsrKanjiNameStrのSET
	public void SetConfReqUsrKanjiName( String kanjiName )
	{
		if( kanjiName != null )
		{
			this.confReqUsrKanjiNameStr = kanjiName;
		}
	}
	// confReqUsrKanjiNameStrのGET
	public String GetConfReqUsrKanjiName()
	{
		return this.confReqUsrKanjiNameStr;
	}
	//
	// confReqTimestampのSET
	public void SetConfReqTimestamp( Timestamp date )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(date) )
		{
			this.confReqTimestamp = date;
		}
	}
	// confReqTimestampのGET
	public Timestamp GetConfReqTimestamp()
	{
		return this.confReqTimestamp;
	}
	//
	// confUsrIDStrのSET
	public void SetConfUsrID( String usrID )
	{
		if( usrID != null )
		{
			this.confUsrIDStr = usrID;
		}
	}
	// confUsrIDStrのGET
	public String GetConfUsrID()
	{
		return this.confUsrIDStr;
	}
	//
	// confUsrKanjiNameStrのSET
	public void SetConfUsrKanjiName( String kanjiName )
	{
		if( kanjiName != null )
		{
			this.confUsrKanjiNameStr = kanjiName;
		}
	}
	// confUsrKanjiNameStrのGET
	public String GetConfUsrKanjiName()
	{
		return this.confUsrKanjiNameStr;
	}
	//
	// confTimestampのSET
	public void SetConfTimestamp( Timestamp date )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(date) )
		{
			this.confTimestamp = date;
		}
	}
	// confTimestampのGET
	public Timestamp GetConfTimestamp()
	{
		return this.confTimestamp;
	}
	//
	// aeTitleStrのSET
	public void SetAETitle( String aeTitle )
	{
		if( aeTitle != null )
		{
			this.aeTitleStr = aeTitle;
		}
	}
	// aeTitleStrのGET
	public String GetAETitle()
	{
		return this.aeTitleStr;
	}
	//
	// studyInstanceUIDStrのSET
	public void SetStudyInstanceUID( String studyInstUID )
	{
		if( studyInstUID != null )
		{
			this.studyInstanceUIDStr = studyInstUID;
		}
	}
	// studyInstanceUIDStrのGET
	public String GetStudyInstanceUID()
	{
		return this.studyInstanceUIDStr;
	}
	//
	//
	// delFlgIntのSET
	public void SetDelFlg( int delFlg )
	{
		if( delFlg == 0 || delFlg == 1 )
		{
			this.delFlgInt = delFlg;
		}
		else
		{
			this.delFlgInt = 0;
		}
	}
	// delFlgIntのGET
	public int GetDelFlg()
	{
		return this.delFlgInt;
	}
	//
	//
	// enfEng1IDStrのSET
	public void SetEnfEng1ID( String engID )
	{
		if( engID != null )
		{
			this.enfEng1IDStr = engID;
		}
	}
	// enfEng1IDStrのGET
	public String GetEnfEng1ID()
	{
		return this.enfEng1IDStr;
	}
	//
	// enfEng1KanjiNameStrのSET
	public void SetEnfEng1KanjiName( String engName )
	{
		if( engName != null )
		{
			this.enfEng1KanjiNameStr = engName;
		}
	}
	// enfEng1KanjiNameStrのGET
	public String GetEnfEng1KanjiName()
	{
		return this.enfEng1KanjiNameStr;
	}
	//
	// enfEng2IDStrのSET
	public void SetEnfEng2ID( String engID )
	{
		if( engID != null )
		{
			this.enfEng2IDStr = engID;
		}
	}
	// enfEng2IDStrのGET
	public String GetEnfEng2ID()
	{
		return this.enfEng2IDStr;
	}
	//
	// enfEng2KanjiNameStrのSET
	public void SetEnfEng2KanjiName( String engName )
	{
		if( engName != null )
		{
			this.enfEng2KanjiNameStr = engName;
		}
	}
	// enfEng2KanjiNameStrのGET
	public String GetEnfEng2KanjiName()
	{
		return this.enfEng2KanjiNameStr;
	}
	//
	// enfEng3IDStrのSET
	public void SetEnfEng3ID( String engID )
	{
		if( engID != null )
		{
			this.enfEng3IDStr = engID;
		}
	}
	// enfEng3IDStrのGET
	public String GetEnfEng3ID()
	{
		return this.enfEng3IDStr;
	}
	//
	// enfEng3KanjiNameStrのSET
	public void SetEnfEng3KanjiName( String engName )
	{
		if( engName != null )
		{
			this.enfEng3KanjiNameStr = engName;
		}
	}
	// enfEng3KanjiNameStrのGET
	public String GetEnfEng3KanjiName()
	{
		return this.enfEng3KanjiNameStr;
	}
	//
	// entryTimestampのSET
	public void SetEntryTimestamp( Timestamp entry )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(entry) )
		{
			this.entryTimestamp = entry;
		}
	}
	// entryTimestampのGET
	public Timestamp GetEntryTimestamp()
	{
		return this.entryTimestamp;
	}
	//
	// entryUsrIDStrのSET
	public void SetEntryUsrID( String usrID )
	{
		if( usrID != null )
		{
			this.entryUsrIDStr = usrID;
		}
	}
	// entryUsrIDStrのGET
	public String GetEntryUsrID()
	{
		return this.entryUsrIDStr;
	}
	//
	// entryUsrNameStrのSET
	public void SetEntryUsrName( String usrName )
	{
		if( usrName != null )
		{
			this.entryUsrNameStr = usrName;
		}
	}
	// entryUsrNameStrのGET
	public String GetEntryUsrName()
	{
		return this.entryUsrNameStr;
	}
	//
	// updTimestampのSET
	public void SetUpdTimestamp( Timestamp upd )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(upd) )
		{
			this.updTimestamp = upd;
		}
	}
	// updTimestampのGET
	public Timestamp GetUpdTimestamp()
	{
		return this.updTimestamp;
	}
	//
	// updUsrIDStrのSET
	public void SetUpdUsrID( String usrID )
	{
		if( usrID != null )
		{
			this.updUsrIDStr = usrID;
		}
	}
	// updUsrIDStrのGET
	public String GetUpdUsrID()
	{
		return this.updUsrIDStr;
	}
	//
	// updUsrNameStrのSET
	public void SetUpdUsrName( String usrName )
	{
		if( usrName != null )
		{
			this.updUsrNameStr = usrName;
		}
	}
	// updUsrNameStrのGET
	public String GetUpdUsrName()
	{
		return this.updUsrNameStr;
	}
	//
	// lgPreRisIDStrのSET
	public void SetLgPreRisID( String lgPreRisID )
	{
		if( lgPreRisID != null )
		{
			this.lgPreRisIDStr = lgPreRisID;
		}
	}
	// lgPreRisIDStrのGET
	public String GetLgPreRisID()
	{
		return this.lgPreRisIDStr;
	}
	//
	// lgApproveCommentStrのSET
	public void SetLgApproveComment( String lgApproveComment )
	{
		if( lgApproveComment != null )
		{
			this.lgApproveCommentStr = lgApproveComment;
		}
	}
	// lgApproveCommentStrのGET
	public String GetLgApproveComment()
	{
		return this.lgApproveCommentStr;
	}
	//
	// enterTimeStrのSET
	public void SetEnterTime( String enterTime )
	{
		if( enterTime != null )
		{
			this.enterTimeStr = enterTime;
		}
	}
	// enterTimeStrのGET
	public String GetEnterTime()
	{
		return this.enterTimeStr;
	}
	//
	// exitTimeStrのSET
	public void SetExitTime( String exitTime )
	{
		if( exitTime != null )
		{
			this.exitTimeStr = exitTime;
		}
	}
	// exitTimeStrのGET
	public String GetExitTime()
	{
		return this.exitTimeStr;
	}
	//
	// stayTimeStrのSET
	public void SetStayTime( String stayTime )
	{
		if( stayTime != null )
		{
			this.stayTimeStr = stayTime;
		}
	}
	// stayTimeStrのGET
	public String GetStayTime()
	{
		return this.stayTimeStr;
	}
	//
	// statusUpdRsnStrのSET
	public void SetStatusUpdRsn( String reason )
	{
		if( reason != null )
		{
			statusUpdRsnStr = reason;
		}
	}
	// statusUpdRsnStrのGET
	public String GetStatusUpdRsn()
	{
		return this.statusUpdRsnStr;
	}
	//
	// statusUpdUserIDStrのSET
	public void SetStatusUpdUserID( String userID )
	{
		if( userID != null )
		{
			this.statusUpdUserIDStr = userID;
		}
	}
	// statusUpdUserIDStrのGET
	public String GetStatusUpdUserID()
	{
		return this.statusUpdUserIDStr;
	}
	//
	// statusUpdUserNameStrのSET
	public void SetStatusUpdUserName( String userName )
	{
		if( userName != null )
		{
			this.statusUpdUserNameStr = userName;
		}
	}
	// statusUpdUserNameStrのGET
	public String GetStatusUpdUserName()
	{
		return this.statusUpdUserNameStr;
	}
	//
	// statusUpdDateDTのSET
	public void SetStatusUpdDate( Timestamp statusDT )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(statusDT) )
		{
			this.statusUpdDateDT = statusDT;
		}
	}
	// statusUpdDateDTのGET
	public Timestamp GetStatusUpdDate()
	{
		return this.statusUpdDateDT;
	}
	//
	/*
	// arrListOrderBuiへの追加
	public void AddOrderBui( OrderBuiBean orderBui )
	{
		if( orderBui != null )
		{
			arrListOrderBui.Add(orderBui);
		}
	}
	*/
	// arrListOrderBuiの取得
	public ArrayList GetOrderBuiList()
	{
		return this.arrListOrderBui;
	}
	// arrListOrderBuiの初期化
	public void ReconstructOrderBuiList( ArrayList orderBuiList )
	{
		if( orderBuiList != null )
		{
			this.arrListOrderBui.clear();
			this.arrListOrderBui = orderBuiList;
		}
	}
	//
	// arrListZoueizaiの取得
	public ArrayList GetZoueizaiList()
	{
		return this.arrListZoueizai;
	}
	// arrListZoueizaiの初期化
	public void ReconstructZoueizaiList( ArrayList zoueizaiList )
	{
		if( zoueizaiList != null )
		{
			this.arrListZoueizai.clear();
			this.arrListZoueizai = zoueizaiList;
		}
	}
	//
	// arrListSatueiの取得
	public ArrayList GetSatueiList()
	{
		return this.arrListSatuei;
	}
	// arrListSatueiの初期化
	public void ReconstructSatueiList( ArrayList listSatuei )
	{
		if( listSatuei != null )
		{
			this.arrListSatuei.clear();
			this.arrListSatuei = listSatuei;
		}
	}
	//
	//
	public void SetResultPatientInfo( MwmPatientInfoBean resultPatient )
	{
		if( resultPatient != null )
		{
			this.resultPatientBean = resultPatient;
		}
	}
	//
	public MwmPatientInfoBean GetResultPatientInfo()
	{
		return this.resultPatientBean;
	}
	//
	//
	public void SetResultBool( boolean result )
	{
		this.resultBool = result;
	}
	//
	public boolean GetResultBool()
	{
		return this.resultBool;
	}
}
