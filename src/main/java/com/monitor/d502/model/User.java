package com.monitor.d502.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.monitor.d502.uitl.MD5Encode;

@Entity
@Table
public class User {
	public static String CURRENT_USER = "currentUser"; // 当前用户
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	private String userName;
	private String password;
	private String name;
	private String emil;
	private String websocketSession;
	@ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
	@JoinTable(name = "SysUserDevice", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
			@JoinColumn(name = "deviceId") })
	private List<Device> deviceList;// 一个用户管理多个设备
	private Long rid;// 用户表外键

	public Long getId() {
		return uid;
	}

	public void setId(Long id) {
		this.uid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmil() {
		return emil;
	}

	public void setEmil(String emil) {
		this.emil = emil;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public String getWebsocketSession() {
		return websocketSession;
	}

	public void setWebsocketSession(String websocketSession) {
		this.websocketSession = websocketSession;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}
	public boolean isPasswordCorrect(String password) {
		return this.password.equals(password);
	}
	
	public static String encryptPassword(String password) {
		return MD5Encode.encode(password) + MD5Encode.encode(password);
	}


	
}
