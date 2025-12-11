package com.enifl33fi.lab1.api.model.product;

import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Cacheable
public class Address extends OwnedEntity {
  @Column(nullable = false)
  private String zipCode;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Location town;
}
