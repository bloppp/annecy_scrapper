package baka.annecy.scraper.domain.user.security;

import com.fasterxml.jackson.annotation.JsonCreator;

import baka.annecy.scraper.domain.user.User.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class UserJwtPayload {

  private String login;
  private Role role;

  @Builder
  @JsonCreator
  protected UserJwtPayload(@NonNull String login, @NonNull String role) {
    this.login = login;
    this.role = Role.valueOf(role);
  }
}
