package cn.minsin.core.tools;

import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 	数值工具类  对{@link NumberUtils} 进行拓展
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class NumberUtil extends NumberUtils {

    protected NumberUtil() {
        // allow Subclass
    }
	/**
	 * 判断字符串是否是数字（无法具体判断是整型还是浮点型）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumbers(String... str) {
		try {
			for (String string : str) {
				if (!NumberUtils.isCreatable(string)) {
					return false;
				}
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
	 * 保留指定小数长度的数据
	 * 
	 * @param length
	 * @param old
	 * @return
	 */
	public static BigDecimal decimalsXLength(int length, BigDecimal old) {
		if (length < 0 || old == null) {
			return old;
		}
		return old.setScale(length, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * a+b and Keep 'decimal' decimals
	 * 
	 * @param <T>     must extend {@code Number}
	 * @param a
	 * @param b
	 * @param decimal
	 * @return
	 */
	public static <T extends Number> BigDecimal add(T a, T b, int decimal) {
		if (a != null && b != null) {
			return decimalsXLength(2, BigDecimal.valueOf(a.doubleValue()))
					.add(BigDecimal.valueOf(b.doubleValue()))
					.setScale(decimal);
		}
		return BigDecimal.valueOf(0);
	}

	/**
	 * like a-b and Keep 'decimal' decimals
	 * 
	 * @param <T>     must extend {@code Number}
	 * @param a
	 * @param b
	 * @param decimal
	 * @return
	 */
	public static <T extends Number> BigDecimal subtract(T a, T b, int decimal) {
		if (a != null && b != null) {
			return decimalsXLength(2, BigDecimal.valueOf(a.doubleValue()))
					.subtract(BigDecimal.valueOf(b.doubleValue()))
					.setScale(decimal);
		}
		return BigDecimal.valueOf(0);
	}

	/**
	 * a/b and Keep 'decimal' decimals
	 * 
	 * @param <T>     must extend {@code Number}
	 * @param a
	 * @param b
	 * @param decimal
	 * @return
	 */
	public static <T extends Number> BigDecimal divide(T a, T b, int decimal) {
		if (a != null && b != null) {
			return decimalsXLength(2, BigDecimal.valueOf(a.doubleValue()))
					.divide(BigDecimal.valueOf(b.doubleValue()), decimal, RoundingMode.HALF_UP)
					.setScale(decimal);
		}
		return BigDecimal.valueOf(0);
	}

	/**
	 * a*b and Keep 'decimal' decimals
	 * 
	 * @param <T>     must extend {@code Number}
	 * @param a
	 * @param b
	 * @param decimal
	 * @return
	 */
	public static <T extends Number> BigDecimal multiply(T a, T b, int decimal) {
		if (a != null && b != null) {
			return decimalsXLength(2, BigDecimal.valueOf(a.doubleValue()))
					.multiply(BigDecimal.valueOf(b.doubleValue()))
					.setScale(decimal);
		}
		return BigDecimal.valueOf(0);
	}

	/**
	 * a%b and Keep 'decimal' decimals
	 * 
	 * @param <T>     must extend {@code Number}
	 * @param a
	 * @param b
	 * @param decimal
	 * @return
	 */
	public static <T extends Number> BigDecimal remainder(T a, T b, int decimal) {
		if (a != null && b != null) {
			return decimalsXLength(2, BigDecimal.valueOf(a.doubleValue()))
					.remainder(BigDecimal.valueOf(b.doubleValue()))
					.setScale(decimal);
		}
		return BigDecimal.valueOf(0);
	}
}
