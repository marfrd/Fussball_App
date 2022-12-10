package de.eahjena.app.wi.fussball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ActivityTabelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabelle);

        // ArrayList<TableTeam> tableTeamList = new ArrayList<TableTeam>();
        String[] tableTeamList = {"Volvo", "BMW", "Ford", "Mazda"};
        RecyclerView recyclerView = findViewById(R.id.team_table);
        // TableAdapter tableAdapter = new TableAdapter(this,tableTeamList);
        TableAdapter tableAdapter = new TableAdapter(tableTeamList);
        recyclerView.setAdapter( tableAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );
    }
}