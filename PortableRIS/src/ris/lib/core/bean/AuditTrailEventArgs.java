package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.portable.common.Const;

/// <summary>
///
/// AuditTrailEventArgs情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.18	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class AuditTrailEventArgs // extends System.EventArgs
{
	// private members
	private boolean     resultFlg		= true;
	private String   patientIDStr	= "";
	private String   patientNameStr = "";
	private String   orderNoStr		= "";
	private Timestamp studyDateDT	= Const.TIMESTAMP_MINVALUE;
	private String   modalityStr	= "";
	private String   actiontNameStr	= "";
	private String   sqlStr			= "";
	private String   windowIDStr	= "";
	private String   screenStr		= "";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public AuditTrailEventArgs()
	{
		//
	}
	//
	// resultFlgのSET
	public void SetResultFlg( boolean flg )
	{
		this.resultFlg = flg;
	}
	// resultFlgのGET
	public boolean GetResultFlg()
	{
		return this.resultFlg;
	}

	// patientIDStrのSET
	public void SetPatientID( String patientID )
	{
		if( patientID != null )
		{
			this.patientIDStr = patientID;
		}
	}
	// patientIDStrのGET
	public String GetPatientID()
	{
		return this.patientIDStr;
	}

	// patientNameStrのSET
	public void SetPatientName( String patientName )
	{
		if( patientName != null )
		{
			this.patientNameStr = patientName;
		}
	}
	// patientNameStrのGET
	public String GetPatientName()
	{
		return this.patientNameStr;
	}

	// orderNoStrのSET
	public void SetOrderNo(String orderNo)
	{
		if (orderNo != null)
		{
			this.orderNoStr = orderNo;
		}
	}
	// orderNoStrのGET
	public String GetOrderNo()
	{
		return this.orderNoStr;
	}

	// studyDateDTのSET
	public void SetStudyDate( Timestamp studyDate )
	{
		if( !Const.TIMESTAMP_MINVALUE.equals(studyDate) )
		{
			this.studyDateDT = studyDate;
		}
	}
	// studyDateDTのGET
	public Timestamp GetStudyDate()
	{
		return this.studyDateDT;
	}

	// modalityStrのSET
	public void SetModality( String modality )
	{
		if( modality != null )
		{
			this.modalityStr = modality;
		}
	}
	// modalityStrのGET
	public String GetModality()
	{
		return this.modalityStr;
	}

	// actiontNameStrのSET
	public void SetAction( String actiontName )
	{
		if( actiontName != null )
		{
			this.actiontNameStr = actiontName;
		}
	}
	// actiontNameStrのGET
	public String GetAction()
	{
		return this.actiontNameStr;
	}

	// sqlStrのSET
	public void SetSql(String sql)
	{
		if (sql != null)
		{
			this.sqlStr = sql;
		}
	}
	// sqlStrのGET
	public String GetSql()
	{
		return this.sqlStr;
	}

	// windowIDStrのSET
	public void SetWindowID(String windowID)
	{
		if (windowID != null)
		{
			this.windowIDStr = windowID;
		}
	}
	// windowIDStrのGET
	public String GetWindowID()
	{
		return this.windowIDStr;
	}

	// screenStrのSET
	public void SetScreen(String screen)
	{
		if (screen != null)
		{
			this.screenStr = screen;
		}
	}
	// screenStrのGET
	public String GetScreen()
	{
		return this.screenStr;
	}
}
