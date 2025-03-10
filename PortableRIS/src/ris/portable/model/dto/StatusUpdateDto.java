package ris.portable.model.dto;

import java.util.List;

public class StatusUpdateDto extends BaseDto {

	private List<Info> satuei_array;
	private List<Info> mpps_array;

	public List<Info> getSatuei_array() {
		return satuei_array;
	}

	public void setSatuei_array(List<Info> satuei_array) {
		this.satuei_array = satuei_array;
	}

	public List<Info> getMpps_array() {
		return mpps_array;
	}

	public void setMpps_array(List<Info> mpps_array) {
		this.mpps_array = mpps_array;
	}

	public static class Info {
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

}
