package com.fouribnb.user.domain.repository;


import com.fouribnb.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);
}
