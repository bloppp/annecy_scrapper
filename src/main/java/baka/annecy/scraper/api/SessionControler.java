package baka.annecy.scraper.api;

import static baka.annecy.scraper.configuration.security.SecurityConstants.ATTRIBUTE_JWT_USER;
import static baka.annecy.scraper.configuration.security.SecurityConstants.SECURITY_SCHEME_JW_TOKEN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baka.annecy.scraper.application.SessionService;
import baka.annecy.scraper.configuration.exception.ApplicationAuthorisationException;
import baka.annecy.scraper.domain.session.dto.SessionDto;
import baka.annecy.scraper.domain.user.User.Role;
import baka.annecy.scraper.domain.user.security.UserJwtPayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import springfox.documentation.annotations.ApiIgnore;

@Validated
@RestController
@Api(tags = {"Session"})
@RequestMapping("/session")
public class SessionControler {

  @Autowired SessionService sessionService;

  @ApiOperation(value = "Get all session")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<SessionDto>> findAllSession() {
    return ResponseEntity.ok(sessionService.findAllSession());
  }

  @ApiOperation(value = "Get session by category")
  @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/title/{category-title}")
  public ResponseEntity<List<SessionDto>> findSessionByCategory(
      @PathVariable("category-title") String categoryTitle) {
    return ResponseEntity.ok(sessionService.findByCategory(categoryTitle));
  }

  @ApiOperation(
      value = "Import all session",
      authorizations = {@Authorization(SECURITY_SCHEME_JW_TOKEN)})
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/import")
  public ResponseEntity<List<SessionDto>> importSession(
      @ApiIgnore @RequestAttribute(ATTRIBUTE_JWT_USER) UserJwtPayload userJwtPayload) {
    if (!userJwtPayload.getRole().equals(Role.ADMIN)) {
      throw new ApplicationAuthorisationException("not-authorized");
    }
    return ResponseEntity.ok(sessionService.importSessions());
  }
}
