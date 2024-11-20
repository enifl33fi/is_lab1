package com.enifl33fi.lab1.api.dto.entity;

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
  @NotBlank private String name;

  @NotNull @Positive private Float annualTurnover;

  @Positive private long employeesCount;

  @NotNull private String fullName;

  @NotNull @Positive private Float rating;
}
