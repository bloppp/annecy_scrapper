package annecy.scrapper.annecy.scrapper.domain;

import java.time.Instant;

public class Session {
  private String id;
  private String title;
  private String category;
  private Instant startDateTime;
  private Instant endDateTime;
  private String location;

  public Session(
      String id,
      String title,
      String category,
      Instant startDateTime,
      Instant endDateTime,
      String location) {
    this.id = id;
    this.title = title;
    this.category = category;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.location = location;
  }

  @Override
  public String toString() {
    return "Session [id="
        + id
        + ", title="
        + title
        + ", category="
        + category
        + ", startDateTime="
        + startDateTime
        + ", endDateTime="
        + endDateTime
        + ", location="
        + location
        + "]";
  }
}
