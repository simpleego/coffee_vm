<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	String url="jdbc:mysql://localhost:3306/vm";
	String id ="root";
	String password="pjc0129";
	String sql = "SELECT p_name, COUNT(s_amount) AS COUNT,"+ 
			"SUM(p_saleprice) AS total "+
			"FROM sales " +
			"GROUP BY p_name";
	
	int count=0;
	
	Class.forName("org.mariadb.jdbc.Driver");
	conn = DriverManager.getConnection(url, id, password);
	System.out.println("연결 성공");
	
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	
	int[][] pList = new int[4][2];
	int i = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커피 관리자 </title>
</head>
<body>
<div id="wrapper">
	<h1> 커피 자판기 상품별 판매 현황</h1>
	<table>
		<tr>
			<th>상품명</th>
			<th>판매수량</th>
			<th>판매금액</th>		
		</tr>
		<%while(rs.next()) {%>
			<tr>
				<td><%=rs.getString("p_name") %></td>
				<td><%=rs.getInt("count") %></td>
				<td><%=rs.getInt("total") %></td>	
			<% pList[i][0] = rs.getInt("count"); %>	
			<% pList[i][1] = rs.getInt("total"); %>	
			<%i++; %>
			</tr>
		<%} %>
		
		<tr>
			<td><a href="vm.jsp">자판기 메인</a></td>
			<td><a href="saleChart.jsp">판매현황 차트보기</a></td>
		</tr>
		
		<%
			session.setAttribute("pList", pList);
			rs.close();
			conn.close();
			stmt.close();		
		%>
		
	</table>

</div>

</body>
</html>