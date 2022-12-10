package de.eahjena.app.wi.fussball;

public class TableTeam {
    int place;
    int teamInfoId;
    String teamName;
    String shortName;
    int matches;
    int won;
    int draw;
    int lost;
    int goals;
    int opponentgoals;
    int points;
    String teamIconUrl;

    public TableTeam(int place, int teamInfoId, String teamName, String shortName, int matches, int won, int draw, int lost, int goals, int opponentgoals, int points, String teamIconUrl) {
        this.place = place;
        this.teamInfoId = teamInfoId;
        this.teamName = teamName;
        this.shortName = shortName;
        this.matches = matches;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.goals = goals;
        this.opponentgoals = opponentgoals;
        this.points = points;
        this.teamIconUrl = teamIconUrl;
    }
}
