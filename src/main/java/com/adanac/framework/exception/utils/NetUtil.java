package com.adanac.framework.exception.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 获取主机名的工具类
 * @author adanac
 * @version 1.0
 */
public class NetUtil {
	private static Logger logger = LoggerFactory.getLogger(NetUtil.class);

	public static List<String> getAllLocalIP() {
		List<String> localIPs = new ArrayList<String>();
		Enumeration<NetworkInterface> allNetInterfaces = null;
		InetAddress ip = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && !ip.isLoopbackAddress() && ip instanceof Inet4Address) {
						localIPs.add(ip.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
		}
		return localIPs;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(StringUtils.collectionToDelimitedString(getAllLocalIP(), ","));
	}
}
