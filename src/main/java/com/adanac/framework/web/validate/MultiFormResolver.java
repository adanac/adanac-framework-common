package com.adanac.framework.web.validate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义注解MultiForm的解读
 * @author adanac
 * @version 1.0
 */
public class MultiFormResolver implements HandlerMethodArgumentResolver {

	// 判断MultiForm是不是一个Parameter注解
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(MultiForm.class) != null;
	}

	// 解析参数，对应@MultiForm注解的参数
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		// 返回比较结果
		boolean isNotMuilt = ReMultiFormUtil.validateFormId(request);
		return isNotMuilt;
	}
}
