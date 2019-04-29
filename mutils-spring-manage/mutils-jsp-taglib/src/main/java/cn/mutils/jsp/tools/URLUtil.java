package cn.mutils.jsp.tools;

import java.util.ArrayList;
import java.util.List;

import cn.minsin.core.tools.StringUtil;
import cn.minsin.core.web.VO;

public class URLUtil {

	/**
	 * 拼接url回跳
	 * 
	 * @param url
	 * @param vo
	 * @return
	 */
	public static StringBuffer spliceUrl(String url, VO vo) {
		try {
			StringBuffer sb = new StringBuffer(url).append("?");
			List<String> sets = new ArrayList<>(vo.keySet());
			int size = sets.size();
			for (int i = 0; i < size; i++) {
				String string = sets.get(i);
				Object value = vo.getValue(string);
				boolean blank = StringUtil.isBlank(value);
				if (!blank) {
					sb.append(string).append("=").append(value);
					if (size - 1 != i) {
						sb.append("&");
					}
				}
			}
			return sb;
		} catch (Exception e) {
			return new StringBuffer();
		}
	}
}
