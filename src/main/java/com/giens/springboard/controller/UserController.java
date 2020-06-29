package com.giens.springboard.controller;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;

import com.giens.springboard.service.User.UserService;
import com.giens.springboard.vo.LoginHistoryVO;
import com.giens.springboard.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	
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
		UserVO loginResult = userService.getUser(userVO.getUserID());
		
		//로그인 시간 등록
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();		
		loginHistoryVO.setUserID(userVO.getUserID());
		loginHistoryVO.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginHistoryVO", loginHistoryVO);
		
		//암호화된 패스워드 비교
		System.out.println(pwdEncoder.encode(userVO.getPassword()));
		boolean pwdMatch = false;
		if(loginResult != null) {
			userService.insertLoginHistory(params);
			int logID = (int) params.get("logID");
			pwdMatch  = pwdEncoder.matches(userVO.getPassword(), loginResult.getPassword());
			loginResult.setLogID(Integer.toString(logID));		
		}
		
		
		String result = "";		
		if(loginResult == null || !pwdMatch) {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", false);
			result = "redirect:/loginView.do";
		} else if(loginResult.getActive().equals("N")) {
			System.out.println("===============>  휴면계정입니다");
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", "N");
			result="redirect:/loginView.do";
		} else {
			System.out.println("===============>  활성화된 계정입니다");
			session.setAttribute("user", loginResult);
			result = "redirect:/board.do";
		}
		System.out.println(loginResult);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/logout.do", method= RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		UserVO userVO = (UserVO) session.getAttribute("user");
		userService.updateLoginHistory(userVO);
		session.invalidate();
		
		return "redirect:/loginView.do";
	}
	
	@RequestMapping(value="/user.do")
	public String getListUser(HttpSession session) throws Exception {
		logger.info("user list");
		if(session.getAttribute("user") == null) {
			return "redirect:/loginView.do";
		}else {	
			return "userList";
		}
	}
	
	@RequestMapping(value="/registView.do")
	public String userRegistForm() throws Exception {
		logger.info("user regist form");
		
		return "regist";
	}	
	
	@RequestMapping(value="/regist.do")
	public String addUser(UserVO userVO, int role) throws Exception {
		logger.info("user regist");
		//비밀번호 암호화
		String inputPass = userVO.getPassword();
		String pwd = pwdEncoder.encode(inputPass);
		userVO.setPassword(pwd);
		
		userService.addUser(userVO);
		userService.addRole(userVO);
		
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
	public String loginHistoryList(HttpServletRequest request, Model model, HttpSession session) throws Exception{
		logger.info("login history");
		UserVO loginUser = (UserVO) session.getAttribute("user");
		if(loginUser == null) {
			return "redirect:/loginView.do";
		}else {	
			String queryString = request.getQueryString();
			if (queryString != null) {
				model.addAttribute("profile", true);
			} else {
				model.addAttribute("profile", false);
			}
			//model.addAttribute("userRole", loginUser.getRole());
			return "loginHistory";
		}
	}
	
	//사용자 상세보기
	@RequestMapping(value="/userDetail.do")
	public String getUser(String userID, Model model, HttpSession session) throws Exception {
		logger.info("user detail");
		if(session.getAttribute("user") == null) {
			return "redirect:/loginView.do";
		}else {	
			UserVO userVO = userService.getUser(userID);
			UserVO sessionUserVO = (UserVO) session.getAttribute("user");
			model.addAttribute("user", sessionUserVO);
			model.addAttribute("userDetail", userVO);
			return "userDetail";
		}
	}
	
	//사용자 삭제
	@RequestMapping(value="/userDelete.do")
	public String deleteUser(String userID, HttpSession session) throws Exception {
		logger.info("user delete");
		if(session.getAttribute("user") == null) {
			return "redirect:/loginView.do";
		}else {	
			userService.deleteUser(userID);
			return "redirect:/user.do";
		}
	}
	
	//사용자 업데이트
	@RequestMapping(value="/userUpdate.do", method = RequestMethod.POST)
	public String updateUser(UserVO userVo) throws Exception{
		logger.info("user update");
		//비밀번호 암호화
		String inputPass = userVo.getPassword();
		String pwd = pwdEncoder.encode(inputPass);
		userVo.setPassword(pwd);
		
		userService.userUpdate(userVo);
		return "redirect:/userDetail.do?userID="+userVo.getUserID();
	}
	
	//사용자 업데이트2
	@RequestMapping(value="/selfDetailView.do")
	public String selfDetailView(Model model, HttpSession session) throws Exception{
		logger.info("self detail");
		UserVO sessionUserVO = (UserVO) session.getAttribute("user");
		model.addAttribute("user", sessionUserVO);
		return "selfDetail";
	}
	@RequestMapping(value="/selfDetail.do", method = RequestMethod.POST)
	public String updateSelf(UserVO userVo) throws Exception{
		logger.info("user update");
		//비밀번호 암호화
		String inputPass = userVo.getPassword();
		String pwd = pwdEncoder.encode(inputPass);
		userVo.setPassword(pwd);
		
		userService.selfUpdate(userVo);
		return "redirect:/userDetail.do?userID="+userVo.getUserID();
	}
		
}
