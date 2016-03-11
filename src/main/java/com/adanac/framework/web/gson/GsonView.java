package com.adanac.framework.web.gson;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class GsonView extends AbstractView {
	private String datePattern = "yyyy-MM-dd HH:mm:ss";
	/** Default content type. Overridable as bean property. */
	// private static final String DEFAULT_JSON_CONTENT_TYPE =
	// "application/json";
	private String jsonObjectName;

	public GsonView() {
		super();
	}

	public GsonView(String jsonObjectName, ExclusionStrategy excludeStrategy) {
		super();
		this.jsonObjectName = jsonObjectName;
		this.excludeStrategy = excludeStrategy;
	}

	private int responseStatus = HttpStatus.OK.value();
	private ExclusionStrategy excludeStrategy;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setStatus(getResponseStatus());
		response.setContentType(getContentType());
		GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat(datePattern);
		if (excludeStrategy != null) {
			gsonBuilder.setExclusionStrategies(excludeStrategy);
		}
		Gson gson = gsonBuilder.create();
		gson.toJson(jsonObjectName == null ? model : model.get(jsonObjectName), response.getWriter());
	}

	public String getJsonObjectName() {
		return jsonObjectName;
	}

	public void setDatePattern(String pattern) {
		this.datePattern = pattern;
	}

	public void setJsonObjectName(String objectName) {
		this.jsonObjectName = objectName;
	}

	public ExclusionStrategy getExcludeStrategy() {
		return excludeStrategy;
	}

	public void setExcludeStrategy(ExclusionStrategy excludeStrategy) {
		this.excludeStrategy = excludeStrategy;
	}

	@Override
	public String getContentType() {
		return "text/html;charset=utf-8";
	}

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
}
