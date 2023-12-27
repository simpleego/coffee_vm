package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {

	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// SQL 명령어 준비 (SELECT * FROM BOARD)
	private final String BOARD_INSERT = "INSERT INTO board(seq,title, writer, content) "
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

		System.out.println("===> JDBC로 insertBoard() 기능처리");

		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}

	}

	// 글 수정
	public void updateBoard(BoardVO vo) {

		System.out.println("===> JDBC로 updateBoard() 기능처리");
		System.out.println(vo.getSeq());
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());

		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}

	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {

		System.out.println("===> JDBC로 deleteBoard() 기능처리");

		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}

	}

	// 글 상세 
	public BoardVO getBoard(BoardVO vo) {
		
		System.out.println(vo.getSeq());

		System.out.println("===> JDBC로 getBoard() 기능처리");

		BoardVO board = null;

		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			rs.next();

			board = new BoardVO();
			board.setSeq(rs.getInt("SEQ"));
			board.setTitle(rs.getString("TITLE"));
			board.setWriter(rs.getString("WRITER"));
			board.setContent(rs.getString("CONTENT"));
			board.setRegDate(rs.getDate("REGDATE"));
			board.setCnt(rs.getInt("CNT"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		System.out.println(board);
		return board;
	}
	
	// 글 목록
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("===> JDBC로 getBoardList() 기능처리");
		
		System.out.println("query: "+ BOARD_LIST_T );
		System.out.println("query: "+ BOARD_LIST_C );
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			if(vo.getSearchCondition().equals("TITLE")) {
				pstmt = conn.prepareStatement(BOARD_LIST_T);				
			}else if(vo.getSearchCondition().equals("CONTENT")) {
				pstmt = conn.prepareStatement(BOARD_LIST_C);
			}else if(vo.getSearchCondition().equals("WRITER")) {
				pstmt = conn.prepareStatement(BOARD_LIST_W);
			}
			
//			pstmt = conn.prepareStatement(BOARD_LIST);
			pstmt.setString(1, vo.getSearchKeyword());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}		
		
		return boardList;
	}

}
