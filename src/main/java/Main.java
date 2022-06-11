import models.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<WorldCup> worldCups = new ArrayList<>();
        List<Match> matches = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        try {
            worldCups = loadWordCupData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            matches = loadMatchData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        associateMatchWithWordCup(matches, worldCups);

        System.out.println("Number of matches: " + matches.size());
        System.out.println("Name of the host: ");
        String line = sc.nextLine();
        System.out.println("2. Maximum goal difference: " + biggestGoalDifference(line, matches));
        System.out.println("3. The player has won " + numberOfWonBets(matches, line) + " bets.");
        System.out.println("4. Total goals by stage:");
        Map<String, Integer> summary = numberOfGolasPerStages(matches, line);
        writeSummaryOfGoals(numberOfGolasPerStages(matches, line));

        try {
            saveSelected(line, matches);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static List<WorldCup> loadWordCupData() throws IOException {
        List<WorldCup> worldCups = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("worldcups.csv"))){
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                worldCups.add(new WorldCup(line));
            }
        }

        return worldCups;
    }

    private static List<Match> loadMatchData() throws IOException {
        List<Match> matches = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("matches_all.csv"))){
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                matches.add(MatchFactory.createMatch(line));
            }
        }

        return matches;
    }

    private static void associateMatchWithWordCup(List<Match> matches, List<WorldCup> worldCups) {

        for (Match match : matches) {
            if (match.getClass().getSimpleName().equalsIgnoreCase("groupstage")) {
                GroupStage temp = (GroupStage) match;
                for (WorldCup worldCup : worldCups) {
                    if (worldCup.getYear() == temp.getYear()) {
                        match.setWorldCup(worldCup);
                    }
                }
            } else if (match.getClass().getSimpleName().equalsIgnoreCase("knockoutstage")) {
                KnockoutStage temp = (KnockoutStage) match;
                for (WorldCup worldCup : worldCups) {
                    if (worldCup.getYear() == temp.getYear()) {
                        match.setWorldCup(worldCup);
                    }
                }
            }
        }
    }

    private static List<Match> getSelected(List<Match> matches, String name) {
        List<Match> selected = new ArrayList<>();

        for (Match match : matches) {
            if (match.getWorldCup().getHost().equalsIgnoreCase(name)) {
                selected.add(match);
            }
        }

        return selected;
    }

    private static void saveSelected(String line, List<Match> matches) throws IOException{
        List<Match> selected = getSelected(matches, line);

        try (PrintWriter writer = new PrintWriter(new FileWriter("selected.csv"))){
            writer.println("stage;date;team_a;team_b;goals_a;goals_b;penalties_a;penalties_b");

            for (Match match : selected) {
                if (match.getClass().getSimpleName().equalsIgnoreCase("groupstage")) {
                    GroupStage temp = (GroupStage) match;
                    writer.println(temp.toString());
                } else if (match.getClass().getSimpleName().equalsIgnoreCase("knockoutstage")) {
                    KnockoutStage temp = (KnockoutStage) match;
                    writer.println(temp.toString());
                }
            }
        }
    }

    private static int biggestGoalDifference(String worldcup, List<Match> matches) {
        List<Match> selected = getSelected(matches, worldcup);
        int biggestDiff = 0;

        for (Match match : selected) {
            if (match.getClass().getSimpleName().equalsIgnoreCase("groupstage")) {
                GroupStage temp = (GroupStage) match;
                if (Math.abs((temp.getScoreA() - temp.getScoreB())) > biggestDiff) {
                    biggestDiff = Math.abs((temp.getScoreA() - temp.getScoreB()));
                }
            } else if (match.getClass().getSimpleName().equalsIgnoreCase("knockoutstage")) {
                KnockoutStage temp = (KnockoutStage) match;
                if (Math.abs((temp.getScoreA() - temp.getScoreB())) > biggestDiff) {
                    biggestDiff = Math.abs((temp.getScoreA() - temp.getScoreB()));
                }
            }
        }

        return biggestDiff;
    }

    private static int numberOfWonBets(List<Match> matches, String worldcup) {
        List<Match> selected = getSelected(matches, worldcup);
        int won = 0;

        for (Match match : selected) {
            if (match.getClass().getSimpleName().equalsIgnoreCase("groupstage")) {
                GroupStage temp = (GroupStage) match;
                if (temp.getScoreA() > temp.getScoreB()) {
                    won++;
                }
            } else {
                KnockoutStage temp = (KnockoutStage) match;
                if (temp.getPenaltiesA() != null && temp.getPenaltiesB() != null) {
                    if (temp.getPenaltiesA() > temp.getPenaltiesB()) {
                        won++;
                    }
                } else {
                    if (temp.getScoreA() > temp.getScoreB()) {
                        won++;
                    }
                }
            }
        }

        return won;
    }

    private static Map<String, Integer> numberOfGolasPerStages(List<Match> matches, String worldcup) {
        List<Match> selected = getSelected(matches, worldcup);
        Map<String, Integer> summary = new LinkedHashMap<>();

        for (Match match : selected) {
            GroupStage temp = (GroupStage) match;
            Integer sum = temp.getScoreA() + temp.getScoreB();

            if (summary.containsKey(temp.getStage())) {
                int current = summary.get(temp.getStage());
                summary.put(temp.getStage(), current + sum);
            }
            summary.putIfAbsent(temp.getStage(), sum);

        }


        return summary;
    }

    private static void writeSummaryOfGoals(Map<String, Integer> summary) {
        Iterator<Map.Entry<String, Integer>> itr = summary.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();

            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
