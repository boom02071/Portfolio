package com.ssafy.vue.dto;

public class Member {
	private String userid;
	private String userpasswd;
	private String username;
	private String email;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpasswd() {
		return userpasswd;
	}
	public void setUserpasswd(String userpasswd) {
		this.userpasswd = userpasswd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", userpasswd=" + userpasswd + ", username=" + username + ", email=" + email
				+ "]";
	}
	public Member() {
	}
	public Member(String userid, String userpasswd, String username, String email) {
		super();
		this.userid = userid;
		this.userpasswd = userpasswd;
		this.username = username;
		this.email = email;
	}
	
}
