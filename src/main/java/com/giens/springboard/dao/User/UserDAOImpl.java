package com.giens.springboard.dao.User;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.giens.springboard.vo.LoginHistoryVO;
import com.giens.springboard.vo.SearchCriteria;
import com.giens.springboard.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

	@Inject
	private SqlSessionTemplate sqlSession;
	
	//회원가입
	@Override
	public void addUser(UserVO userVO) throws Exception {
		sqlSession.insert("userMapper.addUser", userVO);
	}

	//로그인
	@Override
	public UserVO login(UserVO userVO) throws Exception {		
		return sqlSession.selectOne("userMapper.login", userVO);
	}

	//아이디 중복 체크
	@Override
	public int idCheck(String userID) throws Exception {
		return sqlSession.selectOne("userMapper.idCheck", userID);  
	}
	
	//사용자 목록
	@Override
	public List<UserVO> getUserList(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectList("userMapper.userList", searchCriteria);
	}
	
	//사용자 목록 개수
	@Override
	public int countUserList(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectOne("userMapper.countUserList", searchCriteria);
	}
	
	//사용자 상세
	@Override
	public UserVO getUser(String userID) throws Exception {
		return sqlSession.selectOne("userMapper.getUser", userID);
	}
	
	//로그인 히스토리 목록
	@Override
	public List<LoginHistoryVO> getLoginHistoryList(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectList("userMapper.loginHistoryList", searchCriteria);
	}
	
	//로그인 히스토리 목록 개수
	@Override
	public int countLoginHistoryList(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectOne("userMapper.countLoginHistoryList", searchCriteria);
	}

	//로그인 히스토리 등록
	@Override
	public void insertLoginHistory(LoginHistoryVO loginHistoryVO) throws Exception {
		sqlSession.insert("userMapper.insertLoginHistory", loginHistoryVO);
	}
	
	//로그아웃 히스토리 등록
	@Override
	public void updateLoginHistory(String userID) throws Exception {
		sqlSession.update("userMapper.updateLoginHistory", userID);
	}
	
	//사용자 삭제(업데이트 del_flag)
	@Override
	public void deleteUser(String userID) throws Exception {
		sqlSession.update("userMapper.deleteUser", userID);
	}



}
