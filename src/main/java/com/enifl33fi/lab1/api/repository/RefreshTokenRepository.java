package com.enifl33fi.lab1.api.repository;

import com.enifl33fi.lab1.api.model.security.RefreshToken;
import com.enifl33fi.lab1.api.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  void deleteByToken(String token);

  Optional<RefreshToken> findByUser(User user);
}
