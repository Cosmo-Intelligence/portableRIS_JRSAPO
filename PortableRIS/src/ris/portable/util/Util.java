package ris.portable.util;


import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Util {

	private static Log logger = LogFactory.getLog(Util.class);


	public static String toNullString(String str){
		return toNullString(str, "");
	}

	public static String toNullInteger(String str){
		return toNullString(str, null);
	}

	public static String toNullZero(Object obj){
		if (obj == null) {
			return toNullString(null, "0");
		} else {
			return obj.toString();
		}
	}

	public static String toNullString(String str, String str2){
		if(str == null){
			return str2;
		}

		if(str.equals("")){
			return str2;
		}

		return str;
	}


	//hh:mmをhhmmに変換
	public static String toTimePlainString(String tim){

		String ret = "";

		if(Util.toNullString(tim).equals("")){
			return ret;
		}

		String[] list = tim.split(":",-1);

		for(int i = 0; i < list.length; i++){
			ret += list[i];

			if(i > 1){
				break;
			}
		}

		return ret;

	}

	//hhmmをhh:mmに変換
	public static String toTimeFormatString(String tim){

		String ret = "";

		if(Util.toNullString(tim).equals("")){
			return ret;
		}

		if(tim.indexOf(":") > -1){
			return tim;
		}

		ret = tim.substring(0, 2) + ":" + tim.substring(2,4);

		return ret;

	}

	public static boolean toBool(String val){
		boolean ret = false;


		if(Util.toNullString(val).equals("")){
			return false;
		}

		if(val.equals(String.valueOf(true))){
			ret = true;
		}

		if(val.equals("1")){
			ret = true;
		}

		return ret;
	}


	public static String toCurrencyString(String value){

		if(Util.toNullString(value).equals("")){
			return "";
		}

		return toCurrencyString(Integer.parseInt(value));
	}


	public static String toCurrencyString(int value){
		NumberFormat format = NumberFormat.getCurrencyInstance();
		format.setMaximumFractionDigits(0);
		return format.format(value);
	}

	public static Timestamp toTimestamp(String str){

		Timestamp ret = null;

		try{
			ret = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(str).getTime());
		}
		catch(Exception e){
			logger.error(e.toString(),e);
		}

		return ret;
	}

	public static String toFormatString(Timestamp time,String format){

		String ret = "";

		if(time == null){
			return ret;
		}


		try{
			ret = new SimpleDateFormat(format).format(time);
		}
		catch(Exception e){
			logger.error(e.toString(),e);
		}

		return ret;
	}

	/**
	 * 文字列をnullであるか判定し、nullでなければInteger型に変換
	 * @param str
	 * @return
	 */
	public static Integer isNullAndParseInt(Object obj) throws Exception {

		if (obj == null) {
			return null;
		}
		else {
			// 2019.03.13 Mod H.Watanabe@COSMO Start 20190301：山形中央病院向け対応
			try{
				return Integer.parseInt(obj.toString());
			}catch(NumberFormatException e){
				return null;
			}

			//return Integer.parseInt(obj.toString());
			// 2019.03.13 Mod H.Watanabe@COSMO End   20190301：山形中央病院向け対応
		}
	}

	/**
	 * BGRをRGBに変換する
	 * @param bgr
	 * @return
	 * @throws Exception
	 */
	public static int convertBGRtoRGB(int bgr) throws Exception {

		return (bgr & 0x000000FF) << 16 | (bgr & 0x0000FF00) | (bgr & 0x00FF0000 ) >> 16;
	}

	/*
	public static String toFormatStringJP(Timestamp time,String format){

		String ret = "";

		if(time == null){
			return ret;
		}

		Connection conn = null;

		try{

			conn = DataBase.getRisConnection();

			DataTable dat = DataBase.getJeraBirthday(time, conn);

			String GGGG = dat.getRows().get(0).get("JERA2").toString();
			String G = dat.getRows().get(0).get("JERA").toString();
			String year = dat.getRows().get(0).get("YEAR").toString();

			format = format.replaceAll("GGGG", GGGG);
			format = format.replaceAll("G", G);
			format = format.replaceAll("[yY]+",year);

			ret = new SimpleDateFormat(format).format(time);


		}
		catch(Exception e){
			logger.error(e.toString(),e);
		}
		finally{
			DataBase.closeConnection(conn);
		}

		return ret;
	}

	public static String toFormatStringJPforJava6(Timestamp time,String format){

		String ret = "";

		if(time == null){
			return ret;
		}

		Locale locale = new Locale("ja","JP","JP");

		try{
			ret = new SimpleDateFormat(format,locale).format(time);
		}
		catch(Exception e){
			logger.error(e.toString(),e);
		}

		return ret;
	}
	*/

	/*
	public static boolean doLogin(HttpServletRequest request, HttpServletResponse response, ServletContext ctx ,boolean isKarte, boolean isChangeUser){

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		if(!isChangeUser){

			//セッションの破棄
			SessionControler.clearSession(request);

			//セッション作成
			SessionControler.createSession(request);

			//システム設定取り込み
			Config config = Config.getConfig(ctx);
			SessionControler.setValue(request, SessionControler.SYSTEMCONFIG, config);

		}

		if(Common.toNullString(userid).equals("")){
			request.setAttribute("ERRMSG", "ユーザIDが入力されていません。");
			return false;
		}

		if(isKarte){
			password = null;	//電子カルテ連携時はパスワードなし
		}
		else{
			if(Common.toNullString(password).equals("")){
				request.setAttribute("ERRMSG", "パスワードがないのは認められません。");
				return false;
			}
		}


		Connection conn = null;

		try{
			conn = DataBase.getUnityConnection();

			DataTable Dat = DataBase.getUserManage(userid, password, conn);

			if(Dat == null){
				return false;
			}

			if(Dat.getRowCount() == 0){
				request.setAttribute("ERRMSG", "ユーザIDまたはパスワードが間違っています。");
				return false;
			}

			//ログインユーザー保持
			SessionControler.setValue(request, SessionControler.LOGINUSER, Dat.getRows().get(0));


			//監査証跡
			Connection risConn = null;
			Connection unityConn = null;
			try{

				risConn = DataBase.getRisConnection();
				unityConn = DataBase.getUnityConnection();

				Timestamp sysdate = DataBase.getSysdate(unityConn);

				DataTable serverDat = DataBase.getCurrentServerType(risConn);

				String servertype = serverDat.getRows().get(0).get("CURRENTTYPE").toString();

				String ipaddr = request.getRemoteAddr();

				DataBase.insertAuditTrail(servertype, sysdate, "LOGIN", null, Dat.getRows().get(0), ipaddr, unityConn);
				unityConn.commit();
			}

			catch(Exception ee){
				try{
					unityConn.rollback();
				}
				catch(Exception eee){
					//NULL;
				}
				logger.error(ee.toString(),ee);
			}
			finally{
				DataBase.closeConnection(risConn);
				DataBase.closeConnection(unityConn);

			}

		}
		catch(Exception e){
			logger.error(e.toString(),e);
			//受入No13 DBエラー時のメッセージ
			//request.setAttribute("ERRMSG", "ログイン処理に失敗しました。<br>" + e.toString());
			request.setAttribute("ERRMSG", "ログイン処理に失敗しました。");
			return false;
		}
		finally{
			DataBase.closeConnection(conn);
		}

		return true;
	}
	*/

	/**
	 * JSON形式文字列取得
	 * @param dto JSON変換構造体
	 * @return
	 */
	public static String getJson(Object dto) {

        // jacksonでJSON文字列にエンコードする。
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = "";

        try {
			json = mapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}
}
