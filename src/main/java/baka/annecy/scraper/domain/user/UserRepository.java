package baka.annecy.scraper.domain.user;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {

  public User findByLogin(String id);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public User findForUpdateByLogin(String id);

  @Modifying
  public void save(User user);
}
