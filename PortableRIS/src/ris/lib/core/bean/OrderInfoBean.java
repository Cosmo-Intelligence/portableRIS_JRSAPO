package ris.lib.core.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import ris.lib.core.util.CommonUtil;
import ris.portable.common.Const;

/// <summary>
///
/// オーダ情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.03.05	: 112478 / A.Kobayashi		: original
/// V2.01.01.13000	: 2011.11.15    extends 999999 / NSK_H.Hashimoto	: OMH-1-05
///
/// </summary>
public class OrderInfoBean
{
	private String risIDStr				= "";	//Ris.OrderMainTable.RIS_ID
	private String systemKbnStr			= "";	//Ris.OrderMainTable.SystemKbn
	private String studyInstanceUIDStr	= "";	//Ris.OrderMainTable.StudyInstanceUID
	private String orderNoStr			= "";	//Ris.OrderMainTable.OrderNo
	private String accessionNoStr		= "";	//Ris.OrderMainTable.AccessionNo
	private String kensaDateStr			= "";	//Ris.OrderMainTable.Kensa_Date
	private String kensaStartTimeStr	= "";	//Ris.OrderMainTable.Kensa_StartTime
	private String kensatypeIDStr		= "";	//Ris.OrderMainTable.Kensatype_ID
	private String kensasituIDStr		= "";	//Ris.OrderMainTable.Kensasitu_ID
	private String kensakikiIDStr		= "";	//Ris.OrderMainTable.Kensakiki_ID
	private String syotisituFlgStr		= "";	//Ris.OrderMainTable.Syotisitu_Flg
	private String kanjaIDStr			= "";	//Ris.OrderMainTable.Kanja_ID
	private String kensaDateAgeStr		= "";	//Ris.OrderMainTable.Kensa_Date_Age
	private String denpyoNyugaiKbnStr	= "";	//Ris.OrderMainTable.Denpyo_NyugaiKbn
	private String denpyoByoutouIDStr	= "";	//Ris.OrderMainTable.Denpyo_Byotou_ID
	private String denpyoByosituIDStr	= "";	//Ris.OrderMainTable.Denpyo_Byositu_ID
	private String iraiSectionIDStr		= "";	//Ris.OrderMainTable.Irai_Section_ID
	private String iraiDoctorNameStr	= "";	//Ris.OrderMainTable.Irai_Doctor_Name
	private String iraiDoctorNoStr		= "";	//Ris.OrderMainTable.Irai_Doctor_NO
	private String orderSectionIDStr	= "";	//Ris.OrderMainTable.Order_Section_ID
	private String iraiDoctorRenrakuStr	= "";	//Ris.OrderMainTable.Irai_Doctor_Renraku
	private String dokueiFlgStr			= "";	//Ris.OrderMainTable.Dokuei_Flg
	//
	//作業用
	private String statusStr			= "";
	private int	   terminalIDInt		= -1;     // 一ノ瀬保留Const.INT_MINVALUE;
	private String terminalNameStr		= "";
	private String receptNumberStr		= "";
	private String receptInitialCharStr	= "";	// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
	private String modalityTypeStr		= "";
	private boolean   receitpDateBool		= false;
	//
	private ExtendOrderInfoBean extendOrderInfoBean = null;
	//
	private ArrayList arrListOrderBui = new ArrayList(); //OrderBuiBeanのリスト
	//--> OrderBuiTable.RIS_ID=ExMainTable.RIS_IDとなるレコード（複数）のリスト
//
//
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
	// systemKbnStrのSET
	public void SetSystemKbn(String systemKbn)
	{
		if (systemKbn != null)
		{
			this.systemKbnStr = systemKbn;
		}
	}
	// systemKbnStrのGET
	public String GetSystemKbn()
	{
		return systemKbnStr;
	}
//
	// studyInstanceUIDStrのSET
	public void SetStudyInstanceUID(String studyInstanceUID)
	{
		if (studyInstanceUID != null)
		{
			this.studyInstanceUIDStr = studyInstanceUID;
		}
	}
	// studyInstanceUIDStrのGET
	public String GetStudyInstanceUID()
	{
		return studyInstanceUIDStr;
	}
//
	// orderNoStrのSET
	public void SetOrderNo(String orderNo)
	{
		if (orderNo != null)
		{
			this.orderNoStr = orderNo;
			if (this.accessionNoStr.length() == 0)
			{
				this.accessionNoStr = orderNo;
			}
		}
	}
	// orderNoStrのGET
	public String GetOrderNo()
	{
		return this.orderNoStr;
	}
//
	// accessionNoStrのSET
	public void SetAccessionNo(String accessionNo)
	{
		if (accessionNo != null)
		{
			this.accessionNoStr = accessionNo;
		}
	}
	// accessionNoStrのGET
	public String GetAccessionNo()
	{
		return accessionNoStr;
	}
//
	// kensaDateStrのSET
	public void SetKensaDate(String kensaDate)
	{
		if (kensaDate != null)
		{
			this.kensaDateStr = kensaDate;
		}
	}
	// kensaDateStrのGET
	public Integer GetKensaDate()
	{
		if (kensaDateStr == null || kensaDateStr.length() == 0)
		{
			return Const.INT_MINVALUE;
		}
		else
		{
			return Integer.parseInt(kensaDateStr);
		}
	}
	// kensaDateStrのGET
	public Timestamp GetKensaDateValue()
	{
		Timestamp retDate = Const.TIMESTAMP_MINVALUE;

		try
		{
			if (kensaDateStr.toString().length() > 0 &&
				kensaDateStr.length() == 8)
			{
				String dateStr = kensaDateStr.substring(0, 4) + "-" +
								 kensaDateStr.substring(4, 4 + 2) + "-" +
								 kensaDateStr.substring(6, 6 + 2) + " 00:00:00";

				retDate = Timestamp.valueOf(dateStr);
			}
		}
		catch (Exception e)
		{
		}
		return retDate;
	}

	// kensaDateStr+kensaStartTimeStrのGET
	public String GetKensaTimestampString()
	{
		String retStr = "";

		try
		{
			if (kensaDateStr.toString().length() > 0 &&
				kensaDateStr.length() == 8)
			{
				retStr = kensaDateStr.substring(0, 4) + "/" +
						 kensaDateStr.substring(4, 4 + 2) + "/" +
						 kensaDateStr.substring(6, 6 + 2);

				retStr += " " + GetKensaStartTimeFormat();
			}
		}
		catch (Exception e)
		{
		}
		return retStr;
	}
//
	// kensaStartTimeStrのSET
	public void SetKensaStartTime(String kensaStartTime)
	{
		if (kensaStartTime != null)
		{
			this.kensaStartTimeStr = kensaStartTime;
		}
	}
	// kensaStartTimeStrのGET
	public String GetKensaStartTime()
	{
		if (kensaStartTimeStr == null || kensaStartTimeStr.length() == 0)
		{
			return "0";
		}
		else
		{
			return kensaStartTimeStr;
		}
	}

	// kensaTimestampFormatStrのGET
	public String GetKensaStartTimeFormat()
	{
		//検査時刻を変換する
		return CommonUtil.ConvertKensaTime(this.kensaStartTimeStr);
	}
//
	// kensatypeIDStrのSET
	public void SetKensatypeID(String kensatypeID)
	{
		if (kensatypeID != null)
		{
			this.kensatypeIDStr = kensatypeID;
		}
	}
	// kensatypeIDStrのGET
	public String GetKensatypeID()
	{
		return this.kensatypeIDStr;
	}
	//
	// kensasituIDStrのSET
	public void SetKensasituID(String kensasituID)
	{
		if (kensasituID != null)
		{
			this.kensasituIDStr = kensasituID;
		}
	}
	// kensasituIDStrのGET
	public String GetKensasituID()
	{
		return this.kensasituIDStr;
	}
	//
	// kensakikiIDStrのSET
	public void SetKensakikiID(String kensakikiID)
	{
		if (kensakikiID != null)
		{
			this.kensakikiIDStr = kensakikiID;
		}
	}
	// kensakikiIDStrのGET
	public String GetKensakikiID()
	{
		return this.kensakikiIDStr;
	}
//
	// syotisituFlgStrのSET
	public void SetSyotisituFlg(String syotisituFlg)
	{
		if (syotisituFlg != null)
		{
			this.syotisituFlgStr = syotisituFlg;
		}
	}
	// syotisituFlgStrのGET
	public String GetSyotisituFlg()
	{
		return syotisituFlgStr;
	}
//
	// kanjaIDStrのSET
	public void SetKanjaID(String kanjaID)
	{
		if (kanjaID != null)
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
		if (kensaDateAge != null)
		{
			this.kensaDateAgeStr = kensaDateAge;
		}
	}
	// kensaDateAgeStrのGET
	public Integer GetKensaDateAge()
	{
		if (kensaDateAgeStr == null || kensaDateAgeStr.length() ==0)
		{
			return Const.INT_MINVALUE;
		}
		else
		{
			return Integer.parseInt(kensaDateAgeStr);
		}
	}
//
	// denpyoNyugaiKbnStrのSET
	public void SetDenpyoNyugaiKbn(String denpyoNyugaiKbn)
	{
		if (denpyoNyugaiKbn != null)
		{
			this.denpyoNyugaiKbnStr = denpyoNyugaiKbn;
		}
	}
	// denpyoNyugaiKbnStrのGET
	public String GetDenpyoNyugaiKbn()
	{
		return this.denpyoNyugaiKbnStr;
	}
//
	// denpyoByoutouIDStrのSET
	public void SetDenpyoByoutouID(String denpyoByoutouID)
	{
		if (denpyoByoutouID != null)
		{
			this.denpyoByoutouIDStr = denpyoByoutouID;
		}
	}
	// denpyoByoutouIDStrのGET
	public String GetDenpyoByoutouID()
	{
		return this.denpyoByoutouIDStr;
	}
//
	// denpyoByosituIDStrのSET
	public void SetDenpyoByosituID(String denpyoByosituID)
	{
		if (denpyoByosituID != null)
		{
			this.denpyoByosituIDStr = denpyoByosituID;
		}
	}
	// denpyoByosituIDStrのGET
	public String GetDenpyoByosituID()
	{
		return this.denpyoByosituIDStr;
	}
//
	// iraiSectionIDStrのSET
	public void SetIraiSectionID(String iraiSectionID)
	{
		if (iraiSectionID != null)
		{
			this.iraiSectionIDStr = iraiSectionID;
		}
	}
	// iraiSectionIDStrのGET
	public String GetIraiSectionID()
	{
		return this.iraiSectionIDStr;
	}
//
	// iraiDoctorNameStrのSET
	public void SetIraiDoctorName(String iraiDoctorName)
	{
		if (iraiDoctorName != null)
		{
			this.iraiDoctorNameStr = iraiDoctorName;
		}
	}
	// iraiDoctorNameStrのGET
	public String GetIraiDoctorName()
	{
		return iraiDoctorNameStr;
	}
//
	// iraiDoctorNoStrのSET
	public void SetIraiDoctorNo(String iraiDoctorNo)
	{
		if (iraiDoctorNo != null)
		{
			this.iraiDoctorNoStr = iraiDoctorNo;
		}
	}
	// iraiDoctorNameStrのGET
	public String GetIraiDoctorNo()
	{
		return iraiDoctorNoStr;
	}
//
	// orderSectionIDStrのSET
	public void SetOrderSectionID(String orderSectionID)
	{
		if (orderSectionID != null)
		{
			this.orderSectionIDStr = orderSectionID;
		}
	}
	// orderSectionIDStrのGET
	public String GetOrderSectionID()
	{
		return orderSectionIDStr;
	}
//
	// iraiDoctorRenrakuStrのSET
	public void SetIraiDoctorRenraku(String iraiDoctorRenraku)
	{
		if (iraiDoctorRenraku != null)
		{
			this.iraiDoctorRenrakuStr = iraiDoctorRenraku;
		}
	}
	// iraiDoctorRenrakuStrのGET
	public String GetIraiDoctorRenraku()
	{
		return iraiDoctorRenrakuStr;
	}
//
	// dokueiFlgStrのSET
	public void SetDokueiFlg(String dokueiFlg)
	{
		if (dokueiFlg != null)
		{
			this.dokueiFlgStr = dokueiFlg;
		}
	}
	// dokueiFlgStrのGET
	public String GetDokueiFlg()
	{
		return dokueiFlgStr;
	}
//
//
//
	// statusStrのSET
	public void SetStatus( String status )
	{
		if( status != null )
		{
			this.statusStr = status;
		}
	}
	// statusStrのGET
	public String GetStatus()
	{
		return this.statusStr;
	}
//
	// terminalIDIntのSET
	public void SetTerminalID(Integer terminalID)
	{
		this.terminalIDInt = terminalID;
	}
	// terminalIDIntのGET
	public Integer GetTerminalID()
	{
		return this.terminalIDInt;
	}
//
	// terminalNameStrのSET
	public void SetTerminalName(String terminalName)
	{
		if (terminalName != null)
		{
			this.terminalNameStr = terminalName;
		}
	}
	// terminalNameStrのGET
	public String GetTerminalName()
	{
		return terminalNameStr;
	}
//
	// receptNumberStrのSET
	public void SetReceptNumber(String receptNumber)
	{
		if (receptNumber != null)
		{
			this.receptNumberStr = receptNumber;
		}
	}
	// kensasituIDStrのGET
	public String GetReceptNumber()
	{
		return this.receptNumberStr;
	}
//
	// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
	// receptInitialCharStrのSET
	public void SetReceptInitialChar(String receptInitialChar)
	{
		if (receptInitialChar != null)
		{
			this.receptInitialCharStr = receptInitialChar;
		}
	}
	// receptInitialCharStrのGET
	public String GetReceptInitialChar()
	{
		return this.receptInitialCharStr;
	}
	// 2011.11.15 Add NSK_H.Hashimoto End
//
	// modalityTypeStrのSET
	public void SetModalityType(String modalityType)
	{
		if (modalityType != null)
		{
			this.modalityTypeStr = modalityType;
		}
	}
	// modalityTypeStrのGET
	public String GetModalityType()
	{
		return modalityTypeStr;
	}
//
	// receitpDateBoolのSET
	public void SetReceitpDateFlg(boolean receitpDate)
	{
		this.receitpDateBool = receitpDate;
	}
	// receitpDateBoolのGET
	public boolean IsReceitpDateFlg()
	{
		return receitpDateBool;
	}
//
//
//
	// extendOrderInfoBeanのSET
	public void SetExtendOrderInfoBean(ExtendOrderInfoBean extendOrderInfoBean)
	{
		if (extendOrderInfoBean != null)
		{
			this.extendOrderInfoBean = extendOrderInfoBean;
		}
	}
	// extendOrderInfoBeanのGET
	public ExtendOrderInfoBean GetExtendOrderInfoBean()
	{
		return extendOrderInfoBean;
	}
//
	// arrListOrderBuiの取得
	public ArrayList GetOrderBuiList()
	{
		return this.arrListOrderBui;
	}
	// arrListOrderBuiの初期化
	public void ReconstructOrderBuiList(ArrayList orderBuiList)
	{
		if (orderBuiList != null)
		{
			this.arrListOrderBui.clear();
			this.arrListOrderBui = orderBuiList;
		}
	}
}
