package models;

public class WorldCup {

    private int year;
    private String host;
    private String confederation;
    private String dateFrom;
    private String dateTo;

    public WorldCup() {
    }

    public WorldCup(String csv) {
        String[] parts = csv.split(";");
        this.year = Integer.parseInt(parts[0]);
        this.host = parts[1];
        this.confederation = parts[2];
        this.dateFrom = parts[3];
        this.dateTo = parts[4];
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConfederation() {
        return confederation;
    }

    public void setConfederation(String confederation) {
        this.confederation = confederation;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "WorldCup{" +
                "year=" + year +
                ", host='" + host + '\'' +
                ", confederation='" + confederation + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                '}';
    }
}
