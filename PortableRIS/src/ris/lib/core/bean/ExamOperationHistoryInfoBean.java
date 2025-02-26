package ris.lib.core.bean;

/// <summary>
///
/// ExamOperationHistoryテーブル情報クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.14	: 112478 / A.Kobayashi	: original
/// V2.01.00    extends 2011.08.16    extends 999999 / DD.Chinh     extends NML-CAT2-010
///
/// </summary>
public class ExamOperationHistoryInfoBean
{
	// private members
	private String logIDStr			= "";	//Ris.ExamOperationHistory.LOG_ID
	private String risIDStr			= "";	//Ris.ExamOperationHistory.RIS_ID
	private Integer operationTypeInt	= 0;	//Ris.ExamOperationHistory.OPERATIONTYPE
	private String operatorStr		= "";	//Ris.ExamOperationHistory.OPERATOR
	private String operatorminalStr = "";	//Ris.ExamOperationHistory.OPERATIONTERMINAL
	// 2011.08.16 Add DD.Chinh@MERX Start NML-CAT2-010
	private String operatorAuth		= "";
	// 2011.08.16 Add DD.Chinh@MERX End

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExamOperationHistoryInfoBean( )
	{
		//
	}
	//
	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExamOperationHistory]");
		strBuild.append("logID="			+ logIDStr			+ ";");
		strBuild.append("RIS_ID="			+ risIDStr			+ ";");
		strBuild.append("operationType="	+ operationTypeInt	+ ";");
		strBuild.append("operator="			+ operatorStr		+ ";");
		strBuild.append("operatorminal="	+ operatorminalStr	+ ";");
		// 2011.08.16 Add DD.Chinh@MERX Start NML-CAT2-010
		strBuild.append("operatorAuth="		+ operatorAuth		+ ";");
		// 2011.08.16 Add DD.Chinh@MERX End
		return strBuild.toString();
	}
	//
	// logIDStrのGET
	public String GetLogID()
	{
		return this.logIDStr;
	}
	// logIDStrのSET
	public void SetLogID( String logID )
	{
		if( logID != null )
		{
			try
			{
				Integer.parseInt(logID);
				this.logIDStr = logID;
			}
			catch( Exception ex )
			{
			}
		}
	}
	//
	// risIDStrのGET
	public String GetRisID()
	{
		return this.risIDStr;
	}
	// risIDStrのSET
	public void SetRisID( String risID )
	{
		if( risID != null )
		{
			this.risIDStr = risID;
		}
	}
	//
	// operationTypeIntのGET
	public Integer GetOperationType()
	{
		return this.operationTypeInt;
	}
	// operationTypeStrのSET
	public void SetOperationType(Integer operationType )
	{
		this.operationTypeInt = operationType;
	}
	//
	// operatorStrのGET
	public String GetOperator()
	{
		return this.operatorStr;
	}
	// operatorStrのSET
	public void SetOperator( String tmpOperatorStr )
	{
		if( tmpOperatorStr != null )
		{
			this.operatorStr = tmpOperatorStr;
		}
	}
	//
	// operatorminalStrのGET
	public String GetOperatorminal()
	{
		return this.operatorminalStr;
	}
	// operatorminalStrのSET
	public void SetOperatorminal( String operatorminal )
	{
		if( operatorminal != null )
		{
			this.operatorminalStr = operatorminal;
		}
	}
	// 2011.08.16 Add DD.Chinh@MERX Start NML-CAT2-010
	// operatorAuthのGET
	public String GetOperatorAuth()
	{
		return this.operatorAuth;
	}
	// operatorAuthのSET
	public void SetOperatorAuth(String tmpOperatorAuthStr)
	{
		if (tmpOperatorAuthStr != null)
		{
			this.operatorAuth = tmpOperatorAuthStr;
		}
	}
	// 2011.08.16 Add DD.Chinh@MERX End
}
