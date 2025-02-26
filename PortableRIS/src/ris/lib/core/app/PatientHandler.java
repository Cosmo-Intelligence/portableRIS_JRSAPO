package ris.lib.core.app;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.Configuration;
import ris.lib.core.bean.PatientInfoBean;
import ris.lib.core.database.MasterInfoTbl;
import ris.lib.core.database.RisPatientCommentTbl;
import ris.lib.core.database.RisPatientInfoTbl;
import ris.lib.core.database.RisPatientResultsInfoTbl;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.MessageParameter;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
///
/// 実績データ管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.03.05	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class PatientHandler
{

	public static Log logger = LogFactory.getLog(PatientHandler.class);

	/// <summary>
	/// 実績患者情報と患者情報の取得
	/// </summary>
	/// <param name="risID"></param>
	/// <param name="status"></param>
	/// <returns></returns>
	public PatientInfoBean GetRisResultsPatientData( String risID, String patientID, Connection con ) throws Exception
	{
		// parameters
		PatientInfoBean patientInfoBean = null;
		PatientInfoBean nowPatientInfoBean = null;

		RisPatientResultsInfoTbl risPatientResultsInfoTbl = new RisPatientResultsInfoTbl();
		RisPatientInfoTbl risPatientInfoTbl = new RisPatientInfoTbl();
		RisPatientCommentTbl risPatientCommentTbl = new RisPatientCommentTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if( con != null && patientID != null )
			{
				//実績患者情報
				patientInfoBean = risPatientResultsInfoTbl.GetPatientResultData(con, risID);

				//患者情報の取得
				nowPatientInfoBean = risPatientInfoTbl.GetPatientData(con, patientID);
				if (patientInfoBean == null)
				{
					patientInfoBean = nowPatientInfoBean;
				}
				else
				{
					//実績患者に存在しない情報を患者情報より取得する
					patientInfoBean.SetJusyo1(nowPatientInfoBean.GetJusyo1());
					patientInfoBean.SetJusyo2(nowPatientInfoBean.GetJusyo2());
					patientInfoBean.SetJusyo3(nowPatientInfoBean.GetJusyo3());
					patientInfoBean.SetRisUpdate(nowPatientInfoBean.GetRisUpdate());
					patientInfoBean.SetHisUpdate(nowPatientInfoBean.GetHisUpdate());
				}

				if (patientInfoBean != null)
				{
					//表示加工用のマスタを取得
					MasterUtil mUtil = new MasterUtil();
					MasterInfoTbl masterInfoTbl = new MasterInfoTbl();
					DataTable codeDt = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_CODECONVERT, false);			//項目変換マスタ
					DataTable sectionDt = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_SECTIONMASTER, false);			//依頼科マスタ
					DataTable byoutouDt = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_BYOUTOUMASTER, false);			//病棟マスタ
					DataTable byousituDt = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_BYOUSITUMASTER, false);			//病室マスタ

					//年齢
					String age = patientInfoBean.GetPatientAgeDate(patientInfoBean.GetBirthday(), Configuration.GetInstance().GetSysDate(con));
					patientInfoBean.SetAge(age);

					//入外区分変換
					String nyugaiKbnName = ConvertNyugaiKbn(patientInfoBean.GetNyugaiKbn(), codeDt, con);
					patientInfoBean.SetNyugaiKbnName(nyugaiKbnName);

					//依頼科
					String sectionName = ConvertSection(patientInfoBean.GetSectionID(), sectionDt);
					patientInfoBean.SetSectionName(sectionName);

					//病棟
					String byoutouName = ConvertByoutou(patientInfoBean.GetByoutouID(), byoutouDt);
					patientInfoBean.SetByoutouName(byoutouName);

					//病室
					String byousituName = ConvertByousitu(patientInfoBean.GetByousituID(), byousituDt);
					patientInfoBean.SetByousituName(byousituName);

					//患者コメントを取得設定する
					ArrayList commentList = risPatientCommentTbl.GetPatientCommentAll(con, patientID);
					patientInfoBean.ReconstructCommentList(commentList);

					//患者コメント(共通)の設定
					if (commentList != null && commentList.size() > 0)
					{
						for (int i = 0; i < commentList.size(); i++)
						{
							DataRow row = (DataRow)commentList.get(i);

							if (MasterUtil.RIS_PATIENTCOMMENT_DEF.equals(row.get(RisPatientCommentTbl.PATIENTKENSATYPE_COLUMN).toString()))
							{
								patientInfoBean.SetComment(row.get(RisPatientCommentTbl.PATIENTCOMMENT_COLUMN).toString());
								break;
							}
						}
					}
				}

				// すべて成功した場合はCOMMIT
				con.commit();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);

			// 失敗した場合はROLLBACK
			try
			{
				con.rollback();
			}
			catch( Exception e1 )
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1 );
			}
			throw ex;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return patientInfoBean;
	}

	/// <summary>
	/// 患者情報を取得する
	/// </summary>
	/// <param name="risID"></param>
	/// <param name="status"></param>
	/// <returns></returns>
	public PatientInfoBean GetRisPatientData( String patientID, Connection con ) throws Exception
	{
		// parameters
		PatientInfoBean patientInfoBean = null;

		RisPatientInfoTbl risPatientInfoTbl = new RisPatientInfoTbl();
		RisPatientCommentTbl risPatientCommentTbl = new RisPatientCommentTbl();
		// begin log
		logger.debug("begin");

		try
		{
			if( con != null && patientID != null )
			{
				//患者情報の取得
				patientInfoBean = risPatientInfoTbl.GetPatientData(con, patientID);

				if (patientInfoBean != null)
				{
					//表示加工用のマスタを取得
					MasterUtil mUtil = new MasterUtil();
					MasterInfoTbl masterInfoTbl = new MasterInfoTbl();
					DataTable codeDt		= masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_CODECONVERT, false);			//項目変換マスタ
					DataTable sectionDt		= masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_SECTIONMASTER, false);			//依頼科マスタ
					DataTable byoutouDt		= masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_BYOUTOUMASTER, false);			//病棟マスタ
					DataTable byousituDt	= masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_BYOUSITUMASTER, false);			//病室マスタ

					//年齢
					String age = patientInfoBean.GetPatientAgeDate(patientInfoBean.GetBirthday(), Configuration.GetInstance().GetSysDate(con));
					//String age = ConvertAgeStr(patientInfoBean.GetBirthday());
					patientInfoBean.SetAge(age);

					//入外区分変換
					String nyugaiKbnName = ConvertNyugaiKbn(patientInfoBean.GetNyugaiKbn(), codeDt, con);
					patientInfoBean.SetNyugaiKbnName(nyugaiKbnName);

					//依頼科
					String sectionName = ConvertSection(patientInfoBean.GetSectionID(), sectionDt);
					patientInfoBean.SetSectionName(sectionName);

					//病棟
					String byoutouName = ConvertByoutou(patientInfoBean.GetByoutouID(), byoutouDt);
					patientInfoBean.SetByoutouName(byoutouName);

					//病室
					String byousituName = ConvertByousitu(patientInfoBean.GetByousituID(), byousituDt);
					patientInfoBean.SetByousituName(byousituName);

					//患者コメントを取得設定する
					ArrayList commentList = risPatientCommentTbl.GetPatientCommentAll(con, patientID);
					patientInfoBean.ReconstructCommentList(commentList);

					//患者コメント(共通)の設定
					if (commentList != null && commentList.size() > 0)
					{
						for (int i=0; i<commentList.size(); i++)
						{
							DataRow row = (DataRow)commentList.get(i);

							if (MasterUtil.RIS_PATIENTCOMMENT_DEF.equals(row.get(RisPatientCommentTbl.PATIENTKENSATYPE_COLUMN).toString()))
							{
								patientInfoBean.SetComment(row.get(RisPatientCommentTbl.PATIENTCOMMENT_COLUMN).toString());
								break;
							}
						}
					}
				}

				// すべて成功した場合はCOMMIT
				con.commit();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);

			// 失敗した場合はROLLBACK
			try
			{
				con.rollback();
			}
			catch( Exception e1 )
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1 );
			}
			throw ex;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return patientInfoBean;
	}

	/*
	/// <summary>
	/// 患者情報を検索する
	/// </summary>
	/// <param name="patient">患者情報</param>
	/// <returns>患者検索結果一覧</returns>
	public DataTable GetRisPatientData(PatientInfoBean bean)
	{
		// parameters
		DataTable dt = null;

		Connection con = null;
		Transaction transaction = null;
		RisPatientInfoTbl risPatientInfoTbl = new RisPatientInfoTbl();
		PatientInfoBean patientBean = new PatientInfoBean();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && bean != null)
			{
				//患者情報の取得
				if (dt == null)
				{
					dt = risPatientInfoTbl.GetPatientData(con, transaction, bean);
				}

				//表示加工用のマスタを取得
				MasterUtil mUtil = new MasterUtil();
				MasterInfoTbl masterInfoTbl = new MasterInfoTbl();
				DataTable codeDt		= masterInfoTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_CODECONVERT, false);			//項目変換マスタ
				DataTable sectionDt		= masterInfoTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_SECTIONMASTER, false);			//依頼科マスタ
				DataTable byoutouDt		= masterInfoTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_BYOUTOUMASTER, false);			//病棟マスタ
				DataTable byousituDt	= masterInfoTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_BYOUSITUMASTER, false);			//病室マスタ
				Timestamp  sysTimestamp	= Configuration.GetInstance().GetSysDate(con, transaction);

				//表示加工を行う
				if (dt != null)
				{
					int rowCountInt = dt.Rows.Count;
					// 1件毎の処理
					for (int i = 0; i < rowCountInt; i++)
					{
						// 現在のレコード
						DataRow curRow = dt.Rows[i];

						//生年月日
						String birtyday = curRow[RisPatientInfoTbl.BIRTHDAY_COLUMN].toString();
						curRow[RisPatientInfoTbl.BIRTHDAY_STRING_COLUMN] = ConvertBirthdayStr(birtyday);

						//年齢
						curRow[RisPatientInfoTbl.AGE_COLUMN] = patientBean.GetPatientAgeDate(birtyday, sysTimestamp);
						//curRow[RisPatientInfoTbl.AGE_COLUMN] = patientBean.GetPatientAgeDate(birtyday, Configuration.GetInstance().GetSysDate(con, transaction));
						//curRow[RisPatientInfoTbl.AGE_COLUMN] = ConvertAgeStr(birtyday);

						//入外区分
						String nyugaiKbn = curRow[RisPatientInfoTbl.KANJA_NYUGAIKBN_COLUMN].toString();
						curRow[RisPatientInfoTbl.KANJA_NYUGAIKBN_NAME_COLUMN] = ConvertNyugaiKbn(nyugaiKbn, codeDt);

						//依頼科
						String sectionID = curRow[RisPatientInfoTbl.SECTIONID_COLUMN].toString();
						curRow[RisPatientInfoTbl.SECTION_NAME_COLUMN] = ConvertSection(sectionID, sectionDt);

						//病棟
						String byoutouID = curRow[RisPatientInfoTbl.BYOUTOUID_COLUMN].toString();
						curRow[RisPatientInfoTbl.BYOUTOU_NAME_COLUMN] = ConvertByoutou(byoutouID, byoutouDt);

						//病室
						String byousituID = curRow[RisPatientInfoTbl.BYOUSITUID_COLUMN].toString();
						curRow[RisPatientInfoTbl.BYOUSITU_NAME_COLUMN] = ConvertByousitu(byousituID, byousituDt);
					}
				}

				// すべて成功した場合はCOMMIT
				transaction.Commit();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);

			// 失敗した場合はROLLBACK
			try
			{
				transaction.Rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}
			throw ex;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/// <summary>
	/// 誕生日を変換する
	/// </summary>
	/// <param name="value">値</param>
	/// <returns></returns>
	private String ConvertBirthdayStr(String value)
	{
		String retStr = "";

		if (value != null && value.length() == 8)
		{
			String tempBirthdayStr = value.SubString(0, 4);
			tempBirthdayStr += "/" + value.SubString(4, 2);
			tempBirthdayStr += "/" + value.SubString(6, 2);
			retStr = tempBirthdayStr;
		}

		return retStr;
	}
	*/

	/// <summary>
	/// 入外区分を変換する
	/// </summary>
	/// <param name="value">値</param>
	/// <param name="masterDt">マスター情報</param>
	/// <returns></returns>
	private String ConvertNyugaiKbn(String value, DataTable masterDt, Connection con)
	{
		String retStr = "";

		retStr = Configuration.GetInstance().GetCodeConvertValue(masterDt, CommonString.CODECONVERT_ID_INOUT, value, con);

		return retStr;
	}

	/// <summary>
	/// 依頼科を変換する
	/// </summary>
	/// <param name="value">値</param>
	/// <param name="masterDt">マスター情報</param>
	/// <returns></returns>
	private String ConvertSection(String value, DataTable masterDt)
	{
		String retStr = "";

		MasterUtil mUtil = new MasterUtil();
		retStr = mUtil.FindData(masterDt, MasterUtil.RIS_SECTION_NAME, MasterUtil.RIS_SECTION_ID, value);

		return retStr;
	}

	/// <summary>
	/// 病棟を変換する
	/// </summary>
	/// <param name="value">値</param>
	/// <param name="masterDt">マスター情報</param>
	/// <returns></returns>
	private String ConvertByoutou(String value, DataTable masterDt)
	{
		String retStr = "";

		MasterUtil mUtil = new MasterUtil();
		retStr = mUtil.FindData(masterDt, MasterUtil.RIS_BYOUTOU_NAME, MasterUtil.RIS_BYOUTOU_ID, value);

		return retStr;
	}

	/// <summary>
	/// 病室を変換する
	/// </summary>
	/// <param name="value">値</param>
	/// <param name="masterDt">マスター情報</param>
	/// <returns></returns>
	private String ConvertByousitu(String value, DataTable masterDt)
	{
		String retStr = "";

		MasterUtil mUtil = new MasterUtil();
		retStr = mUtil.FindData(masterDt, MasterUtil.RIS_BYOUSITU_NAME, MasterUtil.RIS_BYOUSITU_ID, value);

		return retStr;
	}

	/// <summary>
	/// 患者情報を更新する
	/// </summary>
	/// <param name="patientBean">更新後の患者情報</param>
	/// <returns></returns>
	public boolean UpdateRisPatientData( PatientInfoBean patientBean, Connection con ) throws Exception
	{
		// parameters
		boolean retFlg = false;
		RisPatientInfoTbl risPatientInfoTbl = new RisPatientInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if( con != null && patientBean != null )
			{
				// Ris.PatientInfoに患者情報を更新
				retFlg = risPatientInfoTbl.UpdatePatientData( con, patientBean);
			}

			// 全てのINSERTに成功したらCOMMIT
			// そうでない場合はROLLBACK
			if(retFlg)
			{
				con.commit();
			}
			else
			{
				con.rollback();
			}
		}
		catch( Exception e )
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );//2007.5.25 メッセージを外部ファイル定義に変更

			try
			{
				con.rollback();
			}
			catch( Exception e1 )
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1 ); //2007.5.25 メッセージを外部ファイル定義に変更
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/*
	/// <summary>
	///  患者コメントを更新する
	/// </summary>
	/// <param name="patientID">患者ID</param>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="comment">コメント</param>
	/// <returns></returns>
	public boolean UpdateRisPatientComment(String patientID, String kensatypeID, String comment)
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		Transaction transaction = null;
		RisPatientCommentTbl risPatientCommentTbl = new RisPatientCommentTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && patientID != null && kensatypeID != null)
			{
				// Ris.PatientInfoに患者情報を更新
				retFlg = risPatientCommentTbl.UpdatePatientComment(con, transaction, patientID, kensatypeID, comment);
			}

			// 全てのINSERTに成功したらCOMMIT
			// そうでない場合はROLLBACK
			if (retFlg)
			{
				transaction.Commit();
			}
			else
			{
				transaction.Rollback();
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);//2007.5.25 メッセージを外部ファイル定義に変更

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1); //2007.5.25 メッセージを外部ファイル定義に変更
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// 患者情報を登録する
	/// </summary>
	/// <param name="patientBean">患者情報</param>
	/// <returns>成功：true　失敗：false</returns>
	public boolean RegisterRisPatientInfo(PatientInfoBean patientBean)
	{
		// parameters
		boolean retBool = false;
		Connection	con				= null;
		Transaction	transaction		= null;
		RisPatientInfoTbl	patientInfoTbl	= new RisPatientInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && patientBean != null)
			{
				//PatientInfoの登録
				retBool = patientInfoTbl.InsertPatientData(con, transaction, patientBean);
				if (!retBool)
				{
					throw new Exception();
				}

				// commit
				transaction.Commit();
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/// <summary>
	/// 実績患者身長体重情報を更新する
	/// </summary>
	/// <param name="risID">RisID</param>
	/// <param name="patientBean">更新後の患者情報</param>
	/// <returns></returns>
	public boolean UpdateRisPatientResultTallWeightData(String risID, PatientInfoBean patientBean)
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		Transaction transaction = null;
		RisPatientResultsInfoTbl risPatientResultsInfoTbl = new RisPatientResultsInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID.length() > 0 && patientBean != null)
			{
				// Ris.PatientInfoに患者情報を更新
				retFlg = risPatientResultsInfoTbl.UpdatePatientResultTallWeightData(con, transaction, risID, patientBean);
			}

			// 全てのINSERTに成功したらCOMMIT
			// そうでない場合はROLLBACK
			if (retFlg)
			{
				transaction.Commit();
			}
			else
			{
				transaction.Rollback();
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);//2007.5.25 メッセージを外部ファイル定義に変更

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1); //2007.5.25 メッセージを外部ファイル定義に変更
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}

	/// <summary>
	/// 治療DBの患者共通コメントを検索する
	/// </summary>
	/// <param name="patientID">患者ID</param>
	/// <returns>患者検索結果一覧</returns>
	public String GetRrisPatientComment(String patientID)
	{
		// parameters
		String retStr = "";

		Connection con = null;
		Transaction transaction = null;
		RisPatientCommentTbl patientCommentTbl = new RisPatientCommentTbl(true);
		PatientInfoBean patientBean = new PatientInfoBean();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRrisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && patientID != null)
			{
				//患者コメントの取得
				ArrayList list = patientCommentTbl.GetPatientCommentAll(con, transaction, patientID);
				if (list != null)
				{
					//患者コメントのループ
					for (int i=0; i<list.Count; i++)
					{
						DataRow row = (DataRow)list[i];

						//共通コメントを取得する
						if (row[RisPatientCommentTbl.PATIENTKENSATYPE_COLUMN].toString() == MasterUtil.RIS_PATIENTCOMMENT_DEF)
						{
							retStr = row[RisPatientCommentTbl.PATIENTCOMMENT_COLUMN].toString();
							break;
						}
					}
				}

				// すべて成功した場合はCOMMIT
				transaction.Commit();
			}
		}
		catch (Exception ex)
		{
			logger.fatal(ex);

			// 失敗した場合はROLLBACK
			try
			{
				transaction.Rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}
			throw ex;
		}
		finally
		{
			//実際に開放
			DBConnectionFactory.GetInstance().CloseDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retStr;
	}

	/// <summary>
	/// 実績患者情報を最新の患者情報へ更新する
	/// </summary>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="risID">RISID</param>
	/// <returns></returns>
	public boolean UpdateRisResultPatientData(String kanjaID, String risID)
	{
		// parameters
		boolean retFlg = false;
		Connection con = null;
		Transaction transaction = null;
		RisPatientInfoTbl			patientInfoTbl			= new RisPatientInfoTbl();
		RisPatientResultsInfoTbl	patientResultsInfoTbl	= new RisPatientResultsInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && kanjaID != null && risID != null)
			{
				PatientInfoBean patientBean = patientInfoTbl.GetPatientData(con, transaction, kanjaID);
				if (patientBean != null)
				{
					retFlg = patientResultsInfoTbl.UpdatePatientResultData(con, transaction, risID, patientBean);
					if (retFlg == false)
					{
						throw new Exception();
					}
				}
			}

			// 全てのINSERTに成功したらCOMMIT
			// そうでない場合はROLLBACK
			if (retFlg)
			{
				transaction.Commit();
			}
			else
			{
				transaction.Rollback();
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retFlg = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retFlg;
	}
	*/
}
