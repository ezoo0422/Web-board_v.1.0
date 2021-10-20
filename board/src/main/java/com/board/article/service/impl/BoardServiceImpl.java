package com.board.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.article.dao.BoardDao;
import com.board.article.dto.BoardDto;
import com.board.article.service.BoardService;

/**
 * 게시판 게시글 관련 controller
 * 게시판 목록 출력, 글작성, 수정, 삭제, 조회
 * @author 강은주
 *
 */
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	/**
	 * 게시판 목록 제공
	 * @return boardList
	 */
	@Override
	public List<BoardDto> boardList(int start, int pageScale, String searchOption, String keyword) throws Exception {
		return dao.boardList(start, pageScale, searchOption, keyword);
	}

	/**
	 * 게시글 VO에 담아 DB 저장
	 */
	@Override
	public void create(BoardDto boardDto) throws Exception {
		dao.create(boardDto);
	}

	/**
	 * 게시글 상세 내용 제공
	 * @return boardDetail
	 */
	@Override
	public BoardDto read(int sequence) throws Exception {
		return dao.read(sequence);
	}

	/**
	 * 게시글 수정
	 */
	@Override
	public void update(BoardDto boardDto) throws Exception {
		dao.update(boardDto);
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public void delete(int sequence) throws Exception {
		dao.delete(sequence);
	}

	/**
	 * 조회수 증가
	 */
	@Override
	public void increaseCnt(int sequence) throws Exception {
		dao.updatecnt(sequence);
	}

	/**
	 * 전체 게시글 수 제공
	 * @return countBoard 게시글 수
	 */
	@Override
	public int countBoard(String searchOption, String keyword) throws Exception {
		return dao.countBoard(searchOption, keyword);
	}

}
