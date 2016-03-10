package com.despegar.jav.service;


import java.util.HashMap;

import com.despegar.jav.exceptions.InvalidCityCodeException;


public class CityService{
	
	private static final HashMap<String,String> COUNTRY = new HashMap<String,String>();
	static{
			//ARGENTINA
			COUNTRY.put("BUE", "AR");
			COUNTRY.put("BRC", "AR");
			COUNTRY.put( "IGR" ,"AR");
			COUNTRY.put( "COR" ,"AR");
			COUNTRY.put( "MDZ" ,"AR");
			COUNTRY.put( "FTE" ,"AR");
			COUNTRY.put( "USH" ,"AR");
			COUNTRY.put( "EZE" ,"AR");
			
			//BRASIL
			COUNTRY.put("BSB", "BR");
			COUNTRY.put("CWB", "BR");
			COUNTRY.put("SAO", "BR");
			COUNTRY.put("SSA", "BR");
			COUNTRY.put("REC" ,"BR");
			COUNTRY.put("RIO", "BR");
			COUNTRY.put("FOR", "BR");
			COUNTRY.put("POA", "BR");
			COUNTRY.put("MCZ", "BR"); 
			COUNTRY.put("FLN", "BR");
			COUNTRY.put("NAT", "BR");
			COUNTRY.put("GRU", "BR");
			COUNTRY.put("BPS", "BR");
			COUNTRY.put("JPA", "BR");
			COUNTRY.put("BHZ", "BR");
			COUNTRY.put("RIO", "BR");
			
			//CHILE
			COUNTRY.put("SCL", "CL");
			COUNTRY.put("IQQ", "CL");
			
			//MEXICO
			COUNTRY.put("MEX", "MX");
			COUNTRY.put("CUN", "MX");
			
			//PERU
			COUNTRY.put("LIM", "PE");
			COUNTRY.put("CUZ", "PE");
			COUNTRY.put("PIU","PE");
			
			//USA
			COUNTRY.put("MIA", "US");
			COUNTRY.put("ORL", "US");
			COUNTRY.put("NYC", "US");
			
			//ESPAÑA
			COUNTRY.put("MAD", "ES");
			COUNTRY.put("BCN", "ES");
			
			//ITALIA
			COUNTRY.put("ROM", "IT");
			
			//REP-DOMINICANA
			COUNTRY.put("PUJ", "DO");
			
			//COLOMBIA
			COUNTRY.put("BOG", "CO");
			COUNTRY.put("CTG", "CO");
			COUNTRY.put("ADZ", "CO");
			COUNTRY.put("SMR", "CO");
			COUNTRY.put("MDE", "CO");
			
			//PORTUGAL	
			COUNTRY.put("LIS", "PT");
			
			//PANAMA
			COUNTRY.put("PTY", "PA");
	}
	
	public Boolean isValid(String cityCode){
		for(String iataCode : COUNTRY.keySet()){
			if(cityCode == iataCode){
				return true;
			}
		}return false;
	}
	
	public String getCountry(String iatacode){
		iatacode = iatacode.toUpperCase();
		if(!COUNTRY.containsKey(iatacode)){
			throw new InvalidCityCodeException("The city's iatacode is not valid.");
		}
		return COUNTRY.get(iatacode);
	}

}
