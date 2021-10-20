package com.board.user.service;

import com.board.user.dto.LoginDto;
import com.board.user.dto.UserDto;

public interface UserService {
	
	void register(UserDto userDto) throws Exception;
	
	UserDto login(LoginDto loginDto) throws Exception;
	
	int checkId(String userId) throws Exception;
}
