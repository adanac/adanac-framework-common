package com.adanac.framework.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currDate = sdf.format(new Date().getTime());
		return currDate;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTimestamp() {
		return new Timestamp(new Date().getTime()).toString();
	}

	/**
	 * 获取当前时间+1年
	 * 
	 * @return
	 */
	public static Timestamp getOneYearLaterTs() {
		Calendar currCalendar = Calendar.getInstance();
		currCalendar.set(Calendar.YEAR, currCalendar.get(Calendar.YEAR) + 1);
		return new Timestamp(currCalendar.getTimeInMillis());

	}
}
