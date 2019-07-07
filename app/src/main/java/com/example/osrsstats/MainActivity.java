package com.example.osrsstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.osrsstats.osrsApiClient.OsrsApiService;

public class MainActivity extends AppCompatActivity {

    private OsrsApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new OsrsApiService();

        Button button = findViewById(R.id.btnScore);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtPlayerName = findViewById(R.id.txtPlayerName);
                service.getHiscoreByPlayerName(txtPlayerName.getText().toString());
            }
        });


    }

}
