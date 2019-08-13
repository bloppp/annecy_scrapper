package baka.annecy.scraper.api;

import static baka.annecy.scraper.configuration.MessageConstants.HEADER_JWT;
import static baka.annecy.scraper.configuration.security.SecurityConstants.ATTRIBUTE_JWT_USER;
import static baka.annecy.scraper.configuration.security.SecurityConstants.SECURITY_SCHEME_JW_TOKEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baka.annecy.scraper.application.user.UserService;
import baka.annecy.scraper.application.user.command.CreateUserCommand;
import baka.annecy.scraper.configuration.security.SecurityUtils;
import baka.annecy.scraper.domain.user.dto.UserDto;
import baka.annecy.scraper.domain.user.security.UserJwtPayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import springfox.documentation.annotations.ApiIgnore;

@Validated
@RestController
@Api(tags = {"User"})
@RequestMapping("/user")
public class UserControler {

  @Autowired UserService userService;
  @Autowired SecurityUtils securityUtils;

  @ApiOperation(value = "Create user")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> createUser(@RequestBody CreateUserCommand command) {
    UserDto user = userService.createUser(command);

    String jwtToken = securityUtils.generateJwToken(user);
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HEADER_JWT, jwtToken);

    return new ResponseEntity<UserDto>(user, headers, HttpStatus.OK);
  }

  @ApiOperation(value = "login")
  @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/login")
  public ResponseEntity<UserDto> login(@RequestBody CreateUserCommand command) {
    UserDto user = userService.login(command);

    String jwtToken = securityUtils.generateJwToken(user);
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HEADER_JWT, jwtToken);

    return new ResponseEntity<UserDto>(user, headers, HttpStatus.OK);
  }

  @ApiOperation(
      value = "Validate token",
      authorizations = {@Authorization(SECURITY_SCHEME_JW_TOKEN)})
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> validateToken(
      @ApiIgnore @RequestAttribute(ATTRIBUTE_JWT_USER) UserJwtPayload userJwtPayload) {
    UserDto user = userService.getUserByLogin(userJwtPayload.getLogin());

    return ResponseEntity.ok(user);
  }
}
