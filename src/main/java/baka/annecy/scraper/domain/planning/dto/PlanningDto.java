package baka.annecy.scraper.domain.planning.dto;

import java.util.List;
import java.util.stream.Collectors;

import baka.annecy.scraper.domain.planning.Planning;
import baka.annecy.scraper.domain.session.dto.SessionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PlanningDto {
  private Float score;
  private List<SessionDto> sessionList;

  public PlanningDto(Planning planning) {
    this.score = planning.getScore();
    this.sessionList =
        planning.getSessionList().stream().map(SessionDto::new).collect(Collectors.toList());
  }
}
