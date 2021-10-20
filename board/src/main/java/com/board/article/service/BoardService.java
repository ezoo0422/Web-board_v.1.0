package com.board.article.service;

import java.util.List;

import com.board.article.dto.BoardDto;

public interface BoardService {

	public List<BoardDto> boardList(int start, int pageScale, String searchOption, String keyword) throws Exception;

	public void create(BoardDto boardDto) throws Exception;

	public BoardDto read(int sequence) throws Exception;

	public void update(BoardDto boardDto) throws Exception;

	public void delete(int sequence) throws Exception;

	public void increaseCnt(int sequence) throws Exception;

	public int countBoard(String searchOption, String keyword) throws Exception;


}
