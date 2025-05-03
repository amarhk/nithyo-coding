package com.flipkart.groceries.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	public Item item;

	public Inventory(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}
}