package com.pwmexample.demo.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionList {

private List<Transaction> transaction;
	
	public TransactionList() {
		transaction = new ArrayList<>();
    }

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
}
