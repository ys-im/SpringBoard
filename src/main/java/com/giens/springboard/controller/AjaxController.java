package com.giens.springboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giens.springboard.service.Board.BoardService;
import com.giens.springboard.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("ajax")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Inject
	BoardService boardService;


	@RequestMapping("/toastBoardList.do")
	public List<BoardVO> ajaxBoardList() throws Exception {
		logger.info("board list");
		List<BoardVO> boardList = boardService.getBoardlist();
		
		return boardList;
	}	
	
	@RequestMapping("/toastBoardDetail.do")
	public List<BoardVO> ajaxBoardDetail(int boardNo) throws Exception {
		logger.info("board detail");
		
		List<BoardVO> boardDetail = boardService.getBoard(boardNo);
						
		return  boardDetail;
	}
}

