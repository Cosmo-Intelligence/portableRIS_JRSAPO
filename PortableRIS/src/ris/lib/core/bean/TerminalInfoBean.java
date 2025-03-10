package ris.lib.core.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import ris.portable.common.Const;

/// <summary>
///
/// 端末情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.25	: 112478 / A.Kobayashi	: original
/// V2.04.001	: 2010.11.16	: 999999 / T.Nishikubo	: KANRO-R-3
/// V2.04.00	: 2011.02.16	: 999999 / T.Nishikubo	: KANRO-R-27
/// V2.06.010	: 2011.05.19	: 999999 / T.Ootsuka	: KG-2-X02
/// V2.01.00    extends 2011.07.27    extends 999999 / NSK_T.Koudate: NML-CAT2-004
///
/// </summary>
public class TerminalInfoBean
{
	//TerminalInfo
	private String terminalIDStr			= "";
	private String terminalNameStr			= "";
	private String terminalAddressStr		= "";
	private Timestamp entryDateDT			= Const.TIMESTAMP_MINVALUE;
	private String explanationStr			= "";
	private String showOrderStr				= "";
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	private String kakuhoRoomStr			= "";
	// 2010.11.16 Add T.Nishikubo End

	//TerminalDefine
	private String autoSearchPeriodStr		= "";
	private String appButton00Str 			= "";
	private String appButton01Str 			= "";
	private String appButton02Str 			= "";
	private String appButton03Str 			= "";
	private String appButton04Str 			= "";
	private String appButton05Str 			= "";
	private String appButton06Str 			= "";
	private String appButton07Str 			= "";
	private String appButton10Str 			= "";
	private String appButton11Str 			= "";
	private String appButton12Str 			= "";
	private String appButton13Str 			= "";
	private String appButton14Str 			= "";
	private String appButton15Str 			= "";
	private String appButton16Str 			= "";
	private String appButton17Str 			= "";
	private String shemaLinkStr				= "";
	private String shemaProgramStr			= "";
	private String shemaModuleStr			= "";
	private String comportNoStr				= "";
	private String comportTypeStr			= "";
	private String reportFormPathStr 		= "";
	private String reportOutput01Str 		= "";
	private String reportOutput02Str 		= "";
	private String reportOutput03Str 		= "";
	private String reportOutput04Str 		= "";
	private String reportOutput05Str 		= "";
	private String reportOutput06Str 		= "";
	private String reportOutput07Str 		= "";
	private String reportOutput08Str 		= "";
	private String readerInfoStr			= "";
	private String comport01Str 			= "";
	private String comport02Str 			= "";
	private String comport03Str 			= "";
	private String defaultExamRoomID01Str	= "";
	private String defaultKensaKikiID01Str	= "";
	private String defaultExamRoomID02Str	= "";
	private String defaultKensaKikiID02Str	= "";
	private String defaultKensaKikiID03Str	= "";
	private String tachpanelFlagStr			= "";
	private String outputType01Str 			= "";
	private String outputType02Str 			= "";
	private String outputType03Str 			= "";
	private String outputType04Str 			= "";
	private String outputType05Str 			= "";
	private String outputType06Str 			= "";
	private String outputType07Str 			= "";
	private String outputType08Str 			= "";
	private String imageFilePathStr			= "";
	private String kenzouinfoStr			= "";
	// 2010.06.23 Add T.Nishikubo Start
	private String reportOutput09Str 		= "";
	private String reportOutput10Str 		= "";
	private String reportOutput11Str 		= "";
	private String reportOutput12Str 		= "";
	private String reportOutput13Str 		= "";
	private String reportOutput14Str 		= "";
	private String reportOutput15Str 		= "";
	private String reportOutput16Str 		= "";
	private String reportOutput17Str 		= "";
	private String reportOutput18Str 		= "";
	private String reportOutput19Str 		= "";
	private String reportOutput20Str 		= "";
	private String outputType09Str			= "";
	private String outputType10Str			= "";
	private String outputType11Str			= "";
	private String outputType12Str			= "";
	private String outputType13Str			= "";
	private String outputType14Str			= "";
	private String outputType15Str			= "";
	private String outputType16Str			= "";
	private String outputType17Str			= "";
	private String outputType18Str			= "";
	private String outputType19Str			= "";
	private String outputType20Str			= "";
	// 2010.06.23 Add T.Nishikubo End
	// 2010.07.30 Add DD.Chinh Start
	//デフォルトプリンタ出力
	private String defaultPrinter01Str 		= "";
	private String defaultPrinter02Str 		= "";
	private String defaultPrinter03Str 		= "";
	private String defaultPrinter04Str 		= "";
	private String defaultPrinter05Str 		= "";
	private String defaultPrinter06Str 		= "";
	private String defaultPrinter07Str 		= "";
	private String defaultPrinter08Str 		= "";
	private String defaultPrinter09Str 		= "";
	private String defaultPrinter10Str 		= "";
	private String defaultPrinter11Str 		= "";
	private String defaultPrinter12Str 		= "";
	private String defaultPrinter13Str 		= "";
	private String defaultPrinter14Str 		= "";
	private String defaultPrinter15Str 		= "";
	private String defaultPrinter16Str 		= "";
	private String defaultPrinter17Str 		= "";
	private String defaultPrinter18Str 		= "";
	private String defaultPrinter19Str 		= "";
	private String defaultPrinter20Str 		= "";
	// 2010.07.30 Add DD.Chinh End
	private String defaultFormFileNameStr	= "";

	// 2011.05.19 Add T.Ootsuka Start KG-2-X02
	private String defaultSearchCriteriaStr = "";
	// 2011.05.19 Add T.Ootsuka End

	// 2011.07.27 Add NSK_T.Koudate Start NML-CAT2-004
	private String defaultTeamSectionIDStr = "";
	// 2011.07.27 Add NSK_T.Koudate End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public TerminalInfoBean()
	{
		//
	}

	// terminalIDStrのGET
	public String GetTerminalID()
	{
		return this.terminalIDStr;
	}
	// terminalIDStrのSET
	public void SetTerminalID(String terminalID)
	{
		if (terminalID != null)
		{
			this.terminalIDStr = terminalID;
		}
	}

	// terminaNameDStrのGET
	public String GetTerminalName()
	{
		return this.terminalNameStr;
	}
	// terminalNameStrのSET
	public void SetTerminalName(String terminalName)
	{
		if (terminalName != null)
		{
			this.terminalNameStr = terminalName;
		}
	}

	// terminaAddressDStrのGET
	public String GetTerminalAddress()
	{
		return this.terminalAddressStr;
	}
	// terminalAddressStrのSET
	public void SetTerminalAddress(String terminalAddress)
	{
		if (terminalAddress != null)
		{
			this.terminalAddressStr = terminalAddress;
		}
	}

	// entryDateDTのGET
	public Timestamp GetEntryDate()
	{
		return this.entryDateDT;
	}
	// hisUpdateTimestampのSET
	public void SetEntryDate(Timestamp entryDate)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(entryDate) )
		{
			this.entryDateDT = entryDate;
		}
	}

	// explanationStrのGET
	public String GetExplanation()
	{
		return this.explanationStr;
	}
	// terminalAddressStrのSET
	public void SetExplanation(String explanation)
	{
		if (explanation != null)
		{
			this.explanationStr = explanation;
		}
	}

	// showOrderStrのGET
	public String GetShowOrder()
	{
		return this.showOrderStr;
	}
	// showOrderStrのSET
	public void SetShowOrder(String showOrder)
	{
		if (showOrder != null)
		{
			this.showOrderStr = showOrder;
		}
	}

	// 2010.11.16 Add T.Nishikubo Start KANRO-R-3
	// kakuhoRoomStrのGET
	public String GetKakuhoRoom()
	{
		return this.kakuhoRoomStr;
	}
	// kakuhoRoomStrのSET
	public void SetKakuhoRoom(String kakuhoRoomStr)
	{
		if (kakuhoRoomStr != null)
		{
			this.kakuhoRoomStr = kakuhoRoomStr;
		}
	}
	// 2010.11.16 Add T.Nishikubo End

	// autoSearchPeriodStrのGET
	public int GetAutoSearchPeriodInt()
	{
		int autoSearchPeriodInt = 0;

		if ("".equals(this.autoSearchPeriodStr))
		{
			return autoSearchPeriodInt;
		}
		else
		{
			try
			{
				autoSearchPeriodInt = Integer.parseInt(autoSearchPeriodStr);
			}
			catch (Exception e)
			{
			}

			return autoSearchPeriodInt;
		}
	}
	// autoSearchPeriodStrのGET
	public String GetAutoSearchPeriod()
	{
		return this.autoSearchPeriodStr;
	}
	// autoSearchPeriodStrのSET
	public void SetAutoSearchPeriod(String autoSearchPeriod)
	{
		if (autoSearchPeriod != null)
		{
			this.autoSearchPeriodStr = autoSearchPeriod;
		}
	}

	// appButton00StrのGET
	public String GetAppButton00()
	{
		return this.appButton00Str;
	}
	// appButton00StrのSET
	public void SetAppButton00(String appButton00)
	{
		if (appButton00 != null)
		{
			this.appButton00Str = appButton00;
		}
	}

	// appButton01StrのGET
	public String GetAppButton01()
	{
		return this.appButton01Str;
	}
	// appButton01StrのSET
	public void SetAppButton01(String appButton01)
	{
		if (appButton01 != null)
		{
			this.appButton01Str = appButton01;
		}
	}

	// appButton02StrのGET
	public String GetAppButton02()
	{
		return this.appButton02Str;
	}
	// appButton02StrのSET
	public void SetAppButton02(String appButton02)
	{
		if (appButton02 != null)
		{
			this.appButton02Str = appButton02;
		}
	}

	// appButton03StrのGET
	public String GetAppButton03()
	{
		return this.appButton03Str;
	}
	// appButton03StrのSET
	public void SetAppButton03(String appButton03)
	{
		if (appButton03 != null)
		{
			this.appButton03Str = appButton03;
		}
	}

	// appButton04StrのGET
	public String GetAppButton04()
	{
		return this.appButton04Str;
	}
	// appButton04StrのSET
	public void SetAppButton04(String appButton04)
	{
		if (appButton04 != null)
		{
			this.appButton04Str = appButton04;
		}
	}

	// appButton05StrのGET
	public String GetAppButton05()
	{
		return this.appButton05Str;
	}
	// appButton05StrのSET
	public void SetAppButton05(String appButton05)
	{
		if (appButton05 != null)
		{
			this.appButton05Str = appButton05;
		}
	}

	// appButton06StrのGET
	public String GetAppButton06()
	{
		return this.appButton06Str;
	}
	// appButton06StrのSET
	public void SetAppButton06(String appButton06)
	{
		if (appButton06 != null)
		{
			this.appButton06Str = appButton06;
		}
	}

	// appButton07StrのGET
	public String GetAppButton07()
	{
		return this.appButton07Str;
	}
	// appButton07StrのSET
	public void SetAppButton07(String appButton07)
	{
		if (appButton07 != null)
		{
			this.appButton07Str = appButton07;
		}
	}

	// appButton10StrのGET
	public String GetAppButton10()
	{
		return this.appButton10Str;
	}
	// appButton10StrのSET
	public void SetAppButton10(String appButton10)
	{
		if (appButton10 != null)
		{
			this.appButton10Str = appButton10;
		}
	}

	// appButton11StrのGET
	public String GetAppButton11()
	{
		return this.appButton11Str;
	}
	// appButton11StrのSET
	public void SetAppButton11(String appButton11)
	{
		if (appButton11 != null)
		{
			this.appButton11Str = appButton11;
		}
	}

	// appButton12StrのGET
	public String GetAppButton12()
	{
		return this.appButton12Str;
	}
	// appButton12StrのSET
	public void SetAppButton12(String appButton12)
	{
		if (appButton12 != null)
		{
			this.appButton12Str = appButton12;
		}
	}

	// appButton13StrのGET
	public String GetAppButton13()
	{
		return this.appButton13Str;
	}
	// appButton13StrのSET
	public void SetAppButton13(String appButton13)
	{
		if (appButton13 != null)
		{
			this.appButton13Str = appButton13;
		}
	}

	// appButton14StrのGET
	public String GetAppButton14()
	{
		return this.appButton14Str;
	}
	// appButton14StrのSET
	public void SetAppButton14(String appButton14)
	{
		if (appButton14 != null)
		{
			this.appButton14Str = appButton14;
		}
	}

	// appButton15StrのGET
	public String GetAppButton15()
	{
		return this.appButton15Str;
	}
	// appButton15StrのSET
	public void SetAppButton15(String appButton15)
	{
		if (appButton15 != null)
		{
			this.appButton15Str = appButton15;
		}
	}

	// appButton16StrのGET
	public String GetAppButton16()
	{
		return this.appButton16Str;
	}
	// appButton16StrのSET
	public void SetAppButton16(String appButton16)
	{
		if (appButton16 != null)
		{
			this.appButton16Str = appButton16;
		}
	}

	// appButton17StrのGET
	public String GetAppButton17()
	{
		return this.appButton17Str;
	}
	// appButton17StrのSET
	public void SetAppButton17(String appButton17)
	{
		if (appButton17 != null)
		{
			this.appButton17Str = appButton17;
		}
	}

	/// <summary>
	/// メニュー1用IDリストのGET
	/// </summary>
	/// <returns></returns>
	public ArrayList GetMenu1IDList()
	{
		ArrayList retList = new ArrayList();

		if (this.appButton00Str.length() > 0)
		{
			retList.add(this.appButton00Str);
		}
		if (this.appButton01Str.length() > 0)
		{
			retList.add(this.appButton01Str);
		}
		if (this.appButton02Str.length() > 0)
		{
			retList.add(this.appButton02Str);
		}
		if (this.appButton03Str.length() > 0)
		{
			retList.add(this.appButton03Str);
		}
		if (this.appButton04Str.length() > 0)
		{
			retList.add(this.appButton04Str);
		}
		if (this.appButton05Str.length() > 0)
		{
			retList.add(this.appButton05Str);
		}
		if (this.appButton06Str.length() > 0)
		{
			retList.add(this.appButton06Str);
		}
		if (this.appButton07Str.length() > 0)
		{
			retList.add(this.appButton07Str);
		}
		return retList;
	}

	/// <summary>
	/// メニュー2用IDリストのGET
	/// </summary>
	/// <returns></returns>
	public ArrayList GetMenu2IDList()
	{
		ArrayList retList = new ArrayList();

		if (this.appButton10Str.length() > 0)
		{
			retList.add(this.appButton10Str);
		}
		if (this.appButton11Str.length() > 0)
		{
			retList.add(this.appButton11Str);
		}
		if (this.appButton12Str.length() > 0)
		{
			retList.add(this.appButton12Str);
		}
		if (this.appButton13Str.length() > 0)
		{
			retList.add(this.appButton13Str);
		}
		if (this.appButton14Str.length() > 0)
		{
			retList.add(this.appButton14Str);
		}
		if (this.appButton15Str.length() > 0)
		{
			retList.add(this.appButton15Str);
		}
		if (this.appButton16Str.length() > 0)
		{
			retList.add(this.appButton16Str);
		}
		if (this.appButton17Str.length() > 0)
		{
			retList.add(this.appButton17Str);
		}
		return retList;
	}

	// shemaLinkStrのGET
	public String GetShemaLink()
	{
		return this.shemaLinkStr;
	}
	// shemaLinkStrのSET
	public void SetShemaLink(String shemaLink)
	{
		if (shemaLink != null)
		{
			this.shemaLinkStr = shemaLink;
		}
	}

	// shemaProgramStrのGET
	public String GetShemaProgram()
	{
		return this.shemaProgramStr;
	}
	// shemaProgramStrのSET
	public void SetShemaProgram(String shemaProgram)
	{
		if (shemaProgram != null)
		{
			this.shemaProgramStr = shemaProgram;
		}
	}

	// shemaModuleStrのGET
	public String GetShemaModule()
	{
		return this.shemaModuleStr;
	}
	// shemaModuleStrのSET
	public void SetShemaModule(String shemaModule)
	{
		if (shemaModule != null)
		{
			this.shemaModuleStr = shemaModule;
		}
	}

	// comportNoStrのGET
	public String GetComportNo()
	{
		return this.comportNoStr;
	}
	// comportNoStrのSET
	public void SetComportNo(String comportNo)
	{
		if (comportNo != null)
		{
			this.comportNoStr = comportNo;
		}
	}

	// comportTypeStrのGET
	public String GetComportType()
	{
		return this.comportTypeStr;
	}
	// comportTypeStrのSET
	public void SetComportType(String comportType)
	{
		if (comportType != null)
		{
			this.comportTypeStr = comportType;
		}
	}

	// reportFormPathStrのGET
	public String GetReportFormPath()
	{
		return this.reportFormPathStr;
	}
	// reportFormPathStrのSET
	public void SetReportFormPath(String reportFormPath)
	{
		if (reportFormPath != null)
		{
			this.reportFormPathStr = reportFormPath;
		}
	}

	// reportOutput01StrのGET
	public String GetReportOutput01()
	{
		return this.reportOutput01Str;
	}
	// reportOutput01StrのSET
	public void SetReportOutput01(String reportOutput01)
	{
		if (reportOutput01 != null)
		{
			this.reportOutput01Str = reportOutput01;
		}
	}

	// reportOutput02StrのGET
	public String GetReportOutput02()
	{
		return this.reportOutput02Str;
	}
	// reportOutput02StrのSET
	public void SetReportOutput02(String reportOutput02)
	{
		if (reportOutput02 != null)
		{
			this.reportOutput02Str = reportOutput02;
		}
	}

	// reportOutput03StrのGET
	public String GetReportOutput03()
	{
		return this.reportOutput03Str;
	}
	// reportOutput03StrのSET
	public void SetReportOutput03(String reportOutput03)
	{
		if (reportOutput03 != null)
		{
			this.reportOutput03Str = reportOutput03;
		}
	}

	// reportOutput04StrのGET
	public String GetReportOutput04()
	{
		return this.reportOutput04Str;
	}
	// reportOutput04StrのSET
	public void SetReportOutput04(String reportOutput04)
	{
		if (reportOutput04 != null)
		{
			this.reportOutput04Str = reportOutput04;
		}
	}

	// reportOutput05StrのGET
	public String GetReportOutput05()
	{
		return this.reportOutput05Str;
	}
	// reportOutput05StrのSET
	public void SetReportOutput05(String reportOutput05)
	{
		if (reportOutput05 != null)
		{
			this.reportOutput05Str = reportOutput05;
		}
	}

	// reportOutput06StrのGET
	public String GetReportOutput06()
	{
		return this.reportOutput06Str;
	}
	// reportOutput06StrのSET
	public void SetReportOutput06(String reportOutput06)
	{
		if (reportOutput06 != null)
		{
			this.reportOutput06Str = reportOutput06;
		}
	}

	// reportOutput07StrのGET
	public String GetReportOutput07()
	{
		return this.reportOutput07Str;
	}
	// reportOutput07StrのSET
	public void SetReportOutput07(String reportOutput07)
	{
		if (reportOutput07 != null)
		{
			this.reportOutput07Str = reportOutput07;
		}
	}

	// reportOutput08StrのGET
	public String GetReportOutput08()
	{
		return this.reportOutput08Str;
	}
	// reportOutput08StrのSET
	public void SetReportOutput08(String reportOutput08)
	{
		if (reportOutput08 != null)
		{
			this.reportOutput08Str = reportOutput08;
		}
	}

	// readerInfoStrのGET
	public String GetReaderInfo()
	{
		return this.readerInfoStr;
	}
	// readerInfoStrのSET
	public void SetReaderInfo(String readerInfo)
	{
		if (readerInfo != null)
		{
			this.readerInfoStr = readerInfo;
		}
	}

	// comport01StrのGET
	public String GetComport01()
	{
		return this.comport01Str;
	}
	// comport01StrのSET
	public void SetComport01(String comport01)
	{
		if (comport01 != null)
		{
			this.comport01Str = comport01;
		}
	}

	// comport02StrのGET
	public String GetComport02()
	{
		return this.comport02Str;
	}
	// comport02StrのSET
	public void SetComport02(String comport02)
	{
		if (comport02 != null)
		{
			this.comport02Str = comport02;
		}
	}

	// comport03StrのGET
	public String GetComport03()
	{
		return this.comport03Str;
	}
	// comport03StrのSET
	public void SetComport03(String comport03)
	{
		if (comport03 != null)
		{
			this.comport03Str = comport03;
		}
	}

	// defaultExamRoomID01StrのGET
	public String GetDefaultExamRoomID01()
	{
		return this.defaultExamRoomID01Str;
	}
	// defaultExamRoomID01StrのSET
	public void SetDefaultExamRoomID01(String defaultExamRoomID01)
	{
		if (defaultExamRoomID01 != null)
		{
			this.defaultExamRoomID01Str = defaultExamRoomID01;
		}
	}

	// defaultKensaKikiID01StrのGET
	public String GetDefaultKensaKikiID01()
	{
		return this.defaultKensaKikiID01Str;
	}
	// defaultKensaKikiID01StrのSET
	public void SetDefaultKensaKikiID01(String defaultKensaKikiID01)
	{
		if (defaultKensaKikiID01 != null)
		{
			this.defaultKensaKikiID01Str = defaultKensaKikiID01;
		}
	}

	// defaultExamRoomID02StrのGET
	public String GetDefaultExamRoomID02()
	{
		return this.defaultExamRoomID02Str;
	}
	// defaultExamRoomID02StrのSET
	public void SetDefaultExamRoomID02(String defaultExamRoomID02)
	{
		if (defaultExamRoomID02 != null)
		{
			this.defaultExamRoomID02Str = defaultExamRoomID02;
		}
	}

	// defaultKensaKikiID02StrのGET
	public String GetDefaultKensaKikiID02()
	{
		return this.defaultKensaKikiID02Str;
	}
	// defaultKensaKikiID02StrのSET
	public void SetDefaultKensaKikiID02(String defaultKensaKikiID02)
	{
		if (defaultKensaKikiID02 != null)
		{
			this.defaultKensaKikiID02Str = defaultKensaKikiID02;
		}
	}

	// defaultKensaKikiID03StrのGET
	public String GetDefaultKensaKikiID03()
	{
		return this.defaultKensaKikiID03Str;
	}
	// defaultKensaKikiID03StrのSET
	public void SetDefaultKensaKikiID03(String defaultKensaKikiID03)
	{
		if (defaultKensaKikiID03 != null)
		{
			this.defaultKensaKikiID03Str = defaultKensaKikiID03;
		}
	}

	// tachpanelFlagStrのGET
	public String GetTachpanelFlag()
	{
		return this.tachpanelFlagStr;
	}
	// tachpanelFlagStrのSET
	public void SetTachpanelFlag(String tachpanelFlag)
	{
		if (tachpanelFlag != null)
		{
			this.tachpanelFlagStr = tachpanelFlag;
		}
	}

	// outputType01StrのGET
	public String GetOutputType01()
	{
		return this.outputType01Str;
	}
	// outputType01StrのSET
	public void SetOutputType01(String outputType01)
	{
		if (outputType01 != null)
		{
			this.outputType01Str = outputType01;
		}
	}

	// outputType02StrのGET
	public String GetOutputType02()
	{
		return this.outputType02Str;
	}
	// outputType02StrのSET
	public void SetOutputType02(String outputType02)
	{
		if (outputType02 != null)
		{
			this.outputType02Str = outputType02;
		}
	}

	// outputType03StrのGET
	public String GetOutputType03()
	{
		return this.outputType03Str;
	}
	// outputType03StrのSET
	public void SetOutputType03(String outputType03)
	{
		if (outputType03 != null)
		{
			this.outputType03Str = outputType03;
		}
	}

	// outputType04StrのGET
	public String GetOutputType04()
	{
		return this.outputType04Str;
	}
	// outputType04StrのSET
	public void SetOutputType04(String outputType04)
	{
		if (outputType04 != null)
		{
			this.outputType04Str = outputType04;
		}
	}

	// outputType05StrのGET
	public String GetOutputType05()
	{
		return this.outputType05Str;
	}
	// outputType05StrのSET
	public void SetOutputType05(String outputType05)
	{
		if (outputType05 != null)
		{
			this.outputType05Str = outputType05;
		}
	}

	// outputType06StrのGET
	public String GetOutputType06()
	{
		return this.outputType06Str;
	}
	// outputType06StrのSET
	public void SetOutputType06(String outputType06)
	{
		if (outputType06 != null)
		{
			this.outputType06Str = outputType06;
		}
	}

	// outputType07StrのGET
	public String GetOutputType07()
	{
		return this.outputType07Str;
	}
	// outputType07StrのSET
	public void SetOutputType07(String outputType07)
	{
		if (outputType07 != null)
		{
			this.outputType07Str = outputType07;
		}
	}

	// outputType08StrのGET
	public String GetOutputType08()
	{
		return this.outputType08Str;
	}
	// outputType08StrのSET
	public void SetOutputType08(String outputType08)
	{
		if (outputType08 != null)
		{
			this.outputType08Str = outputType08;
		}
	}

	// imageFilePathStrのGET
	public String GetImageFilePath()
	{
		return this.imageFilePathStr;
	}
	// imageFilePathStrのSET
	public void SetImageFilePath(String imageFilePath)
	{
		if (imageFilePath != null)
		{
			this.imageFilePathStr = imageFilePath;
		}
	}

	// kenzouinfoStrのGET
	public String GetKenzouinfo()
	{
		return this.kenzouinfoStr;
	}
	// kenzouinfoStrのSET
	public void SetKenzouinfo(String kenzouinfo)
	{
		if (kenzouinfo != null)
		{
			this.kenzouinfoStr = kenzouinfo;
		}
	}

	// defaultFormFileNameStrのGET
	public String GetDefaultFormFileName()
	{
		return this.defaultFormFileNameStr;
	}
	// defaultFormFileNameStrのSET
	public void SetDefaultFormFileName(String defaultFormFileName)
	{
		if (defaultFormFileName != null)
		{
			this.defaultFormFileNameStr = defaultFormFileName;
		}
	}

	// defaultFormFilePathNameのGET
	public String GetDefaultFormFilePath()
	{
		return reportFormPathStr;
	}

	// 2010.06.23 Add T.Nishikubo Start
	// reportOutput09StrのGET
	public String GetReportOutput09()
	{
		return this.reportOutput09Str;
	}
	// reportOutput09StrのSET
	public void SetReportOutput09(String reportOutput09)
	{
		if (reportOutput09 != null)
		{
			this.reportOutput09Str = reportOutput09;
		}
	}
	// reportOutput10StrのGET
	public String GetReportOutput10()
	{
		return this.reportOutput10Str;
	}
	// reportOutput10StrのSET
	public void SetReportOutput10(String reportOutput10)
	{
		if (reportOutput10 != null)
		{
			this.reportOutput10Str = reportOutput10;
		}
	}
	// reportOutput11StrのGET
	public String GetReportOutput11()
	{
		return this.reportOutput11Str;
	}
	// reportOutput11StrのSET
	public void SetReportOutput11(String reportOutput11)
	{
		if (reportOutput11 != null)
		{
			this.reportOutput11Str = reportOutput11;
		}
	}
	// reportOutput12StrのGET
	public String GetReportOutput12()
	{
		return this.reportOutput12Str;
	}
	// reportOutput12StrのSET
	public void SetReportOutput12(String reportOutput12)
	{
		if (reportOutput12 != null)
		{
			this.reportOutput12Str = reportOutput12;
		}
	}
	// reportOutput13StrのGET
	public String GetReportOutput13()
	{
		return this.reportOutput13Str;
	}
	// reportOutput13StrのSET
	public void SetReportOutput13(String reportOutput13)
	{
		if (reportOutput13 != null)
		{
			this.reportOutput13Str = reportOutput13;
		}
	}
	// reportOutput14StrのGET
	public String GetReportOutput14()
	{
		return this.reportOutput14Str;
	}
	// reportOutput14StrのSET
	public void SetReportOutput14(String reportOutput14)
	{
		if (reportOutput14 != null)
		{
			this.reportOutput14Str = reportOutput14;
		}
	}
	// reportOutput15StrのGET
	public String GetReportOutput15()
	{
		return this.reportOutput15Str;
	}
	// reportOutput15StrのSET
	public void SetReportOutput15(String reportOutput15)
	{
		if (reportOutput15 != null)
		{
			this.reportOutput15Str = reportOutput15;
		}
	}
	// reportOutput16StrのGET
	public String GetReportOutput16()
	{
		return this.reportOutput16Str;
	}
	// reportOutput16StrのSET
	public void SetReportOutput16(String reportOutput16)
	{
		if (reportOutput16 != null)
		{
			this.reportOutput16Str = reportOutput16;
		}
	}
	// reportOutput17StrのGET
	public String GetReportOutput17()
	{
		return this.reportOutput17Str;
	}
	// reportOutput17StrのSET
	public void SetReportOutput17(String reportOutput17)
	{
		if (reportOutput17 != null)
		{
			this.reportOutput17Str = reportOutput17;
		}
	}
	// reportOutput18StrのGET
	public String GetReportOutput18()
	{
		return this.reportOutput18Str;
	}
	// reportOutput18StrのSET
	public void SetReportOutput18(String reportOutput18)
	{
		if (reportOutput18 != null)
		{
			this.reportOutput18Str = reportOutput18;
		}
	}
	// reportOutput19StrのGET
	public String GetReportOutput19()
	{
		return this.reportOutput19Str;
	}
	// reportOutput19StrのSET
	public void SetReportOutput19(String reportOutput19)
	{
		if (reportOutput19 != null)
		{
			this.reportOutput19Str = reportOutput19;
		}
	}
	// reportOutput20StrのGET
	public String GetReportOutput20()
	{
		return this.reportOutput20Str;
	}
	// reportOutput20StrのSET
	public void SetReportOutput20(String reportOutput20)
	{
		if (reportOutput20 != null)
		{
			this.reportOutput20Str = reportOutput20;
		}
	}

	// outputType09StrのGET
	public String GetOutputType09()
	{
		return this.outputType09Str;
	}
	// outputType09StrのSET
	public void SetOutputType09(String outputType09)
	{
		if (outputType09 != null)
		{
			this.outputType09Str = outputType09;
		}
	}
	// outputType10StrのGET
	public String GetOutputType10()
	{
		return this.outputType10Str;
	}
	// outputType10StrのSET
	public void SetOutputType10(String outputType10)
	{
		if (outputType10 != null)
		{
			this.outputType10Str = outputType10;
		}
	}
	// outputType11StrのGET
	public String GetOutputType11()
	{
		return this.outputType11Str;
	}
	// outputType11StrのSET
	public void SetOutputType11(String outputType11)
	{
		if (outputType11 != null)
		{
			this.outputType11Str = outputType11;
		}
	}
	// outputType12StrのGET
	public String GetOutputType12()
	{
		return this.outputType12Str;
	}
	// outputType12StrのSET
	public void SetOutputType12(String outputType12)
	{
		if (outputType12 != null)
		{
			this.outputType12Str = outputType12;
		}
	}
	// outputType13StrのGET
	public String GetOutputType13()
	{
		return this.outputType13Str;
	}
	// outputType13StrのSET
	public void SetOutputType13(String outputType13)
	{
		if (outputType13 != null)
		{
			this.outputType13Str = outputType13;
		}
	}
	// outputType14StrのGET
	public String GetOutputType14()
	{
		return this.outputType14Str;
	}
	// outputType14StrのSET
	public void SetOutputType14(String outputType14)
	{
		if (outputType14 != null)
		{
			this.outputType14Str = outputType14;
		}
	}
	// outputType15StrのGET
	public String GetOutputType15()
	{
		return this.outputType15Str;
	}
	// outputType15StrのSET
	public void SetOutputType15(String outputType15)
	{
		if (outputType15 != null)
		{
			this.outputType15Str = outputType15;
		}
	}
	// outputType16StrのGET
	public String GetOutputType16()
	{
		return this.outputType16Str;
	}
	// outputType16StrのSET
	public void SetOutputType16(String outputType16)
	{
		if (outputType16 != null)
		{
			this.outputType16Str = outputType16;
		}
	}
	// outputType17StrのGET
	public String GetOutputType17()
	{
		return this.outputType17Str;
	}
	// outputType17StrのSET
	public void SetOutputType17(String outputType17)
	{
		if (outputType17 != null)
		{
			this.outputType17Str = outputType17;
		}
	}
	// outputType18StrのGET
	public String GetOutputType18()
	{
		return this.outputType18Str;
	}
	// outputType18StrのSET
	public void SetOutputType18(String outputType18)
	{
		if (outputType18 != null)
		{
			this.outputType18Str = outputType18;
		}
	}
	// outputType19StrのGET
	public String GetOutputType19()
	{
		return this.outputType19Str;
	}
	// outputType19StrのSET
	public void SetOutputType19(String outputType19)
	{
		if (outputType19 != null)
		{
			this.outputType19Str = outputType19;
		}
	}
	// outputType20StrのGET
	public String GetOutputType20()
	{
		return this.outputType20Str;
	}
	// outputType20StrのSET
	public void SetOutputType20(String outputType20)
	{
		if (outputType20 != null)
		{
			this.outputType20Str = outputType20;
		}
	}
	// 2010.06.23 Add T.Nishikubo End

	// 2010.07.30 Add DD.Chinh Start
	// defaultPrinter01StrのGET
	public String GetDefaultPrinter01()
	{
		return this.defaultPrinter01Str;
	}
	// defaultPrinter01StrのSET
	public void SetDefaultPrinter01(String defaultPrinter01)
	{
		if (defaultPrinter01 != null)
		{
			this.defaultPrinter01Str = defaultPrinter01;
		}
	}
	// defaultPrinter02StrのGET
	public String GetDefaultPrinter02()
	{
		return this.defaultPrinter02Str;
	}
	// defaultPrinter02StrのSET
	public void SetDefaultPrinter02(String defaultPrinter02)
	{
		if (defaultPrinter02 != null)
		{
			this.defaultPrinter02Str = defaultPrinter02;
		}
	}
	// defaultPrinter03StrのGET
	public String GetDefaultPrinter03()
	{
		return this.defaultPrinter03Str;
	}
	// defaultPrinter03StrのSET
	public void SetDefaultPrinter03(String defaultPrinter03)
	{
		if (defaultPrinter03 != null)
		{
			this.defaultPrinter03Str = defaultPrinter03;
		}
	}
	// defaultPrinter04StrのGET
	public String GetDefaultPrinter04()
	{
		return this.defaultPrinter04Str;
	}
	// defaultPrinter04StrのSET
	public void SetDefaultPrinter04(String defaultPrinter04)
	{
		if (defaultPrinter04 != null)
		{
			this.defaultPrinter04Str = defaultPrinter04;
		}
	}
	// defaultPrinter05StrのGET
	public String GetDefaultPrinter05()
	{
		return this.defaultPrinter05Str;
	}
	// defaultPrinter05StrのSET
	public void SetDefaultPrinter05(String defaultPrinter05)
	{
		if (defaultPrinter05 != null)
		{
			this.defaultPrinter05Str = defaultPrinter05;
		}
	}
	// defaultPrinter06StrのGET
	public String GetDefaultPrinter06()
	{
		return this.defaultPrinter06Str;
	}
	// defaultPrinter06StrのSET
	public void SetDefaultPrinter06(String defaultPrinter06)
	{
		if (defaultPrinter06 != null)
		{
			this.defaultPrinter06Str = defaultPrinter06;
		}
	}
	// defaultPrinter07StrのGET
	public String GetDefaultPrinter07()
	{
		return this.defaultPrinter07Str;
	}
	// defaultPrinter07StrのSET
	public void SetDefaultPrinter07(String defaultPrinter07)
	{
		if (defaultPrinter07 != null)
		{
			this.defaultPrinter07Str = defaultPrinter07;
		}
	}
	// defaultPrinter08StrのGET
	public String GetDefaultPrinter08()
	{
		return this.defaultPrinter08Str;
	}
	// defaultPrinter08StrのSET
	public void SetDefaultPrinter08(String defaultPrinter08)
	{
		if (defaultPrinter08 != null)
		{
			this.defaultPrinter08Str = defaultPrinter08;
		}
	}
	// defaultPrinter09StrのGET
	public String GetDefaultPrinter09()
	{
		return this.defaultPrinter09Str;
	}
	// defaultPrinter09StrのSET
	public void SetDefaultPrinter09(String defaultPrinter09)
	{
		if (defaultPrinter09 != null)
		{
			this.defaultPrinter09Str = defaultPrinter09;
		}
	}
	// defaultPrinter10StrのGET
	public String GetDefaultPrinter10()
	{
		return this.defaultPrinter10Str;
	}
	// defaultPrinter10StrのSET
	public void SetDefaultPrinter10(String defaultPrinter10)
	{
		if (defaultPrinter10 != null)
		{
			this.defaultPrinter10Str = defaultPrinter10;
		}
	}
	// defaultPrinter11StrのGET
	public String GetDefaultPrinter11()
	{
		return this.defaultPrinter11Str;
	}
	// defaultPrinter11StrのSET
	public void SetDefaultPrinter11(String defaultPrinter11)
	{
		if (defaultPrinter11 != null)
		{
			this.defaultPrinter11Str = defaultPrinter11;
		}
	}
	// defaultPrinter12StrのGET
	public String GetDefaultPrinter12()
	{
		return this.defaultPrinter12Str;
	}
	// defaultPrinter12StrのSET
	public void SetDefaultPrinter12(String defaultPrinter12)
	{
		if (defaultPrinter12 != null)
		{
			this.defaultPrinter12Str = defaultPrinter12;
		}
	}
	// defaultPrinter13StrのGET
	public String GetDefaultPrinter13()
	{
		return this.defaultPrinter13Str;
	}
	// defaultPrinter13StrのSET
	public void SetDefaultPrinter13(String defaultPrinter13)
	{
		if (defaultPrinter13 != null)
		{
			this.defaultPrinter13Str = defaultPrinter13;
		}
	}
	// defaultPrinter14StrのGET
	public String GetDefaultPrinter14()
	{
		return this.defaultPrinter14Str;
	}
	// defaultPrinter14StrのSET
	public void SetDefaultPrinter14(String defaultPrinter14)
	{
		if (defaultPrinter14 != null)
		{
			this.defaultPrinter14Str = defaultPrinter14;
		}
	}
	// defaultPrinter15StrのGET
	public String GetDefaultPrinter15()
	{
		return this.defaultPrinter15Str;
	}
	// defaultPrinter15StrのSET
	public void SetDefaultPrinter15(String defaultPrinter15)
	{
		if (defaultPrinter15 != null)
		{
			this.defaultPrinter15Str = defaultPrinter15;
		}
	}
	// defaultPrinter16StrのGET
	public String GetDefaultPrinter16()
	{
		return this.defaultPrinter16Str;
	}
	// defaultPrinter16StrのSET
	public void SetDefaultPrinter16(String defaultPrinter16)
	{
		if (defaultPrinter16 != null)
		{
			this.defaultPrinter16Str = defaultPrinter16;
		}
	}
	// defaultPrinter17StrのGET
	public String GetDefaultPrinter17()
	{
		return this.defaultPrinter17Str;
	}
	// defaultPrinter17StrのSET
	public void SetDefaultPrinter17(String defaultPrinter17)
	{
		if (defaultPrinter17 != null)
		{
			this.defaultPrinter17Str = defaultPrinter17;
		}
	}
	// defaultPrinter18StrのGET
	public String GetDefaultPrinter18()
	{
		return this.defaultPrinter18Str;
	}
	// defaultPrinter18StrのSET
	public void SetDefaultPrinter18(String defaultPrinter18)
	{
		if (defaultPrinter18 != null)
		{
			this.defaultPrinter18Str = defaultPrinter18;
		}
	}
	// defaultPrinter19StrのGET
	public String GetDefaultPrinter19()
	{
		return this.defaultPrinter19Str;
	}
	// defaultPrinter19StrのSET
	public void SetDefaultPrinter19(String defaultPrinter19)
	{
		if (defaultPrinter19 != null)
		{
			this.defaultPrinter19Str = defaultPrinter19;
		}
	}
	// defaultPrinter20StrのGET
	public String GetDefaultPrinter20()
	{
		return this.defaultPrinter20Str;
	}
	// defaultPrinter20StrのSET
	public void SetDefaultPrinter20(String defaultPrinter20)
	{
		if (defaultPrinter20 != null)
		{
			this.defaultPrinter20Str = defaultPrinter20;
		}
	}
	// 2010.07.30 Add DD.Chinh End

	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	/// <summary>
	/// 指定した機能ボタンが設定されているか否かを返す
	/// </summary>
	/// <param name="appButton">対象ボタンID</param>
	/// <returns>true:設定/false:未設定</returns>
	public boolean IsAppButtonExist(String appButton)
	{
		boolean retBool = false;
		if (appButton00Str.equals(appButton))
			retBool = true;
		if (appButton01Str.equals(appButton))
			retBool = true;
		if (appButton02Str.equals(appButton))
			retBool = true;
		if (appButton03Str.equals(appButton))
			retBool = true;
		if (appButton04Str.equals(appButton))
			retBool = true;
		if (appButton05Str.equals(appButton))
			retBool = true;
		if (appButton06Str.equals(appButton))
			retBool = true;
		if (appButton07Str.equals(appButton))
			retBool = true;
		if (appButton10Str.equals(appButton))
			retBool = true;
		if (appButton11Str.equals(appButton))
			retBool = true;
		if (appButton12Str.equals(appButton))
			retBool = true;
		if (appButton13Str.equals(appButton))
			retBool = true;
		if (appButton14Str.equals(appButton))
			retBool = true;
		if (appButton15Str.equals(appButton))
			retBool = true;
		if (appButton16Str.equals(appButton))
			retBool = true;
		if (appButton17Str.equals(appButton))
			retBool = true;
		//
		return retBool;
	}
	// 2011.02.16 Add T.Nishikubo End

	// 2011.05.19 Add T.Ootsuka Start KG-2-X02
	// defaultSearchCriteriaStrのGET
	public String GetDefaultSearchCriteria()
	{
		return this.defaultSearchCriteriaStr;
	}
	// defaultSearchCriteriaStrのSET
	public void SetDefaultSearchCriteria(String defaultSearchCriteria)
	{
		if (defaultSearchCriteria != null)
		{
			this.defaultSearchCriteriaStr = defaultSearchCriteria;
		}
	}
	// 2011.05.19 Add T.Ootsuka End

	// 2011.07.27 Add NSK_T.Koudate Start NML-CAT2-004
	// defaultTeamSectionIDStrのGET
	public String GetDefaultTeamSectionID()
	{
		return this.defaultTeamSectionIDStr;
	}
	// defaultTeamSectionIDStrのSET
	public void SetDefaultTeamSectionID(String defaultTeamSectionID)
	{
		if (defaultTeamSectionID != null)
		{
			this.defaultTeamSectionIDStr = defaultTeamSectionID;
		}
	}
	// 2011.07.27 Add NSK_T.Koudate End
}
