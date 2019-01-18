package org.mutils.gexin.push;

import java.util.ArrayList;
import java.util.List;

import org.mutils.gexin.push.model.PushModel;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.GexinPushConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;

/**
 * 	推送功能列表
 * 
 * @author mintonzhang
 * @date 2019年1月16日
 * @since 0.2.3
 */
public class GexinPushFunctions extends FunctionRule {

	private final static GexinPushConfig config = InitConfig.loadConfig(GexinPushConfig.class);

	protected static IGtPush initPush() {
		return new IGtPush(config.getUrl(), config.getAppkey(), config.getMasterSecret());
	}

	/**
	 * 推送单个
	 * 
	 * @param model 推送的model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static IPushResult pushSingle(PushModel model) throws MutilsErrorException {
		model.setPushMany(false);
		return push(model);
	}

	/**
	 * 推送单个
	 * 
	 * @param model 推送的model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static IPushResult pushMany(PushModel model) throws MutilsErrorException {
		model.setPushMany(true);
		return push(model);
	}

	/**
	 * 	 推送一个或多个由pushMany 控制
	 * 
	 * @param model 推送的对象
	 * @return
	 * @throws MutilsErrorException
	 */
	public static IPushResult push(PushModel model) throws MutilsErrorException {
		model.verificationField();// 检查
		IGtPush push = initPush();
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(config.getAppid());
		template.setAppkey(config.getAppkey());
		template.setTransmissionType(model.getTransmissionType());
		template.setTransmissionContent(model.getContent());

		APNPayload payload = new APNPayload();
		payload.setContentAvailable(1);
		payload.setSound(model.getSound());
		payload.setCategory(model.getContent());
		payload.setAlertMsg(new APNPayload.SimpleAlertMsg(model.getTitle()));
		template.setAPNInfo(payload);

		List<String> clientids = model.getClientids();
		if (clientids.isEmpty()) {
			throw new MutilsErrorException("clientid不能为空");
		}
		try {
			boolean pushMany = model.isPushMany();
			if (pushMany) {
				SingleMessage message = new SingleMessage();
				message.setOffline(true);
				message.setOfflineExpireTime(model.getTimeout());
				message.setData(template);
				message.setPushNetWorkType(model.getPushNetWorkType());

				Target target = new Target();
				target.setAppId(config.getAppid());
				target.setClientId(clientids.get(0));
				return push.pushMessageToSingle(message, target);
			}
			ListMessage message = new ListMessage();
			message.setOffline(true);
			message.setOfflineExpireTime(24 * 3600 * 1000);
			message.setData(template);
			message.setPushNetWorkType(0);

			List<Target> targets = new ArrayList<Target>();
			for (String clientid : clientids) {
				Target target = new Target();
				target.setAppId(config.getAppid());
				target.setClientId(clientid);
				targets.add(target);
			}
			String contentId = push.getContentId(message);
			return push.pushMessageToList(contentId, targets);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "推送失败");
		}
	}

}
