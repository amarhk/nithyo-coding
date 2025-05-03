package com.flipkart.groceries.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flipkart.groceries.dto.InventoryDto;
import com.flipkart.groceries.dto.ItemDto;
import com.flipkart.groceries.entity.Inventory;
import com.flipkart.groceries.entity.Item;
import com.flipkart.groceries.exception.InventoryNotFound;
import com.flipkart.groceries.exception.ItemNotFound;
import com.flipkart.groceries.repository.InventoryRepository;
import com.flipkart.groceries.repository.ItemRepository;
import com.flipkart.groceries.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	@Transactional
	public InventoryDto addInventory(InventoryDto inventoryDto) throws ItemNotFound {
		if (inventoryDto.getItem() == null)
			throw new ItemNotFound("Inventory with null Item canot be added");
		Item item = itemRepository.findByCategoryAndBrand(inventoryDto.getItem().getCategory(),
				inventoryDto.getItem().getBrand());
		if (item == null) {
			throw new ItemNotFound("Item with category '" + inventoryDto.getItem().getCategory() + "' and brand '"
					+ inventoryDto.getItem().getBrand() + "' not found, Please add Item");
		}
		Inventory inventory = inventoryRepository.findByItem_CategoryAndItem_Brand(inventoryDto.getItem().getCategory(),
				inventoryDto.getItem().getBrand());
		if (inventory == null) {
			inventory = new Inventory(item, 0);
//			inventory = inventoryRepository.save(inventory);
		}
		inventory.setQuantity(inventory.getQuantity() + inventoryDto.getQuantity());

		inventory = inventoryRepository.save(inventory);
		try {
			
			BeanUtils.copyProperties(inventory, inventoryDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return inventoryDto;
	}

	@Override
	public List<InventoryDto> searchItems(List<String> categories, List<String> brands, BigDecimal priceFrom,
			BigDecimal priceTo, String sortBy) throws ItemNotFound, InventoryNotFound {
		Sort orderBy = Sort.by(Sort.Direction.ASC, "item.price");
		if (sortBy != null) {
			switch (sortBy.toLowerCase()) {
			case "highestprice":
				orderBy = Sort.by(Sort.Direction.DESC, "item.price");
				break;
			case "leastquantity":
				orderBy = Sort.by(Sort.Direction.ASC, "quantity");
				break;
			}
		}
		List<Inventory> inventoryList = inventoryRepository.searchInventory(categories, brands, priceFrom, priceTo,
				orderBy);
		if (inventoryList.isEmpty()) {
			throw new InventoryNotFound("Inventory not found with search Criteria");
		}
		List<InventoryDto> inventoryDtoList = new ArrayList<InventoryDto>();
		for (Inventory inventory : inventoryList) {
			InventoryDto inventoryDto = new InventoryDto();
			inventoryDto.setId(inventory.getId());
			inventoryDto.setQuantity(inventory.getQuantity());
			Item item = inventory.getItem();
			ItemDto itemDto = new ItemDto(item.getId(), item.getCategory(), item.getBrand(), item.getPrice());
			inventoryDto.setItem(itemDto);
			inventoryDtoList.add(inventoryDto);
		}
		return inventoryDtoList;
	}
}