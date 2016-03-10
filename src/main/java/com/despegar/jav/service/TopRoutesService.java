package com.despegar.jav.service;


import java.util.ArrayList;
import java.util.List;


import com.despegar.jav.domain.TopRoute;
import com.despegar.jav.json.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;

public class TopRoutesService extends ServiceReader{
	static String url = "";
	public JsonFactory jsonFactory;
	public TopRoutesService( JsonFactory jsonFactory){
		this.jsonFactory  = jsonFactory;
	}
	
	public List<TopRoute> getTopRoutes(){
		List<TopRoute> topRoutes = new ArrayList<TopRoute>();
		String inputStreamString = read(url);
		topRoutes = jsonFactory.fromJson(inputStreamString, new TypeReference<List<TopRoute>>() {	
		});
		return topRoutes;
	
		
	}
	

}
