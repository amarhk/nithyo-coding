package com.flipkart.groceries.service;

import java.util.List;

import com.flipkart.groceries.dto.ItemDto;
import com.flipkart.groceries.exception.ItemNotFound;

public interface ItemService {
	public ItemDto addItem(ItemDto itemDto);

	public ItemDto updateItem(ItemDto itemDto) throws ItemNotFound;

	//public void deleteItem(Long itemId) throws ItemNotFound;

	public List<ItemDto> listItems(String category) throws ItemNotFound;

	public List<ItemDto> listItems();
}