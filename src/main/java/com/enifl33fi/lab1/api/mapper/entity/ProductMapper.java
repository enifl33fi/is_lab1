package com.enifl33fi.lab1.api.mapper.entity;

import com.enifl33fi.lab1.api.dto.request.entity.ProductRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.ProductResponseDto;
import com.enifl33fi.lab1.api.exception.NotFoundException;
import com.enifl33fi.lab1.api.model.product.*;
import com.enifl33fi.lab1.api.repository.CoordinatesRepository;
import com.enifl33fi.lab1.api.repository.OrganizationRepository;
import com.enifl33fi.lab1.api.repository.PersonRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = "spring",
    uses = {CoordinatesMapper.class, OrganizationMapper.class, PersonMapper.class})
public abstract class ProductMapper
    extends OwnedEntityMapper<Product, ProductRequestDto, ProductResponseDto> {
  private CoordinatesRepository coordinatesRepository;
  private OrganizationRepository organizationRepository;
  private PersonRepository personRepository;

  @Override
  @Mappings({
    @Mapping(target = "user", ignore = true),
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "creationDate", ignore = true),
    @Mapping(target = "adminPermission", defaultValue = "false"),
    @Mapping(
        target = "coordinates",
        expression = "java(findCoordinatesById(request.getCoordinatesId()))"),
    @Mapping(
        target = "manufacturer",
        expression = "java(findOrganizationById(request.getManufacturerId()))"),
    @Mapping(target = "owner", expression = "java(findPersonById(request.getOwnerId()))"),
  })
  public abstract Product mapFromRequest(ProductRequestDto request);

  protected Coordinates findCoordinatesById(Integer id) {
    return coordinatesRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Coordinates not found"));
  }

  protected Organization findOrganizationById(Integer id) {
    return organizationRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Organization not found"));
  }

  protected Person findPersonById(Integer id) {
    return personRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Person not found"));
  }

  @Autowired
  private void setRepositories(
      CoordinatesRepository coordinatesRepository,
      OrganizationRepository organizationRepository,
      PersonRepository personRepository) {
    this.coordinatesRepository = coordinatesRepository;
    this.organizationRepository = organizationRepository;
    this.personRepository = personRepository;
  }
}
