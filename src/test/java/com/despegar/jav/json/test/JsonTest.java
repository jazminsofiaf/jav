package com.despegar.jav.json.test;

import static org.junit.Assert.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.despegar.jav.domain.Flight;
import com.despegar.jav.domain.Item;
import com.despegar.jav.domain.PriceDetail;
import com.despegar.jav.json.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;

public class JsonTest {

	private String cheapestFlightResponse = "{ \"items\": [ { \"id\": \"0_ROUNDTRIP_AR_BUE_MIA_site_20160617_1-TOTAL\",\"airline\": \"JJ\", \"stops\": 1, \"departure_date\": \"2016-06-17\", \"return_date\": \"2016-06-18\", \"price_detail\": { \"currency\": \"USD\", \"adult_base\": 600, \"total\": 776 }    } ], \"paging\": { \"offset\": 0, \"limit\": 1, \"total\": 1806 }}";
	private String wrongCheapestFlightResponse = "{ \"items\" [ { \"id\": \"0_ROUNDTRIP_AR_BUE_MIA_site_20160617_1-TOTAL\",\"airline\": \"JJ\", \"stops\": 1, \"departure_date\": \"2016-06-17\", \"return_date\": \"2016-06-18\", \"price_detail\": { \"currency\": \"USD\", \"adult_base\": 600, \"total\": 776 }    } ], \"paging\": { \"offset\": 0, \"limit\": 1, \"total\": 1806 }}";
	public static JsonFactory json = new JsonFactory();

	@Test
	public void testJsonToFlight() {

		Flight flight = json.fromJson(cheapestFlightResponse, new TypeReference<Flight>() {});
		assertEquals(new BigDecimal(776), flight.getAmount());
		assertEquals("JJ",flight.getAirline());

	}
	
	
	@Test(expected = RuntimeException.class)
	public void testJsonNotValidToFlight() {
		Flight flight = json.fromJson(wrongCheapestFlightResponse, new TypeReference<Flight>() {});
		assertEquals(new BigDecimal(776), flight.getAmount());
		assertEquals("JJ",flight.getAirline());

	}
	
	@Test
	public void testFlightToJson(){
		PriceDetail priceDetail= new PriceDetail();
		priceDetail.setTotal(new BigDecimal(300));
		
		Item item = new Item();
		item.setPriceDetail(priceDetail);
		item.setAirline("IBZ");
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		
		Flight flight = new Flight();
		flight.setItems(items);
		
		String jsonFlight =json.toJson(flight);
	}
	
	@Test(expected = RuntimeException.class)
	public void testWrongFlightToJson(){
		Flight flight = new Flight();
		json.toJson(flight);
	}
}
