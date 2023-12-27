package com.springbook.biz.user.impl;

import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;


//@Service
public class UserServiceImpl implements UserService{
	
	
	private UserDAO userDAO;	

	@Override
	public UserVO getUser(UserVO vo) {			
		return userDAO.getUser(vo);
	}
	
	
	public void setUserDAO(UserDAO userDao) {
		this.userDAO = userDao;		
	}

}
