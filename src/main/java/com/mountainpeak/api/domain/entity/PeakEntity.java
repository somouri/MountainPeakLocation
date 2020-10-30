package com.mountainpeak.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;
import com.vividsolutions.jts.geom.Geometry;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "details about a mountain peak")
@Entity(name = "peak")
public class PeakEntity {
	@Id
	@Column(name = "id")
	@NotNull
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "altitude")
	private float altitude;

	@NotNull
	@Column(name = "geometry")
	private Geometry geometry;

	
	public PeakEntity(Long id, String name, float altitude, Geometry geometry) {
		super();
		this.id = id;
		this.name = name;
		this.altitude = altitude;
		this.geometry = geometry;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
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

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	
	
	
}
