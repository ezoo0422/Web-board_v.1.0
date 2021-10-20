package com.board.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.board.reply.controller.ReplyController;

/**
 * 예외 처리 controllerAdvice
 * @author 강은주
 *
 */
@ControllerAdvice
public class CommonExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	/**
	 * 타입 불일치 오류 처리
	 * @param e MethodArgumentTypeMismatchException
	 * @return modelAndView 오류 내용 화면 전달
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ModelAndView MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>요청 인자 값의 type 오류 발생 " + e.toString());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comment", "요청 인자 값의 type 오류 발생");
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("/error/error");

		return modelAndView;
	}

	/**
	 * 요청 인자 누락 오류 처리
	 * @param e MissingServletRequestParameterException
	 * @return modelAndView 오류 내용 화면 전달
	 */ 
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView MissingServletRequestParameterException(MissingServletRequestParameterException e) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>요청 인자 누락 오류 발생 " + e.toString());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comment", "요청 인자 누락 오류 발생");
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("/error/error");
		return modelAndView;
	}

	/**
	 * null값 오류 처리
	 * @param e NullPointerException
	 * @return modelAndView 오류 내용 화면 전달
	 */
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView NullPointerException(NullPointerException e) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>null 값 오류 발생 " + e.toString());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comment", "null 값 오류 발생");
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("/error/error");
		return modelAndView;
	}

	/**
	 * compile 오류 처리
	 * @param e Exception
	 * @return modelAndView 오류 내용 화면 전달
	 */
	@Order(Ordered.LOWEST_PRECEDENCE)
	@ExceptionHandler(Exception.class)
	public ModelAndView commonException(Exception e) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>예외 발생 " + e.toString());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("/error/common");
		return modelAndView;
	}

	/**
	 * runtime 오류 처리
	 * @param e RuntimeException
	 * @return modelAndView 오류 내용 화면 전달
	 */
	@Order(Ordered.LOWEST_PRECEDENCE)
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView commonException(RuntimeException e) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>> runtime 예외 발생 " + e.toString());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("/error/common");
		return modelAndView;
	}

}
