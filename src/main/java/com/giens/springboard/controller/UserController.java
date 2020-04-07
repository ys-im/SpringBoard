package com.giens.springboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value="/user.do")
	public String getListUser() throws Exception {
		logger.info("user list");
		
		return "userManagement";
	}
}
