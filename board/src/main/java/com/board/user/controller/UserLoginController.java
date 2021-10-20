package com.board.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.user.dto.LoginDto;
import com.board.user.dto.UserDto;
import com.board.user.service.UserService;

/**
 * 회원 로그인, 로그아웃 처리 Controller
 * @author 강은주
 *
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {

	private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

	@Autowired
	public UserService userService;

	/**
	 * 로그인 화면 출력
	 * @param loginDto 로그인 정보
	 * @return /user/login 로그인 화면
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@ModelAttribute("loginDto") LoginDto loginDto) {
		return "/user/login";
	}

	/**
	 * 로그인 처리 (존재하지 않은 ID, password 일치 여부 확인)
	 * @param loginDto 로그인 정보
	 * @param httpSession 
	 * @param model 
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(LoginDto loginDto, HttpSession httpSession, Model model) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>> 로그인 정보 " + loginDto.toString());
		logger.info(">>>>>>>>>>>>>>>>> 로그인 정보 LoginPw " + loginDto.getUserPw());

		try {
			UserDto userDto = userService.login(loginDto);
			if (!BCrypt.checkpw(loginDto.getUserPw(), userDto.getUserPw())) {
				return;
			}
			model.addAttribute("user", userDto);

		} catch (NullPointerException e) {
			return;
		}
	}

	/**
	 * 로그아웃 처리
	 * @param request 화면 요청 내용
	 * @param response 화면 결과 내용
	 * @param httpSession
	 * @return /user/logout 로그아웃 화면
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws Exception {
		Object object = httpSession.getAttribute("login");
		if (object != null) {
			httpSession.removeAttribute("login");
			httpSession.invalidate();
		}
		return "/user/logout";
	}
}
