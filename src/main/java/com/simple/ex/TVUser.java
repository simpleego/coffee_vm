package com.simple.ex;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		
//		TV tv = new SamsungTV();
		//BeanFactory factory = new BeanFactory();
		
		// 1. Spring 컨테이너를 구동한다.
		AbstractApplicationContext factory = 
		new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring 컨테이너로부터 필요한 객체를 요청한다.
		TV tv = (TV) factory.getBean("tv"); //new SamsungTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		
		factory.close();


	}

}
