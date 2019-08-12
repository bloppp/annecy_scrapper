package baka.annecy.scraper.domain.location;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface LocationRepository extends Repository<Location, String> {
  public List<Location> findAll();

  public Location findByName(String id);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public Location findForUpdateByName(String id);

  @Modifying
  public void save(Location location);
}
