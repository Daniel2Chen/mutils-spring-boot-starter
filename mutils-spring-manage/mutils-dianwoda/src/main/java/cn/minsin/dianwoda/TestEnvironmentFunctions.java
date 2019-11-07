package cn.minsin.dianwoda;

import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.vo.VO;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;


/**
 * 	点我达测试环境的专用接口
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class TestEnvironmentFunctions  extends AbstractFunctionRule{

	/**
	 * 接受订单（测试接口仅测试环境有效）
	 * 
	 * @param order_original_id
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static JSONObject orderAcceptedTest(String order_original_id) throws ClientProtocolException, IOException{
		return DianWoDaFunctions.doSend("/api/v3/order-accepted-test.json",
				VO.init().put("order_original_id", order_original_id));
	}

	/**
	 * 完成到店（测试接口仅测试环境有效）
	 * 
	 * @param order_original_id
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static JSONObject orderArriveTest(String order_original_id) throws ClientProtocolException, IOException {
		return DianWoDaFunctions.doSend("/api/v3/order-arrive-test.json",
				VO.init().put("order_original_id", order_original_id));
	}


	/**
	 * 完成取货（测试接口仅测试环境有效）
	 * @param order_original_id
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static JSONObject orderFetchTest(String order_original_id) throws ClientProtocolException, IOException{
		return DianWoDaFunctions.doSend("/api/v3/order-fetch-test.json",
				VO.init().put("order_original_id", order_original_id));
	}
	
	/**
	 * 完成配送（测试接口仅测试环境有效）
	 * @param order_original_id
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static JSONObject orderFinishTest(String order_original_id) throws ClientProtocolException, IOException {
		return DianWoDaFunctions.doSend("/api/v3/order-finish-test.json",
				VO.init().put("order_original_id", order_original_id));
	}
}
