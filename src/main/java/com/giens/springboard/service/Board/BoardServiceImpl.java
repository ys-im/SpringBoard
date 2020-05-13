package com.giens.springboard.service.Board;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.giens.springboard.dao.Board.BoardDAO;
import com.giens.springboard.util.FileUtils;
import com.giens.springboard.vo.BoardVO;


@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardlist() throws Exception {
		return boardDAO.getBoardlist();
	}
	
	@Override
	public void addBoard(Map<String, Object> params) throws Exception {
		boardDAO.addBoard(params);
	}

	@Override
	public void addBoardFile(int boardNo, MultipartHttpServletRequest mpRequest) throws Exception {
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(boardNo, mpRequest);
		
		int size = list.size();		
		
		for(int i = 0; i < size; i++) {
			boardDAO.addBoardFile(list.get(i));
		}
	}

	@Override
	public void updateBoardNew() throws Exception {
		boardDAO.updateBoardNew();
	}

	@Override
	public List<BoardVO> getBoard(int boardNo) throws Exception {
		return boardDAO.getBoard(boardNo);
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
	public void deleteBoardFile(int boardNo, List<String> deleteFileNoList) throws Exception {
		int size = deleteFileNoList.size();
		for(int i = 0; i < size; i++) {
			int fileNo = Integer.parseInt(deleteFileNoList.get(i));
			boardDAO.deleteBoardFile(fileNo);
		}
	}

	@Override
	public void deleteBoard(int boardNo) throws Exception {
		boardDAO.deleteBoard(boardNo);
	}
}
