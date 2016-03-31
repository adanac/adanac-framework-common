package com.adanac.framework.web.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 实现dateTostring转换
 * @author adanac
 * @version 1.0
 */
public class DateStringConverter extends DateConverterBase implements Converter<Date, String> {
	@Override
	public String convert(Date source) {
		if (source == null) {
			return "";
		}
		return getDateFormat().format(source);
	}
}
