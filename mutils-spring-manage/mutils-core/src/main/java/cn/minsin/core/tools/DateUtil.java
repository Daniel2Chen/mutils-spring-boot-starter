package cn.minsin.core.tools;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 不同时间类型之间的转换
 *
 * @author mintonzhang@163.com
 * @date 2018年6月8日
 */
public class DateUtil extends DateUtils {

	public enum DateFormatEnum {
		yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
		yyyyMMddHHmmss("yyyyMMddHHmmss"), 
		yyyy_MM_dd("yyyy-MM-dd"),
		yyyyMMdd("yyyyMMdd"), 
		yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm");
		
		private String format;

		private DateFormatEnum(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}
	}


	public static String date2String(Date date) {
		return date2String(date, DateFormatEnum.yyyy_MM_dd_HH_mm_ss.getFormat());
	}

	public static String date2String(Date date, DateUtil.DateFormatEnum format) {
		return date2String(date, format.getFormat());
	}

	public static String date2String(Date date, String format) {
		if (date == null || StringUtil.isBlank(format)) {
			return null;
		}
		return DateFormatUtils.format(date, format, TimeZone.getTimeZone("GMT+8"));
	}

	public static String date2String(Date date, String format, String defaultValue) {
		try {
			if (StringUtil.isBlank(format)) {
				format = DateFormatEnum.yyyy_MM_dd_HH_mm_ss.getFormat();
			}
			return date2String(date, format);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String long2DateStr(long source) {
		return long2DateStr(source, DateFormatEnum.yyyy_MM_dd_HH_mm_ss.getFormat());
	}

	public static String long2DateStr(long source, DateUtil.DateFormatEnum format) {
		return DateFormatUtils.format(new Date(source), format.getFormat(), TimeZone.getTimeZone("GMT+8"));
	}

	public static String long2DateStr(long source, String format) {
		return DateFormatUtils.format(new Date(source), format, TimeZone.getTimeZone("GMT+8"));
	}
	
	public static String long2DateStr(long source, String format,String defaultValue) {
		try {
			return DateFormatUtils.format(new Date(source), format, TimeZone.getTimeZone("GMT+8"));
		}catch (Exception e) {
			return defaultValue;
		}

	}

	public static Date long2Date(long source) {
		return new Date(source);
	}

	/**
	 * 根据日期字符串格式化为指定的日期
	 *
	 * @param source 时间字符串
	 * @param format 字符串格式
	 * @return
	 */
	public static Date string2Date(String source, String format) {
		if (StringUtils.isBlank(format)) {
			format = DateFormatEnum.yyyy_MM_dd_HH_mm_ss.getFormat();
		}
		try {
			return DateUtils.parseDate(source, format);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 根据日期字符串格式化为指定的日期
	 *
	 * @param source 时间字符串
	 * @param format 字符串格式
	 * @return
	 */
	public static Date string2Date(String source,  DateUtil.DateFormatEnum format) {
		try {
			return DateUtils.parseDate(source, format.getFormat());
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 将指定格式的时间字符串转换指定的格式
	 * 
	 * @param origin 原时间字符串
	 * @param formater 原时间字符串的格式
	 * @param formatTo 转换为指定格式
	 * @return
	 */
	public static String transform(String origin, String formater, String formatTo) {
		try {
			Date tmp = string2Date(origin, formater);
			return date2String(tmp, formatTo);
		} catch (Exception e) {
			return origin;
		}
	}
	
	/**
	 * 系统当前时间
	 * @return 当前时间字符串 yyyy-mm-dd
	 */
	public static String getCurrentDate(){
		return date2String(new Date(),DateFormatEnum.yyyy_MM_dd);
	}
}
