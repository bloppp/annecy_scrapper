package baka.annecy.scraper.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import baka.annecy.scraper.domain.planning.Planning;
import baka.annecy.scraper.domain.planning.dto.PlanningDto;
import baka.annecy.scraper.domain.session.Session;
import baka.annecy.scraper.domain.session.SessionRepository;

public class PlanningService {

  @Autowired SessionRepository sessionRepository;

  public List<PlanningDto> generatePlanningList() {

    List<Session> sessionList = sessionRepository.findAll();
    List<Planning> planningList = new ArrayList<Planning>();

    // TODO GoGo Tialys rangers !

    return planningList.stream().map(PlanningDto::new).collect(Collectors.toList());
  }
}
