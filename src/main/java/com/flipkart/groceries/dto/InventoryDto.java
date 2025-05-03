package com.flipkart.groceries.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InventoryDto {

    private Long id;
    private Integer quantity;
    private ItemDto item;
}