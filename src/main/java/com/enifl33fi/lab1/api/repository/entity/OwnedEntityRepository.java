package com.enifl33fi.lab1.api.repository.entity;

import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface OwnedEntityRepository<E extends OwnedEntity>
    extends JpaRepository<E, Integer>, JpaSpecificationExecutor<E> {
  @Query("SELECT e FROM #{#entityName} e WHERE CONCAT('', e.id) LIKE CONCAT(:prefix, '%')")
  List<E> findFirstNByIdPrefix(@Param("prefix") String prefix, Pageable pageable);
}
