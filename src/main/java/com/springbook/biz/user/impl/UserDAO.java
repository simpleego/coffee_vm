package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

//@Repository("userDAO")
public class UserDAO implements UserService {
	
	// JDBC 관련변수
	private Connection conn=null;
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// SQL
	private String USER_GET ="SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
	
	// 회원 조회
	@Override
	public UserVO getUser(UserVO vo) {
		
		UserVO user = null;
		
		System.out.println("==> JDBC로 getUser() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return user;
		
	}
	

}
