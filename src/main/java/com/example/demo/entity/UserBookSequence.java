package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="UserBookSequence")
public class UserBookSequence {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Integer id;
       private Integer userid;
       private Integer bookid;
       @Temporal(TemporalType.TIMESTAMP) 
       private Date createtime;
       private String staus;
       @Temporal(TemporalType.TIMESTAMP) 
       private Date deadline;
       @Temporal(TemporalType.TIMESTAMP) 
       private Date returntime;
       private Integer borrowSequenceId;
   	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getReturntime() {
		return returntime;
	}
	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}
	public Integer getBorrowSequenceId() {
		return borrowSequenceId;
	}
	public void setBorrowSequenceId(Integer borrowSequenceId) {
		this.borrowSequenceId = borrowSequenceId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getStaus() {
		return staus;
	}
	public void setStaus(String staus) {
		this.staus = staus;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}
