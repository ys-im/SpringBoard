package com.giens.springboard.dao.User;

import java.util.List;

import com.giens.springboard.vo.LoginHistoryVO;
import com.giens.springboard.vo.SearchCriteria;
import com.giens.springboard.vo.UserVO;

public interface UserDAO {
	
	//회원가입
	public void addUser(UserVO userVO) throws Exception;
	
	//로그인
	public UserVO login(UserVO userVO) throws Exception;
	
	//아이디 중복 체크
	public int idCheck(String userID) throws Exception;
	
	//사용자 목록
	public List<UserVO> getUserList(SearchCriteria searchCriteria) throws Exception;
	
	//사용자 목록 개수
	public int countUserList(SearchCriteria searchCriteria) throws Exception;
	
	//사용자 상세
	public UserVO getUser(String userID) throws Exception;
	
	//로그인 히스토리 목록
	public List<LoginHistoryVO> getLoginHistoryList(SearchCriteria searchCriteria) throws Exception;
	
	//로그인 히스토리 목록 개수
	public int countLoginHistoryList(SearchCriteria searchCriteria) throws Exception;
	
	//로그인 히스토리 등록
	public void insertLoginHistory(LoginHistoryVO loginHistoryVO) throws Exception;
		
	//로그아웃 히스토리 등록
	public void updateLoginHistory(String userID) throws Exception;
	
	//사용자 삭제
	public void deleteUser(String userID) throws Exception;
	
}
