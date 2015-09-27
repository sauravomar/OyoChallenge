package com.oyo.search.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "OyoOutput")
public class OyoOutput {
	
	@JsonProperty(value="count")
	long count;
	@JsonProperty(value="hotels")
	List<PropertyInfo> hotels;
	@JsonProperty(value="distance_multiplier")
	String distanceMultiplier;
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<PropertyInfo> getHotels() {
		return hotels;
	}
	public void setHotels(List<PropertyInfo> hotels) {
		this.hotels = hotels;
	}
	public String getDistanceMultiplier() {
		return distanceMultiplier;
	}
	public void setDistanceMultiplier(String distanceMultiplier) {
		this.distanceMultiplier = distanceMultiplier;
	}
	
	
}
