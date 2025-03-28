package ris.portable.model.dto;

import org.apache.commons.lang3.math.NumberUtils;


public class SystemDto extends BaseDto {

	private String sysdate;
	private int kanjaidlen = 10;
	private int renrakumemolen = 250;
	private boolean use_mpps = true;
	private boolean use_portable_kbn = false;
	// 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
	private boolean use_receipt_mwm = false;
	// 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応

	// 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
	//検査進捗ボタンフラグ(jsに渡すフラグ)
	private boolean statuscolor_flg = true;
	// 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

	// LoginSystemResource
	// 2021.09.01 Add Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応
	private boolean loginesckey_flg = true;
	// 2021.09.01 Add Nishihara@COSMO end ログイン画面のESCキーフォーカス除外有無対応

	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public int getKanjaidlen() {
		return kanjaidlen;
	}

	public void setKanjaidlen(String kanjaidlen) {

		if (NumberUtils.isNumber(kanjaidlen)) {
			this.kanjaidlen = Integer.parseInt(kanjaidlen);
		}
	}

	public int getRenrakumemolen() {
		return renrakumemolen;
	}

	public void setRenrakumemolen(String renrakumemolen) {

		if (NumberUtils.isNumber(renrakumemolen)) {
			this.renrakumemolen = Integer.parseInt(renrakumemolen) / 2;
		}
	}

	public boolean getUse_mpps() {
		return this.use_mpps;
	}

	public void setUse_mpps(boolean value) {
		this.use_mpps = value;
	}

	public boolean getUse_portable_kbn() {
		return this.use_portable_kbn;
	}

	public void setUse_portable_kbn(boolean value) {
		this.use_portable_kbn = value;
	}

	// 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
	public boolean getUse_receipt_mwm() {
		return this.use_receipt_mwm;
	}

	public void setUse_receipt_mwm(boolean value) {
		this.use_receipt_mwm = value;
	}
	// 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応

	// 2020.08.26 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
	public boolean getStatuscolor_flg() {
		return this.statuscolor_flg;
	}

	public void setStatuscolor_flg(boolean value) {
		this.statuscolor_flg = value;
	}
	// 2020.08.26 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応


	// 2021.09.01 Add Nishihara@COSMO start ログイン画面のESCキーフォーカス除外有無対応
	// LoginSystemResource
	public boolean getLoginesckey_flg() {
		return this.loginesckey_flg;
	}

	public void setLoginesckey_flg(boolean value) {
		this.loginesckey_flg = value;
	}
	// 2021.09.01 Add Nishihara@COSMO end ログイン画面のESCキーフォーカス除外有無対応
}
