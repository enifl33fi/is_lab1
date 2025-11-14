package com.enifl33fi.lab1.api.dto.request.file;

import com.enifl33fi.lab1.api.dto.entity.OrganizationDto;
import com.enifl33fi.lab1.api.dto.request.entity.OwnedEntityRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrganizationImportRequestDto extends OrganizationDto implements OwnedEntityRequestDto {
  @NotNull private AddressImportRequestDto officialAddress;

  private Boolean adminPermission;
}
