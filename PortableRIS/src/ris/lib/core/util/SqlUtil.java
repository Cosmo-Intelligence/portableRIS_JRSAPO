package ris.lib.core.util;

/// <summary>
/// SQL文字列作成時に文字のエスケープ等を行なうUTILITYクラス
///
///	(Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V1.00.00	: 2009.02.08	: 112478 / A.Kobayashi	: original
///
/// </summary>
public final class SqlUtil
{
	/// <summary>
	/// SQL内で使用する文字列をエスケープする関数
	/// 「'」を「''」に変換する
	/// </summary>
	/// <param name="before">エスケープ前の文字列</param>
	/// <returns>エスケープ後の文字列</returns>
	public static String EscapeInsertSQL(String before) {
		// parameters
		int curPosInt = 0;
		String after = "";

		if (before != null) {

			// 元の文字列をコピー
			after = before;

			StringBuilder sb = new StringBuilder(after);

			// エスケープ対象文字（'）を探す
			curPosInt = sb.indexOf("'", curPosInt);

			while(curPosInt >= 0) {

				// エスケープ対象文字の前に(')を入れる
				sb = sb.insert(curPosInt , "'");

				// ポインタを2文字分進める
				curPosInt += 2;
				if (curPosInt > sb.length()) {
					break;
				}

				// 次のエスケープ対象文字（'）を探す
				curPosInt = sb.indexOf( "'", curPosInt );
			}

			after = sb.toString();
		}

		return after;
	}

	/// <summary>
	/// SQL内で使用する文字列をエスケープする関数
	/// 「'」を「''」に、「*」を「%」に変換する
	/// </summary>
	/// <param name="before">エスケープ前の文字列</param>
	/// <returns>エスケープ後の文字列</returns>
	public static String EscapeSelectSQL(String before) {
		// parameters
		String after = "";

		if (before != null) {
			// 元の文字列をコピー
			after = before;

			// 「'」を「''」に変換
			after = EscapeInsertSQL(before);
		}

		//// 「*」を「%」に変換
		//return after.Replace( "*", "%" );

		return after;
	}
}
