package com.adanac.framework.contexts;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * RequestContext类借鉴了Log4J的MDC的实现方法
 * 主要改变包括:
 * 对数据类型进行了规范 - 保存数据为(String, Object)格式
 * 增加了请求标识(Request ID)的处理方式
 * 与MDC功能类似，RequestContext提供了线程级别的环境变量的保存方法。子线程自动获得父线程的环境变量。
 * [注] 由于从线程池获取的线程不保证被清理过，使用时要在初始时刻调用reset()来清理原有变量并生成新的Request ID
 * 
 * @author adanac
 * @version 1.0
 */
public class RequestContext {
	public final static String REQUESTCONTEXT_KEY4REQUESTID = "requestId";

	final static RequestContext requestContext = new RequestContext();

	static final int HASHTABLE_SIZE = 8;

	private ValueMapPerThreadContext valueMapPerThreadContext;

	private RequestContext() {
		valueMapPerThreadContext = new ValueMapPerThreadContext();
	}

	/**
	 * 以标识(参数<code>key</code>)将数值(参数<code>value</code>)放入处理当前请求线程的上下文环境中
	 * @param key
	 * @param value
	 */
	static public void put(String key, Object value) {
		if (REQUESTCONTEXT_KEY4REQUESTID.equalsIgnoreCase(key))
			throw new RuntimeException("Not allowed for adding the key of \"" + REQUESTCONTEXT_KEY4REQUESTID + "\"");
		if (requestContext != null)
			requestContext.putInValueMap(key, value);
	}

	/**
	 * 从线程上下线文中获得指定标识(参数<code>key</code>)的数值
	 * @param key
	 * @return 值对象
	 */
	static public Object get(String key) {
		if (requestContext != null) {
			return requestContext.getFromValueMap(key);
		}
		return null;
	}

	/**
	 * 从线程上下线文中删除指定标识(参数<code>key</code>)的数值(除了Request ID)
	 * @param key
	 */
	static public void remove(String key) {
		if (REQUESTCONTEXT_KEY4REQUESTID.equalsIgnoreCase(key))
			throw new RuntimeException("Not allowed for removing the key of \"" + REQUESTCONTEXT_KEY4REQUESTID + "\"");
		if (requestContext != null) {
			requestContext.removeFromValueMap(key);
		}
	}

	/**
	 * 从线程上下线文中获得全部的数值,返回值为<code>Map</code>
	 * @return
	 */
	public static Map<String, Object> getAllValues() {
		if (requestContext != null) {
			return requestContext.getAllValuesFromValueMap();
		} else {
			return null;
		}
	}

	/**
	 * 清除线程上下线文中全部数值(除了Request ID)
	 */
	public static void clear() {
		if (requestContext != null) {
			String requestId = (String) requestContext.getFromValueMap(REQUESTCONTEXT_KEY4REQUESTID);
			requestContext.clearValueMap();
			if (requestId != null) {
				requestContext.putInValueMap(REQUESTCONTEXT_KEY4REQUESTID, requestId);
			}
		}
	}

	/**
	 * 获得线程上下线文中的当前的Request Id
	 * @return <code>Request Id<code>
	 */
	public static String getRequestId() {
		return (String) get(REQUESTCONTEXT_KEY4REQUESTID);
	}

	/**
	 * 清除线程上下线文中全部数值并生成新的<code>Request Id</code>
	 */
	public static void reset() {
		reset(null);
	}

	/**
	 * 清除线程上下线文中全部数值并集成<code>requestTracingEnabled</code>中的Request ID
	 * @param requestTracingEnabled 
	 */
	public static void reset(RequestTracingEnabled requestTracingEnabled) {
		clear();
		if (requestContext != null) {
			if (requestTracingEnabled != null && requestTracingEnabled.getRequestId() != null)
				requestContext.putInValueMap(REQUESTCONTEXT_KEY4REQUESTID, requestTracingEnabled.getRequestId());
			else
				requestContext.putInValueMap(REQUESTCONTEXT_KEY4REQUESTID, generateRequestId());
		}
	}

	private void putInValueMap(String key, Object value) {
		if (valueMapPerThreadContext == null) {
			return;
		} else {
			Hashtable<String, Object> valueMap4CurrentThread = valueMapPerThreadContext.get();
			if (valueMap4CurrentThread == null) {
				valueMap4CurrentThread = new Hashtable<String, Object>(HASHTABLE_SIZE);
				((ValueMapPerThreadContext) valueMapPerThreadContext).set(valueMap4CurrentThread);
			}
			valueMap4CurrentThread.put(key, value);
		}
	}

	private Object getFromValueMap(String key) {
		if (valueMapPerThreadContext == null) {
			return null;
		} else {
			Hashtable<String, Object> valueMap4CurrentThread = valueMapPerThreadContext.get();
			if (valueMap4CurrentThread != null && key != null) {
				return valueMap4CurrentThread.get(key);
			} else {
				return null;
			}
		}
	}

	private void removeFromValueMap(String key) {
		if (valueMapPerThreadContext != null) {
			Hashtable<String, Object> valueMap4CurrentThread = valueMapPerThreadContext.get();
			if (valueMap4CurrentThread != null) {
				valueMap4CurrentThread.remove(key);
			}
		}
	}

	private Map<String, Object> getAllValuesFromValueMap() {
		if (valueMapPerThreadContext == null) {
			return null;
		} else {
			return valueMapPerThreadContext.get();
		}
	}

	private void clearValueMap() {
		if (valueMapPerThreadContext != null) {
			Hashtable<String, Object> valueMap4CurrentThread = valueMapPerThreadContext.get();
			if (valueMap4CurrentThread != null) {
				valueMap4CurrentThread.clear();
			}
		}
	}

	private static String generateRequestId() {
		return "req-" + UUID.randomUUID().toString();
	}
}
