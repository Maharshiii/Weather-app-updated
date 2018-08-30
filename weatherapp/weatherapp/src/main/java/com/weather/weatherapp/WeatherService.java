package com.weather.weatherapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

	@Value("${weather.url}")
	private String url;

	@Value("${weather.key}")
	private String appKey;

	@Value("${weather.country}")
	private String country;

	private RestTemplate restTemplate = null;
	
	public Map getWeatherReport(String inCity, Integer inZipCode) {
		if (StringUtils.isEmpty(inCity)) {
			return getWeahtherInfo(inZipCode);
		} else {
			return getWeahtherInfo(inCity);
		}
	}

	private Map getWeahtherInfo(String inCity) {
		restTemplate = new RestTemplate();
		Map response = null;
		try {
		response = restTemplate.getForObject(url + getUrlParams(inCity), Map.class);
		}catch(Exception e) {
			
		}
		if(null == response) {
			return noDataFound();
		}
		return response;
	}

	private Map getWeahtherInfo(Integer inZipCode) {
		restTemplate = new RestTemplate();
		Map response = null;
		try {
			response = restTemplate.getForObject(url + getUrlParams(inZipCode), Map.class);	
		}catch(Exception e) {
			
		}
		
		if(null == response) {
			return noDataFound();
		}
		return response;
	}
	
	private String getUrlParams(String inCity) {
		StringBuilder urlParams = new StringBuilder("q=");
		urlParams.append(inCity).append(",").append(country);
		urlParams.append("&APPID=").append(appKey);
		return urlParams.toString();
	}
	
	private String getUrlParams(Integer inZipCode) {
		StringBuilder urlParams = new StringBuilder("zip=");
		urlParams.append(inZipCode).append(",").append(country);
		urlParams.append("&APPID=").append(appKey);
		return urlParams.toString();
	}
	
	private Map noDataFound() {
		Map map = new HashMap<>();
		map.put("Info", "No Data Found");
		return map;
	}
}
