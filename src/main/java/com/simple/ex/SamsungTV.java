package com.simple.ex;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("tv")
public class SamsungTV implements TV {
	
	//SonySpeaker speaker;
	@Autowired	
	Speaker speaker;
	private int price;
	
	public SamsungTV() {
		System.out.println(" 삼성 TV 객체를 생성합니다.");		
		//speaker = new SonySpeaker();
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public void powerOn() {
		System.out.println("SamsungTV ---전원을 켠다");
	}
	
	
	public SamsungTV(Speaker speaker, int price) {	
		this.speaker = speaker;
		this.price = price;
	}
	
	public void powerOff() {
		System.out.println("SamsungTV ---전원을 끈다");
	}
	
	public void volumeUp() {
		speaker.volumeUp();
		System.out.println("SamsungTV ---소리 올린다.");
	}
	public void volumeDown() {
		speaker.volumeDown();		
		System.out.println("SamsungTV ---소리 내린다.");
	}
	
	public void initMethod() {
		System.out.println(" 객체 생성 이후에 초기화 작업을 수행한다.");
	}
	
	public void destroyMethod() {
		System.out.println(" 객체 소멸시 필요한 작업을 수행한다.");		
	}
	
	public void setChanne(int num) {
		int channel;
		channel = num;
		System.out.println("채널을 설정한다.");
	}


	@Override
	public String toString() {
		return "SamsungTV [speaker=" + speaker + ", price=" + price + "]";
	}
		

}
