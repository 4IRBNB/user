package com.fourirbnb.user.domain.repository;

import com.fourirbnb.user.domain.entity.ApprovalStatus;
import com.fourirbnb.user.domain.entity.Staging;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagingRepository extends JpaRepository<Staging, UUID> {

  boolean existsByEmail(String email);

  boolean existsByEmailAndStatusIn(
      String email, List<ApprovalStatus> pending);

  List<Staging> findAllByStatus(ApprovalStatus approvalStatus);
}
