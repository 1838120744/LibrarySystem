package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="ReturnBookSequence")
public class ReturnBookSequence {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;
	  @Temporal(TemporalType.TIMESTAMP) 
      private Date returntime;
	  private int operator;
      @OneToOne
      private BorrowBookSequence borrowSequence;
	public BorrowBookSequence getBorrowSequence() {
		return borrowSequence;
	}
	public void setBorrowSequence(BorrowBookSequence borrowSequence) {
		this.borrowSequence = borrowSequence;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getReturntime() {
		return returntime;
	}
	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}   
	public Integer getBid(){
		return borrowSequence.getId();
	}
	public Integer getBookid(){
		return borrowSequence.getBookid();
	}
	public Integer getUserid(){
		return borrowSequence.getUserid();
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getCreatetime(){
		return borrowSequence.getCreatetime();
	}
}
