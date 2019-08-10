package com.example.osrsstats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osrsstats.activities.HiscoreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnScore) Button btnGetScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        btnGetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HiscoreActivity.class);
                EditText txtPlayerName = findViewById(R.id.txtPlayerName);
                intent.putExtra("playerName", txtPlayerName.getText().toString());
                startActivity(intent);
            }
        });

    }
}
