package models;


public class GroupStage extends Match{

    private int year;
    private String stage;
    private String date;
    private int scoreA;
    private int scoreB;

    public GroupStage() {
    }

    public GroupStage(String csv) {
        super(csv);
        String[] parts = csv.split(";");
        this.year = Integer.parseInt(parts[0]);
        this.stage = parts[1];
        this.date = parts[2];
        this.scoreA = (Integer.parseInt(parts[5]));
        this.scoreB = (Integer.parseInt(parts[6]));
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    @Override
    public String toString() {
        return  stage + ";" +
                date + ";" +
                super.getTeamA() + ";" +
                super.getTeamB() + ";" +
                scoreA + ";" +
                scoreB;
    }
}
