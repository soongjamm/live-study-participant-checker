import org.kohsuke.github.GHIssue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueRepository {
    private static Map<Integer, GHIssue> issues = new HashMap<>();

    public static void addIssue(int week, GHIssue issue) {
        issues.put(week, issue);
    }

    public static void addIssueListToMap(List<GHIssue> issues) {
        issues.stream()
                .forEach(issue -> addIssue(issue.getNumber(), issue));
    }

    public static Map<Integer, GHIssue> getIssues() {
        return issues;
    }

    public static void showParticipant() {
        issues.forEach((x,y) -> System.out.println(x));
    }
}
