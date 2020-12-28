import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.util.List;

public class Controller {

    protected static GitHub github;

    public void run() throws IOException {
        connectToGitHub();
        loadIssues();
        updateParticipant();
//        ParticipantRepository.showParticipant();
    }

    private void connectToGitHub() throws IOException {
        github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
    }

    private void loadIssues() throws IOException {
        List<GHIssue> issues = github.getRepository(Properties.TARGET_REPOSITORY).getIssues(GHIssueState.ALL);
        IssueRepository.addIssueListToMap(issues);
    }

    private void updateParticipant() {
        ParticipantRepository.updateLatest();
    }



}
