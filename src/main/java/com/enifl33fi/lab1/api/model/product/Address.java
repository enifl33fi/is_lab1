package com.enifl33fi.lab1.api.model.product;

import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import jakarta.persistence.*;
import lombok.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Address extends OwnedEntity {
  @Column(nullable = false)
  private String zipCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  @CascadeOnDelete
  private Location town;
}
