package com.tz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 时间工具类 */
public class DateUtil {
	
	/**
	 * 根据给定的参数设置日期的年月日
	 */
	public static Date createDate(int year,int month,int day){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}
	
	/**
	 * 根据给定的格式格式化Date
	 */
	public static String formatDate(Date date,String pattern){
		//对date进行判断
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * 根据给定格式将String转换成Date
	 */
	public static Date parseString(String pattern,String time){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
