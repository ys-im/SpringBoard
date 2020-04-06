package com.giens.springboard.service.Board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giens.springboard.dao.Board.BoardDAO;
import com.giens.springboard.vo.BoardVO;

import lombok.AllArgsConstructor;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardlist() throws Exception {
		return boardDAO.getBoardlist();
	}
	
	@Override
	public void addBoard(String title, String userID, String contents, int pBoardNo) throws Exception {
		boardDAO.addBoard(title, userID, contents, pBoardNo);
	}

	@Override
	public void updateBoardNew() throws Exception {
		boardDAO.updateBoardNew();
	}

	@Override
	public List<BoardVO> getBoard(int boardNo) throws Exception {
		return boardDAO.getBoard(boardNo);
	}
}
