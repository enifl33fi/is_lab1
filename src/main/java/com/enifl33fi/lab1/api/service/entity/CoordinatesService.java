package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.CoordinatesRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.CoordinatesResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.CoordinatesMapper;
import com.enifl33fi.lab1.api.model.product.Coordinates;
import com.enifl33fi.lab1.api.repository.entity.CoordinatesRepository;
import com.enifl33fi.lab1.api.service.EventPublisher;
import com.enifl33fi.lab1.api.service.ValidatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesService
    extends OwnedEntityService<
        Coordinates,
        CoordinatesRequestDto,
        CoordinatesResponseDto,
        CoordinatesMapper,
        CoordinatesRepository> {

  @Autowired
  public CoordinatesService(
      ValidatingService validatingService,
      CoordinatesRepository coordinatesRepository,
      EventPublisher eventPublisher,
      CoordinatesMapper coordinatesMapper) {
    super(
        coordinatesMapper, coordinatesRepository, validatingService, eventPublisher, "coordinates");
  }
}
