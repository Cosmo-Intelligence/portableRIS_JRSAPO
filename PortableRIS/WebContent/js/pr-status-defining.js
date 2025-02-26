/**
 * ポータブルRISオーダ進捗定義
 */
var PrStatusDefining = (function() {
	/**
	 * オーダ進捗のコード値定義
	 */
	var _status = {
		/**
		 * 未受
		 * @return {Number} 未受のコード値
		 */
		unregistered: function() {return 0;},
		/**
		 * 遅刻
		 * @return {Number} 遅刻のコード値
		 */
		isLate: function() {return 1;},
		/**
		 * 呼出
		 * @return {Number} 呼出のコード値
		 */
		isCalling: function() {return 2;},
		/**
		 * 受済
		 * @return {Number} 受済のコード値
		 */
		isRegistered: function() {return 10;},
		/**
		 * 検中
		 * @return {Number} 検中のコード値
		 */
		inOperation: function() {return 20;},
		/**
		 * 保留
		 * @return {Number} 保留のコード値
		 */
		rest: function() {return 21;},
		/**
		 * 再呼
		 * @return {Number} 再呼のコード値
		 */
		recalling: function() {return 24;},
		/**
		 * 再受
		 * @return {Number} 再受のコード値
		 */
		reregistered: function() {return 25;},
		/**
		 * 検済
		 * @return {Number} 検済のコード値
		 */
		isFinished: function() {return 90;},
		/**
		 * 中止
		 * @return {Number} 中止のコード値
		 */
		stop: function() {return 91;},
		/**
		 * 削除
		 * @return {Number} 削除のコード値
		 */
		delete: function() {return -9;}
	};

	return {
		/**
		 * 受付出来るかを判定する
		 * @param {Number} status オーダ進捗のコード値
		 * @return {Boolean} true:出来る / false:出来ない
		 */
		canRegister: function(status) {
			if (status < _status.isRegistered()) {
				// オーダ進捗が受済未満である場合
				return true;
			}
			return false;
		}
	};
})();
