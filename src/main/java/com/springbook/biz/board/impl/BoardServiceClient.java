package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardVO;

public class BoardServiceClient {
	
	public static void main(String[] args) {
		
		// 1. SpringContainer 구동(주문서)
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. SpringContainer로부터 준비된 객체를 요청(Lookup)
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		// 3. 글 등록 기능 테스트
		BoardVO vo = new BoardVO();
		vo.setSeq(100);
		vo.setTitle("스프링 배우기-100");
		vo.setWriter("spring");
		vo.setContent("Spring JDBC Template으로 설정합니다...");
		
		boardService.insertBoard(vo);	
		
		
		// 4. 글목록 기능 테스트
		List<BoardVO>  boardList = boardService.getBoardList(vo);
		
		for(BoardVO board : boardList) {
			System.out.println(board.toString());
		}
		
		// 5. SpringContaner 종료
		container.close();		
		
	}

}
