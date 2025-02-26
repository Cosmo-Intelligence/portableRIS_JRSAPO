package ris.lib.core.bean;

import ris.lib.core.util.CommonString;
import ris.lib.core.util.MasterUtil;
import ris.portable.util.DataRow;

/// <summary>
/// ExSatueiBean の概要の説明です。
/// </summary>
public class ExSatueiBean
{
	// private members
	private String risIDStr					= "";	//Ris.ExSatueiTable.RIS_ID
	private String buiNoStr					= "0";	//Ris.ExSatueiTable.Bui_No
	private String noStr					= "0";	//Ris.ExSatueiTable.No
	private String satueiStatusStr			= "";	//Ris.ExSatueiTable.SatueiStatus
	private String satueiMenuIDStr			= ""; 	//Ris.ExSatueiTable.SatueiMenu_ID
	private String satueiMenuNameStr		= ""; 	//Ris.ExSatueiTable.SatueiMenu_Name
	private String satueiMenuNameKanaStr	= ""; 	//Ris.ExSatueiTable.SatueiMenu_Name_kana
	private String satueiCodeStr			= ""; 	//Ris.ExSatueiTable.Satuei_Code
	private String filmIDStr				= ""; 	//Ris.ExSatueiTable.Film_ID
	private String partitionStr				= ""; 	//Ris.ExSatueiTable.Partition
	private String usedStr					= ""; 	//Ris.ExSatueiTable.Used
	private String reshotFlgStr				= ""; 	//Ris.ExSatueiTable.ReShot_Flg
	private String cassetteNoStr			= ""; 	//Ris.ExSatueiTable.Cassette_No
	private String portableStatusStr		= ""; 	//Ris.ExSatueiTable.Portable_Status
	private String examData01Str 			= ""; 	//Ris.ExSatueiTable.ExamData01
	private String examData02Str 			= ""; 	//Ris.ExSatueiTable.ExamData02
	private String examData03Str 			= ""; 	//Ris.ExSatueiTable.ExamData03
	private String examData04Str 			= ""; 	//Ris.ExSatueiTable.ExamData04
	private String examData05Str 			= ""; 	//Ris.ExSatueiTable.ExamData05
	private String examData06Str 			= ""; 	//Ris.ExSatueiTable.ExamData06
	private String examData07Str 			= ""; 	//Ris.ExSatueiTable.ExamData07
	private String examData08Str 			= ""; 	//Ris.ExSatueiTable.ExamData08
	private String examData09Str 			= ""; 	//Ris.ExSatueiTable.ExamData09
	private String examData10Str 			= ""; 	//Ris.ExSatueiTable.ExamData10
	private String examData11Str 			= ""; 	//Ris.ExSatueiTable.ExamData11
	private String examData12Str 			= ""; 	//Ris.ExSatueiTable.ExamData12
	private String examData13Str 			= ""; 	//Ris.ExSatueiTable.ExamData13
	private String examData14Str 			= ""; 	//Ris.ExSatueiTable.ExamData14
	private String examData15Str 			= ""; 	//Ris.ExSatueiTable.ExamData15
	private String examData16Str 			= ""; 	//Ris.ExSatueiTable.ExamData16
	private String examData17Str 			= ""; 	//Ris.ExSatueiTable.ExamData17
	private String examData18Str 			= ""; 	//Ris.ExSatueiTable.ExamData18
	private String examData19Str 			= ""; 	//Ris.ExSatueiTable.ExamData19
	private String examData20Str 			= ""; 	//Ris.ExSatueiTable.ExamData20
	private String kensaKikiIDStr			= ""; 	//Ris.ExSatueiTable.KensaKiki_ID
	private String kvpStr					= "";	//Ris.ExSatueiTable.Kvp
	private String uaStr					= "";	//Ris.ExSatueiTable.Ua
	private String msecStr					= "";	//Ris.ExSatueiTable.Msec
	private String maStr					= "";	//Ris.ExSatueiTable.Ma
	private String secStr					= "";	//Ris.ExSatueiTable.Sec
	private String masStr					= "";	//Ris.ExSatueiTable.Mas
	// 2011.02.03 Add K.Shinohara Start
	private String satueiAddFlagStr			= "0";	//Ris.ExSatueiTable.SatueiAddFlag
	// 2011.02.03 Add K.Shinohara End
	//MPPS
	private String mpMppsInstanceUIDStr		= "";	//Ris.ExSatueiTable.MppsInstanceUID
	private String mpMppsVmNoStr			= "";	//Ris.ExSatueiTable.MppsVmNo
	private String mpMppsSatueiCodeStr 		= "";	//Ris.ExSatueiTable.MppsSatueiCode
	private String mpMppsAeTitleStr 		= "";	//Ris.ExSatueiTable.MppsAeTitle
	private String mpXRayTubeCurrentMaStr	= "";	//Ris.ExSatueiTable.XRayTubeCurrentMa
	private String mpExposureTimeSecStr		= "";	//Ris.ExSatueiTable.ExposureTimeSec
	private String mpExposureTimeMinStr		= "";	//Ris.ExSatueiTable.ExposureTimeMin
	private String mpKvStr 					= "";	//Ris.ExSatueiTable.Kv
	private String mpExposureNoStr 			= "";	//Ris.ExSatueiTable.ExposureNo
	private String mpCTDIStr 				= "";	//Ris.ExSatueiTable.CTDI
	private String mpDLPStr 				= "";	//Ris.ExSatueiTable.DLP
	private String mpFluoroScopyStr 		= "";	//Ris.ExSatueiTable.FluoroScopy
	private String mpImageAreaStr 			= "";	//Ris.ExSatueiTable.ImageArea
	private String mpDDistanceMMStr 		= "";	//Ris.ExSatueiTable.DDistanceMM
	private String mpDDistanceCMStr			= "";	//Ris.ExSatueiTable.DDistanceCM
	private String mpEDistanceMMStr			= "";	//Ris.ExSatueiTable.EDistanceMM
	private String mpEntranceDoseDgyStr		= "";	//Ris.ExSatueiTable.EntranceDoseDgy
	private String mpEntranceDoseMgyStr		= "";	//Ris.ExSatueiTable.EntranceDoseMgy
	private String mpExposedAreaStr			= "";	//Ris.ExSatueiTable.ExposedArea
	private String mpRadiationModeStr		= "";	//Ris.ExSatueiTable.RadiationMode
	private String mpFilterTypeStr			= "";	//Ris.ExSatueiTable.FilterType
	private String mpFilterMaterialStr		= "";	//Ris.ExSatueiTable.FilterMaterial
//
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExSatueiBean()
	{
		//
	}
//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExSatueiBean]");
		strBuild.append("RIS_ID="					+ risIDStr					+ ";");
		strBuild.append("Bui_No="					+ buiNoStr					+ ";");
		strBuild.append("No="						+ noStr						+ ";");
		strBuild.append("SatueiStatus="				+ satueiStatusStr			+ ";");
		strBuild.append("SatueiMenu_ID="			+ satueiMenuIDStr			+ ";");
		strBuild.append("SatueiMenu_Name="			+ satueiMenuNameStr			+ ";");
		strBuild.append("SatueiMenu_Name_kana="		+ satueiMenuNameKanaStr		+ ";");
		strBuild.append("Satuei_Code="				+ satueiCodeStr				+ ";");
		strBuild.append("Film_ID="					+ filmIDStr					+ ";");
		strBuild.append("Partition="				+ partitionStr				+ ";");
		strBuild.append("Used="						+ usedStr					+ ";");
		strBuild.append("ReShot_Flg="				+ reshotFlgStr				+ ";");
		strBuild.append("Cassette_No="				+ cassetteNoStr				+ ";");
		strBuild.append("Portable_Status="			+ portableStatusStr			+ ";");
		strBuild.append("ExamData01=" 				+ examData01Str 			+ ";");
		strBuild.append("ExamData02=" 				+ examData02Str 			+ ";");
		strBuild.append("ExamData03=" 				+ examData03Str 			+ ";");
		strBuild.append("ExamData04=" 				+ examData04Str 			+ ";");
		strBuild.append("ExamData05=" 				+ examData05Str 			+ ";");
		strBuild.append("ExamData06=" 				+ examData06Str 			+ ";");
		strBuild.append("ExamData07=" 				+ examData07Str 			+ ";");
		strBuild.append("ExamData08=" 				+ examData08Str 			+ ";");
		strBuild.append("ExamData09=" 				+ examData09Str 			+ ";");
		strBuild.append("ExamData10=" 				+ examData10Str 			+ ";");
		strBuild.append("ExamData11=" 				+ examData11Str 			+ ";");
		strBuild.append("ExamData12=" 				+ examData12Str 			+ ";");
		strBuild.append("ExamData13=" 				+ examData13Str 			+ ";");
		strBuild.append("ExamData14=" 				+ examData14Str 			+ ";");
		strBuild.append("ExamData15=" 				+ examData15Str 			+ ";");
		strBuild.append("ExamData16=" 				+ examData16Str 			+ ";");
		strBuild.append("ExamData17=" 				+ examData17Str 			+ ";");
		strBuild.append("ExamData18=" 				+ examData18Str 			+ ";");
		strBuild.append("ExamData19=" 				+ examData19Str 			+ ";");
		strBuild.append("ExamData20="				+ examData20Str 			+ ";");
		strBuild.append("KensaKiki_ID="				+ kensaKikiIDStr			+ ";");
		strBuild.append("KVP="						+ kvpStr					+ ";");
		strBuild.append("uA="						+ uaStr						+ ";");
		strBuild.append("mSec="						+ msecStr					+ ";");
		strBuild.append("mA="						+ maStr						+ ";");
		strBuild.append("Sec="						+ secStr					+ ";");
		strBuild.append("mAs="						+ masStr					+ ";");
		// 2011.02.03 Add K.Shinohara Start
		strBuild.append("SatueiAddFlag="			+ satueiAddFlagStr			+ ";");
		// 2011.02.03 Add K.Shinohara End
		/* 一ノ瀬保留
		//MPPS
		if (CommonString.FLG_ON.equals(Configuration.GetInstance().GetMppsKensaFlg()))
		{
			strBuild.append("MppsInstanceUID="		+ mpMppsInstanceUIDStr		+ ";");
			strBuild.append("MppsVmNo="				+ mpMppsVmNoStr				+ ";");
			strBuild.append("MppsSatueiCode="		+ mpMppsSatueiCodeStr 		+ ";");
			strBuild.append("MppsAeTitle="			+ mpMppsAeTitleStr 			+ ";");
			strBuild.append("XRayTubeCurrentMa="	+ mpXRayTubeCurrentMaStr	+ ";");
			strBuild.append("ExposureTimeSec="		+ mpExposureTimeSecStr		+ ";");
			strBuild.append("ExposureTimeMin="		+ mpExposureTimeMinStr		+ ";");
			strBuild.append("Kv="					+ mpKvStr 					+ ";");
			strBuild.append("ExposureNo="			+ mpExposureNoStr 			+ ";");
			strBuild.append("CTDI="					+ mpCTDIStr 				+ ";");
			strBuild.append("DLP="					+ mpDLPStr 					+ ";");
			strBuild.append("FluoroScopy="			+ mpFluoroScopyStr 			+ ";");
			strBuild.append("ImageArea="			+ mpImageAreaStr 			+ ";");
			strBuild.append("DDistanceMM="			+ mpDDistanceMMStr 			+ ";");
			strBuild.append("DDistanceCM="			+ mpDDistanceCMStr			+ ";");
			strBuild.append("EDistanceMM="			+ mpEDistanceMMStr			+ ";");
			strBuild.append("EntranceDoseDgy="		+ mpEntranceDoseDgyStr		+ ";");
			strBuild.append("EntranceDoseMgy="		+ mpEntranceDoseMgyStr		+ ";");
			strBuild.append("ExposedArea="			+ mpExposedAreaStr			+ ";");
			strBuild.append("RadiationMode="		+ mpRadiationModeStr		+ ";");
			strBuild.append("FilterType="			+ mpFilterTypeStr			+ ";");
			strBuild.append("FilterMaterial="		+ mpFilterMaterialStr		+ ";");
		}
		*/
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
	// buiNoStrのSET
	public void SetBuiNo( String buiNo )
	{
		if( buiNo != null )
		{
			try
			{
				Integer.parseInt(buiNo);
				this.buiNoStr = buiNo; //Exceptionが出なければBeanにSET
			}
			catch (Exception e)
			{
			}
		}
	}
	// buiNoStrのGET
	public String GetBuiNo()
	{
		return this.buiNoStr;
	}

	// noStrのGET
	public String GetBuiNoString()
	{
		if (this.buiNoStr.length() <= 1)
		{
			return "0" + this.buiNoStr;
		}
		else
		{
			return this.buiNoStr;
		}
	}
//
	// noStrのSET
	public void SetNo( String no )
	{
		if( no != null )
		{
			try
			{
				Integer.parseInt(no);
				this.noStr = no; //Exceptionが出なければBeanにSET
			}
			catch (Exception e)
			{
			}
		}
	}
	// noStrのGET
	public String GetNo()
	{
		return this.noStr;
	}
	// noStrのGET
	public String GetNoString()
	{
		if (this.noStr.length() <= 1)
		{
			return "0" + this.noStr;
		}
		else
		{
			return this.noStr;
		}
	}
//
	// satueiStatusStrのSET
	public void SetSatueiStatus( String status )
	{
		if( status != null )
		{
			this.satueiStatusStr = status;
		}
	}
	// satueiStatusStrのGET
	public String GetSatueiStatus()
	{
		return this.satueiStatusStr;
	}
	// satueiStatusStrのGET
	public String GetSatueiStatusString()
	{
		if (CommonString.SATUEISTATUS_MI.equals(this.satueiStatusStr))
		{
			return CommonString.SATUEISTATUS_MI_STR;
		}
		else if (CommonString.SATUEISTATUS_SUMI.equals(this.satueiStatusStr))
		{
			return CommonString.SATUEISTATUS_SUMI_STR;
		}
		else if (CommonString.SATUEISTATUS_STOP.equals(this.satueiStatusStr))
		{
			return CommonString.SATUEISTATUS_STOP_STR;
		}
		else
		{
			return CommonString.SATUEISTATUS_MI_STR;
		}
	}
//
	// satueiMenuIDStrのSET
	public void SetSatueiMenuID( String satueiMenuID )
	{
		if( satueiMenuID != null )
		{
			this.satueiMenuIDStr = satueiMenuID;
		}
	}
	// satueiMenuIDStrのGET
	public String GetSatueiMenuID()
	{
		return this.satueiMenuIDStr;
	}
//
	// satueiMenuNameStrのSET
	public void SetSatueiMenuName( String satueiMenuName )
	{
		if( satueiMenuName != null )
		{
			this.satueiMenuNameStr = satueiMenuName;
		}
	}
	// satueiMenuNameStrのGET
	public String GetSatueiMenuName()
	{
		return this.satueiMenuNameStr;
	}
//
	// satueiMenuNameKanaStrのSET
	public void SetSatueiMenuNameKana( String nameKana )
	{
		if( nameKana != null )
		{
			this.satueiMenuNameKanaStr = nameKana;
		}
	}
	// satueiMenuNameKanaStrのGET
	public String GetSatueiMenuNameKana()
	{
		return this.satueiMenuNameKanaStr;
	}
//
	// satueiCodeStrのSET
	public void SetSatueiCode( String satueiCode )
	{
		if( satueiCode != null )
		{
			this.satueiCodeStr = satueiCode;
		}
	}
	// satueiCodeStrのGET
	public String GetSatueiCode()
	{
		return this.satueiCodeStr;
	}
//
	// filmIDStrのSET
	public void SetFilmID( String filmID )
	{
		if( filmID != null )
		{
			this.filmIDStr = filmID;
		}
	}
	// filmIDStrのGET
	public String GetFilmID()
	{
		return this.filmIDStr;
	}
//
	// partitionStrのSET
	public void SetPartition( String partition )
	{
		if( partition != null )
		{
			try
			{
				Integer.parseInt(partition);
				this.partitionStr = partition;
			}
			catch (Exception e)
			{
			}
		}
	}
	// partitionStrのGET
	public String GetPartition()
	{
		return this.partitionStr;
	}
//
	// usedStrのSET
	public void SetUsed( String used )
	{
		if( used != null )
		{
			try
			{
				Integer.parseInt(used);
				this.usedStr = used;
			}
			catch (Exception e)
			{
			}
		}
	}
	// usedStrのGET
	public String GetUsed()
	{
		return this.usedStr;
	}
//
	// reshotFlgStrのSET]
	public void SetReshotFlg( String flg )
	{
		if( flg != null )
		{
			this.reshotFlgStr = flg;
		}
	}
	// reshotFlgStrのGET
	public String GetReshotFlg()
	{
		return this.reshotFlgStr;
	}
	// reshotFlgStrのGET
	public String GetReshotFlgString()
	{
		if (CommonString.RESHOT_FLG_ON.equals(this.reshotFlgStr))
		{
			return CommonString.RESHOT_FLG_ON_STR;
		}
		else
		{
			return CommonString.RESHOT_FLG_OFF_STR;
		}
	}
//
	// cassetteNoStrのSET
	public void SetCassetteNo( String no )
	{
		if( no != null )
		{
			this.cassetteNoStr = no;
		}
	}
	// cassetteNoStrのGET
	public String GetCassetteNo()
	{
		return this.cassetteNoStr;
	}
//
	// portableStatusStrのSET
	public void SetPortableStatus( String status )
	{
		if( status != null )
		{
			this.portableStatusStr = status;
		}
	}
	// portableStatusStrのGET
	public String GetPortableStatus()
	{
		return this.portableStatusStr;
	}
//
	// 全ExamDataをDataRowへ設定する
	public void SetExamDataToDataRow(DataRow row)
	{
		row.put(MasterUtil.RIS_EXAMDATA01, this.examData01Str);
		row.put(MasterUtil.RIS_EXAMDATA02, this.examData02Str);
		row.put(MasterUtil.RIS_EXAMDATA03, this.examData03Str);
		row.put(MasterUtil.RIS_EXAMDATA04, this.examData04Str);
		row.put(MasterUtil.RIS_EXAMDATA05, this.examData05Str);
		row.put(MasterUtil.RIS_EXAMDATA06, this.examData06Str);
		row.put(MasterUtil.RIS_EXAMDATA07, this.examData07Str);
		row.put(MasterUtil.RIS_EXAMDATA08, this.examData08Str);
		row.put(MasterUtil.RIS_EXAMDATA09, this.examData09Str);
		row.put(MasterUtil.RIS_EXAMDATA10, this.examData10Str);
		row.put(MasterUtil.RIS_EXAMDATA11, this.examData11Str);
		row.put(MasterUtil.RIS_EXAMDATA12, this.examData12Str);
		row.put(MasterUtil.RIS_EXAMDATA13, this.examData13Str);
		row.put(MasterUtil.RIS_EXAMDATA14, this.examData14Str);
		row.put(MasterUtil.RIS_EXAMDATA15, this.examData15Str);
		row.put(MasterUtil.RIS_EXAMDATA16, this.examData16Str);
		row.put(MasterUtil.RIS_EXAMDATA17, this.examData17Str);
		row.put(MasterUtil.RIS_EXAMDATA18, this.examData18Str);
		row.put(MasterUtil.RIS_EXAMDATA19, this.examData19Str);
		row.put(MasterUtil.RIS_EXAMDATA20, this.examData20Str);
	}
//
	// 全ExamDataをBeanへ設定する
	public void SetExamDataToBean(DataRow row)
	{
		this.examData01Str = row.get(MasterUtil.RIS_EXAMDATA01).toString();
		this.examData02Str = row.get(MasterUtil.RIS_EXAMDATA02).toString();
		this.examData03Str = row.get(MasterUtil.RIS_EXAMDATA03).toString();
		this.examData04Str = row.get(MasterUtil.RIS_EXAMDATA04).toString();
		this.examData05Str = row.get(MasterUtil.RIS_EXAMDATA05).toString();
		this.examData06Str = row.get(MasterUtil.RIS_EXAMDATA06).toString();
		this.examData07Str = row.get(MasterUtil.RIS_EXAMDATA07).toString();
		this.examData08Str = row.get(MasterUtil.RIS_EXAMDATA08).toString();
		this.examData09Str = row.get(MasterUtil.RIS_EXAMDATA09).toString();
		this.examData10Str = row.get(MasterUtil.RIS_EXAMDATA10).toString();
		this.examData11Str = row.get(MasterUtil.RIS_EXAMDATA11).toString();
		this.examData12Str = row.get(MasterUtil.RIS_EXAMDATA12).toString();
		this.examData13Str = row.get(MasterUtil.RIS_EXAMDATA13).toString();
		this.examData14Str = row.get(MasterUtil.RIS_EXAMDATA14).toString();
		this.examData15Str = row.get(MasterUtil.RIS_EXAMDATA15).toString();
		this.examData16Str = row.get(MasterUtil.RIS_EXAMDATA16).toString();
		this.examData17Str = row.get(MasterUtil.RIS_EXAMDATA17).toString();
		this.examData18Str = row.get(MasterUtil.RIS_EXAMDATA18).toString();
		this.examData19Str = row.get(MasterUtil.RIS_EXAMDATA19).toString();
		this.examData20Str = row.get(MasterUtil.RIS_EXAMDATA20).toString();
	}

	// 全ExamDataをBeanへ設定する
	public void SetExamDataToBean2(DataRow row)
	{
		this.examData01Str = row.get(MasterUtil.RIS_CRPRESETDATA + "01").toString();
		this.examData02Str = row.get(MasterUtil.RIS_CRPRESETDATA + "02").toString();
		this.examData03Str = row.get(MasterUtil.RIS_CRPRESETDATA + "03").toString();
		this.examData04Str = row.get(MasterUtil.RIS_CRPRESETDATA + "04").toString();
		this.examData05Str = row.get(MasterUtil.RIS_CRPRESETDATA + "05").toString();
		this.examData06Str = row.get(MasterUtil.RIS_CRPRESETDATA + "06").toString();
		this.examData07Str = row.get(MasterUtil.RIS_CRPRESETDATA + "07").toString();
		this.examData08Str = row.get(MasterUtil.RIS_CRPRESETDATA + "08").toString();
		this.examData09Str = row.get(MasterUtil.RIS_CRPRESETDATA + "09").toString();
		this.examData10Str = row.get(MasterUtil.RIS_CRPRESETDATA + "10").toString();
		this.examData11Str = row.get(MasterUtil.RIS_CRPRESETDATA + "11").toString();
		this.examData12Str = row.get(MasterUtil.RIS_CRPRESETDATA + "12").toString();
		this.examData13Str = row.get(MasterUtil.RIS_CRPRESETDATA + "13").toString();
		this.examData14Str = row.get(MasterUtil.RIS_CRPRESETDATA + "14").toString();
		this.examData15Str = row.get(MasterUtil.RIS_CRPRESETDATA + "15").toString();
		this.examData16Str = row.get(MasterUtil.RIS_CRPRESETDATA + "16").toString();
		this.examData17Str = row.get(MasterUtil.RIS_CRPRESETDATA + "17").toString();
		this.examData18Str = row.get(MasterUtil.RIS_CRPRESETDATA + "18").toString();
		this.examData19Str = row.get(MasterUtil.RIS_CRPRESETDATA + "19").toString();
		this.examData20Str = row.get(MasterUtil.RIS_CRPRESETDATA + "20").toString();
	}
//
	// examData01StrのSET
	public void SetExamData01( String data )
	{
		if( data != null )
		{
			this.examData01Str = data;
		}
	}
	// examData01StrのGET
	public String GetExamData01()
	{
		return this.examData01Str;
	}
//
	// examData02StrのSET
	public void SetExamData02( String data )
	{
		if( data != null )
		{
			this.examData02Str = data;
		}
	}
	// examData02StrのGET
	public String GetExamData02()
	{
		return this.examData02Str;
	}
//
	// examData03StrのSET
	public void SetExamData03( String data )
	{
		if( data != null )
		{
			this.examData03Str = data;
		}
	}
	// examData03StrのGET
	public String GetExamData03()
	{
		return this.examData03Str;
	}
//
	// examData04StrのSET
	public void SetExamData04( String data )
	{
		if( data != null )
		{
			this.examData04Str = data;
		}
	}
	// examData04StrのGET
	public String GetExamData04()
	{
		return this.examData04Str;
	}
//
	// examData05StrのSET
	public void SetExamData05( String data )
	{
		if( data != null )
		{
			this.examData05Str = data;
		}
	}
	// examData05StrのGET
	public String GetExamData05()
	{
		return this.examData05Str;
	}
//
	// examData06StrのSET
	public void SetExamData06( String data )
	{
		if( data != null )
		{
			this.examData06Str = data;
		}
	}
	// examData06StrのGET
	public String GetExamData06()
	{
		return this.examData06Str;
	}
//
	// examData07StrのSET
	public void SetExamData07( String data )
	{
		if( data != null )
		{
			this.examData07Str = data;
		}
	}
	// examData07StrのGET
	public String GetExamData07()
	{
		return this.examData07Str;
	}
//
	// examData08StrのSET
	public void SetExamData08( String data )
	{
		if( data != null )
		{
			this.examData08Str = data;
		}
	}
	// examData08StrのGET
	public String GetExamData08()
	{
		return this.examData08Str;
	}
//
	// examData09StrのSET
	public void SetExamData09( String data )
	{
		if( data != null )
		{
			this.examData09Str = data;
		}
	}
	// examData09StrのGET
	public String GetExamData09()
	{
		return this.examData09Str;
	}
//
	// examData10StrのSET
	public void SetExamData10( String data )
	{
		if( data != null )
		{
			this.examData10Str = data;
		}
	}
	// examData10StrのGET
	public String GetExamData10()
	{
		return this.examData10Str;
	}
//
	// examData11StrのSET
	public void SetExamData11( String data )
	{
		if( data != null )
		{
			this.examData11Str = data;
		}
	}
	// examData11StrのGET
	public String GetExamData11()
	{
		return this.examData11Str;
	}
//
	// examData12StrのSET
	public void SetExamData12( String data )
	{
		if( data != null )
		{
			this.examData12Str = data;
		}
	}
	// examData12StrのGET
	public String GetExamData12()
	{
		return this.examData12Str;
	}
//
	// examData13StrのSET
	public void SetExamData13( String data )
	{
		if( data != null )
		{
			this.examData13Str = data;
		}
	}
	// examData13StrのGET
	public String GetExamData13()
	{
		return this.examData13Str;
	}
//
	// examData14StrのSET
	public void SetExamData14( String data )
	{
		if( data != null )
		{
			this.examData14Str = data;
		}
	}
	// examData14StrのGET
	public String GetExamData14()
	{
		return this.examData14Str;
	}
//
	// examData15StrのSET
	public void SetExamData15( String data )
	{
		if( data != null )
		{
			this.examData15Str = data;
		}
	}
	// examData15StrのGET
	public String GetExamData15()
	{
		return this.examData15Str;
	}
//
	// examData16StrのSET
	public void SetExamData16( String data )
	{
		if( data != null )
		{
			this.examData16Str = data;
		}
	}
	// examData16StrのGET
	public String GetExamData16()
	{
		return this.examData16Str;
	}
//
	// examData17StrのSET
	public void SetExamData17( String data )
	{
		if( data != null )
		{
			this.examData17Str = data;
		}
	}
	// examData17StrのGET
	public String GetExamData17()
	{
		return this.examData17Str;
	}
//
	// examData18StrのSET
	public void SetExamData18( String data )
	{
		if( data != null )
		{
			this.examData18Str = data;
		}
	}
	// examData18StrのGET
	public String GetExamData18()
	{
		return this.examData18Str;
	}
//
	// examData19StrのSET
	public void SetExamData19( String data )
	{
		if( data != null )
		{
			this.examData19Str = data;
		}
	}
	// examData19StrのGET
	public String GetExamData19()
	{
		return this.examData19Str;
	}
//
	// examData20StrのSET
	public void SetExamData20( String data )
	{
		if( data != null )
		{
			this.examData20Str = data;
		}
	}
	// examData20StrのGET
	public String GetExamData20()
	{
		return this.examData20Str;
	}
//
	// kensaKikiIDStrのSET
	public void SetKensaKikiID( String kikiID )
	{
		if( kikiID != null )
		{
			this.kensaKikiIDStr = kikiID;
		}
	}
	// kensaKikiIDStrのGET
	public String GetKensaKikiID()
	{
		return this.kensaKikiIDStr;
	}
//
	// kvpStrのSET
	public void SetKVP(String kvp)
	{
		if (kvp != null)
		{
			this.kvpStr = kvp;
		}
	}
	// kvpStrのGET
	public String GetKVP()
	{
		return this.kvpStr;
	}
//
	// uaStrのSET
	public void SetuA(String ua)
	{
		if (ua != null)
		{
			this.uaStr = ua;
		}
	}
	// uaStrのGET
	public String GetuA()
	{
		return this.uaStr;
	}
//
	// msecStrのSET
	public void SetmSec(String msec)
	{
		if (msec != null)
		{
			this.msecStr = msec;
		}
	}
	// msecStrのGET
	public String GetmSec()
	{
		return this.msecStr;
	}
//
	// maStrのSET
	public void SetmA(String ma)
	{
		if (ma != null)
		{
			this.maStr = ma;
		}
	}
	// maStrのGET
	public String GetmA()
	{
		return this.maStr;
	}
//
	// secStrのSET
	public void SetSec(String sec)
	{
		if (sec != null)
		{
			this.secStr = sec;
		}
	}
	// secStrのGET
	public String GetSec()
	{
		return this.secStr;
	}
//
	// masStrのSET
	public void SetmAs(String mas)
	{
		if (mas != null)
		{
			this.masStr = mas;
		}
	}
	// masStrのGET
	public String GetmAs()
	{
		return this.masStr;
	}
//
	// 2011.02.03 Add K.Shinohara Start
	// satueiAddFlagStrのSET
	public void SetSatueiAddFlag(String satueiAddFlag)
	{
		if (satueiAddFlag != null && !"".equals(satueiAddFlag))
		{
			this.satueiAddFlagStr = satueiAddFlag;
		}
	}
	// satueiAddFlagStrのGET
	public String GetSatueiAddFlag()
	{
		return this.satueiAddFlagStr;
	}
	// 2011.02.03 Add K.Shinohara End
//
// MPPS
//
	// 全Mpps情報をBeanへ設定する
	public void SetMppsDataToBean(DataRow row)
	{
		/* 一ノ瀬保留
		if (CommonString.FLG_ON.equals(Configuration.GetInstance().GetMppsKensaFlg()))
		{
			this.kvpStr					= row.get(MasterUtil.RIS_KVP).toString();
			this.uaStr					= row.get(MasterUtil.RIS_UA).toString();
			this.msecStr				= row.get(MasterUtil.RIS_MSEC).toString();
			this.maStr					= row.get(MasterUtil.RIS_MA).toString();
			this.secStr					= row.get(MasterUtil.RIS_SEC).toString();
			this.masStr					= row.get(MasterUtil.RIS_MAS).toString();
			this.mpMppsInstanceUIDStr	= row.get(MasterUtil.RIS_MPPSINSTANCEUID).toString();
			this.mpMppsVmNoStr			= row.get(MasterUtil.RIS_MPPSVMNO).toString();
			this.mpMppsSatueiCodeStr	= row.get(MasterUtil.RIS_MPPS_SATUEI_CODE).toString();
			this.mpMppsAeTitleStr		= row.get(MasterUtil.RIS_MPPS_AE_TITLE).toString();
			this.mpXRayTubeCurrentMaStr	= row.get(MasterUtil.RIS_XRAYTUBECURRENT_MA).toString();
			this.mpExposureTimeSecStr	= row.get(MasterUtil.RIS_EXPOSURETIME_SEC).toString();
			this.mpExposureTimeMinStr	= row.get(MasterUtil.RIS_EXPOSURETIME_MIN).toString();
			this.mpKvStr				= row.get(MasterUtil.RIS_KV).toString();
			this.mpExposureNoStr		= row.get(MasterUtil.RIS_EXPOSURENO).toString();
			this.mpCTDIStr				= row.get(MasterUtil.RIS_CTDI).toString();
			this.mpDLPStr				= row.get(MasterUtil.RIS_DLP).toString();
			this.mpFluoroScopyStr		= row.get(MasterUtil.RIS_FLUOROSCOPY).toString();
			this.mpImageAreaStr			= row.get(MasterUtil.RIS_IMAGEAREA).toString();
			this.mpDDistanceMMStr		= row.get(MasterUtil.RIS_D_DISTANCE_MM).toString();
			this.mpDDistanceCMStr		= row.get(MasterUtil.RIS_D_DISTANCE_CM).toString();
			this.mpEDistanceMMStr		= row.get(MasterUtil.RIS_E_DISTANCE_MM).toString();
			this.mpEntranceDoseDgyStr	= row.get(MasterUtil.RIS_ENTRANCEDOSE_DGY).toString();
			this.mpEntranceDoseMgyStr	= row.get(MasterUtil.RIS_ENTRANCEDOSE_MGY).toString();
			this.mpExposedAreaStr		= row.get(MasterUtil.RIS_EXPOSEDAREA).toString();
			this.mpRadiationModeStr		= row.get(MasterUtil.RIS_RADIATIONMODE).toString();
			this.mpFilterTypeStr		= row.get(MasterUtil.RIS_FILTERTYPE).toString();
			this.mpFilterMaterialStr	= row.get(MasterUtil.RIS_FILTERMATERIAL).toString();
		}
		*/
	}

	// mpMppsInstanceUIDStrのSET
	public void SetMPMppsInstanceUID(String mpMppsInstanceUID)
	{
		if (mpMppsInstanceUID != null)
		{
			this.mpMppsInstanceUIDStr = mpMppsInstanceUID;
		}
	}
	// mpMppsInstanceUIDStrのGET
	public String GetMPMppsInstanceUID()
	{
		return this.mpMppsInstanceUIDStr;
	}
//
	// mpMppsVmNoStrのSET
	public void SetMPMppsVmNo(String mpMppsVmNo)
	{
		if (mpMppsVmNo != null)
		{
			this.mpMppsVmNoStr = mpMppsVmNo;
		}
	}
	// mpMppsVmNoStrのGET
	public String GetMPMppsVmNo()
	{
		return this.mpMppsVmNoStr;
	}
//
	// mpMppsSatueiCodeStrのSET
	public void SetMPMppsSatueiCode(String mpMppsSatueiCode)
	{
		if (mpMppsSatueiCode != null)
		{
			this.mpMppsSatueiCodeStr = mpMppsSatueiCode;
		}
	}
	// mpMppsSatueiCodeStrのGET
	public String GetMPMppsSatueiCode()
	{
		return this.mpMppsSatueiCodeStr;
	}
//
	// mpMppsAeTitleStrのSET
	public void SetMPMppsAeTitle(String mpMppsAeTitle)
	{
		if (mpMppsAeTitle != null)
		{
			this.mpMppsAeTitleStr = mpMppsAeTitle;
		}
	}
	// mpMppsAeTitleStrのGET
	public String GetMPMppsAeTitle()
	{
		return this.mpMppsAeTitleStr;
	}
//
	// mpXRayTubeCurrentMaStrのSET
	public void SetMPXRayTubeCurrentMa(String mpXRayTubeCurrentMa)
	{
		if (mpXRayTubeCurrentMa != null)
		{
			this.mpXRayTubeCurrentMaStr = mpXRayTubeCurrentMa;
		}
	}
	// mpXRayTubeCurrentMaStrのGET
	public String GetMPXRayTubeCurrentMa()
	{
		return this.mpXRayTubeCurrentMaStr;
	}
//
	// mpExposureTimeSecStrのSET
	public void SetMPExposureTimeSec(String mpExposureTimeSec)
	{
		if (mpExposureTimeSec != null)
		{
			this.mpExposureTimeSecStr = mpExposureTimeSec;
		}
	}
	// mpExposureTimeSecStrのGET
	public String GetMPExposureTimeSec()
	{
		return this.mpExposureTimeSecStr;
	}
//
	// mpExposureTimeMinStrのSET
	public void SetMPExposureTimeMin(String mpExposureTimeMin)
	{
		if (mpExposureTimeMin != null)
		{
			this.mpExposureTimeMinStr = mpExposureTimeMin;
		}
	}
	// mpExposureTimeMinStrのGET
	public String GetMPExposureTimeMin()
	{
		return this.mpExposureTimeMinStr;
	}
//
	// mpKvStrのSET
	public void SetMPKv(String mpKv)
	{
		if (mpKv != null)
		{
			this.mpKvStr = mpKv;
		}
	}
	// mpKvStrのGET
	public String GetMPKv()
	{
		return this.mpKvStr;
	}
//
	// mpExposureNoStrのSET
	public void SetMPExposureNo(String mpExposureNo)
	{
		if (mpExposureNo != null)
		{
			this.mpExposureNoStr = mpExposureNo;
		}
	}
	// mpExposureNoStrのGET
	public String GetMPExposureNo()
	{
		return this.mpExposureNoStr;
	}
//
	// mpCTDIStrのSET
	public void SetMPCTDI(String mpCTDI)
	{
		if (mpCTDI != null)
		{
			this.mpCTDIStr = mpCTDI;
		}
	}
	// mpCTDIStrのGET
	public String GetMPCTDI()
	{
		return this.mpCTDIStr;
	}
//
	// mpDLPStrのSET
	public void SetMPDLP(String mpDLP)
	{
		if (mpDLP != null)
		{
			this.mpDLPStr = mpDLP;
		}
	}
	// mpDLPStr
	public String GetMPDLP()
	{
		return this.mpDLPStr;
	}
//
	// mpFluoroScopyStrのSET
	public void SetMPFluoroScopy(String mpFluoroScopy)
	{
		if (mpFluoroScopy != null)
		{
			this.mpFluoroScopyStr = mpFluoroScopy;
		}
	}
	// mpFluoroScopyStr
	public String GetMPFluoroScopy()
	{
		return this.mpFluoroScopyStr;
	}
//
	// mpImageAreaStrのSET
	public void SetMPImageArea(String mpImageArea)
	{
		if (mpImageArea != null)
		{
			this.mpImageAreaStr = mpImageArea;
		}
	}
	// mpImageAreaStr
	public String GetMPImageArea()
	{
		return this.mpImageAreaStr;
	}
//
	// mpDDistanceMMStrのSET
	public void SetMPDDistanceMM(String mpDDistanceMM)
	{
		if (mpDDistanceMM != null)
		{
			this.mpDDistanceMMStr = mpDDistanceMM;
		}
	}
	// mpDDistanceMMStr
	public String GetMPDDistanceMM()
	{
		return this.mpDDistanceMMStr;
	}
//
	// mpDDistanceCMStrのSET
	public void SetMPDDistanceCM(String mpDDistanceCM)
	{
		if (mpDDistanceCM != null)
		{
			this.mpDDistanceCMStr = mpDDistanceCM;
		}
	}
	// mpDDistanceCMStr
	public String GetMPDDistanceCM()
	{
		return this.mpDDistanceCMStr;
	}
//
	// mpEDistanceMMStrのSET
	public void SetMPEDistanceMM(String mpEDistanceMM)
	{
		if (mpEDistanceMM != null)
		{
			this.mpEDistanceMMStr = mpEDistanceMM;
		}
	}
	// mpEDistanceMMStr
	public String GetMPEDistanceMM()
	{
		return this.mpEDistanceMMStr;
	}
//
	// mpEntranceDoseDgyStrのSET
	public void SetMPEntranceDoseDgy(String mpEntranceDoseDgy)
	{
		if (mpEntranceDoseDgy != null)
		{
			this.mpEntranceDoseDgyStr = mpEntranceDoseDgy;
		}
	}
	// mpEntranceDoseDgyStr
	public String GetMPEntranceDoseDgy()
	{
		return this.mpEntranceDoseDgyStr;
	}
//
	// mpEntranceDoseMgyStrのSET
	public void SetMPEntranceDoseMgy(String mpEntranceDoseMgy)
	{
		if (mpEntranceDoseMgy != null)
		{
			this.mpEntranceDoseMgyStr = mpEntranceDoseMgy;
		}
	}
	// mpEntranceDoseMgyStr
	public String GetMPEntranceDoseMgy()
	{
		return this.mpEntranceDoseMgyStr;
	}
//
	// mpExposedAreaStrのSET
	public void SetMPExposedArea(String mpExposedArea)
	{
		if (mpExposedArea != null)
		{
			this.mpExposedAreaStr = mpExposedArea;
		}
	}
	// mpExposedAreaStr
	public String GetMPExposedArea()
	{
		return this.mpExposedAreaStr;
	}
//
	// mpRadiationModeStrのSET
	public void SetMPRadiationMode(String mpRadiationMode)
	{
		if (mpRadiationMode != null)
		{
			this.mpRadiationModeStr = mpRadiationMode;
		}
	}
	// mpRadiationModeStr
	public String GetMPRadiationMode()
	{
		return this.mpRadiationModeStr;
	}
//
	// mpFilterTypeStrのSET
	public void SetMPFilterType(String mpFilterType)
	{
		if (mpFilterType != null)
		{
			this.mpFilterTypeStr = mpFilterType;
		}
	}
	// mpFilterTypeStr
	public String GetMPFilterType()
	{
		return this.mpFilterTypeStr;
	}
//
	// mpFilterMaterialStrのSET
	public void SetMPFilterMaterial(String mpFilterMaterial)
	{
		if (mpFilterMaterial != null)
		{
			this.mpFilterMaterialStr = mpFilterMaterial;
		}
	}
	// mpFilterMaterialStr
	public String GetMPFilterMaterial()
	{
		return this.mpFilterMaterialStr;
	}

	// 全Mpps情報をBeanへ設定する
	public ExSatueiBean copy() {

		ExSatueiBean satueiBean = new ExSatueiBean();

		satueiBean.risIDStr					= this.risIDStr;
		satueiBean.buiNoStr					= this.buiNoStr;
		satueiBean.noStr					= this.noStr;
		satueiBean.satueiStatusStr			= this.satueiStatusStr;
		satueiBean.satueiMenuIDStr			= this.satueiMenuIDStr;
		satueiBean.satueiMenuNameStr		= this.satueiMenuNameStr;
		satueiBean.satueiMenuNameKanaStr	= this.satueiMenuNameKanaStr;
		satueiBean.satueiCodeStr			= this.satueiCodeStr;
		satueiBean.filmIDStr				= this.filmIDStr;
		satueiBean.partitionStr				= this.partitionStr;
		satueiBean.usedStr					= this.usedStr;
		satueiBean.reshotFlgStr				= this.reshotFlgStr;
		satueiBean.cassetteNoStr			= this.cassetteNoStr;
		satueiBean.portableStatusStr		= this.portableStatusStr;
		satueiBean.examData01Str 			= this.examData01Str;
		satueiBean.examData02Str 			= this.examData02Str;
		satueiBean.examData03Str 			= this.examData03Str;
		satueiBean.examData04Str 			= this.examData04Str;
		satueiBean.examData05Str 			= this.examData05Str;
		satueiBean.examData06Str 			= this.examData06Str;
		satueiBean.examData07Str 			= this.examData07Str;
		satueiBean.examData08Str 			= this.examData08Str;
		satueiBean.examData09Str 			= this.examData09Str;
		satueiBean.examData10Str 			= this.examData10Str;
		satueiBean.examData11Str 			= this.examData11Str;
		satueiBean.examData12Str 			= this.examData12Str;
		satueiBean.examData13Str 			= this.examData13Str;
		satueiBean.examData14Str 			= this.examData14Str;
		satueiBean.examData15Str 			= this.examData15Str;
		satueiBean.examData16Str 			= this.examData16Str;
		satueiBean.examData17Str 			= this.examData17Str;
		satueiBean.examData18Str 			= this.examData18Str;
		satueiBean.examData19Str 			= this.examData19Str;
		satueiBean.examData20Str 			= this.examData20Str;
		satueiBean.kensaKikiIDStr			= this.kensaKikiIDStr;
		satueiBean.kvpStr					= this.kvpStr;
		satueiBean.uaStr					= this.uaStr;
		satueiBean.msecStr					= this.msecStr;
		satueiBean.maStr					= this.maStr;
		satueiBean.secStr					= this.secStr;
		satueiBean.masStr					= this.masStr;
		satueiBean.satueiAddFlagStr			= this.satueiAddFlagStr;
		satueiBean.mpMppsInstanceUIDStr		= this.mpMppsInstanceUIDStr;
		satueiBean.mpMppsVmNoStr			= this.mpMppsVmNoStr;
		satueiBean.mpMppsSatueiCodeStr 		= this.mpMppsSatueiCodeStr;
		satueiBean.mpMppsAeTitleStr 		= this.mpMppsAeTitleStr;
		satueiBean.mpXRayTubeCurrentMaStr	= this.mpXRayTubeCurrentMaStr;
		satueiBean.mpExposureTimeSecStr		= this.mpExposureTimeSecStr;
		satueiBean.mpExposureTimeMinStr		= this.mpExposureTimeMinStr;
		satueiBean.mpKvStr 					= this.mpKvStr;
		satueiBean.mpExposureNoStr 			= this.mpExposureNoStr;
		satueiBean.mpCTDIStr 				= this.mpCTDIStr;
		satueiBean.mpDLPStr 				= this.mpDLPStr;
		satueiBean.mpFluoroScopyStr 		= this.mpFluoroScopyStr;
		satueiBean.mpImageAreaStr 			= this.mpImageAreaStr;
		satueiBean.mpDDistanceMMStr 		= this.mpDDistanceMMStr;
		satueiBean.mpDDistanceCMStr			= this.mpDDistanceCMStr;
		satueiBean.mpEDistanceMMStr			= this.mpEDistanceMMStr;
		satueiBean.mpEntranceDoseDgyStr		= this.mpEntranceDoseDgyStr;
		satueiBean.mpEntranceDoseMgyStr		= this.mpEntranceDoseMgyStr;
		satueiBean.mpExposedAreaStr			= this.mpExposedAreaStr;
		satueiBean.mpRadiationModeStr		= this.mpRadiationModeStr;
		satueiBean.mpFilterTypeStr			= this.mpFilterTypeStr;
		satueiBean.mpFilterMaterialStr		= this.mpFilterMaterialStr;

		return satueiBean;
	}
}
