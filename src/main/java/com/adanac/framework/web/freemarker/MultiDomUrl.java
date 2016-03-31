package com.adanac.framework.web.freemarker;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 实现静态资源自动多域名处理
 * @author adanac
 * @version 1.0
 */
public class MultiDomUrl implements TemplateMethodModel {

	/** 原始域名：含新域名编号所在位置的占位符 */
	String host = null;

	/** 新域名编号所在位置的占位符 */
	String imgHostTag = null;

	/** 图片处理域名数量 */
	String imgHostNumber = null;

	/** 域名编号起始值*/
	int imgUrlStarNum = 0;

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		if (null == arguments || arguments.size() == 0) {
			return "";
		}

		// 获取资源文件名
		String url = (String) arguments.get(0);

		int imgNumber = 5;

		if (!StringUtils.isBlank(imgHostNumber)) {
			imgNumber = Integer.valueOf(imgHostNumber);
		}
		if (StringUtils.isBlank(url)) {
			return "";
		}

		// 获取新域名编号
		int suffix = Math.abs(stringToInt(url) % imgNumber) + imgUrlStarNum;

		// 替换编号所在位置的占位符
		String hostName = host.replace(imgHostTag, String.valueOf(suffix));

		if (!hostName.endsWith("/")) {
			hostName = hostName + "/";
		}

		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		// 组装形成新域名
		String result = hostName + url;

		return result;
	}

	// 获取资源文件名的hashcode
	private int stringToInt(String str) {
		return str.hashCode();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getImgHostTag() {
		return imgHostTag;
	}

	public void setImgHostTag(String imgHostTag) {
		this.imgHostTag = imgHostTag;
	}

	public String getImgHostNumber() {
		return imgHostNumber;
	}

	public void setImgHostNumber(String imgHostNumber) {
		this.imgHostNumber = imgHostNumber;
	}

	public int getImgUrlStarNum() {
		return imgUrlStarNum;
	}

	public void setImgUrlStarNum(int imgUrlStarNum) {
		this.imgUrlStarNum = imgUrlStarNum;
	}
}
