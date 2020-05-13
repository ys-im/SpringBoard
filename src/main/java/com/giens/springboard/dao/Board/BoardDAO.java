package com.giens.springboard.dao.Board;

import java.util.List;
import java.util.Map;

import com.giens.springboard.vo.BoardVO;

public interface BoardDAO {	
	
	//게시글 목록
	public List<BoardVO> getBoardlist() throws Exception;
	
	//게시글 작성
	public void addBoard(Map<String, Object> params) throws Exception;
	
	//게시글 첨부파일 업로드
	public void addBoardFile(Map<String, Object> params) throws Exception;
	
	//원본게시글 작성 업데이트
	public void updateBoardNew() throws Exception;
	
	//게시글 상세보기
	public List<BoardVO> getBoard(int boardNo) throws Exception;
	
	//첨부파일 조회
	public List<Map<String, Object>> getBoardFileList(int boardNo) throws Exception;
	
	//첨부파일 다운로드
	public Map<String, Object> getBoardFile(Map<String, Object> map) throws Exception;
	
	//게시글 수정
	public void editBoard(BoardVO boardVO) throws Exception;
	
	//게시글 첨부파일 삭제
	public void deleteBoardFile(int fileNo) throws Exception;
	
	//게시글 삭제
	public void deleteBoard(int boardNo) throws Exception;
}
