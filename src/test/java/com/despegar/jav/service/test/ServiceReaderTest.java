package com.despegar.jav.service.test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.despegar.jav.service.ServiceReader;


public class ServiceReaderTest {
	private ServiceReader serviceReader = new ServiceReader(); 
	@Test
	public void  testReadingUrl(){
        String inputStreamString = serviceReader.read("http://www.mocky.io/v2/56e03b2a100000fb0a40f8d3");
        assertEquals(inputStreamString, "Hello world");
	}
	@Test(expected = RuntimeException.class)
	public void testReadingWrong(){ 
		serviceReader.read("hola/");
	}
	

}
