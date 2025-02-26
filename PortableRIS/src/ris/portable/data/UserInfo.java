package ris.portable.data;

/**
 * ユーザ情報を保持するデータクラス
 */
public class UserInfo {
	/**
	 * ユーザID
	 */
	private String _userId = null;
	/**
	 * ユーザIDを取得する
	 * @return ユーザID
	 */
	public String getUserId() {
		return _userId;
	}
	/**
	 * ユーザIDをセットする
	 * @param userId ユーザID
	 */
	public void setUserId(String userId ) {
		_userId = userId;
	}

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
