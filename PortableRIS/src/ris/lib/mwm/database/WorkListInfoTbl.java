package ris.lib.mwm.database;

import java.sql.Connection;
import java.util.ArrayList;

import ris.lib.mwm.bean.MWMInfoBean;
import ris.portable.util.DataTable;

	/// <summary>
	/// WorkListInfoTbl の概要の説明です。
    ///
    /// Copyright (C) 2009, Yokogawa Electric Corpration
    ///
    ///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
    /// V1.00.00	: 2009.03.29	: 112478 / A.Kobayashi		: original
    ///
    /// </summary>
	public class WorkListInfoTbl extends BaseTbl
	{
		// アクセステーブル名
		public  final String TABLE_NAME = "WORKLISTINFO";
		public  final String TABLE_CAPTION = "MWM情報";

		// カラム名
		private  final String SCH_STATION_AE_TITLE_COLUMN 				= "SCH_STATION_AE_TITLE";
		private  final String SCH_PROC_STEP_LOCATION_COLUMN 				= "SCH_PROC_STEP_LOCATION";
		private  final String SCH_PROC_STEP_START_DATE_COLUMN 			= "SCH_PROC_STEP_START_DATE";
		private  final String SCH_PROC_STEP_START_TIME_COLUMN 			= "SCH_PROC_STEP_START_TIME";
		private  final String SCH_PERF_PHYSICIANS_NAME_ROMA_COLUMN 		= "SCH_PERF_PHYSICIANS_NAME_ROMA";
		private  final String SCH_PERF_PHYSICIANS_NAME_KANJI_COLUMN 		= "SCH_PERF_PHYSICIANS_NAME_KANJI";
		private  final String SCH_PERF_PHYSICIANS_NAME_KANA_COLUMN 		= "SCH_PERF_PHYSICIANS_NAME_KANA";
		private  final String SCH_PROC_STEP_DESCRIPTION_COLUMN 			= "SCH_PROC_STEP_DESCRIPTION";
		private  final String SCH_PROC_STEP_ID_COLUMN 					= "SCH_PROC_STEP_ID";
		private  final String COMMENTS_ON_THE_SCH_PROC_STEP_COLUMN 		= "COMMENTS_ON_THE_SCH_PROC_STEP";
		private  final String MODALITY_COLUMN 							= "MODALITY";
		private  final String REQ_PROC_ID_COLUMN 						= "REQ_PROC_ID";
		private  final String STUDY_INSTANCE_UID_COLUMN 					= "STUDY_INSTANCE_UID";
		private  final String REQ_PROC_DESCRIPTION_COLUMN 				= "REQ_PROC_DESCRIPTION";
		private  final String REQUESTING_PHYSICIAN_COLUMN 				= "REQUESTING_PHYSICIAN";
		private  final String REQUESTING_SERVICE_COLUMN 					= "REQUESTING_SERVICE";
		private  final String ACCESSION_NUMBER_COLUMN 					= "ACCESSION_NUMBER";
		private  final String INSTITUTION_NAME_COLUMN 					= "INSTITUTION_NAME";
		private  final String INSTITUTION_ADDRESS_COLUMN 				= "INSTITUTION_ADDRESS";
		private  final String PATIENT_NAME_ROMA_COLUMN 					= "PATIENT_NAME_ROMA";
		private  final String PATIENT_NAME_KANJI_COLUMN 					= "PATIENT_NAME_KANJI";
		private  final String PATIENT_NAME_KANA_COLUMN 					= "PATIENT_NAME_KANA";
		private  final String PATIENT_ID_COLUMN 							= "PATIENT_ID";
		private  final String PATIENT_BIRTH_DATE_COLUMN 					= "PATIENT_BIRTH_DATE";
		private  final String PATIENT_SEX_COLUMN 						= "PATIENT_SEX";
		private  final String PATIENT_WEIGHT_COLUMN 						= "PATIENT_WEIGHT";
		private  final String SCH_ACTION_CODES_COLUMN 					= "SCH_ACTION_CODES";
		private  final String SCH_ACTION_DESIGNATOR_COLUMN 				= "SCH_ACTION_DESIGNATOR";
		private  final String SCH_ACTION_VERSION_COLUMN 					= "SCH_ACTION_VERSION";
		private  final String SCH_ACTION_MEANINGS_COLUMN 				= "SCH_ACTION_MEANINGS";
		private  final String REF_STUDY_CLASS_UID_COLUMN 				= "REF_STUDY_CLASS_UID";
		private  final String REF_STUDY_INSTANCE_UID_COLUMN 				= "REF_STUDY_INSTANCE_UID";
		private  final String REF_PHYSICIAN_NAME_COLUMN 					= "REF_PHYSICIAN_NAME";
		private  final String PATIENT_RESIDENCE_COLUMN 					= "PATIENT_RESIDENCE";
		private  final String PATIENT_SIZE_COLUMN 						= "PATIENT_SIZE";
		private  final String PATIENT_COMMENTS_COLUMN 					= "PATIENT_COMMENTS";
		private  final String REQ_PROCEDURE_CODES_COLUMN 				= "REQ_PROCEDURE_CODES";
		private  final String REQ_PROCEDURE_DESIGNATOR_COLUMN 			= "REQ_PROCEDURE_DESIGNATOR";
		private  final String REQ_PROCEDURE_VERSION_COLUMN 				= "REQ_PROCEDURE_VERSION";
		private  final String REQ_PROCEDURE_MEANINGS_COLUMN 				= "REQ_PROCEDURE_MEANINGS";
		private  final String NUMBEROFCOPIES_COLUMN 						= "NUMBEROFCOPIES";

		/// <summary>
		/// コンストラクタ
		/// </summary>
		public WorkListInfoTbl()
		{
			this.tableNameStr = TABLE_NAME;
			this.infoCaption  = TABLE_CAPTION;
		}

		/// <summary>
		/// MWM情報を取得する
		/// </summary>
		/// <param name="con">データベース接続オブジェクト</param>
		/// <param name="trans">DBトランザクションオブジェクト</param>
		/// <param name="studyID">Study_Instance_UID</param>
		/// <param name="logList">ログリスト</param>
		/// <returns></returns>
		public DataTable SelectMWMData(Connection con, String studyID, ArrayList logList) throws Exception
		{
			// parameters
			DataTable retDt = null;

			// begin log
			logList.add("WorkListInfoTbl.SelectMWMData-begin");

			if (con != null && studyID.length() > 0)
			{
				String sqlStr = CreateSelectSQL(studyID);
				retDt = Select(con, sqlStr, null, logList);
			}

			// end log
			logList.add("WorkListInfoTbl.SelectMWMData-end");

			return retDt;
		}


		/// <summary>
		/// MWM情報を新規に登録する
		/// </summary>
		/// <param name="con">データベース接続オブジェクト</param>
		/// <param name="trans">DBトランザクションオブジェクト</param>
		/// <param name="bean">MWM情報データ</param>
		/// <param name="logList">ログリスト</param>
		/// <returns></returns>
		public boolean InsertMWMData(Connection con, MWMInfoBean bean, ArrayList logList) throws Exception
		{
			// parameters
			boolean retFlg = false;

			// begin log
			logList.add("WorkListInfoTbl.InsertMWMData-begin");

			if( con != null && bean != null)
			{
				String sqlStr = CreateInsertSQL(bean);
				retFlg = Insert( con, sqlStr, null, logList );
			}

			// end log
			logList.add("WorkListInfoTbl.InsertMWMData-end");

			return retFlg;
		}

		/// <summary>
		/// MWM情報をAETitleで削除する
		/// </summary>
		/// <param name="con">データベース接続オブジェクト</param>
		/// <param name="trans">DBトランザクションオブジェクト</param>
		/// <param name="bean">MWM情報データ</param>
		/// <param name="logList">ログリスト</param>
		/// <returns></returns>
		public boolean DeleteMWMDataByAETitle(Connection con, MWMInfoBean bean, ArrayList logList) throws Exception
		{
			// parameters
			boolean retFlg = false;

			// begin log
			logList.add("WorkListInfoTbl.DeleteMWMDataByAETitle-begin");

			if( con != null && bean != null)
			{
				String sqlStr = CreateDeleteSQLByAETitle(bean);
				retFlg = ForceDelete( con, sqlStr, null, logList );
			}

			// end log
			logList.add("WorkListInfoTbl.DeleteMWMDataByAETitle-end");

			return retFlg;
		}

		/// <summary>
		/// MWM情報をACNOで削除する
		/// </summary>
		/// <param name="con">データベース接続オブジェクト</param>
		/// <param name="trans">DBトランザクションオブジェクト</param>
		/// <param name="bean">MWM情報データ</param>
		/// <param name="logList">ログリスト</param>
		/// <returns></returns>
		public boolean DeleteMWMDataByAccNo(Connection con, MWMInfoBean bean, ArrayList logList) throws Exception
		{
			// parameters
			boolean retFlg = false;

			// begin log
			logList.add("WorkListInfoTbl.DeleteMWMDataByAccNo-begin");

			if( con != null && bean != null)
			{
				String sqlStr = CreateDeleteSQLByAccNo(bean);
				retFlg = ForceDelete( con, sqlStr, null, logList );
			}

			// end log
			logList.add("WorkListInfoTbl.DeleteMWMDataByAccNo-end");

			return retFlg;
		}

		/// <summary>
		/// セレクトSQL
		/// </summary>
		/// <param name="studyID">Study_Instance_UID</param>
		/// <returns></returns>
		private String CreateSelectSQL(String studyID)
		{
			this.keys.clear();
			this.AddColValue(STUDY_INSTANCE_UID_COLUMN, studyID, true);

			return this.GetSelectSQL();
		}

		/// <summary>
		/// インサートSQL
		/// </summary>
		/// <param name="bean">MWM情報データ</param>
		/// <returns></returns>
		private String CreateInsertSQL( MWMInfoBean bean )
		{
			SetInfoValue(bean);

			return this.GetInsertSQL();
		}

		/// <summary>
		/// デリートSQL(ACCESSION_NUMBER)
		/// </summary>
		/// <param name="bean">MWM情報データ</param>
		/// <returns></returns>
		private String CreateDeleteSQLByAccNo( MWMInfoBean bean )
		{
			this.keys.clear();
			this.AddColValue(ACCESSION_NUMBER_COLUMN, bean.GetAccessionNumber(), true);

			return this.GetDeleteSQL();
		}

		/// <summary>
		/// デリートSQL(SCH_STATION_AE_TITLE)
		/// </summary>
		/// <param name="bean">MWM情報データ</param>
		/// <returns></returns>
		private String CreateDeleteSQLByAETitle( MWMInfoBean bean )
		{
			this.keys.clear();

			this.keys.clear();
			this.AddColValue(SCH_STATION_AE_TITLE_COLUMN , bean.GetSchStationAeTitle(), true);

			return this.GetDeleteSQL();

			//StringBuilder sql = new StringBuilder(256);
			//sql.append("delete ");
			//sql.append(TABLE_NAME);
			//sql.append(" where ");
			//sql.append(" ( ");
			//sql.append(ACCESSION_NUMBER_COLUMN);
			//sql.append(" = '");
			//sql.append(bean.GetAccessionNumber());
			//sql.append("'");
			//sql.append(" ) or ( ");
			//sql.append(SCH_STATION_AE_TITLE_COLUMN);
			//sql.append(" = '");
			//sql.append(bean.GetSchStationAeTitle());
			//sql.append("')");

			//return sql.toString();
		}

		/// <summary>
		/// デリートSQL
		/// </summary>
		/// <param name="bean">MWM情報データ</param>
		/// <returns></returns>
		private String CreateSameDeleteSQL( MWMInfoBean bean )
		{
			//logger.debug("begin");

			this.keys.clear();
			this.AddColValue(SCH_STATION_AE_TITLE_COLUMN,		bean.GetSchStationAeTitle(), true);
			this.AddColValue(SCH_PROC_STEP_START_DATE_COLUMN,	bean.GetSchProcStepStartDate(), true, SignType.Under);
			this.AddColValue(SCH_PROC_STEP_START_DATE_COLUMN,	bean.GetSchProcStepStartDate(), true, SignType.Over);
			this.AddColValue(PATIENT_ID_COLUMN,					bean.GetPatientID(), true);

			//logger.debug("end");

			return this.GetDeleteSQL();
		}

		/// <summary>
		/// カラム値設定
		/// </summary>
		/// <param name="bean"></param>
		private void SetInfoValue( MWMInfoBean bean )
		{
			//logger.debug("begin");

			this.keys.clear();
			this.AddColValue(SCH_STATION_AE_TITLE_COLUMN,				bean.GetSchStationAeTitle() );
			this.AddColValue(SCH_PROC_STEP_LOCATION_COLUMN,				bean.GetSchProcStepLocation() );
			this.AddColValue(SCH_PROC_STEP_START_DATE_COLUMN,			bean.GetSchProcStepStartDate() );
			this.AddColValue(SCH_PROC_STEP_START_TIME_COLUMN,			bean.GetSchProcStepStartTime() );
			this.AddColValue(SCH_PERF_PHYSICIANS_NAME_ROMA_COLUMN,		bean.GetSchPerfPhysiciansNameRoma() );
			this.AddColValue(SCH_PERF_PHYSICIANS_NAME_KANJI_COLUMN,		bean.GetSchPerfPhysiciansNameKanji() );
			this.AddColValue(SCH_PERF_PHYSICIANS_NAME_KANA_COLUMN,		bean.GetSchPerfPhysiciansNameKana() );
			this.AddColValue(SCH_PROC_STEP_DESCRIPTION_COLUMN,			bean.GetSchProcStepDescription() );
			this.AddColValue(SCH_PROC_STEP_ID_COLUMN,					bean.GetSchProcStepID() );
			this.AddColValue(COMMENTS_ON_THE_SCH_PROC_STEP_COLUMN,		bean.GetCommentsOnTheSchProcStep() );
			this.AddColValue(MODALITY_COLUMN,							bean.GetModality() );
			this.AddColValue(REQ_PROC_ID_COLUMN,						bean.GetReqProcID() );
			this.AddColValue(STUDY_INSTANCE_UID_COLUMN,					bean.GetStudyInstanceUID() );
			this.AddColValue(REQ_PROC_DESCRIPTION_COLUMN,				bean.GetReqProcDescription() );
			this.AddColValue(REQUESTING_PHYSICIAN_COLUMN, 				bean.GetRequestingPhysician() );
			this.AddColValue(REQUESTING_SERVICE_COLUMN,					bean.GetRequestingService() );
			this.AddColValue(ACCESSION_NUMBER_COLUMN,					bean.GetAccessionNumber() );
			this.AddColValue(INSTITUTION_NAME_COLUMN,					bean.GetInstitutionName() );
			this.AddColValue(INSTITUTION_ADDRESS_COLUMN,				bean.GetInstitutionAddress() );
			this.AddColValue(PATIENT_NAME_ROMA_COLUMN,					bean.GetPatientNameRoma() );
			this.AddColValue(PATIENT_NAME_KANJI_COLUMN,					bean.GetPatientNameKanji() );
			this.AddColValue(PATIENT_NAME_KANA_COLUMN,					bean.GetPatientNameKana() );
			this.AddColValue(PATIENT_ID_COLUMN,							bean.GetPatientID() );
			this.AddColValue(PATIENT_BIRTH_DATE_COLUMN,					bean.GetPatientBirthDate() );
			this.AddColValue(PATIENT_SEX_COLUMN,						bean.GetPatientSex() );
			this.AddColValue(PATIENT_WEIGHT_COLUMN,						bean.GetPatientWeight() );
			this.AddColValue(SCH_ACTION_CODES_COLUMN,					bean.GetSchActionCodes() );
			this.AddColValue(SCH_ACTION_DESIGNATOR_COLUMN,				bean.GetSchActionDesignator() );
			this.AddColValue(SCH_ACTION_VERSION_COLUMN,					bean.GetSchActionVersion() );
			this.AddColValue(SCH_ACTION_MEANINGS_COLUMN,				bean.GetSchActionMeanings() );
			this.AddColValue(REF_STUDY_CLASS_UID_COLUMN,				bean.GetRefStudyClassUID() );
			this.AddColValue(REF_STUDY_INSTANCE_UID_COLUMN,				bean.GetRefStudyInstanceUID() );
			this.AddColValue(REF_PHYSICIAN_NAME_COLUMN,					bean.GetRefPhysicianName() );
			this.AddColValue(PATIENT_RESIDENCE_COLUMN,					bean.GetPatientResidence() );
			this.AddColValue(PATIENT_SIZE_COLUMN,						bean.GetPatientSize() );
			this.AddColValue(PATIENT_COMMENTS_COLUMN,					bean.GetPatientComments() );
			this.AddColValue(REQ_PROCEDURE_CODES_COLUMN,				bean.GetReqProcedureCodes() );
			this.AddColValue(REQ_PROCEDURE_DESIGNATOR_COLUMN,			bean.GetReqProcedureDesignator() );
			this.AddColValue(REQ_PROCEDURE_VERSION_COLUMN,				bean.GetReqProcedureVersion() );
			this.AddColValue(REQ_PROCEDURE_MEANINGS_COLUMN,				bean.GetReqProcedureMeanings() );
			this.AddColValue(NUMBEROFCOPIES_COLUMN,						bean.GetNumberOfCopies());

			//logger.debug("end");
		}
	}
