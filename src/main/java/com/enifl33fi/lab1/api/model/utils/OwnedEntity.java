package com.enifl33fi.lab1.api.model.utils;

import com.enifl33fi.lab1.api.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(OwnedEntityListener.class)
public class OwnedEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "created_by", referencedColumnName = "username", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @Column(name = "creation_date", nullable = false, updatable = false)
  private java.util.Date creationDate;

  @Column(name = "admin_permission", nullable = false)
  private boolean adminPermission;
}
