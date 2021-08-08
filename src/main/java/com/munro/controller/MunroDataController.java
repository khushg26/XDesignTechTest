package com.munro.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.munro.dto.MunroLibrary;
import com.munro.exception.InvalidHillCategoryException;
import com.munro.exception.InvalidMinMaxHeight;
import com.munro.exception.InvalidSortByField;
import com.munro.representation.MunroLibraryRep;
import com.munro.service.MunroService;
import com.munro.util.HillCategory;

@RestController
public class MunroDataController {

	@Autowired
	MunroService munroService;

	@GetMapping(value = "getHill", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchByHillCategory(
			@RequestParam(name = "hillCategory", defaultValue = "", required = false) String hillCategory,
			@RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
			@RequestParam(name = "ascendingOrder", defaultValue = "true", required = false) Boolean ascendingOrder,
			@RequestParam(name = "count", required = false) Integer count,
			@RequestParam(name = "minHeight", required = false) Float minHeight,
			@RequestParam(name = "maxHeight", required = false) Float maxHeight) {

		// validating hill category
		if (StringUtils.isNotBlank(hillCategory) && HillCategory.getValueOfCategory(hillCategory) == null) {
			throw new InvalidHillCategoryException("Invalid value of hillCategory should be one of MUN or TOP");
		}

		// validating sort by
		if (!StringUtils.equalsIgnoreCase("name", sortBy) && !StringUtils.equalsIgnoreCase("height", sortBy)) {
			throw new InvalidSortByField("Invalid value of sortBy should be one of name or height");

		}

		// minHeight and maxHeight validations
		if (minHeight != null && minHeight < 0) {
			throw new InvalidMinMaxHeight("Invalid value of minHeight. It should not be less than 0");
		}
		if (maxHeight != null && maxHeight <= 0) {
			throw new InvalidMinMaxHeight("Invalid value of maxHeight. It should not be less than equal to 0");
		}
		if (maxHeight != null && minHeight != null && maxHeight < minHeight) {
			throw new InvalidMinMaxHeight("MinHeight should be less than or equal to maxHeight.");
		}

		// count validations
		if (count != null && count <= 0) {
			throw new RuntimeException("Value of count should be greater than 0");
		}

		List<MunroLibrary> munroList = munroService.searchByHillCategory(hillCategory, sortBy, ascendingOrder,
				minHeight, maxHeight);

		// converting to reps
		List<MunroLibraryRep> munroReps = MunroLibraryRep.getReps(munroList, count);
		return new ResponseEntity<List<MunroLibraryRep>>(munroReps, HttpStatus.OK);
	}

	/**
	 * Exception handler
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Object> exception(RuntimeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
