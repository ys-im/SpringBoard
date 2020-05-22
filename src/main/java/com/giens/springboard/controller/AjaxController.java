package com.giens.springboard.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.giens.springboard.service.Board.BoardService;
import com.giens.springboard.vo.BoardVO;
import com.giens.springboard.vo.Criteria;
import com.giens.springboard.vo.PageMaker;
import com.giens.springboard.vo.SearchCriteria;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("ajax")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Inject
	BoardService boardService;


	@RequestMapping(value="/toastBoardList.do", produces="application/json; charset=UTF-8")
	public List<BoardVO> ajaxBoardList(Model model, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		logger.info("board list");
//		System.out.println(searchCriteria);
//		List<BoardVO> boardList = boardService.getBoardlist(searchCriteria);
//		System.out.println(boardList.size());
//		return boardList
		
//		if(session.getAttribute("id") == null) {
//			mav = new ModelAndView("redirect");
//			mav.addObject("msg", "잘못된 접근입니다!!");
//			mav.addObject("url", "/loginView.do");
//			return mav;
//		}else {		
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(searchCriteria);
			pageMaker.setTotalCount(boardService.countBoardList(searchCriteria));
			
			System.out.println(pageMaker.getTotalCount());
			model.addAttribute("pageMakerAjax", pageMaker);
			List<BoardVO> boardList = boardService.getBoardlist(searchCriteria);
			
			return boardList;
//		}
	}	
	
	@RequestMapping(value="/listCount.do", produces="application/json; charset=UTF-8")
	public PageMaker countBoardList(Model model, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		logger.info("board count");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(boardService.countBoardList(searchCriteria));
		
		return pageMaker;
	}	
	
	@RequestMapping("/toastBoardDetail.do")
	public List<BoardVO> ajaxBoardDetail(int boardNo) throws Exception {
		logger.info("board detail");
		
		List<BoardVO> boardDetail = boardService.getBoard(boardNo);
		
		return boardDetail;
	}
}

