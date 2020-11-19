package kr.co.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.vo.MemberVO;

//현재 클래스의 DAO bean으로 등록
@Repository
public class MemberDAOImpl implements MemberDAO {
	
	//SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	//의존관계 주입 (Dependency Injection, DI)
	//느스한 결함
	//Ioc(Inversion of Control, 제어의 역전)
	//(AutoWirde) 어노테이션이 없으면 sqlSession은 널이지만 어노테이션이 있으면 외부에서 객체를 주입시켜주게 된다.
	//try catch 문, finally 문, 객체를 close할 필요가 없어졌다.
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<MemberVO> memberList() throws Exception {
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberVO vo) throws Exception {
		sqlSession.insert("member.insertMember", vo);

	}

	@Override
	public MemberVO viewMember(String userId) throws Exception {
		return sqlSession.selectOne("member.viewMember", userId);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		sqlSession.delete("member.deleteMember", userId);

	}

	@Override
	public void updateMember(MemberVO vo) throws Exception {
		sqlSession.update("member.updateMember", vo);

	}

	@Override
	public boolean checkPw(String userId, String userPw) throws Exception {
		
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("userId", userId);
		map.put("userPw", userPw);
		
		int count = sqlSession.selectOne("member.checkPw", map);
		
		if(count ==1) result = true;
			
		return result;
		
	}

	@Override
	public boolean loginCheck(MemberVO vo) throws Exception {
		String name = sqlSession.selectOne("member.loginCheck", vo);
		return (name == null) ? false : true;
	}

	@Override
	public MemberVO viewMember2(MemberVO vo) throws Exception {
		return sqlSession.selectOne("member.viewMember", vo);
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
