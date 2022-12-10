package de.eahjena.app.wi.fussball;

import java.util.List;

public class Match {
    int matchId;
    String team1;
    String team2;
    List<Goal> goals;
    String midResult;
    String finalResult;
    String matchDateTime;

    public Match(int matchId, String team1, String team2, List<Goal> goals, String midResult, String finalResult, String matchDateTime) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.goals = goals;
        this.midResult = midResult;
        this.finalResult = finalResult;
        this.matchDateTime = matchDateTime;
    }
}
