package com.giens.springboard.service.Board;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.giens.springboard.dao.Board.BoardDAO;
import com.giens.springboard.util.FileUtils;
import com.giens.springboard.vo.BoardVO;
import com.giens.springboard.vo.Criteria;
import com.giens.springboard.vo.SearchCriteria;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardlist(SearchCriteria searchCriteria) throws Exception {
		return boardDAO.getBoardlist(searchCriteria);
	}
	
	@Override
	public int countBoardList(SearchCriteria searchCriteria) throws Exception {
		return boardDAO.countBoardList(searchCriteria);
	}
	
	@Override
	public void addBoard(Map<String, Object> params) throws Exception {
		boardDAO.addBoard(params);
	}

	@Override
	public void addBoardFile(List<Map<String, Object>> list) throws Exception {
		boardDAO.addBoardFile(list);
	}

	@Override
	public void updateBoardNew() throws Exception {
		boardDAO.updateBoardNew();
	}
	
	@Override
	public void updateBoardReply(Map<String, Object> params) throws Exception {
		boardDAO.updateBoardReply(params);
	}

	@Override
	public List<BoardVO> getBoard(int boardNo) throws Exception {
		return boardDAO.getBoard(boardNo);
	}

	//게시글 조회수
	@Override
	public void updateBoardHit(int boardNo) throws Exception {
		boardDAO.updateBoardHit(boardNo);
	}
		
	@Override
	public List<Map<String, Object>> getBoardFileList(int boardNo) throws Exception {
		return boardDAO.getBoardFileList(boardNo);
	}

	@Override
	public Map<String, Object> getBoardFile(Map<String, Object> map) throws Exception {
		return boardDAO.getBoardFile(map);
	}

	@Override
	public void editBoard(BoardVO boardVO) throws Exception {
		boardDAO.editBoard(boardVO);		
	}

	@Override
	public void deleteBoardFile(List<Map<String, String>> deleteFileList) throws Exception {
		boardDAO.deleteBoardFile(deleteFileList);
	}

	@Override
	public void deleteBoard(int boardNo) throws Exception {
		boardDAO.deleteBoard(boardNo);
	}
}
