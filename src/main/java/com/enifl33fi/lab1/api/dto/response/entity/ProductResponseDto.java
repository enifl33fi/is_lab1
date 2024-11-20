package com.enifl33fi.lab1.api.dto.response.entity;

import com.enifl33fi.lab1.api.dto.entity.ProductDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductResponseDto extends ProductDto implements OwnedEntityResponseDto {
  private Integer id;

  private CoordinatesResponseDto coordinates;

  private OrganizationResponseDto manufacturer;

  private PersonResponseDto owner;

  private boolean hasAccess;
}
