
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantRepository {

    static private Map<String, GHUser> participants = new HashMap<>();

    public static void updateLatest() {
        IssueRepository.getIssues()
                .entrySet()
                .parallelStream()
                .forEach((issue) -> {
                    try {
                        OutputView.updateNameByCommentsInProgressMessage(issue.getValue().getNumber());
                        addParticipantByComments(issue.getValue().getComments());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void addParticipant(String nickname, GHUser participant) {
        participants.put(nickname, participant);
    }

    private static void addParticipantByComments(List<GHIssueComment> comments) {
        comments.parallelStream()
                .forEach(comment -> {
                    try {
                        GHUser gitHubUser = comment.getUser();
                        addParticipant(gitHubUser.getLogin(), gitHubUser);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static List<String> getParticipantsName() {
        return new ArrayList<>(participants.keySet());
    }

}
