package com.enifl33fi.lab1.api.repository.entity;

import com.enifl33fi.lab1.api.model.product.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends OwnedEntityRepository<Person> {}
