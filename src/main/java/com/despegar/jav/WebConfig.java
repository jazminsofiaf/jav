package com.despegar.jav;

import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.despegar.jav.json.JsonFactory;

@EnableWebMvc
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

	private JsonFactory jsonFactory;

	public WebConfig(JsonFactory jsonFactory) {
		this.jsonFactory = jsonFactory;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(this.jsonFactory.getObjectMapper());
		converters.add(converter);
	}
}
