package com.monitor.d502.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.d502.model.User;
import com.monitor.d502.model.repository.RecordRepository;
import com.monitor.d502.model.repository.UserRepository;
import com.monitor.d502.uitl.NetResult;

@RestController
public class TestCotroller {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RecordRepository recordRepository;
	@Autowired
	NetResult netResult;
	/**
	 * 开通用户
	 * @return
	 */
	@RequestMapping(value = "/addUser")
	public Object addUser() {
		User user = new User();
		user.setEmil("152@qq.com");
		user.setName("小张");
		User use=userRepository.save(user);
		return user;
	}
	/**
	 * 展示全部用户
	 */
	@RequestMapping(value="/getAllUser")
	public List<User> getAllUser(){
		List<User> all = userRepository.findAll();
		return all;
	}
	@RequestMapping(value="/loginTest")
	public NetResult loginTest(HttpSession session){
			 User user = userRepository.findUserByUid(1);
			 session.setAttribute(User.CURRENT_USER, user);
			 netResult.result=user.getUid();
			return netResult ;
	}
	
}
