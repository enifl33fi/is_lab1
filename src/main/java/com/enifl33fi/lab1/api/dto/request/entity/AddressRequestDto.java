package com.enifl33fi.lab1.api.dto.request.entity;

import com.enifl33fi.lab1.api.dto.entity.AddressDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressRequestDto extends AddressDto implements OwnedEntityRequestDto {
  @NotNull private Integer townId;

  private Boolean adminPermission;
}
