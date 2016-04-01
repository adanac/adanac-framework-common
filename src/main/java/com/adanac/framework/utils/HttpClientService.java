package com.adanac.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientService {
	private static Logger logger = LoggerFactory.getLogger(HttpClientService.class);

	private static int maxTotalConn = 128;
	private static int maxConnPerRoute = 32;

	/** 默认等待连接建立超时，单位:毫秒 */
	private static int CONN_TIME_OUT = 1000;

	/** 默认等待数据返回超时，单位:毫秒 */
	private static int SO_TIME_OUT = 5000;

	private static final String PARAMETER_SEPARATOR = "&";

	private static final String NAME_VALUE_SEPARATOR = "=";

	private static final String DEFAULT_CONTENT_ENCODING = "UTF-8";
	/**
	 * 线程安全的HttpClient连接管理器
	 */
	private static ThreadSafeClientConnManager connectionManager = null;

	/**
	 * 初始化httpclient的连接管理器
	 */
	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

		HttpParams params = new BasicHttpParams();
		params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxTotalConn);
		ConnPerRouteBean connPerRouteBean = new ConnPerRouteBean(maxConnPerRoute);
		params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, connPerRouteBean);

		connectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);
		// try {
		// connectionManager.setMaxTotal(maxTotalConn);
		// } catch (NumberFormatException e) {
		// throw new
		// RuntimeException("Key[httpclient.max_total] Not Found in
		// systemConfig.properties",
		// e);
		// }
		// // 每条通道的并发连接数设置（连接池）
		// try {
		// connectionManager.setDefaultMaxPerRoute(maxConnPerRoute);
		// } catch (NumberFormatException e) {
		// throw new
		// RuntimeException("Key[httpclient.default_max_connection] Not Found in
		// systemConfig.properties",
		// e);
		// }
	}

	/**
	 * 以Get方式执行http请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws UnsupportedEncodingException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws RscException
	 */
	public static String executeGet(final String url, String enchode, int timeOut, int soTimeOut)
			throws UnsupportedEncodingException, IllegalStateException, IOException {
		String result = execute(new HttpGet(url), enchode, timeOut, soTimeOut);
		logger.debug("[executeGet()]: [url={" + url + "}]: [response={" + result + "}] send successful!");
		return result;
	}

	public static String executeGet(final String url)
			throws UnsupportedEncodingException, IllegalStateException, IOException {
		return executeGet(url, null, 0, 0);
	}

	/**
	 * 以Post方式执行http请求
	 * 
	 * @param url
	 * @param params
	 * @param encoding
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws RscException
	 */
	public static String executePost(String url, List<? extends NameValuePair> params, String encoding,
			boolean isUrlEncode, int connTimeOut, int soTimeOut)
					throws IOException, SocketTimeoutException, HttpHostConnectException {
		if (encoding == null) {
			encoding = DEFAULT_CONTENT_ENCODING;
		}
		if (connTimeOut == 0) {
			connTimeOut = CONN_TIME_OUT;
		}
		if (soTimeOut == 0) {
			soTimeOut = SO_TIME_OUT;
		}
		HttpPost httpPost = new HttpPost(url);
		StringEntity reqEntity;
		if (isUrlEncode) {
			reqEntity = new UrlEncodedFormEntity(params, encoding);
		} else {
			reqEntity = createRequestStringEntity(params, encoding);
		}
		httpPost.setEntity(reqEntity);
		String result = execute(httpPost, encoding, connTimeOut, soTimeOut);

		logger.info("[executePost()]: [url={" + url + "}]: [response={" + result + "}] send successful!");
		return result;
	}

	public static String executePost(String url, List<? extends NameValuePair> params, final boolean isUrlEncode)
			throws IOException, SocketTimeoutException, HttpHostConnectException {
		return executePost(url, params, null, isUrlEncode, 0, 0);
	}

	public static String executePost(String url, List<? extends NameValuePair> params)
			throws IOException, SocketTimeoutException, HttpHostConnectException {
		return executePost(url, params, false);
	}

	public static String executePost(String url, String params, String encoding, int timeOut, int soTimeOut)
			throws IOException, SocketTimeoutException, HttpHostConnectException {
		if (encoding == null) {
			encoding = DEFAULT_CONTENT_ENCODING;
		}
		if (timeOut == 0) {
			timeOut = CONN_TIME_OUT;
		}
		if (soTimeOut == 0) {
			soTimeOut = SO_TIME_OUT;
		}
		HttpPost httpPost = new HttpPost(url);
		StringEntity reqEntity = new StringEntity(params, encoding);
		httpPost.setEntity(reqEntity);
		String result = execute(httpPost, encoding, timeOut, soTimeOut);
		logger.info("[HttpClientTemplate:executePost()]: [url={" + url + "}]: [response={" + result
				+ "}] send successful!");
		return result;
	}

	public static String executePost(final String url, final String params)
			throws IOException, SocketTimeoutException, HttpHostConnectException {
		return executePost(url, params, null, 0, 0);
	}

	/**
	 * 创建不进行url encode的请求具体内容
	 * 
	 * @param params
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static StringEntity createRequestStringEntity(final List<? extends NameValuePair> params,
			final String encoding) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		for (final NameValuePair parameter : params) {
			final String encodedName = parameter.getName();
			final String value = parameter.getValue();
			final String encodedValue = value != null ? value : "";
			if (result.length() > 0)
				result.append(PARAMETER_SEPARATOR);
			result.append(encodedName);
			result.append(NAME_VALUE_SEPARATOR);
			result.append(encodedValue);
		}
		StringEntity reqEntity = new StringEntity(result.toString(), encoding);
		return reqEntity;
	}

	/**
	 * 传入HttpRequest以执行http请求
	 * 
	 * @param httpReq
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws UnsupportedEncodingException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws RscException
	 */
	private static String execute(HttpRequestBase httpReq, String enchode, int timeOut, int soTimeOut) {
		HttpClient httpclient = getHttpClient(timeOut, soTimeOut);
		StringBuilder sb = new StringBuilder();

		try {
			HttpResponse response = httpclient.execute(httpReq);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				logger.info("Response content length: " + entity.getContentLength());
			}
			if (enchode == null || "".equals(enchode)) {
				enchode = DEFAULT_CONTENT_ENCODING;
			}
			// 显示结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), enchode));
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			if (entity != null) {
				entity.consumeContent();
			}

		} catch (SocketTimeoutException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			// 释放HttpClient连接
			if (httpReq != null) {
				httpReq.abort();
			}

		}

		return sb.toString();

	}

	/**
	 * 以流的方式向服务端post Xml字符串
	 * 
	 * @throws RscException
	 */
	public static String postMsgStream(String url, String msg, String enCode, int timeOut, int soTimeOut)
			throws SocketTimeoutException, ConnectTimeoutException, ConnectException {

		if (enCode == null) {
			enCode = DEFAULT_CONTENT_ENCODING;
		}
		if (timeOut == 0) {
			timeOut = CONN_TIME_OUT;
		}
		if (soTimeOut == 0) {
			soTimeOut = SO_TIME_OUT;
		}
		StringBuffer result = new StringBuffer();
		HttpClient httpclient = getHttpClient(timeOut, soTimeOut);

		HttpPost httppost = new HttpPost(url);

		try {
			StringEntity myEntity = new StringEntity(msg, enCode);
			httppost.addHeader("Content-Type", "text/xml");
			httppost.setEntity(myEntity);

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity resEntity = response.getEntity();
			InputStreamReader reader = new InputStreamReader(resEntity.getContent(), enCode);
			char[] buff = new char[1024];
			int length = 0;
			while ((length = reader.read(buff)) != -1) {
				result.append(buff, 0, length);
				;
			}
		} catch (SocketTimeoutException e) {
			throw e;
		} catch (ConnectTimeoutException e) {
			throw e;
		} catch (ConnectException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			// 释放HttpClient连接
			if (httppost != null) {
				httppost.abort();
			}
		}

		logger.info("[postMsgStream()]: [url={" + url + "}]:[message={" + msg + "}]:[response={" + result
				+ "}] send successful!");

		return result.toString();
	}

	/**
	 * 以流的方式向服务端post字符串
	 */
	public static String postMsgStream(String url, String msg, String enCode) throws Exception {

		return postMsgStream(url, msg, enCode, 0, 0);
	}

	public static String postMsgStream(String url, String msg) throws Exception {

		return postMsgStream(url, msg, null);
	}

	public static HttpClient getHttpClient(int timeOut, int soTimeout) {
		if (timeOut == 0) {
			timeOut = CONN_TIME_OUT;
		}
		if (soTimeout == 0) {
			soTimeout = SO_TIME_OUT;
		}

		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeOut);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
		DefaultHttpClient httpclient = new DefaultHttpClient(connectionManager, params);
		return httpclient;
	}

	public static void release() {
		if (connectionManager != null) {
			connectionManager.shutdown();
		}
	}

	/**
	 * 用https协议调用Commerce接口
	 * 
	 * @param url 请求url
	 * @param request request对象
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String callCommerceInterface(String url, HttpServletRequest request)
			throws NoSuchAlgorithmException, KeyManagementException, ClientProtocolException, IOException {
		logger.info("[callCommerceInterface()]: [url={" + request + "}]");
		// First create a trust manager that won't care.
		X509TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// Don't do anything.
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// Don't do anything.
			}

			public X509Certificate[] getAcceptedIssuers() {
				// Don't do anything.
				return null;
			}

		};
		// Now put the trust manager into an SSLContext.
		SSLContext sslcontext = SSLContext.getInstance("SSL");
		sslcontext.init(null, new TrustManager[] { trustManager }, null);

		// Use the above SSLContext to create your socket factory
		// (I found trying to extend the factory a bit difficult due to a
		// call to createSocket with no arguments, a method which doesn't
		// exist anywhere I can find, but hey-ho).
		SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sf, 443));

		HttpPost httpPost = new HttpPost(url);
		String result = "";
		// Execute HTTP request
		httpPost.setHeader("Authorization", "basic " + "dGNsb3VkYWRtaW46dGNsb3VkMTIz");
		httpPost.setHeader("Cookie", request.getHeader("Cookie"));
		httpPost.setHeader("Content-type", "application/xml");
		httpPost.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (iPhone; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		httpclient.getParams().setParameter(HttpHeaders.USER_AGENT,
				"Mozilla/5.0 (iPhone; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		HttpResponse response = httpclient.execute(httpPost);

		HttpEntity resEntity = response.getEntity();
		InputStreamReader reader = new InputStreamReader(resEntity.getContent());

		char[] buff = new char[1024];
		int length = 0;
		while ((length = reader.read(buff)) != -1) {
			result += new String(buff, 0, length);
		}
		httpclient.getConnectionManager().shutdown();

		// logger.info("callMemberInfoInterface >>>:" + new
		// String(result.getBytes("GBK"), "UTF-8"));
		return result;
	}

	/**
	 * 调用Commerce客户端的GET接口，调用当前方法
	 * 
	 * @param url 请求完整的url
	 * @return json格式字符串
	 */
	public static String callCommerceGetInterf(String url) {
		logger.info("[callCommerceGetInterf()]: [url={" + url + "}]");
		HttpRequestBase httpReq = new HttpGet(url);

		httpReq.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (iPhone; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		HttpClient httpclient = getHttpClient(1000, 5000);

		StringBuilder sb = new StringBuilder();

		try {
			HttpResponse response = httpclient.execute(httpReq);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				logger.info("Response content length: " + entity.getContentLength());
			}

			// 显示结果
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(entity.getContent(), DEFAULT_CONTENT_ENCODING));
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			if (entity != null) {
				entity.consumeContent();
			}

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			// 释放HttpClient连接
			if (httpReq != null) {
				httpReq.abort();
			}
		}

		return sb.toString();
	}

	public static String callCommerceGetInterf(String url, HttpServletRequest request)
			throws NoSuchAlgorithmException, KeyManagementException, ClientProtocolException, IOException {
		X509TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// Don't do anything.
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// Don't do anything.
			}

			public X509Certificate[] getAcceptedIssuers() {
				// Don't do anything.
				return null;
			}

		};
		// Now put the trust manager into an SSLContext.
		SSLContext sslcontext = SSLContext.getInstance("SSL");
		sslcontext.init(null, new TrustManager[] { trustManager }, null);

		// Use the above SSLContext to create your socket factory
		// (I found trying to extend the factory a bit difficult due to a
		// call to createSocket with no arguments, a method which doesn't
		// exist anywhere I can find, but hey-ho).
		SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sf, 443));

		HttpPost httpPost = new HttpPost(url);
		String result = "";
		// Execute HTTP request
		httpPost.setHeader("Authorization", "basic " + "dGNsb3VkYWRtaW46dGNsb3VkMTIz");
		httpPost.setHeader("Cookie", request.getHeader("Cookie"));
		httpPost.setHeader("Content-type", "application/xml");
		httpPost.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (iPhone; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		httpclient.getParams().setParameter(HttpHeaders.USER_AGENT,
				"Mozilla/5.0 (iPhone; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		HttpResponse response = httpclient.execute(httpPost);

		HttpEntity resEntity = response.getEntity();
		InputStreamReader reader = new InputStreamReader(resEntity.getContent());

		char[] buff = new char[1024];
		int length = 0;
		while ((length = reader.read(buff)) != -1) {
			result += new String(buff, 0, length);
		}
		httpclient.getConnectionManager().shutdown();

		// logger.info("callMemberInfoInterface >>>:" + new
		// String(result.getBytes("GBK"), "UTF-8"));
		return result;
	}
}
