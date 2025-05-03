package com.flipkart.groceries.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.groceries.dto.InventoryDto;
import com.flipkart.groceries.exception.InventoryNotFound;
import com.flipkart.groceries.exception.ItemNotFound;
import com.flipkart.groceries.service.InventoryService;

@RestController
@RequestMapping("/api")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/inventory")
	public ResponseEntity<InventoryDto> addInventory(@RequestBody InventoryDto inventory) throws ItemNotFound {
		try {
			InventoryDto inventoryDto = inventoryService.addInventory(inventory);
			return new ResponseEntity<>(inventoryDto, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/inventory")
	public ResponseEntity<List<InventoryDto>> searchInventory(@RequestParam(required = false) List<String> category,
			@RequestParam(required = false) List<String> brand, @RequestParam(required = false) BigDecimal priceFrom,
			@RequestParam(required = false) BigDecimal priceTo, 
			@RequestParam(required = false, defaultValue = "lowestprice") String sortBy) throws ItemNotFound, InventoryNotFound {
		List<InventoryDto> results = inventoryService.searchItems(category, brand, priceFrom, priceTo, sortBy);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}