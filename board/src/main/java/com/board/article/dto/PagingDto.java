package com.board.article.dto;

/**
 * 게시판 페이징 관련 DTO
 * @author 강은주
 *
 */
public class PagingDto {

	public int pageScale = 10; // 페이지당 게시물 수
	public int blockScale = 5; // 화면당 페이지 수
	private int nowPage; // 현재 페이지
	private int totalPage; // 전체 페이지 수
	private int start; // SQL start
	private int blockStart; // 현재 페이지 블록의 시작 번호
	private int blockEnd; // 현재 페이지 블록의 끝 번호

	/**
	 * 전체 페이지 수, SQL 시작 번호, 블록의 범위 생성자
	 * @param total 전체 게시글 수
	 * @param nowPage 현재 페이지 번호
	 */
	public PagingDto(int total, int nowPage) {
		this.nowPage = nowPage;
		setTotalPage(total); // 전체 페이지 수 계산
		setStart();
		setBlockRange(); // 페이지 블록의 시작, 끝 번호 계산
	}

	/**
	 * 블록 범위 생성
	 */
	public void setBlockRange() {
		blockEnd = (((int) Math.ceil((double) nowPage / (double) blockScale)) * blockScale);
		if (totalPage < blockEnd) blockEnd = totalPage;
		blockStart = blockEnd - blockScale + 1;
		if (blockStart < 1) blockStart = 1;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int total) {
		this.totalPage = (int) Math.ceil(total * 1.0 / pageScale);
	}

	public int getStart() {
		return start;
	}

	public void setStart() {
		this.start = (nowPage - 1) * pageScale;
	}

	public int getBlockStart() {
		return blockStart;
	}

	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

}
