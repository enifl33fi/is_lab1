package com.enifl33fi.lab1.api.repository;

import com.enifl33fi.lab1.api.model.user.Role;
import com.enifl33fi.lab1.api.model.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  List<User> findAllByRole(Role role);
}
