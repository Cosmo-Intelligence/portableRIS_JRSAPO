package ris.lib.core.database;

import java.sql.Connection;
import java.sql.Timestamp;

import ris.lib.core.Configuration;
import ris.lib.core.bean.PatientInfoBean;
import ris.lib.core.util.SqlUtil;
import ris.portable.util.DataTable;

/// <summary>
/// PatientInfoTbl の概要の説明です。
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.01.00	: 2011.08.22	: 999999 / T.Nishikubo	: NML-CAT1-000
///
/// </summary>
public class RisPatientInfoTbl extends BaseTbl
{
	// アクセステーブル名
	public static final String TABLE_NAME = "PATIENTINFO";
	private static final String TABLE_CAPTION = "患者情報";
	private static String TableNameStr = Configuration.GetInstance().GetRisDbUserStr() + "." + TABLE_NAME;

	// カラム定義
	public static final String KANJAID_COLUMN				= "KANJA_ID";
	public static final String KANJISIMEI_COLUMN			= "KANJISIMEI";
	public static final String ROMASIMEI_COLUMN			= "ROMASIMEI";
	public static final String KANASIMEI_COLUMN			= "KANASIMEI";
	public static final String BIRTHDAY_COLUMN				= "BIRTHDAY";
	public static final String SEX_COLUMN					= "SEX";
	public static final String JUSYO1_COLUMN				= "JUSYO1";
	public static final String JUSYO2_COLUMN				= "JUSYO2";
	public static final String JUSYO3_COLUMN				= "JUSYO3";
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
	public static final String HIS_UPDATEDATE_COLUMN		= "HIS_UPDATEDATE";
	public static final String RIS_UPDATEDATE_COLUMN		= "RIS_UPDATEDATE";
	public static final String DEATHDATE_COLUMN			= "DEATHDATE";
	// 2010.07.30 Add T.Ootsuka Start
	public static final String CREATININERESULT_COLUMN		= "CREATININERESULT";
	public static final String EGFRRESULT_COLUMN			= "EGFRRESULT";
	public static final String CREATININEUPDATEDATE_COLUMN	= "CREATININEUPDATEDATE";
	public static final String EGFRUPDATEDATE_COLUMN		= "EGFRUPDATEDATE";
	// 2010.07.30 Add T.Ootsuka End

	//表示用カラム
	public static final String AGE_COLUMN					= "AGE";
	public static final String BIRTHDAY_STRING_COLUMN		= "BIRTHDAY_STRING";
	public static final String KANJA_NYUGAIKBN_NAME_COLUMN = "KANJA_NYUGAIKBN_NAME";
	public static final String SECTION_NAME_COLUMN			= "SECTION_NAME";
	public static final String BYOUTOU_NAME_COLUMN			= "BYOUTOU_NAME";
	public static final String BYOUSITU_NAME_COLUMN		= "BYOUSITU_NAME";

	/// <summary>
	/// publicコンストラクタ
	/// </summary>
	/// <remarks></remarks>
    public RisPatientInfoTbl()
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//
		this.tableNameStr = TableNameStr;

	}

    /*
	/// <summary>
	/// 患者情報を削除する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patientID">削除対象患者の患者ID</param>
	/// <returns></returns>
	public boolean DeletePatientData( Connection con, Transaction trans, String patientID )
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && patientID != null )
		{
			retFlg = Delete( con, trans, CreateDeleteSQL(patientID) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

    /*
	/// <summary>
	/// 患者移動情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patientBean">更新後の患者データ</param>
	/// <returns></returns>
	public boolean UpdatePatientTransportType( Connection con, Transaction trans, String patientID, String transportType )
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && patientID != null && transportType != null )
		{
			retFlg = Update( con, trans, CreateTransportTypeUpdateSQL( patientID, transportType ) );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

	/// <summary>
	/// 患者情報を更新する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patientBean">更新後の患者データ</param>
	/// <returns></returns>
	public boolean UpdatePatientData( Connection con, PatientInfoBean patientBean ) throws Exception
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && patientBean != null )
		{
			retFlg = Update( con, CreateUpdateSQL(patientBean), null );
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

    /*
	/// <summary>
	/// 患者情報を新規に登録する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patientBean">登録対象患者データ</param>
	/// <returns></returns>
	public boolean InsertPatientData( Connection con, Transaction trans, PatientInfoBean patientBean )
	{
		// parameters
		boolean retFlg = false;

		// begin log
		logger.debug("begin");

		if( con != null && trans != null && patientBean != null )
		{
			retFlg = Insert(con, trans, CreateNewInsertSQL(patientBean));
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/

    /*
	/// <summary>
	/// 患者検索Dlgで患者情報を検索する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patient">検索条件の患者情報</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable GetPatientData( Connection con, Transaction trans, PatientInfoBean bean )
	{
		// parameters
		DataTable dt = null;
		Command cmd = new Command();

		// begin log
		logger.debug("begin");

		if (con != null && trans != null && bean != null)
		{
			cmd = CreateSelectDtSQL(bean);
			cmd.Connection  = con;
			cmd.Transaction = trans;
			dt = Select(cmd, false);
			//dt = Select(con, trans, sql, false);

			if (dt != null)
			{
				// 不足カラム追加
				if (!dt.Columns.Contains(BIRTHDAY_STRING_COLUMN))
				{
					dt.Columns.Add(BIRTHDAY_STRING_COLUMN,typeof(String));
				}
				if (!dt.Columns.Contains(AGE_COLUMN))
				{
					dt.Columns.Add(AGE_COLUMN,typeof(String));
				}
				if (!dt.Columns.Contains(KANJA_NYUGAIKBN_NAME_COLUMN))
				{
					dt.Columns.Add(KANJA_NYUGAIKBN_NAME_COLUMN,typeof(String));
				}
				if (!dt.Columns.Contains(SECTION_NAME_COLUMN))
				{
					dt.Columns.Add(SECTION_NAME_COLUMN,typeof(String));
				}
				if (!dt.Columns.Contains(BYOUTOU_NAME_COLUMN))
				{
					dt.Columns.Add(BYOUTOU_NAME_COLUMN,typeof(String));
				}
				if (!dt.Columns.Contains(BYOUSITU_NAME_COLUMN))
				{
					dt.Columns.Add(BYOUSITU_NAME_COLUMN,typeof(String));
				}


				dt.TableName = TABLE_NAME;
			}

			bean.SetExecSql(cmd.CommandText + base.GetCmdSQLString(cmd));
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/

	/// <summary>
	/// 患者詳細情報を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <param name="patientID">取得対象患者の患者ID</param>
	/// <returns></returns>
	public PatientInfoBean GetPatientData( Connection con, String patientID ) throws Exception
	{
		// parameters
		DataTable dt = null;
		PatientInfoBean patientBean = null;

		// begin log
		logger.debug("begin");

		if( con != null && patientID != null )
		{
			dt = Select( con, CreateSelectSQL(patientID), null );
		}
		if( dt != null && dt.getRowCount() > 0 )
		{
			/* 一ノ瀬保留
			// 不足カラム追加
			if (!dt.Columns.Contains(BIRTHDAY_STRING_COLUMN))
			{
				dt.Columns.Add(BIRTHDAY_STRING_COLUMN,typeof(String));
			}
			if (!dt.Columns.Contains(AGE_COLUMN))
			{
				dt.Columns.Add(AGE_COLUMN,typeof(String));
			}
			if (!dt.Columns.Contains(KANJA_NYUGAIKBN_NAME_COLUMN))
			{
				dt.Columns.Add(KANJA_NYUGAIKBN_NAME_COLUMN,typeof(String));
			}
			if (!dt.Columns.Contains(SECTION_NAME_COLUMN))
			{
				dt.Columns.Add(SECTION_NAME_COLUMN,typeof(String));
			}
			if (!dt.Columns.Contains(BYOUTOU_NAME_COLUMN))
			{
				dt.Columns.Add(BYOUTOU_NAME_COLUMN,typeof(String));
			}
			if (!dt.Columns.Contains(BYOUSITU_NAME_COLUMN))
			{
				dt.Columns.Add(BYOUSITU_NAME_COLUMN,typeof(String));
			}
			*/

			patientBean = new PatientInfoBean();

			if( dt.getRows().get(0).get(ALLERGY_COLUMN) != null )
			{
				patientBean.SetAllergy(dt.getRows().get(0).get(ALLERGY_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(ALLERGYMARK_COLUMN) != null )
			{
				patientBean.SetAllergyMark(Integer.parseInt(dt.getRows().get(0).get(ALLERGYMARK_COLUMN).toString()));
			}
			if( dt.getRows().get(0).get(BIRTHDAY_COLUMN) != null )
			{
				//patientBean.SetBirthday(Integer.parseInt(dt.getRows().get(0).get(BIRTHDAY_COLUMN).toString()).toString("00000000"));
				patientBean.SetBirthday(String.format("%1$08d", Integer.parseInt(dt.getRows().get(0).get(BIRTHDAY_COLUMN).toString())));
			}
			if( dt.getRows().get(0).get(BLOOD_COLUMN) != null )
			{
				patientBean.SetBlood(dt.getRows().get(0).get(BLOOD_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(BYOUSITUID_COLUMN) != null )
			{
				patientBean.SetByousituID(dt.getRows().get(0).get(BYOUSITUID_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(BYOUTOUID_COLUMN) != null )
			{
				patientBean.SetByoutouID(dt.getRows().get(0).get(BYOUTOUID_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(CONTRADICTION_COLUMN) != null )
			{
				patientBean.SetContraindication(dt.getRows().get(0).get(CONTRADICTION_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(CONTRADICTIONMARK_COLUMN) != null )
			{
				patientBean.SetContraindicationMark(Integer.parseInt(dt.getRows().get(0).get(CONTRADICTIONMARK_COLUMN).toString()));
			}
			if( dt.getRows().get(0).get(DEATHDATE_COLUMN) != null )
			{
				patientBean.SetDeathDate((Timestamp)(dt.getRows().get(0).get(DEATHDATE_COLUMN)));
			}
			if( dt.getRows().get(0).get(EXAMDATA_COLUMN) != null )
			{
				patientBean.SetExamData(dt.getRows().get(0).get(EXAMDATA_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(EXTRA_PROFILE_COLUMN) != null )
			{
				patientBean.SetExtraProfile(dt.getRows().get(0).get(EXTRA_PROFILE_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(HANDICAPPED_COLUMN) != null )
			{
				patientBean.SetHandicap(dt.getRows().get(0).get(HANDICAPPED_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(HANDICAPPEDMARK_COLUMN) != null )
			{
				patientBean.SetHandicappedMark(Integer.parseInt(dt.getRows().get(0).get(HANDICAPPEDMARK_COLUMN).toString()));
			}
			if( dt.getRows().get(0).get(HIS_UPDATEDATE_COLUMN) != null )
			{
				patientBean.SetHisUpdate((Timestamp)(dt.getRows().get(0).get(HIS_UPDATEDATE_COLUMN)));
			}
			if( dt.getRows().get(0).get(INFECTION_COLUMN) != null )
			{
				patientBean.SetInfection(dt.getRows().get(0).get(INFECTION_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(INFECTIONMARK_COLUMN) != null )
			{
				patientBean.SetInfectionMark(Integer.parseInt(dt.getRows().get(0).get(INFECTIONMARK_COLUMN).toString()));
			}
			if( dt.getRows().get(0).get(JUSYO1_COLUMN) != null )
			{
				patientBean.SetJusyo1(dt.getRows().get(0).get(JUSYO1_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(JUSYO2_COLUMN) != null )
			{
				patientBean.SetJusyo2(dt.getRows().get(0).get(JUSYO2_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(JUSYO3_COLUMN) != null )
			{
				patientBean.SetJusyo3(dt.getRows().get(0).get(JUSYO3_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(KANASIMEI_COLUMN) != null )
			{
				patientBean.SetKanaSimei(dt.getRows().get(0).get(KANASIMEI_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(ROMASIMEI_COLUMN) != null )
			{
				patientBean.SetRomaSimei(dt.getRows().get(0).get(ROMASIMEI_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(KANJAID_COLUMN) != null )
			{
				patientBean.SetKanjaID(dt.getRows().get(0).get(KANJAID_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(KANJISIMEI_COLUMN) != null )
			{
				patientBean.SetKanjiSimei(dt.getRows().get(0).get(KANJISIMEI_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(NOTES_COLUMN) != null )
			{
				patientBean.SetNotes(dt.getRows().get(0).get(NOTES_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(NOTESMARK_COLUMN) != null )
			{
				patientBean.SetNotesMark(Integer.parseInt(dt.getRows().get(0).get(NOTESMARK_COLUMN).toString()));
			}
			if( dt.getRows().get(0).get(KANJA_NYUGAIKBN_COLUMN) != null )
			{
				patientBean.SetNyugaiKbn(dt.getRows().get(0).get(KANJA_NYUGAIKBN_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(PREGNANCY_COLUMN) != null )
			{
				patientBean.SetPregnancy(dt.getRows().get(0).get(PREGNANCY_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(PREGNANCYMARK_COLUMN) != null )
			{
				patientBean.SetPregnancyMark(Integer.parseInt(dt.getRows().get(0).get(PREGNANCYMARK_COLUMN).toString()));
			}
			if( dt.getRows().get(0).get(RIS_UPDATEDATE_COLUMN) != null )
			{
				patientBean.SetRisUpdate((Timestamp)(dt.getRows().get(0).get(RIS_UPDATEDATE_COLUMN)));
			}
			if( dt.getRows().get(0).get(SECTIONID_COLUMN) != null )
			{
				patientBean.SetSectionID(dt.getRows().get(0).get(SECTIONID_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(SEX_COLUMN) != null )
			{
				patientBean.SetSex(dt.getRows().get(0).get(SEX_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(TALL_COLUMN) != null )
			{
				patientBean.SetTall(dt.getRows().get(0).get(TALL_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(TRANSPORTTYPE_COLUMN) != null )
			{
				patientBean.SetTransportType(dt.getRows().get(0).get(TRANSPORTTYPE_COLUMN).toString());
			}
			if( dt.getRows().get(0).get(WEIGHT_COLUMN) != null )
			{
				patientBean.SetWeight(dt.getRows().get(0).get(WEIGHT_COLUMN).toString());
			}
			// 2010.07.30 Add T.Ootsuka Start
			if (dt.getRows().get(0).get(CREATININERESULT_COLUMN) != null)
			{
				patientBean.SetCreatinineresult(dt.getRows().get(0).get(CREATININERESULT_COLUMN).toString());
			}
			if (dt.getRows().get(0).get(EGFRRESULT_COLUMN) != null)
			{
				patientBean.SetEgfrresult(dt.getRows().get(0).get(EGFRRESULT_COLUMN).toString());
			}
			if (dt.getRows().get(0).get(CREATININEUPDATEDATE_COLUMN) != null)
			{
				patientBean.SetCreatinineUpdateDate((Timestamp)(dt.getRows().get(0).get(CREATININEUPDATEDATE_COLUMN)));
			}
			if (dt.getRows().get(0).get(EGFRUPDATEDATE_COLUMN) != null)
			{
				patientBean.SetEgfrresultUpdateDate((Timestamp)(dt.getRows().get(0).get(EGFRUPDATEDATE_COLUMN)));
			}
			// 2010.07.30 Add T.Ootsuka End
		}

		// end log
		logger.debug("end");

		return patientBean;
	}

    /*
	/// <summary>
	/// 患者情報検索SQL
	/// </summary>
	/// <param name="patient">検索条件</param>
	/// <returns>Select文</returns>
	private Command CreateSelectDtSQL(PatientInfoBean bean)
	{

		// start log
		logger.debug("begin");

		Command cmd = new Command();

		if (bean != null)
		{
			keys.clear();
			inList.clear();

			//前後に"%"を付加する
			String idStr = bean.GetKanjaID();
			if (idStr.length() > 0)
			{
				idStr.Replace("%", "");
				idStr = "%" + idStr + "%";
			}

			String kanaStr = bean.GetKanaSimei();
			if (kanaStr.length() > 0)
			{
				kanaStr.Replace("%", "");
				kanaStr = "%" + kanaStr + "%";
			}

			//検索フラグ判断
			if (bean.GetSearchFlg())
			{
				idStr	= bean.GetKanjaID();
				kanaStr	= bean.GetKanaSimei();
			}

			if (idStr.length() > 0)
			{
				this.AddColSetValue(KANJAID_COLUMN, ":" + KANJAID_COLUMN, true, SignType.Like);
			}
			if (kanaStr.length() > 0)
			{
				this.AddColSetValue(KANASIMEI_COLUMN, ":" + KANASIMEI_COLUMN, true, SignType.Like);
			}

			this.ClearOrderKey();

			//検索フラグ判断
			if (!bean.GetSearchFlg())
			{
				this.AddOrderKeyAsc(KANJAID_COLUMN); //第一キー昇順
			}
			else
			{
				this.AddOrderKeyAsc(KANASIMEI_COLUMN); //第一キー昇順
				this.AddOrderKeyAsc(KANJAID_COLUMN);   //第二キー昇順
			}

			cmd.CommandText = this.GetSelectBindsSQL(TABLE_NAME);

			if (idStr.length() > 0)
			{
				Parameter oraParam = new Parameter(KANJAID_COLUMN, SqlUtil.EscapeSelectSQL(idStr));
				cmd.Parameters.Add(oraParam);
				//cmd.Parameters.Add(KANJAID_COLUMN, .DataAccess.Client.DbType.Varchar2, SqlUtil.EscapeSelectSQL(idStr), ParameterDirection.Input);
			}
			if (kanaStr.length() > 0)
			{
				Parameter oraParam = new Parameter(KANASIMEI_COLUMN, SqlUtil.EscapeSelectSQL(kanaStr));
				cmd.Parameters.Add(oraParam);
				//cmd.Parameters.Add(KANASIMEI_COLUMN, .DataAccess.Client.DbType.Varchar2, SqlUtil.EscapeSelectSQL(kanaStr), ParameterDirection.Input);
			}
		}
		else
		{
			cmd.CommandText = this.GetSelectSQL();
		}

		// end log
		logger.debug("end");

		return cmd;

	}
	*/

    /*
	/// <summary>
	/// INSERT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="bui">INSERT対象の部位データ</param>
	/// <returns>INSERT用のSQL文</returns>
	private String CreateNewInsertSQL(PatientInfoBean bean)
	{
		logger.debug("begin");

		this.keys.clear();

		this.AddColValue(KANJAID_COLUMN, bean.GetKanjaID());
		//
		this.AddColValue(KANJISIMEI_COLUMN,			bean.GetKanjiSimei());
		this.AddColValue(ROMASIMEI_COLUMN,			bean.GetRomaSimei());
		this.AddColValue(KANASIMEI_COLUMN,			bean.GetKanaSimei());
		this.AddColValue(BIRTHDAY_COLUMN,			bean.GetBirthday());
		this.AddColValue(SEX_COLUMN,				bean.GetSex());
		this.AddColValue(JUSYO1_COLUMN,				bean.GetJusyo1());
		this.AddColValue(JUSYO2_COLUMN,				bean.GetJusyo2());
		this.AddColValue(JUSYO3_COLUMN,				bean.GetJusyo3());
		this.AddColValue(KANJA_NYUGAIKBN_COLUMN,	bean.GetNyugaiKbn());
		this.AddColValue(SECTIONID_COLUMN,			bean.GetSectionID());
		this.AddColValue(BYOUTOUID_COLUMN,			bean.GetByoutouID());
		this.AddColValue(BYOUSITUID_COLUMN,			bean.GetByousituID());
		this.AddColValue(TALL_COLUMN,				bean.GetTall());
		this.AddColValue(WEIGHT_COLUMN,				bean.GetWeight());

		logger.debug("end");

		return this.GetInsertSQL();

	}
	*/

    /*
	private String CreateTransportTypeUpdateSQL( String patientID, String transportType )
	{
		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		// start log
		logger.debug("begin");

		if( patientID != null && transportType != null )
		{
			sqlStrBuild.append("UPDATE " + tableNameStr + " set ");

			// TRANSPORTTYPE
			sqlStrBuild.append( TRANSPORTTYPE_COLUMN + " = '" + SqlUtil.EscapeInsertSQL(transportType) + "'" );
			sqlStrBuild.append(", ");
			// RIS_UPDATEDATE
			sqlStrBuild.append( RIS_UPDATEDATE_COLUMN + " = SYSDATE");

			// WHERE節 for KANJAID
			sqlStrBuild.append( " where " + KANJAID_COLUMN + "= '" + SqlUtil.EscapeInsertSQL(patientID) + "'" );
		}

		// end log
		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/// <summary>
	/// UPDATE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="patient">UPDATE対象の患者データ</param>
	/// <returns>UPDATE用のSQL文</returns>
	private String CreateUpdateSQL( PatientInfoBean patient )
	{
		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		// start log
		logger.debug("begin");

		if( patient != null )
		{
			sqlStrBuild.append("UPDATE " + tableNameStr + " set ");

			// TRANSPORTTYPE
			sqlStrBuild.append( TRANSPORTTYPE_COLUMN + " = '" + SqlUtil.EscapeInsertSQL(patient.GetTransportType()) + "'" );
			sqlStrBuild.append(", ");
			// HANDICAPPEDMARK
			sqlStrBuild.append( HANDICAPPEDMARK_COLUMN + " ='" + patient.GetHandicappedMark() + "'" );
			sqlStrBuild.append(", ");
			// HANDICAPPED
			sqlStrBuild.append( HANDICAPPED_COLUMN + " = '" + SqlUtil.EscapeInsertSQL(patient.GetHandicap()) + "'" );
			sqlStrBuild.append(", ");
			// NOTESMARK
			sqlStrBuild.append( NOTESMARK_COLUMN + " = '" + patient.GetNotesMark() + "'" );
			sqlStrBuild.append(", ");
			// NOTES
			sqlStrBuild.append( NOTES_COLUMN + " = '" + SqlUtil.EscapeInsertSQL(patient.GetNotes()) + "'" );
			sqlStrBuild.append(", ");

			// RIS_UPDATEDATE
			sqlStrBuild.append( RIS_UPDATEDATE_COLUMN + " = SYSDATE");

			// WHERE節 for KANJAID
			sqlStrBuild.append( " where " + KANJAID_COLUMN + "= '" + SqlUtil.EscapeInsertSQL(patient.GetKanjaID()) + "'" );
		}

		// end log
		logger.debug("end");

		return sqlStrBuild.toString();
	}

    /*
	/// <summary>
	/// DELETE用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="patientIDStr">削除対象患者の患者ID</param>
	/// <returns>DELETE用のSQL文</returns>
	private String CreateDeleteSQL( String patientIDStr )
	{
		// parameters
		StringBuilder sqlStrBuild = new StringBuilder("");

		// start log
		logger.debug("begin");

		if( patientIDStr != null )
		{
			sqlStrBuild.append("DELETE from " + tableNameStr + " where " + KANJAID_COLUMN + " = '" + SqlUtil.EscapeInsertSQL(patientIDStr) + "'");
		}

		// end log
		logger.debug("end");

		return sqlStrBuild.toString();
	}
	*/

	/// <summary>
	/// SELECT用のSQL文を作成する
	/// </summary>
	/// <remarks></remarks>
	/// <param name="patientIDStr">SELECT対象患者の患者ID</param>
	/// <returns>SELECT用のSQL文</returns>
	private String CreateSelectSQL( String patientIDStr )
	{
		logger.debug("begin");

		this.keys.clear();
		this.inList.clear();

		this.AddColValue(KANJAID_COLUMN, patientIDStr, true, SignType.Equal);

		logger.debug("end");

		return this.GetSelectSQL();
	}

    /*
	/// <summary>
	///	帳票用-患者情報の取得
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="param">一覧検索条件</param>
	/// <returns>ステータス名</returns>
	public DataTable GetPrintPatientDataTable(Connection con, Transaction trans, OrderSearchParameter param)
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
    //	sqlStrBuild.append(" SELECT /*+ INDEX(PI) INDEX(OM) INDEX(EO) INDEX(EM) */ DISTINCT PI.* FROM ");
    //	// 元の処理
    //	//sqlStrBuild.append(" SELECT /*+ RULE */ DISTINCT PI.* FROM ");	//2010.11.08 Mod H.Orikasa
    //	////sqlStrBuild.append(" SELECT DISTINCT PI.* FROM ");				//
    //
    //	// 2011.08.22 Mod T.Nishikubo@MERX End
    //	sqlStrBuild.append(TABLE_NAME + " PI");
    //	sqlStrBuild.append(", ORDERMAINTABLE OM, EXTENDORDERINFO EO ");
    //	// 2011.06.24 Add K.Shinohara Start A0060
    //	sqlStrBuild.append(", EXMAINTABLE EM ");
    //	// 2011.06.24 Add K.Shinohara End
    //	sqlStrBuild.append(" WHERE ");
    //	sqlStrBuild.append(" OM.RIS_ID = EO.RIS_ID AND");
    //	sqlStrBuild.append(" PI.KANJA_ID = OM.KANJA_ID ");
    //	CommonUtil.AddPrintWhereString(sqlStrBuild, param);
    //
    //	logger.debug("end");
    //
    //	return sqlStrBuild.toString();
    //}

}
