package annecy.scrapper.annecy.scrapper;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import annecy.scrapper.annecy.scrapper.domain.Session;

public class App {
  public static void main(String[] args) {
    Document doc;

    List<String> dayList =
        Arrays.asList(
            "2019-06-10", "2019-06-11", "2019-06-12", "2019-06-13", "2019-06-14", "2019-06-15");

    /* pour l'année prochaine :
     * List<String> dayList =
     * Arrays.asList(
     * "2020-06-15", "2020-06-16", "2020-06-17", "2020-06-18", "2020-06-19", "2020-06-20");
     */

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
                      .plus(endTime.getHour(), ChronoUnit.HOURS)
                      .plus(endTime.getMinute(), ChronoUnit.MINUTES),
                  location);
          System.out.println(session.toString());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
