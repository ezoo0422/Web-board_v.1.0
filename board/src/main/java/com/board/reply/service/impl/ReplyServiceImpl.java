package com.board.reply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.reply.dao.ReplyDao;
import com.board.reply.dto.ReplyDto;
import com.board.reply.service.ReplyService;

/**
 * 게시판 댓글 처리 Service
 * 게시판 댓글 조회, 작성, 수정, 삭제 처리
 * @author user
 *
 */
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	/**
	 * 댓글 목록 조회
	 */
	@Override
	public List<ReplyDto> list(int sequence) throws Exception {
		return replyDao.list(sequence);
	}

	/**
	 * 댓글 작성 처리
	 */
	@Override
	public void create(ReplyDto replyDto) throws Exception {
		replyDao.create(replyDto);
		
	}

	/**
	 * 댓글 수정 처리
	 */
	@Override
	public void update(ReplyDto replyDto) throws Exception {
		replyDao.update(replyDto);
		
	}

	/**
	 * 댓글 삭제 처리
	 */
	@Override
	public void delete(int replyNo) throws Exception {
		replyDao.delete(replyNo);
		
	}

}
