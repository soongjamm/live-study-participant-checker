
import org.kohsuke.github.GHIssueComment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantRepository {

    static private Map<String, Participant> participants = new HashMap<>();

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

    private static void addParticipant(String nickname, Participant participant) {
        participants.put(nickname, participant);
    }

    private static void addParticipantByComments(List<GHIssueComment> comments) {
        comments.parallelStream()
                .forEach(comment -> {
                    try {
                        String nickname = comment.getUser().getLogin();
                        Participant participant = findParticipantByNicknameElseCreate(nickname);
                        participant.participateStudy(comment.getParent());
                        addParticipant(nickname, participant);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static Participant findParticipantByNicknameElseCreate(String nickname) {
        if (participants.containsKey(nickname)) {
            return participants.get(nickname);
        }
        return new Participant();
    }

    public static List<String> getParticipantsName() {
        return new ArrayList<>(participants.keySet());
    }

}
