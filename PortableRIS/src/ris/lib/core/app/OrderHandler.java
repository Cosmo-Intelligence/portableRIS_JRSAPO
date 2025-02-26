package ris.lib.core.app;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.lib.core.Configuration;
import ris.lib.core.bean.ExtendOrderInfoBean;
import ris.lib.core.bean.OrderBuiBean;
import ris.lib.core.bean.OrderInfoBean;
import ris.lib.core.bean.TerminalInfoBean;
import ris.lib.core.database.MasterInfoTbl;
import ris.lib.core.database.RisBuiSummaryView;
import ris.lib.core.database.RisExtendOrderInfoTbl;
import ris.lib.core.database.RisOrderBuiInfoTbl;
import ris.lib.core.database.RisOrderMainTbl;
import ris.lib.core.database.RisSummaryView;
import ris.lib.core.util.MasterUtil;
import ris.lib.core.util.MessageParameter;
import ris.lib.core.util.OrderFormat;
import ris.lib.core.util.OrderSearchParameter;
import ris.portable.util.DataRow;
import ris.portable.util.DataTable;


/// <summary>
/// オーダ情報管理クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.26	: 112478 / A.Kobayashi	: original
/// V2.01.00    extends 2011.08.22    extends 999999 / T.Ootsuka    extends NML-2-X04
///
/// </summary>
public class OrderHandler
{
	public static Log logger = LogFactory.getLog(OrderHandler.class);

	//private System.Configuration.AppSettingsReader configurationAppSettings = null;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public OrderHandler()
	{
		//configurationAppSettings = new System.Configuration.AppSettingsReader();
	}

	/// <summary>
	/// オーダ情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchRisOrderData(OrderSearchParameter param, Connection con) throws Exception
	{
		// parameters
		DataTable dt = null;
		DataTable getDt = null;
		DataTable buiDt = null;
		RisSummaryView risSummaryView = new RisSummaryView();
		RisBuiSummaryView risBuiSummaryView = new RisBuiSummaryView();
		OrderFormat orderFormat = new OrderFormat();

		// begin log
		logger.debug("begin");

		try
		{
			//端末情報の取得
			// 2011.08.22 Mod T.Ootsuka@MERX Start NML-2-X04
			TerminalInfoBean termBean = Configuration.GetInstance().GetTerminalInfoBean();
			//TerminalInfoBean termBean = Configuration.GetInstance().GetCoreController().GetTerminalInfoDataByIPAdrress(Configuration.GetInstance().GetIPAddress());
			// 2011.08.22 Mod T.Ootsuka@MERX End

			if (con != null && param != null)
			{
				getDt = risSummaryView.SearchOrderData(con, param);
			}

			//オーダ情報を形成する
			// 一ノ瀬保留
			//dt = orderFormat.OrderFormatData(con, getDt, buiDt, param, termBean);

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

		return dt;
	}

	/*
	/// <summary>
	/// オーダ情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public ArrayList SearchRisOrderData(ArrayList risIDArrayList)
	{
		// parameters
		ArrayList arrayList = new ArrayList();
		Connection con = null;
		Transaction transaction = null;
		RisSummaryView risSummaryView = new RisSummaryView();
		RisBuiSummaryView risBuiSummaryView = new RisBuiSummaryView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && risIDArrayList != null)
			{
				arrayList = risSummaryView.SearchOrderData(con, transaction, risIDArrayList);
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

		return arrayList;
	}
	*/

	/*
	/// <summary>
	/// 印刷オーダ情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchPrintSummaryData(OrderSearchParameter param)
	{
		// parameters
		DataTable getDt = null;
		Connection con = null;
		Transaction transaction = null;
		PrintSummaryView printSummaryView = new PrintSummaryView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				getDt = printSummaryView.SearchPrintSummaryData(con, transaction, param);
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

		return getDt;
	}
	*/

	/*
	/// <summary>
	/// オーダ一覧の件数をカウントする
	/// </summary>
	/// <param name="param">　【検索条件】
	/// ・患者ID      ：「*」をサポート
	/// ・患者漢字氏名：「*」をサポート
	/// ・患者カナ氏名：「*」をサポート
	/// ・入外区分    ：1=外来、2=入院
	/// ・依頼日      ：期間検索をサポート
	/// ・オーダ種別  ：複数指定をサポート
	/// ・依頼科コード：複数指定をサポート
	/// ・依頼医コード：複数指定をサポート
	/// ・予定検査日  ：期間検索をサポート
	/// ・担当医      ：                  </param>
	/// <returns>検索結果の件数</returns>
	public int CountRisOrderData(OrderSearchParameter param)
	{
		// parameters
		int orderCount = 0;
		Connection con = null;
		Transaction transaction = null;
		RisSummaryView risSummaryView = new RisSummaryView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				orderCount = risSummaryView.CountOrderData(con, transaction, param);
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

		return orderCount;
	}
	*/

	/// <summary>
	/// オーダ部位情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchRisOrderBuiData(OrderSearchParameter param, Connection con) throws Exception
	{
		// parameters
		DataTable dt = null;
		RisBuiSummaryView risBuiSummaryView = new RisBuiSummaryView();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && param != null)
			{
				// 2011.03.04 Mod K.Shinohara Start
				// RIS_IDの件数により処理を分ける
				String[] risIDs = param.GetRisIDList().split(",");

				if (risIDs.length == 1)
				{
					// RisIDが1件の場合
					dt = risBuiSummaryView.SearchBuiData(con, risIDs[0]);
				}
				/* 一ノ瀬保留
				else if (risIDs.length > 1)
				{
					// RisIDがN件の場合
					dt = new DataTable();

					for (int i = 0; i < risIDs.length; i++)
					{
						DataTable dtBuf = risBuiSummaryView.SearchBuiData(con, risIDs[i]);

						// データが取得できた場合
						if (dtBuf != null && dtBuf.getRowCount() > 0)
						{
							// 1回目のみ実行
							if (dt.getRowCount() <= 0)
							{
								dt = dtBuf;
							}

							// 取得した部位情報を追加する
							for (DataRow row : dtBuf.getRows())
							{
								dt.Rows.Add(row.ItemArray);
							}
						}
					}

					// ID、部位NOでソートする
					if (dt.Rows.Count > 0)
					{
						DataTable newTable = dt.Clone();
						newTable.Rows.clear();
						DataRow[] rows = dt.Select("", RisBuiSummaryView.RIS_ID_COLUMN + "," + RisBuiSummaryView.NO_COLUMN);
						for (DataRow row : rows)
						{
							newTable.Rows.Add(row.ItemArray);
						}
						dt = newTable;
					}
				}
				else
				{
					// RisIDが0件の場合
					dt = new DataTable(RisBuiSummaryView.VIEW_NAME);
					dt.Rows.clear();
				}
				//dt = risBuiSummaryView.SearchBuiData(con, transaction, param);
				// 2011.03.04 Mod K.Shinohara End
				*/
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

		return dt;
	}

	/// <summary>
	/// オーダ情報を取得する
	/// </summary>
	/// <param name="risID">取得対象のRisID</param>
	/// <param name="satueiFinishFlg">撮影済ボタンフラグ(1：撮影済ボタン押下時動作、0：それ以外の動作)</param>
	/// <returns>オーダ情報</returns>
	// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
	public OrderInfoBean GetRisOrderData(String risID, int satueiFinishFlg, Connection con) throws Exception
//	public OrderInfoBean GetRisOrderData(String risID, Connection con) throws Exception
	// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加
	{
		// parameters
		OrderInfoBean orderInfoBean = null;
		RisOrderMainTbl			risOrderMainTbl			= new RisOrderMainTbl();
		RisExtendOrderInfoTbl	risExtendOrderInfoTbl	= new RisExtendOrderInfoTbl();
		RisOrderBuiInfoTbl		risOrderBuiInfoTbl		= new RisOrderBuiInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && risID != null)
			{
				orderInfoBean = risOrderMainTbl.GetOrderInfoBean(con, risID);

				// 2023.07.26 Mod Nishihara@COSMO Start 撮影済ボタン機能追加
				if (orderInfoBean != null && satueiFinishFlg != 1)
//				if (orderInfoBean != null)
				// 2023.07.26 Mod Nishihara@COSMO End 撮影済ボタン機能追加
				{
					ExtendOrderInfoBean extendOrderInfoBean = risExtendOrderInfoTbl.GetExtendOrderInfoBean(con, risID);
					orderInfoBean.SetExtendOrderInfoBean(extendOrderInfoBean);

					//オーダ部位情報を取得する
					ArrayList buiList = new ArrayList();
					DataTable buiDt = GetRisOrderBuiInfo(con, risID);
					for (int i = 0; i < buiDt.getRowCount(); i++)
					{
						DataRow row = buiDt.getRows().get(i);
						OrderBuiBean orderBuiBean = risOrderBuiInfoTbl.CreateOrderBuiBean(row);
						buiList.add(orderBuiBean);
					}
					orderInfoBean.ReconstructOrderBuiList(buiList);
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return orderInfoBean;
	}

	/*
	/// <summary>
	/// RIS_IDを元に撮影部位を取得する(RRIS)
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
	public DataTable GetRisOrderBuiInfo(String risID)
	{
		// parameters
		DataTable dt = null;
		Connection con = null;
		Transaction transaction = null;
		RisOrderBuiInfoTbl orderBuiInfoTbl = new RisOrderBuiInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null)
			{
				dt = orderBuiInfoTbl.GetOrderBuiData(con, transaction, risID);
			}

			if (dt != null)
			{
				// 1件毎の処理
				for (int i = 0; i < dt.Rows.Count; i++)
				{
					// 現在のレコード
					DataRow curOrderRow = dt.Rows[i];

					//オーダ部位情報を補填する
					curOrderRow = MargeOrderBuiData(con, transaction, curOrderRow);
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

		return dt;
	}
	*/

	/// <summary>
	/// RIS_IDを元に撮影部位を取得する(RRIS)
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="transaction">トランザクション</param>
	/// <param name="risID">RIS_ID</param>
	/// <returns></returns>
	public DataTable GetRisOrderBuiInfo(Connection con, String risID) throws Exception
	{
		// parameters
		DataTable dt = null;
		RisOrderBuiInfoTbl orderBuiInfoTbl = new RisOrderBuiInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			if (con != null && risID.length() > 0)
			{
				dt = orderBuiInfoTbl.GetOrderBuiData(con, risID);
			}

			if (dt != null)
			{
				// 1件毎の処理
				for (int i = 0; i < dt.getRowCount(); i++)
				{
					// 現在のレコード
					DataRow curOrderRow = dt.getRows().get(i);

					//オーダ部位情報を補填する
					curOrderRow = MargeOrderBuiData(con, curOrderRow);
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		// end log
		logger.debug("end");

		return dt;
	}

	/// <summary>
	/// オーダ部位情報を補填する
	/// </summary>
	/// <param name="con">コネクション</param>
	/// <param name="transaction">トランザクション</param>
	/// <param name="row"></param>
	private DataRow MargeOrderBuiData(Connection con, DataRow row) throws Exception
	{
		MasterInfoTbl masterInfoTbl = new MasterInfoTbl();

		if (row.get(RisOrderBuiInfoTbl.NO_COLUMN) != null)
		{
			if (row.get(RisOrderBuiInfoTbl.NO_COLUMN).toString().length() == 1)
			{
				row.put(RisOrderBuiInfoTbl.NO_COLUMN, "0" + row.get(RisOrderBuiInfoTbl.NO_COLUMN).toString());
			}
		}

		if (row.get(RisOrderBuiInfoTbl.BUI_ID_COLUMN) != null)
		{
			DataRow dataRow = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_BUIMASTER, RisOrderBuiInfoTbl.BUI_ID_COLUMN, row.get(RisOrderBuiInfoTbl.BUI_ID_COLUMN).toString());

			if (dataRow != null)
			{
				row.put(RisOrderBuiInfoTbl.BUI_NAME_COLUMN, dataRow.get(RisOrderBuiInfoTbl.BUI_NAME_COLUMN).toString());

				String buibunruiID = dataRow.get(MasterUtil.RIS_BUIBUNRUI_ID).toString();
				if (buibunruiID.length() > 0)
				{
					DataRow dataRow2 = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_BUIBUNRUIMASTER, MasterUtil.RIS_BUIBUNRUI_ID, buibunruiID);
					if (dataRow2 != null)
					{
						row.put(RisOrderBuiInfoTbl.BUIBUNRUI_NAME_COLUMN, dataRow2.get(MasterUtil.RIS_BUIBUNRUI_NAME).toString());
					}
				}
			}
		}

		if (row.get(RisOrderBuiInfoTbl.HOUKOU_ID_COLUMN) != null)
		{
			DataRow dataRow = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_HOUKOUMASTER, RisOrderBuiInfoTbl.HOUKOU_ID_COLUMN, row.get(RisOrderBuiInfoTbl.HOUKOU_ID_COLUMN).toString());

			if (dataRow != null)
			{
				row.put(RisOrderBuiInfoTbl.HOUKOU_NAME_COLUMN, dataRow.get(RisOrderBuiInfoTbl.HOUKOU_NAME_COLUMN).toString());
			}
		}

		if (row.get(RisOrderBuiInfoTbl.SAYUU_ID_COLUMN) != null)
		{
			DataRow dataRow = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_SAYUUMASTER, RisOrderBuiInfoTbl.SAYUU_ID_COLUMN, row.get(RisOrderBuiInfoTbl.SAYUU_ID_COLUMN).toString());

			if (dataRow != null)
			{
				row.put(RisOrderBuiInfoTbl.SAYUU_NAME_COLUMN, dataRow.get(RisOrderBuiInfoTbl.SAYUU_NAME_COLUMN).toString());
			}
		}

		if (row.get(RisOrderBuiInfoTbl.KENSAHOUHOU_ID_COLUMN) != null)
		{
			DataRow dataRow = masterInfoTbl.GetMasterDataTable(con, MasterUtil.RIS_KENSAHOUHOUMASTER, RisOrderBuiInfoTbl.KENSAHOUHOU_ID_COLUMN, row.get(RisOrderBuiInfoTbl.KENSAHOUHOU_ID_COLUMN).toString());
			if (dataRow != null)
			{
				row.put(RisOrderBuiInfoTbl.KENSAHOUHOU_NAME_COLUMN, dataRow.get(RisOrderBuiInfoTbl.KENSAHOUHOU_NAME_COLUMN).toString());
			}
		}

		return row;
	}

	/*
	/// <summary>
	/// 連絡メモ情報の更新
	/// </summary>
	/// <param name="admissionUser">操作者</param>
	/// <param name="memo">連絡メモ</param>
	/// <returns></returns>
	public boolean UpdateRisRenrakuMemo(String risID, UserAccountBean userAccount, String memo)
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

			if (con != null && transaction != null && memo != null)
			{
				retBool = risExMainTbl.UpdateRisRenrakuMemo(con, transaction, risID, userAccount, memo);
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

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// 検査室情報の更新
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="exRoomID">検査室情報</param>
	/// <returns>成功：true　失敗：false</returns>
	public boolean UpdateRisExRoom(String risID, String exRoomID)
	{
		// parameters
		boolean retBool = false;
		Connection con = null;
		Transaction transaction = null;
		RisOrderMainTbl risOrderMainTbl = new RisOrderMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && exRoomID != null)
			{
				retBool = risOrderMainTbl.UpdateRisExRoom(con, transaction, risID, exRoomID);
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

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// オーダ補足情報を取得する
	/// </summary>
	/// <param name="risID">ユニークID</param>
	/// <returns>オーダ補足情報</returns>
	public DataTable GetOrderIndicateTable(String risID)
	{
		// parameters
		DataTable dt = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisOrderIndicateTbl risOrderIndicateTbl = new RisOrderIndicateTbl();
		RisPreDefinedCommentMaster preDefinedCommentMaster = new RisPreDefinedCommentMaster();

		// begin log
		logger.debug("begin");

		try
		{
			//区切り文字
			DataTable systemDefineDataTable = Configuration.GetInstance().GetCoreController().GetRisSystemDefine();
			String mark = "";
			if (systemDefineDataTable != null && systemDefineDataTable.Rows.Count > 0)
			{
				mark = systemDefineDataTable.Rows[0][RisSystemDefineTbl.MARKERCHARACTER_COLUMN].toString();
			}

			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && risID != null)
			{
				dt = risOrderIndicateTbl.GetOrderIndicateTable(rCon, rTransaction, risID);

				if (dt != null && dt.Rows != null && dt.Rows.Count > 0)
				{
					String commentStr = dt.Rows[0][RisOrderIndicateTbl.ORDERCOMMENT_ID_COLUMN].toString();
					String orderComment = "";

					if (commentStr != null && commentStr.length() > 0)
					{
						String[] commentID = commentStr.Split(char.Parse(mark));
						for (int i = 0; i < commentID.length(); i++)
						{
							String commentName = preDefinedCommentMaster.GetCommentName(rCon, rTransaction, commentID[i]);
							if (commentName.length() > 0)
							{
								if (i > 0)
								{
									orderComment = orderComment + "\r\n" + commentName;
								}
								else
								{
									orderComment = commentName;
								}
							}
							else
							{
								// 2010.08.30 Mod K.Shinohara Start
								if (i > 0)
								{
									orderComment = orderComment + "\r\n" + commentID[i];
								}
								else
								{
									orderComment = commentID[i];
								}
								//orderComment = commentID[i];
								// 2010.08.30 Mod K.Shinohara End
							}
						}
						dt.Rows[0][RisOrderIndicateTbl.ORDERCOMMENT_ID_COLUMN] = orderComment;
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

		return dt;
	}
	*/

	/*
	// 2010.07.30 Add T.Ootsuka Start
	/// <summary>
	/// オーダ指示情報を取得する
	/// コメントIDの文字列変換は行わない
	/// </summary>
	/// <param name="risID">ユニークID</param>
	/// <returns>オーダ補足情報</returns>
	public OrderIndicateBean GetOrderIndicateData(String risID)
	{
		// parameters
		OrderIndicateBean orderIndicateBean = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisOrderIndicateTbl risOrderIndicateTbl = new RisOrderIndicateTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && risID != null)
			{
				orderIndicateBean = risOrderIndicateTbl.GetOrderIndicateBean(rCon, rTransaction, risID);
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

		return orderIndicateBean;
	}
	// 2010.07.30 Add T.Ootsuka End
	*/

	/*
	/// <summary>
	/// HIS受信履歴情報の取得
	/// </summary>
	/// <returns>HIS受信履歴情報</returns>
	public DataTable GetFromHisInfo(String risID, String recieveID)
	{
		// parameters
		DataTable dt = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisFromHisInfoTbl fromHisInfoTbl = new RisFromHisInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && risID != null)
			{
				dt = fromHisInfoTbl.GetRisFromHisInfo(rCon, rTransaction, risID, recieveID);

				if (dt != null)
				{
					//種別の変換
					for (int i = 0; dt.Rows.Count > i; i++)
					{
						dt.Rows[i][RisFromHisInfoTbl.MESSAGETYPE_COLUMN] = SqlDataChangeUnit.ChangeFromHisInfo(dt.Rows[i][RisFromHisInfoTbl.MESSAGETYPE_COLUMN].toString());
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

		return dt;
	}
	*/

	/*
	/// <summary>
	/// HIS送信履歴情報の取得
	/// </summary>
	/// <returns>HIS送信履歴情報</returns>
	public DataTable GetToHisInfo(String risID, String requestID)
	{
		// parameters
		DataTable dt = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisToHisInfoTbl toHisInfoTbl = new RisToHisInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && risID != null)
			{
				dt = toHisInfoTbl.GetRisToHisInfo(rCon, rTransaction, risID, requestID);

				if (dt != null)
				{
					//種別の変換
					for (int i = 0; dt.Rows.Count > i; i++)
					{
						dt.Rows[i][RisToHisInfoTbl.REQUEST_TYPE_COLUMN] = SqlDataChangeUnit.ChangeToHisInfo(dt.Rows[i][RisToHisInfoTbl.REQUEST_TYPE_COLUMN].toString());
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

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 所見送信ﾘｸｴｽﾄ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>所見送信ﾘｸｴｽﾄ情報</returns>
	public DataTable GetToReportInfo(String risID, String requestID)
	{
		// parameters
		DataTable dt = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisToReportInfoTbl toReportInfoTbl = new RisToReportInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && risID != null)
			{
				dt = toReportInfoTbl.GetRisToReportInfo(rCon, rTransaction, risID, requestID);

				if (dt != null)
				{
					//種別の変換
					for (int i = 0; dt.Rows.Count > i; i++)
					{
						dt.Rows[i][RisToHisInfoTbl.REQUEST_TYPE_COLUMN] = SqlDataChangeUnit.ChangeToReportInfo(dt.Rows[i][RisToHisInfoTbl.REQUEST_TYPE_COLUMN].toString());
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

		return dt;
	}
	*/

	/*
	/// <summary>
	/// 画像送信ﾘｸｴｽﾄ情報の取得
	/// </summary>
	/// <param name="risID">RIS_ID</param>
	/// <param name="requestID">送信ID</param>
	/// <returns>所見送信ﾘｸｴｽﾄ情報</returns>
	public DataTable GetToPacsInfo(String risID, String requestID)
	{
		// parameters
		DataTable dt = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisToPacsInfoTbl toPacsInfoTbl = new RisToPacsInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && risID != null)
			{
				dt = toPacsInfoTbl.GetRisToPacsInfo(rCon, rTransaction, risID, requestID);

				if (dt != null)
				{
					//種別の変換
					for (int i = 0; dt.Rows.Count > i; i++)
					{
						dt.Rows[i][RisToHisInfoTbl.REQUEST_TYPE_COLUMN] = SqlDataChangeUnit.ChangeToPacsInfo(dt.Rows[i][RisToHisInfoTbl.REQUEST_TYPE_COLUMN].toString());
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

		return dt;
	}
	*/

	/*
	/// <summary>
	/// オーダ情報の更新
	/// </summary>
	/// <param name="orderInfoBean">オーダ情報</param>
	/// <returns></returns>
	public boolean UpdateRisOrderInfo(OrderInfoBean orderInfoBean)
	{
		// parameters
		boolean retBool = false;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisOrderMainTbl risOrderMainTbl = new RisOrderMainTbl();
		RisExtendOrderInfoTbl risExtendOrderInfoTbl = new RisExtendOrderInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && orderInfoBean != null)
			{
				retBool = risOrderMainTbl.UpdateOrderInfo(rCon, rTransaction, orderInfoBean);
				ExtendOrderInfoBean extendOrderInfoBean = orderInfoBean.GetExtendOrderInfoBean();

				if (retBool && extendOrderInfoBean != null)
				{
					retBool = risExtendOrderInfoTbl.UpdateExtendOrderInfo(rCon, rTransaction, extendOrderInfoBean);
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

		return retBool;
	}
	*/

	/*
	/// <summary>
	/// HIS送信履歴情報の取得
	/// </summary>
	/// <returns>HIS送信履歴情報</returns>
	public ToHisInfoBean GetToHisMaxDate(String risID)
	{
		// parameters
		ToHisInfoBean toHisInfoBean = null;
		Connection rCon = null;
		Transaction rTransaction = null;
		RisToHisInfoTbl toHisInfoTbl = new RisToHisInfoTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			rCon = DBConnectionFactory.GetInstance().GetRisDBConnection();
			rTransaction = rCon.BeginTransaction();

			if (rCon != null && rTransaction != null && risID != null)
			{
				toHisInfoBean = toHisInfoTbl.GetToHisMaxDate(rCon, rTransaction, risID);
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

		return toHisInfoBean;
	}
	*/

	/*
	/// <summary>
	/// オーダ情報を登録する
	/// </summary>
	/// <param name="orderBean">オーダ情報</param>
	/// <param name="indicateBean">オーダ指示情報</param>
	/// <param name="exBean">実績情報</param>
	/// <returns>成功：true　失敗：false</returns>
	public boolean RegisterRisOrderInfo(OrderInfoBean orderBean, OrderIndicateBean indicateBean, ExecutionInfoBean exBean)
	{
		// parameters
		boolean retBool = false;
		Connection con = null;
		Transaction transaction = null;
		RisRisIDSequenceTbl risIDSequenceTbl = new RisRisIDSequenceTbl();
		RisRisOrderSequenceTbl orderSequenceTbl = new RisRisOrderSequenceTbl();
		RisOrderMainTbl orderMainTbl = new RisOrderMainTbl();
		RisExtendOrderInfoTbl extendOrderInfoTbl = new RisExtendOrderInfoTbl();
		RisOrderIndicateTbl orderIndicateTbl = new RisOrderIndicateTbl();
		RisOrderBuiInfoTbl orderBuiInfoTbl = new RisOrderBuiInfoTbl();
		RisExMainTbl exMainTbl = new RisExMainTbl();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && orderBean != null && indicateBean != null && exBean != null)
			{
				//RisIDの取得
				String newRisIDStr = risIDSequenceTbl.GetNewRisID(con, transaction);
				if (newRisIDStr == null || (newRisIDStr != null && newRisIDStr.length() == 0))
				{
					throw new Exception();
				}

				//OrderNoの取得
				String newOrderNoStr = orderSequenceTbl.GetNewOrderNo(con, transaction);
				if (newOrderNoStr == null || (newOrderNoStr != null && newOrderNoStr.length() == 0))
				{
					throw new Exception();
				}

				//StudyInstanceUIDの作成
				String newStudyInstanceUID = Configuration.GetInstance().CreateStudyInstanceUID(newRisIDStr);

				//①OrderMainTableの登録
				orderBean.SetRisID(newRisIDStr);
				orderBean.SetStudyInstanceUID(newStudyInstanceUID);
				orderBean.SetOrderNo(newOrderNoStr);
				orderBean.SetAccessionNo(newOrderNoStr);
				retBool = orderMainTbl.InsertNewOrderMainData(con, transaction, orderBean);
				if (!retBool)
				{
					throw new Exception();
				}

				//②ExtendOrderInfoの登録
				orderBean.GetExtendOrderInfoBean().SetRisID(newRisIDStr);
				retBool = extendOrderInfoTbl.InsertNewExtendOrderInfo(con, transaction, orderBean.GetExtendOrderInfoBean());
				if (!retBool)
				{
					throw new Exception();
				}

				//③OrderIndicateTableの登録
				indicateBean.SetRisID(newRisIDStr);
				retBool = orderIndicateTbl.InsertNewOrderIndicate(con, transaction, indicateBean);
				if (!retBool)
				{
					throw new Exception();
				}

				//④OrderBuiTableの登録
				for (int i = 0; i < orderBean.GetOrderBuiList().Count; i++)
				{
					OrderBuiBean buiBean = (OrderBuiBean)orderBean.GetOrderBuiList()[i];
					buiBean.SetRisID(newRisIDStr);
					retBool = orderBuiInfoTbl.InsertNewOrderBuiData(con, transaction, buiBean);
					if (!retBool)
					{
						throw new Exception();
					}
				}

				//⑤ExMainTableの登録
				exBean.SetRisID(newRisIDStr);
				retBool = exMainTbl.InsertNewExMainData(con, transaction, exBean);
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
	*/

	/*
	/// <summary>
	/// 他検査情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable GetRisOtherKensaList(OrderSearchParameter param)
	{
		// parameters
		DataTable dt = null;
		Connection con = null;
		Transaction transaction = null;
		RisSummaryView risSummaryView = new RisSummaryView();
		//OrderFormat orderFormat = new OrderFormat();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				param.SetTakensaSearch(false);

				//他検査情報を取得する
				//2010.10.29 Mod K.Shinohara Start
				dt = risSummaryView.SearchTakensaOrderDataList(con, transaction, param);
				//dt = risSummaryView.SearchTakensaOrderData(con, transaction, param);
				//2010.10.29 Mod K.Shinohara End

				////オーダ情報を形成する
				//dt = orderFormat.OrderFormatData(con, transaction, dt, null, param, null);

				//当該オーダを除外する
				for (int i = dt.Rows.Count - 1; i >= 0; i--)
				{
					if (dt.Rows[i][MasterUtil.RIS_RIS_ID].toString() == param.GetRisID())
					{
						dt.Rows.RemoveAt(i);
					}
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

		return dt;
	}
	*/

	/*
	/// <summary>
	/// (治療DB)他検査情報を検索する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable GetRtrisOtherKensaList(OrderSearchParameter param)
	{
		// parameters
		DataTable getDt = null;
		Connection con = null;
		Transaction transaction = null;
		OtherOrderView otherOrderView = new OtherOrderView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRtrisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				getDt = otherOrderView.GetRtrisOtherKensaList(con, transaction, param);
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

		return getDt;
	}
	*/

	/*
	/// <summary>
	/// 業務詳細過去情報の件数を取得する
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public int GetRisGyomuOldOrderDataCount(OrderSearchParameter param)
	{
		// parameters
		int retInt = 0;
		Connection con = null;
		Transaction transaction = null;
		RisSummaryView risSummaryView = new RisSummaryView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				//業務詳細過去情報の件数を取得する
				retInt = risSummaryView.SearchGyomuOldOrderDataCount(con, transaction, param);
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

		return retInt;
	}
	*/

	/*
	/// <summary>
	/// オーダ情報を検索する(履歴)
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public DataTable SearchRisOrderDataHistory(OrderSearchParameter param, Connection con)
	{
		// parameters
		DataTable dt = null;
		DataTable getDt = null;
		RisHistoryTabSummaryView view = new RisHistoryTabSummaryView();
		OrderFormat orderFormat = new OrderFormat();

		// begin log
		logger.debug("begin");

		try
		{
			//端末情報の取得
			// 2011.08.22 Mod T.Ootsuka@MERX Start NML-2-X04
			TerminalInfoBean termBean = Configuration.GetInstance().GetTerminalInfoBean();
			//TerminalInfoBean termBean = Configuration.GetInstance().GetCoreController().GetTerminalInfoDataByIPAdrress(Configuration.GetInstance().GetIPAddress());
			// 2011.08.22 Mod T.Ootsuka@MERX End

			if (con != null && param != null)
			{
				getDt = view.SearchOrderDataHistory(con, param);
				if (getDt != null)
				{
					for (int i = getDt.getRowCount() - 1; i >= 0; i--)
					{
						//当該オーダを除外する
						if (getDt.getRows().get(i).get(RisHistoryTabSummaryView.RIS_ID_COLUMN).toString().equals(param.GetRisID()))
						{
							getDt.getRows().remove(getDt.getRows().get(i));
						}
					}
				}
			}

			//オーダ情報を形成する
			dt = orderFormat.OrderFormatDataHistory(con, getDt, param);

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

		return dt;
	}
	*/

	/*
	// 2010.11.08 Add K.Shinohara Start
	/// <summary>
	/// オーダ件数を検索する(履歴)
	/// </summary>
	/// <param name="param">検索条件</param>
	/// <returns>検索結果のDataTable</returns>
	public int CountRisOrderDataHistory(OrderSearchParameter param)
	{
		// parameters
		int orderCount = 0;
		Connection con = null;
		Transaction transaction = null;
		RisHistoryTabSummaryView view = new RisHistoryTabSummaryView();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			if (con != null && transaction != null && param != null)
			{
				orderCount = view.CountOrderDataHistory(con, transaction, param);
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

		return orderCount;
	}
	// 2010.11.08 Add K.Shinohara End
	*/

	/*
	// 2011.09.09 Add T.Ootsuka@MERX Start NML-CAT9-031
	/// <summary>
	/// オーダ情報に部位略称を付加する
	/// </summary>
	/// <param name="orderDt">オーダ情報</param>
	/// <param name="oParam">オーダパラメータ</param>
	/// <returns></returns>
	public void ModifyOrder_MultiLocus(DataTable orderDt, OrderSearchParameter oParam)
	{
		// parameters
		Connection con = null;
		Transaction transaction = null;
		OrderFormat orderFormat = new OrderFormat();

		// begin log
		logger.debug("begin");

		try
		{
			// get connection and begin transaction
			con = DBConnectionFactory.GetInstance().GetRisDBConnection();
			transaction = con.BeginTransaction();

			//部位情報設定
			orderFormat.SetBuiInfoForReception(con, transaction, orderDt, oParam);

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
	// 2011.09.09 Add T.Ootsuka@MERX End
	*/
}
