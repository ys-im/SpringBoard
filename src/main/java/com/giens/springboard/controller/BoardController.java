package com.giens.springboard.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.giens.springboard.service.Board.BoardService;
import com.giens.springboard.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService boardService;

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
	public String addBoard(String title, String userID, String contents, int pBoardNo) throws Exception {
		logger.info("add board");
		boardService.addBoard(title, userID, contents, pBoardNo);
		
		if(pBoardNo == 0) {
			boardService.updateBoardNew();
		}
		return "redirect:/board.do";
	}
	
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public String getBoard(int boardNo) throws Exception {
		logger.info("get board detail");
		
		return "boardDetail";
	}
	
}
