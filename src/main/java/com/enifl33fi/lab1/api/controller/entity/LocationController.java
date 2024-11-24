package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.entity.LocationRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.LocationResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.LocationMapper;
import com.enifl33fi.lab1.api.model.product.Location;
import com.enifl33fi.lab1.api.repository.entity.LocationRepository;
import com.enifl33fi.lab1.api.service.entity.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
public class LocationController
    extends OwnedEntityController<
        Location,
        LocationRequestDto,
        LocationResponseDto,
        LocationMapper,
        LocationRepository,
        LocationService> {

  @Autowired
  public LocationController(LocationService locationService) {
    super(locationService);
  }
}
