package com.enifl33fi.lab1.api.mapper.entity;

import com.enifl33fi.lab1.api.dto.request.entity.OrganizationRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.OrganizationResponseDto;
import com.enifl33fi.lab1.api.exception.NotFoundException;
import com.enifl33fi.lab1.api.model.product.Address;
import com.enifl33fi.lab1.api.model.product.Organization;
import com.enifl33fi.lab1.api.repository.entity.AddressRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = "spring",
    uses = {AddressMapper.class})
public abstract class OrganizationMapper
    extends OwnedEntityMapper<Organization, OrganizationRequestDto, OrganizationResponseDto> {
  private AddressRepository addressRepository;

  @Override
  @Mappings({
    @Mapping(target = "user", ignore = true),
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "creationDate", ignore = true),
    @Mapping(target = "adminPermission", defaultValue = "false"),
    @Mapping(
        target = "officialAddress",
        expression = "java(findAddressById(request.getOfficialAddressId()))"),
  })
  public abstract Organization mapFromRequest(OrganizationRequestDto request);

  protected Address findAddressById(Integer id) {
    if (id == null) {
      return null;
    }

    return addressRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Address not found"));
  }

  @Autowired
  private void setAddressRepository(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }
}
