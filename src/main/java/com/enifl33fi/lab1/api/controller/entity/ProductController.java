package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.entity.ProductRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.ProductResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.ProductMapper;
import com.enifl33fi.lab1.api.model.product.Product;
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

  @GetMapping("/count_owner_less_than/{id}")
  public ResponseEntity<Integer> countOwnerLessThan(@PathVariable Integer id) {
    return ResponseEntity.ok(productService.countProductsByOwnerLessThan(id));
  }

  @GetMapping("/find_by_part_number")
  public ResponseEntity<List<ProductResponseDto>> findByPartNumber(
      @RequestParam String partNumber) {
    return ResponseEntity.ok(productService.findProductsByPartNumberContaining(partNumber));
  }

  @GetMapping("/ratings")
  public ResponseEntity<List<Integer>> findRatings() {
    return ResponseEntity.ok(productService.getDistinctRatings());
  }

  @PatchMapping("/decrease-price/{id})")
  public ResponseEntity<?> decreasePrice(@PathVariable Integer id, @RequestBody Double percent) {
    productService.decreaseProductsPriceByPercentage(percent, id);
    return ResponseEntity.ok().build();
  }
}
