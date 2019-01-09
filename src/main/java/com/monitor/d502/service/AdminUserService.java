package com.monitor.d502.service;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.monitor.d502.model.Record;

public interface AdminUserService {
	List<String> getPermissionSet(HttpSession session);
	void addWebSocket(String wsid,HttpSession session);
	Record getWebsocket(String did);
	
}
