package com.board.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 게시글 사용 권한 인터셉터
 * @author 강은주
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	/**
	 * 로그인 하지 않은 사용자에게는 권한 부여 하지 않고 login 페이지로 이동
	 * return true/false 로그인 한 사용자는 true, 로그인 안한 사용자는 false
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession();
		
		if (httpSession.getAttribute("login") == null) {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 로그인 하지 않은 사용자");
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}

}
