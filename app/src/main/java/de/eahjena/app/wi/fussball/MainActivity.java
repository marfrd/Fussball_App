package de.eahjena.app.wi.fussball;

import static de.eahjena.app.wi.fussball.MainApplication.goalList;
import static de.eahjena.app.wi.fussball.MainApplication.matchList;
import static de.eahjena.app.wi.fussball.MainApplication.tableTeamList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final Handler mainHandler = new Handler();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(!MainApplication.isLoaded) {
            new fetchData().start();
            MainApplication.isLoaded = true;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTable = (Button)findViewById(R.id.btn_tabelle);
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityTabelle.class);
                startActivity(i);
            }
        });

        Button btnErgebnisse = (Button)findViewById(R.id.btn_ergebnisse);
        btnErgebnisse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityErgebnisse.class);
                startActivity(i);
            }
        });

    }

    class fetchData extends Thread {

        String data = "";

        public void run() {


            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Daten werden geladen");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });


            // GOALS + MATCHES

            try {
                URL url = new URL("https://api.openligadb.de/getmatchdata/bl1");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }

                if(!data.isEmpty()) {
                    // JSONObject json = XML.toJSONObject(data);
                    JSONArray result = new JSONArray(data);

                    for (int i  = 0; i < result.length(); i++) {
                        JSONObject matchJson = result.getJSONObject(i);

                        // Goals
                        List<Goal> goals = new ArrayList<Goal>();
                        JSONArray goalsArray = matchJson.getJSONArray("goals");
                        for(int j = 0; j < goalsArray.length(); j++) {
                            JSONObject goalJson = goalsArray.getJSONObject(j);

                            int goalId = goalJson.getInt("goalID");
                            int goalGetterId = goalJson.getInt("goalGetterID");
                            String goalGetterName = goalJson.getString("goalGetterName");
                            int matchMinute = goalJson.getInt("matchMinute");
                            int scoreTeam1 = goalJson.getInt("scoreTeam1");
                            int scoreTeam2 = goalJson.getInt("scoreTeam2");
                            Goal goal = new Goal(
                                    goalId,
                                    goalGetterId,
                                    goalGetterName,
                                    matchMinute,
                                    scoreTeam1,
                                    scoreTeam2
                            );
                            goalList.add(goal);
                            goals.add(goal);
                        }

                        // Match
                        int matchID = matchJson.getInt("matchID");
                        String team1 = matchJson.getJSONObject("team1").getString("teamName");
                        String team2 = matchJson.getJSONObject("team2").getString("teamName");
                        String matchDateTime = matchJson.getString("matchDateTime");
                        String midResult = "";
                        String finalResult = "";
                        JSONArray resultsArray = matchJson.getJSONArray("matchResults");
                        for(int j = 0; j < resultsArray.length(); j++) {
                            JSONObject resultJson = resultsArray.getJSONObject(j);
                            if(resultJson.getInt("resultTypeID") == 1) {
                                midResult = resultJson.getString("pointsTeam1") + ":" + resultJson.getString("pointsTeam2");
                            } else if(resultJson.getInt("resultTypeID") == 2) {
                                finalResult = resultJson.getString("pointsTeam1") + ":" + resultJson.getString("pointsTeam2");
                            }
                        }
                        Match match = new Match(
                                matchID,
                                team1,
                                team2,
                                goals,
                                midResult,
                                finalResult,
                                matchDateTime
                        );
                        matchList.add(match);

                        // Log.d(" ", matchJson.toString());
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }



            // TABLETEAMS

            try {
                URL url = new URL("https://api.openligadb.de/getbltable/bl1/2022");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                data = "";
                while((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }

                if(!data.isEmpty()) {
                    JSONArray result = new JSONArray(data);

                    for (int i  = 0; i < result.length(); i++) {
                        JSONObject teamTable = result.getJSONObject(i);

                        int teamInfoId = teamTable.getInt("teamInfoId");
                        String teamName = teamTable.getString("teamName");
                        String shortName = teamTable.getString("shortName");
                        int matches = teamTable.getInt("matches");
                        int won = teamTable.getInt("won");
                        int draw = teamTable.getInt("draw");
                        int lost = teamTable.getInt("lost");
                        int goals = teamTable.getInt("goals");
                        int opponentGoals = teamTable.getInt("opponentGoals");
                        int points = teamTable.getInt("points");
                        String teamIconUrl = teamTable.getString("teamIconUrl");

                        TableTeam tableTeam = new TableTeam(
                                i + 1,
                                teamInfoId,
                                teamName,
                                shortName,
                                matches,
                                won,
                                draw,
                                lost,
                                goals,
                                opponentGoals,
                                points,
                                teamIconUrl
                        );


                        tableTeam.teamIcon = LoadImageFromWebOperations(teamIconUrl);
                        // while(tableTeam.teamIcon == null) {
                        //     tableTeam.teamIcon = LoadImageFromWebOperations(teamIconUrl);
                        // }

                        tableTeamList.add(tableTeam);
                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            });

            // Log.d("", "GOAL COUNT: "+goalList.size());
            // Log.d("", "MATCH COUNT: "+matchList.size());
            // Log.d("", "TEAM TABLE COUNT: "+tableTeamList.size());
        }
    }

    public static Drawable LoadImageFromWebOperations(String teamIconUrl) {
        try {
            InputStream is = (InputStream) new URL(teamIconUrl).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}