package baka.annecy.scraper.domain.user;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class UserJwtPayload {

  private UUID id;
  private String email;
  private String firstName;
  private String lastName;
  private String role;

  @Builder
  @JsonCreator
  protected UserJwtPayload(
      @NonNull UUID id, String email, String firstName, String lastName, @NonNull String role) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

  public boolean isAdmin() {
    return role.equalsIgnoreCase("ADMIN");
  }

  public boolean isEmployee() {
    return role.equalsIgnoreCase("SUPPORT") || role.equalsIgnoreCase("RELAY") || isAdmin();
  }

  public boolean isArtisan() {
    return role.equalsIgnoreCase("ARTISAN");
  }

  public boolean isClient() {
    return role.equalsIgnoreCase("CLIENT");
  }
}
