package ris.lib.core.bean;

import ris.portable.common.Const;

/// <summary>
///
/// ExamDailyInfo情報クラス
///
/// Copyright (C) 2010, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00   extends 2010.00.00    extends 999999 / T.Nishikubo		: original
///
/// </summary>
public class ExamDailyInfoBean
{
	// private members
	private String	examDateStr				= "";
	private String	gisiId1Str				= "";
	private String	gisiName1Str			= "";
	private String	gisiId2Str				= "";
	private String	gisiName2Str			= "";
	private String	helpGisiId1Str			= "";
	private String	helpGisiName1Str		= "";
	private String	helpKbn1Str				= "";
	private Integer	helpTime1Int			= Const.INT_MINVALUE;
	private String	helpGisiId2Str			= "";
	private String	helpGisiName2Str		= "";
	private String	helpKbn2Str				= "";
	private Integer	helpTime2Int			= Const.INT_MINVALUE;
	private String	helpGisiId3Str			= "";
	private String	helpGisiName3Str		= "";
	private String	helpKbn3Str				= "";
	private Integer	helpTime3Int			= Const.INT_MINVALUE;
	private String	helpGisiId4Str			= "";
	private String	helpGisiName4Str		= "";
	private String	helpKbn4Str				= "";
	private Integer	helpTime4Int			= Const.INT_MINVALUE;
	private String	dailyComment1Str		= "";
	private String	dailyComment2Str		= "";
	private String	dailyComment3Str		= "";
	private String	gisiId1_2Str			= "";
	private String	gisiName1_2Str			= "";
	private String	gisiId2_2Str			= "";
	private String	gisiName2_2Str			= "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExamDailyInfoBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExamDailyInfoBean]");
		strBuild.append("EXAMDATE="				+ examDateStr				+ ";");
		strBuild.append("GISI_ID1="				+ gisiId1Str				+ ";");
		strBuild.append("GISI_NAME1="			+ gisiName1Str				+ ";");
		strBuild.append("GISI_ID2="				+ gisiId2Str				+ ";");
		strBuild.append("GISI_NAME2="			+ gisiName2Str				+ ";");
		strBuild.append("HELPGISI_ID1="			+ helpGisiId1Str			+ ";");
		strBuild.append("HELPGISI_NAME1="		+ helpGisiName1Str			+ ";");
		strBuild.append("HELPKBN_1="			+ helpKbn1Str				+ ";");
		strBuild.append("HELPTIME_1="			+ helpTime1Int.toString()	+ ";");
		strBuild.append("HELPGISI_ID2="			+ helpGisiId2Str			+ ";");
		strBuild.append("HELPGISI_NAME2="		+ helpGisiName2Str			+ ";");
		strBuild.append("HELPKBN_2="			+ helpKbn2Str				+ ";");
		strBuild.append("HELPTIME_2="			+ helpTime2Int.toString()	+ ";");
		strBuild.append("HELPGISI_ID3="			+ helpGisiId3Str			+ ";");
		strBuild.append("HELPGISI_NAME3="		+ helpGisiName3Str			+ ";");
		strBuild.append("HELPKBN_3="			+ helpKbn3Str				+ ";");
		strBuild.append("HELPTIME_3="			+ helpTime3Int.toString()	+ ";");
		strBuild.append("HELPGISI_ID4="			+ helpGisiId4Str			+ ";");
		strBuild.append("HELPGISI_NAME4="		+ helpGisiName4Str			+ ";");
		strBuild.append("HELPKBN_4="			+ helpKbn4Str				+ ";");
		strBuild.append("HELPTIME_4="			+ helpTime4Int.toString()	+ ";");
		strBuild.append("DAILYCOMMENT1="		+ dailyComment1Str			+ ";");
		strBuild.append("DAILYCOMMENT2="		+ dailyComment2Str			+ ";");
		strBuild.append("DAILYCOMMENT3="		+ dailyComment3Str			+ ";");
		strBuild.append("GISI_ID1_2="			+ gisiId1_2Str				+ ";");
		strBuild.append("GISI_NAME1_2="			+ gisiName1_2Str			+ ";");
		strBuild.append("GISI_ID2_2="			+ gisiId2_2Str				+ ";");
		strBuild.append("GISI_NAME2_2="			+ gisiName2_2Str			+ ";");

		return strBuild.toString();
	}

	// examDateStrのSET
	public void SetExamDate(String examDateStr)
	{
		if (examDateStr != null)
		{
			this.examDateStr = examDateStr;
		}
	}
	// examDateStrのGET
	public String GetExamDate()
	{
		return this.examDateStr;
	}

	// gisiId1StrのSET
	public void SetGisiId1(String gisiId1Str)
	{
		if (gisiId1Str != null)
		{
			this.gisiId1Str = gisiId1Str;
		}
	}
	// gisiId1StrのGET
	public String GetGisiId1()
	{
		return this.gisiId1Str;
	}

	// gisiName1StrのSET
	public void SetGisiName1(String gisiName1Str)
	{
		if (gisiName1Str != null)
		{
			this.gisiName1Str = gisiName1Str;
		}
	}
	// gisiName1StrのGET
	public String GetGisiName1()
	{
		return this.gisiName1Str;
	}

	// gisiId2StrのSET
	public void SetGisiId2(String gisiId2Str)
	{
		if (gisiId2Str != null)
		{
			this.gisiId2Str = gisiId2Str;
		}
	}
	// gisiId2StrのGET
	public String GetGisiId2()
	{
		return this.gisiId2Str;
	}

	// gisiName2StrのSET
	public void SetGisiName2(String gisiName2Str)
	{
		if (gisiName2Str != null)
		{
			this.gisiName2Str = gisiName2Str;
		}
	}
	// gisiName2StrのGET
	public String GetGisiName2()
	{
		return this.gisiName2Str;
	}

	// helpGisiId1StrのSET
	public void SetHelpGisiId1(String helpGisiId1Str)
	{
		if (helpGisiId1Str != null)
		{
			this.helpGisiId1Str = helpGisiId1Str;
		}
	}
	// helpGisiId1StrのGET
	public String GetHelpGisiId1()
	{
		return this.helpGisiId1Str;
	}

	// helpGisiName1StrのSET
	public void SetHelpGisiName1(String helpGisiName1Str)
	{
		if (helpGisiName1Str != null)
		{
			this.helpGisiName1Str = helpGisiName1Str;
		}
	}
	// helpGisiName1StrのGET
	public String GetHelpGisiName1()
	{
		return this.helpGisiName1Str;
	}

	// helpKbn1StrのSET
	public void SetHelpKbn1(String helpKbn1Str)
	{
		if (helpKbn1Str != null)
		{
			this.helpKbn1Str = helpKbn1Str;
		}
	}
	// helpKbn1StrのGET
	public String GetHelpKbn1()
	{
		return this.helpKbn1Str;
	}

	// helpTime1IntのSET
	public void SetHelpTime1(Integer helpTime1Int)
	{
		this.helpTime1Int = helpTime1Int;
	}
	// helpTime1IntのGET
	public Integer GetHelpTime1()
	{
		return this.helpTime1Int;
	}

	// helpGisiId2StrのSET
	public void SetHelpGisiId2(String helpGisiId2Str)
	{
		if (helpGisiId2Str != null)
		{
			this.helpGisiId2Str = helpGisiId2Str;
		}
	}
	// helpGisiId2StrのGET
	public String GetHelpGisiId2()
	{
		return this.helpGisiId2Str;
	}

	// helpGisiName2StrのSET
	public void SetHelpGisiName2(String helpGisiName2Str)
	{
		if (helpGisiName2Str != null)
		{
			this.helpGisiName2Str = helpGisiName2Str;
		}
	}
	// helpGisiName2StrのGET
	public String GetHelpGisiName2()
	{
		return this.helpGisiName2Str;
	}

	// helpKbn2StrのSET
	public void SetHelpKbn2(String helpKbn2Str)
	{
		if (helpKbn2Str != null)
		{
			this.helpKbn2Str = helpKbn2Str;
		}
	}
	// helpKbn2StrのGET
	public String GetHelpKbn2()
	{
		return this.helpKbn2Str;
	}

	// helpTime2IntのSET
	public void SetHelpTime2(Integer helpTime2Int)
	{
		this.helpTime2Int = helpTime2Int;
	}
	// helpTime2IntのGET
	public Integer GetHelpTime2()
	{
		return this.helpTime2Int;
	}

	// helpGisiId3StrのSET
	public void SetHelpGisiId3(String helpGisiId3Str)
	{
		if (helpGisiId3Str != null)
		{
			this.helpGisiId3Str = helpGisiId3Str;
		}
	}
	// helpGisiId3StrのGET
	public String GetHelpGisiId3()
	{
		return this.helpGisiId3Str;
	}

	// helpGisiName3StrのSET
	public void SetHelpGisiName3(String helpGisiName3Str)
	{
		if (helpGisiName3Str != null)
		{
			this.helpGisiName3Str = helpGisiName3Str;
		}
	}
	// helpGisiName3StrのGET
	public String GetHelpGisiName3()
	{
		return this.helpGisiName3Str;
	}

	// helpKbn3StrのSET
	public void SetHelpKbn3(String helpKbn3Str)
	{
		if (helpKbn3Str != null)
		{
			this.helpKbn3Str = helpKbn3Str;
		}
	}
	// helpKbn3StrのGET
	public String GetHelpKbn3()
	{
		return this.helpKbn3Str;
	}

	// helpTime3IntのSET
	public void SetHelpTime3(Integer helpTime3Int)
	{
		this.helpTime3Int = helpTime3Int;
	}
	// helpTime3IntのGET
	public Integer GetHelpTime3()
	{
		return this.helpTime3Int;
	}

	// helpGisiId4StrのSET
	public void SetHelpGisiId4(String helpGisiId4Str)
	{
		if (helpGisiId4Str != null)
		{
			this.helpGisiId4Str = helpGisiId4Str;
		}
	}
	// helpGisiId4StrのGET
	public String GetHelpGisiId4()
	{
		return this.helpGisiId4Str;
	}

	// helpGisiName4StrのSET
	public void SetHelpGisiName4(String helpGisiName4Str)
	{
		if (helpGisiName4Str != null)
		{
			this.helpGisiName4Str = helpGisiName4Str;
		}
	}
	// helpGisiName4StrのGET
	public String GetHelpGisiName4()
	{
		return this.helpGisiName4Str;
	}

	// helpKbn4StrのSET
	public void SetHelpKbn4(String helpKbn4Str)
	{
		if (helpKbn4Str != null)
		{
			this.helpKbn4Str = helpKbn4Str;
		}
	}
	// helpKbn4StrのGET
	public String GetHelpKbn4()
	{
		return this.helpKbn4Str;
	}

	// helpTime4IntのSET
	public void SetHelpTime4(Integer helpTime4Int)
	{
		this.helpTime4Int = helpTime4Int;
	}
	// helpTime4IntのGET
	public Integer GetHelpTime4()
	{
		return this.helpTime4Int;
	}

	// dailyComment1StrのSET
	public void SetDailyComment1(String dailyComment1Str)
	{
		if (dailyComment1Str != null)
		{
			this.dailyComment1Str = dailyComment1Str;
		}
	}
	// dailyComment1StrのGET
	public String GetDailyComment1()
	{
		return this.dailyComment1Str;
	}

	// dailyComment2StrのSET
	public void SetDailyComment2(String dailyComment2Str)
	{
		if (dailyComment2Str != null)
		{
			this.dailyComment2Str = dailyComment2Str;
		}
	}
	// dailyComment2StrのGET
	public String GetDailyComment2()
	{
		return this.dailyComment2Str;
	}

	// dailyComment3StrのSET
	public void SetDailyComment3(String dailyComment3Str)
	{
		if (dailyComment3Str != null)
		{
			this.dailyComment3Str = dailyComment3Str;
		}
	}
	// dailyComment3StrのGET
	public String GetDailyComment3()
	{
		return this.dailyComment3Str;
	}

	// gisiId1_2StrのSET
	public void SetGisiId1_2(String gisiId1_2Str)
	{
		if (gisiId1_2Str != null)
		{
			this.gisiId1_2Str = gisiId1_2Str;
		}
	}
	// gisiId1_2StrのGET
	public String GetGisiId1_2()
	{
		return this.gisiId1_2Str;
	}

	// gisiName1_2StrのSET
	public void SetGisiName1_2(String gisiName1_2Str)
	{
		if (gisiName1_2Str != null)
		{
			this.gisiName1_2Str = gisiName1_2Str;
		}
	}
	// gisiName1_2StrのGET
	public String GetGisiName1_2()
	{
		return this.gisiName1_2Str;
	}

	// gisiId2_2StrのSET
	public void SetGisiId2_2(String gisiId2_2Str)
	{
		if (gisiId2_2Str != null)
		{
			this.gisiId2_2Str = gisiId2_2Str;
		}
	}
	// gisiId2_2StrのGET
	public String GetGisiId2_2()
	{
		return this.gisiId2_2Str;
	}

	// gisiName2_2StrのSET
	public void SetGisiName2_2(String gisiName2_2Str)
	{
		if (gisiName2_2Str != null)
		{
			this.gisiName2_2Str = gisiName2_2Str;
		}
	}
	// gisiName2_2StrのGET
	public String GetGisiName2_2()
	{
		return this.gisiName2_2Str;
	}
}
