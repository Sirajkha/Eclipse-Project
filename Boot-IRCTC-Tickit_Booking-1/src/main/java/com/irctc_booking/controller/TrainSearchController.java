package com.irctc_booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irctc_booking.response.TrainSearchResultResponse;
import com.irctc_booking.service.TrainSearchService;

@RestController
@RequestMapping("/trains/")
public class TrainSearchController {

	final TrainSearchService searchService;

	public TrainSearchController(TrainSearchService searchService) {
		this.searchService = searchService;
	}
	
	@GetMapping("search")
	public ResponseEntity<List<TrainSearchResultResponse>> searchTrains(@RequestParam String sourceCode, @RequestParam String destinationCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate journeyDate) {
		List<TrainSearchResultResponse> resultResponses = searchService.searchTrains(sourceCode, destinationCode, journeyDate);
		return ResponseEntity.ok(resultResponses);
	}
}
