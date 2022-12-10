package de.eahjena.app.wi.fussball;

import static de.eahjena.app.wi.fussball.MainApplication.goalList;
import static de.eahjena.app.wi.fussball.MainApplication.matchList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity {

    Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new fetchData().start();

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

    }

    class fetchData extends Thread {

        String data = "";

        public void run() {

            try {
                URL url = new URL("https://api.openligadb.de/getmatchdata/bl1/2020/8");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while((line = bufferedReader.readLine()) != null) {

                    data = data + line;

                }

                if(!data.isEmpty()) {
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


                        Log.d(" ", matchJson.toString());
                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("", "hhhhhh"+result.toString());
                }
            });
            */
        }
    }
}