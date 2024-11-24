package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.PersonRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.PersonResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.PersonMapper;
import com.enifl33fi.lab1.api.model.product.Person;
import com.enifl33fi.lab1.api.repository.entity.PersonRepository;
import com.enifl33fi.lab1.api.service.ValidatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService
    extends OwnedEntityService<
        Person, PersonRequestDto, PersonResponseDto, PersonMapper, PersonRepository> {

  @Autowired
  public PersonService(
      ValidatingService validatingService,
      PersonRepository personRepository,
      PersonMapper personMapper) {
    super(personMapper, personRepository, validatingService);
  }
}
