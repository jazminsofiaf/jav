package com.despegar.jav.domain.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.despegar.jav.domain.Destination;
import com.despegar.jav.domain.Trip;

public class TripTest {
	private Trip trip;
	private Destination destination;
	
	@Before
	public void setUp() {
		trip = new Trip("BUE", new BigDecimal(1000));
		destination  = mock(Destination.class);
		when(destination.getPriceUSD()).thenReturn(new BigDecimal(900));
		when(destination.getIataCode()).thenReturn("CUN");
	}
			
			
	@Test
	public void testNotNull(){
		assertNotNull(trip);
	}
	
	@Test
	public void testGetInicialMoney(){
		assertEquals(new BigDecimal(1000),trip.getWalletUSD());
	}
	
	@Test
	public void testCanAffordSmallerAmountOfMoneyThanWalletAmount(){
		
		assertTrue(trip.canAfford(destination));
	}
	
	
	@Test
	public void testFirstCityIsVisited(){
		assertTrue(trip.isVisited("BUE"));
	}

	@Test
	public void testAddingNewDestinationToVisitedList(){
		trip.AddDestination(destination);
		assertEquals(2, trip.getDestinations().size());	
		assertTrue(trip.isVisited("BUE"));
		assertTrue(trip.isVisited("CUN"));
		
	}
	
	@Test
	public void testAddingNewDestinationReduceMoney(){
		trip.AddDestination(destination);
		assertEquals(new BigDecimal(100), trip.getWalletUSD());	
		
	}


}
