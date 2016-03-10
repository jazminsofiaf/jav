package com.despegar.jav.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight  {
	private List<Item> items;

	@JsonIgnore
	public List<Item> getItems() {
		return items;
	}

	@JsonProperty
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public BigDecimal getAmount(){
		return this.items.get(0).getPriceDetail().getTotal();
	}

	public String getAirline() {
		return this.items.get(0).getAirline();
	}


}