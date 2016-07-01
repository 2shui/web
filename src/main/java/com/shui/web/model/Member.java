package com.shui.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 * @author <a href="mailto:zgjlovelife@gmail.com">ZGJ</a>
 * */
public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3957374852009524075L;
	
	private Long id;
	private String openId;		//第三方uid
	private String nickName;	//昵称
	private Date regTime;		//注册时间
	private String originType;	//注册来源
	private String gender;		//性别
	private String email;		//邮箱
	private String profileImage;//用戶頭像
	
	public Member() {}
	public Member(String openId, String nickName, Date regTime, String originType, String gender, String email, String profileImage) {
		this.openId = openId;
		this.nickName = nickName;
		this.regTime = regTime;
		this.originType = originType;
		this.gender = gender;
		this.email = email;
		this.profileImage = profileImage;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getOriginType() {
		return originType;
	}
	public void setOriginType(String originType) {
		this.originType = originType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	
}
