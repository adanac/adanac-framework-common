package com.adanac.framework.web.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 整站压缩过滤器
 * @author adanac
 * @version 1.0
 */
public class GZIPFilter implements Filter {
	private static final String INCLUDE_SUFFIXS_NAME = "includeSuffixs";
	private static final String[] DEFAULT_INCLUDE_SUFFIXS = { ".js", ".css", ".html", ".htm", ".gif", ".jpg" };
	private static String[] includeSuffixs = null;

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		String includeSuffixStr = filterconfig.getInitParameter(INCLUDE_SUFFIXS_NAME);
		if (StringUtils.isNotBlank(includeSuffixStr)) {
			includeSuffixs = includeSuffixStr.split(",");
			// 处理匹配字符串为". 后缀名"
			for (int i = 0; i < includeSuffixs.length; i++) {
				includeSuffixs[i] = "." + includeSuffixs[i];
			}
		} else {
			includeSuffixs = DEFAULT_INCLUDE_SUFFIXS;
		}
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {

		if (shouldNotFilter((HttpServletRequest) servletrequest)) {
			HttpServletResponse resp = (HttpServletResponse) servletresponse;
			GZipDatasWrapper wrapper = new GZipDatasWrapper(resp);
			filterchain.doFilter(servletrequest, wrapper);
			byte[] gzipData = gzip(wrapper.getResponseData());
			resp.addHeader("Content-Encoding", "gzip");
			resp.setContentLength(gzipData.length);

			ServletOutputStream output = servletresponse.getOutputStream();
			output.write(gzipData);
			output.flush();
		} else {
			filterchain.doFilter(servletrequest, servletresponse);
		}

	}

	@Override
	public void destroy() {

	}

	private byte[] gzip(byte[] data) {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10240);
		GZIPOutputStream output = null;
		try {
			output = new GZIPOutputStream(byteOutput);
			output.write(data);
		} catch (IOException e) {

		} finally {
			try {
				output.close();
			} catch (IOException e) {
			}
		}
		return byteOutput.toByteArray();

	}

	/**   *过滤控制函数,对指定后缀名的请求进行gzip压缩. 
	 */
	protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
		String path = request.getServletPath();
		boolean isCompression = false;
		for (String suffix : includeSuffixs) {
			if (path.endsWith(suffix)) {
				isCompression = true;
				break;
			}

		}
		return isCompression;
	}
}