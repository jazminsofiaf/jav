package com.despegar.jav.domain;

import java.math.BigDecimal;

public class Destination {
		private String iataCode;
		private Flight flight;
		
		public Destination(String iataCode, Flight flight){
			
			this.iataCode = iataCode;
			this.flight = flight;
		}
		
		public Flight getFlight(){
			return flight;
		}
		
		public String getIataCode(){
			return iataCode;
		}
		
		public BigDecimal getPriceUSD(){
			return flight.getAmount();
		}

}

