package com.shui.recommend.bean;

import java.io.Serializable;
import java.util.List;

public class UserRecommend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4068657014155954779L;
	
	private Long userId;
	private String userName;
	private List<Score> commend;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Score> getCommend() {
		return commend;
	}
	public void setCommend(List<Score> commend) {
		this.commend = commend;
	}
	
}
