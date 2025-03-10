package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.portable.common.Const;

/// <summary>
///
/// 拡張オーダ情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			 (Date)			(ID / NAME)				 (Comment)
/// V1.00.00		: 2009.03.05	: 112478 / A.Kobayashi	: original
/// V2.01.01.13000	: 2011.11.25  　: 999999 / NSK_M.Ochiai	: OMH-1-2
///
/// </summary>
public class ExtendOrderInfoBean
{
	private String risIDStr				= "";	//Ris.ExtendOrderInfo.RIS_ID
	private String orderDateStr			= "";	//Ris.ExtendOrderInfo.ORDER_DATE
	private String userNoStr			= "";	//Ris.ExtendOrderInfo.USER_NO
	private String updatedateStr		= "";	//Ris.ExtendOrderInfo.UPDATEDATE
	private String updatetimeStr		= "";	//Ris.ExtendOrderInfo.UPDATETIME
	private String risHakkoTerminalStr	= "";	//Ris.ExtendOrderInfo.RIS_HAKKO_TERMINAL
	private String risHakkoUserStr		= "";	//Ris.ExtendOrderInfo.RIS_HAKKO_USER
	private String hisHakkoDateStr		= "";	//Ris.ExtendOrderInfo.HIS_HAKKO_DATE
	private String hisHakkoTerminalStr	= "";	//Ris.ExtendOrderInfo.HIS_HAKKO_TERMINAL
	private String hisHakkoUserStr		= "";	//Ris.ExtendOrderInfo.HIS_HAKKO_USER
	private String hisUpdateDateStr		= "";	//Ris.ExtendOrderInfo.HIS_UPDATE_DATE
	private String riOrderFlgStr		= "";	//Ris.ExtendOrderInfo.RI_ORDER_FLG
	private String satueiPlaceStr		= "";	//Ris.ExtendOrderInfo.SATUEI_PLACE
	private String yoteiKaikeiFlgStr	= "";	//Ris.ExtendOrderInfo.YOTEIKAIKEI_FLG
	private String isitatiaiFlgStr		= "";	//Ris.ExtendOrderInfo.ISITATIAI_FLG
	private String denpyoInsatuFlgStr	= "";	//Ris.ExtendOrderInfo.DENPYO_INSATU_FLG
	private String portableFlgStr		= "";	//Ris.ExtendOrderInfo.PORTABLE_FLG
	private String kanjaSyokaiFlgStr	= "";	//Ris.ExtendOrderInfo.KANJA_SYOKAI_FLG
	private String sikyuFlgStr			= "";	//Ris.ExtendOrderInfo.SIKYU_FLG
	private String seisanDateStr		= "";	//Ris.ExtendOrderInfo.SEISAN_DATE
	private String seisanFlgStr			= "";	//Ris.ExtendOrderInfo.SEISAN_FLG
	private String seisanKbnDateStr		= "";	//Ris.ExtendOrderInfo.SEISAN_KBN_DATE
	private String douishoFlgStr		= "";	//Ris.ExtendOrderInfo.DOUISHO_FLG
	private String addendum01Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM01
	private String addendum02Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM02
	private String addendum03Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM03
	private String addendum04Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM04
	private String addendum05Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM05
	private String addendum06Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM06
	private String addendum07Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM07
	private String addendum08Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM08
	private String addendum09Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM09
	private String addendum10Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM10
	private String addendum11Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM11
	private String addendum12Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM12
	private String addendum13Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM13
	private String addendum14Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM14
	private String addendum15Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM15
	private String addendum16Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM16
	private String addendum17Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM17
	private String addendum18Str		= "";	//Ris.ExtendOrderInfo.ADDENDUM18
	private String addendum19Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM19
	private String addendum20Str 		= "";	//Ris.ExtendOrderInfo.ADDENDUM20
	private String shemaurlStr			= "";	//Ris.ExtendOrderInfo.SHEMAURL
	private String kenzoukinkyuuFlgStr	= "";	//Ris.ExtendOrderInfo.KENZOUKINKYUU_FLG
	// 2011.11.25 Add NSK_M.Ochiai Start OMH-1-2
	private String ExamTimingStr		= "";	//Ris.ExtendOrderInfo.EXAM_TIMING
	// 2011.11.25 Add NSK_M.Ochiai End
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExtendOrderInfoBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExtendOrderInfoBean]");
		strBuild.append("RIS_ID="				+	risIDStr			+ ";");
		strBuild.append("ORDER_DATE="			+	orderDateStr		+ ";");
		strBuild.append("USER_NO="				+	userNoStr			+ ";");
		strBuild.append("UPDATEDATE="			+	updatedateStr		+ ";");
		strBuild.append("UPDATETIME="			+	updatetimeStr		+ ";");
		strBuild.append("RIS_HAKKO_TERMINAL="	+	risHakkoTerminalStr	+ ";");
		strBuild.append("RIS_HAKKO_USER="		+	risHakkoUserStr		+ ";");
		strBuild.append("HIS_HAKKO_DATE="		+	hisHakkoDateStr		+ ";");
		strBuild.append("HIS_HAKKO_TERMINAL="	+	hisHakkoTerminalStr	+ ";");
		strBuild.append("HIS_HAKKO_USER="		+	hisHakkoUserStr		+ ";");
		strBuild.append("HIS_UPDATE_DATE="		+	hisUpdateDateStr	+ ";");
		strBuild.append("RI_ORDER_FLG="			+	riOrderFlgStr		+ ";");
		strBuild.append("SATUEI_PLACE="			+	satueiPlaceStr		+ ";");
		strBuild.append("YOTEIKAIKEI_FLG="		+	yoteiKaikeiFlgStr	+ ";");
		strBuild.append("ISITATIAI_FLG="		+	isitatiaiFlgStr		+ ";");
		strBuild.append("DENPYO_INSATU_FLG="	+	denpyoInsatuFlgStr	+ ";");
		strBuild.append("PORTABLE_FLG="			+	portableFlgStr		+ ";");
		strBuild.append("KANJA_SYOKAI_FLG="		+	kanjaSyokaiFlgStr	+ ";");
		strBuild.append("SIKYU_FLG="			+	sikyuFlgStr			+ ";");
		strBuild.append("SEISAN_DATE="			+	seisanDateStr		+ ";");
		strBuild.append("SEISAN_FLG="			+	seisanFlgStr		+ ";");
		strBuild.append("SEISAN_KBN_DATE="		+	seisanKbnDateStr	+ ";");
		strBuild.append("DOUISHO_FLG="			+	douishoFlgStr		+ ";");
		strBuild.append("ADDENDUM01="			+	addendum01Str		+ ";");
		strBuild.append("ADDENDUM02="			+	addendum02Str		+ ";");
		strBuild.append("ADDENDUM03="			+	addendum03Str		+ ";");
		strBuild.append("ADDENDUM04="			+	addendum04Str		+ ";");
		strBuild.append("ADDENDUM05="			+	addendum05Str		+ ";");
		strBuild.append("ADDENDUM06="			+	addendum06Str		+ ";");
		strBuild.append("ADDENDUM07="			+	addendum07Str		+ ";");
		strBuild.append("ADDENDUM08="			+	addendum08Str		+ ";");
		strBuild.append("ADDENDUM09="			+	addendum09Str 		+ ";");
		strBuild.append("ADDENDUM10="			+	addendum10Str 		+ ";");
		strBuild.append("ADDENDUM11="			+	addendum11Str 		+ ";");
		strBuild.append("ADDENDUM12="			+	addendum12Str 		+ ";");
		strBuild.append("ADDENDUM13="			+	addendum13Str 		+ ";");
		strBuild.append("ADDENDUM14="			+	addendum14Str 		+ ";");
		strBuild.append("ADDENDUM15="			+	addendum15Str 		+ ";");
		strBuild.append("ADDENDUM16="			+	addendum16Str 		+ ";");
		strBuild.append("ADDENDUM17="			+	addendum17Str 		+ ";");
		strBuild.append("ADDENDUM18="			+	addendum18Str		+ ";");
		strBuild.append("ADDENDUM19="			+	addendum19Str 		+ ";");
		strBuild.append("ADDENDUM20="			+	addendum20Str 		+ ";");
		strBuild.append("SHEMAURL="				+	shemaurlStr			+ ";");
		strBuild.append("KENZOUKINKYUU_FLG="	+	kenzoukinkyuuFlgStr	+ ";");

		return strBuild.toString();
	}
//
	public String GetRisID()
	{
		return this.risIDStr;
	}
	public String GetOrderDate()
	{
		return orderDateStr;
	}
	public Timestamp GetOrderDateTime()
	{
		Timestamp retDate = Const.TIMESTAMP_MINVALUE;
		try
		{
			if (orderDateStr.length() > 0)
			{
				retDate = Timestamp.valueOf(orderDateStr);
			}
		}
		catch (Exception e)
		{
		}
		return retDate;
	}
	public String GetUserNo()
	{
		return userNoStr;
	}
	public String GetUpdatedate()
	{
		return updatedateStr;
	}
	public String GetUpdatetime()
	{
		return updatetimeStr;
	}
	public String GetRisHakkoTerminal()
	{
		return risHakkoTerminalStr;
	}
	public String GetRisHakkoUser()
	{
		return risHakkoUserStr;
	}
	public String GetHisHakkoDate()
	{
		return hisHakkoDateStr;
	}
	public Timestamp GetHisHakkoDateTime()
	{
		Timestamp retDate = Const.TIMESTAMP_MINVALUE;
		try
		{
			if (hisHakkoDateStr.length() > 0)
			{
				retDate = Timestamp.valueOf(hisHakkoDateStr);
			}
		}
		catch (Exception e)
		{
		}
		return retDate;
	}
	public String GetHisHakkoTerminal()
	{
		return hisHakkoTerminalStr;
	}
	public String GetHisHakkoUser()
	{
		return hisHakkoUserStr;
	}
	public String GetHisUpdateDate()
	{
		return hisUpdateDateStr;
	}
	public String GetRiOrderFlg()
	{
		return riOrderFlgStr;
	}
	public String GetYoteiKaikeiFlg()
	{
		return yoteiKaikeiFlgStr;
	}
	public String GetSatueiPlace()
	{
		return satueiPlaceStr;
	}
	public String GetIsitatiaiFlg()
	{
		return isitatiaiFlgStr;
	}
	public String GetDenpyoInsatuFlg()
	{
		return denpyoInsatuFlgStr;
	}
	public String GetPortableFlg()
	{
		return portableFlgStr;
	}
	public String GetKanjaSyokaiFlg()
	{
		return kanjaSyokaiFlgStr;
	}
	public String GetSikyuFlg()
	{
		return sikyuFlgStr;
	}
	public String GetSeisanDate()
	{
		return seisanDateStr;
	}
	public String GetSeisanFlg()
	{
		return seisanFlgStr;
	}
	public String GetSeisanKbnDate()
	{
		return seisanKbnDateStr;
	}
	public String GetDouishoFlg()
	{
		return douishoFlgStr;
	}
	public String GetAddendum01()
	{
		return addendum01Str;
	}
	public String GetAddendum02()
	{
		return addendum02Str;
	}
	public String GetAddendum03()
	{
		return addendum03Str;
	}
	public String GetAddendum04()
	{
		return addendum04Str;
	}
	public String GetAddendum05()
	{
		return addendum05Str;
	}
	public String GetAddendum06()
	{
		return addendum06Str;
	}
	public String GetAddendum07()
	{
		return addendum07Str;
	}
	public String GetAddendum08()
	{
		return addendum08Str;
	}
	public String GetAddendum09()
	{
		return addendum09Str;
	}
	public String GetAddendum10()
	{
		return addendum10Str;
	}
	public String GetAddendum11()
	{
		return addendum11Str;
	}
	public String GetAddendum12()
	{
		return addendum12Str;
	}
	public String GetAddendum13()
	{
		return addendum13Str;
	}
	public String GetAddendum14()
	{
		return addendum14Str;
	}
	public String GetAddendum15()
	{
		return addendum15Str;
	}
	public String GetAddendum16()
	{
		return addendum16Str;
	}
	public String GetAddendum17()
	{
		return addendum17Str;
	}
	public String GetAddendum18()
	{
		return addendum18Str;
	}
	public String GetAddendum19()
	{
		return addendum19Str;
	}
	public String GetAddendum20()
	{
		return addendum20Str;
	}
	public String GetShemaurl()
	{
		return shemaurlStr;
	}
	public String GetKenzoukinkyuuFlg()
	{
		return kenzoukinkyuuFlgStr;
	}
	// 2011.11.25 Add NSK_M.Ochiai Start OMH-1-2
	public String GetExamTiming()
	{
		return ExamTimingStr;
	}
	// 2011.11.25 Add NSK_M.Ochiai End
//
	public void SetRisID(String risID)
	{
		if (risID != null)
		{
			this.risIDStr = risID;
		}
	}
	public void SetOrderDate(String orderDate)
	{
		if (orderDate != null)
		{
			this.orderDateStr = orderDate;
		}
	}
	public void SetUserNo(String userNo)
	{
		if (userNo != null)
		{
			this.userNoStr = userNo;
		}
	}
	public void SetUpdatedate(String updatedate)
	{
		if (updatedate != null)
		{
			this.updatedateStr = updatedate;
		}
	}
	public void SetUpdatetime(String updatetime)
	{
		if (updatetime != null)
		{
			this.updatetimeStr = updatetime;
		}
	}
	public void SetRisHakkoTerminal(String risHakkoTerminal)
	{
		if (risHakkoTerminal != null)
		{
			this.risHakkoTerminalStr = risHakkoTerminal;
		}
	}
	public void SetRisHakkoUser(String risHakkoUser)
	{
		if (risHakkoUser != null)
		{
			this.risHakkoUserStr = risHakkoUser;
		}
	}
	public void SetHisHakkoDate(String hisHakkoDate)
	{
		if (hisHakkoDate != null)
		{
			this.hisHakkoDateStr = hisHakkoDate;
		}
	}
	public void SetHisHakkoTerminal(String hisHakkoTerminal)
	{
		if (hisHakkoTerminal != null)
		{
			this.hisHakkoTerminalStr = hisHakkoTerminal;
		}
	}
	public void SetHisHakkoUser(String hisHakkoUser)
	{
		if (hisHakkoUser != null)
		{
			this.hisHakkoUserStr = hisHakkoUser;
		}
	}
	public void SetHisUpdateDate(String hisUpdateDate)
	{
		if (hisUpdateDate != null)
		{
			this.hisUpdateDateStr = hisUpdateDate;
		}
	}
	public void SetRiOrderFlg(String riOrderFlg)
	{
		if (riOrderFlg != null)
		{
			this.riOrderFlgStr = riOrderFlg;
		}
	}
	public void SetSatueiPlace(String satueiPlace)
	{
		if (satueiPlace != null)
		{
			this.satueiPlaceStr = satueiPlace;
		}
	}
	public void SetYoteiKaikeiFlg(String yoteiKaikeiFlg)
	{
		if (yoteiKaikeiFlg != null)
		{
			this.yoteiKaikeiFlgStr = yoteiKaikeiFlg;
		}
	}

	public void SetIsitatiaiFlg(String isitatiaiFlg)
	{
		if (isitatiaiFlg != null)
		{
			this.isitatiaiFlgStr = isitatiaiFlg;
		}
	}
	public void SetDenpyoInsatuFlg(String denpyoInsatuFlg)
	{
		if (denpyoInsatuFlg != null)
		{
			this.denpyoInsatuFlgStr = denpyoInsatuFlg;
		}
	}
	public void SetPortableFlg(String portableFlg)
	{
		if (portableFlg != null)
		{
			this.portableFlgStr = portableFlg;
		}
	}
	public void SetKanjaSyokaiFlg(String kanjaSyokaiFlg)
	{
		if (kanjaSyokaiFlg != null)
		{
			this.kanjaSyokaiFlgStr = kanjaSyokaiFlg;
		}
	}
	public void SetSikyuFlg(String sikyuFlg)
	{
		if (sikyuFlg != null)
		{
			this.sikyuFlgStr = sikyuFlg;
		}
	}
	public void SetSeisanDate(String seisanDate)
	{
		if (seisanDate != null)
		{
			this.seisanDateStr = seisanDate;
		}
	}
	public void SetSeisanFlg(String seisanFlg)
	{
		if (seisanFlg != null)
		{
			this.seisanFlgStr = seisanFlg;
		}
	}
	public void SetSeisanKbnDate(String seisanKbnDate)
	{
		if (seisanKbnDate != null)
		{
			this.seisanKbnDateStr = seisanKbnDate;
		}
	}
	public void SetDouishoFlg(String douishoFlg)
	{
		if (douishoFlg != null)
		{
			this.douishoFlgStr = douishoFlg;
		}
	}
	public void SetAddendum01(String addendum01)
	{
		if (addendum01 != null)
		{
			this.addendum01Str = addendum01;
		}
	}
	public void SetAddendum02(String addendum02)
	{
		if (addendum02 != null)
		{
			this.addendum02Str = addendum02;
		}
	}
	public void SetAddendum03(String addendum03)
	{
		if (addendum03 != null)
		{
			this.addendum03Str = addendum03;
		}
	}
	public void SetAddendum04(String addendum04)
	{
		if (addendum04 != null)
		{
			this.addendum04Str = addendum04;
		}
	}
	public void SetAddendum05(String addendum05)
	{
		if (addendum05 != null)
		{
			this.addendum05Str = addendum05;
		}
	}
	public void SetAddendum06(String addendum06)
	{
		if (addendum06 != null)
		{
			this.addendum06Str = addendum06;
		}
	}
	public void SetAddendum07(String addendum07)
	{
		if (addendum07 != null)
		{
			this.addendum07Str = addendum07;
		}
	}
	public void SetAddendum08(String addendum08)
	{
		if (addendum08 != null)
		{
			this.addendum08Str = addendum08;
		}
	}
	public void SetAddendum09(String addendum09)
	{
		if (addendum09 != null)
		{
			this.addendum09Str = addendum09;
		}
	}
	public void SetAddendum10(String addendum10)
	{
		if (addendum10 != null)
		{
			this.addendum10Str = addendum10;
		}
	}
	public void SetAddendum11(String addendum11)
	{
		if (addendum11 != null)
		{
			this.addendum11Str = addendum11;
		}
	}
	public void SetAddendum12(String addendum12)
	{
		if (addendum12 != null)
		{
			this.addendum12Str = addendum12;
		}
	}
	public void SetAddendum13(String addendum13)
	{
		if (addendum13 != null)
		{
			this.addendum13Str = addendum13;
		}
	}
	public void SetAddendum14(String addendum14)
	{
		if (addendum14 != null)
		{
			this.addendum14Str = addendum14;
		}
	}
	public void SetAddendum15(String addendum15)
	{
		if (addendum15 != null)
		{
			this.addendum15Str = addendum15;
		}
	}
	public void SetAddendum16(String addendum16)
	{
		if (addendum16 != null)
		{
			this.addendum16Str = addendum16;
		}
	}
	public void SetAddendum17(String addendum17)
	{
		if (addendum17 != null)
		{
			this.addendum17Str = addendum17;
		}
	}
	public void SetAddendum18(String addendum18)
	{
		if (addendum18 != null)
		{
			this.addendum18Str = addendum18;
		}
	}
	public void SetAddendum19(String addendum19)
	{
		if (addendum19 != null)
		{
			this.addendum19Str = addendum19;
		}
	}
	public void SetAddendum20(String addendum20)
	{
		if (addendum20 != null)
		{
			this.addendum20Str = addendum20;
		}
	}
	public void SetShemaurl(String shemaurl)
	{
		if (shemaurl != null)
		{
			this.shemaurlStr = shemaurl;
		}
	}
	public void SetKenzoukinkyuuFlg(String kenzoukinkyuuFlg)
	{
		if (kenzoukinkyuuFlg != null)
		{
			this.kenzoukinkyuuFlgStr = kenzoukinkyuuFlg;
		}
	}
	// 2011.11.25 Add NSK_M.Ochiai Start OMH-1-2
	public void SetExamTiming(String examTiming)
	{
		if (examTiming != null)
		{
			this.ExamTimingStr = examTiming;
		}
	}
	// 2011.11.25 Add NSK_M.Ochiai End
}
