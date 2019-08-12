package baka.annecy.scraper.domain.movie;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

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
@Entity(name = "movie")
@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
  @Id private String title;

  @Valid
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category")
  private Category category;

  @NotNull private Integer year;

  public void update(Category category, Integer year) {
    this.category = category;
    this.year = year;
  }
}
