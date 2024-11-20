package com.enifl33fi.lab1.api.mapper.entity;

import com.enifl33fi.lab1.api.dto.request.entity.CoordinatesRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.CoordinatesResponseDto;
import com.enifl33fi.lab1.api.model.product.Coordinates;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CoordinatesMapper
    extends OwnedEntityMapper<Coordinates, CoordinatesRequestDto, CoordinatesResponseDto> {}
