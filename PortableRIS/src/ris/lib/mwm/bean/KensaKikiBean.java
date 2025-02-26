package ris.lib.mwm.bean;

import ris.lib.mwm.util.MwmMasterUtil;
import ris.portable.util.DataRow;

/// <summary>
/// KensaKikiBean の概要の説明です。
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
///
/// </summary>
public class KensaKikiBean
{
	// private members
	private String kensaKikiIDStr = "";		//Ris.KensaKikiMaster.kensaKikiID
	private String kensaKikiNameStr = "";	//Ris.KensaKikiMaster.kensaKikiName
	private String mwmTypeStr = "";			//Ris.KensaKikiMaster.MWMType
	private String kikiTypeStr = "";		//Ris.KensaKikiMaster.Kikitype
	private String nameModeStr = "0";		//Ris.KensaKikiMaster.NameMode

	private String mwmParam01Str = "";		//Ris.KensaKikiMaster.MWMPARAM1
	private String mwmParam02Str = "";		//Ris.KensaKikiMaster.MWMPARAM2
	private String mwmParam03Str = "";		//Ris.KensaKikiMaster.MWMPARAM3
	private String mwmParam04Str = "";		//Ris.KensaKikiMaster.MWMPARAM4
	private String mwmParam05Str = "";		//Ris.KensaKikiMaster.MWMPARAM5
	private String mwmParam06Str = "";		//Ris.KensaKikiMaster.MWMPARAM6
	private String mwmParam07Str = "";		//Ris.KensaKikiMaster.MWMPARAM7
	private String mwmParam08Str = "";		//Ris.KensaKikiMaster.MWMPARAM8
	private String mwmParam09Str = "";		//Ris.KensaKikiMaster.MWMPARAM9
	private String mwmParam10Str = "";		//Ris.KensaKikiMaster.MWMPARAM10

	private String aeTitleMwmStr = "";		//Ris.KensaKikiMaster.AE_Title_MWM
	private String mppsTypeStr = "";		//Ris.KensaKikiMaster.MPPSType
	private String aeTitlePpsStr = "";		//Ris.KensaKikiMaster.AE_Title_PPS
	private String modalityTypeStr = "";	//Ris.KensaKikiMaster.Modality_Type
//
	public KensaKikiBean()
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
		strBuild.append("[KensaKikiBean]");

		strBuild.append("kensaKikiID=" + kensaKikiIDStr + ";");
		strBuild.append("kensaKikiName=" + kensaKikiNameStr + ";");
		strBuild.append("MWMType=" + mwmTypeStr + ";");
		strBuild.append("kikiType=" + kikiTypeStr + ";");
		strBuild.append("NameMode=" + nameModeStr + ";");

		strBuild.append("MWMParam01=" + mwmParam01Str + ";");
		strBuild.append("MWMParam02=" + mwmParam02Str + ";");
		strBuild.append("MWMParam03=" + mwmParam03Str + ";");
		strBuild.append("MWMParam04=" + mwmParam04Str + ";");
		strBuild.append("MWMParam05=" + mwmParam05Str + ";");
		strBuild.append("MWMParam06=" + mwmParam06Str + ";");
		strBuild.append("MWMParam07=" + mwmParam07Str + ";");
		strBuild.append("MWMParam08=" + mwmParam08Str + ";");
		strBuild.append("MWMParam09=" + mwmParam09Str + ";");
		strBuild.append("MWMParam10=" + mwmParam10Str + ";");

		strBuild.append("AE_Title_MWM=" + aeTitleMwmStr + ";");
		strBuild.append("MPPSType=" + mppsTypeStr + ";");
		strBuild.append("AE_Title_PPS=" + aeTitlePpsStr + ";");
		strBuild.append("Modality_Type=" + modalityTypeStr + ";");

		return strBuild.toString();
	}

	/// <summary>
	/// DataRowからのデータ設定
	/// </summary>
	/// <param name="curRow">データ</param>
	public void SetKensaKikiBean( DataRow curRow )
	{
		if (curRow != null)
		{
			if( curRow.get(MwmMasterUtil.RIS_KENSAKIKI_ID) != null)
			{
				this.SetKensaKikiID(curRow.get(MwmMasterUtil.RIS_KENSAKIKI_ID).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_KENSAKIKI_NAME) != null)
			{
				this.SetKensaKikiName(curRow.get(MwmMasterUtil.RIS_KENSAKIKI_NAME).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_TYPE) != null)
			{
				this.SetMwmType(curRow.get(MwmMasterUtil.RIS_MWM_TYPE).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_KIKI_TYPE) != null)
			{
				this.SetKikiType(curRow.get(MwmMasterUtil.RIS_KIKI_TYPE).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_NAME_MODE) != null)
			{
				this.SetNameMode(curRow.get(MwmMasterUtil.RIS_NAME_MODE).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM01) != null)
			{
				this.SetMWMParam01(curRow.get(MwmMasterUtil.RIS_MWM_PARAM01).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM02) != null)
			{
				this.SetMWMParam02(curRow.get(MwmMasterUtil.RIS_MWM_PARAM02).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM03) != null)
			{
				this.SetMWMParam03(curRow.get(MwmMasterUtil.RIS_MWM_PARAM03).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM04) != null)
			{
				this.SetMWMParam04(curRow.get(MwmMasterUtil.RIS_MWM_PARAM04).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM05) != null)
			{
				this.SetMWMParam05(curRow.get(MwmMasterUtil.RIS_MWM_PARAM05).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM06) != null)
			{
				this.SetMWMParam06(curRow.get(MwmMasterUtil.RIS_MWM_PARAM06).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM07) != null)
			{
				this.SetMWMParam07(curRow.get(MwmMasterUtil.RIS_MWM_PARAM07).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM08) != null)
			{
				this.SetMWMParam08(curRow.get(MwmMasterUtil.RIS_MWM_PARAM08).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM09) != null)
			{
				this.SetMWMParam09(curRow.get(MwmMasterUtil.RIS_MWM_PARAM09).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MWM_PARAM10) != null)
			{
				this.SetMWMParam10(curRow.get(MwmMasterUtil.RIS_MWM_PARAM10).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_AE_TITLE_MWM) != null)
			{
				this.SetAETitle(curRow.get(MwmMasterUtil.RIS_AE_TITLE_MWM).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MPPS_TYPE) != null)
			{
				this.SetMppsType(curRow.get(MwmMasterUtil.RIS_MPPS_TYPE).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_AE_TITLE_PPS) != null)
			{
				this.SetAETitlePps(curRow.get(MwmMasterUtil.RIS_AE_TITLE_PPS).toString());
			}
			if( curRow.get(MwmMasterUtil.RIS_MODALITY_TYPE) != null)
			{
				this.SetModality(curRow.get(MwmMasterUtil.RIS_MODALITY_TYPE).toString());
			}
		}
	}

//
	// kensaKikiIDStrのSET
	public void SetKensaKikiID( String kensaKikiID )
	{
		if( kensaKikiID != null )
		{
			kensaKikiIDStr = kensaKikiID;
		}
	}

	// kensaKikiIDStrのGET
	public String GetKensaKikiID()
	{
		return this.kensaKikiIDStr;
	}
//
	// kensaKikiNameStrのSET
	public void SetKensaKikiName( String kensaKikiName )
	{
		if( kensaKikiName != null )
		{
			kensaKikiNameStr = kensaKikiName;
		}
	}

	// kensaKikiNameStrのGET
	public String GetKensaKikiName()
	{
		return this.kensaKikiNameStr;
	}
//
	// mwmTypeStrのSET
	public void SetMwmType( String mwmType )
	{
		if( mwmType != null )
		{
			try
			{
				int mwmTypeInt = Integer.parseInt(mwmType);
				this.mwmTypeStr = mwmType;
			}
			catch( Exception ex )
			{
			}
		}
	}
	// mwmTypeStrのGET
	public String GetMwmType()
	{
		return this.mwmTypeStr;
	}
//
	// kikiTypeStrのSET
	public void SetKikiType( String kikiType )
	{
		if( kikiType != null )
		{
			try
			{
				int kikiTypeInt = Integer.parseInt(kikiType);
				this.kikiTypeStr = kikiType;
			}
			catch( Exception ex )
			{
			}
		}
	}
//
	// kikiTypeStrのGET
	public String GetKikiType()
	{
		return this.kikiTypeStr;
	}
//
	// nameModeStrのSET
	public void SetNameMode( String nameMode )
	{
		if( nameMode != null )
		{
			if( nameMode.compareTo("0") == 0 || nameMode.compareTo("1") == 0 )
			{
				this.nameModeStr = nameMode;
			}
		}
	}
	// nameModeStrのGET
	public String GetNameMode()
	{
		return this.nameModeStr;
	}
//
	// mwmParam01StrのSET
	public void SetMWMParam01( String mwmParam01 )
	{
		if( mwmParam01 != null )
		{
			this.mwmParam01Str = mwmParam01;
		}
	}
	// mwmParam01StrのGET
	public String GetMWMParam01()
	{
		return this.mwmParam01Str;
	}
//
	// mwmParam02StrのSET
	public void SetMWMParam02( String mwmParam02 )
	{
		if( mwmParam02 != null )
		{
			this.mwmParam02Str = mwmParam02;
		}
	}
	// mwmParam02StrのGET
	public String GetMWMParam02()
	{
		return this.mwmParam02Str;
	}
//
	// mwmParam03StrのSET
	public void SetMWMParam03( String mwmParam03 )
	{
		if( mwmParam03 != null )
		{
			this.mwmParam03Str = mwmParam03;
		}
	}
	// mwmParam03StrのGET
	public String GetMWMParam03()
	{
		return this.mwmParam03Str;
	}
	//
	// mwmParam04StrのSET
	public void SetMWMParam04( String mwmParam04 )
	{
		if( mwmParam04 != null )
		{
			this.mwmParam04Str = mwmParam04;
		}
	}		// aeTitleStrのGET
	public String GetMWMParam04()
	{
		return this.mwmParam04Str;
	}
	//
	// mwmParam05StrのSET
	public void SetMWMParam05( String mwmParam05 )
	{
		if( mwmParam05 != null )
		{
			this.mwmParam05Str = mwmParam05;
		}
	}
	// mwmParam05StrのGET
	public String GetMWMParam05()
	{
		return this.mwmParam05Str;
	}
	//
	// mwmParam06StrのSET
	public void SetMWMParam06( String mwmParam06 )
	{
		if( mwmParam06 != null )
		{
			this.mwmParam06Str = mwmParam06;
		}
	}
	// mwmParam06StrのGET
	public String GetMWMParam06()
	{
		return this.mwmParam06Str;
	}
	//
	// mwmParam07StrのSET
	public void SetMWMParam07( String mwmParam07 )
	{
		if( mwmParam07 != null )
		{
			this.mwmParam07Str = mwmParam07;
		}
	}
	// mwmParam07StrのGET
	public String GetMWMParam07()
	{
		return this.mwmParam07Str;
	}
	//
	// mwmParam08StrのSET
	public void SetMWMParam08( String mwmParam08 )
	{
		if( mwmParam08 != null )
		{
			this.mwmParam08Str = mwmParam08;
		}
	}
	// mwmParam08StrのGET
	public String GetMWMParam08()
	{
		return this.mwmParam08Str;
	}
	//
	// mwmParam09StrのSET
	public void SetMWMParam09( String mwmParam09 )
	{
		if( mwmParam09 != null )
		{
			this.mwmParam09Str = mwmParam09;
		}
	}
	// mwmParam09StrのGET
	public String GetMWMParam09()
	{
		return this.mwmParam09Str;
	}
	//
	// mwmParam10StrのSET
	public void SetMWMParam10( String mwmParam10 )
	{
		if( mwmParam10 != null )
		{
			this.mwmParam10Str = mwmParam10;
		}
	}
	// mwmParam10StrのGET
	public String GetMWMParam10()
	{
		return this.mwmParam10Str;
	}
	//
	// aeTitleMwmStrのSET
	public void SetAETitle( String aeTitle )
	{
		if( aeTitle != null )
		{
			this.aeTitleMwmStr = aeTitle;
		}
	}
	// aeTitleMwmStrのGET
	public String GetAETitle()
	{
		return this.aeTitleMwmStr;
	}
//
	// mppsTypeStrのSET
	public void SetMppsType( String mppsType )
	{
		if( mppsType != null )
		{
			if( mppsType.compareTo("0") == 0 || mppsType.compareTo("1") == 0 )
			{
				this.mppsTypeStr = mppsType;
			}
		}
	}
	// mppsTypeStrのGET
	public String GetMppsType()
	{
		return this.mppsTypeStr;
	}
//
	// aeTitlePpsStrのSET
	public void SetAETitlePps( String aeTitlePps )
	{
		if( aeTitlePps != null )
		{
			this.aeTitlePpsStr = aeTitlePps;
		}
	}
	// aeTitlePpsStrのGET
	public String GetAETitlePps()
	{
		return this.aeTitlePpsStr;
	}
//
	// modalityTypeStrのSET
	public void SetModality( String modality )
	{
		if( modality != null )
		{
			this.modalityTypeStr = modality;
		}
	}
	// modalityTypeStrのGET
	public String GetModality()
	{
		return this.modalityTypeStr;
	}
//
}
