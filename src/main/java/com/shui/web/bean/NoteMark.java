package com.shui.web.bean;

import java.io.Serializable;

/**
 * 读书笔记实体
 * @author <a href="mailto:zgjlovelife@gmail.com">ZGJ</a>
 * */
public class NoteMark implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1893480496989872926L;
	
	private Long id;
	private String nickName;		// not work?!
	private String customerName;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
