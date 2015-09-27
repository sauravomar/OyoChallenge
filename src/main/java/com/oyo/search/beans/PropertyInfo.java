package com.oyo.search.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyInfo {
	
	@JsonProperty(value="oyo_id")
	String oyoId;
	@JsonProperty(value="name")
	String name;
	@JsonProperty(value="longitude")
	double longitude;
	@JsonProperty(value="latitude")
	double latitude;
	@JsonProperty(value="dynamic_priority")
	long dynamicPriority;
	@JsonProperty(value="currency_id")
	long currencyId;
	@JsonProperty(value="distance")
	double distance;
	@JsonProperty(value="pricing")
	List<Long>pricing;
	
	
	public String getOyoId() {
		return oyoId;
	}
	public void setOyoId(String oyoId) {
		this.oyoId = oyoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public long getDynamicPriority() {
		return dynamicPriority;
	}
	public void setDynamicPriority(long dynamicPriority) {
		this.dynamicPriority = dynamicPriority;
	}
	public long getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(long currencyId) {
		this.currencyId = currencyId;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public List<Long> getPricing() {
		return pricing;
	}
	public void setPricing(List<Long> pricing) {
		this.pricing = pricing;
	}
	@Override
	public String toString() {
		return "PropertyInfo [oyoId=" + oyoId + ", name=" + name
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", dynamicPriority=" + dynamicPriority + ", currencyId="
				+ currencyId + ", distance=" + distance + ", pricing="
				+ pricing + "]";
	}
	
	
	
}
