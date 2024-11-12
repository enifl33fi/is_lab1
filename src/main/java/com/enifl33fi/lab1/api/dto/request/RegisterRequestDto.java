package com.enifl33fi.lab1.api.dto.request;

import com.enifl33fi.lab1.api.model.user.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto extends LoginRequestDto {
  @NotNull private Role role;
}
