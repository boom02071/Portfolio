package com.ssafy.vue.dto;

public class GugunCode {
	private String code;
	private String sidoCode;
	private String GugunName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGugunName() {
		return GugunName;
	}
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public void setGugunName(String gugunName) {
		GugunName = gugunName;
	}
	public GugunCode(String code, String sidoCode, String gugunName) {
		super();
		this.code = code;
		this.sidoCode = sidoCode;
		GugunName = gugunName;
	}
	@Override
	public String toString() {
		return "GugunCode [code=" + code + ", sidoCode=" + sidoCode + ", GugunName=" + GugunName + "]";
	}
	
}
