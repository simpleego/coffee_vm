package com.springbook.biz.vm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoinController {	
	
	@RequestMapping("/coins.do")
	@ResponseBody 
	public String insertCoins(@RequestParam("coin")String coin, HttpSession session) {
		
		int balance = 0;
						
		// 세션 저장소에 저장된 잔액 값을 읽어온다.
		if (session.getAttribute("balance") != null) {
			balance = (int) session.getAttribute("balance");
		}
		
		if (coin.equals("50원")) {
			balance += 50;
		} else if (coin.equals("100원")) {
			balance += 100;
		} else {
			balance += 500;
		}
		
		session.setAttribute("balance", balance);

		return String.valueOf(balance);	
	}
	
	
	@RequestMapping("/return.do")
	@ResponseBody 
	public String returnCoins(@RequestParam("balance")String balance_, HttpSession session) {
		
		int balance = 0;
		
		System.out.println("반환버튼");
						
		// 반환 값을 동전 종류별로 반환처리
		

		return String.valueOf(balance);	
	}

}
