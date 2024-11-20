package com.enifl33fi.lab1.api.dto.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
  private long x;
  private long y;
  @NotNull private Integer z;

  @Size(max = 779)
  private String name;
}
