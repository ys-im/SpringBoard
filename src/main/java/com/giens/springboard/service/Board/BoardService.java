package com.giens.springboard.service.Board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.giens.springboard.vo.BoardVO;
import com.giens.springboard.vo.Criteria;
import com.giens.springboard.vo.SearchCriteria;

@Service("boardService")
public interface BoardService {

	//게시글 목록
	public List<BoardVO> getBoardlist(SearchCriteria searchCriteria) throws Exception;
	
	//게시글 목록 개수
	public int countBoardList(SearchCriteria searchCriteria) throws Exception;
	
	//게시글 작성
	public void addBoard(Map<String, Object> params) throws Exception;
	
	//게시글 첨부파일 업로드
	public void addBoardFile(List<Map<String, Object>> list) throws Exception;
	
	//원본게시글 작성 업데이트
	public void updateBoardNew() throws Exception;
	
	//답글 작성 후 업데이트
	public void updateBoardReply(Map<String, Object> params) throws Exception;
	
	//게시글 상세보기
	public List<BoardVO> getBoard(int boardNo) throws Exception;
	
	//게시글 조회수
	public void updateBoardHit(int boardNo) throws Exception;
	
	//첨부파일 조회
	public List<Map<String, Object>> getBoardFileList(int boardNo) throws Exception;
	
	//첨부파일 다운로드
	public Map<String, Object> getBoardFile(Map<String, Object> map) throws Exception;
	
	//게시글 수정
	public void editBoard(BoardVO boardVO) throws Exception;
	
	//게시글 삭제
	public void deleteBoard(int boardNo) throws Exception;
	
	//게시글 첨부파일 삭제
	public void deleteBoardFile(List<Map<String, String>> deleteFileList) throws Exception;
	
}
