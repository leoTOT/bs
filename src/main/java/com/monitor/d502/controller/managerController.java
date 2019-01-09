package com.monitor.d502.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.d502.model.Device;
import com.monitor.d502.model.User;
import com.monitor.d502.model.repository.DeviceRepository;

@RestController
public class managerController {
	@Autowired
	DeviceRepository devicerepository;
	                
	@RequestMapping(value="/getManagerlist",method = RequestMethod.POST)
	public List<Map<String,Object>> getManager(HttpSession session,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("id");
		List<String> list = devicerepository.findByUid(user.getUid());
		List<Map<String,Object>> ddlist = new ArrayList<Map<String,Object>>();
		 for(String str: list ){
             Device dlist = devicerepository.findbyId(str);
             Map<String,Object> map = new HashMap<String,Object>();
             map.put("id",dlist.getId());
             map.put("location",dlist.getLocation());
             map.put("tem",dlist.getTemperature());
             map.put("hum",dlist.getHuminity());
             ddlist.add(map);          
         }			 
		 	return ddlist;	
	}
	}
	
	
		   

