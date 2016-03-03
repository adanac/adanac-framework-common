package com.adanac.framework.exception.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import com.adanac.framework.exception.BaseException;
import com.adanac.framework.exception.logger.ExceptionLogger;
import com.adanac.framework.exception.logger.LogMessageBuilder;
import com.adanac.framework.exception.model.ExceptionCode;

/**
 * 根据bean name 从ApplicationContext中获取bean实例的帮助类
 * @author adanac
 * @version 1.0
 */
public class SpringBeanHelper {
	public static <T> T getBean(String name, Class<T> requiredType, ApplicationContext applicationContext) {
		if (null == applicationContext) {
			return null;
		}
		T bean = null;
		try {
			bean = applicationContext.getBean(name, requiredType);
		} catch (NoSuchBeanDefinitionException exp) {
			String message = String.format("Bean [%s] no be defined.", name);
			String messageWithStacktrace = LogMessageBuilder.build(message, exp);
			ExceptionLogger.log(messageWithStacktrace);
		} catch (BeanNotOfRequiredTypeException ex) {
			String message = String.format("The type of bean [%s] is not the required type.", name);
			throw new BaseException(message, ex, ExceptionCode.INNER_EXCEPTION);
		} catch (BeansException ex) {
			String message = String.format("The bean [%s] could not be created.", name);
			throw new BaseException(message, ex, ExceptionCode.INNER_EXCEPTION);
		}
		return bean;
	}

	public static <T> T getBean(Class<T> requiredType, ApplicationContext applicationContext) {
		if (null == applicationContext) {
			return null;
		}
		T bean = null;
		try {
			bean = applicationContext.getBean(requiredType);
		} catch (NoSuchBeanDefinitionException exp) {
			String message = String.format("there is not exactly one matching bean [%s] found", requiredType);
			String messageWithStacktrace = LogMessageBuilder.build(message, exp);
			ExceptionLogger.log(messageWithStacktrace);
		} catch (BeansException ex) {
			String message = String.format("The bean [%s] could not be created.");
			throw new BaseException(message, ex, ExceptionCode.INNER_EXCEPTION);
		}
		return bean;
	}
}
