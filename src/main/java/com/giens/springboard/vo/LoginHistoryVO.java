package com.giens.springboard.vo;

public class LoginHistoryVO {
	
	private String rowNo;
	private String userID;
	private String loginTime;
	private String logoutTime;
	private String ipAddress;
	
	public LoginHistoryVO(String rowNo, String userID, String loginTime, String logoutTime, String ipAddress) {
		super();
		this.rowNo = rowNo;
		this.userID = userID;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.ipAddress = ipAddress;
	}
	
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Override
	public String toString() {
		return "LoginHistoryVO [rowNo=" + rowNo + ", userID=" + userID + ", loginTime=" + loginTime + ", logoutTime="
				+ logoutTime + ", ipAddress=" + ipAddress + "]";
	}
	
	

}
