package baka.annecy.scraper.domain.movie.Category;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface CategoryRepository extends Repository<Category, String> {
  public List<Category> findAll();

  public Category findByName(String id);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public Category findForUpdateByName(String id);

  @Modifying
  public void save(Category session);
}
