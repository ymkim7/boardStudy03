package kr.co.dao;

import java.util.List;

import kr.co.vo.ReplyVO;

public interface ReplyDAO {
	
	//댓글 입력
	public void create(ReplyVO vo) throws Exception;
	
	//댓글 목록
	public List<ReplyVO> list(Integer bno, int start, int end) throws Exception;
	
	//댓글 상세보기
	public ReplyVO detail(Integer rno) throws Exception;
	
	//댓글 수정
	public void update(ReplyVO vo) throws Exception;
	
	//댓글 삭제
	public void delete(Integer rno) throws Exception;
	
	//댓글 갯수
	public int count(Integer bno) throws Exception;

}
