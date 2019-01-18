package org.mutils.aliyun.sms.model;

import java.util.Date;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.ModelRule;
import cn.minsin.core.tools.DateUtil;

public class AliyunQueryModel extends ModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8125943806355576059L;

	@NotNull("分页查看发送记录，指定发送记录的的当前页码。")
	private Long currentPage = 1L;

	@NotNull("分页查看发送记录，指定每页显示的短信记录数量。 取值范围为1~50。")
	private Long pageSize = 10L;

	@NotNull("接收短信的手机号码。格式：国内短信：11位手机号码，例如15900000000。国际/港澳台消息：国际区号+号码，例如85200000000。")
	private String phoneNumber;

	@NotNull("短信发送日期，支持查询最近30天的记录。格式为yyyyMMdd，例如20181225。")
	private Date sendDate;
	
	@NotNull("连接超时时间 默认1000ms")
	private Long  defaultConnectTimeout=1000L;
	
	@NotNull("读取超时时间 默认1000ms")
	private Long defaultReadTimeout=1000L;
	
	
	public Long getDefaultConnectTimeout() {
		return defaultConnectTimeout;
	}

	public void setDefaultConnectTimeout(Long defaultConnectTimeout) {
		this.defaultConnectTimeout = defaultConnectTimeout;
	}

	public Long getDefaultReadTimeout() {
		return defaultReadTimeout;
	}

	public void setDefaultReadTimeout(Long defaultReadTimeout) {
		this.defaultReadTimeout = defaultReadTimeout;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public QuerySendDetailsRequest toQuerySendDetailsRequest() {
		this.verificationField();
		QuerySendDetailsRequest querySendDetailsRequest = new QuerySendDetailsRequest();
		querySendDetailsRequest.setCurrentPage(currentPage);
		querySendDetailsRequest.setPageSize(pageSize);
		querySendDetailsRequest.setPhoneNumber(phoneNumber);
		querySendDetailsRequest.setSendDate(DateUtil.date2String(sendDate, 3));
		return querySendDetailsRequest;
	}

}
