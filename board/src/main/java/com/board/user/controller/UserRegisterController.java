package com.board.user.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.user.dto.UserDto;
import com.board.user.service.UserService;

/**
 * 회원가입 처리 Controller (회원 가입, id 중복 확인)
 * @author 강은주
 *
 */
@Controller
@RequestMapping("/user")
public class UserRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(UserRegisterController.class);

	@Autowired
	private UserService userService;

	/**
	 * 회원가입 화면 출력
	 * @return /user/register 회원가입 화면
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet() throws Exception {
		return "/user/register";
	}

	/**
	 * 회원가입 처리
	 * @param userDto 회원 정보
	 * @param redirectAttributes 회원가입 결과 전송
	 * @return redirect:/user/login 로그인 화면 redirect
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(UserDto userDto, RedirectAttributes redirectAttributes) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 회원가입 " + userDto.toString());

		String hashedPw = BCrypt.hashpw(userDto.getUserPw(), BCrypt.gensalt());
		userDto.setUserPw(hashedPw);
		userService.register(userDto);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");

		return "redirect:/user/login";
	}

	/**
	 * 아이디 중복 확인
	 * @param userId 사용자 입력 ID
	 * @return countId 동일 id 존재 개수
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/register/checkId", method = RequestMethod.POST, produces = "application/json")
	public int checkId(@RequestBody String userId) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> 아이디 중복 체크 " + userId);
		int countId = userService.checkId(userId);
		return countId;
	}
}
