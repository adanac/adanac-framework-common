package com.adanac.framework.web.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateConverterBase {
	private String datePattern = "yyyy-MM-dd";
	private String timePattern = "HH:mm:ss";
	private DateFormat dateFormat = new SimpleDateFormat(datePattern);
	private DateFormat dateTimeFormat = new SimpleDateFormat(datePattern + " " + timePattern);

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateFormat getDateTimeFormat() {
		return dateTimeFormat;
	}

	public void setDateTimeFormat(DateFormat dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}
}
