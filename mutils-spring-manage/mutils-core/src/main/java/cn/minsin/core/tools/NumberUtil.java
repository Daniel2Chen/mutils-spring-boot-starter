package cn.minsin.core.tools;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil extends NumberUtils {

	/**
	 * 判断字符串是否是数字（无法具体判断是整型还是浮点型）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumbers(String... str) {
		try {
			for (String string : str) {
				if (!NumberUtils.isCreatable(string))
					return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是数字（指定类型）
	 * @param str
	 * @return
	 */
	public static <T extends Number> boolean isNumbers(Class<T> type, String... str) {
		try {
			Method method = type.getMethod("valueOf", String.class);
			for (String string : str) {
				method.invoke(type, string);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 	保留指定小数长度的数据
	 * @param length
	 * @param old
	 * @return
	 */
	public static BigDecimal keepLenthDecimals(int length,BigDecimal old) {
		if(length<0||old==null) {
			return old;
		}
		return old.setScale(length, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 转换成分
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal toCent(BigDecimal bigDecimal) {
		if(bigDecimal!=null) {
			return keepLenthDecimals(2, bigDecimal).multiply(new BigDecimal(100));
		}
		return bigDecimal;
	}
	/**
	 * 转换成分
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal toYuan(BigDecimal bigDecimal) {
		if(bigDecimal!=null) {
			return bigDecimal.divide(new BigDecimal(100),2,RoundingMode.HALF_DOWN);
		}
		return bigDecimal;
	}
}
