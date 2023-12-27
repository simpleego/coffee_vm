package com.springbook.biz.user.impl;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserVO;

public class UserServiceClient {

	public static void main(String[] args) {
		

		// 1. SpringContainer 구동(주문서)
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. SpringContainer로부터 준비된 객체를 요청(Lookup)
		UserService userService = (UserService) container.getBean("userService");
		
		// 3. 로그인 테스트
		UserVO  vo = new  UserVO();
		
		vo.setId("test");
		vo.setPassword("test123");
		
		UserVO user = userService.getUser(vo);
		
		if(user != null ) {
			System.out.println(user.getName()+"환영합니다.");
		}else {
			System.out.println("로그인 실패");
		}
		
		// 4. Spring 컨테이너 종료
		container.close();
	}

}
