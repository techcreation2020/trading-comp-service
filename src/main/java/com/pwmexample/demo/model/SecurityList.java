package com.pwmexample.demo.model;

import java.util.ArrayList;
import java.util.List;

public class SecurityList {

	private List<Security> securities;
	
	public SecurityList() {
		securities = new ArrayList<>();
    }

	public List<Security> getSecurities() {
		return securities;
	}

	public void setSecurities(List<Security> securities) {
		this.securities = securities;
	}
}
