package models;

public class MatchFactory {

    public static Match createMatch(String csv) {
        String[] parts = csv.split(";");

        if (parts.length == 7) {
            return new GroupStage(csv);
        } else if (parts.length == 9) {
            return new KnockoutStage(csv);
        } else {
            return null;
        }
    }
}
