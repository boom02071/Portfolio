package com.ssafy.vue.dto;

public class Heart {
	private int heartid;
	private String userid;
	private String aptname;
	private String guarantee;
	private String rent;
	private String date;
	private double fsize;
	private int floor;
	public int getHeartid() {
		return heartid;
	}
	public void setHeartid(int heartid) {
		this.heartid = heartid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAptname() {
		return aptname;
	}
	public void setAptname(String aptname) {
		this.aptname = aptname;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getFsize() {
		return fsize;
	}
	public void setFsize(double fsize) {
		this.fsize = fsize;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	@Override
	public String toString() {
		return "Heart [heartid=" + heartid + ", userid=" + userid + ", aptname=" + aptname + ", guarantee=" + guarantee
				+ ", rent=" + rent + ", date=" + date + ", fsize=" + fsize + ", floor=" + floor + "]";
	}
	public Heart(int heartid, String userid, String aptname, String guarantee, String rent, String date, double fsize,
			int floor) {
		super();
		this.heartid = heartid;
		this.userid = userid;
		this.aptname = aptname;
		this.guarantee = guarantee;
		this.rent = rent;
		this.date = date;
		this.fsize = fsize;
		this.floor = floor;
	}
	
	
	
	
}
