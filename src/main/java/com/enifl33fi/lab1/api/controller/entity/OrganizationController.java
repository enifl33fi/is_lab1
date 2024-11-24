package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.entity.OrganizationRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.OrganizationResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.OrganizationMapper;
import com.enifl33fi.lab1.api.model.product.Organization;
import com.enifl33fi.lab1.api.repository.entity.OrganizationRepository;
import com.enifl33fi.lab1.api.service.entity.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController
    extends OwnedEntityController<
        Organization,
        OrganizationRequestDto,
        OrganizationResponseDto,
        OrganizationMapper,
        OrganizationRepository,
        OrganizationService> {

  @Autowired
  public OrganizationController(OrganizationService organizationService) {
    super(organizationService);
  }
}
