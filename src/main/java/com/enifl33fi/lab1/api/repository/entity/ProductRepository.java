package com.enifl33fi.lab1.api.repository.entity;

import com.enifl33fi.lab1.api.model.product.Product;
import com.enifl33fi.lab1.api.model.product.Person;
import com.enifl33fi.lab1.api.model.product.UnitOfMeasure;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends OwnedEntityRepository<Product> {
  @Query("SELECT AVG(p.rating) FROM Product p")
  Double findAverageRating();

  int countByRating(Integer rating);

  @Query("SELECT DISTINCT p.owner FROM Product p WHERE p.owner IS NOT NULL")
  List<Person> findDistinctOwners();

  List<Product> findByUnitOfMeasureIn(List<UnitOfMeasure> unitOfMeasures);

  @Modifying
  @Query("UPDATE Product p SET p.price = p.price * (1 - :percent / 100.0)")
  void decreaseAllPricesByPercentage(@Param("percent") double percent);
}
