package com.fourirbnb.user.domain.repository;

import com.fourirbnb.user.domain.entity.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

  boolean existsByEmail(String email);

  Optional<Admin> findByEmail(String email);
}
