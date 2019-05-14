package org.mutils.aliyun.sms.model;

import java.util.Date;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.ModelUtil;
import cn.minsin.core.tools.date.DateUtil;
import cn.minsin.core.tools.date.DefaultDateFormat;

public class AliyunQueryModel extends AbstractModelRule {

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
		ModelUtil.verificationField(this);
		QuerySendDetailsRequest querySendDetailsRequest = new QuerySendDetailsRequest();
		querySendDetailsRequest.setCurrentPage(currentPage);
		querySendDetailsRequest.setPageSize(pageSize);
		querySendDetailsRequest.setPhoneNumber(phoneNumber);
		querySendDetailsRequest.setSendDate(DateUtil.date2String(sendDate, DefaultDateFormat.yyyyMMdd));
		return querySendDetailsRequest;
	}

}
