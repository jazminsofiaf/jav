package com.despegar.jav.domain.test;

import org.junit.Before;
import org.junit.Test;

import com.despegar.jav.domain.Flight;
import com.despegar.jav.domain.Item;
import com.despegar.jav.domain.PriceDetail;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlightTest {
	private Item item;
	private List<Item> items = new ArrayList<Item>();
	private Flight flight = new Flight();
	private PriceDetail priceDetail;
	


	@Before
	public void setUp() {
		priceDetail = mock(PriceDetail.class);
		when(priceDetail.getTotal()).thenReturn(new BigDecimal(500));
		item = mock(Item.class);
		when(item.getPriceDetail()).thenReturn(priceDetail);
		items.add(item);
		flight.setItems(items);
	}
			
			
	@Test
	public void testNotNull(){
		assertNotNull(flight);
	}
	
	@Test
	public void testGetPrice(){
		assertEquals(new BigDecimal(500),flight.getAmount());
	}

}
