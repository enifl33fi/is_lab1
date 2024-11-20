package com.enifl33fi.lab1.api.mapper.entity;

import com.enifl33fi.lab1.api.dto.request.entity.PersonRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.PersonResponseDto;
import com.enifl33fi.lab1.api.exception.NotFoundException;
import com.enifl33fi.lab1.api.model.product.Location;
import com.enifl33fi.lab1.api.model.product.Person;
import com.enifl33fi.lab1.api.repository.LocationRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = "spring",
    uses = {LocationMapper.class})
public abstract class PersonMapper
    extends OwnedEntityMapper<Person, PersonRequestDto, PersonResponseDto> {
  private LocationRepository locationRepository;

  @Override
  @Mappings({
    @Mapping(target = "user", ignore = true),
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "creationDate", ignore = true),
    @Mapping(target = "adminPermission", defaultValue = "false"),
    @Mapping(target = "location", expression = "java(findLocationById(request.getLocationId()))"),
  })
  public abstract Person mapFromRequest(PersonRequestDto request);

  protected Location findLocationById(Integer id) {
    return locationRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Location not found"));
  }

  @Autowired
  private void setLocationRepository(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }
}
