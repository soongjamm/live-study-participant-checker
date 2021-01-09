import org.kohsuke.github.GHIssue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueRepository {
    private static Map<Study, GHIssue> issues = new HashMap<>();

    public static void addIssue(GHIssue issue) {
        int week;
        int season;
        try {
            week = Utility.parseWeek(issue.getTitle());
            season = Utility.parseSeason(issue.getLabels());
            if (week == -1 || season == -1) {
                return;
            }
            Study study = new Study(week, season);
            issues.put(study, issue);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Map<Study, GHIssue> getIssues() {
        return issues;
    }

    public static boolean addIssues(List<GHIssue> issues) {
        issues.stream()
                .forEach(issue -> {
                        addIssue(issue);
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
