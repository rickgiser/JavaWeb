package com.wx.ssm.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wx.ssm.entity.User;
import com.wx.ssm.service.UserService;

@SessionAttributes({"user","name"})
@RequestMapping(value="/user")
@Controller
@Scope(value="prototype")
public class UserHandler {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@Param("username") String username,@Param("password") String password,
			Map<String, Object> map,HttpSession session){
		User user = userService.login(username, password);
		if(user != null && user.getEnabled() == 1 && user.getPassword().equals(password)){
			map.put("user", user);
			return "redirect:/success";
		}
		return "redirect:/index.jsp";
	}
}
