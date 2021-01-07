import org.kohsuke.github.GHIssue;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private List<GHIssue> participatedIssue = new ArrayList<>();

    public void participateStudy(GHIssue issue) {
        if (!participatedIssue.contains(issue)) {
            participatedIssue.add(issue);
        }
    }

    public float getParticipationRate() {
        System.out.println(IssueRepository.getIssues().size() + " .. " + participatedIssue.size());
        return IssueRepository.getIssues().size() / participatedIssue.size();
    }

}
