package com.adanac.framework.lang.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class EventUtils {
	private static final Logger logger = LoggerFactory.getLogger(EventUtils.class);

	public static final int LINE_NA = -1;

	private static final String SNFLOGGER_BOUNDARY = "com.suning.framework.lang2.SnfLogger";

	private static final String SPRING_AOP_BOUNDARY = "org.springframework.aop.framework.Cglib2AopProxy";

	private static final String DEFAULT_SERVER_NAME = "NOT_WEB_SERVER";

	private static final String NOP_FUN_NAME = "?";

	public static final EventGson EVENT_GSON = new EventGson();

	private static String hostIp; // 主机ip

	private static String hostName; // 主机名

	private static String serverName; // 服务器名称

	static {
		serverName = getServerNameInit();
	}

	public static String getServerName() {
		return serverName;
	}

	public static String getHostIp() {
		if (hostIp == null) {
			List<String> ips = new ArrayList<String>();
			Enumeration<NetworkInterface> netInterfaces = null;
			try {
				netInterfaces = NetworkInterface.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface netInterface = netInterfaces.nextElement();
					Enumeration<InetAddress> inteAddresses = netInterface.getInetAddresses();
					while (inteAddresses.hasMoreElements()) {
						InetAddress inetAddress = inteAddresses.nextElement();
						if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
							ips.add(inetAddress.getHostAddress());
						}
					}
				}
			} catch (SocketException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("SocketExcetion while get host ip, can not get host ip!");
				}
			}
			hostIp = StringUtils.collectionToDelimitedString(ips, ",");
		}
		return hostIp;
	}

	public static String getHostName() {
		if (hostName == null) {
			try {
				hostName = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				if (logger.isWarnEnabled()) {
					logger.warn("UnknownHostException while get host name, can not get host name!");
				}
			}
		}
		return hostName;
	}

	private static String getServerNameInit() {
		if (serverName == null) {
			try {
				Class<?> c = Class.forName("com.ibm.websphere.runtime.ServerName");
				Method m = c.getMethod("getDisplayName", new Class<?>[0]);
				Object o = m.invoke(c, new Object[0]);
				serverName = o.toString();
			} catch (Exception ex) {
				logger.warn("can not get webspher server name, then try to get jboss's!");
				serverName = System.getProperty("jboss.server.name");
				if (null == serverName) {
					serverName = DEFAULT_SERVER_NAME;
					logger.warn("can not get jboss server name, then return the default value!");
				}
			}
		}
		return serverName;
	}

	public static String getCaller() {
		Throwable t = new Throwable();
		StackTraceElement[] steArray = t.getStackTrace();
		int found = LINE_NA;
		for (int i = 0; i < steArray.length; i++) {
			if (steArray[i].getClassName().startsWith(SNFLOGGER_BOUNDARY)
					|| steArray[i].getClassName().startsWith(SPRING_AOP_BOUNDARY)) {
				// the caller is assumed to be the next stack frame, hence the
				// +1.
				found = i + 1;
			} else {
				if (found != LINE_NA) {
					break;
				}
			}
		}
		if (found == LINE_NA) {
			return NOP_FUN_NAME;
		}

		StackTraceElement ste = steArray[found];
		String className = ste.getClassName();
		String methodName = ste.getMethodName();
		return className + "#" + methodName;
	}

	/**
	 * 得到指定异常的rootCause
	 * 如果没有rootCause,则把参数指定的异常作为rootCause, 以免返回null
	 * 
	 * @param ex
	 * @return
	 */
	public static Throwable getMostSpecificCause(Throwable ex) {
		Throwable rootCause = getRootCause(ex);
		return (rootCause != null ? rootCause : ex);
	}

	/**
	 * 得到指定异常的rootCause
	 * 
	 * @param ex
	 * @return
	 */
	public static Throwable getRootCause(Throwable ex) {
		Throwable rootCause = null;
		Throwable cause = ex.getCause();
		while (cause != null && cause != rootCause) {
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause;
	}

	public static String getExceptionStackTrace(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			ex.printStackTrace(pw);
			pw.close();
		} catch (Exception e) {
			logger.warn("Transfer exception stack trace error!! Will return \"\"");
		}
		return sw.toString();
	}
}
