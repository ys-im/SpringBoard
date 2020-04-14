package com.giens.springboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/loginView.do", method = RequestMethod.GET)
	public String login() {
		logger.info("login");
		return "login";
	}
	
	@RequestMapping(value="/user.do")
	public String getListUser() throws Exception {
		logger.info("user list");
		
		return "userList";
	}
	
	@RequestMapping(value="/registView.do")
	public String userRegistForm() throws Exception {
		logger.info("user regist form");
		
		return "regist";
	}
	
	@RequestMapping(value="/regist.do")
	public String addUser(/*여기에 받을 파라미터 입력하면 됨. BoardController/addBoard 참고*/) throws Exception {
		logger.info("user regist");
		
		return "redirect:/user.do";
	}
	
	@RequestMapping(value="/loginHistory.do")
	public String loginHistoryList(String userID) throws Exception{
		logger.info("login history");
		
		return "loginHistory";
	}
}
