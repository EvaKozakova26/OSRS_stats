package com.example.osrsstats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.osrsstats.activities.OverallScoreActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_stats:
                    mTextMessage.setText(R.string.title_stats);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_grand_exchange);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @BindView(R.id.btnScore) Button btnGetScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        btnGetScore.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OverallScoreActivity.class);
            EditText txtPlayerName = findViewById(R.id.txtPlayerName);
            intent.putExtra("playerName", txtPlayerName.getText().toString());
            startActivity(intent);
        });

    }
}
