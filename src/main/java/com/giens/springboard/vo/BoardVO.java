package com.giens.springboard.vo;

import java.util.Date;

import org.apache.ibatis.annotations.AutomapConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardVO {
	
	private String rowNo;
	private String title;
	private String contents;
	private String regDate;
	private String userID;
	private String boardNo;
	private String originNo;
	private String groupSeq;
	private String groupLayer;
	private String pBoardNo;
	private String groupPath;
	private String replyCnt;
	
	//Constructor		
	public BoardVO(String rowNo, String title, String contents, String regDate, String userID, String boardNo, String originNo,
			String groupSeq, String groupLayer, String pBoardNo, String groupPath, String replyCnt) {
		super();
		this.rowNo = rowNo;
		this.title = title;
		this.contents = contents;
		this.regDate = regDate;
		this.userID = userID;
		this.boardNo = boardNo;
		this.originNo = originNo;
		this.groupSeq = groupSeq;
		this.groupLayer = groupLayer;
		this.pBoardNo = pBoardNo;
		this.groupPath = groupPath;
		this.replyCnt = replyCnt;
	}
	
	//Getter/Setter
	public String getRowNo() {
		return rowNo;
	}	

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getOriginNo() {
		return originNo;
	}

	public void setOriginNo(String originNo) {
		this.originNo = originNo;
	}

	public String getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(String groupSeq) {
		this.groupSeq = groupSeq;
	}

	public String getGroupLayer() {
		return groupLayer;
	}

	public void setGroupLayer(String groupLayer) {
		this.groupLayer = groupLayer;
	}

	public String getpBoardNo() {
		return pBoardNo;
	}

	public void setpBoardNo(String pBoardNo) {
		this.pBoardNo = pBoardNo;
	}

	public String getGroupPath() {
		return groupPath;
	}

	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}
	
	public String getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(String replyCnt) {
		this.replyCnt = replyCnt;
	}

	@Override
	public String toString() {
		return "BoardVO [rowNo=" + rowNo + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", userID=" + userID + ", boardNo=" + boardNo + ", originNo=" + originNo + ", groupSeq=" + groupSeq
				+ ", groupLayer=" + groupLayer + ", pBoardNo=" + pBoardNo + ", groupPath=" + groupPath + ", replyCnt="
				+ replyCnt + "]";
	}
	
	
	
}
