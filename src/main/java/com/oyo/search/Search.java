package com.oyo.search;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oyo.search.beans.OyoOutput;
import com.oyo.search.beans.PropertyInfo;

public class Search {
	//score, property
	
	
	public void sort(double lon, double lat){
		
		Map<Double,PropertyInfo>map =  new TreeMap <Double,PropertyInfo>();
		
		OyoOutput oyoOutput = getOyoResultObj(lon, lat);
		double score = 0;
		for(PropertyInfo prop : oyoOutput.getHotels() ){
			score = SearchUtil.adjust(SearchUtil.minInList(prop.getPricing()), 4000, 20);
			score =  score + SearchUtil.adjust(prop.getDistance(), 3, 20);
			score =  score + SearchUtil.adjust(getMapsObject("airport", prop.getLongitude(), prop.getLatitude()), 3, 30);
			score =  score + SearchUtil.adjust(getMapsObject("hospital", prop.getLongitude(), prop.getLatitude()), 3, 30);
			map.put(score, prop);
		}
		
		for(Map.Entry<Double,PropertyInfo> entry : map.entrySet()){
			System.out.println(entry.getValue().getOyoId() + " " + entry.getValue().getName() + " " +entry.getKey());
		}
		
	}
	
	public OyoOutput getOyoResultObj(double lon, double lat){
		
		StringBuilder oyoUrl = new StringBuilder();
		
		oyoUrl.append("http://utilities-oyorooms.herokuapp.com/api/v2/search/hotels?filters[coordinates][longitude]=").append(lon);
		oyoUrl.append("&filters[coordinates][latitude]=").append(lat);
		oyoUrl.append("&filters[coordinates][distance]=20&fields=name,longitude,latitude,oyo_id"
				+ "&access_token=MXB2cE44LWJGaTViWExHQ0xCOC06VUtucEhhVV9mclNNeWdrNFBveFY=&"
				+ "additional_fields=room_pricing");
		
		
		String oyoResult = RestCall.call(oyoUrl.toString());
		
        ObjectMapper objectMapper = new ObjectMapper();
        OyoOutput oyoOutput = null;
        
		try {
			oyoOutput = objectMapper.readValue(oyoResult, OyoOutput.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oyoOutput;
	}
	
	public  double getMapsObject(String loc, double lon, double lat){
		
		StringBuilder oyoUrl = new StringBuilder();
		double dist = 3.0;
		oyoUrl.append("https://maps.googleapis.com/maps/api/place/search/json?location=").append(lat).append(",").append(lon);
		oyoUrl.append("&radius=5000&sensor=true&type").append(loc);
		oyoUrl.append("&key=AIzaSyCiUL3FVScMAT9pXvETbzMQqNcuek2C2WQ&rankBy=distance");
		
		String oyoResult = RestCall.call(oyoUrl.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        
		try {
			List<JsonNode> location = objectMapper.readTree(oyoResult).findValues("location");
			
			for(JsonNode node : location ){
				double longt = node.findPath("lng").doubleValue();
				double late = node.findPath("lat").doubleValue();
				double dis = SearchUtil.distance(lat, lon, late, longt, 'K');
				if(dis < dist) dist = dis; 
			}
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dist;
	}
	
	public static void main(String[] args) {
		
		if(args.length == 2){
			new Search().sort(Double.parseDouble(args[1]), Double.parseDouble(args[0]));
		}
		
	}
}
