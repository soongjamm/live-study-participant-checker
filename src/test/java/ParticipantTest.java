import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @Test
    @DisplayName("참여자 객체에 참여한 스터디를 추가한다.")
    void addParticipatedStudy() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        GHIssue issue = github.getRepository(Properties.TARGET_REPOSITORY).getIssue(2);
        GHUser user = issue.getComments().get(0).getUser();

        Participant participant = new Participant(user);
        participant.participateStudy(issue);
        assertNotNull(participant.getParticipatedStudyList());
    }

    @Test
    @DisplayName("참여자 레포지토리에 참여자 객체를 추가한다.")
    void addParticipantTest() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        GHIssue issue = github.getRepository(Properties.TARGET_REPOSITORY).getIssue(8);
        List<GHIssueComment> comments = issue.getComments();

        int participantCount = 0;

        for (GHIssueComment comment : comments) {
            Participant result = ParticipantRepository.addParticipant(comment.getUser());
            if (result != null) {
                participantCount++;
            }
        }
        System.out.println(participantCount+ " .. " + comments.size());
        assertEquals(participantCount, ParticipantRepository.getParticiants().size());
    }

    @Test
    void addAllParticipantsTest() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        List<GHIssue> issues = github.getRepository(Properties.TARGET_REPOSITORY).getIssues(GHIssueState.ALL);

        ParticipantRepository.addAllParticipant(issues);
        assertEquals(236, ParticipantRepository.getParticiants().size());
    }

    @Test
    void getPartifipantByNicknameTest() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        GHUser user = github.getRepository(Properties.TARGET_REPOSITORY).getIssue(8).getUser();

        ParticipantRepository.addParticipant(user);

        assertEquals(user.getLogin(), ParticipantRepository.getByNickname(user.getLogin()).getNickname());
    }

    @Test
    void printRateTest() throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(Properties.GITHUB_PERSONAL_TOKEN).build();
        GHRepository repository = github.getRepository(Properties.TARGET_REPOSITORY);

        GHIssue issue = repository.getIssue(8);
        ParticipantRepository.addParticipantsByIssue(issue);
        IssueRepository.addIssue(issue);


        List<String> participants = ParticipantRepository.getParticipantsName();
        participants.parallelStream().forEach(participant -> {
            double rate = ParticipantRepository.getByNickname(participant).getParticipationRate();
            System.out.println(String.format("%-25s : %.2f %%", participant + "님의 참여율", rate));
        });
    }

}