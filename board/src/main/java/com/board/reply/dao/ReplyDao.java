package com.board.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.reply.dto.ReplyDto;

/**
 * 댓글 정보 DAO
 * @author 강은주
 *
 */
@Repository
public class ReplyDao {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * 댓글 목록 조회
	 * @param boardSeq 게시글 번호
	 * @return replyList 댓글 목록 전달
	 * @throws Exception
	 */
	public List<ReplyDto> list(int boardSeq) throws Exception {
		return sqlSession.selectList("reply.list", boardSeq);
	}

	/**
	 * 댓글 작성 처리
	 * @param replyDto 댓글 내용
	 * @throws Exception
	 */
	public void create(ReplyDto replyDto) throws Exception {
		sqlSession.insert("reply.create", replyDto);
	}

	/**
	 * 댓글 수정 처리
	 * @param replyDto 댓글 수정 내용
	 * @throws Exception
	 */
	public void update(ReplyDto replyDto) throws Exception {
		sqlSession.update("reply.update", replyDto);
	}

	/**
	 * 댓글 삭제 처리
	 * @param replyNo 댓글 번호
	 * @throws Exception
	 */
	public void delete(int replyNo) throws Exception {
		sqlSession.delete("reply.delete", replyNo);
	}

}
