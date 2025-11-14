package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.ProductRequestDto;
import com.enifl33fi.lab1.api.dto.request.file.ProductImportRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.PersonResponseDto;
import com.enifl33fi.lab1.api.dto.response.entity.ProductResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.PersonMapper;
import com.enifl33fi.lab1.api.mapper.entity.ProductMapper;
import com.enifl33fi.lab1.api.model.product.*;
import com.enifl33fi.lab1.api.repository.entity.ProductRepository;
import com.enifl33fi.lab1.api.service.EventPublisher;
import com.enifl33fi.lab1.api.service.ValidatingService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class ProductService
    extends OwnedEntityService<
        Product,
        ProductRequestDto,
        ProductImportRequestDto,
        ProductResponseDto,
        ProductMapper,
        ProductRepository> {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final PersonMapper personMapper;

  @Autowired
  public ProductService(
      ValidatingService validatingService,
      ProductRepository productRepository,
      EventPublisher eventPublisher,
      ProductMapper productMapper,
      PersonMapper personMapper) {
    super(productMapper, productRepository, validatingService, eventPublisher, "product");
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.personMapper = personMapper;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public void saveAll(List<ProductImportRequestDto> productDtos) {
    for (ProductImportRequestDto productDto : productDtos) {
      validatingService.validateEntity(productDto);
    }

    validateUniqueNames(productDtos);

    List<Product> products = productDtos.stream().map(productMapper::mapFromImportRequest).toList();

    productRepository.saveAll(products);
    this.eventPublisher.publishEntityChangedEvent(this.entityType);
  }

  private void validateUniqueNames(List<ProductImportRequestDto> productDtos) {
    validateNamesUniqueInRequest(productDtos);
    validateNamesUniqueInDatabase(productDtos);
  }

  private void validateNamesUniqueInRequest(List<ProductImportRequestDto> productDtos) {
    Set<String> uniqueNames = new HashSet<>();
    List<String> duplicateNames =
        productDtos.stream()
            .map(ProductImportRequestDto::getName)
            .filter(name -> !uniqueNames.add(name))
            .toList();

    if (!duplicateNames.isEmpty()) {
      throw new IllegalArgumentException("Обнаружены дубликаты имен в запросе: " + duplicateNames);
    }
  }

  private void validateNamesUniqueInDatabase(List<ProductImportRequestDto> productDtos) {
    List<String> newNames = productDtos.stream().map(ProductImportRequestDto::getName).toList();

    List<String> existingNames = productRepository.findExistingNames(newNames);
    if (!existingNames.isEmpty()) {
      throw new IllegalArgumentException(
          "Продукты с такими именами уже существуют: " + existingNames);
    }
  }

  public Double getAverageRating() {
    return productRepository.findAverageRating();
  }

  public int countProductsByRating(Integer rating) {
    return productRepository.countByRating(rating);
  }

  public List<PersonResponseDto> getDistinctOwners() {
    return productRepository.findDistinctOwners().stream()
        .map(personMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  public List<ProductResponseDto> findProductsByUnitOfMeasure(List<UnitOfMeasure> unitOfMeasures) {
    return productRepository.findByUnitOfMeasureIn(unitOfMeasures).stream()
        .map(productMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Transactional
  public void decreaseAllPricesByPercentage(double percent) {
    productRepository.decreaseAllPricesByPercentage(percent);
    this.eventPublisher.publishEntityChangedEvent(this.entityType);
  }
}
