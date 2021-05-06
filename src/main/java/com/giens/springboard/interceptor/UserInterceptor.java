package com.giens.springboard.interceptor;


import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.giens.springboard.service.User.UserService;
import com.giens.springboard.vo.UserVO;


public class UserInterceptor implements HandlerInterceptor {
	
	@Inject
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession();
		Object object = httpSession.getAttribute("user");
		
		if(object == null) { 
			//로그인된 세션이 없는 경우
			Cookie autoLogin = WebUtils.getCookie(request, "autoLogin");
			if(autoLogin != null) {
				//쿠키가 존재하는 경우(이전에 로그인 때 생성된 쿠키가 존재한다는 것)
				//autoLogin의 값을 꺼내오고==session의 id를 꺼냄
				String sessionId = autoLogin.getValue();
				//sessionId 체크,유효시간 체크
				UserVO userVO = userService.checkUserWithSessionKey(sessionId);
				if(userVO != null) {
					httpSession.setAttribute("user", userVO);
					System.out.println("UserInterceptor 확인1");
					return true;
				}
			}else {
				//로그인X, 쿠키X
				System.out.println("UserInterceptor 확인2");
				
				return true;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}



}
