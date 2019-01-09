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

@Entity
@Table
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	private String url;
	@ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
	@JoinTable(name = "RolePermission", joinColumns = { @JoinColumn(name = "pid") }, inverseJoinColumns = {
			@JoinColumn(name = "rid") })
	private List<Role> roleList;// 一个用户管理多个设备
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
}
