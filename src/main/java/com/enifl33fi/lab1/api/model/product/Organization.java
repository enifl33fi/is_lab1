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
public class Organization extends OwnedEntity {
  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "official_address_id", referencedColumnName = "id", nullable = false)
  @CascadeOnDelete
  private Address officialAddress;

  @Column(nullable = false)
  private Float annualTurnover;

  @Column(nullable = false)
  private long employeesCount;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private Float rating;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "postal_address_id", referencedColumnName = "id", nullable = false)
  @CascadeOnDelete
  private Address postalAddress;
}
