package de.eahjena.app.wi.fussball;

import static de.eahjena.app.wi.fussball.MainApplication.getTeamIconByTeamName;
import static de.eahjena.app.wi.fussball.MainApplication.matchList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ActivitySpiel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);


        Button btnBack = (Button)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivitySpiel.this, ActivityErgebnisse.class);
                startActivity(i);
            }
        });


        ImageView ivTeamLogo1 = (ImageView)findViewById(R.id.iv_teamLogo1);
        ImageView ivTeamLogo2 = (ImageView)findViewById(R.id.iv_teamLogo2);
        TextView tvFinalResult = (TextView)findViewById(R.id.tv_finalResult);
        TextView tvMidResult = (TextView)findViewById(R.id.tv_midResult);
        TextView tvTeamName1 = (TextView)findViewById(R.id.tv_teamName1);
        TextView tvTeamName2 = (TextView)findViewById(R.id.tv_teamName2);
        TextView tvMatchDateTime = (TextView)findViewById(R.id.tv_matchDateTime);

        Bundle bundle = getIntent().getExtras();

        if(bundle.get("matchId")!= null)
        {
            int matchId = bundle.getInt("matchId");
            Match match = MainApplication.getMatchByMatchId(matchId);

            ivTeamLogo1.setImageDrawable(getTeamIconByTeamName(match.team1));
            ivTeamLogo2.setImageDrawable(getTeamIconByTeamName(match.team2));
            tvFinalResult.setText(match.finalResult);
            tvMidResult.setText(match.midResult);
            tvTeamName1.setText(match.team1);
            tvTeamName2.setText(match.team2);

            String[] dateTimeSplit = match.matchDateTime.split("T");
            String[] dateTimeSplit2 = dateTimeSplit[1].split("Z");

            // 2022-11-12T14:30:00Z
            SimpleDateFormat from1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat to1 = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat from2 = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat to2 = new SimpleDateFormat("HH:mm");
            try {
                String finalDateTime1 = to1.format(from1.parse(dateTimeSplit[0]));
                String finalDateTime2 = to2.format(from2.parse(dateTimeSplit2[0]));
                tvMatchDateTime.setText("Spielstart: " + finalDateTime1 + " " + finalDateTime2 + " Uhr");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            RecyclerView recyclerView = findViewById(R.id.goals);
            GoalsAdapter goalsAdapter = new GoalsAdapter(this, match.goals);
            recyclerView.setAdapter( goalsAdapter );
            recyclerView.setLayoutManager( new LinearLayoutManager(this) );
        }
    }
}