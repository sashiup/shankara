package com.proto.shankara.pojo;

import java.io.Serializable;

public class SFeed implements Serializable{
	private static final long serialVersionUID = 3512119072124572386L;
	
	protected int totalEntries;
	protected String title;
	protected String author;
	protected String content;
	
	public int getTotalEntries() {
		return totalEntries;
	}
	public void setTotalEntries(int totalEntries) {
		this.totalEntries = totalEntries;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "SFeed [getTotalEntries()=" + getTotalEntries()
				+ ", getTitle()=" + getTitle() + ", getAuthor()=" + getAuthor()
				+ ", getContent()=" + getContent() + "]";
	}
}