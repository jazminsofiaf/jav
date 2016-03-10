package com.despegar.jav.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.despegar.jav.domain.Destination;
import com.despegar.jav.domain.Flight;

public class DestinationTest {
		private Destination destination;
		private Flight flight;
	
		@Before
		public void setUp() {
			flight = mock(Flight.class);
			when(flight.getAmount()).thenReturn(new BigDecimal(500));
			destination = new Destination("BUE", flight);
		}
				
				
		@Test
		public void testNotNull(){
			assertNotNull(destination);
		}
		
		@Test
		public void testGetPrice(){
			assertEquals(new BigDecimal(500),destination.getPriceUSD());
		}
		
		@Test
		public void testGetIataCode(){
			assertEquals("BUE", destination.getIataCode());
		}
	

}
