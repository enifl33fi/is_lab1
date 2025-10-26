package com.enifl33fi.lab1.api.dto.entity;

import com.enifl33fi.lab1.api.model.product.UnitOfMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  @NotBlank
  private String name;

  @NotNull
  private UnitOfMeasure unitOfMeasure;

  @NotNull
  @Positive
  private Float price;

  @NotNull
  private Integer manufactureCost;

  @NotNull
  @Positive
  private Integer rating;
}
