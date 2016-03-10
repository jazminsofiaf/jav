package com.despegar.jav.domain;

public class Item {
	private PriceDetail priceDetail;
	private String airline;

	public PriceDetail getPriceDetail() {
		return priceDetail;
	}

	public void setPriceDetail(PriceDetail priceDetail) {
		this.priceDetail = priceDetail;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

}
