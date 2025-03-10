package ris.lib.core.bean;

import java.util.ArrayList;

import org.apache.cxf.common.util.StringUtils;

/// <summary>
/// システム共通パラメータクラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.04.20	: 112478 / A.Kobayashi		: original
/// V2.01.00		: 2011.07.14	: 999999 / NSK_R.Akimoto	: NML-CAT2-017
/// V2.01.00		: 2011.08.22	: 999999 / T.Nishikubo		: NML-2-X01
/// V2.01.01.13000	: 2011.11.18    extends 999999 / NSK_H.Hashimoto	: OMH-1-03
///
/// </summary>
public class SystemParamBean
{
	// private members
	private String kanjaidValue1Str				= "";
	private String sijiIsiCommentValue1Str		= "";
	private String renrakuMemoValue1Str			= "";
	private String bikouValue1Str				= "";
	private String handicappedValue1Str			= "";
	private String notesValue1Str				= "";
	private String placeValue1Str				= "";
	private String placeValue2Str				= "";
	private String radIdValue1Str				= "";
	private String resultcountValue1Str			= "";
	private String combolistsValue1Str			= "";
	private String flagsValue1Str				= "";
	private String flagsValue2Str				= "";
	private String flagsValue3Str				= "";
	private String receiptnoValue1Str			= "";
	private String maxcountValue1Str			= "";
	private String maxcountValue2Str			= "";
	private String maxcountValue3Str			= "";
	private String barcodeValue1Str				= "";
	private String barcodeValue2Str				= "";
	private String barcodeValue3Str				= "";
	private String statusbgcolorValue1Str		= "";
	private String statusbgcolorValue2Str		= "";
	private String statusbgcolorValue3Str		= "";
	private String selectrowbgcolorValue1Str	= "";
	private String selectrowbgcolorValue2Str	= "";
	private String selectrowbgcolorValue3Str	= "";
	private String selectrowtextcolorValue1Str	= "";
	private String selectrowtextcolorValue2Str	= "";
	private String selectrowtextcolorValue3Str	= "";
	private String suuryouValue1Str				= "";
	private String suuryouValue2Str				= "";
	private String suuryouIjiValue1Str			= "";
	private String suuryouIjiValue2Str			= "";
	private String setstarttimeValue1Str		= "";
	private String passwordValue1Str			= "";
	private String passwordValue2Str			= "";
	// 2010.07.30 Add T.Ootsuka Start
	// 新規テンプレート区分追加
	private String kanjaKyotuCommentValue1Str	= "";
	private String kensaTypeCommentValue1Str	= "";
	// 2010.07.30 Add T.Ootsuka End
	// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
	private String receiptRuleValue1Str = "";
	private String receiptRuleValue2Str = "";
	// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

	// 2011.07.14 Add NSK_R.Akimoto Start NML-CAT2-017
	private String receiptKanjaValue1Str = "";
	// 2011.07.14 Add NSK_R.Akimoto End

	// 2011.08.22 Add T.Nishikubo@MERX Start NML-2-X01
	private String holidayModeValue1Str			= "";
	// 2011.08.22 Add T.Nishikubo@MERX End

	// 2011.11.18 Add NSK_H.Hashimoto Start OMH-1-03
	private ArrayList emergencyValueList;
	// 2011.11.18 Add NSK_H.Hashimoto End
//
	/// <summary>
	/// コンストラクタ
	/// </summary>
	public SystemParamBean()
	{
		//
	}
//
	/// <summary>
	/// kanjaidValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetKanjaIDValue1()
	{
		return this.kanjaidValue1Str;
	}

	/// <summary>
	/// kanjaidValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetKanjaIDValue1(String kanjaidValue1)
	{
		if (kanjaidValue1 != null)
		{
			this.kanjaidValue1Str = kanjaidValue1;
		}
	}
//
	/// <summary>
	/// sijiIsiCommentValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetSijiIsiCommentValue1()
	{
		return this.sijiIsiCommentValue1Str;
	}

	/// <summary>
	/// sijiIsiCommentValue1取得
	/// </summary>
	/// <returns></returns>
	public Integer GetSijiIsiCommentValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (sijiIsiCommentValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(sijiIsiCommentValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// sijiIsiCommentValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetSijiIsiCommentValue1(String sijiIsiCommentValue1)
	{
		if (sijiIsiCommentValue1 != null)
		{
			this.sijiIsiCommentValue1Str = sijiIsiCommentValue1;
		}
	}
//
	/// <summary>
	/// renrakuMemoValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetRenrakuMemoValue1()
	{
		return this.renrakuMemoValue1Str;
	}

	/// <summary>
	/// renrakuMemoValue1取得
	/// </summary>
	/// <returns></returns>
	public Integer GetRenrakuMemoValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (renrakuMemoValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(renrakuMemoValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// renrakuMemoValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetRenrakuMemoValue1(String renrakuMemoValue1)
	{
		if (renrakuMemoValue1 != null)
		{
			this.renrakuMemoValue1Str = renrakuMemoValue1;
		}
	}
//
	/// <summary>
	/// bikouValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetBikouValue1()
	{
		return this.bikouValue1Str;
	}

	/// <summary>
	/// bikouValue1Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetBikouValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (bikouValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(bikouValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// bikouValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetBikouValue1(String bikouValue1)
	{
		if (bikouValue1 != null)
		{
			this.bikouValue1Str = bikouValue1;
		}
	}
//
	/// <summary>
	/// handicappedValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetHandicappedValue1()
	{
		return this.handicappedValue1Str;
	}

	/// <summary>
	/// handicappedValue1Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetHandicappedValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (handicappedValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(handicappedValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// handicappedValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetHandicappedValue1(String handicappedValue1)
	{
		if (handicappedValue1 != null)
		{
			this.handicappedValue1Str = handicappedValue1;
		}
	}
//
	/// <summary>
	/// notesValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetNotesValue1()
	{
		return this.notesValue1Str;
	}

	/// <summary>
	/// notesValue1Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetNotesValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (notesValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(notesValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// notesValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetNotesValue1(String notesValue1)
	{
		if (notesValue1 != null)
		{
			this.notesValue1Str = notesValue1;
		}
	}
//
	/// <summary>
	/// placeValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetPlaceValue1()
	{
		return this.placeValue1Str;
	}
//
	/// <summary>
	/// placeValue1Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetPlaceValue1Int()
	{
		Integer retInt = 8;
		try
		{
			if (placeValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(placeValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	/// <summary>
	/// placeValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetPlaceValue1(String placeValue1)
	{
		if (placeValue1 != null)
		{
			this.placeValue1Str = placeValue1;
		}
	}
//
	/// <summary>
	/// placeValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetPlaceValue2()
	{
		return this.placeValue2Str;
	}
//
	/// <summary>
	/// placeValue2Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetPlaceValue2Int()
	{
		Integer retInt = 6;
		try
		{
			if (placeValue2Str.length() > 0)
			{
				retInt = Integer.parseInt(placeValue2Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	/// <summary>
	/// placeValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetPlaceValue2(String placeValue2)
	{
		if (placeValue2 != null)
		{
			this.placeValue2Str = placeValue2;
		}
	}
//
	/// <summary>
	/// radIdValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetRadIdValue1()
	{
		return this.radIdValue1Str;
	}

	/// <summary>
	/// radIdValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetRadIdValue1(String radIdValue1)
	{
		if (radIdValue1 != null)
		{
			this.radIdValue1Str = radIdValue1;
		}
	}
//
	/// <summary>
	/// resultcountValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetResultcountValue1()
	{
		return this.resultcountValue1Str;
	}

	/// <summary>
	/// resultcountValue1取得
	/// </summary>
	/// <returns></returns>
	public Integer GetResultcountValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (resultcountValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(resultcountValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// resultcountValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetResultcountValue1(String resultcountValue1)
	{
		if (resultcountValue1 != null)
		{
			this.resultcountValue1Str = resultcountValue1;
		}
	}
//
	/// <summary>
	/// combolistsValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetCombolistsValue1()
	{
		return this.combolistsValue1Str;
	}

	/// <summary>
	/// combolistsValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetCombolistsValue1(String combolistsValue1)
	{
		if (combolistsValue1 != null)
		{
			this.combolistsValue1Str = combolistsValue1;
		}
	}
//
	/// <summary>
	/// flagsValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetFlagsValue1()
	{
		return this.flagsValue1Str;
	}

	/// <summary>
	/// flagsValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetFlagsValue1(String flagsValue1)
	{
		if (flagsValue1 != null)
		{
			this.flagsValue1Str = flagsValue1;
		}
	}
//
	/// <summary>
	/// flagsValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetFlagsValue2()
	{
		return this.flagsValue2Str;
	}

	/// <summary>
	/// flagsValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetFlagsValue2(String flagsValue2)
	{
		if (flagsValue2 != null)
		{
			this.flagsValue2Str = flagsValue2;
		}
	}
//
	/// <summary>
	/// flagsValue3取得
	/// </summary>
	/// <returns></returns>
	public String GetFlagsValue3()
	{
		return this.flagsValue3Str;
	}

	/// <summary>
	/// flagsValue3設定
	/// </summary>
	/// <returns></returns>
	public void SetFlagsValue3(String flagsValue3)
	{
		if (flagsValue3 != null)
		{
			this.flagsValue3Str = flagsValue3;
		}
	}
//
	/// <summary>
	/// receiptnoValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetReceiptnoValue1()
	{
		return this.receiptnoValue1Str;
	}

	/// <summary>
	/// receiptnoValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetReceiptnoValue1(String receiptnoValue1)
	{
		if (receiptnoValue1 != null)
		{
			this.receiptnoValue1Str = receiptnoValue1;
		}
	}
//
	/// <summary>
	/// maxcountValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetMaxcountValue1()
	{
		return this.maxcountValue1Str;
	}

	/// <summary>
	/// maxcountValue1取得
	/// </summary>
	/// <returns></returns>
	public Integer GetMaxcountValue1Int()
	{
		Integer retInt = -1;
		try
		{
			if (this.maxcountValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(this.maxcountValue1Str);
			}
		}
		finally
		{
		}
		return retInt;
	}

	/// <summary>
	/// maxcountValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetMaxcountValue1(String maxcountValue1)
	{
		if (maxcountValue1 != null)
		{
			this.maxcountValue1Str = maxcountValue1;
		}
	}
//
	/// <summary>
	/// maxcountValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetMaxcountValue2()
	{
		return this.maxcountValue2Str;
	}

	/// <summary>
	/// maxcountValue2取得
	/// </summary>
	/// <returns></returns>
	public Integer GetMaxcountValue2Int()
	{
		Integer retInt = -1;
		try
		{
			if (this.maxcountValue2Str.length() > 0)
			{
				retInt = Integer.parseInt(this.maxcountValue2Str);
			}
		}
		finally
		{
		}
		return retInt;
	}

	/// <summary>
	/// maxcountValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetMaxcountValue2(String maxcountValue2)
	{
		if (maxcountValue2 != null)
		{
			this.maxcountValue2Str = maxcountValue2;
		}
	}
//
	/// <summary>
	/// maxcountValue3取得
	/// </summary>
	/// <returns></returns>
	public String GetMaxcountValue3()
	{
		return maxcountValue3Str;
	}

	/// <summary>
	/// maxcountValue3取得
	/// </summary>
	/// <returns></returns>
	public Integer GetMaxcountValue3Int()
	{
		Integer retInt = -1;
		try
		{
			if (this.maxcountValue3Str.length() > 0)
			{
				retInt = Integer.parseInt(this.maxcountValue3Str);
			}
		}
		finally
		{
		}
		return retInt;
	}

	/// <summary>
	/// maxcountValue3設定
	/// </summary>
	/// <returns></returns>
	public void SetMaxcountValue3(String maxcountValue3)
	{
		if (maxcountValue3 != null)
		{
			this.maxcountValue3Str = maxcountValue3;
		}
	}
//
	/// <summary>
	/// barcodeValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetBarcodeValue1()
	{
		return this.barcodeValue1Str;
	}

	/// <summary>
	/// barcodeValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetBarcodeValue1(String barcodeValue1)
	{
		if (barcodeValue1 != null)
		{
			this.barcodeValue1Str = barcodeValue1;
		}
	}
//
	/// <summary>
	/// barcodeValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetBarcodeValue2()
	{
		return this.barcodeValue2Str;
	}

	/// <summary>
	/// barcodeValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetBarcodeValue2(String barcodeValue2)
	{
		if (barcodeValue2 != null)
		{
			this.barcodeValue2Str = barcodeValue2;
		}
	}
//
	/// <summary>
	/// barcodeValue3取得
	/// </summary>
	/// <returns></returns>
	public String GetBarcodeValue3()
	{
		return this.barcodeValue3Str;
	}

	/// <summary>
	/// barcodeValue3設定
	/// </summary>
	/// <returns></returns>
	public void SetBarcodeValue3(String barcodeValue3)
	{
		if (barcodeValue3 != null)
		{
			this.barcodeValue3Str = barcodeValue3;
		}
	}
//
	/// <summary>
	/// statusbgcolorValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetStatusbgcolorValue1()
	{
		return this.statusbgcolorValue1Str;
	}

	/// <summary>
	/// statusbgcolorValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetStatusbgcolorValue1(String statusbgcolorValue1)
	{
		if (statusbgcolorValue1 != null)
		{
			this.statusbgcolorValue1Str = statusbgcolorValue1;
		}
	}
//
	/// <summary>
	/// statusbgcolorValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetStatusbgcolorValue2()
	{
		return this.statusbgcolorValue2Str;
	}

	/// <summary>
	/// statusbgcolorValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetStatusbgcolorValue2(String statusbgcolorValue2)
	{
		if (statusbgcolorValue2 != null)
		{
			this.statusbgcolorValue2Str = statusbgcolorValue2;
		}
	}
//
	/// <summary>
	/// statusbgcolorValue3取得
	/// </summary>
	/// <returns></returns>
	public String GetStatusbgcolorValue3()
	{
		return this.statusbgcolorValue3Str;
	}

	/// <summary>
	/// statusbgcolorValue3設定
	/// </summary>
	/// <returns></returns>
	public void SetStatusbgcolorValue3(String statusbgcolorValue3)
	{
		if (statusbgcolorValue3 != null)
		{
			this.statusbgcolorValue3Str = statusbgcolorValue3;
		}
	}
//
	/// <summary>
	/// selectrowbgcolorValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetSelectrowbgcolorValue1()
	{
		return this.selectrowbgcolorValue1Str;
	}

	/// <summary>
	/// selectrowbgcolorValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetSelectrowbgcolorValue1(String selectrowbgcolorValue1)
	{
		if (selectrowbgcolorValue1 != null)
		{
			this.selectrowbgcolorValue1Str = selectrowbgcolorValue1;
		}
	}
//
	/// <summary>
	/// selectrowbgcolorValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetSelectrowbgcolorValue2()
	{
		return this.selectrowbgcolorValue2Str;
	}

	/// <summary>
	/// selectrowbgcolorValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetSelectrowbgcolorValue2(String selectrowbgcolorValue2)
	{
		if (selectrowbgcolorValue2 != null)
		{
			this.selectrowbgcolorValue2Str = selectrowbgcolorValue2;
		}
	}
//
	/// <summary>
	/// selectrowbgcolorValue3取得
	/// </summary>
	/// <returns></returns>
	public String GetSelectrowbgcolorValue3()
	{
		return this.selectrowbgcolorValue3Str;
	}

	/// <summary>
	/// selectrowbgcolorValue3設定
	/// </summary>
	/// <returns></returns>
	public void SetSelectrowbgcolorValue3(String selectrowbgcolorValue3)
	{
		if (selectrowbgcolorValue3 != null)
		{
			this.selectrowbgcolorValue3Str = selectrowbgcolorValue3;
		}
	}
//
	/// <summary>
	/// selectrowtextcolorValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetSelectrowtextcolorValue1()
	{
		return this.selectrowtextcolorValue1Str;
	}

	/// <summary>
	/// selectrowtextcolorValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetSelectrowtextcolorValue1(String selectrowtextcolorValue1)
	{
		if (selectrowtextcolorValue1 != null)
		{
			this.selectrowtextcolorValue1Str = selectrowtextcolorValue1;
		}
	}
//
	/// <summary>
	/// selectrowtextcolorValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetSelectrowtextcolorValue2()
	{
		return selectrowtextcolorValue2Str;
	}

	/// <summary>
	/// selectrowtextcolorValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetSelectrowtextcolorValue2(String selectrowtextcolorValue2)
	{
		if (selectrowtextcolorValue2 != null)
		{
			this.selectrowtextcolorValue2Str = selectrowtextcolorValue2;
		}
	}
//
	/// <summary>
	/// selectrowtextcolorValue3取得
	/// </summary>
	/// <returns></returns>
	public String GetSelectrowtextcolorValue3()
	{
		return this.selectrowtextcolorValue3Str;
	}

	/// <summary>
	/// selectrowtextcolorValue3設定
	/// </summary>
	/// <returns></returns>
	public void SetSelectrowtextcolorValue3(String selectrowtextcolorValue3)
	{
		if (selectrowtextcolorValue3 != null)
		{
			this.selectrowtextcolorValue3Str = selectrowtextcolorValue3;
		}
	}
//
	/// <summary>
	/// suuryouValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetSuuryouValue1()
	{
		return this.suuryouValue1Str;
	}
//
	/// <summary>
	/// suuryouValue1Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetSuuryouValue1Int()
	{
		Integer retInt = 6;
		try
		{
			if (suuryouValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(suuryouValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	/// <summary>
	/// suuryouValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetSuuryouValue1(String suuryouValue1)
	{
		if (suuryouValue1 != null)
		{
			this.suuryouValue1Str = suuryouValue1;
		}
	}
//
	/// <summary>
	/// suuryouValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetSuuryouValue2()
	{
		return this.suuryouValue2Str;
	}
//
	/// <summary>
	/// suuryouValue2Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetSuuryouValue2Int()
	{
		Integer retInt = 1;
		try
		{
			if (suuryouValue2Str.length() > 0)
			{
				retInt = Integer.parseInt(suuryouValue2Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	/// <summary>
	/// suuryouValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetSuuryouValue2(String suuryouValue2)
	{
		if (suuryouValue2 != null)
		{
			this.suuryouValue2Str = suuryouValue2;
		}
	}
//
	/// <summary>
	/// suuryouIjiValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetSuuryouIjiValue1()
	{
		return this.suuryouIjiValue1Str;
	}
//
	/// <summary>
	/// suuryouIjiValue1Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetSuuryouIjiValue1Int()
	{
		Integer retInt = 6;
		try
		{
			if (suuryouIjiValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(suuryouIjiValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	/// <summary>
	/// suuryouIjiValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetSuuryouIjiValue1(String suuryouIjiValue1)
	{
		if (suuryouIjiValue1 != null)
		{
			this.suuryouIjiValue1Str = suuryouIjiValue1;
		}
	}
//
	/// <summary>
	/// suuryouIjiValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetSuuryouIjiValue2()
	{
		return this.suuryouIjiValue2Str;
	}
//
	/// <summary>
	/// suuryouIjiValue2Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetSuuryouIjiValue2Int()
	{
		Integer retInt = 1;
		try
		{
			if (suuryouIjiValue2Str.length() > 0)
			{
				retInt = Integer.parseInt(suuryouIjiValue2Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}
//
	/// <summary>
	/// suuryouIjiValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetSuuryouIjiValue2(String suuryouIjiValue2)
	{
		if (suuryouIjiValue2 != null)
		{
			this.suuryouIjiValue2Str = suuryouIjiValue2;
		}
	}
//
	/// <summary>
	/// setstarttimeValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetSetstarttimeValue1()
	{
		return this.setstarttimeValue1Str;
	}

	/// <summary>
	/// setstarttimeValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetSetstarttimeValue1(String setstarttimeValue1)
	{
		if (setstarttimeValue1 != null)
		{
			this.setstarttimeValue1Str = setstarttimeValue1;
		}
	}
//
	/// <summary>
	/// passwordValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetPasswordValue1()
	{
		return this.passwordValue1Str;
	}

	/// <summary>
	/// passwordValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetPasswordValue1(String passwordValue1)
	{
		if (passwordValue1 != null)
		{
			this.passwordValue1Str = passwordValue1;
		}
	}
//
	/// <summary>
	/// passwordValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetPasswordValue2()
	{
		return this.passwordValue2Str;
	}

	/// <summary>
	/// passwordValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetPasswordValue2(String passwordValue2)
	{
		if (passwordValue2 != null)
		{
			this.passwordValue2Str = passwordValue2;
		}
	}

	// 2010.07.30 Add T.Ootsuka Start
	// 新規テンプレート区分追加
	/// <summary>
	/// kanjaKyotuCommentValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetKanjaKyotuCommentValue1()
	{
		return this.kanjaKyotuCommentValue1Str;
	}

	/// <summary>
	/// kanjaKyotuCommentValue1取得
	/// </summary>
	/// <returns></returns>
	public Integer GetKanjaKyotuCommentValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (kanjaKyotuCommentValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(kanjaKyotuCommentValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// kanjaKyotuCommentValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetKanjaKyotuCommentValue1(String kanjaKyotuCommentValue1Str)
	{
		if (kanjaKyotuCommentValue1Str != null)
		{
			this.kanjaKyotuCommentValue1Str = kanjaKyotuCommentValue1Str;
		}
	}

	/// <summary>
	/// kensaTypeCommentValue1Str取得
	/// </summary>
	/// <returns></returns>
	public String GetKensaTypeCommentValue1()
	{
		return this.kensaTypeCommentValue1Str;
	}

	/// <summary>
	/// kensaTypeCommentValue1Str取得
	/// </summary>
	/// <returns></returns>
	public Integer GetKensaTypeCommentValue1Int()
	{
		Integer retInt = 0;
		try
		{
			if (kensaTypeCommentValue1Str.length() > 0)
			{
				retInt = Integer.parseInt(kensaTypeCommentValue1Str);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/// <summary>
	/// kensaTypeCommentValue1Str設定
	/// </summary>
	/// <returns></returns>
	public void SetKensaTypeCommentValue1(String kensaTypeCommentValue1Str)
	{
		if (kensaTypeCommentValue1Str != null)
		{
			this.kensaTypeCommentValue1Str = kensaTypeCommentValue1Str;
		}
	}
	// 2010.07.30 Add T.Ootsuka End

	// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
	/// <summary>
	/// receiptRuleValue1取得
	/// </summary>
	/// <returns></returns>
	public String GetReceiptRuleValue1Str()
	{
		return this.receiptRuleValue1Str;
	}
	/// <summary>
	/// receiptRuleValue1設定
	/// </summary>
	/// <returns></returns>
	public void SetReceiptRuleValue1Str(String receiptRuleValue1)
	{
		if (receiptRuleValue1 != null)
		{
			this.receiptRuleValue1Str = receiptRuleValue1;
		}
	}

	/// <summary>
	/// receiptRuleValue2取得
	/// </summary>
	/// <returns></returns>
	public String GetReceiptRuleValue2Str()
	{
		return this.receiptRuleValue2Str;
	}
	/// <summary>
	/// receiptRuleValue2設定
	/// </summary>
	/// <returns></returns>
	public void SetReceiptRuleValue2Str(String receiptRuleValue2)
	{
		if (receiptRuleValue2 != null)
		{
			this.receiptRuleValue2Str = receiptRuleValue2;
		}
	}
	// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

	// 2011.07.14 Add NSK_R.Akimoto Start NML-CAT2-017
	/// <summary>
	/// SUBID-RECEIPTKANJAの値を設定します
	/// </summary>
	/// <param name="receiptKanjaValue1">RECEIPTKANJAに設定する値</param>
	public void SetReceiptKanjaValue1Str(String receiptKanjaValue1)
	{
		if (!StringUtils.isEmpty(receiptKanjaValue1))
		{
			this.receiptKanjaValue1Str = receiptKanjaValue1;
		}
	}

	/// <summary>
	/// SUBID-RECEIPTKANJAの値を取得します
	/// </summary>
	/// <returns>SUBID-RECEIPTKANJAの値</returns>
	public String GetReceiptKanjaValue1Str()
	{
		return this.receiptKanjaValue1Str;
	}
	// 2011.07.14 Add NSK_R.Akimoto End

	// 2011.08.22 Add T.Nishikubo@MERX Start NML-2-X01
	/// <summary>
	/// holidayModeValue1Str取得
	/// </summary>
	/// <returns></returns>
	public String GetHolidayModeValue1()
	{
		return this.holidayModeValue1Str;
	}
	/// <summary>
	/// holidayModeValue1Str設定
	/// </summary>
	/// <returns></returns>
	public void SetHolidayModeValue1(String holidayModeValue1Str)
	{
		if (holidayModeValue1Str != null)
		{
			this.holidayModeValue1Str = holidayModeValue1Str;
		}
	}
	// 2011.08.22 Add T.Nishikubo@MERX End

	// 2011.11.18 Add NSK_H.Hashimoto Start OMH-1-03
	/// <summary>
	/// emergencyValueListの設定
	/// </summary>
	/// <param name="arrayList">Value1～6のリスト</param>
	public void SetEemergencyValueList(ArrayList arrayList)
	{
		if (arrayList != null)
		{
			this.emergencyValueList = arrayList;
		}
	}
	/// <summary>
	/// emergencyValueListの取得
	/// </summary>
	/// <returns>emergencyValueList</returns>
	public ArrayList GetEemergencyValueList()
	{
		return this.emergencyValueList;
	}
	// 2011.11.18 Add NSK_H.Hashimoto End
}
