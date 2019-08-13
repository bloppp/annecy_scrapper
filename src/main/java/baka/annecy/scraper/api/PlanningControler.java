package baka.annecy.scraper.api;

import static baka.annecy.scraper.configuration.security.SecurityConstants.ATTRIBUTE_JWT_USER;
import static baka.annecy.scraper.configuration.security.SecurityConstants.SECURITY_SCHEME_JW_TOKEN;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baka.annecy.scraper.application.planning.PlanningService;
import baka.annecy.scraper.application.planning.command.GeneratePlanningListCommand;
import baka.annecy.scraper.domain.planning.dto.PlanningDto;
import baka.annecy.scraper.domain.user.security.UserJwtPayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import springfox.documentation.annotations.ApiIgnore;

@Validated
@RestController
@Api(tags = {"Planning"})
@RequestMapping("/planning")
public class PlanningControler {

  @Autowired PlanningService planningService;;

  @ApiOperation(
      value = "Get all session",
      authorizations = {@Authorization(SECURITY_SCHEME_JW_TOKEN)})
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/generate")
  public ResponseEntity<List<PlanningDto>> generatePlanning(
      @ApiIgnore @RequestAttribute(ATTRIBUTE_JWT_USER) UserJwtPayload userJwtPayload) {
    return ResponseEntity.ok(
        planningService.generatePlanningList(
            new GeneratePlanningListCommand(userJwtPayload.getLogin())));
  }

  @ApiOperation(
      value = "Get all session",
      authorizations = {@Authorization(SECURITY_SCHEME_JW_TOKEN)})
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{planning-id}/select")
  public ResponseEntity<PlanningDto> selectPlanning(
      @RequestAttribute("planning-id") UUID id,
      @ApiIgnore @RequestAttribute(ATTRIBUTE_JWT_USER) UserJwtPayload userJwtPayload) {
    return ResponseEntity.ok(planningService.selectPlanning(id, userJwtPayload.getLogin()));
  }
}
