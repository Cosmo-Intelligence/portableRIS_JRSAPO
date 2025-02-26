package ris.portable.model.dto;

public class LoginDto extends BaseDto {

	private String userid;
	private String username;
	// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
	private Boolean isReadMode = false;
	// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応

	// 2020.08.27 Add Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器)
	//検査室・検査機器の前回選択したプルダウンの情報保持
	private String kensasituid_pulldown;
	private String kensakikiid_pulldown;
	// 2020.08.27 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器)

	// 2020.09.09 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
	private String kanjanyugai_pulldown;
	private String kensatypeid_pulldown;
	// 2020.09.09 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
	public Boolean getIsReadMode() {
		return isReadMode;
	}
	public void setIsReadMode(Boolean isReadMode) {
		this.isReadMode = isReadMode;
	}
	// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応

	// 2020.08.27 Add Nishihara@COSMO start MWM対象装置の制御追加(検査室・検査機器)
	public String getKensasituid_pulldown() {
		return kensasituid_pulldown;
	}

	public void setKensasituid_pulldown(String kensasituid_pulldown) {
		this.kensasituid_pulldown = kensasituid_pulldown;
	}

	public String getKensakikiid_pulldown() {
		return kensakikiid_pulldown;
	}

	public void setKensakikiid_pulldown(String kensakikiid_pulldown) {
		this.kensakikiid_pulldown = kensakikiid_pulldown;
	}
	// 2020.08.27 Add Nishihara@COSMO end MWM対象装置の制御追加(検査室・検査機器)

	// 2020.09.09 Add Nishihara@COSMO start 検索一覧初期表示対応(入外区分・検査種別)
	public String getKanjanyugai_pulldown() {
		return kanjanyugai_pulldown;
	}

	public void setKanjanyugai_pulldown(String kanjanyugai_pulldown) {
		this.kanjanyugai_pulldown = kanjanyugai_pulldown;
	}

	public String getKensatypeid_pulldown() {
		return kensatypeid_pulldown;
	}

	public void setKensatypeid_pulldown(String kensatypeid_pulldown) {
		this.kensatypeid_pulldown = kensatypeid_pulldown;
	}
	// 2020.09.09 Add Nishihara@COSMO end 検索一覧初期表示対応(入外区分・検査種別)

	/**
	 * パスワード
	 */
	private String _password = null;
	/**
	 * パスワードを取得する
	 * @return パスワード
	 */
	public String getPassword() {
		return _password;
	}
	/**
	 * パスワードをセットする
	 * @param password パスワード
	 */
	public void setPassword(String password ) {
		_password = password;
	}
}
