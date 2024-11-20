package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.entity.CoordinatesRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.CoordinatesResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.CoordinatesMapper;
import com.enifl33fi.lab1.api.model.product.Coordinates;
import com.enifl33fi.lab1.api.repository.CoordinatesRepository;
import com.enifl33fi.lab1.api.service.entity.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coordinates")
public class CoordinatesController
    extends OwnedEntityController<
        Coordinates,
        CoordinatesRequestDto,
        CoordinatesResponseDto,
        CoordinatesMapper,
        CoordinatesRepository,
        CoordinatesService> {

  @Autowired
  public CoordinatesController(CoordinatesService coordinatesService) {
    super(coordinatesService);
  }
}
