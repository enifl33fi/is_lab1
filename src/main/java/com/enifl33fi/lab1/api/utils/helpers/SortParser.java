package com.enifl33fi.lab1.api.utils.helpers;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SortParser {
  public Sort parse(String sortBy, Integer sortDirection) {
    if (sortBy == null || sortBy.isEmpty()) {
      sortBy = "id";
    }

    Sort.Direction direction =
        (sortDirection != null && sortDirection < 0) ? Sort.Direction.DESC : Sort.Direction.ASC;

    return Sort.by(direction, sortBy);
  }
}
