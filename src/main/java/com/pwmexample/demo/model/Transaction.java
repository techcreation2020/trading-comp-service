package com.pwmexample.demo.model;

import java.util.Date;
import com.wissen.trading.enums.TransactionType;

public class Transaction {

	private Long id;
	private User user;
	private Fund fund;
	private Integer transactionUnit;
	private TransactionType transactionType;
	private Date createOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getTransactionUnit() {
		return transactionUnit;
	}

	public void setTransactionUnit(Integer transactionUnit) {
		this.transactionUnit = transactionUnit;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public Fund getFund() {
		return fund;
	}

	public void setFund(Fund fund) {
		this.fund = fund;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", user=" + user + ", fund=" + fund + ", transactionUnit=" + transactionUnit
				+ ", transactionType=" + transactionType + ", createOn=" + createOn + "]";
	}
	
	
}
