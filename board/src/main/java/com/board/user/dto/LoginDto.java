package com.board.user.dto;

/**
 * 로그인하는 사용자 정보 DTO
 * @author 강은주
 *
 */
public class LoginDto {

	private String userId;
	private String userPw;
	private boolean userCookie;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public boolean isUserCookie() {
		return userCookie;
	}

	public void setUserCookie(boolean userCookie) {
		this.userCookie = userCookie;
	}

}
