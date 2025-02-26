package ris.portable.model.dto;

import java.sql.Timestamp;

public class OrderDetailDto extends BaseDto{

	private OrderDetail order_detail;

	public OrderDetail getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(OrderDetail order_detail) {
		this.order_detail = order_detail;
	}

	public static class OrderDetail{
		private String ris_id;
		private String kanja_id;
		private Integer status = null;
		private String sex;
		private String kanjisimei;
		private String kanasimei;
		private String byoutou;
		private String byousitu;
		private String age;
		private String irai_section;
		private String irai_doctor_name;
		private String renraku_memo;
		// 2020.09.15 Add Nishihara@COSMO start 実施技師登録機能対応
		private String med_person_id01;
		private String med_person_id02;
		private String med_person_id03;
		// 2020.09.15 Add Nishihara@COSMO end 実施技師登録機能対応
		private String acno;
		private Integer receiptnumber = null;
		private Timestamp receiptdate;
		private Integer kensa_date = null;
		private String irai_info;
		private String kanja_info;
		private String satueibui_info;
		private String kensa_kbn; // add 201806_ポータブルRIS検査系種別対応

		// add sta 201806_ポータブルRIS検査系種別対応
		public String getKensa_kbn() {
			return kensa_kbn;
		}

		public void setKensa_kbn(String kensa_kbn) {
			this.kensa_kbn = kensa_kbn;
		}
		// add end 201806_ポータブルRIS検査系種別対応

		public String getRis_id() {
			return ris_id;
		}

		public void setRis_id(String ris_id) {
			this.ris_id = ris_id;
		}

		public String getKanja_id() {
			return kanja_id;
		}

		public void setKanja_id(String kanja_id) {
			this.kanja_id = kanja_id;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getKanjisimei() {
			return kanjisimei;
		}

		public void setKanjisimei(String kanjisimei) {
			this.kanjisimei = kanjisimei;
		}

		public String getKanasimei() {
			return kanasimei;
		}

		public void setKanasimei(String kanasimei) {
			this.kanasimei = kanasimei;
		}

		public String getByoutou() {
			return byoutou;
		}

		public void setByoutou(String byoutou) {
			this.byoutou = byoutou;
		}

		public String getByousitu() {
			return byousitu;
		}

		public void setByousitu(String byousitu) {
			this.byousitu = byousitu;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age + "才";
		}

		public String getIrai_section() {
			return irai_section;
		}

		public void setIrai_section(String irai_section) {
			this.irai_section = irai_section;
		}

		public String getIrai_doctor_name() {
			return irai_doctor_name;
		}

		public void setIrai_doctor_name(String irai_doctor_name) {
			this.irai_doctor_name = irai_doctor_name;
		}

		public String getRenraku_memo() {
			return renraku_memo;
		}

		public void setRenraku_memo(String renraku_memo) {
			this.renraku_memo = renraku_memo;
		}
		// 2020.09.15 Add Nishihara@COSMO start 実施技師登録機能追加対応

		public String getMed_person_id01() {
			return med_person_id01;
		}

		public void setMed_person_id01(String med_person_id01) {
			this.med_person_id01 = med_person_id01;
		}

		public String getMed_person_id02() {
			return med_person_id02;
		}

		public void setMed_person_id02(String med_person_id02) {
			this.med_person_id02 = med_person_id02;
		}

		public String getMed_person_id03() {
			return med_person_id03;
		}

		public void setMed_person_id03(String med_person_id03) {
			this.med_person_id03 = med_person_id03;
		}

		// 2020.09.15 Add Nishihara@COSMO end 実施技師登録機能追加対応

		public String getAcno() {
			return acno;
		}

		public void setAcno(String acno) {
			this.acno = acno;
		}

		public Integer getReceiptnumber() {
			return receiptnumber;
		}

		public void setReceiptnumber(Integer receiptnumber) {
			this.receiptnumber = receiptnumber;
		}

		public Timestamp getReceiptdate() {
			return receiptdate;
		}

		public void setReceiptdate(Timestamp receiptdate) {
			this.receiptdate = receiptdate;
		}

		public int getKensa_date() {
			return kensa_date;
		}

		public void setKensa_date(Integer kensa_date) {
			this.kensa_date = kensa_date;
		}

		public String getIrai_info() {
			return irai_info;
		}

		public void setIrai_info(String irai_info) {
			this.irai_info = irai_info;
		}

		public String getKanja_info() {
			return kanja_info;
		}

		public void setKanja_info(String kanja_info) {
			this.kanja_info = kanja_info;
		}

		public String getSatueibui_info() {
			return satueibui_info;
		}

		public void setSatueibui_info(String satueibui_info) {
			this.satueibui_info = satueibui_info;
		}

		/**
		 * ViewAirのURL
		 */
		private String _viewAirUrl = "";
		/**
		 * ViewAirのURLを取得する
		 * @return ViewAirのURL
		 */
		public String getViewAirUrl() {
			return _viewAirUrl;
		}
		/**
		 * ViewAirのURLをセットする
		 * @param viewAirUrl ViewAirのURL
		 */
		public void setViewAirUrl(String viewAirUrl) {
			_viewAirUrl = viewAirUrl;
		}
	}
}