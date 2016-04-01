package com.adanac.framework.web.timer;

public enum RemoteResultEnum {
	FAIL("F"), SUCCESS("S");
	private String name;

	private RemoteResultEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
