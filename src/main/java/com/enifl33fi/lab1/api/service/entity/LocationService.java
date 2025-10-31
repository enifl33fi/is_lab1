package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.LocationRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.LocationResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.LocationMapper;
import com.enifl33fi.lab1.api.model.product.Location;
import com.enifl33fi.lab1.api.repository.entity.LocationRepository;
import com.enifl33fi.lab1.api.service.EventPublisher;
import com.enifl33fi.lab1.api.service.ValidatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService
    extends OwnedEntityService<
        Location, LocationRequestDto, LocationResponseDto, LocationMapper, LocationRepository> {

  @Autowired
  public LocationService(
      ValidatingService validatingService,
      LocationRepository locationRepository,
      EventPublisher eventPublisher,
      LocationMapper locationMapper) {
    super(locationMapper, locationRepository, validatingService, eventPublisher, "location");
  }
}
