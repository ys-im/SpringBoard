package com.giens.springboard.vo;

public class UserVO {
	
	private String userID;
	private String password;
	private String name;
	private String email;
	private String regDate;
	private String active;
	private String delFlag;
	
	
	public UserVO(String userID, String name, String email, String regDate, String active,
			String delFlag) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.regDate = regDate;
		this.active = active;
		this.delFlag = delFlag;
	}
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}


	@Override
	public String toString() {
		return "UserVO [userID=" + userID + ", name=" + name + ", email=" + email
				+ ", regDate=" + regDate + ", active=" + active + ", delFlag=" + delFlag + "]";
	}	
	
}
