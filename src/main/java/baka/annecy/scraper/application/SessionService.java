package baka.annecy.scraper.application;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baka.annecy.scraper.domain.session.Session;
import baka.annecy.scraper.domain.session.SessionRepository;
import baka.annecy.scraper.domain.session.dto.SessionDto;

@Service
@Transactional
public class SessionService {
  @Autowired SessionRepository sessionRepository;
  @Autowired MovieService movieService;
  @Autowired LocationService locationService;

  private List<Session> getAllSessionFromWebsite() {
    Document doc;

    List<String> dayList =
        Arrays.asList(
            "2019-06-10", "2019-06-11", "2019-06-12", "2019-06-13", "2019-06-14", "2019-06-15");

    /* pour l'année prochaine :
     * List<String> dayList =
     * Arrays.asList(
     * "2020-06-15", "2020-06-16", "2020-06-17", "2020-06-18", "2020-06-19", "2020-06-20");
     */

    List<Session> sessionList = new ArrayList<Session>();

    Integer year =
        Instant.now().atZone(ZoneId.of("Europe/Paris")).getMonth().compareTo(Month.JUNE) < 0
            ? Instant.now().atZone(ZoneId.of("Europe/Paris")).getYear() - 1
            : Instant.now().atZone(ZoneId.of("Europe/Paris")).getYear();

    for (String day : dayList) {
      try {
        doc = Jsoup.connect("https://www.annecy.org/programme:" + day).get();

        Elements htmlSessionList = doc.select("li.prog__item");
        for (Element htmlSession : htmlSessionList) {
          Element hours = htmlSession.select("ul.infoProg__det-prat").get(0).child(0);
          Pattern p = Pattern.compile("([0-9]{2}:[0-9]{2})( → )(([0-9]{2}:[0-9]{2}))");

          Matcher m = p.matcher(hours.html());
          if (!m.find()) {
            continue;
          }
          LocalTime startTime = LocalTime.parse(m.group(1).trim());
          LocalTime endTime = LocalTime.parse(m.group(3).trim());

          Element theater = htmlSession.select("ul.infoProg__det-prat").get(0).child(1);

          p = Pattern.compile("(<span.+<\\/span>)(.+)");
          m = p.matcher(theater.html());
          if (!m.find()) {
            continue;
          }
          String location = m.group(2).trim();

          String title = htmlSession.select("h3").html().trim();

          String sessionLink = htmlSession.select("a.voirFiche").get(0).attr("href");
          p = Pattern.compile("(.+plng-)([0-9]+)");
          m = p.matcher(sessionLink);
          if (!m.find()) {
            continue;
          }
          String id = m.group(2).trim();

          Instant startOfDay =
              LocalDate.parse(day).atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();

          String category = htmlSession.select("p.catProg").get(0).child(0).html();

          Session session =
              new Session(
                  id,
                  title,
                  category,
                  startOfDay
                      .plus(startTime.getHour(), ChronoUnit.HOURS)
                      .plus(startTime.getMinute(), ChronoUnit.MINUTES),
                  startOfDay
                      .plus(
                          endTime.getHour() >= startTime.getHour()
                              ? endTime.getHour()
                              : endTime.getHour() + 24,
                          ChronoUnit.HOURS)
                      .plus(endTime.getMinute(), ChronoUnit.MINUTES),
                  location,
                  year);
          sessionList.add(session);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sessionList;
  }

  @Transactional
  public List<SessionDto> importSessions() {
    List<Session> sessionList = getAllSessionFromWebsite();

    movieService.saveList(sessionList.stream().map(Session::getMovie).collect(Collectors.toList()));
    locationService.saveList(sessionList.stream().map(Session::getLocation).collect(Collectors.toList()));

    sessionList.stream().forEach(this::saveSession);
    return sessionList.stream().map(SessionDto::new).collect(Collectors.toList());
  }

  @Transactional
  public List<SessionDto> findAllSession() {
    return sessionRepository.findAll().stream().map(SessionDto::new).collect(Collectors.toList());
  }

  private void saveSession(Session session) {
    Session oldSession = sessionRepository.findForUpdateById(session.getId());

    if (oldSession == null) {
      sessionRepository.save(session);
    } else {
      oldSession.update(
          session.getMovie().getTitle(),
          session.getMovie().getCategory().getName(),
          session.getStartDateTime(),
          session.getEndDateTime(),
          session.getLocation());
      sessionRepository.save(oldSession);
    }
  }
}
