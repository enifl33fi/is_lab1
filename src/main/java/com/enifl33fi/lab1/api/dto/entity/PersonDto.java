package com.enifl33fi.lab1.api.dto.entity;

import com.enifl33fi.lab1.api.model.product.Color;
import com.enifl33fi.lab1.api.model.product.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
  @NotBlank private String name;

  @NotNull private Color eyeColor;

  @NotNull private Color hairColor;

  @NotNull private java.time.ZonedDateTime birthday;

  @NotNull private Country nationality;
}
