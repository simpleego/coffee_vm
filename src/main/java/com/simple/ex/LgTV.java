package com.simple.ex;

import org.springframework.stereotype.Component;

@Component("lgtv")
public class LgTV implements TV {	
	
	Speaker speaker;
	
	public LgTV() {
		System.out.println("LG TV 객체를 생성한다.");
	}
	
	
	@Override
	public void powerOn() {
		System.out.println("LgTVTV ---전원을 끈다");
	}
		
	@Override
	public void powerOff() {
		System.out.println("LgTVTV ---전원을 켠다");		
	}
	
	@Override
	public void volumeUp() {
		speaker.volumeUp();
		System.out.println("LgTVTV ---소리 올린다.");		
	}
	
	@Override
	public void volumeDown() {
		speaker.volumeDown();
		System.out.println("LgTVTV ---소리 내린다.");		
	}
	
	public void channelUp() {
		System.out.println("채널을 올린다.");
	}
	
	public void channelDown() {
		System.out.println("채널을 내린다.");
	}

}
