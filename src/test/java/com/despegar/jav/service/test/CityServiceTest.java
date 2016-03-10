package com.despegar.jav.service.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.despegar.jav.exceptions.InvalidCityCodeException;
import com.despegar.jav.service.CityService;

public class CityServiceTest {
	private CityService cities = new CityService();
	@Test
	public void  testIataCodeIsValid(){
		assertTrue(cities.isValid("BUE"));
		assertTrue(cities.isValid("LIS"));	
	}
	
	@Test
	public void  testIataCodeIsInvalid(){
		assertFalse(cities.isValid("lis"));	
	}
	
	@Test
	public void testCountry(){
		assertEquals("AR",cities.getCountry("BUE"));
		assertEquals("PT", cities.getCountry("LIS"));
		assertEquals("PT", cities.getCountry("lis"));
	}
	
	@Test(expected = InvalidCityCodeException.class)
	public void testInvalidCountry(){
		cities.getCountry("gg");
	}
	

}

