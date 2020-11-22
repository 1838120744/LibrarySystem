package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
@Entity
@Table(name="BorrowBookSequence")
public class BorrowBookSequence {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;
	 @Temporal(TemporalType.TIMESTAMP) 
      private Date createtime;
      private Integer userid;
      private Integer bookid;
      private Integer operator;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
}
