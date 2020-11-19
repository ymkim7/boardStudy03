package kr.co.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dao.ReplyDAO;
import kr.co.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyDAO dao;

	@Override
	public void create(ReplyVO vo) throws Exception {
		dao.create(vo);
		
	}
	
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session) throws Exception {
		
		List<ReplyVO> items = dao.list(bno, start, end);
		
		//세션에서 현재 사용자 ID값 저장
		String userId = (String) session.getAttribute("userId");
		
		for(ReplyVO vo : items) {
			//댓글 목록중에 비밀 댓글이 있을 경우
			if(vo.getSecretReply().equals("y")) {
				if(userId == null) {
					vo.setReplytext("비밀 댓글입니다.");
			
				//로그인 상태일 경우
				} else {
					//게시물 작성자 저장
					String writer = vo.getWriter();
					//댓글 작성자 저장
					String replyer = vo.getReplyer();
					
					//로구인한 사용자가 게시물의 작성자x 댓글 작성자도x 비밀댓글로 처리
					if(!userId.equals(writer) && !userId.equals(replyer)) {
						vo.setReplytext("비밀 댓글입니다.");
					}
				}
			}
		}
		
		return items;
	}
	
	@Override
	public ReplyVO detail(Integer rno) throws Exception {
		return dao.detail(rno);
	}
	
	@Override
	public void update(ReplyVO vo) throws Exception {
		dao.update(vo);
		
	}

	@Override
	public void delete(Integer rno) throws Exception {
		dao.delete(rno);
		
	}

	@Override
	public int count(Integer bno) throws Exception {
		return dao.count(bno);
	}

}
