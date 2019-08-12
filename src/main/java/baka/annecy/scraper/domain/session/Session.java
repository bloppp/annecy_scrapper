package baka.annecy.scraper.domain.session;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import baka.annecy.scraper.domain.location.Location;
import baka.annecy.scraper.domain.movie.Movie;
import baka.annecy.scraper.domain.movie.Category.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@ToString
@AllArgsConstructor
@BatchSize(size = 100)
@Entity(name = "session")
@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {
  @Id private String id;

  @Valid
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie")
  @NotNull
  private Movie movie;

  @Valid
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location")
  private Location location;

  @NotNull private Instant startDateTime;
  @NotNull private Instant endDateTime;

  public Session(
      String id,
      @NotNull String title,
      @NotNull String category,
      @NotNull Instant startDateTime,
      @NotNull Instant endDateTime,
      @NotNull String location,
      Integer year) {
    this.id = id;
    this.movie = new Movie(title, new Category(category), year);
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.location = new Location(location);
  }

  public void update(
      @NotNull String title,
      @NotNull String category,
      @NotNull Instant startDateTime,
      @NotNull Instant endDateTime,
      @NotNull Location location) {
    this.movie = new Movie(title, new Category(category), movie.getYear());
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.location = location;
  }
}
