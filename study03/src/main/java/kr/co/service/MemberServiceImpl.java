package kr.co.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dao.MemberDAO;
import kr.co.vo.MemberVO;

//현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class MemberServiceImpl implements MemberService {
	
	//MemberDAOImpl 객체를 스프링에서 생성하여 주입
	@Autowired
	MemberDAO dao;

	@Override
	public List<MemberVO> memberList() throws Exception {
		return dao.memberList();
	}

	@Override
	public void insertMember(MemberVO vo) throws Exception {
		dao.insertMember(vo);

	}

	@Override
	public MemberVO viewMember(String userId) throws Exception {
		return dao.viewMember(userId);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		dao.deleteMember(userId);

	}

	@Override
	public void updateMember(MemberVO vo) throws Exception {
		dao.updateMember(vo);

	}

	@Override
	public boolean checkPw(String userId, String userPw) throws Exception {
		return dao.checkPw(userId, userPw);
	}

	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) throws Exception {
		boolean result = dao.loginCheck(vo);
		
		//true일 경우 세션 등록
		if(result) {
			MemberVO vo2 = viewMember2(vo);
			
			//세션 변수 등록
			session.setAttribute("userId", vo2.getUserId());
			session.setAttribute("userName", vo2.getUserName());
		}
		
		return result;
	}

	@Override
	public MemberVO viewMember2(MemberVO vo) throws Exception {
		return dao.viewMember2(vo);
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
		
	}

}
