package kr.co.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.vo.MemberVO;

public interface MemberDAO {
	
	//회원목록
	public List<MemberVO> memberList() throws Exception;
	
	//회원입력
	public void insertMember(MemberVO vo) throws Exception;
	
	//회원 정보 상세보기
	public MemberVO viewMember(String userId) throws Exception;
	
	//회원 삭제
	public void deleteMember(String userId) throws Exception;
	
	//회원정보수정
	public void updateMember(MemberVO vo) throws Exception;
	
	//비밀번호 체크
	public boolean checkPw(String userId, String userPw) throws Exception;
	
	//회원 로그인 체크
	public boolean loginCheck(MemberVO vo) throws Exception;
	
	//회원 로그인 정보
	public MemberVO viewMember2(MemberVO vo) throws Exception;
	
	//회원 로그아웃
	public void logout(HttpSession session) throws Exception;

}
