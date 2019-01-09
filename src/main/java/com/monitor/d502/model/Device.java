package com.monitor.d502.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table

public class Device {
	// 初始化
	public Device() {
		ensureId();

	}

	// 主键id
	@Id
	private String id;
	// 设备id（设备上面自己得id）
	private String did;
	// 设备位置
	private String location;
	// 最新温度
	private Double temperature;
	// 最新湿度
	private Double huminity;
	// 湿度温度最新更新时间
	private Date Update_time;
	// 用户 - 设备关系定义;
	@ManyToMany
	@JoinTable(name = "SysUserDevice", joinColumns = { @JoinColumn(name = "deviceId") }, inverseJoinColumns = {
			@JoinColumn(name = "uid") })
	private List<User> users;// 一个角色对应多个用户
	// 生成String类型的id

	private void ensureId() {
		this.setId(UUID.randomUUID().toString());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHuminity() {
		return huminity;
	}

	public void setHuminity(Double huminity) {
		this.huminity = huminity;
	}

	public Date getUpdate_time() {
		return Update_time;
	}

	public void setUpdate_time(Date update_time) {
	    Update_time = update_time;
	}
}
