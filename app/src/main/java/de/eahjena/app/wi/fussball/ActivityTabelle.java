package de.eahjena.app.wi.fussball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
                "URL"
        ));
        // String[] tableTeamList = {"Volvo", "BMW", "Ford", "Mazda"};
        RecyclerView recyclerView = findViewById(R.id.team_table);
        // TableAdapter tableAdapter = new TableAdapter(this,tableTeamList);
        TableAdapter tableAdapter = new TableAdapter(tableTeamList);
        recyclerView.setAdapter( tableAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );
    }
}