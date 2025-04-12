package com.fourirbnb.user.domain.entity;

import com.fourirbnb.common.domain.BasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BasicEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(nullable = false, unique = true)
  public String email;

  @Column(nullable = false)
  public String username;

  @Column(nullable = false)
  public String nickname;

  @Column(nullable = false)
  public String slackId;

  @Column(nullable = false)
  public String role;

  public String phone;

  public User(String email, String username, String nickname,
      String slackId, String phone) {
    this.email = email;
    this.username = username;
    this.nickname = nickname;
    this.slackId = slackId;
    this.phone = phone;
  }
}
