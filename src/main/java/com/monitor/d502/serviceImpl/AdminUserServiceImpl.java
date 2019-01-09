package com.monitor.d502.serviceImpl;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.d502.model.Record;
import com.monitor.d502.model.User;
import com.monitor.d502.model.repository.RecordRepository;
import com.monitor.d502.model.repository.UserRepository;
import com.monitor.d502.service.AdminUserService;
@Service
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RecordRepository recordRepository;
	@Autowired
	HttpSession session;

	@Override
	public List<String> getPermissionSet(HttpSession session) {
		User user = (User)session.getAttribute(User.CURRENT_USER);
		if (user==null) {
			return null;
		}
		List<String> findUrl = userRepository.findUrl(user.getUid());
		return findUrl;
	}

	@Override
	public void addWebSocket(String wsid,HttpSession session) {
		Object object = session.getAttribute("Did");
		System.out.println("Did"+object.toString());
	}

	@Override
	public Record getWebsocket(String did) {		
			 Record record = recordRepository.finddid(did);
			return record;
		
		
	}

}
