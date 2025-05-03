package com.flipkart.groceries.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flipkart.groceries.dto.ItemDto;
import com.flipkart.groceries.entity.Item;
import com.flipkart.groceries.exception.ItemNotFound;
import com.flipkart.groceries.repository.ItemRepository;
import com.flipkart.groceries.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemDto addItem(ItemDto itemDto) {
        Item item = itemRepository.findByCategoryAndBrand(itemDto.getCategory(), itemDto.getBrand());
        if (item == null) {
        	Item newItem = new Item(itemDto.getCategory(), itemDto.getBrand(), itemDto.getPrice());
        	item = itemRepository.save(newItem);
        	BeanUtils.copyProperties(item, itemDto);
        }
        return itemDto;
    }

	@Override
	public ItemDto updateItem(ItemDto itemDto) throws ItemNotFound {
        Item item = itemRepository.findByCategoryAndBrand(itemDto.getCategory(), itemDto.getBrand());
        if (item == null) {
            throw new ItemNotFound("Item with category '" + itemDto.getCategory() + "' and brand '" + itemDto.getBrand() + "' not found.");
        }
        item.setCategory(itemDto.getCategory());
        item.setBrand(itemDto.getBrand());
        item = itemRepository.save(item);
        BeanUtils.copyProperties(item, itemDto);
        return itemDto;
    }

	/*
	 * @Override public void deleteItem(Long itemId) throws ItemNotFound {
	 * Optional<Item> item = itemRepository.findById(itemId); if (!item.isPresent())
	 * { throw new ItemNotFound("Item with id '" + itemId + " not found."); }
	 * itemRepository.delete(item.get()); }
	 */

	@Override
	public List<ItemDto> listItems(String category) throws ItemNotFound {
		List<Item> itemList = itemRepository.findByCategory(category);
		if(itemList.isEmpty()) {
			throw new ItemNotFound("Items With Category "+category+" not found");
		}
		List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
		for(Item item : itemList) {
			itemDtoList.add(new ItemDto(item.getId(), item.getCategory(), item.getBrand(), item.getPrice()));
		}
		return itemDtoList;
	}

	@Override
	public List<ItemDto> listItems() {
		List<Item> itemList = itemRepository.findAll();
		List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
		for(Item item : itemList) {
			itemDtoList.add(new ItemDto(item.getId(), item.getCategory(), item.getBrand(), item.getPrice()));
		}
		return itemDtoList;
	}


}