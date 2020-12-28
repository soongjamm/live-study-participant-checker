import java.util.Collections;
import java.util.List;

public class OutputView {
    public static void printParticipants() {
        List<String> names = ParticipantRepository.getParticipantsName();
        Collections.sort(names);
        names.stream()
                .forEach(name -> System.out.println(name));
    }

    public static void updateNameByCommentsInProgressMessage(int number) {
        System.out.println(String.format("%d주차 이슈의 코멘트를 처리하는 중입니다.", number));
    }
}
