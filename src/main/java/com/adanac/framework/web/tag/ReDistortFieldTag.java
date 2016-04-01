package com.adanac.framework.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 防篡改 form field tag
 */
public class ReDistortFieldTag extends BodyTagSupport {

	/** 防篡改的对象的属性值 */
	private String value;

	/** 防篡改的对象的属性名 */
	private String name;

	public int doStartTag() throws JspException {

		StringBuffer inputString = new StringBuffer();

		inputString.append("<input type=\"hidden\" name=\"").append(name).append("\" value=\"").append(value)
				.append("\"/>");

		try {
			// 给防篡改的对象设置隐藏域，并加入页面
			this.pageContext.getOut().write(inputString.toString());
		} catch (IOException e) {
			throw new JspTagException(e);
		}
		ReDistortFormTag parent = (ReDistortFormTag) getParent();
		// 将防篡改的对象的属性名与值放入到ReDistortFormTag的ValueMap中
		parent.getValueMap().put(name, value);

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}