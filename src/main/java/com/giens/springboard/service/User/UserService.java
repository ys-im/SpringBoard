package com.giens.springboard.service.User;

import org.springframework.stereotype.Service;

import com.giens.springboard.vo.UserVO;

@Service("userService")
public interface UserService {

	//회원가입
	public void addUser(UserVO userVO) throws Exception;
	
	//로그인
	public UserVO login(UserVO userVO) throws Exception;
}
