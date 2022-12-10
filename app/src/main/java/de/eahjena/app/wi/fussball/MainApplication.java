package de.eahjena.app.wi.fussball;

import android.app.Application;

import java.util.ArrayList;

public class MainApplication extends Application {

    public static ArrayList<TableTeam> tableTeamList = new ArrayList<TableTeam>();
    public static ArrayList<Match> matchList = new ArrayList<Match>();
    public static ArrayList<Goal> goalList = new ArrayList<Goal>();
}
