package com.adanac.framework.web.tag;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

/**
 * 防重复提交同时生成input
 * @author adanac
 * @version 1.0
 */
public class NoRepeatSumitTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String formName;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int doStartTag() throws JspException {

		if (StringUtils.isEmpty(formName)) {
			return SKIP_BODY;
		}

		UUID uuid = UUID.randomUUID();
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		StringBuffer inputString = new StringBuffer();
		inputString.append("<input type=\"hidden\" name=\"currentFormId\" value=\"").append(formName).append("\"/>");
		inputString.append("<input type=\"hidden\" name=\"").append(formName).append("\" value=\"")
				.append(uuid.toString()).append("\"/>");
		try {
			// 给提交的表单添加唯一标识隐藏域
			this.pageContext.getOut().write(inputString.toString());
		} catch (IOException e) {
			throw new JspTagException(e);
		}
		// 将给表单添加的唯一标识同时添加到session中
		request.getSession().setAttribute(formName, uuid.toString());
		return EVAL_BODY_INCLUDE;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

}