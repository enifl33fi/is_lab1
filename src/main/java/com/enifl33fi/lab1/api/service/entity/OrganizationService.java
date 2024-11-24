package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.OrganizationRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.OrganizationResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.OrganizationMapper;
import com.enifl33fi.lab1.api.model.product.Organization;
import com.enifl33fi.lab1.api.repository.entity.OrganizationRepository;
import com.enifl33fi.lab1.api.service.ValidatingService;
import com.enifl33fi.lab1.api.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService
    extends OwnedEntityService<
        Organization,
        OrganizationRequestDto,
        OrganizationResponseDto,
        OrganizationMapper,
        OrganizationRepository> {

  @Autowired
  public OrganizationService(
      ValidatingService validatingService,
      OrganizationRepository organizationRepository,
      WebSocketService webSocketService,
      OrganizationMapper organizationMapper) {
    super(
        organizationMapper,
        organizationRepository,
        validatingService,
        webSocketService,
        "organization");
  }
}
