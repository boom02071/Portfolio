package com.ssafy.vue.dto;

public class Shop {
	private int id;
	private String writer;
	private String title;
	private String content;
	private int star;
	private String shop;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	@Override
	public String toString() {
		return "Shop [id=" + id + ", writer=" + writer + ", title=" + title + ", content=" + content + ", star=" + star
				+ ", shop=" + shop + "]";
	}
	public Shop(int id, String writer, String title, String content, int star, String shop) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.star = star;
		this.shop = shop;
	}
	
}
