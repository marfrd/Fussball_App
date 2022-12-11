package de.eahjena.app.wi.fussball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityErgebnisse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnisse);


        Button btnBack = (Button)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityErgebnisse.this, MainActivity.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.ergebnisse);
        ErgebnisseAdapter ergebnisseAdapter = new ErgebnisseAdapter(this);
        recyclerView.setAdapter( ergebnisseAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );
    }
}