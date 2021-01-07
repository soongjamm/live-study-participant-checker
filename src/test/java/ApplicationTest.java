import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void connectGitHubTest() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();

        assertNotNull(github);
    }

    @Test
    void loadIssueTest() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        List<GHIssue> issues = github.getRepository(Properties.TARGET_REPOSITORY).getIssues(GHIssueState.ALL);

        assertNotNull(issues);
    }

}