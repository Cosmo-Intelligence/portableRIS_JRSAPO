/**
 * ポータブルRISライブラリ
 */
var PrLibrary = (function() {
	return {
		/**
		 * ViewAir連携を使用するかを判定する
		 * @param {String} viewAirUrl ViewAirのURL
		 * @return {Boolean} true:ViewAir連携を使用する / false:ViewAir連携を使用しない
		 */
		usesViewAir: function(viewAirUrl) {
			if (viewAirUrl === null) {
				// ViewAirのURLがnullである場合
				return false;
			}
			if (viewAirUrl === "") {
				// ViewAirのURLが空である場合
				return false;
			}
			return true;
		},
		/**
		 * 新しくWindowを開く
		 * @param {String} url 遷移先URL
		 * @return {Object} WindowProxyオブジェクト
		 */
		openNewWindow: function(url) {
			var windowProxy = window.open(url, "_blank");
			return windowProxy;
		}
	};
})();
