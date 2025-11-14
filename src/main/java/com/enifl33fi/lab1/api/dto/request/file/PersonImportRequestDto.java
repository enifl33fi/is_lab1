package com.enifl33fi.lab1.api.dto.request.file;

import com.enifl33fi.lab1.api.dto.entity.PersonDto;
import com.enifl33fi.lab1.api.dto.request.entity.OwnedEntityRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonImportRequestDto extends PersonDto implements OwnedEntityRequestDto {
  private LocationImportRequestDto location;

  private Boolean adminPermission;
}
