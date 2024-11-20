package com.enifl33fi.lab1.api.repository;

import com.enifl33fi.lab1.api.model.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  @Query("SELECT COUNT(p) FROM Product p WHERE p.owner.id < :ownerId")
  int countByOwnerLessThan(@Param("ownerId") Integer ownerId);

  @Query("SELECT p FROM Product p WHERE p.partNumber LIKE %:substring%")
  List<Product> findByPartNumberContaining(@Param("substring") String substring);

  @Query("SELECT DISTINCT p.rating FROM Product p")
  List<Integer> findDistinctRatings();

  @Query("SELECT p FROM Product p WHERE p.manufacturer.id = :manufacturerId")
  List<Product> findByManufacturer(@Param("manufacturerId") Integer manufacturerId);

  @Modifying
  @Query(
      "UPDATE Product p SET p.price = p.price * (1 - :percent / 100) WHERE p.manufacturer.id = :manufacturerId")
  void decreasePriceByPercentage(
      @Param("percent") double percent, @Param("manufacturerId") Integer manufacturerId);
}
