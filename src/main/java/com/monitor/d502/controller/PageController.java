package com.monitor.d502.controller;

import javax.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.monitor.d502.configuration.RequiredPermission;
import com.monitor.d502.permission.PermissionConstants;

@Controller
@EnableScheduling
@SpringBootApplication
public class PageController {

	@RequestMapping(value = "/abc")
	public String testDemo() {
		return "/demo";
	}

	@RequestMapping(value = "/home")
	public String home(HttpSession session) {
		return "/index";
	}

	@RequestMapping(value = "/index")
	@RequiredPermission(PermissionConstants.SELECT_LIST)
	public String index(HttpSession session) {
		return "/index";
	}

	@RequestMapping(value ="/list")
	public String getWendu(@RequestParam String did,HttpSession session) {
		// 省略产品列表查询逻辑
		session.setAttribute("did", did);
		return "user/manager";
	}

	@RequestMapping(value ="/ok")
	@RequiredPermission(PermissionConstants.SELECT_LIST)
	public String ok() {
		// 省略产品列表查询逻辑
		return "/index";
	}

	// -------------------------------普通用户--------------------
	/**
	 * 机房管理
	 * 
	 * @return
	 */
	@RequestMapping(value ="/getManager")
	public String getManager() {
		return "user/device";
	}

	/**
	 * 更改密码
	 * 
	 * @return
	 */
	@RequestMapping(value ="/userUpdatePassword")
	public String userUpdatePassword() {
		return "user/updatepassword";
	}
	// ----------------------------管理员--------------

	@RequestMapping(value = "/adminIndex")
	public String getaddUser() {
		return "admin/list";
	}

	/**
	 * 管理员 更改权限
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminUpdatePermission")
	public String adminUpdatePermission(HttpSession session) {
		return "admin/permission";
	}

	/**
	 * 管理员 用户管理
	 */
	@RequestMapping(value = "/adminUserManager")
	public String adminUserManager(HttpSession session) {
		return "admin/userList";
	}

	/**
	 * 管理员 更改密码
	 */
	@RequestMapping(value = "/admiUpdatePassword")
	public String admiUpdatePassword(HttpSession session) {
		return "admin/updatePassw";
	}
	// -----------------------------------socket----------------------------------------------------

	@GetMapping(value ="/web1")
	public String index() {
		return "/web1";
	}

	@GetMapping(value ="/web2")
	public String index2(HttpSession session) {
		session.setAttribute("Did","dj410");
		return "/web2";
	}


}
