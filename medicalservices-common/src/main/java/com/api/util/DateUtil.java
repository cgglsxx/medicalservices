package com.api.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public final static String	FORMAT_DEFAULT		= "yyyy-MM-dd";
	public final static String	FORMAT_FULL			= "yyyy-MM-dd HH:mm:ss";
	public final static String	FORMAT_FULL_12		= "yyyy-MM-dd hh:mm:ss";
	public final static String	FORMAT_FULL_THREE	= "yyyyMMddHHmmss";

	/**
	 * 校验时间格式是否是指定格式
	 * 
	 * @param dateStr
	 * @return 如果待检测时间不满足，返回true,否则，返回false
	 */
	public static boolean isErrorDateFormat(String dateStr) {
		boolean t;
		try {
			new SimpleDateFormat(FORMAT_FULL_12).parse(dateStr);
			t = false;
		} catch (Exception e) {
			t = true;
		}
		return t;
	}

	/**
	 * 将日期格式转换为字符串
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String formatDateToString(Date date, String formatString) {
		if ((formatString == null) || "".equals(formatString)) {
			formatString = FORMAT_DEFAULT;
		}
		return new SimpleDateFormat(formatString).format(date);
	}

	/**
	 * 将日期格式转换为字符串
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String formatDateToString(Timestamp date, String formatString) {
		if ((formatString == null) || "".equals(formatString)) {
			formatString = FORMAT_DEFAULT;
		}
		return new SimpleDateFormat(formatString).format(date);
	}

	/**
	 * 将日期格式转换为字符串[取服务器时间]
	 * 
	 * @param formatString
	 * @return
	 */
	public static String formatDateToString(String formatString) {
		if ((formatString == null) || "".equals(formatString)) {
			formatString = FORMAT_DEFAULT;
		}
		return new SimpleDateFormat(formatString).format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 将字符串转换为日期
	 * 
	 * @param strDate
	 * @param formatString
	 * @return
	 */
	public static Date formatStringToDate(String strDate, String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		Date date;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			date = new Date();
			logger.error(strDate + ", Date format string parse error ! use current date ", e);
		}
		return date;
	}

	/**
	 * 得到当前应用服务器的时间
	 * 
	 * @return 时间
	 */
	public static Date getCurrApplicationDate() {
		return new Date();
	}

	/**
	 * 得到当前应用服务器的时间
	 * 
	 * @return 时间
	 */
	public static Timestamp getCurrApplicationTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 将字符串日期解析为指定的日期格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatyyyyMMddHHmmss(String date) {
		try {
			return formatDateToString(new SimpleDateFormat(FORMAT_FULL).parse(date), FORMAT_FULL_THREE);
		} catch (ParseException e) {
			logger.error( date + "解析为[" + FORMAT_FULL_THREE + "]时发生解析错误", e);
			return "";
		}
	}
}
