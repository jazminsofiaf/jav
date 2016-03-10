package com.despegar.jav.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.client.fluent.Request;

import com.despegar.jav.exceptions.ReaderException;


public class ServiceReader {
	public String read(String url){
		String inputStreamString;
		try{
			InputStream inputStream =  Request.Get(url).execute().returnContent().asStream();
			inputStreamString = new Scanner(inputStream).useDelimiter("\\A").next();
		}
		catch(IOException e){ 
			throw new ReaderException("Error connecting to server ", e);
		}
		return inputStreamString;
		
	}
}
