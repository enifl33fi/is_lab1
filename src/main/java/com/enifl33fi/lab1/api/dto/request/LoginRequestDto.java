package com.enifl33fi.lab1.api.dto.request;

import com.enifl33fi.lab1.api.utils.validation.user.username.ValidUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
  @ValidUsername private String username;
  @NotBlank private String password;
}
