package baka.annecy.scraper.domain.movie.Category;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

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
@Entity(name = "category")
@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
  @Id private String name;
}
