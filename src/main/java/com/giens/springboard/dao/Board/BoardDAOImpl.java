package com.giens.springboard.dao.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public void addBoard(Map<String, Object> params) throws Exception {		
		sqlSession.insert("boardMapper.addBoard", params);
	}

	@Override
	public void addBoardFile(Map<String, Object> params) throws Exception {
		sqlSession.insert("boardMapper.addBoardFile", params);		
	}
	
	@Override
	public void updateBoardNew() throws Exception {
		sqlSession.update("boardMapper.updateBoardNew");
	}

	@Override
	public List<BoardVO> getBoard(int boardNo) throws Exception {
		return sqlSession.selectList("boardMapper.boardDetail", boardNo);
	}

	@Override
	public List<Map<String, Object>> getBoardFileList(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.fileList", boardNo);
	}

	@Override
	public Map<String, Object> getBoardFile(Map<String, Object> map) throws Exception {	
		return sqlSession.selectOne("boardMapper.file", map);
	}

	@Override
	public void editBoard(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.editBoard", boardVO);
	}

	@Override
	public void deleteBoardFile(int fileNo) throws Exception {
		sqlSession.delete("boardMapper.deleteBoardFile", fileNo);
	}

	@Override
	public void deleteBoard(int boardNo) throws Exception {
		sqlSession.delete("boardMapper.deleteBoard", boardNo);
	}

}
