package kr.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.service.BoardService;
import kr.co.service.ReplyService;
import kr.co.util.BoardPager;
import kr.co.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//의존관계 주입
	//IoC 의존관계 역전
	@Autowired
	BoardService boardService;
	
	@Autowired
	ReplyService replyService;
	
	// 01. 게시글 목록
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam(defaultValue="title") String searchOption
							,@RequestParam(defaultValue="") String keyword
							,@RequestParam(defaultValue="1") int curPage) throws Exception {
		logger.info("list");
		
		//레코드의 갯수 계산
		int count = boardService.countArticle(searchOption, keyword);
		
		//페이지 나누기 관련 처리
		BoardPager boardPager = new BoardPager(count, curPage);
		int start = boardPager.getPageBegin();
		int end = boardPager.getPageEnd();
		
		List<BoardVO> list = boardService.listAll(start, end, searchOption, keyword);
		
		Map<String, Object> map = new HashMap<String, Object>();
		//list
		map.put("list", list);
		///레코드 갯수
		map.put("count", count);
		//검색옵션
		map.put("searchOption", searchOption);
		//검색키워드
		map.put("keyword", keyword);
		map.put("boardPager", boardPager);
		
		ModelAndView mav = new ModelAndView();
		//맵에 저장된 데이터를 mav에 저장
		mav.addObject("map", map);
		//뷰를 list.jsp로 설정
		mav.setViewName("board/list");
		
		//list.jsp로 List가 전달된다
		return mav;
	}
	
	// 02_01. 게시글 작성화면
	// @RepquestMapping("board/write.do")
	// value="", method="전송방식"
	@RequestMapping(value = "write.do", method = RequestMethod.GET)
	public String write() {
		logger.info("write");
		
		//write.jsp로 이동
		return "board/write";
	}
	
	// 02_02. 게시물 작성처리
	@RequestMapping(value = "insert.do", method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception {
		logger.info("insert");
		
		//세션에 저장된 userId를 writer에 저장
		String writer = (String) session.getAttribute("userId");
		
		vo.setWriter(writer);
		boardService.create(vo);
		
		return "redirect:list.do";
	}
	
	// 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
	// @RequestParam : get/post 방식으로 전달된 변수 1개
	// Httpsession 세션객체
	@RequestMapping(value = "view.do", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno
						   , @RequestParam int curPage
						   , @RequestParam String searchOption
						   , @RequestParam String keyword, HttpSession session) throws Exception {
		logger.info("view");
		
		//조회수 증가 처리
		boardService.increaseViewcnt(bno, session);
		
		//모델(데이터) + 뷰(화면)를 함께 전달하는 객체
		ModelAndView mav = new ModelAndView();
		
		//댓글의 수를 맵에 저장 : 댓글이 존재하는 게시물의 삭제처리 방지하기 위해
		mav.addObject("conut", replyService.count(bno));
		mav.addObject("dto", boardService.read(bno));
		mav.addObject("curPage", curPage);
		mav.addObject("searchOption", searchOption);
		mav.addObject("keyword", keyword);
		
		return mav;
	}
	
	// 04. 게시글 수정
	// 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception {
		logger.info("update");
		
		boardService.update(vo);
		
		return "redirect:list.do";
	}
	
	// 05. 게시글 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int bno) throws Exception {
		boardService.delete(bno);
		
		return "redirect:list.do";
	}

}
