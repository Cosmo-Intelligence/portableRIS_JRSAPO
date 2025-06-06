package ris.portable.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.cxf.common.util.StringUtils;

import ris.lib.core.util.CommonString;
import ris.portable.common.Const;
import ris.portable.util.DataTable;

public class DataBase extends DataBaseCore {

	/**
	 * RISユーザ取得
	 * @return
	 */
	public static String getRisDBUser() {
		return getDBUser(Const.DBNAME_RIS, Const.RISUSER);
	}

	/**
	 * RIS接続
	 * @return
	 */
	public static Connection getRisConnection() {
		return getConnection(Const.DBNAME_RIS);
	}

	/**
	 * MWM接続
	 * @return
	 */
	public static Connection getMwmConnection() {
		return getConnection(Const.DBNAME_MWM);
	}

	/**
	 * MPPS接続
	 * @return
	 */
	public static Connection getMppsConnection() {
		return getConnection(Const.DBNAME_MPPS);
	}

	/**
	 * ARWORK接続接続
	 * @return
	 */
	public static Connection getArworkConnection() throws Exception {

		String dbname = "";

		Connection conn = null;

		//No27 接続Close漏れ
		try {
			conn = getRisConnection();

			DataTable Dat = new DataTable();// getCurrentServerType(conn);

			if (Dat.getRowCount() > 0) {
				String servertype = Dat.getRows().get(0).get("CURRENTTYPE").toString();

				logger.debug("CURRENTTYPE = " + servertype );
			}
		} finally {
			closeConnection(conn);
		}

		return getConnection(dbname);
	}

	/**
	 * 切断
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {

		try{
			conn.close();
		} catch (Exception e) {
			//NULL;
		}
	}

	/**
	 * システム日付取得
	 * @param conn :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getSysdate(Connection conn) throws Exception {

		DataTable Dat = new DataTable();

		String sql = "select SYSDATE from dual";

		Object[] args = new Object[0];
		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * SYSTEMPARAM取得
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static DataTable getSystemParam(Connection conn, String subid) throws Exception {

		DataTable Dat = new DataTable();

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " select";
		sql += "     VALUE1";
		sql += " from";
		sql += "     SYSTEMPARAM";

		where += " where";
		where += "     MAINID = 'DATASIZE'";
		where += " and";
		where += "     SUBID = ?";

		// バインド
		arglist.add(subid);

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/*
    public static DataTable getCurrentServerType(Connection conn) throws Exception{

    	DataTable Dat = new DataTable();
		ArrayList<Object> arglist = new ArrayList<Object>();

        String sql = "";
        String where = "";

        sql += "SELECT a.* ";
        sql += "  FROM currentservertype a ";

        sql += where;


		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

        return Dat;
    }
	*/

	/**
	 * ユーザ情報取得
	 * @param userid   :ユーザID
	 * @param password :パスワード
	 * @param conn     :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getUserManage(String userid, String password, Connection conn) throws Exception {

		DataTable Dat = null;

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql   += " select";
		sql   += "   USERID,";
		sql   += "   USERNAME";
		sql   += " from";
		sql   += "   USERMANAGE";

		where += " where";
		where += "   USERID = ?";
		// バインド
		arglist.add(userid);

		where += " and ";
		where += "   PASSWORD = md5_digest(?) ";
		// バインド
		arglist.add(password);

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	// 2020.08.27 Add Nishihara@COSMO start MWM対象装置の制御対応(取得処理)
	/**
	 * 前回選択した検査室/検査機器取得処理
	 * @param host   :ホスト名
	 * @param ipaddress :IPアドレス
	 * @param conn     :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getPortableRIS_TerminalInfo(String host, String ipaddress, Connection conn) throws Exception {

		DataTable Dat = null;

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql   += " select";
		sql   += "   KENSASITU_ID,";
		sql   += "   KENSAKIKI_ID,";
		sql   += "   KANJA_NYUGAI,";
		sql   += "   KENSATYPE_ID";
		sql   += " from";
		sql   += "   PORTABLERIS_TERMINALINFO";

		where += " where";
		where += "   HOST = ?";
		// バインド
		arglist.add(host);

		where += " and ";
		where += "   IPADDRESS = ? ";
		// バインド
		arglist.add(ipaddress);

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}
	// 2020.08.27 Add Nishihara@COSMO end MWM対象装置の制御対応(取得処理)

	// 2020.08.31 Add Nishihara@COSMO start MWM対象装置の制御対応(更新処理)
	/**
	 * プルダウンで選択した検査室/検査機器の更新処理
	 * @param host   :ホスト名
	 * @param ipaddress :IPアドレス
	 * @param kensasituid :プルダウンで選択した検査室ID
	 * @param kensakikiid :プルダウンで選択した検査機器ID
	 * @param kanja_nyugai :プルダウンで選択した入外区分
	 * @param kensatypeid :プルダウンで選択した検査種別ID
	 * @param conn     :接続情報
	 * @return
	 * @throws Exception
	 */
	public static boolean PortableRIS_TerminalInfoUpdate(String host, String ipaddress, String kensasitu_id, String kensakiki_id, String kanja_nyugai, String kensatype_id, Connection conn) throws Exception {

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql   += " merge into";
		sql   += "   PORTABLERIS_TERMINALINFO pt";
		sql   += " using";
		sql   += " (select ";
		sql   += " ? as HOST,";
		// バインド
		arglist.add(host);
		sql   += " ? as IPADDRESS,";
		// バインド
		arglist.add(ipaddress);
		sql   += " ? as KENSASITU_ID,";
		// バインド
		arglist.add(kensasitu_id);
		sql   += " ? as KENSAKIKI_ID,";
		// バインド
		arglist.add(kensakiki_id);
		sql   += " ? as KANJA_NYUGAI,";
		// バインド
		arglist.add(kanja_nyugai);
		sql   += " ? as KENSATYPE_ID";
		// バインド
		arglist.add(kensatype_id);
		sql   += " from";
		sql   += "  dual) pd";
		sql   += " on";
		sql   += " ( pt.HOST = pd.HOST and pt.IPADDRESS = pd.IPADDRESS)";

		where += " when matched then";
		where += "  update set";
		where += "   pt.KENSASITU_ID = pd.KENSASITU_ID,";
		where += "   pt.KENSAKIKI_ID = pd.KENSAKIKI_ID,";
		where += "   pt.KANJA_NYUGAI = pd.KANJA_NYUGAI,";
		where += "   pt.KENSATYPE_ID = pd.KENSATYPE_ID";

		where += " when not matched then";
		where += " insert (";
		where += " HOST, ";
		where += " IPADDRESS,";
		where += " KENSASITU_ID,";
		where += " KENSAKIKI_ID,";
		where += " KANJA_NYUGAI,";
		where += " KENSATYPE_ID";
		where += " )";

		where += " values (";
		where += " pd.HOST,";
		where += " pd.IPADDRESS,";
		where += " pd.KENSASITU_ID,";
		where += " pd.KENSAKIKI_ID,";
		where += " pd.KANJA_NYUGAI,";
		where += " pd.KENSATYPE_ID";
		where += " ) ";

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		try {
			executeSQL(sql, args, conn);
		} catch (Exception e) {
			return false;
		}
		return true;

	}
	// 2020.08.31 Add Nishihara@COSMO end MWM対象装置の制御対応(更新処理)


	/**
	 * 受付者マスタ情報取得
	 * @param syokuinkbn :職員区分
	 * @param conn       :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getUserManage(String syokuinkbn[], Connection conn) throws Exception {

		DataTable Dat = null;

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql   += " select";
		sql   += "   USERMANAGE.USERID as ID,";
		sql   += "   USERMANAGE.USERNAME as NAME";
		sql   += " from";
		sql   += "   USERMANAGE,";
		sql   += "   USERINFO_CA";

		where += " where";
		where += "   USERMANAGE.USERID = USERINFO_CA.LOGINID";

		if (!StringUtils.isEmpty(Arrays.asList(syokuinkbn))) {
			if (!StringUtils.isEmpty(where)) {
				where += " and";
			}
			where += "   USERINFO_CA.SYOKUIN_KBN in (";

			String buff = "";
			for (int i = 0; i < syokuinkbn.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += " ?";
				// バインド
				arglist.add(syokuinkbn[i]);
			}
			where += buff + " )";
		}

		sql += where;
		sql += " order by";
		sql += "   USERINFO_CA.SHOWORDER asc";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * 病棟マスタ情報取得
	 * @param byoutouid :病棟ID
	 * @param userid    :ユーザID
	 * @param password  :パスワード
	 * @param conn      :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getByoutouMaster(String byoutouid[], Connection conn) throws Exception {

		DataTable Dat = null;

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql   += " select";
		sql   += "   BYOUTOU_ID as ID,";
		sql   += "   BYOUTOU_NAME as NAME";
		sql   += " from";
		sql   += "   BYOUTOUMASTER";

		where += " where";
		where += "   USEFLAG = '1'";

		if (!StringUtils.isEmpty(Arrays.asList(byoutouid))) {
			if (!StringUtils.isEmpty(where)) {
				where += " and";
			}
			where += "   BYOUTOU_ID in (";

			String buff = "";
			for (int i = 0; i < byoutouid.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += " ?";
				// バインド
				arglist.add(byoutouid[i]);
			}
			where += buff + " )";
		}

		sql += where;
		sql += " order by";

		// configの病棟ID順に並べる
		// case BYOUTOU_ID when ? then 表示順 when ?... else 表示順 end
		sql += " case BYOUTOU_ID";

		String buff = "";
		for (int i = 0; i < byoutouid.length; i++) {
			buff += " when";
			buff += " ? then " + i;

			// バインド
			arglist.add(byoutouid[i]);

			// 最後のループ
			if (byoutouid.length == (i + 1)) {
				buff += " else " + (i + 1) + " end";
			}
		}
		sql += buff;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * 検査機器マスタ情報取得
	 * @param kensakikiid :検査機器ID
	 * @param conn        :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getKensakikiMaster(String[] kensakikiid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " select ";
		sql += "   KENSAKIKI_ID as ID,";
		sql += "   KENSAKIKI_NAME as NAME";
		sql += " from";
		sql += "   KENSAKIKIMASTER";

		where += " where";
		where += "   USEFLAG = '1'";

		if (!StringUtils.isEmpty(Arrays.asList(kensakikiid))) {
			if ("".equals(where)) {
				where += " where";
			} else {
				where += " and";
			}
			where += "   KENSAKIKI_ID in (";

			String buff = "";
			for (int i = 0; i < kensakikiid.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += " ?";
				arglist.add(kensakikiid[i]);
			}

			where += buff + " )";
		}

		sql += where;

		sql += " order by";
		sql += "   SHOWORDER asc";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * 検査室マスタ情報取得
	 * @param kensatypeid :検査種別ID
	 * @param conn        :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getKensasituMaster(String[] kensatypeid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " select ";
		sql += "   EXAMROOM_ID as ID,";
		sql += "   EXAMROOM_NAME as NAME,";
		sql += "   KENSATYPE_ID,";
		sql += "   KENSAKIKI_ID";
		sql += " from";
		sql += "   EXAMROOMMASTER";

		where += " where";
		where += "   USEFLAG = '1'";

		if (!StringUtils.isEmpty(Arrays.asList(kensatypeid))) {
			where += "and (";

			String buff = "";
			for (int i = 0; i < kensatypeid.length; i++) {
				if (!buff.equals("")) {
					buff += "or";
				}
				buff += "   KENSATYPE_ID like '%' || ? || '%'";
				arglist.add(kensatypeid[i]);
			}

			where += buff + " )";
		}

		sql += where;

		sql += " order by";
		sql += "   SHOWORDER asc";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/*
	public static DataTable getJisisya(String jisisyaid,Connection conn) throws Exception {

		DataTable Dat = null;

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM usermanage a ";

		where += " WHERE a.hospitalid = 'HID' ";
		where += "   AND a.userid = ? ";

		arglist.add(jisisyaid);

		sql += where;


		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	public static DataTable getPatientInfo(String patientid, Connection conn)  throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " SELECT a.*, ";
		sql += "        TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'yyyymmdd')) - birthday)/10000,0) AS age,";
		sql += "        NULL AS nengo_char, ";
		sql += "        NULL AS nengo_year, ";
		sql += "        b.byoutou_name, ";
		sql += "        c.byousitu_name ";
		sql += "   FROM patientinfo a, ";
		sql += "        byoutoumaster b, ";
		sql += "        byousitumaster c ";

		where += " WHERE a.byoutou_id = b.byoutou_id(+) ";
		where += "   AND a.byousitu_id = c.byousitu_id(+) ";

		where += "   AND a.kanja_id = ? ";
		arglist.add(patientid);

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);


		if(Dat.getRowCount() > 0) {
			if(Dat.getRows().get(0).get("BIRTHDAY").toString() != "") {
				DataTable jeraDat = getJeraBirthday(Long.parseLong(Dat.getRows().get(0).get("BIRTHDAY").toString()), conn);

				if(jeraDat.getRowCount() > 0) {
					Dat.getRows().get(0).put("NENGO_CHAR",jeraDat.getRows().get(0).get("JERA"));
					Dat.getRows().get(0).put("NENGO_YEAR",jeraDat.getRows().get(0).get("YEAR"));
				}

			}
		}

		return Dat;
	}
	*/

	/*
	public static DataTable getJeraBirthday(Timestamp birthday, Connection conn) throws Exception {

		String date = Util.toFormatString(birthday, "yyyyMMdd");

		return getJeraBirthday(Long.parseLong(date),conn);

	}

	public static DataTable getJeraBirthday(long birthday, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();


		sql += "SELECT a.*, ";
		sql += "       TO_NUMBER(TO_CHAR(TO_DATE('" + String.valueOf(birthday) + "','yyyymmdd'),'yyyy')) - TO_NUMBER(TO_CHAR(firstdate,'yyyy')) + 1 AS year";
		sql += "  FROM (SELECT * ";
		sql += "          FROM jeramaster ";
		sql += "         WHERE firstdate = (SELECT MAX(firstdate) FROM jeramaster WHERE firstdate <= TO_DATE('" + String.valueOf(birthday) + "','yyyymmdd')) ";
		sql += "       ) a ";

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	public static DataTable getBuiMaster(String buiid, Connection conn) throws Exception {

		String[] builist = new String[0];

		if(buiid != null){
			builist = buiid.split(",");
		}

		return getBuiMaster(builist, conn);
	}

	public static DataTable getBuiMaster(String[] buiid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM buimaster a ";

		where += "   WHERE a.useflag = 1 ";


		if(buiid != null && buiid.length > 0) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.bui_id in (";

			String buff = "";
			for(int i = 0; i < buiid.length; i++){
				if(!buff.equals("")){
					buff += ",";
				}
				buff += " ?";
				arglist.add(buiid[i]);
			}

			where += buff + " )";
		}

		sql += where;

		sql += "ORDER BY a.showorder,a.bui_id ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	public static DataTable getSectionMaster(String doctorid, String sectionid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM sectionmaster a ";

		where += "   WHERE a.useflag = 1 ";


		if(doctorid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     EXISTS (SELECT * ";
			where += "               FROM sectiondoctormaster x ";
			where += "              WHERE x.doctor_id = ? ";
			where += "                AND useflag = '1' ";
			where += "                AND INSTR(',' || TRIM(x.tanto_section_id) || ',' , ',' || a.section_id || ',') > 0 ";
			where += "            ) ";
			arglist.add(doctorid);
		}


		if(sectionid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.section_id = ? ";
			arglist.add(sectionid);
		}

		sql += where;

		sql += "ORDER BY a.showorder,a.section_id ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/**
	 * 検査種別マスタ取得
	 * @param kensatypeid :検査種別ID
	 * @param conn        :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getKensaTypeMaster(String[] kensatypeid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " select";
		sql += "   KENSATYPE_ID as ID,";
		sql += "   KENSATYPE_NAME as NAME";
		sql += " from";
		sql += "   KENSATYPEMASTER";

		where += " where";
		where += "   USEFLAG = '1'";

		if (!StringUtils.isEmpty(Arrays.asList(kensatypeid))) {
			if ("".equals(where)) {
				where += " where";
			} else {
				where += " and";
			}
			where += "   KENSATYPE_ID in (";

			String buff = "";

			for (int i = 0; i < kensatypeid.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += "?";
				arglist.add(kensatypeid[i]);
			}
			where += buff + ")";
		}

		sql += where;

		sql += " order by";

		// 2019.01.04 Mod S.Ichinose@COSMO Start 呼出参照機能対応
		// configの検査種別ID順に並べる
		// case KENSATYPE_ID when ? then 表示順 when ?... else 表示順 end
		sql += " case KENSATYPE_ID";

		String buff = "";
		for (int i = 0; i < kensatypeid.length; i++) {
			buff += " when";
			buff += " ? then " + i;

			// バインド
			arglist.add(kensatypeid[i]);

			// 最後のループ
			if (kensatypeid.length == (i + 1)) {
				buff += " else " + (i + 1) + " end";
			}
		}
		sql += buff;
		// 2019.01.04 Mod S.Ichinose@COSMO End   呼出参照機能対応

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * テンプレートマスタ取得
	 * @param groupcode :グループコード
	 * @param conn      :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getTemplateContents(String[] groupcode, Connection conn) throws Exception{

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql +=  "select";
		sql += "    TEMPLATECONTENTS.TEMPLATEID as ID,";
		sql += "    TEMPLATECONTENTS.CONTENTS as NAME";
		sql += " from";
		sql += "    TEMPLATECONTENTS,";
		sql += "    TEMPLATEGROUPMASTER";

		where += " where";
		where += "   TEMPLATECONTENTS.GROUPCODE = TEMPLATEGROUPMASTER.GROUPCODE";
		where += " and";
		where += "   TEMPLATECONTENTS.USEFLAG = '1'";

		if (!StringUtils.isEmpty(Arrays.asList(groupcode))) {
			if (where.equals("")) {
				where += " where";
			} else {
				where += " and";
			}

			where += "   TEMPLATEGROUPMASTER.GROUPCODE in (";
			String buff = "";
			for (int i = 0; i < groupcode.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += "?";
				arglist.add(groupcode[i]);
			}
			where += buff + ")";
		}

		sql += where;

		sql += " order by";
		sql += "   TEMPLATEGROUPMASTER.GROUPCODE asc,";
		sql += "   TEMPLATECONTENTS.COUNT asc";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * 検査ステータスマスタ取得
	 * @param statusCode
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static DataTable getKensaStatus(String[] statusCode, Connection conn) throws Exception{

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " select";
		sql += "   STATUSCODE,";
		sql += "   SHORTLABEL,";
		sql += "   nvl(COLOR, 0) as COLOR,";
		sql += "   nvl(COLORBK, 0) as COLORBK";
		sql += " from";
		sql += "   STATUSDEFINE";

		where += " where";
		where += "   1 = 1";

		if (!StringUtils.isEmpty(Arrays.asList(statusCode))) {
			if (where.equals("")) {
				where += " where";
			} else {
				where += " and";
			}

			where += "   STATUSCODE in (";
			String buff = "";
			for (int i = 0; i < statusCode.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += "?";
				arglist.add(statusCode[i]);
			}
			where += buff + ")";
		}

		sql += where;
		sql += " order by";
		sql += "   SHOWORDER asc";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	// 2020.09.15 Add Nishihara@COSMO start 実施技師登録機能対応(実施者取得)
	/**
	 * 実施者(担当技師)マスタ取得
	 * @param statusCode
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static DataTable getZisshishaMaster(Connection conn) throws Exception{

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " select";
		sql += "   a.USERID,";
		sql += "   a.USERNAME";
		sql += " from";
		sql += "   USERMANAGE a,";
		sql += "   USERINFO_CA b";

		where += " where";
		where += "   a.USERID = b.LOGINID";
		where += " and";
		where += "   b.SYOKUIN_KBN = '3'";

		sql += where;
		sql += " order by";
		sql += "   b.showorder";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		return Dat;
	}
	// 2020.09.15 Add Nishihara@COSMO end 実施技師登録機能対応(実施者取得)

	// 2020.09.15 Add Nishihara@COSMO start 実施技師登録機能対応(更新処理)
	/**
	 * 詳細画面の実施者(担当技師)の更新処理
	 * @param ris_id   :EXMAINTABLEのRIS_ID
	 * @param conn     :接続情報
	 * @return
	 * @throws Exception
	 */
	public static boolean zishisha_Update(String ris_id, String med_person_id01, String med_person_id02, String med_person_id03,
			String med_person_name01, String med_person_name02, String med_person_name03, Connection conn) throws Exception {

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " update";
		sql += "  EXMAINTABLE";
		sql += " set";
//		sql += "  KENSA_GISI_ID = ?";
//		// バインド
//		arglist.add(kensa_gisi_id1);
//		sql += " ,KENSA_GISI_ID2 = ?";
//		// バインド
//		arglist.add(kensa_gisi_id2);
//		sql += " ,KENSA_GISI_ID3 = ?";
//		// バインド
//		arglist.add(kensa_gisi_id3);
//		sql += " ,KENSA_GISI_NAME = ?";
//		// バインド
//		arglist.add(kensa_gisi_name1);
//		sql += " ,KENSA_GISI_NAME2 = ?";
//		// バインド
//		arglist.add(kensa_gisi_name2);
//		sql += " ,KENSA_GISI_NAME3 = ?";
//		// バインド
//		arglist.add(kensa_gisi_name3);

		sql += "  MED_PERSON_ID01 = ?";
		// バインド
		arglist.add(med_person_id01);
		sql += " ,MED_PERSON_ID02 = ?";
		// バインド
		arglist.add(med_person_id02);
		sql += " ,MED_PERSON_ID03 = ?";
		// バインド
		arglist.add(med_person_id03);
		sql += " ,MED_PERSON_NAME01 = ?";
		// バインド
		arglist.add(med_person_name01);
		sql += " ,MED_PERSON_NAME02 = ?";
		// バインド
		arglist.add(med_person_name02);
		sql += " ,MED_PERSON_NAME03 = ?";
		// バインド
		arglist.add(med_person_name03);
		sql += " ,MED_PERSON_SYOKUINKBN01 = '3'";
		sql += " ,MED_PERSON_SYOKUINKBN02 = '3'";
		sql += " ,MED_PERSON_SYOKUINKBN03 = '3'";

		where += " where";
		where += "  RIS_ID = ?";
		arglist.add(ris_id);

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		try {
			executeSQL(sql, args, conn);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	// 2020.09.15 Add Nishihara@COSMO end 実施技師登録機能対応(更新処理)

	/*
	public static DataTable getSectionDoctorMaster(String sectionid, String doctorid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM sectiondoctormaster a ";

		where += "   WHERE a.useflag = 1 ";


		if(sectionid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.section_id = ? ";
			arglist.add(sectionid);
		}

		if(doctorid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.doctor_id = ? ";
			arglist.add(doctorid);
		}

		sql += where;

		sql += "ORDER BY a.showorder,a.doctor_id ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	public static DataTable getModalityList( Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT * ";
		sql += "  FROM (SELECT modality, ";
		sql += "               MIN(showorder) AS showorder ";
		sql += "          FROM kensatypemaster ";
		sql += "         WHERE useflag = '1' ";
		sql += "           AND modality IS NOT NULL ";
		sql += "        GROUP BY modality ";
		sql += "       ) a ";

		sql += where;

		sql += "ORDER BY a.modality, a.showorder ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	public static DataTable getSystemDefine( Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT * ";
		sql += "  FROM systemdefine ";

		sql += where;


		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	public static String createRisId(Connection conn) throws Exception {

		String ret = null;

		long seq = getSequenceValue("RisIdSequence",conn);

		DataTable dat = getSysdate(conn);

		Timestamp sysdate = (Timestamp)dat.getRows().get(0).get("SYSDATE");

		ret = new SimpleDateFormat("yyyyMMdd").format(sysdate);
		ret += String.valueOf(seq);

		return ret;
	}
	*/

	/*
	public static String createOrderNo(Connection conn) throws Exception {

		String ret = null;

		long seq = getSequenceValue("RisOrderSequence",conn);

		ret = String.valueOf(seq);

		return ret;
	}
	*/

	/*
	public static String createSutudyInstanceUid(String risid, String studyInstanceUid, Connection conn) throws Exception {

		String ret = null;

		DataTable Dat = getSystemDefine(conn);

		//"1.2.392.200045.6960.4.7.nnnnnn.ris_id"
		//ret = "1.2.392.200045.6960.4.7.";
		ret = studyInstanceUid;

		ret += ".";
		ret += Dat.getRows().get(0).get("LICENSENO").toString();

		ret += ".";
		ret += risid;

		return ret;
	}
	*/

	/*
	public static String createToCodonicsRequestId(Connection conn) throws Exception {

		String ret = null;

		long seq = getSequenceValue("ToCodonicsSequence",conn);

		ret = String.valueOf(seq);

		return ret;
	}
	*/

	/*
	private static long getSequenceValue(String sequensename, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT " + sequensename + ".nextval AS nextval ";
		sql += "  FROM dual ";

		sql += where;


		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);


		return Long.parseLong(Dat.getRows().get(0).get("NEXTVAL").toString());

	}
	*/

	/*
	public static DataTable getToCodonicsInfo(String requestid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM tocodonicsinfo a ";


		where += " WHERE requestid = ? ";
		arglist.add(requestid);

		sql += where;

		sql += " ORDER BY requestid ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;
	}
	*/

	/*
	public static DataTable getCodonicsMediaOrderTable(String risid, String patientid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.*, ";
		sql += "       b.doctor_name, ";
		sql += "       c.section_name, ";
		sql += "       e.kensakiki_name, ";
		sql += "       f.bui_name, ";

		//社内テストNo6 依頼ファイル作成失敗時のTransferStatus
		//失敗時はTransferStatuが"02"になる。
		//sql += "       CASE WHEN g.orderno IS NULL THEN '作成中' ELSE '完了' END AS sintyoku, ";
		sql += "       CASE WHEN g.orderno IS NULL THEN ";
		sql += "                 CASE WHEN h.transferstatus = '02' THEN '失敗' ";	//jspで文字を見てるので変更する時は注意
		sql += "                      ELSE '作成中' ";
		sql += "                 END ";
		sql += "            ELSE '完了' ";
		sql += "       END AS sintyoku, ";

		sql += "       d.kanjisimei ";
		sql += "  FROM codonicsmediaordertable a, ";
		sql += "       sectiondoctormaster b, ";
		sql += "       sectionmaster c, ";
		sql += "       patientresultsinfo d, ";
		sql += "       kensakikimaster e, ";
		sql += "       buimaster f, ";
		sql += "       (SELECT DISTINCT orderno FROM codonicsmediacompletetable ) g, ";
		sql += "       tocodonicsinfo h, ";
		sql += "       exmaintable i ";

		where += " WHERE a.irai_doctor_id = b.doctor_id(+) ";
		where += "   AND a.irai_section_id = c.section_id(+) ";
		where += "   AND a.ris_id = d.ris_id(+) ";
		//where += "   AND a.kensakiki = e.kensakiki_id(+) ";
		where += "   AND a.order_bui_id = f.bui_id(+) ";
		where += "   AND a.orderno = g.orderno(+) ";
		where += "   AND a.ris_id = h.ris_id(+) ";		//同じRIS_IDで複数の作成依頼は出来ないはず。
		where += "   AND a.ris_id = i.ris_id(+) ";				//社内テストNo7 CodonicsMediaOrderTableのKensaKikiの値
		where += "   AND i.kensakiki_id = e.kensakiki_id(+) ";	//社内テストNo7 CodonicsMediaOrderTableのKensaKikiの値


		if(patientid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.kanja_id = ? ";
			arglist.add(patientid);
		}

		if(risid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.ris_id = ? ";
			arglist.add(risid);
		}

		sql += where;

		sql += " ORDER BY a.ris_id ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;
	}
	*/

	/*
	public static DataTable getCodonicsMediaOrderStudyTable(String risid, String no, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM codonicsmediaorderstudytable a ";


		if(risid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.ris_id = ? ";
			arglist.add(risid);
		}

		if(no != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.no = ? ";
			arglist.add(no);
		}

		sql += where;

		sql += " ORDER BY ris_id, no ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;
	}
	*/

	/*
	public static DataTable getCodonicsMediaCompleteTable(String orderno, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM codonicsmediacompletetable a ";



		if(orderno != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.orderno = ? ";
			arglist.add(orderno);
		}


		sql += where;

		sql += " ORDER BY a.orderno, a.diskid ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;
	}
	*/

	/*
	public static DataTable getZoneCode(Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		//業務区分を取得する。
		sql += "SELECT b.today, ";
		sql += "       CASE WHEN c.hizuke IS NOT NULL THEN '4' ";		//休日の場合は'4':緊急
		sql += "            WHEN a.zone1_time  <= b.timenum AND b.timenum < NVL(a.zone2_time ,2400) THEN zone1_code ";
		sql += "            WHEN a.zone2_time  <= b.timenum AND b.timenum < NVL(a.zone3_time ,2400) THEN zone2_code ";
		sql += "            WHEN a.zone3_time  <= b.timenum AND b.timenum < NVL(a.zone4_time ,2400) THEN zone3_code ";
		sql += "            WHEN a.zone4_time  <= b.timenum AND b.timenum < NVL(a.zone5_time ,2400) THEN zone4_code ";
		sql += "            WHEN a.zone5_time  <= b.timenum AND b.timenum < NVL(a.zone6_time ,2400) THEN zone5_code ";
		sql += "            WHEN a.zone6_time  <= b.timenum AND b.timenum < NVL(a.zone7_time ,2400) THEN zone6_code ";
		sql += "            WHEN a.zone7_time  <= b.timenum AND b.timenum < NVL(a.zone8_time ,2400) THEN zone7_code ";
		sql += "            WHEN a.zone8_time  <= b.timenum AND b.timenum < NVL(a.zone9_time ,2400) THEN zone8_code ";
		sql += "            WHEN a.zone9_time  <= b.timenum AND b.timenum < NVL(a.zone10_time,2400) THEN zone9_code ";
		sql += "            WHEN a.zone10_time <= b.timenum AND b.timenum <                   2400  THEN zone10_code ";
		sql += "       END AS zone_code ";
		sql += "  FROM timezonetable a, ";
		sql += "       (SELECT b.today, ";				//週と曜日でその日の診療日区分を取得する
		sql += "               TO_NUMBER(TO_CHAR(SYSDATE,'HH24MI')) AS timenum, ";
		sql += "               CASE WHEN b.weeknum = 1 THEN a.week01 ";
		sql += "                    WHEN b.weeknum = 2 THEN a.week02 ";
		sql += "                    WHEN b.weeknum = 3 THEN a.week03 ";
		sql += "                    WHEN b.weeknum = 4 THEN a.week04 ";
		sql += "                    WHEN b.weeknum = 5 THEN a.week05 ";
		sql += "                    WHEN b.weeknum = 6 THEN a.week06 ";
		sql += "                    ELSE NULL ";
		sql += "               END AS dateclassification ";
		sql += "          FROM dayclassificationtable a, ";
		sql += "               (SELECT today, ";		//第何週の何曜日か取得する
		sql += "                       ((today - firstday) / 7) + 1 AS weeknum, ";
		sql += "                       TO_CHAR(today,'D') - 1 AS dayofweek, ";
		sql += "                       TO_CHAR(today,'DY') AS dayname ";
		sql += "                  FROM (SELECT TRUNC(SYSDATE) AS today, ";
		//sql += "                               NEXT_DAY(TRUNC(SYSDATE,'MM'),TO_NUMBER(TO_CHAR(SYSDATE ,'D'))) AS firstday ";
		sql += "                               NEXT_DAY(TRUNC(SYSDATE,'MM') - 1,TO_NUMBER(TO_CHAR(SYSDATE ,'D'))) AS firstday ";	//No28 業務時間のチェックが効かない
		sql += "                          FROM dual ";
		sql += "                       ) ";
		sql += "               ) b ";
		sql += "         WHERE a.dayofweek = b.dayofweek ";
		sql += "       ) b, ";
		sql += "       calendarmaster c ";
		sql += " WHERE a.dateclassification = b.dateclassification ";
		sql += "   AND TO_CHAR(b.today,'YYYY/MM/DD') = c.hizuke(+) ";


		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;
	}
	*/

	/*
	//ARQS
	public static DataTable getMasterStudy(	String acno,
											String patientid,
											String modality,
											String studyInstanceUid,
											Timestamp dateFrom,
											Timestamp dateTo,
											Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.*, ";
		sql += "       CASE WHEN institutionaddress = 'OTHERINSTITUTE' THEN '他院画像'  ELSE NULL END AS media_bikou ";
		sql += "  FROM masterstudy a ";



		if(acno != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.accessionnumber = ? ";
			arglist.add(acno);
		}

		if(patientid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.patientid = ? ";
			arglist.add(patientid);
		}

		if(modality != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.modality = ? ";
			arglist.add(modality);
		}

		if(studyInstanceUid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.studyinstanceuid = ? ";
			arglist.add(studyInstanceUid);
		}

		if(dateFrom != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     TRUNC(a.studydate) >= ? ";
			arglist.add(dateFrom);
		}

		if(dateTo != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     TRUNC(a.studydate) <= ? ";
			arglist.add(dateTo);
		}

		sql += where;

		//No25 検査一覧のソート順
		//sql += "ORDER BY a.studyid,a.studydate,a.studytime ";
		sql += "ORDER BY a.studydate desc ,a.studytime desc ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	//ARQS
	public static DataTable getUserManageVins(	String userid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT a.* ";
		sql += "  FROM usermanageforvins a ";


		if(userid != null) {
			if("".equals(where)) {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.userid = ? ";
			arglist.add(userid);
		}

		sql += where;

		sql += "ORDER BY a.userid ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}

	/*
	//ARQS
	public static DataTable getModalityListFromArqs(String patientid, Connection conn) throws Exception {

		DataTable Dat = new DataTable();
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += "SELECT DISTINCT modality ";
		sql += "  FROM masterstudy a ";

		if(patientid != null) {
			if(where == "") {
				where += " WHERE ";
			}
			else {
				where += " AND ";
			}
			where += "     a.patientid = ? ";
			arglist.add(patientid);
		}

		sql += where;

		sql += "ORDER BY a.modality ";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql,args,conn);

		return Dat;

	}
	*/

	/*
	//監査証跡登録
	public static void insertAuditTrail(String unitutype,
										Timestamp eventdate,
										String eventname,
										DataRow patientRow,
										DataRow loginRow,
										String iparrd,
										Connection conn
										) throws Exception  {

		String sql = "";
		//String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		String tablename = "audittrail";
		if(unitutype.equals(UNITYTYPE_SERV_NML)) {
			tablename = "arqs.audittrail";
		}

		sql += "INSERT INTO " + tablename + " ( ";
		sql += "            eventdatetime, ";
		sql += "            eventname, ";
		sql += "            hospitalid, ";
		sql += "            hostuserid, ";
		sql += "            hostusername, ";
		sql += "            hostipaddress, ";
		sql += "            patientid, ";
		sql += "            patientname, ";
		sql += "            studydate, ";
		sql += "            modality, ";
		sql += "            result, ";
		sql += "            additionalinfo1, ";
		sql += "            appcode ";
		sql += "            ) ";
		sql += "     VALUES ( ";
		sql += "            ?, ";
		sql += "            ?, ";
		sql += "            ?, ";
		sql += "            ?, ";
		sql += "            ?, ";
		sql += "            ?, ";
		sql += "            ?, ";
		sql += "            ?, ";
		sql += "            TO_DATE(?,'yyyymmdd'), ";
		sql += "            ?, ";
		sql += "            0, ";
		sql += "            ?, ";
		sql += "            ?  ";
		sql += "            ) ";




		String hospitalid = "HID";
		String patientid = "";
		String patientname = "";
		String modality = "";
		String studydate = "";
		String additional1 = "";

		if(patientRow != null) {
			patientid = patientRow.get("KANJA_ID").toString();
			patientname = patientRow.get("KANJISIMEI").toString();
			//modality = orderRow.get("KENSATYPE_NAME").toString();
			//studydate = orderRow.get("EX_KENSA_DATE").toString();
			//additional1 = "ORDER=" + orderRow.get("ORDERNO").toString();
		}

		arglist.add(eventdate);
		arglist.add(eventname);
		arglist.add(hospitalid);
		arglist.add(loginRow.get("USERID").toString());
		arglist.add(loginRow.get("USERNAME").toString());
		arglist.add(iparrd);
		arglist.add(patientid);
		arglist.add(patientname);

		arglist.add(studydate);

		arglist.add(modality);
		arglist.add(additional1);
		arglist.add("MediaCreator");

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);
		executeSQL(sql,args,conn);
	}
	*/

	/**
	 * オーダ一覧情報取得
	 * @param kensa_date        :検査日
	 * @param kanja_id          :患者ID
	 * @param uketuke_tantou_id :受付担当者ID
	 * @param byoutou_id        :病棟ID
	 * @param kensatype_id      :検査種別ID
	 * @param nyugaikbn			:入外区分
	 * @param status            :検査ステータス(変換前)
	 * @param sort				:ソート条件
	 * @param convstatus        :検査ステータス(変換後)
	 * @param conn              :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getOrderList(
			String kensa_date,
			String kanja_id,
			String uketuke_tantou_id,
			String byoutou_id,
			String kensatype_id,
			String nyugaikbn,
			String portablekbn,
			String status,
			String sort,
			String[] convstatus,
			Connection conn) throws Exception {

		DataTable Dat = null;

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql   += " select";
		sql   += "   RISSUMMARYVIEW.RIS_ID,";
		sql   += "   RISSUMMARYVIEW.KANJA_ID,";
		sql   += "   RISSUMMARYVIEW.STATUS,";
		sql   += "   RISSUMMARYVIEW.SEX,";
		sql   += "   RISSUMMARYVIEW.KANJISIMEI,";
		sql   += "   RISSUMMARYVIEW.KANASIMEI,";
		sql   += "   RISSUMMARYVIEW.BYOUTOU,";
		sql   += "   RISSUMMARYVIEW.BYOUSITU,";
		sql   += "   to_char(TRUNC((RISSUMMARYVIEW.KENSA_DATE - RISSUMMARYVIEW.BIRTHDAY) / 10000, 0)) as AGE,";
		sql   += "   RISSUMMARYVIEW.IRAI_SECTION,";
		sql   += "   RISSUMMARYVIEW.IRAI_DOCTOR_NAME || ' ' || RISSUMMARYVIEW.IRAIDOCTOR_TEL as IRAI_DOCTOR_NAME,";
		sql   += "   case";
		sql   += "     when length(EXMAINTABLE.RENRAKU_MEMO) > 0 then '連絡メモあり'";
		sql   += "   end as RENRAKU_MEMO,";
		sql   += "   EXMAINTABLE.RENRAKU_MEMO AS RENRAKU_MEMO_DETAIL,";
		sql   += "   RISSUMMARYVIEW.BYOUTOU_ID,";         // デバッグ用
		sql   += "   RISSUMMARYVIEW.BYOUSITU_ID,";        // デバッグ用
		sql   += "   RISSUMMARYVIEW.UKETUKE_TANTOU_ID,";  // デバッグ用
		sql   += "   RISSUMMARYVIEW.EX_KENSA_DATE,";      // デバッグ用
		sql   += "   RISSUMMARYVIEW.ORDER_KENSA_DATE,";   // デバッグ用
		sql   += "   RISSUMMARYVIEW.KENSATYPE_ID";        // デバッグ用
		sql   += " from";
		sql   += "   RISSUMMARYVIEW,";
		sql   += "   EXMAINTABLE";

		where += " where";
		where += "   RISSUMMARYVIEW.RIS_ID = EXMAINTABLE.RIS_ID";

		// 検査日
		if (!StringUtils.isEmpty(kensa_date)) {
			where += " and";
			where += "   ((RISSUMMARYVIEW.EX_KENSA_DATE = ? and RISSUMMARYVIEW.STATUS >= 10)";
			where += "     or";
			where += "   (RISSUMMARYVIEW.ORDER_KENSA_DATE = ? and RISSUMMARYVIEW.STATUS < 10))";
			// バインド
			arglist.add(kensa_date);
			arglist.add(kensa_date);
		}

		// 患者ID
		if (!StringUtils.isEmpty(kanja_id)) {
			where += " and";
			where += "   RISSUMMARYVIEW.KANJA_ID = ?";
			// バインド
			arglist.add(kanja_id);
		}

		// 受付者担当ID
		if (!StringUtils.isEmpty(uketuke_tantou_id)) {
			where += " and";
			where += "   RISSUMMARYVIEW.UKETUKE_TANTOU_ID = ?";
			// バインド
			arglist.add(uketuke_tantou_id);
		}

		// 病棟ID
		if (!StringUtils.isEmpty(byoutou_id)) {
			where += " and";
			where += "   RISSUMMARYVIEW.BYOUTOU_ID = ?";
			// バインド
			arglist.add(byoutou_id);
		}

		// 検査種別ID
		if (!StringUtils.isEmpty(kensatype_id)) {
			where += " and";
			where += "   RISSUMMARYVIEW.KENSATYPE_ID = ?";
			// バインド
			arglist.add(kensatype_id);
		}

		// 入外区分
		if (!StringUtils.isEmpty(nyugaikbn)) {
			where += " and";
			where += "     RISSUMMARYVIEW.KANJA_NYUGAIKBN = ?";
			// バインド
			arglist.add(nyugaikbn);
		}

		// ポータブル区分
		if (!StringUtils.isEmpty(portablekbn)) {
			where += " and";
			where += "     RISSUMMARYVIEW.PORTABLE_FLG = ?";
			// バインド
			arglist.add(portablekbn);
		}

		// 検査ステータス
		if (!StringUtils.isEmpty(Arrays.asList(convstatus))) {
			if (!StringUtils.isEmpty(where)) {
				where += " and";
			}
			where += "   RISSUMMARYVIEW.STATUS in (";

			String buff = "";
			for(int i = 0; i < convstatus.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += " ?";
				// バインド
				arglist.add(convstatus[i]);
			}
			where += buff + " )";
		}

		sql += where;

		// 検査ステータスまたはソート条件が存在する場合のみ、order by 句を使用
		if (!StringUtils.isEmpty(status) || !StringUtils.isEmpty(sort)) {
			sql += " order by";
		}

		// ソート条件
		if (Const.STATUS_UNREGISTERED.equals(status)) {         // 未受
			sql += "   RISSUMMARYVIEW.ORDER_KENSA_DATE asc";
		}
		// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
		else if (Const.STATUS_ISCALLING.equals(status)) {       // 呼出
			sql += "   RISSUMMARYVIEW.ORDER_KENSA_DATE asc";
		}
		// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
		else if (Const.STATUS_ISREGISTERED.equals(status)) {  // 受付
			sql += "   RISSUMMARYVIEW.BYOUTOU asc, RISSUMMARYVIEW.KANASIMEI asc";
		} else if (Const.STATUS_REST.equals(status)) {          // 保留
			sql += "   RISSUMMARYVIEW.EXAMSTARTDATE asc";
		} else if (Const.STATUS_ISFINISHED.equals(status)) {    // 検済
			sql += "   RISSUMMARYVIEW.EXAMENDDATE asc";
		} else {
			if (Const.ORDERLIST_SORT_KANASIMEI.equals(sort)) {       // 名前順
				sql += "   RISSUMMARYVIEW.KANASIMEI asc, RISSUMMARYVIEW.BYOUTOU asc, RISSUMMARYVIEW.BYOUSITU asc";
			} else if (Const.ORDERLIST_SORT_BYOUTOU.equals(sort)) {  // 病棟順
				sql += "   RISSUMMARYVIEW.BYOUTOU asc, RISSUMMARYVIEW.BYOUSITU asc, RISSUMMARYVIEW.KANASIMEI asc";
			}
		}

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);
		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * オーダ詳細情報取得
	 * @param ris_id     :RIS識別ID
	 * @param convstatus :検査ステータス(変換後)
	 * @param conn       :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getOrderDetail(
			String ris_id,
			String[] convstatus,
			Connection conn) throws Exception {

		DataTable Dat = null;

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql   += " select";
		sql   += "   RISSUMMARYVIEW.RIS_ID,";
		sql   += "   RISSUMMARYVIEW.KANJA_ID,";
		sql   += "   RISSUMMARYVIEW.STATUS,";
		sql   += "   RISSUMMARYVIEW.SEX,";
		sql   += "   RISSUMMARYVIEW.KANJISIMEI,";
		sql   += "   RISSUMMARYVIEW.KANASIMEI,";
		sql   += "   RISSUMMARYVIEW.BYOUTOU,";
		sql   += "   RISSUMMARYVIEW.BYOUSITU,";
		sql   += "   to_char(TRUNC((RISSUMMARYVIEW.KENSA_DATE - RISSUMMARYVIEW.BIRTHDAY) / 10000, 0)) as AGE,";
		sql   += "   RISSUMMARYVIEW.IRAI_SECTION,";
		sql   += "   RISSUMMARYVIEW.IRAI_DOCTOR_NAME || ' ' || RISSUMMARYVIEW.IRAIDOCTOR_TEL as IRAI_DOCTOR_NAME,";
		sql	  += "   EXMAINTABLE.RENRAKU_MEMO,";
		// 2020.09.15 Add Nishihara@COSMO start 実施技師登録機能追加対応
		sql	  += "   EXMAINTABLE.MED_PERSON_ID01,";
		sql	  += "   EXMAINTABLE.MED_PERSON_ID02,";
		sql	  += "   EXMAINTABLE.MED_PERSON_ID03,";
		// 2020.09.15 Add Nishihara@COSMO end 実施技師登録機能追加対応
		sql   += "   RISSUMMARYVIEW.ACNO,";
		sql   += "   RISSUMMARYVIEW.RECEIPTNUMBER,";
		sql   += "   RISSUMMARYVIEW.RECEIPTDATE,";
		sql   += "   case";
		sql   += "     when RISSUMMARYVIEW.STATUS >= 10 then RISSUMMARYVIEW.EX_KENSA_DATE";
		sql   += "     when RISSUMMARYVIEW.STATUS < 10 then RISSUMMARYVIEW.ORDER_KENSA_DATE";
		sql   += "   end as KENSA_DATE,";
		sql   += "   RISSUMMARYVIEW.ORDERCOMMENT_ID,";
		sql   += "   RISSUMMARYVIEW.KENSA_SIJI,";
		sql   += "   ORDERINDICATETABLE.RINSYOU,";
		sql   += "   RISSUMMARYVIEW.REMARKS,";
		sql   += "   PATIENTINFO.HANDICAPPED,";
		sql   += "   PATIENTINFO.INFECTION,";
		sql   += "   PATIENTINFO.CONTRAINDICATION,";
		sql   += "   PATIENTINFO.ALLERGY,";
		sql   += "   PATIENTINFO.PREGNANCY,";
		sql   += "   PATIENTINFO.NOTES,";
		sql   += "   PATIENTINFO.EXAMDATA,";
		sql   += "   RISSUMMARYVIEW.BYOUTOU_ID,";         // デバッグ用
		sql   += "   RISSUMMARYVIEW.BYOUSITU_ID,";        // デバッグ用
		sql   += "   RISSUMMARYVIEW.UKETUKE_TANTOU_ID,";  // デバッグ用
		sql   += "   RISSUMMARYVIEW.EX_KENSA_DATE,";      // デバッグ用
		sql   += "   RISSUMMARYVIEW.ORDER_KENSA_DATE,";   // デバッグ用
		sql   += "   RISSUMMARYVIEW.KENSATYPE_ID,";       // デバッグ用
		sql   += "   RISSUMMARYVIEW.KENSASITU_ID,";
		sql   += "   RISSUMMARYVIEW.KENSAKIKI_ID,";
		sql   += "   RISSUMMARYVIEW.JISSI_KENSAKIKI_ID";
		sql   += " from";
		sql   += "   RISSUMMARYVIEW,";
		sql   += "   EXMAINTABLE,";
		sql   += "   PATIENTINFO,";
		sql   += "   ORDERINDICATETABLE";

		where += " where";
		where += "   RISSUMMARYVIEW.RIS_ID = EXMAINTABLE.RIS_ID";
		where += " and";
		where += "   RISSUMMARYVIEW.RIS_ID = ORDERINDICATETABLE.RIS_ID";
		where += " and";
		where += "   RISSUMMARYVIEW.KANJA_ID = PATIENTINFO.KANJA_ID";
		where += " and";
		where += "   RISSUMMARYVIEW.RIS_ID = ?";

		// バインド
		arglist.add(ris_id);

		// 検査ステータス
		if (!StringUtils.isEmpty(Arrays.asList(convstatus))) {
			if (!StringUtils.isEmpty(where)) {
				where += " and";
			}
			where += "   RISSUMMARYVIEW.STATUS in (";

			String buff = "";
			for(int i = 0; i < convstatus.length; i++) {
				if (!buff.equals("")) {
					buff += ",";
				}
				buff += " ?";
				// バインド
				arglist.add(convstatus[i]);
			}
			where += buff + " )";
		}

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);
		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * オーダ詳細情報（撮影部位情報）取得
	 * @param risId :RIS識別ID
	 * @param conn  :接続情報
	 * @return
	 * @throws Exception
	 */
	public static DataTable getSatueiBuiInfo(
			String risId,
			int status,
			Connection conn) throws Exception{

		DataTable Dat = null;

		String sql = "";
		String where = "";
		String table = "EXBUITABLE";
		ArrayList<Object> arglist = new ArrayList<Object>();

		// 検査ステータス：受付未満の場合
		if (status < Integer.parseInt(Const.STATUS_ISREGISTERED)) {
			table = "ORDERBUITABLE";
		}

		sql += " select";
		sql += "     BUIMASTER.BUI_NAME,";
		sql += "     HOUKOUMASTER.HOUKOU_NAME,";
		sql += "     SAYUUMASTER.SAYUU_NAME,";
		sql += "     KENSAHOUHOUMASTER.KENSAHOUHOU_NAME,";
		sql += "     " + table + ".BUICOMMENT";
		sql += " from";
		sql += "     BUIMASTER,";
		sql += "     HOUKOUMASTER,";
		sql += "     SAYUUMASTER,";
		sql += "     KENSAHOUHOUMASTER,";
		sql += "     " + table;

		where += " where";
		where += "     " + table + ".RIS_ID = ?";
		// バインド
		arglist.add(risId);
		where += " and";
		where += "     " + table + ".BUI_ID = BUIMASTER.BUI_ID(+)";
		where += " and";
		where += "     " + table + ".HOUKOU_ID = HOUKOUMASTER.HOUKOU_ID(+)";
		where += " and";
		where += "     " + table + ".SAYUU_ID = SAYUUMASTER.SAYUU_ID(+)";
		where += " and";
		where += "     " + table + ".KENSAHOUHOU_ID = KENSAHOUHOUMASTER.KENSAHOUHOU_ID(+)";
		// 検査ステータス：受付以上の場合、中止ステータスは除外する
		if (status >= Integer.parseInt(Const.STATUS_ISREGISTERED)) {
			where += " and";
			where += "     " + table + ".SATUEISTATUS != " + CommonString.SATUEISTATUS_STOP;
		}

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);
		Dat = executeQuery(sql, args, conn);

		return Dat;
	}

	/**
	 * 連絡メモ更新
	 * @param ris_id   :RIS識別ID
	 * @param memo     :連絡メモ
	 * @param conn     :接続情報
	 * @throws Exception
	 */
	public static boolean memoUpdate(
			String ris_id,
			String memo,
			Connection conn) throws Exception {

		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();

		sql += " update";
		sql += "     EXMAINTABLE";
		sql += " set";
		sql += "     RENRAKU_MEMO = ?";
		arglist.add(memo);

		where += " where";
		where += "     RIS_ID = ?";
		arglist.add(ris_id);

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		try {
			executeSQL(sql, args, conn);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

    public static DataTable getOrderList(String risid, Connection conn) throws Exception {

        //logger.debug("getOrderList start");

        DataTable Dat = null;
        String sql = "";
        String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();


        sql += " SELECT";
        sql += "   b.label,";
        sql += "   b.shortlabel,";
        sql += "   a.*, ";
        sql += "   null as bui_name,";
        sql += "   null as sayuu_name,";
        sql += "   null as houkou_name, ";
        sql += "   null as kensahouhou_name,";
        sql += "   d.comment_name";
        sql += " FROM";
        sql += "   rissummaryview a,";
        sql += "   statusdefine b,";
        sql += "   predefinedcommentmaster d";

        where += " WHERE";
        where += "   a.status = b.statuscode(+)";
        where += " AND a.ordercomment_id = d.comment_id(+)";
        where += " AND a.ris_id = ?";
        arglist.add(risid);

        sql += where;
        sql += " ORDER BY a.ris_id";

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		Dat = executeQuery(sql, args, conn);

		if (Dat.getRowCount() != 1) {
			return Dat;
		}

        sql = "";
        where = "";

        sql += " SELECT * ";
        sql += " FROM buisummaryview a ";

        where += " WHERE a.ris_id = ? ";

        sql += where;
        sql += " ORDER BY a.ris_id,a.no ";

        DataTable buiDat = executeQuery(sql, args, conn);

        if(buiDat.getRowCount() > 0) {
            Dat.getRows().get(0).put("BUI_NAME", buiDat.getRows().get(0).get("BUI_NAME").toString());
            Dat.getRows().get(0).put("SAYUU_NAME", buiDat.getRows().get(0).get("SAYUU_NAME").toString());
            Dat.getRows().get(0).put("HOUKOU_NAME", buiDat.getRows().get(0).get("HOUKOU_NAME").toString());
            Dat.getRows().get(0).put("KENSAHOUHOU_NAME", buiDat.getRows().get(0).get("KENSAHOUHOU_NAME").toString());
        }

 		//logger.debug("getOrderList end");

        return Dat;
    }

    /**
     * コメントIDを指定して定型コメントマスタ取得する
     * @param commentId :コメントID配列
     * @param conn      :接続情報
     * @return          :定型コメントマスタ
     * @throws Exception
     */
	public static DataTable getPredefineCommentMaster(String[] commentId,
			Connection conn) throws Exception {
		String sql = "";
		sql += " SELECT *";
		sql += " FROM PREDEFINEDCOMMENTMASTER";
		sql += " WHERE COMMENT_ID IN";
		sql += " (";
		for (int i = 0; i < commentId.length; i++) {
			if (i > 0) {
				sql += ",";
			}
			sql += "?";
		}
		sql += " )";
		return executeQuery(sql, commentId, conn);
	}
    /**
     * コメントIDを指定して定型コメントマスタ取得する
     * @param commentId :コメントID配列
     * @param conn      :接続情報
     * @return          :実施件数
     * @throws Exception
     */
	public static boolean ExMAinReturnUpdate(
			String ris_id,
			Connection conn) throws Exception {
		String sql = "";
		String where = "";
		ArrayList<Object> arglist = new ArrayList<Object>();
		sql += " UPDATE EXMAINTABLE ";
		sql += " SET ";
		sql += "  EXAMSTARTDATE = NULL ";
		sql += " ,KENSASITU_ID = NULL ";
		sql += " ,KENSAKIKI_ID = NULL ";
		// 2020.09.17 Add Nishihara@COSMO start 実施技師登録機能追加対応
		sql += " ,MED_PERSON_ID01 = NULL ";
		sql += " ,MED_PERSON_NAME01 = NULL ";
		sql += " ,MED_PERSON_SYOKUINKBN01 = NULL ";
		sql += " ,MED_PERSON_ID02 = NULL ";
		sql += " ,MED_PERSON_NAME02 = NULL ";
		sql += " ,MED_PERSON_SYOKUINKBN02 = NULL ";
		sql += " ,MED_PERSON_ID03 = NULL ";
		sql += " ,MED_PERSON_NAME03 = NULL ";
		sql += " ,MED_PERSON_SYOKUINKBN03 = NULL ";
		// 2020.09.17 Add Nishihara@COSMO end 実施技師登録機能追加対応
		sql += " ,STATUS = '10'  ";
		sql += " ,STARTNUMBER = 0  ";
		sql += " ,EXAMSAVEFLAG = '0' ";
		where += " WHERE RIS_ID = ? ";
		arglist.add(ris_id);

		sql += where;

		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);

		try {
			executeSQL(sql, args, conn);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * WorkListInfoに受済オーダーと同一AcNoのレコードがあるか確認
	 * @param ris_id :RIS_ID
	 * @param conn   :接続情報
	 * @return       :レコード有無
	 * */
	public static boolean isMWMOrder(String ris_id, String status, Connection conn) throws Exception {
		String sql = "";
		ArrayList<Object> arglist = new ArrayList<Object>();
		
		sql += "SELECT 1 ";
		sql += "FROM RisSummaryView rsv ";
		sql += "WHERE ris_id = ? ";
		sql += "AND status = ? ";
		sql += "AND EXISTS( ";
		sql += "  select 1 ";
		sql += "  from WorkListInfo wli ";
		sql += "  where wli.accession_number = rsv.acno ";
		sql += ")";
		
		arglist.add(ris_id);
		arglist.add(status);
		Object[] args = new Object[arglist.size()];
		arglist.toArray(args);
		
		try {
			// データあり
			if(existRecord(sql, args, conn)) return true;
		}catch(Exception e) {
			throw e;
		}
		// データなし
		return false;
	}
}
