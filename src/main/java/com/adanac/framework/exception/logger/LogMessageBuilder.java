package com.adanac.framework.exception.logger;

import static com.adanac.framework.exception.model.ConfigConstant.LINE_SEPARATOR;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.adanac.framework.statistics.VersionStatistics;

/**
 * 异常日志信息的Builders
 * @author adanac
 * @version 1.0
 */
public class LogMessageBuilder {
	static {
		VersionStatistics.reportVersion(LogMessageBuilder.class);
	}

	public static String build(String message, Throwable thr) {
		StringBuilder sb = new StringBuilder(LINE_SEPARATOR).append(message).append(LINE_SEPARATOR);
		String stackTrace = getExceptionStackTrace(thr);
		sb.append(stackTrace);

		return sb.toString();
	}

	public static String getExceptionStackTrace(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		pw.close();
		return sw.toString();
	}
}
