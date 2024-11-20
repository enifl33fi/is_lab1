package com.enifl33fi.lab1.api.controller.entity;

import com.enifl33fi.lab1.api.dto.request.entity.AddressRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.AddressResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.AddressMapper;
import com.enifl33fi.lab1.api.model.product.Address;
import com.enifl33fi.lab1.api.repository.AddressRepository;
import com.enifl33fi.lab1.api.service.entity.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController
    extends OwnedEntityController<
        Address,
        AddressRequestDto,
        AddressResponseDto,
        AddressMapper,
        AddressRepository,
        AddressService> {

  @Autowired
  public AddressController(AddressService addressService) {
    super(addressService);
  }
}
