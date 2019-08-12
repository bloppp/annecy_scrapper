package baka.annecy.scraper.domain.session.dto;

import baka.annecy.scraper.domain.session.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SessionDto {

  private String id;
  private String title;
  private String category;
  private Long startTimestamp;
  private Long endTimestamp;
  private String location;

  public SessionDto(Session session) {
    this.id = session.getId();
    this.title = session.getMovie().getTitle();
    this.category = session.getMovie().getCategory().getName();
    this.startTimestamp =
        session.getStartDateTime() != null ? session.getStartDateTime().toEpochMilli() : null;
    this.endTimestamp =
        session.getEndDateTime() != null ? session.getEndDateTime().toEpochMilli() : null;
    this.location = session.getLocation().getName();
  }
}
