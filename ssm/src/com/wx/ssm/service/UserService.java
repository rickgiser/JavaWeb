package com.wx.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.ssm.dao.UserMapper;
import com.wx.ssm.entity.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String username,String password){
		User user = userMapper.getUserByName(username);
		return user;
	}
}
