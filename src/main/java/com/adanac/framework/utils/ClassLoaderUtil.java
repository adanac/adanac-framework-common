package com.adanac.framework.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderUtil {
	private static Method addURL;

	static {
		try {
			addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
		} catch (Exception e) {
			e.printStackTrace();
		}
		addURL.setAccessible(true);
	}

	public static URLClassLoader getSystemClassLoader() {
		return (URLClassLoader) ClassLoader.getSystemClassLoader();
	}

	public static URLClassLoader getExtClassLoader() {
		return (URLClassLoader) getSystemClassLoader().getParent();
	}

	public static URLClassLoader getThreadClassLoader() {
		return (URLClassLoader) Thread.currentThread().getContextClassLoader();
	}

	public static URL[] getSystemClassLoaderURLs() {
		return getSystemClassLoader().getURLs();
	}

	public static URL[] getExtClassLoaderURLs() {
		return getExtClassLoader().getURLs();
	}

	public static URL[] getThreadClassLoaderURLs() {
		return getThreadClassLoader().getURLs();
	}

	public static void addURL2SystemClassLoader(URL url) throws RuntimeException {
		try {
			addURL.invoke(getSystemClassLoader(), new Object[] { url });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void addURL2ExtClassLoader(URL url) throws RuntimeException {
		try {
			addURL.invoke(getExtClassLoader(), new Object[] { url });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void addURL2ThreadClassLoader(URL url) throws RuntimeException {
		try {
			addURL.invoke(getThreadClassLoader(), new Object[] { url });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void addSystemClassPath(String path) {
		addSystemClassPath(new File(path));
	}

	public static void addExtClassPath(String path) {
		addExtClassPath(new File(path));
	}

	public static void addClassPath(String path) {
		addClassPath(new File(path));
	}

	public static void addSystemClassPath(File dirOrJar) {
		try {
			addURL2SystemClassLoader(new URL("file", "", dirOrJar.getAbsolutePath()));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void addExtClassPath(File dirOrJar) {
		try {
			addURL2ExtClassLoader(new URL("file", "", dirOrJar.getAbsolutePath()));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void addClassPath(File dirOrJar) {
		try {
			addURL2ThreadClassLoader(new URL("file", "", dirOrJar.getAbsolutePath()));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
