import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    GitHub github;
    GHRepository repository;
    GHIssue issue;

    @BeforeEach
    void initialize() throws IOException {
        github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        repository = github.getRepository(Properties.TARGET_REPOSITORY);
        issue = repository.getIssues(GHIssueState.ALL).get(0);
        issue = repository.getIssues(GHIssueState.ALL).get(0);
    }

    @Test
    void parseWeekTest() throws IOException {
        String title = issue.getTitle();

        String regExp = "(\\d+)주차\\s?과제(\\D*)";
        assertTrue(title.matches(regExp));

        assertEquals("3", (title.split("\\D+"))[0]);

        assertEquals(3, Utility.parseWeek(issue.getTitle()));
    }

    @Test
    @DisplayName("라벨 집합을 넣으면 시즌x 라벨만 가져는 기능 테스트")
    void getLabelByNameTest() throws IOException {
        List<GHLabel> labels = (List<GHLabel>) issue.getLabels();
        assertEquals(repository.getLabel("시즌2"), Utility.getSeasonLabel(labels));
    }

    @Test
    @DisplayName("라벨 집합을 파라미터로 넣으면 시즌 라벨만 찾아서 시즌 넘버만 가져오는 기능 테스트")
    void parseSeasonTest() throws IOException {
        List<GHLabel> labels = (List<GHLabel>) issue.getLabels();
        assertEquals(2, Utility.parseSeason(labels));
    }
}