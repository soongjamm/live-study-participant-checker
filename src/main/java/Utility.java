import org.kohsuke.github.GHLabel;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Utility {
    public static int parseWeek(String title) {
        String regExp = "(\\d+)(\\D*)\\s?과제(\\D*)";

        if (title.matches(regExp)) {
            String weekString = (title.split("\\D+"))[0];
            return Integer.parseInt(weekString);
        }
        return -1;
//        throw new NullPointerException("몇 주차 정보가 존재하지 않습니다. title : " + title + "빠이");
    }

    public static int parseSeason(Collection<GHLabel> labelsCollection) {
        List<GHLabel> labels = (List<GHLabel>) labelsCollection;
        GHLabel label = getSeasonLabel(labels);
        if (label == null) {
            return -1;
        }
        String seasonLabelString = label.getName();
        String seasonString = Arrays.asList(seasonLabelString.split("\\D+"))
                .stream()
                .filter(str -> !str.equals(""))
                .findFirst()
                .get();

        return Integer.parseInt(seasonString);
    }

    public static GHLabel getSeasonLabel(Collection<GHLabel> labelsCollection) {
        List<GHLabel> labels = (List<GHLabel>) labelsCollection;
        String regExp = "시즌(\\d+)";

        Optional o =  labels.stream()
                .filter(label -> label.getName().matches(regExp))
                .findFirst();
        if (o.isPresent()) {
            return (GHLabel) o.get();
        }
        return null;
    }
}
