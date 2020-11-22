package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
	@Column(name="name")
	@NotEmpty(message="名字不能为空！")
	private String name;
	@Column(name="psw")
   private String psw;//密码
	@Column(name="tel")
   private long tel;//电话

   public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int Id) {
	this.id = Id;
}
public String getPsw() {
	return psw;
}
public void setPsw(String psw) {
	this.psw = psw;
}
public long getTel() {
	return tel;
}
public void setTel(long tel) {
	this.tel = tel;
}
   
}
