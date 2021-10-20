package com.board.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.user.dto.LoginDto;
import com.board.user.dto.UserDto;

/**
 * 사용자 정보 DAO
 * @author user 강은주
 *
 */
@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * 회원가입 처리
	 * @param userDto 회원 정보
	 */
	public void register(UserDto userDto) {
		sqlSession.insert("user.register", userDto);
	}

	/**
	 * 
	 * @param loginDto
	 * @return user 로그인 정보
	 * @throws Exception
	 */
	public UserDto login(LoginDto loginDto) throws Exception {
		return sqlSession.selectOne("user.login", loginDto);
	}

	/**
	 * 아이디 중복확인을 위한 동일 아이디 개수 전달
	 * @param userId 사용자 입력 ID
	 * @return countId 동일 아이디 개수 
	 * @throws Exception
	 */
	public int checkId(String userId) throws Exception {
		return sqlSession.selectOne("user.checkId", userId);
	}
}
