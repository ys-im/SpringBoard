package com.giens.springboard.service.User;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.giens.springboard.vo.LoginHistoryVO;
import com.giens.springboard.vo.SearchCriteria;
import com.giens.springboard.vo.UserVO;

@Service("userService")
public interface UserService {

	//회원가입
	public void addUser(UserVO userVO) throws Exception;
	
	//로그인
	public UserVO login(UserVO userVO) throws Exception;
	
	//아이디 중복체크
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
	public void insertLoginHistory(Map<String, Object> params) throws Exception;
	
	//로그아웃 히스토리 등록
	public void updateLoginHistory(UserVO userVO) throws Exception;
	
	//사용자 삭제
	public void deleteUser(String userID) throws Exception;

	
	public void addRole(UserVO userVO) throws Exception;
}
