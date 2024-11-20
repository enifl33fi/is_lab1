package com.enifl33fi.lab1.api.dto.request.entity;

import com.enifl33fi.lab1.api.dto.entity.LocationDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocationRequestDto extends LocationDto implements OwnedEntityRequestDto {
  private Boolean adminPermission;
}
