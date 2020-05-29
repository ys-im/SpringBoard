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
import com.giens.springboard.service.User.UserService;
import com.giens.springboard.vo.BoardVO;
import com.giens.springboard.vo.Criteria;
import com.giens.springboard.vo.LoginHistoryVO;
import com.giens.springboard.vo.PageMaker;
import com.giens.springboard.vo.SearchCriteria;
import com.giens.springboard.vo.UserVO;
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
	
	@Inject
	UserService userService;


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

	
	@RequestMapping(value="/loginHistoryList.do", produces="application/json; charset=UTF-8")
	public List<LoginHistoryVO> ajaxLoginHistoryList(Model model, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		logger.info("login hitory list");
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
			System.out.println(searchCriteria);
			pageMaker.setTotalCount(userService.countLoginHistoryList(searchCriteria));
			
			System.out.println(pageMaker.getTotalCount());
			model.addAttribute("pageMakerAjax", pageMaker);
			List<LoginHistoryVO> loginHistoryList = userService.getLoginHistoryList(searchCriteria);
			
			return loginHistoryList;
//		}
	}	

	@RequestMapping(value="/loginHistoryListCount.do", produces="application/json; charset=UTF-8")
	public PageMaker countLoginHistoryList(Model model, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		logger.info("login history count");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(userService.countLoginHistoryList(searchCriteria));
		
		return pageMaker;
	}
	
	@RequestMapping(value="/userList.do", produces="application/json; charset=UTF-8")
	public List<UserVO> ajaxUserList(Model model, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		logger.info("user list");
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
			System.out.println(searchCriteria);
			pageMaker.setTotalCount(userService.countUserList(searchCriteria));
			
			System.out.println(pageMaker.getTotalCount());
			model.addAttribute("pageMakerAjax", pageMaker);
			List<UserVO> userList = userService.getUserList(searchCriteria);
			
			return userList;
//		}
	}	

	@RequestMapping(value="/userListCount.do", produces="application/json; charset=UTF-8")
	public PageMaker countUserList(Model model, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		logger.info("user count");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(userService.countUserList(searchCriteria));
		
		return pageMaker;
	}
}

