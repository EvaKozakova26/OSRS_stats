package com.example.osrsstats;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osrsstats.osrsApiClient.OsrsApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ComponentUpdateCallback {

    @BindView(R.id.txtHiscore) TextView txtScore;
    @BindView(R.id.btnScore) Button btnGetScore;

    private OsrsApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        service = new OsrsApiService(this);

        btnGetScore.setOnClickListener(new View.OnClickListener() {
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
