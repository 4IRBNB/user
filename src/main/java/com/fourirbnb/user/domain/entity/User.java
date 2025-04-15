package com.fourirbnb.user.domain.entity;


import com.fourirbnb.common.domain.BaseEntity;
import com.fourirbnb.user.presentation.dto.UpdateUserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

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

  public User(String email, String password, String nickname, String username, String phone,
      String slackId, Role role) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.username = username;
    this.phone = phone;
    this.slackId = slackId;
    this.role = role;
  }


  public void update(UpdateUserRequest request) {
    this.nickname = request.getNickname();
    this.phone = request.getPhone();
    this.slackId = request.getSlackId();
  }

  public void updatePassword(String encodedPassword) {
    this.password = encodedPassword;
  }
}
