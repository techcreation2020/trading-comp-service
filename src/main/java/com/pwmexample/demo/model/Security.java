package com.pwmexample.demo.model;

public class Security {

	private String fundName;

	private int unit;

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public Security(String fundName, int unit) {
		this.fundName = fundName;
		this.unit=unit;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}


	@Override
	public String toString() {
		return String.format("User [fundName=%s, unit=%s]" ,fundName, unit);
	}

}
