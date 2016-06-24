package com.shui.web.model;

import java.io.Serializable;

/**
 * 读书笔记实体
 * @author <a href="mailto:zgjlovelife@gmail.com">ZGJ</a>
 * */
public class Marks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1893480496989872926L;
	
	private Long id;
	private Long customerId;
	private String bookName;
	private int bookNum;
	private String markText;
	private String note;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookNum() {
		return bookNum;
	}
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public String getMarkText() {
		return markText;
	}
	public void setMarkText(String markText) {
		this.markText = markText;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
