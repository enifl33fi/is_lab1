package com.enifl33fi.lab1.api.dto.entity;

import com.enifl33fi.lab1.api.model.product.Color;
import com.enifl33fi.lab1.api.model.product.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
  @NotBlank
  private String name;

  @NotNull
  private Color eyeColor;

  private Color hairColor;

  @Positive
  private Integer weight;;

  @NotNull
  private Country nationality;
}
