import org.kohsuke.github.GHIssue;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private List<GHIssue> participatedIssue = new ArrayList<>();

    public void participate(GHIssue issue){
        if (!participatedIssue.contains(issue)) {
            participatedIssue.add(issue);
        }
    }

    public List<GHIssue> getIssues() {
        return participatedIssue;
    }
}
