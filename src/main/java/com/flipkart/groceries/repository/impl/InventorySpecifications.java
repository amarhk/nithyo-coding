package com.flipkart.groceries.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.flipkart.groceries.entity.Inventory;

public class InventorySpecifications {

	public static Specification<Inventory> hasCategoryIn(List<String> categories) {
		return (root, query, criteriaBuilder) -> categories == null || categories.isEmpty()
				? criteriaBuilder.conjunction()
				: root.get("item").get("category").in(categories);
	}

	public static Specification<Inventory> hasBrandIn(List<String> brands) {
		return (root, query, criteriaBuilder) -> brands == null || brands.isEmpty() ? criteriaBuilder.conjunction()
				: root.get("item").get("brand").in(brands);
	}

	public static Specification<Inventory> priceGreaterThanOrEqualTo(BigDecimal priceFrom) {
		return (root, query, criteriaBuilder) -> priceFrom == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.greaterThanOrEqualTo(root.get("item").get("price"), priceFrom);
	}

	public static Specification<Inventory> priceLessThanOrEqualTo(BigDecimal priceTo) {
		return (root, query, criteriaBuilder) -> priceTo == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.lessThanOrEqualTo(root.get("item").get("price"), priceTo);
	}
}