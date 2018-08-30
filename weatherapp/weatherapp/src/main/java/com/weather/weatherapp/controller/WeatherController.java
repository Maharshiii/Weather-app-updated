package com.weather.weatherapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weatherapp.WeatherService;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/heartbeat")
	public HttpStatus message() {
		return HttpStatus.OK;
	}
	
	@GetMapping("/weatherinfo")
	public Map getWeather(@RequestParam(value = "city", required = false) String inCity,  @RequestParam(value = "zip", required = false) Integer inZipCode) {
		if(StringUtils.isEmpty(inCity) &&  StringUtils.isEmpty(inZipCode)) {
			Map map = new HashMap();
			map.put("Error", "City or Zip Code must be provided");
			return map;
		}
		return weatherService.getWeatherReport(inCity, inZipCode);
	}
	
}
