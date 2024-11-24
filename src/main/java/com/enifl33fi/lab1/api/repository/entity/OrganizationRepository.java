package com.enifl33fi.lab1.api.repository.entity;

import com.enifl33fi.lab1.api.model.product.Organization;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends OwnedEntityRepository<Organization> {}
