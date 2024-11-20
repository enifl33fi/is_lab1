package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.OwnedEntityRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.AccessAware;
import com.enifl33fi.lab1.api.dto.response.entity.OwnedEntityResponseDto;
import com.enifl33fi.lab1.api.exception.NotFoundException;
import com.enifl33fi.lab1.api.mapper.entity.OwnedEntityMapper;
import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import com.enifl33fi.lab1.api.service.ValidatingService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class OwnedEntityService<
    E extends OwnedEntity,
    REQ extends OwnedEntityRequestDto,
    RES extends OwnedEntityResponseDto,
    MAP extends OwnedEntityMapper<E, REQ, RES>,
    REPO extends JpaRepository<E, Integer>> {
  private final MAP mapper;
  private final REPO repo;
  private final ValidatingService validatingService;

  public List<RES> getAllEntities() {
    return repo.findAll().stream().map(mapper::mapToResponse).collect(Collectors.toList());
  }

  public List<RES> getAllEditableEntities() {
    return repo.findAll().stream()
        .map(mapper::mapToResponse)
        .filter((AccessAware::isHasAccess))
        .collect(Collectors.toList());
  }

  public RES getEntityById(Integer id) {
    return mapper.mapToResponse(getRawEntityById(id));
  }

  private E getRawEntityById(Integer id) {
    return repo.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Entity with id %d not found", id)));
  }

  @Transactional
  public void saveEntity(REQ dto) {
    validatingService.validateEntity(dto);
    repo.save(mapper.mapFromRequest(dto));
  }

  @Transactional
  public void updateEntity(REQ dto, Integer id) {
    validatingService.validateEntity(dto);

    E existingEntity = getRawEntityById(id);
    dto.setAdminPermission(existingEntity.isAdminPermission());

    E updatedEntity = mapper.mapFromRequest(dto);
    updatedEntity.setId(existingEntity.getId());
    updatedEntity.setUser(existingEntity.getUser());
    updatedEntity.setCreationDate(existingEntity.getCreationDate());

    repo.save(updatedEntity);
  }

  @Transactional
  public void deleteEntity(Integer id) {
    repo.deleteById(id);
  }
}
