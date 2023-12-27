package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOSpring {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// SQL 명령어 준비 (SELECT * FROM BOARD)
	private final String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content) "
			+ "VALUES( (SELECT nvl(max(seq),0)+1 FROM board),?,?,?)";

	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_UPDATE = "update board set title=? , content=? where seq=?";
	
	private final String BOARD_GET = "SELECT * FROM board WHERE seq = ?";
	private final String BOARD_LIST = "SELECT * FROM board ORDER BY seq DESC";
	
	private final String BOARD_LIST_T = 
			"SELECT * FROM board WHERE title like '%'||?||'%' ORDER BY seq DESC";
	
	private final String BOARD_LIST_C = 
			"SELECT * FROM board WHERE content like '%'||?||'%' ORDER BY seq DESC";

	private final String BOARD_LIST_W = 
			"SELECT * FROM board WHERE writer like '%'||?||'%' ORDER BY seq DESC";
	
	// 글 등록 기능
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC Template로 insertBoard() 기능처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC Template로 updateBoard() 기능처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());		
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC Template로 deleteBoard() 기능처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());	
	}

	// 글 상세 
	public BoardVO getBoard(BoardVO vo) {
		
		System.out.println("===> JDBC Template로 getBoard() 기능처리");
		
		System.out.println(vo.getSeq());
		
		Object[] args = { vo.getSeq() };
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowmapper() );			
	}
	
	// 글 목록
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("===> JDBC Template로 getBoardList() 기능처리");
		Object[] args = {vo.getSearchKeyword()};
		
		if(vo.getSearchCondition().equals("TITLE")) {
			return jdbcTemplate.query(BOARD_LIST_T, args, new BoardRowmapper());
			
		}else if (vo.getSearchCondition().equals("CONTENT")) {
			return jdbcTemplate.query(BOARD_LIST_C, args, new BoardRowmapper());
			
		}else if (vo.getSearchCondition().equals("WRITER")) {
			return jdbcTemplate.query(BOARD_LIST_W, args, new BoardRowmapper());
			
		}
		
		return null;
	}
	
	class BoardRowmapper implements RowMapper<BoardVO> {

		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt("SEQ"));
			board.setTitle(rs.getString("TITLE"));
			board.setWriter(rs.getString("WRITER"));
			board.setContent(rs.getString("CONTENT"));
			board.setRegDate(rs.getDate("REGDATE"));
			board.setCnt(rs.getInt("CNT"));
			
			return board;
		}
		
	}

}
