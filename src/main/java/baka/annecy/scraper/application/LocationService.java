package baka.annecy.scraper.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baka.annecy.scraper.domain.location.Location;
import baka.annecy.scraper.domain.location.LocationRepository;

@Service
@Transactional
public class LocationService {

  @Autowired LocationRepository locationRepository;

  public void saveList(List<Location> locationList) {
    for (Location category : locationList) {
      Location oldLocation = locationRepository.findForUpdateByName(category.getName());
      if (oldLocation == null) {
    	  locationRepository.save(category);
      }
    }
  }
}
