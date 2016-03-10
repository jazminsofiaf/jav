package com.despegar.jav.service.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.despegar.jav.domain.Flight;
import com.despegar.jav.domain.TopRoute;
import com.despegar.jav.domain.Trip;
import com.despegar.jav.service.BestTrip;
import com.despegar.jav.service.CityService;
import com.despegar.jav.service.FlightService;
import com.despegar.jav.service.TopRoutesService;

public class BestTripTest {
	

	private FlightService flightService = mock(FlightService.class);
	private CityService cityService = mock(CityService.class);
	private TopRoutesService topRouteService = mock(TopRoutesService.class);
	private BestTrip bestTrip = new BestTrip(cityService, flightService, topRouteService);

	@Before
	public void setUp() {
		// moqueos para city
		when(cityService.getCountry("BUE")).thenReturn("AR");

		// moqueos para el flight
		Flight vueloCuzco = mock(Flight.class);
		when(vueloCuzco.getAmount()).thenReturn(new BigDecimal(500));
		when(flightService.cheapestFlightReader(eq("AR"), eq("BUE"), eq("CUZ"))).thenReturn(vueloCuzco);

		// mockeos para el toprutes
		TopRoute BsAsCuzco = mock(TopRoute.class);
		when(BsAsCuzco.getFrom()).thenReturn("BUE");
		when(BsAsCuzco.getTo()).thenReturn("CUZ");
		List<TopRoute> topRoutes = new ArrayList<TopRoute>();
		topRoutes.add(BsAsCuzco);
		when(topRouteService.getTopRoutes()).thenReturn(topRoutes);

	}

	@Test
	public void testNotNullTrip() {
		Trip trip = bestTrip.generate("BUE", new BigDecimal(500));
		assertNotNull(trip);
	}

	@Test
	public void testOriginCityIsInVisitedDestinationsList() {
		Trip trip = bestTrip.generate("BUE", new BigDecimal(200));
		assertEquals(1, trip.getDestinations().size());
		assertEquals("BUE", trip.getDestinations().get(0).getIataCode());
	
	}

	@Test
	public void testVisitedCityIsInVisitedDestinationsList() {
		Trip trip = bestTrip.generate("BUE", new BigDecimal(500));
		assertEquals(2, trip.getDestinations().size());
		assertEquals("CUZ", trip.getDestinations().get(1).getIataCode());
	}

	@Test
	public void testWalletIsEmptyAfterSpendingAllMoney() {
		Trip trip = bestTrip.generate("BUE", new BigDecimal(500));
		assertEquals(new BigDecimal(0), trip.getWalletUSD());
	}

	@Test
	public void testWalletIsHalfAfterSpendingHalfMoney() {
		Trip trip = bestTrip.generate("BUE", new BigDecimal(1000));
		assertEquals(new BigDecimal(500), trip.getWalletUSD());
	}

	@Test
	public void testNotEnoughMoneyToTravel() {
		Trip trip = bestTrip.generate("BUE", new BigDecimal(200));
		assertEquals(new BigDecimal(200), trip.getWalletUSD());
		assertEquals(1, trip.getDestinations().size());
		assertEquals("BUE", trip.getDestinations().get(0).getIataCode());
	}

	@Test 
	public void testChoseTheCheapestTrip(){
		// dos opciones de vuelo: a cuzco o a lima
		Flight vueloCuzco = mock(Flight.class);
		when(vueloCuzco.getAmount()).thenReturn(new BigDecimal(500));
		when(flightService.cheapestFlightReader(eq("AR"), eq("BUE"), eq("CUZ"))).thenReturn(vueloCuzco);
		
		Flight vueloLima = mock(Flight.class);
		when(vueloLima.getAmount()).thenReturn(new BigDecimal(200));
		when(flightService.cheapestFlightReader(eq("AR"), eq("BUE"), eq("LIM"))).thenReturn(vueloLima);
		
		//dos opciones de ruta: a cuzco o a lima
		TopRoute BsAsCuzco = mock(TopRoute.class);
		when(BsAsCuzco.getFrom()).thenReturn("BUE");
		when(BsAsCuzco.getTo()).thenReturn("CUZ");
		
		TopRoute BsAsLima = mock(TopRoute.class);
		when(BsAsLima .getFrom()).thenReturn("BUE");
		when(BsAsLima .getTo()).thenReturn("LIM");
		
		
		List<TopRoute> topRoutes = new ArrayList<TopRoute>();
		topRoutes.add(BsAsLima);
		topRoutes.add(BsAsCuzco);
		when(topRouteService.getTopRoutes()).thenReturn(topRoutes);
		
		Trip trip = bestTrip.generate("BUE", new BigDecimal(500));
		
		System.out.println(trip.getDestinations().get(0).getIataCode());
		System.out.println(trip.getDestinations().get(1).getIataCode());
		assertEquals(2, trip.getDestinations().size());
		assertEquals(new BigDecimal(300), trip.getWalletUSD());
		assertEquals("LIM", trip.getDestinations().get(1).getIataCode());
	}

}
