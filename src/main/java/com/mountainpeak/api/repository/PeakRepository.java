package com.mountainpeak.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mountainpeak.api.domain.entity.PeakEntity;
import com.vividsolutions.jts.geom.Geometry; 

@Repository
public interface PeakRepository extends JpaRepository<PeakEntity, Long>  {
	@Query("SELECT p FROM peak AS p WHERE within(p.geometry, :filter) = TRUE")
    List<PeakEntity> findWithin( @Param("filter") Geometry filter);
}