package ris.lib.core.util;

import java.util.ArrayList;

import ris.portable.util.DataTable;

/// <summary>
///
/// MWM条件クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.00.00	: 2009.03.02	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MWMParameter
{
	// private members
	private String		mwmModeStr		= "";
	private DataTable	orderDt			= null;
	private DataTable	buiDt			= null;
	private DataTable	satueiDt		= null;
	private ArrayList	risIDList		= null;
	private boolean		allFlgBool		= false;
	private String		gisiIDStr		= "";
	private String		gisiNameStr		= "";
	private String		kikiIDStr		= "";
	DataTable			mstKensatypeDt	= null;
	DataTable			mstKensakikiDt	= null;
	DataTable			mstBuiDt		= null;
	DataTable			mstHoukouDt		= null;
	DataTable			mstKHouhouDt	= null;
	DataTable			mstDoctorDt		= null;
	DataTable			mstSectionDt	= null;
	// 2010.09.01 Add K.Shinohara Start
	// 撮影系・検査系を判別
	private String		opeKbn			= "";
	// 2010.09.01 Add K.Shinohara End
//
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MWMParameter()
	{
		//
	}
//
	/// <summary>
	/// MWMモードを取得する
	/// </summary>
	/// <returns>MWMモード</returns>
	public String GetMWMMode()
	{
		return this.mwmModeStr;
	}
	/// <summary>
	/// MWMモードを設定する
	/// </summary>
	/// <param name="mwmMode">MWMモード</param>
	public void SetMWMMode(String mwmMode)
	{
		this.mwmModeStr = mwmMode;
	}
//
	/// <summary>
	/// オーダ情報を取得する
	/// </summary>
	/// <returns>オーダ情報</returns>
	public DataTable GetOrderDt()
	{
		return this.orderDt;
	}
	/// <summary>
	/// オーダ情報を設定する
	/// </summary>
	/// <param name="order">オーダ情報</param>
	public void SetOrderDt(DataTable order)
	{
		this.orderDt = order;
	}
//
	/// <summary>
	/// 部位情報を取得する
	/// </summary>
	/// <returns>部位情報</returns>
	public DataTable GetBuiDt()
	{
		return this.buiDt;
	}
	/// <summary>
	/// 部位情報を設定する
	/// </summary>
	/// <param name="buiDatatable">部位情報</param>
	public void SetBuiDt(DataTable buiDatatable)
	{
		this.buiDt = buiDatatable;
	}
//
	/// <summary>
	/// 撮影情報を取得する
	/// </summary>
	/// <returns>撮影情報</returns>
	public DataTable GetSatueiDt()
	{
		return this.satueiDt;
	}
	/// <summary>
	/// 撮影情報を設定する
	/// </summary>
	/// <param name="satueiDatatable">撮影情報</param>
	public void SetSatueiDt(DataTable satueiDatatable)
	{
		this.satueiDt = satueiDatatable;
	}
//
	/// <summary>
	/// RISIDリストを取得する
	/// </summary>
	/// <returns>RISIDリスト</returns>
	public ArrayList GetRisIDList()
	{
		return this.risIDList;
	}

	/// <summary>
	/// RISIDリストを設定する
	/// </summary>
	/// <param name="risIDLists">RISIDリスト</param>
	public void SetRisIDList(ArrayList risIDLists)
	{
		this.risIDList = risIDLists;
	}
//
	/// <summary>
	/// 全受付フラグを取得する
	/// </summary>
	/// <returns>全受付フラグ</returns>
	public boolean GetAllFlg()
	{
		return this.allFlgBool;
	}

	/// <summary>
	/// 全受付フラグを設定する
	/// </summary>
	/// <param name="allFlg">全受付フラグ</param>
	public void SetAllFlg(boolean allFlg)
	{
		this.allFlgBool = allFlg;
	}
//
	/// <summary>
	/// 技師IDを取得する
	/// </summary>
	/// <returns>技師ID</returns>
	public String GetGisiID()
	{
		return this.gisiIDStr;
	}

	/// <summary>
	/// 技師IDを設定する
	/// </summary>
	/// <param name="gisiID">技師ID</param>
	public void SetGisiID(String gisiID)
	{
		this.gisiIDStr = gisiID;
	}
//
	/// <summary>
	/// 技師名を取得する
	/// </summary>
	/// <returns>技師名</returns>
	public String GetGisiName()
	{
		return this.gisiNameStr;
	}

	/// <summary>
	/// 技師名を設定する
	/// </summary>
	/// <param name="gisiName">技師名</param>
	public void SetGisiName(String gisiName)
	{
		this.gisiNameStr = gisiName;
	}
//
	/// <summary>
	/// 検査機器IDを取得する
	/// </summary>
	/// <returns>検査機器ID</returns>
	public String GetKikiID()
	{
		return this.kikiIDStr;
	}

	/// <summary>
	/// 検査機器IDを設定する
	/// </summary>
	/// <param name="kikiID">検査機器ID</param>
	public void SetKikiID(String kikiID)
	{
		this.kikiIDStr = kikiID;
	}
//
	/// <summary>
	/// 検査種別マスタ情報を取得する
	/// </summary>
	/// <returns>検査種別マスタ情報</returns>
	public DataTable GetMstKensatype()
	{
		return this.mstKensatypeDt;
	}

	/// <summary>
	/// 検査種別マスタ情報を設定する
	/// </summary>
	/// <param name="mstKensatype">検査種別マスタ情報</param>
	public void SetMstKensatype(DataTable mstKensatype)
	{
		this.mstKensatypeDt = mstKensatype;
	}
//
	/// <summary>
	/// 検査機器マスタ情報を取得する
	/// </summary>
	/// <returns>検査機器マスタ情報</returns>
	public DataTable GetMstKensakiki()
	{
		return this.mstKensakikiDt;
	}

	/// <summary>
	/// 検査機器マスタ情報を設定する
	/// </summary>
	/// <param name="mstKensakiki">検査機器マスタ情報</param>
	public void SetMstKensakiki(DataTable mstKensakiki)
	{
		this.mstKensakikiDt = mstKensakiki;
	}
//
	/// <summary>
	/// 部位マスタ情報を取得する
	/// </summary>
	/// <returns>部位マスタ情報</returns>
	public DataTable GetMstBui()
	{
		return this.mstBuiDt;
	}

	/// <summary>
	/// 部位マスタ情報を設定する
	/// </summary>
	/// <param name="mstBui">部位マスタ情報</param>
	public void SetMstBui(DataTable mstBui)
	{
		this.mstBuiDt = mstBui;
	}
//
	/// <summary>
	/// 方向マスタ情報を取得する
	/// </summary>
	/// <returns>方向マスタ情報</returns>
	public DataTable GetMstHoukou()
	{
		return this.mstHoukouDt;
	}

	/// <summary>
	/// 方向マスタ情報を設定する
	/// </summary>
	/// <param name="mstHoukou">方向マスタ情報</param>
	public void SetMstHoukou(DataTable mstHoukou)
	{
		this.mstHoukouDt = mstHoukou;
	}
//
	/// <summary>
	/// 検査方法マスタ情報を取得する
	/// </summary>
	/// <returns>検査方法マスタ情報</returns>
	public DataTable GetMstKHouhou()
	{
		return this.mstKHouhouDt;
	}

	/// <summary>
	/// 検査方法マスタ情報を設定する
	/// </summary>
	/// <param name="mstKHouhou">検査方法マスタ情報</param>
	public void SetMstKHouhou(DataTable mstKHouhou)
	{
		this.mstKHouhouDt = mstKHouhou;
	}
//
	/// <summary>
	/// 診療科医師マスタ情報を取得する
	/// </summary>
	/// <returns>診療科医師マスタ情報</returns>
	public DataTable GetMstDoctor()
	{
		return this.mstDoctorDt;
	}

	/// <summary>
	/// 診療科医師マスタ情報を設定する
	/// </summary>
	/// <param name="mstDoctor">診療科医師マスタ情報</param>
	public void SetMstDoctor(DataTable mstDoctor)
	{
		this.mstDoctorDt = mstDoctor;
	}
//
	/// <summary>
	/// 診療科マスタ情報を取得する
	/// </summary>
	/// <returns>診療科マスタ情報</returns>
	public DataTable GetMstSection()
	{
		return this.mstSectionDt;
	}

	/// <summary>
	/// 診療科マスタ情報を設定する
	/// </summary>
	/// <param name="mstSection">診療科マスタ情報</param>
	public void SetMstSection(DataTable mstSection)
	{
		this.mstSectionDt = mstSection;
	}

	// 2010.09.01 Add K.Shinohara Start
	/// <summary>
	/// 詳細画面区分を取得する
	/// </summary>
	/// <returns></returns>
	public String GetOpeKbn()
	{
		return this.opeKbn;
	}

	/// <summary>
	/// 詳細画面区分を設定する
	/// </summary>
	/// <param name="opeKbn"></param>
	public void SetOpeKbn(String opeKbn)
	{
		this.opeKbn = opeKbn;
	}
	// 2010.09.01 Add K.Shinohara End
}
