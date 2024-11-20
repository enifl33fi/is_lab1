package com.enifl33fi.lab1.api.repository;

import com.enifl33fi.lab1.api.model.product.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {}
