package com.enifl33fi.lab1.api.service.entity;

import com.enifl33fi.lab1.api.dto.request.entity.AddressRequestDto;
import com.enifl33fi.lab1.api.dto.response.entity.AddressResponseDto;
import com.enifl33fi.lab1.api.mapper.entity.AddressMapper;
import com.enifl33fi.lab1.api.model.product.Address;
import com.enifl33fi.lab1.api.repository.entity.AddressRepository;
import com.enifl33fi.lab1.api.service.EventPublisher;
import com.enifl33fi.lab1.api.service.ValidatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService
    extends OwnedEntityService<
        Address, AddressRequestDto, AddressResponseDto, AddressMapper, AddressRepository> {

  @Autowired
  public AddressService(
      ValidatingService validatingService,
      AddressRepository addressRepository,
      EventPublisher eventPublisher,
      AddressMapper addressMapper) {
    super(addressMapper, addressRepository, validatingService, eventPublisher, "address");
  }
}
