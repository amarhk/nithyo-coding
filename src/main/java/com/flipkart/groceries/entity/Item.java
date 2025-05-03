package com.flipkart.groceries.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Item {
	
	public Item(String category, String brand, BigDecimal price) {
		this.brand = brand;
		this.category = category;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private String brand;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
}