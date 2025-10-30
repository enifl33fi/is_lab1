package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.QueryRequestDto;
import com.enifl33fi.lab1.api.dto.request.entity.OwnedEntityRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.OwnedEntityResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.OwnedEntityMapper;
import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import com.enifl33fi.lab1.api.repository.entity.OwnedEntityRepository;
import com.enifl33fi.lab1.api.service.entity.OwnedEntityService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public class OwnedEntityController<
    E extends OwnedEntity,
    REQ extends OwnedEntityRequestDto,
    RES extends OwnedEntityResponseDto,
    MAP extends OwnedEntityMapper<E, REQ, RES>,
    REPO extends OwnedEntityRepository<E>,
    SERV extends OwnedEntityService<E, REQ, RES, MAP, REPO>> {

  private final SERV service;

  @GetMapping("/all")
  @ResponseBody
  public ResponseEntity<Page<RES>> getAllEntities(@Valid QueryRequestDto dto) {
    return ResponseEntity.ok(
        service.getAllEntities(
            dto.getFiltersValues(),
            dto.getSortBy(),
            dto.getSortDirection(),
            dto.getPage(),
            dto.getSize()));
  }

  @GetMapping("/own")
  @ResponseBody
  public ResponseEntity<List<Integer>> getOwnEntities() {
    return ResponseEntity.ok(service.getAllEditableEntitiesIds());
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<RES> getEntity(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getEntityById(id));
  }

  @GetMapping("/prefix/{prefix}")
  @ResponseBody
  public ResponseEntity<List<RES>> getEntitiesByPrefix(
      @PathVariable("prefix") String prefix,
      @RequestParam(name = "count", defaultValue = "10") int count) {
    return ResponseEntity.ok(service.getEntitiesByIdPrefix(prefix, count));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<RES> saveEntity(@RequestBody REQ entity) {
    return ResponseEntity.ok(service.saveEntity(entity));
  }

  @PatchMapping("/{id}")
  @ResponseBody
  public ResponseEntity<RES> updateEntity(@PathVariable("id") Integer id, @RequestBody REQ entity) {
    return ResponseEntity.ok(service.updateEntity(entity, id));
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<?> deleteEntity(@PathVariable("id") Integer id) {
    service.deleteEntity(id);
    return ResponseEntity.ok().build();
  }
}
