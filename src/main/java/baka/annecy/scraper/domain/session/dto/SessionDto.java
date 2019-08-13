package baka.annecy.scraper.domain.session.dto;

import baka.annecy.scraper.domain.movie.dto.MovieDto;
import baka.annecy.scraper.domain.session.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SessionDto {

  private String id;
  private MovieDto movie;
  private Long startTimestamp;
  private Long endTimestamp;
  private String location;

  public SessionDto(Session session) {
    this.id = session.getId();
    this.movie = new MovieDto(session.getMovie());
    this.startTimestamp =
        session.getStartDateTime() != null ? session.getStartDateTime().toEpochMilli() : null;
    this.endTimestamp =
        session.getEndDateTime() != null ? session.getEndDateTime().toEpochMilli() : null;
    this.location = session.getLocation().getName();
  }
}
