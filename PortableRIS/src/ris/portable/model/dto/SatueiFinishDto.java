package ris.portable.model.dto;

import java.util.ArrayList;

public class SatueiFinishDto extends BaseDto {

	private ArrayList<InsertData> satuei_array;

	private ArrayList<Master> uketuke_array;
	private ArrayList<Master> byoutou_array;
	private ArrayList<Master> kensatype_array;
	private ArrayList<Master2> kensasitu_array;
	private ArrayList<Template> template_array;
	private ArrayList<Status> status_array;
	private ArrayList<Master> nyugai_array;
	private ArrayList<Master> portable_array;
	// 2020.09.14 Add Nishihara@COSMO start 実施技師登録機能追加対応
	private ArrayList<Zisshisha> zisshisha_array;
	// 2020.09.14 Add Nishihara@COSMO end 実施技師登録機能追加対応





	public static class InsertData {
		private String ris_id;
		private int bui_no;
		private int no;
		private String satueistatus;
		private String satueimenu_id;
		private String satueimenu_name;
		private String satueimenu_name_kana;
		private String satuei_code;
		private String film_id;
		private int partition;
		private int used;
		private String reshot_flg;


		public String getRis_id() {
			return ris_id;
		}
		public void setRis_id(String ris_id) {
			this.ris_id = ris_id;
		}
		public int getBui_no() {
			return bui_no;
		}
		public void setBui_no(int bui_no) {
			this.bui_no = bui_no;
		}
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
		public String getSatueistatus() {
			return satueistatus;
		}
		public void setSatueistatus(String satueistatus) {
			this.satueistatus = satueistatus;
		}
		public String getSatueimenu_id() {
			return satueimenu_id;
		}
		public void setSatueimenu_id(String satueimenu_id) {
			this.satueimenu_id = satueimenu_id;
		}
		public String getSatueimenu_name() {
			return satueimenu_name;
		}
		public void setSatueimenu_name(String satueimenu_name) {
			this.satueimenu_name = satueimenu_name;
		}
		public String getSatueimenu_name_kana() {
			return satueimenu_name_kana;
		}
		public void setSatueimenu_name_kana(String satueimenu_name_kana) {
			this.satueimenu_name_kana = satueimenu_name_kana;
		}
		public String getSatuei_code() {
			return satuei_code;
		}
		public void setSatuei_code(String satuei_code) {
			this.satuei_code = satuei_code;
		}
		public String getFilm_id() {
			return film_id;
		}
		public void setFilm_id(String film_id) {
			this.film_id = film_id;
		}
		public int getPartition() {
			return partition;
		}
		public void setPartition(int partition) {
			this.partition = partition;
		}
		public int getUsed() {
			return used;
		}
		public void setUsed(int used) {
			this.used = used;
		}
		public String getReshot_flg() {
			return reshot_flg;
		}
		public void setReshot_flg(String reshot_flg) {
			this.reshot_flg = reshot_flg;
		}

	}

	public ArrayList<InsertData> getSatuei_array() {
		return satuei_array;
	}
	public void setSatuei_array(ArrayList<InsertData> satuei_array) {
		this.satuei_array = satuei_array;
	}






	public ArrayList<Master> getUketuke_array() {
		return uketuke_array;
	}
	public void setUketuke_array(ArrayList<Master> uketuke_array) {
		this.uketuke_array = uketuke_array;
	}

	public ArrayList<Master> getByoutou_array() {
		return byoutou_array;
	}
	public void setByoutou_array(ArrayList<Master> byoutou_array) {
		this.byoutou_array = byoutou_array;
	}

	public ArrayList<Master> getKensatype_array() {
		return kensatype_array;
	}
	public void setKensatype_array(ArrayList<Master> kensatype_array) {
		this.kensatype_array = kensatype_array;
	}

	public ArrayList<Master2> getKensasitu_array() {
		return kensasitu_array;
	}
	public void setKensasitu_array(ArrayList<Master2> kensasitu_array) {
		this.kensasitu_array = kensasitu_array;
	}

	public ArrayList<Template> getTemplate_array() {
		return template_array;
	}
	public void setTemplate_array(ArrayList<Template> template_array) {
		this.template_array = template_array;
	}

	public ArrayList<Status> getStatus_array() {
		return status_array;
	}
	public void setStatus_array(ArrayList<Status> status_array) {
		this.status_array = status_array;
	}

	public ArrayList<Master> getNyugai_array() {
		return nyugai_array;
	}
	public void setNyugai_array(ArrayList<Master> nyugai_array) {
		this.nyugai_array = nyugai_array;
	}

	public ArrayList<Master> getPortable_array() {
		return portable_array;
	}
	public void setPortable_array(ArrayList<Master> portable_array) {
		this.portable_array = portable_array;
	}

	// 2020.09.14 Add Nishihara@COSMO start 実施技師登録機能追加対応
	public ArrayList<Zisshisha> getZisshisha_array() {
		return zisshisha_array;
	}

	public void setZisshisha_array(ArrayList<Zisshisha> zisshisha_array) {
		this.zisshisha_array = zisshisha_array;
	}
	// 2020.09.14 Add Nishihara@COSMO end 実施技師登録機能追加対応

	public static class Master {
		private String id;
		private String name;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public static class Master2 {
		private String id;
		private String name;
		private ArrayList<Master> kensakiki_array;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<Master> getKensakiki_array() {
			return kensakiki_array;
		}
		public void setKensakiki_array(ArrayList<Master> kensakiki_array) {
			this.kensakiki_array = kensakiki_array;
		}
	}

	public static class Template {
		private int id;
		private String name;

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public static class Status {
		private int statuscode;
		private String shortlabel;
		private int color;
		private int colorbk;
		// 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
		private int color_on;
		private int colorbk_on;
		private int color_off;
		private int colorbk_off;
		// 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

		public int getStatuscode() {
			return statuscode;
		}
		public void setStatuscode(int statuscode) {
			this.statuscode = statuscode;
		}

		public String getShortlabel() {
			return shortlabel;
		}
		public void setShortlabel(String shortlabel) {
			this.shortlabel = shortlabel;
		}

		public int getColor() {
			return color;
		}
		public void setColor(int color) {
			this.color = color;
		}

		public int getColorbk() {
			return colorbk;
		}
		public void setColorbk(int colorbk) {
			this.colorbk = colorbk;
		}

		// 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
		public int getColor_ON() {
			return color_on;
		}
		public void setColor_ON(int color_on) {
			this.color_on = color_on;
		}
		public int getColorbk_ON() {
			return colorbk_on;
		}
		public void setColorbk_ON(int colorbk_on) {
			this.colorbk_on = colorbk_on;
		}
		public int getColor_OFF() {
			return color_off;
		}
		public void setColor_OFF(int color_off) {
			this.color_off = color_off;
		}
		public int getColorbk_OFF() {
			return colorbk_off;
		}
		public void setColorbk_OFF(int colorbk_off) {
			this.colorbk_off = colorbk_off;
		}
		// 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応
	}

	// 2020.09.14 Add Nishihara@COSMO start 実施技師登録機能追加対応
	public static class Zisshisha {
		private String userid;
		private String username;

		public String getUserId() {
			return userid;
		}
		public void setUserId(String userid) {
			this.userid = userid;
		}

		public String getUserName() {
			return username;
		}
		public void setUserName(String username) {
			this.username = username;
		}
	}
	// 2020.09.14 Add Nishihara@COSMO end 実施技師登録機能追加対応
}