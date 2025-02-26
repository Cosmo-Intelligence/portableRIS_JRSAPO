package ris.portable.parameter;

/**
 * パラメータを置換するクラスで使用するオーダ情報パラメータクラス
 */
public class ParameterReplacerOrderInfo {
	/**
	 * アクセッション番号
	 */
	private String _accessionNo = null;
	/**
	 * アクセッション番号を取得する
	 * @return アクセッション番号
	 */
	public String getAccessionNo() {
		return _accessionNo;
	}
	/**
	 * アクセッション番号をセットする
	 * @param accessionNo アクセッション番号
	 */
	public void setAccessionNo(String accessionNo) {
		_accessionNo = accessionNo;
	}
}
