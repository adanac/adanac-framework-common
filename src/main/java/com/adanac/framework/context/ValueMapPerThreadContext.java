package com.adanac.framework.context;

import java.util.Hashtable;

/**
 * 参照了Log4J的ThreadLocalMap 是 {@link InheritableThreadLocal}的扩展
 * @author adanac
 * @version 1.0
 */
public class ValueMapPerThreadContext extends InheritableThreadLocal<Hashtable<String, Object>> {

	@SuppressWarnings("unchecked")
	public final Hashtable<String, Object> childValue(Hashtable<String, Object> parentValue) {
		Hashtable<String, Object> ht = (Hashtable<String, Object>) parentValue;
		if (ht != null) {
			return (Hashtable<String, Object>) ht.clone();
		} else {
			return null;
		}
	}
}
