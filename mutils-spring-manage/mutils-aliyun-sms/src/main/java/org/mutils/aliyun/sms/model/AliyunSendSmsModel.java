package org.mutils.aliyun.sms.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.rule.ModelRule;
import cn.minsin.core.web.VO;

public class AliyunSendSmsModel extends ModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6467905286980123978L;

	@NotNull("支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔。")
	private List<String> phones = new ArrayList<>();

	@NotNull("短信签名名称。请在控制台签名管理页面签名名称一列查看。")
	private List<String> signName = new ArrayList<>();

	@NotNull("短信模板ID。请在控制台模板管理页面模板CODE一列查看。")
	private String templateCode;

	@NotNull("短信模板变量对应的实际值")
	private List<VO> templateParam = new ArrayList<>();

	public AliyunSendSmsModel addPhoneNumber(String phone) {
		this.phones.add(phone);
		return this;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}


	public AliyunSendSmsModel addPhoneNumber(List<String> phoneNumbers) {
		this.phones.addAll(phoneNumbers);
		return this;
	}

	public AliyunSendSmsModel addSignName(String signName) {
		this.signName.add(signName);
		return this;
	}

	public AliyunSendSmsModel addSignName(List<String> signName) {
		this.signName.addAll(signName);
		return this;
	}

	public AliyunSendSmsModel addTemplateParam(VO vo) {
		this.templateParam.add(vo);
		return this;
	}

	public List<String> getPhones() {
		return phones;
	}

	public List<String> getSignName() {
		return signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public List<VO> getTemplateParam() {
		return templateParam;
	}

	public SendBatchSmsRequest toSendBatchSmsRequest() throws MutilsErrorException {
		this.verificationField();
		SendBatchSmsRequest sendBatchSmsRequest = new SendBatchSmsRequest();
		sendBatchSmsRequest.setPhoneNumberJson(JSON.toJSONString(phones));
		sendBatchSmsRequest.setTemplateCode(templateCode);
		sendBatchSmsRequest.setSignNameJson(JSON.toJSONString(signName));
		sendBatchSmsRequest.setTemplateParamJson(JSON.toJSONString(signName));
		return sendBatchSmsRequest;
	}

	public SendSmsRequest toSendSmsRequest() throws MutilsErrorException {
		this.verificationField();
		SendSmsRequest sendSmsRequest = new SendSmsRequest();
		sendSmsRequest.setTemplateCode(templateCode);
		sendSmsRequest.setTemplateParam(templateParam.get(0).toString());
		sendSmsRequest.setSignName(signName.get(0));
		sendSmsRequest.setPhoneNumbers(phones.get(0));
		return sendSmsRequest;
	}
}
