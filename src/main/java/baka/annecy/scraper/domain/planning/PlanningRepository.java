package baka.annecy.scraper.domain.planning;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface PlanningRepository extends Repository<Planning, UUID> {

  public Planning findById(UUID id);
  
  public List<Planning> findByUserLogin(String login);

  @Modifying
  public void delete(Planning planning);

  @Modifying
  public void save(Planning planning);
}
