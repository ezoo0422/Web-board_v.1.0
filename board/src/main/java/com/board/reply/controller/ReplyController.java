package com.board.reply.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.reply.dto.ReplyDto;
import com.board.reply.service.ReplyService;

/**
 * 게시판 댓글 처리 RestController
 * 게시판 댓글 조회, 작성, 수정, 삭제 처리
 * @author 강은주
 *
 */
@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Autowired
	private ReplyService replyService;

	/**
	 * 댓글 작성 처리
	 * @param replyDto 댓글 내용
	 * @throws Exception 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@RequestBody ReplyDto replyDto) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> 댓글 작성 controller" + replyDto.toString());
		replyService.create(replyDto);
	}

	/**
	 * 댓글 조회
	 * @param sequence 게시글 번호
	 * @return replyList 댓글 목록 전달
	 * @throws Exception
	 */
	@RequestMapping(value = "/all/{sequence}", method = RequestMethod.GET)
	public List<ReplyDto> list(@PathVariable("sequence") int sequence) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> 댓글 list controller");
		return replyService.list(sequence);
	}

	/**
	 * 댓글 수정 처리
	 * @param replyNo 댓글 번호
	 * @param replyDto 댓글 수정 내용
	 * @return "success" 처리 결과 전송
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/{replyNo}", method = RequestMethod.PUT)
	public String update(@PathVariable("replyNo") int replyNo, @RequestBody ReplyDto replyDto) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> 수정 controller" + replyDto.toString());
		replyDto.setReplyNo(replyNo);
		replyService.update(replyDto);

		return "success";
	}

	/**
	 * 댓글 삭제 처리
	 * @param replyNo 댓글 번호
	 * @throws Exception
	 */
	@RequestMapping(value = "/{replyNo}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("replyNo") int replyNo) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> 삭제 controller" + replyNo);
		replyService.delete(replyNo);
	}

}
