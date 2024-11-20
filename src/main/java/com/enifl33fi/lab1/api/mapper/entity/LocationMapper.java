package com.enifl33fi.lab1.api.mapper.entity;

import com.enifl33fi.lab1.api.dto.request.entity.LocationRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.LocationResponseDto;
import com.enifl33fi.lab1.api.model.product.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class LocationMapper
    extends OwnedEntityMapper<Location, LocationRequestDto, LocationResponseDto> {}
