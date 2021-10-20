package com.board.article.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.article.dto.BoardDto;

/**
 * 게시판 게시글 관련 DAO
 * @author 강은주
 *
 */
@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 게시판 목록
	 * @param start 게시판 시작 게시글 번호
	 * @param pageScale 페이지 크기
	 * @param searchOption 게시글 검색 옵션
	 * @param keyword 게시글 검색 키워드
	 * @return boardDto 게시판 게시글 목록 전달
	 * @throws Exception
	 */
	public List<BoardDto> boardList(int start, int pageScale, String searchOption, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("pageScale", pageScale);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectList("board.boardList", map);
	}
	
	/**
	 * 게시글 작성 처리
	 * @param boardDto 작성 게시글 내용
	 * @throws Exception
	 */
	public void create(BoardDto boardDto) throws Exception {
		sqlSession.insert("board.insert", boardDto);
	}

	/**
	 * 게시글 상세 내용 조회
	 * @param sequence 게시글 번호
	 * @return boardDto 게시글 상세 내용 전달
	 * @throws Exception
	 */
	public BoardDto read(int sequence) throws Exception {
		return sqlSession.selectOne("board.view", sequence);
	}

	/**
	 * 게시글 수정 처리
	 * @param vo 게시글 수정 내용
	 * @throws Exception
	 */
	public void update(BoardDto vo) throws Exception {
		sqlSession.update("board.update", vo);
	}

	/**
	 * 게시글 삭제 처리
	 * @param sequence 게시글 번호
	 * @throws Exception
	 */
	public void delete(int sequence) throws Exception {
		sqlSession.delete("board.delete", sequence);
	}

	/**
	 * 조회수 증가 처리
	 * @param sequence 게시글 번호
	 * @throws Exception
	 */
	public void updatecnt(int sequence) throws Exception {
		sqlSession.update("board.updateCnt", sequence);
	}

	/**
	 * 게시글 수 계산
	 * @param searchOption 게시글 검색 옵션
	 * @param keyword 게시글 검색 키워드
	 * @return countBoard 게시글 수
	 * @throws Exception
	 */
	public int countBoard(String searchOption, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectOne("board.countBoard", map);
	}
	
}
