package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import ris.lib.core.Configuration;
import ris.lib.core.bean.PatientInfoBean;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
/// 実績患者テーブルの管理クラス
///
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.04	: 112478 / A.Kobayashi	: original
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisPatientResultsInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PATIENTRESULTSINFO";
	private static final String TABLE_CAPTION = "実績患者情報";
	private static String TableNameStr = Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String RIS_ID_COLUMN				= "RIS_ID";
	public static final String KANJA_ID_COLUMN				= "KANJA_ID";
	public static final String KANJISIMEI_COLUMN			= "KANJISIMEI";
	public static final String ROMASIMEI_COLUMN			= "ROMASIMEI";
	public static final String KANASIMEI_COLUMN			= "KANASIMEI";
	public static final String BIRTHDAY_COLUMN				= "BIRTHDAY";
	public static final String SEX_COLUMN					= "SEX";
	public static final String KANJA_NYUGAIKBN_COLUMN		= "KANJA_NYUGAIKBN";
	public static final String SECTIONID_COLUMN			= "SECTION_ID";
	public static final String BYOUTOUID_COLUMN			= "BYOUTOU_ID";
	public static final String BYOUSITUID_COLUMN			= "BYOUSITU_ID";
	public static final String TALL_COLUMN					= "TALL";
	public static final String WEIGHT_COLUMN				= "WEIGHT";
	public static final String BLOOD_COLUMN				= "BLOOD";
	public static final String TRANSPORTTYPE_COLUMN		= "TRANSPORTTYPE";
	public static final String HANDICAPPEDMARK_COLUMN		= "HANDICAPPEDMARK";
	public static final String HANDICAPPED_COLUMN			= "HANDICAPPED";
	public static final String INFECTIONMARK_COLUMN		= "INFECTIONMARK";
	public static final String INFECTION_COLUMN			= "INFECTION";
	public static final String CONTRADICTIONMARK_COLUMN	= "CONTRAINDICATIONMARK";
	public static final String CONTRADICTION_COLUMN		= "CONTRAINDICATION";
	public static final String ALLERGYMARK_COLUMN			= "ALLERGYMARK";
	public static final String ALLERGY_COLUMN				= "ALLERGY";
	public static final String PREGNANCYMARK_COLUMN		= "PREGNANCYMARK";
	public static final String PREGNANCY_COLUMN			= "PREGNANCY";
	public static final String NOTESMARK_COLUMN			= "NOTESMARK";
	public static final String NOTES_COLUMN				= "NOTES";
	public static final String EXAMDATA_COLUMN				= "EXAMDATA";
	public static final String EXTRA_PROFILE_COLUMN		= "EXTRAPROFILE";

	// 2010.07.30 Add T.Ootsuka Start
	public static final String CREATININERESULT_COLUMN		= "CREATININERESULT";
	public static final String EGFRRESULT_COLUMN			= "EGFRRESULT";
	public static final String CREATININEUPDATEDATE_COLUMN	= "CREATININEUPDATEDATE";
	public static final String EGFRUPDATEDATE_COLUMN		= "EGFRUPDATEDATE";
	// 2010.07.30 Add T.Ootsuka End

    public RisPatientResultsInfoTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
		this.tableNameStr = TableNameStr;
	}

	/// <summary>
	/// 患者情報新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RisID</param>
	/// <param name="patient">患者情報</param>
	/// <returns></returns>
	public boolean InsertPatientResultData( Connection con, String risID, PatientInfoBean patient ) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && risID != null && patient != null )
		{
			retFlg = Insert( con, CreateInsertSQL(risID, patient), null );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// 患者情報更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RisID</param>
	/// <param name="patient">患者情報</param>
	/// <returns></returns>
	public boolean UpdatePatientResultData( Connection con, String risID, PatientInfoBean patient ) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && risID != null && patient != null)
		{
			retFlg = Update( con, CreateUpdateSQL(risID, patient), null );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

    /*
	/// <summary>
	/// 身長体重を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">RisID</param>
	/// <param name="patient">患者情報</param>
	/// <returns></returns>
	public boolean UpdatePatientResultTallWeightData(Connection con, Transaction trans, String risID, PatientInfoBean patient)
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && risID != null && patient != null)
		{
			retFlg = Update(con, trans, CreateUpdateTallWeightSQL(risID, patient));
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/// <summary>
	/// 患者情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">削除対象患者情報のRisID</param>
	/// <param name="kanjaID">削除対象患者情報のkanajaID</param>
	/// <returns></returns>
	public boolean DeletePatientResultData( Connection con, String risID ) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && risID != null)
		{
			retFlg = ForceDelete(con, CreateDeleteSQL(risID), null);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// 患者情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="risID">取得対象患者情報のRisID</param>
	/// <param name="kanjaID">取得対象患者情報のkanajaID</param>
	/// <returns>患者情報</returns>
	public PatientInfoBean GetPatientResultData( Connection con, String risID ) throws Exception
	{
		// parameters
		PatientInfoBean patientBean = null;

		// begin log
		logger.debug("begin");

		if (con != null && risID != null && risID.length() > 0)
		{
			ArrayList<Object> arglist = new ArrayList<Object>();
			String cmd = CreateSelectSQL(risID, arglist);
			DataTable dt = Select(con, cmd, arglist);
			if( dt != null && dt.getRowCount() == 1 )
			{
				patientBean = this.CreatePatientInfoBean(dt.getRows().get(0));
			}
		}

		// end log
		logger.debug("end");

		return patientBean;
	}

	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">INSERT対象のRisID</param>
	/// <param name="patient">INSERT対象の患者情報データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateInsertSQL( String risID, PatientInfoBean patient )
	{
		logger.debug("begin");

		SetColValue(risID, patient);

		logger.debug("end");

		return this.GetInsertSQL();

	}

	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">UPDATE対象のRisID</param>
	/// <param name="patient">UPDATE対象の患者情報データ</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateSQL( String risID, PatientInfoBean patient )
	{
		logger.debug("begin");

		SetColValue(risID, patient);

		logger.debug("end");

		return this.GetUpdateSQL();
	}

    /*
	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID">UPDATE対象のRisID</param>
	/// <param name="patient">UPDATE対象の患者情報データ</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateTallWeightSQL(String risID, PatientInfoBean patient)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(TALL_COLUMN,	patient.GetTall());
		this.AddColValue(WEIGHT_COLUMN, patient.GetWeight());

		logger.debug("end");

		return this.GetUpdateSQL();
	}
	*/

	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="kanjaID"></param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL( String risID )
	{
		logger.debug("begin");

		this.keys.clear();
		this.AddColValue(RIS_ID_COLUMN, risID, true);

		logger.debug("end");

		return this.GetDeleteSQL();
	}

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="risID"></param>
	/// <param name="kanjaID"></param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL(String risID, ArrayList<Object> arglist)
	{
		logger.debug("begin");

		String cmd = "";

		this.keys.clear();

		//患者ID
		if (risID.length() > 0)
		{
			this.AddColSetValue(RIS_ID_COLUMN, "?", true, SignType.Equal);
			arglist.add(risID);
			//Parameter oraParam = new Parameter(RIS_ID_COLUMN, risID);
			//cmd.Parameters.Add(oraParam);
			//cmd.Parameters.Add(RIS_ID_COLUMN, .DataAccess.Client.DbType.Varchar2, risID, ParameterDirection.Input);
		}

		//取得するカラム文字列を作成
		String colmunName = " * ";

		cmd = this.GetSelectColmunSQL(colmunName, TABLE_NAME);

		logger.debug("end");

		return cmd;
	}

	/// <summary>
	/// カラム値設定
	/// </summary>
	/// <param name="projectNote"></param>
	/// <param name="user"></param>
	/// <param name="sqlType"></param>
	/// <param name="extend"></param>
	private void SetColValue( String risID, PatientInfoBean patient )
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(RIS_ID_COLUMN, risID, true);
		this.AddColValue(KANJA_ID_COLUMN, patient.GetKanjaID(),true);
		this.AddColValue(ROMASIMEI_COLUMN, patient.GetRomaSimei());
		this.AddColValue(KANJISIMEI_COLUMN, patient.GetKanjiSimei());
		this.AddColValue(KANASIMEI_COLUMN, patient.GetKanaSimei());
		this.AddColValue(BIRTHDAY_COLUMN, patient.GetBirthday());
		this.AddColValue(SEX_COLUMN, patient.GetSex());
		this.AddColValue(KANJA_NYUGAIKBN_COLUMN, patient.GetNyugaiKbn());
		this.AddColValue(SECTIONID_COLUMN, patient.GetSectionID());
		this.AddColValue(BYOUTOUID_COLUMN, patient.GetByoutouID());
		this.AddColValue(BYOUSITUID_COLUMN, patient.GetByousituID());
		this.AddColValue(TALL_COLUMN, patient.GetTall());
		this.AddColValue(WEIGHT_COLUMN, patient.GetWeight());
		this.AddColValue(BLOOD_COLUMN, patient.GetBlood());
		this.AddColValue(TRANSPORTTYPE_COLUMN, patient.GetTransportType());
		this.AddColValue(HANDICAPPEDMARK_COLUMN, patient.GetHandicappedMark());
		this.AddColValue(HANDICAPPED_COLUMN, patient.GetHandicap());
		this.AddColValue(INFECTIONMARK_COLUMN, patient.GetInfectionMark());
		this.AddColValue(INFECTION_COLUMN, patient.GetInfection());
		this.AddColValue(CONTRADICTIONMARK_COLUMN, patient.GetContraindicationMark());
		this.AddColValue(CONTRADICTION_COLUMN, patient.GetContraindication());
		this.AddColValue(ALLERGYMARK_COLUMN, patient.GetAllergyMark());
		this.AddColValue(ALLERGY_COLUMN, patient.GetAllergy());
		this.AddColValue(PREGNANCYMARK_COLUMN, patient.GetPregnancyMark());
		this.AddColValue(PREGNANCY_COLUMN, patient.GetPregnancy());
		this.AddColValue(NOTESMARK_COLUMN, patient.GetNotesMark());
		this.AddColValue(NOTES_COLUMN, patient.GetNotes());
		this.AddColValue(EXAMDATA_COLUMN, patient.GetExamData());
		this.AddColValue(EXTRA_PROFILE_COLUMN, patient.GetExtraProfile());

		// 2010.07.30 Add T.Ootsuka Start
		this.AddColValue(CREATININERESULT_COLUMN, patient.GetCreatinineresult());
		this.AddColValue(EGFRRESULT_COLUMN, patient.GetEgfrresult());
		this.AddColValue(CREATININEUPDATEDATE_COLUMN, patient.GetCreatinineUpdateDate());
		this.AddColValue(EGFRUPDATEDATE_COLUMN, patient.GetEgfrresultUpdateDate());
		// 2010.07.30 Add T.Ootsuka End

		logger.debug("end");
	}

	/// <summary>
	/// PatientInfoBean 作成
	/// </summary>
	/// <param name="row"></param>
	/// <returns></returns>
	private PatientInfoBean CreatePatientInfoBean(DataRow row)
	{
		PatientInfoBean bean = new PatientInfoBean();
		// Set Primay Key
		if ( row.get(RIS_ID_COLUMN) == null || row.get(KANJA_ID_COLUMN) == null)
			return null;

		bean.SetKanjaID((String)row.get(KANJA_ID_COLUMN));
		//
		// Set PatientData(PATIENTINFO)
		//
		if( row.get(KANJISIMEI_COLUMN) != null)
			bean.SetKanjiSimei(row.get(KANJISIMEI_COLUMN).toString());
		if( row.get(ROMASIMEI_COLUMN) != null)
			bean.SetRomaSimei(row.get(ROMASIMEI_COLUMN).toString());
		if( row.get(KANASIMEI_COLUMN) != null)
			bean.SetKanaSimei(row.get(KANASIMEI_COLUMN).toString());
		if( row.get(BIRTHDAY_COLUMN) != null)
			bean.SetBirthday(String.format("%1$08d", Integer.parseInt(row.get(BIRTHDAY_COLUMN).toString())));
		if( row.get(SEX_COLUMN) != null)
			bean.SetSex(row.get(SEX_COLUMN).toString());
		if( row.get(KANJA_NYUGAIKBN_COLUMN) != null)
			bean.SetNyugaiKbn(row.get(KANJA_NYUGAIKBN_COLUMN).toString());
		if( row.get(SECTIONID_COLUMN) != null)
			bean.SetSectionID(row.get(SECTIONID_COLUMN).toString());
		if( row.get(BYOUTOUID_COLUMN) != null)
			bean.SetByoutouID(row.get(BYOUTOUID_COLUMN).toString());
		if( row.get(BYOUSITUID_COLUMN) != null)
			bean.SetByousituID(row.get(BYOUSITUID_COLUMN).toString());
		if( row.get(TALL_COLUMN) != null)
			bean.SetTall(row.get(TALL_COLUMN).toString());
		if( row.get(WEIGHT_COLUMN) != null)
			bean.SetWeight(row.get(WEIGHT_COLUMN).toString());
		if( row.get(BLOOD_COLUMN) != null)
			bean.SetBlood(row.get(BLOOD_COLUMN).toString());
		if( row.get(TRANSPORTTYPE_COLUMN) != null)
			bean.SetTransportType(row.get(TRANSPORTTYPE_COLUMN).toString());
		if( row.get(HANDICAPPEDMARK_COLUMN) != null)
			bean.SetHandicappedMark(Integer.parseInt(row.get(HANDICAPPEDMARK_COLUMN).toString()));
		if( row.get(HANDICAPPED_COLUMN) != null)
			bean.SetHandicap(row.get(HANDICAPPED_COLUMN).toString());
		if( row.get(INFECTIONMARK_COLUMN) != null)
			bean.SetInfectionMark(Integer.parseInt(row.get(INFECTIONMARK_COLUMN).toString()));
		if( row.get(INFECTION_COLUMN) != null)
			bean.SetInfection(row.get(INFECTION_COLUMN).toString());
		if( row.get(CONTRADICTIONMARK_COLUMN) != null)
			bean.SetContraindicationMark(Integer.parseInt(row.get(CONTRADICTIONMARK_COLUMN).toString()));
		if( row.get(CONTRADICTION_COLUMN) != null)
			bean.SetContraindication(row.get(CONTRADICTION_COLUMN).toString());
		if( row.get(ALLERGYMARK_COLUMN) != null)
			bean.SetAllergyMark(Integer.parseInt(row.get(ALLERGYMARK_COLUMN).toString()));
		if( row.get(ALLERGY_COLUMN) != null)
			bean.SetAllergy(row.get(ALLERGY_COLUMN).toString());
		if( row.get(PREGNANCYMARK_COLUMN) != null)
			bean.SetPregnancyMark(Integer.parseInt(row.get(PREGNANCYMARK_COLUMN).toString()));
		if( row.get(PREGNANCY_COLUMN) != null)
			bean.SetPregnancy(row.get(PREGNANCY_COLUMN).toString());
		if( row.get(NOTESMARK_COLUMN) != null)
			bean.SetNotesMark(Integer.parseInt(row.get(NOTESMARK_COLUMN).toString()));
		if( row.get(NOTES_COLUMN) != null)
			bean.SetNotes(row.get(NOTES_COLUMN).toString());
		if( row.get(EXAMDATA_COLUMN) != null)
			bean.SetExamData(row.get(EXAMDATA_COLUMN).toString());
		if( row.get(EXTRA_PROFILE_COLUMN) != null)
			bean.SetExtraProfile(row.get(EXTRA_PROFILE_COLUMN).toString());

		// 2010.07.30 Add T.Ootsuka Start
		if (row.get(CREATININERESULT_COLUMN) != null)
		{
			bean.SetCreatinineresult(row.get(CREATININERESULT_COLUMN).toString());
		}
		if (row.get(EGFRRESULT_COLUMN) != null)
		{
			bean.SetEgfrresult(row.get(EGFRRESULT_COLUMN).toString());
		}
		if (row.get(CREATININEUPDATEDATE_COLUMN) != null)
		{
			bean.SetCreatinineUpdateDate((Timestamp)row.get(CREATININEUPDATEDATE_COLUMN));
		}
		if (row.get(EGFRUPDATEDATE_COLUMN) != null)
		{
			bean.SetEgfrresultUpdateDate((Timestamp)row.get(EGFRUPDATEDATE_COLUMN));
		}
		// 2010.07.30 Add T.Ootsuka End

		return bean;
	}

    /*
	/// <summary>
	///	帳票用-実績患者情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintResultsPatientDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
    //	sqlStrBuild.append(" SELECT /*+ INDEX(PI) INDEX(OM) INDEX(EO) INDEX(EM) */ PI.* FROM ");
    //	// 元の処理
    //	//sqlStrBuild.append(" SELECT /*+ RULE */ PI.* FROM ");	//2010.11.08 Mod H.Orikasa
    //	////sqlStrBuild.append(" SELECT PI.* FROM ");			//

    //	// 2011.08.22 Mod T.Nishikubo@MERX End
    //	sqlStrBuild.append(TABLE_NAME + " PI");
    //	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
    //	// 2011.06.24 Add K.Shinohara Start A0060
    //	sqlStrBuild.append(", EXMAINTABLE EM ");
    //	// 2011.06.24 Add K.Shinohara End
    //	sqlStrBuild.append(" WHERE ");
    //	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
    //	sqlStrBuild.append(" PI.RIS_ID = OM.RIS_ID ");
    //	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
    //
    //	logger.debug("end");
    //
    //	return sqlStrBuild.toString();
    //}
}
