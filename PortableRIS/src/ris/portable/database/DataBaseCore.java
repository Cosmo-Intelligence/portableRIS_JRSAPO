package ris.portable.database;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.pool.OracleDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ris.portable.util.DataRow;
import ris.portable.util.DataTable;

public class DataBaseCore {

	public static Log logger = LogFactory.getLog(DataBaseCore.class);

	/**
	 * DB接続
	 * @param dbname
	 * @param defUser
	 * @return
	 */
	public static String getDBUser(String dbname, String defUser) {

		String retUser = "";

		try {
			Context ctx = new InitialContext();
			OracleDataSource ds = (OracleDataSource) ctx.lookup(dbname);

			retUser = ds.getUser();

		} catch (Exception e) {
			logger.error(e.toString(), e);
			retUser = defUser;
		}

		return retUser;
	}

	/**
	 * DB接続
	 * @param dbname
	 * @return
	 */
	public static Connection getConnection(String dbname) {

		logger.debug("Connect To [" + dbname + "]");

		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			//OracleDataSource ds = (OracleDataSource) ctx.lookup(dbname);
			DataSource ds = (DataSource) ctx.lookup(dbname);

			conn = ds.getConnection();
			conn.setAutoCommit(false);

		} catch (Exception e) {
			logger.error(e.toString(), e);
			conn = null;
		}

		return conn;
	}

	/**
	 * DB接続
	 * @param dbname
	 * @param tns
	 * @param user
	 * @param pass
	 * @return
	 */
	public static Connection getConnection(String dbname, String tns, String user, String pass) {

		logger.debug("Connect To [" + dbname + "]");
		logger.debug("service:" + tns + ", user:" + user + ", pass:" + pass);

		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			//OracleDataSource ds = (OracleDataSource) ctx.lookup(dbname);
			DataSource ds = (DataSource) ctx.lookup(dbname);
			//ds.setURL(String.format(ds.getURL(), tns));
			//ds.setUser(user);
			//ds.setPassword(pass);
			conn = ds.getConnection(user, pass);
			conn.setAutoCommit(false);

		} catch (Exception e) {
			logger.error(e.toString(), e);
			conn = null;
		}

		return conn;
	}

	public static void closeConnection(Connection conn) {

		try {
			conn.close();
		} catch (Exception e) {
			// NULL;
		}

	}

	public static DataTable executeQuery(String sql, Object[] args,
			Connection conn) throws Exception {

		OraclePreparedStatement stmt = null;
		ResultSet rset = null;
		DataTable tbl = null;

		logger.debug(sql);

		try {

			stmt = (OraclePreparedStatement) conn.prepareStatement(sql);

			setParameters(stmt, args);

			rset = stmt.executeQuery();

			tbl = new DataTable(rset);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			tbl = null;
			throw e;
		} finally {
			try {
				rset.close();
				stmt.close();
			} catch (Exception ee) {
				// NULL;
			}
		}

		return tbl;
	}

	public static boolean executeSQL(String sql, Object[] args, Connection conn)
			throws Exception {

		OraclePreparedStatement stmt = null;

		logger.debug(sql);

		try {

			stmt = (OraclePreparedStatement) conn.prepareStatement(sql);

			setParameters(stmt, args);

			stmt.execute();

		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw e;
			// return false;
		} finally {
			try {
				stmt.close();
			} catch (Exception ee) {
				// null;
			}
		}

		return true;
	}

	private static void setParameters(OraclePreparedStatement stmt,
			Object[] args) throws Exception {

		for (int i = 0; i < args.length; i++) {
			Object obj = args[i];

			if (obj != null) {
				logger.debug(obj.toString());
				stmt.setObject(i + 1, obj);
			} else {
				logger.debug(obj);
				ParameterMetaData pmd = stmt.getParameterMetaData();
				// int type = pmd.getParameterType(i + 1);
				int type = java.sql.Types.TIMESTAMP; // getParameterTypeできないのでTimestamp固定にする。
				stmt.setNull(i + 1, type);
			}
		}

		return;
	}

	public static boolean updateTable(String tableName, String keyColumn,
			DataRow row, Connection conn) throws Exception {
		return updateTable(tableName, new String[] { keyColumn }, row, conn);
	}

	public static boolean updateTable(String tableName, String[] keyColumns,
			DataRow row, Connection conn) throws Exception {

		ArrayList<Object> arglist = new ArrayList<Object>();

		String sql = "";

		sql += "SELECT * ";
		sql += "  FROM " + tableName + " ";

		for (int i = 0; i < keyColumns.length; i++) {
			if (i == 0) {
				sql += " WHERE ";
			} else {
				sql += "  AND ";
			}
			sql += keyColumns[i] + " = ? ";
			arglist.add(row.get(keyColumns[i]));
		}

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		DataTable tbl = executeQuery(sql, args, conn);

		if (tbl == null) {
			return false;
		}

		sql = "";
		String[] columnNames = row.getColumnNames();
		arglist = new ArrayList<Object>();

		int colCount = 0;

		if (tbl.getRowCount() == 0) {
			sql += "INSERT INTO " + tableName + " ( ";
			colCount = 0;
			for (int i = 0; i < columnNames.length; i++) {
				if (existColumn(tbl.getColmunNames(), columnNames[i])) {
					if (colCount > 0) {
						sql += " , ";
					}
					sql += columnNames[i];
					colCount++;
				}
			}
			sql += " ) VALUES (";
			colCount = 0;
			for (int i = 0; i < columnNames.length; i++) {
				if (existColumn(tbl.getColmunNames(), columnNames[i])) {
					if (colCount > 0) {
						sql += " , ";
					}
					sql += " ? ";
					arglist.add(row.get(columnNames[i]));
					colCount++;
				}
			}
			sql += " ) ";
		} else {
			sql += "UPDATE " + tableName + " SET ";
			for (int i = 0; i < columnNames.length; i++) {
				boolean isKey = existColumn(keyColumns, columnNames[i]);
				if (!isKey) {
					if (existColumn(tbl.getColmunNames(), columnNames[i])) {
						if (arglist.size() > 0) {
							sql += " , ";
						}
						sql += columnNames[i] + " = ? ";
						arglist.add(row.get(columnNames[i]));
					}
				}
			}
			for (int i = 0; i < keyColumns.length; i++) {
				if (i == 0) {
					sql += " WHERE ";
				} else {
					sql += "  AND ";
				}
				sql += keyColumns[i] + " = ? ";
				arglist.add(row.get(keyColumns[i]));
			}
		}

		args = new Object[arglist.size()];
		arglist.toArray(args);

		return executeSQL(sql, args, conn);
	}

	public static boolean deleteTable(String tableName, String keyColumn,
			DataRow row, Connection conn) throws Exception {
		return deleteTable(tableName, new String[] { keyColumn }, row, conn);
	}

	public static boolean deleteTable(String tableName, String[] keyColumns,
			DataRow row, Connection conn) throws Exception {

		ArrayList<Object> arglist = new ArrayList<Object>();

		String sql = "";

		sql += "DELETE ";
		sql += "  FROM " + tableName + " ";

		for (int i = 0; i < keyColumns.length; i++) {
			if (i == 0) {
				sql += " WHERE ";
			} else {
				sql += "  AND ";
			}
			sql += keyColumns[i] + " = ? ";
			arglist.add(row.get(keyColumns[i]));
		}

		String[] args = new String[arglist.size()];
		arglist.toArray(args);

		return executeSQL(sql, args, conn);
	}

	private static boolean existColumn(String[] columnList, String columnName) {

		for (int i = 0; i < columnList.length; i++) {
			if (columnList[i].toUpperCase().equals(columnName.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

}
