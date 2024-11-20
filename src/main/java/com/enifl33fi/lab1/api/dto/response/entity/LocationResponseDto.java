package com.enifl33fi.lab1.api.dto.response.entity;

import com.enifl33fi.lab1.api.dto.entity.LocationDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocationResponseDto extends LocationDto implements OwnedEntityResponseDto {
  private Integer id;

  private boolean hasAccess;
}
