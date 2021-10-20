package com.board.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.article.dto.BoardDto;
import com.board.article.dto.PagingDto;
import com.board.article.service.BoardService;

/**
 * 게시판 게시글 관련 controller
 * 게시판 목록 출력, 글작성, 수정, 삭제, 조회
 * @author 강은주
 *
 */
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	/**
	 * 게시판 목록 출력 (main 화면)
	 * @param searchOption 게시글 검색 옵션
	 * @param keyword 게시글 검색 키워드
	 * @param nowPage 게시판 현재 페이지
	 * @param model
	 * @return main 메인 화면
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String paging(@RequestParam(defaultValue="BOARD_SUBJECT") String searchOption,
						 @RequestParam(defaultValue = "") String keyword, 
						 @RequestParam(defaultValue = "1") int nowPage,
						 Model model) throws Exception {
		// 게시글 수 계산
		int total = boardService.countBoard(searchOption, keyword);

		// 페이징 처리
		PagingDto paging = new PagingDto(total, nowPage);
		int start = paging.getStart();
		int pageScale = paging.pageScale;
		List<BoardDto> boardList = boardService.boardList(start, pageScale, searchOption, keyword);
		
		model.addAttribute("total", total);
		model.addAttribute("boardList", boardList);
		model.addAttribute("keyword", keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("start", start);
		model.addAttribute("searchOption", searchOption);
		return "main";
	}

	/**
	 * 게시글 작성 화면
	 * @return write 게시글 작성 화면
	 */
	@RequestMapping("/write")
	public String write() {
		return "write";
	}

	/**
	 * 게시글 작성 처리
	 * @param boardDto 게시글 작성 내용
	 * @param model
	 * @return redirect:/ 메인 화면 재출력
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardDto boardDto, 
						 Model model) throws Exception {
		boardService.create(boardDto);
		return "redirect:/";
	}

	/**
	 * 게시글 상세 화면
	 * @param sequence 게시글 번호
	 * @param searchOption 게시글 검색 옵션
	 * @param keyword 게시글 검색 키워드
	 * @param nowPage 게시판 현재 페이지
	 * @param model
	 * @return read 게시글 상세 화면
	 * @throws Exception
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String read(@RequestParam("sequence") int sequence, 
					   @RequestParam(defaultValue="BOARD_SUBJECT") String searchOption,
					   @RequestParam(defaultValue = "") String keyword,
					   @RequestParam(defaultValue = "1") int nowPage, 
					   Model model) throws Exception {
		// 조회수 처리
		boardService.increaseCnt(sequence);

		model.addAttribute("view", boardService.read(sequence));
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("keyword", keyword);
		model.addAttribute("nowPage", nowPage);
		return "read";
	}

	/**
	 * 게시글 수정 화면
	 * @param sequence 게시글 번호
	 * @param model
	 * @return update 수정 화면
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(@RequestParam("sequence") int sequence, 
							 Model model) throws Exception {
		model.addAttribute("view", boardService.read(sequence));
		return "update";
	}

	/**
	 * 게시글 수정 처리
	 * @param boardDto 게시글 수정 내용
	 * @param model 
	 * @return redirect:/ 메인 화면 redirect
	 * @throws Exception 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardDto boardDto,
					     Model model) throws Exception {
		boardService.update(boardDto);
		return "redirect:/";
	}

	/**
	 * 게시글 삭제 처리
	 * @param sequence 게시글 번호
	 * @return redirect:/ 메인 화면 redirect
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("sequence") int sequence) throws Exception {
		boardService.delete(sequence);
		return "redirect:/";
	}
	
}
