package baka.annecy.scraper.domain.planning;

import java.util.ArrayList;
import java.util.List;

import baka.annecy.scraper.domain.session.Session;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Planning {

  @NonNull private Float score;
  private List<Session> sessionList = new ArrayList<Session>();

  private Planning(List<Session> sessionList) {
    this.sessionList = sessionList;
    this.score = calculateScore();
  }

  public void addSession(Session session) {
    sessionList.add(session);
    calculateScore();
  }

  public Planning copy() {
    return new Planning(new ArrayList<Session>(sessionList));
  }

  private Float calculateScore() {
    // TODO GoGo Tialys Rangers !
    return 0f;
  }
}
