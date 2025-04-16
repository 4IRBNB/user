package com.fourirbnb.user.domain.entity;

import com.fourirbnb.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "p_staging")
public class Staging extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  public UUID id;

  @Column(nullable = false)
  public String email;

  @Column(nullable = false)
  public String password;

  public String username;

  public String nickname;

  public String slackId;

  public String phone;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  public Role role;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  public ApprovalStatus status;

  public Staging(String email, String password, String username, String nickname,
      String slackId, String phone, Role role, ApprovalStatus status) {
    this.email = email;
    this.password = password;
    this.username = username;
    this.nickname = nickname;
    this.slackId = slackId;
    this.phone = phone;
    this.role = role;
    this.status = status;
  }

  public void updateStatus(ApprovalStatus approvalStatus) {
    this.status = approvalStatus;
  }
}
