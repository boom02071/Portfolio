package com.ssafy.vue.dto;

import java.util.Date;

public class SidoCode {
	private String code;
	private String SidoName;
	public SidoCode(String code, String sidoname) {
		super();
		this.code = code;
		this.SidoName = sidoname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSidoname() {
		return SidoName;
	}
	public void setSidoname(String sidoname) {
		this.SidoName = sidoname;
	}
	@Override
	public String toString() {
		return "Sidocode [code=" + code + ", sidoname=" + SidoName + "]";
	}
}	