package com.enifl33fi.lab1.api.dto.response.entity;

import com.enifl33fi.lab1.api.dto.entity.ProductDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductResponseDto extends ProductDto implements OwnedEntityResponseDto {
  private Integer id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
  private Date creationDate;

  private CoordinatesResponseDto coordinates;

  private OrganizationResponseDto manufacturer;

  private PersonResponseDto owner;

  private boolean hasAccess;
}
