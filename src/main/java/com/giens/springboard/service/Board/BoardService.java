package com.giens.springboard.service.Board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.giens.springboard.vo.BoardVO;

@Service("boardService")
public interface BoardService {

	//게시글 목록
	public List<BoardVO> getBoardlist() throws Exception;
	
	//게시글 작성
	public void addBoard(Map<String, Object> params) throws Exception;
	
	//게시글 첨부파일 업로드
	public void addBoardFile(int boardNo, MultipartHttpServletRequest mpRequest) throws Exception;
	
	//원본게시글 작성 업데이트
	public void updateBoardNew() throws Exception;
	
	//게시글 상세보기
	public List<BoardVO> getBoard(int boardNo) throws Exception;
}
