<%@page import="java.util.List"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<title>게시판 목록 보기</title>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<style>
#board {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 700px;
}

#board td, #board th {
  border: 1px solid #ddd;
  padding: 8px;
}

#board tr:nth-child(even){background-color: #f2f2f2;}

#board tr:hover {background-color: #ddd;}

#board th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}

</style>
</head>
<body>

	<div class="container">
		<h1><spring:message code="message.board.list.mainTitle"/></h1>
		<h3>
			${userName}<spring:message code="message.board.list.welcomeMsg"/><a href="logout.do">Log-out</a>
		</h3>

		<!-- 검색 시작 -->
		<form action="getBoardList.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
					<select name="searchCondition">
						<c:forEach items="${conditionMap }" var="option">
							<option value="${option.value }">${option.key }
						</c:forEach>							
					</select> 
					<input name="searchKeyword" type="text" /> 
					<input type="submit" value="<spring:message code="message.board.list.search.condition.btn"/>" /></td>
				</tr>
			</table>
		</form>

		<!-- 검색 종료 -->

		<table class="table table-hover">
			<thead>
				<tr>
					<th width="100"><spring:message code="message.board.list.table.head.seq"/></th>
					<th width="200"><spring:message code="message.board.list.table.head.title"/></th>
					<th width="150"><spring:message code="message.board.list.table.head.writer"/></th>
					<th width="150"><spring:message code="message.board.list.table.head.regDate"/></th>
					<th width="100"><spring:message code="message.board.list.table.head.cnt"/></th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.seq}</td>
					<td align="left">
						<a href="getBoard.do?seq=${board.seq}"> ${board.title}</a>
					</td>
					<td>${board.writer}</td>
					<td>${board.regDate}</td>
					<td>${board.cnt}</td>
				</tr>
			</c:forEach>
		 </tbody>
		</table>
		<br> <a href="insertBoard.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
	</div>

</body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</html>