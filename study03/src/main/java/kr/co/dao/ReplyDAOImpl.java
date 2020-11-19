package kr.co.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	SqlSession sql; 

	@Override
	public void create(ReplyVO vo) throws Exception {
		sql.insert("reply.insertReply", vo);
		
	}
	
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("start", start);
		map.put("end", end);
		
		return sql.selectList("reply.listReply", map);
	}

	@Override
	public ReplyVO detail(Integer rno) throws Exception {
		return sql.selectOne("reply.detailReply", rno);
	}
	
	@Override
	public void update(ReplyVO vo) throws Exception {
		sql.update("reply.updateReply", vo);
		
	}

	@Override
	public void delete(Integer rno) throws Exception {
		sql.delete("reply.deleteReply", rno);
		
	}

	@Override
	public int count(Integer bno) throws Exception {
		return sql.selectOne("reply.countReply", bno);
	}

}
