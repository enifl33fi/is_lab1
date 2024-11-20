package com.enifl33fi.lab1.api.dto.request.entity;

import com.enifl33fi.lab1.api.dto.entity.ProductDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductRequestDto extends ProductDto implements OwnedEntityRequestDto {
  @NotNull private Integer coordinatesId;

  @NotNull private Integer manufacturerId;

  @NotNull private Integer ownerId;

  private Boolean adminPermission;
}
