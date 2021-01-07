import org.kohsuke.github.GHIssue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueRepository {
    private static Map<Study, GHIssue> issues = new HashMap<>();

    public static void addIssue(Study study, GHIssue issue) {
        issues.put(study, issue);
    }

    public static Map<Study, GHIssue> getIssues() {
        return issues;
    }

    public static boolean issueListToHashMap(List<GHIssue> issues) {
        issues.stream()
                .forEach(issue -> {
                    try {
                        Study study = new Study(Utility.parseWeek(issue.getTitle()), Utility.parseSeason(issue.getLabels()));
                        addIssue(study, issue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return true;
    }

    public static List<GHIssue> getIssuesBySeason(int season) {
        return issues.entrySet().stream()
                .filter(set -> set.getKey().compareSeason(season))
                .map(set -> set.getValue())
                .collect(Collectors.toList());
    }

    public static GHIssue getIssueBySeasonAndWeek(int week, int season) {
        return issues.get(new Study(week, season));
    }
}
