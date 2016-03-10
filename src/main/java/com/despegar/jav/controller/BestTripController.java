package com.despegar.jav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;

import com.despegar.jav.domain.Trip;
import com.despegar.jav.service.BestTrip;

@Controller
@RequestMapping("/")
public class BestTripController {
	private BestTrip bestTrip;
	BestTripController(BestTrip bestTrip){
		this.bestTrip = bestTrip;
	}
	@RequestMapping(value = "best-trip/", method = {RequestMethod.GET})
	@ResponseBody
	public Trip bestTrip(@RequestParam(value = "cityCode", required = true) String cityCode,
			@RequestParam(value = "money", required = true) BigDecimal amountUSD){
		return this.bestTrip.generate(cityCode, amountUSD);
	} 

}
