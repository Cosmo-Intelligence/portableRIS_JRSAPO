package ris.portable.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	private final Socket socket;

	private DataOutputStream output;

	private BufferedReader input;

	/**
	 * ストリームソケットを作成し、指定されたホスト上の指定されたポート番号に接続します。
	 *
	 * @param hostName 通信相手のホスト名、もしくはIPアドレス
	 * @param port 通信相手のポート番号
	 * @throws UnknownHostException ホストの IP アドレスを決定できなかった場合
	 * @throws IOException ソケットの生成中に入出力エラーが発生した場合
	 */
	public SocketClient(String hostName, int port) throws UnknownHostException,
			IOException {
		super();
		socket = new Socket(hostName, port);

		output = new DataOutputStream(socket.getOutputStream());
		input = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * サーバーソケットとメッセージの送受信を行います。
	 *
	 * @param sendMessage 送信メッセージ
	 * @param timeout ソケット通信のタイムアウト時間（millisec指定）
	 * @return サーバーから受信したメッセージ
	 * @throws IOException 接続時にエラーが発生した場合
	 */
	public String sendMessage(String message, int timeout) throws IOException {
		try {
			socket.setSoTimeout(timeout);

			// データの送信
			output.write(message.getBytes("US-ASCII"));

			// データの受信（データが来るまでブロック）
			StringBuilder builder = new StringBuilder();
			char[] buf = new char[1024];
			int readNum = input.read(buf);
			builder.append(buf, 0, readNum);

			return builder.toString();
		} finally {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}

	/**
	 * サーバーソケットとメッセージの送受信をマルチバイト文字で行います。
	 *
	 * @param sendMessage 送信メッセージ
	 * @param timeout ソケット通信のタイムアウト時間（millisec指定）
	 * @return サーバーから受信したメッセージ
	 * @throws IOException 接続時にエラーが発生した場合
	 */
	public String sendMultibyteMessage(String message, int timeout) throws IOException {
		try {
			socket.setSoTimeout(timeout);

			// データの送信（そのままStringで送信）
			output.writeBytes(message);

			// データの受信（データが来るまでブロック）
			String line;
			if ((line = input.readLine()) != null) {
				return line;
			} else {
				return "";
			}
		} finally {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
}
