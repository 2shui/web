package com.shui.web.bean;

import java.io.Serializable;

import com.shui.web.model.Member;

public class Associator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1893480496989872926L;
	
	private String nickName;	//昵称
	private String gender;		//性别
	private String email;		//邮箱
	private String profileImage;//用戶頭像
	
	private String sign;
	
	public Associator() {}
	public Associator(Member member) {
		this.nickName = member.getNickName();
		this.gender = member.getGender();
		this.email = member.getEmail();
		this.profileImage = member.getProfileImage();
	}
	
	public Associator addSign(String sign) {
		return null;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
