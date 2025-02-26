package ris.portable.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ris.portable.parameter.ParameterReplacerOrderInfo;
import ris.portable.parameter.ParameterReplacerUserInfo;

/**
 * パラメータを置換するクラス
 */
public class ParameterReplacer {
	/**
	 * パラメータを表す正規表現
	 */
	final String PARAMETER_REGULAR_EXPRESSION = "%[^%]+%";

	/**
	 * 置換対象文字列
	 */
	private String _target = null;
	/**
	 * パラメータを置換するクラスで使用するユーザ情報クラス
	 */
	private ParameterReplacerUserInfo _parameterReplacerUserInfo = null;
	/**
	 * パラメータを置換するクラスで使用するオーダ情報パラメータクラス
	 */
	private ParameterReplacerOrderInfo _parameterReplacerOrderInfo = null;
	/**
	 * パラメータと置換後の値紐付け
	 */
	private HashMap<String, String> _replacingHashMap = new HashMap<String, String>();
	/**
	 * 置換対象文字列から抽出したパラメータ
	 */
	private ArrayList<String> _parameterArrayList = new ArrayList<String>();
	/**
	 * パラメータを置換した文字列
	 */
	private String _replaced = null;
	/**
	 * 未定義パラメータ
	 */
	private ArrayList<String> _undefinedArrayList = new ArrayList<String>();

	/**
	 * コンストラクタ
	 * @param target 置換対象文字列
	 * @param parameterReplacerUserInfo パラメータを置換するクラスで使用するユーザ情報クラス
	 * @param parameterReplacerOrderInfo パラメータを置換するクラスで使用するオーダ情報パラメータクラス
	 */
	public ParameterReplacer(String target, ParameterReplacerUserInfo parameterReplacerUserInfo, ParameterReplacerOrderInfo parameterReplacerOrderInfo) {
		_target = target;
		_parameterReplacerUserInfo = parameterReplacerUserInfo;
		_parameterReplacerOrderInfo = parameterReplacerOrderInfo;
	}

	/**
	 * パラメータと置換後の値を紐付ける
	 */
	private void map() {
		// パラメータ「ユーザID」
		_replacingHashMap.put("%USER_ID%", _parameterReplacerUserInfo.getUserId());
		// パラメータ「パスワード」
		_replacingHashMap.put("%USER_PW%", _parameterReplacerUserInfo.getPassword());
		// パラメータ「アクセッション番号」
		_replacingHashMap.put("%AC_NO%", _parameterReplacerOrderInfo.getAccessionNo());
	}
	/**
	 * パラメータ定義を解析する
	 */
	private void parse() {
		// 正規表現パターンを定義する
		Pattern pattern = Pattern.compile(PARAMETER_REGULAR_EXPRESSION);
		// 正規表現エンジンを作成する
		Matcher matcher = pattern.matcher(_target);
		while (matcher.find()) {
			// 一致した文字列
			String matched = matcher.group();
			if (_parameterArrayList.contains(matched)) {
				// 置換対象文字列から抽出したパラメータに存在する場合
				// 処理をスキップする
				continue;
			} else {
				// 置換対象文字列から抽出したパラメータに存在しない場合
				// 置換対象文字列から抽出したパラメータに追加する
				_parameterArrayList.add(matched);
			}
		}
	}
	/**
	 * 置換対象文字列から抽出したパラメータを紐付く置換後の値で置換する
	 */
	private void replace() {
		_replaced = _target;
		for (String parameter : _parameterArrayList) {
			if (_replacingHashMap.containsKey(parameter)) {
				// パラメータと置換後の値紐付けが存在する場合
				// パラメータに紐付く置換後の値で置換する
				_replaced = _replaced.replace(parameter, _replacingHashMap.get(parameter));
			} else {
				// パラメータと置換後の値紐付けが存在しない場合
				// 未定義パラメータに追加する
				_undefinedArrayList.add(parameter);
			}
		}
	}

	/**
	 * パラメータを置換した文字列を取得する
	 * @return パラメータを置換した文字列
	 */
	public String getReplacedString() {
		// パラメータと置換後の値を紐付ける
		map();
		// パラメータ定義を解析する
		parse();
		// 置換対象文字列から抽出したパラメータを紐付く置換後の値で置換する
		replace();
		// パラメータを置換した文字列を返す
		return _replaced;
	}
	/**
	 * 未定義パラメータが存在するかを判定する
	 * @return true:存在する / false:存在しない
	 */
	public boolean ExistsUndefinedParameter() {
		boolean isEmpty = _undefinedArrayList.isEmpty();
		return !isEmpty;
	}
	/**
	 * カンマ区切りの文字列にした未定義パラメータを取得する
	 * @return カンマ区切りの文字列にした未定義パラメータ
	 */
	public String getUndefinedParameter() {
		if (_undefinedArrayList.isEmpty()) {
			// 未定義パラメータが空である場合
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(_undefinedArrayList.get(0));
		// 2個目以降は先頭にカンマを追加する
		for (int index = 1; index < _undefinedArrayList.size(); index++) {
			stringBuilder.append(String.format(",%s", _undefinedArrayList.get(index)));
		}
		String joined = stringBuilder.toString();
		return joined;
	}
}
