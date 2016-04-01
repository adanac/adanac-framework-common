package com.adanac.framework.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.adanac.framework.web.validate.ReDistortFormUtil;

/**
 * 防篡改Form Tag
 * @author adanac
 * @version 1.0
 */
public class ReDistortFormTag extends BodyTagSupport {

	/** valueMap用来放表单中的防篡改的对象的属性名与值 */
	private Map<String, String> valueMap = new LinkedHashMap<String, String>();

	/** 防篡改的表单名 */
	private String name;

	private static final String ANTI_DISTORT_TOKENNAME = "antiDistorTokenName";

	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * 对关键数据加密，放入session
	 */
	public int doEndTag() throws JspException {
		if (null == name || name.trim().equals("")) {
			name = ANTI_DISTORT_TOKENNAME;
		}
		StringBuffer inputString = new StringBuffer();
		// 将ANTI_DISTORT_TOKENNAME与防篡改的表单名放入页面隐藏域中
		inputString.append("<input type=\"hidden\" name=\"" + ANTI_DISTORT_TOKENNAME + "\" value=\"").append(name)
				.append("\"/>");
		try {
			List<String> filedsNames = new ArrayList<String>();
			if (null != getValueMap() && !getValueMap().isEmpty()) {
				Set<Entry<String, String>> set = getValueMap().entrySet();
				for (Iterator<Entry<String, String>> it = set.iterator(); it.hasNext();) {
					Map.Entry entry = (Map.Entry) it.next();
					filedsNames.add((String) entry.getKey());
				}
				// 将表单中的防篡改的所有对象的属性名组成string类型放入隐藏域
				inputString.append("<input type=\"hidden\" name=\"" + name + "Fileds" + "\" value=\"")
						.append(StringUtils.join(filedsNames.toArray(), ",")).append("\"/>");

				getValueMap().put("suning", name);

				// 将表单中的防篡改的所有对象的属性名与值组成的hashmap加密算法后获取的值放入隐藏域中
				String value = ReDistortFormUtil.reDistort(getValueMap());
				inputString.append("<input type=\"hidden\" name=\"" + name + "Value" + "\" value=\"").append(value)
						.append("\"/>");
			}

			this.pageContext.getOut().write(inputString.toString());

		} catch (IOException e) {
			throw new JspTagException(e);
		}

		return EVAL_PAGE;
	}

	/**
	 * @return the valueMap
	 */
	public Map<String, String> getValueMap() {
		return valueMap;
	}

	/**
	 * @param valueMap the valueMap to set
	 */
	public void setValueMap(Map<String, String> valueMap) {
		this.valueMap = valueMap;
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
