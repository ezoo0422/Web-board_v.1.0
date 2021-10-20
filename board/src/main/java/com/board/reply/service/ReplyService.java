package com.board.reply.service;

import java.util.List;

import com.board.reply.dto.ReplyDto;

public interface ReplyService {

	List<ReplyDto> list(int sequence) throws Exception;
	
	void create(ReplyDto replyDto) throws Exception;
	
	void update(ReplyDto replyDto) throws Exception;
	
	void delete(int replyNo) throws Exception;

}
