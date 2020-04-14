package com.giens.springboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.giens.springboard.service.Board.BoardService;
import com.giens.springboard.util.FileUtils;
import com.giens.springboard.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService boardService;

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@RequestMapping(value = "/board.do", method = RequestMethod.GET)
	public String getListBoard() throws Exception {
		logger.info("board view");

		return "board";
	}
	
	@RequestMapping(value = "/writeView.do")
	public String writeView() throws Exception {
		return "write";
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String addBoard(String title, String userID, String contents, int pBoardNo, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("add board");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("userID", userID);
		params.put("contents", contents);
		params.put("pBoardNo", pBoardNo);
		
		//게시판 글 등록
		boardService.addBoard(params);
		int boardNo = (int)params.get("boardNo");
		
		if(pBoardNo == 0) {
			boardService.updateBoardNew();
		}
				
		//게시판 글에 첨부파일 등록
		boardService.addBoardFile(boardNo, mpRequest);
		
		return "redirect:/board.do";
	}
	
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public String getBoard(int boardNo) throws Exception {
		logger.info("get board detail");
		
		return "boardDetail";
	}
	
	@RequestMapping(value="/editView.do")
	public String editView(int boardNo) throws Exception {
		logger.info("edit board");
		
		return "edit";
	}
	
}
