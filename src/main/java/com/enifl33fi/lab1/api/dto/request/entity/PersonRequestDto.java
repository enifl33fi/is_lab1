package com.enifl33fi.lab1.api.dto.request.entity;

import com.enifl33fi.lab1.api.dto.entity.PersonDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonRequestDto extends PersonDto implements OwnedEntityRequestDto {
  @NotNull private Integer locationId;

  private Boolean adminPermission;
}
