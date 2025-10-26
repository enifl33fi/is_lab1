package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.OwnedEntityRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.AccessAware;
import com.enifl33fi.lab1.api.dto.response.entity.Identifiable;
import com.enifl33fi.lab1.api.dto.response.entity.OwnedEntityResponseDto;
import com.enifl33fi.lab1.api.exception.NotFoundException;
import com.enifl33fi.lab1.api.mapper.entity.OwnedEntityMapper;
import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import com.enifl33fi.lab1.api.repository.entity.OwnedEntityRepository;
import com.enifl33fi.lab1.api.service.ValidatingService;
import com.enifl33fi.lab1.api.service.WebSocketService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class OwnedEntityService<
    E extends OwnedEntity,
    REQ extends OwnedEntityRequestDto,
    RES extends OwnedEntityResponseDto,
    MAP extends OwnedEntityMapper<E, REQ, RES>,
    REPO extends OwnedEntityRepository<E>> {
  private final MAP mapper;
  private final REPO repo;
  private final ValidatingService validatingService;
  private final WebSocketService webSocketService;
  private final String entityType;

  protected void notifyClients() {
    webSocketService.notifyEntitiesChanged(entityType);
  }

  public List<RES> getAllEntities() {
    return repo.findAll().stream().map(mapper::mapToResponse).collect(Collectors.toList());
  }

  public List<Integer> getAllEditableEntitiesIds() {
    return repo.findAll().stream()
        .map(mapper::mapToResponse)
        .filter((AccessAware::isHasAccess))
        .map(Identifiable::getId)
        .collect(Collectors.toList());
  }

  public List<RES> getEntitiesByIdPrefix(String prefix, int n) {
    List<E> entities = repo.findFirstNByIdPrefix(prefix, PageRequest.of(0, n));
    return entities.stream().map(mapper::mapToResponse).collect(Collectors.toList());
  }

  public RES getEntityById(Integer id) {
    return mapper.mapToResponse(getRawEntityById(id));
  }

  private E getRawEntityById(Integer id) {
    return repo.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Entity with id %d not found", id)));
  }

  @Transactional
  public RES saveEntity(REQ dto) {
    validatingService.validateEntity(dto);
    E entity = repo.saveAndFlush(mapper.mapFromRequest(dto));
    notifyClients();
    return mapper.mapToResponse(entity);
  }

  @Transactional
  public RES updateEntity(REQ dto, Integer id) {
    validatingService.validateEntity(dto);

    E existingEntity = getRawEntityById(id);
    dto.setAdminPermission(existingEntity.isAdminPermission());

    E updatedEntity = mapper.mapFromRequest(dto);
    updatedEntity.setId(existingEntity.getId());
    updatedEntity.setUser(existingEntity.getUser());
    updatedEntity.setCreationDate(existingEntity.getCreationDate());

    E entity = repo.saveAndFlush(updatedEntity);
    notifyClients();

    return mapper.mapToResponse(entity);
  }

  @Transactional
  public void deleteEntity(Integer id) {
    repo.deleteById(id);
    notifyClients();
  }
}
