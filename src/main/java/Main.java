import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
            List<GHIssue> issues = github.getRepository(Properties.TARGET_REPOSITORY).getIssues(GHIssueState.ALL);
            for (GHIssue issue : issues) {
                System.out.println(issue.getUser().getName());
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
