package ris.lib.core.bean;

import ris.portable.common.Const;

/// <summary>
///
/// CSV出力Bean
///
/// Copyright (C) 2010, Yokogawa Electric Corpration
///
/// (Rev.)         (Date)           (ID / NAME)         (Comment)
/// V1.00.00	: 2010.07.30	: 999999 / T.Nishikubo	: original
///
/// </summary>
public class OutputCsvBean
{
	// private members
	private String			kensaTypeStr		= "";				// 検査種別
	private String			houhouStr			= "";				// 方法
	private String			buiBunruiStr		= "";				// 部位分類
	private String			buiStr				= "";				// 部位
	private String			kizaiNameStr		= "";				// 名称
	private double			kizaiSuryoDbl		= Const.DOUBLE_MINVALUE;	// 数量
	private String			kizaiTaniStr		= "";				// 単位
	private double			kizaiIjiSuDbl		= Const.DOUBLE_MINVALUE;	// 医事数量
	private String			kizaiIjiTaniStr		= "";				// 医事単位
	private String			filmNameStr			= "";				// フィルム名
	private int				filmCountInt		= Const.INT_MINVALUE;		// 枚数
	private int				filmLossInt			= Const.INT_MINVALUE;		// ロス

	private int				nyuuinCountInt		= Const.INT_MINVALUE;		// 入院数
	private int				gairaiCountInt		= Const.INT_MINVALUE;		// 外来数
	private int				goukeiCountInt		= Const.INT_MINVALUE;		// 合計

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public OutputCsvBean()
	{
		//
	}

	//
	// 検査種別のSET
	public void SetkensaType(String kensaTypeStr)
	{
		if (kensaTypeStr != null)
		{
			this.kensaTypeStr = kensaTypeStr;
		}
	}
	// 検査種別のGET
	public String GetKensaType()
	{
		return this.kensaTypeStr;
	}
	//
	// 方法SET
	public void SetHouhou(String houhouStr)
	{
		if (houhouStr != null)
		{
			this.houhouStr = houhouStr;
		}
	}
	// 方法GET
	public String GetHouhou()
	{
		return this.houhouStr;
	}
	//
	// 部位分類SET
	public void SetBuiBunrui(String buiBunruiStr)
	{
		if (buiBunruiStr != null)
		{
			this.buiBunruiStr = buiBunruiStr;
		}
	}
	// 部位分類GET
	public String GetBuiBunrui()
	{
		return this.buiBunruiStr;
	}
	//
	// 部位SET
	public void SetBui(String buiStr)
	{
		if (buiStr != null)
		{
			this.buiStr = buiStr;
		}
	}
	// 部位GET
	public String GetBui()
	{
		return this.buiStr;
	}
	//
	// 名称SET
	public void SetKizaiName(String kizaiNameStr)
	{
		if (kizaiNameStr != null)
		{
			this.kizaiNameStr = kizaiNameStr;
		}
	}
	// 名称GET
	public String GetKizaiName()
	{
		return this.kizaiNameStr;
	}
	//
	// 数量SET
	public void SetKizaiSuryo(double kizaiSuryoDbl)
	{
		this.kizaiSuryoDbl = kizaiSuryoDbl;
	}
	// 数量GET
	public double GetKizaiSuryo()
	{
		return this.kizaiSuryoDbl;
	}
	//
	// 単位SET
	public void SetKizaiTani(String kizaiTaniStr)
	{
		if (kizaiTaniStr != null)
		{
			this.kizaiTaniStr = kizaiTaniStr;
		}
	}
	// 単位GET
	public String GetKizaiTani()
	{
		return this.kizaiTaniStr;
	}
	//
	// 医事数量SET
	public void SetKizaiIjiSu(double kizaiIjiSuDbl)
	{
		this.kizaiIjiSuDbl = kizaiIjiSuDbl;
	}
	// 医事数量GET
	public double GetKizaiIjiSu()
	{
		return this.kizaiIjiSuDbl;
	}
	//
	// 医事単位SET
	public void SetKizaiIjiTani(String kizaiIjiTaniStr)
	{
		if (kizaiIjiTaniStr != null)
		{
			this.kizaiIjiTaniStr = kizaiIjiTaniStr;
		}
	}
	// 医事単位GET
	public String GetKizaiIjiTani()
	{
		return this.kizaiIjiTaniStr;
	}
	//
	// フィルム名SET
	public void SetFilmName(String filmNameStr)
	{
		if (filmNameStr != null)
		{
			this.filmNameStr = filmNameStr;
		}
	}
	// フィルム名GET
	public String GetFilmName()
	{
		return this.filmNameStr;
	}
	//
	// 枚数SET
	public void SetFilmCount(Integer filmCountInt)
	{
		this.filmCountInt = filmCountInt;
	}
	// 枚数GET
	public Integer GetFilmCount()
	{
		return this.filmCountInt;
	}
	//
	// ロスSET
	public void SetFilmLoss(Integer filmLossInt)
	{
		this.filmLossInt = filmLossInt;
	}
	// ロスGET
	public Integer GetFilmLoss()
	{
		return this.filmLossInt;
	}

	//
	// 入院数のSET
	public void SetNyuuinCount(Integer nyuuinCountInt)
	{
		this.nyuuinCountInt = nyuuinCountInt;
	}
	// 入院数のGET
	public Integer GetNyuuinCount()
	{
		return this.nyuuinCountInt;
	}
	//
	// 外来数のSET
	public void SetGairaiCount(Integer gairaiCountInt)
	{
		this.gairaiCountInt = gairaiCountInt;
	}
	// 外来数のGET
	public Integer GetGairaiCount()
	{
		return this.gairaiCountInt;
	}
	//
	// 合計のSET
	public void SetGoukeiCount(Integer goukeiCountInt)
	{
		this.goukeiCountInt = goukeiCountInt;
	}
	// 合計のGET
	public Integer GetGoukeiCount()
	{
		return this.goukeiCountInt;
	}

}
