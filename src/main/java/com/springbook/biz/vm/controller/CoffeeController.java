package com.springbook.biz.vm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoffeeController {	
	
	private static final int MILK_COFFEE=400;
	private static final int SUGAR_COFFEE=300;
	private static final int CREAM_COFFEE=300;
	private static final int BLACK_COFFEE=200;
	
	@RequestMapping("/coffee.do")
	@ResponseBody 
	public Map<String, String> insertCoins(@RequestParam("coffee")String coffee, HttpSession session) {
		
		int balance = 0;
		String outCoffee = "";
		
		System.out.println("커피 판매 : "+coffee);
		
		Map<String, String> saleMap = new HashMap<String,String>(); 
						
		// 세션 저장소에 저장된 잔액 값을 읽어온다.
		if (session.getAttribute("balance") != null) {
			balance = (int) session.getAttribute("balance");
		}
		
		if (coffee.equals("밀크커피")) {
			outCoffee = "milkOut";
			balance -= MILK_COFFEE;
		}else if (coffee.equals("프림커피")) {
			outCoffee = "creamOut";
			balance -= CREAM_COFFEE;
		}else if (coffee.equals("설탕커피")) {
			outCoffee = "sugarOut";
			balance -= SUGAR_COFFEE;
		}else if (coffee.equals("블랙커피")) {
			outCoffee = "blackOut";
			balance -= BLACK_COFFEE;
		}
			
		session.setAttribute("balance", balance);
		
		saleMap.put("coffee", outCoffee);
		saleMap.put("balance", String.valueOf(balance));		

		return saleMap;
	}	

}
