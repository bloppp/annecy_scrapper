package baka.annecy.scraper.domain.session;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "session")
@Getter
@ToString
@AllArgsConstructor
public class Session {
  @Id private String id;
  @NotNull private String title;
  @NotNull private String category;
  @NotNull private Instant startDateTime;
  @NotNull private Instant endDateTime;
  @NotNull private String location;

  public void update(
      String id,
      @NotNull String title,
      @NotNull String category,
      @NotNull Instant startDateTime,
      @NotNull Instant endDateTime,
      @NotNull String location) {
    this.id = id;
    this.title = title;
    this.category = category;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.location = location;
  }
}
