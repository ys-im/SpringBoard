package com.giens.springboard.service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giens.springboard.dao.User.UserDAO;
import com.giens.springboard.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	//회원가입
	@Override
	public void addUser(UserVO userVO) throws Exception {
		
	}

	//로그인
	@Override
	public UserVO login(UserVO userVO) throws Exception {
		
		return null;
	}

	@Override
	public int idCheck(String userID) throws Exception {
		return userDAO.idCheck(userID);
	}
	
	
}
