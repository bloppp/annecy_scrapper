package annecy.scrapper.annecy.scrapper.domain;

import java.util.ArrayList;
import java.util.List;

public class Planning {
  private List<Session> sessionList;

  public Planning() {
    this.sessionList = new ArrayList<Session>();
  }

  public List<Session> getSessionSet() {
    return sessionList;
  }

  public void addSession(Session session) {
    sessionList.add(session);
  }

  @Override
  public String toString() {
    String tmp = "Planning [";
    for (Session session : sessionList) {
      tmp += session.toString();
    }
    return tmp + "]";
  }
}
