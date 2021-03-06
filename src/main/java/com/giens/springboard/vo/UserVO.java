package com.giens.springboard.vo;

import java.sql.Date;

public class UserVO {
	
	private String userID;
	private String password;
	private String name;
	private String email;
	private String regDate;
	private String active;
	private String delFlag;
	private String role;
	private String logID;
	private String session_id;
	private Date  limit_time;
	
	
	/*
	 * public UserVO(String userID, String password, String name, String email,
	 * String regDate, String active, String delFlag) { super(); this.userID =
	 * userID; this.password = password; this.name = name; this.email = email;
	 * this.regDate = regDate; this.active = active; this.delFlag = delFlag; }
	 */
	
	
	public String getUserID() {
		return userID;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Date getLimite_time() {
		return limit_time;
	}
	public void setLimite_time(Date limite_time) {
		this.limit_time = limite_time;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	
	
	@Override
	public String toString() {
		return "UserVO [userID=" + userID + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", regDate=" + regDate + ", active=" + active + ", delFlag=" + delFlag + ", role=" + role + ", logID="
				+ logID + "]";
	}	
}
