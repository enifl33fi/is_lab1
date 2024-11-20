package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.entity.PersonRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.PersonResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.PersonMapper;
import com.enifl33fi.lab1.api.model.product.Person;
import com.enifl33fi.lab1.api.repository.PersonRepository;
import com.enifl33fi.lab1.api.service.entity.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController
    extends OwnedEntityController<
        Person,
        PersonRequestDto,
        PersonResponseDto,
        PersonMapper,
        PersonRepository,
        PersonService> {

  @Autowired
  public PersonController(PersonService personService) {
    super(personService);
  }
}
