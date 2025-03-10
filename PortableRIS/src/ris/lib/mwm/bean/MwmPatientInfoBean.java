package ris.lib.mwm.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import ris.lib.mwm.util.MwmMasterUtil;
import ris.portable.common.Const;

/// <summary>
/// 患者情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class MwmPatientInfoBean
{
	private String kanjaIDStr = ""; //Ris.PatientInfo.KANJA_ID
	private String kanjiSimeiStr = ""; //Ris.PatientInfo.KANJISIMEI
	private String romaSimeiStr = ""; //Ris.PatientInfo.ROMASIMEI
	private String kanaSimeiStr = ""; //Ris.PatientInfo.KANASIMEI
	private String birthdayStr = ""; //Ris.PatientInfo.BIRTHDAY
	private String sexStr = ""; //Ris.PatientInfo.SEX
	private String tallStr = ""; //Ris.PatientInfo.TALL
	private String weightStr = ""; //Ris.PatientInfo.WEIGHT

	public MwmPatientInfoBean()
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
		strBuild.append("[PatientInfoBean]");

		strBuild.append("KANJA_ID=" + kanjaIDStr + ";");
		strBuild.append("KANJISIMEI=" + kanjiSimeiStr + ";");
		strBuild.append("ROMASIMEI=" + romaSimeiStr + ";");
		strBuild.append("KANASIMEI=" + kanaSimeiStr + ";");
		strBuild.append("BIRTHDAY=" + birthdayStr + ";");
		strBuild.append("SEX=" + sexStr + ";");
		strBuild.append("TALL=" + tallStr + ";");
		strBuild.append("WEIGHT=" + weightStr + ";");

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
	// birthdayStrのGET
	public Timestamp GetBirthdayDateTime()
	{
		Timestamp retDate = Const.TIMESTAMP_MINVALUE;
		if (this.birthdayStr.length() > 0)
		{
			if (this.birthdayStr.length() == 8)
			{
				String tempBirthdayStr = birthdayStr.substring(0, 4);
				tempBirthdayStr += "-" + birthdayStr.substring(4, 4 + 2);
				tempBirthdayStr += "-" + birthdayStr.substring(6, 6 + 2) + " 00:00:00";
				retDate = Timestamp.valueOf(tempBirthdayStr);
			}
			else if (this.birthdayStr.length() == 10)
			{
				retDate = Timestamp.valueOf(this.birthdayStr.replace("/", "-") + " 00:00:00");
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
			if( MwmMasterUtil.IS_OTOKO.equals(sexStr) ||
				MwmMasterUtil.IS_OTOKO_MSG.equals(sexStr))
			{
				this.sexStr = MwmMasterUtil.IS_OTOKO;
			}
			else if( MwmMasterUtil.IS_ONNA.equals(sexStr) ||
				MwmMasterUtil.IS_ONNA_MSG.equals(sexStr) )
			{
				this.sexStr = MwmMasterUtil.IS_ONNA;
			}
			else
			{
				this.sexStr = MwmMasterUtil.OTHER_SEX;
			}
		}
	}
	//
	// tallStrのGET
	public String GetTall()
	{
		return this.tallStr;
		//// parameters
		//int decimalPointIndex = 0; // 小数点のINDEX
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
	// tallStrのGET
	public String GetTallMeter()
	{
		String retStr = "";

		if (this.tallStr.length() > 0)
		{
			try
			{
				BigDecimal tallDec = BigDecimal.valueOf(Double.parseDouble(this.tallStr) / 100);
				retStr = String.format("%.4f", tallDec);
			}
			catch (Exception e)
			{
			}
		}

		return retStr;
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
		String retStr = "";

		if (this.weightStr.length() > 0)
		{
			try
			{
				BigDecimal weightDec = BigDecimal.valueOf(Double.parseDouble(this.weightStr));
				retStr = String.format("%.2f", weightDec);
			}
			catch (Exception e)
			{
			}
		}

		return retStr;
	}
	// weightStrのSET
	public void SetWeight( String weightStr )
	{
		if( weightStr != null )
		{
			this.weightStr = weightStr;
		}
	}
}
