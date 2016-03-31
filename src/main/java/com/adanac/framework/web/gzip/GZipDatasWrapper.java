package com.adanac.framework.web.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GZipDatasWrapper extends HttpServletResponseWrapper {

	public static final int OUT_NONE = 0;
	public static final int OUT_WRITER = 1;
	public static final int OUT_STREAM = 2;
	private int outputType = OUT_NONE;

	private ServletOutputStream output = null;
	private PrintWriter writer = null;

	private ByteArrayOutputStream buffer = null;

	public GZipDatasWrapper(HttpServletResponse response) {
		super(response);

	}

	public PrintWriter getWriter() throws IOException {
		if (outputType == OUT_STREAM) {
			throw new IllegalStateException();
		} else if (outputType == OUT_WRITER) {
			return writer;

		} else {
			outputType = OUT_WRITER;
			writer = new PrintWriter(new OutputStreamWriter(buffer, getCharacterEncoding()));
			return writer;
		}

	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (outputType == OUT_WRITER) {
			throw new IllegalStateException();
		} else if (outputType == OUT_STREAM) {
			return output;
		} else {
			outputType = OUT_STREAM;
			output = new DatasWrappedOutputStream(buffer);
			return output;
		}
	}

	public void flushBuffer() throws IOException {
		if (outputType == OUT_WRITER)
			writer.flush();
		if (outputType == OUT_STREAM)
			output.flush();
	}

	public void reset() {
		outputType = OUT_NONE;
		buffer.reset();

	}

	public void finalize() throws Throwable {
		super.finalize();
		output.close();
		writer.close();
	}

	public byte[] getResponseData() throws IOException {
		flushBuffer();
		return buffer.toByteArray();
	}
}
