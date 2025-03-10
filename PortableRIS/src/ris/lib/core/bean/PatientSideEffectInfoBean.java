package ris.lib.core.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import ris.lib.core.util.CommonString;
import ris.portable.common.Const;

/// <summary>
/// PatientSideEffectInfoBean の概要の説明です。
/// </summary>
public class PatientSideEffectInfoBean
{
	// private members
	private String sideEffectIDStr			= "";
	private String kanjaIDStr				= "";
	private String zoueizaiIDStr			= "";
	private Timestamp episodeDateTime		= Const.TIMESTAMP_MINVALUE;
	private String gradeStr					= "";
	private String commentID1Str			= "";
	private String commentID2Str			= "";
	private String commentID3Str			= "";
	private String commentID4Str			= "";
	private String commentID5Str			= "";
	private String freeCommentStr			= "";
	private Timestamp updateDateTime		= Const.TIMESTAMP_MINVALUE;
	private String updateUserStr			= "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public PatientSideEffectInfoBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[PatientSideEffectInfoBean]");
		strBuild.append("SideEffect_ID	="			+ sideEffectIDStr	 + ";");
		strBuild.append("Kanja_ID		="			+ kanjaIDStr		 + ";");
		strBuild.append("Zoueizai_ID	="			+ zoueizaiIDStr		 + ";");
		strBuild.append("EpisodeDate	="			+ episodeDateTime	 + ";");
		strBuild.append("Grade			="			+ gradeStr			 + ";");
		strBuild.append("Comment_ID1	="			+ commentID1Str		 + ";");
		strBuild.append("Comment_ID2	="			+ commentID2Str		 + ";");
		strBuild.append("Comment_ID3	="			+ commentID3Str		 + ";");
		strBuild.append("Comment_ID4	="			+ commentID4Str		 + ";");
		strBuild.append("Comment_ID5	="			+ commentID5Str		 + ";");
		strBuild.append("FreeComment	="			+ freeCommentStr	 + ";");
		strBuild.append("UpdateDate		="			+ updateDateTime	 + ";");
		strBuild.append("UpdateUser		="			+ updateUserStr		 + ";");

		return strBuild.toString();
	}

	// sideEffectIDStr
	// sideEffectIDStrのSET
	public void SetSideEffectID(String sideEffectID)
	{
		if (sideEffectID != null)
		{
			this.sideEffectIDStr = sideEffectID;
		}
	}
	// sideEffectIDStrのGET
	public String GetSideEffectID()
	{
		return this.sideEffectIDStr;
	}


	// kanjaIDStr
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


	// zoueizaiIDStr
	// zoueizaiIDStrのSET
	public void SetZoueizaiID(String zoueizaiID)
	{
		if (zoueizaiID != null)
		{
			this.zoueizaiIDStr = zoueizaiID;
		}
	}
	// zoueizaiIDStrのGET
	public String GetZoueizaiID()
	{
		return this.zoueizaiIDStr;
	}


	// episodeTimestamp
	// episodeTimestampのSET
	public void SetEpisodeDateTime(String episodeDate)
	{
		if (episodeDate != null)
		{
			try {
				Timestamp workDate = Timestamp.valueOf(episodeDate);
				this.episodeDateTime = workDate;
			} catch (Exception e) {
				this.episodeDateTime = Const.TIMESTAMP_MINVALUE;
			}
		}
	}
	// episodeTimestampのGET
	public Timestamp GetEpisodeDateTime()
	{
		return this.episodeDateTime;
	}
	//
	// episodeTimestamp(hh:mm)のGET
	public String GetEpisodeDateTimeFormat()
	{
		if (episodeDateTime != null && !Const.TIMESTAMP_MINVALUE.equals(episodeDateTime) )
		{
			//検査時刻を変換する
			return new SimpleDateFormat(CommonString.LIST_FORMAT_TIME_0).format(this.episodeDateTime);
		}
		else
		{
			return "";
		}
	}


	// gradeStr
	// gradeStrのSET
	public void SetGrade(String grade)
	{
		if (grade != null)
		{
			this.gradeStr = grade;
		}
	}
	// gradeStrのGET
	public String GetGrade()
	{
		return this.gradeStr;
	}


	// commentID1Str
	// commentID1StrのSET
	public void SetCommentID1(String commentID1)
	{
		if (commentID1 != null)
		{
			this.commentID1Str = commentID1;
		}
	}
	// commentID1StrのGET
	public String GetCommentID1()
	{
		return this.commentID1Str;
	}


	// commentID2Str
	// commentID2StrのSET
	public void SetCommentID2(String commentID2)
	{
		if (commentID2 != null)
		{
			this.commentID2Str = commentID2;
		}
	}
	// commentID2StrのGET
	public String GetCommentID2()
	{
		return this.commentID2Str;
	}


	// commentID3Str
	// commentID3StrのSET
	public void SetCommentID3(String commentID3)
	{
		if (commentID3 != null)
		{
			this.commentID3Str = commentID3;
		}
	}
	// commentID3StrのGET
	public String GetCommentID3()
	{
		return this.commentID3Str;
	}


	// commentID4Str
	// commentID4StrのSET
	public void SetCommentID4(String commentID4)
	{
		if (commentID4 != null)
		{
			this.commentID4Str = commentID4;
		}
	}
	// commentID4StrのGET
	public String GetCommentID4()
	{
		return this.commentID4Str;
	}


	// commentID5Str
	// commentID5StrのSET
	public void SetCommentID5(String commentID5)
	{
		if (commentID5 != null)
		{
			this.commentID5Str = commentID5;
		}
	}
	// commentID5StrのGET
	public String GetCommentID5()
	{
		return this.commentID5Str;
	}


	// freeCommentStr
	// freeCommentStrのSET
	public void SetFreeComment(String freeComment)
	{
		if (freeComment != null)
		{
			this.freeCommentStr = freeComment;
		}
	}
	// freeCommentStrのGET
	public String GetFreeComment()
	{
		return this.freeCommentStr;
	}


	// updateTimestamp
	// updateTimestampのSET
	public void SetUpdateDateTime(String updateDate)
	{
		if (updateDate != null)
		{
			try {
				Timestamp workDate = Timestamp.valueOf(updateDate);
				this.updateDateTime = workDate;
			} catch (Exception e) {
				this.updateDateTime = Const.TIMESTAMP_MINVALUE;
			}
		}
	}
	// updateTimestampのGET
	public Timestamp GetUpdateDateTime()
	{
		return this.updateDateTime;
	}
	// updateTimestamp(hh:mm)のGET
	public String GetUpdateDateTimeFormat()
	{
		if (updateDateTime != null && !Const.TIMESTAMP_MINVALUE.equals(updateDateTime) )
		{
			//検査時刻を変換する
			return new SimpleDateFormat(CommonString.LIST_FORMAT_TIME_0).format(this.updateDateTime);
		}
		else
		{
			return "";
		}
	}


	// updateUserStr
	// updateUserStrのSET
	public void SetUpdateUser(String updateUser)
	{
		if (updateUser != null)
		{
			this.updateUserStr = updateUser;
		}
	}
	// updateUserStrのGET
	public String GetUpdateUser()
	{
		return this.updateUserStr;
	}


}
