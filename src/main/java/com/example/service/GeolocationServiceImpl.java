package com.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.domain.Shop;

@Service
public class GeolocationServiceImpl implements GeolocationService {

	public Shop getLatLong(Shop shop) throws Exception {
		// TODO Auto-generated method stub
		String url = "http://maps.googleapis.com/maps/api/geocode/json?address="+
				URLEncoder.encode(shop.getShopAddress()) + "&sensor=true";
		final URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray jsonArrayResults = jsonObject.getJSONArray("results");
        JSONObject jsonObjectResult = jsonArrayResults.getJSONObject(0);
        JSONObject jsonObjectGeometry = jsonObjectResult.getJSONObject("geometry");
        JSONObject jsonObjectLocation = jsonObjectGeometry.getJSONObject("location");
        String lon = jsonObjectLocation.get("lng")+"";
        String lat = jsonObjectLocation.get("lat")+"";
        shop.setLongitude(lon);
        shop.setLatitude(lat);
		return shop;
	}

}
