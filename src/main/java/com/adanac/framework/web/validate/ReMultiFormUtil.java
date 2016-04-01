package com.adanac.framework.web.validate;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class ReMultiFormUtil {
	/**
	 * 验证当前的formID与session中的formID是否一致
	 * @param request
	 * @return
	 */
	public static boolean validateFormId(HttpServletRequest request) {

		// 获取当前的formID
		String currentFormId = request.getParameter("currentFormId");
		if (StringUtils.isNotEmpty(currentFormId)) {
			// 获取当前的formID的uuid
			String currentUuid = request.getParameter(currentFormId);
			// 获取session中的uuid
			Object o = request.getSession().getAttribute(currentFormId);
			if (null == o || !o.toString().equals(currentUuid)) {
				return false;
			}
		}
		request.getSession().removeAttribute(currentFormId);
		return true;
	}
}
