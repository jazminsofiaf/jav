package com.despegar.jav.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.jav.domain.Destination;
import com.despegar.jav.domain.Flight;
import com.despegar.jav.domain.TopRoute;
import com.despegar.jav.domain.Trip;
import com.despegar.jav.exceptions.RoutesNotFoundException;

public class BestTrip {

	// private final static Logger LOGGER =
	// LoggerFactory.getLogger(BestTrip.class);

	// comparador para ordenar destinos por precio de vuelo
	public static final Comparator<Destination> DESTINATION_COMPARATOR = new Comparator<Destination>(){
		public int compare(Destination o1,Destination o2){return o1.getPriceUSD().compareTo(o2.getPriceUSD());}};

	// atributos
	private TopRoutesService topRoutesService;
	private FlightService flightService;
	private CityService cityService;

	// constructor
	public BestTrip(CityService cityservice, FlightService flightservice, TopRoutesService topRoutesService) {
		this.flightService = flightservice;
		this.cityService = cityservice;
		this.topRoutesService = topRoutesService;
	}

	// dado una ruta y un codigo crea el detino
	private Destination createDestination(String originCode, TopRoute route) {
		String countryCode = cityService.getCountry(originCode);
		Flight flight = flightService.cheapestFlightReader(countryCode, route.getFrom(), route.getTo());
		Destination destination = new Destination(route.getTo(), flight);
		return destination;
	}

	// devuelve el destino mas barato (desde la ciudad de origen hasta un
	// destino aun no visitado)
	private Destination cheapestDestinations(String originCode, Trip trip){
		SortedSet<Destination> possibleDestinations = new TreeSet<Destination>(DESTINATION_COMPARATOR);
		List<TopRoute> routes = this.topRoutesService.getTopRoutes();
		for (TopRoute route : routes) {
			if (route.getFrom().equals(originCode) && !trip.isVisited(route.getTo())) {
				possibleDestinations.add(createDestination(originCode, route));
				//LOGGER.debug("adding new destination from {} to {}", route.getFrom(),route.getTo());
			}	
		}
		try{
			return possibleDestinations.first();
		}
		catch(NoSuchElementException e){
			throw new RoutesNotFoundException("Error there's no route available", e);
		}
	}

	// crea el viaje
	public Trip generate(String firstCityCode, BigDecimal firstAmountUSD) {
		Trip trip = new Trip(firstCityCode, firstAmountUSD);
		try {
			Destination cheapestDestination = cheapestDestinations(firstCityCode, trip);
			while (trip.canAfford(cheapestDestination)) {
				trip.AddDestination(cheapestDestination);
				String cityCode = cheapestDestination.getIataCode();
				cheapestDestination = cheapestDestinations(cityCode, trip);
			}
		} catch (RoutesNotFoundException e) {
			// LOGGER.debug("there are no more route available");
		}
		return trip;
	}
}
