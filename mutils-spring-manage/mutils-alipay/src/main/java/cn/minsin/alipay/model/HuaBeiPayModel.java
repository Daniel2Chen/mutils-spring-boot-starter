package cn.minsin.alipay.model;

import cn.minsin.alipay.AlipayFunctions;
import cn.minsin.alipay.enums.HuaBeiSellerPercent;
import cn.minsin.alipay.enums.HuaBeiStaging;
import cn.minsin.core.annotation.NotNull;

/**
 * 分期支付的实体类 可以正常传入正常传入到 {@link AlipayFunctions }使用支付功能
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class HuaBeiPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887384829795015728L;

	@NotNull("需要使用花呗分期，此项默认pcreditpayInstallment")
	private String enable_pay_channels = "pcreditpayInstallment";

	@NotNull("分期数 默认3期")
	private Integer hb_fq_num = 3;
	
	@NotNull("分期利率  默认用户承担所有分期费")
	private Integer hb_fq_seller_percent = 0;

	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}

	public int getHb_fq_num() {
		return hb_fq_num;
	}

	public int getHb_fq_seller_percent() {
		return hb_fq_seller_percent;
	}

	public void setHuaBeiStaging(HuaBeiStaging huaBeiStaging, HuaBeiSellerPercent huaBeiSellerPercent) {
		if (huaBeiStaging == null || huaBeiSellerPercent == null) {
			log.info(
					"HuaBeiStaging or huaBeiSellerPercent is null. so,the value of hb_fq_num is 3,hb_fq_seller_percent is 0.");
			return;
		}
		hb_fq_num = huaBeiStaging.getStag();
		hb_fq_seller_percent = huaBeiSellerPercent.getPercent();
	}

}
