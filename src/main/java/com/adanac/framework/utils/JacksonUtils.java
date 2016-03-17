package com.adanac.framework.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jackson工具类
 * @author adanac
 * @version 1.0
 */
public class JacksonUtils {
	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtils.class);
	/**
	 * User-Agent
	 */
	private static final String USER_AGETN = "User-Agent";
	/**
	 * iphone
	 */
	private static final String IPHNOE = "iphone";
	/**
	 * ObjectMapper
	 */
	private ObjectMapper om = new ObjectMapper();

	private JacksonUtils() {
		initMapper();
	}

	/**
	 * 
	 * 
	 * 获取单例
	 */
	private static class Holder {
		private static final JacksonUtils JU_INSTANCE = new JacksonUtils();

	}

	/**
	 * 
	 * 获取JacksonUtils的实例
	 * 
	 * @return JacksonUtils
	 */
	public static JacksonUtils getInstance() {
		return Holder.JU_INSTANCE;
	}

	/**
	 * 
	 * 转换成json字符串 <br>
	 * 将java对象转换成json字符串
	 * 
	 * @param obj 需要转的java 对象
	 * @return String json 字符串
	 */
	public String obj2Json(Object obj) {
		String jsonStr = null;
		try {
			jsonStr = om.writeValueAsString(obj);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("parse object {} to json {}", obj, jsonStr);
			}
		} catch (JsonGenerationException e) {
			LOGGER.error("parse object {} to string error, {}", obj, e);
		} catch (JsonMappingException e) {
			LOGGER.error("parse object {} to string error, {}", obj, e);
		} catch (IOException e) {
			LOGGER.error("parse object {} to string error, {}", obj, e);
		}
		return jsonStr;
	}

	/**
	 * 
	 * 获取ObjectMapper
	 * 
	 * @return ObjectMapper
	 */
	public ObjectMapper getObjectMapper() {
		return om;
	}

	/**
	 * 
	 * 根据string获取JsonNode
	 * 
	 * @param json字符串
	 * @return JsonNode
	 */
	public JsonNode obj2JsonNode(String str) {
		JsonNode jn = null;
		try {
			jn = om.readTree(str);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("parse json String {} to JsonNode {}", str, jn);
			}
		} catch (JsonProcessingException e) {
			LOGGER.error("parse string {} to JsonNode error, {}", str, e);
		} catch (IOException e) {
			LOGGER.error("parse string {} to JsonNode error, {}", str, e);
		}
		return jn;
	}

	private void initMapper() {
		// 设置日期格式
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// 忽略目标对象中不存在的属性
		om.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		om.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 空值输出为""而不是null
		om.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
				arg1.writeString("");
			}
		});
	}

	/**
	 * 
	 * 将json字符串转换成java对象 <br>
	 * 将json字符串装成目标java对象
	 * 
	 * @param jsonStr json字符串
	 * 
	 * @param target 目标java对象
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public <T> T json2Object(String jsonStr, Class<T> target) {
		T t = null;
		if (jsonStr == null) {
			try {
				return target.newInstance();
			} catch (InstantiationException e) {
				LOGGER.error("create {} instance error. {}", target, e);
			} catch (IllegalAccessException e) {
				LOGGER.error("create {} instance error. {}", target, e);
			}
		}
		try {
			t = om.readValue(jsonStr, target);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("parse json String {} to object {}", jsonStr, target);
			}
		} catch (JsonParseException e) {
			LOGGER.error("parse string to object error,", e);
		} catch (JsonMappingException e) {
			LOGGER.error("parse string to object error,", e);
		} catch (IOException e) {
			LOGGER.error("parse string to object error,", e);
		}
		return t;
	}

	/**
	 * 
	 * 根据url获取jsonNode
	 * 
	 * @param url
	 * @return jsonnode
	 * @throw 异常描述
	 * @see 需要参见的其它内容
	 */
	public JsonNode getJsonNode(String url) {
		JsonNode jn = null;
		URL u = null;
		try {
			u = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) u.openConnection();
			httpConnection.setRequestProperty(USER_AGETN, IPHNOE);
			jn = om.readTree(httpConnection.getInputStream());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("parse url {} to JsonNode {}", url, jn);
			}
		} catch (JsonProcessingException e) {
			LOGGER.error("get jsonNode from {} error. {}", url, e);
		} catch (IOException e) {
			LOGGER.error("get jsonNode from {} error. {}", url, e);
		}
		return jn;
	}

	/**
	 * 
	 * 功能描述：jsonNode转java对象 输入参数：<按照参数定义顺序>
	 * 
	 * @param 参数说明 jn jsonNode， target 目标java对象 返回值: 类型 <说明>
	 * @return 返回值 java对象 target类型
	 */
	public <T> T json2Object(JsonNode jn, Class<T> target) {
		T t = null;
		if (jn == null) {
			try {
				return target.newInstance();
			} catch (InstantiationException e) {
				LOGGER.error("create {} instance error. {}", target, e);
			} catch (IllegalAccessException e) {
				LOGGER.error("create {} instance error. {}", target, e);
			}
		}
		try {
			t = om.readValue(jn, target);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("parse JsonNode {} to object {}", jn, target);
			}
		} catch (JsonParseException e) {
			LOGGER.error("paser JsonNode to {} error. {}", target, e);
		} catch (JsonMappingException e) {
			LOGGER.error("paser JsonNode to {} error. {}", target, e);
		} catch (IOException e) {
			LOGGER.error("paser JsonNode to {} error. {}", target, e);
		}
		return t;
	}

	/**
	 * 
	 * jsonz字符串转list： 输入参数：<按照参数定义顺序>
	 * 
	 * @param 参数说明 jsonVal josn字符串, clazz list的泛型类型
	 * @return 返回值 ArrayList
	 * @throw 异常描述
	 */
	public <T> List<T> getList(String jsonVal, Class<?> clazz) {
		if (StringUtils.isEmpty(jsonVal)) {
			return null;
		}
		TypeFactory t = TypeFactory.defaultInstance();
		// 指定容器结构和类型（这里是ArrayList和clazz）
		List<T> list = null;
		try {
			list = om.readValue(jsonVal, t.constructCollectionType(ArrayList.class, clazz));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("parse json {} to List<{}> {}", new Object[] { jsonVal, clazz, list });
			}
		} catch (JsonParseException e) {
			LOGGER.error("paser json string {} to List<{}> error. {}", new Object[] { jsonVal, clazz, e });
		} catch (JsonMappingException e) {
			LOGGER.error("paser json string {} to List<{}> error. {}", new Object[] { jsonVal, clazz, e });
		} catch (IOException e) {
			LOGGER.error("paser json string {} to List<{}> error. {}", new Object[] { jsonVal, clazz, e });
		}
		return list;
	}

	/**
	 * 
	 * jsonz字符串转list： 输入参数：<按照参数定义顺序>
	 * 
	 * @param 参数说明 jn JosnNode字符串， clazz list的泛型类型
	 * @return 返回值 ArrayList
	 * @throw 异常描述
	 */
	public <T> List<T> getList(JsonNode jn, Class<?> clazz) {
		if (jn == null) {
			return null;
		}
		TypeFactory t = TypeFactory.defaultInstance();
		// 指定容器结构和类型（这里是ArrayList和clazz）
		List<T> list = null;
		try {
			list = om.readValue(jn, t.constructCollectionType(ArrayList.class, clazz));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("parse JsonNode {} to List<{}> {}", new Object[] { jn, clazz, list });
			}
		} catch (JsonParseException e) {
			LOGGER.error("paser JsonNode {} to List<{}> error. {}", new Object[] { jn, clazz, e });
		} catch (JsonMappingException e) {
			LOGGER.error("paser JsonNode {} to List<{}> error. {}", new Object[] { jn, clazz, e });
		} catch (IOException e) {
			LOGGER.error("paser JsonNode {} to List<{}> error. {}", new Object[] { jn, clazz, e });
		}
		return list;
	}
}
