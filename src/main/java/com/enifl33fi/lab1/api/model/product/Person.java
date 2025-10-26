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
public class Person extends OwnedEntity {
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Color eyeColor;

  @Column
  @Enumerated(value = EnumType.STRING)
  private Color hairColor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  @CascadeOnDelete
  private Location location;

  @Column
  private Integer weight;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Country nationality;
}
