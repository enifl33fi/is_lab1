package com.enifl33fi.lab1.api.dto.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
  @NotNull
  @Min(value = -444)
  private Float x;

  @NotNull
  @Min(value = -356)
  private Double y;
}
