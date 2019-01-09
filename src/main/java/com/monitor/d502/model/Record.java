package com.monitor.d502.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Record {
	// 初始化
	public Record() {
		ensureId();

	}

	// id
	@Id
	private String id;
	// 设备id
	private String did;
	private Double temperature;
	private Double huminity;
	private Date report_time;

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

	public Date getReport_time() {
		return report_time;
	}

	public void setReport_time(Date report_time) {
		this.report_time = report_time;
	}

}
