package com.adanac.framework.utils;

/**
 * 对象操作工具类
 * @author adanac
 * @version 1.0
 */
public class ObjectUtil {

	/**
	 * 判断是否数组
	 * @param 判断的对象
	 * @return 判断结果
	 */
	public static boolean isArray(Object obj) {
		return (obj != null && obj.getClass().isArray());
	}

	/**
	 * 判断数组是否为空
	 * @param array 判断的数组
	 * @return 判断结果
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	* 
	* 判定一个对象是否为null or empty
	*
	* @param o Object
	* @return
	*/
	public static boolean isEmpty(Object o) {
		return o == null || String.valueOf(o).trim().length() == 0;
	}
}
