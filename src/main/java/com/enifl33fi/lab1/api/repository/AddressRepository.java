package com.enifl33fi.lab1.api.repository;

import com.enifl33fi.lab1.api.model.product.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {}
