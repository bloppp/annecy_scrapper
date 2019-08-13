package baka.annecy.scraper.application.planning;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baka.annecy.scraper.application.planning.command.GeneratePlanningListCommand;
import baka.annecy.scraper.domain.planning.Planning;
import baka.annecy.scraper.domain.planning.PlanningRepository;
import baka.annecy.scraper.domain.planning.dto.PlanningDto;
import baka.annecy.scraper.domain.session.Session;
import baka.annecy.scraper.domain.session.SessionRepository;
import baka.annecy.scraper.domain.user.User;
import baka.annecy.scraper.domain.user.UserRepository;

@Service
@Transactional
public class PlanningService {

  @Autowired SessionRepository sessionRepository;
  @Autowired UserRepository userRepository;
  @Autowired PlanningRepository planningRepository;

  public List<PlanningDto> generatePlanningList(GeneratePlanningListCommand command) {

    List<Session> sessionList = sessionRepository.findAll();
    List<Planning> planningList = new ArrayList<Planning>();

    // TODO GoGo Tialys rangers !

    savePlanningList(planningList, userRepository.findByLogin(command.getUserLoging()));

    return planningList.stream().map(PlanningDto::new).collect(Collectors.toList());
  }

  public void savePlanningList(List<Planning> planningList, User user) {
    for (Planning planning : planningList) {
      planning.setUser(user);
      planningRepository.save(planning);
    }
  }

  public PlanningDto selectPlanning(UUID id, String login) {
    List<Planning> planningList = planningRepository.findByUserLogin(login);
    Planning selectedPlanning = null;
    for (Planning planning : planningList) {
      if (planning.getId().equals(id)) {
        selectedPlanning = planning;
        continue;
      }
      planningRepository.delete(planning);
    }

    return selectedPlanning != null ? new PlanningDto(selectedPlanning) : null;
  }
}
