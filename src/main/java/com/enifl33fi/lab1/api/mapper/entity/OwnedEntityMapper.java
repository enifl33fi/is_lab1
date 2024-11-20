package com.enifl33fi.lab1.api.mapper.entity;

import com.enifl33fi.lab1.api.dto.request.entity.OwnedEntityRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.OwnedEntityResponseDto;
import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import com.enifl33fi.lab1.api.service.AccessService;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OwnedEntityMapper<
    E extends OwnedEntity, REQ extends OwnedEntityRequestDto, RES extends OwnedEntityResponseDto> {
  protected AccessService accessService;

  @Mapping(target = "hasAccess", expression = "java(accessService.checkAccess(entity))")
  public abstract RES mapToResponse(E entity);

  @Mappings({
    @Mapping(target = "user", ignore = true),
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "creationDate", ignore = true),
    @Mapping(target = "adminPermission", defaultValue = "false"),
  })
  public abstract E mapFromRequest(REQ request);

  @Autowired
  private void setAccessService(AccessService accessService) {
    this.accessService = accessService;
  }
}
