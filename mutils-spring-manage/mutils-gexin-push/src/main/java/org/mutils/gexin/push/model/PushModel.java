package org.mutils.gexin.push.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.StringUtil;

/**
 *	推送model
 * @author mintonzhang
 * @date 2019年1月16日
 * @since 0.2.3
 */
public class PushModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2444460637534039680L;

	@NotNull("推送用户唯一标识 当 pushMany为false时只读取第一个")
	private Set<String> clientids = new HashSet<>();

	@NotNull("提示消息头下方文字")
	private String subTitle;
	
	@NotNull("提示消息头")
	private String title;

	@NotNull("推送内容 一般情况下是json字符串")
	private String content;

	@NotNull("透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动  默认2")
	private int TransmissionType = 2;

	@NotNull("离线有效时间，单位为毫秒，可选  默认一天")
	private long timeout = 24 * 3600 * 1000;

	@NotNull("声音  默认default")
	private String sound = "default";

	@NotNull("是否推送多个 如果是多个应该设置true")
	private boolean pushMany = false;

	@NotNull("1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发 默认0")
	private int pushNetWorkType = 0;

	public int getPushNetWorkType() {
		return pushNetWorkType;
	}

	public void setPushNetWorkType(int pushNetWorkType) {
		this.pushNetWorkType = pushNetWorkType;
	}

	public List<String> getClientids() {
		return new ArrayList<>(clientids);
	}

	public void setClientids(List<String> clientids) {
		for (String string : clientids) {
			if (StringUtil.isNotBlank(string)) {
				this.clientids.add(string);
			}
		}
	}

	public void setClientids(String... clientids) {
		for (String string : clientids) {
			if (StringUtil.isNotBlank(string)) {
				this.clientids.add(string);
			}
		}
	}
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public void setClientids(String clientid) {
		this.clientids.add(clientid);
	}

	public boolean isPushMany() {
		return pushMany;
	}

	public void setPushMany(boolean pushMany) {
		this.pushMany = pushMany;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTransmissionType() {
		return TransmissionType;
	}

	public void setTransmissionType(int transmissionType) {
		TransmissionType = transmissionType;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

}
