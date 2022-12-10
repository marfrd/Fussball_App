package de.eahjena.app.wi.fussball;

import static java.lang.System.exit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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

public class ActivityTabelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabelle);

        ArrayList<TableTeam> tableTeamList = new ArrayList<TableTeam>();
        tableTeamList.add(new TableTeam(
                23,
                34,
                "TEST",
                "PEST",
                45,
                56,
                67,
                78,
                98,
                13,
                21,
                "a_1_fsv_mainz_05"
        ));

        /*
        JsonReader reader = new JsonReader();
        reader.execute("https://www.openligadb.de/api/getmatchdata/bl1");
        https://api.openligadb.de/getmatchdata/bl1/2020/8
        */

        RecyclerView recyclerView = findViewById(R.id.team_table);
        TableAdapter tableAdapter = new TableAdapter(this, tableTeamList);
        recyclerView.setAdapter( tableAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );
    }
}