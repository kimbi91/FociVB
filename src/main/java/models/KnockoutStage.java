package models;


public class KnockoutStage extends GroupStage{

    private Integer penaltiesA;
    private Integer penaltiesB;

    public KnockoutStage() {
    }

    public KnockoutStage(String csv) {
        super(csv);
        String[] parts = csv.split(";");
        this.penaltiesA = (Integer.parseInt(parts[7]));
        this.penaltiesB = (Integer.parseInt(parts[8]));
    }

    public Integer getPenaltiesA() {
        return penaltiesA;
    }

    public void setPenaltiesA(Integer penaltiesA) {
        this.penaltiesA = penaltiesA;
    }

    public Integer getPenaltiesB() {
        return penaltiesB;
    }

    public void setPenaltiesB(Integer penaltiesB) {
        this.penaltiesB = penaltiesB;
    }

    @Override
    public String toString() {
        return  super.getStage() + ";" +
                super.getDate() + ";" +
                super.getTeamA() + ";" +
                super.getTeamB() + ";" +
                super.getScoreA() + ";" +
                super.getScoreB() + ";" +
                penaltiesA + ";" +
                penaltiesB;
    }
}
