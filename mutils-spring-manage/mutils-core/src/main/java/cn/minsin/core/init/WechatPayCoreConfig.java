package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class WechatPayCoreConfig extends InitConfig {
	
	// 	商户id
	private String partnerId;

	// 	商户秘钥
	private String partnerKey;

	// 	退款请求地址
	private String refundUrl="https://api.mch.weixin.qq.com/secapi/pay/refund";

	// 	付款通知地址
	private String notifyUrl;
	
	// 	退款异步通知地址
	private String refundNotifyUrl;

	// 	退款证书地址
	private String certificatePath;

	// 	统一下单地址
	private String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	// 	退款证书格式
	private String certificateFormat = "PKCS12";

	// 	是否包含退款 如果为true 如果为true certificatePath、refundUrl、certificateFormat必填
	private boolean withRefund = false;
	
	//	提现地址
	private String withdrawUrl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
	//	是否同步 如果为false 那么 refundNotifyUrl不能为空
	private boolean isSynchronizeRefund =true;
	

	public boolean isSynchronizeRefund() {
		return isSynchronizeRefund;
	}


	public void setSynchronizeRefund(boolean isSynchronizeRefund) {
		this.isSynchronizeRefund = isSynchronizeRefund;
	}


	public String getRefundNotifyUrl() {
		return refundNotifyUrl;
	}


	public void setRefundNotifyUrl(String refundNotifyUrl) {
		this.refundNotifyUrl = refundNotifyUrl;
	}


	public boolean isWithRefund() {
		return withRefund;
	}

	
	public String getWithdrawUrl() {
		return withdrawUrl;
	}

	public void setWithdrawUrl(String withdrawUrl) {
		this.withdrawUrl = withdrawUrl;
	}



	public void setWithRefund(boolean withRefund) {
		this.withRefund = withRefund;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getUnifiedOrderUrl() {
		return unifiedOrderUrl;
	}

	public void setUnifiedOrderUrl(String unifiedOrderUrl) {
		this.unifiedOrderUrl = unifiedOrderUrl;
	}

	public String getCertificateFormat() {
		return certificateFormat;
	}

	public void setCertificateFormat(String certificateFormat) {
		this.certificateFormat = certificateFormat;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization partnerId,partnerKey,notifyUrl,unifiedOrderUrl."
				+ "When withRefund is true,You also need certificatePath, refundUrl, certificateFormat,refundNotifyUrl");
		if (StringUtil.isBlank(partnerId, partnerKey, notifyUrl, unifiedOrderUrl)) {
			throw new MutilsException("An error when initialization the basic config for wechat pay, please check config");
		}
		if (withRefund) {
			if (StringUtil.isBlank(certificatePath, refundUrl, certificateFormat)) {
				throw new MutilsException("The refund config of wechat pay was initialization failed.");
			}
			if(!isSynchronizeRefund) {
				if (StringUtil.isBlank(refundNotifyUrl)) {
					throw new MutilsException("RefundNotifyUrl  cannot be empty.");
				}
			}
		}

	}
}
