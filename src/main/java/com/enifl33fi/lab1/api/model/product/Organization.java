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
public class Organization extends OwnedEntity {
  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "official_address_id", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Address officialAddress;

  @Column private Integer annualTurnover;

  @Column(nullable = false)
  private Long employeesCount;

  @Column(nullable = false)
  private Float rating;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private OrganizationType type;
}
