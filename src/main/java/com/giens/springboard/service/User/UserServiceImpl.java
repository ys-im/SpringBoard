package com.giens.springboard.service.User;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giens.springboard.dao.User.UserDAO;
import com.giens.springboard.vo.LoginHistoryVO;
import com.giens.springboard.vo.SearchCriteria;
import com.giens.springboard.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	//회원가입
	@Override
	public void addUser(UserVO userVO) throws Exception {
		userDAO.addUser(userVO);
	}

	//로그인
	@Override
	public UserVO login(UserVO userVO) throws Exception {
		return userDAO.login(userVO);
	}
	
	//사용자 상세
	@Override
	public UserVO getUser(String userID) throws Exception {
		return userDAO.getUser(userID);
	}

	@Override
	public int idCheck(String userID) throws Exception {
		return userDAO.idCheck(userID);
	}
	
	@Override
	public List<UserVO> getUserList(SearchCriteria searchCriteria) throws Exception {
		return userDAO.getUserList(searchCriteria);
	}
	
	@Override
	public int countUserList(SearchCriteria searchCriteria) throws Exception {
		return userDAO.countUserList(searchCriteria);
	}
	
	@Override
	public List<LoginHistoryVO> getLoginHistoryList(SearchCriteria searchCriteria) throws Exception {
		return userDAO.getLoginHistoryList(searchCriteria);
	}

	@Override
	public int countLoginHistoryList(SearchCriteria searchCriteria) throws Exception {
		return userDAO.countLoginHistoryList(searchCriteria);
	}

	@Override
	public void insertLoginHistory(Map<String, Object> params) throws Exception {
		userDAO.insertLoginHistory(params);
	}
	
	@Override
	public void updateLoginHistory(UserVO userVO) throws Exception {
		userDAO.updateLoginHistory(userVO);
	}
	
	@Override
	public void deleteUser(String userID) throws Exception {
		userDAO.deleteUser(userID);
	}

	@Override
	public void addRole(UserVO userVO) throws Exception {
		userDAO.addRole(userVO);
	}

	@Override
	public void userUpdate(UserVO userVO) throws Exception {
		userDAO.userUpdate(userVO);
	}

	@Override
	public void selfUpdate(UserVO userVO) throws Exception {
		userDAO.selfUpdate(userVO);
	}

	@Override
	public void roleUpdate(UserVO userVO) throws Exception {
		userDAO.roleUpdate(userVO);		
	}


	
}
