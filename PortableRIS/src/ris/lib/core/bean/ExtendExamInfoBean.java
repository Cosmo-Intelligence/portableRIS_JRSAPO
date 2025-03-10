package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.lib.core.util.CommonString;
import ris.portable.common.Const;

/// <summary>
/// ExtendExamInfoBean の概要の説明です。
/// </summary>
public class ExtendExamInfoBean
{
	// private members
	private String		risIDStr			=  "";					//Ris.ExtendExamInfo.RIS_ID
	private String		douishoFlgStr		= "0";					//Ris.ExtendExamInfo.DOUISHO_FLG
	private String		dejitaizuFlgStr		= "0";					//Ris.ExtendExamInfo.DEJITAIZU_FLG
	private String		dokueiFlgStr		= "0";					//Ris.ExtendExamInfo.DOKUEI_FLG
	private String		jissekiKaikeiFlgStr	= "0";					//Ris.ExtendExamInfo.JISSEKIKAIKEI_FLG
	private String		filmAutoFlgStr		= "0";					//Ris.ExtendExamInfo.FILMAUTO_FLG
	private String		setPortableFlgStr	=  "";					//Ris.ExtendExamInfo.SETPORTABLE_FLG
	private String		kenzouTantouIDStr	=  "";					//Ris.ExtendExamInfo.KENZOU_TANTOU_ID
	private String		kenzouTantouNameStr	=  "";					//Ris.ExtendExamInfo.KENZOU_TANTOU_NAME
	private String		kenzouKinkyuuFlgStr	= "0";					//Ris.ExtendExamInfo.KENZOUKINKYUU_FLG
	private String		kenzouStatusStr		= "0";					//Ris.ExtendExamInfo.KENZOU_STATUS_FLG
	private Timestamp	kenzouTimestamp		= Const.TIMESTAMP_MINVALUE;	//Ris.ExtendExamInfo.KENZOU_DATE
	private String		examData01Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA01
	private String		examData02Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA02
	private String		examData03Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA03
	private String		examData04Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA04
	private String		examData05Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA05
	private String		examData06Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA06
	private String		examData07Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA07
	private String		examData08Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA08
	private String		examData09Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA09
	private String		examData10Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA10
	private String		examData11Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA11
	private String		examData12Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA12
	private String		examData13Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA13
	private String		examData14Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA14
	private String		examData15Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA15
	private String		examData16Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA16
	private String		examData17Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA17
	private String		examData18Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA18
	private String		examData19Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA19
	private String		examData20Str		=  "";					//Ris.ExtendExamInfo.EXAMDATA20

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExtendExamInfoBean()
	{
		//
	}
//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExtendExamInfoBean]");
		strBuild.append("RIS_ID="				+ risIDStr				+ ";");
		strBuild.append("DOUISHO_FLG="			+ douishoFlgStr			+ ";");
		strBuild.append("DEJITAIZU_FLG="		+ dejitaizuFlgStr		+ ";");
		strBuild.append("DOKUEI_FLG="			+ dokueiFlgStr			+ ";");
		strBuild.append("JISSEKIKAIKEI_FLG="	+ jissekiKaikeiFlgStr	+ ";");
		strBuild.append("FILMAUTO_FLG="			+ filmAutoFlgStr		+ ";");
		strBuild.append("SETPORTABLE_FLG="		+ setPortableFlgStr		+ ";");
		strBuild.append("KENZOU_TANTOU_ID="		+ kenzouTantouIDStr		+ ";");
		strBuild.append("KENZOU_TANTOU_NAME="	+ kenzouTantouNameStr	+ ";");
		strBuild.append("KENZOUKINKYUU_FLG="	+ kenzouKinkyuuFlgStr	+ ";");
		strBuild.append("KENZOU_STATUS_FLG="	+ kenzouStatusStr		+ ";");
		strBuild.append("KENZOU_DATE="			+ kenzouTimestamp		+ ";");
		strBuild.append("EXAMDATA01="			+ examData01Str			+ ";");
		strBuild.append("EXAMDATA02="			+ examData02Str			+ ";");
		strBuild.append("EXAMDATA03="			+ examData03Str			+ ";");
		strBuild.append("EXAMDATA04="			+ examData04Str			+ ";");
		strBuild.append("EXAMDATA05="			+ examData05Str			+ ";");
		strBuild.append("EXAMDATA06="			+ examData06Str			+ ";");
		strBuild.append("EXAMDATA07="			+ examData07Str			+ ";");
		strBuild.append("EXAMDATA08="			+ examData08Str			+ ";");
		strBuild.append("EXAMDATA09="			+ examData09Str			+ ";");
		strBuild.append("EXAMDATA10="			+ examData10Str			+ ";");
		strBuild.append("EXAMDATA11="			+ examData11Str			+ ";");
		strBuild.append("EXAMDATA12="			+ examData12Str			+ ";");
		strBuild.append("EXAMDATA13="			+ examData13Str			+ ";");
		strBuild.append("EXAMDATA14="			+ examData14Str			+ ";");
		strBuild.append("EXAMDATA15="			+ examData15Str			+ ";");
		strBuild.append("EXAMDATA16="			+ examData16Str			+ ";");
		strBuild.append("EXAMDATA17="			+ examData17Str			+ ";");
		strBuild.append("EXAMDATA18="			+ examData18Str			+ ";");
		strBuild.append("EXAMDATA19="			+ examData19Str			+ ";");
		strBuild.append("EXAMDATA20="			+ examData20Str			+ ";");

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
	// douishoFlgStrのSET
	public void SetDouishoFlg( String douishoFlg )
	{
		if( douishoFlg != null )
		{
			this.douishoFlgStr = douishoFlg;
		}
	}
	// douishoFlgStrのGET
	public String GetDouishoFlg()
	{
		return this.douishoFlgStr;
	}
//
	// dejitaizuFlgStrのSET
	public void SetDejitaizuFlg( String dejitaizuFlg )
	{
		if( dejitaizuFlg != null )
		{
			this.dejitaizuFlgStr = dejitaizuFlg;
		}
	}
	// dejitaizuFlgStrのGET
	public String GetDejitaizuFlg()
	{
		return this.dejitaizuFlgStr;
	}
//
	// dokueiFlgStrのSET
	public void SetDokueiFlg( String dokueiFlg )
	{
		if( dokueiFlg != null )
		{
			this.dokueiFlgStr = dokueiFlg;
		}
	}
	// dokueiFlgStrのGET
	public String GetDokueiFlg()
	{
		return this.dokueiFlgStr;
	}
//
	// jissekiKaikeiFlgStrのSET
	public void SetJissekiKaikeiFlg( String jissekiKaikeiFlg )
	{
		if( jissekiKaikeiFlg != null )
		{
			this.jissekiKaikeiFlgStr = jissekiKaikeiFlg;
		}
	}
	// jissekiKaikeiFlgStrのGET
	public String GetJissekiKaikeiFlg()
	{
		return this.jissekiKaikeiFlgStr;
	}
//
	// filmAutoFlgStrのSET
	public void SetFilmAutoFlg( String filmAutoFlg )
	{
		if( filmAutoFlg != null )
		{
			this.filmAutoFlgStr = filmAutoFlg;
		}
	}
	// filmAutoFlgStrのGET
	public String GetFilmAutoFlg()
	{
		return this.filmAutoFlgStr;
	}
//
	// setPortableFlgStrのSET
	public void SetSetPortableFlg( String setPortableFlg )
	{
		if( setPortableFlg != null )
		{
			this.setPortableFlgStr = setPortableFlg;
		}
	}
	// setPortableFlgStrのGET
	public String GetSetPortableFlg()
	{
		return this.setPortableFlgStr;
	}
//
	// kenzouTantouIDStrのSET
	public void SetKenzouTantouID( String kenzouTantouID )
	{
		if( kenzouTantouID != null )
		{
			this.kenzouTantouIDStr = kenzouTantouID;
		}
	}
	// kenzouTantouIDStrのGET
	public String GetKenzouTantouID()
	{
		return this.kenzouTantouIDStr;
	}
//
	// kenzouTantouNameStrのSET
	public void SetKenzouTantouName( String kenzouTantouName )
	{
		if( kenzouTantouName != null )
		{
			this.kenzouTantouNameStr = kenzouTantouName;
		}
	}
	// kenzouTantouNameStrのGET
	public String GetKenzouTantouName()
	{
		return this.kenzouTantouNameStr;
	}
//
	// kenzouKinkyuuFlgStrのSET
	public void SetKenzouKinkyuuFlg( String kenzouKinkyuuFlg )
	{
		if( kenzouKinkyuuFlg != null )
		{
			this.kenzouKinkyuuFlgStr = kenzouKinkyuuFlg;
		}
	}
	// kenzouKinkyuuFlgStrのGET
	public String GetKenzouKinkyuuFlg()
	{
		return this.kenzouKinkyuuFlgStr;
	}
//
	// kenzouStatusStrのSET
	public void SetKenzouStatus(String kenzouStatus)
	{
		if (kenzouStatus != null)
		{
			this.kenzouStatusStr = kenzouStatus;
		}
	}
	// kenzouStatusStrのGET
	public String GetKenzouStatus()
	{
		return this.kenzouStatusStr;
	}
//
	// kenzouTimestampのSET
	public void SetKenzouDate(Timestamp kenzouDate)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(kenzouDate) )
		{
			this.kenzouTimestamp = kenzouDate;
		}
	}
	// kenzouTimestampのGET
	public Timestamp GetKenzouDate()
	{
		return this.kenzouTimestamp;
	}

	// examDataのGET
	public boolean IsExamDataEnabled()
	{
		String checkExamDataStr = "";
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_01.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_02.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_03.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_04.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_05.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_06.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_07.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_08.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_09.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_10.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_11.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_12.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_13.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_14.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_15.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_16.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_17.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_18.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_19.toString());
		checkExamDataStr += GetExamDataValue(CommonString.EXAMDATA_ITEM_ID_20.toString());
		if (checkExamDataStr.length() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
//
	// examData01StrのGET
	public String GetExamDataValue(String itemIDStr)
	{
		String retStr = "";
		if (CommonString.EXAMDATA_ITEM_ID_01.toString().equals(itemIDStr))
		{
			retStr = this.examData01Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_02.toString().equals(itemIDStr))
		{
			retStr = this.examData02Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_03.toString().equals(itemIDStr))
		{
			retStr = this.examData03Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_04.toString().equals(itemIDStr))
		{
			retStr = this.examData04Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_05.toString().equals(itemIDStr))
		{
			retStr = this.examData05Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_06.toString().equals(itemIDStr))
		{
			retStr = this.examData06Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_07.toString().equals(itemIDStr))
		{
			retStr = this.examData07Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_08.toString().equals(itemIDStr))
		{
			retStr = this.examData08Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_09.toString().equals(itemIDStr))
		{
			retStr = this.examData09Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_10.toString().equals(itemIDStr))
		{
			retStr = this.examData10Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_11.toString().equals(itemIDStr))
		{
			retStr = this.examData11Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_12.toString().equals(itemIDStr))
		{
			retStr = this.examData12Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_13.toString().equals(itemIDStr))
		{
			retStr = this.examData13Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_14.toString().equals(itemIDStr))
		{
			retStr = this.examData14Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_15.toString().equals(itemIDStr))
		{
			retStr = this.examData15Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_16.toString().equals(itemIDStr))
		{
			retStr = this.examData16Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_17.toString().equals(itemIDStr))
		{
			retStr = this.examData17Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_18.toString().equals(itemIDStr))
		{
			retStr = this.examData18Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_19.toString().equals(itemIDStr))
		{
			retStr = this.examData19Str;
		}
		else if (CommonString.EXAMDATA_ITEM_ID_20.toString().equals(itemIDStr))
		{
			retStr = this.examData20Str;
		}
		return retStr;
	}
//
	// examData01StrのSET
	public void SetExamData01(String examData01)
	{
		if (examData01 != null)
		{
			this.examData01Str = examData01;
		}
	}
	// examData01StrのGET
	public String GetExamData01()
	{
		return this.examData01Str;
	}
//
	// examData02StrのSET
	public void SetExamData02(String examData02)
	{
		if (examData02 != null)
		{
			this.examData02Str = examData02;
		}
	}
	// examData02StrのGET
	public String GetExamData02()
	{
		return this.examData02Str;
	}
//
	// examData03StrのSET
	public void SetExamData03(String examData03)
	{
		if (examData03 != null)
		{
			this.examData03Str = examData03;
		}
	}
	// examData03StrのGET
	public String GetExamData03()
	{
		return this.examData03Str;
	}
//
	// examData04StrのSET
	public void SetExamData04(String examData04)
	{
		if (examData04 != null)
		{
			this.examData04Str = examData04;
		}
	}
	// examData04StrのGET
	public String GetExamData04()
	{
		return this.examData04Str;
	}
//
	// examData05StrのSET
	public void SetExamData05(String examData05)
	{
		if (examData05 != null)
		{
			this.examData05Str = examData05;
		}
	}
	// examData05StrのGET
	public String GetExamData05()
	{
		return this.examData05Str;
	}
//
	// examData06StrのSET
	public void SetExamData06(String examData06)
	{
		if (examData06 != null)
		{
			this.examData06Str = examData06;
		}
	}
	// examData06StrのGET
	public String GetExamData06()
	{
		return this.examData06Str;
	}
//
	// examData07StrのSET
	public void SetExamData07(String examData07)
	{
		if (examData07 != null)
		{
			this.examData07Str = examData07;
		}
	}
	// examData07StrのGET
	public String GetExamData07()
	{
		return this.examData07Str;
	}
//
	// examData08StrのSET
	public void SetExamData08(String examData08)
	{
		if (examData08 != null)
		{
			this.examData08Str = examData08;
		}
	}
	// examData08StrのGET
	public String GetExamData08()
	{
		return this.examData08Str;
	}
//
	// examData09StrのSET
	public void SetExamData09(String examData09)
	{
		if (examData09 != null)
		{
			this.examData09Str = examData09;
		}
	}
	// examData09StrのGET
	public String GetExamData09()
	{
		return this.examData09Str;
	}
//
	// examData10StrのSET
	public void SetExamData10(String examData10)
	{
		if (examData10 != null)
		{
			this.examData10Str = examData10;
		}
	}
	// examData10StrのGET
	public String GetExamData10()
	{
		return this.examData10Str;
	}
//
	//
	// examData11StrのSET
	public void SetExamData11(String examData11)
	{
		if (examData11 != null)
		{
			this.examData11Str = examData11;
		}
	}
	// examData11StrのGET
	public String GetExamData11()
	{
		return this.examData11Str;
	}
	//
	// examData12StrのSET
	public void SetExamData12(String examData12)
	{
		if (examData12 != null)
		{
			this.examData12Str = examData12;
		}
	}
	// examData12StrのGET
	public String GetExamData12()
	{
		return this.examData12Str;
	}
	//
	// examData13StrのSET
	public void SetExamData13(String examData13)
	{
		if (examData13 != null)
		{
			this.examData13Str = examData13;
		}
	}
	// examData13StrのGET
	public String GetExamData13()
	{
		return this.examData13Str;
	}
	//
	// examData14StrのSET
	public void SetExamData14(String examData14)
	{
		if (examData14 != null)
		{
			this.examData14Str = examData14;
		}
	}
	// examData14StrのGET
	public String GetExamData14()
	{
		return this.examData14Str;
	}
	//
	// examData15StrのSET
	public void SetExamData15(String examData15)
	{
		if (examData15 != null)
		{
			this.examData15Str = examData15;
		}
	}
	// examData15StrのGET
	public String GetExamData15()
	{
		return this.examData15Str;
	}
	//
	// examData16StrのSET
	public void SetExamData16(String examData16)
	{
		if (examData16 != null)
		{
			this.examData16Str = examData16;
		}
	}
	// examData16StrのGET
	public String GetExamData16()
	{
		return this.examData16Str;
	}
	//
	// examData17StrのSET
	public void SetExamData17(String examData17)
	{
		if (examData17 != null)
		{
			this.examData17Str = examData17;
		}
	}
	// examData17StrのGET
	public String GetExamData17()
	{
		return this.examData17Str;
	}
	//
	// examData18StrのSET
	public void SetExamData18(String examData18)
	{
		if (examData18 != null)
		{
			this.examData18Str = examData18;
		}
	}
	// examData18StrのGET
	public String GetExamData18()
	{
		return this.examData18Str;
	}
	//
	// examData19StrのSET
	public void SetExamData19(String examData19)
	{
		if (examData19 != null)
		{
			this.examData19Str = examData19;
		}
	}
	// examData19StrのGET
	public String GetExamData19()
	{
		return this.examData19Str;
	}
	//
	// examData20StrのSET
	public void SetExamData20(String examData20)
	{
		if (examData20 != null)
		{
			this.examData20Str = examData20;
		}
	}
	// examData20StrのGET
	public String GetExamData20()
	{
		return this.examData20Str;
	}
}
