package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.entity.ProductRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.PersonResponseDto;
import com.enifl33fi.lab1.api.dto.response.entity.ProductResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.ProductMapper;
import com.enifl33fi.lab1.api.model.product.Product;
import com.enifl33fi.lab1.api.model.product.UnitOfMeasure;
import com.enifl33fi.lab1.api.repository.entity.ProductRepository;
import com.enifl33fi.lab1.api.service.entity.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController
    extends OwnedEntityController<
        Product,
        ProductRequestDto,
        ProductResponseDto,
        ProductMapper,
        ProductRepository,
        ProductService> {
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    super(productService);
    this.productService = productService;
  }

  @GetMapping("/average-rating")
  public ResponseEntity<Double> getAverageRating() {
    Double averageRating = productService.getAverageRating();
    return ResponseEntity.ok(averageRating);
  }

  @GetMapping("/count-by-rating/{rating}")
  public ResponseEntity<Integer> countByRating(@PathVariable Integer rating) {
    int count = productService.countProductsByRating(rating);
    return ResponseEntity.ok(count);
  }

  @GetMapping("/distinct-owners")
  public ResponseEntity<List<PersonResponseDto>> getDistinctOwners() {
    List<PersonResponseDto> owners = productService.getDistinctOwners();
    return ResponseEntity.ok(owners);
  }

  @GetMapping("/by-unit-of-measure")
  public ResponseEntity<List<ProductResponseDto>> getProductsByUnitOfMeasure(
          @RequestParam List<UnitOfMeasure> unitOfMeasures) {
    List<ProductResponseDto> products = productService.findProductsByUnitOfMeasure(unitOfMeasures);
    return ResponseEntity.ok(products);
  }

  @PatchMapping("/decrease-all-prices")
  public ResponseEntity<?> decreaseAllPrices(@RequestBody Double percent) {
    productService.decreaseAllPricesByPercentage(percent);
    return ResponseEntity.ok().build();
  }
}
