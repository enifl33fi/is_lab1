package com.enifl33fi.lab1.api.dto.response.entity;

import com.enifl33fi.lab1.api.dto.entity.OrganizationDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrganizationResponseDto extends OrganizationDto implements OwnedEntityResponseDto {
  private Integer id;

  private AddressResponseDto officialAddress;

  private boolean hasAccess;
}
