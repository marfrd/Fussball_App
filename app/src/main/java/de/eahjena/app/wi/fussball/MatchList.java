package de.eahjena.app.wi.fussball;

import java.util.List;

public class MatchList {
    String LeagueName;
    int groupId;
    int groupOrderId;
    String name;
    List<Match> matches;

    public MatchList(String leagueName, int groupId, int groupOrderId, String name, List<Match> matches) {
        LeagueName = leagueName;
        this.groupId = groupId;
        this.groupOrderId = groupOrderId;
        this.name = name;
        this.matches = matches;
    }
}
