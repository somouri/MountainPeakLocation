package com.mountainpeak.api.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainpeak.api.dao.PeakDao;
import com.mountainpeak.api.domain.entity.PeakEntity;
import com.mountainpeak.api.repository.PeakRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateFilter;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.CoordinateSequenceComparator;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
import com.vividsolutions.jts.geom.CoordinateSequences;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryComponentFilter;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.GeometryFilter;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class PeakService {

	@Autowired
	private PeakRepository peakRepository;

	public PeakService() {

	}

	public Long savePeak(PeakDao peak) {
		Point p = new GeometryFactory().createPoint(new Coordinate(peak.getLat(), peak.getLon()));
		PeakEntity peakEntity = new PeakEntity(peak.getId(), peak.getName(), peak.getAltitude(), p);
		peakRepository.save(peakEntity);
		return peak.getId();
	}

	public PeakDao findById(Long id) {
		PeakEntity peakEntity = peakRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Montagne peak not found" + id);
		});
		return this.convertEntityToDao(peakEntity);
	}

	public List<PeakDao> findAllPeaks() {
		List<PeakEntity> peakEntityList = peakRepository.findAll();
		List<PeakDao> peadDaoList = mapEntityListToPeakDaoList(peakEntityList);
		return peadDaoList;
	}

	public Long updatepeak(PeakDao peak) {
		Point p = new GeometryFactory().createPoint(new Coordinate(peak.getLat(), peak.getLon()));
		PeakEntity peakEntity = new PeakEntity(peak.getId(), peak.getName(), peak.getAltitude(), p);
		peakRepository.save(peakEntity);
		return peak.getId();
	}

	private PeakDao convertEntityToDao(PeakEntity entity) {
		Coordinate coordinate = ((Point) entity.getGeometry()).getCoordinate();
		float lat = (float) coordinate.getOrdinate(0);
		float lon = (float) coordinate.getOrdinate(1);
		return new PeakDao(entity.getId(), entity.getName(), lat, lon, entity.getAltitude());
	}

	public void deletePeak(Long id) {
		peakRepository.deleteById(id);
	}

	private List<PeakDao> mapEntityListToPeakDaoList(List<PeakEntity> peakEntityList) {
		PeakDao peakDao;
		List<PeakDao> peadDaoList = new ArrayList<PeakDao>();
		
		for (PeakEntity peakEntity : peakEntityList) {
			peakDao = convertEntityToDao(peakEntity);
			peadDaoList.add(peakDao);
		}
		return peadDaoList;
	}

	public Collection<PeakDao> findAllPeaksWithinBBox(String coordinatesBbox) {
		
		String[] coordinates = coordinatesBbox.split(",");
		
		Polygon bbox = new GeometryFactory().createPolygon(
				new Coordinate[] { new Coordinate(Float.valueOf(coordinates[0]), Float.valueOf(coordinates[1])),
						new Coordinate(Float.valueOf(coordinates[0]), Float.valueOf(coordinates[3])),
						new Coordinate(Float.valueOf(coordinates[2]), Float.valueOf(coordinates[3])),
						new Coordinate(Float.valueOf(coordinates[2]), Float.valueOf(coordinates[1])),
						new Coordinate(Float.valueOf(coordinates[0]), Float.valueOf(coordinates[1]))});
		
		List<PeakEntity> peakEntityList = peakRepository.findWithin(bbox);
		List<PeakDao> peadDaoList = mapEntityListToPeakDaoList(peakEntityList);
		return peadDaoList;
	}
	
	public boolean isPeakExist(Long id) {
        return peakRepository.existsById(id);
    }

}
