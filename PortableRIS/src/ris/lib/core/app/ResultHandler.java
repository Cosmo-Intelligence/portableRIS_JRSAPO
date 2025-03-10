package ris.lib.core.app;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExBuiBean;
import ris.lib.core.bean.ExFilmBean;
import ris.lib.core.bean.ExInfuseBean;
import ris.lib.core.bean.ExSatueiBean;
import ris.lib.core.bean.ExZoueizaiBean;
import ris.lib.core.bean.ExamOperationHistoryInfoBean;
import ris.lib.core.bean.ExecutionInfoBean;
import ris.lib.core.bean.ExtendExamInfoBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.PatientInfoBean;
import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.bean.ToHisInfoBean;
import ris.lib.core.bean.UserAccountBean;
import ris.lib.core.database.RisExBuiInfoTbl;
import ris.lib.core.database.RisExFilmInfoTbl;
import ris.lib.core.database.RisExInfuseInfoTbl;
import ris.lib.core.database.RisExMainTbl;
import ris.lib.core.database.RisExSatueiInfoTbl;
import ris.lib.core.database.RisExZoueizaiInfoTbl;
import ris.lib.core.database.RisExamOperationHistoryTbl;
import ris.lib.core.database.RisExtendExamInfoTbl;
import ris.lib.core.database.RisExtendOrderInfoTbl;
import ris.lib.core.database.RisFromRisSequenceTbl;
import ris.lib.core.database.RisLogSequenceTbl;
import ris.lib.core.database.RisOrderBuiInfoTbl;
import ris.lib.core.database.RisOrderMainTbl;
import ris.lib.core.database.RisPatientCommentTbl;
import ris.lib.core.database.RisPatientInfoTbl;
import ris.lib.core.database.RisPatientResultsInfoTbl;
import ris.lib.core.database.RisReceiptKanjaNumberControlTbl;
import ris.lib.core.database.RisReceiptNumberAccessInfoTbl;
import ris.lib.core.database.RisReceiptNumberControlTbl;
import ris.lib.core.database.RisSystemDefineTbl;
import ris.lib.core.database.RisSystemParamTbl;
import ris.lib.core.database.RisToHisInfoTbl;
import ris.lib.core.database.RisToPacsInfoTbl;
import ris.lib.core.database.RisToReportInfoTbl;
import ris.lib.core.util.CommonString;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.MessageParameter;
import ris.lib.mwm.bean.ConnectionInfoBean;
import ris.lib.mwm.bean.MWMInfoBean;
import ris.lib.mwm.bean.MwmOrderInfoBean;
import ris.lib.mwm.core.MwmManager;
import ris.portable.common.Const;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

/// <summary>
///
/// 実績データ管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)			(Date)			(ID / NAME)					(Comment)
/// V1.00.00		: 2009.03.05	: 112478 / A.Kobayashi		: original
/// 2.04.001 		: 2010.10.04	: 999999 / DD.Chinh			: KANRO-R-6
/// V2.04.001		: 2010.11.16	: 999999 / T.Nishikubo		: KANRO-R-9
/// V2.01.00		: 2011.07.11    extends 999999 / NSK_H.Hashimoto	: NML-CAT1-002
/// V2.01.00		: 2011.07.14	: 999999 / NSK_R.Akimoto	: NML-CAT2-017
/// V2.01.00		: 2011.08.05	: 999999 / H.Satou@MERX		: NML-CAT2-010
/// V2.01.00		: 2011.08.22    extends 999999 / T.Ootsuka		: NML-2-X04
/// V2.01.01.13000	: 2011.11.15    extends 999999 / NSK_H.Hashimoto	: OMH-1-05
/// V2.01.01.13000	: 2011.11.21	: 999999 / NSK_M.Ochiai		: OMH-1-7
///
/// </summary>
public class ResultHandler
{
	public static Log logger = LogFactory.getLog(ResultHandler.class);

	/// <summary>
	/// 実績情報を取得する
	/// </summary>
	/// <param name="risUID">取得対象のRisID</param>
	/// <returns>実績情報</returns>
	public ExecutionInfoBean GetExecutionInfoBean(String risID, Connection con) throws Exception
	{
		// parameters
		ExecutionInfoBean exBean = null;

		RisExMainTbl				exMainTbl				= new RisExMainTbl();
		RisExtendExamInfoTbl		risExtendExamInfoTbl	= new RisExtendExamInfoTbl();
		RisExBuiInfoTbl				exBuiTbl				= new RisExBuiInfoTbl();
		RisExSatueiInfoTbl			exSatueiTbl				= new RisExSatueiInfoTbl();
		RisExZoueizaiInfoTbl		exZoueizaiTbl			= new RisExZoueizaiInfoTbl();
		RisExInfuseInfoTbl			exInfuseInfoTbl			= new RisExInfuseInfoTbl();
		RisExFilmInfoTbl			exFilmInfoTbl			= new RisExFilmInfoTbl();
		RisPatientResultsInfoTbl	patResultsTbl			= new RisPatientResultsInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && risID != null)
			{
				exBean = exMainTbl.GetExecutionInfoBean(con, risID);

				if (exBean != null)
				{
					// Ris.ExBuiTable 取得
					ArrayList buiList = exBuiTbl.GetExBuiListByRisID(con, risID);
					if (buiList != null)
					{
						exBean.ReconstructExBuiList(buiList);
					}

					// Ris.ExSatueiTable 取得
					ArrayList satueiList = exSatueiTbl.GetSatueiListByRisID(con, risID);
					if (satueiList != null)
					{
						exBean.ReconstructExSatueiList(satueiList);
					}

					// Ris.ExZoueizaiTable 取得
					ArrayList zoueiList = exZoueizaiTbl.GetZoueizaiListByRisID(con, risID);
					if (zoueiList != null)
					{
						exBean.ReconstructExZoueizaiList(zoueiList);
					}

					// Ris.ExInfuseTable 取得
					ArrayList infuseList = exInfuseInfoTbl.GetInfuseListByRisID(con, risID);
					if (infuseList != null)
					{
						exBean.ReconstructExInfuseList(infuseList);
					}

					// Ris.ExFilmTable 取得
					ArrayList filmList = exFilmInfoTbl.GetFilmListByRisID(con, risID);
					if (filmList != null)
					{
						exBean.ReconstructExFilmList(filmList);
					}

					// Ris.ExtendExamInfo 取得
					ExtendExamInfoBean extendExamInfoBean = risExtendExamInfoTbl.GetExtendExamInfoData(con, risID);
					exBean.SetExtendExamInfoBean(extendExamInfoBean);

					// 実績患者情報を取得する
					PatientInfoBean resultPatientBean = patResultsTbl.GetPatientResultData(con, risID);
					if (resultPatientBean != null)
					{
						exBean.SetResultPatientInfo(resultPatientBean);
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
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return exBean;
	}

	/*
	// 2010.07.30 Add DD.Chinh Start
	/// <summary>
	/// 予定・実績情報の検査室、検査機器を更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="status">ステータス</param>
	/// <param name="user">ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns></returns>
	public boolean UpdateRisExamRoomMachine(String risID, String status, UserAccountBean user, String roomID, String kikiID)
	{
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;

		RisExMainTbl	exMainTbl	 = new RisExMainTbl();
		RisOrderMainTbl	orderMainTbl = new RisOrderMainTbl();

		// begin log
		logger.debug("begin");
		try
		{
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null && status != null && user != null)
			{
				//受済
				if (status == CommonString.STATUS_ISREGISTERED)
				{
					//検査情報メインテーブルの検査室、検査機器の変更
					retBool = exMainTbl.UpdateRisExamRoomMachine(con, transaction, risID, roomID, kikiID);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				//受済未満
				else
				{
					//オーダ情報メインテーブルの検査室、検査機器の変更
					retBool = orderMainTbl.UpdateRisExamRoomMachine(con, transaction, risID, roomID, kikiID);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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
	// 2010.07.30 Add DD.Chinh End
	*/

	/// <summary>
	/// 実績情報のステータスを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="status">ステータス</param>
	/// <param name="user">ユーザ情報</param>
	/// <param name="roomID">検査室ID</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns></returns>
	public boolean UpdateRisExecutionResultStatus( String risID, String status, UserAccountBean user, String roomID, String kikiID, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;
		//
		OrderHandler				orderHandler				= new OrderHandler();
		//
		RisExMainTbl				exMainTbl					= new RisExMainTbl();
		RisPatientResultsInfoTbl	patientResultsInfoTbl		= new RisPatientResultsInfoTbl();
		RisPatientInfoTbl			patientInfoTbl				= new RisPatientInfoTbl();
		RisOrderBuiInfoTbl			orderBuiInfoTbl				= new RisOrderBuiInfoTbl();
		RisExBuiInfoTbl				exBuiInfoTbl				= new RisExBuiInfoTbl();
		RisExFilmInfoTbl			exFilmInfoTbl				= new RisExFilmInfoTbl();
		RisExZoueizaiInfoTbl		exZoueizaiInfoTbl			= new RisExZoueizaiInfoTbl();
		RisExInfuseInfoTbl			exInfuseInfoTbl				= new RisExInfuseInfoTbl();
		RisExSatueiInfoTbl			exSatueiInfoTbl				= new RisExSatueiInfoTbl();
		RisExtendExamInfoTbl		extendExamInfoTbl			= new RisExtendExamInfoTbl();
		RisExtendOrderInfoTbl		extendOrderInfoTbl			= new RisExtendOrderInfoTbl();
		RisOrderMainTbl				orderMainTbl				= new RisOrderMainTbl();
		RisFromRisSequenceTbl		fromRisSequenceTbl			= new RisFromRisSequenceTbl();
		RisToHisInfoTbl				toHisInfoTbl				= new RisToHisInfoTbl();
		RisExamOperationHistoryTbl	examOperationHistoryTbl		= new RisExamOperationHistoryTbl();
		RisLogSequenceTbl			logSequenceTbl				= new RisLogSequenceTbl();
		RisSystemDefineTbl			systemDefineTbl				= new RisSystemDefineTbl();
		RisSystemParamTbl			systemParamTbl				= new RisSystemParamTbl();
		RisToReportInfoTbl			toReportInfoTbl				= new RisToReportInfoTbl();
		RisToPacsInfoTbl			toPacsInfoTbl				= new RisToPacsInfoTbl();
		//
		PatientInfoBean patientInfoBean = null;

		// begin log
		logger.debug("begin");
		try
		{
			// 2011.08.22 Del T.Ootsuka@MERX Start NML-2-X04
			// コメント
			////端末情報の取得
			//DataTable terminalDataTable = Configuration.GetInstance().GetRRisTerminalInfo();

			// 2011.08.22 Del T.Ootsuka@MERX End

			//ユーザ情報の取得
			if (user == null)
			{
				user = Configuration.GetInstance().GetUserAccountBean();
			}

			// 2010.10.04 Del DD.Chinh KANRO-R-6
			//受付番号の取得
			//String receiptNumber = "";
			//if (status.compareTo(CommonString.STATUS_ISREGISTERED) == 0)
			//{
			//    receiptNumber = GetReceiptNumber(risID);
			//}
			// 2010.10.04 Del DD.Chinh End

			DataTable mstSysparam2 = Configuration.GetInstance().GetRRisSystemParam2();

			//患者情報送信判定
			String sendPatientFlg = Configuration.GetInstance().GetSystemParamValue(mstSysparam2, RisSystemParamTbl.REQUESTFLG, RisSystemParamTbl.VALUE2_COLUMN);

			//実績検査日判定
			String receiptDateFlg = Configuration.GetInstance().GetSystemParamValue(mstSysparam2, RisSystemParamTbl.RECEIPTDATE, RisSystemParamTbl.VALUE1_COLUMN);

			// 2010.10.04 Add DD.Chinh Start KANRO-R-6
			OrderInfoBean orderInfoBean  = null;
			ExecutionInfoBean exMainBean = null;

			if (con != null && risID != null && status != null && user != null)
			{
				//オーダ情報の取得
				orderInfoBean = orderMainTbl.GetOrderInfoBean(con, risID);

				//実績情報を取得する
				exMainBean = exMainTbl.GetExecutionInfoBean(con, risID);
				if (exMainBean == null)
				{
					// 当該exMainBeanの実績情報が存在しない場合はException
					throw new Exception();
				}
			}

			//受付番号の取得
			String receiptNumber = "";

			// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
			ArrayList strList = new ArrayList();
			//String receiptInitialChar = "";
			// 2011.11.15 Add NSK_H.Hashimoto End

			// 2011.07.14 Mod NSK_R.Akimoto Start NML-CAT2-017
			// 受付時、再受付時行う
			if ((status.compareTo(CommonString.STATUS_ISREGISTERED) == 0) ||
				(status.compareTo(CommonString.STATUS_REREGISTERED) == 0))
			// コメント
			//if (status.compareTo(CommonString.STATUS_ISREGISTERED) == 0)

			// 2011.07.14 Mod NSK_R.Akimoto End
			{
				// 2011.11.15 Mod NSK_H.Hashimoto Start OMH-1-05
				// コメント
				//receiptNumber = GetReceiptNumber(con, transaction, risID, exMainBean.GetKanjaID());		// 2011.05.23 Mod H.Orikasa 業務詳細高速化対応
				////receiptNumber = GetReceiptNumber(risID, exMainBean.GetKanjaID());						//

				// ポータブルRIS仕様上、受付番号は設定しない
				//strList = GetReceiptNumber(con, risID, exMainBean.GetKanjaID());
				//receiptNumber		= strList.get(0).toString();
				//receiptInitialChar	= strList.get(1).toString();
				// 2011.11.15 Mod NSK_H.Hashimoto End
			}
			// 2010.10.04 Add DD.Chinh End

			if (con != null && risID != null && status != null && user != null)
			{
				// 2010.10.04 Del DD.Chinh Start KANRO-R-6
				// コメント
				////オーダ情報の取得
				//OrderInfoBean orderInfoBean = orderMainTbl.GetOrderInfoBean(con, transaction, risID);

				////実績情報を取得する
				//ExecutionInfoBean exMainBean = exMainTbl.GetExecutionInfoBean(con, transaction, risID);
				//if (exMainBean == null)
				//{
				//    // 当該exMainBeanの実績情報が存在しない場合はException
				//    throw new Exception();
				//}

				// 2010.10.04 Del DD.Chinh End

				String exStatus = exMainBean.GetStatus();

				//UpdateExecutionStatus用に情報を設定
				orderInfoBean.SetStatus(exMainBean.GetStatus());

				//受付,再受のみ検査室と検査機器IDを設定する
				if (CommonString.STATUS_ISREGISTERED.equals(status) ||
					CommonString.STATUS_REREGISTERED.equals(status))
				{
					orderInfoBean.SetKensasituID(roomID);
					orderInfoBean.SetKensakikiID(kikiID);
				}

				// 端末IDを取得する

				Integer terminalIDInt   = Const.INT_MINVALUE;
				String terminalNameStr = "";
				// 2011.08.22 Mod T.Ootsuka@MERX Start NML-2-X04
				if (Configuration.GetInstance().GetTerminalInfoBean() != null)
				{
					TerminalInfoBean terminalBean = Configuration.GetInstance().GetTerminalInfoBean();
					if (terminalBean.GetTerminalID().length() > 0)
					{
						terminalIDInt	= Integer.parseInt(terminalBean.GetTerminalID());
						orderInfoBean.SetTerminalID(terminalIDInt);
					}
					if (terminalBean.GetTerminalName() != null)
					{
						terminalNameStr	= terminalBean.GetTerminalName();
					}
				}
				// コメント
				////端末情報のループ
				//for (int i = 0; i < terminalDataTable.Rows.Count; i++)
				//{
				//    if (Configuration.GetInstance().GetIPAddress() == terminalDataTable.Rows[i][MasterUtil.RIS_TERMINALADDRESS].toString())
				//    {
				//        String terminalID = terminalDataTable.Rows[i][MasterUtil.RIS_TERMINALID].toString();
				//        if (terminalID.length() != 0)
				//        {
				//            terminalIDInt = Integer.parseInt(terminalID);
				//            orderInfoBean.SetTerminalID(terminalIDInt);
				//        }

				//        String terminalName = terminalDataTable.Rows[i][MasterUtil.RIS_TERMINALNAME].toString();
				//        if (terminalName != null)
				//        {
				//            terminalNameStr = terminalName;
				//        }
				//        break;
				//    }
				//}

				// 2011.08.22 Mod T.Ootsuka@MERX End



				// 変更前のステータスと変更後のステータス（引数で入ってきた値）が
				// ステータス変更可能ルールに従っているか否かをcheck
				if (exStatus != null)
				{
					if (status.compareTo(CommonString.STATUS_UNREGISTERED) == 0)
					{
						// 未受付に変更しようとしている

						// ->　現在のステータス：
						// 受付済に変更しょうとしている
						// ->　現在のステータス：受付済、遅刻、呼出、再呼、再受以外はNG
						if (exStatus.compareTo(CommonString.STATUS_ISLATE) != 0 &&
							exStatus.compareTo(CommonString.STATUS_ISCALLING) != 0 &&
							exStatus.compareTo(CommonString.STATUS_ISREGISTERED) != 0 &&
							exStatus.compareTo(CommonString.STATUS_RECALLING) != 0 &&
							exStatus.compareTo(CommonString.STATUS_REREGISTERED) != 0)
						{
							throw new Exception();
						}

						//受済→未受付
						if (CommonString.STATUS_ISREGISTERED.equals(exStatus))
						{
							// ToHisInfoへの書込み

							//ToHisInfoにINSERTするかを判断する
							String risOrderSendFlag = Configuration.GetInstance().GetRisOrdersendFlag();
							if (CommonString.HIS_ORDER_FLG_VALUE.equals(orderInfoBean.GetSystemKbn()) ||
								(CommonString.RIS_ORDER_FLG_VALUE.equals(orderInfoBean.GetSystemKbn()) &&
								CommonString.SEND_FLG_VALUE.equals(risOrderSendFlag)))
							{
								// HIS通信テーブルのPKを生成
								String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
								if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
								{
									throw new Exception();
								}

								// ToHisInfoにINSERTするBeanを作成
								ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOHIS_ORDER_ABORT_RECEPT, risSeqStr);
								if (orderInfoBean != null)
								{
									toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
									toHisBean.SetRisID(orderInfoBean.GetRisID());
									toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
								}
								if (orderInfoBean != null)
								{
									toHisBean.SetUserInfo(user);

									if (!Const.INT_MINVALUE.equals(terminalIDInt))
									{
										toHisBean.SetRequestTerminalID(terminalIDInt.toString());
									}
								}

								//ToHisInfo書き込み
								retBool = toHisInfoTbl.InsertToHisData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
								if (retBool == false)
								{
									throw new Exception();
								}
							}
							else
							{
								//書込みなし
								logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToHisInfo_MessageString));
								retBool = true;
							}


							// ToReportInfo, ToPacsInfoへの書込み
							DataTable systemParamDataTable = systemParamTbl.GetSystemParam(con, MasterUtil.RIS_SYSTEM, MasterUtil.RIS_FLAGS);
							if (systemParamDataTable != null && systemParamDataTable.getRowCount() > 0)
							{
								// ToReportInfoへの書込み
								if (CommonString.SEND_FLG_VALUE.equals(systemParamDataTable.getRows().get(0).get(MasterUtil.RIS_VALUE1).toString()))
								{
									// HIS通信テーブルのPKを生成
									String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
									if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
									{
										throw new Exception();
									}

									// ToReportInfoにINSERTするBeanを作成
									ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOREPORT_ORDER_ABORT_RECEPT, risSeqStr);
									if (orderInfoBean != null)
									{
										toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
										toHisBean.SetRisID(orderInfoBean.GetRisID());
										toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
									}
									if (orderInfoBean != null)
									{
										toHisBean.SetUserInfo(user);

										if (!Const.INT_MINVALUE.equals(terminalIDInt))
										{
											toHisBean.SetRequestTerminalID(terminalIDInt.toString());
										}
									}

									//ToReportInfo書き込み
									retBool = toReportInfoTbl.InsertToReportData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
									if (retBool == false)
									{
										throw new Exception();
									}

									//書込み成功
									logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.addToReportInfo_MessageString));
								}
								else
								{
									//書込みなし
									logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToReportInfo_MessageString));
									retBool = true;
								}


								// ToPacsInfoへの書込み
								// 2010.10.20 Mod K.Shinohara Start
								if (CommonString.SEND_FLG_VALUE.equals(systemParamDataTable.getRows().get(0).get(MasterUtil.RIS_VALUE2).toString()))
								//if (systemParamDataTable.Rows[0][MasterUtil.RIS_VALUE1].toString() == CommonString.SEND_FLG_VALUE)
								// 2010.10.20 Mod K.Shinohara End
								{
									// HIS通信テーブルのPKを生成
									String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
									if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
									{
										throw new Exception();
									}

									// ToPacsInfoにINSERTするBeanを作成
									ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOPACS_ORDER_ABORT_RECEPT, risSeqStr);
									if (orderInfoBean != null)
									{
										toHisBean.SetRisID(orderInfoBean.GetRisID());
										toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
										toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
									}
									if (orderInfoBean != null)
									{
										toHisBean.SetUserInfo(user);

										if (!Const.INT_MINVALUE.equals(terminalIDInt))
										{
											toHisBean.SetRequestTerminalID(terminalIDInt.toString());
										}
									}

									//ToPacsInfo書き込み
									retBool = toPacsInfoTbl.InsertToPacsData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
									if (retBool == false)
									{
										throw new Exception();
									}

									//書込み成功
									logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.addToPacsInfo_MessageString));
								}
								else
								{
									//書込みなし
									logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToPacsInfo_MessageString));
									retBool = true;
								}

							}


							//実績部位の削除
							if (!exBuiInfoTbl.DeleteAllExBuiData(con, risID))
							{
								throw new Exception();
							}

							//実績フィルムの削除
							if (!exFilmInfoTbl.DeleteAllFilmDataByRisID(con, risID))
							{
								throw new Exception();
							}

							//実績造影剤の削除
							if (!exZoueizaiInfoTbl.DeleteAllZoueizaiDataByRisID(con, risID))
							{
								throw new Exception();
							}

							//実績手技の削除
							if (!exInfuseInfoTbl.DeleteAllInfuseDataByRisID(con, risID))
							{
								throw new Exception();
							}

							//実績撮影情報の削除
							if (!exSatueiInfoTbl.DeleteAllSatueiData(con, risID))
							{
								throw new Exception();
							}

							//実績患者情報の削除
							if (!patientResultsInfoTbl.DeletePatientResultData(con, risID))
							{
								throw new Exception();
							}

							//実績拡張情報の削除
							if (!extendExamInfoTbl.DeleteExtendExamData(con, risID))
							{
								throw new Exception();
							}

							//実績操作履歴情報の登録
							ExamOperationHistoryInfoBean examOperationHistoryInfoBean = new ExamOperationHistoryInfoBean();
							examOperationHistoryInfoBean.SetRisID(risID);
							examOperationHistoryInfoBean.SetLogID(logSequenceTbl.GetNewUID(con));
							examOperationHistoryInfoBean.SetOperator(user.GetUserName());
							examOperationHistoryInfoBean.SetOperationType(CommonString.OPERATIONTYPE_ABORT_RECEPT_INT);
							examOperationHistoryInfoBean.SetOperatorminal(terminalNameStr);
							examOperationHistoryTbl.InsertExamOperationHistory(con, examOperationHistoryInfoBean);
						}

					}
					else if (status.compareTo(CommonString.STATUS_ISLATE) == 0)
					{
						// 遅刻に変更しようとしている
						// ->　現在のステータス：未受付以外はNG
						if (exStatus.compareTo(CommonString.STATUS_UNREGISTERED) != 0 &&
							exStatus.compareTo(CommonString.STATUS_ISCALLING) != 0)
						{
							throw new Exception();
						}

						//実績操作履歴情報の登録
						ExamOperationHistoryInfoBean examOperationHistoryInfoBean = new ExamOperationHistoryInfoBean();
						examOperationHistoryInfoBean.SetRisID(risID);
						examOperationHistoryInfoBean.SetLogID(logSequenceTbl.GetNewUID(con));
						examOperationHistoryInfoBean.SetOperator(user.GetUserName());
						examOperationHistoryInfoBean.SetOperationType(CommonString.OPERATIONTYPE_LATE_INT);
						examOperationHistoryInfoBean.SetOperatorminal(terminalNameStr);
						examOperationHistoryTbl.InsertExamOperationHistory(con, examOperationHistoryInfoBean);


					}
					else if (status.compareTo(CommonString.STATUS_ISCALLING) == 0)
					{
						// 呼出に変更しようとしている

						// ->　現在のステータス：未受付以外はNG
						if (exStatus.compareTo(CommonString.STATUS_UNREGISTERED) != 0 &&
							exStatus.compareTo(CommonString.STATUS_ISLATE) != 0 &&
							exStatus.compareTo(CommonString.STATUS_REST) != 0)
						{
							throw new Exception();
						}

						//実績操作履歴情報の登録
						ExamOperationHistoryInfoBean examOperationHistoryInfoBean = new ExamOperationHistoryInfoBean();
						examOperationHistoryInfoBean.SetRisID(risID);
						examOperationHistoryInfoBean.SetLogID(logSequenceTbl.GetNewUID(con));
						examOperationHistoryInfoBean.SetOperator(user.GetUserName());
						examOperationHistoryInfoBean.SetOperationType(CommonString.OPERATIONTYPE_CALL_INT);
						examOperationHistoryInfoBean.SetOperatorminal(terminalNameStr);
						examOperationHistoryTbl.InsertExamOperationHistory(con, examOperationHistoryInfoBean);


					}
					else if (status.compareTo(CommonString.STATUS_ISREGISTERED) == 0)
					{
						//  受付済に変更しようとしている

						// ->　現在のステータス：未受付or遅刻、呼出、再呼、検済以外はNG ⇒ [再呼]or[保留]は[再受]になる！	2009.06.30	EditBy A.Kobayashi
						if (exStatus.compareTo(CommonString.STATUS_UNREGISTERED) != 0 &&
							exStatus.compareTo(CommonString.STATUS_ISLATE) != 0 &&
							exStatus.compareTo(CommonString.STATUS_ISCALLING) != 0 &&
							exStatus.compareTo(CommonString.STATUS_ISFINISHED) != 0)
						{
							throw new Exception();
						}

						//実績検査日判定
						if (CommonString.FLG_ON.equals(receiptDateFlg))
						{
							orderInfoBean.SetReceitpDateFlg(true);
						}

						//受付番号を設定
						orderInfoBean.SetReceptNumber(receiptNumber);

						// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
						//if (Configuration.GetInstance().GetSystemID().equals(CommonString.SYSID_OMH))
						//{
						//	// 受付番号の頭文字を設定
						//	orderInfoBean.SetReceptInitialChar(receiptInitialChar);
						//}
						// 2011.11.15 Add NSK_H.Hashimoto End

						//患者情報の取得
						patientInfoBean = patientInfoTbl.GetPatientData(con, exMainBean.GetKanjaID());

						// ToHisInfoへの書込み(受付)

						//ToHisInfoにINSERTするかを判断する
						String risOrderSendFlag = Configuration.GetInstance().GetRisOrdersendFlag();
						if (CommonString.HIS_ORDER_FLG_VALUE.equals(orderInfoBean.GetSystemKbn()) ||
							(CommonString.RIS_ORDER_FLG_VALUE.equals(orderInfoBean.GetSystemKbn()) &&
									CommonString.SEND_FLG_VALUE.equals(risOrderSendFlag)))
						{
							// HIS通信テーブルのPKを生成
							String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
							if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
							{
								throw new Exception();
							}

							// ToHisInfoにINSERTするBeanを作成
							ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOHIS_ORDER_RECEPT, risSeqStr);
							if (orderInfoBean != null)
							{
								toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
								toHisBean.SetRisID(orderInfoBean.GetRisID());
								toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
							}
							if (orderInfoBean != null)
							{
								toHisBean.SetUserInfo(user);

								if (!Const.INT_MINVALUE.equals(terminalIDInt))
								{
									toHisBean.SetRequestTerminalID(terminalIDInt.toString());
								}
							}

							//ToHisInfo書き込み
							retBool = toHisInfoTbl.InsertToHisData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
							if (retBool == false)
							{
								throw new Exception();
							}
						}
						else
						{
							//書込みなし
							logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToHisInfo_MessageString));
							retBool = true;
						}


						// ToReportInfo, ToPacsInfoへの書込み
						DataTable systemParamDataTable = systemParamTbl.GetSystemParam(con, MasterUtil.RIS_SYSTEM, MasterUtil.RIS_FLAGS);
						if (systemParamDataTable != null && systemParamDataTable.getRowCount() > 0)
						{
							// ToReportInfoへの書込み
							if (CommonString.SEND_FLG_VALUE.equals(systemParamDataTable.getRows().get(0).get(MasterUtil.RIS_VALUE1).toString()))
							{
								// HIS通信テーブルのPKを生成
								String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
								if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
								{
									throw new Exception();
								}

								// ToReportInfoにINSERTするBeanを作成
								ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOREPORT_ORDER_RECEPT, risSeqStr);
								if (orderInfoBean != null)
								{
									toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
									toHisBean.SetRisID(orderInfoBean.GetRisID());
									toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
								}
								if (orderInfoBean != null)
								{
									toHisBean.SetUserInfo(user);

									if (!Const.INT_MINVALUE.equals(terminalIDInt))
									{
										toHisBean.SetRequestTerminalID(terminalIDInt.toString());
									}
								}

								//ToReportInfo書き込み
								retBool = toReportInfoTbl.InsertToReportData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
								if (retBool == false)
								{
									throw new Exception();
								}

								//書込み成功
								logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.addToReportInfo_MessageString));
							}
							else
							{
								//書込みなし
								logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToReportInfo_MessageString));
								retBool = true;
							}


							// ToPacsInfoへの書込み
							if (CommonString.SEND_FLG_VALUE.equals(systemParamDataTable.getRows().get(0).get(MasterUtil.RIS_VALUE2).toString()))
							{
								// HIS通信テーブルのPKを生成
								String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
								if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
								{
									throw new Exception();
								}

								// ToPacsInfoにINSERTするBeanを作成
								ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOPACS_ORDER_RECEPT, risSeqStr);
								if (orderInfoBean != null)
								{
									toHisBean.SetRisID(orderInfoBean.GetRisID());
									toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
									toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
								}
								if (orderInfoBean != null)
								{
									toHisBean.SetUserInfo(user);

									if (!Const.INT_MINVALUE.equals(terminalIDInt))
									{
										toHisBean.SetRequestTerminalID(terminalIDInt.toString());
									}
								}

								//ToReportInfo書き込み
								retBool = toPacsInfoTbl.InsertToPacsData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
								if (retBool == false)
								{
									throw new Exception();
								}

								//書込み成功
								logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.addToPacsInfo_MessageString));
							}
							else
							{
								//書込みなし
								logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToPacsInfo_MessageString));
								retBool = true;
							}

						}


						// ToHisInfoへの書込み(患者)

						//ToHisInfoにINSERTするかを判断する
						if (CommonString.SEND_FLG_VALUE.equals(sendPatientFlg))
						{
							// HIS通信テーブルのPKを生成
							String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
							if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
							{
								throw new Exception();
							}

							// ToHisInfoにINSERTするBeanを作成
							ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOHIS_PATIENT, risSeqStr);
							if (orderInfoBean != null)
							{
								toHisBean.SetRisID(orderInfoBean.GetRisID());
								toHisBean.SetMessageID1(orderInfoBean.GetKanjaID());
								toHisBean.SetMessageID2(patientInfoBean.GetKanaSimei());
							}
							if (orderInfoBean != null)
							{
								toHisBean.SetUserInfo(user);

								if (!Const.INT_MINVALUE.equals(terminalIDInt))
								{
									toHisBean.SetRequestTerminalID(terminalIDInt.toString());
								}
							}

							//ToHisInfo書き込み
							retBool = toHisInfoTbl.InsertToHisData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
							if (retBool == false)
							{
								throw new Exception();
							}
						}
						else
						{
							//書込みなし
							logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToHisInfo_MessageString));
							retBool = true;
						}


						//実績患者情報の削除
						patientResultsInfoTbl.DeletePatientResultData(con, risID);

						//実績患者情報登録
						if (!patientResultsInfoTbl.InsertPatientResultData(con, risID, patientInfoBean))
						{
							throw new Exception();
						}

						//オーダ部位の取得
						DataTable orderBuiDataTable = orderBuiInfoTbl.GetOrderBuiData(con, risID);

						//実績部位の削除
						exBuiInfoTbl.DeleteAllExBuiData(con, risID);

						if (orderBuiDataTable != null)
						{
							for (int i = 0; i < orderBuiDataTable.getRowCount(); i++)
							{
								//実績部位の登録
								if (!exBuiInfoTbl.InsertOrderBuiData(con, orderBuiDataTable.getRows().get(i)))
								{
									throw new Exception();
								}
							}
						}

						//拡張情報の取得
						DataTable extendOrderDataTable = extendOrderInfoTbl.SearchExtendOrderInfo(con, risID);

						//実績拡張情報の削除
						extendExamInfoTbl.DeleteExtendExamData(con, risID);

						ExtendExamInfoBean risExtendExamInfoBean = new ExtendExamInfoBean();
						if (extendOrderDataTable != null && extendOrderDataTable.getRowCount() > 0)
						{
							risExtendExamInfoBean.SetRisID(risID);
							risExtendExamInfoBean.SetDouishoFlg(extendOrderDataTable.getRows().get(0).get(RisExtendOrderInfoTbl.DOUISHO_FLG_COLUMN).toString());
							risExtendExamInfoBean.SetDokueiFlg(orderInfoBean.GetDokueiFlg());
							risExtendExamInfoBean.SetJissekiKaikeiFlg(extendOrderDataTable.getRows().get(0).get(RisExtendOrderInfoTbl.YOTEIKAIKEI_FLG_COLUMN).toString());
						}

						//実績拡張情報の登録
						if (!extendExamInfoTbl.InsertExtendExamInfo(con, risExtendExamInfoBean))
						{
							throw new Exception();
						}

						//現ステータスが 検済 の場合のみ
						if (CommonString.STATUS_ISFINISHED.equals(exStatus))
						{
							//実績フィルムの削除
							if (!exFilmInfoTbl.DeleteAllFilmDataByRisID(con, risID))
							{
								throw new Exception();
							}

							//実績造影剤の削除
							if (!exZoueizaiInfoTbl.DeleteAllZoueizaiDataByRisID(con, risID))
							{
								throw new Exception();
							}

							//実績手技の削除
							if (!exInfuseInfoTbl.DeleteAllInfuseDataByRisID(con, risID))
							{
								throw new Exception();
							}

							//実績撮影情報の削除
							if (!exSatueiInfoTbl.DeleteAllSatueiData(con, risID))
							{
								throw new Exception();
							}
						}

						//実績操作履歴情報の登録
						ExamOperationHistoryInfoBean examOperationHistoryInfoBean = new ExamOperationHistoryInfoBean();
						examOperationHistoryInfoBean.SetRisID(risID);
						examOperationHistoryInfoBean.SetLogID(logSequenceTbl.GetNewUID(con));
						examOperationHistoryInfoBean.SetOperator(user.GetUserName());
						examOperationHistoryInfoBean.SetOperationType(CommonString.OPERATIONTYPE_RECEPT_INT);
						examOperationHistoryInfoBean.SetOperatorminal(terminalNameStr);
						examOperationHistoryTbl.InsertExamOperationHistory(con, examOperationHistoryInfoBean);


					}
					else if (status.compareTo(CommonString.STATUS_REST) == 0)
					{
						// 保留に変更しようとしている
						// ->　現在のステータス：再呼、再受以外はNG
						if (exStatus.compareTo(CommonString.STATUS_RECALLING) != 0 &&
							exStatus.compareTo(CommonString.STATUS_REREGISTERED) != 0)
						{
							throw new Exception();
						}

						//操作ログには書かない


					}
					else if (status.compareTo(CommonString.STATUS_RECALLING) == 0)
					{
						// 再呼に変更しようとしている

						// ->　現在のステータス：保留以外はNG
						if (exStatus.compareTo(CommonString.STATUS_REST) != 0)
						{
							throw new Exception();
						}

						//実績操作履歴情報の登録
						ExamOperationHistoryInfoBean examOperationHistoryInfoBean = new ExamOperationHistoryInfoBean();
						examOperationHistoryInfoBean.SetRisID(risID);
						examOperationHistoryInfoBean.SetLogID(logSequenceTbl.GetNewUID(con));
						examOperationHistoryInfoBean.SetOperator(user.GetUserName());
						examOperationHistoryInfoBean.SetOperationType(CommonString.OPERATIONTYPE_RE_CALLL_INT);
						examOperationHistoryInfoBean.SetOperatorminal(terminalNameStr);
						examOperationHistoryTbl.InsertExamOperationHistory(con, examOperationHistoryInfoBean);


					}
					else if (status.compareTo(CommonString.STATUS_REREGISTERED) == 0)
					{
						// 再受済に変更しようとしている

						// ->　現在のステータス：再呼、[保留][再呼]以外はNG
						if (exStatus.compareTo(CommonString.STATUS_REST) != 0 &&
							exStatus.compareTo(CommonString.STATUS_RECALLING) != 0)
						{
							throw new Exception();
						}

						// 2011.09.14 Add H.Orikasa Start NML-CAT2-017
						// 受付番号:患者単位の場合は再受付時に受付番号を発番する
						if (CommonString.SYSTEMPARAM_1.equals(Configuration.GetInstance().GetSystemParam().GetReceiptKanjaValue1Str()))
						{
							//受付番号を設定
							orderInfoBean.SetReceptNumber(receiptNumber);

							// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
							//if (Configuration.GetInstance().GetSystemID().equals(CommonString.SYSID_OMH))
							//{
							//	// 受付番号の頭文字を設定
							//	orderInfoBean.SetReceptInitialChar(receiptInitialChar);
							//}
							// 2011.11.15 Add NSK_H.Hashimoto End
						}
						// 2011.09.14 Add H.Orikasa End

						//実績操作履歴情報の登録
						ExamOperationHistoryInfoBean examOperationHistoryInfoBean = new ExamOperationHistoryInfoBean();
						examOperationHistoryInfoBean.SetRisID(risID);
						examOperationHistoryInfoBean.SetLogID(logSequenceTbl.GetNewUID(con));
						examOperationHistoryInfoBean.SetOperator(user.GetUserName());
						examOperationHistoryInfoBean.SetOperationType(CommonString.OPERATIONTYPE_RE_RECEPT_INT);
						examOperationHistoryInfoBean.SetOperatorminal(terminalNameStr);
						examOperationHistoryTbl.InsertExamOperationHistory(con, examOperationHistoryInfoBean);


					}
					else
					{
						throw new Exception();
					}
				}

				//ステータス変更
				retBool = exMainTbl.UpdateExecutionStatus(con, risID, orderInfoBean, status, user);
				if (retBool == false)
				{
					throw new Exception();
				}

				// 受済に変更する場合、年齢を更新する
				if (status.compareTo(CommonString.STATUS_ISREGISTERED) == 0)
				{
					//日未定対応
					String unknownDateType	= Configuration.GetInstance().GetSystemParamValue(mstSysparam2, RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE1_COLUMN);
					String unknownDate		= Configuration.GetInstance().GetSystemParamValue(mstSysparam2, RisSystemParamTbl.UNKNOWNDATE, RisSystemParamTbl.VALUE2_COLUMN);

					//年齢の更新のために、ExMainInfoを取得する
					exMainBean = exMainTbl.GetExecutionInfoBean(con, risID);
					if (exMainBean != null && patientInfoBean != null)
					{
						//年齢の再計算　ＤＢへのステータス変更時に更新される
						String birthdayStr = patientInfoBean.GetBirthday();
						String kensaDateStr = exMainBean.GetKensaDate().toString();

						if (birthdayStr != null && kensaDateStr != null && birthdayStr.length() == 8 && kensaDateStr.length() == 8)
						{
							//日未定は年齢を計算しない
							String age = "";
							if (unknownDateType != null && unknownDate != null
								&& (CommonString.VALEU_1.equals(unknownDateType) || CommonString.VALEU_2.equals(unknownDateType))
								&& unknownDate.equals(kensaDateStr))
							{
								//年齢を計算しない
							}
							else
							{
								//年齢を計算する
								age = patientInfoBean.GetPatientAge(birthdayStr, kensaDateStr);
							}
							exMainBean.SetKensaDateAge(age);
						}

						//ステータス変更
						//変更前ステータスを渡すように変更
						retBool = exMainTbl.UpdateExecutionKensaDateAge(con, risID, exMainBean);

						if (retBool == false)
						{
							throw new Exception();
						}
					}
				}


				// 未受付への更新時、WorkListInfoの削除を行う

				// 2011.10.21 Mod H.Orikasa Start 外部検証1020-4対応
				// オーダの検査機器に関わらず削除を行う
				if (CommonString.STATUS_UNREGISTERED.equals(status))
				{
					DeleteWorkListInfo(
						risID,
						orderInfoBean.GetAccessionNo(),
						orderInfoBean.GetKensakikiID(),
						CommonString.MWMDELETE_INDEX_0,
						con);
				}
				// コメント
				//if (status == CommonString.STATUS_UNREGISTERED &&
				//    orderInfoBean.GetKensakikiID().length() > 0)
				//{
				//    DeleteWorkListInfo(
				//        risID,
				//        orderInfoBean.GetAccessionNo(),
				//        orderInfoBean.GetKensakikiID(),
				//        CommonString.MWMDELETE_INDEX_0);
				//}

				// 2011.10.21 Mod H.Orikasa End



				// すべて成功した場合はCOMMIT
				con.commit();
			}
		}
		catch( Exception e )
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retBool = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			try
			{
				con.rollback();
			}
			catch( Exception e1 )
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1 );
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}
		// end log
		logger.debug("end");

		return retBool;
	}

	/*
	/// <summary>
	/// 実績情報の連絡メモを更新する
	/// </summary>
	/// <param name="exBean"></param>
	/// <returns></returns>
	public boolean UpdateRisExecutionRenrakuMemo(ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//実績情報の連絡メモを更新する
				retBool = risExMainTbl.UpdateExecutionRenrakuMemo(con, transaction, exBean);
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/// <summary>
	/// 受付番号の取得
	///
	/// （受付番号処理がＯＦＦの場合は空値の受付番号を返す）
	///
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="trans">トランザクション</param>
	/// <param name="risID">RIS識別ID</param>
	/// <param name="kanjaID">患者ID</param>
	/// <returns>受付番号</returns>
	// 2011.11.15 Mod NSK_H.Hashimoto Start OMH-1-05
	// コメント
	// 2010.10.04 Mod DD.Chinh Start KANRO-R-6
	//private String GetReceiptNumber(Connection con, Transaction trans, String risID, String kanjaID)	// 2011.05.23 Mod H.Orikasa 業務詳細高速化対応
	//private String GetReceiptNumber(String risID, String kanjaID)													//
	//private String GetReceiptNumber(String risID)
	// 2010.10.04 Mod DD.Chinh End

	private ArrayList GetReceiptNumber(Connection con, String risID, String kanjaID) throws Exception
	// 2011.11.15 Mod NSK_H.Hashimoto End
	{
		String receiptNumberStr = "";
		//Connection con = null;		// 2011.05.23 Mod H.Orikasa 業務詳細高速化対応
		//Transaction trans = null;	//

		// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
		ArrayList strList = new ArrayList();
		String receiptInitialCharStr = "";
		// 2011.11.15 Add NSK_H.Hashimoto End

		try
		{
			//コネクションの取得
			//con = DBConnectionFactory.GetInstance().GetRisDBConnection();		// 2011.05.23 Del H.Orikasa 業務詳細高速化対応

			// 受付番号処理
			RisSystemParamTbl systemParamTbl = new RisSystemParamTbl();
			DataTable dt = systemParamTbl.GetSystemParam(con, RisSystemParamTbl.SYSTEM_COLUMN, RisSystemParamTbl.RECEIPTNO_COLUMN);

			if (dt != null && "1".equals(dt.getRows().get(0).get(RisSystemParamTbl.VALUE1_COLUMN).toString()))
			{
				// 受付番号:オーダ単位発行処理

				RisReceiptNumberAccessInfoTbl risReceiptNumberAccessInfoTbl = new RisReceiptNumberAccessInfoTbl();
				boolean retBool = false;

				//ロック
				int retryCount = 0;	// 2011.09.20 Add H.Orikasa A0098
				while (!retBool)
				{
					retBool = risReceiptNumberAccessInfoTbl.CanCheckin(con, RisReceiptNumberControlTbl.DEFAULT, risID);
					if (retBool)
					{
						// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
						retBool = risReceiptNumberAccessInfoTbl.Checkout(con, RisReceiptNumberControlTbl.DEFAULT, risID);
						// コメント
						//retBool = risReceiptNumberAccessInfoTbl.Checkout(con, trans, risID);

						// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
					}
					else
					{
						//リトライ
						Thread.sleep(200);

						//リトライ回数+1	// 2011.09.20 Add H.Orikasa A0098
						retryCount += 1;
					}

					// 2011.09.20 Add H.Orikasa Start A0098
					// 最大５０回（１０秒）で処理を抜ける
					if (!retBool && retryCount > 50)
					{
						throw new Exception();
					}
					// 2011.09.20 Add H.Orikasa End
				}

				con.commit();

				try
				{
					if (retBool)
					{
						// 2011.07.14 Del NSK_R.Akimoto Start NML-CAT2-017
						// コメント
						//// 2010.10.04 Mod DD.Chinh Start KANRO-R-6
						////関東労災による特注処理対応
						//if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
						//{
						//    //特注処理
						//    RisReceiptKanjaNumberControlTbl risReceiptKanjaNumberControlTbl = new RisReceiptKanjaNumberControlTbl();
						//    receiptNumberStr = risReceiptKanjaNumberControlTbl.GetData(con, trans, kanjaID);
						//}
						//else
						//{
						//    //通常時の処理
						//    RisReceiptNumberControlTbl risReceiptNumberControlTbl = new RisReceiptNumberControlTbl();
						//    // 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
						//    receiptNumberStr = risReceiptNumberControlTbl.GetData(con, trans, RisReceiptNumberControlTbl.DEFAULT);
						//    // コメント
						//    //receiptNumberStr = risReceiptNumberControlTbl.GetData(con, trans);
						//
						//    // 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
						//}

						//RisReceiptNumberControlTbl risReceiptNumberControlTbl = new RisReceiptNumberControlTbl();
						//receiptNumberStr = risReceiptNumberControlTbl.GetData(con, trans);
						// 2010.10.04 Mod DD.Chinh End

						// 2011.07.14 Del NSK_R.Akimoto End

						// 2011.07.14 Add NSK_R.Akimoto Start NML-CAT2-017
						RisReceiptNumberControlTbl risReceiptNumberControlTbl = new RisReceiptNumberControlTbl();
						receiptNumberStr = risReceiptNumberControlTbl.GetData(con, RisReceiptNumberControlTbl.DEFAULT);
						// 2011.07.14 Add NSK_R.Akimoto End
					}
				}
				catch (Exception e)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
				}
				finally
				{
					//ロック解除
					// 2011.03.09 Mod T.Ohyama@CIJ Start MY-1-08
					risReceiptNumberAccessInfoTbl.Uncheckout(con, RisReceiptNumberControlTbl.DEFAULT, risID);
					// コメント
					//risReceiptNumberAccessInfoTbl.Uncheckout(con, trans, risID);

					// 2011.03.09 Mod T.Ohyama@CIJ End   MY-1-08
				}


			}

			// 2011.07.14 Add NSK_R.Akimoto Start NML-CAT2-017
			if (CommonString.SYSTEMPARAM_1.equals(Configuration.GetInstance().GetSystemParam().GetReceiptKanjaValue1Str()))
			{
				// 受付番号:患者単位発行処理

				RisReceiptNumberAccessInfoTbl risReceiptNumberAccessInfoTbl = new RisReceiptNumberAccessInfoTbl();
				boolean retBool = false;

				//ロック
				int retryCount = 0;	// 2011.09.20 Add H.Orikasa A0098
				while (!retBool)
				{
					retBool = risReceiptNumberAccessInfoTbl.CanCheckin(con, RisReceiptNumberControlTbl.DEFAULT, risID);
					if (retBool)
					{
						retBool = risReceiptNumberAccessInfoTbl.Checkout(con, RisReceiptNumberControlTbl.DEFAULT, risID);
					}
					else
					{
						//リトライ
						Thread.sleep(200);

						//リトライ回数+1	// 2011.09.20 Add H.Orikasa A0098
						retryCount += 1;
					}

					// 2011.09.20 Add H.Orikasa Start A0098
					// 最大５０回（１０秒）で処理を抜ける
					if (!retBool && retryCount > 50)
					{
						throw new Exception();
					}
					// 2011.09.20 Add H.Orikasa End
				}

				con.commit();

				try
				{
					if (retBool)
					{
						RisReceiptKanjaNumberControlTbl risReceiptKanjaNumberControlTbl = new RisReceiptKanjaNumberControlTbl();
						receiptNumberStr = risReceiptKanjaNumberControlTbl.GetData(con, kanjaID);
					}
				}
				catch (Exception e)
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
				}
				finally
				{
					//ロック解除
					risReceiptNumberAccessInfoTbl.Uncheckout(con, RisReceiptNumberControlTbl.DEFAULT, risID);
				}


			}
			// 2011.07.14 Add NSK_R.Akimoto End

			// 2011.03.09 Add T.Ohyama@CIJ Start MY-1-08
			if (CommonString.SYSTEMPARAM_1.equals(Configuration.GetInstance().GetSystemParam().GetReceiptRuleValue1Str()))
			{
				// 受付番号:グループ単位発行処理

				//RIS識別IDより検査種別を特定
				RisOrderMainTbl risOrderMainTbl = new RisOrderMainTbl();
				OrderInfoBean oiBean = risOrderMainTbl.GetOrderInfoBean(con, risID);
				String ktype = oiBean.GetKensatypeID();

				//検査種別より受付番号Grを特定
				DataTable mstKensaType = Configuration.GetInstance().GetRRisKensaTypeMaster(con);
				MasterUtil masterUtil = new MasterUtil();
				DataRow row = masterUtil.FindDataRow(mstKensaType, MasterUtil.RIS_KENSATYPE_ID, ktype);
				String receiptNOKeyValue = row.get(MasterUtil.RIS_RECEIPTNUMBERKEYVALUE).toString();

				if (!StringUtils.isEmpty(receiptNOKeyValue))
				{
					RisReceiptNumberAccessInfoTbl risReceiptNumberAccessInfoTbl = new RisReceiptNumberAccessInfoTbl();
					boolean retBool = false;

					//ロック
					int retryCount = 0;	// 2011.09.20 Add H.Orikasa A0098
					while (!retBool)
					{
						retBool = risReceiptNumberAccessInfoTbl.CanCheckin(con, receiptNOKeyValue, risID);
						if (retBool)
						{
							retBool = risReceiptNumberAccessInfoTbl.Checkout(con, receiptNOKeyValue, risID);
						}
						else
						{
							//リトライ
							Thread.sleep(200);

							//リトライ回数+1	// 2011.09.20 Add H.Orikasa A0098
							retryCount += 1;
						}

						// 2011.09.20 Add H.Orikasa Start A0098
						// 最大５０回（１０秒）で処理を抜ける
						if (!retBool && retryCount > 50)
						{
							throw new Exception();
						}
						// 2011.09.20 Add H.Orikasa End
					}

					con.commit();

					try
					{
						if (retBool)
						{
							RisReceiptNumberControlTbl risReceiptNumberControlTbl = new RisReceiptNumberControlTbl();
							receiptNumberStr = risReceiptNumberControlTbl.GetData(con, receiptNOKeyValue);
							// 2011.11.15 Add NSK_H.Hashimoto Start OMH-1-05
							//if (Configuration.GetInstance().GetSystemID().equals(CommonString.SYSID_OMH))
							//{
							//	receiptInitialCharStr = risReceiptNumberControlTbl.GetInitialChar(con, trans, receiptNOKeyValue);
							//}
							// 2011.11.15 Add NSK_H.Hashimoto End
						}
					}
					catch (Exception e)
					{
						logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
					}
					finally
					{
						//ロック解除
						risReceiptNumberAccessInfoTbl.Uncheckout(con, receiptNOKeyValue, risID);
					}
				}


			}
			// 2011.03.09 Add T.Ohyama@CIJ End   MY-1-08

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

		// end log
		logger.debug("end");

		// 2011.11.15 Mod NSK_H.Hashimoto Start OMH-1-05
		// コメント
		//return receiptNumberStr;
		strList.add(receiptNumberStr);
		strList.add(receiptInitialCharStr);
		return strList;
		// 2011.11.15 Mod NSK_H.Hashimoto End
	}

	/*
	/// <summary>
	/// オーダ情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchRisOrderData( String risStr )
	{
		// parameters
		DataTable dt = null;
		Connection con = null;
		Transaction transaction = null;
		RisExBuiInfoTbl exBuiInfoTbl = new RisExBuiInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if( con != null && transaction != null && risStr != null )
			{
				dt = exBuiInfoTbl.SelectExBuiData( con, transaction, risStr );
			}

			if (dt.Rows != null && dt.Rows.Count > 0)
			{
				for(int i = 0; i < dt.Rows.Count; i++)
				{
					DataTable rrisBuiMasterData = Configuration.GetInstance().GetRRisBuiMaster();
					DataTable rrisHoukouMasterData = Configuration.GetInstance().GetRRisHoukouMaster();

					//マスタの件数分ループ
					for (int j = 0; j < rrisBuiMasterData.Rows.Count; j ++)
					{
						//マスタのStatusCode,ShortLabelを取得
						if (dt.Rows[i][RisExBuiInfoTbl.BUI_ID_COLUMN].toString() == rrisBuiMasterData.Rows[j][RisExBuiInfoTbl.BUI_ID_COLUMN].toString())
						{
							dt.Rows[i][RisExBuiInfoTbl.BUI_NAME_COLUMN] = rrisBuiMasterData.Rows[j][RisExBuiInfoTbl.BUI_NAME_COLUMN].toString();
							break;
						}
					}

					//マスタの件数分ループ
					for (int j = 0; j < rrisHoukouMasterData.Rows.Count; j ++)
					{
						//マスタのStatusCode,ShortLabelを取得
						if (dt.Rows[i][RisExBuiInfoTbl.HOUKOU_ID_COLUMN].toString() == rrisHoukouMasterData.Rows[j][RisExBuiInfoTbl.HOUKOU_ID_COLUMN].toString())
						{
							dt.Rows[i][RisExBuiInfoTbl.HOUKOU_NAME_COLUMN] = rrisHoukouMasterData.Rows[j][RisExBuiInfoTbl.HOUKOU_NAME_COLUMN].toString();
							break;
						}
					}
				}
			}

			// commit
			transaction.Commit();
		}
		catch( Exception e )
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e );

			if( transaction != null )
			{
				try
				{
					transaction.Rollback();
				}
				catch( Exception e1 )
				{
					logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1 );
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

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 実績操作履歴情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns>実績操作履歴情報</returns>
	public DataTable GetRisExamOperationHistory(String risID)
	{
		// parameters
		DataTable dt = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisExamOperationHistoryTbl risExamOperationHistoryTbl = new RisExamOperationHistoryTbl();

		// begin log
		logger.debug("begin");

        try
        {
            // get connection and begin transaction
            rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
            rTransaction = rCon.BeginTransaction();

            if (rCon != null && rTransaction != null && risID != null)
            {
				dt = risExamOperationHistoryTbl.GetRisExamOperationHistory(rCon, rTransaction, risID);

				if (dt != null)
				{
					//種別の変換
					for (int i = 0; dt.Rows.Count > i; i++)
					{
						dt.Rows[i][RisExamOperationHistoryTbl.OPERATIONTYPE_STR_COLUMN] = SqlDataChangeUnit.ChangeOperationType(dt.Rows[i][RisExamOperationHistoryTbl.OPERATIONTYPE_COLUMN].toString());
					}
				}
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
                    logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString), e1);
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

	/// <summary>
	/// 実績操作履歴情報を登録する
	/// </summary>
	/// <param name="bean">実績操作履歴情報</param>
	/// <returns>成功：true　失敗：false</returns>
	public boolean RegisterExamOperationHistory(ExamOperationHistoryInfoBean bean, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;
		RisExamOperationHistoryTbl risExamOperationHistoryTbl = new RisExamOperationHistoryTbl();
		RisLogSequenceTbl risLogSequenceTbl = new RisLogSequenceTbl();

		// begin log
		logger.debug("begin");

		try
		{
			//ユーザ端末情報の補填
			// 2011.08.16 Mod DD.Chinh@MERX Start NML-CAT2-010
			String userName = Configuration.GetInstance().GetUserAccountBean().GetUserName();
			if (bean.GetOperatorAuth().length() != 0)
			{
				userName = bean.GetOperatorAuth();
			}
			bean.SetOperator(userName);

			// 2011.08.22 Mod T.Ootsuka@MERX Start NML-2-X04
			String termName = Configuration.GetInstance().GetTerminalInfoBean().GetTerminalName();
			//String termName = Configuration.GetInstance().GetCoreController().GetTerminalInfoDataByIPAdrress(Configuration.GetInstance().GetIPAddress()).GetTerminalName();
			// 2011.08.22 Mod T.Ootsuka@MERX End
			bean.SetOperatorminal(termName);

			// コメント
			//// 2011.08.05 Mod H.Satou@MERX Start NML-CAT2-010
			//if (bean.GetOperator().length() == 0)
			//{
			//    String userName = Configuration.GetInstance().GetUserAccountBean().GetUserName();
			//    bean.SetOperator(userName);
			//}
			//String termName = Configuration.GetInstance().GetCoreController().GetTerminalInfoDataByIPAdrress(Configuration.GetInstance().GetIPAddress()).GetTerminalName();
			//bean.SetOperatorminal(termName);
			//// コメント
			////String userName = Configuration.GetInstance().GetUserAccountBean().GetUserName();
			////String termName = Configuration.GetInstance().GetCoreController().GetTerminalInfoDataByIPAdrress(Configuration.GetInstance().GetIPAddress()).GetTerminalName();
			////bean.SetOperator(userName);
			////bean.SetOperatorminal(termName);
			//
			//// 2011.08.05 Mod H.Satou@MERX End

			// 2011.08.16 Mod DD.Chinh@MERX End

			if (con != null)
			{
				String logID	= risLogSequenceTbl.GetNewUID(con);
				bean.SetLogID(logID);
				retBool = risExamOperationHistoryTbl.InsertExamOperationHistory(con, bean);
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

		return retBool;
	}

	/// <summary>
	/// WorkListTableより該当AccessionNoのレコードを削除する
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="accessionNo">ACCESSIONNO</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <param name="typeIndex">操作Index</param>
	/// <returns></returns>
	public boolean DeleteWorkListInfo(String risID, String accessionNo, String kikiID, int typeIndex, Connection con)
	{
		// parameters
		boolean retBool = false;
		MasterUtil masterUtil = new MasterUtil();

		// begin log
		logger.debug("begin");

		try
		{
			//MWMDB設定が無い場合は処理しない
			if (!Configuration.GetInstance().IsMWMDBConnectionEnabled())
			{
				return true;
			}

			//削除判断
			boolean deleteFlg = false;

			if (CommonString.MWMDELETE_INDEX_0 == typeIndex)
			{
				//削除対象
				deleteFlg = true;
			}
			else
			{
				//機器マスタの取得
				DataTable kikiDt    = Configuration.GetInstance().GetRRisKensaKikiMaster(con);
				String mwmDeleteStr = masterUtil.FindData(kikiDt, MasterUtil.RIS_MWMDELETE, MasterUtil.RIS_KENSAKIKI_ID, kikiID);
				if (mwmDeleteStr.length() > 0 &&
						"1".equals(mwmDeleteStr.substring(typeIndex - 1, typeIndex)))
				{
					//削除対象
					deleteFlg = true;
				}
			}

			//削除対象の場合
			if (deleteFlg)
			{
				//MWM削除処理

				//MWMライブラリ生成
				ArrayList logList = new ArrayList();
				ConnectionInfoBean conBean = new ConnectionInfoBean();
				conBean.SetService(Configuration.GetInstance().GetMwmServiceName());
				conBean.SetUser(Configuration.GetInstance().GetMwmUser());
				conBean.SetPassword(Configuration.GetInstance().GetMwmPassword());
				MwmManager mManager = new MwmManager(conBean, logList);
				ArrayList mwmBeanList = new ArrayList();

				//MWM情報の準備
				MWMInfoBean mwmBean = new MWMInfoBean();
				MwmOrderInfoBean mwmOrderInfoBean = new MwmOrderInfoBean();
				mwmOrderInfoBean.SetAccessionNo(accessionNo);
				mwmBean.SetOrderInfo(mwmOrderInfoBean);
				mwmBeanList.add(mwmBean);

				//MWMDB接続チェック
				if (!mManager.CheckMWMDBConnection(logList))
				{
					//接続失敗
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mwmdbConnectNo_MessageString);
					//Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
					logger.fatal(message);
					retBool = false;
				}
				else
				{
					//MWM情報の削除
					if (mManager.DeleteMWMInfo(mwmBeanList, logList))
					{
						retBool = true;
					}
					else
					{
						retBool = false;
					}
				}

				//MWM処理ログを出力する(必須)
				if (logList != null)
				{
					for (int i=0; i<logList.size(); i++)
					{
						String logStr = logList.get(i).toString();
						logger.info(logStr);
					}
				}
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/*
	//2011.11.29 Add NSK_M.Ochiai Start extends OMH-1-7
	/// <summary>
	/// WorkListTableより該当AccessionNoのレコードを削除する(REVISIONチェックONのデータのみ)
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="accessionNo">ACCESSIONNO</param>
	/// <param name="kikiID">検査機器ID</param>
	/// <returns></returns>
	public boolean DeleteRevisionWorkListInfo(String risID, String accessionNo, String kikiID)
	{
		// parameters
		boolean retBool = false;
		MasterUtil masterUtil = new MasterUtil();

		// begin log
		logger.debug("begin");

		try
		{
			//MWMDB設定が無い場合は処理しない
			if (!Configuration.GetInstance().IsMWMDBConnectionEnabled())
			{
				return true;
			}

			//削除判断
			boolean deleteFlg = false;

			//機器マスタの取得
			DataTable kikiDt    = Configuration.GetInstance().GetRRisKensaKikiMaster();
			String mwmDeleteStr = masterUtil.FindData(kikiDt, MasterUtil.RIS_MWMDELETE, MasterUtil.RIS_KENSAKIKI_ID, kikiID);

			if (mwmDeleteStr.length() > 4 &&
				mwmDeleteStr.SubString(CommonString.MWMDELETE_INDEX_5 - 1, 1) == "1")
			{
				//削除対象
				deleteFlg = true;
			}

			//削除対象の場合
			if (deleteFlg)
			{
				//MWM削除処理

				//MWMライブラリ生成
				ArrayList logList = new ArrayList();
				ConnectionInfoBean conBean = new ConnectionInfoBean();
				conBean.SetService(Configuration.GetInstance().GetMwmServiceName());
				conBean.SetUser(Configuration.GetInstance().GetMwmUser());
				conBean.SetPassword(Configuration.GetInstance().GetMwmPassword());
				MwmManager mManager = new MwmManager(conBean, ref logList);
				ArrayList mwmBeanList = new ArrayList();

				//MWM情報の準備
				MWMInfoBean mwmBean = new MWMInfoBean();
				MwmOrderInfoBean mwmOrderInfoBean = new MwmOrderInfoBean();
				mwmOrderInfoBean.SetAccessionNo(accessionNo);
				mwmBean.SetOrderInfo(mwmOrderInfoBean);
				mwmBeanList.Add(mwmBean);

				//MWMDB接続チェック
				if (!mManager.CheckMWMDBConnection(ref logList))
				{
					//接続失敗
					String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mwmdbConnectNo_MessageString);
					Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
					logger.fatal(message);
					retBool = false;
				}
				else
				{
					//MWM情報の削除
					if (mManager.DeleteMWMInfo(mwmBeanList, ref logList))
					{
						retBool = true;
					}
					else
					{
						retBool = false;
					}
				}

				//MWM処理ログを出力する(必須)
				if (logList != null)
				{
					for (int i=0; i<logList.Count; i++)
					{
						String logStr = logList[i].toString();
						logger.info(logStr);
					}
				}
			}
			else
			{
				retBool = true;
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
			if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_OMH))
			{
				Configuration.GetInstance().GetWindowController().ShowMessageBox_Error(e.Message);
			}
			retBool = false;
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	//2011.11.29 Add NSK_M.Ochiai End
	*/

	/*
	/// <summary>
	/// WorkListTableより該当AETitleのレコードを削除する
	/// </summary>
	/// <param name="aeTitle">AEタイトル</param>
	/// <returns></returns>
	public boolean DeleteWorkListInfo(String aeTitle)
	{
		// parameters
		boolean retBool = false;
		MasterUtil masterUtil = new MasterUtil();

		// begin log
		logger.debug("begin");

		try
		{
			//MWMDB設定が無い場合は処理しない
			if (!Configuration.GetInstance().IsMWMDBConnectionEnabled())
			{
				return true;
			}

			//MWM削除処理

			//MWMライブラリ生成
			ArrayList logList = new ArrayList();
			ConnectionInfoBean conBean = new ConnectionInfoBean();
			conBean.SetService(Configuration.GetInstance().GetMwmServiceName());
			conBean.SetUser(Configuration.GetInstance().GetMwmUser());
			conBean.SetPassword(Configuration.GetInstance().GetMwmPassword());
			MwmManager mManager = new MwmManager(conBean, ref logList);
			ArrayList mwmBeanList = new ArrayList();

			//MWM情報の準備
			MWMInfoBean mwmBean = new MWMInfoBean();
			mwmBean.SetSchStationAeTitle(aeTitle);
			mwmBeanList.Add(mwmBean);

			//MWMDB接続チェック
			if (!mManager.CheckMWMDBConnection(ref logList))
			{
				//接続失敗
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.mwmdbConnectNo_MessageString);
				Configuration.GetInstance().GetWindowController().ShowMessageBox_Warning(message);
				logger.fatal(message);
				retBool = false;
			}
			else
			{
				//MWM情報の削除
				if (mManager.DeleteMWMInfoAETitle(mwmBeanList, ref logList))
				{
					retBool = true;
				}
				else
				{
					retBool = false;
				}
			}

			//MWM処理ログを出力する(必須)
			if (logList != null)
			{
				for (int i=0; i<logList.Count; i++)
				{
					String logStr = logList[i].toString();
					logger.info(logStr);
				}
			}
		}
		catch (Exception e)
		{
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);
		}

		// end log
		logger.debug("end");

		return retBool;
	}
	*/

	/// <summary>
	/// 実績情報を保存する
	/// </summary>
	/// <param name="bean">実績情報</param>
	/// <param name="patientCommentList">患者コメントリスト</param>
	/// <returns></returns>
	public boolean SaveRisExecutionInfo(ExecutionInfoBean bean, ArrayList patientCommentList, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;
		RisExMainTbl			exMainTbl			= new RisExMainTbl();
		RisExtendExamInfoTbl	extendExamInfoTbl	= new RisExtendExamInfoTbl();
		RisExBuiInfoTbl			exBuiInfoTbl		= new RisExBuiInfoTbl();
		RisExSatueiInfoTbl		exSatueiInfoTbl		= new RisExSatueiInfoTbl();
		RisExZoueizaiInfoTbl	exZoueizaiInfoTbl	= new RisExZoueizaiInfoTbl();
		RisExInfuseInfoTbl		exInfuseInfoTbl		= new RisExInfuseInfoTbl();
		RisExFilmInfoTbl		exFilmInfoTbl		= new RisExFilmInfoTbl();
		RisPatientCommentTbl	patientCommentTbl	= new RisPatientCommentTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null &&
				bean != null && bean.GetRisID().length() > 0 && patientCommentList.size() > 0)
			{
				//保存フラグON
				bean.SetExamSaveFlg(CommonString.EXMAIN_EXAMSAVE_ON);

				//①EXMAINTABLEの更新
				if (!exMainTbl.SaveExecutionInfo(con, bean))
				{
					throw new Exception();
				}

				//②EXTENDEXAMINFOの更新
				if (!extendExamInfoTbl.SaveExtendExamInfo(con, bean.GetExtendExamInfoBean()))
				{
					throw new Exception();
				}

				//③EXBUITABLE, EXSATUEITABLE, EXZOUEIZAITABLE, EXINFUSETABLE, EXFILMTABLEの削除
				retBool = exBuiInfoTbl.DeleteAllExBuiData(con, bean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exSatueiInfoTbl.DeleteAllSatueiData(con, bean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exZoueizaiInfoTbl.DeleteAllZoueizaiDataByRisID(con, bean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exInfuseInfoTbl.DeleteAllInfuseDataByRisID(con, bean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exFilmInfoTbl.DeleteAllFilmDataByRisID(con, bean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}

				//④EXBUITABLE, EXSATUEITABLE, EXZOUEIZAITABLE, EXINFUSETABLE, EXFILMTABLEの登録
				for (int i=0; i<bean.GetExBuiList().size(); i++)
				{
					ExBuiBean exBuiBean = (ExBuiBean)bean.GetExBuiList().get(i);
					retBool = exBuiInfoTbl.InsertExBuiData(con, exBuiBean);
					if (retBool == false)
					{
					    throw new Exception();
					}
				}
				for (int i=0; i<bean.GetExSatueiList().size(); i++)
				{
					ExSatueiBean exSatueiBean = (ExSatueiBean)bean.GetExSatueiList().get(i);
					retBool = exSatueiInfoTbl.InsertSatueiData(con, exSatueiBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				for (int i=0; i<bean.GetExZoueizaiList().size(); i++)
				{
					ExZoueizaiBean exSatueiBean = (ExZoueizaiBean)bean.GetExZoueizaiList().get(i);
					retBool = exZoueizaiInfoTbl.InsertZoueizaiData(con, exSatueiBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				for (int i=0; i<bean.GetExInfuseList().size(); i++)
				{
					ExInfuseBean exInfuseBean = (ExInfuseBean)bean.GetExInfuseList().get(i);
					retBool = exInfuseInfoTbl.InsertInfuseData(con, exInfuseBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				for (int i=0; i<bean.GetExFilmList().size(); i++)
				{
					ExFilmBean exFilmBean = (ExFilmBean)bean.GetExFilmList().get(i);
					retBool = exFilmInfoTbl.InsertExFilmData(con, exFilmBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}

				//④PATIENTCOMMENTSの更新(共通、検査種別)
				if (patientCommentList.size() <= 2)
				{
					String cmtCommon = patientCommentList.get(0).toString();
					retBool = patientCommentTbl.UpdatePatientComment(con, bean.GetKanjaID(), MasterUtil.RIS_PATIENTCOMMENT_DEF, cmtCommon);
					if (retBool == false)
					{
						throw new Exception();
					}
					String cmtKensatype = patientCommentList.get(1).toString();
					retBool = patientCommentTbl.UpdatePatientComment(con, bean.GetKanjaID(), bean.GetKensaTypeID(), cmtKensatype);
					if (retBool == false)
					{
						throw new Exception();
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

		return retBool;
	}

	/// <summary>
	/// 実績情報の検査日、ステータスと拡張情報の検像ステータスを更新する
	/// </summary>
	/// <param name="updExmainFlg">実績メイン情報更新可否フラグ</param>
	/// <param name="exBean">実績メイン情報</param>
	/// <returns></returns>
	public boolean UpdateRisExecutionKensaDateStatus(boolean updExmainFlg, ExecutionInfoBean exBean, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;

		RisExMainTbl			risExMainTbl			= new RisExMainTbl();
		RisExtendExamInfoTbl	risExtendExamInfoTbl	= new RisExtendExamInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//実績メイン情報更新可否判断
				if (updExmainFlg && exBean != null)
				{
					//実績メイン情報の検査日、ステータスの更新
					retBool = risExMainTbl.UpdateKensaDateStatus(con, exBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}

				//実績拡張更新可否判定
				if (CommonString.KENZOU_FLG_ON.equals(Configuration.GetInstance().GetKenzouFlg()))
				{
					//一旦拡張情報を取得する
					ExtendExamInfoBean checkExtendBean = risExtendExamInfoTbl.GetExtendExamInfoData(con, exBean.GetRisID());
					if (CommonString.KENZOU_STATUS_SUMI.equals(checkExtendBean.GetKenzouStatus()))
					{
						//検像ステータスが済の場合、再へ更新する
						ExtendExamInfoBean extendBean = new ExtendExamInfoBean();
						extendBean.SetRisID(exBean.GetRisID());
						extendBean.SetKenzouStatus(CommonString.KENZOU_STATUS_SAI);

						//拡張実績情報の検像ステータスを更新する
						retBool = risExtendExamInfoTbl.UpdateKenzouStatus(con, extendBean);
						if (retBool == false)
						{
							throw new Exception();
						}
					}
					else
					{
						retBool = true;
					}
				}
				else
				{
					retBool = true;
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					con.commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					con.rollback();
				}
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retBool = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/// <summary>
	/// 実績情報を更新する(検査開始)
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns></returns>
	public boolean UpdateRisKensaStartProc(ExecutionInfoBean exBean, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;

		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//一旦最新の情報を取得する
				ExecutionInfoBean nowExBean = risExMainTbl.GetExecutionInfoBean(con, exBean.GetRisID());

				//実施回数を加算する
				int startNum = nowExBean.GetStartNumber() + 1;
				exBean.SetStartNumber(startNum);

				// 2011.03.18 Del K.Shinohara Start
				// 検査開始時は保存フラグを変更しない
				//保存フラグON
				//exBean.SetExamSaveFlg(CommonString.EXMAIN_EXAMSAVE_ON);
				// 2011.03.18 Del K.Shinohara End

				//実績メイン情報を更新する(検査開始)
				retBool = risExMainTbl.UpdateKensaStartProc(con, exBean);
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					con.commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					con.rollback();
				}
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retBool = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/*
	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns></returns>
	public boolean RestoreRisKensaData(ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl			risExMainTbl			= new RisExMainTbl();
		RisExtendExamInfoTbl	risExtendExamInfoTbl	= new RisExtendExamInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//実績情報を元に戻す
				retBool = risExMainTbl.RestoreKensaData(con, transaction, exBean);
				if (retBool == false)
				{
					throw new Exception();
				}

				//実績拡張更新可否判定
				if (Configuration.GetInstance().GetKenzouFlg() == CommonString.KENZOU_FLG_ON)
				{
					ExtendExamInfoBean extendBean = new ExtendExamInfoBean();
					extendBean.SetRisID(exBean.GetRisID());
					extendBean.SetKenzouStatus(exBean.GetExtendExamInfoBean().GetKenzouStatus());

					//拡張実績情報の検像ステータスを更新する
					retBool = risExtendExamInfoTbl.UpdateKenzouStatus(con, transaction, extendBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	// 2011.08.16 Add DD.Chinh@MERX Start NML-CAT2-010
	/// <summary>
	/// 実績情報を元に戻す
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <returns>判定</returns>
	public boolean UnReceiptData(ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl			risExMainTbl			= new RisExMainTbl();
		RisExtendExamInfoTbl	risExtendExamInfoTbl	= new RisExtendExamInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//実績情報を元に戻す
				retBool = risExMainTbl.UnReceiptData(con, transaction, exBean);
				if (retBool == false)
				{
					throw new Exception();
				}

				// コメント化(不要) 2011.08.23 Del H.Orikasa NML-CAT2-010(修正)
				////実績拡張更新可否判定
				//if (Configuration.GetInstance().GetKenzouFlg() == CommonString.KENZOU_FLG_ON)
				//{
				//    ExtendExamInfoBean extendBean = new ExtendExamInfoBean();
				//    extendBean.SetRisID(exBean.GetRisID());
				//    extendBean.SetKenzouStatus(exBean.GetExtendExamInfoBean().GetKenzouStatus());

				//    //拡張実績情報の検像ステータスを更新する
				//    retBool = risExtendExamInfoTbl.UpdateKenzouStatus(con, transaction, extendBean);
				//    if (retBool == false)
				//    {
				//        throw new Exception();
				//    }
				//}


				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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
	// 2011.08.16 Add DD.Chinh@MERX End
	*/

	/// <summary>
	/// 実績情報を更新する(検査終了)
	/// </summary>
	/// <param name="exBean">実績情報</param>
	/// <param name="patientCommentList">患者コメントリスト</param>
	/// <returns></returns>
	public boolean UpdateRisKensaFinishProc(ExecutionInfoBean exBean, ArrayList patientCommentList, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;

		RisExMainTbl				exMainTbl				= new RisExMainTbl();
		RisExtendExamInfoTbl		extendExamInfoTbl		= new RisExtendExamInfoTbl();
		RisExBuiInfoTbl				exBuiInfoTbl			= new RisExBuiInfoTbl();
		RisExSatueiInfoTbl			exSatueiInfoTbl			= new RisExSatueiInfoTbl();
		RisExZoueizaiInfoTbl		exZoueizaiInfoTbl		= new RisExZoueizaiInfoTbl();
		RisExInfuseInfoTbl			exInfuseInfoTbl			= new RisExInfuseInfoTbl();
		RisExFilmInfoTbl			exFilmInfoTbl			= new RisExFilmInfoTbl();
		RisPatientInfoTbl			patientInfoTbl			= new RisPatientInfoTbl();
		RisPatientCommentTbl		patientCommentTbl		= new RisPatientCommentTbl();
		RisPatientResultsInfoTbl	patientResultsInfoTbl	= new RisPatientResultsInfoTbl();
		// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
		RisOrderMainTbl				risOrderMainTbl			= new RisOrderMainTbl();
		// 2011.07.11 Add NSK_H.Hashimoto End

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//一旦最新の情報を取得する
				ExecutionInfoBean nowExBean = exMainTbl.GetExecutionInfoBean(con, exBean.GetRisID());

				//検査終了回数を加算する
				int endNum = nowExBean.GetEndCount() + 1;
				exBean.SetEndCount(endNum);

				//検査開始日を設定する
				if (Const.TIMESTAMP_MINVALUE.equals(exBean.GetExamStartDateTime()) )
				{
					String timeKnd = Configuration.GetInstance().GetSystemParam().GetSetstarttimeValue1();
					if (CommonString.STARTTIME_SET_NULL.equals(timeKnd))
					{
						exBean.SetExamStartDateTimeNull(true);
					}
					else if (CommonString.STARTTIME_SET_RECEIPT.equals(timeKnd))
					{
						exBean.SetExamStartDateTime(exBean.GetReceiptDateTime());
					}
					else if (CommonString.STARTTIME_SET_ENDDATE.equals(timeKnd))
					{
						exBean.SetExamStartDateTime(exBean.GetExamEndDateTime());
					}
					else if (CommonString.STARTTIME_SET_FORMSHOW.equals(timeKnd))
					{
						exBean.SetExamStartDateTime(exBean.GetExamStartDateTimeFormShow());
					}
				}

				//保存フラグON
				exBean.SetExamSaveFlg(CommonString.EXMAIN_EXAMSAVE_ON);

				// 2011.07.11 Add NSK_H.Hashimoto Start NML-CAT1-002
				if (Configuration.GetInstance().IsUnKnownDate() == true)
				{
					// 検査日(年月日)取得
					OrderInfoBean orderInfoBean = risOrderMainTbl.GetOrderInfoBean(con, exBean.GetRisID());

					//日付チェック
					String strOibKensaDate = orderInfoBean.GetKensaDate().toString();
					if (strOibKensaDate.equals(Configuration.GetInstance().GetUnKnownDateString()))
					{
						// 検査日の再設定(日未定 → 当日日付)
						String strEmbKensaDate = nowExBean.GetKensaDate();
						orderInfoBean.SetKensaDate(strEmbKensaDate);

						// ORDERMAINの更新
						if (!risOrderMainTbl.UpdateOrderInfoOnlyKensaDate(con, orderInfoBean))
						{
							throw new Exception();
						}

						// 日未定オーダフラグON
						exBean.SetUnknownDateFlg(CommonString.UNKNOWNDATE_FLG_ON);
					}
				}
				// 2011.07.11 Add NSK_H.Hashimoto End

				//①EXMAINTABLEの更新
				if (!exMainTbl.SaveExecutionInfo(con, exBean))
				{
					throw new Exception();
				}

				//②EXTENDEXAMINFOの更新
				if (!extendExamInfoTbl.SaveExtendExamInfo(con, exBean.GetExtendExamInfoBean()))
				{
					throw new Exception();
				}

				//③EXBUITABLE, EXSATUEITABLE, EXZOUEIZAITABLE, EXINFUSETABLE, EXFILMTABLEの削除
				retBool = exBuiInfoTbl.DeleteAllExBuiData(con, exBean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exSatueiInfoTbl.DeleteAllSatueiData(con, exBean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exZoueizaiInfoTbl.DeleteAllZoueizaiDataByRisID(con, exBean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exInfuseInfoTbl.DeleteAllInfuseDataByRisID(con, exBean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}
				retBool = exFilmInfoTbl.DeleteAllFilmDataByRisID(con, exBean.GetRisID());
				if (retBool == false)
				{
					throw new Exception();
				}

				//④実績患者情報の更新
				if (exBean.IsUpdatePatientResultsInfo())
				{
					PatientInfoBean patientBean = patientInfoTbl.GetPatientData(con, exBean.GetKanjaID());
					if (patientBean != null)
					{
						retBool = patientResultsInfoTbl.UpdatePatientResultData(con, exBean.GetRisID(), patientBean);
						if (retBool == false)
						{
							throw new Exception();
						}
					}
					else
					{
						throw new Exception();
					}
				}

				//⑤EXBUITABLE, EXSATUEITABLE, EXZOUEIZAITABLE, EXINFUSETABLE, EXFILMTABLEの登録
				for (int i=0; i<exBean.GetExBuiList().size(); i++)
				{
					ExBuiBean exBuiBean = (ExBuiBean)exBean.GetExBuiList().get(i);
					retBool = exBuiInfoTbl.InsertExBuiData(con, exBuiBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				for (int i=0; i<exBean.GetExSatueiList().size(); i++)
				{
					ExSatueiBean exSatueiBean = (ExSatueiBean)exBean.GetExSatueiList().get(i);
					retBool = exSatueiInfoTbl.InsertSatueiData(con, exSatueiBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				for (int i=0; i<exBean.GetExZoueizaiList().size(); i++)
				{
					ExZoueizaiBean exSatueiBean = (ExZoueizaiBean)exBean.GetExZoueizaiList().get(i);
					retBool = exZoueizaiInfoTbl.InsertZoueizaiData(con, exSatueiBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				for (int i=0; i<exBean.GetExInfuseList().size(); i++)
				{
					ExInfuseBean exInfuseBean = (ExInfuseBean)exBean.GetExInfuseList().get(i);
					retBool = exInfuseInfoTbl.InsertInfuseData(con, exInfuseBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}
				for (int i=0; i<exBean.GetExFilmList().size(); i++)
				{
					ExFilmBean exFilmBean = (ExFilmBean)exBean.GetExFilmList().get(i);
					retBool = exFilmInfoTbl.InsertExFilmData(con, exFilmBean);
					if (retBool == false)
					{
						throw new Exception();
					}
				}

				//⑥PATIENTCOMMENTSの更新(共通、検査種別)
				if (patientCommentList.size() <= 2)
				{
					String cmtCommon = patientCommentList.get(0).toString();
					retBool = patientCommentTbl.UpdatePatientComment(con, exBean.GetKanjaID(), MasterUtil.RIS_PATIENTCOMMENT_DEF, cmtCommon);
					if (retBool == false)
					{
						throw new Exception();
					}
					String cmtKensatype = patientCommentList.get(1).toString();
					retBool = patientCommentTbl.UpdatePatientComment(con, exBean.GetKanjaID(), exBean.GetKensaTypeID(), cmtKensatype);
					if (retBool == false)
					{
						throw new Exception();
					}
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					con.commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					con.rollback();
				}
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retBool = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/// <summary>
	/// ToHisInfoへ情報を登録する
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <param name="reSendFlg">再送フラグ</param>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	public boolean RegisterToHisInfoByOperation(OrderInfoBean orderBean, TerminalInfoBean terminalBean, boolean reSendFlg, String status, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;

		RisToHisInfoTbl toHisInfoTbl = new RisToHisInfoTbl();
		RisFromRisSequenceTbl fromRisSequenceTbl = new RisFromRisSequenceTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && orderBean != null && orderBean.GetRisID().length() > 0)
			{
				//オーダ区分判断
				boolean sendFlg = false;
				if (CommonString.HIS_ORDER_FLG_VALUE.equals(orderBean.GetSystemKbn()))
				{
					//Hisの場合は送る
					sendFlg = true;
				}
				else
				{
					//Risの場合→ToHisInfoへの書込み判断
					if (!CommonString.TOHISINFO_SEND_FLG_ON.equals(Configuration.GetInstance().GetRisOrdersendFlag2()))
					{
						//書込みなし
						logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToHisInfo_MessageString));
						// 2010.12.10 Add Y.Shibata Start
						//トランザクションを終了させる
						con.rollback();
						// 2010.12.10 Add Y.Shibata End
						return true;
					}
					else
					{
						//書込みあり
						sendFlg = true;
					}
				}
				if (sendFlg)
				{
					//書込みあり

					//ユーザ情報取得
					UserAccountBean userBean = Configuration.GetInstance().GetUserAccountBean();

					//シーケンスID取得
					String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
					if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
					{
						throw new Exception();
					}

					//処理タイプ設定
					String setRequestType = "";
					if (CommonString.STATUS_ISFINISHED.equals(status))
					{
						if (!reSendFlg)
						{
							setRequestType = CommonString.TOHIS_ORDER_EXECUTE;		//実績通知
						}
						else
						{
							setRequestType = CommonString.TOHIS_ORDER_RE_EXECUTE;	//実績通知（再送）
						}
					}
					else if (CommonString.STATUS_STOP.equals(status))
					{
						setRequestType = CommonString.TOHIS_ORDER_ABORT_EXECUTE;	//中止通知
					}

					// TOHISINFOにINSERTするBeanを作成
					ToHisInfoBean toHisBean = new ToHisInfoBean(setRequestType, risSeqStr);
					if (orderBean != null)
					{
						toHisBean.SetRisID(orderBean.GetRisID());
						toHisBean.SetMessageID1(orderBean.GetOrderNo());
						toHisBean.SetMessageID2(orderBean.GetKanjaID());
					}
					if (userBean != null)
					{
						toHisBean.SetUserInfo(userBean);
					}
					if (terminalBean != null &&
						terminalBean.GetTerminalID().length() > 0)
					{
						toHisBean.SetRequestTerminalID(terminalBean.GetTerminalID());

						if ("0".equals(terminalBean.GetTerminalID()))
						{
							toHisBean.SetRequestTerminalID("");
						}
					}

					//HIS送信情報を新規に登録する
					retBool = toHisInfoTbl.InsertToHisData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
					if (retBool == false)
					{
						throw new Exception();
					}
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					con.commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					con.rollback();
				}
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retBool = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/*
	/// <summary>
	/// ToHisInfoへ情報を登録する(患者)
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	///<param name="patientBean">患者情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
	public boolean RegisterToHisInfoByPatient(OrderInfoBean orderBean, PatientInfoBean patientBean, TerminalInfoBean terminalBean)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisToHisInfoTbl toHisInfoTbl = new RisToHisInfoTbl();
		RisFromRisSequenceTbl fromRisSequenceTbl = new RisFromRisSequenceTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && orderBean != null && patientBean != null && terminalBean != null)
			{
				//ユーザ情報取得
				UserAccountBean userBean = Configuration.GetInstance().GetUserAccountBean();

				//シーケンスID取得
				String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con, transaction);
				if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
				{
					throw new Exception();
				}

				//処理タイプ設定
				String setRequestType = CommonString.TOHIS_PATIENT;

				// TOHISINFOにINSERTするBeanを作成
				ToHisInfoBean toHisBean = new ToHisInfoBean(setRequestType, risSeqStr);
				if (orderBean != null)
				{
					toHisBean.SetRisID(orderBean.GetRisID());
					toHisBean.SetMessageID1(patientBean.GetKanjaID());
					toHisBean.SetMessageID2(patientBean.GetKanaSimei());
				}
				if (userBean != null)
				{
					toHisBean.SetUserInfo(userBean);
				}
				if (terminalBean != null &&
						terminalBean.GetTerminalID().length() > 0)
				{
					toHisBean.SetRequestTerminalID(terminalBean.GetTerminalID());

					if (terminalBean.GetTerminalID() == "0")
					{
						toHisBean.SetRequestTerminalID("");
					}
				}

				//HIS送信情報を新規に登録する
				retBool = toHisInfoTbl.InsertToHisData(con, transaction, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/// <summary>
	/// ToReportInfo, ToPacsInfoへ情報を登録する
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <param name="reSendFlg">再送フラグ</param>
	/// <param name="status">ステータス</param>
	/// <returns></returns>
	public boolean RegisterToReportPacsInfoByOperation(OrderInfoBean orderBean, TerminalInfoBean terminalBean, boolean reSendFlg, String status, Connection con) throws Exception
	{
		// parameters
		boolean retBool = false;

		//
		RisToHisInfoTbl			toHisInfoTbl		= new RisToHisInfoTbl();
		RisToReportInfoTbl		toReportInfoTbl		= new RisToReportInfoTbl();
		RisToPacsInfoTbl		toPacsInfoTbl		= new RisToPacsInfoTbl();
		RisFromRisSequenceTbl	fromRisSequenceTbl	= new RisFromRisSequenceTbl();
		RisSystemParamTbl		systemParamTbl		= new RisSystemParamTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && orderBean != null && orderBean.GetRisID().length() > 0)
			{
				//ユーザ情報取得
				UserAccountBean userBean = Configuration.GetInstance().GetUserAccountBean();

				DataTable systemParamDataTable = systemParamTbl.GetSystemParam(con, MasterUtil.RIS_SYSTEM, MasterUtil.RIS_FLAGS);
				if (systemParamDataTable != null && systemParamDataTable.getRows().get(0) != null)
				{
					// ToReportInfoへの書込み

					if (CommonString.SEND_FLG_VALUE.equals(systemParamDataTable.getRows().get(0).get(MasterUtil.RIS_VALUE1).toString()))
					{
						retBool = false;

						//シーケンスID取得
						String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
						if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
						{
							throw new Exception();
						}

						//処理タイプ設定
						String setRequestType = "";
						if (CommonString.STATUS_ISFINISHED.equals(status))
						{
							if (!reSendFlg)
							{
								setRequestType = CommonString.TOREPORT_ORDER_EXECUTE;		//実績通知
							}
							else
							{
								setRequestType = CommonString.TOREPORT_ORDER_RE_EXECUTE;	//実績通知（再送）
							}
						}
						else if (CommonString.STATUS_STOP.equals(status))
						{
							setRequestType = CommonString.TOREPORT_ORDER_ABORT_EXECUTE;		//中止通知
						}


						// ToReportInfoにINSERTするBeanを作成
						ToHisInfoBean toHisBean = new ToHisInfoBean(setRequestType, risSeqStr);
						if (orderBean != null)
						{
							toHisBean.SetRisID(orderBean.GetRisID());
							toHisBean.SetMessageID1(orderBean.GetOrderNo());
							toHisBean.SetMessageID2(orderBean.GetKanjaID());
						}
						if (userBean != null)
						{
							toHisBean.SetUserInfo(userBean);
						}
						if (terminalBean != null &&
						terminalBean.GetTerminalID().length() > 0)
						{
							toHisBean.SetRequestTerminalID(terminalBean.GetTerminalID());
							if (terminalBean.GetTerminalID() == "0")
							{
								toHisBean.SetRequestTerminalID("");
							}
						}

						//ToReportInfo書き込み
						retBool = toReportInfoTbl.InsertToReportData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
						if (retBool == false)
						{
							throw new Exception();
						}
					}
					else
					{
						//書込みなし
						logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToReportInfo_MessageString));
						retBool = true;
					}



					// ToPacsInfoへの書込み

					if (CommonString.SEND_FLG_VALUE.equals(systemParamDataTable.getRows().get(0).get(MasterUtil.RIS_VALUE2).toString()))
					{
						retBool = false;

						//シーケンスID取得
						String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con);
						if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
						{
							throw new Exception();
						}

						//処理タイプ設定
						String setRequestType = "";
						if (CommonString.STATUS_ISFINISHED.equals(status))
						{
							if (!reSendFlg)
							{
								setRequestType = CommonString.TOPACS_ORDER_EXECUTE;		//実績通知
							}
							else
							{
								setRequestType = CommonString.TOPACS_ORDER_RE_EXECUTE;	//実績通知（再送）
							}
						}
						else if (CommonString.STATUS_STOP.equals(status))
						{
							setRequestType = CommonString.TOPACS_ORDER_ABORT_EXECUTE;	//中止通知
						}


						// ToPacsInfoにINSERTするBeanを作成
						ToHisInfoBean toHisBean = new ToHisInfoBean(setRequestType, risSeqStr);
						if (orderBean != null)
						{
							toHisBean.SetRisID(orderBean.GetRisID());
							toHisBean.SetMessageID1(orderBean.GetOrderNo());
							toHisBean.SetMessageID2(orderBean.GetKanjaID());
						}
						if (userBean != null)
						{
							toHisBean.SetUserInfo(userBean);
						}
						if (terminalBean != null &&
						terminalBean.GetTerminalID().length() > 0)
						{
							toHisBean.SetRequestTerminalID(terminalBean.GetTerminalID());
							if ("0".equals(terminalBean.GetTerminalID()))
							{
								toHisBean.SetRequestTerminalID("");
							}
						}

						//ToPacsInfo書き込み
						retBool = toPacsInfoTbl.InsertToPacsData(con, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
						if (retBool == false)
						{
							throw new Exception();
						}
					}
					else
					{
						//書込みなし
						logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToPacsInfo_MessageString));
						retBool = true;
					}


				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					con.commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					con.rollback();
				}
			}
		}
		catch (Exception e)
		{
			// Exceptionが出たら戻り値をfalseに設定する
			retBool = false;
			logger.fatal(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbTransactionError_MessageString), e);

			try
			{
				con.rollback();
			}
			catch (Exception e1)
			{
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.dbRollBackError_MessageString);
				logger.fatal(message, e1);
			}

			throw e;
		}
		finally
		{
			//DBConnectionFactory.GetInstance().ReturnDBConnection(con);
		}

		// end log
		logger.debug("end");

		return retBool;
	}

	/*
	/// <summary>
	/// 優先フラグを更新する
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="oldYuusenFlg">旧優先フラグ</param>
	/// <param name="newYuusenFlg">新設定フラグ</param>
	/// <returns></returns>
	public boolean UpdateRisYuusenFlg(String risID, String appID, String oldYuusenFlg, String newYuusenFlg)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null && risID.length() > 0)
			{
				//①最新の情報を取得する
				ExecutionInfoBean exBean = risExMainTbl.GetExecutionInfoBean(con, transaction, risID);
				if (exBean == null)
				{
					throw new Exception();
				}

				//②新旧値の判断
				if (oldYuusenFlg == exBean.GetYuusenFlg())
				{
					//設定値の判断
					if (newYuusenFlg == CommonString.IS_NONE)
					{
						//解除の場合
						if (exBean.GetYuusenFlg() != CommonString.IS_YUU &&
						    exBean.GetYuusenFlg() != CommonString.IS_SYOU)
						{
							//最新の情報が優or承以外場合エラー
							retBool = false;
						}
					}
					else if (newYuusenFlg == CommonString.IS_YUU)
					{
						//優の場合
						if (exBean.GetYuusenFlg() == CommonString.IS_YUU)
						{
							//最新情報が優の場合エラー
							retBool = false;
						}
					}
					else if (newYuusenFlg == CommonString.IS_SYOU)
					{
						//承の場合
						if (exBean.GetYuusenFlg() == CommonString.IS_SYOU)
						{
							//最新情報が承の場合エラー
							retBool = false;
						}
					}
					else
					{
						retBool = false;
					}
				}
				else
				{
					//エラー
					retBool = false;
				}

				//③実績情報の更新
				retBool = risExMainTbl.UpdateRisYuusenFlg(con, transaction, risID, newYuusenFlg);
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	/// <summary>
	/// 感染症フラグを更新する
	/// </summary>
	/// <param name="risID"></param>
	/// <param name="appID"></param>
	/// <param name="infectionFlg"></param>
	/// <returns></returns>
	public boolean UpdateRisInfectionFlg(String risID, String appID, String infectionFlg)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null && risID.length() > 0)
			{
				//実績情報を取得する
				ExecutionInfoBean exMainBean = risExMainTbl.GetExecutionInfoBean(con, transaction, risID);

				if (exMainBean != null)
				{
					String exStatus =exMainBean.GetStatus();

					//中止、検査済み以外
					if (exStatus.compareTo(CommonString.STATUS_UNREGISTERED) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISLATE) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISCALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISREGISTERED) == 0 ||
						exStatus.compareTo(CommonString.STATUS_INOPERATION) == 0 ||
						exStatus.compareTo(CommonString.STATUS_RECALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_REST) == 0 ||
						exStatus.compareTo(CommonString.STATUS_RECALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_REREGISTERED) == 0)
					{
						//実績情報の更新
						retBool = risExMainTbl.UpdateRisInfectionFlg(con, transaction, risID, infectionFlg);
						if (retBool == false)
						{
							throw new Exception();
						}
					}
					else
					{
						// メッセージ
					}
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	/// <summary>
	/// 同意書チェックフラグを更新する
	/// </summary>
	/// <param name="risID"></param>
	/// <param name="appID"></param>
	/// <param name="douishoFlg"></param>
	/// <returns></returns>
	public boolean UpdateRisDouishoFlg(String risID, String appID, String douishoFlg)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null && risID.length() > 0)
			{
				//実績情報を取得する
				ExecutionInfoBean exMainBean = risExMainTbl.GetExecutionInfoBean(con, transaction, risID);

				if (exMainBean != null)
				{
					String exStatus = exMainBean.GetStatus();

					//中止、検査済み以外
					if (exStatus.compareTo(CommonString.STATUS_UNREGISTERED) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISLATE) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISCALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISREGISTERED) == 0 ||
						exStatus.compareTo(CommonString.STATUS_INOPERATION) == 0 ||
						exStatus.compareTo(CommonString.STATUS_RECALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_REST) == 0 ||
						exStatus.compareTo(CommonString.STATUS_RECALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_REREGISTERED) == 0)
					{
						//実績情報の更新
						retBool = risExMainTbl.UpdateRisDouishoFlg(con, transaction, risID, douishoFlg);
						if (retBool == false)
						{
							throw new Exception();
						}
					}
					else
					{
						// メッセージ
					}
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	// 2010.11.16 Add T.Nishikubo Start KANRO-R-9
	/// <summary>
	/// 薬剤発注チェックフラグを更新する
	/// </summary>
	/// <param name="risID"></param>
	/// <param name="appID"></param>
	/// <param name="pharmaFlg"></param>
	/// <returns></returns>
	public boolean UpdateRisPharmaFlg(String risID, String appID, String pharmaFlg)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null && risID.length() > 0)
			{
				//実績情報を取得する
				ExecutionInfoBean exMainBean = risExMainTbl.GetExecutionInfoBean(con, transaction, risID);

				if (exMainBean != null)
				{
					String exStatus = exMainBean.GetStatus();

					//中止、検査済み以外
					if (exStatus.compareTo(CommonString.STATUS_UNREGISTERED) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISLATE) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISCALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_ISREGISTERED) == 0 ||
						exStatus.compareTo(CommonString.STATUS_INOPERATION) == 0 ||
						exStatus.compareTo(CommonString.STATUS_RECALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_REST) == 0 ||
						exStatus.compareTo(CommonString.STATUS_RECALLING) == 0 ||
						exStatus.compareTo(CommonString.STATUS_REREGISTERED) == 0)
					{
						//実績情報の更新
						retBool = risExMainTbl.UpdateRisPharmaFlg(con, transaction, risID, pharmaFlg);
						if (retBool == false)
						{
							throw new Exception();
						}
					}
					else
					{
						// メッセージ
					}
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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
	// 2010.11.16 Add T.Nishikubo End
	*/

	/*
	/// <summary>
	/// 確保処理を行う
	/// </summary>
	/// <param name="risID">RISID</param>
	/// <param name="appID">画面ID</param>
	/// <param name="terminalID">端末ID</param>
	/// <returns></returns>
	public boolean UpdateRisKakuho(String risID, String appID, String terminalID)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null && risID.length() > 0)
			{
				//確保処理を行う
				retBool = risExMainTbl.UpdateRisKakuho(con, transaction, risID, terminalID);
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	/// <summary>
	/// オーダ削除
	/// </summary>
	/// <param name="risID"></param>
	/// <returns></returns>
	public boolean DeleteOrderData(String risID)
	{
		boolean retBool = false;
		Connection con = null;
		Transaction transaction = null;

		// begin log
		logger.debug("begin");

		try
		{
			// 2011.08.22 Del T.Ootsuka@MERX Start NML-2-X04
			////端末情報の取得
			//DataTable terminalDataTable = Configuration.GetInstance().GetRRisTerminalInfo();
			// 2011.08.22 Del T.Ootsuka@MERX End

			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null && risID.length() > 0)
			{
				//削除処理
				OrderInfoBean				orderInfoBean				= null;
				UserAccountBean				user						= null;
				//
				RisOrderBuiInfoTbl			risOrderBuiInfoTbl			= new RisOrderBuiInfoTbl();
				RisExtendOrderInfoTbl		risExtendOrderInfoTbl		= new RisExtendOrderInfoTbl();
				RisExtendExamInfoTbl		risExtendExamInfoTbl		= new RisExtendExamInfoTbl();
				RisExBuiInfoTbl				risExBuiInfoTbl				= new RisExBuiInfoTbl();
				RisOrderIndicateTbl			risOrderIndicateTbl			= new RisOrderIndicateTbl();
				RisPatientResultsInfoTbl	risPatientResultsInfoTbl	= new RisPatientResultsInfoTbl();
				RisToHisInfoTbl				risToHisInfoTbl				= new RisToHisInfoTbl();
				RisToReportInfoTbl			risToReportInfoTbl			= new RisToReportInfoTbl();
				RisToPacsInfoTbl			risToPacsInfoTbl			= new RisToPacsInfoTbl();
				RisOrderMainTbl				risOrderMainTbl				= new RisOrderMainTbl();
				RisExMainTbl				risExMainTbl				= new RisExMainTbl();
				RisSystemParamTbl			systemParamTbl				= new RisSystemParamTbl();
				RisFromRisSequenceTbl		fromRisSequenceTbl			= new RisFromRisSequenceTbl();

				//オーダ情報を取得
				orderInfoBean = risOrderMainTbl.GetOrderInfoBean(con, transaction, risID);

				//ユーザ情報を取得
				user = Configuration.GetInstance().GetUserAccountBean();

				// 端末IDを取得する

				int    terminalIDInt   = int.MinValue;
				String terminalNameStr = "";
				// 2011.08.22 Mod T.Ootsuka@MERX Start NML-2-X04
				if (Configuration.GetInstance().GetTerminalInfoBean() != null)
				{
					TerminalInfoBean terminalBean = Configuration.GetInstance().GetTerminalInfoBean();
					if (terminalBean.GetTerminalID().length() > 0)
					{
						terminalIDInt	= Integer.parseInt(terminalBean.GetTerminalID());
						orderInfoBean.SetTerminalID(terminalIDInt);
					}
					if (terminalBean.GetTerminalName() != null)
					{
						terminalNameStr	= terminalBean.GetTerminalName();
					}
				}
				// コメント
				////端末情報のループ
				//for (int i = 0; i < terminalDataTable.Rows.Count; i++)
				//{
				//    if (Configuration.GetInstance().GetIPAddress() == terminalDataTable.Rows[i][MasterUtil.RIS_TERMINALADDRESS].toString())
				//    {
				//        String terminalID = terminalDataTable.Rows[i][MasterUtil.RIS_TERMINALID].toString();
				//        if (terminalID.length() != 0)
				//        {
				//            terminalIDInt = Integer.parseInt(terminalID);
				//            orderInfoBean.SetTerminalID(terminalIDInt);
				//        }

				//        String terminalName = terminalDataTable.Rows[i][MasterUtil.RIS_TERMINALNAME].toString();
				//        if (terminalName != null)
				//        {
				//            terminalNameStr = terminalName;
				//        }
				//        break;
				//    }
				//}

				// 2011.08.22 Mod T.Ootsuka@MERX End



				// 各種情報を削除する

				retBool = risOrderBuiInfoTbl.DeleteOrderBuiData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risExtendOrderInfoTbl.DeleteExtendOrderData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risExtendExamInfoTbl.DeleteExtendExamData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risExBuiInfoTbl.DeleteAllExBuiData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risOrderIndicateTbl.DeleteOrderIndicateData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risPatientResultsInfoTbl.DeletePatientResultData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risToHisInfoTbl.DeleteToHisInfoData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risOrderMainTbl.DeleteOrderMainData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = risExMainTbl.DeleteExMainData(con, transaction, risID);
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}



				// ToReportInfo, ToPacsInfoへの書込み
				DataTable systemParamDataTable = systemParamTbl.GetSystemParam(con, transaction, MasterUtil.RIS_SYSTEM, MasterUtil.RIS_FLAGS);
				if (systemParamDataTable != null && systemParamDataTable.Rows[0] != null)
				{
					// ToReportInfoへの書込み
					if (systemParamDataTable.Rows[0][MasterUtil.RIS_VALUE1].toString() == CommonString.SEND_FLG_VALUE)
					{
						// HIS通信テーブルのPKを生成
						String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con, transaction);
						if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
						{
							throw new Exception();
						}

						// ToReportInfoにINSERTするBeanを作成
						ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOREPORT_ORDER_CANCEL, risSeqStr);
						if (orderInfoBean != null)
						{
							toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
							toHisBean.SetRisID(orderInfoBean.GetRisID());
							toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
						}
						if (orderInfoBean != null)
						{
							toHisBean.SetUserInfo(user);

							if (terminalIDInt != int.MinValue)
							{
								toHisBean.SetRequestTerminalID(terminalIDInt.toString());
							}
						}

						//ToReportInfo書き込み
						retBool = risToReportInfoTbl.InsertToReportData(con, transaction, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
						if (retBool == false)
						{
							throw new Exception();
						}

						//書込み成功
						logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.addToReportInfo_MessageString));
					}
					else
					{
						//書込みなし
						logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToReportInfo_MessageString));
						retBool = true;
					}


					// ToPacsInfoへの書込み
					// 2010.10.20 Mod K.Shinohara Start
					if (systemParamDataTable.Rows[0][MasterUtil.RIS_VALUE2].toString() == CommonString.SEND_FLG_VALUE)
					//if (systemParamDataTable.Rows[0][MasterUtil.RIS_VALUE1].toString() == CommonString.SEND_FLG_VALUE)
					// 2010.10.20 Mod K.Shinohara End
					{
						// HIS通信テーブルのPKを生成
						String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con, transaction);
						if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
						{
							throw new Exception();
						}

						// ToPacsInfoにINSERTするBeanを作成
						ToHisInfoBean toHisBean = new ToHisInfoBean(CommonString.TOPACS_ORDER_CANCEL, risSeqStr);
						if (orderInfoBean != null)
						{
							toHisBean.SetRisID(orderInfoBean.GetRisID());
							// 2010.10.20 Mod K.Shinohara Start
							// 【関東労災】MessageID1, MessageID2の登録値変更
							if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
							{
								String sMessageID1 = orderInfoBean.GetOrderNo() + "," +
									                 orderInfoBean.GetAccessionNo() + "," +
									                 orderInfoBean.GetKensatypeID() + "," +
									                 orderInfoBean.GetIraiDoctorNo() + "," +
									                 orderInfoBean.GetIraiDoctorName();
								String sMessageID2 = orderInfoBean.GetKanjaID() + "," +
									                 orderInfoBean.GetStudyInstanceUID();

								toHisBean.SetMessageID1(sMessageID1);
								toHisBean.SetMessageID2(sMessageID2);
							}
							else
							{
								toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
								toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
							}
							//toHisBean.SetMessageID1(orderInfoBean.GetOrderNo());
							//toHisBean.SetMessageID2(orderInfoBean.GetKanjaID());
							// 2010.10.20 Mod K.Shinohara End
						}
						if (orderInfoBean != null)
						{
							toHisBean.SetUserInfo(user);

							if (terminalIDInt != int.MinValue)
							{
								toHisBean.SetRequestTerminalID(terminalIDInt.toString());
							}
						}

						//ToPacsInfo書き込み
						retBool = risToPacsInfoTbl.InsertToPacsData(con, transaction, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
						if (retBool == false)
						{
							throw new Exception();
						}

						//書込み成功
						logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.addToPacsInfo_MessageString));
					}
					else
					{
						//書込みなし
						logger.info(Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.unAddToPacsInfo_MessageString));
						retBool = true;
					}

				}


				// commit
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

	/*
	/// <summary>
	/// ToReportInfoへ情報を登録する
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
	public boolean RegisterToReportInfoByCreateOrder(OrderInfoBean orderBean, TerminalInfoBean terminalBean)
	{
		// parameters
		boolean retBool = false;

		Connection		con					= null;
		Transaction		transaction			= null;
		RisToReportInfoTbl		toReportInfoTbl		= new RisToReportInfoTbl();
		RisFromRisSequenceTbl	fromRisSequenceTbl	= new RisFromRisSequenceTbl();

		// begin log
		logger.debug("begin");

		try
		{
			//登録判断
			if (Configuration.GetInstance().GetSystemParam().GetFlagsValue1() != CommonString.TOREPORTINFO_SEND_FLG_ON)
			{
				retBool = true;
				return retBool;
			}

			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && orderBean != null && orderBean.GetRisID().length() > 0)
			{
				//ユーザ情報取得
				UserAccountBean userBean = Configuration.GetInstance().GetUserAccountBean();

				//シーケンスID取得
				String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con, transaction);
				if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
				{
					throw new Exception();
				}

				//処理タイプ設定
				String setRequestType = CommonString.TOREPORT_ORDER;

				// TOREPORTINFOにINSERTするBeanを作成
				ToHisInfoBean toHisBean = new ToHisInfoBean(setRequestType, risSeqStr);
				if (orderBean != null)
				{
					toHisBean.SetRisID(orderBean.GetRisID());
					toHisBean.SetMessageID1(orderBean.GetOrderNo());
					toHisBean.SetMessageID2(orderBean.GetKanjaID());
				}
				if (userBean != null)
				{
					toHisBean.SetUserInfo(userBean);
				}
				if (terminalBean != null &&
						terminalBean.GetTerminalID().length() > 0)
				{
					toHisBean.SetRequestTerminalID(terminalBean.GetTerminalID());
					if (terminalBean.GetTerminalID() == "0")
					{
						toHisBean.SetRequestTerminalID("");
					}
				}

				//ToReportInfo書き込み
				retBool = toReportInfoTbl.InsertToReportData(con, transaction, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	/// <summary>
	/// ToPacsInfoへ情報を登録する
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="terminalBean">端末情報</param>
	/// <returns></returns>
	public boolean RegisterToPacsInfoByCreateOrder(OrderInfoBean orderBean, TerminalInfoBean terminalBean)
	{
		// parameters
		boolean retBool = false;

		Connection		con					= null;
		Transaction		transaction			= null;
		RisToPacsInfoTbl		toPacsInfoTbl		= new RisToPacsInfoTbl();
		RisFromRisSequenceTbl	fromRisSequenceTbl	= new RisFromRisSequenceTbl();

		// begin log
		logger.debug("begin");

		try
		{
			//登録判断
			if (Configuration.GetInstance().GetSystemParam().GetFlagsValue2() != CommonString.TOPACSINFO_SEND_FLG_ON)
			{
				retBool = true;
				return retBool;
			}

			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && orderBean != null && orderBean.GetRisID().length() > 0)
			{
				//ユーザ情報取得
				UserAccountBean userBean = Configuration.GetInstance().GetUserAccountBean();

				//シーケンスID取得
				String risSeqStr = fromRisSequenceTbl.GetNewRisUID(con, transaction);
				if (risSeqStr == null || (risSeqStr != null && risSeqStr.length() == 0))
				{
					throw new Exception();
				}

				//処理タイプ設定
				String setRequestType = CommonString.TOPACS_ORDER;

				// TOPACSINFOにINSERTするBeanを作成
				ToHisInfoBean toHisBean = new ToHisInfoBean(setRequestType, risSeqStr);
				if (orderBean != null)
				{
					toHisBean.SetRisID(orderBean.GetRisID());
					// 2010.10.20 Mod K.Shinohara Start
					// 【関東労災】MessageID1, MessageID2の登録値変更
					if (Configuration.GetInstance().GetSystemID().Equals(CommonString.SYSID_KANRO))
					{
						String sMessageID1 = orderBean.GetOrderNo() + "," +
									         orderBean.GetAccessionNo() + "," +
									         orderBean.GetKensatypeID() + "," +
									         orderBean.GetIraiDoctorNo() + "," +
									         orderBean.GetIraiDoctorName();
						String sMessageID2 = orderBean.GetKanjaID() + "," +
									         orderBean.GetStudyInstanceUID();

						toHisBean.SetMessageID1(sMessageID1);
						toHisBean.SetMessageID2(sMessageID2);
					}
					else
					{
						toHisBean.SetMessageID1(orderBean.GetOrderNo());
						toHisBean.SetMessageID2(orderBean.GetKanjaID());
					}
					//toHisBean.SetMessageID1(orderBean.GetOrderNo());
					//toHisBean.SetMessageID2(orderBean.GetKanjaID());
					// 2010.10.20 Mod K.Shinohara End
				}
				if (userBean != null)
				{
					toHisBean.SetUserInfo(userBean);
				}
				if (terminalBean != null &&
						terminalBean.GetTerminalID().length() > 0)
				{
					toHisBean.SetRequestTerminalID(terminalBean.GetTerminalID());
					if (terminalBean.GetTerminalID() == "0")
					{
						toHisBean.SetRequestTerminalID("");
					}
				}

				//PACS送信情報を新規に登録する
				retBool = toPacsInfoTbl.InsertToPacsData(con, transaction, toHisBean, Configuration.GetInstance().GetRisDbUserStr());
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	/// <summary>
	/// 指示情報を更新する
	/// </summary>
	/// <param name="appID">画面ID</param>
	/// <param name="exBean">実績情報</param>
	/// <returns></returns>
	public boolean UpdateRisSijiInfo(String appID, ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//実績情報の更新
				retBool = risExMainTbl.UpdateRisSijiInfo(con, transaction, exBean);
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
    /// <summary>
	/// RIS実績情報の日時情報を更新する
	/// </summary>
    /// <param name="exBean">実績情報</param>
	/// <returns>変更成功：true　変更失敗：false</returns>
    public boolean UpdateRisExamTimestamp(ExecutionInfoBean exBean)
    {
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && exBean != null && exBean.GetRisID().length() > 0)
			{
				//実績情報の更新
				retBool = risExMainTbl.UpdateRisExamTimestamp(con, transaction, exBean);
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	/// <summary>
	/// 検像情報更新を行う
	/// </summary>
	/// <param name="exExamBean">拡張実績情報</param>
	/// <returns></returns>
	public boolean UpdateKenzouOperation(ExtendExamInfoBean exExamBean, String bikouStr)
	{
		// parameters
		boolean retBool = false;

		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl risExMainTbl = new RisExMainTbl();
		RisExtendExamInfoTbl risExtendExamInfoTbl = new RisExtendExamInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && exExamBean != null && exExamBean.GetRisID().length() > 0)
			{
				//検像情報更新を行う
				retBool = risExtendExamInfoTbl.UpdateKenzouStatusOperation(con, transaction, exExamBean);
				if (retBool == false)
				{
					throw new Exception();
				}

				//実績情報の備考を更新する
				retBool = risExMainTbl.UpdateExecutionBikou(con, transaction, exExamBean.GetRisID(), bikouStr);
				if (retBool == false)
				{
					throw new Exception();
				}

				if (retBool)
				{
					// 成功した場合はCOMMIT
					transaction.Commit();
				}
				else
				{
					// 失敗した場合はROLLBACK
					transaction.Rollback();
				}
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

	/*
	/// <summary>
	/// 進捗の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
	public String GetRisStatus(String risID)
	{
		// parameters
		ExecutionInfoBean exBean = null;
		Connection con = null;
		Transaction transaction = null;

		RisExMainTbl exMainTbl = new RisExMainTbl();

		String status = "";

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null)
			{
				exBean = exMainTbl.GetExecutionInfoBean(con, transaction, risID);

				if (exBean != null)
				{
					status = exBean.GetStatus();
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

		return status;
	}
	*/

	/*
	// 2011.02.17 Add H.Orikasa Start
	/// <summary>
	/// 実績情報のみを取得する
	/// </summary>
	/// <param name="risUID">取得対象のRisID</param>
	/// <returns>実績情報</returns>
	public ExecutionInfoBean GetExecutionInfoBeanOnly(String risID)
	{
		// parameters
		ExecutionInfoBean exBean = null;
		Connection con = null;
		Transaction transaction = null;
		RisExMainTbl exMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risID != null)
			{
				exBean = exMainTbl.GetExecutionInfoBean(con, transaction, risID);
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

		return exBean;
	}
	// 2011.02.17 Add H.Orikasa End
	*/

	/*
	/// <summary>
	///
	/// </summary>
	private class ErrorCatchException extends ApplicationException
	{
	}
	*/

	/*
	//2011.11.21 Add NSK_M.Ochiai Start extends OMH-1-7
	/// <summary>
	/// 最撮影(Revision)処理
	/// </summary>
	/// <param name="bean">実績情報</param>
	/// <returns></returns>
	public boolean UpdateRisToRevision(ExecutionInfoBean bean)
	{
		// parameters
		bool					retBool				= false;
		Connection		con					= null;
		Transaction		transaction			= null;
		RisOrderBuiInfoTbl		orderBuiInfoTbl		= new RisOrderBuiInfoTbl();
		RisExBuiInfoTbl			exBuiInfoTbl		= new RisExBuiInfoTbl();
		RisExMainTbl			exMainTbl			= new RisExMainTbl();
		RisExtendExamInfoTbl	extendExamInfoTbl	= new RisExtendExamInfoTbl();
		RisExSatueiInfoTbl		exSatueiInfoTbl		= new RisExSatueiInfoTbl();
		RisExZoueizaiInfoTbl	exZoueizaiInfoTbl	= new RisExZoueizaiInfoTbl();
		RisExInfuseInfoTbl		exInfuseInfoTbl		= new RisExInfuseInfoTbl();
		RisExFilmInfoTbl		exFilmInfoTbl		= new RisExFilmInfoTbl();
		RisPatientCommentTbl	patientCommentTbl	= new RisPatientCommentTbl();
		RisLogSequenceTbl		logSequenceTbl		= new RisLogSequenceTbl();
		RisExtendOrderInfoTbl	extendOrderInfoTbl	= new RisExtendOrderInfoTbl();
		RisOrderMainTbl			orderMainTbl		= new RisOrderMainTbl();

		OrderInfoBean			orderInfoBean		= null;

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null &&
				bean != null && bean.GetRisID().length() > 0)
			{
				//②EXBUITABLEの更新
				if (!exMainTbl.UpdateRisRevisionInfo(con, transaction, bean))
				{
					throw new ErrorCatchException();
				}


				//②EXBUITABLE, EXSATUEITABLE, EXZOUEIZAITABLE, EXINFUSETABLE, EXFILMTABLEの削除
				retBool = exBuiInfoTbl.DeleteAllExBuiData(con, transaction, bean.GetRisID());
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = exSatueiInfoTbl.DeleteAllSatueiData(con, transaction, bean.GetRisID());
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = exZoueizaiInfoTbl.DeleteAllZoueizaiDataByRisID(con, transaction, bean.GetRisID());
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = exInfuseInfoTbl.DeleteAllInfuseDataByRisID(con, transaction, bean.GetRisID());
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}
				retBool = exFilmInfoTbl.DeleteAllFilmDataByRisID(con, transaction, bean.GetRisID());
				if (retBool == false)
				{
					throw new ErrorCatchException();
				}

				//③EXTENDEXAMINFOの削除
				if (!extendExamInfoTbl.DeleteExtendExamData(con, transaction, bean.GetRisID()))
				{
					throw new ErrorCatchException();
				}

				//④部位情報を再作成
				//拡張情報の取得
				DataTable extendOrderDataTable = extendOrderInfoTbl.SearchExtendOrderInfo(con, transaction, bean.GetRisID());

				ExtendExamInfoBean risExtendExamInfoBean = new ExtendExamInfoBean();
				if (extendOrderDataTable != null && extendOrderDataTable.Rows.Count > 0)
				{
					risExtendExamInfoBean.SetRisID(bean.GetRisID());
					risExtendExamInfoBean.SetDouishoFlg(extendOrderDataTable.Rows[0][RisExtendOrderInfoTbl.DOUISHO_FLG_COLUMN].toString());
					risExtendExamInfoBean.SetDokueiFlg(Configuration.GetInstance().GetCoreController().GetRisOrderInfo(bean.GetRisID()).GetDokueiFlg());
					risExtendExamInfoBean.SetJissekiKaikeiFlg(extendOrderDataTable.Rows[0][RisExtendOrderInfoTbl.YOTEIKAIKEI_FLG_COLUMN].toString());
				}

				//実績拡張情報の登録
				if (!extendExamInfoTbl.InsertExtendExamInfo(con, transaction, risExtendExamInfoBean))
				{
					throw new Exception();
				}

				//オーダ部位の取得
				DataTable orderBuiDataTable = orderBuiInfoTbl.GetOrderBuiData(con, transaction, bean.GetRisID());

				if (orderBuiDataTable != null)
				{
					for (int i = 0; i < orderBuiDataTable.Rows.Count; i++)
					{
						//実績部位の登録
						if (!exBuiInfoTbl.InsertOrderBuiData(con, transaction, orderBuiDataTable.Rows[i]))
						{
							throw new Exception();
						}
					}
				}

				orderInfoBean = orderMainTbl.GetOrderInfoBean(con, transaction, bean.GetRisID());

				//WorkListInfo削除
				if (!DeleteRevisionWorkListInfo(
					bean.GetRisID(),
					orderInfoBean.GetAccessionNo(),
					bean.GetKensaKikiID()))
				{
					throw new Exception();
				}

			}
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

		return retBool;
	}
	//2011.11.21 Add NSK_M.Ochiai End
	*/
}
