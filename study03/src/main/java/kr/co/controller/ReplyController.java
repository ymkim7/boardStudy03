package kr.co.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.service.ReplyService;
import kr.co.util.ReplyPager;
import kr.co.vo.ReplyVO;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {

	public static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	ReplyService replyService;
	
	//댓글입력
	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {
		logger.info("insert");
		
		String userId = (String) session.getAttribute("userId");
		vo.setReplyer(userId);
		replyService.create(vo);
		
	}
	
	//댓글입력 (Rest방식으로 json전달하여 글쓰기)
	//@ResponseEntity : 데이터 + http status code
	//@ResponseBody : 객체를 json으로 (json - String)
	//@RequestBoyd : json을 객체로
	@RequestMapping(value = "insertRest.do", method = RequestMethod.POST)
	public ResponseEntity<String> insertRest(@RequestBody ReplyVO vo, HttpSession session) throws Exception {
		logger.info("insertRest");
		
		ResponseEntity<String> entity = null;
		
		try {
			String userId = (String) session.getAttribute("userId");
			vo.setReplyer(userId);
			replyService.create(vo);
			
			//댓글입력이 성공하면 성공메시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			//댓글입력이 실패하면 실패 메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		//입력 처리 HTTP 상태 메시지 리턴
		return entity;
		
	}
	
	//댓글 목록 (Controller 방식 : view를 리턴)
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno
							,@RequestParam(defaultValue="1") int curPage
							, ModelAndView mav
							, HttpSession session) throws Exception {
		logger.info("list");
		
		//페이징 처리
		//댓글 갯수
		int count = replyService.count(bno);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();
		int end = replyPager.getPageEnd();
		
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		
		//뷰 이름 지정
		mav.setViewName("board/replyList");
		//뷰에 전달할 데이터 지정
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		
		return mav;
	}
	
	
	//댓글 목록 (RestController Json 방식으로 처리 : 데이터를 리턴)
	@RequestMapping("listJson.do")
	//리턴 데이터를 json으로 변환(RestController 사용시 @ResponseBody 생각가능)
	@ResponseBody
	public List<ReplyVO> listJson(@RequestParam int bno
			, @RequestParam(defaultValue="1") int curPage, HttpSession session) throws Exception {
		logger.info("listJson");
		
		//페이징 처리
		int count = replyService.count(bno);
		ReplyPager pager = new ReplyPager(count, curPage);
		//현재 페이지의 페이징 시작 번호
		int start = pager.getPageBegin();
		//현재 페이지의 페이징 끝 번호
		int end = pager.getPageEnd();
		  
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		  
		return list;
	}
	
	//Controller 추가 사항 - Rest방식으로 댓글 목록, 수정, 삭제 처리
	//댓글 목록 (RestController 방식 : json으로 전달하여 목록생성)
	// /reply/list/1 => 1번 게시물의 댓글 목록 리턴
	// /reply/list/2 => 2번 게시물의 댓글 목록 리턴
	//@PathVariable : url에 입력될 변수값 지정
	@RequestMapping(value = "/list/{bno}/{curPage}", method = RequestMethod.GET)
	public ModelAndView replyList(@PathVariable("bno") int bno , @PathVariable int curPage
					, ModelAndView mav, HttpSession session) throws Exception {
		logger.info("replyList");
		
		//페이징처리
		int count = replyService.count(bno);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		//현재 페이지의 페이징 시작 번호
		int start = replyPager.getPageBegin();
		//현재 페이지의 페이징 끝 번호
		int end = replyPager.getPageEnd();
		  
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		
		//뷰 이름 지정
		mav.setViewName("board/replyList");
		//뷰에 전달할 데이터 지정
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		
		return mav;
	}
	
	//댓글 상세 보기
	// /reply/detail/1 => 1번 댓글의 상세화면 리턴
	// /reply/detail/2 => 2번 댓글의 상세화면 리턴
	//@PathVariable : url에 입력될 변수값 지정
	@RequestMapping(value = "/detail/{rno}", method = RequestMethod.GET)
	public ModelAndView replyDetail(@PathVariable("rno") Integer rno, ModelAndView mav) throws Exception {
		logger.info("replyDetail");
		
		ReplyVO vo = replyService.detail(rno);
		
		//뷰 이름 지정
		mav.setViewName("board/replyDetail");
		//뷰에 전달할 데이터 지정
		mav.addObject("vo", vo);
		
		return mav;
	}
	
	//댓글 수정 처리 - PUT : 전체 수정, PATCH : 일부수정
	//RequestMethod를 여러 방식으로 설정할 경우 {}안에 작성
	@RequestMapping(value = "/update/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo) throws Exception {
		logger.info("replyUpdate");
		
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno);
			replyService.update(vo);
			//댓글 수정이 성공하면 성공 상태 매시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			//댓글 수정이 실패하면 실패 상태 메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

	//댓글 삭제처리
	@RequestMapping(value = "/delete/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> replyDelete(@PathVariable("rno") Integer rno) throws Exception {
		logger.info("replyDelete");
		
		ResponseEntity<String> entity = null;
		
		try {
			replyService.delete(rno);
			//댓글 수정이 성공하면 성공 상태 매시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			//댓글 수정이 실패하면 실패 상태 메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
