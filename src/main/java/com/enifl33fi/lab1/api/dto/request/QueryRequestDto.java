package com.enifl33fi.lab1.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryRequestDto {
  private Map<String, String> filtersValues = new HashMap<>();
  @NotBlank private String sortBy = "id";

  @Min(-1)
  @Max(1)
  private Integer sortDirection = 1;

  @Min(0)
  private Integer page = 0;

  @Min(1)
  private Integer size = 10;
}
