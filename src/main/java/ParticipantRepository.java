
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ParticipantRepository {

    static private Map<String, Participant> participants = new HashMap<>();

    public static List<Participant> getParticiants() {
        return participants.values().stream()
                .collect(Collectors.toList());
    }

    public static Participant addParticipant(GHUser user) {
        Participant participant;
        if (hasParticipant(user)) {
            participant = getByNickname(user.getLogin());
            return participant;
        }
        participant = new Participant(user);
        participants.put(user.getLogin(), participant);
        return participant;

    }

    public static void addParticipantsByIssue(GHIssue issue) throws IOException {
        List<GHIssueComment> comments = issue.getComments();
        comments.parallelStream().forEach(comment -> {
            try {
                Participant participant = addParticipant(comment.getUser());
                participant.participateStudy(issue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static int addAllParticipant(List<GHIssue> issues) {
        issues.parallelStream().forEach(issue -> {
            try {
                OutputView.updateNameByCommentsInProgressMessage(issue.getTitle());
                addParticipantsByIssue(issue);
                IssueRepository.addIssue(issue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return participants.size();
    }

    public static boolean hasParticipant(GHUser user) {
        if (participants.containsKey(user.getLogin())) {
            return true;
        }
        return false;
    }

    public static Participant getByNickname(String nickname) {
        return participants.get(nickname);
    }

    public static List<String> getParticipantsName() {
        return new ArrayList<>(participants.keySet());
    }



}
