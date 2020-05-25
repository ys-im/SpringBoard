package com.giens.springboard.dao.User;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.giens.springboard.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

	@Inject
	private SqlSessionTemplate sqlSession;
	
	//회원가입
	@Override
	public void addUser(UserVO userVO) throws Exception {
		
	}

	//로그인
	@Override
	public UserVO login(UserVO userVO) throws Exception {
		
		return null;
	}

	//아이디 중복 체크
	@Override
	public int idCheck(String userID) throws Exception {
		return sqlSession.selectOne("userMapper.idCheck", userID);
	}

}
