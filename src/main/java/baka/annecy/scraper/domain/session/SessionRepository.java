package baka.annecy.scraper.domain.session;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface SessionRepository extends Repository<Session, String> {
  public List<Session> findAll();
  
  public List<Session> findByMovieCategoryName(String categoryTitle);

  public Session findById(String id);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public Session findForUpdateById(String id);

  @Modifying
  public void save(Session session);
}
