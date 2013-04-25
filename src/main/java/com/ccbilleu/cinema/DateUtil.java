package com.ccbilleu.cinema;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
	private static final String DATE_PATTERN = "yyyyy-mm-dd hh:mm:ss";
	private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat(DATE_PATTERN);
	
	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		return DEFAULT_FORMAT.format(date);
	}
	
	public static Date getFutureDate(int days) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
}
