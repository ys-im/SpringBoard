package com.giens.springboard.dao.Board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.giens.springboard.vo.BoardVO;
import com.giens.springboard.vo.SearchCriteria;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//게시글 목록
	@Override
	public List<BoardVO> getBoardlist(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectList("boardMapper.boardList", searchCriteria);
	}
	
	//게시글 목록 개수
	@Override
	public int countBoardList(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectOne("boardMapper.countBoardList", searchCriteria);		
	}

	//게시글 작성
	@Override
	public void addBoard(Map<String, Object> params) throws Exception {		
		sqlSession.insert("boardMapper.addBoard", params);
	}
	
	//게시글 첨부파일 업로드
	@Override
	public void addBoardFile(List<Map<String, Object>> params) throws Exception {
		sqlSession.insert("boardMapper.addBoardFile", params);		
	}
	
	//원본게시글 작성 업데이트
	@Override
	public void updateBoardNew() throws Exception {
		sqlSession.update("boardMapper.updateBoardNew");
	}
	
	//답글 작성 후 업데이트
	@Override
	public void updateBoardReply(Map<String, Object> params) throws Exception {
		sqlSession.update("boardMapper.updateBoardReply", params);
	}

	//게시글 상세보기
	@Override
	public List<BoardVO> getBoard(int boardNo) throws Exception {
		return sqlSession.selectList("boardMapper.boardDetail", boardNo);
	}

	//게시글 조회수
	@Override
	public void updateBoardHit(int boardNo) throws Exception {
		sqlSession.update("boardMapper.boardHit", boardNo);
	}
		
	//첨부파일 조회
	@Override
	public List<Map<String, Object>> getBoardFileList(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.fileList", boardNo);
	}

	//첨부파일 다운로드
	@Override
	public Map<String, Object> getBoardFile(Map<String, Object> map) throws Exception {	
		return sqlSession.selectOne("boardMapper.file", map);
	}

	//게시글 수정
	@Override
	public void editBoard(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.editBoard", boardVO);
	}

	//게시글 삭제
	@Override
	public void deleteBoard(int boardNo) throws Exception {
		sqlSession.delete("boardMapper.deleteBoard", boardNo);
	}
	
	//게시글 첨부파일 삭제
	@Override
	public void deleteBoardFile(List<Map<String, String>> deleteFileList) throws Exception {
		sqlSession.delete("boardMapper.deleteBoardFile", deleteFileList);
	}


}
