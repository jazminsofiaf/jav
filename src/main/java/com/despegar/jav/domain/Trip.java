package com.despegar.jav.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Trip implements Response{
	
	private BigDecimal wallet;
	private List<Destination> destinations = new ArrayList<Destination>();
	
	public Trip( String firstCityCode, BigDecimal firstAmountUSD){
		Destination firstCity = new Destination(firstCityCode, null);
		destinations.add(firstCity);
		this.wallet = firstAmountUSD;
	}
	
	public BigDecimal getWalletUSD(){
		return wallet;
	}
	
	public List<Destination> getDestinations(){
		return destinations;
	}

	public void AddDestination(Destination destination){
		this.destinations.add(destination);
		wallet =wallet.subtract(destination.getPriceUSD());
	}
	
	//devuelve true si una ciudad ya fue visitada.
	public boolean isVisited(String cityCode) {
		for (Destination destination : destinations) {
			if (destination.getIataCode() == cityCode)
				return true;
		}
		return false;
	}
	
	//devuelve true si el precio del vuelo es menor al de la billetera
	public boolean canAfford(Destination destination){
		return (destination.getPriceUSD().compareTo(wallet) <= 0);
	}

}
