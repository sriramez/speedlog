package com.speedlog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.util.ResourceUtils;

public class PublishData {

	List<List<Double>> getLocationsData() throws FileNotFoundException {
		File file = ResourceUtils.getFile("classpath:output (2) (1).csv");
		InputStream inputStream = new FileInputStream(file);
        @SuppressWarnings("resource")
		String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        JSONArray json = CDL.toJSONArray(csvAsString);
        int jsonArraySize = json.length();
        List<List<Double>> locations = new ArrayList<>();
        for(int i=0;i<jsonArraySize;i++)
        {
        	org.json.JSONObject coordinates = json.getJSONObject(i);
        	double latitude = coordinates.getDouble("latitude");
        	double longitude = coordinates.getDouble("longitude");
        	locations.add(List.of(latitude,longitude));
        }
        return locations;
		
		/*return List.of(List.of(19.28749799808018, -81.37306064177642), List.of(19.285610856037238, -81.37306064177642),
				List.of(19.28395145454608, -81.37299169872145), List.of(19.283658617243233, -81.36992373277549),
				List.of(19.28567593026286, -81.36675235224709), List.of(19.286229060136474, -81.36302942727895),
				List.of(19.2860338380415, -81.35847918565125), List.of(19.285827563599923, -81.35491154657787),
				List.of(19.285718243719312, -81.3513661162542), List.of(19.28767190531685, -81.35170342108148),
				List.of(19.28854019187743, -81.35406455487252), List.of(19.288959862064274, -81.35671699737802),
				List.of(19.288844091085696, -81.3585568418905), List.of(19.289474033087632, -81.36528476934981),
				List.of(19.288910570234954, -81.36813698372555), List.of(19.28803406860942, -81.37189571584861),
				List.of(19.28701147742254, -81.37302333541328));*/
		
	}

	public void publishData(List<List<Double>> locations)
			throws ClientProtocolException, IOException, URISyntaxException {
		locations.forEach(location -> {
			try {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpPut httpPut = new HttpPut("http://localhost:8081/vehicle/location?carNumber=213252");
				java.net.URI uri = new URIBuilder(httpPut.getURI())
						.addParameter("latitude", String.valueOf(location.get(0)))
						.addParameter("longitude", String.valueOf(location.get(1))).build();
				httpPut.setURI(uri);

				httpclient.execute(httpPut);
				httpclient.close();
				Thread.sleep(1000);
				System.out.print("publishing data:" + location);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
