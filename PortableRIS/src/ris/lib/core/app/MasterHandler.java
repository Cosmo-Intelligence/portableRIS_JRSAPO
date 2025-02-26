package ris.lib.core.app;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.Configuration;
import ris.lib.core.bean.PresetInfoBean;
import ris.lib.core.database.MasterInfoTbl;
import ris.lib.core.database.RisCalendarMasterTbl;
import ris.lib.core.database.RisCrSatueiMenuMaster;
import ris.lib.core.database.RisCurrentServerTypeTbl;
import ris.lib.core.database.RisPresetExamMasterTbl;
import ris.lib.core.database.RisPresetFilmMasterTbl;
import ris.lib.core.database.RisPresetInfuseMasterTbl;
import ris.lib.core.database.RisPresetMasterTbl;
import ris.lib.core.database.RisPresetSatueiMasterTbl;
import ris.lib.core.database.RisPresetZoueizaiMasterTbl;
import ris.lib.core.database.RisSystemDefineTbl;
import ris.lib.core.database.RisSystemParamTbl;
import ris.lib.core.database.SysDateTbl;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.PresetParameter;
import ris.lib.core.util.RequestParameter;
import ris.portable.common.Const;
import ris.portable.util.DataTable;

/// <summary>
///
/// マスタデータ管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.22	: 112478 / A.Kobayashi	: original
///
/// </summary>
public class MasterHandler
{
	public static Log logger = LogFactory.getLog(MasterHandler.class);

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public MasterHandler()
	{
		//
	}

	/// <summary>
	/// システム設定情報をDBから取得する
	/// </summary>
	/// <returns></returns>
	public DataTable GetRisSystemDefine(Connection con) throws Exception
	{
		// parameters
		DataTable dataTable = null;

		// begin log
		logger.debug("begin");

		// SYSTEM_DEFINEテーブルにアクセス
		try
		{
			// SYSTEMDEFINEテーブルにアクセス
			RisSystemDefineTbl systemDefTbl = new RisSystemDefineTbl();
			dataTable = systemDefTbl.GetSystemDefine(con);
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return dataTable;
	}

	/// <summary>
	/// システム情報をDBから取得する
	/// </summary>
	/// <returns></returns>
	public DataTable GetRisSystemParam(Connection con) throws Exception
	{
		// parameters
		DataTable dataTable = null;
		// begin log
		logger.debug("begin");

		// SYSTEMPARAMテーブルにアクセス
		try
		{
			// SYSTEMDEFINEテーブルにアクセス
			RisSystemParamTbl systemParamTbl = new RisSystemParamTbl();
			dataTable = systemParamTbl.GetSystemParam(con);
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return dataTable;
	}

	/// <summary>
	/// ｶﾚﾝﾄ認証監査証跡ｻｰﾊﾞ種別をDBから取得する
	/// </summary>
	/// <returns></returns>
	public DataTable GetRisCurrentServerType(Connection con) throws Exception
	{
		// parameters
		DataTable dataTable = null;

		// begin log
		logger.debug("begin");

		// CURRENTSERVERTYPE テーブルにアクセス
		try
		{
			// CURRENTSERVERTYPEテーブルにアクセス
			RisCurrentServerTypeTbl currentServerTypeTbl = new RisCurrentServerTypeTbl();
			dataTable = currentServerTypeTbl.GetCurrentServerType(con);
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return dataTable;
	}

	/*
	/// <summary>
	/// 共通情報をDBから取得する
	/// </summary>
	/// <returns></returns>
	public CommonParameterBean GetCommonParameters()
	{
		// parameters
		CommonParameterBean commonParam = null;
		Connection con = null;
		Transaction transaction = null;

		// begin log
		logger.debug("begin");

		// ATTR_MANAGEテーブルにアクセス
		try
		{
			// get connection
			con = DBConnectionFactory.GetInstance().GetUserManageDBConnection();

			// begin transaction
			transaction = con.BeginTransaction();

			// ATTR_MANAGEテーブルにアクセス
			AttrManageTbl attrManageTbl = new AttrManageTbl();
			commonParam = attrManageTbl.GetCommonParameter(con, transaction);

			// commit
			transaction.Commit();
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
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1);
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

		return commonParam;
	}
	*/

	/*
	/// <summary>
	/// マスタを取得する
	/// </summary>
	/// <param name="tableNameStr">指定マスタテーブル名</param>
	/// <param name="keyColumnStr">指定カラム名</param>
	/// <param name="keyData">指定値（条件句)</param>
	/// <returns>結果</returns>
	public DataRow GetMaster(String tableNameStr, String keyColumnStr, String keyData)
    {
        Connection rCon = null;
        Transaction rTransaction = null;
        MasterInfoTbl masterInfoTbl = new MasterInfoTbl();

		DataRow dataRow = null;

        // begin log
        logger.debug("begin");

        try
        {
            // get connection and begin transaction
            rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
            rTransaction = rCon.BeginTransaction();

            if (rCon != null && rTransaction != null)
            {
				dataRow = masterInfoTbl.GetMasterDataTable(rCon, rTransaction, tableNameStr, keyColumnStr, keyData);
            }

            // commit
            rTransaction.Commit();
        }
        catch (Exception e)
        {
            logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

            if (rTransaction != null)
            {
                try
                {
                    rTransaction.Rollback();
                }
                catch (Exception e1)
                {
                    String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
                    logger.fatal(message, e1);
                }
            }
            throw e;
        }
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(rCon);
		}

        // end log
        logger.debug("end");

		return dataRow;
    }
	*/

    /// <summary>
    /// マスタテーブルの取得
    /// </summary>
    /// <param name="tableName">マスタのテーブル名</param>
    /// <param name="ascBool">ORDER BY SHOWORDER ASC の有無</param>
    /// <returns>マスタテーブル</returns>
    public DataTable GetMaster(String tableName, boolean ascBool, Connection con) throws Exception
    {
        MasterInfoTbl masterInfoTbl = new MasterInfoTbl();

        DataTable masterDataTable = null;

        // begin log
        logger.debug("begin");

        try
        {
            if (con != null)
            {
                masterDataTable = masterInfoTbl.GetMasterDataTable(con, tableName, ascBool);
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        // end log
        logger.debug("end");

        return masterDataTable;
    }

	/*
	// 2010.07.30 Add T.Nishikubo Start
	/// <summary>
	/// マスタを取得する
	/// </summary>
	/// <param name="tableNameStr">指定マスタテーブル名</param>
	/// <param name="keyColumnStr">指定カラム名</param>
	/// <param name="keyData">指定値（条件句)</param>
	/// <param name="sortCol">ソート順カラム</param>
	/// <returns>結果</returns>
	public DataTable GetMaster(String tableNameStr, String keyColumnStr, String keyData, String sortCol)
	{
		Connection rCon = null;
		Transaction rTransaction = null;
		MasterInfoTbl masterInfoTbl = new MasterInfoTbl();

		DataTable masterDataTable = null;

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null)
			{
				masterDataTable = masterInfoTbl.GetMasterDataTable(rCon, rTransaction, tableNameStr, keyColumnStr, keyData, sortCol);
			}

			// commit
			rTransaction.Commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (rTransaction != null)
			{
				try
				{
					rTransaction.Rollback();
				}
				catch (Exception e1)
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1);
				}
			}
			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(rCon);
		}

		// end log
		logger.debug("end");

		return masterDataTable;
	}
	// 2010.07.30 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// テンプレートマスタを収集する
	/// </summary>
	/// <returns></returns>
	public DataTable GetTelplateGroupMaster(String groupCode)
	{
		DataTable dt = null;
		MasterInfoTbl masterInfoTbl = new MasterInfoTbl();
		Connection rCon = null;
		Transaction rTransaction = null;

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			dt = masterInfoTbl.GetTelplateGroupMaster(rCon, rTransaction, groupCode);

			// commit
			rTransaction.Commit();
		}
		catch( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			if( rTransaction != null )
			{
				try
				{
					rTransaction.Rollback();
				}
				catch( Exception e1 )
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1 );
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(rCon);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/

	/*
	/// <summary>
	/// テンプレートマスタを収集する
	/// </summary>
	/// <returns></returns>
	public DataTable GetTemplateContents(String templateIDStr, String paramStr)
	{
		DataTable dt = null;
		MasterInfoTbl masterInfoTbl = new MasterInfoTbl();
		Connection rCon = null;
		Transaction rTransaction = null;

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			dt = masterInfoTbl.GetTelplateContents(rCon, rTransaction, templateIDStr, paramStr);

			// commit
			rTransaction.Commit();
		}
		catch( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			if( rTransaction != null )
			{
				try
				{
					rTransaction.Rollback();
				}
				catch( Exception e1 )
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1 );
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(rCon);
		}

		// end log
		logger.debug("end");

		return dt;
	}
	*/

	/*
	/// <summary>
	/// マスタデータをRisDBから収集する
	/// </summary>
	/// <returns></returns>
	public DataSet CollectRisMasterInfo()
	{
		Connection rCon = null;
		Transaction rTransaction = null;

		DataSet masterInfo = null;

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();
			MasterInfoTbl masterTable = new MasterInfoTbl();

			if( rCon != null && rTransaction != null)
			{
				masterInfo = masterTable.CollectRisMasterInfo(rCon, rTransaction);
			}

			// commit
			rTransaction.Commit();
		}
		catch( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			if( rTransaction != null )
			{
				try
				{
					rTransaction.Rollback();
				}
				catch( Exception e1 )
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1 );
				}
			}

			throw e;
		}
		finally
		{
			DBConnectionFactory.GetInstance().ReturnDBConnection(rCon);
		}

		// end log
		logger.debug("end");

		return masterInfo;
	}
	*/

	/// <summary>
	/// 現在の時刻を取得する
	/// </summary>
	/// <returns>現在の時刻</returns>
	public Timestamp GetSysDate(Connection con) throws Exception
	{
		// parameters
		Timestamp retDate = Const.TIMESTAMP_MINVALUE;
		SysDateTbl sysDateTbl = new SysDateTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null)
			{
				retDate = sysDateTbl.GetSysDate(con);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return retDate;
	}


	/*
	/// <summary>
	/// 現在の時刻を取得する
	/// </summary>
	/// <param name="con">データベース接続オブジェクト</param>
	/// <param name="trans">DBトランザクションオブジェクト</param>
	/// <returns>現在の時刻</returns>
	public Timestamp GetSysDate(Connection con, Transaction trans)
	{
		// parameters
		Timestamp retDate = Timestamp.MinValue;
		SysDateTbl sysDateTbl = new SysDateTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && trans != null)
			{
				retDate = sysDateTbl.GetSysDate(con, trans);
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			throw e;
		}

		// end log
		logger.debug("end");

		return retDate;
	}
	*/

	/// <summary>
	/// 祝日情報を取得する
	/// </summary>
	/// <returns>祝日情報</returns>
	public DataTable GetHolidayDataTable(Timestamp date, Connection con) throws Exception
	{
		// parameters
		DataTable retList = null;
		RisCalendarMasterTbl risCalendarMasterTbl = new RisCalendarMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null)
			{
				retList = risCalendarMasterTbl.GetHolidayData(con, date);
			}

			// commit
			con.commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retList;
	}

	/*
	/// <summary>
	/// 器材マスタの取得
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <returns></returns>
	public void GetPartsMasterList(String kensatypeID,
		ref DataTable partsBunruiDt, ref DataTable detailPartsDt, ref DataTable partsDt, ref DataTable infuseDt)
	{
		// parameters
		Connection con = null;
		Transaction transaction = null;
		MasterInfoTbl					masterInfoTbl					= new MasterInfoTbl();
		RisPartsBunruiMasterTbl			risPartsBunruiMasterTbl			= new RisPartsBunruiMasterTbl();
		RisDetailPartsBunruiMasterTbl	risDetailPartsBunruiMasterTbl	= new RisDetailPartsBunruiMasterTbl();
		RisPartsMasterTbl				risPartsMasterTbl				= new RisPartsMasterTbl();
		RisInfuseMasterTbl				risInfuseMasterTbl				= new RisInfuseMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && kensatypeID != null)
			{
				//検索条件の準備
				PartsSearchParameter param = new PartsSearchParameter();
				DataTable kensatypeDt = masterInfoTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_KENSATYPEMASTER, true);
				String filterStr = Configuration.GetInstance().GetKensatypeFilterFlg(kensatypeDt, kensatypeID);
				param.SetKensatypeFilterFlg(filterStr);

				//器材区分情報の取得
				partsBunruiDt = risPartsBunruiMasterTbl.GetPartsBunruiDataTable(con, transaction, param);

				//器材区分IDを元に器材詳細区分を取得する
				String partsBunruiIDs = "";
				for (int i=0; i<partsBunruiDt.Rows.Count; i++)
				{
					DataRow row = partsBunruiDt.Rows[i];
					if (partsBunruiIDs.length() <= 0)
					{
						partsBunruiIDs += row[RisPartsBunruiMasterTbl.ID_COLUMN].toString();
					}
					else
					{
						partsBunruiIDs += "," + row[RisPartsBunruiMasterTbl.ID_COLUMN].toString();
					}
				}
				if (partsBunruiIDs.length() > 0)
				{
					//器材詳細区分情報の取得
					param = new PartsSearchParameter();
					param.SetPartsBunruiID(partsBunruiIDs);
					detailPartsDt = risDetailPartsBunruiMasterTbl.GetDetailPartsBunruiDataTable(con, transaction, param);

					// 2011.05.25 K.Shinohara Add Start A0051
					// 検査種別ID、使用フラグで器材・手技の絞込み
					int kensaTypeIndex = filterStr.IndexOf('1');
					String whereStr = MasterUtil.RIS_USEFLAG + " = " + CommonString.USEDFLG_1;

					// 2011.09.01 Mod K.Shinohara Start A0051
					// 器材ID、区分を元に器材マスタor手技マスタを取得する
					String partsIDs = "";
					String infuseIDs = "";
					for (int i=0; i<detailPartsDt.Rows.Count; i++)
					{
						DataRow row = detailPartsDt.Rows[i];
						String type = row[RisDetailPartsBunruiMasterTbl.TYPE_COLUMN].toString();
						String idList = row[RisDetailPartsBunruiMasterTbl.PARTSLISTS_COLUMN].toString();

						//idListを変換する
						char markChar = char.Parse(Configuration.GetInstance().GetMarkerCharacter());
						String[] idLists = idList.Split(markChar);
						for (int j = 0; j < idLists.length(); j++)
						{
							String id = "'" + idLists[j].toString() + "'";

							if (type == CommonString.ITEM_KBN_PARTS.toString())
							{
								// 同一器材IDが存在する場合は処理しない
								if (partsIDs.Contains(id))
									continue;

								if (partsIDs.length() <= 0)
								{
									partsIDs += id;
								}
								else
								{
									partsIDs += "," + id;
								}
							}
							else if (type == CommonString.ITEM_KBN_INFUSE.toString())
							{
								// 同一手技IDが存在する場合は処理しない
								if (infuseIDs.Contains(id))
									continue;

								if (infuseIDs.length() <= 0)
								{
									infuseIDs += id;
								}
								else
								{
									infuseIDs += "," + id;
								}
							}
						}
					}
					// コメント
					//// 2011.08.24 Add K.Shinohara Start A0051
					//// 器材ID、区分を元に器材マスタor手技マスタを取得する
					//String partsIDs = "";
					//String infuseIDs = "";
					//for (int i=0; i<detailPartsDt.Rows.Count; i++)
					//{
					//    DataRow row = detailPartsDt.Rows[i];
					//    String type = row[RisDetailPartsBunruiMasterTbl.TYPE_COLUMN].toString();
					//    String idList = row[RisDetailPartsBunruiMasterTbl.PARTSLISTS_COLUMN].toString();

					//    //idListを変換する
					//    String markChar = Configuration.GetInstance().GetMarkerCharacter();
					//    idList = idList.Replace(markChar, ",");

					//    if (type == CommonString.ITEM_KBN_PARTS.toString())
					//    {
					//        if (partsIDs.length() <= 0)
					//        {
					//            partsIDs += idList;
					//        }
					//        else
					//        {
					//            partsIDs += "," + idList;
					//        }
					//    }
					//    else if (type == CommonString.ITEM_KBN_INFUSE.toString())
					//    {
					//        if (infuseIDs.length() <= 0)
					//        {
					//            infuseIDs += idList;
					//        }
					//        else
					//        {
					//            infuseIDs += "," + idList;
					//        }
					//    }
					//}
					//// 2011.08.24 Add K.Shinohara End A0051

					// 2011.09.01 Mod K.Shinohara End A0051

					// 器材情報絞込み
					// 検査種別IDでの絞り込み
					if (partsDt.Rows.Count > 0)
					{
						DataTable newTable = partsDt.Clone();
						newTable.Rows.clear();

						for (int i = 0; i < partsDt.Rows.Count; i++)
						{
							DataRow row = partsDt.Rows[i];

							if ((row[MasterUtil.RIS_KENSATYPE_FILTER_FLG].toString().length() > kensaTypeIndex) &&
								(row[MasterUtil.RIS_KENSATYPE_FILTER_FLG].toString().SubString(kensaTypeIndex, 1) == "1"))
							{
								newTable.Rows.Add(row.ItemArray);
							}
						}
						partsDt = newTable;
					}
					// 使用可否フラグでの絞り込み
					if (partsDt.Rows.Count > 0)
					{
						DataTable newTable = partsDt.Clone();
						newTable.Rows.clear();
						DataRow[] rows = partsDt.Select(whereStr, MasterUtil.RIS_SHOWORDER);
						for (DataRow row : rows)
						{
							newTable.Rows.Add(row.ItemArray);
						}
						partsDt = newTable;
					}
					// 2011.09.01 Add K.Shinohara Start A0051
					// 器材IDでの絞り込み
					if (partsDt.Rows.Count > 0)
					{
						if (partsIDs.length() > 0)
						{
							DataTable newTable = partsDt.Clone();
							newTable.Rows.clear();
							String partsWhere = MasterUtil.RIS_ZOUEIZAI_ID + " IN (" + partsIDs + ") ";
							DataRow[] rows = partsDt.Select(partsWhere, MasterUtil.RIS_SHOWORDER);
							for (DataRow row : rows)
							{
								newTable.Rows.Add(row.ItemArray);
							}
							partsDt = newTable;
						}
					}
					// 2011.09.01 Add K.Shinohara End A0051

					// 手技情報絞込み
					// 検査種別IDでの絞り込み
					if (infuseDt.Rows.Count > 0)
					{
						DataTable newTable = infuseDt.Clone();
						newTable.Rows.clear();

						for (int i = 0; i < infuseDt.Rows.Count; i++)
						{
							DataRow row = infuseDt.Rows[i];

							if ((row[MasterUtil.RIS_KENSATYPE_FILTER_FLG].toString().length() > kensaTypeIndex) &&
								(row[MasterUtil.RIS_KENSATYPE_FILTER_FLG].toString().SubString(kensaTypeIndex, 1) == "1"))
							{
								newTable.Rows.Add(row.ItemArray);
							}
						}
						infuseDt = newTable;
					}
					// 使用可否フラグでの絞り込み
					if (infuseDt.Rows.Count > 0)
					{
						// 手技情報の絞込み
						DataTable newTable = infuseDt.Clone();
						newTable.Rows.clear();
						DataRow[] rows = infuseDt.Select(whereStr, MasterUtil.RIS_SHOWORDER);
						for (DataRow row : rows)
						{
							newTable.Rows.Add(row.ItemArray);
						}
						infuseDt = newTable;
					}
					// 2011.09.01 Add K.Shinohara Start A0051
					// 手技IDでの絞込み
					if (infuseDt.Rows.Count > 0)
					{
						if (infuseIDs.length() > 0)
						{
							DataTable newTable = infuseDt.Clone();
							newTable.Rows.clear();
							String infuseWhere = MasterUtil.RIS_INFUSE_ID + " IN (" + infuseIDs + ") ";
							DataRow[] rows = infuseDt.Select(infuseWhere, MasterUtil.RIS_SHOWORDER);
							for (DataRow row : rows)
							{
								newTable.Rows.Add(row.ItemArray);
							}
							infuseDt = newTable;
						}
					}
					// 2011.09.01 Add K.Shinohara End A0051

					// 2011.05.25 K.Shinohara Add End

					// コメント化 2011.05.24 Del H.Orikasa A0051
					////器材ID、区分を元に器材マスタor手技マスタを取得する
					//String partsIDs = "";
					//String infuseIDs = "";
					//for (int i=0; i<detailPartsDt.Rows.Count; i++)
					//{
					//    DataRow row = detailPartsDt.Rows[i];
					//    String type = row[RisDetailPartsBunruiMasterTbl.TYPE_COLUMN].toString();
					//    String idList = row[RisDetailPartsBunruiMasterTbl.PARTSLISTS_COLUMN].toString();

					//    //idListを変換する
					//    String markChar = Configuration.GetInstance().GetMarkerCharacter();
					//    idList = idList.Replace(markChar, ",");

					//    if (type == CommonString.ITEM_KBN_PARTS.toString())
					//    {
					//        if (partsIDs.length() <= 0)
					//        {
					//            partsIDs += idList;
					//        }
					//        else
					//        {
					//            partsIDs += "," + idList;
					//        }
					//    }
					//    else if (type == CommonString.ITEM_KBN_INFUSE.toString())
					//    {
					//        if (infuseIDs.length() <= 0)
					//        {
					//            infuseIDs += idList;
					//        }
					//        else
					//        {
					//            infuseIDs += "," + idList;
					//        }
					//    }
					//}
					//if (partsIDs.length() > 0)
					//{
					//    //器材情報の取得
					//    param = new PartsSearchParameter();
					//    param.SetPartsID(partsIDs);
					//    param.SetKensatypeFilterFlg(filterStr);
					//    partsDt = risPartsMasterTbl.GetPartsDataTable(con, transaction, param);
					//}
					//if (infuseIDs.length() > 0)
					//{
					//    //手技情報の取得
					//    param = new PartsSearchParameter();
					//    param.SetPartsID(infuseIDs);
					//    param.SetKensatypeFilterFlg(filterStr);
					//    infuseDt = risInfuseMasterTbl.GetInfuseDataTable(con, transaction, param);
					//}

				}
			}

			// commit
			transaction.Commit();
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
	}
	*/

	/*
	// 2011.02.14 Add K.Shinohara Start
	/// <summary>
	/// 器材マスタの取得
	/// </summary>
	/// <param name="kensatypeID">検査種別ID</param>
	/// <param name="partsBaseDt">器材情報(全検査種別)</param>
	/// <param name="infuseBaseDt">手技情報(全検査種別)</param>
	/// <returns></returns>
	public void GetPartsMasterList(String kensatypeID, DataTable partsBaseDt, DataTable infuseBaseDt,
		ref DataTable partsBunruiDt, ref DataTable detailPartsDt, ref DataTable partsDt, ref DataTable infuseDt)
	{
		// parameters
		Connection con = null;
		Transaction transaction = null;
		MasterInfoTbl					masterInfoTbl					= new MasterInfoTbl();
		RisPartsBunruiMasterTbl			risPartsBunruiMasterTbl			= new RisPartsBunruiMasterTbl();
		RisDetailPartsBunruiMasterTbl	risDetailPartsBunruiMasterTbl	= new RisDetailPartsBunruiMasterTbl();
		RisPartsMasterTbl				risPartsMasterTbl				= new RisPartsMasterTbl();
		RisInfuseMasterTbl				risInfuseMasterTbl				= new RisInfuseMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && kensatypeID != null)
			{
				//検索条件の準備
				PartsSearchParameter param = new PartsSearchParameter();
				DataTable kensatypeDt = masterInfoTbl.GetMasterDataTable(con, transaction, MasterUtil.RIS_KENSATYPEMASTER, true);
				String filterStr = Configuration.GetInstance().GetKensatypeFilterFlg(kensatypeDt, kensatypeID);
				param.SetKensatypeFilterFlg(filterStr);

				//器材区分情報の取得
				partsBunruiDt = risPartsBunruiMasterTbl.GetPartsBunruiDataTable(con, transaction, param);

				//器材区分IDを元に器材詳細区分を取得する
				String partsBunruiIDs = "";
				for (int i=0; i<partsBunruiDt.Rows.Count; i++)
				{
					DataRow row = partsBunruiDt.Rows[i];
					if (partsBunruiIDs.length() <= 0)
					{
						partsBunruiIDs += row[RisPartsBunruiMasterTbl.ID_COLUMN].toString();
					}
					else
					{
						partsBunruiIDs += "," + row[RisPartsBunruiMasterTbl.ID_COLUMN].toString();
					}
				}
				if (partsBunruiIDs.length() > 0)
				{
					//器材詳細区分情報の取得
					param = new PartsSearchParameter();
					param.SetPartsBunruiID(partsBunruiIDs);
					detailPartsDt = risDetailPartsBunruiMasterTbl.GetDetailPartsBunruiDataTable(con, transaction, param);

					// コメント化 2011.05.24 Del H.Orikasa A0051
					////器材ID、区分を元に器材マスタor手技マスタを取得する
					//String partsIDs = "";
					//String infuseIDs = "";
					//for (int i=0; i<detailPartsDt.Rows.Count; i++)
					//{
					//    DataRow row = detailPartsDt.Rows[i];
					//    String type = row[RisDetailPartsBunruiMasterTbl.TYPE_COLUMN].toString();
					//    String idList = row[RisDetailPartsBunruiMasterTbl.PARTSLISTS_COLUMN].toString();

					//    //idListを変換する
					//    String markChar = Configuration.GetInstance().GetMarkerCharacter();
					//    idList = idList.Replace(markChar, ",");

					//    if (type == CommonString.ITEM_KBN_PARTS.toString())
					//    {
					//        if (partsIDs.length() <= 0)
					//        {
					//            partsIDs += idList;
					//        }
					//        else
					//        {
					//            partsIDs += "," + idList;
					//        }
					//    }
					//    else if (type == CommonString.ITEM_KBN_INFUSE.toString())
					//    {
					//        if (infuseIDs.length() <= 0)
					//        {
					//            infuseIDs += idList;
					//        }
					//        else
					//        {
					//            infuseIDs += "," + idList;
					//        }
					//    }
					//}
					//if (partsIDs.length() > 0)
					//{
					//    //器材情報の取得
					//    param = new PartsSearchParameter();
					//    param.SetPartsID(partsIDs);
					//    param.SetKensatypeFilterFlg(filterStr);
					//    partsDt = risPartsMasterTbl.GetPartsDataTable(partsBaseDt, param);
					//}
					//if (infuseIDs.length() > 0)
					//{
					//    //手技情報の取得
					//    param = new PartsSearchParameter();
					//    param.SetPartsID(infuseIDs);
					//    param.SetKensatypeFilterFlg(filterStr);
					//    infuseDt = risInfuseMasterTbl.GetInfuseDataTable(infuseBaseDt, param);
					//}

				}
			}

			// commit
			transaction.Commit();
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
	}
	// 2011.02.14 Add K.Shinohara End
	*/

	/*
	/// <summary>
	/// 器材情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	public DataTable GetPartsDataTable(PartsSearchParameter param)
	{
		// parameters
		DataTable retDt = null;
		Connection con = null;
		Transaction transaction = null;
		RisPartsMasterTbl risPartsMasterTbl = new RisPartsMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				retDt = risPartsMasterTbl.GetPartsDataTable(con, transaction, param);
			}

			// commit
			transaction.Commit();
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

		return retDt;
	}
	*/

	/*
	/// <summary>
	/// 手技情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	public DataTable GetInfuseDataTable(PartsSearchParameter param)
	{
		// parameters
		DataTable retDt = null;
		Connection con = null;
		Transaction transaction = null;
		RisInfuseMasterTbl risInfuseMasterTbl = new RisInfuseMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				retDt = risInfuseMasterTbl.GetInfuseDataTable(con, transaction, param);
			}

			// commit
			transaction.Commit();
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

		return retDt;
	}
	*/

	/// <summary>
	/// プリセット情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <param name="satueiFinishFlg">撮影済ボタンフラグ(1：撮影済ボタン押下時動作、0：それ以外の動作)</param>
	/// <returns>プリセット情報</returns>
	// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
	public ArrayList GetPresetInfoData(PresetParameter param, int satueiFinishFlg, Connection con) throws Exception
//	public ArrayList GetPresetInfoData(PresetParameter param, Connection con) throws Exception
	// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加
	{
		// parameters
		ArrayList retList = new ArrayList();
		RisPresetMasterTbl			presetMasterTbl			= new RisPresetMasterTbl();
		RisPresetSatueiMasterTbl	presetSatueiMasterTbl	= new RisPresetSatueiMasterTbl();
		RisPresetZoueizaiMasterTbl	presetZoueizaiMasterTbl = new RisPresetZoueizaiMasterTbl();
		RisPresetInfuseMasterTbl	presetInfuseMasterTbl	= new RisPresetInfuseMasterTbl();
		RisPresetFilmMasterTbl		presetFilmMasterTbl		= new RisPresetFilmMasterTbl();
		RisPresetExamMasterTbl		presetExamMasterTbl		= new RisPresetExamMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && param != null)
			{
				retList = presetMasterTbl.GetPresetInfoData(con, param);
				if (retList != null)
				{
					for (int i=0; i<retList.size(); i++)
					{
						PresetInfoBean presetBean = (PresetInfoBean)retList.get(i);

						if (presetBean != null && presetBean.GetPresetID().length() > 0)
						{
							//検索条件をプリセットIDにする
							param = new PresetParameter();
							param.SetPresetID(presetBean.GetPresetID());

							//プリセット撮影マスタの取得
							ArrayList satueiList = presetSatueiMasterTbl.GetPresetSatueiData(con, param);
							presetBean.ReconstructSatueiList(satueiList);

							// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
							// 撮影済ボタン動作以外の場合は取得する。
							if(satueiFinishFlg != 1) {
								//プリセット器材マスタの取得
								ArrayList zoueizaiList = presetZoueizaiMasterTbl.GetPresetZoueizaiData(con, param);
								presetBean.ReconstructZoueizaiList(zoueizaiList);

								//プリセット手技マスタの取得
								ArrayList infuseList = presetInfuseMasterTbl.GetPresetInfuseData(con, param);
								presetBean.ReconstructInfuseList(infuseList);

								//プリセットフィルムマスタの取得
								ArrayList filmList = presetFilmMasterTbl.GetPresetFilmData(con, param);
								presetBean.ReconstructFilmList(filmList);

								//管理項目が有効の場合
								if (Configuration.GetInstance().GetExamItemFlg())
								{
									//プリセット管理項目マスタの取得
									ArrayList examList = presetExamMasterTbl.GetPresetExamData(con, param);
									presetBean.ReconstructExamItemList(examList);
								}
							}

//							//プリセット器材マスタの取得
//							ArrayList zoueizaiList = presetZoueizaiMasterTbl.GetPresetZoueizaiData(con, param);
//							presetBean.ReconstructZoueizaiList(zoueizaiList);
//
//							//プリセット手技マスタの取得
//							ArrayList infuseList = presetInfuseMasterTbl.GetPresetInfuseData(con, param);
//							presetBean.ReconstructInfuseList(infuseList);
//
//							//プリセットフィルムマスタの取得
//							ArrayList filmList = presetFilmMasterTbl.GetPresetFilmData(con, param);
//							presetBean.ReconstructFilmList(filmList);
//
//							//管理項目が有効の場合
//							if (Configuration.GetInstance().GetExamItemFlg())
//							{
//								//プリセット管理項目マスタの取得
//								ArrayList examList = presetExamMasterTbl.GetPresetExamData(con, param);
//								presetBean.ReconstructExamItemList(examList);
//							}
							// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加
						}
					}
				}
			}

			// commit
			con.commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retList;
	}

	/// <summary>
	/// プリセット撮影情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>プリセット撮影情報</returns>
	public ArrayList GetPresetSatueiData(PresetParameter param, Connection con) throws Exception
	{
		// parameters
		ArrayList retList = null;
		RisPresetSatueiMasterTbl presetSatueiMasterTbl = new RisPresetSatueiMasterTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && param != null)
			{
				//プリセット撮影マスタの取得
				retList = presetSatueiMasterTbl.GetPresetSatueiData(con, param);
			}

			// commit
			con.commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retList;
	}

	/*
	/// <summary>
	/// テーブル、ビューの列情報を取得する
	/// </summary>
	/// <param name="tableName">テーブル、ビュー名称</param>
	/// <returns></returns>
	public DataTable GetUserTableColumns(String tableName)
	{
		// parameters
		DataTable retDt = null;
		Connection con = null;
		Transaction transaction = null;
		UserTabColumnsTbl tabTbl = new UserTabColumnsTbl();


		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && tableName != null && tableName.length() > 0)
			{
				retDt = tabTbl.GetUserTabColumnsData(con, transaction, tableName);
			}

			// commit
			transaction.Commit();
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

		return retDt;
	}
	*/

	/*
	/// <summary>
	/// テーブル、ビューの列情報を取得する
	/// </summary>
	/// <param name="tableName">テーブル、ビュー名称</param>
	/// <returns></returns>
	public DataTable GetMwmUserTableColumns(String tableName)
	{
		// parameters
		DataTable retDt = null;
		Connection con = null;
		Transaction transaction = null;
		UserTabColumnsTbl tabTbl = new UserTabColumnsTbl();


		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetMWMDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && tableName != null && tableName.length() > 0)
			{
				retDt = tabTbl.GetUserTabColumnsData(con, transaction, tableName);
			}

			// commit
			transaction.Commit();
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

		return retDt;
	}
	*/

	/// <summary>
	/// 撮影メニュー情報を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns></returns>
	public DataTable GetSatueiMenuDataTable(RequestParameter param, Connection con) throws Exception
	{
		// parameters
		DataTable retDt = null;
		RisCrSatueiMenuMaster risCrSatueiMenuMaster = new RisCrSatueiMenuMaster();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && param != null)
			{
				retDt = risCrSatueiMenuMaster.GetCrSatueiMenuData(con, param);
			}

			// commit
			con.commit();
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retDt;
	}

	/*
	/// <summary>
	/// 指定されたテーブル情報を取得する
	/// </summary>
	/// <param name="tableName">テーブル名</param>
	/// <param name="kanjaID">患者ID</param>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
	public DataTable GetTableTable(String tableName, String kanjaID, String risID)
	{
		// parameters
		DataTable retDt = null;
		Connection con = null;
		Transaction transaction = null;
		MasterInfoTbl masterInfoTbl = new MasterInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && tableName.length() > 0)
			{
				retDt = masterInfoTbl.GetTableDataTable(con, transaction, tableName, kanjaID, risID);
			}

			// commit
			transaction.Commit();
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

		return retDt;
	}
	*/

	/*
	/// <summary>
	/// SystemParam2の値を更新する
	/// </summary>
	/// <param name="mainID">メインキー</param>
	/// <param name="subID">サブキー</param>
	/// <param name="column">列名</param>
	/// <param name="valueStr">値</param>
	/// <returns></returns>
	public boolean UpdateSystemParam2Value(String mainID, String subID, String column, String valueStr, Connection con)
	{
		// parameters
		boolean retBool = false;

		RisSystemParamTbl tbl = new RisSystemParamTbl(true);

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null &&
				mainID != null && mainID.length() > 0 && subID != null && subID.length() > 0 &&
				column != null && column.length() > 0)
			{
				//SystemParam2の値を更新する
				retBool = tbl.UpdateValue(con, transaction, mainID, subID, column, valueStr);
				if (retBool == false)
				{
					throw new Exception();
				}

				// すべて成功した場合はCOMMIT
				transaction.Commit();
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retBool = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			if (transaction != null)
			{
				try
				{
					transaction.Rollback();
				}
				catch (Exception e1)
				{
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
					logger.fatal(message, e1);
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
	*/
}
