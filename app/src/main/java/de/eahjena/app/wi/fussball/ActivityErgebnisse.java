package de.eahjena.app.wi.fussball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ActivityErgebnisse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnisse);

        RecyclerView recyclerView = findViewById(R.id.ergebnisse);
        ErgebnisseAdapter ergebnisseAdapter = new ErgebnisseAdapter(this);
        recyclerView.setAdapter( ergebnisseAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );
    }
}