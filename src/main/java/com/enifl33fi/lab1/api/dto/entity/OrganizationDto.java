package com.enifl33fi.lab1.api.dto.entity;

import com.enifl33fi.lab1.api.model.product.OrganizationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
  @NotBlank
  private String name;

  @Positive
  private Integer annualTurnover;

  @NotNull
  @Positive
  private Long employeesCount;

  @NotNull
  @Positive
  private Float rating;

  @NotNull
  private OrganizationType type;
}
