package com.flipkart.groceries.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ItemDto {
	private Long id;

	private String category;
	private String brand;

	private BigDecimal price;
}