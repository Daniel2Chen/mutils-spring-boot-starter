package cn.minsin.core.tools;

import java.util.Calendar;
import java.util.Date;

/**
 * 日历类工具类
 * 
 * @author mintonzhang
 * @date 2019年2月15日
 * @since 0.3.4
 */
public class CalendarUtil {

	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 求两个日期之间距离的天数
	 *
	 * @param date1 开始时间
	 * @param date2 结束时间
	 * @return 天数
	 */
	public static int differentDays(Date date1, Date date2) {
		calendar.setTime(date1);
		int day1 = calendar.get(Calendar.DAY_OF_YEAR);
		int year1 = calendar.get(Calendar.YEAR);

		calendar.setTime(date2);
		int day2 = calendar.get(Calendar.DAY_OF_YEAR);
		int year2 = calendar.get(Calendar.YEAR);

		if (year1 != year2) {
			// 不同年
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				timeDistance += (isLeapYear(i) ? 366 : 365);
			}
			return timeDistance + (day2 - day1);
		}
		// 同一年
		return day2 - day1;

	}

	/**
	 * 获取此月最大天数
	 * 
	 * @param date 日期
	 * @return 天数
	 */
	public static int getDaysOfMonth(int year, int month) {
		if (month < 0 || month > 12) {
			return 0;
		}
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取此年的最大天数
	 * 
	 * @param year
	 * @return
	 */
	public static int getDaysOfYear(int year) {
		calendar.set(Calendar.YEAR, year);
		return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 是否闰年
	 * 
	 * @param year 需要判断的年份
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		// 闰年规则：
		// (1)年份能被4整除，但不能被100整除；
		// (2)能被400整除。
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}

	/**
	 * 指定月 获取该月份对应季度的开始日期或结束日期
	 * 
	 * @param year  指定年
	 * @param month 指定月
	 * @param isEnd true将返回季度结束日期，false为季度开始时间
	 * @return
	 */
	public static Date getQuarterTime(int month, boolean isEnd) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}  
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		calendar.set(Calendar.MONTH, month - 1);
		int year = calendar.get(Calendar.YEAR);
		int m;
		int days = 1;
		if (isEnd) {
			m = array[season - 1][2];
			days = getDaysOfMonth(year, m);
		} else {
			m = array[season - 1][0];
		}
		calendar.set(Calendar.MONTH, m - 1);
		calendar.set(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 根据指定日期，向后或向前推算出对应的日期 比如：2019-2-15日 向后推7天或向前推7天 是哪天
	 * 
	 * @param date 指定日期 如果为空默认当前时间
	 * @param days 整数向未来日期计算 负数向过去日期计算
	 * @return
	 */
	public static Date getDate(Date date, int days) {
		checkDate(date);
		int i = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, i + days);
		Date time = calendar.getTime();
		System.out.println(DateUtil.date2String(time));
		return time;
	}

	/**
	 * 获得今天是这个月的第几周
	 * 
	 * @param date 指定日期 如果为null 默认当前时间
	 * @return
	 */
	public static int getWeekOfMonth(Date date) {
		checkDate(date);
		return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 获得指定日期在对应年的第几天
	 * 
	 * @param date 指定日期 如果为null 默认当前时间
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		checkDate(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获得指定日期在对应月的第几天(获得当前日)
	 * 
	 * @param date 指定日期 如果为null 默认当前时间
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		checkDate(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得指定日期在对应周的第几天
	 * 
	 * @param date             指定日期 如果为null 默认当前时间
	 * @param isChineseWeekend 在中国,周一是一周的开始，其他国家是周日为一周的开始
	 * @return
	 */
	public static int getDayOfWeek(Date date, boolean isChineseWeekend) {
		checkDate(date);
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		return isChineseWeekend ? i - 1 : i;
	}
	
	/**
	 * 获取一天的开始时间
	 * @param date
	 * @return
	 */
	public static Date getBeginOfDay(Date date) {
		if(date==null) {
			return null;
		}
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	}
	/**
	 *	 获取一天的结束
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		if(date==null) {
			return null;
		}
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		return calendar.getTime();
	}
	

	/**
	 * 检查日期，如果日期为空，默认当前时间
	 * 
	 * @param date 需要验证的时间
	 */
	private static void checkDate(Date date) {
		calendar.setTime(date == null ? new Date() : date);
	}
}
