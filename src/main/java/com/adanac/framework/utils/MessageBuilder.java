package com.adanac.framework.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/*8
 * 国际化消息创建类，提供国际化消息字符串的取得功能
 */
public class MessageBuilder {
	/**
	 * MessageSource对象，从ApplicationContext中取得。该对象需要在Spring配置文件中进行配置。
	 */
	private static List<MessageSource> messageSourceList = new ArrayList<MessageSource>();

	/**
	 * @param messageSource the messageSource to set
	 */
	public static void setMessageSource(MessageSource messageSource) {
		MessageBuilder.messageSourceList.add(messageSource);
	}

	/**
	 * 
	 * 功能描述：根据参数id和parameters从MessageSource对象中取得对应的国际化消息字符串。
	 * 
	 * @param id 消息Id
	 * @param parameters 消息参数
	 * @return String 国际化消息字符串
	 */
	public static String getMessage(String id, Object[] parameters) {
		return getMessage(id, parameters, Locale.SIMPLIFIED_CHINESE);
	}

	/**
	 * 
	 * 功能描述：根据参数id和parameters从MessageSource对象中取得对应的国际化消息字符串。
	 * 
	 * @param id 消息Id
	 * @param parameters 消息参数
	 * @param locale 区域
	 * @return String 国际化消息字符串
	 */
	public static String getMessage(String id, Object[] parameters, Locale locale) {
		String message = null;
		for (MessageSource messageSource : messageSourceList) {
			try {
				// 强制使用zh-cn
				message = messageSource.getMessage(id, parameters, Locale.SIMPLIFIED_CHINESE);
				break;
			} catch (NoSuchMessageException ex) {
				continue;
			}
		}
		// if (message == null) {
		// message = "id:[" + id + "] not found in message properties files.";
		// }
		return message;
	}
}
