package com.flipkart.groceries.service;

import java.math.BigDecimal;
import java.util.List;

import com.flipkart.groceries.dto.InventoryDto;
import com.flipkart.groceries.exception.InventoryNotFound;
import com.flipkart.groceries.exception.ItemNotFound;

public interface InventoryService {

	public InventoryDto addInventory(InventoryDto inventoryDto) throws ItemNotFound;

	public List<InventoryDto> searchItems(List<String> categories, List<String> brands, BigDecimal priceFrom,
			BigDecimal priceTo, String sortBy) throws ItemNotFound, InventoryNotFound ;
}