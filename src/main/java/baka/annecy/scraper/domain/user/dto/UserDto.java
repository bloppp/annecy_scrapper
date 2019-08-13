package baka.annecy.scraper.domain.user.dto;

import baka.annecy.scraper.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
  private String login;
  private String role;

  public UserDto(User user) {
    this.login = user.getLogin();
    this.role = user.getRole().toString();
  }
}
