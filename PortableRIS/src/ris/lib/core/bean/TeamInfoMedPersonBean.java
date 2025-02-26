package ris.lib.core.bean;

import ris.lib.core.Configuration;
import ris.lib.core.util.MessageParameter;

/// <summary>
/// 担当者クラス
///
/// Copyright (C) 2009, Yokogawa Electric Corpration
/// (Rev.)		  (Date)		  (ID / NAME)			  (Comment)
/// V2.01.00    extends 2011.07.29    extends 999999 / NSK_T.Koudate extends NML-CAT2-004
///
/// </summary>
public class TeamInfoMedPersonBean
{
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
	}

	// 定数
	/// <summary>
	/// 担当者カラムの最少数
	/// </summary>
	public  final Integer MEDPERSON_MIN_VALUE = 1;
	/// <summary>
	/// 担当者カラムの最大数
	/// </summary>
	public  final Integer MEDPERSON_MAX_VALUE = 10;


	// フィールド
	/// <summary>
	/// チーム情報クラス
	/// </summary>
	private TeamInfoBean teamInfoBean;
	/// <summary>
	/// 現在対象MedPersonカラム
	/// </summary>
	private Integer currentMedPersonIndex;

	/// <summary>
	/// コンストラクタ
	/// </summary>
	/// <param name=="team">チーム情報クラス</param>
	public TeamInfoMedPersonBean(TeamInfoBean team)
	{
		this.teamInfoBean = team;
		currentMedPersonIndex = MEDPERSON_MIN_VALUE;
	}

	// プロパティ

	/// <summary>
	/// 参照しているチーム情報クラス
	/// </summary>
	public TeamInfoBean TeamInfo;

	/// <summary>
	/// 現在対象MedPersonカラム
	/// </summary>
	/**
	 * @return teamInfo
	 */
	public TeamInfoBean getTeamInfo() {
		return TeamInfo;
	}

	public Integer CurrentMedPersonIndex;

	/// <summary>
	/// ID
	/// </summary>
	/**
	 * @return currentMedPersonIndex
	 */
	public Integer getCurrentMedPersonIndex() {
		return CurrentMedPersonIndex;
	}

	/**
	 * @param currentMedPersonIndex セットする currentMedPersonIndex
	 * @throws Exception
	 */
	public void setCurrentMedPersonIndex(Integer currentMedPersonIndex) throws Exception {
		if ((currentMedPersonIndex < MEDPERSON_MIN_VALUE) ||
				(currentMedPersonIndex > MEDPERSON_MAX_VALUE))
			{
				throw new Exception(Configuration.GetInstance().GetCoreController().
						GetMessageStringImpl(MessageParameter.setIndexMedPersonOutOfRangeException_MessageString) + "value:" + currentMedPersonIndex);
			}
		CurrentMedPersonIndex = currentMedPersonIndex;
	}

	public String ID;

	/// <summary>
	/// 名称
	/// </summary>
	/**
	 * @return iD
	 */
	public String getID() {
		if (this.teamInfoBean == null)
		{
			return "";
		}
		else
		if (currentMedPersonIndex == 1)
		{
			return this.teamInfoBean.GetMedPersonID01();
		}
		else
		if (currentMedPersonIndex == 2)
		{
			return this.teamInfoBean.GetMedPersonID02();
		}
		else
		if (currentMedPersonIndex == 3)
		{
			return this.teamInfoBean.GetMedPersonID03();
		}
		else
		if (currentMedPersonIndex == 4)
		{
			return this.teamInfoBean.GetMedPersonID04();
		}
		else
		if (currentMedPersonIndex == 5)
		{
			return this.teamInfoBean.GetMedPersonID05();
		}
		else
		if (currentMedPersonIndex == 6)
		{
			return this.teamInfoBean.GetMedPersonID06();
		}
		else
		if (currentMedPersonIndex == 7)
		{
			return this.teamInfoBean.GetMedPersonID07();
		}
		else
		if (currentMedPersonIndex == 8)
		{
			return this.teamInfoBean.GetMedPersonID08();
		}
		else
		if (currentMedPersonIndex == 9)
		{
			return this.teamInfoBean.GetMedPersonID09();
		}
		else
		if (currentMedPersonIndex == 10)
		{
			return this.teamInfoBean.GetMedPersonID10();
		}
		return "";
	}

	/**
	 * @param iD セットする iD
	 */
	public void setID(String iD) {
		if (this.teamInfoBean == null)
		{
			return;
		}
		else
		if (currentMedPersonIndex == 1)
		{
			this.teamInfoBean.SetMedPersonID01(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 2)
		{
			this.teamInfoBean.SetMedPersonID02(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 3)
		{
			this.teamInfoBean.SetMedPersonID03(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 4)
		{
			this.teamInfoBean.SetMedPersonID04(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 5)
		{
			this.teamInfoBean.SetMedPersonID05(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 6)
		{
			this.teamInfoBean.SetMedPersonID06(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 7)
		{
			this.teamInfoBean.SetMedPersonID07(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 8)
		{
			this.teamInfoBean.SetMedPersonID08(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 9)
		{
			this.teamInfoBean.SetMedPersonID09(iD);
			return;
		}
		else
		if (currentMedPersonIndex == 10)
		{
			this.teamInfoBean.SetMedPersonID10(iD);
			return;
		}
	}

	public String Name;

	/// <summary>
	/// 職種区分
	/// </summary>
	/**
	 * @return name
	 */
	public String getName() {
		if (this.teamInfoBean == null)
		{
			return "";
		}
		else
		if (currentMedPersonIndex == 1)
		{
			return this.teamInfoBean.GetMedPersonName01();
		}
		else
		if (currentMedPersonIndex == 2)
		{
			return this.teamInfoBean.GetMedPersonName02();
		}
		else
		if (currentMedPersonIndex == 3)
		{
			return this.teamInfoBean.GetMedPersonName03();
		}
		else
		if (currentMedPersonIndex == 4)
		{
			return this.teamInfoBean.GetMedPersonName04();
		}
		else
		if (currentMedPersonIndex == 5)
		{
			return this.teamInfoBean.GetMedPersonName05();
		}
		else
		if (currentMedPersonIndex == 6)
		{
			return this.teamInfoBean.GetMedPersonName06();
		}
		else
		if (currentMedPersonIndex == 7)
		{
			return this.teamInfoBean.GetMedPersonName07();
		}
		else
		if (currentMedPersonIndex == 8)
		{
			return this.teamInfoBean.GetMedPersonName08();
		}
		else
		if (currentMedPersonIndex == 9)
		{
			return this.teamInfoBean.GetMedPersonName09();
		}
		else
		if (currentMedPersonIndex == 10)
		{
			return this.teamInfoBean.GetMedPersonName10();
		}
		return "";
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		if (this.teamInfoBean == null)
		{
			return;
		}
		else
		if (currentMedPersonIndex == 1)
		{
			this.teamInfoBean.SetMedPersonName01(name);
			return;
		}
		else
		if (currentMedPersonIndex == 2)
		{
			this.teamInfoBean.SetMedPersonName02(name);
			return;
		}
		else
		if (currentMedPersonIndex == 3)
		{
			this.teamInfoBean.SetMedPersonName03(name);
			return;
		}
		else
		if (currentMedPersonIndex == 4)
		{
			this.teamInfoBean.SetMedPersonName04(name);
			return;
		}
		else
		if (currentMedPersonIndex == 5)
		{
			this.teamInfoBean.SetMedPersonName05(name);
			return;
		}
		else
		if (currentMedPersonIndex == 6)
		{
			this.teamInfoBean.SetMedPersonName06(name);
			return;
		}
		else
		if (currentMedPersonIndex == 7)
		{
			this.teamInfoBean.SetMedPersonName07(name);
			return;
		}
		else
		if (currentMedPersonIndex == 8)
		{
			this.teamInfoBean.SetMedPersonName08(name);
			return;
		}
		else
		if (currentMedPersonIndex == 9)
		{
			this.teamInfoBean.SetMedPersonName09(name);
			return;
		}
		else
		if (currentMedPersonIndex == 10)
		{
			this.teamInfoBean.SetMedPersonName10(name);
			return;
		}
	}

	public String SyokuinKbn;

	/**
	 * @return syokuinKbn
	 */
	public String getSyokuinKbn() {
		if (this.teamInfoBean == null)
		{
			return "";
		}
		else
		if (currentMedPersonIndex == 1)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn01();
		}
		else
		if (currentMedPersonIndex == 2)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn02();
		}
		else
		if (currentMedPersonIndex == 3)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn03();
		}
		else
		if (currentMedPersonIndex == 4)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn04();
		}
		else
		if (currentMedPersonIndex == 5)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn05();
		}
		else
		if (currentMedPersonIndex == 6)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn06();
		}
		else
		if (currentMedPersonIndex == 7)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn07();
		}
		else
		if (currentMedPersonIndex == 8)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn08();
		}
		else
		if (currentMedPersonIndex == 9)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn09();
		}
		else
		if (currentMedPersonIndex == 10)
		{
			return this.teamInfoBean.GetMedPersonSyokuinKbn10();
		}
		return "";
	}

	/**
	 * @param syokuinKbn セットする syokuinKbn
	 */
	public void setSyokuinKbn(String syokuinKbn) {
		if (this.teamInfoBean == null)
		{
			return;
		}
		else
		if (currentMedPersonIndex == 1)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn01(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 2)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn02(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 3)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn03(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 4)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn04(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 5)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn05(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 6)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn06(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 7)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn07(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 8)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn08(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 9)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn09(syokuinKbn);
			return;
		}
		else
		if (currentMedPersonIndex == 10)
		{
			this.teamInfoBean.SetMedPersonSyokuinKbn10(syokuinKbn);
			return;
		}
	}

	// メソッド
	/// <summary>
	/// CurrentMedPersonIndex以外のプロパティをクリアする
	/// </summary>
	public void clear()
	{
		ID = "";
		Name = "";
		SyokuinKbn = "";
	}

	/// <summary>
	/// シャローコピー
	/// </summary>
	/// <returns>担当者クラスのクローン</returns>
	public TeamInfoMedPersonBean thisClone() throws CloneNotSupportedException
	{
		return (TeamInfoMedPersonBean)clone();
	}


}
