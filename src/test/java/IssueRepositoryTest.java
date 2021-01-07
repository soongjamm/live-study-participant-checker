import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {

    GitHub github;
    GHRepository repository;
    List<GHIssue> issues;
    GHIssue issue;


    @BeforeEach
    void initialize() throws IOException {
        github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        repository = github.getRepository(Properties.TARGET_REPOSITORY);
        issues = repository.getIssues(GHIssueState.ALL);
        issue = issues.get(0);
    }

    @Test
    @DisplayName("Issue 리스트를 IssueRepository에 HashSet으로 저장하는 테스트")
    void putIssuesTestIntoRepoTest() throws IOException {
        assertTrue(IssueRepository.issueListToHashMap(issues));
        assertFalse(IssueRepository.getIssues().isEmpty());
    }


    @Test
    @DisplayName("원하는 라벨링이된 이슈만 가져온다.")
    void getIssuesBySeasonTest() throws IOException {
        GHLabel season2Label = repository.getLabel("시즌2");

        IssueRepository.issueListToHashMap(issues);

        List<GHIssue> season2Issues = IssueRepository.getIssuesBySeason(2);
        assertTrue(
                season2Issues.stream()
                        .allMatch(issue -> {
                            try {
                                return issue.getLabels().contains(season2Label);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return false;
                        })
        );
    }

    @Test
    @DisplayName("시즌과 주차를 입력해서 이슈를 가져온다.")
    void getIssueBySeasonAndWeekTest() throws IOException {
        int week = 10;
        int season = 1;

        IssueRepository.issueListToHashMap(issues);

        GHIssue foundIssue = IssueRepository.getIssueBySeasonAndWeek(week, season);
        assertEquals("10주차 과제: 멀티쓰레드 프로그래밍", foundIssue.getTitle());
    }

}