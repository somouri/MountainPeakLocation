package com.mountainpeak.api.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.mountainpeak.api.dao.PeakDao;
import com.mountainpeak.api.service.PeakService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(value="Mountain peaks controller")
@RestController
public class PeakLocationController {

	private PeakService peakService;

	@Autowired
	public PeakLocationController(PeakService peakService) {
		this.peakService = peakService;
	}

	@GetMapping("/peaks/{id}")
	@ApiOperation(value = "Finds peaks by id", notes = "Provide id to look up to specific mountain peak from DB")
	public ResponseEntity findPeak(@ApiParam( required = true )@PathVariable(value = "id") Long id) {
		if (!peakService.isPeakExist(id)) {
			return new ResponseEntity<>("Le peak n'existe pas !!!", HttpStatus.NOT_FOUND);
		}
		PeakDao peak = peakService.findById(id);
		return ResponseEntity.ok().body(peak);
	}

	@PostMapping(value = "/peak")
	@ApiOperation(value = "Add a new peak")
	public ResponseEntity postPeak(@ApiParam(name = "Peak data", required = true )@RequestBody PeakDao peak) {

		Long id = peakService.savePeak(peak);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@GetMapping("/peaks")
	@ApiOperation(value = "Finds all peaks from the DB")
	public ResponseEntity<List<PeakDao>> findAll() {
		return ResponseEntity.ok(peakService.findAllPeaks());
	}

	@DeleteMapping(value = "/peak/{id}")
	@ApiOperation(value = "Deletes peaks by id", notes = "Provide id to delete a specific mountain peak from DB")
	public ResponseEntity deletePeak(@ApiParam(required = true )@PathVariable("id") Long id) {

		if (!peakService.isPeakExist(id)) {
			return new ResponseEntity<>("Le peak n'existe pas !!!", HttpStatus.NOT_FOUND);
		}
		peakService.deletePeak(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping(value = "/peak/{id}")
	@ApiOperation(value = "Updates a peak", notes = "Provide object with existing mountain peak from DB")
	public ResponseEntity putLocation(@ApiParam(name = "Peak data", required = true )@RequestBody PeakDao peak) {
		peakService.updatepeak(peak);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/peaks/within")
	@ApiOperation(value = "Finds peak within bounding box", notes = "Provide string that respecting format: xmin,ymin,xmax,ymin to look up to specifics peaks in this bouding box")
	
	public ResponseEntity getPeaksWithinBbox(@ApiParam(name = "bbox coordinates", required = true )
											 @RequestBody String coordinateBbox) {
		if (!coordinateBbox.matches("(([-+]?[0-9]*\\.?[0-9]*)+,){3}(([-+]?[0-9]*\\.?[0-9]*)+)$"))
			return new ResponseEntity<>("Le format est incorrecte", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(peakService.findAllPeaksWithinBBox(coordinateBbox), HttpStatus.OK);
	}
}
