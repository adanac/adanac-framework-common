package com.adanac.framework.web.converter;

import java.text.ParseException;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.adanac.framework.exception.BaseException;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class StringDateConverter extends DateConverterBase implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		if (source == null) {
			return null;
		}
		String trim = source.trim();
		if (trim.length() == 0) {
			return null;
		}
		try {
			return source.contains(":") ? getDateTimeFormat().parse(trim) : getDateFormat().parse(trim);
		} catch (ParseException e) {
			throw new BaseException("无效的日期格式：" + trim);
		}
	}
}
