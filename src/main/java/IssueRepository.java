import org.kohsuke.github.GHIssue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueRepository {
    public static Map<Integer, GHIssue> issues = new HashMap<>();

    public static void addIssue(int week, GHIssue Issue) {
        issues.put(week, Issue);
    }

    public static void addIssueListToMap(List<GHIssue> issues) {
        issues.stream()
                .forEach(issue -> addIssue(issue.getNumber(), issue));
    }

}
