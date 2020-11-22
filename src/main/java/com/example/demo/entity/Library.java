package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Library {
	
	@Id
	@GeneratedValue//自动选择策略
    private String name;
    private String direction;
    @Lob
	@Column(columnDefinition="TEXT")//不能直接text
    private String introd;
    //@Temporal(TemporalType.TIMESTAMP) //日期加时间
    @Temporal(TemporalType.DATE)
    private Date createData;
   @JsonSerialize(using=CustomDateSerializer.class)
	public Date getCreateData() {
		return createData;
	}
	public void setCreateData(Date createData) {
		this.createData = createData;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getIntrod() {
		return introd;
	}
	public void setIntrod(String introd) {
		this.introd = introd;
	}
    
}
