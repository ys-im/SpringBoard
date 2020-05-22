package com.giens.springboard.dao.User;

import com.giens.springboard.vo.UserVO;

public interface UserDAO {
	
	//회원가입
	public void addUser(UserVO userVO) throws Exception;
	
	//로그인
	public UserVO login(UserVO userVO) throws Exception;
}
