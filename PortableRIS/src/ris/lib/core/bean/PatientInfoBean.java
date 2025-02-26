package ris.lib.core.bean;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import ris.lib.core.Configuration;
import ris.lib.core.database.RisPatientCommentTbl;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MasterUtil;
import ris.portable.common.Const;
import ris.portable.util.DataRow;

/// <summary>
/// 患者情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.25	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class PatientInfoBean
{
	private String kanjaIDStr					= ""; 					//Ris.PatientInfo.KANJA_ID
	private String kanjiSimeiStr				= ""; 					//Ris.PatientInfo.KANJISIMEI
	private String romaSimeiStr					= ""; 					//Ris.PatientInfo.ROMASIMEI
	private String kanaSimeiStr					= ""; 					//Ris.PatientInfo.KANASIMEI
	private String birthdayStr					= ""; 					//Ris.PatientInfo.BIRTHDAY
	private String ageStr						= ""; 					//当日を元に求める
	private String sexStr						= ""; 					//Ris.PatientInfo.SEX
	private String jusyo1Str					= ""; 					//Ris.PatientInfo.JUSYO1
	private String jusyo2Str					= ""; 					//Ris.PatientInfo.JUSYO2
	private String jusyo3Str					= ""; 					//Ris.PatientInfo.JUSYO3
	private String nyugaiKbnStr					= ""; 					//Ris.PatientInfo.KANJA_NYUGAIKBN
	private String nyugaiKbnNameStr				= ""; 					//CODECONVERTから引く
	private String sectionIDStr					= ""; 					//Ris.PatientInfo.SECTION_ID
	private String sectionNameStr				= ""; 					//SectionMasterから引く
	private String byoutouIDStr					= ""; 					//Ris.PatientInfo.BYOUTOU_ID
	private String byoutouNameStr				= ""; 					//ByoutouMasterから引く
	private String byousituIDStr				= ""; 					//Ris.PatientInfo.BYOUSITU_ID
	private String byousituNameStr				= ""; 					//ByousituMasterから引く
	private String tallStr						= ""; 					//Ris.PatientInfo.TALL
	private String weightStr					= ""; 					//Ris.PatientInfo.WEIGHT
	private String bloodStr						= ""; 					//Ris.PatientInfo.BLOOD
	private String transportTypeStr				= ""; 					//Ris.PatientInfo.TRANSPORTTYPE
	private Integer handicappedMarkInt				= 0;					//Ris.PatientInfo.HANDICAPPEDMARK
	private String handicappedStr				= "";					//Ris.PatientInfo.HANDICAPPED
	private Integer infectionMarkInt				= 0;					//Ris.PatientInfo.INFECTIONMARK
	private String infectionStr					= "";					//Ris.PatientInfo.INFECTION
	private Integer contraindicationMarkInt 		= 0;					//Ris.PatientInfo.CONTRAINDICATIONMARK
	private String contraindicationStr			= "";					//Ris.PatientInfo.CONTRAINDICATION
	private Integer allergyMarkInt					= 0;					//Ris.PatientInfo.ALLERGYMARK
	private String allergyStr					= "";					//Ris.PatientInfo.ALLERGY
	private Integer pregnancyMarkInt				= 0;					//Ris.PatientInfo.PREGNANCYMARK
	private String pregnancyStr					= "";					//Ris.PatientInfo.PREGNANCY
	private Integer notesMarkInt					= 0;					//Ris.PatientInfo.NOTESMARK
	private String notesStr						= "";					//Ris.PatientInfo.NOTES
	private String examDataStr					= "";					//Ris.PatientInfo.EXAMDATA
	private String extraProfileStr				= "";					//Ris.PatientInfo.EXTRAPROFILE
	private Timestamp hisUpdateDateTime			= Const.TIMESTAMP_MINVALUE;	//Ris.PatientInfo.HIS_UPDATEDATE
	private Timestamp risUpdateDateTime			= Const.TIMESTAMP_MINVALUE;	//Ris.PatientInfo.RIS_UPDATEDATE
	private Timestamp deathDate					= Const.TIMESTAMP_MINVALUE;	//Ris.PatientInfo.DEATHDATE
	private String commentStr					= "";					//Ris.PatientComment.PatientComment where Ris.PatientComment.KensaType_ID='Common'
	// 2010.07.30 Add T.Ootsuka Start
	public String creatinineresultStr			= "";					// Ris.PatientInfo.CREATININERESULT
	public String egfrresultStr					= "";					// Ris.PatientInfo.EGFRRESULT
	public Timestamp creatinineresultUpdateDate	= Const.TIMESTAMP_MINVALUE;	// Ris.PatientInfo.CREATININEUPDATEDATE
	public Timestamp egfrresultUpdateDate		= Const.TIMESTAMP_MINVALUE;	// Ris.PatientInfo.EGFRUPDATEDATE
	// 2010.07.30 Add T.Ootsuka End
	private ArrayList arrCommentList			= new ArrayList();		//Ris.PatientCommentのリスト
	private String templateTypeStr				= "1";					// 1の場合は「障害情報」 それ以外は「その他の注意事項」
	private boolean templateMarkBool				= false;
	private String execSqlStr					= "";
	private boolean searchFlgBool					= false;				//検索フラグ
	private boolean updateFlgBool					= false;				//更新フラグ

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public PatientInfoBean()
	{
		//
	}
	//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[PatientInfoBean]");

		strBuild.append("KANJA_ID=" + kanjaIDStr + ";");
		// 2011.02.23 Mod H.Orikasa Start
		strBuild.append("KANJISIMEI=@@@;");
		strBuild.append("ROMASIMEI=@@@;");
		strBuild.append("KANASIMEI=@@@;");
		// コメント
		//strBuild.append("KANJISIMEI=" + kanjiSimeiStr + ";");
		//strBuild.append("ROMASIMEI=" + romaSimeiStr + ";");
		//strBuild.append("KANASIMEI=" + kanaSimeiStr + ";");

		// 2011.02.23 Mod H.Orikasa End
		strBuild.append("BIRTHDAY=" + birthdayStr + ";");
		strBuild.append("AGE=" + ageStr + ";");
		strBuild.append("SEX=" + sexStr + ";");
		// 2011.02.23 Mod H.Orikasa Start
		strBuild.append("JUSYO1=@@@;");
		strBuild.append("JUSYO2=@@@;");
		strBuild.append("JUSYO3=@@@;");
		// コメント
		//strBuild.append("JUSYO1=" + jusyo1Str + ";");
		//strBuild.append("JUSYO2=" + jusyo2Str + ";");
		//strBuild.append("JUSYO3=" + jusyo3Str + ";");

		// 2011.02.23 Mod H.Orikasa End
		strBuild.append("KANJA_NYUGAIKBN=" + nyugaiKbnStr + ";");
		strBuild.append("SECTION_ID=" + sectionIDStr + ";");
		strBuild.append("BYOUTOU_ID=" + byoutouIDStr + ";");
		strBuild.append("BYOUSITU_ID=" + byousituIDStr + ";");
		strBuild.append("TALL=" + tallStr + ";");
		strBuild.append("WEIGHT=" + weightStr + ";");
		strBuild.append("BLOOD=" + bloodStr + ";");
		strBuild.append("TRANSPORTTYPE=" + transportTypeStr + ";");
		strBuild.append("HANDICAPPEDMARK=" + handicappedMarkInt + ";");
		strBuild.append("HANDICAPPED=" + handicappedStr + ";");
		strBuild.append("INFECTIONMARK=" + infectionMarkInt + ";");
		strBuild.append("INFECTION=" + infectionStr + ";");
		strBuild.append("CONTRAINDICATIONMARK=" + contraindicationMarkInt + ";");
		strBuild.append("CONTRAINDICATION=" + contraindicationStr + ";");
		strBuild.append("ALLERGYMARK=" + allergyMarkInt + ";");
		strBuild.append("ALLERGY=" + allergyStr + ";");
		strBuild.append("PREGNANCYMARK=" + pregnancyMarkInt + ";");
		strBuild.append("PREGNANCY=" + pregnancyStr + ";");
		strBuild.append("NOTESMARK=" + notesMarkInt + ";");
		strBuild.append("NOTES=" + notesStr + ";");
		strBuild.append("EXAMDATA=" + examDataStr + ";");
		strBuild.append("EXTRAPROFILE=" + extraProfileStr + ";");
		strBuild.append("HIS_UPDATEDATE=" + hisUpdateDateTime.toString() + ";");
		strBuild.append("RIS_UPDATEDATE=" + risUpdateDateTime.toString() + ";");
		strBuild.append("DEATHDATE=" + deathDate.toString() + ";");
		strBuild.append("PatientComment=" + commentStr + ";");
		// 2010.07.30 Add T.Ootsuka Start
		strBuild.append("CREATININERESULT=" + creatinineresultStr + ";");
		strBuild.append("EGFRRESULT=" + egfrresultStr + ";");
		strBuild.append("CREATININEUPDATEDATE=" + creatinineresultUpdateDate.toString() + ";");
		strBuild.append("EGFRUPDATEDATE=" + egfrresultUpdateDate.toString() + ";");
		// 2010.07.30 Add T.Ootsuka End

		return strBuild.toString();
	}
	//
	// kanjaIDStrのGET
	public String GetKanjaID()
	{
		return this.kanjaIDStr;
	}
	// kanjaIDStrのSET
	public void SetKanjaID( String kanjaID )
	{
		if( kanjaID != null )
		{
			this.kanjaIDStr = kanjaID;
		}
	}
	//
	// kanjiSimeiStrのGET
	public String GetKanjiSimei()
	{
		return this.kanjiSimeiStr;
	}
	// kanjiSimeiStrのSET
	public void SetKanjiSimei( String kanjiSimei )
	{
		if( kanjiSimei != null )
		{
			this.kanjiSimeiStr = kanjiSimei;
		}
	}
	//
	// romaSimeiStrのGET
	public String GetRomaSimei()
	{
		return this.romaSimeiStr;
	}
	// romaSimeiStrのSET
	public void SetRomaSimei( String romaSimei )
	{
		if( romaSimei != null )
		{
			this.romaSimeiStr = romaSimei;
		}
	}
	//
	// kanaSimeiStrのGET
	public String GetKanaSimei()
	{
		return this.kanaSimeiStr;
	}
	// kanaSimeiStrのSET
	public void SetKanaSimei( String kanaSimei )
	{
		if( kanaSimei != null )
		{
			this.kanaSimeiStr = kanaSimei;
		}
	}
	//
	// birthdayStrのGET
	public String GetBirthday()
	{
		return this.birthdayStr;
	}
	//
	// birthdayStrのGET
	public String GetBirthdayString()
	{
		String retStr = this.birthdayStr;
		if( birthdayStr.length() == 8 )
		{
			String tempBirthdayStr = birthdayStr.substring(0, 4);
			tempBirthdayStr += "/" + birthdayStr.substring(4, 4 + 2);
			tempBirthdayStr += "/" + birthdayStr.substring(6, 6 + 2);
			retStr = tempBirthdayStr;
		}
		return retStr;
	}
	// birthdayStrのGET
	public Timestamp GetBirthdayDateTime()
	{
		Timestamp retDate = new Timestamp(System.currentTimeMillis());

		String birthDayString = GetBirthdayString();
		if (birthDayString.length() > 0)
		{
			try
			{
				if (birthDayString.length() == 8)
				{
					String tempBirthdayStr = birthDayString.substring(0, 4);
					tempBirthdayStr += "-" + birthDayString.substring(4, 4 + 2);
					tempBirthdayStr += "-" + birthDayString.substring(6, 6 + 2) + " 00:00:00";
					retDate = Timestamp.valueOf(tempBirthdayStr);
				}
				else if (birthDayString.length() == 10)
				{
					retDate = Timestamp.valueOf(this.birthdayStr.replace("/", "-") + " 00:00:00");
				}
			}
			catch (Exception e)
			{
			}
		}
		return retDate;
	}
	// birthdayStrのSET
	public void SetBirthday( String birthdayStr )
	{
		if( birthdayStr != null )
		{
			if( birthdayStr.length() == 10 )
			{
				String tempBirthdayStr = birthdayStr.substring(0, 4);
				tempBirthdayStr += birthdayStr.substring(5, 5 + 2);
				tempBirthdayStr += birthdayStr.substring(8, 8 + 2);
				birthdayStr = tempBirthdayStr;
			}
			this.birthdayStr = birthdayStr;
		}
	}
	// birthdayStrのSET
	public void SetBirthday( Timestamp birthday )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(birthday) )
		{
			this.birthdayStr = (birthday.toString().substring( 0, 10 )).replace( "-", "" );
		}
	}
	//
	// ageStrのGET
	public String GetAge()
	{
		return this.ageStr;
	}
	// ageStrのGET
	public String GetAgeString()
	{
		String retStr = "";
		if (this.ageStr.length() >0)
		{
			retStr = this.ageStr + CommonString.AGE_FORMAT;
		}
		return retStr;
	}
	// ageStrのSET
	public void SetAge( String age )
	{
		if( age != null )
		{
			this.ageStr = age;
		}
	}
	//
	// sexStrのGET
	public String GetSex()
	{
		return this.sexStr;
	}
	// sexStrのSET
	public void SetSex(String sexStr)
	{
		if( sexStr != null )
		{
			this.sexStr = sexStr;
		}
	}
	//
	// jusyo1StrのGET
	public String GetJusyo1()
	{
		return this.jusyo1Str;
	}
	// jusyo1StrのSET
	public void SetJusyo1( String jusyo1 )
	{
		if( jusyo1 != null )
		{
			this.jusyo1Str = jusyo1;
		}
	}
	//
	// jusyo2StrのGET
	public String GetJusyo2()
	{
		return this.jusyo2Str;
	}
	// jusyo2StrのSET
	public void SetJusyo2( String jusyo2 )
	{
		if( jusyo2 != null )
		{
			this.jusyo2Str = jusyo2;
		}
	}
	//
	// jusyo3StrのGET
	public String GetJusyo3()
	{
		return this.jusyo3Str;
	}
	// jusyo3StrのSET
	public void SetJusyo3( String jusyo3 )
	{
		if( jusyo3 != null )
		{
			this.jusyo3Str = jusyo3;
		}
	}
	//
	// nyugaiKbnStrのGET
	public String GetNyugaiKbn()
	{
		return this.nyugaiKbnStr;
	}
	// nyugaiKbnStrのSET
	public void SetNyugaiKbn( String nyugaiKbn )
	{
		if( nyugaiKbn != null )
		{
			this.nyugaiKbnStr = nyugaiKbn;
		}
	}
	//
	// nyugaiKbnNameStrのGET
	public String GetNyugaiKbnName()
	{
		return this.nyugaiKbnNameStr;
	}
	// nyugaiKbnNameStrのSET
	public void SetNyugaiKbnName( String nyugaiKbnName )
	{
		if( nyugaiKbnName != null )
		{
			this.nyugaiKbnNameStr = nyugaiKbnName;
		}
	}
	//
	// sectionIDStrのGET
	public String GetSectionID()
	{
		return this.sectionIDStr;
	}
	// sectionIDStrのSET
	public void SetSectionID( String sectionID )
	{
		if( sectionID != null )
		{
			this.sectionIDStr = sectionID;
		}
	}
	//
	// sectionNameStrのGET
	public String GetSectionName()
	{
		return this.sectionNameStr;
	}
	// sectionNameStrのSET
	public void SetSectionName( String sectionName )
	{
		if( sectionName != null )
		{
			this.sectionNameStr = sectionName;
		}
	}
	//
	// byoutouIDStrのGET
	public String GetByoutouID()
	{
		return this.byoutouIDStr;
	}
	// byoutouIDStrのSET
	public void SetByoutouID( String byoutouID )
	{
		if( byoutouID != null )
		{
			this.byoutouIDStr = byoutouID;
		}
	}
	//
	// byoutouNameStrのGET
	public String GetByoutouName()
	{
		return this.byoutouNameStr;
	}
	// byoutouNameStrのSET
	public void SetByoutouName( String byoutouName )
	{
		if( byoutouName != null )
		{
			this.byoutouNameStr = byoutouName;
		}
	}
	//
	// byousituIDStrのGET
	public String GetByousituID()
	{
		return this.byousituIDStr;
	}
	// byousituIDStrのSET
	public void SetByousituID( String byousituID )
	{
		if( byousituID != null )
		{
			this.byousituIDStr = byousituID;
		}
	}
	//
	// byousituNameStrのGET
	public String GetByousituName()
	{
		return this.byousituNameStr;
	}
	// byousituNameStrのSET
	public void SetByousituName( String byousituName )
	{
		if( byousituName != null )
		{
			this.byousituNameStr = byousituName;
		}
	}

	// byoutou,byousituのGET
	public String GetByoutouByousituString()
	{
		String retStr = "";

		retStr = this.byoutouNameStr;
		if (retStr.trim().length() > 0 && this.byousituNameStr.length() > 0)
		{
			retStr += "/" + this.byousituNameStr;
		}
		else if (this.byousituNameStr.length() > 0)
		{
			retStr += this.byousituNameStr;
		}

		return retStr;
	}

	//
	// tallStrのGET
	public String GetTall()
	{
		return this.tallStr;
		//// parameters
		//Integer decimalPointIndex = 0; // 小数点のINDEX
		//String retStr = "";

		//if( this.tallStr.length() > 0 )
		//{
		//    decimalPointIndex = this.tallStr.IndexOf( "." );
		//}
		//if( decimalPointIndex > 0 )
		//{
		//    retStr = this.tallStr.substring( 0, decimalPointIndex );
		//}
		//else
		//{
		//    retStr = this.tallStr;
		//}

		//return retStr;
	}
	// tallStrのSET
	public void SetTall( String tallStr )
	{
		if( tallStr != null )
		{
			this.tallStr = tallStr;
		}
	}
	//
	// weightStrのGET
	public String GetWeight()
	{
		return this.weightStr;
		//// parameters
		//Integer decimalPointIndex = 0; // 小数点のINDEX
		//String retStr = "";

		//if( this.weightStr.length() > 0 )
		//{
		//    decimalPointIndex = this.weightStr.IndexOf(".");
		//}
		//if( decimalPointIndex > 0 )
		//{
		//    retStr = this.weightStr.substring( 0, decimalPointIndex );
		//}
		//else
		//{
		//    retStr = this.weightStr;
		//}

		//return retStr;
	}
	// weightStrのSET
	public void SetWeight( String weightStr )
	{
		if( weightStr != null )
		{
			this.weightStr = weightStr;
		}
	}

	// heightweightのGET
	public String GetHeightWeightString()
	{
		String retStr = " ";

		if (this.tallStr.length() > 0)
		{
			retStr += this.tallStr + CommonString.HEIGHT_UNIT;
		}
		if (retStr.trim().length() > 0 && this.weightStr.length() > 0)
		{
			retStr += "/" + this.weightStr + CommonString.WEIGHT_UNIT;
		}
		else if (this.weightStr.length() > 0)
		{
			retStr += this.weightStr + CommonString.WEIGHT_UNIT;
		}

		return retStr;
	}

	//
	// bloodStrのGET
	public String GetBlood()
	{
		return this.bloodStr;
	}
	// bloodStrのSET
	public void SetBlood( String bloodStr )
	{
		if( bloodStr != null )
		{
			this.bloodStr = bloodStr;
		}
	}
	//
	// transportTypeStrのGET
	public String GetTransportType()
	{
		return this.transportTypeStr;
	}
	// transportTypeStrのSET
	public void SetTransportType( String transportType )
	{
		if( transportType != null )
		{
			this.transportTypeStr = transportType;
		}
	}
	//
	// handicappedMarkIntのGET
	public Integer GetHandicappedMark()
	{
		return this.handicappedMarkInt;
	}
	// handicappedMarkIntのSET
	public void SetHandicappedMark( Integer handicappedMarkInt )
	{
		if (handicappedMarkInt == 1)
		{
			this.handicappedMarkInt = handicappedMarkInt;
		}
		else
		{
			this.handicappedMarkInt = 0;
		}
	}
	//
	// handicappedStrのGET
	public String GetHandicap()
	{
		return this.handicappedStr;
	}
	// handicappedStrのSET
	public void SetHandicap( String handicapped )
	{
		if( handicapped != null )
		{
			this.handicappedStr = handicapped;
		}
	}
	//
	// infectionMarkIntのGET
	public Integer GetInfectionMark()
	{
		return this.infectionMarkInt;
	}
	// infectionMarkIntのSET
	public void SetInfectionMark( Integer infectionMarkInt )
	{
		if (infectionMarkInt == 1)
		{
			this.infectionMarkInt = infectionMarkInt;
		}
		else
		{
			this.infectionMarkInt = 0;
		}
	}
	//
	// infectionStrのGET
	public String GetInfection()
	{
		return this.infectionStr;
	}
	// infectionStrのSET
	public void SetInfection( String infection )
	{
		if( infection != null )
		{
			this.infectionStr = infection;
		}
	}
	//
	// contraindicationMarkIntのGET
	public Integer GetContraindicationMark()
	{
		return this.contraindicationMarkInt;
	}
	// contraindicationMarkIntのSET
	public void SetContraindicationMark( Integer contraindicationMarkInt )
	{
		if (contraindicationMarkInt == 1)
		{
			this.contraindicationMarkInt = contraindicationMarkInt;
		}
		else
		{
			this.contraindicationMarkInt = 0;
		}
	}
	//
	// contraindicationStrのGET
	public String GetContraindication()
	{
		return this.contraindicationStr;
	}
	// contraindicationStrのSET
	public void SetContraindication( String contraindication )
	{
		if( contraindication != null )
		{
			this.contraindicationStr = contraindication;
		}
	}
	//
	// allergyMarkIntのGET
	public Integer GetAllergyMark()
	{
		return this.allergyMarkInt;
	}
	// allergyMarkIntのSET
	public void SetAllergyMark( Integer allergyMarkInt )
	{
		if (allergyMarkInt == 1)
		{
			this.allergyMarkInt = allergyMarkInt;
		}
		else
		{
			this.allergyMarkInt = 0;
		}
	}
	//
	// allergyStrのGET
	public String GetAllergy()
	{
		return this.allergyStr;
	}
	// allergyStrのSET
	public void SetAllergy( String allergy )
	{
		if( allergy != null )
		{
			this.allergyStr = allergy;
		}
	}
	//
	// pregnancyMarkIntのGET
	public Integer GetPregnancyMark()
	{
		return this.pregnancyMarkInt;
	}
	// pregnancyMarkIntのSET
	public void SetPregnancyMark( Integer pregnancyMarkInt )
	{
		if (pregnancyMarkInt == 1)
		{
			this.pregnancyMarkInt = pregnancyMarkInt;
		}
		else
		{
			this.pregnancyMarkInt = 0;
		}
	}
	//
	// pregnancyStrのGET
	public String GetPregnancy()
	{
		return this.pregnancyStr;
	}
	// pregnancyStrのSET
	public void SetPregnancy( String pregnancy )
	{
		if( pregnancy != null )
		{
			this.pregnancyStr = pregnancy;
		}
	}
	//
	// notesMarkIntのGET
	public Integer GetNotesMark()
	{
		return this.notesMarkInt;
	}
	// notesMarkIntのSET
	public void SetNotesMark( Integer notesMarkInt )
	{
		if (notesMarkInt == 1)
		{
			this.notesMarkInt = notesMarkInt;
		}
		else
		{
			this.notesMarkInt = 0;
		}
	}
	//
	// notesStrのGET
	public String GetNotes()
	{
		return this.notesStr;
	}
	// notesStrのSET
	public void SetNotes( String notes )
	{
		if( notes != null )
		{
			this.notesStr = notes;
		}
	}
	//
	// examDataStrのGET
	public String GetExamData()
	{
		return this.examDataStr;
	}
	// examDataStrのSET
	public void SetExamData( String examData )
	{
		if( examData != null )
		{
			this.examDataStr = examData;
		}
	}
	//
	// extraProfileStrのGET
	public String GetExtraProfile()
	{
		return this.extraProfileStr;
	}
	// extraProfileStrのSET
	public void SetExtraProfile( String extraProfile )
	{
		if( extraProfile != null )
		{
			this.extraProfileStr = extraProfile;
		}
	}
	//
	// hisUpdateTimestampのGET
	public Timestamp GetHisUpdate()
	{
		return this.hisUpdateDateTime;
	}
	// hisUpdateTimestampのGET
	public String GetHisUpdateString()
	{
		String retStr = "";

		if (!Const.TIMESTAMP_MINVALUE.equals(this.hisUpdateDateTime) )
		{
			retStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(this.hisUpdateDateTime);
		}

		return retStr;
	}
	// hisUpdateTimestampのSET
	public void SetHisUpdate( Timestamp hisUpdateDateTime )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(hisUpdateDateTime) )
		{
			this.hisUpdateDateTime = hisUpdateDateTime;
		}
	}
	//
	// risUpdateTimestampのGET
	public Timestamp GetRisUpdate()
	{
		return this.risUpdateDateTime;
	}
	// risUpdateTimestampのSET
	public void SetRisUpdate( Timestamp risUpdateDateTime )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(risUpdateDateTime) )
		{
			this.risUpdateDateTime = risUpdateDateTime;
		}
	}
	//
	// deathDateのGET
	public Timestamp GetDeathDate()
	{
		return this.deathDate;
	}
	// deathDateのSET
	public void SetDeathDate( Timestamp deathDate )
	{
		//			if( deathDate != Const.TIMESTAMP_MINVALUE )
		//			{
		this.deathDate = deathDate;
		//			}
	}
	//
	// commentStrのGET
	public String GetComment()
	{
		return this.commentStr;
	}
	// commentStrのGET
	public String GetCommentAll(String kensatypeID)
	{
		String retStr = this.commentStr;

		//対象の検査種別コメントを探す
		String kTypeCmt = "";
		if (kensatypeID.length()   > 0    &&
			this.arrCommentList != null &&
			this.arrCommentList.size() > 0)
		{
			for (Integer i=0; i<this.arrCommentList.size(); i++)
			{
				DataRow row = (DataRow)this.arrCommentList.get(i);

				if (kensatypeID.equals(row.get(MasterUtil.RIS_KENSATYPE_ID).toString()))
				{
					kTypeCmt = row.get(RisPatientCommentTbl.PATIENTCOMMENT_COLUMN).toString();
					break;
				}
			}
		}

		//検査種別コメントの追加
		if (kTypeCmt.length() > 0)
		{
			if (retStr.length() <= 0)
			{
				retStr = kTypeCmt;
			}
			else
			{
				retStr += "\r\n" + kTypeCmt;
			}
		}

		return retStr;
	}

	// commentStrのSET
	public void SetComment( String comment )
	{
		if( comment != null )
		{
			this.commentStr = comment;
		}
	}
	//
	// arrCommentListの取得
	public ArrayList GetCommentList()
	{
		return this.arrCommentList;
	}
	// arrCommentListの初期化
	public void ReconstructCommentList(ArrayList commentList)
	{
		if (commentList != null)
		{
			this.arrCommentList.clear();
			this.arrCommentList = commentList;
		}
	}
	// templateTypeStrのGET
	public String GetTemplateType()
	{
		return this.templateTypeStr;
	}
	// templateTypeStrのSET
	public void SetTemplateType(String templateType)
	{
		if (templateType != null)
		{
			this.templateTypeStr = templateType;
		}
	}

	// templateMarkBoolのGET
	public boolean GetTemplateMark()
	{
		return this.templateMarkBool;
	}
	// templateMarkBoolのSET
	public void SetTemplateMark(boolean templateMark)
	{
		this.templateMarkBool = templateMark;
	}

	// 2010.07.30 Add T.Ootsuka Start
	//
	// creatinineresultStrのGET
	public String GetCreatinineresult()
	{
		return this.creatinineresultStr;
	}
	// creatinineresultStrのSET
	public void SetCreatinineresult(String creatinineresult)
	{
		if (creatinineresult != null)
		{
			this.creatinineresultStr = creatinineresult;
		}
	}
	//
	// egfrresultStrのGET
	public String GetEgfrresult()
	{
		return this.egfrresultStr;
	}
	// egfrresultStrのSET
	public void SetEgfrresult(String Egfrresult)
	{
		if (Egfrresult != null)
		{
			this.egfrresultStr = Egfrresult;
		}
	}
	//
	// creatinineresultUpdateDateのGET
	public Timestamp GetCreatinineUpdateDate()
	{
		return this.creatinineresultUpdateDate;
	}
	// creatinineresultUpdateDateのGET
	public String GetCreatinineUpdateDateString()
	{
		String retStr = "";

		if (!Const.TIMESTAMP_MINVALUE.equals(this.creatinineresultUpdateDate) )
		{
			retStr = new SimpleDateFormat(CommonString.LIST_FORMAT_DATETIME_1).format(this.creatinineresultUpdateDate);
		}

		return retStr;
	}
	// creatinineresultUpdateDateのSET
	public void SetCreatinineUpdateDate(Timestamp creatinineresultUpdateDateTime)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(creatinineresultUpdateDateTime) )
		{
			this.creatinineresultUpdateDate = creatinineresultUpdateDateTime;
		}
	}
	//
	// egfrresultUpdateDateのGET
	public Timestamp GetEgfrresultUpdateDate()
	{
		return this.egfrresultUpdateDate;
	}
	// egfrresultUpdateDateのGET
	public String GetEgfrresultUpdateDateString()
	{
		String retStr = "";

		if (!Const.TIMESTAMP_MINVALUE.equals(this.egfrresultUpdateDate) )
		{
			retStr = new SimpleDateFormat(CommonString.LIST_FORMAT_DATETIME_1).format(this.egfrresultUpdateDate);
		}

		return retStr;
	}
	// egfrresultUpdateDateのSET
	public void SetEgfrresultUpdateDate(Timestamp egfrresultUpdateTimestamp)
	{
		if (!Const.TIMESTAMP_MINVALUE.equals(egfrresultUpdateTimestamp) )
		{
			this.egfrresultUpdateDate = egfrresultUpdateTimestamp;
		}
	}
	// 2010.07.30 Add T.Ootsuka End

	//
	/// <summary>
	/// 入外区分のフォーマットを表示用にする
	/// </summary>
	/// <param name="nyugaiKbn"></param>
	/// <returns></returns>
    public String NyugaiKbnFormat(String nyugaiKbn, Connection con)
    {
		return Configuration.GetInstance().GetCodeConvertValue(null, CommonString.CODECONVERT_ID_INOUT, nyugaiKbn, con);
    }
	//
	/// <summary>
	/// 性別のフォーマットを表示用にする
	/// </summary>
	/// <param name="sex"></param>
	/// <returns></returns>
	public String SexFormat(String sex, Connection con)
	{
		return Configuration.GetInstance().GetCodeConvertValue(null, CommonString.CODECONVERT_ID_SEX, sex, con);
	}
	//
	/// <summary>
	/// 生年月日のフォーマットを表示用にする
	/// </summary>
	/// <param name="birthdayStr"></param>
	/// <returns></returns>
	public String BirthdayFormat(String birthdayStr)
	{
		if (birthdayStr != null && birthdayStr.length() == 8)
		{
			String tempBirthdayStr = birthdayStr.substring(0, 4);
			tempBirthdayStr += "/" + birthdayStr.substring(4, 4 + 2);
			tempBirthdayStr += "/" + birthdayStr.substring(6, 6 + 2);
			birthdayStr = tempBirthdayStr;
		}
		return birthdayStr;
	}

	/// <summary>
	/// 年齢を計算する
	/// </summary>
	/// <param name="birthdayStr"></param>
	/// <param name="date"></param>
	/// <returns></returns>
	public String GetPatientAge(String birthdayStr, String targetDate)
	{
		String age = "";
		String birthYear = "";
		String birthMonth = "";
		String birthDay = "";

		String chkYear = "";
		String chkMonth = "";
		String chkDay = "";

		try
		{
			Integer result = 0;

			if(0 < birthdayStr.length() && 0 < targetDate.length())
			{
				if (birthdayStr.length() == 10)
				{
					birthYear = birthdayStr.substring(0, 4);
					birthMonth = birthdayStr.substring(5, 7);
					birthDay = birthdayStr.substring(8, 10);
				}
				else if(birthdayStr.length() == 8)
				{
					birthYear = birthdayStr.substring(0, 4);
					birthMonth = birthdayStr.substring(4, 6);
					birthDay = birthdayStr.substring(6, 8);
				}

				if (targetDate.length() == 10)
				{
					chkYear = targetDate.substring(0, 4);
					chkMonth = targetDate.substring(5, 7);
					chkDay = targetDate.substring(8, 10);
				}
				else if(targetDate.length() == 8)
				{
					chkYear = targetDate.substring(0, 4);
					chkMonth = targetDate.substring(4, 6);
					chkDay = targetDate.substring(6, 8);
				}

				int b_year = Integer.parseInt(birthYear);
				int b_month = Integer.parseInt(birthMonth);
				int b_day = Integer.parseInt(birthDay);

				int c_year = Integer.parseInt(chkYear);
				int c_month = Integer.parseInt(chkMonth);
				int c_day = Integer.parseInt(chkDay);

				GregorianCalendar gCal = new GregorianCalendar();

				//現在（任意の年月日）の「年」から誕生日の「年」を引く
				result = c_year - b_year;

				//チェックしたい年がうるう年ではない場合
				if (!gCal.isLeapYear(c_year)) {
					//誕生日がうるう日なら
					if (b_month == 2 && b_day == 29) {
						b_month = 3;
						b_day = 1;
					} //誕生日を3/1にしておく
				}

				//その年の誕生日を過ぎていなければさらに１歳引く
				if (c_month < b_month) {
					result -= 1;
				} else if (c_month == b_month) {
					if (c_day < b_day) {
						result -= 1;
					}
				}

				if( result < 0 )
				{
					result = 0;
				}
				if (result > 999)
				{
					result = 999;
				}

				age = result.toString();
			}
		}
		catch (Exception e)
		{
			//
		}

		return age;
	}

	/// <summary>
	/// 年齢を計算する
	/// </summary>
	/// <param name="birthdayStr"></param>
	/// <param name="date"></param>
	/// <returns></returns>
	public String GetPatientAgeDate(String birthdayStr, Timestamp targetDate)
	{
		return GetPatientAge(birthdayStr, new SimpleDateFormat("yyyyMMdd").format(targetDate));
	}

	// execSqlStrのGET
	public String GetExecSql()
	{
		return this.execSqlStr;
	}
	// execSqlStrのSET
	public void SetExecSql(String execSql)
	{
		if (execSql != null)
		{
			this.execSqlStr = execSql;
		}
	}
//
	// searchFlgBoolのGET
	public boolean GetSearchFlg()
	{
		return this.searchFlgBool;
	}
	// searchFlgBoolのSET
	public void SetSearchFlg(boolean searchFlg)
	{
		this.searchFlgBool = searchFlg;
	}
//
	// updateFlgBoolのGET
	public boolean GetUpdateFlg()
	{
		return this.updateFlgBool;
	}
	// updateFlgBoolのSET
	public void SetUpdateFlg(boolean updateFlg)
	{
		this.updateFlgBool = updateFlg;
	}
//
	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// 生年月日のフォーマットを表示用にする
	/// </summary>
	/// <returns>"元号記号.年.月.日. (西暦年)"</returns>
	public String GetBirthDayFormat_Wareki()
	{
		String birthDayFormatStr = "";
		// 生年月日が無ければ終了
		if (StringUtils.isEmpty(GetBirthday()))
		{
			return birthDayFormatStr;
		}

		try
		{
			CultureInfo culture = new CultureInfo("ja-JP", true);
			culture.TimestampFormat.Calendar = new JapaneseCalendar();

			// 元号漢字
			String wareki = Configuration.GetInstance().GetDynamicPropertyString("Wareki.Text");
			String[] warekiList = wareki.split(",");

			// 元号記号
			String warekiMark = Configuration.GetInstance().GetDynamicPropertyString("wareki_Mark.Text");
			String[] warekiMarkList = warekiMark.split(",");

			birthDayFormatStr = GetBirthdayTimestamp().toString(CommonString.OPERATION_FORMAT_DATE1, culture);
			birthDayFormatStr += " ";
			birthDayFormatStr += GetBirthdayTimestamp().toString(CommonString.OPERATION_FORMAT_DATE2);

			// 元号漢字を元号記号へ変換
			for (Integer i = 0; i < warekiList.length; i++)
			{
				if (birthDayFormatStr.indexOf(warekiList[i]) >= 0)
				{
					birthDayFormatStr = birthDayFormatStr.replace(warekiList[i], warekiMarkList[i]);
				}
			}
		}
		catch (Exception e)
		{
			//
		}
		return birthDayFormatStr;
	}
	*/

	/*
	/// <summary>
	/// 指定日の月齢年齢を取得
	/// </summary>
	/// <param name="targetDate">指定日</param>
	/// <param name="Mode">フォーマットモード</param>
	/// <param name="Age1">第1指定年齢</param>
	/// <param name="Age2">第2指定年齢</param>
	/// <returns>フォーマット年齢</returns>
	public String GetLunarAge(Timestamp targetDate, String Mode, Integer Age1, Integer Age2)
	{
		double retDbl = 0;
		return GetLunarAge(targetDate, Mode, Age1, Age2, retDbl);
	}
	*/

	/*
	/// <summary>
	/// 指定日の月齢年齢を取得
	/// </summary>
	/// <param name="targetDate">指定日</param>
	/// <param name="Mode">フォーマットモード</param>
	/// <param name="Age1">第1指定年齢</param>
	/// <param name="Age2">第2指定年齢</param>
	/// <param name="ageDbl">年齢数値</param>
	/// <returns>フォーマット年齢</returns>
	public String GetLunarAge(Timestamp targetDate, String Mode, Integer Age1, Integer Age2, double ageDbl)
	{
		String lunarAgeStr = "";
		try
		{
			// 生年月日を取得
			Timestamp birthDayDate = GetBirthdayTimestamp();

			// 生年月日がない場合、空文字列を返す
			if (StringUtils.isEmpty(GetBirthday()))
			{
				return lunarAgeStr;
			}

			// 生年月日が指定日以降の場合、空文字列を返す
			// 2010.09.19 Mod T.Ootsuka Start 年齢表示修正
			if (birthDayDate == targetDate)
			{
				return ("0" + CommonString.AGE_FORMAT);
			}
			else if (birthDayDate.compareTo(targetDate) > 0)
			//if (birthDayDate.Date >= targetDate.Date)
			// 2010.09.19 Mod T.Ootsuka End 年齢表示修正
			{
				return lunarAgeStr;
			}

			Integer yearInt		= 0;
			Integer monthInt	= 0;
			Integer dayInt		= 0;

			// 期間を取得
			TimeSpan sp		= targetDate.Subtract(birthDayDate);

			// 生年月日から指定日までの期間計算
			Timestamp ageTimestamp	= new Timestamp(sp.Ticks);

			// 年取得
			if (ageTimestamp. > 1)
			{
				ageTimestamp		= ageTimestamp.AddYears(-1);
				yearInt			= ageTimestamp.Year;
			}
			else
			{
				yearInt			= 0;
			}

			// 月取得
			if (ageTimestamp.Month > 1)
			{
				ageTimestamp		= ageTimestamp.AddMonths(-1);
				monthInt		= ageTimestamp.Month;
			}
			else
			{
				monthInt		= 0;
			}

			// 日取得
			if (ageTimestamp.Day > 1)
			{
				ageTimestamp		= ageTimestamp.AddDays(-1);
				dayInt			= ageTimestamp.Day;
			}
			else
			{
				dayInt		= 0;
			}

			//年齢数値を求める
			try
			{
				String ageStr = yearInt + "." + String.Format("{0:D2}", monthInt) + String.Format("{0:D2}", dayInt);
				ageDbl = TextUtil.ParseStringToDouble(ageStr);
			}
			catch (Exception ex)
			{
				logger.fatal(ex);
			}

			// フォーマット（年齢条件）
			if (yearInt == 0)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_NORMALFORMAT1, monthInt.toString(), dayInt.toString());
			}
			else if ((yearInt >= Age1) && (yearInt < Age2))
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_NORMALFORMAT2, yearInt.toString(), monthInt.toString());
			}
			else
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_NORMALFORMAT3, yearInt.toString());
			}

			// フォーマット（モード条件指定時）
			if (Mode == CommonString.KANJA_LUNARAGE_MODE1)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT1,
								yearInt.toString(), monthInt.toString(), dayInt.toString());
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE2)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT2,
								yearInt.toString(), monthInt.toString(), dayInt.toString());
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE3)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT3, yearInt, monthInt, dayInt);
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE4)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT4, yearInt.toString());
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE5)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT5, yearInt, monthInt);
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE6)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT6, yearInt, monthInt, dayInt);
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE7)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT7, yearInt.toString());
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE8)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT8, yearInt, monthInt);
			}
			else if (Mode == CommonString.KANJA_LUNARAGE_MODE9)
			{
				lunarAgeStr = String.Format(CommonString.KANJA_LUNARAGE_MODEFORMAT9,
								yearInt, monthInt, dayInt);

			}
		}
		catch (Exception ex)
		{
			//
			logger.fatal(ex);
		}

		return lunarAgeStr;

	}
	// 2010.07.30 Add T.Ootsuka End
	*/
}
