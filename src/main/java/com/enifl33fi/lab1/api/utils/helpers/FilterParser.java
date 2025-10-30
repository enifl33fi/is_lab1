package com.enifl33fi.lab1.api.utils.helpers;

import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import java.util.Map;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FilterParser<E extends OwnedEntity> {
  public Specification<E> parse(Map<String, String> filtersValues) {
    if (filtersValues == null || filtersValues.isEmpty()) {
      return Specification.where(null);
    }

    Specification<E> specification = Specification.where(null);

    for (Map.Entry<String, String> entry : filtersValues.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();

      if (value != null && !value.isEmpty()) {
        specification =
            specification.and(
                (root, query, criteriaBuilder) -> {
                  try {
                    Class<?> fieldType = root.get(key).getJavaType();

                    if (fieldType == String.class || fieldType.isEnum()) {
                      if (fieldType.isEnum()) {
                        return criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(key).as(String.class)),
                            "%" + value.toLowerCase() + "%");
                      } else {
                        return criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(key)), "%" + value.toLowerCase() + "%");
                      }
                    } else {
                      return criteriaBuilder.conjunction();
                    }
                  } catch (IllegalArgumentException e) {
                    return criteriaBuilder.conjunction();
                  }
                });
      }
    }

    return specification;
  }
}
