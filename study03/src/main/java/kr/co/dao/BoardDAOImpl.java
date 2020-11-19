package kr.co.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSession sql;

	@Override
	public void create(BoardVO vo) throws Exception {
		sql.insert("board.insert", vo);
		
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return sql.selectOne("board.view", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		sql.update("board.updateArticle", vo);
		
	}

	@Override
	public void delete(int bno) throws Exception {
		sql.delete("board.deleteArticle", bno);
		
	}

	@Override
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
		
		//검색옵션, 키워드 맵에 정장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		//BETWEEN #{start}, #{end}에 입력될 값을 맵에 저장
		map.put("start", start);
		map.put("end", end);
		
		return sql.selectList("board.listAll", map);
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sql.update("board.increaseViewcnt", bno);
		
	}

	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		
		//검색옵션, 키워드 맵에 정장
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return sql.selectOne("board.countArticle", map);
	}

}
