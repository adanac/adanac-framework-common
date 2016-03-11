package com.adanac.framework.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.adanac.framework.web.gson.GsonView;
import com.adanac.framework.web.utils.ParamsUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 基类controller
 * @author adanac
 * @version 1.0
 */
public class BaseController {
	static Logger log = LoggerFactory.getLogger(BaseController.class);
	public static final String VIEW = "view";
	public static final String LIST = "list";
	public static final String STATUS = "status";
	public static final String WARN = "warn";
	public static final String SUCCESS = "1";
	public static final String ERROR = "0";
	public static final String MESSAGE = "message";
	public static final String MESSAGES = "messages";
	public static final String CONTENT = "content";

	/**
	 * 错误码
	 */
	public static final String ERROR_CODE = "errorCode";
	/**
	 * 错误消息
	 */
	public static final String ERROR_MSG = "errorMsg";

	/**
	 * 响应代码
	 */
	public static final String RETCODE = "retCode";

	public static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	/**
	 * 参数列表 params
	 */
	protected Map<String, Object> params;

	/**
	 * 获取当前request请求参数
	 */
	public Map<String, Object> getParams() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return ParamsUtil.getParams(request);
	}

	/**
	 * 获取当前request请求参数
	 */
	public Map<String, Object> getParams(HttpServletRequest request) {
		return ParamsUtil.getParams(request);
	}

	public Map<String, Object> getParams(boolean isCheck) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return ParamsUtil.getParams(request, isCheck);
	}

	/**
	 * 获取当前request请求参数
	 */
	public Map<String, Object> getParams(HttpServletRequest request, boolean isCheck) {
		return ParamsUtil.getParams(request, isCheck);
	}

	/**
	 * AJAX输出，返回null
	 * 
	 * @param content
	 * @param type
	 * @return
	 */
	public String ajax(HttpServletResponse response, String content, String type) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			log.error("IOException:", e);
		}
		return null;
	}

	/**
	 * AJAX输出文本，返回null
	 * 
	 * @param text
	 * @return
	 */
	public String ajaxText(HttpServletResponse response, String text) {
		return ajax(response, text, "text/plain");
	}

	/**
	 * AJAX输出HTML，返回null
	 * 
	 * @param html
	 * @return
	 */
	public String ajaxHtml(HttpServletResponse response, String html) {
		return ajax(response, html, "text/html");
	}

	/**
	 * AJAX输出XML，返回null
	 * 
	 * @param xml
	 * @return
	 */
	public String ajaxXml(HttpServletResponse response, String xml) {
		return ajax(response, xml, "text/xml");
	}

	/**
	 * 根据字符串输出JSON，返回null
	 * 
	 * @param jsonString
	 * @return
	 */
	public String ajaxJson(HttpServletResponse response, String jsonString) {
		return ajax(response, jsonString, "text/html");
	}

	/**
	 * 根据Map输出JSON，返回null
	 * 
	 * @param jsonMap
	 * @return
	 */
	public String ajaxJson(HttpServletResponse response, Map<String, String> jsonMap) {
		return ajax(response, gson.toJson(jsonMap), "text/html");
	}

	/**
	 * 输出JSON警告消息，返回null
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxJsonWarnMessage(HttpServletResponse response, String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, WARN);
		jsonMap.put(MESSAGE, message);
		return ajax(response, gson.toJson(jsonMap), "text/html");
	}

	/**
	 * 输出JSON警告消息，返回null
	 * 
	 * @param messages
	 * @return
	 */
	public String ajaxJsonWarnMessages(HttpServletResponse response, List<String> messages) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS, WARN);
		jsonMap.put(MESSAGES, messages);
		return ajax(response, gson.toJson(jsonMap), "text/html");
	}

	/**
	 * 输出JSON成功消息，返回null
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxJsonSuccessMessage(HttpServletResponse response, String message) {

		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(MESSAGE, message);
		return ajax(response, gson.toJson(jsonMap), "text/html");
	}

	/**
	 * 输出JSON成功消息，返回null
	 * 
	 * @param messages
	 * @return
	 */
	public String ajaxJsonSuccessMessages(HttpServletResponse response, List<String> messages) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(MESSAGES, messages);
		return ajax(response, gson.toJson(jsonMap), "text/html");
	}

	/**
	 * 输出JSON错误消息，返回null
	 * 
	 * @param message
	 * @return
	 */
	public String ajaxJsonErrorMessage(HttpServletResponse response, String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, ERROR);
		jsonMap.put(MESSAGE, message);
		return ajax(response, gson.toJson(jsonMap), "text/html");
	}

	/**
	 * 输出JSON错误消息，返回null
	 * 
	 * @param messages
	 * @return
	 */
	public String ajaxJsonErrorMessages(HttpServletResponse response, List<String> messages) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUS, ERROR);
		jsonMap.put(MESSAGES, messages);
		return ajax(response, gson.toJson(jsonMap), "text/html");
	}

	/**
	 * 设置页面不缓存
	 */
	public void setResponseNoCache(HttpServletResponse response) {
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
	}

	/**
	 * 创建新的GsonView返回对象，并填充默认值
	 * 
	 * @return GsonView对象
	 */
	public GsonView createGsonView() {
		GsonView gsonView = new GsonView();
		gsonView.addStaticAttribute(STATUS, SUCCESS);
		gsonView.addStaticAttribute(ERROR_CODE, StringUtils.EMPTY);
		gsonView.addStaticAttribute(ERROR_MSG, StringUtils.EMPTY);
		return gsonView;
	}

	/**
	 * 根据返回的res初始化一个GsonView对象
	 * @param res
	 * @return
	 */
	public GsonView createGsonView(BaseResult res) {
		GsonView gsonView = new GsonView();
		gsonView.addStaticAttribute(STATUS, res.getStatus());
		gsonView.addStaticAttribute(ERROR_CODE, res.getErrorCode());
		gsonView.addStaticAttribute(ERROR_MSG, res.getErrorMsg());
		return gsonView;
	}

	/*
	 * 样例返回页面url(jsp,ftl,html等形式WEB页面)
	 *
	 */
	/*
	 * @RequestMapping("/samples01") public String
	 * getShopDailyReports(HttpServletRequest request) { Map<String, Object>
	 * param = getParams();
	 * 
	 * 
	 * 
	 * 
	 * request.setAttribute("parameter", param);
	 * 
	 * return "xx/xx/xxx.ftl"; }
	 */

	/*
	 * 返回JSON字符串
	 * 
	 */

	/*
	 * @RequestMapping("/private/sample03", method = RequestMethod.POST) public
	 * void mySample03(@RequestParam(defaultValue = "1") String currentPage,
	 * 
	 * @RequestParam(defaultValue = "10") String pageSize, String depStatus,
	 * HttpServletRequest request, HttpServletResponse response, Model model) {
	 * String userId = request.getRemoteUser();
	 * 
	 * //建议一般以List<Map<String,String>>形式返回到前端，这样可减少对返回数据的封装
	 * List<Map<String,String>> result =new ArrayList<Map<String,String>>();
	 * 
	 * // 业务处理 ajaxJson(response, JSONObject.fromObject(result).toString());
	 * 
	 * }
	 */

	/*
	 * 
	 * 返回ModelAndView<br>
	 * 
	 */

	/*
	 * @RequestMapping("/sample04", method = RequestMethod.POST) public
	 * ModelAndView mySample04(HttpServletRequest request, HttpServletResponse
	 * response, ModelAndView view) { view.setViewName("wap/pai/payagrement");
	 * PageCacheUtil.setCacheHeader(response, 3600);
	 * 
	 * 业务处理
	 * 
	 * return view; }
	 */
}
