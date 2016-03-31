package com.adanac.framework.web.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class DatasWrappedOutputStream extends ServletOutputStream {
	private ByteArrayOutputStream buffer;

	public DatasWrappedOutputStream(ByteArrayOutputStream buffer) {
		this.buffer = buffer;
	}

	public void write(int b) throws IOException {
		buffer.write(b);
	}

	public byte[] toByteArray() {
		return buffer.toByteArray();
	}
}
