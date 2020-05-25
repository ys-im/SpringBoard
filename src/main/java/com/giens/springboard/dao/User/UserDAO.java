package com.giens.springboard.dao.User;

import com.giens.springboard.vo.UserVO;

public interface UserDAO {
	
	//회원가입
	public void addUser(UserVO userVO) throws Exception;
	
	//로그인
	public UserVO login(UserVO userVO) throws Exception;
	
	//아이디 중복 체크
	public int idCheck(String userID) throws Exception;
}
