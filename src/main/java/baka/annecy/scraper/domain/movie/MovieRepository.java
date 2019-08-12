package baka.annecy.scraper.domain.movie;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface MovieRepository extends Repository<Movie, String> {
  public List<Movie> findAll();

  public Movie findByTitle(String id);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public Movie findForUpdateByTitle(String id);

  @Modifying
  public void save(Movie session);
}
