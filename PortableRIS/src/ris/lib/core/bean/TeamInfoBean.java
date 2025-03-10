package ris.lib.core.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

/// <summary>
///
/// チーム情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
/// (Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.00.00	: 2010.07.30	:     / T.Ootsuka		: original
/// V2.01.00    extends 2011.07.26    extends 999999 / NSK_T.Koudate extends NML-CAT2-004
///
/// </summary>
public class TeamInfoBean
{
	// private members
	private Integer		teamIDInt			= 0;		// Ris.TeamInfoTable.TEAM_ID
	private Integer		terminalIDInt		= 0;		// Ris.TeamInfoTable.TERMINALID
	private String	windowAppIDStr		= "0";		// Ris.TeamInfoTable.WINDOWAPPID
	// 2011.07.25 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//private String	kensaGisiID1Str		= "";		// Ris.TeamInfoTable.KENSA_GISI_ID1
	//private String	kensaGisiName1Str	= "";		// Ris.TeamInfoTable.KENSA_GISI_NAME1
	//private String	kensaGisiID2Str		= "";		// Ris.TeamInfoTable.KENSA_GISI_ID2
	//private String	kensaGisiName2Str	= "";		// Ris.TeamInfoTable.KENSA_GISI_NAME2
	//private String	kensaGisiID3Str		= "";		// Ris.TeamInfoTable.KENSA_GISI_ID3
	//private String	kensaGisiName3Str	= "";		// Ris.TeamInfoTable.KENSA_GISI_NAME3
	//private String	kensaGisiID4Str		= "";		// Ris.TeamInfoTable.KENSA_GISI_ID4
	//private String	kensaGisiName4Str	= "";		// Ris.TeamInfoTable.KENSA_GISI_NAME4
	//private String	kensaGisiID5Str		= "";		// Ris.TeamInfoTable.KENSA_GISI_ID5
	//private String	kensaGisiName5Str	= "";		// Ris.TeamInfoTable.KENSA_GISI_NAME5

	// 2011.07.25 Del NSK_T.Koudate End
	private String	enforcedocID1Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_ID1
	private String	enforcedocName1Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_NAME1
	private String	enforcedocSecID1Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_SECTION_ID1
	private String	enforcedocID2Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_ID2
	private String	enforcedocName2Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_NAME2
	private String	enforcedocSecID2Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_SECTION_ID2
	private String	enforcedocID3Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_ID3
	private String	enforcedocName3Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_NAME3
	private String	enforcedocSecID3Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_SECTION_ID3
	private String	enforcedocID4Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_ID4
	private String	enforcedocName4Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_NAME4
	private String	enforcedocSecID4Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_SECTION_ID4
	private String	enforcedocID5Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_ID5
	private String	enforcedocName5Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_NAME5
	private String	enforcedocSecID5Str	= "";		// Ris.TeamInfoTable.ENFORCEDOC_SECTION_ID5
	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//private String	kangosiID1Str		= "";		// Ris.TeamInfoTable.KANGOSI_ID1
	//private String	kangosiName1Str		= "";		// Ris.TeamInfoTable.KANGOSI_NAME1
	//private String	kangosiID2Str		= "";		// Ris.TeamInfoTable.KANGOSI_ID2
	//private String	kangosiName2Str		= "";		// Ris.TeamInfoTable.KANGOSI_NAME2
	//private String	kangosiID3Str		= "";		// Ris.TeamInfoTable.KANGOSI_ID3
	//private String	kangosiName3Str		= "";		// Ris.TeamInfoTable.KANGOSI_NAME3
	//private String	kangosiID4Str		= "";		// Ris.TeamInfoTable.KANGOSI_ID4
	//private String	kangosiName4Str		= "";		// Ris.TeamInfoTable.KANGOSI_NAME4
	//private String	kangosiID5Str		= "";		// Ris.TeamInfoTable.KANGOSI_ID5
	//private String	kangosiName5Str		= "";		// Ris.TeamInfoTable.KANGOSI_NAME5

	// 2011.07.26 Del NSK_T.Koudate End
	// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	private String	medPersonID01Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID01
	private String	medPersonName01Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME01
	private String	medPersonSyokuinKbn01Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN01
	private String	medPersonID02Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID02
	private String	medPersonName02Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME02
	private String	medPersonSyokuinKbn02Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN02
	private String	medPersonID03Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID03
	private String	medPersonName03Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME03
	private String	medPersonSyokuinKbn03Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN03
	private String	medPersonID04Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID04
	private String	medPersonName04Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME04
	private String	medPersonSyokuinKbn04Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN04
	private String	medPersonID05Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID05
	private String	medPersonName05Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME05
	private String	medPersonSyokuinKbn05Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN05
	private String	medPersonID06Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID06
	private String	medPersonName06Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME06
	private String	medPersonSyokuinKbn06Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN06
	private String	medPersonID07Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID07
	private String	medPersonName07Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME07
	private String	medPersonSyokuinKbn07Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN07
	private String	medPersonID08Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID08
	private String	medPersonName08Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME08
	private String	medPersonSyokuinKbn08Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN08
	private String	medPersonID09Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID09
	private String	medPersonName09Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME09
	private String	medPersonSyokuinKbn09Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN09
	private String	medPersonID10Str	= "";			// Ris.TeamInfoTable.MED_PERSON_ID10
	private String	medPersonName10Str	= "";			// Ris.TeamInfoTable.MED_PERSON_NAME10
	private String	medPersonSyokuinKbn10Str	= "";	// Ris.TeamInfoTable.MED_PERSON_SYOKUINKBN10

	/// <summary>
	/// 担当者リスト
	/// </summary>
	List<TeamInfoMedPersonBean> medPersonList = new ArrayList<TeamInfoMedPersonBean>();
	// 2011.07.26 Add NSK_T.Koudate End


	/// <summary>
	/// コンストラクタ
	/// </summary>
	public TeamInfoBean()
	{
		//
	}

	//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[TeamInfoBean]");
		strBuild.append("TEAM_ID="					+	teamIDInt			+	";");
		strBuild.append("TERMINALID="				+	terminalIDInt		+	";");
		strBuild.append("WINDOWAPPID="				+	windowAppIDStr		+	";");
		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//strBuild.append("KENSA_GISI_ID1="			+	kensaGisiID1Str		+	";");
		//strBuild.append("KENSA_GISI_NAME1="			+	kensaGisiName1Str	+	";");
		//strBuild.append("KENSA_GISI_ID2="			+	kensaGisiID2Str		+	";");
		//strBuild.append("KENSA_GISI_NAME2="			+	kensaGisiName2Str	+	";");
		//strBuild.append("KENSA_GISI_ID3="			+	kensaGisiID3Str		+	";");
		//strBuild.append("KENSA_GISI_NAME3="			+	kensaGisiName3Str	+	";");
		//strBuild.append("KENSA_GISI_ID4="			+	kensaGisiID4Str		+	";");
		//strBuild.append("KENSA_GISI_NAME4="			+	kensaGisiName4Str	+	";");
		//strBuild.append("KENSA_GISI_ID5="			+	kensaGisiID5Str		+	";");
		//strBuild.append("KENSA_GISI_NAME5="			+	kensaGisiName5Str	+	";");

		// 2011.07.26 Del NSK_T.Koudate End
		strBuild.append("ENFORCEDOC_ID1="			+	enforcedocID1Str	+	";");
		strBuild.append("ENFORCEDOC_NAME1="			+	enforcedocName1Str	+	";");
		strBuild.append("ENFORCEDOC_SECTION_ID1="	+	enforcedocSecID1Str	+	";");
		strBuild.append("ENFORCEDOC_ID2="			+	enforcedocID2Str	+	";");
		strBuild.append("ENFORCEDOC_NAME2="			+	enforcedocName2Str	+	";");
		strBuild.append("ENFORCEDOC_SECTION_ID2="	+	enforcedocSecID2Str	+	";");
		strBuild.append("ENFORCEDOC_ID3="			+	enforcedocID3Str	+	";");
		strBuild.append("ENFORCEDOC_NAME3="			+	enforcedocName3Str	+	";");
		strBuild.append("ENFORCEDOC_SECTION_ID3="	+	enforcedocSecID3Str	+	";");
		strBuild.append("ENFORCEDOC_ID4="			+	enforcedocID4Str	+	";");
		strBuild.append("ENFORCEDOC_NAME4="			+	enforcedocName4Str	+	";");
		strBuild.append("ENFORCEDOC_SECTION_ID4="	+	enforcedocSecID4Str	+	";");
		strBuild.append("ENFORCEDOC_ID5="			+	enforcedocID5Str	+	";");
		strBuild.append("ENFORCEDOC_NAME5="			+	enforcedocName5Str	+	";");
		strBuild.append("ENFORCEDOC_SECTION_ID5="	+	enforcedocSecID5Str	+	";");
		// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
		// コメント(担当者にまとめるため廃止)
		//strBuild.append("KANGOSI_ID1="				+	kangosiID1Str		+	";");
		//strBuild.append("KANGOSI_NAME1="			+	kangosiName1Str		+	";");
		//strBuild.append("KANGOSI_ID2="				+	kangosiID2Str		+	";");
		//strBuild.append("KANGOSI_NAME2="			+	kangosiName2Str		+	";");
		//strBuild.append("KANGOSI_ID3="				+	kangosiID3Str		+	";");
		//strBuild.append("KANGOSI_NAME3="			+	kangosiName3Str		+	";");
		//strBuild.append("KANGOSI_ID4="				+	kangosiID4Str		+	";");
		//strBuild.append("KANGOSI_NAME4="			+	kangosiName4Str		+	";");
		//strBuild.append("KANGOSI_ID5="				+	kangosiID5Str		+	";");
		//strBuild.append("KANGOSI_NAME5="			+	kangosiName5Str		+	";");

		// 2011.07.26 Del NSK_T.Koudate End

		// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
		// 担当者
		strBuild.append("MED_PERSON_ID01="	+	medPersonID01Str	+	";");
		strBuild.append("MED_PERSON_NAME01="	+	medPersonName01Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN01="	+	medPersonSyokuinKbn01Str	+	";");
		strBuild.append("MED_PERSON_ID02="	+	medPersonID02Str	+	";");
		strBuild.append("MED_PERSON_NAME02="	+	medPersonName02Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN02="	+	medPersonSyokuinKbn02Str	+	";");
		strBuild.append("MED_PERSON_ID03="	+	medPersonID03Str	+	";");
		strBuild.append("MED_PERSON_NAME03="	+	medPersonName03Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN03="	+	medPersonSyokuinKbn03Str	+	";");
		strBuild.append("MED_PERSON_ID04="	+	medPersonID04Str	+	";");
		strBuild.append("MED_PERSON_NAME04="	+	medPersonName04Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN04="	+	medPersonSyokuinKbn04Str	+	";");
		strBuild.append("MED_PERSON_ID05="	+	medPersonID05Str	+	";");
		strBuild.append("MED_PERSON_NAME05="	+	medPersonName05Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN05="	+	medPersonSyokuinKbn05Str	+	";");
		strBuild.append("MED_PERSON_ID06="	+	medPersonID06Str	+	";");
		strBuild.append("MED_PERSON_NAME06="	+	medPersonName06Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN06="	+	medPersonSyokuinKbn06Str	+	";");
		strBuild.append("MED_PERSON_ID07="	+	medPersonID07Str	+	";");
		strBuild.append("MED_PERSON_NAME07="	+	medPersonName07Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN07="	+	medPersonSyokuinKbn07Str	+	";");
		strBuild.append("MED_PERSON_ID08="	+	medPersonID08Str	+	";");
		strBuild.append("MED_PERSON_NAME08="	+	medPersonName08Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN08="	+	medPersonSyokuinKbn08Str	+	";");
		strBuild.append("MED_PERSON_ID09="	+	medPersonID09Str	+	";");
		strBuild.append("MED_PERSON_NAME09="	+	medPersonName09Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN09="	+	medPersonSyokuinKbn09Str	+	";");
		strBuild.append("MED_PERSON_ID10="	+	medPersonID10Str	+	";");
		strBuild.append("MED_PERSON_NAME10="	+	medPersonName10Str	+	";");
		strBuild.append("MED_PERSON_SYOKUINKBN10="	+	medPersonSyokuinKbn10Str	+	";");

		// 2011.07.26 Add NSK_T.Koudate End

		return strBuild.toString();
	}

	// teamIDIntのSET
	public void SetTeamID(Integer teamID)
	{
		this.teamIDInt = teamID;
	}
	// teamIDIntのGET
	public Integer GetTeamID()
	{
		return this.teamIDInt;
	}

	// teaminalIDIntのSET
	public void SetTerminalID(Integer terminalID)
	{
		this.terminalIDInt = terminalID;
	}
	// teaminalIDIntのGET
	public Integer GetTerminalID()
	{
		return this.terminalIDInt;
	}

	// windowAppIDStrのSET
	public void SetWindowAppID(String windowAppID)
	{
		if (windowAppIDStr != null)
		{
			this.windowAppIDStr = windowAppID;
		}
	}
	// windowAppIDStrのGET
	public String GetWindowAppID()
	{
		return this.windowAppIDStr;
	}

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	// kensaGisiIDStr1のSET
	//public void SetKensaGisiID1(String kensaGisiID1)
	//{
	//    if (kensaGisiID1 != null)
	//    {
	//        this.kensaGisiID1Str = kensaGisiID1;
	//    }
	//}
	//// kensaGisiIDStr1のGET
	//public String GetKensaGisiID1()
	//{
	//    return this.kensaGisiID1Str;
	//}

	//// kensaGisiNameStr1のSET
	//public void SetKensaGisiName1(String kensaGisiName1)
	//{
	//    if (kensaGisiName1 != null)
	//    {
	//        this.kensaGisiName1Str = kensaGisiName1;
	//    }
	//}
	//// kensaGisiNameStr1のGET
	//public String GetKensaGisiName1()
	//{
	//    return this.kensaGisiName1Str;
	//}

	//// kensaGisiIDStr2のSET
	//public void SetKensaGisiID2(String kensaGisiID2)
	//{
	//    if (kensaGisiID2 != null)
	//    {
	//        this.kensaGisiID2Str = kensaGisiID2;
	//    }
	//}
	//// kensaGisiIDStr2のGET
	//public String GetKensaGisiID2()
	//{
	//    return this.kensaGisiID2Str;
	//}

	//// kensaGisiNameStr2のSET
	//public void SetKensaGisiName2(String kensaGisiName2)
	//{
	//    if (kensaGisiName2 != null)
	//    {
	//        this.kensaGisiName2Str = kensaGisiName2;
	//    }
	//}
	//// kensaGisiNameStr2のGET
	//public String GetKensaGisiName2()
	//{
	//    return this.kensaGisiName2Str;
	//}

	//// kensaGisiIDStr3のSET
	//public void SetKensaGisiID3(String kensaGisiID3)
	//{
	//    if (kensaGisiID3 != null)
	//    {
	//        this.kensaGisiID3Str = kensaGisiID3;
	//    }
	//}
	//// kensaGisiIDStr3のGET
	//public String GetKensaGisiID3()
	//{
	//    return this.kensaGisiID3Str;
	//}

	//// kensaGisiNameStr3のSET
	//public void SetKensaGisiName3(String kensaGisiName3)
	//{
	//    if (kensaGisiName3 != null)
	//    {
	//        this.kensaGisiName3Str = kensaGisiName3;
	//    }
	//}
	//// kensaGisiNameStr3のGET
	//public String GetKensaGisiName3()
	//{
	//    return this.kensaGisiName3Str;
	//}

	//// kensaGisiIDStr4のSET
	//public void SetKensaGisiID4(String kensaGisiID4)
	//{
	//    if (kensaGisiID4 != null)
	//    {
	//        this.kensaGisiID4Str = kensaGisiID4;
	//    }
	//}
	//// kensaGisiIDStr4のGET
	//public String GetKensaGisiID4()
	//{
	//    return this.kensaGisiID4Str;
	//}

	//// kensaGisiNameStr4のSET
	//public void SetKensaGisiName4(String kensaGisiName4)
	//{
	//    if (kensaGisiName4 != null)
	//    {
	//        this.kensaGisiName4Str = kensaGisiName4;
	//    }
	//}
	//// kensaGisiNameStr4のGET
	//public String GetKensaGisiName4()
	//{
	//    return this.kensaGisiName4Str;
	//}

	//// kensaGisiIDStr5のSET
	//public void SetKensaGisiID5(String kensaGisiID5)
	//{
	//    if (kensaGisiID5 != null)
	//    {
	//        this.kensaGisiID5Str = kensaGisiID5;
	//    }
	//}
	//// kensaGisiIDStr5のGET
	//public String GetKensaGisiID5()
	//{
	//    return this.kensaGisiID5Str;
	//}

	//// kensaGisiNameStr5のSET
	//public void SetKensaGisiName5(String kensaGisiName5)
	//{
	//    if (kensaGisiName5 != null)
	//    {
	//        this.kensaGisiName5Str = kensaGisiName5;
	//    }
	//}
	//// kensaGisiNameStr5のGET
	//public String GetKensaGisiName5()
	//{
	//    return this.kensaGisiName5Str;
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	// enforcedocIDStr1のSET
	public void SetEnforcedocID1(String enforcedocID1)
	{
		if (enforcedocID1 != null)
		{
			this.enforcedocID1Str = enforcedocID1;
		}
	}
	// enforcedocIDStr1のGET
	public String GetEnforcedocID1()
	{
		return this.enforcedocID1Str;
	}

	// enforcedocNameStr1のSET
	public void SetEnforcedocName1(String enforcedocName1)
	{
		if (enforcedocName1 != null)
		{
			this.enforcedocName1Str = enforcedocName1;
		}
	}
	// enforcedocNameStr1のGET
	public String GetEnforcedocName1()
	{
		return this.enforcedocName1Str;
	}

	// enforcedocSecIDStr1のSET
	public void SetEnforcedocSecID1(String enforcedocSecID1)
	{
		if (enforcedocSecID1 != null)
		{
			this.enforcedocSecID1Str = enforcedocSecID1;
		}
	}
	// enforcedocSecIDStr1のGET
	public String GetEnforcedocSecID1()
	{
		return this.enforcedocSecID1Str;
	}

	// enforcedocIDStr2のSET
	public void SetEnforcedocID2(String enforcedocID2)
	{
		if (enforcedocID2 != null)
		{
			this.enforcedocID2Str = enforcedocID2;
		}
	}
	// enforcedocIDStr2のGET
	public String GetEnforcedocID2()
	{
		return this.enforcedocID2Str;
	}

	// enforcedocNameStr2のSET
	public void SetEnforcedocName2(String enforcedocName2)
	{
		if (enforcedocName2 != null)
		{
			this.enforcedocName2Str = enforcedocName2;
		}
	}
	// enforcedocNameStr2のGET
	public String GetEnforcedocName2()
	{
		return this.enforcedocName2Str;
	}

	// enforcedocSecIDStr2のSET
	public void SetEnforcedocSecID2(String enforcedocSecID2)
	{
		if (enforcedocSecID2 != null)
		{
			this.enforcedocSecID2Str = enforcedocSecID2;
		}
	}
	// enforcedocSecIDStr2のGET
	public String GetEnforcedocSecID2()
	{
		return this.enforcedocSecID2Str;
	}

	// enforcedocIDStr3のSET
	public void SetEnforcedocID3(String enforcedocID3)
	{
		if (enforcedocID3 != null)
		{
			this.enforcedocID3Str = enforcedocID3;
		}
	}
	// enforcedocIDStr3のGET
	public String GetEnforcedocID3()
	{
		return this.enforcedocID3Str;
	}

	// enforcedocNameStr3のSET
	public void SetEnforcedocName3(String enforcedocName3)
	{
		if (enforcedocName3 != null)
		{
			this.enforcedocName3Str = enforcedocName3;
		}
	}
	// enforcedocNameStr3のGET
	public String GetEnforcedocName3()
	{
		return this.enforcedocName3Str;
	}

	// enforcedocSecIDStr3のSET
	public void SetEnforcedocSecID3(String enforcedocSecID3)
	{
		if (enforcedocSecID3 != null)
		{
			this.enforcedocSecID3Str = enforcedocSecID3;
		}
	}
	// enforcedocSecIDStr3のGET
	public String GetEnforcedocSecID3()
	{
		return this.enforcedocSecID3Str;
	}

	// enforcedocIDStr4のSET
	public void SetEnforcedocID4(String enforcedocID4)
	{
		if (enforcedocID4 != null)
		{
			this.enforcedocID4Str = enforcedocID4;
		}
	}
	// enforcedocIDStr4のGET
	public String GetEnforcedocID4()
	{
		return this.enforcedocID4Str;
	}

	// enforcedocNameStr4のSET
	public void SetEnforcedocName4(String enforcedocName4)
	{
		if (enforcedocName4 != null)
		{
			this.enforcedocName4Str = enforcedocName4;
		}
	}
	// enforcedocNameStr4のGET
	public String GetEnforcedocName4()
	{
		return this.enforcedocName4Str;
	}

	// enforcedocSecIDStr4のSET
	public void SetEnforcedocSecID4(String enforcedocSecID4)
	{
		if (enforcedocSecID4 != null)
		{
			this.enforcedocSecID4Str = enforcedocSecID4;
		}
	}
	// enforcedocSecIDStr4のGET
	public String GetEnforcedocSecID4()
	{
		return this.enforcedocSecID4Str;
	}

	// enforcedocIDStr5のSET
	public void SetEnforcedocID5(String enforcedocID5)
	{
		if (enforcedocID5 != null)
		{
			this.enforcedocID5Str = enforcedocID5;
		}
	}
	// enforcedocIDStr5のGET
	public String GetEnforcedocID5()
	{
		return this.enforcedocID5Str;
	}

	// enforcedocNameStr5のSET
	public void SetEnforcedocName5(String enforcedocName5)
	{
		if (enforcedocName5 != null)
		{
			this.enforcedocName5Str = enforcedocName5;
		}
	}
	// enforcedocNameStr5のGET
	public String GetEnforcedocName5()
	{
		return this.enforcedocName5Str;
	}

	// enforcedocSecIDStr5のSET
	public void SetEnforcedocSecID5(String enforcedocSecID5)
	{
		if (enforcedocSecID5 != null)
		{
			this.enforcedocSecID5Str = enforcedocSecID5;
		}
	}
	// enforcedocSecIDStr5のGET
	public String GetEnforcedocSecID5()
	{
		return this.enforcedocSecID5Str;
	}

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(担当者にまとめるため廃止)
	//// kangosiIDStr1のSET
	//public void SetKangosiID1(String kangosiID1)
	//{
	//    if (kangosiID1 != null)
	//    {
	//        this.kangosiID1Str = kangosiID1;
	//    }
	//}
	//// kangosiIDStr1のGET
	//public String GetKangosiID1()
	//{
	//    return this.kangosiID1Str;
	//}

	//// kangosiNameStr1のSET
	//public void SetKangosiName1(String kangosiName1)
	//{
	//    if (kangosiName1 != null)
	//    {
	//        this.kangosiName1Str = kangosiName1;
	//    }
	//}
	//// kangosiNameStr1のGET
	//public String GetKangosiName1()
	//{
	//    return this.kangosiName1Str;
	//}

	//// kangosiIDStr2のSET
	//public void SetKangosiID2(String kangosiID2)
	//{
	//    if (kangosiID2 != null)
	//    {
	//        this.kangosiID2Str = kangosiID2;
	//    }
	//}
	//// kangosiIDStr2のGET
	//public String GetKangosiID2()
	//{
	//    return this.kangosiID2Str;
	//}

	//// kangosiNameStr2のSET
	//public void SetKangosiName2(String kangosiName2)
	//{
	//    if (kangosiName2 != null)
	//    {
	//        this.kangosiName2Str = kangosiName2;
	//    }
	//}
	//// kangosiNameStr2のGET
	//public String GetKangosiName2()
	//{
	//    return this.kangosiName2Str;
	//}

	//// kangosiIDStr3のSET
	//public void SetKangosiID3(String kangosiID3)
	//{
	//    if (kangosiID3 != null)
	//    {
	//        this.kangosiID3Str = kangosiID3;
	//    }
	//}
	//// kangosiIDStr3のGET
	//public String GetKangosiID3()
	//{
	//    return this.kangosiID3Str;
	//}

	//// kangosiNameStr3のSET
	//public void SetKangosiName3(String kangosiName3)
	//{
	//    if (kangosiName3 != null)
	//    {
	//        this.kangosiName3Str = kangosiName3;
	//    }
	//}
	//// kangosiNameStr3のGET
	//public String GetKangosiName3()
	//{
	//    return this.kangosiName3Str;
	//}

	//// kangosiIDStr4のSET
	//public void SetKangosiID4(String kangosiID4)
	//{
	//    if (kangosiID4 != null)
	//    {
	//        this.kangosiID4Str = kangosiID4;
	//    }
	//}
	//// kangosiIDStr4のGET
	//public String GetKangosiID4()
	//{
	//    return this.kangosiID4Str;
	//}

	//// kangosiNameStr4のSET
	//public void SetKangosiName4(String kangosiName4)
	//{
	//    if (kangosiName4 != null)
	//    {
	//        this.kangosiName4Str = kangosiName4;
	//    }
	//}
	//// kangosiNameStr4のGET
	//public String GetKangosiName4()
	//{
	//    return this.kangosiName4Str;
	//}

	//// kangosiIDStr5のSET
	//public void SetKangosiID5(String kangosiID5)
	//{
	//    if (kangosiID5 != null)
	//    {
	//        this.kangosiID5Str = kangosiID5;
	//    }
	//}
	//// kangosiIDStr5のGET
	//public String GetKangosiID5()
	//{
	//    return this.kangosiID5Str;
	//}

	//// kangosiNameStr5のSET
	//public void SetKangosiName5(String kangosiName5)
	//{
	//    if (kangosiName5 != null)
	//    {
	//        this.kangosiName5Str = kangosiName5;
	//    }
	//}
	//// kangosiNameStr5のGET
	//public String GetKangosiName5()
	//{
	//    return this.kangosiName5Str;
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	// 2011.07.26 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	// medPersonID01StrのSET
	public void SetMedPersonID01(String medPersonID01)
	{
		if (medPersonID01 != null)
		{
			this.medPersonID01Str = medPersonID01;
		}
	}
	// medPersonID01のGET
	public String GetMedPersonID01()
	{
		return this.medPersonID01Str;
	}
	// medPersonName01StrのSET
	public void SetMedPersonName01(String medPersonName01)
	{
		if (medPersonName01 != null)
		{
			this.medPersonName01Str = medPersonName01;
		}
	}
	// medPersonName01のGET
	public String GetMedPersonName01()
	{
		return this.medPersonName01Str;
	}
	// medPersonSyokuinKbn01StrのSET
	public void SetMedPersonSyokuinKbn01(String medPersonSyokuinKbn01)
	{
		if (medPersonSyokuinKbn01 != null)
		{
			this.medPersonSyokuinKbn01Str = medPersonSyokuinKbn01;
		}
	}
	// medPersonSyokuinKbn01のGET
	public String GetMedPersonSyokuinKbn01()
	{
		return this.medPersonSyokuinKbn01Str;
	}
	// medPersonID02StrのSET
	public void SetMedPersonID02(String medPersonID02)
	{
		if (medPersonID02 != null)
		{
			this.medPersonID02Str = medPersonID02;
		}
	}
	// medPersonID02のGET
	public String GetMedPersonID02()
	{
		return this.medPersonID02Str;
	}
	// medPersonName02StrのSET
	public void SetMedPersonName02(String medPersonName02)
	{
		if (medPersonName02 != null)
		{
			this.medPersonName02Str = medPersonName02;
		}
	}
	// medPersonName02のGET
	public String GetMedPersonName02()
	{
		return this.medPersonName02Str;
	}
	// medPersonSyokuinKbn02StrのSET
	public void SetMedPersonSyokuinKbn02(String medPersonSyokuinKbn02)
	{
		if (medPersonSyokuinKbn02 != null)
		{
			this.medPersonSyokuinKbn02Str = medPersonSyokuinKbn02;
		}
	}
	// medPersonSyokuinKbn02のGET
	public String GetMedPersonSyokuinKbn02()
	{
		return this.medPersonSyokuinKbn02Str;
	}
	// medPersonID03StrのSET
	public void SetMedPersonID03(String medPersonID03)
	{
		if (medPersonID03 != null)
		{
			this.medPersonID03Str = medPersonID03;
		}
	}
	// medPersonID03のGET
	public String GetMedPersonID03()
	{
		return this.medPersonID03Str;
	}
	// medPersonName03StrのSET
	public void SetMedPersonName03(String medPersonName03)
	{
		if (medPersonName03 != null)
		{
			this.medPersonName03Str = medPersonName03;
		}
	}
	// medPersonName03のGET
	public String GetMedPersonName03()
	{
		return this.medPersonName03Str;
	}
	// medPersonSyokuinKbn03StrのSET
	public void SetMedPersonSyokuinKbn03(String medPersonSyokuinKbn03)
	{
		if (medPersonSyokuinKbn03 != null)
		{
			this.medPersonSyokuinKbn03Str = medPersonSyokuinKbn03;
		}
	}
	// medPersonSyokuinKbn03のGET
	public String GetMedPersonSyokuinKbn03()
	{
		return this.medPersonSyokuinKbn03Str;
	}
	// medPersonID04StrのSET
	public void SetMedPersonID04(String medPersonID04)
	{
		if (medPersonID04 != null)
		{
			this.medPersonID04Str = medPersonID04;
		}
	}
	// medPersonID04のGET
	public String GetMedPersonID04()
	{
		return this.medPersonID04Str;
	}
	// medPersonName04StrのSET
	public void SetMedPersonName04(String medPersonName04)
	{
		if (medPersonName04 != null)
		{
			this.medPersonName04Str = medPersonName04;
		}
	}
	// medPersonName04のGET
	public String GetMedPersonName04()
	{
		return this.medPersonName04Str;
	}
	// medPersonSyokuinKbn04StrのSET
	public void SetMedPersonSyokuinKbn04(String medPersonSyokuinKbn04)
	{
		if (medPersonSyokuinKbn04 != null)
		{
			this.medPersonSyokuinKbn04Str = medPersonSyokuinKbn04;
		}
	}
	// medPersonSyokuinKbn04のGET
	public String GetMedPersonSyokuinKbn04()
	{
		return this.medPersonSyokuinKbn04Str;
	}
	// medPersonID05StrのSET
	public void SetMedPersonID05(String medPersonID05)
	{
		if (medPersonID05 != null)
		{
			this.medPersonID05Str = medPersonID05;
		}
	}
	// medPersonID05のGET
	public String GetMedPersonID05()
	{
		return this.medPersonID05Str;
	}
	// medPersonName05StrのSET
	public void SetMedPersonName05(String medPersonName05)
	{
		if (medPersonName05 != null)
		{
			this.medPersonName05Str = medPersonName05;
		}
	}
	// medPersonName05のGET
	public String GetMedPersonName05()
	{
		return this.medPersonName05Str;
	}
	// medPersonSyokuinKbn05StrのSET
	public void SetMedPersonSyokuinKbn05(String medPersonSyokuinKbn05)
	{
		if (medPersonSyokuinKbn05 != null)
		{
			this.medPersonSyokuinKbn05Str = medPersonSyokuinKbn05;
		}
	}
	// medPersonSyokuinKbn05のGET
	public String GetMedPersonSyokuinKbn05()
	{
		return this.medPersonSyokuinKbn05Str;
	}
	// medPersonID06StrのSET
	public void SetMedPersonID06(String medPersonID06)
	{
		if (medPersonID06 != null)
		{
			this.medPersonID06Str = medPersonID06;
		}
	}
	// medPersonID06のGET
	public String GetMedPersonID06()
	{
		return this.medPersonID06Str;
	}
	// medPersonName06StrのSET
	public void SetMedPersonName06(String medPersonName06)
	{
		if (medPersonName06 != null)
		{
			this.medPersonName06Str = medPersonName06;
		}
	}
	// medPersonName06のGET
	public String GetMedPersonName06()
	{
		return this.medPersonName06Str;
	}
	// medPersonSyokuinKbn06StrのSET
	public void SetMedPersonSyokuinKbn06(String medPersonSyokuinKbn06)
	{
		if (medPersonSyokuinKbn06 != null)
		{
			this.medPersonSyokuinKbn06Str = medPersonSyokuinKbn06;
		}
	}
	// medPersonSyokuinKbn06のGET
	public String GetMedPersonSyokuinKbn06()
	{
		return this.medPersonSyokuinKbn06Str;
	}
	// medPersonID07StrのSET
	public void SetMedPersonID07(String medPersonID07)
	{
		if (medPersonID07 != null)
		{
			this.medPersonID07Str = medPersonID07;
		}
	}
	// medPersonID07のGET
	public String GetMedPersonID07()
	{
		return this.medPersonID07Str;
	}
	// medPersonName07StrのSET
	public void SetMedPersonName07(String medPersonName07)
	{
		if (medPersonName07 != null)
		{
			this.medPersonName07Str = medPersonName07;
		}
	}
	// medPersonName07のGET
	public String GetMedPersonName07()
	{
		return this.medPersonName07Str;
	}
	// medPersonSyokuinKbn07StrのSET
	public void SetMedPersonSyokuinKbn07(String medPersonSyokuinKbn07)
	{
		if (medPersonSyokuinKbn07 != null)
		{
			this.medPersonSyokuinKbn07Str = medPersonSyokuinKbn07;
		}
	}
	// medPersonSyokuinKbn07のGET
	public String GetMedPersonSyokuinKbn07()
	{
		return this.medPersonSyokuinKbn07Str;
	}
	// medPersonID08StrのSET
	public void SetMedPersonID08(String medPersonID08)
	{
		if (medPersonID08 != null)
		{
			this.medPersonID08Str = medPersonID08;
		}
	}
	// medPersonID08のGET
	public String GetMedPersonID08()
	{
		return this.medPersonID08Str;
	}
	// medPersonName08StrのSET
	public void SetMedPersonName08(String medPersonName08)
	{
		if (medPersonName08 != null)
		{
			this.medPersonName08Str = medPersonName08;
		}
	}
	// medPersonName08のGET
	public String GetMedPersonName08()
	{
		return this.medPersonName08Str;
	}
	// medPersonSyokuinKbn08StrのSET
	public void SetMedPersonSyokuinKbn08(String medPersonSyokuinKbn08)
	{
		if (medPersonSyokuinKbn08 != null)
		{
			this.medPersonSyokuinKbn08Str = medPersonSyokuinKbn08;
		}
	}
	// medPersonSyokuinKbn08のGET
	public String GetMedPersonSyokuinKbn08()
	{
		return this.medPersonSyokuinKbn08Str;
	}
	// medPersonID09StrのSET
	public void SetMedPersonID09(String medPersonID09)
	{
		if (medPersonID09 != null)
		{
			this.medPersonID09Str = medPersonID09;
		}
	}
	// medPersonID09のGET
	public String GetMedPersonID09()
	{
		return this.medPersonID09Str;
	}
	// medPersonName09StrのSET
	public void SetMedPersonName09(String medPersonName09)
	{
		if (medPersonName09 != null)
		{
			this.medPersonName09Str = medPersonName09;
		}
	}
	// medPersonName09のGET
	public String GetMedPersonName09()
	{
		return this.medPersonName09Str;
	}
	// medPersonSyokuinKbn09StrのSET
	public void SetMedPersonSyokuinKbn09(String medPersonSyokuinKbn09)
	{
		if (medPersonSyokuinKbn09 != null)
		{
			this.medPersonSyokuinKbn09Str = medPersonSyokuinKbn09;
		}
	}
	// medPersonSyokuinKbn09のGET
	public String GetMedPersonSyokuinKbn09()
	{
		return this.medPersonSyokuinKbn09Str;
	}
	// medPersonID10StrのSET
	public void SetMedPersonID10(String medPersonID10)
	{
		if (medPersonID10 != null)
		{
			this.medPersonID10Str = medPersonID10;
		}
	}
	// medPersonID10のGET
	public String GetMedPersonID10()
	{
		return this.medPersonID10Str;
	}
	// medPersonName10StrのSET
	public void SetMedPersonName10(String medPersonName10)
	{
		if (medPersonName10 != null)
		{
			this.medPersonName10Str = medPersonName10;
		}
	}
	// medPersonName10のGET
	public String GetMedPersonName10()
	{
		return this.medPersonName10Str;
	}
	// medPersonSyokuinKbn10StrのSET
	public void SetMedPersonSyokuinKbn10(String medPersonSyokuinKbn10)
	{
		if (medPersonSyokuinKbn10 != null)
		{
			this.medPersonSyokuinKbn10Str = medPersonSyokuinKbn10;
		}
	}
	// medPersonSyokuinKbn10のGET
	public String GetMedPersonSyokuinKbn10()
	{
		return this.medPersonSyokuinKbn10Str;
	}

	// 2011.07.26 Add NSK_T.Koudate End

	// 2011.08.18 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者をリストにする

	/// <summary>
	/// 担当者リストを取得する
	/// </summary>
	/// <remarks>
	/// 01カラムから順に設定されている前提
	/// 設定された数が変わったら自動的に再取得する
	/// </remarks>
	/// <returns>担当者リスト</returns>
	public List<TeamInfoMedPersonBean> GetMedPersonList() throws CloneNotSupportedException
	{
		if (medPersonList.size() != GetMedPersonCount())
		{
			RefleshMedPersonList(this);
		}
		return medPersonList;
	}

	/// <summary>
	/// 担当者リストを再設定する
	/// </summary>
	/// <param name="team">チーム情報クラス</param>
	/// <remarks>
	/// 01カラムから順に設定されている前提
	/// </remarks>
	private void RefleshMedPersonList(TeamInfoBean team) throws CloneNotSupportedException
	{
		TeamInfoMedPersonBean medPerson = new TeamInfoMedPersonBean(this);
		for (int index = 0; index < GetMedPersonCount(); index++)
		{
			medPerson.CurrentMedPersonIndex = index + 1;
			medPersonList.add(medPerson.thisClone());
		}
	}


	/*
	// 並び替えた担当者リストを取得する

	/// <summary>
	/// 並び替えた担当者リストを取得する
	/// </summary>
	/// <param name="syokuinKbnArray">並び替える順番に並んだ職員区分配列</param>
	/// <returns>並び替えた担当者リスト</returns>
	public List<TeamInfoMedPersonBean> GetSortedMedPersonList(String[] syokuinKbnArray)
	{
		List<TeamInfoMedPersonBean> medPersonList = this.GetMedPersonList();
		TeamInfoMedPersonComparer comparer = new TeamInfoMedPersonComparer();
		comparer.SetOrderBySyokuinKbnArray(syokuinKbnArray);
		medPersonList.Sort(comparer);
		return medPersonList;
	}

	// 2011.08.18 Add NSK_T.Koudate End
	*/

	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////

	// 2011.07.26 Mod NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	/// <summary>
	/// 担当者群に存在するか
	/// </summary>
	/// <param name="staffID">職員ID</param>
	/// <returns>いたらtrue</returns>
	public boolean MatchingMedPersonID(String staffID)
	{
		boolean retBool = false;

		if (StringUtils.isEmpty(staffID)) {
			return retBool;
		}

		if (staffID.equals(this.medPersonID01Str) ||
			staffID.equals(this.medPersonID02Str) ||
			staffID.equals(this.medPersonID03Str) ||
			staffID.equals(this.medPersonID04Str) ||
			staffID.equals(this.medPersonID05Str) ||
			staffID.equals(this.medPersonID06Str) ||
			staffID.equals(this.medPersonID07Str) ||
			staffID.equals(this.medPersonID08Str) ||
			staffID.equals(this.medPersonID09Str) ||
			staffID.equals(this.medPersonID10Str))
		{
			retBool = true;
		}
		return retBool;
	}

	// コメント(担当者にまとめるため廃止)
	////2010.09.19 Add H.Orikasa Start
	///// <summary>
	///// 担当者群に存在するか
	///// </summary>
	///// <param name="staffID">職員ID</param>
	///// <returns></returns>
	//public boolean MatchingKensaGisiID(String staffID)
	//{
	//    boolean retBool = false;

	//    if (this.kensaGisiID1Str == staffID ||
	//        this.kensaGisiID2Str == staffID ||
	//        this.kensaGisiID3Str == staffID ||
	//        this.kensaGisiID4Str == staffID ||
	//        this.kensaGisiID5Str == staffID)
	//    {
	//        retBool = true;
	//    }
	//    return retBool;
	//}

	///// <summary>
	///// 看護師郡に存在するか
	///// </summary>
	///// <param name="staffID">職員ID</param>
	///// <returns></returns>
	//public boolean MatchingKangosiID(String staffID)
	//{
	//    boolean retBool = false;

	//    if (this.kangosiID1Str == staffID ||
	//        this.kangosiID2Str == staffID ||
	//        this.kangosiID3Str == staffID ||
	//        this.kangosiID4Str == staffID ||
	//        this.kangosiID5Str == staffID)
	//    {
	//        retBool = true;
	//    }
	//    return retBool;
	//}
	//2010.09.19 Add H.Orikasa End

	// 2011.07.26 Mod NSK_T.Koudate End

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(ログインユーザをチーム情報へ追加する処理削除)
	////2010.09.22 Add H.Orikasa Start
	///// <summary>
	///// 担当者へ追加する
	///// </summary>
	///// <param name="staffID">職員ID</param>
	///// <param name="nameStr">名称</param>
	///// <returns></returns>
	//public boolean AddKensaGisi(String staffID, String nameStr)
	//{
	//    boolean retBool = true;
	//    if (this.kensaGisiID1Str.length() <= 0)
	//    {
	//        this.kensaGisiID1Str	= staffID;
	//        this.kensaGisiName1Str	= nameStr;
	//    }
	//    else if (this.kensaGisiID2Str.length() <= 0)
	//    {
	//        this.kensaGisiID2Str	= staffID;
	//        this.kensaGisiName2Str	= nameStr;
	//    }
	//    else if (this.kensaGisiID3Str.length() <= 0)
	//    {
	//        this.kensaGisiID3Str	= staffID;
	//        this.kensaGisiName3Str	= nameStr;
	//    }
	//    else if (this.kensaGisiID4Str.length() <= 0)
	//    {
	//        this.kensaGisiID4Str	= staffID;
	//        this.kensaGisiName4Str	= nameStr;
	//    }
	//    else if (this.kensaGisiID5Str.length() <= 0)
	//    {
	//        this.kensaGisiID5Str	= staffID;
	//        this.kensaGisiName5Str	= nameStr;
	//    }
	//    else
	//    {
	//        return false;
	//    }
	//    return retBool;
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(ログインユーザをチーム情報へ追加する処理削除)
	// <summary>
	// 設定済の担当者数を取得する
	// </summary>
	// <returns></returns>
	//public Integer GetKensaGisiCount()
	//{
	//    Integer retInt = 0;

	//    if (this.kensaGisiID1Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kensaGisiID2Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kensaGisiID3Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kensaGisiID4Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kensaGisiID5Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }

	//    return retInt;
	//}

	// 2011.07.26 Del NSK_T.Koudate End
	// 2011.08.01 Add NSK_T.Koudate Start NML-CAT2-004
	// 担当者
	// <summary>
	// 設定済の担当者数を取得する
	// </summary>
	// <returns>設定済の担当者数</returns>
	public Integer GetMedPersonCount()
	{
		Integer retInt = 0;

		if (this.medPersonID01Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID02Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID03Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID04Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID05Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID06Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID07Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID08Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID09Str.length() > 0)
		{
			retInt += 1;
		}
		if (this.medPersonID10Str.length() > 0)
		{
			retInt += 1;
		}

		return retInt;
	}

	// 2011.08.01 Add NSK_T.Koudate End

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(ログインユーザをチーム情報へ追加する処理削除)
	///// <summary>
	///// 担当者から指定の情報を削除する
	///// </summary>
	///// <param name="staffID">職員ID</param>
	//public void DeleteKensaGisi(String staffID)
	//{
	//    if (this.kensaGisiID1Str == staffID)
	//    {
	//        this.kensaGisiID1Str	= "";
	//        this.kensaGisiName1Str	= "";
	//    }
	//    else if (this.kensaGisiID2Str == staffID)
	//    {
	//        this.kensaGisiID2Str	= "";
	//        this.kensaGisiName2Str	= "";
	//    }
	//    else if (this.kensaGisiID3Str == staffID)
	//    {
	//        this.kensaGisiID3Str	= "";
	//        this.kensaGisiName3Str	= "";
	//    }
	//    else if (this.kensaGisiID4Str == staffID)
	//    {
	//        this.kensaGisiID4Str	= "";
	//        this.kensaGisiName4Str	= "";
	//    }
	//    else if (this.kensaGisiID5Str == staffID)
	//    {
	//        this.kensaGisiID5Str	= "";
	//        this.kensaGisiName5Str	= "";
	//    }
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(ログインユーザをチーム情報へ追加する処理削除)
	///// <summary>
	///// 看護師へ追加する
	///// </summary>
	///// <param name="staffID">職員ID</param>
	///// <param name="nameStr">名称</param>
	///// <returns></returns>
	//public boolean AddKangosi(String staffID, String nameStr)
	//{
	//    boolean retBool = true;
	//    if (this.kangosiID1Str.length() <= 0)
	//    {
	//        this.kangosiID1Str		= staffID;
	//        this.kangosiName1Str	= nameStr;
	//    }
	//    else if (this.kangosiID2Str.length() <= 0)
	//    {
	//        this.kangosiID2Str		= staffID;
	//        this.kangosiName2Str	= nameStr;
	//    }
	//    else if (this.kangosiID3Str.length() <= 0)
	//    {
	//        this.kangosiID3Str		= staffID;
	//        this.kangosiName3Str	= nameStr;
	//    }
	//    else if (this.kangosiID4Str.length() <= 0)
	//    {
	//        this.kangosiID4Str		= staffID;
	//        this.kangosiName4Str	= nameStr;
	//    }
	//    else if (this.kangosiID5Str.length() <= 0)
	//    {
	//        this.kangosiID5Str		= staffID;
	//        this.kangosiName5Str	= nameStr;
	//    }
	//    else
	//    {
	//        return false;
	//    }
	//    return retBool;
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(ログインユーザをチーム情報へ追加する処理削除)
	/// <summary>
	/// 設定済の看護師数を取得する
	/// </summary>
	/// <returns></returns>
	//public Integer GetKangosiCount()
	//{
	//    Integer retInt = 0;

	//    if (this.kangosiID1Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kangosiID2Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kangosiID3Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kangosiID4Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }
	//    if (this.kangosiID5Str.length() > 0)
	//    {
	//        retInt += 1;
	//    }

	//    return retInt;
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	// 2011.07.26 Del NSK_T.Koudate Start NML-CAT2-004
	// コメント(ログインユーザをチーム情報へ追加する処理削除)
	///// <summary>
	///// 看護師から指定の情報を削除する
	///// </summary>
	///// <param name="staffID">職員ID</param>
	//public void DeleteKangosi(String staffID)
	//{
	//    if (this.kangosiID1Str == staffID)
	//    {
	//        this.kangosiID1Str		= "";
	//        this.kangosiName1Str	= "";
	//    }
	//    else if (this.kangosiID2Str == staffID)
	//    {
	//        this.kangosiID2Str		= "";
	//        this.kangosiName2Str	= "";
	//    }
	//    else if (this.kangosiID3Str == staffID)
	//    {
	//        this.kangosiID3Str		= "";
	//        this.kangosiName3Str	= "";
	//    }
	//    else if (this.kangosiID4Str == staffID)
	//    {
	//        this.kangosiID4Str		= "";
	//        this.kangosiName4Str	= "";
	//    }
	//    else if (this.kangosiID5Str == staffID)
	//    {
	//        this.kangosiID5Str		= "";
	//        this.kangosiName5Str	= "";
	//    }
	//}

	// 2011.07.26 Del NSK_T.Koudate End

	/// <summary>
	/// チーム情報が設定されているか
	/// </summary>
	/// <returns></returns>
	public boolean EnabledTeamInfo()
	{
		boolean retBool = false;

		// 2011.08.10 Mod NSK_T.Koudate Start NML-CAT2-004
		if (
			medPersonID01Str.length() > 0			||
			medPersonName01Str.length() > 0		||
			medPersonSyokuinKbn01Str.length() > 0	||
			medPersonID02Str.length() > 0			||
			medPersonName02Str.length() > 0		||
			medPersonSyokuinKbn02Str.length() > 0	||
			medPersonID03Str.length() > 0			||
			medPersonName03Str.length() > 0		||
			medPersonSyokuinKbn03Str.length() > 0	||
			medPersonID04Str.length() > 0			||
			medPersonName04Str.length() > 0		||
			medPersonSyokuinKbn04Str.length() > 0	||
			medPersonID05Str.length() > 0			||
			medPersonName05Str.length() > 0		||
			medPersonSyokuinKbn05Str.length() > 0	||
			medPersonID06Str.length() > 0			||
			medPersonName06Str.length() > 0		||
			medPersonSyokuinKbn06Str.length() > 0	||
			medPersonID07Str.length() > 0			||
			medPersonName07Str.length() > 0		||
			medPersonSyokuinKbn07Str.length() > 0	||
			medPersonID08Str.length() > 0			||
			medPersonName08Str.length() > 0		||
			medPersonSyokuinKbn08Str.length() > 0	||
			medPersonID09Str.length() > 0			||
			medPersonName09Str.length() > 0		||
			medPersonSyokuinKbn09Str.length() > 0	||
			medPersonID10Str.length() > 0			||
			medPersonName10Str.length() > 0		||
			medPersonSyokuinKbn10Str.length() > 0	||
			enforcedocID1Str.length() > 0			||
	        enforcedocName1Str.length() > 0		||
	        enforcedocID2Str.length() > 0			||
	        enforcedocName2Str.length() > 0		||
	        enforcedocID3Str.length() > 0			||
	        enforcedocName3Str.length() > 0		||
	        enforcedocID4Str.length() > 0			||
	        enforcedocName4Str.length() > 0		||
	        enforcedocID5Str.length() > 0			||
	        enforcedocName5Str.length() > 0
			)
		{
			retBool = true;
		}
		// コメント(担当者にまとめられた)
		//if (kensaGisiID1Str.length() > 0		||
		//    kensaGisiName1Str.length() > 0	||
		//    kensaGisiID2Str.length() > 0		||
		//    kensaGisiName2Str.length() > 0	||
		//    kensaGisiID3Str.length() > 0		||
		//    kensaGisiName3Str.length() > 0	||
		//    kensaGisiID4Str.length() > 0		||
		//    kensaGisiName4Str.length() > 0	||
		//    kensaGisiID5Str.length() > 0		||
		//    kensaGisiName5Str.length() > 0	||
		//    enforcedocID1Str.length() > 0		||
		//    enforcedocName1Str.length() > 0	||
		//    enforcedocID2Str.length() > 0		||
		//    enforcedocName2Str.length() > 0	||
		//    enforcedocID3Str.length() > 0		||
		//    enforcedocName3Str.length() > 0	||
		//    enforcedocID4Str.length() > 0		||
		//    enforcedocName4Str.length() > 0	||
		//    enforcedocID5Str.length() > 0		||
		//    enforcedocName5Str.length() > 0	||
		//    kangosiID1Str.length() > 0		||
		//    kangosiName1Str.length() > 0		||
		//    kangosiID2Str.length() > 0		||
		//    kangosiName2Str.length() > 0		||
		//    kangosiID3Str.length() > 0		||
		//    kangosiName3Str.length() > 0		||
		//    kangosiID4Str.length() > 0		||
		//    kangosiName4Str.length() > 0		||
		//    kangosiID5Str.length() > 0		||
		//    kangosiName5Str.length() > 0)
		//{
		//    retBool = true;
		//}

		// 2011.08.10 Mod NSK_T.Koudate End
		return retBool;
	}

	//2010.09.22 Add H.Orikasa End
}

/*
// 2011.07.29 Add NSK_T.Koudate Start NML-CAT2-004
/// <summary>
/// 担当者比較クラス
/// </summary>
public class TeamInfoMedPersonComparer extends IComparer<TeamInfoMedPersonBean>
{
	/// <summary>
	/// 職員区分表示順
	/// </summary>
	/// <remarks>カンマ区切りの文字列</remarks>
	private String[] orderBySyokuinKbnArray;

	/// <summary>
	/// チーム情報-所属表示順から職員区分表示順配列へ設定する
	/// </summary>
	/// <param name="teamInfoSyokuinKbnOrderby">app.configのTeamInfoSyokuinKbnOrderby.Value</param>
	public void SetOrderBySyokuinKbnArray(String[] orderByArray)
	{
		orderBySyokuinKbnArray = orderByArray;
	}

	// IComparer<TeamInfoMedPersonBean> メンバ

	/// <summary>
	/// 比較メソッド
	/// </summary>
	/// <param name="x">比較元</param>
	/// <param name="y">比較先</param>
	/// <returns>比較結果</returns>
	public Integer Compare(TeamInfoMedPersonBean x, TeamInfoMedPersonBean y)
	{
		if (x == null || y== null)
		{
			return 0;
		}
		Integer xIndex = Array.IndexOf<String>(orderBySyokuinKbnArray, x.SyokuinKbn);
		Integer yIndex = Array.IndexOf<String>(orderBySyokuinKbnArray, y.SyokuinKbn);
		Integer compared = xIndex.compareTo(yIndex);
		if (compared != 0)
		{
			return compared;
		}
		return x.CurrentMedPersonIndex.compareTo(y.CurrentMedPersonIndex);
	}


}
// 2011.07.29 Add NSK_T.Koudate End
*/
