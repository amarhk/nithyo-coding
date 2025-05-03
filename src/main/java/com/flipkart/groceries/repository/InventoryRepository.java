package com.flipkart.groceries.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flipkart.groceries.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	@Query("SELECT i FROM Inventory i " + "WHERE 1=1 " + 
			"AND (:categories IS NULL OR i.item.category IN :categories) "
			+ "AND (:brands IS NULL OR i.item.brand IN :brands) "
			+ "AND (:priceFrom IS NULL OR i.item.price >= :priceFrom) "
			+ "AND (:priceTo IS NULL OR i.item.price <= :priceTo)")
	List<Inventory> searchInventory(@Param("categories") List<String> categories, @Param("brands") List<String> brands,
			@Param("priceFrom") BigDecimal priceFrom, @Param("priceTo") BigDecimal priceTo, @Param("sort") Sort sort);

	@Query("SELECT i FROM Inventory i where i.item.category= :category and i.item.brand =:brand")
	Inventory findByItem_CategoryAndItem_Brand(@Param("category") String category, @Param("brand") String brand);
}