package ris.portable.util;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.bean.BuiInfoBean;
import ris.lib.core.bean.ExFilmBean;
import ris.lib.core.bean.ExSatueiBean;
import ris.lib.core.bean.ExecutionInfoBean;
import ris.lib.core.bean.OrderBuiBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.PatientInfoBean;
import ris.lib.core.bean.PresetInfoBean;
import ris.lib.core.database.RisOrderBuiInfoTbl;
import ris.lib.core.database.RisPatientCommentTbl;
import ris.lib.core.database.RisSummaryView;
import ris.lib.core.database.RisSystemParamTbl;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MWMParameter;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.OrderSearchParameter;
import ris.lib.core.util.PresetParameter;
import ris.lib.core.util.RequestParameter;
import ris.lib.core.util.TextUtil;
import ris.lib.mwm.bean.ConnectionInfoBean;
import ris.lib.mwm.bean.KensaKikiBean;
import ris.lib.mwm.bean.MWMInfoBean;
import ris.lib.mwm.bean.MwmPatientInfoBean;
import ris.lib.mwm.util.MwmMasterUtil;
import ris.portable.common.Const;

/// <summary>
/// 画面共通処理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.02.18	: 112478 / A.Kobayashi		: original
/// V2.04.001		: 2010.10.04	: 999999 / Y.Shibata		: KANRO-R-1
/// V2.04.00		: 2011.02.16	: 999999 / T.Nishikubo		: KANRO-R-27
/// V2.01.00		: 2011.07.27	: 999999 / NSK_S.Imai		: NML-CAT1-008
/// V2.01.00		: 2011.08.05	: 999999 / K.Aizawa			: NML-CAT2-015 NML-CAT3-034
/// V2.01.00		: 2011.08.22	: 999999 / T.Nishikubo		: NML-2-X01
/// V2.01.01.13000	: 2011.11.18    : 999999 / NSK_H.Hashimoto	: OMH-1-03
/// V2.01.01.13000	: 2011.11.14    : 999999 / NSK_H.Hashimoto	: OMH-1-04
/// V2.01.01.13000	: 2011.11.22  　: 999999 / NSK_M.Ochiai		: OMH-1-2
///
/// </summary>
public final class AppCommon
{
	private static Log logger = LogFactory.getLog(AppCommon.class);

	public static String TIME_FORMAT = "TimeFormat";

	/*
	// 2010.11.01 Add K.Shinohara Start
	[DllImport("user32.dll")]
	extern static int GetWindowThreadProcessId(IntPtr hWnd, IntPtr ProcessId);

	[DllImport("user32.dll")]
	extern static IntPtr GetForegroundWindow();

	[DllImport("user32.dll")]
	extern static bool AttachThreadInput(int idAttach, int idAttachTo, bool fAttach);
	// 2010.11.01 Add K.Shinohara End
	*/

	// 2011.11.18 Add NSK_H.Hashimoto Start OMH-1-03
	public static final int EMG_COLOR_ID		= 0;	// 救急科ＩＤ
	public static final int EMG_COLOR_BACK_KBN	= 1;	// 背景色区分(0:システム色／1:指定色)
	public static final int EMG_COLOR_BACK_COL	= 2;	// 背景色指定色
	public static final int EMG_COLOR_FORE_KBN	= 3;	// 文字色区分(0:進捗文字色／1:進捗背景色／2:指定色)
	public static final int EMG_COLOR_FORE_COL	= 4;	// 文字色指定色
	public static final int EMG_COLOR_ORDER	= 5;	// 検済・中止オーダ指定(0:救急科指定／1:進捗毎設定)
	// 2011.11.18 Add NSK_H.Hashimoto End
	// 2011.11.14 Add NSK_H.Hashimoto Start OMH-1-04
	// CSV出力先設定の保存ファイル名
	public static final String LIST_REFERENCEFORM_CSV_TXTNAME = "ListReferenceForm_CSV.txt";
	// 2011.11.14 Add NSK_H.Hashimoto End

    private static String MARK_STR = "●";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public AppCommon()
	{
		//
	}

	/*
	/// ------------------------------------------------------------------------
	/// <summary>
	///     指定した精度の数値に四捨五入します。</summary>
	/// <param name="dValue">
	///     丸め対象の倍精度浮動小数点数。</param>
	/// <param name="iDigits">
	///     戻り値の有効桁数の精度。</param>
	/// <returns>
	///     iDigits に等しい精度の数値に四捨五入された数値。</returns>
	/// ------------------------------------------------------------------------
	public static double ToHalfAdjust(double dValue, int iDigits)
	{
		double dCoef = System.Math.Pow(10, iDigits);

		return dValue > 0 ? System.Math.Floor((dValue * dCoef) + 0.5) / dCoef:
                    System.Math.Ceiling((dValue * dCoef) - 0.5) / dCoef;
	}

	/// <summary>
	/// ボタン枠の色を取得する
	/// </summary>
	/// <param name="type"></param>
	/// <returns></returns>
	public static Color GetBtnLineColor(int type)
	{
		//枠色の初期値をconfigより取得
		String colorString = "";
		ArrayList colors   = new ArrayList();
		Color retColor = Color.FromArgb(45, 45, 45);

		try
		{
			//タイプにより分岐
			if (type.Equals(0))
			{
				//通常-外枠用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("BtnDefault.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor = Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
			else if (type.Equals(1))
			{
				//通常-内枠用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("BtnInDefault.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor =  Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
			else if (type.Equals(2))
			{
				//基本用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("BtnNormal.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor =  Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
			else if (type.Equals(3))
			{
				//空中用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("BtnHover.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor =  Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
			else if (type.Equals(4))
			{
				//前景色用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("BtnFont.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor =  Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
		}
		catch( Exception ex)
		{
			logger.Error(ex);
		}
		return retColor;
	}

	/// <summary>
	/// ボタン枠の色を取得する
	/// </summary>
	/// <param name="type"></param>
	/// <returns></returns>
	public static Color GetVistaBtnLineColor(int type)
	{
		//枠色の初期値をconfigより取得
		String colorString = "";
		ArrayList colors   = new ArrayList();
		Color retColor = Color.FromArgb(45, 45, 45);

		try
		{
			//タイプにより分岐
			if (type.Equals(0))
			{
				//基本用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("VistaBtnNormal.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor =  Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
			else if (type.Equals(1))
			{
				//フォーカス用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("VistaBtnFocus.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor = Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
			else if (type.Equals(2))
			{
				//空中用
				colorString = Configuration.GetInstance().GetDynamicPropertyString("VistaBtnHover.Color");
				colors = AppCommon.GetRGBArrayFromCSVString(colorString);
				retColor =  Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
			}
		}
		catch (Exception ex)
		{
			logger.Error(ex);
		}
		return retColor;
	}

	/// <summary>
	/// RGB値取得
	/// </summary>
	/// <param name="colors"></param>
	/// <returns></returns>
	public static ArrayList GetRGBArrayFromCSVString(String colors)
	{
		ArrayList ret = null;

		if(null != colors)
		{
			String [] colorsArray = AppCommon.ParseCSVString(colors);
			if(3 == colorsArray.Length)
			{
				ret = new ArrayList();
				for(int i=0; i<3; i++)
				{
					String val = colorsArray[i];
					ret.add(Convert.ToByte(val, 16));
				}
			}
		}

		return ret;
	}

	/// <summary>
	/// 色を取得する
	/// </summary>
	/// <returns></returns>
	public static Color GetColor(String keyStr)
	{
		//configより取得
		String colorString = "";
		ArrayList colors = new ArrayList();
		Color retColor = Color.Black;

		try
		{
			//強調色
			colorString = Configuration.GetInstance().GetDynamicPropertyString(keyStr);
			colors = GetRGBArrayFromCSVString(colorString);
			retColor = Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
		}
		catch (Exception ex)
		{
			logger.Error(ex);
		}

		return retColor;
	}

	/// <summary>
	/// 色を取得する
	/// </summary>
	/// <returns></returns>
	public static Color GetColorRGB(String colorString)
	{
		ArrayList colors = new ArrayList();
		Color retColor = Color.Black;

		try
		{
			//色を取得
			colors = GetRGBArrayFromCSVString(colorString);
			retColor = Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
		}
		catch (Exception ex)
		{
			logger.Error(ex);
		}

		return retColor;
	}

	/// -----------------------------------------------------------------------------------------
	/// <summary>
	///     半角 1 バイト、全角 2 バイトとして、指定された文字列のバイト数を返します。</summary>
	/// <param name="stTarget">
	///     バイト数取得の対象となる文字列。</param>
	/// <returns>
	///     半角 1 バイト、全角 2 バイトでカウントされたバイト数。</returns>
	/// -----------------------------------------------------------------------------------------
	public static int GetLenB(String stTarget)
	{
		return System.Text.Encoding.GetEncoding(CommonString.ENCODE_DEF).GetByteCount(stTarget);
	}

	/// <summary>
	/// Imageオブジェクトにローカルファイル設定
	/// </summary>
	/// <param name="image"></param>
	/// <param name="fileName"></param>
	/// <param name="patientId"></param>
	/// <returns></returns>
	public static Image GetImageFromLocalFile(String filePath)
	{

		if(System.IO.File.Exists(filePath))
		{
			System.IO.FileStream fs = new System.IO.FileStream(filePath, System.IO.FileMode.Open, System.IO.FileAccess.Read);
			Image tempImage = Image.FromStream(fs);
			fs.Close();
			return tempImage;
		}

		return null;
	}

	public static String [] ParseCSVString(String str)
	{
		String [] ret = str.Split(',');
		for(int i=0; i<ret.Length; i++)
		{
			ret[i] = ret[i].Trim();
		}
		return ret;
	}

	/// <summary>
	/// 共通フォント設定(入力系)
	/// </summary>
	/// <param name=""></param>
	/// <remarks>入力系:テキスト、プルダウン、日付</remarks>
	public static Font SetInputFont(Font font)
	{
		System.Configuration.AppSettingsReader cfgAppSettings = new System.Configuration.AppSettingsReader();

		//共通フォント(入力系)設定
		String inputFont = "";
		Font   retFont   = font;
		try
		{
			inputFont = ((string)(cfgAppSettings.GetValue("InputFont.Value", typeof(string))));
			retFont   = new System.Drawing.Font(inputFont, font.Size, font.Style, font.Unit, font.GdiCharSet, font.GdiVerticalFont);
		}
		catch( Exception ex)
		{
			logger.Error(ex);
		}
		return retFont;
	}

	/// <summary>
	/// 共通フォント設定(ラベル系)
	/// </summary>
	/// <param name=""></param>
	/// <remarks>ラベル系:ラベル、タブ、ボタン、画面タイトル、グリッド、カレンダ、メニュー、画像パレットに対応</remarks>
	public static Font SetNormalFont(Font font)
	{
		System.Configuration.AppSettingsReader cfgAppSettings = new System.Configuration.AppSettingsReader();

		//共通フォント(ラベル系)設定
		String normalFont = "";
		Font   retFont    = font;
		try
		{
			normalFont = ((string)(cfgAppSettings.GetValue("NormalFont.Value", typeof(string))));
			retFont    = new System.Drawing.Font(normalFont, font.Size, font.Style, font.Unit, font.GdiCharSet, font.GdiVerticalFont);
		}
		catch( Exception ex)
		{
			logger.Error(ex);
		}
		return retFont;
	}

	/// <summary>
	/// システム用区切り文字の取得
	/// </summary>
	/// <returns></returns>
	public static char GetMarkerCharacter()
	{
		char retChar = '|';

		// 2011.04.18 Mod H.Orikasa Start
		String mark = Configuration.GetInstance().GetMarkerCharacter();
		if (mark.Length > 0)
		{
			try
			{
				retChar = char.Parse(mark);
			}
			catch
			{
			}
		}
		// コメント
		//DataTable systemDefineDataTable = Configuration.GetInstance().GetCoreController().GetRisSystemDefine();
		//String mark = "";
		//if (systemDefineDataTable != null && systemDefineDataTable.Rows.Count > 0)
		//{
		//    mark = systemDefineDataTable.Rows[0][RisSystemDefineTbl.MARKERCHARACTER_COLUMN].toString();
		//    if (mark.Length > 0)
		//    {
		//        try
		//        {
		//            retChar = char.Parse(mark);
		//        }
		//        catch
		//        {}
		//    }
		//}

		// 2011.04.18 Mod H.Orikasa End

		return retChar;
	}

	/// <summary>
	/// 更新日時降順にファイルを取得
	/// </summary>
	/// <param name="dir">フォルダパス</param>
	/// <param name="format">形式</param>
	/// <returns></returns>
	public static String [] GetFilesByName(String dir, String format)
	{
		String [] ret = null;
		String [] temp = null;
		ArrayList tempArrayList = new ArrayList();
		NameComparer comp = new NameComparer();

		temp = System.IO.Directory.GetFiles(dir, format);
		for(int i=0; i<temp.Length; i++)
		{
			tempArrayList.add(temp[i]);
		}
		tempArrayList.Sort();
		tempArrayList.Sort(comp);

		ret = new string[tempArrayList.Count];
		for(int i=0; i<tempArrayList.Count; i++)
		{
			ret[i] = (String)tempArrayList[i];
		}

		return ret;
	}

	/// <summary>
	/// ソート用コンパレータ
	/// </summary>
	public class NameComparer : System.Collections.IComparer
	{
		//xがyより小さいときはマイナスの数、大きいときはプラスの数、
		//同じときは0を返す
		public int Compare(object x, object y)
		{
			int ret = 0;

			DateTime a = System.IO.File.GetLastWriteTime((string)x);
			DateTime b = System.IO.File.GetLastWriteTime((string)y);

			String ext_x = System.IO.Path.GetExtension((string)x);
			ext_x = ext_x.Replace(".", "");
			String name_x = System.IO.Path.GetFileNameWithoutExtension((string)x);

			String ext_y = System.IO.Path.GetExtension((string)y);
			ext_y = ext_y.Replace(".", "");
			String name_y = System.IO.Path.GetFileNameWithoutExtension((string)y);

			try
			{
				ret = (name_x).CompareTo(name_y);
				if(0 == ret)
				{
					int index_x = int.Parse(ext_x);
					int index_y = int.Parse(ext_y);
					ret = index_x.CompareTo(index_y);
				}
			}
			catch
			{
			}
			return ret;
		}
	}

	*/

	/// <summary>
	/// 文字列から数値を取得する
	/// </summary>
	/// <param name="value">文字列</param>
	/// <returns></returns>
	public static Integer StrToInt0(String value)
	{
		Integer retInt = 0;
		try
		{
			if (value.length() > 0)
			{
				retInt = Integer.parseInt(value);
			}
		}
		catch (Exception e)
		{
		}
		return retInt;
	}

	/*

	/// <summary>
	/// DBレコードから値を取得（文字列）
	/// </summary>
	/// <param name="dr"></param>
	/// <param name="key"></param>
	/// <returns></returns>
	public static String GetDataRowString(DataRow dr, String key)
	{
		if(!dr.IsNull(key))
		{
			return dr[key].toString();
		}
		else
		{
			return "";
		}
	}

	/// <summary>
	/// DBレコードから値を取得（日付）
	/// </summary>
	/// <param name="dr"></param>
	/// <param name="key"></param>
	/// <returns></returns>
	public static DateTime GetDataRowDateTime(DataRow dr, String key)
	{
		if(!dr.IsNull(key))
		{
			return (DateTime)dr[key];
		}
		else
		{
			return DateTime.MinValue;
		}
	}

	/// <summary>
	/// 患者の移動情報画像を取得する
	/// </summary>
	/// <param name="imgPath"></param>
	/// <param name="valueStr">移動情報タイプ</param>
	/// <param name="valueName">移動情報</param>
	/// <returns></returns>
	public static Image GetTransportsImage(String imgPath, String valueStr, ref String valueName)
	{
		Image retImg = null;

		try
		{
			if (valueStr.Length > 0)
			{
				DataTable dt = Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_CODECONVERT, false);

				//項目変換のループ
				for (int i = 0; i < dt.Rows.Count; i++)
				{
					DataRow row = dt.Rows[i];
					String itemID		= row[MasterUtil.RIS_ITEMID].toString();
					String itemValue	= row[MasterUtil.RIS_ITEMVALUE].toString();
					String valueLabel	= row[MasterUtil.RIS_VALUELABEL].toString();
					String valuePath	= row[MasterUtil.RIS_VALUEPATH].toString();
					String valueOption	= row[MasterUtil.RIS_VALUEOPTIONS].toString();

					if (itemID    == CommonString.CODECONVERT_ID_TRANSPORT &&
					itemValue == valueStr)
					{
						String filePath = imgPath + @"\" +  valueOption;

						logger.info("FilePath = " + filePath);

						Image pathImage = AppCommon.GetImageFromLocalFile(filePath);
						if (pathImage != null)
						{
							retImg = pathImage;
						}
						valueName = valueLabel;

						break;
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retImg;
	}

	/// <summary>
	/// 一覧の背景色を設定する
	/// </summary>
	/// <param name="item"></param>
	public static void SetListFormBackColor(MasterItem item, ListBaseForm form)
	{
		if (item != null && form != null)
		{
			String colorType = "";

			//メニューボタン設定用情報を準備
			ArrayList menuButtonList = Configuration.GetInstance().GetMenuButtonList();

			//リストのループ
			for (int i = 0; i < menuButtonList.Count; i++)
			{
				MasterItem masterItem = (MasterItem)menuButtonList[i];

				if (item.GetID() == masterItem.GetID())
				{
					colorType = masterItem.GetUnit();
					break;
				}
			}

			if (colorType.Length <= 0)
			{
				colorType = item.GetUnit();
			}

			if (colorType.Length > 0)
			{
				//背景色を取得
				Color frontColor = GetColor(colorType + "ListFrontPanel.Color");
				Color mainColor  = GetColor(colorType + "ListMainPanel.Color");
				Color compColor  = GetColor(colorType + "ListComponent.Color");

				//背景色を設定
				form.SetBackColor(frontColor, mainColor, compColor);
			}
		}
	}

	/// <summary>
	/// GDI色を取得する
	/// </summary>
	/// <param name="colorStr"></param>
	/// <returns></returns>
	public static Color GetWin32Color(String colorStr)
	{
		Color retColor = Color.Black;

		try
		{
			if (colorStr.Length > 0)
			{
				int colorInt = int.Parse(colorStr);
				retColor = ColorTranslator.FromWin32(colorInt);
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retColor;
	}

	/// <summary>
	/// 一覧へスタイルを設定する
	/// </summary>
	/// <param name="gridView">一覧</param>
	/// <param name="styleRow">スタイル</param>
	public static void DistributeListStyle(NormalOrderListDataGridView gridView, DataRow styleRow)
	{
		try
		{
			if (styleRow != null)
			{
				gridView.SuspendLayout();

				//行サイズ変更可
				gridView.AllowUserToResizeRows = true;

				//タイトル用
				string		tFontStr	= styleRow[RisShowListDefineTbl.TITLE_FONT_COLUMN].toString();
				FontStyle	tFontStyle	= FontStyle.Bold;
				string		tStyle		= styleRow[RisShowListDefineTbl.TITLE_FONTSTYLE_COLUMN].toString();
				if (tStyle == "0")
				{
					tFontStyle			= FontStyle.Regular;
				}
				else if (tStyle == "1")
				{
					tFontStyle			= FontStyle.Italic;
				}
				else if (tStyle == "2")
				{
					tFontStyle			= FontStyle.Bold;
				}
				else if (tStyle == "3")
				{
					tFontStyle			= FontStyle.Bold | FontStyle.Italic;
				}
				int			tSizeInt	= TextUtil.ParseStringToInt(styleRow[RisShowListDefineTbl.TITLE_FONTSIZE_COLUMN].toString());
				int			tHeightInt	= TextUtil.ParseStringToInt(styleRow[RisShowListDefineTbl.TITLE_HEIGHT_COLUMN].toString());

				//タイトルへスタイルの適応
				gridView.ColumnHeadersDefaultCellStyle.Font = new Font(tFontStr, tSizeInt, tFontStyle);
				//if (gridView.ColumnHeadersHeight < tHeightInt)
				//{
					gridView.ColumnHeadersHeight			= tHeightInt;
				//}

				//データ用
				string		dFontStr	= styleRow[RisShowListDefineTbl.DATA_FONT_COLUMN].toString();
				FontStyle	dFontStyle	= FontStyle.Bold;
				string		dStyle		= styleRow[RisShowListDefineTbl.DATA_FONTSTYLE_COLUMN].toString();
				if (dStyle == "0")
				{
					dFontStyle			= FontStyle.Regular;
				}
				else if (dStyle == "1")
				{
					dFontStyle			= FontStyle.Italic;
				}
				else if (dStyle == "2")
				{
					dFontStyle			= FontStyle.Bold;
				}
				else if (dStyle == "3")
				{
					dFontStyle			= FontStyle.Bold | FontStyle.Italic;
				}
				int			dSizeInt	= TextUtil.ParseStringToInt(styleRow[RisShowListDefineTbl.DATA_FONTSIZE_COLUMN].toString());
				int			dHeightInt	= TextUtil.ParseStringToInt(styleRow[RisShowListDefineTbl.DATA_HEIGHT_COLUMN].toString());

				//データへスタイルの適応
				gridView.RowTemplate.DefaultCellStyle.Font	= new Font(dFontStr, dSizeInt, dFontStyle);
				gridView.RowTemplate.Height					= dHeightInt;
				for (int i=0; i<gridView.Rows.Count; i++)
				{
					gridView.Rows[i].DefaultCellStyle.Font	= new Font(dFontStr, dSizeInt, dFontStyle);
					gridView.Rows[i].Height					= dHeightInt;
				}

				//行サイズ変更不可
				gridView.AllowUserToResizeRows = false;

				gridView.ResumeLayout();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}

	/// <summary>
	/// ステータス文字列取得
	/// </summary>
	/// <param name="statusID">ステータスID</param>
	public static String GetStatusString(String statusID)
	{
		String retStr = Configuration.GetInstance().GetStatusMsg0();
		if (statusID == null || statusID.Length <= 0)
		{
			return retStr;
		}

		// ステータスIDに応じて文字列を取得する

		if (statusID == CommonString.STATUS_UNREGISTERED)
		{
			retStr = Configuration.GetInstance().GetStatusMsg0();
		}
		else if (statusID == CommonString.STATUS_ISLATE)
		{
			retStr = Configuration.GetInstance().GetStatusMsg1();
		}
		else if (statusID == CommonString.STATUS_ISCALLING)
		{
			retStr = Configuration.GetInstance().GetStatusMsg2();
		}
		else if (statusID == CommonString.STATUS_ISREGISTERED)
		{
			retStr = Configuration.GetInstance().GetStatusMsg3();
		}
		else if (statusID == CommonString.STATUS_INOPERATION)
		{
			retStr = Configuration.GetInstance().GetStatusMsg4();
		}
		else if (statusID == CommonString.STATUS_REST)
		{
			retStr = Configuration.GetInstance().GetStatusMsg5();
		}
		else if (statusID == CommonString.STATUS_RECALLING)
		{
			retStr = Configuration.GetInstance().GetStatusMsg6();
		}
		else if (statusID == CommonString.STATUS_REREGISTERED)
		{
			retStr = Configuration.GetInstance().GetStatusMsg7();
		}
		else if (statusID == CommonString.STATUS_ISFINISHED)
		{
			retStr = Configuration.GetInstance().GetStatusMsg8();
		}
		else if (statusID == CommonString.STATUS_STOP)
		{
			retStr = Configuration.GetInstance().GetStatusMsg9();
		}
		// 2011.03.20 Add Yk.Suzuki@CIJ Start A0005
		if (Configuration.GetInstance().GetOrderDeleteFlg())
		{
			if (statusID == CommonString.STATUS_DELETE)
			{
				retStr = Configuration.GetInstance().GetStatusMsg10();
			}
		}
		// 2011.03.20 Add Yk.Suzuki@CIJ End


		return retStr;
	}

	/// <summary>
	/// ステータス色取得
	/// </summary>
	/// <param name="statusID">ステータスID</param>
	public static Color GetStatusColor(String statusID)
	{
		Color statusColor = Color.Black;
		if (statusID == null || statusID.Length <= 0)
		{
			return statusColor;
		}

		// ステータスIDに応じて色を取得する

		String colorStr = "";
		if (statusID == CommonString.STATUS_UNREGISTERED)
		{
			colorStr = Configuration.GetInstance().GetStatusColor0();
		}
		else if (statusID == CommonString.STATUS_ISLATE)
		{
			colorStr = Configuration.GetInstance().GetStatusColor1();
		}
		else if (statusID == CommonString.STATUS_ISCALLING)
		{
			colorStr = Configuration.GetInstance().GetStatusColor2();
		}
		else if (statusID == CommonString.STATUS_ISREGISTERED)
		{
			colorStr = Configuration.GetInstance().GetStatusColor3();
		}
		else if (statusID == CommonString.STATUS_INOPERATION)
		{
			colorStr = Configuration.GetInstance().GetStatusColor4();
		}
		else if (statusID == CommonString.STATUS_REST)
		{
			colorStr = Configuration.GetInstance().GetStatusColor5();
		}
		else if (statusID == CommonString.STATUS_RECALLING)
		{
			colorStr = Configuration.GetInstance().GetStatusColor6();
		}
		else if (statusID == CommonString.STATUS_REREGISTERED)
		{
			colorStr = Configuration.GetInstance().GetStatusColor7();
		}
		else if (statusID == CommonString.STATUS_ISFINISHED)
		{
			colorStr = Configuration.GetInstance().GetStatusColor8();
		}
		else if (statusID == CommonString.STATUS_STOP)
		{
			colorStr = Configuration.GetInstance().GetStatusColor9();
		}
		// 2011.03.20 Add Yk.Suzuki@CIJ Start A0005
		if (Configuration.GetInstance().GetOrderDeleteFlg())
		{
			if (statusID == CommonString.STATUS_DELETE)
			{
				colorStr = Configuration.GetInstance().GetStatusColor10();
			}
		}
		// 2011.03.20 Add Yk.Suzuki@CIJ End


		statusColor = AppCommon.GetWin32Color(colorStr);

		return statusColor;
	}

	/// <summary>
	/// ステータス背景色取得
	/// </summary>
	/// <param name="statusID">ステータスID</param>
	public static Color GetStatusBackColor(String statusID)
	{
		Color statusBackColor = Color.Black;
		if (statusID == null || statusID.Length <= 0)
		{
			return statusBackColor;
		}

		// ステータスIDに応じて色を取得する

		String colorStr = "";
		if (statusID == CommonString.STATUS_UNREGISTERED)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk0();
		}
		else if (statusID == CommonString.STATUS_ISLATE)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk1();
		}
		else if (statusID == CommonString.STATUS_ISCALLING)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk2();
		}
		else if (statusID == CommonString.STATUS_ISREGISTERED)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk3();
		}
		else if (statusID == CommonString.STATUS_INOPERATION)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk4();
		}
		else if (statusID == CommonString.STATUS_REST)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk5();
		}
		else if (statusID == CommonString.STATUS_RECALLING)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk6();
		}
		else if (statusID == CommonString.STATUS_REREGISTERED)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk7();
		}
		else if (statusID == CommonString.STATUS_ISFINISHED)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk8();
		}
		else if (statusID == CommonString.STATUS_STOP)
		{
			colorStr = Configuration.GetInstance().GetStatusColorBk9();
		}
		// 2011.03.20 Add Yk.Suzuki@CIJ Start A0005
		if (Configuration.GetInstance().GetOrderDeleteFlg())
		{
			if (statusID == CommonString.STATUS_DELETE)
			{
				colorStr = Configuration.GetInstance().GetStatusColorBk10();
			}
		}
		// 2011.03.20 Add Yk.Suzuki@CIJ End


		statusBackColor = AppCommon.GetWin32Color(colorStr);

		return statusBackColor;
	}

	/// <summary>
	/// ステータス(検像)文字列取得
	/// </summary>
	/// <param name="dt">コード変換情報</param>
	/// <param name="statusID">ステータスID</param>
	/// <returns></returns>
	public static String GetStatusKenzouString(DataTable dt, String statusID)
	{
		String retStr = "";
		if (dt == null)
		{
			dt = Configuration.GetInstance().GetRRisCodeConvertMaster();
		}
		if (statusID == null || statusID.Length <= 0)
		{
			return retStr;
		}

		//ステータス変換
		retStr = Configuration.GetInstance().GetCodeConvertValue(dt, CommonString.CODECONVERT_ID_KENZOUSTATUS, statusID);
		if (retStr.Length <= 0)
		{
			retStr = "－";
		}

		return retStr;
	}

	/// <summary>
	/// メニューボタン情報を取得する
	/// </summary>
	/// <returns></returns>
	public static ArrayList GetMenuButtonData()
	{
		ArrayList retList = new ArrayList();
		try
		{
			//ボタン数を取得
			int count = Configuration.GetInstance().GetDynamicPropertyInt("MenuForm.ButtonCount.Value");

			//ボタン数分ループする
			for (int i = 0; i < count; i++)
			{
				int index = i + 1;
				String key = "MenuForm.Button{0:D2}.Value";
				key = string.Format(key, index);
				string[] value = Configuration.GetInstance().GetDynamicPropertyString(key).Split(',');
				if (value != null && value.Length <= 3)
				{
					//掲示板非表示 2011.03.20 Add H.Orikasa A0008
					if (value[0] == CommonString.MENUBUTTON_ID_U1)
					{
						//現時点では非表示とする
						continue;
					}

					MasterItem item = new MasterItem(
						value[0],
						value[1],
						value[2]);
					retList.add(item);
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retList;
	}

	// 2010.06.23 Add T.Nishikubo Start
	/// <summary>
	/// メニューボタン情報を取得する
	/// </summary>
	/// <param name="dailyFlg">業務日誌機能（有効:true／無効:false）</param>
	/// <returns></returns>
	public static ArrayList GetMenuButtonData(bool dailyFlg)
	{
		ArrayList retList = new ArrayList();
		try
		{
			//ボタン数を取得
			int count = Configuration.GetInstance().GetDynamicPropertyInt("MenuForm.ButtonCount.Value");

			//ボタン数分ループする
			for (int i = 0; i < count; i++)
			{
				int index = i + 1;
				String key = "MenuForm.Button{0:D2}.Value";
				key = string.Format(key, index);
				string[] value = Configuration.GetInstance().GetDynamicPropertyString(key).Split(',');
				if (value != null && value.Length <= 3)
				{
					//掲示板非表示 2011.03.20 Add H.Orikasa
					if (!Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO) &&
						value[0] == CommonString.MENUBUTTON_ID_U1)
					{
						//現時点では関労以外非表示とする
						continue;
					}

					MasterItem item = new MasterItem(
						value[0],
						value[1],
						value[2]);
					if ((dailyFlg) || (!dailyFlg && !item.GetID().Equals(CommonString.MENUBUTTON_ID_T1)))
					{
						retList.add(item);
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retList;
	}
	// 2010.06.23 Add T.Nishikubo End

	/// <summary>
	/// 特定のメニューボタン情報を取得する
	/// </summary>
	/// <param name="windowsID">画面ID</param>
	/// <returns></returns>
	public static MasterItem GetMenuButtonItem(String windowsID)
	{
		MasterItem retItem = new MasterItem("","");
		try
		{
			ArrayList menuList = GetMenuButtonData();

			//メニューのループ
			for (int i=0; i<menuList.Count; i++)
			{
				MasterItem item = (MasterItem)menuList[i];
				if (item.GetID() == windowsID)
				{
					retItem = item;
					break;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retItem;
	}

	/// <summary>
	/// サブ画面アイテムを取得する
	/// </summary>
	/// <param name="keyStr">キー文字列</param>
	/// <returns></returns>
	public static MasterItem GetSubFormItem(String keyStr)
	{
		MasterItem retItem = new MasterItem("", "");
		try
		{
			string[] value = Configuration.GetInstance().GetDynamicPropertyString(keyStr).Split(',');
			if (value != null && value.Length <= 3)
			{
				retItem = new MasterItem(value[0], value[1], value[2]);
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retItem;
	}

	/// <summary>
	/// 画面表示タイプを取得
	/// </summary>
	/// <returns></returns>
	public static String GetDisplayType()
	{
		String retStr = CommonString.DISPLAYSIZE_TYPE_A;

		try
		{
			//画面表示タイプを取得
			retStr = Configuration.GetInstance().GetTermConfDataString("DisplayType.Value");
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retStr;
	}

	/// <summary>
	/// プルダウンを作成
	/// </summary>
	/// <param name="masterDt">マスタ情報</param>
	/// <param name="combo">プルダウン</param>
	/// <param name="emptyFlg">空行追加有無</param>
	/// <param name="keyColumnName">キーカラム名</param>
	/// <param name="valueColumnName">値カラム名</param>
	public static void CreateMasterComboBox(DataTable masterDt, NormalComboBox combo, bool emptyFlg,
		String keyColumnName, String valueColumnName)
	{
		CreateMasterComboBox(masterDt, combo, emptyFlg, keyColumnName, valueColumnName, "");
	}

	/// <summary>
	/// プルダウンを作成
	/// </summary>
	/// <param name="masterDt">マスタ情報</param>
	/// <param name="combo">プルダウン</param>
	/// <param name="emptyFlg">空行追加有無</param>
	/// <param name="keyColumnName">キーカラム名</param>
	/// <param name="valueColumnName">値カラム名</param>
	/// <param name="kTypeID">検査種別ID</param>
	public static void CreateMasterComboBox(DataTable masterDt, NormalComboBox combo, bool emptyFlg,
		String keyColumnName, String valueColumnName, String kTypeID)
	{
		combo.Items.Clear();
		combo.BeginUpdate();

		//空行判断
		if (emptyFlg)
		{
			MasterItem emptyItem = new MasterItem("", "");
			combo.Items.add(emptyItem);
		}

		//マスタ情報のループ
		for (int i = 0; i < masterDt.Rows.Count; i++)
		{
			DataRow row = masterDt.Rows[i];

			// 2011.04.18 Mod H.Orikasa Start
			//検査種別IDの指定がある場合は絞り込みを行う
			if (kTypeID.Length > 0 &&
				masterDt.Columns.Contains(MasterUtil.RIS_KENSATYPE_ID))
			{
				// 複数指定対応
				string[] kensatypeIDs = null;
				String kensatypeIDStr = row[MasterUtil.RIS_KENSATYPE_ID].toString();
				//区切り文字判断
				// カンマ
				if (kensatypeIDStr.IndexOf(",") != -1)
				{
					kensatypeIDs = kensatypeIDStr.Split(',');
				}
				// システム区切文字
				if (kensatypeIDStr.IndexOf(GetMarkerCharacter()) != -1)
				{
					kensatypeIDs = kensatypeIDStr.Split(GetMarkerCharacter());
				}

				// 単一or複数判定
				if (kensatypeIDs != null && kensatypeIDs.Length > 0 && kensatypeIDs[0].Length > 0)
				{
					// 複数
					bool matchFlg = false;
					for (int j=0; j<kensatypeIDs.Length; j++)
					{
						// 一致チェック
						if (kensatypeIDs[j] == kTypeID)
						{
							matchFlg = true;
							break;
						}
					}
					if (!matchFlg)
					{
						continue;
					}
				}
				else
				{
					// 単一
					if (!kensatypeIDStr.Equals(kTypeID))
					{
						continue;
					}
				}


			}
			// コメント
			////検査種別IDの指定がある場合は絞り込みを行う
			//if (kTypeID.Length > 0 &&
			//    masterDt.Columns.Contains(MasterUtil.RIS_KENSATYPE_ID) &&
			//    row[MasterUtil.RIS_KENSATYPE_ID].toString().IndexOf(kTypeID) == -1)
			//{
			//    continue;
			//}

			// 2011.04.18 Mod H.Orikasa End

			//使用フラグ判断
			if (!masterDt.Columns.Contains(MasterUtil.RIS_USEFLAG) ||
				row[MasterUtil.RIS_USEFLAG].toString() == CommonString.MASTER_USEFLAG_ON)
			{
				MasterItem masterItem = new MasterItem(
					masterDt.Rows[i][keyColumnName].toString(),
					masterDt.Rows[i][valueColumnName].toString(),
					masterDt.Rows[i]
					);
				combo.Items.add(masterItem);
			}
		}
		combo.EndUpdate();
	}

	/// <summary>
	/// 検査室に紐付く検査機器プルダウンを作成
	/// </summary>
	/// <param name="combo">プルダウン</param>
	/// <param name="roomID">検査室ID</param>
	public static void CreateKensakikiComboBox(NormalComboBox combo, String roomID)
	{
		if (roomID.Length > 0)
		{
			//部屋マスタより、対象となる検査室IDを取得する
			String kikiID = "";
			DataTable roomDt = Configuration.GetInstance().GetRRisExamRoomMaster();
			for (int i= 0; i<roomDt.Rows.Count; i++)
			{
				DataRow row = roomDt.Rows[i];

				if (row[MasterUtil.RIS_EXAMROOM_ID].toString() == roomID)
				{
					kikiID = row[MasterUtil.RIS_KENSAKIKI_ID].toString();
					break;
				}
			}

			string[] kikiIDs = kikiID.Split(',');

			combo.Items.Clear();
			combo.BeginUpdate();

			//空行を追加する
			MasterItem emptyItem = new MasterItem("", "");
			combo.Items.add(emptyItem);

			//機器マスタ情報のループ
			DataTable kikiDt = Configuration.GetInstance().GetRRisKensaKikiMaster();
			for (int i = 0; i < kikiDt.Rows.Count; i++)
			{
				DataRow row = kikiDt.Rows[i];

				//部屋に紐付く機器IDリストに無い場合は処理しない
				bool addFlg = false;
				for (int j=0; j<kikiIDs.Length; j++)
				{
					String checkKikiID = kikiIDs[j];
					if (checkKikiID.Length > 0 &&
						checkKikiID == row[MasterUtil.RIS_KENSAKIKI_ID].toString())
					{
						addFlg = true;
						break;
					}
				}

				if (!addFlg)
				{
					continue;
				}

				//使用フラグ判断
				if (row[MasterUtil.RIS_USEFLAG].toString() == CommonString.MASTER_USEFLAG_ON)
				{
					MasterItem masterItem = new MasterItem(
						kikiDt.Rows[i][MasterUtil.RIS_KENSAKIKI_ID].toString(),
						kikiDt.Rows[i][MasterUtil.RIS_KENSAKIKI_NAME].toString(),
						kikiDt.Rows[i]
						);
					combo.Items.add(masterItem);
				}
			}

			//機器が1件の場合は選択する
			if (combo.Items.Count == 2)
			{
				combo.SelectedIndex = 1;
			}

			combo.EndUpdate();
		}
		else
		{
			combo.Items.Clear();
		}
	}

	/// <summary>
	/// アイテムマスタプルダウンの選択を行う
	/// </summary>
	/// <param name="box"></param>
	/// <param name="value"></param>
	public static void SelectMasterItemComboBox(NormalComboBox box, String value)
	{
		if (box != null)
		{
			for (int i = 0; i < box.Items.Count; i++)
			{
				if (box.Items[i].GetType().Name == "MasterItem")
				{
					MasterItem item = (MasterItem)box.Items[i];
					if (item.GetID() == value)
					{
						box.SelectedIndex = i;
						break;
					}
				}
			}
		}
	}

	/// <summary>
	/// アイテムマスタプルダウンの選択を行う
	/// </summary>
	/// <param name="box"></param>
	/// <param name="value"></param>
	public static void SelectMasterItemComboBoxByName(NormalComboBox box, String value)
	{
		if (box != null)
		{
			for (int i = 0; i < box.Items.Count; i++)
			{
				if (box.Items[i].GetType().Name == "MasterItem")
				{
					MasterItem item = (MasterItem)box.Items[i];
					if (item.toString() == value)
					{
						box.SelectedIndex = i;
						break;
					}
				}
			}
		}
	}

	/// <summary>
	/// 「なし」用プルダウンを追加する
	/// </summary>
	/// <param name="box"></param>
	public static void AddNullSayuuComboboxItem(DataTable dt, NormalComboBox box)
	{
		if (dt != null)
		{
			for (int i=0; i<dt.Rows.Count; i++)
			{
				DataRow row = dt.Rows[i];

				if (row[MasterUtil.RIS_SAYUU_ID].toString() == CommonString.PULLDOWN_NULL_ID)
				{
					MasterItem item = new MasterItem(
						row[MasterUtil.RIS_SAYUU_ID].toString(),
						row[MasterUtil.RIS_SAYUU_NAME].toString());
					box.Items.add(item);

					break;
				}
			}
		}
	}

	// 2010.07.30 Add DD.Chinh Start
	/// <summary>
	/// 「なし」用データグリッドを追加する
	/// </summary>
	/// <param name="dt">マスタ情報</param>
	// 2010.07.30 Mod DD.Chinh Start
	/// <param name="gridData">データグリッドのデータソース</param>
	public static void AddNullSayuuDataGridItem(DataTable dt, ref DataTable gridData)
	///// <param name="grid">データグリッド</param>
	//public static void AddNullSayuuDataGridItem(DataTable dt, NormalOrderListDataGridView grid)
	// 2010.07.30 Mod DD.Chinh End
	{
		if (dt != null)
		{
			DataTable insDt = new DataTable();
			insDt.Columns.add(MasterUtil.RIS_SAYUU_NAME);
			insDt.Columns.add(MasterUtil.RIS_SAYUU_ID);

			for (int i=0; i<dt.Rows.Count; i++)
			{
				DataRow row = dt.Rows[i];

				if (row[MasterUtil.RIS_SAYUU_ID].toString() == CommonString.PULLDOWN_NULL_ID)
				{
					DataRow insRow = insDt.NewRow();
					insRow[MasterUtil.RIS_SAYUU_NAME] = row[MasterUtil.RIS_SAYUU_NAME].toString();
					insRow[MasterUtil.RIS_SAYUU_ID]	  = row[MasterUtil.RIS_SAYUU_ID].toString();
					insDt.Rows.add(insRow);
					break;
				}
			}
			gridData = insDt;
		}
	}
	// 2010.07.30 Add DD.Chinh End

	/// <summary>
	/// 検索条件用ステータス文字列の作成
	/// </summary>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	public static String CreateSearchStatusString(String status)
	{
		String retStr = "";
		String workStatus = "";

		// 各ステータス判断
		//未受(1)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_UNREGISTERED))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//遅刻(2)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_ISLATE))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//呼出(3)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_ISCALLING))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//4～10
		retStr += "0000000";
		//受済(11)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_ISREGISTERED))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//12～20
		retStr += "000000000";
		//検中(21)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_INOPERATION))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//中断(22)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_REST))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//23
		retStr += "0";
		//24
		retStr += "0";
		//再呼(25)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_RECALLING))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//再受(26)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_REREGISTERED))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//27～90
		for (int i=27; i<=90; i++)
		{
			retStr += "0";
		}
		//検済(91)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_ISFINISHED))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		//中止(92)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_STOP))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		// 2011.07.27 Mod H.Orikasa Start A0005
		//93～99
		for (int i=93; i<=99; i++)
		{
			retStr += "0";
		}
		//削除(100)
		workStatus = "0";
		if (IsStatusEnabled(status, CommonString.STATUS_DELETE))
		{
			workStatus = "1";
		}
		retStr += workStatus;
		// コメント
		////93～100
		//for (int i=93; i<=100; i++)
		//{
		//    retStr += "0";
		//}

		// 2011.07.27 Mod H.Orikasa End



		return retStr;
	}

	/// <summary>
	/// あるステータスがリスト内に存在するか否か
	/// </summary>
	/// <param name="status">ステータス（,区切り）</param>
	/// <param name="checkStatus">判断するステータス</param>
	/// <returns></returns>
	private static bool IsStatusEnabled(String status, String checkStatus)
	{
		bool retBool = false;

		string[] statusList = status.Split(',');

		//リストのループ
		for (int i = 0; i < statusList.Length; i++)
		{
			String statusStr = statusList[i];

			if (statusStr == checkStatus)
			{
				retBool = true;
			}
		}
		return retBool;
	}

	/// <summary>
	/// 選択行のDataRowを取得する
	/// </summary>
	/// <param name="gridView">グリッド情報</param>
	/// <returns>選択行のDataRow。選択行が無い場合はnullを返す。</returns>
	public static DataRow GetSelectRow(NormalOrderListDataGridView gridView)
	{
		DataRow retRow = null;

		try
		{
			// 2011.06.27 Mod Yk.Suzuki@CIJ Start NML-CAT1-007
			if (gridView.SelectedRows.Count > 0)
			{
				DataGridViewRow dRow = gridView.CurrentRow;
				if (dRow != null && dRow.DataBoundItem != null)
				{
					DataRowView drv = (DataRowView)dRow.DataBoundItem;
					if (drv != null && drv.Row != null)
					{
						retRow = (DataRow)drv.Row;
					}
				}
			}
			// コメント
			//DataGridViewRow dRow = gridView.CurrentRow;
			//if (dRow != null && dRow.DataBoundItem != null)
			//{
			//    DataRowView drv = (DataRowView)dRow.DataBoundItem;
			//    if (drv != null && drv.Row != null)
			//    {
			//        retRow = (DataRow)drv.Row;
			//    }
			//}
			// コメント
			// 2011.06.27 Mod Yk.Suzuki@CIJ End
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retRow;
	}

	/// <summary>
	/// フォーカスを再設定する
	/// </summary>
	/// <param name="dataGridView">一覧</param>
	/// <param name="keyColName">キーカラム名称</param>
	/// <param name="key">キー</param>
	public static void ReFocusRow(NormalOrderListDataGridView dataGridView, String keyColName, String key)
	{
		int index = -1;
		for (int i=0; i<dataGridView.Rows.Count; i++)
		{
			DataGridViewRow row =dataGridView.Rows[i];
			DataRowView rowView = (DataRowView)row.DataBoundItem;
			if (rowView.Row[keyColName].toString() == key)
			{
				index = i;
				break;
			}
		}

		if (index != -1)
		{
			//行を再選択
			// 2011.06.27 Add Yk.Suzuki@CIJ Start NML-CAT1-007
			if (dataGridView.CurrentCell == null)
			{
				dataGridView.CurrentCell = dataGridView[0, index];
			}
			else
			{
				dataGridView.CurrentCell = dataGridView[
					dataGridView.CurrentCell.ColumnIndex,
					index];
			}
			// コメント
			//dataGridView.CurrentCell = dataGridView[
			//    dataGridView.CurrentCell.ColumnIndex,
			//    index];
			// コメント
			// 2011.06.27 Add Yk.Suzuki@CIJ End

			dataGridView.Rows[index].Selected = true;
		}
	}


	/// <summary>
	/// Imageオブジェクトの取得
	/// </summary>
	/// <param name="image"></param>
	/// <param name="fileName"></param>
	/// <param name="patientId"></param>
	/// <returns></returns>
	public static Image GetImageFile(String filePath)
	{

		if (System.IO.File.Exists(filePath))
		{
			System.IO.FileStream fs = new System.IO.FileStream(filePath, System.IO.FileMode.Open, System.IO.FileAccess.Read);
			Image tempImage = Image.FromStream(fs);
			fs.Close();
			return tempImage;
		}

		return null;
	}

	/// <summary>
	/// 画像参照を行う(URL連携)
	/// </summary>
	/// <param name="bean">条件</param>
	/// <returns></returns>
	public static bool ShowImageURL(OrderInfoBean bean)
	{
		bool retBool = false;
		logger.Debug("begin");
		try
		{
			//画像連携使用ﾌﾗｸﾞ判断
			if (Configuration.GetInstance().GetImageUrlFlag() == CommonString.IMAGE_URL_VALUE)
			{
				//ブラウザパスを取得
				String brawserPath = Configuration.GetInstance().GetDynamicPropertyString("BrowserPath.Value");
				if (!File.Exists(brawserPath))
				{
					//ブラウザパスが存在しない場合は警告メッセージを表示
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getBrowserNotFound_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
					return retBool;
				}

				//URLを取得
				String urlStr  = Configuration.GetInstance().GetImageUrl();
				String urlPath = "";
				if (urlStr.IndexOf('?') != -1)
				{
					urlPath = urlStr.Substring(0, urlStr.IndexOf('?'));
				}
				if (urlPath.Length <= 0)
				{
					//URLが未設定の場合は警告メッセージを表示
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getUrlPathNotFound_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
					return retBool;
				}

				//URL連携を行う
				ShowUrl(bean, brawserPath, urlStr, urlPath);

				retBool = true;
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		logger.Debug("end");

		return retBool;
	}

	/// <summary>
	/// レポート参照を行う(URL連携)
	/// </summary>
	/// <param name="bean">条件</param>
	/// <returns></returns>
	public static bool ShowReportURL(OrderInfoBean bean)
	{
		bool retBool = false;
		logger.Debug("begin");
		try
		{
			//レポート連携使用ﾌﾗｸﾞ判断
			if (Configuration.GetInstance().GetReportUrlFlag() == CommonString.REPORT_URL_VALUE)
			{
				//ブラウザパスを取得
				String brawserPath = Configuration.GetInstance().GetDynamicPropertyString("BrowserPath.Value");
				if (!File.Exists(brawserPath))
				{
					//ブラウザパスが存在しない場合は警告メッセージを表示
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getBrowserNotFound_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
					return retBool;
				}

				//URLを取得
				String urlStr  = Configuration.GetInstance().GetReportUrl();
				String urlPath = urlStr.Substring(0, urlStr.IndexOf('?'));
				if (urlPath.Length <= 0)
				{
					//URLが未設定の場合は警告メッセージを表示
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getUrlPathNotFound_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
					return retBool;
				}

				//URL連携を行う
				ShowUrl(bean, brawserPath, urlStr, urlPath);

				retBool = true;
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		logger.Debug("end");

		return retBool;
	}

	/// <summary>
	/// シェーマ参照を行う(URL連携)
	/// </summary>
	/// <param name="bean">条件</param>
	/// <returns></returns>
	public static bool ShowShemaURL(OrderInfoBean bean)
	{
		bool retBool = false;
		logger.Debug("begin");
		try
		{
			//シェーマ連携使用ﾌﾗｸﾞ判断
			if (Configuration.GetInstance().GetSchemaUrlFlag() == CommonString.SCHEMA_URL_VALUE)
			{
				//ブラウザパスを取得
				String brawserPath = Configuration.GetInstance().GetDynamicPropertyString("BrowserPath.Value");
				if (!File.Exists(brawserPath))
				{
					//ブラウザパスが存在しない場合は警告メッセージを表示
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getBrowserNotFound_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
					return retBool;
				}

				//URLを取得
				String urlStr  = Configuration.GetInstance().GetSchemaUrl();
				String urlPath = urlStr.Substring(0, urlStr.IndexOf('?'));
				if (urlPath.Length <= 0)
				{
					//URLが未設定の場合は警告メッセージを表示
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getUrlPathNotFound_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
					return retBool;
				}
				if (bean.GetExtendOrderInfoBean().GetShemaurl().Length > 0)
				{
					String shemaURL = bean.GetExtendOrderInfoBean().GetShemaurl();
					urlStr  = urlStr.Replace(CommonString.URL_KEY_SHEMA_URL, shemaURL);
					urlPath = urlPath.Replace(CommonString.URL_KEY_SHEMA_URL, shemaURL);
				}

				//URL連携を行う
				// 2011.03.30 Mod K.Shinohara Start A0012
				ShowUrl(bean, brawserPath, urlStr, urlPath, true);
				//ShowUrl(bean, brawserPath, urlStr, urlPath);
				// 2011.03.30 Mod K.Shinohara End

				retBool = true;
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		logger.Debug("end");

		return retBool;
	}

	/// <summary>
	/// 過去情報を取得（同一患者ID、検査種別、直近(KensaDate Desc)）
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <returns></returns>
	private static bool GetURLBeforeData(OrderInfoBean orderBean)
	{
		bool retBool = false;
		try
		{
			// 2010.10.30 Del K.Shinohara Start
			////検査種別マスタ準備
			//DataTable kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster();
			//MasterUtil mUtil = new MasterUtil();
			// 2010.10.30 Del K.Shinohara End

			// 2010.10.30 Add K.Shinohara Start
			String beforeRisID			= string.Empty;
			String beforeKensaKikiID	= string.Empty;
			// 2010.10.30 Add K.Shinohara End

			OrderSearchParameter oParam = new OrderSearchParameter();
			oParam.SetKanjaIDOnlyBool(true);
			oParam.SetKanjaID(orderBean.GetKanjaID());
			oParam.SetRrisKensaTypeID(orderBean.GetKensatypeID());
			// 2010.10.30 Add K.Shinohara Start
			oParam.SetRrisStatus(CommonString.STATUS_ISFINISHED);
			// 2010.10.30 Add K.Shinohara End
			DataTable dt = Configuration.GetInstance().GetCoreController().GetRisOrderList(oParam);
			DataTable newTable = dt.Clone();
			newTable.Rows.Clear();
			//当該オーダ除外＆検査日の降順でソート
			// 2010.10.07 Mod K.Shinohara Start
			String where = RisSummaryView.RIS_ID_COLUMN + " <> '" + orderBean.GetRisID() + "'";
			//String where = RisSummaryView.RIS_ID_COLUMN + " != '" + orderBean.GetRisID() + "'";
			// 2010.10.07 Mod K.Shinohara End
			DataRow[] rows = dt.Select(where, RisSummaryView.KENSA_DATE_COLUMN + CommonString.SORT_DESC);
			if (rows != null && rows.Length > 0)
			{
				retBool = true;

				//過去オーダ情報
				DataRow row = rows[0];
				String beforeKensaDate		= row[RisSummaryView.KENSA_DATE_COLUMN].toString();
				String beforeKensatypeID	= row[RisSummaryView.KENSATYPE_ID_COLUMN].toString();
				// 2010.10.30 Del K.Shinohara Start
				//String beforeModalityType	= row[RisSummaryView.MODALITY_TYPE_COLUMN].toString();
				// 2010.10.30 Del K.Shinohara End
				String beforeAccessionNo	= row[RisSummaryView.ACNO_COLUMN].toString();

				// 2011.03.22 Add K.Shinohara Start A0011
				String beforeOrderNo		= row[RisSummaryView.ORDERNO_COLUMN].toString();
				// 2011.03.22 Add K.Shinohara End

				// 2010.10.30 Add K.Shinohara Start
				beforeRisID					= row[RisSummaryView.RIS_ID_COLUMN].toString();
				beforeKensaKikiID			= row[RisSummaryView.KENSAKIKI_ID_COLUMN].toString();
				// 2010.10.30 Add K.Shinohara End

				// 2010.10.30 Del K.Shinohara Start
				////検査機器マスタのモダリティタイプが空の場合は検査種別マスタのモダリティを設定
				//if (beforeModalityType.Length <= 0)
				//{
				//    beforeModalityType = mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, beforeKensatypeID);
				//}
				// 2010.10.30 Del K.Shinohara End

				//補填
				orderBean.SetKensaDate(beforeKensaDate);
				orderBean.SetAccessionNo(beforeAccessionNo);

				// 2011.03.22 Add K.Shinohara Start A0011
				orderBean.SetOrderNo(beforeOrderNo);
				// 2011.03.22 Add K.Shinohara End

				// 2010.10.30 Del K.Shinohara Start
				//orderBean.SetModalityType(beforeModalityType);
				// 2010.10.30 Del K.Shinohara End
			}
			// 2010.10.30 Add K.Shinohara Start
			// 何も取得できなかった場合は空欄がセットされる
			orderBean.SetRisID(beforeRisID);
			orderBean.SetKensakikiID(beforeKensaKikiID);
			// 2010.10.30 Add K.Shinohara End
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retBool;
	}

	/// <summary>
	/// パラメータを元に条件リストを準備する
	/// </summary>
	/// <param name="path">URL</param>
	/// <param name="specialFlg">特殊フラグ</param>
	/// <param name="bean">オーダ情報</param>
	/// <returns></returns>
	private static ArrayList GetURLParamList(String path, bool specialFlg, OrderInfoBean bean)
	{
		ArrayList retList = new ArrayList();
		try
		{
			String paramStr = path.Substring(path.IndexOf('?')+1);

			//パラメータを分割する
			string[] paramList = paramStr.Split('&');

			//パラメータ毎に条件リストを準備する
			for (int i=0; i<paramList.Length; i++)
			{
				String param = paramList[i];

				String value = "";
				if (param.IndexOf(CommonString.URL_KEY_USER_ID) != -1)
				{
					//ユーザID
					value = Configuration.GetInstance().GetUserAccountBean().GetUserID();
					value = param.Replace(CommonString.URL_KEY_USER_ID, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (Configuration.GetInstance().GetUserAccountBean().GetUserID().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End
				}
				else if (param.IndexOf(CommonString.URL_KEY_USER_PW) != -1)
				{
					//ユーザパスワード
					value = Configuration.GetInstance().GetUserAccountBean().GetPassword();
					value = param.Replace(CommonString.URL_KEY_USER_PW, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (Configuration.GetInstance().GetUserAccountBean().GetPassword().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End
				}
				else if (param.IndexOf(CommonString.URL_KEY_KANJA_ID) != -1)
				{
					//患者ID
					value = bean.GetKanjaID();
					value = param.Replace(CommonString.URL_KEY_KANJA_ID, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (bean.GetKanjaID().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End
				}
				else if (param.IndexOf(CommonString.URL_KEY_EXAM_DATE) != -1)
				{
					//検査日
					value = bean.GetKensaDate().toString();
					value = param.Replace(CommonString.URL_KEY_EXAM_DATE, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (bean.GetKensaDate().toString().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End

					//特殊フラグON時は追加しない
					if (specialFlg)
					{
						continue;
					}
				}
				else if (param.IndexOf(CommonString.URL_KEY_MODALITY_TYPE) != -1)
				{
					//モダリティ
					value = bean.GetModalityType();
					value = param.Replace(CommonString.URL_KEY_MODALITY_TYPE, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (bean.GetModalityType().toString().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End
				}
				else if (param.IndexOf(CommonString.URL_KEY_AC_NO) != -1)
				{
					//AccessionNo
					value = bean.GetAccessionNo();
					value = param.Replace(CommonString.URL_KEY_AC_NO, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (bean.GetAccessionNo().toString().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End

					//特殊フラグON時は追加しない
					if (specialFlg)
					{
						continue;
					}
				}
				else if (param.IndexOf(CommonString.URL_KEY_ORDER_NO) != -1)
				{
					//オーダ番号
					value = bean.GetOrderNo();
					value = param.Replace(CommonString.URL_KEY_ORDER_NO, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (bean.GetOrderNo().toString().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End

					//特殊フラグON時は追加しない
					if (specialFlg)
					{
						continue;
					}
				}
				else if (param.IndexOf(CommonString.URL_KEY_RIS_ID) != -1)
				{
					//RisID
					value = bean.GetRisID();
					value = param.Replace(CommonString.URL_KEY_RIS_ID, value);

					// 2010.10.30 Add K.Shinohara Start
					// 設定値が存在しない場合は追加しない
					if (bean.GetRisID().toString().Equals(string.Empty))
					{
						continue;
					}
					// 2010.10.30 Add K.Shinohara End

					//特殊フラグON時は追加しない
					if (specialFlg)
					{
						continue;
					}
				}
				// 2011.03.23 Add K.Shinohara Start A0012
				else if (param.IndexOf(CommonString.URL_KEY_EXAM_DATE2) != -1)
				{
					//検査日
					value = bean.GetKensaDate().toString();
					value = param.Replace(CommonString.URL_KEY_EXAM_DATE2, value);

					// 設定値が存在しない場合は追加しない
					if (bean.GetKensaDate().toString().Equals(string.Empty))
					{
						continue;
					}
				}
				else if (param.IndexOf(CommonString.URL_KEY_AC_NO2) != -1)
				{
					//AccessionNo
					value = bean.GetAccessionNo();
					value = param.Replace(CommonString.URL_KEY_AC_NO2, value);

					// 設定値が存在しない場合は追加しない
					if (bean.GetAccessionNo().toString().Equals(string.Empty))
					{
						continue;
					}
				}
				else if (param.IndexOf(CommonString.URL_KEY_ORDER_NO2) != -1)
				{
					//オーダ番号
					value = bean.GetOrderNo();
					value = param.Replace(CommonString.URL_KEY_ORDER_NO2, value);

					// 設定値が存在しない場合は追加しない
					if (bean.GetOrderNo().toString().Equals(string.Empty))
					{
						continue;
					}
				}
				else if (param.IndexOf(CommonString.URL_KEY_RIS_ID2) != -1)
				{
					//RisID
					value = bean.GetRisID();
					value = param.Replace(CommonString.URL_KEY_RIS_ID2, value);

					// 設定値が存在しない場合は追加しない
					if (bean.GetRisID().toString().Equals(string.Empty))
					{
						continue;
					}
				}
				// 2011.03.23 Add K.Shinohara End
				else
				{
					//パラメータに該当しない値はそのまま追加
					value = param;
				}

				//値があった場合は追加
				if (value.Length > 0)
				{
					//条件リストへ追加
					retList.add(value);
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retList;
	}

	// 2011.03.23 Add K.Shinohara Start A0012
	/// <summary>
	/// パラメータを元に条件リストを準備する
	/// </summary>
	/// <param name="path">URL</param>
	/// <param name="specialFlg">特殊フラグ</param>
	/// <param name="bean">オーダ情報</param>
	/// <returns></returns>
	private static bool CheckURLParam(String path)
	{
		bool retFlg = false;
		try
		{
			String paramStr = path.Substring(path.IndexOf('?')+1);

			//パラメータを分割する
			string[] paramList = paramStr.Split('&');

			//パラメータ毎に条件リストを準備する
			for (int i=0; i<paramList.Length; i++)
			{
				String param = paramList[i];

				// 常に今回情報を使うパラメータが存在した場合
				if ((param.IndexOf(CommonString.URL_KEY_EXAM_DATE2) != -1) ||
					(param.IndexOf(CommonString.URL_KEY_AC_NO2) != -1) ||
					(param.IndexOf(CommonString.URL_KEY_ORDER_NO2) != -1) ||
					(param.IndexOf(CommonString.URL_KEY_RIS_ID2) != -1))
				{
					retFlg = true;
					break;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retFlg;
	}
	// 2011.03.23 Add K.Shinohara End

	// 2011.03.30 Mod K.Shinohara Start A0012
	/// <summary>
	/// URL連携を行う
	/// </summary>
	/// <param name="bean">オーダ情報</param>
	/// <param name="brawserPath">ブラウザパス</param>
	/// <param name="urlStr">URL</param>
	/// <param name="urlPath">URLパラメータ</param>
	private static void ShowUrl(OrderInfoBean bean, String brawserPath, String urlStr, String urlPath)
	{
		// URL連携（シェーマ以外）
		ShowUrl(bean, brawserPath, urlStr, urlPath, false);
	}

	/// <summary>
	/// URL連携を行う
	/// </summary>
	/// <param name="bean">オーダ情報</param>
	/// <param name="brawserPath">ブラウザパス</param>
	/// <param name="urlStr">URL</param>
	/// <param name="urlPath">URLパラメータ</param>
	/// <param name="shemaFlg">シェーマフラグ</param>
	private static void ShowUrl(OrderInfoBean bean, String brawserPath, String urlStr, String urlPath, bool shemaFlg)
	{
		logger.Debug("begin");
		try
		{
			// RIS_ID未指定は処理しない
			if (bean.GetRisID().Length <= 0)
			{
				return;
			}

			// オーダ情報を取得
			bean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(bean.GetRisID());

			// 実績情報を取得
			ExecutionInfoBean exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(bean.GetRisID());
			// ステータスを取得
			String statusStr = exBean.GetStatus();

			// 特殊フラグ
			bool specialFlg = false;

			// 今回用パラメータが登録されていない場合
			if (!CheckURLParam(urlStr))
			{
				bool beforeFlg = false;
				if (statusStr != CommonString.STATUS_ISFINISHED)
				{
					// 自身が検済以外の場合は過去情報を取得（同一患者ID、検査種別、直近(KensaDate Desc)）
					beforeFlg = GetURLBeforeData(bean);
				}
				else
				{
					// 一部情報を実績側の情報に置き換える
					bean.SetKensaDate(exBean.GetKensaDate());
					bean.SetKensakikiID(exBean.GetKensaKikiID());
				}

				// 特殊フラグ
				if (statusStr != CommonString.STATUS_ISFINISHED && !beforeFlg)
				{
					// 自身が検済以外＆過去データがない場合
					specialFlg = true;
				}
			}
			else
			{
				// シェーマ以外の場合は進捗により情報を置き換える
				if (!shemaFlg && TextUtil.ParseStringToInt(statusStr) >= TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED))
				{
					// 一部情報を実績側の情報に置き換える
					bean.SetKensaDate(exBean.GetKensaDate());
					bean.SetKensakikiID(exBean.GetKensaKikiID());
				}
			}
			// モダリティタイプの設定
			MasterUtil mUtil = new MasterUtil();

			// 検査機器マスタのモダリティタイプを取得する
			DataTable kKikiDt = Configuration.GetInstance().GetRRisKensaKikiMaster();
			if (kKikiDt != null)
			{
				bean.SetModalityType(mUtil.FindData(kKikiDt, MasterUtil.RIS_MODALITY_TYPE, MasterUtil.RIS_KENSAKIKI_ID, bean.GetKensakikiID()));
			}

			// 検査機器マスタのモダリティタイプが空の場合は検査種別マスタのモダリティを設定
			if (bean.GetModalityType().Length <= 0)
			{
				// 検査種別マスタ準備
				DataTable kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster();

				bean.SetModalityType(mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, bean.GetKensatypeID()));
			}


			// 条件リストを準備する
			ArrayList paramList = GetURLParamList(urlStr, specialFlg, bean);

			// URLを作成する
			String whereStr = "";
			for (int i=0; i<paramList.Count; i++)
			{
				if (whereStr.Length <= 0)
				{
					whereStr = paramList[i].toString();
				}
				else
				{
					whereStr += "&" + paramList[i];
				}
			}
			if (whereStr.Length > 0)
			{
				urlPath += "?" + whereStr;
			}

			// URLをログ出力する
			logger.info("連携URL[" + urlPath + "]");

			// URL連携を行う
			System.Diagnostics.ProcessStartInfo processStartInfo =
							new System.Diagnostics.ProcessStartInfo();
			processStartInfo.Arguments = urlPath;
			processStartInfo.FileName = brawserPath;
			System.Diagnostics.Process.Start(processStartInfo);
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		logger.Debug("end");
	}
	// コメント
	///// <summary>
	///// URL連携を行う
	///// </summary>
	///// <param name="bean">オーダ情報</param>
	///// <param name="brawserPath">ブラウザパス</param>
	///// <param name="urlStr">URL</param>
	///// <param name="urlPath">URLパラメータ</param>
	//private static void ShowUrl(OrderInfoBean bean, String brawserPath, String urlStr, String urlPath)
	//{
	//    logger.Debug("begin");
	//    try
	//    {
	//        //RIS_ID未指定は処理しない
	//        if (bean.GetRisID().Length <= 0)
	//        {
	//            return;
	//        }

	//        //オーダ情報を取得
	//        bean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(bean.GetRisID());

	//        // 2010.10.30 Mod K.Shinohara Start
	//        // 実績情報を取得
	//        ExecutionInfoBean exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(bean.GetRisID());
	//        //ステータスを取得
	//        String statusStr = exBean.GetStatus();
	//        ////ステータスを取得
	//        //String statusStr = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(bean.GetRisID()).GetStatus();
	//        // 2010.10.30 Mod K.Shinohara End

	//        // 2011.03.23 Mod K.Shinohara Start A0012
	//        // 特殊フラグ
	//        bool specialFlg = false;

	//        // 今回用パラメータが登録されていない場合
	//        if (!CheckURLParam(urlStr))
	//        {
	//            bool beforeFlg = false;
	//            if (statusStr != CommonString.STATUS_ISFINISHED)
	//            {
	//                //自身が検済以外の場合は過去情報を取得（同一患者ID、検査種別、直近(KensaDate Desc)）
	//                beforeFlg = GetURLBeforeData(bean);
	//            }
	//            else
	//            {
	//                // 一部情報を実績側の情報に置き換える
	//                bean.SetKensaDate(exBean.GetKensaDate());
	//                bean.SetKensakikiID(exBean.GetKensaKikiID());
	//            }

	//            // 特殊フラグ
	//            if (statusStr != CommonString.STATUS_ISFINISHED && !beforeFlg)
	//            {
	//                //自身が検済以外＆過去データがない場合
	//                specialFlg = true;
	//            }
	//        }
	//        else
	//        {
	//            if (TextUtil.ParseStringToInt(statusStr) >= TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED))
	//            {
	//                // 一部情報を実績側の情報に置き換える
	//                bean.SetKensaDate(exBean.GetKensaDate());
	//                bean.SetKensakikiID(exBean.GetKensaKikiID());
	//            }
	//        }
	//        // モダリティタイプの設定
	//        MasterUtil mUtil = new MasterUtil();

	//        // 検査機器マスタのモダリティタイプを取得する
	//        DataTable kKikiDt = Configuration.GetInstance().GetRRisKensaKikiMaster();
	//        if (kKikiDt != null)
	//        {
	//            bean.SetModalityType(mUtil.FindData(kKikiDt, MasterUtil.RIS_MODALITY_TYPE, MasterUtil.RIS_KENSAKIKI_ID, bean.GetKensakikiID()));
	//        }

	//        // 検査機器マスタのモダリティタイプが空の場合は検査種別マスタのモダリティを設定
	//        if (bean.GetModalityType().Length <= 0)
	//        {
	//            //検査種別マスタ準備
	//            DataTable kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster();

	//            bean.SetModalityType(mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, bean.GetKensatypeID()));
	//        }
	//

	//        // コメント
	//        ////過去判断
	//        //bool beforeFlg = false;
	//        //if (statusStr != CommonString.STATUS_ISFINISHED)
	//        //{
	//        //    //自身が検済以外の場合は過去情報を取得（同一患者ID、検査種別、直近(KensaDate Desc)）
	//        //    beforeFlg = GetURLBeforeData(bean);
	//        //}
	//        //// 2010.10.30 Add K.Shinohara Start
	//        //else
	//        //{
	//        //    // 一部情報を実績側の情報に置き換える
	//        //    bean.SetKensaDate(exBean.GetKensaDate());
	//        //    bean.SetKensakikiID(exBean.GetKensaKikiID());
	//        //}
	//        //// 2010.10.30 Add K.Shinohara End

	//        //// 2010.10.30 Add K.Shinohara Start
	//        //// モダリティタイプの設定
	//        //MasterUtil mUtil = new MasterUtil();

	//        //// 検査機器マスタのモダリティタイプを取得する
	//        //DataTable kKikiDt = Configuration.GetInstance().GetRRisKensaKikiMaster();
	//        //if (kKikiDt != null)
	//        //{
	//        //    bean.SetModalityType(mUtil.FindData(kKikiDt, MasterUtil.RIS_MODALITY_TYPE, MasterUtil.RIS_KENSAKIKI_ID, bean.GetKensakikiID()));
	//        //}

	//        //// 検査機器マスタのモダリティタイプが空の場合は検査種別マスタのモダリティを設定
	//        //if (bean.GetModalityType().Length <= 0)
	//        //{
	//        //    //検査種別マスタ準備
	//        //    DataTable kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster();

	//        //    bean.SetModalityType(mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, bean.GetKensatypeID()));
	//        //}
	//        //
	//        //// 2010.10.30 Add K.Shinohara End

	//        ////特殊フラグ
	//        //bool specialFlg = false;
	//        //if (statusStr != CommonString.STATUS_ISFINISHED && !beforeFlg)
	//        //{
	//        //    //自身が検済以外＆過去データがない場合
	//        //    specialFlg = true;
	//        //}
	//
	//        // 2011.03.23 Mod K.Shinohara

	//        //条件リストを準備する
	//        ArrayList paramList = GetURLParamList(urlStr, specialFlg, bean);

	//        //URLを作成する
	//        String whereStr = "";
	//        for (int i=0; i<paramList.Count; i++)
	//        {
	//            if (whereStr.Length <= 0)
	//            {
	//                whereStr = paramList[i].toString();
	//            }
	//            else
	//            {
	//                whereStr += "&" + paramList[i];
	//            }
	//        }
	//        if (whereStr.Length > 0)
	//        {
	//            urlPath += "?" + whereStr;
	//        }

	//        // 2010.10.30 Add K.Shinohara Start
	//        // URLをログ出力する
	//        logger.info("連携URL[" + urlPath + "]");
	//        // 2010.10.30 Add K.Shinohara End

	//        //URL連携を行う
	//        System.Diagnostics.ProcessStartInfo processStartInfo =
	//                        new System.Diagnostics.ProcessStartInfo();
	//        processStartInfo.Arguments = urlPath;
	//        processStartInfo.FileName = brawserPath;
	//        System.Diagnostics.Process.Start(processStartInfo);
	//    }
	//    catch (Exception ex)
	//    {
	//        logger.fatal(ex);
	//    }
	//    logger.Debug("end");
	//}

	// 2011.03.30 Mod K.Shinohara End

	// コメント

	///// <summary>
	///// 画像参照を行う(URL連携)
	///// </summary>
	///// <param name="bean">条件</param>
	///// <param name="showBeforeOrderFlg">過去画像表示フラグ</param>
	///// <returns></returns>
	//public static bool ShowImageURL(OrderInfoBean bean, bool showBeforeOrderFlg)
	//{
	//    bool retBool = false;
	//    logger.Debug("begin");
	//    try
	//    {
	//        //画像連携使用ﾌﾗｸﾞ判断
	//        if (Configuration.GetInstance().GetImageUrlFlag() == CommonString.IMAGE_URL_VALUE)
	//        {
	//            //ブラウザパスを取得
	//            String brawserPath = GetDynamicPropertyString("BrowserPath.Value");
	//            if (!File.Exists(brawserPath))
	//            {
	//                //ブラウザパスが存在しない場合は警告メッセージを表示
	//                String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getBrowserNotFound_MessageString);
	//                Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
	//                return retBool;
	//            }

	//            //URLを取得
	//            String urlPath = Configuration.GetInstance().GetImageUrl();
	//            if (urlPath.Length <= 0)
	//            {
	//                //URLが未設定の場合は警告メッセージを表示
	//                String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getUrlPathNotFound_MessageString);
	//                Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
	//                return retBool;
	//            }

	//            //検査種別マスタ準備
	//            DataTable kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster();
	//            MasterUtil mUtil = new MasterUtil();

	//            //今回画像or過去画像の判定
	//            if (showBeforeOrderFlg)
	//            {
	//                // 過去画像表示の場合、条件の補完を行う

	//                //過去情報を取得（同一患者ID、検査種別、検済、直近(ExamEndDate Desc））
	//                OrderSearchParameter oParam = new OrderSearchParameter();
	//                oParam.SetKanjaIDOnlyBool(true);
	//                oParam.SetKanjaID(bean.GetKanjaID());
	//                oParam.SetRrisKensaTypeID(bean.GetKensatypeID());
	//                oParam.SetRrisStatus(CommonString.STATUS_ISFINISHED);
	//                DataTable dt = Configuration.GetInstance().GetCoreController().GetRisOrderList(oParam);
	//                DataTable newTable = dt.Clone();
	//                newTable.Rows.Clear();
	//                //検査終了時刻の降順でソート
	//                DataRow[] rows = dt.Select("", RisSummaryView.EXAMENDDATE_COLUMN + CommonString.SORT_DESC);
	//                foreach (DataRow row in rows)
	//                {
	//                    String beforeKensaDate    = row[RisSummaryView.KENSA_DATE_COLUMN].toString();
	//                    String beforeKensatypeID  = row[RisSummaryView.KENSATYPE_ID_COLUMN].toString();
	//                    String beforeModalityType = row[RisSummaryView.MODALITY_TYPE_COLUMN].toString();
	//                    String beforeAccessionNo  = row[RisSummaryView.ACNO_COLUMN].toString();

	//                    //検査機器マスタのモダリティタイプが空の場合は検査種別マスタのモダリティを設定
	//                    if (beforeModalityType.Length <= 0)
	//                    {
	//                        beforeModalityType = mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, beforeKensatypeID);
	//                    }

	//                    //補填
	//                    bean.SetKensaDate(beforeKensaDate);
	//                    bean.SetAccessionNo(beforeAccessionNo);
	//                    bean.SetModalityType(beforeModalityType);

	//                    break;
	//                }
	//
	//            }
	//            else
	//            {
	//                //今回画像の場合、検査種別マスタのモダリティを設定
	//                String modalityType = mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, bean.GetKensatypeID());
	//                bean.SetModalityType(modalityType);
	//            }

	//            // 条件を準備する
	//            String whereStr = "";
	//            //ユーザID
	//            if (Configuration.GetInstance().GetImgUserLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetImgUserLabel();
	//                String value  = Configuration.GetInstance().GetUserAccountBean().GetUserID();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //パスワード
	//            if (Configuration.GetInstance().GetImgPasswordLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetImgPasswordLabel();
	//                String value  = Configuration.GetInstance().GetUserAccountBean().GetPassword();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //患者ID
	//            if (Configuration.GetInstance().GetImgPidLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetImgPidLabel();
	//                String value  = bean.GetKanjaID();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //検査日
	//            if (Configuration.GetInstance().GetImgDateLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetImgDateLabel();
	//                String value  = bean.GetKensaDate().toString();
	//                if (value.Length > 0)
	//                {
	//                    whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//                }
	//            }
	//            //AccessionNo
	//            if (Configuration.GetInstance().GetImgAcnoLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetImgAcnoLabel();
	//                String value  = bean.GetAccessionNo();
	//                if (value.Length > 0)
	//                {
	//                    whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//                }
	//            }
	//            //モダリティ
	//            if (Configuration.GetInstance().GetImgModalityLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetImgModalityLabel();
	//                String value  = bean.GetModalityType();
	//                if (value.Length > 0)
	//                {
	//                    whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//                }
	//            }
	//

	//            //URLを作成する
	//            if (whereStr.Length > 0)
	//            {
	//                urlPath += "?" + whereStr;
	//            }

	//            //URL連携を行う
	//            System.Diagnostics.ProcessStartInfo processStartInfo =
	//                        new System.Diagnostics.ProcessStartInfo();
	//            processStartInfo.Arguments = urlPath;
	//            processStartInfo.FileName = brawserPath;
	//            System.Diagnostics.Process.Start(processStartInfo);

	//            retBool = true;
	//        }
	//    }
	//    catch (Exception ex)
	//    {
	//        logger.fatal(ex);
	//    }
	//    logger.Debug("end");

	//    return retBool;
	//}

	///// <summary>
	///// レポート参照を行う(URL連携)
	///// </summary>
	///// <param name="bean">条件</param>
	///// <param name="showBeforeOrderFlg">過去レポート表示フラグ</param>
	///// <returns></returns>
	//public static bool ShowReportURL(OrderInfoBean bean, bool showBeforeOrderFlg)
	//{
	//    bool retBool = false;
	//    logger.Debug("begin");
	//    try
	//    {
	//        //レポート連携使用ﾌﾗｸﾞ判断
	//        if (Configuration.GetInstance().GetReportUrlFlag() == CommonString.REPORT_URL_VALUE)
	//        {
	//            //ブラウザパスを取得
	//            String brawserPath = GetDynamicPropertyString("BrowserPath.Value");
	//            if (!File.Exists(brawserPath))
	//            {
	//                //ブラウザパスが存在しない場合は警告メッセージを表示
	//                String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getBrowserNotFound_MessageString);
	//                Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
	//                return retBool;
	//            }

	//            //URLを取得
	//            String urlPath = Configuration.GetInstance().GetReportUrl();
	//            if (urlPath.Length <= 0)
	//            {
	//                //URLが未設定の場合は警告メッセージを表示
	//                String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getUrlPathNotFound_MessageString);
	//                Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
	//                return retBool;
	//            }

	//            //検査種別マスタ準備
	//            DataTable kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster();
	//            MasterUtil mUtil = new MasterUtil();

	//            //今回or過去の判定
	//            if (showBeforeOrderFlg)
	//            {
	//                // 過去表示の場合、条件の補完を行う

	//                //過去情報を取得（同一患者ID、検査種別、検済、直近(ExamEndDate Desc））
	//                OrderSearchParameter oParam = new OrderSearchParameter();
	//                oParam.SetKanjaIDOnlyBool(true);
	//                oParam.SetKanjaID(bean.GetKanjaID());
	//                oParam.SetRrisKensaTypeID(bean.GetKensatypeID());
	//                oParam.SetRrisStatus(CommonString.STATUS_ISFINISHED);
	//                DataTable dt = Configuration.GetInstance().GetCoreController().GetRisOrderList(oParam);
	//                DataTable newTable = dt.Clone();
	//                newTable.Rows.Clear();
	//                //検査終了時刻の降順でソート
	//                DataRow[] rows = dt.Select("", RisSummaryView.EXAMENDDATE_COLUMN + CommonString.SORT_DESC);
	//                foreach (DataRow row in rows)
	//                {
	//                    String beforeKensaDate    = row[RisSummaryView.KENSA_DATE_COLUMN].toString();
	//                    String beforeKensatypeID  = row[RisSummaryView.KENSATYPE_ID_COLUMN].toString();
	//                    String beforeModalityType = row[RisSummaryView.MODALITY_TYPE_COLUMN].toString();
	//                    String beforeAccessionNo  = row[RisSummaryView.ACNO_COLUMN].toString();

	//                    //検査機器マスタのモダリティタイプが空の場合は検査種別マスタのモダリティを設定
	//                    if (beforeModalityType.Length <= 0)
	//                    {
	//                        beforeModalityType = mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, beforeKensatypeID);
	//                    }

	//                    //補填
	//                    bean.SetKensaDate(beforeKensaDate);
	//                    bean.SetAccessionNo(beforeAccessionNo);
	//                    bean.SetModalityType(beforeModalityType);

	//                    break;
	//                }
	//
	//            }
	//            else
	//            {
	//                //今回の場合、検査種別マスタのモダリティを設定
	//                String modalityType = mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, bean.GetKensatypeID());
	//                bean.SetModalityType(modalityType);
	//            }

	//            // 条件を準備する
	//            String whereStr = "";
	//            //ユーザID
	//            if (Configuration.GetInstance().GetRptUserLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetRptUserLabel();
	//                String value  = Configuration.GetInstance().GetUserAccountBean().GetUserID();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //パスワード
	//            if (Configuration.GetInstance().GetRptPasswordLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetRptPasswordLabel();
	//                String value  = Configuration.GetInstance().GetUserAccountBean().GetPassword();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //患者ID
	//            if (Configuration.GetInstance().GetRptPidLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetRptPidLabel();
	//                String value  = bean.GetKanjaID();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //検査日
	//            if (Configuration.GetInstance().GetRptDateLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetRptDateLabel();
	//                String value  = bean.GetKensaDate().toString();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //AccessionNo
	//            if (Configuration.GetInstance().GetRptAcnoLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetRptAcnoLabel();
	//                String value  = bean.GetAccessionNo();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //モダリティ
	//            if (Configuration.GetInstance().GetRptModalityLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetRptModalityLabel();
	//                String value  = bean.GetModalityType();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//

	//            //URLを作成する
	//            if (whereStr.Length > 0)
	//            {
	//                urlPath += "?" + whereStr;
	//            }

	//            //URL連携を行う
	//            System.Diagnostics.ProcessStartInfo processStartInfo =
	//                        new System.Diagnostics.ProcessStartInfo();
	//            processStartInfo.Arguments = urlPath;
	//            processStartInfo.FileName = brawserPath;
	//            System.Diagnostics.Process.Start(processStartInfo);

	//            retBool = true;
	//        }
	//    }
	//    catch (Exception ex)
	//    {
	//        logger.fatal(ex);
	//    }
	//    logger.Debug("end");

	//    return retBool;
	//}

	///// <summary>
	///// シェーマ参照を行う(URL連携)
	///// </summary>
	///// <param name="bean">条件</param>
	///// <returns></returns>
	//public static bool ShowShemaURL(OrderInfoBean bean)
	//{
	//    bool retBool = false;
	//    logger.Debug("begin");
	//    try
	//    {
	//        //シェーマ連携使用ﾌﾗｸﾞ判断
	//        if (Configuration.GetInstance().GetSchemaUrlFlag() == CommonString.SCHEMA_URL_VALUE)
	//        {
	//            //ブラウザパスを取得
	//            String brawserPath = GetDynamicPropertyString("BrowserPath.Value");
	//            if (!File.Exists(brawserPath))
	//            {
	//                //ブラウザパスが存在しない場合は警告メッセージを表示
	//                String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getBrowserNotFound_MessageString);
	//                Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
	//                return retBool;
	//            }

	//            //URLを取得
	//            String urlPath = Configuration.GetInstance().GetSchemaUrl();
	//            if (urlPath.Length <= 0)
	//            {
	//                //URLが未設定の場合は警告メッセージを表示
	//                String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.getUrlPathNotFound_MessageString);
	//                Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
	//                return retBool;
	//            }
	//            if (bean.GetExtendOrderInfoBean().GetShemaurl().Length > 0)
	//            {
	//                urlPath += bean.GetExtendOrderInfoBean().GetShemaurl();
	//            }

	//            //検査種別マスタ準備
	//            DataTable kTypeDt = Configuration.GetInstance().GetRRisKensaTypeMaster();
	//            MasterUtil mUtil = new MasterUtil();

	//            //検査種別マスタのモダリティを設定
	//            String modalityType = mUtil.FindData(kTypeDt, MasterUtil.RIS_MODALITY, MasterUtil.RIS_KENSATYPE_ID, bean.GetKensatypeID());
	//            bean.SetModalityType(modalityType);

	//            // 条件を準備する
	//            String whereStr = "";
	//            //ユーザID
	//            if (Configuration.GetInstance().GetSchUserLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetSchUserLabel();
	//                String value  = Configuration.GetInstance().GetUserAccountBean().GetUserID();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //パスワード
	//            if (Configuration.GetInstance().GetSchPasswordLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetSchPasswordLabel();
	//                String value  = Configuration.GetInstance().GetUserAccountBean().GetPassword();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //患者ID
	//            if (Configuration.GetInstance().GetSchPidLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetSchPidLabel();
	//                String value  = bean.GetKanjaID();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //検査日
	//            if (Configuration.GetInstance().GetSchEdateLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetSchEdateLabel();
	//                String value  = bean.GetKensaDate().toString();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //モダリティ
	//            if (Configuration.GetInstance().GetSchModalityLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetSchModalityLabel();
	//                String value  = bean.GetModalityType();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//            //OrderNo
	//            if (Configuration.GetInstance().GetSchOrdernoLabel().Length > 0)
	//            {
	//                String keyStr = Configuration.GetInstance().GetSchOrdernoLabel();
	//                String value  = bean.GetAccessionNo();
	//                whereStr = AddURLWhereStr(whereStr, keyStr, value);
	//            }
	//

	//            //URLを作成する
	//            if (whereStr.Length > 0)
	//            {
	//                urlPath += "?" + whereStr;
	//            }

	//            //URL連携を行う
	//            System.Diagnostics.ProcessStartInfo processStartInfo =
	//                        new System.Diagnostics.ProcessStartInfo();
	//            processStartInfo.Arguments = urlPath;
	//            processStartInfo.FileName = brawserPath;
	//            System.Diagnostics.Process.Start(processStartInfo);

	//            retBool = true;
	//        }
	//    }
	//    catch (Exception ex)
	//    {
	//        logger.fatal(ex);
	//    }
	//    logger.Debug("end");

	//    return retBool;
	//}

	///// <summary>
	///// URL検索文字列に条件追加する
	///// </summary>
	///// <param name="whereStr">URL検索文字列</param>
	///// <param name="keyStr">条件キー文字列</param>
	///// <param name="value">条件</param>
	///// <returns></returns>
	//private static String AddURLWhereStr(String whereStr, String keyStr, String value)
	//{
	//    if (whereStr.Length <= 0)
	//    {
	//        whereStr += keyStr + "=" + value;
	//    }
	//    else
	//    {
	//        whereStr += "&" + keyStr + "=" + value;
	//    }
	//    return whereStr;
	//}
	*/


	/// <summary>
	/// 患者コメントを取得する
	/// </summary>
	/// <param name="list"></param>
	/// <param name="keyStr"></param>
	/// <returns></returns>
	public static String GetPatientComment(ArrayList list, String keyStr)
	{
		String retStr = "";

		if (list != null)
		{
			//コメントリストのループ
			for (int i=0; i<list.size(); i++)
			{
				DataRow row = (DataRow)list.get(i);

				if (keyStr.equals(row.get(RisPatientCommentTbl.PATIENTKENSATYPE_COLUMN).toString()))
				{
					retStr = row.get(RisPatientCommentTbl.PATIENTCOMMENT_COLUMN).toString();
					break;
				}
			}
		}

		return retStr;
	}

	/// <summary>
	/// 現在の時刻を元に業務区分を取得する
	/// </summary>
	/// <returns></returns>
	public static String GetGyoumuKbnToday(Connection con)
	{
		String retStr = CommonString.GYOUMUKBN_DAY;

		try
		{
			//診療日区分を決定する
			String dateClassStr = "";

			//現在の時刻を取得
			Timestamp sysDate = Configuration.GetInstance().GetSysDate(con);

			//祝日マスタに存在するかチェック
			DataTable holidayDt =  Configuration.GetInstance().GetCoreController().GetHolidayDataTable(sysDate, con);
			if (holidayDt.getRowCount() > 0)
			{
				//休診日
				dateClassStr = CommonString.DATE_CLASS_HOLIDAY;
			}
			else
			{
				//その他
				DataTable dayClassDt = Configuration.GetInstance().GetRRisDayClassificationTable(con);

				Calendar cal = Calendar.getInstance();
				cal.setTime(sysDate);

				//本日の日付を元に何週目、曜日を取得する
				String weekNumStr = GetWeekOfMonth(sysDate);				//何週目か求める
				Integer weekInt = (Integer)cal.get(Calendar.DAY_OF_WEEK) -1;	//曜日の数値
				String weekStr = weekInt.toString();	//曜日の文字列

				// 診療日区分設定から該当の診療日区分を探す
				for (int i = 0; i < dayClassDt.getRowCount(); i++)
				{
					DataRow row = dayClassDt.getRows().get(i);

					String dayofWeek = row.get(MasterUtil.RIS_DAYOFWEEK).toString();

					//曜日が一致する場合
					if (dayofWeek.equals(weekStr))
					{
						//診療日区分を求める
						if ("1".equals(weekNumStr))
						{
							dateClassStr = row.get(MasterUtil.RIS_WEEK01).toString();
						}
						else if ("2".equals(weekNumStr))
						{
							dateClassStr = row.get(MasterUtil.RIS_WEEK02).toString();
						}
						else if ("3".equals(weekNumStr))
						{
							dateClassStr = row.get(MasterUtil.RIS_WEEK03).toString();
						}
						else if ("4".equals(weekNumStr))
						{
							dateClassStr = row.get(MasterUtil.RIS_WEEK04).toString();
						}
						else if ("5".equals(weekNumStr))
						{
							dateClassStr = row.get(MasterUtil.RIS_WEEK05).toString();
						}
						else if ("6".equals(weekNumStr))
						{
							dateClassStr = row.get(MasterUtil.RIS_WEEK06).toString();
						}
						else
						{
							//該当なしの場合は診療日とする
							dateClassStr = CommonString.DATE_CLASS_NORMAL;
						}

						break;
					}
				}

			}

			//診療時間帯区分設定と診療日区分と時刻を元に業務区分を取得する
			DataTable timezoneDt = Configuration.GetInstance().GetRRisTimezoneTable(con);
			for (int i = 0; i < timezoneDt.getRowCount(); i++)
			{
				DataRow row = timezoneDt.getRows().get(i);

				String kbn = row.get(MasterUtil.RIS_DATECLASSIFICATION).toString();

				// 診療日区分が一致する場合
				if (kbn.equals(dateClassStr))
				{
					int nowTime = Integer.parseInt(new SimpleDateFormat("HHmm").format(sysDate));
					int time01  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE1_TIME)))));
					int time02  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE2_TIME)))));
					int time03  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE3_TIME)))));
					int time04  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE4_TIME)))));
					int time05  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE5_TIME)))));
					int time06  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE6_TIME)))));
					int time07  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE7_TIME)))));
					int time08  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE8_TIME)))));
					int time09  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE9_TIME)))));
					int time10  = Integer.parseInt(String.format("%1$04d", Integer.parseInt(Util.toNullZero(row.get(MasterUtil.RIS_ZONE10_TIME)))));
					//
					String code01 = row.get(MasterUtil.RIS_ZONE1_CODE).toString();
					String code02 = row.get(MasterUtil.RIS_ZONE2_CODE).toString();
					String code03 = row.get(MasterUtil.RIS_ZONE3_CODE).toString();
					String code04 = row.get(MasterUtil.RIS_ZONE4_CODE).toString();
					String code05 = row.get(MasterUtil.RIS_ZONE5_CODE).toString();
					String code06 = row.get(MasterUtil.RIS_ZONE6_CODE).toString();
					String code07 = row.get(MasterUtil.RIS_ZONE7_CODE).toString();
					String code08 = row.get(MasterUtil.RIS_ZONE8_CODE).toString();
					String code09 = row.get(MasterUtil.RIS_ZONE9_CODE).toString();
					String code10 = row.get(MasterUtil.RIS_ZONE10_CODE).toString();

					if (time01 <= nowTime && (nowTime < time02 || time02 == 0))
					{
						retStr = code01;
						break;
					}
					if (time02 <= nowTime && (nowTime < time03 || time03 == 0))
					{
						retStr = code02;
						break;
					}
					if (time03 <= nowTime && (nowTime < time04 || time04 == 0))
					{
						retStr = code03;
						break;
					}
					if (time04 <= nowTime && (nowTime < time05 || time05 == 0))
					{
						retStr = code04;
						break;
					}
					if (time05 <= nowTime && (nowTime < time06 || time06 == 0))
					{
						retStr = code05;
						break;
					}
					if (time06 <= nowTime && (nowTime < time07 || time07 == 0))
					{
						retStr = code06;
						break;
					}
					if (time07 <= nowTime && (nowTime < time08 || time08 == 0))
					{
						retStr = code07;
						break;
					}
					if (time08 <= nowTime && (nowTime < time09 || time09 == 0))
					{
						retStr = code08;
						break;
					}
					if (time09 <= nowTime && (nowTime < time10 || time10 == 0))
					{
						retStr = code09;
					}
					else
					{
						retStr = code10;
					}
					break;
				}

			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}

		return retStr;
	}

	/// <summary>
	/// 指定日が月の何週目かを取得する(日曜始まり）
	/// </summary>
	/// <param name="date">指定日</param>
	/// <returns></returns>
	private static String GetWeekOfMonth(Timestamp date)
	{
		String retStr = "1";
		try
		{
			// 2011.08.25 Mod H.Orikasa Start NML-2-X01
			int weekInt = 0;

			//休診日設定によって切り替える
			String holidayMode = Configuration.GetInstance().GetSystemParam().GetHolidayModeValue1();

			// カレンダー
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			if (CommonString.HOLIDAY_MODE_TYPE_WEEK.equals(holidayMode))
			{
				//週判定の場合

				//指定日の１日の曜日を求める
				cal.set(Calendar.DATE, 1);
				weekInt = cal.get(Calendar.DAY_OF_WEEK) - 1;

				//指定日の日付に曜日分加算する
				cal.setTime(date);
				double ddDbl = (cal.get(Calendar.DATE)) + weekInt;

				//加算した日付を7で割り切り上げる
				double weekNo = ddDbl / 7;
				Integer i = (int) Math.ceil(weekNo);
				retStr = i.toString();
			}
			else
			{
				//その他は回判定

				//指定日の日付に曜日分加算する
				double ddDbl = (cal.get(Calendar.DATE)) + weekInt;

				//加算した日付を7で割り切り上げる
				double weekNo = ddDbl / 7;
				Integer i = (int) Math.ceil(weekNo);
				retStr = i.toString();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retStr;
	}

	/*
	/// <summary>
	/// 文字列の改行コードを変換する
	/// </summary>
	/// <param name="str">文字列</param>
	/// <returns></returns>
	public static String ConvLF(String str)
	{
		return str.Replace("\n\n", "\r\n");
	}

	/// <summary>
	/// 一覧幅テキストファイルを読み込む
	/// </summary>
	/// <param name="appID">画面ID</param>
	/// <param name="numStr">連番</param>
	/// <param name="grid">部位一覧</param>
	public static void ReadDataListWidthText(String appID, String numStr, NormalOrderListDataGridView grid)
	{
		try
		{
			String widthStr = "";

			//パスを準備
			String path		= Configuration.GetInstance().GetDynamicPropertyString("DataListWidthTextFile.Path");
			String pathStr	= Application.StartupPath + @"\..\" + path + appID + "_" + numStr + ".txt";

			//ファイル存在チェック
			if (File.Exists(pathStr))
			{
				//読み込み
				widthStr = File.ReadAllText(pathStr, Encoding.GetEncoding(CommonString.ENCODE_DEF));
			}

			if (widthStr.Length > 0)
			{

				//幅を設定する
				string[] listWidth = widthStr.Split(',');

				grid.SuspendLayout();
				for (int i=0; i<listWidth.Length; i++)
				{
					if (grid.Columns.Count >  i+1)
					{
						grid.Columns[i].Width = int.Parse(listWidth[i].toString());
					}
				}
				grid.ResumeLayout();
			}

		}
		catch (Exception ex)
		{
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
			logger.fatal(ex);
		}
	}

	/// <summary>
	/// 一覧幅テキストファイルを書き込む
	/// </summary>
	/// <param name="appID">画面ID</param>
	/// <param name="numStr">連番</param>
	/// <param name="grid">一覧</param>
	public static void WriteDataListWidthText(String appID, String numStr, NormalOrderListDataGridView grid)
	{
		try
		{
			//保存するテキストを準備する
			String saveStr = "";
			if (grid != null)
			{
				for (int i=0; i<grid.Columns.Count; i++)
				{
					int width = -1;

					//if (grid.Columns[i].Visible)
					//{
						width = grid.Columns[i].Width;
					//}

					if (saveStr.Length <= 0)
					{
						saveStr  = width.toString();
					}
					else
					{
						saveStr += "," + width.toString();
					}
				}
			}

			if (saveStr.Length > 0)
			{
				//パスを準備
				String path		= Configuration.GetInstance().GetDynamicPropertyString("DataListWidthTextFile.Path");
				String pathStr	= Application.StartupPath + @"\..\" + path;

				//フォルダ存在チェック
				if (!Directory.Exists(pathStr))
				{
					//フォルダ作成
					Directory.CreateDirectory(pathStr);
				}

				//ファイル名追加
				pathStr += appID + "_" + numStr + ".txt";

				File.WriteAllText(pathStr, saveStr, Encoding.GetEncoding(CommonString.ENCODE_DEF));
			}
		}
		catch (Exception ex)
		{
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
			logger.fatal(ex);
		}
	}

	/// <summary>
	/// 一覧の背景色を設定する
	/// </summary>
	/// <param name="grid"></param>
	public static void ListBaseInitGridBackColor(NormalOrderListDataGridView grid)
	{
		SystemParamBean sysParam = Configuration.GetInstance().GetSystemParam();

		//選択行背景色
		if (sysParam.GetSelectrowbgcolorValue1() == CommonString.SYSTEMPARAM_0)
		{
			grid.DefaultCellStyle.SelectionBackColor = SystemColors.Highlight;
		}
		else if (sysParam.GetSelectrowbgcolorValue1() == CommonString.SYSTEMPARAM_1)
		{
			grid.DefaultCellStyle.SelectionBackColor = AppCommon.GetWin32Color(sysParam.GetSelectrowbgcolorValue2());
		}

		//行の背景色
		if (sysParam.GetStatusbgcolorValue1() == CommonString.SYSTEMPARAM_0)
		{
			//進捗背景色
			//ListBaseSetRowColorで進捗毎に設定
		}
		else if (sysParam.GetStatusbgcolorValue1() == CommonString.SYSTEMPARAM_1)
		{
			//交互設定
			grid.DefaultCellStyle.BackColor = AppCommon.GetWin32Color(sysParam.GetStatusbgcolorValue2());
			grid.AlternatingRowsDefaultCellStyle.BackColor = AppCommon.GetWin32Color(sysParam.GetStatusbgcolorValue3());
		}
	}

	/// <summary>
	/// 一覧の行毎に色を設定する
	/// </summary>
	/// <param name="grid">一覧</param>
	public static void ListBaseSetRowColor(NormalOrderListDataGridView grid)
	{
		try
		{
			SystemParamBean			sysParam	= Configuration.GetInstance().GetSystemParam();
			ExtendColorDefineBean	exColorDef	= CommonUtil.GetExtendColorDefine(null, CommonString.EXCOLORDEF_INOPERATION);

			//グリッドのループ
			for (int i = 0; i < grid.Rows.Count; i++)
			{
				DataGridViewRow row = (DataGridViewRow)grid.Rows[i];
				DataRowView drv = (DataRowView)row.DataBoundItem;
				if (drv != null && drv.Row != null)
				{
					DataRow dRow = (DataRow)drv.Row;

					//ステータス列がない場合は処理を抜ける
					if (dRow.Table != null && !dRow.Table.Columns.Contains(RisSummaryView.STATUS_COLUMN))
					{
						return;
					}

					String status   = dRow[RisSummaryView.STATUS_COLUMN].toString();
					String inopeFlg = dRow[RisSummaryView.TAKENSA_INOPE_FLG_COLUMN].toString();

					// 2011.11.18 Mod NSK_H.Hashimoto Start OMH-1-03
					// コメント
					//ステータスに応じて文字色を設定する
					//row.DefaultCellStyle.ForeColor = AppCommon.GetStatusColor(status);

					// 緊急科設定の取得
					ArrayList EemergencyValueList = new ArrayList();
					EemergencyValueList = sysParam.GetEemergencyValueList();
					String strSectionID = "";
					try
					{
						strSectionID = dRow[RisSummaryView.IRAI_SECTION_ID_COLUMN].toString();
					}
					catch
					{
						// catchに入った場合、dRowの中身がRISSUMMARYVIEWではない為、
						// ｢IRAI_SECTION_ID_COLUMN｣の取得を失敗している時なので、
						// あえて値をnullにして下記の｢救急科ＩＤのチェック｣がfalseになるようにする。
						// ※値を""にすると｢救急科指定なし｣と同じになってしまうので避けた
						strSectionID = null;
					}

					bool blFlag = false;	// ステータス毎の設定

					// 救急科ＩＤのチェック
					if (strSectionID == EemergencyValueList[EMG_COLOR_ID].toString())
					{
						// 検済・中止オーダ指定チェック
						if (EemergencyValueList[EMG_COLOR_ORDER].toString() == CommonString.DB_FLG_ON)
						{
							switch (status)
							{
								case CommonString.STATUS_ISFINISHED:	// 検済
								case CommonString.STATUS_STOP:			// 中止
									break;
								default:
									blFlag = true;	// 緊急科設定
									break;
							}
						}
						else
						{
							blFlag = true;	// 緊急科設定
						}
					}

					// 文字色の設定
					if (blFlag == true)
					{
						// 文字色区分チェック
						switch (EemergencyValueList[EMG_COLOR_FORE_KBN].toString())
						{
							case "0":	// 進捗文字色
								row.DefaultCellStyle.ForeColor =
									AppCommon.GetStatusColor(status);
								break;
							case "1":	// 進捗背景色
								row.DefaultCellStyle.ForeColor =
									AppCommon.GetStatusBackColor(status);
								break;
							case "2":	// 緊急科設定の文字色
								row.DefaultCellStyle.ForeColor =
									AppCommon.GetWin32Color(EemergencyValueList[EMG_COLOR_FORE_COL].toString());
								break;
						}
					}
					else
					{
						//ステータスに応じて文字色を設定する
						row.DefaultCellStyle.ForeColor =
							AppCommon.GetStatusColor(status);
					}
					// 2011.11.18 Mod NSK_H.Hashimoto End

					//行の背景色
					if (sysParam.GetStatusbgcolorValue1() == CommonString.SYSTEMPARAM_0)
					{
						// 2011.11.18 Mod NSK_H.Hashimoto Start OMH-1-03
						// コメント
						//進捗背景色
						//row.DefaultCellStyle.BackColor = AppCommon.GetStatusBackColor(status);

						// 背景色の設定
						if (blFlag == true)
						{
							// 背景色区分チェック
							if (EemergencyValueList[EMG_COLOR_BACK_KBN].toString() == CommonString.DB_FLG_ON)
							{
								// 緊急科設定の背景色
								row.DefaultCellStyle.BackColor =
									AppCommon.GetWin32Color(EemergencyValueList[EMG_COLOR_BACK_COL].toString());
							}
							else
							{
								// システム色(赤)
								row.DefaultCellStyle.BackColor =
									Color.FromArgb(0xFF, 0x00, 0x00);
							}
						}
						else
						{
							//進捗背景色
							row.DefaultCellStyle.BackColor =
								AppCommon.GetStatusBackColor(status);
						}
						// 2011.11.18 Mod NSK_H.Hashimoto End
					}

					//検中他検査＆検中ありの場合
					if (exColorDef != null && exColorDef.GetColorMode() == CommonString.FLG_ON &&
						inopeFlg == CommonString.FLG_ON)
					{
						// 2011.11.18 Mod NSK_H.Hashimoto Start OMH-1-03
						// コメント
						//row.DefaultCellStyle.ForeColor = AppCommon.GetWin32Color(exColorDef.GetColor());
						//row.DefaultCellStyle.BackColor = AppCommon.GetWin32Color(exColorDef.GetColorBk());

						if (blFlag == false)
						{
							row.DefaultCellStyle.ForeColor = AppCommon.GetWin32Color(exColorDef.GetColor());
							row.DefaultCellStyle.BackColor = AppCommon.GetWin32Color(exColorDef.GetColorBk());
						}
						// 2011.11.18 Mod NSK_H.Hashimoto End
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}

	/// <summary>
	/// 一覧の選択行に色を設定する
	/// </summary>
	/// <param name="grid">一覧</param>
	public static void ListBaseSetSelectionRowColor(NormalOrderListDataGridView grid)
	{
		try
		{
			if (grid.SelectedRows.Count > 0)
			{
				SystemParamBean sysParam = Configuration.GetInstance().GetSystemParam();

				Color setColor = SystemColors.HighlightText;
				String value1 = sysParam.GetSelectrowtextcolorValue1();
				String value2 = sysParam.GetSelectrowtextcolorValue2();
				if (value1 == CommonString.SYSTEMPARAM_0 ||
					value1 == CommonString.SYSTEMPARAM_1)
				{
					//ステータスIDの取得
					String statusID = "";
					DataGridViewRow row = grid.SelectedRows[0];
					DataRowView drv = (DataRowView)grid.SelectedRows[0].DataBoundItem;
					if (drv != null && drv.Row != null)
					{
						DataRow dRow = (DataRow)drv.Row;

						//ステータス列がない場合は処理を抜ける
						if (dRow.Table != null && !dRow.Table.Columns.Contains(RisSummaryView.STATUS_COLUMN))
						{
							return;
						}

						statusID = dRow[RisSummaryView.STATUS_COLUMN].toString();
					}

					if (value1 == CommonString.SYSTEMPARAM_0)
					{
						//進捗文字色
						setColor = AppCommon.GetStatusColor(statusID);
					}
					else if (value1 == CommonString.SYSTEMPARAM_1)
					{
						//進捗背景色
						setColor = AppCommon.GetStatusBackColor(statusID);
					}
				}
				else
				{
					//指定色
					setColor = AppCommon.GetWin32Color(value2);
				}

				//色の設定
				grid.SelectedRows[0].DefaultCellStyle.SelectionForeColor = setColor;
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}

	/// <summary>
	/// 一覧の列サイズをチェックする
	/// </summary>
	/// <param name="columnsDt">テーブル定義情報</param>
	/// <param name="columnID">列ID</param>
	/// <returns></returns>
	public static bool CheckWrapModeColumn(String columnID)
	{
		bool retBool = false;

		//改行する列の項目IDを取得
		string[] wrapIDList = Configuration.GetInstance().GetDynamicPropertyString("ListBaseForm.WrapModeItemIDs.Value").Split(',');

		for (int i=0; i<wrapIDList.Length; i++)
		{
			if (wrapIDList[i].Length > 0 && wrapIDList[i] == columnID)
			{
				retBool = true;
				break;
			}
		}

		return retBool;
	}

	*/

	/// <summary>
	/// 部位No毎の連番を取得する
	/// </summary>
	/// <param name="satueiBeanList">撮影情報リスト</param>
	/// <param name="buiNo">部位No</param>
	/// <returns></returns>
	public static String GetSequenceSatuei(ArrayList<ExSatueiBean> satueiBeanList, String buiNo)
	{
		Integer no = 0;
		Integer maxno = 0;

		for (int i = 0; i < satueiBeanList.size(); i++) {

			ExSatueiBean satueiBean = satueiBeanList.get(i);

			if (buiNo.equals(satueiBean.GetBuiNo())) {

				try
				{
					no = Integer.parseInt(satueiBean.GetNo());

					if (maxno < no) {
						maxno = no;
					}
				}
				catch (Exception ex)
				{
				}
			}
		}

		maxno += 1;

		return maxno.toString();
	}

	/// <summary>
	/// 部位No毎の連番を取得する
	/// </summary>
	/// <param name="filmBeanList">フィルム情報リスト</param>
	/// <param name="buiNo">部位No</param>
	/// <returns></returns>
	public static String GetSequenceFilm(ArrayList<ExFilmBean> filmBeanList, String buiNo)
	{
		Integer no = 0;
		Integer maxno = 0;

		for (int i = 0; i < filmBeanList.size(); i++) {

			ExFilmBean filmBean = filmBeanList.get(i);

			if (buiNo.equals(filmBean.GetBuiNo())) {

				try
				{
					no = Integer.parseInt(filmBean.GetNo());

					if (maxno < no) {
						maxno = no;
					}
				}
				catch (Exception ex)
				{
				}
			}
		}

		maxno += 1;

		return maxno.toString();
	}

	/// <summary>
	/// 部位Noが最大の撮影情報を取得する
	/// </summary>
	/// <param name="satueiBeanList">撮影情報リスト</param>
	/// <returns></returns>
	public static ExSatueiBean GetMaxBuiNoExSatueiBean(ArrayList<ExSatueiBean> satueiBeanList)
	{
		Integer buino = 0;
		Integer maxno = 0;
		Integer index = 0;

		for (int i = 0; i < satueiBeanList.size(); i++) {

			ExSatueiBean satueiBean = satueiBeanList.get(i);

			try
			{
				buino = Integer.parseInt(satueiBean.GetBuiNo());

				if (maxno < buino) {
					maxno = buino;
					index = i;
				}
			}
			catch (Exception ex)
			{
			}
		}

		return satueiBeanList.get(index);
	}

	/*

	/// <summary>
	/// 実績参照ボタンEnabled設定
	/// </summary>
	/// <param name="view"></param>
	/// <returns></returns>
	public static bool IsShowOperationButtonEnabled(NormalOrderListDataGridView view)
	{
		// 2010.09.02 Mod K.Shinohara Start
		return IsShowOperationButtonEnabled(view, false);
		//bool retBool = false;

		//if (view != null)
		//{
		//    DataRow row = GetSelectRow(view);
		//    if (row != null)
		//    {
		//        if (row.Table != null && row.Table.Columns.Contains(RisSummaryView.STATUS_COLUMN))
		//        {
		//            String statusStr = row[RisSummaryView.STATUS_COLUMN].toString();
		//            try
		//            {
		//                if (statusStr.Length > 0)
		//                {
		//                    //受済以降を可とする
		//                    if (TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED) <= TextUtil.ParseStringToInt(statusStr))
		//                    {
		//                        retBool = true;
		//                    }
		//                }
		//            }
		//            catch
		//            {
		//            }
		//        }
		//    }
		//}

		//return retBool;
		// 2010.09.02 Mod K.Shinohara End
	}

	// 2010.09.02 Add K.Shinohara Start
	/// <summary>
	/// 実績参照ボタンEnabled設定
	/// </summary>
	/// <param name="view"></param>
	/// <param name="checkFlg"></param>
	/// <returns></returns>
	public static bool IsShowOperationButtonEnabled(NormalOrderListDataGridView view, bool checkFlg)
	{
		bool retBool = false;

		if (checkFlg)
		{
			if (view != null)
			{
				DataRow row = GetSelectRow(view);
				if (row != null)
				{
					if (row.Table != null && row.Table.Columns.Contains(RisSummaryView.STATUS_COLUMN))
					{
						String statusStr = row[RisSummaryView.STATUS_COLUMN].toString();
						try
						{
							if (statusStr.Length > 0)
							{
								//受済以降を可とする
								if (TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED) <= TextUtil.ParseStringToInt(statusStr))
								{
									retBool = true;
								}
							}
						}
						catch
						{
						}
					}
				}
			}
		}
		else
		{
			retBool = true;
		}

		return retBool;
	}
	// 2010.09.02 Add K.Shinohara End
	*/

	/// <summary>
	/// MWM-targetOrderKeyHash用
	/// </summary>
	enum MWMOrderKeys
	{
		BuiNo(0),		//部位No
		BuiID(1),		//部位ID
		HoukouID(2),	//方向ID
		KHouhouID(3),	//検査方法ID
		SatueiNo(4),	//撮影No
		SatueiID(5)	//撮影ID
		,KikiID2(6);	// 検査機器ID 2010.10.21 Add K.Shinohara 関東労災

	    private final int id;

	    private MWMOrderKeys(final int id) {
	        this.id = id;
	    }

	    public int getId() {
	        return id;
	    }
	}

	/// <summary>
	/// MWM-MWM登録を行う
	/// </summary>
	/// <param name="param">MWM条件</param>
	/// <returns></returns>
	public static boolean RegisterMWM(MWMParameter param, Connection con) throws Exception
	{
		try
		{
			/* 一ノ瀬保留
			if (param.GetAllFlg())
			{
				//MWM出力有無判定
				if (!CommonString.FLG_ON.equals(Configuration.GetInstance().GetMwmUketukeEntry()))
				{
					return false;
				}
			}
			*/

			//各マスタの取得
			MasterUtil mUtil		= new MasterUtil();
			DataTable  mstKensatype	= param.GetMstKensatype();		//検査種別マスタ
			DataTable  mstKensakiki	= param.GetMstKensakiki();		//検査機器マスタ
			DataTable  mstBui		= param.GetMstBui();			//部位マスタ
			DataTable  mstHoukou	= param.GetMstHoukou();			//方向マスタ
			DataTable  mstKHouhou	= param.GetMstKHouhou();		//検査方法マスタ
			DataTable  mstDoctor	= param.GetMstDoctor();			//診療科医師マスタ
			DataTable  mstSection	= param.GetMstSection();		//診療科マスタ

			// 2010.10.21 Add K.Shinohara Start
			// 仮想機器モードフラグ保持
			boolean multiMWMModeFlg = false;
			// 2010.10.21 Add K.Shinohara End

			// オーダ情報と対象オーダRisIDリストを元に対象オーダのHashテーブルを作成する

			Hashtable targetOrderHash = new Hashtable();
			for (int i=0; i<param.GetRisIDList().size(); i++)
			{
				String risID = param.GetRisIDList().get(i).toString();

				//オーダ情報のループ
				for (int j=0; j<param.GetOrderDt().getRowCount(); j++)
				{
					DataRow row = param.GetOrderDt().getRows().get(j);
					if (row.get(MasterUtil.RIS_RIS_ID).toString().equals(risID))
					{
						targetOrderHash.put(risID, row);
					}
				}
			}

			// 対象オーダが全て同じ検査種別か判断する

			/* 一ノ瀬保留
			// 2010.08.04 Mod K.Shinohara Start
			// 一括登録モードのみ行う
			if (param.GetAllFlg())
			//if ((param.GetMWMMode() == CommonString.MWM_MODE_RECEIPT)
			//	|| (param.GetMWMMode() == CommonString.MWM_MODE_PORTABLE))
			// 2010.08.04 Mod K.Shinohara End
			{
				String kTypeID = "";
				for (String risIDStr : targetOrderHash.Keys)
				{
					DataRow row = (DataRow)targetOrderHash.get(risIDStr);
					if (kTypeID.length() <= 0)
					{
						kTypeID = row.get(MasterUtil.RIS_KENSATYPE_ID).toString();
					}
					else
					{
						if (kTypeID != row.get(MasterUtil.RIS_KENSATYPE_ID).toString())
						{
							//検査種別相違
							String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mwmMultiKensatypeIDWarning_MessageString);
							DialogResult retDlg = Configuration.GetInstance().GetWindowController().ShowMessageBox_QuestionYesNo(msg);
							if (retDlg == DialogResult.Yes)
							{
								return true;
							}
							else
							{
								return false;
							}
						}
					}
				}
			}
			*/

			// MWM出力する検査機器を選択させる
			String targetKikiID = "";
			targetKikiID = param.GetKikiID();


			// 2010.08.04 Add K.Shinohara Start
			// 受付時MWM登録の場合、検査機器が受付時MWM対象となっているかチェックする
			if ((!param.GetAllFlg()) &&
				(CommonString.MWM_MODE_RECEIPT.equals(param.GetMWMMode())))
			{
				if (!CheckReceiptMWMFlag(mstKensakiki, targetKikiID))
				{
					// MWM登録は行わないが、処理は正常として扱う
					return true;
				}
			}

			// 2010.08.04 Add K.Shinohara End

			// オーダの部位、撮影情報を登録用に処理する。対象外のオーダがあった場合サブ画面を表示する

			ArrayList disTargetRisIDList = new ArrayList();	//対象外オーダのRisIDリスト
			Hashtable targetOrderKeyHash = new Hashtable(); //RISIDをキーにした、各キーリストのHash

			String risIDs = "";
			Enumeration e1 = targetOrderHash.keys();

			while (e1.hasMoreElements()){
				String risIDStr = (String)e1.nextElement();
				//部位取得用の条件を作成
				if (risIDs.length() <= 0)
				{
					risIDs = risIDStr;
				}
				else
				{
					risIDs += "," + risIDStr;
				}
			}

			if (risIDs.split(",").length > 0)
			{
				//部位情報の取得
				OrderSearchParameter oParam = new OrderSearchParameter();
				oParam.SetRisIDList(risIDs);
				DataTable buiList = null;
				if (CommonString.MWM_MODE_RECEIPT.equals(param.GetMWMMode()))
				{
					//受付モード→DB上の部位情報
					buiList = Configuration.GetInstance().GetCoreController().GetRisOrderBuiList(oParam, con);
				}
				else if (CommonString.MWM_MODE_OPERATION.equals(param.GetMWMMode()))
				{
					//実施モード→画面上の部位情報
					buiList = param.GetBuiDt();
				}
				// 2010.07.30 Add Y.Shibata Start
				else if (CommonString.MWM_MODE_PORTABLE.equals(param.GetMWMMode()))
				{
					//ポータブル一括モード→DB上の部位情報
					buiList = Configuration.GetInstance().GetCoreController().GetRisOrderBuiList(oParam, con);
				}
				// 2010.07.30 Add Y.Shibata End
				Enumeration e2 = targetOrderHash.keys();
				while (e2.hasMoreElements()){
					String risIDStr = (String)e2.nextElement();
					String kensatypeID = ((DataRow)targetOrderHash.get(risIDStr)).get(MasterUtil.RIS_KENSATYPE_ID).toString();
					String kensaKbn    = Configuration.GetInstance().GetOperationKbn(mstKensatype, kensatypeID, con);

					//部位情報のループ
					boolean buiMatchFlg = false;
					int  buiNoInt = 0;
					ArrayList buiNoList		= new ArrayList();	//targetOrderKeyHash用-部位Noリスト
					ArrayList buiIDList		= new ArrayList();	//targetOrderKeyHash用-部位IDリスト
					ArrayList houkouIDList	= new ArrayList();	//targetOrderKeyHash用-方向IDリスト
					ArrayList kHouhouIDList	= new ArrayList();	//targetOrderKeyHash用-検査方法IDリスト
					ArrayList satueiNoList	= new ArrayList();	//targetOrderKeyHash用-撮影Noリスト
					ArrayList satueiIDList	= new ArrayList();	//targetOrderKeyHash用-撮影IDリスト
					// 2010.10.21 Add K.Shinohara Start
					// 【関東労災】撮影毎の検査機器ID
					ArrayList kikiID2List	= new ArrayList();	//targetOrderKeyHash用-検査機器IDリスト
					// 2010.10.21 Add K.Shinohara End
					for (int j=0; j<buiList.getRowCount(); j++)
					{
						DataRow buiRow = buiList.getRows().get(j);

						String buiRisID		= "";
						if (CommonString.MWM_MODE_RECEIPT.equals(param.GetMWMMode()))
						{
							//受付モード→
							buiRisID = buiRow.get(RisOrderBuiInfoTbl.RIS_ID_COLUMN).toString();
						}
						else if (CommonString.MWM_MODE_OPERATION.equals(param.GetMWMMode()))
						{
							//実施モード→
							buiRisID = param.GetRisIDList().get(0).toString();
						}
						// 2010.07.30 Add Y.Shibata Start
						else if (CommonString.MWM_MODE_PORTABLE.equals(param.GetMWMMode()))
						{
							//ポータブル一括モード→
							buiRisID = buiRow.get(RisOrderBuiInfoTbl.RIS_ID_COLUMN).toString();
						}
						// 2010.07.30 Add Y.Shibata End

						String buiID		= buiRow.get(RisOrderBuiInfoTbl.BUI_ID_COLUMN).toString();
						String houkouID		= buiRow.get(RisOrderBuiInfoTbl.HOUKOU_ID_COLUMN).toString();
						String sayuuID		= buiRow.get(RisOrderBuiInfoTbl.SAYUU_ID_COLUMN).toString();
						String kHouhouID	= buiRow.get(RisOrderBuiInfoTbl.KENSAHOUHOU_ID_COLUMN).toString();

						if (risIDStr.equals(buiRisID))
						{
							buiMatchFlg = true;

							buiNoInt += 1;

							// 2010.09.28 Add K.Shinohara Start
							// 実施モードのみ
							if (CommonString.MWM_MODE_OPERATION.equals(param.GetMWMMode()))
							{
								// 2010.11.02 Mod K.Shinohara Start
								// 検査系の場合、選択マークがセットされている部位情報のみ対象とする
								if ((CommonString.OPE_KBN_INSPECT.equals(kensaKbn)) &&
									(!(MARK_STR.equals(buiRow.get(MasterUtil.RIS_MARK).toString()))))
								{
									continue;
								}
								//// 選択マークがセットされている部位情報のみ対象とする
								//if (!buiRow[MasterUtil.RIS_MARK].toString().Equals(OperationDetailForm.MARK_STR))
								//{
								//    continue;
								//}
								// 2010.11.02 Mod K.Shinohara End
							}
							// 2010.09.28 Add K.Shinohara End

							//IDリストへ追加
							buiNoList.add(String.format("%1$02d", buiNoInt));
							buiIDList.add(buiID);
							houkouIDList.add(houkouID);
							kHouhouIDList.add(kHouhouID);

							// 2010.07.30 Mod Y.Shibata Start
							if ((CommonString.MWM_MODE_RECEIPT.equals(param.GetMWMMode()))
								|| (CommonString.MWM_MODE_PORTABLE.equals(param.GetMWMMode())))
							//if (param.GetMWMMode() == CommonString.MWM_MODE_RECEIPT)
							// 2010.07.30 Mod Y.Shibata End
							{
								// 受付モード→部位情報を元にプリセット取得、その後撮影情報を取得し利用

								//プリセットの確認を行う
								PresetParameter pParam = new PresetParameter();
								pParam.SetKensatypeID(kensatypeID);
								pParam.SetBuiID(buiID);
								pParam.SetHoukouID(houkouID);
								pParam.SetSayuuID(sayuuID);
								pParam.SetKensaHouhouID(kHouhouID);
								// 2010.09.28 Add K.Shinohara Start
								pParam.SetUseFlag(TextUtil.ParseStringToInt(CommonString.USEFLAG_ON));
								// 2010.09.28 Add K.Shinohara End
								// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
								ArrayList presetBeanList = Configuration.GetInstance().GetCoreController().GetRisPresetInfoData(pParam, 0, con);
//								ArrayList presetBeanList = Configuration.GetInstance().GetCoreController().GetRisPresetInfoData(pParam, con);
								// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加
								PresetInfoBean presetBean = null;
								if (presetBeanList != null && presetBeanList.size() > 0)
								{
									presetBean = (PresetInfoBean)presetBeanList.get(0);
								}
								if (presetBean != null)
								{
									//プリセットID→撮影情報を取得する
									PresetParameter presetParam = new PresetParameter();
									presetParam.SetPresetID(presetBean.GetPresetID());
									ArrayList presetSatueiList = Configuration.GetInstance().GetCoreController().GetRisPresetSatueiData(presetParam, con);
									if (presetSatueiList.size() > 0)
									{
										int  satueiNoInt = 0;
										for (int k=0; k<presetSatueiList.size(); k++)
										{
											DataRow rowSatuei = (DataRow)presetSatueiList.get(k);
											satueiNoInt += 1;

											//IDリストへ追加
											String satueiNo = String.format("%1$02d", buiNoInt) + String.format("%1$02d", satueiNoInt);
											satueiNoList.add(satueiNo);
											// mod sta 201806_ポータブルRIS検査系種別対応
											String satueiID = rowSatuei.get(MasterUtil.RIS_SATUEIMENU_ID) == null ? "" : rowSatuei.get(MasterUtil.RIS_SATUEIMENU_ID).toString();
											satueiIDList.add(satueiID);
											// satueiIDList.add(rowSatuei.get(MasterUtil.RIS_SATUEIMENU_ID).toString());
											// mod end 201806_ポータブルRIS検査系種別対応
										}
									}
								}


							}
							else if (CommonString.MWM_MODE_OPERATION.equals(param.GetMWMMode()))
							{
								// 実施モード→Paramの撮影情報を元に設定
								// 2010.09.03 Mod K.Shinohara Start
								// 部位のチェックを追加する
								int  satueiNoInt = 0;
								for (int k=0; k<param.GetSatueiDt().getRowCount(); k++)
								{
									DataRow rowSatuei = param.GetSatueiDt().getRows().get(k);

									// 部位が違う場合は読み込まない
									if (buiNoInt != TextUtil.ParseStringToInt(rowSatuei.get(MasterUtil.RIS_BUI_NO).toString()))
									{
										continue;
									}

									satueiNoInt += 1;

									// 2010.09.14 Add K.Shinohara Start
									// 撮影系のみ
									if (CommonString.OPE_KBN_PHOTO.equals(param.GetOpeKbn()))
									{
										// 選択マークがセットされている撮影情報のみ対象とする
										if (!MARK_STR.equals(rowSatuei.get(MasterUtil.RIS_MARK).toString()))
										{
											continue;
										}
									}
									// 2010.09.14 Add K.Shinohara End

									//IDリストへ追加
									String satueiNo = String.format("%1$02d", buiNoInt) + String.format("%1$02d", satueiNoInt);
									satueiNoList.add(satueiNo);
									satueiIDList.add(rowSatuei.get(MasterUtil.RIS_SATUEIMENU_ID).toString());
									//// 2010.10.21 Add K.Shinohara Start
									//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO) &&
									//	param.GetOpeKbn().Equals(CommonString.OPE_KBN_PHOTO))
									//{
									//	kikiID2List.add(rowSatuei[MasterUtil.RIS_KENSAKIKI_ID2].toString());
									//}
									// 2010.10.21 Add K.Shinohara End
								}
							}
						}
					}

					// 2010.11.11 Add K.Shinohara Start
					//撮影系で撮影情報が無い場合は対象外
					//検査系は無くてもOK
					if (CommonString.OPE_KBN_PHOTO.equals(kensaKbn) && satueiIDList.size() <= 0)
					{
						disTargetRisIDList.add(risIDStr);

						// ログ出力
						String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMSatueiDataNullException_MessageString);
						message = message + " " + risIDStr;
						logger.warn(message);

						// エラーダイアログ出力
						if (!param.GetAllFlg())
						{
							//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
						}
					} else
					// 2010.11.11 Add K.Shinohara End
					//部位が0件の場合
					if (!buiMatchFlg)
					{
						//対象外
						disTargetRisIDList.add(risIDStr);
						// 2010.09.15 Add K.Shinohara Start
						// ログ出力
						String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMBuiDataNullException_MessageString);
						message = message + "" + risIDStr;
						logger.warn(message);
						// 2010.09.15 Add K.Shinohara End

						// 2010.09.30 Add K.Shinohara Start
						// エラーダイアログ出力
						if (!param.GetAllFlg())
						{
							//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
						}
						// 2010.09.30 Add K.Shinohara End
					}
					else
					{
						//対象→IDリストをHashへ追加
						ArrayList keysList = new ArrayList();
						keysList.add(buiNoList);
						keysList.add(buiIDList);
						keysList.add(houkouIDList);
						keysList.add(kHouhouIDList);
						keysList.add(satueiNoList);
						keysList.add(satueiIDList);
						// 2010.10.21 Add K.Shinohara Start
						keysList.add(kikiID2List);
						// 2010.10.21 Add K.Shinohara End
						targetOrderKeyHash.put(risIDStr, keysList);
					}
				}
			}

			//全オーダの撮影IDリストを元に撮影情報を取得する
			DataTable satueiDt = GetMWMSatueiData(targetOrderKeyHash, con);


			// 2010.07.30 Mod K.Shinohara Start
			// MWM登録処理

			// 2012.06.28 Add H.Orikasa Start NML-3-X56-8
			// システム日付取得
			Timestamp sysDateTime = Configuration.GetInstance().GetSysDate(con);
			// 2012.06.28 Add H.Orikasa End

			// 2010.10.04 Add Y.Shibata Start KANRO-R-1
			ArrayList kensaKikiList = new ArrayList();
			kensaKikiList.clear();
			kensaKikiList.add(targetKikiID);
			// 2010.10.21 Del K.Shinohara
			//ArrayList kikiFromSatueiDataList = new ArrayList();	// 2010.09.17 Add Y.Shibata KANRO-R-1 修正2
			/* 一ノ瀬保留
			//関労特注
			if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
			{
				//機器が複数設定されていた場合それを適用する
				if (param.GetMWMMode() == CommonString.MWM_MODE_OPERATION &&
				param.GetOpeKbn().Equals(CommonString.OPE_KBN_PHOTO))
				{
					kensaKikiList.Clear();

					//撮影情報のループ
					for (int j=0; j<param.GetSatueiDt().Rows.Count; j++)
					{
						// 選択マークがセットされている撮影情報のみ対象とする(撮影系のみ)
						if (!param.GetSatueiDt().Rows[j][MasterUtil.RIS_MARK].toString().Equals(OperationDetailForm.MARK_STR))
						{
							continue;
						}

						//検査機器ID取得
						String satueiKikiID = param.GetSatueiDt().Rows[j][MasterUtil.RIS_KENSAKIKI_ID2].toString();
						if (!string.IsNullOrEmpty(satueiKikiID))
						{
							// 2010.10.21 Mod K.Shinohara Start
							bool kikiAddFlg = true;
							for (int i = 0; i < kensaKikiList.Count; i++)
							{
								if (kensaKikiList[i].Equals(satueiKikiID))
								{
									kikiAddFlg = false;
									break;
								}
							}
							if (kikiAddFlg)
							{
								kensaKikiList.add(satueiKikiID);
							}
							//kensaKikiList.add(satueiKikiID);
							// 2010.10.21 Mod K.Shinohara End
							// 2010.10.21 Del K.Shinohara
							//kikiFromSatueiDataList.add(param.GetSatueiDt().Rows[j]);	// 2010.10.04 Add Y.Shibata KANRO-R-1 修正2
						}
					}
				}
			}
			*/

			// 2019.03.01 Add H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
			//仮想機器が有効の場合、仮想機器データを使用する
			DataTable mstSystemParam2 = Configuration.GetInstance().GetRRisSystemParam2();
			String mwmCntstr = Configuration.GetInstance().GetSystemParamValue(mstSystemParam2, RisSystemParamTbl.MWM_MULTICOUNT, RisSystemParamTbl.VALUE1_COLUMN);
			Integer mwmCntint = Util.isNullAndParseInt(mwmCntstr);

			if (mwmCntint != null)
			{

				if ((CommonString.KENSAKIKI_MULTIMWM_DEFAULTCOUNT <= mwmCntint)
					&& (mwmCntint <= CommonString.KENSAKIKI_MULTIMWM_MAXCOUNT))
				{
					//仮想検査機器を取得
					ArrayList vKikiList = new ArrayList();
					ArrayList vSumKikiList = new ArrayList();

					for (int lp = 0; lp < kensaKikiList.size(); lp++)
					{
						//実施機器を取得
						vKikiList = (ArrayList)GetMultiKensaKikiIDList(mstKensakiki, kensaKikiList.get(lp).toString()).clone();

						for (int lp2 = 0; lp2 < vKikiList.size(); lp2++)
						{
							vSumKikiList.add(vKikiList.get(lp2).toString());
						}
					}

					kensaKikiList.clear();

					kensaKikiList = (ArrayList)vSumKikiList.clone();

					// 仮想機器モードフラグ保持
					multiMWMModeFlg = true;
				}
			}
			// 2019.03.01 Add H.Watanabe@COSMO End   20190301：山形中央病院向け対応

			/*
			//仮想機器が有効の場合、仮想機器データを使用する
			DataTable mstSystemParam2 = Configuration.GetInstance().GetCoreController().GetMaster(MasterUtil.RIS_SYSTEMPARAM2, true);
			String mwmCntstr = Configuration.GetInstance().GetSystemParamValue(mstSystemParam2, RisSystemParamTbl.MWM_MULTICOUNT, RisSystemParamTbl.VALUE1_COLUMN);
			int mwmCntint;
			if (int.TryParse(mwmCntstr, out mwmCntint))
			{
				if ((CommonString.KENSAKIKI_MULTIMWM_DEFAULTCOUNT <= mwmCntint)
					&& (mwmCntint <= CommonString.KENSAKIKI_MULTIMWM_MAXCOUNT))
				{
					//仮想検査機器を取得
					ArrayList vKikiList = new ArrayList();
					ArrayList vSumKikiList = new ArrayList();
					// 2010.10.21 Del K.Shinohara
					//ArrayList vkikiFromSatueiDataList = new ArrayList();	// 2010.09.17 Add Y.Shibata KANRO-R-1 修正2
					for (int lp = 0; lp < kensaKikiList.Count; lp++)
					{
						//実施機器を取得
						vKikiList = (ArrayList)GetMultiKensaKikiIDList(mstKensakiki, kensaKikiList[lp].toString()).Clone();
						for (int lp2 = 0; lp2 < vKikiList.Count; lp2++)
						{
							vSumKikiList.add(vKikiList[lp2].toString());
							// 2010.10.21 Del K.Shinohara Start
							// 2010.10.04 Mod Y.Shibata Start KANRO-R-1 修正3
							//if (kikiFromSatueiDataList.Count > 0)
							//{
							//    vkikiFromSatueiDataList.add(kikiFromSatueiDataList[lp]);
							//}
							//vkikiFromSatueiDataList.add(kikiFromSatueiDataList[lp]);	// 2010.10.04 Add Y.Shibata KANRO-R-1 修正2
							// 2010.10.04 Mod Y.Shibata End
							// 2010.10.21 Del K.Shinohara End
						}
					}

					kensaKikiList.Clear();
					kensaKikiList = (ArrayList)vSumKikiList.Clone();
					// 2010.10.21 Del K.Shinohara Start
					// 2010.10.04 Add Y.Shibata Start KANRO-R-1 修正2
					//kikiFromSatueiDataList.Clear();
					//kikiFromSatueiDataList = (ArrayList)vkikiFromSatueiDataList.Clone();
					// 2010.10.04 Add Y.Shibata End
					// 2010.10.21 Del K.Shinohara End

					// 2010.10.21 Add K.Shinohara Start
					// 仮想機器モードフラグ保持
					multiMWMModeFlg = true;
					// 2010.10.21 Add K.Shinohara End
				}
			}
			*/

			for (int lp = 0; lp < kensaKikiList.size(); lp++)
			{
				// 対象機器IDを置き換える
				targetKikiID = kensaKikiList.get(lp).toString();

				// 2010.08.04 Add K.Shinohara Start
				// MWMTypeが空欄の場合は何もしない
				if (!CheckMWMType(mstKensakiki, targetKikiID))
				{
					continue;
				}
				// 2010.08.04 Add K.Shinohara End

				ArrayList insMWMInfoList = new ArrayList();
				ArrayList updPatientIDList = new ArrayList();
				KanaRomaConvert kanaRomaConv = new KanaRomaConvert(Configuration.GetInstance().getKanaromatext());
				Enumeration e3 = targetOrderHash.keys();
				while (e3.hasMoreElements()){
					String risIDStr = (String)e3.nextElement();
					//対象外オーダは除く
					if (disTargetRisIDList.contains(risIDStr))
					{
						continue;
					}

					DataRow orderRow = (DataRow)targetOrderHash.get(risIDStr);

					// 各種キー情報の準備

					//
					// ↓↓↓　RISオーダ登録画面ではRowを自作しているため、行の追加がある場合はそちらのRowも追加する事　↓↓↓
					//

					//値の設定
					String odr_risID			= GetDataRowContainsString(orderRow, RisSummaryView.RIS_ID_COLUMN);
					String odr_orderNO			= GetDataRowContainsString(orderRow, RisSummaryView.ORDERNO_COLUMN);
					String odr_accessionNo		= GetDataRowContainsString(orderRow, RisSummaryView.ACNO_COLUMN);
					String odr_studyInstanceUID = GetDataRowContainsString(orderRow, RisSummaryView.STUDYINSTANCEUID_COLUMN);
					String odr_status			= GetDataRowContainsString(orderRow, RisSummaryView.STATUS_COLUMN);
					String odr_kanjaID			= GetDataRowContainsString(orderRow, RisSummaryView.KANJA_ID_COLUMN);
					String odr_exStartDateStr	= GetDataRowContainsString(orderRow, RisSummaryView.EXAMSTARTDATE_COLUMN);
					Timestamp odr_exStartDate = Const.TIMESTAMP_MINVALUE;
					try
					{
						odr_exStartDate = sysDateTime;
					}
					catch (Exception e)
					{
					}
					String odr_kikiID			= GetDataRowContainsString(orderRow, RisSummaryView.KENSAKIKI_ID_COLUMN);
					String odr_kensaDateStr		= GetDataRowContainsString(orderRow, RisSummaryView.KENSA_DATE_AGE_COLUMN);
					int odr_kensaDateInt = 0;
					try
					{
						if (odr_kensaDateStr.length() > 0)
						{
							odr_kensaDateInt = Integer.parseInt(odr_kensaDateStr);
						}
					}
					catch (Exception e)
					{
					}
					String odr_doctorNo			= GetDataRowContainsString(orderRow, RisSummaryView.IRAI_DOCTOR_NO_COLUMN);
					String odr_doctorName		= mUtil.FindData(mstDoctor, MasterUtil.RIS_DOCTOR_NAME, MasterUtil.RIS_DOCTOR_ID, odr_doctorNo);
					String odr_doctorEng		= mUtil.FindData(mstDoctor, MasterUtil.RIS_DOCTOR_ENGLISH_NAME, MasterUtil.RIS_DOCTOR_ID, odr_doctorNo);
					String odr_byoutouID		= GetDataRowContainsString(orderRow, RisSummaryView.BYOUTOU_ID_COLUMN);
					String odr_sectionID		= GetDataRowContainsString(orderRow, RisSummaryView.IRAI_SECTION_ID_COLUMN);
					String odr_sectionName		= mUtil.FindData(mstSection, MasterUtil.RIS_SECTION_NAME, MasterUtil.RIS_SECTION_ID, odr_sectionID);
					String odr_sectionEng		= mUtil.FindData(mstSection, MasterUtil.RIS_SECTION_ENGLISHNAME, MasterUtil.RIS_SECTION_ID, odr_sectionID);

					//
					// ↑↑↑　RISオーダ登録画面ではRowを自作しているため、行の追加がある場合はそちらのRowも追加する事　↑↑↑
					//



					// 必要な情報を取得する

					//オーダ情報の取得
	                // 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
					OrderInfoBean orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(odr_risID, 0, con);
//					OrderInfoBean orderBean = Configuration.GetInstance().GetCoreController().GetRisOrderInfo(odr_risID, con);
	                // 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加

					//実績情報の取得
					String ex_startNumber = "";
	                // 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
					ExecutionInfoBean exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(odr_risID, 0, con);
//					ExecutionInfoBean exBean = Configuration.GetInstance().GetCoreController().GetRisExecutionInfo(odr_risID, con);
	                // 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加
					if (exBean != null)
					{
						ex_startNumber = exBean.GetStartNumber().toString();
						// 2010.08.13 Mod K.Shinohara Start
						if ((CommonString.MWM_MODE_OPERATION.equals(param.GetMWMMode())) &&
							("0".equals(ex_startNumber)))
						{
							ex_startNumber = "1";
						}
						//if (ex_startNumber == "0")
						//{
						//    ex_startNumber = "1";
						//}
						// 2010.08.13 Mod K.Shinohara End
					}

					//MWM処理用に検査機器情報の取得
					KensaKikiBean kikiBean = null;

					kikiBean = AppCommon.GetMWMKensaKikiBean(mstKensakiki, targetKikiID);

					//検査機器情報の整合性チェック
					if (!AppCommon.CheckMWMKikiBean(kikiBean))
					{
						return false;
					}

					//患者情報の取得
					//2010.09.08 Mod H.Orikasa Start
					//(標準)患者進捗が受済以降の場合は患者履歴よりデータを取得する
					PatientInfoBean patientBean = null;
					if (TextUtil.ParseStringToInt(odr_status) >= TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED))
					{
						//受済→実績患者情報
						patientBean = Configuration.GetInstance().GetCoreController().GetRisResultPatientInfo(odr_risID, odr_kanjaID, con);
					}
					else
					{
						//未受→最新の患者情報
						patientBean = Configuration.GetInstance().GetCoreController().GetRisPatientInfo(odr_kanjaID, con);
					}

					//2010.09.08 Mod H.Orikasa End
					if (patientBean == null)
					{
						String msg = "患者情報が設定されていません。";
						//return false;
						throw new Exception(msg);
					}

					//患者ローマ字チェックと更新を行う(未処理患者のみ)
					if (!updPatientIDList.contains(odr_kanjaID))
					{
						//患者ローマ字チェック
						if (AppCommon.CheckMWMPatientRoma(patientBean))
						{
							//患者情報を更新する
							Configuration.GetInstance().GetCoreController().UpdateRisPatientData(patientBean, con);
						}
						updPatientIDList.add(odr_kanjaID);
					}

					//患者情報の整合性チェックとコンバート
					MwmPatientInfoBean mwmPatientInfoBean = AppCommon.CheckConvertMWMpatientBean(patientBean);

					//患者情報がnullの場合は処理を抜ける
					if (mwmPatientInfoBean == null)
					{
						String msg = "MWM患者情報が設定されていません。";
						//return false;
						throw new Exception(msg);
					}

					//氏名キャレット変換
					if (CommonString.FLG_ON.equals(kikiBean.GetNameMode()))
					{
						//スペースを^へ変換
						mwmPatientInfoBean.SetRomaSimei(ConvMWMPatientName(mwmPatientInfoBean.GetRomaSimei()));
						mwmPatientInfoBean.SetKanjiSimei(ConvMWMPatientName(mwmPatientInfoBean.GetKanjiSimei()));
						mwmPatientInfoBean.SetKanaSimei(ConvMWMPatientName(mwmPatientInfoBean.GetKanaSimei()));
					}

					/* 一ノ瀬保留
					// 2011.08.05 Add T.Ootsuka@MERX Start NML-CAT2-015
					// StudyInstanceUIDの末尾採番付加
					String incrementStr = mUtil.FindData(mstKensakiki, MasterUtil.RIS_STUDYINSTANCEUID_INCREMENT, MasterUtil.RIS_KENSAKIKI_ID, kikiBean.GetKensaKikiID());
					if (!StringUtils.isEmpty(incrementStr) && kensaKikiList.size() > 0)
					{
						odr_studyInstanceUID += "." + incrementStr;
					}
					// 2011.08.05 Add T.Ootsuka@MERX End
					*/


					// 登録するMWM情報の準備

					MWMInfoBean mwmInfoBean = new MWMInfoBean();
					String wkStr = "";

					// 設定する値

					String setSchStationAETitle = "";
					String setSchProcStepLocation = "";
					Timestamp setSchProcStepStartDate = Const.TIMESTAMP_MINVALUE;
					Timestamp setSchProcStepStartTime = Const.TIMESTAMP_MINVALUE;
					String setSchPerfPhysiciansNameRoma = "";
					String setSchPerfPhysiciansNameKanji = "";
					String setSchPerfPhysiciansNameKana = "";
					String setSchProcStepDescription = "";
					String setSchProcStepID = "";
					String setCommetsOnTheSchProcStep = "";					//空で登録
					String setModality = "";
					String setReqProcID = "";
					String setStudyInstanceUID = "";
					String setReqProcDescreption = "";
					String setRequestingPhysician = "";
					String setRequestingService = "";
					String setAccessionNumber = "";
					String setInstitutionName = "";					//空で登録
					String setInstitutionAddress = "";					//空で登録
					String setPatientNameRoma = "";
					String setPatientNameKanji = "";
					String setPatientNameKana = "";
					String setPatientID = "";
					Timestamp setPatientBirthDate = Const.TIMESTAMP_MINVALUE;
					String setPatientSex = "";
					String setPatientWeight = "";
					String setActionCodes = "";
					String setActionVersion = "";
					String setActionDesignator = "";
					String setActionMeanings = "";
					String setRefStudyClassUID = "";
					String setRefStudyInstanceUID = "";
					String setRefPhysicianName = "";					//空で登録
					String setPatientResidence = "";
					String setPatientSize = "";
					String setPatientComments = "";					//空で登録
					String setReqProcedureCodes = "";
					String setReqProcedureDesignator = "";
					String setReqProcedureVersion = "";
					String setReqProcedureMeanings = "";
					int setNumberOfCopies = 0;					//0で登録



					// 値を設定する

					setSchStationAETitle = kikiBean.GetAETitle();
					setSchProcStepLocation = kikiBean.GetAETitle();
					setSchProcStepStartDate = odr_exStartDate;
					setSchProcStepStartTime = odr_exStartDate;

					if (CommonString.MWM_TYPE_DICOM.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_FIRIPS_MR.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM3.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_CR.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_CR2.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_FCR.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_FCR2.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_FCR3.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_KOCR.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_KOCR2.equals(kikiBean.GetMwmType()))
					{
						setSchPerfPhysiciansNameRoma = "";
						setSchPerfPhysiciansNameKanji = "";
						setSchPerfPhysiciansNameKana = "";
					}
					else if (CommonString.MWM_TYPE_DICOM_KOCR3.equals(kikiBean.GetMwmType()))
					{
						setSchPerfPhysiciansNameRoma = param.GetGisiID();
						setSchPerfPhysiciansNameKanji = param.GetGisiName();
						setSchPerfPhysiciansNameKana = "";
					}

					// 2010.09.01 Mod K.Shinohara Start
					// 実施モードの場合は詳細画面の部位、それ以外はオーダ部位を用いる
					BuiInfoBean buiBean = null;

					if (CommonString.MWM_MODE_OPERATION.equals(param.GetMWMMode()))
					{
						// 詳細画面の部位を用いる
						if (CommonString.OPE_KBN_PHOTO.equals(param.GetOpeKbn()))
						{
							// 撮影系
							if ((param.GetBuiDt() != null) && (param.GetBuiDt().getRowCount() > 0) &&
								(param.GetSatueiDt() != null) && (param.GetSatueiDt().getRowCount() > 0))
							{
								for (int i = 0; i < param.GetSatueiDt().getRowCount(); i++)
								{
									DataRow satueiRow = param.GetSatueiDt().getRows().get(i);

									// 選択状態の撮影に紐付く部位を対象とする
									if (MARK_STR.equalsIgnoreCase(satueiRow.get(MasterUtil.RIS_MARK).toString()))
									{
										String buiNo = satueiRow.get(MasterUtil.RIS_BUI_NO).toString();

										for (int j = 0; j < param.GetBuiDt().getRowCount(); j++)
										{
											DataRow buiRow = param.GetBuiDt().getRows().get(j);

											if (buiNo.equals(buiRow.get(MasterUtil.RIS_BUI_NO).toString()))
											{
												buiBean = new BuiInfoBean();
												buiBean.SetBuiID(buiRow.get(MasterUtil.RIS_BUI_ID).toString());
												buiBean.SetHoukouID(buiRow.get(MasterUtil.RIS_HOUKOU_ID).toString());
												buiBean.SetKensaHouhouID(buiRow.get(MasterUtil.RIS_KENSAHOUHOU_ID).toString());

												break;
											}
										}

										break;
									}
								}
							}

						}
						else if (CommonString.OPE_KBN_INSPECT.equals(param.GetOpeKbn()))
						{
							// 検査系
							if ((param.GetBuiDt() != null) && (param.GetBuiDt().getRowCount() > 0))
							{
								for (int i = 0; i < param.GetBuiDt().getRowCount(); i++)
								{
									DataRow row = param.GetBuiDt().getRows().get(i);

									// 選択状態の部位を対象とする
									if (MARK_STR.equals(row.get(MasterUtil.RIS_MARK).toString()))
									{
										buiBean = new BuiInfoBean();
										buiBean.SetBuiID(row.get(MasterUtil.RIS_BUI_ID).toString());
										buiBean.SetHoukouID(row.get(MasterUtil.RIS_HOUKOU_ID).toString());
										buiBean.SetKensaHouhouID(row.get(MasterUtil.RIS_KENSAHOUHOU_ID).toString());

										break;
									}
								}
							}

						}
					}
					else
					{
						// オーダ部位を用いる
						if (orderBean.GetOrderBuiList().size() > 0)
						{
							OrderBuiBean orderBuiBean = (OrderBuiBean)orderBean.GetOrderBuiList().get(0);

							buiBean = new BuiInfoBean();
							buiBean.SetBuiID(orderBuiBean.GetBuiID());
							buiBean.SetHoukouID(orderBuiBean.GetHoukouID());
							buiBean.SetKensaHouhouID(orderBuiBean.GetKensaHouhouID());
						}
					}

					// 部位情報が取得できた場合
					if (buiBean != null)
					{
						//部位英語名
						String engBuiName = mUtil.FindData(mstBui, MasterUtil.RIS_BUI_ENGLISHNAME, MasterUtil.RIS_BUI_ID, buiBean.GetBuiID());
						//方向英語名
						String engHoukouName = mUtil.FindData(mstHoukou, MasterUtil.RIS_HOUKOU_ENGLISHNAME, MasterUtil.RIS_HOUKOU_ID, buiBean.GetHoukouID());
						//検査方法英語名
						String engKHouhouName = mUtil.FindData(mstKHouhou, MasterUtil.RIS_KENSAHOUHOU_ENGLISHNAME, MasterUtil.RIS_KENSAHOUHOU_ID, buiBean.GetKensaHouhouID());

						//部位-MWMタイプ判断
						if (!(CommonString.MWM_TYPE_DICOM2.equals(kikiBean.GetMwmType()))
								&& !(CommonString.MWM_TYPE_DICOM2_PATCOMMENT.equals(kikiBean.GetMwmType())))
						{
							if (engBuiName.length() <= 0)
							{
								//英語名が無い場合はIDを設定
								engBuiName = buiBean.GetBuiID();
							}
						}

						//方向
						if (engHoukouName.length() <= 0)
						{
							//英語名が無い場合はIDを設定
							engHoukouName = buiBean.GetHoukouID();
						}

						//検査方法
						if (engKHouhouName.length() <= 0)
						{
							//英語名が無い場合はIDを設定
							engKHouhouName = buiBean.GetKensaHouhouID();
						}

						//設定値の決定
						if (CommonString.MWM_TYPE_DICOM.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_FIRIPS_MR.equals(kikiBean.GetMwmType()))
						{
							wkStr = engBuiName + " " + engKHouhouName;
							setSchProcStepDescription = wkStr;
							setReqProcDescreption = wkStr;
						}
						else if (CommonString.MWM_TYPE_DICOM2.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM2_PATCOMMENT.equals(kikiBean.GetMwmType()))
						{
							if (engBuiName.length() > 0)
							{
								wkStr = engBuiName;
							}
							else
							{
								wkStr = CommonString.MWM_UNKNOWN;
								if (kikiBean.GetMWMParam07().length() > 0)
								{
									wkStr = kikiBean.GetMWMParam07();
								}
							}
							setSchProcStepDescription = wkStr;
							setReqProcDescreption = wkStr;
						}
						else if (CommonString.MWM_TYPE_DICOM_CR.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_CR2.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_FCR.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_FCR2.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_FCR3.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_KOCR.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_KOCR2.equals(kikiBean.GetMwmType()) ||
								CommonString.MWM_TYPE_DICOM_KOCR3.equals(kikiBean.GetMwmType()))
						{
							wkStr = engBuiName + " " + engHoukouName;
							setSchProcStepDescription = wkStr;
							setReqProcDescreption = wkStr;
						}

						if (!(CommonString.MWM_TYPE_DICOM3.equals(kikiBean.GetMwmType())))
						{
							if (setSchProcStepDescription.length() <= 0)
							{
								setSchProcStepDescription = CommonString.MWM_UNKNOWN_KENSA_NAME;
							}
						}
					}

					// 2010.09.01 Mod K.Shinohara End

					setSchProcStepID = ex_startNumber;
					setModality = kikiBean.GetModality();
					// 2010.09.15 Add K.Shinohara Start
					// 検査機器にモダリティがセットされていない場合、検査種別マスタのモダリティを用いる
					if ("".equals(setModality))
					{
						String kensatypeID = ((DataRow)targetOrderHash.get(risIDStr)).get(MasterUtil.RIS_KENSATYPE_ID).toString();
						setModality = Configuration.GetInstance().GetKensaTypeModality(mstKensatype, kensatypeID, con);
					}
					// 2010.09.15 Add K.Shinohara End
					setReqProcID = odr_orderNO;
					setStudyInstanceUID = odr_studyInstanceUID;
					setAccessionNumber = odr_accessionNo;
					setPatientNameRoma = kanaRomaConv.GetRomaConvert(mwmPatientInfoBean.GetRomaSimei());
					//setPatientNameRoma	= mwmPatientInfoBean.GetRomaSimei().ToUpper();
					setPatientNameKanji = mwmPatientInfoBean.GetKanjiSimei();
					setPatientNameKana = mwmPatientInfoBean.GetKanaSimei();
					setPatientID = mwmPatientInfoBean.GetKanjaID();
					setPatientBirthDate = mwmPatientInfoBean.GetBirthdayDateTime();
					setPatientSex = mwmPatientInfoBean.GetSex();
					setPatientSize = mwmPatientInfoBean.GetTallMeter();
					setPatientWeight = mwmPatientInfoBean.GetWeight();

					//各キーリスト
					ArrayList keysList = (ArrayList)targetOrderKeyHash.get(risIDStr);

					//SCH_ACTION_CODESの設定
					//撮影IDリストのループ
					ArrayList satueiNoList = (ArrayList)keysList.get(MWMOrderKeys.SatueiNo.getId());
					ArrayList satueiIDList = (ArrayList)keysList.get(MWMOrderKeys.SatueiID.getId());
					// 2010.10.21 Add K.Shinohara Start
					ArrayList kikiID2List = new ArrayList();
					if (CommonString.MWM_MODE_OPERATION.equals(param.GetMWMMode()) &&
							CommonString.OPE_KBN_PHOTO.equals(param.GetOpeKbn()))
					{
						kikiID2List = (ArrayList)keysList.get(MWMOrderKeys.KikiID2.getId());
					}
					// 2010.10.21 Add K.Shinohara End
					if (satueiDt != null)
					{
						for (int i = 0; i < satueiIDList.size(); i++)
						{
							wkStr = "";
							String satueiNo = satueiNoList.get(i).toString();
							String satueiID = satueiIDList.get(i).toString();
							String satueiName = mUtil.FindData(satueiDt, MasterUtil.RIS_SATUEIMENU_NAME, MasterUtil.RIS_SATUEIMENU_ID, satueiID);
							String satueiKana = mUtil.FindData(satueiDt, MasterUtil.RIS_SATUEIMENU_NAME_KANA, MasterUtil.RIS_SATUEIMENU_ID, satueiID);
							String satueiEng = mUtil.FindData(satueiDt, MasterUtil.RIS_SATUEIMENU_NAME_ENGLISH, MasterUtil.RIS_SATUEIMENU_ID, satueiID);
							String satueiCode = GetMWMSatueiCode(satueiDt, satueiID, kikiBean.GetKikiType());

							// 2010.10.04 Add Y.Shibata Start KANRO-R-1 修正
							/* 一ノ瀬保留
							//関労特注
							if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
							{
								//撮影詳細画面の場合
								if (param.GetMWMMode().Equals(CommonString.MWM_MODE_OPERATION) &&
									param.GetOpeKbn().Equals(CommonString.OPE_KBN_PHOTO))
								{
									// 2010.10.21 Mod K.Shinohara Start
									String kensaKikiID2 = kikiID2List[i].toString();
									bool sendKikiFlg = false;

									// 仮想機器モードを考慮する
									if (multiMWMModeFlg)
									{
										// 仮想機器モード
										ArrayList multiKikiList = (ArrayList)GetMultiKensaKikiIDList(mstKensakiki, kensaKikiID2).Clone();
										for (int lp2 = 0; lp2 < multiKikiList.Count; lp2++)
										{
											if (multiKikiList[lp2].toString().Equals(targetKikiID))
											{
												sendKikiFlg = true;
												break;
											}
										}
									}
									else
									{
										// 通常モード
										sendKikiFlg = kensaKikiID2.Equals(targetKikiID);
									}
									// 作業中機器IDと撮影毎の機器IDが違う場合は処理しない
									if (!sendKikiFlg)
									{
										continue;
									}
									// コメント
									////詳細画面撮影情報の撮影IDと同一のを検索
									//bool hitBool = false;
									//// 2010.10.04 Mod Y.Shibata Start KANRO-R-1 修正2
									////作業中検査機器毎の撮影情報取得
									//DataRow satueiRow = (DataRow)kikiFromSatueiDataList[lp];
									//if (satueiRow != null)
									//{
									//    //撮影情報が作業中検査機器の撮影情報であれば続行する
									//    String mySatueiMenuID	= satueiRow[MasterUtil.RIS_SATUEIMENU_ID].toString();
									//    if (mySatueiMenuID.Equals(satueiID))
									//    {
									//        hitBool = true;
									//    }
									//}
									//// コメント化
									////for (int j=0; j<param.GetSatueiDt().Rows.Count; j++)
									////{
									////    // 詳細撮影情報に作業撮影IDがある場合続行する
									////    if (param.GetSatueiDt().Rows[j][MasterUtil.RIS_SATUEIMENU_ID].toString().Equals(satueiID))
									////    {
									////        //作業中機器が対象詳細撮影と同じかを確認
									////        String serchkikiID = param.GetSatueiDt().Rows[j][MasterUtil.RIS_KENSAKIKI_ID2].toString();
									////        ArrayList vKiki = (ArrayList)GetMultiKensaKikiIDList(mstKensakiki, serchkikiID).Clone();
									////        if (vKiki.Contains(targetKikiID))
									////        {
									////            hitBool = true;
									////            break;
									////        }
									////    }
									////}
									//
									//// 2010.10.04 Mod Y.Shibata End

									////撮影情報に自身の撮影情報が無い場合行わない
									//if (!hitBool)
									//{
									//    continue;
									//}

								}
							}
							// 2010.10.04 Add Y.Shibata End
							*/

							if (CommonString.MWM_TYPE_DICOM_FCR.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_FCR2.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_FCR3.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_KOCR2.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_KOCR3.equals(kikiBean.GetMwmType()))
							{
								//撮影コードの最後尾が*か判断する
								if (satueiCode.length() > 0 && "*".equals(satueiCode.substring(satueiCode.length() - 1, satueiCode.length())))
								{
									String setCode = satueiCode.substring(0, satueiCode.length() - 1);

									wkStr = setCode + "0";
									try
									{
										if (Integer.parseInt(Configuration.GetInstance().GetBabylowlimit()) <= odr_kensaDateInt &&
												Integer.parseInt(Configuration.GetInstance().GetBabyhighlimit()) > odr_kensaDateInt)
										{
											//乳児の場合
											wkStr = setCode + "2";
										}
										else if (Integer.parseInt(Configuration.GetInstance().GetChildlowlimit()) <= odr_kensaDateInt &&
												Integer.parseInt(Configuration.GetInstance().GetChildhighlimit()) > odr_kensaDateInt)
										{
											//小児の場合
											wkStr = setCode + "1";
										}
									}
									catch (Exception ex)
									{
										logger.fatal(ex);
									}
								}
								else
								{
									wkStr = satueiCode;
								}
							}
							else if (CommonString.MWM_TYPE_DICOM_CR.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_CR2.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_KOCR.equals(kikiBean.GetMwmType()))
							{
								wkStr = satueiCode;
							}

							if (setActionCodes.length() <= 0)
							{
								setActionCodes = wkStr;
							}
							else
							{
								setActionCodes += CommonString.MWM_DELIMITATION + wkStr;
							}

							//SCH_ACTION_VERSIONの設定
							if (setActionVersion.length() <= 0)
							{
								setActionVersion = kikiBean.GetMWMParam01();
							}
							else
							{
								setActionVersion += CommonString.MWM_DELIMITATION + kikiBean.GetMWMParam01();
							}

							//SCH_ACTION_DESIGNATORの設定
							if (CommonString.MWM_TYPE_DICOM_FCR.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_FCR2.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_FCR3.equals(kikiBean.GetMwmType()))
							{
								wkStr = satueiNo;
							}
							else
							{
								wkStr = kikiBean.GetMWMParam02();
							}
							if (setActionDesignator.length() <= 0)
							{
								setActionDesignator = wkStr;
							}
							else
							{
								setActionDesignator += CommonString.MWM_DELIMITATION + wkStr;
							}

							//SCH_ACTION_MEANINGSの設定
							if (CommonString.MWM_TYPE_DICOM_CR.equals(kikiBean.GetMwmType()))
							{
								wkStr = satueiEng;
							}
							else if (CommonString.MWM_TYPE_DICOM_CR2.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_FCR.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_FCR3.equals(kikiBean.GetMwmType()))
							{
								wkStr = satueiKana;
							}
							else if (CommonString.MWM_TYPE_DICOM_KOCR.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_KOCR2.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_KOCR3.equals(kikiBean.GetMwmType()) ||
									CommonString.MWM_TYPE_DICOM_FCR2.equals(kikiBean.GetMwmType()))
							{
								wkStr = satueiName;
							}
							if (setActionMeanings.length() <= 0)
							{
								setActionMeanings = wkStr;
							}
							else
							{
								setActionMeanings += CommonString.MWM_DELIMITATION + wkStr;
							}
						}
					}

					setRefStudyClassUID = odr_studyInstanceUID;
					setRefStudyInstanceUID = odr_studyInstanceUID;

					//下記MWMタイプの場合はクリアする
					if (CommonString.MWM_TYPE_DICOM.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM_FIRIPS_MR.equals(kikiBean.GetMwmType()) ||
							CommonString.MWM_TYPE_DICOM3.equals(kikiBean.GetMwmType()))
					{
						setActionCodes = "";
						setActionVersion = "";
						setActionDesignator = "";
						setActionMeanings = "";
					}

					// 2010.08.13 Mod K.Shinohara Start
					if (CommonString.MWM_TYPE_DICOM_FIRIPS_MR.equals(kikiBean.GetMwmType()))
					{
						setReqProcedureCodes = kikiBean.GetMWMParam03();
						// 2010.09.01 Mod K.Shinohara Start
						setReqProcedureVersion = kikiBean.GetMWMParam04();
						setReqProcedureDesignator = kikiBean.GetMWMParam05();
						//setReqProcedureDesignator = kikiBean.GetMWMParam04();
						//setReqProcedureVersion = kikiBean.GetMWMParam05();
						// 2010.09.01 Mod K.Shinohara End
						setReqProcedureMeanings = kikiBean.GetMWMParam06();
					}
					//if (kikiBean.GetKikiType() == CommonString.MWM_TYPE_DICOM_FIRIPS_MR)
					//{
					//    setReqProcedureCodes = kikiBean.GetMWMParam03();
					//    setReqProcedureDesignator = kikiBean.GetMWMParam04();
					//    setReqProcedureVersion = kikiBean.GetMWMParam05();
					//    setReqProcedureMeanings = kikiBean.GetMWMParam06();
					//}
					// 2010.08.13 Mod K.Shinohara End

					// 2010.08.13 Mod K.Shinohara Start
					if (CommonString.MWM_TYPE_DICOM_KOCR3.equals(kikiBean.GetMwmType()))
					{
						setRequestingPhysician = odr_doctorNo + "=" + odr_doctorName;
						setPatientResidence = odr_byoutouID;
					}
					else
					{
						if (odr_doctorEng.length() > 0 &&
                        odr_doctorName.length() > 0 &&
                        odr_doctorNo.length() > 0)
						{
							setRequestingPhysician = odr_doctorEng + "=" + odr_doctorName;
						}
						else if (odr_doctorEng.length() <= 0 &&
                             odr_doctorName.length() > 0 &&
                             odr_doctorNo.length() > 0)
						{
							setRequestingPhysician = odr_doctorNo + "=" + odr_doctorName;
						}
						else
						{
							setRequestingPhysician = odr_doctorNo;
						}
						setPatientResidence = "";
					}

					// 2010.08.13 Mod K.Shinohara Start
					if (CommonString.MWM_TYPE_DICOM_FCR3.equals(kikiBean.GetMwmType()))
					{
						setRequestingService = odr_sectionName;
					}
					else
					{
						setRequestingService = odr_sectionEng;
					}
					// 2010.08.13 Mod K.Shinohara End



					// データチェックと補填

					// 2010.08.13 Mod K.Shinohara Start
					if (CommonString.MWM_TYPE_DICOM_KOCR3.equals(kikiBean.GetMwmType()))
					{
						// AE-Title
						if (setSchStationAETitle.length() <= 0)
						{
							setSchStationAETitle = CommonString.MWM_UNKNOWN_AE_TITLE;
							setSchProcStepLocation = CommonString.MWM_UNKNOWN_AE_TITLE;
						}
						// SCH_PROC_STEP_START_DATE
						if (setSchProcStepStartDate.toString().length() <= 0)
						{
							//setSchProcStepStartDate	= CommonString.MWM_UNKNOWN_KENSA_DATE;
							//setSchProcStepStartTime	= CommonString.MWM_UNKNOWN_KENSA_DATE;
						}
						// PATIENT_NAME_ROMA
						if (setPatientNameRoma.length() <= 0)
						{
							setPatientNameRoma = CommonString.MWM_UNKNOWN_ROMASIMEI;
						}
						// PATIENT_NAME_KANJI
						if (setPatientNameKanji.length() <= 0)
						{
							setPatientNameKanji = CommonString.MWM_UNKNOWN_KANJISIMEI2;
						}
						// PATIENT_NAME_KANA
						if (setPatientNameKana.length() <= 0)
						{
							setPatientNameKana = CommonString.MWM_UNKNOWN_KANASIMEI;
						}
					}


					// MWM用オーダ情報へ設定し、リストへ追加

					mwmInfoBean.SetSchStationAeTitle(setSchStationAETitle);
					mwmInfoBean.SetSchProcStepLocation(setSchProcStepLocation);
					mwmInfoBean.SetSchProcStepStartDate(setSchProcStepStartDate);
					mwmInfoBean.SetSchProcStepStartTime(setSchProcStepStartTime);
					mwmInfoBean.SetSchPerfPhysiciansNameRoma(setSchPerfPhysiciansNameRoma);
					mwmInfoBean.SetSchPerfPhysiciansNameKanji(setSchPerfPhysiciansNameKanji);
					mwmInfoBean.SetSchPerfPhysiciansNameKana(setSchPerfPhysiciansNameKana);
					mwmInfoBean.SetSchProcStepDescription(setSchProcStepDescription);
					mwmInfoBean.SetSchProcStepID(setSchProcStepID);
					mwmInfoBean.SetCommentsOnTheSchProcStep(setCommetsOnTheSchProcStep);
					mwmInfoBean.SetModality(setModality);
					mwmInfoBean.SetReqProcID(setReqProcID);
					mwmInfoBean.SetStudyInstanceUID(setStudyInstanceUID);
					mwmInfoBean.SetReqProcDescription(setReqProcDescreption);
					mwmInfoBean.SetRequestingPhysician(setRequestingPhysician);
					mwmInfoBean.SetRequestingService(setRequestingService);
					mwmInfoBean.SetAccessionNumber(setAccessionNumber);
					mwmInfoBean.SetInstitutionName(setInstitutionName);
					mwmInfoBean.SetInstitutionAddress(setInstitutionAddress);
					mwmInfoBean.SetPatientNameRoma(setPatientNameRoma);
					mwmInfoBean.SetPatientNameKanji(setPatientNameKanji);
					mwmInfoBean.SetPatientNameKana(setPatientNameKana);
					mwmInfoBean.SetPatientID(setPatientID);
					mwmInfoBean.SetPatientBirthDate(setPatientBirthDate);
					mwmInfoBean.SetPatientSex(setPatientSex);
					mwmInfoBean.SetPatientWeight(setPatientWeight);
					mwmInfoBean.SetSchActionCodes(setActionCodes);
					mwmInfoBean.SetSchActionVersion(setActionVersion);
					mwmInfoBean.SetSchActionDesignator(setActionDesignator);
					mwmInfoBean.SetSchActionMeanings(setActionMeanings);
					mwmInfoBean.SetRefStudyClassUID(setRefStudyClassUID);
					mwmInfoBean.SetRefStudyInstanceUID(setRefStudyInstanceUID);
					mwmInfoBean.SetRefPhysicianName(setRefPhysicianName);
					mwmInfoBean.SetPatientResidence(setPatientResidence);
					mwmInfoBean.SetPatientSize(setPatientSize);
					mwmInfoBean.SetPatientComments(setPatientComments);
					mwmInfoBean.SetReqProcedureCodes(setReqProcedureCodes);
					mwmInfoBean.SetReqProcedureDesignator(setReqProcedureDesignator);
					mwmInfoBean.SetReqProcedureVersion(setReqProcedureVersion);
					mwmInfoBean.SetReqProcedureMeanings(setReqProcedureMeanings);
					mwmInfoBean.SetNumberOfCopies(setNumberOfCopies);
					//
					insMWMInfoList.add(mwmInfoBean);

				}

				// MWM用DB接続情報の取得
				ConnectionInfoBean conBean = Configuration.GetInstance().GetMWMConnectionInfo();
				if (conBean == null)
				{
					//取得失敗エラー
					String message = "MWM用のDB接続情報が設定されていません。";
					logger.warn(message);
					//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
					//return false;
					throw new Exception(message);
				}


				//削除モードの準備
				String deleteMode = MwmMasterUtil.DELETE_MODE_ACCNO;


				if (kensaKikiList.size() > 1)
				{
					if (lp != 0)
					{
						//2装置同時MWM登録の場合、削除は1回のみとする
						deleteMode = MwmMasterUtil.DELETE_MODE_NONE;
					}
				}

				//MWM情報を登録する
				if (insMWMInfoList != null && insMWMInfoList.size() > 0)
				{
					boolean retBool = Configuration.GetInstance().GetCoreController().RegisterWorkListInfo(
						insMWMInfoList,
						conBean,
						deleteMode);
					if (!retBool)
					{
						String msg = "WorkListInfoの登録に失敗しました。";
						logger.error(msg);
						//Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
						//return false;
						throw new Exception(msg);
					}

					// ソケット通信を行う(MWMParam8が設定済の場合)
					if (targetKikiID.length() > 0)
					{
						String aeTitle = mUtil.FindData(mstKensakiki, MasterUtil.RIS_AE_TITLE_MWM, MasterUtil.RIS_KENSAKIKI_ID, targetKikiID);
						String connectStr = mUtil.FindData(mstKensakiki, MasterUtil.RIS_MWMPARAM + "08", MasterUtil.RIS_KENSAKIKI_ID, targetKikiID);
						if (connectStr.length() > 0)
						{
							//ソケット通信を行う
							MWMSendMessage(aeTitle, connectStr);
						}
					}

				} else {
					String msg = "MWM登録データがありませんでした。";
					logger.error(msg);
					//Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
					//return false;
					throw new Exception(msg);
				}
			}
		}
		catch (Exception ex)
		{
			String msg = "MWM登録が失敗しました。\n" + ex.getMessage();
			//Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
			logger.fatal(msg);
			logger.fatal(ex);
			throw new Exception(msg);
		}

		return true;
	}

	/// <summary>
	/// MWM-検査機器IDを元に機器情報を取得する
	/// </summary>
	/// <param name="kikiDt">検査機器情報</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>検査機器情報</returns>
	public static KensaKikiBean GetMWMKensaKikiBean(DataTable kikiDt, String kikiID)
	{
		KensaKikiBean kikiBean = new KensaKikiBean();

		//検査機器情報のループ
		for (int i=0; i<kikiDt.getRowCount(); i++)
		{
			DataRow row = kikiDt.getRows().get(i);

			//検査機器IDと一致する行を探す
			if (kikiID.equals(row.get(MasterUtil.RIS_KENSAKIKI_ID).toString()))
			{
				kikiBean.SetKensaKikiBean(row);
				break;
			}
		}

		return kikiBean;
	}

	/// <summary>
	/// MWM-検査機器情報のチェックを行う
	/// </summary>
	/// <param name="bean">検査機器情報</param>
	/// <returns>登録可否フラグ</returns>
	private static boolean CheckMWMKikiBean(KensaKikiBean bean) throws Exception
	{
		boolean retBool = false;

		if (bean != null)
		{
			// 検査機器IDのチェック
			if (bean.GetKensaKikiID().length() <= 0)
			{
				//空はNG
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMInfoKikiIDException_MessageString);
				message = message + " " + bean.GetKensaKikiName();
				logger.warn(message);
				//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
				//return retBool;
				throw new Exception(message);
			}


			// MWMTypeのチェック(今回不要？)
			//if (bean.GetMwmType().Length > 0)
			//{
			//    //下記値以外はエラーとする
			//    //
			//    // 0	CR以外 標準
			//    // 1	MR
			//    // 2	CR以外 標準2
			//    // 500	CR標準1
			//    // 501	CR標準2
			//    // 550	FCR標準
			//    // 600	KOCR標準
			//    //
			//    if (bean.GetMwmType() == CommonString.MWM_TYPE_CTS1	||
			//        bean.GetMwmType() == CommonString.MWM_TYPE_PMR1	||
			//        bean.GetMwmType() == CommonString.MWM_TYPE_CTS2	||
			//        bean.GetMwmType() == CommonString.MWM_TYPE_CRS1	||
			//        bean.GetMwmType() == CommonString.MWM_TYPE_CRS2	||
			//        bean.GetMwmType() == CommonString.MWM_TYPE_FCR1	||
			//        bean.GetMwmType() == CommonString.MWM_TYPE_KCR1)
			//    {
			//        //正常
			//    }
			//    else
			//    {
			//        //対象外の値はNG
			//        String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMInfoMWMTypeErrorException_MessageString);
			//        message = string.Format(message, bean.GetKensaKikiName());
			//        Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
			//        return retBool;
			//    }
			//}
			//else
			//{
			//    //空はNG
			//    String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMInfoMWMTypeException_MessageString);
			//    message = string.Format(message, bean.GetKensaKikiName());
			//    Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
			//    return retBool;
			//}


			// AETitleのチェック
			if (bean.GetAETitle().length() > 0)
			{
				if (bean.GetAETitle().length() > 16)
				{
					//16文字以上はNG
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMInfoAeException_MessageString);
					message = message + " " + bean.GetKensaKikiName();
					//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
					logger.warn(message);
					//return retBool;
					throw new Exception(message);
				}
			}
			else
			{
				//空はNG
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMInfoAeTitleMWMException_MessageString);
				message = message + " " + bean.GetKensaKikiName();
				//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
				logger.warn(message);
				//return retBool;
				throw new Exception(message);
			}


			// ModalityTypeのチェック(今回不要？)
			//if (bean.GetModality().Length > 0)
			//{
			//	//正常
			//}
			//else
			//{
			//	//空はNG
			//	String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.registerMWMInfoModalityTypeException_MessageString);
			//	message = string.Format(message, bean.GetKensaKikiName());
			//	Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
			//	return false;
			//}


			//trueを設定
			retBool = true;
		}

		return retBool;
	}

	/// <summary>
	/// MWM-患者情報のチェックと設定を行う
	/// </summary>
	/// <param name="bean">患者情報</param>
	/// <returns>MWM情報</returns>
	public static MwmPatientInfoBean CheckConvertMWMpatientBean(PatientInfoBean bean)
	{
		MwmPatientInfoBean retBean = new MwmPatientInfoBean();

		//
		//RQ_WORK_TYPE用の場合の処理になっています
		//

		//Beanへ設定
		retBean.SetKanjaID(bean.GetKanjaID());
		retBean.SetKanjiSimei(bean.GetKanjiSimei());
		retBean.SetKanaSimei(bean.GetKanaSimei());
		retBean.SetRomaSimei(bean.GetRomaSimei());
		retBean.SetSex(bean.GetSex());
		retBean.SetWeight(bean.GetWeight());
		retBean.SetTall(bean.GetTall());
		retBean.SetBirthday(bean.GetBirthday());

		//患者ローマ氏名
		//128バイト以上の場合はカットする
		if (bean.GetRomaSimei().length() > 128)
		{
			//警告メッセージ表示
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMPatientNameRomaException_MessageString);
			String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
			bean.SetRomaSimei(bean.GetRomaSimei().substring(0, 128));
			logger.warn(message
				+ "128" + message2);
		}

		//患者漢字氏名
		//128バイト以上の場合はカットする
		if (bean.GetKanjiSimei().length() > 64)
		{
			//警告メッセージ表示
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMPatientNameKanjiException_MessageString);
			String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
			bean.SetKanjiSimei(bean.GetKanjiSimei().substring(0, 64));
			logger.warn(message
				+ "64" + message2);
		}

		//患者カナ氏名
		//128バイト以上の場合はカットする
		if (bean.GetKanaSimei().length() > 64)
		{
			//警告メッセージ表示
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMPatientNameKanaException_MessageString);
			String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
			bean.SetKanaSimei(bean.GetKanaSimei().substring(0, 64));
			logger.warn(message
				+ "64" + message2);
		}

		//患者ID
		//16バイト以上の場合はカットする
		if (bean.GetKanjaID().length() > 16)
		{
			//2007.5.17 メッセージを外部ファイル定義に変更
			String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.questionMWMPatientIDWarning_MessageString);

			bean.SetKanjaID(bean.GetKanjaID().substring(0, 16));
			String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMPatientIDWarning_MessageString);
			String message3 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
			logger.warn(message2 + "16" + message3);

			/* 一ノ瀬保留
			if (Configuration.GetInstance().GetWindowController().ShowMessageBox_QuestionYesNo(
				message) == DialogResult.Yes)
			{
				bean.SetKanjaID(bean.GetKanjaID().Substring(0, 16));
				//警告メッセージ表示
				String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMPatientIDWarning_MessageString);
				String message3 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
				logger.Warn(message2
					+ "16" + message3);
			}
			else
			{
				//警告メッセージ表示
				String message3 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.cancelMWMPatientIDWarning_MessageString);
				String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
				logger.Warn(message3
					+ "16" + message2);

				//処理キャンセル
				retBean = null;
			}
			*/
		}

		return retBean;
	}

	/*
	// 未使用
	///// <summary>
	///// MMWM-オーダ情報のチェックと設定を行う
	///// </summary>
	///// <param name="bean">オーダ情報</param>
	///// <returns>MWM情報</returns>
	//private static MwmOrderInfoBean CheckConvertMwmOrderInfoBean(OrderInfoBean bean)
	//{
	//    MwmOrderInfoBean retBean = new MwmOrderInfoBean();
	//    MasterUtil mUtil = new MasterUtil();

	//    //Beanへ設定
	//    retBean.SetOrderNo(bean.GetOrderNo());
	//    retBean.SetStudyInstanceUID(bean.GetStudyInstanceUID());
	//    retBean.SetAccessionNo(bean.GetAccessionNo());

	//    //依頼医ID→依頼医英語名称を取得
	//    if (bean.GetIraiDoctorNo().Length > 0)
	//    {
	//        String name = mUtil.FindData(
	//            Configuration.GetInstance().GetRRisSectionDoctorMaster(),
	//            MasterUtil.RIS_DOCTOR_ENGLISH_NAME,
	//            MasterUtil.RIS_DOCTOR_ID,
	//            bean.GetIraiDoctorNo());
	//        retBean.SetIraiDoctorEnglishName(name);
	//    }

	//    //依頼科ID→依頼科英語名称を取得
	//    if (bean.GetIraiSectionID().Length > 0)
	//    {
	//        String name = mUtil.FindData(
	//            Configuration.GetInstance().GetRRisSectionMaster(),
	//            MasterUtil.RIS_SECTION_ENGLISHNAME,
	//            MasterUtil.RIS_SECTION_ID,
	//            bean.GetIraiSectionID());
	//        retBean.SetSectionEnglishName(name);
	//    }

	//    //依頼医英語名称
	//    //80バイト以上の場合はカットする
	//    if (retBean.GetIraiDoctorEnglishName().Length > 80)
	//    {
	//        //警告メッセージ表示
	//        String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMDoctorEnglishNameWarning_MessageString);
	//        String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
	//        retBean.SetIraiDoctorEnglishName(retBean.GetIraiDoctorEnglishName().Substring(0, 80));
	//        logger.Warn(message
	//            + "80" + message2);
	//    }

	//    //依頼科英語名称
	//    //80バイト以上の場合はカットする
	//    if (retBean.GetSectionEnglishName().Length > 80)
	//    {
	//        //警告メッセージ表示
	//        String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMSectionEnglishNameWarning_MessageString);
	//        String message2 = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkMWMEndException_MessageString);
	//        retBean.SetSectionEnglishName(retBean.GetSectionEnglishName().Substring(0, 80));
	//        logger.Warn(message
	//            + "80" + message2);
	//    }

	//    //オーダ部位情報設定
	//    retBean.ReconstructOrderBuiList(bean.GetOrderBuiList());

	//    return retBean;
	//}
	*/

	/// <summary>
	/// MWM-撮影情報リストを取得する
	/// </summary>
	/// <param name="hash">各キーリストのHash</param>
	/// <returns></returns>
	private static DataTable GetMWMSatueiData(Hashtable hash, Connection con)
	{
		DataTable retDt = null;

		//対象オーダのループ
		String satueiMenuIDs = "";
		ArrayList addIDList = new ArrayList();
		Enumeration e1 = hash.keys();

		while (e1.hasMoreElements()){
			String risIDStr = (String)e1.nextElement();
			ArrayList keysList = (ArrayList)hash.get(risIDStr);
			if (keysList != null)
			{
				//撮影IDリストの取得
				ArrayList satueiIDList = (ArrayList)keysList.get(MWMOrderKeys.SatueiID.getId());

				//検索用の撮影ID文字列を作成
				for (int i=0; i<satueiIDList.size(); i++)
				{
					String menuID = satueiIDList.get(i).toString();
					if (!addIDList.contains(menuID))
					{

						if (satueiMenuIDs.length() <= 0)
						{
							satueiMenuIDs = menuID;
						}
						else
						{
							satueiMenuIDs += "," + menuID;
						}
						addIDList.add(menuID);
					}
				}
			}
		}

		if (addIDList.size() > 0)
		{
			//撮影情報の検索
			RequestParameter rParam = new RequestParameter();
			rParam.SetSatueiMenuIDs(satueiMenuIDs);
			retDt = Configuration.GetInstance().GetCoreController().GetRisSatueiMenuDataTable(rParam, con);
		}

		return retDt;
	}

	/// <summary>
	/// MWM-撮影コードを取得する
	/// </summary>
	/// <param name="dt">撮影情報</param>
	/// <param name="satueiID">撮影ID</param>
	/// <param name="kikiType">機器タイプ</param>
	/// <returns></returns>
	private static String GetMWMSatueiCode(DataTable dt, String satueiID, String kikiType)
	{
		String retStr = "";

		if (dt != null)
		{
			MasterUtil mUtil = new MasterUtil();
			DataRow row = mUtil.FindDataRow(dt, MasterUtil.RIS_SATUEIMENU_ID, satueiID);

			try
			{
				if (kikiType.length() > 0)
				{
					int kikitypeInt = Integer.parseInt(kikiType);

					if (kikitypeInt > 0 && kikitypeInt < 11)
					{
						String noStr = String.format("%1$02d", kikitypeInt);
						retStr = row.get(MasterUtil.RIS_SATUEI_CODE + noStr).toString();
					}
				}
			}
			catch(Exception ex)
			{
				logger.fatal(ex);
			}
		}

		return retStr;
	}

	/// <summary>
	/// MWM-患者ローマ字チェック
	/// </summary>
	/// <param name="patientBean">患者情報</param>
	/// <returns></returns>
	public static boolean CheckMWMPatientRoma(PatientInfoBean patientBean)
	{
		boolean retBool = false;

		KanaRomaConvert kanaRomaConv = new KanaRomaConvert(Configuration.GetInstance().getKanaromatext());
		if (patientBean.GetRomaSimei().length() <= 0 &&
			patientBean.GetKanaSimei().length() >  0)
		{
			//カナを元にローマ字を更新する
			patientBean.SetRomaSimei(kanaRomaConv.GetRomaConvert(patientBean.GetKanaSimei()));
		}

		return retBool;
	}

	/// <summary>
	/// MWM-患者名のスペースを^へ変換する
	/// </summary>
	/// <param name="patName">患者名</param>
	/// <returns></returns>
	private static String ConvMWMPatientName(String patName)
	{
		String retStr = patName;

		retStr = retStr.replace(" ",  "^");
		retStr = retStr.replace("　", "^");

		return retStr;
	}

	/// <summary>
	/// MWM-ソケット通信を行う
	/// </summary>
	/// <param name="aeTitle">AEタイトル</param>
	/// <param name="connectStr">接続用文字列</param>
	/// <returns></returns>
	private static boolean MWMSendMessage(String aeTitle, String connectStr) throws Exception
	{
		boolean retBool = false;
		//MsgDialogForm msgForm = null;
		try
		{
			//ソケット通信中メッセージ表示
			/* 一ノ瀬保留
			String msgStr = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.MWMSendMessage_MessageString);
			MsgItem msgItem = new MsgItem();
			msgItem.Msg = msgStr;
			msgForm = new MsgDialogForm(msgItem);
			msgForm.SetMWMFormat();
			msgForm.Show();
			msgForm.Refresh();
			*/

			//IPアドレス、ポート番号、タイムアウト時間の準備
			String ip		= connectStr.substring(0, connectStr.indexOf(":"));
			int    port		= Integer.parseInt(connectStr.substring(connectStr.indexOf(":")+1));
			int    timeout	= Configuration.GetInstance().getMwmtimeout();

			SocketClient socket = new SocketClient(ip, port);

			//送信を行う
			String responseData = socket.sendMessage(aeTitle, timeout);
			if (responseData != null && responseData.length() > 0)
			{
				if (CommonString.SUCCESS_MESSAGE.equals(responseData))
				{
					//成功
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.workListSendSuccess_MessageString);
					logger.info(msg + " [" +  CommonString.SUCCESS_MESSAGE + "]");
					// 2010.10.07 Del K.Shinohara Start
					// 成功時はメッセージ表示を行わない
					//Configuration.GetInstance().GetWindowController().ShowMessageBox_Information(msg);
					// 2010.10.07 Del K.Shinohara End
					retBool = true;
				}
				else
				{
					//失敗(OK以外)
					String msg = "患者情報の送信に失敗しました。";
					String msg2 = "Server Response = ";
					logger.fatal(msg + " [" + CommonString.ERROR_MESSAGE + "]");
					//Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg + "\r\n\r\n" + msg2 + responseData);
					throw new Exception(msg + "：" + msg2 + responseData);
				}
			}
			else
			{
				//失敗(レスポンス無)
				String msg = "患者情報の送信に失敗しました。";
				logger.fatal(msg + " [" + CommonString.ERROR_MESSAGE + "]");
				//Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
				throw new Exception(msg);
			}
		}
		catch (Exception ex)
		{
			String msg = "トリガー送信に失敗しました。再度ご確認ください。";
			//Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
			logger.fatal(ex);
			throw new Exception(msg);
		}

		return retBool;
	}

	// 2010.07.30 Add Y.Shibata Start
	/// <summary>
	/// 仮想検査機器IDを元に、実施機器情報を取得する
	/// </summary>
	/// <param name="kikiID">仮想検査機器ＩＤ</param>
	/// <returns>検査機器ＩＤ</returns>
	public static ArrayList GetMultiKensaKikiIDList(DataTable kikiDt, String kikiID)
	{
		ArrayList retDt = new ArrayList();

		//検査機器情報のループ
		for (int i=0; i<kikiDt.getRowCount(); i++)
		{
			DataRow row = kikiDt.getRows().get(i);

			//検査機器IDと一致する行を探す
			if (kikiID.equals(row.get(MasterUtil.RIS_KENSAKIKI_ID).toString()))
			{
				if (CommonString.KENSAKIKI_MODE_MULTI.equals(row.get(MasterUtil.RIS_KENSAKIKI_MODE).toString()))
                {
                    //仮想機器モード
                    String str = "";

                    //実施装置機器1～5を取得
                    str = row.get(MasterUtil.RIS_REF_KENSAKIKI_ID_1).toString();
                    if (str != "")
                    {
                        retDt.add(str);
                    }
                    str = row.get(MasterUtil.RIS_REF_KENSAKIKI_ID_2).toString();
                    if (str != "")
                    {
                        retDt.add(str);
                    }
                    str = row.get(MasterUtil.RIS_REF_KENSAKIKI_ID_3).toString();
                    if (str != "")
                    {
                        retDt.add(str);
                    }
                    str = row.get(MasterUtil.RIS_REF_KENSAKIKI_ID_4).toString();
                    if (str != "")
                    {
                        retDt.add(str);
                    }
                    str = row.get(MasterUtil.RIS_REF_KENSAKIKI_ID_5).toString();
                    if (str != "")
                    {
                        retDt.add(str);
                    }
                }
                else
                {
                    //実機器モード
                    retDt.add(kikiID);
                }

				break;
			}
		}

		return retDt;
	}
	// 2010.07.30 Add Y.Shibata End

	// 2010.08.04 Add K.Shinohara Start
	/// <summary>
	/// 対象機器が、受付時MWM登録対象かチェックする
	/// </summary>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>True:受付時MWM登録対象</returns>
	public static boolean CheckReceiptMWMFlag(DataTable kikiDt, String kikiID)
	{
		boolean retFlg = false;

		//検査機器情報のループ
		for (int i=0; i<kikiDt.getRowCount(); i++)
		{
			DataRow row = kikiDt.getRows().get(i);

			//検査機器IDと一致する行を探す
			if (kikiID.equals(row.get(MasterUtil.RIS_KENSAKIKI_ID).toString()))
			{
				if (CommonString.FLG_ON.equals(row.get(MasterUtil.RIS_RECEIPTMWMFLAG).toString()))
				{
					retFlg = true;
				}

				break;
			}
		}

		return retFlg;
	}
	// 2010.08.04 Add K.Shinohara End

	// 2010.08.04 Add K.Shinohara Start
	/// <summary>
	/// MWMTypeが登録されているかチェックする
	/// </summary>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns>True:MWMType設定あり</returns>
	public static boolean CheckMWMType(DataTable kikiDt, String kikiID)
	{
		boolean retFlg = false;

		//検査機器情報のループ
		for (int i=0; i<kikiDt.getRowCount(); i++)
		{
			DataRow row = kikiDt.getRows().get(i);

			//検査機器IDと一致する行を探す
			if (kikiID.equals(row.get(MasterUtil.RIS_KENSAKIKI_ID).toString()))
			{
				if (!StringUtils.isEmpty(row.get(MasterUtil.RIS_MWMTYPE).toString()))
				{
					retFlg = true;
				}

				break;
			}
		}

		return retFlg;
	}
	// 2010.08.04 Add K.Shinohara End

	/*
	/// <summary>
	/// 画面ABの同期を行う
	/// </summary>
	/// <param name="formA">設定するForm</param>
	/// <param name="formB">元となるForm</param>
	public static void SyncForm(Form formA, Form formB)
	{
		if (formA != null && formB != null)
		{
			formA.SuspendLayout();
			formA.Size = formB.Size;
			for (int i=0; i<formA.Controls.Count; i++)
			{
				Control ctlA = formA.Controls[i];

				for (int j=0; j<formB.Controls.Count; j++)
				{
					Control ctlB = formB.Controls[j];

					//画面ABの同期を行う(再帰)
					SyncFormSub(ctlA, ctlB);
				}
			}
			formA.ResumeLayout();
		}
	}

	/// <summary>
	/// UserControlABの同期を行う
	/// </summary>
	/// <param name="formA">設定するUserControl</param>
	/// <param name="formB">元となるUserControl</param>
	public static void SyncUserControl(UserControl userCtlA, UserControl userCtlB)
	{
		if (userCtlA != null && userCtlB != null)
		{
			userCtlA.SuspendLayout();
			userCtlA.Size = userCtlB.Size;
			for (int i=0; i<userCtlA.Controls.Count; i++)
			{
				Control ctlA = userCtlA.Controls[i];

				for (int j=0; j<userCtlB.Controls.Count; j++)
				{
					Control ctlB = userCtlB.Controls[j];

					//画面ABの同期を行う(再帰)
					SyncFormSub(ctlA, ctlB);
				}
			}
			userCtlA.ResumeLayout();
		}
	}

	/// <summary>
	/// 画面ABの同期を行う(再帰)
	/// </summary>
	/// <param name="ctlA">設定するControl</param>
	/// <param name="ctlB">元となるControl</param>
	public static void SyncFormSub(Control ctlA, Control ctlB)
	{
		//自Controlの設定
		if (ctlA.Name == ctlB.Name)
		{
			ctlA.Location	= ctlB.Location;
			ctlA.Size		= ctlB.Size;
			ctlA.Font		= ctlB.Font;
		}

		//子のControlの設定
		for (int i=0; i<ctlA.Controls.Count; i++)
		{
			Control ctlAA = ctlA.Controls[i];

			for (int j=0; j<ctlB.Controls.Count; j++)
			{
				Control ctlBB = ctlB.Controls[j];

				if (ctlAA.Name == ctlBB.Name)
				{
					ctlAA.Location	= ctlBB.Location;
					ctlAA.Size		= ctlBB.Size;

					//画面ABの同期を行う(再帰)
					SyncFormSub(ctlAA, ctlBB);
				}
			}
		}
	}

	/// <summary>
	/// オーダ情報テーブル情報を取得する
	/// </summary>
	/// <returns></returns>
	public static ArrayList GetOrderDetailInfoData()
	{
		ArrayList retList = new ArrayList();
		try
		{
			//テーブル数を取得
			int count = Configuration.GetInstance().GetDynamicPropertyInt("OrderDetailList.ItemCount.Value");

			//テーブル数分ループする
			for (int i = 0; i < count; i++)
			{
				int index = i + 1;
				String key = "OrderDetailList.Item{0:D2}.Value";
				key = string.Format(key, index);
				string[] value = Configuration.GetInstance().GetDynamicPropertyString(key).Split(',');
				if (value != null && value.Length <= 2)
				{
					MasterItem item = new MasterItem(
						value[0],
						value[1]);
					retList.add(item);
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retList;
	}

	/// <summary>
	/// 検像サーバに接続できるかチェックを行う
	/// </summary>
	/// <returns>検像処理前に行う事</returns>
	public static bool CheckKenzouServer()
	{
		bool retBool = true;
		ViewerController viewer = new ViewerController();
		//接続チェック
		Ping png = new Ping();

		PingReply pingReply =  png.Send(viewer.GetKenzouIPAddress());

		if (!(pingReply.Status == IPStatus.Success))
		{
			retBool = false;
			String errMsg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.viewerIPAddressException_MessageString);
			errMsg = string.Format(errMsg, viewer.GetKenzouIPAddress());
			logger.Warn(errMsg);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(errMsg);
		}
		return retBool;
	}

	/// <summary>
	/// 検像用クリア処理
	/// </summary>
	public static void CleanupKenzouPort()
	{
		ViewerController viewer = null;
		try
		{
			//Viewer生成
			viewer = ViewerController.GetInstance();

			int retSend = -1;
			int count = 1;
			while(true)
			{
				//受信
				retSend = viewer.ViewerSyncRecv();
				if (retSend == -1 || retSend == 0)
				{
					break;
				}

				count += 1;

				//通常ありえないが、Portが暴走？した場合処理を抜ける
				if (count > 10)
				{
					String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleCleanupKenzouPort_MessageString);
					logger.fatal(msg);
					break;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		finally
		{
			viewer = null;
		}
	}

	/// <summary>
	/// 撮影系MPPS取得画面位置テキストファイルを読み込む
	/// </summary>
	public static Point ReadMppsPhotoFormPosText()
	{
		Point retPos = new Point(0,250);
		try
		{
			String posStr = "";

			//パスを準備
			String path		= Configuration.GetInstance().GetDynamicPropertyString("DataListWidthTextFile.Path");
			String pathStr	= Application.StartupPath + @"\..\" + path + "MppsPhotoForm.txt";

			//ファイル存在チェック
			if (File.Exists(pathStr))
			{
				//読み込み
				posStr = File.ReadAllText(pathStr, Encoding.GetEncoding(CommonString.ENCODE_DEF));
			}

			if (posStr.Length > 0)
			{

				//設定する
				string[] posStrs = posStr.Split(',');

				if (posStr.Length >= 2)
				{
					retPos.X = TextUtil.ParseStringToInt(posStrs[0]);
					retPos.Y = TextUtil.ParseStringToInt(posStrs[1]);
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retPos;
	}

	/// <summary>
	/// 撮影系MPPS取得画面位置テキストファイルを書き込む
	/// </summary>
	/// <param name="pos">ポイント</param>
	public static void WriteMppsPhotoFormPosText(Point pos)
	{
		try
		{
			//保存するテキストを準備する
			if (pos != null)
			{
				String saveStr = pos.X.toString() + "," + pos.Y.toString();

				if (saveStr.Length > 0)
				{
					//パスを準備
					String path		= Configuration.GetInstance().GetDynamicPropertyString("DataListWidthTextFile.Path");
					String pathStr	= Application.StartupPath + @"\..\" + path;

					//フォルダ存在チェック
					if (!Directory.Exists(pathStr))
					{
						//フォルダ作成
						Directory.CreateDirectory(pathStr);
					}

					//ファイル名追加
					pathStr += "MppsPhotoForm.txt";

					File.WriteAllText(pathStr, saveStr, Encoding.GetEncoding(CommonString.ENCODE_DEF));
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}

	/// <summary>
	/// オーダ一覧の行高を調整する
	/// </summary>
	/// <param name="view"></param>
	public static void SettingOrderListRowHeight(DataGridView view)
	{
		try
		{
			int heightDefInt = view.RowTemplate.Height;
			for (int i=0; i<view.Rows.Count; i++)
			{
				//部位数
				int buiCount = TextUtil.ParseStringToInt(((DataRowView)view.Rows[i].DataBoundItem)[RisBuiSummaryView.BUI_COUNT_COLUMN].toString());
				if (buiCount <= 0)
				{
					buiCount = 1;
				}

				//部位数×行高を設定する
				// 2011.09.20 Mod H.Orikasa Start
				if (buiCount <= 1)
				{
					// １部位の場合:通常計算
					view.Rows[i].Height = heightDefInt * buiCount;
				}
				else
				{
					// Ｎ部位の場合:Fontの高さで行高を計算した後、余白分を加算する
					int defHeight = (int)Math.Round((decimal)view.RowTemplate.DefaultCellStyle.Font.GetHeight());
					view.Rows[i].Height = defHeight * buiCount + heightDefInt - defHeight;
				}
				//view.Rows[i].Height = heightDefInt * buiCount;
				// 2011.09.20 Mod H.Orikasa End
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}

	//2011.07.27 Add NSK_S.Imai Start NML-CAT1-008
	/// <summary>
	/// オーダ一覧の文字配置（縦位置）を上→中央または中央→上に切り替える
	/// </summary>
	/// <param name="view">対象のDataGridView</param>
	/// <param name="buiChecked">部位展開表示チェックボックスのチェック状態</param>
	public static void ChangeOrderListTextAlignment(DataGridView view, bool buiChecked)
	{
		try
		{
			for (int i = 0; i<view.Columns.Count; i++)
			{
				if (buiChecked)
				{
					if (view.Columns[i].DefaultCellStyle.Alignment == DataGridViewContentAlignment.MiddleCenter)
					{
						view.Columns[i].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopCenter;
					}
					else if (view.Columns[i].DefaultCellStyle.Alignment == DataGridViewContentAlignment.MiddleLeft)
					{
						view.Columns[i].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
					}
					else if (view.Columns[i].DefaultCellStyle.Alignment == DataGridViewContentAlignment.MiddleRight)
					{
						view.Columns[i].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;
					}
				}
				else
				{
					if (view.Columns[i].DefaultCellStyle.Alignment == DataGridViewContentAlignment.TopCenter)
					{
						view.Columns[i].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleCenter;
					}
					else if (view.Columns[i].DefaultCellStyle.Alignment == DataGridViewContentAlignment.TopLeft)
					{
						view.Columns[i].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleLeft;
					}
					else if (view.Columns[i].DefaultCellStyle.Alignment == DataGridViewContentAlignment.TopRight)
					{
						view.Columns[i].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}
	//2011.07.27 Add NSK_S.Imai End

	/// <summary>
	/// 一覧文字検索
	/// </summary>
	/// <param name="keyCB">キーComboBox</param>
	/// <param name="valueTB">値TextBox</param>
	/// <param name="view">各画面一覧</param>
	/// <param name="iMode">ﾓｰﾄﾞﾓｰﾄﾞ 0:検索 1:↓ 2:↑</param>
	/// <param name="idStr">ID列カラム名</param>
	/// <param name="nmStr">名称列カラム名</param>
	/// <param name="rnmStr">略名称列カラム名</param>
	/// <returns></returns>
	public static String CharSearch(NormalComboBox keyCB, NormalTextBox valueTB, NormalOrderListDataGridView view, int iMode, String idStr, String nmStr, String rnmStr)
	{
		String retStr = "";
		try
		{
			// IDをインデックスに変換
			int idIndex = GetColumnIndex(view, idStr);

			bool checkFlg = true;
			// 検索種別チェック
			if (keyCB.SelectedIndex <= 0)
			{
				keyCB.Focus();
				checkFlg = false;
			}

			// 検索文字列チェック
			if (valueTB.Text == string.Empty)
			{
				valueTB.Focus();
				checkFlg = false;
			}
			if (!checkFlg)
			{
				// メッセージ表示
				String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.searchWhere_MessageString);
				Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(msg);
				return retStr;
			}

			int iStartIndex = 0;
			bool bUpMode = false;

			//選択行Index
			int index = -1;
			if (view.SelectedRows.Count > 0)
			{
				index = view.SelectedRows[0].Index;
			}
			else
			{
				index++;
			}
			iStartIndex = index;

			if (iMode == 1)
			{
				// [↓]ボタン
				if (iStartIndex < (view.Rows.Count - 1))
				{
					iStartIndex++;
				}
				else
				{
					iStartIndex = 0;
				}
			}
			else if (iMode == 2)
			{
				// [↑]ボタン
				if (iStartIndex == 0)
				{
					iStartIndex = view.Rows.Count - 1;
				}
				else
				{
					iStartIndex--;
				}

				bUpMode = true;
			}

			//IDor名称orPHS
			int checkIndex = -1;
			if (keyCB.Text == CommonString.ITEMSEARCH_KTYPE_ID)
			{
				checkIndex = idIndex;
			}
			else if (keyCB.Text == CommonString.ITEMSEARCH_KTYPE_NAME)
			{
				checkIndex = GetColumnIndex(view, nmStr);
			}
			else if (keyCB.Text == CommonString.ITEMSEARCH_KTYPE_RNAME)
			{
				checkIndex = GetColumnIndex(view, rnmStr);
			}

			int iRet = CharSearchSub(view,
									 checkIndex,
									 valueTB.Text,
									 iStartIndex,
									 bUpMode);

			if (iRet != int.MinValue)
			{
				if (view[idIndex, iRet].Value != null)
				{
					retStr = view[idIndex, iRet].Value.toString();
				}
			}
			else
			{
				// メッセージ表示
				String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.searchNotFound_MessageString);
				Configuration.GetInstance().GetWindowController().ShowMessageBox_Information(msg);
			}
		}
		catch (Exception ex)
		{
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString);
			Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(msg);
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(msg) + "\r\n" + ex);
		}
		return retStr;
	}

	/// <summary>
	/// 一覧文字検索
	/// </summary>
	/// <param name="view">各画面一覧</param>
	/// <param name="checkColumn">検索カラムIndex</param>
	/// <param name="checkData">検索文字列</param>
	/// <param name="startRow">検索開始位置</param>
	/// <param name="bUpFlag">True: 上方向検索 False: 下方向検索</param>
	/// <returns>検索結果Index</returns>
	public static int CharSearchSub(DataGridView view, int checkIndex, String checkData, int startRow, bool bUpFlag)
	{
		String strSearchKey = checkData.ToLower(CultureInfo.InvariantCulture);
		String strCurrenValue;
		bool bFoundFlag = false;
		int i;
		int iFirst = startRow;
		int iLast;
		int iRet = int.MinValue;

		if (view.RowCount == 0)
		{
			return iRet;
		}


		if (bUpFlag)
		{
			iLast = 0;

			for (i = iFirst; i >= iLast; i--)
			{
				if (view[checkIndex, i].Value != null)
				{
					strCurrenValue = view[checkIndex, i].Value.toString().ToLower(CultureInfo.InvariantCulture);
					if (strCurrenValue.Contains(strSearchKey))
					{
						bFoundFlag = true;
						break;
					}
				}
			}

			if (!bFoundFlag)
			{
				// 端末行から選択行まで検索
				for (i = view.RowCount - 1; i > iFirst; i--)
				{
					if (view[checkIndex, i].Value != null)
					{
						strCurrenValue = view[checkIndex, i].Value.toString().ToLower(CultureInfo.InvariantCulture);
						if (strCurrenValue.Contains(strSearchKey))
						{
							bFoundFlag = true;
							break;
						}
					}
				}
			}
		}
		else
		{
			iLast = view.RowCount - 1;

			for (i = iFirst; i <= iLast; i++)
			{
				if (view[checkIndex, i].Value != null)
				{
					strCurrenValue = view[checkIndex, i].Value.toString().ToLower(CultureInfo.InvariantCulture);
					if (strCurrenValue.Contains(strSearchKey))
					{
						bFoundFlag = true;
						break;
					}
				}
			}

			if (!bFoundFlag)
			{
				// 先頭行から選択行まで検索
				for (i = 0; i < iFirst; i++)
				{
					if (view[checkIndex, i].Value != null)
					{
						strCurrenValue = view[checkIndex, i].Value.toString().ToLower(CultureInfo.InvariantCulture);
						if (strCurrenValue.Contains(strSearchKey))
						{
							bFoundFlag = true;
							break;
						}
					}
				}
			}
		}

		if (bFoundFlag)
		{
			iRet = i;
		}

		return iRet;
	}

	/// <summary>
	/// 指定のColumnNameのインデックスを取得
	/// </summary>
	/// <param name="view">検索対象DataGridView</param>
	/// <param name="columnName">指定ColumnName</param>
	/// <returns>インデックス</returns>
	public static int GetColumnIndex(NormalOrderListDataGridView view, String columnName)
	{
		int retIndex = -1;
		if (!string.IsNullOrEmpty(columnName))
		{
			for (int i = 0; i < view.Columns.Count; i++)
			{
				if (view.Columns[i].DataPropertyName.Equals(columnName))
				{
					retIndex = i;
					break;
				}
			}
		}
		return retIndex;
	}

	/// <summary>
	/// 選択されたIDにフォーカス
	/// </summary>
	/// <param name="view">対象DataGridView</param>
	/// <param name="idCol">対象カラム</param>
	/// <param name="idVal">選択ID</param>
	public static void CurrentFocus(NormalOrderListDataGridView view, String idCol, String idVal)
	{
		try
		{
			int iDisplayRowCount = view.DisplayedRowCount(true);
			int i;
			bool bFound = false;
			if (view.Rows.Count == 0)
			{
				return;
			}

			// GridViewに追加されたアイテムを選択状態とするため
			for (i = 0; i < view.Rows.Count; i++)
			{
				if (idVal != string.Empty)
				{
					if ((view[idCol, i].Value != null)
                            && (view[idCol, i].Value.toString() == idVal))
					{
						view.Rows[i].Selected = true;
						view.CurrentCell = view[view.FirstDisplayedScrollingColumnIndex, i];
						bFound = true;
						break;
					}
				}
			}

			if (!bFound)
			{
				// 対象アイテムがない場合、先頭にフォーカス
				view.Rows[0].Visible = true;
				view.FirstDisplayedScrollingRowIndex = 0;
			}
			else
			{
				if (2 * i > iDisplayRowCount)
				{
					try
					{
						view.Rows[i - iDisplayRowCount / 2].Visible = true;
						view.FirstDisplayedScrollingRowIndex = i - iDisplayRowCount / 2;
					}
					catch
					{
					}
				}
				else
				{
					view.Rows[0].Visible = true;
					view.FirstDisplayedScrollingRowIndex = 0;
				}
			}
		}
		catch (Exception ex)
		{
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString);
			logger.fatal(msg + "\r\n" + ex);
		}
	}

	/// <summary>
	/// 本日他検査情報を取得する
	/// </summary>
	/// <param name="rtrisFlg">治療DBフラグ</param>
	/// <param name="risID">RisID</param>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="kensaDate">検査日</param>
	/// <returns></returns>
	public static DataTable GetOtherList(DataTable mstKType, bool rtrisFlg, String risID, String kanjaID, DateTime kensaDate)	//2010.10.29 Mod K.Shinohara
	//public static DataTable GetOtherList(bool rtrisFlg, String risID, String kanjaID, DateTime kensaDate)						//
	{
		DataTable retDt = null;
		try
		{
			MasterUtil masterUtil = new MasterUtil();	//2010.10.29 Add K.Shinohara
			OrderSearchParameter oParam = new OrderSearchParameter();

			//他検査情報を取得する
			oParam = new OrderSearchParameter();
			oParam.SetRisID(risID);
			oParam.SetKanjaID(kanjaID);
			// 2010.11.04 Add K.Shinohara Start
			oParam.SetKanjaIDOnlyBool(true);
			// 2010.11.04 Add K.Shinohara End
			oParam.SetKensaDate(kensaDate.ToString(CommonString.LIST_FORMAT_DATE_2));
			oParam.SetExecutePeriodStartDate(kensaDate);	//2010.10.29 Add K.Shinohara
			oParam.SetExecutePeriodEndDate(kensaDate);		//
			retDt = Configuration.GetInstance().GetCoreController().GetRisOtherKensaList(oParam);
			if (!retDt.Columns.Contains(RisSummaryView.RECEIPTDATE2_STRING_COLUMN))
			{
				retDt.Columns.add(RisSummaryView.RECEIPTDATE2_STRING_COLUMN);
			}

			// 2010.09.16 Add T.Ootsuka Start V2ST-10
			if (retDt != null)
			{
				for (int i = 0; i < retDt.Rows.Count; i++)
				{
					DataRow row = retDt.Rows[i];

					String statusStr	= row[RisSummaryView.STATUS_COLUMN].toString();

					// 2010.09.19 Mod K.Shinohara Start
					// {未受・遅刻・呼出}
					if (statusStr.Equals(CommonString.STATUS_UNREGISTERED) ||
						statusStr.Equals(CommonString.STATUS_ISLATE) ||
						statusStr.Equals(CommonString.STATUS_ISCALLING))
					{
						// 予定検査時刻
						String timeStr = TextUtil.ConvertKensaTimeString(row[RisSummaryView.KENSA_STARTTIME_COLUMN].toString());
						DateTime	dateTime	= new DateTime();
						if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
						{
							row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
						}
					}
					// {受済}
					else if (statusStr.Equals(CommonString.STATUS_ISREGISTERED))
					{
						// 受付時刻
						String timeStr = row[RisSummaryView.RECEIPTDATE_COLUMN].toString();
						DateTime	dateTime	= new DateTime();
						if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
						{
							row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
						}
					}
					// {検中・保留・再呼・再受付}
					// 2011.08.22 Mod K.Shinohara Start A0089
					else if (statusStr.Equals(CommonString.STATUS_INOPERATION)	||
							 statusStr.Equals(CommonString.STATUS_REST)	||
							 statusStr.Equals(CommonString.STATUS_RECALLING)	||
							 statusStr.Equals(CommonString.STATUS_REREGISTERED))
					//else if (statusStr.Equals(CommonString.STATUS_ISCALLING)	||
					//         statusStr.Equals(CommonString.STATUS_ISLATE)		||
					//         statusStr.Equals(CommonString.STATUS_RECALLING)	||
					//         statusStr.Equals(CommonString.STATUS_REREGISTERED) ||
					//         statusStr.Equals(CommonString.STATUS_REST))
					// 2011.08.22 Mod K.Shinohara End
					{
						// 検査開始時刻
						String timeStr = row[RisSummaryView.EXAMSTARTDATE_COLUMN].toString();
						DateTime	dateTime	= new DateTime();
						if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
						{
							row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
						}
					}
					// {検査済・中止}
					else if (statusStr.Equals(CommonString.STATUS_ISFINISHED) ||
							 statusStr.Equals(CommonString.STATUS_STOP))
					{
						// 検査終了時刻
						String timeStr = row[RisSummaryView.EXAMENDDATE_COLUMN].toString();
						DateTime	dateTime	= new DateTime();
						if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
						{
							row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
						}
					}
					// コメント
					//// {未受・検中}
					//if (statusStr.Equals(CommonString.STATUS_UNREGISTERED) ||
					//    statusStr.Equals(CommonString.STATUS_INOPERATION))
					//{
					//    // 検査開始時刻
					//    String timeStr = TextUtil.ConvertKensaTimeString(row[RisSummaryView.KENSA_STARTTIME_COLUMN].toString());
					//    DateTime	dateTime	= new DateTime();
					//    if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
					//    {
					//        row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
					//    }
					//}
					//// {受済}
					//else if (statusStr.Equals(CommonString.STATUS_ISREGISTERED))
					//{
					//    // 受付時刻
					//    String timeStr = row[RisSummaryView.RECEIPTDATE_COLUMN].toString();
					//    DateTime	dateTime	= new DateTime();
					//    if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
					//    {
					//        row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
					//    }
					//}
					//// {呼出・遅刻・再呼・再受付・保留}
					//else if (statusStr.Equals(CommonString.STATUS_ISCALLING)	||
					//         statusStr.Equals(CommonString.STATUS_ISLATE)		||
					//         statusStr.Equals(CommonString.STATUS_RECALLING)	||
					//         statusStr.Equals(CommonString.STATUS_REREGISTERED) ||
					//         statusStr.Equals(CommonString.STATUS_REST))
					//{
					//    RequestParameter param = new RequestParameter();
					//    param.SetRequestID(row[RisSummaryView.RIS_ID_COLUMN].toString());

					//    DataTable otherHistryDt	= Configuration.GetInstance().GetCoreController().GetRisExamOperationHistory(param.GetRequestID());

					//    for (int Lp = 0; Lp < otherHistryDt.Rows.Count; Lp++)
					//    {
					//        String OperationTypeStr = otherHistryDt.Rows[Lp][RisExamOperationHistoryTbl.OPERATIONTYPE_COLUMN].toString();

					//        if (statusStr.Equals(OperationTypeStr))
					//        {
					//            // 操作時刻
					//            String timeStr = otherHistryDt.Rows[Lp][RisExamOperationHistoryTbl.OPERATIONTIME_COLUMN].toString();
					//            DateTime	dateTime	= new DateTime();
					//            if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
					//            {
					//                row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
					//            }
					//            break;
					//        }
					//    }
					//}
					//// {検査済・中止}
					//else if (statusStr.Equals(CommonString.STATUS_ISFINISHED) ||
					//         statusStr.Equals(CommonString.STATUS_STOP))
					//{
					//    // 検査終了時刻
					//    String timeStr = row[RisSummaryView.EXAMENDDATE_COLUMN].toString();
					//    DateTime	dateTime	= new DateTime();
					//    if ((timeStr != null) && (timeStr != "") && DateTime.TryParse(timeStr, out dateTime))
					//    {
					//        row[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN] = TextUtil.ParseStringToDateTime(timeStr).ToString(CommonString.LIST_FORMAT_TIME_0);
					//    }
					//}

					// 2010.09.19 Mod K.Shinohara End

					//2010.10.29 Add K.Shinohara Start
					//検査種別名称を設定
					DataRow kTypeRow = masterUtil.FindDataRow(mstKType, MasterUtil.RIS_KENSATYPE_ID, row[MasterUtil.RIS_KENSATYPE_ID].toString());
					if (kTypeRow != null)
					{
						row[RisSummaryView.KENSATYPE_NAME_COLUMN]		= kTypeRow[MasterUtil.RIS_KENSATYPE_NAME].toString();
						row[RisSummaryView.KENSATYPE_RYAKUNAME_COLUMN]	= kTypeRow[MasterUtil.RIS_KENSATYPE_RYAKUNAME].toString();
					}
					//2010.10.29 Add K.Shinohara End
				}
			}
			// 2010.09.16 Add T.Ootsuka End

			if (rtrisFlg)
			{
				//治療DBの他検査情報を取得する
				oParam = new OrderSearchParameter();
				oParam.SetKanjaID(kanjaID);
				oParam.SetExecutePeriodStartDate(kensaDate);
				oParam.SetExecutePeriodEndDate(kensaDate);
				DataTable rtrisOtherDt = Configuration.GetInstance().GetCoreController().GetRtrisOtherKensaList(oParam);
				if (retDt != null && rtrisOtherDt != null && rtrisOtherDt.Rows.Count > 0)
				{
					for (int i=0; i<rtrisOtherDt.Rows.Count; i++)
					{
						DataRow row = rtrisOtherDt.Rows[i];

						DateTime startDate   = TextUtil.ParseStringToDateTime(row[OtherOrderView.APPOINT_START_DATE_COLUMN].toString());
						DateTime receiptDate = TextUtil.ParseStringToDateTime(row[OtherOrderView.RECEIPTDATE_COLUMN].toString());

						//治療DBの他検査情報を戻り値の他検査情報へ追加する
						DataRow addRow = retDt.NewRow();
						//
						addRow[RisSummaryView.KENSATYPE_ID_COLUMN]					= row[OtherOrderView.KENSATYPE_ID_COLUMN];
						addRow[RisSummaryView.KENSATYPE_NAME_COLUMN]				= row[OtherOrderView.KENSATYPE_NAME_COLUMN];
						addRow[RisSummaryView.KENSATYPE_RYAKUNAME_COLUMN]			= row[OtherOrderView.KENSATYPE_RYAKUNAME_COLUMN];
						addRow[RisSummaryView.STATUS_COLUMN]						= row[OtherOrderView.STATUS_COLUMN];
						if (startDate != DateTime.MinValue)
						{
							addRow[RisSummaryView.KENSA_STARTTIME_STRING_COLUMN]	= startDate.ToString(CommonString.LIST_FORMAT_TIME_0);
						}
						if (receiptDate != DateTime.MinValue)
						{
						    addRow[RisSummaryView.RECEIPTDATE2_STRING_COLUMN]		= receiptDate.ToString(CommonString.LIST_FORMAT_TIME_0);
						}
						addRow[RisSummaryView.YOTEIKENSASITU_NAME_COLUMN]			= row[OtherOrderView.EXAM_ROOM_NAME_COLUMN];
						addRow[RisSummaryView.JISSIKENSASITU_NAME_COLUMN]			= row[OtherOrderView.EXAM_ROOM_NAME_COLUMN];
						addRow[RisSummaryView.RIS_ID_COLUMN]						= row[OtherOrderView.RIS_ID_COLUMN];
						//
						retDt.Rows.add(addRow);
					}
				}
			}
		}
		catch (Exception ex)
		{
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString);
			logger.fatal(msg + "\r\n" + ex);
		}
		return retDt;
	}

	/// <summary>
	/// 一覧表示対象検査種別が存在するかチェックする
	/// </summary>
	/// <param name="menuID">画面ID</param>
	/// <returns>True: 対象検査種別あり False: 対象検査種別なし</returns>
	public static bool CheckListFormKensaType(String menuID)
	{
		//一覧画面以外の場合は処理を終える
		if (!(menuID.Equals(CommonString.MENUBUTTON_ID_B1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_D1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_E1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_F1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_G1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_J1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_H1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_I1) ||	// 2011.08.05 Add K.Aizawa@MERX NML-CAT3-034
              menuID.Equals(CommonString.MENUBUTTON_ID_K1) ||
              menuID.Equals(CommonString.MENUBUTTON_ID_P1)))
		{
			return true;
		}

		//検査種別マスタの取得
		DataTable mstKensaType = Configuration.GetInstance().GetRRisKensaTypeMaster();

		bool kensaTypeFlag = false;

		for (int i = 0; i < mstKensaType.Rows.Count; i++)
		{
			String picFlg1 = mstKensaType.Rows[i][MasterUtil.RIS_PICTUREFLAG01].toString();
			String picFlg2 = mstKensaType.Rows[i][MasterUtil.RIS_PICTUREFLAG02].toString();
			String picFlg3 = mstKensaType.Rows[i][MasterUtil.RIS_PICTUREFLAG03].toString();
			String picFlg4 = mstKensaType.Rows[i][MasterUtil.RIS_PICTUREFLAG04].toString();
			String picFlg5 = mstKensaType.Rows[i][MasterUtil.RIS_PICTUREFLAG05].toString();		// 2011.08.05 Add K.Aizawa@MERX NML-CAT3-034

			if (menuID.Equals(CommonString.MENUBUTTON_ID_G1))
			{
				//撮影系一覧
				if (picFlg1.Equals(CommonString.KENSATYPE_SHOW_FLG_ON))
				{
					kensaTypeFlag = true;
				}
			}
			else if (menuID.Equals(CommonString.MENUBUTTON_ID_J1))
			{
				//ポータブル一覧
				if (picFlg2.Equals(CommonString.KENSATYPE_SHOW_FLG_ON))
				{
					kensaTypeFlag = true;
				}
			}
			else if (menuID.Equals(CommonString.MENUBUTTON_ID_H1))
			{
				//検査系一覧
				if (picFlg3.Equals(CommonString.KENSATYPE_SHOW_FLG_ON))
				{
					kensaTypeFlag = true;
				}
			}
			else if (menuID.Equals(CommonString.MENUBUTTON_ID_K1))
			{
				//核医学一覧
				if (picFlg4.Equals(CommonString.KENSATYPE_SHOW_FLG_ON))
				{
					kensaTypeFlag = true;
				}
			}
			else if (menuID.Equals(CommonString.MENUBUTTON_ID_E1))
			{
				//プレチェック一覧
				if (picFlg3.Equals(CommonString.KENSATYPE_SHOW_FLG_ON) ||
                    picFlg4.Equals(CommonString.KENSATYPE_SHOW_FLG_ON))
				{
					kensaTypeFlag = true;
				}
			}
			// 2011.08.05 Add K.Aizawa@MERX Start NML-CAT3-034
			else if (menuID.Equals(CommonString.MENUBUTTON_ID_I1))
			{
				//業務３一覧
				if (picFlg5.Equals(CommonString.KENSATYPE_SHOW_FLG_ON))
				{
					kensaTypeFlag = true;
				}
			}
			// 2011.08.05 Add K.Aizawa@MERX End
			else
			{
				//他一覧画面
				kensaTypeFlag = true;
			}

			//表示対象検査種別が見つかった場合はループを終える
			if (kensaTypeFlag)
			{
				break;
			}
		}

		return kensaTypeFlag;
	}

	/// <summary>
	/// 対象の検査種別IDで絞り込んだDataTableを取得する
	/// </summary>
	/// <param name="mstKType">検査種別情報</param>
	/// <param name="dt">絞込み対象情報</param>
	/// <param name="kTypeID">対象検査種別ID</param>
	/// <returns></returns>
	public static DataTable GetKensatypeFilteringDataTable(DataTable mstKType, DataTable dt, String kTypeID)
	{
		DataTable	retDt = dt;
		MasterUtil	mUtil = new MasterUtil();
		try
		{
			if (mstKType == null)
			{
				mstKType = Configuration.GetInstance().GetRRisKensaTypeMaster();
			}

			//検査種別IDの実IDを取得
			int kTypeIDInt = TextUtil.ParseStringToInt(mUtil.FindData(mstKType, MasterUtil.RIS_ID, MasterUtil.RIS_KENSATYPE_ID, kTypeID));

			if (kTypeIDInt != 0)
			{
				//処理カラムが存在する場合
				if (dt.Columns.Contains(MasterUtil.RIS_KENSATYPE_FILTER_FLG) &&
					dt.Columns.Contains(MasterUtil.RIS_SHOWORDER))
				{
					//情報のループ
					for (int i = retDt.Rows.Count - 1; i >= 0; i--)
					{
						DataRow row = retDt.Rows[i];
						String filterStr = row[MasterUtil.RIS_KENSATYPE_FILTER_FLG].toString();
						if (filterStr.Length < kTypeIDInt ||
							filterStr.Substring(kTypeIDInt-1, 1) != CommonString.FLG_ON)
						{
							//該当しない行を削除する
							retDt.Rows.Remove(row);
						}
					}

					//SHOWORDERでソートする
					DataTable newTable = retDt.Clone();
					newTable.Rows.Clear();
					DataRow[] rows = retDt.Select("", MasterUtil.RIS_SHOWORDER);
					foreach (DataRow row in rows)
					{
						newTable.Rows.add(row.ItemArray);
					}
					retDt = newTable;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retDt;
	}

	/// <summary>
	/// 画面位置を設定する
	/// </summary>
	/// <param name="form">画面</param>
	/// <param name="xInt">補正X座標</param>
	/// <param name="yInt">補正Y座標</param>
	public static void SetDialogPosition(Form form, int xInt, int yInt)
	{
		try
		{
			//位置を補正
			form.Left	= Cursor.Position.X-xInt;
			form.Top	= Cursor.Position.Y-yInt;

			//左端が0以下の場合は0に設定する
			if (form.Left < 0)
			{
				form.Left = 0;
			}

			//上端が0以下の場合は0に設定する
			if (form.Top < 0)
			{
				form.Top = 0;
			}

			//右端がデスクトップ領域を超えた場合は調整する
			if (form.Left+form.Width > Screen.PrimaryScreen.Bounds.Width)
			{
				form.Left = Screen.PrimaryScreen.Bounds.Width - form.Width;
			}

			//下端がデスクトップ領域を超えた場合は調整する
			int marginInt		= Configuration.GetInstance().GetTermConfDataInt("DisplayType.Margin");
			int dispHeightInt	= Configuration.GetInstance().GetTermConfDataInt("DisplayType.Height");
			if (form.Top+form.Height > marginInt+dispHeightInt)
			{
				form.Top = marginInt+dispHeightInt - form.Height;
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}

	/// <summary>
	/// 業務画面用URLパスを取得する
	/// </summary>
	/// <param name="mstKType">検査種別情報</param>
	/// <param name="mstURL">URL情報</param>
	/// <param name="keyID">取得URLキーID</param>
	/// <param name="patFixFlg">患者確定フラグ</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="patientID">患者ID</param>
	/// <param name="kTypeID">検査種別ID</param>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	public static String GetGyomuTabURLPath(DataTable mstKType, DataTable mstURL, String keyID, bool patFixFlg, String risID, String patientID, String kTypeID, String status)
	{
		String retStr = "";
		try
		{
			if (mstKType != null && mstURL != null && keyID.Length > 0 && risID.Length > 0  && patientID.Length > 0  && kTypeID.Length > 0  && status.Length > 0)
			{
				MasterUtil mUtil = new MasterUtil();

				//対象のURL情報を取得
				DataRow urlRow = mUtil.FindDataRow(mstURL, MasterUtil.RIS_ID, keyID);
				if (urlRow != null)
				{
					String address = urlRow[MasterUtil.RIS_URLADDRESS].toString();

					//パラメータを変換する
					address = address.Replace(CommonString.URL_GYOMU_KEY_RIS_ID,		risID);
					address = address.Replace(CommonString.URL_GYOMU_KEY_PATIENT_ID,	patientID);
					address = address.Replace(CommonString.URL_GYOMU_KEY_KENSA_TYPE,	kTypeID);
					address = address.Replace(CommonString.URL_GYOMU_KEY_USER,			Configuration.GetInstance().GetUserAccountBean().GetUserID());
					address = address.Replace(CommonString.URL_GYOMU_KEY_PASSWORD,		Configuration.GetInstance().GetUserAccountBean().GetPassword());

					if (address.IndexOf(CommonString.URL_GYOMU_KEY_REQUEST_TYPE) != -1)
					{
						if (patFixFlg)
						{
							//検査時患者情報
							address = address.Replace(CommonString.URL_GYOMU_KEY_REQUEST_TYPE, CommonString.URL_GYOMU_VAL_RPATIENT);
						}
						else
						{
							//現在患者情報
							address = address.Replace(CommonString.URL_GYOMU_KEY_REQUEST_TYPE, CommonString.URL_GYOMU_VAL_PATIENT);
						}
					}

					//URLを設定
					retStr = address;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		finally
		{
			//URLをログ出力する
			String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.infoTitleGyomuURL_MessageString);
			msg = string.Format(msg, keyID, retStr);
			logger.info(msg);
		}
		return retStr;
	}

	/// <summary>
	/// 業務詳細の優先タブを取得する
	/// </summary>
	/// <param name="mstKType">検査種別情報</param>
	/// <param name="windowID">画面ID</param>
	/// <param name="risID">RIS_ID</param>
	/// <param name="patientID">患者ID</param>
	/// <param name="kTypeID">検査種別ID</param>
	/// <param name="status">ステータス</param>
	/// <param name="yoteiKensaDate">予定検査日</param>
	/// <param name="jissekiKensaDate">実施検査日</param>
	/// <returns></returns>
	public static String GetDefaultGyomuTabID(DataTable mstKType, String windowID, String risID, String patientID, String kTypeID, String status, String yoteiKensaDate, String jissekiKensaDate)
	{
		String retStr = "";
		try
		{
			// 2011.06.29 Mod Yk.Suzuki@CIJ Start NML-CAT1-023
			if (mstKType != null && windowID.Length > 0 && risID.Length > 0 && patientID.Length > 0 && kTypeID.Length > 0 && status.Length > 0)
			//if (mstKType != null && windowID.Length > 0 && risID.Length > 0 && patientID.Length > 0 && kTypeID.Length > 0 && status.Length > 0 && yoteiKensaDate.Length > 0 && jissekiKensaDate.Length > 0)
			// 2011.06.29 Mod Yk.Suzuki@CIJ End
			{
				MasterUtil mUtil = new MasterUtil();

				//対象の検査種別情報を取得
				DataRow kTypeRow = mUtil.FindDataRow(mstKType, MasterUtil.RIS_KENSATYPE_ID, kTypeID);
				if (kTypeRow != null)
				{
					String defaultTab		= "";
					String historyFlg		= "";
					String defaultDay		= "";
					String kensaDateStart	= "";
					String kensaDateEnd		= "";

					//画面IDを元に利用する値を取得
					if (windowID == CommonString.MENUBUTTON_ID_E2)
					{
						//設定値1を利用
						defaultTab = kTypeRow[MasterUtil.RIS_DEFAULTTABTYPE1].toString();
						historyFlg = kTypeRow[MasterUtil.RIS_HISTORYFLAG1].toString();
						defaultDay = kTypeRow[MasterUtil.RIS_DEFAULTDAYCOUNT1].toString();
					}
					else if (windowID == CommonString.MENUBUTTON_ID_G2 || windowID == CommonString.MENUBUTTON_ID_H2)
					{
						//設定値2を利用
						defaultTab = kTypeRow[MasterUtil.RIS_DEFAULTTABTYPE2].toString();
						historyFlg = kTypeRow[MasterUtil.RIS_HISTORYFLAG2].toString();
						defaultDay = kTypeRow[MasterUtil.RIS_DEFAULTDAYCOUNT2].toString();
					}
					else if (windowID == CommonString.MENUBUTTON_ID_F2)
					{
						//設定値3を利用
						defaultTab = kTypeRow[MasterUtil.RIS_DEFAULTTABTYPE3].toString();
						historyFlg = kTypeRow[MasterUtil.RIS_HISTORYFLAG3].toString();
						defaultDay = kTypeRow[MasterUtil.RIS_DEFAULTDAYCOUNT3].toString();
					}

					// 検査履歴優先の場合

					if (historyFlg == CommonString.FLG_ON)
					{
						//画面IDを元に利用する値を取得
						if (windowID == CommonString.MENUBUTTON_ID_E2)
						{
							//ﾌﾟﾚﾁｪｯｸ→予定検査日
							kensaDateEnd = yoteiKensaDate;
						}
						else if (windowID == CommonString.MENUBUTTON_ID_G2 || windowID == CommonString.MENUBUTTON_ID_H2)
						{
							//業務詳細→ステータス判断
							if (TextUtil.ParseStringToInt(status) >= TextUtil.ParseStringToInt(CommonString.STATUS_ISREGISTERED))
							{
								//受済以降→実施検査日
								kensaDateEnd = jissekiKensaDate;
							}
							else
							{
								//受済以前→予定検査日
								kensaDateEnd = yoteiKensaDate;
							}
						}
						else if (windowID == CommonString.MENUBUTTON_ID_F2)
						{
							//検像業務詳細
							if (jissekiKensaDate.Length > 0)
							{
								//実施検査日がある場合は実施検査日
								kensaDateEnd = jissekiKensaDate;
							}
							else
							{
								//ない場合は予定検査日
								kensaDateEnd = yoteiKensaDate;
							}
						}

						if (defaultDay.Length > 0)
						{
							//検査日から過去日を求める
							DateTime baseDate = TextUtil.ParseDateStrToDateTime(kensaDateEnd);
							if (baseDate != DateTime.MinValue)
							{
								kensaDateStart = baseDate.AddDays(-TextUtil.ParseStringToInt(defaultDay)).ToString(CommonString.LIST_FORMAT_DATE_2);
							}
						}

						//過去情報の取得
						OrderSearchParameter oParam = new OrderSearchParameter();
						oParam.SetRrisKensaTypeID(kTypeID);
						oParam.SetKanjaID(patientID);
						oParam.SetRisID(risID);
						oParam.SetExecutePeriodStartDate(TextUtil.ParseDateStrToDateTime(kensaDateStart));
						oParam.SetExecutePeriodEndDate(TextUtil.ParseDateStrToDateTime(kensaDateEnd));
						int oldCount = Configuration.GetInstance().GetCoreController().GetRrisGyomuOldOrderDataCount(oParam);
						if (oldCount > 0)
						{
							//過去情報がある場合は優先画面を検査履歴とする
							defaultTab = CommonString.KENSATYPE_DEFTAB3_CODE;
						}
					}



					//タブIDを設定する
					retStr = defaultTab;
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retStr;
	}

	/// <summary>
	/// DataTableから列リストを取得する
	/// </summary>
	/// <param name="dt">DataTable</param>
	/// <param name="whereStr">条件文字列</param>
	/// <param name="sortStr">ソート条件</param>
	/// <returns></returns>
	public static DataRow[] SelectDataRows(DataTable dt, String whereStr, String sortStr)
	{
		DataRow[] retRows = null;
		try
		{
			if (dt != null && whereStr.Length > 0)
			{
				retRows = dt.Select(whereStr, sortStr);
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retRows;
	}

	/// <summary>
	/// リスト内にキーが存在するか否かを戻す
	/// </summary>
	/// <param name="lists">リスト</param>
	/// <param name="key">キー</param>
	/// <returns></returns>
	public static bool IsStringsMatch(string[] lists, String key)
	{
		bool retBool = false;
		try
		{
			if (lists != null)
			{
				for (int i=0; i<lists.Length; i++)
				{
					if (lists[i] == key)
					{
						retBool = true;
						break;
					}
				}
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retBool;
	}
	*/

	/// <summary>
	/// DataRowに存在する文字列を取得する
	/// </summary>
	/// <param name="row">行情報</param>
	/// <param name="key">列キー情報</param>
	/// <returns></returns>
	public static String GetDataRowContainsString(DataRow row, String key)
	{
		String retStr = "";
		if (row != null && Arrays.asList(row.getColumnNames()).indexOf(key) > -1)
		{
			if (row.get(key) != null) {
				retStr = row.get(key).toString();
			}
		}
		return retStr;
	}

	/*
	// コメント(CommonUtilの同メソッドを利用する事) 2011.08.19 Del H.Orikasa A0060(修正)
	//// 2011.06.24 Mod K.Shinohara Start A0060
	///// <summary>
	///// 日付を用意する
	///// </summary>
	///// <param name="startDate">開始日</param>
	///// <param name="endDate">終了日</param>
	///// <param name="orderDate">予定検査日</param>
	///// <param name="exDate">実績検査日</param>
	///// <param name="isUnknownDate">日未定フラグ</param>
	//public static void CheckDateSpan(ref DateTime startDate, ref DateTime endDate, DateTime orderDate, DateTime exDate, ref bool isUnknownDate)
	//{
	//    try
	//    {
	//        // 日未定対応

	//        String unknownStr = Configuration.GetInstance().GetUnKnownDateString();
	//        String uStartDate = orderDate.ToString(CommonString.LIST_FORMAT_DATE_2);
	//        String uCloseDate = exDate.ToString(CommonString.LIST_FORMAT_DATE_2);
	//        if (Configuration.GetInstance().IsUnKnownDate())
	//        {
	//            if (uStartDate == unknownStr && uCloseDate != unknownStr)
	//            {
	//                //開始日が日未定の場合、終了日を開始日に上書きする
	//                isUnknownDate = true;
	//                orderDate = exDate;
	//            }
	//            else if (uStartDate != unknownStr && uCloseDate == unknownStr)
	//            {
	//                //終了日が日未定の場合、開始日を終了日に上書きする
	//                isUnknownDate = true;
	//                exDate = orderDate;
	//            }
	//            else if (uStartDate == unknownStr && uCloseDate == unknownStr)
	//            {
	//                //開始日、終了日が日未定の場合は日未定フラグONで抜ける
	//                isUnknownDate = true;
	//                return;
	//            }
	//        }

	//

	//        //開始日のチェック(予定検査日)
	//        if (startDate > orderDate)
	//        {
	//            startDate = orderDate;
	//        }
	//        //開始日のチェック(実績検査日)
	//        if (startDate > exDate)
	//        {
	//            startDate = exDate;
	//        }

	//        //終了日のチェック(予定検査日)
	//        if (endDate < orderDate)
	//        {
	//            endDate = orderDate;
	//        }
	//        //終了日のチェック(実績検査日)
	//        if (endDate < exDate)
	//        {
	//            endDate = exDate;
	//        }
	//    }
	//    catch (Exception ex)
	//    {
	//        Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString) + "\r\n" + ex);
	//        logger.fatal(ex);
	//    }
	//}
	//// コメント
	/////// <summary>
	/////// 日付を用意する
	/////// </summary>
	/////// <param name="startDate">開始日</param>
	/////// <param name="endDate">終了日</param>
	/////// <param name="orderDate">予定検査日</param>
	/////// <param name="exDate">実績検査日</param>
	////public static void CheckDateSpan(ref DateTime startDate, ref DateTime endDate, DateTime orderDate, DateTime exDate)
	////{
	////    try
	////    {
	////        //開始日のチェック(予定検査日)
	////        if (startDate > orderDate)
	////        {
	////            startDate = orderDate;
	////        }
	////        //開始日のチェック(実績検査日)
	////        if (startDate > exDate)
	////        {
	////            startDate = exDate;
	////        }

	////        //終了日のチェック(予定検査日)
	////        if (endDate < orderDate)
	////        {
	////            endDate = orderDate;
	////        }
	////        //終了日のチェック(実績検査日)
	////        if (endDate < exDate)
	////        {
	////            endDate = exDate;
	////        }
	////    }
	////    catch (Exception ex)
	////    {
	////        Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.exeException_MessageString) + "\r\n" + ex);
	////        logger.fatal(ex);
	////    }
	////}
	//
	//// 2011.06.24 Mod K.Shinohara End


	// 2010.11.01 Add K.Shinohara Start
	/// <summary>
	/// Formを必ずアクティブにする
	/// </summary>
	public static void ForceAtivate(Form form)
	{
		// Thread のアタッチ
		int fore_thread = GetWindowThreadProcessId(GetForegroundWindow(), IntPtr.Zero);
		int this_thread = AppDomain.GetCurrentThreadId();
		//int this_thread = System.Threading.Thread.CurrentThread.ManagedThreadId;

		AttachThreadInput(this_thread, fore_thread, true);

		// this をアクティブ
		form.Activate();

		// Thread のデタッチ
		AttachThreadInput(this_thread, fore_thread, false);
	}
	// 2010.11.01 Add K.Shinohara End

	// 2011.02.16 Add T.Nishikubo Start KANRO-R-27
	/// <summary>
	/// 強調ボタンの色を取得する
	/// </summary>
	/// <returns></returns>
	public static Color GetHighLightBtnColor()
	{
		//configより取得
		//System.Configuration.AppSettingsReader configurationAppSettings = new System.Configuration.AppSettingsReader();
		String colorString = "";
		ArrayList colors   = new ArrayList();
		Color retColor = Color.FromArgb(78, 72, 61);

		try
		{
			//強調色
			colorString = (string)(Configuration.GetInstance().GetDynamicPropertyString("HighLightButton.Color"));
			colors = AppCommon.GetRGBArrayFromCSVString(colorString);
			retColor = Color.FromArgb((System.Byte)colors[0], (System.Byte)colors[1], (System.Byte)colors[2]);
		}
		catch (Exception ex)
		{
			logger.Error(ex);
		}

		return retColor;
	}

	/// <summary>
	/// チェックアウト時のエラーメッセージを生成する
	/// </summary>
	/// <param name="bean">チェックアウトエラー情報</param>
	/// <param name="defaultMessage">エラーが取得できなかった時に使用するエラーメッセージ</param>
	/// <returns>生成したエラーメッセージ</returns>
	/// <remarks>Add by Asking Nisimura</remarks>
	public static String CreateCheckoutErrorMessage(AccessInfoBean bean, String defaultMessage)
	{
		String msg = null;
		logger.Debug("begin");
		try
		{
			if (bean != null)
			{
				// メッセージをコモンストリングを使って整形する
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.checkout_Error_Message_Format_MessageString);
				TerminalInfoBean termBean = Configuration.GetInstance().GetCoreController().GetTerminalInfoDataByIPAdrress(bean.GetIpAddress());
				if (termBean != null && termBean.GetTerminalName().Length > 0)
				{
					msg = String.Format(message, termBean.GetTerminalName());
				}
				else
				{
					msg = defaultMessage;
				}
			}
			else
			{
				msg = defaultMessage;
			}
		}
		catch (Exception ex)
		{
			msg = defaultMessage;
			logger.fatal(ex);
		}
		finally
		{
			logger.Debug("end");
		}
		return msg;
	}
	// 2011.02.16 Add T.Nishikubo End

	// 2011.03.21 Add Yk.Suzuki@CIJ Start A0004
	/// <summary>
	/// ブラウザクリア→表示
	/// </summary>
	/// <param name="browser">Webブラウザ</param>
	/// <param name="url">URL</param>
	public static void BrowserClearNavigate(ref WebBrowser browser, String url)
	{
		try
		{
			// 2011.03.28 Mod Yk.Suzuki@CIJ Start A0004
			WebBrowserHandler browserHandler = new WebBrowserHandler();
			browserHandler.NavigateUrl(browser, url);
			// コメント
			//// 検査
			//if (null == browser || browser.IsDisposed)
			//{
			//    return;
			//}

			//// 2011.03.24 Mod Yk.Suzuki@CIJ Start A0004
			//if (string.IsNullOrEmpty(url))
			//{
			//    // クリア
			//    browser.Navigate("");
			//}
			//else
			//{
			//    browser.Stop();
			//    if (browser.IsDisposed)
			//    {
			//        return;
			//    }
			//    // 指定URLがある場合は、一旦検索中画面を出してから表示
			//    // 検索中HTMLを組み立て、表示

			//    String htmlStr = Configuration.GetInstance().GetDynamicPropertyString("AppCommon.WebBrowserClear.Text");
			//    browser.DocumentText = htmlStr;
			//    browser.Show();
			//    // コメント
			//    //// 実行exeのパスを取得し、そのフォルダ内のHTMLへのパスを組み立てる
			//    //String exePath = Application.ExecutablePath;
			//    //String blankUrlPath = exePath.Substring(0,exePath.LastIndexOf('\\')) + "\\blank.html";
			//    //browser.Navigate(blankUrlPath);
			//    // コメント
			//    while (browser.ReadyState != WebBrowserReadyState.Complete)
			//    {
			//        if (browser.IsDisposed)
			//        {
			//            return;
			//        }
			//        Application.DoEvents();
			//    }

			//    // 本命のURLを表示
			//    browser.Navigate(url);
			//}

			//// コメント
			////// クリア
			////browser.Navigate("");

			////// 指定URLがある場合は表示
			////if (false == string.IsNullOrEmpty(url))
			////{
			////    browser.Navigate(url);
			////}
			//// コメント
			//// 2011.03.24 Mod Yk.Suzuki@CIJ End
			// コメント
			// 2011.03.28 Mod Yk.Suzuki@CIJ End
		}
		catch (Exception ex)
		{
			logger.Error(ex);
		}
	}
	// 2011.03.21 Add Yk.Suzuki@CIJ End


	// 2011.04.11 Add Yk.Suzuki@CIJ Start A0004
	/// <summary>
	/// ブラウザクリア→表示
	/// </summary>
	/// <param name="browser">Webブラウザ</param>
	/// <param name="url">URL</param>
	/// <param name="isPost">POST送信するかどうか</param>
	public static void BrowserClearNavigate(ref WebBrowser browser, String url, bool isPost)
	{
		try
		{
			WebBrowserHandler browserHandler = new WebBrowserHandler();
			browserHandler.IsPost = isPost;
			browserHandler.NavigateUrl(browser, url);
		}
		catch (Exception ex)
		{
			logger.Error(ex);
		}
	}
	// 2011.04.11 Add Yk.Suzuki@CIJ End

	// 2011.06.27 Add Yk.Suzuki@CIJ Start NML-CAT2-023

	/// <summary>
	/// 親コントロール取得
	/// </summary>
	/// <remarks>
	/// controlの親に、指定種別のものがあるか再帰検索し、
	/// 存在する場合はそのコントロールを返します。
	/// </remarks>
	/// <param name="control">検索対象</param>
	/// <param name="parentType">親コントロール種別</param>
	/// <returns>親コントロール。存在しない場合はnull。</returns>
	public static Control GetParentControl(Control control, Type parentType)
	{
		// 検査
		if (null == control || null == control.Parent) return null;

		// 指定された種別と一致した場合は、そのコントロールを返す
		if (control.Parent.GetType() == parentType)
		{
			return control.Parent;
		}

		// 見つからない場合は、さらに親を辿る
		return GetParentControl(control.Parent, parentType);
	}
	// 2011.06.27 Add Yk.Suzuki@CIJ End

	// 2011.06.30 Add H.Orikasa Start A0065
	/// <summary>
	/// 略名のIDを元に名称のIDを取得する
	/// </summary>
	/// <param name="itemID">ItemID</param>
	/// <returns></returns>
	public static String GetListRyakuToNameItemID(String itemID)
	{
		String setItemID = itemID;

		// 略名の場合は名称のItemIDを利用する

		if (setItemID == CommonString.LIST_ITEM_ID_IRAI_SECTION_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_IRAI_SECTION.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_BYOUTOU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_BYOUTOU.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KENSATYPE_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KENSATYPE_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_BUI_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_BUI_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_HOUKOU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_HOUKOU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KENSAHOUHOU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KENSAHOUHOU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KANJYA_SECTION_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KANJYA_SECTION.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_YOTEIKENSAROOM_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_YOTEIKENSAROOM.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KENSAROOM_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KENSAROOM.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_BYOUSITU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_BYOUSITU.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_YOTEIKENSASITU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_YOTEIKENSASITU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_JISSIKENSASITU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_JISSIKENSASITU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_DENPYO_BYOUTOU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_DENPYO_BYOUTOU_NAME.toString();
		}
		//履歴タブ用
		else if (setItemID == CommonString.HISTORY_ITEM_ID_KENSATYPE_RYAKU_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_KENSATYPE_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_BUI_RYAKU_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_BUI_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_HOUKOU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_HOUKOU_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_KENSAHOUHOU_RYAKU_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_KENSAHOUHOU_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_ZOUEIZAI_RYAKU_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_ZOUEIZAI_NAME.toString();
		}



		return setItemID;
	}

	/// <summary>
	/// 名称のIDを元に略称のIDを取得する
	/// </summary>
	/// <param name="itemID">ItemID</param>
	/// <returns></returns>
	public static String GetListNameToRyakuItemID(String itemID)
	{
		String setItemID = itemID;

		// 名称の場合は略称のItemIDを利用する

		if (setItemID == CommonString.LIST_ITEM_ID_IRAI_SECTION.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_IRAI_SECTION_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_BYOUTOU.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_BYOUTOU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KENSATYPE_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KENSATYPE_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_BUI_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_BUI_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_HOUKOU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_HOUKOU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KENSAHOUHOU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KENSAHOUHOU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KANJYA_SECTION.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KANJYA_SECTION_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_YOTEIKENSAROOM.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_YOTEIKENSAROOM_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_KENSAROOM.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_KENSAROOM_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_BYOUSITU.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_BYOUSITU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_YOTEIKENSASITU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_YOTEIKENSASITU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_JISSIKENSASITU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_JISSIKENSASITU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.LIST_ITEM_ID_DENPYO_BYOUTOU_NAME.toString())
		{
			setItemID = CommonString.LIST_ITEM_ID_DENPYO_BYOUTOU_RYAKU_NAME.toString();
		}
		//履歴タブ用
		else if (setItemID == CommonString.HISTORY_ITEM_ID_KENSATYPE_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_KENSATYPE_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_BUI_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_BUI_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_HOUKOU_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_HOUKOU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_KENSAHOUHOU_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_KENSAHOUHOU_RYAKU_NAME.toString();
		}
		else if (setItemID == CommonString.HISTORY_ITEM_ID_ZOUEIZAI_NAME.toString())
		{
			setItemID = CommonString.HISTORY_ITEM_ID_ZOUEIZAI_RYAKU_NAME.toString();
		}



		return setItemID;
	}
	// 2011.06.30 Add H.Orikasa End

	// 2011.11.14 Add NSK_H.Hashimoto Start OMH-1-04
	/// <summary>
	/// CSV出力先フォルダパスのテキストファイルを読み込む
	/// </summary>
	/// <returns>フォルダパス</returns>
	public static String ReadOutputCsvFolderPathText()
	{
		String strPath = "";

		try
		{
			// パスを準備
			String path =
				Configuration.GetInstance().GetDynamicPropertyString("DataListWidthTextFile.Path");
			String pathStr =
				Application.StartupPath + @"\..\" + path + LIST_REFERENCEFORM_CSV_TXTNAME;

			// ファイルの存在チェック
			if (File.Exists(pathStr))
			{
				// パス取得
				strPath =
					File.ReadAllText(pathStr, Encoding.GetEncoding(CommonString.ENCODE_DEF));
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return strPath;
	}

	/// <summary>
	/// CSV出力先フォルダパスのテキストファイルを書き込む
	/// </summary>
	/// <param name="string">フォルダパス</param>
	public static void WriteOutputCsvFolderPathText(String strPath)
	{
		try
		{
			// 保存するテキストを準備する
			if (!string.IsNullOrEmpty(strPath))
			{
				// パスを準備
				String path =
					Configuration.GetInstance().GetDynamicPropertyString("DataListWidthTextFile.Path");
				String pathStr =
					Application.StartupPath + @"\..\" + path;

				// フォルダ存在チェック
				if (!Directory.Exists(pathStr))
				{
					// フォルダ作成
					Directory.CreateDirectory(pathStr);
				}

				// ファイル名追加
				pathStr += LIST_REFERENCEFORM_CSV_TXTNAME;

				File.WriteAllText(pathStr, strPath, Encoding.GetEncoding(CommonString.ENCODE_DEF));
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
	}
	// 2011.11.14 Add NSK_H.Hashimoto End

	// 2011.11.22 Add NSK_M.Ochiai Start OMH-1-2
	/// <summary>
	/// カラーコードからColor値取得
	/// </summary>
	/// <param name="colorNumber">該当数値</param>
	/// <returns>Colorコード</returns>
	public static Color GetColorCode(int colorNumber)
	{
		Color retColor = Color.Empty;
		try
		{
			String color16x = colorNumber.ToString("X2");

			if (color16x.Length != 6)
			{
				int addNumber = 6 - color16x.Length;

				for (int i = 0; i < addNumber; i++)
				{
					color16x = "0" + color16x;
				}
			}

			int b = Convert.ToInt32(color16x.Substring(0, 2), 16);
			int g = Convert.ToInt32(color16x.Substring(2, 2), 16);
			int r = Convert.ToInt32(color16x.Substring(4, 2), 16);
			retColor = Color.FromArgb(r, g, b);
		}
		catch (Exception ex)
		{
			logger.fatal(ex);
		}
		return retColor;
	}
	// 2011.11.22 Add NSK_M.Ochiai End
	*/
}
