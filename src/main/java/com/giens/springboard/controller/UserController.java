package com.giens.springboard.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giens.springboard.service.User.UserService;
import com.giens.springboard.vo.BoardVO;
import com.giens.springboard.vo.LoginHistoryVO;
import com.giens.springboard.vo.PageMaker;
import com.giens.springboard.vo.SearchCriteria;
import com.giens.springboard.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	UserService userService;
	
	@RequestMapping(value = "/loginView.do")
	public String loginView() {
		logger.info("login view");
		return "login";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO userVO, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("login");
		System.out.println(userVO);
		HttpSession session = req.getSession();
		UserVO loginResult = userService.login(userVO);
		
		String result = "";
		if(loginResult == null)	{
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", false);
			result = "redirect:/loginView.do";
		} else {
			session.setAttribute("user", loginResult);
			result = "redirect:/board.do";
		}
		System.out.println(loginResult);
		System.out.println(result);
		return result;
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
	public String addUser(UserVO userVO) throws Exception {
		logger.info("user regist");
		
		userService.addUser(userVO);
		return "redirect:/user.do";
	}

	//아이디 중복 체크
	@RequestMapping(value="/idCheck.do")
	@ResponseBody
	public int idCheck(String userID) throws Exception {
		logger.info("id check");
		
		int idCheckValue = userService.idCheck(userID);
		
		return idCheckValue;
	}
	@RequestMapping(value="/loginHistory.do")
	public String loginHistoryList(String userID) throws Exception{
		logger.info("login history");
		
		return "loginHistory";
	}
	
	//사용자 상세보기
	@RequestMapping(value="/userDetail.do")
	public String getUser(String userID, Model model) throws Exception {
		logger.info("user detaile");
		UserVO userVO = userService.getUser(userID);
		model.addAttribute("user", userVO);
		return "userDetail";
	}
	
	//사용자 삭제
	@RequestMapping(value="/userDelete.do")
	public String deleteUser(String userID) throws Exception {
		logger.info("user delete");
		
		userService.deleteUser(userID);
		return "redirect:/user.do";
	}
	
}
