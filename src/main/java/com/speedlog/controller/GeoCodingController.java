package com.speedlog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.speeding.model.GeoCoding;

@RestController
@RequestMapping("/geoCoding")
public class GeoCodingController {
	
	
	//https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=YOUR_API_KEY
	private static final Logger log = LoggerFactory.getLogger(GeoCodingController.class);
	private static final String GEOCODING_URI = "https://maps.googleapis.com/maps/api/geocode/json";
	

	@GetMapping
	public GeoCoding getGeoCodingForLoc(@RequestParam(value = "address", defaultValue="silicon+valley") String address) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GEOCODING_URI).queryParam("address", address)
				.queryParam("key", "AIzaSyC7rysP9Rbn5Z0FNbi-fG6Q_2xP7x2vfVA");
			
		log.info("Calling geocoding api with: " + builder.toUriString());
		
		GeoCoding geoCoding = restTemplate.getForObject(builder.toUriString(), GeoCoding.class);
		log.info(geoCoding.toString());

		return geoCoding;
	}

	@GetMapping(value="getAddress")
	
	public GeoCoding getGeoCodingAddress(@RequestParam(value = "latLong", defaultValue="19.31813256772372,-81.18150701558531") String latlng) {
		//longitude = "-73.961452";
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GEOCODING_URI).queryParam("latlng", latlng)
				.queryParam("key", "AIzaSyC7rysP9Rbn5Z0FNbi-fG6Q_2xP7x2vfVA");
			
	//	log.info("Calling geocoding api with: " + builder.toUriString());
		System.out.println(builder.toUriString());
	//	GeoCoding geoCoding = restTemplate.getForObject(builder.toUriString(), GeoCoding.class);
		GeoCoding geoCoding = restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?latlng=19.312948606993007, -81.18266572980914&key=AIzaSyC7rysP9Rbn5Z0FNbi-fG6Q_2xP7x2vfVA", GeoCoding.class);
		log.info(geoCoding.toString());

		return geoCoding;
	}	
	
	
    @GetMapping(value="sample", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {

        return "This is Home page";
    }
	

}
