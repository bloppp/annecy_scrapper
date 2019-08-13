package baka.annecy.scraper.domain.movie.dto;

import baka.annecy.scraper.domain.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MovieDto {

  private String title;
  private String category;
  private Integer year;

  public MovieDto(Movie movie) {
    this.title = movie.getTitle();
    this.category = movie.getCategory().getName();
    this.year = movie.getYear();
  }
}
