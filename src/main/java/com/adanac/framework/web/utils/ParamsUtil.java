package com.adanac.framework.web.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.adanac.framework.utils.GETIPUtil;
import com.adanac.framework.utils.ObjectUtil;
import com.adanac.framework.web.constant.CommonConstant;
import com.adanac.framework.web.controller.LocaleAndThemResolve;
import com.adanac.framework.web.security.SqlInjectCheck;
import com.adanac.framework.web.security.XSSCheck;

/**
 * 功能描述： request请求参数处理
 * @author adanac
 * @version 1.0
 */
public class ParamsUtil {

	private static final String PAGE = "page";
	private static final String PAGESIZE = "pageSize";
	private static final String MSG = "msg";
	private static final String LANG = "lang";
	private static final String LANG_SPLIT = "_";

	private ParamsUtil() {
	}

	/**
	 * 
	 * 〈从request里面获取参数，并将分页、国际化信息做统一处理〉 〈用于代替baseController.getParams()〉 〈baseController.getParams()在多线程环境不安全〉
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getParams(HttpServletRequest request) {
		return getParams(request, false);
	}

	/**
	 * 
	 * 〈从request里面获取参数，并将分页、国际化信息做统一处理〉 〈用于代替baseController.getParams()〉 〈baseController.getParams()在多线程环境不安全〉
	 * 
	 * @param request
	 * @param isCheck
	 * @return
	 */
	public static Map<String, Object> getParams(HttpServletRequest request, boolean isCheck) {
		Map<String, Object> requestParams = ParamsUtil.handleServletParameter(request, isCheck);

		// 处理分页参数
		if (null == requestParams.get(PAGE)) {
			requestParams.put(PAGE, CommonConstant.PAGE_START);
		} else {
			requestParams.put(PAGE, requestParams.get(PAGE));
		}
		if (null == requestParams.get(PAGESIZE)) {
			requestParams.put(PAGESIZE, CommonConstant.DEFAULT_PAGE_SIZE);
		} else {
			requestParams.put(PAGESIZE, requestParams.get(PAGESIZE));
		}

		// 处理页面消息
		if (!ObjectUtil.isEmpty(requestParams.get(MSG))) {
			requestParams.put(MSG, requestParams.get(MSG));
		}

		// 国际化处理
		String languagecode = CommonConstant.LANGUAGECODE_DEFAULT;
		if (!ObjectUtil.isEmpty(requestParams.get(LANG))) {
			String[] langArr = requestParams.get(LANG).toString().split(LANG_SPLIT);
			languagecode = langArr[0];
		} else {
			Locale defaultLocale = LocaleAndThemResolve.resolveLocale(request);
			languagecode = LocaleAndThemResolve.getLocaleLang(defaultLocale);
		}
		requestParams.put(CommonConstant.LANGUAGECODE, languagecode);

		// 客户端IP处理
		requestParams.put(CommonConstant.USER_IP, GETIPUtil.getIpAddr(request));

		return requestParams;
	}

	/**
	 * 封装HttpServletRequest参数
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> handleServletParameter(HttpServletRequest request, boolean isCheck) {
		// 非文件类型表单域,表单参数通过getParameterMap获取
		Map<String, Object> requestParameter = request.getParameterMap();

		// 文件类型表单域,表单参数通过getAttribute获取(MyMultiParseFile类中实现)
		Map<String, Object> attributeParameter = new HashMap<String, Object>();
		if (null != request.getAttribute(CommonConstant.AUTOATTRIBUTE)) {
			attributeParameter = (Map<String, Object>) request.getAttribute(CommonConstant.AUTOATTRIBUTE);
		}

		// 合并参数
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.putAll(requestParameter);
		parameter.putAll(attributeParameter);

		// 将POST表单参数转换成普通key-value对
		Map<String, Object> rParams = new HashMap<String, Object>(0);

		// 不需转码
		if (isCheck == true) {
			for (Map.Entry<String, Object> m : parameter.entrySet()) {
				String key = m.getKey();
				Object[] obj = (Object[]) parameter.get(key);
				rParams.put(key, (obj.length > 1) ? StringSecurityCheck(obj) : StringSecurityCheck(obj[0]));
			}
		} else {
			for (Map.Entry<String, Object> m : parameter.entrySet()) {
				String key = m.getKey();
				Object[] obj = (Object[]) parameter.get(key);
				rParams.put(key, (obj.length > 1) ? obj : obj[0]);
			}
		}
		return rParams;
	}

	/**
	 * 
	 * 〈从request参数里面获取分页参数page〉 〈用于代替baseController.getPage()〉
	 * 
	 * @param requestParams
	 * @return
	 */
	public static Integer getPage(Map<String, Object> requestParams) {
		if (null == requestParams || requestParams.isEmpty() || ObjectUtil.isEmpty(requestParams.get(PAGE))) {
			return CommonConstant.PAGE_START;
		}
		return Integer.valueOf(requestParams.get(PAGE).toString());
	}

	/**
	 * 
	 * 〈从request参数里面获取分页参数pageSize〉 〈用于代替baseController.getPageSize()〉
	 * 
	 * @param requestParams
	 * @return
	 */
	public static Integer getPageSize(Map<String, Object> requestParams) {
		if (null == requestParams || requestParams.isEmpty() || ObjectUtil.isEmpty(requestParams.get(PAGESIZE))) {
			return CommonConstant.DEFAULT_PAGE_SIZE;
		}
		return Integer.valueOf(requestParams.get(PAGESIZE).toString());
	}

	/**
	 * 
	 * 〈从request参数里面获取消息msg〉 〈用于代替baseController.getPageSize()〉
	 * 
	 * @param requestParams
	 * @return
	 */
	public static String getMsg(Map<String, Object> requestParams) {
		return String.valueOf(requestParams.get(MSG));
	}

	public static Object StringSecurityCheck(Object obj) {
		SqlInjectCheck sqlCheck = new SqlInjectCheck();
		XSSCheck xssCheck = new XSSCheck();
		String midStr;
		if (obj == null) {
			return obj;
		}
		if (obj instanceof String) {
			midStr = sqlCheck.check((String) obj);
			return xssCheck.check(midStr);
		}
		return obj;
	}
}
