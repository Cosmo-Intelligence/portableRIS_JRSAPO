package ris.portable.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.Transliterator;

/// <summary>
/// KanaRomaConvert の概要の説明です。
/// </summary>
public class KanaRomaConvert {

	public static Log logger = LogFactory.getLog(KanaRomaConvert.class);

	// カナ→ローマ字変換用ファイル名
	//private static final String fileName = Const.KANAROMATEXT;
	// 最大文字数
	private static final int maxCount = 300;
	// "ッ"の変換文字列
	private static final String ltuConst = "LTU";

	// 変換用ファイルの読込成否
	private boolean fileReadFlg;
	// カナ変換リスト
	private String[] kanaList = new String[maxCount];
	// ローマ字変換リスト
	private String[] romaList = new String[maxCount];
	// 撥音
	private int hatuon = 0;
	// 促音
	private int sokuon = 0;
	// 長音
	private int chouon = 0;
	// 姓名の最後の「ｳ」
	private int uText  = 0;
	// 大文字・小文字の指定
	private int caps   = 0;

	public KanaRomaConvert(String filepath)
	{
		//
		// TODO: コンストラクタ ロジックをここに追加してください。
		//

		// 変換用ファイルの読込を行う
		fileReadFlg = ReadKanaRomaList(filepath);
	}

	/// <summary>
	/// カナ→ローマ字変換用関数
	/// </summary>
	/// <param name="convertKana">カナ文字列</param>
	/// <returns>ローマ字文字列</returns>
	public String GetRomaConvert(String convertKana)
	{
		// 変換後のローマ字入力用
		String romaStr = "";

		logger.debug("begin");
		try
		{
			// 変換用ファイル読込に成功した場合のみ
			if (fileReadFlg)
			{
				// カナ→ローマ字の変換を行う
				// 半角カナ形式に変換する
				Transliterator transliterator = Transliterator.getInstance("Fullwidth-Halfwidth");
				String kanaStr = transliterator.transliterate(convertKana.trim());
				// 大文字に統一する
				kanaStr = kanaStr.toUpperCase();

				// 初期化
				boolean ltuFlg			= false;
				String getKana		= "";
				String getRoma		= "";
				String beforeKana	= "";
				String afterKana	= "";
				String ltuStr		= "";

				//「ｯ」の単体の変換文字列を探す。
				for (int listCount = 0; listCount < maxCount; listCount++)
				{
					if (kanaList[listCount].equals("ｯ"))
					{
						ltuStr = romaList[listCount];
						break;
					}
				}
				// 見つからなかった場合はconst文字を使う
				if (ltuStr.trim().equals(""))
				{
					ltuStr = ltuConst;
				}

				int kanaCount = 0;
				//１文字づつ処理
				while (kanaCount < kanaStr.length())
				{
					getKana = kanaStr.substring(kanaCount, kanaCount + 1);
					if (kanaCount < kanaStr.length() - 1)
					{
						afterKana = kanaStr.substring(kanaCount + 1, kanaCount + 1 + 1);
					}
					else
					{
						afterKana = "";
					}
					if (kanaCount == 0)
					{
						beforeKana = "";
					}
					else
					{
						beforeKana = kanaStr.substring(kanaCount - 1, kanaCount);
					}

					//「ｯ」は次で処理するので飛ばす
					if (getKana.equals("ｯ"))
					{
						if (ltuFlg)
						{
							romaStr = romaStr + ltuStr;
						}
						ltuFlg = true;
						kanaCount++;

						continue;
					}
					//「ﾞ」、「ﾟ」は結合して処理する
					if ((afterKana.equals("ﾞ")) ||
						(afterKana.equals("ﾟ")))
					{
						getKana = getKana + afterKana;
						kanaCount++;
						// 更にその次を見る
						if (kanaCount < kanaStr.length() - 1)
						{
							afterKana = kanaStr.substring(kanaCount + 1, kanaCount + 1 + 1);
						}
						else
						{
							afterKana = "";
						}
					}
					//「ｧ」「ｨ」「ｩ」「ｪ」「ｫ」
					//「ｬ」「ｨ」「ｭ」「ｪ」「ｮ」
					//は結合して処理する
					if ((afterKana.equals("ｧ")) ||
						(afterKana.equals("ｨ")) ||
						(afterKana.equals("ｩ")) ||
						(afterKana.equals("ｪ")) ||
						(afterKana.equals("ｫ")) ||
						(afterKana.equals("ｬ")) ||
						(afterKana.equals("ｭ")) ||
						(afterKana.equals("ｮ")))
					{
						// 結合した文字が変換リストに存在しない場合はそのまま処理
						getRoma = "";
						for (int listCount = 0; listCount < maxCount; listCount++)
						{
							if (kanaList[listCount].equals(getKana + afterKana))
							{
								getRoma = romaList[listCount];
								break;
							}
						}
						if (!getRoma.equals(""))
						{
							getKana = getKana + afterKana;
							kanaCount++;
							// 更にその次を見る
							if (kanaCount < kanaStr.length() - 1)
							{
								afterKana = kanaStr.substring(kanaCount + 1, kanaCount + 1 + 1);
							}
							else
							{
								afterKana = "";
							}
						}
					}
					//「ｳ」は姓、名の最後であれば飛ばす
					if (getKana.equals("ｳ"))
					{
						if (afterKana.trim().equals(""))
						{
							if (uText == 1)
							{
								getKana = afterKana;
								kanaCount++;
								// 更にその次を見る
								if (kanaCount < kanaStr.length() - 1)
								{
									afterKana = kanaStr.substring(kanaCount + 1, 1);
								}
								else
								{
									afterKana = "";
								}
								//ループを抜ける
								continue;
							}
						}
					}
					//空白は無条件に299番目とする
					if (getKana.equals(" "))
					{
						if (ltuFlg)
						{
							getRoma = ltuStr + romaList[299];
							ltuFlg = false;
						}
						else
						{
							getRoma = romaList[299];
						}
					}
					else
					{
						getRoma = "";
						for (int listCount = 0; listCount < maxCount; listCount++)
						{
							if (kanaList[listCount].equals(getKana))
							{
								getRoma = romaList[listCount];
								break;
							}
						}
						if (getRoma.equals(""))
						{
							if (ltuFlg)
							{
								getRoma = ltuStr;
								ltuFlg = false;
							}
							getRoma = getRoma + " ";
						}
						else if (getRoma.equals(" "))
						{
							if (ltuFlg)
							{
								getRoma = ltuStr + " ";
								ltuFlg = false;
							}
						}
						else
						{
							//撥音－前に「ﾝ」がある場合
							if (beforeKana.equals("ﾝ"))
							{
								if ((getKana.equals("ｱ")) ||
									(getKana.equals("ｲ")) ||
									(getKana.equals("ｳ")) ||
									(getKana.equals("ｴ")) ||
									(getKana.equals("ｵ")))
								{
									if (hatuon == 0)
									{
										//訓令式－nのまま
										//getRoma = getRoma;
									}
									else if (hatuon == 2)
									{
										//nn
										getRoma = "N" + getRoma;
									}
								}
								if ((getRoma.substring(0,1).equals("M")) ||
									(getRoma.substring(0,1).equals("B")) ||
									(getRoma.substring(0,1).equals("P")))
								{
									if (hatuon == 1)
									{
										//ﾍﾎﾞﾝ式－m,b,pの前にnの変りにmをおく
										romaStr = romaStr.substring(0, romaStr.length() - 1);
										getRoma = "M" + getRoma;
									}
								}
							}

							//促音－「ｯ」
							if (ltuFlg)
							{
								// 続く文字が
								//「A」「I」「U」「E」「O」「N」
								// の場合は固定文字
								if ((getRoma.substring(0, 1).equals("A")) ||
									(getRoma.substring(0, 1).equals("I")) ||
									(getRoma.substring(0, 1).equals("U")) ||
									(getRoma.substring(0, 1).equals("E")) ||
									(getRoma.substring(0, 1).equals("O")) ||
									(getRoma.substring(0, 1).equals("N")))
								{
									getRoma = ltuStr + getRoma;
								}
								else
								{
									if (sokuon == 0)
									{
										//訓令式－子音を重ねる
										getRoma = getRoma.substring(0, 1) + getRoma;
									}
									else if (sokuon == 1)
									{
										//ﾍﾎﾞﾝ式－chi,cha,chu,choに限り前にtをおく
										if ((getRoma.equals("CHI")) ||
											(getRoma.equals("CHA")) ||
											(getRoma.equals("CHU")) ||
											(getRoma.equals("CHO")))
										{
											getRoma = "T" + getRoma;
										}
										else
										{
											//ﾍﾎﾞﾝ式－子音を重ねる
											getRoma = getRoma.substring(0, 1) + getRoma;
										}
									}
								}
								ltuFlg = false;
							}

							//長音－「-」
							if (getRoma.equals("-"))
							{
								if (chouon == 0)
								{
									//そのまま
									//getRoma = getRoma;
								}
								else if (chouon == 1)
								{
									//母音字を並べる
									if (romaStr.length() > 0)
									{
										getRoma = romaStr.substring(romaStr.length() - 1, romaStr.length());
									}
									else
									{
										//getRoma = getRoma;
									}
								}
							}
						}
					}

					//１文字づつを結合
					romaStr = romaStr + getRoma;
					//次へ
					kanaCount++;
				}

				//最後に「ｯ」が残っている場合
				if (ltuFlg)
				{
					romaStr = romaStr + ltuStr;
				}

				// 大文字・小文字のチェック
				if (caps == 1)
				{
					// 大文字
					romaStr = romaStr.toUpperCase();
				}
				/*
				else if (caps == 2)
				{
					// 1文字目のみ大文字 str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
					romaStr = romaStr.substring(0, 1).toUpperCase()   Strings.StrConv(romaStr, VbStrConv.ProperCase, 0);
				}
				*/
				else
				{
					// 小文字
					romaStr = romaStr.toLowerCase();
				}
			}
		}
		catch(Exception ex)
		{
			logger.fatal(ex);
		}
		finally
		{
			logger.debug("end");
		}
		// 変換文字列を返す
		return romaStr;
	}

	/// <summary>
	/// カナ→ローマ字変換用ファイル読込
	/// </summary>
	/// <returns>true=成功 false=失敗</returns>
	private boolean ReadKanaRomaList(String filepath)
	{
		boolean returnFlg = false;
		logger.debug("begin");
		FileInputStream in = null;
		BufferedReader br = null;

		try
		{
			// ファイルPath
			//String filePath = fileName;

			//String path = new File(".").getAbsoluteFile().getParent();
	        //System.out.println(path);

			File file = new File(filepath);
			//ファイルが存在しない場合は処理しない
			//if (!file.exists())
			//{
			//	String msg = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.notfoundKanaRomaList_MessageString);
			//	logger.fatal(msg + "\r\n" + filePath);
			//	return false;
			//}

			// ファイル読込
			in = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			// カナ行・ローマ字行のカウント
			int kanaCount = 0;
			int romaCount = 0;

            String line;

			// 1行ずつ読み込む
            while ((line = br.readLine()) != null) {
				// 列の1文字目はカナ・ローマ字の分類
				String lineTitle = line.substring(0, 1).toUpperCase();
				// 実データ部分以外の項目は飛ばす
				if (lineTitle.equals("/")) continue;
				// 実データ部分
				String lineData	= line.substring(3, line.length());
				String[] splitList = lineData.split("，");

				// 1文字ずつ読み込む
				for (int i = 0; i < splitList.length; i++)
				{
					String readStr = splitList[i].trim();
					// 先頭と最後に「"」があるならばリムーブ
					if( readStr.length() >= 2 )
					{
						if( readStr.substring(0, 1).equals("\"") && readStr.substring(readStr.length() - 1, readStr.length()).equals("\""))
						{
							readStr = readStr.substring(1, readStr.length() - 1);
						}
					}
					// フラグ行の場合
					if (lineTitle.equals("F"))
					{
						//logger.debug(i + ":" + readStr);
						switch(i)
						{
                            // 撥音
							case 0:	hatuon = Integer.parseInt(readStr);	break;
							// 促音
							case 1:	sokuon = Integer.parseInt(readStr);	break;
							// 長音
							case 2:	chouon = Integer.parseInt(readStr);	break;
							// 姓名の最後の「ｳ」
							case 3:	uText  = Integer.parseInt(readStr);	break;
							// 大文字・小文字の指定
							case 4:	caps   = Integer.parseInt(readStr);	break;
						}
					}
					// カナ行の場合
					if (lineTitle.equals("K"))
					{
						kanaList[kanaCount] = readStr.toUpperCase();
						kanaCount++;
					}
					// ローマ字行の場合
					if (lineTitle.equals("R"))
					{
						romaList[romaCount] = readStr.toUpperCase();
						romaCount++;
					}
				}
			}
			// 読込データが足りない場合は失敗
			if (!((kanaCount == 300) && (romaCount == 300)))
			{
				returnFlg = false;
			}
			// ファイル読込成功
			returnFlg = true;
		}
		catch(Exception ex)
		{
			logger.fatal(ex);
			// ファイル読込失敗
			returnFlg = false;
		}
		finally
		{
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
				}
			}

			logger.debug("end");
		}
		return returnFlg;
	}
}