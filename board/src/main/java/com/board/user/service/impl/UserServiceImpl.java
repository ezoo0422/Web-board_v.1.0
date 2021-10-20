package com.board.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.user.dao.UserDao;
import com.board.user.dto.LoginDto;
import com.board.user.dto.UserDto;
import com.board.user.service.UserService;

/**
 * 사용자 관련 Service
 * 회원가입, 로그인, 아이디 중복확인 처리
 * @author user
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 회원가입 처리
	 */
	@Override
	public void register(UserDto userDto) throws Exception {
		userDao.register(userDto);
	}

	/**
	 * 로그인 처리 정보 전달
	 */
	@Override
	public UserDto login(LoginDto loginDto) throws Exception {
		return userDao.login(loginDto);
	}

	/**
	 * 아이디 중복확인 아이디 개수 전달
	 */
	@Override
	public int checkId(String userId) throws Exception {
		return userDao.checkId(userId);
	}
}
