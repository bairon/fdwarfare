package wof.warfare;

import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Round {
    public int index;
    public List<FormationReport> attacking;
    public List<FormationReport> defending;

    @Override
    public String toString() {
        return "============== Round " + index + " ===============\n" +
                "\nАтакующие потери" + attacking.stream().map(FormationReport::toString).collect(Collectors.joining()) +
                "\n\nЗащитники потери" + defending.stream().map(FormationReport::toString).collect(Collectors.joining()) +
                "\n";
    }
}
