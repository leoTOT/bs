package com.monitor.d502.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.d502.model.User;
import com.monitor.d502.model.repository.UserRepository;
import com.monitor.d502.uitl.NetResult;

@RestController
public class userController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	NetResult netResult;
	@RequestMapping(value="/updatedPass",method = RequestMethod.POST)
	public NetResult updatePassword(@RequestParam String oldPassword,@RequestParam String newPass,@RequestParam String newPass2,HttpSession session,HttpServletRequest request) {	
		String id = (String)session.getAttribute("uesrid");
		User user = userRepository.findUserByUserName(id);
		if(!user.isPasswordCorrect(oldPassword)){
			netResult.status=0;
			netResult.result="账号原密码错误";
			return netResult;
		}else{ 
			int update = userRepository.updateStatusById(newPass,id);
			if(update!=0) 
			{
				netResult.status=0;
				netResult.result="密码已更改";
				return netResult;
			}else
			{
			netResult.status=-1;
			netResult.result="出现未知错误";
			return netResult;
			}
		}		
	}
	@RequestMapping(value="/getUser",method = RequestMethod.POST)
	public User getUser(HttpSession session) {
		String id = (String)session.getAttribute("userid");
		User user = userRepository.findUserByUserName(id);
		return user;
	}
}
