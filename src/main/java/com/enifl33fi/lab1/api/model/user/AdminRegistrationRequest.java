package com.enifl33fi.lab1.api.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AdminRegistrationRequest {
  @Id private String username;

  @Column(nullable = false)
  private String password;
}
