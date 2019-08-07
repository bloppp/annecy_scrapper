package baka.annecy.scraper.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baka.annecy.scraper.application.SessionService;
import baka.annecy.scraper.domain.session.dto.SessionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

  @ApiOperation(value = "Get all session")
  @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<SessionDto>> importSession() {
    return ResponseEntity.ok(sessionService.importSessions());
  }
}
