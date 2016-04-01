package com.adanac.framework.web.timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adanac.framework.web.gson.GsonView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class RemoteJobController {

	private static String taskServletPath = DESUtil.CALLBACKROOT;

	private Logger logger = LoggerFactory.getLogger(RemoteJobController.class);

	private String address = "";

	private String taskName;

	private HttpServletRequest request;

	private HttpServletResponse response;

	/**
	 * 验证远程调用
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	protected boolean doValid(HttpServletRequest request, HttpServletResponse response) {
		boolean isLostPort = StringUtils.isEmpty(request.getParameter("port"));
		boolean isLostTaskName = StringUtils.isEmpty(request.getParameter("taskName"));
		boolean isLostSysCode = StringUtils.isEmpty(request.getParameter("sysCode"));
		boolean isLostKey = StringUtils.isEmpty(request.getParameter("key"));

		if (isLostPort || isLostTaskName || isLostSysCode || isLostKey) {
			logger.error("Lost transfer parameter!");
			return false;
		} else if (!this.validatStr(request.getParameter("sysCode"), request.getParameter("key"))) {
			logger.error("url validate fail!");
			return false;
		} else {

			logger.info("url validate success");
			address = "http://" + request.getRemoteAddr();
			address += ":" + request.getParameter("port");
			address += "/" + taskServletPath;
			// address += "?taskName=" + request.getParameter("taskName");
			this.taskName = request.getParameter("taskName");

			this.taskName = request.getParameter("taskName");
			return true;
		}
	}

	private boolean validatStr(String str, String key) {

		String sysCode = DESUtil.decode(str, key);
		if (sysCode.equals(DESUtil.SYSTEMCODE)) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/sync/execute")
	protected GsonView syncExecute(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		try {
			if (!doValid(request, response)) {
				throw new Exception();
			}
			InvokeMessage invokeMessage = executeTask();
			return this.returnView(invokeMessage.isSuccess() ? RemoteResultEnum.SUCCESS : RemoteResultEnum.FAIL,
					invokeMessage.getMessage());
		} catch (Exception e) {
			return this.returnView(RemoteResultEnum.FAIL, "执行失败 " + e.getMessage());
		}
	}

	@RequestMapping(value = "/async/execute")
	protected GsonView asyncExecute(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		InvokeMessage invokeMessage = null;
		try {
			if (!doValid(request, response)) {
				throw new Exception();
			}
			invokeMessage = executeTask();
		} catch (Exception e) {
			invokeMessage = new InvokeMessage(false, "执行失败 " + e.getMessage());
		}
		callback(invokeMessage);
		return new GsonView();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract InvokeMessage executeTask() throws Exception;

	protected GsonView returnView(RemoteResultEnum result, String message) {
		GsonView gson = new GsonView();
		gson.addStaticAttribute("result", result.getName());
		gson.addStaticAttribute("message", message);
		return gson;
	}

	protected String callback(InvokeMessage invokeMessage) {
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(address);
		try {
			Gson json = new Gson();
			JsonObject element = new JsonObject();
			element.addProperty("result",
					invokeMessage.isSuccess() ? RemoteResultEnum.SUCCESS.getName() : RemoteResultEnum.FAIL.getName());
			element.addProperty("message", invokeMessage.getMessage());

			StringBuffer param = new StringBuffer();
			param.append("msg").append("=").append(json.toJson(element)).append("&");
			param.append("taskName").append("=").append(this.taskName);

			StringEntity stringEntity = new StringEntity(param.toString(), "UTF-8");
			httpPost.setEntity(stringEntity);
			httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"));
			client.execute(httpPost);
		} catch (Exception e) {
			logger.error("call task status action error!", e);
		} finally {
			client.getConnectionManager().shutdown();
		}
		return null;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public static void main(String[] args) {
		String key = DESUtil.genDESKey();
		String strEncode = DESUtil.encode(DESUtil.SYSTEMCODE, key);
		System.out.println(key + " " + strEncode);
	}
}
