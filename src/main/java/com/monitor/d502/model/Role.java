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
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rid;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
	@JoinTable(name = "RolePermission", joinColumns = { @JoinColumn(name = "rid") }, inverseJoinColumns = {
			@JoinColumn(name = "pid") })
	private List<Permission> permissionList;// 一个用户管理多个设备
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
