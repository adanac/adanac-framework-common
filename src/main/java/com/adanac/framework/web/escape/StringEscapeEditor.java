package com.adanac.framework.web.escape;

import java.beans.PropertyEditorSupport;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

/**
 * 自定义属性编辑器StringEscapeEditor
 * @author adanac
 * @version 1.0
 */
public class StringEscapeEditor extends PropertyEditorSupport {
	private boolean escapeHTML;

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML) {
		super();
		this.escapeHTML = escapeHTML;
	}

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (escapeHTML) {
				// 将String类型的value转化为html的格式
				value = HtmlUtils.htmlEscape(value);
				setValue(value);
			}

		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

	public static void main(String[] args) {
		String value = "<script> alert(aa)</script>";
		System.out.println(HtmlUtils.htmlEscape(value));
		System.out.println(JavaScriptUtils.javaScriptEscape(value));
	}
}
