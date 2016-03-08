package com.adanac.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 时间处理工具类
 * @author adanac
 * @version 1.0
 */
public class DateUtils {
	/**
	 * 默认时间格式
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 当天时间，二十四小时制
	 */
	public static final String DEFAULT_TODAY_PATTERN = "HH:mm";

	/**
	 * 昨天格式
	 */
	public static final String DEFUALT_YESTERDAY_PATTERN = "昨天 HH:mm";

	/**
	 * 月日格式
	 */
	public static final String DEFAULT_MONTH_AND_DAY = "M月d日 HH:mm";
	/**
	 * 年月日格式
	 */
	public static final String DEFAULT_YEAR_MONTH_DAY = "yyyy-MM-dd HH:mm";
	/**
	 * 年月日时分秒格式
	 */
	public static final String DEFAULT_YEAR_MONTH_DAY_HMS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * MONDAY
	 */
	public static final String MONDAY = "星期一";
	/**
	 * TUESDAY
	 */
	public static final String TUESDAY = "星期二";
	/**
	 * WEDNESDAY
	 */
	public static final String WEDNESDAY = "星期三";
	/**
	 * THURSDAY
	 */
	public static final String THURSDAY = "星期四";
	/**
	 * FRIDAY
	 */
	public static final String FRIDAY = "星期五";
	/**
	 * SATURDAY
	 */
	public static final String SATURDAY = "星期六";
	/**
	 * SUNDAY
	 */
	public static final String SUNDAY = "星期日";

	/**
	 * 日期不能为空提示信息
	 */
	private static final String DATE_NULL_MSG = "The date must not be null";

	/**
	 * 私有化构造函数
	 */
	private DateUtils() {
	}

	/**
	 * 处理时间戳为格式化字符串<br>
	 * 
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String format(long timestamp, String pattern) {
		Date date = new Date(timestamp);
		return format(date, pattern);
	}

	/**
	 * 处理时间为格式化字符串<br>
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 * 解析时间字符串<br>
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date parse(String source, String pattern) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 是否同一天<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameday(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameday(cal1, cal2);
	}

	/**
	 * 是否同一天<br>
	 * 
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static boolean isSameday(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}
		return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 是否昨天<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isYesterday(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isYesterday(cal1, cal2);
	}

	/**
	 * 是否昨天<br>
	 * 
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static boolean isYesterday(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}

		return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& (cal1.get(Calendar.DAY_OF_YEAR) - 1 == cal2.get(Calendar.DAY_OF_YEAR)
						|| cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) - 1);
	}

	/**
	 * 是否本周<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameweek(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameweek(cal1, cal2);
	}

	/**
	 * 是否本周<br>
	 * 
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static boolean isSameweek(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}

		return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.WEEK_OF_MONTH) == cal2.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 是否本年<br>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameyear(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameyear(cal1, cal2);
	}

	/**
	 * 是否本年<br>
	 * 
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static boolean isSameyear(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException(DATE_NULL_MSG);
		}

		return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
	}

	/**
	 * 取一天内的小时数，二十四小时制<br>
	 * 
	 * @param calendar
	 * @return
	 */
	public static String toToday(Date date) {
		return format(date, DEFAULT_TODAY_PATTERN);
	}

	/**
	 * 昨天时间格式<br>
	 * 
	 * @param date
	 * @return
	 */
	public static String toYesterday(Date date) {
		return format(date, DEFUALT_YESTERDAY_PATTERN);
	}

	/**
	 * 取星期几<br>
	 * 
	 * @param calendar
	 * @return
	 */
	public static String toDayOfWeek(Calendar calendar) {
		String when = null;
		if (Calendar.MONDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

			when = MONDAY;
		} else if (Calendar.TUESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

			when = TUESDAY;
		} else if (Calendar.WEDNESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

			when = WEDNESDAY;
		} else if (Calendar.THURSDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

			when = THURSDAY;
		} else if (Calendar.FRIDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

			when = FRIDAY;
		} else if (Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

			when = SATURDAY;
		} else if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {

			when = SUNDAY;
		}
		return when;
	}

	/**
	 * 返回月日<br>
	 * 
	 * @param date
	 * @return
	 */
	public static String toMonthAndDay(Date date) {
		return format(date, DEFAULT_MONTH_AND_DAY);
	}

	/**
	 * 返回年月日<br>
	 * 
	 * @param date
	 * @return
	 */
	public static String toYearMonthDay(Date date) {
		return format(date, DEFAULT_YEAR_MONTH_DAY);
	}

	/**
	 * 格式化时间字符串<br>
	 * <p>
	 * 今天的：时间 24小时制
	 * </p>
	 * <p>
	 * 昨天：昨天
	 * </p>
	 * <p>
	 * 一周内：显示星期几
	 * </p>
	 * <p>
	 * 大于一周小于一年：4-7
	 * </p>
	 * <p>
	 * 大于一年：2012-4-7
	 * </p>
	 * 
	 * @param source
	 * @return
	 */
	public static String formatDate(String source) {
		if (StringUtils.isNotBlank(source)) {
			Date date = DateUtils.parse(source, DateUtils.DEFAULT_DATE_PATTERN);

			if (date != null) {
				Date now = new Date();

				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(now);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date);

				String result = null;

				if (DateUtils.isSameday(cal1, cal2)) {

					result = DateUtils.toToday(date);
				} else if (DateUtils.isYesterday(cal1, cal2)) {

					result = DateUtils.toYesterday(date);
				} else if (DateUtils.isSameweek(cal1, cal2)) {

					String dayOfWeek = DateUtils.toDayOfWeek(cal2);
					result = DateUtils.toToday(date);

					if (StringUtils.isNotBlank(dayOfWeek)) {
						result = dayOfWeek + " " + result;
					}
				} else if (DateUtils.isSameyear(cal1, cal2)) {

					result = DateUtils.toMonthAndDay(date);
				} else {

					result = DateUtils.toYearMonthDay(date);
				}

				return result;
			}
		}

		return "";
	}
}
