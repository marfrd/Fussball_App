package de.eahjena.app.wi.fussball;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {

    public static boolean isLoaded = false;
    public static ArrayList<TableTeam> tableTeamList = new ArrayList<TableTeam>();
    public static ArrayList<Match> matchList = new ArrayList<Match>();
    public static ArrayList<Goal> goalList = new ArrayList<Goal>();


    public static Drawable getTeamIconByTeamName(String teamName) {
        for (int i = 0; i < tableTeamList.size(); i++) {
            //Log.d("","hhhh"+tableTeamList.get(i).teamName);
            if(tableTeamList.get(i).teamName.equals(teamName)) {
                return tableTeamList.get(i).teamIcon;
            }
        }
        return null;
    }
}
