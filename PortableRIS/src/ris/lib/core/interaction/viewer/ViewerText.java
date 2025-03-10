package ris.lib.core.interaction.viewer;

/*
/// <summary>
/// 検像送信種別の定義
/// </summary>
public enum SENDKIND //: int
{
	KenzouStart			= 103,		//検像開始
	KenzouEnd			= 104,		//検像終了
	KenzouCancel		= 105,		//検像キャンセル
	KenzouStop			= 106,		//検像ストップ
	KenzouReStart		= 109,		//検像開始(再送)
	KenzouGetMode		= 110,		//検像ﾓｰﾄﾞ問合せ
	KenzouGetMaisu		= 118,		//枚数問合わせ
	KenzouRetMaisu		= 119,		//枚数問合わせ
	UnKnown				= -1,
	None				= 0
}

// 2010.09.11 Add K.Shinohara Start
/// <summary>
/// 検像CODEの定義
/// </summary>
public enum CODEKIND
{
	CodeOK			= 0,	// 正常終了
	CodeNone		= 1,	// 検像すべき画像が0枚
	CodeAllDone		= 2,	// 全て検像済み(StudyUID有り)
	CodeStart		= 3,	// 検像中に検像開始を受けた
	CodeRetrySend	= 4,	// 再転送要求を受け再送を行った(StudyUID有り)
	CodeRetryNone	= 5,	// 再転送要求を受けたが検像済み画像無し
	CodeRetryError	= 6,	// 再転送要求を受けたがエラーとなった
	CodeAnyError	= 99	// 何らかのエラーが発生した
}
// 2010.09.11 Add K.Shinohara End

/// <summary>
/// MilkyText の概要の説明です。
/// </summary>
public class ViewerText
{
	internal Hashtable data;
	private SENDKIND sendKind;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	public ViewerText()
	{
		this.data = new Hashtable();
	}
	/// <summary>
	/// 値の追加
	/// </summary>
	/// <param name="key"></param>
	/// <param name="val"></param>
	internal void Add(String key,String val)
	{
		this.data.Add(key,val);
	}
	internal void Add(String key,int subKeyNo,String val)
	{
		this.data.Add(key+subKeyNo.toString(),val);
	}
	internal boolean IsAdd(String key)
	{
		boolean retBool = false;

		//追加済の場合
		if (this.data.Contains(key))
		{
			retBool = true;
		}

		return retBool;
	}
	/// <summary>
	/// コマンド種別
	/// </summary>
	public SENDKIND SendKind
	{
		get
		{
			return this.sendKind;
		}
		set
		{
			this.sendKind = value;
		}
	}
	/// <summary>
	/// 値の数
	/// </summary>

	internal int Count
	{
		get
		{
			return this.data.Count;
		}
	}
	/// <summary>
	/// 値
	/// </summary>
	internal String this[String key]
	{
		get
		{
			return key.SubString(0,4) + (String)data[key] + "\n";
		}
	}
	/// <summary>
	/// 送信テキスト
	/// </summary>
	public String Text
	{
		get
		{
			StringBuilder buffer = new StringBuilder();
			ArrayList keys = new ArrayList(this.data.Keys);
			ArrayList values = new ArrayList(this.data.Values);
			for(int i=0;i<keys.Count;i++)
			{
				if((keys[i] != null) && (values[i] != null))
					buffer.append(keys[i].toString().SubString(0,4)+values[i].toString()+"\n");
			}
			return buffer.toString();
		}
	}
	/// <summary>
	/// 送信テキストのパース
	/// </summary>
	/// <param name="sendKind">コマンド種別</param>
	/// <param name="src">送信テキスト</param>
	/// <returns></returns>
	public static ViewerText Parse(SENDKIND sendKind,String src)
	{
		ViewerText viewerText;
		String separatorKey = null;

		switch (sendKind)
		{
			case SENDKIND.KenzouRetMaisu:
				//枚数問合わせ
				viewerText = new ViewerKenzouGetMaisu();
				break;
			case SENDKIND.KenzouEnd:
			case SENDKIND.KenzouCancel:
			case SENDKIND.KenzouStop:
				//検像
				viewerText = new ViewerKenzouStart();
				break;
			default:
				//2007.5.18 メッセージを外部ファイル定義に変更
				String message = Configuration.GetInstance().GetCoreController().GetMessageStringImpl(MessageParameter.viewerSendTextException_MessageString);
				logger.fatal(message);
				throw new Exception(message);
		}
		int subKeyNo = -1;
		String[] parts = src.Split('\n');
		for (int i=0; i<parts.length(); i++)
		{
			if (parts[i].length() > 4)
			{
				String key = parts[i].SubString(0, 4);
				String val = parts[i].SubString(4);
				if (viewerText.IsAdd(key))
				{
					continue;
				}
				if ((separatorKey != null) && (key.Equals(separatorKey)))
					subKeyNo++;
				if (subKeyNo >= 0)
					viewerText.Add(key, subKeyNo, val);
				else
					viewerText.Add(key, val);
			}
		}
		return viewerText;
	}
	/// <summary>
	/// 送信テキストのパース
	/// </summary>
	/// <param name="src">IVMilky 受け渡し用構造体</param>
	/// <returns></returns>
	public static ViewerText Parse(IVMilkyIf.IVMilkySWInfo src)
	{
		return Parse((SENDKIND)src.m_Header.m_Kind,src.strData);
	}

	/// <summary>
	/// 受信テキストから特定のkeyを元に値を取得する
	/// </summary>
	/// <param name="key"></param>
	/// <returns></returns>
	public String GetText(String key)
	{
		String retStr = "";
		if (this.data.ContainsKey(key))
		{
			retStr = this.data[key].toString();
		}
		return retStr;
	}
}
*/