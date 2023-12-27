package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

//@Service
//@Aspect
public class AfterThrowingAdvice {
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	
	@After("allPointcut()")
	public void finallylog() {
		System.out.println("[사후처리] 비즈니스 로직 수행 후 무조건 동작합니다.");
		
	}
	
	@AfterThrowing(pointcut="allPointcut()", throwing = "execeptObj")
	public void execeptionLog(JoinPoint jp, Exception execeptObj ) {
		
		String method = jp.getSignature().getName();
		System.out.println(method +"() 메서드 수행 중 예외 발생");
		
		if(execeptObj instanceof IllegalArgumentException) {
			System.out.println(method+"() 부적합한 값이 입력되었습니다. ");
		}else if(execeptObj instanceof NumberFormatException) {
			System.out.println(method+"() 숫자 형식의 값이 아닙니다. ");
		}else if(execeptObj instanceof Exception) {
			System.out.println(method+"() 문제가 발생했습니다. ");
		}
		
	}

}
