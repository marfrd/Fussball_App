package de.eahjena.app.wi.fussball;

import static java.lang.System.exit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

        Button btnBack = (Button)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityTabelle.this, MainActivity.class);
                startActivity(i);
            }
        });

        /*
        JsonReader reader = new JsonReader();
        reader.execute("https://www.openligadb.de/api/getmatchdata/bl1");
        https://api.openligadb.de/getmatchdata/bl1/2020/8
        */

        RecyclerView recyclerView = findViewById(R.id.team_table);
        TableAdapter tableAdapter = new TableAdapter(this);
        recyclerView.setAdapter( tableAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );
    }
}