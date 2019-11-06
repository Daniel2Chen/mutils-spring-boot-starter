package cn.minsin.wechat.miniprogram.model;

import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Code2SessionReturnModel extends AbstractModelRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1259691916411620938L;
	/**
	 * 	用户唯一标识	
	 */
	private String openid;
	/**
	 * 	会话密钥
	 */
	private String session_key;
	/**
	 * 	用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
	 */
	private String unionid;
	/**
	 * 	错误码	
	 */
	private Integer errcode;
	/**
	 * 	错误信息
	 */
	private String errmsg;


}
