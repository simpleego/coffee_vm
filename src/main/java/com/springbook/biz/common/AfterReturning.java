package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

import com.springbook.biz.user.UserVO;

public class AfterReturning {
	
	public void afterReturningLog(JoinPoint jp, Object returnObj) {
		
		String method = jp.getSignature().getName();
		if(returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			
			if(user.getRole().equals("Admin")) {
				System.out.println(user.getName()+"로그인(Admin)");
			}
		}		
		
		System.out.println("[사후처리]: "+ method+"() 메서드 리턴값 :"
					+returnObj.toString());		
	}

}
