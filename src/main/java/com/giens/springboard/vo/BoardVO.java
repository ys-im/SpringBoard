package com.giens.springboard.vo;

import java.util.Date;

import org.apache.ibatis.annotations.AutomapConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardVO {
	
	private int rowNo;
	private String title;
	private String contents;
	private String regDate;
	private String userID;
	private int boardNo;
	private int originNo;
	private int groupSeq;
	private int groupLayer;
	private int pBoardNo;
	private String groupPath;
	private int replyCnt;
	
	//Constructor		
	public BoardVO(int rowNo, String title, String contents, String regDate, String userID, int boardNo, int originNo,
			int groupSeq, int groupLayer, int pBoardNo, String groupPath, int replyCnt) {
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
	public int getRowNo() {
		return rowNo;
	}	

	public void setRowNo(int rowNo) {
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getOriginNo() {
		return originNo;
	}

	public void setOriginNo(int originNo) {
		this.originNo = originNo;
	}

	public int getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}

	public int getGroupLayer() {
		return groupLayer;
	}

	public void setGroupLayer(int groupLayer) {
		this.groupLayer = groupLayer;
	}

	public int getpBoardNo() {
		return pBoardNo;
	}

	public void setpBoardNo(int pBoardNo) {
		this.pBoardNo = pBoardNo;
	}

	public String getGroupPath() {
		return groupPath;
	}

	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}
	
	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
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
