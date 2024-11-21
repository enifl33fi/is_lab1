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
public class Product extends OwnedEntity {
  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "coordinates_id", referencedColumnName = "id", nullable = false)
  @CascadeOnDelete
  private Coordinates coordinates;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UnitOfMeasure unitOfMeasure;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
  @CascadeOnDelete
  private Organization manufacturer;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  private Integer manufactureCost;

  @Column(nullable = false)
  private Integer rating;

  @Column(nullable = false, unique = true)
  private String partNumber;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
  @CascadeOnDelete
  private Person owner;
}
