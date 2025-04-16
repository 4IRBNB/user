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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "p_admin")
public class Admin extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(nullable = false, unique = true)
  public String email;

  @Column(nullable = false)
  public String password;

  @Column(nullable = false)
  public String username;

  @Column(nullable = false)
  public String nickname;

  @Column(nullable = false)
  public String slackId;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  public Role role;

  public String phone;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ApprovalStatus approvalStatus;

  public Admin(String email, String password, String nickname, String username, String phone,
      String slackId, Role role, ApprovalStatus status) {
    this.email = email;
    this.password = password;
    this.username = username;
    this.nickname = nickname;
    this.phone = phone;
    this.role = role;
    this.slackId = slackId;
    this.approvalStatus = status;
  }


}
