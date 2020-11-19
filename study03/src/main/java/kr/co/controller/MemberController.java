package kr.co.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.service.MemberService;
import kr.co.vo.MemberVO;

//현재의 클래스를 controller bean에 등록시킴
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	//MemberService 객체를 스플엥서 생성하여 주입시킴
	@Autowired
	MemberService memberService;
	
	//01. 회원 목록
	//URL 패턴 맵핑
	@RequestMapping("member/list.do")
	public String memberList(Model model) throws Exception {
		logger.info("memberList");
		
		//controller => service => dao 요청
		List<MemberVO> list = memberService.memberList();
		model.addAttribute("list", list);
		
		return "member/memberList";
	}
	
	//02-01 회원 등록 처리 후 => 회언목으로 리다이렉트
	@RequestMapping("member/write.do")
	public String memberWrite() {
		logger.info("memberWrite");
		
		return "member/memberWrite";
	}
	
	//02-02 회원등록 처리 후 => 회원목으로 리다이렉트
	//@ModelAttribute 폼에서 입력한 데이터가 저장
	@RequestMapping("member/insert.do")
	public String memberInsert(@ModelAttribute MemberVO vo) throws Exception {
		
	//폼에 입력한 데이터를 받아오는 방법 +2
	//public String memberInsert(HttpServlet request)
	//public String memberInsert(String userId, String userPw, String userName, String userEmail)
		logger.info("memberInsert");
		
		//테이블에 레코드 입력
		memberService.insertMember(vo);
		
		// * (/)의 유무의 차이
		// /member/list.do : 루트 디렉토리를 기준
		// member/list.do : 현재 디렉토리를 기준
		// member_list.jsp.로 리다이렉트
		return "redirect:/member/list.do";
	}
	
	//03. 회원 상세정보 조회
	@RequestMapping("member/view.do")
	public String memberView(String userId, Model model) throws Exception {
		logger.info("memberView");
		
		//회원 정보를 model에 저장
		model.addAttribute("dto", memberService.viewMember(userId));
		
		//System.out.println("클릭한 아이디 확인 : " + userId);
		logger.info("클릭한 아이디 확인 : " + userId);
		
		//memberView.jsp로 포워드
		return "member/memberView";
	}
	
	//04. 회원 정보 수정 처리
	@RequestMapping("member/update.do")
	public String memberUpdate(@ModelAttribute MemberVO vo, Model model) throws Exception {
		logger.info("memberUpdate");
		
		//비밀번호 체크
		boolean result = memberService.checkPw(vo.getUserId(), vo.getUserPw());
		
		//비밀번호가 맞다면 삭제 처리 후, 전체 회원목록으로 리다이렉트
		if(result) {
			memberService.updateMember(vo);
			return "redirect:/member/list.do";
		
		//비밀번호가 틀리다면 div에 불일치 문구 출력
		} else {
			MemberVO vo2 = memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "비밀번호 불일치");
			return "member/memberView";
		}
	}
	
	//05. 회원정보 삭제 처리
	//@RequestMapping : URL mapping
	//@RequestParam : get or post 방식으로 전달된 변수값
	@RequestMapping("member/delete.do")
	public String memberDelete(@RequestParam String userId, @RequestParam String userPw, Model model) throws Exception {
		logger.info("memberDelete");
		
		//비밀번호 체크
		boolean result = memberService.checkPw(userId, userPw);
		
		//비밀번호가 맞다면 삭제 처리 후, 전체 회원목록으로 리다이렉트
		if(result) {
			memberService.deleteMember(userId);
			return "redirect:/member/list.do";
		
		//비밀번호가 틀리다면 div에 불일치 문구 출력
		} else {
			model.addAttribute("message", "비밀번호 불일치");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "member/memberView";
		}
		
	}
	
	//로그인 화면
	@RequestMapping("login.do")
	public String login() {
		logger.info("login");
		
		return "member/login";
	}
	
	//로그인 처리
	@RequestMapping("loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute MemberVO vo, HttpSession session) throws Exception {
		logger.info("loginCheck");
		
		boolean result = memberService.loginCheck(vo, session);
		ModelAndView mav = new ModelAndView();
		
		//로그인 성공
		if(result == true) {
			//main.jsp로 이동
			mav.setViewName("main");
			mav.addObject("msg", "sucess");
		
		//로그인 실패
		} else {
			//login.jsp로 이동
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
		}
		
		return mav;
	}
	
	//로그아웃 처리
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) throws Exception {
		logger.info("logout");
		
		memberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		mav.addObject("mag", "logout");
		
		return mav;
	}
	
}
