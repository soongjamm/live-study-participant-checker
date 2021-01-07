import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

public class Application {

    static GitHub github;
    static GHRepository repository;
    static List<GHIssue> issues;

    public static void main(String[] args) {

        try {
            initialize();
            IssueRepository.issueListToHashMap(issues);
            ParticipantRepository.updateLatest();
            OutputView.printParticipants();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static public void initialize() throws IOException {
        github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        repository = github.getRepository(Properties.TARGET_REPOSITORY);
        issues = repository.getIssues(GHIssueState.ALL);
    };
}
