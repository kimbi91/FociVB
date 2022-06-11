package models;

public class Match {

    private WorldCup worldCup;

    private String teamA;
    private String teamB;


    public Match() {
    }

    public Match(String csv) {
        String[] parts = csv.split(";");
        this.teamA = parts[3];
        this.teamB = parts[4];
    }

    public WorldCup getWorldCup() {
        return worldCup;
    }

    public void setWorldCup(WorldCup worldCup) {
        this.worldCup = worldCup;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

}
