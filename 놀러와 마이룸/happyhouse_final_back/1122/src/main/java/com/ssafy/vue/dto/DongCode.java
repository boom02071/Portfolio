package com.ssafy.vue.dto;

public class DongCode {
	private String Guguncode;
	private String GugunName;
	private String code;
	private String DongName;
	public String getGuguncode() {
		return Guguncode;
	}
	public String getGugunName() {
		return GugunName;
	}
	public void setGugunName(String gugunName) {
		GugunName = gugunName;
	}
	public void setGuguncode(String guguncode) {
		Guguncode = guguncode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDongName() {
		return DongName;
	}
	public void setDongName(String dongName) {
		DongName = dongName;
	}
	public DongCode(String guguncode, String gugunName, String code, String dongName) {
		super();
		Guguncode = guguncode;
		GugunName = gugunName;
		this.code = code;
		DongName = dongName;
	}
	@Override
	public String toString() {
		return "DongCode [Guguncode=" + Guguncode + ", GugunName=" + GugunName + ", code=" + code + ", DongName="
				+ DongName + "]";
	}
	
	
	
	
}
