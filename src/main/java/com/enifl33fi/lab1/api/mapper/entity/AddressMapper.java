package com.enifl33fi.lab1.api.mapper.entity;

import com.enifl33fi.lab1.api.dto.request.entity.AddressRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.AddressResponseDto;
import com.enifl33fi.lab1.api.exception.NotFoundException;
import com.enifl33fi.lab1.api.model.product.Address;
import com.enifl33fi.lab1.api.model.product.Location;
import com.enifl33fi.lab1.api.repository.entity.LocationRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = "spring",
    uses = {LocationMapper.class})
public abstract class AddressMapper
    extends OwnedEntityMapper<Address, AddressRequestDto, AddressResponseDto> {
  private LocationRepository locationRepository;

  @Override
  @Mappings({
    @Mapping(target = "user", ignore = true),
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "creationDate", ignore = true),
    @Mapping(target = "adminPermission", defaultValue = "false"),
    @Mapping(target = "town", expression = "java(findLocationById(request.getTownId()))"),
  })
  public abstract Address mapFromRequest(AddressRequestDto request);

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
