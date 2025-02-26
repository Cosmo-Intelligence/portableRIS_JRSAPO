package ris.lib.core.bean;

/// <summary>
/// ExFilmBean の概要の説明です。
/// </summary>
public class ExFilmBean
{
	// private members
	private String risIDStr					=  ""; //Ris.ExFilmTable.RIS_ID
	private String buiNoStr					= "0"; //Ris.ExFilmTable.Bui_No
	private String noStr					= "0"; //Ris.ExFilmTable.No
	private String filmIDStr				=  ""; //Ris.ExFilmTable.Film_ID
	private String partitionStr				=  ""; //Ris.ExFilmTable.Partition
	private String usedStr					=  ""; //Ris.ExFilmTable.Used
	private String lossStr					=  ""; //Ris.ExFilmTable.Loss

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ExFilmBean()
	{
		//
	}

	@Override
	public String toString()
	{
		StringBuilder strBuild = new StringBuilder();

		strBuild.append(">>>>>OBJ_DUMP<<<<<");
		strBuild.append("[ExFilmBean]");
		strBuild.append("RIS_ID="				+ risIDStr		+ ";");
		strBuild.append("Bui_No="				+ buiNoStr		+ ";");
		strBuild.append("No="					+ noStr			+ ";");
		strBuild.append("Film_ID="				+ filmIDStr		+ ";");
		strBuild.append("Partition="			+ partitionStr	+ ";");
		strBuild.append("Used="					+ usedStr		+ ";");
		strBuild.append("Loss="					+ lossStr		+ ";");

		return strBuild.toString();
	}

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
			this.buiNoStr = buiNo;
		}
	}
	public void SetBuiNo( Integer buiNo )
	{
		if( buiNo >= 0 )
		{
			this.buiNoStr = buiNo.toString();
		}
	}
	// buiNoStrのGET
	public String GetBuiNo()
	{
		return this.buiNoStr;
	}
	// buiNoStrのGET
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
			this.noStr = no;
		}
	}
	public void SetNo( Integer no )
	{
		if( no >= 0 )
		{
			this.noStr = no.toString();
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
	// filmIDStrのSET
	public void SetFilmID(String filmID)
	{
		if (filmID != null)
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
	public void SetPartition(String partition)
	{
		if (partition != null)
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
	// partitionStrのGET
	public Integer GetPartitionInt()
	{
		Integer retInt = 0;
		try
		{
			if (this.partitionStr.length() > 0)
			{
				retInt = Integer.parseInt(this.partitionStr);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	// usedStrのSET
	public void SetUsed(String used)
	{
		if (used != null)
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
	// usedStrのGET
	public Integer GetUsedInt()
	{
		Integer retInt = 0;
		try
		{
			if (this.usedStr.length() > 0)
			{
				retInt = Integer.parseInt(this.usedStr);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	// lossStrのSET
	public void SetLoss(String loss)
	{
		if (loss != null)
		{
			try
			{
				Integer.parseInt(loss);
				this.lossStr = loss;
			}
			catch (Exception e)
			{
			}
		}
	}
	// lossStrのGET
	public String GetLoss()
	{
		return this.lossStr;
	}
}
