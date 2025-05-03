package com.flipkart.groceries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.groceries.dto.ItemDto;
import com.flipkart.groceries.exception.ItemNotFound;
import com.flipkart.groceries.service.ItemService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class ItemsController {

	@Autowired
	private ItemService itemService;

	@PostMapping("/item")
	public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) {
		ItemDto item = itemService.addItem(itemDto);
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	@PutMapping("/item")
	public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto) throws ItemNotFound {
		ItemDto item = itemService.updateItem(itemDto);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@DeleteMapping("/item/{itemId}")
	public ResponseEntity<String> deleteItem(@PathParam("itemId") Long itemId) throws ItemNotFound {
		itemService.deleteItem(itemId);
		return new ResponseEntity<>("Item Removed Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/items")
	public ResponseEntity<List<ItemDto>> items() throws ItemNotFound {
		List<ItemDto> item = itemService.listItems();
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@GetMapping("/items/{category}")
	public ResponseEntity<List<ItemDto>> itemsByCategory(@PathParam("category") String category) throws ItemNotFound {
		List<ItemDto> item = itemService.listItems(category);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
}