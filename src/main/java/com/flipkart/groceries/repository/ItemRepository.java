package com.flipkart.groceries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flipkart.groceries.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("Select item from Item item where item.category=:category and item.brand=:brand")
	Item findByCategoryAndBrand(@Param("category") String category, @Param("brand") String brand);

	@Query("Select item from Item item where item.category=:category")
	List<Item> findByCategory(@Param("category") String category);
}