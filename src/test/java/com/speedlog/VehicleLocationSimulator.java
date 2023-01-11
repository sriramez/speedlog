package com.speedlog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;



public class VehicleLocationSimulator {


	public static void main(String args[]) throws ClientProtocolException, IOException, URISyntaxException
	{

		
		PublishData data = new PublishData();
		List<List<Double>> locations = data.getLocationsData();
		while(true)
		{
			data.publishData(locations);		
		}
	}

	
	
}
