package org.mutils.union.pay.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.ModelUtil;

/**
 * 	银联下单对象
 * @author mintonzhang
 * @date 2019年1月16日
 */
public class UnionPayModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4016100203073832316L;
	@NotNull("版本号，全渠道默认值")
	private String version = "5.0.0";

	@NotNull("字符集编码，可以使用UTF-8,GBK两种方式 默认utf-8")
	private String encoding = "UTF-8";

	@NotNull("签名方法，只支持 01：RSA方式证书加密")
	private String signMethod = "01";

	@NotNull("交易类型 ，01：消费")
	private String txnType = "01";

	@NotNull("交易子类型， 01：自助消费")
	private String txnSubType = "01";

	@NotNull("B2C网关支付，手机wap支付")
	private String bizType;

	@NotNull("渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机")
	private String channelType;

	@NotNull("前台回调地址(自定义)")
	private String frontUrl;

	@NotNull("商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号")
	private String merId;

	@NotNull("接入类型，0：直连商户 ")
	private String accessType = "0";

	@NotNull("商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则")
	private String orderId;

	@NotNull("订单发送时间 系统默认 ")
	private String txnTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

	@NotNull("境内商户一般是156 人民币 ")
	private String currencyCode = "156";

	@NotNull("交易金额，单位分，不要带小数点")
	private Integer txnAmt;

	@NotNull(value = "请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节", notNull = false)
	private String reqReserved;

	// 后台通知地址（需设置为【外网】能访问 http
	// https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
	// 后台通知参数详见open.unionpay.com帮助中心 下载 产品接口规范 网关支付产品接口规范 消费交易 商户通知
	// 注意:1.需设置为外网能访问，否则收不到通知 2.http https均可 3.收单后台通知后需要10秒内返回http200或302状态码
	// 4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
	// 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d
	// 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
	@NotNull("回调地址")
	private String backUrl;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTxnSubType() {
		return txnSubType;
	}

	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Integer getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(Integer txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getReqReserved() {
		return reqReserved;
	}

	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public SortedMap<String, String> toTreeMap() throws MutilsErrorException{
		return 	ModelUtil.toTreeMap(this);
	}

}
