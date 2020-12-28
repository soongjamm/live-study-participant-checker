
import java.util.Collections;
import java.util.List;

public class OutputView {
    public static void printParticipants() {
        List<String> nicknames = ParticipantRepository.getParticipantsName();
        Collections.sort(nicknames);
        nicknames.stream()
                .forEach(nickname -> {
                    Float participationRate = ParticipantRepository
                            .findParticipantByNickname(nickname).getParticipationRate();
                    System.out.println(String.format("%-25s : %.2f %%", nickname + "님의 참여율", participationRate));
                });
    }

    public static void updateNameByCommentsInProgressMessage(int issueNumber) {
        System.out.println(String.format("%d주차 이슈의 코멘트를 처리하는 중입니다.", issueNumber));
    }

}
