package com.adanac.framework.exception.handler;

import static com.adanac.framework.exception.model.ConfigConstant.LINE_SEPARATOR;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.adanac.framework.exception.model.ConfigConstant;
import com.adanac.framework.exception.model.ExceptionInfo;
import com.adanac.framework.exception.utils.NetUtil;

/**
 * 用于log的缺省的异常信息格式化器
 * 普通TEXT格式化器,将异常信息对象 {@link ExceptionInfo} 转换为String字符串
 * @author adanac
 * @version 1.0
 */
public class DefaultLogExceptionInfoFormater implements ExceptionInfoFormater {
	public String getFormatString(ExceptionInfo expInfo) {
		Assert.notNull(expInfo, "ExceptionInfo can't be null!");

		StringBuilder buffer = new StringBuilder(0);

		/*
		 * buffer.append(expInfo.getExceptionClass()).append(" ").append(
		 * " been intercepted:").append(LINE_SEPARATOR) .append(
		 * "[Intercepted Code]: "
		 * ).append(expInfo.getCode()).append(LINE_SEPARATOR) .append(
		 * "[Intercepted Time]: "
		 * ).append(expInfo.getInterceptTime()).append(LINE_SEPARATOR) .append(
		 * "[Intercepted Class]: "
		 * ).append(expInfo.getInterceptedClass()).append(LINE_SEPARATOR)
		 * .append("[Intercepted Interface]: "
		 * ).append(expInfo.getInterceptedMethod()).append(LINE_SEPARATOR)
		 * .append("[Arguments]: "
		 * ).append(expInfo.getParameterValue()).append(LINE_SEPARATOR) .append(
		 * "[Root Exception]: "
		 * ).append(expInfo.getRootException()).append(LINE_SEPARATOR) .append(
		 * "[Root Exception Message]: "
		 * ).append(expInfo.getRootExceptionMsg()).append(LINE_SEPARATOR)
		 * .append("[Root exception throwed on]: ")
		 * .append(expInfo.getRootCauseSpotClass()).append(".").append(expInfo.
		 * getRootCauseSpotMethod()) .append("  Line:"
		 * ).append(expInfo.getRootCauseSpotLine()).append(LINE_SEPARATOR)
		 * .append("[Full Stack trace]:").append(LINE_SEPARATOR)
		 * .append(expInfo.getFullStackTrace());
		 */
		buffer.append(ConfigConstant.LOGGER_MESSAGE_PREFIX).append(expInfo.getInterceptTime())
				.append(ConfigConstant.SPLIT_SEPARATOR)
				.append(StringUtils.collectionToDelimitedString(NetUtil.getAllLocalIP(), ","))
				.append(ConfigConstant.SPLIT_SEPARATOR).append(expInfo.getCode()).append(ConfigConstant.SPLIT_SEPARATOR)
				.append("[Intercepted Class]: ").append(expInfo.getInterceptedClass()).append(LINE_SEPARATOR)
				.append("[Intercepted Interface]: ").append(expInfo.getInterceptedMethod()).append(LINE_SEPARATOR)
				.append("[Root Exception]: ").append(expInfo.getRootException()).append(LINE_SEPARATOR)
				.append("[Root Exception Message]: ").append(expInfo.getRootExceptionMsg()).append(LINE_SEPARATOR)
				.append("[Root exception throwed on]: ").append(expInfo.getRootCauseSpotClass()).append(".")
				.append(expInfo.getRootCauseSpotMethod()).append("  Line:").append(expInfo.getRootCauseSpotLine())
				.append(LINE_SEPARATOR).append("[Full Stack trace]:").append(LINE_SEPARATOR)
				.append(expInfo.getFullStackTrace()).append(ConfigConstant.LOGGER_MESSAGE_END)
				// .append(expInfo.getFullStackTrace()).append(LINE_SEPARATOR)
				;

		return buffer.toString();
	}

}
