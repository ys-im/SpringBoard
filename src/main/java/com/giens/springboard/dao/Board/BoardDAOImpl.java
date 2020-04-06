package com.giens.springboard.dao.Board;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.giens.springboard.vo.BoardVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVO> getBoardlist() throws Exception {
		return sqlSession.selectList("boardMapper.boardList");
	}

	@Override
	public void addBoard(String title, String userID, String contents, int pBoardNo) throws Exception {		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("userID", userID);
		params.put("contents", contents);
		params.put("pBoardNo", pBoardNo);
		sqlSession.insert("boardMapper.addBoard", params);
	}

	@Override
	public void updateBoardNew() throws Exception {
		sqlSession.update("boardMapper.updateBoardNew");
	}

	@Override
	public List<BoardVO> getBoard(int boardNo) throws Exception {
		return sqlSession.selectList("boardMapper.boardDetail");
	}

}
