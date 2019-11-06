package cn.minsin.meituan.peisong.model.receive;

import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.meituan.peisong.enums.OrderStatus;

public abstract class AbstractMeituanReceiveModel extends AbstractModelRule {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1818509328181140464L;
	
	protected OrderStatus code;
	
    protected String message;

    public OrderStatus getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = OrderStatus.findByCode(code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
