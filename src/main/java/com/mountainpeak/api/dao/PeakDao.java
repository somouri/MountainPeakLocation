package com.mountainpeak.api.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about a mountain peak")
public class PeakDao {
	@ApiModelProperty(notes = "The id of the mountain peak")
	private Long id;
	@ApiModelProperty(notes = "The name of the mountain")
	private String name;
	@ApiModelProperty(notes = "The lat of the peak")
	private float lat;
	@ApiModelProperty(notes = "The lon of the peak")
	private float lon;
	@ApiModelProperty(notes = "the altitute of the peak")
	private float altitude;

	public PeakDao(Long id, String name, float lat, float lon, float altitude) {
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.altitude = altitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

}
