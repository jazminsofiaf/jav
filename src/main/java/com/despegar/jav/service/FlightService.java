package com.despegar.jav.service;



import com.despegar.jav.domain.Flight;
import com.despegar.jav.json.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;

public class FlightService extends ServiceReader{
	static String urlBase = "http://backoffice.despegar.com/v3/flights/search-stats/cheapest-itineraries?channel=site&cheapest_criteria=total&search_type=roundtrip&offset=0&limit=1&currency=USD";
	private JsonFactory jsonFactory;
	public FlightService(JsonFactory jsonFactory){
		this.jsonFactory  = jsonFactory;
		
	}
 
	//lee desde un servidor el vuelo mas barato desde una ciudad a otra y lo devuelve en forma de json.
	public  Flight cheapestFlightReader(String countryCode, String originCode, String destinationCode){
		String url = urlBase + "&country=" + countryCode + "&from="+ originCode +"&to="+ destinationCode;
		String inputStreamString = read(url);
		return jsonFactory.fromJson(inputStreamString, new TypeReference<Flight>() {
		});
	}
}
	
