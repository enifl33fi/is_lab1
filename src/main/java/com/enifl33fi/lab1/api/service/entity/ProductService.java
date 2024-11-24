package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.ProductRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.ProductResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.ProductMapper;
import com.enifl33fi.lab1.api.model.product.*;
import com.enifl33fi.lab1.api.repository.entity.ProductRepository;
import com.enifl33fi.lab1.api.service.ValidatingService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class ProductService
    extends OwnedEntityService<
        Product, ProductRequestDto, ProductResponseDto, ProductMapper, ProductRepository> {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Autowired
  public ProductService(
      ValidatingService validatingService,
      ProductRepository productRepository,
      ProductMapper productMapper) {
    super(productMapper, productRepository, validatingService);
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  public int countProductsByOwnerLessThan(Integer ownerId) {
    return productRepository.countByOwnerLessThan(ownerId);
  }

  public List<ProductResponseDto> findProductsByPartNumberContaining(String substring) {
    return productRepository.findByPartNumberContaining(substring).stream()
        .map(productMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  public List<Integer> getDistinctRatings() {
    return productRepository.findDistinctRatings();
  }

  public List<ProductResponseDto> findProductsByManufacturer(Integer manufacturerId) {
    return productRepository.findByManufacturer(manufacturerId).stream()
        .map(productMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Transactional
  public void decreaseProductsPriceByPercentage(double percent, Integer manufacturerId) {
    productRepository.decreasePriceByPercentage(percent, manufacturerId);
  }
}
