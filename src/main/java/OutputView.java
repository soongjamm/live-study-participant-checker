
import java.util.Collections;
import java.util.List;

public class OutputView {
    public static void printParticipants() {
//        List<String> participants = ParticipantRepository.getParticipantsName();
        List<Participant> participants = ParticipantRepository.getParticiants();
        Collections.sort(participants);
        participants.forEach(participant -> {
            double rate = participant.getParticipationRate();
            System.out.println(String.format("%-25s : %.2f %%", participant.getNickname() + "님의 참여율", rate));
        });
    }

    public static void updateNameByCommentsInProgressMessage(String title) {
        System.out.println(String.format("%s 이슈의 코멘트를 처리하는 중입니다.", title));
    }

}
