package com.enifl33fi.lab1.api.dto.response.entity;

import com.enifl33fi.lab1.api.dto.entity.AddressDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressResponseDto extends AddressDto implements OwnedEntityResponseDto {
  private Integer id;

  private LocationResponseDto town;

  private boolean hasAccess;
}
