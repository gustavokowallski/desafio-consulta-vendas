package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;


	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}



	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryMinDTO>> getSummary(@RequestParam(required = false ) String minDate,
														 @RequestParam(required = false) String maxDate,
														 @RequestParam(name = "name", defaultValue = "") String name) {
		List<SummaryMinDTO> list = service.searchSummary(minDate,maxDate,name);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<?> getReport() {
		// TODO
		return null;
	}
}
