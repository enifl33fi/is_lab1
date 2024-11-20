package com.enifl33fi.lab1.api.dto.response.entity;

import com.enifl33fi.lab1.api.dto.entity.CoordinatesDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CoordinatesResponseDto extends CoordinatesDto implements OwnedEntityResponseDto {
  private Integer id;

  private boolean hasAccess;
}
