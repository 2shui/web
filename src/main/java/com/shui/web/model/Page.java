package com.shui.web.model;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String site;
	private String title;
	private String content;
	private String uri;
	private int hits;
	private int enjoy;
	private String fileName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getEnjoy() {
		return enjoy;
	}
	public void setEnjoy(int enjoy) {
		this.enjoy = enjoy;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
