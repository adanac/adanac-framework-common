package com.adanac.framework.web.escape;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 注册自定义的StringEscapeEditor属性编辑器
 * @author adanac
 * @version 1.0
 */
public class StringEscapeSupportController {
	private boolean escapeHTML = true;

	// 将自定义的StringEscapeEditor属性编辑器注册到绑定器对象中
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringEscapeEditor(escapeHTML));
	}

	protected void setEscapeHTML(boolean escapeHTML) {
		this.escapeHTML = escapeHTML;
	}

}
