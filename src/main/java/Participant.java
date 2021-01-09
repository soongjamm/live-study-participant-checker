import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHUser;

import java.util.ArrayList;
import java.util.List;

public class Participant implements Comparable {

    private List<GHIssue> participatedStudy = new ArrayList<>();
    private GHUser ghUser;

    public Participant(GHUser ghUser) {
        this.ghUser = ghUser;
    }

    public String getNickname() {
        return ghUser.getLogin();
    }

    public void participateStudy(GHIssue aStudy) {
        if (!participatedStudy.contains(aStudy)) {
            participatedStudy.add(aStudy);
        }
    }

    public List<GHIssue> getParticipatedStudyList() {
        return participatedStudy;
    }

    @Override
    public int hashCode() {
        return (int) ghUser.getId();
    }

    @Override
    public boolean equals(Object object) {
        Participant other = (Participant) object;
        return ghUser.getLogin().equals(other.ghUser.getLogin());
    }

    @Override
    public int compareTo(Object o) {
        Participant other = (Participant) o;
        return ghUser.getLogin().compareTo(other.ghUser.getLogin());
    }

    public double getParticipationRate() {
        return participatedStudy.size() / IssueRepository.getIssues().size() * 100;
    }


}
