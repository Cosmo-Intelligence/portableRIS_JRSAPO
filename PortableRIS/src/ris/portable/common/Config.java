package ris.portable.common;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import jakarta.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ris.portable.model.dto.LoginDto;
import ris.portable.util.Util;


public class Config implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(Config.class);

	private static String FILE_NAME = Const.CONFIG_FILE;

	private String appid = "";
	private String kensatypeid = "";
	private String syokuinkbn = "";
	private String byoutouid = "";
	private String statuscode = "";
	private String statuscode_miuke = "";
	// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
	private String statuscode_yobidasi = "";
	// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
	private String statuscode_ukezumi = "";
	private String statuscode_kentyuu = "";
	private String statuscode_horyuu = "";
	private String statuscode_kenzumi = "";
	private String groupcode = "";
	private String nyugaikbn = "";
	private String portablekbn = "";
	private String ordercomment_id = "";
	private String kensa_siji = "";
	private String rinsyou = "";
	private String remarks = "";
	private String handicapped = "";
	private String infection = "";
	private String contraindication = "";
	private String allergy = "";
	private String pregnancy = "";
	private String notes = "";
	private String examdata = "";
	private String buicomment = "";
	private Integer mwmtimeout = 5;   // デフォルト値「5秒」
	private Integer mppstimeout = 30; // デフォルト値「30秒」
	private String kanaromatext = "";
	private boolean useMPPS = true;
	private boolean usePortableKbn = false;
	// 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
	private boolean useReceiptMWM = false;
	// 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応
	// 2019.08.06 Add Cosmo Start 排他対応
	private boolean exclusive = false;
	// 2019.08.06 Add Cosmo End 排他対応
	// 2020.01.30 Add Cosmo Start 排他IP変更対応
	private boolean usePIP = false;
	// 2019.08.06 Add Cosmo End 排他IP変更対応
	// 2020.03.05 Nishihara@COSMO Start 町田市民病院PortableRIS改造対応
	private boolean transitionFlg;
	// 2020.03.05 Nishihara@COSMO End 町田市民病院PortableRIS改造対応

	// 2020.08.25 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
	//進捗ボタン表示制御対応(DB値と設定ファイルの色取得切り替え、背景・文字色のON/OFF色設定値)
	private boolean statusColorFlg;
	private String statusColorBk_ON;
	private String statusColorBk_OFF;
	private String statusColor_ON;
	private String statusColor_OFF;
	// 2020.08.25 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

	/**
	 * @return appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid セットする appid
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return kensatypeid
	 */
	public String getKensatypeid() {
		return kensatypeid;
	}

	/**
	 * @param kensatypeid セットする kensatypeid
	 */
	public void setKensatypeid(String kensatypeid) {
		this.kensatypeid = kensatypeid;
	}

	/**
	 * @return syokuinkbn
	 */
	public String getSyokuinkbn() {
		return syokuinkbn;
	}

	/**
	 * @param syokuinkbn セットする syokuinkbn
	 */
	public void setSyokuinkbn(String syokuinkbn) {
		this.syokuinkbn = syokuinkbn;
	}

	/**
	 * @return byoutouid
	 */
	public String getByoutouid() {
		return byoutouid;
	}

	/**
	 * @param byoutouid セットする byoutouid
	 */
	public void setByoutouid(String byoutouid) {
		this.byoutouid = byoutouid;
	}

	/**
	 * @return statuscode
	 */
	public String getStatuscode() {
		return statuscode;
	}

	/**
	 * @param statuscode セットする statuscode
	 */
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	/**
	 * @return statuscode_miuke
	 */
	public String getStatuscode_miuke() {
		return statuscode_miuke;
	}

	/**
	 * @param statuscode_miuke セットする statuscode_miuke
	 */
	public void setStatuscode_miuke(String statuscode_miuke) {
		this.statuscode_miuke = statuscode_miuke;
	}

	// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
	/**
	 * @return statuscode_yobidasi
	 */
	public String getStatuscode_yobidasi() {
		return statuscode_yobidasi;
	}

	/**
	 * @param statuscode_yobidasi セットする statuscode_yobidasi
	 */
	public void setStatuscode_yobidasi(String statuscode_yobidasi) {
		this.statuscode_yobidasi = statuscode_yobidasi;
	}
	// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応

	/**
	 * @return statuscode_ukezumi
	 */
	public String getStatuscode_ukezumi() {
		return statuscode_ukezumi;
	}

	/**
	 * @param statuscodeukezumi セットする statuscodeukezumi
	 */
	public void setStatuscode_ukezumi(String statuscode_ukezumi) {
		this.statuscode_ukezumi = statuscode_ukezumi;
	}

	/**
	 * @return statuscode_kentyuu
	 */
	public String getStatuscode_kentyuu() {
		return statuscode_kentyuu;
	}

	/**
	 * @param statuscode_kentyuu セットする statuscode_kentyuu
	 */
	public void setStatuscode_kentyuu(String statuscode_kentyuu) {
		this.statuscode_kentyuu = statuscode_kentyuu;
	}

	/**
	 * @return statuscode_horyuu
	 */
	public String getStatuscode_horyuu() {
		return statuscode_horyuu;
	}

	/**
	 * @param statuscode_horyuu セットする statuscode_horyuu
	 */
	public void setStatuscode_horyuu(String statuscode_horyuu) {
		this.statuscode_horyuu = statuscode_horyuu;
	}

	/**
	 * @return statuscode_kenzumi
	 */
	public String getStatuscode_kenzumi() {
		return statuscode_kenzumi;
	}

	/**
	 * @param statuscode_kenzumi セットする statuscode_kenzumi
	 */
	public void setStatuscode_kenzumi(String statuscode_kenzumi) {
		this.statuscode_kenzumi = statuscode_kenzumi;
	}

	/**
	 * @return groupcode
	 */
	public String getGroupcode() {
		return groupcode;
	}

	/**
	 * @param groupcode セットする groupcode
	 */
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	/**
	 * @return nyugaikbn
	 */
	public String getNyugaikbn() {
		return nyugaikbn;
	}

	/**
	 * @param nyugaikbn セットする nyugaikbn
	 */
	public void setNyugaikbn(String nyugaikbn) {
		this.nyugaikbn = nyugaikbn;
	}

	/**
	 * @return portablekbn
	 */
	public String getPortablekbn() {
		return portablekbn;
	}

	/**
	 * @param portablekbn セットするportablekbn
	 */
	public void setPortablekbn(String portablekbn) {
		this.portablekbn = portablekbn;
	}

	public String getOrdercomment_id() {
		return ordercomment_id;
	}

	public void setOrdercomment_id(String ordercomment_id) {
		this.ordercomment_id = ordercomment_id;
	}

	public String getKensa_siji() {
		return kensa_siji;
	}

	public void setKensa_siji(String kensa_siji) {
		this.kensa_siji = kensa_siji;
	}

	public String getRinsyou() {
		return rinsyou;
	}

	public void setRinsyou(String rinsyou) {
		this.rinsyou = rinsyou;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getHandicapped() {
		return handicapped;
	}

	public void setHandicapped(String handicapped) {
		this.handicapped = handicapped;
	}

	public String getInfection() {
		return infection;
	}

	public void setInfection(String infection) {
		this.infection = infection;
	}

	public String getContraindication() {
		return contraindication;
	}

	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(String pregnancy) {
		this.pregnancy = pregnancy;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getExamdata() {
		return examdata;
	}

	public void setExamdata(String examdata) {
		this.examdata = examdata;
	}

	public String getBuicomment() {
		return buicomment;
	}

	public void setBuicomment(String buicomment) {
		this.buicomment = buicomment;
	}

	public Integer getMwmtimeout() {
		return mwmtimeout * 1000; // ミリ秒変換
	}

	public void setMwmtimeout(String mwmtimeout) {

		if (!StringUtils.isEmpty(mwmtimeout) && NumberUtils.isNumber(mwmtimeout))
		{
			this.mwmtimeout = Integer.parseInt(mwmtimeout);
		}
	}

	/**
	 * @return mppstimeout
	 */
	public Integer getMppstimeout() {
		return mppstimeout * 1000; // ミリ秒変換
	}

	/**
	 * @param mppstimeout セットする mppstimeout
	 */
	public void setMppstimeout(String mppstimeout) {
		if (!StringUtils.isEmpty(mppstimeout) && NumberUtils.isNumber(mppstimeout))
		{
			this.mppstimeout = Integer.parseInt(mppstimeout);
		}
	}


	public String getKanaromatext() {
		return kanaromatext;
	}

	public void setKanaromatext(String kanaromatext) {
		this.kanaromatext = kanaromatext;
	}

	public boolean getUseMPPS() {
		return this.useMPPS;
	}

	public void setUseMPPS(boolean value) {
		this.useMPPS = value;
	}

	public boolean getUsePortableKbn() {
		return this.usePortableKbn;
	}

	public void setUsePortableKbn(boolean value) {
		this.usePortableKbn = value;
	}

	// 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
	public boolean getUseReceiptMWM() {
		return useReceiptMWM;
	}

	public void setUseReceiptMWM(boolean useReceiptMWM) {
		this.useReceiptMWM = useReceiptMWM;
	}
	// 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応
	// 2019.08.06 Add Cosmo Start 排他対応
	public boolean getExclusive() {
		return exclusive;
	}
	/**
	 * @param exclusive セットするexclusive
	 */
	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}
	// 2019.08.06 Add Cosmo End 排他対応
	// 2020.01.30 Add Cosmo Start 排他IP変更対応

	/**
	 * @return usePIP
	 */
	public boolean getUsePIP() {
		return usePIP;
	}

	/**
	 * @param usePIP セットする usePIP
	 */
	public void setUsePIP(boolean usePIP) {
		this.usePIP = usePIP;
	}
	// 2020.01.30 Add Cosmo End 排他IP変更対応

	/**
	 * @return transitionFlg
	 */
	public boolean getTransitionFlg() {
		return transitionFlg;
	}

	/**
	 * @param transitionFlg セットする transitionFlg
	 */
	public void setTransitionFlg(boolean transitionFlg) {
		this.transitionFlg = transitionFlg;
	}
	//2020.03.05 受付ボタン押下時の画面遷移フラグ


	// 2020.08.25 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
	/**
	 * @return statusColorFlg
	 */
	public boolean getStatusColorFlg() {
		return statusColorFlg;
	}

	/**
	 * @param statusColorFlg セットする statusColorFlg
	 */
	//設定ファイルから取得したフラグが格納される
	public void setStatusColorFlg(boolean statusColorFlg) {
		this.statusColorFlg = statusColorFlg;
	}

	/**
	 * @return statusColorBk_ON
	 */
	public String getStatusColorBk_ON() {
		return statusColorBk_ON;
	}

	/**
	 * @param statusColorBk_ON セットする statusColorBk_ON
	 */
	public void setStatusColorBk_ON(String statusColorBk_ON) {
		this.statusColorBk_ON = statusColorBk_ON;
	}

	/**
	 * @return statusColorBk_OFF
	 */
	public String getStatusColorBk_OFF() {
		return statusColorBk_OFF;
	}

	/**
	 * @param statusColorBk_OFF セットする statusColorBk_OFF
	 */
	public void setStatusColorBk_OFF(String statusColorBk_OFF) {
		this.statusColorBk_OFF = statusColorBk_OFF;
	}

	/**
	 * @return statusColor_ON
	 */
	public String getStatusColor_ON() {
		return statusColor_ON;
	}

	/**
	 * @param statusColor_ON セットする statusColor_ON
	 */
	public void setStatusColor_ON(String statusColor_ON) {
		this.statusColor_ON = statusColor_ON;
	}

	/**
	 * @return statusColor_OFF
	 */
	public String getStatusColor_OFF() {
		return statusColor_OFF;
	}

	/**
	 * @param statusColor_OFF セットする statusColor_OFF
	 */
	public void setStatusColor_OFF(String statusColor_OFF) {
		this.statusColor_OFF = statusColor_OFF;
	}

	// 2020.08.25 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

	/**
	 * 設定ファイル情報取得
	 * @param ctx
	 * @param dto
	 * @return
	 */
	public static Config getConfig(
			ServletContext ctx,
			// 2019.01.04 Add S.Ichinose@COSMO Start 呼出参照機能対応
			LoginDto dto
			// 2019.01.04 Add S.Ichinose@COSMO End   呼出参照機能対応
			) throws Exception {

		Config config = loadConfig(ctx, dto);

		return config;
	}

	/**
	 * 設定ファイル情報読込
	 * @param ctx
	 * @param dto
	 * @return
	 */
	private static Config loadConfig(
			ServletContext ctx,
			// 2019.01.04 Add S.Ichinose@COSMO Start 呼出参照機能対応
			LoginDto dto
			// 2019.01.04 Add S.Ichinose@COSMO End   呼出参照機能対応
			) throws Exception {

 		Config config = null;

		try {

			URL url = ctx.getResource(FILE_NAME);
			logger.debug(FILE_NAME + " = " + url);

			InputStream stream = ctx.getResourceAsStream(FILE_NAME);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(stream);
			Element root = doc.getDocumentElement();

			HashMap<String,String> map = new HashMap<String,String>();
			for (int i = 0; i < root.getChildNodes().getLength(); i++) {
				logger.debug(root.getChildNodes().item(i).getNodeName() + ":" + root.getChildNodes().item(i).getTextContent());
				map.put(root.getChildNodes().item(i).getNodeName(), Util.toNullString(root.getChildNodes().item(i).getTextContent()));
			}

			config = new Config();

			config.setAppid(map.get("AppId"));
			if (StringUtils.isEmpty(config.getAppid())) {
				throw new Exception("アプリケーションIDが未設定です。");
			}
			config.setKensatypeid(map.get("KensaTypeId"));
			if (StringUtils.isEmpty(config.getKensatypeid())) {
				throw new Exception("検査種別IDが未設定です。");
			}
			// 2019.01.04 Add S.Ichinose@COSMO Start 呼出参照機能対応
			if (dto.getIsReadMode()) {
				config.setKensatypeid(map.get("KensaTypeId_Read"));
				if (StringUtils.isEmpty(config.getKensatypeid())) {
					throw new Exception("検査種別ID(参照モード)が未設定です。");
				}
			}
			// 2019.01.04 Add S.Ichinose@COSMO End   呼出参照機能対応
			config.setSyokuinkbn(map.get("SyokuinKbn"));
			config.setByoutouid(map.get("ByoutouId"));
			config.setStatuscode(map.get("StatusCode"));
			if (StringUtils.isEmpty(config.getStatuscode())) {
				throw new Exception("検査ステータスが未設定です。");
			}
			config.setStatuscode_miuke(map.get("StatusCode_MIUKE"));
			if (StringUtils.isEmpty(config.getStatuscode_miuke())) {
				throw new Exception("検査ステータス変換：未受が未設定です。");
			}
			// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
			config.setStatuscode_yobidasi(map.get("StatusCode_YOBIDASI"));
			if (StringUtils.isEmpty(config.getStatuscode_yobidasi())) {
				throw new Exception("検査ステータス変換：呼出が未設定です。");
			}
			// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
			config.setStatuscode_ukezumi(map.get("StatusCode_UKEZUMI"));
			if (StringUtils.isEmpty(config.getStatuscode_ukezumi())) {
				throw new Exception("検査ステータス変換：受済が未設定です。");
			}
			config.setStatuscode_kentyuu(map.get("StatusCode_KENTYUU"));
			if (StringUtils.isEmpty(config.getStatuscode_kentyuu())) {
				throw new Exception("検査ステータス変換：検中が未設定です。");
			}
			config.setStatuscode_horyuu(map.get("StatusCode_HORYUU"));
			if (StringUtils.isEmpty(config.getStatuscode_horyuu())) {
				throw new Exception("検査ステータス変換：保留が未設定です。");
			}
			config.setStatuscode_kenzumi(map.get("StatusCode_KENZUMI"));
			if (StringUtils.isEmpty(config.getStatuscode_kenzumi())) {
				throw new Exception("検査ステータス変換：検済が未設定です。");
			}
			config.setGroupcode(map.get("GroupCode"));
			config.setNyugaikbn(map.get("NyugaiKbn"));
			if (StringUtils.isEmpty(config.getNyugaikbn())) {
				throw new Exception("入外区分が未設定です。");
			}
			config.setPortablekbn(map.get("PortableKbn"));
			if (StringUtils.isEmpty(config.getPortablekbn())) {
				throw new Exception("ポータブル区分が未設定です。");
			}
			config.setOrdercomment_id(map.get("ORDERCOMMENT_ID"));
			config.setKensa_siji(map.get("KENSA_SIJI"));
			config.setRinsyou(map.get("RINSYOU"));
			config.setRemarks(map.get("REMARKS"));
			config.setHandicapped(map.get("HANDICAPPED"));
			config.setInfection(map.get("INFECTION"));
			config.setContraindication(map.get("CONTRAINDICATION"));
			config.setAllergy(map.get("ALLERGY"));
			config.setPregnancy(map.get("PREGNANCY"));
			config.setNotes(map.get("NOTES"));
			config.setExamdata(map.get("EXAMDATA"));
			config.setBuicomment(map.get("BUICOMMENT"));

			config.setMwmtimeout(map.get("MWMTimeout"));
			config.setMppstimeout(map.get("MPPSTimeout"));

			config.setKanaromatext(map.get("KanaRomaText"));
			if (StringUtils.isEmpty(config.getKanaromatext())) {
				throw new Exception("かなローマ変換テキストファイルパスが未設定です。");
			}
			config.setUseMPPS(Boolean.valueOf(map.get("UseMPPS")));
			config.setUsePortableKbn(Boolean.valueOf(map.get("UsePortableKbn")));
			// 2019.06.06 Add H.Watanabe@COSMO Start 熊本大学PortableRIS改造対応
			config.setUseReceiptMWM(Boolean.valueOf(map.get("UseReceiptMWM")));
			// 2019.06.06 Add H.Watanabe@COSMO End   熊本大学PortableRIS改造対応
			// 2019.08.06 Add Cosmo Start 排他対応
			config.setExclusive(Boolean.valueOf(map.get("Exclusive")));
			// 2019.08.06 Add Cosmo End 排他対応

			// 2020.01.30 Add Cosmo Start 排他IP変更対応
			config.setUsePIP(Boolean.valueOf(map.get("UsePIP")));
			// 2019.08.06 Add Cosmo End 排他IP変更対応

			// 2020.03.05 Nishihara@COSMO Start 町田市民病院PortableRIS改造対応
			config.setTransitionFlg(Boolean.valueOf(map.get("TransitionFlg")));
			// 2020.03.05 Nishihara@COSMO End 町田市民病院PortableRIS改造対応

			// 2020.08.25 Add Nishihara@COSMO start 進捗ボタン背景・文字色設定対応
			config.setStatusColorFlg(Boolean.valueOf(map.get("StatusColorFlg")));
			config.setStatusColorBk_ON(map.get("StatusColorBk_ON"));
			config.setStatusColorBk_OFF(map.get("StatusColorBk_OFF"));
			config.setStatusColor_ON(map.get("StatusColor_ON"));
			config.setStatusColor_OFF(map.get("StatusColor_OFF"));
			// 2020.08.25 Add Nishihara@COSMO end 進捗ボタン背景・文字色設定対応

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		return config;
	}

	/**
	 * 検査ステータス変換（サーバ用）
	 * @param status
	 * @return
	 */
	public String convStatusServer(String status) {

		// 変換後検査ステータス
		String convstatus = "";

		// 検査ステータス変換値取得
		if (Const.STATUS_UNREGISTERED.equals(status)) {
			convstatus = statuscode_miuke;     // 未受
		}
		// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
		else if (Const.STATUS_ISCALLING.equals(status)) {
			convstatus = statuscode_yobidasi;  // 呼出
		}
		// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
		else if (Const.STATUS_ISREGISTERED.equals(status)) {
			convstatus = statuscode_ukezumi;   // 受付
		} else if (Const.STATUS_INOPERATION.equals(status)) {
			convstatus = statuscode_kentyuu;   // 検中
		} else if (Const.STATUS_REST.equals(status)) {
			convstatus = statuscode_horyuu;    // 保留
		} else if (Const.STATUS_ISFINISHED.equals(status)) {
			convstatus = statuscode_kenzumi;   // 検済
		}

		return convstatus;
	}

	/**
	 * 検査ステータス変換（クライアント用）
	 * @param status
	 * @return
	 */
	public Integer convStatusClient(String status) {

		// 変換後検査ステータス
		Integer convstatus = null;

		// 検査ステータス変換値取得
		if (Arrays.asList(statuscode_miuke.split(",")).indexOf(status) > -1) {
			convstatus = Integer.parseInt(Const.STATUS_UNREGISTERED);   // 未受
		}
		// 2018.12.20 Add H.Watanabe@COSMO Start 呼出参照機能対応
		else if (Arrays.asList(statuscode_yobidasi.split(",")).indexOf(status) > -1) {
			convstatus = Integer.parseInt(Const.STATUS_ISCALLING);       // 呼出
		}
		// 2018.12.20 Add H.Watanabe@COSMO End   呼出参照機能対応
		else if (Arrays.asList(statuscode_ukezumi.split(",")).indexOf(status) > -1) {
			convstatus = Integer.parseInt(Const.STATUS_ISREGISTERED);    // 受付
		} else if (Arrays.asList(statuscode_kentyuu.split(",")).indexOf(status) > -1) {
			convstatus = Integer.parseInt(Const.STATUS_INOPERATION);     // 検中
		} else if (Arrays.asList(statuscode_horyuu.split(",")).indexOf(status) > -1) {
			convstatus = Integer.parseInt(Const.STATUS_REST);            // 保留
		} else if (Arrays.asList(statuscode_kenzumi.split(",")).indexOf(status) > -1) {
			convstatus = Integer.parseInt(Const.STATUS_ISFINISHED);      // 検済
		}

		return convstatus;
	}
}
