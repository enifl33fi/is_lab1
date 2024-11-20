package com.enifl33fi.lab1.api.dto.response.entity;

import com.enifl33fi.lab1.api.dto.entity.PersonDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonResponseDto extends PersonDto implements OwnedEntityResponseDto {
  private Integer id;

  private LocationResponseDto location;

  private boolean hasAccess;
}
