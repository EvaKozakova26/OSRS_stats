package com.example.osrsstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osrsstats.osrsApiClient.OsrsApiService;

public class MainActivity extends AppCompatActivity implements ComponentUpdateCallback {

    private OsrsApiService service;
    private TextView txtScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new OsrsApiService(this);

        txtScore = findViewById(R.id.txtHiscore);

        Button button = findViewById(R.id.btnScore);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtPlayerName = findViewById(R.id.txtPlayerName);
                service.getHiscoreByPlayerName(txtPlayerName.getText().toString());
            }
        });

    }

    @Override
    public void updateTextView(String myString) {
        txtScore.setText(myString);
    }
}
