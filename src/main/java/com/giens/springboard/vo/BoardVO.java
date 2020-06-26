package com.giens.springboard.vo;

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
	private String hit;
	private String replyCnt;
	private String fileCnt;
	
		
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
	
	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(String replyCnt) {
		this.replyCnt = replyCnt;
	}
	
	public String getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(String fileCnt) {
		this.fileCnt = fileCnt;
	}

	@Override
	public String toString() {
		return "BoardVO [rowNo=" + rowNo + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", userID=" + userID + ", boardNo=" + boardNo + ", originNo=" + originNo + ", groupSeq=" + groupSeq
				+ ", groupLayer=" + groupLayer + ", pBoardNo=" + pBoardNo + ", groupPath=" + groupPath + ", hit=" + hit
				+ ", replyCnt=" + replyCnt + ", fileCnt=" + fileCnt + "]";
	}
	
}
