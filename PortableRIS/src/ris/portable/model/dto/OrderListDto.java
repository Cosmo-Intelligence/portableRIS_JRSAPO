package ris.portable.model.dto;

import java.util.List;

public class OrderListDto extends BaseDto {

	private List<Order> order_array;


	public List<Order> getOrder_array() {
		return order_array;
	}

	public void setOrder_array(List<Order> orderlist_array) {
		this.order_array = orderlist_array;
	}

	public static class Order{
		private String ris_id;
		private String kanja_id;
		private Integer status;
		private String sex;
		private String kanjisimei;
		private String kanasimei;
		private String byoutou;
		private String byousitu;
		private String age;
		private String irai_section;
		private String irai_doctor_name;
		private String renraku_memo;
		private boolean transition_flg;


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

		public Integer getStatus() {
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

		// 2020.03.05 Nishihara@COSMO Start 町田市民病院PortableRIS改造対応
		public boolean getTransition_flg() {
			return transition_flg;
		}

		public void setTransition_flg(boolean transition_flg) {
			this.transition_flg = transition_flg;
		}
		// 2020.03.05 Nishihara@COSMO End 町田市民病院PortableRIS改造対応
	}
}
