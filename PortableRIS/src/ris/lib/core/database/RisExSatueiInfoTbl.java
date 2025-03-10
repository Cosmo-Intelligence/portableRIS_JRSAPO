package ris.lib.core.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExSatueiBean;
import ris.lib.core.util.CommonString;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
/// RisExSatueiInfoTbl の概要の説明です。
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisExSatueiInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "EXSATUEITABLE";
	private static final String TABLE_CAPTION = "撮影情報テーブル";
	private static String TableNameStr	= Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String RIS_ID_COLUMN               	= "RIS_ID";					//Ris.ExSatueiTable.RIS_ID
	public static final String BUI_NO_COLUMN               	= "BUI_NO";					//Ris.ExSatueiTable.Bui_No
	public static final String NO_COLUMN                   	= "NO";						//Ris.ExSatueiTable.No
	public static final String SATUEISTATUS_COLUMN         	= "SATUEISTATUS";			//Ris.ExSatueiTable.SatueiStatus
	public static final String SATUEIMENU_ID_COLUMN        	= "SATUEIMENU_ID";			//Ris.ExSatueiTable.SatueiMenu_ID
	public static final String SATUEIMENU_NAME_COLUMN      	= "SATUEIMENU_NAME";		//Ris.ExSatueiTable.SatueiMenu_Name
	public static final String SATUEIMENU_NAME_KANA_COLUMN 	= "SATUEIMENU_NAME_KANA";	//Ris.ExSatueiTable.SatueiMenu_Name_kana
	public static final String SATUEI_CODE_COLUMN          	= "SATUEI_CODE";			//Ris.ExSatueiTable.Satuei_Code
	public static final String FILM_ID_COLUMN              	= "FILM_ID";				//Ris.ExSatueiTable.Film_ID
	public static final String FILM_NAME_COLUMN            	= "FILM_NAME";				//表示用
	public static final String SHOW_NO_COLUMN              	= "SHOW_NO";				//表示用
	public static final String SELECT_COLUMN               	= "SELECT";					//表示用
	public static final String PARTITION_COLUMN            	= "PARTITION";				//Ris.ExSatueiTable.Partition
	public static final String USED_COLUMN                 	= "USED";					//Ris.ExSatueiTable.Used
	public static final String RESHOT_FLG_COLUMN           	= "RESHOT_FLG";				//Ris.ExSatueiTable.ReShot_Flg
	public static final String CASSETTE_NO_COLUMN          	= "CASSETTE_NO";			//Ris.ExSatueiTable.Cassette_No
	public static final String PORTABLE_STATUS_COLUMN      	= "PORTABLE_STATUS";		//Ris.ExSatueiTable.Portable_Status
	public static final String KENSAKIKI_ID_COLUMN         	= "KENSAKIKI_ID";			//Ris.ExSatueiTable.KensaKiki_ID
	public static final String EXAMDATA01_COLUMN           	= "EXAMDATA01";				//Ris.ExSatueiTable.ExamData01
	public static final String EXAMDATA02_COLUMN           	= "EXAMDATA02";				//Ris.ExSatueiTable.ExamData02
	public static final String EXAMDATA03_COLUMN           	= "EXAMDATA03";				//Ris.ExSatueiTable.ExamData03
	public static final String EXAMDATA04_COLUMN           	= "EXAMDATA04";				//Ris.ExSatueiTable.ExamData04
	public static final String EXAMDATA05_COLUMN           	= "EXAMDATA05";				//Ris.ExSatueiTable.ExamData05
	public static final String EXAMDATA06_COLUMN           	= "EXAMDATA06";				//Ris.ExSatueiTable.ExamData06
	public static final String EXAMDATA07_COLUMN           	= "EXAMDATA07";				//Ris.ExSatueiTable.ExamData07
	public static final String EXAMDATA08_COLUMN           	= "EXAMDATA08";				//Ris.ExSatueiTable.ExamData08
	public static final String EXAMDATA09_COLUMN           	= "EXAMDATA09";				//Ris.ExSatueiTable.ExamData09
	public static final String EXAMDATA10_COLUMN           	= "EXAMDATA10";				//Ris.ExSatueiTable.ExamData10
	public static final String EXAMDATA11_COLUMN           	= "EXAMDATA11";				//Ris.ExSatueiTable.ExamData11
	public static final String EXAMDATA12_COLUMN           	= "EXAMDATA12";				//Ris.ExSatueiTable.ExamData12
	public static final String EXAMDATA13_COLUMN           	= "EXAMDATA13";				//Ris.ExSatueiTable.ExamData13
	public static final String EXAMDATA14_COLUMN           	= "EXAMDATA14";				//Ris.ExSatueiTable.ExamData14
	public static final String EXAMDATA15_COLUMN           	= "EXAMDATA15";				//Ris.ExSatueiTable.ExamData15
	public static final String EXAMDATA16_COLUMN           	= "EXAMDATA16";				//Ris.ExSatueiTable.ExamData16
	public static final String EXAMDATA17_COLUMN           	= "EXAMDATA17";				//Ris.ExSatueiTable.ExamData17
	public static final String EXAMDATA18_COLUMN           	= "EXAMDATA18";				//Ris.ExSatueiTable.ExamData18
	public static final String EXAMDATA19_COLUMN           	= "EXAMDATA19";				//Ris.ExSatueiTable.ExamData19
	public static final String EXAMDATA20_COLUMN           	= "EXAMDATA20";				//Ris.ExSatueiTable.ExamData20
	public static final String KVP_COLUMN						= "KVP";					//Ris.ExSatueiTable.Kvp
	public static final String UA_COLUMN						= "UA";						//Ris.ExSatueiTable.Ua
	public static final String MSEC_COLUMN						= "MSEC";					//Ris.ExSatueiTable.Msec
	public static final String MA_COLUMN						= "MA";						//Ris.ExSatueiTable.Ma
	public static final String SEC_COLUMN						= "SEC";					//Ris.ExSatueiTable.Sec
	public static final String MAS_COLUMN						= "MAS";					//Ris.ExSatueiTable.Mas
	// 2011.02.03 Add K.Shinohara Start
	public static final String SATUEIADDFLAG_COLUMN			= "SATUEIADDFLAG";			//Ris.ExSatueiTable.SatueiAddFlag
	// 2011.02.03 Add K.Shinohara End
	//MPPS
	public static final String MPPSINSTANCEUID_COLUMN			= "MPPSINSTANCEUID";		//Ris.ExSatueiTable.MppsInstanceUID
	public static final String MPPSVMNO_COLUMN					= "MPPSVMNO";				//Ris.ExSatueiTable.MppsVmNo
	public static final String MPPS_SATUEI_CODE_COLUMN			= "MPPS_SATUEI_CODE";		//Ris.ExSatueiTable.MppsSatueiCode
	public static final String MPPS_AE_TITLE_COLUMN			= "MPPS_AE_TITLE";			//Ris.ExSatueiTable.MppsAeTitle
	public static final String XRAYTUBECURRENT_MA_COLUMN		= "XRAYTUBECURRENT_MA";		//Ris.ExSatueiTable.XRayTubeCurrentMa
	public static final String EXPOSURETIME_SEC_COLUMN			= "EXPOSURETIME_SEC";		//Ris.ExSatueiTable.ExposureTimeSec
	public static final String EXPOSURETIME_MIN_COLUMN			= "EXPOSURETIME_MIN";		//Ris.ExSatueiTable.ExposureTimeMin
	public static final String KV_COLUMN						= "KV";						//Ris.ExSatueiTable.Kv
	public static final String EXPOSURENO_COLUMN				= "EXPOSURENO";				//Ris.ExSatueiTable.ExposureNo
	public static final String CTDI_COLUMN						= "CTDI";					//Ris.ExSatueiTable.CTDI
	public static final String DLP_COLUMN						= "DLP";					//Ris.ExSatueiTable.DLP
	public static final String FLUOROSCOPY_COLUMN				= "FLUOROSCOPY";			//Ris.ExSatueiTable.FluoroScopy
	public static final String IMAGEAREA_COLUMN				= "IMAGEAREA";				//Ris.ExSatueiTable.ImageArea
	public static final String D_DISTANCE_MM_COLUMN			= "D_DISTANCE_MM";			//Ris.ExSatueiTable.DDistanceMM
	public static final String D_DISTANCE_CM_COLUMN			= "D_DISTANCE_CM";			//Ris.ExSatueiTable.DDistanceCM
	public static final String E_DISTANCE_MM_COLUMN			= "E_DISTANCE_MM";			//Ris.ExSatueiTable.EDistanceMM
	public static final String ENTRANCEDOSE_DGY_COLUMN			= "ENTRANCEDOSE_DGY";		//Ris.ExSatueiTable.EntranceDoseDgy
	public static final String ENTRANCEDOSE_MGY_COLUMN			= "ENTRANCEDOSE_MGY";		//Ris.ExSatueiTable.EntranceDoseMgy
	public static final String EXPOSEDAREA_COLUMN				= "EXPOSEDAREA";			//Ris.ExSatueiTable.ExposedArea
	public static final String RADIATIONMODE_COLUMN			= "RADIATIONMODE";			//Ris.ExSatueiTable.RadiationMode
	public static final String FILTERTYPE_COLUMN				= "FILTERTYPE";				//Ris.ExSatueiTable.FilterType
	public static final String FILTERMATERIAL_COLUMN			= "FILTERMATERIAL";			//Ris.ExSatueiTable.FilterMaterial

	//撮影照射録用マッピング値
	public static final String NO_COLUMN_MAPPINGVAL			=   "1";
	public static final String SATUEIMENU_COLUMN_MAPPINGVAL	=   "3";
	public static final String KENSAKIKI_ID_COLUMN_MAPPINGVAL	=   "4";
	public static final String FILM_ID_COLUMN_MAPPINGVAL		=   "5";
	public static final String PARTITION_COLUMN_MAPPINGVAL		=   "6";
	public static final String USED_COLUMN_MAPPINGVAL			=   "7";
	public static final String SATUEICODE_COLUMN_MAPPINGVAL	=  "11";
	public static final String CASSETTE_NO_COLUMN_MAPPINGVAL	=  "14";

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public RisExSatueiInfoTbl()
	{
		this.tableNameStr = TableNameStr;
		this.infoCaption = TABLE_CAPTION;
	}

	//
	/// <summary>
	/// 撮影情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="satuei">撮影情報</param>
	/// <returns></returns>
	public boolean InsertSatueiData(Connection con, ExSatueiBean satuei) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && satuei != null && satuei.GetRisID().length() > 0)
		{
			retFlg = Insert( con, CreateInsertSQL(satuei), null );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/*
	//
	/// <summary>
	/// 撮影情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="satuei">撮影情報</param>
	/// <returns></returns>
	public boolean UpdateSatueiData(Connection con, Transaction trans, ExSatueiBean satuei)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && satuei != null )
		{
			retFlg = Update( con, trans, CreateUpdateSQL(satuei) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/*
	//
	/// <summary>
	/// 撮影情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="buiNo">削除対象撮影情報の部位No</param>
	/// <param name="risID">削除対象撮影情報のRisID</param>
	/// <param name="no">削除対象撮影情報のNo</param>
	/// <returns></returns>
	public boolean DeleteSatueiData(Connection con, Transaction trans, String risID, String buiNo, String no)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null && buiNo != null && no != null )
		{
			retFlg = Delete( con, trans, CreateDeleteSQL(risID,buiNo,no) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	//
	/// <summary>
	/// 撮影情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象撮影情報のRisID</param>
	/// <returns></returns>
	public boolean DeleteAllSatueiData(Connection con, String risID) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && risID.length() > 0)
		{
			retFlg = ForceDelete(con, CreateDeleteSQL(risID), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/*
	//
	/// <summary>
	/// 撮影情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="buiNo">削除対象フィルム情報の部位No</param>
	/// <param name="risID">削除対象フィルム情報のRisID</param>
	/// <param name="no">削除対象フィルム情報のNo</param>
	/// <returns>撮影情報</returns>
	public ExSatueiBean GetSatueiData(Connection con, Transaction trans, String risID, String buiNo, String no)
	{
		// parameters
		ExSatueiBean satueiBean = null;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && risID != null && buiNo != null && no != null )
		{
			DataTable dt = Select( con, trans, CreateSelectSQL(risID, buiNo, no) );
			if( dt != null && dt.Rows.Count == 1 )
			{
				satueiBean = this.CreateExSatueiBean(dt.Rows[0]);
			}
		}

		// end log
		logger.debug("end");

		return satueiBean;
	}
	*/

	/*
	//
	/// <summary>
	/// RisIDに紐付く撮影情報リストを取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">検索対象オーダー部位のRisID</param>
	/// <returns>撮影情報リスト</returns>
	public DataTable GetSatueiListByRisID(Connection con, String risID) throws Exception
	{
		// parameters
		DataTable satueiDt = new DataTable();

		// begin log
		logger.debug("begin");

		if( con != null && risID != null )
		{
			satueiDt = Select(con, CreateSelectByRisIdSQL(risID), null);
		}

		// end log
		logger.debug("end");

		return satueiDt;
	}
	*/

	//
	/// <summary>
	/// RisIDに紐付く撮影情報リストを取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">検索対象オーダー部位のRisID</param>
	/// <returns>撮影情報リスト</returns>
	public ArrayList GetSatueiListByRisID(Connection con, String risID) throws Exception
	{
		// parameters
		ArrayList satueiBeanList = new ArrayList();

		// begin log
		logger.debug("begin");

		if( con != null && risID != null )
		{
			DataTable dt = Select( con, CreateSelectByRisIdSQL(risID), null );
			for( int i=0; dt != null && i<dt.getRowCount(); i++ )
			{
				ExSatueiBean satueiBean = this.CreateExSatueiBean(dt.getRows().get(i));
				satueiBeanList.add(satueiBean);
			}
		}

		// end log
		logger.debug("end");

		return satueiBeanList;
	}

	//
	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="film">INSERT対象のフィルム情報</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL( ExSatueiBean bean )
	{
		logger.debug("begin");

		SetInfoValue(bean);

		logger.debug("end");

		return this.GetInsertSQL();

	}

	/*
	//
	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bean">UPDATE対象の撮影情報</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateSQL( ExSatueiBean bean )
	{
		logger.debug("begin");

		SetInfoValue(bean);

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/*
	//
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="buiNo"></param>
	/// <param name="no"></param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL( String risID, String buiNo, String no )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(BUI_NO_COLUMN, buiNo, true);
		this.AddColValue(NO_COLUMN, no, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}
	*/

	//
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL(String risID)
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}

	/*
	//
	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="buiNo"></param>
	/// <param name="no"></param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL( String risID, String buiNo, String no )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(BUI_NO_COLUMN, buiNo, true);
		this.AddColValue(NO_COLUMN, no, true);

		this.AddOrderKeyAsc(RIS_ID_COLUMN);
		this.AddOrderKeyAsc(BUI_NO_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}
	*/

	//
	/// <summary>
	/// SELECT用のSQL文を作成する(RisIDで検索)
	/// </summary>
	/// <remarks></remarks>
	/// <param name="buiUIDStr">SELECT対象部位のUID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectByRisIdSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		this.AddOrderKeyAsc(RIS_ID_COLUMN);
		this.AddOrderKeyAsc(BUI_NO_COLUMN);
		this.AddOrderKeyAsc(NO_COLUMN);

		logger.debug("end");

		return this.GetSelectSQL();
	}

	//
	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="film"></param>
	private void SetInfoValue( ExSatueiBean bean )
	{
		logger.debug("begin");

		// カラム値設定

		this.keys.clear();
		this.AddColValue( RIS_ID_COLUMN,				bean.GetRisID(),	true);
		this.AddColValue( BUI_NO_COLUMN,				bean.GetBuiNo(),	true);
		this.AddColValue( NO_COLUMN,					bean.GetNo(),		true);
		//
		this.AddColValue( SATUEISTATUS_COLUMN,			bean.GetSatueiStatus());
		this.AddColValue( SATUEIMENU_ID_COLUMN,			bean.GetSatueiMenuID());
		this.AddColValue( SATUEIMENU_NAME_COLUMN,		bean.GetSatueiMenuName());
		this.AddColValue( SATUEIMENU_NAME_KANA_COLUMN,	bean.GetSatueiMenuNameKana());
		this.AddColValue( SATUEI_CODE_COLUMN,			bean.GetSatueiCode());
		this.AddColValue( FILM_ID_COLUMN,				bean.GetFilmID());
		this.AddColValue( PARTITION_COLUMN,				bean.GetPartition());
		this.AddColValue( USED_COLUMN,					bean.GetUsed());
		this.AddColValue( RESHOT_FLG_COLUMN,			bean.GetReshotFlg());
		this.AddColValue( CASSETTE_NO_COLUMN,			bean.GetCassetteNo());
		this.AddColValue( PORTABLE_STATUS_COLUMN,		bean.GetPortableStatus());
		this.AddColValue( KENSAKIKI_ID_COLUMN,			bean.GetKensaKikiID());
		this.AddColValue( EXAMDATA01_COLUMN,			bean.GetExamData01());
		this.AddColValue( EXAMDATA02_COLUMN,			bean.GetExamData02());
		this.AddColValue( EXAMDATA03_COLUMN,			bean.GetExamData03());
		this.AddColValue( EXAMDATA04_COLUMN,			bean.GetExamData04());
		this.AddColValue( EXAMDATA05_COLUMN,			bean.GetExamData05());
		this.AddColValue( EXAMDATA06_COLUMN,			bean.GetExamData06());
		this.AddColValue( EXAMDATA07_COLUMN,			bean.GetExamData07());
		this.AddColValue( EXAMDATA08_COLUMN,			bean.GetExamData08());
		this.AddColValue( EXAMDATA09_COLUMN,			bean.GetExamData09());
		this.AddColValue( EXAMDATA10_COLUMN,			bean.GetExamData10());
		this.AddColValue( EXAMDATA11_COLUMN,			bean.GetExamData11());
		this.AddColValue( EXAMDATA12_COLUMN,			bean.GetExamData12());
		this.AddColValue( EXAMDATA13_COLUMN,			bean.GetExamData13());
		this.AddColValue( EXAMDATA14_COLUMN,			bean.GetExamData14());
		this.AddColValue( EXAMDATA15_COLUMN,			bean.GetExamData15());
		this.AddColValue( EXAMDATA16_COLUMN,			bean.GetExamData16());
		this.AddColValue( EXAMDATA17_COLUMN,			bean.GetExamData17());
		this.AddColValue( EXAMDATA18_COLUMN,			bean.GetExamData18());
		this.AddColValue( EXAMDATA19_COLUMN,			bean.GetExamData19());
		this.AddColValue( EXAMDATA20_COLUMN,			bean.GetExamData20());
		this.AddColValue( KVP_COLUMN,					bean.GetKVP());
		this.AddColValue( UA_COLUMN,					bean.GetuA());
		this.AddColValue( MSEC_COLUMN,					bean.GetmSec());
		this.AddColValue( MA_COLUMN,					bean.GetmA());
		this.AddColValue( SEC_COLUMN,					bean.GetSec());
		this.AddColValue( MAS_COLUMN,					bean.GetmAs());
		// 2011.02.03 Add K.Shinohara Start
		this.AddColValue( SATUEIADDFLAG_COLUMN,			bean.GetSatueiAddFlag());
		// 2011.02.03 Add K.Shinohara End

		//MPPS
		if (CommonString.FLG_ON.equals(Configuration.GetInstance().GetMppsKensaFlg()))
		{
			this.AddColValue(MPPSINSTANCEUID_COLUMN,	bean.GetMPMppsInstanceUID());
			this.AddColValue(MPPSVMNO_COLUMN,			bean.GetMPMppsVmNo());
			/* 一ノ瀬保留
			this.AddColValue(MPPS_SATUEI_CODE_COLUMN,	bean.GetMPMppsSatueiCode());
			this.AddColValue(MPPS_AE_TITLE_COLUMN,		bean.GetMPMppsAeTitle());
			this.AddColValue(XRAYTUBECURRENT_MA_COLUMN, bean.GetMPXRayTubeCurrentMa());
			this.AddColValue(EXPOSURETIME_SEC_COLUMN,	bean.GetMPExposureTimeSec());
			this.AddColValue(EXPOSURETIME_MIN_COLUMN,	bean.GetMPExposureTimeMin());
			this.AddColValue(KV_COLUMN,					bean.GetMPKv());
			this.AddColValue(EXPOSURENO_COLUMN,			bean.GetMPExposureNo());
			this.AddColValue(CTDI_COLUMN,				bean.GetMPCTDI());
			this.AddColValue(DLP_COLUMN,				bean.GetMPDLP());
			this.AddColValue(FLUOROSCOPY_COLUMN,		bean.GetMPFluoroScopy());
			this.AddColValue(IMAGEAREA_COLUMN,			bean.GetMPImageArea());
			this.AddColValue(D_DISTANCE_MM_COLUMN,		bean.GetMPDDistanceMM());
			this.AddColValue(D_DISTANCE_CM_COLUMN,		bean.GetMPDDistanceCM());
			this.AddColValue(E_DISTANCE_MM_COLUMN,		bean.GetMPEDistanceMM());
			this.AddColValue(ENTRANCEDOSE_DGY_COLUMN,	bean.GetMPEntranceDoseDgy());
			this.AddColValue(ENTRANCEDOSE_MGY_COLUMN,	bean.GetMPEntranceDoseMgy());
			this.AddColValue(EXPOSEDAREA_COLUMN,		bean.GetMPExposedArea());
			this.AddColValue(RADIATIONMODE_COLUMN,		bean.GetMPRadiationMode());
			this.AddColValue(FILTERTYPE_COLUMN,			bean.GetMPFilterType());
			this.AddColValue(FILTERMATERIAL_COLUMN,		bean.GetMPFilterMaterial());
			 */
		}

		logger.debug("end");
	}

	//
	/// <summary>
	/// 撮影情報Bean作成
	/// </summary>
	/// <param name="row"></param>
	/// <returns></returns>
	private ExSatueiBean CreateExSatueiBean( DataRow row )
	{
		// 撮影情報Bean作成
		ExSatueiBean bean = new ExSatueiBean();

		// beanへ設定

		// PrimaryKey
		bean.SetRisID((String)row.get(RIS_ID_COLUMN));
		bean.SetBuiNo(row.get(BUI_NO_COLUMN).toString());
		bean.SetNo(row.get(NO_COLUMN).toString());

		// Data
		if( row.get(SATUEISTATUS_COLUMN) != null )
			bean.SetSatueiStatus(row.get(SATUEISTATUS_COLUMN).toString());
		if( row.get(SATUEIMENU_ID_COLUMN) != null )
			bean.SetSatueiMenuID(row.get(SATUEIMENU_ID_COLUMN).toString());
		if( row.get(SATUEIMENU_NAME_COLUMN) != null )
			bean.SetSatueiMenuName(row.get(SATUEIMENU_NAME_COLUMN).toString());
		if( row.get(SATUEIMENU_NAME_KANA_COLUMN) != null )
			bean.SetSatueiMenuNameKana(row.get(SATUEIMENU_NAME_KANA_COLUMN).toString());
		if( row.get(SATUEI_CODE_COLUMN) != null )
			bean.SetSatueiCode(row.get(SATUEI_CODE_COLUMN).toString());
		if( row.get(FILM_ID_COLUMN) != null )
			bean.SetFilmID(row.get(FILM_ID_COLUMN).toString());
		if( row.get(PARTITION_COLUMN) != null )
			bean.SetPartition(row.get(PARTITION_COLUMN).toString());
		if( row.get(USED_COLUMN) != null )
			bean.SetUsed(row.get(USED_COLUMN).toString());
		if( row.get(RESHOT_FLG_COLUMN) != null )
			bean.SetReshotFlg(row.get(RESHOT_FLG_COLUMN).toString());
		if( row.get(CASSETTE_NO_COLUMN) != null )
			bean.SetCassetteNo(row.get(CASSETTE_NO_COLUMN).toString());
		if( row.get(PORTABLE_STATUS_COLUMN) != null )
			bean.SetPortableStatus(row.get(PORTABLE_STATUS_COLUMN).toString());
		if( row.get(KENSAKIKI_ID_COLUMN) != null )
			bean.SetKensaKikiID(row.get(KENSAKIKI_ID_COLUMN).toString());
		if( row.get(EXAMDATA01_COLUMN) != null )
			bean.SetExamData01(row.get(EXAMDATA01_COLUMN).toString());
		if( row.get(EXAMDATA02_COLUMN) != null )
			bean.SetExamData02(row.get(EXAMDATA02_COLUMN).toString());
		if( row.get(EXAMDATA03_COLUMN) != null )
			bean.SetExamData03(row.get(EXAMDATA03_COLUMN).toString());
		if( row.get(EXAMDATA04_COLUMN) != null )
			bean.SetExamData04(row.get(EXAMDATA04_COLUMN).toString());
		if( row.get(EXAMDATA05_COLUMN) != null )
			bean.SetExamData05(row.get(EXAMDATA05_COLUMN).toString());
		if( row.get(EXAMDATA06_COLUMN) != null )
			bean.SetExamData06(row.get(EXAMDATA06_COLUMN).toString());
		if( row.get(EXAMDATA07_COLUMN) != null )
			bean.SetExamData07(row.get(EXAMDATA07_COLUMN).toString());
		if( row.get(EXAMDATA08_COLUMN) != null )
			bean.SetExamData08(row.get(EXAMDATA08_COLUMN).toString());
		if( row.get(EXAMDATA09_COLUMN) != null )
			bean.SetExamData09(row.get(EXAMDATA09_COLUMN).toString());
		if( row.get(EXAMDATA10_COLUMN) != null )
			bean.SetExamData10(row.get(EXAMDATA10_COLUMN).toString());
		if( row.get(EXAMDATA11_COLUMN) != null )
			bean.SetExamData11(row.get(EXAMDATA11_COLUMN).toString());
		if( row.get(EXAMDATA12_COLUMN) != null )
			bean.SetExamData12(row.get(EXAMDATA12_COLUMN).toString());
		if( row.get(EXAMDATA13_COLUMN) != null )
			bean.SetExamData13(row.get(EXAMDATA13_COLUMN).toString());
		if( row.get(EXAMDATA14_COLUMN) != null )
			bean.SetExamData14(row.get(EXAMDATA14_COLUMN).toString());
		if( row.get(EXAMDATA15_COLUMN) != null )
			bean.SetExamData15(row.get(EXAMDATA15_COLUMN).toString());
		if( row.get(EXAMDATA16_COLUMN) != null )
			bean.SetExamData16(row.get(EXAMDATA16_COLUMN).toString());
		if( row.get(EXAMDATA17_COLUMN) != null )
			bean.SetExamData17(row.get(EXAMDATA17_COLUMN).toString());
		if( row.get(EXAMDATA18_COLUMN) != null )
			bean.SetExamData18(row.get(EXAMDATA18_COLUMN).toString());
		if( row.get(EXAMDATA19_COLUMN) != null )
			bean.SetExamData19(row.get(EXAMDATA19_COLUMN).toString());
		if( row.get(EXAMDATA20_COLUMN) != null )
			bean.SetExamData20(row.get(EXAMDATA20_COLUMN).toString());
		if (row.get(KVP_COLUMN) != null )
			bean.SetKVP(row.get(KVP_COLUMN).toString());
		if (row.get(UA_COLUMN) != null )
			bean.SetuA(row.get(UA_COLUMN).toString());
		if (row.get(MSEC_COLUMN) != null )
			bean.SetmSec(row.get(MSEC_COLUMN).toString());
		if (row.get(MA_COLUMN) != null )
			bean.SetmA(row.get(MA_COLUMN).toString());
		if (row.get(SEC_COLUMN) != null )
			bean.SetSec(row.get(SEC_COLUMN).toString());
		if (row.get(MAS_COLUMN) != null )
			bean.SetmAs(row.get(MAS_COLUMN).toString());
		// 2011.02.03 Add K.Shinohara Start
		if (row.get(SATUEIADDFLAG_COLUMN) != null )
			bean.SetSatueiAddFlag(row.get(SATUEIADDFLAG_COLUMN).toString());
		// 2011.02.03 Add K.Shinohara End
		/* 一ノ瀬保留
		//MPPS
		if (CommonString.FLG_ON.equals(Configuration.GetInstance().GetMppsKensaFlg()))
		{
			if (Arrays.asList(row.getColumnNames()).indexOf(MPPSINSTANCEUID_COLUMN) > -1 && row.get(MPPSINSTANCEUID_COLUMN) != null)
				bean.SetMPMppsInstanceUID(row.get(MPPSINSTANCEUID_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(MPPSVMNO_COLUMN) > -1 && row.get(MPPSVMNO_COLUMN) != null)
				bean.SetMPMppsVmNo(row.get(MPPSVMNO_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(MPPS_SATUEI_CODE_COLUMN) > -1 && row.get(MPPS_SATUEI_CODE_COLUMN) != null)
				bean.SetMPMppsSatueiCode(row.get(MPPS_SATUEI_CODE_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(MPPS_AE_TITLE_COLUMN) > -1 && row.get(MPPS_AE_TITLE_COLUMN) != null)
				bean.SetMPMppsAeTitle(row.get(MPPS_AE_TITLE_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(XRAYTUBECURRENT_MA_COLUMN) > -1 && row.get(XRAYTUBECURRENT_MA_COLUMN) != null)
				bean.SetMPXRayTubeCurrentMa(row.get(XRAYTUBECURRENT_MA_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(EXPOSURETIME_SEC_COLUMN) > -1 && row.get(EXPOSURETIME_SEC_COLUMN) != null)
				bean.SetMPExposureTimeSec(row.get(EXPOSURETIME_SEC_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(EXPOSURETIME_MIN_COLUMN) > -1 && row.get(EXPOSURETIME_MIN_COLUMN) != null)
				bean.SetMPExposureTimeMin(row.get(EXPOSURETIME_MIN_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(KV_COLUMN) > -1 && row.get(KV_COLUMN) != null)
				bean.SetMPKv(row.get(KV_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(EXPOSURENO_COLUMN) > -1 && row.get(EXPOSURENO_COLUMN) != null)
				bean.SetMPExposureNo(row.get(EXPOSURENO_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(CTDI_COLUMN) > -1 && row.get(CTDI_COLUMN) != null)
				bean.SetMPCTDI(row.get(CTDI_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(DLP_COLUMN) > -1 && row.get(DLP_COLUMN) != null)
				bean.SetMPDLP(row.get(DLP_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(FLUOROSCOPY_COLUMN) > -1 && row.get(FLUOROSCOPY_COLUMN) != null)
				bean.SetMPFluoroScopy(row.get(FLUOROSCOPY_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(IMAGEAREA_COLUMN) > -1 && row.get(IMAGEAREA_COLUMN) != null)
				bean.SetMPImageArea(row.get(IMAGEAREA_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(D_DISTANCE_MM_COLUMN) > -1 && row.get(D_DISTANCE_MM_COLUMN) != null)
				bean.SetMPDDistanceMM(row.get(D_DISTANCE_MM_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(D_DISTANCE_CM_COLUMN) > -1 && row.get(D_DISTANCE_CM_COLUMN) != null)
				bean.SetMPDDistanceCM(row.get(D_DISTANCE_CM_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(E_DISTANCE_MM_COLUMN) > -1 && row.get(E_DISTANCE_MM_COLUMN) != null)
				bean.SetMPEDistanceMM(row.get(E_DISTANCE_MM_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(ENTRANCEDOSE_DGY_COLUMN) > -1 && row.get(ENTRANCEDOSE_DGY_COLUMN) != null)
				bean.SetMPEntranceDoseDgy(row.get(ENTRANCEDOSE_DGY_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(ENTRANCEDOSE_MGY_COLUMN) > -1 && row.get(ENTRANCEDOSE_MGY_COLUMN) != null)
				bean.SetMPEntranceDoseMgy(row.get(ENTRANCEDOSE_MGY_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(EXPOSEDAREA_COLUMN) > -1 && row.get(EXPOSEDAREA_COLUMN) != null)
				bean.SetMPExposedArea(row.get(EXPOSEDAREA_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(RADIATIONMODE_COLUMN) > -1 && row.get(RADIATIONMODE_COLUMN) != null)
				bean.SetMPRadiationMode(row.get(RADIATIONMODE_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(FILTERTYPE_COLUMN) > -1 && row.get(FILTERTYPE_COLUMN) != null)
				bean.SetMPFilterType(row.get(FILTERTYPE_COLUMN).toString());
			if (Arrays.asList(row.getColumnNames()).indexOf(FILTERMATERIAL_COLUMN) > -1 && row.get(FILTERMATERIAL_COLUMN) != null)
				bean.SetMPFilterMaterial(row.get(FILTERMATERIAL_COLUMN).toString());
		}
		*/
		return bean;
	}

	/*
	/// <summary>
	///	帳票用-実績撮影情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintExSatueiDataTable(Connection con, Transaction trans, OrderSearchParameter param)
	{
		// parameters
		DataTable retDt = new DataTable();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && param != null)
		{
			String sql = CreateSelectSQL(param);

			if (sql.length() > 0)
			{
				//SELECT
				retDt = Select(con, trans, sql, false);
			}
		}

		// end log
		logger.debug("end");

		return retDt;
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <param name="risID">SELECT対象のRisID</param>
	/// <returns>SELECT用のSQL文</returns>
	//private String CreateSelectSQL(OrderSearchParameter param)
	//{
	//	logger.debug("begin");
	//
	//	// parameters
	//	StringBuilder sqlStrBuild = new StringBuilder("");
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX Start NML-CAT1-000
	//	sqlStrBuild.append(" SELECT /*+ INDEX(ES) INDEX(OM) INDEX(EO) INDEX(EM) */ ES.* FROM ");
	//	// 元の処理
	//	//sqlStrBuild.append(" SELECT /*+ RULE */ ES.* FROM ");	//2010.11.08 Mod H.Orikasa
	//	////sqlStrBuild.append(" SELECT ES.* FROM ");			//
	//
	//	// 2011.08.22 Mod T.Nishikubo@MERX End
	//	sqlStrBuild.append(TABLE_NAME + " ES");
	//	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
	//	// 2011.06.24 Add K.Shinohara Start A0060
	//	sqlStrBuild.append(", EXMAINTABLE EM ");
	//	// 2011.06.24 Add K.Shinohara End
	//	sqlStrBuild.append(" WHERE ");
	//	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
	//	sqlStrBuild.append(" OM.RIS_ID = ES.RIS_ID ");
	//	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
	//	sqlStrBuild.append(" ORDER BY OM.RIS_ID, ES.BUI_NO, ES.NO ");
	//
	//	logger.debug("end");
	//
	//	return sqlStrBuild.toString();
	//}
}
