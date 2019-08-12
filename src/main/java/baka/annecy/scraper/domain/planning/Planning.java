package baka.annecy.scraper.domain.planning;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.BatchSize;

import baka.annecy.scraper.domain.session.Session;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Table
@ToString
@AllArgsConstructor
@BatchSize(size = 100)
@Entity(name = "planning")
@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Planning {

  @Id private UUID id;

  @NonNull private Float score;

  @Valid
  @BatchSize(size = 100)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "planning_session",
      joinColumns = @JoinColumn(name = "planning_id", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "session_id", nullable = false),
      indexes = {@Index(columnList = "planning_id"), @Index(columnList = "session_id")})
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