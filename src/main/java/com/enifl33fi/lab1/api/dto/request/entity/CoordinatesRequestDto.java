package com.enifl33fi.lab1.api.dto.request.entity;

import com.enifl33fi.lab1.api.dto.entity.CoordinatesDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CoordinatesRequestDto extends CoordinatesDto implements OwnedEntityRequestDto {
  private Boolean adminPermission;
}
