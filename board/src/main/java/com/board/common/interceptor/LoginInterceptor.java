package com.board.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 로그인 처리 인터셉터
 * @author 강은주
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	/**
	 * 로그인 처리 인터셉터
	 * controller 처리 후 정보를 받아 권한 부여
	 */
	@Override
	public void postHandle(HttpServletRequest request, 
						   HttpServletResponse response, 
						   Object handler,
						   ModelAndView modelAndView) throws Exception {
		HttpSession httpSession = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userDto = modelMap.get("user");

		if (userDto != null) {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> new login success");
			httpSession.setAttribute(LOGIN, userDto);
			response.sendRedirect("/");
		}
	}

	/**
	 * 로그인 처리 전에 로그인 정보 지우기
	 * controller 이전에 진행
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, 
							 Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute(LOGIN) != null) {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> clear login data before");
			httpSession.removeAttribute(LOGIN);
		}
		return true;
	}

}
